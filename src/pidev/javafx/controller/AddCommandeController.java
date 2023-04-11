/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import pidev_javafx.entitie.Commande;
import pidev_javafx.entitie.LigneCommande;
import pidev_javafx.entitie.Produit;
import pidev_javafx.entitie.User;
import pidev_javafx.service.CommandeService;
import pidev_javafx.service.LigneCommandeService;
import pidev_javafx.service.ProduitService;

/**
 * FXML Controller class
 *
 * @author marni
 */
public class AddCommandeController implements Initializable {

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
    private ToggleGroup MethodePaiement;
    @FXML
    private RadioButton MPR2;
    @FXML
    private RadioButton MPR3;
    @FXML
    private Button btnComander;
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterCommande(ActionEvent event) throws IOException {
        
                erreurAdresse.setText("");
                erreurTelephone.setText("");
                erreurPaiement.setText("");
        
         if(!txtAdresseLivraison.getText().isEmpty()&&!txtTelephone.getText().isEmpty()&&txtTelephone.getText().matches("\\d+")&& txtTelephone.getText().length()==8 && (txtAdresseLivraison.getText().length()>=5)){
     
             int telephone = Integer.valueOf(txtTelephone.getText());
                    String AdresseLivarison = txtAdresseLivraison.getText();

                    MethodePaiement.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                        RadioButton selectedRadioBtn = (RadioButton) newValue;
                    });
                    String MethodedePaiement = ((RadioButton) MethodePaiement.getSelectedToggle()).getText();
                    ///a changer aprés la création de la session panier
                    ProduitService pserv = new ProduitService();
                    LigneCommandeService lcs = new LigneCommandeService();
                    Produit p1= pserv.getProduitParId(20); 
                    User u =new User(1, "test", "test", "test", "test", "test", 0, true);
                    ///
                    Commande t =new Commande(u, AdresseLivarison,250, MethodedePaiement, telephone) ;
                    ///
                    
                    
                    CommandeService ps = new CommandeService();
                    
                    ps.ajouter(t);
                    LigneCommande lc =new LigneCommande(ps.getLatestCommande(), p1, 2, p1.getPrix_produit());
                    
                    lcs.ajouter(lc);



                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/ListeCommandeClient.fxml"));
                    Parent root = loader.load();
                    btnComander.getScene().setRoot(root);
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
            if(MethodePaiement.getSelectedToggle()== null){
            erreurPaiement.setText("choisir la méthode de paiement");
            }
         }
    }

    @FXML
    private void retourList(ActionEvent event) throws IOException {
        
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/ListeCommandeClient.fxml"));
                    Parent root = loader.load();
                    btnComander.getScene().setRoot(root);
    }
    
}
