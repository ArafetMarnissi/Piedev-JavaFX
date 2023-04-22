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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pidev_javafx.entitie.Activite;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author mmarr
 */
public class DetailsActiviteFrontController implements Initializable {

    @FXML
    private VBox v2;
    @FXML
    private ImageView image_act_details;
    @FXML
    private Label nom_act_details;
    @FXML
    private Label espace;
    @FXML
    private Label desc_act_details;
    @FXML
    private ImageView image_qrcode;
    @FXML
    private Label espace1;
    @FXML
    private Label espace2;
    @FXML
    private Label espace4;
    @FXML
    private Button btn_retour_details;
    @FXML
    private ImageView image_coach_detail;
    @FXML
    private Label espace_c1;
    @FXML
    private Label espace_c2;
    @FXML
    private Label age_coach;
    @FXML
    private Label espace_c3;
    @FXML
    private Label date_act_vbox;
    @FXML
    private Label espace_c4;
    @FXML
    private Label deb_act_detail;
    @FXML
    private Label espace_c5;
    @FXML
    private Label fin_act_detail;
    @FXML
    private Label espace_c6;
    @FXML
    private ImageView image_qrcode_coach;
    @FXML
    private Label nom_coach;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
        public void recupDataD(Activite t,String s) throws FileNotFoundException{
             LocalDate ld=t.getDateActivite().toLocalDate();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
                        String formattedDate = ld.format(formatter);
       nom_act_details.setText(t.getNomActivite());
       desc_act_details.setText(t.getDescriptionActivite());
       nom_coach.setText(t.getCoach().getNom_coach());
       age_coach.setText("Coach");
       date_act_vbox.setText("Date: "+formattedDate);
       deb_act_detail.setText("Commence à: "+String.valueOf(t.getTimeActivite()));
       fin_act_detail.setText("Se termine à: "+String.valueOf(t.getEnd()));
       
      espace.setText("\n");
       espace1.setText("\n");
       espace2.setText("\n");
       espace4.setText("\n");
       espace_c1.setText("\n");
       espace_c2.setText("\n");
       espace_c3.setText("\n");
       espace_c4.setText("\n");
       espace_c5.setText("\n");
       espace_c6.setText("\n");
       
       /*String chara = Integer.toString(t.getNbrePlace());
       nbreplace_activite_modif.setText(chara);*/
            System.out.println(t.getCoach());
       image_act_details.setImage(new Image(new FileInputStream(Statics.uploadDirectory+t.getImage())));
       //image_qrcode.setImage(new Image(new FileInputStream(Statics.uploadDirectory1+s)));
       image_qrcode_coach.setImage(new Image(new FileInputStream(Statics.uploadDirectory1+s)));
       image_coach_detail.setImage(new Image(new FileInputStream(Statics.uploadDirectory+t.getCoach().getImage())));
       
            //System.out.println(t);
        image_act_details.setFitHeight(1550);
        image_act_details.setFitWidth(850);
        image_act_details.setStyle("-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
        nom_act_details.setFont(Font.font("Verdana",FontWeight.BOLD, 24));
        nom_act_details.setAlignment(Pos.CENTER);
        desc_act_details.setFont(Font.font("Verdana",FontWeight.NORMAL, 20));
        desc_act_details.setAlignment(Pos.CENTER);
        nom_coach.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
        nom_coach.setAlignment(Pos.CENTER);
        age_coach.setFont(Font.font("Verdana",FontWeight.NORMAL, 14));
        age_coach.setAlignment(Pos.CENTER);
        date_act_vbox.setFont(Font.font("Verdana",FontWeight.NORMAL, 16));
        date_act_vbox.setAlignment(Pos.CENTER);
        deb_act_detail.setFont(Font.font("Verdana",FontWeight.NORMAL, 16));
        deb_act_detail.setAlignment(Pos.CENTER);
        fin_act_detail.setFont(Font.font("Verdana",FontWeight.NORMAL, 16));
        fin_act_detail.setAlignment(Pos.CENTER);
        
    }    

    @FXML
    private void Retour_detailsOnClick(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/AffichageActiviteFront.fxml"));
        Parent root = loader.load();
        btn_retour_details.getScene().setRoot(root);
    }
    
}
