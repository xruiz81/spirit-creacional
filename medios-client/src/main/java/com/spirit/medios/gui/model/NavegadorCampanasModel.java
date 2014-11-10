package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.crm.entity.ClienteData;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.entity.TipoNegocioIf;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.general.gui.model.SubtipoOrdenModel;
import com.spirit.general.gui.model.TipoOrdenModel;
import com.spirit.medios.entity.CampanaData;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.entity.OrdenTrabajoData;
import com.spirit.medios.entity.OrdenTrabajoDetalleData;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PlanMedioData;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoData;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.medios.gui.panel.JPNavegadorCampanas;
import com.spirit.util.Utilitarios;
import com.spirit.util.tree.BrowserEvent;
import com.spirit.util.tree.BrowserListener;
import com.spirit.util.tree.TreeNode;

public class NavegadorCampanasModel extends JPNavegadorCampanas {
	
	private static final long serialVersionUID = 1668016122107930029L;
	private JDPopupFinderModel popupFinder;
	private EmpleadoCriteria empleadoCriteria;
	private CorporacionCriteria corporacionCriteria;
	private ClienteCriteria clienteCriteria;
	private CorporacionIf porCorporacionIf;
	private ClienteIf porClienteIf;
	private EmpleadoIf porResponsableOTIf;
	DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
	Map clientesMap = new HashMap();
	
	private static final String NOMBRE_ESTADO_ACTIVA = "ACTIVA";
	private static final String NOMBRE_ESTADO_INACTIVA = "INACTIVA";
	private static final String NOMBRE_ESTADO_FINALIZADA = "FINALIZADA";
	private static final String NOMBRE_ESTADO_APROBADO = "APROBADO";
	private static final String NOMBRE_ESTADO_COTIZADO= "COTIZADO";
	private static final String NOMBRE_ESTADO_SUSPENDIDA = "SUSPENDIDA";
	private static final String NOMBRE_ESTADO_CANCELADO = "CANCELADO";
	private static final String NOMBRE_ESTADO_REALIZADA = "REALIZADA";
	private static final String NOMBRE_ESTADO_EN_CURSO = "EN CURSO";
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";	
	private static final String NOMBRE_ESTADO_FACTURADO = "FACTURADO";
	public static final String ESTADO_FACTURADO = NOMBRE_ESTADO_FACTURADO.substring(0, 1);	
	private static final String ESTADO_ACTIVA = NOMBRE_ESTADO_ACTIVA.substring(0,1);
	private static final String ESTADO_INACTIVA = NOMBRE_ESTADO_INACTIVA.substring(0,1);
	private static final String ESTADO_FINALIZADA = NOMBRE_ESTADO_FINALIZADA.substring(0,1);
	private static final String ESTADO_SUSPENDIDA = NOMBRE_ESTADO_SUSPENDIDA.substring(0,1);
	private static final String ESTADO_CANCELADO = NOMBRE_ESTADO_CANCELADO.substring(0,1);
	private static final String ESTADO_REALIZADA = NOMBRE_ESTADO_REALIZADA.substring(0,1);
	private static final String ESTADO_EN_CURSO = NOMBRE_ESTADO_EN_CURSO.substring(0,1);
	private static final String ESTADO_PENDIENTE = NOMBRE_ESTADO_PENDIENTE.substring(0,1);
	private static final String ESTADO_APROBADO = NOMBRE_ESTADO_APROBADO.substring(0,1);
	private static final String ESTADO_COTIZADO = NOMBRE_ESTADO_COTIZADO.substring(2,3);
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
	private Date fechaInicio = new Date(new GregorianCalendar().getTimeInMillis());
	private Date fechaFin = new Date(new GregorianCalendar().getTimeInMillis());
	
	public NavegadorCampanasModel() {
		initKeyListeners();
		cargarMapas();
		showSaveMode();
		initListeners();
		new HotKeyComponent(this);
	}
	
	public void initKeyListeners(){
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
	}
	
	public void clean() {
		cleanPanelCliente();
		cleanPanelCampana();
		cleanPanelOrdenTrabajo();
		cleanPanelOrdenTrabajoDetalle();
		cleanPanelPresupuesto();		
		cleanPanelPlanMedios();
	}

	private void cleanPanelPlanMedios() {
		getTxtCodigoPlanMedios().setText("");
		getTxtConceptoPlanMedios().setText("");
		getTxtTipoProveedorPlanMedios().setText("");
		getTxtTipoOrdenPlanMedios().setText("");
		getTxtOrdenTrabajoDetallePlanMedios().setText("");
		getTxtOrdenTrabajoPlanMedios().setText("");
		getTxtOficinaPlanMedios().setText("");
		getTxtClientePlanMedios().setText("");
		getTxtCorporacionPlanMedios().setText("");
		getTxtFechaInicialPlanMedios().setText("");
		getTxtEstadoPlanMedios().setText("");
		getTxtValorPlanMedios().setText("");
	}

	private void cleanPanelPresupuesto() {
		getTxtCodigoPresupuesto().setText("");
		getTxtConceptoPresupuesto().setText("");
		getTxtTipoOrdenPresupuesto().setText("");
		getTxtOrdenTrabajoDetallePresupuesto().setText("");
		getTxtOrdenTrabajoPresupuesto().setText("");
		getTxtOficinaPresupuesto().setText("");
		getTxtClientePresupuesto().setText("");
		getTxtCorporacionPresupuesto().setText("");
		getTxtFechaInicialPresupuesto().setText("");
		getTxtFechaFinalPresupuesto().setText("");
		getTxtFechaValidezPresupuesto().setText("");
		getTxtEstadoPresupuesto().setText("");
		getTxtModificacionPresupuesto().setText("");
		getTxtCabeceraPresupuesto().setText("");
	}

	private void cleanPanelOrdenTrabajoDetalle() {
		getTxtSubTipoOrdenTrabajoDetalle().setText("");
		getTxtTipoOrdenTrabajoDetalle().setText("");
		getTxtEquipoOrdenTrabajoDetalle().setText("");
		getTxtAsignadoOrdenTrabajoDetalle().setText("");
		getTxtFechaLimiteOrdenTrabajoDetalle().setText("");
		getTxtFechaEntregaOrdenTrabajoDetalle().setText("");
		getTxtEstadoOrdenTrabajoDetalle().setText("");
		getTxtUrlDescripcionOrdenTrabajoDetalle().setText("");
		getTxtUrlPropuestaOrdenTrabajoDetalle().setText("");
		getTxtDescripcionOrdenTrabajoDetalle().setText("");
	}

	private void cleanPanelOrdenTrabajo() {
		getTxtCodigoOrdenTrabajo().setText("");
		getTxtDescripcionOrdenTrabajo().setText("");
		getTxtOficinaOrdenTrabajo().setText("");
		getTxtClienteOrdenTrabajo().setText("");
		getTxtCorporacionOrdenTrabajo().setText("");
		getTxtCampanaOrdenTrabajo().setText("");
		getTxtAsignadoAOrdenTrabajo().setText("");
		getTxtEstadoOrdenTrabajo().setText("");
		getTxtFechaLimiteOrdenTrabajo().setText("");
		getTxtFechaEntregaOrdenTrabajo().setText("");
		getTxtUrlPropuestaOrdenTrabajo().setText("");
		getTxtObservacionOrdenTrabajo().setText("");
	}

	private void cleanPanelCampana() {
		getTxtCodigoCampana().setText("");
		getTxtNombreCampana().setText("");
		getTxtClienteCampana().setText("");
		getTxtCorporacionCampana().setText("");
		getTxtFechaInicioCampana().setText("");
		getTxtEstadoCampana().setText("");
		getTxtPublicoObjetivoCampana().setText("");
		getTxtObservacionCampana().setText("");
	}

	private void cleanPanelCliente() {
		getTxtTipoIdentificacionCliente().setText("");
		getTxtIdentificacionCliente().setText("");
  		getTxtNombreLegalCliente().setText("");
  		getTxtRazonSocialCliente().setText("");
  		getTxtRepresentanteCliente().setText("");
  		getTxtCorporacionCliente().setText("");
		getTxtFechaCreacionCliente().setText("");
		getTxtEstadoCliente().setText("");
		getTxtTipoCliente().setText("");
		getTxtTipoNegocioCliente().setText("");
		getTxtObservacionesCliente().setText("");
		getTxtCtaContableCliente().setText("");
	}
	
