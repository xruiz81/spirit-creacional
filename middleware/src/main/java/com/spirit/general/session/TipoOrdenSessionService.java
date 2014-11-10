package com.spirit.general.session;

import java.util.List;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.session.generated._TipoOrdenSessionService;
import com.spirit.medios.entity.SubtipoOrdenIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TipoOrdenSessionService extends _TipoOrdenSessionService{
	public void procesarTipoOrden(TipoOrdenIf model,List<SubtipoOrdenIf> modelDetalleList) throws GenericBusinessException;
    public void actualizarTipoOrden(TipoOrdenIf model,List<SubtipoOrdenIf> modelDetalleList)throws GenericBusinessException;
    public void eliminarTipoOrden(Long tipoOrdenId) throws GenericBusinessException;
    java.util.Collection findTipoOrdenByQuery(int startIndex,int endIndex,Map aMap) throws GenericBusinessException;
    int findTipoOrdenByQuerySize(Map aMap) throws GenericBusinessException;

}
