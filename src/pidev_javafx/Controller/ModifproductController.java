/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev_javafx.entitie.Category;
import pidev_javafx.entitie.Produit;
import pidev_javafx.service.CategoryService;
import pidev_javafx.service.ProduitService;

/**
 * FXML Controller class
 *
 * @author damma
 */
public class ModifproductController implements Initializable {

    @FXML
    private TextField textnommodif;
    @FXML
    private TextField textprixmodif;
    @FXML
    private TextField textquantmodif;
    @FXML
    private TextField textimgmodif;
    @FXML
    private TextArea textdescmodif;
    @FXML
    private DatePicker datemodif;
    @FXML
    private Button btnimgmofig;
    @FXML
    private Button btnmodif;
    @FXML
    private ComboBox<String> textcatmodif;
private File selectedFile = null;
Produit p1;
CategoryService cs=new CategoryService();
    ProduitService ps=new ProduitService();
    @FXML
    private FontAwesomeIconView closeicon;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategoryService cs=new CategoryService();
        ObservableList<Category> l= cs.afficher();
        List<String> nc = new ArrayList<>();
            for (Category category : l) {
                String s=category.getId()+":"+category.getNomCategory() ;
                 nc.add(s);
            }
            //System.out.println(nc);
            
      textcatmodif.setItems(FXCollections.observableList(nc));
      datemodif.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
      textcatmodif.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
      textdescmodif.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
      textimgmodif.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
      textnommodif.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
      textprixmodif.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
      textquantmodif.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
      
    }    

    @FXML
    private void addimgonclickmodif(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser(); //outil eli nekhdhou bih el fichier
        final Stage stage = null;// el fenetre eli bech tethal 

        File file = fileChooser.showOpenDialog(stage); //halina el fenetre w recuperina el fichier
        if (file != null) { //ntestiow est ce que fichier null wale
            //Image image1 = new Image(file.toURI().toString());
            //addImage.setImage(image1);//badalna el image
            textimgmodif.setText(file.getName()); //badalna el input
            selectedFile = file;
        }
    }

    @FXML
    private void btnmodifonclick(ActionEvent event) {
            if(!textimgmodif.getText().equals(p1.getImage_produit())){
                int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
                String newFileName = random_int+"-"+selectedFile.getName();
                String chaine = textcatmodif.getValue();
                int index = chaine.indexOf(":");
                String sousChaine = chaine.substring(0, index);
                Category cat1=cs.getCatParId(Integer.parseInt(sousChaine));
                //System.out.println(sousChaine);
                Produit pp;
                pp = new Produit(p1.getId(),cat1 , textnommodif.getText(), textdescmodif.getText(), Float.parseFloat(textprixmodif.getText()), Integer.parseInt(textquantmodif.getText()), newFileName, java.sql.Date.valueOf(datemodif.getValue()));
                System.out.println(pp);
                ps.modifier(pp);
            }
            else{
                String chaine = textcatmodif.getValue();
                int index = chaine.indexOf(":");
                String sousChaine = chaine.substring(0, index);
                Category cat2=cs.getCatParId(Integer.parseInt(sousChaine));
                Produit ppp=new Produit(p1.getId(),cat2 , textnommodif.getText(), textdescmodif.getText(), Float.parseFloat(textprixmodif.getText()), Integer.parseInt(textquantmodif.getText()), textimgmodif.getText(), java.sql.Date.valueOf(datemodif.getValue()));
                System.out.println(ppp);
                ps.modifier(ppp);
            }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/DisplayProduct.fxml"));
            DisplayProductController dpc= loader.getController();
            //dpc.afficherprodfxml();
            Stage stage = (Stage) closeicon.getScene().getWindow();
            stage.close();
            Parent root = loader.load();
            Stage stage1 = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Product");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifproductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getdata(Produit p){
        textnommodif.setText(p.getNom());
        String box=p.getCategory().getId()+":"+p.getCategory().getNomCategory();
        textcatmodif.setValue(box);
        textdescmodif.setText(p.getDescription());
        textimgmodif.setText(p.getImage_produit());
        textprixmodif.setText(Float.toString(p.getPrix_produit()));
        textquantmodif.setText(Integer.toString(p.getQuantite_produit()));
        datemodif.setValue(p.getDate_expiration().toLocalDate());
        p1=p;
    } 

    @FXML
    private void closemodif(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/DisplayProduct.fxml"));
            DisplayProductController dpc= loader.getController();
            //dpc.afficherprodfxml();
            Stage stage = (Stage) closeicon.getScene().getWindow();
            stage.close();
            Parent root = loader.load();
            Stage stage1 = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Product");
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(ModifproductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
