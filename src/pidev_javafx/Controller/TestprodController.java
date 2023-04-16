/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import pidev_javafx.entitie.Produit;
import pidev_javafx.service.ProduitService;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author damma
 */
public class TestprodController implements Initializable {

    @FXML
    private FlowPane panaff;
ObservableList<Produit>listprod=FXCollections.observableArrayList();
    @FXML
    private Button versacceuilbtn;
    @FXML
    private Button versajoutbtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addtopane();
    }    
    public void addtopane(){
        ProduitService ps=new ProduitService();
        listprod=ps.afficher();
        for(Produit p:listprod){
            VBox card=new VBox();
            card.setPrefSize(200, 250);
            if(p.getQuantite_produit()==0){
                card.setStyle("-fx-background-color: #ffffff; -fx-border-color: RED; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
            }
            else{
                card.setStyle("-fx-background-color: #ffffff; -fx-border-color: GREEN; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");

            }
            
            ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory1+p.getImage_produit())));
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                imageView.setPreserveRatio(true);
                imageView.setStyle("-fx-border-radius: 40px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DisplayProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Label l1=new Label("\n");
            card.getChildren().add(l1);
            Label namelabel=new Label("Nom produit:"+p.getNom());     
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            card.getChildren().add(namelabel);
            Label prixLabel=new Label("Prix"+Float.toString(p.getPrix_produit())+"DT");
            prixLabel.setWrapText(true);
            prixLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(prixLabel);
            Label catLabel=new Label("Categorie"+p.getCategory().getNomCategory());
            catLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(catLabel);
            Button bb=new Button("EDIT");
            bb.setStyle("-fx-background-color: #1372f4; -fx-background-radius: 25px; -fx-text-fill: white;");
            bb.setOnMouseClicked(e->{
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Modifproduct.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Edit Product");
                    ModifproductController mpc = loader.getController();
                    mpc.getdata(p);
                    Stage stage1 = (Stage) card.getScene().getWindow();
                    stage1.close();
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(DisplayProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            card.getChildren().add(bb);
            card.setOnMouseClicked(e->{
                showProductDetails(p);
        });
            panaff.getChildren().add(card);
            panaff.setMargin(card, new Insets(5, 5, 5, 5));
        }
    }
    private void showProductDetails(Produit product) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Product Details");
    alert.setHeaderText(product.getNom());
    alert.setContentText("Description: " + product.getDescription() + "\nPrix: " + product.getPrix_produit()+"DT"+"\nQuantite:"+product.getQuantite_produit()+"\nNote:"+product.getNote());
    alert.show();
}

    @FXML
    private void versacceuilonclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/acceuil.fxml"));
            Parent root;
        try {
            root = loader.load();
            panaff.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void versajouonclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Produit.fxml"));
            Parent root;
        try {
            root = loader.load();
            panaff.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
