package ru.omsu.imit.course3.first.lab5.prodandcons.syncmethods;

import java.util.List;
import java.util.Random;

public class ProducerConsumer implements Runnable {
	private List<Integer> list;
	private boolean produce;
	private static final int N = 10000;

	public ProducerConsumer(List<Integer> list, boolean produce) {
		this.list = list;
		this.produce = produce;
	}

	@Override
	public synchronized void run() {
		if (produce) {
			put();
			produce = false;
		} else {
			get();
		}
	}

	private synchronized void put() {
		Random rand = new Random();
		for (int i = 0; i < N; i++) {
			list.add(rand.nextInt());
			System.out.println("Produce " + i);
		}
		System.out.println("Producer stop");
	}

	private synchronized void get() {
		Random rand = new Random();
		for (int i = 0; i < list.size(); i++) {
			int j = rand.nextInt(list.size());
			list.remove(j);
			System.out.println("Consume " + j);
		}
		System.out.println("Consumer stop");
	}
}
