package ru.omsu.imit.course3.first.lab3;

import ru.omsu.imit.course3.first.lab1.Trainee;
import ru.omsu.imit.course3.first.lab1.TraineeErrorCode;
import ru.omsu.imit.course3.first.lab1.TraineeException;

import java.util.Arrays;

public class Group {
	private String name;
	private Trainee[] students;

	public Group(String name, Trainee[] students) throws TraineeException {
		if (name == null || name.equals("")) throw new TraineeException(TraineeErrorCode.GROUP_WRONG_NAME);
		if (students == null || students.length == 0) throw new TraineeException(TraineeErrorCode.GROUP_STUDENTS_EMPTY);
		this.name = name;
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws TraineeException {
		if (name == null || name.equals("")) throw new TraineeException(TraineeErrorCode.GROUP_WRONG_NAME);
		this.name = name;
	}

	public Trainee[] getStudents() {
		return students;
	}

	public void setStudents(Trainee[] students) throws TraineeException {
		if (students == null || students.length == 0) throw new TraineeException(TraineeErrorCode.GROUP_STUDENTS_EMPTY);
		this.students = students;
	}

	@Override
	public String toString() {
		return "Group{" +
				"name='" + name + '\'' +
				", students=" + Arrays.toString(students) +
				'}';
	}
}
