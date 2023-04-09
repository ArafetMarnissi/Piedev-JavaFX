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
import pidev_javafx.entitie.Category;
import pidev_javafx.entitie.Produit;
import pidev_javafx.tools.MaConnection;
import pidev_javafx.entitie.Category;


/**
 *
 * @author marni
 */
public class ProduitService implements CrudInterface<Produit>{
    Connection cnx;
    public ProduitService() {
       cnx=MaConnection.getInstance().getCnx();
    }
    

    @Override
    public ObservableList<Produit> afficher() {
        ObservableList<Produit>prod=FXCollections.observableArrayList(); 
        String sql="select * from produit";
        //Category cat=new Category();
        CategoryService cs=new CategoryService();
        //cat=cs.getCatParId(0);
        try {
            Statement ste=cnx.createStatement();
            ResultSet rs= ste.executeQuery(sql);
            while(rs.next()){
                Produit c=new Produit(rs.getInt(1),cs.getCatParId(rs.getInt(2)),rs.getString(3), rs.getString(4),rs.getFloat(5),rs.getInt(6),rs.getString(7),rs.getDate(8),rs.getFloat(9));
                prod.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return prod;
    }

    @Override
    public void ajouter(Produit t) {
        System.out.println(t);
        String sql="insert into produit(category_id ,nom,description,prix_produit,quantite_produit,image_produit,date_expiration,note) values (?,?,?,?,?,?,?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setObject(1, t.getCategory().getId());
            ste.setString(2, t.getNom());
            ste.setString(3, t.getDescription());
            ste.setFloat(4, t.getPrix_produit());
            ste.setInt(5, t.getQuantite_produit());
            ste.setString(6, t.getImage_produit());
            ste.setDate(7, t.getDate_expiration());
            ste.setFloat(8, 0.f);
            ste.executeUpdate();
            System.out.println("produit Ajouter");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void supprimer(Produit t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Produit t) {
        String sql="update produit set category_id=?,nom=?,description=?,prix_produit=?,quantite_produit=?,image_produit=?,date_expiration=? where id=? ";
        PreparedStatement ste ;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setObject(1, t.getCategory().getId());
            ste.setString(2, t.getNom());
            ste.setString(3, t.getDescription());
            ste.setFloat(4, t.getPrix_produit());
            ste.setInt(5, t.getQuantite_produit());
            ste.setString(6, t.getImage_produit());
            ste.setDate(7, t.getDate_expiration());
            ste.setInt(8, t.getId());
            ste.executeUpdate();
            System.out.println("produit modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ObservableList<Produit> findprodbycat(int idprod){
        ObservableList<Produit>prod=FXCollections.observableArrayList(); 
        String sql="select * from produit where category_id=?";
        PreparedStatement ste;
        CategoryService cs=new CategoryService();
        try{
            ste=cnx.prepareStatement(sql);
            ste.setInt(1, idprod);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Produit c=new Produit(rs.getInt(1),cs.getCatParId(rs.getInt(2)),rs.getString(3), rs.getString(4),rs.getFloat(5),rs.getInt(6),rs.getString(7),rs.getDate(8),rs.getFloat(9));
                prod.add(c);
            }
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return prod;
    }

   
}
