package com.spirit.nomina.session;

import java.sql.Date;
import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.session.generated._RubroEventualSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface RubroEventualSessionService extends _RubroEventualSessionService{
	public java.util.Collection findRubroEventualByContratoIdByTipoRolIdByFechaRolPago(Long contratoId,Long tipoRolId,Date fechaRolPago,String... estados) throws GenericBusinessException;
	public java.util.Collection<Object> findRubroEventualByQueryByEstados(Map aMap,Map<String,Date> mapaFechas,
		Collection<Long> contratosId,Boolean detallado,String tipoRubro,String... estados) throws GenericBusinessException;
	public java.util.Collection<Object[]> findRubroEventualByQueryByMesCobroByAnioCobroByTipoContratoId(Map aMap,String mes,String anio,Long tipoContratoId) throws GenericBusinessException;
	
	public Collection<Map<String, Object>> findRubroEventualByQueryByEstadosAgrupadosRubroByMesByAnioParaAutorizacion(
		RolPagoIf rolPagoIf,Map<String,Object> aMap,String... estados) throws GenericBusinessException;
	
	public Collection<RolPagoIf> getRolPagoAnticiposList(String... estadoDetalle) throws GenericBusinessException;
	public Collection<RubroEventualIf> findRubroEventualesByTipoRolCobroIdByContratoByMesByAnioByEstado(Long tipoRolCobroId,Long contratoId,String mes,String anio,String... estadosDetalle) throws GenericBusinessException;
	
	public RubroEventualIf registrarRubroEventual(RubroEventualIf modelRubroEventual);

}
