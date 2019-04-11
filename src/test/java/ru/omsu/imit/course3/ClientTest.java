package ru.omsu.imit.course3;

import com.google.gson.Gson;
import org.junit.*;

import static junit.framework.TestCase.assertEquals;


public class ClientTest {

    @Test
    public void testNothingFind() {
        Gson gson = new Gson();
        Server server = new Server();
        Person person1 = new Person("T", "Kek", null);
        String request = gson.toJson(person1);
        String resultJson = server.get(request);
        Person personResult = gson.fromJson(resultJson, Person.class);
        System.out.println(personResult);
        String expected = null;
        assertEquals(resultJson,expected);
    }

    @Test
    public void testFinded() {
        Gson gson = new Gson();
        Server server = new Server();
        Person person1 = new Person("Lol", "Kek", null);
        String request = gson.toJson(person1);
        String resultJson = server.get(gson.toJson(person1));
        assertEquals(resultJson, request);
    }


    @Test
    public void addingWithNull() {
        Gson gson = new Gson();
        Server server = new Server();
        Person person1 = new Person("Kek", "Lol", null);
        String request = gson.toJson(person1);
        server.add(request);
        String resultJson = server.get(gson.toJson(person1));
        assertEquals(resultJson, null);
    }

    @Test
    public void addingAndGetting(){
        Gson gson = new Gson();
        Server server = new Server();
        Person person1 = new Person("1","2",3);
        Person person2 = new Person("6","7",8);
        Person person3 = new Person("t","p",25);
        Person person4 = new Person("ol","thi",3);
        Person person5 = new Person("por","gh",3);
        String request1 = gson.toJson(person1);
        String request2 = gson.toJson(person2);
        String request3 = gson.toJson(person3);
        String request4 = gson.toJson(person4);
        String request5 = gson.toJson(person5);
        server.add(request1);
        server.add(request2);
        server.add(request3);
        server.add(request4);
        server.add(request5);
        String result1Json = server.get(request1);
        String result2Json = server.get(request2);
        String result3Json = server.get(request3);
        String result4Json = server.get(request4);
        String result5Json = server.get(request5);
        server.printAll();
    }

    @Test
    public void noJson() {
        Gson gson = new Gson();
        Server server = new Server();
        server.add("tr");
        String resultJson = server.get("pr");
        server.printAll();
    }
}
