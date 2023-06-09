/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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
import javafx.geometry.HPos;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import pidev_javafx.entitie.Category;
import pidev_javafx.entitie.PanierSession;
import pidev_javafx.entitie.Produit;
import pidev_javafx.service.CategoryService;
import pidev_javafx.service.ProduitService;
import pidev_javafx.service.SessionManager;
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
        catfrontanchor.toFront();
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
                card.setPrefSize(250, 250);
                
                scrolcat.setContent(flowcatfront);
                //card.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");
                card.setStyle("-fx-background-color: #AED6F1; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
                card.setOnMouseEntered(e->{
                    card.setStyle("-fx-scale-x: 1.1; -fx-scale-y: 1.1; -fx-transition: -fx-scale-x 0.3s, -fx-scale-y 0.3s; -fx-background-color: #AED6F1; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
                });
                card.setOnMouseExited(e->{
                    card.setStyle("-fx-scale-x: 1; -fx-scale-y: 1; -fx-transition: -fx-scale-x 0.3s, -fx-scale-y 0.3s; -fx-background-color: #AED6F1; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");

                });
                ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectoryProduit+cat.getImageCategory())));
                imageView.setFitWidth(240);
                imageView.setFitHeight(220);
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrontmagController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label namelabel=new Label(cat.getNomCategory());     
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
            namelabel.setAlignment(Pos.CENTER);
            card.getChildren().add(namelabel);
            card.setOnMouseClicked((MouseEvent e)->{
                cat(cat.getId());
                frontprodanchor.toFront();
            });
            card.setAlignment(Pos.CENTER);
            flowcatfront.getChildren().add(card);
            flowcatfront.setAlignment(Pos.CENTER);
            flowcatfront.setMargin(card, new Insets(5, 5, 5, 5));
          
                    
        }
    }
    private void displayprod(){
        listprodbycat.getChildren().clear();
        ProduitService ps=new ProduitService();
        ObservableList<Produit>listp=FXCollections.observableArrayList();
        listp=ps.findprodbycat(idc);
        //listp=ps.afficher();
        if(listp.isEmpty()){
                Label tt=new Label("cette categorie est vide");
                tt.setFont(Font.font("Arial", FontWeight.BOLD, 25));
                listprodbycat.setAlignment(Pos.CENTER);
                listprodbycat.getChildren().add(tt);
                }else{
            for(Produit produit:listp){
            VBox card=new VBox();
            card.setPrefSize(270, 270);
            card.setStyle("-fx-background-color: #AED6F1; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
            if(produit.getQuantite_produit()!=0){
                card.setOnMouseEntered(e->{
            card.setStyle("-fx-scale-x: 1.1; -fx-scale-y: 1.1; -fx-transition: -fx-scale-x 0.3s, -fx-scale-y 0.3s; -fx-background-color: #AED6F1; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
        });
            card.setOnMouseExited(event -> {
            card.setStyle("-fx-scale-x: 1; -fx-scale-y: 1; -fx-transition: -fx-scale-x 0.3s, -fx-scale-y 0.3s; -fx-background-color: #AED6F1; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
            });
            }
            
            
            ImageView imgview;
            try {
                imgview = new ImageView(new Image(new FileInputStream(Statics.uploadDirectoryProduit1+produit.getImage_produit())));
                imgview.setFitWidth(250);
                imgview.setFitHeight(250);
                imgview.setPreserveRatio(true);
                                imgview.setOnMouseClicked(e->{
                    setlabelprod(produit);
                    pan.toFront();
            });
                card.getChildren().add(imgview);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrontmagController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label namelabel=new Label(produit.getNom());     
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 25));
            namelabel.setAlignment(Pos.CENTER);
            //card.getChildren().add(namelabel);
            Label prixLabel=new Label(Float.toString(produit.getPrix_produit())+"DT");
                        prixLabel.setFont(Font.font("Verdana",FontWeight.NORMAL, 22));
            prixLabel.setWrapText(true);
            prixLabel.setAlignment(Pos.CENTER);
            //card.getChildren().add(prixLabel);
            Rating rateprod=new Rating();
            rateprod.setDisable(true);
            rateprod.setPrefSize(100, 100);
            rateprod.setRating(Math.floor(produit.getNote()));
            rateprod.setStyle("-fx-background-color: transparent; -fx-shadow-highlight-color: transparent; -fx-shadow-highlight-size: 0; -fx-shadow-raise: 0; -fx-shadow-color: transparent; -fx-shadow-size: 0;");
            //card.getChildren().add(rateprod);
            Label dd;
            if(produit.getQuantite_produit()==0){
                Label dispo=new Label("Out Of Stock");
                dd=dispo;
                                        dispo.setFont(Font.font("Verdana",FontWeight.NORMAL, 30));
                dispo.setAlignment(Pos.CENTER);
                dispo.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                dispo.setTextFill(Color.WHITE);
                //card.getChildren().add(dispo);
            }else{
                Label dispo=new Label("In Stock");
                dd=dispo;
                dispo.setFont(Font.font("Verdana",FontWeight.NORMAL, 30));
                dispo.setAlignment(Pos.CENTER);
                dispo.setTextFill(Color.GREEN);
                //card.getChildren().add(dispo);

            }
            Label catLabel=new Label(produit.getCategory().getNomCategory());
            catLabel.setAlignment(Pos.CENTER);
            catLabel.setFont(Font.font("Verdana",FontWeight.NORMAL, 16));
            //card.getChildren().add(catLabel);
            VBox hbox = new VBox();
            hbox.getChildren().addAll(namelabel, prixLabel, rateprod,dd);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setSpacing(10);

            FontAwesomeIconView icon1 = new FontAwesomeIconView(FontAwesomeIcon.CART_PLUS);
            icon1.getStyleClass().add("icon1");
            icon1.setSize("50");
            icon1.setOnMouseEntered(event -> {
            icon1.setStyle("-fx-scale-x: 1.3; -fx-scale-y: 1.3; -fx-transition: -fx-font-size 0.3s;");
            });
            icon1.setOnMouseExited(event -> {
            icon1.setStyle("-fx-scale-x: 1; -fx-scale-y: 1;");
            });
            icon1.setOnMouseClicked(event -> {
                if(SessionManager.getInstance()!=null){
                
                 try{
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/DashbordFront.fxml"));
                       Parent root = loader.load();
                       pidev_javafx.controller.DashbordFrontController controller = loader.getController();
                       controller.AjouterProduitPanier(produit);
                       notif2("GoldenGym","Succée, Produit Ajouté ");
                     
                                                       /* try {
                                                            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/login.fxml"));
                                                            Pane autreInterface = loader2.load();
                                                            Region parent = (Region) loader2.getRoot();
                                                            parent.prefWidthProperty().bind(controller.PaneContent.widthProperty());
                                                            parent.prefHeightProperty().bind(controller.PaneContent.heightProperty());
                                                            
                                                            controller.PaneContent.getChildren().setAll(autreInterface);
                                                            
                                                        } catch (IOException ex) {
                                                            ex.printStackTrace();
                                                        }*/
                                                        //pan.getScene().setRoot(root);
                                                        
                                                        
                                                        
                                                    } catch (IOException ex) {
                                        Logger.getLogger(pidev_javafx.controller.AffichageActiviteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                
                
                
                }else{
                                    try{
                                         FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/Acceuil.fxml"));
                                        Parent root = loader.load();
                                       pidev_javafx.controller.AcceuilController controller = loader.getController();
                                       controller.AjouterProduitPanier(produit);
                                       notif2("GoldenGym","Succée, Produit Ajouté ");
                                       //controller.VBoxPanier.getChildren().clear();
                     
                                                       /* try {
                                                            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/login.fxml"));
                                                            Pane autreInterface = loader2.load();
                                                            Region parent = (Region) loader2.getRoot();
                                                            parent.prefWidthProperty().bind(controller.PaneContent.widthProperty());
                                                            parent.prefHeightProperty().bind(controller.PaneContent.heightProperty());
                                                            
                                                            controller.PaneContent.getChildren().setAll(autreInterface);
                                                            
                                                        } catch (IOException ex) {
                                                            ex.printStackTrace();
                                                        }*/
                                                       // pan.getScene().setRoot(root);
                                                        
                                                        
                                                        
                                                        
                                                    } catch (IOException ex) {
                                        Logger.getLogger(pidev_javafx.controller.AffichageActiviteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                                    

                
                }
               
            });
            //FontAwesomeIconView icon2 = new FontAwesomeIconView();
            Label lllll=new Label("   \n"
                    + "\n"
                    + "\n");
            if(dd.getText()=="In Stock"){
            HBox hbox2 = new HBox();
            hbox2.getChildren().addAll(hbox,icon1);
            hbox2.setAlignment(Pos.CENTER_RIGHT);
            card.getChildren().addAll(hbox, hbox2);
            }
            else{
                HBox hbox2 = new HBox();
            hbox2.getChildren().addAll(hbox,lllll);
            hbox2.setAlignment(Pos.CENTER_RIGHT);
            card.getChildren().addAll(hbox, hbox2);
            }
            
            listprodbycat.getChildren().add(card);
            listprodbycat.setAlignment(Pos.TOP_LEFT);
            listprodbycat.setMargin(card, new Insets(5, 5, 5, 5));
    }
        }
    }
    public void cat(int id){
        this.idc=id;
        displayprod();
    }
    
     public void setlabelprod(Produit p){
        
        try {
            prodimgview.setImage(new Image(new FileInputStream(Statics.uploadDirectoryProduit1+p.getImage_produit())));
            prodimgview.setFitWidth(320);
            prodimgview.setFitHeight(320);
            prodimgview.setPreserveRatio(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrontmagController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pr=p;
        nomprodlabel.setText(p.getNom());
        nomprodlabel.setFont(Font.font("Arial", FontWeight.BOLD, 27));
        prixprodlabel.setText(String.format("%.2f DT", p.getPrix_produit()));
        prixprodlabel.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
        descriptionprodlabel.setText(p.getDescription());
        descriptionprodlabel.setWrapText(true);
        descriptionprodlabel.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
        notelabel.setText(Math.round(p.getNote())+"/5");
        notelabel.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
        //ratingstar.setRating(p.getNote());
        if(p.getQuantite_produit()==0){
            dispoprodlabel.setText("Out Of Stock");
            dispoprodlabel.setTextFill(Color.RED);
            dispoprodlabel.setFont(Font.font("Arial", FontWeight.NORMAL, 30));

        }else{
            dispoprodlabel.setText("In Stock");
            dispoprodlabel.setTextFill(Color.GREEN);
            dispoprodlabel.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        }
//        VBox labelsVBox = new VBox(10, nomprodlabel, prixprodlabel, descriptionprodlabel, dispoprodlabel);
//        HBox contentHBox = new HBox(20, prodimgview, labelsVBox);
//        //contentHBox.setPadding(new Insets(20));
//        hboxlabe.getChildren().add(contentHBox);
//        vboxlabe.getChildren().addAll(nomprodlabel, prixprodlabel, descriptionprodlabel, dispoprodlabel);
//        Pane parentPane = new Pane(contentHBox);
//        parentPane.setPrefSize(500, 500);
        
        
        
        
}
       public void notif2(String title, String text){
   Image img = new Image("/pidev/javafx/assets/logo1.png");
   
   ImageView img1= new ImageView(img);
                img1.setFitWidth(200);
                img1.setFitHeight(200);
                img1.setPreserveRatio(true);
                img1.setStyle("-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Verdana",FontWeight.NORMAL, 14));

        // create a label with the message and the Verdana font
        Label messageLabel = new Label(text);
        messageLabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
                StackPane customRegion = new StackPane(img1,titleLabel,messageLabel);
        customRegion.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        customRegion.setAlignment(Pos.CENTER);
            //customRegion.getChildrenUnmodifiable().add(img1);
    Notifications notificationBuilder = Notifications.create()
    .title(title)
    .text(text)
            .graphic(img1)
            .hideAfter(Duration.seconds(5))
            //.darkStyle()
            .position(Pos.BOTTOM_RIGHT);
   
    notificationBuilder.show();
         
}
}
