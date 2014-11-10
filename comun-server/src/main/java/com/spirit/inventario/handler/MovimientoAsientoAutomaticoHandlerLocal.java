package com.spirit.inventario.handler;

import java.util.List;

import javax.ejb.Local;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.inventario.entity.MovimientoDetalleIf;
import com.spirit.inventario.entity.MovimientoIf;

@Local
public interface MovimientoAsientoAutomaticoHandlerLocal {

	public AsientoIf generarAsientoAutomatico(MovimientoIf movimiento, MovimientoDetalleIf movimientoDetalle, MovimientoIf movimientoAnterior, boolean procesarAsiento, boolean actualizar, UsuarioIf usuario)
	throws GenericBusinessException;
	
	//public void procesarCompraAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento);
	
	public void setAsientoDetalleColeccion(
			List<AsientoDetalleIf> asientoDetalleColeccion);
	
}
