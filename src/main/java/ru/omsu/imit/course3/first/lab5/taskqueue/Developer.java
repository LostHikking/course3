package ru.omsu.imit.course3.first.lab5.taskqueue;


public class Developer extends Thread {
	private TaskQueue taskQueue;
	private int count;

	public Developer(TaskQueue taskQueue, int count) {
		this.taskQueue = taskQueue;
		this.count = count;
	}

	@Override
	public void run() {
		for (int i = 0; i < count; i++) {
			ExecutableImpl task = new ExecutableImpl("from " + Thread.currentThread().getName() + " " + i);
			taskQueue.put(task);
		}
		System.out.println(Thread.currentThread().getName() + " stoped");
	}
}
