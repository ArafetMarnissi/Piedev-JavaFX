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
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.smartcardio.Card;
import pidev_javafx.entitie.Category;
import pidev_javafx.entitie.Produit;
import pidev_javafx.service.ProduitService;
import pidev_javafx.tools.Statics;


/**
 * FXML Controller class
 *
 * @author damma
 */
public class DisplayProductController implements Initializable {

    
    ProduitService ps;
    private Button btnajout;
    
    private HBox hboxlist;
    @FXML
    private FlowPane panf;
        
ObservableList<Produit>listprod=FXCollections.observableArrayList();
    @FXML
    private Button ajoutprod;
    @FXML
    private Button acceuibtn;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ProduitService ps=new ProduitService();
        
        listprod=ps.afficher();
        //System.out.println(listprod);
        for(Produit produit:listprod){
            VBox card=new VBox();
//            card.setMinHeight(200);
//        card.setMaxHeight(200);
//        card.setMinWidth(150);
//        card.setMaxWidth(150);
        card.setPrefSize(200, 250);
            if(produit.getQuantite_produit()==0){
                card.setStyle("-fx-background-color: #ffffff; -fx-border-color: RED; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
            }
            else{
                card.setStyle("-fx-background-color: #ffffff; -fx-border-color: GREEN; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");

            }
            ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory1+produit.getImage_produit())));
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
            Label namelabel=new Label("Nom produit:"+produit.getNom());     
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            card.getChildren().add(namelabel);
            Label prixLabel=new Label("Prix"+Float.toString(produit.getPrix_produit())+"DT");
            prixLabel.setWrapText(true);
            prixLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(prixLabel);
            Label catLabel=new Label("Categorie"+produit.getCategory().getNomCategory());
            catLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(catLabel);
            Button btn=new Button("Edit");
            btn.setAlignment(Pos.TOP_LEFT);
            btn.setOnAction(e->{
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Modifproduct.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Edit Product");
                    ModifproductController mpc = loader.getController();
                    mpc.getdata(produit);
                    Stage stage1 = (Stage) card.getScene().getWindow();
                    stage1.close();
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(DisplayProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            card.getChildren().add(btn);
            card.setOnMousePressed(e -> {
                if (e.isPrimaryButtonDown()) { 
                    showProductDetails(produit);
                }
            });

            panf.getChildren().add(card);
            panf.setMargin(card, new Insets(5, 5, 5, 5));
        }
        
    }    

    private void versajouteronclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Produit.fxml"));
            Parent root;
        try {
            root = loader.load();
            btnajout.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void showProductDetails(Produit product) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Product Details");
    alert.setHeaderText(product.getNom());
    alert.setContentText("Description: " + product.getDescription() + "\nPrix: " + product.getPrix_produit()+"DT"+"\nQuantite:"+product.getQuantite_produit()+"\nNote:"+product.getNote());
    alert.show();
}
    public void afficherbycat(int idcat){
        ProduitService ps=new ProduitService();
        
        listprod=ps.findprodbycat(idcat);
        //System.out.println(listprod);
        for(Produit produit:listprod){
            VBox card=new VBox();
            card.setMinHeight(200);
        card.setMaxHeight(200);
        card.setMinWidth(150);
        card.setMaxWidth(150);
            if(produit.getQuantite_produit()==0){
                card.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-padding: 10px;");
            }
            else{
                card.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-padding: 10px;");

            }
            ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory1+produit.getImage_produit())));
                imageView.setFitWidth(120);
                imageView.setFitHeight(80);
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DisplayProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            Label namelabel=new Label(produit.getNom());     
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            card.getChildren().add(namelabel);
            Label prixLabel=new Label(Float.toString(produit.getPrix_produit())+"DT");
            prixLabel.setWrapText(true);
            prixLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(prixLabel);
            Label catLabel=new Label(produit.getCategory().getNomCategory());
            catLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(catLabel);
            Button btn=new Button("Edit");
            btn.setAlignment(Pos.TOP_RIGHT);
            btn.setOnAction(e->{
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Modifproduct.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Edit Product");
                    ModifproductController mpc = loader.getController();
                    mpc.getdata(produit);
                    Stage stage1 = (Stage) card.getScene().getWindow();
                    stage1.close();
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(DisplayProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            card.getChildren().add(btn);
            card.setOnMousePressed(e -> {
                if (e.isPrimaryButtonDown()) { 
                    showProductDetails(produit);
                }
            });

            panf.getChildren().add(card);
            panf.setMargin(card, new Insets(5, 5, 5, 5));
    }
    }

    @FXML
    private void versaddonclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Produit.fxml"));
            Parent root;
        try {
            root = loader.load();
            panf.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void versacceuilonclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/acceuil.fxml"));
            Parent root;
        try {
            root = loader.load();
            panf.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
