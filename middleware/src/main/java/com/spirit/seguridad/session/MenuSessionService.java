package com.spirit.seguridad.session;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.seguridad.session.generated._MenuSessionService;

public interface MenuSessionService extends _MenuSessionService{

	java.util.Collection findMenuByCodigoAndRolIdAndNivelMinimo(String codigo, Long rolId, int nivelMinimo) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findMenuByCodigoAndNivelMinimo(String codigo, int nivelMinimo) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection getMenuListSortedByFavorito() throws com.spirit.exception.GenericBusinessException;
	java.util.Collection getMenuListSortedByCodigo() throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findTreeMenuByPadreId(java.lang.Long idMenuRaiz) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findMenuByPadreIdAndByRolId(java.lang.Long idRol, java.lang.Long idMenuPadre) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findMenuByGrandFatherId(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findMenuByNombreAndByPadreId(String nombre, java.lang.Long padreId) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findSubMenuByNombre(String nombre) throws com.spirit.exception.GenericBusinessException;
	public void actualizarMenu(List<MenuIf> modelMenu)throws GenericBusinessException;
	public void eliminarMenu(Long menuId, List<MenuIf> modelMenu) throws GenericBusinessException;
	public Collection findMenuByRolId(Long idRol) throws GenericBusinessException;
		
}
