package ru.omsu.imit.course3.first.lab1;

import java.io.Serializable;
import java.util.Objects;

public class Trainee implements Serializable, Comparable<Trainee> {
	private String lastName, firstName;
	private int rating;

	public Trainee(String firstName, String lastName, int rating) throws TraineeException {
		setFirstName(firstName);
		setLastName(lastName);
		setRating(rating);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws TraineeException {
		if (firstName == null || firstName.equals(""))
			throw new TraineeException(TraineeErrorCode.TRAINEE_WRONG_FIRSTNAME);
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws TraineeException {
		if (lastName == null || lastName.equals(""))
			throw new TraineeException(TraineeErrorCode.TRAINEE_WRONG_LASTNAME);
		this.lastName = lastName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) throws TraineeException {
		if (rating < 1 || rating > 5) throw new TraineeException(TraineeErrorCode.TRAINEE_WRONG_RATING);
		this.rating = rating;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Trainee trainee = (Trainee) o;
		return rating == trainee.rating &&
				Objects.equals(lastName, trainee.lastName) &&
				Objects.equals(firstName, trainee.firstName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lastName, firstName, rating);
	}


	@Override
	public int compareTo(Trainee o) {
		return getFullName().compareTo(o.getFullName());
	}
}
