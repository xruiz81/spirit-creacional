package com.spirit.medios.gui.model;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.EquipoEmpleadoIf;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.entity.OrdenTrabajoData;
import com.spirit.medios.entity.OrdenTrabajoDetalleData;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.OrdenTrabajoProductoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.medios.gui.criteria.OrdenTrabajoCriteria;
import com.spirit.medios.gui.panel.JPOrdenTrabajo;
import com.spirit.medios.handler.EstadoOrdenTrabajo;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Archivos;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.DeepCopy;
import com.spirit.util.ExtensionFileFilter;
import com.spirit.util.FileInputStreamSerializable;
import com.spirit.util.LabelAccessory;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class OrdenTrabajoModel extends JPOrdenTrabajo {
	
	private static final long serialVersionUID = 2749925286638925373L;
	private JDPopupFinderModel popupFinder;
	private CorporacionCriteria corporacionCriteria;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private ClienteCriteria clienteCriteria;
	private OrdenTrabajoIf ordentrabajoIf;
	private OrdenTrabajoDetalleIf ordenTrabajoDetalleIf;
	ArrayList lista;
	DefaultTableModel modelOrden, modelOrdenDetalle;
	Vector<OrdenTrabajoDetalleIf> ordenDetalleColeccion = new Vector<OrdenTrabajoDetalleIf>();
	final JPopupMenu popupMenuTipoOrdenDetalle = new JPopupMenu();
	final JPopupMenu popupMenuTipoOrdenDetalleSave = new JPopupMenu();
	final JPopupMenu popupMenuProductoCliente = new JPopupMenu();
	JMenuItem itemEliminarTipoOrdenDetalle;
	JMenuItem itemEliminarTipoOrdenDetalleSave;
	private CorporacionIf corporacionIf;
	private TipoOrdenIf tipo;
	private ClienteIf clienteIf;
	private ClienteOficinaIf clienteOficinaIf;
	private ProductoClienteIf productoClienteIf;
	private java.util.Date fechaCreacion = new java.util.Date();
	private static Date fechaLimite;
	private static Date fechaLimiteDetalle;
	private File archivoOrden;
	private Vector<File> archivosDescripcionColeccion = new Vector<File>();
	private Vector<File> archivosPropuestaColeccion = new Vector<File>();
	private Vector<OrdenTrabajoDetalleIf> ordenDetalleEliminadas = new Vector<OrdenTrabajoDetalleIf>();
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no}; 
	
	private static final int MAX_LONGITUD_OBSERVACION = 255;
	private static final int MAX_LONGITUD_DESCRIPCION_ORDEN = 200;
	private static final int MAX_LONGITUD_DESCRIPCION_ORDEN_DETALLE = 3500;
	private static final int MAX_LONGITUD_URL_PROPUESTA_ORDEN = 100;
	private static final int MAX_LONGITUD_URL_PROPUESTA_ORDEN_DETALLE = 100;
	private static final int MAX_LONGITUD_URL_DESCRIPCION_ORDEN_DETALLE = 100;
	
	private static final String ESTADO_ACTIVO = "A";
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private Vector<OrdenTrabajoDetalleReporteData> ordenTrabajoDetalleColeccion = new Vector<OrdenTrabajoDetalleReporteData>();
	private List<ProductoClienteIf> productoClienteList = new ArrayList<ProductoClienteIf>();
	private static final String CODIGO_SUBTIPO_ORDEN_CUENTAS = "CU";
	private static final String SI_CREAR_ORDENES_TRABAJO_AUTOMATICAS = "S";
	private static final String CODIGO_CREAR_ORDENES_TRABAJO_AUTOMATICAS = "CREAROTCUENTAS";
	private DefaultListModel modelProductoCliente = new DefaultListModel();
	
	
	public OrdenTrabajoModel() {
		initKeyListeners();
		cargarCombos();
		showSaveMode();
		initPopup();		
		getTblOrdenTrabajoDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarListenersComponents();
		new HotKeyComponent(this);
	}
	
	public OrdenTrabajoModel(OrdenTrabajoIf ordenTrabajo) {
		initKeyListeners();
		cargarCombos();
		showSaveMode();
		initPopup();		
		getTblOrdenTrabajoDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarListenersComponents();
		new HotKeyComponent(this);
		ordentrabajoIf = ordenTrabajo;
		getSelectedObject(true);
	}
	
	private void initKeyListeners() {
		getBtnAgregarDetalle().setText("");
		getBtnActualizarDetalle().setText("");
		getBtnEliminarDetalle().setText("");				
		getBtnBuscarCorporacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCorporacion().setToolTipText("Buscar Corporación");		
		getBtnBuscarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCliente().setToolTipText("Buscar Cliente");		
		getBtnBuscarClienteOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarClienteOficina().setToolTipText("Buscar Oficina del Cliente");		
		getBtnAgregarArchivoPropuestaOrden().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/attachFile.png"));
		getBtnAgregarArchivoPropuestaOrden().setToolTipText("Agregar Archivo de Propuesta");		
		getBtnVerArchivoPropuestaOrden().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnVerArchivoPropuestaOrden().setToolTipText("Ver Archivo");				
		getBtnAgregarArchivoDescripcion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/attachFile.png"));
		getBtnAgregarArchivoDescripcion().setToolTipText("Agregar Archivo de Descripción");		
		getBtnAgregarArchivoPropuestaOrdenDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/attachFile.png"));
		getBtnAgregarArchivoPropuestaOrdenDetalle().setToolTipText("Agregar Archivo de Propuesta");		
		getBtnAgregarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarDetalle().setToolTipText("Agregar Detalle a la Orden");		
		getBtnActualizarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarDetalle().setToolTipText("Actualizar Detalle");		
		getBtnEliminarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarDetalle().setToolTipText("Eliminar Detalle");
		getBtnLimpiarArchivoDescripcion().setToolTipText("Limpiar archivo");
		getBtnLimpiarArchivoDescripcion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/cancelar.png"));
		getBtnLimpiarArchivoPropuesta().setToolTipText("Limpiar archivo");
		getBtnLimpiarArchivoPropuesta().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/cancelar.png"));
		
		getTxtDescripcionOrden().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION_ORDEN));
		getTxtObservacion().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACION, true, 0));
		getTxtDescripcionOrdenDetalle().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION_ORDEN_DETALLE, true, 0));
		getTxtUrlDescripcionOrdenDetalle().addKeyListener(new TextChecker(MAX_LONGITUD_URL_DESCRIPCION_ORDEN_DETALLE));
		getTxtUrlPropuestaOrden().addKeyListener(new TextChecker(MAX_LONGITUD_URL_PROPUESTA_ORDEN));
		getTxtUrlPropuestaOrdenDetalle().addKeyListener(new TextChecker(MAX_LONGITUD_URL_PROPUESTA_ORDEN_DETALLE));
		getCmbFechaLimiteOrden().setLocale(Utilitarios.esLocale);
		getCmbFechaLimiteOrdenDetalle().setLocale(Utilitarios.esLocale);
		getCmbFechaLimiteOrden().setShowNoneButton(false);
		getCmbFechaLimiteOrdenDetalle().setShowNoneButton(false);
		getCmbFechaLimiteOrden().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaLimiteOrdenDetalle().setFormat(Utilitarios.setFechaUppercase());
		
		getTxtCliente().setEditable(false);
		getTxtCorporacion().setEditable(false);
		getTxtOficina().setEditable(false);
		getCmbFechaEntregaOrden().setLocale(Utilitarios.esLocale);
		getCmbFechaEntregaOrden().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaEntregaOrden().setEditable(false);
		getCmbFechaEntregaOrdenDetalle().setLocale(Utilitarios.esLocale);
		getCmbFechaEntregaOrdenDetalle().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaEntregaOrdenDetalle().setEditable(false);
	}
	
	private void initPopup() {
		itemEliminarTipoOrdenDetalle = new JMenuItem("Eliminar");
		itemEliminarTipoOrdenDetalle.addActionListener(oActionListenerEliminarOrdenDetalle);
		popupMenuTipoOrdenDetalle.add(itemEliminarTipoOrdenDetalle);
	}
	
	public void copy(File src, File dst) throws IOException { 
		InputStream in = new FileInputStream(src); 
		OutputStream out = new FileOutputStream(dst); 
		
		byte[] buf = new byte[1024]; 
		int len; 
		while ((len = in.read(buf)) > 0) { 
			out.write(buf, 0, len); 
		} 
		in.close(); 
		out.close(); 
	}
	
	public void crearListaProductos(){
		productoClienteList = null;
		productoClienteList = new ArrayList<ProductoClienteIf>();
		int[] selected = getCbListProductos().getCheckBoxListSelectedIndices();
		for(int i=0; i<selected.length; i++){
			if(selected[i] < getCbListProductos().getModel().getSize()){
				productoClienteList.add((ProductoClienteIf)getCbListProductos().getModel().getElementAt(selected[i]));
			}
		}
	}
	
	public void save() {
		crearListaProductos();
		if (validateFields()) {
			try {
				OrdenTrabajoIf ordenTrabajo = registrarOrdenTrabajo();
				FileInputStreamSerializable fileInputArchivo = null;
				
				if ( archivoOrden != null )
					fileInputArchivo = new FileInputStreamSerializable(archivoOrden);
				
				ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenTrabajo.getClienteoficinaId());
				ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
				String slashUrl = Parametros.getUrlCarpetaServidor().substring(Parametros.getUrlCarpetaServidor().length()-1,Parametros.getUrlCarpetaServidor().length());
				String slashRuta = Parametros.getRutaWindowsCarpetaServidor().substring(Parametros.getRutaWindowsCarpetaServidor().length()-1,Parametros.getRutaWindowsCarpetaServidor().length());
								
				//Para el Timetracker se crea ordenes detalle automaticas de las ejecutivas
				crearOrdenesDetalleAutomaticas(ordenTrabajo);				
				
				OrdenTrabajoIf ordenTrabajoGuardada = SessionServiceLocator.getOrdenTrabajoSessionService().procesarOrdenTrabajo(ordenTrabajo, ordenDetalleColeccion, productoClienteList, Parametros.getIdEmpresa(), fileInputArchivo, Parametros.getUrlCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashUrl, Parametros.getRutaWindowsCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashRuta+slashRuta, (archivoOrden!=null?archivoOrden.getName():""));
				
				Collection<FileInputStreamSerializable> fileDescripcionCollection = new Vector<FileInputStreamSerializable>();
				Collection<FileInputStreamSerializable> filePropuestaCollection = new Vector<FileInputStreamSerializable>();
				
				for ( File archivo: archivosDescripcionColeccion ){
					if (archivo!=null){
						fileDescripcionCollection.add( new FileInputStreamSerializable(archivo,archivo.getName()) );
					} else
						fileDescripcionCollection.add(null);
				}
				
				for ( File archivo: archivosPropuestaColeccion ){
					if (archivo!=null){
						filePropuestaCollection.add( new FileInputStreamSerializable(archivo,archivo.getName()) );
					} else
						filePropuestaCollection.add(null);
				}
				
				SessionServiceLocator.getOrdenTrabajoSessionService().actualizarOrdenTrabajoDetalle(
					ordenTrabajoGuardada,ordenDetalleColeccion,ordenDetalleEliminadas,fileDescripcionCollection,
					filePropuestaCollection,Parametros.getUrlCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashUrl,
					Parametros.getRutaWindowsCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashRuta+slashRuta,
					false);
				
				SpiritAlert.createAlert("Orden de Trabajo guardada con éxito", SpiritAlert.INFORMATION);
				report(ordenTrabajoGuardada);	
				

				ordenDetalleColeccion.clear();
				productoClienteList.clear();
				showSaveMode();
				
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof GenericBusinessException)
					SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
				else
					SpiritAlert.createAlert("Ocurrió un error al guardar la Orden de Trabajo", SpiritAlert.ERROR);
			}
		}
	}
	
	private void crearOrdenesDetalleAutomaticas(OrdenTrabajoIf ordenTrabajo) {		
		try {		
			//Si esta activa la opcion en Parametro de Empresa:
			//Le agrego a la Orden de Trabajo dos detalles más para el Ejecutivo y Director de Cuentas
			ArrayList parametrosEmpresaTimetracker = (ArrayList)SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByCodigo(CODIGO_CREAR_ORDENES_TRABAJO_AUTOMATICAS);
			
			if(parametrosEmpresaTimetracker.size() > 0){
				
				ParametroEmpresaIf parametroTimetrackerOrdenes = (ParametroEmpresaIf)parametrosEmpresaTimetracker.iterator().next();
									
				if(parametroTimetrackerOrdenes.getValor().equals(SI_CREAR_ORDENES_TRABAJO_AUTOMATICAS)){
					
					//Busco el director del equipo
					HashMap buscarDirectorMap = new HashMap();
					buscarDirectorMap.put("equipoId", ordenTrabajo.getEquipoId());
					buscarDirectorMap.put("rol", "DCU"); //ROL DIRECTOR DE CUENTAS
					Collection directorEquipos = SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByQuery(buscarDirectorMap);
					
					if(directorEquipos.size() > 0){
						EquipoEmpleadoIf directorEquipo = (EquipoEmpleadoIf)directorEquipos.iterator().next();
						EmpleadoIf director = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(directorEquipo.getEmpleadoId());
						EquipoTrabajoIf equipoTrabajoDirector = SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajo(directorEquipo.getEquipoId());
						
						//creo orden detalle para el director de cuentas si no ha sido asignada en una orden detalle.
						boolean crearDetalleDirector = true;
						for(OrdenTrabajoDetalleIf ordenTrabajoDetalle : ordenDetalleColeccion){
							if(ordenTrabajoDetalle.getAsignadoaId().compareTo(director.getId()) == 0){
								crearDetalleDirector = false;
							}
						}
						
						ArrayList subTipoOrdenCuentas = (ArrayList) SessionServiceLocator.getSubtipoOrdenSessionService().findSubtipoOrdenByCodigo(CODIGO_SUBTIPO_ORDEN_CUENTAS);
						
						if(crearDetalleDirector && subTipoOrdenCuentas.size() > 0){	
							OrdenTrabajoDetalleData dataDirector = new OrdenTrabajoDetalleData();														
							SubtipoOrdenIf subTipo = (SubtipoOrdenIf)subTipoOrdenCuentas.iterator().next();
							dataDirector.setSubtipoId(subTipo.getId());
							dataDirector.setEquipoId(ordenTrabajo.getEquipoId());
							dataDirector.setAsignadoaId(director.getId());
							dataDirector.setFechalimite(Utilitarios.fromTimestampToSqlDate(ordenTrabajo.getFechalimite()));
							dataDirector.setFechaentrega(Utilitarios.fromTimestampToSqlDate(ordenTrabajo.getFechaentrega()));
							archivosDescripcionColeccion.add(null);
							archivosPropuestaColeccion.add(null);
							dataDirector.setDescripcion(ordenTrabajo.getDescripcion());
							dataDirector.setEstado(EstadoOrdenTrabajo.PENDIENTE.getLetra());
								
							Calendar fechaHora = new GregorianCalendar();
							Timestamp fechaHoraReporte = new Timestamp(fechaHora.getTimeInMillis());
							dataDirector.setFecha(fechaHoraReporte);
							
							ordenDetalleColeccion.add(dataDirector);
						}else{
							SpiritAlert.createAlert("No existe el Sub-Tipo Orden Cuentas", SpiritAlert.WARNING);
						}
						
						//creo orden detalle automatica para el ejecutivo de cuentas si no ha sido asignada en una orden detalle.
						boolean crearDetalleEjecutiva = true;
						for(OrdenTrabajoDetalleIf ordenTrabajoDetalle : ordenDetalleColeccion){
							if(ordenTrabajoDetalle.getAsignadoaId().compareTo(ordenTrabajo.getEmpleadoId()) == 0){
								crearDetalleEjecutiva = false;
							}
						}
						if(crearDetalleEjecutiva){
							//OrdenTrabajoDetalleData dataEjecutivo = (OrdenTrabajoDetalleData)DeepCopy.copy(dataDirector);
							OrdenTrabajoDetalleData dataEjecutivo = new OrdenTrabajoDetalleData();
							
							SubtipoOrdenIf subTipo = (SubtipoOrdenIf)subTipoOrdenCuentas.iterator().next();
							dataEjecutivo.setSubtipoId(subTipo.getId());
							dataEjecutivo.setEquipoId(ordenTrabajo.getEquipoId());
							dataEjecutivo.setAsignadoaId(ordenTrabajo.getEmpleadoId());
							dataEjecutivo.setFechalimite(Utilitarios.fromTimestampToSqlDate(ordenTrabajo.getFechalimite()));
							dataEjecutivo.setFechaentrega(Utilitarios.fromTimestampToSqlDate(ordenTrabajo.getFechaentrega()));
							dataEjecutivo.setDescripcion(ordenTrabajo.getDescripcion());
							dataEjecutivo.setEstado(EstadoOrdenTrabajo.PENDIENTE.getLetra());
								
							Calendar fechaHora = new GregorianCalendar();
							Timestamp fechaHoraReporte = new Timestamp(fechaHora.getTimeInMillis());
							dataEjecutivo.setFecha(fechaHoraReporte);
															
							archivosDescripcionColeccion.add(null);
							archivosPropuestaColeccion.add(null);
							ordenDetalleColeccion.add(dataEjecutivo);
						}
						
					}else{
						SpiritAlert.createAlert("Debe existir un director de cuenta para crear orden detalle automática.", SpiritAlert.WARNING);
					}
				}					
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarEstadoRealizado(OrdenTrabajoIf ordenTrabajo){
		boolean isEstadoRealizado = true;
		for(OrdenTrabajoDetalleIf ordenTrabajoDetalleIf : ordenDetalleColeccion){
			if(!ordenTrabajoDetalleIf.getEstado().equals(EstadoOrdenTrabajo.REALIZADO.getLetra())){
				isEstadoRealizado = false;
			}
			/*if(!ordenTrabajoDetalleIf.getEstado().equals(ESTADO_REALIZADO)){
				isEstadoRealizado = false;
			}*/
		}
		if(isEstadoRealizado){
			//ordenTrabajo.setEstado(ESTADO_REALIZADO);
			ordenTrabajo.setEstado(EstadoOrdenTrabajo.REALIZADO.getLetra());
		}
	}
	
	public void update() {
		crearListaProductos();
		if (validateFields()) {
			try {
				OrdenTrabajoIf ordenTrabajo = registrarOrdenTrabajoForUpdate();
				//actualizarEstadoRealizado(ordenTrabajo);
				FileInputStreamSerializable fileInputArchivo = null;
				
				if ( archivoOrden!=null )
					fileInputArchivo = new FileInputStreamSerializable(archivoOrden);
				
				ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenTrabajo.getClienteoficinaId());
				ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
				String slashUrl = Parametros.getUrlCarpetaServidor().substring(Parametros.getUrlCarpetaServidor().length()-1,Parametros.getUrlCarpetaServidor().length());
				String slashRuta = Parametros.getRutaWindowsCarpetaServidor().substring(Parametros.getRutaWindowsCarpetaServidor().length()-1,Parametros.getRutaWindowsCarpetaServidor().length());
				
				OrdenTrabajoIf ordenTrabajoGuardada = SessionServiceLocator.getOrdenTrabajoSessionService().actualizarOrdenTrabajo(ordenTrabajo, productoClienteList, fileInputArchivo, Parametros.getRutaWindowsCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashRuta+slashRuta,(archivoOrden!=null?archivoOrden.getName():""));
				//SE PREPARA LA COLECCION DE ARCHIVOS A SER GUARDADOS
				Collection<FileInputStreamSerializable> fileDescripcionCollection = new Vector<FileInputStreamSerializable>();
				Collection<FileInputStreamSerializable> filePropuestaCollection = new Vector<FileInputStreamSerializable>();
				
				for ( File archivo: archivosDescripcionColeccion ){
					if (archivo!=null){
						fileDescripcionCollection.add( new FileInputStreamSerializable(archivo,archivo.getName()) );
					} else
						fileDescripcionCollection.add(null);
				}
				
				for ( File archivo: archivosPropuestaColeccion ){
					if (archivo!=null){
						filePropuestaCollection.add( new FileInputStreamSerializable(archivo,archivo.getName()) );
					} else
						filePropuestaCollection.add(null);
				}
				
				SessionServiceLocator.getOrdenTrabajoSessionService().actualizarOrdenTrabajoDetalle(
					ordenTrabajoGuardada,ordenDetalleColeccion,ordenDetalleEliminadas, fileDescripcionCollection,
					filePropuestaCollection,Parametros.getUrlCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashUrl,
					Parametros.getRutaWindowsCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashRuta+slashRuta,
					true);
				
				revisarEstadoOrdenTrabajo(ordenTrabajoGuardada);
								
				SpiritAlert.createAlert("Orden de Trabajo actualizada con éxito", SpiritAlert.INFORMATION);
				report(ordenTrabajoGuardada);
			
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof GenericBusinessException)
					SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
				else
					SpiritAlert.createAlert("Error al actualizar la Orden de Trabajo!", SpiritAlert.ERROR);
			}
			showSaveMode();
		}
	}
	
	public void revisarEstadoOrdenTrabajo(OrdenTrabajoIf ordenTrabajo){
		try {
			ArrayList<OrdenTrabajoDetalleIf> ordenTrabajoDetalleColeccion = (ArrayList<OrdenTrabajoDetalleIf>)SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByOrdenId(ordenTrabajo.getId());
			boolean isEstadoRealizado = false;
			boolean isEstadoPendiente = false;
			boolean isEstadoEnCurso = false;
			for(OrdenTrabajoDetalleIf ordenTrabajoDetalleIf : ordenTrabajoDetalleColeccion){
				if(ordenTrabajoDetalleIf.getEstado().equals(EstadoOrdenTrabajo.REALIZADO.getLetra())){
					isEstadoRealizado = true;
				}else if(ordenTrabajoDetalleIf.getEstado().equals(EstadoOrdenTrabajo.PENDIENTE.getLetra())){
					isEstadoPendiente = true;
				}else if(ordenTrabajoDetalleIf.getEstado().equals(EstadoOrdenTrabajo.EN_CURSO.getLetra())){
					isEstadoEnCurso = true;
				}
			}
			
			//El orden de los IF es muy importante, si por lo menos hay una en curso entonces la cabecera debe estar en curso,
			//si no hay en curso y por lo menos hay una pendiente entonces la cabecera esta en pendiente, y si no hay en curso ni
			//pendiente y hay por lo menos una realizada entonces la cabera debe estar realizada.
			if(isEstadoEnCurso){
				ordenTrabajo.setEstado(EstadoOrdenTrabajo.EN_CURSO.getLetra());
			}else if(isEstadoPendiente){
				ordenTrabajo.setEstado(EstadoOrdenTrabajo.PENDIENTE.getLetra());
			}else if(isEstadoRealizado){
				ordenTrabajo.setEstado(EstadoOrdenTrabajo.REALIZADO.getLetra());
			}
			
			//Actualizo el estado el estado de la orden
			SessionServiceLocator.getOrdenTrabajoSessionService().saveOrdenTrabajo(ordenTrabajo);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	public void delete() {
		//if ((ordentrabajoIf.getEstado().equals(ESTADO_EN_CURSO)))
		if ((ordentrabajoIf.getEstado().equals(EstadoOrdenTrabajo.EN_CURSO)))
			SpiritAlert.createAlert("La Orden de Trabajo está en curso y no puede ser eliminada!", SpiritAlert.WARNING);
		else {
			try {
				SessionServiceLocator.getOrdenTrabajoSessionService().eliminarOrdenTrabajo(ordentrabajoIf.getId());
				SpiritAlert.createAlert("Orden de Trabajo eliminada con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				if (e instanceof GenericBusinessException)
					SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
				else
					SpiritAlert.createAlert("Error al eliminar la Orden de Trabajo!", SpiritAlert.ERROR);
			}
		}
	}
	
	public void report() {
		if(getMode() == SpiritMode.UPDATE_MODE){
			report(ordentrabajoIf);
		}
	}
	
	public void report(OrdenTrabajoIf ordenTrabajoIf) {
		if (!ordenTrabajoDetalleColeccion.isEmpty() && !productoClienteList.isEmpty()) {
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de la Orden de Trabajo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				try {
					String fileName = "jaspers/medios/RPOrdenTrabajo.jasper";
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("ORDEN DE TRABAJO").iterator().next();
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", "RUC: " + empresa.getRuc());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("usuario", Parametros.getUsuario());
					parametrosMap.put("numeroOrden", "Orden de Trabajo No. " + ordenTrabajoIf.getCodigo());
					
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					String ciudadNombre = ciudad.getNombre().substring(0,1).concat(ciudad.getNombre().substring(1, ciudad.getNombre().length()).toLowerCase());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)).toLowerCase() + " del " + year;
					parametrosMap.put("lugarFechaEmision", ciudadNombre + ", " + fechaEmision);		
					
					parametrosMap.put("cliente", clienteIf.getNombreLegal());
					parametrosMap.put("descripcion", getTxtDescripcionOrden().getText());
					parametrosMap.put("observacion", getTxtObservacion().getText());
					parametrosMap.put("fechaEntrega", getCmbFechaEntregaOrden().getDate()!=null?Utilitarios.getFechaCortaUppercase(getCmbFechaEntregaOrden().getDate()):"N/A");
					parametrosMap.put("fechaLimite", getCmbFechaLimiteOrden().getDate()!=null?Utilitarios.getFechaCortaUppercase(getCmbFechaLimiteOrden().getDate()):"N/A");
					
					String estadoLetra = ordenTrabajoIf.getEstado();
					EstadoOrdenTrabajo estado = EstadoOrdenTrabajo.getEstadoOrdenTrabajoByLetra(estadoLetra);
					parametrosMap.put("estado", estado.toString());
					
					/*if(ordenTrabajoIf.getEstado().equals(ESTADO_PENDIENTE)){
						parametrosMap.put("estado", NOMBRE_ESTADO_PENDIENTE);
					}else if(ordenTrabajoIf.getEstado().equals(ESTADO_EN_CURSO)){
						parametrosMap.put("estado", NOMBRE_ESTADO_EN_CURSO);
					}else if(ordenTrabajoIf.getEstado().equals(ESTADO_REALIZADO)){
						parametrosMap.put("estado", NOMBRE_ESTADO_REALIZADO);
					}else if(ordenTrabajoIf.getEstado().equals(ESTADO_CANCELADO)){
						parametrosMap.put("estado", NOMBRE_ESTADO_CANCELADO);
					}else if(ordenTrabajoIf.getEstado().equals(ESTADO_SUSPENDIDO)){
						parametrosMap.put("estado", NOMBRE_ESTADO_SUSPENDIDO);
					}*/				
					
					String productos = "";
					String marcas = "";
					String marcaRepedita = "";
					for(ProductoClienteIf productoClienteIf : productoClienteList){
						productos = productos + productoClienteIf.getNombre() + ", ";
						if(!marcas.contains(productoClienteIf.getMarcaProductoNombre())){
							marcas = marcas + productoClienteIf.getMarcaProductoNombre() + ", ";
						}
						/*if(!marcaRepedita.equals(productoClienteIf.getMarcaProductoNombre())){
							marcas = marcas + productoClienteIf.getMarcaProductoNombre() + ", ";
							marcaRepedita = productoClienteIf.getMarcaProductoNombre();
						}*/					
					}
					productos = productos.substring(0,productos.length()-2);
					marcas = marcas.substring(0,marcas.length()-2);
					parametrosMap.put("productos", productos);
					parametrosMap.put("marca", marcas);
					
					ReportModelImpl.processReport(fileName, parametrosMap, ordenTrabajoDetalleColeccion, true);
					
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				} catch (ParseException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		}else if(productoClienteList.isEmpty()){
			SpiritAlert.createAlert("No se puede generar el reporte, la Orden es Genérica.", SpiritAlert.WARNING);
		}
	}
	
	public void refresh() {
		if ( getCmbCampana().isEnabled() )
			cargarComboCampana();
		
		if ( getCmbAsignadoAOrden().isEnabled() )
			cargarComboAsignado(0L);
		
		cargarComboTipoOrden();
		
		if (getCmbTipo().getModel().getSelectedItem()!= null)
			cargarComboSubTipoOrden(tipo);
		
		if (getCmbTipo().getModel().getSelectedItem()!= null)
			cargarComboEquipo(tipo);
		
		cargarListaProductos();
	}
	
	public void duplicate() {
		try {
			if (getMode() == SpiritMode.UPDATE_MODE){
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea hacer una copia de la Orden de Trabajo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					OrdenTrabajoIf ordenTrabajoDuplicado = (OrdenTrabajoIf)DeepCopy.copy(ordentrabajoIf);
					
					java.util.Date fechaCreacion = new java.util.Date();
					String codigo = getCodigoOrdenTrabajo(new java.sql.Date(fechaCreacion.getTime()));
					ordenTrabajoDuplicado.setId(null);
					ordenTrabajoDuplicado.setCodigo(codigo);
					//ordenTrabajoDuplicado.setEstado(ESTADO_PENDIENTE);
					ordenTrabajoDuplicado.setEstado(EstadoOrdenTrabajo.PENDIENTE.getLetra());
					ordenTrabajoDuplicado.setFecha(new java.sql.Timestamp(fechaCreacion.getTime()));
					//ordenTrabajoDuplicado.setFechalimite(new java.sql.Date(fechaCreacion.getTime()));
					//ordenTrabajoDuplicado.setFechaentrega(new java.sql.Date(fechaCreacion.getTime()));
					
					for(int i=0; i < ordenDetalleColeccion.size(); i++){
						ordenDetalleColeccion.get(i).setId(null);
						ordenDetalleColeccion.get(i).setOrdenId(null);
						//ordenDetalleColeccion.get(i).setEstado(ESTADO_PENDIENTE);
						ordenDetalleColeccion.get(i).setEstado(EstadoOrdenTrabajo.PENDIENTE.getLetra());
						//ordenDetalleColeccion.get(i).setFechaentrega(new java.sql.Date(fechaCreacion.getTime()));
						//ordenDetalleColeccion.get(i).setFechalimite(new java.sql.Date(fechaCreacion.getTime()));
					}
					
					FileInputStreamSerializable fileInputArchivo = null;
					if (getTxtUrlPropuestaOrden().getText()!=null && !getTxtUrlPropuestaOrden().getText().equals("")){
						archivoOrden = new File(getTxtUrlPropuestaOrden().getText());
						fileInputArchivo = new FileInputStreamSerializable(archivoOrden);
					}
					ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenTrabajoDuplicado.getClienteoficinaId());
					ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
					String slashUrl = Parametros.getUrlCarpetaServidor().substring(Parametros.getUrlCarpetaServidor().length()-1,Parametros.getUrlCarpetaServidor().length());
					String slashRuta = Parametros.getRutaWindowsCarpetaServidor().substring(Parametros.getRutaWindowsCarpetaServidor().length()-1,Parametros.getRutaWindowsCarpetaServidor().length());
										
					OrdenTrabajoIf ordenTrabajoGuardada = SessionServiceLocator.getOrdenTrabajoSessionService().procesarOrdenTrabajo(ordenTrabajoDuplicado, ordenDetalleColeccion, productoClienteList, Parametros.getIdEmpresa(), fileInputArchivo, Parametros.getUrlCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashUrl,Parametros.getRutaWindowsCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashRuta+slashRuta, (archivoOrden!=null?archivoOrden.getName():""));
					
					Collection<FileInputStreamSerializable> fileDescripcionCollection = new Vector<FileInputStreamSerializable>();
					Collection<FileInputStreamSerializable> filePropuestaCollection = new Vector<FileInputStreamSerializable>();
					
					for ( File archivo: archivosDescripcionColeccion ){
						if (archivo!=null){
							fileDescripcionCollection.add( new FileInputStreamSerializable(archivo,archivo.getName()) );
						} else
							fileDescripcionCollection.add(null);
					}
					
					for ( File archivo: archivosPropuestaColeccion ){
						if (archivo!=null){
							filePropuestaCollection.add( new FileInputStreamSerializable(archivo,archivo.getName()) );
						} else
							filePropuestaCollection.add(null);
					}
					
					SessionServiceLocator.getOrdenTrabajoSessionService().actualizarOrdenTrabajoDetalle(
						ordenTrabajoGuardada,ordenDetalleColeccion,ordenDetalleEliminadas,fileDescripcionCollection,
						filePropuestaCollection,Parametros.getUrlCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashUrl,
						Parametros.getRutaWindowsCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashRuta+slashRuta,
						false);
									
					SpiritAlert.createAlert("Orden de Trabajo copiada con éxito!, el código es: " + ordenTrabajoGuardada.getCodigo(), SpiritAlert.INFORMATION);
					ordenDetalleColeccion.clear();
					productoClienteList.clear();
					showSaveMode();			
				}	
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpOrdenTrabajo().getSelectedIndex();
		int tabCount = this.getJtpOrdenTrabajo().getTabCount();
		
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpOrdenTrabajo().setSelectedIndex(selectedTab);
	}
	
	public void addDetail() {
		if (this.getJtpOrdenTrabajo().getSelectedIndex() == 2)
			agregarDetalleOrdenTrabajo();
	}
	
	public void updateDetail() {
		if (this.getJtpOrdenTrabajo().getSelectedIndex() == 2)
			actualizarDetalleOrdenTrabajo();
	}
	
	public void deleteDetail() {
		if (this.getJtpOrdenTrabajo().getSelectedIndex() == 2)
			eliminarDetalleOrdenTrabajo();
	}
	
	private OrdenTrabajoIf registrarOrdenTrabajo() {
		OrdenTrabajoData data = new OrdenTrabajoData();
		String codigo = getCodigoOrdenTrabajo(new java.sql.Date(fechaCreacion.getTime()));
		data.setCodigo(codigo);
		data.setDescripcion(this.getTxtDescripcionOrden().getText().toUpperCase());
		data.setOficinaId(Parametros.getIdOficina());
		data.setClienteoficinaId(clienteOficinaIf.getId());
		data.setCampanaId(((CampanaIf) this.getCmbCampana().getSelectedItem()).getId());
		data.setEmpleadoId(((EmpleadoIf) this.getCmbAsignadoAOrden().getSelectedItem()).getId());
		data.setFecha(new java.sql.Timestamp(fechaCreacion.getTime()));
		data.setFechalimite(new java.sql.Timestamp(getCmbFechaLimiteOrden().getDate().getTime()));
		if (getCmbFechaEntregaOrden().getDate() != null)
			data.setFechaentrega(new java.sql.Timestamp(getCmbFechaEntregaOrden().getDate().getTime()));
		
		if (getTxtUrlPropuestaOrden().getText()!=null && !getTxtUrlPropuestaOrden().getText().equals("")){
			data.setUrlPropuesta(this.getTxtUrlPropuestaOrden().getText());
			archivoOrden = new File(getTxtUrlPropuestaOrden().getText()); 
		}			
		
		if (this.getCmbEstadoOrden().getSelectedItem() != null)
			data.setEstado(this.getCmbEstadoOrden().getSelectedItem().toString().substring(0, 1));
		
		data.setObservacion(this.getTxtObservacion().getText());
		data.setUsuarioCreacionId(((UsuarioIf)Parametros.getUsuarioIf()).getId());
		data.setFechaCreacion(new java.sql.Timestamp((new Date()).getTime()));
				
		try {
			//para setear el equipo del ejecutivo
			String equipoDirector = (String)getCmbDirector().getSelectedItem();
			String codigoEquipo = equipoDirector.split(" - ")[0];		
			EquipoTrabajoIf equipoSeleccionado = (EquipoTrabajoIf)SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByCodigo(codigoEquipo).iterator().next();
			data.setEquipoId(equipoSeleccionado.getId());
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
	
	private OrdenTrabajoIf registrarOrdenTrabajoForUpdate() {
		ordentrabajoIf.setDescripcion(this.getTxtDescripcionOrden().getText().toUpperCase());
		ordentrabajoIf.setOficinaId(Parametros.getIdOficina());
		ordentrabajoIf.setClienteoficinaId(clienteOficinaIf.getId());
		ordentrabajoIf.setObservacion(this.getTxtObservacion().getText());
		ordentrabajoIf.setCampanaId(((CampanaIf) this.getCmbCampana().getSelectedItem()).getId());
		ordentrabajoIf.setEmpleadoId(((EmpleadoIf) this.getCmbAsignadoAOrden().getSelectedItem()).getId());
		ordentrabajoIf.setFechalimite(new java.sql.Timestamp(getCmbFechaLimiteOrden().getDate().getTime()));
		if (getCmbFechaEntregaOrden().getDate() != null)
			ordentrabajoIf.setFechaentrega(new java.sql.Timestamp(getCmbFechaEntregaOrden().getDate().getTime()));
		else
			ordentrabajoIf.setFechaentrega(null);
		
		if (getTxtUrlPropuestaOrden().getText()!=null	&& !getTxtUrlPropuestaOrden().getText().equals("")){
			ordentrabajoIf.setUrlPropuesta(this.getTxtUrlPropuestaOrden().getText());
			archivoOrden = new File(getTxtUrlPropuestaOrden().getText());
		}else
			archivoOrden = null;		
		
		ordentrabajoIf.setEstado(this.getCmbEstadoOrden().getSelectedItem().toString().substring(0, 1));
		ordentrabajoIf.setObservacion(this.getTxtObservacion().getText());
		ordentrabajoIf.setUsuarioActualizacionId(((UsuarioIf)Parametros.getUsuarioIf()).getId());
		ordentrabajoIf.setFechaActualizacion(new java.sql.Timestamp((new Date()).getTime()));
				
		try {
			//para actualizar el equipo del ejecutivo
			String equipoDirector = (String)getCmbDirector().getSelectedItem();
			String codigoEquipo = equipoDirector.split(" - ")[0];		
			EquipoTrabajoIf equipoSeleccionado = (EquipoTrabajoIf)SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByCodigo(codigoEquipo).iterator().next();
			ordentrabajoIf.setEquipoId(equipoSeleccionado.getId());
			
			//reviso si existe una factura creada a partir de esta orden y chequeo si se debe actualizar el vendedor y el equipo id.
			/*Collection presupuestos = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByOrdenTrabajoId(ordentrabajoIf.getId());
			Iterator presupuestosIt = presupuestos.iterator();
			while(presupuestosIt.hasNext()){
				PresupuestoIf presupuesto = (PresupuestoIf)presupuestosIt.next();
				HashMap pedidoMap = new HashMap();
				pedidoMap.put("tiporeferencia", "P");
				pedidoMap.put("referencia", presupuesto.getCodigo());
				Collection pedidos = SessionServiceLocator.getPedidoSessionService().findPedidoByQuery(pedidoMap);
				Iterator pedidosIt = pedidos.iterator();
				while(pedidosIt.hasNext()){
					PedidoIf pedido = (PedidoIf)pedidosIt.next();
					Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId());
					Iterator facturasIt = facturas.iterator();
					while(facturasIt.hasNext()){
						FacturaIf factura = (FacturaIf)facturasIt.next();
						
					}
				}
			}*/			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
		
			
		return ordentrabajoIf;
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if ("".equals(getTxtCodigo().getText()) == false)
			aMap.put("codigo", getTxtCodigo().getText());
		else
			aMap.put("codigo", "%");
		
		if ("".equals(getTxtDescripcionOrden().getText()) == false)
			aMap.put("descripcion", "%" + getTxtDescripcionOrden().getText() + "%");
		else
			aMap.put("descripcion", "%");
		
		if ((getTxtCliente().getText() != null) && !getTxtCliente().getText().equals("") && clienteOficinaIf != null)
			aMap.put("clienteoficinaId", clienteOficinaIf.getId());
		
		EstadoOrdenTrabajo estado = (EstadoOrdenTrabajo) getCmbEstadoOrden().getSelectedItem(); 
		if(estado != null){
			aMap.put("estado", estado.getLetra());
		}		
		
		if (getCmbFechaLimiteOrden().getSelectedItem() != null)
			aMap.put("fechalimite", new java.sql.Date(getCmbFechaLimiteOrden().getDate().getTime()));
		
		return aMap;
	}
	
	public void find() {
		try {
			Long clienteId = null;
			if ( clienteIf != null )
				clienteId = clienteIf.getId();
			Map mapa = buildQuery();
			int tamanoLista = 0;
			
			if ( clienteId != null )
				tamanoLista = SessionServiceLocator.getOrdenTrabajoSessionService().findOrdenTrabajoByQueryAndByClienteIdSize(mapa, clienteId, Parametros.getIdEmpresa());
			else
				tamanoLista = SessionServiceLocator.getOrdenTrabajoSessionService().findOrdenTrabajoByQuerySize(mapa);
			
			if (tamanoLista > 0) {
				OrdenTrabajoCriteria ordenTrabajoCriteria = new OrdenTrabajoCriteria(clienteId);
				ordenTrabajoCriteria.setResultListSize(tamanoLista);
				ordenTrabajoCriteria.setQueryBuilded(mapa);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(500);	
				anchoColumnas.add(80);	
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	ordenTrabajoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null )
					getSelectedObject(false);
			} else
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		}
	}
	
	public boolean isEmpty() {
		if ("".equals(this.getTxtObservacion().getText())
				&& "".equals(this.getTxtFechaCreacion().getText())
				&& "".equals(this.getTxtCliente().getText())
				&& "".equals(this.getTxtOficina().getText())
				&& "".equals(this.getTxtDescripcionOrden().getText())
				&& "".equals(this.getTxtDescripcionOrdenDetalle().getText())
				&& "".equals(this.getTxtUrlDescripcionOrdenDetalle().getText())
				&& "".equals(this.getTxtUrlPropuestaOrden().getText())
				&& "".equals(this.getTxtUrlPropuestaOrdenDetalle().getText())
				&& (this.getCmbCampana().getSelectedItem() == null)
				&& (this.getCmbEstadoOrden().getSelectedItem() == null)
				&& (this.getCmbAsignadoAOrden().getSelectedItem() == null)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validateFields() {
		Date fechaLimite = getCmbFechaLimiteOrden().getDate();
		Date fechaEntrega = getCmbFechaEntregaOrden().getDate();
		
		/*if ((getMode() == SpiritMode.UPDATE_MODE) && ordentrabajoIf.getEstado().equals(ESTADO_REALIZADO)) {
			SpiritAlert.createAlert("La Orden de Trabajo se encuentra en estado REALIZADO, no puede ser actualizada !", SpiritAlert.WARNING);
			getJtpOrdenTrabajo().setSelectedIndex(0);
			this.getCmbEstadoOrden().grabFocus();
			return false;
		}*/
		
		if ("".equals(getTxtDescripcionOrden().getText()) || getTxtDescripcionOrden().getText() == null) {
			SpiritAlert.createAlert("Debe ingresar una descripción para la Orden de Trabajo !", SpiritAlert.WARNING);
			getJtpOrdenTrabajo().setSelectedIndex(0);
			getTxtDescripcionOrden().grabFocus();
			return false;
		}
		
		if(fechaEntrega != null){
			if (fechaEntrega.before(fechaLimite)) {
				SpiritAlert.createAlert("La Fecha de entrega no puede estar antes de la Fecha límite !", SpiritAlert.WARNING);
				getJtpOrdenTrabajo().setSelectedIndex(0);
				getCmbFechaEntregaOrden().grabFocus();
				return false;
			}
		}			
		
		if ("".equals(getTxtCorporacion().getText()) || getTxtCorporacion().getText() == null) {
			SpiritAlert.createAlert("Debe seleccionar una corporación para la Orden de Trabajo !", SpiritAlert.WARNING);
			getJtpOrdenTrabajo().setSelectedIndex(0);
			getBtnBuscarCorporacion().grabFocus();
			return false;
		}
		
		if ("".equals(getTxtCliente().getText()) || getTxtCliente().getText() == null) {
			SpiritAlert.createAlert("Debe seleccionar un cliente para la Orden de Trabajo !", SpiritAlert.WARNING);
			getJtpOrdenTrabajo().setSelectedIndex(0);
			getBtnBuscarCliente().grabFocus();
			return false;
		}
		
		if ("".equals(getTxtOficina().getText()) || getTxtOficina().getText() == null) {
			SpiritAlert.createAlert("Debe seleccionar una Oficina para la Orden de Trabajo !", SpiritAlert.WARNING);
			getJtpOrdenTrabajo().setSelectedIndex(0);
			getBtnBuscarClienteOficina().grabFocus();
			return false;
		}
		
		if (getCmbCampana().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar una campaña para la Orden de Trabajo !", SpiritAlert.WARNING);
			getJtpOrdenTrabajo().setSelectedIndex(0);
			getCmbCampana().grabFocus();
			return false;
		}
		
		if (getCmbAsignadoAOrden().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el empleado asignado a la Orden de Trabajo !", SpiritAlert.WARNING);
			getJtpOrdenTrabajo().setSelectedIndex(0);
			getCmbAsignadoAOrden().grabFocus();
			return false;
		}
		
		if (getCmbDirector().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el director del ejecutivo !", SpiritAlert.WARNING);
			getJtpOrdenTrabajo().setSelectedIndex(0);
			getCmbDirector().grabFocus();
			return false;
		}
		
		if (getCmbEstadoOrdenDetalle().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un estado para la Orden de Trabajo !", SpiritAlert.WARNING);
			getJtpOrdenTrabajo().setSelectedIndex(2);
			getCmbEstadoOrdenDetalle().grabFocus();
			return false;
		}
		
		if (productoClienteList.size() == 0) {
			SpiritAlert.createAlert("Debe ingresar al menos un Producto para la Orden !!", SpiritAlert.WARNING);
			getJtpOrdenTrabajo().setSelectedIndex(1);
			getCbListProductos().grabFocus();
			return false;
		}
					
		if (ordenDetalleColeccion.size() == 0) {
			SpiritAlert.createAlert("Debe ingresar por lo menos un Detalle a la Orden de Trabajo !", SpiritAlert.WARNING);
			getJtpOrdenTrabajo().setSelectedIndex(2);
			getCmbTipo().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public boolean validateFieldsDetalle() {
		try {
			Date fechaHoy = Utilitarios.dateHoy();
			Date fechaLimiteDetalle = getCmbFechaLimiteOrdenDetalle().getDate();
			Date fechaEntregaDetalle = getCmbFechaEntregaOrdenDetalle().getDate();
			
			if (getCmbTipo().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar el tipo del Detalle !", SpiritAlert.WARNING);
				getCmbTipo().grabFocus();
				return false;
			}
			
			if (getCmbEquipo().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar un equipo para el Detalle !", SpiritAlert.WARNING);
				getCmbEquipo().grabFocus();
				return false;
			}
			
			if (getCmbAsignadoAOrdenDetalle().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar el miembro del equipo asignado al Detalle !", SpiritAlert.WARNING);
				getCmbAsignadoAOrdenDetalle().grabFocus();
				return false;
			}
			
			if (getCmbSubTipo().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar el subtipo del Detalle !", SpiritAlert.WARNING);
				getCmbSubTipo().grabFocus();
				return false;
			}
			
			if (getCmbEstadoOrdenDetalle().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar el estado del Detalle !", SpiritAlert.WARNING);
				getCmbEstadoOrdenDetalle().grabFocus();
				return false;
			}
			
			if((fechaEntregaDetalle != null) && fechaEntregaDetalle.before(fechaLimiteDetalle)){
				SpiritAlert.createAlert("La Fecha entrega del Detalle no puede estar antes de la Fecha límite del Detalle !", SpiritAlert.WARNING);
				getCmbFechaEntregaOrdenDetalle().grabFocus();
				return false;
			}			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void clean() {
		getTtJefe().setVisible(false);
		corporacionIf = null;
		clienteIf = null;
		getModelProductoCliente().removeAllElements();
		DefaultTableModel model = (DefaultTableModel) this.getTblOrdenTrabajoDetalle().getModel();
		
		for (int i = this.getTblOrdenTrabajoDetalle().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
		
		cleanListaProductos();
		
		//Coleccion para el reporte
		ordenTrabajoDetalleColeccion = null;
		ordenTrabajoDetalleColeccion = new Vector<OrdenTrabajoDetalleReporteData>();
		
		ordenDetalleColeccion = new Vector<OrdenTrabajoDetalleIf>();
		productoClienteList = new ArrayList<ProductoClienteIf>();
		archivoOrden = null;
		archivosDescripcionColeccion= new Vector<File>();
		archivosPropuestaColeccion= new Vector<File>();
		ordenDetalleEliminadas = new Vector<OrdenTrabajoDetalleIf>();
		this.getTxtCodigo().setText("");
		this.getTxtFechaCreacion().setText("");
		this.getTxtObservacion().setText("");
		this.getTxtCliente().setText("");
		this.getTxtCorporacion().setText("");
		this.getTxtDescripcionOrden().setText("");
		this.getTxtDescripcionOrdenDetalle().setText("");
		this.getTxtFechaCreacion().setText("");
		this.getTxtOficina().setText("");
		this.getTxtUrlDescripcionOrdenDetalle().setText("");
		this.getTxtUrlPropuestaOrden().setText("");
		this.getTxtUrlPropuestaOrdenDetalle().setText("");
		this.getCmbAsignadoAOrdenDetalle().setSelectedItem(null);
		this.getCmbCampana().setSelectedItem(null);
		this.getCmbEquipo().setSelectedItem(null);
		this.getCmbAsignadoAOrden().setSelectedItem(null);
		this.getCmbDirector().setSelectedItem(null);
		this.getCmbSubTipo().setSelectedItem("");
		this.getCmbTipo().setSelectedItem("");
		this.getCmbEstadoOrden().setSelectedItem(null);
		this.getCmbEstadoOrdenDetalle().setSelectedItem(null);
		getCmbFechaEntregaOrden().setSelectedItem(null);
		getCmbFechaEntregaOrdenDetalle().setSelectedItem(null);
		Calendar calendarInicio = new GregorianCalendar();
		this.getCmbFechaLimiteOrden().setCalendar(calendarInicio);
		this.getCmbFechaLimiteOrdenDetalle().setCalendar(calendarInicio);		
		this.repaint();
	}
	
	public void showFindMode() {
		getCmbTipo().setSelectedIndex(-1);
		this.getTxtCodigo().setBackground(Parametros.findColor);
		this.getTxtDescripcionOrden().setBackground(Parametros.findColor);
		this.getTxtOficina().setBackground(Parametros.findColor);
		this.getCmbFechaLimiteOrden().setBackground(Parametros.findColor);
		this.getCmbEstadoOrden().setBackground(Parametros.findColor);
		getBtnVerArchivoPropuestaOrden().setEnabled(false);
		this.getTxtCodigo().setEditable(true);
		this.getTxtFechaCreacion().setEnabled(false);
		this.getTxtObservacion().setEnabled(false);
		this.getTxtDescripcionOrden().setEnabled(true);
		this.getTxtDescripcionOrdenDetalle().setEnabled(false);
		this.getCmbCampana().setEnabled(false);
		this.getCmbEquipo().setEnabled(false);
		this.getCmbFechaLimiteOrden().setEnabled(true);
		this.getCmbFechaLimiteOrden().setSelectedItem(null);
		this.getCmbFechaEntregaOrden().setEnabled(false);
		this.getCmbFechaLimiteOrdenDetalle().setEnabled(false);
		this.getCmbAsignadoAOrden().setEnabled(false);
		this.getCmbDirector().setEnabled(false);
		this.getCmbAsignadoAOrdenDetalle().setEnabled(false);
		this.getCmbSubTipo().setEnabled(false);
		this.getCmbTipo().setEnabled(false);
		this.getCmbEstadoOrden().setEnabled(true);
		this.getCmbEstadoOrdenDetalle().setEnabled(false);
		this.getBtnBuscarCorporacion().setEnabled(true);
		this.getBtnBuscarCliente().setEnabled(true);
		//cargarCombos();
		this.getCmbEstadoOrden().setSelectedItem(null);
		this.getJtpOrdenTrabajo().setSelectedIndex(0);
		this.getTxtCodigo().grabFocus();
	}
	
	public void showSaveMode() {
		setSaveMode();
		clean();
		getCmbTipo().setSelectedIndex(-1);
		this.getTxtCodigo().setBackground(getBackground());
		this.getTxtDescripcionOrden().setBackground(Parametros.saveUpdateColor);
		this.getTxtOficina().setBackground(getBackground());
		this.getCmbFechaLimiteOrden().setBackground(Parametros.saveUpdateColor);
		this.getCmbEstadoOrden().setBackground(Parametros.saveUpdateColor);
		getBtnVerArchivoPropuestaOrden().setEnabled(false);
		fechaLimite = fechaCreacion;
		fechaLimiteDetalle = fechaCreacion;
		
		if(getCmbEstadoOrden().getItemCount() >0)
			getCmbEstadoOrden().setSelectedItem(EstadoOrdenTrabajo.PENDIENTE);
			//getCmbEstadoOrden().setSelectedItem(NOMBRE_ESTADO_PENDIENTE);
		getCmbEstadoOrden().repaint();
		
		if(getCmbEstadoOrdenDetalle().getItemCount() >0)
			getCmbEstadoOrdenDetalle().setSelectedItem(EstadoOrdenTrabajo.PENDIENTE);
			//getCmbEstadoOrdenDetalle().setSelectedItem(NOMBRE_ESTADO_PENDIENTE);
		getCmbEstadoOrdenDetalle().repaint();
		
		getTxtCodigo().setEditable(false);
		getTxtFechaCreacion().setText(Utilitarios.fechaAhora());
		getTxtFechaCreacion().setEditable(false);
		getTxtObservacion().setEnabled(true);
		getBtnAgregarDetalle().setEnabled(true);
		getTxtDescripcionOrden().setEnabled(true);
		getTxtUrlDescripcionOrdenDetalle().setEnabled(true);
		getTxtUrlDescripcionOrdenDetalle().setEditable(false);
		getTxtUrlPropuestaOrden().setEnabled(true);
		getTxtUrlPropuestaOrden().setEditable(false);
		getTxtUrlPropuestaOrdenDetalle().setEnabled(true);
		getTxtUrlPropuestaOrdenDetalle().setEditable(false);
		getTxtDescripcionOrdenDetalle().setEnabled(true);
		getCmbFechaLimiteOrden().setEnabled(false);
		getCmbFechaLimiteOrden().setEditable(false);
		getCmbFechaEntregaOrden().setEnabled(false);
		getCmbFechaLimiteOrdenDetalle().setEnabled(true);
		getCmbEquipo().setEnabled(false);
		getCmbCampana().setEnabled(false);
		getCmbAsignadoAOrden().setEnabled(false);
		getCmbDirector().setEnabled(false);
		getCmbAsignadoAOrdenDetalle().setEnabled(false);
		getCmbSubTipo().setEnabled(false);
		getBtnAgregarArchivoDescripcion().setEnabled(true);
		getBtnAgregarArchivoPropuestaOrden().setEnabled(true);
		getBtnAgregarArchivoPropuestaOrdenDetalle().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getBtnBuscarClienteOficina().setEnabled(true);
		getBtnBuscarCorporacion().setEnabled(true);
		getCmbEstadoOrden().setEnabled(true);
		getCmbEstadoOrdenDetalle().setEnabled(true);
		getCmbTipo().setEnabled(true);
		getJtpOrdenTrabajo().setSelectedIndex(0);
		getTxtDescripcionOrden().grabFocus();
	}
	
	public void showUpdateMode() {
		getCmbTipo().setSelectedIndex(-1);
		this.getTxtCodigo().setBackground(getBackground());
		this.getTxtDescripcionOrden().setBackground(Parametros.saveUpdateColor);
		this.getTxtOficina().setBackground(getBackground());
		this.getCmbFechaLimiteOrden().setBackground(Parametros.saveUpdateColor);
		this.getCmbEstadoOrden().setBackground(Parametros.saveUpdateColor);
		this.getTxtCodigo().setEditable(false);
		this.getTxtFechaCreacion().setEnabled(false);
		this.getTxtObservacion().setEnabled(true);
		this.getCmbCampana().setEnabled(true);
		this.getCmbEquipo().setEnabled(true);
		this.getCmbFechaLimiteOrden().setEnabled(false);
		this.getCmbFechaEntregaOrden().setEnabled(false);
		this.getTxtDescripcionOrdenDetalle().setEnabled(true);
		this.getTxtDescripcionOrden().setEnabled(true);
		this.getCmbSubTipo().setEnabled(true);
		this.getCmbTipo().setEnabled(true);
		this.getCmbAsignadoAOrden().setEnabled(true);
		this.getCmbDirector().setEnabled(true);
		this.getCmbAsignadoAOrdenDetalle().setEnabled(true);
		this.getCmbFechaLimiteOrdenDetalle().setEnabled(true);
		this.getBtnBuscarCliente().setEnabled(true);
		this.getBtnBuscarClienteOficina().setEnabled(true);
		this.getBtnBuscarCorporacion().setEnabled(true);
		this.getCmbEstadoOrden().setEnabled(true);
		this.getCmbEstadoOrdenDetalle().setEnabled(true);
		this.getTxtUrlDescripcionOrdenDetalle().setEditable(false);
		getJtpOrdenTrabajo().setSelectedIndex(0);
		getTxtDescripcionOrden().grabFocus();
	}
	
	ActionListener oActionListenerCmbEjecutivo = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			try {
				if(getCmbAsignadoAOrden().getSelectedItem() != null){
					EmpleadoIf ejecutivo = (EmpleadoIf)getCmbAsignadoAOrden().getSelectedItem();
					//busco en que equipos esta el ejecutivo
					Collection equiposEjecutivo = SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByEmpleadoId(ejecutivo.getId());
					Iterator equiposEjecutivoIt = equiposEjecutivo.iterator();
					HashMap directorEquipoMap = new HashMap();
					while(equiposEjecutivoIt.hasNext()){
						EquipoEmpleadoIf ejecutivoEquipo = (EquipoEmpleadoIf)equiposEjecutivoIt.next();
						//armo un listado de todos los directores del ejecutivo (uno por equipo)
						HashMap buscarDirectorMap = new HashMap();
						buscarDirectorMap.put("equipoId", ejecutivoEquipo.getEquipoId());
						buscarDirectorMap.put("rol", "DCU");
						Collection directorEquipos = SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByQuery(buscarDirectorMap);
						if(directorEquipos.size() > 0){
							EquipoEmpleadoIf directorEquipo = (EquipoEmpleadoIf)directorEquipos.iterator().next();
							EmpleadoIf director = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(directorEquipo.getEmpleadoId());
							EquipoTrabajoIf equipoTrabajoDirector = SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajo(directorEquipo.getEquipoId());
							directorEquipoMap.put(equipoTrabajoDirector.getCodigo(), director);
						}						
					}
					
					getCmbDirector().removeAllItems();
					Iterator directorEquipoMapIt = directorEquipoMap.keySet().iterator();
					while(directorEquipoMapIt.hasNext()){
						String equipoCodigo = (String)directorEquipoMapIt.next();
						EmpleadoIf director = (EmpleadoIf)directorEquipoMap.get(equipoCodigo);
						getCmbDirector().addItem(equipoCodigo + " - " + director);
					}
					
					/*if(ejecutivo.getJefeId() != null){
						EmpleadoIf director = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ejecutivo.getJefeId());
						getTxtDirector().setText(director.getNombres() + " " + director.getApellidos());
					}else{
						getTxtDirector().setText("");
					}*/
				}				
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}
	};
	
	public void resetearFechaLimiteyEntrega(){
		if(ordenDetalleColeccion.size() == 0){
			getCmbFechaLimiteOrden().setDate(getCmbFechaLimiteOrdenDetalle().getDate());
			if(getCmbFechaEntregaOrdenDetalle().getDate() != null){
				getCmbFechaEntregaOrden().setDate(getCmbFechaEntregaOrdenDetalle().getDate());
			}
		}else{
			Date fechaLimiteMenor = ordenDetalleColeccion.get(0).getFechalimite();
			for(OrdenTrabajoDetalleIf ordenTrabajoDetalleIf : ordenDetalleColeccion){
				if(ordenTrabajoDetalleIf.getFechalimite().compareTo(fechaLimiteMenor) < 0){
					fechaLimiteMenor = ordenTrabajoDetalleIf.getFechalimite();
				}
			}
			getCmbFechaLimiteOrden().setDate(fechaLimiteMenor);
			
			getCmbFechaEntregaOrden().setDate(null);
			Date fechaEntregaMenor = fechaLimiteMenor;
			boolean isFechaEntregaNull = true;
			for(OrdenTrabajoDetalleIf ordenTrabajoDetalleIf : ordenDetalleColeccion){
				if(ordenTrabajoDetalleIf.getFechaentrega() != null){
					if(isFechaEntregaNull){
						isFechaEntregaNull = false;
						fechaEntregaMenor = ordenTrabajoDetalleIf.getFechaentrega();
					}else if(ordenTrabajoDetalleIf.getFechaentrega().compareTo(fechaEntregaMenor) > 0){
						fechaEntregaMenor = ordenTrabajoDetalleIf.getFechaentrega();
					}
				}
			}
			if(!isFechaEntregaNull){
				getCmbFechaEntregaOrden().setDate(fechaEntregaMenor);
			}
		}		
	}
	
	Comparator<ProductoClienteIf> ordenadorProductoClienteIf = new Comparator<ProductoClienteIf>(){
		public int compare(ProductoClienteIf o1, ProductoClienteIf o2) {
			return o1.getMarcaProductoNombre().compareTo(o2.getMarcaProductoNombre());
		}		
	};
	
	private void cargarListaProductos(){
		try {
			if(getCmbCampana().getSelectedItem() != null){
				DefaultListModel model = new DefaultListModel();
				CampanaIf campana = (CampanaIf)getCmbCampana().getSelectedItem();
				List<ProductoClienteIf> productosCliente = (ArrayList<ProductoClienteIf>)SessionServiceLocator.getProductoClienteSessionService().findProductoClienteByCampanaId(campana.getId());
				Collections.sort((ArrayList)productosCliente,ordenadorProductoClienteIf);			
				Iterator productosClienteIt = productosCliente.iterator();
				
				while(productosClienteIt.hasNext()){
					ProductoClienteIf productoCliente = (ProductoClienteIf)productosClienteIt.next();
					model.addElement(productoCliente);
				}
				getCbListProductos().setModel(model);
			}			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void initListenersSave() {
		getBtnSeleccionarTodo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				/*try {
				 //Para revisar si existen Asientos sin sus detalles o con un solo detalle.
					LinkedMap asientosMap = new LinkedMap();
					Collection asientos = SessionServiceLocator.getAsientoSessionService().getAsientoList();
					Iterator asientosIt = asientos.iterator();
					while(asientosIt.hasNext()){
						AsientoIf asiento = (AsientoIf)asientosIt.next();
						Collection asientosDetalle = SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByAsientoId(asiento.getId());
						if(asientosDetalle.size() == 0){
							asientosMap.put(asiento.getNumero(), 0);
						}else if(asientosDetalle.size() == 1){
							asientosMap.put(asiento.getNumero(), 1);
						}
						
						Iterator asientosDetalleIt = asientosDetalle.iterator();
						BigDecimal debe = new BigDecimal(0);
						BigDecimal haber = new BigDecimal(0);
						while(asientosDetalleIt.hasNext()){
							AsientoDetalleIf asientoDetalleIf = (AsientoDetalleIf)asientosDetalleIt.next();
							debe = debe.add(asientoDetalleIf.getDebe());
							haber = haber.add(asientoDetalleIf.getHaber());
						}
						if(debe.compareTo(haber) != 0){
							asientosMap.put(asiento.getNumero(), 2);
						}
					}
					
					Iterator mapIt = asientosMap.keySet().iterator();
					while(mapIt.hasNext()){
						String asientoNumero = (String)mapIt.next();
						Integer valor = (Integer)asientosMap.get(asientoNumero);
						if(valor == 0){
							System.out.println("ASIENTO: " + asientoNumero + " , SIN DETALLES");
						}else if(valor == 1){
							System.out.println("ASIENTO: " + asientoNumero + " , SOLO UN DETALLE");
						}else if(valor == 2){
							System.out.println("ASIENTO: " + asientoNumero + " , DEBE Y HABER NO CUADRAN");
						}
					}
					
					//Detalles sin Asientos
					/*ArrayList<AsientoDetalleIf> asientosDetallesSinCabecera = new ArrayList<AsientoDetalleIf>();
					Collection asientosDetalles = SessionServiceLocator.getAsientoDetalleSessionService().getAsientoDetalleList();
					Iterator asientosDetallesIt = asientosDetalles.iterator();
					while(asientosDetallesIt.hasNext()){
						AsientoDetalleIf asientoDetalle = (AsientoDetalleIf)asientosDetallesIt.next();
						AsientoIf asiento = SessionServiceLocator.getAsientoSessionService().getAsiento(asientoDetalle.getAsientoId());
						if(asiento.getId() == null){
							asientosDetallesSinCabecera.add(asientoDetalle);
						}
					}
					if(asientosDetallesSinCabecera.size() > 0){
						for (AsientoDetalleIf asientoDetalleSinCabecera : asientosDetallesSinCabecera){
							System.out.println("ASIENTO DETALLE: " + asientoDetalleSinCabecera.getId() + " , SIN CABECERA");
						}
					}else{
						System.out.println("NO HAY PROBLEMA");
					}*/
					
				/*} catch (GenericBusinessException e) {
					e.printStackTrace();
				}*/
				
				//restaurarAsientos();
				/*try {
					Iterator it = SessionServiceLocator.getPlanMedioDetalleSessionService().getPlanMedioDetalleList().iterator();
					while (it.hasNext()) {
						PlanMedioDetalleIf pmd = (PlanMedioDetalleIf) it.next();
						String comercial = pmd.getComercial() + ",1";
						pmd.setComercial(comercial);
						String version = comercial.split(",")[0];
						pmd.setVersion(version);
						SessionServiceLocator.getPlanMedioDetalleSessionService().savePlanMedioDetalle(pmd);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}*/
				
				getCbListProductos().getCheckBoxListSelectionModel().addSelectionInterval(0, getCbListProductos().getModel().getSize() - 1);
			}
		});
		
		getBtnDeseleccionarTodo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				/*
				 try
			        {
			            // Propiedades de la conexión
			            Properties props = new Properties();//mail
			            props.clear();
			            props.setProperty("mail.smtp.host", "mail.creacional.com");
			            props.setProperty("mail.smtp.auth", "FALSE");          
			            props.setProperty("mail.smtp.port", "25");            
			            props.setProperty("mail.smtp.user", "tecnico@creacional.com");
			            props.setProperty("mail.smtp.pass", "tecnico");
			            props.setProperty("mail.smtps.password", "tecnico");
			          
			             // Preparamos la sesion
			            Session session = Session.getDefaultInstance(props);
			            session.setDebug(true);
			        
			         // Se compone la parte del texto
			            BodyPart texto = new MimeBodyPart();
			            texto.setText("Texto del mensaje");

			            // Se compone el adjunto con la imagen
			            BodyPart adjunto = new MimeBodyPart();            //
			            adjunto.setDataHandler(new DataHandler(new FileDataSource("c:\\TMP\\"+"clientes"+".xls")));
			            adjunto.setFileName("clientes.xls");
			            adjunto.setHeader( "Content-Type", "application/pdf;" );
			            //adjunto.setHeader( "Content-Transfer-Encoding", "base64;");
			            
			            
			            // Una MultiParte para agrupar texto e imagen.
			            MimeMultipart multiParte = new MimeMultipart();
			            multiParte.addBodyPart(texto);
			            multiParte.addBodyPart(adjunto);
			           

						MimeMessage message = new MimeMessage(session);
						message.setFrom(new InternetAddress("tecnico@creacional.com"));
						message.addRecipient(Message.RecipientType.TO,new InternetAddress("sistemas@creacional.com"));
						message.setSubject("Envio de Rol por mail");
						message.setContent(multiParte);
						
						// Lo enviamos.
						Transport t = session.getTransport("smtp");//t.connect("sistemas@creacional.com","sistemas");
						t.connect();
						t.sendMessage(message, message.getAllRecipients());
						// Cierre.
						t.close();
			            
			        }
			        catch (Exception e){
			            e.printStackTrace();            
			        }
				*/
				getCbListProductos().getCheckBoxListSelectionModel().clearSelection();
			}
		});
		
		getCmbAsignadoAOrdenDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					if(getCmbAsignadoAOrdenDetalle().getSelectedItem() != null){
						EmpleadoIf empleado = ((EmpleadoIf) getCmbAsignadoAOrdenDetalle().getSelectedItem());
						EquipoEmpleadoIf equipoEmpleado = (EquipoEmpleadoIf)SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByEmpleadoId(empleado.getId()).iterator().next();
						if(equipoEmpleado.getJefe().equals("S")){
							getCmbAsignadoAOrdenDetalle().setSelectedItem(empleado);
							getTtJefe().setVisible(true);
						}else{
							getTtJefe().setVisible(false);
						}
					}else{
						getTtJefe().setVisible(false);
					}					
				}			
				catch (GenericBusinessException e) {
					e.printStackTrace();
				}
			}
		});
		
		getTblOrdenTrabajoDetalle().addMouseListener(oMouseAdapterTblOrdenDetalleSave);
		getTblOrdenTrabajoDetalle().addKeyListener(oKeyAdapterTblOrdenDetalleSave);
		getBtnAgregarArchivoDescripcion().addActionListener(oActionListenerAgregarArchivo);
		getBtnAgregarArchivoPropuestaOrden().addActionListener(	oActionListenerAgregarArchivo);
		getBtnAgregarArchivoPropuestaOrdenDetalle().addActionListener(oActionListenerAgregarArchivo);
		getBtnActualizarDetalle().addActionListener(oActionListenerBtnActualizarDetalle);
		getCmbAsignadoAOrden().addActionListener(oActionListenerCmbEjecutivo);
		
		getBtnBuscarCorporacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(500);	
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null ){
					corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
					getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
					clienteIf = null;
					clienteOficinaIf = null;
					getTxtCliente().setText("");
					getTxtOficina().setText("");
					cleanListaProductos();
				}
			}
		});
		
		getBtnBuscarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					Long idCorporacion = 0L;
					
					if (corporacionIf != null)
						idCorporacion = corporacionIf.getId();
					
					clienteCriteria = new ClienteCriteria("Clientes", idCorporacion, CODIGO_TIPO_CLIENTE);
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.add(80);
					anchoColumnas.add(300);
					anchoColumnas.add(300);
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
					if ( popupFinder.getElementoSeleccionado() != null ){
						clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
						getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
						if (corporacionIf == null) {
							corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
							getTxtCorporacion().setText(corporacionIf.getCodigo() + " - "+ corporacionIf.getNombre());
						}
						
						clienteOficinaIf = null;
						getTxtOficina().setText("");
						cleanListaProductos();
						getModelProductoCliente().removeAllElements();
						productoClienteList = new ArrayList<ProductoClienteIf>();
						
						Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(clienteIf.getId());
						if ( oficinas.size() == 1 ){
							clienteOficinaIf = oficinas.iterator().next();
							setClienteOficina();
						}
					}
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		});
		
		// Manejo los eventos de Buscar ClienteOficina
		getBtnBuscarClienteOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					Long idCorporacion = 0L;
					Long idCliente = 0L;
					String tipoCliente = "CL";
					String tituloVentanaBusqueda = "Oficinas de Clientes";
									
					if (corporacionIf != null)
						idCorporacion = corporacionIf.getId();
					
					if (clienteIf != null)
						idCliente = clienteIf.getId();
					
					clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, "", false);
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.addElement(70);
					anchoColumnas.addElement(200);
					anchoColumnas.addElement(80);
					anchoColumnas.addElement(230);
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
					if (popupFinder.getElementoSeleccionado() != null) {
						clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
						if (clienteIf == null) {
							clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
							getTxtCliente().setText(clienteIf.getNombreLegal());
							
							if (corporacionIf == null) {
								corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
								getTxtCorporacion().setText(corporacionIf.getNombre());
							}
						}
						setClienteOficina();
					}
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		});
		
		getCmbCampana().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cargarListaProductos();
			}
		}
		);
		
		getCmbTipo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipo = (TipoOrdenIf) getCmbTipo().getModel().getSelectedItem();
				
				if ( tipo != null ){
					getCmbSubTipo().setEnabled(true);
					cargarComboSubTipoOrden(tipo);
					getCmbEquipo().setEnabled(true);
					cargarComboEquipo(tipo);
				}
			}
		});
		
		getCmbEquipo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cargarComboAsignadoAOrdenDetalle();
			}
		});
		
		getBtnLimpiarArchivoDescripcion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getTxtUrlDescripcionOrdenDetalle().setText("");
			}
		});
		
		getBtnLimpiarArchivoPropuesta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getTxtUrlPropuestaOrdenDetalle().setText("");
			}
		});
	}
	
	/*private void restaurarAsientos() {
		try {
			Iterator<AsientoDescuadradoIf> it = SessionServiceLocator.getAsientoDescuadradoSessionService().getAsientoDescuadradoList().iterator();
			while (it.hasNext()) {
				AsientoDescuadradoIf asientoDescuadrado = it.next();
				SessionServiceLocator.getAsientoDescuadradoSessionService().restaurarAsiento(asientoDescuadrado.getAsientoNumero());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}*/
	
	/*private void restaurarAsientos() {
		try {
			int i=1;
			Iterator<AsientoDetalleTmpIf> asientoDetalleTmpIterator = SessionServiceLocator.getAsientoDetalleTmpSessionService().getAsientoDetalleTmpList().iterator();
			while (asientoDetalleTmpIterator.hasNext()) {
				AsientoDetalleTmpIf asientoDetalleTmp = asientoDetalleTmpIterator.next();
				AsientoDetalleIf asientoDetalle = SessionServiceLocator.getAsientoDetalleSessionService().getAsientoDetalle(asientoDetalleTmp.getId());
				if (asientoDetalle != null) {
					asientoDetalle.setAsientoId(asientoDetalleTmp.getAsientoId());
					SessionServiceLocator.getAsientoDetalleSessionService().saveAsientoDetalle(asientoDetalle);
				}
			}
			System.out.println(i);
			i++;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}*/
	
	private void setClienteOficina() {
		getTxtOficina().setText(clienteOficinaIf.getCodigo() + " - "+ clienteOficinaIf.getDescripcion());
		/**
		 * cargo las campañas según el producto cliente
		 * que haya escogido
		 */
		getCmbCampana().setEnabled(true);
		cargarComboCampana();
		
		/**
		 * cargo los empleados de la empresa con que se
		 * logoneo
		 */
		getCmbAsignadoAOrden().setEnabled(true);
		getCmbDirector().setEnabled(true);
		cargarComboAsignado(0L);
	}
	
	private void cargarComboCampana(){
		try {
			Map mapaCampana = new HashMap();
			mapaCampana.put("clienteId",clienteIf.getId());
			mapaCampana.put("estado",ESTADO_ACTIVO);
			List campanas = (List) SessionServiceLocator.getCampanaSessionService().findCampanaByQuery(mapaCampana);
			refreshCombo(getCmbCampana(),campanas);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	Comparator<EquipoTrabajoIf> ordenadorEquipoPorCodigo = new Comparator<EquipoTrabajoIf>(){
		public int compare(EquipoTrabajoIf o1, EquipoTrabajoIf o2) {
			return (o1.getCodigo().compareTo(o2.getCodigo()));
		}		
	};
	
	private void cargarComboEquipo(TipoOrdenIf tipo){
		try {
			List equipos = (List) SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByEmpresaIdAndTipoOrdenId(Parametros.getIdEmpresa(),	tipo.getId());
			Collections.sort(equipos, ordenadorEquipoPorCodigo);
			refreshCombo(getCmbEquipo(),equipos);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboSubTipoOrden(TipoOrdenIf tipo){
		try {
			List subTipoOrden = (List) SessionServiceLocator.getSubtipoOrdenSessionService().findSubtipoOrdenByTipoordenId(tipo.getId());
			refreshCombo(getCmbSubTipo(),subTipoOrden);
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	Comparator<EmpleadoIf> ordenadorArrayListPorNombre = new Comparator<EmpleadoIf>(){
		public int compare(EmpleadoIf o1, EmpleadoIf o2) {
			return (o1.getNombres().compareTo(o2.getNombres()));
		}		
	};
	
	private void cargarComboAsignado(Long ejecutivoSeleccionadoId){
		try {		
			String CODIGO_ROL_EJECUTIVO_CUENTA = "ECU";
			int ejecutivoSeleccionadoIndex = -1;
			List<EmpleadoIf> ejecutivos = new ArrayList<EmpleadoIf>();
			Collection empleadosEquipo = SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Iterator empleadosEquipoIt = empleadosEquipo.iterator();
			while(empleadosEquipoIt.hasNext()){
				EquipoEmpleadoIf empleadoEquipo = (EquipoEmpleadoIf)empleadosEquipoIt.next();
				if(empleadoEquipo.getRol().equals(CODIGO_ROL_EJECUTIVO_CUENTA)){
					EmpleadoIf ejecutivo = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(empleadoEquipo.getEmpleadoId());
					boolean ejecutivoIngresado = false;
					for(EmpleadoIf ejecutivoAgregado : ejecutivos){
						if(ejecutivoAgregado.getId().compareTo(ejecutivo.getId()) == 0){
							ejecutivoIngresado = true;
						}
					}
					if(!ejecutivoIngresado)
						ejecutivos.add(ejecutivo);
				}
			}
			Collections.sort((ArrayList)ejecutivos,ordenadorArrayListPorNombre);
			//Se es update y se envio un id de ejecutivo entonces lo busco para setear su nombre en el combo.
			for(int i=0; i<ejecutivos.size(); i++){
				EmpleadoIf ejecutivo = ejecutivos.get(i);
				if(ejecutivoSeleccionadoId > 0L && ejecutivo.getId().compareTo(ejecutivoSeleccionadoId) == 0){
					ejecutivoSeleccionadoIndex = i;
				}
			}
			refreshComboByIndex(getCmbAsignadoAOrden(), ejecutivos, ejecutivoSeleccionadoIndex);
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}	
	
	Comparator<EquipoEmpleadoIf> ordenadorEquipoEmpleadoPorEmpleadoNombre = new Comparator<EquipoEmpleadoIf>(){
		public int compare(EquipoEmpleadoIf o1, EquipoEmpleadoIf o2) {
			try {
				EmpleadoIf empleado1 = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(o1.getEmpleadoId());
				EmpleadoIf empleado2 = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(o2.getEmpleadoId());
				return (empleado1.getNombres().compareTo(empleado2.getNombres()));
			} catch(GenericBusinessException e) {
				e.printStackTrace();
			}
			return 0;
		}		
	};
	
	private void cargarComboAsignadoAOrdenDetalle() {
		try {			
			if (getCmbEquipo().getSelectedItem() != null) {
				EquipoTrabajoIf equipoTrabajo = (EquipoTrabajoIf) getCmbEquipo().getSelectedItem();
				List<EmpleadoIf> asignados = (List<EmpleadoIf>) SessionServiceLocator.getEquipoEmpleadoSessionService().findEmpleadoByEquipoTrabajoId(equipoTrabajo.getId());
				refreshCombo(getCmbAsignadoAOrdenDetalle(),asignados);
				
				for(EmpleadoIf empleado : asignados){
					EquipoEmpleadoIf equipoEmpleado = (EquipoEmpleadoIf)SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByEmpleadoId(empleado.getId()).iterator().next();
					if(equipoEmpleado.getJefe().equals("S")){
						getCmbAsignadoAOrdenDetalle().setSelectedItem(empleado);
						getTtJefe().setVisible(true);
					}
				}			
				
				/*TipoOrdenIf tipoOrden = SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(equipoTrabajo.getTipoordenId());
				if(tipoOrden.getNombre().equals("MEDIOS")){
					for(EmpleadoIf empleado : asignados){
						EquipoEmpleadoIf equipoEmpleado = (EquipoEmpleadoIf)SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByEmpleadoId(empleado.getId()).iterator().next();
						if(equipoEmpleado.getRol().equals("PME")){
							getCmbAsignadoAOrdenDetalle().setSelectedItem(empleado);
						}
					}
				}else if(tipoOrden.getNombre().equals("CREATIVO")){
					for(EmpleadoIf empleado : asignados){
						EquipoEmpleadoIf equipoEmpleado = (EquipoEmpleadoIf)SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByEmpleadoId(empleado.getId()).iterator().next();
						if(equipoEmpleado.getRol().equals("DCR")){
							getCmbAsignadoAOrdenDetalle().setSelectedItem(empleado);
						}
					}
				}*/		
				getCmbAsignadoAOrdenDetalle().repaint();
				getCmbAsignadoAOrdenDetalle().setEnabled(true);
			}
			
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	MouseListener oMouseAdapterTblOrdenDetalleSave = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblOrdenTrabajoDetalle().getModel().getRowCount() > 0)
				popupMenuTipoOrdenDetalle.show(evt.getComponent(), evt.getX(), evt.getY());
			else
				enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblOrdenDetalleSave = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (getTblOrdenTrabajoDetalle().getSelectedRow() != -1) {
			OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) ordenDetalleColeccion.get(((JTable) evt.getSource()).getSelectedRow());
			
			try {
				SubtipoOrdenIf subt = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(ordenTrabajoDetalleIf.getSubtipoId());
				getCmbTipo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipo(), subt.getTipoordenId()));
				getCmbTipo().repaint();
				
				if (getCmbSubTipo().getItemCount() == 0) {
					getCmbSubTipo().setEnabled(true);
					SpiritComboBoxModel cmbModel2;
					
					try {
						cmbModel2 = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getSubtipoOrdenSessionService().findSubtipoOrdenByTipoordenId(subt.getTipoordenId()));
						getCmbSubTipo().setModel(cmbModel2);
					} catch (GenericBusinessException e1) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e1.printStackTrace();
					}
				} else
					getCmbSubTipo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbSubTipo(), subt.getId()));
				
				getCmbSubTipo().repaint();
				getCmbEquipo().removeAllItems();
				Collection equipo = SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByEmpresaIdAndTipoOrdenId(Parametros.getIdEmpresa(),subt.getTipoordenId());
				Iterator itequipo = equipo.iterator();
				
				while (itequipo.hasNext()) {
					EquipoTrabajoIf equipoIf = (EquipoTrabajoIf) itequipo.next();
					getCmbEquipo().addItem(equipoIf);
					if (equipoIf.getId().compareTo(ordenTrabajoDetalleIf.getEquipoId()) == 0){
						getCmbEquipo().setSelectedItem(equipoIf);
					}
				}
				
				if (getCmbAsignadoAOrdenDetalle().getItemCount() == 0) {
					getCmbAsignadoAOrdenDetalle().setEnabled(true);
					getTtJefe().setVisible(false);
					
					SpiritComboBoxModel cmbModel8;
					
					try {
						cmbModel8 = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaId(Parametros.getIdEmpresa()));
						getCmbAsignadoAOrdenDetalle().setModel(cmbModel8);
					} catch (GenericBusinessException e1) {
						e1.printStackTrace();
					}
				} else
					getCmbAsignadoAOrdenDetalle().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbAsignadoAOrdenDetalle(),ordenTrabajoDetalleIf.getAsignadoaId()));
				
				getTxtDescripcionOrdenDetalle().setText(ordenTrabajoDetalleIf.getDescripcion());
				getTxtUrlDescripcionOrdenDetalle().setText(ordenTrabajoDetalleIf.getUrlDescripcion());
				getTxtUrlPropuestaOrdenDetalle().setText(ordenTrabajoDetalleIf.getUrlPropuesta());
				
				String estadoLetra = ordenTrabajoDetalleIf.getEstado();
				EstadoOrdenTrabajo estado = EstadoOrdenTrabajo.getEstadoOrdenTrabajoByLetra(estadoLetra);
				getCmbEstadoOrdenDetalle().setSelectedItem(estado);
				
				Calendar calendarInicio = new GregorianCalendar();
				calendarInicio.setTime(ordenTrabajoDetalleIf.getFechalimite());
				getCmbFechaLimiteOrdenDetalle().setCalendar(calendarInicio);
				fechaLimiteDetalle = ordenTrabajoDetalleIf.getFechalimite();
				
				if (ordenTrabajoDetalleIf.getFechaentrega() != null)
					getCmbFechaEntregaOrdenDetalle().setDate(ordenTrabajoDetalleIf.getFechaentrega());
				else
					getCmbFechaEntregaOrdenDetalle().setSelectedItem(null);
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getSelectedObject(boolean desdeControlTrafico){	
		try {
			setUpdateMode();
			if(!desdeControlTrafico){
				ordentrabajoIf = (OrdenTrabajoIf) popupFinder.getElementoSeleccionado();
			}
			getTxtCodigo().setText(ordentrabajoIf.getCodigo());
			getTxtObservacion().setText(ordentrabajoIf.getObservacion());
			getTxtDescripcionOrden().setText(ordentrabajoIf.getDescripcion());
			if(ordentrabajoIf.getUrlPropuesta() != null) {
				getTxtUrlPropuestaOrden().setText(ordentrabajoIf.getUrlPropuesta());
				getBtnVerArchivoPropuestaOrden().setEnabled(true);
			} else
				getBtnVerArchivoPropuestaOrden().setEnabled(false);
			
			clienteOficinaIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordentrabajoIf.getClienteoficinaId());
			clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
			corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
			getTxtOficina().setText(clienteOficinaIf.getCodigo() + " - " + clienteOficinaIf.getDescripcion());
			getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
			getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
			Collection campana = SessionServiceLocator.getCampanaSessionService().findCampanaByClienteId(clienteIf.getId());
			Iterator itcampana = campana.iterator();
			getCmbCampana().removeAllItems();
			
			while (itcampana.hasNext()) {
				CampanaIf campanaIf = (CampanaIf) itcampana.next();
				getCmbCampana().addItem(campanaIf);
				if (campanaIf.getId().compareTo(ordentrabajoIf.getCampanaId()) == 0)
					getCmbCampana().setSelectedItem(campanaIf);
			}
			
			getCmbCampana().repaint();
			
			//Cargo ejecutivo seleccionado
			EmpleadoIf empleadoIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordentrabajoIf.getEmpleadoId());
			cargarComboAsignado(empleadoIf.getId());
			getCmbAsignadoAOrden().repaint();
			
			//Selecciono el equipo y director del ejecutivo
			HashMap buscarDirectorMap = new HashMap();
			buscarDirectorMap.put("equipoId", ordentrabajoIf.getEquipoId());
			buscarDirectorMap.put("rol", "DCU");
			Collection directorEquipos = SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByQuery(buscarDirectorMap);
			if(directorEquipos.size() > 0){
				EquipoEmpleadoIf directorEquipo = (EquipoEmpleadoIf)directorEquipos.iterator().next();
				EmpleadoIf director = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(directorEquipo.getEmpleadoId());
				EquipoTrabajoIf equipoTrabajoDirector = SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajo(directorEquipo.getEquipoId());
				getCmbDirector().setSelectedItem(equipoTrabajoDirector.getCodigo() + " - " + director);
			}			
						
			getTxtFechaCreacion().setText(Utilitarios.getFechaUppercase(ordentrabajoIf.getFecha()));
			Calendar calendarInicio = new GregorianCalendar();
			calendarInicio.setTime(ordentrabajoIf.getFechalimite());
			getCmbFechaLimiteOrden().setCalendar(calendarInicio);
			fechaLimite = ordentrabajoIf.getFechalimite();
			
			if(ordentrabajoIf.getFechaentrega() != null)
				getCmbFechaEntregaOrden().setDate(ordentrabajoIf.getFechaentrega());
			else
				getCmbFechaEntregaOrden().setSelectedItem(null);
			
			String estadoLetra = ordentrabajoIf.getEstado();
			EstadoOrdenTrabajo estado = EstadoOrdenTrabajo.getEstadoOrdenTrabajoByLetra(estadoLetra);
			getCmbEstadoOrden().setSelectedItem(estado);
			
			/*if (ESTADO_PENDIENTE.equals(ordentrabajoIf.getEstado()))
				getCmbEstadoOrden().setSelectedItem(NOMBRE_ESTADO_PENDIENTE);
			else if (ESTADO_SUSPENDIDO.equals(ordentrabajoIf.getEstado()))
				getCmbEstadoOrden().setSelectedItem(NOMBRE_ESTADO_SUSPENDIDO);
			else if (ESTADO_CANCELADO.equals(ordentrabajoIf.getEstado()))
				getCmbEstadoOrden().setSelectedItem(NOMBRE_ESTADO_CANCELADO);
			else if (ESTADO_EN_CURSO.equals(ordentrabajoIf.getEstado()))
				getCmbEstadoOrden().setSelectedItem(NOMBRE_ESTADO_EN_CURSO);
			else if (ESTADO_REALIZADO.equals(ordentrabajoIf.getEstado()))
				getCmbEstadoOrden().setSelectedItem(NOMBRE_ESTADO_REALIZADO);*/
			
			// Cargo los productos clientes pertencientes a la orden
			cargarListaProductos();
			Collection listaProductos = SessionServiceLocator.getOrdenTrabajoProductoSessionService().findOrdenTrabajoProductoByOrdenTrabajoId(ordentrabajoIf.getId());
			Iterator listaProductosIt = listaProductos.iterator();
			while (listaProductosIt.hasNext()) {
				OrdenTrabajoProductoIf ordenTrabajoProductoIf = (OrdenTrabajoProductoIf) listaProductosIt.next();

				productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(ordenTrabajoProductoIf.getProductoClienteId());

				// Agrego el producto cliente cargado a la coleccion
				productoClienteList.add(productoClienteIf);
			}
			for(int i=0; i<getCbListProductos().getModel().getSize(); i++){
				ProductoClienteIf productoCliente = (ProductoClienteIf)getCbListProductos().getModel().getElementAt(i);
				for(int j=0; j<productoClienteList.size(); j++){
					ProductoClienteIf productoClienteUpdate = productoClienteList.get(j);
					if(productoCliente.getId().compareTo(productoClienteUpdate.getId()) == 0){
						getCbListProductos().getCheckBoxListSelectionModel().addSelectionInterval(i,i);
					}
				}
			}
			
			// cargo los elementos de orden trabajo detalle
			ordenTrabajoDetalleColeccion = null;
			ordenTrabajoDetalleColeccion = new Vector<OrdenTrabajoDetalleReporteData>();
			
			modelOrdenDetalle = (DefaultTableModel) getTblOrdenTrabajoDetalle().getModel();
			Collection listaOrdenDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByOrdenId(ordentrabajoIf.getId());
			Iterator listaOrdenDetalleIt = listaOrdenDetalle.iterator();
			
			while (listaOrdenDetalleIt.hasNext()) {
				ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) listaOrdenDetalleIt.next();
				Vector<String> filaOrdenDetalle = new Vector<String>();
				ordenDetalleColeccion.add(ordenTrabajoDetalleIf);
											
				//Se pone null en la lista de archivos para los archivos que ya estan en el servidor
				archivosDescripcionColeccion.add(null);
				archivosPropuestaColeccion.add(null);
				SubtipoOrdenIf subtipo = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(ordenTrabajoDetalleIf.getSubtipoId());
				EmpleadoIf empleadoDetalle = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenTrabajoDetalleIf.getAsignadoaId());
				EquipoTrabajoIf equipoTrabajo = SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajo(ordenTrabajoDetalleIf.getEquipoId());
				filaOrdenDetalle.add(subtipo.getNombre());
				filaOrdenDetalle.add(equipoTrabajo.getCodigo());
				filaOrdenDetalle.add(empleadoDetalle.getNombres() + " "	+ empleadoDetalle.getApellidos());
				// filaOrdenDetalle.add(fecha);
				filaOrdenDetalle.add(Utilitarios.getFechaCortaUppercase(ordenTrabajoDetalleIf.getFechalimite()));
				
				//Cargo los datos para el reporte
				OrdenTrabajoDetalleReporteData ordenTrabajoDetalleReporteData = new OrdenTrabajoDetalleReporteData();
				EmpleadoIf empleadoOrden = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenTrabajoDetalleIf.getAsignadoaId());
				ordenTrabajoDetalleReporteData.setAsignadoA(empleadoOrden.getNombres().split(" ")[0] + " " + empleadoOrden.getApellidos().split(" ")[0]);
				ordenTrabajoDetalleReporteData.setDescripcion(ordenTrabajoDetalleIf.getDescripcion());
				
				estadoLetra = ordenTrabajoDetalleIf.getEstado();
				estado = EstadoOrdenTrabajo.getEstadoOrdenTrabajoByLetra(estadoLetra);
				getCmbEstadoOrdenDetalle().setSelectedItem(estado);
				filaOrdenDetalle.add(estado.toString());
				ordenTrabajoDetalleReporteData.setEstado(estado.toString());
				
				/*if (ESTADO_PENDIENTE.equals(ordenTrabajoDetalleIf.getEstado())) {
					getCmbEstadoOrdenDetalle().setSelectedItem(NOMBRE_ESTADO_PENDIENTE);
					filaOrdenDetalle.add(NOMBRE_ESTADO_PENDIENTE);
					ordenTrabajoDetalleReporteData.setEstado(NOMBRE_ESTADO_PENDIENTE);
					
				} else if (ESTADO_SUSPENDIDO.equals(ordenTrabajoDetalleIf.getEstado())) {
					getCmbEstadoOrdenDetalle().setSelectedItem(NOMBRE_ESTADO_SUSPENDIDO);
					filaOrdenDetalle.add(NOMBRE_ESTADO_SUSPENDIDO);
					ordenTrabajoDetalleReporteData.setEstado(NOMBRE_ESTADO_SUSPENDIDO);
					
				} else if (ESTADO_CANCELADO.equals(ordenTrabajoDetalleIf.getEstado())) {
					getCmbEstadoOrdenDetalle().setSelectedItem(NOMBRE_ESTADO_CANCELADO);
					filaOrdenDetalle.add(NOMBRE_ESTADO_CANCELADO);
					ordenTrabajoDetalleReporteData.setEstado(NOMBRE_ESTADO_CANCELADO);
					
				} else if (ESTADO_EN_CURSO.equals(ordenTrabajoDetalleIf.getEstado())) {
					getCmbEstadoOrdenDetalle().setSelectedItem(NOMBRE_ESTADO_EN_CURSO);
					filaOrdenDetalle.add(NOMBRE_ESTADO_EN_CURSO);
					ordenTrabajoDetalleReporteData.setEstado(NOMBRE_ESTADO_EN_CURSO);
					
				} else if (ESTADO_REALIZADO.equals(ordenTrabajoDetalleIf.getEstado())) {
					getCmbEstadoOrdenDetalle().setSelectedItem(NOMBRE_ESTADO_REALIZADO);
					filaOrdenDetalle.add(NOMBRE_ESTADO_REALIZADO);
					ordenTrabajoDetalleReporteData.setEstado(NOMBRE_ESTADO_REALIZADO);
				}*/
				modelOrdenDetalle.addRow(filaOrdenDetalle);
								
				ordenTrabajoDetalleReporteData.setFechaEntrega(ordenTrabajoDetalleIf.getFechaentrega()!=null?Utilitarios.getFechaCortaUppercase(ordenTrabajoDetalleIf.getFechaentrega()):"N/A");
				ordenTrabajoDetalleReporteData.setSubtipo(subtipo.getNombre());
				TipoOrdenIf tipo = SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(subtipo.getTipoordenId());
				ordenTrabajoDetalleReporteData.setTipo(tipo.getNombre());
				
				if(ordenTrabajoDetalleIf.getFecha() != null){
					Timestamp fechaHoraReporte = new Timestamp(ordenTrabajoDetalleIf.getFecha().getTime());
					ordenTrabajoDetalleReporteData.setFechaDetalle(Utilitarios.getFechaCortaUppercase(new Date(fechaHoraReporte.getTime())));
					ordenTrabajoDetalleReporteData.setHoraDetalle(Utilitarios.getHora(fechaHoraReporte.getHours(), fechaHoraReporte.getMinutes(), fechaHoraReporte.getSeconds()));
				}else{
					ordenTrabajoDetalleReporteData.setFechaDetalle("N/A");
					ordenTrabajoDetalleReporteData.setHoraDetalle("N/A");
				}
								
				ordenTrabajoDetalleColeccion.add(ordenTrabajoDetalleReporteData);
			}
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		} 
		
		this.showUpdateMode();
	}
		
	/**
	 * Función que me permite agregar detalles a la tabla y sue sean despues
	 * guardados en el orden detalle
	 */
	public void agregarDetalle() {
		try {			
			if (validateFieldsDetalle()){
				modelOrdenDetalle = (DefaultTableModel) getTblOrdenTrabajoDetalle().getModel();
				Vector<String> filaDetalle = new Vector<String>();
				
				OrdenTrabajoDetalleData data = new OrdenTrabajoDetalleData();
				SubtipoOrdenIf subTipo = ((SubtipoOrdenIf) this.getCmbSubTipo().getSelectedItem());
				EquipoTrabajoIf equipo = ((EquipoTrabajoIf) this.getCmbEquipo().getSelectedItem());
				EmpleadoIf empleado = ((EmpleadoIf) this.getCmbAsignadoAOrdenDetalle().getSelectedItem());
				data.setSubtipoId(subTipo.getId());
				data.setEquipoId(equipo.getId());
				data.setAsignadoaId(empleado.getId());
				fechaLimiteDetalle = getCmbFechaLimiteOrdenDetalle().getDate();
				data.setFechalimite(new java.sql.Date(fechaLimiteDetalle.getTime()));
				
				if (getCmbFechaEntregaOrdenDetalle().getDate() != null)
					data.setFechaentrega(new java.sql.Date(getCmbFechaEntregaOrdenDetalle().getDate().getTime()));
				
				if (this.getTxtUrlDescripcionOrdenDetalle().getText() != null && !this.getTxtUrlDescripcionOrdenDetalle().getText().equals(""))
					data.setUrlDescripcion(this.getTxtUrlDescripcionOrdenDetalle().getText());
				
				if (this.getTxtUrlPropuestaOrdenDetalle().getText() != null && !this.getTxtUrlPropuestaOrdenDetalle().getText().equals(""))
					data.setUrlPropuesta(this.getTxtUrlPropuestaOrdenDetalle().getText());
				
				data.setDescripcion(getTxtDescripcionOrdenDetalle().getText()!=null?getTxtDescripcionOrdenDetalle().getText():"");
				//data.setEstado(this.getCmbEstadoOrdenDetalle().getSelectedItem().toString().substring(0, 1));
				EstadoOrdenTrabajo estadoDetalle = (EstadoOrdenTrabajo) getCmbEstadoOrdenDetalle().getSelectedItem();
				data.setEstado(estadoDetalle.getLetra());
				
				if ( data.getUrlDescripcion()!=null && !data.getUrlDescripcion().equals("") ){
					File archivoDescripcion = new File(getTxtUrlDescripcionOrdenDetalle().getText());
					archivosDescripcionColeccion.add(archivoDescripcion);
				} else
					archivosDescripcionColeccion.add(null);
				
				if ( data.getUrlPropuesta()!=null && !data.getUrlPropuesta().equals("") ){
					File archivoPropuesta = new File(getTxtUrlPropuestaOrdenDetalle().getText());
					archivosPropuestaColeccion.add(archivoPropuesta);
				} else
					archivosPropuestaColeccion.add(null);
				
				Calendar fechaHora = new GregorianCalendar();
				Timestamp fechaHoraReporte = new Timestamp(fechaHora.getTimeInMillis());
				data.setFecha(fechaHoraReporte);
				
				ordenDetalleColeccion.add(data);
								
				filaDetalle.add(subTipo.getNombre());
				filaDetalle.add(this.getCmbEquipo().getSelectedItem().toString());
				filaDetalle.add(empleado.getNombres() + " " + empleado.getApellidos());
				filaDetalle.add(Utilitarios.getFechaCortaUppercase(fechaLimiteDetalle));
				filaDetalle.add(this.getCmbEstadoOrdenDetalle().getSelectedItem().toString());
				modelOrdenDetalle.addRow(filaDetalle);				
				
				OrdenTrabajoDetalleReporteData ordenTrabajoDetalleReporteData = new OrdenTrabajoDetalleReporteData();
				ordenTrabajoDetalleReporteData.setAsignadoA(empleado.getNombres().split(" ")[0] + " " + empleado.getApellidos().split(" ")[0]);
				ordenTrabajoDetalleReporteData.setDescripcion(getTxtDescripcionOrdenDetalle().getText()!=null?getTxtDescripcionOrdenDetalle().getText():"");
				ordenTrabajoDetalleReporteData.setEstado(this.getCmbEstadoOrdenDetalle().getSelectedItem().toString());
				
				if (getCmbFechaEntregaOrdenDetalle().getDate() != null)
					ordenTrabajoDetalleReporteData.setFechaEntrega(Utilitarios.getFechaCortaUppercase(getCmbFechaEntregaOrdenDetalle().getDate()));
								
				ordenTrabajoDetalleReporteData.setSubtipo(((SubtipoOrdenIf) this.getCmbSubTipo().getSelectedItem()).toString());
				TipoOrdenIf tipo = SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(subTipo.getTipoordenId());
				ordenTrabajoDetalleReporteData.setTipo(tipo.getNombre());
				
				ordenTrabajoDetalleReporteData.setFechaDetalle(Utilitarios.getFechaCortaUppercase(new Date(fechaHoraReporte.getTime())));
				ordenTrabajoDetalleReporteData.setHoraDetalle(Utilitarios.getHora(fechaHoraReporte.getHours(), fechaHoraReporte.getMinutes(), fechaHoraReporte.getSeconds()));
								
				ordenTrabajoDetalleColeccion.add(ordenTrabajoDetalleReporteData);
								
				resetearFechaLimiteyEntrega();
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se pudo ingresar el detalle a la orden !!!", SpiritAlert.ERROR);
		}
	}
	
	/**
	 * me permite darle un formato a lafecha para poder ser presentada en al
	 * tabla
	 */
	SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-mm-dd");
	
	ActionListener oActionListenerEliminarOrdenDetalle = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			eliminarDetalleOrdenTrabajo();
		}
	};
	
	ActionListener oActionListenerBtnActualizarDetalle = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			actualizarDetalleOrdenTrabajo();
		}
	};
	
	private void actualizarDetalleOrdenTrabajo() {
		if (getTblOrdenTrabajoDetalle().getSelectedRow() != -1) {
			if (validateFieldsDetalle()){
				int fila = getTblOrdenTrabajoDetalle().getSelectedRow();
				ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) ordenDetalleColeccion.get(fila);
				
				//if ((getMode() == SpiritMode.UPDATE_MODE) && ordenTrabajoDetalleIf.getEstado().equals(ESTADO_REALIZADO)) {
					//SpiritAlert.createAlert("Esta orden Detalle ya está Realizada, no se puede actualizar.", SpiritAlert.WARNING);
				//}else{
					OrdenTrabajoDetalleData data = new OrdenTrabajoDetalleData();
					
					SubtipoOrdenIf subTipo = ((SubtipoOrdenIf) getCmbSubTipo().getSelectedItem());
					EquipoTrabajoIf equipo = ((EquipoTrabajoIf) getCmbEquipo().getSelectedItem());
					EmpleadoIf empleado = ((EmpleadoIf) getCmbAsignadoAOrdenDetalle().getSelectedItem());
					
					data.setId(ordenTrabajoDetalleIf.getId());
					data.setOrdenId(ordenTrabajoDetalleIf.getOrdenId());
					data.setSubtipoId(subTipo.getId());
					data.setEquipoId(equipo.getId());
					data.setAsignadoaId(empleado.getId());
					data.setFechalimite(new java.sql.Date(getCmbFechaLimiteOrdenDetalle().getDate().getTime()));
							
					if(getCmbFechaEntregaOrdenDetalle().getDate() != null){
						data.setFechaentrega(new java.sql.Date(getCmbFechaEntregaOrdenDetalle().getDate().getTime()));
					}else{
						data.setFechaentrega(null);
					}
					
					if (getTxtUrlDescripcionOrdenDetalle().getText() != null
							&& !getTxtUrlDescripcionOrdenDetalle().getText().equals(""))
						data.setUrlDescripcion(getTxtUrlDescripcionOrdenDetalle().getText());
					else
						data.setUrlDescripcion(null);
					
					if (getTxtUrlPropuestaOrdenDetalle().getText() != null
							&& !getTxtUrlPropuestaOrdenDetalle().getText().equals(""))
						data.setUrlPropuesta(getTxtUrlPropuestaOrdenDetalle().getText());
					else
						data.setUrlPropuesta(null);
					
					data.setDescripcion(getTxtDescripcionOrdenDetalle().getText()!=null?getTxtDescripcionOrdenDetalle().getText():"");
					
					/*if ((ordenTrabajoDetalleIf.getEstado().equals(ESTADO_EN_CURSO) && !getCmbEstadoOrdenDetalle().getSelectedItem().toString().substring(0, 1).equals(ESTADO_EN_CURSO)) 
							|| (ordenTrabajoDetalleIf.getEstado().equals(ESTADO_REALIZADO) && !getCmbEstadoOrdenDetalle().getSelectedItem().toString().substring(0, 1).equals(ESTADO_REALIZADO))) {
						SpiritAlert.createAlert("Esta orden detalle ya está siendo procesada, el Estado no puede cambiar.",	SpiritAlert.WARNING);
						data.setEstado(ordenTrabajoDetalleIf.getEstado());
					} else*/
					//if(getCmbEstadoOrdenDetalle().getSelectedItem() != null){
					//	data.setEstado(getCmbEstadoOrdenDetalle().getSelectedItem().toString().substring(0, 1));
					//}
					EstadoOrdenTrabajo estadoDetalle = (EstadoOrdenTrabajo) getCmbEstadoOrdenDetalle().getSelectedItem(); 
					if(estadoDetalle != null){
						data.setEstado(estadoDetalle.getLetra());
					}
					
					if (data.getUrlDescripcion()!=null && !data.getUrlDescripcion().equals("")){
						File archivoDescripcion = new File(getTxtUrlDescripcionOrdenDetalle().getText());
						archivosDescripcionColeccion.add(fila,archivoDescripcion);
					} else
						archivosDescripcionColeccion.add(fila,null);
					archivosDescripcionColeccion.remove(fila+1);
					
					if (data.getUrlPropuesta()!=null && !data.getUrlPropuesta().equals("")){
						File archivoPropuesta = new File(getTxtUrlPropuestaOrdenDetalle().getText());
						archivosPropuestaColeccion.add(archivoPropuesta);
					} else
						archivosPropuestaColeccion.add(fila,null);
					
					Calendar fechaHora = new GregorianCalendar();
					Timestamp fechaHoraReporte = new Timestamp(fechaHora.getTimeInMillis());
					data.setFecha(fechaHoraReporte);
					
					ordenDetalleColeccion.set(fila, data);
					
					archivosPropuestaColeccion.remove(fila+1);
					
					modelOrdenDetalle.setValueAt(subTipo.getNombre(), getTblOrdenTrabajoDetalle().getSelectedRow(), 0);
					modelOrdenDetalle.setValueAt(getCmbEquipo().getSelectedItem().toString(), getTblOrdenTrabajoDetalle().getSelectedRow(), 1);
					modelOrdenDetalle.setValueAt(empleado.getNombres() + " " + empleado.getApellidos(),	getTblOrdenTrabajoDetalle().getSelectedRow(), 2);
					modelOrdenDetalle.setValueAt(Utilitarios.getFechaCortaUppercase(getCmbFechaLimiteOrdenDetalle().getDate()),	getTblOrdenTrabajoDetalle().getSelectedRow(), 3);
					modelOrdenDetalle.setValueAt(estadoDetalle.toString(), getTblOrdenTrabajoDetalle().getSelectedRow(), 4);
											
					OrdenTrabajoDetalleReporteData ordenTrabajoDetalleReporteData = new OrdenTrabajoDetalleReporteData();
					ordenTrabajoDetalleReporteData.setAsignadoA(empleado.getNombres().split(" ")[0] + " " + empleado.getApellidos().split(" ")[0]);
					ordenTrabajoDetalleReporteData.setDescripcion(getTxtDescripcionOrdenDetalle().getText()!=null?getTxtDescripcionOrdenDetalle().getText():"");
					ordenTrabajoDetalleReporteData.setEstado(estadoDetalle.toString());
					
					if (getCmbFechaEntregaOrdenDetalle().getDate() != null)
						ordenTrabajoDetalleReporteData.setFechaEntrega(Utilitarios.getFechaCortaUppercase(getCmbFechaEntregaOrdenDetalle().getDate()));
									
					ordenTrabajoDetalleReporteData.setSubtipo(((SubtipoOrdenIf) this.getCmbSubTipo().getSelectedItem()).toString());
					
					try {
						TipoOrdenIf tipo = SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(subTipo.getTipoordenId());
						ordenTrabajoDetalleReporteData.setTipo(tipo.getNombre());
					} catch (GenericBusinessException e) {
						e.printStackTrace();
					}
										
					if(ordenTrabajoDetalleIf.getFecha() != null){
						ordenTrabajoDetalleReporteData.setFechaDetalle(Utilitarios.getFechaCortaUppercase(new Date(fechaHoraReporte.getTime())));
						ordenTrabajoDetalleReporteData.setHoraDetalle(Utilitarios.getHora(fechaHoraReporte.getHours(), fechaHoraReporte.getMinutes(), fechaHoraReporte.getSeconds()));
					}else{
						ordenTrabajoDetalleReporteData.setFechaDetalle("N/A");
						ordenTrabajoDetalleReporteData.setHoraDetalle("N/A");
					}
									
					ordenTrabajoDetalleColeccion.set(fila,ordenTrabajoDetalleReporteData);
					
					resetearFechaLimiteyEntrega();
				//}	
			}
			else {
				SpiritAlert.createAlert("Por favor ingrese los datos completos para Orden Detalle.", SpiritAlert.WARNING);
			}
			
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de Orden Detalle a actualizar.", SpiritAlert.WARNING);
		}
		
		cleanDetalle();
	}
	
	private void cleanListaProductos() {
		if(getCbListProductos().getModel().getSize()>0){
			getCbListProductos().getCheckBoxListSelectionModel().clearSelection();
			((DefaultListModel) getCbListProductos().getModel()).removeAllElements();
		}		
	}
	
	public void cargarCombos() {		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel(EstadoOrdenTrabajo.values());
		getCmbEstadoOrden().setModel(modelo);
		DefaultComboBoxModel modeloDetalle = new DefaultComboBoxModel(EstadoOrdenTrabajo.values());
		getCmbEstadoOrdenDetalle().setModel(modeloDetalle);
		cargarComboTipoOrden();
	}
	
	private void cargarComboTipoOrden(){
		try {
			List tiposOrden = (List) SessionServiceLocator.getTipoOrdenSessionService()
			.findTipoOrdenByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTipo(),tiposOrden);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Action Listener que me permite escoger un archivo
	 */
	
	ActionListener oActionListenerAgregarArchivo = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			
			Component parent = (Component) actionEvent.getSource();
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAccessory(new LabelAccessory(fileChooser));
			
			FileFilter filterJPG = new ExtensionFileFilter(null, new String[] {"JPG", "JPEG" });
			fileChooser.addChoosableFileFilter(filterJPG);
			FileFilter filterGIF = new ExtensionFileFilter("gif", new String[] { "gif" });
			fileChooser.addChoosableFileFilter(filterGIF);
			FileFilter filterDOC = new ExtensionFileFilter("doc", new String[] { "doc" });
			fileChooser.addChoosableFileFilter(filterDOC);
			fileChooser.setFileFilter(fileChooser.getAcceptAllFileFilter());
			int status = fileChooser.showOpenDialog(parent);
			
			if (status == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				boolean existe;
				try {
					existe = SessionServiceLocator.getFileManagerSessionService().existeArchivo(
							Parametros.getUrlCarpetaServidor(), selectedFile.getName());
					if (!existe){
						/**
						 * valido que botón ha sido presionado y le asigno al
						 * correspondiente textbox el path del archivo que haya
						 * seleccionado
						 */
						if ((actionEvent.getSource() == getBtnAgregarArchivoDescripcion()))
							getTxtUrlDescripcionOrdenDetalle().setText(fileChooser.getSelectedFile().toString());
						else if ((actionEvent.getSource() == getBtnAgregarArchivoPropuestaOrdenDetalle()))
							getTxtUrlPropuestaOrdenDetalle().setText(fileChooser.getSelectedFile().toString());
						else if ((actionEvent.getSource() == getBtnAgregarArchivoPropuestaOrden())){
							getTxtUrlPropuestaOrden().setText(fileChooser.getSelectedFile().toString());
							getBtnVerArchivoPropuestaOrden().setEnabled(true);
						}else
							getBtnVerArchivoPropuestaOrden().setEnabled(false);
						
						/**
						 * ejecuto el archivo con su respectivo programa para poder ser
						 * previsualizado
						 */
						try {
							int opcion = JOptionPane.showOptionDialog(null, "Desea previsualizar el archivo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
							if (opcion == JOptionPane.YES_OPTION) {
								String filename = selectedFile.getAbsolutePath();
								Archivos.abrirArchivoLocal(filename);
							}
						} catch (IOException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					} else {
						int opcion = JOptionPane.showOptionDialog(null, "Archivo ya existe, desea reemplazarlo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "No");
						if (opcion == JOptionPane.YES_OPTION) {
							if ((actionEvent.getSource() == getBtnAgregarArchivoDescripcion()))
								getTxtUrlDescripcionOrdenDetalle().setText(fileChooser.getSelectedFile().toString());
							else if ((actionEvent.getSource() == getBtnAgregarArchivoPropuestaOrdenDetalle()))
								getTxtUrlPropuestaOrdenDetalle().setText(fileChooser.getSelectedFile().toString());
							else if ((actionEvent.getSource() == getBtnAgregarArchivoPropuestaOrden())){
								getTxtUrlPropuestaOrden().setText(fileChooser.getSelectedFile().toString());
								getBtnVerArchivoPropuestaOrden().setEnabled(true);
							}else
								getBtnVerArchivoPropuestaOrden().setEnabled(false);
						} else {
							if ((actionEvent.getSource() == getBtnAgregarArchivoDescripcion()))
								getTxtUrlDescripcionOrdenDetalle().setText("");
							else if ((actionEvent.getSource() == getBtnAgregarArchivoPropuestaOrdenDetalle()))
								getTxtUrlPropuestaOrdenDetalle().setText("");
							else if ((actionEvent.getSource() == getBtnAgregarArchivoPropuestaOrden())){
								getTxtUrlPropuestaOrden().setText("");
								getBtnVerArchivoPropuestaOrden().setEnabled(false);
							}
							else
								getBtnVerArchivoPropuestaOrden().setEnabled(true);
						}
					}
				} catch (GenericBusinessException e1) {
					e1.printStackTrace();
					SpiritAlert.createAlert(e1.getMessage(), SpiritAlert.ERROR);
				}
			} else if (status == JFileChooser.CANCEL_OPTION) {
				
			}
		}
	};
	
	public void cargarListenersComponents() {
		// Opcion Que Permite Borrar un registro deseado de la lista de
		// productos cliente
		JMenuItem itemEliminarProductoCliente = new JMenuItem("Eliminar");
		popupMenuProductoCliente.add(itemEliminarProductoCliente);
		// Añado el listener de menupopup
		getBtnAgregarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDetalleOrdenTrabajo();
			}
		});
		
		getBtnEliminarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetalleOrdenTrabajo();
			}
		});
		
		getBtnVerArchivoPropuestaOrden().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int opcion = JOptionPane.showOptionDialog(null, "Desea visualizar el archivo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
				if (opcion == JOptionPane.YES_OPTION) {
					try {
						String urlCampanaArchivo = getTxtUrlPropuestaOrden().getText();
						Archivos.abrirArchivoDesdeServidor(urlCampanaArchivo);
					} catch (IOException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}
			}
		});
		
		// Opcion Que Permite Visualizar un archivo deseado de la tabla de
		// archivos
		JMenuItem itemVerCampanaArchivo = new JMenuItem("Visualizar Archivo Url Descripción");
		popupMenuTipoOrdenDetalle.add(itemVerCampanaArchivo);
		// Añado el listener de menupopup
		itemVerCampanaArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				verArchivo(getTxtUrlDescripcionOrdenDetalle().getText());
			}
		});
		
		// Opcion Que Permite Visualizar un archivo deseado de la tabla de
		// archivos
		JMenuItem itemVerCampanaArchivoPropuesta = new JMenuItem("Visualizar Archivo Url Propuesta");
		popupMenuTipoOrdenDetalle.add(itemVerCampanaArchivoPropuesta);
		// Añado el listener de menupopup
		itemVerCampanaArchivoPropuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				verArchivo(getTxtUrlPropuestaOrdenDetalle().getText());
			}
		});
		
		initListenersSave();
	}
	
	private void verArchivo(String urlArchivo) {
		if (getTblOrdenTrabajoDetalle().getSelectedRow() != -1) {
			try {
				String urlCampanaArchivo = urlArchivo;
				Archivos.abrirArchivoDesdeServidor(urlCampanaArchivo);
			} catch (IOException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de la lista a visualizar !!!", SpiritAlert.WARNING);
		}
	}
	
	private void agregarDetalleOrdenTrabajo() {
		if (validateFieldsDetalle()) {
			agregarDetalle();
			Calendar calendarInicio = new GregorianCalendar();
			getCmbFechaLimiteOrdenDetalle().setCalendar(calendarInicio);
			cleanDetalle();
		}
	}
	
	private void cleanDetalle() {
		getCmbTipo().setSelectedIndex(-1);
		getCmbTipo().repaint();	
		getCmbEquipo().setSelectedIndex(-1);
		getCmbEquipo().repaint();	
		getCmbAsignadoAOrdenDetalle().setSelectedIndex(-1);
		getCmbAsignadoAOrdenDetalle().repaint();	
		getCmbSubTipo().setSelectedIndex(-1);
		getCmbSubTipo().repaint();		
		getTxtUrlDescripcionOrdenDetalle().setText("");
		getTxtUrlPropuestaOrdenDetalle().setText("");
		getTxtDescripcionOrdenDetalle().setText("");
		//getCmbEstadoOrdenDetalle().setSelectedItem(NOMBRE_ESTADO_PENDIENTE);
		getCmbEstadoOrdenDetalle().setSelectedItem(EstadoOrdenTrabajo.PENDIENTE);
		getBtnAgregarDetalle().setEnabled(true);
		getBtnActualizarDetalle().setEnabled(true);
	}
	
	private void eliminarDetalleOrdenTrabajo() {
		if (getTblOrdenTrabajoDetalle().getSelectedRow() != -1) {
			int filaSeleccionada = getTblOrdenTrabajoDetalle().getSelectedRow();
			OrdenTrabajoDetalleIf ordenTrabajoDetalle = ordenDetalleColeccion.get(filaSeleccionada);
			if (ordenTrabajoDetalle.getId() != null){
				ordenDetalleEliminadas.add(ordenTrabajoDetalle);
			}
			archivosDescripcionColeccion.remove(getTblOrdenTrabajoDetalle().getSelectedRow());
			archivosPropuestaColeccion.remove(getTblOrdenTrabajoDetalle().getSelectedRow());
			ordenDetalleColeccion.remove(getTblOrdenTrabajoDetalle().getSelectedRow());
			ordenTrabajoDetalleColeccion.remove(getTblOrdenTrabajoDetalle().getSelectedRow());
			modelOrdenDetalle.removeRow(getTblOrdenTrabajoDetalle().getSelectedRow());
			cleanDetalle();
			resetearFechaLimiteyEntrega();
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de Orden Detalle a eliminar !!!", SpiritAlert.WARNING);
		}
	}
	
	public DefaultListModel getModelProductoCliente() {
		return modelProductoCliente;
	}
}
