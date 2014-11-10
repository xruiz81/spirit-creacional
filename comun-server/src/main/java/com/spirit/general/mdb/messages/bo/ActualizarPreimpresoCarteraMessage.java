package com.spirit.general.mdb.messages.bo;

import javax.ejb.Stateless;

import com.spirit.general.mdb.messages.object.ObjectMessenger;

@Stateless
public class ActualizarPreimpresoCarteraMessage extends ObjectMessenger implements ActualizarPreimpresoCarteraMessageLocal {

	Long idOficina;
	Long idCarteraOrigen;
	String preImpreso;
	Long idTipoDocumento;

	public void setData(Long idOficina, Long idCarteraOrigen, String preImpreso, Long idTipoDocumento) {
		this.idOficina = idOficina;
		this.idCarteraOrigen = idCarteraOrigen;
		this.preImpreso = preImpreso;
		this.idTipoDocumento = idTipoDocumento;
	}

	public Long getIdOficina() {
		return idOficina;
	}

	public Long getIdCarteraOrigen() {
		return idCarteraOrigen;
	}

	public String getPreImpreso() {
		return preImpreso;
	}

	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}
}
