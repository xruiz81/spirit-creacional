package com.spirit.general.mdb.messages.bo;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ejb.Stateless;

import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.mdb.messages.object.ObjectMessenger;

@Stateless
public class TransferirDocPosMessage extends ObjectMessenger implements TransferirDocPosMessageLocal{
	private CarteraIf comprobanteOriginal;
	private Map<String,Object> parametrosEmpresa; 
	private OficinaIf oficinaOrigen; 
	private OficinaIf oficinaDestino;
	public CarteraIf getComprobanteOriginal() {
		return comprobanteOriginal;
	}
	public void setComprobanteOriginal(CarteraIf comprobanteOriginal) {
		this.comprobanteOriginal = comprobanteOriginal;
	}
	public Map<String, Object> getParametrosEmpresa() {
		return parametrosEmpresa;
	}
	public void setParametrosEmpresa(Map<String, Object> parametrosEmpresa) {
		this.parametrosEmpresa = parametrosEmpresa;
	}
	public OficinaIf getOficinaOrigen() {
		return oficinaOrigen;
	}
	public void setOficinaOrigen(OficinaIf oficinaOrigen) {
		this.oficinaOrigen = oficinaOrigen;
	}
	public OficinaIf getOficinaDestino() {
		return oficinaDestino;
	}
	public void setOficinaDestino(OficinaIf oficinaDestino) {
		this.oficinaDestino = oficinaDestino;
	}
}
