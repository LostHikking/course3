package ru.omsu.imit.course3;

import com.google.gson.Gson;
import org.junit.Test;


public class ClientTest {

    @Test
    public void standartTest() {
        Gson gson = new Gson();
        Server server = new Server();
        Person person1 = new Person("T", "Kek", 27);
        String request = gson.toJson(person1);
        String resultJson = server.get(gson.toJson(person1));
        Person personResult = gson.fromJson(resultJson, Person.class);
        System.out.println(personResult);
        server.add(request);
        resultJson = server.get(request);
        personResult = gson.fromJson(resultJson, Person.class);
        System.out.println(personResult);
    }

    @Test
    public void testWithNull() {
        Gson gson = new Gson();
        Server server = new Server();
        Person person1 = new Person("T", "Kek", null);
        String request = gson.toJson(person1);
        String resultJson = server.get(gson.toJson(person1));
        Person personResult = gson.fromJson(resultJson, Person.class);
        System.out.println(personResult);
        server.add(request);
        resultJson = server.get(request);
        personResult = gson.fromJson(resultJson, Person.class);
        System.out.println(personResult);
    }
}