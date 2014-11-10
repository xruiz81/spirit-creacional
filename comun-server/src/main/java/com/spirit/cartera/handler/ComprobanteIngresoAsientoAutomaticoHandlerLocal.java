package com.spirit.cartera.handler;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.exception.GenericBusinessException;

@Local
public interface ComprobanteIngresoAsientoAutomaticoHandlerLocal {
	
	public AsientoIf generarComprobanteIngresoAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento, Map<String,Object> parametrosEmpresa, Long etapa)  throws GenericBusinessException;

	public void setAsientoDetalleColeccion(List<AsientoDetalleIf> asientoDetalleColeccion);
	
}
