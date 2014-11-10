package com.spirit.general.mdb.messages;

import javax.ejb.Local;

@Local
public interface GenericMessengerLocal {
	public String getSource();

	public void setSource(String source);
	
	public void sendToPrincipal() throws Exception ;

	public void sendToPos(Long oficinaId) throws Exception;

	public void sendToAll() throws Exception ;

	public String getDestiny();

	public void setDestiny(String destiny);

	public boolean isReenviar();

	public void setReenviar(boolean reenviar);
	
	public void clear();
	
	public String getOperacion();

	public void setOperacion(String operacion);
	
	public String getSourceType();

	public void setSourceType(String sourceType);
	
	public void sendToPrincipalIfPosElseResto() throws Exception ;
	
	public void sendToPrincipalIfPos() throws Exception ;
	
	public void sendToPosIfPrincipal(Long oficinaId) throws Exception;
	
}
