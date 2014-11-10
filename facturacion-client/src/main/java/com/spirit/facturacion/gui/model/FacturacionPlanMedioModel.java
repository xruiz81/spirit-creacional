package com.spirit.facturacion.gui.model;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.WindowConstants;

import org.apache.commons.collections.map.LinkedMap;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.gui.panel.JDFacturacionPlanMedio;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.medios.entity.CampanaProductoIf;
import com.spirit.medios.entity.CampanaProductoVersionIf;
import com.spirit.medios.entity.ComercialData;
import com.spirit.medios.entity.ComercialIf;
import com.spirit.medios.entity.DerechoProgramaIf;
import com.spirit.medios.entity.PlanMedioFacturacionIf;
import com.spirit.medios.entity.PlanMedioFormaPagoIf;
import com.spirit.medios.entity.ProductoClienteIf;

public class FacturacionPlanMedioModel extends JDFacturacionPlanMedio{
	
	private int positionEscogida = -1; 
	private ArrayList<CampanaProductoVersionIf> listaCampanaProductoVersion = new ArrayList<CampanaProductoVersionIf>();
	private Map<CampanaProductoVersionIf,ArrayList<PlanMedioFacturacionIf>> listPlanMedioFacturacionCanjeMap = new LinkedHashMap<CampanaProductoVersionIf, ArrayList<PlanMedioFacturacionIf>>();
	private Map<CampanaProductoVersionIf,ArrayList<PlanMedioFacturacionIf>> listPlanMedioFacturacionNoCanjeMap = new LinkedHashMap<CampanaProductoVersionIf, ArrayList<PlanMedioFacturacionIf>>();
	private Map<ClienteOficinaIf,ArrayList<PlanMedioFacturacionIf>> listPlanMedioFacturacionCanjeByProveedorMap = new LinkedHashMap<ClienteOficinaIf, ArrayList<PlanMedioFacturacionIf>>();
	private ArrayList<ProductoClienteIf> listProductosClienteNoCanje = new ArrayList<ProductoClienteIf>();
	private Map<ProductoClienteIf,ArrayList<Long>> mapaProductosClienteVersionesNoCanje = new LinkedHashMap<ProductoClienteIf,ArrayList<Long>>();
	private ArrayList<CampanaProductoVersionIf> listCampanaProductoVersionCanje   = new ArrayList<CampanaProductoVersionIf>();
	private Map<ClienteOficinaIf,ArrayList<PlanMedioFacturacionIf>> listPlanMedioFacturacionNoCanjeByProveedorMap = new LinkedHashMap<ClienteOficinaIf, ArrayList<PlanMedioFacturacionIf>>(); //Los Canales son los Clientes de Tipo Proveedor
	private boolean  checkPeriodoSelected   = false;
	private Date     periodoFechaInicio     = null;
	private Date 	 periodoFechaFin	    = null;
	private Calendar calendarFechaInicioMin = GregorianCalendar.getInstance();
	private Calendar calendarFechaFinMax    = GregorianCalendar.getInstance();
	private Calendar calendarFechaInicioSeleccionada = GregorianCalendar.getInstance();
	private Calendar calendarFechaFinSeleccionada    = GregorianCalendar.getInstance();
	private Collection<PlanMedioFormaPagoIf> planMedioFormaPagoColl;
	private Long idProductoElegido = new Long(0);
	private Long versionProductoElegidoId;
	private Long planMedioId = 0L;
	private ArrayList<Object> presupuestoDetallesSeleccionados = new ArrayList<Object>(); 
	private Map<Long, Long> ordenesFacturadas = new HashMap<Long, Long>();
	
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long, ProductoIf> mapaProductoProveedor = new HashMap<Long, ProductoIf>();
	private Map<Long, GenericoIf> mapaGenerico = new HashMap<Long, GenericoIf>();
	
				
	public FacturacionPlanMedioModel(Dialog owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}

	public FacturacionPlanMedioModel(Frame owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}

