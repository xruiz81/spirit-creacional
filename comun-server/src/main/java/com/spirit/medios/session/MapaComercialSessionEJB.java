package com.spirit.medios.session;

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

import com.spirit.medios.entity.MapaComercialEJB;
import com.spirit.medios.session.generated._MapaComercialSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>MapaComercialSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class MapaComercialSessionEJB extends _MapaComercialSession implements MapaComercialSessionRemote, MapaComercialSessionLocal  {

	@PersistenceContext(unitName="spirit")
	private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(MapaComercialSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
   public Collection findMapaComercialByFechas(String fechaInicio, String fechaFinal) {
	 	  String queryString = "from MapaComercialEJB e where e.fecha >= :fechaInicio and e.fecha <= :fechaFinal";
	   	  //String queryString = "from MapaComercialEJB e where e.fecha >= '" + fechaInicio + "' and e.fecha <= '" + fechaFinal;	
	   	  // Add a an order by on all primary keys to assure reproducable results.
	      String orderByPart = "";
	      orderByPart += " order by e.id";
	      queryString += orderByPart;
	      Query query = manager.createQuery(queryString);
	      query.setParameter("fechaInicio",fechaInicio);
	      query.setParameter("fechaFinal",fechaFinal);
	      return query.getResultList();
	}

   public Collection findMapaComercialByPlanMedioDetalleIdAndFechas(Long idPlanMedioDetalle, String fechaInicio, String fechaFinal) {
	 	  String queryString = "from MapaComercialEJB e where e.planmediosdetalleId = " + idPlanMedioDetalle + " and e.fecha >= :fechaInicio and e.fecha <= :fechaFinal";
	   
	   	  //String queryString = "from MapaComercialEJB e where e.fecha >= '" + fechaInicio + "' and e.fecha <= '" + fechaFinal;	
	   	  // Add a an order by on all primary keys to assure reproducable results.
	      String orderByPart = "";
	      orderByPart += " order by e.id";
	      queryString += orderByPart;
	      Query query = manager.createQuery(queryString);
	      query.setParameter("fechaInicio",fechaInicio);
	      query.setParameter("fechaFinal",fechaFinal);
	      return query.getResultList();
	}

}
