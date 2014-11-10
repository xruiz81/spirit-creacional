package com.spirit.nomina.session;

import java.math.BigDecimal;
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
import com.spirit.nomina.entity.RubroEJB;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.session.generated._RubroSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>RubroSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:21 $
 *
 */
@Stateless
public class RubroSessionEJB extends _RubroSession implements RubroSessionRemote,RubroSessionLocal  {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(RubroSessionEJB.class);

	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/

	public int getRubroListSize(Map aMap) throws GenericBusinessException {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from RubroEJB " + objectName
		+ " where " + where;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();

	}

	public java.util.Collection<RubroIf> findRubroByQuery(int startIndex,
			int endIndex, Map aMap) throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList<RubroIf>();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from RubroEJB " + objectName + " where " + where;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();

	}

	public java.util.Collection<RubroIf> findRubroByQueryByTiposRubro(Map aMap,String... tiposRubro) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from RubroEJB " + objectName + " where " + where;
		if ( tiposRubro != null ){
			queryString += " and ( ";
			for ( String tp : tiposRubro ){
				queryString += " e.tipoRubro = '"+tp+"' or";
			}
			queryString = queryString .substring(0,queryString.length()-2);
			queryString += " ) ";
		}

		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		return query.getResultList();
	}
	
	public BigDecimal getRubroTotalByRubroCodigo(String codigoRubro,Long contratoId,String queryRolesPago) throws GenericBusinessException {
		
		try{
			String queryString = "select sum(rpd.valor) from RolPagoDetalleEJB rpd, RubroEJB r " +
				" where rpd.rubroId = r.id and r.codigo = :codigoRubro " +
				" and rpd.contratoId = :contratoId " +
				" and rpd.rolpagoId in "+queryRolesPago ;
			
			Query query = manager.createQuery(queryString);
			query.setParameter("codigoRubro", codigoRubro);
			query.setParameter("contratoId", contratoId);
	
			List countQueryResult = query.getResultList();
			BigDecimal total = (BigDecimal) countQueryResult.get(0);
			return total;
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error en sumatoria de Rubro con codigo "+codigoRubro);
		}
	}

}
