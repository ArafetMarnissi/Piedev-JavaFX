/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_javafx.tools;

import java.io.File;
import java.sql.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import pidev_javafx.entitie.Commande;

/**
 *
 * @author marni
 */
public class MailFacture {
        public static void sendMail(String recepient,Commande c) throws Exception {
        System.err.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "ggym45@outlook.com";   //    goldengympidev@gmail.com
        String password = "Arafet26845815";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }  
        });
        Message message = prepareMessage(session, myAccountEmail, recepient, c);
        Transport.send(message);
        System.err.println("Message sent !");
    }

private static Message prepareMessage(Session session,String myAccountEmail, String recepient,Commande c ) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Commande confirmée");
            message.setText("MERCI D’AVOIR MAGASINÉ SUR Golden Gym!");
        MimeBodyPart pdfAttachment = new MimeBodyPart();
        pdfAttachment.attachFile(new File("C:\\Users\\marni\\OneDrive\\Bureau\\Facture_N°" + c.getId() + ".pdf"));
        
        // create the multipart object and add the message body part to it
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(pdfAttachment);
        
        // add the multipart object to the message
        message.setContent(multipart);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
    
}
