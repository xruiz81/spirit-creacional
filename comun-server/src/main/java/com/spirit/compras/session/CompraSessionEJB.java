package com.spirit.compras.session;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.bpm.compras.exception.ComprasBpmException;
import com.spirit.cartera.entity.CarteraDetalleData;
import com.spirit.cartera.entity.CarteraDetalleEJB;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraEJB;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.session.CarteraDetalleSessionLocal;
import com.spirit.compras.entity.CompraDetalleEJB;
import com.spirit.compras.entity.CompraDetalleGastoIf;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraEJB;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionEJB;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.compras.entity.OrdenAsociadaEJB;
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.compras.gastos.CompraDetalleGastoClase;
import com.spirit.compras.gastos.CompraGastoClase;
import com.spirit.compras.handler.CompraAsientoAutomaticoHandlerLocal;
import com.spirit.compras.handler.GastoAsientoAutomaticoHandlerLocal;
import com.spirit.compras.handler.OrderData;
import com.spirit.compras.session.generated._CompraSession;
import com.spirit.contabilidad.entity.AsientoDetalleData;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaEntidadIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.PlantillaIf;
import com.spirit.contabilidad.session.AsientoDetalleSessionLocal;
import com.spirit.contabilidad.session.AsientoSessionLocal;
import com.spirit.contabilidad.session.CuentaEntidadSessionLocal;
import com.spirit.contabilidad.session.CuentaSessionLocal;
import com.spirit.contabilidad.session.EventoContableSessionLocal;
import com.spirit.contabilidad.session.PlanCuentaSessionLocal;
import com.spirit.contabilidad.session.PlantillaSessionLocal;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.ClienteOficinaSessionLocal;
import com.spirit.crm.session.ClienteSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ServidorIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.OficinaSessionLocal;
import com.spirit.general.session.ServidorSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.GenericoSessionLocal;
import com.spirit.inventario.session.MovimientoSessionLocal;
import com.spirit.inventario.session.ProductoSessionLocal;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.session.OrdenMedioSessionLocal;
import com.spirit.medios.session.PresupuestoSessionLocal;
import com.spirit.server.QueryBuilder;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.sri.session.SriAirSessionLocal;
import com.spirit.sri.session.SriIvaRetencionSessionLocal;

/**
 * The <code>CompraSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:13 $
 *
 */
@Stateless
public class CompraSessionEJB extends _CompraSession implements CompraSessionRemote,CompraSessionLocal  {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	@EJB private DocumentoSessionLocal documentoLocal; 
	@EJB private OrdenCompraSessionLocal ordenCompraLocal;
	@EJB private OrdenAsociadaSessionLocal ordenAsociadaSessionLocal;
	//ADD 19 JULIO
	@EJB private OrdenMedioSessionLocal ordenMedioLocal;
	@EJB private PresupuestoSessionLocal presupuestoLocal; 
	//END 19 JULIO
	@EJB private TipoDocumentoSessionLocal tipoDocumentoLocal;
	@EJB private EmpresaSessionLocal empresaLocal;
	@EJB private OficinaSessionLocal oficinaLocal;
	@EJB private ServidorSessionLocal servidorLocal;
	@EJB private SolicitudCompraSessionLocal solicitudCompraLocal;
	@EJB private CompraDetalleSessionLocal compraDetalleLocal;
	@EJB private AsientoSessionLocal asientoLocal;
	@EJB private PlanCuentaSessionLocal planCuentaLocal;
	@EJB private EventoContableSessionLocal eventoContableLocal;
	@EJB private PlantillaSessionLocal plantillaLocal;
	@EJB private CuentaSessionLocal cuentaLocal;
	@EJB private CuentaEntidadSessionLocal cuentaEntidadLocal;
	@EJB private CarteraDetalleSessionLocal carteraDetalleLocal;
	@EJB private CompraAsientoAutomaticoHandlerLocal compraAsientoAutomaticoHandlerLocal;
	@EJB private GastoAsientoAutomaticoHandlerLocal gastoAsientoAutomaticoHandlerLocal;
	@EJB private CompraRetencionSessionLocal compraRetencionLocal;
	@EJB private CompraGastoSessionLocal compraGastoLocal;
	@EJB private AsientoDetalleSessionLocal asientoDetalleLocal;
	@EJB private ClienteSessionLocal clienteLocal;
	@EJB private ClienteOficinaSessionLocal clienteOficinaLocal;
	@EJB private UtilitariosSessionLocal utilitariosLocal;
	//@EJB private ProcesoPrincipalCompraLocal procesoPrincipalCompraLocal;
	@EJB private SriAirSessionLocal sriAirLocal;
	@EJB private SriIvaRetencionSessionLocal sriIvaRetencionLocal;
	@EJB private CompraRetencionSessionLocal compraRetencionSessionLocal;
	@EJB private MovimientoSessionLocal movimientoSessionLocal;
	@EJB private ProductoSessionLocal productoLocal;
	@EJB private GenericoSessionLocal genericoLocal;
	@EJB private OrdenCompraSessionLocal ordenCompraSessionLocal;
	@EJB private OrdenMedioSessionLocal ordenMedioSessionLocal;
	
	//@EJB
	//private MovimientoSessionLocal movimientoLocal;
	
	/**
	 * The logger object.
	 */
	private static final String ESTADO_INACTIVA = "I";
	private static String REDONDEO_TOTAL = "T";
	private static String REDONDEO_PARCIAL = "P";
	DecimalFormat formatoSerial = new DecimalFormat("00000");
	boolean nuevaCodificacionActiva = true;
	
	//private DecimalFormat formatoSerial = new DecimalFormat("00000");
	
