package ru.omsu.imit.course3.first.lab5.pingpong.cond;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PingPong {
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();

	private boolean send = true;

	public void ping() throws InterruptedException {
		lock.lock();
		try {
			while (!send) {
				condition1.await();
			}
			System.out.println("Ping");
			send = false;
			condition2.signal();
		} finally {
			lock.unlock();
		}
	}

	public void pong() throws InterruptedException {
		lock.lock();
		try {
			while (send) {
				condition2.await();
			}
			System.out.println("Pong");
			send = true;
			condition1.signal();
		} finally {
			lock.unlock();
		}
	}
}
