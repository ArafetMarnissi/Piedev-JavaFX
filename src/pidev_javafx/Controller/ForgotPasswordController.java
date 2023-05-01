/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import pidev_javafx.entitie.User;
import pidev_javafx.service.UserService;
import pidev_javafx.tools.JavaMailUser;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField emailfield;
    private Stage stage;
    private  Parent root;
    private Scene scene;
    @FXML
    private TextField codefield;
    @FXML
    private Button recupererButton;
    @FXML
    private Button confirmerButton;
    
    String sentCode;
    @FXML
    private TextField enterPassword1;
    @FXML
    private TextField enterPassword2;
    @FXML
    private Button confirmerButton1;
    @FXML
    private Label labelMailSent;
    @FXML
    private Label changeMDPLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        confirmerButton.setVisible(false);
        codefield.setVisible(false);
        enterPassword1.setVisible(false);
        enterPassword2.setVisible(false);
        confirmerButton1.setVisible(false);
    }   
    
     public static int generateRandomNumber() {
        Random random = new Random();
        int min = 100000; // minimum 6-digit number
        int max = 999999; // maximum 6-digit number
        return random.nextInt(max - min + 1) + min;
    }

    @FXML
    private void resetPassword(ActionEvent event) {
        
        
        UserService us = new UserService();
        
        if (us.getUserParEmail(emailfield.getText())==null)
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("echec");
                 alert.setHeaderText(null);
                 alert.setContentText("Le compte avec l'adresse email " + emailfield.getText() + "n'existe pas" );
                 alert.showAndWait();
        }
        else {
           int key = generateRandomNumber();
            sentCode = String.valueOf(key);
           
           try
        {
          
             JavaMailUser.sendMail(emailfield.getText(),sentCode);
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
           labelMailSent.setText("nous avon envoyé un code à " + emailfield.getText());
            emailfield.setVisible(false);
            confirmerButton.setVisible(true);
            codefield.setVisible(true);
            recupererButton.setVisible(false);
           
            
            
        }
        
    }

    @FXML
    private void confirmCode(ActionEvent event) {
       
        
       
        
        
        if(sentCode.equals(codefield.getText()))
        {
            codefield.setVisible(false);
            confirmerButton.setVisible(false);
            labelMailSent.setVisible(false);
            
            changeMDPLabel.setText("entrer votre nouveau mot de passe : ");
            enterPassword1.setVisible(true);
        enterPassword2.setVisible(true);
        confirmerButton1.setVisible(true);
        
       
            
        
        
            
           
            
        }
        else
        {
           Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("echec");
                 alert.setHeaderText(null);
                 alert.setContentText("Code incorrect" );
                 alert.showAndWait(); 
        }
    }

    @FXML
    private void changerMDP(ActionEvent event) {
         UserService us = new UserService();
        User u = us.getUserParEmail(emailfield.getText());
        if (enterPassword1.getText().equals(enterPassword2.getText()))
        {
         u.setPassword(enterPassword1.getText());
         us.modifier(u);
         
         
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Félicitations");
            alert.setHeaderText(null);
            alert.setContentText("Le mot de passe du compte associé à l'adresse email " + emailfield.getText() + " a été changé avec succes." );
            alert.showAndWait();
            
                        try{                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/Acceuil.fxml"));
                                                        Parent root = loader.load();
                                                        AcceuilController controller = loader.getController();
                                                        try {
                                                            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/login.fxml"));
                                                            Pane autreInterface = loader2.load();
                                                            Region parent = (Region) loader2.getRoot();
                                                            parent.prefWidthProperty().bind(controller.PaneContent.widthProperty());
                                                            parent.prefHeightProperty().bind(controller.PaneContent.heightProperty());
                                                            
                                                            controller.PaneContent.getChildren().setAll(autreInterface);
                                                            
                                                        } catch (IOException ex) {
                                                            ex.printStackTrace();
                                                        }
                                                        enterPassword1.getScene().setRoot(root);
                                                        
                                                        
                                                        
                                                    } catch (IOException ex) {
                                        Logger.getLogger(AffichageActiviteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
        }
        
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("echec");
                 alert.setHeaderText(null);
                 alert.setContentText("Les deux mots de passe ne sont pas identiques !" );
                 alert.showAndWait();  
        }
        
    }
    
}
