package ru.omsu.imit.course3.first.lab5.email;

import java.io.IOException;

public class Sender implements Runnable {
	private Transport transport;

	public Sender(Transport transport) {
		this.transport = transport;
	}

	@Override
	public void run() {
		while (transport.hasMessage()) {
			try {
				transport.send(transport.next());
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
