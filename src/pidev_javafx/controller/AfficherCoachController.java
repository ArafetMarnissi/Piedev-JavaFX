/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev_javafx.entitie.Coach;
import pidev_javafx.service.CoachService;

/**
 * FXML Controller class
 *
 * @author mmarr
 */
public class AfficherCoachController implements Initializable {

    @FXML
    private TableColumn<Coach, String> colonne_nom;
    @FXML
    private TableColumn<Coach, Integer> colonne_age;
    @FXML
    private TableView<Coach> table_Coachs;
    @FXML
    private Button btn_SupprimerCoach;
    CoachService cs;
    @FXML
    private Button btn_ModifierCoach1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //id.setCellValueFactory(new PropertyValueFactory<Coach,Integer>("id"));
        colonne_nom.setCellValueFactory(new PropertyValueFactory<Coach, String>("nom_coach"));
        colonne_age.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("age_coach"));
        ObservableList<Coach>listCoachs=FXCollections.observableArrayList();
        CoachService cs=new CoachService();
        listCoachs=cs.afficher();
        table_Coachs.setItems(listCoachs);
    }    

    @FXML
    private void SupprimerCoachOnClick(ActionEvent event) {
        cs=new CoachService();
        Coach c = new Coach(table_Coachs.getSelectionModel().getSelectedItem().getId());
        //c=(Coach)table_Coachs.getSelectionModel().getSelectedItem();
       // System.out.println(table_Coachs.getSelectionModel().getSelectedItem());
        cs.supprimer(c);
    }

    @FXML
    private void ModifierCoachOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/ModifierCoach.fxml"));
            Parent root = loader.load();
            ModifierCoachController mcc = loader.getController();
            mcc.recupData(table_Coachs.getSelectionModel().getSelectedItem());
            btn_ModifierCoach1.getScene().setRoot(root);
    }
    
}
