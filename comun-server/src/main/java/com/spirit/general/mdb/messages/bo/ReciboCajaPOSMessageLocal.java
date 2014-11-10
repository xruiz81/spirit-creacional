package com.spirit.general.mdb.messages.bo;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ejb.Local;

import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.general.mdb.messages.object.ObjectMessengerLocal;

@Local
public interface ReciboCajaPOSMessageLocal extends ObjectMessengerLocal {

	public CarteraIf getCartera();
	public void setCartera(CarteraIf cartera);
	public String getPreimpreso();
	public void setPreimpreso(String preimpreso);
	public Vector<Vector> getDetallesPagos();
	public void setDetallesPagos(Vector<Vector> detallesPagos);
	public Map<String, Object> getParametros();
	public void setParametros(Map<String, Object> parametros);

	
}
