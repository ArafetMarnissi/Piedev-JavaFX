/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Skymil
 */
public class Reponse {
    private int id;
    private String descriptionreponse;
    private Date date_reponse;
    private int idreclamation_id;

    public Reponse() {
    }

    public Reponse(String descriptionreponse, Date date_reponse, int idreclamation_id) {
        this.descriptionreponse = descriptionreponse;
        this.date_reponse = date_reponse;
        this.idreclamation_id = idreclamation_id;
    }

    public Reponse(int id, String descriptionreponse, Date date_reponse, int idreclamation_id) {
        this.id = id;
        this.descriptionreponse = descriptionreponse;
        this.date_reponse = date_reponse;
        this.idreclamation_id = idreclamation_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescriptionreponse() {
        return descriptionreponse;
    }

    public void setDescriptionreponse(String descriptionreponse) {
        this.descriptionreponse = descriptionreponse;
    }

    public Date getDate_reponse() {
        return date_reponse;
    }

    public void setDate_reponse(Date date_reponse) {
        this.date_reponse = date_reponse;
    }

    public int getIdreclamation_id() {
        return idreclamation_id;
    }

    public void setIdreclamation_id(int idreclamation_id) {
        this.idreclamation_id = idreclamation_id;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", descriptionreponse=" + descriptionreponse + ", date_reponse=" + date_reponse + ", idreclamation_id=" + idreclamation_id + '}';
    }
    
    
}
