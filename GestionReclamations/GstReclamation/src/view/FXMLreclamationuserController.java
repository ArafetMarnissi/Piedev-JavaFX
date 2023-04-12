/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Reclamation;
import entity.TypeReclamation;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author Skymil
 */
public class FXMLreclamationuserController implements Initializable {

    @FXML
    private ComboBox<TypeReclamation> cbtype;
    @FXML
    private TextArea tfdescription;
    ObservableList<TypeReclamation> cbdata=FXCollections.observableArrayList();
    ServiceReclamation sr=new ServiceReclamation();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        cbdata.add(TypeReclamation.Abonnement);
        cbdata.add(TypeReclamation.Activite);
        cbdata.add(TypeReclamation.Commande);
        cbdata.add(TypeReclamation.Magasin);
        cbtype.setItems(cbdata);
    }    

    @FXML
    private void envoyer(ActionEvent event) {
        Reclamation r=new Reclamation();
        r.setDate_reclamation(new Date());
        r.setDescription_reclamation(tfdescription.getText());
        r.setType_reclamation(cbtype.getSelectionModel().getSelectedItem());
        if(controleDeSaisie().length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("invalide");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("Vous avez bien ajouter une reclamation");
            alert.showAndWait();
            sr.ajouter(r);
        }
        
    }
    public String controleDeSaisie(){
        String erreurs="";
        if(cbtype.getSelectionModel().getSelectedItem()==null){
            erreurs+="-Veuillez saisire un type de reclamation!\n";
        }
        if(tfdescription.getText().isEmpty()){
            erreurs+="-Veuillez remplire le champs description!\n";
        }
        return erreurs;
    }
    
}
