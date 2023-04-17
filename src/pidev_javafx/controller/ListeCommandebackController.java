/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import pidev_javafx.entitie.Commande;
import pidev_javafx.entitie.LigneCommande;
import pidev_javafx.entitie.Produit;
import pidev_javafx.entitie.User;
import pidev_javafx.service.CommandeService;
import pidev_javafx.service.LigneCommandeService;

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
    private Button btnDetaisCommande;
    
    CommandeService cs =new CommandeService();
    @FXML
    private AnchorPane PaneDetaisCommandeBack;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnpdf;
    @FXML
    private TableColumn<LigneCommande, String> colProduit;
    @FXML
    private TableColumn<LigneCommande, Integer> colQuantite;
    @FXML
    private TableColumn<LigneCommande, Float> colPrixUnitaire;
    @FXML
    private TableView<LigneCommande> tableLigneCommande;
    @FXML
    private AnchorPane PaneListCommandeBack;
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation de Suppression");
        confirmation.setHeaderText(" Êtes vous sûrs de supprimer cette commande?");
        confirmation.setContentText("Cette action est irréversible");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            cs.supprimer(tableCommande.getSelectionModel().getSelectedItem());
        } else {
            afficher();
        }
        afficher();
    }


@FXML
private void DetailsCommande(ActionEvent event) throws IOException {
   
        PaneDetaisCommandeBack.toFront();
        Commande commande=tableCommande.getSelectionModel().getSelectedItem();
   
        colProduit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LigneCommande, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<LigneCommande, String> cellData) {
            Produit produit = cellData.getValue().getProduit(); // récupérer le produit associé à la Ligne de commande
            return new SimpleStringProperty(produit.getNom()); // retourner le nom de produit
        }
        });

        colQuantite.setCellValueFactory(new PropertyValueFactory<LigneCommande, Integer>("quantite_produit"));
        colPrixUnitaire.setCellValueFactory(new PropertyValueFactory<LigneCommande, Float>("prix_unitaire"));
        ObservableList<LigneCommande>listLigneCommande=FXCollections.observableArrayList();
        LigneCommandeService lcs =new LigneCommandeService();
        listLigneCommande=lcs.afficherLigneCommandesParCommande(commande);
        tableLigneCommande.setItems(listLigneCommande);
}

    @FXML
    private void retourner(ActionEvent event) {
        afficher();
        PaneListCommandeBack.toFront();
    }


}