	//Clase que controla el evento de seleccion del arbol
	BrowserListener oBrowserListener = new BrowserListener () {
      	public void Select(BrowserEvent e) {
      		//Vacio los textfieldsde todos los paneles
      		clean();
      		try {
          		//Extraigo el nodo seleccionado
          		TreeNode nodoSeleccionado = (TreeNode) e.getSource();
          		//Creo instancias de todos los tipos de nodos que hay en el arbol}
          		ClienteIf cliente = new ClienteData();
          		CampanaIf campana = new CampanaData();
          		OrdenTrabajoIf ordenTrabajo = new OrdenTrabajoData();
          		OrdenTrabajoDetalleIf ordenTrabajoDetalle = new OrdenTrabajoDetalleData();
          		PresupuestoIf presupuesto = new PresupuestoData();
          		PlanMedioIf planMedio = new PlanMedioData();
          		
          		//Veo si el nodo seleccionado es de Cliente
          		//if(ClienteData.class.equals(nodoSeleccionado.getObject().getClass())
          		//	|| ClienteEJB.class.equals(nodoSeleccionado.getObject().getClass())	){
          		if(nodoSeleccionado.getObject() instanceof ClienteIf ){
          			//Activo el tab de Campaña
          			getJideTabbedPane1().setSelectedIndex(0);
          			
          			//Creo la instancia del nodo cliente seleccionado
          			cliente = (ClienteIf) nodoSeleccionado.getObject();
          			
          			//LLeno los campos de la campana seleccionada en el panel
          			TipoIdentificacionIf tipoIdentificacionTemp = SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacion(cliente.getTipoidentificacionId());
          			getTxtTipoIdentificacionCliente().setText(tipoIdentificacionTemp.getNombre());
          			
          			getTxtIdentificacionCliente().setText(cliente.getIdentificacion());
          			getTxtNombreLegalCliente().setText(cliente.getNombreLegal());
          			getTxtRazonSocialCliente().setText(cliente.getRazonSocial());
          			getTxtRepresentanteCliente().setText(cliente.getRepresentante());
          			
          			CorporacionIf corporacionTemp = SessionServiceLocator.getCorporacionSessionService().getCorporacion(cliente.getCorporacionId());
    				getTxtCorporacionCliente().setText(corporacionTemp.getNombre());
    				
    				//String fechaCreacionCliente = dateFormatter.format(cliente.getFechaCreacion());
    				getTxtFechaCreacionCliente().setText(
    						Utilitarios.getFechaUppercase(cliente.getFechaCreacion())
    				);
    				
    				if (ESTADO_ACTIVA.equals(cliente.getEstado().toString()))
    					getTxtEstadoCliente().setText(NOMBRE_ESTADO_ACTIVA);
    				else if (ESTADO_INACTIVA.equals(cliente.getEstado().toString()))
    					getTxtEstadoCliente().setText(NOMBRE_ESTADO_INACTIVA);
    				
          			TipoClienteIf tipoClienteTemp = SessionServiceLocator.getTipoClienteSessionService().getTipoCliente(cliente.getTipoclienteId());
          			getTxtTipoCliente().setText(tipoClienteTemp.getNombre());

          			TipoNegocioIf tipoNegocioTemp = SessionServiceLocator.getTipoNegocioSessionService().getTipoNegocio(cliente.getTiponegocioId());
          			getTxtTipoNegocioCliente().setText(tipoNegocioTemp.getNombre());
          			
          			getTxtObservacionesCliente().setText(cliente.getObservacion());
          			
          			if(cliente.getCuentaId()!=null){
          				CuentaIf cuentaTemp = SessionServiceLocator.getCuentaSessionService().getCuenta(cliente.getCuentaId());
          				getTxtCtaContableCliente().setText(cuentaTemp.getCodigo() + " - " + cuentaTemp.getNombre());
          			}        			
          		}
          		
          		//Veo si el nodo seleccionado es de Campaña
          		//if(campana.getClass().equals(nodoSeleccionado.getObject().getClass())){
          		if(nodoSeleccionado.getObject() instanceof CampanaIf){
          			//Activo el tab de Campaña
          			getJideTabbedPane1().setSelectedIndex(1);
          			
          			//Creo la instancia del nodo campana seleccionado
          			campana = (CampanaIf) nodoSeleccionado.getObject();
          			
          			//LLeno los campos de la campana selecciionada en el panel
          			getTxtCodigoCampana().setText(campana.getCodigo());
          			getTxtNombreCampana().setText(campana.getNombre());
          			
          			ClienteIf clienteTemp = SessionServiceLocator.getClienteSessionService().getCliente(campana.getClienteId());
    				CorporacionIf corporacionTemp = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteTemp.getCorporacionId());
    				
    				getTxtClienteCampana().setText(clienteTemp.getNombreLegal());
    				getTxtCorporacionCampana().setText(corporacionTemp.getNombre());
    				
    				//String fechaInicioCampana = dateFormatter.format(campana.getFechaini());
    				getTxtFechaInicioCampana().setText(
    						Utilitarios.getFechaUppercase(campana.getFechaini())
    				);
    				
    				if (ESTADO_ACTIVA.equals(campana.getEstado().toString()))
    					getTxtEstadoCampana().setText(NOMBRE_ESTADO_ACTIVA);
    				else if (ESTADO_INACTIVA.equals(campana.getEstado().toString()))
    					getTxtEstadoCampana().setText(NOMBRE_ESTADO_INACTIVA);
    				else if (ESTADO_FINALIZADA.equals(campana.getEstado().toString()))
    					getTxtEstadoCampana().setText(NOMBRE_ESTADO_FINALIZADA);

    				getTxtPublicoObjetivoCampana().setText(campana.getPublicoObjetivo());
          			getTxtObservacionCampana().setText(campana.getObservacion());          			
          		}
          		
          		//Veo si el nodo seleccionado es de orden Trabajo
          		//if(ordenTrabajo.getClass().equals(nodoSeleccionado.getObject().getClass())){
          		if(nodoSeleccionado.getObject() instanceof OrdenTrabajoIf){
          			//Activo el tab de orden Trabajo
          			getJideTabbedPane1().setSelectedIndex(2);
          			
          			//Creo la instancia del nodo ordenTrabajo seleccionado
          			ordenTrabajo = (OrdenTrabajoIf) nodoSeleccionado.getObject();
          			
          			//LLeno los campos de la orden trabajo selecciionada en el panel
          			getTxtCodigoOrdenTrabajo().setText(ordenTrabajo.getCodigo());
          			
          			//String fechaCreacionOrdenTrabajo = dateFormatter.format(ordenTrabajo.getFecha());
    				getTxtFechaCreacionOrdenTrabajo().setText(
    						Utilitarios.getFechaUppercase(ordenTrabajo.getFecha())
    				);

    				getTxtDescripcionOrdenTrabajo().setText(ordenTrabajo.getDescripcion());
    				
    				ClienteOficinaIf clienteOficinaTemp = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenTrabajo.getClienteoficinaId());
          			ClienteIf clienteTemp = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaTemp.getClienteId());
    				CorporacionIf corporacionTemp = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteTemp.getCorporacionId());
    				CampanaIf campanaTemp = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajo.getCampanaId());
    				EmpleadoIf empleadoTemp = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenTrabajo.getEmpleadoId());
    				
    				getTxtOficinaOrdenTrabajo().setText(clienteOficinaTemp.getDescripcion());
    				getTxtClienteOrdenTrabajo().setText(clienteTemp.getNombreLegal());
    				getTxtCorporacionOrdenTrabajo().setText(corporacionTemp.getNombre());
    				getTxtCampanaOrdenTrabajo().setText(campanaTemp.getCodigo() + " - " + campanaTemp.getNombre());
    				getTxtAsignadoAOrdenTrabajo().setText(empleadoTemp.getNombres() + " " + empleadoTemp.getApellidos());
    				
    				if (ESTADO_PENDIENTE.equals(ordenTrabajo.getEstado().toString()))
    					getTxtEstadoOrdenTrabajo().setText(NOMBRE_ESTADO_PENDIENTE);
    				if (ESTADO_EN_CURSO.equals(ordenTrabajo.getEstado().toString()))
    					getTxtEstadoOrdenTrabajo().setText(NOMBRE_ESTADO_EN_CURSO);
    				if (ESTADO_REALIZADA.equals(ordenTrabajo.getEstado().toString()))
    					getTxtEstadoOrdenTrabajo().setText(NOMBRE_ESTADO_REALIZADA);
    				if (ESTADO_CANCELADO.equals(ordenTrabajo.getEstado().toString()))
    					getTxtEstadoOrdenTrabajo().setText(NOMBRE_ESTADO_CANCELADO);
    				if (ESTADO_SUSPENDIDA.equals(ordenTrabajo.getEstado().toString()))
    					getTxtEstadoOrdenTrabajo().setText(NOMBRE_ESTADO_SUSPENDIDA);
    				
    				//String fechaLimiteOrdenTrabajo = dateFormatter.format(ordenTrabajo.getFechalimite());
    				getTxtFechaLimiteOrdenTrabajo().setText(
    						Utilitarios.getFechaUppercase(ordenTrabajo.getFechalimite())
    				);
    				
    				String fechaEntregaOrdenTrabajo = "";
    				if(ordenTrabajo.getFechaentrega() != null){ 
    					//fechaEntregaOrdenTrabajo = dateFormatter.format(ordenTrabajo.getFechaentrega());
    					fechaEntregaOrdenTrabajo = Utilitarios.getFechaUppercase(ordenTrabajo.getFechalimite());
    				}
    				if(fechaEntregaOrdenTrabajo != null) 
    					getTxtFechaEntregaOrdenTrabajo().setText(fechaEntregaOrdenTrabajo);
    				
    				getTxtUrlPropuestaOrdenTrabajo().setText(ordenTrabajo.getUrlPropuesta());
    				getTxtObservacionOrdenTrabajo().setText(ordenTrabajo.getObservacion());
          		}
          		
          		//Veo si el nodo seleccionado es de orden Trabajo Detalle
          		//if(ordenTrabajoDetalle.getClass().equals(nodoSeleccionado.getObject().getClass())){
          		if(nodoSeleccionado.getObject() instanceof OrdenTrabajoDetalleIf){
          			//Activo el tab de orden Trabajo Detalle
          			getJideTabbedPane1().setSelectedIndex(3);
          			
          			//Creo la instancia del nodo ordenTrabajoDetalle seleccionado
          			ordenTrabajoDetalle = (OrdenTrabajoDetalleIf) nodoSeleccionado.getObject();
          			
          			//LLeno los campos de la orden trabajo detalle selecciionada en el panel
          			SubtipoOrdenIf subTipoOrdenTemp = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(ordenTrabajoDetalle.getSubtipoId());
          			TipoOrdenIf tipoOrdenTemp = SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(subTipoOrdenTemp.getTipoordenId());
          			EquipoTrabajoIf equipoTrabajoTemp = SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajo(ordenTrabajoDetalle.getEquipoId());
          			EmpleadoIf empleadoTemp = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenTrabajoDetalle.getAsignadoaId());
          			
          			getTxtSubTipoOrdenTrabajoDetalle().setText(subTipoOrdenTemp.getCodigo() + " - " + subTipoOrdenTemp.getNombre());
          			getTxtTipoOrdenTrabajoDetalle().setText(tipoOrdenTemp.getCodigo() + " - " + tipoOrdenTemp.getNombre());
          			getTxtEquipoOrdenTrabajoDetalle().setText(equipoTrabajoTemp.getCodigo());
          			getTxtAsignadoOrdenTrabajoDetalle().setText(empleadoTemp.getNombres() + empleadoTemp.getApellidos());
          			
          			//String fechaLimiteOrdenTrabajoDetalle = dateFormatter.format(ordenTrabajoDetalle.getFechalimite());
    				getTxtFechaLimiteOrdenTrabajoDetalle().setText(
    						Utilitarios.getFechaUppercase(ordenTrabajoDetalle.getFechalimite())
    				);
    				
    				String fechaEntregaOrdenTrabajoDetalle = "";
    				if (ordenTrabajoDetalle.getFechaentrega() != null){
    					//fechaEntregaOrdenTrabajoDetalle = dateFormatter.format(ordenTrabajoDetalle.getFechaentrega());
    					fechaEntregaOrdenTrabajoDetalle = Utilitarios.getFechaUppercase(ordenTrabajoDetalle.getFechaentrega());
    				}
    				if (fechaEntregaOrdenTrabajoDetalle != null) getTxtFechaEntregaOrdenTrabajoDetalle().setText(fechaEntregaOrdenTrabajoDetalle);
    				
    				if (ESTADO_PENDIENTE.equals(ordenTrabajoDetalle.getEstado().toString()))
    					getTxtEstadoOrdenTrabajoDetalle().setText(NOMBRE_ESTADO_PENDIENTE);
    				if (ESTADO_EN_CURSO.equals(ordenTrabajoDetalle.getEstado().toString()))
    					getTxtEstadoOrdenTrabajoDetalle().setText(NOMBRE_ESTADO_EN_CURSO);
    				if (ESTADO_REALIZADA.equals(ordenTrabajoDetalle.getEstado().toString()))
    					getTxtEstadoOrdenTrabajoDetalle().setText(NOMBRE_ESTADO_REALIZADA);
    				if (ESTADO_CANCELADO.equals(ordenTrabajoDetalle.getEstado().toString()))
    					getTxtEstadoOrdenTrabajoDetalle().setText(NOMBRE_ESTADO_CANCELADO);
    				if (ESTADO_SUSPENDIDA.equals(ordenTrabajoDetalle.getEstado().toString()))
    					getTxtEstadoOrdenTrabajoDetalle().setText(NOMBRE_ESTADO_SUSPENDIDA);
          			
    				getTxtUrlDescripcionOrdenTrabajoDetalle().setText(ordenTrabajoDetalle.getUrlDescripcion());
    				getTxtUrlPropuestaOrdenTrabajoDetalle().setText(ordenTrabajoDetalle.getUrlPropuesta());
    				getTxtDescripcionOrdenTrabajoDetalle().setText(ordenTrabajoDetalle.getDescripcion());
          		}
          		
          		//Veo si el nodo seleccionado es de Presupuesto
          		//if(presupuesto.getClass().equals(nodoSeleccionado.getObject().getClass())){
          		if(nodoSeleccionado.getObject() instanceof PresupuestoIf){
          			//Activo el tab de presupuesto
          			getJideTabbedPane1().setSelectedIndex(4);
          			
          			//Creo la instancia del nodo presupuesto seleccionado
          			presupuesto = (PresupuestoIf) nodoSeleccionado.getObject();
          			
          			//LLeno los campos del presupuesto seleccionado en el panel
          			getTxtCodigoPresupuesto().setText(presupuesto.getCodigo());
          			getTxtConceptoPresupuesto().setText(presupuesto.getConcepto());
          			
          			
          			OrdenTrabajoDetalleIf ordenTrabajoDetalleTemp = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuesto.getOrdentrabajodetId());
          			SubtipoOrdenIf subTipoOrdenTemp = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(ordenTrabajoDetalleTemp.getSubtipoId());
          			TipoOrdenIf tipoOrdenTemp = SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(subTipoOrdenTemp.getTipoordenId());
          			OrdenTrabajoIf ordenTrabajoTemp = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalleTemp.getOrdenId());          			
          			ClienteOficinaIf clienteOficinaTemp = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenTrabajoTemp.getClienteoficinaId());
          			ClienteIf clienteTemp = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaTemp.getClienteId());
    				CorporacionIf corporacionTemp = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteTemp.getCorporacionId());
    				//ClienteOficinaIf proveedorTemp = getClienteOficinaSessionService().getClienteOficina(presupuesto.getProveedorId());
    				/*********************************
    				 * OJO CON ESTO
    				 */
    				getTxtTipoOrdenPresupuesto().setText(tipoOrdenTemp.getCodigo() + " - " + tipoOrdenTemp.getNombre());
    				getTxtOrdenTrabajoDetallePresupuesto().setText(subTipoOrdenTemp.getCodigo() + " - " + subTipoOrdenTemp.getNombre());
    				getTxtOrdenTrabajoPresupuesto().setText(ordenTrabajoTemp.getDescripcion());
    				getTxtOficinaPresupuesto().setText(clienteOficinaTemp.getDescripcion());
    				getTxtClientePresupuesto().setText(clienteTemp.getNombreLegal());
    				getTxtCorporacionPresupuesto().setText(corporacionTemp.getNombre());
    				
    				//String fechaInicialPresupuesto = dateFormatter.format(presupuesto.getFecha());
    				getTxtFechaInicialPresupuesto().setText(
    						Utilitarios.getFechaUppercase(presupuesto.getFecha())
    				);
    				
    				//String fechaFinalPresupuesto = dateFormatter.format(presupuesto.getFechaValidez());
    				getTxtFechaFinalPresupuesto().setText(
    						Utilitarios.getFechaUppercase(presupuesto.getFechaValidez())
    				);
    				
    				//String fechaValidezPresupuesto = dateFormatter.format(presupuesto.getFechaValidez());
    				getTxtFechaValidezPresupuesto().setText(
    						Utilitarios.getFechaUppercase(presupuesto.getFechaValidez())
    				);
          			
          			if (ESTADO_PENDIENTE.equals(presupuesto.getEstado().toString()))
    					getTxtEstadoPresupuesto().setText(NOMBRE_ESTADO_PENDIENTE);
          			if (ESTADO_FACTURADO.equals(presupuesto.getEstado().toString()))
    					getTxtEstadoPresupuesto().setText(NOMBRE_ESTADO_FACTURADO);
    				if (ESTADO_COTIZADO.equals(presupuesto.getEstado().toString()))
    					getTxtEstadoPresupuesto().setText(NOMBRE_ESTADO_COTIZADO);
    				if (ESTADO_CANCELADO.equals(presupuesto.getEstado().toString()))
    					getTxtEstadoPresupuesto().setText(NOMBRE_ESTADO_CANCELADO);
    				if (ESTADO_APROBADO.equals(presupuesto.getEstado().toString()))
    					getTxtEstadoPresupuesto().setText(NOMBRE_ESTADO_APROBADO);
          			
    				getTxtModificacionPresupuesto().setText(presupuesto.getModificacion().toString());
    				getTxtCabeceraPresupuesto().setText(presupuesto.getCabecera());
          		}
          		
        		//Veo si el nodo seleccionado es de Plan Medio
          		//if(planMedio.getClass().equals(nodoSeleccionado.getObject().getClass())){
          		if(nodoSeleccionado.getObject() instanceof PlanMedioIf){
          			//Activo el tab de presupuesto
          			getJideTabbedPane1().setSelectedIndex(5);
          			
          			//Creo la instancia del nodo presupuesto seleccionado
          			planMedio = (PlanMedioIf) nodoSeleccionado.getObject();
          			
          			//LLeno los campos del planMedio seleccionado en el panel
          			getTxtCodigoPlanMedios().setText(planMedio.getCodigo());
          			getTxtConceptoPlanMedios().setText(planMedio.getConcepto());
          			
          			
          			TipoProveedorIf tipoProveedorTemp = SessionServiceLocator.getTipoProveedorSessionService().getTipoProveedor(planMedio.getTipoProveedorId());
          			OrdenTrabajoDetalleIf ordenTrabajoDetalleTemp = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(planMedio.getOrdenTrabajoDetalleId());
          			SubtipoOrdenIf subTipoOrdenTemp = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(ordenTrabajoDetalleTemp.getSubtipoId());
          			TipoOrdenIf tipoOrdenTemp = SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(subTipoOrdenTemp.getTipoordenId());
          			OrdenTrabajoIf ordenTrabajoTemp = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalleTemp.getOrdenId());          			
          			ClienteOficinaIf clienteOficinaTemp = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenTrabajoTemp.getClienteoficinaId());
          			ClienteIf clienteTemp = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaTemp.getClienteId());
    				CorporacionIf corporacionTemp = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteTemp.getCorporacionId());
    				
    				getTxtTipoProveedorPlanMedios().setText(tipoProveedorTemp.getCodigo() + " - " + tipoProveedorTemp.getNombre());
    				getTxtTipoOrdenPlanMedios().setText(tipoOrdenTemp.getCodigo() + " - " + tipoOrdenTemp.getNombre());
    				getTxtOrdenTrabajoDetallePlanMedios().setText(subTipoOrdenTemp.getCodigo() + " - " + subTipoOrdenTemp.getNombre());
    				getTxtOrdenTrabajoPlanMedios().setText(ordenTrabajoTemp.getDescripcion());
    				getTxtOficinaPlanMedios().setText(clienteOficinaTemp.getDescripcion());
    				getTxtClientePlanMedios().setText(clienteTemp.getNombreLegal());
    				getTxtCorporacionPlanMedios().setText(corporacionTemp.getNombre());
    				
    				//String fechaInicialPlanMedio = dateFormatter.format(planMedio.getFechaini());
    				getTxtFechaInicialPlanMedios().setText(
    						Utilitarios.getFechaUppercase(planMedio.getFechaInicio())
    				);
    				
    				//String fechaFinalPlanMedio = dateFormatter.format(planMedio.getFechafin());
    				getTxtFechaFinalPlanMedios().setText(
    						Utilitarios.getFechaUppercase(planMedio.getFechaFin())
    				);
          			
          			if (ESTADO_PENDIENTE.equals(planMedio.getEstado().toString()))
    					getTxtEstadoPlanMedios().setText(NOMBRE_ESTADO_PENDIENTE);
    				if (ESTADO_COTIZADO.equals(planMedio.getEstado().toString()))
    					getTxtEstadoPlanMedios().setText(NOMBRE_ESTADO_COTIZADO);
    				if (ESTADO_CANCELADO.equals(planMedio.getEstado().toString()))
    					getTxtEstadoPlanMedios().setText(NOMBRE_ESTADO_CANCELADO);
    				if (ESTADO_APROBADO.equals(planMedio.getEstado().toString()))
    					getTxtEstadoPlanMedios().setText(NOMBRE_ESTADO_APROBADO);
    				if (ESTADO_FACTURADO.equals(planMedio.getEstado().toString()))
    					getTxtEstadoPlanMedios().setText(NOMBRE_ESTADO_FACTURADO);
    				if (ESTADO_SUSPENDIDA.equals(planMedio.getEstado().toString()))
    					getTxtEstadoPlanMedios().setText(NOMBRE_ESTADO_SUSPENDIDA);
          			
    				getTxtValorPlanMedios().setText(planMedio.getValorSubtotal().toString());
          		}
			} catch (GenericBusinessException e1) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e1.printStackTrace();
			}
		}
		public void Deselect(BrowserEvent e) {}
		public void Activate(BrowserEvent e) {}
		public void Deactivate(BrowserEvent e) {}
    };

	public void insertarNodosCampanaByCliente(){
		try {
			Collection campanaByClienteCollection = SessionServiceLocator.getCampanaSessionService().findCampanaByClienteIdAndByFechaInicioAndFechaFin(porClienteIf.getId(), fechaInicio, fechaFin);
			Iterator itCampanaByClienteCollection = campanaByClienteCollection.iterator();
			Object nodoCliente = null;
			
			//Sino hay registros al hacer la busqueda muestro un mensaje
			if(!itCampanaByClienteCollection.hasNext()){
				clean();
				SpiritAlert.createAlert("No se encontraron registros de Campañas para esta búsqueda", SpiritAlert.WARNING);
			}
			else{
				//Extraiogo el texto a  mostrar en el Nodo
				String labelNodoCliente = porClienteIf.getNombreLegal(); 
				//Creo el nodo cliente
				nodoCliente = getTreeCampana().insertNode(getRootNodeTreeCampana(),labelNodoCliente,"images/icons/entidad/Cliente.png","images/icons/entidad/Cliente.png",2,porClienteIf);
			}
			//Recorro todas las campañs encontradas de la base
			while(itCampanaByClienteCollection.hasNext()){
				CampanaIf campanaIf = (CampanaIf) itCampanaByClienteCollection.next();
				
				//Extraiogo el texto a  mostrar en el Nodo
				String labelNodoCampana = campanaIf.getCodigo() + " - " + campanaIf.getNombre() + " (" + campanaIf.getEstado() + ")"; 
				
				//Creo el nodo campana 
				Object nodoCampana = getTreeCampana().insertNode(nodoCliente,labelNodoCampana,"images/icons/entidad/Campana.png","images/icons/entidad/Campana.png",2,campanaIf);
				//Añado los nodos hijos de este nodo
				insertarNodosOrdenTrabajo(nodoCampana,campanaIf.getId());
			}		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void insertarNodosCampanaByClienteAndByEstado(){
		try {
			//Extraigo el estado por el cual voy a buscar
			String estadoBuscar = getCmbPorEstado().getSelectedItem().toString().substring(0, 1);
			Map parameterMap = new HashMap();
			parameterMap.put("clienteId", porClienteIf.getId());
			parameterMap.put("estado", estadoBuscar);
			Collection campanaByClienteAndByEstadoCollection = SessionServiceLocator.getCampanaSessionService().findCampanaByQueryAndByFechaInicioAndFechaFin(parameterMap, fechaInicio, fechaFin);
			Iterator itCampanaByClienteAndByEstadoCollection = campanaByClienteAndByEstadoCollection.iterator();
			Object nodoCliente = null;
			
			//Sino hay registros al hacer la busqueda muestro un mensaje
			if(!itCampanaByClienteAndByEstadoCollection.hasNext()){
				clean();
				SpiritAlert.createAlert("No se encontraron registros de acuerdo al criterio de búsqueda !", SpiritAlert.WARNING);
			}
			else{
				//Extraiogo el texto a  mostrar en el Nodo
				String labelNodoCliente = porClienteIf.getNombreLegal(); 
				//Creo el nodo cliente
				nodoCliente = getTreeCampana().insertNode(getRootNodeTreeCampana(),labelNodoCliente,"images/icons/entidad/Cliente.png","images/icons/entidad/Cliente.png",2,porClienteIf);
			}
			//Recorro todas las campañs encontradas de la base
			while(itCampanaByClienteAndByEstadoCollection.hasNext()){
				CampanaIf campanaIf = (CampanaIf) itCampanaByClienteAndByEstadoCollection.next();
				
				//Extraiogo el texto a  mostrar en el Nodo
				String labelNodoCampana = campanaIf.getCodigo() + " - " + campanaIf.getNombre() + " (" + campanaIf.getEstado() + ")"; 
				
				//Creo el nodo campana 
				Object nodoCampana = getTreeCampana().insertNode(nodoCliente,labelNodoCampana,"images/icons/entidad/Campana.png","images/icons/entidad/Campana.png",2,campanaIf);
				//Añado los nodos hijos de este nodo
				insertarNodosOrdenTrabajo(nodoCampana,campanaIf.getId());
			}		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void insertarNodosCampanaByClienteAndByResponsableOrdenTrabajo(){
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("clienteId", porClienteIf.getId());
			Collection campanaByClienteAndByResponsableOrdenTrabajoCollection = SessionServiceLocator.getCampanaSessionService().findCampanaByQueryAndByResponsableOrdenTrabajoAndByFechaInicioAndFechaFin(parameterMap, porResponsableOTIf.getId(), fechaInicio, fechaFin);
			Iterator itCampanaByClienteAndByResponsableOrdenTrabajoCollection = campanaByClienteAndByResponsableOrdenTrabajoCollection.iterator();
			Object nodoCliente = null;
			
			//Sino hay registros al hacer la busqueda muestro un mensaje
			if(!itCampanaByClienteAndByResponsableOrdenTrabajoCollection.hasNext()){
				clean();
				SpiritAlert.createAlert("No se encontraron registros de acuerdo al criterio de búsqueda !", SpiritAlert.WARNING);
			}else{
				//Extraiogo el texto a  mostrar en el Nodo
				String labelNodoCliente = porClienteIf.getNombreLegal(); 
				//Creo el nodo cliente
				nodoCliente = getTreeCampana().insertNode(getRootNodeTreeCampana(),labelNodoCliente,"images/icons/entidad/Cliente.png","images/icons/entidad/Cliente.png",2,porClienteIf);
			}
			//Recorro todas las campañs encontradas de la base
			while(itCampanaByClienteAndByResponsableOrdenTrabajoCollection.hasNext()){
				CampanaIf campanaIf = (CampanaIf) itCampanaByClienteAndByResponsableOrdenTrabajoCollection.next();
				
				//Extraiogo el texto a  mostrar en el Nodo
				String labelNodoCampana = campanaIf.getCodigo() + " - " + campanaIf.getNombre() + " (" + campanaIf.getEstado() + ")"; 
				
				//Creo el nodo campana 
				Object nodoCampana = getTreeCampana().insertNode(nodoCliente,labelNodoCampana,"images/icons/entidad/Campana.png","images/icons/entidad/Campana.png",2,campanaIf);
				//Añado los nodos hijos de este nodo
				insertarNodosOrdenTrabajo(nodoCampana,campanaIf.getId());
			}		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void insertarNodosCampanaByClienteAndByEstadoAndByResponsableOrdenTrabajo(){
		try {
			//Extraigo el estado por el cual voy a buscar
			String estadoBuscar = getCmbPorEstado().getSelectedItem().toString().substring(0, 1);
			Map parameterMap = new HashMap();
			parameterMap.put("clienteId", porClienteIf.getId());
			parameterMap.put("estado", estadoBuscar);
			Collection campanaByClienteAndByEstadoAndByResponsableOrdenTrabajoCollection = SessionServiceLocator.getCampanaSessionService().findCampanaByQueryAndByResponsableOrdenTrabajoAndByFechaInicioAndFechaFin(parameterMap, porResponsableOTIf.getId(), fechaInicio, fechaFin);
			Iterator itCampanaByClienteAndByEstadoAndByResponsableOrdenTrabajoCollection = campanaByClienteAndByEstadoAndByResponsableOrdenTrabajoCollection.iterator();
			Object nodoCliente = null;
			
			//Sino hay registros al hacer la busqueda muestro un mensaje
			if(!itCampanaByClienteAndByEstadoAndByResponsableOrdenTrabajoCollection.hasNext()){
				clean();
				SpiritAlert.createAlert("No se encontraron registros de acuerdo al criterio de búsqueda !", SpiritAlert.WARNING);
			}else{
				//Extraiogo el texto a  mostrar en el Nodo
				String labelNodoCliente = porClienteIf.getNombreLegal(); 
				//Creo el nodo cliente
				nodoCliente = getTreeCampana().insertNode(getRootNodeTreeCampana(),labelNodoCliente,"images/icons/entidad/Cliente.png","images/icons/entidad/Cliente.png",2,porClienteIf);
			}
			//Recorro todas las campañs encontradas de la base
			while(itCampanaByClienteAndByEstadoAndByResponsableOrdenTrabajoCollection.hasNext()){
				CampanaIf campanaIf = (CampanaIf) itCampanaByClienteAndByEstadoAndByResponsableOrdenTrabajoCollection.next();
				
				//Extraiogo el texto a  mostrar en el Nodo
				String labelNodoCampana = campanaIf.getCodigo() + " - " + campanaIf.getNombre() + " (" + campanaIf.getEstado() + ")"; 
				
				//Creo el nodo campana 
				Object nodoCampana = getTreeCampana().insertNode(nodoCliente,labelNodoCampana,"images/icons/entidad/Campana.png","images/icons/entidad/Campana.png",2,campanaIf);
				//Añado los nodos hijos de este nodo
				insertarNodosOrdenTrabajo(nodoCampana,campanaIf.getId());
			}		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void cargarMapas(){
		try {
			mapaCliente.clear();
			Collection clientes = SessionServiceLocator.getClienteSessionService().getClienteList();
			Iterator clientesIt = clientes.iterator();
			while(clientesIt.hasNext()){
				ClienteIf cliente = (ClienteIf)clientesIt.next();
				mapaCliente.put(cliente.getId(), cliente);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	Comparator<CampanaIf> ordenadorCampanaAlfabetico = new Comparator<CampanaIf>(){
		public int compare(CampanaIf o1, CampanaIf o2) {
			ClienteIf clienteO1 = mapaCliente.get(o1.getClienteId());
			ClienteIf clienteO2 = mapaCliente.get(o2.getClienteId());
			return clienteO1.getNombreLegal().compareTo(clienteO2.getNombreLegal());
		}		
	};			
			
	public void insertarNodosCampana(){
		try {
			Collection clienteCollection = SessionServiceLocator.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator itClienteCollection = clienteCollection.iterator();
			
			//Recorro todas los clientes encontrados en la base
			while(itClienteCollection.hasNext()){
				ClienteIf clienteIf = (ClienteIf) itClienteCollection.next();
				
				Collection campanaByClienteCollection = SessionServiceLocator.getCampanaSessionService().findCampanaByClienteIdAndByFechaInicioAndFechaFin(clienteIf.getId(), fechaInicio, fechaFin);
				Iterator itCampanaByClienteCollection = campanaByClienteCollection.iterator();
				Object nodoCliente = null;
				
				//Sino hay registros al hacer la busqueda muestro un mensaje
				if(itCampanaByClienteCollection.hasNext()){
					//Extraiogo el texto a  mostrar en el Nodo
					String labelNodoCliente = clienteIf.getNombreLegal(); 
					//Creo el nodo cliente
					nodoCliente = getTreeCampana().insertNode(getRootNodeTreeCampana(),labelNodoCliente,"images/icons/entidad/Cliente.png","images/icons/entidad/Cliente.png",2,clienteIf);
				}
				//Recorro todas las campañs encontradas de la base
				while(itCampanaByClienteCollection.hasNext()){
					CampanaIf campanaIf = (CampanaIf) itCampanaByClienteCollection.next();
					
					//Extraiogo el texto a  mostrar en el Nodo
					String labelNodoCampana = campanaIf.getCodigo() + " - " + campanaIf.getNombre() + " (" + campanaIf.getEstado() + ")"; 
					
					//Creo el nodo campana 
					Object nodoCampana = getTreeCampana().insertNode(nodoCliente,labelNodoCampana,"images/icons/entidad/Campana.png","images/icons/entidad/Campana.png",2,campanaIf);
					//Añado los nodos hijos de este nodo
					insertarNodosOrdenTrabajo(nodoCampana,campanaIf.getId());
				}				
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
    
	public void insertarNodosCampanaByEstado(){
		try {
			//Extraigo el estado por el cual voy a buscar
			String estadoBuscar = getCmbPorEstado().getSelectedItem().toString().substring(0, 1);
			Collection campanaByEstadoCollection = SessionServiceLocator.getCampanaSessionService().findCampanaByEstadoAndByFechaInicioAndFechaFin(estadoBuscar, fechaInicio, fechaFin);
			Iterator itCampanaByEstadoCollection = campanaByEstadoCollection.iterator();
			
			//Sino hay registros al hacer la busqueda muestro un mensaje
			if(!itCampanaByEstadoCollection.hasNext()){
				clean();
				SpiritAlert.createAlert("No se encontraron registros de acuerdo al criterio de búsqueda !", SpiritAlert.WARNING);
			}
			//Recorro todas las campañs encontradas de la base
			while(itCampanaByEstadoCollection.hasNext()){
				CampanaIf campanaIf = (CampanaIf) itCampanaByEstadoCollection.next();
				
				Object nodoCliente = null;
				
				//Si el "nodo cliente"  ya existe en el arbol ingreso el nodo campaña en el
				if(clientesMap.get(campanaIf.getClienteId())!=null){
					//Obtengo el nodo campana que se encuentra guardado en el mapa 
					nodoCliente = (Object) clientesMap.get(campanaIf.getClienteId());
					
				}
				//Sino existe el nodo cliente lo creo para luego insertar el nodo campana
				else {
					//Creo la instancia del nodo cliente seleccionado
          			ClienteIf clienteIf = (ClienteIf) SessionServiceLocator.getClienteSessionService().getCliente(campanaIf.getClienteId());
					
          			if(clienteIf != null){
          				//Extraiogo el texto a  mostrar en el Nodo
              			String labelNodoCliente = "";
    					if(clienteIf.getNombreLegal() != null) labelNodoCliente = clienteIf.getNombreLegal(); 
    					//Creo el nodo cliente
    					nodoCliente = getTreeCampana().insertNode(getRootNodeTreeCampana(),labelNodoCliente,"images/icons/entidad/Cliente.png","images/icons/entidad/Cliente.png",2,clienteIf);
    					//Lo guardo en el mapa el nuevo nodo cliente creado
    					clientesMap.put(clienteIf.getId(),nodoCliente);
          			}          			
				}
				if(nodoCliente != null){
					//Extraiogo el texto a  mostrar en el Nodo
					String labelNodoCampana = campanaIf.getCodigo() + " - " + campanaIf.getNombre() + " (" + campanaIf.getEstado() + ")"; 
					
					//Creo el nodo campana 
					Object nodoCampana = getTreeCampana().insertNode(nodoCliente,labelNodoCampana,"images/icons/entidad/Campana.png","images/icons/entidad/Campana.png",2,campanaIf);
					//Añado los nodos hijos de este nodo
					insertarNodosOrdenTrabajo(nodoCampana,campanaIf.getId());
				}
			}		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void insertarNodosCampanaByEstadoAndByResponsableOrdenTrabajo(){
		try {
			//Extraigo el estado por el cual voy a buscar
			String estadoBuscar = getCmbPorEstado().getSelectedItem().toString().substring(0, 1);
			Map parameterMap = new HashMap();
			parameterMap.put("estado", estadoBuscar);
			Collection campanasByEstadoAndByResponsableOrdenTrabajoCollection = SessionServiceLocator.getCampanaSessionService().findCampanaByQueryAndByResponsableOrdenTrabajoAndByFechaInicioAndFechaFin(parameterMap, porResponsableOTIf.getId(), fechaInicio, fechaFin);
			Iterator itCampanaByEstadoByResponsableOrdenTrabajoCollection = campanasByEstadoAndByResponsableOrdenTrabajoCollection.iterator();
			
			//Sino hay registros al hacer la busqueda muestro un mensaje
			if(!itCampanaByEstadoByResponsableOrdenTrabajoCollection.hasNext()){
				clean();
				SpiritAlert.createAlert("No se encontraron registros de acuerdo al criterio de búsqueda !", SpiritAlert.WARNING);
			}
			//Recorro todas las campañs encontradas de la base
			while(itCampanaByEstadoByResponsableOrdenTrabajoCollection.hasNext()){
				CampanaIf campanaIf = (CampanaIf) itCampanaByEstadoByResponsableOrdenTrabajoCollection.next();
				
				Object nodoCliente = null;
				
				//Si el "nodo cliente"  ya existe en el arbol ingreso el nodo campaña en el
				if(clientesMap.get(campanaIf.getClienteId())!=null){
					//Obtengo el nodo campana que se encuentra guardado en el mapa 
					nodoCliente = (Object) clientesMap.get(campanaIf.getClienteId());
					
				}
				//Sino existe el nodo cliente lo creo para luego insertar el nodo campana
				else{
					//Creo la instancia del nodo cliente seleccionado
          			ClienteIf clienteIf = (ClienteIf) SessionServiceLocator.getClienteSessionService().getCliente(campanaIf.getClienteId());
					//Extraiogo el texto a  mostrar en el Nodo
					String labelNodoCliente = clienteIf.getNombreLegal(); 
					//Creo el nodo cliente
					nodoCliente = getTreeCampana().insertNode(getRootNodeTreeCampana(),labelNodoCliente,"images/icons/entidad/Cliente.png","images/icons/entidad/Cliente.png",2,clienteIf);
					//Lo guardo en el mapa el nuevo nodo cliente creado
					clientesMap.put(clienteIf.getId(),nodoCliente);
				}
				
				//Extraiogo el texto a  mostrar en el Nodo
				String labelNodoCampana = campanaIf.getCodigo() + " - " + campanaIf.getNombre() + " (" + campanaIf.getEstado() + ")"; 
				
				//Creo el nodo campana 
				Object nodoCampana = getTreeCampana().insertNode(nodoCliente,labelNodoCampana,"images/icons/entidad/Campana.png","images/icons/entidad/Campana.png",2,campanaIf);
				//Añado los nodos hijos de este nodo
				insertarNodosOrdenTrabajo(nodoCampana,campanaIf.getId());
			}		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void insertarNodosCampanaByResponsableOrdenTrabajo(){
		try {
			Collection campanaByResponsableOrdenTrabajoCollection = SessionServiceLocator.getCampanaSessionService().findCampanaByResponsableOrdenTrabajoAndByFechaInicioAndFechaFin(porResponsableOTIf.getId(), fechaInicio, fechaFin);
			Iterator itCampanaByResponsableOrdenTrabajoCollection = campanaByResponsableOrdenTrabajoCollection.iterator();
			
			//Sino hay registros al hacer la busqueda muestro un mensaje
			if(!itCampanaByResponsableOrdenTrabajoCollection.hasNext()){
				clean();
				SpiritAlert.createAlert("No se encontraron registros de acuerdo al criterio de búsqueda !", SpiritAlert.WARNING);
			}
			//Recorro todas las campañs encontradas de la base
			while(itCampanaByResponsableOrdenTrabajoCollection.hasNext()){
				CampanaIf campanaIf = (CampanaIf) itCampanaByResponsableOrdenTrabajoCollection.next();
				
				Object nodoCliente = null;
				
				//Si el "nodo cliente"  ya existe en el arbol ingreso el nodo campaña en el
				if(clientesMap.get(campanaIf.getClienteId())!=null){
					//Obtengo el nodo campana que se encuentra guardado en el mapa 
					nodoCliente = (Object) clientesMap.get(campanaIf.getClienteId());
					
				}
				//Sino existe el nodo cliente lo creo para luego insertar el nodo campana
				else{
					//Creo la instancia del nodo cliente seleccionado
          			ClienteIf clienteIf = (ClienteIf) SessionServiceLocator.getClienteSessionService().getCliente(campanaIf.getClienteId());
					//Extraiogo el texto a  mostrar en el Nodo
					String labelNodoCliente = clienteIf.getNombreLegal(); 
					//Creo el nodo cliente
					nodoCliente = getTreeCampana().insertNode(getRootNodeTreeCampana(),labelNodoCliente,"images/icons/entidad/Cliente.png","images/icons/entidad/Cliente.png",2,clienteIf);
					//Lo guardo en el mapa el nuevo nodo cliente creado
					clientesMap.put(clienteIf.getId(),nodoCliente);
				}

				
				//Extraiogo el texto a  mostrar en el Nodo
				String labelNodoCampana = campanaIf.getCodigo() + " - " + campanaIf.getNombre() + " (" + campanaIf.getEstado() + ")"; 
				
				//Creo el nodo campana 
				Object nodoCampana = getTreeCampana().insertNode(nodoCliente,labelNodoCampana,"images/icons/entidad/Campana.png","images/icons/entidad/Campana.png",2,campanaIf);
				//Añado los nodos hijos de este nodo
				insertarNodosOrdenTrabajo(nodoCampana,campanaIf.getId());
			}		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	
	public void insertarNodosOrdenTrabajo(Object nodoCampana,Long idCampana){
		try {
			Collection ordenTrabajoByCampanaCollection = SessionServiceLocator.getOrdenTrabajoSessionService().findOrdenTrabajoByCampanaId(idCampana);
			Iterator itOrdenTrabajoByCampanaCollection = ordenTrabajoByCampanaCollection.iterator();
			
			//Recorro todas las ordenes de trabajo encontradas de la base
			while(itOrdenTrabajoByCampanaCollection.hasNext()){
				OrdenTrabajoIf ordenTrabajoIf = (OrdenTrabajoIf) itOrdenTrabajoByCampanaCollection.next();
				
				//Extraiogo el texto a  mostrar en el Nodo
				String labelNodoOrdenTrabajo = ordenTrabajoIf.getCodigo() + " - " + ordenTrabajoIf.getDescripcion() + " (" + ordenTrabajoIf.getEstado() + ")"; 
				
				//Creo el nodo Ordentrabajo
				Object nodoOrdenTrabajo = getTreeCampana().insertNode(nodoCampana,labelNodoOrdenTrabajo,"images/icons/entidad/OrdenTrabajo.png","images/icons/entidad/OrdenTrabajo.png",2,ordenTrabajoIf);
				//Añado los nodos hijos de este nodo
				insertarNodosOrdenTrabajoDetalle(nodoOrdenTrabajo,ordenTrabajoIf.getId());
			}
			//Para que el arbol se vea colapsado solo hasta las campañas
			getTreeCampana().collapse(getTreeCampana().getNode(nodoCampana));
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void insertarNodosOrdenTrabajoDetalle(Object nodoOrdenTrabajo,Long idOrdenTrabajo){
		try {
			Collection ordenTrabajoDetalleByOrdenTrabajoCollection = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByOrdenId(idOrdenTrabajo);
			Iterator itOrdenTrabajoDetalleByOrdenTrabajoCollection = ordenTrabajoDetalleByOrdenTrabajoCollection.iterator();
			
			//Recorro todas las ordenes de trabajo detalle encontradas de la base
			while(itOrdenTrabajoDetalleByOrdenTrabajoCollection.hasNext()){
				OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) itOrdenTrabajoDetalleByOrdenTrabajoCollection.next();
				
				SubtipoOrdenIf subtipoOrdenIf = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(ordenTrabajoDetalleIf.getSubtipoId());
				
				//Extraiogo el texto a  mostrar en el Nodo
				String labelNodoOrdenTrabajoDetalle = "SUBTIPO: " + subtipoOrdenIf.getCodigo() + " - " + subtipoOrdenIf.getNombre() + " (" + ordenTrabajoDetalleIf.getEstado() + ")"; 
				
				//Creo el nodo OrdentrabajoDetalle
				Object nodoOrdenTrabajoDetalle = getTreeCampana().insertNode(nodoOrdenTrabajo,labelNodoOrdenTrabajoDetalle,"images/icons/entidad/OrdenTrabajoDetalle.png","images/icons/entidad/OrdenTrabajoDetalle.png",2,ordenTrabajoDetalleIf);
				//Añado los nodos hijos de este nodo
				insertarNodosPresupuesto(nodoOrdenTrabajoDetalle,ordenTrabajoDetalleIf.getId());
				insertarNodosPlanMedio(nodoOrdenTrabajoDetalle,ordenTrabajoDetalleIf.getId());
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void insertarNodosPresupuesto(Object nodoOrdenTrabajoDetalle,Long idOrdenTrabajoDetalle){
		try {
			Collection presupuestoByOrdenTrabajoDetalleCollection = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByOrdentrabajodetId(idOrdenTrabajoDetalle);
			Iterator itPresupuestoByOrdenTrabajoDetalleCollection = presupuestoByOrdenTrabajoDetalleCollection.iterator();
			
			//Recorro todas los presupuestos encontrados de la base
			while(itPresupuestoByOrdenTrabajoDetalleCollection.hasNext()){
				PresupuestoIf presupuestoIf = (PresupuestoIf) itPresupuestoByOrdenTrabajoDetalleCollection.next();
				
				//Extraiogo el texto a  mostrar en el Nodo
				String labelNodoPresupuesto = presupuestoIf.getCodigo() + " - " + presupuestoIf.getConcepto() + " (" + presupuestoIf.getEstado() + ")"; 
				
				//Creo el nodo Presupuesto
				Object nodoPresupuesto = getTreeCampana().insertNode(nodoOrdenTrabajoDetalle,labelNodoPresupuesto,"images/icons/entidad/Presupuesto.png","images/icons/entidad/Presupuesto.png",2,presupuestoIf);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	
	public void insertarNodosPlanMedio(Object nodoOrdenTrabajoDetalle,Long idOrdenTrabajoDetalle){
		try {
			Collection planMedioByOrdenTrabajoDetalleCollection = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByOrdenTrabajoDetalleId(idOrdenTrabajoDetalle);
			Iterator itPlanMedioByOrdenTrabajoDetalleCollection = planMedioByOrdenTrabajoDetalleCollection.iterator();
			
			//Recorro todas los plan medios encontrados de la base
			while(itPlanMedioByOrdenTrabajoDetalleCollection.hasNext()){
				PlanMedioIf planMedioIf = (PlanMedioIf) itPlanMedioByOrdenTrabajoDetalleCollection.next();
				
				//Extraiogo el texto a  mostrar en el Nodo
				String labelNodoPlanMedio = planMedioIf.getCodigo() + " - " + planMedioIf.getConcepto() + " (" + planMedioIf.getEstado() + ")"; 
				
				//Creo el nodo PlanMedio
				Object nodoPlanMedio = getTreeCampana().insertNode(nodoOrdenTrabajoDetalle,labelNodoPlanMedio,"images/icons/entidad/Plan de Medios.png","images/icons/entidad/Plan de Medios.png",2,planMedioIf);
				
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void initListeners(){	
		// Manejo los eventos de Buscar Campañas
		getBtnBuscar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				//Mando a vaciar el arbol y Vacio el mapa de nodos raices (CLientes)
				getTreeCampana().removeRootNodeChildren();
				clientesMap.clear();
				
				if(getCbPorFechas().isSelected()){
					fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTimeInMillis());
					fechaFin = new Date(getCmbFechaFin().getCalendar().getTimeInMillis());
				}else{
					fechaInicio = null;
					fechaFin = null;
				}
				
				//Mando a buscar por todas las campañas
				if(getCbTodasCampanas().isSelected())
					insertarNodosCampana();
				
				//Mando a buscar las campañas solo por cliente y que el cliente no sea en blanco
				else if(getCbPorCliente().isSelected() && !"".equals(getTxtPorCliente().getText()) && !getCbPorEstado().isSelected() &&  !getCbPorResponsableOrdenTrabajo().isSelected()){
					insertarNodosCampanaByCliente();
				}
				//Mando a bsucar todas las campanas por el estado escogido
				else if(getCbPorEstado().isSelected() && !getCbPorCliente().isSelected() &&  !getCbPorResponsableOrdenTrabajo().isSelected()){
					insertarNodosCampanaByEstado();
				}
				//Mando a bsucar todas las campanas por el responsable de la orden trabajo
				else if(getCbPorResponsableOrdenTrabajo().isSelected() && !"".equals(getTxtPorResponsableOrdenTrabajo().getText()) && !getCbPorCliente().isSelected() &&  !getCbPorEstado().isSelected()){
					insertarNodosCampanaByResponsableOrdenTrabajo();
				}
				
				//Mando a buscar las campañas por cliente y por estado y que el cliente no sea en blanco
				else if(getCbPorCliente().isSelected() && !"".equals(getTxtPorCliente().getText()) && getCbPorEstado().isSelected() &&  !getCbPorResponsableOrdenTrabajo().isSelected()){
					insertarNodosCampanaByClienteAndByEstado();
				}
				//Mando a buscar las campañas por cliente y por responsable de la orden de trabajo y que el cliente ni el responsable sea en blanco no sea en blanco
				else if(getCbPorCliente().isSelected() && !"".equals(getTxtPorCliente().getText()) && !getCbPorEstado().isSelected() &&  getCbPorResponsableOrdenTrabajo().isSelected() && !"".equals(getTxtPorResponsableOrdenTrabajo().getText())){
					insertarNodosCampanaByClienteAndByResponsableOrdenTrabajo();				}
				//Mando a bsucar todas las campanas por el responsable de la orden trabajo y por estado
				else if(getCbPorResponsableOrdenTrabajo().isSelected() && !"".equals(getTxtPorResponsableOrdenTrabajo().getText()) && getCbPorEstado().isSelected() && !getCbPorCliente().isSelected()){
					insertarNodosCampanaByEstadoAndByResponsableOrdenTrabajo();
				}
				//Mando a buscar las campañas por cliente, por estado y por responsable de la orden de trabajo y que el cliente ni el responsable sea en blanco no sea en blanco
				else if(getCbPorCliente().isSelected() && !"".equals(getTxtPorCliente().getText()) && getCbPorEstado().isSelected() &&  getCbPorResponsableOrdenTrabajo().isSelected() && !"".equals(getTxtPorResponsableOrdenTrabajo().getText())){
					insertarNodosCampanaByClienteAndByEstadoAndByResponsableOrdenTrabajo();
				}
				else
					SpiritAlert.createAlert("Criterio de búsqueda inválido", SpiritAlert.WARNING);

				getTreeCampana().repaint();
			}
		});

		
		// Manejo los eventos de Buscar Corporación
		getBtnBuscarCorporacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				corporacionCriteria = new CorporacionCriteria("Corporaciones",Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					porCorporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
					getTxtPorCorporacion().setText(porCorporacionIf.getNombre());
					getTxtPorCliente().setText("");
				}
			}
		});
		
		// Manejo los eventos de Buscar Cliente
		getBtnBuscarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				
				if (porCorporacionIf != null)
					idCorporacion = porCorporacionIf.getId();

				clienteCriteria = new ClienteCriteria("Clientes",idCorporacion,CODIGO_TIPO_CLIENTE);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					porClienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtPorCliente().setText(porClienteIf.getNombreLegal());
					if (porCorporacionIf == null) {
						try {
							porCorporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(porClienteIf.getCorporacionId());
							getTxtPorCorporacion().setText(porCorporacionIf.getNombre());
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
				}
			}
		});		
		
		// Manejo los eventos de Buscar Represante por Orden Trabajo
		getBtnBuscarResponsableOrdenTrabajo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoCriteria = new EmpleadoCriteria("Representantes por Orden Trabajo",Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					porResponsableOTIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getTxtPorResponsableOrdenTrabajo().setText(porResponsableOTIf.getNombres() + " " + porResponsableOTIf.getApellidos());
				}
			}
		});
		
		//Agrego el listener para cada checkbox
		getCbTodasCampanas().addActionListener(new CheckBoxTodasCampanas());
		getCbPorCliente().addActionListener(new CheckBoxPorCliente());
		getCbPorEstado().addActionListener(new CheckBoxPorEstado());
		getCbPorResponsableOrdenTrabajo().addActionListener(new CheckBoxPorResponsableOrdenTrabajo());
		getTreeCampana().addBrowserListener(oBrowserListener);
	}

	public void cargarCombos(){
		getCmbPorEstado().addItem(NOMBRE_ESTADO_ACTIVA);
		getCmbPorEstado().addItem(NOMBRE_ESTADO_INACTIVA);
		getCmbPorEstado().addItem(NOMBRE_ESTADO_FINALIZADA);
	}

	public void showSaveMode() {
		setSaveMode();
		getCbTodasCampanas().setSelected(false);
		getCbPorCliente().setSelected(false);
		getCbPorEstado().setSelected(false);
		getCbPorResponsableOrdenTrabajo().setSelected(false);
		getCmbPorEstado().setEnabled(false);
		getBtnBuscarCorporacion().setEnabled(false);
		getBtnBuscarCliente().setEnabled(false);
		getBtnBuscarResponsableOrdenTrabajo().setEnabled(false);
		getBtnBuscar().setEnabled(true);
	
		cargarCombos();
	}

	private class CheckBoxTodasCampanas implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Veo si el checkbox esta seleccionado
			if(getCbTodasCampanas().isSelected()){
				getCbPorCliente().setSelected(false);
				getCbPorEstado().setSelected(false);
				getCbPorResponsableOrdenTrabajo().setSelected(false);
				getCmbPorEstado().setEnabled(false);
				getBtnBuscarCorporacion().setEnabled(false);
				getBtnBuscarCliente().setEnabled(false);
				getBtnBuscarResponsableOrdenTrabajo().setEnabled(false);
				getTxtPorCorporacion().setText("");
				getTxtPorCliente().setText("");
				getTxtPorResponsableOrdenTrabajo().setText("");
			}
		}
	}
	
	private class CheckBoxPorCliente implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Veo si el checkbox esta seleccionado
			if(getCbPorCliente().isSelected()){
				getCbTodasCampanas().setSelected(false);
				getBtnBuscarCorporacion().setEnabled(true);
				getBtnBuscarCliente().setEnabled(true);
			} else {
				porCorporacionIf = null;
				porClienteIf = null;
				getBtnBuscarCorporacion().setEnabled(false);
				getBtnBuscarCliente().setEnabled(false);
				getTxtPorCorporacion().setText("");
				getTxtPorCliente().setText("");
			}
		}
	}

	private class CheckBoxPorEstado implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Veo si el checkbox esta seleccionado
			if(getCbPorEstado().isSelected()){
				getCbTodasCampanas().setSelected(false);
				getCmbPorEstado().setEnabled(true);
			}
			else
				getCmbPorEstado().setEnabled(false);
		}
	}
	
	private class CheckBoxPorResponsableOrdenTrabajo implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Veo si el checkbox esta seleccionado
			if(getCbPorResponsableOrdenTrabajo().isSelected()){
				getCbTodasCampanas().setSelected(false);
				getBtnBuscarResponsableOrdenTrabajo().setEnabled(true);
			}
			else{
				getBtnBuscarResponsableOrdenTrabajo().setEnabled(false);
				getTxtPorResponsableOrdenTrabajo().setText("");
			}
		}
	}

	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
}
