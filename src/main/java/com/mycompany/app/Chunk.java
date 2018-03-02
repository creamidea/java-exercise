package com.mycompany.app;

import java.io.IOException;
import java.io.LineNumberReader;

public class Chunk {
    private int offset; // 记录分片偏移位置
    private int capacity; // unit: B
    private int lines; // 记录分片行数 -2147483648 to 2147483647

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public int getOffset() {
        return offset;
    }

    public int getLines() {
        return lines;
    }

    public int getCapacity() {
        return capacity;
    }

    public String readLine (LineNumberReader reader) throws IOException {
        int lineNumber = reader.getLineNumber();
        if (lineNumber >= getLines()) {
            return null;
        }
        return reader.readLine();
    }

    public static LineNumberReader createChunkReader(LineNumberReader reader, Chunk chunk) throws IOException {
        int off = chunk.getOffset();
        if (off > 0) {
            reader.setLineNumber(0);
            reader.skip(off);
        }
        return reader;
    }
}
