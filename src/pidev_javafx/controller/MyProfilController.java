/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pidev_javafx.service.SessionManager;
import pidev_javafx.tools.JavaMail;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class MyProfilController implements Initializable {

    @FXML
    private TextField emailtextfield;
    @FXML
    private TextField nomtextfield;
    @FXML
    private TextField prenomtextfield;
    @FXML
    private TextField mdptextfield;
    @FXML
    private Label checkStatus;
    @FXML
    private Button sendmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emailtextfield.setText(SessionManager.getEmail());
        nomtextfield.setText(SessionManager.getNom());
        prenomtextfield.setText(SessionManager.getPrenom());
        mdptextfield.setText(SessionManager.getPassword());
       if (SessionManager.isStatus())
       {
           checkStatus.setText("vérifié");
       }
       else
       {
           checkStatus.setText("non vérifié");
       }
    }    

    @FXML
    private void modifiercoordonnee(ActionEvent event) {
        
        
    }

    @FXML
    private void sendmail(ActionEvent event) {
        try
        {
             JavaMail.sendMail("floppybird981@gmail.com","salut");
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
       
        
    }
    
}
