/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pidev_javafx.entitie.User;
import pidev_javafx.gui.AddUserController;
import pidev_javafx.service.UserService;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class LoginController implements Initializable {

    @FXML
    private TextField LoginEmail;
    @FXML
    private TextField LoginPassword;
    @FXML
    private Button BtnLogin;
    @FXML
    private Label emailverif;
    @FXML
    private Label mdpverif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //TO DO

    }    

    private void verifier(InputMethodEvent event) {
        
       
    }


    private void seconnecter(ActionEvent event) {
       
        /*  boolean test= us.login(email, password);
        if (test)
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Félicitations");
        alert.setHeaderText(null);
        alert.setContentText("Vous serez redirigé vers votre page d'acceuil." );
        alert.showAndWait();
        }
        else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Echec");
        alert.setHeaderText(null);
        alert.setContentText("Le mot de passe ou l'email fourni est incorrect" );
        alert.showAndWait();
        } */
    }

    @FXML
    private void loginUser(ActionEvent event) {
        
        String email = LoginEmail.getText();
        String password = LoginPassword.getText();
        UserService us = new UserService();
         boolean test= us.login(email, password);
        if (test)
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Félicitations");
        alert.setHeaderText(null);
        alert.setContentText("Vous serez redirigé vers votre page d'acceuil." );
        alert.showAndWait();
        }
        else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Echec");
        alert.setHeaderText(null);
        alert.setContentText("Le mot de passe ou l'email fourni est incorrect" );
        alert.showAndWait();
        }
        
    }

    @FXML
    private void clicksignup(ActionEvent event) {
        
    }

    @FXML
    private void verifieremail(KeyEvent event) {
        emailverif.setText("");
         if (!LoginEmail.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{1,}"))
        {
            emailverif.setText("email invalide");
            
        }
         
        
        
    }

    @FXML
    private void verifiermdp(KeyEvent event) {
                mdpverif.setText("");

         if(LoginPassword.getText().length()<8)
         {
             mdpverif.setText("mdp doit être > 8");
         }
    }
    
}
