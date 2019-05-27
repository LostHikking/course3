package ru.omsu.imit.course3.lab5.server.selectioncommittee;

import java.io.Serializable;

public class Application implements Serializable {
    private static final long serialVersionUID = 784029545976931839L;
    private int id;
    private int applicantID;
    private int specialtyID;

    public Application(int id, int applicantID, int specialtyID) {
        this.id = id;
        this.applicantID = applicantID;
        this.specialtyID = specialtyID;
    }

    public Application(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(int applicantID) {
        this.applicantID = applicantID;
    }

    public int getSpecialtyID() {
        return specialtyID;
    }

    public void setSpecialtyID(int specialtyID) {
        this.specialtyID = specialtyID;
    }
}
