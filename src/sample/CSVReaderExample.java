package sample;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CSVReaderExample {

	public static void main(String[] args) {

		String csvFile = "/Users/rotunb200/Documents/workspace/sample/src/sample/contact.csv";

		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(csvFile));
			String[] line;
			while ((line = reader.readNext()) != null) {
				sendEmail(line[0], line[1], line[3]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void sendEmail(String recipients, String agent, String address) {
		final String username = "contact@preofferinspection.com";
		final String password = "contact1@poi";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("contact@preofferinspection.com", "PreOfferInspection"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
			message.setSubject(address);
			message.setContent(
					"<div style=\"font-size:16px\">Dear " + agent
							+ ",<br/><br/>Potential buyers are searching for your listed property: " + address
							+ " on preofferinspection.com. "
							+ "Please send your contact information to contact@preofferinspection.com for our licensed inspector to reach you."
							+ "<br/><br/>Home buyers come to preofferinspection.com to view trusted inspections by our licensed affiliate inspectors for properties like yours. "
							+ "Be proactive and competitive with a PreOfferInspection. Gain trust from potential buyers. "
							+ "Get better prepared for questions and offers. No more inspection contingencies. "
							+ "No need to have multiple inspectors in and out of your house. Sell your home quicker."
							+ "<br/><br/>Daniel Watkins<br/>Customer Service Representative<br/>PreofferInspection.com<br/>(571)315-0790</div>",
					"text/html; charset=utf-8");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}