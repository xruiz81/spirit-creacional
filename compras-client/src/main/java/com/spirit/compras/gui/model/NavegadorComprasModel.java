package com.spirit.compras.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.gui.panel.JPNavegadorCompras;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.entity.TipoNegocioIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.util.Utilitarios;

public class NavegadorComprasModel extends JPNavegadorCompras{
	
	private static final String NOMBRE_ESTADO_ACTIVA = "ACTIVA";
	private static final String ESTADO_ACTIVA = "A";
	private static final String NOMBRE_ESTADO_INACTIVA = "INACTIVA";
	private static final String ESTADO_INACTIVA = "I";
	private static final String NOMBRE_ESTADO_ENVIADA = "ENVIADA";
	private static final String ESTADO_ENVIADA = "E";
	private static final String NOMBRE_PERSONA_NATURAL = "NATURAL";
	private static final String PERSONA_NATURAL = "N";
	private static final String NOMBRE_PERSONA_JURIDICA = "JURIDICA";
	private static final String PERSONA_JURIDICA = "J";
	private static final String NOMBRE_SI = "SI";
	private static final String SI = "S";
	private static final String NOMBRE_NO = "NO";
	private static final String NO = "N";
	private static final String NOMBRE_TIPOCOMPRA_LOCAL = "LOCAL";
	private static final String TIPOCOMPRA_LOCAL = "L";
	private static final String NOMBRE_TIPOCOMPRA_IMPORTACION = "IMPORTACIÓN";
	private static final String TIPOCOMPRA_IMPORTACION = "I";
	
	private Icon customOpenIcon = SpiritResourceManager.getImageIcon("images/icons/funcion/dineroColor.png");
	private Icon customClosedIcon = SpiritResourceManager.getImageIcon("images/icons/funcion/dineroBlanco.png");
	private Icon customLeftIcon = SpiritResourceManager.getImageIcon("images/icons/funcion/leftNodeVerde.png");
			
	private Calendar calendarFechaInicio = new GregorianCalendar();
	private Calendar calendarFechaFin = new GregorianCalendar();
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private JDPopupFinderModel popupFinder;	
	private ClienteOficinaIf proveedorIf;
	private DefaultMutableTreeNode node;
	private ClienteIf cliente;
	private OrdenCompraIf ordenCompra;
	private CompraIf compra;
	private String nodoOrdenCompra;
	private String nodo;
	private int espacioBlanco; 
	private Map aMapBusquedaxEstado = new HashMap();
	private Map<Long,TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long,TipoDocumentoIf>();
	private Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
	private Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	
	public NavegadorComprasModel(){
		cargarMapas();
		showSaveMode();
		cargarCombos();
		initKeyListeners();
		initListeners();
		new HotKeyComponent(this);
	}
	
