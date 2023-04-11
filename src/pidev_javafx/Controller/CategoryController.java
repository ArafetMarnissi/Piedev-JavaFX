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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
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
public class CategoryController implements Initializable {

    @FXML
    private TextField nomfiled;
    @FXML
    private TextField imagefiled;
    @FXML
    private Button imagebutton;
    @FXML
    private Button submitbutton;
    
    private File selectedFile = null;
    
    CategoryService sc;
    private TextField suppfield;
    @FXML
    private Label erreurnomcat;
    @FXML
    private Button btncataff;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sc=new CategoryService();
        erreurnomcat.setTextFill(Color.RED);
        nomfiled.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
        imagefiled.setStyle("-fx-background-color: -fx-text-box-border, -fx-background ;\n" +
"    -fx-background-insets: 0, 0 0 1 0 ;\n" +
"    -fx-background-radius: 0 ;");
        
    }    

    @FXML
    private void addimage(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser(); //outil eli nekhdhou bih el fichier
        final Stage stage = null;// el fenetre eli bech tethal 

        File file = fileChooser.showOpenDialog(stage); //halina el fenetre w recuperina el fichier
        if (file != null) { //ntestiow est ce que fichier null wale
            //Image image1 = new Image(file.toURI().toString());
            //addImage.setImage(image1);//badalna el image
            imagefiled.setText(file.getName()); //badalna el input
            selectedFile = file;
        }
    }
        public boolean estAlpha(String chaine) {
            return chaine.matches("[a-zA-Z]+");
        }
    @FXML
    private void addcat(ActionEvent event) throws IOException {

        
        String fieldnom=nomfiled.getText();
        if(!estAlpha(fieldnom)){
            erreurnomcat.setText("verifier seulement des alphabets");
        }else{
            erreurnomcat.setText("");
          int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
          String newFileName = random_int+"-"+selectedFile.getName();
            Category cat=new Category(nomfiled.getText(),newFileName);
       sc.ajouter(cat);
        
        Path sourceFile = Paths.get(selectedFile.toPath().toString());
        Path targetFile = Paths.get(Statics.uploadDirectory+newFileName);
//
        Files.copy(sourceFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
//        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/CategoryDisplay.fxml"));
            Parent root = loader.load();
            nomfiled.getScene().setRoot(root);
        }
//        
    }

    private void suppcat(ActionEvent event) {
        sc.supp( Integer.parseInt(suppfield.getText()));
    }

    @FXML
    private void testnomcat(KeyEvent event) {
        erreurnomcat.setTextFill(Color.RED);
        String input=event.getCharacter();  
        System.out.println(input);
            if(!input.matches("[a-z]")){
                event.consume();
                erreurnomcat.setText("seulement des alphabet");
            }else{
                erreurnomcat.setText("");
            }
    }

    @FXML
    private void test3(InputMethodEvent event) {
        erreurnomcat.setTextFill(Color.RED);
        String input=event.getCommitted();
                System.out.println(input);
            if(!input.matches("[a-z]")){
                event.consume();
                erreurnomcat.setText("seulement des alphabet");
            }else{
                erreurnomcat.setText("");
            }
    }

    @FXML
    private void test1(KeyEvent event) {
        erreurnomcat.setTextFill(Color.RED);
        String input=event.getCharacter();
                System.out.println(input);
            if(!input.matches("[a-z]")){
                event.consume();
                erreurnomcat.setText("seulement des alphabet");
            }else{
                erreurnomcat.setText("");
            }
    }

    @FXML
    private void test2(KeyEvent event) {
        erreurnomcat.setTextFill(Color.RED);
        String input=event.getCharacter();
        System.out.println(input);
            if(!input.matches("[a-z]")){
                event.consume();
                erreurnomcat.setText("seulement des alphabet");
            }else{
                erreurnomcat.setText("");
            }
    }

    @FXML
    private void cataffonclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/CategoryDisplay.fxml"));
            Parent root = loader.load();
            nomfiled.getScene().setRoot(root);
    }
    
}
