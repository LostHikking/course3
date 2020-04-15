package ru.omsu.imit.course3.first.lab5.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {
	private ThreadLocal<SimpleDateFormat> formatter = new InheritableThreadLocal<>();

	public Formatter() {
		formatter.set(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
	}

	public String format(Date date) {
		return formatter.get().format(date);
	}
}
