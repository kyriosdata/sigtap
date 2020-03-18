package com.github.kyriosdata.sigtap;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DownloadTabelaUnificada {

    public static final String FTP_SERVER = "ftp2.datasus.gov.br";
    public static final String DIRECTORY = "/pub/sistemas/tup/downloads";
    public static final String PREFIXO = "TabelaUnificada_%s_v";

    public static void main(String[] args) {
        List<String> arquivos = getNomesArquivosPara("202003");
        arquivos.stream().forEach(System.out::println);

    }

    public static List<String> getNomesArquivosPara(String competencia) {
        final String prefixo = String.format(PREFIXO, competencia);
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(FTP_SERVER);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.login("anonymous", "");

            FTPFile[] files = ftpClient.listFiles(DIRECTORY);

            List<String> arquivos = Arrays.stream(files)
                    .filter(f -> f.getName().startsWith(prefixo))
                    .map(s -> s.getName())
                    .collect(Collectors.toList());

            ftpClient.logout();
            ftpClient.disconnect();

            return arquivos;
        } catch (IOException exp) {
            throw new RuntimeException(exp);
        }
    }
}