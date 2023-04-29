/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.entitie;

//import java.util.Date;

import java.sql.Date;
import java.sql.Time;


/**
 *
 * @author marni
 */
public class Activite {
    
    private int id,nbrePlace;
    private String nomActivite,descriptionActivite,Image;
    private Date DateActivite;
    private Time TimeActivite,end;
    private Coach coach;

    public Activite() {
    }

    public Activite(int id) {
        this.id = id;
    }

    public Activite(int id, int nbrePlace, String nomActivite, String descriptionActivite, String Image, Date DateActivite, Time TimeActivite, Time end) {
        this.id = id;
        this.nbrePlace = nbrePlace;
        this.nomActivite = nomActivite;
        this.descriptionActivite = descriptionActivite;
        this.Image = Image;
        this.DateActivite = DateActivite;
        this.TimeActivite = TimeActivite;
        this.end = end;
    }

    public Activite(int id, int nbrePlace, String nomActivite, String descriptionActivite, String Image, Date DateActivite, Time TimeActivite, Time end, Coach coach) {
        this.id = id;
        this.nbrePlace = nbrePlace;
        this.nomActivite = nomActivite;
        this.descriptionActivite = descriptionActivite;
        this.Image = Image;
        this.DateActivite = DateActivite;
        this.TimeActivite = TimeActivite;
        this.end = end;
        this.coach = coach;
    }

    public Activite(int id, int nbrePlace, String nomActivite, String descriptionActivite) {
        this.id = id;
        this.nbrePlace = nbrePlace;
        this.nomActivite = nomActivite;
        this.descriptionActivite = descriptionActivite;
    }

    public Activite(int id, int nbrePlace, String nomActivite, String descriptionActivite, Coach coach) {
        this.id = id;
        this.nbrePlace = nbrePlace;
        this.nomActivite = nomActivite;
        this.descriptionActivite = descriptionActivite;
        this.coach = coach;
    }

    public Activite(int id, int nbrePlace, String nomActivite, String descriptionActivite, String Image) {
        this.id = id;
        this.nbrePlace = nbrePlace;
        this.nomActivite = nomActivite;
        this.descriptionActivite = descriptionActivite;
        this.Image = Image;
    }
    
    public Activite(int nbrePlace, String nomActivite, String descriptionActivite, String Image) {
        this.nbrePlace = nbrePlace;
        this.nomActivite = nomActivite;
        this.descriptionActivite = descriptionActivite;
        this.Image = Image;
    }

    public Activite(int nbrePlace, String nomActivite, String descriptionActivite, String Image, Coach coach) {
        this.nbrePlace = nbrePlace;
        this.nomActivite = nomActivite;
        this.descriptionActivite = descriptionActivite;
        this.Image = Image;
        this.coach = coach;
    }

    public Activite(int id, int nbrePlace, String nomActivite, String descriptionActivite, String Image, Coach coach) {
        this.id = id;
        this.nbrePlace = nbrePlace;
        this.nomActivite = nomActivite;
        this.descriptionActivite = descriptionActivite;
        this.Image = Image;
        this.coach = coach;
    }

    public Activite(int nbrePlace, String nomActivite, String descriptionActivite, String Image, Date DateActivite, Coach coach) {
        this.nbrePlace = nbrePlace;
        this.nomActivite = nomActivite;
        this.descriptionActivite = descriptionActivite;
        this.Image = Image;
        this.DateActivite = DateActivite;
        this.coach = coach;
    }

    public Activite(int nbrePlace, String nomActivite, String descriptionActivite, String Image, Date DateActivite, Time TimeActivite, Time end, Coach coach) {
        this.nbrePlace = nbrePlace;
        this.nomActivite = nomActivite;
        this.descriptionActivite = descriptionActivite;
        this.Image = Image;
        this.DateActivite = DateActivite;
        this.TimeActivite = TimeActivite;
        this.end = end;
        this.coach = coach;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrePlace() {
        return nbrePlace;
    }

    public void setNbrePlace(int nbrePlace) {
        this.nbrePlace = nbrePlace;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public String getDescriptionActivite() {
        return descriptionActivite;
    }

    public void setDescriptionActivite(String descriptionActivite) {
        this.descriptionActivite = descriptionActivite;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public Date getDateActivite() {
        return DateActivite;
    }

    public void setDateActivite(Date DateActivite) {
        this.DateActivite = DateActivite;
    }

    public Time getTimeActivite() {
        return TimeActivite;
    }

    public void setTimeActivite(Time TimeActivite) {
        this.TimeActivite = TimeActivite;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }
    
    
    
}
