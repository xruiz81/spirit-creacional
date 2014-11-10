package com.spirit.compras.handler;

import java.util.List;

import javax.ejb.Local;

import com.spirit.compras.entity.CompraDetalleGastoIf;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.UsuarioIf;

@Local
public interface GastoAsientoAutomaticoHandlerLocal {

	public AsientoIf generarAsientoAutomatico(CompraIf compra, CompraDetalleGastoIf compraDetalleGasto, CompraDetalleIf compraDetalle, DocumentoIf documentoCompraDetalle, DocumentoIf documentoGastoImportacion, boolean procesarAsiento, UsuarioIf usuario, int etapa)
	throws GenericBusinessException;
	
	//public void procesarCompraAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento);
	
	public void setAsientoDetalleColeccion(
			List<AsientoDetalleIf> asientoDetalleColeccion);
	
}
