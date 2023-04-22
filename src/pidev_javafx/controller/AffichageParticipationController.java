/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev_javafx.entitie.Activite;
import pidev_javafx.entitie.Participation;
import pidev_javafx.service.ActiviteService;
import pidev_javafx.service.ParticipationService;

/**
 * FXML Controller class
 *
 * @author mmarr
 */
public class AffichageParticipationController implements Initializable {

    @FXML
    private TableView<Participation> table_participation;
    @FXML
    private TableColumn<Participation, Date> date_participation_column;
    ParticipationService cs;
    ActiviteService ac;
    @FXML
    private TableColumn<Participation, Activite> activite_participation_column;
    @FXML
    private TableColumn<Participation, Integer> user_participation_column;
    @FXML
    private Button btn_supprimer_participation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        affich();
    }    
        private void affich(){
        date_participation_column.setCellValueFactory(new PropertyValueFactory<Participation, Date>("dateParticipation"));
        //activite_participation_column.setCellValueFactory(new PropertyValueFactory<Participation, Activite>("activite"));
        user_participation_column.setCellValueFactory(new PropertyValueFactory<Participation, Integer>("id_u"));
        ObservableList<Participation>listAc=FXCollections.observableArrayList();
        ParticipationService cs=new ParticipationService();
        listAc=cs.afficher();
        table_participation.setItems(listAc);
    }

    @FXML
    private void supprimer_participationOnClick(ActionEvent event) {
                 cs=new ParticipationService();
                 ac=new ActiviteService();
        Participation c = new Participation(table_participation.getSelectionModel().getSelectedItem().getId());
        //c=(Coach)table_Coachs.getSelectionModel().getSelectedItem();
       //System.out.println(table_activite_affich.getSelectionModel().getSelectedItem());
       //Activite a=cs.findbyid(c.getActivite().getId());
       Participation c1=cs.findParticipationbyid(table_participation.getSelectionModel().getSelectedItem().getId());
        System.out.println(c1.getActivite().getNbrePlace());
        Activite a=cs.findbyid(c1.getActivite().getId());
       a.setNbrePlace(a.getNbrePlace()+1);
       ac.modifier(a);
        cs.supprimer(c);
       affich();
    }
}
