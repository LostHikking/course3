package ru.omsu.imit.course3;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class Client {
    public static void main(String[] args) {
        final List<Person> list = new ArrayList<>();
        Gson gson = new Gson();
        Server server = new Server();
        Person person1 = new Person("T", "Kek", 27);
        String request = gson.toJson(person1);
        String resultJson = server.get(request);
        Person personResult = gson.fromJson(resultJson, Person.class);
        System.out.println(personResult);
        server.add(request);
        resultJson = server.get(request);
        personResult = gson.fromJson(resultJson, Person.class);
        System.out.println(personResult);
        server.printAll();
    }

}
