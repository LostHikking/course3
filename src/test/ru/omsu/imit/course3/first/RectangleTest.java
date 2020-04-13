package ru.omsu.imit.course3.first;

import org.junit.Test;
import ru.omsu.imit.course3.first.lab1.Rectangle;
import ru.omsu.imit.course3.first.lab1.ReverseLineInputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;

public class RectangleTest {
	@Test
	public void createRectangle() throws IOException {
		Rectangle rectangle = new Rectangle(new Rectangle.Point(5, 7), new Rectangle.Point(9, 15));

		File file = new File("rectangle");
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(String.valueOf(rectangle));
		writer.close();
		Scanner myReader = new Scanner(file);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			List<Integer> coords = new ArrayList<>();
			String[] pointsInString = data.split("\\|");
			for (String pointInString : pointsInString) {
				String[] coordsInString = pointInString.split(" ");
				for (String coordInString : coordsInString) {
					coords.add(Integer.parseInt(coordInString));
				}
			}
			Rectangle recFromFile = new Rectangle(new Rectangle.Point(coords.get(0), coords.get(1)),
					new Rectangle.Point(coords.get(2), coords.get(3)));
			assertEquals(rectangle.toString(), recFromFile.toString());
		}
		file.delete();
	}

	@Test
	public void writeAndRean5Rectangles() throws IOException {
		Rectangle rectangle1 = new Rectangle(new Rectangle.Point(5, 7), new Rectangle.Point(9, 15));
		Rectangle rectangle2 = new Rectangle(new Rectangle.Point(4, 7), new Rectangle.Point(9, 14));
		Rectangle rectangle3 = new Rectangle(new Rectangle.Point(3, 7), new Rectangle.Point(9, 13));
		Rectangle rectangle4 = new Rectangle(new Rectangle.Point(2, 7), new Rectangle.Point(9, 12));
		Rectangle rectangle5 = new Rectangle(new Rectangle.Point(1, 7), new Rectangle.Point(9, 11));
		File file = new File("rectangles");
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(rectangle1.toString() + rectangle2 + rectangle3 + rectangle4 + rectangle5);
		writer.close();
		Scanner myReader = new Scanner(file);
		BufferedReader in = new BufferedReader(new InputStreamReader(new ReverseLineInputStream(file)));
		while(true) {
			String line = in.readLine();
			if (line == null) {
				break;
			}
			List<Integer> coords = new ArrayList<>();
			String[] pointsInString = line.split("\\|");
			for (String pointInString : pointsInString) {
				String[] coordsInString = pointInString.split(" ");
				for (String coordInString : coordsInString) {
					coords.add(Integer.parseInt(coordInString));
				}
			}
			Rectangle recFromFile = new Rectangle(new Rectangle.Point(coords.get(0), coords.get(1)),
					new Rectangle.Point(coords.get(2), coords.get(3)));
			System.out.println(recFromFile);
		}
		file.delete();
	}
}
