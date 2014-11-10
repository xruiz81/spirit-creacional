package com.spirit.general.mdb.consumer;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jms.Message;

@Local
public interface ConsumerListenerLocal {
	public void procesarMensage(Message message);
}
