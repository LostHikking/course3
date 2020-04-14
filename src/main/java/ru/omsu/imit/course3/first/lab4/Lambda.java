package ru.omsu.imit.course3.first.lab4;

import ru.omsu.imit.course3.second.lab4.Person;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.*;
import java.util.stream.IntStream;

public class Lambda {
	public static MyFunction<String, List<String>> split = s -> Arrays.asList(s.split(" "));
	public static Function<List<?>, Integer> count = List::size;
	public static MyFunction<String, Integer> splitAndCountAndThen = s -> split.andThen(count).apply(s);
	public static MyFunction<String, Integer> splitAndCountComposed = s -> count.compose(split).apply(s);
	public static MyFunction<String, Person> create = Person::new;
	public static BinaryOperator<Integer> maxInt = Math::max;
	public static Supplier<Date> getCurrentDate = java.util.Date::new;
	public static Predicate<Integer> isEven = (i) -> i % 2 == 0;
	public static BiPredicate<Integer, Integer> areEqual = Integer::equals;

	public static IntStream transform(IntStream stream, IntUnaryOperator op) {
		return stream.map(op);
	}

	public static IntStream transformParallel(IntStream stream, IntUnaryOperator op) {
		return stream.parallel().map(op);
	}
	public static int sum(List<Integer> list) {
		return list.stream().reduce(Integer::sum).get();
	}

	public static int product(List<Integer> list) {
		return list.stream().reduce((a, u) -> a * u).get();
	}
}
