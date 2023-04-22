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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//import java.util.Date;
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
public class AjoutActiviteController implements Initializable {

    @FXML
    private TextField nom_activite_ajout_field;
    @FXML
    private TextField description_activite_ajout_field;
    @FXML
    private TextField nbreplaces_activite_ajout_field;
    @FXML
    private TextField image_activite_ajout_field;
    @FXML
    private Button btn_image_activite_ajout;
    
        private File selectedFile = null;
    ActiviteService cs;
    CoachService cs1;
    @FXML
    private ComboBox<String> comb;
    @FXML
    private JFXDatePicker date_activite;
    @FXML
    private JFXTimePicker deb_activite;
    @FXML
    private JFXTimePicker fin_activite;
    @FXML
    private Button btn_retour_activite;
    @FXML
    private Label erreur_nom_activite;
    @FXML
    private Label erreur_description_activite;
    @FXML
    private Label erreur_nbre_place;
    @FXML
    private Label erreur_date_act;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        date_activite.setValue(LocalDate.now());
        deb_activite.setValue(LocalTime.now());
        fin_activite.setValue(LocalTime.now());
        nbreplaces_activite_ajout_field.setText("0");
        cs=new ActiviteService();
        cs1 = new CoachService();
        ObservableList<Coach> l= cs1.afficher();
        List<String> nom_id = new ArrayList<>();
            for (Coach c : l) {
                String s=c.getId()+":"+c.getNom_coach() ;
                 nom_id.add(s);
            }
            System.out.println(nom_id);
        comb.setItems(FXCollections.observableList(nom_id));

    }    

    @FXML
    private void image_activite_ajoutOnClick(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser(); //outil eli nekhdhou bih el fichier
        final Stage stage = null;// el fenetre eli bech tethal 
        

        File file = fileChooser.showOpenDialog(stage); //halina el fenetre w recuperina el fichier
        if (file != null) { //ntestiow est ce que fichier null wale
            //Image image1 = new Image(file.toURI().toString());
            //addImage.setImage(image1);//badalna el image
            image_activite_ajout_field.setText(file.getName()); //badalna el input
            selectedFile = file;
        }
    }

    @FXML
    private void Ajouter_activiteOnClick(ActionEvent event) throws IOException {
                erreur_description_activite.setText("");
        erreur_nbre_place.setText("");
        erreur_nom_activite.setText("");
         erreur_date_act.setText("");
         LocalDate date=date_activite.getValue();
        if(!nom_activite_ajout_field.getText().isEmpty()&&!description_activite_ajout_field.getText().isEmpty()&&!nbreplaces_activite_ajout_field.getText().isEmpty()&&nom_activite_ajout_field.getText().matches("[a-zA-Z ]+")&&description_activite_ajout_field.getText().matches("[a-zA-Z0-9 ]+")&&Integer.parseInt(nbreplaces_activite_ajout_field.getText())>0&&date.isAfter(LocalDate.now())){
        int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
        String newFileName = random_int+"-"+selectedFile.getName();
        String chaine = comb.getValue();
        int index = chaine.indexOf(":");
        String sousChaine = chaine.substring(0, index);
        System.out.println(sousChaine);
        Coach cat1=cs1.getCoachById(Integer.parseInt(sousChaine));
        Date d=java.sql.Date.valueOf(date_activite.getValue());
        //Date t1=java.sql.Date.valueOf(deb_activite.getValue());
        Time t1=java.sql.Time.valueOf(deb_activite.getValue());
        Time t2=java.sql.Time.valueOf(fin_activite.getValue());
        Activite a=new Activite(Integer.parseInt(nbreplaces_activite_ajout_field.getText()), nom_activite_ajout_field.getText(),description_activite_ajout_field.getText(),newFileName,d,t1,t2,cat1);
        cs.ajouter(a);
        Path sourceFile = Paths.get(selectedFile.toPath().toString());
        Path targetFile = Paths.get(Statics.uploadDirectory+newFileName);
        

        Files.copy(sourceFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/AffichageActivite.fxml"));
        Parent root = loader.load();
        btn_image_activite_ajout.getScene().setRoot(root);}
        else{
            if(!date.isAfter(LocalDate.now())){
                erreur_date_act.setText("la date doit être supérieure à celle d'aujourd'hui");
            }
            if(nom_activite_ajout_field.getText().isEmpty()){
            erreur_nom_activite.setText("Le nom est obligatoire");}
            if(description_activite_ajout_field.getText().isEmpty()){
            erreur_description_activite.setText("La description est obligatoire");}
            if(Integer.parseInt(nbreplaces_activite_ajout_field.getText())==0){
            erreur_nbre_place.setText("Le nombre de places est obligatoire");}
            if(!nom_activite_ajout_field.getText().matches("[a-zA-Z ]+")){
              erreur_nom_activite.setText("le nom doit contenir que des alphabets");
            }
            if(!description_activite_ajout_field.getText().matches("[a-zA-Z0-9 ]+")){
              erreur_description_activite.setText("la description ne doit pas contenir des caracteres speciaux");
            }
            if(Integer.parseInt(nbreplaces_activite_ajout_field.getText())<0){
               erreur_nbre_place.setText("le nombre de places doit être positif");
            }

        }
    }

    private void testOnClick(ActionEvent event) {
        
        System.out.println(date_activite.getValue());
        System.out.println(deb_activite.getValue());
    }

    @FXML
    private void retour_activiteOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/AffichageActivite.fxml"));
        Parent root = loader.load();
        btn_retour_activite.getScene().setRoot(root);
    }
    
}
