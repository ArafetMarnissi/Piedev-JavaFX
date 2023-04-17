/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author marni
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DashbordFront.fxml"));
            //Scene scene = new Scene(root, 870, 584);
            Scene scene = new Scene(root, 1095, 665);

            primaryStage.setTitle("Ajouter Commande");
            primaryStage.setScene(scene);
            primaryStage.setMinHeight(665);
            primaryStage.setMinWidth(1095);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
