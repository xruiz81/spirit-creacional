package com.spirit.server;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
/*import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;*/

public class MailerSend {
	
	/*
	public static void enviarEmailRolesAdjuntoVarios(String emailTo,String pdfNombre,String from,String clave)
    {    try
        {
            // Propiedades de la conexión
            Properties props = new Properties();//mail
            props.clear();
            props.setProperty("mail.smtp.host", "mail.creacional.com");
            props.setProperty("mail.smtp.auth", "FALSE");          
            props.setProperty("mail.smtp.port", "25");            
            props.setProperty("mail.smtp.user", from);
            props.setProperty("mail.smtp.pass", clave);
            props.setProperty("mail.smtps.password", clave);
          
             // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);
        
         // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setText("Texto del mensaje");

            // Se compone el adjunto con la imagen
            BodyPart adjunto = new MimeBodyPart();            //
            adjunto.setDataHandler(new DataHandler(new FileDataSource("c:\\rolesEnviadosMail\\"+pdfNombre+".pdf")));
            adjunto.setFileName(pdfNombre+".pdf");
            adjunto.setHeader( "Content-Type", "application/pdf;" );
            //adjunto.setHeader( "Content-Transfer-Encoding", "base64;");
            
            
            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
           

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailTo));
			message.setSubject("Envio de Rol por mail");
			message.setContent(multiParte);
			
			// Lo enviamos.
			Transport t = session.getTransport("smtp");//t.connect("sistemas@creacional.com","sistemas");
			t.connect();
			t.sendMessage(message, message.getAllRecipients());
			// Cierre.
			t.close();
            
        }
        catch (Exception e)
        {
            e.printStackTrace();            
        }
    } */
	

}
