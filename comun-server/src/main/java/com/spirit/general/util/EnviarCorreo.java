package com.spirit.general.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EnviarCorreo extends Thread  {
	
	private String host, port, from, clave, emailTo, codigo, mensaje, asunto, cuerpo;
	
	public EnviarCorreo(String host, String port, String from, String clave, String emailTo, 
			String codigo, String mensaje, String asunto, String cuerpo){
		this.host = host;
		this.port = port;
		this.from = from;
		this.clave = clave;
		this.emailTo = emailTo;
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
	}
	
	public void run(){
		try
		{
			// Propiedades de la conexión
            Properties props = new Properties();//mail
            props.clear();
            props.setProperty("mail.smtp.host", host); //"110.110.2.102"
            props.setProperty("mail.smtp.auth", "true"); 
            props.setProperty("mail.smtp.port", port); //25          
            props.setProperty("mail.smtp.user", from); 
            props.setProperty("mail.smtp.pass", clave); 
            props.setProperty("mail.smtps.password", clave); 
          
             // Preparamos la sesion
            Authenticator auth = new SMTPAuthenticator(from, clave);
            //Session session = Session.getDefaultInstance(props,auth);
            Session session = Session.getInstance(props,auth);
            session.setDebug(true);
        
            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            //texto.setText(cuerpo + codigo + ".\n\n" + mensaje + "\n\nPor favor para revisarla ingrese al Timetracker.\n\n(Importante: Por favor no responder este e-mail).");
            texto.setText(cuerpo + codigo + ".\n\n" + mensaje + "\n\n(Importante: Por favor no responder este e-mail).");
            
            // Se compone el adjunto con la imagen
            BodyPart adjunto = new MimeBodyPart();            
            //adjunto.setDataHandler(new DataHandler(new FileDataSource("c:\\rolesEnviadosMail\\"+pdfNombre+".pdf")));
            //adjunto.setFileName(pdfNombre+".pdf");
            adjunto.setHeader( "Content-Type", "application/pdf;" );
            //adjunto.setHeader( "Content-Transfer-Encoding", "base64;");
            
            
            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            //multiParte.addBodyPart(adjunto);
           

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from)); //"tecnico@creacional.com"
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailTo)); //"sistemas@creacional.com"
			message.setSubject(asunto + codigo);
			message.setContent(multiParte);
			
			// Lo enviamos.			 
			Transport t = session.getTransport("smtp");
			//t.connect("spirit@creacional.com","Spirit.2012");
			t.connect();
			t.sendMessage(message, message.getAllRecipients());
			// Cierre.
			t.close();
            
        }
        catch (Exception e){
            e.printStackTrace();            
        }
	}
	
	private class SMTPAuthenticator extends javax.mail.Authenticator {
		
		String username = "";
        String password = "";
		
		public SMTPAuthenticator(String username, String password){
			this.username = username;
			this.password = password;
		}
		
        public PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(username, password);
        }
    }
}
