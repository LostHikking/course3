package ru.omsu.imit.course3.first.lab5.taskqueueext;

import ru.omsu.imit.course3.first.lab5.taskqueue.Executable;

import java.util.List;

public class Task {
	private List<Executable> stages;
	private int currentStage;

	public Task(List<Executable> stages) {
		this.stages = stages;
		currentStage = 0;
	}

	public List<Executable> getStages() {
		return stages;
	}

	public int getCurrentStage() {
		return currentStage;
	}

	public void next() {
		currentStage++;
	}

	public Executable get(int i) {
		return stages.get(i);
	}

	public boolean isEnd() {
		return currentStage == stages.size();
	}
}
