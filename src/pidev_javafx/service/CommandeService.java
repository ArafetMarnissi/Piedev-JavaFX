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
import pidev_javafx.entitie.Commande;
import pidev_javafx.entitie.User;
import pidev_javafx.tools.MaConnection;

/**
 *
 * @author marni
 */


public class CommandeService implements CrudInterface<Commande> {
    Connection cnx;
    public CommandeService(){
    cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Commande t) {

            
            String sql="insert into commande(user_id,adresse_livraison,date_commande,prix_commande,methode_paiement,telephone)values(?,?,?,?,?,?)";
            PreparedStatement ste ;
            try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getUser_id());
            ste.setString(2, t.getAdresse_livraison());
            ste.setString(3, t.getDate_commande());
            ste.setFloat(4,t.getPrix_commande());
            ste.setString(5, t.getMethode_paiement());
            ste.setInt(6, t.getTelephone());
            ste.executeUpdate();
            System.out.println("Commande ajouté");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    
        
    }

    @Override
    public List<Commande> afficher() {
        List<Commande> commandes =new ArrayList<>();
        String sql ="select * from commande";
        Statement ste;
        try {
            ste = cnx.createStatement();
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

    @Override
    public void supprimer(Commande t) {
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
    public void modifier(Commande t) {
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
            System.out.println("Commande modifiée");
            
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
    //recuperer la commande par son id
       
   public Commande getCommandeParId(int id) {
    String sql = "SELECT * FROM commande WHERE id = ?";
    PreparedStatement ste;
    try {
        ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
        if (rs.next()) {
            Commande c = new Commande(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("date_commande"),
                    rs.getString("adresse_livraison"),
                    rs.getFloat("prix_commande"),
                    rs.getString("methode_paiement"),
                    rs.getInt("telephone")
            );
            return c;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}

       


    
}
