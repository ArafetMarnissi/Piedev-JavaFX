/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.tools;

import java.sql.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author saifz
 */
public class JavaMailReservation {
    public static void sendMail(String recepient, Date dateDebut, Date dateFin, String nom, Float prix, String duree) throws Exception {
        System.err.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "goldengym2023@outlook.com";   //    goldengympidev@gmail.com
        String password = "Golden2023";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }  
        });
        Message message = prepareMessage(session, myAccountEmail, recepient, dateDebut, dateFin, nom, prix, duree);
        Transport.send(message);
        System.err.println("Message sent !");
    }

private static Message prepareMessage(Session session,String myAccountEmail, String recepient, Date dateDebut, Date dateFin, String nom, Float prix, String duree) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Reservation confirmée");
            message.setText("Votre reservation a été effectuée avec succès. \n\nDétails de la réservation : \nDate de début : " + dateDebut + "\nDate de fin : " + dateFin +"\n\nDétails de l'abonnement reserve : \nNom : " + nom + "\nPrix : " + prix + "\nDuree : " + duree );
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
   
}

