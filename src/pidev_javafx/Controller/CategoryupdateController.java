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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev_javafx.entitie.Category;
import pidev_javafx.service.CategoryService;
import pidev_javafx.tools.Statics;


/**
 * FXML Controller class
 *
 * @author damma
 */
public class CategoryupdateController implements Initializable {

    @FXML
    private Button filebutton;
    @FXML
    private TextField nomupdate;
    @FXML
    private TextField imgupdate;
    @FXML
    private Button updatecat;
    private File selectedFile = null;
    Category c1;
    CategoryService sc;
    @FXML
    private Label nomerrur;
    @FXML
    private FontAwesomeIconView closebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                sc=new CategoryService();
                nomupdate.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
                imgupdate.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
                nomerrur.setTextFill(Color.RED);
                

    }    

    @FXML
    private void choosefile(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser(); //outil eli nekhdhou bih el fichier
        final Stage stage = null;// el fenetre eli bech tethal 

        File file = fileChooser.showOpenDialog(stage); //halina el fenetre w recuperina el fichier
        if (file != null) { //ntestiow est ce que fichier null wale
            //Image image1 = new Image(file.toURI().toString());
            //addImage.setImage(image1);//badalna el image
            imgupdate.setText(file.getName()); //badalna el input
            selectedFile = file;
    }}
    public boolean estAlpha(String chaine) {
            return chaine.matches("[a-zA-Z]+");
        }

    @FXML
    private void catupdate(ActionEvent event) throws IOException {
        //System.out.println(c1.getNomCategory());
        if(!imgupdate.getText().equals(c1.getImageCategory()) && estAlpha(nomupdate.getText()) ){
            int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
            String newFileName = random_int+"-"+selectedFile.getName();
            Category cat=new Category(c1.getId(),nomupdate.getText(),newFileName);
            System.out.println(cat);
            sc.modifier(cat);
            Path sourceFile = Paths.get(selectedFile.toPath().toString());
            Path targetFile = Paths.get(Statics.uploadDirectory+newFileName);

            Files.copy(sourceFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/CategoryDisplay.fxml"));
        Parent root = loader.load();
        CategoryDisplayController cdc=loader.getController();
        cdc.vueEstOuverte=false;
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.setTitle("display");
        stage1.show();
        Stage stage = (Stage) updatecat.getScene().getWindow();
        stage.close();
        }else if(!estAlpha(nomupdate.getText())){
            nomerrur.setText("seulement des alphabets");
        }else
        {
            Category cat=new Category(c1.getId(),nomupdate.getText(),imgupdate.getText());
            sc.modifier(cat);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/CategoryDisplay.fxml"));
        Parent root = loader.load();
        CategoryDisplayController cdc=loader.getController();
        cdc.vueEstOuverte=false;
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.setTitle("display");
        stage1.show();
        Stage stage = (Stage) updatecat.getScene().getWindow();
        stage.close();
        }
        
        
        
        
        
        
        
    }
    
    public void setData(Category c){
        nomupdate.setText(c.getNomCategory());
        imgupdate.setText(c.getImageCategory());
        c1=c;
    }

    @FXML
    private void verscataffonclick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/CategoryDisplay.fxml"));
            DisplayProductController dpc= loader.getController();
            //dpc.afficherprodfxml();
            Stage stage = (Stage) closebtn.getScene().getWindow();
            stage.close();
            Parent root = loader.load();
            Stage stage1 = new Stage();
            stage.setScene(new Scene(root,1000,700));
            stage.setTitle("Categorie");
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(ModifproductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
