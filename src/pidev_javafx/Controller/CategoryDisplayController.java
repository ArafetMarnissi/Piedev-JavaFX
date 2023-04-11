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
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import pidev_javafx.entitie.Category;
import pidev_javafx.service.CategoryService;
import pidev_javafx.tools.Statics;

/**
 * FXML Controller class
 *
 * @author damma
 */
public class CategoryDisplayController implements Initializable {

    private TableView<Category> cat;
    @FXML
    private FlowPane catflow;
    @FXML
    private Button versacceuilbtn;

    public TableView<Category> getCat() {
        return cat;
    }
    private TableColumn<Category, String> nom;
    private TableColumn<Category, String> img;
    private Button modifcatbutton;
    private TableColumn<Category,Integer> id; 
    boolean vueEstOuverte=false;
    @FXML
    private Button versajouter;
    

    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            btn.setAlignment(Pos.TOP_RIGHT);
            btn.setOnAction(e->{
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Categoryupdate.fxml"));
                    Parent root = loader.load();
                    CategoryupdateController cuc=loader.getController();
                    cuc.setData(cat);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Edit categorie");
                    Stage stage1 = (Stage) card.getScene().getWindow();
                    stage1.close();
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(DisplayProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            card.getChildren().add(btn);
            Button btn1=new Button("Supprimer");
            btn1.setAlignment(Pos.TOP_RIGHT);
            btn1.setOnAction(e->{
                CategoryService sc=new CategoryService();
                sc.supprimer(cat);
                refrechpane();
            });
            card.getChildren().add(btn1);
            catflow.getChildren().add(card);
            catflow.setMargin(card, new Insets(5, 5, 5, 5));
                    
        }
        
    }   
    
    public void dispaly(){
        //cat.getItems().clear();
        //id.setCellValueFactory(new PropertyValueFactory<Category,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Category, String>("NomCategory"));
        img.setCellValueFactory(new PropertyValueFactory<Category, String>("ImageCategory"));
        ObservableList<Category>list=FXCollections.observableArrayList();
        CategoryService sc=new CategoryService();
        list=sc.afficher();
        cat.setItems(list);
    }

    private void updatecat(ActionEvent event) {
           modifcatbutton.setOnAction(e -> {
               if (!vueEstOuverte) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Categoryupdate.fxml"));
        Parent root = loader.load();
        CategoryupdateController cuc=loader.getController();
        cuc.setData(cat.getSelectionModel().getSelectedItem());
        Stage stage1 = (Stage) versajouter.getScene().getWindow();
            stage1.close();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("update");
        stage.show();
        vueEstOuverte=true;
    } catch (IOException ex) {
        ex.printStackTrace();
    }
               }
});

    }

    private void deletecat(ActionEvent event) {
        CategoryService sc=new CategoryService();
        //Category aa=cat.getSelectionModel().getSelectedItem();
      sc.supprimer(cat.getSelectionModel().getSelectedItem());
      //id.setCellValueFactory(new PropertyValueFactory<Category,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Category, String>("NomCategory"));
        img.setCellValueFactory(new PropertyValueFactory<Category, String>("ImageCategory"));
        ObservableList<Category>listcat=FXCollections.observableArrayList();
        listcat=sc.afficher();
        cat.setItems(listcat);
    }

    @FXML
    private void afficherajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Category.fxml"));
            Parent root = loader.load();
            versajouter.getScene().setRoot(root);
               
                
        
    }

    private void refrechtable(MouseEvent event) {
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
            btn.setAlignment(Pos.TOP_RIGHT);
            btn.setOnAction(e->{
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Categoryupdate.fxml"));
                    Parent root = loader.load();
                    CategoryupdateController cuc=loader.getController();
                    cuc.setData(cat);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Edit categorie");
                    Stage stage1 = (Stage) card.getScene().getWindow();
                    stage1.close();
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(DisplayProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            card.getChildren().add(btn);
            Button btn1=new Button("Supprimer");
            btn1.setAlignment(Pos.TOP_RIGHT);
            btn1.setOnAction(e->{
                CategoryService sc=new CategoryService();
                sc.supprimer(cat);
            });
            card.getChildren().add(btn1);
            catflow.getChildren().add(card);
            catflow.setMargin(card, new Insets(5, 5, 5, 5));
    }
    }
    public void refrechpane(){
        catflow.getChildren().clear();
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
            btn.setAlignment(Pos.TOP_RIGHT);
            btn.setOnAction(e->{
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/Categoryupdate.fxml"));
                    Parent root = loader.load();
                    CategoryupdateController cuc=loader.getController();
                    cuc.setData(cat);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Edit categorie");
                    Stage stage1 = (Stage) card.getScene().getWindow();
                    stage1.close();
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(DisplayProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            card.getChildren().add(btn);
            Button btn1=new Button("Supprimer");
            btn1.setAlignment(Pos.TOP_RIGHT);
            btn1.setOnAction(e->{
                CategoryService sc=new CategoryService();
                sc.supprimer(cat);
            });
            card.getChildren().add(btn1);
            catflow.getChildren().add(card);
            catflow.setMargin(card, new Insets(5, 5, 5, 5));
    }
    }

    @FXML
    private void versacceuilonclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx.GUI/acceuil.fxml"));
            Parent root;
        try {
            root = loader.load();
            catflow.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
