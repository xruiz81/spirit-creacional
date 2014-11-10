package com.spirit.inventario.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

 
 
import com.spirit.inventario.session.generated._TipoProductoSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
 

/**
 * The <code>TipoProductoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 *
 */
@Stateless
public class TipoProductoSessionEJB extends _TipoProductoSession implements TipoProductoSessionRemote, TipoProductoSessionLocal {
	
	
	@PersistenceContext(unitName="spirit")
	private EntityManager manager;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(TipoProductoSessionEJB.class);
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findTipoProductoDonacion() {
		String queryString = "select e  from TipoProductoEJB e where e.id in( SELECT h.tipoProductoId from DonacionTipoproductoEJB h)";		 
			
		Query query = manager.createQuery(queryString);
		//query.setParameter("fechaInicio",fechaInicio);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findTipoProductoDonacionResto() {
		String queryString = "select e  from TipoProductoEJB e where e.id NOT in( SELECT h.tipoProductoId from DonacionTipoproductoEJB h)";		 
			
		Query query = manager.createQuery(queryString);
		//query.setParameter("fechaInicio",fechaInicio);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findTipoProductoTarjetaAfiliacion(String tarjetatipoId) {
		String queryString = "select e  from TipoProductoEJB e where e.id in( SELECT h.tipoProductoId from PuntosTipoProductoEJB h where h.tarjetaTipoId = "+ tarjetatipoId+")";
		Query query = manager.createQuery(queryString);
		//query.setParameter("fechaInicio",fechaInicio);
		return query.getResultList();
	}
	 
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findTipoProductoTarjetaAfiliacionResto(String tarjetatipoId) {
		String queryString = "select e  from TipoProductoEJB e where e.id NOT in( SELECT h.tipoProductoId from PuntosTipoProductoEJB h where h.tarjetaTipoId = "+ tarjetatipoId+")";
		Query query = manager.createQuery(queryString);
		//query.setParameter("fechaInicio",fechaInicio);
		return query.getResultList();
	}
	
	//ADD 21 JULIO
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findTipoProductoByMedioProduccion(String medioProduccion) {
		String queryString = "select distinct tp from GenericoEJB ge, TipoProductoEJB tp" +
				             " where ge.tipoproductoId = tp.id and ge.mediosProduccion = '" + medioProduccion +"' )";
			
		Query query = manager.createQuery(queryString);
		//query.setParameter("fechaInicio",fechaInicio);
		return query.getResultList();
	}
	//END 21 JULIO
	
}
