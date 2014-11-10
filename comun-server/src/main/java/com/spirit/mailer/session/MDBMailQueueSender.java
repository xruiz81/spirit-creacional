package com.spirit.mailer.session;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.logging.Logger;

/**
 * MDB que lee de la cola de correos y luego los procesa, si hubo algun error
 * envia los mensajes al DLQ
 * 
 * @author Ricardo Andrade
 * 
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/cola-mail"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1"),
		@ActivationConfigProperty(propertyName = "maxMessages", propertyValue = "1") })
public class MDBMailQueueSender implements MessageListener {

	private Logger log = Logger.getLogger(this.getClass());
	@EJB
	private MailerLocal mailerLocal;

	public void onMessage(Message message) {
		log.info("LEYENDO MENSAJE DE COLA queue/cola-mail " + message);
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			MailMessage mailMessage = (MailMessage) objectMessage.getObject();
			log.info("PROCESANDO MAIL EN COLA " + mailMessage.getSubject());
			mailerLocal.sendMailMessage(mailMessage);
		} catch (Exception e) {
			log.error(e);
		}
	}
}