package ru.omsu.imit.course3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientServiceThread extends Thread {
    private Socket clientSocket;
    private int clientID;
    private static String addCommand = "add";
    private static String getCommand = "get";
    public ClientServiceThread(Socket socket, int id) {
        clientSocket = socket;
        clientID = id;
    }

    public void run() {
        System.out.println(
                "Accepted Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());
        try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            while (true) {
                String answer = "Error";
                String clientCommand = in.readUTF();
                System.out.println("Client "  + clientID + " says :" + clientCommand);
                String partCommand[] = clientCommand.split("\\s+");
                if (addCommand.equalsIgnoreCase(partCommand[0]))
                {
                    Server server = new Server();
                    if (server.add(partCommand[1]))
                        answer = "Added";
                    else answer = "Adding error";
                }
                if (getCommand.equalsIgnoreCase(partCommand[0])){
                    Server server = new Server();
                    answer = server.get(partCommand[1]);
                    if (answer == null)
                        answer = "Getting error";
                }
                if (clientCommand.equalsIgnoreCase("quit")) {
                    System.out.println("Stopping client thread for client : " + clientID);
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
            System.out.println("Closing socket");
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}