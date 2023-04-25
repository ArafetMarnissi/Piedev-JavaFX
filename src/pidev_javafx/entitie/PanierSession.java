/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.entitie;

import java.util.HashMap;

/**
 *
 * @author marni
 */
public class PanierSession {
    
    private static PanierSession instance;
    private static HashMap<Produit, Integer> panier = new HashMap<>();

    private PanierSession(HashMap<Produit, Integer> panier) {
        this.panier = panier;
    }
    
    
    public static PanierSession getInstance() {
        if (instance == null) {
            instance = new PanierSession(new HashMap<>());
        }
        return instance;
    }

    public static void setInstance(PanierSession instance) {
        PanierSession.instance = instance;
    }
    public static PanierSession EndSession(){
        instance =null;
        panier.clear();
        return instance;
    }
    public static HashMap<Produit, Integer> getPanier() {
        return panier;
    }

    public static void setPanier(HashMap<Produit, Integer> panier) {
        PanierSession.panier = panier;
    }

    public static synchronized PanierSession getInstance(HashMap<Produit, Integer> panier) {
        if (instance == null) {
            instance = new PanierSession(panier);
        }
        return instance;
    }
    public void addProduct(Produit produit){
        PanierSession.getInstance();
        if(panier.containsKey(produit))
        {
            panier.replace(produit, panier.get(produit)+1);
        }
        else{
            panier.put(produit, 1);
        }
    }
        public void decreaseProduct(Produit produit){
        PanierSession.getInstance();
        if(panier.containsKey(produit))
        {
            if(panier.get(produit)>1){
            panier.replace(produit, panier.get(produit)-1);
            }
        }
    }
    public int getQuantity(Produit produit){
        PanierSession.getInstance();
        if(panier.containsKey(produit))
        {
            return panier.get(produit);
        }
        else
            return 0;
    }    
    public float calculTotale(){
        PanierSession.getInstance();
        float total =0;
        for (Produit prod : panier.keySet()){
            total +=prod.getPrix_produit()*panier.get(prod);
        }
        return total;
    }    
    }
    

