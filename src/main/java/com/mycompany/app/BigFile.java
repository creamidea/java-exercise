package com.mycompany.app;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class BigFile {
    private static String dirname = "data";
    private static Comparator<Record> nameComparator = Comparator.comparing(Record::getName);
    private static Comparator<Record> scoreComparator = Comparator.comparing(Record::getScore);

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
            String content = this.createBigFileContent(i);
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
    public void sort(String filename) throws IOException, ExecutionException, InterruptedException {
        this.read(filename);
        this.read(filename);
//        List<Record> records = this.read(filename);
        System.out.println("Reading DONE.");
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

    private List<String> read(String filename) throws IOException {
        String filepath = String.format("data/%s", filename);
        FileReader fileReader = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fileReader);
        int initLineNumber = 20000;
        List<String> container = new ArrayList<>(initLineNumber);

//        FileInputStream fileIS = new FileInputStream(filepath);
//        int available = fileIS.available();
//        char[] cbuf = new char[available];
//        br.read(cbuf);
//
//        StringBuilder sb = new StringBuilder();
//        for (char c : cbuf) {
//            if (c == '\n') {
//                Record r = new Record();
//                String[] _r = sb.toString().split("\t");
//
//                if (_r.length != 3) continue;
//
//                r.setId(Integer.parseInt(_r[0]));
//                r.setName(_r[1]);
//                r.setScore(Integer.parseInt(_r[2]));
//                container.add(r);
//                continue;
//            }
//
//            sb.append(c);
//        }

        String sCurrentLine;
        while ((sCurrentLine = br.readLine()) != null) {
//            Record r = new Record();
//            String[] _r = sCurrentLine.split("\t");
//
//            if (_r.length != 3) continue;
//
//            r.setId(Integer.parseInt(_r[0]));
//            r.setName(_r[1]);
//            r.setScore(Integer.parseInt(_r[2]));
//            container.add(r);
            container.add(sCurrentLine);
        }

        br.close();
        return container;
    }

    /**
     * 排序处理
     */
    private void sortRecords(List<Record> records) throws
            ExecutionException, InterruptedException, FileNotFoundException {
//        Collections.sort(records, scoreComparator);
//        PrintWriter out = new PrintWriter(
//                new FileOutputStream("data/sorted-big-file.txt", false));
//        for (Record r : records) {
//            out.println(r);
//        }
//        out.flush();
//        out.close();
        Assigner assigner = new Assigner();
        assigner.assign(4, records, scoreComparator);
    }

    private String createBigFilePath(String filename) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(dirname));
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