	@Resource private SessionContext ctx;
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCompraByQueryByFechaInicioAndByFechaFin(Map aMap, Date fechaInicio, Date fechaFin) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String fechas = "and e.fecha >= :fechaInicio and e.fecha <= :fechaFin";
		String queryString = "from CompraEJB " + objectName + " where " + where + fechas;
		queryString += " order by e.codigo desc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		
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
	public java.util.Collection findCompraByFechaInicioByFechaFinByOficinaIdAndByOrdenCompraId(Date fechaInicio, Date fechaFin, Long idOficina, Long idOrdenCompra) {
		//select * from orden_compra oc where oc.FECHA between TO_Date('2007-11-01','YYYY-MM-DD') and TO_Date('2007-11-30','YYYY-MM-DD')
		String queryString = "select distinct c from CompraEJB c where c.oficinaId = " + idOficina + " and c.ordencompraId = " + idOrdenCompra + " and c.fecha >= :fechaInicio and c.fecha <= :fechaFin";
		queryString += " order by c.codigo desc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCompraByFechaInicioByFechaFinByOficinaIdAndByProveedorId(Date fechaInicio, Date fechaFin, Long idOficina, Long idProveedor) {
		//select * from orden_compra oc where oc.FECHA between TO_Date('2007-11-01','YYYY-MM-DD') and TO_Date('2007-11-30','YYYY-MM-DD')
		String queryString = "select distinct c from CompraEJB c where c.oficinaId = " + idOficina + " and c.proveedorId = " + idProveedor + " and c.fecha >= :fechaInicio and c.fecha <= :fechaFin";
		queryString += " order by c.codigo desc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCompraByQueryAndEmpresaId(Map aMap, Long idEmpresa) throws GenericBusinessException {
		String objectName = "c";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct c, cl from CompraEJB " + objectName + ", CarteraEJB ca, OficinaEJB o, EmpresaEJB e, ClienteEJB cl, ClienteOficinaEJB clo where " + where 
		//+ " and c.tipodocumentoId = ca.tipodocumentoId and c.id = ca.referenciaId and c.proveedorId = clo.id and clo.clienteId = cl.id and c.oficinaId = o.id and o.empresaId = e.id and e.id = " + idEmpresa + " order by c.id asc";
		+ " and c.tipodocumentoId = ca.tipodocumentoId and c.id = ca.referenciaId and c.proveedorId = clo.id and clo.clienteId = cl.id and c.oficinaId = o.id and o.empresaId = e.id and e.id = " + idEmpresa + " and ca.saldo > 0 order by c.fecha asc, cl.nombreLegal asc, c.preimpreso asc";
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
	public Collection findCompraFixByQueryAndEmpresaId(Map aMap, Long idEmpresa) throws GenericBusinessException {
		String objectName = "c";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct c, cl from CompraEJB " + objectName + ", CarteraEJB ca, OficinaEJB o, EmpresaEJB e, ClienteEJB cl, ClienteOficinaEJB clo where " + where 
		//+ " and c.tipodocumentoId = ca.tipodocumentoId and c.id = ca.referenciaId and c.proveedorId = clo.id and clo.clienteId = cl.id and c.oficinaId = o.id and o.empresaId = e.id and e.id = " + idEmpresa + " order by c.id asc";
		+ " and c.tipodocumentoId = ca.tipodocumentoId and c.id = ca.referenciaId and c.proveedorId = clo.id and clo.clienteId = cl.id and c.oficinaId = o.id and o.empresaId = e.id and e.id = " + idEmpresa + 
		" order by c.fecha asc, cl.nombreLegal asc, c.preimpreso asc";
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
	public Collection findCompraByEmpresaId(Long idEmpresa) throws GenericBusinessException {
		String queryString = "select distinct c from CompraEJB c, OficinaEJB o, EmpresaEJB e where c.oficinaId = o.id and o.empresaId = e.id and e.id = " + idEmpresa + " order by c.codigo";
		Query query = manager.createQuery(queryString);
		return query.getResultList();  
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findComprasPorPagarByEmpresaId(Long empresaId, Boolean diferido) throws GenericBusinessException {
		//select distinct c.* from compra c, cartera ca, tipo_documento td where ca.CODIGO like 'CRE-COM%' and ca.REFERENCIA_ID = c.id and ca.SALDO > 0 and c.TIPODOCUMENTO_ID = td.id and td.EMPRESA_ID = 1
		/*String codigo = "";
		
		try {
			EmpresaIf empresa = empresaLocal.getEmpresa(empresaId);
			codigo = empresa.getCodigo() + "-COM";
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}*/
		
		//String queryString = "select distinct c from CompraEJB c, CarteraEJB ca, TipoDocumentoEJB td where ca.codigo like '" + codigo + "%' and ca.referenciaId = c.id and c.tipodocumentoId = td.id and td.empresaId = " + empresaId;
		String queryString = "select distinct c from CompraEJB c, CarteraEJB ca, TipoDocumentoEJB td where ca.referenciaId = c.id and c.tipodocumentoId = td.id and td.empresaId = " + empresaId;
		
		if(!diferido)
			queryString += ("and ca.saldo > 0 " );
		
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public java.util.Collection getCompraByMapFechaInicioFechaFin(Map aMap,Date fechaInicio,Date fechaFin, java.lang.Long idEmpresa) 
	throws com.spirit.exception.GenericBusinessException{
		try{
			String objectName = "e";
			String queryString = "select distinct e from CompraEJB " + objectName + ", TipoDocumentoEJB td where " ;
	
			queryString += " e.tipodocumentoId = td.id and td.empresaId = " + idEmpresa ;
			 if (fechaInicio!=null && fechaFin!=null){
				 queryString += " and e.fecha >= :fechaInicio and e.fecha <= :fechaFin";
			 }
			 
			 if (aMap!=null && aMap.size()>0) {
				 String where = QueryBuilder.buildWhere(aMap, objectName);
				 queryString += (" and  "+where);
			 }
			 
			 queryString += " order by e.codigo asc";
			
			Query query = manager.createQuery(queryString);
			 
			if (aMap!=null){
				Iterator it = aMap.keySet().iterator();
				while (it.hasNext()) {
					String propertyKey = (String) it.next();
					Object property = aMap.get(propertyKey);
					query.setParameter(propertyKey, property);
				}
			}
			
			query.setParameter("fechaInicio",fechaInicio);
			query.setParameter("fechaFin",fechaFin);
			return query.getResultList();
		} catch(Exception e){
			throw new GenericBusinessException("Se ha producido un error al consultar Compras por fecha ");
		}
	}
	
	public java.util.Collection getCompraByQuery(Map aMap, java.lang.Long idEmpresa) 
	throws com.spirit.exception.GenericBusinessException{
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e from CompraEJB " + objectName + ", TipoDocumentoEJB td where " + where;

		queryString += " and e.tipodocumentoId = td.id and td.empresaId = " + idEmpresa + " order by e.codigo asc";
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
	public java.util.Collection findCompraByQueryList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa) {
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e from CompraEJB " + objectName + ", TipoDocumentoEJB td where " + where;
		queryString += " and e.tipodocumentoId = td.id and td.empresaId = " + idEmpresa + " order by e.codigo desc";
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
	
	
	public Collection<Long> getCompraByQueryListSizeReestriccionStartEnd(Long idEmpresa) throws GenericBusinessException{
		String objectName = "e";	
		String queryString = "select distinct fd.compraId from FacturaDetalleEJB fd, FacturaEJB fa, " +
				"OficinaEJB o where fd.facturaId = fa.id and fa.oficinaId = o.id and o.empresaId = " + idEmpresa + " and fa.estado = 'C' and fd.compraId is not null ";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	 
	
	  
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection getCompraByQueryListStartEnd(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa,Collection<Long> notin) {
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e from CompraEJB " + objectName + ", TipoDocumentoEJB td where " + where;
		queryString += " and e.tipodocumentoId = td.id and td.empresaId = " + idEmpresa;

		if ( notin.size() > 0 ){
			queryString += " and e.id not in (";
			for ( Long codigoRubroFondoReserva : notin ){
				queryString += ("'"+codigoRubroFondoReserva+"',");
			}
			queryString = queryString.substring(0,queryString.length()-1);
			queryString += " )";
			
			//order by e.codigo asc
		}
		
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
	public java.util.Collection getCompraByQueryList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa) {
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e from CompraEJB " + objectName + ", TipoDocumentoEJB td where " + where;
		queryString += " and e.tipodocumentoId = td.id and td.empresaId = " + idEmpresa;
		queryString += " and e.id not in (select distinct fd.compraId from FacturaDetalleEJB fd, FacturaEJB fa, " +
		"OficinaEJB o where fd.facturaId = fa.id and fa.oficinaId = o.id and o.empresaId = " + idEmpresa + " and fa.estado = 'C' and fd.compraId is not null) order by e.codigo desc";

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
	public int findCompraByQueryListSize(Map aMap, java.lang.Long idEmpresa) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from CompraEJB " + objectName + ", TipoDocumentoEJB td where " + where;
		queryString += " and e.tipodocumentoId = td.id and td.empresaId = " + idEmpresa;
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
	public int getCompraByQueryListSize(Map aMap, java.lang.Long idEmpresa) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from CompraEJB " 
			+ objectName + ", TipoDocumentoEJB td where " + where;
		
		queryString += " and e.tipodocumentoId = td.id and td.empresaId = " + idEmpresa;
		queryString += " and e.id not in (select distinct fd.compraId from FacturaDetalleEJB fd, FacturaEJB fa, " +
				"OficinaEJB o where fd.facturaId = fa.id and fa.oficinaId = o.id and o.empresaId = " + idEmpresa + " and fa.estado = 'C' and fd.compraId is not null)";
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
	public java.util.Collection getCompraByMapList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		Query query = getCompraByMapList(aMap, idEmpresa);
		
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getCompraByMapSize(Map aMap, java.lang.Long idEmpresa) {

		Query query = getCompraByMapList(aMap, idEmpresa);
		
		List countQueryResult = query.getResultList();
		Long countResult = Long.valueOf(countQueryResult.size());
		return countResult.intValue();
	}
	
	private Query getCompraByMapList(Map aMap, java.lang.Long idEmpresa) {
		Long idOrdenCompra = (Long)aMap.get("idOrdenCompra");
		String codigo = (String) aMap.get("codigo");
		String descripcionCliente = (String) aMap.get("descripcionCliente");
		String observacionCompra = (String) aMap.get("observacionCompra");
		String estado = (String) aMap.get("estado");
		String queryString = "select c from CompraEJB c, ClienteOficinaEJB co, TipoDocumentoEJB td ";
		queryString += (" where c.proveedorId = co.id and c.tipodocumentoId = td.id and td.empresaId = "+idEmpresa);
		queryString += (" and c.codigo like '"+ codigo +"'");
		if (idOrdenCompra != null)
			queryString += (" and c.ordencompraId = "+idOrdenCompra);
		if ( descripcionCliente != null )
			queryString += (" and co.descripcion like '"+descripcionCliente+"'");
		if ( observacionCompra != null )
			queryString += (" and c.observacion like '"+observacionCompra+"'");
		if (estado != null)
			queryString += (" and c.estado = '"+estado+"'");

		Query query = manager.createQuery(queryString);
		return query;
	}
	
	private Map<Long, SriAirIf> getAirs() throws GenericBusinessException{
		Map<String, Object> mapaConsultaAir = new HashMap<String, Object>();
		mapaConsultaAir.put("porcentaje", BigDecimal.ZERO);
		Collection<SriAirIf> airs = sriAirLocal.findSriAirByQueryAndFecha(
				mapaConsultaAir, new Date(new java.util.Date().getTime()));
		Map<Long, SriAirIf> mapaAir =  new HashMap<Long, SriAirIf>();
		for ( SriAirIf sriAirIf : airs ){
			mapaAir.put(sriAirIf.getId(), sriAirIf);
		}
		return mapaAir;
	}
	
	/*private Map<Long, SriIvaRetencionIf> getIvaRetenciones() throws GenericBusinessException{
		Map<String, Object> mapaConsultaIvaRetencion = new HashMap<String, Object>();
		mapaConsultaIvaRetencion.put("porcentaje", BigDecimal.ZERO);
		Collection<SriIvaRetencionIf> airs = sriIvaRetencionLocal.findSriIvaRetencionByQueryAndFecha(mapaConsultaIvaRetencion, new Date(new java.util.Date().getTime()));
		Map<Long, SriIvaRetencionIf> mapaIvaRetencion =  new HashMap<Long, SriIvaRetencionIf>();
		for ( SriIvaRetencionIf sriIvaRetencionIf : airs ){
			mapaIvaRetencion.put(sriIvaRetencionIf.getId(), sriIvaRetencionIf);
		}
		return mapaIvaRetencion;
	}*/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CompraIf procesarCompra(CompraIf model, List<CompraDetalleIf> modelDetalleList, Long idEmpresa, 
			Long idOficina, long idTarea, Vector<CompraRetencionIf> listaRetenciones, Long tipoDocumentoIfId, 
			UsuarioIf usuario,CompraGastoClase compraGastoClase, Vector<OrderData> ordenesAsociadasVector) throws GenericBusinessException {
		
		CompraEJB compra;
		Vector<CompraDetalleIf> compraDetalleColeccion = new Vector<CompraDetalleIf>(); 
		Map<Long, SriAirIf> mapaAir = getAirs();
		//Map<Long, SriIvaRetencionIf> mapaIvaRetencion = getIvaRetenciones();
		boolean crearRetencionCero = true;
		Long airSeleccionadoId = null;
		//Long ivaRetencionSeleccionadoId = null;
		try {
			if ( verificarPreimpreso(model.getProveedorId(),model.getPreimpreso(),model.getAutorizacion()) ){
				Collection<CompraIf> compras = findCompraByPreimpreso(model.getPreimpreso());
				CompraIf compraTemp = compras.iterator().next();
				throw new GenericBusinessException("Preimpreso no dispobible. Preimpreso utilizado en compra con código " + compraTemp.getCodigo());
			}
			
			int codigoCompra = getCodigoCompra(model, idEmpresa);
			model.setCodigo(model.getCodigo() + formatoSerial.format(codigoCompra));
			compra = registrarCompra(model);
			manager.persist(compra);
			compraGastoClase.setCompraId(compra.getPrimaryKey());
			
			//TODO: Actualizar Orden de Compra, cambiar el estado de la misma
			for (CompraDetalleIf modelDetalle : modelDetalleList) {
				modelDetalle.setCompraId(compra.getPrimaryKey());
				CompraDetalleIf compraDetalle = registrarCompraDetalle(modelDetalle);
				//manager.merge(compraDetalle);
				compraDetalle = compraDetalleLocal.addCompraDetalle(compraDetalle);
				compraDetalleColeccion.add(compraDetalle);
				Long compraDetalleId = compraDetalle.getPrimaryKey()!= null ? compraDetalle.getPrimaryKey() : compraDetalle.getId();
				actualizarCompraDetalleIdEnDetalleGasto(compraGastoClase, modelDetalle,compraDetalleId);
				if ( crearRetencionCero && !mapaAir.containsKey(modelDetalle.getIdSriAir()) && !compra.getEstado().equals(ESTADO_INACTIVA)){
					crearRetencionCero = false;
				}
				/*if ( crearRetencionCero && !mapaIvaRetencion.containsKey(modelDetalle.getSriIvaRetencionId()) ){
					crearRetencionCero = false;
				}*/
				airSeleccionadoId = modelDetalle.getIdSriAir();
				//ivaRetencionSeleccionadoId = modelDetalle.getSriIvaRetencionId();
			}
						
			ClienteIf cliente = null;
			
			for (int i=0; i<ordenesAsociadasVector.size(); i++) {
				OrderData orden = ordenesAsociadasVector.get(i);
				OrdenAsociadaEJB ordenAsociada = new OrdenAsociadaEJB();
				ordenAsociada.setCompraId(compra.getPrimaryKey());
				ordenAsociada.setTipoOrden(orden.getOrderType());
				if (orden.getOrderType().equals("OC")) {
					ordenAsociada.setOrdenId(orden.getPurchaseOrder().getId());
					if (cliente == null) {
						SolicitudCompraIf solicitudCompra = solicitudCompraLocal.getSolicitudCompra(orden.getPurchaseOrder().getSolicitudcompraId());
						if (solicitudCompra.getTipoReferencia().equals("P")) {
							Map<String, Object> queryMap = new HashMap<String, Object>();
							queryMap.put("codigo", solicitudCompra.getReferencia());
							Iterator<PresupuestoIf> it = presupuestoLocal.findPresupuestoByQuery(queryMap, idEmpresa).iterator();
							if (it.hasNext()) {
								PresupuestoIf presupuesto = it.next();
								cliente = (ClienteIf) clienteLocal.findClienteByPresupuestoId(presupuesto.getId()).iterator().next();
							}
						} else if (solicitudCompra.getTipoReferencia().equals("M")) {
							Map<String, Object> queryMap = new HashMap<String, Object>();
							queryMap.put("codigo", solicitudCompra.getReferencia());
							Iterator<OrdenMedioIf> it = ordenMedioLocal.findOrdenMedioByQuery(queryMap, idEmpresa).iterator();
							if (it.hasNext()) {
								OrdenMedioIf ordenMedio = it.next();
								cliente = (ClienteIf) clienteLocal.findClienteByOrdenMedioId(ordenMedio.getId()).iterator().next();
							}
						}
					}
				} else {
					ordenAsociada.setOrdenId(orden.getMediaOrder().getId());
					if (cliente == null)
						cliente = (ClienteIf) clienteLocal.findClienteByOrdenMedioId(orden.getMediaOrder().getId()).iterator().next();
				}
				ordenAsociadaSessionLocal.addOrdenAsociada((OrdenAsociadaIf) ordenAsociada);
			}
			
			if (!compra.getEstado().equals(ESTADO_INACTIVA)) {
				procesarDatosCompra(modelDetalleList, idOficina, idTarea,
						listaRetenciones, usuario, compraGastoClase, compra,
						compraDetalleColeccion, mapaAir, crearRetencionCero,
						airSeleccionadoId, cliente);
			}
		} catch (ComprasBpmException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch(GenericBusinessException e){ 
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar la Compra");
		}
		
		return compra;
	}

	private void procesarDatosCompra(List<CompraDetalleIf> modelDetalleList,
			Long idOficina, long idTarea,
			Vector<CompraRetencionIf> listaRetenciones, UsuarioIf usuario,
			CompraGastoClase compraGastoClase, CompraEJB compra,
			Vector<CompraDetalleIf> compraDetalleColeccion,
			Map<Long, SriAirIf> mapaAir, boolean crearRetencionCero,
			Long airSeleccionadoId, ClienteIf clienteAsociado) throws GenericBusinessException,
			ComprasBpmException {
		for (CompraRetencionIf modelRetencion : listaRetenciones) {
			modelRetencion.setCompraId(compra.getPrimaryKey());
			CompraRetencionEJB compraRetencion = registrarCompraRetencion(modelRetencion);
			manager.merge(compraRetencion);
		}

		//Se crean retenciones si todos los detalles tienen retencion cero
		if ( crearRetencionCero ){
			if ( airSeleccionadoId !=null ){
				String codigoAir = mapaAir.get(airSeleccionadoId).getCodigo();
				CompraRetencionIf retencion = registrarCompraRetencionCero(compra, codigoAir,"R");
				compraRetencionSessionLocal.addCompraRetencion(retencion);
			}
			//LO QUE ES IVA NO VA
			/*if ( ivaRetencionSeleccionadoId != null ){
			String codigoIvaRetencion = mapaIvaRetencion.get(ivaRetencionSeleccionadoId).getCodigo();
			CompraRetencionIf retencion = registrarCompraRetencion(compra, codigoIvaRetencion,"I");
			compraRetencionSessionLocal.addCompraRetencion(retencion);
		}*/
		}

		compraGastoLocal.procesarCompraGasto(compraGastoClase);

		CarteraEJB carteraCompra = registrarCartera(compra, null, true, idOficina);
		manager.persist(carteraCompra);
		Object[] carteraSumaValores = registroCartera(compra, modelDetalleList, carteraCompra);
		CarteraEJB cartera = (CarteraEJB)carteraSumaValores[0]; 
		Double sumaValoresComprasDetalleRedondeoTotal = (Double) carteraSumaValores[1];
		Double sumaValoresComprasDetalleRedondeoParcial = (Double) carteraSumaValores[2];

		int year = compra.getFecha().getYear() + 1900;
		int month = compra.getFecha().getMonth() + 1;
		AsientoIf asiento = null;
		if ((year == 2008 && month >= 9) || (year > 2008)) {
			asiento = generarAsientoCompra(compra, compraDetalleColeccion, null, false, usuario, clienteAsociado);
		}

		TipoDocumentoIf tipoDocumentoCompra = tipoDocumentoLocal.getTipoDocumento(compra.getTipodocumentoId());
		//procesoPrincipalCompraLocal.guardarCompraPrincipalCompra(idTarea, String.valueOf(compra.getId()), String.valueOf(compra.getTipodocumentoId()), compra.getObservacion());
		verificacionValores(compra, cartera, sumaValoresComprasDetalleRedondeoTotal, sumaValoresComprasDetalleRedondeoParcial, asiento, tipoDocumentoCompra);
		movimientoSessionLocal.procesarCompra(compra, (List<CompraDetalleIf>)compraDetalleLocal.findCompraDetalleByCompraId(compra.getId()), usuario);

		if (((year == 2008 && month >= 9) || (year > 2008)) && (!tipoDocumentoCompra.getCodigo().equals("GCI"))) {
			for (int etapa = 1; etapa <= 2; etapa++)
				asiento = generarAsientoGasto(compra, usuario, etapa);
		}
	}
	
	private void actualizarCompraDetalleIdEnDetalleGasto(CompraGastoClase compraGastoClase, CompraDetalleIf compraDetalle, Long compraDetalleId){
		Map<Long, CompraDetalleGastoClase> mapaDetalleGasto = compraGastoClase.getMapaCompraDetalleGasto();
		for ( Long gastoId : mapaDetalleGasto.keySet() ){
			CompraDetalleGastoClase cdgc = mapaDetalleGasto.get(gastoId);
			Map<CompraDetalleIf, CompraDetalleGastoIf> mapaCompraDetalleGasto = cdgc.getDetalle();
			for ( CompraDetalleIf cd : mapaCompraDetalleGasto.keySet() ){
				if ( compraDetalle == cd  ){
					CompraDetalleGastoIf cdg = mapaCompraDetalleGasto.get(cd);
					if ( cdg != null ){
						if ( cd.getId() == null )
							cd.setId(compraDetalleId);
						if ( cdg.getCompraDetalleId() == null )
							cdg.setCompraDetalleId(compraDetalleId);
					}
				}
			}
		}
	}
	
	private String getNumeroCartera(java.sql.Date fechaCartera, String codigoTipoDocumento, Long idEmpresa, Long idOficina) {
		String codigo = "";

		try {
			EmpresaIf empresa = empresaLocal.getEmpresa(idEmpresa);
			OficinaIf oficina = oficinaLocal.getOficina(idOficina);
			ServidorIf servidor = (oficina.getServidorId()!=null)?servidorLocal.getServidor(oficina.getServidorId()):null;
			String monthCartera = utilitariosLocal.getMonthFromDate(fechaCartera);
			String anioCartera = utilitariosLocal.getYearFromDate(fechaCartera);
			codigo = empresa.getCodigo() + "-";
			if (servidor!=null)
				codigo += servidor.getCodigo() + "-";
			codigo += codigoTipoDocumento + "-";
			nuevaCodificacionActiva = (Double.parseDouble(anioCartera) <= 2008)?false:true;
			if (nuevaCodificacionActiva)
				codigo += monthCartera + "-";
			codigo += anioCartera + "-";
			return codigo;
		} catch (GenericBusinessException e1) {
			e1.printStackTrace();
		}

		return null;
	}
	
	private int getMaximoNumeroCartera(CarteraIf modelCartera) {
		String queryString = "select max(codigo) from CarteraEJB c where c.codigo like '" + modelCartera.getCodigo() + "%'";
		Query query = manager.createQuery(queryString);
		String maxCodigoCartera = query.getResultList().toString();
		queryString = "select max(codigo) from LogCarteraEJB lc where lc.codigo like '" + modelCartera.getCodigo() + "%'";
		query = manager.createQuery(queryString);
		String maxCodigoLog = query.getResultList().toString();
		String codigo = (maxCodigoCartera.compareTo(maxCodigoLog) >= 0 || maxCodigoLog.equals("[null]"))?maxCodigoCartera:maxCodigoLog;
		int codigoCartera = 1;
		if (!codigo.equals("[null]")) {
			codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
			codigoCartera = Integer.parseInt(codigo.split(modelCartera.getCodigo())[1]) + 1;
		}
		return codigoCartera;
	}
	
	private CompraRetencionIf registrarCompraRetencionCero (CompraIf compra,String codigoImpuesto,String tipoImpuesto){
		
		Calendar cal = Calendar.getInstance();
		CompraRetencionIf retencion = new CompraRetencionEJB();
		retencion.setFechaEmision(new Date(new java.util.Date().getTime()));
		retencion.setAutorizacion("0");
		retencion.setEstablecimiento("0");
		retencion.setPuntoEmision("0");
		retencion.setSecuencial("0");
		retencion.setCompraId(compra.getPrimaryKey()!=null?compra.getPrimaryKey():compra.getId());
		retencion.setEjercicioFiscal(String.valueOf(cal.get(Calendar.YEAR)));
		double total = compra.getValor().doubleValue()+compra.getIva().doubleValue()
						+ compra.getOtroImpuesto().doubleValue() + compra.getDescuento().doubleValue();
		retencion.setBaseImponible(BigDecimal.valueOf(utilitariosLocal.redondeoValor(total)));
		retencion.setPorcentajeRetencion(BigDecimal.ZERO);
		retencion.setValorRetenido(BigDecimal.ZERO);
		retencion.setCodigoImpuesto(codigoImpuesto);
		retencion.setImpuesto(tipoImpuesto);
		return retencion;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean actualizarCompra(CompraIf model, List<CompraDetalleIf> modelDetalleList, CompraIf compraAnterior, 
			CarteraIf carteraAnterior, List<CompraDetalleIf> modelDetalleEliminadoList, Long idEmpresa, Long idOficina, 
			long idTarea, UsuarioIf usuario, boolean actualizarCodigoCartera,CompraGastoClase compraGastoClase, Vector<OrderData> ordenesAsociadasVector) throws GenericBusinessException {
		
		CompraEJB compra;
		Vector<CompraDetalleIf> compraDetalleColeccion = new Vector<CompraDetalleIf>(); 
		Map<Long, SriAirIf> mapaAir = getAirs();
		//Map<Long, SriIvaRetencionIf> mapaIvaRetencion = getIvaRetenciones();
		boolean crearRetencionCero = true;
		Long airSeleccionadoId = null;
		try {
			compra = registrarCompra(model);
			manager.merge(compra);
			
			compraGastoClase.setCompraId(compra.getPrimaryKey());
			
			for (CompraDetalleIf modelDetalleEliminado: modelDetalleEliminadoList) {
				CompraDetalleIf compraDetalleEliminado = compraDetalleLocal.getCompraDetalle(modelDetalleEliminado.getId());
				if(compraDetalleEliminado != null && compraDetalleEliminado.getId() != null){
					compraDetalleLocal.deleteCompraDetalle(compraDetalleEliminado.getId());
				}
				//manager.remove(modelDetalleEliminado);
			}
			
			for (CompraDetalleIf modelDetalle : modelDetalleList) {
				modelDetalle.setCompraId(compra.getPrimaryKey());
				CompraDetalleEJB compraDetalle = registrarCompraDetalle(modelDetalle);
				manager.merge(compraDetalle);
				Long idDetalle = compraDetalle.getId()!= null ?compraDetalle.getId() : compraDetalle.getPrimaryKey(); 
				actualizarCompraDetalleIdEnDetalleGasto(compraGastoClase, modelDetalle, idDetalle);
				compraDetalleColeccion.add(compraDetalle);
				if ( crearRetencionCero && !mapaAir.containsKey(modelDetalle.getIdSriAir()) && compraAnterior.getEstado().equals(ESTADO_INACTIVA) && !compra.getEstado().equals(ESTADO_INACTIVA)){
					crearRetencionCero = false;
				}
				/*if ( crearRetencionCero && !mapaIvaRetencion.containsKey(modelDetalle.getSriIvaRetencionId()) ){
					crearRetencionCero = false;
				}*/
				airSeleccionadoId = modelDetalle.getIdSriAir();
			}
			
			Iterator<OrdenAsociadaIf> ordenesAsociadasIterator = ordenAsociadaSessionLocal.findOrdenAsociadaByCompraId(compra.getId()).iterator();
			while (ordenesAsociadasIterator.hasNext()) {
				OrdenAsociadaIf ordenAsociada = ordenesAsociadasIterator.next();
				if (ordenAsociada.getTipoOrden().equals("OC")) {
					OrdenCompraIf ordenCompra = ordenCompraSessionLocal.getOrdenCompra(ordenAsociada.getOrdenId());
					ordenCompra.setEstado("E");
					manager.merge(ordenCompra);
				} else if (ordenAsociada.getTipoOrden().equals("OM")) {
					OrdenMedioIf ordenMedio = ordenMedioSessionLocal.getOrdenMedio(ordenAsociada.getOrdenId());
					ordenMedio.setEstado("E");
					manager.merge(ordenMedio);
				}
				manager.remove(ordenAsociada);
			}
			ClienteIf cliente = null;
			for (int i=0; i<ordenesAsociadasVector.size(); i++) {
				OrderData orden = ordenesAsociadasVector.get(i);
				OrdenAsociadaEJB ordenAsociada = new OrdenAsociadaEJB();
				ordenAsociada.setCompraId(compra.getPrimaryKey());
				ordenAsociada.setTipoOrden(orden.getOrderType());
				if (orden.getOrderType().equals("OC")) {
					ordenAsociada.setOrdenId(orden.getPurchaseOrder().getId());
					if (cliente == null) {
						SolicitudCompraIf solicitudCompra = solicitudCompraLocal.getSolicitudCompra(orden.getPurchaseOrder().getSolicitudcompraId());
						if (solicitudCompra.getTipoReferencia().equals("P")) {
							Map<String, Object> queryMap = new HashMap<String, Object>();
							queryMap.put("codigo", solicitudCompra.getReferencia());
							Iterator<PresupuestoIf> it = presupuestoLocal.findPresupuestoByQuery(queryMap, idEmpresa).iterator();
							if (it.hasNext()) {
								PresupuestoIf presupuesto = it.next();
								cliente = (ClienteIf) clienteLocal.findClienteByPresupuestoId(presupuesto.getId()).iterator().next();
							}
						} else if (solicitudCompra.getTipoReferencia().equals("M")) {
							Map<String, Object> queryMap = new HashMap<String, Object>();
							queryMap.put("codigo", solicitudCompra.getReferencia());
							Iterator<OrdenMedioIf> it = ordenMedioLocal.findOrdenMedioByQuery(queryMap, idEmpresa).iterator();
							if (it.hasNext()) {
								OrdenMedioIf ordenMedio = it.next();
								cliente = (ClienteIf) clienteLocal.findClienteByOrdenMedioId(ordenMedio.getId()).iterator().next();
							}
						}
					}
				} else {
					ordenAsociada.setOrdenId(orden.getMediaOrder().getId());
					if (cliente == null)
						cliente = (ClienteIf) clienteLocal.findClienteByOrdenMedioId(orden.getMediaOrder().getId()).iterator().next();
				}
				ordenAsociadaSessionLocal.addOrdenAsociada((OrdenAsociadaIf) ordenAsociada);
			}
			
			if (compraAnterior.getEstado().equals(ESTADO_INACTIVA) && !compra.getEstado().equals(ESTADO_INACTIVA))
				procesarDatosCompra(modelDetalleList, idOficina, idTarea,
						new Vector<CompraRetencionIf>(), usuario, compraGastoClase, compra,
						compraDetalleColeccion, mapaAir, crearRetencionCero,
						airSeleccionadoId, cliente);
			else if (!compraAnterior.getEstado().equals(ESTADO_INACTIVA))
				actualizarDatosCompra(modelDetalleList, compraAnterior,
					carteraAnterior, idOficina, idTarea, usuario,
					actualizarCodigoCartera, compraGastoClase, compra,
					compraDetalleColeccion, cliente);
		} catch (ComprasBpmException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch(GenericBusinessException e){
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar la Compra");
		}
		
		return true;
	}

	private void actualizarDatosCompra(List<CompraDetalleIf> modelDetalleList,
			CompraIf compraAnterior, CarteraIf carteraAnterior, Long idOficina,
			long idTarea, UsuarioIf usuario, boolean actualizarCodigoCartera,
			CompraGastoClase compraGastoClase, CompraEJB compra,
			Vector<CompraDetalleIf> compraDetalleColeccion, ClienteIf clienteAsociado)
			throws GenericBusinessException, ComprasBpmException {
		compraGastoLocal.procesarCompraGasto(compraGastoClase);
		
		long carteraAnteriorId = carteraAnterior.getId();
		Iterator it = carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraAnteriorId).iterator();
		while (it.hasNext()) {
			CarteraDetalleIf carteraDetalleAnterior = (CarteraDetalleIf) it.next();
			manager.remove(carteraDetalleAnterior);
		}
		CarteraEJB carteraCompra = registrarCartera(compra, carteraAnterior, actualizarCodigoCartera, idOficina);
		carteraCompra.setId(carteraAnteriorId);
		manager.merge(carteraCompra);
		Object[] carteraSumaValores = registroCartera(compra, modelDetalleList, carteraCompra);
		CarteraEJB cartera = (CarteraEJB)carteraSumaValores[0]; 
		Double sumaValoresComprasDetalleRedondeoTotal = (Double) carteraSumaValores[1];
		Double sumaValoresComprasDetalleRedondeoParcial = (Double) carteraSumaValores[2];
		int year = compra.getFecha().getYear() + 1900;
		int month = compra.getFecha().getMonth() + 1;
		AsientoIf asiento = null;
		if ((year == 2008 && month >= 9) || (year > 2008))
			asiento = generarAsientoCompra(compra, compraDetalleColeccion, compraAnterior, true, usuario, clienteAsociado);
		//procesoPrincipalCompraLocal.guardarCompraPrincipalCompra(idTarea, String.valueOf(compra.getId()), String.valueOf(compra.getTipodocumentoId()), compra.getObservacion());
		TipoDocumentoIf tipoDocumentoCompra = tipoDocumentoLocal.getTipoDocumento(compra.getTipodocumentoId());
		verificacionValores(compra, cartera, sumaValoresComprasDetalleRedondeoTotal, sumaValoresComprasDetalleRedondeoParcial, asiento, tipoDocumentoCompra);
	}
	
	private Object[] registroCartera(CompraEJB compra,List<CompraDetalleIf> modelDetalleList, CarteraEJB cartera) throws GenericBusinessException{
		 Map sumaValoresComprasDetalleMap = registrarCarteraDetalle(modelDetalleList,compra, cartera);
		 Double sumaValoresComprasDetalleRedondeoTotal = (Double) sumaValoresComprasDetalleMap.get(REDONDEO_TOTAL);
		 Double sumaValoresComprasDetalleRedondeoParcial = (Double) sumaValoresComprasDetalleMap.get(REDONDEO_PARCIAL);
		 
		return new Object[]{cartera, sumaValoresComprasDetalleRedondeoTotal, sumaValoresComprasDetalleRedondeoParcial};
	}

	private Map registrarCarteraDetalle(List<CompraDetalleIf> modelDetalleList, CompraEJB compra, CarteraEJB cartera) throws GenericBusinessException {
		Map valoresComprasDetalles = new HashMap();
		Double valorComprasDetalleRedondeoTotal = 0.0;
		Double valorComprasDetalleRedondeoParcial = 0.0;
		Double valorCompras = 0.0;
		Double valorComprasSinRedondeo = 0.0;
		int secuencial = 0;
		Collection documentos = documentoLocal.findDocumentoByTipoDocumentoId(compra.getTipodocumentoId());
		Iterator itDocumentos = documentos.iterator();
		while (itDocumentos.hasNext()) {
			DocumentoIf documento = (DocumentoIf) itDocumentos.next();
			double valorCartera = 0D;
			
			for (CompraDetalleIf modelDetalle : modelDetalleList) {
				if (modelDetalle.getDocumentoId().compareTo(documento.getId()) == 0 && documento.getBonificacion().equals("N")) {
					double cantidadCompra = modelDetalle.getCantidad().doubleValue();
					double valorCompra = modelDetalle.getValor().doubleValue();
					double ivaCompra = modelDetalle.getIva().doubleValue();
					double iceCompra = modelDetalle.getIce().doubleValue();
					double otrosImpuestosCompra = modelDetalle.getOtroImpuesto().doubleValue();
					double descuentoCompra = modelDetalle.getDescuento().doubleValue();
					
					double subtotal = valorCompra * cantidadCompra;
					double porcentajeDescuentoEspecial = modelDetalle.getPorcentajeDescuentoEspecial().doubleValue() / 100;
					double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
					double porcentajeDescuentosVarios = modelDetalle.getPorcentajeDescuentosVarios().doubleValue() / 100;
					double descuentosVarios = (subtotal - descuentoEspecial) * porcentajeDescuentosVarios;				
				
					valorCartera = subtotal - descuentoEspecial - descuentoCompra - descuentosVarios + ivaCompra + iceCompra + otrosImpuestosCompra;
					valorCompras += valorCartera;
					valorComprasSinRedondeo += valorCartera;
				}
			}
			
			if (valorCompras > 0.0 && valorCartera > 0.0 && valorComprasSinRedondeo > 0.0) {
				CarteraDetalleData modelCarteraDetalle = new CarteraDetalleData();
				modelCarteraDetalle.setCarteraId(cartera.getPrimaryKey());
				modelCarteraDetalle.setDocumentoId(documento.getId());
				modelCarteraDetalle.setPreimpreso(compra.getPreimpreso());
				modelCarteraDetalle.setFechaCreacion(compra.getFecha());
				modelCarteraDetalle.setFechaCartera(compra.getFecha());
				modelCarteraDetalle.setFechaVencimiento(compra.getFechaVencimiento());
				modelCarteraDetalle.setFechaUltimaActualizacion(compra.getFecha());
				modelCarteraDetalle.setValor(BigDecimal.valueOf(utilitariosLocal.redondeoValor(valorCompras)));
				modelCarteraDetalle.setSaldo(BigDecimal.valueOf(utilitariosLocal.redondeoValor(valorCompras)));
				/*modelCarteraDetalle.setValor(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorCartera)));
				modelCarteraDetalle.setSaldo(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorCartera)));*/
				modelCarteraDetalle.setCartera("S");
				modelCarteraDetalle.setAutorizacion(compra.getAutorizacion());
				modelCarteraDetalle.setSriSustentoTributarioId(compra.getIdSriSustentoTributario());
				modelCarteraDetalle.setSecuencial(++secuencial);
				CarteraDetalleEJB carteraDetalle = registrarCarteraDetalle(modelCarteraDetalle);
				//valorComprasDetalleRedondeoTotal = modelCarteraDetalle.getValor().doubleValue();
				valorComprasDetalleRedondeoTotal = cartera.getValor().doubleValue();
				valorComprasDetalleRedondeoParcial = utilitariosLocal.redondeoValor(valorComprasSinRedondeo);
				manager.merge(carteraDetalle);
			}
		}
		
		valoresComprasDetalles.put(REDONDEO_TOTAL, valorComprasDetalleRedondeoTotal);
		valoresComprasDetalles.put(REDONDEO_PARCIAL, valorComprasDetalleRedondeoParcial);
		return valoresComprasDetalles;
	}

	private AsientoIf generarAsientoCompra(CompraEJB compra, Vector<CompraDetalleIf> compraDetalleColeccion, CompraIf compraAnterior, boolean actualizar, UsuarioIf usuario, ClienteIf clienteAsociado) throws GenericBusinessException {
		AsientoIf asiento = null;
		compraAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
		compraAsientoAutomaticoHandlerLocal.setIva(0D);
		compraAsientoAutomaticoHandlerLocal.setCtaxpag(0D);
		for (int i = 0; i < compraDetalleColeccion.size(); i++) {
			CompraDetalleIf compraDetalle = (CompraDetalleIf) compraDetalleColeccion.get(i);
			if (i != compraDetalleColeccion.size() - 1)
				asiento = compraAsientoAutomaticoHandlerLocal.generarAsientoAutomatico(compra, compraDetalle, compraAnterior, false, actualizar, usuario, clienteAsociado);
			else if (i == compraDetalleColeccion.size() - 1)
				asiento = compraAsientoAutomaticoHandlerLocal.generarAsientoAutomatico(compra, compraDetalle, compraAnterior, true, actualizar, usuario, clienteAsociado);
		}
		return asiento;
	}
	
	private AsientoIf generarAsientoGasto(CompraIf compra, UsuarioIf usuario, int etapa) throws GenericBusinessException {
		Map parameterMap = new HashMap();
		DocumentoIf documentoGastoImportacion = null;
		parameterMap.put("codigo", "GCIN");
		Iterator it = documentoLocal.findDocumentoByQueryAndEmpresaId(parameterMap, usuario.getEmpresaId()).iterator();
		if (it.hasNext())
			documentoGastoImportacion = (DocumentoIf) it.next();
		AsientoIf asiento = null;
		if (documentoGastoImportacion != null) {
			gastoAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
			ArrayList gastoDetalleColeccion = (ArrayList) compraGastoLocal.findGastoByCompraId(compra.getId());
			it = gastoDetalleColeccion.iterator();
			for (int i = 0; i < gastoDetalleColeccion.size(); i++) {
				Object[] gasto = (Object[]) it.next();
				CompraDetalleGastoIf compraDetalleGasto = (CompraDetalleGastoIf) gasto[0];
				DocumentoIf documentoCompraDetalle = (DocumentoIf) gasto[1];
				CompraDetalleIf compraDetalle = (CompraDetalleIf) gasto[2];
				if (i != gastoDetalleColeccion.size() - 1)
					asiento = gastoAsientoAutomaticoHandlerLocal.generarAsientoAutomatico(compra, compraDetalleGasto, compraDetalle, documentoCompraDetalle, documentoGastoImportacion, false, usuario, etapa);
				else if (i == gastoDetalleColeccion.size() - 1)
					asiento = gastoAsientoAutomaticoHandlerLocal.generarAsientoAutomatico(compra, compraDetalleGasto, compraDetalle, documentoCompraDetalle, documentoGastoImportacion, true, usuario, etapa);
			}
		}
		return asiento;
	}

	private void verificacionValores(CompraEJB compra, CarteraEJB cartera, Double valorComprasDetalleRedondeoTotal, Double valorComprasDetalleRedondeoParcial, AsientoIf asiento, TipoDocumentoIf tipoDocumentoCompra) throws GenericBusinessException {
		
		double descuentoEspecialCompra = 0D;
		double descuentosVariosCompra = 0D;
		Collection comprasDetalle = compraDetalleLocal.findCompraDetalleByCompraId(compra.getId());
		Iterator comprasDetalleIt = comprasDetalle.iterator();
		while(comprasDetalleIt.hasNext()){
			CompraDetalleIf compraDetalle = (CompraDetalleIf)comprasDetalleIt.next();
			double subtotal = compraDetalle.getValor().doubleValue() * compraDetalle.getCantidad().doubleValue();
			double porcentajeDescuentoEspecial = compraDetalle.getPorcentajeDescuentoEspecial().doubleValue() / 100;
			double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
			double porcentajeDescuentosVarios = compraDetalle.getPorcentajeDescuentosVarios().doubleValue() / 100;
			double descuentosVarios = (subtotal - descuentoEspecial) * porcentajeDescuentosVarios;
			
			descuentoEspecialCompra = descuentoEspecialCompra + descuentoEspecial;
			descuentosVariosCompra = descuentosVariosCompra + descuentosVarios;
		}	
		
		double totalCompra = utilitariosLocal.redondeoValor(compra.getValor().doubleValue() + compra.getIva().doubleValue() 
							+ compra.getIce().doubleValue() + compra.getOtroImpuesto().doubleValue()
							- compra.getDescuento().doubleValue() - descuentoEspecialCompra - descuentosVariosCompra);
		
		if (  totalCompra != valorComprasDetalleRedondeoTotal && totalCompra != valorComprasDetalleRedondeoParcial )
			throw new GenericBusinessException("Error al guardar la compra, el valor de la Compra no coincide con el Detalle");
		
		if ( cartera.getValor().doubleValue() != valorComprasDetalleRedondeoTotal && cartera.getValor().doubleValue() != valorComprasDetalleRedondeoParcial )
			throw new GenericBusinessException("Error al guardar la compra, el valor de la Cartera no coincide con los valores del Detalle de la Compra");
		
		if ( asiento != null ) {
				double debe = 0.0; double haber = 0.0;
				Collection<AsientoDetalleIf> asientosDetalle = asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getPrimaryKey());
				for ( Iterator itAsientos = asientosDetalle.iterator() ; itAsientos.hasNext() ; ){
					AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) itAsientos.next();
					debe += asientoDetalle.getDebe().doubleValue();
					haber += asientoDetalle.getHaber().doubleValue();
				}
				debe = utilitariosLocal.redondeoValor(debe);
				haber = utilitariosLocal.redondeoValor(haber);
				//if ( debe != haber )
				if ( debe - haber > 0.02 )
					throw new GenericBusinessException("Error al guardar la compra, el valor del Debe y Haber no coinciden en el Asiento");
				//else if (  debe != valorComprasDetalleRedondeoTotal && debe != valorComprasDetalleRedondeoParcial )
				else if (  (debe - valorComprasDetalleRedondeoTotal) > 0.02
						&& (debe - valorComprasDetalleRedondeoParcial ) > 0.02 )
					throw new GenericBusinessException("Error al guardar la compra, el valor del Debe y Haber no coinciden con el detalle de la Compra");
		} else {
			int year = compra.getFecha().getYear() + 1900;
			int month = compra.getFecha().getMonth() + 1;
			if (((year == 2008 && month >= 9) || (year > 2008)) && (!tipoDocumentoCompra.getCodigo().equals("GCI")))
				throw new GenericBusinessException("No se gener\u00f3 el asiento de la Compra");
		}
	}

	private int getCodigoCompra(CompraIf model, Long idEmpresa) {
		String codigo = getMaximoCodigoCompra(model.getCodigo(), idEmpresa);
		int codigoCompra = 1;
		if (!codigo.equals("[null]")) {
			codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
			codigoCompra = Integer.parseInt(codigo.split(model.getCodigo())[1]) + 1;
		}
		return codigoCompra;
	}
	
	private String getMaximoCodigoCompra(String codigoParcialCompra, Long idEmpresa) {
		String queryString = "select max(c.codigo) from CompraEJB c, TipoDocumentoEJB td where c.tipodocumentoId = td.id and td.empresaId = " + idEmpresa + " and c.codigo like '" + codigoParcialCompra + "%'";
		Query query = manager.createQuery(queryString);
		return query.getResultList().toString();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CarteraEJB registrarCartera(CompraIf compraIf, CarteraIf carteraAnterior, boolean actualizarCodigoCartera, Long idOficina) {
		CarteraEJB cartera = new CarteraEJB();
		
		try {
			cartera.setTipo("P");
			cartera.setOficinaId(compraIf.getOficinaId());
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(compraIf.getTipodocumentoId());
			cartera.setTipodocumentoId(tipoDocumento.getId());
			EmpresaIf empresa = empresaLocal.getEmpresa(tipoDocumento.getEmpresaId());
			cartera.setCodigo(carteraAnterior!=null?carteraAnterior.getCodigo():"");
			nuevaCodificacionActiva = (compraIf.getFecha().getYear() + 1900 <= 2008)?false:true;
			if (nuevaCodificacionActiva && actualizarCodigoCartera) {
				String unNumeroCartera = getNumeroCartera(compraIf.getFecha(), "COM", empresa.getId(), idOficina);
				cartera.setCodigo(unNumeroCartera);
				cartera.setCodigo(cartera.getCodigo() + formatoSerial.format(getMaximoNumeroCartera(cartera)));
			} else if (!nuevaCodificacionActiva) {
				cartera.setCodigo(empresa.getCodigo() + "-" + "COM-" + compraIf.getCodigo());
			}
			cartera.setReferenciaId(compraIf.getId());
			cartera.setClienteoficinaId(compraIf.getProveedorId());
			cartera.setPreimpreso(compraIf.getPreimpreso());
			cartera.setUsuarioId(compraIf.getUsuarioId());
			//END 19 JULIO
			cartera.setMonedaId(compraIf.getMonedaId());
			cartera.setFechaEmision(utilitariosLocal.fromSqlDateToTimestamp(compraIf.getFecha()));
			
			double valorCompra = compraIf.getValor().doubleValue();
			double ivaCompra = compraIf.getIva().doubleValue();
			double iceCompra = compraIf.getIce().doubleValue();
			double otrosImpuestosCompra = compraIf.getOtroImpuesto().doubleValue();
			double descuentoCompra = compraIf.getDescuento().doubleValue();
			
			double descuentoEspecialCompra = 0D;
			double descuentosVariosCompra = 0D;
			Collection comprasDetalle = compraDetalleLocal.findCompraDetalleByCompraId(compraIf.getId());
			Iterator comprasDetalleIt = comprasDetalle.iterator();
			while(comprasDetalleIt.hasNext()){
				CompraDetalleIf compraDetalle = (CompraDetalleIf)comprasDetalleIt.next();
				double subtotal = compraDetalle.getValor().doubleValue() * compraDetalle.getCantidad().doubleValue();
				double porcentajeDescuentoEspecial = compraDetalle.getPorcentajeDescuentoEspecial().doubleValue() / 100;
				double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
				double porcentajeDescuentosVarios = compraDetalle.getPorcentajeDescuentosVarios().doubleValue() / 100;
				double descuentosVarios = (subtotal - descuentoEspecial) * porcentajeDescuentosVarios;
				
				descuentoEspecialCompra = descuentoEspecialCompra + descuentoEspecial;
				descuentosVariosCompra = descuentosVariosCompra + descuentosVarios;
			}
			
			double valorCartera = valorCompra - descuentoEspecialCompra - descuentoCompra - descuentosVariosCompra + ivaCompra + iceCompra + otrosImpuestosCompra ;
			
			cartera.setValor(BigDecimal.valueOf(utilitariosLocal.redondeoValor(valorCartera)));
			cartera.setSaldo(BigDecimal.valueOf(utilitariosLocal.redondeoValor(valorCartera)));
			cartera.setEstado("N");
			cartera.setComentario(compraIf.getObservacion());
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		return cartera;
	}
	
	public CarteraEJB registrarCartera(CarteraIf model) {
		CarteraEJB cartera = new CarteraEJB();
		
		cartera.setId(model.getId());
		cartera.setTipo(model.getTipo());
		cartera.setOficinaId(model.getOficinaId());
		cartera.setTipodocumentoId(model.getTipodocumentoId());
		cartera.setCodigo(model.getCodigo());
		cartera.setReferenciaId(model.getReferenciaId());
		cartera.setClienteoficinaId(model.getClienteoficinaId());
		cartera.setPreimpreso(model.getPreimpreso());
		cartera.setUsuarioId(model.getUsuarioId());
		cartera.setVendedorId(model.getVendedorId());
		cartera.setMonedaId(model.getMonedaId());
		cartera.setFechaEmision(model.getFechaEmision());
		cartera.setValor(utilitariosLocal.redondeoValor(model.getValor()));
		cartera.setSaldo(utilitariosLocal.redondeoValor(model.getSaldo()));
		cartera.setFechaUltimaActualizacion(model.getFechaUltimaActualizacion());
		cartera.setEstado(model.getEstado());
		cartera.setComentario(model.getComentario());
		cartera.setAprobado(model.getAprobado());
		
		return cartera;
	}
	
	public CarteraDetalleEJB registrarCarteraDetalle(CarteraDetalleIf modelDetalle) {
		CarteraDetalleEJB carteraDetalle = new CarteraDetalleEJB();				
		
		carteraDetalle.setId(modelDetalle.getId());
		carteraDetalle.setCarteraId(modelDetalle.getCarteraId());
		carteraDetalle.setDocumentoId(modelDetalle.getDocumentoId());
		carteraDetalle.setSecuencial(modelDetalle.getSecuencial());
		carteraDetalle.setLineaId(modelDetalle.getLineaId());
		carteraDetalle.setPreimpreso(modelDetalle.getPreimpreso());
		carteraDetalle.setFechaCreacion(modelDetalle.getFechaCreacion());
		carteraDetalle.setFechaCartera(modelDetalle.getFechaCartera());
		carteraDetalle.setFechaVencimiento(modelDetalle.getFechaVencimiento());
		carteraDetalle.setFechaUltimaActualizacion(modelDetalle.getFechaUltimaActualizacion());
		carteraDetalle.setValor(utilitariosLocal.redondeoValor(modelDetalle.getValor()));
		carteraDetalle.setSaldo(utilitariosLocal.redondeoValor(modelDetalle.getSaldo()));
		carteraDetalle.setCartera(modelDetalle.getCartera());
		carteraDetalle.setAutorizacion(modelDetalle.getAutorizacion());
		carteraDetalle.setSriSustentoTributarioId(modelDetalle.getSriSustentoTributarioId());
		
		return carteraDetalle;
	}
	
	/**
	 * @param model
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CompraEJB registrarCompra(CompraIf model) {
		CompraEJB compra = new CompraEJB();
		
		compra.setId(model.getId());
		compra.setOficinaId(model.getOficinaId());
		compra.setTipodocumentoId(model.getTipodocumentoId());
		compra.setTipoCompra(model.getTipoCompra());
		//END 19 JULIO
		compra.setCodigo(model.getCodigo());
		compra.setProveedorId(model.getProveedorId());
		compra.setMonedaId(model.getMonedaId());
		compra.setUsuarioId(model.getUsuarioId());
		compra.setFecha(model.getFecha());
		compra.setFechaVencimiento(model.getFechaVencimiento());
		//compra.setFechaCaducidad(model.getFechaCaducidad());
		compra.setPreimpreso(model.getPreimpreso());
		compra.setAutorizacion(model.getAutorizacion());
		compra.setReferencia(model.getReferencia());
		compra.setLocalimportada(model.getLocalimportada());
		compra.setValor(model.getValor());
		compra.setDescuento(model.getDescuento());
		compra.setIva(model.getIva());
		compra.setIce(model.getIce());
		compra.setOtroImpuesto(model.getOtroImpuesto());
		compra.setObservacion(model.getObservacion());
		compra.setEstado(model.getEstado());
		compra.setRetencion(model.getRetencion());
		compra.setIdSriSustentoTributario(model.getIdSriSustentoTributario());
		
		return compra;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CompraDetalleEJB registrarCompraDetalle(CompraDetalleIf modelDetalle) {
		CompraDetalleEJB compraDetalle = new CompraDetalleEJB();
		
		compraDetalle.setId(modelDetalle.getId());
		compraDetalle.setDocumentoId(modelDetalle.getDocumentoId());
		compraDetalle.setProductoId(modelDetalle.getProductoId());
		compraDetalle.setCompraId(modelDetalle.getCompraId());
		compraDetalle.setCantidad(modelDetalle.getCantidad());
		compraDetalle.setValor(modelDetalle.getValor());
		compraDetalle.setDescuento(modelDetalle.getDescuento());
		compraDetalle.setIva(modelDetalle.getIva());
		compraDetalle.setIce(modelDetalle.getIce());
		compraDetalle.setOtroImpuesto(modelDetalle.getOtroImpuesto());
		compraDetalle.setIdSriAir(modelDetalle.getIdSriAir());
		compraDetalle.setDescripcion(modelDetalle.getDescripcion());
		compraDetalle.setSriIvaRetencionId(modelDetalle.getSriIvaRetencionId());
		compraDetalle.setPorcentajeDescuentosVarios(modelDetalle.getPorcentajeDescuentosVarios());
		compraDetalle.setPorcentajeDescuentoEspecial(modelDetalle.getPorcentajeDescuentoEspecial());
		
		return compraDetalle;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CompraRetencionEJB registrarCompraRetencion(CompraRetencionIf modelRetencion) {
		CompraRetencionEJB compraRetencion = new CompraRetencionEJB();
		
		compraRetencion.setAutorizacion(modelRetencion.getAutorizacion());
		compraRetencion.setCompraId(modelRetencion.getCompraId());
		compraRetencion.setEstablecimiento(modelRetencion.getEstablecimiento());
		compraRetencion.setFechaEmision(modelRetencion.getFechaEmision());
		compraRetencion.setId(modelRetencion.getId());
		compraRetencion.setPuntoEmision(modelRetencion.getPuntoEmision());
		compraRetencion.setSecuencial(modelRetencion.getSecuencial());
		compraRetencion.setEjercicioFiscal(modelRetencion.getEjercicioFiscal());
		compraRetencion.setBaseImponible(modelRetencion.getBaseImponible());
		compraRetencion.setImpuesto(modelRetencion.getImpuesto());
		compraRetencion.setPorcentajeRetencion(modelRetencion.getPorcentajeRetencion());
		compraRetencion.setValorRetenido(modelRetencion.getValorRetenido());
		compraRetencion.setCodigoImpuesto(modelRetencion.getCodigoImpuesto());
		
		return compraRetencion;
	}
	
	public Collection findCompraByEmpresaIdByFechaInicioAndFechaFin(Long idEmpresa, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws GenericBusinessException {
		String queryString = "select distinct c from CompraEJB c, TipoDocumentoEJB td where c.tipodocumentoId = td.id and td.empresaId = " + idEmpresa + " and c.fecha >= :fechaInicio and c.fecha <= :fechaFin";
		Query query = manager.createQuery(queryString);
		java.sql.Timestamp startDate = utilitariosLocal.resetTimestampStartDate(new java.sql.Timestamp(fechaInicio.getTime()));
		query.setParameter("fechaInicio", startDate);
		java.sql.Timestamp endDate = utilitariosLocal.resetTimestampEndDate(new java.sql.Timestamp(fechaFin.getTime()));
		query.setParameter("fechaFin", endDate);		
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCompraReembolsoParaReversarByQueryAndEmpresaId(Map aMap, Long idEmpresa) throws GenericBusinessException {
		String objectName = "c";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		//String queryString = "select distinct c, ca, a from CompraEJB " + objectName + ", CarteraEJB ca, AsientoEJB a, OficinaEJB o, EmpresaEJB e where " + where 
		//+ " and c.tipodocumentoId = ca.tipodocumentoId and c.id = ca.referenciaId and c.oficinaId = o.id and o.empresaId = e.id and a.transaccionId = c.id and a.tipoDocumentoId = c.tipodocumentoId and e.id = " + idEmpresa + " order by c.fecha asc, c.preimpreso asc";
		
		String queryString = "select distinct c, ca from CompraEJB " + objectName + ", CarteraEJB ca, OficinaEJB o, EmpresaEJB e where " + where 
		+ " and c.tipodocumentoId = ca.tipodocumentoId and c.id = ca.referenciaId and c.oficinaId = o.id and o.empresaId = e.id and e.id = " + idEmpresa + " order by c.fecha asc, c.preimpreso asc";
		
		System.out.println("QUERY -> " + queryString);
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
	
	public void procesarReversacionCompraPorReembolso(CompraIf compra, CompraIf compraAnterior, CarteraIf cartera, TipoDocumentoIf tipoDocumentoCompraPorReembolso, DocumentoIf documentoCompraPorReembolso, UsuarioIf usuario, Vector<OrderData> ordenesAsociadasVector) throws GenericBusinessException, SaldoCuentaNegativoException {
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("tipoDocumentoId", compra.getTipodocumentoId());
			parameterMap.put("transaccionId", compra.getId());
			Iterator it = asientoLocal.findAsientoByQuery(parameterMap).iterator();
			AsientoIf asiento = null;
			if (it.hasNext()) {
				asiento = (AsientoIf) it.next();
			}
			Iterator compraDetalleIterator = compraDetalleLocal.findCompraDetalleByCompraId(compra.getId()).iterator();
			Iterator carteraDetalleIterator = carteraDetalleLocal.findCarteraDetalleByCarteraId(cartera.getId()).iterator();
			compra.setTipodocumentoId(tipoDocumentoCompraPorReembolso.getId());
			compra.setEstado("A");
			CompraEJB compraPorReembolso = registrarCompra(compra);
			manager.merge(compraPorReembolso);
			Vector<CompraDetalleIf> compraDetalleColeccion = new Vector<CompraDetalleIf>();
			while (compraDetalleIterator.hasNext()) {
				CompraDetalleIf compraDetalleModel = (CompraDetalleIf) compraDetalleIterator.next();
				compraDetalleModel.setDocumentoId(documentoCompraPorReembolso.getId());
				CompraDetalleEJB compraDetalle = registrarCompraDetalle(compraDetalleModel);
				manager.merge(compraDetalle);
				compraDetalleColeccion.add(compraDetalle);
			}
			
			cartera.setTipodocumentoId(tipoDocumentoCompraPorReembolso.getId());
			CarteraEJB carteraCompraPorReembolso = registrarCartera(cartera);
			manager.merge(carteraCompraPorReembolso);
			while (carteraDetalleIterator.hasNext()) {
				CarteraDetalleIf carteraDetalleModel = (CarteraDetalleIf) carteraDetalleIterator.next();
				carteraDetalleModel.setDocumentoId(documentoCompraPorReembolso.getId());
				CarteraDetalleEJB carteraDetalle = registrarCarteraDetalle(carteraDetalleModel);
				manager.merge(carteraDetalle);
			}
			
			ClienteIf cliente = null;	
			
			for (int i=0; i<ordenesAsociadasVector.size(); i++) {
				OrderData orden = ordenesAsociadasVector.get(i);
				if (orden != null) {
					if (orden.getOrderType().equals("OC")) {
						if (cliente == null) {
							if (orden.getPurchaseOrder() != null) {
								SolicitudCompraIf solicitudCompra = solicitudCompraLocal.getSolicitudCompra(orden.getPurchaseOrder().getSolicitudcompraId());
								if (solicitudCompra.getTipoReferencia().equals("P")) {
									Map<String, Object> queryMap = new HashMap<String, Object>();
									queryMap.put("codigo", solicitudCompra.getReferencia());
									it = presupuestoLocal.findPresupuestoByQuery(queryMap, usuario.getEmpresaId()).iterator();
									if (it.hasNext()) {
										PresupuestoIf presupuesto = (PresupuestoIf) it.next();
										cliente = (ClienteIf) clienteLocal.findClienteByPresupuestoId(presupuesto.getId()).iterator().next();
										break;
									}
								} else if (solicitudCompra.getTipoReferencia().equals("M")) {
									Map<String, Object> queryMap = new HashMap<String, Object>();
									queryMap.put("codigo", solicitudCompra.getReferencia());
									it = ordenMedioLocal.findOrdenMedioByQuery(queryMap, usuario.getEmpresaId()).iterator();
									if (it.hasNext()) {
										OrdenMedioIf ordenMedio = (OrdenMedioIf) it.next();
										cliente = (ClienteIf) clienteLocal.findClienteByOrdenMedioId(ordenMedio.getId()).iterator().next();
										break;
									}
								}
							}
						}
					} else {
						if (cliente == null) {
							cliente = (ClienteIf) clienteLocal.findClienteByOrdenMedioId(orden.getMediaOrder().getId()).iterator().next();
							break;
						}
					}
				}
			}
			
			int year = compraPorReembolso.getFecha().getYear() + 1900;
			int month = compraPorReembolso.getFecha().getMonth() + 1;
			if ((year == 2008 && month >= 9) || (year > 2008)) {
				if (asiento!=null)
					asiento = generarAsientoCompra(compraPorReembolso, compraDetalleColeccion, compraAnterior, true, usuario, cliente);
				else
					asiento = generarAsientoCompra(compraPorReembolso, compraDetalleColeccion, null, false, usuario, cliente);
			}
			
			/*if (asiento != null) {
				List<AsientoDetalleIf> asientoDetalleList = new ArrayList<AsientoDetalleIf>();
				List<AsientoDetalleIf> asientoDetalleAnteriorList = (List) asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getId());
				AsientoEJB asientoAnterior = asientoLocal.registrarAsiento(asiento);
				asiento.setTipoDocumentoId(tipoDocumentoCompraPorReembolso.getId());
				AsientoEJB asientoCompraPorReembolso = asientoLocal.registrarAsiento(asiento);
				boolean reversarSaldos = false;
				if (asientoAnterior.getStatus().equals("A"))
					reversarSaldos = true;
				
				asiento.setId(asientoAnterior.getId());
				asiento.setPrimaryKey(asientoAnterior.getPrimaryKey());
				Iterator eventoContableIterator = eventoContableLocal.findEventoContableByDocumentoId(documentoCompraPorReembolso.getId()).iterator();
				if (eventoContableIterator.hasNext()) {
					EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();;
					if (eventoContable != null) {
						Double descuento = Double.valueOf(compra.getDescuento().doubleValue());
						Double iva = Double.valueOf(compra.getIva().doubleValue());
						Double subtotal = Double.valueOf(compra.getValor().doubleValue());
						double otroImpuesto = Double.valueOf(compra.getOtroImpuesto().doubleValue());
						Double total = subtotal + iva + otroImpuesto - descuento;
						parameterMap = new HashMap();
						parameterMap.put("REEMBOLSO", total);
						parameterMap.put("CTAXPAG", total);
						parameterMap.put("PROVEEDOR_ID", compra.getProveedorId());
						asientoDetalleList = generarAsientoDetallesReversacion(eventoContable, parameterMap, compra);
						asientoLocal.actualizarAsiento(asientoCompraPorReembolso, asientoDetalleList, asientoAnterior, asientoDetalleAnteriorList, asientoDetalleAnteriorList, reversarSaldos, false, usuario);
					}
				}
			}*/
		} catch(GenericBusinessException e){ 
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar la Compra");
		}
	}
	
	public void procesarReversacionCompra(CompraIf compra, CompraIf compraAnterior, CarteraIf cartera, TipoDocumentoIf tipoDocumentoCompra, DocumentoIf documentoCompraLocal, UsuarioIf usuario, Vector<OrderData> ordenesAsociadasVector) throws GenericBusinessException, SaldoCuentaNegativoException {
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("tipoDocumentoId", compra.getTipodocumentoId());
			parameterMap.put("transaccionId", compra.getId());
			Iterator it = asientoLocal.findAsientoByQuery(parameterMap).iterator();
			AsientoIf asiento = null;
			if (it.hasNext()) {
				asiento = (AsientoIf) it.next();
			}
			Iterator compraDetalleIterator = compraDetalleLocal.findCompraDetalleByCompraId(compra.getId()).iterator();
			Iterator carteraDetalleIterator = carteraDetalleLocal.findCarteraDetalleByCarteraId(cartera.getId()).iterator();
			compra.setTipodocumentoId(tipoDocumentoCompra.getId());
			compra.setEstado("A");
			CompraEJB compraLocal = registrarCompra(compra);
			manager.merge(compraLocal);
			Vector<CompraDetalleIf> compraDetalleColeccion = new Vector<CompraDetalleIf>();
			while (compraDetalleIterator.hasNext()) {
				CompraDetalleIf compraDetalleModel = (CompraDetalleIf) compraDetalleIterator.next();
				compraDetalleModel.setDocumentoId(documentoCompraLocal.getId());
				CompraDetalleEJB compraDetalle = registrarCompraDetalle(compraDetalleModel);
				manager.merge(compraDetalle);
				compraDetalleColeccion.add(compraDetalle);
			}
			
			cartera.setTipodocumentoId(tipoDocumentoCompra.getId());
			CarteraEJB carteraCompra = registrarCartera(cartera);
			manager.merge(carteraCompra);
			while (carteraDetalleIterator.hasNext()) {
				CarteraDetalleIf carteraDetalleModel = (CarteraDetalleIf) carteraDetalleIterator.next();
				carteraDetalleModel.setDocumentoId(documentoCompraLocal.getId());
				CarteraDetalleEJB carteraDetalle = registrarCarteraDetalle(carteraDetalleModel);
				manager.merge(carteraDetalle);
			}
			
			ClienteIf cliente = null;	
			
			for (int i=0; i<ordenesAsociadasVector.size(); i++) {
				OrderData orden = ordenesAsociadasVector.get(i);
				if (orden != null) {
					if (orden.getOrderType().equals("OC")) {
						if (cliente == null) {
							if (orden.getPurchaseOrder() != null) {
								SolicitudCompraIf solicitudCompra = solicitudCompraLocal.getSolicitudCompra(orden.getPurchaseOrder().getSolicitudcompraId());
								if (solicitudCompra.getTipoReferencia().equals("P")) {
									Map<String, Object> queryMap = new HashMap<String, Object>();
									queryMap.put("codigo", solicitudCompra.getReferencia());
									it = presupuestoLocal.findPresupuestoByQuery(queryMap, usuario.getEmpresaId()).iterator();
									if (it.hasNext()) {
										PresupuestoIf presupuesto = (PresupuestoIf) it.next();
										cliente = (ClienteIf) clienteLocal.findClienteByPresupuestoId(presupuesto.getId()).iterator().next();
										break;
									}
								} else if (solicitudCompra.getTipoReferencia().equals("M")) {
									Map<String, Object> queryMap = new HashMap<String, Object>();
									queryMap.put("codigo", solicitudCompra.getReferencia());
									it = ordenMedioLocal.findOrdenMedioByQuery(queryMap, usuario.getEmpresaId()).iterator();
									if (it.hasNext()) {
										OrdenMedioIf ordenMedio = (OrdenMedioIf) it.next();
										cliente = (ClienteIf) clienteLocal.findClienteByOrdenMedioId(ordenMedio.getId()).iterator().next();
										break;
									}
								}
							}
						}
					} else {
						if (cliente == null) {
							cliente = (ClienteIf) clienteLocal.findClienteByOrdenMedioId(orden.getMediaOrder().getId()).iterator().next();
							break;
						}
					}
				}
			}
			
			int year = compraLocal.getFecha().getYear() + 1900;
			int month = compraLocal.getFecha().getMonth() + 1;
			if ((year == 2008 && month >= 9) || (year > 2008)) {
				if (asiento!=null)
					asiento = generarAsientoCompra(compraLocal, compraDetalleColeccion, compraAnterior, true, usuario, cliente);
				else
					asiento = generarAsientoCompra(compraLocal, compraDetalleColeccion, null, false, usuario, cliente);
			}
			
			/*if (asiento != null) {
				List<AsientoDetalleIf> asientoDetalleList = new ArrayList<AsientoDetalleIf>();
				List<AsientoDetalleIf> asientoDetalleAnteriorList = (List) asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getId());
				AsientoEJB asientoAnterior = asientoLocal.registrarAsiento(asiento);
				asiento.setTipoDocumentoId(tipoDocumentoCompra.getId());
				AsientoEJB asientoCompra = asientoLocal.registrarAsiento(asiento);
				boolean reversarSaldos = false;
				if (asientoAnterior.getStatus().equals("A"))
					reversarSaldos = true;
				
				asiento.setId(asientoAnterior.getId());
				asiento.setPrimaryKey(asientoAnterior.getPrimaryKey());
				Iterator eventoContableIterator = eventoContableLocal.findEventoContableByDocumentoId(documentoCompraLocal.getId()).iterator();
				if (eventoContableIterator.hasNext()) {
					EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();;
					if (eventoContable != null) {
						Double descuento = Double.valueOf(compra.getDescuento().doubleValue());
						Double iva = Double.valueOf(compra.getIva().doubleValue());
						Double subtotal = Double.valueOf(compra.getValor().doubleValue());
						double otroImpuesto = Double.valueOf(compra.getOtroImpuesto().doubleValue());
						Double total = subtotal + iva + otroImpuesto - descuento;
						parameterMap = new HashMap();
						parameterMap.put("COSTO", total - iva);
						parameterMap.put("IVA", iva);
						parameterMap.put("CTAXPAG", total);
						parameterMap.put("PROVEEDOR_ID", compra.getProveedorId());
						asientoDetalleList = generarAsientoDetallesReversacion(eventoContable, parameterMap, compra);
						asientoLocal.actualizarAsiento(asientoCompra, asientoDetalleList, asientoAnterior, asientoDetalleAnteriorList, asientoDetalleAnteriorList, reversarSaldos, false, usuario);
					}
				}
			}*/
		} catch(GenericBusinessException e){ 
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar la Compra");
		}
	}
	
	private List<AsientoDetalleIf> generarAsientoDetallesReversacion(EventoContableIf eventoContable, Map parameterMap, CompraIf compra) throws GenericBusinessException {
		List<AsientoDetalleIf> asientoDetallesReversacionList = new ArrayList<AsientoDetalleIf>();
		PlanCuentaIf planCuenta = planCuentaLocal.getPlanCuenta(eventoContable.getPlanCuentaId());
		Collection plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
		Iterator plantillasIterator = plantillas.iterator();
		CuentaIf cuenta = null;

		while (plantillasIterator.hasNext()) {
			PlantillaIf plantilla = (PlantillaIf) plantillasIterator.next();
			cuenta = cuentaLocal.getCuenta(plantilla.getCuentaId());
			Map aMap = new HashMap();
			if (cuenta.getImputable().equals("N") && !parameterMap.get("TIPO_DOCUMENTO_CODIGO").toString().equals("LIC")) {
				if (plantilla.getNemonico().equals("RETEFUENTE")) {
					Long idCuentaRetefuente = (Long) parameterMap.get("ID_CUENTA_RETEFUENTE");
					if (idCuentaRetefuente != null)
						cuenta = cuentaLocal.getCuenta(idCuentaRetefuente);
				} else if (plantilla.getNemonico().equals("RETEIVA"))  {
					Long idCuentaReteiva = (Long) parameterMap.get("ID_CUENTA_RETEIVA");
					if (idCuentaReteiva != null)
						cuenta = cuentaLocal.getCuenta(idCuentaReteiva);
				} else {
					if (plantilla.getNemonico().equals("COSTO")) {
						Long idProducto = (Long) parameterMap.get("PRODUCTO_ID");
						ProductoIf producto = productoLocal.getProducto(idProducto);
						GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
						parameterMap.put("entidadId", idProducto);
						parameterMap.put("tipoEntidad", "I");
						if (generico != null) {
							if (generico.getMediosProduccion().equals("M") || generico.getMediosProduccion().equals("P"))
								parameterMap.put("nemonico", "COSTO");
							else if (generico.getMediosProduccion().equals("G"))
								parameterMap.put("nemonico", "GASTO");
						}
					}
					
					if (plantilla.getNemonico().equals("CTAXPAG")) {
						Long idProveedor = (Long) parameterMap.get("PROVEEDOR_ID");
						ClienteOficinaIf proveedorOficina = clienteOficinaLocal.getClienteOficina(idProveedor);
						ClienteIf proveedor = clienteLocal.getCliente(proveedorOficina.getClienteId());
						if (proveedor != null) {
							parameterMap.put("entidadId", proveedor.getId());
							parameterMap.put("tipoEntidad", "C");
							parameterMap.put("nemonico", "CTAXPAG");
						}
						
						Iterator cuentaEntidadIterator = cuentaEntidadLocal.findCuentaEntidadByQuery(aMap).iterator();
						if (cuentaEntidadIterator.hasNext()) {
							CuentaEntidadIf cuentaEntidad = (CuentaEntidadIf) cuentaEntidadIterator.next();
							cuenta = cuentaLocal.getCuenta(cuentaEntidad.getCuentaId());
						} else {
							aMap.clear();
							aMap.put("plancuentaId", planCuenta.getId());
							if (plantilla.getDebehaber().equals("D"))
								aMap.put("codigo", "DEFING");
							else if (plantilla.getDebehaber().equals("H"))
								aMap.put("codigo", "DEFEGR");
							Collection cuentas = cuentaLocal.findCuentaByQuery(aMap);
							if (cuentas.size() > 0)
								cuenta = (CuentaIf) cuentaLocal.findCuentaByQuery(aMap).iterator().next();
							else
								throw new GenericBusinessException("No existe cuenta Asociada");
						}
					}
				}
			}
			
			if (cuenta != null) {
				AsientoDetalleIf asientoDetalle = new AsientoDetalleData();
				asientoDetalle.setCuentaId(cuenta.getId());
				String numeroFactura = compra.getPreimpreso();
				asientoDetalle.setReferencia("FACT. # " + numeroFactura);
				asientoDetalle.setGlosa("COMPRA/FACT. # " + compra.getPreimpreso() + " " + compra.getObservacion());
				double valor = ((Double) parameterMap.get(plantilla.getNemonico())).doubleValue();
				if (plantilla.getDebehaber().equals("D")) {
					asientoDetalle.setDebe(BigDecimal.valueOf(valor));
					asientoDetalle.setHaber(BigDecimal.ZERO);
				} else if (plantilla.getDebehaber().equals("H")) {
					asientoDetalle.setHaber(BigDecimal.valueOf(valor));
					asientoDetalle.setDebe(BigDecimal.ZERO);
				}
				
				if (valor > 0.0)
					asientoDetallesReversacionList.add(asientoDetalle);
			}
		}
		
		return asientoDetallesReversacionList;
	}
	
	public Boolean verificarPreimpreso(Long proveedorId, String preimpreso, String autorizacion) throws GenericBusinessException {
		if ( proveedorId != null && preimpreso!= null && !preimpreso.equals("") ){
			Map aMap = new HashMap();
			aMap.put("preimpreso", preimpreso);
			aMap.put("autorizacion", autorizacion);
			aMap.put("proveedorId", proveedorId);
			Collection<CompraIf> compras = findCompraByQuery(aMap);
			if ( compras.size() == 0 )
				return false;
			else if ( compras.size() == 1 ){
				CompraIf compra = compras.iterator().next();
				//Si la factura de proveedor esta anulada, no hay que tomarla en cuenta
				//en la verificacion del preimpreso
				if ( !"N".equals(compra.getEstado()) )
					return true;
				else
					return false;
			} else if ( compras.size() > 1 ){
				throw new GenericBusinessException("Preimpreso existe MAS de UNA vez!");
			}
		}
		return true;
	}
		
	
	public Boolean verificarPreimpreso(Long proveedorId, String preimpreso, String autorizacion,Long tipoDocumentoId) throws GenericBusinessException {
		if ( proveedorId != null && preimpreso!= null && !preimpreso.equals("") ){
			Map aMap = new HashMap();
			aMap.put("preimpreso", preimpreso);
			aMap.put("autorizacion", autorizacion);
			aMap.put("proveedorId", proveedorId);
			aMap.put("tipodocumentoId", tipoDocumentoId);
			Collection<CompraIf> compras = findCompraByQuery(aMap);
			if ( compras.size() == 0 )
				return false;
			else if ( compras.size() == 1 )
				return true;
			else if ( compras.size() > 1 ){
				throw new GenericBusinessException("Preimpreso existe MAS de UNA vez!");
			}
		}
		return true;
	}
	
	public Collection findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFinRetencionesAnuladasEmpresaId(Map aMap, Date fechaInicio, Date fechaFin, boolean ordenarPorSecuencialRetencion,Long idIva,Long idRenta,String retencionNumero,Long empresaId)  throws GenericBusinessException{
        String objectName = "co";
        String where = "";
        if(!aMap.isEmpty()){
     	   where = QueryBuilder.buildWhere(aMap, objectName) + " and";
        }
  	   String queryString = "select co, cod, cr, lcr from CompraEJB " + objectName +
  	   ", CompraDetalleEJB cod, CompraRetencionEJB cr, ClienteOficinaEJB clo, ClienteEJB cl, LogCompraRetencionEJB lcr,TipoDocumentoEJB td where " + where + 
  	   " co.id = cod.compraId and co.id = cr.compraId and co.proveedorId = clo.id and clo.clienteId = cl.id and lcr.compraId=co.id " +
  		" and co.tipodocumentoId=td.id and td.empresaId='"+empresaId+"' "+
  	   "and co.fecha >= :fechaInicio and co.fecha <= :fechaFin";
  	   
  	   
  	   if(idIva!=null)		queryString= queryString + " and (cod.sriIvaRetencionId="+idIva+" or cod.sriIvaRetencionId is NULL) ";
  	   if(idRenta!=null)	queryString= queryString + " and (cod.idSriAir="+idRenta+" or cod.idSriAir is NULL) ";
  	   if(retencionNumero!=null && !retencionNumero.equals(""))		queryString= queryString + " and cr.secuencial like '%"+retencionNumero+"%' ";
  	   
  		   
  	   String orderByPart = "";
  	   if(ordenarPorSecuencialRetencion){
  		   orderByPart += " order by cr.secuencial asc"; 
  	   }else{
  		   orderByPart += " order by cl.nombreLegal asc";
  	   }
  	   queryString += orderByPart;
  	   Query query = manager.createQuery(queryString);
  	   query.setParameter("fechaInicio",fechaInicio);
  	   query.setParameter("fechaFin",fechaFin);
  	   
  	   Set keys = aMap.keySet();
  	   
  	   Iterator it = keys.iterator();
  	   while (it.hasNext()) {
  		   String propertyKey = (String) it.next();
  		   Object property = aMap.get(propertyKey);
  		   query.setParameter(propertyKey, property);
  	   }
  	   return query.getResultList();
     }
	
	
	 public Collection findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFinRetencionesAnuladas(Map aMap, Date fechaInicio, Date fechaFin, boolean ordenarPorSecuencialRetencion,Long idIva,Long idRenta,String retencionNumero)  throws GenericBusinessException{
	        String objectName = "co";
	        String where = "";
	        if(!aMap.isEmpty()){
	     	   where = QueryBuilder.buildWhere(aMap, objectName) + " and";
	        }
	  	   String queryString = "select co, cod, cr, lcr from CompraEJB " + objectName +
	  	   ", CompraDetalleEJB cod, CompraRetencionEJB cr, ClienteOficinaEJB clo, ClienteEJB cl, LogCompraRetencionEJB lcr where " + where + 
	  	   " co.id = cod.compraId and co.id = cr.compraId and co.proveedorId = clo.id and clo.clienteId = cl.id and lcr.compraId=co.id " +
	  	   "and co.fecha >= :fechaInicio and co.fecha <= :fechaFin";
	  	   
	  	   
	  	   if(idIva!=null)		queryString= queryString + " and (cod.sriIvaRetencionId="+idIva+" or cod.sriIvaRetencionId is NULL) ";
	  	   if(idRenta!=null)	queryString= queryString + " and (cod.idSriAir="+idRenta+" or cod.idSriAir is NULL) ";
	  	   if(retencionNumero!=null && !retencionNumero.equals(""))		queryString= queryString + " and cr.secuencial like '%"+retencionNumero+"%' ";
	  	   
	  		   
	  	   String orderByPart = "";
	  	   if(ordenarPorSecuencialRetencion){
	  		   orderByPart += " order by cr.secuencial asc"; 
	  	   }else{
	  		   orderByPart += " order by cl.nombreLegal asc";
	  	   }
	  	   queryString += orderByPart;
	  	   Query query = manager.createQuery(queryString);
	  	   query.setParameter("fechaInicio",fechaInicio);
	  	   query.setParameter("fechaFin",fechaFin);
	  	   
	  	   Set keys = aMap.keySet();
	  	   
	  	   Iterator it = keys.iterator();
	  	   while (it.hasNext()) {
	  		   String propertyKey = (String) it.next();
	  		   Object property = aMap.get(propertyKey);
	  		   query.setParameter(propertyKey, property);
	  	   }
	  	   return query.getResultList();
	     }
	 
    public Collection findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFin(Map aMap, Date fechaInicio, Date fechaFin, boolean ordenarPorSecuencialRetencion,Long idIva,Long idRenta,String retencionNumero)  throws GenericBusinessException
    {
        String objectName = "co";
        String where = "";
        if(!aMap.isEmpty()){  where = QueryBuilder.buildWhere(aMap, objectName) + " and";       }
  	   String queryString = "select co, cod, cr from CompraEJB " + objectName +
  	   ", CompraDetalleEJB cod, CompraRetencionEJB cr, ClienteOficinaEJB clo, ClienteEJB cl where " + where + " co.id = cod.compraId and co.id = cr.compraId and co.proveedorId = clo.id and clo.clienteId = cl.id and co.fecha >= :fechaInicio and co.fecha <= :fechaFin";
  	   
  	   if(idIva!=null)		queryString= queryString + " and (cod.sriIvaRetencionId="+idIva+" or cod.sriIvaRetencionId is NULL) ";
  	   if(idRenta!=null)	queryString= queryString + " and (cod.idSriAir="+idRenta+" or cod.idSriAir is NULL) ";
  	   if(retencionNumero!=null && !retencionNumero.equals(""))		queryString= queryString + " and cr.secuencial like '%"+retencionNumero+"%' ";
  	      
  	   String orderByPart = "";
  	   if(ordenarPorSecuencialRetencion){
  		   orderByPart += " order by cr.secuencial asc"; 
  	   }else{
  		   orderByPart += " order by cl.nombreLegal asc";
  	   }
  	   queryString += orderByPart;
  	   Query query = manager.createQuery(queryString);
  	   query.setParameter("fechaInicio",fechaInicio);
  	   query.setParameter("fechaFin",fechaFin);
  	   
  	   Set keys = aMap.keySet();
  	   
  	   Iterator it = keys.iterator();
  	   while (it.hasNext()) {
  		   String propertyKey = (String) it.next();
  		   Object property = aMap.get(propertyKey);
  		   query.setParameter(propertyKey, property);
  	   }
  	   return query.getResultList();
    }
    
    public Collection findComprasAndCompraDetalleByQueryByFechaInicioAndByFechaFinEmpresaId(Map aMap, Date fechaInicio, Date fechaFin, Long empresaId)  throws GenericBusinessException {
    	//SELECT C.*, CD.* FROM COMPRA C, COMPRA_DETALLE CD, CLIENTE_OFICINA CO, CLIENTE CLI WHERE C.ID NOT IN (SELECT DISTINCT CO.ID FROM COMPRA CO, COMPRA_DETALLE COD, COMPRA_RETENCION CR, CLIENTE_OFICINA CLO, CLIENTE CL, TIPO_DOCUMENTO TD WHERE CO.ID = COD.COMPRA_ID AND CO.ID = CR.COMPRA_ID AND CO.PROVEEDOR_ID = CLO.ID AND CLO.CLIENTE_ID = CL.ID  AND CO.TIPODOCUMENTO_ID = TD.ID AND TD.EMPRESA_ID = 1 AND CO.FECHA >= '2012-05-01' AND CO.FECHA <= '2012-05-30') AND C.ID = CD.COMPRA_ID AND C.PROVEEDOR_ID = CO.ID AND CO.CLIENTE_ID = CLI.ID AND C.FECHA >= '2012-05-01' AND C.FECHA <= '2012-05-30'
    	String objectName = "c";
        String where = "";
        if (!aMap.isEmpty())
        	where = QueryBuilder.buildWhere(aMap, objectName) + " and";       
        String queryString = "select c, cd from CompraEJB " + objectName + ", CompraDetalleEJB cd, ClienteOficinaEJB co, ClienteEJB cli where " + where + " c.id not in (select distinct co.id from CompraEJB co, CompraDetalleEJB cod, CompraRetencionEJB cr, ClienteOficinaEJB clo, ClienteEJB cl, TipoDocumentoEJB td where co.id = cod.compraId and co.id = cr.compraId and co.proveedorId = clo.id and clo.clienteId = cl.id and co.tipodocumentoId = td.id and td.empresaId = " + empresaId + " and co.fecha >= :fechaInicio and co.fecha <= :fechaFin) and c.id = cd.compraId and c.proveedorId = co.id and co.clienteId = cli.id and c.fecha >= :fechaInicio and c.fecha <= :fechaFin";
        Query query = manager.createQuery(queryString);
   	   	query.setParameter("fechaInicio",fechaInicio);
   	   	query.setParameter("fechaFin",fechaFin);
   	   	
   	   	if(aMap.get("proveedorId") != null)
   	   		query.setParameter("proveedorId",(Long)aMap.get("proveedorId"));
   	   	
   	   	return query.getResultList();
    }
     
    public Collection findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFinEmpresaId(Map aMap, Date fechaInicio, Date fechaFin, boolean ordenarPorSecuencialRetencion,Long idIva,Long idRenta,String retencionNumero,Long empresaId)  throws GenericBusinessException
    {
        String objectName = "co";
        String where = "";
        if(!aMap.isEmpty()){  where = QueryBuilder.buildWhere(aMap, objectName) + " and";       }
  	   String queryString = "select co, cod, cr from CompraEJB " + objectName +
  	   ", CompraDetalleEJB cod, CompraRetencionEJB cr, ClienteOficinaEJB clo, ClienteEJB cl , TipoDocumentoEJB td where " + where + 
  	   " co.id = cod.compraId and co.id = cr.compraId and co.proveedorId = clo.id and clo.clienteId = cl.id " +
  	   " and co.tipodocumentoId= td.id and td.empresaId='"+empresaId+"'"+
  	   " and co.fecha >= :fechaInicio and co.fecha <= :fechaFin";
  	   
  	   if(idIva!=null)		queryString= queryString + " and (cod.sriIvaRetencionId="+idIva+" or cod.sriIvaRetencionId is NULL) ";
  	   if(idRenta!=null)	queryString= queryString + " and (cod.idSriAir="+idRenta+" or cod.idSriAir is NULL) ";
  	   if(retencionNumero!=null && !retencionNumero.equals(""))		queryString= queryString + " and cr.secuencial like '%"+retencionNumero+"%' ";
  	      
  	   String orderByPart = "";
  	   if(ordenarPorSecuencialRetencion){
  		   orderByPart += " order by cr.secuencial asc"; 
  	   }else{
  		   orderByPart += " order by cl.nombreLegal asc";
  	   }
  	   queryString += orderByPart;
  	   Query query = manager.createQuery(queryString);
  	   query.setParameter("fechaInicio",fechaInicio);
  	   query.setParameter("fechaFin",fechaFin);
  	   
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
	public AsientoIf fixCompra(CompraIf model, List<CompraDetalleIf> modelDetalleList, Long idEmpresa, Long idOficina, UsuarioIf usuario) throws GenericBusinessException {
		AsientoIf asientoRetencion = null;
		CompraEJB compra;
		Vector<CompraDetalleIf> compraDetalleVector = new Vector<CompraDetalleIf>(); 
		try {
			compra = registrarCompra(model);
			//Iterator find
			
			for (CompraDetalleIf modelDetalle : modelDetalleList) {
				CompraDetalleEJB compraDetalle = registrarCompraDetalle(modelDetalle);
				manager.merge(compraDetalle);
				compraDetalleVector.add(compraDetalle);
			}
			
			/* Aquí hay que buscar los registros de compra retención para modificarlos */
			fixCompraRetenciones(compra, compraDetalleVector);
			
			/* Aquí hay que buscar la cartera de la retención para modificar el asiento correspondiente */
			Iterator it = findCarteraRetencionProveedorByCompraId(compra.getId()).iterator();
			CarteraIf carteraRetencion = null;
			if (it.hasNext()) {
				carteraRetencion = (CarteraIf) it.next();
				Map parameterMap = new HashMap();
				parameterMap.put("tipoDocumentoId", carteraRetencion.getTipodocumentoId());
				parameterMap.put("transaccionId", carteraRetencion.getId());
				Iterator itAsiento = asientoLocal.findAsientoByQuery(parameterMap).iterator();
				if (itAsiento.hasNext()) {
					asientoRetencion = (AsientoIf) itAsiento.next();
					System.out.println(asientoRetencion.getNumero());
				}
			}
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar la Compra");
		}
		
		return asientoRetencion;
	}
    
    private void fixCompraRetenciones(CompraIf compra, Vector<CompraDetalleIf> compraDetalleVector) throws GenericBusinessException {
    	Collection compraRetencionColeccion = compraRetencionLocal.findCompraRetencionByCompraId(compra.getId());
    	Iterator compraRetencionIt = compraRetencionColeccion.iterator();
    	CompraRetencionEJB retencionAnterior = (compraRetencionIt.hasNext())?(CompraRetencionEJB)compraRetencionIt.next():null;
    	Iterator detallesCompraIt = compraDetalleVector.iterator();
    	Vector<CompraRetencionEJB> retencionRentaVector = new Vector<CompraRetencionEJB>();
    	Vector<CompraRetencionEJB> retencionIvaVector = new Vector<CompraRetencionEJB>();

    	while (detallesCompraIt.hasNext()) {
    		Map<String,Object> parameterMap = new HashMap<String,Object>();
    		CompraDetalleIf compraDetalle = (CompraDetalleIf) detallesCompraIt.next();
    		Double descuento = Double.valueOf(compraDetalle.getDescuento().doubleValue());
    		Double iva = Double.valueOf(compraDetalle.getIva().doubleValue());
    		Double valor = Double.valueOf(compraDetalle.getValor().doubleValue());
    		double cantidad = compraDetalle.getCantidad().doubleValue();
    		double subtotal = cantidad * valor;
    		double descuentoSubtotal = descuento;
    		java.sql.Date fechaCompra = compra.getFecha();
    		SriAirIf sriAir = sriAirLocal.getSriAir(compraDetalle.getIdSriAir());
    		SriIvaRetencionIf sriIvaRetencion = null;
    		if (compraDetalle.getSriIvaRetencionId() != null)
    			sriIvaRetencion = sriIvaRetencionLocal.getSriIvaRetencion(compraDetalle.getSriIvaRetencionId());
    		double porcentaje_retencion_renta = (sriAir!=null)?sriAir.getPorcentaje().doubleValue():0D;
    		double porcentaje_retencion_iva = (sriIvaRetencion!=null)?sriIvaRetencion.getPorcentaje().doubleValue():0D;
    		double retencion_renta = (subtotal - descuentoSubtotal) * porcentaje_retencion_renta / 100;
    		double retencion_iva = iva * porcentaje_retencion_iva / 100;
    		int ejercicio = compra.getFecha().getYear() + 1900;

    		if (retencion_renta > 0D) {
    			CompraRetencionEJB retencionRenta = new CompraRetencionEJB();
    			retencionRenta.setEstablecimiento(retencionAnterior.getEstablecimiento());
    			retencionRenta.setPuntoEmision(retencionAnterior.getPuntoEmision());
    			retencionRenta.setSecuencial(retencionAnterior.getSecuencial());
    			retencionRenta.setAutorizacion(retencionAnterior.getAutorizacion());
    			retencionRenta.setFechaEmision(retencionAnterior.getFechaEmision());
    			retencionRenta.setCompraId(compra.getId());
    			retencionRenta.setEjercicioFiscal(String.valueOf(ejercicio));
    			retencionRenta.setBaseImponible(BigDecimal.valueOf(subtotal - descuentoSubtotal));
    			retencionRenta.setImpuesto("R");
    			retencionRenta.setPorcentajeRetencion(BigDecimal.valueOf(porcentaje_retencion_renta));
    			retencionRenta.setValorRetenido(BigDecimal.valueOf(retencion_renta));
    			retencionRenta.setCodigoImpuesto(sriAir.getCodigo());
    			retencionRentaVector.add(retencionRenta);
    		}

    		if (retencion_iva > 0D) {
    			CompraRetencionEJB retencionIva = new CompraRetencionEJB();
    			retencionIva.setEstablecimiento(retencionAnterior.getEstablecimiento());
    			retencionIva.setPuntoEmision(retencionAnterior.getPuntoEmision());
    			retencionIva.setSecuencial(retencionAnterior.getSecuencial());
    			retencionIva.setAutorizacion(retencionAnterior.getAutorizacion());
    			retencionIva.setFechaEmision(retencionAnterior.getFechaEmision());
    			retencionIva.setCompraId(compra.getId());
    			retencionIva.setEjercicioFiscal(String.valueOf(ejercicio));
    			retencionIva.setBaseImponible(BigDecimal.valueOf(subtotal - descuentoSubtotal));
    			retencionIva.setImpuesto("I");
    			retencionIva.setPorcentajeRetencion(BigDecimal.valueOf(porcentaje_retencion_iva));
    			retencionIva.setValorRetenido(BigDecimal.valueOf(retencion_iva));
    			retencionIva.setCodigoImpuesto(sriIvaRetencion.getCodigo());
    			retencionIvaVector.add(retencionIva);
    		}
    	}

    	//Agrupar filas
    	retencionRentaVector = agruparRetenciones(retencionRentaVector);
    	retencionIvaVector = agruparRetenciones(retencionIvaVector);
    	compraRetencionIt = compraRetencionColeccion.iterator();
    	while (compraRetencionIt.hasNext()) {
    		CompraRetencionEJB retencionAnteriorPorEliminar = (compraRetencionIt.hasNext())?(CompraRetencionEJB)compraRetencionIt.next():null;
    		manager.remove(retencionAnteriorPorEliminar);
    	}
    	
		Iterator retencionRentaIterator = retencionRentaVector.iterator();
		while (retencionRentaIterator.hasNext()) {
			CompraRetencionEJB retencionRenta = (CompraRetencionEJB) retencionRentaIterator.next();
			manager.persist(retencionRenta);
		}
		Iterator retencionIvaIterator = retencionIvaVector.iterator();
		while (retencionIvaIterator.hasNext()) {
			CompraRetencionEJB retencionIva = (CompraRetencionEJB) retencionIvaIterator.next();
			manager.persist(retencionIva);
		}
	}
    
    private Vector<CompraRetencionEJB> agruparRetenciones(Vector<CompraRetencionEJB> retencionesVector) {
		Vector<CompraRetencionEJB> retencionesAgrupadas = new Vector<CompraRetencionEJB>();
		Iterator retencionesIterator = retencionesVector.iterator();
		while(retencionesIterator.hasNext()) {
			CompraRetencionEJB retencion = (CompraRetencionEJB) retencionesIterator.next();
			Iterator retencionesAgrupadasIterator = retencionesAgrupadas.iterator();
			boolean agrupada = false;
			while (retencionesAgrupadasIterator.hasNext()) {
				CompraRetencionEJB retencionAgrupada = (CompraRetencionEJB) retencionesAgrupadasIterator.next();
				if (agrupar(retencion, retencionAgrupada)) {
					double baseImponible = 0D;
					double valorRetenido = 0D;
					baseImponible = Double.valueOf(retencionAgrupada.getBaseImponible().doubleValue());
					baseImponible += Double.valueOf(retencion.getBaseImponible().doubleValue());
					valorRetenido = Double.valueOf(retencionAgrupada.getValorRetenido().doubleValue());
					valorRetenido += Double.valueOf(retencion.getValorRetenido().doubleValue());
						
					if (valorRetenido >= 0D) {
						retencionAgrupada.setBaseImponible(BigDecimal.valueOf(baseImponible));
						retencionAgrupada.setValorRetenido(BigDecimal.valueOf(valorRetenido));
					}
					agrupada = true;
				}
			}
			if (!agrupada)
				retencionesAgrupadas.add(retencion);
		}
		retencionesVector = null;
		retencionesVector = retencionesAgrupadas;
		return retencionesVector;
	}
    
    private boolean agrupar(CompraRetencionEJB retencion, CompraRetencionEJB retencionAgrupada) {
    	if (retencion.getEjercicioFiscal().equals(retencionAgrupada.getEjercicioFiscal()) && 
    		retencion.getEstablecimiento().equals(retencionAgrupada.getEstablecimiento()) &&
    		retencion.getPuntoEmision().equals(retencionAgrupada.getPuntoEmision()) &&
    		retencion.getSecuencial().equals(retencionAgrupada.getSecuencial()) &&
    		retencion.getAutorizacion().equals(retencionAgrupada.getAutorizacion()) &&
    		retencion.getImpuesto().equals(retencionAgrupada.getImpuesto()) &&
    		retencion.getPorcentajeRetencion().compareTo(retencionAgrupada.getPorcentajeRetencion()) == 0 &&
    		retencion.getCodigoImpuesto().equals(retencionAgrupada.getCodigoImpuesto()))
    		return true;
    	else
    		return false;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCarteraRetencionProveedorByCompraId(Long idCompra) throws GenericBusinessException {
    	//select distinct caret.* from CARTERA caret, CARTERA_DETALLE cardet, TIPO_DOCUMENTO td where cardet.CARTERA_ID = caret.ID and cardet.ID in (select caaf.CARTERADETALLE_ID from CARTERA_AFECTA caaf where caaf.CARTERAAFECTA_ID in (select cd.ID from COMPRA c, CARTERA ca, CARTERA_DETALLE cd where c.ID = ca.REFERENCIA_ID and c.TIPODOCUMENTO_ID = ca.TIPODOCUMENTO_ID and ca.ID = cd.CARTERA_ID and c.ID = 80)) and caret.TIPODOCUMENTO_ID = td.ID and td.CODIGO = 'CRE'
		String queryString = "select distinct caret from CarteraEJB caret, CarteraDetalleEJB cardet, TipoDocumentoEJB td where cardet.carteraId = caret.id and cardet.id in (select distinct caaf.carteradetalleId from CarteraAfectaEJB caaf where caaf.carteraafectaId in (select distinct cd.id from CompraEJB c, CarteraEJB ca, CarteraDetalleEJB cd where c.id = ca.referenciaId and c.tipodocumentoId = ca.tipodocumentoId and ca.id = cd.carteraId and c.id = " + idCompra + ")) and caret.tipodocumentoId = td.id and td.codigo = 'CRE'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();  
	}
    
    public CarteraIf procesarDatosRetenciones(CompraIf compra, Vector<CompraRetencionIf> compraRetencionVector) throws GenericBusinessException {
    	
    	//Este código comentado a continuación sirve para modificar arreglos de registros de compra_retencion con sus respectivas carteras
    	/*String queryStringTemp = "select distinct cr from CompraRetencionEJB cr where cr.secuencial >= '000036651' and cr.secuencial <= '000036829' and cr.autorizacion = '1109407638'";
    	Query queryTemp = manager.createQuery(queryStringTemp);
    	Iterator itRetencionesTemp = ((ArrayList) queryTemp.getResultList()).iterator();
    	while (itRetencionesTemp.hasNext()) {
    		CompraRetencionIf compRet = (CompraRetencionIf) itRetencionesTemp.next();
    		CompraIf compraTemp = getCompra(compRet.getCompraId());
    		ArrayList<CompraRetencionIf> retCompTemp = (ArrayList<CompraRetencionIf>)compraRetencionLocal.findCompraRetencionByCompraId(compraTemp.getId());
    		
    		String queryString = "select distinct car, cardet from CarteraEJB car, CarteraDetalleEJB cardet, TipoDocumentoEJB td where cardet.id in (select cf.carteradetalleId from CarteraAfectaEJB cf where cf.carteraafectaId in (select distinct cd.id from CompraEJB c, CarteraEJB ca, CarteraDetalleEJB cd where c.preimpreso = '" + compraTemp.getPreimpreso() + "' and c.tipodocumentoId = ca.tipodocumentoId and c.id = ca.referenciaId and ca.id = cd.carteraId)) and car.id = cardet.carteraId and car.tipodocumentoId = td.id and td.codigo = 'CRE'";
        	Query query = manager.createQuery(queryString);
        	Iterator itRetenciones = ((ArrayList) query.getResultList()).iterator();
        	CarteraIf carteraRetencion = null;
        	while (itRetenciones.hasNext()) {
        		Object[] o = (Object[]) itRetenciones.next();
        		carteraRetencion = (CarteraIf) o[0];
        		CarteraDetalleIf carteraDetalleRetencion = (CarteraDetalleIf) o[1];
        		DocumentoIf documentoRetencion = (DocumentoIf) documentoLocal.getDocumento(carteraDetalleRetencion.getDocumentoId());
        		Iterator itCompraRetencion = retCompTemp.iterator();
    			String impuesto = (documentoRetencion.getCodigo().equals("REIP"))?"I":"R";
        		while (itCompraRetencion.hasNext()) {
        			CompraRetencionIf compraRetencion = (CompraRetencionIf) itCompraRetencion.next();
        			if (compraRetencion.getImpuesto().equals(impuesto) && compraRetencion.getValorRetenido().doubleValue() == carteraDetalleRetencion.getValor().doubleValue()) {
        				//carteraDetalleRetencion.setPreimpreso(compraRetencion.getEstablecimiento()+"-"+compraRetencion.getPuntoEmision()+"-"+compraRetencion.getSecuencial());
        				carteraDetalleRetencion.setAutorizacion("1110002714");
        				carteraDetalleLocal.saveCarteraDetalle(carteraDetalleRetencion);
        			}
        		}
        		
        		//Obtener compra retencion a partir de compra y modificar secuencial y autorizacion
        		Map parameterMap = new HashMap();
        		parameterMap.put("compraId", compraTemp.getId());
        		itCompraRetencion = compraRetencionLocal.findCompraRetencionByQuery(parameterMap).iterator();
        		while (itCompraRetencion.hasNext()) {
        			CompraRetencionIf compraRetencion = (CompraRetencionIf) itCompraRetencion.next();
        			if (compraRetencion.getImpuesto().equals(impuesto) && compraRetencion.getValorRetenido().doubleValue() == carteraDetalleRetencion.getValor().doubleValue()) {
        				//compraRetencion.setEstablecimiento(carteraDetalleRetencion.getPreimpreso().substring(0,3));
        				//compraRetencion.setPuntoEmision(carteraDetalleRetencion.getPreimpreso().substring(4,7));
        				//compraRetencion.setSecuencial(carteraDetalleRetencion.getPreimpreso().substring(8, carteraDetalleRetencion.getPreimpreso().length()));
        				compraRetencion.setAutorizacion("1110002714");
        				compraRetencionLocal.saveCompraRetencion(compraRetencion);
        			}
        		}
        	}
    	}
    	CarteraIf carteraRetencion = null;*/
    	
    	//Obtener Cartera Retención y modificar preimpreso y autorización
    	//select distinct car.*, cardet.* from CARTERA car, CARTERA_DETALLE cardet, TIPO_DOCUMENTO td where cardet.ID in (select cf.CARTERADETALLE_ID from CARTERA_AFECTA cf where cf.CARTERAAFECTA_ID in (select distinct cd.ID from COMPRA c, CARTERA ca, CARTERA_DETALLE cd where c.PREIMPRESO like '%&a' and c.TIPODOCUMENTO_ID = ca.TIPODOCUMENTO_ID and c.ID = ca.REFERENCIA_ID and ca.ID = cd.CARTERA_ID)) and car.ID = cardet.CARTERA_ID and car.TIPODOCUMENTO_ID = td.ID and td.CODIGO = 'CRE'
    	String queryString = "select distinct car, cardet from CarteraEJB car, CarteraDetalleEJB cardet, TipoDocumentoEJB td where cardet.id in (select cf.carteradetalleId from CarteraAfectaEJB cf where cf.carteraafectaId in (select distinct cd.id from CompraEJB c, CarteraEJB ca, CarteraDetalleEJB cd where c.preimpreso = '" + compra.getPreimpreso() + "' and c.tipodocumentoId = ca.tipodocumentoId and c.id = ca.referenciaId and ca.id = cd.carteraId)) and car.id = cardet.carteraId and car.tipodocumentoId = td.id and td.codigo = 'CRE'";
    	Query query = manager.createQuery(queryString);
    	Iterator itRetenciones = ((ArrayList) query.getResultList()).iterator();
    	CarteraIf carteraRetencion = null;
    	while (itRetenciones.hasNext()) {
    		Object[] o = (Object[]) itRetenciones.next();
    		carteraRetencion = (CarteraIf) o[0];
    		CarteraDetalleIf carteraDetalleRetencion = (CarteraDetalleIf) o[1];
    		DocumentoIf documentoRetencion = (DocumentoIf) documentoLocal.getDocumento(carteraDetalleRetencion.getDocumentoId());
    		Iterator itCompraRetencion = compraRetencionVector.iterator();
			String impuesto = (documentoRetencion.getCodigo().equals("REIP"))?"I":"R";
    		while (itCompraRetencion.hasNext()) {
    			CompraRetencionIf compraRetencion = (CompraRetencionIf) itCompraRetencion.next();
    			if (compraRetencion.getImpuesto().equals(impuesto) && compraRetencion.getValorRetenido().doubleValue() == carteraDetalleRetencion.getValor().doubleValue()) {
    				carteraDetalleRetencion.setPreimpreso(compraRetencion.getEstablecimiento()+"-"+compraRetencion.getPuntoEmision()+"-"+compraRetencion.getSecuencial());
    				carteraDetalleRetencion.setAutorizacion(compraRetencion.getAutorizacion());
    				carteraDetalleLocal.saveCarteraDetalle(carteraDetalleRetencion);
    			}
    		}
    		
    		//Obtener compra retencion a partir de compra y modificar secuencial y autorizacion
    		Map parameterMap = new HashMap();
    		parameterMap.put("compraId", compra.getId());
    		itCompraRetencion = compraRetencionLocal.findCompraRetencionByQuery(parameterMap).iterator();
    		while (itCompraRetencion.hasNext()) {
    			CompraRetencionIf compraRetencion = (CompraRetencionIf) itCompraRetencion.next();
    			if (compraRetencion.getImpuesto().equals(impuesto) && compraRetencion.getValorRetenido().doubleValue() == carteraDetalleRetencion.getValor().doubleValue()) {
    				compraRetencion.setEstablecimiento(carteraDetalleRetencion.getPreimpreso().substring(0,3));
    				compraRetencion.setPuntoEmision(carteraDetalleRetencion.getPreimpreso().substring(4,7));
    				compraRetencion.setSecuencial(carteraDetalleRetencion.getPreimpreso().substring(8, carteraDetalleRetencion.getPreimpreso().length()));
    				compraRetencion.setAutorizacion(carteraDetalleRetencion.getAutorizacion());
    				compraRetencionLocal.saveCompraRetencion(compraRetencion);
    			}
    		}
    	}
    	
    	//Obtener asiento Retención a partir de cartera de retencion y modificar observación en asiento y glosas en detalles de asiento
    	
    	return carteraRetencion;
  
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCompraByCarteraDetalleComprobante(Long idCarteraDetalleComprobante) throws com.spirit.exception.GenericBusinessException {
		String queryString = "select distinct c from CompraEJB c, CarteraEJB car, CarteraDetalleEJB cardet where car.tipodocumentoId = c.tipodocumentoId and car.referenciaId = c.id and cardet.carteraId = car.id and cardet.id in (select distinct ca.carteraafectaId from CarteraDetalleEJB cd, CarteraAfectaEJB ca where cd.id = ca.carteradetalleId and cd.id = " + idCarteraDetalleComprobante + ")";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByOrdenAsociadaQuery(Map aMap) throws GenericBusinessException {
    	//SELECT C.PREIMPRESO FROM ORDEN_ASOCIADA OA, COMPRA C WHERE TIPO_ORDEN = 'OM' AND ORDEN_ID = 219 AND OA.`COMPRA_ID` = C.`ID`
    	String queryString = "select distinct c from CompraEJB c, OrdenAsociadaEJB oa where oa.tipoOrden = :tipoOrden and oa.ordenId = :ordenId and oa.compraId = c.id";
    	Query query = manager.createQuery(queryString);
    	query.setParameter("tipoOrden", (String) aMap.get("tipoOrden"));
    	query.setParameter("ordenId", (Long) aMap.get("ordenId"));
    	return query.getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraByFacturaDetalleId(Long facturaDetalleId) throws GenericBusinessException {
    	String queryString = "select distinct c from CompraEJB c, FacturaDetalleCompraAsociadaEJB fdca where c.id = fdca.compraId and fdca.facturaDetalleId = :facturaDetalleId";
    	Query query = manager.createQuery(queryString);
    	query.setParameter("facturaDetalleId", facturaDetalleId);
    	return query.getResultList();
    }
    
    public java.util.Collection findPurchases(Long providerId) throws GenericBusinessException {
		String selectFromString = "select distinct c, p from CompraEJB c, ClienteOficinaEJB co, ClienteEJB p";
		String whereJoinsString = "where c.proveedorId = co.id and co.clienteId = p.id";
		String whereConditionsString = "and c.proveedorId = :providerId";
		String orderByString = "order by c.fecha desc, c.codigo asc";
		String queryString = selectFromString + " " + whereJoinsString + " " + whereConditionsString + " " + orderByString;
		Query query = manager.createQuery(queryString);
		query.setParameter("providerId", providerId);
		return query.getResultList();
	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findOrdenCompraPresupuestoClienteOficinaClienteByCompraId(Long compraId) throws GenericBusinessException {
		String queryString = "select distinct oc, p, clo, cl from OrdenAsociadaEJB oa, OrdenCompraEJB oc, PresupuestoDetalleEJB pd, PresupuestoEJB p, ClienteOficinaEJB clo, ClienteEJB cl where " 
		+ " oa.compraId = " + compraId + " and oa.ordenId = oc.id  and oc.id = pd.ordenCompraId and pd.presupuestoId = p.id and p.clienteOficinaId = clo.id and clo.clienteId = cl.id";
		Query query = manager.createQuery(queryString);
		
		return query.getResultList();  
	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findOrdenMedioClienteOficinaClienteByCompraId(Long compraId) throws GenericBusinessException {
		String queryString = "select distinct om, clo, cl from OrdenAsociadaEJB oa, OrdenMedioEJB om, ClienteOficinaEJB clo, ClienteEJB cl where " 
		+ " oa.compraId = " + compraId + " and oa.ordenId = om.id and om.clienteOficinaId = clo.id and clo.clienteId = cl.id";
		Query query = manager.createQuery(queryString);
		
		return query.getResultList();  
	}
}
