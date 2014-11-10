package com.spirit.contabilidad.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.session.generated._CuentaSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CuentaSessionService extends _CuentaSessionService{

	Collection findCuentaByQueryForBalance(Map aMap) throws com.spirit.exception.GenericBusinessException;
	Collection getCuentaList(int startIndex, int endIndex, Map aMap, Long idUsuario) throws com.spirit.exception.GenericBusinessException;
	int getCuentaListSize(Map aMap)throws com.spirit.exception.GenericBusinessException;
	int getCuentaListSize(Map aMap, Long idUsuario)throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findCuentaByPadreIdAndByUsuarioId(java.lang.Long idUsuario, java.lang.Long idCuentaPadre) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findCuentaByPadreIdByUsuarioIdAndNivel(java.lang.Long idUsuario, java.lang.Long idCuentaPadre, int nivel) throws com.spirit.exception.GenericBusinessException;   
    java.util.Collection findCuentaByQueryByUsuarioIdAndCodigoOrNombre(Map aMap, Long idUsuario) throws com.spirit.exception.GenericBusinessException;
    java.util.Collection findCuentaByQueryAndUsuarioId(Map aMap, Long idUsuario) throws com.spirit.exception.GenericBusinessException;
    int findCuentaByQueryByUsuarioIdAndCodigoOrNombreListSize(Map aMap, Long idUsuario)throws com.spirit.exception.GenericBusinessException;
    java.util.Collection findCuentasForBalanceGeneral(Long planCuentaId, Long periodoId, java.sql.Date fechaFinMovimiento) throws com.spirit.exception.GenericBusinessException;
    java.util.Collection findCuentasAdicionalesForBalanceGeneral(Long planCuentaId, Long periodoId, java.sql.Date fechaFinMovimiento) throws com.spirit.exception.GenericBusinessException;
    java.util.Collection findCuentasForEstadoResultados(Long planCuentaId, Long periodoId, java.sql.Date fechaInicioMovimiento, java.sql.Date fechaFinMovimiento) throws com.spirit.exception.GenericBusinessException;
    java.util.Collection findCuentaByPlanCuentaIdBetweenCuentaInicialCodigoAndCuentaFinalCodigoByUsuarioId(Long idPlanCuenta, Long idUsuario, String codigoCuentaInicial, String codigoCuentaFinal, String imputable) throws com.spirit.exception.GenericBusinessException;
    public void procesarCuenta(CuentaIf cuenta, Long idUsuario) throws GenericBusinessException;
    public boolean procesarEliminarCuenta(Long idCuenta) throws GenericBusinessException;
    public int getNivelMaximoByPlanCuentaId(Long idPlanCuenta);
    java.util.Collection findCuentaByEmpresaId(Long idEmpresa) throws GenericBusinessException;
    java.util.Collection findCuentaByEmpresaIdAndQuery(Long idEmpresa, Map aMap) throws GenericBusinessException;
    public Collection<CuentaIf> findCuentasDeCuentasBancarias(Long planCuentaId,String tipoCuenta,Long empresaId) throws GenericBusinessException ;
    public Map mapearCuentas(Long idEmpresa) throws GenericBusinessException;
}
