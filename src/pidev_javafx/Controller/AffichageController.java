/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import pidev_javafx.entitie.User;
import pidev_javafx.service.SessionManager;
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
    @FXML
    private Label verifemail;
    @FXML
    private Label verifmdp;
    @FXML
    private Label verifnom;
    @FXML
    private Label verifprenom;
    private Stage stage;
    private  Parent root;
    private Scene scene;
    private Label connectedUsername;
    

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

        ObservableList<User>listU=FXCollections.observableArrayList();
        listU = us.afficher();
        userTable.setItems(listU); 
        
        
    }

    @FXML
    private void supprimerUser(ActionEvent event) {
        
        us.supprimer(userTable.getSelectionModel().getSelectedItem());
        afficher();
        emailtext.setText("");
        passwordtext.setText("");
        nomtext.setText("");
        prenomtext.setText("");
        
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

    @FXML
    private void checkemail(KeyEvent event) {
       /* verifemail.setText("");
        
        if (!email.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{1,}"))
        {
            verifemail.setText("L'email n'est pas valide.");
        }*/
    }

    private void changeUI(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/pidev_javafx/gui/login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
    
}

    @FXML
    private void sedeconnecter(MouseEvent event) {
          try {
            root = FXMLLoader.load(getClass().getResource("/pidev_javafx/gui/login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
    }
}
