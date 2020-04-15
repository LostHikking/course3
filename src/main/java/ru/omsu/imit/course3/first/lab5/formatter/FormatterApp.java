package ru.omsu.imit.course3.first.lab5.formatter;

import java.util.Date;

public class FormatterApp implements Runnable {
	private Formatter formatter;


	public FormatterApp(Formatter formatter) {
		this.formatter = formatter;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(formatter.format(new Date()));
		}
	}
}
