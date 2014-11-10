package com.spirit.general.mdb.producer;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import com.spirit.client.Parametros;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.mdb.ServiceLocator;
import com.spirit.general.mdb.messages.GenericMessenger;
import com.spirit.general.mdb.server.QueryIf;
import com.spirit.poscola.entity.PosColaIf;

//TODO: reference jboss at work
/**
 * <code>JmsProducer</code> encapsulates sending a JMS Message.
 * 
 */

@Stateless
public class JmsProducer implements JmsProducerLocal {
	@EJB
	private QueryIf queryLocal;

	private PosColaIf yo = null;

	public void sendMessageToPrincipal(GenericMessenger payload)
			throws Exception {
		PosColaIf posCola = queryLocal.obtenerInfoColaPrincipal();
		sendMessageTo(payload, posCola);
	}

	public void sendMessageToPos(GenericMessenger payload, Long oficinaId)
			throws Exception {
		PosColaIf posCola = queryLocal.obtenerInfoPosColaByOficinaId(oficinaId);
		sendMessageTo(payload, posCola);
	}

	public void sendMessageToAllMenosPrincipalYoParametro(
			GenericMessenger payload, String posName) throws Exception {
		List<PosColaIf> lista = queryLocal
				.findTodosMenosPrincipalYoParametro(posName);
		for (PosColaIf posCola : lista) {
			sendMessageTo(payload, posCola);
		}
	}

	public void sendMessageToAllPos(GenericMessenger payload) throws Exception {
		List<PosColaIf> lista = queryLocal.obtenerAllPosCola();
		for (PosColaIf posCola : lista) {
			if (!posCola.getTipoServer().equalsIgnoreCase("P")) {
				sendMessageTo(payload, posCola);
			}
		}
	}

	public void sendToPrincipalIfPosElseResto(GenericMessenger payload)
			throws Exception {
		setYo();
		if (yo.getTipoServer().equalsIgnoreCase("P")) {
			sendMessageToAllPos(payload);
		} else {
			sendMessageToPrincipal(payload);
		}
	}

	public void sendToPosIfPrincipal(GenericMessenger payload, Long oficinaId)
			throws Exception {
		setYo();
		if (yo.getTipoServer().equalsIgnoreCase("P")
				&& yo.getOficinaId().compareTo(oficinaId) != 0) {
			sendMessageToPos(payload, oficinaId);
		} else {

		}
	}

	public void sendToPrincipalIfPos(GenericMessenger payload) throws Exception {
		setYo();
		if (yo.getTipoServer().equalsIgnoreCase("P")) {
			// NO ENVIO NADA
		} else {
			sendMessageToPrincipal(payload);
		}

	}

	private void sendMessageTo(GenericMessenger payload, PosColaIf posCola)
			throws Exception {
		if (posCola != null) {
			setYo();
			System.out.println("Mensaje para host:" + posCola.getHostName());
			System.out
					.println("Factory local del host:" + posCola.getFactory());
			System.out.println("Context:" + yo.getDireccionIp() + ":"
					+ yo.getPort());
			payload.setSource(yo.getPosName());
			payload.setSourceType(yo.getTipoServer());
			payload.setDestiny(posCola.getHostName());
			sendMessage(payload, posCola.getFactory(), posCola.getQname(), yo
					.getDireccionIp(), yo.getPort());
		} else {
			System.out.println("REGISTRO DE POS NO ENCONTRADO");
		}
	}

	private void setYo() throws GenericBusinessException {
		if (yo == null)
			yo = queryLocal.obtenerInfoColaYO();
	}

	synchronized public void sendMessage(Serializable payload,
			String connectionFactoryJndiName, String destinationJndiName,
			String ip, String port) throws Exception {
		if (Parametros.standAlone || !Parametros.ACTIVAR_REPLICACION) {
			System.out.println("Mensajeria desactivada..");
			return;
		}

		QueueConnectionFactory connectionFactory = null;
		QueueConnection connection = null;
		QueueSession session = null;
		ObjectMessage message = null;
		QueueSender enviar = null;

		System.out.println("obteniendo ConnectionFactory para jndi: "
				+ connectionFactoryJndiName);
		connectionFactory = (QueueConnectionFactory) ServiceLocator.getContext(
				ip, port).lookup(connectionFactoryJndiName);
		Queue cola = (Queue) ServiceLocator.getContext(ip, port).lookup(
				destinationJndiName);
		connection = connectionFactory.createQueueConnection();
		session = connection
				.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

		enviar = session.createSender(cola);

		message = session.createObjectMessage(payload);

		enviar.send(message, javax.jms.DeliveryMode.PERSISTENT,
				javax.jms.Message.DEFAULT_PRIORITY, 0);

		System.out.println("Mensaje Enviado con exito a: "
				+ destinationJndiName + " " + message.getJMSPriority());
		enviar.close();
		session.close();
		connection.close();
	}

	private static String buildString(int numMensaje) {
		String cadena = "";
		for (int i = 0; i < 200; i++) {
			cadena += numMensaje;
		}
		return cadena;
	}

	public static void main(String[] args) throws Exception {
		JmsProducer jmsProducer = new JmsProducer();

		/*
		 * jmsProducer.sendMessage(buildString(1), "java:/ConnectionFactory",
		 * "queue/cola-central", "192.168.100.147", "1099");
		 */

		jmsProducer.sendMessage("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
				+ "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
				+ "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
				+ "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXX",
				"java:/ConnectionFactory", "queue/cola-remote-mall",
				"192.168.100.147", "1099");

	}
}