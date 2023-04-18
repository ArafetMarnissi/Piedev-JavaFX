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

 * @author Fayechi
=======
 * @author marni

 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
<<<<<<< HEAD
=======
    
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DashbordFront.fxml"));
            //Scene scene = new Scene(root, 870, 584);
            Scene scene = new Scene(root, 1095, 665);
>>>>>>> b9c67ec267e5e87014dcd39fab13bc063408a180

        try {
            Parent root = FXMLLoader.load(getClass().
                    getResource("DashbordBack.fxml"));
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Espace admin");
            primaryStage.setScene(scene);
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
