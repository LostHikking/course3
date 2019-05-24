package ru.omsu.imit.course3.lab3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientServiceThread extends Thread {
    private Server server;
    private Socket clientSocket;
    private int clientID;
    private static String addCommand = "add";
    private static String getCommand = "get";

    public ClientServiceThread(Socket socket, int id, Server server) {
        clientSocket = socket;
        clientID = id;
        this.server = server;
    }

    public void run() {
        try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            while (true) {
                String answer = "Error";
                String clientCommand = in.readUTF();
                String[] partCommand = clientCommand.split("\\s+");
                if (addCommand.equalsIgnoreCase(partCommand[0]))
                {
                    if (server.add(partCommand[1]))
                        answer = "Added";
                    else answer = "Adding error";
                }
                if (getCommand.equalsIgnoreCase(partCommand[0])){
                    answer = server.get(partCommand[1]);
                    if (answer == null)
                        answer = "Getting error";
                }
                if (clientCommand.equalsIgnoreCase("quit")) {
                    break;
                } else {
                    out.writeUTF(answer);
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}