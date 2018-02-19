package com.mycompany.app;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;

public class BigFile {
    private static String dirname = "data";

    /**
     * 调用创建文件指令
     * @param filename
     * @param countOfColumn
     * @throws IOException
     */
    public void create(String filename, int countOfColumn) throws IOException {
        File fd = new File(this.createBigFilePath(filename));
        FileOutputStream stream = new FileOutputStream(fd);
        BufferedOutputStream buff = new BufferedOutputStream(stream);
//      buff.write("id\tname\tscore\n".getBytes());
        for (int i = 0; i < countOfColumn; i++) {
            String content = this.createBigFileContent(i);
            buff.write(content.getBytes());
        }
        buff.flush();
        buff.close();
    }

    /**
     * 调用排序指令
     * @param filename
     * @throws IOException
     */
    public void sort(String filename) throws IOException {
        LinkedList<Record> records = this.read(filename);
        this.sortRecords(records);
    }

    /**
     * 读取数据
     * @param filename
     * @return
     * @throws IOException
     */
    private LinkedList<Record> read(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/" + filename));
        LinkedList<Record> container = new LinkedList<>();
        String sCurrentLine;
        while ((sCurrentLine = br.readLine()) != null) {
            Record r = new Record();
            String[] _r = sCurrentLine.split("\t");

            if (_r.length != 3) continue;

            r.setId(Integer.parseInt(_r[0]));
            r.setName(_r[1]);
            r.setScroce(Integer.parseInt(_r[2]));
            container.push(r);
        }
        return container;
    }

    /**
     * 排序
     * @param records
     */
    private void sortRecords (LinkedList<Record> records) {
//        BubbleSort sorter = new BubbleSort();
        MergeSort sorter = new MergeSort();
        long startTime = System.currentTimeMillis();
        sorter.sort(records);
        System.out.println(String.format("Duration: %d", System.currentTimeMillis() - startTime));
//        System.out.println(records);
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
