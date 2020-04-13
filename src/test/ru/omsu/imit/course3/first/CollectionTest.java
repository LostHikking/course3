package ru.omsu.imit.course3.first;

import org.junit.Assert;
import org.junit.Test;
import ru.omsu.imit.course3.first.lab1.Trainee;
import ru.omsu.imit.course3.first.lab1.TraineeException;
import ru.omsu.imit.course3.first.lab3.Group;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static ru.omsu.imit.course3.first.lab1.TraineeErrorCode.GROUP_STUDENTS_EMPTY;
import static ru.omsu.imit.course3.first.lab1.TraineeErrorCode.GROUP_WRONG_NAME;

public class CollectionTest {

	@Test
	public void createGroupEmptyName() {
		try {
			Trainee trainee = new Trainee("Lol", "Kek", 5);
			Trainee[] trainees = new Trainee[1];
			trainees[0] = trainee;
			Group group = new Group("", trainees);
			Assert.fail("Expected TraineeException");
		} catch (TraineeException e) {
			assertEquals(GROUP_WRONG_NAME.getErrorString(), e.getMessage());
		}
	}


	@Test
	public void createGroupEmptyTrainees() {
		try {
			Group group = new Group("МПБ", null);
			Assert.fail("Expected TraineeException");
		} catch (TraineeException e) {
			assertEquals(GROUP_STUDENTS_EMPTY.getErrorString(), e.getMessage());
		}
	}

	@Test
	public void sortStudentsByRating() {
		try {
			Trainee trainee = new Trainee("Lol", "Kek", 5);
			Trainee trainee1 = new Trainee("Lol", "Kek", 2);
			Trainee trainee2 = new Trainee("Lol", "Kek", 4);
			Trainee trainee3 = new Trainee("Lol", "Kek", 1);
			Trainee trainee4 = new Trainee("Lol", "Kek", 5);
			Trainee[] trainees = new Trainee[5];
			trainees[0] = trainee;
			trainees[1] = trainee1;
			trainees[2] = trainee2;
			trainees[3] = trainee3;
			trainees[4] = trainee4;
			Group group = new Group("МПБ", trainees);
			System.out.println(group.toString());
			Arrays.sort(group.getStudents(), Comparator.comparingInt(Trainee::getRating));
			System.out.println(group.toString());
		} catch (TraineeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void sortStudentsByName() {
		try {
			Trainee trainee = new Trainee("a", "b", 5);
			Trainee trainee1 = new Trainee("c", "n", 2);
			Trainee trainee2 = new Trainee("g", "g", 4);
			Trainee trainee3 = new Trainee("d", "d", 1);
			Trainee trainee4 = new Trainee("a", "a", 5);
			Trainee[] trainees = new Trainee[5];
			trainees[0] = trainee;
			trainees[1] = trainee1;
			trainees[2] = trainee2;
			trainees[3] = trainee3;
			trainees[4] = trainee4;
			Group group = new Group("МПБ", trainees);
			System.out.println(group.toString());
			Arrays.sort(group.getStudents());
			System.out.println(group.toString());
		} catch (TraineeException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void findStudent() {
		try {
			Trainee trainee = new Trainee("a", "b", 5);
			Trainee trainee1 = new Trainee("c", "n", 2);
			Trainee trainee2 = new Trainee("g", "g", 4);
			Trainee trainee3 = new Trainee("g", "d", 1);
			Trainee trainee4 = new Trainee("a", "a", 5);
			Trainee[] trainees = new Trainee[5];
			trainees[0] = trainee;
			trainees[1] = trainee1;
			trainees[2] = trainee2;
			trainees[3] = trainee3;
			trainees[4] = trainee4;
			Group group = new Group("МПБ", trainees);
			String name = "g";
			for (int i = 0; i < group.getStudents().length; i++) {
				if (group.getStudents()[i].getFirstName().equals(name))
					System.out.println(group.getStudents()[i]);
			}
		} catch (TraineeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void trainessArrayList() throws TraineeException {
		ArrayList<Trainee> trainees = new ArrayList<>();
		Trainee trainee = new Trainee("a", "b", 5);
		Trainee trainee1 = new Trainee("c", "n", 2);
		Trainee trainee2 = new Trainee("g", "g", 4);
		Trainee trainee3 = new Trainee("g", "d", 1);
		Trainee trainee4 = new Trainee("a", "a", 5);
		trainees.add(trainee);
		trainees.add(trainee1);
		trainees.add(trainee2);
		trainees.add(trainee3);
		trainees.add(trainee4);
		System.out.println(trainees);
		Collections.reverse(trainees);
		System.out.println(trainees);
		Collections.rotate(trainees, 2);
		System.out.println(trainees);
		Collections.shuffle(trainees);
		System.out.println(trainees);
		System.out.println(Collections.max(trainees, Comparator.comparingInt(Trainee::getRating)));
		trainees.sort(Comparator.comparingInt(Trainee::getRating));
		System.out.println(trainees);
		Collections.sort(trainees);
		System.out.println(trainees);
		String name = "a";
		System.out.println(trainees.stream().filter(t -> t.getFirstName().equals(name)).findAny().get());
	}

	@Test
	public void trainessLinkedList() throws TraineeException {
		LinkedList<Trainee> trainees = new LinkedList<>();
		Trainee trainee = new Trainee("a", "b", 5);
		Trainee trainee1 = new Trainee("c", "n", 2);
		Trainee trainee2 = new Trainee("g", "g", 4);
		Trainee trainee3 = new Trainee("g", "d", 1);
		Trainee trainee4 = new Trainee("a", "a", 5);
		trainees.add(trainee);
		trainees.add(trainee1);
		trainees.add(trainee2);
		trainees.add(trainee3);
		trainees.add(trainee4);
		System.out.println(trainees);
		Collections.reverse(trainees);
		System.out.println(trainees);
		Collections.rotate(trainees, 2);
		System.out.println(trainees);
		Collections.shuffle(trainees);
		System.out.println(trainees);
		System.out.println(Collections.max(trainees, Comparator.comparingInt(Trainee::getRating)));
		trainees.sort(Comparator.comparingInt(Trainee::getRating));
		System.out.println(trainees);
		Collections.sort(trainees);
		System.out.println(trainees);
		String name = "a";
		System.out.println(trainees.stream().filter(t -> t.getFirstName().equals(name)).findAny().get());
	}

	@Test
	public void speedTestArrayList(){
		ArrayList<Integer> list = new ArrayList<>();
		Random rand = new Random();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			list.add(i);
		}
		for (int i = 0; i < 100000; i++) {
			list.get(rand.nextInt(list.size()));
		}
		long finish = System.currentTimeMillis();
		System.out.println(finish - start);
	}

	@Test
	public void speedTestLinkedList(){
		LinkedList<Integer> list = new LinkedList<>();
		Random rand = new Random();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			list.add(i);
		}
		for (int i = 0; i < 100000; i++) {
			list.get(rand.nextInt(list.size()));
		}
		long finish = System.currentTimeMillis();
		System.out.println(finish - start);
	}
}
