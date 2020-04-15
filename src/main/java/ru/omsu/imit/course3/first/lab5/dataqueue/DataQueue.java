package ru.omsu.imit.course3.first.lab5.dataqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class DataQueue {
	private BlockingQueue<Data> data;
	private AtomicInteger dataRemains;

	public DataQueue() {
		data = new LinkedBlockingQueue<>();
		dataRemains = new AtomicInteger(0);
	}

	public void put(Data d) {
		dataRemains.addAndGet(1);
		data.offer(d);
	}

	public Data get() throws InterruptedException {
		dataRemains.decrementAndGet();
		return data.take();
	}

	public boolean isEmpty() {
		return dataRemains.get() == 0;
	}
}
