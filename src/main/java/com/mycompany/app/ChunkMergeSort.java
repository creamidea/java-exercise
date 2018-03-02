package com.mycompany.app;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ChunkMergeSort {
    private String filename;

    public ChunkMergeSort(String filename) {
        this.filename = filename;
    }

    public LineNumberReader createReader(String filename) throws FileNotFoundException {
        String filepath = String.format("data/%s", filename);
        FileReader fileReader = new FileReader(filepath);
        return new LineNumberReader(fileReader);
    }

    public Chunk createChunk(int offset, int capacity, int lineNumber) throws IOException {
        Chunk chunk = new Chunk();
        chunk.setOffset(offset);
        chunk.setCapacity(capacity);
        chunk.setLines(lineNumber);

        return chunk;
    }

    /**
     * 切分文件成 chunk
     */
    private List<Chunk> split(int chunkLineNumber) throws IOException {
        LineNumberReader lnr = createReader(filename);

        String currentLine;
        int lineNumber = 0; // 单次行记录器
        int offset = 0; // 记录总偏移字节量
        int preOffset = 0; // 记录前一个偏移量
        List<Chunk> chunks = new ArrayList<>();
        List<String> container = new ArrayList<>();
        List<String> chunkFiles = new ArrayList<>();
        File f = new File("data/sorted-big-file-2.txt");
        if (f.exists()) Files.delete(Paths.get("data/sorted-big-file-2.txt"));
        PrintWriter out = new PrintWriter(
                new FileOutputStream("data/sorted-big-file-2.txt", true));
        int page = 0; // 页码

        while ((currentLine = lnr.readLine()) != null) {
            lineNumber++;
            offset += (currentLine.length() + 1); // 这里的 1 是换行符，暂不处理 \r\n
            container.add(currentLine);

            if (lineNumber >= chunkLineNumber) {
                Chunk chunk = createChunk(preOffset, offset - preOffset, lineNumber);
                chunks.add(chunk);

                // 排序写入
                Collections.sort(container, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        Record r1 = new Record(o1);
                        Record r2 = new Record(o2);
                        return r1.compareTo(r2);
                    }
                });
                String chunkFileName = String.format("data/chunk.%d.sorted", page);
                PrintWriter pw = new PrintWriter(
                        new FileOutputStream(chunkFileName, false));
                String sData = String.join("\n", container);
                pw.write(sData);
                pw.close();
                page++;
                chunkFiles.add(chunkFileName);

                // 容器清空
                container = new ArrayList<>();

                // 计数器清零
                lineNumber = 0;
                preOffset = offset;
            }
        }

        if (lineNumber > 0) {
            Chunk chunk = createChunk(preOffset, offset - preOffset, lineNumber);
            chunks.add(chunk);
            // 排序写入
            Collections.sort(container, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    Record r1 = new Record(o1);
                    Record r2 = new Record(o2);
                    return r1.compareTo(r2);
                }
            });

            String sData = String.join("\n", container) + "\n";
            out.write(sData);
        }

        out.flush();
        out.close();
        lnr.close();
        return chunks;
    }

    private void mergeChunk(Chunk a, Chunk b) throws IOException {
        LineNumberReader r1 = Chunk.createChunkReader(createReader("sorted-big-file-2.txt"), a);
        LineNumberReader r2 = Chunk.createChunkReader(createReader("sorted-big-file-2.txt"), b);
        PrintWriter out = new PrintWriter(
                new FileOutputStream("data/chunk.txt", false));

        String x = a.readLine(r1);
        String y = b.readLine(r2);
//        String x;
//
//        List<String> list = new ArrayList<>();
//        while ((x = r1.readLine()) != null) {
//            list.add(x);
//        }
//        Collections.sort(list, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                Record r1 = new Record(o1);
//                Record r2 = new Record(o2);
//                return r1.compareTo(r2);
//            }
//        });
//
//        out2.write(String.join("\n", list));
//        out2.close();
        while (x != null && y != null) {
            Record record1 = new Record(x);
            Record record2 = new Record(y);

            if (record1.compareTo(record2) < 0) {
                out.println(x);
                x = a.readLine(r1);
            } else {
                out.println(y);
                y = b.readLine(r2);
            }
        }

        while (x != null) {
            out.println(x);
            x = a.readLine(r1);
        }

        while (y != null) {
            out.println(y);
            y = b.readLine(r2);
        }

        // 文件读写清理工作
        r1.close();
        r2.close();

        out.close();

//        out2.flush();
//        out2.close();
    }

    /**
     * 归并文件
     */
    private void merge(List<Chunk> chunks) throws IOException {
        int s = 2; // 记录步数
        int i = 0; // 索引记录器

        Chunk a = chunks.get(0);
        Chunk b = chunks.get(1);

        mergeChunk(a, b);
    }

    public void sort() throws IOException {
        List<Chunk> data = split(50000);
//        merge(data);
    }
}
