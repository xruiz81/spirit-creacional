package com.spirit.general.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoOrdenEJB;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.session.generated._TipoOrdenSession;
import com.spirit.medios.entity.SubtipoOrdenEJB;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.medios.session.SubtipoOrdenSessionLocal;
import com.spirit.server.QueryBuilder;

/**
 * The <code>TipoOrdenSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class TipoOrdenSessionEJB extends _TipoOrdenSession implements TipoOrdenSessionRemote,TipoOrdenSessionLocal  {

	@EJB 
	private SubtipoOrdenSessionLocal subtipoOrdenLocal; 

	/*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	
	public void procesarTipoOrden(TipoOrdenIf model, List<SubtipoOrdenIf> modelDetalleList) throws GenericBusinessException {
		try {
			TipoOrdenEJB tipoOrden = registrarTipoOrden(model);
			manager.persist(tipoOrden);

			for (SubtipoOrdenIf modelDetalle : modelDetalleList) {

				modelDetalle.setTipoordenId(tipoOrden.getPrimaryKey());
				SubtipoOrdenEJB subtipoOrden = registrarSubtipoOrden(modelDetalle);
				manager.merge(subtipoOrden);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al insertar información en TipoOrden-SubtipoOrden");
		}
	}

	public void actualizarTipoOrden(TipoOrdenIf model, List<SubtipoOrdenIf> modelDetalleList) throws GenericBusinessException {
		TipoOrdenIf tipoOrden = registrarTipoOrden(model);
		manager.merge(tipoOrden);

		for (SubtipoOrdenIf modelDetalle : modelDetalleList) {

			modelDetalle.setTipoordenId(tipoOrden.getPrimaryKey());
			SubtipoOrdenIf subtipoOrden = registrarSubtipoOrden(modelDetalle);
			manager.merge(subtipoOrden);

		}
	}

	public void eliminarTipoOrden(Long tipoOrdenId) throws GenericBusinessException {
		try {
			TipoOrdenEJB data = manager.find(TipoOrdenEJB.class, tipoOrdenId);
			Collection<SubtipoOrdenIf> modelDetalleList = subtipoOrdenLocal.findSubtipoOrdenByTipoordenId(tipoOrdenId);

			for (SubtipoOrdenIf modelDetalle : modelDetalleList) {
				manager.remove(modelDetalle);
			}
			manager.remove(data);
			manager.flush();

		} catch (Exception e) {
			throw new GenericBusinessException("Error al eliminar información en TipoOrden");
		}
	}
	
	/**
	 * @param model
	 * @return
	 */
	private TipoOrdenEJB registrarTipoOrden(TipoOrdenIf model) {
		TipoOrdenEJB tipoOrden = new TipoOrdenEJB();
		
		tipoOrden.setId(model.getId());
		tipoOrden.setCodigo(model.getCodigo());
		tipoOrden.setNombre(model.getNombre());
		tipoOrden.setEmpresaId(model.getEmpresaId());
		tipoOrden.setTipo(model.getTipo());
		
		return tipoOrden;
	}
	
	private SubtipoOrdenEJB registrarSubtipoOrden(SubtipoOrdenIf modelDetalle) {
		SubtipoOrdenEJB subtipoOrden = new SubtipoOrdenEJB();
		
		subtipoOrden.setId(modelDetalle.getId());
		subtipoOrden.setCodigo(modelDetalle.getCodigo());
		subtipoOrden.setNombre(modelDetalle.getNombre());
		subtipoOrden.setTipoordenId(modelDetalle.getTipoordenId());
		subtipoOrden.setTipoproveedorId(modelDetalle.getTipoproveedorId());
		
		return subtipoOrden;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoOrdenByQuery(int startIndex,int endIndex,Map aMap) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
 	String objectName = "e";
 	String where = QueryBuilder.buildWhere(aMap, objectName);
 	String queryString = "from TipoOrdenEJB " + objectName + " where " + where;
 	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
    }
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int findTipoOrdenByQuerySize(Map aMap) {
 	String objectName = "e";
 	String where = QueryBuilder.buildWhere(aMap, objectName);
 	String queryString = "select count(*) from TipoOrdenEJB " + objectName + " where " + where;
 	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
    }

}
