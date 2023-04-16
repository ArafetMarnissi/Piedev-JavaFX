/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.javafx.controller;

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
import pidev.javafx.controller.DashbordFrontController;

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
        //PrixProduitCart.setText("DT "+PanierSession.getPanier().get(1));
        
        quantiteProduitCart.setText("1");
        try {
            //Image image = new Image(getClass().getResourceAsStream(produit.getImage_produit()));
            //imageProduitCart.setImage(image);
            imageProduitCart.setImage(new Image(new FileInputStream(produit.getImage_produit())));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CartPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void RemoveProduct(ActionEvent event) throws IOException {
        HashMap<Produit,Integer> panier =PanierSession.getPanier();
        panier.remove(produit);
        PanierSession.setPanier(panier);
        System.out.println(PanierSession.getPanier().toString());


    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/DashbordFront.fxml"));
    Parent root = loader.load();
    // Créer le contrôleur pour la vue des détails de la commande
    DashbordFrontController controller = loader.getController();
    //controller.afficherPanier();
    btnRemoveCart.getScene().setRoot(root);
    
//    controller.updatePanier();
 //   controller.afficherPanier();

    //controller.afficherPanier();
    //btnRemoveCart.getScene().setRoot(root);
       
    //DashbordFrontController controller = (DashbordFrontController) btnRemoveCart.getScene().getUserData();
    //controller.afficherPanier();

        
    }

    @FXML
    private void plus(ActionEvent event) {
        HashMap<Produit,Integer> panier =PanierSession.getPanier();
        panier.replace(produit, panier.get(produit), panier.get(produit)+1);
        PanierSession.setPanier(panier);
        quantiteProduitCart.setText(PanierSession.getPanier().get(produit).toString());
    }

    @FXML
    private void Minus(ActionEvent event) {
        HashMap<Produit,Integer> panier =PanierSession.getPanier();
        panier.replace(produit, panier.get(produit), panier.get(produit)-1);
        PanierSession.setPanier(panier);
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
