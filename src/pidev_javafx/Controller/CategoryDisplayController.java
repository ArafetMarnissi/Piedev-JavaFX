    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.Controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pidev_javafx.entitie.Category;
import pidev_javafx.service.CategoryService;

/**
 * FXML Controller class
 *
 * @author damma
 */
public class CategoryDisplayController implements Initializable {

    @FXML
    private TableView<Category> cat;
    @FXML
    private FontAwesomeIconView refrechicon;

    public TableView<Category> getCat() {
        return cat;
    }
    @FXML
    private TableColumn<Category, String> nom;
    @FXML
    private TableColumn<Category, String> img;
    @FXML
    private Button modifcatbutton;
    @FXML
    private Button suppcatbutton;
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
        //id.setCellValueFactory(new PropertyValueFactory<Category,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Category, String>("NomCategory"));
        img.setCellValueFactory(new PropertyValueFactory<Category, String>("ImageCategory"));
//        try {
//            CategoryService sc=new CategoryService();
//            sc.affichercat().forEach(r->{listcat.add(r);});
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        cat.setItems(listcat);
    ObservableList<Category>listcat=FXCollections.observableArrayList();
    CategoryService sc=new CategoryService();
        listcat=sc.afficher();
        cat.setItems(listcat);
        
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

    @FXML
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

    @FXML
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

    @FXML
    private void refrechtable(MouseEvent event) {
        cat.getItems().clear();
        id.setCellValueFactory(new PropertyValueFactory<Category,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Category, String>("NomCategory"));
        img.setCellValueFactory(new PropertyValueFactory<Category, String>("ImageCategory"));
        ObservableList<Category>list=FXCollections.observableArrayList();
        CategoryService sc=new CategoryService();
        list=sc.afficher();
        cat.setItems(list);
    }
    
    
}
