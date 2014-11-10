package com.spirit.general.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.general.session.generated._UsuarioSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface UsuarioSessionService extends _UsuarioSessionService{
	
	java.util.Collection getUsuarioList(int startIndex,int endIndex,Map aMap) throws com.spirit.exception.GenericBusinessException;
	int getUsuarioListSize(Map aMap) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findUsuarioAndEmpleadoAndRolesByUsuario(java.lang.String usuario) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findUsuarioAndRolesByUsuario(java.lang.String usuario) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findUsuarioAndRolesByUsuarioAndEmpresaId(java.lang.String usuario, java.lang.Long empresaId) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findUsuarioAndRolesByUsuarioTipoSupervisor(java.lang.String usuario) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findUsuarioAndRolesByUsuario(java.lang.String usuario,Long empresaId) throws com.spirit.exception.GenericBusinessException;
	public Map mapearUsuarios(Long idEmpresa);
	public Collection findUsuarioByCredencialesAcceso(Map queryMap) throws com.spirit.exception.GenericBusinessException;
}
