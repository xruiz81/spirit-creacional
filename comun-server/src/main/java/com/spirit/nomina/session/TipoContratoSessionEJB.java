package com.spirit.nomina.session;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.session.generated._TipoContratoSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * The <code>TipoContratoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:21 $
 *
 */
@Stateless
public class TipoContratoSessionEJB extends _TipoContratoSession implements TipoContratoSessionRemote,TipoContratoSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(TipoContratoSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   public Collection<TipoContratoIf> findTiposContratosUsados(Long empresaId) throws GenericBusinessException{
	   try{
		   String queryString = "Select distinct e from TipoContratoEJB e,ContratoEJB c " +
		   " where c.tipocontratoId = e.id and e.empresaId = :empresaId ";
		   // Add a an order by on all primary keys to assure reproducable results.
		   String orderByPart = "";
		   orderByPart += " order by e.id";
		   queryString += orderByPart;
		   Query query = manager.createQuery(queryString);
		   query.setParameter("empresaId", empresaId);
		   return query.getResultList();
	   } catch(Exception e){
		   throw new GenericBusinessException("Error en consulta de Tipos de Contrato !!");
	   }
   }
   
}
