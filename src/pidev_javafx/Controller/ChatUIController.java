/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class ChatUIController implements Initializable {

    @FXML
    private TextField messageInput;
    @FXML
    private Button sendButton;
    @FXML
    private TextArea chatLog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Thread clientThread = new Thread(() -> {
        try {
            ChatClient client = new ChatClient();
            client.setUpNetworking();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
    clientThread.start();
        
        
        
    }    
    
}
