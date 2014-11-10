package com.spirit.bpm.campana;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.bpm.BPMInterceptor;
import com.spirit.bpm.impl.SpiritServerProcess;
import com.spirit.bpm.process.SpiritProcessDefinition;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.ClientParams;
import com.spirit.bpm.process.elements.InstanciaProceso;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.bpm.process.elemets.state.EnumTareaState;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.ClienteOficinaSessionLocal;
import com.spirit.crm.session.ClienteSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.session.EmpleadoSessionLocal;
import com.spirit.general.session.OficinaSessionLocal;
import com.spirit.general.session.TipoOrdenSessionLocal;
import com.spirit.general.session.UsuarioSessionLocal;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.medios.handler.EstadoPresupuesto;
import com.spirit.medios.session.OrdenTrabajoDetalleSessionLocal;
import com.spirit.medios.session.SubtipoOrdenSessionLocal;
import com.spirit.server.QueryBuilder;

@Stateless
@Interceptors(value = BPMInterceptor.class)
public class ProcesoOrdenTrabajoCreacionalSessionEJB extends SpiritServerProcess
		implements ProcesoOrdenTrabajoCreacionalSessionLocal,
		ProcesoOrdenTrabajoSessionRemote {
	
	@PersistenceContext(unitName = "spirit")
	   private EntityManager manager;
	
	//@EJB private OrdenTrabajoSessionLocal ordenTrabajoLocal;
	@EJB private OrdenTrabajoDetalleSessionLocal ordenTrabajoDetalleSessionLocal;
	@EJB private SubtipoOrdenSessionLocal subTipoOrdenLocal;
	@EJB private TipoOrdenSessionLocal tipoOrdenLocal;
	@EJB private OficinaSessionLocal oficinaLocal;
	@EJB private EmpleadoSessionLocal empleadoSessionLocal;
	@EJB private ClienteOficinaSessionLocal clienteOficinaLocal;
	@EJB private ClienteSessionLocal clienteLocal;
	@EJB private UsuarioSessionLocal usuarioSessionLocal;

	public static final String ORDEN_TRABAJO_ID_PARAM = "ordenTrabajoId";
	public static final String CAMPANA_ID_PARAM = "campanaId";
	public static final String LISTA_VALORES_PARAM = "listaValores";
	public static final String SUBJECT_PARAM = "subject";
	private String saltoLinea = "\n";

	@Override
	public void procesarOrdenTrabajo(OrdenTrabajoIf ordenTrabajoIf,boolean esActualizacion)
			throws GenericBusinessException, BPMException {

		if ( esActualizacion )
			return;
		
		UsuarioIf usuario = usuarioSessionLocal.getUsuario(ordenTrabajoIf
				.getUsuarioCreacionId());
		ClientParams cp = new ClientParams(usuario.getUsuario(), usuario.getClave());

		// INICIAR LA ORDEN Y POR CADA DETALLE INICIAR UNA NUEVA TAREA ASIGNADA
		// AL USUARIO ESPECIFICO
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(DESCRIPCION_PARAM,
				"Inicio del Proceso de la Orden de trabajo # "
						+ ordenTrabajoIf.getCodigo());
		parametros.put(CAMPANA_ID_PARAM, ordenTrabajoIf.getCampanaId());
		parametros.put(ORDEN_TRABAJO_ID_PARAM, ordenTrabajoIf.getId());
		parametros.put(SUBJECT_PARAM, "Orden de trabajo Pendiente # "
				+ ordenTrabajoIf.getCodigo());
		// ID,CORREO,USUARIO,OBSERVACION;ID,CORREO,USUARIO,OBSERVACION ETC
		String listaValores = "";
		Collection<OrdenTrabajoDetalleIf> ordenTrabajoDetalleIfList = ordenTrabajoDetalleSessionLocal
				.findOrdenTrabajoDetalleByOrdenId(ordenTrabajoIf.getId());
		for (OrdenTrabajoDetalleIf ordenTrabajoDetalleIf : ordenTrabajoDetalleIfList) {
			listaValores += ordenTrabajoDetalleIf.getId() + SEPARADOR;
			EmpleadoIf e = empleadoSessionLocal
					.getEmpleado(ordenTrabajoDetalleIf.getAsignadoaId());
			listaValores += e.getEmailOficina() + SEPARADOR;
			UsuarioIf u = (UsuarioIf) usuarioSessionLocal
					.findUsuarioByEmpleadoId(
							ordenTrabajoDetalleIf.getAsignadoaId()).iterator()
					.next();
			listaValores += u.getUsuario() + SEPARADOR;
			//listaValores += ordenTrabajoDetalleIf.getDescripcion()+ SEPARADOR;
			listaValores += ordenTrabajoIf.getDescripcion()+ SEPARADOR;
			listaValores +=	"Orden de Trabajo Creada"+saltoLinea+
			"Codigo: "+ordenTrabajoIf.getCodigo()+saltoLinea+
			"Descripcion: "+ordenTrabajoIf.getDescripcion()+";";
		}
		parametros.put(LISTA_VALORES_PARAM, listaValores.substring(0,
				listaValores.length() - 1));
		getSpiritBPMConnector().iniciarProceso(cp,
				SpiritProcessDefinition.ORDEN_TRABAJO, parametros);
	}

	@Override
	public void procesarPresupuesto(PresupuestoIf presupuestoIf,ClienteOficinaIf clienteOficina, Tarea tarea)
			throws GenericBusinessException, BPMException, Exception {

		
		UsuarioIf usuario = usuarioSessionLocal.getUsuario(presupuestoIf
				.getUsuarioCreacionId());
		ClientParams cp = new ClientParams(usuario.getUsuario(), usuario.getClave());
		
		OrdenTrabajoDetalleIf otd = ordenTrabajoDetalleSessionLocal.getOrdenTrabajoDetalle(presupuestoIf.getOrdentrabajodetId());
		//OrdenTrabajoIf ot = ordenTrabajoLocal.getOrdenTrabajo(otd.getOrdenId());
		ClienteOficinaIf co = clienteOficinaLocal.getClienteOficina( clienteOficina.getId() );
		ClienteIf cliente = clienteLocal.getCliente(co.getClienteId());
		
		String cabeceraParaCorreoDePedido = "Presupuesto aprobado de cliente "+cliente.getNombreLegal();
		
		StringBuilder mensajeParaCorreoDePedido = new StringBuilder();
		mensajeParaCorreoDePedido.append("Presupuesto: ");
		mensajeParaCorreoDePedido.append(presupuestoIf.getCodigo());
		mensajeParaCorreoDePedido.append(NUEVA_LINEA);
		mensajeParaCorreoDePedido.append("Descripcion: ");
		mensajeParaCorreoDePedido.append(presupuestoIf.getConcepto());
		mensajeParaCorreoDePedido.append(NUEVA_LINEA);
		SubtipoOrdenIf sto = subTipoOrdenLocal.getSubtipoOrden( otd.getSubtipoId() );
		TipoOrdenIf to = tipoOrdenLocal.getTipoOrden(sto.getTipoordenId());
		mensajeParaCorreoDePedido.append("Departamento: ");
		mensajeParaCorreoDePedido.append(to.getNombre());
		
		
		//Object presupuestoIdObjeto = getSpiritBPMConnector().getValueFromInstance(cp, tarea, "presupuestoId");
		String estadoPresupuesto = presupuestoIf.getEstado();
		if (tarea != null ){
			procesoDeAprobacionPresupuesto(presupuestoIf, tarea, cp, cliente,
				cabeceraParaCorreoDePedido, mensajeParaCorreoDePedido, to,estadoPresupuesto);
		} else {
			InstanciaProceso ip = getSpiritBPMConnector().buscarPorVariable(cp, "presupuestoId", presupuestoIf.getId());
			if ( ip != null ) {
				/*InstanciaProceso ipPadre = getSpiritBPMConnector().buscarPadreDeInstancia(ip);
				if ( EstadoPresupuesto.CANCELADO.getLetra().equalsIgnoreCase(estadoPresupuesto) ) {
					getSpiritBPMConnector().cancelarInstanciaProceso(cp, ipPadre);
				}*/
				tarea = ip.getTareas().get(0);
				if ( tarea == null ){
					
					procesoDeAprobacionPresupuesto(presupuestoIf, tarea, cp, cliente,
						cabeceraParaCorreoDePedido, mensajeParaCorreoDePedido, to,
						estadoPresupuesto);
				}
			}
		}
	}

	private void procesoDeAprobacionPresupuesto(PresupuestoIf presupuestoIf,
			Tarea tarea, ClientParams cp, ClienteIf cliente,
			String cabeceraParaCorreoDePedido,
			StringBuilder mensajeParaCorreoDePedido, TipoOrdenIf to,
			String estadoPresupuesto) throws BPMException {
		getSpiritBPMConnector().setInstanceVariable(cp, tarea,"presupuestoId", presupuestoIf.getId());
		if (EstadoPresupuesto.APROBADO.getLetra().equalsIgnoreCase(estadoPresupuesto) ) { 
			establecerDatosDeCorreoParaPedido(presupuestoIf, tarea, cp,
					cabeceraParaCorreoDePedido, mensajeParaCorreoDePedido);
			
			InstanciaProceso instanciaProceso=getSpiritBPMConnector().getInstanciaProcesoByTarea(tarea);
			getSpiritBPMConnector().setValue(cp, tarea, "cancelar_presupuesto", false);
			getSpiritBPMConnector().terminarTarea(cp, tarea);
			
			/*OrdenTrabajoDetalleIf otd = ordenTrabajoDetalleSessionLocal.getOrdenTrabajoDetalle(
					presupuestoIf.getOrdentrabajodetId());
			OrdenTrabajoIf ot = ordentr*/
			//getSpiritBPMConnector().login(cp);
			
			
			instanciaProceso = getSpiritBPMConnector()
				.getInstanciaProcesoById(cp, instanciaProceso);
			
			
			Tarea tareaActiva = getSpiritBPMConnector().getTareaActivaByInstanciaProceso(instanciaProceso);
			
			getSpiritBPMConnector().setValue(cp, tareaActiva, DESCRIPCION_PARAM, 
				"Presupuesto"+saltoLinea+
				"Cliente: "+cliente.getNombreLegal()+saltoLinea+
				"Descripcion: "+presupuestoIf.getConcepto()+saltoLinea+
				"Codigo: "+presupuestoIf.getCodigo()+saltoLinea+
				"Departamento: "+to.getNombre()+saltoLinea
			);
			//getSpiritBPMConnector().logout();
		} else if ( EstadoPresupuesto.CANCELADO.getLetra().equalsIgnoreCase(estadoPresupuesto) ){
			getSpiritBPMConnector().setValue(cp, tarea, "cancelar_presupuesto", true);
			getSpiritBPMConnector().terminarTarea(cp, tarea);
		}
	}

	private void establecerDatosDeCorreoParaPedido(PresupuestoIf presupuestoIf,
			Tarea tarea, ClientParams cp, String cabeceraParaCorreoDePedido,
			StringBuilder mensajeParaCorreoDePedido) throws BPMException {
		getSpiritBPMConnector().setInstanceVariable(cp, tarea,"cabeceraParaCorreoDePedido", cabeceraParaCorreoDePedido);
		getSpiritBPMConnector().setInstanceVariable(cp, tarea,"mensajeParaCorreoDePedido", mensajeParaCorreoDePedido.toString());
	}

	@Override
	public void procesarPedido(PedidoIf pedidoIf, boolean generaFactura,
			Tarea tarea) throws GenericBusinessException, BPMException {

		UsuarioIf usuario = usuarioSessionLocal.getUsuario(pedidoIf.getUsuarioId());
		ClientParams cp = new ClientParams(usuario.getUsuario(), usuario.getClave());
		
		if ( tarea == null ){
			if ( "P".equalsIgnoreCase(pedidoIf.getTiporeferencia()) ){
				OficinaIf oficina = oficinaLocal.getOficina(pedidoIf.getOficinaId());
				Map<String,Object> mapaPresupuesto = new HashMap<String, Object>();
				mapaPresupuesto.put("codigo",pedidoIf.getReferencia());
				Collection<PresupuestoIf> presupuestos = findPresupuestoByQuery(mapaPresupuesto, oficina.getEmpresaId());
				if ( presupuestos.size() == 1 ){
					PresupuestoIf presupuesto = presupuestos.iterator().next();
					InstanciaProceso ip = getSpiritBPMConnector().buscarPorVariable(cp, "presupuestoId", presupuesto.getId());
					tarea = ip.getTareas().get(0);
				}
			}
		}
		
		if ( tarea != null ){
			if ( pedidoIf != null )
				getSpiritBPMConnector().setInstanceVariable(cp, tarea, "pedidoId", pedidoIf.getId());
	
			EnumTareaState estadoTarea = tarea.getEstado();
			if (  generaFactura && pedidoIf != null && tarea!= null &&
				EnumTareaState.TERMINADA != estadoTarea	){
				getSpiritBPMConnector().terminarTarea(cp, tarea);
			}
		} 
	}
	
	private java.util.Collection findPresupuestoByQuery(Map aMap, Long idEmpresa) {
		String objectName = "p";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct p from PresupuestoEJB " + objectName + ",OrdenTrabajoDetalleEJB otd, EmpleadoEJB e, EmpresaEJB em where ";
		if (aMap.size() > 0)
			queryString += where + " and ";
		queryString += "p.ordentrabajodetId = otd.id and otd.asignadoaId = e.id and e.empresaId = em.id and em.id = " + idEmpresa + " order by p.codigo desc";
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

	@Override
	public InstanciaProceso iniciarProceso(ClientParams clientParams,
			String descripcion) throws BPMException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(DESCRIPCION_PARAM, descripcion);
		InstanciaProceso instanciaProceso = getSpiritBPMConnector()
			.iniciarProceso(clientParams,SpiritProcessDefinition.ORDEN_TRABAJO, parametros);
		return instanciaProceso;
	}

}
