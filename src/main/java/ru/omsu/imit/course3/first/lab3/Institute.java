package ru.omsu.imit.course3.first.lab3;

import ru.omsu.imit.course3.first.lab1.TraineeErrorCode;
import ru.omsu.imit.course3.first.lab1.TraineeException;

public class Institute {
	private String name;
	private String city;

	public Institute(String name, String city) throws TraineeException {
		if (name == null || name.equals("")) throw new TraineeException(TraineeErrorCode.INSTITUTE_WRONG_NAME);
		if (city == null || city.equals("")) throw new TraineeException(TraineeErrorCode.INSTITUTE_WRONG_CITY);
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Institute{" +
				"name='" + name + '\'' +
				", city='" + city + '\'' +
				'}';
	}
}

