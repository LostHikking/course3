package ru.omsu.imit.course3.first.lab5.readerwriter;

import java.util.Random;

public class Writer implements Runnable {

	private Book book;
	private Random rand = new Random();

	public Writer(Book book) {
		this.book = book;
	}

	@Override
	public void run() {
		for (int i = 0; i < 15; i++) {
			int text = rand.nextInt(10);
			System.out.println("Писатель пишет " + text);
			book.write(Integer.toString(text));
		}
	}
}