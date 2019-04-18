package ru.omsu.imit.course3;

import com.google.gson.Gson;
import java.net.*;
import java.io.*;


public class Client {
    public void run() {
        new Thread(()-> {
            final int serverPort = 6666;
            final String address = "localhost";
            InetAddress ipAddress;
            Gson gson = new Gson();
            try {
                ipAddress = InetAddress.getByName(address);
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return;
            }
            try (Socket socket = new Socket(ipAddress, serverPort);
                 DataInputStream in = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                 BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in, "CP866"))) {

                System.out.println(socket.getLocalPort());
                String line = null;
                System.out.println("Type in something and press enter");

                while (true) {
                    line = keyboardReader.readLine();
                    System.out.println("Sending this line to the server: " + line);
                    out.writeUTF(line);
                    out.flush();
                    if (line.equalsIgnoreCase("quit")) {
                        System.out.println("Client stopped");
                        break;
                    }
                    line = in.readUTF();
                    System.out.println("Server answer is: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

//        Server server = new Server();
//        Person person1 = new Person("u", "Kek", 27);
//        String request = gson.toJson(person1);
//        String resultJson = server.get(request);
//        Person personResult = gson.fromJson(resultJson, Person.class);
//        System.out.println(personResult);
//        server.add(request);
//        resultJson = server.get(request);
//        personResult = gson.fromJson(resultJson, Person.class);
//        System.out.println(personResult);
//        server.printAll();
    }

}
