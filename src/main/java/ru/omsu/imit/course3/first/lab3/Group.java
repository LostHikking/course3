package ru.omsu.imit.course3.first.lab3;

import ru.omsu.imit.course3.first.lab1.Trainee;
import ru.omsu.imit.course3.first.lab1.TraineeErrorCode;
import ru.omsu.imit.course3.first.lab1.TraineeException;

import java.util.*;

public class Group {
	private String groupName;
	private List<Trainee> students;

	public Group(String name) throws TraineeException {
		students = new ArrayList<>();
		setName(name);
	}

	public String getName() {
		return groupName;
	}

	public void setName(String name) throws TraineeException {
		if (name == null || name.equals("")) throw new TraineeException(TraineeErrorCode.GROUP_WRONG_NAME);
		groupName = name;
	}


	public List<Trainee> getTrainees() {
		return students;
	}

	public void addTrainee(Trainee trainee) {
		students.add(trainee);
	}

	public void removeTrainee(Trainee trainee) throws TraineeException {
		if (!students.remove(trainee)) throw new TraineeException(TraineeErrorCode.TRAINEE_NOT_FOUND);
	}

	public void removeTrainee(int index) throws TraineeException {
		try {
			students.remove(index);
		} catch (IndexOutOfBoundsException e) {
			throw new TraineeException(TraineeErrorCode.TRAINEE_NOT_FOUND);
		}
	}

	public Trainee getTraineeByFirstName(String firstName) throws TraineeException {
		for (Trainee trn : students) {
			if (trn.getFirstName().equals(firstName)) return trn;
		}
		throw new TraineeException(TraineeErrorCode.TRAINEE_NOT_FOUND);
	}

	public Trainee getTraineeByFullName(String fullName) throws TraineeException {
		for (Trainee trn : students) {
			if (trn.getFullName().equals(fullName)) return trn;
		}
		throw new TraineeException(TraineeErrorCode.TRAINEE_NOT_FOUND);
	}

	public void sortTraineeListByFirstNameAscendant() {
		students.sort(Comparator.comparing(Trainee::getFirstName));
	}

	public void sortTraineeListByRatingDescendant() {
		students.sort((s, t) -> t.getRating() - s.getRating());
	}

	public void reverseTraineeList() {
		Collections.reverse(students);
	}

	public void rotateTraineeList(int positions) {
		Collections.rotate(students, positions);
	}

	public List<Trainee> getTraineesWithMaxRating() throws TraineeException {
		if (students.isEmpty()) throw new TraineeException(TraineeErrorCode.TRAINEE_NOT_FOUND);
		List<Trainee> res = new ArrayList<>();
		int max = 0;
		for (Trainee trainee : students) {
			if (trainee.getRating() > max) {
				max = trainee.getRating();
				res.clear();
				res.add(trainee);
			} else {
				if (trainee.getRating() == max) res.add(trainee);
			}
		}
		return res;
	}

	public boolean hasDuplicates() {
		return new HashSet<>(students).size() < students.size();
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Group group = (Group) o;
		return Objects.equals(groupName, group.groupName) &&
				Objects.equals(students, group.students);
	}

	@Override
	public int hashCode() {
		return Objects.hash(groupName, students);
	}
}
