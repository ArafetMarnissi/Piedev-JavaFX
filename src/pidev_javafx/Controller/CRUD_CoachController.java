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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
public class CRUD_CoachController implements Initializable {

    @FXML
    private AnchorPane anchorPane_affich_coach;
    @FXML
    private TextField recherche_textfield_coach;
    @FXML
    private TableView<Coach> table_Coachs;
    @FXML
    private TableColumn<Coach, String> colonne_nom;
    @FXML
    private TableColumn<Coach, Integer> colonne_age;
    @FXML
    private Button btn_SupprimerCoach;
    @FXML
    private Button btn_ModifierCoach1;
    @FXML
    private Button btn_ajouter_coach;
    @FXML
    private AnchorPane anchorpane_ajout_coach;
    @FXML
    private TextField textfield_nom_coach;
    @FXML
    private TextField textfield_age_coach;
    @FXML
    private Button btn_AjoutCoach;
    @FXML
    private TextField image_coach_field;
    @FXML
    private Button image_coach_btn;
    @FXML
    private Label erreur_nom_coach;
    @FXML
    private Label erreur_age_coach;
    @FXML
    private AnchorPane anchorpane_modif_coach;
    @FXML
    private TextField textfield_nom_modifier;
    @FXML
    private TextField textfield_age_modifier;
    @FXML
    private TextField image_coach_modif_field;
    @FXML
    private Button image_coach_modif;
    @FXML
    private Label erreur_nom_modif;
    @FXML
    private Label erreur_age_modif;
    CoachService cs= new CoachService();
    private File selectedFile = null;
    Coach c1;
    @FXML
    private Button btn_modifierCoach;
    @FXML
    private Label erreur_age_coach1;
    @FXML
    private Label erreur_age_coach11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textfield_age_coach.setText("0");
        anchorPane_affich_coach.toFront();
                //id.setCellValueFactory(new PropertyValueFactory<Coach,Integer>("id"));
        colonne_nom.setCellValueFactory(new PropertyValueFactory<Coach, String>("nom_coach"));
        colonne_age.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("age_coach"));
        ObservableList<Coach>listCoachs=FXCollections.observableArrayList();
        CoachService cs=new CoachService();
        listCoachs=cs.afficher();
        table_Coachs.setItems(listCoachs);
        
         FilteredList<Coach> filteredData = new FilteredList<>(listCoachs, b -> true);
        recherche_textfield_coach.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(activite -> {
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if(activite.getNom_coach().toLowerCase().indexOf(searchKeyword) != -1){
                    return true;
                }else if(String.valueOf(activite.getAge_coach()).indexOf(searchKeyword) != -1){
                    return true;
                }else
                return false;
            });
        });
        SortedList<Coach> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_Coachs.comparatorProperty());
        table_Coachs.setItems(sortedData);
    }    

    @FXML
    private void SupprimerCoachOnClick(ActionEvent event) {
               cs=new CoachService();
        Coach c = new Coach(table_Coachs.getSelectionModel().getSelectedItem().getId());
        //c=(Coach)table_Coachs.getSelectionModel().getSelectedItem();
       // System.out.println(table_Coachs.getSelectionModel().getSelectedItem());
        cs.supprimer(c);
                colonne_nom.setCellValueFactory(new PropertyValueFactory<Coach, String>("nom_coach"));
        colonne_age.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("age_coach"));
        ObservableList<Coach>listCoachs=FXCollections.observableArrayList();
        CoachService cs=new CoachService();
        listCoachs=cs.afficher();
        table_Coachs.setItems(listCoachs);
        
         FilteredList<Coach> filteredData = new FilteredList<>(listCoachs, b -> true);
        recherche_textfield_coach.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(activite -> {
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if(activite.getNom_coach().toLowerCase().indexOf(searchKeyword) != -1){
                    return true;
                }else if(String.valueOf(activite.getAge_coach()).indexOf(searchKeyword) != -1){
                    return true;
                }else
                return false;
            });
        });
        SortedList<Coach> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_Coachs.comparatorProperty());
        table_Coachs.setItems(sortedData);
    }

    @FXML
    private void ModifierCoachOnClick(ActionEvent event) {
         recupData(table_Coachs.getSelectionModel().getSelectedItem());
        anchorpane_modif_coach.toFront();
    }

    @FXML
    private void ajouter_coachOnClick(ActionEvent event) {
        anchorpane_ajout_coach.toFront();
    }

    @FXML
    private void addCoachOnClick(ActionEvent event) throws IOException {
                erreur_age_coach.setText("");
        erreur_nom_coach.setText("");
        if(!textfield_age_coach.getText().isEmpty()&&!textfield_nom_coach.getText().isEmpty()&&Integer.parseInt(textfield_age_coach.getText())>=18&&textfield_nom_coach.getText().matches("[a-zA-Z ]+")){
         int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
        String newFileName = random_int+"-"+selectedFile.getName();
        Coach c=new Coach(Integer.parseInt(textfield_age_coach.getText()), textfield_nom_coach.getText(),newFileName);
        cs.ajouter(c);
        Path sourceFile = Paths.get(selectedFile.toPath().toString());
        Path targetFile = Paths.get(Statics.uploadDirectory+newFileName);

        Files.copy(sourceFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
                        colonne_nom.setCellValueFactory(new PropertyValueFactory<Coach, String>("nom_coach"));
        colonne_age.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("age_coach"));
        ObservableList<Coach>listCoachs=FXCollections.observableArrayList();
        CoachService cs=new CoachService();
        listCoachs=cs.afficher();
        table_Coachs.setItems(listCoachs);
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/AfficherCoach.fxml"));
        Parent root = loader.load();
        btn_AjoutCoach.getScene().setRoot(root);*/
        anchorPane_affich_coach.toFront();
        /* try {
            Parent root = loader.load();
           // AfficherCoachController cc = loader.getController();
            btn_AjoutCoach.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/}
        else{
            if(Integer.parseInt(textfield_age_coach.getText())==0){
                erreur_age_coach.setText("l'age est obligatoire");
            }
            if(textfield_nom_coach.getText().isEmpty()){
                erreur_nom_coach.setText("le nom est obligatoire");
            }
            if(Integer.parseInt(textfield_age_coach.getText())<18&&Integer.parseInt(textfield_age_coach.getText())>0){
                erreur_age_coach.setText("l'age doit être superieur à 18");
            }
            if(Integer.parseInt(textfield_age_coach.getText())<0){
                erreur_age_coach.setText("l'age doit être positif");
            }
            if(!textfield_nom_coach.getText().matches("[a-zA-Z ]+")){
                erreur_nom_coach.setText("le nom doit être alphabetique");
            }
        }
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

        public void recupData(Coach t){
       textfield_nom_modifier.setText(t.getNom_coach());
       String chara = Integer.toString(t.getAge_coach());
       textfield_age_modifier.setText(chara);
       image_coach_modif_field.setText(t.getImage());
       c1=t;
        System.out.println(t.getImage());
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

    @FXML
    private void ModifCoachOnClick(ActionEvent event) throws IOException {
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
                         colonne_nom.setCellValueFactory(new PropertyValueFactory<Coach, String>("nom_coach"));
        colonne_age.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("age_coach"));
        ObservableList<Coach>listCoachs=FXCollections.observableArrayList();
        CoachService cs=new CoachService();
        listCoachs=cs.afficher();
        table_Coachs.setItems(listCoachs);
           /* FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/AfficherCoach.fxml"));
        Parent root = loader.load();
        btn_modifierCoach.getScene().setRoot(root);*/
           anchorPane_affich_coach.toFront();
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
    
}
