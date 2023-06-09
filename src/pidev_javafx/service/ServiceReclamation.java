/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.service;

import pidev_javafx.entitie.Reclamation;
import pidev_javafx.entitie.TypeReclamation;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import pidev_javafx.service.IService;
import pidev_javafx.tools.MaConnection;

/**
 *
 * @author Skymil
 */
public class ServiceReclamation implements IService<Reclamation>{
    Connection conn;
    public ServiceReclamation(){
        conn=MaConnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Reclamation t) {
        try {
            
            
            String query="INSERT INTO `reclamation`"
                    + "(`description_reclamation`, `type_reclamation`,"
                    + " `date_reclamtion`) "
                    + "VALUES ('"+FilterBadWord.filter(t.getDescription_reclamation())+"',"
                    + "'"+t.getType_reclamation()+"','"+t.getDate_reclamation()+"')";
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(int id, Reclamation t) {
        try {
            
            
            String query="UPDATE `reclamation` SET "
                    + "`description_reclamation`='"+FilterBadWord.filter(t.getDescription_reclamation())+"',"
                    + "`type_reclamation`='"+t.getType_reclamation()+"',"
                    + "`date_reclamtion`='"+t.getDate_reclamation()+"' WHERE id="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String query="DELETE FROM `reclamation` WHERE id="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reclamation> afficher() {
        List<Reclamation> lr=new ArrayList<>();
        try {
            String query="SELECT * FROM `reclamation`";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reclamation r=new Reclamation();
                Date d=new Date();
                try {
                    SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yy");
                    d=dateFormat.parse(rs.getString("date_reclamtion"));
                } catch (ParseException ex) {
                    Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                r.setDate_reclamation(d);
                r.setDescription_reclamation(rs.getString("description_reclamation"));
                r.setId(rs.getInt("id"));
                r.setType_reclamation(TypeReclamation.valueOf(rs.getString("type_reclamation")));
                lr.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lr;
    }
    public List<Reclamation> tri(){
        
        return afficher()
                .stream()
                .sorted(Comparator.comparing(Reclamation::getDescription_reclamation))
                .collect(Collectors.toList());
    }
    
}
