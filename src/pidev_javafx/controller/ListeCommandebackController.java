 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.awt.Desktop;
import java.io.File;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import pidev_javafx.entitie.Commande;
import pidev_javafx.entitie.LigneCommande;
import pidev_javafx.entitie.Produit;
import pidev_javafx.entitie.User;
import pidev_javafx.service.CommandeService;
import pidev_javafx.service.LigneCommandeService;
import pidev_javafx.service.PdfService;

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
    @FXML
    private Button btnPdf;
    

    
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
 
                             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de Suppression");
                                alert.setHeaderText("Êtes vous sûrs de supprimer cette commande?");
                                alert.setContentText("Cette action est irréversible");
                                Font font = Font.font("Verdana",FontWeight.BOLD, 16);

                            ButtonType buttonTypeYes = new ButtonType("Oui");
                            ButtonType buttonTypeNo = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
                            
                            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
                            alert.getDialogPane().setStyle("-fx-background-color: #FFFFFF;");
                            Button buttonYes = (Button) alert.getDialogPane().lookupButton(buttonTypeYes);
                            Button buttonNo = (Button) alert.getDialogPane().lookupButton(buttonTypeNo);
                            

                                                buttonYes.setStyle("-fx-text-fill:#ffffff; -fx-background-color: #1372f4; -fx-background-radius: 25px;"
                                                        + " -fx-min-width: 130px;\n" +
                                    "    -fx-max-width: 130px;\n" +
                                    "    -fx-min-height: 40px;\n" +
                                    "    -fx-max-height: 40px;");
                                                buttonNo.setStyle("-fx-text-fill:#ffffff; -fx-background-color: #f00020; -fx-background-radius: 25px;"
                                                        + " -fx-min-width: 130px;\n" +
                                    "    -fx-max-width: 130px;\n" +
                                    "    -fx-min-height: 40px;\n" +
                                    "    -fx-max-height: 40px;");
                                                //buttonCancel.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
                                                    alert.setContentText(null);
                                                    Label headerLabel1 = new Label("Vous devez étre connecté");
                                                    headerLabel1.setFont(font);
                                                    alert.getDialogPane().setContent(headerLabel1);

                                                Optional<ButtonType> result = alert.showAndWait();
                                                    
        if (result.isPresent() && result.get() == buttonTypeYes){
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
    @FXML
    private void GenererPdf(ActionEvent event) throws IOException {
       Commande commande=tableCommande.getSelectionModel().getSelectedItem();
        PdfService PdfS = new PdfService();
        PdfS.genererPdf(commande);
        Desktop.getDesktop().open(new File("C:\\Users\\marni\\OneDrive\\Bureau\\Facture_N°"+commande.getId()+".pdf"));
    }


}
