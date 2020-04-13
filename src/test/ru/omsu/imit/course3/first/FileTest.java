package ru.omsu.imit.course3.first;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

public class FileTest {
	@Test
	public void testCreateDeleteRename() {
		File file = new File("Test"),
				nestedFile = new File(file, "file"),
				renamed = new File(file, "renamedFile");
		assertFalse(file.exists());
		assertFalse(nestedFile.exists());
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(file.exists());
		assertFalse(file.isDirectory());
		file.delete();
		assertFalse(file.exists());
		try {
			file.mkdir();
			nestedFile.createNewFile();
			assertTrue(nestedFile.exists());
			assertEquals("file", nestedFile.getName());
			nestedFile.renameTo(renamed);
			File temp = file.listFiles()[0];
			assertEquals(renamed.getName(), temp.getName());
			nestedFile.delete();
			renamed.delete();
			assertFalse(nestedFile.exists());
			file.delete();
			assertFalse(file.exists());
		} catch (IOException exc) {
			exc.printStackTrace();
			fail();
		}
	}

	@Test
	public void testFullName() {
		File dir = new File("src"),
				file = new File(dir, "check");
		assertTrue(dir.exists());
		assertTrue(dir.isDirectory());
		try {
			file.createNewFile();
		} catch (IOException exc) {
			exc.printStackTrace();
			fail();
		}
		assertEquals(dir.getPath() + "\\" + file.getName(), file.getPath());
		file.delete();
	}

	@Test
	public void testExists() {
		File folder1 = new File("src");
		File folder2 = new File("source");
		File file1 = new File("./pom.xml");
		assertTrue(folder1.exists());
		if (folder1.exists()) {
			assertTrue(folder1.isDirectory());
		}
		assertFalse(folder2.exists());
		if (folder2.exists()) {
			assertTrue(folder2.isDirectory());
		}
		assertTrue(file1.exists());
		if (file1.exists()) {
			assertTrue(file1.isFile());
		}
	}

	@Test
	public void testList() {
		File sqlDir = new File("sql");
		File[] arr = sqlDir.listFiles();
		File[] arr1 = sqlDir.listFiles((dir, name) -> name.contains(".sql"));
		assertEquals(4, arr.length);
		assertEquals(3, arr1.length);

	}
}
