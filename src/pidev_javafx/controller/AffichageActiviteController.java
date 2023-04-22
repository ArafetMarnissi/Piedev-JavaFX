/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev_javafx.entitie.Activite;
import pidev_javafx.entitie.Coach;
import pidev_javafx.service.ActiviteService;
import pidev_javafx.service.CoachService;

/**
 * FXML Controller class
 *
 * @author mmarr
 */
public class AffichageActiviteController implements Initializable {

    @FXML
    private Button btn_ajouter_activite_affichage;
    @FXML
    private Button btn_modif_activite_affichage;
    @FXML
    private Button btn_supprimer_activite_affichage;
    @FXML
    private TableColumn<Activite, String> nom_activite_column;
    @FXML
    private TableColumn<Activite, String> description_activite_column;
    @FXML
    private TableColumn<Activite, Integer> nbreplace_activite_column;
    @FXML
    private TableColumn<Activite, String> image_activite_column;
    ActiviteService cs;
    @FXML
    private TableView<Activite> table_activite_affich;
    @FXML
    private TableColumn<Activite, Date> date_activite_column;
    @FXML
    private TableColumn<Activite, Time> debut_activite_column;
    @FXML
    private TableColumn<Activite, Time> fin_activite_column;
    @FXML
    private TableColumn<Activite, Integer> coach_activite_column;
    @FXML
    private TextField recherche_textfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        affich();
        
    }    

    @FXML
    private void Ajouter_activite_affichageOnClick(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/AjoutActivite.fxml"));
        Parent root = loader.load();
        btn_ajouter_activite_affichage.getScene().setRoot(root);
    }

    @FXML
    private void Modifier_activite_affichageOnClick(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/ModifierActivite.fxml"));
            Parent root = loader.load();
            ModifierActiviteController mcc = loader.getController();
            mcc.recupData(table_activite_affich.getSelectionModel().getSelectedItem());
            btn_modif_activite_affichage.getScene().setRoot(root);
    }

    @FXML
    private void Supprimer_activite_affichageOnClick(ActionEvent event) {
         cs=new ActiviteService();
        Activite c = new Activite(table_activite_affich.getSelectionModel().getSelectedItem().getId());
        //c=(Coach)table_Coachs.getSelectionModel().getSelectedItem();
       //System.out.println(table_activite_affich.getSelectionModel().getSelectedItem());
        cs.supprimer(c);
       affich();
    }
    
    private void affich(){
        nom_activite_column.setCellValueFactory(new PropertyValueFactory<Activite, String>("nomActivite"));
        description_activite_column.setCellValueFactory(new PropertyValueFactory<Activite, String>("descriptionActivite"));
        nbreplace_activite_column.setCellValueFactory(new PropertyValueFactory<Activite, Integer>("nbrePlace"));
        image_activite_column.setCellValueFactory(new PropertyValueFactory<Activite, String>("Image"));
        date_activite_column.setCellValueFactory(new PropertyValueFactory<Activite, Date>("DateActivite"));
        debut_activite_column.setCellValueFactory(new PropertyValueFactory<Activite, Time>("TimeActivite"));
        fin_activite_column.setCellValueFactory(new PropertyValueFactory<Activite, Time>("end"));
        //coach_activite_column.setCellValueFactory(new PropertyValueFactory<Activite, Integer>("coach"));
        ObservableList<Activite>listAc=FXCollections.observableArrayList();
        ActiviteService cs=new ActiviteService();
        listAc=cs.afficher();
        table_activite_affich.setItems(listAc);
        
        FilteredList<Activite> filteredData = new FilteredList<>(listAc, b -> true);
        recherche_textfield.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(activite -> {
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if(activite.getNomActivite().toLowerCase().indexOf(searchKeyword) != -1){
                    return true;
                }else if(activite.getDescriptionActivite().toLowerCase().indexOf(searchKeyword) != -1){
                    return true;
                }else if(String.valueOf(activite.getNbrePlace()).indexOf(searchKeyword) != -1){
                    return true;
                }else if(String.valueOf(activite.getNbrePlace()).indexOf(searchKeyword) != -1){
                    
                }
                return false;
            });
        });
        SortedList<Activite> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_activite_affich.comparatorProperty());
        table_activite_affich.setItems(sortedData);
    }
    
}
