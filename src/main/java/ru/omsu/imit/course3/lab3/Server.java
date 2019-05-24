package ru.omsu.imit.course3.lab3;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private List<Person> persons = Collections.synchronizedList(new ArrayList<>());
    public void run() {
        new Thread(()->{
            final int port = 6666;
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                int id = 0;
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    ClientServiceThread clientThread = new ClientServiceThread(clientSocket, id++, this);
                    clientThread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    public Server(){
        Person person1 = new Person("Vasya", "Pupkin", 15);
        Person person2 = new Person("Ivan", "Ivanov", 23);
        Person person3 = new Person("Sidor", "Sidorov", 67);
        Person person4 = new Person("Kirill", "Petrov", 19);
        Person person5 = new Person("Semyon", "Ivanov", 55);
        Person person7 = new Person("Lol", "Kek", 27);
        this.persons.add(person1);
        this.persons.add(person2);
        this.persons.add(person3);
        this.persons.add(person4);
        this.persons.add(person5);
        this.persons.add(person7);
    }

    public boolean add(String request){
        Gson gson = new Gson();
        try {
            Person person = gson.fromJson(request, Person.class);
        }
        catch (JsonSyntaxException e){
            return false;
        }
        Person person = gson.fromJson(request, Person.class);
        if (person.getFirstName() == null)
            return false;
        if (person.getLastName() == null)
            return false;
        if (person.getAge() == null)
            return false;
        this.persons.add(person);
        return true;
    }

    public String get(String request) {
        Gson gson = new Gson();
        try {
            Person person = gson.fromJson(request, Person.class);
        } catch (JsonSyntaxException e) {
            return "Error";
        }
        Person person = gson.fromJson(request, Person.class);
        String result;
        Person resultPerson;
        for (Person temp : this.persons) {
            if (person.getFirstName().equals(temp.getFirstName())) {
                if (person.getLastName().equals(temp.getLastName())) {
                    if ((person.getAge() == null) || (temp.getAge()) == null) {
                        resultPerson = temp;
                        result = gson.toJson(resultPerson);
                        return result;
                    }
                    if (person.getAge().equals(temp.getAge())) {
                        resultPerson = temp;
                        result = gson.toJson(resultPerson);
                        return result;
                    }
                }
            }
        }
        return "Not found";
    }

    public void printAll() {
        System.out.println(this.persons);
    }
}

