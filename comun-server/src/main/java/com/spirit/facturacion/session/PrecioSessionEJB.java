package com.spirit.facturacion.session;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.session.generated._PrecioSession;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class PrecioSessionEJB extends _PrecioSession implements PrecioSessionRemote,PrecioSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public java.util.Collection findPrecioProductosActivosByListaPrecioId(Long listaPrecioId) throws GenericBusinessException {
		//SELECT DISTINCT * FROM PRECIO PR, PRODUCTO P WHERE P.ID = PR.PRODUCTO_ID AND P.ESTADO = 'A' AND PR.LISTAPRECIO_ID = 1
		String queryString = "select distinct pr from PrecioEJB pr, ProductoEJB p where p.id = pr.productoId and p.estado = 'A' and pr.listaprecioId = :listaPrecioId";
		Query query = manager.createQuery(queryString);
		query.setParameter("listaPrecioId", listaPrecioId);
		return query.getResultList();
	}
}
