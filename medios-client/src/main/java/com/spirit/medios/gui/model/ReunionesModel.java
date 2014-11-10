package com.spirit.medios.gui.model;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
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

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteContactoIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.ClienteContactoCriteria;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoArchivoIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.entity.ReunionArchivoData;
import com.spirit.medios.entity.ReunionArchivoIf;
import com.spirit.medios.entity.ReunionAsistenteIf;
import com.spirit.medios.entity.ReunionCompromisoData;
import com.spirit.medios.entity.ReunionCompromisoIf;
import com.spirit.medios.entity.ReunionData;
import com.spirit.medios.entity.ReunionIf;
import com.spirit.medios.entity.ReunionProductoIf;
import com.spirit.medios.gui.criteria.ProductoClienteCriteria;
import com.spirit.medios.gui.criteria.ReunionesCriteria;
import com.spirit.medios.gui.panel.JPReuniones;
import com.spirit.util.Archivos;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.ExtensionFileFilter;
import com.spirit.util.FileInputStreamSerializable;
import com.spirit.util.LabelAccessory;
import com.spirit.util.NumberTextField;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JFrame;

public class ReunionesModel extends JPReuniones {

	private static final long serialVersionUID = -7226444909509735554L;
	
	private JDPopupFinderModel popupFinder;
	private ProductoClienteCriteria productoClienteCriteria;
	private ClienteContactoCriteria clienteContactoCriteria;
	private ClienteCriteria clienteCriteria;
	private EmpleadoCriteria empleadoCriteria;
	private CorporacionCriteria corporacionCriteria;
	final JPopupMenu popupMenuProductoCliente = new JPopupMenu();
	final JPopupMenu popupMenuAsistenteAgencia = new JPopupMenu();
	final JPopupMenu popupMenuAsistenteCliente = new JPopupMenu();
	final JPopupMenu popupMenuReunionArchivo = new JPopupMenu();
	final JPopupMenu popupMenuReunionCompromiso = new JPopupMenu();
	protected ReunionIf reunion;
	protected CorporacionIf corporacionIf;
	protected ClienteIf clienteIf;
	protected ClienteContactoIf clienteContactoIf;
	protected ArrayList lista;
	EmpleadoIf empleadoIf;
	protected ProductoClienteIf productoClienteIf;

