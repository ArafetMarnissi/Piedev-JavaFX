/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import pidev_javafx.service.SessionManager;
import pidev_javafx.service.UserService;
import java.net.HttpURLConnection;
import javafx.scene.control.PasswordField;



/**
 * FXML Controller class
 *
 * @author khali
 */
public class LoginController implements Initializable {

    @FXML
    private TextField LoginEmail;

    @FXML
    private Button BtnLogin;
    @FXML
    private Label emailverif;
    @FXML
    private Label mdpverif;
    private Stage stage;
    private  Parent root;
    private Scene scene;
    @FXML
    private Button createaccount;
    @FXML
    private PasswordField LoginPassword;

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
            //verified
             if (SessionManager.getRole().equals("[\"ROLE_CLIENT\"]") && SessionManager.isStatus()==true)
             {
                 
          try {
            root = FXMLLoader.load(getClass().getResource("/pidev_javafx/gui/DashbordFront.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
             }
             //not verified
             else if (SessionManager.getRole().equals("[\"ROLE_CLIENT\"]") && SessionManager.isStatus()==false)
             {
                 try {
            root = FXMLLoader.load(getClass().getResource("/pidev_javafx/gui/verifyAccount.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
             }
             else if (SessionManager.getRole().equals("[\"ROLE_ADMIN\"]"))
             {
                   try {
            root = FXMLLoader.load(getClass().getResource("/pidev_javafx/gui/DashbordBack.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
             }
             
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
         try {
            root = FXMLLoader.load(getClass().getResource("/pidev_javafx/gui/addUser.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void verifieremail(KeyEvent event) {
        emailverif.setText("");
         if (!LoginEmail.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{1,}"))
        {
            emailverif.setText("email invalide");
            
        }
         
        
        
    }

    private void verifiermdp(KeyEvent event) {
                mdpverif.setText("");

         if(LoginPassword.getText().length()<8)
         {
             mdpverif.setText("mdp doit être > 8");
         }
    }

   

}

