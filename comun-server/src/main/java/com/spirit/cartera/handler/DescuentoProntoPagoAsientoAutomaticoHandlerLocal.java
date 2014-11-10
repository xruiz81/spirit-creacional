package com.spirit.cartera.handler;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoDocumentoIf;

@Local
public interface DescuentoProntoPagoAsientoAutomaticoHandlerLocal {

	public AsientoIf generarDescuentoProntoPagoAsientoAutomaticoHandler(EventoContableIf eventoContable, Map parameterMap, TipoDocumentoIf tipoDocumento, String tipoReferencia, boolean procesarAsiento, Map<String,Object> parametrosEmpresa) throws GenericBusinessException;
	
	public void setAsientoDetalleColeccion(List<AsientoDetalleIf> asientoDetalleColeccion);
	
}
