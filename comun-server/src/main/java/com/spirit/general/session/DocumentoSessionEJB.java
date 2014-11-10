package com.spirit.general.session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.client.SpiritConstants;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.session.generated._DocumentoSession;
import com.spirit.server.QueryBuilder;

/**
 * The <code>DocumentoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class DocumentoSessionEJB extends _DocumentoSession implements DocumentoSessionRemote, DocumentoSessionLocal  {
	
	@EJB 
	private ModuloSessionLocal moduloLocal;
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findDocumentoByTipodocumentoIdAndUsuarioId(java.lang.Long idTipoDocumento, java.lang.Long idUsuario) {
		//select distinct d.* from documento d, usuario_documento ud where d.ID = ud.DOCUMENTO_ID and ud.USUARIO_ID = 2 and d.TIPODOCUMENTO_ID = 1
		String queryString = "select distinct d from DocumentoEJB d, UsuarioDocumentoEJB ud where d.id = ud.documentoId and ud.usuarioId = " + idUsuario + " and d.tipoDocumentoId = " + idTipoDocumento + " and ud.estado = 'A'";
		queryString += " order by d.nombre asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findDocumentoByUsuarioIdAndModuloId(java.lang.Long idUsuario, java.lang.Long idModulo) {
		//select distinct d.* from documento d, usuario_documento ud where d.ID = ud.DOCUMENTO_ID and ud.USUARIO_ID = 2 
		String queryString = "select distinct d from DocumentoEJB d, UsuarioDocumentoEJB ud, TipoDocumentoEJB td where d.id = ud.documentoId and ud.usuarioId = " + idUsuario + " and ud.estado = 'A' and d.tipoDocumentoId = td.id and td.moduloId = " + idModulo;
		queryString += " order by d.nombre asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
		
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findDocumentoByEmpresaIdAndModuloIdAndSigno (java.lang.Long idEmpresa, java.lang.Long idModulo, java.lang.String signo) {
		
		String columnObjectAfecta = "";
		String columnObjectSigno = "";
		
		try {
			ModuloIf moduloIf = moduloLocal.getModulo(idModulo);			
			
			if ("CART".equals(moduloIf.getCodigo())) {
				columnObjectAfecta = "afectacartera";
				columnObjectSigno = "signocartera";
			}
			
			if ("INVE".equals(moduloIf.getCodigo())) {
				columnObjectAfecta = "afectastock";
				columnObjectSigno = "signostock";
			}
			
			if ("FACT".equals(moduloIf.getCodigo())) {
				columnObjectAfecta = "afectaventa";
				columnObjectSigno = "signoventa";
			}
			
			/*if ("COMP".equals(moduloIf.getCodigo())) {
			 columnObjectAfecta = "afectacartera";
			 columnObjectSigno = "signocartera";
			 }*/
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		String queryString = "select distinct d from DocumentoEJB d, TipoDocumentoEJB td where d.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa + " and td.moduloId = " + idModulo + " and td." + columnObjectAfecta + " = 'S' and td." + columnObjectSigno + " = '" + signo + "'";
		queryString += " order by d.nombre asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findDocumentoByEmpresaId(java.lang.Long idEmpresa) {
		String queryString = "select distinct d from DocumentoEJB d, TipoDocumentoEJB td where d.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa + "";
		queryString += " order by d.nombre asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findDocumentoByQuery(int startIndex,int endIndex,Map aMap, Long idEmpresa) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e from DocumentoEJB " + objectName + ", TipoDocumentoEJB td where " + where + " and e.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa;
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
	public java.util.Collection findDocumentoByQueryAndEmpresaId(Map aMap, Long idEmpresa) {
		String objectName = "d";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct d from DocumentoEJB " + objectName + ", TipoDocumentoEJB td where " + where + " and d.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa;
		queryString += " order by d.nombre asc";
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
	public int getDocumentoListSize(Map aMap, Long idEmpresa) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from DocumentoEJB " + objectName + ", TipoDocumentoEJB td where " + where + " and e.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa;
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
	public java.util.Collection findDocumentoByEmpresaIdAndModuloId(java.lang.Long idEmpresa, java.lang.Long idModulo) {		
		String queryString = "select distinct d from DocumentoEJB d, TipoDocumentoEJB td where d.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa + " and td.moduloId = " + idModulo;
		String orderByPart = " order by d.nombre asc";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findDocumentoByModuloAndUsuarioId(String modulo, Long idUsuario) {
		//select distinct d.NOMBRE from TIPO_DOCUMENTO td, DOCUMENTO d, MODULO m, USUARIO_DOCUMENTO ud where td.ID = d.TIPODOCUMENTO_ID and td.MODULO_ID = m.ID and m.CODIGO = 'FACT' and d.ID = ud.DOCUMENTO_ID and ud.USUARIO_ID = 121
		String queryString = "select distinct d from TipoDocumentoEJB td, DocumentoEJB d, ModuloEJB m, UsuarioDocumentoEJB ud where td.id = d.tipoDocumentoId and td.moduloId = m.id and m.codigo = '" + modulo + "' and d.id = ud.documentoId and ud.usuarioId = " + idUsuario;
		queryString += " order by d.nombre asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findDocumentoByCodigoEmpresaId(String codigo, Long empresaId) {
		//select distinct d.NOMBRE from TIPO_DOCUMENTO td, DOCUMENTO d, MODULO m, USUARIO_DOCUMENTO ud where td.ID = d.TIPODOCUMENTO_ID and td.MODULO_ID = m.ID and m.CODIGO = 'FACT' and d.ID = ud.DOCUMENTO_ID and ud.USUARIO_ID = 121
		String queryString = "select distinct d from TipoDocumentoEJB td, DocumentoEJB d where td.id = d.tipoDocumentoId and td.empresaId = '" + empresaId + "' and d.codigo = '" + codigo+"'";		
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection<DocumentoIf> findAdvancePaymentDocumentsByQuery(Map<String, Object> queryMap) {
		//select d.* from TIPO_DOCUMENTO td, DOCUMENTO d where d.TIPO_DOCUMENTO_ID = td.ID and td.EMPRESA_ID = 40 and td.MODULO_ID = 5 and td.TIPOCARTERA = 'C' and d.ANTICIPO = 'S' order by d.nombre asc
		String select = "select d from TipoDocumentoEJB td, DocumentoEJB d";
		String where = "where d.tipoDocumentoId = td.id and td.empresaId = :enterpriseId and td.moduloId = :moduleId and td.tipocartera = :walletType and d.anticipo = 'S'";
		String orderBy = "order by d.nombre asc";
		String queryString = select + SpiritConstants.getBlankSpaceCharacter() + where + SpiritConstants.getBlankSpaceCharacter() + orderBy;
		Query query = manager.createQuery(queryString);
		query.setParameter("enterpriseId", (Long) queryMap.get("enterpriseId"));
		query.setParameter("moduleId", (Long) queryMap.get("moduleId"));
		query.setParameter("walletType", (String) queryMap.get("walletType"));
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection<DocumentoIf> findLevelingDocumentsByQuery(Map<String, Object> queryMap) {
		//select d.* from TIPO_DOCUMENTO td, DOCUMENTO d where d.TIPO_DOCUMENTO_ID = td.ID and td.EMPRESA_ID = 40 and td.MODULO_ID = 5 and td.TIPOCARTERA = 'C' and d.NIVELACION = 'S' order by d.nombre asc
		String select = "select d from TipoDocumentoEJB td, DocumentoEJB d";
		String where = "where d.tipoDocumentoId = td.id and td.empresaId = :enterpriseId and td.moduloId = :moduleId and td.tipocartera = :walletType and d.nivelacion = 'S'";
		String orderBy = "order by d.nombre asc";
		String queryString = select + SpiritConstants.getBlankSpaceCharacter() + where + SpiritConstants.getBlankSpaceCharacter() + orderBy;
		Query query = manager.createQuery(queryString);
		query.setParameter("enterpriseId", (Long) queryMap.get("enterpriseId"));
		query.setParameter("moduleId", (Long) queryMap.get("moduleId"));
		query.setParameter("walletType", (String) queryMap.get("walletType"));
		return query.getResultList();
	}
}
