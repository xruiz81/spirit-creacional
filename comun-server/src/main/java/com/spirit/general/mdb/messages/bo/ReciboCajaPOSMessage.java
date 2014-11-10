package com.spirit.general.mdb.messages.bo;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ejb.Stateless;

import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.general.mdb.messages.object.ObjectMessenger;

@Stateless
public class ReciboCajaPOSMessage extends ObjectMessenger implements ReciboCajaPOSMessageLocal{
	private CarteraIf cartera;
	private String preimpreso;
	private Vector<Vector> detallesPagos;
	private Map<String, Object> parametros;
	public CarteraIf getCartera() {
		return cartera;
	}
	public void setCartera(CarteraIf cartera) {
		this.cartera = cartera;
	}
	public String getPreimpreso() {
		return preimpreso;
	}
	public void setPreimpreso(String preimpreso) {
		this.preimpreso = preimpreso;
	}
	public Vector<Vector> getDetallesPagos() {
		return detallesPagos;
	}
	public void setDetallesPagos(Vector<Vector> detallesPagos) {
		this.detallesPagos = detallesPagos;
	}
	public Map<String, Object> getParametros() {
		return parametros;
	}
	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}
	
}
