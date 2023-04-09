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
import pidev_javafx.entitie.Participation;
import pidev_javafx.tools.MaConnection;

/**
 *
 * @author mmarr
 */
public class ParticipationService implements CrudInterface<Participation>{

        Connection cnx;

    public ParticipationService() {
         cnx=MaConnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Participation t) {
                String SQL="INSERT INTO participation(date_participation,activite_id,user_id) values (?,?,?)";
        PreparedStatement ste;
        try{
            ste=cnx.prepareStatement(SQL);
        ste.setDate(1, t.getDateParticipation());
        ste.setInt(2, t.getActivite().getId());
        ste.setInt(3, t.getId_u());
        ste.executeUpdate();
            System.out.println("ajouter");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    private Activite findbyid(int id)
    {
         String SQL="SELECT * FROM activite where id=?";
    PreparedStatement ste;
    try {
        ste = cnx.prepareStatement(SQL);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
        if (rs.next()) {
            Activite c = new Activite(
                    rs.getInt("id"),
                    rs.getInt("nbre_place"),
                    rs.getString("nom_acitivite"),
                    rs.getString("description_activite"),
                    
                    rs.getString("image"),
                    rs.getDate("date_activite"),
                    rs.getTime("time_activite"),
                    rs.getTime("end")
                   
            );
            return c;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
    }
    @Override
    public ObservableList<Participation> afficher() {
      ObservableList<Participation> c=FXCollections.observableArrayList();
        String SQL="SELECT * FROM participation";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet res= ste.executeQuery(SQL);
            while(res.next()){
                Activite c1=findbyid(res.getInt("activite_id"));
                Participation co=new Participation(res.getInt(1),res.getInt("user_id"),res.getDate("date_participation"),c1);
                c.add(co);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return c;
    }

    @Override
    public void supprimer(Participation t) {
                       String SQL="delete from participation where id=?";
        try {
            PreparedStatement ste=cnx.prepareStatement(SQL);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Participation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
