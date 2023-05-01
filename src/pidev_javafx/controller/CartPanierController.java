/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pidev_javafx.entitie.PanierSession;
import pidev_javafx.entitie.Produit;
import pidev_javafx.controller.DashbordFrontController;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author marni
 */
public class CartPanierController implements Initializable {

    @FXML
    private Label NomProduitCart;
    @FXML
    private Label PrixProduitCart;
    @FXML
    private ImageView imageProduitCart;
    @FXML
    private JFXButton btnPlusCart;
    @FXML
    private Label quantiteProduitCart;
    @FXML
    private JFXButton btnMinusCart;
    @FXML
    private JFXButton btnRemoveCart;

    private Produit produit;
    /**
     * Initializes the controller class.
     */
       @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void SetData(Produit produit){
        this.produit=produit; 
        NomProduitCart.setText(produit.getNom());
        PrixProduitCart.setText("DT "+produit.getPrix_produit());      
        
        try {
            imageProduitCart.setImage(new Image(new FileInputStream(Statics.uploadDirectoryProduit1+produit.getImage_produit())));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CartPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @FXML
    private void plus(ActionEvent event) {
        PanierSession.getInstance().addProduct(produit);
        quantiteProduitCart.setText(PanierSession.getPanier().get(produit).toString());
    }

    @FXML
    private void Minus(ActionEvent event) {
        PanierSession.getInstance().decreaseProduct(produit);
        quantiteProduitCart.setText(PanierSession.getPanier().get(produit).toString());
    }

private Node getNodeFromParent(Parent parent, String id) {for (Node node : parent.getChildrenUnmodifiable()) {
        if (node.getId() != null && node.getId().equals(id)) {
            return node;
        } else if (node instanceof Parent) {
            Node result = getNodeFromParent((Parent) node, id);
            if (result != null) {
                return result;
            }
        }
    }
    return null;
}

    public Produit getProduit() {
        return produit;
    }

    
    
}
