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
 * @author damma
 */
public class SMS {
   public static final String ACCOUNT_SID = "ACc210d7a671c53bca6b98f6a1bec72d25";
    public static final String AUTH_TOKEN = "3ba965c93b750e6672363b75a4e5b5ae";
    public void sms(String nomp){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try{
            Message message = Message.creator(
                new PhoneNumber("+216 93 763 578"),
                new PhoneNumber("+12763303927"),
                "La quantite du produit "+nomp+" va expiree bientot.")
            .create();

        System.out.println(message.getSid());
        }catch(Exception e){
            System.out.println(e.getMessage()); 
        }
    }
    
}
//public class SMS{
//    public void sms(String username,String password,String to,String message){
//        try{
//            // This URL is used for sending messages
//    String myURI = "https://api.bulksms.com/v1/messages";
//
//    // change these values to match your own account
//    String myUsername = ""+username+"";
//    String myPassword = ""+password+"";
//
//    // the details of the message we want to send
//    String myData = "{to: \""+to+"\", encoding: \"UNICODE\", body: \""+message+"\"}";
//
//    // if your message does not contain unicode, the "encoding" is not required:
//    // String myData = "{to: \"1111111\", body: \"Hello Mr. Smith!\"}";
//
//    // build the request based on the supplied settings
//    URL url = new URL(myURI);
//    HttpURLConnection request = (HttpURLConnection) url.openConnection();
//    request.setDoOutput(true);
//
//    // supply the credentials
//    String authStr = myUsername + ":" + myPassword;
//    String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
//    request.setRequestProperty("Authorization", "Basic " + authEncoded);
//
//    // we want to use HTTP POST
//    request.setRequestMethod("POST");
//    request.setRequestProperty( "Content-Type", "application/json");
//
//    // write the data to the request
//    OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
//    out.write(myData);
//    out.close();
//
//    // try ... catch to handle errors nicely
//    try {
//      // make the call to the API
//      InputStream response = request.getInputStream();
//      BufferedReader in = new BufferedReader(new InputStreamReader(response));
//      String replyText;
//      while ((replyText = in.readLine()) != null) {
//        System.out.println(replyText);
//      }
//      in.close();
//    } catch (IOException ex) {
//      System.out.println("An error occurred:" + ex.getMessage());
//      BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
//      // print the detail that comes with the error
//      String replyText;
//      while ((replyText = in.readLine()) != null) {
//        System.out.println(replyText);
//      }
//      in.close();
//    }
//    request.disconnect();
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//            
//    }
//}
