package com.spirit.facturacion.session;

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

import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleEJB;
import com.spirit.facturacion.session.generated._FacturaDetalleSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>FacturaDetalleSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:17 $
 *
 */
@Stateless
public class FacturaDetalleSessionEJB extends _FacturaDetalleSession implements FacturaDetalleSessionRemote, FacturaDetalleSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public Collection findFacturaDetalleByDocumentoIdAndByFacturaId(Long documentoId, Long facturaId) throws GenericBusinessException {
		
		String queryString = "from FacturaDetalleEJB m where m.documentoId = " + documentoId + " and m.facturaId =  " + facturaId;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public Collection findFacturaDetalleByFacturaIdAndByProductoId(Long facturaId, Long productoId) throws GenericBusinessException {
		
		String queryString = "from FacturaDetalleEJB m where m.productoId = " + productoId + " and m.facturaId =  " + facturaId;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

}
