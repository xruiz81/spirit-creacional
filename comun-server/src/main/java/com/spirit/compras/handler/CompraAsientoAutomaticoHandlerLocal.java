package com.spirit.compras.handler;

import java.util.List;

import javax.ejb.Local;

import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;

@Local
public interface CompraAsientoAutomaticoHandlerLocal {
	public AsientoIf generarAsientoAutomatico(CompraIf compra, CompraDetalleIf compraDetalle, CompraIf compraAnterior, boolean procesarAsiento, boolean actualizar, UsuarioIf usuario, ClienteIf clienteAsociado) throws GenericBusinessException;
	public void setAsientoDetalleColeccion(List<AsientoDetalleIf> asientoDetalleColeccion);
	public void setCtaxpag(double ctaxpag);
	public void setIva(double iva);
}
