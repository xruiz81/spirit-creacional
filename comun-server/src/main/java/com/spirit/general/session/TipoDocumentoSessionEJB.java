package com.spirit.general.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.session.generated._TipoDocumentoSession;
import com.spirit.server.QueryBuilder;

/**
 * The <code>TipoDocumentoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:16 $
 *
 */
@Stateless
public class TipoDocumentoSessionEJB extends _TipoDocumentoSession implements TipoDocumentoSessionRemote, TipoDocumentoSessionLocal {
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findTipoDocumentoByQuery(int startIndex,int endIndex,Map aMap) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from TipoDocumentoEJB " + objectName + " where " + where;
		queryString += " order by e.nombre asc";
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getTipoDocumentoListSize(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from TipoDocumentoEJB " + objectName + " where " + where;
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findTipoDocumentoByEmpresaId(java.lang.Long empresaId) {
		
		String queryString = "from TipoDocumentoEJB e where e.empresaId = :empresaId ";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.nombre asc";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findTipoDocumentoByQuery(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from TipoDocumentoEJB " + objectName + " where " + where;
		String orderBy = " order by e.nombre asc";
		queryString += orderBy;
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findTipoDocumentoByQueryAndUsuarioId(Map aMap, java.lang.Long idUsuario) {
		//select distinct d.* from documento d, usuario_documento ud where d.ID = ud.DOCUMENTO_ID and ud.USUARIO_ID = 2 and d.TIPODOCUMENTO_ID = 1
		String objectName = "td";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct td from TipoDocumentoEJB td, DocumentoEJB d, UsuarioDocumentoEJB ud where d.tipoDocumentoId = td.id and d.id = ud.documentoId and ud.usuarioId = " + idUsuario + " and ud.estado = 'A'";
		if (where != null && !where.equals(""))
			queryString += " and " + where;
		queryString += " order by td.nombre asc";
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findTipoDocumentoTransferibleByUsuarioIdAndModuloId(java.lang.Long idUsuario, java.lang.Long idModulo) {
		//select distinct d.* from documento d, usuario_documento ud where d.ID = ud.DOCUMENTO_ID and ud.USUARIO_ID = 2 
		String queryString = "select distinct td from DocumentoEJB d, UsuarioDocumentoEJB ud, TipoDocumentoEJB td where d.id = ud.documentoId and ud.usuarioId = " + idUsuario + " and ud.estado = 'A' and d.tipoDocumentoId = td.id and td.transferible = 'S' and td.moduloId = " + idModulo;
		queryString += " order by d.nombre asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public Map mapearTiposDocumento(Long idEmpresa) {
		Map tiposDocumentoMap = new HashMap();
		Iterator tiposDocumentoIterator = findTipoDocumentoByEmpresaId(idEmpresa).iterator();
		while (tiposDocumentoIterator.hasNext()) {
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoIterator.next();
			tiposDocumentoMap.put(tipoDocumento.getId(), tipoDocumento);
		}
		return tiposDocumentoMap;
	}
}
