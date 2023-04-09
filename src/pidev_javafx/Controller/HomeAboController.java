/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev_javafx.entitie.Abonnement;
import pidev_javafx.service.AbonnementService;
import pidev_javafx.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author saifz
 */
public class HomeAboController implements Initializable {

    @FXML
    private TableColumn<Abonnement, Integer> idAbonnement;
    @FXML
    private TableColumn<Abonnement, String> nomAbonnement;
    @FXML
    private TableColumn<Abonnement, Float> prixAbonnement;
    @FXML
    private TableColumn<Abonnement, String> dureeAbonnement;
    @FXML
    private TableColumn<Abonnement, Integer> countAbonnement;    
    @FXML
    private Button supprimerabo;
    @FXML
    private TextArea prixa;
    @FXML
    private TextArea noma;
    @FXML
    private TextArea dureea;
    @FXML
    private Button ajouterabo;
    @FXML
    private Button modifierabo;
    @FXML
    private TableView<Abonnement> table;
    
    AbonnementService cr = new AbonnementService();

    /**
     * Initializes the controller class.
     */
    ObservableList<Abonnement> abList = FXCollections.observableArrayList();
    ObservableList<Abonnement> abList2;
    @FXML
    private Label formulaire_erreur;
    @FXML
    private Label erreurNom;
    @FXML
    private Label erreurPrix;
    @FXML
    private Label erreurDuree;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showAbonnement();
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                pidev_javafx.entitie.Abonnement r = (pidev_javafx.entitie.Abonnement) table.getSelectionModel().getSelectedItem();
                if (table.getSelectionModel().getSelectedItem() != null) {
                    pidev_javafx.entitie.Abonnement e = (pidev_javafx.entitie.Abonnement) table.getSelectionModel().getSelectedItem();
                     System.out.println();
                     noma.setText(e.getNomAbonnement());                                       
                     prixa.setText(Float.toString(e.getPrixAbonnement()));
                     dureea.setText(e.getDureeAbonnement());
                }
            }
            
        });                                
    }    
    
    public void showAbonnement() {
        try {
            Connection cnx = MaConnection.getInstance().getCnx();
            String query = "select * from abonnement";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Abonnement Abonnement;
            while (rs.next()) {
                Abonnement = new Abonnement(rs.getInt("id"),
                    rs.getString("nom_abonnement"),
                    rs.getFloat("prix_abonnement"),
                    rs.getString("duree_abonnement"),
                    rs.getInt("count"));
                abList.add(Abonnement);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idAbonnement.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomAbonnement.setCellValueFactory(new PropertyValueFactory<>("nomAbonnement"));
        prixAbonnement.setCellValueFactory(new PropertyValueFactory<>("prixAbonnement"));
        dureeAbonnement.setCellValueFactory(new PropertyValueFactory<>("dureeAbonnement"));
        countAbonnement.setCellValueFactory(new PropertyValueFactory<>("count"));
        

        table.setItems(abList);
    }
    
    public void showabo2(){
        abList.removeAll(abList);
        try {
            Connection cnx = MaConnection.getInstance().getCnx();
            String query = "select * from abonnement";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Abonnement Abonnement;
            while (rs.next()) {
                Abonnement = new Abonnement(rs.getInt("id"),
                    rs.getString("nom_abonnement"),
                    rs.getFloat("prix_abonnement"),
                    rs.getString("duree_abonnement"),
                    rs.getInt("count"));
                abList.add(Abonnement);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idAbonnement.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomAbonnement.setCellValueFactory(new PropertyValueFactory<>("nomAbonnement"));
        prixAbonnement.setCellValueFactory(new PropertyValueFactory<>("prixAbonnement"));
        dureeAbonnement.setCellValueFactory(new PropertyValueFactory<>("dureeAbonnement"));
        countAbonnement.setCellValueFactory(new PropertyValueFactory<>("count"));
        

        table.setItems(abList);        
    }
    
    
    @FXML
    private void ajoutAbo(ActionEvent Abonnement) throws IOException, SQLException {
        
        AbonnementService aa = new AbonnementService();
        Scanner sc = new Scanner(System.in);
        Connection cnx = MaConnection.getInstance().getCnx();
        Statement st;
        ResultSet rs;
        st = cnx.createStatement();
        Statement stm = cnx.createStatement();
        
        
          Abonnement t = new Abonnement();
        
           /* t.setNomAbonnement(noma.getText());
            t.setPrixAbonnement(Float.parseFloat(prixa.getText()));
            t.setDureeAbonnement(dureea.getText());*/
            Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(noma.getText());
            boolean nom = m.find();
            if(
                    noma.getText().isEmpty() || prixa.getText().isEmpty() || dureea.getText().isEmpty()      //prixa.getText()).isEmpty()    Float.parseFloat(prixa.getText())==0
                    ) 
            {
                
                    formulaire_erreur.setText("Veuillez remplir le formulaire");
                    
            }
            
            else if (nom){
                   
                    erreurNom.setText("Le nom ne doit pas contenir des symboles");
                    
               }
              else if(
                       Float.parseFloat(prixa.getText()) == 0
                       ) { 
               erreurPrix.setText("Le Prix ne doit pas etre nulle");
               }
               else if(
                       Float.parseFloat(prixa.getText()) < 0
                       ) { 
               
                    erreurPrix.setText("Le prix de l abonnement ne peut pas inferieure à 0");
                    
               }
               
               else {
                    t.setNomAbonnement(noma.getText());
                    t.setPrixAbonnement(Float.parseFloat(prixa.getText()));
                    t.setDureeAbonnement(dureea.getText());
                    aa.ajouter(t); 
                       showabo2(); 
                       noma.clear();
                       prixa.clear();
                       dureea.clear();                   
               }
 }       
    

    @FXML
    private void SupprimerAbonnementOnClick(ActionEvent event) {
        AbonnementService cs=new AbonnementService();
        Abonnement c = new Abonnement(table.getSelectionModel().getSelectedItem().getId());
        //c=(Coach)table_Coachs.getSelectionModel().getSelectedItem();
       // System.out.println(table_Coachs.getSelectionModel().getSelectedItem());
        cs.supprimer(c);
        showabo2();
        noma.clear();
        prixa.clear();
        dureea.clear();          
    }
    
    @FXML
    private void ModAbo(ActionEvent event) {
        

        Abonnement t = new Abonnement();
        AbonnementService sv = new AbonnementService();
	t = table.getSelectionModel().getSelectedItem();
	t.setId(table.getSelectionModel().getSelectedItem().getId());
        t.setNomAbonnement(noma.getText());
        t.setPrixAbonnement(Float.parseFloat(prixa.getText()));
        t.setDureeAbonnement(dureea.getText());
        sv.modifier(t);
        showabo2();
        noma.clear();
        prixa.clear();
        dureea.clear();  
        

    }
    

    
}
