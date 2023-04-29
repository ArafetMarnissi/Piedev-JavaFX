/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.entitie;

import java.net.*;
import java.util.Base64;
import java.io.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author mmarr
 */
public class SMS {
       public static final String ACCOUNT_SID = "ACe4bdf7fb2d45469b792d64f783a534a0";
    public static final String AUTH_TOKEN = "7c5946dfc5b4beffa4a01b0249bbd381";
    public void sms(String nomp){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try{
            Message message = Message.creator(
                new PhoneNumber("+216 52 441 562"),
                new PhoneNumber("+15076906590"),
                "Bonjour, une nouvelle activité "+nomp+" vient de s'ajouter.")
            .create();

        System.out.println(message.getSid());
        }catch(Exception e){
            System.out.println(e.getMessage()); 
        }
    }
}
