package com.spirit.general.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.general.session.generated._EmpleadoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EmpleadoSessionService extends _EmpleadoSessionService{
	Collection getEmpleadoList(int startIndex, int endIndex, Long idEmpresa)	throws com.spirit.exception.GenericBusinessException;
	Collection getEmpleadoList(int startIndex, int endIndex, Map aMap) throws com.spirit.exception.GenericBusinessException;
	Collection getEmpleadoList(int startIndex, int endIndex, Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int getEmpleadoListSize(Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	int getEmpleadoListSize(Map aMap)throws com.spirit.exception.GenericBusinessException;
	int getEmpleadoListSize(Map aMap , Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findEmpleadoByEmpresaIdAndByUsuario(java.lang.Long idEmpresa, java.lang.String usuario) throws com.spirit.exception.GenericBusinessException;
	public Collection findEmpleadoByEmpresaIdAndTipoEmpleadoVendedor(Long idEmpresa, String vendedor) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findEmpleadoByQueryVariosId(Map aMap);
	public Collection findVendedoresByOficinaId(Long idOficina);
	public Collection findVendedoresByEmpresaId(Long idEmpresa);
	public Collection findVendedoresEmpleados();
	public Map mapearEmpleados(Long idEmpresa);
	
}
