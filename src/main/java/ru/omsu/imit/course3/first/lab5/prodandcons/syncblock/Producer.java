package ru.omsu.imit.course3.first.lab5.prodandcons.syncblock;

import java.util.List;
import java.util.Random;

public class Producer implements Runnable {
	private final List<Integer> list;
	public static final int N = 10000;

	public Producer(List<Integer> list) {
		this.list = list;
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		Random rand = new Random();
		for (int i = 0; i < N; i++) {
			synchronized (list) {
				list.add(rand.nextInt());
				System.out.println("Produce " + i);
			}
		}
	}
}
