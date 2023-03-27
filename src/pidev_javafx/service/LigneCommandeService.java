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
/*
    @Override
    public void ajouter(LigneCommande t) {

            
            String sql="insert into ligne_commande(commande_id,produit_id,quantite_produit,prix_unitaire)values(?,?,?,?)";
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
    public List<LigneCommande> afficher() {
        List<LigneCommande> Ligne_commandes =new ArrayList<>();
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
                    PS.getProduitParId(rs.getInt("produits_id")),
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
/*
    @Override
    public void supprimer(LigneCommande t) {
        String sql = "delete from commande where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
            System.out.println("commande supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void modifier(LigneCommande t) {
            String sql = "UPDATE commande SET user_id=?, adresse_livraison=?, date_commande=?, prix_commande=?, methode_paiement=?, telephone=? WHERE id=?";
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
        
    }

    
       public List<Commande> afficherCommandesParClient(User u) {
        List<Commande> commandes =new ArrayList<>();
        
        String sql ="select * from commande where user_id="+u.getId();
        PreparedStatement ste ;
        try {
            ste = cnx.prepareStatement(sql);
            //ste.setInt(1,u.getId());
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
            Commande c = new Commande(rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("date_commande"),
                    rs.getString("adresse_livraison"),
                    rs.getFloat("prix_commande"),
                    rs.getString("methode_paiement"),
                    rs.getInt("telephone"));
            commandes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commandes;
        
    }
*/

    @Override
    public void supprimer(LigneCommande t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(LigneCommande t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter(LigneCommande t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LigneCommande> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
