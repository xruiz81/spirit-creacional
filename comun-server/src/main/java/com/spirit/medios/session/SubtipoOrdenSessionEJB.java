package com.spirit.medios.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.medios.session.generated._SubtipoOrdenSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * The <code>SubtipoOrdenSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class SubtipoOrdenSessionEJB extends _SubtipoOrdenSession implements SubtipoOrdenSessionRemote, SubtipoOrdenSessionLocal {

	@PersistenceContext(unitName="spirit")
	   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(SubtipoOrdenSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findSubtipoOrdenByEmpresaId(java.lang.Long idEmpresa) {
	   String queryString = "select distinct so from TipoOrdenEJB to, SubtipoOrdenEJB so where so.tipoordenId = to.id and to.empresaId = " + idEmpresa;
	   String orderByPart = " order by so.id";
	   queryString += orderByPart;
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }

}
