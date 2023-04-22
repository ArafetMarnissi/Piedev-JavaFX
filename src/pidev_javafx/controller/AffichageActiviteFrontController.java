/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pidev_javafx.entitie.Activite;
import pidev_javafx.entitie.Coach;
import pidev_javafx.entitie.Participation;
import pidev_javafx.service.ActiviteService;
import pidev_javafx.service.CoachService;
import pidev_javafx.service.ParticipationService;
import pidev_javafx.tools.Statics;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import java.util.Optional;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;

/**
 * FXML Controller class
 *
 * @author mmarr
 */
public class AffichageActiviteFrontController implements Initializable {

    @FXML
    private FlowPane flowpane_front;
ActiviteService cs;
ParticipationService ps;
CoachService coachS;
    @FXML
    private TextField recherche_textfield;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int i=0;
       ObservableList<Activite>listAc=FXCollections.observableArrayList();
        ActiviteService cs=new ActiviteService();
        ParticipationService ps=new ParticipationService();
        CoachService coachS=new CoachService();
        listAc=cs.afficher();
        flowpane_front.setOrientation(Orientation.HORIZONTAL);
            flowpane_front.setAlignment(Pos.CENTER);
            flowpane_front.setHgap(10);
            flowpane_front.setVgap(10);
                       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            
        for(Activite a:listAc)
        {
            
            VBox v1=new VBox();
            /*v1.setMinHeight(210);
            v1.setMaxHeight(210);
            v1.setMinWidth(210);
            v1.setMaxWidth(210);*/
            v1.setPrefSize(300, 350);
            
            v1.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
            ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory+a.getImage())));
                imageView.setFitWidth(300);
                imageView.setFitHeight(300);
                imageView.setPreserveRatio(true);
                imageView.setStyle("-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
                v1.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
            Label nom_act1=new Label("\n");
            v1.getChildren().add(nom_act1);
            Label nom_act=new Label(a.getNomActivite());
            nom_act.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            nom_act.setAlignment(Pos.CENTER);
            v1.getChildren().add(nom_act);
            LocalDate ld1=a.getDateActivite().toLocalDate();     
            String formattedDate1 = ld1.format(formatter);
            Label nom_act3=new Label("La date: "+formattedDate1);
            nom_act3.setFont(Font.font("Verdana",FontWeight.NORMAL, 16));
            nom_act3.setAlignment(Pos.CENTER);
            v1.getChildren().add(nom_act3);
            Label nom_act4=new Label("Nombre de places: "+a.getNbrePlace());
            nom_act4.setFont(Font.font("Verdana",FontWeight.NORMAL, 16));
            nom_act4.setAlignment(Pos.CENTER);
            v1.getChildren().add(nom_act4);
            Label nom_act2=new Label("\n");
            v1.getChildren().add(nom_act2);
            Participation test=ps.FindPartById(a.getId(), 126);
            if(a.getNbrePlace()>0){
           if(test==null){
            Button btn=new Button("Participer");
            btn.setOnAction(e->{
                try {
                    //a.setNbrePlace(a.getNbrePlace()-1);
                    LocalDate today=LocalDate.now();
                    Date d=java.sql.Date.valueOf(today);
                    Participation p = new Participation(126,d,a);
                    Activite a1=ps.findbyid(p.getActivite().getId());
                    a1.setNbrePlace(a1.getNbrePlace()-1);
                    cs.modifier(a1);
                    ps.ajouter(p);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/AffichageActiviteFront.fxml"));
                    Parent root = loader.load();
                    flowpane_front.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AffichageActiviteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            });
            btn.setStyle("-fx-text-fill:#ffffff; -fx-background-color: #1372f4; -fx-background-radius: 25px;"
                    + " -fx-min-width: 130px;\n" +
"    -fx-max-width: 130px;\n" +
"    -fx-min-height: 40px;\n" +
"    -fx-max-height: 40px;");
            v1.getChildren().add(btn);
           }else{
            Button btn1=new Button("Annuler");
            btn1.setOnAction(e->{
Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("");
                alert.setContentText("Voulez-vous vraiment annuler votre participation ?");
                Font font = Font.font("Verdana",FontWeight.BOLD, 16);

            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonData.CANCEL_CLOSE);
            //ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            alert.getDialogPane().setStyle("-fx-background-color: #FFFFFF;");
            Button buttonYes = (Button) alert.getDialogPane().lookupButton(buttonTypeYes);
            Button buttonNo = (Button) alert.getDialogPane().lookupButton(buttonTypeNo);
            //Button buttonCancel = (Button) alert.getDialogPane().lookupButton(buttonTypeCancel);

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
            //buttonCancel.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
                alert.setContentText(null);
                Label headerLabel1 = new Label("Voulez-vous vraiment annuler votre participation ?");
                headerLabel1.setFont(font);
                alert.getDialogPane().setContent(headerLabel1);

            Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes){
    // code pour le bouton "Oui"
                    
                try {
                    Activite a1=ps.findbyid(a.getId());
                    a1.setNbrePlace(a1.getNbrePlace()+1);
                    cs.modifier(a1);
                    ps.supprimer(test);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/AffichageActiviteFront.fxml"));
                    Parent root = loader.load();
                    flowpane_front.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AffichageActiviteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            });
            btn1.setStyle("-fx-text-fill:#ffffff; -fx-background-color: #f00020; -fx-background-radius: 25px;"
                    + " -fx-min-width: 130px;\n" +
"    -fx-max-width: 130px;\n" +
"    -fx-min-height: 40px;\n" +
"    -fx-max-height: 40px;");
            v1.getChildren().add(btn1);
           }
            }else{
                if(test!=null){
                                Button btn2=new Button("Annuler");
            btn2.setOnAction(e->{
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("");
                alert.setContentText("Voulez-vous vraiment annuler votre participation ?");
                Font font = Font.font("Verdana",FontWeight.BOLD, 16);

            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonData.CANCEL_CLOSE);
            //ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            alert.getDialogPane().setStyle("-fx-background-color: #FFFFFF;");
            Button buttonYes = (Button) alert.getDialogPane().lookupButton(buttonTypeYes);
            Button buttonNo = (Button) alert.getDialogPane().lookupButton(buttonTypeNo);
            //Button buttonCancel = (Button) alert.getDialogPane().lookupButton(buttonTypeCancel);

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
            //buttonCancel.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
                alert.setContentText(null);
                Label headerLabel1 = new Label("Voulez-vous vraiment annuler votre participation ?");
                headerLabel1.setFont(font);
                alert.getDialogPane().setContent(headerLabel1);

            Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes){
                                    try {
                                        Activite a1=ps.findbyid(a.getId());
                                        a1.setNbrePlace(a1.getNbrePlace()+1);
                                        cs.modifier(a1);
                                        ps.supprimer(test);
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/AffichageActiviteFront.fxml"));
                                        Parent root = loader.load();
                                        flowpane_front.getScene().setRoot(root);
                                    } catch (IOException ex) {
                                        Logger.getLogger(AffichageActiviteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                }
            });
            btn2.setStyle("-fx-text-fill:#ffffff; -fx-background-color: #f00020; -fx-background-radius: 25px;"
                    + " -fx-min-width: 130px;\n" +
"    -fx-max-width: 130px;\n" +
"    -fx-min-height: 40px;\n" +
"    -fx-max-height: 40px;");
            v1.getChildren().add(btn2);
                }
            }
            v1.setOnMouseClicked(e -> {
               
                    try{
                        
                        int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
                        String newFileName = random_int+"-qrCode.png";
                        //String path=Statics.uploadDirectory1+newFileName;
                        
                        Coach c=coachS.getCoachById(a.getCoach().getId());
                        //System.out.println(Paths.get(path));
                        String data=Statics.URL+"Le nom du coach est "+c.getNom_coach()+" agé de "+c.getAge_coach();
                        String text="hello";
                        try{
                            generateQRCode(data,newFileName);
                            System.out.println("done");
                        }catch(WriterException e1){
                            System.out.println(e1.getMessage());
                        }catch(IOException ex){
                            System.out.println(ex.getMessage());
                        }
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/GUI/DetailsActiviteFront.fxml"));
                        Parent root = loader.load();
                        DetailsActiviteFrontController mcc = loader.getController();
                        mcc.recupDataD(a,newFileName);
                        flowpane_front.getScene().setRoot(root);
                        
                    }catch(IOException ex){
                        Logger.getLogger(AffichageActiviteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }

            });

            flowpane_front.getChildren().add(v1);
                           /* if ((i + 1) % 4 == 0) { // ajoute une nouvelle ligne après chaque 4ème élément
        flowpane_front.getChildren().add(new Region());
                }
        i=i+1;*/
            flowpane_front.setMargin(v1,new Insets(5,5,5,5));

        }

    }

    private static void generateQRCode(String text,String nom) throws IOException, WriterException{
        QRCodeWriter writer= new QRCodeWriter();
        BitMatrix bm = writer.encode(text, BarcodeFormat.QR_CODE, 300, 300);
        
        MatrixToImageWriter.writeToPath(bm, "PNG", new File("C:/Users/mmarr/Desktop/test/PIDEV-Golden-Gym/Pidev/public/img/qr-code/"+nom).toPath());
    }
    
}
