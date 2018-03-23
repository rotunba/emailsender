package sample;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class CSVReaderExample {

	public static void main(String[] args) {

		String csvFile = "/Users/rotunb200/Documents/workspace/sample/src/sample/contact.csv";

		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(csvFile));
			String[] line;
			while ((line = reader.readNext()) != null) {
				sendEmail(line[0], line[1].split(" ")[0], "Capability statement for subcontracting");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void sendEmail(String recipients, String agent, String address) {
		final String username = "contact@vizoomi.com";
		final String password = "Contact#P8";

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
			message.setFrom(new InternetAddress("contact@vizoomi.com", "Vizoomi"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
			message.setSubject(address);

			// creates body part for the message
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(
					"<div style=\"font-size:16px\">Dear " + agent
							+ ",<br/><br/>We (Vizoomi LLC) would like to submit a capability statement (please, see attached) for subcontracting. "
							+ "We found your information on the SBA website. We would appreciate some feedback on how to proceed."
							+ "<br/><br/>Thank you<br/>Rasaq Otunba<br/>vizoomi.tech<br/>(571)315-0790</div>",
					"text/html; charset=utf-8");

			// creates multi-part
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			MimeBodyPart attachPart = new MimeBodyPart();
			String attachFile = "/Users/rotunb200/Dropbox/vizoomi/Capability_statement.pdf";
			try {
				attachPart.attachFile(attachFile);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			multipart.addBodyPart(attachPart);
			// sets the multipart as message's content
			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Done " + recipients);

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}