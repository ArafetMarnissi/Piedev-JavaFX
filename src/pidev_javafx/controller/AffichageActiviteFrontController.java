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
import javafx.event.ActionEvent;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;
   import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

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
    public AnchorPane anchorPane_affich_details_acts_front;
    @FXML
    private VBox v2;
    @FXML
    private ImageView image_act_details;
    @FXML
    private Label espace1;
    @FXML
    private Label nom_act_details;
    @FXML
    private Label espace;
    @FXML
    private Label desc_act_details;
    @FXML
    private Label espace2;
    @FXML
    private ImageView image_qrcode;
    @FXML
    private Button btn_retour_details;
    @FXML
    private Label espace4;
    @FXML
    private ImageView image_coach_detail;
    @FXML
    private Label espace_c1;
    @FXML
    private Label nom_coach;
    @FXML
    private Label age_coach;
    @FXML
    private Label espace_c2;
    @FXML
    private Label espace_c3;
    @FXML
    private Label date_act_vbox;
    @FXML
    private Label espace_c4;
    @FXML
    private Label deb_act_detail;
    @FXML
    private Label espace_c5;
    @FXML
    private Label fin_act_detail;
    @FXML
    private Label espace_c6;
    @FXML
    private ImageView image_qrcode_coach;
    @FXML
    private AnchorPane anchorPane_affich_acts_front;
    @FXML
    private Button btn_affich_date;
    @FXML
    private AnchorPane anchorpane_affich_date;
    @FXML
    private FlowPane flowpane_affich_date;
    @FXML
    private Label espace_label;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_affich_date.setStyle("-fx-text-fill:#ffffff; -fx-background-color: #1372f4; -fx-background-radius: 25px;"
                    + " -fx-min-width: 130px;\n" +
"    -fx-max-width: 130px;\n" +
"    -fx-min-height: 40px;\n" +
"    -fx-max-height: 40px;");
        espace_label.setText("\n");
        anchorPane_affich_acts_front.toFront();
        
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
                    notif2("GoldenGym","Dés maintenant, Vous participez à cette activité ");
                    
                            //************,dkonbonidbnibdfibgdgndbgndjkbgdgdhgdgbvdfvguyebubgtebgfjdfbgdeSystem.out.println("reclamation ajoutée");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/AffichageActiviteFront.fxml"));
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
                     notif2("GoldenGym","Dés maintenant, Vous ne participez plus à cette activité ");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/AffichageActiviteFront.fxml"));
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
                                        notif2("GoldenGym","Dés maintenant, Vous ne participez plus à cette activité ");
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/AffichageActiviteFront.fxml"));
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
               
                   // try{
                        
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
                        
                        
                        anchorPane_affich_details_acts_front.toFront();
                        
                try {
                    recupDataD(a,newFileName);
                    /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/DetailsActiviteFront.fxml"));
                    Parent root = loader.load();
                    DetailsActiviteFrontController mcc = loader.getController();
                    mcc.recupDataD(a,newFileName);
                    flowpane_front.getScene().setRoot(root);*/
                    
                    /*}catch(IOException ex){
                    Logger.getLogger(AffichageActiviteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
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

    @FXML
    private void Retour_detailsOnClick(ActionEvent event){
       anchorPane_affich_acts_front.toFront();
    }
    
    public void recupDataD(Activite t,String s) throws FileNotFoundException{
             LocalDate ld=t.getDateActivite().toLocalDate();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
                        String formattedDate = ld.format(formatter);
       nom_act_details.setText(t.getNomActivite());
       desc_act_details.setText(t.getDescriptionActivite());
       nom_coach.setText(t.getCoach().getNom_coach());
       age_coach.setText("Coach");
       date_act_vbox.setText("Date: "+formattedDate);
       deb_act_detail.setText("Commence à: "+String.valueOf(t.getTimeActivite()));
       fin_act_detail.setText("Se termine à: "+String.valueOf(t.getEnd()));
       
      espace.setText("\n");
       espace1.setText("\n");
       espace2.setText("\n");
       espace4.setText("\n");
       espace_c1.setText("\n");
       espace_c2.setText("\n");
       espace_c3.setText("\n");
       espace_c4.setText("\n");
       espace_c5.setText("\n");
       espace_c6.setText("\n");
       
       /*String chara = Integer.toString(t.getNbrePlace());
       nbreplace_activite_modif.setText(chara);*/
            System.out.println(t.getCoach());
       image_act_details.setImage(new Image(new FileInputStream(Statics.uploadDirectory+t.getImage())));
       //image_qrcode.setImage(new Image(new FileInputStream(Statics.uploadDirectory1+s)));
       image_qrcode_coach.setImage(new Image(new FileInputStream(Statics.uploadDirectory1+s)));
       image_coach_detail.setImage(new Image(new FileInputStream(Statics.uploadDirectory+t.getCoach().getImage())));
       
            //System.out.println(t);
        image_act_details.setFitHeight(1550);
        image_act_details.setFitWidth(850);
        image_act_details.setStyle("-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
        nom_act_details.setFont(Font.font("Verdana",FontWeight.BOLD, 24));
        nom_act_details.setAlignment(Pos.CENTER);
        desc_act_details.setFont(Font.font("Verdana",FontWeight.NORMAL, 20));
        desc_act_details.setAlignment(Pos.CENTER);
        nom_coach.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
        nom_coach.setAlignment(Pos.CENTER);
        age_coach.setFont(Font.font("Verdana",FontWeight.NORMAL, 14));
        age_coach.setAlignment(Pos.CENTER);
        date_act_vbox.setFont(Font.font("Verdana",FontWeight.NORMAL, 16));
        date_act_vbox.setAlignment(Pos.CENTER);
        deb_act_detail.setFont(Font.font("Verdana",FontWeight.NORMAL, 16));
        deb_act_detail.setAlignment(Pos.CENTER);
        fin_act_detail.setFont(Font.font("Verdana",FontWeight.NORMAL, 16));
        fin_act_detail.setAlignment(Pos.CENTER);
        
    } 
    
  public void notif2(String title, String text){
   Image img = new Image("/pidev_javafx/assets/logo1.png");
   
   ImageView img1= new ImageView(img);
                img1.setFitWidth(200);
                img1.setFitHeight(200);
                img1.setPreserveRatio(true);
                img1.setStyle("-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Verdana",FontWeight.NORMAL, 14));

        // create a label with the message and the Verdana font
        Label messageLabel = new Label(text);
        messageLabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
                StackPane customRegion = new StackPane(img1,titleLabel,messageLabel);
        customRegion.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        customRegion.setAlignment(Pos.CENTER);
            //customRegion.getChildrenUnmodifiable().add(img1);
    Notifications notificationBuilder = Notifications.create()
    .title(title)
    .text(text)
            .graphic(img1)
            .hideAfter(Duration.seconds(5))
            //.darkStyle()
            .position(Pos.BOTTOM_RIGHT);
   
    notificationBuilder.show();
         
}

    @FXML
    private void afficher_dateOnClick(ActionEvent event) {
                int i=0;
       ObservableList<Activite>listAc=FXCollections.observableArrayList();
        ActiviteService cs=new ActiviteService();
        ParticipationService ps=new ParticipationService();
        CoachService coachS=new CoachService();
        listAc=cs.afficher_avecDate();
        flowpane_affich_date.setOrientation(Orientation.HORIZONTAL);
            flowpane_affich_date.setAlignment(Pos.CENTER);
            flowpane_affich_date.setHgap(10);
            flowpane_affich_date.setVgap(10);
                       
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
                    notif2("GoldenGym","Dés maintenant, Vous participez à cette activité ");
                    
                            //************,dkonbonidbnibdfibgdgndbgndjkbgdgdhgdgbvdfvguyebubgtebgfjdfbgdeSystem.out.println("reclamation ajoutée");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/AffichageActiviteFront.fxml"));
                    Parent root = loader.load();
                    flowpane_affich_date.getScene().setRoot(root);
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
                     notif2("GoldenGym","Dés maintenant, Vous ne participez plus à cette activité ");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/AffichageActiviteFront.fxml"));
                    Parent root = loader.load();
                    flowpane_affich_date.getScene().setRoot(root);
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
                                        notif2("GoldenGym","Dés maintenant, Vous ne participez plus à cette activité ");
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/AffichageActiviteFront.fxml"));
                                        Parent root = loader.load();
                                        flowpane_affich_date.getScene().setRoot(root);
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
               
                   // try{
                        
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
                        
                        
                        anchorPane_affich_details_acts_front.toFront();
                        
                try {
                    recupDataD(a,newFileName);
                    /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/DetailsActiviteFront.fxml"));
                    Parent root = loader.load();
                    DetailsActiviteFrontController mcc = loader.getController();
                    mcc.recupDataD(a,newFileName);
                    flowpane_front.getScene().setRoot(root);*/
                    
                    /*}catch(IOException ex){
                    Logger.getLogger(AffichageActiviteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }

            });

            flowpane_affich_date.getChildren().add(v1);
                           /* if ((i + 1) % 4 == 0) { // ajoute une nouvelle ligne après chaque 4ème élément
        flowpane_front.getChildren().add(new Region());
                }
        i=i+1;*/
            flowpane_affich_date.setMargin(v1,new Insets(5,5,5,5));

        }
        anchorpane_affich_date.toFront();
    }


}
