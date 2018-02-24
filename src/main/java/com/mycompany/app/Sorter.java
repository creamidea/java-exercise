package com.mycompany.app;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

final public class Sorter implements Runnable {
    private String name;
    private List<Record> jobs;
    private Comparator<Record> comparator;

    public Sorter(int index, List jobs, Comparator<Record> comparator) {
        this.name = String.format("Sorter ID: %s", index);
        this.jobs = jobs;
        this.comparator = comparator;
    }

    @Override
    public void run() {
        Collections.sort(jobs, comparator);
        System.out.println(String.format("[Sorter] %s. Now, the sorting is finished.", name));
    }
}
