package com.spirit.medios.session;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.bpm.campana.ProcesoOrdenTrabajoSessionService;
import com.spirit.bpm.procesos.ProcesoOrdenTrabajoLocal;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.ClienteOficinaSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.session.FacturaSessionLocal;
import com.spirit.facturacion.session.PedidoSessionLocal;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.session.EmpleadoSessionLocal;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.FileManagerSessionLocal;
import com.spirit.general.session.NumeradoresSessionLocal;
import com.spirit.general.session.OficinaSessionLocal;
import com.spirit.general.session.ParametroEmpresaSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.general.util.EnviarCorreo;
import com.spirit.medios.entity.EquipoEmpleadoIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleEJB;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoEJB;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.OrdenTrabajoProductoData;
import com.spirit.medios.entity.OrdenTrabajoProductoEJB;
import com.spirit.medios.entity.OrdenTrabajoProductoIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.generated._OrdenTrabajoSession;
import com.spirit.seguridad.handler.TipoUsuarioTimeTracker;
import com.spirit.server.QueryBuilder;
import com.spirit.timetracker.handler.TimeTrackerParametros;
import com.spirit.util.FileInputStreamSerializable;
import com.spirit.util.FindQuery;
import com.spirit.util.Utilitarios;

/**
 * The <code>OrdenTrabajoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.2 $, $Date: 2014/07/04 22:53:49 $
 *
 */
