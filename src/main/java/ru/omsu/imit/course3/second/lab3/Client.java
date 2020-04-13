package ru.omsu.imit.course3.second.lab3;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {



    public String send(String message) {

            final int serverPort = 6666;
            final String address = "localhost";
            InetAddress ipAddress;
            Gson gson = new Gson();
            try {
                ipAddress = InetAddress.getByName(address);
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return "Connection error";
            }
            try {
                Socket socket = new Socket(ipAddress, serverPort);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF(message);
                out.flush();
                return in.readUTF();
            }
             catch (IOException e) {
                e.printStackTrace();
            }
        return "error";
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
