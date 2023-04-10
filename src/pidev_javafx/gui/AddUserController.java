/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import pidev_javafx.entitie.User;
import pidev_javafx.service.UserService;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void creerCompte(ActionEvent event) {
        String mail = email.getText();
        String password = mdp.getText();
        String nom = name.getText();
        String prenom  = lastname.getText();

        User t = new User(mail,password, nom, prenom);
        UserService us = new UserService();
        us.ajouter(t);
        FXMLLoader loder = new FXMLLoader(getClass().
                getResource("AfficherPersonne.fxml"));
        
    }
    
}
