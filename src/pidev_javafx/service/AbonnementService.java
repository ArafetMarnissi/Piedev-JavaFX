/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pidev_javafx.entitie.Abonnement;
import pidev_javafx.entitie.Commande;
import pidev_javafx.tools.MaConnection;

/**
 *
 * @author saifz
 */
public class AbonnementService implements CrudInterface<Abonnement>{
    Connection cnx;
    public AbonnementService(){
    cnx = MaConnection.getInstance().getCnx();}

    @Override
    public void ajouter(Abonnement t) {
            String sql="insert into abonnement(nom_abonnement,prix_abonnement,duree_abonnement,count)values(?,?,?,?)";
            PreparedStatement ste ;
            try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNomAbonnement());
            ste.setFloat(2, t.getPrixAbonnement());
            ste.setString(3, t.getDureeAbonnement());
            ste.setInt(4, t.getCount());

            ste.executeUpdate();
            System.out.println("Abonnement ajouté");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Abonnement> afficher() {
        List<Abonnement> abonnements =new ArrayList<>();
        String sql ="select * from abonnement";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
            Abonnement c = new Abonnement(rs.getInt("id"),
                    rs.getString("nom_abonnement"),
                    rs.getFloat("prix_abonnement"),
                    rs.getString("duree_abonnement"),
                    rs.getInt("count")
            );
            abonnements.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return abonnements;
    }
    
    

    @Override
    public void supprimer(Abonnement t) {
        String sql = "delete from abonnement where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            int i=ste.executeUpdate();
            System.out.println(i+ "abonnement supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Abonnement t) {
            String sql = "UPDATE abonnement SET nom_abonnement=?, prix_abonnement=?, duree_abonnement=?, count=? WHERE id=?";
            PreparedStatement ste ;
            try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1,t.getNomAbonnement());
            ste.setFloat(2,t.getPrixAbonnement());            
            ste.setString(3, t.getDureeAbonnement());
            ste.setInt(4, t.getCount());
            ste.setInt(5, t.getId());
            ste.executeUpdate();
            System.out.println("Abonnement modifiée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
