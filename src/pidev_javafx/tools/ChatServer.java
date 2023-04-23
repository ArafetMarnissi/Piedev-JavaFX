package pidev_javafx.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    private static final int PORT = 1234;

    private List<PrintWriter> clients = new ArrayList<>();

    public static void main(String[] args) {
        new ChatServer().start();
    }

    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                
                System.out.println("New Client connected");
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                clients.add(out);
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void broadcast(String message) {
        for (PrintWriter client : clients) {
            client.println(message);
        }
    }

    private class ClientHandler implements Runnable {

        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("Received message: " + line);
                    broadcast(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Client disconnected");
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clients.removeIf(out -> out == null || out.checkError());
            }
        }
    }
}
