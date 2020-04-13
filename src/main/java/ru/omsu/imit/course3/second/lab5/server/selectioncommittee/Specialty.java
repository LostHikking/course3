package ru.omsu.imit.course3.second.lab5.server.selectioncommittee;

import java.io.Serializable;

public class Specialty implements Serializable {
    private static final long serialVersionUID = 3960961109155580225L;
    private int id;
    private int code;
    private int name;
    private int firstDisciplineID;
    private int secondDisciplineID;
    private int thirdDisciplineID;

    public Specialty(int id, int code, int name, int firstDisciplineID, int secondDisciplineID, int thirdDisciplineID) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.firstDisciplineID = firstDisciplineID;
        this.secondDisciplineID = secondDisciplineID;
        this.thirdDisciplineID = thirdDisciplineID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getFirstDisciplineID() {
        return firstDisciplineID;
    }

    public void setFirstDisciplineID(int firstDisciplineID) {
        this.firstDisciplineID = firstDisciplineID;
    }

    public int getSecondDisciplineID() {
        return secondDisciplineID;
    }

    public void setSecondDisciplineID(int secondDisciplineID) {
        this.secondDisciplineID = secondDisciplineID;
    }

    public int getThirdDisciplineID() {
        return thirdDisciplineID;
    }

    public void setThirdDisciplineID(int thirdDisciplineID) {
        this.thirdDisciplineID = thirdDisciplineID;
    }
}
