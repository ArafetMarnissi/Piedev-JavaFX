/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Skymil
 */
public class Reclamation {
    private int id;
    private String description_reclamation;
    private TypeReclamation type_reclamation;
    private Date date_reclamation;

    public Reclamation() {
    }

    public Reclamation(String description_reclamation, TypeReclamation type_reclamation, Date date_reclamation) {
        this.description_reclamation = description_reclamation;
        this.type_reclamation = type_reclamation;
        this.date_reclamation = date_reclamation;
    }

    public Reclamation(int id, String description_reclamation, TypeReclamation type_reclamation, Date date_reclamation) {
        this.id = id;
        this.description_reclamation = description_reclamation;
        this.type_reclamation = type_reclamation;
        this.date_reclamation = date_reclamation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription_reclamation() {
        return description_reclamation;
    }

    public void setDescription_reclamation(String description_reclamation) {
        this.description_reclamation = description_reclamation;
    }

    public TypeReclamation getType_reclamation() {
        return type_reclamation;
    }

    public void setType_reclamation(TypeReclamation type_reclamation) {
        this.type_reclamation = type_reclamation;
    }

    public String getDate_reclamation() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yy");
        String date=dateFormat.format(date_reclamation);
        return date;
    }

    public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yy");
        String date=dateFormat.format(date_reclamation);
        return "Reclamation{" + "id=" + id + ", description_reclamation=" + description_reclamation + ", type_reclamation=" + type_reclamation + ", date_reclamation=" + date + '}'+"\n";
    }
    
    
    
}
