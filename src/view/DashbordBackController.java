/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Skymil
 */
public class DashbordBackController implements Initializable {

    @FXML
    private AnchorPane PaneContent;
    @FXML
    private ImageView profile;
    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private JFXButton StatAbDa;
    @FXML
    private JFXButton btnListeCommande;
    @FXML
    private JFXButton BtnAB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void StatAboDashboard(ActionEvent event) {
    }

    @FXML
    private void ListeCommandeBack(ActionEvent event) {
    }

    @FXML
    private void Listabonnement(ActionEvent event) {
    }

    @FXML
    private void reclamation(ActionEvent event) {
        pane1.getChildren().clear();
        try {
            pane1.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/FXMLreclamationadmin.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(DashbordBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
