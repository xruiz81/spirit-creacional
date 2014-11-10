package com.spirit.compras.session;

import java.util.ArrayList;
import java.util.Collection;
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

import com.spirit.compras.entity.CompraDetalleEJB;
import com.spirit.compras.session.generated._CompraDetalleSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>CompraDetalleSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class CompraDetalleSessionEJB extends _CompraDetalleSession implements CompraDetalleSessionRemote, CompraDetalleSessionLocal  {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	
	//idSriAir
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findSriAirCompraDetalle() {
		String queryString = "select distinct e from SriAirEJB e where e.id in( SELECT h.idSriAir from CompraDetalleEJB h) order by e.codigo asc";		 
			
		Query query = manager.createQuery(queryString);
		//query.setParameter("fechaInicio",fechaInicio);
		return query.getResultList();
	}
	
	//sriIvaRetencionId
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	
	public Collection findSriIvaRetencionCompraDetalle() {
		String queryString = "select distinct e from SriIvaRetencionEJB e where e.id in( SELECT h.sriIvaRetencionId from CompraDetalleEJB h)  order by e.codigo asc";		 
			
		Query query = manager.createQuery(queryString);
		//query.setParameter("fechaInicio",fechaInicio);
		return query.getResultList();
	}
	
	
	
	public Collection findCompraDetalleByDocumentoIdAndByCompraId(Long documentoId, Long compraId) throws GenericBusinessException {
		String queryString = "select distinct cd from CompraDetalleEJB cd, DocumentoEJB d, TipoDocumentoEJB td where cd.documentoId = " + documentoId + " and cd.compraId =  " + compraId + " and cd.documentoId = d.id and d.tipoDocumentoId = td.id and td.signocartera = '+' and td.afectacartera = 'S'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
}
