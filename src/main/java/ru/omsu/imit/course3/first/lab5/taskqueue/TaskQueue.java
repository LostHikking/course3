package ru.omsu.imit.course3.first.lab5.taskqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue {
	private BlockingQueue<Executable> tasks;


	public TaskQueue() {
		tasks = new LinkedBlockingQueue<>();
	}

	public void put(Executable e) {
		System.out.println("New task from " + Thread.currentThread().getName());
		tasks.offer(e);
	}

	public void execute() {
		Executable task;
		try {
			task = tasks.take();
			task.execute();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public Executable get() throws InterruptedException {
		return tasks.take();
	}

	public boolean isEmpty() {
		return tasks.isEmpty();
	}

}
