/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
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

/**
 * FXML Controller class
 *
 * @author mmarr
 */
public class ListeParticipationsUserController implements Initializable {

    @FXML
    private FlowPane flowpane_part;
    ActiviteService as;
    ParticipationService ps;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Participation>listAc=FXCollections.observableArrayList();
        ActiviteService as=new ActiviteService();
        ParticipationService ps=new ParticipationService();
        listAc=ps.findPartsByUser(126);
        flowpane_part.setOrientation(Orientation.HORIZONTAL);
            flowpane_part.setAlignment(Pos.CENTER);
            flowpane_part.setHgap(10);
            flowpane_part.setVgap(10);
            //System.out.println(listAc);
            
         for(Participation p:listAc){
                        int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
                        String newFileName = random_int+"-qrCode.png";
                        //String path=Statics.uploadDirectory1+newFileName;
                        
                        Activite c=ps.findbyid(p.getActivite().getId());
                        //System.out.println(Paths.get(path));
                        LocalDate ld=c.getDateActivite().toLocalDate();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
                        String formattedDate = ld.format(formatter);
                        //System.out.println(formattedDate);
                        String data=Statics.URL+c.getNomActivite()+" aura lieu le "+formattedDate+" à "+c.getTimeActivite()+" avec le coach "+c.getCoach().getNom_coach();
                        //String text="hello";
                        try{
                            generateQRCode(data,newFileName);
                            System.out.println("done");
                        }catch(WriterException e1){
                            System.out.println(e1.getMessage());
                        }catch(IOException ex){
                            System.out.println(ex.getMessage());
                        }
            VBox v1=new VBox();
            /*v1.setMinHeight(210);
            v1.setMaxHeight(210);
            v1.setMinWidth(210);
            v1.setMaxWidth(210);*/
            v1.setPrefSize(300, 350);
            
            v1.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
             System.out.println(p.getActivite().getImage());
             //System.out.println("bonjour");
                         ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory1+newFileName)));
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
            Label nom_act=new Label("Coach: "+p.getActivite().getCoach().getNom_coach());
            nom_act.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            nom_act.setAlignment(Pos.CENTER);
            v1.getChildren().add(nom_act);
            Label nom_act3=new Label("Agé de: "+p.getActivite().getCoach().getAge_coach());
            nom_act3.setFont(Font.font("Verdana",FontWeight.NORMAL, 16));
            nom_act3.setAlignment(Pos.CENTER);
            v1.getChildren().add(nom_act3);
            LocalDate ld1=p.getDateParticipation().toLocalDate();
                        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            String formattedDate1 = ld1.format(formatter);
            Label nom_act4=new Label("Participation faite le: "+formattedDate1);
            nom_act4.setFont(Font.font("Verdana",FontWeight.NORMAL, 16));
            nom_act4.setAlignment(Pos.CENTER);
            v1.getChildren().add(nom_act4);
            Label nom_act2=new Label("\n");
            v1.getChildren().add(nom_act2);
            
            Button btn1=new Button("Annuler");
            btn1.setOnAction(e->{
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("");
                alert.setContentText("Voulez-vous vraiment annuler votre participation ?");
                Font font = Font.font("Verdana",FontWeight.BOLD, 16);

            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
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
                    Activite a1=ps.findbyid(p.getActivite().getId());
                    a1.setNbrePlace(a1.getNbrePlace()+1);
                    as.modifier(a1);
                    ps.supprimer(p);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/ListeParticipationsUser.fxml"));
                    Parent root = loader.load();
                    flowpane_part.getScene().setRoot(root);
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
            
                        flowpane_part.getChildren().add(v1);
            flowpane_part.setMargin(v1,new Insets(5,5,5,5));
         }
    }
    private static void generateQRCode(String text,String nom) throws IOException, WriterException{
        QRCodeWriter writer= new QRCodeWriter();
        BitMatrix bm = writer.encode(text, BarcodeFormat.QR_CODE, 300, 300);
        
        MatrixToImageWriter.writeToPath(bm, "PNG", new File("C:/Users/mmarr/Desktop/test/PIDEV-Golden-Gym/Pidev/public/img/qr-code/"+nom).toPath());
    }    
    
}
