package ru.omsu.imit.course3.first.lab5.prodandcons.reentrant;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer implements Runnable {
	private List<Integer> integerList;
	private ReentrantLock lock;

	public Consumer(List<Integer> integerList, ReentrantLock lock) {
		this.integerList = integerList;
		this.lock = lock;
	}

	@Override
	public void run() {
		lock.lock();
		try {
			Random random = new Random();
			for (int i = 0; i < Producer.N; i++) {
				if (integerList.size() > 0) {
					int j = random.nextInt(integerList.size());
					integerList.remove(j);
					System.out.println("Consume " + j);
				}
			}
		} finally {
			lock.unlock();
		}
	}
}
