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
    
   public boolean userExists(User t ) {
    boolean exists = false;
    try {
        // Assuming you have a connection object called "conn" to the database
        PreparedStatement stmt = cnx.prepareStatement("SELECT COUNT(*) FROM User WHERE email = ?");
        stmt.setString(1, t.getEmail());
        ResultSet rs = stmt.executeQuery();
        rs.next();
        if (rs.getInt(1) > 0) {
            exists = true;
        }
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception here, e.g. log it or display an error message to the user
    }
    return exists;
}
 public boolean login(String email,String password)
    {
       boolean exists = false;
       
    try {
        PreparedStatement stmt = cnx.prepareStatement("SELECT COUNT(*) FROM User WHERE email = ? and password = ?");
        stmt.setString(1,email);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        if (rs.getInt(1) > 0) {
            
            exists = true;
            User us=getUserParEmail(email);
            System.out.println(us.toString());
            // Session Start
            SessionManager.setId(us.getId());
            SessionManager.setEmail(us.getEmail());
            SessionManager.setNom(us.getNom());
            SessionManager.setPrenom(us.getPrenom());
            SessionManager.setPassword(us.getPassword());
            SessionManager.setPrivate_key(us.getPrivate_key());
            SessionManager.setStatus(us.isStatus());
            SessionManager.setRole(us.getRole());
            
        }
                System.out.println(rs.getArray(email));

        rs.close();
        stmt.close();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
        System.out.println(exists);
        
    return exists; 
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
            String sql = "UPDATE User SET nom=?, prenom=?, email=? , password=?, status=? WHERE id=?";
            PreparedStatement ste ;
            try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1,t.getNom());
            ste.setString(2,t.getPrenom());
            ste.setString(3, t.getEmail());
            ste.setString(4, t.getPassword());
            ste.setBoolean(5, t.isStatus());
            ste.setInt(6, t.getId());
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
    
        public User getUserParEmail(String email){
        String sql="select * from user where email=?";
        PreparedStatement ste;
        try{
            ste=cnx.prepareStatement(sql);
            ste.setString(1, email);
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
