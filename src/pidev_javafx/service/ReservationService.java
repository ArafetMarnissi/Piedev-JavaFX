/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pidev_javafx.entitie.Reservation;
import pidev_javafx.entitie.Abonnement;
import pidev_javafx.tools.MaConnection;

/**
 *
 * @author saifz
 */
public class ReservationService implements CrudInterface<Reservation> {

    Connection cnx;
    public ReservationService(){
    cnx = MaConnection.getInstance().getCnx();}    
@Override
    public void ajouter(Reservation t ) {
        String sql = "insert into reservation(reservation_abonnement_id, date_debut, date_fin) values (?, ?, ?)";   //Il construit une requête SQL pour insérer les informations de la réservation dans la table "reservation" de la base de données. La requête SQL est stockée dans la chaîne de caractères sql
        PreparedStatement ste; //Il crée un objet PreparedStatement à partir de la connexion à la base de données cnx et de la requête SQL.
        try {
            ste = cnx.prepareStatement(sql);
            ArrayList<Abonnement> abonnements = t.getAbonnements();
            if (abonnements != null && !abonnements.isEmpty()) {
                Abonnement a = abonnements.get(0); // Obtenez le premier abonnement de la liste (vous pouvez adapter cela à vos besoins)
                int abonnementId = a.getId(); // Obtenez l'ID de l'abonnement
                ste.setInt(1, abonnementId);
                ste.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
                LocalDate dateFin = LocalDate.now();
                if (a.getDureeAbonnement().equalsIgnoreCase("Mensuel")) {
                    dateFin = dateFin.plusMonths(1);
                } else if (a.getDureeAbonnement().equalsIgnoreCase("Annuel")) {
                    dateFin = dateFin.plusYears(1);
                }
                ste.setDate(3, java.sql.Date.valueOf(dateFin));
                a.setCount(a.getCount() + 1);
                ste.executeUpdate(); //Il exécute la requête SQL en appelant la méthode executeUpdate() sur l'objet PreparedStatement.
                System.out.println("Reservation ajoutée");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}




    @Override
    public List<Reservation> afficher() {
        List<Reservation> reservations =new ArrayList<>();
        String sql ="select * from reservation";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
            Reservation c = new Reservation(rs.getInt("id"),
                    rs.getDate("date_debut"),
                    rs.getDate("date_fin")
            );
            reservations.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reservations ;    
    }

    @Override
    public void supprimer(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

public void ajouter1(Reservation t, Abonnement a) {
String sql = "insert into reservation(reservation_abonnement_id, date_debut, date_fin) values (?, ?, ?)";
PreparedStatement ste;
try {
LocalDate dateActuelle1 = LocalDate.now();    
ste = cnx.prepareStatement(sql);
int abonnementId = a.getId();
ste.setInt(1, abonnementId);
ste.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
java.sql.Date dateFin = Date.valueOf(dateActuelle1) ;
 
/*if (a.getDureeAbonnement().equalsIgnoreCase("Mensuel")) {
dateFin = dateFin.plusMonths(1);
} else if (a.getDureeAbonnement().equalsIgnoreCase("Annuel")) {
dateFin = dateFin.plusYears(1);
}*/
ste.setDate(3, dateFin);
ste.executeUpdate();
System.out.println("Reservation ajoutée");
} catch (SQLException ex) {
System.out.println(ex.getMessage());
}
}
    
}
