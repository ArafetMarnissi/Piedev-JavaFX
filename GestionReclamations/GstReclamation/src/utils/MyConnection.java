/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Skymil
 */
public class MyConnection {
    private final String URL="jdbc:mysql://127.0.0.1:3306/goldengym";
    private final String USER="root";
    private final String PWD="";
    private Connection conn;
    private static MyConnection instance;
    private MyConnection(){
        try {
            conn=DriverManager.getConnection(URL, USER, PWD);
            System.out.println("connecter");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public static MyConnection getInstance(){
        if(instance==null){
            instance=new MyConnection();
        }
        else{
            System.out.println("deja connecter");
        }
        
        return instance;
    }

    public Connection getConn() {
        return conn;
    }
    
}
