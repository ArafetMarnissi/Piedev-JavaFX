/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gstreclamation;

import entity.Reclamation;
import entity.TypeReclamation;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import service.ServiceReclamation;
import service.ServiceReponse;
import utils.FilterBadWord;
import utils.MyConnection;

/**
 *
 * @author Skymil
 */
public class GstReclamation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       ServiceReclamation sr=new ServiceReclamation();
        ServiceReponse srep=new ServiceReponse();
       Reclamation r=new Reclamation("hello world",TypeReclamation.Activite,new Date());
       //sr.ajouter(r);
       //sr.modifier(38, r);
       //sr.supprimer(38);
       //System.out.println(sr.afficher());
        System.out.println(FilterBadWord.filter("hello shit world"));
    }
    
}
