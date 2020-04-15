package ru.omsu.imit.course3.first.lab5.readerwriter;

public class Reader implements Runnable {

	private Book book;

	public Reader(Book book) {
		this.book = book;
	}

	@Override
	public void run() {
		for (int i = 0; i < 15; i++) {
			System.out.println("Читатель читает " + book.read());
		}
	}
}
