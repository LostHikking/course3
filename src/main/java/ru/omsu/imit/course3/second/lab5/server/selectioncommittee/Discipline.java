package ru.omsu.imit.course3.second.lab5.server.selectioncommittee;

import java.io.Serializable;

public class Discipline implements Serializable {
    private static final long serialVersionUID = 2635319101774179395L;
    private int id;
    private String name;

    public Discipline(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Discipline() {
    }

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
}
