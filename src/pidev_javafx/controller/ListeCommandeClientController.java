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
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonBar;
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
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
import pidev_javafx.service.SessionManager;
import pidev_javafx.test.Test;
import pidev_javafx.tools.MailFacture;

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
    
    private Stage stage;
    private  Parent root;
    private Scene scene;



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
        
        listCommande=cs.afficherCommandesParClient(new User(SessionManager.getId(), "test", "test", "test", "test", "test", 0, true));
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
    private void ModiferCommandeClient(ActionEvent event) throws IOException {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        String dateCommande=tableCommande.getSelectionModel().getSelectedItem().getDate_commande();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime currentDateTime = LocalDateTime.parse(currentTime, formatter);
        LocalDateTime commandDateTime = LocalDateTime.parse(dateCommande, formatter);
        Duration duration = Duration.between(commandDateTime, currentDateTime);
        long diffInMinutes = duration.toMinutes();

if (Math.abs(diffInMinutes) > 60) {
    
        Alert confirmation = new Alert(Alert.AlertType.ERROR);
        confirmation.setTitle("Erreur");
        confirmation.setHeaderText(" Vous ne pouvez pas modifer cette commande");
        Optional<ButtonType> result = confirmation.showAndWait();    
    }
    else{
        setDataCommandeModifer(tableCommande.getSelectionModel().getSelectedItem());
       PaneModiferCommande.toFront();
}

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
    private void AjouterCommande(ActionEvent event) throws IOException, SQLException {
                
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
                    
                    Produit p1= pserv.findprodbyid(20); 
                    User u =new User(SessionManager.getId(), "test", "test", "test", "test", "test", 0, true);
                    

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
                    PdfService PdfS =new PdfService();
                    PdfS.genererPdf(ps.getLatestCommande());
                    ///Send Email 
                   /* try {

                        MailFacture.sendMail(SessionManager.getEmail(), ps.getLatestCommande());
                    } catch (Exception ex) {
                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                    ///vider la panier
                    PanierSession.EndSession();
        
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/DashbordFront.fxml"));
                    Parent root = loader.load();
                    DashbordFrontController controller = loader.getController();
                    try {
                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/ListeCommandeClient.fxml"));
                    Pane autreInterface = loader2.load();
                    Region parent = (Region) loader2.getRoot();
                    parent.prefWidthProperty().bind(controller.PaneContent.widthProperty());
                    parent.prefHeightProperty().bind(controller.PaneContent.heightProperty());

                        controller.PaneContent.getChildren().setAll(autreInterface);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    btnSupprimer.getScene().setRoot(root);
                
                    
                    ///

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
    private void GenererPdf(ActionEvent event) throws IOException {
        Commande commande=tableCommande.getSelectionModel().getSelectedItem();
        PdfService PdfS = new PdfService();
        PdfS.genererPdf(commande);
        Desktop.getDesktop().open(new File("C:\\Users\\marni\\OneDrive\\Bureau\\Facture_N°"+commande.getId()+".pdf"));

    }

}
