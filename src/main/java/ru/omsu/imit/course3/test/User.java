package ru.omsu.imit.course3.test;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 2643418936820495109L;
    private String userName;
    private boolean status;
    private String card;

    @Override
    public String toString() {
        return "User [userName=" + userName + ", status=" + status + ", card=" + card + "]";
    }

    public User(String userName, boolean status, String card) {
        super();
        this.userName = userName;
        this.status = status;
        this.card = card;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

}
