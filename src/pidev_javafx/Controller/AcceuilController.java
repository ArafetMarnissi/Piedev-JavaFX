/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author damma
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button gc;
    @FXML
    private Button gp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void affcatonclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/CategoryDisplay.fxml"));
            Parent root;
        try {
            root = loader.load();
            gc.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void affprodonclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/DisplayProduct.fxml"));
            Parent root;
        try {
            root = loader.load();
            gc.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
