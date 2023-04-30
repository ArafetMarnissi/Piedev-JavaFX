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
import pidev_javafx.entitie.Commande;
import pidev_javafx.entitie.LigneCommande;
import pidev_javafx.entitie.User;
import pidev_javafx.tools.MaConnection;

/**
 *
 * @author marni
 */
public class LigneCommandeService implements CrudInterface<LigneCommande>{
    Connection cnx;
    public LigneCommandeService(){
    cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(LigneCommande t) {

            
            String sql="insert into ligne_commande(commande_id,produits_id,quantite_produit,prix_unitaire)values(?,?,?,?)";
            PreparedStatement ste ;
            try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getCommande().getId());
            ste.setInt(2, t.getProduit().getId());
            ste.setInt(3, t.getQuantite_produit());
            ste.setFloat(4,t.getPrix_unitaire());
            ste.executeUpdate();
            System.out.println("Ligne commande  ajouté");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    
        
    }

    @Override
    public ObservableList<LigneCommande> afficher() {
        ObservableList <LigneCommande> Ligne_commandes = FXCollections.observableArrayList();
        
        String sql ="select * from ligne_commande";
        Statement ste;
        CommandeService CS = new CommandeService();
        ProduitService PS = new ProduitService();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
            LigneCommande c = new LigneCommande(rs.getInt("id"),
                    CS.getCommandeParId(rs.getInt("commande_id")),
                    PS.findprodbyid(rs.getInt("produits_id")),
                    rs.getInt("quantite_produit"),
                    rs.getFloat("prix_unitaire")
                    );
            Ligne_commandes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Ligne_commandes;
        
    }

    @Override
    public void supprimer(LigneCommande t) {
        String sql = "delete from ligne_commande where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
            System.out.println("Ligne commande supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

/// a utiliser sont on est besoin
    
    @Override
    public void modifier(LigneCommande t) {
      /*      String sql = "UPDATE commande SET user_id=?, adresse_livraison=?, date_commande=?, prix_commande=?, methode_paiement=?, telephone=? WHERE id=?";
            PreparedStatement ste ;
            try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getUser_id());
            ste.setString(2, t.getAdresse_livraison());
            ste.setString(3, t.getDate_commande());
            ste.setFloat(4,t.getPrix_commande());
            ste.setString(5, t.getMethode_paiement());
            ste.setInt(6, t.getTelephone());
            ste.setInt(7, t.getId());
            ste.executeUpdate();
            System.out.println("LigneCommande modifiée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        */
    }

    
       public ObservableList<LigneCommande>  afficherLigneCommandesParCommande(Commande t) {
        ObservableList <LigneCommande> Lignecommandes = FXCollections.observableArrayList();
        
        String sql ="select * from ligne_commande where commande_id="+t.getId();
        PreparedStatement ste ;
        CommandeService CS = new CommandeService();
        ProduitService PS = new ProduitService();
        try {
            ste = cnx.prepareStatement(sql);
            //ste.setInt(1,u.getId());
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
            LigneCommande c = new LigneCommande(rs.getInt("id"),
                    CS.getCommandeParId(rs.getInt("commande_id")),
                    PS.findprodbyid(rs.getInt("produits_id")),
                    rs.getInt("quantite_produit"),
                    rs.getFloat("prix_unitaire")
                    );
            Lignecommandes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Lignecommandes;
        
    }
        public List <LigneCommande>  getLigneCommandesParCommande(Commande t) {
        List <LigneCommande> Lignecommandes = new ArrayList<>();
        
        String sql ="select * from ligne_commande where commande_id="+t.getId();
        PreparedStatement ste ;
        CommandeService CS = new CommandeService();
        ProduitService PS = new ProduitService();
        try {
            ste = cnx.prepareStatement(sql);
            //ste.setInt(1,u.getId());
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
            LigneCommande c = new LigneCommande(rs.getInt("id"),
                    CS.getCommandeParId(rs.getInt("commande_id")),
                    PS.findprodbyid(rs.getInt("produits_id")),
                    rs.getInt("quantite_produit"),
                    rs.getFloat("prix_unitaire")
                    );
            Lignecommandes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Lignecommandes;
        
    }




}
