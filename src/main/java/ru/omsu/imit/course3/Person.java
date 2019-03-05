package ru.omsu.imit.course3;

import java.io.Serializable;


public class Person implements Serializable {
    private static final long serialVersionUID = 2350340094990612631L;
    @Override
    public String toString() {
        return "Person [firstname=" + firstName + ", lastname=" + lastName + ", age=" + age + "]";
    }
    private String firstName;
    private String lastName;
    private Integer age;

    public Person(String firstName, String lastName, Integer age) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
