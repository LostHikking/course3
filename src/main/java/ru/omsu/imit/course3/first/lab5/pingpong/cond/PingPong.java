package ru.omsu.imit.course3.first.lab5.pingpong.cond;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PingPong {
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();

	private boolean send = true;

	public void ping() throws InterruptedException {
		lock.lock();
		try {
			while (!send) {
				condition.await();
			}
			System.out.println("Ping");
			send = false;
			condition.signal();
		} finally {
			lock.unlock();
		}
	}

	public void pong() throws InterruptedException {
		lock.lock();
		try {
			while (send) {
				condition.await();
			}
			System.out.println("Pong");
			send = true;
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
}
