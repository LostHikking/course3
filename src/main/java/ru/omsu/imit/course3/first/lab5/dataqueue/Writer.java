package ru.omsu.imit.course3.first.lab5.dataqueue;

import java.util.Arrays;
import java.util.Random;

public class Writer implements Runnable {
	private DataQueue queue;
	private Random random;

	public Writer(DataQueue queue) {
		this.queue = queue;
		random = new Random();
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			int size = random.nextInt(3) + 1;
			int[] data = new int[size];
			for (int j = 0; j < data.length; j++) {
				data[j] = random.nextInt(10);
			}
			System.out.println(Thread.currentThread().getName() + " wrote: " + Arrays.toString(data));
			queue.put(new Data(data));
		}
	}
}
