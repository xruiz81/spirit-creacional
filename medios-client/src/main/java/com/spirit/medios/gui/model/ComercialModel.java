package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.medios.entity.CampanaData;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.CampanaProductoIf;
import com.spirit.medios.entity.CampanaProductoVersionIf;
import com.spirit.medios.entity.ComercialData;
import com.spirit.medios.entity.ComercialIf;
import com.spirit.medios.entity.DerechoProgramaIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.gui.criteria.CampanaCriteria;
import com.spirit.medios.gui.panel.JPComercial;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;


public class ComercialModel extends JPComercial{
	private static final long serialVersionUID = -6633355195363191951L;
	private CampanaCriteria campanaCriteria;
	private JDPopupFinderModel popupFinder;
	private ClienteCriteria clienteCriteria;
	private CorporacionCriteria corporacionCriteria;
	protected CorporacionIf corporacionIf;
	protected ClienteIf clienteIf;
	protected EmpleadoIf creativoIf,ejecutivoIf;
	protected DerechoProgramaIf derechoProgramaIf;
	protected CampanaIf campanaIf;
	ArrayList lista;
	Vector<ComercialIf> comercialColeccion = new Vector<ComercialIf>();
	Vector<ComercialIf> comercialEliminadoColeccion = new Vector<ComercialIf>();
	DefaultTableModel modelComercialCliente;
	final JPopupMenu  popupMenuProductoCliente = new JPopupMenu();
	final JPopupMenu  popupMenuComercialCliente = new JPopupMenu();
	private static final int MAX_LONGITUD_CODIGO_COMERCIAL = 3;
	private static final int MAX_LONGITUD_NOMBRE_COMERCIAL= 40;
	private static final int MAX_LONGITUD_DESCRIPCION = 200;
	private static final int MAX_LONGITUD_VERSION = 2 ;
	private static final int MAX_LONGITUD_DURACION = 3 ;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = "A";
	private static final String ESTADO_INACTIVO = "I";
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private int selectedRowTblComercial = -1;
	
	//ADD 15 ABRIL
	//para evitar error cuando se elimina un item nuevo en la tabla que no esta guardado en la DB
	private ArrayList<ComercialIf> listComercialesTemp = new ArrayList<ComercialIf>();
	
	//ADD 28 SEPTIEMBRE
	private Map<Long,ProductoClienteIf> mapIdCampanaProductoListProductoCliente = new LinkedHashMap<Long, ProductoClienteIf>();
	private List<CampanaProductoVersionIf> listCampanaProductoVersionIf = new ArrayList<CampanaProductoVersionIf>();
	
	//ADD 4 OCTUBRE
	private Map<Long,CampanaProductoVersionIf> mapCampanaProductoVersion = new LinkedHashMap<Long, CampanaProductoVersionIf>();
	//ADD 5 OCTUBRE
	private List<DerechoProgramaIf> derechosProgramasComerciales = new ArrayList<DerechoProgramaIf>();
	
