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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev_javafx.entitie.Coach;
import pidev_javafx.service.CoachService;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author mmarr
 */
public class CoachController implements Initializable {

    @FXML
    private TextField textfield_nom_coach;
    @FXML
    private TextField textfield_age_coach;
    @FXML
    private Button btn_AjoutCoach;
    private File selectedFile = null;
    CoachService cs;
    @FXML
    private TextField image_coach_field;
    @FXML
    private Button image_coach_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cs=new CoachService();
    }    

    @FXML
    private void addCoachOnClick(ActionEvent event) throws IOException {
         int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
        String newFileName = random_int+"-"+selectedFile.getName();
        Coach c=new Coach(Integer.parseInt(textfield_age_coach.getText()), textfield_nom_coach.getText(),newFileName);
        cs.ajouter(c);
        Path sourceFile = Paths.get(selectedFile.toPath().toString());
        Path targetFile = Paths.get(Statics.uploadDirectory+newFileName);

        Files.copy(sourceFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/AfficherCoach.fxml"));
        Parent root = loader.load();
        btn_AjoutCoach.getScene().setRoot(root);
        /* try {
            Parent root = loader.load();
           // AfficherCoachController cc = loader.getController();
            btn_AjoutCoach.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
    }

    @FXML
    private void image_coach_btnOnClick(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser(); //outil eli nekhdhou bih el fichier
        final Stage stage = null;// el fenetre eli bech tethal 

        File file = fileChooser.showOpenDialog(stage); //halina el fenetre w recuperina el fichier
        if (file != null) { //ntestiow est ce que fichier null wale
            //Image image1 = new Image(file.toURI().toString());
            //addImage.setImage(image1);//badalna el image
            image_coach_field.setText(file.getName()); //badalna el input
            selectedFile = file;
        }
    }
    
}
