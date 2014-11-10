package com.spirit.general.mdb.messages.bo;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.spirit.general.mdb.messages.object.ObjectMessengerLocal;

@Local
public interface ClienteMessageLocal extends ObjectMessengerLocal{

	public void setMessageDataProcess(Object clienteIf, List modelClienteZonaList,
			List modelClienteOficinaList, Map modelClienteContactoMap,
			Map modelClienteIndicadorMap);

	public void setMessageDataUpdate(Object clienteIf,
			List modelClienteZonaList, List modelClienteOficinaList,
			Map modelClienteContactoMap, Map modelClienteIndicadorMap,
			List detalleZonaRemovidaClienteList,
			List detalleOficinaRemovidaClienteList,
			Map detalleContactoRemovidoClienteList,
			Map detalleIndicadorRemovidoClienteList);

	public void setMessageDataDelete(String idCliente);
	
	public Object getClienteIf();

	public List getModelClienteZonaList();

	public List getModelClienteOficinaList();

	public Map getModelClienteContactoMap();

	public Map getModelClienteIndicadorMap();

	public List getDetalleZonaRemovidaClienteList();

	public List getDetalleOficinaRemovidaClienteList();

	public Map getDetalleContactoRemovidoClienteList();

	public Map getDetalleIndicadorRemovidoClienteList();

	public String getIdClienteEliminar();
}
