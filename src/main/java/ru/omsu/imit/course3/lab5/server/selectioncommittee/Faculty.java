package ru.omsu.imit.course3.lab5.server.selectioncommittee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Faculty implements Serializable {
    private static final long serialVersionUID = 4686792674864666207L;
    private int id;
    private String name;
    private List<Specialty> specialtyList = Collections.synchronizedList(new ArrayList<>());

    public Faculty(int id, String name) {
        this.id = id;
        this.name = name;
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
