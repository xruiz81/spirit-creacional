package com.spirit.facturacion.handler;


import java.util.List;

import javax.ejb.Local;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.entity.UsuarioIf;

@Local
public interface FacturaAsientoAutomaticoHandlerLocal {
	public AsientoIf generarAsientoAutomatico(FacturaIf factura, FacturaDetalleIf facturaDetalle, Long tipoDocumentoAnteriorId, boolean procesarAsiento, boolean actualizar, UsuarioIf usuario, String tipoReferencia, boolean contabilizarDescuento) throws GenericBusinessException;
	public void setAsientoDetalleColeccion(List<AsientoDetalleIf> asientoDetalleColeccion);
}
