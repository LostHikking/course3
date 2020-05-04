package ru.omsu.imit.course3.first.lab3;

import java.util.*;
import java.util.stream.Collectors;

public class MatrixServ {
	public static Integer[][] findSetsUnlike(Integer[][] matrix) {
		Set<Integer> set;
		Map<Integer[], ArrayList<Integer[]>> resultMap = new HashMap<>();
		for (int i = 0; i < matrix.length; i++) {
			resultMap.put(matrix[i], new ArrayList<>());
			resultMap.get(matrix[i]).add(matrix[i]);
			for (int j = i+1; j < matrix.length; j++) {
				set = new HashSet<>(Arrays.asList(matrix[i]));
				set.addAll(new HashSet<>(Arrays.asList(matrix[j])));
				if (set.size() > matrix[i].length) {
					resultMap.get(matrix[i]).add(matrix[j]);
				}
			}
		}
		List<ArrayList<Integer[]>> result = resultMap
				.values().stream()
				.filter(integers -> integers.size() > 1)
				.collect(Collectors.toList());
		if (result.size() == 0)
			return null;
		Random random = new Random();
		return result.get(random.nextInt(result.size())).toArray(new Integer[0][]);
	}
}
