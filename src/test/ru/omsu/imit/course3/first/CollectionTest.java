package ru.omsu.imit.course3.first;

import org.junit.Assert;
import org.junit.Test;
import ru.omsu.imit.course3.first.lab1.Trainee;
import ru.omsu.imit.course3.first.lab1.TraineeException;
import ru.omsu.imit.course3.first.lab3.Color;
import ru.omsu.imit.course3.first.lab3.Group;
import ru.omsu.imit.course3.first.lab3.Institute;
import ru.omsu.imit.course3.first.lab3.MatrixServ;

import java.util.*;

import static org.junit.Assert.*;
import static ru.omsu.imit.course3.first.lab1.TraineeErrorCode.GROUP_STUDENTS_EMPTY;
import static ru.omsu.imit.course3.first.lab1.TraineeErrorCode.GROUP_WRONG_NAME;
import static ru.omsu.imit.course3.first.lab3.Color.*;

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


	@Test
	public void traineeQueue() throws TraineeException {
		Queue<Trainee> traineeQueue = new PriorityQueue<>();
		Trainee trainee = new Trainee("a", "b", 5);
		Trainee trainee1 = new Trainee("c", "n", 2);
		Trainee trainee2 = new Trainee("g", "g", 4);
		Trainee trainee3 = new Trainee("g", "d", 1);
		Trainee trainee4 = new Trainee("a", "a", 5);
		traineeQueue.add(trainee);
		traineeQueue.add(trainee1);
		traineeQueue.add(trainee2);
		traineeQueue.add(trainee3);
		traineeQueue.add(trainee4);
		if (traineeQueue.isEmpty())
			System.out.println("Очередь пуста");
		else
			System.out.println("Очередь не пуста");
		while (!traineeQueue.isEmpty()) {
			System.out.println(traineeQueue.poll());
		}
		if (traineeQueue.isEmpty())
			System.out.println("Очередь пуста");
		else
			System.out.println("Очередь не пуста");
	}

	@Test
	public void traineeHashSet() throws TraineeException {
		HashSet<Trainee> traineeHashSet = new HashSet<>();
		Trainee trainee = new Trainee("a", "b", 5);
		Trainee trainee1 = new Trainee("c", "n", 2);
		Trainee trainee2 = new Trainee("g", "g", 4);
		Trainee trainee3 = new Trainee("g", "d", 1);
		Trainee trainee4 = new Trainee("a", "a", 5);
		Trainee trainee5 = new Trainee("t", "y", 5);
		traineeHashSet.add(trainee);
		traineeHashSet.add(trainee1);
		traineeHashSet.add(trainee2);
		traineeHashSet.add(trainee3);
		traineeHashSet.add(trainee4);
		assertTrue(traineeHashSet.contains(trainee4));
		assertFalse(traineeHashSet.contains(trainee5));
		System.out.println(traineeHashSet);
	}

	@Test
	public void traineeTreeSet() throws TraineeException {
		TreeSet<Trainee> traineeTreeSet = new TreeSet<>();
		Trainee trainee = new Trainee("a", "b", 5);
		Trainee trainee1 = new Trainee("c", "n", 2);
		Trainee trainee2 = new Trainee("g", "g", 4);
		Trainee trainee3 = new Trainee("g", "d", 1);
		Trainee trainee4 = new Trainee("a", "a", 5);
		Trainee trainee5 = new Trainee("a", "y", 5);
		traineeTreeSet.add(trainee);
		traineeTreeSet.add(trainee1);
		traineeTreeSet.add(trainee2);
		traineeTreeSet.add(trainee3);
		traineeTreeSet.add(trainee4);
		assertTrue(traineeTreeSet.contains(trainee4));
		assertFalse(traineeTreeSet.contains(trainee5));
		System.out.println(traineeTreeSet);
	}

	@Test
	public void speedTest() {
		ArrayList<Integer> list = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i < 100000; i++) {
			list.add(rand.nextInt());
		}
		long start = System.nanoTime();
		list.contains(rand.nextInt());
		long finish = System.nanoTime();
		System.out.println("ArrayList " + (finish - start));
		HashSet<Integer> hashSet = new HashSet<>();
		for (int i = 0; i < 100000; i++) {
			hashSet.add(rand.nextInt());
		}
		start = System.nanoTime();
		hashSet.contains(rand.nextInt());
		finish = System.nanoTime();
		System.out.println("HashSet " + (finish - start));
		TreeSet<Integer> treeSet = new TreeSet<>();
		for (int i = 0; i < 100000; i++) {
			treeSet.add(rand.nextInt());
		}
		start = System.nanoTime();
		treeSet.contains(rand.nextInt());
		finish = System.nanoTime();
		System.out.println("TreeSet " + (finish - start));
	}

	@Test
	public void hashMapTest() throws TraineeException {
		HashMap<Trainee, Institute> hashMap = new HashMap<>();
		Trainee trainee = new Trainee("a", "b", 5);
		Trainee trainee1 = new Trainee("c", "n", 2);
		Trainee trainee2 = new Trainee("g", "g", 4);
		Trainee trainee3 = new Trainee("g", "d", 1);
		Trainee trainee4 = new Trainee("a", "a", 5);
		Trainee trainee5 = new Trainee("a", "y", 5);
		Institute institute = new Institute("OmSU", "Omsk");
		Institute institute1 = new Institute("HSE", "SPB");
		Institute institute2 = new Institute("MSU", "MSK");
		hashMap.put(trainee, institute1);
		hashMap.put(trainee1, institute1);
		hashMap.put(trainee2, institute);
		hashMap.put(trainee3, institute2);
		hashMap.put(trainee4, institute2);
		hashMap.put(trainee5, institute1);
		System.out.println(hashMap.get(trainee2).toString());
		System.out.println(hashMap.keySet());
	}

	@Test
	public void treeMapTest() throws TraineeException {
		TreeMap<Trainee, Institute> treeMap = new TreeMap<>();
		Trainee trainee = new Trainee("a", "b", 5);
		Trainee trainee1 = new Trainee("c", "n", 2);
		Trainee trainee2 = new Trainee("g", "g", 4);
		Trainee trainee3 = new Trainee("g", "d", 1);
		Trainee trainee4 = new Trainee("a", "a", 5);
		Trainee trainee5 = new Trainee("a", "y", 5);
		Institute institute = new Institute("OmSU", "Omsk");
		Institute institute1 = new Institute("HSE", "SPB");
		Institute institute2 = new Institute("MSU", "MSK");
		treeMap.put(trainee, institute1);
		treeMap.put(trainee1, institute1);
		treeMap.put(trainee2, institute);
		treeMap.put(trainee3, institute2);
		treeMap.put(trainee4, institute2);
		treeMap.put(trainee5, institute1);
		System.out.println(treeMap.get(trainee2).toString());
		System.out.println(treeMap.keySet());
		System.out.println(treeMap);
	}


	@Test
	public void treeMapAnotherTest() throws TraineeException {
		TreeMap<Trainee, Institute> treeMap = new TreeMap<>();
		Trainee trainee = new Trainee("a", "b", 5);
		Trainee trainee1 = new Trainee("c", "n", 2);
		Trainee trainee2 = new Trainee("g", "g", 4);
		Trainee trainee3 = new Trainee("g", "d", 1);
		Trainee trainee4 = new Trainee("a", "a", 5);
		Trainee trainee5 = new Trainee("a", "y", 5);
		Institute institute = new Institute("OmSU", "Omsk");
		Institute institute1 = new Institute("HSE", "SPB");
		Institute institute2 = new Institute("MSU", "MSK");
		treeMap.put(trainee, institute1);
		treeMap.put(trainee1, institute1);
		treeMap.put(trainee2, institute);
		treeMap.put(trainee3, institute2);
		treeMap.put(trainee4, institute2);
		if (treeMap.keySet().contains(trainee5))
			System.out.println("TreeMap содержит " + trainee5);
		else
			System.out.println("TreeMap не содержит " + trainee5);
		System.out.println(treeMap.get(trainee2).toString());
		System.out.println(treeMap.keySet());
		System.out.println(treeMap);
	}


	@Test
	public void bitSetTest() {
		BitSet bitSet = new BitSet();
		for (int i = 0; i < 1000000; i++) {
			bitSet.set(i);
		}
		System.out.println(bitSet.get(50));
		bitSet.clear(50);
		System.out.println(bitSet.get(50));
	}


	@Test
	public void speedTestSet() {
		BitSet bitSet = new BitSet();
		HashSet<Integer> hashSet = new HashSet<>();
		TreeSet<Integer> treeSet = new TreeSet<>();
		long start;
		long finish;
		start = System.nanoTime();
		for (int i = 0; i < 1000000; i++) {
			bitSet.set(i);
		}
		finish = System.nanoTime();
		System.out.println("BitSet " + (finish - start));
		start = System.nanoTime();
		for (int i = 0; i < 1000000; i++) {
			hashSet.add(i);
		}
		finish = System.nanoTime();
		System.out.println("HashSet " + (finish - start));
		start = System.nanoTime();
		for (int i = 0; i < 1000000; i++) {
			treeSet.add(i);
		}
		finish = System.nanoTime();
		System.out.println("TreeSet " + (finish - start));
	}


	@Test
	public void enumSetTest() {
		EnumSet<Color> all = EnumSet.allOf(Color.class);
		EnumSet<Color> one = EnumSet.of(RED);
		EnumSet<Color> empty = EnumSet.noneOf(Color.class);
		EnumSet<Color> range = EnumSet.range(YELLOW, BLUE);
		Color color = VIOLET;
		System.out.println(all.contains(color));
		System.out.println(one.contains(color));
		System.out.println(empty.contains(color));
		System.out.println(range.contains(color));
	}

	@Test
	public void matrixTest() {
		Random random = new Random();
		Integer[][] matrix = new Integer[3][3];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = random.nextInt(10);
			}
		}
		System.out.println(Arrays.deepToString(matrix));
		System.out.println(Arrays.deepToString(MatrixServ.findSetsUnlike(matrix)));
	}

	@Test
	public void matrixFromExampleTest() {
		Random random = new Random();
		Integer[][] matrix = new Integer[3][3];
		matrix[0][0] = 1;
		matrix[0][1] = 2;
		matrix[0][2] = 3;
		matrix[1][0] = 3;
		matrix[1][1] = 2;
		matrix[1][2] = 1;
		matrix[2][0] = 4;
		matrix[2][1] = 2;
		matrix[2][2] = 1;
		System.out.println(Arrays.deepToString(matrix));
		System.out.println(Arrays.deepToString(MatrixServ.findSetsUnlike(matrix)));
	}
}
