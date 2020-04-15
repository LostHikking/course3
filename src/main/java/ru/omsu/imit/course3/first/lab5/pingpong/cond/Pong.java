package ru.omsu.imit.course3.first.lab5.pingpong.cond;

public class Pong implements Runnable {
	private PingPong pingPong;

	public Pong(PingPong pingPong) {
		this.pingPong = pingPong;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 30; i++) {
				pingPong.pong();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
