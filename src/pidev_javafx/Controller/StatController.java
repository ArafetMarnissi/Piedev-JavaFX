/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import pidev_javafx.entitie.Produit;
import pidev_javafx.service.ProduitService;

/**
 * FXML Controller class
 *
 * @author damma
 */
public class StatController implements Initializable {

    @FXML
    private StackPane stackstat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherStatistiques();
    }    
    
    private void afficherStatistiques() {
    // Récupérer la liste des produits depuis une source de données
   ProduitService cs=new ProduitService();
        ObservableList<Produit>produits=FXCollections.observableArrayList();
        List<String>nompfaible=new ArrayList<>();  
        List<String>nompex=new ArrayList<>();
        List<String>nompmoy=new ArrayList<>();
        produits=cs.afficher();  // récupérer la liste des produits
System.out.println(produits);
  int nbproduitsNoteFaible = 0;
int nbproduitsNoteMoyenne = 0;
int nbproduitsNoteExcellente = 0;

// Compter le nombre d'produits pour chaque catégorie de note
for (Produit produit : produits) {
    float note = produit.getNote();
    if (note < 2.0) {
        nbproduitsNoteFaible++;
        nompfaible.add(produit.getNom());
    } else if (note < 3.0) {
        nbproduitsNoteMoyenne++;
        nompmoy.add(produit.getNom());
    } else {
        nbproduitsNoteExcellente++;
        nompex.add(produit.getNom());
    }
}

    // Créer une liste de données pour le camembert
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    int totalproduits = nbproduitsNoteFaible + nbproduitsNoteMoyenne + nbproduitsNoteExcellente;
   
    // Ajouter les données au diagramme circulaire
if (nbproduitsNoteFaible > 0) {
    double pourcentage = ((float)nbproduitsNoteFaible / totalproduits) * 100.0;
    pieChartData.add(new PieChart.Data("Note faible (" + nbproduitsNoteFaible + ") " + String.format("%.2f", pourcentage) + "%"+"\n"+nompfaible, nbproduitsNoteFaible));
}
if (nbproduitsNoteMoyenne > 0) {
    double pourcentage = ((float)nbproduitsNoteMoyenne / totalproduits) * 100.0;
    pieChartData.add(new PieChart.Data("Note moyenne (" + nbproduitsNoteMoyenne + ") " + String.format("%.2f", pourcentage) + "%"+"\n"+nompmoy, nbproduitsNoteMoyenne));
}
if (nbproduitsNoteExcellente > 0) {
    double pourcentage = ((float)nbproduitsNoteExcellente / totalproduits) * 100.0;
    pieChartData.add(new PieChart.Data("Note excellente (" + nbproduitsNoteExcellente + ") " + String.format("%.2f", pourcentage) + "%"+"\n"+nompex, nbproduitsNoteExcellente));
}
    // Créer et configurer le camembert
    PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Statistiques des produits en fonction de leur note");
    stackstat.getChildren().add(chart);
    }
    
}