	public FacturacionPlanMedioModel(Frame owner, Long idPlanMedio, Map<ProductoClienteIf, 
			ArrayList<Long>> mapaProductosClienteVersionNoCanje, 
			Map<ClienteOficinaIf,ArrayList<PlanMedioFacturacionIf>> listPlanMedioFacturacionCanjeByProveedorMap, 
			Map<ClienteOficinaIf,ArrayList<PlanMedioFacturacionIf>> listPlanMedioFacturacionNoCanjeByProveedorMap, 
			Collection<PlanMedioFormaPagoIf> planMedioFormaPagoColl, 
			Date fechaInicioPlanMedioPedido, 
			Date fechaFinPlanMedioPedido,
			boolean op1,boolean op2,boolean op3, boolean op4, boolean op5, 
			boolean facturaAcliente, boolean reembolso, 
			Map<Long, Long> ordenesFacturadas, 
			Map<Long, ClienteOficinaIf> mapaClienteOficina,	Map<Long, GenericoIf> mapaGenerico, 
			Map<Long, ProductoIf> mapaProductoProveedor){
		super(owner);
		
		this.planMedioId = idPlanMedio;
		this.mapaProductosClienteVersionesNoCanje = mapaProductosClienteVersionNoCanje;
		
		this.listPlanMedioFacturacionCanjeByProveedorMap   = listPlanMedioFacturacionCanjeByProveedorMap;
		this.listPlanMedioFacturacionNoCanjeByProveedorMap = listPlanMedioFacturacionNoCanjeByProveedorMap;
		this.calendarFechaInicioMin.setTime(fechaInicioPlanMedioPedido);
		this.calendarFechaFinMax.setTime(fechaFinPlanMedioPedido);
		this.calendarFechaInicioSeleccionada = this.calendarFechaInicioMin;
		this.calendarFechaFinSeleccionada    = this.calendarFechaFinMax;
		this.planMedioFormaPagoColl = planMedioFormaPagoColl;
		this.ordenesFacturadas = ordenesFacturadas;
		
		this.mapaClienteOficina = mapaClienteOficina;
		this.mapaGenerico = mapaGenerico;
		this.mapaProductoProveedor = mapaProductoProveedor;
		
		if (op5){
			llenarComboPorProductoComercial();
			getRbPorProductoComercial().setSelected(true);
			getCbPeriodo().setEnabled(true);
			getCmbPeridoInicio().setEnabled(false);
			getCmbPeriodoFin().setEnabled(false);
		}else{
			getRbPorProductoComercial().setEnabled(false);
			getCmbPorProductoComercial().setEnabled(false);
			getCbPeriodo().setEnabled(false);
			getCmbPeridoInicio().setEnabled(false);
			getCmbPeriodoFin().setEnabled(false);
		}
				
		if (op4){
			llenarComboPorProveedor();
			getRbPorProveedor().setSelected(true);
			getCbPeriodo().setEnabled(true);
			getCmbPeridoInicio().setEnabled(false);
			getCmbPeriodoFin().setEnabled(false);
		}else{
			getRbPorProveedor().setEnabled(false);
			getCmbPorProveedor().setEnabled(false);
			getCbPeriodo().setEnabled(false);
			getCmbPeridoInicio().setEnabled(false);
			getCmbPeriodoFin().setEnabled(false);
		}
		
		if(op3){
			llenarComboComisiones();
			getRbPorComisionMedio().setSelected(true);
			getCbPeriodo().setEnabled(true);
			getCmbPeridoInicio().setEnabled(false);
			getCmbPeriodoFin().setEnabled(false);
		}else{
			getRbPorComisionMedio().setEnabled(false);
			getCmbPorComisionMedio().setEnabled(false);
			getCbPeriodo().setEnabled(false);
			getCmbPeridoInicio().setEnabled(false);
			getCmbPeriodoFin().setEnabled(false);
		}
		
		if(op2){
			llenarComboVersiones();
			getRbPorVersion().setSelected(true);
			getCbPeriodo().setEnabled(true);
			getCmbPeridoInicio().setEnabled(false);
			getCmbPeriodoFin().setEnabled(false);
		}else{
			getRbPorVersion().setEnabled(false);
			getCmbPorVersion().setEnabled(false);
			getCbPeriodo().setEnabled(false);
			getCmbPeridoInicio().setEnabled(false);
			getCmbPeriodoFin().setEnabled(false);
		}
		
		if(!op1){
			getRbCompleto().setEnabled(false);	
			getCbPeriodo().setEnabled(true);
			getCmbPeridoInicio().setEnabled(false);
			getCmbPeriodoFin().setEnabled(false);
		
		}else{
			getRbCompleto().setSelected(true);
			getCbPeriodo().setEnabled(false);
			getCmbPeridoInicio().setEnabled(false);
			getCmbPeriodoFin().setEnabled(false);
		}
		
		if(!facturaAcliente){
			getRbCompleto().setEnabled(true);	
		}
		
		if (reembolso) {
			getRbPorProveedor().setEnabled(false);
			getCmbPorProveedor().setEnabled(false);
			getRbPorProductoComercial().setEnabled(false);
			getCmbPorProductoComercial().setEnabled(false);
			getRbPorVersion().setEnabled(false);
			getCmbPorVersion().setEnabled(false);
			getRbPorComisionMedio().setEnabled(false);
			getCmbPorComisionMedio().setEnabled(false);
			getCbPeriodo().setEnabled(false);
			getCmbPeridoInicio().setEnabled(false);
			getCmbPeriodoFin().setEnabled(false);
		}
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		initKeyListeners();
		initListeners();
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setVisible(true);
	}
		
	private void initListeners() {
		getRbCompleto().addActionListener(oActionListenerRbCompleto);
		getRbPorVersion().addActionListener(oActionListenerRbPorComercial);
		getRbPorComisionMedio().addActionListener(oActionListenerRbPorComisionMedio);
		getRbPorProveedor().addActionListener(oActionListenerRbPorCanal);
		getRbCompleto().addActionListener(oActionListenerRbCompleto);
		getCbPeriodo().addActionListener(oActionListenerCbPeriodo);
		getCmbPeridoInicio().addActionListener(oActionListenerCmbPeriodoInicio);
		getCmbPeriodoFin().addActionListener(oActionListenerCmbPeriodoFin);
		
	}
	