	public void cargarMapas(){
		try {
			mapaTipoDocumento.clear();
			Collection tiposDocumento = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator tiposDocumentoIt = tiposDocumento.iterator();
			while(tiposDocumentoIt.hasNext()){
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)tiposDocumentoIt.next();
				mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
			}
			
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while(clientesOficinaIt.hasNext()){
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf)clientesOficinaIt.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
			
			mapaCliente.clear();
			Collection clientes = SessionServiceLocator.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesIt = clientes.iterator();
			while(clientesIt.hasNext()){
				ClienteIf cliente = (ClienteIf)clientesIt.next();
				mapaCliente.put(cliente.getId(), cliente);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	private void cargarArbol(){
		try {
			DefaultTreeModel treeModel = generateTreeModel();					
			getArbolNavegadorCompras().setModel(treeModel);
						
			DefaultTreeCellRenderer customTreeRenderer = new DefaultTreeCellRenderer();
			customTreeRenderer.setOpenIcon(customOpenIcon);
			customTreeRenderer.setClosedIcon(customClosedIcon);
			customTreeRenderer.setLeafIcon(customLeftIcon);
			getArbolNavegadorCompras().setCellRenderer(customTreeRenderer);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al generar el árbol!",SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	Comparator<ClienteOficinaIf> ordenadorClientesOficina = new Comparator<ClienteOficinaIf>(){
		public int compare(ClienteOficinaIf co1, ClienteOficinaIf co2) {
			return mapaCliente.get(co1.getClienteId()).getNombreLegal().compareTo(mapaCliente.get(co2.getClienteId()).getNombreLegal());
		}		
	};
	
	private DefaultTreeModel generateTreeModel() throws GenericBusinessException {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("NAVEGADOR DE COMPRAS");	
		Collection proveedores = null;
		calendarFechaInicio = getCmbFechaInicio().getCalendar();
		calendarFechaFin = getCmbFechaFin().getCalendar();
		
		try {
			if(!getCbProveedor().isSelected()){
				proveedores = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
				Collections.sort((ArrayList)proveedores,ordenadorClientesOficina);
				Iterator proveedoresIterator = proveedores.iterator();
				while(proveedoresIterator.hasNext()){
					ClienteOficinaIf clienteOficina = (ClienteOficinaIf) proveedoresIterator.next();
					
					List<CompraIf> compras = null;
					aMapBusquedaxEstado = new HashMap();
					aMapBusquedaxEstado.put("proveedorId", clienteOficina.getId());
					
					if((getCbTodasCompras().isSelected() || getCbProveedor().isSelected()) && !getCbRangoFechas().isSelected()&& !getCbEstado().isSelected()){
						compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByQuery(aMapBusquedaxEstado);
					}else if((getCbProveedor().isSelected() && getCbRangoFechas().isSelected() && !getCbEstado().isSelected()) || (getCbRangoFechas().isSelected()&& !getCbEstado().isSelected())){
						compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByFechaInicioByFechaFinByOficinaIdAndByProveedorId(new Date(calendarFechaInicio.getTime().getTime()), new Date(calendarFechaFin.getTime().getTime()), Parametros.getIdOficina(), clienteOficina.getId());
					}else{
						if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVA)){
							aMapBusquedaxEstado.put("estado", ESTADO_ACTIVA);
						}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVA)){
							aMapBusquedaxEstado.put("estado", ESTADO_INACTIVA);
						}
						
						if((getCbProveedor().isSelected() && getCbEstado().isSelected() && !getCbRangoFechas().isSelected()) || (getCbEstado().isSelected() && !getCbRangoFechas().isSelected())){				
							compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByQuery(aMapBusquedaxEstado);
						}else if(getCbRangoFechas().isSelected() && getCbEstado().isSelected()){				
							compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByQueryByFechaInicioAndByFechaFin(aMapBusquedaxEstado, new Date(calendarFechaInicio.getTime().getTime()), new Date(calendarFechaFin.getTime().getTime()));
						}
					}
					
					if(compras.size() > 0){
						ClienteIf nodo = mapaCliente.get(clienteOficina.getClienteId());
						DefaultMutableTreeNode proveedorNodo = new DefaultMutableTreeNode(nodo);
						root.add(proveedorNodo);
						
						for(int k=0; k<compras.size(); k++){
							CompraIf nodoCompra = compras.get(k);
							DefaultMutableTreeNode nodoHijoCompra = new DefaultMutableTreeNode(nodoCompra + " (C)");
							proveedorNodo.add(nodoHijoCompra);
						}	
					}									
				}
			}else{
				if(proveedorIf != null){
					List<CompraIf> compras = null;
					aMapBusquedaxEstado = new HashMap();
					aMapBusquedaxEstado.put("proveedorId", proveedorIf.getId());
					
					if((getCbTodasCompras().isSelected() || getCbProveedor().isSelected()) && !getCbRangoFechas().isSelected()&& !getCbEstado().isSelected()){
						compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByQuery(aMapBusquedaxEstado);
					}else if((getCbProveedor().isSelected() && getCbRangoFechas().isSelected() && !getCbEstado().isSelected()) || (getCbRangoFechas().isSelected()&& !getCbEstado().isSelected())){
						compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByFechaInicioByFechaFinByOficinaIdAndByProveedorId(new Date(calendarFechaInicio.getTime().getTime()), new Date(calendarFechaFin.getTime().getTime()), Parametros.getIdOficina(), proveedorIf.getId());
					}else{
						if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVA)){
							aMapBusquedaxEstado.put("estado", ESTADO_ACTIVA);
						}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVA)){
							aMapBusquedaxEstado.put("estado", ESTADO_INACTIVA);
						}
						
