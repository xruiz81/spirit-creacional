package com.spirit.general.mdb.messages.bo;

import javax.ejb.Local;

import com.spirit.general.mdb.messages.object.ObjectMessengerLocal;

@Local
public interface ActualizarPreimpresoCarteraMessageLocal extends ObjectMessengerLocal{

	public void setData(Long idOficina, Long idCarteraOrigen, String preImpreso, Long idTipoDocumento);

	public Long getIdOficina();

	public Long getIdCarteraOrigen();

	public String getPreImpreso();
	
	public Long getIdTipoDocumento();

}
