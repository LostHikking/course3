package ru.omsu.imit.course3.first.lab5.taskqueueext;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue {
	private final BlockingQueue<Task> queue;

	public TaskQueue() {
		queue = new LinkedBlockingQueue<>();
	}

	public void addTask(Task task) {
		System.out.println("New task from " + Thread.currentThread().getName() + " " + task.getStages().size());
		queue.offer(task);
	}


	public void execute() {
		Task task;
		try {
			task = queue.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		if (task.isEnd())
			return;
		task.get(task.getCurrentStage()).execute();
		task.next();
		queue.offer(task);
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}
}

