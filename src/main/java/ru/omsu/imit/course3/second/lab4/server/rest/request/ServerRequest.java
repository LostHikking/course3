package ru.omsu.imit.course3.second.lab4.server.rest.request;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import ru.omsu.imit.course3.second.lab3.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServerRequest {
    private List<Person> persons = Collections.synchronizedList(new ArrayList<>());
    private String text;

    public ServerRequest() {
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

    public ServerRequest(String text) {
        super();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean add(String request) {
        Gson gson = new Gson();
        try {
            Person person = gson.fromJson(request, Person.class);
        } catch (JsonSyntaxException e) {
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
            String result;
            Person resultPerson;
            for (Person temp : this.persons) {
                if ((person.getFirstName() == null) || (person.getFirstName().equals(temp.getFirstName()))) {
                    if ((person.getLastName() == null) || (person.getLastName().equals(temp.getLastName())) || (person.getLastName().isEmpty())) {
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
            return gson.toJson(new Person());
        } catch (JsonSyntaxException e) {
            return gson.toJson(new Person());
        }
    }

    public void printAll() {
        System.out.println(this.persons);
    }
}
