/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.entitie;

import java.util.*;
import java.util.Date;

/**
 *
 * @author marni
 */
public class Reservation {
    
    private int id ;
    private Date DateDebut;
    private Date DateFin;
    private ArrayList<Abonnement> abonnements;
    private int user;

    public Reservation() {
    }

    public Reservation(int id, Date DateDebut, Date DateFin, ArrayList<Abonnement> abonnements) {
        this.id = id;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.abonnements = abonnements;
    }

    public Reservation(Date DateDebut, Date DateFin, ArrayList<Abonnement> abonnements) {
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.abonnements = abonnements;
    }

    public Reservation(int id, Date DateDebut, Date DateFin) {
        this.id = id;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public ArrayList<Abonnement> getAbonnements() {
        return abonnements;
    }

    public void setAbonnements(ArrayList<Abonnement> abonnements) {
        this.abonnements = abonnements;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
    
    

   
    
    
}
