
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
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteZonaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoArchivoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.medios.entity.CampanaArchivoData;
import com.spirit.medios.entity.CampanaArchivoIf;
import com.spirit.medios.entity.CampanaBriefData;
import com.spirit.medios.entity.CampanaBriefIf;
import com.spirit.medios.entity.CampanaData;
import com.spirit.medios.entity.CampanaDetalleData;
import com.spirit.medios.entity.CampanaDetalleIf;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.CampanaProductoIf;
import com.spirit.medios.entity.CampanaProductoVersionData;
import com.spirit.medios.entity.CampanaProductoVersionIf;
import com.spirit.medios.entity.ComercialIf;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.OrdenTrabajoProductoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.entity.TipoBriefIf;
import com.spirit.medios.gui.criteria.CampanaCriteria;
import com.spirit.medios.gui.panel.JPCampana;
import com.spirit.util.Archivos;
import com.spirit.util.ExtensionFileFilter;
import com.spirit.util.FileInputStreamSerializable;
import com.spirit.util.LabelAccessory;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class CampanaModel extends JPCampana {

	private static final long serialVersionUID = 3618701906990872114L;
	protected int mode;
	private JDPopupFinderModel popupFinder;
	private ClienteCriteria clienteCriteria;
	private CorporacionCriteria corporacionCriteria;
	final JPopupMenu popupMenuProductoCliente = new JPopupMenu();
	final JPopupMenu popupMenuDetalleCampana = new JPopupMenu();
	final JPopupMenu popupMenuBriefCampana = new JPopupMenu();
	final JPopupMenu popupMenuArchivoCampana = new JPopupMenu();
	protected ArrayList lista;
	protected CampanaIf campana;
	protected CorporacionIf corporacionIf;
	protected ClienteIf clienteIf;
	protected ClienteZonaIf clienteZonaIf;
	protected ProductoClienteIf productoClienteIf;

	DefaultTableModel modelDetalleCampana, modelBriefCampana,modelArchivoCampana;
	Vector<CampanaDetalleIf> detalleCampanaColeccion = new Vector<CampanaDetalleIf>();
	Vector<CampanaBriefIf> briefCampanaColeccion = new Vector<CampanaBriefIf>();
	Vector<CampanaArchivoIf> archivoCampanaColeccion = new Vector<CampanaArchivoIf>();
	Vector<File> archivosColeccionCampana = new Vector<File>();
	Vector<File> archivosColeccionBrief = new Vector<File>();
	Vector<CampanaBriefIf> briefsEliminadosColeccion = new Vector<CampanaBriefIf>();
	Vector<CampanaArchivoIf> archivosEliminadosColeccion = new Vector<CampanaArchivoIf>();
	Vector<CampanaDetalleIf> detallesCampanaEliminadosColeccion = new Vector<CampanaDetalleIf>();
	Double restanteParticipacion = 100.00;
	
	String thisName = this.getName();

	private static final int MAX_LONGITUD_NOMBRE = 200;
	private static final int MAX_LONGITUD_PUBLICO_OBJETIVO = 200;
	private static final int MAX_LONGITUD_OBSERVACION = 200;
	private static final int MAX_LONGITUD_DESCRIPCION_DETALLE = 100;
	private static final int MAX_LONGITUD_DESCRIPCION_BRIEF = 1000;
	private static final int MAX_LONGITUD_PARTICIPACION = 6;
	private static final int MAX_LONGITUD_RESTANTE = 6;
	private static final String NOMBRE_ESTADO_ACTIVA = "ACTIVA";
	private static final String NOMBRE_ESTADO_FINALIZADA = "FINALIZADA";
	private static final String NOMBRE_ESTADO_INACTIVA = "INACTIVA";
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	
	java.util.Date fechaInicio = new java.util.Date();
	java.util.Date fechaArchivo = new java.util.Date();
	
	private List<ProductoClienteIf> productoClienteList = new ArrayList<ProductoClienteIf>();
	private List<ProductoClienteIf> productoClienteListAnterior = new ArrayList<ProductoClienteIf>();
	
	
	//ADD 15 SEPTIEMBRE
	private List<ProductoClienteIf> productoClienteListToVersion = new ArrayList<ProductoClienteIf>();
	
	//ADD 16 SEPTIEMBRE
	private int selectedRowTblVersion = -1;
	
	private static final String NOMBRE_VERSION_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_VERSION_ESTADO_INACTIVO = "INACTIVO";
	private static final String VERSION_ESTADO_ACTIVO = "A";
	private static final String VERSION_ESTADO_INACTIVO = "I";	
	
	private Map<ProductoClienteIf,ArrayList<CampanaProductoVersionIf>> productoClienteVersionColeccion = new LinkedHashMap<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>>();
	private Map<ProductoClienteIf,ArrayList<CampanaProductoVersionIf>> productoClienteversionEliminadoColeccion = new LinkedHashMap<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>>();
		
	private ArrayList<CampanaProductoVersionIf> listVersionesTemp = new ArrayList<CampanaProductoVersionIf>();
	private Map<ProductoClienteIf,ArrayList<CampanaProductoVersionIf>> mapProductoClienteVersionesTemp = new LinkedHashMap<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>>();
	
	DefaultTableModel modelVersion;
	
	//ADD 19 SEPTIEMBRE  AKI AGREGAR productoClienteListAnterior PERO INCLUYENDO VERSIONES
	private Map<ProductoClienteIf,ArrayList<CampanaProductoVersionIf>> mapaProductoClienteVersiones = new LinkedHashMap<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>>();
	
	//ADD 20 SEPTIEMBRE
	private ArrayList<ProductoClienteIf> productosClientesListaTotal = new ArrayList<ProductoClienteIf>();
	
	//ADD 21 SEPTIEMBRE
	private Map<ProductoClienteIf,ArrayList<CampanaProductoVersionIf>> productoClienteVersionColeccionAnterior = new LinkedHashMap<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>>();
	private ArrayList<ComercialIf> listComericiales = new ArrayList<ComercialIf>();
	
	//ADD 22 SEPTIEMBRE
	private ArrayList<OrdenTrabajoIf> listOrdenesTrabajo = new ArrayList<OrdenTrabajoIf>();
	private Map<ProductoClienteIf,ArrayList<CampanaProductoVersionIf>> mapaProductoClienteVersionesAnterior = new LinkedHashMap<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>>();
	
	//CampanaProductoVersiones q recien se estan creando y tb las q existen en la base
	private Vector<CampanaProductoVersionIf> versionColeccion = new Vector<CampanaProductoVersionIf>();           //ADD 16 SEPTIEMBRE
	//CampanaProductoVersiones que exiten en la base
	private Vector<CampanaProductoVersionIf> versionColeccionAnterior = new Vector<CampanaProductoVersionIf>();	  //ADD 21 SEPTIEMBRE
	//CampanaProductoVersiones que recien estan siendo creadas
	private Vector<CampanaProductoVersionIf> versionColeccionTemporal = new Vector<CampanaProductoVersionIf>();   //ADD 23 SEPTIEMBRE
	//CampanaProductoVersiones que existen en la base y se quiere eliminar
	private Vector<CampanaProductoVersionIf> versionColeccionEliminadas = new Vector<CampanaProductoVersionIf>(); //ADD 22 SEPTIEMBRE	
	
	//productos que recien se seleccionan y tb los que existen en la base 
	private Vector<ProductoClienteIf> productoColeccion = new Vector<ProductoClienteIf>(); 
	//productos que existen en la base
	private Vector<ProductoClienteIf> productoColeccionAnterior = new Vector<ProductoClienteIf>();
	//productos que recien estan siendo seleccionandos
	private Vector<ProductoClienteIf> productoColeccionTemporal = new Vector<ProductoClienteIf>(); //ADD 23 SEPTIEMBRE
	//productos que existen en la base y se quiere eliminar
	private Vector<ProductoClienteIf> productoColeccionEliminados = new Vector<ProductoClienteIf>();
	
	//ADD 27 SEPTIEMBRE
	private ArrayList<CampanaProductoVersionIf> listCampanaProductosVersion = new ArrayList<CampanaProductoVersionIf>();
	private ArrayList<CampanaProductoIf> listCampanaProductos = new ArrayList<CampanaProductoIf>();
	
	
	public CampanaModel() {	
		initKeyListeners();
		setSorterTable(getTblVersion()); //ADD 15 SEPTIEMBRE
		this.showSaveMode();
		initListeners();
		//ADD 15 SEPTIEMBRE
		getTblVersion().addMouseListener(oMouseAdapterTblVersion); //preferible para consistencia de todas las tablas    
		getTblVersion().addKeyListener(oKeyAdapterTblVersion);	 //este pa q se carguen los datos arriba de la version seleccionada
						
		anchoColumnasTablas();
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtPublicoObjetivo().addKeyListener(new TextChecker(MAX_LONGITUD_PUBLICO_OBJETIVO,0));
		getTxtObservacion().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACION,0));
		getTxtDescripcionDetalle().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION_DETALLE,0));
		getTxtDescripcionBrief().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION_BRIEF, true, 0));
		getTxtParticipacion().addKeyListener(new TextChecker(MAX_LONGITUD_PARTICIPACION));
		getTxtParticipacion().addKeyListener(new NumberTextFieldDecimal());
		getTxtRestante().addKeyListener(new TextChecker(MAX_LONGITUD_RESTANTE));
		getTxtRestante().addKeyListener(new NumberTextFieldDecimal());
		
		//ADD 4 OCTUBRE
		getTxtNombreVersion().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getBtnActualizarProductos().setVisible(false);
		
		getCmbFechaArchivo().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaArchivo().setShowNoneButton(false);
		getCmbFechaInicio().setShowNoneButton(false);
				
		//ADD 15 SEPTIEMBRE
		getBtnEliminarDetalle().setText("");
		getBtnAgregarDetalle().setText("");
		getBtnAgregarBrief().setText("");
		getBtnActualizarBrief().setText("");
		getBtnEliminarBrief().setText("");
		getBtnAgregarArchivo().setText("");
		getBtnActualizarArchivo().setText("");
		getBtnEliminarArchivo().setText("");		
		//---- btnBuscarCorporacion ----
		getBtnBuscarCorporacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCorporacion().setToolTipText("Buscar Corporacion");		
		//---- btnBuscarCliente ----
		getBtnBuscarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCliente().setToolTipText("Buscar Cliente");		
		//---- btnAgregarDetalle ----
		getBtnAgregarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarDetalle().setToolTipText("Agregar Detalle de Campaña");				
		//---- btnEliminarDetalle ----
		getBtnEliminarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarDetalle().setToolTipText("Eliminar Detalle de Campaña");
		//---- btnAgregarArchivoBrief ----
		getBtnAgregarArchivoBrief().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/attachFile.png"));
		getBtnAgregarArchivoBrief().setToolTipText("Agregar Archivo Brief");		
		//---- btnAgregarBrief ----
		getBtnAgregarBrief().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarBrief().setToolTipText("Agregar Brief");
		//---- btnActualizarBrief ----
		getBtnActualizarBrief().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarBrief().setToolTipText("Actualizar Brief");		
		//---- btnEliminarBrief ----
		getBtnEliminarBrief().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarBrief().setToolTipText("Eliminar Brief");		
		//---- btnAgregarArchivoArchivo ----
		getBtnAgregarURLArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/attachFile.png"));
		getBtnAgregarURLArchivo().setToolTipText("Agregar Archivo Descripcion");		
		//---- btnAgregarArchivo ----
		getBtnAgregarArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarArchivo().setToolTipText("Agregar Archivo");		
		//---- btnActualizarArchivo ----
		getBtnActualizarArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarArchivo().setToolTipText("Actualizar Archivo");
		//---- btnEliminarArchivo ----
		getBtnEliminarArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarArchivo().setToolTipText("Eliminar Archivo");
		//---- btnActualizarProductos ----
		getBtnActualizarProductos().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarProductos().setToolTipText("Actualizar Productos");
		//---- btnAgregarVersion ----
		getBtnAgregarVersion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarVersion().setToolTipText("Agregar Versión");
		//---- btnActualizarVersion ----
		getBtnActualizarVersion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarVersion().setToolTipText("Actualizar Version");		
		//---- btnEliminarVersion ----
		getBtnEliminarVersion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarVersion().setToolTipText("Eliminar Version");		
	}
	
	private void anchoColumnasTablas() {
		TableColumn anchoColumna = getTableDetalleCampana().getColumnModel().getColumn(0);
		anchoColumna.setMinWidth(130);
		anchoColumna = getTableDetalleCampana().getColumnModel().getColumn(1);
		anchoColumna.setMinWidth(20);
		anchoColumna = getTableDetalleCampana().getColumnModel().getColumn(2);
		anchoColumna.setMinWidth(400);
		
		anchoColumna = getTableBrief().getColumnModel().getColumn(0);
		anchoColumna.setMinWidth(100);
		anchoColumna = getTableBrief().getColumnModel().getColumn(1);
		anchoColumna.setMinWidth(350);
		anchoColumna = getTableBrief().getColumnModel().getColumn(2);
		anchoColumna.setMinWidth(250);		
		
		anchoColumna = getTableArchivo().getColumnModel().getColumn(0);
		anchoColumna.setMinWidth(170);
		anchoColumna = getTableArchivo().getColumnModel().getColumn(1);
		anchoColumna.setMinWidth(30);
		anchoColumna = getTableArchivo().getColumnModel().getColumn(2);
		anchoColumna.setMinWidth(320);
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
	
	//se agrega los productos seleccionados a la lista
	public void crearListaProductos(){
		productoClienteList = null;
		productoClienteList = new ArrayList<ProductoClienteIf>();
		int[] selected = getCbListProductos().getCheckBoxListSelectedIndices();
		for(int i=0; i<selected.length; i++){
			productoClienteList.add((ProductoClienteIf)getCbListProductos().getModel().getElementAt(selected[i]));
		}
	}
	
	//ADD 19 SEPTIEMBRE 
	//agrega todos los productos clientes seleccionados y se agrega versiones a los que tienen versiones asignadas
	public void crearListaProductosVersiones(){
		mapaProductoClienteVersiones = null;
		mapaProductoClienteVersiones = new LinkedHashMap<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>>();
		int[] selected = getCbListProductos().getCheckBoxListSelectedIndices();
		for(int i = 0; i < selected.length; i++){
			ProductoClienteIf productoClienteIf = (ProductoClienteIf)getCbListProductos().getModel().getElementAt(selected[i]);
			ArrayList<CampanaProductoVersionIf> listCampanaProductoVersion = null;
			//si el producto cliente tiene versiones 
			//COMENTED 29 SEPTIEMBRE
			/*if (productoClienteVersionColeccion.containsKey(productoClienteIf)){
				listCampanaProductoVersion = productoClienteVersionColeccion.get(productoClienteIf);				
			}
			mapaProductoClienteVersiones.put(productoClienteIf,listCampanaProductoVersion);	*/	
			
			//ADD 9 SEPTIEMBRE
			for(ProductoClienteIf productoClienteColl : productoClienteVersionColeccion.keySet()){
				if (productoClienteColl.getId().compareTo(productoClienteIf.getId()) == 0){
					listCampanaProductoVersion = productoClienteVersionColeccion.get(productoClienteColl);					
					break;
				}
			}
			mapaProductoClienteVersiones.put(productoClienteIf,listCampanaProductoVersion);	
			
		}
	}
	//END 19 SEPTIEMBRE
	
	
	//MODIFIED 19 SEPTIEMBRE
	public void save() {
		//crearListaProductos(); COMENTED 19 SEPTIEMBRE
		
		//ADD 19 SEPTIEMBRE se agrega todos los productos seleccionados y se les asigna las versiones
		crearListaProductosVersiones();
		//END 19 SEPTIEMBRE
		
		if (validateFields()) {
			try {
				CampanaIf campana = registrarCampana();
				//COMENTED 19 SEPTIEMBRE
				//CampanaIf campanaGuardada = SessionServiceLocator.getCampanaSessionService().procesarCampana(campana, detalleCampanaColeccion, productoClienteList);
				//ADD 19 SEPTIEMBRE
				CampanaIf campanaGuardada = SessionServiceLocator.getCampanaSessionService().procesarCampana(campana, detalleCampanaColeccion, mapaProductoClienteVersiones);
				
				int posicionArchivo=0;
				if ( !Parametros.getUrlCarpetaServidor().equals("") ){
					ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(campana.getClienteId());
					String slashUrl = Parametros.getUrlCarpetaServidor().substring(Parametros.getUrlCarpetaServidor().length()-1,Parametros.getUrlCarpetaServidor().length());
					
					for (File archivo : archivosColeccionBrief){
						if(archivo!=null && !archivo.getName().equals("")){
							try{
		    					FileInputStreamSerializable archivoFuente = new FileInputStreamSerializable(archivo);
		    					int n = SessionServiceLocator.getFileManagerSessionService().guardarArchivoZip(archivoFuente, Parametros.getUrlCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashUrl, archivo.getName());
		    					if (n == 3){
		    						SpiritAlert.createAlert("Archivo: "+archivo.getName()+" no se guard\u00f3",SpiritAlert.WARNING);
		    					}
		    				} catch(Exception ex0){
		    					briefCampanaColeccion.remove(posicionArchivo);
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
					SessionServiceLocator.getCampanaSessionService().actualizarArchivosBrief(campanaGuardada,briefCampanaColeccion,briefsEliminadosColeccion,Parametros.getRutaWindowsCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashRuta+slashRuta);
				
					posicionArchivo = 0;
				
					for (File archivo : archivosColeccionCampana){
						if(archivo!=null && !archivo.getName().equals("")){
							try{
		    					FileInputStreamSerializable archivoFuente = new FileInputStreamSerializable(archivo);
		    					int n = SessionServiceLocator.getFileManagerSessionService().guardarArchivoZip(archivoFuente, Parametros.getUrlCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashUrl, archivo.getName());
		    					if (n == 3){
		    						SpiritAlert.createAlert("Archivo: "+archivo.getName()+" no se guard\u00f3",SpiritAlert.WARNING);
		    					}
		    				} catch(Exception ex0){
		    					archivoCampanaColeccion.remove(posicionArchivo);
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
					SessionServiceLocator.getCampanaSessionService().actualizarArchivosCampana(campanaGuardada,archivoCampanaColeccion,archivosEliminadosColeccion,Parametros.getRutaWindowsCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashRuta+slashRuta);
				}
								
				SpiritAlert.createAlert("Campaña guardada con éxito", SpiritAlert.INFORMATION);

				showSaveMode();

			} catch (Exception e) {
				if (e instanceof GenericBusinessException)
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				else
					SpiritAlert.createAlert("Ocurrió un error al guardar la Campaña", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	//ADD 19 SEPTIEMBRE
	
	/* COMENTED 19 SEPTIEMBRE
	 * public void save() {
		crearListaProductos();
				
		if (validateFields()) {
			try {
				CampanaIf campana = registrarCampana();
				CampanaIf campanaGuardada = SessionServiceLocator.getCampanaSessionService().procesarCampana(campana, detalleCampanaColeccion, productoClienteList);
				
				int posicionArchivo=0;
				if ( !Parametros.getUrlCarpetaServidor().equals("") ){
					ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(campana.getClienteId());
					String slashUrl = Parametros.getUrlCarpetaServidor().substring(Parametros.getUrlCarpetaServidor().length()-1,Parametros.getUrlCarpetaServidor().length());
					
					for (File archivo : archivosColeccionBrief){
						if(archivo!=null && !archivo.getName().equals("")){
							try{
		    					FileInputStreamSerializable archivoFuente = new FileInputStreamSerializable(archivo);
		    					int n = SessionServiceLocator.getFileManagerSessionService().guardarArchivoZip(archivoFuente, Parametros.getUrlCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashUrl, archivo.getName());
		    					if (n == 3){
		    						SpiritAlert.createAlert("Archivo: "+archivo.getName()+" no se guard\u00f3",SpiritAlert.WARNING);
		    					}
		    				} catch(Exception ex0){
		    					briefCampanaColeccion.remove(posicionArchivo);
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
					SessionServiceLocator.getCampanaSessionService().actualizarArchivosBrief(campanaGuardada,briefCampanaColeccion,briefsEliminadosColeccion,Parametros.getRutaWindowsCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashRuta+slashRuta);
				
					posicionArchivo = 0;
				
					for (File archivo : archivosColeccionCampana){
						if(archivo!=null && !archivo.getName().equals("")){
							try{
		    					FileInputStreamSerializable archivoFuente = new FileInputStreamSerializable(archivo);
		    					int n = SessionServiceLocator.getFileManagerSessionService().guardarArchivoZip(archivoFuente, Parametros.getUrlCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashUrl, archivo.getName());
		    					if (n == 3){
		    						SpiritAlert.createAlert("Archivo: "+archivo.getName()+" no se guard\u00f3",SpiritAlert.WARNING);
		    					}
		    				} catch(Exception ex0){
		    					archivoCampanaColeccion.remove(posicionArchivo);
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
					SessionServiceLocator.getCampanaSessionService().actualizarArchivosCampana(campanaGuardada,archivoCampanaColeccion,archivosEliminadosColeccion,Parametros.getRutaWindowsCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashRuta+slashRuta);
				}
								
				SpiritAlert.createAlert("Campaña guardada con éxito", SpiritAlert.INFORMATION);

				showSaveMode();

			} catch (Exception e) {
				if (e instanceof GenericBusinessException)
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				else
					SpiritAlert.createAlert("Ocurrió un error al guardar la Campaña", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	END COMENTED 19 SEPTIEMBRE */

	public void update() {
		//se agrega los productos seleccionados a la lista
		//crearListaProductos(); COMENTED 28 SEPTIEMBRE
		
		//ADD 28 SEPTIEMBRE se agrega todos los productos seleccionados y se les asigna las versiones
		crearListaProductosVersiones();
		//END 28 SEPTIEMBRE
		
		if (validateFields()) {
			try {
				CampanaIf campana = registrarCampanaForUpdate();
				//COMENTED 28 SEPTIEMBRE
				//CampanaIf campanaActualizada = SessionServiceLocator.getCampanaSessionService().actualizarCampana(campana, detalleCampanaColeccion, productoClienteList, detallesCampanaEliminadosColeccion);
				//ADD 28 SEPTIEMBRE
				CampanaIf campanaActualizada = SessionServiceLocator.getCampanaSessionService().actualizarCampana(campana, detalleCampanaColeccion, mapaProductoClienteVersiones,productoColeccionEliminados,versionColeccionEliminadas, detallesCampanaEliminadosColeccion);
				
				if ( !Parametros.getUrlCarpetaServidor().equals("") ){
					int posicionArchivo = 0;
					ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(campana.getClienteId());
					String slashUrl = Parametros.getUrlCarpetaServidor().substring(Parametros.getUrlCarpetaServidor().length()-1,Parametros.getUrlCarpetaServidor().length());
					
					for (File archivo: archivosColeccionBrief){
		    			if(archivo!=null && !archivo.getName().equals("")){
		    				try{
		    					FileInputStreamSerializable archivoFuente = new FileInputStreamSerializable(archivo);
		    					int n = SessionServiceLocator.getFileManagerSessionService().guardarArchivoZip(archivoFuente, Parametros.getUrlCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashUrl, archivo.getName());
		    					if (n == 3){
		    						SpiritAlert.createAlert("Archivo: "+archivo.getName()+" no se guard\u00f3",SpiritAlert.WARNING);
		    					}
		    				} catch(Exception ex0){
		    					briefCampanaColeccion.remove(posicionArchivo);
		    					((DefaultTableModel)getTableBrief().getModel()).removeRow(posicionArchivo);
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
					SessionServiceLocator.getCampanaSessionService().actualizarArchivosBrief(campanaActualizada,briefCampanaColeccion,briefsEliminadosColeccion,Parametros.getRutaWindowsCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashRuta+slashRuta);
					
					posicionArchivo = 0;
					for (File archivo: archivosColeccionCampana){
		    			if(archivo!=null && !archivo.getName().equals("")){
		    				try{
		    					FileInputStreamSerializable archivoFuente = new FileInputStreamSerializable(archivo);
		    					int n = SessionServiceLocator.getFileManagerSessionService().guardarArchivoZip(archivoFuente, Parametros.getUrlCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashUrl, archivo.getName());
		    					if (n == 3){
		    						SpiritAlert.createAlert("Archivo: "+archivo.getName()+" no se guard\u00f3",SpiritAlert.WARNING);
		    					}
		    				} catch(Exception ex0){
		    					archivoCampanaColeccion.remove(posicionArchivo);
		    					((DefaultTableModel)getTableArchivo().getModel()).removeRow(posicionArchivo);
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
					SessionServiceLocator.getCampanaSessionService().actualizarArchivosCampana(campanaActualizada,archivoCampanaColeccion,archivosEliminadosColeccion,Parametros.getRutaWindowsCarpetaServidor()+cliente.getIdentificacion().replaceAll(" ", "_")+slashRuta+slashRuta);
				}
								
				SpiritAlert.createAlert("Campaña actualizada con éxito", SpiritAlert.INFORMATION);
				
				showSaveMode();

			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof GenericBusinessException)
					SpiritAlert.createAlert(e.getMessage()+"!", SpiritAlert.ERROR);
				else
					SpiritAlert.createAlert("Error al actualizar la Campaña!", SpiritAlert.ERROR);
			}
		}
	}
	
	public void delete() {
		try {
			SessionServiceLocator.getCampanaSessionService().eliminarCampana(campana.getId());
			SpiritAlert.createAlert("Campaña eliminada con éxito", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof GenericBusinessException)
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			else
				SpiritAlert.createAlert("Error al eliminar la Campaña, puede estar asignada!", SpiritAlert.ERROR);
		}
	}
	
	public void report() {

	}
	
	public void refresh() {
		cargarComboTipoBrief();
		cargarComboTipoArchivo();
		cargarComboZonaCliente();
		cargarListaProductos();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpCampana().getSelectedIndex();
		int tabCount = this.getJtpCampana().getTabCount();
		
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpCampana().setSelectedIndex(selectedTab);
	}
	
	public void addDetail() {
		if (getJtpCampana().getSelectedIndex() == 2)
			agregarDetalleCampana();
		if (getJtpCampana().getSelectedIndex() == 3)
			agregarBriefCampana();
		if (getJtpCampana().getSelectedIndex() == 4)
			agregarArchivoCampana();
	}

	public void updateDetail() {

	}
	
	public void deleteDetail() {
		
	}

	private CampanaIf registrarCampana() throws GenericBusinessException {
		CampanaData data = new CampanaData();
		java.sql.Date fechaCreacion = new java.sql.Date(getCmbFechaInicio().getDate().getTime());
		String codigo = getCodigoCampana(fechaCreacion);
		data.setCodigo(codigo);
		data.setNombre(this.getTxtNombre().getText().toUpperCase());
		data.setClienteId(clienteIf.getId());
		data.setFechaini(fechaCreacion);
		data.setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));
		data.setPublicoObjetivo(this.getTxtPublicoObjetivo().getText());
		data.setObservacion(this.getTxtObservacion().getText());
		data.setUsuarioCreacionId(((UsuarioIf)Parametros.getUsuarioIf()).getId());
		data.setFechaCreacion(new java.sql.Date((new Date()).getTime()));
		
		return data;
	}
	
	private String getCodigoCampana(java.sql.Date fechaCampana) {
		String codigo = "";
		String anioCampana = Utilitarios.getYearFromDate(fechaCampana);
		codigo += anioCampana + "-";
		return codigo;
	}
	
	private CampanaIf registrarCampanaForUpdate() {

		campana.setNombre(this.getTxtNombre().getText().toUpperCase());
		campana.setClienteId(clienteIf.getId());
		campana.setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));
		campana.setPublicoObjetivo(this.getTxtPublicoObjetivo().getText());
		campana.setObservacion(this.getTxtObservacion().getText());
		campana.setUsuarioActualizacionId(((UsuarioIf)Parametros.getUsuarioIf()).getId());
		campana.setFechaActualizacion(new java.sql.Date((new Date()).getTime()));
		
		return campana;
	}
	
	public boolean validateFields() {
		try {
			String strNombre = this.getTxtNombre().getText();
			String strCorporacion = this.getTxtCorporacion().getText();
			String strCliente = this.getTxtCliente().getText();
			String strPublicoObjetivo = this.getTxtPublicoObjetivo().getText();
			Date fechaInicio = getCmbFechaInicio().getDate();
			Date fechaHoy = Utilitarios.dateHoy();
			
			if ((("".equals(strNombre)) || (strNombre == null))) {
				SpiritAlert.createAlert("Debe ingresar un nombre para la Campaña !!", SpiritAlert.WARNING);
				getJtpCampana().setSelectedIndex(0);
				getTxtNombre().grabFocus();
				return false;
			}
			if ((("".equals(strCorporacion)) || (strCorporacion == null))) {
				SpiritAlert.createAlert("Debe seleccionar una corporación para la Campaña !!", SpiritAlert.WARNING);
				getJtpCampana().setSelectedIndex(0);
				getBtnBuscarCorporacion().grabFocus();
				return false;
			}
			if ((("".equals(strCliente)) || (strCliente == null))) {
				SpiritAlert.createAlert("Debe seleccionar un cliente para la Campaña !!", SpiritAlert.WARNING);
				getJtpCampana().setSelectedIndex(0);
				getBtnBuscarCliente().grabFocus();
				return false;
			}
			/*if ((getMode() == SpiritMode.SAVE_MODE) && (fechaInicio.before(fechaHoy))) {
				SpiritAlert.createAlert("La Fecha Inicio no puede estar antes de la fecha actual!", SpiritAlert.INFORMATION);
				getJtpCampana().setSelectedIndex(0);
				getCmbFechaInicio().grabFocus();
				return false;
			}*/
			if ((("".equals(strPublicoObjetivo)) || (strPublicoObjetivo == null))) {
				SpiritAlert.createAlert("Debe ingresar el público objetivo para la Campaña !!", SpiritAlert.WARNING);
				getJtpCampana().setSelectedIndex(0);
				getTxtPublicoObjetivo().grabFocus();
				return false;
			}
			
			
			//ADD 19 SEPTIEMBRE
			if (mapaProductoClienteVersiones.size() == 0) {
				SpiritAlert.createAlert("Debe ingresar al menos un Producto para la Campaña !!", SpiritAlert.WARNING);
				getJtpCampana().setSelectedIndex(1);
				getCbListProductos().grabFocus();
				return false;
			}
			//END 19 SEPTIEMBRE
			
			try {
				//Para ver que no se quite un producto cliente que este siendo utilizado en una orden de trabajo.
				if(getMode() == SpiritMode.UPDATE_MODE){
					//Primero creo una lista con los productos que se han removido en la actualizacion
					List<ProductoClienteIf> productoClienteRemovidoList = new ArrayList<ProductoClienteIf>();
					boolean productoIgual = false;
					for(ProductoClienteIf productoClienteAnteriorIf : productoClienteListAnterior){
						for(ProductoClienteIf productoClienteNuevoIf : productoClienteList){
							if(productoClienteAnteriorIf.getId().compareTo(productoClienteNuevoIf.getId()) == 0){
								productoIgual = true;
							}
						}
						if(!productoIgual){
							productoClienteRemovidoList.add(productoClienteAnteriorIf);
						}
						productoIgual = false;
					}				
								
					//Luego comparo la lista de removidos con los productos que hay en las ordenes de esta campaña para ver si no se estan utilizando
					Collection ordenesTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().findOrdenTrabajoByCampanaId(campana.getId());
					Iterator ordenesTrabajoIt = ordenesTrabajo.iterator();
					while(ordenesTrabajoIt.hasNext()){
						OrdenTrabajoIf ordenTrabajoIf = (OrdenTrabajoIf)ordenesTrabajoIt.next();
						Collection ordenTrabajoProducto = SessionServiceLocator.getOrdenTrabajoProductoSessionService().findOrdenTrabajoProductoByOrdenTrabajoId(ordenTrabajoIf.getId());
						Iterator ordenTrabajoProductoIt = ordenTrabajoProducto.iterator();
						while(ordenTrabajoProductoIt.hasNext()){
							OrdenTrabajoProductoIf ordenTrabajoProductoIf = (OrdenTrabajoProductoIf)ordenTrabajoProductoIt.next();
							ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(ordenTrabajoProductoIf.getProductoClienteId());
							for(ProductoClienteIf productoClienteCampanaIf : productoClienteRemovidoList){
								if(productoClienteIf.getId().compareTo(productoClienteCampanaIf.getId()) == 0){
									SpiritAlert.createAlert("El producto " + productoClienteIf.getNombre() + ", no se puede quitar porque se encuentra en la Orden de Trabajo: " + ordenTrabajoIf.getCodigo() + ", marquelo nuevamente o actualice la Orden.", SpiritAlert.WARNING);
									getJtpCampana().setSelectedIndex(1);
									getCbListProductos().grabFocus();
									return false;
								}
							}
						}
					}					
				}				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}			
			
			if (briefCampanaColeccion.size() == 0) {
				SpiritAlert.createAlert("Debe ingresar los datos del Brief de la Campaña !!", SpiritAlert.WARNING);
				getJtpCampana().setSelectedIndex(4);
				getCmbTipoBrief().grabFocus();
				return false;
			}
			
			if (Parametros.getUrlCarpetaServidor().equals("")){
				Object[] options = {"   Si   ",
	    		"   No   "};
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
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return true;
	}
	
	public boolean validateFieldsDetalle() {
		String strDescripcion = this.getTxtDescripcionDetalle().getText();
		String strParticipacion = Utilitarios.removeDecimalFormat(this.getTxtParticipacion().getText());
		
		if (this.getCmbZonaCliente().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la zona del ciente asociada a este detalle !!", SpiritAlert.WARNING);
			getCmbZonaCliente().grabFocus();
			return false;
		}
		
		if ((("".equals(strParticipacion)) || (strParticipacion == null))) {
			SpiritAlert.createAlert("Debe ingresar el porcentaje de participación !!", SpiritAlert.WARNING);
			getTxtParticipacion().grabFocus();
			return false;
		}
		
		if (Double.parseDouble(strParticipacion) > 100) {
			SpiritAlert.createAlert("El valor de la participación debe ser menor o igual a 100 !!", SpiritAlert.WARNING);
			getTxtParticipacion().grabFocus();
			return false;
		}
		
		if (getCmbZonaCliente().getItemCount() == 1 && Double.valueOf(strParticipacion) < 100D && restanteParticipacion > 0.0) {
			SpiritAlert.createAlert("Debido a que el cliente tiene asociada una sola zona, la participación de ésta debe ser del 100% !!", SpiritAlert.WARNING);
			getTxtParticipacion().grabFocus();
			return false;
		}

		if (restanteParticipacion == 0.0) {
			SpiritAlert.createAlert("No se puede agregar registro. El valor restante de la participación es " + restanteParticipacion + " !!", SpiritAlert.WARNING);
			getTxtParticipacion().grabFocus();
			return false;
		}

		if (restanteParticipacion < Double.parseDouble(strParticipacion)) {
			SpiritAlert.createAlert("El valor de la participación debe ser menor o igual a " + restanteParticipacion + " !!", SpiritAlert.WARNING);
			getTxtParticipacion().grabFocus();
			return false;
		}
						
		/*if ((("".equals(strDescripcion)) || (strDescripcion == null))) {
			SpiritAlert.createAlert("Debe ingresar una descripción para el detalle !!", SpiritAlert.WARNING);
			getTxtDescripcionDetalle().grabFocus();
			return false;
		}*/
		
		return true;
	}

	public boolean validateFieldsCampanaBrief() {
		
		//String strURLBrief = this.getTxtURLBrief().getText();
		//String strDescripcion = this.getTxtDescripcionBrief().getText();
		
		if (this.getCmbTipoBrief().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo para el Brief !!", SpiritAlert.WARNING);
			getCmbTipoBrief().grabFocus();
			return false;
		}			
		return true;
	}

	public boolean validateFieldsCampanaArchivo() {
		try {
			String strURLArchivo = this.getTxtURLArchivo().getText();
			Date fechaArchivo = getCmbFechaArchivo().getDate();
			Date fechaHoy = Utilitarios.dateHoy();
							
			if (this.getCmbTipoArchivo().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar el tipo para el Archivo !!", SpiritAlert.WARNING);
				getCmbTipoArchivo().grabFocus();
				return false;
			}
			if (this.getCmbFechaArchivo().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar la fecha del Archivo !!", SpiritAlert.WARNING);
				this.getCmbFechaArchivo().grabFocus();
				return false;
			}
			if ((getMode() == SpiritMode.SAVE_MODE) && (fechaArchivo.before(fechaHoy))) {
				SpiritAlert.createAlert("La Fecha Archivo no puede estar antes de la fecha actual!", SpiritAlert.INFORMATION);
				getCmbFechaArchivo().grabFocus();
				return false;
			}
			if ((strURLArchivo == null) || ("".equals(strURLArchivo))) {
				SpiritAlert.createAlert("Debe seleccionar un archivo para la Campaña !!", SpiritAlert.WARNING);
				this.getBtnAgregarURLArchivo().grabFocus();
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void clean() {		
		corporacionIf = null;
		clienteIf = null;
				
		if (detalleCampanaColeccion.size() != 0) {
			for (int i = getTableDetalleCampana().getRowCount(); i > 0; --i)
				modelDetalleCampana.removeRow(i - 1);
		}

		if (briefCampanaColeccion.size() != 0) {
			for (int i = getTableBrief().getRowCount(); i > 0; --i)
				modelBriefCampana.removeRow(i - 1);
		}

		if (archivoCampanaColeccion.size() != 0) {
			for (int i = getTableArchivo().getRowCount(); i > 0; --i)
				modelArchivoCampana.removeRow(i - 1);
		}
		
		cleanListaProductos();

		detalleCampanaColeccion = new Vector<CampanaDetalleIf>();
		briefCampanaColeccion = new Vector<CampanaBriefIf>();
		archivoCampanaColeccion = new Vector<CampanaArchivoIf>();
		archivosColeccionCampana = new Vector<File>();
		archivosColeccionBrief = new Vector<File>();
		briefsEliminadosColeccion = new Vector<CampanaBriefIf>();
		archivosEliminadosColeccion = new Vector<CampanaArchivoIf>();
		detallesCampanaEliminadosColeccion = new Vector<CampanaDetalleIf>();
		restanteParticipacion = 100.00;
		productoClienteList = null;
		productoClienteList = new ArrayList<ProductoClienteIf>();
		productoClienteListAnterior = null;
		productoClienteListAnterior = new ArrayList<ProductoClienteIf>();
		
		//ADD 19 SEPTIEMBRE
		cleanProductoVersionesColecciones();
		//END 19 SEPTIEMBRE

		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getTxtCorporacion().setText("");
		this.getTxtCliente().setText("");
		this.getTxtPublicoObjetivo().setText("");
		this.getTxtObservacion().setText("");
		this.getCmbZonaCliente().setSelectedItem("");
		this.getCmbZonaCliente().removeAllItems();
		this.getTxtParticipacion().setText("");
		this.getTxtRestante().setText("");
		this.getTxtDescripcionDetalle().setText("");
		this.getTxtDescripcionBrief().setText("");
		this.getTxtURLBrief().setText("");
		this.getCmbTipoBrief().setSelectedItem("");
		this.getCmbTipoBrief().removeAllItems();
		Calendar currentDate = Calendar.getInstance();
		this.getCmbFechaInicio().setCalendar(currentDate);
		this.getTxtURLArchivo().setText("");
		this.getCmbTipoArchivo().setSelectedItem("");
		this.getCmbTipoArchivo().removeAllItems();
		this.getCmbFechaArchivo().setCalendar(currentDate);
	}
	
	//ADD 19 SEPTIEMBRE
	private void cleanProductoVersionesColecciones() {		
				
		productoClienteListToVersion.clear();
		versionColeccion = null;
		versionColeccion = new Vector<CampanaProductoVersionIf>();
		
		productoClienteVersionColeccion.clear();
		productoClienteversionEliminadoColeccion.clear();
			
		listVersionesTemp.clear();
		mapProductoClienteVersionesTemp.clear();
		
		mapaProductoClienteVersiones.clear();
		
		//ADD 21 SEPTIEMBRE
		selectedRowTblVersion = -1;
		productosClientesListaTotal.clear();
		productoClienteVersionColeccionAnterior.clear();
		versionColeccionAnterior.clear();
		
		//ADD 29 SEPTEIMBRE
		listComericiales = null;
		listComericiales = new ArrayList<ComercialIf>();
		versionColeccionTemporal.clear();
		versionColeccionEliminadas.clear();
		productoColeccion.clear();
		productoColeccionAnterior.clear();
		productoColeccionTemporal.clear();
		productoColeccionEliminados.clear();
		listCampanaProductosVersion.clear();
		
		
		getCmbProducto().removeAllItems();
		cleanTableVersion();
	}
	//END 19 SEPTIEMBRE
	
	//ADD 21 SEPTIEMBRE
	private void cleanTableVersion() {
		limpiarTabla(getTblVersion());		
	}
	//END 21 SEPTIEMBRE
	
	//ADD 27 SEPTIEMBRE
	private void cleanTableVersionByProducto(ProductoClienteIf productoClienteIf) {
		//Vector<Integer> indicesRemover = new Vector();
		
		try{
			
			MarcaProductoIf marcaProductoIf = SessionServiceLocator.getMarcaProductoSessionService().getMarcaProducto(productoClienteIf.getMarcaProductoId());	

			modelVersion =  (DefaultTableModel) getTblVersion().getModel();
			String nombreProductoCliente;
			for (int i = 0; i < modelVersion.getRowCount(); i++){
				nombreProductoCliente = "(" + marcaProductoIf.getNombre() +") - " + productoClienteIf.getNombre();
				
				if(nombreProductoCliente.compareTo(getTblVersion().getModel().getValueAt(i, 1).toString()) == 0 ){
					//indicesRemover.add(i);
					modelVersion.removeRow(i);
					i--; //ADD 28 SEPTIEMBRE
					//break;//ADD 3 oCTUBRE
				}
			}
			
			/*for (int i = 0; i < indicesRemover.size(); i++){
				modelVersion.removeRow(indicesRemover.get(i));
			}*/
			
			
		}catch(GenericBusinessException e){
			e.printStackTrace();
		}
	}
	//END 27 SEPTIEMBRE

	private void cleanListaProductos() {
		if(getCbListProductos().getModel().getSize()>0){
			getCbListProductos().getCheckBoxListSelectionModel().clearSelection();
			((DefaultListModel) getCbListProductos().getModel()).removeAllElements();
		}		
	}

	public void cargarCombos() {
			//Seteo del formato de combos de fecha
			getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
			getCmbFechaArchivo().setFormat(Utilitarios.setFechaUppercase());
			getCmbEstado().removeAllItems();
			getCmbEstado().addItem(NOMBRE_ESTADO_ACTIVA);
			getCmbEstado().addItem(NOMBRE_ESTADO_FINALIZADA);
			getCmbEstado().addItem(NOMBRE_ESTADO_INACTIVA);
			cargarComboTipoBrief();
			cargarComboTipoArchivo();
	}
	
	//ADD 15 SEPTIEMBRE
	//cargo el Combo de Productos con los Productos seleccionados
	private void cargarComboProductos(){
		productoClienteListToVersion.clear(); //ADD 26 SEPTIEMBRE
		
		int[] selected = getCbListProductos().getCheckBoxListSelectedIndices();
		boolean existeProducto = false;
		for(int i = 0; i < selected.length; i++){
			ProductoClienteIf productoClienteIf = (ProductoClienteIf)getCbListProductos().getModel().getElementAt(selected[i]);
			
			
			//ADD 30 SEPTIEMBRE			
			for(ProductoClienteIf productoClienteColl : productoClienteListToVersion){
				if (productoClienteColl.getId().compareTo(productoClienteIf.getId()) == 0){
					existeProducto = true;
					break;
				}
			}
			if(!existeProducto){
				productoClienteListToVersion.add(productoClienteIf);
			}			
		}
		
		
		getCmbProducto().removeAllItems();
		for (int i = 0; i < productoClienteListToVersion.size(); i ++){
			getCmbProducto().addItem(productoClienteListToVersion.get(i));
		}
	}
	//END 15 SEPTIWMBRE
	
	//ADD 20 SEPTIEMBRE 
	//verifica si el indice le pertenece a un Producto que contiene versiones
	private boolean existeProductoClienteVersionByProducto(ProductoClienteIf productoClienteIf){
		boolean existenVersiones = false;		
		//ADD 29 SEPTIEMBRE
		for (ProductoClienteIf productoClienteCol : productoClienteVersionColeccion.keySet()){
			if (productoClienteCol.getId().compareTo(productoClienteIf.getId()) == 0 ){
				//ADD 4 OCTUBRE
				ArrayList<CampanaProductoVersionIf> versionesList = productoClienteVersionColeccion.get(productoClienteCol);
				if (versionesList != null && versionesList.size() > 0){
					existenVersiones = true;
					break;
				}
				//END 4 OCTUBRE				
			}
		}
		//END 29 SEPTIEMBRE		
		return existenVersiones;
	}
	//END 20 SEPTIEMBRE
	
	//ADD 20 SEPTIEMBRE
	//verifica si existe al menos un Producto seleccionado que contenga Versiones
	private boolean existeProductoClientesInProductoClienteVersionColeccionMap(){
		boolean existenVersiones = false;
		
		//MODIFIED 26 SEPTIEMBRE
		if (productoClienteListToVersion.size() > 0){
			for (ProductoClienteIf productoClienteIf : productoClienteListToVersion){
				
				//ADD 30 SEPTIEMBRE
				for(ProductoClienteIf productoClienteColl : productoClienteVersionColeccion.keySet()){
					if (productoClienteColl.getId().compareTo(productoClienteIf.getId()) == 0){
						//ADD 4 OCTUBRE
						ArrayList<CampanaProductoVersionIf> versionesList = productoClienteVersionColeccion.get(productoClienteColl);
						if (versionesList != null && versionesList.size() > 0){
							existenVersiones = true;
							break;
						}
					}
				}
			}	
		}
		return existenVersiones;
	}
	//END 20 SEPTIEMBRE
	
		
	//ADD 21 SEPTIEMBRE
	//se obtiene todos los comerciales de la Campana
	public void cargarListaComerciales(){
		try{
			listComericiales = (ArrayList)SessionServiceLocator.getComercialSessionService().findComercialByCampanaId(campana.getId());	
		}
		catch(GenericBusinessException e){
			e.printStackTrace();
		}
	}
	//END 21 SEPTIEMBRE
	
	//ADD 27 SEPTIEMBRE
	public void listCampanaProductosVersion(){
		try{
			listCampanaProductosVersion	= (ArrayList)SessionServiceLocator.getCampanaProductoVersionSessionService().findCampanaProductoVersionByIdCampana(campana.getId(),Parametros.getIdEmpresa());
		}
		catch(GenericBusinessException e){
			e.printStackTrace();
		}
	}
	//END 27 SEPTIEMBRE
		
	//ADD 21 SEPTIEMBRE
	//verifico la existencia de comerciales con el idCampanaProductoVersion pasado como parametro
	private boolean existeComercialesWithCampanaProductoVersionId(Long idCampanaProductoVersion){
		boolean existen = false;
		if (idCampanaProductoVersion != null){ //ADD 4 OCTUBRE
			for (ComercialIf comercialIf : listComericiales){
				if (comercialIf.getCampanaProductoVersionId().compareTo(idCampanaProductoVersion) == 0){
					existen = true;
					break;
				}
			}
		}//ADD 4 OCTUBRE
		return existen;
	}
	//END 21 SEPTIEMBRE
	
	//se obtiene todos las Ordenes de Trabajo de la Campaña
	public void cargarListaOrdenesTrabajo(){
		try{
			listOrdenesTrabajo = (ArrayList)SessionServiceLocator.getOrdenTrabajoSessionService().findOrdenTrabajoByCampanaId(campana.getId());	
		}
		catch(GenericBusinessException e){
			e.printStackTrace();
		}
	}
	//END 21 SEPTIEMBRE
	
	//MODIFIED 27 SEPRIEMBRE ADD 21 SEPTIEMBRE
	//verifico si el indice le corresponde a un Producto a deseleccionar que posee versiones
	//utilizadas en al menos un comercial
	private boolean existenComercialesByProducto(ProductoClienteIf productoClienteIfUnSelected){
		boolean existen = false;
	
		//ADD 27 SEPTIEMBRE
		for (ComercialIf comercialIf : listComericiales){			
			if (comercialIf.getProductoClienteId().compareTo(productoClienteIfUnSelected.getId()) == 0){
				existen = true;
				break;
			}
		}
			
		return existen;
	}
	//END 21 SEPTIEMBRE
	
	
	//ADD 22 SEPTIEMBRE
	//verifico si el indice le corresponde a un Producto a deseleccionar que se 
	//utiliza en ordenes de trabajo
	private boolean existenOrdenesTrabajoByProducto(ProductoClienteIf productoClienteIfUnSelected){
		boolean existen = false;
			
		try{
			for (OrdenTrabajoIf ordenTrabajoIf : listOrdenesTrabajo){
				ArrayList<OrdenTrabajoProductoIf> ordenTrabajoProductoList = (ArrayList)SessionServiceLocator.getOrdenTrabajoProductoSessionService().findOrdenTrabajoProductoByOrdenTrabajoId(ordenTrabajoIf.getId());	
				
				for (OrdenTrabajoProductoIf ordenTrabajoProducto : ordenTrabajoProductoList){
					if (ordenTrabajoProducto.getProductoClienteId().compareTo(productoClienteIfUnSelected.getId()) == 0){
						existen = true;
						break;
					}
				}
			}
		}catch(GenericBusinessException e){
			e.printStackTrace();
		}
			
		return existen;
	}
	//END 22 SEPTIEMBRE
	
	//ADD 23 SEPTIEMBRE
	//agrego todos los productos seleccionados a los vectores de producto
	private void agregarProductosToVectores(){
		
		//int[] selected = getCbListProductos().getCheckBoxListSelectedIndices();
		//for(int i = 0; i < selected.length; i++){
		for(int i = 0; i < productoClienteListToVersion.size(); i++){
			//ProductoClienteIf productoClienteIf = (ProductoClienteIf)getCbListProductos().getModel().getElementAt(selected[i]);
			ProductoClienteIf productoClienteIf = (ProductoClienteIf)productoClienteListToVersion.get(i);
					
			//ADD 30 SEPTIEMBRE
			boolean existeProducto = false;
			for (ProductoClienteIf productoClienteColl : productoColeccion){
				if (productoClienteColl.getId().compareTo(productoClienteIf.getId()) == 0){
					existeProducto = true;
					break;
				}
			}
			if (!existeProducto){
				productoColeccion.add(productoClienteIf);
			}
			
			boolean existeProductoTemp = false;
			if (productoColeccionTemporal.size() > 0){
				for (ProductoClienteIf productoClienteTempColl : productoColeccionTemporal){		
					if (productoClienteTempColl.getId().compareTo(productoClienteIf.getId()) == 0 || 
						existeProductoInVectorAnteriorById(productoClienteIf.getId())){
						existeProductoTemp = true;
						break;
					}
				}			
				if (!existeProductoTemp){
					productoColeccionTemporal.add(productoClienteIf);
				}
			}else if (!existeProductoInVectorAnteriorById(productoClienteIf.getId())){
				productoColeccionTemporal.add(productoClienteIf);						
			}else if (existeProductoInVectorEliminadoById(productoClienteIf.getId())){
				productoColeccionTemporal.add(productoClienteIf);
			}	
			//END 30 SEPTIEMBRE
		}
	}
	//END 23 SEPTIEMBRE
	
	//ADD 3 OCTUBRE
	public boolean existeProductoInVectorAnteriorById(Long idProducto){
		boolean existeProducto = false;
		
		for(ProductoClienteIf productoCliente : productoColeccionAnterior){
			if (productoCliente.getId().compareTo(idProducto) == 0){
				existeProducto = true;
				break;
			}
		}
		return existeProducto;
	}
	//END 3 OCTUBRE
	
	//ADD 3 OCTUBRE
	public boolean existeProductoInVectorEliminadoById(Long idProducto){
		boolean existeProducto = false;
		
		for(ProductoClienteIf productoCliente : productoColeccionEliminados){
			if (productoCliente.getId().compareTo(idProducto) == 0){
				existeProducto = true;
				break;
			}
		}
		return existeProducto;
	}
	//END 3 OCTUBRE
	
	
	//ADD 23 SEPTIEMBRE
	//quito los productos existentes en los vectores ahora deseleccionadas
	private void quitarProductosOfVectores(){
		
		//productoClienteListToVersion
		Vector<Integer> indicesRemover = new Vector(); 
		
		for (int i = 0; i < productoColeccion.size(); i++){
			ProductoClienteIf productoClienteIf = productoColeccion.get(i);
						
			//ADD 3 OCTUBRE
			boolean existeProductoOfColeccion = false;
			for (ProductoClienteIf productoClienteOfList : productoClienteListToVersion){
				if (productoClienteOfList.getId().compareTo(productoClienteIf.getId()) == 0){
					boolean existeProductoOfColTemp = false;
					for (ProductoClienteIf productoClienteColTemp : productoColeccionTemporal){
						if (productoClienteColTemp.getId().compareTo(productoClienteIf.getId()) == 0){
							productoColeccionTemporal.remove(productoClienteColTemp);
							existeProductoOfColTemp = true;
							break;			
						}
					}					
					if (!existeProductoOfColTemp){
						productoColeccionEliminados.add(productoClienteIf);
					}					
					existeProductoOfColeccion = true;
					break;
				}
			}			
			if (!existeProductoOfColeccion){
				indicesRemover.add(i);
			}
			//END 3 OCTUBRE			
		}
		
		//ADD 30 SEPTIEMBRE
		Collections.sort(indicesRemover);
		Collections.reverse(indicesRemover);
		
		for (int i = 0; i < indicesRemover.size(); i++){
			productoColeccion.remove(indicesRemover.get(i));
		}
	}
	//END 23 SEPTIEMBRE
	
	//ADD 23 SEPTIEMBRE
	//verifica si existe al menos una OrdenesTrabajo con un Producto
	//en la lista de productoClienteListToVersion(productosSeleccionados) 
	private boolean existenProductoClienteInListOrdenesTrabajo(){
		boolean existenVersiones = false;
		
		try{
			for (OrdenTrabajoIf ordenTrabajoIf : listOrdenesTrabajo){
				ArrayList<OrdenTrabajoProductoIf> ordenTrabajoProductoList = (ArrayList)SessionServiceLocator.getOrdenTrabajoProductoSessionService().findOrdenTrabajoProductoByOrdenTrabajoId(ordenTrabajoIf.getId());	
				
				for (OrdenTrabajoProductoIf ordenTrabajoProductoIf : ordenTrabajoProductoList){
					for (ProductoClienteIf productoClienteIf : productoClienteListToVersion){
						
						if (productoClienteIf.getId().compareTo(ordenTrabajoProductoIf.getProductoClienteId()) == 0){
							existenVersiones = true;
							break;
						}
					}
				}
			}
			return existenVersiones;
		}catch(GenericBusinessException e){
			e.printStackTrace();
			return existenVersiones;
		}			
	}
	//END 23 SEPTIEMBRE
	
	//ADD 23 SEPTIEMBRE
	//verifica si existe al menos un Comercial de una Version 
	//de un Producto en la lista de productoClienteListToVersion (productosSeleccionados) 
	private boolean existenProductoClienteInListComerciales(){
		boolean existenVersiones = false;
		
		try{
			for (ComercialIf comercialIf : listComericiales){
				//ArrayList<OrdenTrabajoProductoIf> ordenTrabajoProductoList = (ArrayList)SessionServiceLocator.getOrdenTrabajoProductoSessionService().findOrdenTrabajoProductoByOrdenTrabajoId(ordenTrabajoIf.getId());	
				
				//for (OrdenTrabajoProductoIf ordenTrabajoProductoIf : ordenTrabajoProductoList){
				for (ProductoClienteIf productoClienteIf : productoClienteListToVersion){
						
					if (productoClienteIf.getId().compareTo(comercialIf.getProductoClienteId()) == 0){
						existenVersiones = true;
						break;
					}
				}
				//}
			}
			return existenVersiones;
		}catch(Exception e){
			e.printStackTrace();
			return existenVersiones;
		}			
	}
	//END 23 SEPTIEMBRE
	
	//ADD 26 SEPTIEMBRE
	//quito todas las versiones existentes de los vectores
	private void quitarCampanaProductoVersionesOfVectores(){
						
		for (int i = 0; i < versionColeccion.size(); i++){
			CampanaProductoVersionIf campanaProductoVersionIf = versionColeccion.get(i);
					
			//ADD 3 OCTUBRE
			boolean existeVersion = false;
			for(CampanaProductoVersionIf versionColTemp : versionColeccionTemporal){
				if (versionColTemp.getNombre().compareTo(campanaProductoVersionIf.getNombre()) == 0 &&
				    versionColTemp.getEstado().compareTo(campanaProductoVersionIf.getEstado()) == 0	){
					existeVersion = true;
					break;
				}
				
			}
			if (!existeVersion){
				versionColeccionEliminadas.add(campanaProductoVersionIf);
			}
			//END 3 OCTUBRE
		}
			
		versionColeccion.removeAllElements();
		versionColeccionTemporal.removeAllElements();
		
	}
	//END 26 SEPTIEMBRE
	
	//MODIFIED 3 OCTUBRE ADD 26 SEPTIEMBRE
	//eliminar las Versiones de los vectores segun Productos
	private void quitarCampanaProductoVersionesOfVectoresByProducto(ProductoClienteIf productoClienteIf){
		//Vector<Integer> indicesRemover = new Vector(); 
		
		//ADD 29 SEPTIEMBRE
		for (ProductoClienteIf productoClienteColl : productoClienteVersionColeccion.keySet()){ 
			if (productoClienteColl.getId().compareTo(productoClienteIf.getId()) == 0){
						
			//if (productoClienteVersionColeccion.containsKey(productoClienteIf)){  COMENTED 29 SEPTIEMBRE
				ArrayList<CampanaProductoVersionIf> listVersiones = productoClienteVersionColeccion.get(productoClienteColl);
				
				//for (int i = 0; i < versionColeccion.size(); i++){
				for (CampanaProductoVersionIf campanaProductoVersionIf: listVersiones){
						//ADD 3 0CTUBRE
						for(CampanaProductoVersionIf versionCol : versionColeccion){
							if (versionCol.getNombre().compareTo(campanaProductoVersionIf.getNombre()) == 0 &&
								versionCol.getEstado().compareTo(campanaProductoVersionIf.getEstado()) == 0	){
								boolean existeVersionColTemp = false;
								for(CampanaProductoVersionIf versionColTemp : versionColeccionTemporal){
									if (versionColTemp.getNombre().compareTo(campanaProductoVersionIf.getNombre()) == 0 && 
										versionColTemp.getEstado().compareTo(campanaProductoVersionIf.getEstado()) == 0	){
										versionColeccionTemporal.remove(versionColTemp);
										existeVersionColTemp = true;
										break;
									}
								}
								if (!existeVersionColTemp){
									versionColeccionEliminadas.add(campanaProductoVersionIf);
								}
								versionColeccion.remove(versionCol);	//ADD 29 SEPTIEMBRE
								break;
							}							
						}//ADD 29 SEPTIEMBRE
					//} COMENTED 3 OCTUBRE
				}
				
			}//ADD 29 SEPTIEMBRE
		}
	}
	//END MODIFIED 3 OCTUBRE
	
	
	//ADD 27 SEPTIEMBRE
	//eliminar las Versiones de los vectores segun Productos
	private void quitarProductoOfVectoresByProducto(ProductoClienteIf productoClienteIf){
		int indice,indiceTmp; 						
				
		//ADD 30 SEPTIEMBRE
		for (ProductoClienteIf productoClienteColl : productoColeccion){
			if (productoClienteColl.getId().compareTo(productoClienteIf.getId()) == 0){
				indice = productoColeccion.indexOf(productoClienteColl);
				boolean existeProductoOfColTemp = false;
				for (ProductoClienteIf productoClienteTempColl : productoColeccionTemporal){
					if (productoClienteTempColl.getId().compareTo(productoClienteIf.getId()) == 0){
						indiceTmp = productoColeccionTemporal.indexOf(productoClienteTempColl);
						if (indiceTmp != -1){
							productoColeccionTemporal.remove(indiceTmp);
							existeProductoOfColTemp = true;
							break;
						}/*else{
							productoColeccionEliminados.add(productoClienteIf);
						}*/
						//existeProducto = true;
						//break;
					}
				}
				//if (!existeProducto){
					//productoColeccionEliminados.add(productoClienteIf);
				//}
				
				if (!existeProductoOfColTemp){
					productoColeccionEliminados.add(productoClienteIf);
				}					
					
				
				productoColeccion.remove(indice);
				break;
			}
		}
	}
	//END 27 SEPTIEMBRE
	
	//ADD 27 SEPTIEMBRE
	//eliminar el Producto y sus Versiones del mapa segun el Producto
	private void quitarProductoAndCampanaProductoVersionOfVectoresByProducto(ProductoClienteIf productoClienteIf){
				
		Iterator productoClienteVersionColeccionIt = productoClienteVersionColeccion.keySet().iterator();
		while (productoClienteVersionColeccionIt.hasNext()){
			ProductoClienteIf productoClienteColl = (ProductoClienteIf) productoClienteVersionColeccionIt.next();
			if (productoClienteColl.getId().compareTo(productoClienteIf.getId()) == 0){
				productoClienteVersionColeccion.remove(productoClienteColl);	
				break;
			}			
		}
		
		Iterator mapProductoClienteVersionesTempIt = mapProductoClienteVersionesTemp.keySet().iterator();
		while (mapProductoClienteVersionesTempIt.hasNext()){
			ProductoClienteIf productoClienteColl = (ProductoClienteIf) mapProductoClienteVersionesTempIt.next();
			if (productoClienteColl.getId().compareTo(productoClienteIf.getId()) == 0){
				mapProductoClienteVersionesTemp.remove(productoClienteColl);	
				break;
			}			
		}		
		
	}
	//END 27 SEPTIEMBRE
	
	private void cargarComboTipoBrief(){
		try{
			List tiposBrief = (List) SessionServiceLocator.getTipoBriefSessionService().findTipoBriefByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTipoBrief(),tiposBrief);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboTipoArchivo(){
		try{
			List tiposArchivos = (List) SessionServiceLocator.getTipoArchivoSessionService().getTipoArchivoList();
			refreshCombo(getCmbTipoArchivo(),tiposArchivos);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboZonaCliente() {
		try{
			if(clienteIf != null){
				List zonaClientes = (List) SessionServiceLocator.getClienteZonaSessionService().findClienteZonaByClienteId(clienteIf.getId());
				refreshCombo(getCmbZonaCliente(),zonaClientes);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void showFindMode() {
		corporacionIf = null;
		clienteIf = null;
		getTxtCodigo().setBackground(Parametros.findColor);
		getTxtNombre().setBackground(Parametros.findColor);
		getTxtCliente().setBackground(Parametros.findColor);
		//getTxtCorporacion().setBackground(Parametros.findColor);
		getCmbFechaInicio().setBackground(Parametros.findColor);
		getCmbEstado().setBackground(Parametros.findColor);
		getCmbEstado().setSelectedIndex(-1);
		getTxtCodigo().setEditable(true);
		getTxtNombre().setEnabled(true);
		getTxtCorporacion().setEditable(false);
		getTxtCliente().setEditable(false);
		getTxtObservacion().setEnabled(false);
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getCmbFechaInicio().setEnabled(true);
		getCmbFechaInicio().setEditable(false);
		getCmbFechaInicio().setSelectedItem(null);
		getCmbZonaCliente().setEnabled(false);
		getTxtParticipacion().setEditable(false);
		getTxtRestante().setEditable(false);
		getTxtDescripcionDetalle().setEditable(false);
		getBtnAgregarDetalle().setEnabled(false);
		getCmbTipoBrief().setSelectedItem("");
		getCmbTipoBrief().removeAllItems();
		getCmbTipoArchivo().setEnabled(false);
		getCmbFechaArchivo().setEditable(false);
		getCmbFechaArchivo().setEnabled(false);
		getBtnAgregarURLArchivo().setEnabled(false);
		getBtnAgregarArchivo().setEnabled(false);
		getJtpCampana().setSelectedIndex(0);
		getTxtCodigo().grabFocus();
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
			FileFilter filterGIF = new ExtensionFileFilter("gif",new String[] { "gif" });
			fileChooser.addChoosableFileFilter(filterGIF);
			FileFilter filterDOC = new ExtensionFileFilter("doc",new String[] { "doc" });
			fileChooser.addChoosableFileFilter(filterDOC);
			fileChooser.setFileFilter(fileChooser.getAcceptAllFileFilter());
			int status = fileChooser.showOpenDialog(parent);

			if (status == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				//String root = fileChooser.getSelectedFile().toString();
				//root = root.replaceAll("\\\\", "\\\\\\\\");
				Object[] options ={"Si","No"};
				try {
					boolean existe = SessionServiceLocator.getFileManagerSessionService().existeArchivo(Parametros.getUrlCarpetaServidor(), selectedFile.getName());
					if (!existe){
						/**
						 * valido que botón ha sido presionado y le asigno al
						 * correspondiente textbox el path del archivo que haya
						 * seleccionado
						 */

						if ((actionEvent.getSource() == getBtnAgregarArchivoBrief()))
							getTxtURLBrief().setText(fileChooser.getSelectedFile().toString());
						else if ((actionEvent.getSource() == getBtnAgregarURLArchivo()))
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
						int opcion = JOptionPane.showOptionDialog(null, "El archivo ya existe en el servidor, desea reemplazarlo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "No");
						if (opcion == JOptionPane.YES_OPTION) {
							if ((actionEvent.getSource() == getBtnAgregarArchivoBrief()))
								getTxtURLBrief().setText(fileChooser.getSelectedFile().toString());
							else if ((actionEvent.getSource() == getBtnAgregarURLArchivo()))
								getTxtURLArchivo().setText(fileChooser.getSelectedFile().toString());
						} else {
							if ((actionEvent.getSource() == getBtnAgregarArchivoBrief()))
								getTxtURLBrief().setText("");
							else if ((actionEvent.getSource() == getBtnAgregarURLArchivo()))
								getTxtURLArchivo().setText("");
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
	
	//carga todos los productos de todas las marcas del cliente en las lista de chexbox
	private void cargarListaProductos(){
		try {
			
			if (clienteIf != null){ //ADD 21 SEPTIEMBRE
				DefaultListModel model = new DefaultListModel();
				List productosCliente = (ArrayList)SessionServiceLocator.getProductoClienteSessionService().findProductoClienteByClienteId(clienteIf.getId());
				Collections.sort((ArrayList)productosCliente,ordenadorProductoClienteIf);		
				
				//ADD 20 SEPTIEMBRE
				//agrego todos los productos del cliente a la lista de productos totalea
				productosClientesListaTotal = (ArrayList<ProductoClienteIf>) productosCliente;
				//END 20 SEPTIEMBRE
				
				Iterator productosClienteIt = productosCliente.iterator();
				
				while(productosClienteIt.hasNext()){
					ProductoClienteIf productoCliente = (ProductoClienteIf)productosClienteIt.next();
					model.addElement(productoCliente);
				}
				getCbListProductos().setModel(model);
			}else{//ADD 21 SEPTIEMBRE
				SpiritAlert.createAlert("Debe elegir un Cliente", SpiritAlert.WARNING);
			}//END 21 SEPTIEMBRE
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	Comparator<ProductoClienteIf> ordenadorProductoClienteIf = new Comparator<ProductoClienteIf>(){
		public int compare(ProductoClienteIf o1, ProductoClienteIf o2) {
			return o1.getMarcaProductoNombre().compareTo(o2.getMarcaProductoNombre());
		}		
	};

	/**
	 * 
	 */
	public void initListeners() {
		
		getBtnSeleccionarTodo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				//selecciona a todos los Productos de getCbListProductos()
				getCbListProductos().getCheckBoxListSelectionModel().addSelectionInterval(0, getCbListProductos().getModel().getSize() - 1);
				//ADD 15 SEPTIEMBRE
				cargarComboProductos();
				getCmbProducto().repaint();
				//END 15 SEPTIEMBRE
				
				//ADD 23 SEPTIEMBRE
				agregarProductosToVectores();
				
			}
		});
		
		
		getBtnDeseleccionarTodo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				//getCbListProductos().getCheckBoxListSelectionModel().clearSelection(); MOVED 23 SEPTIEMBRE
					
				//ADD 23 SEPTIEMBRE
				if (getMode() == SpiritMode.SAVE_MODE){//ADD 21 SEPTIEMBRE
			       	//verificamos si el indice le pertenece a un Producto que tiene asignado versiones
			       	if (existeProductoClientesInProductoClienteVersionColeccionMap()){
			       		Object[] options = {"   Si   ",	"   No   "};
			       		int n = JOptionPane.showOptionDialog(Parametros.getMainFrame(),
			       				"Existen Productos Comerciales que tienen Versiones asignadas" +
			    				"\n¿Desea continuar y Eliminar las Versiones? ",
			    				"Confirmaci\u00f3n",
			    				JOptionPane.YES_NO_OPTION,
			    				JOptionPane.QUESTION_MESSAGE,
			    				null,
			    				options,
			    				options[0]);
			       		if (n == 0){//x SI
			       			
			       			//ADD 26 SEPTIEMBRE
			       			//elimino todas las versiones, versiones temporales
			       			versionColeccion.removeAllElements();
			       			versionColeccionTemporal.removeAllElements();
			       			
			       			//elimino todos los productos, productos temporales
			       			productoColeccion.removeAllElements();
			       			productoColeccionTemporal.removeAllElements();
			       			
			       		    //elimino todas las campanasProductosVersiones,campanasProductosVersionesTemp
			       			productoClienteVersionColeccion.clear();
			       			mapProductoClienteVersionesTemp.clear();
			       			
			       			//limpio la tabla de las Versiones
			       			cleanTableVersion();
			       			//deselecciono todos los productos comerciales
			       			getCbListProductos().getCheckBoxListSelectionModel().clearSelection();//MOVED 23 SEPTIEMBRE
			       			//vuelvo a cargar el Combo de Productos
			       			cargarComboProductos();
			       			//END 26 SEPTIEMBRE			       			
			       		}
			       		else{ //x NO
			       			//NO SE HACE NADA
			       			//volvemos a marcar el checkbox del Producto Cliente deseleccionado
			       			//getCbListProductos().getCheckBoxListSelectionModel().setSelectionInterval(indexCheckBoxClick, indexCheckBoxClick);
			       		}
			       	}else{
			       		//ADD 27 SEPTIEMBRE
			       		//elimino todos los productos, productos temporales
		       			productoColeccion.removeAllElements();
		       			productoColeccionTemporal.removeAllElements();
			       		
			       		//ADD 26 SEPTIEMBRE
			       		//deselecciono todos los productos comerciales
			       		getCbListProductos().getCheckBoxListSelectionModel().clearSelection();//MOVED 23 SEPTIEMBRE
			       		//vuelvo a cargar el Combo de Productos
		       			cargarComboProductos();
		       			//END 26 SEPTIEMBRE
			       	}
		        }//ADD 23 SEPTIEMBRE
		        else if (getMode() == SpiritMode.UPDATE_MODE){
		        			        		
		        	//ADD 22 SEPTIEMBRE
		        	//verifico si existen Ordenes de Trabajo con alguno de los productos a deseleccionar
		        	if (!existenProductoClienteInListOrdenesTrabajo()){ 
		        			
			       		//ADD 26 SEPTIEMBRE
			       		//verifico si algun Producto a deseleccionar posee versiones utilizadas en comerciales
				       	if (!existenProductoClienteInListComerciales()){
				       		//no existen Comerciales con las Versiones del Producto a deseleccionar
				       		//pregunto si desea eliminar las Versiones
				       		
				       	//verificamos si el indice le pertenece a un Producto que tiene asignado versiones
					       	if (existeProductoClientesInProductoClienteVersionColeccionMap()){
					       		Object[] options = {"   Si   ",	"   No   "};
					       		int n = JOptionPane.showOptionDialog(Parametros.getMainFrame(),
					       				"Existen Productos Comerciales que tienen Versiones asignadas" +
					    				"\n¿Desea continuar y Eliminar las Versiones? ",
					    				"Confirmaci\u00f3n",
					    				JOptionPane.YES_NO_OPTION,
					    				JOptionPane.QUESTION_MESSAGE,
					    				null,
					    				options,
					    				options[0]);
					       		if (n == 0){//x SI
					       			
					       			//ADD 26 SEPTIEMBRE
					       			//elimino todas las versiones, versiones temporales y 
					       			//agrego las versiones a lista de eliminados
					       			quitarCampanaProductoVersionesOfVectores();
					       			
					       		
					       			//deselecciono todos los productos comerciales
					       			getCbListProductos().getCheckBoxListSelectionModel().clearSelection();
					       			//vuelvo a cargar el Combo de Productos
					       			cargarComboProductos();	
					       			//elimino todos los productos, productos temporales 
					       			//agrego los productos a lista de eliminados
					       			quitarProductosOfVectores();
					       			
					       		
					       			//elimino todas las campanasProductosVersiones,campanasProductosVersionesTemp
					       			productoClienteVersionColeccion.clear();
					       			mapProductoClienteVersionesTemp.clear();					       			
					       								       			
					       		 	//limpio la tabla de las Versiones
					       			cleanTableVersion();
					       			
					       			//END 26 SEPTIEMBRE			       			
					       		}
					       		else{ //x NO
					       			//NO SE HACE NADA
					       			//volvemos a marcar el checkbox del Producto Cliente deseleccionado
					       			//getCbListProductos().getCheckBoxListSelectionModel().setSelectionInterval(indexCheckBoxClick, indexCheckBoxClick);
					       		}
					       	}else{
					       						       		
					       		//ADD 27 SEPTIEMBRE
					       		//deselecciono todos los productos comerciales
				       			getCbListProductos().getCheckBoxListSelectionModel().clearSelection();
				       			//vuelvo a cargar el Combo de Productos
				       			cargarComboProductos();	
				       			//elimino todos los productos, productos temporales 
				       			//agrego los productos a lista de eliminados
				       			quitarProductosOfVectores();					       		
					       		
					       	}
				       	}
				       	else{
				       		//existen Comerciales con las Versiones del Producto a deseleccionar x lo q no se eliminara el Producto ni sus versiones		        			
				       		//getCbListProductos().getCheckBoxListSelectionModel().setSelectionInterval(indexCheckBoxClick, indexCheckBoxClick);
				       		SpiritAlert.createAlert("Existen Productos que no se pueden quitar porque existen Comerciales de sus Versiones", SpiritAlert.WARNING);
				        		
				       	}
		        	}else{ 
		        			//existen Ordenes de Trabajo con el Producto a deseleccionar x lo q no se quitará el Producto        			
			       		//getCbListProductos().getCheckBoxListSelectionModel().setSelectionInterval(indexCheckBoxClick, indexCheckBoxClick);
			       		SpiritAlert.createAlert("Existen Productos que no se pueden quitar porque se encuentran en Órdenes de Trabajo ", SpiritAlert.WARNING);
		        	}			       		
		        }
		        //END 23 SEPTIEMBRE
			}
		});
		
		//ADD 15 SEPTIEMBRE
		getBtnActualizarProductos().addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent evento){
				cargarComboProductos();
				
			}
		});		
		//END 15 SEPTIEMBRE
		
		//ADD 16 SEPTIEMBRE
		getBtnAgregarVersion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarVersion();			
			}
		});

		getBtnActualizarVersion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarVersion();			
			}
		});
		
		getBtnEliminarVersion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarVersion();			
			}
		});
		//END 16 SEPTIEMBRE
		
		
		//ADD 20 SEPTIEMBRE
		getCbListProductos().addMouseListener(new MouseAdapter() {
			
					
			
			public void mouseReleased(MouseEvent e) {
				
				//se obtiene el indice del checkBox donde se hizo click
			    int indexCheckBoxClick = getCbListProductos().locationToIndex(e.getPoint());
			    //se obtiene el Producto correspondiente al indice 
			    ProductoClienteIf productoClienteIfUnSelected = productosClientesListaTotal.get(indexCheckBoxClick);
			   		       				
		        //se pregunta si el checkBox de ese indice no esta seleccionado
		        if (!getCbListProductos().getCheckBoxListSelectionModel().isSelectedIndex(indexCheckBoxClick)){
		        	
		        	if (getMode() == SpiritMode.SAVE_MODE){//ADD 21 SEPTIEMBRE
		        		
			        	//verificamos si el indice le pertenece a un Producto que tiene asignado versiones
			        	if (existeProductoClienteVersionByProducto(productoClienteIfUnSelected)){
			        		Object[] options = {"   Si   ",	"   No   "};
			        		int n = JOptionPane.showOptionDialog(Parametros.getMainFrame(),
			    				"El Producto Comercial tiene Versiones asignadas" +
			    				"\n¿Desea continuar y Eliminar las Versiones? ",
			    				"Confirmaci\u00f3n",
			    				JOptionPane.YES_NO_OPTION,
			    				JOptionPane.QUESTION_MESSAGE,
			    				null,
			    				options,
			    				options[0]);
			        		if (n == 0){//x SI		        			
			        			//ADD 27 SEPTIEMBRE
			        			
			        			//eliminando versiones de los vectores segun Producto Cliente
			        			quitarCampanaProductoVersionesOfVectoresByProducto(productoClienteIfUnSelected);
			        			
			        			//eliminando producto de los vectores segun Producto Cliente
			        			quitarProductoOfVectoresByProducto(productoClienteIfUnSelected);
			        						        			
			        			//elimino el producto y sus campanasProductosVersiones,campanasProductosVersionesTemp 
			        			//del mapa segun Producto
			        			quitarProductoAndCampanaProductoVersionOfVectoresByProducto(productoClienteIfUnSelected);
			        					
			        			//limpio la tabla de las Versiones segun producto
			        			cleanTableVersionByProducto(productoClienteIfUnSelected);
			        			
				       			//vuelvo a cargar el Combo de Productos
				       			cargarComboProductos();
				       			//END 27 SEPTIEMBRE		
			        		}
			        		else{ //x NO
			        			//volvemos a marcar el checkbox del Producto Cliente deseleccionado
			        			getCbListProductos().getCheckBoxListSelectionModel().addSelectionInterval(indexCheckBoxClick, indexCheckBoxClick);
			        			//vuelvo a cargar el Combo de Productos
				       			cargarComboProductos();
			        		}
			        	}else{
				       		//ADD 27 SEPTIEMBRE
			        		//eliminando producto de los vectores segun Producto Cliente
		        			quitarProductoOfVectoresByProducto(productoClienteIfUnSelected);
				       		//vuelvo a cargar el Combo de Productos
			       			cargarComboProductos();
			       			//END 27 SEPTIEMBRE
				       	}			        	
		        	}//ADD 27 SEPTIEMBRE
		        	else if (getMode() == SpiritMode.UPDATE_MODE){
		        			        		
		        		//ADD 22 SEPTIEMBRE
		        		//verifico si existen Ordenes de Trabajo con el Producto Cliente a deseleccionar
		        		if (!existenOrdenesTrabajoByProducto(productoClienteIfUnSelected)){ 
		        			
			        		//ADD 21 SEPTIEMBRE
			        		//verifico si Producto a deseleccionar versiones utilizadas en al menos un comercial
				        	if (!existenComercialesByProducto(productoClienteIfUnSelected)){
				        		//no existen Comerciales con las Versiones del Producto a deseleccionar
				        		//pregunto si desea eliminar las Versiones
				        		
				        		if (existeProductoClienteVersionByProducto(productoClienteIfUnSelected)){//CGDF
					        		Object[] options = {"   Si   ",	"   No   "};
						        	int n = JOptionPane.showOptionDialog(Parametros.getMainFrame(),
						    			"El Producto Comercial tiene Versiones asignadas" +
						    			"\n¿Desea continuar y Eliminar el Producto y sus Versiones? ",
						    			"Confirmaci\u00f3n",
						    			JOptionPane.YES_NO_OPTION,
						    			JOptionPane.QUESTION_MESSAGE,
						    			null,
						    			options,
						    			options[0]);
						        	if (n == 0){//x SI
						        		//ADD 27 SEPTIEMBRE
					        			
					        			//eliminando versiones de los vectores segun Producto Cliente
					        			quitarCampanaProductoVersionesOfVectoresByProducto(productoClienteIfUnSelected);
					        			
					        			//eliminando producto de los vectores segun Producto Cliente
					        			quitarProductoOfVectoresByProducto(productoClienteIfUnSelected);
					        						        			
					        			//elimino el producto y sus campanasProductosVersiones,campanasProductosVersionesTemp 
					        			//del mapa segun Producto
					        			quitarProductoAndCampanaProductoVersionOfVectoresByProducto(productoClienteIfUnSelected);
					        					
					        			//limpio la tabla de las Versiones segun producto
					        			cleanTableVersionByProducto(productoClienteIfUnSelected);
					        			
						       			//vuelvo a cargar el Combo de Productos
						       			cargarComboProductos();
						       			//END 27 SEPTIEMBRE		
						        	}else{//x NO
						        		//x NO
					        			//volvemos a marcar el checkbox del Producto Cliente deseleccionado
						        		getCbListProductos().getCheckBoxListSelectionModel().addSelectionInterval(indexCheckBoxClick, indexCheckBoxClick);
					        			//vuelvo a cargar el Combo de Productos
						       			cargarComboProductos();
						        	}
				        		}else{
				        			//ADD 27 SEPTIEMBRE
					        		//eliminando producto de los vectores segun Producto Cliente
				        			quitarProductoOfVectoresByProducto(productoClienteIfUnSelected);
						       		//vuelvo a cargar el Combo de Productos
					       			cargarComboProductos();
					       			//END 27 SEPTIEMBRE
				        		}
				        	}
				        	else{
				        		//existen Comerciales con las Versiones del Producto a deseleccionar x lo q no se eliminara el Producto ni sus versiones		        			
				        		getCbListProductos().getCheckBoxListSelectionModel().addSelectionInterval(indexCheckBoxClick, indexCheckBoxClick);
				        		SpiritAlert.createAlert("No se puede quitar el Producto comercial " + productoClienteIfUnSelected.getNombre() + "\nporque existen Comerciales de sus Versiones", SpiritAlert.WARNING);
				        		
				        	}
				        	//END 21 SEPTIEMBRE
				        	
		        		}else{ //ADD 22 SEPTIEMBRE
		        			//existen Ordenes de Trabajo con el Producto a deseleccionar x lo q no se quitará el Producto 
		        			getCbListProductos().getCheckBoxListSelectionModel().addSelectionInterval(indexCheckBoxClick, indexCheckBoxClick);
			        		SpiritAlert.createAlert("No se puede quitar el Producto comercial " + productoClienteIfUnSelected.getNombre() + "\nporque se encuentran en Órdenes de Trabajo de la Campaña", SpiritAlert.WARNING);
			        		
		        		}
		        		//END 22 SEPTIEMBRE
			        		
			        		
		        	}
		        	//END 27 SEPTIEMBRE
		        	
		        }else{
		        	//cargar Combo de Productos
		        	cargarComboProductos();
		        	//ADD 23 SEPTIEMBRE
		        	//agregamos el producto a los vectores si que no los contienen
		        	agregarProductosToVectores();
		        }
			}
		});			
		//END 20 SEPTIEMBRE
				
		//ADD 20 SEPTIEMBRE
		/*getCbListProductos().addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				getCbListProductos().getCheckBoxListSelectionModel(); 
			}
		});*/		
		//END 20 SEPTIEMBRE
		
		// Opcion Que Permite Borrar un regitsro deseado de la tabla de detalle
		JMenuItem itemEliminarDetalleCampana = new JMenuItem("Eliminar");
		popupMenuDetalleCampana.add(itemEliminarDetalleCampana);
		// Añado el listener de menupopup
		itemEliminarDetalleCampana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetalleCampana();
			}
		});

		// Listenner de la tabla de detalles de campana
		getTableDetalleCampana().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if (getTableDetalleCampana().getSelectedRow() != -1) {
					for (int i = 0; i < getCmbZonaCliente().getItemCount(); i++) {
						ClienteZonaIf bean = (ClienteZonaIf) getCmbZonaCliente().getItemAt(i);
						if (bean.getNombre().compareTo(getTableDetalleCampana().getModel().getValueAt(getTableDetalleCampana().getSelectedRow(), 0).toString()) == 0)
							getCmbZonaCliente().setSelectedItem(bean);
							getCmbZonaCliente().repaint();
					}

					getTxtParticipacion().setText(getTableDetalleCampana().getModel().getValueAt(getTableDetalleCampana().getSelectedRow(),1).toString());
					
					if(getTableDetalleCampana().getModel().getValueAt(getTableDetalleCampana().getSelectedRow(),2) != null){
						getTxtDescripcionDetalle().setText(getTableDetalleCampana().getModel().getValueAt(getTableDetalleCampana().getSelectedRow(),2).toString());
					}else{
						getTxtDescripcionDetalle().setText("");
					}
				}
			}

			public void mouseReleased(MouseEvent evt) {
				if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTableDetalleCampana().getModel().getRowCount() > 0) {
					popupMenuDetalleCampana.show(evt.getComponent(),evt.getX(), evt.getY());
				}
			}
		});

		// Opcion Que Permite Borrar un regitsro deseado de la tabla de brief
		JMenuItem itemEliminarBriefCampana = new JMenuItem("Eliminar");
		popupMenuBriefCampana.add(itemEliminarBriefCampana);
		// Añado el listener de menupopup
		itemEliminarBriefCampana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarBriefCampana();
			}
		});

		// Opcion Que Permite Visualizar un archivo deseado de la tabla de briefs
		JMenuItem itemVerBriefArchivo = new JMenuItem("Visualizar Archivo");
		popupMenuBriefCampana.add(itemVerBriefArchivo);
		// Añado el listener de menupopup
		itemVerBriefArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getTableBrief().getSelectedRow() != -1) {
					try {
						String urlArchivo = (String) getTableBrief().getModel().getValueAt(getTableBrief().getSelectedRow(), getTableBrief().getColumnModel().getColumnIndex("URL"));
						Archivos.abrirArchivoDesdeServidor(urlArchivo);
					} catch (IOException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				} else {
					SpiritAlert.createAlert("Debe elegir el registro de la lista a visualizar !!!", SpiritAlert.WARNING);
				}
			}
		});

		// Listenner de la tabla de reunion brief
		getTableBrief().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if (getTableBrief().getSelectedRow() != -1) {
					for (int i = 0; i < getCmbTipoBrief().getItemCount(); i++) {
						TipoBriefIf bean = (TipoBriefIf) getCmbTipoBrief().getItemAt(i);
						if (bean.getNombre().compareTo(getTableBrief().getModel().getValueAt(getTableBrief().getSelectedRow(), 0).toString()) == 0){
							getCmbTipoBrief().setSelectedItem(bean);
							getCmbTipoBrief().repaint();
						}
					}
					if(getTableBrief().getModel().getValueAt(getTableBrief().getSelectedRow(), 1) != null){
						getTxtDescripcionBrief().setText(getTableBrief().getModel().getValueAt(getTableBrief().getSelectedRow(), 1).toString());
					}else{
						getTxtDescripcionBrief().setText("");
					}
					getTxtURLBrief().setText(getTableBrief().getModel().getValueAt(getTableBrief().getSelectedRow(), 2).toString());
				}
			}

			public void mouseReleased(MouseEvent evt) {
				if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTableBrief().getModel().getRowCount() > 0) {
					popupMenuBriefCampana.show(evt.getComponent(), evt.getX(),evt.getY());
				}
			}
		});

		// Opcion Que Permite Borrar un regitsro deseado de la tabla de archivo
		JMenuItem itemEliminarArchivoCampana = new JMenuItem("Eliminar");
		popupMenuArchivoCampana.add(itemEliminarArchivoCampana);
		// Añado el listener de menupopup
		itemEliminarArchivoCampana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarArchivoCampana();
			}
		});

		// Opcion Que Permite Visualizar un archivo deseado de la tabla de archivos
		JMenuItem itemVerCampanaArchivo = new JMenuItem("Visualizar Archivo");
		popupMenuArchivoCampana.add(itemVerCampanaArchivo);
		// Añado el listener de menupopup
		itemVerCampanaArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getTableArchivo().getSelectedRow() != -1) {
					try {
						String urlArchivo = (String) getTableArchivo().getModel().getValueAt(
								getTableArchivo().getSelectedRow(), getTableArchivo().getColumnModel().getColumnIndex("URL"));
						Archivos.abrirArchivoDesdeServidor(urlArchivo);
					} catch (IOException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				} else {
					SpiritAlert.createAlert("Debe elegir el registro de la lista a visualizar !!!", SpiritAlert.WARNING);
				}

			}
		});

		// Listener de la tabla de campana archivo
		getTableArchivo().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if (getTableArchivo().getSelectedRow() != -1) {
					for (int i = 0; i < getCmbTipoArchivo().getItemCount(); i++) {
						TipoArchivoIf bean = (TipoArchivoIf) getCmbTipoArchivo().getItemAt(i);
						if (bean.getNombre().compareTo(getTableArchivo().getModel().getValueAt(getTableArchivo().getSelectedRow(), 0).toString()) == 0)
							getCmbTipoArchivo().setSelectedItem(bean);
							getCmbTipoArchivo().repaint();
					}

					// Extraigo el objeto campana archivo de la coleccion segun
					// el registro seleccionado de la tabla
					CampanaArchivoIf campanaArchivoTemp = (CampanaArchivoIf) archivoCampanaColeccion.get(getTableArchivo().getSelectedRow());
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(campanaArchivoTemp.getFecha());
					// Muestro la fecha
					getCmbFechaArchivo().setCalendar(calendar);
					
					// Muestro el URL
					getTxtURLArchivo().setText(getTableArchivo().getModel().getValueAt(getTableArchivo().getSelectedRow(), 2).toString());

				}
			}

			public void mouseReleased(MouseEvent evt) {
				if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTableArchivo().getModel().getRowCount() > 0) {
					popupMenuArchivoCampana.show(evt.getComponent(),evt.getX(), evt.getY());
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
					// Seteo los text fields en blanco
					getTxtCliente().setText("");
					cleanListaProductos();
				}
			}
		});		

		// Manejo los eventos de Buscar Cliente
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
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
					if ( popupFinder.getElementoSeleccionado() != null ){
						clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
						getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
						
						cleanListaProductos();
						//carga todos los productos de todas las marcas del cliente en las lista de chexbox
						cargarListaProductos();
						
						if (corporacionIf == null) {
							corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
							getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
						}
						
						getCmbZonaCliente().setEnabled(true);
						getCmbZonaCliente().setSelectedItem("");
						getCmbZonaCliente().removeAllItems();
						
						cargarComboZonaCliente();
						
						// Habilito los textfield y el boto de agregar detalle
						getTxtDescripcionDetalle().setEditable(true);
						getTxtParticipacion().setEditable(true);
						getTxtRestante().setEditable(false);
						getTxtRestante().setText(restanteParticipacion.toString());
						getBtnAgregarDetalle().setEnabled(true);
						// Si la coleccion de detalle no esta vacia
						if (detalleCampanaColeccion.size() != 0) {
							for (int i = getTableDetalleCampana().getRowCount(); i > 0; --i)
								modelDetalleCampana.removeRow(i - 1);
							detalleCampanaColeccion = new Vector();
						}
					}
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		});

		// Veo si el botnon de agregar detalle de campaña ha sido presionado
		getBtnAgregarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDetalleCampana();
			}
		});
		
		getBtnEliminarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetalleCampana();
			}
		});

		// Veo si el botnon de agregar brief de campana ha sido presionado
		getBtnAgregarBrief().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarBriefCampana();
			}
		});
		
		getBtnActualizarBrief().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarBriefCampana();
			}
		});
		
		getBtnEliminarBrief().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarBriefCampana();
			}
		});

		// Veo si el botón de agregar archivo de campana ha sido presionado
		getBtnAgregarArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarArchivoCampana();
			}
		});
		
		getBtnActualizarArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarArchivoCampana();
			}
		});

		getBtnEliminarArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarArchivoCampana();
			}
		});

		// Veo si la fecha es valida, es decir si es que no es antes del dia de hoy
		getBtnAgregarArchivoBrief().addActionListener(oActionListenerAgregarArchivo);
		getBtnAgregarURLArchivo().addActionListener(oActionListenerAgregarArchivo);
	}
		
	//ADD 15 SEPTIEMBRE
	MouseListener oMouseAdapterTblVersion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			//if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblVersion().getModel().getRowCount()>0)
	       //     popupMenuComercialCliente.show(evt.getComponent(), evt.getX(), evt.getY());
	       // else
	    		enableSelectedRowForUpdate(evt);
		}
	};
		
	//ADD 15 SEPTIEMBRE
	KeyListener oKeyAdapterTblVersion = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	//ADD 15 SEPTIEMBRE
	//muestro la versión seleccionada de la tabla version en los componentes respectivos
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
        	try {
        		
        		int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setSelectedRowTblVersion(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
									
				CampanaProductoVersionIf versionTemp= (CampanaProductoVersionIf) versionColeccion.get(getSelectedRowTblVersion());
        		//setSelectedRowTblVersion(getSelectedRowTblVersion());
            	
            	for(int i=0;i < getCmbProducto().getItemCount();i++){
					ProductoClienteIf bean = (ProductoClienteIf) getCmbProducto().getItemAt(i);
					MarcaProductoIf marcaProductoIf = SessionServiceLocator.getMarcaProductoSessionService().getMarcaProducto(bean.getMarcaProductoId());
					String nombreProductoClienteSeleccionado = "("+marcaProductoIf.getNombre()+") - "+ bean.getNombre();
					if(nombreProductoClienteSeleccionado.compareTo(getTblVersion().getModel().getValueAt(getSelectedRowTblVersion(), 1).toString()) == 0 ){
						getCmbProducto().setSelectedItem(bean);
						getCmbProducto().repaint();
						break;
					}						
				}
            
            	getTxtCodigoVersion().setText(versionTemp.getCodigo());
            	getTxtNombreVersion().setText(versionTemp.getNombre());
				            	
            	if (versionTemp.getEstado().equals("A")) {
            		getCmbEstadoVersion().setSelectedItem(NOMBRE_VERSION_ESTADO_ACTIVO);
            	} else {
            		getCmbEstadoVersion().setSelectedItem(NOMBRE_VERSION_ESTADO_INACTIVO);
            	}
								
			} catch (Exception e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	//END 15 SEPTIEMBRE
	
	//ADD 16 SEPTIEMBRE
	//agrego una nueva versión a la tabla versión y a la colección de versiones
	private void agregarVersion() {
		try {	
			setSelectedRowTblVersion(-1);
			boolean isExisteVersion = false;
			//int validador = 0;
			String mensajeError = null;
			//0   valido se puede agregar la version
			//-1  No puede ser agregada, ya existe esta Version (para otro Producto Comercial o para el mismo que esta en estado ACTIVO)
						
			if (validateFieldsVersion()) {
				
				ProductoClienteIf productoCliente = (ProductoClienteIf)getCmbProducto().getSelectedItem();
				MarcaProductoIf marcaProductoIf = SessionServiceLocator.getMarcaProductoSessionService().getMarcaProducto(productoCliente.getMarcaProductoId());
				String nombreVersion = getTxtNombreVersion().getText();
				String estadoVersion = (String) getCmbEstadoVersion().getSelectedItem();
				
				if (estadoVersion.trim().equals(NOMBRE_VERSION_ESTADO_ACTIVO)) {
					estadoVersion = "A";
            	} else {
            		estadoVersion = "I";
            	}
								
				//Si la coleccion tiene algun elemento
				if(versionColeccion.size() != 0){
					//Recorro la coleccion de versiones
					int contador = 0;
	    			for(int i = 0; i < versionColeccion.size(); i++){
	    				CampanaProductoVersionIf versionTemp = (CampanaProductoVersionIf) versionColeccion.get(i);
	    				//Si la version cargada ya esta en lista, entonces muestro un mensaje de error
	    				if( versionTemp.getNombre().compareTo(nombreVersion) == 0  &&  
	    					versionTemp.getEstado().compareTo(estadoVersion) == 0){ 	    					
    						contador++;
    						mensajeError = "No puede ser agregada, ya existe esta Versión";
	    				} 
	    				if (contador >= 1){
	    					isExisteVersion = true;
    						break;
	    				}
	    			}
	    		}			
				
				modelVersion =  (DefaultTableModel) getTblVersion().getModel();
				Vector<String> filaVersion = new Vector<String>();
				ArrayList<CampanaProductoVersionIf> campanaProductoVersionList;
				ArrayList<CampanaProductoVersionIf> campanaProductoVersionTempList;
				//Si la Version no existe la inserto
				if (isExisteVersion == false) {
					CampanaProductoVersionData data = new CampanaProductoVersionData();
					
					//Seteo los datos del objeto 
					data.setNombre(nombreVersion);
											
					if (getCmbEstadoVersion().getSelectedItem().equals(NOMBRE_VERSION_ESTADO_ACTIVO)) {
						data.setEstado(VERSION_ESTADO_ACTIVO);
					} else {
						data.setEstado(VERSION_ESTADO_INACTIVO);
					}
					
					versionColeccion.add(data);
										
					//ADD 30 SEPTIEMBRE
					if (productoClienteVersionColeccion.size() > 0){
						boolean existeProducto = false;
						for (ProductoClienteIf productoClienteColl : productoClienteVersionColeccion.keySet()){
							if (productoClienteColl.getId().compareTo(productoCliente.getId()) == 0){
								campanaProductoVersionList = productoClienteVersionColeccion.get(productoClienteColl);
								campanaProductoVersionList.add(data);
								productoClienteVersionColeccion.put(productoClienteColl,campanaProductoVersionList);
								existeProducto = true;
							}
						}
						if (!existeProducto){
							campanaProductoVersionList = new ArrayList<CampanaProductoVersionIf>();
							campanaProductoVersionList.add(data);
							productoClienteVersionColeccion.put(productoCliente,campanaProductoVersionList);	
						}
					}else{
						campanaProductoVersionList = new ArrayList<CampanaProductoVersionIf>();
						campanaProductoVersionList.add(data);
						productoClienteVersionColeccion.put(productoCliente,campanaProductoVersionList);
					}
					//END 30 SEPTIEMBRE
					
					CampanaProductoVersionData dataTemp = new CampanaProductoVersionData();
					dataTemp.setNombre(nombreVersion);
					
					if (getCmbEstadoVersion().getSelectedItem().equals(NOMBRE_VERSION_ESTADO_ACTIVO)) {
						dataTemp.setEstado(VERSION_ESTADO_ACTIVO);
					} else {
						dataTemp.setEstado(VERSION_ESTADO_INACTIVO);
					}
					
					//agrega las nuevas versiones a la lista y al mapa temporal
					listVersionesTemp.add(dataTemp);
					versionColeccionTemporal.add(dataTemp); //ADD 26 SEPTIEMBRE
										
					//ADD 30 SEPTIEMBRE
					if (mapProductoClienteVersionesTemp.size() > 0){
						boolean existeProductoTemp = false;
						for (ProductoClienteIf productoClienteTempColl : mapProductoClienteVersionesTemp.keySet()){
							if (productoClienteTempColl.getId().compareTo(productoCliente.getId()) == 0){
								campanaProductoVersionTempList = mapProductoClienteVersionesTemp.get(productoClienteTempColl);
								campanaProductoVersionTempList.add(dataTemp);
								mapProductoClienteVersionesTemp.put(productoClienteTempColl,campanaProductoVersionTempList);
								existeProductoTemp = true;
							}
						}
						if(!existeProductoTemp){
							campanaProductoVersionTempList = new ArrayList<CampanaProductoVersionIf>();
							campanaProductoVersionTempList.add(dataTemp);
							mapProductoClienteVersionesTemp.put(productoCliente,campanaProductoVersionTempList);
						}
					}else{
						campanaProductoVersionTempList = new ArrayList<CampanaProductoVersionIf>();
						campanaProductoVersionTempList.add(dataTemp);
						mapProductoClienteVersionesTemp.put(productoCliente,campanaProductoVersionTempList);
					}
					//END 30 SEPTIEMBRE
					
					// Agrega los valores al registro que va  ser añadido  a la tabla.
					filaVersion.add("");
					filaVersion.add("("+marcaProductoIf.getNombre()+") - "+productoCliente.getNombre());
					filaVersion.add(getTxtNombreVersion().getText());
					filaVersion.add(getCmbEstadoVersion().getSelectedItem().toString());
					
					modelVersion.addRow(filaVersion);
					cleanVersion();
				} else {
					SpiritAlert.createAlert(mensajeError, SpiritAlert.WARNING);
					getTxtNombreVersion().grabFocus();
				}
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert(" No se pudo ingresar la Versión !!!", SpiritAlert.WARNING);
		}
	}
	//END 16 SEPTIEMBRE
	
	//ADD 16 SEPTIEMBRE
	private void actualizarVersion() {
		try
		{						
			//Veo que todos los campos hayan sido llenados
			if (validateFieldsVersion()) {
				
				boolean isExisteVersion = false;
				ProductoClienteIf productoCliente = (ProductoClienteIf)getCmbProducto().getSelectedItem();
				MarcaProductoIf marcaProductoIf = SessionServiceLocator.getMarcaProductoSessionService().getMarcaProducto(productoCliente.getMarcaProductoId());
				String nombreVersion = getTxtNombreVersion().getText();
				String estadoVersion = (String) getCmbEstadoVersion().getSelectedItem();
				
				if (estadoVersion.trim().equals(NOMBRE_VERSION_ESTADO_ACTIVO)) {
					estadoVersion = "A";
            	} else {
            		estadoVersion = "I";
            	}
				
				//Si la coleccion tiene algun elemento
				if(versionColeccion.size() != 0){
					//Recorro la coleccion de versiones
	    			for(int i = 0; i < versionColeccion.size(); i++){
	    				CampanaProductoVersionIf versionTemp = (CampanaProductoVersionIf) versionColeccion.get(i);
	    				//Si la version cargada ya esta en lista, entonces muestro un mensaje de error
	    				if (i != getSelectedRowTblVersion()) {
	    					if( versionTemp.getNombre().compareTo(nombreVersion) == 0  &&  
	    	    				versionTemp.getEstado().compareTo(estadoVersion) == 0) { 	
	    						isExisteVersion = true;
	    						break;
	    					}
	    				}
	    			}
	    		}
				
				modelVersion =  (DefaultTableModel) getTblVersion().getModel();
				ArrayList<CampanaProductoVersionIf> campanaProductoVersionList;
				ArrayList<CampanaProductoVersionIf> campanaProductoVersionTempList;				
				
				//Si la Version no existe la actualizo
				if(isExisteVersion == false){
					//Creo el obejto donde se va a gurdar el registro
					CampanaProductoVersionIf data = (CampanaProductoVersionIf) versionColeccion.get(getSelectedRowTblVersion());
					
					boolean isVersionInListTemp = false;
					if(listVersionesTemp.size() > 0){
						int contadorTemp = 0;
		    			for(int i = 0; i < listVersionesTemp.size(); i++){
		    				CampanaProductoVersionIf versionActualizarTemp = (CampanaProductoVersionIf) listVersionesTemp.get(i);
		    				//Si la version a eliminar se encuentra en la lista temporal
		    				if(data.getNombre().compareTo(versionActualizarTemp.getNombre()) == 0 && 
		    				   data.getEstado().compareTo(versionActualizarTemp.getEstado()) == 0){
		    					contadorTemp++;
		    				}if (contadorTemp >= 1){
		    					isVersionInListTemp = true;
		    					listVersionesTemp.remove(i);
								break;
		    				}
		    			}
					}
					
					//ADD 30 SEPTIEMBRE
					Iterator productoClienteVersionColeccionIt = productoClienteVersionColeccion.keySet().iterator();
					while(productoClienteVersionColeccionIt.hasNext()){
						ProductoClienteIf productoClienteTempIf = (ProductoClienteIf) productoClienteVersionColeccionIt.next();
						ArrayList<CampanaProductoVersionIf> versionTempList = productoClienteVersionColeccion.get(productoClienteTempIf);
						int contadorTemp = 0;
						for (int i = 0; i < versionTempList.size(); i++  ){
							CampanaProductoVersionIf versionActualizarTemp = versionTempList.get(i);    
														
							if(data.getNombre().compareTo(versionActualizarTemp.getNombre()) == 0 && 
				    		   data.getEstado().compareTo(versionActualizarTemp.getEstado()) == 0){
				    		   contadorTemp++;
				    		}
							if (contadorTemp >= 1){
				    			//isVersionInVectorTemp = true;
				    			versionTempList.remove(i);
								break;
				    		}
							
						}
						productoClienteVersionColeccion.put(productoClienteTempIf,versionTempList);
					}
					//END ADD 30 SEPTIEMBRE
					
					Iterator mapProductoClienteVersionesTempIt = mapProductoClienteVersionesTemp.keySet().iterator();
					while(mapProductoClienteVersionesTempIt.hasNext()){
						ProductoClienteIf productoClienteTempIf = (ProductoClienteIf) mapProductoClienteVersionesTempIt.next();
						ArrayList<CampanaProductoVersionIf> versionTempList = mapProductoClienteVersionesTemp.get(productoClienteTempIf);
						for (int i = 0; i < versionTempList.size(); i++  ){
							CampanaProductoVersionIf versionTemp = versionTempList.get(i);
							//COMENTED 3 OCTUBRE
							/*if(!listVersionesTemp.contains(versionTemp)){
								versionTempList.remove(i);
							}*/
							
							//ADD 3 OCUTBRE
							for (CampanaProductoVersionIf versionOfListTemp : listVersionesTemp){
								if (versionOfListTemp.getNombre().compareTo(versionTemp.getNombre()) == 0 &&
									versionOfListTemp.getEstado().compareTo(versionTemp.getEstado()) == 0 	){
									versionTempList.remove(i);
									break;
								}
							}
							//END 3 OCTUBRE
														
						}
						mapProductoClienteVersionesTemp.put(productoClienteTempIf,versionTempList);
					}
					
					//ADD 28 SEPTIEMBRE
					//con vector
					//boolean isVersionInVectorTemp = false;
					if(versionColeccionTemporal.size() > 0){
						int contadorTemp = 0;
		    			for(int i = 0; i < versionColeccionTemporal.size(); i++){
		    				CampanaProductoVersionIf versionActualizarTemp = (CampanaProductoVersionIf) versionColeccionTemporal.get(i);
		    				//Si la version a eliminar se encuentra en la lista temporal
		    				if(data.getNombre().compareTo(versionActualizarTemp.getNombre()) == 0 && 
		    				   data.getEstado().compareTo(versionActualizarTemp.getEstado()) == 0){
		    					contadorTemp++;
		    				}if (contadorTemp >= 1){
		    					//isVersionInVectorTemp = true;
		    					versionColeccionTemporal.remove(i);
								break;
		    				}
		    			}
					}
					//END 28 SEPTIEMBRE				
										
					//Seteo los datos del objeto 									
					data.setNombre(nombreVersion);				
					data.setEstado(estadoVersion);				
					
					//Actualizar en la coleccion de versionColeccion el registro que fue cambiado
					versionColeccion.set(getSelectedRowTblVersion(),data);
									
					//ADD 30 SEPTIEMBRE
					if (productoClienteVersionColeccion.size() > 0){
						boolean existeProducto = false;
						for (ProductoClienteIf productoClienteColl : productoClienteVersionColeccion.keySet()){
							if (productoClienteColl.getId().compareTo(productoCliente.getId()) == 0){
								campanaProductoVersionList = productoClienteVersionColeccion.get(productoClienteColl);
								campanaProductoVersionList.add(data);
								productoClienteVersionColeccion.put(productoClienteColl,campanaProductoVersionList);
								existeProducto = true;
							}	
						}
						if (!existeProducto){
							campanaProductoVersionList = new ArrayList<CampanaProductoVersionIf>();
							campanaProductoVersionList.add(data);
							productoClienteVersionColeccion.put(productoCliente,campanaProductoVersionList);
						}
					}else{
						campanaProductoVersionList = new ArrayList<CampanaProductoVersionIf>();
						campanaProductoVersionList.add(data);
						productoClienteVersionColeccion.put(productoCliente,campanaProductoVersionList);
					}//END 30 SEPTIEMBRE
					
					CampanaProductoVersionData dataTemp = new CampanaProductoVersionData();
					dataTemp.setNombre(nombreVersion);
					dataTemp.setEstado(estadoVersion);
										
					listVersionesTemp.add(dataTemp);
					versionColeccionTemporal.add(dataTemp); //ADD 26 SEPTIEMBRE
											
					//ADD 30 SEPTIEMBRE
					if (mapProductoClienteVersionesTemp.size() > 0){
						boolean existeProductoTemp = false;
						for (ProductoClienteIf productoClienteTempColl : mapProductoClienteVersionesTemp.keySet()){
							if (productoClienteTempColl.getId().compareTo(productoCliente.getId()) == 0){
								campanaProductoVersionTempList = mapProductoClienteVersionesTemp.get(productoClienteTempColl);
								campanaProductoVersionTempList.add(dataTemp);
								mapProductoClienteVersionesTemp.put(productoClienteTempColl,campanaProductoVersionTempList);
								existeProductoTemp = true;
							}
						}
						if (!existeProductoTemp){
							campanaProductoVersionTempList = new ArrayList<CampanaProductoVersionIf>();
							campanaProductoVersionTempList.add(dataTemp);
							mapProductoClienteVersionesTemp.put(productoCliente,campanaProductoVersionTempList);
						}
					}else{
						campanaProductoVersionTempList = new ArrayList<CampanaProductoVersionIf>();
						campanaProductoVersionTempList.add(dataTemp);
						mapProductoClienteVersionesTemp.put(productoCliente,campanaProductoVersionTempList);
					}
					//END 30 SEPTIEMBRE
					
					//Actualizo en la tablaVersion
					modelVersion.setValueAt(getTxtCodigoVersion().getText(),getSelectedRowTblVersion(),0);
					modelVersion.setValueAt("(" + marcaProductoIf.getNombre() + ") - " + productoCliente.getNombre(),getSelectedRowTblVersion(),1);
					modelVersion.setValueAt(getTxtNombreVersion().getText(),getSelectedRowTblVersion(),2);
					modelVersion.setValueAt(getCmbEstadoVersion().getSelectedItem(),getSelectedRowTblVersion(),3);
										
					cleanVersion();
					
				} else {
					SpiritAlert.createAlert("La Versión ya se encuentra agregada !!!", SpiritAlert.INFORMATION);
					getTxtNombreVersion().grabFocus();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Seleccione el registro a actualizar de la tabla Versión !!!", SpiritAlert.ERROR);
		}
	}
	//END 16 SEPTIEMBRE
	
	//ADD 16 SEPTIEMBRE
	private void eliminarVersion() {
		if (getTblVersion().getSelectedRow()!= -1) {
			
			ProductoClienteIf productoCliente = (ProductoClienteIf)getCmbProducto().getSelectedItem();
			//Extraigo el objeto version de la coleccion segun el registro seleccionado de la tabla
        	CampanaProductoVersionIf versionTemp = (CampanaProductoVersionIf) versionColeccion.get(getSelectedRowTblVersion());
			
        	//para indicar si la Version se encuentra en la lista temporal de Versiones(lista a agregar)
        	boolean isVersionInListTemp = false;
        	
        	
        	//Se busca si esa version se encuentra en la lista temporal 
        	//Si la version esta esta en la lista no se lo pone en la lista de eliminados
        	
			//Si la lista temporal tiene algun elemento
			if(listVersionesTemp.size() > 0){
				int contadorTemp = 0;
    			for(int i = 0; i < listVersionesTemp.size(); i++){
    				CampanaProductoVersionIf versionEliminarTemp = (CampanaProductoVersionIf) listVersionesTemp.get(i);
    				//Si la version a eliminar se encuentra en la lista temporal
    				if(versionEliminarTemp.getNombre().equals(versionTemp.getNombre()) &&
    				   versionEliminarTemp.getEstado().equals(versionTemp.getEstado()) ){
    					contadorTemp++;
    				}if (contadorTemp >= 1){
    					isVersionInListTemp = true;
    					listVersionesTemp.remove(i);
						break;
    				}
    			}
			}
			
			Iterator mapProductoClienteVersionesTempIt = mapProductoClienteVersionesTemp.keySet().iterator();
			while(mapProductoClienteVersionesTempIt.hasNext()){
				ProductoClienteIf productoClienteTempIf = (ProductoClienteIf) mapProductoClienteVersionesTempIt.next();
				ArrayList<CampanaProductoVersionIf> versionTempList = mapProductoClienteVersionesTemp.get(productoClienteTempIf);
				for (int i = 0; i < versionTempList.size(); i++  ){
					CampanaProductoVersionIf versionTemp2 = versionTempList.get(i);
					//COMENTED 3 OCTUBRE
					/*if(!listVersionesTemp.contains(versionTemp2)){
						versionTempList.remove(i);
					}*/
					
					//ADD 3 OCTUBRE
					for (CampanaProductoVersionIf versionOfListTemp : listVersionesTemp){
						if(versionOfListTemp.getNombre().compareTo(versionTemp2.getNombre()) == 0 &&
						   versionOfListTemp.getEstado().compareTo(versionTemp2.getEstado()) == 0	){
							versionTempList.remove(i);
							break;
						}						
					}				
				}
				mapProductoClienteVersionesTemp.put(productoClienteTempIf,versionTempList);
			}
			
			//Si el vector temporal tiene algun elemento
			if(versionColeccionTemporal.size() > 0){
				int contadorTemp = 0;
    			for(int i = 0; i < versionColeccionTemporal.size(); i++){
    				CampanaProductoVersionIf versionEliminarTemp = (CampanaProductoVersionIf) versionColeccionTemporal.get(i);
    				//Si la version a eliminar se encuentra en la lista temporal
    				if(versionEliminarTemp.getNombre().equals(versionTemp.getNombre()) &&
    				   versionEliminarTemp.getEstado().equals(versionTemp.getEstado()) ){
    					contadorTemp++;
    				}if (contadorTemp >= 1){
    					//isVersionInListTemp = true;
    					versionColeccionTemporal.remove(i);
						break;
    				}
    			}
			}
			
        	if (!existeComercialesWithCampanaProductoVersionId(versionTemp.getId())){  //ADD 4 OCTUBRE
        	        	
        		//solo se agrega a la lista de eliminados si no esta en la lista temp a agregar
				if (!isVersionInListTemp){				
					versionColeccionEliminadas.add(versionTemp); //ADD 22 SEPTIEMBRE
				}
				//Elimino el registro de la coleccion y de la Tabla
				versionColeccion.remove(getSelectedRowTblVersion());
				
				//ADD 29 SEPTIEMBRE
				for (ProductoClienteIf productoClienteColl : productoClienteVersionColeccion.keySet()){
					if (productoClienteColl.getId().compareTo(productoCliente.getId()) == 0){
						ArrayList<CampanaProductoVersionIf> listVersionesTemp = productoClienteVersionColeccion.get(productoClienteColl);
						
						for (int i = 0; i < listVersionesTemp.size(); i++){
							CampanaProductoVersionIf version = listVersionesTemp.get(i);
							if ( version.getNombre().equals(versionTemp.getNombre()) &&	
								 version.getEstado().equals(versionTemp.getEstado()) ){
								listVersionesTemp.remove(version);
							    break;
							}
						}					
						productoClienteVersionColeccion.put(productoClienteColl,listVersionesTemp);					
					}
				}
				//END 29 SEPTIEMBRE
				
				modelVersion.removeRow(getSelectedRowTblVersion());
				cleanVersion();
				
        	 }//ADD 4 OCTUBRE
        	 else{
        		 SpiritAlert.createAlert("La Versión no puede quitarse porque existen Comerciales con ella", SpiritAlert.WARNING);
        	 }
        	 //END 4 OCTUBRE
			
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de la tabla a eliminar !!!", SpiritAlert.WARNING);
		}
	}
	//END 16 SEPTIEMBRE
	
	//ADD 16 SEPTIEMBRE
	private void cleanVersion() {
		getTxtCodigoVersion().setText("");
		getCmbProducto().setSelectedIndex(-1);
		getTxtNombreVersion().setText("");
		//getBtnBuscarCliente().grabFocus();
	}
	//END 16 SEPTIEMBRE
	
	//ADD 16 SEPTIEMBRE
	public boolean validateFieldsVersion(){
		String strNombreVersion = this.getTxtNombreVersion().getText();
			
		if(getCmbProducto().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar una Versión!"
					,SpiritAlert.WARNING);
			getCmbProducto().grabFocus();
			return false;
		}
				
		if ((("".equals(strNombreVersion)) || (strNombreVersion == null))) {
			SpiritAlert.createAlert("Debe ingresar una versión!"
					,SpiritAlert.WARNING);
			getTxtNombreVersion().grabFocus();
			return false;
		}
				
		return true;
	}
	//END 16 SEPTIEMBRE
	
	private void agregarDetalleCampana() {
		try {
			if (validateFieldsDetalle()) {
				boolean isExisteDetalle = false;
				// Extraigo el objeto clientezona del combo
				ClienteZonaIf clienteZona = (ClienteZonaIf) getCmbZonaCliente().getSelectedItem();

				// Si la coleccion tiene algun elemento
				if (detalleCampanaColeccion.size() != 0) {
					// Recorro la coleccion de detalle campana cliente
					for (int i = 0; i < detalleCampanaColeccion.size(); i++) {
						CampanaDetalleIf detalleCampanaTemp = (CampanaDetalleIf) detalleCampanaColeccion.get(i);
						// Si el detalle campana cargado ya esta en lista,
						// entons muestro un mensaje de error
						if (detalleCampanaTemp.getClienteZonaId().equals(clienteZona.getId())) {
							isExisteDetalle = true;
							break;
						}
					}
				}

				modelDetalleCampana = (DefaultTableModel) getTableDetalleCampana().getModel();
				Vector<String> filaDetalleCampana = new Vector<String>(); 
					
				// Si el detalle no existe lo inserto
				if (isExisteDetalle == false) {

					CampanaDetalleData data = new CampanaDetalleData();

					// Seteo los datos del objeto detalle campaña
					data.setClienteZonaId(clienteZona.getId());
					data.setDescripcion(getTxtDescripcionDetalle().getText());
					data.setParticipacion(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtParticipacion().getText()))));
					// Agregar a la coleccion de DetalleCampanaColeccion
					// para grabar al final toda la coleccion
					detalleCampanaColeccion.add(data);
					// Agregra los valores al registro que va ser añadido a
					// la tabla.
					filaDetalleCampana.add(clienteZona.getNombre());
					filaDetalleCampana.add(Utilitarios.removeDecimalFormat(getTxtParticipacion().getText()));
					filaDetalleCampana.add(getTxtDescripcionDetalle().getText());
					// Agregra informacion a la tabla visual para el
					// usuario.
					modelDetalleCampana.addRow(filaDetalleCampana);
					// Resto la participacion insertada al detalle del valor
					// acumulado
					restanteParticipacion = restanteParticipacion - Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtParticipacion().getText()));
					// Reinicio los componentes
					if (getCmbZonaCliente().getItemCount() > 0){
						getCmbZonaCliente().setSelectedIndex(0);
						getCmbZonaCliente().repaint();
					}
					getTxtDescripcionDetalle().setText("");
					getTxtParticipacion().setText("");
					getCmbZonaCliente().grabFocus();
					getTxtRestante().setText(restanteParticipacion.toString());
				} else {
					SpiritAlert.createAlert("La Zona Cliente ya se encuentra agregada!", SpiritAlert.INFORMATION);
					// Reinicio los componentes
					if (getCmbZonaCliente().getItemCount() > 0){
						getCmbZonaCliente().setSelectedIndex(0);
						getCmbZonaCliente().repaint();
					}
					getTxtDescripcionDetalle().setText("");
					getTxtParticipacion().setText("");
					getCmbZonaCliente().grabFocus();
				}
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("No se pudo ingresar el detalle !!!", SpiritAlert.ERROR);
			System.out.println(e.getMessage());
		}
	}
		
	private void eliminarDetalleCampana() {
		if (getTableDetalleCampana().getSelectedRow() != -1) {
			try {
				// Extraigo los campos del registro de la tabla
				String nombreClienteZona = (String) getTableDetalleCampana().getModel().getValueAt(getTableDetalleCampana().getSelectedRow(), 0);

				// Recorro la coleccion de detalleCampana y veo cual es
				// el seleccionado para eliminarlo
				for (int i = 0; i < detalleCampanaColeccion.size(); i++) {
					CampanaDetalleIf detalleCampanaTemp = detalleCampanaColeccion.get(i);

					// Extraigo el cliente zona buscandolo por el nombre
					// del registro seleccionado en la tabla
					ClienteZonaIf bean = (ClienteZonaIf) SessionServiceLocator.getClienteZonaSessionService().getClienteZona(detalleCampanaTemp.getClienteZonaId());

					if (nombreClienteZona.equals(bean.getNombre())) {
						// Si elimino un detalle sumo el valor de la
						// participacion ala variable que contiene el
						// restante posible de esta
						restanteParticipacion = restanteParticipacion + detalleCampanaTemp.getParticipacion().doubleValue();
						getTxtRestante().setText(restanteParticipacion.toString());
						// Elimino el registro de la coleccion y de la Tabla
						if (detalleCampanaTemp.getId() != null)
							detallesCampanaEliminadosColeccion.add(detalleCampanaTemp);
						detalleCampanaColeccion.remove(i);
						modelDetalleCampana.removeRow(getTableDetalleCampana().getSelectedRow());
						break;
					}
				}
				getCmbZonaCliente().setSelectedIndex(0);
				getCmbZonaCliente().repaint();
				getTxtDescripcionDetalle().setText("");
				
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de la lista a eliminar !!!", SpiritAlert.WARNING);
		}
	}
	
	private void agregarBriefCampana() {
		try {
			// Veo que todos los campos hayan sido llenados
			if (validateFieldsCampanaBrief()) {
				boolean isExisteBrief = false;
				// Extraigo el objeto tipo brief del combo
				TipoBriefIf tipoBrief = (TipoBriefIf) getCmbTipoBrief().getSelectedItem();

				// Si la coleccion tiene algun elemento
				if (briefCampanaColeccion.size() != 0) {
					// Recorro la coleccion de briefs de campana
					for (int i = 0; i < briefCampanaColeccion.size(); i++) {
						CampanaBriefIf campanaBriefTemp = (CampanaBriefIf) briefCampanaColeccion.get(i);
						// Si la campana brief cargado ya esta en lista,
						// entons muestro un mensaje de error
						if(campanaBriefTemp.getUrlDescripcion() != null){
							if (campanaBriefTemp.getTipoBriefId().equals(tipoBrief.getId()) && campanaBriefTemp.getUrlDescripcion().equals(getTxtURLBrief().getText())) {
								isExisteBrief = true;
								break;
							}
						}				
					}
				}

				modelBriefCampana = (DefaultTableModel) getTableBrief().getModel();
				Vector<String> filaCampanaBrief = new Vector<String>();
					
				if (isExisteBrief == false) {

					CampanaBriefData data = new CampanaBriefData();
					
					data.setTipoBriefId(((TipoBriefIf) getCmbTipoBrief().getSelectedItem()).getId());
					data.setDescripcion(getTxtDescripcionBrief().getText());
					
					if (getTxtURLBrief().getText()!=null && !getTxtURLBrief().getText().equals(""))
						data.setUrlDescripcion(getTxtURLBrief().getText());
					else
						data.setUrlDescripcion(null);

					// Agregar a la coleccion de briefCampanaColeccion para
					// grabar al final toda la coleccion
					briefCampanaColeccion.add(data);
					if (data.getUrlDescripcion()!=null){
						File archivo = new File(getTxtURLBrief().getText());
						archivosColeccionBrief.add(archivo);
					} else
						archivosColeccionBrief.add(null);
					
					
					// Agregra los valores al registro que va ser añadido a
					// la tabla.
					filaCampanaBrief.add(tipoBrief.getNombre());
					filaCampanaBrief.add(getTxtDescripcionBrief().getText());
					filaCampanaBrief.add(getTxtURLBrief().getText()!=null?getTxtURLBrief().getText():"");
					// Agregra informacion a la tabla visual para el
					// usuario.
					modelBriefCampana.addRow(filaCampanaBrief);
					// Reinicio los componentes
					if (getCmbTipoBrief().getItemCount() > 0){
						getCmbTipoBrief().setSelectedIndex(0);
						getCmbTipoBrief().repaint();
					}
					getTxtURLBrief().setText("");
					getTxtDescripcionBrief().setText("");
					getTxtURLBrief().grabFocus();
				} else {
					SpiritAlert.createAlert("El Brief ya se encuentra agregado !!!", SpiritAlert.INFORMATION);
					// Reinicio los componentes
					if (getCmbTipoBrief().getItemCount() > 0){
						getCmbTipoBrief().setSelectedIndex(0);
						getCmbTipoBrief().repaint();
					}
					getTxtURLBrief().setText("");
					getTxtDescripcionBrief().setText("");
					getTxtURLBrief().grabFocus();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert(" No se pudo ingresar el Brief !!!", SpiritAlert.ERROR);
		}
	}
	
	private void actualizarBriefCampana() {
		int filaSeleccionada = getTableBrief().getSelectedRow();
		if (filaSeleccionada >= 0) {
			TipoBriefIf tipoBrief = (TipoBriefIf) getCmbTipoBrief().getSelectedItem();
			modelBriefCampana = (DefaultTableModel) getTableBrief().getModel();
			Vector<String> filaCampanaBrief = new Vector<String>();
			
			if (validateFieldsCampanaBrief()) {
				CampanaBriefData data = new CampanaBriefData();
				
				data.setId(briefCampanaColeccion.get(filaSeleccionada).getId());
				data.setTipoBriefId(((TipoBriefIf) getCmbTipoBrief().getSelectedItem()).getId());
				data.setDescripcion(getTxtDescripcionBrief().getText());
				if ( getTxtURLBrief().getText()!=null && !getTxtURLBrief().getText().equals("") )
					data.setUrlDescripcion(getTxtURLBrief().getText());
				else
					data.setUrlDescripcion(null);

				// Agregar a la coleccion de briefCampanaColeccion para
				// grabar al final toda la coleccion
				briefCampanaColeccion.add(filaSeleccionada, data);
				briefCampanaColeccion.remove(filaSeleccionada+1);
				
				if (data.getUrlDescripcion() != null){
					File archivo = new File(getTxtURLBrief().getText());
					archivosColeccionBrief.add(filaSeleccionada,archivo);
					archivosColeccionBrief.remove(filaSeleccionada+1);
				} else{
					archivosColeccionBrief.add(filaSeleccionada,null);
					archivosColeccionBrief.remove(filaSeleccionada+1);
				}
				// Agregra los valores al registro que va ser añadido a
				// la tabla.
				filaCampanaBrief.add(tipoBrief.getNombre());
				filaCampanaBrief.add(getTxtDescripcionBrief().getText());
				filaCampanaBrief.add(getTxtURLBrief().getText()!=null?getTxtURLBrief().getText():"");
				// Agregra informacion a la tabla visual para el
				// usuario.
				modelBriefCampana.insertRow(filaSeleccionada, filaCampanaBrief);
				modelBriefCampana.removeRow(filaSeleccionada+1);
				// Reinicio los componentes
				if (getCmbTipoBrief().getItemCount() > 0){
					getCmbTipoBrief().setSelectedIndex(0);
					getCmbTipoBrief().repaint();
				}
				getTxtURLBrief().setText("");
				getTxtDescripcionBrief().setText("");
				getTxtURLBrief().grabFocus();
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
		}
	}
	
	private void eliminarBriefCampana() {
		if (getTableBrief().getSelectedRow() != -1) {
			int filaSeleccionada = getTableBrief().getSelectedRow();
			CampanaBriefIf campanaBrief = briefCampanaColeccion.get(filaSeleccionada);
			if (campanaBrief.getId() != null){
				briefsEliminadosColeccion.add(campanaBrief);
			}
			archivosColeccionBrief.remove(getTableBrief().getSelectedRow());
			briefCampanaColeccion.remove(getTableBrief().getSelectedRow());
			modelBriefCampana.removeRow(getTableBrief().getSelectedRow());
			getTxtURLBrief().setText("");
			getTxtDescripcionBrief().setText("");
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de la lista a eliminar !!!", SpiritAlert.WARNING);
		}
	}
	
	private void agregarArchivoCampana() {
		try {
			// Veo que todos los campos hayan sido llenados
			if (validateFieldsCampanaArchivo()) {
				boolean isExisteArchivo = false;

				DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
				String fecha = dateFormatter.format(fechaArchivo.getTime());

				TipoArchivoIf tipoArchivo = (TipoArchivoIf) getCmbTipoArchivo().getSelectedItem();

				if (archivoCampanaColeccion.size() != 0) {
					for (int i = 0; i < archivoCampanaColeccion.size(); i++) {
						CampanaArchivoIf campanaArchivoTemp = (CampanaArchivoIf) archivoCampanaColeccion.get(i);
						String fechaTemp = dateFormatter.format(campanaArchivoTemp.getFecha());
						// Si la Campana Archivo cargado ya esta en lista, entonces muestro un mensaje de error
						if (campanaArchivoTemp.getTipoArchivoId().equals(tipoArchivo.getId()) && fechaTemp.equals(fecha) && campanaArchivoTemp.getUrlDescripcion().equals(getTxtURLArchivo().getText())) {
							isExisteArchivo = true;
							break;
						}
					}
				}

				modelArchivoCampana = (DefaultTableModel) getTableArchivo().getModel();
				Vector<String> filaCampanaArchivo = new Vector<String>();

				// Si el Archivo no existe lo inserto
				if (isExisteArchivo == false) {

					CampanaArchivoData data = new CampanaArchivoData();

					// Seteo los datos del objeto reunion
					data.setTipoArchivoId(tipoArchivo.getId());
					data.setUrlDescripcion(getTxtURLArchivo().getText());
					data.setFecha(new java.sql.Date(fechaArchivo.getYear(), fechaArchivo.getMonth(),fechaArchivo.getDate()));

					// Agregar a la coleccion de reunionArchivoColeccion para grabar al final toda la coleccion
					archivoCampanaColeccion.add(data);
					File archivo = new File(getTxtURLArchivo().getText());
					archivosColeccionCampana.add(archivo);
					// Agregra los valores al registro que va ser añadido a la tabla.
					filaCampanaArchivo.add(tipoArchivo.getNombre());
					// Agregra los valores al registro que va ser añadido a la tabla.
					//filaCampanaArchivo.add(fecha);
					filaCampanaArchivo.add(
							Utilitarios.getFechaCortaUppercase(fechaArchivo)
					);
					filaCampanaArchivo.add(getTxtURLArchivo().getText());
					// Agregra informacion a la tabla visual para el usuario.
					modelArchivoCampana.addRow(filaCampanaArchivo);
					// Reinicio los componentes
					if (getCmbTipoArchivo().getItemCount() > 0)
						getCmbTipoArchivo().setSelectedIndex(0);
					getTxtURLArchivo().setText("");
					getTxtURLArchivo().grabFocus();
				} else {
					SpiritAlert.createAlert("El Archivo ya se encuentra agregado !!!", SpiritAlert.INFORMATION);
					// Reinicio los componentes
					if (getCmbTipoArchivo().getItemCount() > 0)
						getCmbTipoArchivo().setSelectedIndex(0);
					getTxtURLArchivo().setText("");
					getTxtURLArchivo().grabFocus();
				}
			}
		} catch (Exception e) {
			SpiritAlert.createAlert(" No se pudo ingresar el detalle !!!", SpiritAlert.ERROR);
			System.out.println(e.getMessage());
		}
	}
	
	private void actualizarArchivoCampana() {
		int filaSeleccionada = getTableArchivo().getSelectedRow();
		if (filaSeleccionada >= 0) {
			TipoArchivoIf tipoArchivo = (TipoArchivoIf) getCmbTipoArchivo().getSelectedItem();
			modelArchivoCampana = (DefaultTableModel) getTableArchivo().getModel();
			Vector<String> filaCampanaArchivo = new Vector<String>();

			if (validateFieldsCampanaArchivo()) {
				CampanaArchivoData data = new CampanaArchivoData();

				// Seteo los datos del objeto reunion
				data.setId(archivoCampanaColeccion.get(filaSeleccionada).getId());
				data.setTipoArchivoId(tipoArchivo.getId());
				data.setUrlDescripcion(getTxtURLArchivo().getText());
				data.setFecha(new java.sql.Date(getCmbFechaArchivo().getDate().getTime()));

				// Agregar a la coleccion de reunionArchivoColeccion para grabar al final toda la coleccion
				archivoCampanaColeccion.add(filaSeleccionada, data);
				archivoCampanaColeccion.remove(filaSeleccionada+1);
				File archivo = new File(getTxtURLArchivo().getText());
				archivosColeccionCampana.add(filaSeleccionada,archivo);
				archivosColeccionCampana.remove(filaSeleccionada+1);
				
				// Agregra los valores al registro que va ser añadido a la tabla.
				filaCampanaArchivo.add(tipoArchivo.getNombre());
				// Agregra los valores al registro que va ser añadido a la tabla.
				//filaCampanaArchivo.add(fecha);
				filaCampanaArchivo.add(	Utilitarios.getFechaCortaUppercase(getCmbFechaArchivo().getDate()));
				filaCampanaArchivo.add(getTxtURLArchivo().getText());
				// Agregra informacion a la tabla visual para el usuario.
				modelArchivoCampana.insertRow(filaSeleccionada, filaCampanaArchivo);
				modelArchivoCampana.removeRow(filaSeleccionada+1);
				// Reinicio los componentes
				if (getCmbTipoArchivo().getItemCount() > 0)
					getCmbTipoArchivo().setSelectedIndex(0);
				getTxtURLArchivo().setText("");
				getTxtURLArchivo().grabFocus();
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
		}
	}
	
	private void eliminarArchivoCampana() {
		if (getTableArchivo().getSelectedRow() != -1) {
			int filaSeleccionada = getTableArchivo().getSelectedRow();
			CampanaArchivoIf campanaArchivo = archivoCampanaColeccion.get(filaSeleccionada);
			if (campanaArchivo.getId() != null){
				archivosEliminadosColeccion.add(campanaArchivo);
			}
			archivosColeccionCampana.remove(getTableArchivo().getSelectedRow());
			archivoCampanaColeccion.remove(getTableArchivo().getSelectedRow());
			modelArchivoCampana.removeRow(getTableArchivo().getSelectedRow());
			getTxtURLArchivo().setText("");
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de la lista a eliminar !!!", SpiritAlert.WARNING);
		}
	}
	
	public void showSaveMode() {
		setSaveMode();
		clean();
		getTxtCodigo().setBackground(getBackground());
		getTxtNombre().setBackground(Parametros.saveUpdateColor);
		getTxtCliente().setBackground(getBackground());
		//getTxtCorporacion().setBackground(getBackground());
		getCmbFechaInicio().setBackground(Parametros.saveUpdateColor);
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().setEditable(false);
		getTxtNombre().setEnabled(true);
		getTxtCorporacion().setEditable(false);
		getTxtCliente().setEditable(false);
		getTxtObservacion().setEnabled(true);
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getCmbZonaCliente().setEnabled(false);
		getTxtParticipacion().setEditable(false);
		getTxtRestante().setEditable(false);
		getTxtDescripcionDetalle().setEditable(false);
		getBtnAgregarDetalle().setEnabled(false);
		getCmbFechaInicio().setEnabled(true);
		getCmbFechaInicio().setEditable(false);
		getCmbFechaArchivo().setEditable(false);
		getCmbFechaArchivo().setEnabled(true);
		getBtnAgregarURLArchivo().setEnabled(true);
		getBtnAgregarArchivo().setEnabled(true);
		getCmbTipoArchivo().setEnabled(true);
		//ADD 15 SEPTIEMBRE
		getTxtCodigoVersion().setEditable(false);
		getTxtCodigoVersion().setEnabled(false);
		//END 15 SEPTIEMBRE

		Calendar calendarFechaArchivo = new GregorianCalendar();
		calendarFechaArchivo.setTime(fechaArchivo);
		getCmbFechaArchivo().setCalendar(calendarFechaArchivo);

		Calendar calendarFechaInicio = new GregorianCalendar();
		calendarFechaInicio.setTime(fechaInicio);
		getCmbFechaInicio().setCalendar(calendarFechaInicio);

		getTableDetalleCampana().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		cargarCombos();
		
		getJtpCampana().setSelectedIndex(0);
		getTxtNombre().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setBackground(getBackground());
		getTxtNombre().setBackground(Parametros.saveUpdateColor);
		getTxtCliente().setBackground(getBackground());
		//getTxtCorporacion().setBackground(getBackground());
		getCmbFechaInicio().setBackground(Parametros.saveUpdateColor);
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().setEditable(false);
		getTxtNombre().setEnabled(true);
		getTxtCorporacion().setEditable(false);
		getTxtCliente().setEditable(false);
		getTxtObservacion().setEnabled(true);
		getBtnBuscarCorporacion().setEnabled(false);
		getBtnBuscarCliente().setEnabled(false);
		getCmbFechaInicio().setEnabled(false);
		getCmbFechaInicio().setEditable(false);
		getCmbZonaCliente().setEnabled(true);
		getTxtParticipacion().setEditable(true);
		getTxtRestante().setEditable(false);
		getTxtDescripcionDetalle().setEditable(true);
		getBtnAgregarDetalle().setEnabled(true);
		getCmbFechaArchivo().setEditable(false);
		getCmbFechaArchivo().setEnabled(true);
		getBtnAgregarURLArchivo().setEnabled(true);
		getBtnAgregarArchivo().setEnabled(true);
		getCmbTipoArchivo().setEnabled(true);
		getJtpCampana().setSelectedIndex(0);
		this.getTxtNombre().grabFocus();
	}

	private Map buildQuery() {
		Map aMap = new HashMap();

		if ("".equals(getTxtCodigo().getText()) == false)
			aMap.put("codigo", getTxtCodigo().getText());
		else
			aMap.put("codigo", "%");

		if ("".equals(getTxtNombre().getText()) == false)
			aMap.put("nombre", "%" + getTxtNombre().getText() + "%");
		else
			aMap.put("nombre", "%");
		
		if ((getTxtCliente().getText() != null) && !getTxtCliente().getText().equals("") && clienteIf != null)
			aMap.put("clienteId", clienteIf.getId());
		
		if (getCmbFechaInicio().getSelectedItem() != null)
			aMap.put("fechaini", new java.sql.Date(getCmbFechaInicio().getDate().getTime()));
		
		if(getCmbEstado().getSelectedItem() != null)
			aMap.put("estado", getCmbEstado().getSelectedItem().toString().substring(0,1));
			
		return aMap;
	}

	public void find() {
		try {
			Long corporacion=0L;
			int tamanoLista = 0;
			Map mapa = buildQuery();
			/*if (corporacionIf != null){
				corporacion = corporacionIf.getId();
				tamanoLista = CampanaModel.getCampanaSessionService().findCampanaByQueryAndByCorporacionIdSize(mapa, corporacion, Parametros.getIdEmpresa());
			}
			else*/
				tamanoLista = SessionServiceLocator.getCampanaSessionService().getCampanaListSize(mapa,Parametros.getIdEmpresa());
			
			if (tamanoLista > 0) {
				CampanaCriteria campanaCriteria = new CampanaCriteria(corporacion);
				campanaCriteria.setResultListSize(tamanoLista);
				campanaCriteria.setQueryBuilded(mapa);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(70);
				anchoColumnas.add(290);	
				anchoColumnas.add(290);
				anchoColumnas.add(70);	
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	campanaCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null )
					getSelectedObject();
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		}
	}
	
	//ADD 28 SEPTIEMBRE 
	//agrega todos los productos clientes con versiones a la coleccion
	public void crearColeccionProductoClienteVersion(){
		productoClienteVersionColeccion = null;
		productoClienteVersionColeccion = new LinkedHashMap<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>>();
		
		for (ProductoClienteIf productoClienteIf : mapaProductoClienteVersiones.keySet()){
			ArrayList<CampanaProductoVersionIf> listVersiones = mapaProductoClienteVersiones.get(productoClienteIf);
			if (listVersiones != null && listVersiones.size() > 0){
				productoClienteVersionColeccion.put(productoClienteIf, listVersiones);
				productoClienteVersionColeccionAnterior.put(productoClienteIf, listVersiones);
			}
		}	
	}
	//END 28 SEPTIEMBRE

	//MODIFIED 21 SEPTIEMBRE
	public void getSelectedObject(){
		clean();
		setUpdateMode();
		campana = (CampanaIf) popupFinder.getElementoSeleccionado();

		cargarCombos();
		//ADD 22 SEPTIEMBRE
		//cargo la lista de Ordenes de Trabajo de la Campaña seleccionada
		cargarListaOrdenesTrabajo();
		//ADD 21 SEPTIEMBRE
		//cargo la lista de Comerciales de la Campaña seleccionada
		cargarListaComerciales();
		

		getTxtCodigo().setText(campana.getCodigo());
		getTxtNombre().setText(campana.getNombre());
		getTxtObservacion().setText(campana.getObservacion());
		getTxtPublicoObjetivo().setText(campana.getPublicoObjetivo());

		// LLeno los datos de los combos
		try {
			clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(campana.getClienteId());
			corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());

			getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
			getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());

			// Seteo la fecha leida de la base
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(campana.getFechaini());
			getCmbFechaInicio().setCalendar(calendar);

			if ("A".equals(campana.getEstado().toString()))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVA);
			else if("F".equals(campana.getEstado().toString()))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_FINALIZADA);
			else
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVA);

			//carga todos los productos de todas las marcas del cliente en las lista de chexbox
			cargarListaProductos();
			Collection campanaProductoColeccion = SessionServiceLocator.getCampanaProductoSessionService().findCampanaProductoByCampanaId(campana.getId());
			Iterator itCampanaProductoColeccion = campanaProductoColeccion.iterator();
			//Object[] productos = new Object[campanaProductoColeccion.size()]; COMENTED 21 SEPTIEMBRE xq no se estaba usando
			//int contador = 0; COMENTED 21 SEPTIEMBRE xq no se estaba usando
			while (itCampanaProductoColeccion.hasNext()) {
				CampanaProductoIf campanaProductoIf = (CampanaProductoIf) itCampanaProductoColeccion.next();

				productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(campanaProductoIf.getProductoClienteId());

				//ADD 21 SEPTIEMBRE
				ArrayList campanaProductoVersionList = (ArrayList)SessionServiceLocator.getCampanaProductoVersionSessionService().findCampanaProductoVersionByCampanaProductoId(campanaProductoIf.getId());
				//END 21 SEPTIEMBRE
					
				//ADD 28 SEPTIEMBRE
				mapaProductoClienteVersiones.put(productoClienteIf,campanaProductoVersionList);
				mapaProductoClienteVersionesAnterior.put(productoClienteIf, campanaProductoVersionList);
				//END 28 SEPTIEMBRE
				
			}
			
			//ADD 21 SEPTIEMBRE
			//slecciona en getCbListProductos() los Productos q intervienen en la Campaña
			for (int i = 0; i < getCbListProductos().getModel().getSize(); i++){
				ProductoClienteIf productoCliente = (ProductoClienteIf)getCbListProductos().getModel().getElementAt(i);
				//for (ProductoClienteIf productoClienteUpdate : productoClienteVersionColeccion.keySet() ){ COMENTED 28 SEPTIEMBRE
				for (ProductoClienteIf productoClienteUpdate : mapaProductoClienteVersiones.keySet() ){  //ADD 28 SEPTIEMBRE
					 if(productoCliente.getId().compareTo(productoClienteUpdate.getId()) == 0){
						 //se marca los check box de los productos asignados a la campaña 
						 getCbListProductos().getCheckBoxListSelectionModel().addSelectionInterval(i,i);
						//ADD 22 SEPTIEMBRE
						 productoColeccion.add(productoClienteUpdate); //COMENTED 28 SEPTIEMBRE
						 productoColeccionAnterior.add(productoClienteUpdate);//ADD 3 OCTUBRE
						
					 }					
				}
			}
			
			//productoColeccionAnterior = productoColeccion; //COMENTED 3 OCTUBRE ADD 22 SEPTIEMBRE
			//END 21 SEPTIEMBRE
			
			//ADD 21 SEPTIEMBRE
			//cargo los Productos seleccionados en el combo de Productos
			cargarComboProductos();
			
			//ADD 28 SEPTIEMBRE
			//agregarProductosToVectores();
			//agrega todos los productos clientes con versiones a la coleccion
			crearColeccionProductoClienteVersion();
			//END 28 SEPTIEMBRE
			
			//creo filas con los Datos de las Versiones segun Producto
			modelVersion =  (DefaultTableModel) getTblVersion().getModel();
			Vector<String> filaVersion;
			for (ProductoClienteIf productoClienteIfOfLista: productoClienteListToVersion){
				MarcaProductoIf marcaProductoIf = SessionServiceLocator.getMarcaProductoSessionService().getMarcaProducto(productoClienteIfOfLista.getMarcaProductoId());
				
				for (ProductoClienteIf productoClienteIfOfMapa: productoClienteVersionColeccion.keySet()){
					if (productoClienteIfOfLista.getId().compareTo(productoClienteIfOfMapa.getId()) == 0){
						ArrayList campanaProductoVersionList = productoClienteVersionColeccion.get(productoClienteIfOfMapa);
						if (campanaProductoVersionList != null && campanaProductoVersionList.size() > 0){
							
							for (CampanaProductoVersionIf version : (ArrayList<CampanaProductoVersionIf>)campanaProductoVersionList){
								//agrega la version existente al vector
								versionColeccion.add(version);
								
								filaVersion = new Vector<String>();
								// Agrega los valores al registro que va  ser añadido  a la tabla.
								filaVersion.add(version.getCodigo());
								filaVersion.add("(" + marcaProductoIf.getNombre() + ") - "+ productoClienteIfOfLista.getNombre());
								filaVersion.add(version.getNombre());
								if (version.getEstado().compareTo(VERSION_ESTADO_ACTIVO) == 0)
									filaVersion.add(NOMBRE_VERSION_ESTADO_ACTIVO);
								else
									filaVersion.add(NOMBRE_VERSION_ESTADO_INACTIVO);
								
								modelVersion.addRow(filaVersion);
							}
						}
					}
				}
				
			}	
			//copia la versiones existentes en un vector anterior
			versionColeccionAnterior = versionColeccion;
			//END 21 SEPTIEMBRE
			
			// Cargo el combo de zonas de cliente
			cargarComboZonaCliente();
			// Cargo los detalles campana pertencientes a la campana leida
			// de la base
			Collection campanaDetalleColeccion = SessionServiceLocator.getCampanaDetalleSessionService().findCampanaDetalleByCampanaId(campana.getId());
			Iterator itCampanaDetalleColeccion = campanaDetalleColeccion.iterator();

			// Obtengo el modelo de la tabla para agregar los detalles
			// leidos de la base
			modelDetalleCampana = (DefaultTableModel) getTableDetalleCampana().getModel();

			while (itCampanaDetalleColeccion.hasNext()) {
				CampanaDetalleIf campanaDetalleIf = (CampanaDetalleIf) itCampanaDetalleColeccion.next();

				Vector<String> filaDetalleCampana = new Vector<String>();

				// Agregar a la coleccion de DetalleCampanaColeccion para
				// grabar al final toda la coleccion
				detalleCampanaColeccion.add(campanaDetalleIf);

				// Extraigo el objeto clientezona del detalle campana
				ClienteZonaIf clienteZona = (ClienteZonaIf) SessionServiceLocator.getClienteZonaSessionService().getClienteZona(campanaDetalleIf.getClienteZonaId());

				// Agregra los valores al registro que va ser añadido a la tabla
				filaDetalleCampana.add(clienteZona.getNombre());
				filaDetalleCampana.add(campanaDetalleIf.getParticipacion().toString());
				filaDetalleCampana.add(campanaDetalleIf.getDescripcion());

				// Agregra informacion a la tabla visual para el usuario.
				modelDetalleCampana.addRow(filaDetalleCampana);

				// Resto la participacion insertada al detalle del valor acumulado
				restanteParticipacion = restanteParticipacion - campanaDetalleIf.getParticipacion().doubleValue();
			}
			getTxtRestante().setText(restanteParticipacion.toString());

			// Cargo los briefs campana pertencientes a la campana leida de
			// la base
			Collection campanaBriefColeccion = SessionServiceLocator.getCampanaBriefSessionService().findCampanaBriefByCampanaId(campana.getId());
			Iterator itCampanaBriefColeccion = campanaBriefColeccion.iterator();

			// Obtengo el modelo de la tabla para agregar los briefs leidos
			// de la base
			modelBriefCampana = (DefaultTableModel) getTableBrief().getModel();

			while (itCampanaBriefColeccion.hasNext()) {
				CampanaBriefIf campanaBriefIf = (CampanaBriefIf) itCampanaBriefColeccion.next();

				Vector<String> filaBriefCampana = new Vector<String>();

				// Agregar a la coleccion de BriefCampanaColeccion para
				// grabar al final toda la coleccion
				briefCampanaColeccion.add(campanaBriefIf);
				archivosColeccionBrief.add(null);

				// Extraigo el objeto tipoBrief del brief campana
				TipoBriefIf tipoBrief = (TipoBriefIf) SessionServiceLocator.getTipoBriefSessionService().getTipoBrief(campanaBriefIf.getTipoBriefId());

				// Agrega los valores al registro que va ser añadido a la tabla.
				filaBriefCampana.add(tipoBrief.getNombre());
				filaBriefCampana.add(campanaBriefIf.getDescripcion());
				if(campanaBriefIf.getUrlDescripcion() != null) filaBriefCampana.add(campanaBriefIf.getUrlDescripcion().toString());
				else filaBriefCampana.add("");
				
				// Agregra informacion a la tabla visual para el usuario.
				modelBriefCampana.addRow(filaBriefCampana);
			}

			Collection campanaArchivoColeccion = SessionServiceLocator.getCampanaArchivoSessionService().findCampanaArchivoByCampanaId(campana.getId());
			Iterator itCampanaArchivoColeccion = campanaArchivoColeccion.iterator();

			modelArchivoCampana = (DefaultTableModel) getTableArchivo().getModel();

			while (itCampanaArchivoColeccion.hasNext()) {
				CampanaArchivoIf campanaArchivoIf = (CampanaArchivoIf) itCampanaArchivoColeccion.next();

				Vector<String> filaCampanaArchivo = new Vector<String>();

				// Agregar a la coleccion de CampanaArchivoColeccion para
				// grabar al final toda la coleccion
				archivoCampanaColeccion.add(campanaArchivoIf);
				archivosColeccionCampana.add(null);

				// Extraigo el objeto tipoarchivo del reunion archivo
				TipoArchivoIf tipoArchivo = (TipoArchivoIf) SessionServiceLocator.getTipoArchivoSessionService().getTipoArchivo(campanaArchivoIf.getTipoArchivoId());

				// Agregra los valores al registro que va ser añadido a la tabla
				filaCampanaArchivo.add(tipoArchivo.getNombre());
				filaCampanaArchivo.add(
						Utilitarios.getFechaCortaUppercase(campanaArchivoIf.getFecha())
				);
				if(campanaArchivoIf.getUrlDescripcion() != null) filaCampanaArchivo.add(campanaArchivoIf.getUrlDescripcion());
				else filaCampanaArchivo.add("");

				modelArchivoCampana.addRow(filaCampanaArchivo);
			}

		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}

		this.showUpdateMode();
	}
	//END MODIFIED 21 SEPTIEMBRE
	
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//ADD 16 SEPTIEMBRE
	public void setSelectedRowTblVersion(int row) {
		this.selectedRowTblVersion = row;
	}
	
	public int getSelectedRowTblVersion() {
		return this.selectedRowTblVersion;
	}
	//END 16 SEPTIEMBRE
	
}
