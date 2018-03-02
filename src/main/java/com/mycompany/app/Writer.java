package com.mycompany.app;

import java.util.List;

public class Writer {
    private String filename;
    private int currentChunk;

    public Writer(String filename) {
        this.filename = filename;
    }

    public void write(List<Reader> records) {

    }
}
