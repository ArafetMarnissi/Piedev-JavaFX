/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import pidev_javafx.entitie.PanierSession;
import pidev_javafx.entitie.Produit;

import pidev_javafx.service.SessionManager;

/**
 * FXML Controller class
 *
 * @author marni
 */
public class AcceuilController implements Initializable {

    @FXML
    public AnchorPane PaneContent;
    @FXML
    private ImageView Panier;
    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private JFXButton loginButton;
    @FXML
    private AnchorPane panePanier1;
    @FXML
    private ScrollPane ScrollPanePanierContent;
    @FXML
    public VBox VBoxPanier;
    @FXML
    private Label LabelPrixTotal;
    @FXML
    private JFXButton btnPasserCommande;
    
        private HashMap<Produit,HBox> mapHashbox = new HashMap<>();
            
        private Stage stage;
    private  Parent root;
    private Scene scene;
    @FXML
    private JFXButton BtnActivite;
    @FXML
    private JFXButton btnCategory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
////
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/Frontmag.fxml"));
            Pane autreInterface = loader.load();
            
            Region parent = (Region) loader.getRoot();
            
            parent.prefWidthProperty().bind(PaneContent.widthProperty());
            parent.prefHeightProperty().bind(PaneContent.heightProperty());
            PaneContent.getChildren().setAll(autreInterface);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
///Panier///

    afficherPanier();
    if(PanierSession.getPanier().isEmpty()){
        btnPasserCommande.setVisible(false);
    }else{
        btnPasserCommande.setVisible(true);
    }
    


/////////all Slide Bar//////////       
pane1.setVisible(false);
hideMenuPane();
hideCartPane();

menu.setOnMouseClicked(event -> {
 
    showMenuPane();

});

Panier.setOnMouseClicked(event -> {
    //reloadPanier();
    afficherPanier();
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
        produit.setId(20);
        produit.setNom("Protein");
        produit.setPrix_produit(150);
        produit.setImage_produit("/pidev_javafx/assets/front-view-fit-woman-training-with-dumbells.jpg");
        PanierSession.getInstance().addProduct(produit);
        
    }
    
    System.out.println(PanierSession.getPanier().toString());
    return produits;
}

public void afficherPanier(){
        if(PanierSession.getPanier().isEmpty()){
        btnPasserCommande.setVisible(false);
    }else{
        btnPasserCommande.setVisible(true);
    }

        VBoxPanier.getChildren().clear();
        HashMap<Produit,Integer> panier =PanierSession.getPanier();
        List<Produit> produitsPanier = new ArrayList<>(panier.keySet());
        

      
            for(int i=0;i<produitsPanier.size();i++){

               HBox hboxEntry =new HBox();
               hboxEntry=createCarteProduit(produitsPanier.get(i));
               VBoxPanier.getChildren().add(hboxEntry);
                
            }
            AnchorPane anchorPaneLast = new AnchorPane();
            anchorPaneLast.setPrefWidth(152);
            anchorPaneLast.setPrefHeight(278);
            VBoxPanier.getChildren().add(anchorPaneLast);
            LabelPrixTotal.setText(String.format("%.2f", PanierSession.getInstance().calculTotale())+" DT");

            
        

}


public void RemoveItemPanier(Produit produit) throws IOException {
  VBoxPanier.getChildren().clear();
  
  
  //SessionCartPanier.getMapHashBox().remove(produit);
  
    mapHashbox.remove(produit);
    
    for(Produit prod :mapHashbox.keySet()){
        
        VBoxPanier.getChildren().add(mapHashbox.get(prod));

    }
    AnchorPane anchorPaneLast = new AnchorPane();
    anchorPaneLast.setPrefWidth(152);
    anchorPaneLast.setPrefHeight(278);
    VBoxPanier.getChildren().add(anchorPaneLast);
}
public VBox creatHboxBtn(Produit produit){
                HashMap<Produit,Integer> panier =PanierSession.getPanier();

                //creer une button plus
                ImageView imageViewPlus = new ImageView(new Image("/pidev/javafx/assets/plus.png"));
                imageViewPlus.setFitWidth(27);
                imageViewPlus.setFitHeight(29);
                JFXButton btnPlus = new JFXButton("", imageViewPlus);
                //creer une button moins
                ImageView imageViewMoins = new ImageView(new Image("/pidev/javafx/assets/minus.png"));
                imageViewMoins.setFitWidth(27);
                imageViewMoins.setFitHeight(29);
                JFXButton btnMoins = new JFXButton("", imageViewMoins);
                //creer une button supprimer
                ImageView imageViewSupp = new ImageView(new Image("/pidev/javafx/assets/close.png"));
                imageViewSupp.setFitWidth(27);
                imageViewSupp.setFitHeight(29);
                JFXButton btnSupp = new JFXButton("", imageViewSupp);
                //creer une label de quantité
                Label labelQua = new Label(panier.get(produit).toString());
                labelQua.setId("labelQu");
                
                
                btnSupp.setUserData(produit);
                btnMoins.setUserData(produit);
                btnPlus.setUserData(produit);
                //creer les evenement pour les btns
                btnSupp.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Node sourceComponent = (Node)event.getSource();
                            Produit produit =(Produit) sourceComponent.getUserData();
                            panier.remove(produit);
                            PanierSession.setPanier(panier);
                            VBoxPanier.getChildren().clear();
                            RemoveItemPanier(produit);
                            LabelPrixTotal.setText(String.format("%.2f", PanierSession.getInstance().calculTotale())+" DT");
                        } catch (IOException ex) {
                            Logger.getLogger(DashbordFrontController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
                btnPlus.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Node sourceComponent = (Node)event.getSource();
                        Produit produit =(Produit) sourceComponent.getUserData();
                        PanierSession.getInstance().addProduct(produit);
                        labelQua.setText(panier.get(produit).toString());
                        LabelPrixTotal.setText(String.format("%.2f", PanierSession.getInstance().calculTotale())+" DT");
                        

                    }
                });
                btnMoins.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Node sourceComponent = (Node)event.getSource();
                        Produit produit =(Produit) sourceComponent.getUserData();
                        PanierSession.getInstance().decreaseProduct(produit);
                        labelQua.setText(panier.get(produit).toString());
                        LabelPrixTotal.setText(String.format("%.2f", PanierSession.getInstance().calculTotale())+" DT");

                    }
                });
                
                HBox hboxBtn =new HBox();
                VBox vboxallBtn =new VBox();
                
                hboxBtn.getChildren().addAll(btnPlus,labelQua,btnMoins);
                hboxBtn.setSpacing(5);
                
                vboxallBtn.getChildren().addAll(hboxBtn,btnSupp);
                vboxallBtn.setSpacing(5);
                vboxallBtn.setAlignment(Pos.CENTER);
                vboxallBtn.setId("VboxBtn");
                return vboxallBtn;
}




