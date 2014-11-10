package com.spirit.general.mdb.messages.bo;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.ejb.Local;

import com.spirit.general.mdb.messages.object.ObjectMessengerLocal;

@Local
public interface AsientoPosMessageLocal extends ObjectMessengerLocal{

	public void setData(HashMap<String, BigDecimal> mapaAsientos, Long idEmpresa, Long idOficina, String cajero);

	public HashMap<String, BigDecimal> getMapaAsientos();

	public Long getIdEmpresa();

	public Long getIdOficina();

	public String getCajero();

}
