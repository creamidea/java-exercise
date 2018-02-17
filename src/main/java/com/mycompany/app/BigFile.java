package com.mycompany.app;

import java.io.*;
import java.util.Random;

public class BigFile {
    private static String dirname = "data";

    public void create(String filename, int countOfColumn) {
        File fd = new File(this.createBigFilePath(filename));
        try {
            FileOutputStream stream = new FileOutputStream(fd);
            BufferedOutputStream buff = new BufferedOutputStream(stream);
            try {
                buff.write("id\tname\tscore\n".getBytes());
                for (int i = 0; i < countOfColumn; i++) {
                    String content = this.createBigFileContent(i);
                    buff.write(content.getBytes());
                }
                buff.flush();
                buff.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void sort(String filename) {
        System.out.println(filename);
    }

    private void read(String path) {
        System.out.println(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader("README.md"));
            String sCurrentLine;
            try {
                while ((sCurrentLine = br.readLine()) != null) {
                    System.out.println(sCurrentLine);
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    private String createBigFilePath(String filename) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(BigFile.dirname));
        sb.append('/');
        sb.append(String.valueOf(filename));
        return sb.toString();
    }

    private String createBigFileContent(int idx) {
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();
        String alphabeta = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        int nameOfLen = rd.nextInt(10) + 6;
        int i;

        sb.append(idx);
        sb.append('\t');
        for (i = 0; i < nameOfLen; i++) {
            int letterOfIndex = rd.nextInt(26);
            sb.append(alphabeta.charAt(letterOfIndex));
        }
        sb.append('\t');
        sb.append(Math.round(rd.nextFloat() * 100));
        sb.append('\n');

        return sb.toString();
    }
}
