package ru.omsu.imit.course3;

import com.google.gson.Gson;
import org.junit.Test;
import ru.omsu.imit.course3.lab4.Person;
import ru.omsu.imit.course3.lab4.client.PersonClient;
import ru.omsu.imit.course3.lab4.server.Server;
import ru.omsu.imit.course3.lab4.server.rest.request.ServerRequest;

import static junit.framework.TestCase.assertEquals;


public class PersonClientTest {

    @Test
    public void testGet() {
        Server.main(null);
        ServerRequest server = new ServerRequest();
        PersonClient personClient = new PersonClient();
        Person person = new Person(null, "Pupkin", null);
        Gson gson = new Gson();
        Person response = (Person) personClient.get("http://localhost:8888/api/person?lastName=" + person.getLastName(), Person.class);
        String json = gson.toJson(person);
        assertEquals(gson.toJson(response), server.get(json));
    }

    @Test
    public void testPost() {
        Server.main(null);
        ServerRequest server = new ServerRequest();
        PersonClient personClient = new PersonClient();
        Person person = new Person("Petya", "Pupkin", 25);
        Gson gson = new Gson();
        Person responseFromFirstGet = (Person) personClient.get("http://localhost:8888/api/person?lastName=" + person.getLastName() + "&firstName=" + person.getFirstName() + "&age" + person.getAge(), Person.class);
        assertEquals(gson.toJson(new Person()), gson.toJson(responseFromFirstGet));
        Person responseFromPost = (Person) personClient.post("http://localhost:8888/api/person", person, Person.class);
        Person responseFromGet = (Person) personClient.get("http://localhost:8888/api/person?lastName=" + person.getLastName() + "&firstName=" + person.getFirstName() + "&age" + person.getAge(), Person.class);
        assertEquals(gson.toJson(responseFromGet), gson.toJson(responseFromPost));
    }

//    @Test
//    public void testErrorGet(){
//        Server server = new Server();
//        PersonClient personClient = new PersonClient();
//        server.run();
//        String answer = personClient.send("Get something");
//        assertEquals("Error", answer);
//    }
//
//    @Test
//    public void testSuccessGet(){
//        Server server = new Server();
//        PersonClient personClient = new PersonClient();
//        server.run();
//        Gson gson = new Gson();
//        Person person1 = new Person("Vasya", "Pupkin", 15);
//        String jsonPerson = gson.toJson(person1);
//        String answer = personClient.send("Get " + jsonPerson);
//        assertEquals(jsonPerson, answer);
//    }
//
//    @Test
//    public void testSuccessAdd() {
//        Server server = new Server();
//        PersonClient personClient = new PersonClient();
//        server.run();
//        Gson gson = new Gson();
//        Person person1 = new Person("Bib", "Bob", 15);
//        String jsonPerson = gson.toJson(person1);
//        String answer = personClient.send("Add " + jsonPerson);
//        assertEquals("Added", answer);
//        answer = personClient.send("Get "+ jsonPerson);
//        assertEquals(jsonPerson, answer);
//    }

//    @Test
//    public void testNothingFind() {
//        Gson gson = new Gson();
//        Server server = new Server();
//        Person person1 = new Person("T", "Kek", null);
//        String request = gson.toJson(person1);
//        String resultJson = server.get(request);
//        Person personResult = gson.fromJson(resultJson, Person.class);
//        System.out.println(personResult);
//        String expected = null;
//        assertEquals(resultJson,expected);
//    }
//
//    @Test
//    public void testFinded() {
//        Gson gson = new Gson();
//        Server server = new Server();
//        Person person1 = new Person("Lol", "Kek", null);
//        String request = gson.toJson(person1);
//        String resultJson = server.get(gson.toJson(person1));
//        assertEquals(resultJson, request);
//    }
//
//
//    @Test
//    public void addingWithNull() {
//        Gson gson = new Gson();
//        Server server = new Server();
//        Person person1 = new Person("Kek", "Lol", null);
//        String request = gson.toJson(person1);
//        server.add(request);
//        String resultJson = server.get(gson.toJson(person1));
//        assertEquals(resultJson, null);
//    }
//
//    @Test
//    public void addingAndGetting(){
//        Gson gson = new Gson();
//        Server server = new Server();
//        Person person1 = new Person("1","2",3);
//        Person person2 = new Person("6","7",8);
//        Person person3 = new Person("t","p",25);
//        Person person4 = new Person("ol","thi",3);
//        Person person5 = new Person("por","gh",3);
//        String request1 = gson.toJson(person1);
//        String request2 = gson.toJson(person2);
//        String request3 = gson.toJson(person3);
//        String request4 = gson.toJson(person4);
//        String request5 = gson.toJson(person5);
//        server.add(request1);
//        server.add(request2);
//        server.add(request3);
//        server.add(request4);
//        server.add(request5);
//        String result1Json = server.get(request1);
//        String result2Json = server.get(request2);
//        String result3Json = server.get(request3);
//        String result4Json = server.get(request4);
//        String result5Json = server.get(request5);
//        server.printAll();
//    }
//
//    @Test
//    public void noJson() {
//        Gson gson = new Gson();
//        Server server = new Server();
//        server.add("tr");
//        String resultJson = server.get("pr");
//        server.printAll();
//    }
}
