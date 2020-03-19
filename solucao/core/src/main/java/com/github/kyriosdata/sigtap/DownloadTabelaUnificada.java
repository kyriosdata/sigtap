package com.github.kyriosdata.sigtap;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Executa download de tabela unificada de procedimentos conforme a
 * configuraçao padrão disponível no momento em que esta classe é escrita.
 * Caso o servidor, o diretório e/ou o formato do nome dos arquivos seja
 * alterado, então isto deve ser indicado por meio de variáveis de ambiente,
 * conforme definido abaixo.
 *
 * <p>O servidor de ftp
 * deve ser definido pela variável de ambiente DATASUS_FTP_SERVER. O
 * diretório no qual os arquivos são consultados é definido pela variável de
 * ambiente DATASUS_FTP_DIR. O formato padrão dos arquivos é estabelecido
 * pela variável de ambiente DATASUS_FTP_FMT.</p>
 */
public class DownloadTabelaUnificada {

    private static final Logger logger =
            LoggerFactory.getLogger(DownloadTabelaUnificada.class);
    private static final String FTP_SERVER = "ftp2.datasus.gov.br";
    private static final String DIRECTORY = "/pub/sistemas/tup/downloads/";
    private static final String FORMAT = "TabelaUnificada_%s_v";

    /**
     * Executa download do arquivo contendo a tabela unificada de procedimentos
     * para a competência fonecida no formato (yyyymm). O arquivo
     * correspondente é depositado no diretório indicado.
     *
     * @param competencia Competência no formato yyyyMM. Ou seja, ano
     *                    fornecido (4 dígitos) e mês usando dois dígitos.
     *                    Por exemplo¸ para janeiro de 2001 a competência
     *                    deve ser "200101".
     * @param dir O diretório onde o arquivo contendo a tabela unificada de
     *            procedimentos para a competência indicada será depositado.
     *
     * @return Instância para o arquivo depositado no diretório indicado se o
     * download foi executado de forma satisfatória. Ou {@code null}, caso
     * nenhum arquivo seja encontrado para a competência fornecida.
     *
     * @throws RuntimeException Se ocorreu situação excepcional que impede o
     * download da tabela unificada.
     */
    public static File baixar(String competencia, String dir) {
        List<String> arquivos = getNomesArquivosPara(competencia);
        if (arquivos.isEmpty()) {
            logger.info("não foi localizado arquivo para competência {}",
                    competencia);
            return null;
        }

        final String arquivo = arquivos.get(0);

        if (arquivos.size() > 1) {
            logger.warn("mais de um arquivo para competência {}, usando " +
                    "arquivo {}", competencia, arquivo);
        }

        File file = downloadTabela(arquivo, dir);
        logger.info("arquivo {} baixado para competencia {}", arquivo,
                competencia);

        return file;
    }

    public static File downloadTabela(String arquivo,
                                      String dir) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ftpServer());
            ftpClient.login("anonymous", "");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            File file = new File(dir + File.separator + arquivo);
            FileOutputStream fos = new FileOutputStream(file);
            OutputStream os = new BufferedOutputStream(fos);
            ftpClient.retrieveFile(directory() + arquivo, os);
            os.close();
            ftpClient.logout();
            ftpClient.disconnect();
            return file;
        } catch (IOException exp) {
            logger.error(exp.getMessage());
            throw new RuntimeException("erro ao realizar download");
        }
    }

    public static List<String> getNomesArquivosPara(String competencia) {
        final String prefixo = String.format(format(), competencia);
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ftpServer());
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.login("anonymous", "");

            FTPFile[] files = ftpClient.listFiles(directory());

            List<String> arquivos = Arrays.stream(files)
                    .filter(f -> f.getName().startsWith(prefixo))
                    .map(s -> s.getName())
                    .collect(Collectors.toList());

            ftpClient.logout();
            ftpClient.disconnect();

            return arquivos;
        } catch (IOException exp) {
            logger.error(exp.getMessage());
            throw new RuntimeException("erro ao listar arquivos de ftp server");
        }
    }

    private static String ftpServer() {
        final String server = System.getenv("DATASUS_FTP_SERVER");
        if (server == null) {
            return FTP_SERVER;
        }

        return server;
    }

    private static String directory() {
        final String dir = System.getenv("DATASUS_FTP_DIR");
        if (dir == null) {
            return DIRECTORY;
        }

        return dir;
    }

    private static String format() {
        final String format = System.getenv("DATASUS_FTP_FMT");
        if (format == null) {
            return FORMAT;
        }

        return format;
    }
}