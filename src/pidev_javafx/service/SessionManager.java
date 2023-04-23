/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.service;

/**
 *
 * @author khali
 */
public class SessionManager {
    
    private static int id;
    private static String email;
    private static String role;
    private static String password,nom,prenom;
    private static int private_key;
    private static boolean status;

    
    
    
    
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SessionManager.id = id;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SessionManager.email = email;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        SessionManager.role = role;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SessionManager.password = password;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        SessionManager.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        SessionManager.prenom = prenom;
    }

    public static int getPrivate_key() {
        return private_key;
    }

    public static void setPrivate_key(int private_key) {
        SessionManager.private_key = private_key;
    }

    public static boolean isStatus() {
        return status;
    }

    public static void setStatus(boolean status) {
        SessionManager.status = status;
    }
    
    
    
    
    
    
}
