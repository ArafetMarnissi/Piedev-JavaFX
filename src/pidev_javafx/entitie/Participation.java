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
    
    private int id,id_u;
    private Date dateParticipation;
    private Activite activite;

    public Participation() {
    }

    public Participation(int id) {
        this.id = id;
    }
    

    public Participation(int id, int id_u, Date dateParticipation, Activite activite) {
        this.id = id;
        this.id_u = id_u;
        this.dateParticipation = dateParticipation;
        this.activite = activite;
    }

    public Participation(int id_u, Date dateParticipation, Activite activite) {
        this.id_u = id_u;
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

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
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
