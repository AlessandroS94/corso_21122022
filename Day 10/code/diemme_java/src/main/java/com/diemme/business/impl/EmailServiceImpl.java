package com.diemme.business.impl;

import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diemme.business.interfaces.EmailService;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	@Value("${email.systemMail}")
	private String systemMail;
	@Value("${email.username}")
	private String username;
	@Value("${email.password}")
	private String password;
	@Value("${email.host}")
	private String host;
	@Value("${email.port}")
	private String port;
    @Value("${email.tls}")
	private String tls;

	@Override
	public void sendContact(String from, String object, String body, String sender) {

		try {

			Message message = new MimeMessage(connect());
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(systemMail));
			message.setSubject("Gentile staff Diemme: " + object);
			message.setText(body + "\n\n Cordiali saluti,\n " + sender + "\n\n Mail from: " + from);

			Transport.send(message);

			System.out.println("email send");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void sendUserActive(String from, String sender) {

		try {

			Message message = new MimeMessage(connect());
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(systemMail));
			message.setSubject("Gentile staff Diemme: hai un nuovo cliente da approvare!");
			message.setText("Il cliente: " + sender + " ha richiesto l'accesso alla piattaforma Diemme"
					+ "\n\n Cordiali saluti,\n " + "\n\n Mail user: " + from);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void sendUserActivated() {

		try {

			Message message = new MimeMessage(connect());
			message.setFrom(new InternetAddress(this.systemMail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(systemMail));
			message.setSubject("Gentile cliente: sei stato approvato dall'admin!");
			message.setText(
					"Gentile cliente, adesso puoi accedereso alla piattaforma Diemme" + "\n\n Cordiali saluti,\n ");

			Transport.send(message);

			System.out.println("email send");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void sendNotifyClientMessage(String from, String sender) {

		try {

			Message message = new MimeMessage(connect());
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(systemMail));
			message.setSubject("Gentile staff Diemme: hai un nuovo messaggio dal Cliente " + sender + " !");
			message.setText("Il cliente: " + sender + " ti ha mandato un nuovo messaggio, controlla la chat!"
					+ "\n\n Cordiali saluti,\n " + "\n\n Mail user: " + from);

			Transport.send(message);

			System.out.println("email send");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void sendNotifyDesignerMessage(String from, String sender) {

		try {

			Message message = new MimeMessage(connect());
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(systemMail));
			message.setSubject("Gentile cliente: hai un nuovo messaggio del designer " + sender + " !");
			message.setText("Il designer: " + sender + " ti ha mandato un nuovo messaggio, controlla la chat!"
					+ "\n\n Cordiali saluti, Staff Diemme\n " + "\n\n Mail user: " + from);

			Transport.send(message);

			System.out.println("email send");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void sendNotifyProductorMessage(String from, String sender) {

		try {

			Message message = new MimeMessage(connect());
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(systemMail));
			message.setSubject("Gentile cliente: hai un nuovo messaggio del produttore " + sender + " !");
			message.setText("Il produttore: " + sender + " ti ha mandato un nuovo messaggio, controlla la chat!"
					+ "\n\n Cordiali saluti, Staff Diemme\n " + "\n\n Mail user: " + from);

			Transport.send(message);

			System.out.println("email send");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void sendNotifyNewOrder(String from, String sender, String order) {

		try {

			Message message = new MimeMessage(connect());
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(systemMail));
			message.setSubject("Gentile Produttore: hai un nuovo ordine da realizzare !");
			message.setText("Il designer: " + sender
					+ " ha trasferito il layout in produzione, controlla la sezione \"Ordini da realizzare !\""
					+ "\n\n Cordiali saluti, Staff Diemme\n " + "\n\n Mail user: " + from);

			Transport.send(message);

			System.out.println("email send");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void sendNotifyOrderShip(String from, String sender, String order, String address) {

		try {

			Message message = new MimeMessage(connect());
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(systemMail));
			message.setSubject("Gentile Cliente: il tuo ordine è stato spedito !");
			message.setText("la produzione ha spedito il tuo ordine all'indirizzo: " + address
					+ ", una volta avvenuta la consegna modifica lo stato in \"completato\", in caso di problemi puoi contattare un nostro designer sulla sezione \"Chat !\""
					+ "\n\n Cordiali saluti, Staff Diemme\n " + "\n\n Mail user: " + from);

			Transport.send(message);

			System.out.println("email send");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void sendChangeStatusOrder(String from, String sender, String order, Boolean status) {

		try {

			Message message = new MimeMessage(connect());
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(systemMail));
			message.setSubject("Staff Diemme: L'ordine " + order + " ha cambiato stato !");
			message.setText("L'ordine " + order + " ha cambiato stato, controlla la sezione \"Status Ordini !\""
					+ "\n\n Cordiali saluti, Staff Diemme\n " + "\n\n Mail user: " + from);

			Transport.send(message);

			System.out.println("email send");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private Session connect() {



		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", this.tls);// it’s optional in Mailtrap
		props.put("mail.smtp.host", this.host);
		props.put("mail.smtp.port", this.port);//

		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		return session;

	}

}
