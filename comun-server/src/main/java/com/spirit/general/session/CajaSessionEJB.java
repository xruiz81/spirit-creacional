package com.spirit.general.session;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.general.session.generated._CajaSession;
import com.spirit.server.QueryBuilder;

/**
 * The <code>CajaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 * 
 */
@Stateless
public class CajaSessionEJB extends _CajaSession implements CajaSessionRemote, CajaSessionLocal {

	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCajaByEmpresaId(java.lang.Long idEmpresa) {
		String queryString = "select distinct c from CajaEJB c, OficinaEJB o, EmpresaEJB e where c.oficinaId = o.id and o.empresaId = e.id and e.id = " + idEmpresa;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCajaByUsuarioId(java.lang.Long idUsuario) {
		//select distinct c.* from caja c, punto_impresion pi, usuario_punto_impresion upi, usuario u where u.id = upi.USUARIO_ID  and upi.PUNTOIMPRESION_ID = pi.ID and pi.CAJA_ID = c.ID and u.id = 131072
		String queryString = "select distinct c from CajaEJB c, PuntoImpresionEJB pi, UsuarioPuntoImpresionEJB upi, UsuarioEJB u where u.id = upi.usuarioId and upi.puntoimpresionId = pi.id and pi.cajaId = c.id and u.id = " + idUsuario;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCajaByQueryVariosOficinaId(Map aMap) {	   

		Vector<String> tipoempleadoId_varios= (Vector)aMap.get("oficinaId");
		aMap.remove("oficinaId");

		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);


		String queryString = "from CajaEJB " + objectName + " where "
		+ where;


		if ( tipoempleadoId_varios!=null && tipoempleadoId_varios.size() > 0 ){
			queryString += "and  (";
			for ( String estado : tipoempleadoId_varios ){
				queryString += (" e.oficinaId = '"+estado+"' or");
				System.out.println("tipo empleado ID"+estado);
			}
			queryString = queryString.substring(0,queryString.length()-3);
			queryString += " )";
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
}
