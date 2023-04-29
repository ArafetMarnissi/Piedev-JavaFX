/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import pidev_javafx.entitie.Category;
import pidev_javafx.entitie.Produit;
import pidev_javafx.service.CategoryService;
import pidev_javafx.service.ProduitService;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author damma
 */
public class FrontmagController implements Initializable {

    @FXML
    private AnchorPane pan;
    @FXML
    private HBox hboxlabe;
    @FXML
    private ImageView prodimgview;
    @FXML
    private VBox vboxlabe;
    @FXML
    private Label nomprodlabel;
    @FXML
    private Label prixprodlabel;
    @FXML
    private Label descriptionprodlabel;
    @FXML
    private Label notelabel;
    @FXML
    private Label dispoprodlabel;
    @FXML
    private Rating ratingstar;
    @FXML
    private FontAwesomeIconView returnprod;
    @FXML
    private AnchorPane frontprodanchor;
    @FXML
    private FlowPane listprodbycat;
    @FXML
    private FontAwesomeIconView returntocat;
    @FXML
    private TextField rechercherfield;
    @FXML
    private AnchorPane catfrontanchor;
    @FXML
    private ScrollPane scrolcat;
    @FXML
    private FlowPane flowcatfront;
    private int idc;
    private Produit pr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        affcat();
    }    

    @FXML
    private void ratted(MouseEvent event) {
                ProduitService ps=new ProduitService();
        ps.updaterate(pr.getId(), (float)ratingstar.getRating());
        Produit pp=new Produit();
        try {
            pp=ps.findprodbyid(pr.getId());
        } catch (SQLException ex) {
            Logger.getLogger(FrontmagController.class.getName()).log(Level.SEVERE, null, ex);
        }
        notelabel.setText(Math.round(pp.getNote())+"/5");
    }

    @FXML
    private void returnprodonclic(MouseEvent event) {
        cat(pr.getCategory().getId());
        frontprodanchor.toFront();
    }

    @FXML
    private void reterntocatonclick(MouseEvent event) {
        catfrontanchor.toFront();
    }
    
    private void affcat(){
        CategoryService cs=new CategoryService();
        ObservableList<Category>listcat=FXCollections.observableArrayList();
        listcat=cs.afficher();     
        for(Category cat:listcat){
                VBox card=new VBox();
                
//                card.setMinHeight(150);
//                card.setMaxHeight(150);
//                card.setMinWidth(150);
//                card.setMaxWidth(150);
                card.setPrefSize(150, 150);
                scrolcat.setContent(flowcatfront);
                //card.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");
                card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
                ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory+cat.getImageCategory())));
                imageView.setFitWidth(120);
                imageView.setFitHeight(80);
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrontmagController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label namelabel=new Label(cat.getNomCategory());     
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            card.getChildren().add(namelabel);
            card.setOnMouseClicked((MouseEvent e)->{
                cat(cat.getId());
                frontprodanchor.toFront();
            });
            flowcatfront.getChildren().add(card);
            flowcatfront.setMargin(card, new Insets(5, 5, 5, 5));
          
                    
        }
    }
    private void displayprod(){
        listprodbycat.getChildren().clear();
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
                Logger.getLogger(FrontmagController.class.getName()).log(Level.SEVERE, null, ex);
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
                    setlabelprod(produit);
                    pan.toFront();
            });
            }
            Label catLabel=new Label(produit.getCategory().getNomCategory());
            catLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(catLabel);
            
            listprodbycat.getChildren().add(card);
            listprodbycat.setMargin(card, new Insets(5, 5, 5, 5));
    }
    }
    public void cat(int id){
        this.idc=id;
        displayprod();
    }
    
     public void setlabelprod(Produit p){
        
        try {
            prodimgview.setImage(new Image(new FileInputStream(Statics.uploadDirectory1+p.getImage_produit())));
            prodimgview.setFitWidth(200);
            prodimgview.setFitHeight(200);
            prodimgview.setPreserveRatio(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrontmagController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pr=p;
        nomprodlabel.setText(p.getNom());
        nomprodlabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        prixprodlabel.setText(String.format("%.2f DT", p.getPrix_produit()));
        prixprodlabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        descriptionprodlabel.setText(p.getDescription());
        descriptionprodlabel.setWrapText(true);
        descriptionprodlabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        notelabel.setText(Math.round(p.getNote())+"/5");
        //ratingstar.setRating(p.getNote());
        if(p.getQuantite_produit()==0){
            dispoprodlabel.setText("Out Of Stock");
            dispoprodlabel.setTextFill(Color.RED);
        }else{
            dispoprodlabel.setText("In Stock");
            dispoprodlabel.setTextFill(Color.GREEN);
        }
//        VBox labelsVBox = new VBox(10, nomprodlabel, prixprodlabel, descriptionprodlabel, dispoprodlabel);
//        HBox contentHBox = new HBox(20, prodimgview, labelsVBox);
//        //contentHBox.setPadding(new Insets(20));
//        hboxlabe.getChildren().add(contentHBox);
//        vboxlabe.getChildren().addAll(nomprodlabel, prixprodlabel, descriptionprodlabel, dispoprodlabel);
//        Pane parentPane = new Pane(contentHBox);
//        parentPane.setPrefSize(500, 500);
        
        
        
        
}
}
