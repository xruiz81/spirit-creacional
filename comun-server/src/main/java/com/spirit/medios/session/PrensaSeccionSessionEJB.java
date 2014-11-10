package com.spirit.medios.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.medios.entity.PrensaSeccionEJB;
import com.spirit.medios.entity.PrensaSeccionIf;
import com.spirit.medios.session.generated._PrensaSeccionSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * The <code>PrensaSeccionSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class PrensaSeccionSessionEJB extends _PrensaSeccionSession implements PrensaSeccionSessionRemote  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(PrensaSeccionSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void procesarPrensa(List<PrensaSeccionIf> prensaSeccionList){
	   for (PrensaSeccionIf prensaSeccionDetalle : prensaSeccionList) {
		   PrensaSeccionEJB prensaSeccion = registrarPrensaSeccion(prensaSeccionDetalle);
		   manager.merge(prensaSeccion);
	   }
   }
  
   private PrensaSeccionEJB registrarPrensaSeccion(PrensaSeccionIf modelSeccion){
	   PrensaSeccionEJB prensaSeccion = new PrensaSeccionEJB();
		
	   prensaSeccion.setId(modelSeccion.getId());
	   prensaSeccion.setClienteId(modelSeccion.getClienteId());
	   prensaSeccion.setCodigo(modelSeccion.getCodigo());
	   prensaSeccion.setSeccion(modelSeccion.getSeccion());
	   
	   return prensaSeccion;
   }
   
   public String getMaximoCodigoPrensaSeccion(Long clienteId){
		String queryString = "select max(ps.codigo) from PrensaSeccionEJB ps where ps.clienteId = " + clienteId + "";
		Query query = manager.createQuery(queryString);
		String codigo = "";
		if(query.getResultList().get(0)!=null){
			codigo = query.getResultList().toString().substring(1).replaceAll("]","");
		}
		return codigo;
	}

}