@FXML
  private void PasserCommande(ActionEvent event) {
      if(SessionManager.getInstance()!=null){  
            try {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/ListeCommandeClient.fxml"));
                  Pane autreInterface = loader.load();

              Region parent = (Region) loader.getRoot();
              parent.prefWidthProperty().bind(PaneContent.widthProperty());
              parent.prefHeightProperty().bind(PaneContent.heightProperty());

                  PaneContent.getChildren().setAll(autreInterface);
                  ListeCommandeClientController controller = loader.getController();
                  controller.PanePasserCommande.toFront();




              } catch (IOException e) {
                  e.printStackTrace();

              } 
      }else{
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Connexion");
                                alert.setHeaderText("");
                                alert.setContentText("Vous devez étre connecté");
                                Font font = Font.font("Verdana",FontWeight.BOLD, 16);

                            ButtonType buttonTypeYes = new ButtonType("Se connecter");
                            ButtonType buttonTypeNo = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
                            
                            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
                            alert.getDialogPane().setStyle("-fx-background-color: #FFFFFF;");
                            Button buttonYes = (Button) alert.getDialogPane().lookupButton(buttonTypeYes);
                            Button buttonNo = (Button) alert.getDialogPane().lookupButton(buttonTypeNo);
                            

                                                buttonYes.setStyle("-fx-text-fill:#ffffff; -fx-background-color: #1372f4; -fx-background-radius: 25px;"
                                                        + " -fx-min-width: 130px;\n" +
                                    "    -fx-max-width: 130px;\n" +
                                    "    -fx-min-height: 40px;\n" +
                                    "    -fx-max-height: 40px;");
                                                buttonNo.setStyle("-fx-text-fill:#ffffff; -fx-background-color: #f00020; -fx-background-radius: 25px;"
                                                        + " -fx-min-width: 130px;\n" +
                                    "    -fx-max-width: 130px;\n" +
                                    "    -fx-min-height: 40px;\n" +
                                    "    -fx-max-height: 40px;");
                                                //buttonCancel.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
                                                    alert.setContentText(null);
                                                    Label headerLabel1 = new Label("Vous devez étre connecté");
                                                    headerLabel1.setFont(font);
                                                    alert.getDialogPane().setContent(headerLabel1);

                                                Optional<ButtonType> result = alert.showAndWait();
                                                    
        if (result.isPresent() && result.get() == buttonTypeYes){
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/login.fxml"));
            Pane autreInterface = loader.load();
            
            Region parent = (Region) loader.getRoot();
            
            parent.prefWidthProperty().bind(PaneContent.widthProperty());
            parent.prefHeightProperty().bind(PaneContent.heightProperty());
            PaneContent.getChildren().setAll(autreInterface);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        } 
      
      }
    }
