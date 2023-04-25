/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev_javafx.entitie.Abonnement;
import pidev_javafx.entitie.Commande;
import pidev_javafx.entitie.User;
import pidev_javafx.service.AbonnementService;
import pidev_javafx.service.CommandeService;
import pidev_javafx.service.LigneCommandeService;
import pidev_javafx.service.UserService;
import pidev_javafx.tools.MailFacture;

/**
 *
 * @author marni
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CommandeService CS =new CommandeService();
        Commande c =CS.getCommandeParId(66);
        
        
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        String dateCommande=c.getDate_commande();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime currentDateTime = LocalDateTime.parse(currentTime, formatter);
        LocalDateTime commandDateTime = LocalDateTime.parse(dateCommande, formatter);
        Duration duration = Duration.between(commandDateTime, currentDateTime);
        long diffInMinutes = duration.toMinutes();

if (Math.abs(diffInMinutes) > 3) {
    System.out.println("La différence entre les deux dates est supérieure à l'erreur.");}
        /*
        try {
            MailFacture.sendMail("marnissiarafet@gmail.com", c);
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
       
*/
       // System.out.println(commandDateTime);
        System.out.println(diffInMinutes);
        
    }
    
}
