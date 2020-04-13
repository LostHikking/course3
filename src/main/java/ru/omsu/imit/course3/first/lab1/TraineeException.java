package ru.omsu.imit.course3.first.lab1;

public class TraineeException extends Exception {
	private TraineeErrorCode code;

	public TraineeException(TraineeErrorCode code) {
		super(code.getErrorString());
		this.code = code;
	}

	public TraineeErrorCode getErrorCode() {
		return code;
	}
}
