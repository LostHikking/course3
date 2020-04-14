package ru.omsu.imit.course3.first.lab4;

import java.util.function.Function;

@FunctionalInterface
public interface MyFunction<T, K> extends Function<T, K> {
	K apply(T arg);
}
