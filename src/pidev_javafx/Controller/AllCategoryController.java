/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
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
  import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import javafx.scene.Node;
/**
 * FXML Controller class
 *
 * @author damma
 */
public class AllCategoryController implements Initializable {

    @FXML
    private AnchorPane anchorajoutcat;
    @FXML
    private TextField nomfiled;
    @FXML
    private TextField imagefiled;
    @FXML
    private Button imagebutton;
    @FXML
    private Button submitbutton;
    @FXML
    private Label erreurnomcat;
    @FXML
    private Button btncataff;
    @FXML
    private AnchorPane anchorupdatecat;
    @FXML
    private Button filebutton;
    @FXML
    private TextField nomupdate;
    @FXML
    private TextField imgupdate;
    @FXML
    private Button updatecat;
    @FXML
    private Label nomerrur;
    @FXML
    private FontAwesomeIconView closebtn;
    @FXML
    private AnchorPane anchoraffichagecat;
    @FXML
    private Button versajouter;
    @FXML
    private FlowPane catflow;
    @FXML
    private Button versacceuilbtn;
    private File selectedFile = null;
    
    CategoryService sc=new CategoryService();
    ProduitService ps=new ProduitService();
            ObservableList<Produit>listprod=FXCollections.observableArrayList();
    private Category c1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        anchoraffichagecat.toFront();
        display();
    }    
    
     public void notif2(String title, String text){
   Image img = new Image("/pidev_javafx.GUI/logo1.png");
    Notifications notificationBuilder = Notifications.create()
    .title(title)
    .text(text)
            .graphic(new ImageView(img))
            .hideAfter(Duration.seconds(15))
            .position(Pos.BOTTOM_RIGHT);
    notificationBuilder.show();
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
            System.out.println(cat);
       sc.ajouter(new Category(nomfiled.getText(),newFileName));
        
        Path sourceFile = Paths.get(selectedFile.toPath().toString());
        Path targetFile = Paths.get(Statics.uploadDirectory+newFileName);
//
        Files.copy(sourceFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
        notif2("GOLDEN GYM","Categorie ajouter");
//        
        display();
        anchoraffichagecat.toFront();
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
    private void cataffonclick(ActionEvent event) {
        display();
        anchoraffichagecat.toFront();
    }

    @FXML
    private void choosefile(ActionEvent event) {
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

    @FXML
    private void catupdate(ActionEvent event) throws IOException {
        if(!imgupdate.getText().equals(c1.getImageCategory()) && estAlpha(nomupdate.getText()) ){
            int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
            String newFileName = random_int+"-"+selectedFile.getName();
            Category cat=new Category(c1.getId(),nomupdate.getText(),newFileName);
            System.out.println(cat);
            sc.modifier(cat);
            Path sourceFile = Paths.get(selectedFile.toPath().toString());
            Path targetFile = Paths.get(Statics.uploadDirectory+newFileName);

            Files.copy(sourceFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
            display();
            anchoraffichagecat.toFront();
        }else if(!estAlpha(nomupdate.getText())){
            nomerrur.setText("seulement des alphabets");
        }else
        {
            Category cat=new Category(c1.getId(),nomupdate.getText(),imgupdate.getText());
            sc.modifier(cat);
            display();
            anchoraffichagecat.toFront();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/CategoryDisplay.fxml"));
//        Parent root = loader.load();
//        CategoryDisplayController cdc=loader.getController();
//        cdc.vueEstOuverte=false;
//        Stage stage1 = new Stage();
//        stage1.setScene(new Scene(root));
//        stage1.setTitle("display");
//        stage1.show();
//        Stage stage = (Stage) updatecat.getScene().getWindow();
//        stage.close();
        }
    }
    
    public void setData(Category c){
        nomupdate.setText(c.getNomCategory());
        imgupdate.setText(c.getImageCategory());
        c1=c;
    }

    @FXML
    private void verscataffonclick(MouseEvent event) {
        display();
        anchoraffichagecat.toFront();
    }

    @FXML
    private void afficherajouter(ActionEvent event) {
        anchorajoutcat.toFront();
    }

    @FXML
    private void versacceuilonclick(ActionEvent event) {
    }
    
    private void display(){
        catflow.getChildren().clear();
        FontAwesomeIconView icon2 = new FontAwesomeIconView(FontAwesomeIcon.PLUS);
            icon2.getStyleClass().add("icon1");
            icon2.setStyle("-fx-fill: white;");
            versajouter.setGraphic(icon2);
        CategoryService cs=new CategoryService();
        ObservableList<Category>listcat=FXCollections.observableArrayList();
        listcat=cs.afficher();     
        for(Category cat:listcat){
                VBox card=new VBox();
                card.setPrefSize(150, 150);
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
            Button btn=new Button("Edit");
            FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);
            icon.getStyleClass().add("icon");
            icon.setStyle("-fx-fill: white;");
            btn.setGraphic(icon);
            btn.setAlignment(Pos.TOP_RIGHT);
            btn.setStyle("-fx-background-color: #1372f4; -fx-background-radius: 25px; -fx-text-fill: white;");
            btn.setOnAction(e->{
                setData(cat);
                anchorupdatecat.toFront();
                
            });
            card.getChildren().add(btn);
            Button btn1=new Button("Supprimer");
            btn1.setAlignment(Pos.TOP_RIGHT);
            FontAwesomeIconView icon1 = new FontAwesomeIconView(FontAwesomeIcon.ERASER);
            icon1.getStyleClass().add("icon1");
            icon1.setStyle("-fx-fill: white;");
            btn1.setGraphic(icon1);
             btn1.setStyle("-fx-background-color: #1372f4; -fx-background-radius: 25px; -fx-text-fill: white;");
            btn1.setOnAction(e->{
               listprod=ps.findprodbycat(cat.getId());
               if(listprod.isEmpty()){
                 sc.supprimer(cat);
                display();  
               }else{
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("");
                alert.setContentText("Voulez-vous vraiment supprimer cette categorie ?");
                Font font = Font.font("Verdana",FontWeight.BOLD, 16);
                ButtonType buttonTypeYes = new ButtonType("Oui");
                ButtonType buttonTypeNo = new ButtonType("Non", ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
                alert.getDialogPane().setStyle("-fx-background-color: #FFFFFF;");
                Button buttonYes = (Button) alert.getDialogPane().lookupButton(buttonTypeYes);
                Button buttonNo = (Button) alert.getDialogPane().lookupButton(buttonTypeNo);
                buttonYes.setStyle("-fx-text-fill:#ffffff; -fx-background-color: #1372f4; -fx-background-radius: 25px;"
                    + " -fx-min-width: 130px;\n" +
                    "    -fx-max-width: 130px;\n" +
                    "    -fx-min-height: 40px;\n" +
                    "    -fx-max-height: 40px;");
                buttonNo.setStyle("-fx-text-fill:#ffffff; -fx-background-color: #f00020; -fx-background-radius: 25px;"
                        + " -fx-min-width: 130px;\n" +
                    "    -fx-max-width: 130px;\n" +
                    "    -fx-min-height: 40px;\n" +
                    "    -fx-max-height: 40px;");
                 alert.setContentText(null);
                Label headerLabel1 = new Label("Voulez-vous vraiment annuler votre participation ?");
                headerLabel1.setFont(font);
                alert.getDialogPane().setContent(headerLabel1);

            Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes){
                    CategoryService sc=new CategoryService();
                    sc.supprimer(cat);
                    display();
                    alert.close();
                }else if(result.get()==buttonTypeNo){
                    alert.close();
                }
               }
//                CategoryService sc=new CategoryService();
//                sc.supprimer(cat);
//                display();
            });
            card.getChildren().add(btn1);
            catflow.getChildren().add(card);
            catflow.setMargin(card, new Insets(5, 5, 5, 5));
                    
        }
    }
    
}
