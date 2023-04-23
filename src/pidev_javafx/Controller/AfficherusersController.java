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
import pidev_javafx.entitie.Category;
import pidev_javafx.entitie.User;
import pidev_javafx.service.UserService;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class AfficherusersController implements Initializable {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private Button btnSupprimer;
    UserService us = new UserService();
    @FXML
    private TableColumn<User, String> password;
    private TextField TextFieldEmail;
    private TextField TextFieldPassord;
    private TextField TextFieldNom;
    private TextField TextFieldPrenom;

    
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        
   } 
    public void afficher()
    {
    TextFieldEmail.setText(userTable.getSelectionModel().getSelectedItem().getEmail());
    TextFieldPassord.setText(userTable.getSelectionModel().getSelectedItem().getPassword());
    TextFieldNom.setText(userTable.getSelectionModel().getSelectedItem().getNom());
    TextFieldPrenom.setText(userTable.getSelectionModel().getSelectedItem().getPrenom());
    
    
    
         nom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));

        ObservableList<User>listU=FXCollections.observableArrayList();
        listU = us.afficher();
        userTable.setItems(listU);
    }

    @FXML
    private void supprimerUser(ActionEvent event) {
        us.supprimer(userTable.getSelectionModel().getSelectedItem());
        afficher();
    }

    
    
    private void ModifierUser(ActionEvent event) {
        us.modifier(userTable.getSelectionModel().getSelectedItem());
        



    }

    @FXML
    private void fill(MouseEvent event) {
    }
    
}
