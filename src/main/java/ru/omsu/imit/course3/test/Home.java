package ru.omsu.imit.course3.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Home implements Serializable {
    private static final long serialVersionUID = 1237780699757451184L;
    private List<User> list = new ArrayList<>();
    private int temperature;
    private int range;
    private int humidity;
    private boolean motion;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public boolean isMotion() {
        return motion;
    }

    public void setMotion(boolean motion) {
        this.motion = motion;
    }

    public Home() {
        User user = new User("user",true, "123456");
        this.list.add(user);
        User user2 = new User("user",true, "123456");
        this.list.add(user2);

    }
}
