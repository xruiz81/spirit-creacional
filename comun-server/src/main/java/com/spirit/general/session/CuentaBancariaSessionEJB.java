package com.spirit.general.session;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.generated._CuentaBancariaSession;
import com.spirit.server.QueryBuilder;

/**
 * The <code>CuentaBancariaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:16 $
 *
 */
@Stateless
public class CuentaBancariaSessionEJB extends _CuentaBancariaSession implements CuentaBancariaSessionRemote, CuentaBancariaSessionLocal  {

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getCuentaBancariaByQueryListSize(Map aMap) {		
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from CuentaBancariaEJB " + objectName + " where " + where;
		Query countQuery = manager.createQuery(queryString);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			countQuery.setParameter(propertyKey, property);
		}
		
		List countQueryResult = countQuery.getResultList();
		Long countResult = (Long) countQueryResult.get(0);		
		return countResult.intValue();
	}
	
	public Object[] getCuentaBancariaPlusByCuentaBancariaId(Long cuentaBancariaId) throws GenericBusinessException {	
		try{
			String queryString = "select b.nombre,cb.cuenta,c.codigo,tc.nombre " +
				" from BancoEJB b,CuentaBancariaEJB cb,CuentaEJB c,CuentaEntidadEJB ce,TipoCuentaEJB tc "+
				" where c.id = ce.cuentaId and ce.entidadId = cb.id and cb.bancoId = b.id "+
				" and c.tipocuentaId = tc.id and ce.tipoEntidad = 'B' and cb.id = :cuentaBancariaId";
			Query query = manager.createQuery(queryString);
			query.setParameter("cuentaBancariaId", cuentaBancariaId);
			List queryResult = query.getResultList();
			Object[] result = (Object[]) queryResult.get(0);		
			return result;
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error al consultar Cuenta Banacaria !!");
		}
	}
	
	public Collection<CuentaIf> getCuentaDeCuentaBancariaByCuentaBancariaId(Long cuentaBancariaId) throws GenericBusinessException {	
		try{
			String queryString = "select c " +
				" from CuentaBancariaEJB cb,CuentaEJB c,CuentaEntidadEJB ce "+
				" where c.id = ce.cuentaId and ce.entidadId = cb.id "+
				" and ce.tipoEntidad = 'B' and cb.id = :cuentaBancariaId";
			Query query = manager.createQuery(queryString);
			query.setParameter("cuentaBancariaId", cuentaBancariaId);
			List queryResult = query.getResultList();
			return queryResult;
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error al consultar Cuenta Banacaria !!");
		}
	}

}
