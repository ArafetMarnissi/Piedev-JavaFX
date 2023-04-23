/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class SupportController implements Initializable {

    @FXML
    private Button buttonStart;

   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
    @FXML
    private void startChat(ActionEvent event) {
   

    // Start the JavaFX application
   
        
            Stage stage = new Stage();
           
            
            
    ChatClient client = new ChatClient();
    try
    {
      client.start(stage);  
    }
    catch (Exception e)
    {
        System.out.println(e.toString());
    }
    
       
   
    
}

    
}
