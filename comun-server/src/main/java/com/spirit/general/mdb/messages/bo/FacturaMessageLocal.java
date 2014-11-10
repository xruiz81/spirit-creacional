package com.spirit.general.mdb.messages.bo;

import javax.ejb.Local;

import com.spirit.general.mdb.messages.object.MasterDetailObjectMessageLocal;

@Local
public interface FacturaMessageLocal extends MasterDetailObjectMessageLocal{
	public Long getIdEmpresa();

	public void setIdEmpresa(Long idEmpresa);
	
	public Object getFactura();

	public void setFactura(Object factura);
	
	public Long getIdOficina();

	public void setIdOficina(Long idOficina);

	public Object getUsuario();

	public void setUsuario(Object usuario);
	
	public Object getCliente();

	public void setCliente(Object cliente);
	
	public Object getCienteOficina();

	public void setCienteOficina(Object cienteOficina);
}
