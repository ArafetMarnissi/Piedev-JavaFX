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
import pidev_javafx.entitie.Produit;
import pidev_javafx.tools.MaConnection;

/**
 *
 * @author marni
 */
public class ProduitService {
   Connection cnx;
    public ProduitService(){
    cnx = MaConnection.getInstance().getCnx();
    }
    
    ///Get Produit Par Id
 /*   
    public Produit getProduitParId(int id) {
   String sql = "SELECT * FROM produit WHERE id = ?";
    PreparedStatement ste;
    try {
        ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
        if (rs.next()) {
            Produit c = new Produit(
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
*/
}
