package com.spirit.general.mdb.messages.bo;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import com.spirit.general.mdb.messages.object.ObjectMessenger;

@Stateless
public class ClienteMessage extends ObjectMessenger implements
		ClienteMessageLocal {

	private Object clienteIf;
	private List modelClienteZonaList;
	private List modelClienteRetencionList;
	private List modelClienteOficinaList;
	private Map modelClienteContactoMap;
	private Map modelClienteIndicadorMap;
	private List detalleZonaRemovidaClienteList;
	private List detalleRetencionRemovidaClienteList;
	private List detalleOficinaRemovidaClienteList;
	private Map detalleContactoRemovidoClienteList;
	private Map detalleIndicadorRemovidoClienteList;
	private String identificacionClienteEliminar;

	public void setMessageDataProcess(Object clienteIf,
			List modelClienteZonaList, List modelClienteRetencionList, 
			List modelClienteOficinaList,
			Map modelClienteContactoMap, Map modelClienteIndicadorMap) {
		this.clienteIf = clienteIf;
		this.modelClienteContactoMap = modelClienteContactoMap;
		this.modelClienteIndicadorMap = modelClienteIndicadorMap;
		this.modelClienteOficinaList = modelClienteOficinaList;
		this.modelClienteZonaList = modelClienteZonaList;
		this.modelClienteRetencionList = modelClienteRetencionList;
		this.setOperacion("I");
		this.setReenviar(true);
	}

	public void setMessageDataUpdate(Object clienteIf,
			List modelClienteZonaList, List modelClienteRetencionList,
			List modelClienteOficinaList,
			Map modelClienteContactoMap, Map modelClienteIndicadorMap,
			List detalleZonaRemovidaClienteList,
			List detalleRetencionRemovidaClienteList,
			List detalleOficinaRemovidaClienteList,
			Map detalleContactoRemovidoClienteList,
			Map detalleIndicadorRemovidoClienteList) {
		this.clienteIf = clienteIf;
		this.modelClienteContactoMap = modelClienteContactoMap;
		this.modelClienteIndicadorMap = modelClienteIndicadorMap;
		this.modelClienteOficinaList = modelClienteOficinaList;
		this.modelClienteZonaList = modelClienteZonaList;
		this.modelClienteRetencionList = modelClienteRetencionList;
		this.detalleZonaRemovidaClienteList = detalleZonaRemovidaClienteList;
		this.detalleRetencionRemovidaClienteList = detalleRetencionRemovidaClienteList;
		this.detalleOficinaRemovidaClienteList = detalleOficinaRemovidaClienteList;
		this.detalleContactoRemovidoClienteList = detalleContactoRemovidoClienteList;
		this.detalleIndicadorRemovidoClienteList = detalleIndicadorRemovidoClienteList;
		this.setOperacion("U");
	}

	public List getDetalleRetencionRemovidaClienteList() {
		return detalleRetencionRemovidaClienteList;
	}

	public void setMessageDataDelete(String idCliente) {
		this.identificacionClienteEliminar = idCliente;
		this.setOperacion("E");
	}

	public List getModelClienteRetencionList() {
		return modelClienteRetencionList;
	}

	public Object getClienteIf() {
		return clienteIf;
	}

	public List getModelClienteZonaList() {
		return modelClienteZonaList;
	}

	public List getModelClienteOficinaList() {
		return modelClienteOficinaList;
	}

	public Map getModelClienteContactoMap() {
		return modelClienteContactoMap;
	}

	public Map getModelClienteIndicadorMap() {
		return modelClienteIndicadorMap;
	}

	public List getDetalleZonaRemovidaClienteList() {
		return detalleZonaRemovidaClienteList;
	}

	public List getDetalleOficinaRemovidaClienteList() {
		return detalleOficinaRemovidaClienteList;
	}

	public Map getDetalleContactoRemovidoClienteList() {
		return detalleContactoRemovidoClienteList;
	}

	public Map getDetalleIndicadorRemovidoClienteList() {
		return detalleIndicadorRemovidoClienteList;
	}

	public String getIdClienteEliminar() {
		return identificacionClienteEliminar;
	}

	@Override
	public void setMessageDataProcess(Object clienteIf,
			List modelClienteZonaList, List modelClienteOficinaList,
			Map modelClienteContactoMap, Map modelClienteIndicadorMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMessageDataUpdate(Object clienteIf,
			List modelClienteZonaList, List modelClienteOficinaList,
			Map modelClienteContactoMap, Map modelClienteIndicadorMap,
			List detalleZonaRemovidaClienteList,
			List detalleOficinaRemovidaClienteList,
			Map detalleContactoRemovidoClienteList,
			Map detalleIndicadorRemovidoClienteList) {
		// TODO Auto-generated method stub
		
	}
}
