package ru.omsu.imit.course3.first.lab4;

public class Person {
	private Person mother;
	private Person father;

	public Person getMother() {
		return mother;
	}

	public Person getFather() {
		return father;
	}

	public Person(Person mother, Person father) {
		this.mother = mother;
		this.father = father;
	}

	public Person getMothersMotherFather() {
		Person res = getMother();
		if (res == null) return null;
		res = res.getMother();
		if (res == null) return null;
		return res.getFather();
	}
}
