package ru.omsu.imit.course3;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.net.*;
import java.io.*;

public class Server {
    private List<Person> list = new ArrayList<>();

    public void run() {
        new Thread(()->{
            final int port = 6666;
            System.out.println("Server started and ready to accept clients requests");
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                int id = 0;
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    ClientServiceThread clientThread = new ClientServiceThread(clientSocket, id++);
                    clientThread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    Server() {
        Person person1 = new Person("Vasya", "Pupkin", 15);
        Person person2 = new Person("Ivan", "Ivanov", 23);
        Person person3 = new Person("Sidor", "Sidorov", 67);
        Person person4 = new Person("Kirill", "Petrov", 19);
        Person person5 = new Person("Semyon", "Ivanov", 55);
        Person person7 = new Person("Lol", "Kek", 27);
        this.list.add(person1);
        this.list.add(person2);
        this.list.add(person3);
        this.list.add(person4);
        this.list.add(person5);
        this.list.add(person7);
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
        this.list.add(person);
        return true;
    }

    public String get(String request) {
        Gson gson = new Gson();
        try {
            Person person = gson.fromJson(request, Person.class);
        } catch (JsonSyntaxException e) {
            return null;
        }
        Person person = gson.fromJson(request, Person.class);
        String result = null;
        Person resultPerson;
        for (Person temp : this.list) {
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
        return result;
    }

    public void printAll() {
        System.out.println(this.list);
    }
}

