/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author marni
 */
public class DashbordController implements Initializable {

    @FXML
    private HBox root;
    @FXML
    private AnchorPane side_anchorpane;
    @FXML
    private Pane inner_pane;
    @FXML
    private Pane most_inner_pane;
    @FXML
    private Pane PaneSlideBarProfil;
    @FXML
    private JFXButton btn_back_Dashbord;
    @FXML
    private JFXButton btn_back_User;
    @FXML
    private JFXButton btn_back_Activite;
    @FXML
    private JFXButton btn_back_produit;
    @FXML
    private JFXButton btn_back_Category;
    @FXML
    private JFXButton btn_back_Commande;
    @FXML
    private JFXButton btn_back_Reservation;
    @FXML
    private JFXButton btn_back_Abonnement;
    @FXML
    private JFXButton btn_back_Reclamation;
    @FXML
    private JFXButton btn_profil;
    
    private boolean isAlreadyTranslated =false;
    @FXML
    private Pane pane1;
    /**
     * Initializes the controller class.
     * 
     * 
     */

    public void translate() {
        pane1.toBack();
        PaneSlideBarProfil.toFront();
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(0.3);
        fadeTransition1.play();

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), PaneSlideBarProfil);
        translateTransition1.setByY(+0);
        translateTransition1.play();
        isAlreadyTranslated = true;
    }

    public void unTranslate() {
         PaneSlideBarProfil.toBack();
        pane1.toFront();
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();

       /* fadeTransition1.setOnFinished(event1 -> {
            //pane1.setVisible(false);
        });*/

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), PaneSlideBarProfil);
        translateTransition1.setByY(-0);
        translateTransition1.play();
        isAlreadyTranslated = false;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane1.toFront();
        //PaneSlideBarProfil.setVisible(false);
        

    }    

    @FXML
    private void Profil_slide_bar(MouseEvent event) {
        
    }

    @FXML
    private void Profil_slide_bar_on_action(ActionEvent event) {
                
            if (!isAlreadyTranslated) {
                translate();
            } else {
                unTranslate();
            }
        
        
    }
    
}
