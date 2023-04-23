/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
public class AllProductController implements Initializable {
    
    ObservableList<Produit>listprod=FXCollections.observableArrayList();
    ObservableList<Produit>listarchiveprod=FXCollections.observableArrayList();
    @FXML
    private AnchorPane anchorajoutprod;
    @FXML
    private Button addprod;
    @FXML
    private Button btnaff;
    @FXML
    private TextField nomprod;
    @FXML
    private TextField quantprod;
    @FXML
    private TextField prixprod;
    @FXML
    private TextArea descprod;
    @FXML
    private DatePicker dateprod;
    @FXML
    private ComboBox<String> catprod;
    @FXML
    private TextField imgprod;
    @FXML
    private Button imgfileprod;
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
    @FXML
    private AnchorPane anchoraffichageprod;
    @FXML
    private FlowPane panaff;
    @FXML
    private Button versacceuilbtn;
    @FXML
    private Button versajoutbtn;
    @FXML
    private AnchorPane anchorupdateprod;
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
    @FXML
    private FontAwesomeIconView closeicon;
    @FXML
    private Label errprix;
    @FXML
    private Label errdesc;
    @FXML
    private Label errdate;
     private File selectedFile = null;
             ProduitService ps=new ProduitService();
    private Produit p1;
    private CategoryService cs;
    @FXML
    private AnchorPane ancherarchiveprod;
    @FXML
    private FlowPane flowarchiveprod;
    @FXML
    private Button archivebtn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dispaly();
        cs=new CategoryService();
        ObservableList<Category> l= cs.afficher();
        List<String> nc = new ArrayList<>();
            for (Category category : l) {
                String s=category.getId()+":"+category.getNomCategory() ;
                 nc.add(s);
            }
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
    
    private void showProductDetails(Produit product) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Product Details");
    alert.setHeaderText(product.getNom());
    alert.setContentText("Description: " + product.getDescription() + "\nPrix: " + product.getPrix_produit()+"DT"+"\nQuantite:"+product.getQuantite_produit()+"\nNote:"+product.getNote());
    alert.show();
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
        dispaly();
        anchoraffichageprod.toFront();
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
    private void affonclick(ActionEvent event) {
        dispaly();
        anchoraffichageprod.toFront();
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
    private void versacceuilonclick(ActionEvent event) {
        dispaly();
        anchoraffichageprod.toFront();
    }

    @FXML
    private void versajouonclick(ActionEvent event) {
        anchorajoutprod.toFront();
    }

    @FXML
    private void addimgonclickmodif(ActionEvent event) {
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
    private void btnmodifonclick(ActionEvent event) {
    }

    @FXML
    private void closemodif(MouseEvent event) {
        dispaly();
        anchoraffichageprod.toFront();
    }
    
    private void dispaly(){
        panaff.getChildren().clear();
        listprod=ps.afficher();
        for(Produit p:listprod){
            VBox card=new VBox();
            card.setPrefSize(200, 250);
            if(p.getQuantite_produit()==0){
                card.setStyle("-fx-background-color: #ffffff; -fx-border-color: RED; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
            }
            else{
                card.setStyle("-fx-background-color: #ffffff; -fx-border-color: GREEN; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");

            }
            
            ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory1+p.getImage_produit())));
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                imageView.setPreserveRatio(true);
                imageView.setStyle("-fx-border-radius: 40px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DisplayProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Label l1=new Label("\n");
            card.getChildren().add(l1);
            Label namelabel=new Label("Nom produit:"+p.getNom());     
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            card.getChildren().add(namelabel);
            Label prixLabel=new Label("Prix"+Float.toString(p.getPrix_produit())+"DT");
            prixLabel.setWrapText(true);
            prixLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(prixLabel);
            Label catLabel=new Label("Categorie"+p.getCategory().getNomCategory());
            catLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(catLabel);
            Button bb=new Button("EDIT");
            bb.setStyle("-fx-background-color: #1372f4; -fx-background-radius: 25px; -fx-text-fill: white;");
            bb.setOnMouseClicked(e->{
                getdata(p);
                anchorupdateprod.toFront();
            });
            card.getChildren().add(bb);
            card.setOnMouseClicked(e->{
                showProductDetails(p);
        });
            panaff.getChildren().add(card);
            panaff.setMargin(card, new Insets(5, 5, 5, 5));
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
    private void dispalyarchiev(){
        listarchiveprod=ps.afficherarchive();
        for(Produit p:listarchiveprod){
            VBox card1=new VBox();
            card1.setPrefSize(200, 250);
            
            ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory1+p.getImage_produit())));
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                imageView.setPreserveRatio(true);
                imageView.setStyle("-fx-border-radius: 40px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
                card1.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DisplayProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Label l1l=new Label("\n");
            card1.getChildren().add(l1l);
            Label namelabell=new Label("Nom produit:"+p.getNom());     
            namelabell.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            namelabell.setAlignment(Pos.CENTER);
            card1.getChildren().add(namelabell);
            Label prixLabell=new Label("Prix"+Float.toString(p.getPrix_produit())+"DT");
            prixLabell.setWrapText(true);
            prixLabell.setAlignment(Pos.CENTER);
            card1.getChildren().add(prixLabell);
            Label catLabel=new Label("Categorie"+p.getCategory().getNomCategory());
            catLabel.setAlignment(Pos.CENTER);
            card1.getChildren().add(catLabel);
            card1.setOnMouseClicked(e->{
                showProductDetails(p);
            });
            flowarchiveprod.getChildren().add(card1);
            flowarchiveprod.setMargin(card1, new Insets(5, 5, 5, 5));
        }
    }

    @FXML
    private void archiveonclick(ActionEvent event) {
        dispalyarchiev();
        ancherarchiveprod.toFront();
    }
    
}
