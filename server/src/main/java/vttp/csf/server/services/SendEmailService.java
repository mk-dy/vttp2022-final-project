package vttp.csf.server.services;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


@Service
public class SendEmailService {
    
    @Value("${proj.email.user}") 
    String fromEmail;
    
    @Value("${proj.email.password}") 
    String password;

    // https://www.digitalocean.com/community/tutorials/javamail-example-send-mail-in-java-smtp
    
    public void sendRegisterEmail(String toEmail, String emailSubject) {

        System.out.println("from email: " + fromEmail);
        System.out.println("to email: " + toEmail);
        System.out.println("password: " + password);

		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
		
        //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
            @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getInstance(props, auth);

        try
	    {
	      MimeMessage msg = new MimeMessage(session);
	    //   set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");
	      msg.setFrom(new InternetAddress(fromEmail, "isaac"));
	      msg.setReplyTo(InternetAddress.parse(fromEmail, false));
	      msg.setSubject(emailSubject, "UTF-8");
          msg.setSentDate(new Date());
          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      msg.setContent(RegisterMsgContent(),"text/html");
        //   msg.setText(body, "UTF-8");

	      System.out.println("Message is ready");
    	  Transport.send(msg);  

	      System.out.println("Email Sent Successfully!!");

	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
    }
    
    public String RegisterMsgContent() {
        String msg = """
            <div style=\"text-align: center;\">
                <h1>Welcome aboard!</h1>
                <img style=\"width: 100px;\" src=\"https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/isaac-logo-2-test.png\"/>
                <p>You have successfully created an account! Have any questions so far? Contact us via email.</p>
                <br>
                <p>Enjoy!</p>
            </div> 
        """;

        return msg;
          
    }


    public void sendOrderEmail(String toEmail, String emailSubject) {

		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
		
        //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
            @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getInstance(props, auth);

        try
	    {
	      MimeMessage msg = new MimeMessage(session);
	    //   set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");
	      msg.setFrom(new InternetAddress(fromEmail, "isaac"));
	      msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
	      msg.setSubject(emailSubject, "UTF-8");
          msg.setSentDate(new Date());
          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      msg.setContent(OrderMsgContent(),"text/html");
        //   msg.setText(body, "UTF-8");

	      System.out.println("Message is ready");
    	  Transport.send(msg);  

	      System.out.println("Email Sent Successfully!!");

	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
    }

    public String OrderMsgContent() {
        String msg = """
            <div>
                <h1>Thank You for Your order</h1>
                <img style=\"width: 250px;\" src=\"https://mattstorage.sgp1.digitaloceanspaces.com/vttp-final-project/isaac-logo-2-test.png\"/>
                <p>Dear isaac customer,</p>
                <br>
                <p>Your order has been placed. Please expect another email once your order has been shipped.</p>
            </div> 
        """;

        return msg;
          
    }



}