	public ComercialModel(){
		initKeyListeners();
		anchoColumnasTabla();
		setSorterTable(getTblComercialCliente());
	    this.showSaveMode();    
	    cargarCombos();
	    initListeners();	    
	    getTblComercialCliente().addMouseListener(oMouseAdapterTblComercialCliente);	    
		getTblComercialCliente().addKeyListener(oKeyAdapterTblComercialCliente);	    
	    new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigoComercial().setEditable(false);
		getTxtCodigoComercial().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO_COMERCIAL));
		//COMENTED 4 OCTUBRE
        //getTxtDescripcionComercial().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION)); COMENTED 4 OCTUBRE
        //getTxtVersionComercial().addKeyListener(new TextChecker(MAX_LONGITUD_VERSION));
		
		//ADD 4 OCTUBRE
		getTxtIdentificacionComercial().addKeyListener(new TextChecker(MAX_LONGITUD_VERSION));
		
    	getTxtTiempo().addKeyListener(new TextChecker(MAX_LONGITUD_DURACION));
    	getTxtTiempo().addKeyListener (new NumberTextField());
    	getTxtCorporacion().setEditable(false);
        getTxtCliente().setEditable(false);
        getTxtCampanaComercial().setEditable(false);
        
        getBtnAgregarComercialCliente().setText("");
        getBtnActualizarComercialCliente().setText("");
        getBtnEliminarComercialCliente().setText("");
		//---- btnBuscarCorporacion ----
        getBtnBuscarCorporacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
        getBtnBuscarCorporacion().setToolTipText("Buscar Corporacion");		
		//---- btnBuscarCliente ----
        getBtnBuscarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
        getBtnBuscarCliente().setToolTipText("Buscar Cliente");		
		//---- btnBuscarCampana ----
        getBtnBuscarCampana().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
        getBtnBuscarCampana().setToolTipText("Buscar Campaña");		
		//---- btnAgregarComercial ----
        getBtnAgregarComercialCliente().setToolTipText("Agregar Comercial");
        getBtnAgregarComercialCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));		
		//---- btnActualizarComercial ----
        getBtnActualizarComercialCliente().setToolTipText("Actualizar Comercial");
        getBtnActualizarComercialCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		//---- btnEliminarComercial ----
        getBtnEliminarComercialCliente().setToolTipText("Eliminar Comercial");
        getBtnEliminarComercialCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		//MODIFIED 15 SEPTIEMBRE 
		//getTblComercialCliente().getColumnModel().getColumn(5).setCellRenderer(tableCellRendererCenter);
		//TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		//getTblComercialCliente().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		
		//ADD 15 SEPTIEMBRE
		getTblComercialCliente().getColumnModel().getColumn(4).setCellRenderer(tableCellRendererCenter);
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblComercialCliente().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
	}
	
	private void anchoColumnasTabla() {
		//setSorterTable(getTblFacturacion());
		//getTblFacturacion().getTableHeader().setReorderingAllowed(false);
		//getTblChequesEmitidos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//getTblComercialCliente().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblComercialCliente().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(20);
		anchoColumna = getTblComercialCliente().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(110);
		anchoColumna = getTblComercialCliente().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(110);
		anchoColumna = getTblComercialCliente().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblComercialCliente().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblComercialCliente().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(15);
		//COMENTED 15 SEPTIEMBRE
		//anchoColumna = getTblComercialCliente().getColumnModel().getColumn(6);
		//anchoColumna.setPreferredWidth(15);		
	}
	
	public void initListeners(){
		// Manejo los eventos de Buscar Corporación
		getBtnBuscarCorporacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(100);
				anchoColumnas.add(500);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null ){
					clean();
					corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
					getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
				}
			}
		});
		
		// Manejo los eventos de Buscar Cliente
		getBtnBuscarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				
				if (corporacionIf != null)
					idCorporacion = corporacionIf.getId();

				clienteCriteria = new ClienteCriteria("Clientes",idCorporacion,CODIGO_TIPO_CLIENTE);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null ){
					cleanCliente();
					clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
					if (corporacionIf == null) {
						try {
							corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
							getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					getBtnBuscarCampana().setEnabled(true);
					//find(); COMENTED 4 OCTUBRE SUGIERO COMENTAR ESTA LINEA PARA QUE EL USUARIO BUSQUE LA CAMPANA
				}
			}
		});
	
		getBtnBuscarCampana().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				campanaCriteria = new CampanaCriteria("Campañas",clienteIf.getId());
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(260);
				anchoColumnas.add(260);
				anchoColumnas.add(50);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), campanaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					campanaIf = (CampanaIf) popupFinder.getElementoSeleccionado();
					getTxtCampanaComercial().setText(campanaIf.getCodigo() + " - " + campanaIf.getNombre());
					//COMENTED 28 SEPTIEMBRE
					//cargarComboProductoCliente();
					//ADD 28 SEPTIEMBRE
					cargarComboCampanaProductoVersion();
					//ADD 4 OCTUBRE
					cargarMapaCampanaProductoVersionByCampanaId();
					cargarMapaCampanaProductoListProductoCliente();
					find(); //SI NO FUNCIONA AKI LO TENGO Q REGREAR A-> getBtnBuscarCliente().addActionListener(new ActionListener()
					
				}
			}
		});
		
		//Opcion Que Permite Borrar un regitsro deseado de la tabla de comercial
	    JMenuItem itemEliminarComercialCliente = new JMenuItem("Eliminar");
	    popupMenuComercialCliente.add(itemEliminarComercialCliente);
	    //Añado el listener de menupopup
	    itemEliminarComercialCliente.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evento) {
	    		eliminarComercialCliente();
	    	}
	    });
		
		getBtnAgregarComercialCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarComercialCliente();			
			}
		});

		getBtnActualizarComercialCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarComercialCliente();			
			}
		});
		
		getBtnEliminarComercialCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarComercialCliente();			
			}
		});
		
		//ADD 5 OCTUBRE
		getCmbCampanaProductoVersion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (mapIdCampanaProductoListProductoCliente.size() > 0){
					CampanaProductoVersionIf version = (CampanaProductoVersionIf) getCmbCampanaProductoVersion().getSelectedItem();
					if (version != null){
						ProductoClienteIf producto = mapIdCampanaProductoListProductoCliente.get(version.getCampanaProductoId());
						if (producto != null){
							getTxtProductoComercial().setText(producto.getNombre());
						}else{
							getTxtProductoComercial().setText("");
						}
					}else{
						getTxtProductoComercial().setText("");
					}
				}
			}
		});
		//END 5 OCTUBRE
		
		
	}
	
	 MouseListener oMouseAdapterTblComercialCliente = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
            if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblComercialCliente().getModel().getRowCount()>0)
            	popupMenuComercialCliente.show(evt.getComponent(), evt.getX(), evt.getY());
            else
    			enableSelectedRowForUpdate(evt);
        }
	};
		
	KeyListener oKeyAdapterTblComercialCliente = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	//MODIFIED 28 SEPTIEMBRE
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
        	try {
        		int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setSelectedRowTblComercial(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
        		ComercialIf comercialTemp= (ComercialIf) comercialColeccion.get(getSelectedRowTblComercial());
        		setSelectedRowTblComercial(getSelectedRowTblComercial());
            	
            	for(int i=0;i < getCmbDerechoProgramaComercial().getItemCount();i++){
					DerechoProgramaIf bean = (DerechoProgramaIf) getCmbDerechoProgramaComercial().getItemAt(i);
					if(bean.getNombre().compareTo(getTblComercialCliente().getModel().getValueAt(getSelectedRowTblComercial(), 3).toString())==0){
						getCmbDerechoProgramaComercial().setSelectedItem(bean);
						getCmbDerechoProgramaComercial().repaint();
					}						
				}
            	
            	//Muestro los datos
            	campanaIf = SessionServiceLocator.getCampanaSessionService().getCampana(comercialTemp.getCampanaId());
            	getTxtCampanaComercial().setText(campanaIf.getCodigo() + " - " + campanaIf.getNombre());
				//cargarComboProductoCliente(); COMENTED 4 OCTUBRE
				//ADD 4 OCTUBRE
				cargarComboCampanaProductoVersion();
				//ADD 5 OCTUBRE
				cargarMapaCampanaProductoVersionByCampanaId();
				cargarMapaCampanaProductoListProductoCliente();
				
            	getTxtCodigoComercial().setText(comercialTemp.getCodigo());
            	//COMENTED 28 SEPTIEMBRE
            	/*if(comercialTemp.getProductoClienteId() != null){
            		getCmbProductoCliente().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbProductoCliente(),comercialTemp.getProductoClienteId()));
            	}else{
            		getCmbProductoCliente().setSelectedIndex(-1);
            	}*/
            	
            	//ADD 28 SEPTIEMBRE
            	if(comercialTemp.getCampanaProductoVersionId() != null){
            		getCmbCampanaProductoVersion().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbCampanaProductoVersion(),comercialTemp.getCampanaProductoVersionId()));
            		//ADD 4 OCTUBRE
            		CampanaProductoVersionIf version = mapCampanaProductoVersion.get(comercialTemp.getCampanaProductoVersionId());
            		ProductoClienteIf producto = mapIdCampanaProductoListProductoCliente.get(version.getCampanaProductoId());
            		getTxtProductoComercial().setText(producto.getNombre());
            		//END 4 OCTUBRE
            	}else{
            		getCmbCampanaProductoVersion().setSelectedIndex(-1);
            	}
            	//END 28 SEPTIEMBRE
            	
            	//COMENTED 4 OCTUBRE
            	//getTxtDescripcionComercial().setText(comercialTemp.getDescripcion()); 
				//getTxtVersionComercial().setText(comercialTemp.getVersion());
            	
            	//ADD 4 OCTUBRE para un COMERCIAL EL CAMPO VERSIO ES LA IDENTIFICACION O LETRA
            	getTxtIdentificacionComercial().setText(comercialTemp.getVersion());
            	
				getTxtTiempo().setText(comercialTemp.getDuracion());
            	
            	if (comercialTemp.getEstado().equals("A")) {
            		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
            	} else {
            		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
            	}
				
            	//Creo un mapa para buscar la campana por codigo, nombre
            	String campana = getTblComercialCliente().getModel().getValueAt(getSelectedRowTblComercial(), 1).toString();
            	Map campanaMap = new HashMap();	            
            	campanaMap.put("codigo", campana.split(" - ")[0]);
            	campanaMap.put("nombre", campana.split(" - ")[1]);
            	campanaMap.put("clienteId", clienteIf.getId());
            	
				campanaIf = (CampanaIf) SessionServiceLocator.getCampanaSessionService().findCampanaByQuery(campanaMap).iterator().next();
				//Mando a mostrar la informacion de la campana
				getTxtCampanaComercial().setText(campanaIf.getCodigo() + " - " + campanaIf.getNombre());
				
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	//END MODIFIED 28 SEPTIEMBRE
	
	/*COMENTED 28 SEPTIEMBRE
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
        	try {
        		int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setSelectedRowTblComercial(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
        		ComercialIf comercialTemp= (ComercialIf) comercialColeccion.get(getSelectedRowTblComercial());
        		setSelectedRowTblComercial(getSelectedRowTblComercial());
            	
            	for(int i=0;i < getCmbDerechoProgramaComercial().getItemCount();i++){
					DerechoProgramaIf bean = (DerechoProgramaIf) getCmbDerechoProgramaComercial().getItemAt(i);
					if(bean.getNombre().compareTo(getTblComercialCliente().getModel().getValueAt(getSelectedRowTblComercial(), 2).toString())==0){
						getCmbDerechoProgramaComercial().setSelectedItem(bean);
						getCmbDerechoProgramaComercial().repaint();
					}						
				}
            	
            	//Muestro los datos
            	campanaIf = SessionServiceLocator.getCampanaSessionService().getCampana(comercialTemp.getCampanaId());
            	getTxtCampanaComercial().setText(campanaIf.getCodigo() + " - " + campanaIf.getNombre());
				cargarComboProductoCliente();
				
            	getTxtCodigoComercial().setText(comercialTemp.getCodigo());
            	if(comercialTemp.getProductoClienteId() != null){
            		getCmbProductoCliente().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbProductoCliente(),comercialTemp.getProductoClienteId()));
            	}else{
            		getCmbProductoCliente().setSelectedIndex(-1);
            	}
            	getTxtDescripcionComercial().setText(comercialTemp.getDescripcion());
				getTxtVersionComercial().setText(comercialTemp.getVersion());
				getTxtTiempo().setText(comercialTemp.getDuracion());
            	
            	if (comercialTemp.getEstado().equals("A")) {
            		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
            	} else {
            		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
            	}
				
            	//Creo un mapa para buscar la campana por codigo, nombre
            	String campana = getTblComercialCliente().getModel().getValueAt(getSelectedRowTblComercial(), 4).toString();
            	Map campanaMap = new HashMap();	            
            	campanaMap.put("codigo", campana.split(" - ")[0]);
            	campanaMap.put("nombre", campana.split(" - ")[1]);
            	campanaMap.put("clienteId", clienteIf.getId());
            	
				campanaIf = (CampanaIf) SessionServiceLocator.getCampanaSessionService().findCampanaByQuery(campanaMap).iterator().next();
				//Mando a mostrar la informacion de la campana
				getTxtCampanaComercial().setText(campanaIf.getCodigo() + " - " + campanaIf.getNombre());
				
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}END COMENTED 28 SEPTIEMBRE
	*/
	
	//MODIFIED 28 SEPTIEMBRE
	private void agregarComercialCliente() {
		try {	
			setSelectedRowTblComercial(-1);
			boolean isExisteComercial = false;
			//int validador = 0;
			String mensajeError = null;
			//0   valido se puede agregar el comercial
			//-1   No puede ser agregado, ya existe este Comercial
			//-2   No puede ser agregado, el Producto ya tiene una Versión
			//-3   No puede ser agregado, el dato Versión le pertenece a otro Producto existente
			
			if (validateFieldsComercialCliente()) {
				
				DerechoProgramaIf derechoPrograma = (DerechoProgramaIf) getCmbDerechoProgramaComercial().getSelectedItem();
				//COMENTED 28 SEPTIEMBRE
				//ProductoClienteIf productoCliente = (ProductoClienteIf)getCmbProductoCliente().getSelectedItem();
				//ADD 28 SEPTIEMBRE
				CampanaProductoVersionIf campanaProductoVersion = (CampanaProductoVersionIf)getCmbCampanaProductoVersion().getSelectedItem();
				//ADD 4 OCTUBRE 
				ProductoClienteIf productoCliente = mapIdCampanaProductoListProductoCliente.get(campanaProductoVersion.getCampanaProductoId());
				getTxtProductoComercial().setText(productoCliente.getNombre());						
								
				//Si la coleccion tiene algun elemento
				if(comercialColeccion.size()!=0){
					//Recorro la coleccion de comerciales
					int contador = 0;
	    			for(int i=0;i<comercialColeccion.size();i++){
	    				ComercialIf comercialClienteTemp = (ComercialIf) comercialColeccion.get(i);
	    					    				
	    				if(comercialClienteTemp.getCampanaProductoVersionId() != null){//ADD 6 OCTUBRE
		    				//Si el comercial cargado ya esta en lista, entons muestro un mensaje de error
		    				if( comercialClienteTemp.getCampanaProductoVersionId().compareTo(campanaProductoVersion.getId()) == 0  &&  //ADD 4 OCTUBRE
		    					comercialClienteTemp.getProductoClienteId().compareTo(productoCliente.getId()) == 0  &&  
		    					comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
		    					//comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText()) && COMENTED 4 OCTUBRE
		    					comercialClienteTemp.getVersion().equals(getTxtIdentificacionComercial().getText()) && //ADD 4 OCTUBRE
		    				    comercialClienteTemp.getDerechoprogramaId().compareTo(derechoPrograma.getId()) == 0 && //){COMENTED 26 AGOSTO
		    				    comercialClienteTemp.getDuracion().equals(getTxtTiempo().getText())){ //ADD 26 AGOSTO
	    						contador++;
	    						mensajeError = "No puede ser agregado, ya existe este Comercial";
		    				} //ADD 26 AGOSTO
		    				else if( comercialClienteTemp.getCampanaProductoVersionId().compareTo(campanaProductoVersion.getId()) == 0  &&  //ADD 4 OCTUBRE 
		    						 comercialClienteTemp.getProductoClienteId().compareTo(productoCliente.getId()) == 0  &&  
			    					 comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
			    					 //comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText()) && COMENTED 4 OCTUBRE
			    					 comercialClienteTemp.getVersion().equals(getTxtIdentificacionComercial().getText()) && //ADD 4 OCTUBRE
			    				     comercialClienteTemp.getDerechoprogramaId().compareTo(derechoPrograma.getId()) == 0 && //){COMENTED 26 AGOSTO
			    				     !comercialClienteTemp.getDuracion().equals(getTxtTiempo().getText())){ //ADD 26 AGOSTO
		    						 contador++;
		    						 //mensajeError = "No puede ser agregado, debe utilizar una nueva Versión"; COMENTED 5 OCTUBRE
		    						 //ADD 6 OCTUBRE
		    						 mensajeError = "La información: "+ campanaProductoVersion.getNombre()+ " (" + comercialClienteTemp.getVersion() + ") " + 
		    						 				"\n" + derechoPrograma.getNombre() + " ya ha sido ingresada";
			    			}//ADD 4 OCTUBRE
		    				else if( comercialClienteTemp.getCampanaProductoVersionId().compareTo(campanaProductoVersion.getId()) == 0  &&  //ADD 4 OCTUBRE 
		    						 comercialClienteTemp.getProductoClienteId().compareTo(productoCliente.getId()) == 0  &&  
			    					 comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
			    					 //comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText()) && COMENTED 4 OCTUBRE
			    					 !comercialClienteTemp.getVersion().equals(getTxtIdentificacionComercial().getText()) && //ADD 4 OCTUBRE
			    				     comercialClienteTemp.getDerechoprogramaId().compareTo(derechoPrograma.getId()) == 0 && //){COMENTED 26 AGOSTO
			    				     comercialClienteTemp.getDuracion().equals(getTxtTiempo().getText())){ //ADD 26 AGOSTO
		    						 contador++;
		    						 //mensajeError = "No puede ser agregado, debe utilizar una nueva Versión"; COMENTED 6 OCTUBRE
		    						 //ADD 6 OCTUBRE
		    						 mensajeError = "La información: "+ campanaProductoVersion.getNombre()+ " " + derechoPrograma.getNombre() + "\nde " +
		    						 				getTxtTiempo().getText() + " (seg) ya tiene asignada una Identificación, seleccione otra Versión";
			    			}				
		    				//COMENTED 26 AGOSTO	    				
		    				/*else if(comercialClienteTemp.getProductoClienteId().compareTo(productoCliente.getId()) == 0  &&  
		    						comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
		    						!comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText())){
	    						isExisteComercial = true;
	    						mensajeError = "No puede ser agregado, el Producto ya tiene una Versión";
	    						break;
		    				}*/
		    				//COMENTED 4 OCTUBRE
		    				/*else if(comercialClienteTemp.getProductoClienteId().compareTo(productoCliente.getId()) != 0  &&  
		    						comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
		    						comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText())){
	    						isExisteComercial = true;
	    						mensajeError = "No puede ser agregado, el dato Versión le pertenece a otro Producto existente";
	    						break;
		    				}*///END COMENTED 4 OCTUBRE
		    				//ADD 4 OCTUBRE
		    				else if( comercialClienteTemp.getCampanaProductoVersionId().compareTo(campanaProductoVersion.getId()) != 0  &&  //ADD 4 OCTUBRE
		    						 comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
		    						 //comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText()) && COMENTED 4 OCTUBRE
			    					 comercialClienteTemp.getVersion().equals(getTxtIdentificacionComercial().getText()) ){//&& //ADD 4 OCTUBRE
			    				    
	    						isExisteComercial = true;
	    						mensajeError = "No puede ser agregado, el dato Versión le pertenece a otra Versión existente";
	    						break;
		    				}//END ADD 4 OCTUBRE	    				
		    				if (contador >= 1){
		    					isExisteComercial = true;
	    						break;
		    				}
	    				
	    				}//ADD 6 OCTUBRE
	    			}
	    		}
				System.out.println("size lista temporales: "+listComercialesTemp.size());
				
				
				modelComercialCliente =  (DefaultTableModel) getTblComercialCliente().getModel();
				Vector<String> filaComercialCliente = new Vector<String>();
				//Si el Comercial no existe lo inserto
				if (isExisteComercial == false) {
					ComercialData data = new ComercialData();
					
					//Seteo los datos del objeto resumen mes
					//data.setCodigo(getTxtCodigoComercial().getText());
					data.setCampanaProductoVersionId(campanaProductoVersion.getId()); //ADD 4 OCTUBRE
					data.setProductoClienteId(productoCliente.getId());
					
					//data.setNombre(productoCliente.getNombre()); COMENTED 5 OCTUBRE
					data.setNombre(campanaProductoVersion.getNombre()); //ADD 5 OCTUBRE
					
					data.setEmpresaId(Parametros.getIdEmpresa());
					data.setDerechoprogramaId(derechoPrograma.getId());
					//data.setDescripcion(getTxtDescripcionComercial().getText()); COMENTED 4 OCTUBRE
					data.setCampanaId(campanaIf.getId());
					//data.setVersion(getTxtVersionComercial().getText()); COMENTED 4 OCTUBRE
					data.setVersion(getTxtIdentificacionComercial().getText()); //ADD 4 OCTUBRE
					data.setDuracion(getTxtTiempo().getText());						
					
					if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO)) {
						data.setEstado(ESTADO_ACTIVO);
					} else {
						data.setEstado(ESTADO_INACTIVO);
					}
					
					comercialColeccion.add(data);
					
					//ADD 15 ABRIL
					ComercialData dataTemp = new ComercialData();
					dataTemp.setCampanaProductoVersionId(campanaProductoVersion.getId()); //ADD 4 OCTUBRE
					dataTemp.setProductoClienteId(productoCliente.getId());
					
					//dataTemp.setNombre(productoCliente.getNombre());	COMENTED 5 OCTUBRE
					dataTemp.setNombre(campanaProductoVersion.getNombre()); //ADD 5 OCTUBRE
					
					
					dataTemp.setEmpresaId(Parametros.getIdEmpresa());
					dataTemp.setDerechoprogramaId(derechoPrograma.getId());
					//dataTemp.setDescripcion(getTxtDescripcionComercial().getText()); COMENTED 4 OCTUBRE
					dataTemp.setCampanaId(campanaIf.getId());
					//dataTemp.setVersion(getTxtVersionComercial().getText()); COMENTED 4 OCTUBRE
					dataTemp.setVersion(getTxtIdentificacionComercial().getText()); //ADD 4 OCTUBRE
					dataTemp.setDuracion(getTxtTiempo().getText());	
					//agrega los nuevos comerciales a la lista temporal
					listComercialesTemp.add(dataTemp);
					
					//END 15 ABRIL
					
					// Agrega los valores al registro que va  ser añadido  a la tabla.
					//filaComercialCliente.add(getTxtCodigoComercial().getText());
					filaComercialCliente.add("");
					filaComercialCliente.add(campanaIf.getCodigo() + " - " + campanaIf.getNombre());
					filaComercialCliente.add(campanaProductoVersion.getNombre()); //ADD 4 OCTUBRE
					//filaComercialCliente.add(productoCliente.getNombre()); COMENTED 4 OCTUBRE
					
					filaComercialCliente.add(derechoPrograma.getNombre());
					//filaComercialCliente.add(getTxtDescripcionComercial().getText()); COMENTED 4 OCTUBRE
					//filaComercialCliente.add(getTxtVersionComercial().getText());	COMENTED 4 OCTUBRE
					
					filaComercialCliente.add(getTxtIdentificacionComercial().getText()); //ADD 4 OCTUBRE
					filaComercialCliente.add(getTxtTiempo().getText());
					modelComercialCliente.addRow(filaComercialCliente);
					cleanComercial();
				} else {
					//SpiritAlert.createAlert("El Comercial ya se encuentra agregado !!!", SpiritAlert.INFORMATION);
					SpiritAlert.createAlert(mensajeError, SpiritAlert.WARNING);
					//getTxtVersionComercial().grabFocus();  COMENTED 4 OCTUBRE
					
					getTxtIdentificacionComercial().grabFocus(); //ADD 4 OCTUBRE
				}
				
				getCmbDerechoProgramaComercial().grabFocus();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert(" No se pudo ingresar el Comercial Cliente !!!", SpiritAlert.WARNING);
		}
	}
	//END MODIFIED 28 SEPTIEMBRE
	
	/* COMENTED 28 AGOSTO
	private void agregarComercialCliente() {
		try {	
			setSelectedRowTblComercial(-1);
			boolean isExisteComercial = false;
			//int validador = 0;
			String mensajeError = null;
			//0   valido se puede agregar el comercial
			//-1   No puede ser agregado, ya existe este Comercial
			//-2   No puede ser agregado, el Producto ya tiene una Versión
			//-3   No puede ser agregado, el dato Versión le pertenece a otro Producto existente
			
			if (validateFieldsComercialCliente()) {
				
				DerechoProgramaIf derechoPrograma = (DerechoProgramaIf) getCmbDerechoProgramaComercial().getSelectedItem();
				ProductoClienteIf productoCliente = (ProductoClienteIf)getCmbProductoCliente().getSelectedItem();
								
				//Si la coleccion tiene algun elemento
				if(comercialColeccion.size()!=0){
					//Recorro la coleccion de comerciales
					int contador = 0;
	    			for(int i=0;i<comercialColeccion.size();i++){
	    				ComercialIf comercialClienteTemp = (ComercialIf) comercialColeccion.get(i);
	    				//Si el comercial cargado ya esta en lista, entons muestro un mensaje de error
	    				if( comercialClienteTemp.getProductoClienteId().compareTo(productoCliente.getId()) == 0  &&  
	    					comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
	    					comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText()) && 
	    				    comercialClienteTemp.getDerechoprogramaId().compareTo(derechoPrograma.getId()) == 0 && //){COMENTED 26 AGOSTO
	    				    comercialClienteTemp.getDuracion().equals(getTxtTiempo().getText())){ //ADD 26 AGOSTO
    						contador++;
    						mensajeError = "No puede ser agregado, ya existe este Comercial";
	    				} //ADD 26 AGOSTO
	    				else if( comercialClienteTemp.getProductoClienteId().compareTo(productoCliente.getId()) == 0  &&  
		    					comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
		    					comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText()) && 
		    				    comercialClienteTemp.getDerechoprogramaId().compareTo(derechoPrograma.getId()) == 0 && //){COMENTED 26 AGOSTO
		    				    !comercialClienteTemp.getDuracion().equals(getTxtTiempo().getText())){ //ADD 26 AGOSTO
	    						contador++;
	    						mensajeError = "No puede ser agregado, debe crear una nueva Versión";
		    			}			
	    				//COMENTED 26 AGOSTO
	    				
	    				else if(comercialClienteTemp.getProductoClienteId().compareTo(productoCliente.getId()) == 0  &&  
	    						comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
	    						!comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText())){
    						isExisteComercial = true;
    						mensajeError = "No puede ser agregado, el Producto ya tiene una Versión";
    						break;
	    				}else if(comercialClienteTemp.getProductoClienteId().compareTo(productoCliente.getId()) != 0  &&  
	    						comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
	    						comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText())){
    						isExisteComercial = true;
    						mensajeError = "No puede ser agregado, el dato Versión le pertenece a otro Producto existente";
    						break;
	    				}if (contador >= 1){
	    					isExisteComercial = true;
    						break;
	    				}
	    			}
	    		}
				System.out.println("size lista temporales: "+listComercialesTemp.size());
				
				
				modelComercialCliente =  (DefaultTableModel) getTblComercialCliente().getModel();
				Vector<String> filaComercialCliente = new Vector<String>();
				//Si el Comercial no existe lo inserto
				if (isExisteComercial==false) {
					ComercialData data = new ComercialData();
					
					//Seteo los datos del objeto resumen mes
					//data.setCodigo(getTxtCodigoComercial().getText());
					data.setProductoClienteId(productoCliente.getId());
					data.setNombre(productoCliente.getNombre());
					data.setEmpresaId(Parametros.getIdEmpresa());
					data.setDerechoprogramaId(derechoPrograma.getId());
					data.setDescripcion(getTxtDescripcionComercial().getText());
					data.setCampanaId(campanaIf.getId());
					data.setVersion(getTxtVersionComercial().getText());
					data.setDuracion(getTxtTiempo().getText());						
					
					if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO)) {
						data.setEstado(ESTADO_ACTIVO);
					} else {
						data.setEstado(ESTADO_INACTIVO);
					}
					
					comercialColeccion.add(data);
					
					//ADD 15 ABRIL
					ComercialData dataTemp = new ComercialData();
					dataTemp.setProductoClienteId(productoCliente.getId());
					dataTemp.setNombre(productoCliente.getNombre());
					dataTemp.setEmpresaId(Parametros.getIdEmpresa());
					dataTemp.setDerechoprogramaId(derechoPrograma.getId());
					dataTemp.setDescripcion(getTxtDescripcionComercial().getText());
					dataTemp.setCampanaId(campanaIf.getId());
					dataTemp.setVersion(getTxtVersionComercial().getText());
					dataTemp.setDuracion(getTxtTiempo().getText());	
					//agrega los nuevos comerciales a la lista temporal
					listComercialesTemp.add(dataTemp);
					
					//END 15 ABRIL
					
					// Agrega los valores al registro que va  ser añadido  a la tabla.
					//filaComercialCliente.add(getTxtCodigoComercial().getText());
					filaComercialCliente.add("");
					filaComercialCliente.add(productoCliente.getNombre());
					filaComercialCliente.add(derechoPrograma.getNombre());
					filaComercialCliente.add(getTxtDescripcionComercial().getText());
					filaComercialCliente.add(campanaIf.getCodigo() + " - " + campanaIf.getNombre());
					filaComercialCliente.add(getTxtVersionComercial().getText());
					filaComercialCliente.add(getTxtTiempo().getText());
					modelComercialCliente.addRow(filaComercialCliente);
					cleanComercial();
				} else {
					//SpiritAlert.createAlert("El Comercial ya se encuentra agregado !!!", SpiritAlert.INFORMATION);
					SpiritAlert.createAlert(mensajeError, SpiritAlert.WARNING);
					getTxtVersionComercial().grabFocus();
				}
				
				getCmbDerechoProgramaComercial().grabFocus();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert(" No se pudo ingresar el Comercial Cliente !!!", SpiritAlert.WARNING);
		}
	} END COMENTED 28 AGOSTO
*/	
	
	private void cleanComercial() {
		getTxtCodigoComercial().setText("");
		//COMENTED 28 SEPTIEMBRE
		//getCmbProductoCliente().setSelectedIndex(-1); 
		//getTxtDescripcionComercial().setText("");
		
		//ADD 28 SEPTIEMBRE
		getCmbCampanaProductoVersion().setSelectedIndex(-1);
		getTxtProductoComercial().setText("");
		
		//getTxtVersionComercial().setText(""); COMENTED 4 OCTUBRE
		//ADD 4 OCTUBRE
		getTxtIdentificacionComercial().setText(""); 
		getTxtProductoComercial().setText("");
		
		getTxtTiempo().setText("");
		getBtnBuscarCliente().grabFocus();
	}
	
	//MODIFIED 4 OCTUBRE
	private void actualizarComercialCliente() {
		try
		{		
			String mensajeError = null; //ADD 5 OCTUBRE
			
			//Veo que todos los campos hayan sido llenados
			if (validateFieldsComercialCliente()) {
				
				boolean isExisteComercial = false;
								
				DerechoProgramaIf derechoPrograma = (DerechoProgramaIf) getCmbDerechoProgramaComercial().getSelectedItem();
				//ProductoClienteIf productoCliente = (ProductoClienteIf)getCmbProductoCliente().getSelectedItem(); COMENTED 4 OCTUBRE
				
				CampanaProductoVersionIf campanaProductoVersion = (CampanaProductoVersionIf)getCmbCampanaProductoVersion().getSelectedItem();
				//ADD 4 OCTUBRE 
				ProductoClienteIf productoCliente = mapIdCampanaProductoListProductoCliente.get(campanaProductoVersion.getCampanaProductoId());
				getTxtProductoComercial().setText(productoCliente.getNombre());		
				
				//Si la coleccion tiene algun elemento
				if(comercialColeccion.size()!=0){
					int contador = 0; //ADD 5 OCTUBRE
					//Recorro la coleccion de comerciales
	    			for(int i=0;i<comercialColeccion.size();i++){
	    				ComercialIf comercialClienteTemp = (ComercialIf) comercialColeccion.get(i);
	    				//Si el comercial cargado ya esta en lista, entons muestro un mensaje de error
	    				if (i != getSelectedRowTblComercial()) {
	    					/*COMENTED 4 OCTUBRE
	    					if(comercialClienteTemp.getProductoClienteId().compareTo(productoCliente.getId()) == 0  &&  
	    					   comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
	    					   !comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText())){
	    					   isExisteComercial = true;
	    						break;
	    					}*/
	    					
	    					if (comercialClienteTemp.getCampanaProductoVersionId() != null){// ADD 6 OCTUBRE
		    					//ADD 5 OCTUBRE
		    					if( comercialClienteTemp.getCampanaProductoVersionId().compareTo(campanaProductoVersion.getId()) == 0  &&  //ADD 4 OCTUBRE
		    	    				comercialClienteTemp.getProductoClienteId().compareTo(productoCliente.getId()) == 0  &&  
		    	    				comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
		    	    				//comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText()) && COMENTED 4 OCTUBRE
		    	    				comercialClienteTemp.getVersion().equals(getTxtIdentificacionComercial().getText()) && //ADD 4 OCTUBRE
		    	    				comercialClienteTemp.getDerechoprogramaId().compareTo(derechoPrograma.getId()) == 0 && //){COMENTED 26 AGOSTO
		    	    				comercialClienteTemp.getDuracion().equals(getTxtTiempo().getText())){ //ADD 26 AGOSTO
		        						contador++;
		        						mensajeError = "No puede ser agregado, ya existe este Comercial";
		    	    				} 
		    	    				else if( comercialClienteTemp.getCampanaProductoVersionId().compareTo(campanaProductoVersion.getId()) == 0  &&  //ADD 4 OCTUBRE 
		    	    						 comercialClienteTemp.getProductoClienteId().compareTo(productoCliente.getId()) == 0  &&  
		    		    					 comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
		    		    					 //comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText()) && COMENTED 4 OCTUBRE
		    		    					 comercialClienteTemp.getVersion().equals(getTxtIdentificacionComercial().getText()) && //ADD 4 OCTUBRE
		    		    				     comercialClienteTemp.getDerechoprogramaId().compareTo(derechoPrograma.getId()) == 0 && //){COMENTED 26 AGOSTO
		    		    				     !comercialClienteTemp.getDuracion().equals(getTxtTiempo().getText())){ //ADD 26 AGOSTO
		    	    						 contador++;
		    	    						 //mensajeError = "No puede ser agregado, debe utilizar una nueva Versión"; COMENTED 6 OCTUBRE
		    	    						 //ADD 6 OCTUBRE
		    	    						 mensajeError = "La información: "+ campanaProductoVersion.getNombre()+ " (" + comercialClienteTemp.getVersion() + ") " + 
		    	    						 				"\n" + derechoPrograma.getNombre() + " ya ha sido ingresada";
		    		    			}
		    	    				else if( comercialClienteTemp.getCampanaProductoVersionId().compareTo(campanaProductoVersion.getId()) == 0  &&  //ADD 4 OCTUBRE 
		    	    						 comercialClienteTemp.getProductoClienteId().compareTo(productoCliente.getId()) == 0  &&  
		    		    					 comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
		    		    					 //comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText()) && COMENTED 4 OCTUBRE
		    		    					 !comercialClienteTemp.getVersion().equals(getTxtIdentificacionComercial().getText()) && //ADD 4 OCTUBRE
		    		    				     comercialClienteTemp.getDerechoprogramaId().compareTo(derechoPrograma.getId()) == 0 && //){COMENTED 26 AGOSTO
		    		    				     comercialClienteTemp.getDuracion().equals(getTxtTiempo().getText())){ //ADD 26 AGOSTO
		    	    						 contador++;
		    	    						 //mensajeError = "No puede ser agregado, debe utilizar una nueva Versión"; COMENTED 6 OCTUBRE
		    	    						 //ADD 6 OCTUBRE
		    	    						 mensajeError = "La información: "+ campanaProductoVersion.getNombre()+ " " + derechoPrograma.getNombre() + "\nde " +
		    	    						 				getTxtTiempo().getText() + " (seg) ya tiene asignada una Identificación, seleccione otra Versión";
		    		    			}
		    	    				else if( comercialClienteTemp.getCampanaProductoVersionId().compareTo(campanaProductoVersion.getId()) != 0  &&  //ADD 4 OCTUBRE
		   	    						 comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
		   	    						 //comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText()) && COMENTED 4 OCTUBRE
		   		    					 comercialClienteTemp.getVersion().equals(getTxtIdentificacionComercial().getText()) ){//&& //ADD 4 OCTUBRE
		   		    				    
		       						//isExisteComercial = true;
		       						mensajeError = "No puede ser agregado, el dato Versión le pertenece a otra Versión existente";
		       						break;
		   	    				}
		    					if (contador >= 1){
			    					isExisteComercial = true;
		    						break;
			    				}
		    					//END 5 OCTUBRE	  
	    					}//ADD 6 OCTUBRE
	    				}
	    			}
	    		}
				
				modelComercialCliente =  (DefaultTableModel) getTblComercialCliente().getModel();
				
				//Si el Comercial no existe lo actualizo
				if(isExisteComercial==false){
					//Creo el obejto donde se va a gurdar el regitro
					ComercialIf data = (ComercialIf) comercialColeccion.get(getSelectedRowTblComercial());
					
					//ADD 25 ABRIL
					boolean isComercialInListTemp = false;
					if(listComercialesTemp.size()>0){
						int contadorTemp = 0;
		    			for(int i = 0; i < listComercialesTemp.size(); i++){
		    				ComercialIf comercialActualizarTemp = (ComercialIf) listComercialesTemp.get(i);
		    				//Si el comercial a eliminar se encuentra en la lista temporal
		    				if(data.getCampanaProductoVersionId().compareTo(comercialActualizarTemp.getCampanaProductoVersionId()) == 0 && //ADD 5 OCTUBRE
		    				   data.getProductoClienteId().compareTo(comercialActualizarTemp.getProductoClienteId()) == 0 &&
		    				   data.getCampanaId().equals(comercialActualizarTemp.getCampanaId()) && 
		    				   data.getVersion().equals(comercialActualizarTemp.getVersion()) && 
		    				   data.getDerechoprogramaId().compareTo(comercialActualizarTemp.getDerechoprogramaId()) == 0 &&
		    				   data.getDuracion().compareTo(comercialActualizarTemp.getDuracion()) == 0){ //ADD 5 OCTUBRE
		    					contadorTemp++;
		    				}if (contadorTemp >= 1){
		    					isComercialInListTemp = true;
		    					listComercialesTemp.remove(i);
								break;
		    				}
		    			}
					}//END 25 ABRIL
					
					//Seteo los datos del objeto resumen mes
					//data.setCodigo(getTxtCodigoComercial().getText());
					
					data.setCampanaProductoVersionId(campanaProductoVersion.getId()); //ADD 5 OCTUBRE
					data.setProductoClienteId(productoCliente.getId());
					
					//data.setNombre(productoCliente.getNombre()); COMENTED 5 OCTUBRE
					data.setNombre(campanaProductoVersion.getNombre()); //ADD 5 OCTUBRE
					
					data.setEmpresaId(Parametros.getIdEmpresa());
					data.setDerechoprogramaId(derechoPrograma.getId());
					//data.setDescripcion(getTxtDescripcionComercial().getText()); COMENTED 5 OCTUBRE
					data.setCampanaId(campanaIf.getId());
					//data.setVersion(getTxtVersionComercial().getText()); COMENTED 5 OCTUBRE
					data.setVersion(getTxtIdentificacionComercial().getText()); //ADD 5 OCTUBRE
					data.setDuracion(getTxtTiempo().getText());
						
					if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO)){
						data.setEstado(ESTADO_ACTIVO);
					}else{
						data.setEstado(ESTADO_INACTIVO);
					}
					
					//Actualizar en la coleccion de comercialClienteColeccion el registro que fue cambiado
					comercialColeccion.set(getSelectedRowTblComercial(),data);
					
					//ADD 25 ABRIL
					listComercialesTemp.add(data);
					//END 25 ABRIL
					
					//Actualizo en la tablaComercialCliente
					modelComercialCliente.setValueAt(getTxtCodigoComercial().getText(),getSelectedRowTblComercial(),0);
					modelComercialCliente.setValueAt(campanaIf.getCodigo() + " - " + campanaIf.getNombre(),getSelectedRowTblComercial(),1);
					
					//modelComercialCliente.setValueAt(productoCliente.getNombre(),getSelectedRowTblComercial(),1);
					modelComercialCliente.setValueAt(campanaProductoVersion.getNombre(),getSelectedRowTblComercial(),2);
					
					modelComercialCliente.setValueAt(derechoPrograma.getNombre(),getSelectedRowTblComercial(),3);
					//modelComercialCliente.setValueAt(getTxtDescripcionComercial().getText(),getSelectedRowTblComercial(),3); COMENTED 5 OCTUBRE
					
					//modelComercialCliente.setValueAt(getTxtVersionComercial().getText(),getSelectedRowTblComercial(),5); COMENTED 5 OCTUBRE
					modelComercialCliente.setValueAt(getTxtIdentificacionComercial().getText(),getSelectedRowTblComercial(),4);
					modelComercialCliente.setValueAt(getTxtTiempo().getText(),getSelectedRowTblComercial(),5);
															
					cleanComercial();
					
				} else {
					//SpiritAlert.createAlert("El Comercial ya se encuentra agregado !!!", SpiritAlert.INFORMATION); COMENTED 5 OCTUBRE
					SpiritAlert.createAlert(mensajeError, SpiritAlert.INFORMATION); //ADD 5 OCTUBRE
					//getTxtVersionComercial().grabFocus(); //COMENTED 5 OCTUBRE
					getTxtIdentificacionComercial().grabFocus(); //ADD 5 OCTUBRE
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Seleccione el registro a actualizar de la tabla Comercial !!!", SpiritAlert.ERROR);
		}
	}
	//MODIFIED 4 OCTUBRE
	
	/* COMENTED 4 OCTUBRE
	private void actualizarComercialCliente() {
		try
		{						
			//Veo que todos los campos hayan sido llenados
			if (validateFieldsComercialCliente()) {
				
				boolean isExisteComercial = false;
				
				DerechoProgramaIf derechoPrograma = (DerechoProgramaIf) getCmbDerechoProgramaComercial().getSelectedItem();
				//ProductoClienteIf productoCliente = (ProductoClienteIf)getCmbProductoCliente().getSelectedItem(); COMENTED 4 OCTUBRE
				
				CampanaProductoVersionIf campanaProductoVersion = (CampanaProductoVersionIf)getCmbCampanaProductoVersion().getSelectedItem();
				//ADD 4 OCTUBRE 
				ProductoClienteIf productoCliente = mapIdCampanaProductoListProductoCliente.get(campanaProductoVersion.getCampanaProductoId());
				getTxtProductoComercial().setText(productoCliente.getNombre());		
				
				//Si la coleccion tiene algun elemento
				if(comercialColeccion.size()!=0){
					//Recorro la coleccion de comerciales
	    			for(int i=0;i<comercialColeccion.size();i++){
	    				ComercialIf comercialClienteTemp = (ComercialIf) comercialColeccion.get(i);
	    				//Si el comercial cargado ya esta en lista, entons muestro un mensaje de error
	    				if (i != getSelectedRowTblComercial()) {
	    					
	    					if(comercialClienteTemp.getProductoClienteId().compareTo(productoCliente.getId()) == 0  &&  
	    					   comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && 
	    					   !comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText())){
	    						isExisteComercial = true;
	    						break;
	    					}
	    				}
	    			}
	    		}
				
				modelComercialCliente =  (DefaultTableModel) getTblComercialCliente().getModel();
				
				//Si el Comercial no existe lo actualizo
				if(isExisteComercial==false){
					//Creo el obejto donde se va a gurdar el regitro
					ComercialIf data = (ComercialIf) comercialColeccion.get(getSelectedRowTblComercial());
					
					//ADD 25 ABRIL
					boolean isComercialInListTemp = false;
					if(listComercialesTemp.size()>0){
						int contadorTemp = 0;
		    			for(int i = 0; i < listComercialesTemp.size(); i++){
		    				ComercialIf comercialActualizarTemp = (ComercialIf) listComercialesTemp.get(i);
		    				//Si el comercial a eliminar se encuentra en la lista temporal
		    				if(data.getProductoClienteId().compareTo(comercialActualizarTemp.getProductoClienteId()) == 0 &&
		    				   data.getCampanaId().equals(comercialActualizarTemp.getCampanaId()) && 
		    				   data.getVersion().equals(comercialActualizarTemp.getVersion()) && 
		    				   data.getDerechoprogramaId().compareTo(comercialActualizarTemp.getDerechoprogramaId()) == 0){
		    					contadorTemp++;
		    				}if (contadorTemp >= 1){
		    					isComercialInListTemp = true;
		    					listComercialesTemp.remove(i);
								break;
		    				}
		    			}
					}//END 25 ABRIL
					
					//Seteo los datos del objeto resumen mes
					//data.setCodigo(getTxtCodigoComercial().getText());
									
					data.setProductoClienteId(productoCliente.getId());
					data.setNombre(productoCliente.getNombre());
					
					data.setEmpresaId(Parametros.getIdEmpresa());
					data.setDerechoprogramaId(derechoPrograma.getId());
					data.setDescripcion(getTxtDescripcionComercial().getText());
					data.setCampanaId(campanaIf.getId());
					data.setVersion(getTxtVersionComercial().getText());
					data.setDuracion(getTxtTiempo().getText());
						
					if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO)){
						data.setEstado(ESTADO_ACTIVO);
					}else{
						data.setEstado(ESTADO_INACTIVO);
					}
					
					//Actualizar en la coleccion de comercialClienteColeccion el registro que fue cambiado
					comercialColeccion.set(getSelectedRowTblComercial(),data);
					
					//ADD 25 ABRIL
					listComercialesTemp.add(data);
					//END 25 ABRIL
					
					//Actualizo en la tablaComercialCliente
					modelComercialCliente.setValueAt(getTxtCodigoComercial().getText(),getSelectedRowTblComercial(),0);
					modelComercialCliente.setValueAt(productoCliente.getNombre(),getSelectedRowTblComercial(),1);
					modelComercialCliente.setValueAt(derechoPrograma.getNombre(),getSelectedRowTblComercial(),2);
					modelComercialCliente.setValueAt(getTxtDescripcionComercial().getText(),getSelectedRowTblComercial(),3);
					modelComercialCliente.setValueAt(campanaIf.getCodigo() + " - " + campanaIf.getNombre(),getSelectedRowTblComercial(),4);
					modelComercialCliente.setValueAt(getTxtVersionComercial().getText(),getSelectedRowTblComercial(),5);
					modelComercialCliente.setValueAt(getTxtTiempo().getText(),getSelectedRowTblComercial(),6);
										
					cleanComercial();
					
				} else {
					SpiritAlert.createAlert("El Comercial ya se encuentra agregado !!!", SpiritAlert.INFORMATION);
					getTxtVersionComercial().grabFocus();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Seleccione el registro a actualizar de la tabla Comercial !!!", SpiritAlert.ERROR);
		}
	}*/
	
	private void eliminarComercialCliente() {
		if (getTblComercialCliente().getSelectedRow()!=-1) {
			//Extraigo el objeto comercial de la coleccion segun el registro seleccionado de la tabla
        	ComercialIf comercialTemp = (ComercialIf) comercialColeccion.get(getSelectedRowTblComercial());
			
        	//ADD 18 ABRIL
        	//para indicar si el Comercial se encuentra en la lista temporal de Comerciales(lista a agregar)
        	boolean isComercialInListTemp = false;
        	//Se busca si ese comercial se encuentra en la lista temporal 
        	//Si el Comercial esta esta en la lista no se lo pone en la lista de eliminados
        	
        	if(comercialTemp.getCampanaProductoVersionId() != null){//ADD 6 OCTUBRE
	        	//ADD 15 ABRIL
				//Si la lista temporal tiene algun elemento
	        	if(listComercialesTemp.size()>0){
					int contadorTemp = 0;
	    			for(int i = 0; i < listComercialesTemp.size(); i++){
	    				ComercialIf comercialEliminarTemp = (ComercialIf) listComercialesTemp.get(i);
	    				//Si el comercial a eliminar se encuentra en la lista temporal
	    				/*COMENTED 5 OCTUBRE
	    				if(comercialEliminarTemp.getProductoClienteId().compareTo(comercialTemp.getProductoClienteId()) == 0 && 
	    				   comercialEliminarTemp.getCampanaId().equals(comercialTemp.getCampanaId()) && 
	    				   comercialEliminarTemp.getVersion().equals(comercialTemp.getVersion()) &&
	    				   comercialEliminarTemp.getDerechoprogramaId().compareTo(comercialTemp.getDerechoprogramaId()) == 0){
	    					contadorTemp++;
	    				}*/
	    				
	    				//ADD 5 OCTUBRE
		    			if(comercialEliminarTemp.getCampanaProductoVersionId().compareTo(comercialTemp.getCampanaProductoVersionId()) == 0 &&  //ADD 5 OCTUBRE
		    			   comercialEliminarTemp.getProductoClienteId().compareTo(comercialTemp.getProductoClienteId()) == 0 && 
		    			   comercialEliminarTemp.getCampanaId().equals(comercialTemp.getCampanaId()) && 
		    			   comercialEliminarTemp.getVersion().equals(comercialTemp.getVersion()) &&
		    			   comercialEliminarTemp.getDerechoprogramaId().compareTo(comercialTemp.getDerechoprogramaId()) == 0 &&
		    			   comercialEliminarTemp.getDuracion().compareTo(comercialTemp.getDuracion()) == 0 ){
		    				contadorTemp++;
		    			}
		    			//END 5 OCTUBRE
		    			if (contadorTemp >= 1){
		    				isComercialInListTemp = true;
		    				listComercialesTemp.remove(i);
							break;
		    			}
	    			}
				}
				//END 15 ABRIL
	        	
        	}//ADD 6 OCTUBRE	
        	//EN 18 ABRIL
        	
        	//MODIFIED 18 ABRIL 
			//solo se agrega a la lista de eliminados si no esta en la lista temp a agregar
			if (!isComercialInListTemp)
				comercialEliminadoColeccion.add(comercialTemp);
			//Elimino el registro de la coleccion y de la Tabla
			comercialColeccion.remove(getSelectedRowTblComercial());
			modelComercialCliente.removeRow(getSelectedRowTblComercial());
			cleanComercial();
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de la tabla a eliminar !!!", SpiritAlert.WARNING);
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				if(clienteIf != null){
					SessionServiceLocator.getComercialSessionService().procesarComercialColeccion(comercialColeccion, comercialEliminadoColeccion, clienteIf.getId());
					SpiritAlert.createAlert("Producto Comercial guardado con éxito",SpiritAlert.INFORMATION);
					this.clean();
					this.showSaveMode();
				}else{
					SpiritAlert.createAlert("Debe seleccionar un Cliente para el Comercial",SpiritAlert.WARNING);
				}			
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		try {
			if (validateFields()) {	
				SessionServiceLocator.getComercialSessionService().procesarComercialColeccion(comercialColeccion, comercialEliminadoColeccion, clienteIf.getId());
				SpiritAlert.createAlert("Producto Comercial actualizado con éxito",SpiritAlert.INFORMATION);
				this.clean();
				this.showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void delete() {
		try {
			deleteComercialesCliente();
			SpiritAlert.createAlert("Producto Comercial eliminado con éxito",SpiritAlert.INFORMATION);
		} catch (Exception e) {
			SpiritAlert.createAlert("El registro actual tiene datos referenciados y no puede ser eliminado"
					,SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		this.clean();
		this.showSaveMode();
	}
	
	public void report() {
		// TODO Auto-generated method stub
	}
	
	public void refresh() {
		cargarComboDerechoProgramaComercial();
		//cargarComboProductoCliente(); COMENTED 4 OCTUBRE
		//ADD 4 OCTUBRE
		cargarComboCampanaProductoVersion(); 
		cargarMapaCampanaProductoListProductoCliente();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	//Caso especial, aquí no se hace el find, sino lo que va en el mouse click 
	public void find() {
		cleanTable();
		cleanColeccions();
		
		if(clienteIf != null){
			try {
				Collection comercialesColeccion = null;
				if(campanaIf != null){
					comercialesColeccion = SessionServiceLocator.getComercialSessionService().findComercialByCampanaId(campanaIf.getId());
				}else if(clienteIf != null){
					comercialesColeccion = SessionServiceLocator.getComercialSessionService().findComercialByClienteIdAndByEmpresaId(clienteIf.getId(),Parametros.getIdEmpresa());
				}
				Iterator itComercialClienteColeccion = comercialesColeccion.iterator();
				
				while(itComercialClienteColeccion.hasNext()){					
					ComercialIf comercialClienteTemp = (ComercialIf) itComercialClienteColeccion.next();
					
					modelComercialCliente = (DefaultTableModel) getTblComercialCliente().getModel();
										
					Vector<String> filaComercialCliente = new Vector<String>();

					// Agregar a la coleccion de ComercialColeccion para grabar al final toda la coleccion
					comercialColeccion.add(comercialClienteTemp);
										
					/*COMENTED 5 OCTUBRE
					DerechoProgramaIf derechoProgramaTemp = (DerechoProgramaIf) SessionServiceLocator.getDerechoProgramaSessionService().getDerechoPrograma(comercialClienteTemp.getDerechoprogramaId());
					CampanaIf campanaTemp = (CampanaIf) SessionServiceLocator.getCampanaSessionService().getCampana(comercialClienteTemp.getCampanaId());
					*/
					
					//ADD 5 OCTUBRE
					DerechoProgramaIf derechoProgramaTemp = getDerechoProgramaOfListById(comercialClienteTemp.getDerechoprogramaId());
					CampanaIf campanaTemp = new CampanaData();
					if (campanaIf != null){
						campanaTemp = campanaIf;
					}else{
						campanaTemp = (CampanaIf) SessionServiceLocator.getCampanaSessionService().getCampana(comercialClienteTemp.getCampanaId());
					}
					
					CampanaProductoVersionIf campanaProductoVersion = mapCampanaProductoVersion.get(comercialClienteTemp.getCampanaProductoVersionId());
					//END 5 OCTUBRE
					
					// Agregra los valores al registro que va  ser añadido  a la tabla.
					filaComercialCliente.add(comercialClienteTemp.getCodigo());
					filaComercialCliente.add(campanaTemp.getCodigo() + " - " + campanaTemp.getNombre());
					
					filaComercialCliente.add(comercialClienteTemp.getNombre());
					filaComercialCliente.add(derechoProgramaTemp.getNombre());
					//filaComercialCliente.add(comercialClienteTemp.getDescripcion()); COMENTED 5 OCTUBRE
					
					filaComercialCliente.add(comercialClienteTemp.getVersion());
					filaComercialCliente.add(comercialClienteTemp.getDuracion());
						
					// Agrega informacion a la tabla visual para el usuario.
					modelComercialCliente.addRow(filaComercialCliente);
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
			this.showUpdateMode();
		}
		else{
			SpiritAlert.createAlert("Debe escoger un Cliente para realizar la búsqueda",SpiritAlert.WARNING);
			showFindMode();
		}
	}

	public void deleteComercialesCliente(){
		//Recorro ahora la coleccion de Comerciales de cliente
		for(int j=0;j<comercialColeccion.size();j++){
			ComercialIf comercialClienteTemp = (ComercialIf) comercialColeccion.get(j);
			try{
				//Elimino el Comercial Cliente encontrado
				SessionServiceLocator.getComercialSessionService().deleteComercial(comercialClienteTemp.getId());
			}catch (Exception e) {
				SpiritAlert.createAlert("No se puede eliminar el registro!"
						,SpiritAlert.ERROR);
				e.printStackTrace();
			}		
		}
	}
	
	public boolean isEmpty() {
		if ("".equals(this.getTxtCorporacion().getText()) && "".equals(this.getTxtCliente().getText()))
			return true;
		else
			return false;
	}

	public boolean validateFields() {
		String strCorporacion = this.getTxtCorporacion().getText();
		String strCliente = this.getTxtCliente().getText();
		
		if ((("".equals(strCorporacion)) || (strCorporacion == null))) {
			SpiritAlert.createAlert("Debe seleccionar una Corporación !!"
					,SpiritAlert.WARNING);
			getBtnBuscarCorporacion().grabFocus();
			return false;
		}
		if ((("".equals(strCliente)) || (strCliente == null))) {
			SpiritAlert.createAlert("Debe seleccionar un cliente !!"
					,SpiritAlert.WARNING);
			getBtnBuscarCliente().grabFocus();
			return false;
		}
		
		return true;
	}

	public void clean() {
		corporacionIf = null;
		clienteIf = null;	
		campanaIf = null;
		cleanTable();
		cleanColeccions();
		
		getTxtCorporacion().setText("");
		getTxtCliente().setText("");
		getTxtCodigoComercial().setText("");
		//getTxtDescripcionComercial().setText(""); COMENTED 5 OCTUBRE
		getTxtCampanaComercial().setText("");
		//getTxtVersionComercial().setText(""); COMENTED 5 OCTUBRE
		getTxtIdentificacionComercial().setText(""); //ADD 5 OCTUBRE
		getTxtTiempo().setText("");
	}
	
	public void cleanCliente() {
		clienteIf = null;	
		campanaIf = null;
		cleanTable();
		cleanColeccions();
		
		getTxtCliente().setText("");
		getTxtCodigoComercial().setText("");
		//COMENTED 28 SEPTIEMBRE
		//getCmbProductoCliente().setSelectedIndex(-1);
		//getTxtDescripcionComercial().setText("");
		
		//ADD 28 SEPTIEMBRE
		getCmbCampanaProductoVersion().setSelectedIndex(-1);
		getTxtProductoComercial().setText("");
		
		getTxtCampanaComercial().setText("");
		//getTxtVersionComercial().setText("");  COMENTED 5 OCTUBRE
		getTxtIdentificacionComercial().setText(""); //ADD 5 OCTUBRE
		getTxtTiempo().setText("");
	}
	
	public void cleanCampana() {
		campanaIf = null;
		cleanTable();
		cleanColeccions();
		
		getTxtCodigoComercial().setText("");
		//COMENTED 28 SEPTIEMBRE
		//getCmbProductoCliente().setSelectedIndex(-1);
		//getTxtDescripcionComercial().setText("");
		
		//ADD 28 SEPTIEMBRE
		getCmbCampanaProductoVersion().setSelectedIndex(-1);
		getTxtProductoComercial().setText("");
		
		getTxtCampanaComercial().setText("");
		//getTxtVersionComercial().setText(""); COMENTED 5 OCTUBRE
		getTxtIdentificacionComercial().setText(""); //ADD 5 OCTUBRE
		getTxtTiempo().setText("");
	}

	private void cleanColeccions() {
		comercialColeccion = null;
		comercialEliminadoColeccion = null;
		comercialColeccion = new Vector<ComercialIf>();
		comercialEliminadoColeccion = new Vector<ComercialIf>();
		//ADD 15 ABRIL
		listComercialesTemp = null;
		listComercialesTemp = new ArrayList<ComercialIf>();
	}

	private void cleanTable() {
		limpiarTabla(getTblComercialCliente());
		/*for(int i= getTblComercialCliente().getRowCount();i>0;--i)
			modelComercialCliente.removeRow(i-1);*/
	}
	
	public void showSaveMode() {
		setSaveMode();
		clean();
		getTxtCliente().setBackground(getBackground());
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getCmbDerechoProgramaComercial().setEnabled(true);
		//COMENTED 28 SEPTIEMBRE
		//getCmbProductoCliente().setEnabled(true);
		//getTxtDescripcionComercial().setEnabled(true);
				
		//ADD 28 SEPTIEMBRE
		getCmbCampanaProductoVersion().setEnabled(true);
						
		getBtnBuscarCampana().setEnabled(false);
		//getTxtVersionComercial().setEnabled(true); COMENETED 5 OCTUBRE
		getTxtIdentificacionComercial().setEnabled(true); //ADD 5 OCTUBRE
		getTxtTiempo().setEnabled(true);
		getBtnAgregarComercialCliente().setEnabled(true);
		getBtnActualizarComercialCliente().setEnabled(true);		
	}
	
	public void showUpdateMode() {
		setUpdateMode();
		getTxtCliente().setBackground(getBackground());
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getCmbDerechoProgramaComercial().setEnabled(true);
		//COMENTED 28 SEPTIEMBRE
		//getCmbProductoCliente().setEnabled(true);
		//getTxtDescripcionComercial().setEnabled(true);
		
		//ADD 28 SEPTIEMBRE
		getCmbCampanaProductoVersion().setEnabled(true);
		
		getBtnBuscarCampana().setEnabled(true);
		//getTxtVersionComercial().setEnabled(true);	COMENETED 5 OCTUBRE
		getTxtIdentificacionComercial().setEnabled(true); //ADD 5 OCTUBRE
		getTxtTiempo().setEnabled(true);
		getBtnAgregarComercialCliente().setEnabled(true);
		getBtnActualizarComercialCliente().setEnabled(true);
	}

	public void showFindMode() {
		getTxtCliente().setBackground(Parametros.findColor);
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getCmbDerechoProgramaComercial().setEnabled(false);
		//COMENTED 28 SEPTIEMBRE
		//getCmbProductoCliente().setEnabled(false);
		//getTxtDescripcionComercial().setEnabled(false);
		
		//ADD 28 SEPTIEMBRE
		getCmbCampanaProductoVersion().setEnabled(false);
		
		getBtnBuscarCampana().setEnabled(false);
		//getTxtVersionComercial().setEnabled(false);	COMENTED 5 OCTUBRE
		getTxtIdentificacionComercial().setEnabled(false); //ADD 5 OCTUBRE
		getTxtTiempo().setEnabled(false);
		getBtnAgregarComercialCliente().setEnabled(false);
		getBtnActualizarComercialCliente().setEnabled(false);
		
		getBtnBuscarCliente().grabFocus();
	}
		
	public boolean validateFieldsComercialCliente(){
		String strCampana = this.getTxtCampanaComercial().getText();
		//String strVersion = this.getTxtVersionComercial().getText(); COMENTED 5 OCTUBRE
		String strVersion = this.getTxtIdentificacionComercial().getText(); //ADD 5 OCTUBRE
		String strDuracion = this.getTxtTiempo().getText();
		String strCorporacion = this.getTxtCorporacion().getText();
		String strCliente = this.getTxtCliente().getText();
		
		if (corporacionIf == null && (("".equals(strCorporacion)) || (strCorporacion == null))) {
			SpiritAlert.createAlert("Debe seleccionar una Corporación para el Comercial!"
					,SpiritAlert.WARNING);
			getBtnBuscarCorporacion().grabFocus();
			return false;
		}
		
		if (clienteIf == null && (("".equals(strCliente)) || (strCliente == null))) {
			SpiritAlert.createAlert("Debe seleccionar un Cliente para el Comercial!"
					,SpiritAlert.WARNING);
			getBtnBuscarCliente().grabFocus();
			return false;
		}
		
		if (campanaIf == null && (("".equals(strCampana)) || (strCampana == null))) {
			SpiritAlert.createAlert("Debe seleccionar una Campaña para el Comercial!"
					,SpiritAlert.WARNING);
			getBtnBuscarCampana().grabFocus();
			return false;
		}
			
		if(getCmbDerechoProgramaComercial().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Derecho Programa para el Comercial!"
					,SpiritAlert.WARNING);
			getCmbDerechoProgramaComercial().grabFocus();
			return false;
		}
		
		/*COMENTED 28 SEPTIEMBRE
		if (getCmbProductoCliente().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar un Producto!",SpiritAlert.WARNING);
			getCmbProductoCliente().grabFocus();
			return false;
		}*/
		
		//ADD 28 SEPTIEMBRE
		if (getCmbCampanaProductoVersion().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar una Versión!",SpiritAlert.WARNING);
			getCmbCampanaProductoVersion().grabFocus();
			return false;
		}
		//END 28 SEPTIEMBRE
		
		if ((("".equals(strVersion)) || (strVersion == null))) {
			SpiritAlert.createAlert("Debe ingresar una identificación para el Comercial!"
					,SpiritAlert.WARNING);
			//getTxtVersionComercial().grabFocus(); COMENTED 5 OCTUBRE
			getTxtIdentificacionComercial().grabFocus();  //ADD 5 OCTUBRE
			return false;
		}
		
		if ((("".equals(strDuracion)) || (strDuracion == null))) {
			SpiritAlert.createAlert("Debe ingresar una duración para el Comercial!"
					,SpiritAlert.WARNING);
			getTxtTiempo().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public void cargarCombos(){
		cargarComboDerechoProgramaComercial();
		//cargarComboProductoCliente(); COMENTED 4 OCTUBRE
		//ADD 4 OCTUBRE
		cargarComboCampanaProductoVersion();
		
		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
		getCmbEstado().repaint();
	}
	
	//ADD 5 OCTUBRE
	private DerechoProgramaIf getDerechoProgramaOfListById(Long idDerechoPrograma){
		DerechoProgramaIf derechoPrograma = null;
		
		for (DerechoProgramaIf derechoProgramaIf : derechosProgramasComerciales){
			if (derechoProgramaIf.getId().compareTo(idDerechoPrograma) == 0){
				derechoPrograma = derechoProgramaIf;
			}
		}		
		return derechoPrograma;
	}
	//END 5 OCTUBRE
	
	private void cargarComboDerechoProgramaComercial(){
		try {
			//List derechosProgramasComerciales = (List) SessionServiceLocator.getDerechoProgramaSessionService().findDerechoProgramaByEmpresaId(Parametros.getIdEmpresa());
			derechosProgramasComerciales = (List<DerechoProgramaIf>) SessionServiceLocator.getDerechoProgramaSessionService().findDerechoProgramaByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbDerechoProgramaComercial(),derechosProgramasComerciales);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	//ADD 4 OCTUBRE
	private void cargarMapaCampanaProductoVersionByCampanaId(){
		try {
			mapCampanaProductoVersion = null;
			mapCampanaProductoVersion = new LinkedHashMap<Long, CampanaProductoVersionIf>();
			if(listCampanaProductoVersionIf != null && listCampanaProductoVersionIf.size() > 0){
				for (CampanaProductoVersionIf campanaProductoVersion : listCampanaProductoVersionIf){
					mapCampanaProductoVersion.put(campanaProductoVersion.getId(),campanaProductoVersion);
				}
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	//END 4 OCTUBRE
	
	//ADD 28 SEPTIEMBRE	
	private void cargarMapaCampanaProductoListProductoCliente(){
		try {
			mapIdCampanaProductoListProductoCliente = null;
			mapIdCampanaProductoListProductoCliente = new LinkedHashMap<Long, ProductoClienteIf>();
			if(campanaIf != null){
				//List productosCliente = (List) SessionServiceLocator.getProductoClienteSessionService().findProductoClienteByCampanaId(campanaIf.getId());	
				List<CampanaProductoIf> campanaProductoList = (List) SessionServiceLocator.getCampanaProductoSessionService().findCampanaProductoByCampanaId(campanaIf.getId());
				
				for (CampanaProductoIf campanaProducto : campanaProductoList){
					ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(campanaProducto.getProductoClienteId());	
					mapIdCampanaProductoListProductoCliente.put(campanaProducto.getId(),productoCliente);
				}
				 
			}			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	//END 28 SEPTIEMBRE
	
	//ADD 28 SEPTIEMBRE
	private void cargarComboCampanaProductoVersion(){
		try {
			if(campanaIf != null){
				listCampanaProductoVersionIf = (List)SessionServiceLocator.getCampanaProductoVersionSessionService().findCampanaProductoVersionByIdCampana(campanaIf.getId(),Parametros.getIdEmpresa());
								
				refreshCombo(getCmbCampanaProductoVersion(),listCampanaProductoVersionIf);
			}			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	//END 28 SEPTIEMBRE
	
	/*COMENTED 28 SEPTIEMBRE
	private void cargarComboProductoCliente(){
		try {
			if(campanaIf != null){
				List productosCliente = (List) SessionServiceLocator.getProductoClienteSessionService().findProductoClienteByCampanaId(campanaIf.getId());
				refreshCombo(getCmbProductoCliente(),productosCliente);
			}			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	*/
	
	
	public void setSelectedRowTblComercial(int row) {
		this.selectedRowTblComercial = row;
	}
	
	public int getSelectedRowTblComercial() {
		return this.selectedRowTblComercial;
	}

	public void addDetail() {
		agregarComercialCliente();
	}

	public void updateDetail() {
		actualizarComercialCliente();
	}
	
	public void deleteDetail() {
		eliminarComercialCliente();
	}
}
