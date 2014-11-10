package com.spirit.cartera.handler;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.exception.GenericBusinessException;

@Local
public interface AnticipoCompraReembolsoAsientoAutomaticoHandlerLocal {
	public AsientoIf generarAsientoContable()  throws GenericBusinessException;
	public AsientoIf registrarAsiento();
	//public List<AsientoDetalleIf> agruparDetalles(List<AsientoDetalleIf> asientoDetalleColeccion);
	public boolean validateAsientoAutomatico(Long empresaId, java.sql.Date fechaAsiento);
	public void setAsientoDetalleColeccion(List<AsientoDetalleIf> asientoDetalleColeccion);
	public AsientoIf procesarAsientoAutomatico(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento)  throws GenericBusinessException;
}