	private static final int MAX_LONGITUD_PROSPECTO_CLIENTE = 80;
	private static final int MAX_LONGITUD_PROSPECTO_PRODUCTO_CLIENTE = 30;
	private static final int MAX_LONGITUD_PROSPECTO_ASISTENTE_CLIENTE = 80;
	private static final int MAX_LONGITUD_DESCRIPCION = 1000;
	private static final int MAX_LONGITUD_DESCRIPCION_COMPROMISO = 4000;
	private static final int MAX_LONGITUD_ENVIADOS = 2;
	private static final int MAX_LONGITUD_CON_COPIA = 100;
	private static final int MAX_LONGITUD_LUGAR_REUNION = 60;
	private static final int MAX_LONGITUD_TEMA_TRATADO = 60;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0, 1);
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0, 1);
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String CODIGO_MAXDIASINGRESOREUNION = "MAXDIASINGRESOREUNION";
	private static final String CODIGO_CUENTAS = "CUE";
	
	java.util.Date fechaCreacion = new java.util.Date();
	java.util.Date fechaUltimoEnvio = new java.util.Date();
	java.util.Date fechaCompromiso = new java.util.Date();
	java.util.Date fechaArchivo = new java.util.Date();
	DefaultTableModel modelReunionArchivo, modelReunionCompromiso;
	Vector asistenteAgenciaColeccion = new Vector();
	Vector asistenteClienteColeccion = new Vector();
	Vector<ReunionArchivoIf> archivoReunionColeccion = new Vector<ReunionArchivoIf>();
	Vector<ReunionCompromisoIf> compromisoReunionColeccion = new Vector<ReunionCompromisoIf>();
	Vector<String> filaReunionArchivo = new Vector<String>();
	Vector<File> archivosColeccion = new Vector<File>();
	Vector<String> archivosNombreColeccion = new Vector<String>();
	Vector<ReunionArchivoIf> archivosEliminadosColeccion = new Vector<ReunionArchivoIf>();
	Vector<ReunionCompromisoIf> compromisosEliminadosColeccion = new Vector<ReunionCompromisoIf>();
	String thisName = this.getName();
	JLabel lblFechaUltimoEnvio = new JLabel();
	JTextField txtFechaUltimoEnvio = new JTextField();
	JLabel lblNumeroEnvios = new JLabel();
	JTextField txtNumeroEnvios = new JTextField();
	private List<ProductoClienteIf> productoClienteList = new ArrayList<ProductoClienteIf>();

	
	public ReunionesModel() throws PropertyVetoException {
		anchoColumnasTabla();
		initKeyListeners();
		showSaveMode();
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumnaArchivos = getTableArchivo().getColumnModel().getColumn(0);
		anchoColumnaArchivos.setPreferredWidth(120);
		anchoColumnaArchivos = getTableArchivo().getColumnModel().getColumn(1);
		anchoColumnaArchivos.setPreferredWidth(110);
		anchoColumnaArchivos = getTableArchivo().getColumnModel().getColumn(2);
		anchoColumnaArchivos.setPreferredWidth(60);
		anchoColumnaArchivos = getTableArchivo().getColumnModel().getColumn(3);
		anchoColumnaArchivos.setPreferredWidth(400);
		
		TableColumn anchoColumnaCompromiso = getTableCompromiso().getColumnModel().getColumn(0);
		anchoColumnaCompromiso.setPreferredWidth(110);
		anchoColumnaCompromiso = getTableCompromiso().getColumnModel().getColumn(1);
		anchoColumnaCompromiso.setPreferredWidth(60);
		anchoColumnaCompromiso = getTableCompromiso().getColumnModel().getColumn(2);
		anchoColumnaCompromiso.setPreferredWidth(500);
	}

	private void initKeyListeners() {
		getBtnAgregarAsistenteAgencia().setText("");
		getBtnEliminarAsistenteAgencia().setText("");
		getBtnAgregarAsistenteCliente().setText("");
		getBtnEliminarAsistenteCliente().setText("");
		getBtnAgregarArchivo().setText("");
		getBtnActualizarArchivo().setText("");
		getBtnEliminarArchivo().setText("");
		getBtnAgregarCompromiso().setText("");
		getBtnActualizarCompromiso().setText("");
		getBtnEliminarCompromiso().setText("");
		
		//---- btnBuscarCorporacion ----
		getBtnBuscarCorporacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCorporacion().setToolTipText("Buscar Corporación");		
		//---- btnBuscarCliente ----
		getBtnBuscarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCliente().setToolTipText("Buscar Cliente");
		//---- btnBuscarAsistenteAgencia ----
		getBtnBuscarAsistenteAgencia().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarAsistenteAgencia().setToolTipText("Buscar Empleado");		
		//---- btnAgregarAsistenteAgencia ----
		getBtnAgregarAsistenteAgencia().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarAsistenteAgencia().setToolTipText("Agregar Empleado a la Lista");		
		//---- btnEliminarAsistenteAgencia ----
		getBtnEliminarAsistenteAgencia().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarAsistenteAgencia().setToolTipText("Eliminar Empleado a la Lista");		
		//---- btnBuscarAsistenteCliente ----
		getBtnBuscarAsistenteCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarAsistenteCliente().setToolTipText("Buscar Contacto");		
		//---- btnAgregarAsistenteCliente ----
		getBtnAgregarAsistenteCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarAsistenteCliente().setToolTipText("Agregar Contacto");				
		//---- btnEliminarAsistenteCliente ----
		getBtnEliminarAsistenteCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarAsistenteCliente().setToolTipText("Eliminar Contacto");
		//---- btnAgregarURLArchivoDescripcion ----
		getBtnAgregarURLArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/attachFile.png"));
		getBtnAgregarURLArchivo().setToolTipText("Agregar archivo de Descripcion");		
		//---- btnAgregarArchivo ----
		getBtnAgregarArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarArchivo().setToolTipText("Agregar Archivo");				
		//---- btnActualizarArchivo ----
		getBtnActualizarArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarArchivo().setToolTipText("Actualizar Archivo");		
		//---- btnEliminarArchivo ----
		getBtnEliminarArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarArchivo().setToolTipText("Eliminar Archivo");
		//---- btnAgregarCompromiso ----
		getBtnAgregarCompromiso().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarCompromiso().setToolTipText("Agregar Compromiso");		
		//---- btnActualizarCompromiso ----
		getBtnActualizarCompromiso().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarCompromiso().setToolTipText("Actualizar Compromiso");
		//---- btnEliminarCompromiso ----
		getBtnEliminarCompromiso().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarCompromiso().setToolTipText("Eliminar Compromiso");
		
		//getJtpReuniones().setEnabledAt(0, true);
		//getJtpReuniones().removeTabAt(3);
		getLblFechaUltimoEnvio().setVisible(false);
		getTxtFechaUltimoEnvio().setVisible(false);
		getLblNumeroEnvios().setVisible(false);
		getTxtNumeroEnvios().setVisible(false);
		
		getTxtProspectoCliente().addKeyListener(new TextChecker(MAX_LONGITUD_PROSPECTO_CLIENTE));
		getTxtNumeroEnvios().addKeyListener(new TextChecker(MAX_LONGITUD_ENVIADOS));
		getTxtNumeroEnvios().addKeyListener(new NumberTextField());
		getTxtDescripcion().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION, true, 0));
		getTxtAsistenteCliente().addKeyListener(new TextChecker(MAX_LONGITUD_PROSPECTO_ASISTENTE_CLIENTE, 0));
		getTxtDescripcionCompromiso().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION_COMPROMISO, true, 0));
		
		getTxtConCopia().addKeyListener(new TextChecker(MAX_LONGITUD_CON_COPIA, 0));
		getTxtLugarReunion().addKeyListener(new TextChecker(MAX_LONGITUD_LUGAR_REUNION, 0));
		getTxtTemaTratado().addKeyListener(new TextChecker(MAX_LONGITUD_TEMA_TRATADO, 0));
		
		getCmbFechaCreacion().setLocale(Utilitarios.esLocale);
		getCmbFechaArchivo().setLocale(Utilitarios.esLocale);
		getCmbFechaCompromiso().setLocale(Utilitarios.esLocale);
		getCmbFechaCreacion().setShowNoneButton(false);
		getCmbFechaCreacion().setEditable(false);
		getCmbFechaArchivo().setShowNoneButton(false);
		getCmbFechaArchivo().setEditable(false);
		getCmbFechaCompromiso().setShowNoneButton(false);
		getCmbFechaCompromiso().setEditable(false);
	}
	
	Comparator<EmpleadoIf> ordenadorArrayListPorNombre = new Comparator<EmpleadoIf>(){
		public int compare(EmpleadoIf o1, EmpleadoIf o2) {
			return (o1.getNombres().compareTo(o2.getNombres()));
		}		
	};
	
	private void cargarComboEjecutivos(){
		try {
			/*DepartamentoIf departamentoCuentas = (DepartamentoIf)getDepartamentoSessionService().findDepartamentoByCodigo(CODIGO_CUENTAS).iterator().next();
			Map aMap = new HashMap();
			aMap.put("empresaId", Parametros.getIdEmpresa());
			aMap.put("departamentoId", departamentoCuentas.getId());
			aMap.put("estado", ESTADO_ACTIVO);
			List ejecutivos = (List) getEmpleadoSessionService().findEmpleadoByQuery(aMap);
			Collections.sort(ejecutivos, ordenadorArrayListPorNombre);
			refreshCombo(getCmbEjecutivo(),ejecutivos);*/
			
			List<EmpleadoIf> ejecutivos = new ArrayList<EmpleadoIf>();
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
			//TODO LUEGO DE INGRESAR LOS DATOS INICIALES, CAMBIAR A -1 EL VALOR DE CERO
			Collections.sort((ArrayList)ejecutivos,ordenadorArrayListPorNombre);
			refreshComboByIndex(getCmbEjecutivo(),ejecutivos,-1);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	

	private void setearFechaMinimaIngreso() {
		try {
			if(getMode() == SpiritMode.SAVE_MODE){
				ParametroEmpresaIf parametroMaxDias = (ParametroEmpresaIf)SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByCodigo(CODIGO_MAXDIASINGRESOREUNION).iterator().next();
				int maxDiasIngresoReunion = Integer.valueOf(parametroMaxDias.getValor());
				Calendar fechaActual = new GregorianCalendar();
				int dia = fechaActual.getTime().getDate();
				int mes = fechaActual.getTime().getMonth();
				int anio = fechaActual.getTime().getYear()+1900;
				if((dia-maxDiasIngresoReunion) < 0){
					dia = Utilitarios.getLastDayOfMonth(mes, anio) - (maxDiasIngresoReunion-dia);
					if(mes == 0){
						mes = 11;
						anio = anio - 1;
					}else{
						mes = mes - 1;
					}
				}else{
					dia = dia - maxDiasIngresoReunion;
				}
				getCmbFechaCreacion().getDateModel().setMinDate(new GregorianCalendar(anio,mes,dia));
			}else{
				getCmbFechaCreacion().getDateModel().setMinDate(new GregorianCalendar(1900,0,1));
			}			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void crearListaProductos(){
		productoClienteList = null;
		productoClienteList = new ArrayList<ProductoClienteIf>();
		int[] selected = getCbListProductos().getCheckBoxListSelectedIndices();
		for(int i=0; i<selected.length; i++){
			productoClienteList.add((ProductoClienteIf)getCbListProductos().getModel().getElementAt(selected[i]));
		}
	}
	
	public void save() {
		crearListaProductos();
		if (validateFields()) {
			try {
				ReunionIf reunion = registrarReunion();
				ReunionIf reunionGuardada = SessionServiceLocator.getReunionSessionService().procesarReunion(reunion, productoClienteList, asistenteAgenciaColeccion, asistenteClienteColeccion, compromisoReunionColeccion,getCbCliente().isSelected(), archivosColeccion, archivosNombreColeccion);

				if ( !Parametros.getUrlCarpetaServidor().equals("") ){
					int posicionArchivo = 0;
					String clienteNombre = reunion.getClienteId()!=null?SessionServiceLocator.getClienteSessionService().getCliente(reunion.getClienteId()).getIdentificacion():"PROSPECTO_CLIENTE_"+reunion.getProspectocliente();
					String slashUrl = Parametros.getUrlCarpetaServidor().substring(Parametros.getUrlCarpetaServidor().length()-1,Parametros.getUrlCarpetaServidor().length());
					
					for (File archivo: archivosColeccion){
		    			if(!archivo.getName().equals("")){
		    				try{
		    					FileInputStreamSerializable archivoFuente = new FileInputStreamSerializable(archivo);
		    					int n = SessionServiceLocator.getFileManagerSessionService().guardarArchivoZip(archivoFuente, Parametros.getUrlCarpetaServidor()+clienteNombre.replaceAll(" ", "_")+slashUrl, archivo.getName());
		    					if (n == 3){
		    						SpiritAlert.createAlert("Archivo: "+archivo.getName()+" no se guard\u00f3",SpiritAlert.WARNING);
		    					}
		    				} catch(Exception ex0){
		    					archivoReunionColeccion.remove(posicionArchivo);
		    					if (ex0 instanceof GenericBusinessException)
		    						SpiritAlert.createAlert(ex0.getMessage(),SpiritAlert.ERROR);
		    					else{
		    						ex0.printStackTrace();
		    						SpiritAlert.createAlert("Error al guardar archivo: "+archivo.getName(),SpiritAlert.WARNING);
		    					}
		    				}
		    				posicionArchivo++;
		    			}
		    		}
					String slashRuta = Parametros.getRutaWindowsCarpetaServidor().substring(Parametros.getRutaWindowsCarpetaServidor().length()-1,Parametros.getRutaWindowsCarpetaServidor().length());
					SessionServiceLocator.getReunionSessionService().actualizarArchivosReunion(reunionGuardada,archivoReunionColeccion,archivosEliminadosColeccion,Parametros.getRutaWindowsCarpetaServidor()+clienteNombre.replaceAll(" ", "_")+slashRuta+slashRuta);
				}
				SpiritAlert.createAlert("Reuni\u00f3n guardada con \u00e9xito",SpiritAlert.INFORMATION);
				report();
				showSaveMode();
			} catch (java.io.NotSerializableException ex) {
				ex.printStackTrace();
			} catch (Exception e) {
				SpiritAlert.createAlert("Ocurri\u00f3 un error al guardar la Reuni\u00f3n",SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}

	public void update() {
		crearListaProductos();
		if (validateFields()) {
			try {
				ReunionIf reunion = registrarReunionForUpdate();
				ReunionIf reunionActualizada = SessionServiceLocator.getReunionSessionService().actualizarReunion(reunion, productoClienteList, asistenteAgenciaColeccion, asistenteClienteColeccion, compromisoReunionColeccion,getCbCliente().isSelected(), archivosColeccion, compromisosEliminadosColeccion);
				
				if ( !Parametros.getUrlCarpetaServidor().equals("") ){
					int posicionArchivo = 0;
					String clienteNombre = reunion.getClienteId()!=null?SessionServiceLocator.getClienteSessionService().getCliente(reunion.getClienteId()).getIdentificacion():"PROSPECTO_CLIENTE_"+reunion.getProspectocliente();
					String slashUrl = Parametros.getUrlCarpetaServidor().substring(Parametros.getUrlCarpetaServidor().length()-1,Parametros.getUrlCarpetaServidor().length());
					
					for (File archivo: archivosColeccion){
		    			if(archivo!=null && !archivo.getName().equals("")){
		    				try{
		    					FileInputStreamSerializable archivoFuente = new FileInputStreamSerializable(archivo);
		    					int n = SessionServiceLocator.getFileManagerSessionService().guardarArchivoZip(archivoFuente, Parametros.getUrlCarpetaServidor()+clienteNombre.replaceAll(" ", "_")+slashUrl, archivo.getName());
		    					if (n == 3){
		    						SpiritAlert.createAlert("Archivo: "+archivo.getName()+" no se guard\u00f3",SpiritAlert.WARNING);
		    					}
		    				} catch(Exception ex0){
		    					archivoReunionColeccion.remove(posicionArchivo);
		    					if (ex0 instanceof GenericBusinessException)
		    						SpiritAlert.createAlert(ex0.getMessage(),SpiritAlert.ERROR);
		    					else{
		    						ex0.printStackTrace();
		    						SpiritAlert.createAlert("Error al guardar archivo: "+archivo.getName(),SpiritAlert.WARNING);
		    					}
		    				}
		    			}
	    				posicionArchivo++;
		    		}
					String slashRuta = Parametros.getRutaWindowsCarpetaServidor().substring(Parametros.getRutaWindowsCarpetaServidor().length()-1,Parametros.getRutaWindowsCarpetaServidor().length());
					SessionServiceLocator.getReunionSessionService().actualizarArchivosReunion(reunionActualizada,archivoReunionColeccion,archivosEliminadosColeccion,Parametros.getRutaWindowsCarpetaServidor()+clienteNombre.replaceAll(" ", "_")+slashRuta+slashRuta);
				}
				
				SpiritAlert.createAlert("Reunión actualizada con éxito",SpiritAlert.INFORMATION);
				report();
				
			} catch (Exception e) {
				SpiritAlert.createAlert("Error al actualizar la Reunión!",SpiritAlert.ERROR);
				e.printStackTrace();
			}
			showSaveMode();
		}
	}

	public void delete() {
		try {
			SessionServiceLocator.getReunionSessionService().eliminarReunion(reunion.getId());
			SpiritAlert.createAlert("Reunión eliminada con éxito",SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al eliminar la Reunión!",SpiritAlert.ERROR);
		}
	}

	private ReunionIf registrarReunion() throws GenericBusinessException {

		ReunionData data = new ReunionData();

		data.setOficinaId(Parametros.getIdOficina());
		data.setFecha(new java.sql.Date(getCmbFechaCreacion().getDate().getTime()));
		data.setFechaEnvio(new java.sql.Date(fechaUltimoEnvio.getYear(),fechaUltimoEnvio.getMonth(), fechaUltimoEnvio.getDate()));
		data.setNumEnviados(Integer.valueOf(getTxtNumeroEnvios().getText()));
		data.setEstado(this.getCmbEstadoReunion().getSelectedItem().toString().substring(0, 1));
		data.setDescripcion(getTxtDescripcion().getText());
		data.setUsuarioCreacionId(((UsuarioIf)Parametros.getUsuarioIf()).getId());
		data.setFechaCreacion(new java.sql.Date((new Date()).getTime()));
		data.setEjecutivoId(((EmpleadoIf)getCmbEjecutivo().getSelectedItem()).getId());
		data.setLugarReunion(getTxtLugarReunion().getText());
		
		if(getTxtConCopia().getText() != null){
			data.setConCopia(getTxtConCopia().getText());
		}
		
		if (getCbCliente().isSelected())
			data.setClienteId(clienteIf.getId());
		else
			data.setProspectocliente(getTxtProspectoCliente().getText());

		return data;
	}

	private ReunionIf registrarReunionForUpdate() {

		reunion.setOficinaId(Parametros.getIdOficina());
		reunion.setFecha(new java.sql.Date(getCmbFechaCreacion().getDate().getTime()));
		reunion.setEstado(this.getCmbEstadoReunion().getSelectedItem().toString().substring(0, 1));
		reunion.setDescripcion(getTxtDescripcion().getText());
		reunion.setEjecutivoId(((EmpleadoIf)getCmbEjecutivo().getSelectedItem()).getId());
		reunion.setLugarReunion(getTxtLugarReunion().getText());
		
		if(getTxtConCopia().getText() != null){
			reunion.setConCopia(getTxtConCopia().getText());
		}
		
		if (getCbCliente().isSelected()) {
			reunion.setClienteId(clienteIf.getId());
			reunion.setProspectocliente(null);
		} else {
			reunion.setClienteId(null);
			reunion.setProspectocliente(getTxtProspectoCliente().getText());
		}
		
		reunion.setUsuarioActualizacionId(((UsuarioIf)Parametros.getUsuarioIf()).getId());
		reunion.setFechaActualizacion(new java.sql.Date((new Date()).getTime()));

		return reunion;
	}

	public boolean validateFields() {
		String strCorporacion = this.getTxtCorporacion().getText();
		String strCliente = this.getTxtCliente().getText();
		String strProspectoCliente = this.getTxtProspectoCliente().getText();
		String strDescripcion = this.getTxtDescripcion().getText();

		if (getCbCliente().isSelected()) {
			if ((("".equals(strCorporacion)) || (strCorporacion == null))) {
				SpiritAlert.createAlert("Debe ingresar una Corporación para la reunión!",SpiritAlert.WARNING);
				getJtpReuniones().setSelectedIndex(0);
				getBtnBuscarCorporacion().grabFocus();
				return false;
			}

			if ((("".equals(strCliente)) || (strCliente == null))) {
				SpiritAlert.createAlert("Debe ingresar un Cliente para la reunión!",SpiritAlert.WARNING);
				getJtpReuniones().setSelectedIndex(0);
				getBtnBuscarCliente().grabFocus();
				return false;
			}
		} else {
			if ((("".equals(strProspectoCliente)) || (strProspectoCliente == null))) {
				SpiritAlert.createAlert("Debe ingresar un Prospecto de Cliente para la reunión!",SpiritAlert.WARNING);
				getJtpReuniones().setSelectedIndex(0);
				getTxtProspectoCliente().grabFocus();
				return false;
			}
		}
		
		if (getCmbEjecutivo().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar un(a) Ejecutivo(a)!",SpiritAlert.WARNING);
			getJtpReuniones().setSelectedIndex(0);
			getCmbEjecutivo().grabFocus();
			return false;
		}
		
		if ((("".equals(getTxtLugarReunion().getText())) || (getTxtLugarReunion().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar el Lugar de Reunión !",SpiritAlert.WARNING);
			getJtpReuniones().setSelectedIndex(0);
			getTxtLugarReunion().grabFocus();
			return false;
		}

		if ((("".equals(strDescripcion)) || (strDescripcion == null))) {
			SpiritAlert.createAlert("Debe ingresar los Temas Tratados para la reunión!",SpiritAlert.WARNING);
			getJtpReuniones().setSelectedIndex(0);
			getTxtDescripcion().grabFocus();
			return false;
		}

		if (getCmbEstadoReunion().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un Estado para la reunión!",SpiritAlert.WARNING);
			getJtpReuniones().setSelectedIndex(0);
			getCmbEstadoReunion().grabFocus();
			return false;
		}
		
		if (getCbCliente().isSelected() && (productoClienteList.size() == 0)) {
			SpiritAlert.createAlert("Debe ingresar al menos un Producto para la reunión!", SpiritAlert.WARNING);
			getJtpReuniones().setSelectedIndex(1);
			getCbListProductos().grabFocus();
			return false;
		}

		if (asistenteAgenciaColeccion.size() == 0) {
			SpiritAlert.createAlert("Debe ingresar los Asistentes por la Agencia para la reunión!",SpiritAlert.WARNING);
			getJtpReuniones().setSelectedIndex(2);
			getBtnBuscarAsistenteAgencia().grabFocus();
			return false;
		}

		if (asistenteClienteColeccion.size() == 0) {
			SpiritAlert.createAlert("Debe ingresar los Asistentes por el Cliente para la reunión!",SpiritAlert.WARNING);
			getJtpReuniones().setSelectedIndex(2);
			getBtnBuscarAsistenteCliente().grabFocus();
			return false;
		}

		if (compromisoReunionColeccion.size() == 0) {
			SpiritAlert.createAlert("Debe ingresar los Acuerdos establecidos para la reunión!", SpiritAlert.WARNING);
			getJtpReuniones().setSelectedIndex(4);
			getCmbFechaCompromiso().grabFocus();
			return false;
		}
		
		if (Parametros.getUrlCarpetaServidor().equals("")){
			Object[] options = {"   Si   ",	"   No   "};
    		int n = JOptionPane.showOptionDialog(Parametros.getMainFrame(),
    				"No est\u00e1 definida carpeta de destino de archivos en el servidor." +
    				"\nArchivo(s) agregados no se guardar\u00e1n" +
    				"¿ Desea guardar el resto de la informaci\u00f3n ?",
    				"Confirmaci\u00f3n",
    				JOptionPane.YES_NO_OPTION,
    				JOptionPane.QUESTION_MESSAGE,
    				null,
    				options,
    				options[0]);
    		if (n==1)
    			return false;
		}

		return true;
	}

	private Map buildQuery() {
		Map aMap = new HashMap();

		if (getCbCliente().isSelected() && clienteIf != null)
			aMap.put("clienteId", clienteIf.getId());
		
		if (!getCbCliente().isSelected() && !("".equals(getTxtProspectoCliente().getText())))
			aMap.put("prospectocliente", "%" + getTxtProspectoCliente().getText() + "%");
		/*else if (!getCbCliente().isSelected())
			aMap.put("prospectocliente", "%");
*/
		if ("".equals(getTxtDescripcion().getText()) == false)
			aMap.put("descripcion", "%" + getTxtDescripcion().getText() + "%");
		/*else
			aMap.put("descripcion", "%");*/
		
		if (getCmbEjecutivo().getSelectedIndex() != -1)
			aMap.put("ejecutivoId", ((EmpleadoIf)getCmbEjecutivo().getSelectedItem()).getId());
		
		if (getCmbFechaCreacion().getSelectedItem() != null)
			aMap.put("fecha", new java.sql.Date(getCmbFechaCreacion().getDate().getTime()));

		return aMap;
	}

	public void find() {
		try {
			Map mapa = buildQuery();
			int tamanoLista = SessionServiceLocator.getReunionSessionService().findReunionByQuerySize(mapa, Parametros.getIdEmpresa());
			if (tamanoLista > 0) {
				ReunionesCriteria reunionesCriteria = new ReunionesCriteria("Reuniones");
				reunionesCriteria.setResultListSize(tamanoLista);
				reunionesCriteria.setQueryBuilded(mapa);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);	
				anchoColumnas.add(380);	
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	reunionesCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null )
					getSelectedObject();

			} else {
				SpiritAlert.createAlert("No se encontraron registros" ,SpiritAlert.WARNING);
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		}
	}

	public boolean isEmpty() {
		if (getCmbFechaCreacion().getDate() == null
				&& "".equals(this.getTxtCliente().getText())
				&& "".equals(this.getTxtProspectoCliente().getText())
				&& "".equals(this.getTxtDescripcion().getText())) {
			return true;

		} else {

			return false;
		}
	}

	public void clean() {
		corporacionIf = null;
		clienteIf = null;
		productoClienteList = null;
		productoClienteList = new ArrayList<ProductoClienteIf>();

		cleanListaProductos();
		getModelAsistenteAgencia().clear();
		getModelAsistenteCliente().clear();

		if (archivoReunionColeccion.size() != 0) {
			// Vacio la tabla de reunion archivo
			for (int i = getTableArchivo().getRowCount(); i > 0; --i)
				modelReunionArchivo.removeRow(i - 1);
		}

		if (compromisoReunionColeccion.size() != 0) {
			for (int i = getTableCompromiso().getRowCount(); i > 0; --i)
				modelReunionCompromiso.removeRow(i - 1);
		}

		asistenteAgenciaColeccion = new Vector();
		asistenteClienteColeccion = new Vector();
		archivoReunionColeccion = new Vector<ReunionArchivoIf>();
		compromisoReunionColeccion = new Vector<ReunionCompromisoIf>();
		archivosColeccion = new Vector<File>();
		archivosNombreColeccion = new Vector<String>();
		archivosEliminadosColeccion = new Vector<ReunionArchivoIf>();
		compromisosEliminadosColeccion = new Vector<ReunionCompromisoIf>();
		
		this.getTxtAsistenteAgencia().setText("");
		this.getTxtAsistenteCliente().setText("");
		this.getTxtAsistenteCliente().setText("");
		this.getTxtCliente().setText("");
		this.getTxtCorporacion().setText("");
		this.getTxtDescripcionCompromiso().setText("");
		this.getTxtTemaTratado().setText("");
		Calendar currentDate = Calendar.getInstance();
		this.getCmbFechaCreacion().setCalendar(currentDate);
		this.getTxtFechaUltimoEnvio().setText("");
		this.getTxtDescripcion().setText("");
		this.getTxtNumeroEnvios().setText("");
		this.getTxtProspectoCliente().setText("");
		this.getTxtURLArchivo().setText("");
		this.getCmbTipoArchivo().setSelectedItem("");
		this.getCmbTipoArchivo().removeAllItems();
		this.getCmbFechaArchivo().removeActionListener(oActionListenerCmbFecha);
		this.getCmbFechaArchivo().setCalendar(currentDate);
		this.getCmbFechaCompromiso().removeActionListener(oActionListenerCmbFecha);
		this.getCmbFechaCompromiso().setCalendar(currentDate);
		this.getTxtLugarReunion().setText("");
		this.getTxtConCopia().setText("");
		getCmbEstadoReunion().setSelectedItem("");
		getCmbEstadoReunion().removeAllItems();
		getCmbEstadoArchivo().setSelectedItem("");
		getCmbEstadoArchivo().removeAllItems();
		getCmbEstadoCompromiso().setSelectedItem("");
		getCmbEstadoCompromiso().removeAllItems();
	}

	public void showSaveMode() {
		setSaveMode();
		//setearFechaMinimaIngreso();
		clean();
		getCbCliente().setBackground(Parametros.saveUpdateColor);
		getCmbFechaCreacion().setBackground(Parametros.saveUpdateColor);
		getTxtProspectoCliente().setBackground(Parametros.saveUpdateColor);
		getTxtDescripcion().setBackground(Parametros.saveUpdateColor);
		getCmbEjecutivo().setBackground(Parametros.saveUpdateColor);
		getCbCliente().setSelected(false);
		getTxtCorporacion().setEditable(false);
		getTxtCliente().setEditable(false);
		getBtnBuscarCorporacion().setEnabled(false);
		getBtnBuscarCliente().setEnabled(false);
		getCmbFechaArchivo().setEnabled(true);
		getCmbFechaCompromiso().setEnabled(true);
		getTxtFechaUltimoEnvio().setEditable(false);
		getBtnAgregarURLArchivo().setEnabled(true);
		getBtnAgregarArchivo().setEnabled(true);
		getBtnAgregarCompromiso().setEnabled(true);
		getTxtDescripcionCompromiso().setEditable(true);
		getTxtAsistenteAgencia().setEditable(false);
		getBtnBuscarAsistenteAgencia().setEnabled(true);
		getBtnBuscarAsistenteCliente().setEnabled(false);
		getTxtAsistenteCliente().setEditable(true);
		getBtnAgregarAsistenteAgencia().setEnabled(false);
		getCmbTipoArchivo().setEnabled(true);
		getBtnAgregarAsistenteCliente().setEnabled(true);
		getCmbEstadoReunion().setEnabled(true);
		getCmbEstadoArchivo().setEnabled(true);
		getCmbEstadoCompromiso().setEnabled(true);
		getTxtProspectoCliente().setEnabled(true);

		fechaArchivo = fechaCreacion;
		fechaCompromiso = fechaCreacion;
		Calendar calendarFechaCreacion = new GregorianCalendar();
		calendarFechaCreacion.setTime(fechaCreacion);
		getCmbFechaCreacion().setCalendar(calendarFechaCreacion);
		Calendar calendarFechaArchivo = new GregorianCalendar();
		calendarFechaArchivo.setTime(fechaArchivo);
		getCmbFechaArchivo().setCalendar(calendarFechaArchivo);
		Calendar calendarFechaCompromiso = new GregorianCalendar();
		calendarFechaCompromiso.setTime(fechaCompromiso);
		getCmbFechaCompromiso().setCalendar(calendarFechaCompromiso);

		// Seteo la fecha de ultimo envio a la fecha de hoy, debido a que es
		// primera vez
		getTxtFechaUltimoEnvio().setEditable(false);
		getTxtFechaUltimoEnvio().setText(Utilitarios.fechaAhora());

		// Seteo el valor de NUmero de veces enviados a 0
		getTxtNumeroEnvios().setText("0");

		cargarCombos();
		filaReunionArchivo.clear();
		
		getJtpReuniones().setSelectedIndex(0);
		getCbCliente().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		//setearFechaMinimaIngreso();
		getCbCliente().setBackground(Parametros.saveUpdateColor);
		getCmbFechaCreacion().setBackground(Parametros.saveUpdateColor);
		getTxtDescripcion().setBackground(Parametros.saveUpdateColor);
		getCmbEjecutivo().setBackground(Parametros.saveUpdateColor);
		getTxtCorporacion().setEditable(false);
		getTxtFechaUltimoEnvio().setEditable(false);
		getCmbFechaArchivo().setEnabled(true);
		getCmbFechaCompromiso().setEnabled(true);
		getTxtFechaUltimoEnvio().setEditable(false);
		getBtnAgregarURLArchivo().setEnabled(true);
		getBtnAgregarArchivo().setEnabled(true);
		getBtnAgregarCompromiso().setEnabled(true);
		getTxtDescripcionCompromiso().setEditable(true);
		getTxtAsistenteAgencia().setEditable(false);
		getBtnBuscarAsistenteAgencia().setEnabled(true);
		getBtnAgregarAsistenteAgencia().setEnabled(false);
		getCmbTipoArchivo().setEnabled(true);
		getCmbEstadoReunion().setEnabled(true);
		getCmbEstadoArchivo().setEnabled(true);
		getCmbEstadoCompromiso().setEnabled(true);
		// Veo si la reunion leida de la base trabaja con un cliente
		if (reunion.getClienteId() != null) {
			getTxtProspectoCliente().setEnabled(false);
			getBtnBuscarCorporacion().setEnabled(true);
			getBtnBuscarCliente().setEnabled(true);
			getBtnBuscarAsistenteCliente().setEnabled(true);
			getTxtAsistenteCliente().setEditable(false);
			getBtnAgregarAsistenteCliente().setEnabled(false);

		}
		// Sino trabajo con el prospecto cliente
		else {
			getTxtProspectoCliente().setEnabled(true);
			getBtnBuscarCorporacion().setEnabled(false);
			getBtnBuscarCliente().setEnabled(false);
			getBtnBuscarAsistenteCliente().setEnabled(false);
			getTxtAsistenteCliente().setEditable(true);
			getBtnAgregarAsistenteCliente().setEnabled(true);
		}

		this.getCmbFechaArchivo().removeActionListener(oActionListenerCmbFecha);
		this.getCmbFechaCompromiso().removeActionListener(
				oActionListenerCmbFecha);

		Calendar calendarFechaArchivo = new GregorianCalendar();
		calendarFechaArchivo.setTime(fechaArchivo);
		getCmbFechaArchivo().setCalendar(calendarFechaArchivo);

		Calendar calendarFechaCompromiso = new GregorianCalendar();
		calendarFechaCompromiso.setTime(fechaCompromiso);
		getCmbFechaCompromiso().setCalendar(calendarFechaCompromiso);

		getJtpReuniones().setSelectedIndex(0);
		getCbCliente().grabFocus();
	}

	public void showFindMode() {
		clienteIf = null;
		//setearFechaMinimaIngreso();
		getCbCliente().setBackground(Parametros.findColor);
		getCmbFechaCreacion().setBackground(Parametros.findColor);
		getTxtProspectoCliente().setBackground(Parametros.findColor);
		getTxtDescripcion().setBackground(Parametros.findColor);
		getCmbEjecutivo().setBackground(Parametros.findColor);
		getCbCliente().setSelected(false);
		getTxtCorporacion().setEditable(false);
		getTxtCliente().setEditable(false);
		getTxtProspectoCliente().setEnabled(true);
		getTxtDescripcion().setEditable(true);
		getBtnBuscarCorporacion().setEnabled(false);
		getBtnBuscarCliente().setEnabled(false);
		getCmbFechaArchivo().setEnabled(false);
		getCmbFechaCompromiso().setEnabled(false);
		getTxtFechaUltimoEnvio().setEditable(false);
		getBtnAgregarURLArchivo().setEnabled(false);
		getBtnAgregarArchivo().setEnabled(false);
		getBtnAgregarCompromiso().setEnabled(false);
		getTxtDescripcionCompromiso().setEditable(false);
		getTxtAsistenteAgencia().setEditable(false);
		getBtnBuscarAsistenteAgencia().setEnabled(false);
		getBtnBuscarAsistenteCliente().setEnabled(false);
		getTxtAsistenteCliente().setEditable(false);
		getBtnAgregarAsistenteAgencia().setEnabled(false);
		getBtnAgregarAsistenteCliente().setEnabled(false);
		getCmbTipoArchivo().setEnabled(false);
		getCmbEstadoReunion().setEnabled(false);
		getCmbEstadoArchivo().setEnabled(false);
		getCmbEstadoCompromiso().setEnabled(false);
		getCmbEjecutivo().setSelectedIndex(-1);
		getCmbFechaCreacion().setCalendar(null);

		getJtpReuniones().setSelectedIndex(0);
		getCbCliente().grabFocus();
	}
	
	private void cleanListaProductos() {
		if(getCbListProductos().getModel().getSize()>0){
			getCbListProductos().getCheckBoxListSelectionModel().clearSelection();
			((DefaultListModel) getCbListProductos().getModel()).removeAllElements();
		}		
	}

	public void cargarCombos() {
		//Seteo del formato de combos de fecha
		getCmbFechaCreacion().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaArchivo().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaCompromiso().setFormat(Utilitarios.setFechaUppercase());

		// Veo si las fechas son validas, es decir si es que no es antes del
		// dia de hoy
		getCmbFechaCompromiso().addActionListener(oActionListenerCmbFecha);
		getCmbFechaArchivo().addActionListener(oActionListenerCmbFecha);
		getCmbEstadoReunion().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbEstadoReunion().addItem(NOMBRE_ESTADO_INACTIVO);
		getCmbEstadoArchivo().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbEstadoArchivo().addItem(NOMBRE_ESTADO_INACTIVO);
		getCmbEstadoCompromiso().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbEstadoCompromiso().addItem(NOMBRE_ESTADO_INACTIVO);
		cargarComboTipoArchivo();
		cargarComboEjecutivos();
	}

	private void cargarComboTipoArchivo()  {
		try {
			SpiritComboBoxModel cmbModelTipoArchivo = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getTipoArchivoSessionService().getTipoArchivoList());
			getCmbTipoArchivo().setModel(cmbModelTipoArchivo);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	Comparator<ProductoClienteIf> ordenadorProductoClienteIf = new Comparator<ProductoClienteIf>(){
		public int compare(ProductoClienteIf o1, ProductoClienteIf o2) {
			return o1.getMarcaProductoNombre().compareTo(o2.getMarcaProductoNombre());
		}		
	};
	
	private void cargarListaProductos(){
		try {
			if((clienteIf != null) && (SessionServiceLocator.getProductoClienteSessionService().findProductoClienteByClienteId(clienteIf.getId()).size()>0)){
				DefaultListModel model = new DefaultListModel();
				List productosCliente = (ArrayList)SessionServiceLocator.getProductoClienteSessionService().findProductoClienteByClienteId(clienteIf.getId());
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
	
	// Takes a list of flavors and returns the HTML Unicode string flavor
	/*private static DataFlavor getHtmlFlavor(DataFlavor[] listOfFlavors) {
	for (int i = 0; i < listOfFlavors.length; i++) {
			String s = "text/html; class=java.lang.String; charset=Unicode";
			try {
				if (listOfFlavors[i].equals(new DataFlavor(s))) {
					return listOfFlavors[i];
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}*/

	public void initListeners() {
		/*getTxtDescripcion().addInputMethodListener(new InputMethodListener() {
			public void inputMethodTextChanged(InputMethodEvent arg0) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				DataFlavor availableFlavors[] = clipboard.getAvailableDataFlavors();
				DataFlavor htmlFlavor = getHtmlFlavor(availableFlavors);
				if (htmlFlavor != null) {
					try {
						String s = (String) (clipboard.getData(htmlFlavor));
						System.out.println(s);
						//getTxtDescripcion().setText(s);

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("No HTML Unicode String Flavor found");
				}
			}

			public void caretPositionChanged(InputMethodEvent event) {
				// TODO Auto-generated method stub
				
			}

		});*/
		
		getBtnSeleccionarTodo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCbListProductos().getCheckBoxListSelectionModel().addSelectionInterval(0, getCbListProductos().getModel().getSize() - 1);
			}
		});
		
		getBtnDeseleccionarTodo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCbListProductos().getCheckBoxListSelectionModel().clearSelection();
			}
		});
		
		// Opcion Que Permite Borrar un regitsro deseado de la lista de
		// asistentes de agencia
		JMenuItem itemEliminarAsistenteAgencia = new JMenuItem("Eliminar");
		popupMenuAsistenteAgencia.add(itemEliminarAsistenteAgencia);
		// Añado el listener de menupopup
		itemEliminarAsistenteAgencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarAsistenteAgenciaReunion();
			}
		});

		// Listenner de la lista de asistente de Agencia
		getListAsistenteAgencia().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				getTxtAsistenteAgencia().setText(
						getModelAsistenteAgencia().get(
								getListAsistenteAgencia().getSelectedIndex())
								.toString());
			}

			public void mouseReleased(MouseEvent evt) {
				if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3)
						&& getModelAsistenteAgencia().getSize() > 0) {
					popupMenuAsistenteAgencia.show(evt.getComponent(), evt
							.getX(), evt.getY());
				}
			}
		});

		// Opcion Que Permite Borrar un regitsro deseado de la lista de
		// asistentes cliente
		JMenuItem itemEliminarAsistenteCliente = new JMenuItem("Eliminar");
		popupMenuAsistenteCliente.add(itemEliminarAsistenteCliente);
		// Añado el listener de menupopup
		itemEliminarAsistenteCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarAsistenteClienteReunion();
			}
		});

		// Listenner de la lista de asistentes cliente
		getListAsistenteCliente().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				getTxtAsistenteCliente().setText(getModelAsistenteCliente().get(getListAsistenteCliente().getSelectedIndex()).toString());
			}

			public void mouseReleased(MouseEvent evt) {
				if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3)
						&& getModelAsistenteCliente().getSize() > 0) {
					popupMenuAsistenteCliente.show(evt.getComponent(), evt
							.getX(), evt.getY());
				}
			}
		});

		// Opcion Que Permite Borrar un regitsro deseado de la tabla de archivo
		JMenuItem itemEliminarReunionArchivo = new JMenuItem("Eliminar");
		popupMenuReunionArchivo.add(itemEliminarReunionArchivo);
		// Añado el listener de menupopup
		itemEliminarReunionArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarArchivoReunion();
			}
		});

		// Opcion Que Permite Visualizar un archivo deseado de la tabla de
		// archivos
		JMenuItem itemVerReunionArchivo = new JMenuItem("Visualizar Archivo");
		popupMenuReunionArchivo.add(itemVerReunionArchivo);
		// Añado el listener de menupopup
		itemVerReunionArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getTableArchivo().getSelectedRow() != -1) {
					try {
						String urlReunionArchivo = (String) getTableArchivo().getModel().getValueAt(getTableArchivo().getSelectedRow(), 3);
						//String nombreArchivo = "\""+urlReunionArchivo.split("\\\\")[8]+"\"";
						//String ruta = urlReunionArchivo.replaceAll(urlReunionArchivo.split("\\\\")[8],nombreArchivo);
						Archivos.abrirArchivoDesdeServidor(urlReunionArchivo);
					} catch (IOException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				} else {
					SpiritAlert.createAlert("Debe elegir el registro de la lista a visualizar !",SpiritAlert.WARNING);
				}
			}
		});

		// Listenner de la tabla de reunion archivo
		getTableArchivo().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if (getTableArchivo().getSelectedRow() != -1) {
					for (int i = 0; i < getCmbTipoArchivo().getItemCount(); i++) {
						TipoArchivoIf bean = (TipoArchivoIf) getCmbTipoArchivo()
								.getItemAt(i);
						if (bean.getNombre().compareTo(
								getTableArchivo().getModel().getValueAt(
										getTableArchivo().getSelectedRow(), 0)
										.toString()) == 0)
							getCmbTipoArchivo().setSelectedItem(bean);
							getCmbTipoArchivo().repaint();
					}

					// Extraigo el objeto reunion archivo de la coleccion segun
					// el registro seleccionado de la tabla
					ReunionArchivoIf reunionArchivoTemp = (ReunionArchivoIf) archivoReunionColeccion
							.get(getTableArchivo().getSelectedRow());
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(reunionArchivoTemp.getFecha());
					// Muestro la fecha
					getCmbFechaArchivo().removeActionListener(
							oActionListenerCmbFecha);
					getCmbFechaArchivo().setCalendar(calendar);
					getCmbFechaArchivo().addActionListener(
							oActionListenerCmbFecha);

					// Muestro el estado
					if (NOMBRE_ESTADO_ACTIVO.equals(getTableArchivo()
							.getModel().getValueAt(
									getTableArchivo().getSelectedRow(), 2)
							.toString()))
						getCmbEstadoArchivo().setSelectedItem(
								NOMBRE_ESTADO_ACTIVO);
					else
						getCmbEstadoArchivo().setSelectedItem(
								NOMBRE_ESTADO_INACTIVO);

					// Muestro el URL
					getTxtURLArchivo().setText(
							getTableArchivo().getModel().getValueAt(
									getTableArchivo().getSelectedRow(), 3)
									.toString());
				}
			}

			public void mouseReleased(MouseEvent evt) {
				if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3)
						&& getTableArchivo().getModel().getRowCount() > 0) {
					popupMenuReunionArchivo.show(evt.getComponent(),
							evt.getX(), evt.getY());
				}
			}
		});

		// Opcion Que Permite Borrar un regitsro deseado de la tabla de
		// Compromiso
		JMenuItem itemEliminarReunionCompromiso = new JMenuItem("Eliminar");
		popupMenuReunionCompromiso.add(itemEliminarReunionCompromiso);
		// Añado el listener de menupopup
		itemEliminarReunionCompromiso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarCompromisoReunion();
			}
		});

		// Listenner de la tabla de reunion Compromiso
		getTableCompromiso().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if (getTableCompromiso().getSelectedRow() != -1) {
					// Extraigo el objeto reunion compromiso de la coleccion
					// segun el registro seleccionado de la tabla
					ReunionCompromisoIf reunionCompromisoTemp = (ReunionCompromisoIf) compromisoReunionColeccion.get(getTableCompromiso().getSelectedRow());

					Calendar calendar = new GregorianCalendar();
					calendar.setTime(reunionCompromisoTemp.getFecha());

					// Muestro la fecha y la descripcion en pantalla
					getCmbFechaCompromiso().removeActionListener(oActionListenerCmbFecha);
					getCmbFechaCompromiso().setCalendar(calendar);
					getCmbFechaCompromiso().addActionListener(oActionListenerCmbFecha);

					// Muestro el estado
					if (NOMBRE_ESTADO_ACTIVO.equals(getTableCompromiso().getModel().getValueAt(getTableCompromiso().getSelectedRow(), 1).toString()))
						getCmbEstadoCompromiso().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
					else
						getCmbEstadoCompromiso().setSelectedItem(NOMBRE_ESTADO_INACTIVO);

					getTxtDescripcionCompromiso().setText(reunionCompromisoTemp.getDescripcion());
					getTxtTemaTratado().setText(reunionCompromisoTemp.getTemaTratado());
				}
			}

			public void mouseReleased(MouseEvent evt) {
				if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTableCompromiso().getModel().getRowCount() > 0) {
					popupMenuReunionCompromiso.show(evt.getComponent(), evt.getX(), evt.getY());
				}
			}
		});

		// Manejo los eventos de Buscar Corporación
		getBtnBuscarCorporacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(500);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null ){
					corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
					getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
					getTxtCliente().setText("");
					cleanListaProductos();
				}
			}
		});

		// Manejo los eventos de Buscar Cliente
		getBtnBuscarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;

				if (corporacionIf != null)
					idCorporacion = corporacionIf.getId();

				clienteCriteria = new ClienteCriteria("Clientes", idCorporacion, CODIGO_TIPO_CLIENTE);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null ){
					clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
					cleanListaProductos();
					cargarListaProductos();
					
					if (corporacionIf == null) {
						try {
							corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
							getTxtCorporacion().setText(corporacionIf.getCodigo() + " - "+ corporacionIf.getNombre());
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					getBtnBuscarAsistenteCliente().setEnabled(true);
					getTxtAsistenteCliente().setText("");
				}
			}
		});

		// Mando a cargar el popup de busqueda para los asistentes por agencia
		// (empleados)
		getBtnBuscarAsistenteAgencia().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoCriteria = new EmpleadoCriteria(
					"Empleados de "+ Parametros.getNombreEmpresa(),Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
										empleadoCriteria,
										JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getTxtAsistenteAgencia().setText(empleadoIf.getCodigo() + " - "
						+ empleadoIf.getApellidos() + " "+ empleadoIf.getNombres());
					// Habilito el boton de agregar asistente a agencia
					getBtnAgregarAsistenteAgencia().setEnabled(true);
					agregarAsistenteAgenciaReunion();
				}
			}
		});

		// ************Agregar a Lista Asistentes por
		// Agencia*******************//
		getBtnAgregarAsistenteAgencia().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarAsistenteAgenciaReunion();
			}
		});
		
		getBtnEliminarAsistenteAgencia().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarAsistenteAgenciaReunion();
			}
		});

		// Mando a cargar el popup de busqueda para los asistentes por cliente
		// (contacto cliente)
		getBtnBuscarAsistenteCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				clienteContactoCriteria = new ClienteContactoCriteria("Contactos de Cliente ", clienteIf.getId());
				popupFinder = new  JDPopupFinderModel(Parametros.getMainFrame(),clienteContactoCriteria,JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					clienteContactoIf = (ClienteContactoIf) popupFinder.getElementoSeleccionado();
					getTxtAsistenteCliente().setText(clienteContactoIf.getNombre());
					// Habilito el boton de agregar asistente por cliente
					getBtnAgregarAsistenteCliente().setEnabled(true);
					agregarAsistenteClienteReunion();
				}
			}
		});

		// ************Agregar a Lista Asistente Cliente*******************//
		getBtnAgregarAsistenteCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarAsistenteClienteReunion();
			}
		});
		
		getBtnEliminarAsistenteCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarAsistenteClienteReunion();
			}
		});

		// Veo si el botnon de agregar archivo de reunion ha sido presionado
		getBtnAgregarArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarArchivoReunion();
			}
		});
		
		getBtnActualizarArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarArchivoReunion();
			}
		});
		
		getBtnEliminarArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarArchivoReunion();
			}
		});

		// Veo si el botnon de agregar compromiso de reunion ha sido presionado
		getBtnAgregarCompromiso().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarCompromisoReunion();
			}
		});
		
		getBtnActualizarCompromiso().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarCompromisoReunion();
			}
		});
		
		getBtnEliminarCompromiso().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarCompromisoReunion();
			}
		});

		getBtnAgregarURLArchivo().addActionListener(oActionListenerAgregarArchivo);
		getCbCliente().addActionListener(new CheckBoxIsClienteHandler());
	}
	
	private void agregarAsistenteAgenciaReunion() {
		try {
			boolean isExisteEmpleado = false;
			// Si la coleccion tiene algun elemento
			if (asistenteAgenciaColeccion.size() != 0) {
				// Recorro la coleccion de asistente agencia
				for (int i = 0; i < asistenteAgenciaColeccion.size(); i++) {
					EmpleadoIf empleadoTemp = (EmpleadoIf) asistenteAgenciaColeccion.get(i);
					// Si el empleado cargado ya esta en lista, entons
					// muestro un mensaje de error
					if (empleadoTemp.getCodigo().equals(empleadoIf.getCodigo()) && empleadoTemp.getNombres().equals(empleadoIf.getNombres())
							&& empleadoTemp.getApellidos().equals(empleadoIf.getApellidos())) {
						isExisteEmpleado = true;
						SpiritAlert.createAlert("El Asistente ya se encuentra agregado !!", SpiritAlert.INFORMATION);
						break;
					}
				}
			}
			// Si el empleado no existe lo inserto en la lista
			if (isExisteEmpleado == false) {
				// Agrego el empleado cargado a la coleccion
				asistenteAgenciaColeccion.add(empleadoIf);
				// Agregra informacion del empleado a la lista
				getModelAsistenteAgencia().addElement(empleadoIf);
				// Vacio el text field del asistente por agencia y
				// desabilito el boton de agregar
				getTxtAsistenteAgencia().setText("");
				getBtnAgregarAsistenteAgencia().setEnabled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Ocurrio un Error al Agregar el Producto Cliente !!!", SpiritAlert.ERROR);
		}
	}
	
	private void eliminarAsistenteAgenciaReunion() {
		if (getListAsistenteAgencia().getSelectedIndex() != -1) {
			// Extraigo el codigo del producto cliente segun el
			// selccionado de la tabla
			//String codigoEmpleadoTemp = getModelAsistenteAgencia().get(getListAsistenteAgencia().getSelectedIndex()).toString().split(" - ")[1];
			String nombreEmpleadoTemp = getModelAsistenteAgencia().get(getListAsistenteAgencia().getSelectedIndex()).toString();

			// Recorro la coleccion de asistenteAgencia y veo cual es el
			// seleccionado para eliminarlo
			for (int i = 0; i < asistenteAgenciaColeccion.size(); i++) {
				EmpleadoIf empleadoTemp = (EmpleadoIf) asistenteAgenciaColeccion.get(i);
				String nombre = "";
				String apellido = "";

				int espacioBlanco = empleadoTemp.getNombres().indexOf(' ');
				if ((espacioBlanco > 0)	&& !empleadoTemp.getNombres().substring(0, espacioBlanco).equals("MARIA"))
					nombre = empleadoTemp.getNombres().substring(0, espacioBlanco);
				else
					nombre = empleadoTemp.getNombres();
				
				espacioBlanco = empleadoTemp.getApellidos().indexOf(' ');
				if (espacioBlanco > 0)
					apellido = empleadoTemp.getApellidos().substring(0, espacioBlanco);
				else
					apellido = empleadoTemp.getApellidos();
				
				String nombreEmpleado = nombre + " " + apellido;

				//if (empleadoTemp.getCodigo().equals(codigoEmpleadoTemp)) {
				if (nombreEmpleado.equals(nombreEmpleadoTemp)) {
					// Elimino el registro de la coleccion y de la Lista
					asistenteAgenciaColeccion.remove(i);
					getModelAsistenteAgencia().remove(getListAsistenteAgencia().getSelectedIndex());
					// Vacio el Text Field del asistenteAgencia
					getTxtAsistenteAgencia().setText("");
					break;
				}
			}
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de la lista a eliminar !!!", SpiritAlert.WARNING);
		}
	}
	
	private void agregarAsistenteClienteReunion() {
		try {
			boolean isExisteContactoCliente = false;

			// Veo si el checkbox de cliente esta seleccionado, xq
			// dependiendo de esto voy a escoger un contacto de un
			// cliente ya existente
			if (getCbCliente().isSelected()) {
				// Si la coleccion tiene algun elemento
				if (asistenteClienteColeccion.size() != 0) {
					// Recorro la coleccion de Asistente cliente
					for (int i = 0; i < asistenteClienteColeccion.size(); i++) {
						ClienteContactoIf clienteContactoTemp = (ClienteContactoIf) asistenteClienteColeccion.get(i);
						// Si el Contacto cliente cargado ya esta en
						// lista, entons muestro un mensaje de error
						if (clienteContactoTemp.getNombre().equals(clienteContactoIf.getNombre())) {
							isExisteContactoCliente = true;
							SpiritAlert.createAlert("El Asistente ya se encuentra agregado !!", SpiritAlert.INFORMATION);
							break;
						}
					}
				}
				// Si el contacto no existe lo inserto en la lista
				if (isExisteContactoCliente == false) {
					// Agrego el contacto cliente cargado a la coleccion
					asistenteClienteColeccion.add(clienteContactoIf);
					// Agregra informacion del contacto cliente a la lista
					//TipoContactoIf tipoContacto = getTipoContactoSessionService().getTipoContacto(clienteContactoIf.getTipocontactoId());
					getModelAsistenteCliente().addElement(clienteContactoIf);
					// Vacio el text field del asistente cliente y
					// desabilito el boton de agregar
					getTxtAsistenteCliente().setText("");
					getBtnAgregarAsistenteCliente().setEnabled(false);
				}
			}
			// Sino es asi, debo ingresar a la lista los asistentes del
			// prospecto cliente que sean tiepado en el text field
			else {
				// Si la coleccion tiene algun elemento
				if (asistenteClienteColeccion.size() != 0) {
					// Recorro la coleccion de asistente cliente
					for (int i = 0; i < asistenteClienteColeccion.size(); i++) {
						String asistenteClienteTemp = (String) asistenteClienteColeccion.get(i);
						// Si el asistente cliente ingresado ya esta en
						// lista, entons muestro un mensaje de error
						if (asistenteClienteTemp.equals(getTxtAsistenteCliente().getText().trim())) {
							isExisteContactoCliente = true;
							JOptionPane.showMessageDialog(null, "El registro ya se encuentra agregado !!", "Mensaje de Aviso", JOptionPane.INFORMATION_MESSAGE);
							break;
						}
					}
				}
				// Si el contacto no existe lo inserto en la lista
				if (isExisteContactoCliente == false && !getTxtAsistenteCliente().getText().equals("")) {
					// Agrego el asistente cliente cargado a la
					// coleccion
					asistenteClienteColeccion.add(getTxtAsistenteCliente().getText().trim());
					// Agregra informacion del asistente cliente a la
					// lista
					getModelAsistenteCliente().addElement(getTxtAsistenteCliente().getText().trim());
					// Vacio el text field del asistente cliente y
					// desabilito el boton de agregar
					getTxtAsistenteCliente().setText("");
					getTxtAsistenteCliente().grabFocus();
				} else if (getTxtAsistenteCliente().getText().equals("")){
					JOptionPane.showMessageDialog(null, "Ingrese el asistente para agregar a la lista !!", "Mensaje de Aviso", JOptionPane.INFORMATION_MESSAGE);
					getTxtAsistenteCliente().grabFocus();
				}
					
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Ocurrio un Error al Agregar el Contacto Cliente !!", SpiritAlert.ERROR);
		}
	}
	
	private void eliminarAsistenteClienteReunion() {
		if (getListAsistenteCliente().getSelectedIndex() != -1) {
			// Extraigo el codigo del contacto cliente segun el selccionado de la tabla
			String nombreAsistenteClienteTemp = getModelAsistenteCliente().get(getListAsistenteCliente().getSelectedIndex()).toString();
			// Veo si el checkbox de cliente esta seleccionado, xq
			// dependiendo de esto voy a eliminar un producto de un
			// cliente ya existente
			if (getCbCliente().isSelected()) {

				// Recorro la coleccion de asistentecliente y veo cual es el seleccionado para eliminarlo
				for (int i = 0; i < asistenteClienteColeccion.size(); i++) {
					ClienteContactoIf clienteContactoTemp = (ClienteContactoIf) asistenteClienteColeccion.get(i);

					if (clienteContactoTemp.getNombre().equals(nombreAsistenteClienteTemp)) {
						// Elimino el registro de la coleccion y de la Lista
						asistenteClienteColeccion.remove(i);
						getModelAsistenteCliente().remove(getListAsistenteCliente().getSelectedIndex());
						// Vacio el Text Field del asistente Cliente
						getTxtAsistenteCliente().setText("");
						break;
					}
				}
			}
			// Sino es asi, debo eliminar de la lista los Conatctos del
			// prospecto cliente que se han tipeado en el text field
			else {
				// Extraigo el asistente cliente segun el selccionado de
				// la tabla
				String asistenteClienteSeleccionado = getModelAsistenteCliente().get(getListAsistenteCliente().getSelectedIndex()).toString();

				// Recorro la coleccion de asistentecliente y veo cual
				// es el seleccionado para eliminarlo
				for (int i = 0; i < asistenteClienteColeccion.size(); i++) {
					String asistenteClienteTemp = (String) asistenteClienteColeccion.get(i);

					if (asistenteClienteTemp.equals(asistenteClienteSeleccionado)) {
						// Elimino el registro de la coleccion y de la
						// Lista
						asistenteClienteColeccion.remove(i);
						getModelAsistenteCliente().remove(getListAsistenteCliente().getSelectedIndex());
						// Vacio el Text Field del asistente Cliente
						getTxtAsistenteCliente().setText("");
						break;
					}
				}
			}
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de la lista a eliminar !!!", SpiritAlert.WARNING);
		}
	}
	
	private void agregarArchivoReunion() {
		try {
			if (validateFieldsReunionArchivo()) {
				
				boolean isExisteArchivo = false;
				// Cambio el formato de la fecha para insertarlo a la base
				DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
				String fecha = dateFormatter.format(fechaArchivo.getTime());
				// Extraigo el objeto tipo Archivo del combo
				TipoArchivoIf tipoArchivo = (TipoArchivoIf) getCmbTipoArchivo().getSelectedItem();

				// Si la coleccion tiene algun elemento
				if (archivoReunionColeccion.size() != 0) {
					// Recorro la coleccion de Archivos de reunion
					for (int i = 0; i < archivoReunionColeccion.size(); i++) {
						ReunionArchivoIf reunionArchivoTemp = (ReunionArchivoIf) archivoReunionColeccion.get(i);
						String fechaTemp = dateFormatter.format(reunionArchivoTemp.getFecha());
						// Si la reunion Archivo cargado ya esta en lista,
						// entons muestro un mensaje de error
						if (reunionArchivoTemp.getTipoArchivoId().equals(tipoArchivo.getId()) && fechaTemp.equals(fecha) 
								&& reunionArchivoTemp.getEstado().equals(getCmbEstadoArchivo().getSelectedItem().toString().substring(0, 1))
								&& reunionArchivoTemp.getUrlDescripcion().equals(getTxtURLArchivo().getText().replaceAll(" ","_"))) {
							isExisteArchivo = true;
							break;
						}
					}
				}

				modelReunionArchivo = (DefaultTableModel) getTableArchivo().getModel();
				filaReunionArchivo = new Vector<String>();				
				
				if (isExisteArchivo == false) {

					ReunionArchivoData data = new ReunionArchivoData();

					data.setTipoArchivoId(tipoArchivo.getId());
					data.setUrlDescripcion(getTxtURLArchivo().getText());
					data.setFecha(new java.sql.Date(fechaArchivo.getYear(), fechaArchivo.getMonth(),fechaArchivo.getDate()));
					data.setEstado(getCmbEstadoArchivo().getSelectedItem().toString().substring(0, 1));

					// Agregar a la coleccion de reunionArchivoColeccion
					// para grabar al final toda la coleccion
					archivoReunionColeccion.add(data);
					File archivo = new File(getTxtURLArchivo().getText());
																
					//FileInputStreamSerializable archivoStream = new FileInputStreamSerializable(archivo);
					
					archivosColeccion.add(archivo);
											
					archivosNombreColeccion.add(archivo.getName());
					// Agregra los valores al registro que va ser añadido a
					// la tabla.
					filaReunionArchivo.add(tipoArchivo.getNombre());
					// Agregra los valores al registro que va ser añadido a
					// la tabla.
					//filaReunionArchivo.add(fecha);
					
					filaReunionArchivo.add(Utilitarios.getFechaUppercase(fechaArchivo));
					filaReunionArchivo.add(getCmbEstadoArchivo().getSelectedItem().toString());
					filaReunionArchivo.add(getTxtURLArchivo().getText());

					// Agregra informacion a la tabla visual para el
					// usuario.
					modelReunionArchivo.addRow(filaReunionArchivo);
					// Reinicio los componentes
					if (getCmbTipoArchivo().getItemCount() > 0)
						getCmbTipoArchivo().setSelectedIndex(0);
					getCmbEstadoArchivo().setSelectedItem("");
					getTxtURLArchivo().setText("");
					getTxtURLArchivo().grabFocus();
				} else {
					SpiritAlert.createAlert("El Archivo ya se encuentra agregado !!!", SpiritAlert.INFORMATION);
					// Reinicio los componentes
					if (getCmbTipoArchivo().getItemCount() > 0)
						getCmbTipoArchivo().setSelectedIndex(0);
					getCmbEstadoArchivo().setSelectedItem("");
					getTxtURLArchivo().setText("");
					getTxtURLArchivo().grabFocus();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert(" No se pudo ingresar el detalle !!!", SpiritAlert.ERROR);
		}
	}
	
	private void actualizarArchivoReunion() {
		int filaSeleccionada = getTableArchivo().getSelectedRow();
		if (filaSeleccionada >= 0) {
			
			//Cambio el formato de la fecha para insertarlo a la base
			DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
			String fecha = dateFormatter.format(fechaArchivo.getTime());
			// Extraigo el objeto tipo Archivo del combo
			TipoArchivoIf tipoArchivo = (TipoArchivoIf) getCmbTipoArchivo().getSelectedItem();

			modelReunionArchivo = (DefaultTableModel) getTableArchivo().getModel();
			filaReunionArchivo = new Vector<String>();
			
			if (validateFieldsReunionArchivo()) {
				ReunionArchivoData data = new ReunionArchivoData();

				data.setId(archivoReunionColeccion.get(filaSeleccionada).getId());
				data.setTipoArchivoId(tipoArchivo.getId());
				data.setUrlDescripcion(getTxtURLArchivo().getText());
				data.setFecha(new java.sql.Date(getCmbFechaArchivo().getDate().getTime()));
				data.setEstado(getCmbEstadoArchivo().getSelectedItem().toString().substring(0, 1));

				// Agregar a la coleccion de reunionArchivoColeccion
				// para grabar al final toda la coleccion
				archivoReunionColeccion.add(filaSeleccionada, data);
				archivoReunionColeccion.remove(filaSeleccionada+1);
				
				File archivo = new File(getTxtURLArchivo().getText());
				//FileInputStream archivo = new FileInputStream(file);
				archivosColeccion.add(archivo);
									
				// Agregra los valores al registro que va ser añadido a
				// la tabla.
				filaReunionArchivo.add(tipoArchivo.getNombre());
				// Agregra los valores al registro que va ser añadido a
				// la tabla.
				//filaReunionArchivo.add(fecha);
				
				filaReunionArchivo.add(Utilitarios.getFechaUppercase(getCmbFechaArchivo().getDate()));
				filaReunionArchivo.add(getCmbEstadoArchivo().getSelectedItem().toString());
				filaReunionArchivo.add(getTxtURLArchivo().getText());

				// Agregra informacion a la tabla visual para el
				// usuario.
				modelReunionArchivo.insertRow(filaSeleccionada, filaReunionArchivo);
				modelReunionArchivo.removeRow(filaSeleccionada+1);
				// Reinicio los componentes
				if (getCmbTipoArchivo().getItemCount() > 0)
					getCmbTipoArchivo().setSelectedIndex(0);
				getCmbEstadoArchivo().setSelectedItem("");
				getTxtURLArchivo().setText("");
				getTxtURLArchivo().grabFocus();
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
		}
	}
	
	private void eliminarArchivoReunion() {
		if (getTableArchivo().getSelectedRow() != -1) {
			int filaSeleccionada = getTableArchivo().getSelectedRow();
			ReunionArchivoIf reunionArchivo = archivoReunionColeccion.get(filaSeleccionada);
			if (reunionArchivo.getId() != null){
				archivosEliminadosColeccion.add(reunionArchivo);
			}
			archivosColeccion.remove(getTableArchivo().getSelectedRow());
			archivoReunionColeccion.remove(getTableArchivo().getSelectedRow());
			modelReunionArchivo.removeRow(getTableArchivo().getSelectedRow());
			getTxtURLArchivo().setText("");
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de la lista a eliminar !!!", SpiritAlert.WARNING);
		}
	}
	
	private void agregarCompromisoReunion() {
		try {
			// Cambio el formato de la fecha para insertarlo a la base
			DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
			String fecha = dateFormatter.format(fechaCompromiso.getTime());

			boolean isExisteCompromiso = false;
			// Si la coleccion tiene algun elemento
			if (compromisoReunionColeccion.size() != 0) {
				// Recorro la coleccion de Compromisos de reunion
				for (int i = 0; i < compromisoReunionColeccion.size(); i++) {
					ReunionCompromisoIf reunionCompromisoTemp = (ReunionCompromisoIf) compromisoReunionColeccion.get(i);
					String fechaTemp = dateFormatter.format(reunionCompromisoTemp.getFecha());
					// Si la reunion Compromiso cargado ya esta en
					// lista, entons muestro un mensaje de error
					if (fechaTemp.equals(fecha) && reunionCompromisoTemp.getEstado().equals(getCmbEstadoCompromiso().getSelectedItem().toString().substring(0, 1)) 
							&& reunionCompromisoTemp.getDescripcion().equals(getTxtDescripcionCompromiso().getText())
							&& reunionCompromisoTemp.getTemaTratado().equals(getTxtTemaTratado().getText())) {
						isExisteCompromiso = true;
						break;
					}
				}
			}

			modelReunionCompromiso = (DefaultTableModel) getTableCompromiso().getModel();
			Vector<String> filaReunionCompromiso = new Vector<String>();

			// Veo que todos los campos hayan sido llenados
			if (validateFieldsReunionCompromiso()) {
				// Si el Compromiso no existe lo inserto
				if (isExisteCompromiso == false) {

					ReunionCompromisoData data = new ReunionCompromisoData();

					// Seteo los datos del objeto reunion compromiso
					data.setFecha(new java.sql.Date(fechaCompromiso.getYear(), fechaCompromiso.getMonth(),fechaCompromiso.getDate()));
					data.setDescripcion(getTxtDescripcionCompromiso().getText());
					data.setEstado(getCmbEstadoCompromiso().getSelectedItem().toString().substring(0, 1));
					data.setTemaTratado(getTxtTemaTratado().getText());
					
					// Agregar a la coleccion de reunionCompromisoColeccion
					// para grabar al final toda la coleccion
					compromisoReunionColeccion.add(data);

					// Agregra los valores al registro que va ser añadido a
					// la tabla.
					//filaReunionCompromiso.add(fecha);
					filaReunionCompromiso.add(Utilitarios.getFechaUppercase(fechaCompromiso));
					filaReunionCompromiso.add(getCmbEstadoCompromiso().getSelectedItem().toString());
					filaReunionCompromiso.add(getTxtTemaTratado().getText());
					// Agregra informacion a la tabla visual para el
					// usuario.
					modelReunionCompromiso.addRow(filaReunionCompromiso);
					// Reinicio los componentes
					getTxtDescripcionCompromiso().setText("");
					getTxtTemaTratado().setText("");
					getCmbEstadoCompromiso().setSelectedItem("");
					getTxtTemaTratado().grabFocus();
				} else {
					SpiritAlert.createAlert("El Acuerdo ya se encuentra agregado !!!", SpiritAlert.INFORMATION);
					// Reinicio los componentes
					getTxtDescripcionCompromiso().setText("");
					getTxtTemaTratado().setText("");
					getCmbEstadoCompromiso().setSelectedItem("");
					getTxtTemaTratado().grabFocus();
				}
			}
		} catch (Exception e) {
			SpiritAlert.createAlert(" No se pudo ingresar el detalle !!!", SpiritAlert.ERROR);
			System.out.println(e.getMessage());
		}
	}
	
	private void actualizarCompromisoReunion() {
		int filaSeleccionada = getTableCompromiso().getSelectedRow();
		if (filaSeleccionada >= 0) {
			modelReunionCompromiso = (DefaultTableModel) getTableCompromiso().getModel();
			Vector<String> filaReunionCompromiso = new Vector<String>();
			
			if (validateFieldsReunionCompromiso()) {
				ReunionCompromisoData data = new ReunionCompromisoData();

				// Seteo los datos del objeto reunion compromiso
				data.setId(compromisoReunionColeccion.get(filaSeleccionada).getId());
				data.setFecha(new java.sql.Date(getCmbFechaCompromiso().getDate().getTime()));
				data.setDescripcion(getTxtDescripcionCompromiso().getText());
				data.setEstado(getCmbEstadoCompromiso().getSelectedItem().toString().substring(0, 1));
				data.setTemaTratado(getTxtTemaTratado().getText());
				
				// Agregar a la coleccion de reunionCompromisoColeccion
				// para grabar al final toda la coleccion
				compromisoReunionColeccion.add(filaSeleccionada, data);
				compromisoReunionColeccion.remove(filaSeleccionada+1);
				// Agregra los valores al registro que va ser añadido a
				// la tabla.
				//filaReunionCompromiso.add(fecha);
				filaReunionCompromiso.add(Utilitarios.getFechaUppercase(getCmbFechaCompromiso().getDate()));
				filaReunionCompromiso.add(getCmbEstadoCompromiso().getSelectedItem().toString());
				filaReunionCompromiso.add(getTxtTemaTratado().getText());
				// Agregra informacion a la tabla visual para el
				// usuario.
				modelReunionCompromiso.insertRow(filaSeleccionada, filaReunionCompromiso);
				modelReunionCompromiso.removeRow(filaSeleccionada+1);
				// Reinicio los componentes
				getTxtDescripcionCompromiso().setText("");
				getTxtTemaTratado().setText("");
				getCmbEstadoCompromiso().setSelectedItem("");
				getTxtTemaTratado().grabFocus();
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
		}
	}
	
	private void eliminarCompromisoReunion() {
		if (getTableCompromiso().getSelectedRow() != -1) {
			int filaSeleccionada = getTableCompromiso().getSelectedRow();
			ReunionCompromisoIf reunionCompromiso = compromisoReunionColeccion.get(filaSeleccionada);
			if (reunionCompromiso.getId() != null){
				compromisosEliminadosColeccion.add(reunionCompromiso);
			}
			compromisoReunionColeccion.remove(getTableCompromiso().getSelectedRow());
			modelReunionCompromiso.removeRow(getTableCompromiso().getSelectedRow());
			getTxtDescripcionCompromiso().setText("");
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de la lista a eliminar !!!",SpiritAlert.WARNING);
		}
	}

	public boolean validateFieldsReunionArchivo() {
		if (this.getCmbTipoArchivo().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de archivo !!!", SpiritAlert.WARNING);
			this.getJtpReuniones().setSelectedIndex(3);
			getCmbTipoArchivo().grabFocus();
			return false;
		}
		
		if ("".equals(this.getTxtURLArchivo().getText())) {
			SpiritAlert.createAlert("Debe ingresar la URL del archivo !!!", SpiritAlert.WARNING);
			this.getJtpReuniones().setSelectedIndex(3);
			this.getBtnAgregarURLArchivo().grabFocus();
			return false;
		}
		
		if ("".equals(this.getCmbFechaArchivo().getSelectedItem())) {
			SpiritAlert.createAlert("Debe seleccionar la fecha", SpiritAlert.WARNING);
			this.getJtpReuniones().setSelectedIndex(3);
			this.getCmbFechaArchivo().grabFocus();
			return false;
		}
		
		if (this.getCmbEstadoArchivo().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el estado del archivo", SpiritAlert.WARNING);
			this.getJtpReuniones().setSelectedIndex(3);
			this.getCmbEstadoArchivo().grabFocus();
			return false;
		}
		
		return true;
	}

	public boolean validateFieldsReunionCompromiso() {		
		if (getCmbFechaCompromiso().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la Fecha", SpiritAlert.WARNING);
			this.getJtpReuniones().setSelectedIndex(4);
			getCmbFechaCompromiso().grabFocus();
			return false;
		}
		
		/*if ("".equals(this.getTxtTemaTratado().getText())) {
			SpiritAlert.createAlert("Debe ingresar el Tema del acuerdo", SpiritAlert.WARNING);
			this.getJtpReuniones().setSelectedIndex(4);
			getTxtTemaTratado().grabFocus();
			return false;
		}*/
		
		if ("".equals(this.getTxtDescripcionCompromiso().getText())) {
			SpiritAlert.createAlert("Debe ingresar la Descripción del acuerdo", SpiritAlert.WARNING);
			this.getJtpReuniones().setSelectedIndex(4);
			getTxtDescripcionCompromiso().grabFocus();
			return false;
		}
		
		if (getCmbEstadoCompromiso().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el Estado del acuerdo", SpiritAlert.WARNING);
			this.getJtpReuniones().setSelectedIndex(4);
			getCmbEstadoCompromiso().grabFocus();
			return false;
		}
		
		return true;
	}

	// Action Listener del combo de fecha reunion
	ActionListener oActionListenerCmbFecha = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			/*try {
				java.util.Date fechaHoy = new java.util.Date();
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(fechaHoy);

				if ((evento.getSource() == getCmbFechaArchivo())) {
					fechaArchivo = (Date) ((DateComboBox) evento.getSource()).getDate();

					if (fechaArchivo.before(Utilitarios.dateHoy())) {
						SpiritAlert.createAlert("La fecha de archivo no puede estar antes de la fecha actual!", SpiritAlert.INFORMATION);
						fechaArchivo = fechaHoy;
						getCmbFechaArchivo().setCalendar(calendar);
					}			
				} else if ((evento.getSource() == getCmbFechaCompromiso())) {
					fechaCompromiso = (Date) ((DateComboBox) evento.getSource()).getDate();

					if (fechaCompromiso.before(Utilitarios.dateHoy())) {
						SpiritAlert.createAlert("La fecha de compromiso no puede estar antes de la fecha actual!", SpiritAlert.INFORMATION);
						fechaCompromiso = fechaHoy;
						getCmbFechaCompromiso().setCalendar(calendar);
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}*/
		}
	};

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
				Object[] options ={"Si","No"};
				try {
					boolean existe = SessionServiceLocator.getFileManagerSessionService().existeArchivo(
							Parametros.getUrlCarpetaServidor(), selectedFile.getName());
					if (!existe){
						/**
						 * valido que botón ha sido presionado y le asigno al
						 * correspondiente textbox el path del archivo que haya
						 * seleccionado
						 */

						if ((actionEvent.getSource() == getBtnAgregarURLArchivo()))
							getTxtURLArchivo().setText(fileChooser.getSelectedFile().toString());

						/**
						 * ejecuto el archivo con su respectivo programa para poder ser
						 * previsualizado
						 */
						try {
							int opcion = JOptionPane.showOptionDialog(null, "Desea previsualizar el archivo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "No");
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
							if ((actionEvent.getSource() == getBtnAgregarURLArchivo()))
								getTxtURLArchivo().setText(
										fileChooser.getSelectedFile().toString());
						} else {
							if ((actionEvent.getSource() == getBtnAgregarURLArchivo()))
								getTxtURLArchivo().setText("");
						}
					}
				} catch (GenericBusinessException e1) {
					e1.printStackTrace();
					SpiritAlert.createAlert(e1.getMessage(), SpiritAlert.ERROR);
				}
			}
		}
	};

	private class CheckBoxIsClienteHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getTxtCorporacion().setText("");
			getTxtCliente().setText("");
			getTxtAsistenteCliente().setText("");
			// Vacio la lista de asistentes clientes
			getModelAsistenteCliente().removeAllElements();
			// Vacio la coleccion de asistentes clientes
			asistenteClienteColeccion = new Vector();

			// Veo si el checkbox esta seleccionado y si el modo es cualquiera
			if (getCbCliente().isSelected()) {
				getBtnBuscarCorporacion().setEnabled(true);
				getBtnBuscarCliente().setEnabled(true);
				getTxtProspectoCliente().setText("");
				getTxtProspectoCliente().setEnabled(false);
				getTxtProspectoCliente().setBackground(getBackground());
				getBtnBuscarCorporacion().grabFocus();
				// Veo si el checkbox no esta seleccionado y si el modo es find
			} else if (!getCbCliente().isSelected()) {
				getBtnBuscarCorporacion().setEnabled(false);
				getBtnBuscarCliente().setEnabled(false);
				getTxtProspectoCliente().setText("");
				getTxtProspectoCliente().setEnabled(true);
				getTxtProspectoCliente().grabFocus();
			}

			// Veo si el checkbox esta seleccionado y si el modo es save o
			// update
			if (getCbCliente().isSelected()
					&& (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE)) {
				getTxtAsistenteCliente().setEditable(false);
				getBtnBuscarAsistenteCliente().setEnabled(false);
				getBtnAgregarAsistenteCliente().setEnabled(false);
				// Veo si el checkbox no esta seleccionado y si el modo es save
				// o update
			} else if (!getCbCliente().isSelected()
					&& (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE)) {
				getTxtAsistenteCliente().setEditable(true);
				getBtnBuscarAsistenteCliente().setEnabled(false);
				getBtnAgregarAsistenteCliente().setEnabled(true);
			}
		}
	}

	public void getSelectedObject(){
		setUpdateMode();

		reunion = (ReunionIf) popupFinder.getElementoSeleccionado();
		cargarCombos();

		getTxtDescripcion().setText(reunion.getDescripcion());
		getTxtNumeroEnvios().setText(reunion.getNumEnviados().toString());
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(reunion.getFecha());
		getCmbFechaCreacion().setCalendar(calendar);
		
		getCmbEjecutivo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbEjecutivo(), reunion.getEjecutivoId()));
		getCmbEjecutivo().repaint();
		
		getTxtLugarReunion().setText(reunion.getLugarReunion());
		getTxtConCopia().setText(reunion.getConCopia()!=null?reunion.getConCopia():"");
		
		DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
		getTxtFechaUltimoEnvio().setText(Utilitarios.getFechaUppercase(reunion.getFechaEnvio())); 

		// LLeno los datos de los combos
		if (ESTADO_ACTIVO.equals(reunion.getEstado()))
			getCmbEstadoReunion().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
		else if (ESTADO_INACTIVO.equals(reunion.getEstado()))
			getCmbEstadoReunion().setSelectedItem(NOMBRE_ESTADO_INACTIVO);

		try {

			// Cargo los Asistentes pertencientes a la reunion leida de la
			// base
			Collection reunionAsistenteColeccion = SessionServiceLocator
					.getReunionAsistenteSessionService()
					.findReunionAsistenteByReunionId(reunion.getId());
			Iterator itReunionAsistenteColeccion = reunionAsistenteColeccion
					.iterator();

			while (itReunionAsistenteColeccion.hasNext()) {
				ReunionAsistenteIf reunionAsistenteIf = (ReunionAsistenteIf) itReunionAsistenteColeccion
						.next();

				// Veo si el asistente de la reunion es por agencia o por
				// cliente
				if (reunionAsistenteIf.getEmpleadoId() != null) {
					EmpleadoIf empleadoTemp = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(
									reunionAsistenteIf.getEmpleadoId());
					// Agrego el asistente por agencia cargado a la
					// coleccion
					asistenteAgenciaColeccion.add(empleadoTemp);
					// Agregra informacion del asistente por agencia a la
					// lista
					getModelAsistenteAgencia().addElement(empleadoTemp);
				}
				// Si el asistente es por cliente veo si es de uno ya
				// existente o no
				else if (reunionAsistenteIf.getClienteContactoId() != null) {
					ClienteContactoIf clienteContactoTemp = SessionServiceLocator.getClienteContactoSessionService()
							.getClienteContacto(reunionAsistenteIf.getClienteContactoId());
					// Agrego el asistente por cliente cargado a la
					// coleccion
					asistenteClienteColeccion.add(clienteContactoTemp);
					// Agregra informacion del asistente por cliente a la
					// lista
					getModelAsistenteCliente().addElement(
							clienteContactoTemp);
				}
				// si es por un prospecto de cliente mando a leer lso
				// prospectos delos conatcttos de cliente
				else {
					// Agrego el asistente por cliente cargado a la
					// coleccion
					asistenteClienteColeccion.add(reunionAsistenteIf
							.getClienteContacto());
					// Agregra informacion del asistente por cliente a la
					// lista
					getModelAsistenteCliente().addElement(
							reunionAsistenteIf.getClienteContacto());
				}
			}

			// Veo si el registro leido de la base de reunion tiene a un
			// cliente existente o a un prospecto de cliente
			if (reunion.getClienteId() != null) {
				// Seteo el checkbox indicando que estoy trabajando con
				// uncliente dela empresa
				getCbCliente().setSelected(true);
				clienteIf = SessionServiceLocator.getClienteSessionService()
						.getCliente(reunion.getClienteId());
				corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(
								clienteIf.getCorporacionId());

				getTxtCliente().setText(
						clienteIf.getIdentificacion() + " - "
								+ clienteIf.getNombreLegal());
				getTxtCorporacion().setText(
						corporacionIf.getCodigo() + " - "
								+ corporacionIf.getNombre());
				getTxtProspectoCliente().setBackground(getBackground());
			} else {
				getCbCliente().setSelected(false);
				getTxtProspectoCliente().setText(reunion.getProspectocliente());
				getTxtProspectoCliente().setBackground(Parametros.saveUpdateColor);
			}

			// Cargo los productos clientes pertencientes a la reunion leida de la base
			cargarListaProductos();
			Collection reunionProductoColeccion = SessionServiceLocator.getReunionProductoSessionService().findReunionProductoByReunionId(reunion.getId());
			Iterator itReunionProductoColeccion = reunionProductoColeccion.iterator();
			while (itReunionProductoColeccion.hasNext()) {
				ReunionProductoIf reunionProductoIf = (ReunionProductoIf) itReunionProductoColeccion.next();
				productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(reunionProductoIf.getProductoClienteId());
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

			// Cargo los archivos pertencientes a la reunion leida de la
			// base
			Collection reunionArchivoColeccion = SessionServiceLocator.getReunionArchivoSessionService().findReunionArchivoByReunionId(reunion.getId());
			Iterator itReunionArchivoColeccion = reunionArchivoColeccion.iterator();

			// Obtengo el modelo de la tabla para agregar los archivos
			// leidos de la base
			modelReunionArchivo = (DefaultTableModel) getTableArchivo()
					.getModel();

			while (itReunionArchivoColeccion.hasNext()) {
				ReunionArchivoIf reunionArchivoIf = (ReunionArchivoIf) itReunionArchivoColeccion
						.next();

				filaReunionArchivo = new Vector<String>();

				// Agregar a la coleccion de ReunionArchivoColeccion para
				// grabar al final toda la coleccion
				archivoReunionColeccion.add(reunionArchivoIf);
				
				//Se pone null en la posicion del archivo que ya esta
				//en la base
				archivosColeccion.add(null);

				// Extraigo el objeto tipoarchivo del reunion archivo
				TipoArchivoIf tipoArchivo = (TipoArchivoIf) SessionServiceLocator.getTipoArchivoSessionService().getTipoArchivo(
								reunionArchivoIf.getTipoArchivoId());

				// Cambio el formato de la fecha para insertarlo a la base
				String fechaArchivo = dateFormatter.format(reunionArchivoIf
						.getFecha().getTime());

				// Veo el estado del archivo leido
				String estadoArchivo = "";
				// LLeno los datos del combo de estado de reunion archivo
				if (ESTADO_ACTIVO.equals(reunionArchivoIf.getEstado()))
					estadoArchivo = NOMBRE_ESTADO_ACTIVO;
				else
					estadoArchivo = NOMBRE_ESTADO_INACTIVO;

				// Agregra los valores al registro que va ser añadido a la
				// tabla.
				filaReunionArchivo.add(tipoArchivo.getNombre());
				//filaReunionArchivo.add(fechaArchivo);
				filaReunionArchivo.add(
						Utilitarios.getFechaUppercase(reunionArchivoIf.getFecha())
				);
				filaReunionArchivo.add(estadoArchivo);
				if(reunionArchivoIf.getUrlDescripcion() != null) filaReunionArchivo.add(reunionArchivoIf.getUrlDescripcion());
				else filaReunionArchivo.add("");
					
				// Agregra informacion a la tabla visual para el usuario.
				modelReunionArchivo.addRow(filaReunionArchivo);
			}

			// Cargo los compromisos pertencientes a la reunion leida de la
			// base
			Collection reunionCompromisoColeccion = SessionServiceLocator
					.getReunionCompromisoSessionService()
					.findReunionCompromisoByReunionId(reunion.getId());
			Iterator itReunionCompromisoColeccion = reunionCompromisoColeccion
					.iterator();

			// Obtengo el modelo de la tabla para agregar los compromisos
			// leidos de la base
			modelReunionCompromiso = (DefaultTableModel) getTableCompromiso()
					.getModel();

			while (itReunionCompromisoColeccion.hasNext()) {
				ReunionCompromisoIf reunionCompromisoIf = (ReunionCompromisoIf) itReunionCompromisoColeccion.next();
				Vector<String> filaReunionCompromiso = new Vector<String>();

				// Agregar a la coleccion de ReunionCompromisoColeccion para
				// grabar al final toda la coleccion
				compromisoReunionColeccion.add(reunionCompromisoIf);

				// Cambio el formato de la fecha para insertarlo a la base
				String fechaCompromiso = dateFormatter.format(reunionCompromisoIf.getFecha().getTime());

				// Veo el estado del archivo leido
				String estadoCompromiso = "";
				// LLeno los datos del combo de estado de reunion compromiso
				if (ESTADO_ACTIVO.equals(reunionCompromisoIf.getEstado()))
					estadoCompromiso = NOMBRE_ESTADO_ACTIVO;
				else
					estadoCompromiso = NOMBRE_ESTADO_INACTIVO;

				// Agregra los valores al registro que va ser añadido a la tabla.
				//filaReunionCompromiso.add(fechaCompromiso);
				filaReunionCompromiso.add(Utilitarios.getFechaUppercase(reunionCompromisoIf.getFecha()));
				filaReunionCompromiso.add(estadoCompromiso);
				filaReunionCompromiso.add(reunionCompromisoIf.getTemaTratado());

				// Agregra informacion a la tabla visual para el usuario.
				modelReunionCompromiso.addRow(filaReunionCompromiso);
			}

		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}

		this.showUpdateMode();
	}

	public void report() {
		String si = "Si"; 
		String no = "No"; 
		Object[] options ={si,no}; 
		int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el Reporte de Contacto?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
		if (opcion == JOptionPane.YES_OPTION) {
			String fileName = "jaspers/medios/RPReunion.jasper";
			
			HashMap parametrosMap = new HashMap();
			parametrosMap.put("urlLogoEmpresa", Parametros.getLogoEmpresa());					
			
			if (getCbCliente().isSelected()) {
				parametrosMap.put("cliente", getTxtCliente().getText());
			}else{
				parametrosMap.put("cliente", getTxtProspectoCliente().getText());
			}
			
			String marcas = "";
			String marcaRepedita = "";
			if(productoClienteList != null && !productoClienteList.isEmpty() && (productoClienteList.get(0) instanceof ProductoClienteIf)){
				List<ProductoClienteIf> productoClienteIfColeccion = productoClienteList;
				for(ProductoClienteIf productoClienteIf : productoClienteIfColeccion){
					if(!marcas.contains(productoClienteIf.getMarcaProductoNombre())){
						marcas = marcas + productoClienteIf.getMarcaProductoNombre() + ", ";
					}			
				}
				marcas = marcas.substring(0,marcas.length()-2);
			}
			parametrosMap.put("marca", marcas);					
			parametrosMap.put("fechaReunion", Utilitarios.getFechaUppercase(getCmbFechaCreacion().getDate()));
			
			String asistentesAgencia = "";
			String asistentesCliente = "";					
			for(int i=0; i<asistenteAgenciaColeccion.size(); i++){
				EmpleadoIf empleadoTemp = (EmpleadoIf)asistenteAgenciaColeccion.get(i);
				String nombreEmpleado = "";
				if((empleadoTemp.getNombres().split(" ")[0].equals("MARIA") || empleadoTemp.getNombres().split(" ")[0].equals("MARÍA")) && (empleadoTemp.getNombres().length() > 8)){
					nombreEmpleado = empleadoTemp.getNombres().split(" ")[0].substring(0,1) + empleadoTemp.getNombres().split(" ")[0].substring(1).toLowerCase() + " " + empleadoTemp.getNombres().split(" ")[1].substring(0,1) + empleadoTemp.getNombres().split(" ")[1].substring(1).toLowerCase();;
				}else{
					nombreEmpleado = empleadoTemp.getNombres().split(" ")[0].substring(0,1) + empleadoTemp.getNombres().split(" ")[0].substring(1).toLowerCase();
				}							
				asistentesAgencia = asistentesAgencia + nombreEmpleado + " " + empleadoTemp.getApellidos().split(" ")[0].substring(0,1)+empleadoTemp.getApellidos().split(" ")[0].substring(1).toLowerCase()   + "\n";
			}					
			
			for(int i=0; i<asistenteClienteColeccion.size(); i++){
				if(asistenteClienteColeccion.get(i) instanceof ClienteContactoIf){
					ClienteContactoIf clienteContactoIf = (ClienteContactoIf)asistenteClienteColeccion.get(i);
					asistentesCliente = asistentesCliente + clienteContactoIf.getNombre() + "\n";
				}else{
					String reunionAsistente = (String)asistenteClienteColeccion.get(i);
					asistentesCliente = asistentesCliente + reunionAsistente + "\n";
				}
			}
			
			asistentesAgencia = asistentesAgencia.substring(0,asistentesAgencia.length()-1);
			asistentesCliente = asistentesCliente.substring(0,asistentesCliente.length()-1);
			parametrosMap.put("presentesCliente", asistentesCliente);
			parametrosMap.put("presentesAgencia", asistentesAgencia);
			
			parametrosMap.put("lugarReunion", getTxtLugarReunion().getText());
			parametrosMap.put("conCopia", getTxtConCopia().getText()!=null?getTxtConCopia().getText():"");
			parametrosMap.put("temasTratados", getTxtDescripcion().getText());
			
			EmpleadoIf ejecutivo = (EmpleadoIf)getCmbEjecutivo().getSelectedItem();
			String nombreEjecutivo = "";
			if(ejecutivo.getNombres().split(" ")[0].equals("MARIA") && (ejecutivo.getNombres().length() > 8)){
				nombreEjecutivo = ejecutivo.getNombres().split(" ")[0].substring(0,1) + ejecutivo.getNombres().split(" ")[0].substring(1).toLowerCase() + " " + ejecutivo.getNombres().split(" ")[1].substring(0,1) + ejecutivo.getNombres().split(" ")[1].substring(1).toLowerCase();;	
			}else{
				nombreEjecutivo = ejecutivo.getNombres().split(" ")[0].substring(0,1) + ejecutivo.getNombres().split(" ")[0].substring(1).toLowerCase();
			}					
			parametrosMap.put("ejecutivo", nombreEjecutivo + " " + ejecutivo.getApellidos().split(" ")[0].substring(0,1)+ejecutivo.getApellidos().split(" ")[0].substring(1).toLowerCase());
			
			ReportModelImpl.processReport(fileName, parametrosMap, compromisoReunionColeccion, true);
		}
	}
	
	public void refresh() {
		cargarComboTipoArchivo();
		cargarComboEjecutivos();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpReuniones().getSelectedIndex();
		int tabCount = this.getJtpReuniones().getTabCount();
		
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpReuniones().setSelectedIndex(selectedTab);
	}
	
	public void addDetail() {
		if (getJtpReuniones().getSelectedIndex() == 2) {
			if (this.getBtnAgregarAsistenteAgencia().isEnabled() == true)
				agregarAsistenteAgenciaReunion();
			if (this.getBtnAgregarAsistenteCliente().isEnabled() == true)
				agregarAsistenteClienteReunion();
		}
		if (getJtpReuniones().getSelectedIndex() == 3)
			agregarArchivoReunion();
		if (getJtpReuniones().getSelectedIndex() == 4)
			agregarCompromisoReunion();
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
	}
	
	public void deleteDetail() {
		
	}

	//Metodos que se agregaron para no eliminar la implementacion que ya estaba hecha para fecha ultimo envio y
	// para numero envios
	
	public java.util.Date getFechaUltimoEnvio() {
		return fechaUltimoEnvio;
	}

	public void setFechaUltimoEnvio(java.util.Date fechaUltimoEnvio) {
		this.fechaUltimoEnvio = fechaUltimoEnvio;
	}

	public JLabel getLblFechaUltimoEnvio() {
		return lblFechaUltimoEnvio;
	}

	public void setLblFechaUltimoEnvio(JLabel lblFechaUltimoEnvio) {
		this.lblFechaUltimoEnvio = lblFechaUltimoEnvio;
	}

	public JLabel getLblNumeroEnvios() {
		return lblNumeroEnvios;
	}

	public void setLblNumeroEnvios(JLabel lblNumeroEnvios) {
		this.lblNumeroEnvios = lblNumeroEnvios;
	}

	public JTextField getTxtNumeroEnvios() {
		return txtNumeroEnvios;
	}

	public void setTxtNumeroEnvios(JTextField txtNumeroEnvios) {
		this.txtNumeroEnvios = txtNumeroEnvios;
	}

	public JTextField getTxtFechaUltimoEnvio() {
		return txtFechaUltimoEnvio;
	}

	public void setTxtFechaUltimoEnvio(JTextField txtFechaUltimoEnvio) {
		this.txtFechaUltimoEnvio = txtFechaUltimoEnvio;
	}
	
}