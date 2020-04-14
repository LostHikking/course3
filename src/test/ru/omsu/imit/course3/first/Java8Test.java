package ru.omsu.imit.course3.first;

import org.junit.Test;
import ru.omsu.imit.course3.first.lab4.Lambda;
import ru.omsu.imit.course3.second.lab4.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Java8Test {
	@Test
	public void splitFunctionTest() {
		String string = "LOL KEK CHEBUREK";
		List<String> result = Lambda.split.apply(string);
		System.out.println(result);
		System.out.println(Lambda.count.apply(result));
	}

	@Test
	public void splitAndCoountFunctionTest() {
		String string = "LOL KEK CHEBUREK";
		System.out.println(Lambda.splitAndCountAndThen.apply(string));
		System.out.println(Lambda.splitAndCountComposed.apply(string));
	}

	@Test
	public void createPersonByName() {
		Person person = Lambda.create.apply("Name");
		System.out.println(person);
	}

	@Test
	public void maxTest() {
		System.out.println(Lambda.maxInt.apply(11, 10));
	}

	@Test
	public void dateTest() {
		System.out.println(Lambda.getCurrentDate.get());
	}

	@Test
	public void transformTest() {
		List<ru.omsu.imit.course3.first.lab4.age.Person> list = new ArrayList<>();
		list.add(new ru.omsu.imit.course3.first.lab4.age.Person("lol", 15));
		list.add(new ru.omsu.imit.course3.first.lab4.age.Person("wow", 35));
		list.add(new ru.omsu.imit.course3.first.lab4.age.Person("wow", 30));
		list.add(new ru.omsu.imit.course3.first.lab4.age.Person("wow", 28));
		Supplier<IntStream> newStream = () -> list.stream().mapToInt(ru.omsu.imit.course3.first.lab4.age.Person::getAge);
		IntStream iStr = newStream.get(), copyStr = newStream.get();
		Lambda.transformParallel(iStr, p -> p).forEach(System.out::println);
		System.out.println("###########");
		Lambda.transform(copyStr, p -> p).forEach(System.out::println);
	}

	@Test
	public void uniqueAgeMore30() {
		List<ru.omsu.imit.course3.first.lab4.age.Person> list = new ArrayList<>();
		list.add(new ru.omsu.imit.course3.first.lab4.age.Person("wowas", 32));
		list.add(new ru.omsu.imit.course3.first.lab4.age.Person("lolf", 15));
		list.add(new ru.omsu.imit.course3.first.lab4.age.Person("wowa", 35));
		list.add(new ru.omsu.imit.course3.first.lab4.age.Person("wowassa", 31));
		list.add(new ru.omsu.imit.course3.first.lab4.age.Person("wowass", 35));
		list.add(new ru.omsu.imit.course3.first.lab4.age.Person("wowassadasd", 100));
		list.add(new ru.omsu.imit.course3.first.lab4.age.Person("wowasdg", 30));
		list.add(new ru.omsu.imit.course3.first.lab4.age.Person("wowas", 31));
		list.stream().filter(person -> person.getAge() > 30)
				.sorted(Comparator
						.comparing(ru.omsu.imit.course3.first.lab4.age.Person::getName))
				.map(ru.omsu.imit.course3.first.lab4.age.Person::getName)
				.forEach(System.out::println);
		System.out.println("###########");
		list.stream().filter(person -> person.getAge() > 30)
				.collect(Collectors.groupingBy(ru.omsu.imit.course3.first.lab4.age.Person::getName, Collectors.counting()))
				.keySet().forEach(System.out::println);
	}
}
