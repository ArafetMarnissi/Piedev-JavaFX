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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    private TableColumn<Abonnement, String> abr;
    @FXML
    private TableColumn<Abonnement, Float> prixr;
    @FXML
    private TableColumn<Abonnement, String> dureea;
    @FXML
    private TableColumn<Abonnement, Integer> ida;
    @FXML
    private TableColumn<Abonnement, Integer> counta;
    @FXML
    private Button reserver;
    @FXML
    private TableColumn<Reservation, Integer> idr;
    @FXML
    private TableColumn<Reservation, Date> debutr;
    @FXML
    private TableColumn<Reservation, Date> finr;
    @FXML
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showAbonnement();
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
        
    
    @FXML
    private void Addreservation(ActionEvent event) throws IOException, SQLException {
        ArrayList<Abonnement> abonnements = new ArrayList<>();
        Abonnement t = new Abonnement();
        AbonnementService sv = new AbonnementService();
        
        ReservationService rse = new ReservationService();
        Scanner sc = new Scanner(System.in);
        Connection cnx = MaConnection.getInstance().getCnx();
        Statement st;
        ResultSet rs;
        st = cnx.createStatement();
        Statement stm = cnx.createStatement();
	t = tableAb.getSelectionModel().getSelectedItem();
	t.setId(tableAb.getSelectionModel().getSelectedItem().getId());
        int abID = t.getId();
        LocalDate dateActuelle1 = LocalDate.now(); // Date actuelle 
        java.sql.Date debutAbo = Date.valueOf(dateActuelle1);
        LocalDate dateActuelle2= LocalDate.now();        
        if (t.getDureeAbonnement().equalsIgnoreCase("Mensuel")){
            dateActuelle2 = dateActuelle2.plusMonths(1);
        }
        else if (t.getDureeAbonnement().equalsIgnoreCase("Annuel")) {
            dateActuelle2 = dateActuelle2.plusYears(1);
        }
        java.sql.Date finAbo = Date.valueOf(dateActuelle1);    
        abonnements.add(t);
        Reservation r = new Reservation(debutAbo, finAbo, abonnements);
        rse.ajouter(r);
        t.setCount(t.getCount() + 1);
        showReservation1();
        
                   
}
}