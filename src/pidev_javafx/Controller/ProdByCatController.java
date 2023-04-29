/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import javafx.scene.paint.Color;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import pidev_javafx.entitie.Category;
import pidev_javafx.entitie.Produit;
import pidev_javafx.service.ProduitService;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author damma
 */
public class ProdByCatController implements Initializable {

    @FXML
    private FlowPane listprodbycat;
    public int idc;
    @FXML
    private FontAwesomeIconView returntocat;
    @FXML
    private TextField rechercherfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void cat(int id){
        this.idc=id;
        displayprod();
    }
    private void displayprod(){
        ProduitService ps=new ProduitService();
        ObservableList<Produit>listp=FXCollections.observableArrayList();
        listp=ps.findprodbycat(idc);
        //listp=ps.afficher();
        for(Produit produit:listp){
            VBox card=new VBox();
            card.setPrefSize(150, 150);
            card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
            
            
            ImageView imgview;
            try {
                imgview = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory1+produit.getImage_produit())));
                imgview.setFitWidth(120);
                imgview.setFitHeight(80);
                imgview.setPreserveRatio(true);
                card.getChildren().add(imgview);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DisplaycatfrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label namelabel=new Label(produit.getNom());     
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            card.getChildren().add(namelabel);
            Label prixLabel=new Label(Float.toString(produit.getPrix_produit())+"DT");
            prixLabel.setWrapText(true);
            prixLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(prixLabel);
            Rating rateprod=new Rating();
            rateprod.setDisable(true);
            rateprod.setPrefSize(50, 50);
            rateprod.setRating(Math.floor(produit.getNote()));
            card.getChildren().add(rateprod);
            if(produit.getQuantite_produit()==0){
                Label dispo=new Label("Out Of Stock");
                dispo.setAlignment(Pos.CENTER);
                dispo.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                dispo.setTextFill(Color.WHITE);
                card.getChildren().add(dispo);
            }else{
                Label dispo=new Label("In Stock");
                dispo.setAlignment(Pos.CENTER);
                dispo.setTextFill(Color.GREEN);
                card.getChildren().add(dispo);
                card.setOnMouseClicked(e->{
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Detailprod.fxml"));
                try{
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Detail Product");
                    DetailprodController dpc=loader.getController();
                    dpc.setlabelprod(produit);
                    Stage stage1 = (Stage) card.getScene().getWindow();
                    stage1.close();
                            stage.show();
                }catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
            }
            Label catLabel=new Label(produit.getCategory().getNomCategory());
            catLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(catLabel);
            
            listprodbycat.getChildren().add(card);
            listprodbycat.setMargin(card, new Insets(5, 5, 5, 5));
    }
    }

    @FXML
    private void reterntocatonclick(MouseEvent event) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Displaycatfront.fxml"));
                try{
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Category");
                    Stage stage1 = (Stage) returntocat.getScene().getWindow();
                    stage1.close();
                            stage.show();
                }catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
    }
}
