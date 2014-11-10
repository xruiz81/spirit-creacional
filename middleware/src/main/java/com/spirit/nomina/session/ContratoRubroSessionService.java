package com.spirit.nomina.session;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Vector;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.ContratoRubroIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.handler.VentasHorasExtrasEmpleado;
import com.spirit.nomina.session.generated._ContratoRubroSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ContratoRubroSessionService extends _ContratoRubroSessionService{
	
	public java.util.Collection findContratoRubroByContratoIdByTipoRolId(Long contratoId,Long tipoRolId) throws GenericBusinessException;
	public java.util.Collection findContratoRubroByContratoIdByFrecuenciaSinProvisiones(Long contratoId,TipoRolIf tipoRolIf) throws GenericBusinessException;
	//public java.util.Collection findContratoRubroByContratoIdRubroId(Long contratoId,Long rubroDecimoId) throws GenericBusinessException;
	public java.util.Collection findContratoRubroByContratoIdByTipoRol(Long contratoId,TipoRolIf tipoRolIf) throws GenericBusinessException;
	public java.util.Map<String, BigDecimal> findMapaContratoRubroByRubroResgistrado(Long contratoId) throws GenericBusinessException;
	public java.util.Collection<ContratoRubroIf> findContratoRubroByMapByTipoRubro(Map<String,Object> mapaContratoRubro,String tipoRubro) throws GenericBusinessException;
	public void saveTotalVentasHorasExtrasEmpleado(Long idEmpresa, Vector<VentasHorasExtrasEmpleado> datosVector) throws GenericBusinessException;
}
