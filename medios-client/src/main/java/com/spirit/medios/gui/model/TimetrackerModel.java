package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jidesoft.docking.DockableFrame;
import com.spirit.client.MainFrameModel;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritModel;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.PanelHandler;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.medios.entity.TiempoParcialDotDetalleIf;
import com.spirit.medios.entity.TiempoParcialDotIf;
import com.spirit.medios.gui.panel.JPTimetracker;
import com.spirit.util.Utilitarios;

public class TimetrackerModel extends JPTimetracker {
	
	private static final long serialVersionUID = -5943775152553978810L;
	
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String TODOS = "TODOS";
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_REALIZADO = "REALIZADO";
	private static final String NOMBRE_ESTADO_ENTREGADO = "ENTREGADO";
	private static final String ESTADO_PENDIENTE = "P";
	private static final String ESTADO_ENCURSO = "E";
	private static final String ESTADO_REALIZADO = "R";
	private static final String ESTADO_ENTREGADO = "T";
	private DefaultTableModel tableModel;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	protected ClienteOficinaIf clienteOficinaIf;
	protected ClienteIf clienteIf;
	private Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
	private Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
	private Map<Long,SubtipoOrdenIf> mapaSubtipoOrden = new HashMap<Long,SubtipoOrdenIf>();
	private Map<Long,EmpleadoIf> mapaEmpleado = new HashMap<Long,EmpleadoIf>();
	private Map<Long,UsuarioIf> mapaUsuario = new HashMap<Long,UsuarioIf>();
	private Map<Long,OrdenTrabajoIf> mapaOrdenTrabajo = new HashMap<Long,OrdenTrabajoIf>();
	private JTable tblReporte = new JTable();
	protected JMenuItem menuItemAbrirOrden, menuItemOrdenDetalleRealizada;
	protected JPopupMenu popup = new JPopupMenu();;
	private static Map panels;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no};
	private OrdenTrabajoIf ordenTrabajoSeleccionada;
	private OrdenTrabajoDetalleIf ordenTrabajoDetalleSeleccionada;
	private List<OrdenTrabajoDetalleIf> ordenTrabajoDetalleList = new ArrayList<OrdenTrabajoDetalleIf>();
	private String[] estados;
	private String[] estadosTodos = {"P","E","R","T"};
	private String[] estadosPendientes = {"P","E"};
	private String[] estadoRealizado = {"R"};	
	private String[] estadoEntregado = {"T"};
	private Long idTipoOrden = 0L;	
	private Integer cambioOrden = 0;
	private Long ordenTiempoTotal = 0L;
	private Map<String,Long> mapaClienteTiempo = new HashMap<String,Long>();
	private Map<String,Long> mapaResponsableTiempo = new HashMap<String,Long>();
	private Long idEmpleadoResponsable = 0L;
	private Date fechaInicio = new Date(new GregorianCalendar().getTimeInMillis());
	private Date fechaFin = new Date(new GregorianCalendar().getTimeInMillis());
	private Long idClienteOficina = 0L;
	private Long totalTiempo = 0L;
	
	public TimetrackerModel(){
		panels = MainFrameModel.get_panels();
		cargarCombos();
		initKeyListeners();
		anchoColumnasTabla();
		showSaveMode();
		cargarMapas();
		initListeners();
		iniciarTablaReporte();
	}
	
	public void cargarCombos(){
		cargarComboEjecutivo();
		cargarComboOficina();
		cargarComboTipoOrden();
		cargarComboEstado();
	}
	
	private void cargarComboTipoOrden(){
		try {
			List tiposOrden = (List) SessionServiceLocator.getTipoOrdenSessionService()
			.findTipoOrdenByEmpresaId(Parametros.getIdEmpresa());
			tiposOrden.add(TODOS);
			refreshCombo(getCmbTipoOrden(),tiposOrden);
			getCmbTipoOrden().setSelectedItem(TODOS);
			getCmbResponsable().setEnabled(false);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void cargarComboEstado() {
		getCmbEstado().removeAllItems();
		getCmbEstado().addItem(TODOS);
		getCmbEstado().addItem(NOMBRE_ESTADO_PENDIENTE);
		getCmbEstado().addItem(NOMBRE_ESTADO_ENTREGADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_REALIZADO);
		getCmbEstado().setSelectedItem(TODOS);
	}
	
	public void iniciarTablaReporte(){		
		tblReporte.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null, "", null, null, null, null, null, ""},
					},
					new String[] {
						"Cod.", "Cliente", "Trabajo", "Ejecutivo(a)", "SubTipo", "Responsable", "F.Creaci\u00f3n", "F.Limite", "F.Entrega", "T.Parcial", "T.Total"
					}
				) {
					boolean[] columnEditable = new boolean[] {
						false, false, false, false, false, false, false, false, false, false, false
					};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
	}
	
	private void anchoColumnasTabla() {
		//setSorterTable(getTblOrdenes());
		getTblOrdenes().getTableHeader().setReorderingAllowed(false);
		getTblOrdenes().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//getTblFacturacion().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblOrdenes().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(145);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(67);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(67);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(67);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(50);
	}
	
	Comparator<EmpleadoIf> ordenadorArrayListPorNombre = new Comparator<EmpleadoIf>(){
		public int compare(EmpleadoIf o1, EmpleadoIf o2) {
			return (o1.getNombres().compareTo(o2.getNombres()));
		}		
	};
	
	private void cargarComboEjecutivo(){
		try {
			List ejecutivos = new ArrayList();
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("empresaId", Parametros.getIdEmpresa());
			mapa.put("vendedor","S");
			Iterator it = SessionServiceLocator.getTipoEmpleadoSessionService().findTipoEmpleadoByQuery(mapa).iterator();
			while (it.hasNext()) {
				TipoEmpleadoIf tipoEmpleado = (TipoEmpleadoIf) it.next();
				mapa.clear();
				mapa.put("tipoempleadoId" , tipoEmpleado.getId() );
				mapa.put("estado" , "A");
				Iterator vendedoresIt = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByQuery(mapa).iterator();
				while (vendedoresIt.hasNext()) {
					EmpleadoIf empleado = (EmpleadoIf) vendedoresIt.next();
					ejecutivos.add(empleado);
				}
			}
			Collections.sort((ArrayList)ejecutivos,ordenadorArrayListPorNombre);
			ejecutivos.add(TODOS);
			refreshCombo(getCmbEjecutivo(),ejecutivos);
			getCmbEjecutivo().setSelectedItem(TODOS);
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboOficina(){
		try {
			List oficinas = (ArrayList)SessionServiceLocator.getOficinaSessionService().getOficinaList();
			oficinas.add(TODOS);
			refreshCombo(getCmbOficina(),oficinas);
			getCmbOficina().setSelectedItem(TODOS);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarMapas(){
		try {
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList();
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while(clientesOficinaIt.hasNext()){
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf)clientesOficinaIt.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
			
			mapaCliente.clear();
			Collection clientes = SessionServiceLocator.getClienteSessionService().getClienteList();
			Iterator clientesIt = clientes.iterator();
			while(clientesIt.hasNext()){
				ClienteIf cliente = (ClienteIf)clientesIt.next();
				mapaCliente.put(cliente.getId(), cliente);
			}
			
			mapaSubtipoOrden.clear();
			Collection subtipoOrdenes = SessionServiceLocator.getSubtipoOrdenSessionService().findSubtipoOrdenByEmpresaId(Parametros.getIdEmpresa());
			Iterator subtipoOrdenesIt = subtipoOrdenes.iterator();
			while(subtipoOrdenesIt.hasNext()){
				SubtipoOrdenIf subtipoOrden = (SubtipoOrdenIf)subtipoOrdenesIt.next();
				mapaSubtipoOrden.put(subtipoOrden.getId(), subtipoOrden);
			}
			
			mapaEmpleado.clear();
			Collection empleados = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Iterator empleadosIt = empleados.iterator();
			while(empleadosIt.hasNext()){
				EmpleadoIf empleado = (EmpleadoIf)empleadosIt.next();
				mapaEmpleado.put(empleado.getId(), empleado);
			}
			
			mapaUsuario.clear();
			Collection usuarios = SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpresaId(Parametros.getIdEmpresa());
			Iterator usuariosIt = usuarios.iterator();
			while(usuariosIt.hasNext()){
				UsuarioIf usuario = (UsuarioIf)usuariosIt.next();
				mapaUsuario.put(usuario.getId(), usuario);
			}
			
			mapaOrdenTrabajo.clear();
			Collection ordenesTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajoList();
			Iterator ordenesTrabajoIt = ordenesTrabajo.iterator();
			while(ordenesTrabajoIt.hasNext()){
				OrdenTrabajoIf ordenTrabajo = (OrdenTrabajoIf)ordenesTrabajoIt.next();
				mapaOrdenTrabajo.put(ordenTrabajo.getId(), ordenTrabajo);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	public void initKeyListeners(){
		getCbOrdenTrabajo().setSelected(true);
		getCbResponsable().setSelected(false);
		getTxtCliente().setEditable(false);
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
		
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnConsultar().setToolTipText("Consultar");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		/*TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		tblFacturacion.getColumnModel().getColumn(4).setCellRenderer(tableCellRendererCenter);
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblFacturacion.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);*/		
	}
	
	private void initListeners() {
		getCmbTipoOrden().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					if(getCmbTipoOrden().getSelectedItem().equals(TODOS)){
						getCmbResponsable().setSelectedIndex(-1);
						getCmbResponsable().setEnabled(false);
					}else{			
						List responsables = new ArrayList();
						Map<Long,EmpleadoIf> empleadosMap = new HashMap<Long,EmpleadoIf>();
						TipoOrdenIf tipoOrden = (TipoOrdenIf)getCmbTipoOrden().getSelectedItem();
						ArrayList<EquipoTrabajoIf> equiposList = (ArrayList<EquipoTrabajoIf>)SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByTipoordenId(tipoOrden.getId());
						for(EquipoTrabajoIf equipoTrabajo : equiposList){
							ArrayList<EmpleadoIf> equipoEmpleadosList = (ArrayList<EmpleadoIf>)SessionServiceLocator.getEquipoEmpleadoSessionService().findEmpleadoByEquipoTrabajoId(equipoTrabajo.getId());
							for(EmpleadoIf empleado : equipoEmpleadosList){
								empleadosMap.put(empleado.getId(), empleado);
							}
						}
						Iterator empleadosMapIt = empleadosMap.keySet().iterator();
						while(empleadosMapIt.hasNext()){
							Long keyEmpleadosMap = (Long)empleadosMapIt.next();
							responsables.add(empleadosMap.get(keyEmpleadosMap));
						}
						Collections.sort((ArrayList)responsables,ordenadorArrayListPorNombre);
						responsables.add(TODOS);
						refreshCombo(getCmbResponsable(),responsables);
						getCmbResponsable().setSelectedItem(TODOS);
						getCmbResponsable().setEnabled(true);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}
			}
		});
		
		getCbOrdenTrabajo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbOrdenTrabajo().isSelected()){
					getCbResponsable().setSelected(false);
					getCbCliente().setSelected(false);
				}
			}
		});
		
		getCbResponsable().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbResponsable().isSelected()){
					getCbOrdenTrabajo().setSelected(false);
					getCbCliente().setSelected(false);
				}
			}
		});
		
		getCbCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbCliente().isSelected()){
					getCbOrdenTrabajo().setSelected(false);
					getCbResponsable().setSelected(false);
				}
			}
		});
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				
				//Para setear un responsable en cada tiempo parcial que tenga nulo en ese campo.
				/*try {
					int contador = 0;
					Collection tiempoParcialDotColeccion = SessionServiceLocator.getTiempoParcialDotSessionService().getTiempoParcialDotList();
					Iterator tiempoParcialDotColeccionIt = tiempoParcialDotColeccion.iterator();
					while(tiempoParcialDotColeccionIt.hasNext()){
						TiempoParcialDotIf tiempoParcialDotIf = (TiempoParcialDotIf)tiempoParcialDotColeccionIt.next();
						if(tiempoParcialDotIf.getUsuarioAsignadoId() == null){
							OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(tiempoParcialDotIf.getIdOrdenTrabajoDetalle());
							tiempoParcialDotIf.setUsuarioAsignadoId(ordenTrabajoDetalleIf.getAsignadoaId());
							SessionServiceLocator.getTiempoParcialDotSessionService().saveTiempoParcialDot(tiempoParcialDotIf);
							contador++;
							System.out.println("cambio " + contador);
						}
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}*/
				
				cleanTable();
				cargarTabla();
			}
		});
		
		getCbTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodos().isSelected()){
					clienteOficinaIf = null;
					getTxtCliente().setText("");
				}
			}
		});
		
		getBtnCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tituloVentanaBusqueda = "Oficinas del Cliente";
				clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_CLIENTE, "", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					clienteIf = mapaCliente.get(clienteOficinaIf.getClienteId());
					getTxtCliente().setText(clienteIf.getNombreLegal());
					getCbTodos().setSelected(false);
				}
			}
		});
		
		menuItemAbrirOrden = new JMenuItem("<html><font color=red>Abrir Orden de Trabajo</font></html>");
		menuItemAbrirOrden.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				abrirOrdenTrabajo(ordenTrabajoSeleccionada);
			}
		});
		
		/*menuItemOrdenDetalleRealizada = new JMenuItem("<html><font color=red>Orden Detalle Realizada</font></html>");
		menuItemOrdenDetalleRealizada.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				setearOrdenTrabajoDetalleEstadoRealizada(ordenTrabajoDetalleSeleccionada);
			}
		});
		*/
		popup.add(menuItemAbrirOrden);
		//popup.add(menuItemOrdenDetalleRealizada);

		getTblOrdenes().add(popup);
		getTblOrdenes().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON3)
					if (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE)
						popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
		getTblOrdenes().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				enableSelectedRowForAuthorize(evt);
			}
		});
		
		getTblOrdenes().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				enableSelectedRowForAuthorize(evt);
			}
		});
	}
	
	private void enableSelectedRowForAuthorize(ComponentEvent evt) {
		if (getTblOrdenes().getSelectedRow() != -1) {
			int selectedRow = ((JTable) evt.getSource()).getSelectedRow();
			ordenTrabajoDetalleSeleccionada = ordenTrabajoDetalleList.get(selectedRow);
			ordenTrabajoSeleccionada = mapaOrdenTrabajo.get(ordenTrabajoDetalleSeleccionada.getOrdenId());
		}
	}
	
	private void abrirOrdenTrabajo(OrdenTrabajoIf ordenTrabajo) {
		SpiritModel panelOrdenTrabajo = (SpiritModel) new OrdenTrabajoModel(ordenTrabajo);
		
		if (panels.size()>0 && panels.get("Orden de Trabajo")!=null){
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea cerrar la ventana Orden de Trabajo?, se perderá la información que no haya sido guardada", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				MainFrameModel.get_dockingManager().removeFrame("Orden de Trabajo");
				DockableFrame panel = PanelHandler.createPanelesApp(panelOrdenTrabajo);
				MainFrameModel.get_dockingManager().addFrame(panel);
				MainFrameModel.get_dockingManager().showFrame(panel.getName());	
			}
		}else{
			DockableFrame panel = PanelHandler.createPanelesApp(panelOrdenTrabajo);
			MainFrameModel.get_dockingManager().addFrame(panel);
			MainFrameModel.get_dockingManager().showFrame(panel.getName());	
		}
	}
	
	public void setearOrdenTrabajoDetalleEstadoRealizada(OrdenTrabajoDetalleIf ordenTrabajoDetalle){
		try {
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea cambiar el estado de la Orden Detalle a Realizado?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				ordenTrabajoDetalleSeleccionada.setEstado(ESTADO_REALIZADO);
				
				List<OrdenTrabajoDetalleIf> ordenDetalleColeccion = new ArrayList<OrdenTrabajoDetalleIf>();
				ordenDetalleColeccion = (ArrayList)SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByOrdenId(ordenTrabajoDetalle.getOrdenId());
				
				boolean isEstadoRealizado = true;
				for(OrdenTrabajoDetalleIf ordenTrabajoDetalleIf : ordenDetalleColeccion){
					if(!ordenTrabajoDetalleIf.getEstado().equals(ESTADO_REALIZADO)
							&& (ordenTrabajoDetalleIf.getId().compareTo(ordenTrabajoDetalleSeleccionada.getId()) != 0)){
						isEstadoRealizado = false;
					}
				}
				if(isEstadoRealizado){
					ordenTrabajoSeleccionada.setEstado(ESTADO_REALIZADO);
				}
				
				SessionServiceLocator.getOrdenTrabajoSessionService().actualizarOrdenTrabajo(ordenTrabajoSeleccionada, ordenTrabajoDetalleSeleccionada);
				//SessionServiceLocator.getOrdenTrabajoDetalleSessionService().saveOrdenTrabajoDetalle(ordenTrabajoDetalleSeleccionada);
				//SessionServiceLocator.getOrdenTrabajoSessionService().saveOrdenTrabajo(ordenTrabajoSeleccionada);
				SpiritAlert.createAlert("Estado de Orden Trabajo Detalle se actualizo con éxito", SpiritAlert.INFORMATION);
				cleanTable();
				cargarTabla();
			}			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void clean() {
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		cleanTable();
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblOrdenes().getModel();
		for(int i= this.getTblOrdenes().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	private void cleanTableReporte() {
		DefaultTableModel model = (DefaultTableModel) getTblReporte().getModel();
		for(int i= this.getTblReporte().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public DefaultTableModel generarDefaultTableModelReporte(){
		cleanTableReporte();
		DefaultTableModel tblModelReporte = (DefaultTableModel) getTblReporte().getModel();
		for(int i=0; i<getTblOrdenes().getModel().getRowCount(); i++) {
			tblModelReporte = (DefaultTableModel) getTblReporte().getModel();
			Vector<String> fila = new Vector<String>();
			fila.add(getTblOrdenes().getModel().getValueAt(getTblOrdenes().convertRowIndexToModel(i),0).toString());
			fila.add(getTblOrdenes().getModel().getValueAt(getTblOrdenes().convertRowIndexToModel(i),1).toString());
			fila.add(getTblOrdenes().getModel().getValueAt(getTblOrdenes().convertRowIndexToModel(i),2).toString());
			fila.add(getTblOrdenes().getModel().getValueAt(getTblOrdenes().convertRowIndexToModel(i),3).toString());
			fila.add(getTblOrdenes().getModel().getValueAt(getTblOrdenes().convertRowIndexToModel(i),4).toString());
			fila.add(getTblOrdenes().getModel().getValueAt(getTblOrdenes().convertRowIndexToModel(i),5).toString());
			fila.add(getTblOrdenes().getModel().getValueAt(getTblOrdenes().convertRowIndexToModel(i),6).toString());
			fila.add(getTblOrdenes().getModel().getValueAt(getTblOrdenes().convertRowIndexToModel(i),7).toString());
			fila.add(getTblOrdenes().getModel().getValueAt(getTblOrdenes().convertRowIndexToModel(i),8).toString());
			fila.add(getTblOrdenes().getModel().getValueAt(getTblOrdenes().convertRowIndexToModel(i),9).toString());
			fila.add(getTblOrdenes().getModel().getValueAt(getTblOrdenes().convertRowIndexToModel(i),10).toString());
			tblModelReporte.addRow(fila);
		}
		
		return tblModelReporte;
	}

	public void report() {
		try {				
			if (getTblOrdenes().getModel().getRowCount() > 0) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte del Timetracker?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/medios/RPTimetracker.jasper";
					if(getCbCliente().isSelected()){
						fileName = "jaspers/medios/RPTimetrackerPorCliente.jasper";
					}else if(getCbResponsable().isSelected()){
						fileName = "jaspers/medios/RPTimetrackerPorResponsable.jasper";
					}else{
						fileName = "jaspers/medios/RPTimetracker.jasper";
					}
					
					DefaultTableModel tblModelReporte = generarDefaultTableModelReporte();
					
					HashMap parametrosMap = new HashMap();
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " del " + year;
					parametrosMap.put("usuario", Parametros.getUsuario());
					parametrosMap.put("emitido", fechaEmision);
					
					if(getCbOrdenTrabajo().isSelected()){
						parametrosMap.put("tipoFecha", "Creación");
					}else if(getCbResponsable().isSelected()){
						parametrosMap.put("tipoFecha", "Entrega");
					}else{
						parametrosMap.put("tipoFecha", "");
					}
					
					parametrosMap.put("fechaInicio", Utilitarios.getFechaCortaUppercase(getCmbFechaInicio().getDate()));
					parametrosMap.put("fechaFin", Utilitarios.getFechaCortaUppercase(getCmbFechaFin().getDate()));
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					
					Map<String,String> mapaClienteTiempoFormato = new HashMap<String,String>();
					Iterator mapaClienteTiempoIt = mapaClienteTiempo.keySet().iterator();
					while(mapaClienteTiempoIt.hasNext()){
						String llave = (String)mapaClienteTiempoIt.next();
						Long tiempo = mapaClienteTiempo.get(llave);
						mapaClienteTiempoFormato.put(llave, Utilitarios.getTiempoCompleto(tiempo));
					}
					Map<String,String> mapaResponsableTiempoFormato = new HashMap<String,String>();
					Iterator mapaResponsableTiempoFormatoIt = mapaResponsableTiempo.keySet().iterator();
					while(mapaResponsableTiempoFormatoIt.hasNext()){
						String llave = (String)mapaResponsableTiempoFormatoIt.next();
						Long tiempo = mapaResponsableTiempo.get(llave);
						mapaResponsableTiempoFormato.put(llave, Utilitarios.getTiempoCompleto(tiempo));
					}
					parametrosMap.put("mapaClienteTiempo", mapaClienteTiempoFormato);
					parametrosMap.put("mapaResponsableTiempo", mapaResponsableTiempoFormato);
					
					parametrosMap.put("totalTiempo", Utilitarios.getTiempoCompleto(totalTiempo));
					
					ReportModelImpl.processReport(fileName, parametrosMap, tblModelReporte, true);
				}
			} else{
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
			}
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}
	
	public Collection cargarOrdenTrabajoColeccion(){
		Map aMap = new HashMap();
		
		if(getCmbOficina().getSelectedItem() != null && !getCmbOficina().getSelectedItem().equals(TODOS)){
			aMap.put("oficinaId", ((OficinaIf)getCmbOficina().getSelectedItem()).getId());
		}
		if(getCmbEjecutivo().getSelectedItem() != null && !getCmbEjecutivo().getSelectedItem().equals(TODOS)){
			aMap.put("empleadoId", ((EmpleadoIf)getCmbEjecutivo().getSelectedItem()).getId());
		}
		if(clienteOficinaIf != null){
			aMap.put("clienteoficinaId", clienteOficinaIf.getId());
		}
		
		fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
		fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());

		String ordenporFecha = "";
		if(getCbResponsable().isSelected()){
			ordenporFecha = "fechaentrega";
		}else if(getCbOrdenTrabajo().isSelected()){
			ordenporFecha = "fecha";
		}
		
		if(!getCmbTipoOrden().getSelectedItem().equals(TODOS)){
			TipoOrdenIf tipoOrden = (TipoOrdenIf)getCmbTipoOrden().getSelectedItem();
			idTipoOrden = tipoOrden.getId();
		}else{
			idTipoOrden = 0L;
		}
		
		idEmpleadoResponsable = 0L;
		if(getCmbResponsable().getSelectedItem() != null && !getCmbResponsable().getSelectedItem().equals(TODOS)){
			EmpleadoIf empleado = (EmpleadoIf)getCmbResponsable().getSelectedItem();
			idEmpleadoResponsable = empleado.getId();
		}else{
			idEmpleadoResponsable = 0L;
		}
		
		if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PENDIENTE)){
			estados = estadosPendientes;
		}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_REALIZADO)){
			estados = estadoRealizado;
		}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ENTREGADO)){
			estados = estadoEntregado;
		}else{
			estados = estadosTodos;
		}	
		
		aMap.put("responsable",true);
		ArrayList ordenTrabajoColeccion = (ArrayList)SessionServiceLocator.getOrdenTrabajoSessionService().findOrdenTrabajoByQueryByTipoFechaByFechaInicioByFechaFinByEmpresaIdByTipoOrdenByResponsableAndByEstados(aMap, ordenporFecha, fechaInicio, fechaFin, Parametros.getIdEmpresa(), idTipoOrden, idEmpleadoResponsable, estados);
		if(ordenTrabajoColeccion.isEmpty()){
			//Si no existe ese idResponsable (empleado) en tiempo_parcial_dot_detalle buscamos en orden_trabajo_detalle
			aMap.put("responsable",false);
			ordenTrabajoColeccion = (ArrayList)SessionServiceLocator.getOrdenTrabajoSessionService().findOrdenTrabajoByQueryByTipoFechaByFechaInicioByFechaFinByEmpresaIdByTipoOrdenByResponsableAndByEstados(aMap, ordenporFecha, fechaInicio, fechaFin, Parametros.getIdEmpresa(), idTipoOrden, idEmpleadoResponsable, estados);
		}

		if(getCbCliente().isSelected()){
			Collections.sort((ArrayList)ordenTrabajoColeccion,ordenadorOrdenTrabajoPorClienteOficina);
		}
		
		return ordenTrabajoColeccion;
	}
	
	Comparator<OrdenTrabajoIf> ordenadorOrdenTrabajoPorClienteOficina = new Comparator<OrdenTrabajoIf>(){
		public int compare(OrdenTrabajoIf ot1, OrdenTrabajoIf ot2) {
			ClienteOficinaIf clienteOficina1 = mapaClienteOficina.get(ot1.getClienteoficinaId());
			ClienteOficinaIf clienteOficina2 = mapaClienteOficina.get(ot2.getClienteoficinaId());
			ClienteIf cliente1 = mapaCliente.get(clienteOficina1.getClienteId());
			ClienteIf cliente2 = mapaCliente.get(clienteOficina2.getClienteId());
			
			return cliente1.getNombreLegal().compareTo(cliente2.getNombreLegal());
		}		
	};
	
	Comparator<TiempoParcialDotIf> tiempoParcialDotPorResponsable = new Comparator<TiempoParcialDotIf>(){
		public int compare(TiempoParcialDotIf tpd1, TiempoParcialDotIf tpd2) {
			EmpleadoIf empleadoIf1 = mapaEmpleado.get(tpd1.getUsuarioAsignadoId());
			EmpleadoIf empleadoIf2 = mapaEmpleado.get(tpd2.getUsuarioAsignadoId());
			if(empleadoIf1 == null || empleadoIf2 == null){
				return 0;
			}else{
				return empleadoIf1.getNombres().compareTo(empleadoIf2.getNombres());
			}			
		}		
	};
	
	Comparator<OrdenTrabajoDetalleIf> ordenadorOrdenTrabajoDetallePorFechaCreacion = new Comparator<OrdenTrabajoDetalleIf>(){
		public int compare(OrdenTrabajoDetalleIf otd1, OrdenTrabajoDetalleIf otd2) {
			return otd2.getFecha().compareTo(otd1.getFecha());
		}		
	};
	
	void cargarTabla() {
		try {
			totalTiempo = 0L;
			mapaClienteTiempo = new HashMap<String,Long>();
			mapaResponsableTiempo = new HashMap<String,Long>();
			ordenTrabajoDetalleList = null;
			ordenTrabajoDetalleList = new ArrayList<OrdenTrabajoDetalleIf>();
			Collection ordenTrabajoColeccion = cargarOrdenTrabajoColeccion();
			Iterator ordenTrabajoColeccionIt = ordenTrabajoColeccion.iterator();
			ArrayList<TiempoParcialDotIf> tiempoParcialDotList = new ArrayList<TiempoParcialDotIf>();
			
			if(getCbResponsable().isSelected()){
				while (ordenTrabajoColeccionIt.hasNext()) {
					OrdenTrabajoIf ordenTrabajoIf = (OrdenTrabajoIf) ordenTrabajoColeccionIt.next();
					
					Collection ordenTrabajoDetalleColeccion = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByOrdenId(ordenTrabajoIf.getId());
					Iterator ordenTrabajoDetalleColeccionIt = ordenTrabajoDetalleColeccion.iterator();

					while (ordenTrabajoDetalleColeccionIt.hasNext()) {
						OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) ordenTrabajoDetalleColeccionIt.next();
						
						ArrayList<TiempoParcialDotIf> tiemposParcialDot = (ArrayList<TiempoParcialDotIf>)SessionServiceLocator.getTiempoParcialDotSessionService().findTiempoParcialDotByIdOrdenTrabajoDetalle(ordenTrabajoDetalleIf.getId());
						for(TiempoParcialDotIf tiempoParcialDot : tiemposParcialDot){
							tiempoParcialDotList.add(tiempoParcialDot);
						}						
					}		
				}
				
				Collections.sort((ArrayList)tiempoParcialDotList, tiempoParcialDotPorResponsable);				
				
				Long idResponsable = 0L;				
				for(TiempoParcialDotIf tiempoParcialDotIf : tiempoParcialDotList){
					OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(tiempoParcialDotIf.getIdOrdenTrabajoDetalle());
					SubtipoOrdenIf subtipoOrden = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(ordenTrabajoDetalleIf.getSubtipoId());
					String estadoOtd = ordenTrabajoDetalleIf.getEstado();
					if(((estados == estadosPendientes) && (estadoOtd.equals(ESTADO_PENDIENTE) || estadoOtd.equals(ESTADO_ENCURSO)))
							|| ((estados == estadoRealizado) && estadoOtd.equals(ESTADO_REALIZADO))
							|| ((estados == estadoEntregado) && estadoOtd.equals(ESTADO_ENTREGADO))
							|| (estados == estadosTodos)){
						if((idTipoOrden.compareTo(0L) == 0) || subtipoOrden.getTipoordenId().compareTo(idTipoOrden) == 0){
							
							//para sacar el tiempo total de cada responsable
							if(idResponsable.compareTo(tiempoParcialDotIf.getUsuarioAsignadoId()) == 0){
								EmpleadoIf empleado = mapaEmpleado.get(tiempoParcialDotIf.getUsuarioAsignadoId());
								String nombre = empleado.getNombres().substring(0,empleado.getNombres().split(" ")[0].length()) + " " + empleado.getApellidos().substring(0,empleado.getApellidos().split(" ")[0].length());
								mapaResponsableTiempo.put(nombre, sumaTiempoDotDetalle(tiempoParcialDotIf)+mapaResponsableTiempo.get(nombre));
							}else{
								idResponsable = tiempoParcialDotIf.getUsuarioAsignadoId();
								EmpleadoIf empleado = mapaEmpleado.get(tiempoParcialDotIf.getUsuarioAsignadoId());
								String nombre = empleado.getNombres().substring(0,empleado.getNombres().split(" ")[0].length()) + " " + empleado.getApellidos().substring(0,empleado.getApellidos().split(" ")[0].length());
								mapaResponsableTiempo.put(nombre, sumaTiempoDotDetalle(tiempoParcialDotIf));
							}							
						}					
					}
				}
				Long sumaTiempo = 0L;	
				for(TiempoParcialDotIf tiempoParcialDotIf : tiempoParcialDotList){
					OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(tiempoParcialDotIf.getIdOrdenTrabajoDetalle());
					SubtipoOrdenIf subtipoOrden = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(ordenTrabajoDetalleIf.getSubtipoId());
					String estadoOtd = ordenTrabajoDetalleIf.getEstado();
					if(((estados == estadosPendientes) && (estadoOtd.equals(ESTADO_PENDIENTE) || estadoOtd.equals(ESTADO_ENCURSO)))
							|| ((estados == estadoRealizado) && estadoOtd.equals(ESTADO_REALIZADO))
							|| ((estados == estadoEntregado) && estadoOtd.equals(ESTADO_ENTREGADO))
							|| (estados == estadosTodos)){
						if((idTipoOrden.compareTo(0L) == 0) || subtipoOrden.getTipoordenId().compareTo(idTipoOrden) == 0){
							OrdenTrabajoIf ordenTrabajoIf = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalleIf.getOrdenId());
							
							tableModel = (DefaultTableModel) getTblOrdenes().getModel();
							Vector<String> fila = new Vector<String>();		
							if(idEmpleadoResponsable == 0L || tiempoParcialDotIf.getUsuarioAsignadoId().compareTo(idEmpleadoResponsable) == 0){
								sumaTiempo = sumaTiempoDotDetalle(tiempoParcialDotIf);
								if(sumaTiempo > 0){
									setCambioOrden(getCambioOrden()+1);
									agregarColumnasTabla(ordenTrabajoIf, ordenTrabajoDetalleIf, fila, getCambioOrden(), tiempoParcialDotIf, sumaTiempo);
									tableModel.addRow(fila);
								}								
							}					
						}					
					}
				}
				
				
			}else{
				idClienteOficina = 0L;
				while (ordenTrabajoColeccionIt.hasNext()) {
					
					OrdenTrabajoIf ordenTrabajoIf = (OrdenTrabajoIf) ordenTrabajoColeccionIt.next();
					
					ArrayList<OrdenTrabajoDetalleIf> ordenTrabajoDetalleColeccion = (ArrayList<OrdenTrabajoDetalleIf>)SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByOrdenId(ordenTrabajoIf.getId());
					Iterator ordenTrabajoDetalleColeccionIt = ordenTrabajoDetalleColeccion.iterator();
					
					calcularTiemposCliente(ordenTrabajoIf, ordenTrabajoDetalleColeccion);				
					
					//Presento Información
					while (ordenTrabajoDetalleColeccionIt.hasNext()) {
						OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) ordenTrabajoDetalleColeccionIt.next();
						SubtipoOrdenIf subtipoOrden = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(ordenTrabajoDetalleIf.getSubtipoId());
						String estadoOtd = ordenTrabajoDetalleIf.getEstado();
						if(((estados == estadosPendientes) && (estadoOtd.equals(ESTADO_PENDIENTE) || estadoOtd.equals(ESTADO_ENCURSO)))
								|| ((estados == estadoRealizado) && estadoOtd.equals(ESTADO_REALIZADO))
								|| ((estados == estadoEntregado) && estadoOtd.equals(ESTADO_ENTREGADO))
								|| (estados == estadosTodos)){
							if((idTipoOrden.compareTo(0L) == 0) || subtipoOrden.getTipoordenId().compareTo(idTipoOrden) == 0){
								
								cargarDatosTabla(ordenTrabajoIf, ordenTrabajoDetalleIf);
							}					
						}
					}		
				}
			}
			
			System.out.println("TOTAL TOTAL: " + Utilitarios.getTiempoCompleto(totalTiempo));		

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void calcularTiemposCliente(OrdenTrabajoIf ordenTrabajoIf,
			ArrayList<OrdenTrabajoDetalleIf> ordenTrabajoDetalleColeccion)
			throws GenericBusinessException {
		cambioOrden = 0;
		ordenTiempoTotal = 0L;
		//para sacar el tiempo total de cada orden de trabajo
		for(OrdenTrabajoDetalleIf ordenTrabajoDetalle : ordenTrabajoDetalleColeccion){
			
			SubtipoOrdenIf subtipoOrden = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(ordenTrabajoDetalle.getSubtipoId());
			String estadoOtd = ordenTrabajoDetalle.getEstado();
			if(((estados == estadosPendientes) && (estadoOtd.equals(ESTADO_PENDIENTE) || estadoOtd.equals(ESTADO_ENCURSO)))
					|| ((estados == estadoRealizado) && estadoOtd.equals(ESTADO_REALIZADO))
					|| ((estados == estadoEntregado) && estadoOtd.equals(ESTADO_ENTREGADO))
					|| (estados == estadosTodos)){
				if((idTipoOrden.compareTo(0L) == 0) || subtipoOrden.getTipoordenId().compareTo(idTipoOrden) == 0){
				
					ArrayList<TiempoParcialDotIf> tiemposParcialDot = (ArrayList<TiempoParcialDotIf>)SessionServiceLocator.getTiempoParcialDotSessionService().findTiempoParcialDotByIdOrdenTrabajoDetalle(ordenTrabajoDetalle.getId());
					for(TiempoParcialDotIf tiempoParcialDot : tiemposParcialDot){
						Long sumaTiempo = sumaTiempoDotDetalle(tiempoParcialDot);
						ordenTiempoTotal = ordenTiempoTotal + sumaTiempo;
					}
				}					
			}					
		}
		
		//Calculos los tiempos
		if(this.idClienteOficina.compareTo(ordenTrabajoIf.getClienteoficinaId()) == 0){
			ClienteOficinaIf clienteOficina = mapaClienteOficina.get(ordenTrabajoIf.getClienteoficinaId());
			ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());
			mapaClienteTiempo.put(cliente.getNombreLegal(), ordenTiempoTotal+mapaClienteTiempo.get(cliente.getNombreLegal()));
		}else{
			this.idClienteOficina = ordenTrabajoIf.getClienteoficinaId();
			ClienteOficinaIf clienteOficina = mapaClienteOficina.get(ordenTrabajoIf.getClienteoficinaId());
			ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());
			mapaClienteTiempo.put(cliente.getNombreLegal(), ordenTiempoTotal);
		}
	}

	private Long sumaTiempoDotDetalle(TiempoParcialDotIf tiempoParcialDot)
			throws GenericBusinessException {
		Long sumaTiempo = 0L;
		Collection tiempoParcialDotDetalleList = SessionServiceLocator.getTiempoParcialDotDetalleSessionService().findTiempoParcialDotDetalleByIdTiempoParcialDot(tiempoParcialDot.getId());
		Iterator tiempoParcialDotDetalleListIt = tiempoParcialDotDetalleList.iterator();
		while(tiempoParcialDotDetalleListIt.hasNext()){
			TiempoParcialDotDetalleIf tiempoParcialDotDetalleIf = (TiempoParcialDotDetalleIf)tiempoParcialDotDetalleListIt.next();
			if(tiempoParcialDotDetalleIf.getFecha() >= Utilitarios.resetTimestampStartDate(new Timestamp(fechaInicio.getTime())).getTime()  && tiempoParcialDotDetalleIf.getFecha() <= Utilitarios.resetTimestampEndDate(new Timestamp(fechaFin.getTime())).getTime()){
				sumaTiempo = sumaTiempo + tiempoParcialDotDetalleIf.getTiempo();
			}
		}
		return sumaTiempo;
	}

	private void cargarDatosTabla(OrdenTrabajoIf ordenTrabajoIf, OrdenTrabajoDetalleIf ordenTrabajoDetalleIf) {
		try {
			tableModel = (DefaultTableModel) getTblOrdenes().getModel();
			//ordenTrabajoDetalleList.add(ordenTrabajoDetalleIf);
			
			ArrayList<TiempoParcialDotIf> tiemposParcialDotIf = (ArrayList<TiempoParcialDotIf>)SessionServiceLocator.getTiempoParcialDotSessionService().findTiempoParcialDotByIdOrdenTrabajoDetalle(ordenTrabajoDetalleIf.getId());
			
			//Si hay trabajos asignados en el Timetracker entra a este IF
			if(tiemposParcialDotIf.size() > 0){
				Iterator tiemposParcialDotIfIt = tiemposParcialDotIf.iterator();
				
				while(tiemposParcialDotIfIt.hasNext()){
					TiempoParcialDotIf tiempoParcialDotIf = (TiempoParcialDotIf)tiemposParcialDotIfIt.next();
					
					Vector<String> fila = new Vector<String>();		
					if(idEmpleadoResponsable == 0L || tiempoParcialDotIf.getUsuarioAsignadoId().compareTo(idEmpleadoResponsable) == 0){
						Long sumaTiempo = sumaTiempoDotDetalle(tiempoParcialDotIf);	
						if(sumaTiempo > 0){
							setCambioOrden(getCambioOrden()+1);
							agregarColumnasTabla(ordenTrabajoIf, ordenTrabajoDetalleIf, fila, getCambioOrden(), tiempoParcialDotIf, sumaTiempo);
							tableModel.addRow(fila);
						}						
					}
				}
			//Para que salgan tambien los tiempoDot detalles en cero
			}/*else{
				Vector<String> fila = new Vector<String>();		
				if(idEmpleadoResponsable == 0L || ordenTrabajoDetalleIf.getAsignadoaId().compareTo(idEmpleadoResponsable) == 0){
					setCambioOrden(getCambioOrden()+1);
					agregarColumnasTabla(ordenTrabajoIf, ordenTrabajoDetalleIf, fila, getCambioOrden(), null);
					tableModel.addRow(fila);
				}
			}	*/
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void agregarColumnasTabla(OrdenTrabajoIf ordenTrabajoIf, OrdenTrabajoDetalleIf ordenTrabajoDetalleIf, Vector<String> fila, int cambioOrden, TiempoParcialDotIf tiempoParcialDotIf, Long sumaTiempo) {
		//Se usa este vector para abrir las ordenes de trabajo.
		ordenTrabajoDetalleList.add(ordenTrabajoDetalleIf);
		
		ClienteOficinaIf clienteOficina = mapaClienteOficina.get(ordenTrabajoIf.getClienteoficinaId());
		ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());
		SubtipoOrdenIf subTipo = mapaSubtipoOrden.get(ordenTrabajoDetalleIf.getSubtipoId());
		EmpleadoIf responsable = mapaEmpleado.get(ordenTrabajoDetalleIf.getAsignadoaId());
		EmpleadoIf ejecutivo = mapaEmpleado.get(ordenTrabajoIf.getEmpleadoId());
		
		if(tiempoParcialDotIf != null && tiempoParcialDotIf.getUsuarioAsignadoId() != null){
			responsable = mapaEmpleado.get(tiempoParcialDotIf.getUsuarioAsignadoId());
		}
		
		String codigo = ordenTrabajoIf.getCodigo().split("-")[1];
		
		if(getCbOrdenTrabajo().isSelected() && cambioOrden > 1){
			fila.add("");
			fila.add("");
			fila.add("");
			fila.add("");
		}else{
			fila.add(codigo);
			fila.add(cliente.getNombreLegal());
			fila.add(ordenTrabajoIf.getDescripcion());
			fila.add(ejecutivo.getNombres().substring(0,ejecutivo.getNombres().split(" ")[0].length()) + " " + ejecutivo.getApellidos().substring(0,ejecutivo.getApellidos().split(" ")[0].length()));
		}
		
		fila.add(subTipo.getNombre());
		String nombreResponsable = responsable.getNombres().substring(0,responsable.getNombres().split(" ")[0].length()) + " " + responsable.getApellidos().substring(0,responsable.getApellidos().split(" ")[0].length());
		fila.add(nombreResponsable);
		fila.add(Utilitarios.getFechaCortaUppercase(ordenTrabajoIf.getFecha()));
		fila.add(ordenTrabajoDetalleIf.getFechalimite()!=null?Utilitarios.getFechaCortaUppercase(ordenTrabajoDetalleIf.getFechalimite()):"");
		fila.add(ordenTrabajoDetalleIf.getFechaentrega()!=null?Utilitarios.getFechaCortaUppercase(ordenTrabajoDetalleIf.getFechaentrega()):"");
		if(tiempoParcialDotIf!=null){
			fila.add(Utilitarios.getTiempoCompleto(sumaTiempo));	
			totalTiempo = totalTiempo + sumaTiempo;
		}else{
			fila.add(Utilitarios.getTiempoCompleto(0L));		
		}
		
		if((getCbOrdenTrabajo().isSelected() || getCbCliente().isSelected()) && cambioOrden <= 1){
			fila.add(Utilitarios.getTiempoCompleto(ordenTiempoTotal));
		}else{
			fila.add("");
		}
		
		/*if(cambioOrden > 1){
			fila.add("");
		}else{
			fila.add(Utilitarios.getTiempoCompleto(ordenTiempoTotal));	
		}*/
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public void refresh() {
		cargarMapas();
		cargarComboEjecutivo();
		cargarComboOficina();
	}
	
	public JTable getTblReporte() {
		return tblReporte;
	}
	
	public Integer getCambioOrden(){
		return cambioOrden;
	}
	
	public void setCambioOrden(Integer cambioOrden){
		this.cambioOrden = cambioOrden;
	}	
}