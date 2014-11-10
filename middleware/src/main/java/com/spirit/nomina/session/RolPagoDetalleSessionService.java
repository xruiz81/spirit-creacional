package com.spirit.nomina.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.session.generated._RolPagoDetalleSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface RolPagoDetalleSessionService extends _RolPagoDetalleSessionService{
	public Collection findRolPagoDetalleByRolpagoIdByContratoId(Long rolpagoId,Long contratoId)
	throws com.spirit.exception.GenericBusinessException;

	//public Collection findRolPagoDetalleByRopagoIdByContratoIdByRubro(Long rolpagoId,Long contratoId,Map mapaRubro)
	public Collection findRolPagoDetalleByQueryByRubroMap(Map<String, Object> mapaRolPagoDetalle,Map<String, Object> mapaRubro,String... estadosRolPagoDetalle)
	throws com.spirit.exception.GenericBusinessException;

	public java.util.Collection findRolPagoDetalleContratoIdByMapByEstadosNormal(Map<String,Object> mapaRolPagoDetalle,
		Map<String,Object> mapaContrato,Boolean tipoProvision,TipoRolIf tipoRolIf,String... estados)  throws GenericBusinessException;
	public java.util.Collection findRolPagoDetalleByQueryNormal(Map aMap);
	public java.util.Collection findRolPagoDetalleContratoIdByQuery(Map aMap);
	public Collection<Map<String, Object>> findRolPagoDetalleEventualesByRolPagoByEstado(RolPagoIf rolPagoIf,String estado) throws GenericBusinessException;
	public Collection<RolPagoDetalleIf> findRolPagoDetalleEventualesByMap(Map<String,Object> mapa) throws GenericBusinessException;
	public Collection<RolPagoDetalleIf> findRolPagoDetalleEventualesByMapByEstados(Map<String,Object> mapa,String... estados) throws GenericBusinessException;
	public Collection<RolPagoDetalleIf> findRolPagoDetalleEventualesByMapByEstados(Map<String,Object> mapa,Long rubroId,String... estados) throws GenericBusinessException;
	public java.util.Collection<RolPagoDetalleIf> findRolPagoDetalleByQueryByEstados(Map<String,Object> aMap,String... estados);
	public Collection<Map<String, Object>> crearColeccionAnticiposRolPagoDetalleByQueryByEstados(Map aMap,String... estados) throws GenericBusinessException;
	public Collection<Map<String, Object>> crearColeccionAnticiposRolPagoDetalleByQueryByEstadosConEventuales(Map aMap,String... estados) throws GenericBusinessException;
	
	public java.util.Collection findRolPagoDetalleByRubroCodigoEmpleadoId(java.lang.Long idEmpleado,String codigoRubro) throws GenericBusinessException;
	
}
