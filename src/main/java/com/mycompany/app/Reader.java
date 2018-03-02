package com.mycompany.app;

// 提供一种按行读取的功能

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private String filename;
    private LineNumberReader lnr;

    public Reader(String filename) throws FileNotFoundException {
        String filepath = String.format("data/%s", filename);
        FileReader fileReader = new FileReader(filepath);

        this.lnr = new LineNumberReader(fileReader);
        this.filename = filename;
    }

    public List<String> readNext(int chunkSize) throws IOException {
        int count = 0;
        List<String> c = new ArrayList<>();
        while (count < chunkSize) {
            String line = lnr.readLine();
            if (line == null) break;
            c.add(line);
            count++;
        }
        return c;
    }

//    public List<String> read(String filename) throws IOException {
//        BufferedReader br = new BufferedReader(fileReader);
//        int initLineNumber = 20000;
//        List<String> container = new ArrayList<>(initLineNumber);
//
////        FileInputStream fileIS = new FileInputStream(filepath);
////        int available = fileIS.available();
////        char[] cbuf = new char[available];
////        br.read(cbuf);
////
////        StringBuilder sb = new StringBuilder();
////        for (char c : cbuf) {
////            if (c == '\n') {
////                Record r = new Record();
////                String[] _r = sb.toString().split("\t");
////
////                if (_r.length != 3) continue;
////
////                r.setId(Integer.parseInt(_r[0]));
////                r.setName(_r[1]);
////                r.setScore(Integer.parseInt(_r[2]));
////                container.add(r);
////                continue;
////            }
////
////            sb.append(c);
////        }
//
//        String sCurrentLine;
//        while ((sCurrentLine = br.readLine()) != null) {
////            Record r = new Record();
////            String[] _r = sCurrentLine.split("\t");
////
////            if (_r.length != 3) continue;
////
////            r.setId(Integer.parseInt(_r[0]));
////            r.setName(_r[1]);
////            r.setScore(Integer.parseInt(_r[2]));
////            container.add(r);
//            container.add(sCurrentLine);
//        }
//
//        br.close();
//        return container;
//    }
}
