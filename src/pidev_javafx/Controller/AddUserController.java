/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
public class AddUserController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField mdp;
    @FXML
    private TextField name;
    @FXML
    private TextField lastname;
    @FXML
    private Label passwordControl;
    @FXML
    private Label emailControl;
    @FXML
    private Label nomControl;
    @FXML
    private Label prenomControl;
    @FXML
    private TextField confirmPassword;
    @FXML
    private Label confirmControl;
    private Stage stage;
    private  Parent root;
    private Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void creerCompte(ActionEvent event) {
      
       emailControl.setText("");
       confirmControl.setText("");
       passwordControl.setText("");
       nomControl.setText("");
       prenomControl.setText("");
       if (mdp.getText().length()<8)
       {
           passwordControl.setText("min : 8 caractères");
       }
       if(!email.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
       {
           emailControl.setText("l'adresse email n'est pas valide!");
       }
       if(!name.getText().matches("[a-zA-Z]+"))
       {
           nomControl.setText("le nom doit être alphabétique");
       }
       if(!lastname.getText().matches("[a-zA-Z]+"))
       {
           prenomControl.setText("le pénom doit être alphabétique");
       }
       if (email.getText().isEmpty())
       {
           emailControl.setText("l'email ne doit pas être vide!");
       }
        if (mdp.getText().isEmpty())
       {
           passwordControl.setText("le mot de passe ne doit pas être vide!");
       }
       if (name.getText().isEmpty())
       {
           nomControl.setText("le nom ne doit pas être vide!");
       }
       if (lastname.getText().isEmpty())
       {
           prenomControl.setText("le prenom ne doit pas être vide!");
       }
       if (confirmPassword.getText().isEmpty())
       {
           confirmControl.setText("ce champ  ne doit pas être vide!");
       }
       if(!confirmPassword.getText().equals(mdp.getText()))
       {
           confirmControl.setText("les mots de passe doivent être identique");
            passwordControl.setText("les mots de passe doivent être identique");
       }
       else if (!email.getText().isEmpty() && !mdp.getText().isEmpty() && !name.getText().isEmpty() && !lastname.getText().isEmpty() && confirmPassword.getText().equals(mdp.getText()) && mdp.getText().length()>=8 && email.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
      {
         
        String mail = email.getText();
        String password = mdp.getText();
        String nom = name.getText();
        String prenom  = lastname.getText();

        User t = new User(mail,password, nom, prenom);
        UserService us = new UserService();
            if (us.userExists(t))
            {
                emailControl.setText(email.getText()+" existe déjà.");
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("echec");
                 alert.setHeaderText(null);
                 alert.setContentText("Le compte avec l'adresse email " + email.getText() + "existe déjà" );
                 alert.showAndWait();
            return;
            }
            us.ajouter(t);
            try
        {
           String key =  String.valueOf(t.getPrivate_key());
             JavaMailUser.sendMail(t.getEmail(),key);
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Félicitations");
            alert.setHeaderText(null);
            alert.setContentText("Le compte avec l'adresse email " + email.getText() + " a été créé avec succes." );
            alert.showAndWait();
       
        
       
               
        FXMLLoader loder = new FXMLLoader(getClass().
                getResource("AfficherPersonne.fxml"));

        
        
      }
    
    }

    @FXML
    private void rediriger(ActionEvent event) {
                                            try{
                                         FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/Acceuil.fxml"));
                                        Parent root = loader.load();
                                       AcceuilController controller = loader.getController();
                     
                                                        try {
                                                            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/Login.fxml"));
                                                            Pane autreInterface = loader2.load();
                                                            Region parent = (Region) loader2.getRoot();
                                                            parent.prefWidthProperty().bind(controller.PaneContent.widthProperty());
                                                            parent.prefHeightProperty().bind(controller.PaneContent.heightProperty());
                                                            
                                                            controller.PaneContent.getChildren().setAll(autreInterface);
                                                            
                                                        } catch (IOException ex) {
                                                            ex.printStackTrace();
                                                        }
                                                        emailControl.getScene().setRoot(root);
                                                        
                                                        
                                                        
                                                    } catch (IOException ex) {
                                        Logger.getLogger(AffichageActiviteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
    }
    
}
