package com.mycompany.app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

final public class SAWWorker implements Runnable {
    private String name;
    private int page;
    private List<String> data;
    private Comparator<String> comparator;

    public SAWWorker(int page, List data, Comparator<String> comparator) {
        this.page = page;
        this.name = String.format("ID: %s", page);
        this.data = data;
        this.comparator = comparator;
    }

    private String writeChunk(String sData, int page) throws FileNotFoundException {
        String chunkFileName = String.format("data/chunk.%d.sorted", page);
        PrintWriter pw = new PrintWriter(
                new FileOutputStream(chunkFileName, false));
        pw.write(sData);
        pw.close();
        return chunkFileName;
    }

    @Override
    public void run() {
//        Arrays.sort(data.toArray());
        Collections.sort(data, comparator);
        String sData = String.join("\n", data);
        try {
            writeChunk(sData, page);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("[Sorter] %s. Now, the sorting is finished.", name));
    }
}


