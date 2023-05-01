/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import pidev_javafx.entitie.Reclamation;
import pidev_javafx.entitie.Reponse;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pidev_javafx.service.ServiceReponse;
import pidev_javafx.service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author Skymil
 */
public class FXMLrecalamtionuserreponseController implements Initializable {

    @FXML
    private TableView<Reponse> tvreponse;
    @FXML
    private TableColumn<Reponse, Date> tcdate;
    @FXML
    private TableColumn<Reponse, String> tcdesc;
    ObservableList<Reponse> data=FXCollections.observableArrayList();
    
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
    private void supprimer(ActionEvent event) {
        if(tvreponse.getSelectionModel().getSelectedItem()!=null){
            srr.supprimer(tvreponse.getSelectionModel().getSelectedItem().getId());
            refresh();
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/view/FXMLreclamationuser.fxml"));

            
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Golden Gym");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void refresh(){
        data.clear();
        data=FXCollections.observableArrayList(srr.afficher());
        tcdate.setCellValueFactory(new PropertyValueFactory<>("date_reponse"));
        tcdesc.setCellValueFactory(new PropertyValueFactory<>("descriptionreponse"));

        tvreponse.setItems(data);
    }
    
}
