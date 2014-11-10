package com.spirit.general.mdb.messages;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.spirit.general.mdb.producer.JmsProducerLocal;

@Stateless
public abstract class GenericMessenger implements Serializable,GenericMessengerLocal {
	@EJB
	private JmsProducerLocal jmsProducerLocal; 
	
	private String source;
	private String sourceType;
	private String destiny;
	private boolean reenviar;
	private String operacion;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "" + this.source;
	}

	public void sendToPrincipal() throws Exception {
		jmsProducerLocal.sendMessageToPrincipal(this);
		clear();
	}

	public void sendToPos(Long oficinaId) throws Exception {
		jmsProducerLocal.sendMessageToPos(this, oficinaId);
		clear();
	}

	public void sendToAll() throws Exception {
		jmsProducerLocal.sendMessageToAllPos(this);
		clear();
	}
	
	public void sendToPrincipalIfPosElseResto() throws Exception
	{
		jmsProducerLocal.sendToPrincipalIfPosElseResto(this);
		clear();
	}
	
	public void sendToPrincipalIfPos() throws Exception 
	{
		jmsProducerLocal.sendToPrincipalIfPos(this);
		clear();
	}
	
	public void sendToPosIfPrincipal(Long oficinaId) throws Exception
	{
		jmsProducerLocal.sendToPosIfPrincipal(this,oficinaId);
		clear();
	}

	public String getDestiny() {
		return destiny;
	}

	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

	public boolean isReenviar() {
		return reenviar;
	}

	public void setReenviar(boolean reenviar) {
		this.reenviar = reenviar;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	

}