						if((getCbProveedor().isSelected() && getCbEstado().isSelected() && !getCbRangoFechas().isSelected()) || (getCbEstado().isSelected() && !getCbRangoFechas().isSelected())){				
							compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByQuery(aMapBusquedaxEstado);
						}else if(getCbRangoFechas().isSelected() && getCbEstado().isSelected()){				
							compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByQueryByFechaInicioAndByFechaFin(aMapBusquedaxEstado, new Date(calendarFechaInicio.getTime().getTime()), new Date(calendarFechaFin.getTime().getTime()));
						}
					}
					
					if(compras.size() > 0){
						ClienteIf nodo = mapaCliente.get(proveedorIf.getClienteId());
						DefaultMutableTreeNode proveedorNodo = new DefaultMutableTreeNode(nodo);
						root.add(proveedorNodo);
						
						for(int k=0; k<compras.size(); k++){
							CompraIf nodoCompra = compras.get(k);
							DefaultMutableTreeNode nodoHijoCompra = new DefaultMutableTreeNode(nodoCompra + " (C)");
							proveedorNodo.add(nodoHijoCompra);
						}	
					}				
				}else{
					SpiritAlert.createAlert("Debe seleccionar el Proveedor", SpiritAlert.WARNING);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el árbol", SpiritAlert.ERROR);
		}	
		
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);
		return defaultTreeModel;
	}
	
	/*private void insertarNodosOrdenCompra(DefaultMutableTreeNode nodoSeleccionado, ClienteOficinaIf proveedor) {
		try {		
			List<OrdenCompraIf>	ordenesCompra = (List<OrdenCompraIf>) SessionServiceLocator.getOrdenCompraSessionService().findOrdenCompraByProveedorId(proveedor.getId());
			
			for(int k=0; k<ordenesCompra.size(); k++){
				OrdenCompraIf nodoOrdenCompra = ordenesCompra.get(k);
				if(tieneCompras(proveedor, nodoOrdenCompra)){
					DefaultMutableTreeNode nodoHijoOrdenCompra = new DefaultMutableTreeNode(nodoOrdenCompra+" (OC)");
					nodoSeleccionado.add(nodoHijoOrdenCompra);
					insertarNodosCompra(nodoHijoOrdenCompra, nodoOrdenCompra, proveedor);
				}					
			}			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}*/
	
	/*private void insertarNodosCompra(DefaultMutableTreeNode nodoSeleccionado, OrdenCompraIf ordenCompra, ClienteOficinaIf proveedor) {
		try {
			List<CompraIf> compras = null;
			if((getCbTodasCompras().isSelected() || getCbProveedor().isSelected()) && !getCbRangoFechas().isSelected()&& !getCbEstado().isSelected()){
				compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByOrdencompraId(ordenCompra.getId());
			}else if((getCbProveedor().isSelected() && getCbRangoFechas().isSelected() && !getCbEstado().isSelected()) || (getCbRangoFechas().isSelected()&& !getCbEstado().isSelected())){
				compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByFechaInicioByFechaFinByOficinaIdAndByOrdenCompraId(new Date(calendarFechaInicio.getTime().getTime()), new Date(calendarFechaFin.getTime().getTime()), Parametros.getIdOficina(), ordenCompra.getId());
			}else if((getCbProveedor().isSelected() && getCbEstado().isSelected() && !getCbRangoFechas().isSelected()) || (getCbEstado().isSelected() && !getCbRangoFechas().isSelected())){				
				compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByQuery(aMapBusquedaxEstado);
			}else if(getCbRangoFechas().isSelected() && getCbEstado().isSelected()){				
				compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByQueryByFechaInicioAndByFechaFin(aMapBusquedaxEstado, new Date(calendarFechaInicio.getTime().getTime()), new Date(calendarFechaFin.getTime().getTime()));
			}
			
			for(int k=0; k<compras.size(); k++){
				CompraIf nodoCompra = compras.get(k);
				DefaultMutableTreeNode nodoHijoCompra = new DefaultMutableTreeNode(nodoCompra + " (C)");
				nodoSeleccionado.add(nodoHijoCompra);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}*/
	
	public boolean tieneCompras(ClienteOficinaIf clienteOficina, OrdenCompraIf nodoOrdenCompra) throws GenericBusinessException{
		List<CompraIf> compras = null;
		aMapBusquedaxEstado = new HashMap();
		if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVA)){
			aMapBusquedaxEstado.put("estado", ESTADO_ACTIVA);
		}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVA)){
			aMapBusquedaxEstado.put("estado", ESTADO_INACTIVA);
		}
		aMapBusquedaxEstado.put("ordencompraId", nodoOrdenCompra.getId());
		aMapBusquedaxEstado.put("proveedorId", clienteOficina.getId());
		
		/*if((getCbTodasCompras().isSelected() || getCbProveedor().isSelected()) && !getCbRangoFechas().isSelected()&& !getCbEstado().isSelected()){
			compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByOrdencompraId(nodoOrdenCompra.getId());
		}else if((getCbProveedor().isSelected() && getCbRangoFechas().isSelected() && !getCbEstado().isSelected()) || (getCbRangoFechas().isSelected()&& !getCbEstado().isSelected())){
			compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByFechaInicioByFechaFinByOficinaIdAndByOrdenCompraId(new Date(calendarFechaInicio.getTime().getTime()), new Date(calendarFechaFin.getTime().getTime()), Parametros.getIdOficina(), nodoOrdenCompra.getId());
		}else if((getCbProveedor().isSelected() && getCbEstado().isSelected() && !getCbRangoFechas().isSelected()) || (getCbEstado().isSelected() && !getCbRangoFechas().isSelected())){				
			compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByQuery(aMapBusquedaxEstado);
		}else if(getCbRangoFechas().isSelected() && getCbEstado().isSelected()){				
			compras = (List<CompraIf>) SessionServiceLocator.getCompraSessionService().findCompraByQueryByFechaInicioAndByFechaFin(aMapBusquedaxEstado, new Date(calendarFechaInicio.getTime().getTime()), new Date(calendarFechaFin.getTime().getTime()));
		}*/
		
		if((compras != null) && compras.size()>0){
			return true;
		}
		
		return false;
	}
	
	private void initKeyListeners() {
		
		getBtnProveedor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnProveedor().setToolTipText("Buscar Proveedor");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnConsultar().setToolTipText("Consultar");
		
		getTxtProveedor().setEditable(false);
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setEditable(false);
		getCmbFechaCreacionCompra().setLocale(Utilitarios.esLocale);
		getCmbFechaCreacionCompra().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaCreacionCompra().setEditable(false);
		getCmbFechaVencimientoCompra().setLocale(Utilitarios.esLocale);
		getCmbFechaVencimientoCompra().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaVencimientoCompra().setEditable(false);
		
		getTxtCodigoCompra().setEditable(false);
		getTxtContribuyenteEspecial().setEditable(false);
		getTxtCorporacion().setEditable(false);
		getTxtEstadoCompra().setEditable(false);
		getTxtIdentificacion().setEditable(false);
		getTxtLlevaContabilidad().setEditable(false);
		getTxtMonedaCompra().setEditable(false);
		getTxtNombreLegal().setEditable(false);
		getTxtObservacionCompra().setEditable(false);
		getTxtObservaciones().setEditable(false);
		getTxtOficinaCompra().setEditable(false);
		getTxtProveedorCompra().setEditable(false);
		getTxtRazonSocial().setEditable(false);
		getTxtRepresentante().setEditable(false);
		getTxtTipoCompraCompra().setEditable(false);
		getTxtTipoDocumentoCompra().setEditable(false);
		getTxtTipoIdentificacion().setEditable(false);
		getTxtTipoNegocio().setEditable(false);
		getTxtTipoPersona().setEditable(false);
		getTxtTipoProveedor().setEditable(false);
		getTxtUsuarioFinal().setEditable(false);
		getTxtReferencia().setEditable(false);
		getTxtTotal().setEditable(false);
	}
	
	public void cargarCombos(){
		getCmbEstado().addItem(NOMBRE_ESTADO_ACTIVA);
		getCmbEstado().addItem(NOMBRE_ESTADO_INACTIVA);
	}
	
	private void initListeners() {
		getBtnProveedor().addActionListener(oActionListenerBtnProveedor);
		getCmbFechaInicio().addActionListener(oActionListenerCmbFechaInicio);
		getCmbFechaFin().addActionListener(oActionListenerCmbFechaFin);
		getBtnConsultar().addActionListener(oActionListenerBtnBuscar);
		getCbTodasCompras().addActionListener(oActionListenerCbTodasCompras);
		getCbProveedor().addActionListener(oActionListenerCbProveedor);
		getCbRangoFechas().addActionListener(oActionListenerCbRangoFechas);
		getCbEstado().addActionListener(oActionListenerCbEstado);
		
		getArbolNavegadorCompras().addTreeSelectionListener(new TreeSelectionListener() {
		    public void valueChanged(TreeSelectionEvent e) {
		    	listenerArbolNavegadorCompras();
		    }
		});
	}
	
	ActionListener oActionListenerBtnBuscar = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if(getCbTodasCompras().isSelected()||getCbProveedor().isSelected()||getCbRangoFechas().isSelected()||getCbEstado().isSelected()){
				cargarArbol();
			}
			else{
				SpiritAlert.createAlert("Debe seleccionar al menos un Criterio de Búsqueda", SpiritAlert.INFORMATION);
			}
		}
	};
	
	ActionListener oActionListenerCbTodasCompras = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			getCbProveedor().setSelected(false);
			getCbRangoFechas().setSelected(false);
			getCbEstado().setSelected(false);
			getTxtProveedor().setText("");
			proveedorIf = null;
		}
	};
	
	ActionListener oActionListenerCbProveedor = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			getCbTodasCompras().setSelected(false);
			getTxtProveedor().setText("");
			proveedorIf = null;
		}
	};
	
	ActionListener oActionListenerCbRangoFechas = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			getCbTodasCompras().setSelected(false);
		}
	};
	
	ActionListener oActionListenerCbEstado = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			getCbTodasCompras().setSelected(false);
		}
	};
	
	private void listenerArbolNavegadorCompras() {
		try {
    		node = (DefaultMutableTreeNode) getArbolNavegadorCompras().getLastSelectedPathComponent();
    		cliente = null;
    		ordenCompra = null;
    		compra = null;
    		nodoOrdenCompra = "";
    		nodo = "";
    		espacioBlanco = 0; 
    		
    		if(node != null){
    			if(node.getUserObject() instanceof ClienteIf){
    				cleanDetalles();
		    		cliente = (ClienteIf) node.getUserObject();
		    		getJtpNavegadorCompras().setSelectedIndex(0);
		    		setearDatosProveedor(cliente);
		    	}
		    	else if(node.getUserObject() instanceof String){
		    		cleanDetalles();
		    		nodo = (String) node.getUserObject();
		    		espacioBlanco = nodo.lastIndexOf(" ");
		    		if(nodo.substring(espacioBlanco+1).equals("(C)")){
		    			cliente = (ClienteIf)((DefaultMutableTreeNode) node.getParent()).getUserObject();
		    			setearDatosProveedor(cliente);
		    			compra = (CompraIf)SessionServiceLocator.getCompraSessionService().findCompraByCodigo(nodo.substring(0, espacioBlanco)).iterator().next();
		    			setearDatosCompra(cliente, compra);	
		    			getJtpNavegadorCompras().setSelectedIndex(1);
		    		}
		    	}   
		    	else{
		    		cleanDetalles();
		    	}
    		}
    		else{
    			cleanDetalles();
	    	}		    		
    	} catch (GenericBusinessException e1) {
			e1.printStackTrace();
			SpiritAlert.createAlert("Error en el listener del árbol", SpiritAlert.ERROR);
		}
	}
	
	private void setearDatosCompra(ClienteIf cliente, CompraIf compra) throws GenericBusinessException {
		getTxtCodigoCompra().setText(compra.getCodigo());
		getCmbFechaCreacionCompra().setDate(compra.getFecha());
		getCmbFechaVencimientoCompra().setDate(compra.getFechaVencimiento());
		OficinaIf oficina = SessionServiceLocator.getOficinaSessionService().getOficina(compra.getOficinaId());
		getTxtOficinaCompra().setText(oficina.getNombre());
		getTxtProveedorCompra().setText(cliente.getIdentificacion() + " - " + cliente.getNombreLegal());
		TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(compra.getTipodocumentoId());
		getTxtTipoDocumentoCompra().setText(tipoDocumento.getNombre());
		MonedaIf moneda = SessionServiceLocator.getMonedaSessionService().getMoneda(compra.getMonedaId());
		getTxtMonedaCompra().setText(moneda.getNombre());
		if(compra.getEstado().equals(ESTADO_INACTIVA)){
			getTxtEstadoCompra().setText(NOMBRE_ESTADO_INACTIVA);
		}else if(compra.getEstado().equals(ESTADO_ACTIVA)){
			getTxtEstadoCompra().setText(NOMBRE_ESTADO_ACTIVA);
		}
		if(compra.getLocalimportada().equals(TIPOCOMPRA_LOCAL)){
			getTxtTipoCompraCompra().setText(NOMBRE_TIPOCOMPRA_LOCAL);
		}else if(compra.getLocalimportada().equals(TIPOCOMPRA_IMPORTACION)){
			getTxtTipoCompraCompra().setText(NOMBRE_TIPOCOMPRA_IMPORTACION);
		}
		if(compra.getObservacion() != null){
			getTxtObservacionCompra().setText(compra.getObservacion());
		}
		if(compra.getReferencia() != null){
			getTxtReferencia().setText(compra.getReferencia());
		}
		BigDecimal total = compra.getValor().subtract(compra.getDescuento()).add(compra.getIva()).add(compra.getIce()).add(compra.getOtroImpuesto());
		getTxtTotal().setText(formatoDecimal.format(total));
	}

	private void setearDatosProveedor(ClienteIf cliente) throws GenericBusinessException {
		if(cliente.getIdentificacion() != null){
			getTxtIdentificacion().setText(cliente.getIdentificacion()); 
		}
		if(cliente.getTipoidentificacionId() != null){
			TipoIdentificacionIf tipoIdentificacion = SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacion(cliente.getTipoidentificacionId());
			getTxtTipoIdentificacion().setText(tipoIdentificacion.getNombre());
		}
		if(cliente.getNombreLegal() != null){
			getTxtNombreLegal().setText(cliente.getNombreLegal()); 
		}
		if(cliente.getRazonSocial() != null){
			getTxtRazonSocial().setText(cliente.getRazonSocial());
		}
		if(cliente.getRepresentante() != null){
			getTxtRepresentante().setText(cliente.getRepresentante()); 			    		
		}
		if(cliente.getCorporacionId() != null){
			CorporacionIf corporacion = SessionServiceLocator.getCorporacionSessionService().getCorporacion(cliente.getCorporacionId());
			getTxtCorporacion().setText(corporacion.getNombre()); 			    		
		}
		if(cliente.getTiponegocioId() != null){
			TipoNegocioIf tipoNegocio = SessionServiceLocator.getTipoNegocioSessionService().getTipoNegocio(cliente.getTiponegocioId());
			getTxtTipoNegocio().setText(tipoNegocio.getNombre()); 			    		
		}
		if(cliente.getTipoproveedorId() != null){
			TipoProveedorIf tipoProveedor = SessionServiceLocator.getTipoProveedorSessionService().getTipoProveedor(cliente.getTipoproveedorId());
			getTxtTipoProveedor().setText(tipoProveedor.getNombre()); 		    		
		}
		if((cliente.getContribuyenteEspecial() != null) && (cliente.getContribuyenteEspecial().equals(SI))){
			getTxtContribuyenteEspecial().setText(NOMBRE_SI); 
		}else if((cliente.getContribuyenteEspecial() != null) && (cliente.getContribuyenteEspecial().equals(NO))){
			getTxtContribuyenteEspecial().setText(NOMBRE_NO); 
		}
		if((cliente.getUsuariofinal() != null) && (cliente.getUsuariofinal().equals(SI))){
			getTxtUsuarioFinal().setText(NOMBRE_SI); 
		}else if((cliente.getUsuariofinal() != null) && (cliente.getUsuariofinal().equals(NO))){
			getTxtUsuarioFinal().setText(NOMBRE_NO); 
		}
		if((cliente.getLlevaContabilidad() != null) && (cliente.getLlevaContabilidad().equals(SI))){
			getTxtLlevaContabilidad().setText(NOMBRE_SI); 
		}else if((cliente.getLlevaContabilidad() != null) && (cliente.getLlevaContabilidad().equals(NO))){
			getTxtLlevaContabilidad().setText(NOMBRE_NO); 
		}
		if(cliente.getObservacion() != null){
			getTxtObservaciones().setText(cliente.getObservacion()); 
		}
		if((cliente.getTipoPersona() != null) && (cliente.getTipoPersona().equals(PERSONA_JURIDICA))){
			getTxtTipoPersona().setText(NOMBRE_PERSONA_JURIDICA); 
		}else if((cliente.getTipoPersona() != null) && (cliente.getTipoPersona().equals(PERSONA_NATURAL))){
			getTxtTipoPersona().setText(NOMBRE_PERSONA_NATURAL); 
		}
	}
	
	ActionListener oActionListenerBtnProveedor = new ActionListener(){
		public void actionPerformed(ActionEvent evento){
			Long idCorporacion = 0L;
			Long idCliente = 0L;
			String tipoCliente = "PR";
			String tituloVentanaBusqueda = "Proveedores";
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.addElement(10);
			anchoColumnas.addElement(350);
					
			clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, "", false);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if (popupFinder.getElementoSeleccionado() != null){
				proveedorIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
				ClienteIf proveedor = mapaCliente.get(proveedorIf.getClienteId());
				if(proveedor != null){
					getTxtProveedor().setText(proveedor.getIdentificacion() + " - " + proveedor.getNombreLegal() + " - " + proveedorIf.getDescripcion());
				} else{
					SpiritAlert.createAlert("No existe el Proveedor", SpiritAlert.ERROR);
				}
			}
		}
	};
	
	ActionListener oActionListenerCmbFechaInicio = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if(getCmbFechaInicio().getCalendar() != null){
				calendarFechaInicio = getCmbFechaInicio().getCalendar();
				
				if(calendarFechaInicio.after(calendarFechaFin)){
					getCmbFechaFin().setCalendar(calendarFechaInicio);
				}
			}
		}
	};
	
	ActionListener oActionListenerCmbFechaFin = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if(getCmbFechaFin().getCalendar() != null){
				calendarFechaFin = getCmbFechaFin().getCalendar();
				
				if(calendarFechaFin.before(calendarFechaInicio)){
					getCmbFechaInicio().setCalendar(calendarFechaFin);
				}
			}
		}
	};
	
	public void clean() {
		proveedorIf = null;
		cleanArbol();
		cleanDetalles();
		
		getTxtProveedor().setText("");
		getCbEstado().setSelected(false);
		getCbProveedor().setSelected(false); 
		getCbRangoFechas().setSelected(false); 
		
		Calendar calendar = new GregorianCalendar();
		getCmbFechaInicio().setCalendar(calendar);
		getCmbFechaFin().setCalendar(calendar); 
	}
	
	private void cleanDetalles(){
		getCmbFechaCreacionCompra().setCalendar(null);
		getCmbFechaVencimientoCompra().setCalendar(null); 
		getTxtCodigoCompra().setText(""); 
		getTxtContribuyenteEspecial().setText(""); 
		getTxtCorporacion().setText(""); 
		getTxtEstadoCompra().setText(""); 
		getTxtIdentificacion().setText(""); 
		getTxtLlevaContabilidad().setText(""); 
		getTxtMonedaCompra().setText(""); 
		getTxtNombreLegal().setText(""); 
		getTxtObservacionCompra().setText(""); 
		getTxtObservaciones().setText(""); 
		getTxtOficinaCompra().setText(""); 
		getTxtProveedorCompra().setText(""); 
		getTxtRazonSocial().setText("");
		getTxtRepresentante().setText(""); 
		getTxtTipoCompraCompra().setText(""); 
		getTxtTipoDocumentoCompra().setText(""); 
		getTxtTipoIdentificacion().setText(""); 
		getTxtTipoNegocio().setText(""); 
		getTxtTipoPersona().setText("");
		getTxtTipoProveedor().setText(""); 
		getTxtUsuarioFinal().setText(""); 
	}
	
	public void cleanArbol() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("NAVEGADOR DE COMPRAS");	
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);
		getArbolNavegadorCompras().setModel(defaultTreeModel);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
	}
	 
}
