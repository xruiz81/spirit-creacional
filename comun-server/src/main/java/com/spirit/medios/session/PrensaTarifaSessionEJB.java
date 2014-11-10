package com.spirit.medios.session;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.medios.session.generated._PrensaTarifaSession;

/**
 * The <code>PrensaTarifaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class PrensaTarifaSessionEJB extends _PrensaTarifaSession implements PrensaTarifaSessionRemote  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;
   
   @EJB
   private UtilitariosSessionLocal utilitariosLocal;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   public String getMaximoCodigoPrensaTarifa(Long clienteId){
		String queryString = "select max(pt.codigo) from PrensaTarifaEJB pt where pt.clienteId = " + clienteId + "";
		Query query = manager.createQuery(queryString);
		String codigo = "";
		if(query.getResultList().get(0)!=null){
			codigo = query.getResultList().toString().substring(1).replaceAll("]","");
		}
		return codigo;
	}

}
