package ru.omsu.imit.course3.first.lab5.readerwriter;

import java.util.Random;

public class Writer implements Runnable {

	private final Book book;
	private final Random rand = new Random();

	public Writer(Book book) {
		this.book = book;
	}

	@Override
	public void run() {
		for (int i = 0; i < 15; i++) {
			int text = rand.nextInt(10);
			book.write(Integer.toString(text));
		}
	}
}