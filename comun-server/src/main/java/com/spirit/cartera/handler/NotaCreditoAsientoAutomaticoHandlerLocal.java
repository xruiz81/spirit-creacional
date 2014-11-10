package com.spirit.cartera.handler;

import java.util.List;

import javax.ejb.Local;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.NotaCreditoDetalleIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.exception.GenericBusinessException;

@Local
public interface NotaCreditoAsientoAutomaticoHandlerLocal {
	public AsientoIf generarAsientoAutomatico(NotaCreditoIf notaCredito, NotaCreditoDetalleIf notaCreditoDetalle, boolean procesarAsiento, Long tipoDocumentoId, long etapa, java.sql.Date fechaAplicacion, double valorAplica, String tipoCartera, CarteraIf carteraAfectada) throws GenericBusinessException;
	public void setAsientoDetalleColeccion(List<AsientoDetalleIf> asientoDetalleColeccion);	
}
