package ru.omsu.imit.course3;

import com.google.gson.Gson;



public class Client {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Server server = new Server();
        Person person1 = new Person("u", "Kek", 27);
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
