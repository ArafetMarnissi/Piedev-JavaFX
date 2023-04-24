/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.test;

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
        Commande c =CS.getCommandeParId(43);
        
        /*
        try {
            MailFacture.sendMail("marnissiarafet@gmail.com", c);
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
       
*/
        
    }
    
}
