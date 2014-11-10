package com.spirit.timeTracker.gui.model;

import static com.spirit.timeTracker.gui.model.Utiles.getMapaProyectosGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.getSubTareaHiloContadorGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.getTablaSubTareasDetalleGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.getTablaSubTareasGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.getTablaTareasGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.removerTodasFilasTabla;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import timeTracker.tiempo.Proyecto;
import timeTracker.tiempo.SubTarea;
import timeTracker.tiempo.SubTareaDetalle;
import timeTracker.tiempo.Tarea;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.EquipoEmpleadoIf;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.entity.OrdenTrabajoData;
import com.spirit.medios.entity.OrdenTrabajoDetalleData;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.medios.entity.TiempoParcialDotDetalleIf;
import com.spirit.medios.entity.TiempoParcialDotIf;
import com.spirit.medios.entity.TipoBriefIf;
import com.spirit.medios.handler.EstadoOrdenTrabajo;
import com.spirit.timeTracker.componentes.SubTareaListener;
import com.spirit.timeTracker.componentes.TiempoCellRenderer;
import com.spirit.timeTracker.gui.main.JPOrdenTrabajo;
import com.spirit.timeTracker.gui.model.cache.MapaCache;
import com.spirit.util.Utilitarios;

public class PanelListaProyectosModel extends JPOrdenTrabajo {

	private static final long serialVersionUID = 1L;
	public static final int COLUMNA_CODIGO_ORDEN_TAREA = 0;
	public static final int COLUMNA_TIEMPO_ORDEN_TAREA = 3;
	
	public static final String ESTADO_ENTREGADO = "T";
	public static final String ESTADO_ACTIVO = "A";

	private static Proyecto proyectoActivoLocal;
	static int numeroProyectos = 0;
	static DefaultTableModel modeloTablaProyectos;
	private JSplitPane splitPaneContenido = null;
	private PanelListaSubTareasModel panelListSubTareas = null;
	private javax.swing.JMenuItem miActualizar;
	private javax.swing.JMenuItem buscarClienteOficina;
	private javax.swing.JMenuItem verOrdenesCreadas;
	private javax.swing.JMenuItem crearOrdenGenerica;
	private javax.swing.JPopupMenu popupProyecto;
	private Map<Long, Set<Long>> mapaEquipos = null;

	TiempoCellRenderer tiempoCellRendererTiempo = new TiempoCellRenderer(true);
	TiempoCellRenderer tiempoCellRendererCodigo = new TiempoCellRenderer(false);
	private List<String> colores = null;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private JDPopupFinderModel popupFinder;
	private ClienteOficinaIf clienteOficinaIf;
	private Vector<OrdenTrabajoDetalleIf> ordenDetalleColeccion = new Vector<OrdenTrabajoDetalleIf>();
	private List<ProductoClienteIf> productoClienteList = new ArrayList<ProductoClienteIf>();

	public PanelListaProyectosModel() {
		iniciarComponente();
		crearPopUp();
		modeloTablaProyectos = (DefaultTableModel) getTblProyectos().getModel();
		modificarTabla();
		iniciarListeners();
		//cargo las ordenes
		//actualizarOrdenesTrabajos(null);
	}

