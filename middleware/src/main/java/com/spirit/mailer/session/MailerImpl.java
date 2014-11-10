package com.spirit.mailer.session;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.jboss.logging.Logger;

@Stateless
public class MailerImpl implements MailerLocal, MailerRemote {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource(mappedName = "java:/Mail")
	private Session mailSession;

	@Resource(mappedName = "java:/XAConnectionFactory")
	private QueueConnectionFactory connectionFactory;

	@Resource(mappedName = "queue/cola-mail")
	private Queue colaMail;

	private void lanzarExcepcion(String mensaje) throws MailerException {
		log.error(mensaje);
		throw new MailerException(mensaje);
	}

	private void lanzarExcepcion(Exception e, String mensaje)
			throws MailerException {
		log.error(mensaje, e);
		throw new MailerException(mensaje);
	}

	private void validateMailObject(MailMessage mailObject)
			throws MailerException {
		if (mailObject == null) {
			lanzarExcepcion("MailObject no puede ser null");
		} else if (mailObject.getSubject() == null) {
			lanzarExcepcion("Debe especificar el subject");
		} else if (mailObject.getTo() == null) {
			lanzarExcepcion("Debe especificar las direcciones de destino");
		}
	}

	private void sendMail(MimeMessage message) throws MailerException {
		Transport transport = null;
		try {
			transport = mailSession.getTransport("smtp");
		} catch (Exception e1) {
			lanzarExcepcion(e1, "Error al obtener capa de transporte");
		}
		Properties props = mailSession.getProperties();
		String hostName = (String) props.get("mail.smtp.host");
		int port = (Integer) props.get("mail.smtp.port");
		String username = (String) props.get("mail.smtp.user");
		String password = (String) props.get("mail.smtp.pass");
		try {
			if (!transport.isConnected())
				transport.connect(hostName, port, username, password);
			log.info("ENVIANDO MAIL: " + message.getSubject());
			transport.sendMessage(message, message.getAllRecipients());
			log.info("MAIL ENVIADO CON EXITO");
		} catch (Exception e) {
			lanzarExcepcion(e, "Error al enviar mail en capa de transporte");
		}
	}

	private MimeBodyPart createAttachment(Multipart multiPart,
			byte[] archivoAttach, String fileName) throws MailerException {
		try {
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			if (archivoAttach != null) {
				File f = File.createTempFile("tmp", "");
				FileOutputStream fileOutputStream = new FileOutputStream(f);
				fileOutputStream.write(archivoAttach);
				FileDataSource fds = new FileDataSource(f);
				mimeBodyPart.setText("");
				mimeBodyPart.setFileName(fileName);
				mimeBodyPart.setDataHandler(new DataHandler(fds));
				multiPart.addBodyPart(mimeBodyPart);
				f.deleteOnExit();
				fileOutputStream.close();
			}
			return mimeBodyPart;
		} catch (Exception e) {
			lanzarExcepcion(e, "Error al crear attachment.. " + e.getMessage());
			return null;
		}
	}

	private String parseToAdress(String[] direcciones) throws MailerException {
		String returnString = "";
		for (String s : direcciones) {
			s = s.trim();
			if (s.equalsIgnoreCase("")) {
				continue;
			}
			try {
				InternetAddress.parse(s, true);
			} catch (AddressException e) {
				lanzarExcepcion("Direccion: " + s + " no es valida");
			}
			returnString += s + ",";
		}
		return returnString.substring(0, returnString.length() - 1);
	}

	@Override
	public void sendMailMessage(MailMessage mailObject) throws MailerException {
		validateMailObject(mailObject);
		log.info("ENVIANDO MAIL: " + mailObject.getSubject());
		MimeMessage message = new MimeMessage(mailSession);
		try {
			message.setFrom(new InternetAddress(mailObject.getFrom()));
			message.setSubject(mailObject.getSubject().toUpperCase());
			message.setRecipients(javax.mail.Message.RecipientType.TO,
					parseToAdress(mailObject.getTo()));

			byte[][] listaArchivos = mailObject.getAttachments();
			if (listaArchivos != null && listaArchivos.length > 0) {
				Multipart multiPart = new MimeMultipart();
				MimeBodyPart mimeBodyPart = new MimeBodyPart();
				mimeBodyPart.setText(mailObject.getBody());
				multiPart.addBodyPart(mimeBodyPart);
				int i = 0;
				for (byte[] f : listaArchivos) {
					createAttachment(multiPart, f,
							mailObject.getFileNames()[i++]);
				}
				message.setContent(multiPart);
			} else {
				try {
					// message.setText(mailObject.getBody());
					message.setDataHandler(new DataHandler(
							new ByteArrayDataSource(mailObject.getBody(),
									"text/html")));
				} catch (Exception e) {
					lanzarExcepcion("Error al constriur body: ");
				}
			}

			sendMail(message);

		} catch (MessagingException e) {
			lanzarExcepcion(e, "Error al constriur mail: " + e.getMessage());
		}
	}

