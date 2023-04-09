/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pidev_javafx.entitie.Activite;
import pidev_javafx.entitie.Participation;
import pidev_javafx.service.ActiviteService;
import pidev_javafx.service.ParticipationService;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author mmarr
 */
public class AffichageActiviteFrontController implements Initializable {

    @FXML
    private FlowPane flowpane_front;
ActiviteService cs;
ParticipationService ps;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       ObservableList<Activite>listAc=FXCollections.observableArrayList();
        ActiviteService cs=new ActiviteService();
        ParticipationService ps=new ParticipationService();
        listAc=cs.afficher();
        flowpane_front.setOrientation(Orientation.HORIZONTAL);
            flowpane_front.setAlignment(Pos.CENTER);
            flowpane_front.setHgap(10);
            flowpane_front.setVgap(10);
            
        for(Activite a:listAc)
        {
            
            VBox v1=new VBox();
            /*v1.setMinHeight(210);
            v1.setMaxHeight(210);
            v1.setMinWidth(210);
            v1.setMaxWidth(210);*/
            v1.setPrefSize(300, 350);
            
            v1.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
            ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory+a.getImage())));
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
            Label nom_act=new Label(a.getNomActivite());
            nom_act.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            nom_act.setAlignment(Pos.CENTER);
            v1.getChildren().add(nom_act);
                   Label nom_act3=new Label("La date: "+a.getDateActivite());
            nom_act3.setFont(Font.font("Verdana",FontWeight.NORMAL, 16));
            nom_act3.setAlignment(Pos.CENTER);
            v1.getChildren().add(nom_act3);
            Label nom_act2=new Label("\n");
            v1.getChildren().add(nom_act2);

            Button btn=new Button("Participer");
            btn.setOnAction(e->{
                LocalDate today=LocalDate.now();
                Date d=java.sql.Date.valueOf(today);
                Participation p = new Participation(126,d,a);
                ps.ajouter(p);
            });
            btn.setStyle("-fx-text-fill:#ffffff; -fx-background-color: #1372f4; -fx-background-radius: 25px;"
                    + " -fx-min-width: 130px;\n" +
"    -fx-max-width: 130px;\n" +
"    -fx-min-height: 40px;\n" +
"    -fx-max-height: 40px;");
            v1.getChildren().add(btn);
            flowpane_front.getChildren().add(v1);
            flowpane_front.setMargin(v1,new Insets(5,5,5,5));

        }

    }    
    
}