@Stateless
public class OrdenTrabajoSessionEJB extends _OrdenTrabajoSession implements OrdenTrabajoSessionRemote, OrdenTrabajoSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

	@EJB private OrdenTrabajoProductoSessionLocal ordenTrabajoProductoLocal; 

	@EJB private OrdenTrabajoDetalleSessionLocal ordenTrabajoDetalleLocal; 

	@EJB private NumeradoresSessionLocal numeradoresLocal;

	@EJB private FileManagerSessionLocal fileManagerLocal;
	
	@EJB private OficinaSessionLocal oficinaLocal;
	
	@EJB private EmpresaSessionLocal empresaLocal;
	
	@EJB private UtilitariosSessionLocal utilitariosLocal;
	
	@EJB private ProcesoOrdenTrabajoLocal procesoOrdenTrabajoLocal; 
	
	@EJB private PedidoSessionLocal pedidoLocal;
	
	@EJB private FacturaSessionLocal facturaLocal;
	
	@EJB private EmpleadoSessionLocal empleadoLocal;
	
	@EJB private EquipoEmpleadoSessionLocal equipoEmpleadoLocal;
	
	@EJB private ParametroEmpresaSessionLocal parametroEmpresaLocal;
	
	@EJB private ClienteOficinaSessionLocal clienteOficinaLocal;

	private DecimalFormat formatoSerial = new DecimalFormat("00000");
	
	private static final String ESTADO_REALIZADO = "R";

	@Resource private SessionContext ctx;
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/

	public java.util.Collection findOrdenTrabajoByQuery(int startIndex,int endIndex,Map aMap) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}

		String objectName = "e";
		String cadenaQuery = "from OrdenTrabajoEJB " + objectName + " where ";
		String orden = "order by e.codigo desc";
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);

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

	public int findOrdenTrabajoByQuerySize(Map aMap) {

		String objectName = "e";
		String cadenaQuery = "select count (*) from OrdenTrabajoEJB " + objectName + " where ";
		String orden = "";
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);

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
	
	public Collection findOrdenTrabajoByQueryAndByAsignadoaByEstados(boolean otCreadasTimetracker, Long idAsignado, String tipoUsuarioTimeTracker, Long idEmpresa, Long filtroClienteOficinaId, String... estados) 
	throws GenericBusinessException {
		
		ParametroEmpresaIf parametro = utilitariosLocal.getParametroEmpresa(idEmpresa,TimeTrackerParametros.TIPO_PARAMETRO, TimeTrackerParametros.PRESENTAR_TODAS_ORDENES_TRABAJO, "");
		boolean presentarTodosParaUsuarioSuper = false;
		
		if ( parametro.getValor() != null )
			presentarTodosParaUsuarioSuper = "S".equals(parametro.getValor().trim().toUpperCase()) ? true : false; 
		
		boolean esUsuarioSuper =  TipoUsuarioTimeTracker.SUPER.getLetra().equals(tipoUsuarioTimeTracker);
		boolean esSuperYPresentaTodo = esUsuarioSuper && presentarTodosParaUsuarioSuper;
		
		String objectName = "e";
		//String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = 
			" select distinct e " +
			" from  OrdenTrabajoEJB e,OrdenTrabajoDetalleEJB otd ,EquipoTrabajoEJB et,EquipoEmpleadoEJB ee," +
			" 		ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc " +
			" where ";
		
		/*if ( !where.trim().equals("") )
			queryString += (where+" and ");*/
		
		queryString += " otd.equipoId = et.id and et.id = ee.equipoId and ";
		queryString += " otd.ordenId = e.id and e.clienteoficinaId = co.id and co.clienteId = c.id and c.tipoclienteId = tc.id ";
		queryString += " and tc.empresaId = :empresaId";
		
		if(filtroClienteOficinaId != null){
			queryString += " and co.id = " + filtroClienteOficinaId + "";
		}
		
		if(otCreadasTimetracker){
			queryString += " and e.timetracker = 'S'";
		}
		
		//J=JEFES, P=POR EQUIPOS
		if("JP".contains(tipoUsuarioTimeTracker)){
			queryString += " and et.id in ( " +
			" select distinct ee1.equipoId from EquipoEmpleadoEJB ee1 " +
			" where  ee1.empleadoId = :empleadoId )" ;
		}
		//E=EJECUTIVAS
		else if("E".contains(tipoUsuarioTimeTracker)){ 
			queryString += " and ((et.id in ( " +
			" select distinct ee1.equipoId from EquipoEmpleadoEJB ee1 " +
			" where  ee1.empleadoId = :empleadoId )) or e.empleadoId = :empleadoId ) " ;
		}
		
		if ( !esSuperYPresentaTodo && estados!=null && estados.length > 0 ){
			queryString += " and (";
			for ( String estado : estados ){
				queryString += (" otd.estado = '"+estado+"' or");
			}
			queryString = queryString.substring(0,queryString.length()-3);
			queryString += " )";
		}
		
		queryString += " order by e.id desc";
		Query query = manager.createQuery(queryString);

		/*Set keys = aMap.keySet();
		Iterator it = keys.iterator();	

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}*/
		
		query.setParameter("empresaId", idEmpresa);
		
		if("PEJ".contains(tipoUsuarioTimeTracker))
		//if ( TipoUsuarioTimeTracker.POR_EQUIPO.getLetra().equals(tipoUsuarioTimeTracker) || "E".equals(tipoUsuarioTimeTracker))
			query.setParameter("empleadoId", idAsignado);

		return query.getResultList();	
	}

	public Collection findOrdenTrabajoByQueryAndByAsignadoa(int startIndex,int endIndex,Map aMap, Long idAsignado, Long idEmpresa) throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}

		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e from OrdenTrabajoEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc, OrdenTrabajoDetalleEJB otd where ";
		if ( !where.trim().equals("") )
			queryString += (where+" and ");
		queryString += " otd.ordenId = " + objectName + ".id and e.clienteoficinaId = co.id and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " order by e.id desc";
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

	public Collection findOrdenTrabajoByQueryAndByClienteId(int startIndex,int endIndex,Map aMap, Long idCliente, Long idEmpresa) throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}

		String objectName = "e";
		String cadenaQuery = "select distinct e from OrdenTrabajoEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where e.clienteoficinaId = co.id and co.clienteId = " + idCliente + " and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and ";
		String orden = "order by e.codigo desc";
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);

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

	public int findOrdenTrabajoByQueryAndByClienteIdSize(Map aMap, Long idCliente, Long idEmpresa) throws GenericBusinessException {
		String objectName = "e";
		String cadenaQuery = "select distinct count(*) from OrdenTrabajoEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c, TipoClienteEJB tc where e.clienteoficinaId = co.id and co.clienteId = " + idCliente + " and co.clienteId = c.id and c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + " and ";
		String orden = "order by e.codigo asc";
		Query query =FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);

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
	public java.util.Collection findOrdenTrabajoByClienteOficinaIdAndEstadoAndEmpresaId(java.lang.Long clienteoficinaId, java.lang.Long idEmpresa) {
		//select distinct * from orden_trabajo ot, empleado e, empresa em where ot.EMPLEADO_ID = e.ID and e.EMPRESA_ID = em.ID and em.ID = 1 and ot.CLIENTEOFICINA_ID = 60 and ot.ESTADO != 'C' and ot.ESTADO != 'S' and ot.ESTADO != 'R' and ot.ESTADO != 'E'
		String queryString = "select distinct ot from OrdenTrabajoEJB ot, EmpleadoEJB e, EmpresaEJB em where ot.empleadoId = e.id and e.empresaId = em.id and em.id = " + idEmpresa + " and ot.clienteoficinaId = " + clienteoficinaId + " and (ot.estado = 'P' or ot.estado = 'E') and ot.timetracker is null";
		String orderByPart = "";
		orderByPart += " order by ot.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenTrabajoPendienteByClienteOficinaIdAndTipoOrden(Long clienteOficinaId, String tipoOrden, boolean saveMode) throws GenericBusinessException {
		//select ot.* from orden_trabajo ot, orden_trabajo_detalle otd, subtipo_orden so, tipo_orden tor where ot.CLIENTEOFICINA_ID = 1 and ot.ESTADO = 'P' and ot.ID = otd.ORDEN_ID and otd.SUBTIPO_ID = so.ID and so.TIPOORDEN_ID = tor.ID and tor.CODIGO = 'ME'
		String queryString = "select distinct ot from OrdenTrabajoEJB ot, OrdenTrabajoDetalleEJB otd, SubtipoOrdenEJB so,  TipoOrdenEJB tor where ot.clienteoficinaId = " + clienteOficinaId + " and ot.id = otd.ordenId and otd.subtipoId = so.id and so.tipoordenId = tor.id and tor.codigo = '" + tipoOrden + "' and ot.timetracker is null";
		// Add a an order by on all primary keys to assure reproducable results.
		
		if(saveMode){
			queryString += " and (ot.estado = 'P' or ot.estado = 'E')";
		}
		
		String orderByPart = "";
		orderByPart += " order by ot.codigo desc";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public OrdenTrabajoIf procesarOrdenTrabajo(OrdenTrabajoIf model,List<OrdenTrabajoDetalleIf> modelDetalleList,List<ProductoClienteIf> modelProductoList,Long idEmpresa
			,FileInputStreamSerializable archivoOrden,String rutaDestino,String rutaSistema, String nombreArchivo) throws GenericBusinessException {
		try {

			String codigo = getMaximoCodigoOrdenTrabajo(model.getCodigo());
			int codigoOrdenTrabajo = 1;
			if (!codigo.equals("[null]")) {
				codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
				codigoOrdenTrabajo = Integer.parseInt(codigo.split(model.getCodigo())[1]) + 1;
			}
			model.setCodigo(model.getCodigo() + formatoSerial.format(codigoOrdenTrabajo));

			boolean exitoArchivo = false;
			//Se manda a guardar primero el archico al servidor, si no hubo exito no se guardar la ruta.
			if ( archivoOrden != null ){
				fileManagerLocal.guardarArchivoZip(archivoOrden, rutaDestino, nombreArchivo);
				exitoArchivo = true;
			}

			OrdenTrabajoEJB ordenTrabajo = registrarOrdenTrabajo(model,rutaSistema,nombreArchivo);
			if (!exitoArchivo){
				ordenTrabajo.setUrlPropuesta("");
			}
			manager.persist(ordenTrabajo);

			for (ProductoClienteIf modelProducto : modelProductoList) {
				OrdenTrabajoProductoData modelOrdenTrabajoProducto = new OrdenTrabajoProductoData();
				modelOrdenTrabajoProducto.setOrdenTrabajoId(ordenTrabajo.getPrimaryKey());
				OrdenTrabajoProductoIf ordenTrabajoProducto = registrarOrdenTrabajoProducto(modelOrdenTrabajoProducto, modelProducto);
				manager.merge(ordenTrabajoProducto);
			}
			
			return ordenTrabajo;
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			if ( e instanceof GenericBusinessException ){
				throw new GenericBusinessException(e.getMessage());
			}
			throw new GenericBusinessException("Se ha producido un error al insertar información en OrdenTrabajo-OrdenTrabajoDetalle");
		}
	}
	
	private String getMaximoCodigoOrdenTrabajo(String codigoParcialOrdenTrabajo) {
		String queryString = "select max(codigo) from OrdenTrabajoEJB ot where ot.codigo like '" + codigoParcialOrdenTrabajo + "%'";
		Query query = manager.createQuery(queryString);
		return query.getResultList().toString();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public OrdenTrabajoIf actualizarOrdenTrabajo(OrdenTrabajoIf model,List<ProductoClienteIf> modelProductoList, FileInputStreamSerializable archivoOrden,String rutaDestino, String nombreArchivo)throws GenericBusinessException {
		try{
			boolean exitoArchivo = false;
			//Se manda a guardar primero el archico al servidor, si no hubo exito no se guardar la ruta.
			if ( archivoOrden != null ){
				fileManagerLocal.guardarArchivoZip(archivoOrden, rutaDestino, nombreArchivo);
				exitoArchivo = true;
			}

			OrdenTrabajoIf ordenTrabajo = registrarOrdenTrabajo(model,rutaDestino,nombreArchivo);
			if (!exitoArchivo){
				ordenTrabajo.setUrlPropuesta("");
			}
			manager.merge(ordenTrabajo);

			//reviso si existe una factura creada a partir de esta orden y chequeo si se debe actualizar el vendedor y el equipo id.
			Collection presupuestos = findPresupuestoByOrdenTrabajoId(ordenTrabajo.getId());
			Iterator presupuestosIt = presupuestos.iterator();
			while(presupuestosIt.hasNext()){
				PresupuestoIf presupuesto = (PresupuestoIf)presupuestosIt.next();
				Map pedidoMap = new HashMap();
				pedidoMap.put("tiporeferencia", "P");
				pedidoMap.put("referencia", presupuesto.getCodigo());
				Collection pedidos = pedidoLocal.findPedidoByQuery(pedidoMap);
				Iterator pedidosIt = pedidos.iterator();
				while(pedidosIt.hasNext()){
					PedidoIf pedido = (PedidoIf)pedidosIt.next();
					if(ordenTrabajo.getEmpleadoId().compareTo(pedido.getVendedorId()) != 0
							|| pedido.getEquipoId() == null
							|| ordenTrabajo.getEquipoId().compareTo(pedido.getEquipoId()) != 0){
						pedido.setVendedorId(ordenTrabajo.getEmpleadoId());
						pedido.setEquipoId(ordenTrabajo.getEquipoId());
						manager.merge(pedido);
					}					
					Collection facturas = facturaLocal.findFacturaByPedidoId(pedido.getId());
					Iterator facturasIt = facturas.iterator();
					while(facturasIt.hasNext()){
						FacturaIf factura = (FacturaIf)facturasIt.next();
						if(ordenTrabajo.getEmpleadoId().compareTo(factura.getVendedorId()) != 0
								|| factura.getEquipoId() == null
								|| ordenTrabajo.getEquipoId().compareTo(factura.getEquipoId()) != 0){
							factura.setVendedorId(ordenTrabajo.getEmpleadoId());
							factura.setEquipoId(ordenTrabajo.getEquipoId());
							manager.merge(factura);
						}						
					}
				}
			}
			
			//Elimino todos los productos para volverlos a agregar luego en caso de que hayan cambiado.
			Collection<OrdenTrabajoProductoIf> modelProductosEliminadosList = ordenTrabajoProductoLocal.findOrdenTrabajoProductoByOrdenTrabajoId(ordenTrabajo.getId());

			for (OrdenTrabajoProductoIf modelProducto : modelProductosEliminadosList) {
				OrdenTrabajoProductoIf data = ordenTrabajoProductoLocal.getOrdenTrabajoProducto(modelProducto.getId());
				manager.remove(data);
			}

			//Agrego los productos
			for (ProductoClienteIf modelProducto : modelProductoList) {
				OrdenTrabajoProductoData modelOrdenTrabajoProducto = new OrdenTrabajoProductoData();
				modelOrdenTrabajoProducto.setOrdenTrabajoId(ordenTrabajo.getPrimaryKey());
				OrdenTrabajoProductoIf ordenTrabajoProducto = registrarOrdenTrabajoProducto(modelOrdenTrabajoProducto, modelProducto);
				manager.merge(ordenTrabajoProducto);
			}
			
			return ordenTrabajo;
			
		} catch(Exception e){
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Se ha producido un error al actualizar información en OrdenTrabajo-OrdenTrabajoDetalle");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarOrdenTrabajo(OrdenTrabajoIf model, OrdenTrabajoDetalleIf modelDetalle)throws GenericBusinessException {
		try{
			OrdenTrabajoIf ordenTrabajo = registrarOrdenTrabajo(model);
			manager.merge(ordenTrabajo);
	
			OrdenTrabajoDetalleIf ordenTrabajoDetalle = registrarOrdenTrabajoDetalle(modelDetalle);
			manager.merge(ordenTrabajoDetalle);			
			
		} catch(Exception e){
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Error al registrar los detalles de la Orden de Trabajo");
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarOrdenTrabajoDetalle(OrdenTrabajoIf model,Collection<OrdenTrabajoDetalleIf> modelDetalleList,
		Collection<OrdenTrabajoDetalleIf> ordenDetalleEliminadasList, 
		Collection<FileInputStreamSerializable> archivosDescripcion,
		Collection<FileInputStreamSerializable> archivosPropuesta,String rutaDestino,String rutaSistema,
		boolean esActualizacion)throws GenericBusinessException {
		
		try{
			OrdenTrabajoIf ordenTrabajo = registrarOrdenTrabajo(model,rutaSistema,"saveupdate");
			manager.merge(ordenTrabajo);
	
			for (OrdenTrabajoDetalleIf modelDetalleEliminado : ordenDetalleEliminadasList) {
				OrdenTrabajoDetalleIf ordenTrabajoDetalle = ordenTrabajoDetalleLocal.getOrdenTrabajoDetalle(modelDetalleEliminado.getId());
				manager.remove(ordenTrabajoDetalle);
			}
	
			Iterator itDescripcion = archivosDescripcion != null? archivosDescripcion.iterator():null; 
			Iterator itPropuesta = archivosPropuesta != null? archivosPropuesta.iterator():null;			
			boolean exitoDescripcion = false, exitoPropuesta = false;
			
			//lleno un mapa con el empleado y el texto del correo
			HashMap<EmpleadoIf,String> receptoresCorreo = new HashMap<EmpleadoIf,String>();
			//si es una orden generica creada en el timetracker
			//entonces el correo debe ir tambien para el ejecutivo para alertarlo de actualizar la orden
			if(model.getDescripcion().length() > 14 && model.getDescripcion().substring(0,14).equals("ORDEN GENERICA")){
				EmpleadoIf ejecutivo = empleadoLocal.getEmpleado(model.getEmpleadoId());
				receptoresCorreo.put(ejecutivo, model.getDescripcion());
			}
			
			for (Iterator itDetalle = modelDetalleList.iterator();itDetalle.hasNext();){
				OrdenTrabajoDetalleIf modelDetalle = (OrdenTrabajoDetalleIf) itDetalle.next();
				FileInputStreamSerializable archivoDescripcion = itDescripcion!=null? (FileInputStreamSerializable) itDescripcion.next() : null;
				FileInputStreamSerializable archivoPropuesta = itPropuesta!=null? (FileInputStreamSerializable) itPropuesta.next() : null;
				exitoDescripcion = false; exitoPropuesta = false;
				try{
					if ( archivoDescripcion!=null )
						fileManagerLocal.guardarArchivoZip(archivoDescripcion, rutaDestino, archivoDescripcion.getNombreArchivo());
					exitoDescripcion = true;
				} catch(Exception exa){
					System.out.println("No se guardo archivo de Descripcion con nombre: "+archivoDescripcion.getNombreArchivo());
				}
				try{
					if ( archivoPropuesta!=null )
						fileManagerLocal.guardarArchivoZip(archivoPropuesta, rutaDestino, archivoPropuesta.getNombreArchivo());
					exitoPropuesta = true;
				} catch(Exception exa){
					System.out.println("No se guardo archivo de Propuesta con nombre: "+archivoPropuesta.getNombreArchivo());
				}
				
				//Correo: lleno un mapa con el empleado y el texto del correo, si la orden no esta Realizada
				if(!modelDetalle.getEstado().equals(ESTADO_REALIZADO))
					generarListaEmpleadosCorreo(receptoresCorreo, modelDetalle);				
					
				modelDetalle.setOrdenId(ordenTrabajo.getPrimaryKey());
				OrdenTrabajoDetalleIf ordenTrabajoDetalle = registrarOrdenTrabajoDetalle(modelDetalle,rutaSistema);
				if (!exitoDescripcion)
					ordenTrabajoDetalle.setUrlDescripcion("");
				if (!exitoPropuesta)
					ordenTrabajoDetalle.setUrlPropuesta("");
	
				manager.merge(ordenTrabajoDetalle);
			}
			
			//Para enviar correo
			envioCorreo(ordenTrabajo,receptoresCorreo);			
			
			//procesoOrdenTrabajoLocal.procesarOrdenTrabajo(ordenTrabajo,esActualizacion);
			
			OficinaIf oficina = oficinaLocal.getOficina(model.getOficinaId());
			EmpresaIf empresa = empresaLocal.getEmpresa(oficina.getEmpresaId());
			ProcesoOrdenTrabajoSessionService procesoOrdenTrabajoSessionLocal = 
				procesoOrdenTrabajoLocal.getProcesoOrdenTrabajoSessionService(empresa);
			if ( procesoOrdenTrabajoSessionLocal != null )
				procesoOrdenTrabajoSessionLocal.procesarOrdenTrabajo(ordenTrabajo, esActualizacion);
				
			
		} catch(Exception e){
			ctx.setRollbackOnly();
			e.printStackTrace();
			if ( e instanceof GenericBusinessException )
				throw new GenericBusinessException(e.getMessage());
			throw new GenericBusinessException("Error al registrar los detalles de la Orden de Trabajo");
		}

	}

	private void generarListaEmpleadosCorreo(HashMap<EmpleadoIf, String> receptoresCorreo,OrdenTrabajoDetalleIf modelDetalle) throws GenericBusinessException {
		
		//Si la opcion envio de correo esta seteada como S
		Collection pmEnviarCorreo = parametroEmpresaLocal.findParametroEmpresaByCodigo("ENVIAR_CORREO_ORDEN_TRABAJO");
		if(pmEnviarCorreo.size() > 0){
			ParametroEmpresaIf parametroEnvioCorreo = (ParametroEmpresaIf)pmEnviarCorreo.iterator().next();
			if(parametroEnvioCorreo.getValor().equals("S")){
				
				EmpleadoIf empleado = empleadoLocal.getEmpleado(modelDetalle.getAsignadoaId());
				receptoresCorreo.put(empleado,modelDetalle.getDescripcion());
				
				//reviso si el empleado es jefe del equipo
				HashMap mapEquipoEmpleado = new HashMap();
				mapEquipoEmpleado.put("empleadoId", empleado.getId());
				mapEquipoEmpleado.put("equipoId", modelDetalle.getEquipoId());
				Collection empleadoEquipo = equipoEmpleadoLocal.findEquipoEmpleadoByQuery(mapEquipoEmpleado);
				if(empleadoEquipo.size() > 0){
					EquipoEmpleadoIf empleadoEquipoIf = (EquipoEmpleadoIf)empleadoEquipo.iterator().next();
					//si el empleado no es jefe debo enviarle un correo tambien al jefe o jefes de equipo
					if(empleadoEquipoIf.getJefe().equals("N")){
						Collection equipoEmpleado = equipoEmpleadoLocal.findEquipoEmpleadoByEquipoId(modelDetalle.getEquipoId());
						Iterator equipoEmpleadoIt = equipoEmpleado.iterator();
						while(equipoEmpleadoIt.hasNext()){
							EquipoEmpleadoIf equipoEmpleadoIf = (EquipoEmpleadoIf)equipoEmpleadoIt.next();
							if(equipoEmpleadoIf.getJefe().equals("S")){
								EmpleadoIf empleadoJefe = empleadoLocal.getEmpleado(equipoEmpleadoIf.getEmpleadoId());
								//si el jefe aun no esta como receptor de correo
								if(receptoresCorreo.get(empleadoJefe) == null)
									receptoresCorreo.put(empleadoJefe,modelDetalle.getDescripcion());
							}
						}
					}
				}
			}
		}
	}

	private void envioCorreo(OrdenTrabajoIf ordenTrabajo, HashMap<EmpleadoIf,String> receptoresCorreo) throws GenericBusinessException {
		
		Iterator receptoresCorreoIt = receptoresCorreo.keySet().iterator();
		while(receptoresCorreoIt.hasNext()){
			EmpleadoIf receptorCorreo = (EmpleadoIf)receptoresCorreoIt.next();
			
			if(receptorCorreo.getEmailOficina() != null){
				
				//formo el cuerpo del mensaje
				EmpleadoIf ejecutivo = empleadoLocal.getEmpleado(ordenTrabajo.getEmpleadoId());
				ClienteOficinaIf clienteOficina = clienteOficinaLocal.getClienteOficina(ordenTrabajo.getClienteoficinaId());
				String mensaje = "Ejecutiva(o): " + ejecutivo.getNombres() + " " + ejecutivo.getApellidos();
				mensaje = mensaje + "\n" + "Cliente: " + clienteOficina.getDescripcion();
				mensaje = mensaje + "\n" + "Descripción: \n" + receptoresCorreo.get(receptorCorreo);
				
				//enviarNotificacion(empleado.getEmailOficina(), "spirit@umacreativa.com", "SPumacreativa2012", ordenTrabajo.getCodigo());
				Collection hosts = parametroEmpresaLocal.findParametroEmpresaByCodigo("SMTP_HOST_NAME");
				Collection puertos = parametroEmpresaLocal.findParametroEmpresaByCodigo("SMTP_HOST_PORT");
				Collection remitentes = parametroEmpresaLocal.findParametroEmpresaByCodigo("SMTP_SPIRIT_AUTH_USER");
				Collection claves = parametroEmpresaLocal.findParametroEmpresaByCodigo("SMTP_SPIRIT_AUTH_PWD");
				if(hosts.size() > 0 && puertos.size() > 0 && remitentes.size() > 0 && claves.size() > 0){
					ParametroEmpresaIf host = (ParametroEmpresaIf)hosts.iterator().next();
					ParametroEmpresaIf puerto = (ParametroEmpresaIf)puertos.iterator().next();
					ParametroEmpresaIf remitente = (ParametroEmpresaIf)remitentes.iterator().next();
					ParametroEmpresaIf clave = (ParametroEmpresaIf)claves.iterator().next();
					
					String cuerpoCorreo = "";
					if(ordenTrabajo.getFechaActualizacion() != null){
						cuerpoCorreo = "Se actualizó el trabajo asignado en la OT: ";
					}else{
						cuerpoCorreo = "Se le asignó un trabajo en la OT: ";
					}
					
					String asunto = "ORDEN DE TRABAJO ASIGNADA, CODIGO: ";
					EnviarCorreo correo = new EnviarCorreo(host.getValor(), puerto.getValor(), remitente.getValor(), clave.getValor(), 
							receptorCorreo.getEmailOficina(), ordenTrabajo.getCodigo(), mensaje, asunto, cuerpoCorreo);
					correo.start();
				}					
			}
		}
	}	

	//String urlCarpetaServidor,FileInputStreamSerializable archivoOrden,List<FileInputStreamSerializable> archivosDescripcion,List<FileInputStreamSerializable> archivosPropuesta

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarOrdenTrabajo(Long ordenTrabajoId) throws GenericBusinessException {
		try {
			OrdenTrabajoEJB data = manager.find(OrdenTrabajoEJB.class, ordenTrabajoId);
			Collection<OrdenTrabajoProductoIf> modelProductoList = ordenTrabajoProductoLocal.findOrdenTrabajoProductoByOrdenTrabajoId(ordenTrabajoId);
			Collection<OrdenTrabajoDetalleIf> modelDetalleList = ordenTrabajoDetalleLocal.findOrdenTrabajoDetalleByOrdenId(ordenTrabajoId);

			for (OrdenTrabajoProductoIf modelProducto : modelProductoList) {
				OrdenTrabajoProductoIf ordenTrabajoProducto = ordenTrabajoProductoLocal.getOrdenTrabajoProducto(modelProducto.getId());
				manager.remove(ordenTrabajoProducto);
			}

			for (OrdenTrabajoDetalleIf modelDetalle : modelDetalleList) {
				OrdenTrabajoDetalleIf ordenTrabajoDetalle = ordenTrabajoDetalleLocal.getOrdenTrabajoDetalle(modelDetalle.getId());
				manager.remove(ordenTrabajoDetalle);
			}
			manager.remove(data);
			manager.flush();

		} catch (Exception e) {
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al eliminar información en OrdenTrabajo");
		}
	}

	/**
	 * @param model
	 * @return
	 * @throws GenericBusinessException 
	 */
	private OrdenTrabajoEJB registrarOrdenTrabajo(OrdenTrabajoIf model, String ruta, String nombreArchivo) 
	throws GenericBusinessException {
		try{
			OrdenTrabajoEJB ordenTrabajo = new OrdenTrabajoEJB();
			ordenTrabajo.setId(model.getId());
			ordenTrabajo.setCodigo(model.getCodigo());
			ordenTrabajo.setDescripcion(model.getDescripcion());
			ordenTrabajo.setOficinaId(model.getOficinaId());
			ordenTrabajo.setClienteoficinaId(model.getClienteoficinaId());
			ordenTrabajo.setCampanaId(model.getCampanaId());
			ordenTrabajo.setEmpleadoId(model.getEmpleadoId());
			ordenTrabajo.setFecha(model.getFecha());
			ordenTrabajo.setFechalimite(model.getFechalimite());
			ordenTrabajo.setFechaentrega(model.getFechaentrega());
			
			if(nombreArchivo!=null){
				if(nombreArchivo.equals("saveupdate") ){
					ordenTrabajo.setUrlPropuesta(model.getUrlPropuesta());
				}
				else if(!nombreArchivo.equals("") ){
					int puntoExt = nombreArchivo.lastIndexOf(".");
		    		String strFilename = nombreArchivo.substring(0, puntoExt) + ".zip";	    	
		    		ordenTrabajo.setUrlPropuesta(ruta + strFilename.replaceAll(" ", "_"));
				}					
				else{
					ordenTrabajo.setUrlPropuesta("");
				}
			}        

			ordenTrabajo.setEstado(model.getEstado());
			ordenTrabajo.setObservacion(model.getObservacion());
			ordenTrabajo.setUsuarioCreacionId(model.getUsuarioCreacionId());
			ordenTrabajo.setFechaCreacion(model.getFechaCreacion());
			ordenTrabajo.setUsuarioActualizacionId(model.getUsuarioActualizacionId());
			ordenTrabajo.setFechaActualizacion(model.getFechaActualizacion());
			ordenTrabajo.setEquipoId(model.getEquipoId());
			ordenTrabajo.setTimetracker(model.getTimetracker());
			
			return ordenTrabajo;
			
		} catch(Exception e){
			throw new GenericBusinessException("Error al registrar los datos generales de la Orden de Trabajo");
		}
	}
	
	private OrdenTrabajoEJB registrarOrdenTrabajo(OrdenTrabajoIf model) throws GenericBusinessException {
		try{
			OrdenTrabajoEJB ordenTrabajo = new OrdenTrabajoEJB();
			ordenTrabajo.setId(model.getId());
			ordenTrabajo.setCodigo(model.getCodigo());
			ordenTrabajo.setDescripcion(model.getDescripcion());
			ordenTrabajo.setOficinaId(model.getOficinaId());
			ordenTrabajo.setClienteoficinaId(model.getClienteoficinaId());
			ordenTrabajo.setCampanaId(model.getCampanaId());
			ordenTrabajo.setEmpleadoId(model.getEmpleadoId());
			ordenTrabajo.setFecha(model.getFecha());
			ordenTrabajo.setFechalimite(model.getFechalimite());
			ordenTrabajo.setFechaentrega(model.getFechaentrega());
			ordenTrabajo.setUrlPropuesta(model.getUrlPropuesta());
			ordenTrabajo.setEstado(model.getEstado());
			ordenTrabajo.setObservacion(model.getObservacion());
			ordenTrabajo.setUsuarioCreacionId(model.getUsuarioCreacionId());
			ordenTrabajo.setFechaCreacion(model.getFechaCreacion());
			ordenTrabajo.setUsuarioActualizacionId(model.getUsuarioActualizacionId());
			ordenTrabajo.setFechaActualizacion(model.getFechaActualizacion());
			
			return ordenTrabajo;
			
		} catch(Exception e){
			throw new GenericBusinessException("Error al registrar los datos generales de la Orden de Trabajo");
		}
	}

	private OrdenTrabajoDetalleEJB registrarOrdenTrabajoDetalle(OrdenTrabajoDetalleIf modelDetalle,String urlCarpetaServidor)
	throws GenericBusinessException {
		try{
			OrdenTrabajoDetalleEJB ordenTrabajoDetalle = new OrdenTrabajoDetalleEJB();
			
			int posicionUltimoSlashPropuesta = -1;
			int posicionUltimoSlashDescripcion = -1;
			if(modelDetalle.getUrlPropuesta() != null) 
				posicionUltimoSlashPropuesta = modelDetalle.getUrlPropuesta().lastIndexOf("\\");
			if(modelDetalle.getUrlDescripcion() != null) 
				posicionUltimoSlashDescripcion = modelDetalle.getUrlDescripcion().lastIndexOf("\\");

			String nombreArchivoPropuesta = ""; 
			String nombreArchivoDescripcion = "";
			if(posicionUltimoSlashPropuesta != -1)
				nombreArchivoPropuesta = modelDetalle.getUrlPropuesta().substring(posicionUltimoSlashPropuesta+1);
			if(posicionUltimoSlashDescripcion != -1)
				nombreArchivoDescripcion = modelDetalle.getUrlDescripcion().substring(posicionUltimoSlashDescripcion+1);
			 
			ordenTrabajoDetalle.setId(modelDetalle.getId());
			ordenTrabajoDetalle.setOrdenId(modelDetalle.getOrdenId());
			ordenTrabajoDetalle.setSubtipoId(modelDetalle.getSubtipoId());
			ordenTrabajoDetalle.setEquipoId(modelDetalle.getEquipoId());
			ordenTrabajoDetalle.setAsignadoaId(modelDetalle.getAsignadoaId());
			ordenTrabajoDetalle.setFechalimite(modelDetalle.getFechalimite());
			ordenTrabajoDetalle.setFechaentrega(modelDetalle.getFechaentrega());

			if (!nombreArchivoDescripcion.equals("")){
				int puntoExt = nombreArchivoDescripcion.lastIndexOf(".");
		    	String strFilename = nombreArchivoDescripcion.substring(0, puntoExt) + ".zip";	    	
		        ordenTrabajoDetalle.setUrlDescripcion(urlCarpetaServidor + strFilename.replaceAll(" ", "_"));
		    } else
				ordenTrabajoDetalle.setUrlDescripcion(null);

			if (!nombreArchivoPropuesta.equals("") ){
				int puntoExt = nombreArchivoPropuesta.lastIndexOf(".");
		    	String strFilename = nombreArchivoPropuesta.substring(0, puntoExt) + ".zip";	    	
		        ordenTrabajoDetalle.setUrlPropuesta(urlCarpetaServidor + strFilename.replaceAll(" ", "_"));
			} else 
				ordenTrabajoDetalle.setUrlPropuesta(null);

			ordenTrabajoDetalle.setDescripcion(modelDetalle.getDescripcion());
			ordenTrabajoDetalle.setEstado(modelDetalle.getEstado());
			ordenTrabajoDetalle.setFecha(modelDetalle.getFecha());

			return ordenTrabajoDetalle;
			
		} catch(Exception e){
			throw new GenericBusinessException("Error al registrar el detalle de la Orden de Trabajo");
		}
	}
	
	private OrdenTrabajoDetalleEJB registrarOrdenTrabajoDetalle(OrdenTrabajoDetalleIf modelDetalle) throws GenericBusinessException {
		try{
			OrdenTrabajoDetalleEJB ordenTrabajoDetalle = new OrdenTrabajoDetalleEJB();
			
			ordenTrabajoDetalle.setId(modelDetalle.getId());
			ordenTrabajoDetalle.setOrdenId(modelDetalle.getOrdenId());
			ordenTrabajoDetalle.setSubtipoId(modelDetalle.getSubtipoId());
			ordenTrabajoDetalle.setEquipoId(modelDetalle.getEquipoId());
			ordenTrabajoDetalle.setAsignadoaId(modelDetalle.getAsignadoaId());
			ordenTrabajoDetalle.setFechalimite(modelDetalle.getFechalimite());
			ordenTrabajoDetalle.setFechaentrega(modelDetalle.getFechaentrega());
			ordenTrabajoDetalle.setUrlDescripcion(modelDetalle.getUrlDescripcion());
			ordenTrabajoDetalle.setUrlPropuesta(modelDetalle.getUrlPropuesta());
			ordenTrabajoDetalle.setDescripcion(modelDetalle.getDescripcion());
			ordenTrabajoDetalle.setEstado(modelDetalle.getEstado());
			ordenTrabajoDetalle.setFecha(modelDetalle.getFecha());

			return ordenTrabajoDetalle;
			
		} catch(Exception e){
			throw new GenericBusinessException("Error al registrar el detalle de la Orden de Trabajo");
		}
	}

	private OrdenTrabajoProductoEJB registrarOrdenTrabajoProducto(OrdenTrabajoProductoIf modelOrdenTrabajoProducto, ProductoClienteIf modelProducto) 
	throws GenericBusinessException {
		try{
			OrdenTrabajoProductoEJB ordenTrabajoProducto = new OrdenTrabajoProductoEJB();

			ordenTrabajoProducto.setId(modelOrdenTrabajoProducto.getId());
			ordenTrabajoProducto.setProductoClienteId(modelProducto.getId());
			ordenTrabajoProducto.setOrdenTrabajoId(modelOrdenTrabajoProducto.getOrdenTrabajoId());

			return ordenTrabajoProducto;
		} catch(Exception e){
			throw new GenericBusinessException("Error al registrar los productos de la Orden de Trabajo");
		}
	}
	
	public Collection findOrdenTrabajoByQueryByTipoFechaByFechaInicioByFechaFinByEmpresaIdByTipoOrdenByResponsableAndByEstados(Map aMap, String ordenporFecha, java.sql.Date fechaInicio, java.sql.Date fechaFin, Long idEmpresa, Long idTipoOrden, Long idEmpleadoResponsable, String... estados){
		String objectName = "ot";
		
		//tiene que ir antes de llenar el "where"
		boolean hayRegistros = (Boolean)aMap.get("responsable");
		aMap.remove("responsable");
		
		String where = QueryBuilder.buildWhere(aMap, objectName);
				
		String queryString = "";
		//Si no hay registros en el log deberia traer registros igual a partir del ot detalle
		if(idEmpleadoResponsable.compareTo(0L) != 0 && hayRegistros){
			queryString = "select distinct ot from OrdenTrabajoEJB " + objectName + ", OrdenTrabajoDetalleEJB otd, OficinaEJB ofi, SubtipoOrdenEJB sto, TiempoParcialDotEJB tpd, TiempoParcialDotDetalleEJB tpdd " +
					"where (tpd.usuarioAsignadoId = " + idEmpleadoResponsable + " or otd.asignadoaId = " + idEmpleadoResponsable + ") and otd.id = tpd.idOrdenTrabajoDetalle and tpd.id = tpdd.idTiempoParcialDot and ";
		}else if(idEmpleadoResponsable.compareTo(0L) != 0){			
			queryString = "select distinct ot from OrdenTrabajoEJB " + objectName + ", OrdenTrabajoDetalleEJB otd, OficinaEJB ofi, SubtipoOrdenEJB sto, TiempoParcialDotEJB tpd, TiempoParcialDotDetalleEJB tpdd " +
					"where otd.asignadoaId = " + idEmpleadoResponsable + " and otd.id = tpd.idOrdenTrabajoDetalle and tpd.id = tpdd.idTiempoParcialDot and ";
		}else{
			queryString = "select distinct ot from OrdenTrabajoEJB " + objectName + ", OrdenTrabajoDetalleEJB otd, OficinaEJB ofi, SubtipoOrdenEJB sto, TiempoParcialDotEJB tpd, TiempoParcialDotDetalleEJB tpdd " +
					"where otd.id = tpd.idOrdenTrabajoDetalle and tpd.id = tpdd.idTiempoParcialDot and ";
		}
		
		if(!where.trim().equals("")){
			queryString += (where+" and ");
		}		
		
		if(idTipoOrden.compareTo(0L) != 0){
			queryString = queryString + "sto.tipoordenId = " + idTipoOrden + " and "; 
		}	
		
		queryString += "otd.ordenId = ot.id and ofi.empresaId = " + idEmpresa + " and otd.subtipoId = sto.id and tpdd.fecha >= :fechaInicio and tpdd.fecha <= :fechaFin";
		
		if ( estados!=null && estados.length > 0 ){
			queryString += " and (";
			for ( String estado : estados ){
				queryString += (" otd.estado = '"+estado+"' or");
			}
			queryString = queryString.substring(0,queryString.length()-3);
			queryString += " )";
		}
		
		queryString += " order by ot.id desc";
				
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio", utilitariosLocal.resetTimestampStartDate(new Timestamp(fechaInicio.getTime())).getTime());
	 	query.setParameter("fechaFin", utilitariosLocal.resetTimestampEndDate(new Timestamp(fechaFin.getTime())).getTime());
	 	
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();	

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}

		return query.getResultList();
	}
	
	public Collection findOrdenTrabajoByQueryByTipoFechaByFechaInicioByFechaFinByEmpresaIdByTipoOrdenAndByEstados(Map aMap, String ordenporFecha, java.sql.Date fechaInicio, java.sql.Date fechaFin, Long idEmpresa, Long idTipoOrden, boolean tipoTodos, String... estados){
		String objectName = "ot";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct ot from OrdenTrabajoEJB " + objectName + ", OrdenTrabajoDetalleEJB otd, OficinaEJB ofi, SubtipoOrdenEJB sto where ";
		
		if(!where.trim().equals("")){
			queryString += (where+" and ");
		}
		
		if(idTipoOrden.compareTo(0L) != 0){
			queryString = queryString + "sto.tipoordenId = " + idTipoOrden + " and "; 
		}
		
		queryString += "otd.ordenId = ot.id and ofi.empresaId = " + idEmpresa + " and otd.subtipoId = sto.id";// and otd.fecha between :fechaInicio and :fechaFin";
		
		if(!ordenporFecha.equals("")){
			queryString += " and otd." + ordenporFecha + " between :fechaInicio and :fechaFin";
		}else{
			queryString += " and ot.fecha between :fechaInicio and :fechaFin";
		}
		
		if ( estados!=null && estados.length > 0 ){
			queryString += " and (";
			for ( String estado : estados ){
				queryString += (" otd.estado = '"+estado+"' or");
			}
			queryString = queryString.substring(0,queryString.length()-3);
			queryString += " )";
		}
		
		queryString += " order by ot.id desc";
				
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",Utilitarios.resetTimestampStartDate(new Timestamp(fechaInicio.getTime())));
	 	query.setParameter("fechaFin",Utilitarios.resetTimestampEndDate(new Timestamp(fechaFin.getTime())));
	 	
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
	public java.util.Collection findPresupuestoByOrdenTrabajoId(java.lang.Long idOrden) throws GenericBusinessException {
		
		String objectName = "m";
		String queryString = "select distinct m from PresupuestoEJB "
			+ objectName
			+ ", OrdenTrabajoDetalleEJB otd where  m.ordentrabajodetId = otd.id and otd.ordenId = "
			+ idOrden + " order by m.id desc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
}
