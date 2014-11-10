package com.spirit.general.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.general.session.generated._TipoParametroSession;

/**
 * The <code>TipoParametroSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class TipoParametroSessionEJB extends _TipoParametroSession implements TipoParametroSessionRemote,TipoParametroSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection  findTipoParametroByEmpresaIdAndbyCodigo(java.lang.Long empresaId,String codigo) {

     String queryString = "from TipoParametroEJB e where e.empresaId = " + empresaId + " and e.codigo = '" + codigo + "'";
     // Add a an order by on all primary keys to assure reproducable results.
     String orderByPart = "";
     orderByPart += " order by e.id";
     queryString += orderByPart;
     Query query = manager.createQuery(queryString);
     return query.getResultList();
   }

}
