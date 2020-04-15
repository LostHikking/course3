package ru.omsu.imit.course3.first.lab5.prodandcons.syncblock;

import java.util.List;
import java.util.Random;

public class Consumer implements Runnable {

	private final List<Integer> list;

	public Consumer(List<Integer> list) {
		this.list = list;
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		Random rand = new Random();
		int i = 0;
		while (true) {
			synchronized (list) {
				if (list.size() > 0) {
					int j = rand.nextInt(list.size());
					list.remove(j);
					System.out.println("Consume " + j);
					i++;
				}
				if (i == Producer.N - 1) break;
			}
		}
	}
}
