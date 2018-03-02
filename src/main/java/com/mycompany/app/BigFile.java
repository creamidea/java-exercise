package com.mycompany.app;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class BigFile {
    private static String dirname = "data";
    private static Comparator<Record> nameComparator = Comparator.comparing(Record::getName);
    private static Comparator<Record> scoreComparator = Comparator.comparing(Record::getScore);
    private static Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    };

    /**
     * 调用创建文件指令
     *
     * @param filename
     * @param countOfColumn
     * @throws IOException
     */
    public void create(String filename, int countOfColumn) throws IOException {
        File fd = new File(this.createBigFilePath(filename));
        FileOutputStream stream = new FileOutputStream(fd);
        BufferedOutputStream buff = new BufferedOutputStream(stream);
//      buff.write("id\tname\tscore\n".getBytes());
        for (int i = 1; i <= countOfColumn; i++) {
            String content = this.createBigFileRecord(i);
            buff.write(content.getBytes());
        }
        buff.flush();
        buff.close();
    }

    /**
     * 调用排序指令
     *
     * @param filename
     * @throws IOException
     */
    public void sort(String filename) throws IOException {
        ChunkMergeSort sorter = new ChunkMergeSort(filename, comparator);
        sorter.sort();

        //        Reader reader = new Reader(filename);
//        while (true) {
//            List<String> lines = reader.readNext(2000000);
//            if (lines.size() == 0) break;
//        }
//        List<Record> records = this.read(filename);

//        LineNumberReader reader = new LineNumberReader(new FileReader("data/" + filename));
//        List<String> records = new ArrayList<>();
//        String s;
//        while ((s = reader.readLine()) != null) {
//            records.add(s);
//        }
//        System.out.println("Reading DONE.");
//        this.sortRecords(records);
    }

    /**
     * 读取数据
     *
     * @param filename
     * @return
     * @throws IOException
     */
    private int getLineNumber(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        FileInputStream fileIS = new FileInputStream(filename);
        int available = fileIS.available();

        LineNumberReader lineNumReader = new LineNumberReader(fileReader);
        lineNumReader.skip(available);
        int lineNumber = lineNumReader.getLineNumber();
        lineNumReader.close();

        return lineNumber;
    }

    /**
     * 排序处理
     */
    private void sortRecords(List<String> records) throws
            ExecutionException, InterruptedException, FileNotFoundException {
        Collections.sort(records);
        PrintWriter out = new PrintWriter(
                new FileOutputStream("data/sorted-big-file.txt", false));
        for (String r : records) {
            out.println(r);
        }
        out.flush();
        out.close();
//        Assigner assigner = new Assigner();
//        assigner.assign(4, records, scoreComparator);
    }

    private String createBigFilePath(String filename) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(dirname));
        sb.append('/');
        sb.append(String.valueOf(filename));
        return sb.toString();
    }

    private String createBigFileRecord(int idx) {
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();
        String alphabeta = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String sNumber = "0123456789";

        int nameOfLen = rd.nextInt(1) + 5;
        int i;

//        sb.append(idx);
//        sb.append('\t');
        for (i = 0; i < nameOfLen; i++) {
            int letterOfIndex = rd.nextInt(10);
            sb.append(sNumber.charAt(letterOfIndex));
//            int letterOfIndex = rd.nextInt(26);
//            sb.append(alphabeta.charAt(letterOfIndex));
        }
//        sb.append('\t');
//        sb.append(Math.round(rd.nextFloat() * 100));
        sb.append('\n');

        return sb.toString();
    }
}
