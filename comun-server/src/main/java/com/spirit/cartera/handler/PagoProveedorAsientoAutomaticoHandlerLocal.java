package com.spirit.cartera.handler;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.exception.GenericBusinessException;

@Local
public interface PagoProveedorAsientoAutomaticoHandlerLocal {
	
	public List<AsientoDetalleIf> generarAsientoContable()  throws GenericBusinessException;
	public AsientoIf registrarAsiento();
	public boolean validateAsientoAutomatico(Long idEmpresa, java.sql.Date fechaAsiento);
	public void procesarAsientoAutomatico(EventoContableIf eventoContable, Map parameterMap, boolean procesarAsiento)  throws GenericBusinessException;
}
