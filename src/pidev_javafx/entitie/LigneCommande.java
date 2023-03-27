/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.entitie;

/**
 *
 * @author marni
 */
public class LigneCommande {
    private int id;
    private Commande commande;
    private Produit produit;
    private int quantite_produit;
    private float prix_unitaire;

    public LigneCommande() {
    }

    public LigneCommande(Commande commande, Produit produit, int quantite_produit, float prix_unitaire) {
        this.commande = commande;
        this.produit = produit;
        this.quantite_produit = quantite_produit;
        this.prix_unitaire = prix_unitaire;
    }

    public LigneCommande(int id, Commande commande, Produit produit, int quantite_produit, float prix_unitaire) {
        this.id = id;
        this.commande = commande;
        this.produit = produit;
        this.quantite_produit = quantite_produit;
        this.prix_unitaire = prix_unitaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite_produit() {
        return quantite_produit;
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    public float getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(float prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    @Override
    public String toString() {
        return "Ligne_Commande{" + "id=" + id + ", commande=" + commande + ", produit=" + produit + ", quantite_produit=" + quantite_produit + ", prix_unitaire=" + prix_unitaire + '}';
    }
    
    
    
}
