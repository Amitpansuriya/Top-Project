package Services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Servicess {

	public void sendMail(String emailid){
		System.out.println("email =======>"+emailid);
		final String username  = "Stalliontech999@gmail.com";
		final String password   = "tzyyavsqmoolpofy";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.debug", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//		587
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		System.out.println("session complete");
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailid));//to mail address.
			message.setSubject("Hi This Is Confirmation Email for testing by System generated(Amit pansuriya)");
			message.setText("Your Appointment Booked By Doctor.Dear ,Your followup consultation with Doctor is approching in 2 days.Please call to Schedule Your appointment-thanks, Stallion Hospital"
					+ "(System Generated (pls if you get this email must replay with this code 'yes' Amit pansuriya))");

			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}		
	}
	public void sendRejectMail(String emailid){
		System.out.println("email =======>"+emailid);
		final String username  = "Stalliontech999@gmail.com";
		final String password   = "tzyyavsqmoolpofy";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.debug", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//		587
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		System.out.println("session complete");
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailid));//to mail address.
			message.setSubject("Hi This Is Confirmation Email for testing by System generated(Amit pansuriya)");
			message.setText("Your Appointment Rejected By Doctor(System Generated (pls if you get this email must replay with this code 'yes' Amit pansuriya))");

			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}		
	}
	public void sendCancelMail(String emailid){
		System.out.println("email =======>"+emailid);
		final String username  = "Stalliontech999@gmail.com";
		final String password   = "tzyyavsqmoolpofy";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.debug", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//		587
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		System.out.println("session complete");
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailid));//to mail address.
			message.setSubject("Hi This Is Confirmation Email for testing by System generated(Amit pansuriya)");
			message.setText("Cancel Appointment by You(System Generated (pls if you get this email must replay with this code 'yes' Amit pansuriya))");

			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}		
	}


}
