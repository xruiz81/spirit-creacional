package com.spirit.general.mdb.consumer.all;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.spirit.general.mdb.consumer.ConsumerListenerLocal;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/cola-pos-destino"),
        @ActivationConfigProperty(propertyName="maxSession",propertyValue="1"),
        @ActivationConfigProperty(propertyName="maxMessages",propertyValue="1")
})
public class ListenerPosDestino implements MessageListener {

	@EJB
	private ConsumerListenerLocal consumerListenerLocal;
	
	public void onMessage(Message message) {
		System.out.println("--------->ListenerPosDestino "+message.getClass());
		consumerListenerLocal.procesarMensage(message);
	}

}