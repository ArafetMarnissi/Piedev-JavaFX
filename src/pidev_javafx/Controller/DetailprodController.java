/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pidev_javafx.entitie.Produit;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author damma
 */
public class DetailprodController implements Initializable {

    @FXML
    private ImageView prodimgview;
    @FXML
    private Label nomprodlabel;
    @FXML
    private Label prixprodlabel;
    @FXML
    private Label descriptionprodlabel;
    @FXML
    private Label dispoprodlabel;
    @FXML
    private AnchorPane pan;
    @FXML
    private HBox hboxlabe;
    @FXML
    private VBox vboxlabe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setlabelprod(Produit p){
        
        try {
            prodimgview.setImage(new Image(new FileInputStream(Statics.uploadDirectory1+p.getImage_produit())));
            prodimgview.setFitWidth(200);
            prodimgview.setFitHeight(200);
            prodimgview.setPreserveRatio(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailprodController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        nomprodlabel.setText(p.getNom());
        nomprodlabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nomprodlabel.setPrefWidth(100);
        nomprodlabel.setPrefHeight(20);
        prixprodlabel.setText(String.format("%.2f DT", p.getPrix_produit()));
        prixprodlabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        prixprodlabel.setPrefWidth(100);
        prixprodlabel.setPrefHeight(20);
        descriptionprodlabel.setText(p.getDescription());
        descriptionprodlabel.setWrapText(true);
        descriptionprodlabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        if(p.getQuantite_produit()==0){
            dispoprodlabel.setText("Out Of Stock");
            dispoprodlabel.setTextFill(Color.RED);
            dispoprodlabel.setPrefWidth(100);
            dispoprodlabel.setPrefHeight(20);
        }else{
            dispoprodlabel.setText("In Stock");
            dispoprodlabel.setTextFill(Color.GREEN);
            dispoprodlabel.setPrefWidth(100);
            dispoprodlabel.setPrefHeight(20);
        }
        VBox labelsVBox = new VBox(10, nomprodlabel, prixprodlabel, descriptionprodlabel, dispoprodlabel);
        HBox contentHBox = new HBox(20, prodimgview, labelsVBox);
        contentHBox.setPadding(new Insets(20));
        hboxlabe.getChildren().add(contentHBox);
        vboxlabe.getChildren().addAll(nomprodlabel, prixprodlabel, descriptionprodlabel, dispoprodlabel);
//        Pane parentPane = new Pane(contentHBox);
//        parentPane.setPrefSize(500, 500);
        
        
        
        
}
    
    
}
