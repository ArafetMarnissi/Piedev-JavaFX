/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author marni
 */
public class DashbordBackController implements Initializable {

    @FXML
    private ImageView profile;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane PaneContent;
    @FXML
    private JFXButton btnListeCommande;
    @FXML
    private JFXButton consultProfilButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SlideBar();

    }    

    @FXML
    private void ListeCommandeBack(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/ListeCommandeback.fxml"));
            Pane autreInterface = loader.load();
            PaneContent.getChildren().setAll(autreInterface);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SlideBar(){
    
        pane1.setVisible(false);
        
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition.setByX(-600);
        translateTransition.play();
        
        menu.setOnMouseClicked(event -> {
            pane1.setVisible(true);
            
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();



            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
            translateTransition1.setByX(+600);
            translateTransition1.play();
        });
        
        pane1.setOnMouseClicked(event -> {
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();
        
         fadeTransition1.setOnFinished(event1 -> {
            pane1.setVisible(false);
        });

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
        translateTransition1.setByX(-600);
        translateTransition1.play();
        });
    }

    @FXML
    private void GestionUtilisateurs(ActionEvent event) {
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/affichage.fxml"));
            Pane autreInterface = loader.load();
            PaneContent.getChildren().setAll(autreInterface);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void consulterProfil(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/myProfil.fxml"));
            Pane autreInterface = loader.load();
            PaneContent.getChildren().setAll(autreInterface);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   

    
}
