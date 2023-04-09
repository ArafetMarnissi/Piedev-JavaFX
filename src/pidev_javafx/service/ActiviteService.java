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
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev_javafx.entitie.Activite;
import pidev_javafx.entitie.Coach;
import pidev_javafx.tools.MaConnection;

/**
 *
 * @author mmarr
 */
public class ActiviteService implements CrudInterface<Activite>{

    Connection cnx;

    public ActiviteService() {
         cnx=MaConnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Activite t) {
        String SQL="INSERT INTO activite(nom_acitivite,description_activite,nbre_place,image,coach_id,date_activite,time_activite,end) values (?,?,?,?,?,?,?,?)";
        PreparedStatement ste;
        try{
            ste=cnx.prepareStatement(SQL);
        ste.setString(1, t.getNomActivite());
        ste.setString(2, t.getDescriptionActivite());
        ste.setInt(3, t.getNbrePlace());
        ste.setString(4, t.getImage());
        ste.setInt(5, t.getCoach().getId());
        ste.setDate(6, t.getDateActivite());
        ste.setTime(7, t.getTimeActivite());
        ste.setTime(8, t.getEnd());
        ste.executeUpdate();
            System.out.println("ajouter");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Activite> afficher() {
                ObservableList<Activite> c=FXCollections.observableArrayList();
        String SQL="SELECT * FROM activite";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet res= ste.executeQuery(SQL);
            CoachService cs=new CoachService();
            /*Coach a=new Coach();
            a=cs.getCoachById(res.getInt("coach_id"));*/
            while(res.next()){
                Activite co=new Activite(res.getInt(1),res.getInt("nbre_place"), res.getString("nom_acitivite"),res.getString("description_activite"),res.getString("image"),res.getDate("date_activite"),res.getTime("time_activite"),res.getTime("end"));
                c.add(co);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return c;
    }

    @Override
    public void supprimer(Activite t) {
                String SQL="delete from activite where id=?";
        try {
            PreparedStatement ste=cnx.prepareStatement(SQL);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Activite t) {
        String sql="update activite set nom_acitivite=?,description_activite=?,nbre_place=?,image=?,coach_id=?,date_activite=?,time_activite=?,end=? where id=? ";
        PreparedStatement ste ;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNomActivite());
            ste.setInt(9, t.getId());
            ste.setString(2, t.getDescriptionActivite());
            ste.setInt(3, t.getNbrePlace());
            ste.setString(4, t.getImage());
            ste.setInt(5, t.getCoach().getId());
            ste.setDate(6, t.getDateActivite());
            ste.setTime(7, t.getTimeActivite());
            ste.setTime(8, t.getEnd());
            ste.executeUpdate();
            System.out.println("Coach modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
