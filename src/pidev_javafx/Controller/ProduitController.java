/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
    @FXML
    private Label erreurnom;
    @FXML
    private Label erreurdesc;
    @FXML
    private Label erreurquant;
    @FXML
    private Label erreurdate;
    @FXML
    private Label erreurprix;
    @FXML
    private Label erreurimg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.FOLDER_OPEN_ALT);
            icon.getStyleClass().add("icon");
            icon.setStyle("-fx-fill: white;");
            imgfileprod.setGraphic(icon);
        cs=new CategoryService();
        ObservableList<Category> l= cs.afficher();
        List<String> nc = new ArrayList<>();
            for (Category category : l) {
                String s=category.getId()+":"+category.getNomCategory() ;
                 nc.add(s);
            }
            System.out.println(nc);
            
      catprod.setItems(FXCollections.observableList(nc));
      catprod.setValue(nc.get(0));
      dateprod.setValue(LocalDate.now());
      prixprod.setText("0");
      quantprod.setText("0");
      erreurdate.setTextFill(Color.RED);
      erreurdesc.setTextFill(Color.RED);
      erreurnom.setTextFill(Color.RED);
      erreurprix.setTextFill(Color.RED);
      erreurquant.setTextFill(Color.RED);
      erreurimg.setTextFill(Color.RED);
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
        public boolean estAlpha(String chaine) {
            return chaine.matches("[a-zA-Z]+");
        }
        public boolean testdate(LocalDate d){
            return d.isAfter(LocalDate.now()) ;
        }
        public boolean testpos(float d){
            if(d>1&&d!=0){
                return true;
            }else return false;
            
        }
    @FXML
    private void addprod(ActionEvent event) {
        erreurdate.setText("");
        erreurdesc.setText("");
        erreurnom.setText("");
        erreurprix.setText("");
        erreurquant.setText("");
        //System.out.println(cat1);
        if(estAlpha(nomprod.getText()) && estAlpha(descprod.getText()) && descprod.getText().length()>=10 && testdate(dateprod.getValue()) && testpos(Float.parseFloat(prixprod.getText())) && testpos(Float.parseFloat(quantprod.getText())) && Integer.parseInt(quantprod.getText())!=0 && !imgprod.getText().isEmpty()){
           String chaine = catprod.getValue();
        int index = chaine.indexOf(":");
        String sousChaine = chaine.substring(0, index);
        System.out.println(sousChaine);
        ps=new ProduitService();
        Category cat1=cs.getCatParId(Integer.parseInt(sousChaine));
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
        else{
            if(imgprod.getText().isEmpty()){
                erreurimg.setText("veuillez choisir une image");
            }
            if(!estAlpha(nomprod.getText())&&nomprod.getText().isEmpty()){
                erreurnom.setText("seulement des alphabets");
            }
            if(!estAlpha(descprod.getText())&&descprod.getText().length()<10&&descprod.getText().isEmpty()){
                erreurdesc.setText("seulement des alphabets et un nombre de caractere sup a 10");
            }
            if(!estAlpha(descprod.getText())&&descprod.getText().isEmpty()){
                erreurdesc.setText("seulement des alphabets");
            }
            if(descprod.getText().length()<10&&descprod.getText().isEmpty()){
                erreurdesc.setText("il faut un nombre de caractere sup a 10");
            }
            if(!testdate(dateprod.getValue())){
                erreurdate.setText("une date superieur a aujourd'hui");
            }
            String hh=prixprod.getText();
            hh+="0";
            if(!testpos(Float.parseFloat(hh))||prixprod.getText().isEmpty()){
                erreurprix.setText("il faut un prix positif");
            }
            String xx=quantprod.getText();
            xx+="0";
            if(!testpos(Float.parseFloat(xx))&&Integer.parseInt(xx)==0||quantprod.getText().isEmpty()){
               erreurquant.setText("il faut une quantite positif et quantite sup a 0");
            }
            
            if(!testpos(Float.parseFloat(xx))||quantprod.getText().isEmpty()){
                erreurquant.setText("il faut une quantite positif"); 
            }
            if(Integer.parseInt(xx)==0||quantprod.getText().isEmpty()){
                erreurquant.setText("quantite sup a 0");
            }
            
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/testprod.fxml"));
            Parent root;
        try {
            root = loader.load();
            descprod.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
