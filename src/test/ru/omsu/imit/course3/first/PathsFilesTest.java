package ru.omsu.imit.course3.first;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class PathsFilesTest {
	@Before
	public void init() throws IOException {
		Path path = Paths.get("files/path");
		Path another = Paths.get("files/path.dat");
		Files.deleteIfExists(path);
		Files.deleteIfExists(another);
	}

	@Test
	public void testCreate() throws IOException {
		Path path = Paths.get("files/path");
		Path another = Paths.get("files/path.dat");
		Files.createFile(path);
		Files.createDirectory(another);
		assertTrue(Files.exists(path));
		assertTrue(Files.exists(another));
		assertTrue(Files.isRegularFile(path));
		assertTrue(Files.isDirectory(another));
	}

	@Test
	public void testMoveFile() throws IOException {
		Path path = Paths.get("files/path");
		Path another = Paths.get("files/path.dat");
		Files.createFile(path);
		Files.move(path, another);
		assertFalse(Files.exists(path));
		assertTrue(Files.exists(another));
	}

	@Test
	public void testDeleteFile() throws IOException {
		Path another = Paths.get("files/path.dat");
		Files.deleteIfExists(another);
		assertFalse(Files.exists(another));
	}

	@Test(expected = NoSuchFileException.class)
	public void testDeleteException() throws IOException {
		Path another = Paths.get("files/path.dat");
		Files.delete(another);
	}

	@Test
	public void testSize() throws IOException {
		Path path = Paths.get("files/file");
		assertEquals(9, Files.size(path));
	}

	@Test
	public void testList() throws IOException {
		Path folder = Paths.get("files");
		assertTrue(Files.exists(folder));
		assertEquals(2, Files.list(folder).toArray().length);
	}

	@Test
	public void testStartsEndsWith() {
		Path path = Paths.get("files/file");
		Path folder = Paths.get("files");
		Path sized = Paths.get("file");
		assertTrue(path.startsWith(folder));
		assertTrue(path.endsWith(sized));
	}

	@Test
	public void testFileName() {
		Path path = Paths.get("files/file");
		Path sized = Paths.get("file");
		assertEquals(path.getFileName(), sized);
	}

	@Test
	public void testParent() {
		Path path = Paths.get("files/file");
		Path folder = Paths.get("files");
		assertEquals(folder, path.getParent());
	}

	@Test
	public void testNameCount() {
		Path path = Paths.get("files/file");
		assertEquals(2, path.getNameCount());
	}
}
