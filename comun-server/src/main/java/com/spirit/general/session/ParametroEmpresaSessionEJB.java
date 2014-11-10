package com.spirit.general.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.general.session.generated._ParametroEmpresaSession;

/**
 * The <code>ParametroEmpresaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class ParametroEmpresaSessionEJB extends _ParametroEmpresaSession implements ParametroEmpresaSessionRemote, ParametroEmpresaSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findParametroEmpresaAndTipoParametroByEmpresaId(java.lang.Long empresaId) {
	   
	   String queryString = "select distinct pe, tp from ParametroEmpresaEJB pe, TipoParametroEJB tp where pe.empresaId = :empresaId and pe.tipoparametroId = tp.id";
	   // Add a an order by on all primary keys to assure reproducable results.
	   String orderByPart = "";
	   orderByPart += " order by pe.id";
	   queryString += orderByPart;
	   Query query = manager.createQuery(queryString);
	   query.setParameter("empresaId", empresaId);
	   return query.getResultList();
   }
   
}