@FXML
    private void ListReservation(ActionEvent event) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/Reservation.fxml"));
            Pane autreInterface = loader.load();

         Region parent = (Region) loader.getRoot();

            parent.prefWidthProperty().bind(PaneContent.widthProperty());
            parent.prefHeightProperty().bind(PaneContent.heightProperty());
            PaneContent.getChildren().setAll(autreInterface);
    }


///
  ///creation carteProdui de panier
  public HBox createCarteProduit(Produit produit){
      HBox hboxEntry =new HBox();  
      try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/CartPanier.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            CartPanierController cartPanierController = fxmlLoader.getController();
            cartPanierController.SetData(produit);
            
            
            hboxEntry.getChildren().add(anchorPane);
            
            VBox VboxBtn=creatHboxBtn(produit);
            
            hboxEntry.getChildren().add(VboxBtn);
            hboxEntry.setStyle("-fx-background-color: #D3D3D3;");
            hboxEntry.setId("HboxCarte");
            mapHashbox.put(produit, hboxEntry);
            
        } catch (IOException ex) {
            Logger.getLogger(DashbordFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hboxEntry;
}
  ////Ajouter un produit au panier
  
  public void AjouterProduitPanier(Produit produit) throws IOException {
 
      VBoxPanier.getChildren().clear();
      
      if(PanierSession.getPanier().containsKey(produit))
      {
          PanierSession.getInstance().addProduct(produit);
          LabelPrixTotal.setText(String.format("%.2f", PanierSession.getInstance().calculTotale())+" DT");
          System.out.println("le produit est deja dans le panier");
          mapHashbox.get(produit);
          HBox hboxProduit = mapHashbox.get(produit);

// Récupération de la VBox dans le HBox
            VBox vboxProduit = (VBox) hboxProduit.lookup("#VboxBtn");

// Récupération de la HBox interne dans la VBox
            //HBox hboxInterne = (HBox) vboxProduit.lookup("#maHBox");

// Récupération de la Label dans la HBox interne
            Label labelProduit = (Label) vboxProduit.lookup("#labelQu");
            labelProduit.setText(PanierSession.getPanier().get(produit).toString());
      }
      else{
          PanierSession.getInstance().addProduct(produit);
          LabelPrixTotal.setText(String.format("%.2f", PanierSession.getInstance().calculTotale())+" DT");
          createCarteProduit(produit);
          System.out.println("le produit n'est pas dans le panier");
      }
      System.out.println(mapHashbox.toString());
      for(Produit prod :mapHashbox.keySet()){
        
        VBoxPanier.getChildren().add(mapHashbox.get(prod));

    }
    AnchorPane anchorPaneLast = new AnchorPane();
    anchorPaneLast.setPrefWidth(152);
    anchorPaneLast.setPrefHeight(278);
    VBoxPanier.getChildren().add(anchorPaneLast);
    
 }

    public void reloadPanier(){
      
    if(PanierSession.getPanier().isEmpty()){
        btnPasserCommande.setVisible(false);
    }else{
        btnPasserCommande.setVisible(true);
    }
      System.out.println(mapHashbox.toString());
  VBoxPanier.getChildren().clear();
        for(Produit prod :mapHashbox.keySet()){
        
        VBoxPanier.getChildren().add(mapHashbox.get(prod));

    }
    AnchorPane anchorPaneLast = new AnchorPane();
    anchorPaneLast.setPrefWidth(152);
    anchorPaneLast.setPrefHeight(278);
    VBoxPanier.getChildren().add(anchorPaneLast);
    LabelPrixTotal.setText(String.format("%.2f", PanierSession.getInstance().calculTotale())+" DT");
    
    

       

    
  }



    @FXML
    private void login(ActionEvent event) {
 
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/login.fxml"));
            Pane autreInterface = loader.load();
            
            Region parent = (Region) loader.getRoot();
            
            parent.prefWidthProperty().bind(PaneContent.widthProperty());
            parent.prefHeightProperty().bind(PaneContent.heightProperty());
            PaneContent.getChildren().setAll(autreInterface);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void CosulterActivite(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/AffichageActiviteFront.fxml"));
            Pane autreInterface = loader.load();
            
            Region parent = (Region) loader.getRoot();
            
            parent.prefWidthProperty().bind(PaneContent.widthProperty());
            parent.prefHeightProperty().bind(PaneContent.heightProperty());
            PaneContent.getChildren().setAll(autreInterface);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    @FXML
    private void ConsulterCategory(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/Frontmag.fxml"));
            Pane autreInterface = loader.load();
            
            Region parent = (Region) loader.getRoot();
            
            parent.prefWidthProperty().bind(PaneContent.widthProperty());
            parent.prefHeightProperty().bind(PaneContent.heightProperty());
            PaneContent.getChildren().setAll(autreInterface);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
