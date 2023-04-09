/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev_javafx.entitie.Category;
import pidev_javafx.entitie.Produit;
import pidev_javafx.service.CategoryService;
import pidev_javafx.service.ProduitService;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author damma
 */
public class ProduitController implements Initializable {

    @FXML
    private DatePicker dateprod;
    @FXML
    private TextField nomprod;
    @FXML
    private TextField quantprod;
    @FXML
    private TextField prixprod;
    @FXML
    private TextField imgprod;
    @FXML
    private ComboBox<String> catprod;
    @FXML
    private TextArea descprod;
    @FXML
    private Button addprod;
    @FXML
    private Button imgfileprod;
    CategoryService cs;
    ProduitService ps;
    private File selectedFile = null;
    @FXML
    private Button btnaff;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cs=new CategoryService();
        ObservableList<Category> l= cs.afficher();
        List<String> nc = new ArrayList<>();
            for (Category category : l) {
                String s=category.getId()+":"+category.getNomCategory() ;
                 nc.add(s);
            }
            System.out.println(nc);
            
      catprod.setItems(FXCollections.observableList(nc));
      catprod.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
      dateprod.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
      nomprod.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
      catprod.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
      descprod.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
      quantprod.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
      prixprod.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
      imgprod.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
//        //System.out.println(l.get(2));
//        catprod.setItems(l.stream()
//                .map(row->row[1])
//                .collect(Collectors.toList()));
  }    

    @FXML
    private void addprod(ActionEvent event) {
        String chaine = catprod.getValue();
        int index = chaine.indexOf(":");
        String sousChaine = chaine.substring(0, index);
        System.out.println(sousChaine);
        ps=new ProduitService();
        Category cat1=cs.getCatParId(Integer.parseInt(sousChaine));
        System.out.println(cat1);
        int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
        String newFileName = random_int+"-"+selectedFile.getName();
        Produit p=new Produit(cat1, nomprod.getText(), descprod.getText(),Float.parseFloat(prixprod.getText()), Integer.parseInt(quantprod.getText()),newFileName,java.sql.Date.valueOf(dateprod.getValue()));
        System.out.println(p);
        ps.ajouter(p);
        Path sourceFile = Paths.get(selectedFile.toPath().toString());
        Path targetFile = Paths.get(Statics.uploadDirectory1    +newFileName);
        try {
            Files.copy(sourceFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/DisplayProduct.fxml"));
            Parent root;
        try {
            root = loader.load();
            descprod.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void addimfprod(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser(); //outil eli nekhdhou bih el fichier
        final Stage stage = null;// el fenetre eli bech tethal 

        File file = fileChooser.showOpenDialog(stage); //halina el fenetre w recuperina el fichier
        if (file != null) { //ntestiow est ce que fichier null wale
            //Image image1 = new Image(file.toURI().toString());
            //addImage.setImage(image1);//badalna el image
            imgprod.setText(file.getName()); //badalna el input
            selectedFile = file;
        }
    }

    @FXML
    private void affonclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/DisplayProduct.fxml"));
            Parent root;
        try {
            root = loader.load();
            descprod.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
