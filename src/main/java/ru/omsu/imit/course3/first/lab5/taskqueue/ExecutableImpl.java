package ru.omsu.imit.course3.first.lab5.taskqueue;


public class ExecutableImpl implements Executable {
	private String s;

	public ExecutableImpl(String s) {
		this.s = s;
	}

	@Override
	public void execute() {
		System.out.println(Thread.currentThread().getName() + " execute task " + s);
	}
}
