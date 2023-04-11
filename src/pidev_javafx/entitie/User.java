/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.entitie;

import java.util.List;
import java.util.Random;

/**
 *
 * @author marni
 */
public class User {
    private int id;
    private String email;
    private String role;
    private String password,nom,prenom;
    private int private_key;
    private boolean status;

    
    public User() {
    }

    public User(String email, String password, String nom, String prenom) {
        this.email = email;
        this.role = "[\"ROLE_CLIENT\"]";
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.private_key = generateRandomNumber();
        this.status = false;
    }

    public User(int id, String email, String role, String password, String nom, String prenom, int private_key, boolean status) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.private_key = private_key;
        this.status = status;
    }
    

   public static int generateRandomNumber() {
        Random random = new Random();
        int min = 100000; // minimum 6-digit number
        int max = 999999; // maximum 6-digit number
        return random.nextInt(max - min + 1) + min;
    }
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(int private_key) {
        this.private_key = private_key;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
    
    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", role=" + role + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", private_key=" + private_key + ", status=" + status + '}';
    }
    
    
}