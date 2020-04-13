package ru.omsu.imit.course3.first;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import ru.omsu.imit.course3.first.lab1.Trainee;
import ru.omsu.imit.course3.first.lab1.TraineeException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class TraineeTest {

	@Test
	public void traineeSuccessCreate() {
		Trainee trainee;
		try {
			trainee = new Trainee("lol", "kek", 5);
		} catch (TraineeException e) {
			Assert.fail("Unexpected exception");
			e.printStackTrace();
		}
	}

	@Test
	public void traineeExceptionOnRationTest() {
		Trainee trainee;
		try {
			trainee = new Trainee("lol", "kek", 10);
			Assert.fail("Expected exception");
		} catch (TraineeException e) {
			assertEquals("Rating must be 1 to 5", e.getMessage());
		}
	}

	@Test
	public void traineeExceptionOnNullTest() {
		Trainee trainee;
		try {
			trainee = new Trainee("lol", null, 5);
			Assert.fail("Expected exception");
		} catch (TraineeException e) {
			assertEquals("Last name mustn't be null", e.getMessage());
		}
	}

	@Test
	public void traineeExceptionOnSetterTest() {
		Trainee trainee;
		try {
			trainee = new Trainee("lol", "kek", 5);
			trainee.setFirstName(null);
		} catch (TraineeException e) {
			assertEquals("First name mustn't be null", e.getMessage());
		}
	}

	@Test
	public void traineeWriteNewLinesAndRead() throws TraineeException, IOException {
		Trainee trainee = new Trainee("lol", "kek", 5);
		File file = new File("trainee");
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(trainee.getFirstName() + "\n"
				+ trainee.getLastName() + "\n"
				+ trainee.getRating() + "\n");
		writer.close();
		Scanner myReader = new Scanner(file);
		List<String> traineeInStr = new ArrayList<>();
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			traineeInStr.add(data);
		}
		Trainee traineeFromStr = new Trainee(traineeInStr.get(0), traineeInStr.get(1), Integer.parseInt(traineeInStr.get(2)));
		assertEquals(trainee, traineeFromStr);
		file.delete();
	}

	@Test
	public void traineeWriteOneLineAndRead() {
		Trainee trainee = null;
		try {
			trainee = new Trainee("lol", "kek", 5);
		File file = new File("traineeOneLine");
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(trainee.getFirstName() + " "
				+ trainee.getLastName() + " "
				+ trainee.getRating() + "\n");
		writer.close();
		Scanner myReader = new Scanner(file);
		List<String> traineeInStr = new ArrayList<>();
		while (myReader.hasNextLine()) {
			String[] data = myReader.nextLine().split(" ");
			traineeInStr.add(data[0]);
			traineeInStr.add(data[1]);
			traineeInStr.add(data[2]);
		}
		Trainee traineeFromStr = new Trainee(traineeInStr.get(0), traineeInStr.get(1), Integer.parseInt(traineeInStr.get(2)));
		assertEquals(trainee, traineeFromStr);
		file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void serAndDeserTrainee() {
		Trainee trainee;
		Trainee traineeFromFile;
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("trainee.dat"))) {
			trainee = new Trainee("Lol", "Kek", 5);
			oos.writeObject(trainee);
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("trainee.dat"));
			traineeFromFile = (Trainee) ois.readObject();
			assertEquals(trainee, traineeFromFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void serAndDeserTraineeBA() {
		Trainee trainee;
		Trainee traineeFromFile;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			trainee = new Trainee("Lol", "Kek", 5);
			oos.writeObject(trainee);
			oos.flush();
			byte[] inBytes = baos.toByteArray();
			ByteArrayInputStream bis = new ByteArrayInputStream(inBytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			traineeFromFile = (Trainee) ois.readObject();
			assertEquals(trainee, traineeFromFile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void gsonTrainee() {
		try {
			Gson gson = new Gson();
			Trainee trainee = new Trainee("Lol", "Kek", 5);
			String json = gson.toJson(trainee);
			File file = new File("trainee.json");
			file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(json);
			writer.flush();
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				assertEquals(trainee, gson.fromJson(data, Trainee.class));
			}
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//________________________TASK2(NIO)_____________________

	@Test
	public void byteBooferTest() {
		try {
			Trainee trainee = new Trainee("lol", "kek", 5);
			File file = new File("traineeOneLine");
			file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(trainee.getFirstName() + " "
					+ trainee.getLastName() + " "
					+ trainee.getRating() + "\n");
			writer.close();
			RandomAccessFile aFile = new RandomAccessFile(
					"traineeOneLine", "r");
			FileChannel inChannel = aFile.getChannel();
			long fileSize = inChannel.size();
			ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);
			inChannel.read(buffer);
			buffer.flip();
			StringBuilder traineeInStr = new StringBuilder();
			for (int i = 0; i < fileSize; i++) {
				traineeInStr.append((char) buffer.get());
			}
			assertEquals("lol kek 5\n", traineeInStr.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void mappedByteBooferTest() {
		try {
			Trainee trainee = new Trainee("lol", "kek", 5);
			File file = new File("traineeOneLine");
			file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(trainee.getFirstName() + " "
					+ trainee.getLastName() + " "
					+ trainee.getRating() + "\n");
			writer.close();
			RandomAccessFile aFile = new RandomAccessFile(
					"traineeOneLine", "r");
			FileChannel inChannel = aFile.getChannel();
			long fileSize = inChannel.size();
			MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
			buffer.load();
			StringBuilder traineeInStr = new StringBuilder();
			for (int i = 0; i < fileSize; i++) {
				traineeInStr.append((char) buffer.get());
			}
			assertEquals("lol kek 5\n", traineeInStr.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void givenPath_whenWriteToItUsingMappedByteBuffer_thenShouldSuccessfullyWrite() {
		Path file = new File("somenewfile").toPath();
		try (FileChannel channel = new RandomAccessFile(new File(file.toUri()), "rw").getChannel()) {
			MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 400);
			for (int i = 0; i < 100; i++) {
				buffer.putInt(i);
			}
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void serializeTraineeToByteBuffer() {
		try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream(500);
		     ObjectOutputStream objectStream = new ObjectOutputStream(byteStream)) {
			Trainee trainee = new Trainee("lol", "kek", 5);
			byte[] inBytes;
			objectStream.writeObject(trainee);
			inBytes = byteStream.toByteArray();
			ByteArrayInputStream bis = new ByteArrayInputStream(inBytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			Trainee traineeFromFile = (Trainee) ois.readObject();
			assertEquals(trainee, traineeFromFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//		try {
//			File file = new File("newfile");
//			file.createNewFile();
//			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//			String numbers = "";
//			for (int i = 0; i < 100; i++) {
//				numbers = numbers + i + "\n";
//			}
//			FileChannel fileChannel = (FileChannel) Files.newByteChannel(file, EnumSet.of(StandardOpenOption.READ,
//					StandardOpenOption.WRITE))
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
}
