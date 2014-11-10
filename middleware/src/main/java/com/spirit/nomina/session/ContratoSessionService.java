package com.spirit.nomina.session;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.ContratoRubroIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.handler.DatoGeneralContrato;
import com.spirit.nomina.session.generated._ContratoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ContratoSessionService extends _ContratoSessionService{
	public void procesarContrato(ContratoIf model,Collection<ContratoRubroIf> contratoRubroColleccion) throws GenericBusinessException;
	public void actualizarContrato(ContratoIf model,Collection<ContratoRubroIf>  contratosRubroColeccion, Collection<RubroIf> rubrosContratoRemovidos)throws GenericBusinessException;
	public void eliminarContrato(Long contratoId) throws GenericBusinessException ;
	
	//public Collection findContratoByFechaRolPago(Date fechaRolPago) throws GenericBusinessException;
	public Collection findContratoByFechaRolPagoByTipoContratoIdByEstado(Date fechaInicioRolPago,Date fechaFinRolPago,Long tipoContratoId,String estado) throws GenericBusinessException;
	//public Collection findContratoDecimosByAnio(int anio) throws GenericBusinessException;
	//public Collection findContratoByFechaRolPagoByEmpleadoId(Date fechaRolPago,Long empleadoId) throws GenericBusinessException;
	//public Collection findContratoByFechaRolPagoByContratoId(Date fechaRolPago,Long empleadoId) throws GenericBusinessException;
	
	public java.util.Collection findContratoByQueryConFecha(Map aMap) throws GenericBusinessException;
	public java.util.Collection findContratoByQuery(int startIndex, int endIndex,Map aMap) throws GenericBusinessException;
	public int getContratoListSize(Map aMap) throws GenericBusinessException;
	
	
	//public Map<ContratoIf,Collection<RubroIf>> getContratosSinFondoReserva(Collection<Long> contratosId) throws GenericBusinessException;
	public Collection<ContratoIf> getContratosSinFondoReserva(Collection<Long> contratosId,Collection<String> codigosFondoReserva) throws GenericBusinessException;
	
	public Collection<DatoGeneralContrato> getContratosByQueryByFechaInicioFechaFinContrato(Map<String,Object> mapa, Date fechaInicio,Date fechaFin);
	public java.util.Collection<Object[]> getContratosEmpleados(Long empresaId) throws GenericBusinessException; 
}
