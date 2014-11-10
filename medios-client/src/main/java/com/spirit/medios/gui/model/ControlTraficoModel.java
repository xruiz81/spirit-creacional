package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
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
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.PanelHandler;
import com.spirit.medios.entity.EquipoEmpleadoIf;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.medios.gui.panel.JPControlTrafico;
import com.spirit.util.Utilitarios;

public class ControlTraficoModel extends JPControlTrafico {
	
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
	private static final String CODIGO_TIPO_ORDEN_CUENTAS = "CU";
	private DefaultTableModel tableModel;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	protected ClienteOficinaIf clienteOficinaIf;
	protected ClienteIf clienteIf;
	private Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
	private Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
	private Map<Long,SubtipoOrdenIf> mapaSubtipoOrden = new HashMap<Long,SubtipoOrdenIf>();
	private Map<Long,TipoOrdenIf> mapaTipoOrden = new HashMap<Long,TipoOrdenIf>();
	private Map<Long,EmpleadoIf> mapaEmpleado = new HashMap<Long,EmpleadoIf>();
	private Map<Long,OrdenTrabajoIf> mapaOrdenTrabajo = new HashMap<Long,OrdenTrabajoIf>();
	private Map<Long,EquipoTrabajoIf> mapaEquipoTrabajo = new HashMap<Long,EquipoTrabajoIf>();
	private Map<Long,EquipoEmpleadoIf> mapaEquipoEmpleadoPorEmpleado = new HashMap<Long,EquipoEmpleadoIf>();
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
	private boolean tipoTodos = false;
	
	
	public ControlTraficoModel(){
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
		cargarComboEqCuentas();
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
	
	private void cargarComboEqCuentas(){
		try {
			Collection tipoOrdenCuenta = SessionServiceLocator.getTipoOrdenSessionService()
			.findTipoOrdenByCodigo(CODIGO_TIPO_ORDEN_CUENTAS);
			if(tipoOrdenCuenta.size() > 0){
				TipoOrdenIf tipoOrdenCuentas = (TipoOrdenIf)tipoOrdenCuenta.iterator().next();
				ArrayList equiposTrabajoCuentas = (ArrayList)SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByTipoordenId(tipoOrdenCuentas.getId());
				equiposTrabajoCuentas.add(TODOS);
				refreshCombo(getCmbEqCuentas(),equiposTrabajoCuentas);
				getCmbEqCuentas().setSelectedItem(TODOS);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void cargarComboEstado() {
		getCmbEstado().removeAllItems();
		getCmbEstado().addItem(TODOS);
		getCmbEstado().addItem(NOMBRE_ESTADO_PENDIENTE);		
		getCmbEstado().addItem(NOMBRE_ESTADO_REALIZADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_ENTREGADO);
		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_PENDIENTE);
	}
	
	public void iniciarTablaReporte(){		
		tblReporte.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null, "", null, null, null, null, null, null, ""},
					},
				new String[] {
					"C\u00f3digo", "Cliente", "Trabajo", "SubTipo", "Responsable", "EqCuent.", "Ejecutivo(a)", "F.Creaci\u00f3n", "F.Limite", "F.Entrega"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false
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
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(120);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(135);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(67);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(67);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(67);
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
			
			mapaTipoOrden.clear();
			Collection tipoOrdenes = SessionServiceLocator.getTipoOrdenSessionService().findTipoOrdenByEmpresaId(Parametros.getIdEmpresa());
			Iterator tipoOrdenesIt = tipoOrdenes.iterator();
			while(tipoOrdenesIt.hasNext()){
				TipoOrdenIf tipoOrden = (TipoOrdenIf)tipoOrdenesIt.next();
				mapaTipoOrden.put(tipoOrden.getId(), tipoOrden);
			}
			
			mapaEmpleado.clear();
			Collection empleados = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Iterator empleadosIt = empleados.iterator();
			while(empleadosIt.hasNext()){
				EmpleadoIf empleado = (EmpleadoIf)empleadosIt.next();
				mapaEmpleado.put(empleado.getId(), empleado);
			}
			
			mapaOrdenTrabajo.clear();
			Collection ordenesTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajoList();
			Iterator ordenesTrabajoIt = ordenesTrabajo.iterator();
			while(ordenesTrabajoIt.hasNext()){
				OrdenTrabajoIf ordenTrabajo = (OrdenTrabajoIf)ordenesTrabajoIt.next();
				mapaOrdenTrabajo.put(ordenTrabajo.getId(), ordenTrabajo);
			}
			
			mapaEquipoTrabajo.clear();
			Collection equiposTrabajo = SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajoList();
			Iterator equiposTrabajoIt = equiposTrabajo.iterator();
			while(equiposTrabajoIt.hasNext()){
				EquipoTrabajoIf equipoTrabajo = (EquipoTrabajoIf)equiposTrabajoIt.next();
				mapaEquipoTrabajo.put(equipoTrabajo.getId(), equipoTrabajo);
			}
			
			mapaEquipoEmpleadoPorEmpleado.clear();
			Collection equiposEmpleado = SessionServiceLocator.getEquipoEmpleadoSessionService().getEquipoEmpleadoList();
			Iterator equiposEmpleadoIt = equiposEmpleado.iterator();
			while(equiposEmpleadoIt.hasNext()){
				EquipoEmpleadoIf equipoEmpleado = (EquipoEmpleadoIf)equiposEmpleadoIt.next();
				mapaEquipoEmpleadoPorEmpleado.put(equipoEmpleado.getEmpleadoId(), equipoEmpleado);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	public void initKeyListeners(){
		getLblResponsable().setVisible(false);
		getCmbResponsable().setVisible(false);
		getCbVerDetallesCuentas().setSelected(false);
		getCbOrdenTrabajo().setSelected(true);
		getCbFechaCreacion().setSelected(false);
		getCbFechaEntrega().setSelected(false);
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
	}
	
	private void initListeners() {
		getCmbTipoOrden().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCmbTipoOrden().getSelectedItem().equals(TODOS)){
					getCmbResponsable().setSelectedIndex(-1);
					getCmbResponsable().setEnabled(false);
				}else{
					getCmbResponsable().setEnabled(true);
				}
			}
		});
		
		getCbFechaCreacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbFechaCreacion().isSelected()){
					getCbFechaEntrega().setSelected(false);
					getCbOrdenTrabajo().setSelected(false);
				}
			}
		});
		
		getCbFechaEntrega().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbFechaEntrega().isSelected()){
					getCbFechaCreacion().setSelected(false);
					getCbOrdenTrabajo().setSelected(false);
				}
			}
		});
		
		getCbOrdenTrabajo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbOrdenTrabajo().isSelected()){
					getCbFechaCreacion().setSelected(false);
					getCbFechaEntrega().setSelected(false);
				}
			}
		});
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
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
		
		menuItemOrdenDetalleRealizada = new JMenuItem("<html><font color=red>Orden Detalle Realizada</font></html>");
		menuItemOrdenDetalleRealizada.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				setearOrdenTrabajoDetalleEstadoRealizada(ordenTrabajoDetalleSeleccionada);
			}
		});
		
		popup.add(menuItemAbrirOrden);
		popup.add(menuItemOrdenDetalleRealizada);

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
			tblModelReporte.addRow(fila);
		}
		
		return tblModelReporte;
	}

	public void report() {
		try {				
			if (getTblOrdenes().getModel().getRowCount() > 0) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Control de Tráfico?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/medios/RPControlTrafico.jasper";
					
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
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					
					if(getCbFechaCreacion().isSelected()){
						parametrosMap.put("tipoFecha", "Creación");
					}else{
						parametrosMap.put("tipoFecha", "Entrega");
					}
					parametrosMap.put("fechaInicio", Utilitarios.getFechaCortaUppercase(getCmbFechaInicio().getDate()));
					parametrosMap.put("fechaFin", Utilitarios.getFechaCortaUppercase(getCmbFechaFin().getDate()));
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
		
		Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
		Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());

		String ordenporFecha = "";
		if(getCbFechaEntrega().isSelected()){
			ordenporFecha = "fechaentrega";
		}else if(getCbFechaCreacion().isSelected()){
			ordenporFecha = "fecha";
		}
		
		//Seteo el booleano tipoTodos dependiendo si se desea o no ver todos los tipos de orden
		tipoTodos = false;
		if(!getCmbTipoOrden().getSelectedItem().equals(TODOS)){
			TipoOrdenIf tipoOrden = (TipoOrdenIf)getCmbTipoOrden().getSelectedItem();
			idTipoOrden = tipoOrden.getId();
		}else{
			idTipoOrden = 0L;
			tipoTodos = true;
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
		
		Collection ordenTrabajoColeccion = SessionServiceLocator.getOrdenTrabajoSessionService().findOrdenTrabajoByQueryByTipoFechaByFechaInicioByFechaFinByEmpresaIdByTipoOrdenAndByEstados(aMap, ordenporFecha, fechaInicio, fechaFin, Parametros.getIdEmpresa(), idTipoOrden, tipoTodos, estados);
		
		//Busco id del tipo de orden CUENTAS si se quiere ver todos los tipos
		//y NO esta seleccionado el check box "Ver detalles de cuentas"
		try {
			if(tipoTodos && !getCbVerDetallesCuentas().isSelected()){
				Collection tipoOrdenCuentas = SessionServiceLocator.getTipoOrdenSessionService().findTipoOrdenByNombre("CUENTAS");
				if(tipoOrdenCuentas.size() > 0){
					idTipoOrden = ((TipoOrdenIf)tipoOrdenCuentas.iterator().next()).getId();
				}
			}		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		return ordenTrabajoColeccion;
	}
	
	Comparator<OrdenTrabajoDetalleIf> ordenadorOrdenTrabajoDetallePorFechaCreacion = new Comparator<OrdenTrabajoDetalleIf>(){
		public int compare(OrdenTrabajoDetalleIf otd1, OrdenTrabajoDetalleIf otd2) {
			return otd2.getFecha().compareTo(otd1.getFecha());
		}		
	};
	
	Comparator<OrdenTrabajoDetalleIf> ordenadorOrdenTrabajoDetallePorFechaEntrega = new Comparator<OrdenTrabajoDetalleIf>(){
		public int compare(OrdenTrabajoDetalleIf otd1, OrdenTrabajoDetalleIf otd2) {
			if(otd2.getFechaentrega() == null && otd1.getFechaentrega() == null){
				return otd2.getFechalimite().compareTo(otd1.getFechalimite());
			}else if(otd2.getFechaentrega() == null){
				return otd2.getFechalimite().compareTo(otd1.getFechaentrega());
			}else if(otd1.getFechaentrega() == null){
				return otd2.getFechaentrega().compareTo(otd1.getFechalimite());
			}else{
				return otd2.getFechaentrega().compareTo(otd1.getFechaentrega());
			}
		}		
	};
	
	private void cargarTabla() {
		try {
			ordenTrabajoDetalleList = null;
			ordenTrabajoDetalleList = new ArrayList<OrdenTrabajoDetalleIf>();
			Collection ordenTrabajoColeccion = cargarOrdenTrabajoColeccion();
			Iterator ordenTrabajoColeccionIt = ordenTrabajoColeccion.iterator();
			ArrayList<OrdenTrabajoDetalleIf> ordenTrabajoDetalleIfList = new ArrayList<OrdenTrabajoDetalleIf>();

			//Si el orden es por orden de trabajo entonces puedo usar la coleccion de ordenes, sino debo crear el arreglo de todos los detalles de ordenes
			if(getCbOrdenTrabajo().isSelected()){
				while (ordenTrabajoColeccionIt.hasNext()) {
					OrdenTrabajoIf ordenTrabajoIf = (OrdenTrabajoIf) ordenTrabajoColeccionIt.next();
					
					Collection ordenTrabajoDetalleColeccion = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByOrdenId(ordenTrabajoIf.getId());
					Iterator ordenTrabajoDetalleColeccionIt = ordenTrabajoDetalleColeccion.iterator();

					while (ordenTrabajoDetalleColeccionIt.hasNext()) {
						OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) ordenTrabajoDetalleColeccionIt.next();
						
						SubtipoOrdenIf subtipoOrden = mapaSubtipoOrden.get(ordenTrabajoDetalleIf.getSubtipoId());
						String estadoOtd = ordenTrabajoDetalleIf.getEstado();
						if(((estados == estadosPendientes) && (estadoOtd.equals(ESTADO_PENDIENTE) || estadoOtd.equals(ESTADO_ENCURSO)))
								|| ((estados == estadoRealizado) && estadoOtd.equals(ESTADO_REALIZADO))
								|| ((estados == estadoEntregado) && estadoOtd.equals(ESTADO_ENTREGADO))
								|| (estados == estadosTodos)){
							
							//Se carga el registro en cualquiera de los tres casos:
							//1. Esta seleccionado un tipo de Orden especifico
							//2. Esta seleccionado todos los tipos de orden y de desea ver los registros de cuentas
							//3. ESta seleccionado todos los tipos de orden y no se desea ver los registros de cuentas
							if((!tipoTodos && subtipoOrden.getTipoordenId().compareTo(idTipoOrden) == 0) 
									|| idTipoOrden.compareTo(0L) == 0 || 
									(tipoTodos && subtipoOrden.getTipoordenId().compareTo(idTipoOrden) != 0)){
								cargarDatosTabla(ordenTrabajoIf, ordenTrabajoDetalleIf);
							}
						}
					}		
				}
			}else{
				while (ordenTrabajoColeccionIt.hasNext()) {
					OrdenTrabajoIf ordenTrabajoIf = (OrdenTrabajoIf) ordenTrabajoColeccionIt.next();
					
					Collection ordenTrabajoDetalleColeccion = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByOrdenId(ordenTrabajoIf.getId());
					Iterator ordenTrabajoDetalleColeccionIt = ordenTrabajoDetalleColeccion.iterator();

					while (ordenTrabajoDetalleColeccionIt.hasNext()) {
						OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) ordenTrabajoDetalleColeccionIt.next();
						
						ordenTrabajoDetalleIfList.add(ordenTrabajoDetalleIf);
					}		
				}
				
				if(getCbFechaEntrega().isSelected()){
					Collections.sort((ArrayList)ordenTrabajoDetalleIfList,ordenadorOrdenTrabajoDetallePorFechaEntrega);
				}else if(getCbFechaCreacion().isSelected()){
					Collections.sort((ArrayList)ordenTrabajoDetalleIfList,ordenadorOrdenTrabajoDetallePorFechaCreacion);
				}
				
				for(OrdenTrabajoDetalleIf ordenTrabajoDetalleIf : ordenTrabajoDetalleIfList){
					SubtipoOrdenIf subtipoOrden = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(ordenTrabajoDetalleIf.getSubtipoId());
					String estadoOtd = ordenTrabajoDetalleIf.getEstado();
					if(((estados == estadosPendientes) && (estadoOtd.equals(ESTADO_PENDIENTE) || estadoOtd.equals(ESTADO_ENCURSO)))
							|| ((estados == estadoRealizado) && estadoOtd.equals(ESTADO_REALIZADO))
							|| ((estados == estadoEntregado) && estadoOtd.equals(ESTADO_ENTREGADO))
							|| (estados == estadosTodos)){
						
						//Se carga el registro en cualquiera de los tres casos:
						//1. Esta seleccionado un tipo de Orden especifico
						//2. Esta seleccionado todos los tipos de orden y de desea ver los registros de cuentas
						//3. ESta seleccionado todos los tipos de orden y no se desea ver los registros de cuentas
						if((!tipoTodos && subtipoOrden.getTipoordenId().compareTo(idTipoOrden) == 0) 
								|| idTipoOrden.compareTo(0L) == 0 || 
								(tipoTodos && subtipoOrden.getTipoordenId().compareTo(idTipoOrden) != 0)){
							OrdenTrabajoIf ordenTrabajoIf = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalleIf.getOrdenId());
							cargarDatosTabla(ordenTrabajoIf, ordenTrabajoDetalleIf);
						}
						
						/*if((idTipoOrden.compareTo(0L) == 0) || subtipoOrden.getTipoordenId().compareTo(idTipoOrden) == 0){
							OrdenTrabajoIf ordenTrabajoIf = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalleIf.getOrdenId());
							cargarDatosTabla(ordenTrabajoIf, ordenTrabajoDetalleIf);
						}*/					
					}
				}
			}			

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void cargarDatosTabla(OrdenTrabajoIf ordenTrabajoIf, OrdenTrabajoDetalleIf ordenTrabajoDetalleIf) {
		tableModel = (DefaultTableModel) getTblOrdenes().getModel();
		ordenTrabajoDetalleList.add(ordenTrabajoDetalleIf);
		Vector<String> fila = new Vector<String>();
		if(agregarColumnasTabla(ordenTrabajoIf, ordenTrabajoDetalleIf, fila))
			tableModel.addRow(fila);
	}

	private boolean agregarColumnasTabla(OrdenTrabajoIf ordenTrabajoIf, OrdenTrabajoDetalleIf ordenTrabajoDetalleIf, Vector<String> fila) {
		ClienteOficinaIf clienteOficina = mapaClienteOficina.get(ordenTrabajoIf.getClienteoficinaId());
		ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());
		SubtipoOrdenIf subTipo = mapaSubtipoOrden.get(ordenTrabajoDetalleIf.getSubtipoId());
		EmpleadoIf responsable = mapaEmpleado.get(ordenTrabajoDetalleIf.getAsignadoaId());
		EmpleadoIf ejecutivo = mapaEmpleado.get(ordenTrabajoIf.getEmpleadoId());
		EquipoEmpleadoIf equipoEjecutivo = mapaEquipoEmpleadoPorEmpleado.get(ejecutivo.getId());
		EquipoTrabajoIf equipoCuentas = mapaEquipoTrabajo.get(equipoEjecutivo.getEquipoId());
		
		//Si se selecciono un equipo de cuentas especifico entonces devuelve false
		//para que no se agrege detalles de otro equipo de cuentas
		if(!getCmbEqCuentas().getSelectedItem().equals(TODOS)){
			EquipoTrabajoIf equipoCuentasSeleccionado = (EquipoTrabajoIf)getCmbEqCuentas().getSelectedItem();
				if(equipoCuentasSeleccionado.getId().compareTo(equipoCuentas.getId()) != 0){
					return false;
				}
		}
		
		fila.add(ordenTrabajoIf.getCodigo());
		fila.add(cliente.getNombreLegal());
		fila.add(ordenTrabajoIf.getDescripcion());
		fila.add(subTipo.getNombre());
		fila.add(responsable.getNombres().substring(0,responsable.getNombres().split(" ")[0].length()) + " " + responsable.getApellidos().substring(0,responsable.getApellidos().split(" ")[0].length()));
		fila.add(equipoCuentas.getCodigo());
		fila.add(ejecutivo.getNombres().substring(0,ejecutivo.getNombres().split(" ")[0].length()) + " " + ejecutivo.getApellidos().substring(0,ejecutivo.getApellidos().split(" ")[0].length()));
		fila.add(ordenTrabajoDetalleIf.getFecha()!=null?Utilitarios.getFechaCortaUppercase(ordenTrabajoDetalleIf.getFecha()):"");
		fila.add(ordenTrabajoDetalleIf.getFechalimite()!=null?Utilitarios.getFechaCortaUppercase(ordenTrabajoDetalleIf.getFechalimite()):"");
		fila.add(ordenTrabajoDetalleIf.getFechaentrega()!=null?Utilitarios.getFechaCortaUppercase(ordenTrabajoDetalleIf.getFechaentrega()):"");
		
		return true;
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
	
}
