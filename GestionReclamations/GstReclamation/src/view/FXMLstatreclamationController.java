/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import gstreclamation.FXMain;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.stage.Stage;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Skymil
 */
public class FXMLstatreclamationController implements Initializable {
    ObservableList<Data> data =FXCollections.observableArrayList();
    @FXML
    private PieChart piechart;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        stat();
    }    
    public void stat(){
        try {
            String query="SELECT count(*) as nbr,type_reclamation as type from reclamation group by type_reclamation";
            Statement st=MyConnection.getInstance().getConn().createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                data.add(new Data("Type:"+rs.getString("type"),rs.getInt("nbr")));
            }
            piechart.setData(data);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLstatreclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void retour(ActionEvent event) {
        Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/view/FXMLreclamationadmin.fxml"));

            
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Golden Gym");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
