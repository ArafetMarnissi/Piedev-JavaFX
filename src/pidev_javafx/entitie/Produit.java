/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.entitie;

import java.sql.Date;


/**
 *
 * @author marni
 */
public class Produit {
    private int id;
    private Category category;
    private String nom,description;
    private float prix_produit;
    private int quantite_produit;
    private String image_produit;
    private Date date_expiration;
    private float note;

    public Produit() {
    }

    public Produit(Category category, String nom, String description, float prix_produit, int quantite_produit, String image_produit, Date date_expiration, float note) {
        this.category = category;
        this.nom = nom;
        this.description = description;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.image_produit = image_produit;
        this.date_expiration = date_expiration;
        this.note = note;
    }

    public Produit(int id, Category category, String nom, String description, float prix_produit, int quantite_produit, String image_produit, Date date_expiration, float note) {
        this.id = id;
        this.category = category;
        this.nom = nom;
        this.description = description;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.image_produit = image_produit;
        this.date_expiration = date_expiration;
        this.note = note;
    }
    
    

    public Produit(int id, Category category, String nom, String description, float prix_produit, int quantite_produit, String image_produit, Date date_expiration) {
        this.id = id;
        this.category = category;
        this.nom = nom;
        this.description = description;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.image_produit = image_produit;
        this.date_expiration = date_expiration;
        this.note = 0;
    }

    public Produit(Category category, String nom, String description, float prix_produit, int quantite_produit, String image_produit, Date date_expiration) {
        this.category = category;
        this.nom = nom;
        this.description = description;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.image_produit = image_produit;
        this.date_expiration = date_expiration;
        this.note = 0;
    }

    public Produit(int id, String nom, String description, float prix_produit, int quantite_produit, String image_produit, Date date_expiration) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.image_produit = image_produit;
        this.date_expiration = date_expiration;
    }

    public Produit(int id, String nom, String description, float prix_produit, int quantite_produit, String image_produit) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.image_produit = image_produit;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(float prix_produit) {
        this.prix_produit = prix_produit;
    }

    public int getQuantite_produit() {
        return quantite_produit;
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    public String getImage_produit() {
        return image_produit;
    }

    public void setImage_produit(String image_produit) {
        this.image_produit = image_produit;
    }

    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", category=" + category + ", nom=" + nom + ", description=" + description + ", prix_produit=" + prix_produit + ", quantite_produit=" + quantite_produit + ", image_produit=" + image_produit + ", date_expiration=" + date_expiration + ", note=" + note + '}';
    }
    
    
    
}
