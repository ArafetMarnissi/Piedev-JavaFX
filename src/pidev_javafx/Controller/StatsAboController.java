/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import pidev_javafx.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author saifz
 */
public class StatsAboController implements Initializable {

    @FXML
    private PieChart AboStat;
    
    private Statement st;
    private ResultSet rs;
    private Connection cnx;
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    @FXML
    private Button btnActualiser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       cnx = MaConnection.getInstance().getCnx();
       stat();
       stat2();
       //btnActualiser.setOnAction(event -> {stat1();});
    }    
    @FXML
    private void stat() {
        data.removeAll(data);
          try{
           
           String query = "SELECT `nom_abonnement`, `count`, `duree_abonnement` FROM abonnement ;";


           PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
            while (rs.next()){               
                data.add(new PieChart.Data(rs.getString("nom_abonnement") + " - " + rs.getString("duree_abonnement"), rs.getInt("count")));
            }
 
             
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
        
        AboStat.setTitle("** Statistiques des abonnements les plus réservés **");
        AboStat.setLegendSide(Side.LEFT);
        AboStat.setData(data);
        stat2();
    }
 private void stat2(){
 }  
    
}
