package ru.omsu.imit.course3.test;

import com.google.gson.Gson;

import java.net.*;
import java.io.*;

public class Test {
    public static void main(String[] args) {
        Home home = new Home();
        Gson gson = new Gson();
        home.setHumidity(32);
        home.setMotion(true);
        home.setRange(33);
        String request = gson.toJson(home);
        System.out.println(request);
    }
}
