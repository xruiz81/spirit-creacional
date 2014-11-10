package com.spirit.crm.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.client.SpiritConstants;
import com.spirit.crm.entity.ClienteOficinaEJB;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.generated._ClienteOficinaSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.session.TipoClienteSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>ClienteOficinaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:20 $
 *
 */
@Stateless
public class ClienteOficinaSessionEJB extends _ClienteOficinaSession implements ClienteOficinaSessionRemote, ClienteOficinaSessionLocal {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	@EJB 
	private TipoClienteSessionLocal tipoClienteLocal;
	
	@EJB 
	private UtilitariosSessionLocal utilitariosLocal;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(ClienteOficinaSessionEJB.class);
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findClienteOficinaByEmpresaId(java.lang.Long idEmpresa) throws GenericBusinessException {
		String objectName = "m";
		String queryString = "select distinct m from ClienteOficinaEJB " + objectName + ", ClienteEJB c, TipoClienteEJB tc where  m.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " order by m.id desc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findClienteOficinaByIdentificacionAndEmpresaId(java.lang.String identificacion, java.lang.Long idEmpresa) throws GenericBusinessException {
		String objectName = "m";
		String queryString = "select distinct m from ClienteOficinaEJB " + objectName + ", ClienteEJB c, TipoClienteEJB tc where m.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and c.identificacion = '" + identificacion + "' order by m.id desc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findClienteOficinaByTipoProveedorIdAndEmpresaId(java.lang.Long idTipoProveedor, java.lang.Long idEmpresa){
		//select distinct co.* from cliente_oficina co, cliente c, tipo_proveedor tp where 
		//co.CLIENTE_ID = c.ID and c.TIPOPROVEEDOR_ID = tp.ID and c.TIPOPROVEEDOR_ID = 1 and tp.EMPRESA_ID = 1
		String queryString = "select distinct co from ClienteOficinaEJB co, ClienteEJB c, TipoProveedorEJB tp where co.clienteId = c.id and c.tipoproveedorId = tp.id and c.tipoproveedorId = " + idTipoProveedor + " and tp.empresaId = " + idEmpresa + "";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	//ADD 27 JULIO
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getClienteOficinaByTipoProveedorIdList(int startIndex, int endIndex, Map aMap, String tipoCliente, Long tipoProveedorId, Long idEmpresa, String mmpg) {
		long idTipoCliente=0L, idTipoProveedor=0L, idTipoAmbos=0L;
		String codigoTipoCliente="CL", codigoTipoProveedor="PR", codigoTipoAmbos="AM";
		String identificacion = (String) aMap.get("identificacion");
		aMap.remove("identificacion");
		
		try {
			Collection tipoClienteCollection;
			tipoClienteCollection = tipoClienteLocal.findTipoClienteByEmpresaId(idEmpresa);
			Iterator tipoClienteIterator = tipoClienteCollection.iterator();
			
			while (tipoClienteIterator.hasNext()) {
				TipoClienteIf tipoClienteIf = (TipoClienteIf) tipoClienteIterator.next();
				
				if (codigoTipoCliente.equals(tipoClienteIf.getCodigo()))
					idTipoCliente=tipoClienteIf.getId();
				
				if (codigoTipoProveedor.equals(tipoClienteIf.getCodigo()))
					idTipoProveedor=tipoClienteIf.getId();
				
				if (codigoTipoAmbos.equals(tipoClienteIf.getCodigo()))
					idTipoAmbos=tipoClienteIf.getId();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		String objectName = "co";
		String where = "";
		
		if (aMap.size() > 0)
			where = QueryBuilder.buildWhere(aMap, objectName) + " and ";
		
		// Si tipoCliente==cliente entonces buscamos clientes con tipo cliente o ambos
		if (tipoCliente.equals(codigoTipoCliente))
			where += "(e.tipoclienteId = " + idTipoCliente + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		// Si tipoCliente==proveedor entonces buscamos clientes con tipo proveedor o ambos
		if (tipoCliente.equals(codigoTipoProveedor))
			where += "(e.tipoclienteId = " + idTipoProveedor 
				   + " or e.tipoclienteId = " + idTipoAmbos + ")"
				   + " and e.tipoproveedorId = " + tipoProveedorId +"";//;
		
		// Si tipoCliente==ambos entonces buscamos todos los clientes y proveedores
		if (tipoCliente.equals(codigoTipoAmbos))
			where += "(e.tipoclienteId = " + idTipoCliente 
				   + " or e.tipoclienteId = " + idTipoProveedor 
				   + " or e.tipoclienteId = " + idTipoAmbos + ")"//;
				   + " and e.tipoproveedorId = " + tipoProveedorId +"";//;
		
		String queryString = "select distinct co from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc, TipoProveedorEJB tp where " + where;
		queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and e.tipoproveedorId = tp.id and tc.empresaId = " + idEmpresa + " and e.identificacion like '" + identificacion + "'";
		
		if(tipoCliente.equals(codigoTipoCliente)){
			
			 queryString = "select distinct co from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc where " + where;
			queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and e.identificacion like '" + identificacion + "'";
			
		}
		
		if (mmpg.toUpperCase().contains("M"))
			queryString += " and tp.tipo <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and tp.tipo <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and tp.tipo <> 'G'";
		queryString += " order by co.descripcion asc";
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
	//END 27 JULIO
	
	//TODO
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getClienteOficinaList(int startIndex, int endIndex, Map aMap, String tipoCliente, Long idEmpresa, String mmpg) {
		long idTipoCliente=0L, idTipoProveedor=0L, idTipoAmbos=0L;
		String codigoTipoCliente="CL", codigoTipoProveedor="PR", codigoTipoAmbos="AM";
		String identificacion = (String) aMap.get("identificacion");
		aMap.remove("identificacion");
		
		try {
			Collection tipoClienteCollection;
			tipoClienteCollection = tipoClienteLocal.findTipoClienteByEmpresaId(idEmpresa);
			Iterator tipoClienteIterator = tipoClienteCollection.iterator();
			
			while (tipoClienteIterator.hasNext()) {
				TipoClienteIf tipoClienteIf = (TipoClienteIf) tipoClienteIterator.next();
				
				if (codigoTipoCliente.equals(tipoClienteIf.getCodigo()))
					idTipoCliente=tipoClienteIf.getId();
				
				if (codigoTipoProveedor.equals(tipoClienteIf.getCodigo()))
					idTipoProveedor=tipoClienteIf.getId();
				
				if (codigoTipoAmbos.equals(tipoClienteIf.getCodigo()))
					idTipoAmbos=tipoClienteIf.getId();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		String objectName = "co";
		String where = "";
		
		if (aMap.size() > 0)
			where = QueryBuilder.buildWhere(aMap, objectName) + " and ";
		
		// Si tipoCliente==cliente entonces buscamos clientes con tipo cliente o ambos
		if (tipoCliente.equals(codigoTipoCliente))
			where += "(e.tipoclienteId = " + idTipoCliente + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		// Si tipoCliente==proveedor entonces buscamos clientes con tipo proveedor o ambos
		if (tipoCliente.equals(codigoTipoProveedor))
			where += "(e.tipoclienteId = " + idTipoProveedor + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		// Si tipoCliente==ambos entonces buscamos todos los clientes y proveedores
		if (tipoCliente.equals(codigoTipoAmbos))
			where += "(e.tipoclienteId = " + idTipoCliente + " or e.tipoclienteId = " + idTipoProveedor + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		String queryString = "select distinct co from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc, TipoProveedorEJB tp where " + where;
		queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and e.tipoproveedorId = tp.id and tc.empresaId = " + idEmpresa + " and e.identificacion like '" + identificacion + "'";
		
		if(tipoCliente.equals(codigoTipoCliente)){
			
			 queryString = "select distinct co from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc where " + where;
			queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and e.identificacion like '" + identificacion + "'";
			
		}
		
		if (mmpg.toUpperCase().contains("M"))
			queryString += " and tp.tipo <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and tp.tipo <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and tp.tipo <> 'G'";
		queryString += " order by co.descripcion asc";
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
	
	
	//ADD 27 JULIO
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getClienteOficinaByTipoProveedorIdListSize(Map aMap, String tipoCliente, Long tipoProveedorId, Long idEmpresa, String mmpg) {
		long idTipoCliente=0L, idTipoProveedor=0L, idTipoAmbos=0L;
		String codigoTipoCliente="CL", codigoTipoProveedor="PR", codigoTipoAmbos="AM";
		String identificacion = (String) aMap.get("identificacion");
		aMap.remove("identificacion");
		
		try {
			Collection tipoClienteCollection;
			tipoClienteCollection = tipoClienteLocal.findTipoClienteByEmpresaId(idEmpresa);
			Iterator tipoClienteIterator = tipoClienteCollection.iterator();
			
			while (tipoClienteIterator.hasNext()) {
				TipoClienteIf tipoClienteIf = (TipoClienteIf) tipoClienteIterator.next();
				
				if (codigoTipoCliente.equals(tipoClienteIf.getCodigo()))
					idTipoCliente=tipoClienteIf.getId();
				
				if (codigoTipoProveedor.equals(tipoClienteIf.getCodigo()))
					idTipoProveedor=tipoClienteIf.getId();
				
				if (codigoTipoAmbos.equals(tipoClienteIf.getCodigo()))
					idTipoAmbos=tipoClienteIf.getId();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		String objectName = "co";
		String where = "";
		
		if (aMap.size() > 0)
			where = QueryBuilder.buildWhere(aMap, objectName) + " and ";
		
		// Si tipoCliente==cliente entonces buscamos clientes con tipo cliente o ambos
		if (tipoCliente.equals(codigoTipoCliente))
			where += "(e.tipoclienteId = " + idTipoCliente + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		// Si tipoCliente==proveedor entonces buscamos clientes con tipo proveedor o ambos
		if (tipoCliente.equals(codigoTipoProveedor)){
			where += "(e.tipoclienteId = " + idTipoProveedor 
			       + " or e.tipoclienteId = " + idTipoAmbos + ")" //; MODIFIED 27 JULIO
				   + " and e.tipoproveedorId = " + tipoProveedorId + "";
		}
		// Si tipoCliente==ambos entonces buscamos todos los clientes y proveedores
		if (tipoCliente.equals(codigoTipoAmbos))
			where += "(e.tipoclienteId = " + idTipoCliente 
					+ " or e.tipoclienteId = " + idTipoProveedor 
					+ " or e.tipoclienteId = " + idTipoAmbos + ")"//;
					 + " and e.tipoproveedorId = " + tipoProveedorId + "";
		
		String queryString = "select distinct count(*) from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc, TipoProveedorEJB tp where " + where;
		queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and e.tipoproveedorId = tp.id and tc.empresaId = " + idEmpresa + " and e.identificacion like '" + identificacion + "'";
		
		if(tipoCliente.equals(codigoTipoCliente)){
			
			 queryString = "select distinct count(*) from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc where " + where;
			queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and e.identificacion like '" + identificacion + "'";
			
		}
		
		if (mmpg.toUpperCase().contains("M"))
			queryString += " and tp.tipo <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and tp.tipo <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and tp.tipo <> 'G'";
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
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}
	//END 27 JULIO
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getClienteOficinaListSize(Map aMap, String tipoCliente, Long idEmpresa, String mmpg) {
		long idTipoCliente=0L, idTipoProveedor=0L, idTipoAmbos=0L;
		String codigoTipoCliente="CL", codigoTipoProveedor="PR", codigoTipoAmbos="AM";
		String identificacion = (String) aMap.get("identificacion");
		aMap.remove("identificacion");
		
		try {
			Collection tipoClienteCollection;
			tipoClienteCollection = tipoClienteLocal.findTipoClienteByEmpresaId(idEmpresa);
			Iterator tipoClienteIterator = tipoClienteCollection.iterator();
			
			while (tipoClienteIterator.hasNext()) {
				TipoClienteIf tipoClienteIf = (TipoClienteIf) tipoClienteIterator.next();
				
				if (codigoTipoCliente.equals(tipoClienteIf.getCodigo()))
					idTipoCliente=tipoClienteIf.getId();
				
				if (codigoTipoProveedor.equals(tipoClienteIf.getCodigo()))
					idTipoProveedor=tipoClienteIf.getId();
				
				if (codigoTipoAmbos.equals(tipoClienteIf.getCodigo()))
					idTipoAmbos=tipoClienteIf.getId();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		String objectName = "co";
		String where = "";
		
		if (aMap.size() > 0)
			where = QueryBuilder.buildWhere(aMap, objectName) + " and ";
		
		// Si tipoCliente==cliente entonces buscamos clientes con tipo cliente o ambos
		if (tipoCliente.equals(codigoTipoCliente))
			where += "(e.tipoclienteId = " + idTipoCliente + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		// Si tipoCliente==proveedor entonces buscamos clientes con tipo proveedor o ambos
		if (tipoCliente.equals(codigoTipoProveedor))
			where += "(e.tipoclienteId = " + idTipoProveedor + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		// Si tipoCliente==ambos entonces buscamos todos los clientes y proveedores
		if (tipoCliente.equals(codigoTipoAmbos))
			where += "(e.tipoclienteId = " + idTipoCliente + " or e.tipoclienteId = " + idTipoProveedor + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		String queryString = "select distinct count(*) from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc, TipoProveedorEJB tp where " + where;
		queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and e.tipoproveedorId = tp.id and tc.empresaId = " + idEmpresa + " and e.identificacion like '" + identificacion + "'";
		
		if(tipoCliente.equals(codigoTipoCliente)){
			
			 queryString = "select distinct count(*) from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc where " + where;
			queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and e.identificacion like '" + identificacion + "'";
			
		}
		
		
		if (mmpg.toUpperCase().contains("M"))
			queryString += " and tp.tipo <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and tp.tipo <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and tp.tipo <> 'G'";
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
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}
	
	//ADD 27 JULIO
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getClienteOficinaByTipoProveedorIdList(int startIndex, int endIndex, Map aMap, Long idCorporacion, String tipoCliente, Long tipoProveedorId, Long idEmpresa, String mmpg) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		long idTipoCliente=0L, idTipoProveedor=0L, idTipoAmbos=0L;
		String codigoTipoCliente="CL", codigoTipoProveedor="PR", codigoTipoAmbos="AM";
		String identificacion = (String) aMap.get("identificacion");
		aMap.remove("identificacion");
		
		try {
			Collection tipoClienteCollection;
			tipoClienteCollection = tipoClienteLocal.findTipoClienteByEmpresaId(idEmpresa);
			Iterator tipoClienteIterator = tipoClienteCollection.iterator();
			
			while (tipoClienteIterator.hasNext()) {
				
				TipoClienteIf tipoClienteIf = (TipoClienteIf) tipoClienteIterator.next();
				
				if (codigoTipoCliente.equals(tipoClienteIf.getCodigo()))
					idTipoCliente=tipoClienteIf.getId();
				
				if (codigoTipoProveedor.equals(tipoClienteIf.getCodigo()))
					idTipoProveedor=tipoClienteIf.getId();
				
				if (codigoTipoAmbos.equals(tipoClienteIf.getCodigo()))
					idTipoAmbos=tipoClienteIf.getId();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		String objectName = "co";
		String where = "";
		
		if (aMap.size() > 0)
			where = QueryBuilder.buildWhere(aMap, objectName) + " and ";
		
		// Si tipoCliente==cliente entonces buscamos clientes con tipo cliente o ambos
		if (tipoCliente.equals(codigoTipoCliente))
			where += "(e.tipoclienteId = " + idTipoCliente + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		// Si tipoCliente==proveedor entonces buscamos clientes con tipo proveedor o ambos
		if (tipoCliente.equals(codigoTipoProveedor))
			where += "(e.tipoclienteId = " + idTipoProveedor 
			       + " or e.tipoclienteId = " + idTipoAmbos + ")" //;COMENTED 27 JULIO
			       + " and e.tipoproveedorId = " + tipoProveedorId + "";
		
		// Si tipoCliente==ambos entonces buscamos todos los clientes y proveedores
		if (tipoCliente.equals(codigoTipoAmbos))
			where += "(e.tipoclienteId = " + idTipoCliente 
					+ " or e.tipoclienteId = " + idTipoProveedor 
					+ " or e.tipoclienteId = " + idTipoAmbos + ")"//;
					+ " and e.tipoproveedorId = " + tipoProveedorId + "";
		
		String queryString = "select distinct co from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc, TipoProveedorEJB tp where " + where;
		queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and e.tipoproveedorId = tp.id and tc.empresaId = " + idEmpresa + " and e.corporacionId = " + idCorporacion + " and e.identificacion like '" + identificacion + "'";
		
		 
			
			if(tipoCliente.equals(codigoTipoCliente)){
				System.out.println("2222222222222222222222222222222");
				queryString = "select distinct co from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc where " + where;
				queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and e.corporacionId = " + idCorporacion + " and e.identificacion like '" + identificacion + "'";
			
			}
		 
		
		if (mmpg.toUpperCase().contains("M"))
			queryString += " and tp.tipo <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and tp.tipo <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and tp.tipo <> 'G'";
		queryString += " order by co.descripcion asc";
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
	//END 27 JULIO
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getClienteOficinaList(int startIndex, int endIndex, Map aMap, Long idCorporacion, String tipoCliente, Long idEmpresa, String mmpg) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		long idTipoCliente=0L, idTipoProveedor=0L, idTipoAmbos=0L;
		String codigoTipoCliente="CL", codigoTipoProveedor="PR", codigoTipoAmbos="AM";
		String identificacion = (String) aMap.get("identificacion");
		aMap.remove("identificacion");
		
		try {
			Collection tipoClienteCollection;
			tipoClienteCollection = tipoClienteLocal.findTipoClienteByEmpresaId(idEmpresa);
			Iterator tipoClienteIterator = tipoClienteCollection.iterator();
			
			while (tipoClienteIterator.hasNext()) {
				
				TipoClienteIf tipoClienteIf = (TipoClienteIf) tipoClienteIterator.next();
				
				if (codigoTipoCliente.equals(tipoClienteIf.getCodigo()))
					idTipoCliente=tipoClienteIf.getId();
				
				if (codigoTipoProveedor.equals(tipoClienteIf.getCodigo()))
					idTipoProveedor=tipoClienteIf.getId();
				
				if (codigoTipoAmbos.equals(tipoClienteIf.getCodigo()))
					idTipoAmbos=tipoClienteIf.getId();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		String objectName = "co";
		String where = "";
		
		if (aMap.size() > 0)
			where = QueryBuilder.buildWhere(aMap, objectName) + " and ";
		
		// Si tipoCliente==cliente entonces buscamos clientes con tipo cliente o ambos
		if (tipoCliente.equals(codigoTipoCliente))
			where += "(e.tipoclienteId = " + idTipoCliente + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		// Si tipoCliente==proveedor entonces buscamos clientes con tipo proveedor o ambos
		if (tipoCliente.equals(codigoTipoProveedor))
			where += "(e.tipoclienteId = " + idTipoProveedor + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		// Si tipoCliente==ambos entonces buscamos todos los clientes y proveedores
		if (tipoCliente.equals(codigoTipoAmbos))
			where += "(e.tipoclienteId = " + idTipoCliente + " or e.tipoclienteId = " + idTipoProveedor + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		String queryString = "select distinct co from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc, TipoProveedorEJB tp where " + where;
		queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and e.tipoproveedorId = tp.id and tc.empresaId = " + idEmpresa + " and e.corporacionId = " + idCorporacion + " and e.identificacion like '" + identificacion + "'";
		
		 
			
			if(tipoCliente.equals(codigoTipoCliente)){
				System.out.println("2222222222222222222222222222222");
				queryString = "select distinct co from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc where " + where;
				queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and e.corporacionId = " + idCorporacion + " and e.identificacion like '" + identificacion + "'";
			
			}
		 
		
		if (mmpg.toUpperCase().contains("M"))
			queryString += " and tp.tipo <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and tp.tipo <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and tp.tipo <> 'G'";
		queryString += " order by co.descripcion asc";
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
	
	//ADD 27 JULIO
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getClienteOficinaByTipoProveedorIdListSize(Map aMap, Long idCorporacion, String tipoCliente, Long tipoProveedorId, Long idEmpresa, String mmpg) {
		long idTipoCliente=0L, idTipoProveedor=0L, idTipoAmbos=0L;
		String codigoTipoCliente="CL", codigoTipoProveedor="PR", codigoTipoAmbos="AM";
		String identificacion = (String) aMap.get("identificacion");
		aMap.remove("identificacion");
		
		try {
			Collection tipoClienteCollection;
			tipoClienteCollection = tipoClienteLocal.findTipoClienteByEmpresaId(idEmpresa);
			Iterator tipoClienteIterator = tipoClienteCollection.iterator();
			
			while (tipoClienteIterator.hasNext()) {
				TipoClienteIf tipoClienteIf = (TipoClienteIf) tipoClienteIterator.next();
				
				if (codigoTipoCliente.equals(tipoClienteIf.getCodigo()))
					idTipoCliente=tipoClienteIf.getId();
				
				if (codigoTipoProveedor.equals(tipoClienteIf.getCodigo()))
					idTipoProveedor=tipoClienteIf.getId();
				
				if (codigoTipoAmbos.equals(tipoClienteIf.getCodigo()))
					idTipoAmbos=tipoClienteIf.getId();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		String objectName = "co";
		String where = "";
		
		if (aMap.size() > 0)
			where = QueryBuilder.buildWhere(aMap, objectName) + " and ";
		
		// Si tipoCliente==cliente entonces buscamos clientes con tipo cliente o ambos
		if (tipoCliente.equals(codigoTipoCliente))
			where += "(e.tipoclienteId = " + idTipoCliente + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		// Si tipoCliente==proveedor entonces buscamos clientes con tipo proveedor o ambos
		if (tipoCliente.equals(codigoTipoProveedor))
			where += "(e.tipoclienteId = " + idTipoProveedor 
				   + " or e.tipoclienteId = " + idTipoAmbos + ")"
				   + " and e.tipoproveedorId = " + tipoProveedorId + "";//;
		
		// Si tipoCliente==ambos entonces buscamos todos los clientes y proveedores
		if (tipoCliente.equals(codigoTipoAmbos))
			where += "(e.tipoclienteId = " + idTipoCliente 
			       + " or e.tipoclienteId = " + idTipoProveedor 
			       + " or e.tipoclienteId = " + idTipoAmbos + ")"//;
			       + " and e.tipoproveedorId = " + tipoProveedorId + "";//;
		
		String queryString = "select distinct count(*) from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc, TipoProveedorEJB tp where " + where;
		queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and e.tipoproveedorId = tp.id and tc.empresaId = " + idEmpresa + " and e.corporacionId = " + idCorporacion + " and e.identificacion like '" + identificacion + "'";
		
		if(tipoCliente.equals(codigoTipoCliente)){
			System.out.println("ASASDASD");
			queryString = "select distinct count(*) from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc where " + where;
			queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and e.corporacionId = " + idCorporacion + " and e.identificacion like '" + identificacion + "'";
		
		}
		
		
		if (mmpg.toUpperCase().contains("M"))
			queryString += " and tp.tipo <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and tp.tipo <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and tp.tipo <> 'G'";
		queryString += " order by co.descripcion";
		Query query = manager.createQuery(queryString);
		
		System.out.println("Hibernate: "+queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
			
		}
		
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}
	//END 27 JULIO
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getClienteOficinaListSize(Map aMap, Long idCorporacion, String tipoCliente, Long idEmpresa, String mmpg) {
		long idTipoCliente=0L, idTipoProveedor=0L, idTipoAmbos=0L;
		String codigoTipoCliente="CL", codigoTipoProveedor="PR", codigoTipoAmbos="AM";
		String identificacion = (String) aMap.get("identificacion");
		aMap.remove("identificacion");
		
		try {
			Collection tipoClienteCollection;
			tipoClienteCollection = tipoClienteLocal.findTipoClienteByEmpresaId(idEmpresa);
			Iterator tipoClienteIterator = tipoClienteCollection.iterator();
			
			while (tipoClienteIterator.hasNext()) {
				TipoClienteIf tipoClienteIf = (TipoClienteIf) tipoClienteIterator.next();
				
				if (codigoTipoCliente.equals(tipoClienteIf.getCodigo()))
					idTipoCliente=tipoClienteIf.getId();
				
				if (codigoTipoProveedor.equals(tipoClienteIf.getCodigo()))
					idTipoProveedor=tipoClienteIf.getId();
				
				if (codigoTipoAmbos.equals(tipoClienteIf.getCodigo()))
					idTipoAmbos=tipoClienteIf.getId();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		String objectName = "co";
		String where = "";
		
		if (aMap.size() > 0)
			where = QueryBuilder.buildWhere(aMap, objectName) + " and ";
		
		// Si tipoCliente==cliente entonces buscamos clientes con tipo cliente o ambos
		if (tipoCliente.equals(codigoTipoCliente))
			where += "(e.tipoclienteId = " + idTipoCliente + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		// Si tipoCliente==proveedor entonces buscamos clientes con tipo proveedor o ambos
		if (tipoCliente.equals(codigoTipoProveedor))
			where += "(e.tipoclienteId = " + idTipoProveedor + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		// Si tipoCliente==ambos entonces buscamos todos los clientes y proveedores
		if (tipoCliente.equals(codigoTipoAmbos))
			where += "(e.tipoclienteId = " + idTipoCliente + " or e.tipoclienteId = " + idTipoProveedor + " or e.tipoclienteId = " + idTipoAmbos + ")";
		
		String queryString = "select distinct count(*) from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc, TipoProveedorEJB tp where " + where;
		queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and e.tipoproveedorId = tp.id and tc.empresaId = " + idEmpresa + " and e.corporacionId = " + idCorporacion + " and e.identificacion like '" + identificacion + "'";
		
		if(tipoCliente.equals(codigoTipoCliente)){
			System.out.println("ASASDASD");
			queryString = "select distinct count(*) from ClienteEJB e, ClienteOficinaEJB co, TipoClienteEJB tc where " + where;
			queryString += " and e.id = co.clienteId and e.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and e.corporacionId = " + idCorporacion + " and e.identificacion like '" + identificacion + "'";
		
		}
		
		
		if (mmpg.toUpperCase().contains("M"))
			queryString += " and tp.tipo <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and tp.tipo <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and tp.tipo <> 'G'";
		queryString += " order by co.descripcion";
		Query query = manager.createQuery(queryString);
		
		System.out.println("Hibernate: "+queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
			
		}
		
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ClienteOficinaEJB registrarClienteOficina(ClienteOficinaIf modelClienteOficina) {
		ClienteOficinaEJB clienteOficina = new ClienteOficinaEJB();
		clienteOficina.setCodigoProveedorAuto(modelClienteOficina.getCodigoProveedorAuto());
		clienteOficina.setId(modelClienteOficina.getId());
		clienteOficina.setCodigo(modelClienteOficina.getCodigo());
		clienteOficina.setClienteId(modelClienteOficina.getClienteId());
		clienteOficina.setCiudadId(modelClienteOficina.getCiudadId());
		clienteOficina.setDireccion(modelClienteOficina.getDireccion());
		clienteOficina.setTelefono(modelClienteOficina.getTelefono());
		clienteOficina.setFax(modelClienteOficina.getFax());
		clienteOficina.setEmail(modelClienteOficina.getEmail());
		clienteOficina.setFechaCreacion(modelClienteOficina.getFechaCreacion());
		clienteOficina.setMontoCredito((modelClienteOficina.getMontoCredito()!=null)?utilitariosLocal.redondeoValor(modelClienteOficina.getMontoCredito()):BigDecimal.ZERO);
		clienteOficina.setMontoGarantia((modelClienteOficina.getMontoGarantia()!=null)?utilitariosLocal.redondeoValor(modelClienteOficina.getMontoGarantia()):BigDecimal.ZERO);
		clienteOficina.setCalificacion(modelClienteOficina.getCalificacion());
		clienteOficina.setObservacion(modelClienteOficina.getObservacion());
		clienteOficina.setEstado(modelClienteOficina.getEstado());
		clienteOficina.setDescripcion(modelClienteOficina.getDescripcion());
		return clienteOficina;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findClienteOficinaByEmailByEmpresaId(String email,Long empresaId) throws GenericBusinessException {
		try{
			String queryString = "select e from ClienteOficinaEJB e,ClienteEJB c,TipoClienteEJB tc where " +
			" e.clienteId = c.id and c.tipoclienteId = tc.id " +
			" and e.email = :email and tc.empresaId = :empresaId ";
			// Add a an order by on all primary keys to assure reproducable results.
			String orderByPart = "";
			orderByPart += " order by e.id";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			query.setParameter("email", email);
			query.setParameter("empresaId", empresaId);
			return query.getResultList();
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error en búsqueda de Cliente Oficina por email !!");
		}
	}
	
	public java.util.Collection findProveedoresProductosVenta(Long empresaId) throws GenericBusinessException {
		//select distinct co.* from CLIENTE_OFICINA co, PRODUCTO p, GENERICO g where p.PROVEEDOR_ID = co.ID and p.PERMITEVENTA = 'S' and g.ID = p.GENERICO_ID and g.EMPRESA_ID = 1
		String queryString = "select distinct co from ClienteOficinaEJB co, ProductoEJB p, GenericoEJB g where p.proveedorId = co.id and p.permiteventa = 'S' and g.id = p.genericoId and g.empresaId = :empresaId";
		Query query = manager.createQuery(queryString);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}
	

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findVendedorByClienteOficinaId(java.lang.Long clienteOficinaId) throws GenericBusinessException {
		String queryString = "select distinct e from ClienteOficinaEJB co, EmpleadoEJB e where co.vendedorId = e.id";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int findBusinessOperatorOfficeByQueryListSize(Map<String, Object> queryMap) {
		String identifierOrName = (String) queryMap.get("identifierOrName");
		Long enterpriseId = (Long) queryMap.get("enterpriseId");
		String walletType = (String) queryMap.get("walletType");
		String bothType = "AM";
		// select distinct co.* from CLIENTE c, CLIENTE_OFICINA co, TIPO_CLIENTE tc where c.ID = co.CLIENTE_ID and c.TIPOCLIENTE_ID = tc.ID and tc.EMPRESA_ID = 1 and (tc.CODIGO = 'CL' or tc.CODIGO = 'AM')
		String select = "select distinct count(*)";
		String from = "from ClienteEJB c, ClienteOficinaEJB co, TipoClienteEJB tc";
		String where = "where c.id = co.clienteId and c.tipoclienteId = tc.id and tc.empresaId = :enterpriseId and (tc.codigo = :walletType or tc.codigo = :bothType)";		
		if (!identifierOrName.equals(SpiritConstants.getEmptyCharacter())) {
			where += SpiritConstants.getBlankSpaceCharacter() + "and (c.identificacion like '" + identifierOrName + "' or c.nombreLegal like '" + identifierOrName + "' or c.razonSocial like '" + identifierOrName + "')";
		}
		String queryString = select + SpiritConstants.getBlankSpaceCharacter() + from + SpiritConstants.getBlankSpaceCharacter() + where;
		Query query = manager.createQuery(queryString);
		query.setParameter("enterpriseId", enterpriseId);
		query.setParameter("walletType", walletType);
		query.setParameter("bothType", bothType);
		@SuppressWarnings("rawtypes")
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<ClienteOficinaIf> findBusinessOperatorOfficeByQuery(Map<String, Object> queryMap) {
		String identifierOrName = (String) queryMap.get("identifierOrName");
		Long enterpriseId = (Long) queryMap.get("enterpriseId");
		String walletType = (String) queryMap.get("walletType");
		String bothType = "AM";
		// select distinct co.* from CLIENTE c, CLIENTE_OFICINA co, TIPO_CLIENTE tc where c.ID = co.CLIENTE_ID and c.TIPOCLIENTE_ID = tc.ID and tc.EMPRESA_ID = 1 and (tc.CODIGO = 'CL' or tc.CODIGO = 'AM')
		String select = "select distinct co";
		String from = "from ClienteEJB c, ClienteOficinaEJB co, TipoClienteEJB tc";
		String where = "where c.id = co.clienteId and c.tipoclienteId = tc.id and tc.empresaId = :enterpriseId and (tc.codigo = :walletType or tc.codigo = :bothType)";		
		if (!identifierOrName.equals(SpiritConstants.getEmptyCharacter())) {
			where += SpiritConstants.getBlankSpaceCharacter() + "and (c.identificacion like '" + identifierOrName + "' or c.nombreLegal like '" + identifierOrName + "' or c.razonSocial like '" + identifierOrName + "')";
		}
		String queryString = select + SpiritConstants.getBlankSpaceCharacter() + from + SpiritConstants.getBlankSpaceCharacter() + where;
		Query query = manager.createQuery(queryString);
		query.setParameter("enterpriseId", enterpriseId);
		query.setParameter("walletType", walletType);
		query.setParameter("bothType", bothType);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<ClienteOficinaIf> findBusinessOperatorOfficeByQuery(int startIndex, int endIndex, Map<String, Object> queryMap) {
		if ((endIndex - startIndex) < 0)
			return new ArrayList<ClienteOficinaIf>();
		String identifierOrName = (String) queryMap.get("identifierOrName");
		Long enterpriseId = (Long) queryMap.get("enterpriseId");
		String walletType = (String) queryMap.get("walletType");
		String bothType = "AM";
		// select distinct co.* from CLIENTE c, CLIENTE_OFICINA co, TIPO_CLIENTE tc where c.ID = co.CLIENTE_ID and c.TIPOCLIENTE_ID = tc.ID and tc.EMPRESA_ID = 1 and (tc.CODIGO = 'CL' or tc.CODIGO = 'AM')
		String select = "select distinct co";
		String from = "from ClienteEJB c, ClienteOficinaEJB co, TipoClienteEJB tc";
		String where = "where c.id = co.clienteId and c.tipoclienteId = tc.id and tc.empresaId = :enterpriseId and (tc.codigo = :walletType or tc.codigo = :bothType)";		
		if (!identifierOrName.equals(SpiritConstants.getEmptyCharacter())) {
			where += SpiritConstants.getBlankSpaceCharacter() + "and (c.identificacion like '" + identifierOrName + "' or c.nombreLegal like '" + identifierOrName + "' or c.razonSocial like '" + identifierOrName + "')";
		}
		String queryString = select + SpiritConstants.getBlankSpaceCharacter() + from + SpiritConstants.getBlankSpaceCharacter() + where;
		Query query = manager.createQuery(queryString);
		query.setParameter("enterpriseId", enterpriseId);
		query.setParameter("walletType", walletType);
		query.setParameter("bothType", bothType);
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
}
