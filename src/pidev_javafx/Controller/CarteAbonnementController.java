/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pidev_javafx.entitie.Abonnement;

/**
 * FXML Controller class
 *
 * @author saifz
 */
public class CarteAbonnementController implements Initializable {

    @FXML
    private Label NomAB;
    @FXML
    private Label DureeAB;
    @FXML
    private Label PrixAB;

    /**
     * Initializes the controller class.
     */
    private Abonnement abonnement;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void SetData(Abonnement abonnement){
        this.abonnement=abonnement; 
        NomAB.setText(abonnement.getNomAbonnement());
        PrixAB.setText("DT "+abonnement.getPrixAbonnement());
        DureeAB.setText(abonnement.getDureeAbonnement());
    }
    
}
