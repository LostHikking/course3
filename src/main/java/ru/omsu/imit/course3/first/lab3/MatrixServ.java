package ru.omsu.imit.course3.first.lab3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MatrixServ {
	public static Integer[][] findSetsUnlike(Integer[][] matrix) {
		Set<Integer> set;
		ArrayList<Integer[]> result = new ArrayList<>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i+1; j < matrix.length; j++) {
				set = new HashSet<>(Arrays.asList(matrix[i]));
				set.addAll(new HashSet<>(Arrays.asList(matrix[j])));
				if (set.size() > matrix[i].length) {
					result.add(matrix[i]);
					result.add(matrix[j]);
				}
			}
		}
		return result.toArray(new Integer[0][]);
	}
}
