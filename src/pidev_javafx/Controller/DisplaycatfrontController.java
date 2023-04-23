/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import pidev_javafx.entitie.Category;
import pidev_javafx.entitie.Produit;
import pidev_javafx.service.CategoryService;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author damma
 */
public class DisplaycatfrontController implements Initializable {

    @FXML
    private FlowPane flowcatfront;
    @FXML
    private ScrollPane scrolcat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //flowcatfront.setStyle("-fx-background-color:#D3D3D3");
        CategoryService cs=new CategoryService();
        ObservableList<Category>listcat=FXCollections.observableArrayList();
        listcat=cs.afficher();     
        for(Category cat:listcat){
                VBox card=new VBox();
                
//                card.setMinHeight(150);
//                card.setMaxHeight(150);
//                card.setMinWidth(150);
//                card.setMaxWidth(150);
                card.setPrefSize(150, 150);
                scrolcat.setContent(flowcatfront);
                //card.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");
                card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
                ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory+cat.getImageCategory())));
                imageView.setFitWidth(120);
                imageView.setFitHeight(80);
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DisplaycatfrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label namelabel=new Label(cat.getNomCategory());     
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            card.getChildren().add(namelabel);
            card.setOnMouseClicked((MouseEvent e)->{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/ProdByCat.fxml"));
                try{
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Product");
                    ProdByCatController pbcc=loader.getController();
                    //DisplayProductController dpc=loader.getController();
                    pbcc.cat(cat.getId());
                    Stage stage1 = (Stage) card.getScene().getWindow();
                    stage1.close();
                            stage.show();
                }catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
            flowcatfront.getChildren().add(card);
            flowcatfront.setMargin(card, new Insets(5, 5, 5, 5));
          
                    
        }
    }    
    
}
