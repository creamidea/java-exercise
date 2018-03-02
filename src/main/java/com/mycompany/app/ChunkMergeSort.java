package com.mycompany.app;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChunkMergeSort {
    private String filename;
    private String output = "data/sorted-big-file.txt";
    private int chunkLineNumber = 3000000;
    private Comparator<String> comparator;

    public ChunkMergeSort(String filename, Comparator<String> comparator) {
        this.filename = filename;
        this.comparator = comparator;
    }

    public LineNumberReader createReader(String filename) throws FileNotFoundException {
        String filepath = String.format("data/%s", filename);
        FileReader fileReader = new FileReader(filepath);
        return new LineNumberReader(fileReader);
    }

    private String createChunkName(int page) {
        return String.format("chunk.%d.sorted", page);
    }

    /**
     * 切分文件成 chunk
     */
    private List<String> split(int chunkLineNumber) throws IOException {
        LineNumberReader lnr = createReader(filename);
        ExecutorService executor = Executors.newFixedThreadPool(5);

        String currentLine;
        int lineNumber = 0; // 单次行记录器
        int page = 0; // 页码

        List<String> container = new ArrayList<>(); // 存储每行记录
        List<String> chunkFiles = new ArrayList<>(); // 存储分片文件

        while ((currentLine = lnr.readLine()) != null) {
            lineNumber++;
            container.add(currentLine);

            if (lineNumber > chunkLineNumber - 1) {
                SAWWorker worker = new SAWWorker(page, container, comparator);
                executor.submit(worker);
                chunkFiles.add(createChunkName(page));

                // 页码加 1
                page++;

                // 容器清空
                container = new ArrayList<>();

                // 计数器清零
                lineNumber = 0;
            }
        }

        if (lineNumber > 0) {
            // 加上最后的文件分片
            SAWWorker worker = new SAWWorker(page, container, comparator);
            executor.submit(worker);
            chunkFiles.add(createChunkName(page));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        lnr.close();
        return chunkFiles;
    }

    /**
     * 选择合适的项
     *
     * @param tracks
     * @return
     */
    private Map.Entry<String, String> selected(Map<String, String> tracks) {
        Map.Entry<String, String> tempTrack = null;
        for (Map.Entry<String, String> track : tracks.entrySet()) {
            if (tempTrack == null) tempTrack = track;
            String a = track.getValue();
            String b = tempTrack.getValue();
            if (comparator.compare(a, b) < 0) {
                tempTrack = track;
            }
        }
        return tempTrack;
    }

    /**
     * 归并文件
     */
    private void merge(List<String> chunkFiles) throws IOException {
        // 写入句柄
        PrintWriter pw = new PrintWriter(
                new FileOutputStream(output, false));

        // 创建 readers
        Map<String, LineNumberReader> readers = new HashMap<>();
        for (String filename : chunkFiles) {
            readers.put(filename, createReader(filename));
        }

        // 多路数据记录
        Map<String, String> tracks = new HashMap<>();

        // 第一回合，每个读取一个
        for (Map.Entry<String, LineNumberReader> reader : readers.entrySet()) {
            tracks.put(reader.getKey(), reader.getValue().readLine());
        }

        while (true) {
            if (tracks.size() == 1) {
                for (Map.Entry<String, String> track : tracks.entrySet()) {
                    String filename = track.getKey();
                    String value = track.getValue();
                    pw.println(value);

                    String s;
                    LineNumberReader r = readers.get(filename);
                    while ((s = r.readLine()) != null) {
                        pw.println(s);
                    }
                }
                break;
            }

            // 选出 track
//            Map.Entry<String, String> tempTrack = Collections.min(tracks.entrySet(),
//                    Comparator.comparing(Map.Entry::getValue));
            Map.Entry<String, String> tempTrack = selected(tracks);

            // 写入一行内容
            String filename = tempTrack.getKey();
            String record = tempTrack.getValue();
            pw.write(record);
            pw.println();

            String nextRecord = readers.get(filename).readLine();
            if (nextRecord != null) {
                tracks.put(filename, nextRecord);
            } else {
                readers.get(filename).close();
                tracks.remove(filename);
                readers.remove(filename);
            }
        }

        pw.close();
    }

    public void sort() throws IOException {
        List<String> data = split(chunkLineNumber);
//        List<String> data = new ArrayList<>();
//        int[] pages = {0, 1};
//        for (int page : pages) {
//            data.add(String.format("chunk.%d.sorted", page));
//        }
        merge(data);
    }
}
