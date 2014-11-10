package com.spirit.cartera.handler;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.exception.GenericBusinessException;

@Local
public interface NotaInternaCreditoClienteAsientoAutomaticoHandlerLocal {
	
	public AsientoIf generarNotaInternaCreditoClienteAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento)  throws GenericBusinessException;

	public void setAsientoDetalleColeccion(List<AsientoDetalleIf> asientoDetalleColeccion);
	
}
