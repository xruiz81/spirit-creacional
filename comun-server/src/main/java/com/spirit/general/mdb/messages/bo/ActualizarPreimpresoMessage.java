package com.spirit.general.mdb.messages.bo;

import javax.ejb.Stateless;

import com.spirit.general.mdb.messages.object.ObjectMessenger;

@Stateless
public class ActualizarPreimpresoMessage extends ObjectMessenger implements
		ActualizarPreimpresoMessageLocal {

	Long idOficina;
	Long idFacturaOrigen;
	String preImpreso;
	boolean propagarMensaje;

	public void setData(Long idOficina, Long idFacturaOrigen, String preImpreso, boolean propagarMensaje) {
		this.idOficina = idOficina;
		this.idFacturaOrigen = idFacturaOrigen;
		this.preImpreso = preImpreso;
		this.propagarMensaje = propagarMensaje;
	}

	public Long getIdOficina() {
		return idOficina;
	}

	public Long getIdFacturaOrigen() {
		return idFacturaOrigen;
	}

	public String getPreImpreso() {
		return preImpreso;
	}
	
	public boolean getPropagarMensaje() {
		return propagarMensaje;
	}

}
