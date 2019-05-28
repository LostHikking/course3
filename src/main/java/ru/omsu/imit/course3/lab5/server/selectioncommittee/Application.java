package ru.omsu.imit.course3.lab5.server.selectioncommittee;

import java.io.Serializable;

public class Application implements Serializable {
    private static final long serialVersionUID = 784029545976931839L;
    private Integer id;
    private Integer applicantID;
    private Integer specialtyID;

    public Application(int id, int applicantID, int specialtyID) {
        this.id = id;
        this.applicantID = applicantID;
        this.specialtyID = specialtyID;
    }

    public Application(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(Integer applicantID) {
        this.applicantID = applicantID;
    }

    public Integer getSpecialtyID() {
        return specialtyID;
    }

    public void setSpecialtyID(Integer specialtyID) {
        this.specialtyID = specialtyID;
    }
}
