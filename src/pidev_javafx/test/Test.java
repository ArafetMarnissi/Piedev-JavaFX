/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.test;

import pidev_javafx.entitie.Abonnement;
import pidev_javafx.entitie.Commande;
import pidev_javafx.entitie.User;
import pidev_javafx.service.AbonnementService;
import pidev_javafx.service.CommandeService;
import pidev_javafx.service.LigneCommandeService;
import pidev_javafx.service.PdfService;
import pidev_javafx.service.UserService;

/**
 *
 * @author marni
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PdfService pdfS =new PdfService();
        Commande c =new Commande();
        CommandeService CS =new CommandeService();
        c=CS.getCommandeParId(43);
        pdfS.genererPdf(c);

        
    }
    
}
