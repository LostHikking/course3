package ru.omsu.imit.course3.first.lab5.readerwriter;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Book {
	private String str;
		private final ReadWriteLock lock;

	public Book() {
		lock = new ReentrantReadWriteLock();
	}

	public void write(String str) {
		System.out.println(Thread.currentThread().getName() + " хочет писать");
		lock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " пишет " + str);
			this.str = str;
			Thread.sleep(new Random().nextInt(500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName() + " закончил писать");
			lock.writeLock().unlock();
		}
	}

	public void read()  {
		System.out.println(Thread.currentThread().getName() + " хочет читать");
		lock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " читает " + str);
			Thread.sleep(new Random().nextInt(500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName() + " закончил читать");
			lock.readLock().unlock();
		}
	}
}
