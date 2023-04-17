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
import pidev_javafx.entitie.User;
import pidev_javafx.tools.MaConnection;

/**
 *
 * @author khali
 */
public class UserService implements CrudInterface<User> {
    Connection cnx;
    public UserService(){
    cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouter(User t) {
         String sql="insert into User(email,roles,password,nom,prenom,private_key,status)values(?,?,?,?,?,?,?)";
            PreparedStatement ste ;
            try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getEmail());
            ste.setString(2, t.getRole());
            ste.setString(3, t.getPassword());
            ste.setString(4, t.getNom());
            ste.setString(5, t.getPrenom());
            ste.setInt(6, t.getPrivate_key());
            ste.setBoolean(7, t.isStatus());

            ste.executeUpdate();
            
       
            
            System.out.println("Utilisateur ajouté avec succès");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

    @Override
    public void supprimer(User t) {
         String sql = "delete from User where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            
            ste.executeUpdate();
            System.out.println("utilisateur supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(User t) {
            String sql = "UPDATE User SET nom=?, prenom=?, email=? , password=? WHERE id=?";
            PreparedStatement ste ;
            try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1,t.getNom());
            ste.setString(2,t.getPrenom());
            ste.setString(3, t.getEmail());
            ste.setString(4, t.getPassword());
            ste.setInt(5, t.getId());
            ste.executeUpdate();
            System.out.println("User modifiée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<User> afficher() {
        ObservableList<User> users =FXCollections.observableArrayList();

        String sql ="select * from User";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
            User u = new User(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("roles"),
                    rs.getString("password"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getInt("private_key"),
                    rs.getBoolean("status"));

            users.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
        
    }    
    
        ////optenir le user par id passer en parametre
    
        public User getUserParId(int id){
        String sql="select * from user where id=?";
        PreparedStatement ste;
        try{
            ste=cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet rs= ste.executeQuery();
            if(rs.next()){
                User u = new User(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("roles"),
                    rs.getString("password"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getInt("private_key"),
                    rs.getBoolean("status"));
                return u;
            }
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
}