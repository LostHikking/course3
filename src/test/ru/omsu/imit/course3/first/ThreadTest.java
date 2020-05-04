package ru.omsu.imit.course3.first;

import org.junit.Test;
import ru.omsu.imit.course3.first.lab5.dataqueue.DataQueue;
import ru.omsu.imit.course3.first.lab5.email.Sender;
import ru.omsu.imit.course3.first.lab5.email.Transport;
import ru.omsu.imit.course3.first.lab5.formatter.Formatter;
import ru.omsu.imit.course3.first.lab5.formatter.FormatterApp;
import ru.omsu.imit.course3.first.lab5.pingpong.cond.PingPong;
import ru.omsu.imit.course3.first.lab5.pingpong.sem.Ping;
import ru.omsu.imit.course3.first.lab5.pingpong.sem.Pong;
import ru.omsu.imit.course3.first.lab5.prodandcons.syncblock.Consumer;
import ru.omsu.imit.course3.first.lab5.prodandcons.syncblock.Producer;
import ru.omsu.imit.course3.first.lab5.prodandcons.syncmethods.ProducerConsumer;
import ru.omsu.imit.course3.first.lab5.readerwriter.Book;
import ru.omsu.imit.course3.first.lab5.readerwriter.Reader;
import ru.omsu.imit.course3.first.lab5.readerwriter.Writer;
import ru.omsu.imit.course3.first.lab5.taskqueueext.Developer;
import ru.omsu.imit.course3.first.lab5.taskqueueext.TaskQueue;
import ru.omsu.imit.course3.first.lab5.taskqueueext.Worker;
import ru.omsu.imit.course3.first.lab5.threethreads.FirtstThread;
import ru.omsu.imit.course3.first.lab5.threethreads.SecondThread;
import ru.omsu.imit.course3.first.lab5.threethreads.ThirdThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {
	@Test
	public void infoThreadTest() {
		System.out.println(Thread.currentThread());
		System.out.println(Thread.currentThread().getId());
		System.out.println(Thread.currentThread().getName());
		System.out.println(Thread.currentThread().getContextClassLoader());
		System.out.println(Thread.currentThread().getPriority());
		System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
		System.out.println(Thread.currentThread().getState());
		System.out.println(Thread.currentThread().getThreadGroup());
	}

	@Test
	public void waitThread() throws InterruptedException {
		Thread thread = new Thread(() -> {
			try {
				System.out.println("Поток запустился");
				System.out.println("Поток спит 500");
				Thread.sleep(500);
				System.out.println("Поток проснулся");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
		System.out.println("Ждём поток");
		thread.join();
	}

	@Test
	public void wait3Threads() throws InterruptedException {
		FirtstThread firtstThread = new FirtstThread();
		SecondThread secondThread = new SecondThread();
		ThirdThread thirdThread = new ThirdThread();
		firtstThread.start();
		secondThread.start();
		thirdThread.start();
		firtstThread.join();
		secondThread.join();
		thirdThread.join();
	}

	@Test
	public void prodAndCons() {
		ArrayList<Integer> integerArrayList = new ArrayList<>();
		Consumer consumer = new Consumer(integerArrayList);
		Producer producer = new Producer(integerArrayList);
		consumer.run();
		producer.run();
	}

	@Test
	public void syncMethodsTest() {
		ArrayList<Integer> integerArrayList = new ArrayList<>();
		ProducerConsumer producer = new ProducerConsumer(integerArrayList, true);
		ProducerConsumer consumer = new ProducerConsumer(integerArrayList, false);
		producer.run();
		consumer.run();
	}

	@Test
	public void syncListTest() {
		List<Integer> syncList = Collections.synchronizedList(new ArrayList<>());
		Consumer consumer = new Consumer(syncList);
		Producer producer = new Producer(syncList);
		consumer.run();
		producer.run();
	}

	@Test
	public void pingPongTest() throws InterruptedException {
		Semaphore sem = new Semaphore(1);
		Semaphore sem1 = new Semaphore(0);
		Thread ping = new Thread(new Ping(sem, sem1));
		Thread pong = new Thread(new Pong(sem, sem1));
		ping.start();
		pong.start();
		ping.join();
		pong.join();
	}

	@Test
	public void readerWriter() throws InterruptedException {
		Book book = new Book();
		Thread writer1 = new Thread(new Writer(book));
		Thread writer2 = new Thread(new Writer(book));
		Thread reader1 = new Thread(new Reader(book));
		Thread reader2 = new Thread(new Reader(book));
		Thread reader3 = new Thread(new Reader(book));
		writer1.start();
		writer2.start();
		reader1.start();
		reader2.start();
		reader3.start();
		writer1.join();
	}

	@Test
	public void reentrantProdAndConsTest() throws InterruptedException {
		ArrayList<Integer> integerArrayList = new ArrayList<>();
		ReentrantLock lock = new ReentrantLock();
		Thread producer = new Thread(new ru.omsu.imit.course3.first.lab5.prodandcons.reentrant.Producer(integerArrayList, lock));
		Thread consumer = new Thread(new ru.omsu.imit.course3.first.lab5.prodandcons.reentrant.Consumer(integerArrayList, lock));
		producer.start();
		consumer.start();
		producer.join();
		consumer.join();
	}

	@Test
	public void pingPongCondTest() throws InterruptedException {
		PingPong pingPong = new PingPong();
		Thread ping = new Thread(new ru.omsu.imit.course3.first.lab5.pingpong.cond.Ping(pingPong));
		Thread pong = new Thread(new ru.omsu.imit.course3.first.lab5.pingpong.cond.Pong(pingPong));
		ping.start();
		pong.start();
		ping.join();
		pong.join();
	}


	@Test
	public void formatterTest() {
		Formatter formatter = new Formatter();
		new Thread(new FormatterApp(formatter)).start();
		new Thread(new FormatterApp(formatter)).start();
		new Thread(new FormatterApp(formatter)).start();
		new Thread(new FormatterApp(formatter)).start();
		new Thread(new FormatterApp(formatter)).start();
	}

	@Test
	public void emailTest() throws IOException, InterruptedException {
		Transport transport = new Transport("temp/email/incoming", "temp/email/log",
				"https://www.youtube.com/watch?v=dQw4w9WgXcQ", "Check this!", "admin@google.com");
		Thread t1 = new Thread(new Sender(transport));
		Thread t2 = new Thread(new Sender(transport));
		Thread t3 = new Thread(new Sender(transport));
		Thread t4 = new Thread(new Sender(transport));
		Thread t5 = new Thread(new Sender(transport));
		Thread t6 = new Thread(new Sender(transport));
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		transport.close();
	}

	@Test
	public void dataQueueTest() throws InterruptedException {
		DataQueue dataQueue = new DataQueue();
		Thread reader = new Thread(new ru.omsu.imit.course3.first.lab5.dataqueue.Reader(dataQueue));
		Thread reader1 = new Thread(new ru.omsu.imit.course3.first.lab5.dataqueue.Reader(dataQueue));
		Thread reader2 = new Thread(new ru.omsu.imit.course3.first.lab5.dataqueue.Reader(dataQueue));
		Thread reader3 = new Thread(new ru.omsu.imit.course3.first.lab5.dataqueue.Reader(dataQueue));
		Thread writer = new Thread(new ru.omsu.imit.course3.first.lab5.dataqueue.Writer(dataQueue));
		Thread writer1 = new Thread(new ru.omsu.imit.course3.first.lab5.dataqueue.Writer(dataQueue));
		Thread writer2 = new Thread(new ru.omsu.imit.course3.first.lab5.dataqueue.Writer(dataQueue));
		Thread writer3 = new Thread(new ru.omsu.imit.course3.first.lab5.dataqueue.Writer(dataQueue));
		reader.start();
		reader1.start();
		reader2.start();
		reader3.start();
		writer1.start();
		writer2.start();
		writer3.start();
		writer.start();
		reader.join();
	}

	@Test
	public void taskQueueTest() throws InterruptedException {
		ru.omsu.imit.course3.first.lab5.taskqueue.TaskQueue taskQueue = new ru.omsu.imit.course3.first.lab5.taskqueue.TaskQueue();
		Thread developer = new Thread(new ru.omsu.imit.course3.first.lab5.taskqueue.Developer(taskQueue, 3), "Dev1");
		Thread developer1 = new Thread(new ru.omsu.imit.course3.first.lab5.taskqueue.Developer(taskQueue, 51), "Dev2");
		Thread developer2 = new Thread(new ru.omsu.imit.course3.first.lab5.taskqueue.Developer(taskQueue, 1), "Dev3");
		Thread developer3 = new Thread(new ru.omsu.imit.course3.first.lab5.taskqueue.Developer(taskQueue, 1), "Dev4");
		Thread worker1 = new Thread(new ru.omsu.imit.course3.first.lab5.taskqueue.Worker(taskQueue), "Worker1");
		Thread worker2 = new Thread(new ru.omsu.imit.course3.first.lab5.taskqueue.Worker(taskQueue), "Worker2");
		Thread worker3 = new Thread(new ru.omsu.imit.course3.first.lab5.taskqueue.Worker(taskQueue), "Worker3");
		Thread worker4 = new Thread(new ru.omsu.imit.course3.first.lab5.taskqueue.Worker(taskQueue), "Worker4");
		developer.start();
		developer1.start();
		worker1.start();
		worker2.start();
		worker3.start();
		worker4.start();
		developer2.start();
		developer3.start();
		worker1.join();
		worker2.join();
	}

	@Test
	public void taskQueueExtTest() throws InterruptedException {
		TaskQueue taskQueue = new TaskQueue();
		Thread developer = new Thread(new Developer(taskQueue, 3), "Dev1");
		Thread developer1 = new Thread(new Developer(taskQueue, 51), "Dev2");
		Thread developer2 = new Thread(new Developer(taskQueue, 1), "Dev3");
		Thread developer3 = new Thread(new Developer(taskQueue, 1), "Dev4");
		Thread worker1 = new Thread(new Worker(taskQueue), "Worker1");
		Thread worker2 = new Thread(new Worker(taskQueue), "Worker2");
		Thread worker3 = new Thread(new Worker(taskQueue), "Worker3");
		Thread worker4 = new Thread(new Worker(taskQueue), "Worker4");
		developer.start();
		developer1.start();
		worker1.start();
		worker2.start();
		worker3.start();
		worker4.start();
		developer2.start();
		developer3.start();
		worker1.join();
		worker2.join();
	}
}
