/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pidev_javafx.entitie.User;
import pidev_javafx.service.UserService;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class AffichageController implements Initializable {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    UserService us = new UserService();
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private TextField emailtext;
    @FXML
    private TextField passwordtext;
    @FXML
    private TextField nomtext;
    @FXML
    private TextField prenomtext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();

    }   
    
    public void afficher ()
    {
        
        nom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));

        ObservableList<User>listU=FXCollections.observableArrayList();
        listU = us.afficher();
        userTable.setItems(listU);        
    }

    @FXML
    private void supprimerUser(ActionEvent event) {
        
        us.supprimer(userTable.getSelectionModel().getSelectedItem());
        afficher();
    }

    
    private User Update(User t)
    {
        t.setEmail(emailtext.getText());
        t.setNom(nomtext.getText());
        t.setPassword(passwordtext.getText());
        t.setPrenom(prenomtext.getText());
        
        return t;
        
    }
    
    @FXML
    private void modifierUser(ActionEvent event) {
       
       us.modifier(Update(userTable.getSelectionModel().getSelectedItem()));
        afficher();
    }

    @FXML
    private void fill(MouseEvent event) {
        emailtext.setText(userTable.getSelectionModel().getSelectedItem().getEmail());
        passwordtext.setText(userTable.getSelectionModel().getSelectedItem().getPassword());
        nomtext.setText(userTable.getSelectionModel().getSelectedItem().getNom());
        prenomtext.setText(userTable.getSelectionModel().getSelectedItem().getPrenom());
    }
    
}
