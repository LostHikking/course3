package ru.omsu.imit.course3.first.lab5.taskqueue;


public class Worker extends Thread {
	private final TaskQueue taskQueue;
	private long time;

	public Worker(TaskQueue taskQueue) {
		this.taskQueue = taskQueue;
		time = System.currentTimeMillis();
	}

	@Override
	public void run() {
		do {
			synchronized (taskQueue) {
				if (!taskQueue.isEmpty()) {
					taskQueue.execute();
					time = System.currentTimeMillis();
				}
			}
		} while (System.currentTimeMillis() - time <= 10000);
		System.out.println(Thread.currentThread().getName() + " stoped");
	}
}
