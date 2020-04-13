package ru.omsu.imit.course3.first;

import org.junit.Test;
import ru.omsu.imit.course3.first.lab2.Renamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class RenamerTest {
	@Test
	public void renamerTest(){
		Path pathOnDatFiles = new File("files/directory").toPath();
		try {
			Renamer.renameDatToBin(pathOnDatFiles);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
