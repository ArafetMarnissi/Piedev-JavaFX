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
public class Coach {
    
      private int id,age_coach;
    private String nom_coach,image;

    public Coach() {
    }

    public Coach(int id, int age_coach, String nom_coach, String image) {
        this.id = id;
        this.age_coach = age_coach;
        this.nom_coach = nom_coach;
        this.image = image;
    }

    public Coach(int id, int age_coach, String nom_coach) {
        this.id = id;
        this.age_coach = age_coach;
        this.nom_coach = nom_coach;
    }
    

    public Coach(int age_coach, String nom_coach) {
        this.age_coach = age_coach;
        this.nom_coach = nom_coach;
    }

    public Coach(int age_coach, String nom_coach, String image) {
        this.age_coach = age_coach;
        this.nom_coach = nom_coach;
        this.image = image;
    }

    public Coach(int id) {
        this.id = id;
    }

    
    
    @Override
    public String toString() {
        return "Coach{" + "id=" + id + ", age_coach=" + age_coach + ", nom_coach=" + nom_coach + ", image=" + image + '}';
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge_coach() {
        return age_coach;
    }

    public void setAge_coach(int age_coach) {
        this.age_coach = age_coach;
    }

    public String getNom_coach() {
        return nom_coach;
    }

    public void setNom_coach(String nom_coach) {
        this.nom_coach = nom_coach;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
