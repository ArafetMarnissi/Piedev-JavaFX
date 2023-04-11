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
public class DetailsCommandeController implements Initializable {


    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifer;
    
    LigneCommandeService lcs =new LigneCommandeService();
    @FXML
    private Button btnRetour;
    @FXML
    private TableColumn<LigneCommande, String> colProduit;
    @FXML
    private TableColumn<LigneCommande, Integer> colQuantite;
    @FXML
    private TableColumn<LigneCommande, Float> colPrixUnitaire;
    @FXML
    private TableView<LigneCommande> tableLigneCommande;
    public  Commande commande;

    /**
     * Initializes the controller class.
     */
 /* public DetailsCommandeController(Commande commande) {
        this.commande = commande;
  }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       // afficher();
        System.out.println(commande);
        
    }    


   public void setCommandeLigne(Commande t){

    commande=t;
       System.out.println(commande);
}
    
    void afficher(){
        
        
        
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
        
        listLigneCommande=lcs.afficherLigneCommandesParCommande(commande);
        tableLigneCommande.setItems(listLigneCommande);
       
    }


    private void SupprimerCommande(ActionEvent event) {

    }

    private void ModiferCommandeClient(ActionEvent event) throws IOException {

         
        
    }


    private void AjouterCommandeClient(ActionEvent event) throws IOException {

    }

    @FXML
    private void retourner(ActionEvent event) throws IOException {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/ListeCommandeClient.fxml"));
                    Parent root = loader.load();
                    btnRetour.getScene().setRoot(root);
    }

}
