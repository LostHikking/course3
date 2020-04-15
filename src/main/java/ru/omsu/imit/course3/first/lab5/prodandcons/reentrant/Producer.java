package ru.omsu.imit.course3.first.lab5.prodandcons.reentrant;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Producer implements Runnable {
	public static final int N = 10000;
	private List<Integer> integerList;
	private ReentrantLock lock;

	public Producer(List<Integer> integerList, ReentrantLock lock) {
		this.integerList = integerList;
		this.lock = lock;
	}

	@Override
	public void run() {
		lock.lock();
		try {
			Random random = new Random();
			for (int i = 0; i < N; i++) {
				integerList.add(random.nextInt());
				System.out.println("Produce " + i);
			}
		} finally {
			lock.unlock();
		}
	}
}
