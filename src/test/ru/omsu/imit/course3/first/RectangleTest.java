package ru.omsu.imit.course3.first;

import org.junit.Test;
import ru.omsu.imit.course3.first.lab1.Rectangle;
import ru.omsu.imit.course3.first.lab1.ReverseLineInputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RectangleTest {
	/* TASK 1.8 */
	@Test
	public void createRectangle() throws IOException {
		Rectangle rectangle = new Rectangle(new Rectangle.Point(5, 7), new Rectangle.Point(9, 15));

		File file = new File("rectangle");
		assertTrue(file.createNewFile());
		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream oos = new DataOutputStream(fos);
		oos.writeInt(rectangle.getLeftTop().getX());
		oos.writeInt(rectangle.getLeftTop().getY());
		oos.writeInt(rectangle.getRightBottom().getX());
		oos.writeInt(rectangle.getRightBottom().getY());
		oos.close();
		DataInputStream ois = new DataInputStream(new FileInputStream(file));
		Integer[] ints = new Integer[4];
		for (int i = 0; i < 4; i++) {
			ints[i] = ois.readInt();
		}
		ois.close();
		Rectangle rectangleFromFile = new Rectangle(new Rectangle.Point(ints[0], ints[1]), new Rectangle.Point(ints[2], ints[3]));
		assertEquals(rectangle, rectangleFromFile);
		assertTrue(file.delete());
	}

	@Test
	public void writeAndRead5Rectangles() throws IOException {
		List<Rectangle> rectangles = new ArrayList<>();
		rectangles.add(new Rectangle(new Rectangle.Point(5, 7), new Rectangle.Point(9, 15)));
		rectangles.add(new Rectangle(new Rectangle.Point(4, 7), new Rectangle.Point(9, 14)));
		rectangles.add(new Rectangle(new Rectangle.Point(3, 7), new Rectangle.Point(9, 13)));
		rectangles.add(new Rectangle(new Rectangle.Point(2, 7), new Rectangle.Point(9, 12)));
		rectangles.add(new Rectangle(new Rectangle.Point(1, 7), new Rectangle.Point(9, 11)));
		File file = new File("rectangles");
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream dos = new DataOutputStream(fos);
		rectangles.forEach((rectangle) ->
				{
					try {
						dos.writeInt(rectangle.getLeftTop().getX());
						dos.writeInt(rectangle.getLeftTop().getY());
						dos.writeInt(rectangle.getRightBottom().getX());
						dos.writeInt(rectangle.getRightBottom().getY());
						dos.writeChar('\n');
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		);
		dos.close();
		DataInputStream dis = new DataInputStream(new ReverseLineInputStream(file));
		boolean check = true;
		List<Integer> integers = new ArrayList<>();
		int i = 0;
		while (check) {
			try {
				integers.add(dis.readInt());
				i++;
				if (i == Rectangle.class.getDeclaredFields().length* Rectangle.Point.class.getDeclaredFields().length) {
					System.out.println(new Rectangle(new Rectangle.Point(integers.get(0), integers.get(1)), new Rectangle.Point(integers.get(2), integers.get(3))));
					dis.readChar();
					i = 0;
					integers = new ArrayList<>();
				}
			} catch (EOFException eofException) {
				check = false;
			}
		}
		dis.close();
		file.delete();
	}
}
