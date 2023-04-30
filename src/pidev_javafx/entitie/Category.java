/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.entitie;

import java.util.List;


/**
 *
 * @author marni
 */
public class Category {
    private int id;
    private String NomCategory;
    private String ImageCategory;
    //@OneToMany(mappedBy="category")
    //private List<Produit> products;

    public Category() {
    }

    public Category(String NomCategory) {
        this.NomCategory = NomCategory;
    }

    public Category(int id) {
        this.id = id;
    }
    

    public Category(String NomCategory, String ImageCategory) {
        this.NomCategory = NomCategory;
        this.ImageCategory = ImageCategory;
    }

    public Category(int id, String NomCategory, String ImageCategory) {
        this.id = id;
        this.NomCategory = NomCategory;
        this.ImageCategory = ImageCategory;
    }

    public int getId() {
        return id;
    }

    public String getNomCategory() {
        return NomCategory;
    }

    public void setNomCategory(String NomCategory) {
        this.NomCategory = NomCategory;
    }

    public String getImageCategory() {
        return ImageCategory;
    }

    public void setImageCategory(String ImageCategory) {
        this.ImageCategory = ImageCategory;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", NomCategory=" + NomCategory + ", ImageCategory=" + ImageCategory + '}';
    }

    
    
}
