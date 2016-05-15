package controllers;
import play.mvc.Controller;
import play.mvc.Result;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * Created by Amruta Pawar on 2/15/2016.
 */
public class Sendmail extends Controller {

    String to = "communitytoolsharing@gmail.com";
    String from = "communitytoolsharing@gmail.com";
     String password = "toolshare";
    String host = "smtp.gmail.com";

    public Result sendemail()
    {
        Properties props = System.getProperties();

      //  props.put("mail.smtp.socketFactory.port","465");
      //  props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com") ;
        props.put("mail.smtp.port","587");

        //props.setProperty("mail.smtp.host",host);
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }

        });


        try{
            String from = "communitytoolsharing@gmail.com";
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Confirmation from CTS");
            message.setContent("This is an email from cts","text/html");
            message.setText("CTS has confirmed your details and purchase of tool.");

            Transport.send(message);



        }
        catch (MessagingException mex){
            mex.printStackTrace();

        }
        return ok("Email sent successfully");

    }


}
