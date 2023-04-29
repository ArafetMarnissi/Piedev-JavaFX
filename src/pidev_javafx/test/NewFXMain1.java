/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.test;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pidev_javafx.entitie.Produit;
import pidev_javafx.entitie.SMS;
import pidev_javafx.service.ProduitService;

/**
 *
 * @author damma
 */
public class NewFXMain1 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/pidev_javafx.GUI/AllProduct.fxml"));
            Scene scene = new Scene(root, 1000, 700);
            primaryStage.setTitle("Categories");
            primaryStage.setScene(scene);
            ProduitService ps=new ProduitService();
        ObservableList<Produit>listprod=FXCollections.observableArrayList();
        listprod=ps.afficher();
        SMS ss=new SMS();
        for(Produit p:listprod){
            if(p.getQuantite_produit()>0 && p.getQuantite_produit()<5){
               // ss.sms(p.getNom());
            }
        }
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
