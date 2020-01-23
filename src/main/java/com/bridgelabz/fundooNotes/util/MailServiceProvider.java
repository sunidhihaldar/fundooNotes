package com.bridgelabz.fundooNotes.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailServiceProvider {

	private static final String SENDER_EMAIL = System.getenv("email");
	private static final String SENDER_PASSWORD = System.getenv("password");
	
	private static MimeMessage mimeMessageConfig(Session session, String toEmail, String subject, String body) {
		MimeMessage message = new MimeMessage(session);
		try {
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			message.setFrom(new InternetAddress(System.getenv("email"), "Fundoo Notes"));
			message.setReplyTo(InternetAddress.parse(System.getenv("email"), false));
			message.setSubject(subject, "UTF-8");
			message.setText(body, "UTF-8");
			message.setSentDate(new Date());
			message.setRecipients(Message.RecipientType.TO ,InternetAddress.parse(toEmail, false));
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public void sendEmail(String toEmail, String subject, String body) {
		//set properties config
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		properties.put("mail.smtp.port", "587"); //TLS Port
		properties.put("mail.smtp.auth", "true"); //enable authentication
		properties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
		//verify authentication details of sender
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
			}
		};
		
		Session session = Session.getInstance(properties, auth);
		try {
			Transport.send(mimeMessageConfig(session, toEmail, subject, body));
			System.out.println("Message sent");
		} catch(MessagingException e) {
			System.out.println("Error message " +e.getMessage());
		}
	}
	
//	public static void main(String[] args) {
//		MailServiceProvider.sendEmail("sunidhihaldar@gmail.com", "hello", "yes");
//	}
}
