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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import pidev_javafx.entitie.Commande;
import pidev_javafx.entitie.LigneCommande;
import pidev_javafx.entitie.PanierSession;
import pidev_javafx.entitie.Produit;
import pidev_javafx.entitie.User;
import pidev_javafx.service.CommandeService;
import pidev_javafx.service.LigneCommandeService;
import pidev_javafx.service.PdfService;
import pidev_javafx.service.ProduitService;

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
    private Button btnModifer;
    @FXML
    private Button btnDetails;
    @FXML
    private BorderPane BordePane;
    @FXML
    public AnchorPane PanePasserCommande;
    @FXML
    private Button btnRetour;
    @FXML
    private TextField txtAdresseLivraison;
    @FXML
    private Label erreurAdresse;
    @FXML
    private TextField txtTelephone;
    @FXML
    private Label erreurTelephone;
    @FXML
    private Label erreurPaiement;
    @FXML
    private RadioButton MPR1;
    @FXML
    private RadioButton MPR2;
    @FXML
    private RadioButton MPR3;
    @FXML
    private Button btnComander;
    @FXML
    private AnchorPane PaneListeCommandeClient;
    @FXML
    private ToggleGroup MethodePaiementAjouter;
    @FXML
    private AnchorPane PaneModiferCommande;
    @FXML
    private Button btnRetourModifer;
    @FXML
    private TextField txtAdresseLivraisonModifer;
    @FXML
    private Label erreurAdresseModifer;
    @FXML
    private TextField txtTelephoneModifer;
    @FXML
    private Label erreurTelephoneModifer;
    @FXML
    private Label erreurPaiementModifer;
    @FXML
    private RadioButton MPR1Modifer;
    @FXML
    private ToggleGroup MethodePaiementModifer;
    @FXML
    private RadioButton MPR2Modifer;
    @FXML
    private RadioButton MPR3Modifer;
    @FXML
    private Button btnModiferListe;
    @FXML
    private AnchorPane PaneDetaisCommandeClient;
    @FXML
    private Button btnRetourDetails;
    @FXML
    private TableView<LigneCommande> tableLigneCommande;
    @FXML
    private TableColumn<LigneCommande, String> colProduit;
    @FXML
    private TableColumn<LigneCommande, Integer> colQuantite;
    @FXML
    private TableColumn<LigneCommande, Float> colPrixUnitaire;
    @FXML
    private Button btnPdf;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PaneListeCommandeClient.toFront();
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
    private void ModiferCommandeClient(ActionEvent event) throws IOException {

       setDataCommandeModifer(tableCommande.getSelectionModel().getSelectedItem());
       PaneModiferCommande.toFront();
        
    }

    @FXML
    private void DetailsCommande(ActionEvent event) throws IOException {

        PaneDetaisCommandeClient.toFront();
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
    private void retourList(ActionEvent event) {
        afficher();
        PaneListeCommandeClient.toFront();
    }

    @FXML
    private void AjouterCommande(ActionEvent event) throws IOException {
                
                erreurAdresse.setText("");
                erreurTelephone.setText("");
                erreurPaiement.setText("");
        
         if(!txtAdresseLivraison.getText().isEmpty()&&!txtTelephone.getText().isEmpty()&&txtTelephone.getText().matches("\\d+")&& txtTelephone.getText().length()==8 && (txtAdresseLivraison.getText().length()>=5)){
     
             int telephone = Integer.valueOf(txtTelephone.getText());
                    String AdresseLivarison = txtAdresseLivraison.getText();

                    MethodePaiementAjouter.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                        RadioButton selectedRadioBtn = (RadioButton) newValue;
                    });
                    String MethodedePaiement = ((RadioButton) MethodePaiementAjouter.getSelectedToggle()).getText();
                    ///a changer aprés la création de la session panier
                    ProduitService pserv = new ProduitService();
                    LigneCommandeService lcs = new LigneCommandeService();
                    
                    Produit p1= pserv.getProduitParId(20); 
                    User u =new User(1, "test", "test", "test", "test", "test", 0, true);
                    ///
                    Commande t =new Commande(u, AdresseLivarison,PanierSession.getInstance().calculTotale(), MethodedePaiement, telephone) ;
                    ///
                    
                    
                    CommandeService ps = new CommandeService();
                    
                    ps.ajouter(t);
                    for(Produit prod :PanierSession.getPanier().keySet()){
                    LigneCommande lc =new LigneCommande(ps.getLatestCommande(), prod, PanierSession.getPanier().get(prod), prod.getPrix_produit());
                    lcs.ajouter(lc);
                    }
                    
                    
                    
                    

                    afficher();
                    PaneListeCommandeClient.toFront();

         }else{
           if(txtAdresseLivraison.getText().isEmpty()){
            erreurAdresse.setText("L'adrresse est obligatoire");}
           if(txtAdresseLivraison.getText().length()<5  && txtAdresseLivraison.getText().length()>0){
            erreurAdresse.setText("Adresse non valide");}
            if(txtTelephone.getText().isEmpty()){
            erreurTelephone.setText("Le téléphone est obligatoire");}
            
            if(txtTelephone.getText().length()!=8 ){
              erreurTelephone.setText("Veiller vérifer le numéro de téléphone");
            }
            if(!txtTelephone.getText().matches("\\d+")){
              erreurTelephone.setText("Veiller vérifer le numéro de téléphone");
            }
            if(MethodePaiementAjouter.getSelectedToggle()== null){
            erreurPaiement.setText("choisir la méthode de paiement");
            }
         }
    }

    @FXML
    private void MretourList(ActionEvent event) {
        afficher();
        PaneListeCommandeClient.toFront();
    }

    @FXML
    private void ModiferCommande(ActionEvent event) {
         
            
            Commande commande =tableCommande.getSelectionModel().getSelectedItem();
            erreurAdresseModifer.setText("");
            erreurTelephoneModifer.setText("");
            erreurPaiementModifer.setText("");
         if(!txtAdresseLivraisonModifer.getText().isEmpty()&&!txtTelephoneModifer.getText().isEmpty()&&txtTelephoneModifer.getText().matches("\\d+")&& txtTelephoneModifer.getText().length()==8 && (txtAdresseLivraisonModifer.getText().length()>=5)){
     
             int telephone = Integer.valueOf(txtTelephoneModifer.getText());
                    String AdresseLivarison = txtAdresseLivraisonModifer.getText();

                    MethodePaiementModifer.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                        RadioButton selectedRadioBtn = (RadioButton) newValue;
                    });
                    String MethodedePaiement = ((RadioButton) MethodePaiementModifer.getSelectedToggle()).getText();
                    Commande t =new Commande(commande.getId(),commande.getUser(), AdresseLivarison,commande.getPrix_commande(), MethodedePaiement, telephone) ;

                    CommandeService ps = new CommandeService();
                    
                    ps.modifier(t);



                    afficher();
                    PaneListeCommandeClient.toFront();
         }else{
           if(txtAdresseLivraisonModifer.getText().isEmpty()){
            erreurAdresseModifer.setText("L'adrresse est obligatoire");}
           if(txtAdresseLivraisonModifer.getText().length()<5  && txtAdresseLivraisonModifer.getText().length()>0){
            erreurAdresseModifer.setText("Adresse non valide");}
            if(txtTelephoneModifer.getText().isEmpty()){
            erreurTelephoneModifer.setText("Le téléphone est obligatoire");}
            
            if(txtTelephoneModifer.getText().length()!=8 ){
              erreurTelephoneModifer.setText("Veiller vérifer le numéro de téléphone");
            }
            if(!txtTelephoneModifer.getText().matches("\\d+")){
              erreurTelephoneModifer.setText("Veiller vérifer le numéro de téléphone");
            }
            if(MethodePaiementModifer.getSelectedToggle()== null){
            erreurPaiementModifer.setText("choisir la méthode de paiement");
            }
         }

    }
    public void setDataCommandeModifer(Commande t){
        txtAdresseLivraisonModifer.setText(t.getAdresse_livraison());
        txtTelephoneModifer.setText(String.valueOf(t.getTelephone())); 
        if (t.getMethode_paiement().equals("Chèque")) {
        MethodePaiementModifer.selectToggle(MPR1Modifer);}
        if (t.getMethode_paiement().equals("à la livraison")) {
        MethodePaiementModifer.selectToggle(MPR2Modifer);}
        if (t.getMethode_paiement().equals("Carte bancaire")) {
        MethodePaiementModifer.selectToggle(MPR3Modifer);}
        
        
   
       
}

    @FXML
    private void RetourDetails(ActionEvent event) {
                afficher();
        PaneListeCommandeClient.toFront();
    }

    @FXML
    private void GenererPdf(ActionEvent event) {
        Commande commande=tableCommande.getSelectionModel().getSelectedItem();
        PdfService PdfS = new PdfService();
        PdfS.genererPdf(commande);

    }

}
