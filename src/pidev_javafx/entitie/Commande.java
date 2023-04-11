/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.entitie;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author marni
 */
public class Commande {
    
    private int id;
    private User user;
    private String date_commande;
    private String adresse_livraison;
    private float prix_commande;
    private String methode_paiement;
    private int telephone;

    public Commande() {
       this.date_commande = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Commande(int id, User user, String date_commande, String adresse_livraison, float prix_commande, String methode_paiement, int telephone) {
        this.id = id;
        this.user = user;
        this.date_commande = date_commande;
        this.adresse_livraison = adresse_livraison;
        this.prix_commande = prix_commande;
        this.methode_paiement = methode_paiement;
        this.telephone = telephone;
    }
       public Commande(int id, User user, String adresse_livraison, float prix_commande, String methode_paiement, int telephone) {
        this.id = id;
        this.user = user;
        this.date_commande = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.adresse_livraison = adresse_livraison;
        this.prix_commande = prix_commande;
        this.methode_paiement = methode_paiement;
        this.telephone = telephone;
    }

    public Commande(User user, String adresse_livraison, float prix_commande, String methode_paiement, int telephone) {
        this.user = user;
        this.date_commande = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.adresse_livraison = adresse_livraison;
        this.prix_commande = prix_commande;
        this.methode_paiement = methode_paiement;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public float getPrix_commande() {
        return prix_commande;
    }

    public void setPrix_commande(float prix_commande) {
        this.prix_commande = prix_commande;
    }

    public String getMethode_paiement() {
        return methode_paiement;
    }

    public void setMethode_paiement(String methode_paiement) {
        this.methode_paiement = methode_paiement;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", user=" + user + ", date_commande=" + date_commande + ", adresse_livraison=" + adresse_livraison + ", prix_commande=" + prix_commande + ", methode_paiement=" + methode_paiement + ", telephone=" + telephone + '}';
    }
    
    
    
}
