/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import pidev_javafx.entitie.Produit;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author damma
 */
public class DetailprodController implements Initializable {

    @FXML
    private ImageView prodimgview;
    @FXML
    private Label nomprodlabel;
    @FXML
    private Label prixprodlabel;
    @FXML
    private Label descriptionprodlabel;
    @FXML
    private Label dispoprodlabel;
    @FXML
    private AnchorPane pan;
    @FXML
    private HBox hboxlabe;
    @FXML
    private VBox vboxlabe;
    @FXML
    private FontAwesomeIconView returnprod;
    Produit pr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setlabelprod(Produit p){
        
        try {
            prodimgview.setImage(new Image(new FileInputStream(Statics.uploadDirectory1+p.getImage_produit())));
            prodimgview.setFitWidth(200);
            prodimgview.setFitHeight(200);
            prodimgview.setPreserveRatio(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailprodController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pr=p;
        nomprodlabel.setText(p.getNom());
        nomprodlabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        prixprodlabel.setText(String.format("%.2f DT", p.getPrix_produit()));
        prixprodlabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        descriptionprodlabel.setText(p.getDescription());
        descriptionprodlabel.setWrapText(true);
        descriptionprodlabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        if(p.getQuantite_produit()==0){
            dispoprodlabel.setText("Out Of Stock");
            dispoprodlabel.setTextFill(Color.RED);
        }else{
            dispoprodlabel.setText("In Stock");
            dispoprodlabel.setTextFill(Color.GREEN);
        }
//        VBox labelsVBox = new VBox(10, nomprodlabel, prixprodlabel, descriptionprodlabel, dispoprodlabel);
//        HBox contentHBox = new HBox(20, prodimgview, labelsVBox);
//        //contentHBox.setPadding(new Insets(20));
//        hboxlabe.getChildren().add(contentHBox);
//        vboxlabe.getChildren().addAll(nomprodlabel, prixprodlabel, descriptionprodlabel, dispoprodlabel);
//        Pane parentPane = new Pane(contentHBox);
//        parentPane.setPrefSize(500, 500);
        
        
        
        
}

    @FXML
    private void returnprodonclic(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/ProdByCat.fxml"));
                try{
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Product");
                    ProdByCatController pbcc=loader.getController();
                    //DisplayProductController dpc=loader.getController();
                    pbcc.cat(pr.getCategory().getId());
                    Stage stage1 = (Stage) pan.getScene().getWindow();
                    stage1.close();
                            stage.show();
                }catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
    }
    
    
}
