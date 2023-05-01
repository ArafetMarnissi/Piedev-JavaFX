/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.service;

import pidev_javafx.entitie.Reclamation;
import java.util.List;

/**
 *
 * @author Skymil
 */
public interface IService<T> {
    void ajouter(T t);
    void modifier(int id,T t);
    void supprimer(int id);
    List<T> afficher();
}
