package com.spirit.general.mdb.producer;

import javax.ejb.Local;

import com.spirit.general.mdb.messages.GenericMessenger;

@Local
public interface JmsProducerLocal {

	public void sendMessageToPrincipal(GenericMessenger payload)
			throws Exception;

	public void sendMessageToPos(GenericMessenger payload, Long oficinaId)
			throws Exception;

	public void sendMessageToAllPos(GenericMessenger payload) throws Exception;

	public void sendMessageToAllMenosPrincipalYoParametro(
			GenericMessenger payload, String posName) throws Exception;

	public void sendToPrincipalIfPosElseResto(GenericMessenger payload)
			throws Exception;

	public void sendToPrincipalIfPos(GenericMessenger payload) throws Exception;
	
	public void sendToPosIfPrincipal(GenericMessenger payload,Long oficinaId) throws Exception;
}
