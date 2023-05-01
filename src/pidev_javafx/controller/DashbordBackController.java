/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;

import javafx.scene.control.Label;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;
import pidev_javafx.service.SessionManager;

/**
 * FXML Controller class
 *
 * @author marni
 */
public class DashbordBackController implements Initializable {

    @FXML
    private ImageView profile;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private ImageView menu;
    @FXML
    public AnchorPane PaneContent;
    @FXML
    private JFXButton btnListeCommande;
    @FXML
    private JFXButton BtnAB;
    @FXML
    private JFXButton StatAbDa;
    @FXML
    private AnchorPane paneProfil;
    @FXML
    private Label LabelAdresse;
    @FXML
    private Label LabelNomPrenom;
    @FXML
    private JFXButton consultProfilButton;
    @FXML
    private JFXButton consultProfilButton1;
    
        private Stage stage;
    private  Parent root;
    private Scene scene;
    @FXML
    private JFXButton btnCoach;
    @FXML
    private JFXButton btnActivite;
    @FXML
    private JFXButton btnProduits;
    @FXML
    private JFXButton btnCategory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LabelAdresse.setText(SessionManager.getEmail());
        LabelNomPrenom.setText(SessionManager.getNom()+ " " + SessionManager.getPrenom());
        
        /////////all Slide Bar//////////       
pane1.setVisible(false);
hideMenuPane();
hideProfilPane();

menu.setOnMouseClicked(event -> {
 
    showMenuPane();

});

profile.setOnMouseClicked(event -> {
    showProfilPane();
    
});

pane1.setOnMouseClicked(event -> {
    hidePane1();
    if (pane2.getTranslateX() == 0) {
        hideMenuPane();
    }else  if (paneProfil.getTranslateX() == 0) {
        hideProfilPane();
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

private void showProfilPane() {
    pane1.setVisible(true);
    FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), pane1);
    fadeTransition1.setFromValue(0);
    fadeTransition1.setToValue(0.15);
    fadeTransition1.play();
    TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), paneProfil);
    translateTransition1.setByY(-1000);
    translateTransition1.play();
}

private void hideMenuPane() {
    hidePane1();
    TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5), pane2);
    translateTransition2.setByX(-600);
    translateTransition2.play();
}

private void hideProfilPane() {
    hidePane1();
    TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), paneProfil);
    translateTransition1.setByY(+1000);
    translateTransition1.play();
}    

    @FXML
    private void ListeCommandeBack(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/ListeCommandeback.fxml"));
            Pane autreInterface = loader.load();
            Region parent = (Region) loader.getRoot();
            parent.prefWidthProperty().bind(PaneContent.widthProperty());
            parent.prefHeightProperty().bind(PaneContent.heightProperty());
            PaneContent.getChildren().setAll(autreInterface);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



@FXML
private void Listabonnement(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/HomeAbo.fxml"));
        Pane autreInterface = loader.load();
        Region parent = (Region) loader.getRoot();
        parent.prefWidthProperty().bind(PaneContent.widthProperty());
        parent.prefHeightProperty().bind(PaneContent.heightProperty());
        PaneContent.getChildren().setAll(autreInterface);
        
    } catch (IOException e) {
        e.printStackTrace();

    }
}





    @FXML
    private void StatAboDashboard(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/StatsAbo.fxml"));
        Pane autreInterface = loader.load();
        Region parent = (Region) loader.getRoot();
        parent.prefWidthProperty().bind(PaneContent.widthProperty());
        parent.prefHeightProperty().bind(PaneContent.heightProperty());
        PaneContent.getChildren().setAll(autreInterface);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

      @FXML
    private void GestionUtilisateurs(ActionEvent event) {
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/affichage.fxml"));
            Pane autreInterface = loader.load();
            PaneContent.getChildren().setAll(autreInterface);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void consulterProfil(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/myProfil.fxml"));
            Pane autreInterface = loader.load();
            PaneContent.getChildren().setAll(autreInterface);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logoutBack(ActionEvent event) {
     try {
            root = FXMLLoader.load(getClass().getResource("/pidev_javafx/gui/Acceuil.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            SessionManager.EndSession();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void aiderClient(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/support.fxml"));
            Pane autreInterface = loader.load();
            
        Insets insets = new Insets(0);
        autreInterface.setPadding(insets);
            
            PaneContent.getChildren().setAll(autreInterface);
            PaneContent.setPadding(insets);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }

    @FXML
    private void ConsulterCoach(ActionEvent event) {
      
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/CRUD_Coach.fxml"));
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
    private void ConsulterActivite(ActionEvent event) {
              
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/CRUD_Activite.fxml"));
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
    private void ConsulterProduit(ActionEvent event) {
                     
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/AllProduct.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/AllCategory.fxml"));
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
    private void AdminReclamation(ActionEvent event) {
                      
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/FXMLreclamationadmin.fxml"));
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
