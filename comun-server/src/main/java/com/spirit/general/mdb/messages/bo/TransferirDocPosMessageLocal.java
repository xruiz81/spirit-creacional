package com.spirit.general.mdb.messages.bo;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ejb.Local;

import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.mdb.messages.object.ObjectMessengerLocal;

@Local
public interface TransferirDocPosMessageLocal extends ObjectMessengerLocal {

	public CarteraIf getComprobanteOriginal();

	public void setComprobanteOriginal(CarteraIf comprobanteOriginal);

	public Map<String, Object> getParametrosEmpresa();

	public void setParametrosEmpresa(Map<String, Object> parametrosEmpresa);

	public OficinaIf getOficinaOrigen();

	public void setOficinaOrigen(OficinaIf oficinaOrigen);

	public OficinaIf getOficinaDestino();

	public void setOficinaDestino(OficinaIf oficinaDestino);

	
}
