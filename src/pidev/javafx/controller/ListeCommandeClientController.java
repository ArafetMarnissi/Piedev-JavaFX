/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.javafx.controller;

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
import pidev_javafx.entitie.Commande;
import pidev_javafx.service.CommandeService;

/**
 * FXML Controller class
 *
 * @author marni
 */
public class ListeCommandeClientController implements Initializable {

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
    private Button btnSupprimer;
    
    CommandeService cs =new CommandeService();
    @FXML
    private Button btnAjouterCommande;
    @FXML
    private Button btnModifer;

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
        btnAjouterCommande.getScene().setRoot(root);
         
        
    }


@FXML
    private void AjouterCommandeClient(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/AddCommande.fxml"));
        Parent root = loader.load();
        btnAjouterCommande.getScene().setRoot(root);
    }

}