	private void llenarComboPorProveedor(){
		try{
			Iterator listPlanMedioFacturacionNoCanjeByProveedorMapIt = listPlanMedioFacturacionNoCanjeByProveedorMap.keySet().iterator();
			while(listPlanMedioFacturacionNoCanjeByProveedorMapIt.hasNext()){
				ClienteOficinaIf clienteOficinaIf = (ClienteOficinaIf)listPlanMedioFacturacionNoCanjeByProveedorMapIt.next();
				String item = clienteOficinaIf.getDescripcion();
				getCmbPorProveedor().addItem(item);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
   		
	private void llenarComboVersiones(){
		try{
			for(ProductoClienteIf productoClienteIf : mapaProductosClienteVersionesNoCanje.keySet() ){
				ArrayList<Long> versionList = mapaProductosClienteVersionesNoCanje.get(productoClienteIf);
				for(Long versionId : versionList){				
					CampanaProductoVersionIf version = SessionServiceLocator.getCampanaProductoVersionSessionService().getCampanaProductoVersion(versionId);
					getCmbPorVersion().addItem(version);					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void llenarComboPorProductoComercial(){
		try{
			//lleno un mapa con los productos comerciales, el mapa filtra los repetidos
			LinkedMap productosClienteMap = new LinkedMap();
			for(ProductoClienteIf productoClienteIf : mapaProductosClienteVersionesNoCanje.keySet() ){
				ArrayList<Long> versionList = mapaProductosClienteVersionesNoCanje.get(productoClienteIf);
				for(Long versionId : versionList){
					CampanaProductoVersionIf version = SessionServiceLocator.getCampanaProductoVersionSessionService().getCampanaProductoVersion(versionId);
					CampanaProductoIf campanaProducto = SessionServiceLocator.getCampanaProductoSessionService().getCampanaProducto(version.getCampanaProductoId());
					ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(campanaProducto.getProductoClienteId());
					productosClienteMap.put(productoCliente.getId(), productoCliente);					
				}
			}
			//recorro el mapa para llenar el combo con los productos comerciales
			Iterator productosClienteMapIt = productosClienteMap.keySet().iterator();
			while(productosClienteMapIt.hasNext()){
				Long productoClienteId = (Long)productosClienteMapIt.next();
				ProductoClienteIf productoCliente = (ProductoClienteIf)productosClienteMap.get(productoClienteId);
				getCmbPorProductoComercial().addItem(productoCliente);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//llena el combo de comerciales con PRODUCTO CLIENTE
	private void llenarComboComerciales(){
		try{
			for(ProductoClienteIf productoClienteIf: listProductosClienteNoCanje ){
				Collection comercialColl = SessionServiceLocator.getComercialSessionService().findComercialByProductoClienteId(productoClienteIf.getId());
				Iterator comercialCollIt = comercialColl.iterator();
				ComercialIf comercialIf = new ComercialData();
				
				while(comercialCollIt.hasNext()){
					comercialIf = (ComercialIf)comercialCollIt.next();
				}
				
				String item = comercialIf.getNombre()+" ("+comercialIf.getVersion()+")";//+","+derechoProgramaIf.getNombre()+")";//-"+comercialIt.getVersion()+"-";
				getCmbPorVersion().addItem(item);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void llenarComboComisiones(){
		try{
			Iterator listPlanMedioFacturacionCanjeByProveedorMapIt = listPlanMedioFacturacionCanjeByProveedorMap.keySet().iterator();
			while(listPlanMedioFacturacionCanjeByProveedorMapIt.hasNext()){
				ClienteOficinaIf clienteOficinaIf = (ClienteOficinaIf)listPlanMedioFacturacionCanjeByProveedorMapIt.next();
				String item = clienteOficinaIf.getDescripcion();
				getCmbPorComisionMedio().addItem(item);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void llenarComboComisiones2(){
		try{
			Iterator listPlanMedioFacturacionCanjeMapIt = listPlanMedioFacturacionCanjeMap.keySet().iterator();
			while(listPlanMedioFacturacionCanjeMapIt.hasNext()){
				ComercialIf comercialIf = (ComercialIf)listPlanMedioFacturacionCanjeMapIt.next();
				ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacion = listPlanMedioFacturacionCanjeMap.get(comercialIf);
				Long derechoId = comercialIf.getDerechoprogramaId();
				Collection derechoProgramaColl = SessionServiceLocator.getDerechoProgramaSessionService().findDerechoProgramaById(derechoId);
				Iterator derechoProgramaCollIt = derechoProgramaColl.iterator(); 
				DerechoProgramaIf derechoProgramaIf = (DerechoProgramaIf)derechoProgramaCollIt.next();
				
				for(PlanMedioFacturacionIf planMedioFacturacionIf:listPlanMedioFacturacion){
					Long idProveedor = planMedioFacturacionIf.getProveedorId();
					Collection proveedorColl = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaById(idProveedor);
					Iterator proveedorCollIt = proveedorColl.iterator();
					String item = "";
					while(proveedorCollIt.hasNext()){
						ClienteOficinaIf proveedor = (ClienteOficinaIf)proveedorCollIt.next();
						item = proveedor.getDescripcion() + " " +comercialIf.getNombre()+" ("+derechoProgramaIf.getNombre()+") ";
					}
					getCmbPorComisionMedio().addItem(item);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void initKeyListeners() {
		getBtnAceptar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					aceptar();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	
	ActionListener oActionListenerRbPorComercial = new ActionListener(){
		public void actionPerformed(ActionEvent evento){
			if (getRbPorVersion().isSelected()){
				getCbPeriodo().setEnabled(true);
			}else{
				getCbPeriodo().setEnabled(false);
			}
			
		}
	};
	
	ActionListener oActionListenerRbPorComisionMedio = new ActionListener(){
		public void actionPerformed(ActionEvent evento){
			if (getRbPorComisionMedio().isSelected()){
				getCbPeriodo().setEnabled(true);
			}else{
				getCbPeriodo().setEnabled(false);
			}
			
		}
	};
	
	ActionListener oActionListenerRbPorCanal = new ActionListener(){
		public void actionPerformed(ActionEvent evento){
			if (getRbPorProveedor().isSelected()){
				getCbPeriodo().setEnabled(true);
			}else{
				getCbPeriodo().setEnabled(false);
			}
			
		}
	};
	
	ActionListener oActionListenerRbCompleto = new ActionListener(){
		public void actionPerformed(ActionEvent evento){
			if (getRbCompleto().isSelected()){
				getCbPeriodo().setEnabled(false);
			}else{
				getCbPeriodo().setEnabled(true);
			}
			
		}
	};
	
	ActionListener oActionListenerCbPeriodo = new ActionListener(){
		public void actionPerformed(ActionEvent evento){
			if (getCbPeriodo().isSelected()){
				checkPeriodoSelected = true;
				getCmbPeridoInicio().setEnabled(true);
				getCmbPeriodoFin().setEnabled(true);
				getCmbPeridoInicio().getDateModel().setMinDate(calendarFechaInicioMin);
				getCmbPeriodoFin().getDateModel().setMinDate(calendarFechaInicioMin);
				getCmbPeridoInicio().getDateModel().setMaxDate(calendarFechaFinMax);
				getCmbPeriodoFin().getDateModel().setMaxDate(calendarFechaFinMax);
				getCmbPeridoInicio().setCalendar(calendarFechaInicioMin);
				getCmbPeriodoFin().setCalendar(calendarFechaFinMax);
				getCmbPeridoInicio().setCalendar(calendarFechaInicioSeleccionada);
				getCmbPeriodoFin().setCalendar(calendarFechaFinSeleccionada);
			}else{
				checkPeriodoSelected = false;
				getCmbPeridoInicio().setCalendar(null);
				getCmbPeriodoFin().setCalendar(null);
				getCmbPeridoInicio().setEnabled(false);
				getCmbPeriodoFin().setEnabled(false);
			}
		}
	};
	
	ActionListener oActionListenerCmbPeriodoInicio = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if(getCmbPeridoInicio().getCalendar() != null){
				calendarFechaInicioSeleccionada = getCmbPeridoInicio().getCalendar();
				
				if(calendarFechaInicioSeleccionada.after(calendarFechaFinSeleccionada)){
					getCmbPeriodoFin().setCalendar(calendarFechaInicioSeleccionada);
				}
			
			}
		}
	};
	
	ActionListener oActionListenerCmbPeriodoFin = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if(getCmbPeriodoFin().getCalendar() != null){
				calendarFechaFinSeleccionada = getCmbPeriodoFin().getCalendar();
				
				if(calendarFechaFinSeleccionada.before(calendarFechaInicioSeleccionada)){
					getCmbPeridoInicio().setCalendar(calendarFechaFinSeleccionada);
				}
				
			}
		}
	};
	
	private Map<ProductoClienteIf,Long> getProductoClienteVersionOfMapaNoCanjeByProductoClienteId(Long idProductoCliente,Long versionProductoClienteId ){
		Map<ProductoClienteIf,Long> mapaProductoClienteVersion = null;
		
		Iterator mapaProductoClienteVersionIt = mapaProductosClienteVersionesNoCanje.keySet().iterator();
		while(mapaProductoClienteVersionIt.hasNext()){
			ProductoClienteIf productoClienteIfTemp = (ProductoClienteIf) mapaProductoClienteVersionIt.next();
			ArrayList<Long> listVersiones = mapaProductosClienteVersionesNoCanje.get(productoClienteIfTemp);
			for (Long versionTempId : listVersiones){
				if (productoClienteIfTemp.getId().compareTo(idProductoCliente) == 0 &&
					versionTempId.compareTo(versionProductoClienteId) == 0 ){
					mapaProductoClienteVersion = new LinkedHashMap<ProductoClienteIf, Long>();
					mapaProductoClienteVersion.put(productoClienteIfTemp, versionProductoClienteId);
				}
			}
		}
		
		return mapaProductoClienteVersion;
	}
	
	private void aceptar()throws CloneNotSupportedException{		
		try {
			if(validateFields()){
				
				if(getRbCompleto().isSelected()){
					this.dispose();
				}
				
				else if(getRbParcial().isSelected()){
					FacturacionParcialModel jdFacturacionParcialModel = new FacturacionParcialModel(Parametros.getMainFrame(), "M", planMedioId, ordenesFacturadas, mapaClienteOficina, mapaGenerico, mapaProductoProveedor);
					setPresupuestoDetallesSeleccionados(jdFacturacionParcialModel.getPresupuestoDetallesSeleccionados());
					this.dispose();
				}
				
				else if(getRbPorProveedor().isSelected()){
					positionEscogida = getCmbPorProveedor().getSelectedIndex();
					if (getCbPeriodo().isSelected()){
						int validoPeriodo = validarPeriodo();
						int validoPeriodoPorCanal = validarPeriodoPorCanal();
						
						if (validoPeriodo == 0 && validoPeriodoPorCanal == 0){
							periodoFechaInicio = calendarFechaInicioSeleccionada.getTime();
							periodoFechaFin    = calendarFechaFinSeleccionada.getTime();
							this.dispose();
						}						
						else if (validoPeriodo == -1)
							SpiritAlert.createAlert("Periodo no corresponden al mismo mes", SpiritAlert.WARNING);
						else if (validoPeriodo == 1)
							SpiritAlert.createAlert("Periodo mayor a un mes", SpiritAlert.WARNING);
						else if (validoPeriodoPorCanal == 1)
							SpiritAlert.createAlert("El canal ya tiene pedido(s) con fechas que contiene al Periodo seleccionado", SpiritAlert.WARNING);
					
					} else {
						int validoPeriodoPorCanal = validarPeriodoPorCanal();
						if(validoPeriodoPorCanal == 0){
							periodoFechaInicio = calendarFechaInicioSeleccionada.getTime();
							periodoFechaFin    = calendarFechaFinSeleccionada.getTime();
							this.dispose();
						}
						else if (validoPeriodoPorCanal == -1)
							SpiritAlert.createAlert("El canal ya tiene pedido(s) con periodo, debe seleecionar un nuevo periodo", SpiritAlert.WARNING);
					}
					
				}else if(getRbPorProductoComercial().isSelected()){
					ProductoClienteIf productoElegido = (ProductoClienteIf) getCmbPorProductoComercial().getSelectedItem();
					idProductoElegido = productoElegido.getId(); 
					
					if (getCbPeriodo().isSelected()){
						int validoPeriodo = validarPeriodo();
						int validoPeriodoPorProductoComercial = validarPeriodoPorProductoComercial();
						
						if (validoPeriodo == 0 && validoPeriodoPorProductoComercial == 0){
							periodoFechaInicio = calendarFechaInicioSeleccionada.getTime();
							periodoFechaFin    = calendarFechaFinSeleccionada.getTime();
							this.dispose();
						}						
						else if (validoPeriodo == -1)
							SpiritAlert.createAlert("Periodo no corresponden al mismo mes", SpiritAlert.WARNING);
						else if (validoPeriodo == 1)
							SpiritAlert.createAlert("Periodo mayor a un mes", SpiritAlert.WARNING);
						else if (validoPeriodoPorProductoComercial == 1)
							SpiritAlert.createAlert("El Producto Comercial ya tiene pedido(s) con fechas que contiene al Periodo seleccionado", SpiritAlert.WARNING);
					} else {
						int validoPeriodoPorProductoComercial = validarPeriodoPorProductoComercial();
						
						if ( validoPeriodoPorProductoComercial == 0){
								periodoFechaInicio = calendarFechaInicioSeleccionada.getTime();
								periodoFechaFin    = calendarFechaFinSeleccionada.getTime();
								this.dispose();
							}
						
						else if (validoPeriodoPorProductoComercial == -1)
							SpiritAlert.createAlert("El Producto Comercial ya tiene pedido(s) con periodo, debe seleecionar un nuevo periodo", SpiritAlert.WARNING);
					}
				}
				
				else if(getRbPorVersion().isSelected()){
					CampanaProductoVersionIf campanaProductoVersionIf = (CampanaProductoVersionIf) getCmbPorVersion().getSelectedItem();
					CampanaProductoIf campanaProductoIf = SessionServiceLocator.getCampanaProductoSessionService().getCampanaProducto(campanaProductoVersionIf.getCampanaProductoId());
					Map<ProductoClienteIf,Long> productoVersionElegido = getProductoClienteVersionOfMapaNoCanjeByProductoClienteId(campanaProductoIf.getProductoClienteId(),campanaProductoVersionIf.getId());
					ProductoClienteIf productoElegido = productoVersionElegido.keySet().iterator().next();
					Long versionElegidaId = productoVersionElegido.get(productoElegido);
					
					idProductoElegido = productoElegido.getId(); 
					versionProductoElegidoId = versionElegidaId;
					
					if (getCbPeriodo().isSelected()){
						int validoPeriodo = validarPeriodo();
						int validoPeriodoPorProductoClienteVersion = validarPeriodoPorVersion();
						
						if (validoPeriodo == 0 && validoPeriodoPorProductoClienteVersion == 0){
							periodoFechaInicio = calendarFechaInicioSeleccionada.getTime();
							periodoFechaFin    = calendarFechaFinSeleccionada.getTime();
							this.dispose();
						}						
						else if (validoPeriodo == -1)
							SpiritAlert.createAlert("Periodo no corresponden al mismo mes", SpiritAlert.WARNING);
						else if (validoPeriodo == 1)
							SpiritAlert.createAlert("Periodo mayor a un mes", SpiritAlert.WARNING);
						else if (validoPeriodoPorProductoClienteVersion == 1)
							SpiritAlert.createAlert("La Versión ya tiene pedido(s) con fechas que contiene al Periodo seleccionado", SpiritAlert.WARNING);
					} else {
						int validoPeriodoPorProductoClienteVersion = validarPeriodoPorVersion();
						
						if ( validoPeriodoPorProductoClienteVersion == 0){
								periodoFechaInicio = calendarFechaInicioSeleccionada.getTime();
								periodoFechaFin    = calendarFechaFinSeleccionada.getTime();
								this.dispose();
							}
						
						else if (validoPeriodoPorProductoClienteVersion == -1)
							SpiritAlert.createAlert("La Versión ya tiene pedido(s) con periodo, debe seleecionar un nuevo periodo", SpiritAlert.WARNING);
					}
					
				}else if(getRbPorComisionMedio().isSelected()){
					positionEscogida = getCmbPorComisionMedio().getSelectedIndex();
					
					if (getCbPeriodo().isSelected()){
						int validoPeriodo = validarPeriodo();
						int validoPeriodoPorComisionMedio = validarPeriodoPorComisionMedio();
						
						if (validoPeriodo == 0 && validoPeriodoPorComisionMedio == 0){
							periodoFechaInicio = calendarFechaInicioSeleccionada.getTime();
							periodoFechaFin    = calendarFechaFinSeleccionada.getTime();
							this.dispose();
						}						
						else if (validoPeriodo == -1)
							SpiritAlert.createAlert("Periodo no corresponden al mismo mes", SpiritAlert.WARNING);
						else if (validoPeriodo == 1)
							SpiritAlert.createAlert("Periodo mayor a un mes", SpiritAlert.WARNING);
						else if (validoPeriodoPorComisionMedio == 1)
							SpiritAlert.createAlert("La comisión ya tiene pedido(s) con fechas que contiene al Periodo seleccionado", SpiritAlert.WARNING);
					
					} else {
						int validoPeriodoPorComisionMedio = validarPeriodoPorComisionMedio();
						
						if(validoPeriodoPorComisionMedio == 0){
							periodoFechaInicio = calendarFechaInicioSeleccionada.getTime();
							periodoFechaFin    = calendarFechaFinSeleccionada.getTime();
							this.dispose();
						}
						
						else if (validoPeriodoPorComisionMedio == -1)
							SpiritAlert.createAlert("La comisión ya tiene pedido(s) con periodo, debe seleecionar un nuevo periodo", SpiritAlert.WARNING);
					}					
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}	
	}
		
	/*
	 * return  0  periodo es valido, el periodo (periodo de facturacion minimo de un mes y dentro del mismo mes)
	 * return -1  periodo no valido, el periodo de facturacion no es dentro del mismo mes  
	 * return  1  periodo no valido, el periodo de facturacion es mayor a un mes 
	 * */
	private int validarPeriodo(){
		
		int month1 = calendarFechaInicioSeleccionada.get(Calendar.MONTH);
		int month2 = calendarFechaFinSeleccionada.get(Calendar.MONTH);
		
		Calendar calendarAMonth = calendarFechaInicioSeleccionada;
		//ADD 21 ABRIL
		
		int dia  = calendarAMonth.get(Calendar.DATE);
		int mes  = calendarAMonth.get(Calendar.MONTH);
		int anio = calendarAMonth.get(Calendar.YEAR);
		
		Calendar calendarHelp = GregorianCalendar.getInstance();
	   	
		//no haya conflictos con los datos locales y solo se riga al dia, mes y anio
		//calendarHelp.setTime(periodoFechaInicio);
		calendarHelp.set(Calendar.DATE, dia);
		calendarHelp.set(Calendar.MONTH, mes);
		calendarHelp.set(Calendar.YEAR, anio);
	    
		//END 21 ABRIL
		//calendarAMonth.add(Calendar.MONTH,1);
		calendarHelp.add(Calendar.MONTH,1);
		//int op =calendarFechaFinSeleccionada.compareTo(calendarAMonth);
		int op =calendarFechaFinSeleccionada.compareTo(calendarHelp);
		
		//int month1 = calendarFechaInicioSeleccionada.get(Calendar.MONTH);
		//int month2 = calendarFechaFinSeleccionada.get(Calendar.MONTH);
		
		if (op == 0){
			return month1 == month2 ? 0 : -1;}
		else if (op < 0){
			return month1 == month2 ? 0 : -1;}
		else return 1;
	}
	
	
	//ADD 8 SEPTIEMBRE
	/*
	 * return  0  periodo x Producto Cliente Version es valido
	 * return -1  periodo no valido, el Producto Cliente Version ya tiene pedido con periodo ahora debe activar el checkbox y seleccionar un periodo correcto
	 * return  1  periodo no valido, el Producto Cliente Version ya tiene pedido con periodo que contiene a las fechas seleccionadas
	 * */
	private int validarPeriodoPorVersion(){
			
		int op = 0;
				
		if (this.planMedioFormaPagoColl.size() > 0){
			Iterator planMedioFormaPagoIfCollIt = this.planMedioFormaPagoColl.iterator();
			while(planMedioFormaPagoIfCollIt.hasNext()){
				PlanMedioFormaPagoIf planMedioFormaPagoTmp = (PlanMedioFormaPagoIf)planMedioFormaPagoIfCollIt.next();
				
				if (idProductoElegido.compareTo(planMedioFormaPagoTmp.getFormaPagoId()) == 0 &&
					versionProductoElegidoId.compareTo(planMedioFormaPagoTmp.getFormaPagoCampanaProductoVersionId()) == 0 && 
					planMedioFormaPagoTmp.getTipoFormaPago().compareTo("V") == 0){ 
					if(!getCbPeriodo().isSelected())
						op = -1;
					else{
						Calendar fechaInicioTemp = GregorianCalendar.getInstance();
						Calendar fechaFinTemp    = GregorianCalendar.getInstance();
						
						fechaInicioTemp.setTime(new Date(planMedioFormaPagoTmp.getFechaInicio().getTime()));
						fechaFinTemp.setTime(new Date(planMedioFormaPagoTmp.getFechaFin().getTime()));
																	
						if (calendarFechaInicioSeleccionada.compareTo(fechaInicioTemp) == 0 || calendarFechaFinSeleccionada.compareTo(fechaFinTemp) == 0 ||	
							calendarFechaInicioSeleccionada.compareTo(fechaFinTemp) == 0 || calendarFechaFinSeleccionada.compareTo(fechaInicioTemp) == 0 ||	
							(calendarFechaInicioSeleccionada.before(fechaInicioTemp) && calendarFechaFinSeleccionada.after(fechaFinTemp))  ||
						    (calendarFechaInicioSeleccionada.after(fechaInicioTemp)  && calendarFechaFinSeleccionada.before(fechaFinTemp)) ||
						    (calendarFechaInicioSeleccionada.after(fechaInicioTemp)  && calendarFechaInicioSeleccionada.before(fechaFinTemp) && calendarFechaFinSeleccionada.after(fechaFinTemp)) ||
							(calendarFechaInicioSeleccionada.before(fechaInicioTemp) && calendarFechaFinSeleccionada.after(fechaInicioTemp)  && calendarFechaFinSeleccionada.before(fechaFinTemp)))
							op = 1;
					}
				}
			}
		}
		return op;
	}
		
	/*
	 * return  0  periodo x Comercial es valido
	 * return -1  periodo no valido, el comercial ya tiene pedido con periodo ahora debe activar el checkbox y seleccionar un periodo correcto
	 * return  1  periodo no valido, el comercial ya tiene pedido con periodo que contiene a las fechas seleccionadas
	 * */
	private int validarPeriodoPorProductoComercial(){
			
		int op = 0;
				
		if (this.planMedioFormaPagoColl.size() > 0){
			Iterator planMedioFormaPagoIfCollIt = this.planMedioFormaPagoColl.iterator();
			while(planMedioFormaPagoIfCollIt.hasNext()){
				PlanMedioFormaPagoIf planMedioFormaPagoTmp = (PlanMedioFormaPagoIf)planMedioFormaPagoIfCollIt.next();
				
				if (idProductoElegido.compareTo(planMedioFormaPagoTmp.getFormaPagoId()) == 0 &&
				    planMedioFormaPagoTmp.getTipoFormaPago().compareTo("P") == 0){ 
					if(!getCbPeriodo().isSelected())
						op = -1;
					else{
						Calendar fechaInicioTemp = GregorianCalendar.getInstance();
						Calendar fechaFinTemp    = GregorianCalendar.getInstance();
						
						fechaInicioTemp.setTime(new Date(planMedioFormaPagoTmp.getFechaInicio().getTime()));
						fechaFinTemp.setTime(new Date(planMedioFormaPagoTmp.getFechaFin().getTime()));
																	
						if (calendarFechaInicioSeleccionada.compareTo(fechaInicioTemp) == 0 || calendarFechaFinSeleccionada.compareTo(fechaFinTemp) == 0 ||	
							calendarFechaInicioSeleccionada.compareTo(fechaFinTemp) == 0 || calendarFechaFinSeleccionada.compareTo(fechaInicioTemp) == 0 ||	
							(calendarFechaInicioSeleccionada.before(fechaInicioTemp) && calendarFechaFinSeleccionada.after(fechaFinTemp))  ||
						    (calendarFechaInicioSeleccionada.after(fechaInicioTemp)  && calendarFechaFinSeleccionada.before(fechaFinTemp)) ||
						    (calendarFechaInicioSeleccionada.after(fechaInicioTemp)  && calendarFechaInicioSeleccionada.before(fechaFinTemp) && calendarFechaFinSeleccionada.after(fechaFinTemp)) ||
							(calendarFechaInicioSeleccionada.before(fechaInicioTemp) && calendarFechaFinSeleccionada.after(fechaInicioTemp)  && calendarFechaFinSeleccionada.before(fechaFinTemp)))
							op = 1;
					}
				}
			}
		}
		return op;
	}
	
	/*
	 * Se obtiene el id del Proveedor seleccionado en Comisiones del Medio
	 */
	public Long getIdProveedorPorComisionMedio(){
		Long id = null;
		int cont = 0;
		Iterator planMedioFacturacionCanjeByProveedorMapIt = this.listPlanMedioFacturacionCanjeByProveedorMap.keySet().iterator();
	    //Se obtien la lista de planes de medio de facturacion con canje del proveedor seleccionado
		while(planMedioFacturacionCanjeByProveedorMapIt.hasNext()){
			ClienteOficinaIf clienteOficinaIf2 = (ClienteOficinaIf)planMedioFacturacionCanjeByProveedorMapIt.next();
			if(positionEscogida == cont)
				return clienteOficinaIf2.getId();
			cont++;
		}
		return id;
	}
	
	/*
	 * return  0  periodo x Comision del Medio es valido
	 * return -1  periodo no valido, la Comision del Medio ya tiene pedido con periodo ahora debe activar el checkbox y seleccionar un periodo correcto
	 * return  1  periodo no valido, la Comision del Medio ya tiene pedido con periodo que contiene a las fechas seleccionadas
	 * */
	private int validarPeriodoPorComisionMedio(){
			
		int op = 0;
				
		if (this.planMedioFormaPagoColl.size() > 0){
			Iterator planMedioFormaPagoIfCollIt = this.planMedioFormaPagoColl.iterator();
			//Se obtiene el id del Proveedor seleccionado en Comisiones del Medio
			Long idProveedorElegido = getIdProveedorPorComisionMedio();
			while(planMedioFormaPagoIfCollIt.hasNext()){
				PlanMedioFormaPagoIf planMedioFormaPagoTmp = (PlanMedioFormaPagoIf)planMedioFormaPagoIfCollIt.next();
				if (idProveedorElegido.compareTo(planMedioFormaPagoTmp.getFormaPagoId()) == 0 && 
					    planMedioFormaPagoTmp.getTipoFormaPago().compareTo("M") == 0){
					if(!getCbPeriodo().isSelected())
						op = -1;
					else{
						Calendar fechaInicioTemp = GregorianCalendar.getInstance();
						Calendar fechaFinTemp    = GregorianCalendar.getInstance();
						
						fechaInicioTemp.setTime(new Date(planMedioFormaPagoTmp.getFechaInicio().getTime()));
						fechaFinTemp.setTime(new Date(planMedioFormaPagoTmp.getFechaFin().getTime()));
												
						if (calendarFechaInicioSeleccionada.compareTo(fechaInicioTemp) == 0 || calendarFechaFinSeleccionada.compareTo(fechaFinTemp) == 0 ||	
							calendarFechaInicioSeleccionada.compareTo(fechaFinTemp) == 0 || calendarFechaFinSeleccionada.compareTo(fechaInicioTemp) == 0 ||		
							(calendarFechaInicioSeleccionada.before(fechaInicioTemp) && calendarFechaFinSeleccionada.after(fechaFinTemp))  ||
						    (calendarFechaInicioSeleccionada.after(fechaInicioTemp)  && calendarFechaFinSeleccionada.before(fechaFinTemp)) ||
						    (calendarFechaInicioSeleccionada.after(fechaInicioTemp)  && calendarFechaInicioSeleccionada.before(fechaFinTemp) && calendarFechaFinSeleccionada.after(fechaFinTemp)) ||
							(calendarFechaInicioSeleccionada.before(fechaInicioTemp) && calendarFechaFinSeleccionada.after(fechaInicioTemp)  && calendarFechaFinSeleccionada.before(fechaFinTemp)))
							op = 1;
					}
				}
			}
		}
		return op;
	}
	
	/*
	 * Se obtiene el id del Proveedor seleccionado en Comisiones del Medio
	 */
	public Long getIdProveedorPorCanal(){
		Long id = null;
		int cont = 0;
		Iterator planMedioFacturacionNoCanjeByProveedorMapIt = this.listPlanMedioFacturacionNoCanjeByProveedorMap.keySet().iterator();
	    //Se obtien la lista de planes de medio de facturacion con canje del proveedor seleccionado
		while(planMedioFacturacionNoCanjeByProveedorMapIt.hasNext()){
			ClienteOficinaIf clienteOficinaIf2 = (ClienteOficinaIf)planMedioFacturacionNoCanjeByProveedorMapIt.next();
			if(positionEscogida == cont)
				return clienteOficinaIf2.getId();
			cont++;
		}
		return id;
	}
	
	/*
	 * return  0  periodo x Canal es valido
	 * return -1  periodo no valido, el Canal ya tiene pedido con periodo ahora debe activar el checkbox y seleccionar un periodo correcto
	 * return  1  periodo no valido, el Canal ya tiene pedido con periodo que contiene a las fechas seleccionadas
	 * */
	private int validarPeriodoPorCanal(){
			
		int op = 0;
				
		if (this.planMedioFormaPagoColl.size() > 0){
			Iterator planMedioFormaPagoIfCollIt = this.planMedioFormaPagoColl.iterator();
			//Se obtiene el id del Proveedor seleccionado en Canal
			Long idProveedorElegido = getIdProveedorPorCanal();
			while(planMedioFormaPagoIfCollIt.hasNext()){
				PlanMedioFormaPagoIf planMedioFormaPagoTmp = (PlanMedioFormaPagoIf)planMedioFormaPagoIfCollIt.next();
				if (idProveedorElegido.compareTo(planMedioFormaPagoTmp.getFormaPagoId()) == 0&& 
					    planMedioFormaPagoTmp.getTipoFormaPago().compareTo("L") == 0){
					if(!getCbPeriodo().isSelected())
						op = -1;
					else{
						Calendar fechaInicioTemp = GregorianCalendar.getInstance();
						Calendar fechaFinTemp    = GregorianCalendar.getInstance();
						
						fechaInicioTemp.setTime(new Date(planMedioFormaPagoTmp.getFechaInicio().getTime()));
						fechaFinTemp.setTime(new Date(planMedioFormaPagoTmp.getFechaFin().getTime()));
						
						if (calendarFechaInicioSeleccionada.compareTo(fechaInicioTemp) == 0 || calendarFechaFinSeleccionada.compareTo(fechaFinTemp) == 0 ||	
							calendarFechaInicioSeleccionada.compareTo(fechaFinTemp) == 0 || calendarFechaFinSeleccionada.compareTo(fechaInicioTemp) == 0 ||		
							(calendarFechaInicioSeleccionada.before(fechaInicioTemp) && calendarFechaFinSeleccionada.after(fechaFinTemp))  ||
						    (calendarFechaInicioSeleccionada.after(fechaInicioTemp)  && calendarFechaFinSeleccionada.before(fechaFinTemp)) ||
						    (calendarFechaInicioSeleccionada.after(fechaInicioTemp)  && calendarFechaInicioSeleccionada.before(fechaFinTemp) && calendarFechaFinSeleccionada.after(fechaFinTemp)) ||
							(calendarFechaInicioSeleccionada.before(fechaInicioTemp) && calendarFechaFinSeleccionada.after(fechaInicioTemp)  && calendarFechaFinSeleccionada.before(fechaFinTemp)))
							op = 1;
					}
				}
			}
		}
		return op;
	}
	
	
	private boolean validateFields(){
		if(getRbPorVersion().isSelected() && getCmbPorVersion().getSelectedIndex()==-1){
			SpiritAlert.createAlert("Debe seleccionar una versión.", SpiritAlert.WARNING);
			return false;
		}else if(!getRbCompleto().isSelected() && !getRbParcial().isSelected() && !getRbPorProveedor().isSelected() && !getRbPorProductoComercial().isSelected() && !getRbPorVersion().isSelected() && !getRbPorComisionMedio().isSelected()){
			SpiritAlert.createAlert("Debe seleccionar una forma de facturación.", SpiritAlert.WARNING);
			return false;
		}
		return true;
	}

	public ArrayList<CampanaProductoVersionIf> getListaCampanaProductoVersion() {
		return listaCampanaProductoVersion;
	}

	public void setListaCampanaProductoVersion(ArrayList<CampanaProductoVersionIf> listaCampanaProductoVersion) {
		this.listaCampanaProductoVersion = listaCampanaProductoVersion;
	}

	public Long getIdProductoElegido() {
		return idProductoElegido;
	}

	public void setIdProductoElegido(Long idProductoElegido) {
		this.idProductoElegido = idProductoElegido;
	}

	public int getPositionEscogida() {
		return positionEscogida;
	}

	public void setPositionEscogida(int positionEscogida) {
		this.positionEscogida = positionEscogida;
	}

	public Date getPeriodoFechaInicio() {
		return periodoFechaInicio;
	}

	public void setPeriodoFechaInicio(Date periodoFechaInicio) {
		this.periodoFechaInicio = periodoFechaInicio;
	}

	public Date getPeriodoFechaFin() {
		return periodoFechaFin;
	}

	public void setPeriodoFechaFin(Date periodoFechaFin) {
		this.periodoFechaFin = periodoFechaFin;
	}

	public boolean isCheckPeriodoSelected() {
		return checkPeriodoSelected;
	}

	public Long getVersionProductoElegidoId() {
		return versionProductoElegidoId;
	}

	public void setVersionProductoElegidoId(Long versionProductoElegidoId) {
		this.versionProductoElegidoId = versionProductoElegidoId;
	}
	
	public ArrayList<Object> getPresupuestoDetallesSeleccionados() {
		return presupuestoDetallesSeleccionados;
	}

	public void setPresupuestoDetallesSeleccionados(
			ArrayList<Object> presupuestoDetallesSeleccionados) {
		this.presupuestoDetallesSeleccionados = presupuestoDetallesSeleccionados;
	}
}
