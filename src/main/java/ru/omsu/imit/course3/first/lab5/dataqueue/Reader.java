package ru.omsu.imit.course3.first.lab5.dataqueue;

import java.util.Arrays;

public class Reader implements Runnable {
	private DataQueue queue;
	private long time;

	public Reader(DataQueue queue) {
		this.queue = queue;
		time = System.currentTimeMillis();
	}

	@Override
	public void run() {
		do {
			if (!queue.isEmpty()) {
				try {
					Data data = queue.get();
					System.out.println(Thread.currentThread().getName() + " read: " + Arrays.toString(data.get()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				time = System.currentTimeMillis();
			}
		} while (System.currentTimeMillis() - time <= 1000);
	}
}
