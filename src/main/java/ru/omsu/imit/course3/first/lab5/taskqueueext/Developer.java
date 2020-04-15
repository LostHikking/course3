package ru.omsu.imit.course3.first.lab5.taskqueueext;

import ru.omsu.imit.course3.first.lab5.taskqueue.Executable;
import ru.omsu.imit.course3.first.lab5.taskqueue.ExecutableImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Developer extends Thread {
	private TaskQueue qt;
	private Random random;
	private int count;

	public Developer(TaskQueue qt, int count) {
		this.qt = qt;
		random = new Random();
		this.count = count;
	}

	@Override
	public void run() {
		for (int i = 0; i < count; i++) {
			List<Executable> executables = new ArrayList<>();
			int size = random.nextInt(7) + 1;
			for (int j = 0; j < size; j++) {
				executables.add(new ExecutableImpl("from " + Thread.currentThread().getName() + " " + i + "." + j + " stage"));
			}
			qt.addTask(new Task(executables));
		}
		System.out.println(Thread.currentThread().getName() + " stoped");
	}
}
