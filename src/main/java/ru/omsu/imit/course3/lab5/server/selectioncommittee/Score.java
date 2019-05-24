package ru.omsu.imit.course3.lab5.server.selectioncommittee;

import java.io.Serializable;

public class Score implements Serializable {
    private static final long serialVersionUID = -1442007729196668854L;
    private int disciplineID;
    private int score;

    public int getDisciplineID() {
        return disciplineID;
    }

    public void setDisciplineID(int disciplineID) {
        this.disciplineID = disciplineID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
