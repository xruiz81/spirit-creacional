package com.spirit.general.mdb.messages.bo;

import javax.ejb.Local;

import com.spirit.general.mdb.messages.object.ObjectMessengerLocal;

@Local
public interface ActualizarPreimpresoMessageLocal extends ObjectMessengerLocal{

	public void setData(Long idOficina, Long idFacturaOrigen, String preImpreso, boolean propagarMensaje);

	public Long getIdOficina();

	public Long getIdFacturaOrigen();

	public String getPreImpreso();
	
	public boolean getPropagarMensaje();

}
