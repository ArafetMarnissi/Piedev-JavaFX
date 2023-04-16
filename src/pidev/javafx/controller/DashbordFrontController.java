/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pidev_javafx.entitie.PanierSession;
import pidev_javafx.entitie.Produit;

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
    @FXML
    private AnchorPane PaneContent;
    
    @FXML
    private ScrollPane ScrollPanePanierContent;
    @FXML
    private GridPane GridPanier;
    
    private List<Produit> produits = new ArrayList<>();
    /**
     * Initializes the controller class.
     * 
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

///Panier///
getData();
afficherPanier();



/////////all Slide Bar//////////       
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


////////panier////////

private List<Produit> getData(){
    HashMap<Produit,Integer> panier =new HashMap<>();
    List<Produit> produits = new ArrayList<>();
    Produit produit;
    for(int i=0;i<5;i++){
        produit =new Produit();
        produit.setId(i+1);
        produit.setNom("Protein");
        produit.setPrix_produit(150);
        produit.setImage_produit("/pidev_javafx/assets/front-view-fit-woman-training-with-dumbells.jpg");
        panier.put(produit, 2);
        PanierSession.getInstance(panier);
        produits.add(produit);
    }
    System.out.println(PanierSession.getPanier().toString());
    return produits;
}

public void afficherPanier(){

        //produits.addAll(getData());
        
        HashMap<Produit,Integer> panier =PanierSession.getPanier();
        List<Produit> produitsPanier = new ArrayList<>(panier.keySet());
        
        int column=0;
        int row=1;
        try {
            for(int i=0;i<produitsPanier.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/CartPanier.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CartPanierController cartPanierController = fxmlLoader.getController();
                cartPanierController.SetData(produitsPanier.get(i));
                GridPanier.add(anchorPane, column, row++);
                
                //set Grid width
                GridPanier.setMinWidth(Region.USE_COMPUTED_SIZE);
                GridPanier.setPrefWidth(Region.USE_COMPUTED_SIZE);
                GridPanier.setMaxWidth(Region.USE_PREF_SIZE);
                //set Grid height
                GridPanier.setMinHeight(Region.USE_COMPUTED_SIZE);
                GridPanier.setPrefHeight(Region.USE_COMPUTED_SIZE);
                GridPanier.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
            AnchorPane anchorPaneLast = new AnchorPane();
            anchorPaneLast.setPrefWidth(152);
            anchorPaneLast.setPrefHeight(278);
            GridPanier.add(anchorPaneLast, column, row++);
            
            } catch (IOException ex) {
                Logger.getLogger(DashbordFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        

}


public void updatePanier() throws IOException {
    HashMap<Produit,Integer> panier = PanierSession.getPanier();
    List<Node> nodesToRemove = new ArrayList<>();
    int row = 1;
    for (Node node : GridPanier.getChildren()) {
        if (GridPane .getRowIndex(node) == row) {
            Produit produit = ((CartPanierController) node.getProperties().get("controller")).getProduit();
            if (panier.containsKey(produit)) {
                row++;
                continue;
            }
            nodesToRemove.add(node);
        }
    }
    for (Node node : nodesToRemove) {
        GridPanier.getChildren().remove(node);
    }
    row = 1;
    for (Produit produit : panier.keySet()) {
        if (panier.get(produit) == 0) {
            continue;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/CartPanier.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CartPanierController cartPanierController = fxmlLoader.getController();
                cartPanierController.SetData(produit);
        GridPane.setRowIndex(anchorPane, row);
        row++;
    }
}

}
