package com.mycompany.app;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

final public class Assignee implements Runnable {
    private String name;
    private List<Record> jobs;
    private Comparator<Record> comparator;

    public Assignee (int index, List jobs, Comparator<Record> comparator) {
        this.name = String.format("Assignee ID: %s", index);
        this.jobs = jobs;
        this.comparator = comparator;
    }

    @Override
    public void run() {
        Collections.sort(jobs, comparator);
        System.out.println(String.format("[Assignee] %s. Now, the sorting is finished.", name));
    }
}
