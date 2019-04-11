package ru.omsu.imit.course3;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
    private Socket socket;

    public static void main(String[] args) {
        final String PORT = "6666";
        final String IP = "localhost";
        InetAddress serverAddress = null;
        connectAndRead(IP, PORT);
        Gson gson = new Gson();
        Server server = new Server();
        Person person1 = new Person("u", "Kek", 27);
        String request = gson.toJson(person1);
        String resultJson = server.get(request);
        Person personResult = gson.fromJson(resultJson, Person.class);
        System.out.println(personResult);
        server.add(request);
        resultJson = server.get(request);
        personResult = gson.fromJson(resultJson, Person.class);
        System.out.println(personResult);
        server.printAll();
    }

    public static void connectAndRead(final String IP, final String PORT) {
        final Gson gson = new Gson();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final int INTPORT = Integer.parseInt(PORT);
                InetAddress serverAddress = null;
                try {
                    serverAddress = InetAddress.getByName(IP);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                final Socket socket;
                try {
                    socket = new Socket(serverAddress, INTPORT);
                } catch (IOException e) {
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final BufferedReader bufferedInputStream;
                        try {
                            bufferedInputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        } catch (IOException e) {
                            throw new RuntimeException();
                        }

                        while (socket.isConnected()) try {
                            String s = bufferedInputStream.readLine();

                            if (!(s == null || s.isEmpty())) {
                                try {
                                    Person person1 = gson.fromJson(s, Person.class);

                                } catch (JsonParseException e) {
                                    throw new RuntimeException();
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }).start();
    }
}
