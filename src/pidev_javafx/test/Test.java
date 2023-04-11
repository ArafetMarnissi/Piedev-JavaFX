/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.test;

import pidev_javafx.entitie.Commande;
import pidev_javafx.entitie.User;
import pidev_javafx.service.CommandeService;
import pidev_javafx.service.LigneCommandeService;
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
       
        CommandeService CS =new CommandeService();
        LigneCommandeService LS=new LigneCommandeService();
        UserService us = new UserService();
        User u =new User(1, "test", "test", "test", "test", "test", 0, true);
         Commande c =new Commande(u,"c21,ariana,haouaria",150,"par chèque",26845811);
        Commande c1 =new Commande(31,u,"c21,tunis,haouaria",150,"Chèque",11111118);
        //CS.supprimer(c1);
        //CS.ajouter(c);
        //User u =new User(2);
        //System.out.println(u.getId());
        //CS.supprimer(c);
        //CS.modifier(c1);
       // System.out.println(LS.afficher());
        //System.out.println(CS.getCommandeParId(1));
        //System.out.println(CS.afficher());
        System.out.println(LS.afficherLigneCommandesParCommande(c1));
        
    }
    
}
