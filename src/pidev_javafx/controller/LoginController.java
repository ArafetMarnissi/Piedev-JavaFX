/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.io.IOException;
import java.net.PasswordAuthentication;
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
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    private Stage stage;
    private  Parent root;
    private Scene scene;
    @FXML
    private Button createaccount;

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
        
          try {
            root = FXMLLoader.load(getClass().getResource("/pidev_javafx/gui/affichage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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

    @FXML
    private void verifiermdp(KeyEvent event) {
                mdpverif.setText("");

         if(LoginPassword.getText().length()<8)
         {
             mdpverif.setText("mdp doit être > 8");
         }
    }

    @FXML
    private void sendEmail(ActionEvent event) {
        String to = "recipient@example.com";
      String from = "sender@example.com";
      String host = "localhost";
      Properties properties = System.getProperties();
      properties.setProperty("mail.smtp.host", host);
      Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
          
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("username", "password");
         }
      });
      try {
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
         message.setSubject("Subject of the email");
         message.setText("This is the message body");
         Transport.send(message);
         System.out.println("Email sent successfully");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
    
    
}
