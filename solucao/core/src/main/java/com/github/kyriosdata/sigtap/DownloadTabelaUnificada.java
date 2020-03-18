package com.github.kyriosdata.sigtap;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.util.Arrays;

public class DownloadTabelaUnificada {

    public static final String FTP_SERVER = "ftp2.datasus.gov.br";
    public static final String DIRECTORY = "/pub/sistemas/tup/downloads";

    public static void main(String[] args) throws Exception {
        getTabelaPara();
        return;
    }

    public static void getTabelaPara() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(FTP_SERVER);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.login("anonymous", "");

        FTPFile[] files = ftpClient.listFiles(DIRECTORY);

        Arrays.stream(files)
                .filter(f -> f.getName().startsWith("TabelaUnificada"))
                .forEach(System.out::println);

        ftpClient.logout();
        ftpClient.disconnect();
    }
}