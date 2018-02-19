package com.mycompany.app;

public class Record implements Comparable<Record> {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScroce() {
        return scroce;
    }

    public void setScroce(int scroce) {
        this.scroce = scroce;
    }

    private int id;
    private String name;
    private int scroce;

    public String toString() {
        return String.format("%d\t%s\t%d", this.getId(), this.getName(), this.getScroce());
    }

    @Override
    public int compareTo(Record o) {
        if (o == null) {
            return 1;
        }
        return this.getScroce() - o.getScroce();
    }
}
