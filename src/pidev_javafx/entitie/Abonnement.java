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
public class Abonnement {
    private int id;
    private String nomAbonnement;
    private float prixAbonnement;
    private String dureeAbonnement;
    private int count =0 ;

    public Abonnement() {
    }

    public Abonnement(int id, String nomAbonnement, float prixAbonnement, String dureeAbonnement) {
        this.id = id;
        this.nomAbonnement = nomAbonnement;
        this.prixAbonnement = prixAbonnement;
        this.dureeAbonnement = dureeAbonnement;
    }

    public Abonnement(String nomAbonnement, float prixAbonnement, String dureeAbonnement) {
        this.nomAbonnement = nomAbonnement;
        this.prixAbonnement = prixAbonnement;
        this.dureeAbonnement = dureeAbonnement;
    }

    public Abonnement(int id, String nomAbonnement, float prixAbonnement, String dureeAbonnement, int count) {
        this.id = id;
        this.nomAbonnement = nomAbonnement;
        this.prixAbonnement = prixAbonnement;
        this.dureeAbonnement = dureeAbonnement;
        this.count = count;
    }

    public Abonnement(int id) {
        this.id = id;
    }



    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomAbonnement() {
        return nomAbonnement;
    }

    public void setNomAbonnement(String nomAbonnement) {
        this.nomAbonnement = nomAbonnement;
    }

    public float getPrixAbonnement() {
        return prixAbonnement;
    }

    public void setPrixAbonnement(float prixAbonnement) {
        this.prixAbonnement = prixAbonnement;
    }

    public String getDureeAbonnement() {
        return dureeAbonnement;
    }

    public void setDureeAbonnement(String dureeAbonnement) {
        this.dureeAbonnement = dureeAbonnement;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", nomAbonnement=" + nomAbonnement + ", prixAbonnement=" + prixAbonnement + ", dureeAbonnement=" + dureeAbonnement + ", count=" + count + '}';
    }
    
    
    
}
