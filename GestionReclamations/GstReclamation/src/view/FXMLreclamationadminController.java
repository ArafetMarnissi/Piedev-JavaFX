/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Reclamation;
import entity.Reponse;
import entity.TypeReclamation;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceReclamation;
import service.ServiceReponse;

/**
 * FXML Controller class
 *
 * @author Skymil
 */
public class FXMLreclamationadminController implements Initializable {

    @FXML
    private TableView<Reclamation> tvreclamation;
    @FXML
    private TableColumn<Reclamation, TypeReclamation> tctype;
    @FXML
    private TableColumn<Reclamation, String> tcdescription;
    @FXML
    private TableColumn<Reclamation, Date> tcdate;
    @FXML
    private TextArea tfreponse;
    ObservableList<Reclamation> data=FXCollections.observableArrayList();
    ServiceReclamation sr=new ServiceReclamation();
    ServiceReponse srr=new ServiceReponse();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refresh();
    }    

    @FXML
    private void repondre(ActionEvent event) {
        Reponse r=new Reponse();
        r.setDate_reponse(new Date());
        r.setDescriptionreponse(tfreponse.getText());
        Reclamation rec=tvreclamation.getSelectionModel().getSelectedItem();
        if(rec!=null){
            r.setIdreclamation_id(rec.getId());
            srr.ajouter(r);
        }
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Reclamation rec=tvreclamation.getSelectionModel().getSelectedItem();
        if(rec!=null){
            sr.supprimer(rec.getId());
            refresh();
        }
    }
    public void refresh(){
        data.clear();
        data=FXCollections.observableArrayList(sr.afficher());
        tcdate.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        tcdescription.setCellValueFactory(new PropertyValueFactory<>("description_reclamation"));
        tctype.setCellValueFactory(new PropertyValueFactory<>("type_reclamation"));

        tvreclamation.setItems(data);
    }
    
}
