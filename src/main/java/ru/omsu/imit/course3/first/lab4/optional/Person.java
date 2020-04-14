package ru.omsu.imit.course3.first.lab4.optional;

import java.util.Optional;

public class Person {
	private Optional<Person> mother;
	private Optional<Person> father;

	public Person(Person mother, Person father) {
		this.mother = Optional.ofNullable(mother);
		this.father = Optional.ofNullable(father);
	}

	public Optional<Person> getMother() {
		return mother;
	}

	public Optional<Person> getFather() {
		return father;
	}

	public Optional<Person> getMothersMotherFather() {
		Optional<Person> res;
		res = mother.flatMap(c -> c.getMother().flatMap(m -> m.getMother().flatMap(Person::getFather)));
		return res;
	}
}
