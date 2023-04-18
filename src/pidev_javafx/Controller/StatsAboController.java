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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import pidev_javafx.entitie.Abonnement;
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
    
    @FXML
    private BarChart<?, ?> chart;
    @FXML
    private NumberAxis NumberAxis;
    @FXML
    private CategoryAxis CategoryAxis;
    
    ObservableList<Abonnement>data1=FXCollections.observableArrayList();
    List<Integer> reservantList = new ArrayList<Integer>();
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
        try {
            Connection cnx = MaConnection.getInstance().getCnx();
            String query = "SELECT * FROM abonnement ;";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Abonnement event;
            data1.clear();
            reservantList.clear();
            chart.getData().clear();
            
            while (rs.next()) {
                Abonnement a= new Abonnement(rs.getInt("id"),
                    rs.getString("nom_abonnement"),
                    rs.getFloat("prix_abonnement"),
                    rs.getString("duree_abonnement"),
                    rs.getInt("count"));
                data1.add(a);
            }
            

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        
        for (Abonnement e : data1){
            int nbrparticipants = e.getCount();
            reservantList.add(nbrparticipants);
        }
        XYChart.Series setl = new XYChart.Series<>();
        for (int i = 0; i < data1.size(); i++){
            String label = data1.get(i).getNomAbonnement() + "-" + data1.get(i).getDureeAbonnement();
            setl.getData().add(new XYChart.Data(label, reservantList.get(i)));
        }

        
            
        NumberAxis yAxis = new NumberAxis(0, 100, 10);
         
        chart.getData().addAll(setl);     
 } 
    
}
