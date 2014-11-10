package com.spirit.general.mdb.messages.bo;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.ejb.Stateless;

import com.spirit.general.mdb.messages.object.ObjectMessenger;

@Stateless
public class AsientoPosMessage extends ObjectMessenger implements
		AsientoPosMessageLocal {

	HashMap<String, BigDecimal> mapaAsientos;
	Long idEmpresa;
	Long idOficina;
	String cajero;

	public void setData(HashMap<String, BigDecimal> mapaAsientos,
			Long idEmpresa, Long idOficina, String cajero) {
		this.mapaAsientos = mapaAsientos;
		this.idEmpresa = idEmpresa;
		this.idOficina = idOficina;
		this.cajero = cajero;
	}

	public HashMap<String, BigDecimal> getMapaAsientos() {
		return mapaAsientos;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public Long getIdOficina() {
		return idOficina;
	}

	public String getCajero() {
		return cajero;
	}

}
