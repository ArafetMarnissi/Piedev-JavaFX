/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author marni
 */
public class DashbordFrontController implements Initializable {

    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private ImageView Panier;
    @FXML
    private AnchorPane panePanier1;
    
    private boolean isMenuTranslated=false;
    private boolean isCartTranslated=false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


       
pane1.setVisible(false);
hideMenuPane();
hideCartPane();

menu.setOnMouseClicked(event -> {
 
    showMenuPane();

});

Panier.setOnMouseClicked(event -> {
    showCartPane();
});

pane1.setOnMouseClicked(event -> {
    hidePane1();
    if (pane2.getTranslateX() == 0) {
        hideMenuPane();
    }else  if (panePanier1.getTranslateX() == 0) {
        hideCartPane();
    }
});


    }

private void hidePane1() {
    FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
    fadeTransition1.setFromValue(0.15);
    fadeTransition1.setToValue(0);
    fadeTransition1.play();
    fadeTransition1.setOnFinished(event1 -> {
        pane1.setVisible(false);
    });
}

private void showMenuPane() {
    pane1.setVisible(true);
    FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
    fadeTransition1.setFromValue(0);
    fadeTransition1.setToValue(0.15);
    fadeTransition1.play();
    TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), pane2);
    translateTransition1.setByX(+600);
    translateTransition1.play();
}

private void showCartPane() {
    pane1.setVisible(true);
    FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
    fadeTransition1.setFromValue(0);
    fadeTransition1.setToValue(0.15);
    fadeTransition1.play();
    TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), panePanier1);
    translateTransition1.setByY(-1000);
    translateTransition1.play();
}

private void hideMenuPane() {
    hidePane1();
    TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5), pane2);
    translateTransition2.setByX(-600);
    translateTransition2.play();
}

private void hideCartPane() {
    hidePane1();
    TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), panePanier1);
    translateTransition1.setByY(+1000);
    translateTransition1.play();
}
}
