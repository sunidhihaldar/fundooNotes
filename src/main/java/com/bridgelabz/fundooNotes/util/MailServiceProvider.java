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

import org.springframework.stereotype.Component;

@Component
public class MailServiceProvider {
	
	private static final String SENDER_EMAIL = System.getenv("email");
	private static final String SENDER_PASSWORD = System.getenv("password");

	public void sendEmail(String toEmailId, String subject, String bodyContaint) {
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		properties.put("mail.smtp.port", "587"); // TLS Port
		properties.put("mail.smtp.auth", "true"); // enable authentication
		properties.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

		Authenticator authentication = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
			}
		};
		Session session = Session.getInstance(properties, authentication);
		try {
			Transport.send(mimeMessageConfiguration(session, toEmailId, subject, bodyContaint));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private MimeMessage mimeMessageConfiguration(Session session, String toEmail, String subject, String body) {

		MimeMessage mimeMessage = new MimeMessage(session);
		// set message headers
		try {
			mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
			mimeMessage.addHeader("format", "flowed");
			mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");
			mimeMessage.setFrom(new InternetAddress(SENDER_EMAIL, "Fundoo Note Application"));
			mimeMessage.setReplyTo(InternetAddress.parse(SENDER_EMAIL, false));
			mimeMessage.setSubject(subject, "UTF-8");
			mimeMessage.setText(body, "UTF-8");
			mimeMessage.setSentDate(new Date());
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return mimeMessage;
	}
}