	public void sendMailToQueue(MailMessage mailMessage) throws MailerException {
		validateMailObject(mailMessage);
		log.info("ENCOLANDO MAIL: " + mailMessage.getSubject());
		QueueConnection con = null;
		QueueSession ses = null;
		QueueSender sender = null;
		try {
			log.info("OBTENIENDO RECURSOS");
			con = connectionFactory.createQueueConnection();
			log.info("QueueConnection " + con);
			ses = con.createQueueSession(false,
					javax.jms.Session.AUTO_ACKNOWLEDGE);
			log.info("QueueSession " + ses);
			sender = ses.createSender(colaMail);
			log.info("QueueSender " + sender);
			ObjectMessage msg = ses.createObjectMessage(mailMessage);
			log.info("ObjectMessage " + msg);
			sender.send(msg, javax.jms.DeliveryMode.PERSISTENT,
					javax.jms.Message.DEFAULT_PRIORITY, 1800000);
			log.info("MENSAJE ENCOLADO CON EXITO");
		} catch (JMSException e) {
			log.error(e);
			throw new RuntimeException(e);
		} finally {
			log.error("CERRANDO RECURSOS");
			close(sender, ses, con);
		}
		log.info("MENSAJE ENCOLADO CON EXITO");
	}

	private static void close(MessageProducer sender,
			javax.jms.Session session, Connection conn) {
		if (sender != null) {
			// noinspection EmptyCatchBlock
			try {
				sender.close();
			} catch (Exception ignore) {

			}
		}
		if (session != null) {
			// noinspection EmptyCatchBlock
			try {
				session.close();
			} catch (Exception ignore) {
			}
		}
		if (conn != null) {
			// noinspection EmptyCatchBlock
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}
	}

	private static String printStrackTrace(Throwable e) {
		String txtTmp = "";
		for (StackTraceElement stackTraceElement : e.getStackTrace()) {
			txtTmp += stackTraceElement.toString() + "\n";
		}
		return txtTmp;
	}

	@Override
	public void mailErrorToAdmin(String descripcionCorta, Exception e)
			throws MailerException {
		MailMessage mailMessage = new MailMessage();
		mailMessage.setSubject("ERROR EN EL SISTEMA: " + descripcionCorta);
		mailMessage.setBody(printStrackTrace(e));
		mailMessage.setTo("versality.spirit@gmail.com");// TODO: Replace
		// con
		// parametros..
		sendMailMessage(mailMessage);
	}

	public Session getMailSession() {
		return mailSession;
	}

	public void setMailSession(Session mailSession) {
		this.mailSession = mailSession;
	}
	
	public static Session createMailSession(Map<String, Object> smtpParametersMap) {
		//Authenticator authenticator = new Authenticator((String) smtpParametersMap.get("SMTP_AUTH_USER"), (String) smtpParametersMap.get("SMTP_AUTH_PWD"));
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		//props.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
		props.put("mail.smtp.host", (String) smtpParametersMap.get("SMTP_HOST_NAME"));
		props.put("mail.smtp.port", Integer.parseInt((String) smtpParametersMap.get("SMTP_HOST_PORT")));
		props.put("mail.smtp.user", (String) smtpParametersMap.get("SMTP_AUTH_USER"));
		props.put("mail.smtp.pass", (String) smtpParametersMap.get("SMTP_AUTH_PWD"));
		props.put("mail.smtp.auth", "false");
		//Authenticator auth = new SMTPAuthenticator("rrhh@creacional.com", "Rrhh.2012");
		//Session mailSession = Session.getDefaultInstance(props, auth);
		Session mailSession = Session.getDefaultInstance(props);
		mailSession.setDebug(true);
		return mailSession;
	}
	
	private static class Authenticator extends javax.mail.Authenticator {
		private PasswordAuthentication authentication;

		public Authenticator(String authuser, String authpassword) {
			authentication = new PasswordAuthentication(authuser, authpassword);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}
}
