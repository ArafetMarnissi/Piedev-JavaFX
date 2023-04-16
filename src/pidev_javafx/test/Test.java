/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import pidev_javafx.entitie.Category;
import pidev_javafx.entitie.Commande;
import pidev_javafx.entitie.User;
import pidev_javafx.entitie.SMS;
import pidev_javafx.service.CategoryService;
import pidev_javafx.service.CommandeService;
import pidev_javafx.service.LigneCommandeService;
import pidev_javafx.service.ProduitService;

/**
 *
 * @author marni
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
//        Category c1=new Category(2);
//        Category c2=new Category("BCCA");
//        //Category cat=new Category("BCCA");
       CategoryService cs=new CategoryService();
//        //cs.ajouter(cat);
//        //System.out.println(cs.afficher());
//        //cs.modifier(c2);
//        //cs.supprimer(c1);
       //System.out.println(cs.afficher());
//        System.out.println(cs.affichercat());
//        ProduitService ps=new ProduitService();
//       System.out.println(ps.findprodbycat(17));
       SMS ss=new SMS();
       //ss.sms("goldengym", "Esprit2023", "+21693763578", "hello ");
       //ss.sms("whey");
//       ObservableList<Category> l= cs.afficher();
//        List<String> nc = new ArrayList<>();
//            for (Category category : l) {
//                 nc.add(category.getNomCategory());
//            }
//            System.out.println(nc);

        
    }
    
}
