/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Reclamation;
import entity.Reponse;
import entity.TypeReclamation;
import gstreclamation.FXMain;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.ServiceReclamation;
import service.ServiceReponse;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    private TableColumn<Reclamation, String> tcdate;
    @FXML
    private TextArea tfreponse;
    ObservableList<Reclamation> data=FXCollections.observableArrayList();
    ServiceReclamation sr=new ServiceReclamation();
    ServiceReponse srr=new ServiceReponse();
    @FXML
    private TextField tfrecherche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //refresh(sr.afficher());
        recherche_avance();
    }    

    @FXML
    private void repondre(ActionEvent event) {
        Reponse r=new Reponse();
        r.setDate_reponse(new Date());
        r.setDescriptionreponse(tfreponse.getText());
        Reclamation rec=tvreclamation.getSelectionModel().getSelectedItem();
        if(rec!=null){
            r.setIdreclamation_id(rec.getId());
            //NOTIFICATION
            TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.FADE);
            tray.setTitle("Ajout avec success");
            tray.setMessage("Vous avez bien ajouter une reponse");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));
            //NOTIFICATION
            srr.ajouter(r);
        }
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Reclamation rec=tvreclamation.getSelectionModel().getSelectedItem();
        if(rec!=null){
            sr.supprimer(rec.getId());
            //NOTIFICATION
                TrayNotification tray=new TrayNotification();
                tray.setAnimationType(AnimationType.FADE);
                tray.setTitle("Supprission avec success");
                tray.setMessage("Vous avez bien supprimer une reclamation");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(2000));
                //NOTIFICATION
            refresh(sr.afficher());
        }
    }
    public void refresh(List<Reclamation> reclamations){
        data.clear();
        data=FXCollections.observableArrayList(reclamations);
        tcdate.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        tcdescription.setCellValueFactory(new PropertyValueFactory<>("description_reclamation"));
        tctype.setCellValueFactory(new PropertyValueFactory<>("type_reclamation"));

        tvreclamation.setItems(data);
    }

    @FXML
    private void tri(ActionEvent event) {
        refresh(sr.tri());
    }
    public void recherche_avance(){
        refresh(sr.afficher());
        ObservableList<Reclamation> reclamations=FXCollections.observableArrayList(sr.afficher());
        FilteredList<Reclamation> filtredlist=new FilteredList<>(reclamations,r->true);
        tfrecherche.textProperty().addListener((observable,oldValue,newValue)->{
            filtredlist.setPredicate(rec->{
                if(newValue==null || newValue.isEmpty()){
                    return true;
                }
                if(rec.getDescription_reclamation().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                    return true;
                }
                else if(String.valueOf(rec.getType_reclamation()).toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                    return true;
                }
                else if(rec.getDate_reclamation().toString().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                    return true;
                }
                else{
                    return false;
                }
                
                
            });
            refresh(filtredlist);
        });
    }

    @FXML
    private void stat(ActionEvent event) {
        Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/view/FXMLstatreclamation.fxml"));

            
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Golden Gym");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
