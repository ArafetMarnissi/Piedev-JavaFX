/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pidev_javafx.entitie.Category;
import pidev_javafx.entitie.Produit;
import pidev_javafx.service.ProduitService;

/**
 * FXML Controller class
 *
 * @author damma
 */
public class ProdbackdisplayController implements Initializable {

    @FXML
    private Button btnajoutprod;
    @FXML
    private Button modifierprodbtn;
    @FXML
    private TableView<Produit> tableprodback;
    @FXML
    private TableColumn<Produit, String> tablenom;
    @FXML
    private TableColumn<Produit, String> tablecat;
    @FXML
    private TableColumn<Produit, String> tabledesc;
    @FXML
    private TableColumn<Produit, Float> tableprix;
    @FXML
    private TableColumn<Produit, Integer> tablequant;
    @FXML
    private TableColumn<Produit, Date> tabledate;
    @FXML
    private TableColumn<Produit, Float> tablenote;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProduitService ps=new ProduitService();
        ObservableList<Produit>listprod = FXCollections.observableArrayList();
        listprod=ps.afficher();
        tablecat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getNomCategory()));
        tabledate.setCellValueFactory(new PropertyValueFactory<Produit,Date>("date_expiration"));
        tabledesc.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
        tablenom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
        tablenote.setCellValueFactory(new PropertyValueFactory<Produit,Float>("note"));
        tableprix.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix_produit"));
        tablequant.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("quantite_produit"));
        tableprodback.setItems(listprod);
        
    } 
 

    @FXML
    private void addprodclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Produit.fxml"));
            Parent root;
        try {
            root = loader.load();
            modifierprodbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateprod(ActionEvent event) {
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Modifproduct.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Edit Product");
                    ModifproductController mpc = loader.getController();
                    mpc.getdata(tableprodback.getSelectionModel().getSelectedItem());
                    Stage stage1 = (Stage) modifierprodbtn.getScene().getWindow();
                    stage1.close();
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(DisplayProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
}
