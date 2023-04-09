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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev_javafx.entitie.Coach;
import pidev_javafx.tools.MaConnection;

/**
 *
 * @author mmarr
 */
public class CoachService implements CrudInterface<Coach> {

     Connection cnx;

    public CoachService() {
        cnx=MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Coach t) {
        String SQL="INSERT INTO coach(nom_coach,age_coach,image) values (?,?,?)";
        PreparedStatement ste;
        try{
            ste=cnx.prepareStatement(SQL);
        ste.setString(1, t.getNom_coach());
        ste.setInt(2, t.getAge_coach());
        ste.setString(3, t.getImage());
        ste.executeUpdate();
            System.out.println("ajouter");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Coach> afficher() {
        ObservableList<Coach> c=FXCollections.observableArrayList();
        String SQL="SELECT * FROM coach";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet res= ste.executeQuery(SQL);
            while(res.next()){
                Coach co=new Coach(res.getInt(1), res.getInt("age_coach"),res.getString("nom_coach"));
                c.add(co);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return c;
    }

    @Override
    public void supprimer(Coach t) {
         String SQL="delete from coach where id=?";
        try {
            PreparedStatement ste=cnx.prepareStatement(SQL);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Coach t) {
     
        String sql="update coach set nom_coach=?,age_coach=?,image=? where id=? ";
        PreparedStatement ste ;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNom_coach());
            ste.setInt(4, t.getId());
            ste.setInt(2, t.getAge_coach());
            ste.setString(3, t.getImage());
            ste.executeUpdate();
            System.out.println("Coach modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   public Coach getCoachById(int id) {
    String sql = "SELECT * FROM coach WHERE id = ?";
    PreparedStatement ste;
    try {
        ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
        if (rs.next()) {
            Coach c = new Coach(
                    rs.getInt("id"),
                    rs.getInt("age_coach"),
                    rs.getString("nom_coach"),
                    rs.getString("image")
                   
            );
            return c;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
   }
   
       public List<Coach> getAllCoachs() {
        ObservableList<Coach> c=FXCollections.observableArrayList();
        String SQL="SELECT * FROM coach";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet res= ste.executeQuery(SQL);
            while(res.next()){
                Coach co=new Coach(res.getInt(1), res.getInt("age_coach"),res.getString("nom_coach"),res.getString("image"));
                c.add(co);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return c;
    }
    
}
