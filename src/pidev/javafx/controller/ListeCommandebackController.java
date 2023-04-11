/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
import pidev_javafx.entitie.Commande;
import pidev_javafx.entitie.User;
import pidev_javafx.service.CommandeService;

/**
 * FXML Controller class
 *
 * @author marni
 */
public class ListeCommandebackController implements Initializable {

@FXML
    private TableView<Commande> tableCommande;
    @FXML
    private TableColumn<Commande, String> coldatecommande;
    @FXML
    private TableColumn<Commande, String> coladresse;
    @FXML
    private TableColumn<Commande, String> colmethpaiement;
    @FXML
    private TableColumn<Commande, Integer> coltelephone;
    @FXML
    private TableColumn<Commande, Float> colprix;
    @FXML
    private TableColumn<Commande, String> colClientName;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifer;
    @FXML
    private Button btnDetaisCommande;
    
    CommandeService cs =new CommandeService();
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
    }    

    private void afficherCommande(ActionEvent event) {
        afficher();  
    }

    
    void afficher(){
    colClientName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commande, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Commande, String> cellData) {
            User user = cellData.getValue().getUser(); // récupérer l'utilisateur associé à la commande
            return new SimpleStringProperty(user.getNom()); // retourner le nom de l'utilisateur
        }
    });
        coldatecommande.setCellValueFactory(new PropertyValueFactory<Commande, String>("date_commande"));
        coladresse.setCellValueFactory(new PropertyValueFactory<Commande, String>("adresse_livraison"));
        colmethpaiement.setCellValueFactory(new PropertyValueFactory<Commande, String>("methode_paiement"));
        coltelephone.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("telephone"));
        colprix.setCellValueFactory(new PropertyValueFactory<Commande, Float>("prix_commande"));
        ObservableList<Commande>listCommande=FXCollections.observableArrayList();
        
        listCommande=cs.afficher();
        tableCommande.setItems(listCommande);
        
    }


    @FXML
    private void SupprimerCommande(ActionEvent event) {
       cs.supprimer(tableCommande.getSelectionModel().getSelectedItem());
       afficher();
    }

@FXML
    private void ModiferCommandeClient(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/EditCommande.fxml"));
        Parent root = loader.load();
        EditCommandeController controller = loader.getController();
        controller.setCommande(tableCommande.getSelectionModel().getSelectedItem());
        btnModifer.getScene().setRoot(root);
         
        
    }


    private void AjouterCommandeClient(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/AddCommande.fxml"));
        Parent root = loader.load();
        btnModifer.getScene().setRoot(root);
    }

@FXML
private void DetailsCommande(ActionEvent event) throws IOException {
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/DetailsCommande.fxml"));
    Parent root = loader.load();
    // Créer le contrôleur pour la vue des détails de la commande
    DetailsCommandeController controller = loader.getController(); 
    controller.commande=tableCommande.getSelectionModel().getSelectedItem();
    controller.afficher();
    btnDetaisCommande.getScene().setRoot(root);
}


}
