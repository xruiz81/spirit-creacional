package com.spirit.compras.handler;

import java.util.List;

import javax.ejb.Local;

import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.exception.GenericBusinessException;

@Local
public interface CompraReembolsoAsientoAutomaticoHandlerLocal {

	public AsientoIf generarAsientoAutomatico(CompraIf compra, CompraDetalleIf compraDetalle, boolean procesarAsiento,Long tipoDocumentoIfId)
	throws GenericBusinessException;
	
	//public void procesarCompraReembolsoAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento);
	
	public void setAsientoDetalleColeccion(
			List<AsientoDetalleIf> asientoDetalleColeccion);
	
}
