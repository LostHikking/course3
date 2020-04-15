package ru.omsu.imit.course3.first.lab5.pingpong.sem;

import java.util.concurrent.Semaphore;

public class Pong implements Runnable {

	private Semaphore semaphore;
	private Semaphore semaphore1;

	public Pong(Semaphore semaphore, Semaphore semaphore1) {
		this.semaphore = semaphore;
		this.semaphore1 = semaphore1;
	}

	@Override
	public void run() {
		while (true) {
			try {
				semaphore1.acquire();
				System.out.println("Pong");
				semaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
