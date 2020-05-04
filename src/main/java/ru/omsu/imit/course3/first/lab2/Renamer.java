package ru.omsu.imit.course3.first.lab2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Renamer {
	public static Path renameDatToBin(Path dir) throws IOException {
		if (!Files.isDirectory(dir)) {
			throw new RuntimeException("Your path is not a directory");
		}
		List<Path> pathList = Files.list(dir)
				.filter(x -> (x.toString().lastIndexOf(".dat") == x.toString().length() - 4))
				.collect(Collectors.toList());
		StringBuilder name = new StringBuilder();
		for (Path path : pathList) {
			name.append(path.toString());
			name.replace(name.length() - 4, name.length(), ".bin");
			Files.move(path, Paths.get(name.toString()));
			name.delete(0, name.length());
		}
		return dir;
	}
}
