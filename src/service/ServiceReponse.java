/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Reclamation;
import entity.Reponse;
import entity.TypeReclamation;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.FilterBadWord;
import utils.MyConnection;

/**
 *
 * @author Skymil
 */
public class ServiceReponse implements IService<Reponse>{
    Connection conn;
    public ServiceReponse(){
        conn=MyConnection.getInstance().getConn();
    }
    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void ajouter(Reponse t) {
        try {
            
            String date=dateFormat.format(t.getDate_reponse());
            String query="INSERT INTO `reponse`"
                    + "(`descriptionreponse`, `date_reponse`,"
                    + " `idreclamation_id`) VALUES "
                    + "('"+FilterBadWord.filter(t.getDescriptionreponse())+"','"+date+"',"
                    + "'"+t.getIdreclamation_id()+"')";
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void modifier(int id, Reponse t) {
        try {
            
            String date=dateFormat.format(t.getDate_reponse());
            String query="UPDATE `reponse` SET "
                    + "`descriptionreponse`='"+FilterBadWord.filter(t.getDescriptionreponse())+"',"
                    + "`date_reponse`='"+date+"',"
                    + "`idreclamation_id`='"+t.getIdreclamation_id()+"' WHERE id="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void supprimer(int id) {
        try {
            
            
            String query="DELETE FROM `reponse` WHERE id="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reponse> afficher() {
        List<Reponse> lr=new ArrayList<>();
        try {
            String query="SELECT * FROM `reponse`";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reponse r=new Reponse();
                Date d=new Date();
                try {
                    d=dateFormat.parse(rs.getString("date_reponse"));
                } catch (ParseException ex) {
                    Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                r.setDate_reponse(d);
                r.setDescriptionreponse(rs.getString("descriptionreponse"));
                r.setId(rs.getInt("id"));
                r.setIdreclamation_id(rs.getInt("idreclamation_id"));
                lr.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lr;
    }
    
}
