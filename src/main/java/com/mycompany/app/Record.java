package com.mycompany.app;

final public class Record implements Comparable<Record> {
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int id;
    private String name;
    private int score;

    public String toString() {
        return String.format("%d\t%s\t%d", this.getId(), this.getName(), this.getScore());
    }

    @Override
    public int compareTo(Record o) {
        return Integer.compare(this.getScore(), o.getScore());
    }
}
