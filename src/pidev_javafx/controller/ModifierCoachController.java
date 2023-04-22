/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev_javafx.entitie.Category;
import pidev_javafx.entitie.Coach;
import pidev_javafx.service.CoachService;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author mmarr
 */
public class ModifierCoachController implements Initializable {

    @FXML
    private TextField textfield_nom_modifier;
    @FXML
    private TextField textfield_age_modifier;
    @FXML
    private Button btn_modifierCoach;
    CoachService cs;
    Coach c1;
    @FXML
    private TextField image_coach_modif_field;
    @FXML
    private Button image_coach_modif;
     private File selectedFile = null;
    @FXML
    private Label erreur_nom_modif;
    @FXML
    private Label erreur_age_modif;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cs = new CoachService();
    }    
    
    public void recupData(Coach t){
       textfield_nom_modifier.setText(t.getNom_coach());
       String chara = Integer.toString(t.getAge_coach());
       textfield_age_modifier.setText(chara);
       image_coach_modif_field.setText(t.getImage());
       c1=t;
        System.out.println(t.getImage());
    }
    
    @FXML
    private void ModifierCoachOnClick(ActionEvent event) throws IOException {
                erreur_age_modif.setText("");
        erreur_nom_modif.setText("");
         if(!textfield_age_modifier.getText().isEmpty()&&!textfield_nom_modifier.getText().isEmpty()&&Integer.parseInt(textfield_age_modifier.getText())>=18&&textfield_nom_modifier.getText().matches("[a-zA-Z ]+")){
         if(!image_coach_modif_field.getText().equals(c1.getImage())){
            int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
            String newFileName = random_int+"-"+selectedFile.getName();
            Coach c2=new Coach(c1.getId(),Integer.parseInt(textfield_age_modifier.getText()),textfield_nom_modifier.getText(),newFileName);
            System.out.println(c2);
            cs.modifier(c2);
            Path sourceFile = Paths.get(selectedFile.toPath().toString());
            Path targetFile = Paths.get(Statics.uploadDirectory+newFileName);

            Files.copy(sourceFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
        }else{
        Coach c=new Coach(c1.getId(),Integer.parseInt(textfield_age_modifier.getText()),textfield_nom_modifier.getText());
        cs.modifier(c);
    }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/AfficherCoach.fxml"));
        Parent root = loader.load();
        btn_modifierCoach.getScene().setRoot(root);
         }
         else{
             if(textfield_age_modifier.getText().isEmpty()){
                erreur_age_modif.setText("l'age est obligatoire");
            }
            if(textfield_nom_modifier.getText().isEmpty()){
                erreur_nom_modif.setText("le nom est obligatoire");
            }
            if(Integer.parseInt(textfield_age_modifier.getText())<18){
                erreur_age_modif.setText("l'age doit être superieur à 18");
            }
            if(!textfield_nom_modifier.getText().matches("[a-zA-Z ]+")){
                erreur_nom_modif.setText("le nom doit être alphabetique");
            }
         }
    }

    @FXML
    private void image_coach_modifOnClick(ActionEvent event) {
              final FileChooser fileChooser = new FileChooser(); //outil eli nekhdhou bih el fichier
        final Stage stage = null;// el fenetre eli bech tethal 

        File file = fileChooser.showOpenDialog(stage); //halina el fenetre w recuperina el fichier
        if (file != null) { //ntestiow est ce que fichier null wale
            //Image image1 = new Image(file.toURI().toString());
            //addImage.setImage(image1);//badalna el image
            image_coach_modif_field.setText(file.getName()); //badalna el input
            selectedFile = file;
    }
    }
    
}
