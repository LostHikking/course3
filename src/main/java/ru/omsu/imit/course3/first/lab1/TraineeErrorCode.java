package ru.omsu.imit.course3.first.lab1;

public enum TraineeErrorCode {
	TRAINEE_WRONG_FIRSTNAME("First name mustn't be null"),
	TRAINEE_WRONG_LASTNAME("Last name mustn't be null"),
	TRAINEE_WRONG_RATING("Rating must be 1 to 5"),
	GROUP_WRONG_NAME("Name mustn't be empty"),
	GROUP_STUDENTS_EMPTY("Students mustn't be empty"),
	TRAINEE_NOT_FOUND("Specified trainee doesn't exist"),
	INSTITUTE_WRONG_NAME("Name mustn't be empty"),
	DUPLICATE_TRAINEE("This Trainee is already added"),
	EMPTY_TRAINEE_QUEUE("The queue is empty");

	private String errorString;

	TraineeErrorCode(String errorCode) {
		this.errorString = errorCode;
	}

	public String getErrorString() {
		return errorString;
	}
}
