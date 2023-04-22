/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev_javafx.entitie.Activite;
import pidev_javafx.entitie.Coach;
import pidev_javafx.service.ActiviteService;
import pidev_javafx.service.CoachService;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author mmarr
 */
public class ModifierActiviteController implements Initializable {

    @FXML
    private TextField nom_activite_modif_field;
    @FXML
    private TextField description_activite_modif_field;
    @FXML
    private TextField nbreplace_activite_modif;
    @FXML
    private TextField image_activite_modif;
    @FXML
    private Button btn_image_activite_modif;
    @FXML
    private Button btn_modifier_activite;
    private File selectedFile = null;
        ActiviteService cs;
        CoachService cs1;
    Activite c1;
    @FXML
    private ComboBox<String> comb1;
    @FXML
    private JFXDatePicker date_activite_modif;
    @FXML
    private JFXTimePicker deb_activite_modif;
    @FXML
    private JFXTimePicker fin_activite_modif;
    @FXML
    private Button retour_activite_modif;
    @FXML
    private Label erreur_nom_act_modif;
    @FXML
    private Label erreur_description_modif;
    @FXML
    private Label erreur_nbreplace_modif;
    @FXML
    private Label erreur_date_modif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         cs = new ActiviteService();
          cs1 = new CoachService();
        ObservableList<Coach> l= cs1.afficher();
        List<String> nom_id = new ArrayList<>();
            for (Coach c : l) {
                String s=c.getId()+":"+c.getNom_coach() ;
                 nom_id.add(s);
            }
            System.out.println(nom_id);
        comb1.setItems(FXCollections.observableList(nom_id));
    }  
    
        public void recupData(Activite t){
       nom_activite_modif_field.setText(t.getNomActivite());
       description_activite_modif_field.setText(t.getDescriptionActivite());
       String chara = Integer.toString(t.getNbrePlace());
       nbreplace_activite_modif.setText(chara);
       image_activite_modif.setText(t.getImage());
       date_activite_modif.setValue(t.getDateActivite().toLocalDate());
       deb_activite_modif.setValue(t.getTimeActivite().toLocalTime());
       fin_activite_modif.setValue(t.getEnd().toLocalTime());
       c1=t;
            //System.out.println(t);
    }

    @FXML
    private void image_activite_modifOnClick(ActionEvent event){
                      final FileChooser fileChooser = new FileChooser(); //outil eli nekhdhou bih el fichier
        final Stage stage = null;// el fenetre eli bech tethal 

        File file = fileChooser.showOpenDialog(stage); //halina el fenetre w recuperina el fichier
        if (file != null) { //ntestiow est ce que fichier null wale
            //Image image1 = new Image(file.toURI().toString());
            //addImage.setImage(image1);//badalna el image
            image_activite_modif.setText(file.getName()); //badalna el input
            selectedFile = file;
    }
 
    }

    @FXML
    private void modifier_activiteOnClick(ActionEvent event) throws IOException {
                        erreur_description_modif.setText("");
        erreur_nbreplace_modif.setText("");
        erreur_nom_act_modif.setText("");
         erreur_date_modif.setText("");
         LocalDate date=date_activite_modif.getValue();
         if(!nom_activite_modif_field.getText().isEmpty()&&!description_activite_modif_field.getText().isEmpty()&&!nbreplace_activite_modif.getText().isEmpty()&&nom_activite_modif_field.getText().matches("[a-zA-Z ]+")&&description_activite_modif_field.getText().matches("[a-zA-Z0-9 ]+")&&Integer.parseInt(nbreplace_activite_modif.getText())>0&&date.isAfter(LocalDate.now())){
     if(!image_activite_modif.getText().equals(c1.getImage())){
            int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
            String newFileName = random_int+"-"+selectedFile.getName();
            String chaine = comb1.getValue();
        int index = chaine.indexOf(":");
        String sousChaine = chaine.substring(0, index);
        System.out.println(sousChaine);
        Coach cat1=cs1.getCoachById(Integer.parseInt(sousChaine));
 Date d=java.sql.Date.valueOf(date_activite_modif.getValue());
        //Date t1=java.sql.Date.valueOf(deb_activite.getValue());
        Time t1=java.sql.Time.valueOf(deb_activite_modif.getValue());
        Time t2=java.sql.Time.valueOf(fin_activite_modif.getValue());
            Activite c2=new Activite(c1.getId(),Integer.parseInt(nbreplace_activite_modif.getText()),nom_activite_modif_field.getText(),description_activite_modif_field.getText(),newFileName,d,t1,t2,cat1);
            System.out.println(c2);
            cs.modifier(c2);
            Path sourceFile = Paths.get(selectedFile.toPath().toString());
            Path targetFile = Paths.get(Statics.uploadDirectory+newFileName);

            Files.copy(sourceFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
        }else{
                            String chaine = comb1.getValue();
        int index = chaine.indexOf(":");
        String sousChaine = chaine.substring(0, index);
        System.out.println(sousChaine);
        Coach cat2=cs1.getCoachById(Integer.parseInt(sousChaine));
         Date d=java.sql.Date.valueOf(date_activite_modif.getValue());
        //Date t1=java.sql.Date.valueOf(deb_activite.getValue());
        Time t1=java.sql.Time.valueOf(deb_activite_modif.getValue());
        Time t2=java.sql.Time.valueOf(fin_activite_modif.getValue());
        Activite c=new Activite(c1.getId(),Integer.parseInt(nbreplace_activite_modif.getText()),nom_activite_modif_field.getText(),description_activite_modif_field.getText(),c1.getImage(),d,t1,t2,cat2);
        cs.modifier(c);
    }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/AffichageActivite.fxml"));
        Parent root = loader.load();
        btn_modifier_activite.getScene().setRoot(root);
         }else{
            if(!date.isAfter(LocalDate.now())){
                erreur_date_modif.setText("la date doit être supérieure à celle d'aujourd'hui");
            }
           if(nom_activite_modif_field.getText().isEmpty()){
            erreur_nom_act_modif.setText("Le nom est obligatoire");}
            if(description_activite_modif_field.getText().isEmpty()){
            erreur_description_modif.setText("La description est obligatoire");}
            if(nbreplace_activite_modif.getText().isEmpty()){
            erreur_nbreplace_modif.setText("Le nombre de places est obligatoire");}
            if(!nom_activite_modif_field.getText().matches("[a-zA-Z ]+")){
              erreur_nom_act_modif.setText("le nom doit contenir que des alphabets");
            }
            if(!description_activite_modif_field.getText().matches("[a-zA-Z0-9 ]+")){
              erreur_description_modif.setText("la description ne doit pas contenir des caracteres speciaux");
            }
            if(Integer.parseInt(nbreplace_activite_modif.getText())<=0){
               erreur_nbreplace_modif.setText("le nombre de places doit être positif");
            }

         }
    }

    @FXML
    private void retour_act_modifOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/AffichageActivite.fxml"));
        Parent root = loader.load();
        retour_activite_modif.getScene().setRoot(root);
    }
    
}
