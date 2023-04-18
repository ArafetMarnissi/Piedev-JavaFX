/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.gui;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import pidev_javafx.Controller.CarteAbonnementController;
import pidev_javafx.entitie.Abonnement;
import pidev_javafx.service.AbonnementService;
import pidev_javafx.tools.MaConnection;
import pidev_javafx.entitie.Reservation;
import pidev_javafx.service.ReservationService;

/**
 * FXML Controller class
 *
 * @author saifz
 */
public class ReservationController implements Initializable {

    private TableColumn<Abonnement, String> abr;
    private TableColumn<Abonnement, Float> prixr;
    private TableColumn<Abonnement, String> dureea;
    @FXML
    private TableColumn<Reservation, Integer> idr;
    @FXML
    private TableColumn<Reservation, Date> debutr;
    @FXML
    private TableColumn<Reservation, Date> finr;
    private TableView<Abonnement> tableAb;
    @FXML
    private TableView<Reservation> tableRe;

    /**
     * Initializes the controller class.
     */
    ObservableList<Abonnement> abList = FXCollections.observableArrayList();
    ObservableList<Abonnement> abList2;
    ObservableList<Reservation> reList = FXCollections.observableArrayList();
    ObservableList<Reservation> reList2;
    @FXML
    private Label labelReservation;
    private HBox HboxAB;
    @FXML
    private GridPane GridPAB;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //showAbonnement();
        afficherAB();
        showReservation();
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
        //ida.setCellValueFactory(new PropertyValueFactory<>("id"));
        abr.setCellValueFactory(new PropertyValueFactory<>("nomAbonnement"));
        prixr.setCellValueFactory(new PropertyValueFactory<>("prixAbonnement"));
        dureea.setCellValueFactory(new PropertyValueFactory<>("dureeAbonnement"));
        //counta.setCellValueFactory(new PropertyValueFactory<>("count"));
        

        tableAb.setItems(abList);
    }  
    
    
    
        public void showReservation() {
        try {
            Connection cnx = MaConnection.getInstance().getCnx();
            String query = "select * from reservation";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Reservation Reservation;
            while (rs.next()) {
                Reservation = new Reservation(rs.getInt("id"),
                    rs.getDate("date_debut"),
                    rs.getDate("date_fin")
            );
                reList.add(Reservation);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idr.setCellValueFactory(new PropertyValueFactory<>("id"));
        debutr.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
        finr.setCellValueFactory(new PropertyValueFactory<>("DateFin"));
        

        tableRe.setItems(reList);
    }  
        
     public void showReservation1() {
         reList.removeAll(reList);
        try {
            Connection cnx = MaConnection.getInstance().getCnx();
            String query = "select * from reservation";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Reservation Reservation;
            while (rs.next()) {
                Reservation = new Reservation(rs.getInt("id"),
                    rs.getDate("date_debut"),
                    rs.getDate("date_fin")
            );
                reList.add(Reservation);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        idr.setCellValueFactory(new PropertyValueFactory<>("id"));
        debutr.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
        finr.setCellValueFactory(new PropertyValueFactory<>("DateFin"));
        

        tableRe.setItems(reList);
    }     
        
    
private void Addreservation(Abonnement ab) {
    ArrayList<Abonnement> abonnements = new ArrayList<>();
    AbonnementService sv = new AbonnementService();

    ReservationService rse = new ReservationService();

    ab.getId();
    
    LocalDate dateActuelle1 = LocalDate.now(); // Date actuelle 
    java.sql.Date debutAbo = Date.valueOf(dateActuelle1);
    LocalDate dateActuelle2 = LocalDate.now();
    if (ab.getDureeAbonnement().equalsIgnoreCase("Mensuel")) {
        dateActuelle2 = dateActuelle2.plusMonths(1);
    } else if (ab.getDureeAbonnement().equalsIgnoreCase("Annuel")) {
        dateActuelle2 = dateActuelle2.plusYears(1);
    }
    java.sql.Date finAbo = Date.valueOf(dateActuelle2);
    ab.setCount(ab.getCount() + 1);

    Reservation derniereReservation = rse.getDerniereReservation();
    if (derniereReservation == null ) {
        ab.setCount(ab.getCount() + 1);
        sv.modifier(ab); // Mettre à jour l'abonnement dans la base de données
        abonnements.add(ab);
        Reservation r = new Reservation(debutAbo, finAbo, abonnements);        
        rse.ajouter(r);
        showReservation1();    
        labelReservation.setText("Vous avez reservez un abonnement "+ab.getNomAbonnement()+" "+ab.getDureeAbonnement());
    }
    else {
        if(derniereReservation.getDateFin() != null && derniereReservation.getDateFin().after(Date.valueOf(dateActuelle1)))
        {
            labelReservation.setText("Vous êtes déjà abonné jusqu'a " + derniereReservation.getDateFin() );
        }
        else 
        {
        ab.setCount(ab.getCount() + 1);
        sv.modifier(ab); // Mettre à jour l'abonnement dans la base de données
        abonnements.add(ab);
        Reservation r = new Reservation(debutAbo, finAbo, abonnements);        
        rse.ajouter(r);
        showReservation1();    
        labelReservation.setText("Vous avez reservez un abonnement "+ab.getNomAbonnement()+" "+ab.getDureeAbonnement());           
        }

    }

}

public void afficherAB(){

    ArrayList<Abonnement> abonnements = new ArrayList<>();
    Abonnement t = new Abonnement();
    AbonnementService sv = new AbonnementService();
    abonnements = (ArrayList<Abonnement>) sv.afficher();
        int column=0;
        int row=1;
        try {
            for(int i=0;i<abonnements.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/CarteAbonnement.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CarteAbonnementController cartAbonnementController = fxmlLoader.getController();
                cartAbonnementController.SetData(abonnements.get(i));
                VBox vboxEntry = new VBox(); 
                vboxEntry.getChildren().add(anchorPane);
                //GridPAB.add(anchorPane, column++, row);

                ///add button
                Button btn = new Button("Reserver");
                
                btn.setUserData(abonnements.get(i));
                btn.setStyle("-fx-background-color: #0c1f28; -fx-text-fill: white; -fx-background-radius: 25;");              
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Node sourceComponent = (Node)event.getSource();
                        Abonnement ab =(Abonnement) sourceComponent.getUserData();                       
                        Addreservation(ab);

                    }
                });
                vboxEntry.getChildren().add(btn);
                
                if( abonnements.get(i).getDureeAbonnement().equalsIgnoreCase("Mensuel")){anchorPane.setStyle("-fx-background-color: #e97d68;");}
               else {anchorPane.setStyle("-fx-background-color: #3aa0d1;");}
                GridPAB.add(vboxEntry, column++, row);
                if(column == 3){
                 row++;
                 column=0;
                }

                //set Grid width
                GridPAB.setMinWidth(Region.USE_COMPUTED_SIZE);
                GridPAB.setPrefWidth(Region.USE_COMPUTED_SIZE);
                GridPAB.setMaxWidth(Region.USE_PREF_SIZE);
                //set Grid height
                GridPAB.setMinHeight(Region.USE_COMPUTED_SIZE);
                GridPAB.setPrefHeight(Region.USE_COMPUTED_SIZE);
                GridPAB.setMaxHeight(Region.USE_PREF_SIZE);
                GridPAB.setMargin(anchorPane, new Insets(10));
            }
            
            } catch (IOException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        

}


}