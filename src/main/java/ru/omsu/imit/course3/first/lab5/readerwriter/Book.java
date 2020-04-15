package ru.omsu.imit.course3.first.lab5.readerwriter;

import java.util.concurrent.Semaphore;

public class Book {
	private String str;
	private Semaphore writeSem;
	private Semaphore readSem;

	public Book() {
		writeSem = new Semaphore(1);
		readSem = new Semaphore(0);
	}

	public void write(String str) {
		try {
			writeSem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.str = str;
		readSem.release();
	}

	public String read() {
		try {
			readSem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		writeSem.release();
		return str;
	}
}
