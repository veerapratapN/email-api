package com.managedCare.salesOperation.enquiry.Service.impl;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {

	@Value("${mail.from.user}")
	String from;

	@Value("${mail.from.user.password}")
	String password;

	@Value("${mail.stander.subject}")
	String subject;

	@Value("${mail.source.filepath}")
	String filePath;

	@Autowired
	Properties properties;

	public void sendMail(String toMailId, String mailBody, String finalFilename) throws MessagingException {

		Session session = null;
		MimeMessage mimeMessage = null;
		Transport transport = null;

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.from", from);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");

		try {
			session = Session.getInstance(properties, null);

			mimeMessage = new MimeMessage(session);
			mimeMessage.setRecipients(Message.RecipientType.TO, toMailId);
			mimeMessage.setSubject(subject);
			mimeMessage.setSentDate(new Date());
			mimeMessage.setText(mailBody);

			Multipart multipart = new MimeMultipart();
			BodyPart messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filePath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(finalFilename);
			multipart.addBodyPart(messageBodyPart);

			mimeMessage.setContent(multipart);

			transport = session.getTransport("smtp");
			System.out.println(from+"==="+password);
			transport.connect(from, password);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

		} finally {
			if (transport != null)
				transport.close();
		}

	}

}
