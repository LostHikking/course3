package ru.omsu.imit.course3;

import com.google.gson.Gson;

public class Client {
    public static void main(String[] args) {

    Person person = new Person("Vasya", "Pupkin", 15);
    Gson gson = new Gson();
    String gsonText = gson.toJson(person);
    System.out.println(gsonText);
    }
}
