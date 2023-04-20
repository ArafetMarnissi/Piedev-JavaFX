/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pidev_javafx.service.SessionManager;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class VerifyAccountController implements Initializable {

    @FXML
    private Label sentEmail;
    @FXML
    private TextField textfieldcode;
    private Stage stage;
    private  Parent root;
    private Scene scene;
    @FXML
    private Button button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sentEmail.setText(SessionManager.getEmail());
    }    

    @FXML
    private void verifierCompte(ActionEvent event) {
    
    }

    @FXML
    private void verifyaccountmethod(ActionEvent event) {
            String key = String.valueOf(SessionManager.getPrivate_key());
        if (textfieldcode.getText().equals(key))
        {
            SessionManager.setStatus(true);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Félicitations");
            alert.setHeaderText(null);
            alert.setContentText("Le compte avec l'adresse email " + SessionManager.getEmail() + " a été vérifié avec succes." );
            alert.showAndWait();
            
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
        else
        {
          
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("echec");
                 alert.setHeaderText(null);
                 alert.setContentText("Le code que vous avez entré est incorrect." );
                 alert.showAndWait();
                 return;
                      
        }
    }
    
}
