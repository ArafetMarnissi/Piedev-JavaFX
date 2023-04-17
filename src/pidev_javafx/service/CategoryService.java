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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev_javafx.entitie.Category;
import pidev_javafx.tools.MaConnection;

/**
 *
 * @author damma
 */
public class CategoryService implements CrudInterface<Category>{
    Connection cnx;
    
    public CategoryService(){
        cnx=MaConnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Category t) {
        String sql="insert into category(nom_category,image_categorie) values (?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNomCategory());
            ste.setString(2, t.getImageCategory());
            ste.executeUpdate();
            System.out.println("Category Ajouter");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    @Override
    public ObservableList<Category> afficher() {
        //List<Category> cat=new ArrayList<>();
        ObservableList<Category>cat=FXCollections.observableArrayList(); 
        String sql="select * from category";
        try {
            Statement ste=cnx.createStatement();
            ResultSet rs= ste.executeQuery(sql);
            while(rs.next()){
                Category c=new Category(rs.getInt(1),rs.getString(2), rs.getString(3));
                cat.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cat;   
    }
    
    public ObservableList<Category> affichernom() {
        //List<Category> cat=new ArrayList<>();
        ObservableList<Category>cat=FXCollections.observableArrayList(); 
        String sql="select * from category";
        try {
            Statement ste=cnx.createStatement();
            ResultSet rs= ste.executeQuery(sql);
            while(rs.next()){
                Category c=new Category(rs.getString(2));
                cat.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cat;   
    }
    
    
    public List<Category> affichercat() throws SQLException {
        List<Category> reclamations = new ArrayList<>();
        String req = "select * from category";
        Statement ste=cnx.createStatement();
        //ensemble de resultat
        ResultSet rst = ste.executeQuery(req);

        while (rst.next()) {
            Category re = new Category(
                    rst.getString("nom_category"),
                    rst.getString("image_categorie"));
            reclamations.add(re);
        }
        return reclamations;
    }
        
    @Override
    public void supprimer(Category t) {
        String sql="delete from category where id=?";
        try {
            PreparedStatement ste=cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    public void supp(int id) {
        String sql="delete from category where id=?";
        try{
            PreparedStatement ste=cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void modifier(Category t) {
        String sql="update category set nom_category=?,image_categorie=? where id=? ";
        PreparedStatement ste ;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNomCategory());
            ste.setInt(3, t.getId());
            ste.setString(2, t.getImageCategory());
            ste.executeUpdate();
            System.out.println("Categorie modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public Category getCatParId(int id) {
    String sql = "SELECT * FROM category WHERE id = ?";
    PreparedStatement ste;
    try {
        ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
        if (rs.next()) {
            Category c = new Category(
                    rs.getInt("id"),
                    rs.getString("nom_category"),
                    rs.getString("image_categorie")
            );
            return c;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}
}