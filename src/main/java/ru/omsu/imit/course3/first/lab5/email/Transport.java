package ru.omsu.imit.course3.first.lab5.email;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Transport {

	private FileWriter fileWriter;

	private List<Message> messageList;
	private int i;

	public Transport(String mailListFilePath, String logFilePath, String messageText, String subject, String sender) throws IOException {
		fileWriter = new FileWriter(logFilePath);
		messageList = new ArrayList<>();
		BufferedReader b = new BufferedReader(new FileReader(new File(mailListFilePath)));
		String readLine = "";
		while ((readLine = b.readLine()) != null) {
			messageList.add(new Message(readLine, sender, subject, messageText));
		}
		b.close();
	}

	public void send(Message message) throws IOException, InterruptedException {
		if (message != null) {
			fileWriter.write(message.toString() + " " + Thread.currentThread().getName() + "\n");
			fileWriter.flush();
			Thread.sleep(50);
		}
	}

	public void close() throws IOException {
		fileWriter.close();
	}

	public boolean hasMessage() {
		return i < messageList.size();
	}

	public Message next() {
		if (hasMessage()) {
			Message m = messageList.get(i);
			i++;
			return m;
		}
		return null;
	}

}
