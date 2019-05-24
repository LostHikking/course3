package ru.omsu.imit.course3.lab5.server.selectioncommittee;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Applicant implements Serializable {
    private static final long serialVersionUID = -3668814543555230264L;
    private int id;
    private String firstName;
    private String lastName;
    private Date birhdate;
    private List<Application> applications = Collections.synchronizedList(new ArrayList<>());
    private List<Score> score = Collections.synchronizedList(new ArrayList<>());

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirhdate() {
        return birhdate;
    }

    public void setBirhdate(Date birhdate) {
        this.birhdate = birhdate;
    }

    public void setApplications(List<Application> collect) {
        this.applications = collect;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setScore(List<Score> collect) {
        this.score = collect;
    }

    public List<Score> getScore() {
        return score;
    }
}
