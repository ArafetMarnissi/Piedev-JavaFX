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
public class Participation {
    
    private int id;
    private User user;
    private Date dateParticipation;
    private Activite activite;

    public Participation() {
    }

    public Participation(int id) {
        this.id = id;
    }
    

    public Participation(int id, User user, Date dateParticipation, Activite activite) {
        this.id = id;
        this.user = user;
        this.dateParticipation = dateParticipation;
        this.activite = activite;
    }

    public Participation(User user, Date dateParticipation, Activite activite) {
        this.user = user;
        this.dateParticipation = dateParticipation;
        this.activite = activite;
    }

    public Participation(int id, Date dateParticipation) {
        this.id = id;
        this.dateParticipation = dateParticipation;
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

  

    public Date getDateParticipation() {
        return dateParticipation;
    }

    public void setDateParticipation(Date dateParticipation) {
        this.dateParticipation = dateParticipation;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }
    
    
}
