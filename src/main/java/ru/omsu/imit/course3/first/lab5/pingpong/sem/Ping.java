package ru.omsu.imit.course3.first.lab5.pingpong.sem;

import java.util.concurrent.Semaphore;

public class Ping implements Runnable {

	private Semaphore semaphore;
	private Semaphore semaphore1;

	public Ping(Semaphore semaphore, Semaphore semaphore1) {
		this.semaphore = semaphore;
		this.semaphore1 = semaphore1;
	}

	@Override
	public void run() {
		while (true) {
			try {
				semaphore.acquire();
				System.out.println("Ping");
				semaphore1.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
