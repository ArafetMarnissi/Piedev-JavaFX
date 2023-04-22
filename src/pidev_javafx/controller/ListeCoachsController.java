/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

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
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pidev_javafx.entitie.Activite;
import pidev_javafx.entitie.Coach;
import pidev_javafx.service.ActiviteService;
import pidev_javafx.service.CoachService;
import pidev_javafx.service.ParticipationService;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author mmarr
 */
public class ListeCoachsController implements Initializable {

    @FXML
    private FlowPane flow_pane_coachs;
    ActiviteService as;
    CoachService cs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Coach>listAc=FXCollections.observableArrayList();
        ActiviteService as=new ActiviteService();
        CoachService cs=new CoachService();
        listAc=cs.afficher();
        flow_pane_coachs.setOrientation(Orientation.HORIZONTAL);
            flow_pane_coachs.setAlignment(Pos.CENTER);
            flow_pane_coachs.setHgap(10);
            flow_pane_coachs.setVgap(10);
            
        for(Coach c:listAc){
            VBox v1=new VBox();
            /*v1.setMinHeight(210);
            v1.setMaxHeight(210);
            v1.setMinWidth(210);
            v1.setMaxWidth(210);*/
            v1.setPrefSize(300, 350);
            
            v1.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
            ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory+c.getImage())));
                imageView.setFitWidth(300);
                imageView.setFitHeight(300);
                imageView.setPreserveRatio(true);
                imageView.setStyle("-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
                v1.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
            Label nom_act1=new Label("\n");
            v1.getChildren().add(nom_act1);
            Label nom_act=new Label(c.getNom_coach());
            nom_act.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            nom_act.setAlignment(Pos.CENTER);
            v1.getChildren().add(nom_act);
            
            v1.setOnMouseClicked(e -> {
                try{
                 /*ObservableList<Activite>listAc1=FXCollections.observableArrayList();
                    listAc1=as.findActsByCoach(c.getId());*/
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/ListeActsByCoach.fxml"));
                        Parent root = loader.load();
                        ListeActsByCoachController mcc = loader.getController();
                        mcc.recupDataD(c.getId());
                        flow_pane_coachs.getScene().setRoot(root);
                        
                    }catch(IOException ex){
                        Logger.getLogger(AffichageActiviteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            });
                        flow_pane_coachs.getChildren().add(v1);
            flow_pane_coachs.setMargin(v1,new Insets(5,5,5,5));
        }
    }    
    
}