	private void iniciarComponente() {
		getTblProyectos().getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "doNothing");
		getTblProyectos().getActionMap().put("doNothing", doNothing);
	}

	Action doNothing = new AbstractAction() {
		private static final long serialVersionUID = 8289909116937822402L;

		public void actionPerformed(ActionEvent e) {
		}
	};

	private void crearPopUp() {
		popupProyecto = new javax.swing.JPopupMenu();
		miActualizar = new javax.swing.JMenuItem();
		buscarClienteOficina = new javax.swing.JMenuItem();
		verOrdenesCreadas = new javax.swing.JMenuItem();
		crearOrdenGenerica = new javax.swing.JMenuItem();

		popupProyecto.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, "Proyectos",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(
								0, 0, 0)));
		miActualizar.setText("Actualizar");
		popupProyecto.add(miActualizar);
		miActualizar.setIcon(SpiritResourceManager.getImageIcon("images/timetracker/refresh.png"));
		
		buscarClienteOficina.setText("Cliente...");
		popupProyecto.add(buscarClienteOficina);
		buscarClienteOficina.setIcon(SpiritResourceManager.getImageIcon("images/timetracker/findElement.png"));
		
		verOrdenesCreadas.setText("Ver OT creadas");
		popupProyecto.add(verOrdenesCreadas);
		verOrdenesCreadas.setIcon(SpiritResourceManager.getImageIcon("images/timetracker/refresh.png"));

		crearOrdenGenerica.setText("Crear OT...");
		popupProyecto.add(crearOrdenGenerica);
		crearOrdenGenerica.setIcon(SpiritResourceManager.getImageIcon("images/timetracker/addSubTaskSmall.png"));
		
		getTblProyectos().setComponentPopupMenu(popupProyecto);
		getJScrollPane1().setComponentPopupMenu(popupProyecto);
	}

	private void iniciarListeners() {
		// TABLA
		getTblProyectos().addMouseListener(getMlTablaProyecto());
		getTblProyectos().addKeyListener(getKlTablaProyecto());
		// ((AbstractTableModel)getTblProyectos().getModel()).addTableModelListener(getTmlTablaProyectos());

		// POPUP MENU
		popupProyecto.addPopupMenuListener(pmlPopUpProyecto);
		miActualizar.addActionListener(getAlPopupMenuProyecto());
		buscarClienteOficina.addActionListener(getAlPopupMenuBuscarCliente());
		verOrdenesCreadas.addActionListener(alPopupVerOrdenesCreadas);
		crearOrdenGenerica.addActionListener(getAlPopupCrearOrdenTrabajoGenerica());
	}

	private PopupMenuListener pmlPopUpProyecto = new PopupMenuListener() {
		public void popupMenuCanceled(PopupMenuEvent e) {
		}

		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		}

		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			actualizarMenuPopUp();
		}
	};

	private void actualizarMenuPopUp() {
		if (getSubTareaHiloContadorGlobal() == null){
			miActualizar.setEnabled(true);
			buscarClienteOficina.setEnabled(true);
		}else{
			miActualizar.setEnabled(false);
			buscarClienteOficina.setEnabled(true);
		}
	}

	public void modificarTabla() {
		// MODO DE SELECCION
		//getTblProyectos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblProyectos().setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		getTblProyectos().getColumnModel().getColumn(COLUMNA_TIEMPO_ORDEN_TAREA).setCellRenderer(tiempoCellRendererTiempo);
		getTblProyectos().getColumnModel().getColumn(1).setCellRenderer(tiempoCellRendererCodigo);
		getTblProyectos().getColumnModel().getColumn(2).setCellRenderer(tiempoCellRendererCodigo);
		getTblProyectos().getColumnModel().getColumn(COLUMNA_CODIGO_ORDEN_TAREA).setCellRenderer(tiempoCellRendererCodigo);

		getTblProyectos().getColumnModel().getColumn(COLUMNA_CODIGO_ORDEN_TAREA).setPreferredWidth(70);
		getTblProyectos().getColumnModel().getColumn(1).setPreferredWidth(200);
		getTblProyectos().getColumnModel().getColumn(2).setPreferredWidth(200);
		getTblProyectos().getColumnModel().getColumn(COLUMNA_TIEMPO_ORDEN_TAREA).setPreferredWidth(70);
	}

	private ActionListener alPopupMenuProyecto = new ActionListener() {
		public void actionPerformed(ActionEvent acev) {
			if (acev.getSource() instanceof JMenuItem) {
				String comando = acev.getActionCommand();
				if ("actualizar".equalsIgnoreCase(comando)) {
					listenerActualizarOrdenesTrabajo(null);
				}
			}
		}	
	};
	
	private void listenerActualizarOrdenesTrabajo(Long filtroClienteOficinaId) {
		if (getMapaProyectosGlobal().isEmpty()) {
			actualizarOrdenesTrabajos(filtroClienteOficinaId, false);
		} else {
			if (Utiles.existenCambios()){
				SubTareaListener.guardar();
			}
			actualizarOrdenesTrabajos(filtroClienteOficinaId, false);
		}

		int numFilas = getTblProyectos().getRowCount() - 1;
		if (numFilas > 0) {
			getTblProyectos().setRowSelectionInterval(numFilas,	numFilas);
			removerTodasFilasTabla(getTablaTareasGlobal().getModel());
			removerTodasFilasTabla(getTablaSubTareasGlobal().getModel());
			removerTodasFilasTabla(getTablaSubTareasDetalleGlobal().getModel());
		}
	}
	
	private ActionListener alPopupMenuBuscarCliente = new ActionListener() {
		public void actionPerformed(ActionEvent acev) {
			if (acev.getSource() instanceof JMenuItem) {
				String comando = acev.getActionCommand();
				if ("Cliente...".equalsIgnoreCase(comando)) {
					Long idCorporacion = 0L;
					Long idCliente = 0L;
					String tipoCliente = "CL";
					String tituloVentanaBusqueda = "Oficinas de Clientes";
									
					clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, "", false);
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.addElement(70);
					anchoColumnas.addElement(200);
					anchoColumnas.addElement(80);
					anchoColumnas.addElement(230);
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
					if (popupFinder.getElementoSeleccionado() != null) {
						clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
						listenerActualizarOrdenesTrabajo(clienteOficinaIf.getId());
					}
				}
			}
		}
	};
	
	private ActionListener alPopupVerOrdenesCreadas = new ActionListener() {
		public void actionPerformed(ActionEvent acev) {
			if (acev.getSource() instanceof JMenuItem) {
				String comando = acev.getActionCommand();
				if ("Ver OT creadas".equalsIgnoreCase(comando)) {
					if (getMapaProyectosGlobal().isEmpty()) {
						actualizarOrdenesTrabajos(null, true);
					} else {
						if (Utiles.existenCambios()){
							SubTareaListener.guardar();
						}
						actualizarOrdenesTrabajos(null, true);
					}

					int numFilas = getTblProyectos().getRowCount() - 1;
					if (numFilas > 0) {
						getTblProyectos().setRowSelectionInterval(numFilas,	numFilas);
						removerTodasFilasTabla(getTablaTareasGlobal().getModel());
						removerTodasFilasTabla(getTablaSubTareasGlobal().getModel());
						removerTodasFilasTabla(getTablaSubTareasDetalleGlobal().getModel());
					}
				}
			}
		}
	};
	
	private ActionListener alPopupCrearOrdenTrabajoGenerica = new ActionListener() {
		public void actionPerformed(ActionEvent acev) {
			if (acev.getSource() instanceof JMenuItem) {
				String comando = acev.getActionCommand();
				if ("Crear OT...".equalsIgnoreCase(comando)) {
					ArrayList ejecutivos = new ArrayList();
					CrearOrdenTrabajoGenericaModel jdCrearOrdenTrabajoGenerica = new CrearOrdenTrabajoGenericaModel(Parametros.getMainFrame(), ejecutivos);
					
					if(jdCrearOrdenTrabajoGenerica.isCrearOrden()){
						
						if(jdCrearOrdenTrabajoGenerica.getCmbEjecutivo().getSelectedItem() != null &&
								jdCrearOrdenTrabajoGenerica.getClienteOficina() != null){
							
							EmpleadoIf ejecutivoSeleccionado = (EmpleadoIf)jdCrearOrdenTrabajoGenerica.getCmbEjecutivo().getSelectedItem();
							ClienteOficinaIf clienteOficinaOrden = jdCrearOrdenTrabajoGenerica.getClienteOficina();
							String descripcion = jdCrearOrdenTrabajoGenerica.getTxtDescripcion().getText();
							SubtipoOrdenIf subtipo = (SubtipoOrdenIf)jdCrearOrdenTrabajoGenerica.getCmbSubtipo().getSelectedItem();
							EquipoTrabajoIf equipo = (EquipoTrabajoIf)jdCrearOrdenTrabajoGenerica.getCmbEquipo().getSelectedItem();
							OrdenTrabajoIf ordenTrabajo = registrarOrdenTrabajo(ejecutivoSeleccionado.getId(), clienteOficinaOrden, descripcion, subtipo, equipo.getId());
														
							try {
								OrdenTrabajoIf ordenTrabajoGuardada = SessionServiceLocator.getOrdenTrabajoSessionService().procesarOrdenTrabajo(ordenTrabajo, ordenDetalleColeccion, productoClienteList, Parametros.getIdEmpresa(), null, null, null, null);
								
								Vector<OrdenTrabajoDetalleIf> ordenDetalleEliminadas = new Vector<OrdenTrabajoDetalleIf>();
								
								SessionServiceLocator.getOrdenTrabajoSessionService().actualizarOrdenTrabajoDetalle(ordenTrabajoGuardada,ordenDetalleColeccion,ordenDetalleEliminadas,null,	null, null, null, false);
								
							} catch (GenericBusinessException e) {
								e.printStackTrace();
							}
							
						}						
						//System.out.println("crear orden");
					}
				}
			}
		}
	};	
	
	private OrdenTrabajoIf registrarOrdenTrabajo(Long ejecutivoId, ClienteOficinaIf clienteOficinaOrden, String descripcion, SubtipoOrdenIf subtipo, Long equipoId) {
				
		OrdenTrabajoData data = new OrdenTrabajoData();
		UsuarioIf usuario = (UsuarioIf)Parametros.getUsuarioIf();
				
		try {
			Calendar fechaActual = new GregorianCalendar();
			String codigo = getCodigoOrdenTrabajo(new java.sql.Date(fechaActual.getTimeInMillis()));
			data.setCodigo(codigo);
			int dia = fechaActual.getTime().getDate();
			int mes = fechaActual.getTime().getMonth()+1;
			int anio = fechaActual.getTime().getYear()+1900;
			//String descripcion = "ORDEN GENERICA CREADA POR EL USUARIO: " + usuario.getUsuario() + ", FECHA: " + dia + "-" + Utilitarios.getNombreMes(mes) + "-" + anio;
			data.setDescripcion(descripcion);
			data.setOficinaId(Parametros.getIdOficina());
			data.setClienteoficinaId(clienteOficinaOrden.getId());
			ClienteIf clienteOrden = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaOrden.getClienteId());
			
			//seteo automatico la campaña mas nueva
			Map mapaCampana = new HashMap();
			mapaCampana.put("estado", ESTADO_ACTIVO);
			mapaCampana.put("clienteId", clienteOrden.getId());
			ArrayList<CampanaIf> campanaCliente = (ArrayList<CampanaIf>)SessionServiceLocator.getCampanaSessionService().findCampanaByQuery(mapaCampana);
			if(campanaCliente.size() > 0){
				data.setCampanaId(campanaCliente.get(campanaCliente.size()-1).getId());
			}			
			
			data.setEmpleadoId(ejecutivoId);
			data.setFecha(new java.sql.Timestamp(fechaActual.getTimeInMillis()));
			data.setFechalimite(new java.sql.Timestamp(fechaActual.getTimeInMillis()));
			data.setFechaentrega(null);
			data.setUrlPropuesta(null);
			data.setEstado("P");		
			data.setObservacion("");
			//UsuarioIf usuarioEjecutivo = (UsuarioIf)SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpleadoId(ejecutivoId).iterator().next();
			//data.setUsuarioCreacionId(usuarioEjecutivo.getId());
			data.setUsuarioCreacionId(((UsuarioIf)Parametros.getUsuarioIf()).getId());
			data.setFechaCreacion(new java.sql.Timestamp((new Date()).getTime()));
			
			//para setear el equipo del ejecutivo
			EquipoEmpleadoIf equipoEjecutivo = (EquipoEmpleadoIf)SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByEmpleadoId(ejecutivoId).iterator().next();				
			EquipoTrabajoIf equipoTrabajoEjecutivo = (EquipoTrabajoIf)SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajo(equipoEjecutivo.getEquipoId());
			data.setEquipoId(equipoTrabajoEjecutivo.getId());
			data.setTimetracker("S");
			
			//creo orden trabajo detalle coleccion con un solo detalle
			//el detalle va dirigido al empleado usuario de timetracker que esta creando esta orden generica.
			ordenDetalleColeccion = new Vector<OrdenTrabajoDetalleIf>();
			
			OrdenTrabajoDetalleData dataDetalle = new OrdenTrabajoDetalleData();
			
			EmpleadoIf empleadoUsuario = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(usuario.getEmpleadoId());
			//EquipoEmpleadoIf equipoEmpleado = (EquipoEmpleadoIf)SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByEmpleadoId(empleadoUsuario.getId()).iterator().next();				
			//EquipoTrabajoIf equipoTrabajo = (EquipoTrabajoIf)SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajo(equipoEmpleado.getEquipoId());
			//SubtipoOrdenIf subtipo = (SubtipoOrdenIf)SessionServiceLocator.getSubtipoOrdenSessionService().findSubtipoOrdenByTipoordenId(equipoTrabajo.getTipoordenId()).iterator().next();
						
			dataDetalle.setSubtipoId(subtipo.getId());
			dataDetalle.setEquipoId(equipoId);
			dataDetalle.setAsignadoaId(empleadoUsuario.getId());
			dataDetalle.setFechalimite(new java.sql.Date(fechaActual.getTimeInMillis()));
			dataDetalle.setFechaentrega(null);
			dataDetalle.setUrlDescripcion(null);
			dataDetalle.setUrlPropuesta(null);			
			dataDetalle.setDescripcion(descripcion);
			dataDetalle.setEstado("P");
			dataDetalle.setFecha(new java.sql.Timestamp(fechaActual.getTimeInMillis()));			
			ordenDetalleColeccion.add(dataDetalle);
			
			//agrego por lo menos un producto en la coleccion productoClienteList
			//productoClienteList
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}	
		
		return data;
	}
	
	private String getCodigoOrdenTrabajo(java.sql.Date fechaOrdenTrabajo) {
		String codigo = "";
		String anioOrdenTrabajo = Utilitarios.getYearFromDate(fechaOrdenTrabajo);
		codigo += anioOrdenTrabajo + "-";
		return codigo;
	}

	private MouseListener mlTablaProyecto = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
			int filaSeleccionada = getTblProyectos().getSelectedRow();
			if (filaSeleccionada >= 0) {
				//Utiles.mostrarInformacionProyectoGlobal();
				llenarTablaTareas();
			} else {
				Utiles.borrarInformacionPanelGlobal();
				removerTodasFilasTabla(Utiles.getTablaTareasGlobal().getModel());
				removerTodasFilasTabla(Utiles.getTablaSubTareasGlobal()
						.getModel());
				removerTodasFilasTabla(getTablaSubTareasDetalleGlobal()
						.getModel());
			}

		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	};

	private KeyListener klTablaProyecto = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
		}

		public void keyReleased(KeyEvent kev) {
			int filaSeleccionada = getTblProyectos().getSelectedRow();
			if (filaSeleccionada >= 0) {
				if (kev.getKeyCode() == KeyEvent.VK_DOWN
						|| kev.getKeyCode() == KeyEvent.VK_UP) {
					//Utiles.mostrarInformacionProyectoGlobal();
					llenarTablaTareas();
				}
			} else {
				Utiles.borrarInformacionPanelGlobal();
				removerTodasFilasTabla(Utiles.getTablaTareasGlobal().getModel());
				removerTodasFilasTabla(Utiles.getTablaSubTareasGlobal()
						.getModel());
				removerTodasFilasTabla(getTablaSubTareasDetalleGlobal()
						.getModel());
			}
		}

		public void keyTyped(KeyEvent e) {
		}
	};

	public void actualizarOrdenesTrabajos(Long filtroClienteOficinaId, boolean otCreadasEnTimeTracker) {

		getMapaProyectosGlobal().clear();

		colores = null;
		colores = new ArrayList<String>();
		tiempoCellRendererTiempo.setColores(colores);
		tiempoCellRendererCodigo.setColores(colores);
		Utiles.mapaContador.clear();
		
		mapaEquipos = null;
		mapaEquipos = new HashMap<Long, Set<Long>>();

		MapaCache.setMapaEmpleados(null);
		MapaCache.setMapaEmpleados(new HashMap<Long, EmpleadoIf>());
		MapaCache.setMapaClientes(null);
		MapaCache.setMapaClientes(new HashMap<Long, ClienteIf>());
		MapaCache.setMapaSubTipoOrden(null);
		MapaCache.setMapaSubTipoOrden(new HashMap<Long, SubtipoOrdenIf>());
		MapaCache.setMapaTipoOrden(null);
		MapaCache.setMapaTipoOrden(new HashMap<Long, TipoOrdenIf>());
		MapaCache.setMapaTipoBrief(null);
		MapaCache.setMapaTipoBrief(new HashMap<Long, TipoBriefIf>());
		MapaCache.setMapaProductoCliente(null);
		MapaCache.setMapaProductoCliente(new HashMap<Long, ProductoClienteIf>());
		MapaCache.setMapaClienteOficina(null);
		MapaCache.setMapaClienteOficina(new HashMap<Long, ClienteOficinaIf>());

		llenarMapaEquipo();

		removerTodasFilasTabla(getTblProyectos().getModel());
		removerTodasFilasTabla(Utiles.getTablaTareasGlobal().getModel());
		Utiles.borrarInformacionPanelGlobal();
		removerTodasFilasTabla(Utiles.getTablaSubTareasGlobal().getModel());
		removerTodasFilasTabla(Utiles.getTablaSubTareasDetalleGlobal().getModel());

		try {
			UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
			Long empleadoAsignadoId = usuario.getEmpleadoId(); 
			HashMap<String, Proyecto> proyectosAntiguos = getMapaProyectosGlobal();
			
			Collection<OrdenTrabajoIf> ordenesTrabajos = SessionServiceLocator
					.getOrdenTrabajoSessionService()
					.findOrdenTrabajoByQueryAndByAsignadoaByEstados(otCreadasEnTimeTracker,
							empleadoAsignadoId,	usuario.getTipousuarioTimetracker(),
							Parametros.getIdEmpresa(), filtroClienteOficinaId, 
							EstadoOrdenTrabajo.PENDIENTE.getLetra(),
							EstadoOrdenTrabajo.EN_CURSO.getLetra(),
							EstadoOrdenTrabajo.ENTREGADO.getLetra());
			
			for (OrdenTrabajoIf ordenTrabajo : ordenesTrabajos) {
				addOrdenTrabajo(proyectosAntiguos, ordenTrabajo, empleadoAsignadoId);
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			e.printStackTrace();
		} catch (Exception e) {
			SpiritAlert.createAlert(
							"Ocurrio un error en la actualizaci\u00f3n de las Ordenes de Trabajo",
							SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void llenarMapaEquipo() {
		mapaEquipos = null;
		mapaEquipos = new HashMap<Long, Set<Long>>();

		// Calendar cal = Calendar.getInstance();
		/*
		 * cal.set(Calendar.DAY_OF_MONTH,1); cal.set(Calendar.MONTH,0); int anio
		 * = cal.get(Calendar.YEAR); Date fechaInicio = new
		 * Date(cal.getTimeInMillis());
		 */

		/*
		 * cal.set(Calendar.DAY_OF_MONTH,31); cal.set(Calendar.MONTH,11); Date
		 * fechaFin = new Date(cal.getTimeInMillis());
		 */

		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("empresaId", Parametros.getIdEmpresa());
		// mapa.put("fechaFin",fechaFin);

		try {
			// TODO: crear metodo que pueda mandar rango de fecha para obtener
			// los equipos de trabajo validos
			Collection<EquipoTrabajoIf> listaEquipos = SessionServiceLocator
					.getEquipoTrabajoSessionService().findEquipoTrabajoByQuery(
							mapa);
			for (EquipoTrabajoIf et : listaEquipos) {

				Set<Long> setEmpleados = new HashSet<Long>();
				Collection<EquipoEmpleadoIf> listaEmpleado = SessionServiceLocator
						.getEquipoEmpleadoSessionService()
						.findEquipoEmpleadoByEquipoId(et.getId());
				for (EquipoEmpleadoIf ee : listaEmpleado) {
					setEmpleados.add(ee.getEmpleadoId());
				}
				mapaEquipos.put(et.getId(), setEmpleados);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private Set<Long> obtenerEquipoTrabajoPorUsuarioId(Long idUsuario) {

		Iterator<Long> itMapaEquipo = mapaEquipos.keySet().iterator();
		while (itMapaEquipo.hasNext()) {
			Long idEquipo = itMapaEquipo.next();
			Set<Long> empleados = mapaEquipos.get(idEquipo);
			Iterator<Long> itEmpleados = empleados.iterator();
			while (itEmpleados.hasNext()) {
				Long idEmpleado = itEmpleados.next();
				if (idUsuario.equals(idEmpleado)) {
					return empleados;
				}
			}
		}
		return null;
	}

	private boolean usuarioPerteneceAEquipo(Long idUsuario, Long idAsignadoA) {

		Set<Long> empleados = obtenerEquipoTrabajoPorUsuarioId(idUsuario);
		if (empleados != null) {
			Iterator<Long> itEmpleados = empleados.iterator();
			while (itEmpleados.hasNext()) {
				Long idEmpleado = itEmpleados.next();
				if (idAsignadoA.equals(idEmpleado)) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Llena la tabla de tareas con las tareas que le pertenecen al proyecto
	 * seleccionado.
	 */
	private void llenarTablaTareas() {
		try {
			Proyecto proyecto = Utiles.getProyectoActivoGlobal();
			habilitarImpresionTablaTareas(proyecto);
			setProyectoActivoLocal(proyecto);
			Iterator itTarea = null;
			if (proyecto != null) {
				removerTodasFilasTabla(getTablaTareasGlobal().getModel());
				removerTodasFilasTabla(getTablaSubTareasGlobal().getModel());
				removerTodasFilasTabla(getTablaSubTareasDetalleGlobal().getModel());

				DefaultTableModel modeloTareas = (DefaultTableModel) getTablaTareasGlobal().getModel();
				itTarea = proyecto.getTareas().keySet().iterator();
				
				while (itTarea.hasNext()) {
					Tarea tarea = (Tarea) proyecto.getTareas().get(	itTarea.next());
					modeloTareas.addRow(tarea.getFilaDeTabla());
				}				
				
				//si es la primera vez que da click en la orden y la tarea va dirigida para el usuario
				//entonces va directamente a la tarea, caso contrario muestra la información general de la orden
				if(Utiles.mapaContador.get(proyecto.getOrdenTrabajo().getId()) == null){
					UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
					//selecciono por default la tarea del usuario actual (si existe)
					for(int i=0; i<getTablaTareasGlobal().getModel().getRowCount(); i++){
						EmpleadoIf empleado = (EmpleadoIf)getTablaTareasGlobal().getModel().getValueAt(i, 0);
												
						if(empleado.getId().compareTo(usuario.getEmpleadoId()) == 0){
							ListSelectionModel selectionModel = getTablaTareasGlobal().getSelectionModel();
							selectionModel.setSelectionInterval(i, i);
						}					
					}
					
					//si la orden no fue dirigida al usuario pero existe una tarea para el usuario
					if(getTablaTareasGlobal().getSelectedRow() == -1){
						HashMap<Long,Tarea> mapaTareas = proyecto.getTareas();
						for ( Long idTarea : mapaTareas.keySet() ){
							Collection subTareas = proyecto.getTareas().get(idTarea).getSubTareas();
							Iterator subTareasIt = subTareas.iterator();
							while(subTareasIt.hasNext()){
								SubTarea subTarea = (SubTarea)subTareasIt.next();
								if(subTarea.getUsuarioAsignadoId().compareTo(usuario.getEmpleadoId()) == 0){
									for(int i=0; i<getTablaTareasGlobal().getModel().getRowCount(); i++){
										EmpleadoIf empleado = (EmpleadoIf)getTablaTareasGlobal().getModel().getValueAt(i, 0);
										Long empleadoOrdenDetalleId = proyecto.getTareas().get(idTarea).getTareaOrdenTrabajo().getAsignadoaId();
										if(empleado.getId().compareTo(empleadoOrdenDetalleId) == 0){
											ListSelectionModel selectionModel = getTablaTareasGlobal().getSelectionModel();
											selectionModel.setSelectionInterval(i, i);
										}					
									}
								}
							}
						}
						//si la orden no esta dirigida al usuario ni existe tarea, muestra la info general
						if(getTablaTareasGlobal().getSelectedRow() == -1){
							Utiles.mostrarInformacionProyectoGlobal();
						}
					}
				}else{
					Utiles.mostrarInformacionProyectoGlobal();
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			if (ex instanceof GenericBusinessException)
				SpiritAlert.createAlert(ex.getMessage(),SpiritAlert.INFORMATION);
			else
				SpiritAlert.createAlert("Ocurrio un error al cargar la tabla de tareas",SpiritAlert.INFORMATION);
		}
	}

	public void addOrdenTrabajo(HashMap<String, Proyecto> proyectosAntiguos,
			OrdenTrabajoIf ordenTrabajo, Long idEmpleadoUsuario) {
		long segundosProyecto = 0L;
		long segundosTarea = 0L;
		
		Proyecto proyectoNuevo = new Proyecto(ordenTrabajo.getCodigo(),ordenTrabajo);
		try {
			boolean ordenDetalleEntregada = false;
			Long tiempo = -1L;
			
			UsuarioIf usuarioIf = (UsuarioIf) Parametros.getUsuarioIf();
			String tipoUsuarioTimeTracker = usuarioIf.getTipousuarioTimetracker();
						
			Collection<OrdenTrabajoDetalleIf> listaOrdenDetalle = null;
			if (tipoUsuarioTimeTracker.equalsIgnoreCase("E") || tipoUsuarioTimeTracker.equalsIgnoreCase("S")) {
				listaOrdenDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByOrdenId(ordenTrabajo.getId());
			} else if (tipoUsuarioTimeTracker.equalsIgnoreCase("J")) {
				listaOrdenDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByEquipo(ordenTrabajo,idEmpleadoUsuario, EstadoOrdenTrabajo.PENDIENTE.getLetra(), EstadoOrdenTrabajo.EN_CURSO.getLetra(), EstadoOrdenTrabajo.ENTREGADO.getLetra());
			} else {
				listaOrdenDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByAsignado(ordenTrabajo,idEmpleadoUsuario, EstadoOrdenTrabajo.PENDIENTE.getLetra(), EstadoOrdenTrabajo.EN_CURSO.getLetra(), EstadoOrdenTrabajo.ENTREGADO.getLetra());
			}
			
			for (OrdenTrabajoDetalleIf ordenTrabajoDetalleIf : listaOrdenDetalle) {
				segundosTarea = 0L;
				Tarea tareaNueva = new Tarea(ordenTrabajoDetalleIf.getId().longValue(), ordenTrabajoDetalleIf);
				ArrayList<TiempoParcialDotIf> tiemposParciales = (ArrayList<TiempoParcialDotIf>)SessionServiceLocator.getTiempoParcialDotSessionService().findTiempoParcialDotByIdOrdenTrabajoDetalle(ordenTrabajoDetalleIf.getId());
				for (int i=0; i<tiemposParciales.size(); i++){
					TiempoParcialDotIf tiempoParcial = tiemposParciales.get(i);
					//solo si es la primera iteracion el tiempo se resetea a cero.
					tiempo = i==0?0L:tiempo;
					SubTarea subTareaNueva = new SubTarea(tiempoParcial);
					segundosTarea += subTareaNueva.getSegundos();
					segundosProyecto += subTareaNueva.getSegundos();
					Collection<TiempoParcialDotDetalleIf> tiemposDetalles = SessionServiceLocator.getTiempoParcialDotDetalleSessionService().findTiempoParcialDotDetalleByIdTiempoParcialDot(tiempoParcial.getId());
					for (TiempoParcialDotDetalleIf tiempoDetalle : tiemposDetalles) {
						SubTareaDetalle detalle = new SubTareaDetalle(tiempoDetalle);
						subTareaNueva.getDetalle().add(detalle);
						tiempo = tiempo + tiempoDetalle.getTiempo();
					}
					tareaNueva.getSubTareas().add(subTareaNueva);
				}
								
				tareaNueva.setSegundosTotales(segundosTarea);
				proyectoNuevo.getTareas().put(tareaNueva.getIdTarea(), tareaNueva);
				
				//Si esta la orden Entregada entonces lo pongo como true para que se pinte de rojo
				//en caso que sea una ejecutiva se verifica que ella haya hecho la orden
				if(ordenTrabajoDetalleIf.getEstado().equals(ESTADO_ENTREGADO)){
					if (!tipoUsuarioTimeTracker.equalsIgnoreCase("E")){
						ordenDetalleEntregada = true;
					}else if(idEmpleadoUsuario.compareTo(ordenTrabajo.getEmpleadoId()) == 0){
						ordenDetalleEntregada = true;
					}
				}
			}
			
			proyectoNuevo.setSegundosTotales(segundosProyecto);
			
			// PINTAR FILA
			//Si el usuario es Ejecutivo (no es Director) y la orden no la hizo él entonces queda en blanco. 
			//Busco el tipo empleado Director de Cuentas (DCU).
			TipoEmpleadoIf tipoEmpleadoDirectorCuentas = (TipoEmpleadoIf)SessionServiceLocator.getTipoEmpleadoSessionService().findTipoEmpleadoByCodigo("DCU").iterator().next();
			EmpleadoIf ejecutivo = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(idEmpleadoUsuario);
			if (tipoUsuarioTimeTracker.equalsIgnoreCase("E") && !(idEmpleadoUsuario.compareTo(ordenTrabajo.getEmpleadoId()) == 0)
					&& !(ejecutivo.getTipoempleadoId().compareTo(tipoEmpleadoDirectorCuentas.getId()) == 0)){
				tiempo = -2L;
			}
			//orden nueva CELESTE (la actual)
			//orden asignada VERDE (con tiempo cero)
			//orden en curso AMARILLO (con tiempo mayor que cero)
			//orden entregada ROJO (nuevo estado asignado por el usuario)
			//Se pinta la fila si el usuario que ingreso al sistema es el usuario asignado a la orden
			if(ordenDetalleEntregada){
				colores.add("ROJO");
			}else if(tiempo.compareTo(-2L) == 0){
				colores.add("BLANCO");
			}else if(tiempo.compareTo(-1L) == 0){
				colores.add("CELESTE");
			}else if(tiempo.compareTo(0L) == 0){
				colores.add("VERDE");
			}else{
				colores.add("AMARILLO");
			}						
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}

		getMapaProyectosGlobal().put(proyectoNuevo.getIdProyecto(),	proyectoNuevo);
		getModeloTablaProyectos().addRow(proyectoNuevo.getFilaDeTabla());
		setProyectoActivoLocal(proyectoNuevo);
		setNumeroProyectos(getNumeroProyectos() + 1);
	}

	private void habilitarImpresionTablaTareas(Proyecto proyecto) {
		if (getSubTareaHiloContadorGlobal() != null) {
			Proyecto proyectoHiloContador = getSubTareaHiloContadorGlobal()
					.getProyecto();
			if (proyecto != proyectoHiloContador) {
				getSubTareaHiloContadorGlobal().setImprimirEnTablaTareas(false);
			} else {
				getSubTareaHiloContadorGlobal().setImprimirEnTablaTareas(true);
			}
		}
	}

	public ActionListener getAlPopupMenuProyecto() {
		return alPopupMenuProyecto;
	}

	public void setAlPopupMenuProyecto(ActionListener alPopupMenuProyecto) {
		this.alPopupMenuProyecto = alPopupMenuProyecto;
	}

	static int getNumeroProyectos() {
		return numeroProyectos;
	}

	public static void setNumeroProyectos(int numeroProyectosS) {
		numeroProyectos = numeroProyectosS;
	}

	public MouseListener getMlTablaProyecto() {
		return mlTablaProyecto;
	}

	public void setMlTablaProyecto(MouseListener mlTablaProyecto) {
		this.mlTablaProyecto = mlTablaProyecto;
	}

	public static DefaultTableModel getModeloTablaProyectos() {
		return modeloTablaProyectos;
	}

	public KeyListener getKlTablaProyecto() {
		return klTablaProyecto;
	}

	public void setKlTablaProyecto(KeyListener klTablaProyecto) {
		this.klTablaProyecto = klTablaProyecto;
	}

	public JSplitPane getSplitPaneContenido() {
		return splitPaneContenido;
	}

	public void setSplitPaneContenido(JSplitPane splitPaneContenido) {
		this.splitPaneContenido = splitPaneContenido;
	}

	public PanelListaSubTareasModel getPanelListSubTareas() {
		return panelListSubTareas;
	}

	public void setPanelListSubTareas(
			PanelListaSubTareasModel panelListSubTareas) {
		this.panelListSubTareas = panelListSubTareas;
	}

	public static Proyecto getProyectoActivoLocal() {
		return proyectoActivoLocal;
	}

	public static void setProyectoActivoLocal(Proyecto aProyectoActivoLocal) {
		proyectoActivoLocal = aProyectoActivoLocal;
	}

	public ActionListener getAlPopupMenuBuscarCliente() {
		return alPopupMenuBuscarCliente;
	}

	public ActionListener getAlPopupCrearOrdenTrabajoGenerica() {
		return alPopupCrearOrdenTrabajoGenerica;
	}

}
