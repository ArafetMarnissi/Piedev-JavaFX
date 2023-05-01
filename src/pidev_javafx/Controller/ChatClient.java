package pidev_javafx.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import static javafx.application.Application.launch;
import pidev_javafx.service.SessionManager;

public class ChatClient extends Application {

    private TextArea chatLog = new TextArea();
    private TextField messageInput = new TextField();
    private Button sendButton = new Button("Send");
    private PrintWriter out;
    private BufferedReader in;
    String nom;


    

    @Override
    public void start(Stage primaryStage) throws Exception {
        nom = SessionManager.getNom();
        BorderPane root = new BorderPane();
        root.setCenter(chatLog);
        root.setBottom(messageInput);
        root.setRight(sendButton);
        Scene scene = new Scene(root, 478.4, 181.6);
        primaryStage.setScene(scene);
        primaryStage.show();
        setUpNetworking();
        sendButton.setOnAction(event -> {
            out.println(messageInput.getText());
            
            messageInput.setText(SessionManager.getNom());
            if(SessionManager.getRole().equals("[\"ROLE_ADMIN\"]"))
            {
                messageInput.setText("admin : ");
            }
            else 
            {
                messageInput.setText("client : "); 
            }
            
            
        });
    }

    void setUpNetworking() {
        try {
            Socket socket = new Socket("localhost", 1234);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Thread readerThread = new Thread(new IncomingReader());
            readerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class IncomingReader implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                String message = in.readLine();
                if (message == null) {
                    break;
                }
                String finalMessage = message; // create a new effectively final variable
                Platform.runLater(() -> chatLog.appendText(finalMessage + "\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

}