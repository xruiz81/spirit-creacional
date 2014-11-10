package com.spirit.crm.session;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.crm.entity.ClienteContactoIf;
import com.spirit.crm.entity.ClienteEJB;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteIndicadorIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.ClienteRetencionIf;
import com.spirit.crm.entity.ClienteZonaIf;
import com.spirit.crm.session.generated._ClienteSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.mdb.messages.bo.ClienteMessageLocal;
import com.spirit.general.session.ParametroEmpresaSessionLocal;
import com.spirit.general.session.TipoClienteSessionLocal;
import com.spirit.performance.session.PerformanceInterceptor;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>ClienteSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.3 $, $Date: 2014/06/13 23:23:51 $
 * 
 */
@Stateless
@Interceptors( { PerformanceInterceptor.class })
public class ClienteSessionEJB extends _ClienteSession implements ClienteSessionRemote,
		ClienteSessionLocal {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	@EJB
	private TipoClienteSessionLocal tipoClienteLocal;

	// @EJB
	// private ClienteCondicionSessionLocal clienteCondicionLocal;

	@EJB
	private ClienteContactoSessionLocal clienteContactoLocal;

	@EJB
	private ClienteIndicadorSessionLocal clienteIndicadorLocal;

	@EJB
	private ClienteOficinaSessionLocal clienteOficinaLocal;

	@EJB
	private ClienteZonaSessionLocal clienteZonaLocal;
	
	@EJB
	private ClienteRetencionSessionLocal clienteRetencionLocal;

	@EJB
	private ClienteMessageLocal clienteMessageLocal;

	@EJB
	private ParametroEmpresaSessionLocal parametroEmpresaSessionLocal;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(ClienteSessionEJB.class);

	@Resource
	private SessionContext ctx;

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findClienteByEmpresaId(Long idEmpresa) throws GenericBusinessException {
		// select distinct c.* from cliente c, tipo_cliente tc where
		// c.TIPOCLIENTE_ID = tc.ID and tc.EMPRESA_ID = 1
		String queryString = "select distinct c from ClienteEJB c, TipoClienteEJB tc where c.tipoclienteId = tc.id and tc.empresaId = "
				+ idEmpresa;
		String orderByPart = "";
		orderByPart += " order by c.nombreLegal asc";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findClienteByIdentificacionAndEmpresaId(String identificacion, Long idEmpresa) throws GenericBusinessException {
		String queryString = "select distinct c from ClienteEJB c, TipoClienteEJB tc where c.identificacion = '" + identificacion + "' and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa;
		String orderByPart = "";
		orderByPart += " order by c.nombreLegal asc";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findClienteByEmpresaIdaAndTipoClienteId(Long idEmpresa,Long idTipoCliente)
			throws GenericBusinessException {
		// select distinct c.* from cliente c, tipo_cliente tc where
		// c.TIPOCLIENTE_ID = tc.ID and tc.EMPRESA_ID = 1
		String queryString = "select distinct c from ClienteEJB c, TipoClienteEJB tc " +
							 " where c.tipoclienteId = tc.id and tc.id = " + idTipoCliente +
							 " and tc.empresaId = " + idEmpresa;
		String orderByPart = "";
		orderByPart += " order by c.nombreLegal asc";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findClienteByOrdenMedioId(Long idOrdenMedio) throws GenericBusinessException {
		//SELECT DISTINCT C.* FROM ORDEN_MEDIO OM, CLIENTE_OFICINA CO, CLIENTE C WHERE OM.`CLIENTE_OFICINA_ID` = CO.`ID` AND C.`ID` = CO.`CLIENTE_ID`
		String queryString = "select distinct c from OrdenMedioEJB om, ClienteOficinaEJB co, ClienteEJB c where om.clienteOficinaId = co.id and co.clienteId = c.id and om.id = :idOrdenMedio";
		Query query = manager.createQuery(queryString);
		query.setParameter("idOrdenMedio", idOrdenMedio);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findClienteByPresupuestoId(Long idPresupuesto) throws GenericBusinessException {
		//SELECT DISTINCT C.* FROM PRESUPUESTO P, ORDEN_TRABAJO_DETALLE OTD, ORDEN_TRABAJO OT, CLIENTE_OFICINA CO, CLIENTE C WHERE P.`ORDENTRABAJODET_ID` = OTD.`ID` AND OTD.`ORDEN_ID` = OT.`ID` AND OT.`CLIENTEOFICINA_ID` = CO.`ID` AND CO.`CLIENTE_ID` = C.`ID`
		String queryString = "select distinct c from PresupuestoEJB p, OrdenTrabajoDetalleEJB otd, OrdenTrabajoEJB ot, ClienteOficinaEJB co, ClienteEJB c where p.ordentrabajodetId = otd.id and otd.ordenId = ot.id and ot.clienteoficinaId = co.id and co.clienteId = c.id and p.id = :idPresupuesto";
		Query query = manager.createQuery(queryString);
		query.setParameter("idPresupuesto", idPresupuesto);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findClienteByClienteOficinaId(
			java.lang.Long idClienteOficina) {
		// SELECT distinct c.* FROM cliente c, cliente_oficina co where
		// c.ID=co.CLIENTE_ID and co.ID=1
		String queryString = "select distinct c from ClienteEJB c, ClienteOficinaEJB co where c.id=co.clienteId and co.id= "
				+ idClienteOficina;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String findClienteByClienteOficinaIdNombreLegal(
			java.lang.Long idClienteOficina) {
		// SELECT distinct c.* FROM cliente c, cliente_oficina co where
		// c.ID=co.CLIENTE_ID and co.ID=1
		String nombreLegal="";
		String queryString = "select c.nombreLegal from ClienteEJB c, ClienteOficinaEJB co where c.id=co.clienteId and co.id= "+ idClienteOficina;
		Query query = manager.createQuery(queryString);
		
		Collection cc= query.getResultList();
		
		Iterator it=cc.iterator();		
		if(it.hasNext()){		
			String datos = (String) it.next();				
			nombreLegal=datos;
		}
		
		return nombreLegal;
	}
	
	//ADD 27 JULIO
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getClienteByTipoProveedorList(int startIndex, int endIndex, Map aMap,
			String tipoCliente, Long tipoProveedorId,java.lang.Long idEmpresa) {

		long idTipoCliente = 0L, idTipoProveedor = 0L, idTipoAmbos = 0L;
		String codigoTipoCliente = "CL", codigoTipoProveedor = "PR", codigoTipoAmbos = "AM";

		try {
			Collection tipoClienteCollection;
			tipoClienteCollection = tipoClienteLocal
					.findTipoClienteByEmpresaId(idEmpresa);
			Iterator tipoClienteIterator = tipoClienteCollection.iterator();

			while (tipoClienteIterator.hasNext()) {

				TipoClienteIf tipoClienteIf = (TipoClienteIf) tipoClienteIterator
						.next();

				if (codigoTipoCliente.equals(tipoClienteIf.getCodigo()))
					idTipoCliente = tipoClienteIf.getId();

				if (codigoTipoProveedor.equals(tipoClienteIf.getCodigo()))
					idTipoProveedor = tipoClienteIf.getId();

				if (codigoTipoAmbos.equals(tipoClienteIf.getCodigo()))
					idTipoAmbos = tipoClienteIf.getId();

			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}

		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = "";
		if (aMap.size() > 0)
			where = QueryBuilder.buildWhere(aMap, objectName) + " and ";

		// Si tipoCliente==cliente entonces buscamos clientes con tipo cliente o
		// ambos
		if (tipoCliente.equals(codigoTipoCliente))
			where += "(e.tipoclienteId = " + idTipoCliente
					+ " or e.tipoclienteId = " + idTipoAmbos + ")";

		// Si tipoCliente==proveedor entonces buscamos clientes con tipo
		// proveedor o ambos
		if (tipoCliente.equals(codigoTipoProveedor))
			where += "(e.tipoclienteId = " + idTipoProveedor
					+ " or e.tipoclienteId = " + idTipoAmbos + ")"//;
					+ " and e.tipoproveedorId = " + tipoProveedorId + "";

		// Si tipoCliente==ambos entonces buscamos todos los clientes y
		// proveedores
		if (tipoCliente.equals(codigoTipoAmbos))
			where += "(e.tipoclienteId = " + idTipoCliente
					+ " or e.tipoclienteId = " + idTipoProveedor
					+ " or e.tipoclienteId = " + idTipoAmbos + ")";

		String queryString = "select distinct e from ClienteEJB " + objectName
				+ ", TipoClienteEJB tc where " + where;
		queryString += " and e.tipoclienteId = tc.id and tc.empresaId = "
				+ idEmpresa;
		queryString += " order by e.nombreLegal asc, e.razonSocial asc";
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
	public Collection getClienteList(int startIndex, int endIndex, Map aMap,
			String tipoCliente, java.lang.Long idEmpresa) {

		long idTipoCliente = 0L, idTipoProveedor = 0L, idTipoAmbos = 0L;
		String codigoTipoCliente = "CL", codigoTipoProveedor = "PR", codigoTipoAmbos = "AM";

		try {
			Collection tipoClienteCollection;
			tipoClienteCollection = tipoClienteLocal
					.findTipoClienteByEmpresaId(idEmpresa);
			Iterator tipoClienteIterator = tipoClienteCollection.iterator();

			while (tipoClienteIterator.hasNext()) {

				TipoClienteIf tipoClienteIf = (TipoClienteIf) tipoClienteIterator
						.next();

				if (codigoTipoCliente.equals(tipoClienteIf.getCodigo()))
					idTipoCliente = tipoClienteIf.getId();

				if (codigoTipoProveedor.equals(tipoClienteIf.getCodigo()))
					idTipoProveedor = tipoClienteIf.getId();

				if (codigoTipoAmbos.equals(tipoClienteIf.getCodigo()))
					idTipoAmbos = tipoClienteIf.getId();

			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}

		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = "";
		if (aMap.size() > 0)
			where = QueryBuilder.buildWhere(aMap, objectName) + " and ";

		// Si tipoCliente==cliente entonces buscamos clientes con tipo cliente o
		// ambos
		if (tipoCliente.equals(codigoTipoCliente))
			where += "(e.tipoclienteId = " + idTipoCliente
					+ " or e.tipoclienteId = " + idTipoAmbos + ")";

		// Si tipoCliente==proveedor entonces buscamos clientes con tipo
		// proveedor o ambos
		if (tipoCliente.equals(codigoTipoProveedor))
			where += "(e.tipoclienteId = " + idTipoProveedor
					+ " or e.tipoclienteId = " + idTipoAmbos + ")";

		// Si tipoCliente==ambos entonces buscamos todos los clientes y
		// proveedores
		if (tipoCliente.equals(codigoTipoAmbos))
			where += "(e.tipoclienteId = " + idTipoCliente
					+ " or e.tipoclienteId = " + idTipoProveedor
					+ " or e.tipoclienteId = " + idTipoAmbos + ")";

		String queryString = "select distinct e from ClienteEJB " + objectName
				+ ", TipoClienteEJB tc where " + where;
		queryString += " and e.tipoclienteId = tc.id and tc.empresaId = "
				+ idEmpresa;
		queryString += " order by e.nombreLegal asc, e.razonSocial asc";
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
	public int getClienteByTipoProveedorListSize(Map aMap, String tipoCliente, Long tipoProveedorId,
			java.lang.Long idEmpresa) {
		long idTipoCliente = 0L, idTipoProveedor = 0L, idTipoAmbos = 0L;
		String codigoTipoCliente = "CL", codigoTipoProveedor = "PR", codigoTipoAmbos = "AM";

		try {
			Collection tipoClienteCollection;
			tipoClienteCollection = tipoClienteLocal
					.findTipoClienteByEmpresaId(idEmpresa);
			Iterator tipoClienteIterator = tipoClienteCollection.iterator();

			while (tipoClienteIterator.hasNext()) {

				TipoClienteIf tipoClienteIf = (TipoClienteIf) tipoClienteIterator
						.next();

				if (codigoTipoCliente.equals(tipoClienteIf.getCodigo()))
					idTipoCliente = tipoClienteIf.getId();

				if (codigoTipoProveedor.equals(tipoClienteIf.getCodigo()))
					idTipoProveedor = tipoClienteIf.getId();

				if (codigoTipoAmbos.equals(tipoClienteIf.getCodigo()))
					idTipoAmbos = tipoClienteIf.getId();

			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}

		String objectName = "e";
		String where = "";

		if (aMap.size() > 0)
			where = QueryBuilder.buildWhere(aMap, objectName) + " and ";

		// Si tipoCliente==cliente entonces buscamos clientes con tipo cliente o
		// ambos
		if (tipoCliente.equals(codigoTipoCliente))
			where += "(e.tipoclienteId = " + idTipoCliente
					+ " or e.tipoclienteId = " + idTipoAmbos + ")";

		// Si tipoCliente==proveedor entonces buscamos clientes con tipo
		// proveedor o ambos
		if (tipoCliente.equals(codigoTipoProveedor))
			where += "(e.tipoclienteId = " + idTipoProveedor
					+ " or e.tipoclienteId = " + idTipoAmbos + ")"
					+ " and e.tipoproveedorId = " + tipoProveedorId + "";

		// Si tipoCliente==ambos entonces buscamos todos los clientes y
		// proveedores
		if (tipoCliente.equals(codigoTipoAmbos))
			where += "(e.tipoclienteId = " + idTipoCliente
					+ " or e.tipoclienteId = " + idTipoProveedor
					+ " or e.tipoclienteId = " + idTipoAmbos + ")";

		String queryString = "select distinct count(*) from ClienteEJB "
				+ objectName + ", TipoClienteEJB tc where " + where;
		queryString += " and e.tipoclienteId = tc.id and tc.empresaId = "
				+ idEmpresa;
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
	public int getClienteListSize(Map aMap, String tipoCliente,
			java.lang.Long idEmpresa) {
		long idTipoCliente = 0L, idTipoProveedor = 0L, idTipoAmbos = 0L;
		String codigoTipoCliente = "CL", codigoTipoProveedor = "PR", codigoTipoAmbos = "AM";

		try {
			Collection tipoClienteCollection;
			tipoClienteCollection = tipoClienteLocal
					.findTipoClienteByEmpresaId(idEmpresa);
			Iterator tipoClienteIterator = tipoClienteCollection.iterator();

			while (tipoClienteIterator.hasNext()) {

				TipoClienteIf tipoClienteIf = (TipoClienteIf) tipoClienteIterator
						.next();

				if (codigoTipoCliente.equals(tipoClienteIf.getCodigo()))
					idTipoCliente = tipoClienteIf.getId();

				if (codigoTipoProveedor.equals(tipoClienteIf.getCodigo()))
					idTipoProveedor = tipoClienteIf.getId();

				if (codigoTipoAmbos.equals(tipoClienteIf.getCodigo()))
					idTipoAmbos = tipoClienteIf.getId();

			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}

		String objectName = "e";
		String where = "";

		if (aMap.size() > 0)
			where = QueryBuilder.buildWhere(aMap, objectName) + " and ";

		// Si tipoCliente==cliente entonces buscamos clientes con tipo cliente o
		// ambos
		if (tipoCliente.equals(codigoTipoCliente))
			where += "(e.tipoclienteId = " + idTipoCliente
					+ " or e.tipoclienteId = " + idTipoAmbos + ")";

		// Si tipoCliente==proveedor entonces buscamos clientes con tipo
		// proveedor o ambos
		if (tipoCliente.equals(codigoTipoProveedor))
			where += "(e.tipoclienteId = " + idTipoProveedor
					+ " or e.tipoclienteId = " + idTipoAmbos + ")";

		// Si tipoCliente==ambos entonces buscamos todos los clientes y
		// proveedores
		if (tipoCliente.equals(codigoTipoAmbos))
			where += "(e.tipoclienteId = " + idTipoCliente
					+ " or e.tipoclienteId = " + idTipoProveedor
					+ " or e.tipoclienteId = " + idTipoAmbos + ")";

		String queryString = "select distinct count(*) from ClienteEJB "
				+ objectName + ", TipoClienteEJB tc where " + where;
		queryString += " and e.tipoclienteId = tc.id and tc.empresaId = "
				+ idEmpresa;
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

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findClienteByFilteredQuery(int startIndex,
			int endIndex, Map aMap, java.lang.Long idTipoCliente,
			java.lang.Long idEmpresa) throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e from ClienteEJB " + objectName
				+ ", TipoClienteEJB tc where ";
		String codigoTipoCliente = "CL", codigoTipoProveedor = "PR", codigoTipoAmbos = "AM";
		Long tipoClienteId = 0L, tipoProveedorId = 0L, tipoAmbosId = 0L;

		try {
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", idEmpresa);
			parameterMap.put("codigo", codigoTipoCliente);
			tipoClienteId = ((TipoClienteIf) tipoClienteLocal
					.findTipoClienteByQuery(parameterMap).iterator().next())
					.getId();
			parameterMap.remove("codigo");
			parameterMap.put("codigo", codigoTipoProveedor);
			tipoProveedorId = ((TipoClienteIf) tipoClienteLocal
					.findTipoClienteByQuery(parameterMap).iterator().next())
					.getId();
			parameterMap.remove("codigo");
			parameterMap.put("codigo", codigoTipoAmbos);
			tipoAmbosId = ((TipoClienteIf) tipoClienteLocal
					.findTipoClienteByQuery(parameterMap).iterator().next())
					.getId();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}

		if (idTipoCliente != 0L && idTipoCliente.compareTo(tipoAmbosId) != 0)
			queryString += " (e.tipoclienteId = " + idTipoCliente
					+ " or e.tipoclienteId = " + tipoAmbosId + ") and";

		if (idTipoCliente.compareTo(tipoAmbosId) == 0)
			queryString += " (e.tipoclienteId = " + tipoClienteId
					+ " or e.tipoclienteId = " + tipoProveedorId
					+ " or e.tipoclienteId = " + tipoAmbosId + ") and";

		queryString += " e.tipoclienteId = tc.id and tc.empresaId = "
				+ idEmpresa;

		if (aMap.size() > 0)
			queryString += " and " + where;

		queryString += " order by e.id";

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
		try {
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
					"Error en la consulta de Operador de Negocio !!");
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int findClienteByFilteredQuerySize(Map aMap,
			java.lang.Long idTipoCliente, java.lang.Long idEmpresa) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from ClienteEJB "
				+ objectName + ", TipoClienteEJB tc where ";
		String codigoTipoCliente = "CL", codigoTipoProveedor = "PR", codigoTipoAmbos = "AM";
		Long tipoClienteId = 0L, tipoProveedorId = 0L, tipoAmbosId = 0L;

		try {
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", idEmpresa);
			parameterMap.put("codigo", codigoTipoCliente);
			tipoClienteId = ((TipoClienteIf) tipoClienteLocal
					.findTipoClienteByQuery(parameterMap).iterator().next())
					.getId();
			parameterMap.remove("codigo");
			parameterMap.put("codigo", codigoTipoProveedor);
			tipoProveedorId = ((TipoClienteIf) tipoClienteLocal
					.findTipoClienteByQuery(parameterMap).iterator().next())
					.getId();
			parameterMap.remove("codigo");
			parameterMap.put("codigo", codigoTipoAmbos);
			tipoAmbosId = ((TipoClienteIf) tipoClienteLocal
					.findTipoClienteByQuery(parameterMap).iterator().next())
					.getId();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}

		if (idTipoCliente != 0L && idTipoCliente.compareTo(tipoAmbosId) != 0)
			queryString += " (e.tipoclienteId = " + idTipoCliente
					+ " or e.tipoclienteId = " + tipoAmbosId + ") and";

		if (idTipoCliente.compareTo(tipoAmbosId) == 0)
			queryString += " (e.tipoclienteId = " + tipoClienteId
					+ " or e.tipoclienteId = " + tipoProveedorId
					+ " or e.tipoclienteId = " + tipoAmbosId + ") and";

		queryString += " e.tipoclienteId = tc.id and tc.empresaId = "
				+ idEmpresa;

		if (aMap.size() > 0)
			queryString += " and " + where;

		queryString += " order by e.id";

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

	private DecimalFormat formatoSerial3 = new DecimalFormat("000");

	public String generarCodigoProveedor(Long empresaId) {
		String queryString = "SELECT pe FROM ParametroEmpresaEJB pe where pe.empresaId = :empresaId and codigo='SERIALPR'";
		Query query = manager.createQuery(queryString);
		query.setParameter("empresaId", empresaId);
		ParametroEmpresaIf parametroEmpresaIf = (ParametroEmpresaIf) query
				.getSingleResult();
		double last = Double.parseDouble(parametroEmpresaIf.getValor()) + 1;
		String ultimoCodigo = formatoSerial3.format(last);
		parametroEmpresaIf.setValor(ultimoCodigo);
		manager.merge(parametroEmpresaIf);
		return ultimoCodigo;
	}

	public void procesarCliente(ClienteIf model,
			List<ClienteZonaIf> modelClienteZonaList,
			List<ClienteRetencionIf> modelClienteRetencionList,
			List<ClienteOficinaIf> modelClienteOficinaList,
			Map modelClienteContactoMap, Map modelClienteIndicadorMap,
			boolean propagarMensaje) throws GenericBusinessException {
		try {
			int i = 0;
			TipoClienteIf tipoCliente = tipoClienteLocal.getTipoCliente(model.getTipoclienteId());
			if (getClienteByIdentificacionAndEmpresaId(model.getIdentificacion(), tipoCliente.getEmpresaId()) != null) {
				throw new GenericBusinessException("Operador con identificacion: " + model.getIdentificacion() + " ya existe");
			}

			ClienteIf cliente = registrarCliente(model);

			manager.persist(cliente);

			for (ClienteZonaIf modelClienteZona : modelClienteZonaList) {
				modelClienteZona.setClienteId(cliente.getPrimaryKey());
				ClienteZonaIf clienteZona = clienteZonaLocal.registrarClienteZona(modelClienteZona, true);
				manager.merge(clienteZona);
			}
			
			for (ClienteRetencionIf modelClienteRetencion : modelClienteRetencionList) {
				modelClienteRetencion.setClienteId(cliente.getPrimaryKey());
				ClienteRetencionIf clienteRetencion = clienteRetencionLocal.registrarClienteRetencion(modelClienteRetencion);
				manager.merge(clienteRetencion);
			}

			TipoClienteIf tipoClienteIf = tipoClienteLocal.getTipoCliente(cliente.getTipoclienteId());
			Long empresaId = tipoClienteIf.getEmpresaId();

			for (ClienteOficinaIf modelClienteOficina : modelClienteOficinaList) {
				/*if (modelClienteOficina.getEmail() != null && !modelClienteOficina.getEmail().equals("")) {
					Collection<ClienteOficinaIf> oficinas = clienteOficinaLocal.findClienteOficinaByEmailByEmpresaId(modelClienteOficina.getEmail(), empresaId);

					if (oficinas.size() > 0)
						throw new GenericBusinessException("Ya existe un cliente con el email " + modelClienteOficina.getEmail());

				}*/

				modelClienteOficina.setClienteId(cliente.getPrimaryKey());
				if (tipoCliente.getCodigo().equalsIgnoreCase("PR")) {
					// GENERAR CODIGO
					modelClienteOficina.setCodigoProveedorAuto(generarCodigoProveedor(tipoCliente.getEmpresaId()));
				}
				ClienteOficinaIf clienteOficina = clienteOficinaLocal.registrarClienteOficina(modelClienteOficina);
				clienteOficinaLocal.addClienteOficina(clienteOficina);
				List<ClienteContactoIf> modelClienteContactoList = (List<ClienteContactoIf>) modelClienteContactoMap.get(modelClienteOficina.getCodigo());

				if (modelClienteContactoList != null) {
					for (ClienteContactoIf modelClienteContacto : modelClienteContactoList) {
						modelClienteContacto.setClienteoficinaId(clienteOficina
								.getId());
						ClienteContactoIf clienteContacto = clienteContactoLocal
								.registrarClienteContacto(modelClienteContacto);
						clienteContactoLocal
								.addClienteContacto(clienteContacto);
					}
				}

				List<ClienteIndicadorIf> modelClienteIndicadorList = (List<ClienteIndicadorIf>) modelClienteIndicadorMap
						.get(modelClienteOficina.getCodigo());

				if (modelClienteIndicadorList != null) {
					for (ClienteIndicadorIf modelClienteIndicador : modelClienteIndicadorList) {
						modelClienteIndicador
								.setClienteOficinaId(clienteOficina.getId());
						ClienteIndicadorIf clienteIndicador = clienteIndicadorLocal
								.registrarClienteIndicador(modelClienteIndicador);
						clienteIndicadorLocal
								.addClienteIndicador(clienteIndicador);
					}
				}

				i++;
			}

			if (propagarMensaje) {
				clienteMessageLocal.setMessageDataProcess(model,
						modelClienteZonaList, modelClienteOficinaList,
						modelClienteContactoMap, modelClienteIndicadorMap);
				try {
					clienteMessageLocal.sendToPrincipalIfPosElseResto();
					clienteMessageLocal.clear();
				} catch (Exception e) {
					System.out.println("ERROR AL ENVIAR CLIENTE: ");
					clienteMessageLocal.clear();
				}
			}
			clienteMessageLocal.clear();
		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al guardar información en Operador Negocio.", e);
			e.printStackTrace();
			throw new GenericBusinessException(
					"Error al insertar información en Cliente");
		}
	}
	
	
	public void procesarClienteWeb(ClienteIf model,
			List<ClienteZonaIf> modelClienteZonaList,
			List<ClienteOficinaIf> modelClienteOficinaList,
			Map modelClienteContactoMap, Map modelClienteIndicadorMap,
			boolean propagarMensaje) throws GenericBusinessException {
		try {
			int i = 0;
			TipoClienteIf tipoCliente = tipoClienteLocal.getTipoCliente(model.getTipoclienteId());
			if (getClienteByIdentificacionAndEmpresaId(model.getIdentificacion(), tipoCliente.getEmpresaId()) != null) {
				throw new GenericBusinessException("Operador con identificacion: " + model.getIdentificacion() + " ya existe");
			}

			ClienteIf cliente = registrarCliente(model);

			manager.persist(cliente);

			for (ClienteZonaIf modelClienteZona : modelClienteZonaList) {
				modelClienteZona.setClienteId(cliente.getPrimaryKey());
				ClienteZonaIf clienteZona = clienteZonaLocal
						.registrarClienteZona(modelClienteZona, true);
				manager.merge(clienteZona);
			}

			TipoClienteIf tipoClienteIf = tipoClienteLocal
					.getTipoCliente(cliente.getTipoclienteId());
			Long empresaId = tipoClienteIf.getEmpresaId();

			for (ClienteOficinaIf modelClienteOficina : modelClienteOficinaList) {
				/*if (modelClienteOficina.getEmail() != null
						&& !modelClienteOficina.getEmail().equals("")) {
					Collection<ClienteOficinaIf> oficinas = clienteOficinaLocal
							.findClienteOficinaByEmailByEmpresaId(
									modelClienteOficina.getEmail(), empresaId);

					if (oficinas.size() > 0)
						throw new GenericBusinessException(
								"Ya existe un cliente con el email "
										+ modelClienteOficina.getEmail());

				}*/

				modelClienteOficina.setClienteId(cliente.getPrimaryKey());
				if (tipoCliente.getCodigo().equalsIgnoreCase("PR")) {
					// GENERAR CODIGO
					modelClienteOficina
							.setCodigoProveedorAuto(generarCodigoProveedor(tipoCliente
									.getEmpresaId()));
				}
				ClienteOficinaIf clienteOficina = clienteOficinaLocal
						.registrarClienteOficina(modelClienteOficina);

				clienteOficinaLocal.addClienteOficina(clienteOficina);

				List<ClienteContactoIf> modelClienteContactoList = (List<ClienteContactoIf>) modelClienteContactoMap
						.get(modelClienteOficina.getCodigo());

				if (modelClienteContactoList != null) {
					for (ClienteContactoIf modelClienteContacto : modelClienteContactoList) {
						modelClienteContacto.setClienteoficinaId(clienteOficina
								.getId());
						ClienteContactoIf clienteContacto = clienteContactoLocal
								.registrarClienteContacto(modelClienteContacto);
						clienteContactoLocal
								.addClienteContacto(clienteContacto);
					}
				}

				List<ClienteIndicadorIf> modelClienteIndicadorList = (List<ClienteIndicadorIf>) modelClienteIndicadorMap
						.get(modelClienteOficina.getCodigo());

				if (modelClienteIndicadorList != null) {
					for (ClienteIndicadorIf modelClienteIndicador : modelClienteIndicadorList) {
						modelClienteIndicador
								.setClienteOficinaId(clienteOficina.getId());
						ClienteIndicadorIf clienteIndicador = clienteIndicadorLocal
								.registrarClienteIndicador(modelClienteIndicador);
						clienteIndicadorLocal
								.addClienteIndicador(clienteIndicador);
					}
				}

				i++;
			}

			if (propagarMensaje) {
				clienteMessageLocal.setMessageDataProcess(model,
						modelClienteZonaList, modelClienteOficinaList,
						modelClienteContactoMap, modelClienteIndicadorMap);
				try {
					clienteMessageLocal.sendToPrincipalIfPosElseResto();
					clienteMessageLocal.clear();
				} catch (Exception e) {
					System.out.println("ERROR AL ENVIAR CLIENTE: ");
					clienteMessageLocal.clear();
				}
			}
			clienteMessageLocal.clear();
		} catch(GenericBusinessException e){
			log.error(e.getMessage(), e);
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			log.error("Error al guardar información en Operador Negocio.", e);
			e.printStackTrace();
			throw new GenericBusinessException(
					"Error al insertar información en Cliente");
		}
	}

	/***************************************************************************
	 * *******************************HELPERS
	 * 
	 * @throws GenericBusinessException
	 */
	private Object getFirstEntity(Collection collection)
			throws GenericBusinessException {
		List listaEntitys = (List) collection;
		if (listaEntitys != null && listaEntitys.size() > 0) {
			if (listaEntitys.size() == 1)
				return listaEntitys.get(0);
			else
				throw new GenericBusinessException(
						"Existe mas de un Operador de Negocio con la misma Identificacion ");
		}
		System.out.println("POSIBLE ERROR EN CAST");
		return null;
	}

	public ClienteIf getClienteByIdentificacionAndEmpresaId(String identificacion, Long empresaId) throws GenericBusinessException {
		try{
			return (ClienteIf) getFirstEntity(findClienteByIdentificacionAndEmpresaId(identificacion, empresaId));
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error al buscar Cliente por Identificacion !!");
		}
	}

	public ClienteOficinaIf getClienteOficina(String identificacionCliente,
			String codigoOficina) throws GenericBusinessException {
		try{
			String queryString = "select oficina "
					+ "from ClienteOficinaEJB oficina, ClienteEJB cliente "
					+ "where " + "oficina.clienteId = cliente.id and "
					+ "cliente.identificacion = :identificacionCliente and "
					+ "oficina.codigo = :codigoOficina";
			String orderByPart = "";
			orderByPart += " order by oficina.id";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			query.setParameter("identificacionCliente", identificacionCliente);
			query.setParameter("codigoOficina", codigoOficina);
			return (ClienteOficinaIf) getFirstEntity(query.getResultList());
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error al buscar Cliente Oficina !!");
		}
	}

	private ClienteOficinaIf getClienteOficina(Long idCliente,
			String codigoOficina) throws GenericBusinessException {

		String queryString = "select oficina "
				+ "from ClienteOficinaEJB oficina " + "where "
				+ "oficina.clienteId = :id and "
				+ "oficina.codigo = :codigoOficina";
		String orderByPart = "";
		orderByPart += " order by oficina.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("id", idCliente);
		query.setParameter("codigoOficina", codigoOficina);
		return (ClienteOficinaIf) getFirstEntity(query.getResultList());
	}

	private ClienteZonaIf getClienteZona(Long idCliente,
			String codigoClienteZona) throws GenericBusinessException {

		String queryString = "select zona from ClienteZonaEJB zona " + "where "
				+ "zona.clienteId = :id and " + "zona.codigo = :codigo";
		String orderByPart = "";
		orderByPart += " order by zona.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("id", idCliente);
		query.setParameter("codigo", codigoClienteZona);
		return (ClienteZonaIf) getFirstEntity(query.getResultList());
	}
	
	/*private ClienteRetencionIf getClienteRetencion(Long clienteId,
			Long sriAirId, Long sriIvaRetencionId) throws GenericBusinessException {

		String queryString = "select retencion from ClienteRetencionEJB retencion " + "where "
				+ "retencion.clienteId = :clienteId and " + "retencion.sriAirId = :sriAirId and retencion.sriIvaRetencionId = :sriIvaRetencionId";
		String orderByPart = "";
		orderByPart += " order by retencion.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("clienteId", clienteId);
		query.setParameter("sriAirId", sriAirId);
		query.setParameter("sriIvaRetencionId", sriIvaRetencionId);
		return (ClienteRetencionIf) getFirstEntity(query.getResultList());
	}*/
	
	private ClienteRetencionIf getClienteRetencionById(Long id) throws GenericBusinessException {

		String queryString = "select retencion from ClienteRetencionEJB retencion where retencion.id = :id";
		String orderByPart = "";
		orderByPart += " order by retencion.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("id", id);
		return (ClienteRetencionIf) getFirstEntity(query.getResultList());
	}

	private ClienteContactoIf getClienteContacto(Long idClienteOficina,
			String codigoClienteContacto) throws GenericBusinessException {

		String queryString = "select cc from ClienteContactoEJB cc " + "where "
				+ "cc.clienteoficinaId= :clienteOficinaId and "
				+ "cc.codigo = :codigo";
		String orderByPart = "";
		orderByPart += " order by cc.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("clienteOficinaId", idClienteOficina);
		query.setParameter("codigo", codigoClienteContacto);
		return (ClienteContactoIf) getFirstEntity(query.getResultList());
	}
	
	private ClienteIndicadorIf getClienteIndicador(Long idClienteOficina,
			String codigoClienteIndicador) throws GenericBusinessException {

		String queryString = "select ci from ClienteIndicadorEJB ci "
				+ "where " + "ci.clienteOficinaId= :clienteOficinaId and "
				+ "ci.codigo = :codigo";
		String orderByPart = "";
		orderByPart += " order by ci.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("clienteOficinaId", idClienteOficina);
		query.setParameter("codigo", codigoClienteIndicador);
		return (ClienteIndicadorIf) getFirstEntity(query.getResultList());
	}

	public void actualizarCliente(
			ClienteIf model,
			List<ClienteZonaIf> modelClienteZonaList,
			List<ClienteRetencionIf> modelClienteRetencionList,
			List<ClienteOficinaIf> modelClienteOficinaList,
			Map<String, Vector<ClienteContactoIf>> modelClienteContactoMap,
			Map<String, Vector<ClienteIndicadorIf>> modelClienteIndicadorMap,
			List<ClienteZonaIf> detalleZonaRemovidaClienteList,
			List<ClienteRetencionIf> detalleRetencionRemovidaClienteList,
			List<ClienteOficinaIf> detalleOficinaRemovidaClienteList,
			Map<String, Vector<ClienteContactoIf>> detalleContactoRemovidoClienteList,
			Map<String, Vector<ClienteIndicadorIf>> detalleIndicadorRemovidoClienteList,
			boolean propagarMensaje) throws GenericBusinessException {
		try {
			/*ClienteIf clienteInt = getClienteByIdentificacion(model
					.getIdentificacion());
			Long idCliente = clienteInt.getId();
			model.setId(idCliente);// INTERCAMBIO LOS IDS
			*/
			Long idCliente = model.getId();
			ClienteIf cliente = registrarCliente(model);
			//manager.merge(cliente);
			saveCliente(cliente);

			// ----------ELIMINACIONES----------------
			for (ClienteZonaIf modelClienteZonaRemovida : detalleZonaRemovidaClienteList) {
				try {
					ClienteZonaIf data = getClienteZona(idCliente, modelClienteZonaRemovida.getCodigo());
					clienteZonaLocal.deleteClienteZona(data.getId());
				} catch (Exception e) {
					throw new GenericBusinessException("Error al eliminar información en Cliente Zona: " + modelClienteZonaRemovida.getCodigo());
				}
			}
			
			for (ClienteRetencionIf modelClienteRetencionRemovida : detalleRetencionRemovidaClienteList) {
				try {
					ClienteRetencionIf data = getClienteRetencionById(modelClienteRetencionRemovida.getId());
					clienteRetencionLocal.deleteClienteRetencion(data.getId());
				} catch (Exception e) {
					throw new GenericBusinessException("Error al eliminar información en Cliente Retencion.");
				}
			}

			for (String codigoOficina : detalleContactoRemovidoClienteList
					.keySet()) {
				try {
					ClienteOficinaIf clienteOficinaIf = getClienteOficina(
							idCliente, codigoOficina);
					Vector<ClienteContactoIf> contactosCliente = detalleContactoRemovidoClienteList
							.get(codigoOficina);
					for (ClienteContactoIf cc : contactosCliente) {
						ClienteContactoIf clienteContactoIf = getClienteContacto(
								clienteOficinaIf.getId(), cc.getCodigo());
						if (clienteContactoIf != null)
							clienteContactoLocal
									.deleteClienteContacto(clienteContactoIf
											.getId());
					}
				} catch (Exception e) {
					throw new GenericBusinessException(
							"Error al eliminar Contacto de Cliente !!");
				}
			}

			/*
			 * for (ClienteContactoIf modelClienteContactoRemovido :
			 * detalleContactoRemovidoClienteList) { try {
			 * //getClienteContacto(idCliente, codigoClienteContacto);
			 * ClienteContactoEJB data = manager.find( ClienteContactoEJB.class,
			 * modelClienteContactoRemovido.getId()); manager.remove(data); }
			 * catch (Exception e) { ctx.setRollbackOnly(); log .error( "Error
			 * al eliminar información en Cliente_Contacto.", e); throw new
			 * GenericBusinessException( "Error al eliminar información en
			 * Cliente_Contacto, registro está siendo utilizado "); } }
			 */

			for (String codigoOficina : detalleIndicadorRemovidoClienteList
					.keySet()) {
				try {
					ClienteOficinaIf clienteOficinaIf = getClienteOficina(
							idCliente, codigoOficina);
					Vector<ClienteIndicadorIf> contactosCliente = detalleIndicadorRemovidoClienteList
							.get(codigoOficina);
					for (ClienteIndicadorIf ci : contactosCliente) {
						ClienteIndicadorIf clienteContactoIf = getClienteIndicador(
								clienteOficinaIf.getId(), ci.getCodigo());
						if (clienteContactoIf != null)
							clienteContactoLocal
									.deleteClienteContacto(clienteContactoIf
											.getId());
					}
				} catch (Exception e) {
					throw new GenericBusinessException(
							"Error al eliminar Indicador de Cliente !!");
				}
			}

			/*
			 * for (ClienteIndicadorIf modelClienteIndicadorRemovido :
			 * detalleIndicadorRemovidoClienteList) { try { ClienteIndicadorEJB
			 * data = manager.find( ClienteIndicadorEJB.class,
			 * modelClienteIndicadorRemovido.getId()); manager.remove(data); }
			 * catch (Exception e) { ctx.setRollbackOnly(); log .error( "Error
			 * al eliminar información en Cliente_Indicador.", e); throw new
			 * GenericBusinessException( "Error al eliminar información en
			 * Cliente_Indicador, registro está siendo utilizado "); } }
			 */

			for (ClienteOficinaIf modelClienteOficinaRemovida : detalleOficinaRemovidaClienteList) {
				try {
					ClienteOficinaIf clienteOficinaIf = getClienteOficina(
							idCliente, modelClienteOficinaRemovida.getCodigo());
					clienteOficinaLocal.deleteClienteOficina(clienteOficinaIf
							.getId());

				} catch (Exception e) {
					throw new GenericBusinessException(
							"Error al eliminar información en Cliente_Oficina, registro está siendo utilizado ");
				}
			}

			// -----------ACTUALIZACIONES-----------------
			
			//ZONAS
			for (ClienteZonaIf modelClienteZona : modelClienteZonaList) {
				ClienteZonaIf clienteZonaIf = getClienteZona(idCliente,	modelClienteZona.getCodigo());
				if (clienteZonaIf != null) {
					modelClienteZona.setId(clienteZonaIf.getId());
					ClienteZonaIf clienteZona = clienteZonaLocal.registrarClienteZona(modelClienteZona);
					clienteZonaLocal.saveClienteZona(clienteZona);
				} else {
					modelClienteZona.setId(null);
					ClienteZonaIf clienteZona = clienteZonaLocal.registrarClienteZona(modelClienteZona);
					clienteZona.setClienteId(idCliente);
					clienteZonaLocal.addClienteZona(clienteZona);
				}
			}
			
			//RETENCIONES
			for (ClienteRetencionIf modelClienteRetencion : modelClienteRetencionList) {
				if (modelClienteRetencion.getId() != null) {
					//ClienteRetencionIf clienteRetencionIf = getClienteRetencionById(modelClienteRetencion.getId());
					//modelClienteRetencion.setId(clienteRetencionIf.getId());
					ClienteRetencionIf clienteRetencion = clienteRetencionLocal.registrarClienteRetencion(modelClienteRetencion);
					clienteRetencionLocal.saveClienteRetencion(clienteRetencion);
				} else {
					modelClienteRetencion.setId(null);
					ClienteRetencionIf clienteRetencion = clienteRetencionLocal.registrarClienteRetencion(modelClienteRetencion);
					clienteRetencion.setClienteId(idCliente);
					clienteRetencionLocal.addClienteRetencion(clienteRetencion);
				}
			}

			TipoClienteIf tipoClienteIf = tipoClienteLocal
					.getTipoCliente(cliente.getTipoclienteId());
			Long empresaId = tipoClienteIf.getEmpresaId();
			for (ClienteOficinaIf modelClienteOficina : modelClienteOficinaList) {

				/*if (modelClienteOficina.getEmail() != null
						&& !modelClienteOficina.getEmail().equals("")) {
					Collection<ClienteOficinaIf> oficinas = clienteOficinaLocal
							.findClienteOficinaByEmailByEmpresaId(
									modelClienteOficina.getEmail(), empresaId);

					if (oficinas.size() > 0) {
						if (oficinas.size() == 1) {
							ClienteOficinaIf co = oficinas.iterator().next();
							if (!co.getId().equals(modelClienteOficina.getId()))
								throw new GenericBusinessException(
										"Ya existe un cliente con el email "
												+ modelClienteOficina
														.getEmail());
						} else
							throw new GenericBusinessException(
									"Existe mas de un cliente con el email "
											+ modelClienteOficina.getEmail());
					}
				}*/

				ClienteOficinaIf clienteOficinaIf = getClienteOficina(
						idCliente, modelClienteOficina.getCodigo());
				ClienteOficinaIf clienteOficina = clienteOficinaLocal
						.registrarClienteOficina(modelClienteOficina);
				if (clienteOficinaIf != null) {
					clienteOficina.setId(clienteOficinaIf.getId());
					clienteOficinaLocal.saveClienteOficina(clienteOficina);
				} else {
					clienteOficina.setClienteId(idCliente);
					clienteOficina = clienteOficinaLocal.addClienteOficina(clienteOficina);
				}

				Collection<ClienteContactoIf> modelClienteContactoList = modelClienteContactoMap
						.get(clienteOficina.getCodigo());

				if (modelClienteContactoList != null) {

					for (ClienteContactoIf modelClienteContacto : modelClienteContactoList) {

						ClienteContactoIf clienteContactoIf = getClienteContacto(
								clienteOficina.getId(), modelClienteContacto
										.getCodigo());
						if (clienteContactoIf != null) {
							modelClienteContacto.setId(clienteContactoIf
									.getId());
							ClienteContactoIf clienteContacto = clienteContactoLocal
									.registrarClienteContacto(modelClienteContacto);
							clienteContactoLocal
									.saveClienteContacto(clienteContacto);
						} else {
							modelClienteContacto.setId(null);
							modelClienteContacto.setClienteoficinaId(clienteOficina
									.getId());
							ClienteContactoIf clienteContacto = clienteContactoLocal
									.registrarClienteContacto(modelClienteContacto);

							clienteContactoLocal
									.addClienteContacto(clienteContacto);
						}
					}
				}

				Collection<ClienteIndicadorIf> modelClienteIndicadorList = modelClienteIndicadorMap
						.get(clienteOficina.getCodigo());

				if (modelClienteIndicadorList != null) {

					for (ClienteIndicadorIf modelClienteIndicador : modelClienteIndicadorList) {

						ClienteIndicadorIf clienteIndicadorIf = getClienteIndicador(
								clienteOficina.getId(), modelClienteIndicador
										.getCodigo());
						if (clienteIndicadorIf != null) {
							modelClienteIndicador.setId(clienteIndicadorIf
									.getId());
							ClienteIndicadorIf clienteIndicador = clienteIndicadorLocal
									.registrarClienteIndicador(modelClienteIndicador);
							clienteIndicadorLocal
									.saveClienteIndicador(clienteIndicador);
						} else {
							modelClienteIndicador.setId(null);
							ClienteIndicadorIf clienteIndicador = clienteIndicadorLocal
									.registrarClienteIndicador(modelClienteIndicador);
							clienteIndicador.setClienteOficinaId(clienteOficina
									.getId());
							clienteIndicadorLocal
									.addClienteIndicador(clienteIndicador);
						}
					}
				}

			}
			
			
			if (propagarMensaje) {
				clienteMessageLocal.setMessageDataUpdate(model,
						modelClienteZonaList, modelClienteOficinaList,
						modelClienteContactoMap, modelClienteIndicadorMap,
						detalleZonaRemovidaClienteList,
						detalleOficinaRemovidaClienteList,
						detalleContactoRemovidoClienteList,
						detalleIndicadorRemovidoClienteList);
				try {
					clienteMessageLocal.sendToPrincipalIfPosElseResto();
					clienteMessageLocal.clear();
				} catch (Exception e) {
					System.out.println("ERROR AL ENVIAR CLIENTE: ");
					clienteMessageLocal.clear();
				}
			}
			clienteMessageLocal.clear();
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			if (e instanceof GenericBusinessException) {
				log.error(e.getMessage(), e);
				throw new GenericBusinessException(e.getMessage());
			}
			log.error("Error al actualizar informacion en Operador de negocio",
					e);
			throw new GenericBusinessException(
					"Se ha producido un error al actualizar Operador de Negocio");
		}
	}

	// public void eliminarCliente(Long clienteId, boolean propagarMensaje)
	// throws GenericBusinessException {
	public void eliminarCliente(String identificacion, Long empresaId, boolean propagarMensaje)
			throws GenericBusinessException {
		try {			
			ClienteIf clienteIf = getClienteByIdentificacionAndEmpresaId(identificacion, empresaId);
			if (clienteIf == null)
				throw new GenericBusinessException(
						"No existe Operador de Negocio con Identificacion \""
								+ identificacion + "\"");
			else {

				Collection<ClienteZonaIf> zonas = clienteZonaLocal
						.findClienteZonaByClienteId(clienteIf.getId());
				for (ClienteZonaIf zona : zonas) {
					clienteZonaLocal.deleteClienteZona(zona.getId());
				}
				
				Collection<ClienteRetencionIf> retenciones = clienteRetencionLocal
				.findClienteRetencionByClienteId(clienteIf.getId());
				for (ClienteRetencionIf retencion : retenciones) {
					clienteRetencionLocal.deleteClienteRetencion(retencion.getId());
				}

				Collection<ClienteOficinaIf> oficinas = clienteOficinaLocal
						.findClienteOficinaByClienteId(clienteIf.getId());
				for (ClienteOficinaIf oficina : oficinas) {

					Collection<ClienteContactoIf> contactos = clienteContactoLocal
							.findClienteContactoByClienteoficinaId(oficina
									.getId());
					for (ClienteContactoIf contacto : contactos) {
						clienteContactoLocal.deleteClienteContacto(contacto
								.getId());
					}

					Collection<ClienteIndicadorIf> indicadores = clienteIndicadorLocal
							.findClienteIndicadorByClienteOficinaId(oficina
									.getId());
					for (ClienteIndicadorIf indicador : indicadores) {
						clienteIndicadorLocal.deleteClienteIndicador(indicador
								.getId());
					}

					try {
						clienteOficinaLocal.deleteClienteOficina(oficina
								.getId());
					} catch (Exception e) {
						throw new GenericBusinessException(
								"Error al aliminar CLiente Oficina con codigo \""
										+ oficina.getCodigo() + "\"");
					}
				}
				
				//Elimino Cliente
				deleteCliente(clienteIf.getId());

				if (propagarMensaje) {
					clienteMessageLocal.setMessageDataDelete(identificacion);
					try {
						clienteMessageLocal.sendToPrincipalIfPosElseResto();
						clienteMessageLocal.clear();
					} catch (Exception e) {
						System.out.println("ERROR AL ENVIAR CLIENTE: ");
						clienteMessageLocal.clear();
					}
				}
				clienteMessageLocal.clear();
			}

			/*
			 * ClienteEJB data = manager.find(ClienteEJB.class, clienteId);
			 * eliminarReferencias(clienteId); manager.remove(data);
			 * manager.flush();
			 */

		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al eliminar información en ClienteEJB.", e);
			if (e instanceof GenericBusinessException)
				throw new GenericBusinessException(e.getMessage());
			throw new GenericBusinessException(
					"Error al eliminar información en Cliente");
		}
	}

	private ClienteEJB registrarCliente(ClienteIf model) {
		ClienteEJB cliente = new ClienteEJB();

		cliente.setId(model.getId());
		cliente.setTipoidentificacionId(model.getTipoidentificacionId());
		cliente.setIdentificacion(model.getIdentificacion());
		cliente.setNombreLegal(model.getNombreLegal());
		cliente.setRazonSocial(model.getRazonSocial());
		cliente.setRepresentante(model.getRepresentante());
		cliente.setCorporacionId(model.getCorporacionId());
		cliente.setFechaCreacion(model.getFechaCreacion());
		cliente.setEstado(model.getEstado());
		cliente.setTipoclienteId(model.getTipoclienteId());
		cliente.setTipoproveedorId(model.getTipoproveedorId());
		cliente.setTiponegocioId(model.getTiponegocioId());
		cliente.setCuentaId(model.getCuentaId());
		cliente.setObservacion(model.getObservacion());
		cliente.setUsuariofinal(model.getUsuariofinal());
		cliente.setContribuyenteEspecial(model.getContribuyenteEspecial());
		cliente.setTipoPersona(model.getTipoPersona());
		cliente.setLlevaContabilidad(model.getLlevaContabilidad());
		cliente.setInformacionAdc(model.getInformacionAdc());
		cliente.setRequiereSap(model.getRequiereSap());
		cliente.setBancoId(model.getBancoId());
		cliente.setTipoCuenta(model.getTipoCuenta());
		cliente.setNumeroCuenta(model.getNumeroCuenta());	
		
		return cliente;
	}

	private void eliminarReferencias(Long clienteId)
			throws GenericBusinessException {
		Collection<ClienteZonaIf> modelClienteZonaList = clienteZonaLocal
				.findClienteZonaByClienteId(clienteId);
		Collection<ClienteOficinaIf> modelClienteOficinaList = clienteOficinaLocal
				.findClienteOficinaByClienteId(clienteId);

		for (ClienteZonaIf modelClienteZona : modelClienteZonaList) {
			manager.remove(modelClienteZona);
		}

		for (ClienteOficinaIf modelClienteOficina : modelClienteOficinaList) {
			Collection<ClienteContactoIf> modelClienteContactoList = clienteContactoLocal
					.findClienteContactoByClienteoficinaId(modelClienteOficina
							.getId());
			Collection<ClienteIndicadorIf> modelClienteIndicadorList = clienteIndicadorLocal
					.findClienteIndicadorByClienteOficinaId(modelClienteOficina
							.getId());

			for (ClienteContactoIf modelClienteContacto : modelClienteContactoList) {
				manager.remove(modelClienteContacto);
			}

			for (ClienteIndicadorIf modelClienteIndicador : modelClienteIndicadorList) {
				manager.remove(modelClienteIndicador);
			}
			manager.remove(modelClienteOficina);
		}
	}
	
	public Collection findClienteByPlanMedioId(Long planMedioId) throws GenericBusinessException {
		//SELECT C.* FROM PLAN_MEDIO PM, ORDEN_TRABAJO_DETALLE ODT, ORDEN_TRABAJO OT, CLIENTE_OFICINA CO, CLIENTE C
		//WHERE PM.`ORDEN_TRABAJO_DETALLE_ID` = ODT.`ID` AND ODT.`ORDEN_ID` = OT.`ID` AND OT.`CLIENTEOFICINA_ID` = CO.`ID` AND CO.`CLIENTE_ID` = C.`ID` AND PM.ID = 1402
		String queryString = 	"select c from PlanMedioEJB pm, OrdenTrabajoDetalleEJB otd, OrdenTrabajoEJB ot, ClienteOficinaEJB co, ClienteEJB c " +
								"where pm.ordenTrabajoDetalleId = otd.id and otd.ordenId = ot.id and ot.clienteoficinaId = co.id and co.clienteId = c.id and pm.id = :planMedioId";
		Query query = manager.createQuery(queryString);
		query.setParameter("planMedioId", planMedioId);
		return query.getResultList();
	}
}
