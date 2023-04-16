/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.javafx.controller;

    import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
/**
 *
 * @author moene
 */
public class FxmlLoader {




    private Pane workPlace;

    public Pane getPage(String fileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev_javafx/gui/" + fileName + ".fxml"));
            workPlace = loader.load();
        } catch (IOException e) {
            System.out.println("no page " + fileName+"\n"+e);
        }
        return workPlace;
    }
}
