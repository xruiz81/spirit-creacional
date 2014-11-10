package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
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

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.spirit.cartera.entity.NotaCreditoDetalleIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
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
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.ComercialIf;
import com.spirit.medios.entity.DerechoProgramaIf;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.gui.criteria.ProductoClienteCriteria;
import com.spirit.medios.gui.panel.JPInversionPlanMedios;
import com.spirit.medios.gui.reporteData.InversionClienteMesData;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.DeepCopy;
import com.spirit.util.Utilitarios;

public class InversionPlanMediosModel extends JPInversionPlanMedios{

	private static final long serialVersionUID = -2629417435947102896L;

	private static final String TODOS = "TODOS";
	private static final String CODIGO_TIPO_CLIENTE   = "CL";
	private static final String CODIGO_TIPO_PROVEEDOR = "PR";
	private static final String TIPO_PROVEEDOR_MEDIOS = "M";
	
	private static final String AGRUPAR_POR_CLIENTE_PRODUCTO_TMEDIOS_MEDIOS = "CPTM";
	private static final String AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO = "CTMP";
	private static final String AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO = "TMCP";
	
	private static final String NOMBRE_AGRUPAR_POR_CLIENTE_PRODUCTO_TMEDIOS_MEDIOS = "Cliente / Producto / T-Medios / Medios";
	private static final String NOMBRE_AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO = "Cliente / T-Medios / Medios / Producto";
	private static final String NOMBRE_AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO = "T-Medios / Medios / Cliente / Producto";
		
	private String agruparBy = AGRUPAR_POR_CLIENTE_PRODUCTO_TMEDIOS_MEDIOS;
	
	private Calendar calendarFechaInicio = new GregorianCalendar();
	private Calendar calendarFechaFin    = new GregorianCalendar();
	private Calendar calendarFechaInicioMin = GregorianCalendar.getInstance();
	private Calendar calendarFechaFinMax = GregorianCalendar.getInstance();
	private Date fechaInicio = calendarFechaInicio.getTime();
	private Date fechaFin = calendarFechaFin.getTime();
	
	private ClienteCriteria clienteCriteria;
	private ClienteCriteria medioCriteria;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private ClienteOficinaCriteria medioOficinaCriteria;
	protected ClienteOficinaIf clienteOficinaIf = null;
	protected ClienteOficinaIf medioOficinaIf = null;
	
	protected ClienteIf clienteIf = null;
	protected ClienteIf medioIf;
		
	protected MarcaProductoIf marcaProductoIf = null;
	protected TipoProveedorIf tipoProveedorIf = null;
	
	private ProductoClienteCriteria productoClienteCriteria;
	private ProductoClienteIf productoClienteIf = null;
		
	private Collection<OrdenMedioIf> ordenesMedioXProductoColl;
	private Collection<Object[]> ordenesMedioDetalleXProductoColl;
	private Collection presupuestosDetalle;
	
	private String[] meses = new String[]{"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
	private static final String CON_IVA = "S";
	protected Double IVA_CERO = 0D;
	protected Double IVA = 0D;
	//protected Double IVA = Parametros.getIVA() / 100;
	
	DefaultTableModel tblInversionesPlanesMedio;
	
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private Vector<InversionClienteMesData> inversionColeccion = new Vector<InversionClienteMesData>();
	private static String NOMBRE_MENU_MEDIOS = "MEDIOS";
	private String filtro = "";
	private Collection<OrdenMedioDetalleIf> ordenesMedioDetalleXCanalColl;
	
	//para Cliente/Producto/T-Medios/Medios/
	private Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioIf>>>> mapClientesIdProductosIdMedioIdOrdenesXProducto = new LinkedHashMap<Long, Map<Long,Map<Long,ArrayList<OrdenMedioIf>>>>();
	private Map<Long,Map<Long,Map<Long,Map<String,BigDecimal>>>> mapClientesIdProductosIdMedioIdMesesTotalOrdenesProducto = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,BigDecimal>>>>();
	private Map<Long,Map<Long,Map<Long,Map<String,BigDecimal>>>> mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,BigDecimal>>>>();
	private Map<Long,Map<Long,Map<Long,Map<String,BigDecimal>>>> mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,BigDecimal>>>>();
	private Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>> mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList = new LinkedHashMap<Long, Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
	private Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>> mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList = new LinkedHashMap<Long, Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
	private Map<Long,Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>> mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
	private Map<Long,Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>> mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
	
	//mapas para mostrar CLIENTES primero
	private Map<Long,Map<Long,Map<String,BigDecimal>>> mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
	private Map<Long,Map<Long,Map<String,BigDecimal>>> mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
	private Map<Long,Map<String,BigDecimal>> mapClientesIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<String,BigDecimal>>();
	
	//para Cliente/T-Medios/Medios/Producto
	private Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioIf>>>> mapClientesIdMediosIdProductosIdOrdenesXProducto = new LinkedHashMap<Long, Map<Long,Map<Long,ArrayList<OrdenMedioIf>>>>();
	private Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>> mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList = new LinkedHashMap<Long, Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
	private Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>> mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList = new LinkedHashMap<Long, Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
	
	private Map<Long,Map<Long,Map<Long,Map<String,BigDecimal>>>> mapClientesIdMedioIdProductosIdMesesTotalOrdenesProducto = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,BigDecimal>>>>();
	private Map<Long,Map<Long,Map<Long,Map<String,BigDecimal>>>> mapClientesIdMedioIdProductosIdMesesTotalOrdenesCanal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,BigDecimal>>>>();
	private Map<Long,Map<Long,Map<Long,Map<String,BigDecimal>>>> mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,BigDecimal>>>>();
	
	//para T-Medios/Medios/Cliente/Producto
	private Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioIf>>>> mapMediosIdClientesIdProductosIdOrdenesXProducto = new LinkedHashMap<Long, Map<Long,Map<Long,ArrayList<OrdenMedioIf>>>>();
	private Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>> mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList = new LinkedHashMap<Long, Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
	private Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>> mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList = new LinkedHashMap<Long, Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
	
	private Map<Long,Map<Long,Map<Long,Map<String,BigDecimal>>>> mapMedioIdClientesIdProductosIdMesesTotalOrdenesProducto = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,BigDecimal>>>>();
	private Map<Long,Map<Long,Map<Long,Map<String,BigDecimal>>>> mapMedioIdClientesIdProductosIdMesesTotalOrdenesCanal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,BigDecimal>>>>();
	private Map<Long,Map<Long,Map<Long,Map<String,BigDecimal>>>> mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,BigDecimal>>>>();
	
	//mapas para mostrar TMEDIOS/MEDIOS primero
	private Map<Long,Map<Long,Map<String,BigDecimal>>> mapMediosIdClientesIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
	private Map<Long,Map<Long,Map<String,BigDecimal>>> mapMediosIdProductoIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
	private Map<Long,Map<String,BigDecimal>> mapMediosIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<String,BigDecimal>>();
	
	private Map<Long,ClienteOficinaIf> mapaClienteOficina = new LinkedHashMap<Long,ClienteOficinaIf>();
	private Map<Long,ClienteIf> mapaCliente = new LinkedHashMap<Long,ClienteIf>();
	
	private Map<Long,MarcaProductoIf> mapaMarcaProducto = new LinkedHashMap<Long,MarcaProductoIf>();
	private Map<Long,ProductoClienteIf> mapaProductoCliente = new LinkedHashMap<Long,ProductoClienteIf>();
	
	private Map<Long,GenericoIf> mapaGenerico = new LinkedHashMap<Long,GenericoIf>();
	private Map<Long,ProductoIf> mapaProducto = new LinkedHashMap<Long,ProductoIf>();
	
	private Map<Long,OrdenMedioIf> mapaOrdenesMedio = new LinkedHashMap<Long,OrdenMedioIf>();
	private Map<Long,Double> mapaOrdenIdValorNotaCreditoError = new LinkedHashMap<Long,Double>();
	private Map<Long,Double> mapaOrdenIdValorNotaCreditoGanancia = new LinkedHashMap<Long,Double>();
		
	//para agrupar	
	//Cliente/Producto/TMedio/Medio
	private Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>>> mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>>>();
	private Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>> mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>>();
	//TMedio/Medio/Cliente/Producto
	private Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>>> mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>>>();
	
	
	//para obtener totales x meses
	//Cliente/Producto/TMedio/Medio
	private Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>>> mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>>>();
	private Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>> mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>>();
	//TMedio/Medio/Cliente/Producto
	private Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>>> mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>>>();
	
	private Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<String,BigDecimal>>>>>>>>> mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsPautaAuspicioMesesTotalOrdenesCanal;
	
	private static final String PAUTA_REGULAR = "P";
	private static final String AUSPICIO = "A";
	private static final String PAUTA_REGULAR_AUSPICIO = "B";	
	private static final String NOMBRE_PAUTA_REGULAR = "Pauta Regular";
	private static final String NOMBRE_AUSPICIO = "Auspicio";
	protected String tipoPauta = PAUTA_REGULAR_AUSPICIO;
	
	protected DerechoProgramaIf derechoProgramaIf = null;	
	private Map<Long,TipoProveedorIf> mapaTipoMedio = new LinkedHashMap<Long,TipoProveedorIf>();
	private Map<Long,DerechoProgramaIf> mapaDerechoPrograma = new LinkedHashMap<Long,DerechoProgramaIf>();
	private Map<Long,UsuarioIf> mapaUsuario = new LinkedHashMap<Long,UsuarioIf>();
	private Map<Long,EmpleadoIf> mapaEmpleado = new LinkedHashMap<Long,EmpleadoIf>();
	private String estado = TODOS;
	
	
	public InversionPlanMediosModel(){
		cargarCombos();
		initKeyListeners();
		cargarMapas();
		showSaveMode();
		initListeners();
	}
	
	public void cargarCombos(){
		cargarComboTipoMedio();
		cargarComboDerechoPrograma();
		cargarComboOficina();
		cargarComboTipoProducto();
	}
	
	Comparator<TipoProductoIf> ordenadorTipoProductoPorNombre = new Comparator<TipoProductoIf>(){
		public int compare(TipoProductoIf o1, TipoProductoIf o2) {
			return o1.getNombre().compareTo(o2.getNombre());
		}		
	};
	
	private void cargarComboTipoProducto(){
		try {
			List tiposProducto = (List) SessionServiceLocator.getTipoProductoSessionService().findTipoProductoByEmpresaId(Parametros.getIdEmpresa());
			Collections.sort((ArrayList)tiposProducto, ordenadorTipoProductoPorNombre);
			tiposProducto.add(TODOS);
			refreshCombo(getCmbTipoProducto(), tiposProducto);
			getCmbTipoProducto().setSelectedItem(TODOS);
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboOficina(){
		try {
			List oficinas = (ArrayList)SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(Parametros.getIdEmpresa());
			oficinas.add(TODOS);
			refreshCombo(getCmbOficinaEmpresa(),oficinas);
			getCmbOficinaEmpresa().setSelectedItem(TODOS);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
		
	public void initKeyListeners(){		
		getSpTblInversiones().setVisible(false);
		getRbAgruparClienteMedioProducto().setVisible(false);
		//getRbAgruparMedioClienteProducto().setVisible(false);
		
		getCmbFechaInicio().setCalendar(calendarFechaInicioMin);
		calendarFechaInicio = calendarFechaInicioMin;
		fechaInicio = calendarFechaInicio.getTime();
		
		getCmbFechaFin().setCalendar(calendarFechaFinMax);
		calendarFechaFin = calendarFechaFinMax;
		fechaFin = calendarFechaFin.getTime();	
		
		getLblDerechoPrograma().setVisible(false);
		getCmbDerechoPrograma().setVisible(false);
		getCbMostrarDerechoPrograma().setVisible(false);		
				
		getTxtCliente().setEditable(false);
		getTxtMedio().setEditable(false);
		getTxtProducto().setEditable(false);
		getTxtClienteOficina().setEditable(false);
		getTxtMedioOficina().setEditable(false);
		
		getCbClientesTodos().setSelected(true);
		getCbMediosTodos().setSelected(true);
		getCbProductosTodos().setSelected(true);
		getCbClienteOficinaTodos().setSelected(true);
		getCbMedioOficinaTodos().setSelected(true);
				
		getCbPautaRegular().setSelected(true);
		getCbAuspicio().setSelected(true);
		getCbMostrarCliente().setSelected(true);
		getCbMostrarProducto().setSelected(true);
		getCbMostrarTipoMedio().setSelected(true);
		getCbMostrarMedio().setSelected(true);
		getCbMostrarDerechoPrograma().setSelected(false);
		
		getCbMostrarCliente().setEnabled(false);
		getCbMostrarClienteOficina().setSelected(true);
		getCbMostrarMarca().setSelected(false);
		getCbMostrarMedioOficina().setSelected(true);
		getCbMostrarTipoPauta().setSelected(true);
		
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);		
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());		
				
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
		
		getCmbMarcas().addItem(TODOS);
		getCmbMarcas().setEnabled(false);
		
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnMedio().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnMedio().setToolTipText("Buscar Medio");
		getBtnProducto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnProducto().setToolTipText("Buscar Producto");
		getBtnConsultar().setToolTipText("Consultar");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnClienteOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnClienteOficina().setToolTipText("Buscar Cliente Oficina");
		getBtnMedioOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnMedioOficina().setToolTipText("Buscar Medio Oficina");
		
		getBtnProducto().setEnabled(false);
	}
	
	private void initListeners() {
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cleanTable();
				
				if (inversionColeccion.size() > 0) inversionColeccion.clear();
				
				obtenerOrdenesMedioDetalleProducto(); 
				//cargo el mapa de Ordenes de Medio segun OrdenMedioId de las OrdenesMedioDetalleXProducto y OrdenesMedioDetalleXCanal 
				cargarMapaOrdenesMedio();
												
				//PARA LA AGRUPACION X CLIENTE/T-MEDIOS/MEDIOS/PRODUCTO
				if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_PRODUCTO_TMEDIOS_MEDIOS) == 0){
					agruparOrdenesMedioDetalleGeneralByClienteProductoTMediosMedio();
					obtenerTotalesMesesOrdenesProductoByClienteProductoTMedios();									
					cargarTablaXClientesProductoTMedios();			
				
				}//PARA LA AGRUPACION X CLIENTE/T-MEDIOS/MEDIOS/PRODUCTO
				else if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO) == 0){
					//agruparOrdenesMedioDetalleProductoByClienteTMediosProducto();
					agruparOrdenesMedioDetalleGeneralByClienteTMediosMedioProducto();
					obtenerTotalesMesesOrdenesProductoByClienteTMediosProducto2();
					//obtenerTotalesMesesOrdenesProductoByClienteTMediosProducto();
					//cargarTablaXClientesTMediosProducto();
					cargarTablaXClientesTMediosProducto2();
				}			
				
				//PARA LA AGRUPACION X T-MEDIOS/MEDIOS/CLIENTE/PRODUCTO 
				else if (agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO) == 0){
					
					agruparOrdenesMedioDetalleGeneralByTMediosMedioClienteProducto();
					obtenerTotalesMesesOrdenesProductoByTMediosClienteProducto();
					cargarTablaXTMediosClientesProducto();
					
					//agruparOrdenesProductoByTMediosClienteProducto(); DESCOMENTAR CUANDO LLEGUE LA ING
					/*agruparOrdenesMedioDetalleProductoByTMediosClienteProducto(); //REEMPLAZARA A LA DE ARRIBA //COMENTAR CUANDO VENGA LA ING
					
					//agruparOrdenesMedioDetalleByTMediosClienteProducto();  
					//agruparOrdenesMedioDetalleCanalByTMediosClienteProducto();
					
					obtenerTotalesMesesOrdenesProductoByTMediosClienteProducto2(); //COMENTAR CUANDO VENGA LA ING
					
					obtenerTotalesMesesOrdenesCanalByTMediosClienteProducto();
					
					//agrupados por T-MEDIOS/MEDIOS/CLIENTE/PRODUCTO de esta agrupacion se obtendra para mostrar
					obtenerTotalesMesesOrdenesProductoAndCanalByTMediosClienteProducto();
					
					//mostrar MEDIO/PRODUCTOS
					if (!getCbMostrarCliente().isSelected() && getCbMostrarProducto().isSelected()){
						mapMediosIdProductoIdMesesTotalOrdenesProductoAndCanal.clear();
						obtenerTotalesMesesOrdenesProductoAndCanalByMedioProductosFiltroTMCP();
						
					}//mostrar MEDIO/CLIENTE 
					else if (getCbMostrarCliente().isSelected() && !getCbMostrarProducto().isSelected()){
						mapMediosIdClientesIdMesesTotalOrdenesProductoAndCanal.clear();
						obtenerTotalesMesesOrdenesProductoAndCanalByMedioTMediosFiltroTMCP();
						
					}//mostrar MEDIO 
					else if (!getCbMostrarCliente().isSelected() && !getCbMostrarProducto().isSelected()){
						mapMediosIdMesesTotalOrdenesProductoAndCanal.clear();
						obtenerTotalesMesesOrdenesProductoAndCanalByMedioFiltroTMCP();
					}
										
					cargarTablaXTMediosClientesProducto2();*/
				}
				
				boolean datosConsultados = true;
				report(true);
				//System.out.println("fin que pasa");
			}
		});
		
		getBtnCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				String tituloVentanaBusqueda = "Clientes";
				clienteCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, CODIGO_TIPO_CLIENTE);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtCliente().setText(clienteIf.getNombreLegal());
					getCbClientesTodos().setSelected(false);
					
					clienteOficinaIf = null;
					getTxtClienteOficina().setText("");										
					
					//COMENTED 27 JULIO
					getBtnProducto().setEnabled(true);
					cargarComboMarcas();
					
				}			
			}
		});
		
		getBtnClienteOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tituloVentanaBusqueda = "Oficinas de Clientes";
									
				if (clienteIf != null){
					idCliente = clienteIf.getId();
					idCorporacion = clienteIf.getCorporacionId();
				}
				
				clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_CLIENTE, "", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria,	JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if(popupFinder.getElementoSeleccionado() != null){
					clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					
					if (clienteIf == null) {
						try {
							clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
							getTxtCliente().setText(clienteIf.getNombreLegal());
							getCbClientesTodos().setSelected(false);
							cargarComboMarcas();
							//COMENTED 27 JULIO
							getBtnProducto().setEnabled(true);
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					setClienteOficina();
					getCbClienteOficinaTodos().setSelected(false);
				}
			}		
		});
		
		getBtnProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				String tituloVentanaBusqueda = "Productos del Cliente";
				Map mapa = new LinkedHashMap();
				Long idMarcaProducto = 0L;
				//ADD 26 JULIO
				if (marcaProductoIf != null){
					idMarcaProducto = marcaProductoIf.getId();
				}
				//END 26 JULIO
				productoClienteCriteria = new ProductoClienteCriteria(tituloVentanaBusqueda,clienteIf.getId(),idMarcaProducto);
				//COMENTED 26 JULIO
				//productoClienteCriteria = new ProductoClienteCriteria(tituloVentanaBusqueda,clienteIf.getId());
				productoClienteCriteria.setQueryBuilded(mapa);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
			    JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), productoClienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					productoClienteIf = (ProductoClienteIf) popupFinder.getElementoSeleccionado();
					getTxtProducto().setText(productoClienteIf.getNombre());
					getCbProductosTodos().setSelected(false);
				}
			}
		});
				
		getBtnMedio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idTipoProveedor = 0L;
				String tituloVentanaBusqueda = "Medios";
				if (tipoProveedorIf != null){
					idTipoProveedor = tipoProveedorIf.getId();			
					medioCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, CODIGO_TIPO_PROVEEDOR,idTipoProveedor);
				}else{
					medioCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, CODIGO_TIPO_PROVEEDOR);
				}
			
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), medioCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					medioIf = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtMedio().setText(medioIf.getNombreLegal());
					getCbMediosTodos().setSelected(false);
					
					medioOficinaIf = null;
					getTxtMedioOficina().setText("");										
										
				}			
			}
			
		});
		
		//ADD 26 JULIO		
		getBtnMedioOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				//ADD 27 JULIO
				Long idTipoProveedor = 0L;
				
				String tituloVentanaBusqueda = "Oficinas de los Medios";
									
				if (medioIf != null){
					idCliente = medioIf.getId();
					idCorporacion = medioIf.getCorporacionId();					
				}
				
				//ADD 27 JULIO
				if (tipoProveedorIf != null){
					idTipoProveedor = tipoProveedorIf.getId();			
					medioOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_PROVEEDOR, idTipoProveedor,"", false);
				}else
					medioOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_PROVEEDOR, "", false);
				//END 27 JULIO
				
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), medioOficinaCriteria,JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if(popupFinder.getElementoSeleccionado() != null){
					medioOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					
					if (medioIf == null) {
						try {
							medioIf = SessionServiceLocator.getClienteSessionService().getCliente(medioOficinaIf.getClienteId());
							getTxtMedio().setText(medioIf.getNombreLegal());
							getCbMediosTodos().setSelected(false);
							
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					setMedioOficina();
					getCbMedioOficinaTodos().setSelected(false);
				}
			}		
		});
		
		//ADD 26 JULIO
		getCbClienteOficinaTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbClienteOficinaTodos().isSelected()){
					if (clienteOficinaIf != null){
						clienteOficinaIf = null;
						getTxtClienteOficina().setText(null);						
					}
				}
			}
		});
		//END 26 JULIO
		
		//ADD 22 JULIO
		getCbClientesTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbClientesTodos().isSelected() || getBtnCliente().isEnabled()){
					if (clienteIf != null){
						clienteIf = null;
						getTxtCliente().setText(null);						
					}
					//ADD 26 JULIO
					if (clienteOficinaIf != null || getBtnClienteOficina().isEnabled()){
						clienteOficinaIf = null;
						getTxtClienteOficina().setText(null);	
						getCbClienteOficinaTodos().setSelected(true);
					}
					if(marcaProductoIf != null || getCmbMarcas().isEnabled()){
						getCmbMarcas().removeAllItems();
						getCmbMarcas().addItem(TODOS);
						getCmbMarcas().setSelectedItem(TODOS);
						getCmbMarcas().setEnabled(false);
						marcaProductoIf = null;
					}
					//END 26 JULIO
					if(productoClienteIf != null || getBtnProducto().isEnabled()){
						productoClienteIf = null;
						getTxtProducto().setText(null);	
						getBtnProducto().setEnabled(false);
						getCbProductosTodos().setSelected(true);
					}					
				}
			}
		});
				
		getCbProductosTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbProductosTodos().isSelected()){
					productoClienteIf = null;
					getTxtProducto().setText(null);
				}
			}
		});
		
		getCbMediosTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbMediosTodos().isSelected()){
					if (medioIf != null){
						medioIf = null;
						getTxtMedio().setText(null);
					}
					
					//ADD 27 JULIO
					if (medioOficinaIf != null){
						medioOficinaIf = null;
						getTxtMedioOficina().setText(null);	
						getCbMedioOficinaTodos().setSelected(true);
					}
					//END 27 JULIO
				}
			}
		});
		
		//ADD 27 JULIO
		getCbMedioOficinaTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbMedioOficinaTodos().isSelected()){
					if (medioOficinaIf != null){
						medioOficinaIf = null;
						getTxtMedioOficina().setText(null);						
					}
				}
			}
		});
		//END 27 JULIO
		
		getRbAgruparClienteProductoMedios().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getRbAgruparClienteProductoMedios().isSelected()){
					agruparBy = AGRUPAR_POR_CLIENTE_PRODUCTO_TMEDIOS_MEDIOS; 
					//ADD 9 AGOSTO
					getCbMostrarCliente().setSelected(true);
					getCbMostrarClienteOficina().setSelected(true);
					getCbMostrarCliente().setEnabled(false);
					getCbMostrarClienteOficina().setEnabled(false);
									
					getCbMostrarProducto().setSelected(false);
					getCbMostrarTipoMedio().setSelected(false);
					getCbMostrarMedio().setSelected(false);
					getCbMostrarTipoMedio().setEnabled(true);
					getCbMostrarMedio().setEnabled(true);
					getCbMostrarDerechoPrograma().setSelected(false);
					//ADD 17 AGOSTO
					getCbMostrarMarca().setSelected(false);
					getCbMostrarMedioOficina().setEnabled(true);
					getCbMostrarMedioOficina().setSelected(false);
					getCbMostrarTipoPauta().setEnabled(true);
				}
			}
		});
		
		getRbAgruparClienteMedioProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getRbAgruparClienteMedioProducto().isSelected()){
					agruparBy = AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO; 
					//ADD 9 AGOSTO
					getCbMostrarCliente().setSelected(true);
					getCbMostrarClienteOficina().setSelected(true);
					getCbMostrarCliente().setEnabled(false);
					getCbMostrarClienteOficina().setEnabled(false);
					
					getCbMostrarProducto().setSelected(false);
					getCbMostrarTipoMedio().setSelected(false);
					getCbMostrarMedio().setSelected(false);
					getCbMostrarTipoMedio().setEnabled(true);
					getCbMostrarMedio().setEnabled(true);
					getCbMostrarDerechoPrograma().setSelected(false);
					//ADD 17 AGOSTO
					getCbMostrarMarca().setSelected(false);
					getCbMostrarMedioOficina().setEnabled(true);
					getCbMostrarTipoPauta().setEnabled(true);
				}
			}
		});
		
		getRbAgruparMedioClienteProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getRbAgruparMedioClienteProducto().isSelected()){
					agruparBy = AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO; 
					
					getCbMostrarTipoMedio().setSelected(true);
					getCbMostrarMedio().setSelected(true);
					getCbMostrarMedioOficina().setSelected(true);
					getCbMostrarTipoMedio().setEnabled(false);
					getCbMostrarMedio().setEnabled(false);
					getCbMostrarMedioOficina().setEnabled(false);
					
					getCbMostrarCliente().setSelected(false);
					getCbMostrarClienteOficina().setSelected(false);					
					getCbMostrarCliente().setEnabled(true);
					getCbMostrarClienteOficina().setEnabled(true);
					getCbMostrarTipoPauta().setEnabled(true);
					getCbMostrarDerechoPrograma().setSelected(false);
				}
			}
		});
		
		getCmbFechaInicio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCmbFechaInicio().getCalendar() != null){
		
					//ADD 1 AGOSTO
					calendarFechaInicioMin.setTime(getCmbFechaInicio().getCalendar().getTime());
					getCmbFechaInicio().setCalendar(calendarFechaInicioMin);
					calendarFechaInicio = getCmbFechaInicio().getCalendar();
					fechaInicio = calendarFechaInicio.getTime();
			
					if(calendarFechaInicio.after(calendarFechaFin)){
						calendarFechaFinMax.setTime(getCmbFechaInicio().getCalendar().getTime());
						getCmbFechaFin().setCalendar(calendarFechaFinMax);
						getCmbFechaFin().repaint();
						calendarFechaFin = getCmbFechaFin().getCalendar();
						fechaFin = calendarFechaFin.getTime();					
					}
				}
			}
		});
		
		getCmbFechaFin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCmbFechaFin().getCalendar() != null){
				
					//ADD 1 AGOSTO
					calendarFechaFinMax.setTime(getCmbFechaFin().getCalendar().getTime());
					getCmbFechaFin().setCalendar(calendarFechaFinMax);
					calendarFechaFin = getCmbFechaFin().getCalendar();
					fechaFin = calendarFechaFin.getTime();
			
					if(calendarFechaFin.before(calendarFechaInicio)){
						calendarFechaInicioMin.setTime(getCmbFechaFin().getCalendar().getTime());
						getCmbFechaInicio().setCalendar(calendarFechaInicioMin);
						getCmbFechaInicio().repaint();
						calendarFechaInicio = getCmbFechaInicio().getCalendar();
						fechaInicio = calendarFechaInicio.getTime();
					}
				}
			}
		});
		
		//ADD 26 JULIO
		getCmbMarcas().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbMarcas().getSelectedItem() != null && !getCmbMarcas().getSelectedItem().equals(TODOS)){
					marcaProductoIf = (MarcaProductoIf) getCmbMarcas().getSelectedItem();	
					//ADD 27 JULIO
					//getBtnProducto().setEnabled(true);
					if (productoClienteIf != null){
						getTxtProducto().setText(null);
						getCbProductosTodos().setSelected(true);
						productoClienteIf = null;
					}
				}
				else{
					marcaProductoIf = null;
				}
			}
		});			
		
		getCmbTipoMedio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoMedio().getSelectedItem() != null && !getCmbTipoMedio().getSelectedItem().equals(TODOS)){
					tipoProveedorIf = (TipoProveedorIf) getCmbTipoMedio().getSelectedItem();
					if (medioIf != null){
						getTxtMedio().setText(null);
						getCbMediosTodos().setSelected(true);
						medioIf = null;
					}
					if (medioOficinaIf != null){
						getTxtMedioOficina().setText(null);
						getCbMedioOficinaTodos().setSelected(true);
						medioOficinaIf = null;
					}
				}
				else{
					tipoProveedorIf = null;
				}
			}
		});
		//END 26 JULIO
		
		//ADD 16 AGOSTO
		getCmbDerechoPrograma().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbDerechoPrograma().getSelectedItem() != null && !getCmbDerechoPrograma().getSelectedItem().equals(TODOS)){
					derechoProgramaIf = (DerechoProgramaIf) getCmbDerechoPrograma().getSelectedItem();
				}
				else{
					derechoProgramaIf = null;
				}
			}
		});
		
		getCbPautaRegular().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbPautaRegular().isSelected() && !getCbAuspicio().isSelected()){
					tipoPauta = PAUTA_REGULAR;
					getCbMostrarTipoPauta().setSelected(true);
					getCbMostrarTipoPauta().setEnabled(true);
				}
				else if(!getCbPautaRegular().isSelected() && getCbAuspicio().isSelected()){
					tipoPauta = AUSPICIO;
					getCbMostrarTipoPauta().setSelected(true);
					getCbMostrarTipoPauta().setEnabled(true);
				}
				else if(getCbPautaRegular().isSelected() && getCbAuspicio().isSelected()){
					tipoPauta = PAUTA_REGULAR_AUSPICIO;
					getCbMostrarTipoPauta().setSelected(true);
					getCbMostrarTipoPauta().setEnabled(true);
				}
				else if(!getCbPautaRegular().isSelected() && !getCbAuspicio().isSelected()){
					tipoPauta = PAUTA_REGULAR_AUSPICIO;
					getCbMostrarTipoPauta().setSelected(false);
					getCbMostrarTipoPauta().setEnabled(false);
				}
			}
		});
		
		getCbAuspicio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbPautaRegular().isSelected() && !getCbAuspicio().isSelected()){
					tipoPauta = PAUTA_REGULAR;
					getCbMostrarTipoPauta().setSelected(true);
					getCbMostrarTipoPauta().setEnabled(true);
				}
				else if(!getCbPautaRegular().isSelected() && getCbAuspicio().isSelected()){
					tipoPauta = AUSPICIO;
					getCbMostrarTipoPauta().setSelected(true);
					getCbMostrarTipoPauta().setEnabled(true);
				}
				else if(getCbPautaRegular().isSelected() && getCbAuspicio().isSelected()){
					tipoPauta = PAUTA_REGULAR_AUSPICIO;
					getCbMostrarTipoPauta().setSelected(true);
					getCbMostrarTipoPauta().setEnabled(true);
				}
				else if(!getCbPautaRegular().isSelected() && !getCbAuspicio().isSelected()){
					tipoPauta = PAUTA_REGULAR_AUSPICIO;
					getCbMostrarTipoPauta().setSelected(false);
					getCbMostrarTipoPauta().setEnabled(false);
				}
			}
		});
		//END 16 AGOSTO
		
		//ADD 18 AGOSTO
		getCbMostrarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (!getCbMostrarCliente().isSelected() && 
						agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO)== 0){
					getCbMostrarClienteOficina().setSelected(false);
				}else if (!getCbMostrarCliente().isSelected() && 
						agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO)== 0){	
					getCbMostrarClienteOficina().setSelected(true);
				}
			}
		});
		
		getCbMostrarClienteOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (!getCbMostrarClienteOficina().isSelected() && 
						agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO)== 0){
					getCbMostrarCliente().setSelected(false);
				}else if (getCbMostrarClienteOficina().isSelected() && 
						agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO)== 0){
					getCbMostrarCliente().setSelected(true);
				}
													
			}
		});
		
		getCbMostrarTipoMedio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (!getCbMostrarTipoMedio().isSelected() && 
						agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO)!= 0){
					getCbMostrarMedio().setSelected(false);
					getCbMostrarMedioOficina().setSelected(false);				
				}/*else if (getCbMostrarTipoMedio().isSelected() && 
						agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO)!= 0){
					getCbMostrarMedio().setSelected(true);
					getCbMostrarMedioOficina().setSelected(true);	
				}	*/			
			}
		});
		
		getCbMostrarMedio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (!getCbMostrarMedio().isSelected() && 
						agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO)!= 0){
					getCbMostrarTipoMedio().setSelected(false);
					getCbMostrarMedioOficina().setSelected(false);
				}else if (getCbMostrarMedio().isSelected() && 
						agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO)!= 0){
					getCbMostrarTipoMedio().setSelected(true);
					//getCbMostrarMedioOficina().setSelected(true);	
				}					
			}
		});
		
		getCbMostrarMedioOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (!getCbMostrarMedioOficina().isSelected() && 
						agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO)!= 0){
					getCbMostrarTipoMedio().setSelected(false);
					getCbMostrarMedio().setSelected(false);
				}else if (getCbMostrarMedioOficina().isSelected() && 
						agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO)!= 0){
					//getCbMostrarTipoMedio().setSelected(true);
					//getCbMostrarTipoMedio().setSelected(true);	
				}					
			}
		});
		
		getCmbEstado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbEstado().getSelectedItem() != null){
					if(getCmbEstado().getSelectedItem().equals("COTIZADO")){
						estado = "COTIZADO";
						getCbVerAprobadosFacturados().setEnabled(false);
						getCbVerAprobadosFacturados().setSelected(false);
					}else if(getCmbEstado().getSelectedItem().equals("APROBADO")){
						estado = "APROBADO";
						getCbVerAprobadosFacturados().setEnabled(false);
						getCbVerAprobadosFacturados().setSelected(false);
					}else if(getCmbEstado().getSelectedItem().equals("FACTURADO")){
						estado = "FACTURADO";
						getCbVerAprobadosFacturados().setEnabled(false);
						getCbVerAprobadosFacturados().setSelected(false);
					}else if(getCmbEstado().getSelectedItem().equals("PREPAGADO")){
						estado = "PREPAGADO";
						getCbVerAprobadosFacturados().setEnabled(false);
						getCbVerAprobadosFacturados().setSelected(false);
					}else{
						estado = TODOS;
						getCbVerAprobadosFacturados().setEnabled(true);
					}					
				}
				else{
					estado = TODOS;
				}
			}
		});
	}
	
	//ADD 26 JULIO
	//se cargan todas las marcas del cliente seleccionado
	public void cargarComboMarcas(){
		try {
			List marcas = new ArrayList();
						
			Iterator it = SessionServiceLocator.getMarcaProductoSessionService().findMarcaProductoByClienteId(clienteIf.getId()).iterator();
			while (it.hasNext()) {
				MarcaProductoIf marcaProducto = (MarcaProductoIf) it.next();
				marcas.add(marcaProducto);
			}
			
			//Collections.sort((ArrayList)tiposMedios);
			marcas.add(TODOS);
			getCmbMarcas().removeAllItems();
			refreshCombo(getCmbMarcas(),marcas);
			getCmbMarcas().setSelectedItem(TODOS);
			getCmbMarcas().setEnabled(true);
			//ADD 27 JULIO
			//getBtnProducto().setEnabled(false);
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	//END 26 JULI0

	//ADD 26 JULIO
	private void cargarComboTipoMedio(){
		try {
			List tiposMedios = new ArrayList();
						
			Iterator it = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByTipo(TIPO_PROVEEDOR_MEDIOS).iterator();
			while (it.hasNext()) {
				TipoProveedorIf tipoProveedorMedio = (TipoProveedorIf) it.next();
				tiposMedios.add(tipoProveedorMedio);
			}
			
			//Collections.sort((ArrayList)tiposMedios);
			tiposMedios.add(TODOS);
			refreshCombo(getCmbTipoMedio(),tiposMedios);
			getCmbTipoMedio().setSelectedItem(TODOS);
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}//END ADD 26 JULIO
	
	private void cargarComboDerechoPrograma(){
		try {
			List derechosPrograma = new ArrayList();
						
			Iterator it = SessionServiceLocator.getDerechoProgramaSessionService().getDerechoProgramaList().iterator();
			while (it.hasNext()) {
				DerechoProgramaIf derechoPrograma = (DerechoProgramaIf) it.next();
				derechosPrograma.add(derechoPrograma);
			}
			
			//Collections.sort((ArrayList)tiposMedios);
			derechosPrograma.add(TODOS);
			refreshCombo(getCmbDerechoPrograma(),derechosPrograma);
			getCmbDerechoPrograma().setSelectedItem(TODOS);
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
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
			Collection clientes = SessionServiceLocator.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesIt = clientes.iterator();
			while(clientesIt.hasNext()){
				ClienteIf cliente = (ClienteIf)clientesIt.next();
				mapaCliente.put(cliente.getId(), cliente);
			}
			
			mapaMarcaProducto.clear();
			Collection marcasProductos = SessionServiceLocator.getMarcaProductoSessionService().getMarcaProductoList();
			Iterator marcasProductosIt = marcasProductos.iterator();
			while(marcasProductosIt.hasNext()){
				MarcaProductoIf marcaProducto = (MarcaProductoIf)marcasProductosIt.next();
				mapaMarcaProducto.put(marcaProducto.getId(), marcaProducto);
			}
			
			mapaProductoCliente.clear();
			Collection productosCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoClienteList();
			Iterator productosClienteIt = productosCliente.iterator();
			while(productosClienteIt.hasNext()){
				ProductoClienteIf productoCliente = (ProductoClienteIf)productosClienteIt.next();
				mapaProductoCliente.put(productoCliente.getId(), productoCliente);
			}		
			
			mapaProducto.clear();
			Collection productos = SessionServiceLocator.getProductoSessionService().getProductoList();
			Iterator productosIt = productos.iterator();
			while(productosIt.hasNext()){
				ProductoIf producto = (ProductoIf)productosIt.next();
				mapaProducto.put(producto.getId(), producto);
			}	
			
			mapaGenerico.clear();
			Collection genericos = SessionServiceLocator.getGenericoSessionService().getGenericoList();
			Iterator genericosIt = genericos.iterator();
			while(genericosIt.hasNext()){
				GenericoIf generico = (GenericoIf)genericosIt.next();
				mapaGenerico.put(generico.getId(), generico);
			}
			
			mapaTipoMedio.clear();
			Iterator tipoMedioIt = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByTipo("M").iterator();
			while(tipoMedioIt.hasNext()){
				TipoProveedorIf tipoProveedort = (TipoProveedorIf)tipoMedioIt.next();
				mapaTipoMedio.put(tipoProveedort.getId(), tipoProveedort);
			}
			
			mapaDerechoPrograma.clear();
			Collection derechosPrograma = SessionServiceLocator.getDerechoProgramaSessionService().getDerechoProgramaList();
			Iterator derechosProgramaIt = derechosPrograma.iterator();
			while(derechosProgramaIt.hasNext()){
				DerechoProgramaIf derechoPrograma = (DerechoProgramaIf)derechosProgramaIt.next();
				mapaDerechoPrograma.put(derechoPrograma.getId(), derechoPrograma);
			}
			
			mapaUsuario.clear();
			Collection usuarios = SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpresaId(Parametros.getIdEmpresa());
			Iterator usuariosIt = usuarios.iterator();
			while(usuariosIt.hasNext()){
				UsuarioIf usuario = (UsuarioIf)usuariosIt.next();
				mapaUsuario.put(usuario.getId(), usuario);
			}
			
			mapaEmpleado.clear();
			Collection empleados = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Iterator empleadosIt = empleados.iterator();
			while(empleadosIt.hasNext()){
				EmpleadoIf empleado = (EmpleadoIf)empleadosIt.next();
				mapaEmpleado.put(empleado.getId(), empleado);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//MODIFIED 29 JULIO ADD 28 JULIO
	public void obtenerOrdenesMedioProducto(){
		try {
			//MAPAS TO ORDENES AGRUPADAS POR PRODUCTO CLIENTE	
			Map<String,Long> mapaOrdenXProducto = new LinkedHashMap<String, Long>();
			if (clienteOficinaIf != null) 
				mapaOrdenXProducto.put("clienteOficinaId", clienteOficinaIf.getId());
			if (productoClienteIf != null)
				mapaOrdenXProducto.put("productoClienteId", productoClienteIf.getId());
			if (medioOficinaIf != null)
				mapaOrdenXProducto.put("proveedorId", medioOficinaIf.getId());
			
			Map<String,Long> mapaGeneralXProducto = new LinkedHashMap<String, Long>();
			if (clienteIf != null && clienteOficinaIf == null) 
				mapaGeneralXProducto.put("keyClienteId", clienteIf.getId());
			if (marcaProductoIf != null && productoClienteIf == null)
				mapaGeneralXProducto.put("keyMarcaProductoId", marcaProductoIf.getId());
			//ADD 29 JULIO
			if (tipoProveedorIf != null && medioIf == null && medioOficinaIf == null)
				mapaGeneralXProducto.put("keyTipoProveedorId", tipoProveedorIf.getId());
			if (medioIf != null && medioOficinaIf == null)
				mapaGeneralXProducto.put("keyProveedorId", medioIf.getId());
			//END 29 JULIO
			
			EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
			Timestamp timeIncio = new Timestamp(fechaInicio.getTime());
			Timestamp timeFin =  new Timestamp(fechaFin.getTime());
			
			ordenesMedioXProductoColl = SessionServiceLocator.getOrdenMedioSessionService().
									 findOrdenMedioByQueryAndQueryGeneralByProductoClienteAndByFechas(mapaOrdenXProducto,mapaGeneralXProducto,timeIncio,timeFin,empresa.getId());
			
			System.out.println("ordenesMedioXProductoIfColl.size: "+ordenesMedioXProductoColl.size());
			
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void obtenerOrdenesMedioDetalleProducto(){
		try {
			boolean aprobadosFacturados = getCbVerAprobadosFacturados().isSelected()?true:false;			
			
			//tipo de producto
			Long tipoProductoId = 0L;
			if(!getCmbTipoProducto().getSelectedItem().equals(TODOS)){
				tipoProductoId = ((TipoProductoIf)getCmbTipoProducto().getSelectedItem()).getId();
			}
			
			//MAPAS ORDENES AGRUPADAS POR PRODUCTO CLIENTE	
			Map<String,Long> mapaOrdenXProducto = new LinkedHashMap<String, Long>();			
			if (clienteOficinaIf != null) 
				mapaOrdenXProducto.put("clienteOficinaId", clienteOficinaIf.getId());			
			if (medioOficinaIf != null)
				mapaOrdenXProducto.put("proveedorId", medioOficinaIf.getId());
			
			Map<String,Long> mapaGeneralXProducto = new LinkedHashMap<String, Long>();			
			if (clienteIf != null && clienteOficinaIf == null) 
				mapaGeneralXProducto.put("keyClienteId", clienteIf.getId());			
			if (marcaProductoIf != null && productoClienteIf == null)
				mapaGeneralXProducto.put("keyMarcaProductoId", marcaProductoIf.getId());			
			if (tipoProveedorIf != null && medioIf == null && medioOficinaIf == null)
				mapaGeneralXProducto.put("keyTipoProveedorId", tipoProveedorIf.getId());			
			
			if (tipoProductoId.compareTo(0L) == 1)
				mapaGeneralXProducto.put("keyTipoProductoId", tipoProductoId);				
			
			if (medioIf != null && medioOficinaIf == null)
				mapaGeneralXProducto.put("keyProveedorId", medioIf.getId());			
			if (productoClienteIf != null)
				mapaGeneralXProducto.put("keyProductoClienteId", productoClienteIf.getId());			
			if (derechoProgramaIf != null)
				mapaGeneralXProducto.put("keyDerechoProgramaId", derechoProgramaIf.getId());
						
			Timestamp timeIncio = Utilitarios.resetTimestampStartDate(new Timestamp(fechaInicio.getTime()));
			Timestamp timeFin =  Utilitarios.resetTimestampEndDate(new Timestamp(fechaFin.getTime()));
			
			ordenesMedioDetalleXProductoColl = SessionServiceLocator.getOrdenMedioSessionService().
									findOrdenMedioDetalleByQueryAndQueryGeneralByProductoAndByFechas(mapaOrdenXProducto,mapaGeneralXProducto,tipoPauta,timeIncio,timeFin, false, Parametros.getIdEmpresa(),estado, aprobadosFacturados, "", true, false);
			//ordenesMedioDetalleXProductoColl.clear();
			
			//Coleccion de Ordenes COMPRA detalle
			/*Map<String,Long> mapaOrdenesCompraDetalle = new LinkedHashMap<String, Long>();			
			if (clienteOficinaIf != null) 
				mapaOrdenesCompraDetalle.put("clienteOficinaId", clienteOficinaIf.getId());			
			if (clienteIf != null && clienteOficinaIf == null) 
				mapaOrdenesCompraDetalle.put("clienteId", clienteIf.getId());			
			if (medioOficinaIf != null)
				mapaOrdenesCompraDetalle.put("proveedorOficinaId", medioOficinaIf.getId());
			if (medioIf != null && medioOficinaIf == null)
				mapaOrdenesCompraDetalle.put("proveedorId", medioIf.getId());
			if (productoClienteIf != null)
				mapaOrdenesCompraDetalle.put("productoClienteId", productoClienteIf.getId());
			if (marcaProductoIf != null && productoClienteIf == null)
				mapaOrdenesCompraDetalle.put("marcaProductoId", marcaProductoIf.getId());		
			if (tipoProveedorIf != null && medioIf == null && medioOficinaIf == null)
				mapaOrdenesCompraDetalle.put("tipoProveedorId", tipoProveedorIf.getId());									
			
			ordenesCompraDetalle = SessionServiceLocator.getOrdenCompraDetalleSessionService().findOrdenCompraByQueryByProductoAndByFechas(mapaOrdenesCompraDetalle, timeIncio,timeFin, Parametros.getIdEmpresa(), agruparBy);
			*/
			
			//Puedo trabajar con presupuesto detalle en lugar de ordenes de compra
			Map<String,Long> mapaPresupuestoDetalle = new LinkedHashMap<String, Long>();			
			if (clienteOficinaIf != null) 
				mapaPresupuestoDetalle.put("clienteOficinaId", clienteOficinaIf.getId());			
			if (clienteIf != null && clienteOficinaIf == null) 
				mapaPresupuestoDetalle.put("clienteId", clienteIf.getId());			
			if (medioOficinaIf != null)
				mapaPresupuestoDetalle.put("proveedorOficinaId", medioOficinaIf.getId());
			if (medioIf != null && medioOficinaIf == null)
				mapaPresupuestoDetalle.put("proveedorId", medioIf.getId());
			if (productoClienteIf != null)
				mapaPresupuestoDetalle.put("productoClienteId", productoClienteIf.getId());
			if (marcaProductoIf != null && productoClienteIf == null)
				mapaPresupuestoDetalle.put("marcaProductoId", marcaProductoIf.getId());		
			if (tipoProveedorIf != null && medioIf == null && medioOficinaIf == null)
				mapaPresupuestoDetalle.put("tipoProveedorId", tipoProveedorIf.getId());
			
			if (tipoProductoId.compareTo(0L) == 1)
				mapaPresupuestoDetalle.put("tipoProductoId", tipoProductoId);
			
			presupuestosDetalle = SessionServiceLocator.getPresupuestoSessionService().findPresupuestosDetalleByQueryByProductoAndByFechas(mapaPresupuestoDetalle, timeIncio,timeFin, false, Parametros.getIdEmpresa(), agruparBy , estado, "MEDIOS", aprobadosFacturados, false, "", true);
			//presupuestosDetalle.clear();			
			
			System.out.println("d");		
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	//ADD 29 JULIO
	public void obtenerOrdenesMedioDetalleCanal(){
		try {
			//MAPAS TO ORDENES AGRUPADAS POR CANAL O MEDIO	
			Map<String,Long> mapaOrdenXCanal = new LinkedHashMap<String, Long>();
			if (clienteOficinaIf != null) 
				mapaOrdenXCanal.put("clienteOficinaId", clienteOficinaIf.getId());
			//if (productoClienteIf != null)
			//	mapaOrdenXCanal.put("productoClienteId", productoClienteIf.getId());
			if (medioOficinaIf != null)
				mapaOrdenXCanal.put("proveedorId", medioOficinaIf.getId());
			
			Map<String,Long> mapaGeneralXCanal = new LinkedHashMap<String, Long>();
			if (clienteIf != null && clienteOficinaIf == null) 
				mapaGeneralXCanal.put("keyClienteId", clienteIf.getId());
			if (marcaProductoIf != null && productoClienteIf == null)
				mapaGeneralXCanal.put("keyMarcaProductoId", marcaProductoIf.getId());
			//ADD 29 JULIO
			if (productoClienteIf != null)
				mapaGeneralXCanal.put("keyProductoClienteId", productoClienteIf.getId());
			if (tipoProveedorIf != null && medioIf == null && medioOficinaIf == null)
				mapaGeneralXCanal.put("keyTipoProveedorId", tipoProveedorIf.getId());
			if (medioIf != null && medioOficinaIf == null)
				mapaGeneralXCanal.put("keyProveedorId", medioIf.getId());
			//END 29 JULIO
			
			EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
			Timestamp timeIncio = new Timestamp(fechaInicio.getTime());
			Timestamp timeFin =  new Timestamp(fechaFin.getTime());
			
			//COMENTED 5 AGOSTO
			//ordenesMedioXCanalColl = SessionServiceLocator.getOrdenMedioSessionService().
			//findOrdenMedioByQueryAndQueryGeneralByCanalAndByFechas(mapaOrdenXCanal,mapaGeneralXCanal,timeIncio,timeFin,empresa.getId());
			
			//ADD 4 AGOSTO
			ordenesMedioDetalleXCanalColl = SessionServiceLocator.getOrdenMedioSessionService().
			findOrdenMedioDetalleByQueryAndQueryGeneralByCanalAndByFechas(mapaOrdenXCanal,mapaGeneralXCanal,timeIncio,timeFin,empresa.getId());
			
			System.out.println("ordenesMedioDetalleXCanalColl: "+ordenesMedioDetalleXCanalColl.size());
			
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//ADD 29 JULIO
	
	//ADD 30 JULIO
	public void agruparOrdenesProductoByClienteProductoTMedios(){
		mapClientesIdProductosIdMedioIdOrdenesXProducto.clear();
		
		try {
			Iterator ordenesMedioXProductoIt = ordenesMedioXProductoColl.iterator();
			while (ordenesMedioXProductoIt.hasNext()){
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioXProductoIt.next();
				Collection<ClienteIf> clienteIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getClienteOficinaId());
				
				if (!clienteIfColl.isEmpty()){
					ClienteIf clienteTempIf = clienteIfColl.iterator().next();
					Long productoClienteId = ordenMedioIf.getProductoClienteId();
					Collection<ClienteIf> medioIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getProveedorId());
					
					if (!medioIfColl.isEmpty()){
						ClienteIf medioTempIf = medioIfColl.iterator().next();
						
						Map<Long,Map<Long,ArrayList<OrdenMedioIf>>> mapProductosIdMedioIdOrdenesXProductoTemp; 
						Map<Long,ArrayList<OrdenMedioIf>> mapMedioIdOrdenesXProductoTemp;
						ArrayList<OrdenMedioIf> listOrdenes;
						
						if (!mapClientesIdProductosIdMedioIdOrdenesXProducto.containsKey(clienteTempIf.getId())){
							mapProductosIdMedioIdOrdenesXProductoTemp = new LinkedHashMap<Long, Map<Long,ArrayList<OrdenMedioIf>>>();
							mapMedioIdOrdenesXProductoTemp = new LinkedHashMap<Long, ArrayList<OrdenMedioIf>>();
							listOrdenes = new ArrayList<OrdenMedioIf>();
												
							listOrdenes.add(ordenMedioIf);
							mapMedioIdOrdenesXProductoTemp.put(medioTempIf.getId(), listOrdenes);
							mapProductosIdMedioIdOrdenesXProductoTemp.put(productoClienteId,mapMedioIdOrdenesXProductoTemp);
							mapClientesIdProductosIdMedioIdOrdenesXProducto.put(clienteTempIf.getId(),mapProductosIdMedioIdOrdenesXProductoTemp);
							
						}else {
							mapProductosIdMedioIdOrdenesXProductoTemp = mapClientesIdProductosIdMedioIdOrdenesXProducto.get(clienteTempIf.getId());
							
							if (!mapProductosIdMedioIdOrdenesXProductoTemp.containsKey(productoClienteId)){
								mapMedioIdOrdenesXProductoTemp = new LinkedHashMap<Long, ArrayList<OrdenMedioIf>>();
								listOrdenes = new ArrayList<OrdenMedioIf>();
								
								listOrdenes.add(ordenMedioIf);
								mapMedioIdOrdenesXProductoTemp.put(medioTempIf.getId(), listOrdenes);
								//mapProductosIdMedioIdOrdenesXProductoTemp.remove(mapMedioIdOrdenesXProductoTemp);	
								mapProductosIdMedioIdOrdenesXProductoTemp.put(productoClienteId, mapMedioIdOrdenesXProductoTemp);
								mapClientesIdProductosIdMedioIdOrdenesXProducto.remove(clienteTempIf.getId());
								mapClientesIdProductosIdMedioIdOrdenesXProducto.put(clienteTempIf.getId(),mapProductosIdMedioIdOrdenesXProductoTemp);
							}else{
								mapMedioIdOrdenesXProductoTemp = mapProductosIdMedioIdOrdenesXProductoTemp.get(productoClienteId);
								if (!mapMedioIdOrdenesXProductoTemp.containsKey(medioTempIf.getId())){
									listOrdenes = new ArrayList<OrdenMedioIf>();
									
									listOrdenes.add(ordenMedioIf);
									mapMedioIdOrdenesXProductoTemp.put(medioTempIf.getId(), listOrdenes);
									mapProductosIdMedioIdOrdenesXProductoTemp.remove(productoClienteId);	
									mapProductosIdMedioIdOrdenesXProductoTemp.put(productoClienteId, mapMedioIdOrdenesXProductoTemp);
									mapClientesIdProductosIdMedioIdOrdenesXProducto.remove(clienteTempIf.getId());
									mapClientesIdProductosIdMedioIdOrdenesXProducto.put(clienteTempIf.getId(),mapProductosIdMedioIdOrdenesXProductoTemp);
								}else{
									listOrdenes = mapMedioIdOrdenesXProductoTemp.get(medioTempIf.getId());
									listOrdenes.add(ordenMedioIf);
									
									mapMedioIdOrdenesXProductoTemp.remove(medioTempIf.getId());
									mapMedioIdOrdenesXProductoTemp.put(medioTempIf.getId(), listOrdenes);
									mapProductosIdMedioIdOrdenesXProductoTemp.remove(productoClienteId);	
									mapProductosIdMedioIdOrdenesXProductoTemp.put(productoClienteId, mapMedioIdOrdenesXProductoTemp);
									mapClientesIdProductosIdMedioIdOrdenesXProducto.remove(clienteTempIf.getId());
									mapClientesIdProductosIdMedioIdOrdenesXProducto.put(clienteTempIf.getId(),mapProductosIdMedioIdOrdenesXProductoTemp);
								}
							}
						}
					}
				}
			}
			System.out.println("mapClientesIdsOrdenesMedioXProducto size00: " + mapClientesIdProductosIdMedioIdOrdenesXProducto.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//END 30 JULIO
	
	
	//ADD 8 AGOSTO
	public void agruparOrdenesProductoByClienteTMediosProducto(){
		mapClientesIdMediosIdProductosIdOrdenesXProducto.clear();
		
		try {
			Iterator ordenesMedioXProductoIt = ordenesMedioXProductoColl.iterator();
			while (ordenesMedioXProductoIt.hasNext()){
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioXProductoIt.next();
				Collection<ClienteIf> clienteIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getClienteOficinaId());
				
				if (!clienteIfColl.isEmpty()){
					ClienteIf clienteTempIf = clienteIfColl.iterator().next();
					Long productoClienteId = ordenMedioIf.getProductoClienteId();
					Collection<ClienteIf> medioIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getProveedorId());
					
					if (!medioIfColl.isEmpty()){
						ClienteIf medioTempIf = medioIfColl.iterator().next();
						
						Map<Long,Map<Long,ArrayList<OrdenMedioIf>>> mapMedioIdProductosIdOrdenesXProductoTemp; 
						Map<Long,ArrayList<OrdenMedioIf>> mapProductoIdOrdenesXProductoTemp;
						ArrayList<OrdenMedioIf> listOrdenes;
						
						if (!mapClientesIdMediosIdProductosIdOrdenesXProducto.containsKey(clienteTempIf.getId())){
							mapMedioIdProductosIdOrdenesXProductoTemp = new LinkedHashMap<Long, Map<Long,ArrayList<OrdenMedioIf>>>();
							mapProductoIdOrdenesXProductoTemp = new LinkedHashMap<Long, ArrayList<OrdenMedioIf>>();
							listOrdenes = new ArrayList<OrdenMedioIf>();
												
							listOrdenes.add(ordenMedioIf);
							mapProductoIdOrdenesXProductoTemp.put(productoClienteId, listOrdenes);
							mapMedioIdProductosIdOrdenesXProductoTemp.put(medioTempIf.getId(),mapProductoIdOrdenesXProductoTemp);
							mapClientesIdMediosIdProductosIdOrdenesXProducto.put(clienteTempIf.getId(),mapMedioIdProductosIdOrdenesXProductoTemp);
							
						}else {
							mapMedioIdProductosIdOrdenesXProductoTemp = mapClientesIdMediosIdProductosIdOrdenesXProducto.get(clienteTempIf.getId());
							
							if (!mapMedioIdProductosIdOrdenesXProductoTemp.containsKey(medioTempIf.getId())){
								mapProductoIdOrdenesXProductoTemp = new LinkedHashMap<Long, ArrayList<OrdenMedioIf>>();
								listOrdenes = new ArrayList<OrdenMedioIf>();
								
								listOrdenes.add(ordenMedioIf);
								mapProductoIdOrdenesXProductoTemp.put(productoClienteId, listOrdenes);
								//mapProductosIdMedioIdOrdenesXProductoTemp.remove(mapMedioIdOrdenesXProductoTemp);	
								mapMedioIdProductosIdOrdenesXProductoTemp.put(medioTempIf.getId(), mapProductoIdOrdenesXProductoTemp);
								mapClientesIdMediosIdProductosIdOrdenesXProducto.remove(clienteTempIf.getId());
								mapClientesIdMediosIdProductosIdOrdenesXProducto.put(clienteTempIf.getId(),mapMedioIdProductosIdOrdenesXProductoTemp);
							}else{
								mapProductoIdOrdenesXProductoTemp = mapMedioIdProductosIdOrdenesXProductoTemp.get(medioTempIf.getId());
								if (!mapProductoIdOrdenesXProductoTemp.containsKey(productoClienteId)){
									listOrdenes = new ArrayList<OrdenMedioIf>();
									
									listOrdenes.add(ordenMedioIf);
									mapProductoIdOrdenesXProductoTemp.put(productoClienteId, listOrdenes);
									mapMedioIdProductosIdOrdenesXProductoTemp.remove(medioTempIf.getId());	
									mapMedioIdProductosIdOrdenesXProductoTemp.put(medioTempIf.getId(), mapProductoIdOrdenesXProductoTemp);
									mapClientesIdMediosIdProductosIdOrdenesXProducto.remove(clienteTempIf.getId());
									mapClientesIdMediosIdProductosIdOrdenesXProducto.put(clienteTempIf.getId(),mapMedioIdProductosIdOrdenesXProductoTemp);
								}else{
									listOrdenes = mapProductoIdOrdenesXProductoTemp.get(productoClienteId);
									listOrdenes.add(ordenMedioIf);
									
									mapProductoIdOrdenesXProductoTemp.remove(productoClienteId);
									mapProductoIdOrdenesXProductoTemp.put(productoClienteId, listOrdenes);
									mapMedioIdProductosIdOrdenesXProductoTemp.remove(medioTempIf.getId());	
									mapMedioIdProductosIdOrdenesXProductoTemp.put(medioTempIf.getId(), mapProductoIdOrdenesXProductoTemp);
									mapClientesIdMediosIdProductosIdOrdenesXProducto.remove(clienteTempIf.getId());
									mapClientesIdMediosIdProductosIdOrdenesXProducto.put(clienteTempIf.getId(),mapMedioIdProductosIdOrdenesXProductoTemp);
								}
							}
						}
					}
				}
			}
			//System.out.println("mapClientesIdMediosIdProductosIdOrdenesXProducto size00: " + mapClientesIdMediosIdProductosIdOrdenesXProducto.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//END 8 AGOSTO
	
	//ADD 8 AGOSTO
	public void agruparOrdenesProductoByTMediosClienteProducto(){
		mapMediosIdClientesIdProductosIdOrdenesXProducto.clear();
		
		try {
			Iterator ordenesMedioXProductoIt = ordenesMedioXProductoColl.iterator();
			while (ordenesMedioXProductoIt.hasNext()){
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioXProductoIt.next();
				Collection<ClienteIf> medioIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getProveedorId());
				
				if (!medioIfColl.isEmpty()){
					ClienteIf medioTempIf = medioIfColl.iterator().next();
					Long productoClienteId = ordenMedioIf.getProductoClienteId();
					Collection<ClienteIf> clienteIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getClienteOficinaId());
					
					if (!clienteIfColl.isEmpty()){
						ClienteIf clienteTempIf = clienteIfColl.iterator().next();
						
						Map<Long,Map<Long,ArrayList<OrdenMedioIf>>> mapClienteIdProductosIdOrdenesXProductoTemp; 
						Map<Long,ArrayList<OrdenMedioIf>> mapProductoIdOrdenesXProductoTemp;
						ArrayList<OrdenMedioIf> listOrdenes;
						
						if (!mapMediosIdClientesIdProductosIdOrdenesXProducto.containsKey(medioTempIf.getId())){
							mapClienteIdProductosIdOrdenesXProductoTemp = new LinkedHashMap<Long, Map<Long,ArrayList<OrdenMedioIf>>>();
							mapProductoIdOrdenesXProductoTemp = new LinkedHashMap<Long, ArrayList<OrdenMedioIf>>();
							listOrdenes = new ArrayList<OrdenMedioIf>();
												
							listOrdenes.add(ordenMedioIf);
							mapProductoIdOrdenesXProductoTemp.put(productoClienteId, listOrdenes);
							mapClienteIdProductosIdOrdenesXProductoTemp.put(clienteTempIf.getId(),mapProductoIdOrdenesXProductoTemp);
							mapMediosIdClientesIdProductosIdOrdenesXProducto.put(medioTempIf.getId(),mapClienteIdProductosIdOrdenesXProductoTemp);
							
						}else {
							mapClienteIdProductosIdOrdenesXProductoTemp = mapMediosIdClientesIdProductosIdOrdenesXProducto.get(medioTempIf.getId());
							
							if (!mapClienteIdProductosIdOrdenesXProductoTemp.containsKey(clienteTempIf.getId())){
								mapProductoIdOrdenesXProductoTemp = new LinkedHashMap<Long, ArrayList<OrdenMedioIf>>();
								listOrdenes = new ArrayList<OrdenMedioIf>();
								
								listOrdenes.add(ordenMedioIf);
								mapProductoIdOrdenesXProductoTemp.put(productoClienteId, listOrdenes);
								//mapProductosIdMedioIdOrdenesXProductoTemp.remove(mapMedioIdOrdenesXProductoTemp);	
								mapClienteIdProductosIdOrdenesXProductoTemp.put(clienteTempIf.getId(), mapProductoIdOrdenesXProductoTemp);
								mapMediosIdClientesIdProductosIdOrdenesXProducto.remove(medioTempIf.getId());
								mapMediosIdClientesIdProductosIdOrdenesXProducto.put(medioTempIf.getId(),mapClienteIdProductosIdOrdenesXProductoTemp);
							}else{
								mapProductoIdOrdenesXProductoTemp = mapClienteIdProductosIdOrdenesXProductoTemp.get(clienteTempIf.getId());
								if (!mapProductoIdOrdenesXProductoTemp.containsKey(productoClienteId)){
									listOrdenes = new ArrayList<OrdenMedioIf>();
									
									listOrdenes.add(ordenMedioIf);
									mapProductoIdOrdenesXProductoTemp.put(productoClienteId, listOrdenes);
									mapClienteIdProductosIdOrdenesXProductoTemp.remove(clienteTempIf.getId());	
									mapClienteIdProductosIdOrdenesXProductoTemp.put(clienteTempIf.getId(), mapProductoIdOrdenesXProductoTemp);
									mapMediosIdClientesIdProductosIdOrdenesXProducto.remove(medioTempIf.getId());
									mapMediosIdClientesIdProductosIdOrdenesXProducto.put(medioTempIf.getId(),mapClienteIdProductosIdOrdenesXProductoTemp);
								}else{
									listOrdenes = mapProductoIdOrdenesXProductoTemp.get(productoClienteId);
									listOrdenes.add(ordenMedioIf);
									
									mapProductoIdOrdenesXProductoTemp.remove(productoClienteId);
									mapProductoIdOrdenesXProductoTemp.put(productoClienteId, listOrdenes);
									mapClienteIdProductosIdOrdenesXProductoTemp.remove(clienteTempIf.getId());	
									mapClienteIdProductosIdOrdenesXProductoTemp.put(clienteTempIf.getId(), mapProductoIdOrdenesXProductoTemp);
									mapMediosIdClientesIdProductosIdOrdenesXProducto.remove(medioTempIf.getId());
									mapMediosIdClientesIdProductosIdOrdenesXProducto.put(medioTempIf.getId(),mapClienteIdProductosIdOrdenesXProductoTemp);
								}
							}
						}
					}
				}
			}
			//System.out.println("mapMediosIdClientesIdProductosIdOrdenesXProducto size00: " + mapMediosIdClientesIdProductosIdOrdenesXProducto.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cargarMapaOrdenesMedio(){
		try {
			mapaOrdenesMedio.clear();
			mapaOrdenIdValorNotaCreditoError.clear();
			mapaOrdenIdValorNotaCreditoGanancia.clear();
			//Map test = new HashMap();
			
			Iterator ordenesMedioDetalleXProductoIt = ordenesMedioDetalleXProductoColl.iterator();
			while (ordenesMedioDetalleXProductoIt.hasNext()){
				Object[] ordenesMedioDetalleXProductoCollObj = (Object[])ordenesMedioDetalleXProductoIt.next();
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenesMedioDetalleXProductoCollObj[0];
				PlanMedioIf planMedioIf = (PlanMedioIf)ordenesMedioDetalleXProductoCollObj[3];
				//test.put(planMedioIf.getId(), planMedioIf);
				
				//busco las notas de credito detalle por presupuesto id y por orden id
				//Con esto armo mapas para NC tipo Error y tipo Ganancia.
				if(mapaOrdenIdValorNotaCreditoError.get(ordenMedioIf.getPlanMedioId()) == null){
					double subtotalError = 0D;
					double subtotalGanancia = 0D;
					Map aMap = new HashMap();
					aMap.put("tipoReferencia", "F");
					aMap.put("tipoPresupuesto", "I");
					aMap.put("presupuestoId", ordenMedioIf.getPlanMedioId());
					aMap.put("ordenId", ordenMedioIf.getId());
					String[] tiposNota = {"E", "G"}; //ERROR Y GANANCIA
					Collection notasCreditoDetalle = SessionServiceLocator.getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByQueryAndByEstados(aMap, tiposNota);
					Iterator notasCreditoDetalleIt = notasCreditoDetalle.iterator();
					while(notasCreditoDetalleIt.hasNext()){
						NotaCreditoDetalleIf notaCreditoDetalle = (NotaCreditoDetalleIf)notasCreditoDetalleIt.next();
						if(notaCreditoDetalle.getTipoNota().equals("E")){
							subtotalError = subtotalError + (notaCreditoDetalle.getValor().doubleValue() * notaCreditoDetalle.getCantidad().doubleValue());
						}else if(notaCreditoDetalle.getTipoNota().equals("G")){
							subtotalGanancia = subtotalGanancia + (notaCreditoDetalle.getValor().doubleValue() * notaCreditoDetalle.getCantidad().doubleValue());
						}
					}
					
					if(subtotalError > 0D){
						mapaOrdenIdValorNotaCreditoError.put(ordenMedioIf.getId(), subtotalError);
					}
					
					if(subtotalGanancia > 0D){
						mapaOrdenIdValorNotaCreditoGanancia.put(ordenMedioIf.getId(), subtotalGanancia);
					}
				}		
				
				
				//si se eligio una oficina empresa especifica entonces solo se presentara las ordenes de medio
				//ingresados por usuarios de esa oficina
				if(!getCmbOficinaEmpresa().getSelectedItem().equals("TODOS")){
					OficinaIf oficinaEmpresa = (OficinaIf)getCmbOficinaEmpresa().getSelectedItem();
					
					UsuarioIf usuarioOrden = mapaUsuario.get(ordenMedioIf.getUsuarioId());
					EmpleadoIf empleadoOrden = mapaEmpleado.get(usuarioOrden.getEmpleadoId());
					
					if(empleadoOrden.getOficinaId().compareTo(oficinaEmpresa.getId()) == 0){
						mapaOrdenesMedio.put(ordenMedioIf.getId(),ordenMedioIf);
					}
					
				}else{
					mapaOrdenesMedio.put(ordenMedioIf.getId(),ordenMedioIf);
				}
				
			}		
			
			//System.out.println("x");
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}	
	
	//Metodo agrupa la collection de ordenes 
	public void agruparOrdenesMedioDetalleGeneralByClienteProductoTMediosMedio(){	
		
		String tipoPautaTemp = "";
		mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.clear();
		
		try {			
			Iterator ordenesMedioDetalleXProductoIt = ordenesMedioDetalleXProductoColl.iterator();
			while (ordenesMedioDetalleXProductoIt.hasNext()){
				Object[] ordenesMedioDetalleXProductoCollObj = (Object[])ordenesMedioDetalleXProductoIt.next();
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXProductoCollObj[1];
						
				OrdenMedioIf ordenMedioIf = mapaOrdenesMedio.get(ordenMedioDetalleIf.getOrdenMedioId());
				
				if(ordenMedioIf != null){
					ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(ordenMedioIf.getClienteOficinaId());
					ClienteIf clienteTempIf = mapaCliente.get(clienteOficinaTempIf.getClienteId());
					
					if (clienteTempIf != null){
										
						ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(ordenMedioIf.getProveedorId());
						ClienteIf medioTempIf = mapaCliente.get(medioOficinaTempIf.getClienteId());
											
						if (medioTempIf != null){
														
							ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(ordenMedioDetalleIf.getProductoClienteId());
							Long marcaProductoId = productoClienteTempIf.getMarcaProductoId();
							Long tipoProveedorId = medioTempIf.getTipoproveedorId();
							TipoProveedorIf tipoProveedor = mapaTipoMedio.get(tipoProveedorId);
							
							//Solo si el proveedor es null es porque no es de medios
							if(tipoProveedor != null){
								
								if (ordenMedioDetalleIf.getPauta().compareTo(PAUTA_REGULAR) == 0)
									tipoPautaTemp = NOMBRE_PAUTA_REGULAR;
								else
									tipoPautaTemp = NOMBRE_AUSPICIO;
								
								ComercialIf comercialIf = (ComercialIf) ordenesMedioDetalleXProductoCollObj[2];
								Long campanaId = comercialIf.getCampanaId();
								Long derechoProgramaId = comercialIf.getDerechoprogramaId();
														
								Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>> mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>> mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>> mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>> mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>> mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>> mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>> mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>> mapPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<Long,ArrayList<OrdenMedioDetalleIf>> mapDerechoProgramaIdsOrdenesMedioDetalle;	
								ArrayList<OrdenMedioDetalleIf> listOrdenesMedioDetalle;
										
								if (!mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.containsKey(clienteTempIf.getId())){
									
									mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>>();
									mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>();
									mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>();
									mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>();
									mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>();
									mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
									mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
									mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
									mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
									listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
									
									listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
									mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
									mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
									mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId,mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
								}
								else {
									mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.get(clienteTempIf.getId());
											
									if (!mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(clienteOficinaTempIf.getId())){
										mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>();
										mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>();
										mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>();
										mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>();
										mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
										mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
										mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
										mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
										listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
																					
										listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
										mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
										mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
										mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId,mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
										mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
									}
									else{
										mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.get(clienteOficinaTempIf.getId());
										if (!mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(marcaProductoId)){
											mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>();
											mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>();
											mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>();
											mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
											mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
											mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
											mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
											listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
											mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
											mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId,mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
											mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
											mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
										}
										else{
											mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.get(marcaProductoId);
											if (!mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(productoClienteTempIf.getId())){
												mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>();
												mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>();
												mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
												mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
												mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
												mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
												listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
													
												listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
												mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
												mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
												mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId,mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
												mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
												mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
												mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
											}
											else{
												mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.get(productoClienteTempIf.getId());
												if (!mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(campanaId)){
													mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>();
													mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
													mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
													mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
													mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
													listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
													
													listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
													mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
													mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
													mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId,mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(productoClienteTempIf.getId());
													mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
													mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
													mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
													mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
												}
												else{
													mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.get(campanaId);
													if (!mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(tipoProveedorId)){
														mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
														mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
														mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
														mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
														listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
														
														listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
														mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
														mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
														mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId,mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(campanaId);
														mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(productoClienteTempIf.getId());
														mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
														mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
														mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
														mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
													}										
													else{
														mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.get(tipoProveedorId);
														if (!mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(medioTempIf.getId())){
															mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
															mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
															mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
															listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
														
															listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
															mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
															mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
															mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoProveedorId);
															mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId,mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(campanaId);
															mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(productoClienteTempIf.getId());
															mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
															mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
															mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
															mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
														}
														else{
															mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle = mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.get(medioTempIf.getId());
															if (!mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(medioOficinaTempIf.getId())){
																mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
																mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
																listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
															
																listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
																mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
																mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
																mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());														
																mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoProveedorId);
																mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId,mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(campanaId);
																mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(productoClienteTempIf.getId());
																mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
																mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
																mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
																mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
															}
															else{
																mapPautaDerechoProgramaIdsOrdenesMedioDetalle = mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.get(medioOficinaTempIf.getId());
																if (!mapPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(tipoPautaTemp)){
																	mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
																	listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
																	
																	listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
																	mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
																	mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
																	mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
																	mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());														
																	mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoProveedorId);
																	mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId,mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(campanaId);
																	mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(productoClienteTempIf.getId());
																	mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
																	mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
																	mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
																	mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																}
																else{
																	mapDerechoProgramaIdsOrdenesMedioDetalle = mapPautaDerechoProgramaIdsOrdenesMedioDetalle.get(tipoPautaTemp);
																	if (!mapDerechoProgramaIdsOrdenesMedioDetalle.containsKey(derechoProgramaId)){
																		listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
																		
																		listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
																		mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
																		mapPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoPautaTemp);
																		mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
																		mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
																		mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());														
																		mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoProveedorId);
																		mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId,mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(campanaId);
																		mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(productoClienteTempIf.getId());
																		mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
																		mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
																		mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
																		mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	}
																	else{
																		listOrdenesMedioDetalle = mapDerechoProgramaIdsOrdenesMedioDetalle.get(derechoProgramaId);
																														
																		listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
																		mapDerechoProgramaIdsOrdenesMedioDetalle.remove(derechoProgramaId);
																		mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
																		mapPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoPautaTemp);
																		mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
																		mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
																		mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());														
																		mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoProveedorId);
																		mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId,mapMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(campanaId);
																		mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(productoClienteTempIf.getId());
																		mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
																		mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
																		mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
																		mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	}
																}
															}
														}
													}						
												}
											}
										}
									}
								}
							}						
						}
					}
				}			
			}			
			System.out.println("mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList size00: " + mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Metodo agrupa la collection de ordenes 
	public void agruparOrdenesMedioDetalleGeneralByTMediosMedioClienteProducto(){	
		
		String tipoPautaTemp = "";
		mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.clear();
		
		try {			
			Iterator ordenesMedioDetalleXProductoIt = ordenesMedioDetalleXProductoColl.iterator();
			while (ordenesMedioDetalleXProductoIt.hasNext()){
				Object[] ordenesMedioDetalleXProductoCollObj = (Object[])ordenesMedioDetalleXProductoIt.next();
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXProductoCollObj[1];
						
				OrdenMedioIf ordenMedioIf = mapaOrdenesMedio.get(ordenMedioDetalleIf.getOrdenMedioId());
				
				if(ordenMedioIf != null){
					ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(ordenMedioIf.getClienteOficinaId());
					ClienteIf clienteTempIf = mapaCliente.get(clienteOficinaTempIf.getClienteId());
					
					if (clienteTempIf != null){
										
						ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(ordenMedioIf.getProveedorId());
						ClienteIf medioTempIf = mapaCliente.get(medioOficinaTempIf.getClienteId());
											
						if (medioTempIf != null){
														
							ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(ordenMedioDetalleIf.getProductoClienteId());
							Long marcaProductoId = productoClienteTempIf.getMarcaProductoId();
							Long tipoProveedorId = medioTempIf.getTipoproveedorId();
							TipoProveedorIf tipoProveedor = mapaTipoMedio.get(tipoProveedorId);
							
							//Solo si el proveedor es null es porque no es de medios
							if(tipoProveedor != null){
								
								if (ordenMedioDetalleIf.getPauta().compareTo(PAUTA_REGULAR) == 0)
									tipoPautaTemp = NOMBRE_PAUTA_REGULAR;
								else
									tipoPautaTemp = NOMBRE_AUSPICIO;
								
								ComercialIf comercialIf = (ComercialIf) ordenesMedioDetalleXProductoCollObj[2];
								Long campanaId = comercialIf.getCampanaId();							
								Long derechoProgramaId = comercialIf.getDerechoprogramaId();
														
								Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>> mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>> mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>> mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>> mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>> mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>> mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>> mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>> mapPautaDerechoProgramaIdsOrdenesMedioDetalle;
								Map<Long,ArrayList<OrdenMedioDetalleIf>> mapDerechoProgramaIdsOrdenesMedioDetalle;	
								ArrayList<OrdenMedioDetalleIf> listOrdenesMedioDetalle;
										
								if (!mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.containsKey(tipoProveedorId)){
									
									mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>>();
									mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>();
									mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>();
									mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>();
									mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>();
									mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
									mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
									mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
									mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
									listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
									
									listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
									mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
									mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
									mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteTempIf.getId(), mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(tipoProveedorId, mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
								}
								else {
									mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.get(tipoProveedorId);
											
									if (!mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(medioTempIf.getId())){
										mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>();
										mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>();
										mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>();
										mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>();
										mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
										mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
										mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
										mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
										listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
																					
										listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
										mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
										mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
										mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteTempIf.getId(), mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(tipoProveedorId);
										mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(tipoProveedorId, mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
									}
									else{
										mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.get(medioTempIf.getId());
										if (!mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(medioOficinaTempIf.getId())){
											mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>();
											mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>();
											mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>();
											mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
											mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
											mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
											mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
											listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
											mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
											mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteTempIf.getId(), mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
											mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(tipoProveedorId);
											mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(tipoProveedorId, mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
										}
										else{
											mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.get(medioOficinaTempIf.getId());
											if (!mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(clienteTempIf.getId())){
												mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>();
												mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>();
												mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
												mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
												mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
												mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
												listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
													
												listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
												mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
												mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
												mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteTempIf.getId(), mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
												mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
												mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(tipoProveedorId);
												mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(tipoProveedorId, mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
											}
											else{
												mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.get(clienteTempIf.getId());
												if (!mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(clienteOficinaTempIf.getId())){
													mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>();
													mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
													mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
													mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
													mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
													listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
													
													listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
													mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
													mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
													mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteTempIf.getId());
													mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteTempIf.getId(), mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
													mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
													mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(tipoProveedorId);
													mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(tipoProveedorId, mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
												}
												else{
													mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.get(clienteOficinaTempIf.getId());
													if (!mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(marcaProductoId)){
														mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
														mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
														mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
														mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
														listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
														
														listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
														mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
														mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
														mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
														mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteTempIf.getId());
														mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteTempIf.getId(), mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
														mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
														mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(tipoProveedorId);
														mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(tipoProveedorId, mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
													}										
													else{
														mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.get(marcaProductoId);
														if (!mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(productoClienteTempIf.getId())){
															mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
															mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
															mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
															listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
														
															listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
															mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
															mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
															mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
															mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
															mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteTempIf.getId());
															mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteTempIf.getId(), mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
															mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
															mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(tipoProveedorId);
															mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(tipoProveedorId, mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
														}
														else{
															mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle = mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.get(productoClienteTempIf.getId());
															if (!mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(campanaId)){
																mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
																mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
																listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
															
																listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
																mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
																mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
																mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(productoClienteTempIf.getId());
																mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
																mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
																mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteTempIf.getId());
																mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteTempIf.getId(), mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
																mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
																mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(tipoProveedorId);
																mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(tipoProveedorId, mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
															}
															else{
																mapPautaDerechoProgramaIdsOrdenesMedioDetalle = mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.get(campanaId);
																if (!mapPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(tipoPautaTemp)){
																	mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
																	listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
																	
																	listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
																	mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
																	mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
																	mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(campanaId);
																	mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(productoClienteTempIf.getId());
																	mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
																	mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
																	mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteTempIf.getId());
																	mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteTempIf.getId(), mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
																	mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
																	mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(tipoProveedorId);
																	mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(tipoProveedorId, mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																}
																else{
																	mapDerechoProgramaIdsOrdenesMedioDetalle = mapPautaDerechoProgramaIdsOrdenesMedioDetalle.get(tipoPautaTemp);
																	if (!mapDerechoProgramaIdsOrdenesMedioDetalle.containsKey(derechoProgramaId)){
																		listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
																		
																		listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
																		mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
																		mapPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoPautaTemp);
																		mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
																		mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(campanaId);
																		mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(productoClienteTempIf.getId());
																		mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
																		mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
																		mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteTempIf.getId());
																		mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteTempIf.getId(), mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
																		mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
																		mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(tipoProveedorId);
																		mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(tipoProveedorId, mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	}
																	else{
																		listOrdenesMedioDetalle = mapDerechoProgramaIdsOrdenesMedioDetalle.get(derechoProgramaId);
																														
																		listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
																		mapDerechoProgramaIdsOrdenesMedioDetalle.remove(derechoProgramaId);
																		mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
																		mapPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoPautaTemp);
																		mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
																		mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(campanaId);
																		mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(campanaId, mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(productoClienteTempIf.getId());
																		mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
																		mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId, mapProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
																		mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteTempIf.getId());
																		mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteTempIf.getId(), mapClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
																		mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
																		mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																		mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(tipoProveedorId);
																		mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(tipoProveedorId, mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaPautaDerechoProgramaIdsOrdenesMedioDetalle);
																	}
																}
															}
														}
													}						
												}
											}
										}
									}
								}
							}						
						}
					}
				}				
			}			
			System.out.println("mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList size00: " + mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	//Metodo agrupa la collection de ordenes x CLIENTE/T-MEDIO/MEDIO/PRODUCTO
	public void agruparOrdenesMedioDetalleGeneralByClienteTMediosMedioProducto(){	
		
		String tipoPautaTemp = "";
		//ADD 23 AGOSTO
		mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.clear();
		
		try {			
			Iterator ordenesMedioDetalleXProductoIt = ordenesMedioDetalleXProductoColl.iterator();
			while (ordenesMedioDetalleXProductoIt.hasNext()){
				Object[] ordenesMedioDetalleXProductoCollObj = (Object[])ordenesMedioDetalleXProductoIt.next();
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXProductoCollObj[1];
						
				//ADD 11 AGOSTO
				OrdenMedioIf ordenMedioIf = mapaOrdenesMedio.get(ordenMedioDetalleIf.getOrdenMedioId());
				ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(ordenMedioIf.getClienteOficinaId());
				ClienteIf clienteTempIf = mapaCliente.get(clienteOficinaTempIf.getClienteId());
				
				if (clienteTempIf != null){
									
					//ADD 11 AGOSTO
					//Long idMedioOficina = ordenMedioIf.getProveedorId();
					ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(ordenMedioIf.getProveedorId());
					ClienteIf medioTempIf = mapaCliente.get(medioOficinaTempIf.getClienteId());
										
					if (medioTempIf != null){
													
						//ADD 15 AGOSTO
						ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(ordenMedioDetalleIf.getProductoClienteId());
						Long marcaProductoId = productoClienteTempIf.getMarcaProductoId();
						Long tipoProveedorId = medioTempIf.getTipoproveedorId();
						TipoProveedorIf tipoProveedor = mapaTipoMedio.get(tipoProveedorId);
						
						//Solo si el proveedor es tipo de medios solo toma en cuenta para los registros
						if(tipoProveedor != null){

							if (ordenMedioDetalleIf.getPauta().compareTo(PAUTA_REGULAR) == 0)
								tipoPautaTemp = NOMBRE_PAUTA_REGULAR;
							else
								tipoPautaTemp = NOMBRE_AUSPICIO;
							
							//ADD 16 AGOSTO
							ComercialIf comercialIf = (ComercialIf) ordenesMedioDetalleXProductoCollObj[2];
							Long derechoProgramaId = comercialIf.getDerechoprogramaId();
													
							//MODIFIED 16 ADD 15 AGOSTO
							Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>> mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle;
							Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>> mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle;
							Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>> mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle;
							Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>> mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle;
							Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>> mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle;
							Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>> mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle;
							Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>> mapPautaDerechoProgramaIdsOrdenesMedioDetalle;
							Map<Long,ArrayList<OrdenMedioDetalleIf>> mapDerechoProgramaIdsOrdenesMedioDetalle;	//ADD 16 AGOSTO
							ArrayList<OrdenMedioDetalleIf> listOrdenesMedioDetalle;
									
							if (!mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.containsKey(clienteTempIf.getId())){
								//ADD 15 AGOSTO
								mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>>();
								mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>();
								mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>();
								mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>();
								mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
								mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
								mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
								mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
								listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
								
								listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
								mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
								mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
								mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
								mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId,mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
								mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
								mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
								mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId, mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
								mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
								mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
							}else {
								mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.get(clienteTempIf.getId());
										
								if (!mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(clienteOficinaTempIf.getId())){
									mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>>();
									mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>();
									mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>();
									mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
									mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
									mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
									mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
									listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
									listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
									mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
									mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
									mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId,mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put( medioOficinaTempIf.getId(), mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId, mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
									mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
									mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
								}else{
									mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.get(clienteOficinaTempIf.getId());
									if (!mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(tipoProveedorId)){
										mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>>();
										mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>();
										mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
										mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
										mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
										mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
										listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
										
										listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
										mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
										mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
										mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId,mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId, mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
										mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
										mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
										mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
									}else{
										mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.get(tipoProveedorId);
										if (!mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(medioTempIf.getId())){
											mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>>();
											mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
											mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
											mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
											mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
											listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
												
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
											mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
											mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId,mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoProveedorId);
											mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId, mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
											mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
											mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
											mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
										}else{
											mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.get(medioTempIf.getId());
											if (!mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(medioOficinaTempIf.getId())){
												mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>>();
												mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
												mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
												mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
												listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
												
												listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
												mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
												mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
												mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId,mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
												mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoProveedorId);
												mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId, mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
												mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
												mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
												mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
											}else{
												mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.get(medioOficinaTempIf.getId());
												if (!mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(marcaProductoId)){
													mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, Map<String,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
													mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
													mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
													listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
													
													listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
													mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
													mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
													mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId,mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
													mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
													mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoProveedorId);
													mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId, mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
													mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
													mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
													mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
												}
												else{
													mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.get(marcaProductoId);
													if (!mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(productoClienteTempIf.getId())){
														mapPautaDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<String, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
														mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
														listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
														
														listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
														mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
														mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
														mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
														mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId,mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
														mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
														mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoProveedorId);
														mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId, mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
														mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
														mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
														mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
													}
													else{
														mapPautaDerechoProgramaIdsOrdenesMedioDetalle = mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.get(productoClienteTempIf.getId());
														if (!mapPautaDerechoProgramaIdsOrdenesMedioDetalle.containsKey(tipoPautaTemp)){
															mapDerechoProgramaIdsOrdenesMedioDetalle = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
															listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
																
															listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
															mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
															mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
															mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(productoClienteTempIf.getId());
															mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
															mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId,mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
															mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
															mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoProveedorId);
															mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId, mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
															mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
															mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
															mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
														}
														else{
															mapDerechoProgramaIdsOrdenesMedioDetalle = mapPautaDerechoProgramaIdsOrdenesMedioDetalle.get(tipoPautaTemp);
															if (!mapDerechoProgramaIdsOrdenesMedioDetalle.containsKey(derechoProgramaId)){
																listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
																	
																listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
																mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
																mapPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoPautaTemp);
																mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
																mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(productoClienteTempIf.getId());
																mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
																mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId,mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
																mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
																mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoProveedorId);
																mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId, mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
																mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
																mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
															}
															else{
																listOrdenesMedioDetalle = mapDerechoProgramaIdsOrdenesMedioDetalle.get(derechoProgramaId);
																													
																listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
																mapDerechoProgramaIdsOrdenesMedioDetalle.remove(derechoProgramaId);
																mapDerechoProgramaIdsOrdenesMedioDetalle.put(derechoProgramaId, listOrdenesMedioDetalle);
																mapPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoPautaTemp);
																mapPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoPautaTemp, mapDerechoProgramaIdsOrdenesMedioDetalle);
																mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(productoClienteTempIf.getId());
																mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(productoClienteTempIf.getId(), mapPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(marcaProductoId);
																mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(marcaProductoId,mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioOficinaTempIf.getId());
																mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioOficinaTempIf.getId(), mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(medioTempIf.getId());
																mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(medioTempIf.getId(), mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(tipoProveedorId);
																mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(tipoProveedorId, mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.remove(clienteOficinaTempIf.getId());
																mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.put(clienteOficinaTempIf.getId(), mapTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
																mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.remove(clienteTempIf.getId());
																mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.put(clienteTempIf.getId(),mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle);
															}
														}
													}
												}
											}						
										}
									}
								}
							}
						}
					}
				}
			}			
			System.out.println("mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList size00: " + mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.size());
			//return mapGeneralByClienteProductoTMedioIdsOrdenesMedioDetalleList; COMENTED 23 AGOSTO
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return null; //COMENTED 23 AGOSTO
		}
	}
	//END 23 AGOSTO

		
	//ADD 11 AGOSTO
	public void agruparOrdenesMedioDetalleProductoByClienteProductoTMediosMedioOficina(){
		//aumentar a esta lista el long de pa el id de cliente oficina
		//mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.clear(); //COMENTAR 11 AGOSTO
		//ADD 11 AGOSTO
		mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList.clear();
		
		try {
			Iterator ordenesMedioDetalleXProductoIt = ordenesMedioDetalleXProductoColl.iterator();
			while (ordenesMedioDetalleXProductoIt.hasNext()){
				Object[] ordenesMedioDetalleXProductoCollObj = (Object[])ordenesMedioDetalleXProductoIt.next();
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXProductoCollObj[1];
				
				//COMENTED 11 AGOSTO
				//OrdenMedioIf ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenMedioDetalleIf.getOrdenMedioId());
				//Collection<ClienteIf> clienteIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getClienteOficinaId());
							
				//ADD 11 AGOSTO
				OrdenMedioIf ordenMedioIf = mapaOrdenesMedio.get(ordenMedioDetalleIf.getOrdenMedioId());
				ClienteOficinaIf clienteOficinaId = mapaClienteOficina.get(ordenMedioIf.getClienteOficinaId());
				ClienteIf clienteTempIf = mapaCliente.get(clienteOficinaId.getClienteId());
				
				if (clienteTempIf != null){
					//ClienteIf clienteTempIf = clienteIfColl.iterator().next();
					//Long productoClienteId = ordenMedioIf.getProductoClienteId();
					
					//ADD 11 AGOSTO
					Long idMedioOficina = ordenMedioIf.getProveedorId();
					ClienteOficinaIf medioOficinaId = mapaClienteOficina.get(ordenMedioIf.getProveedorId());
					ClienteIf medioTempIf = mapaCliente.get(medioOficinaId.getClienteId());
					
					//COMENTED 11 JUNIO
					//Collection<ClienteIf> medioIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(idMedioOficina);
						
					if (medioTempIf != null){
						
						
						//ClienteIf medioTempIf = medioIfColl.iterator().next();
						
						//COMENTED 5 AGOSTO
						//ADD 1 AGOSTO
						/*Collection<OrdenMedioDetalleIf> ordenesMedioDetalleTempIfColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
						Iterator ordenesMedioDetalleXCanalIt = ordenesMedioDetalleTempIfColl.iterator();
						while(ordenesMedioDetalleXCanalIt.hasNext()){
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXCanalIt.next();*/
							Long productoClienteId = ordenMedioDetalleIf.getProductoClienteId();
							
							//COMENTED 5 AGOSTO
							//ADD 4 AGOSTO
							//if (productoClienteIf == null || productoClienteId.equals(productoClienteIf.getId())){
							
							//ADD 11 AGOSTO
							Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>> mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp; 
							
							Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>> mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp; 
							Map<Long,ArrayList<OrdenMedioDetalleIf>> mapMedioOficinaIdOrdenesMedioDetalleTemp; 
							ArrayList<OrdenMedioDetalleIf> listOrdenesMedioDetalle;
									
								if (!mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList.containsKey(clienteTempIf.getId())){
									mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>(); 
									mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
									mapMedioOficinaIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
									listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
															
									listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
									//ADD 11 AGOSTO
									mapMedioOficinaIdOrdenesMedioDetalleTemp.put(idMedioOficina,listOrdenesMedioDetalle);
									
									mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(),mapMedioOficinaIdOrdenesMedioDetalleTemp);
									mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(productoClienteId,mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
									mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList.put(clienteTempIf.getId(),mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
										
								}else {
									mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp = mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList.get(clienteTempIf.getId());
										
									if (!mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.containsKey(productoClienteId)){
										mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
										mapMedioOficinaIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
										listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
										listOrdenesMedioDetalle.add(ordenMedioDetalleIf);									
										mapMedioOficinaIdOrdenesMedioDetalleTemp.put(idMedioOficina, listOrdenesMedioDetalle);
										mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(),mapMedioOficinaIdOrdenesMedioDetalleTemp);
										mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(productoClienteId,mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
										mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList.remove(clienteTempIf.getId());
										mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList.put(clienteTempIf.getId(),mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
									}else{
										mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp = mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.get(productoClienteId);
										if (!mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.containsKey(medioTempIf.getId())){
											mapMedioOficinaIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
											listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapMedioOficinaIdOrdenesMedioDetalleTemp.put(idMedioOficina, listOrdenesMedioDetalle);
											mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(),mapMedioOficinaIdOrdenesMedioDetalleTemp);
											mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.remove(productoClienteId);	
											mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(productoClienteId, mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
											mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList.remove(clienteTempIf.getId());
											mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList.put(clienteTempIf.getId(),mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
										}else{
											mapMedioOficinaIdOrdenesMedioDetalleTemp = mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.get(medioTempIf.getId());
											if (!mapMedioOficinaIdOrdenesMedioDetalleTemp.containsKey(idMedioOficina)){
												listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
												
												listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
												mapMedioOficinaIdOrdenesMedioDetalleTemp.put(idMedioOficina, listOrdenesMedioDetalle);
												mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.remove(medioTempIf.getId());
												mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(),mapMedioOficinaIdOrdenesMedioDetalleTemp);
												mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.remove(productoClienteId);	
												mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(productoClienteId, mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
												mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList.remove(clienteTempIf.getId());
												mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList.put(clienteTempIf.getId(),mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
											}else{
											
												listOrdenesMedioDetalle = mapMedioOficinaIdOrdenesMedioDetalleTemp.get(idMedioOficina);
																								
												listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
												mapMedioOficinaIdOrdenesMedioDetalleTemp.remove(idMedioOficina);
												mapMedioOficinaIdOrdenesMedioDetalleTemp.put(idMedioOficina,listOrdenesMedioDetalle);
												mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.remove(medioTempIf.getId());
												mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(),mapMedioOficinaIdOrdenesMedioDetalleTemp);
												mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.remove(productoClienteId);	
												mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(productoClienteId, mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
												mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList.remove(clienteTempIf.getId());
												mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList.put(clienteTempIf.getId(),mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
											}
										}
									}
								}
							//}//ADD 4 AGOSTO//COMENTED 5 AGOSTO
						//}//END WHILE COMENTED 5 AGOSTO
					}
				}
			}
			System.out.println("mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList size00: " + mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleProductoList.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//END 11 AGOSTO
	
	
	//ADD 10 AGOSTO
	//SERIA BUENO UTILIZAR ESTE METODO PA LAS DOS TIPOS DE ORDENES Y LAS COLLECIONES SE LAS PASARIA X PARAMETRO Y RETURN
	public void agruparOrdenesMedioDetalleProductoByClienteProductoTMedios(){
		//aumentar a esta lista el long de pa el id de cliente oficina
		mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.clear();
		
		try {
			Iterator ordenesMedioDetalleXProductoIt = ordenesMedioDetalleXProductoColl.iterator();
			while (ordenesMedioDetalleXProductoIt.hasNext()){				
				Object[] ordenesMedioDetalleXProductoCollObj = (Object[])ordenesMedioDetalleXProductoIt.next();
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenesMedioDetalleXProductoCollObj[1];
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenesMedioDetalleXProductoCollObj[0];
				Collection<ClienteIf> clienteIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getClienteOficinaId());
							
				if (!clienteIfColl.isEmpty()){
					ClienteIf clienteTempIf = clienteIfColl.iterator().next();
					//Long productoClienteId = ordenMedioIf.getProductoClienteId();
					Collection<ClienteIf> medioIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getProveedorId());
						
					if (!medioIfColl.isEmpty()){
						ClienteIf medioTempIf = medioIfColl.iterator().next();
						
						//COMENTED 5 AGOSTO
						//ADD 1 AGOSTO
						/*Collection<OrdenMedioDetalleIf> ordenesMedioDetalleTempIfColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
						Iterator ordenesMedioDetalleXCanalIt = ordenesMedioDetalleTempIfColl.iterator();
						while(ordenesMedioDetalleXCanalIt.hasNext()){
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXCanalIt.next();*/
							Long productoClienteId = ordenMedioDetalleIf.getProductoClienteId();
							
							//COMENTED 5 AGOSTO
							//ADD 4 AGOSTO
							//if (productoClienteIf == null || productoClienteId.equals(productoClienteIf.getId())){
							
								Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>> mapProductosIdMedioIdOrdenesMedioDetalleTemp; 
								Map<Long,ArrayList<OrdenMedioDetalleIf>> mapMediosIdOrdenesMedioDetalleTemp; 
								ArrayList<OrdenMedioDetalleIf> listOrdenesMedioDetalle;
									
								if (!mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.containsKey(clienteTempIf.getId())){
									mapProductosIdMedioIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
									mapMediosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
									listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
															
									listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
									mapMediosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(),listOrdenesMedioDetalle);
									mapProductosIdMedioIdOrdenesMedioDetalleTemp.put(productoClienteId,mapMediosIdOrdenesMedioDetalleTemp);
									mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.put(clienteTempIf.getId(),mapProductosIdMedioIdOrdenesMedioDetalleTemp);
										
								}else {
									mapProductosIdMedioIdOrdenesMedioDetalleTemp = mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.get(clienteTempIf.getId());
										
									if (!mapProductosIdMedioIdOrdenesMedioDetalleTemp.containsKey(productoClienteId)){
										mapMediosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
										listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
										listOrdenesMedioDetalle.add(ordenMedioDetalleIf);									
										mapMediosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), listOrdenesMedioDetalle);
										mapProductosIdMedioIdOrdenesMedioDetalleTemp.put(productoClienteId, mapMediosIdOrdenesMedioDetalleTemp);
										mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.remove(clienteTempIf.getId());
										mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.put(clienteTempIf.getId(),mapProductosIdMedioIdOrdenesMedioDetalleTemp);
									}else{
										mapMediosIdOrdenesMedioDetalleTemp = mapProductosIdMedioIdOrdenesMedioDetalleTemp.get(productoClienteId);
										if (!mapMediosIdOrdenesMedioDetalleTemp.containsKey(medioTempIf.getId())){
											listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapMediosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), listOrdenesMedioDetalle);
											mapProductosIdMedioIdOrdenesMedioDetalleTemp.remove(productoClienteId);	
											mapProductosIdMedioIdOrdenesMedioDetalleTemp.put(productoClienteId, mapMediosIdOrdenesMedioDetalleTemp);
											mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.remove(clienteTempIf.getId());
											mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.put(clienteTempIf.getId(),mapProductosIdMedioIdOrdenesMedioDetalleTemp);
										}else{
												
											listOrdenesMedioDetalle = mapMediosIdOrdenesMedioDetalleTemp.get(medioTempIf.getId());
																							
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapMediosIdOrdenesMedioDetalleTemp.remove(medioTempIf.getId());
											mapMediosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), listOrdenesMedioDetalle);
											mapProductosIdMedioIdOrdenesMedioDetalleTemp.remove(productoClienteId);	
											mapProductosIdMedioIdOrdenesMedioDetalleTemp.put(productoClienteId, mapMediosIdOrdenesMedioDetalleTemp);
											mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.remove(clienteTempIf.getId());
											mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.put(clienteTempIf.getId(),mapProductosIdMedioIdOrdenesMedioDetalleTemp);
											
										}
									}
								}
							//}//ADD 4 AGOSTO//COMENTED 5 AGOSTO
						//}//END WHILE COMENTED 5 AGOSTO
					}
				}
			}
			System.out.println("mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList size00: " + mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//END 10 AGOSTO
	
	//ADD 11 AGOSTO
	public void agruparOrdenesMedioDetalleCanalByClienteProductoTMediosMedioOficina(){
		//mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.clear();
		mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList.clear();
		
		try {
			Iterator ordenesMedioDetalleXCanalIt = ordenesMedioDetalleXCanalColl.iterator();
			while (ordenesMedioDetalleXCanalIt.hasNext()){
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXCanalIt.next();
				OrdenMedioIf ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenMedioDetalleIf.getOrdenMedioId());
				Collection<ClienteIf> clienteIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getClienteOficinaId());
							
				if (!clienteIfColl.isEmpty()){
					ClienteIf clienteTempIf = clienteIfColl.iterator().next();
					//Long productoClienteId = ordenMedioIf.getProductoClienteId();
					
					//ADD 11 AGOSTO
					Long idMedioOficina = ordenMedioIf.getProveedorId();
					
					Collection<ClienteIf> medioIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(idMedioOficina);
						
					if (!medioIfColl.isEmpty()){
						ClienteIf medioTempIf = medioIfColl.iterator().next();
						
						//COMENTED 5 AGOSTO
						//ADD 1 AGOSTO
						/*Collection<OrdenMedioDetalleIf> ordenesMedioDetalleTempIfColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
						Iterator ordenesMedioDetalleXCanalIt = ordenesMedioDetalleTempIfColl.iterator();
						while(ordenesMedioDetalleXCanalIt.hasNext()){
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXCanalIt.next();*/
							Long productoClienteId = ordenMedioDetalleIf.getProductoClienteId();
							
							//COMENTED 5 AGOSTO
							//ADD 4 AGOSTO
							//if (productoClienteIf == null || productoClienteId.equals(productoClienteIf.getId())){
							
							Map<Long,Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>> mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp; 
							Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>> mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp; 
							Map<Long,ArrayList<OrdenMedioDetalleIf>> mapMedioOficinaIdOrdenesMedioDetalleTemp; 
							ArrayList<OrdenMedioDetalleIf> listOrdenesMedioDetalle;
									
								if (!mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList.containsKey(clienteTempIf.getId())){
									mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>>>();
									mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
									mapMedioOficinaIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
									listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
															
									listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
									mapMedioOficinaIdOrdenesMedioDetalleTemp.put(idMedioOficina,listOrdenesMedioDetalle);
									mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(),mapMedioOficinaIdOrdenesMedioDetalleTemp);
									mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(productoClienteId, mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
									mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList.put(clienteTempIf.getId(),mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
										
								}else {
									mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp = mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList.get(clienteTempIf.getId());
										
									if (!mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.containsKey(productoClienteId)){
										mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
										mapMedioOficinaIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
										listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
										listOrdenesMedioDetalle.add(ordenMedioDetalleIf);									
										mapMedioOficinaIdOrdenesMedioDetalleTemp.put(idMedioOficina, listOrdenesMedioDetalle);
										mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), mapMedioOficinaIdOrdenesMedioDetalleTemp);
										mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(productoClienteId, mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
										mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList.remove(clienteTempIf.getId());
										mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList.put(clienteTempIf.getId(),mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
									}else{
										mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp = mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.get(productoClienteId);
										if (!mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.containsKey(medioTempIf.getId())){
											mapMedioOficinaIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
											listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapMedioOficinaIdOrdenesMedioDetalleTemp.put(idMedioOficina, listOrdenesMedioDetalle);
											mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), mapMedioOficinaIdOrdenesMedioDetalleTemp);
											mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.remove(productoClienteId);	
											mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(productoClienteId, mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
											mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList.remove(clienteTempIf.getId());
											mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList.put(clienteTempIf.getId(),mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
										}else{
											mapMedioOficinaIdOrdenesMedioDetalleTemp = mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.get(medioTempIf.getId());
											
											if (!mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.containsKey(medioTempIf.getId())){
												mapMedioOficinaIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, ArrayList<OrdenMedioDetalleIf>>();
												listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
												
												listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
												mapMedioOficinaIdOrdenesMedioDetalleTemp.put(idMedioOficina, listOrdenesMedioDetalle);
												mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.remove(medioTempIf.getId());
												mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), mapMedioOficinaIdOrdenesMedioDetalleTemp);
												mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.remove(productoClienteId);	
												mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(productoClienteId, mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
												mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList.remove(clienteTempIf.getId());
												mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList.put(clienteTempIf.getId(),mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
											}else{
											
												listOrdenesMedioDetalle = mapMedioOficinaIdOrdenesMedioDetalleTemp.get(idMedioOficina);
												
												mapMedioOficinaIdOrdenesMedioDetalleTemp.remove(idMedioOficina);
												mapMedioOficinaIdOrdenesMedioDetalleTemp.put(idMedioOficina, listOrdenesMedioDetalle);
												mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.remove(medioTempIf.getId());
												mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), mapMedioOficinaIdOrdenesMedioDetalleTemp);
												mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.remove(productoClienteId);	
												mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp.put(productoClienteId, mapMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
												mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList.remove(clienteTempIf.getId());
												mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList.put(clienteTempIf.getId(),mapProductosIdMedioIdMedioOficinaIdOrdenesMedioDetalleTemp);
											}
										}
									}
								}
							//}//ADD 4 AGOSTO//COMENTED 5 AGOSTO
						//}//END WHILE COMENTED 5 AGOSTO
					}
				}
			}
			System.out.println("mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList size00: " + mapClientesIdProductosIdMedioIdMedioOficinaIdOrdenMedioDetalleCanalList.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//END 11 AGOSTO
	
	//ADD 5 AGOSTO
	//public void agruparOrdenesMedioDetalleByClienteProductoTMedios(){ //COMENTED 10 AGOSTO
	public void agruparOrdenesMedioDetalleCanalByClienteProductoTMedios(){
		mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.clear();
		
		try {
			Iterator ordenesMedioDetalleXCanalIt = ordenesMedioDetalleXCanalColl.iterator();
			while (ordenesMedioDetalleXCanalIt.hasNext()){
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXCanalIt.next();
				OrdenMedioIf ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenMedioDetalleIf.getOrdenMedioId());
				Collection<ClienteIf> clienteIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getClienteOficinaId());
							
				if (!clienteIfColl.isEmpty()){
					ClienteIf clienteTempIf = clienteIfColl.iterator().next();
					//Long productoClienteId = ordenMedioIf.getProductoClienteId();
					Collection<ClienteIf> medioIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getProveedorId());
						
					if (!medioIfColl.isEmpty()){
						ClienteIf medioTempIf = medioIfColl.iterator().next();
						
						//COMENTED 5 AGOSTO
						//ADD 1 AGOSTO
						/*Collection<OrdenMedioDetalleIf> ordenesMedioDetalleTempIfColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
						Iterator ordenesMedioDetalleXCanalIt = ordenesMedioDetalleTempIfColl.iterator();
						while(ordenesMedioDetalleXCanalIt.hasNext()){
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXCanalIt.next();*/
							Long productoClienteId = ordenMedioDetalleIf.getProductoClienteId();
							
							//COMENTED 5 AGOSTO
							//ADD 4 AGOSTO
							//if (productoClienteIf == null || productoClienteId.equals(productoClienteIf.getId())){
							
								Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>> mapProductosIdMedioIdOrdenesMedioDetalleTemp; 
								Map<Long,ArrayList<OrdenMedioDetalleIf>> mapMediosIdOrdenesMedioDetalleTemp; 
								ArrayList<OrdenMedioDetalleIf> listOrdenesMedioDetalle;
									
								if (!mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.containsKey(clienteTempIf.getId())){
									mapProductosIdMedioIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
									mapMediosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
									listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
															
									listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
									mapMediosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(),listOrdenesMedioDetalle);
									mapProductosIdMedioIdOrdenesMedioDetalleTemp.put(productoClienteId,mapMediosIdOrdenesMedioDetalleTemp);
									mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.put(clienteTempIf.getId(),mapProductosIdMedioIdOrdenesMedioDetalleTemp);
										
								}else {
									mapProductosIdMedioIdOrdenesMedioDetalleTemp = mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.get(clienteTempIf.getId());
										
									if (!mapProductosIdMedioIdOrdenesMedioDetalleTemp.containsKey(productoClienteId)){
										mapMediosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
										listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
										listOrdenesMedioDetalle.add(ordenMedioDetalleIf);									
										mapMediosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), listOrdenesMedioDetalle);
										mapProductosIdMedioIdOrdenesMedioDetalleTemp.put(productoClienteId, mapMediosIdOrdenesMedioDetalleTemp);
										mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.remove(clienteTempIf.getId());
										mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.put(clienteTempIf.getId(),mapProductosIdMedioIdOrdenesMedioDetalleTemp);
									}else{
										mapMediosIdOrdenesMedioDetalleTemp = mapProductosIdMedioIdOrdenesMedioDetalleTemp.get(productoClienteId);
										if (!mapMediosIdOrdenesMedioDetalleTemp.containsKey(medioTempIf.getId())){
											listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapMediosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), listOrdenesMedioDetalle);
											mapProductosIdMedioIdOrdenesMedioDetalleTemp.remove(productoClienteId);	
											mapProductosIdMedioIdOrdenesMedioDetalleTemp.put(productoClienteId, mapMediosIdOrdenesMedioDetalleTemp);
											mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.remove(clienteTempIf.getId());
											mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.put(clienteTempIf.getId(),mapProductosIdMedioIdOrdenesMedioDetalleTemp);
										}else{
												
											listOrdenesMedioDetalle = mapMediosIdOrdenesMedioDetalleTemp.get(medioTempIf.getId());
																							
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapMediosIdOrdenesMedioDetalleTemp.remove(medioTempIf.getId());
											mapMediosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), listOrdenesMedioDetalle);
											mapProductosIdMedioIdOrdenesMedioDetalleTemp.remove(productoClienteId);	
											mapProductosIdMedioIdOrdenesMedioDetalleTemp.put(productoClienteId, mapMediosIdOrdenesMedioDetalleTemp);
											mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.remove(clienteTempIf.getId());
											mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.put(clienteTempIf.getId(),mapProductosIdMedioIdOrdenesMedioDetalleTemp);
											
										}
									}
								}
							//}//ADD 4 AGOSTO//COMENTED 5 AGOSTO
						//}//END WHILE COMENTED 5 AGOSTO
					}
				}
			}
			System.out.println("mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList size00: " + mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//END 4 AGOSTO
	
	//ADD 11 AGOSTO
	public void agruparOrdenesMedioDetalleProductoByClienteTMediosProducto(){
		mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList.clear();
		
		try {
			Iterator ordenesMedioDetalleXProductoIt = ordenesMedioDetalleXProductoColl.iterator();
			while (ordenesMedioDetalleXProductoIt.hasNext()){
				Object[] ordenesMedioDetalleXProductoCollObj = (Object[])ordenesMedioDetalleXProductoIt.next();
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXProductoCollObj[1];
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioDetalleXProductoCollObj[0];
				Collection<ClienteIf> clienteIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getClienteOficinaId());
							
				if (!clienteIfColl.isEmpty()){
					ClienteIf clienteTempIf = clienteIfColl.iterator().next();
					//Long productoClienteId = ordenMedioIf.getProductoClienteId();
					Collection<ClienteIf> medioIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getProveedorId());
						
					if (!medioIfColl.isEmpty()){
						ClienteIf medioTempIf = medioIfColl.iterator().next();
						
						//COMENTED 5 AGOSTO
						//ADD 1 AGOSTO
						/*Collection<OrdenMedioDetalleIf> ordenesMedioDetalleTempIfColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
						Iterator ordenesMedioDetalleXCanalIt = ordenesMedioDetalleTempIfColl.iterator();
						while(ordenesMedioDetalleXCanalIt.hasNext()){
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXCanalIt.next();*/
							Long productoClienteId = ordenMedioDetalleIf.getProductoClienteId();
							
							//COMENTED 5 AGOSTO
							//ADD 4 AGOSTO
							//if (productoClienteIf == null || productoClienteId.equals(productoClienteIf.getId())){
							
								Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>> mapMedioIdProductosIdOrdenesMedioDetalleTemp; 
								Map<Long,ArrayList<OrdenMedioDetalleIf>> mapProductosIdOrdenesMedioDetalleTemp; 
								ArrayList<OrdenMedioDetalleIf> listOrdenesMedioDetalle;
									
								if (!mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList.containsKey(clienteTempIf.getId())){
									mapMedioIdProductosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
									mapProductosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
									listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
															
									listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
									mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId,listOrdenesMedioDetalle);
									mapMedioIdProductosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(),mapProductosIdOrdenesMedioDetalleTemp);
									mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList.put(clienteTempIf.getId(),mapMedioIdProductosIdOrdenesMedioDetalleTemp);
										
								}else {
									mapMedioIdProductosIdOrdenesMedioDetalleTemp = mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList.get(clienteTempIf.getId());
										
									if (!mapMedioIdProductosIdOrdenesMedioDetalleTemp.containsKey(medioTempIf.getId())){
										mapProductosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
										listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
										listOrdenesMedioDetalle.add(ordenMedioDetalleIf);									
										mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId, listOrdenesMedioDetalle);
										mapMedioIdProductosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), mapProductosIdOrdenesMedioDetalleTemp);
										mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList.remove(clienteTempIf.getId());
										mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList.put(clienteTempIf.getId(),mapMedioIdProductosIdOrdenesMedioDetalleTemp);
									}else{
										mapProductosIdOrdenesMedioDetalleTemp = mapMedioIdProductosIdOrdenesMedioDetalleTemp.get(medioTempIf.getId());
										if (!mapProductosIdOrdenesMedioDetalleTemp.containsKey(productoClienteId)){
											listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId, listOrdenesMedioDetalle);
											mapMedioIdProductosIdOrdenesMedioDetalleTemp.remove(medioTempIf.getId());	
											mapMedioIdProductosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), mapProductosIdOrdenesMedioDetalleTemp);
											mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList.remove(clienteTempIf.getId());
											mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList.put(clienteTempIf.getId(),mapMedioIdProductosIdOrdenesMedioDetalleTemp);
										}else{
												
											listOrdenesMedioDetalle = mapProductosIdOrdenesMedioDetalleTemp.get(productoClienteId);
																							
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapProductosIdOrdenesMedioDetalleTemp.remove(productoClienteId);
											mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId, listOrdenesMedioDetalle);
											mapMedioIdProductosIdOrdenesMedioDetalleTemp.remove(medioTempIf.getId());	
											mapMedioIdProductosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), mapProductosIdOrdenesMedioDetalleTemp);
											mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList.remove(clienteTempIf.getId());
											mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList.put(clienteTempIf.getId(),mapMedioIdProductosIdOrdenesMedioDetalleTemp);
											
										}
									}
								}
							//}//ADD 4 AGOSTO//COMENTED 5 AGOSTO
						//}//END WHILE COMENTED 5 AGOSTO
					}
				}
			}
			System.out.println("mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList size00: " + mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	//END 11 AGOSTO
		
	
	//ADD 8 AGOSTO
	//public void agruparOrdenesMedioDetalleByClienteTMediosProducto(){ COMENTED 10 AGOSTO
	public void agruparOrdenesMedioDetalleCanalByClienteTMediosProducto(){
		mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList.clear();
		
		try {
			Iterator ordenesMedioDetalleXCanalIt = ordenesMedioDetalleXCanalColl.iterator();
			while (ordenesMedioDetalleXCanalIt.hasNext()){
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXCanalIt.next();
				OrdenMedioIf ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenMedioDetalleIf.getOrdenMedioId());
				Collection<ClienteIf> clienteIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getClienteOficinaId());
							
				if (!clienteIfColl.isEmpty()){
					ClienteIf clienteTempIf = clienteIfColl.iterator().next();
					//Long productoClienteId = ordenMedioIf.getProductoClienteId();
					Collection<ClienteIf> medioIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getProveedorId());
						
					if (!medioIfColl.isEmpty()){
						ClienteIf medioTempIf = medioIfColl.iterator().next();
						
						//COMENTED 5 AGOSTO
						//ADD 1 AGOSTO
						/*Collection<OrdenMedioDetalleIf> ordenesMedioDetalleTempIfColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
						Iterator ordenesMedioDetalleXCanalIt = ordenesMedioDetalleTempIfColl.iterator();
						while(ordenesMedioDetalleXCanalIt.hasNext()){
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXCanalIt.next();*/
							Long productoClienteId = ordenMedioDetalleIf.getProductoClienteId();
							
							//COMENTED 5 AGOSTO
							//ADD 4 AGOSTO
							//if (productoClienteIf == null || productoClienteId.equals(productoClienteIf.getId())){
							
								Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>> mapMedioIdProductosIdOrdenesMedioDetalleTemp; 
								Map<Long,ArrayList<OrdenMedioDetalleIf>> mapProductosIdOrdenesMedioDetalleTemp; 
								ArrayList<OrdenMedioDetalleIf> listOrdenesMedioDetalle;
									
								if (!mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList.containsKey(clienteTempIf.getId())){
									mapMedioIdProductosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
									mapProductosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
									listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
															
									listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
									mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId,listOrdenesMedioDetalle);
									mapMedioIdProductosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(),mapProductosIdOrdenesMedioDetalleTemp);
									mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList.put(clienteTempIf.getId(),mapMedioIdProductosIdOrdenesMedioDetalleTemp);
										
								}else {
									mapMedioIdProductosIdOrdenesMedioDetalleTemp = mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList.get(clienteTempIf.getId());
										
									if (!mapMedioIdProductosIdOrdenesMedioDetalleTemp.containsKey(medioTempIf.getId())){
										mapProductosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
										listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
										listOrdenesMedioDetalle.add(ordenMedioDetalleIf);									
										mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId, listOrdenesMedioDetalle);
										mapMedioIdProductosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), mapProductosIdOrdenesMedioDetalleTemp);
										mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList.remove(clienteTempIf.getId());
										mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList.put(clienteTempIf.getId(),mapMedioIdProductosIdOrdenesMedioDetalleTemp);
									}else{
										mapProductosIdOrdenesMedioDetalleTemp = mapMedioIdProductosIdOrdenesMedioDetalleTemp.get(medioTempIf.getId());
										if (!mapProductosIdOrdenesMedioDetalleTemp.containsKey(productoClienteId)){
											listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId, listOrdenesMedioDetalle);
											mapMedioIdProductosIdOrdenesMedioDetalleTemp.remove(medioTempIf.getId());	
											mapMedioIdProductosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), mapProductosIdOrdenesMedioDetalleTemp);
											mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList.remove(clienteTempIf.getId());
											mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList.put(clienteTempIf.getId(),mapMedioIdProductosIdOrdenesMedioDetalleTemp);
										}else{
												
											listOrdenesMedioDetalle = mapProductosIdOrdenesMedioDetalleTemp.get(productoClienteId);
																							
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapProductosIdOrdenesMedioDetalleTemp.remove(productoClienteId);
											mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId, listOrdenesMedioDetalle);
											mapMedioIdProductosIdOrdenesMedioDetalleTemp.remove(medioTempIf.getId());	
											mapMedioIdProductosIdOrdenesMedioDetalleTemp.put(medioTempIf.getId(), mapProductosIdOrdenesMedioDetalleTemp);
											mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList.remove(clienteTempIf.getId());
											mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList.put(clienteTempIf.getId(),mapMedioIdProductosIdOrdenesMedioDetalleTemp);
											
										}
									}
								}
							//}//ADD 4 AGOSTO//COMENTED 5 AGOSTO
						//}//END WHILE COMENTED 5 AGOSTO
					}
				}
			}
			System.out.println("mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList size00: " + mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	//END 8 AGOSTO
	
	//ADD 11 AGOSTO
	public void agruparOrdenesMedioDetalleProductoByTMediosClienteProducto(){
		mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList.clear();
		
		try {
			Iterator ordenesMedioDetalleXProductoIt = ordenesMedioDetalleXProductoColl.iterator();
			while (ordenesMedioDetalleXProductoIt.hasNext()){
				Object[] ordenesMedioDetalleXProductoCollObj = (Object[])ordenesMedioDetalleXProductoIt.next();
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXProductoCollObj[1];
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioDetalleXProductoCollObj[0];
				Collection<ClienteIf> medioIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getProveedorId());
							
				if (!medioIfColl.isEmpty()){
					ClienteIf medioTempIf = medioIfColl.iterator().next();
					//Long productoClienteId = ordenMedioIf.getProductoClienteId();
					Collection<ClienteIf> clienteIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getClienteOficinaId());	
					
					if (!clienteIfColl.isEmpty()){
						ClienteIf clienteTempIf = clienteIfColl.iterator().next();
						
						//COMENTED 5 AGOSTO
						//ADD 1 AGOSTO
						/*Collection<OrdenMedioDetalleIf> ordenesMedioDetalleTempIfColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
						Iterator ordenesMedioDetalleXCanalIt = ordenesMedioDetalleTempIfColl.iterator();
						while(ordenesMedioDetalleXCanalIt.hasNext()){
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXCanalIt.next();*/
							Long productoClienteId = ordenMedioDetalleIf.getProductoClienteId();
							
							//COMENTED 5 AGOSTO
							//ADD 4 AGOSTO
							//if (productoClienteIf == null || productoClienteId.equals(productoClienteIf.getId())){
							
								Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>> mapClienteIdProductosIdOrdenesMedioDetalleTemp; 
								Map<Long,ArrayList<OrdenMedioDetalleIf>> mapProductosIdOrdenesMedioDetalleTemp; 
								ArrayList<OrdenMedioDetalleIf> listOrdenesMedioDetalle;
									
								if (!mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList.containsKey(medioTempIf.getId())){
									mapClienteIdProductosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
									mapProductosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
									listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
															
									listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
									mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId,listOrdenesMedioDetalle);
									mapClienteIdProductosIdOrdenesMedioDetalleTemp.put(clienteTempIf.getId(),mapProductosIdOrdenesMedioDetalleTemp);
									mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList.put(medioTempIf.getId(),mapClienteIdProductosIdOrdenesMedioDetalleTemp);
										
								}else {
									mapClienteIdProductosIdOrdenesMedioDetalleTemp = mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList.get(medioTempIf.getId());
										
									if (!mapClienteIdProductosIdOrdenesMedioDetalleTemp.containsKey(clienteTempIf.getId())){
										mapProductosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
										listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
										listOrdenesMedioDetalle.add(ordenMedioDetalleIf);									
										mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId, listOrdenesMedioDetalle);
										mapClienteIdProductosIdOrdenesMedioDetalleTemp.put(clienteTempIf.getId(), mapProductosIdOrdenesMedioDetalleTemp);
										mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList.remove(medioTempIf.getId());
										mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList.put(medioTempIf.getId(),mapClienteIdProductosIdOrdenesMedioDetalleTemp);
									}else{
										mapProductosIdOrdenesMedioDetalleTemp = mapClienteIdProductosIdOrdenesMedioDetalleTemp.get(clienteTempIf.getId());
										if (!mapProductosIdOrdenesMedioDetalleTemp.containsKey(productoClienteId)){
											listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId, listOrdenesMedioDetalle);
											mapClienteIdProductosIdOrdenesMedioDetalleTemp.remove(clienteTempIf.getId());	
											mapClienteIdProductosIdOrdenesMedioDetalleTemp.put(clienteTempIf.getId(), mapProductosIdOrdenesMedioDetalleTemp);
											mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList.remove(medioTempIf.getId());
											mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList.put(medioTempIf.getId(),mapClienteIdProductosIdOrdenesMedioDetalleTemp);
										}else{
												
											listOrdenesMedioDetalle = mapProductosIdOrdenesMedioDetalleTemp.get(productoClienteId);
																							
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapProductosIdOrdenesMedioDetalleTemp.remove(productoClienteId);
											mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId, listOrdenesMedioDetalle);
											mapClienteIdProductosIdOrdenesMedioDetalleTemp.remove(clienteTempIf.getId());	
											mapClienteIdProductosIdOrdenesMedioDetalleTemp.put(clienteTempIf.getId(), mapProductosIdOrdenesMedioDetalleTemp);
											mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList.remove(medioTempIf.getId());
											mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList.put(medioTempIf.getId(),mapClienteIdProductosIdOrdenesMedioDetalleTemp);
											
										}
									}
								}
							//}//ADD 4 AGOSTO//COMENTED 5 AGOSTO
						//}//END WHILE COMENTED 5 AGOSTO
					}
				}
			}
			System.out.println("mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList size00: " + mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	//END 11 AGOSTO
	
	//ADD 8 AGOSTO
	//public void agruparOrdenesMedioDetalleByTMediosClienteProducto(){ COMENTED 10 AGOSTO
	public void agruparOrdenesMedioDetalleCanalByTMediosClienteProducto(){
		mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList.clear();
		
		try {
			Iterator ordenesMedioDetalleXCanalIt = ordenesMedioDetalleXCanalColl.iterator();
			while (ordenesMedioDetalleXCanalIt.hasNext()){
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXCanalIt.next();
				OrdenMedioIf ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenMedioDetalleIf.getOrdenMedioId());
				Collection<ClienteIf> medioIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getProveedorId());
							
				if (!medioIfColl.isEmpty()){
					ClienteIf medioTempIf = medioIfColl.iterator().next();
					//Long productoClienteId = ordenMedioIf.getProductoClienteId();
					Collection<ClienteIf> clienteIfColl = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getClienteOficinaId());	
					
					if (!clienteIfColl.isEmpty()){
						ClienteIf clienteTempIf = clienteIfColl.iterator().next();
						
						//COMENTED 5 AGOSTO
						//ADD 1 AGOSTO
						/*Collection<OrdenMedioDetalleIf> ordenesMedioDetalleTempIfColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
						Iterator ordenesMedioDetalleXCanalIt = ordenesMedioDetalleTempIfColl.iterator();
						while(ordenesMedioDetalleXCanalIt.hasNext()){
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenesMedioDetalleXCanalIt.next();*/
							Long productoClienteId = ordenMedioDetalleIf.getProductoClienteId();
							
							//COMENTED 5 AGOSTO
							//ADD 4 AGOSTO
							//if (productoClienteIf == null || productoClienteId.equals(productoClienteIf.getId())){
							
								Map<Long,Map<Long,ArrayList<OrdenMedioDetalleIf>>> mapClienteIdProductosIdOrdenesMedioDetalleTemp; 
								Map<Long,ArrayList<OrdenMedioDetalleIf>> mapProductosIdOrdenesMedioDetalleTemp; 
								ArrayList<OrdenMedioDetalleIf> listOrdenesMedioDetalle;
									
								if (!mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList.containsKey(medioTempIf.getId())){
									mapClienteIdProductosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long, Map<Long,ArrayList<OrdenMedioDetalleIf>>>();
									mapProductosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
									listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
															
									listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
									mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId,listOrdenesMedioDetalle);
									mapClienteIdProductosIdOrdenesMedioDetalleTemp.put(clienteTempIf.getId(),mapProductosIdOrdenesMedioDetalleTemp);
									mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList.put(medioTempIf.getId(),mapClienteIdProductosIdOrdenesMedioDetalleTemp);
										
								}else {
									mapClienteIdProductosIdOrdenesMedioDetalleTemp = mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList.get(medioTempIf.getId());
										
									if (!mapClienteIdProductosIdOrdenesMedioDetalleTemp.containsKey(clienteTempIf.getId())){
										mapProductosIdOrdenesMedioDetalleTemp = new LinkedHashMap<Long,ArrayList<OrdenMedioDetalleIf>>();
										listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
										listOrdenesMedioDetalle.add(ordenMedioDetalleIf);									
										mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId, listOrdenesMedioDetalle);
										mapClienteIdProductosIdOrdenesMedioDetalleTemp.put(clienteTempIf.getId(), mapProductosIdOrdenesMedioDetalleTemp);
										mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList.remove(medioTempIf.getId());
										mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList.put(medioTempIf.getId(),mapClienteIdProductosIdOrdenesMedioDetalleTemp);
									}else{
										mapProductosIdOrdenesMedioDetalleTemp = mapClienteIdProductosIdOrdenesMedioDetalleTemp.get(clienteTempIf.getId());
										if (!mapProductosIdOrdenesMedioDetalleTemp.containsKey(productoClienteId)){
											listOrdenesMedioDetalle = new ArrayList<OrdenMedioDetalleIf>();
											
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId, listOrdenesMedioDetalle);
											mapClienteIdProductosIdOrdenesMedioDetalleTemp.remove(clienteTempIf.getId());	
											mapClienteIdProductosIdOrdenesMedioDetalleTemp.put(clienteTempIf.getId(), mapProductosIdOrdenesMedioDetalleTemp);
											mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList.remove(medioTempIf.getId());
											mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList.put(medioTempIf.getId(),mapClienteIdProductosIdOrdenesMedioDetalleTemp);
										}else{
												
											listOrdenesMedioDetalle = mapProductosIdOrdenesMedioDetalleTemp.get(productoClienteId);
																							
											listOrdenesMedioDetalle.add(ordenMedioDetalleIf);
											mapProductosIdOrdenesMedioDetalleTemp.remove(productoClienteId);
											mapProductosIdOrdenesMedioDetalleTemp.put(productoClienteId, listOrdenesMedioDetalle);
											mapClienteIdProductosIdOrdenesMedioDetalleTemp.remove(clienteTempIf.getId());	
											mapClienteIdProductosIdOrdenesMedioDetalleTemp.put(clienteTempIf.getId(), mapProductosIdOrdenesMedioDetalleTemp);
											mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList.remove(medioTempIf.getId());
											mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList.put(medioTempIf.getId(),mapClienteIdProductosIdOrdenesMedioDetalleTemp);
											
										}
									}
								}
							//}//ADD 4 AGOSTO//COMENTED 5 AGOSTO
						//}//END WHILE COMENTED 5 AGOSTO
					}
				}
			}
			System.out.println("mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList size00: " + mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	private Map<String,BigDecimal> inicializarMapMesesValor(){
		Map<String,BigDecimal> mapMesesInicializados = new LinkedHashMap<String, BigDecimal>();
		BigDecimal valor = new BigDecimal(0D);
		for (String mes: meses){
			mapMesesInicializados.put(mes, valor);
		}
		return mapMesesInicializados;
	}
	
	public void obtenerTotalesMesesOrdenesProductoByClienteProductoTMediosAntes11Agosto(){ //MODIFIED 11 AGOSTO
		mapClientesIdProductosIdMedioIdMesesTotalOrdenesProducto.clear();
		
		try {
			
			Iterator mapaClienteProductoMedioIdsOrdenesXProductoIt =  mapClientesIdProductosIdMedioIdOrdenesXProducto.keySet().iterator();
			BigDecimal valorToAdd = new BigDecimal(0D);
			//ADD 2 AGOSTO
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valorSubtotal = new BigDecimal(0D);
			BigDecimal valorDescuentoVenta = new BigDecimal(0D);
			BigDecimal valorComisionAgencia = new BigDecimal(0D);
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			while (mapaClienteProductoMedioIdsOrdenesXProductoIt.hasNext()){
				Long idCliente = (Long) mapaClienteProductoMedioIdsOrdenesXProductoIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapProductosIdMedioIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				Map mapaProductoMedioIdsOrdenesXProducto = mapClientesIdProductosIdMedioIdOrdenesXProducto.get(idCliente);
				Iterator mapaProductoMedioIdsOrdenesXProductoIt = mapaProductoMedioIdsOrdenesXProducto.keySet().iterator();
				
				while (mapaProductoMedioIdsOrdenesXProductoIt.hasNext()){
					Long idProducto = (Long) mapaProductoMedioIdsOrdenesXProductoIt.next();
					Map<Long,Map<String,BigDecimal>> mapMedioIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					Map mapaMedioIdsOrdenesXProducto = (Map) mapaProductoMedioIdsOrdenesXProducto.get(idProducto);
					Iterator mapaMedioIdsOrdenesXProductoIt = mapaMedioIdsOrdenesXProducto.keySet().iterator();
					
					while (mapaMedioIdsOrdenesXProductoIt.hasNext()){
						Long idMedio = (Long) mapaMedioIdsOrdenesXProductoIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenes = inicializarMapMesesValor();
						ArrayList<OrdenMedioIf> ordenesXProductoList = (ArrayList<OrdenMedioIf>) mapaMedioIdsOrdenesXProducto.get(idMedio);
					
						for (OrdenMedioIf ordenMedioIf : ordenesXProductoList){
							mes = Utilitarios.getMonthUpperCase(new Date(ordenMedioIf.getFechaOrden().getTime()));
							
							valorSubtotal = ordenMedioIf.getValorSubtotal();
							valorDescuentoVenta = ordenMedioIf.getValorDescuentoVenta();
							valorComisionAgencia = ordenMedioIf.getValorComisionAgencia();
							valor = valorSubtotal.subtract(valorDescuentoVenta).add(valorComisionAgencia);
							
							//COMENTED 17 AGOSTO
							//ProductoIf productoProveedorIf = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioIf.getProductoProveedorId());
							//GenericoIf genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoProveedorIf.getGenericoId());
							//ADD 17 AGOSTO
							ProductoIf productoProveedorIf = mapaProducto.get(ordenMedioIf.getProductoProveedorId());
							GenericoIf genericoIf = mapaGenerico.get(productoProveedorIf.getGenericoId());
							
							
							if (genericoIf.getCobraIvaCliente().compareTo(CON_IVA)== 0)	
								valor = valor.add(valor.multiply(BigDecimal.valueOf(IVA)));
							
							valorToAdd = mapMesesTotalOrdenes.get(mes);
							valorToAdd = valorToAdd.add(valor);
							mapMesesTotalOrdenes.put(mes, valorToAdd);	
						}
						mapMedioIdMesesTotalOrdenes.put(idMedio, mapMesesTotalOrdenes);
					}
					mapProductosIdMedioIdMesesTotalOrdenes.put(idProducto,mapMedioIdMesesTotalOrdenes);
				}
				mapClientesIdProductosIdMedioIdMesesTotalOrdenesProducto.put(idCliente, mapProductosIdMedioIdMesesTotalOrdenes);
			}
			//System.out.println("mapClientesIdsOrdenesMedioXProducto size00: " + mapClientesIdProductosIdMedioIdOrdenesXProducto.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//END 1 AGOSTO
	
	//ADD 11 AGOSTO
	public void obtenerTotalesMesesOrdenesProductoByClienteTMediosProducto(){
		mapClientesIdMedioIdProductosIdMesesTotalOrdenesProducto.clear();
		
		try {
			
			Iterator mapaClienteMedioProductoIdsOrdenesXProductoIt =  mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList.keySet().iterator();
			BigDecimal valorToAdd = new BigDecimal(0D);
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valorSubtotal = new BigDecimal(0D);
			BigDecimal valorDescuentoVenta = new BigDecimal(0D);
			BigDecimal valorComisionAgencia = new BigDecimal(0D);
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			ProductoIf productoProveedorIf;
			GenericoIf genericoIf;
			boolean cobraIVA;
			
			while (mapaClienteMedioProductoIdsOrdenesXProductoIt.hasNext()){
				Long idCliente = (Long) mapaClienteMedioProductoIdsOrdenesXProductoIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapMedioIdProductosIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				Map mapaMedioProductoIdsOrdenesXProducto = mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList.get(idCliente);
				Iterator mapaMedioProductoIdsOrdenesXProductoIt = mapaMedioProductoIdsOrdenesXProducto.keySet().iterator();
				
				while (mapaMedioProductoIdsOrdenesXProductoIt.hasNext()){
					Long idMedio = (Long) mapaMedioProductoIdsOrdenesXProductoIt.next();
					Map<Long,Map<String,BigDecimal>> mapProductoIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					Map mapaProductoIdsOrdenesXProducto = (Map) mapaMedioProductoIdsOrdenesXProducto.get(idMedio);
					Iterator mapaProductoIdsOrdenesXProductoIt = mapaProductoIdsOrdenesXProducto.keySet().iterator();
					
					while (mapaProductoIdsOrdenesXProductoIt.hasNext()){
						Long idProducto = (Long) mapaProductoIdsOrdenesXProductoIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenes = inicializarMapMesesValor();
												
						//ADD 2 AGOSTO
						ArrayList<OrdenMedioDetalleIf> ordenesMedioDetalleXProductoList = (ArrayList<OrdenMedioDetalleIf>)mapaProductoIdsOrdenesXProducto.get(idProducto);
						cobraIVA = true;
						
						for (OrdenMedioDetalleIf ordenMedioDetalleIf : ordenesMedioDetalleXProductoList){
							
							OrdenMedioIf ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenMedioDetalleIf.getOrdenMedioId());
							mes = Utilitarios.getMonthUpperCase(new Date(ordenMedioIf.getFechaOrden().getTime()));	
							
							//COMENTED 17 AGOSTO
							//productoProveedorIf = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioDetalleIf.getProductoProveedorId());
							//genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoProveedorIf.getGenericoId());
							
							//ADD 17 AGOSTO
							productoProveedorIf = mapaProducto.get(ordenMedioDetalleIf.getProductoProveedorId());
							genericoIf = mapaGenerico.get(productoProveedorIf.getGenericoId());
							
							
							if (genericoIf.getCobraIvaCliente().compareTo(CON_IVA) != 0)	
								cobraIVA = false;
							
							valorSubtotal = ordenMedioDetalleIf.getValorSubtotal();
							valorDescuentoVenta = ordenMedioDetalleIf.getValorDescuentoVenta();//descomentar
							valorComisionAgencia = ordenMedioDetalleIf.getValorComisionAgencia();//descomentar
							valor = valorSubtotal.subtract(valorDescuentoVenta).add(valorComisionAgencia);//descomentar
							
							if (mapProductoIdMesesTotalOrdenes.get(idProducto) == null || mapProductoIdMesesTotalOrdenes.get(idProducto).isEmpty())
								valorToAdd = mapMesesTotalOrdenes.get(mes);		
							else{ 
								mapMesesTotalOrdenes = mapProductoIdMesesTotalOrdenes.get(idProducto);								
								valorToAdd = mapMesesTotalOrdenes.get(mes);									
							}
								
							valorToAdd = valorToAdd.add(valor);//descomentar
							//valorToAdd = valorToAdd.add(valorSubtotal);	//add sacar
							mapMesesTotalOrdenes.put(mes, valorToAdd);
						}
						
						//ADD 3 AGOSTO
						if (cobraIVA){
							Iterator mapMesesTotalOrdenesIt = mapMesesTotalOrdenes.keySet().iterator();
							
							while(mapMesesTotalOrdenesIt.hasNext()){
								String mesMap = (String) mapMesesTotalOrdenesIt.next();
								BigDecimal valorMes = mapMesesTotalOrdenes.get(mesMap);
									
								valorMes = valorMes.add(valorMes.multiply(BigDecimal.valueOf(IVA)));
								mapMesesTotalOrdenes.put(mesMap,valorMes);
							}
						}
						//END 3 AGOSTO
						
						mapProductoIdMesesTotalOrdenes.put(idProducto, mapMesesTotalOrdenes);
					}
					mapMedioIdProductosIdMesesTotalOrdenes.put(idMedio,mapProductoIdMesesTotalOrdenes); 
				}
				mapClientesIdMedioIdProductosIdMesesTotalOrdenesProducto.put(idCliente, mapMedioIdProductosIdMesesTotalOrdenes);
			}
			System.out.println("mapClientesIdMedioIdProductosIdMesesTotalOrdenesProducto size00: " + mapClientesIdMedioIdProductosIdMesesTotalOrdenesProducto.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ADD 8 AGOSTO
	public void obtenerTotalesMesesOrdenesProductoByClienteTMediosProductoAntes11Agosto(){ //MODIFIED 11 AGOSTO
		mapClientesIdMedioIdProductosIdMesesTotalOrdenesProducto.clear();
		
		try {
			
			Iterator mapaClienteMedioProductoIdsOrdenesXProductoIt =  mapClientesIdMediosIdProductosIdOrdenesXProducto.keySet().iterator();
			BigDecimal valorToAdd = new BigDecimal(0D);
			//ADD 2 AGOSTO
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valorSubtotal = new BigDecimal(0D);
			BigDecimal valorDescuentoVenta = new BigDecimal(0D);
			BigDecimal valorComisionAgencia = new BigDecimal(0D);
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			while (mapaClienteMedioProductoIdsOrdenesXProductoIt.hasNext()){
				Long idCliente = (Long) mapaClienteMedioProductoIdsOrdenesXProductoIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapMedioIdProductosIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				Map mapaMedioProductoIdsOrdenesXProducto = mapClientesIdMediosIdProductosIdOrdenesXProducto.get(idCliente);
				Iterator mapaMedioProductoIdsOrdenesXProductoIt = mapaMedioProductoIdsOrdenesXProducto.keySet().iterator();
				
				while (mapaMedioProductoIdsOrdenesXProductoIt.hasNext()){
					Long idMedio = (Long) mapaMedioProductoIdsOrdenesXProductoIt.next();
					Map<Long,Map<String,BigDecimal>> mapProductoIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					Map mapaProductoIdsOrdenesXProducto = (Map) mapaMedioProductoIdsOrdenesXProducto.get(idMedio);
					Iterator mapaProductoIdsOrdenesXProductoIt = mapaProductoIdsOrdenesXProducto.keySet().iterator();
					
					while (mapaProductoIdsOrdenesXProductoIt.hasNext()){
						Long idProducto = (Long) mapaProductoIdsOrdenesXProductoIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenes = inicializarMapMesesValor();
						ArrayList<OrdenMedioIf> ordenesXProductoList = (ArrayList<OrdenMedioIf>) mapaProductoIdsOrdenesXProducto.get(idProducto);
					
						for (OrdenMedioIf ordenMedioIf : ordenesXProductoList){
							mes = Utilitarios.getMonthUpperCase(new Date(ordenMedioIf.getFechaOrden().getTime()));
							
							valorSubtotal = ordenMedioIf.getValorSubtotal();
							valorDescuentoVenta = ordenMedioIf.getValorDescuentoVenta();
							valorComisionAgencia = ordenMedioIf.getValorComisionAgencia();
							valor = valorSubtotal.subtract(valorDescuentoVenta).add(valorComisionAgencia);
							
							//COMENTED 17 AGOSTO
							//ProductoIf productoProveedorIf = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioIf.getProductoProveedorId());
							//GenericoIf genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoProveedorIf.getGenericoId());
							
							//ADD 17 AGOSTO
							ProductoIf productoProveedorIf = mapaProducto.get(ordenMedioIf.getProductoProveedorId());
							GenericoIf genericoIf = mapaGenerico.get(productoProveedorIf.getGenericoId());
							
							if (genericoIf.getCobraIvaCliente().compareTo(CON_IVA)== 0)	
								valor = valor.add(valor.multiply(BigDecimal.valueOf(IVA)));
							
							valorToAdd = mapMesesTotalOrdenes.get(mes);
							valorToAdd = valorToAdd.add(valor);
							mapMesesTotalOrdenes.put(mes, valorToAdd);	
						}
						mapProductoIdMesesTotalOrdenes.put(idProducto, mapMesesTotalOrdenes);
					}
					mapMedioIdProductosIdMesesTotalOrdenes.put(idMedio,mapProductoIdMesesTotalOrdenes);
				}
				mapClientesIdMedioIdProductosIdMesesTotalOrdenesProducto.put(idCliente, mapMedioIdProductosIdMesesTotalOrdenes);
			}
			//System.out.println("mapClientesIdsOrdenesMedioXProducto size00: " + mapClientesIdProductosIdMedioIdOrdenesXProducto.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//END 8 AGOSTO
	
	//ADD 8 AGOSTO
	public void obtenerTotalesMesesOrdenesProductoByTMediosClienteProducto2(){
		mapMedioIdClientesIdProductosIdMesesTotalOrdenesProducto.clear();
		
		try {
			
			Iterator mapaMedioClienteProductoIdsOrdenesXProductoIt =  mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList.keySet().iterator();
			BigDecimal valorToAdd = new BigDecimal(0D);
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valorSubtotal = new BigDecimal(0D);
			BigDecimal valorDescuentoVenta = new BigDecimal(0D);
			BigDecimal valorComisionAgencia = new BigDecimal(0D);
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			ProductoIf productoProveedorIf;
			GenericoIf genericoIf;
			boolean cobraIVA;
			
			while (mapaMedioClienteProductoIdsOrdenesXProductoIt.hasNext()){
				Long idMedio = (Long) mapaMedioClienteProductoIdsOrdenesXProductoIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapClienteIdProductosIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				Map mapaClienteProductoIdsOrdenesXProducto = mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList.get(idMedio);
				Iterator mapaClienteProductoIdsOrdenesXProductoIt = mapaClienteProductoIdsOrdenesXProducto.keySet().iterator();
				
				while (mapaClienteProductoIdsOrdenesXProductoIt.hasNext()){
					Long idCliente = (Long) mapaClienteProductoIdsOrdenesXProductoIt.next();
					Map<Long,Map<String,BigDecimal>> mapProductoIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					Map mapaProductoIdsOrdenesXProducto = (Map) mapaClienteProductoIdsOrdenesXProducto.get(idCliente);
					Iterator mapaProductoIdsOrdenesXProductoIt = mapaProductoIdsOrdenesXProducto.keySet().iterator();
					
					while (mapaProductoIdsOrdenesXProductoIt.hasNext()){
						Long idProducto = (Long) mapaProductoIdsOrdenesXProductoIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenes = inicializarMapMesesValor();
												
						//ADD 2 AGOSTO
						ArrayList<OrdenMedioDetalleIf> ordenesMedioDetalleXProductoList = (ArrayList<OrdenMedioDetalleIf>)mapaProductoIdsOrdenesXProducto.get(idProducto);
						cobraIVA = true;
						
						for (OrdenMedioDetalleIf ordenMedioDetalleIf : ordenesMedioDetalleXProductoList){
							
							OrdenMedioIf ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenMedioDetalleIf.getOrdenMedioId());
							mes = Utilitarios.getMonthUpperCase(new Date(ordenMedioIf.getFechaOrden().getTime()));	
							
							//COMENTED 17 AG
							//productoProveedorIf = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioDetalleIf.getProductoProveedorId());
							//genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoProveedorIf.getGenericoId());
							
							//ADD 17 AGOSTO
							productoProveedorIf = mapaProducto.get(ordenMedioDetalleIf.getProductoProveedorId());
							genericoIf = mapaGenerico.get(productoProveedorIf.getGenericoId());
							
							if (genericoIf.getCobraIvaCliente().compareTo(CON_IVA) != 0)	
								cobraIVA = false;
							
							valorSubtotal = ordenMedioDetalleIf.getValorSubtotal();
							valorDescuentoVenta = ordenMedioDetalleIf.getValorDescuentoVenta();//descomentar
							valorComisionAgencia = ordenMedioDetalleIf.getValorComisionAgencia();//descomentar
							valor = valorSubtotal.subtract(valorDescuentoVenta).add(valorComisionAgencia);//descomentar
							
							if (mapProductoIdMesesTotalOrdenes.get(idProducto) == null || mapProductoIdMesesTotalOrdenes.get(idProducto).isEmpty())
								valorToAdd = mapMesesTotalOrdenes.get(mes);		
							else{ 
								mapMesesTotalOrdenes = mapProductoIdMesesTotalOrdenes.get(idProducto);								
								valorToAdd = mapMesesTotalOrdenes.get(mes);									
							}
								
							valorToAdd = valorToAdd.add(valor);//descomentar
							//valorToAdd = valorToAdd.add(valorSubtotal);	//add sacar
							mapMesesTotalOrdenes.put(mes, valorToAdd);
						}
						
						//ADD 3 AGOSTO
						if (cobraIVA){
							Iterator mapMesesTotalOrdenesIt = mapMesesTotalOrdenes.keySet().iterator();
							
							while(mapMesesTotalOrdenesIt.hasNext()){
								String mesMap = (String) mapMesesTotalOrdenesIt.next();
								BigDecimal valorMes = mapMesesTotalOrdenes.get(mesMap);
									
								valorMes = valorMes.add(valorMes.multiply(BigDecimal.valueOf(IVA)));
								mapMesesTotalOrdenes.put(mesMap,valorMes);
							}
						}
						//END 3 AGOSTO
						
						mapProductoIdMesesTotalOrdenes.put(idProducto, mapMesesTotalOrdenes);
					}
					mapClienteIdProductosIdMesesTotalOrdenes.put(idCliente,mapProductoIdMesesTotalOrdenes); 
				}
				mapMedioIdClientesIdProductosIdMesesTotalOrdenesProducto.put(idMedio, mapClienteIdProductosIdMesesTotalOrdenes);
			}
			System.out.println("mapMedioIdClientesIdProductosIdMesesTotalOrdenesProducto size00: " + mapMedioIdClientesIdProductosIdMesesTotalOrdenesProducto.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
	
	public void obtenerTotalesMesesOrdenesProductoByClienteProductoTMedios(){
		mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.clear();
		
		try {
			Iterator mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIt =  mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.keySet().iterator();
			
			BigDecimal valorAcumulado = new BigDecimal(0D);
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			//mapa para no repetir valores de notas de credito por plan
			Map<Long,Long> mapaOrdenNCerror = new HashMap<Long,Long>();
			Map<Long,Long> mapaOrdenNCganacia = new HashMap<Long,Long>();
							
			while (mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIt.hasNext()){
				Long idCliente = (Long) mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIt.next();
				Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>> 
					mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>>();
					
				Map mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.get(idCliente);
				Iterator mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt = mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
				
				while (mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
					Long idClienteOficina = (Long) mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt.next();
					Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>
					mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>();
					
					Map mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto.get(idClienteOficina);
					Iterator mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt = mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
					
					while (mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
						Long idMarcaProducto = (Long) mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt.next();
						
						Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>
						mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>();
						
						Map mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto.get(idMarcaProducto);
						Iterator mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt = mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
											
						
						while (mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
							Long idProductoCliente = (Long) mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt.next();
							
							Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>
							mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>();
							
							Map mapCampanaTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto.get(idProductoCliente);
							Iterator mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt = mapCampanaTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
													
						
							while (mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
								Long idCampana = (Long) mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt.next();
							
								Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>
								mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>();
								
								Map mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapCampanaTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaOrdenesXProducto.get(idCampana);
								Iterator mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt = mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
										
								while (mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
									Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt.next();
								
									Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>
									mapMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>();
									
									Map mapMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto.get(idTipoMedio);
									Iterator mapMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt = mapMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
									
									while (mapMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
										Long idMedio = (Long) mapMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt.next();
									
										Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>
										mapMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>();
										
										Map mapMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapMedioMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto.get(idMedio);
										Iterator mapMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt = mapMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
								
										while (mapMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
											Long idMedioOficina = (Long) mapMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProductoIt.next();
										
											Map<String,Map<Long,Map<String,BigDecimal>>> mapTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<String,Map<Long,Map<String,BigDecimal>>>();
											
											Map mapTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapMedioOfiIdsTipoPautaDerechoProgramaOrdenesXProducto.get(idMedioOficina);
											Iterator mapTipoPautaDerechoProgramaOrdenesXProductoIt = mapTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
											
											while (mapTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
												String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaOrdenesXProductoIt.next();
											
												Map<Long,Map<String,BigDecimal>> mapDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<String,BigDecimal>>();
												
												Map mapDerechoProgramaOrdenesXProducto = (Map) mapTipoPautaDerechoProgramaOrdenesXProducto.get(tipoPautaTemp);
												Iterator mapDerechoProgramaOrdenesXProductoIt = mapDerechoProgramaOrdenesXProducto.keySet().iterator();
												
												while (mapDerechoProgramaOrdenesXProductoIt.hasNext()){
													Long idDerechoPrograma = (Long) mapDerechoProgramaOrdenesXProductoIt.next();
												
													//se inicializa el mapa de meses con valor 0.0
													mapMesesTotalOrdenes = inicializarMapMesesValor();
																						
													ArrayList<OrdenMedioDetalleIf> ordenesMedioDetalleXProductoList = (ArrayList<OrdenMedioDetalleIf>)mapDerechoProgramaOrdenesXProducto.get(idDerechoPrograma);
																
													for (OrdenMedioDetalleIf ordenMedioDetalleIf : ordenesMedioDetalleXProductoList){
																
														OrdenMedioIf ordenMedioIf = mapaOrdenesMedio.get(ordenMedioDetalleIf.getOrdenMedioId());
														mes = Utilitarios.getMonthUpperCase(new Date(ordenMedioIf.getFechaOrden().getTime()));	
																																									
														valor = ordenMedioDetalleIf.getValorSubtotal();
														
														//solo ingresa una sola ves por cada orden
														double subtotalPlanNotaCreditoError = 0D;
														if(mapaOrdenIdValorNotaCreditoError.get(ordenMedioIf.getId()) != null
																&& mapaOrdenNCerror.get(ordenMedioIf.getId()) == null){
															subtotalPlanNotaCreditoError = mapaOrdenIdValorNotaCreditoError.get(ordenMedioIf.getId());
															mapaOrdenNCerror.put(ordenMedioIf.getId(), ordenMedioIf.getId());
															valor = valor.subtract(BigDecimal.valueOf(subtotalPlanNotaCreditoError));
														}
														
														double subtotalPlanNotaCreditoGanancia = 0D;
														if(getRbValorNeto().isSelected()){
															double porcentajeDescuentoVenta = ordenMedioDetalleIf.getPorcentajeDescuentoVenta().doubleValue() / 100;
															double descuentoVenta = valor.doubleValue() * porcentajeDescuentoVenta;
															double porcentajeComisionAgencia = ordenMedioDetalleIf.getPorcentajeComisionAgencia().doubleValue() / 100;
															double comisionAgencia = (valor.doubleValue() - descuentoVenta) * porcentajeComisionAgencia;
															valor = valor.subtract(BigDecimal.valueOf(descuentoVenta)).add(BigDecimal.valueOf(comisionAgencia));
															
															if(mapaOrdenIdValorNotaCreditoGanancia.get(ordenMedioIf.getId()) != null
																	&& mapaOrdenNCganacia.get(ordenMedioIf.getId()) == null){
																subtotalPlanNotaCreditoGanancia = mapaOrdenIdValorNotaCreditoGanancia.get(ordenMedioIf.getId());
																mapaOrdenNCganacia.put(ordenMedioIf.getId(), ordenMedioIf.getId());
																valor = valor.subtract(BigDecimal.valueOf(subtotalPlanNotaCreditoGanancia));
															}
														}
																	
														if (mapDerechoProgramaMesesTotalOrdenes.get(idDerechoPrograma) == null || mapDerechoProgramaMesesTotalOrdenes.get(idDerechoPrograma).isEmpty())
															valorAcumulado = mapMesesTotalOrdenes.get(mes);		
														else{ 
															mapMesesTotalOrdenes = mapDerechoProgramaMesesTotalOrdenes.get(idDerechoPrograma);								
															valorAcumulado = mapMesesTotalOrdenes.get(mes);									
														}
																		
														valorAcumulado = valorAcumulado.add(valor);
														mapMesesTotalOrdenes.put(mes, valorAcumulado);
													}
															
													mapDerechoProgramaMesesTotalOrdenes.put(idDerechoPrograma, mapMesesTotalOrdenes);
												}
												mapTipoPautaDerechoProgramaMesesTotalOrdenes.put(tipoPautaTemp, mapDerechoProgramaMesesTotalOrdenes);
											}
											mapMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes.put(idMedioOficina, mapTipoPautaDerechoProgramaMesesTotalOrdenes);										
										}
										mapMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes.put(idMedio, mapMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes);	
									}		
									mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes.put(idTipoMedio, mapMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes);	
						
								}
								mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes.put(idCampana, mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes);
							
							}	
							mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes.put(idProductoCliente, mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes);	
						}	
						mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes.put(idMarcaProducto, mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes);	
					}
					mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes.put(idClienteOficina,mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes); 
				}
				mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.put(idCliente,mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaMesesTotalOrdenes);
			}
			System.out.println("mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto size00: " + mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void obtenerTotalesMesesOrdenesProductoByTMediosClienteProducto(){
		mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.clear();
		
		try {			
			Iterator mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoListIt =  mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.keySet().iterator();
			
			BigDecimal valorToAdd = new BigDecimal(0D);
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			//mapa para no repetir valores de notas de credito por plan
			Map<Long,Long> mapaOrdenNCerror = new HashMap<Long,Long>();
			Map<Long,Long> mapaOrdenNCganacia = new HashMap<Long,Long>();
							
			while (mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoListIt.hasNext()){
				Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoListIt.next();
				Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>> 
					mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>>();
					
				Map mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.get(idTipoMedio);
				Iterator mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt = mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
				
				while (mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
					Long idMedio = (Long) mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt.next();
					Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>
					mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>();
					
					Map mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto.get(idMedio);
					Iterator mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt = mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
					
					while (mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
						Long idMedioOficina = (Long) mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt.next();
						
						Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>
						mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>();
						
						Map mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto.get(idMedioOficina);
						Iterator mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
											
						
						while (mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
							Long idCliente = (Long) mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt.next();
							
							Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>
							mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>();
							
							Map mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto.get(idCliente);
							Iterator mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt = mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
													
						
							while (mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
								Long idClienteOficina = (Long) mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt.next();
							
								Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>
								mapMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>();
								
								Map mapMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto.get(idClienteOficina);
								Iterator mapMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt = mapMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
										
								while (mapMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
									Long idMarcaProducto = (Long) mapMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt.next();
								
									Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>
									mapProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>();
									
									Map mapProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapMarcaProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto.get(idMarcaProducto);
									Iterator mapProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt = mapProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
									
									while (mapProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
										Long idProductoCliente = (Long) mapProductoCampanaTipoPautaDerechoProgramaOrdenesXProductoIt.next();
									
										Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>
										mapCampanaTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>();
										
										Map mapCampanaTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapProductoCampanaTipoPautaDerechoProgramaOrdenesXProducto.get(idProductoCliente);
										Iterator mapCampanaTipoPautaDerechoProgramaOrdenesXProductoIt = mapCampanaTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
								
										while (mapCampanaTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
											Long idCampana = (Long) mapCampanaTipoPautaDerechoProgramaOrdenesXProductoIt.next();
										
											Map<String,Map<Long,Map<String,BigDecimal>>> 
											mapTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<String,Map<Long,Map<String,BigDecimal>>>();
											
											Map mapTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapCampanaTipoPautaDerechoProgramaOrdenesXProducto.get(idCampana);
											Iterator mapTipoPautaDerechoProgramaOrdenesXProductoIt = mapTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
											
											while (mapTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
												String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaOrdenesXProductoIt.next();
											
												Map<Long,Map<String,BigDecimal>> mapDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<String,BigDecimal>>();
												
												Map mapDerechoProgramaOrdenesXProducto = (Map) mapTipoPautaDerechoProgramaOrdenesXProducto.get(tipoPautaTemp);
												Iterator mapDerechoProgramaOrdenesXProductoIt = mapDerechoProgramaOrdenesXProducto.keySet().iterator();
												
												while (mapDerechoProgramaOrdenesXProductoIt.hasNext()){
													Long idDerechoPrograma = (Long) mapDerechoProgramaOrdenesXProductoIt.next();
												
													//se inicializa el mapa de meses con valor 0.0
													mapMesesTotalOrdenes = inicializarMapMesesValor();
																						
													ArrayList<OrdenMedioDetalleIf> ordenesMedioDetalleXProductoList = (ArrayList<OrdenMedioDetalleIf>)mapDerechoProgramaOrdenesXProducto.get(idDerechoPrograma);
																
													for (OrdenMedioDetalleIf ordenMedioDetalleIf : ordenesMedioDetalleXProductoList){
																
														OrdenMedioIf ordenMedioIf = mapaOrdenesMedio.get(ordenMedioDetalleIf.getOrdenMedioId());
														mes = Utilitarios.getMonthUpperCase(new Date(ordenMedioIf.getFechaOrden().getTime()));	
																																									
														valor = ordenMedioDetalleIf.getValorSubtotal();
																																									
														//solo ingresa una sola ves por cada orden
														double subtotalPlanNotaCreditoError = 0D;
														if(mapaOrdenIdValorNotaCreditoError.get(ordenMedioIf.getId()) != null
																&& mapaOrdenNCerror.get(ordenMedioIf.getId()) == null){
															subtotalPlanNotaCreditoError = mapaOrdenIdValorNotaCreditoError.get(ordenMedioIf.getId());
															mapaOrdenNCerror.put(ordenMedioIf.getId(), ordenMedioIf.getId());
															valor = valor.subtract(BigDecimal.valueOf(subtotalPlanNotaCreditoError));
														}
														
														double subtotalPlanNotaCreditoGanancia = 0D;
														if(getRbValorNeto().isSelected()){
															double porcentajeDescuentoVenta = ordenMedioDetalleIf.getPorcentajeDescuentoVenta().doubleValue() / 100;
															double descuentoVenta = valor.doubleValue() * porcentajeDescuentoVenta;
															double porcentajeComisionAgencia = ordenMedioDetalleIf.getPorcentajeComisionAgencia().doubleValue() / 100;
															double comisionAgencia = (valor.doubleValue() - descuentoVenta) * porcentajeComisionAgencia;
															valor = valor.subtract(BigDecimal.valueOf(descuentoVenta)).add(BigDecimal.valueOf(comisionAgencia));
															
															if(mapaOrdenIdValorNotaCreditoGanancia.get(ordenMedioIf.getId()) != null
																	&& mapaOrdenNCganacia.get(ordenMedioIf.getId()) == null){
																subtotalPlanNotaCreditoGanancia = mapaOrdenIdValorNotaCreditoGanancia.get(ordenMedioIf.getId());
																mapaOrdenNCganacia.put(ordenMedioIf.getId(), ordenMedioIf.getId());
																valor = valor.subtract(BigDecimal.valueOf(subtotalPlanNotaCreditoGanancia));
															}
														}
																	
														if (mapDerechoProgramaMesesTotalOrdenes.get(idDerechoPrograma) == null || mapDerechoProgramaMesesTotalOrdenes.get(idDerechoPrograma).isEmpty())
															valorToAdd = mapMesesTotalOrdenes.get(mes);		
														else{ 
															mapMesesTotalOrdenes = mapDerechoProgramaMesesTotalOrdenes.get(idDerechoPrograma);								
															valorToAdd = mapMesesTotalOrdenes.get(mes);									
														}
																		
														valorToAdd = valorToAdd.add(valor);
														mapMesesTotalOrdenes.put(mes, valorToAdd);
													}
															
													mapDerechoProgramaMesesTotalOrdenes.put(idDerechoPrograma, mapMesesTotalOrdenes);
												}
												mapTipoPautaDerechoProgramaMesesTotalOrdenes.put(tipoPautaTemp, mapDerechoProgramaMesesTotalOrdenes);
											}
											mapCampanaTipoPautaDerechoProgramaMesesTotalOrdenes.put(idCampana, mapTipoPautaDerechoProgramaMesesTotalOrdenes);										
										}
										mapProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes.put(idProductoCliente, mapCampanaTipoPautaDerechoProgramaMesesTotalOrdenes);	
									}		
									mapMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes.put(idMarcaProducto, mapProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes);	
						
								}
								mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes.put(idClienteOficina, mapMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes);
							
							}	
							mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes.put(idCliente, mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes);	
						}	
						mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes.put(idMedioOficina, mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes);	
					}
					mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes.put(idMedio, mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes); 
				}
				mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.put(idTipoMedio, mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaMesesTotalOrdenes);
			}
			System.out.println("mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto size00: " + mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void obtenerTotalesMesesOrdenesProductoByClienteTMediosProducto2(){
		
		mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.clear();
		
		try {
			
			Iterator mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoListIt =  mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.keySet().iterator();
			BigDecimal valorToAdd = new BigDecimal(0D);
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valorSubtotal = new BigDecimal(0D);
			BigDecimal valorDescuentoVenta = new BigDecimal(0D);
			BigDecimal valorComisionAgencia = new BigDecimal(0D);
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			ProductoIf productoProveedorIf;
			GenericoIf genericoIf;
			boolean cobraIVA;
			
			while (mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoListIt.hasNext()){
				Long idCliente = (Long) mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoListIt.next();
				Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>> 
					mapClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>();
								
				Map mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = (Map) mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.get(idCliente);
				Iterator mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt = mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.keySet().iterator();
				
				while (mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt.hasNext()){
					Long idClienteOficina = (Long) mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt.next();
					Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>
					mapTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>();
					
					Map mapTipoMedioMedioMedioMarcaProductoOfIdsTipoPautaDerechoProgramaOrdenesXProducto = (Map) mapClienteOfTipoMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.get(idClienteOficina);
					Iterator mapTipoMedioMedioMedioMarcaProductoOfIdsTipoPautaDerechoProgramaOrdenesXProductoIt = mapTipoMedioMedioMedioMarcaProductoOfIdsTipoPautaDerechoProgramaOrdenesXProducto.keySet().iterator();
										
					while (mapTipoMedioMedioMedioMarcaProductoOfIdsTipoPautaDerechoProgramaOrdenesXProductoIt.hasNext()){
						Long idTipoMedio = (Long) mapTipoMedioMedioMedioMarcaProductoOfIdsTipoPautaDerechoProgramaOrdenesXProductoIt.next();
						
						Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>
						mapTipoMedioMedioMedioOfProductoIdsTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>();
									
						Map mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = (Map) mapTipoMedioMedioMedioMarcaProductoOfIdsTipoPautaDerechoProgramaOrdenesXProducto.get(idTipoMedio);
						Iterator mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt = mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.keySet().iterator();
						
						while (mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt.hasNext()){
							Long idMedio = (Long) mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt.next();
							
							Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>
							mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>();
				
							Map mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = (Map) mapMedioMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.get(idMedio);
							Iterator mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt = mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.keySet().iterator();
					
							while (mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt.hasNext()){
								Long idMedioOficina = (Long) mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt.next();
								
								Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>
								mapMedioMedioOfIdsTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>();
																
								Map mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = (Map) mapMedioOfMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.get(idMedioOficina);
								Iterator mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt = mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.keySet().iterator();
								
								while (mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt.hasNext()){
									Long idMarcaProducto = (Long) mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt.next();
						
									Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>
									mapMedioOfIdsTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>();
																										
									Map mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle = (Map) mapMarcaProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.get(idMarcaProducto);
									Iterator mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt = mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.keySet().iterator();
						
									while (mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt.hasNext()){
										Long idProductoCliente = (Long) mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalleIt.next();
							
										Map<String,Map<Long,Map<String,BigDecimal>>> mapTipoPautaDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<String,Map<Long,Map<String,BigDecimal>>>();
																														
										Map mapPautaDerechoProgramaIdsOrdenesMedioDetalle = (Map) mapProductoPautaDerechoProgramaIdsOrdenesMedioDetalle.get(idProductoCliente);
										Iterator mapPautaDerechoProgramaIdsOrdenesMedioDetalleIt = mapPautaDerechoProgramaIdsOrdenesMedioDetalle.keySet().iterator();
									
										while (mapPautaDerechoProgramaIdsOrdenesMedioDetalleIt.hasNext()){
											String tipoPautaTemp = (String) mapPautaDerechoProgramaIdsOrdenesMedioDetalleIt.next();
											
											Map<Long,Map<String,BigDecimal>> mapDerechoProgramaMesesTotalOrdenes = new LinkedHashMap<Long,Map<String,BigDecimal>>();
											
											Map mapDerechoProgramaIdsOrdenesMedioDetalle = (Map) mapPautaDerechoProgramaIdsOrdenesMedioDetalle.get(tipoPautaTemp);
											Iterator mapDerechoProgramaIdsOrdenesMedioDetalleIt = mapDerechoProgramaIdsOrdenesMedioDetalle.keySet().iterator();
											
											while (mapDerechoProgramaIdsOrdenesMedioDetalleIt.hasNext()){
												Long idDerechoPrograma = (Long) mapDerechoProgramaIdsOrdenesMedioDetalleIt.next();
												
												//se inicializa el mapa de meses con valor 0.0
												mapMesesTotalOrdenes = inicializarMapMesesValor();
																					
												ArrayList<OrdenMedioDetalleIf> listOrdenesMedioDetalle = (ArrayList<OrdenMedioDetalleIf>)mapDerechoProgramaIdsOrdenesMedioDetalle.get(idDerechoPrograma);
												cobraIVA = true;
															
												for (OrdenMedioDetalleIf ordenMedioDetalleIf : listOrdenesMedioDetalle){
																
													OrdenMedioIf ordenMedioIf = mapaOrdenesMedio.get(ordenMedioDetalleIf.getOrdenMedioId());
													mes = Utilitarios.getMonthUpperCase(new Date(ordenMedioIf.getFechaOrden().getTime()));	
													
													productoProveedorIf = mapaProducto.get(ordenMedioDetalleIf.getProductoProveedorId());
													genericoIf = mapaGenerico.get(productoProveedorIf.getGenericoId());
													
													if (genericoIf.getCobraIvaCliente().compareTo(CON_IVA) != 0)	
														cobraIVA = false;
															
													valorSubtotal = ordenMedioDetalleIf.getValorSubtotal();
													//valorDescuentoVenta = ordenMedioDetalleIf.getValorDescuentoVenta();
													//valorComisionAgencia = ordenMedioDetalleIf.getValorComisionAgencia();
													valor = valorSubtotal.subtract(valorDescuentoVenta).add(valorComisionAgencia);
																
													if (mapDerechoProgramaMesesTotalOrdenes.get(idDerechoPrograma) == null || mapDerechoProgramaMesesTotalOrdenes.get(idDerechoPrograma).isEmpty())
														valorToAdd = mapMesesTotalOrdenes.get(mes);		
													else{ 
														mapMesesTotalOrdenes = mapDerechoProgramaMesesTotalOrdenes.get(idDerechoPrograma);								
														valorToAdd = mapMesesTotalOrdenes.get(mes);									
													}
																	
													valorToAdd = valorToAdd.add(valor);
													mapMesesTotalOrdenes.put(mes, valorToAdd);
												}
															
												if (cobraIVA){
													Iterator mapMesesTotalOrdenesIt = mapMesesTotalOrdenes.keySet().iterator();
														
													while(mapMesesTotalOrdenesIt.hasNext()){
														String mesMap = (String) mapMesesTotalOrdenesIt.next();
														BigDecimal valorMes = mapMesesTotalOrdenes.get(mesMap);
															
														valorMes = valorMes.add(valorMes.multiply(BigDecimal.valueOf(IVA)));
														mapMesesTotalOrdenes.put(mesMap,valorMes);
													}
												}
															
												mapDerechoProgramaMesesTotalOrdenes.put(idDerechoPrograma, mapMesesTotalOrdenes);
											}
											mapTipoPautaDerechoProgramaMesesTotalOrdenes.put(tipoPautaTemp, mapDerechoProgramaMesesTotalOrdenes);
										}
										mapMedioOfIdsTipoPautaDerechoProgramaMesesTotalOrdenes.put(idProductoCliente, mapTipoPautaDerechoProgramaMesesTotalOrdenes);										
									}
									mapMedioMedioOfIdsTipoPautaDerechoProgramaMesesTotalOrdenes.put(idMarcaProducto, mapMedioOfIdsTipoPautaDerechoProgramaMesesTotalOrdenes);	
								}		
								mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaMesesTotalOrdenes.put(idMedioOficina, mapMedioMedioOfIdsTipoPautaDerechoProgramaMesesTotalOrdenes);	
							}	
							mapTipoMedioMedioMedioOfProductoIdsTipoPautaDerechoProgramaMesesTotalOrdenes.put(idMedio, mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaMesesTotalOrdenes);	
						}	
						mapTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaMesesTotalOrdenes.put(idTipoMedio, mapTipoMedioMedioMedioOfProductoIdsTipoPautaDerechoProgramaMesesTotalOrdenes);	
					}
					mapClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaMesesTotalOrdenes.put(idClienteOficina,mapTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaMesesTotalOrdenes); 
				}
				mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.put(idCliente,mapClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaMesesTotalOrdenes);
			}
			System.out.println("mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto size00: " + mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void obtenerTotalesMesesOrdenesProductoByClienteProductoTMediosANTES(){
		mapClientesIdProductosIdMedioIdMesesTotalOrdenesProducto.clear();
		
		try {
			
			Iterator mapaClienteProductoMedioIdsOrdenesXProductoIt =  mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.keySet().iterator();
			BigDecimal valorToAdd = new BigDecimal(0D);
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valorSubtotal = new BigDecimal(0D);
			BigDecimal valorDescuentoVenta = new BigDecimal(0D);
			BigDecimal valorComisionAgencia = new BigDecimal(0D);
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			ProductoIf productoProveedorIf;
			GenericoIf genericoIf;
			boolean cobraIVA;
			
			while (mapaClienteProductoMedioIdsOrdenesXProductoIt.hasNext()){
				Long idCliente = (Long) mapaClienteProductoMedioIdsOrdenesXProductoIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapProductosIdMedioIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				Map mapaProductoMedioIdsOrdenesXProducto = mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.get(idCliente);
				Iterator mapaProductoMedioIdsOrdenesXProductoIt = mapaProductoMedioIdsOrdenesXProducto.keySet().iterator();
				
				while (mapaProductoMedioIdsOrdenesXProductoIt.hasNext()){
					Long idProducto = (Long) mapaProductoMedioIdsOrdenesXProductoIt.next();
					Map<Long,Map<String,BigDecimal>> mapMedioIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					Map mapaMedioIdsOrdenesXProducto = (Map) mapaProductoMedioIdsOrdenesXProducto.get(idProducto);
					Iterator mapaMedioIdsOrdenesXProductoIt = mapaMedioIdsOrdenesXProducto.keySet().iterator();
					
					while (mapaMedioIdsOrdenesXProductoIt.hasNext()){
						Long idMedio = (Long) mapaMedioIdsOrdenesXProductoIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenes = inicializarMapMesesValor();
												
						//ADD 2 AGOSTO
						ArrayList<OrdenMedioDetalleIf> ordenesMedioDetalleXProductoList = (ArrayList<OrdenMedioDetalleIf>)mapaMedioIdsOrdenesXProducto.get(idMedio);
						cobraIVA = true;
						
						for (OrdenMedioDetalleIf ordenMedioDetalleIf : ordenesMedioDetalleXProductoList){
							
							OrdenMedioIf ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenMedioDetalleIf.getOrdenMedioId());
							mes = Utilitarios.getMonthUpperCase(new Date(ordenMedioIf.getFechaOrden().getTime()));	
							
							//COMENTED 17 AGOSTO
							//productoProveedorIf = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioDetalleIf.getProductoProveedorId());
							//genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoProveedorIf.getGenericoId());
							
							//ADD 17 AGOSTO
							productoProveedorIf = mapaProducto.get(ordenMedioDetalleIf.getProductoProveedorId());
							genericoIf = mapaGenerico.get(productoProveedorIf.getGenericoId());
							
							if (genericoIf.getCobraIvaCliente().compareTo(CON_IVA) != 0)	
								cobraIVA = false;
							
							valorSubtotal = ordenMedioDetalleIf.getValorSubtotal();
							valorDescuentoVenta = ordenMedioDetalleIf.getValorDescuentoVenta();//descomentar
							valorComisionAgencia = ordenMedioDetalleIf.getValorComisionAgencia();//descomentar
							valor = valorSubtotal.subtract(valorDescuentoVenta).add(valorComisionAgencia);//descomentar
							
							if (mapMedioIdMesesTotalOrdenes.get(idMedio) == null || mapMedioIdMesesTotalOrdenes.get(idMedio).isEmpty())
								valorToAdd = mapMesesTotalOrdenes.get(mes);		
							else{ 
								mapMesesTotalOrdenes = mapMedioIdMesesTotalOrdenes.get(idMedio);								
								valorToAdd = mapMesesTotalOrdenes.get(mes);									
							}
								
							valorToAdd = valorToAdd.add(valor);//descomentar
							//valorToAdd = valorToAdd.add(valorSubtotal);	//add sacar
							mapMesesTotalOrdenes.put(mes, valorToAdd);
						}
						
						//ADD 3 AGOSTO
						if (cobraIVA){
							Iterator mapMesesTotalOrdenesIt = mapMesesTotalOrdenes.keySet().iterator();
							
							while(mapMesesTotalOrdenesIt.hasNext()){
								String mesMap = (String) mapMesesTotalOrdenesIt.next();
								BigDecimal valorMes = mapMesesTotalOrdenes.get(mesMap);
									
								valorMes = valorMes.add(valorMes.multiply(BigDecimal.valueOf(IVA)));
								mapMesesTotalOrdenes.put(mesMap,valorMes);
							}
						}
						//END 3 AGOSTO
						
						mapMedioIdMesesTotalOrdenes.put(idMedio, mapMesesTotalOrdenes);
					}
					mapProductosIdMedioIdMesesTotalOrdenes.put(idProducto,mapMedioIdMesesTotalOrdenes); 
				}
				mapClientesIdProductosIdMedioIdMesesTotalOrdenesProducto.put(idCliente, mapProductosIdMedioIdMesesTotalOrdenes);
			}
			System.out.println("mapClientesIdProductosIdMedioIdMesesTotalOrdenesProducto size00: " + mapClientesIdProductosIdMedioIdMesesTotalOrdenesProducto.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	//END 11 AGOSTO
	
				
	//ADD 3 AGOSTO
	public void obtenerTotalesMesesOrdenesCanalByClienteProductoTMedios(){
		mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanal.clear();
		
		try {
			
			Iterator mapaClienteProductoMedioIdsOrdenesXCanalIt =  mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.keySet().iterator();
			BigDecimal valorToAdd = new BigDecimal(0D);
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valorSubtotal = new BigDecimal(0D);
			BigDecimal valorDescuentoVenta = new BigDecimal(0D);
			BigDecimal valorComisionAgencia = new BigDecimal(0D);
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			ProductoIf productoProveedorIf;
			GenericoIf genericoIf;
			boolean cobraIVA;
			
			while (mapaClienteProductoMedioIdsOrdenesXCanalIt.hasNext()){
				Long idCliente = (Long) mapaClienteProductoMedioIdsOrdenesXCanalIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapProductosIdMedioIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				Map mapaProductoMedioIdsOrdenesXCanal = mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.get(idCliente);
				Iterator mapaProductoMedioIdsOrdenesXCanalIt = mapaProductoMedioIdsOrdenesXCanal.keySet().iterator();
				
				while (mapaProductoMedioIdsOrdenesXCanalIt.hasNext()){
					Long idProducto = (Long) mapaProductoMedioIdsOrdenesXCanalIt.next();
					Map<Long,Map<String,BigDecimal>> mapMedioIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					Map mapaMedioIdsOrdenesXCanal = (Map) mapaProductoMedioIdsOrdenesXCanal.get(idProducto);
					Iterator mapaMedioIdsOrdenesXCanalIt = mapaMedioIdsOrdenesXCanal.keySet().iterator();
					
					while (mapaMedioIdsOrdenesXCanalIt.hasNext()){
						Long idMedio = (Long) mapaMedioIdsOrdenesXCanalIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenes = inicializarMapMesesValor();
												
						//ADD 2 AGOSTO
						ArrayList<OrdenMedioDetalleIf> ordenesMedioDetalleXCanalList = (ArrayList<OrdenMedioDetalleIf>)mapaMedioIdsOrdenesXCanal.get(idMedio);
						cobraIVA = true;
						
						for (OrdenMedioDetalleIf ordenMedioDetalleIf : ordenesMedioDetalleXCanalList){
							
							OrdenMedioIf ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenMedioDetalleIf.getOrdenMedioId());
							mes = Utilitarios.getMonthUpperCase(new Date(ordenMedioIf.getFechaOrden().getTime()));	
							
							//COMENTED 17 AGOSTO
							//productoProveedorIf = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioDetalleIf.getProductoProveedorId());
							//genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoProveedorIf.getGenericoId());
							
							//ADD 17 AGOSTO
							productoProveedorIf = mapaProducto.get(ordenMedioDetalleIf.getProductoProveedorId());
							genericoIf = mapaGenerico.get(productoProveedorIf.getGenericoId());
							
							if (genericoIf.getCobraIvaCliente().compareTo(CON_IVA) != 0)	
								cobraIVA = false;
							
							valorSubtotal = ordenMedioDetalleIf.getValorSubtotal();
							valorDescuentoVenta = ordenMedioDetalleIf.getValorDescuentoVenta();//descomentar
							valorComisionAgencia = ordenMedioDetalleIf.getValorComisionAgencia();//descomentar
							valor = valorSubtotal.subtract(valorDescuentoVenta).add(valorComisionAgencia);//descomentar
							
							if (mapMedioIdMesesTotalOrdenes.get(idMedio) == null || mapMedioIdMesesTotalOrdenes.get(idMedio).isEmpty())
								valorToAdd = mapMesesTotalOrdenes.get(mes);		
							else{ 
								mapMesesTotalOrdenes = mapMedioIdMesesTotalOrdenes.get(idMedio);								
								valorToAdd = mapMesesTotalOrdenes.get(mes);									
							}
								
							valorToAdd = valorToAdd.add(valor);//descomentar
							//valorToAdd = valorToAdd.add(valorSubtotal);	//add sacar
							mapMesesTotalOrdenes.put(mes, valorToAdd);
						}
						
						//ADD 3 AGOSTO
						if (cobraIVA){
							Iterator mapMesesTotalOrdenesIt = mapMesesTotalOrdenes.keySet().iterator();
							
							while(mapMesesTotalOrdenesIt.hasNext()){
								String mesMap = (String) mapMesesTotalOrdenesIt.next();
								BigDecimal valorMes = mapMesesTotalOrdenes.get(mesMap);
									
								valorMes = valorMes.add(valorMes.multiply(BigDecimal.valueOf(IVA)));
								mapMesesTotalOrdenes.put(mesMap,valorMes);
							}
						}
						//END 3 AGOSTO
						
						mapMedioIdMesesTotalOrdenes.put(idMedio, mapMesesTotalOrdenes);
					}
					mapProductosIdMedioIdMesesTotalOrdenes.put(idProducto,mapMedioIdMesesTotalOrdenes); 
				}
				mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanal.put(idCliente, mapProductosIdMedioIdMesesTotalOrdenes);
			}
			System.out.println("mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanal size00: " + mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanal.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	//END 3 AGOSTO
	
	//ADD 8 AGOSTO
	public void obtenerTotalesMesesOrdenesCanalByClienteTMediosProducto(){
		mapClientesIdMedioIdProductosIdMesesTotalOrdenesCanal.clear();
		
		try {
			
			Iterator mapaClienteMedioProductoIdsOrdenesXCanalIt =  mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList.keySet().iterator();
			BigDecimal valorToAdd = new BigDecimal(0D);
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valorSubtotal = new BigDecimal(0D);
			BigDecimal valorDescuentoVenta = new BigDecimal(0D);
			BigDecimal valorComisionAgencia = new BigDecimal(0D);
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			ProductoIf productoProveedorIf;
			GenericoIf genericoIf;
			boolean cobraIVA;
			
			while (mapaClienteMedioProductoIdsOrdenesXCanalIt.hasNext()){
				Long idCliente = (Long) mapaClienteMedioProductoIdsOrdenesXCanalIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapMedioIdProductosIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				Map mapaMedioProductoIdsOrdenesXCanal = mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList.get(idCliente);
				Iterator mapaMedioProductoIdsOrdenesXCanalIt = mapaMedioProductoIdsOrdenesXCanal.keySet().iterator();
				
				while (mapaMedioProductoIdsOrdenesXCanalIt.hasNext()){
					Long idMedio = (Long) mapaMedioProductoIdsOrdenesXCanalIt.next();
					Map<Long,Map<String,BigDecimal>> mapProductoIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					Map mapaProductoIdsOrdenesXCanal = (Map) mapaMedioProductoIdsOrdenesXCanal.get(idMedio);
					Iterator mapaProductoIdsOrdenesXCanalIt = mapaProductoIdsOrdenesXCanal.keySet().iterator();
					
					while (mapaProductoIdsOrdenesXCanalIt.hasNext()){
						Long idProducto = (Long) mapaProductoIdsOrdenesXCanalIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenes = inicializarMapMesesValor();
												
						//ADD 2 AGOSTO
						ArrayList<OrdenMedioDetalleIf> ordenesMedioDetalleXCanalList = (ArrayList<OrdenMedioDetalleIf>)mapaProductoIdsOrdenesXCanal.get(idProducto);
						cobraIVA = true;
						
						for (OrdenMedioDetalleIf ordenMedioDetalleIf : ordenesMedioDetalleXCanalList){
							
							OrdenMedioIf ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenMedioDetalleIf.getOrdenMedioId());
							mes = Utilitarios.getMonthUpperCase(new Date(ordenMedioIf.getFechaOrden().getTime()));	
							
							//COMENTED 17 AGOSTO
							//productoProveedorIf = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioDetalleIf.getProductoProveedorId());
							//genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoProveedorIf.getGenericoId());
							
							//ADD 17 AGOSTO
							productoProveedorIf = mapaProducto.get(ordenMedioDetalleIf.getProductoProveedorId());
							genericoIf = mapaGenerico.get(productoProveedorIf.getGenericoId());
							
							if (genericoIf.getCobraIvaCliente().compareTo(CON_IVA) != 0)	
								cobraIVA = false;
							
							valorSubtotal = ordenMedioDetalleIf.getValorSubtotal();
							valorDescuentoVenta = ordenMedioDetalleIf.getValorDescuentoVenta();//descomentar
							valorComisionAgencia = ordenMedioDetalleIf.getValorComisionAgencia();//descomentar
							valor = valorSubtotal.subtract(valorDescuentoVenta).add(valorComisionAgencia);//descomentar
							
							if (mapProductoIdMesesTotalOrdenes.get(idProducto) == null || mapProductoIdMesesTotalOrdenes.get(idProducto).isEmpty())
								valorToAdd = mapMesesTotalOrdenes.get(mes);		
							else{ 
								mapMesesTotalOrdenes = mapProductoIdMesesTotalOrdenes.get(idProducto);								
								valorToAdd = mapMesesTotalOrdenes.get(mes);									
							}
								
							valorToAdd = valorToAdd.add(valor);//descomentar
							//valorToAdd = valorToAdd.add(valorSubtotal);	//add sacar
							mapMesesTotalOrdenes.put(mes, valorToAdd);
						}
						
						//ADD 3 AGOSTO
						if (cobraIVA){
							Iterator mapMesesTotalOrdenesIt = mapMesesTotalOrdenes.keySet().iterator();
							
							while(mapMesesTotalOrdenesIt.hasNext()){
								String mesMap = (String) mapMesesTotalOrdenesIt.next();
								BigDecimal valorMes = mapMesesTotalOrdenes.get(mesMap);
									
								valorMes = valorMes.add(valorMes.multiply(BigDecimal.valueOf(IVA)));
								mapMesesTotalOrdenes.put(mesMap,valorMes);
							}
						}
						//END 3 AGOSTO
						
						mapProductoIdMesesTotalOrdenes.put(idProducto, mapMesesTotalOrdenes);
					}
					mapMedioIdProductosIdMesesTotalOrdenes.put(idMedio,mapProductoIdMesesTotalOrdenes); 
				}
				mapClientesIdMedioIdProductosIdMesesTotalOrdenesCanal.put(idCliente, mapMedioIdProductosIdMesesTotalOrdenes);
			}
			System.out.println("mapClientesIdMedioIdProductosIdMesesTotalOrdenesCanal size00: " + mapClientesIdMedioIdProductosIdMesesTotalOrdenesCanal.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
	//END 8 AGOSTO
	
	//ADD 8 AGOSTO
	public void obtenerTotalesMesesOrdenesCanalByTMediosClienteProducto(){
		mapMedioIdClientesIdProductosIdMesesTotalOrdenesCanal.clear();
		
		try {
			
			Iterator mapaMedioClienteProductoIdsOrdenesXCanalIt =  mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList.keySet().iterator();
			BigDecimal valorToAdd = new BigDecimal(0D);
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valorSubtotal = new BigDecimal(0D);
			BigDecimal valorDescuentoVenta = new BigDecimal(0D);
			BigDecimal valorComisionAgencia = new BigDecimal(0D);
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			ProductoIf productoProveedorIf;
			GenericoIf genericoIf;
			boolean cobraIVA;
			
			while (mapaMedioClienteProductoIdsOrdenesXCanalIt.hasNext()){
				Long idMedio = (Long) mapaMedioClienteProductoIdsOrdenesXCanalIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapClienteIdProductosIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				Map mapaClienteProductoIdsOrdenesXCanal = mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList.get(idMedio);
				Iterator mapaClienteProductoIdsOrdenesXCanalIt = mapaClienteProductoIdsOrdenesXCanal.keySet().iterator();
				
				while (mapaClienteProductoIdsOrdenesXCanalIt.hasNext()){
					Long idCliente = (Long) mapaClienteProductoIdsOrdenesXCanalIt.next();
					Map<Long,Map<String,BigDecimal>> mapProductoIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					Map mapaProductoIdsOrdenesXCanal = (Map) mapaClienteProductoIdsOrdenesXCanal.get(idCliente);
					Iterator mapaProductoIdsOrdenesXCanalIt = mapaProductoIdsOrdenesXCanal.keySet().iterator();
					
					while (mapaProductoIdsOrdenesXCanalIt.hasNext()){
						Long idProducto = (Long) mapaProductoIdsOrdenesXCanalIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenes = inicializarMapMesesValor();
												
						//ADD 2 AGOSTO
						ArrayList<OrdenMedioDetalleIf> ordenesMedioDetalleXCanalList = (ArrayList<OrdenMedioDetalleIf>)mapaProductoIdsOrdenesXCanal.get(idProducto);
						cobraIVA = true;
						
						for (OrdenMedioDetalleIf ordenMedioDetalleIf : ordenesMedioDetalleXCanalList){
							
							OrdenMedioIf ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenMedioDetalleIf.getOrdenMedioId());
							mes = Utilitarios.getMonthUpperCase(new Date(ordenMedioIf.getFechaOrden().getTime()));	
							
							//COMENTED 17 AGOSTO
							//productoProveedorIf = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioDetalleIf.getProductoProveedorId());
							//genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoProveedorIf.getGenericoId());
							
							//ADD 17 AGOSTO
							productoProveedorIf = mapaProducto.get(ordenMedioDetalleIf.getProductoProveedorId());
							genericoIf = mapaGenerico.get(productoProveedorIf.getGenericoId());
							
							if (genericoIf.getCobraIvaCliente().compareTo(CON_IVA) != 0)	
								cobraIVA = false;
							
							valorSubtotal = ordenMedioDetalleIf.getValorSubtotal();
							valorDescuentoVenta = ordenMedioDetalleIf.getValorDescuentoVenta();//descomentar
							valorComisionAgencia = ordenMedioDetalleIf.getValorComisionAgencia();//descomentar
							valor = valorSubtotal.subtract(valorDescuentoVenta).add(valorComisionAgencia);//descomentar
							
							if (mapProductoIdMesesTotalOrdenes.get(idProducto) == null || mapProductoIdMesesTotalOrdenes.get(idProducto).isEmpty())
								valorToAdd = mapMesesTotalOrdenes.get(mes);		
							else{ 
								mapMesesTotalOrdenes = mapProductoIdMesesTotalOrdenes.get(idProducto);								
								valorToAdd = mapMesesTotalOrdenes.get(mes);									
							}
								
							valorToAdd = valorToAdd.add(valor);//descomentar
							//valorToAdd = valorToAdd.add(valorSubtotal);	//add sacar
							mapMesesTotalOrdenes.put(mes, valorToAdd);
						}
						
						//ADD 3 AGOSTO
						if (cobraIVA){
							Iterator mapMesesTotalOrdenesIt = mapMesesTotalOrdenes.keySet().iterator();
							
							while(mapMesesTotalOrdenesIt.hasNext()){
								String mesMap = (String) mapMesesTotalOrdenesIt.next();
								BigDecimal valorMes = mapMesesTotalOrdenes.get(mesMap);
									
								valorMes = valorMes.add(valorMes.multiply(BigDecimal.valueOf(IVA)));
								mapMesesTotalOrdenes.put(mesMap,valorMes);
							}
						}
						//END 3 AGOSTO
						
						mapProductoIdMesesTotalOrdenes.put(idProducto, mapMesesTotalOrdenes);
					}
					mapClienteIdProductosIdMesesTotalOrdenes.put(idCliente,mapProductoIdMesesTotalOrdenes); 
				}
				mapMedioIdClientesIdProductosIdMesesTotalOrdenesCanal.put(idMedio, mapClienteIdProductosIdMesesTotalOrdenes);
			}
			System.out.println("mapMedioIdClientesIdProductosIdMesesTotalOrdenesCanal size00: " + mapMedioIdClientesIdProductosIdMesesTotalOrdenesCanal.size());
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
	//END 8 AGOSTO
	
	//ADD 10 AGOSTO
	//MEDIOS DEL FILTRO CLIENTE/T-MEDIO/MEDIOS/PRODUCTOS
	/*public void obtenerTotalesMesesOrdenesProductoAndCanalByMedioFiltroTMCP(){
		mapClientesIdMesesTotalOrdenesProductoAndCanal.clear();	
					
		try{
			
			Map<String,BigDecimal> mapMesesTotalOrdenesProductoAndCanalTemp; 
			String mes = "";
			BigDecimal valorToAdd = new BigDecimal(0);
			BigDecimal valor = new BigDecimal(0);
				
			Iterator mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
			while (mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
				Long idCliente = (Long) mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
				
				//ADD 9 AGOSTO
				//se inicializa el mapa de meses con valor 0.0
				mapMesesTotalOrdenesProductoAndCanalTemp = inicializarMapMesesValor();
						
				Map mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal = mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
				Iterator mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
				while (mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idMedio = (Long) mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
							
					Map mapProductoIdMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
					Iterator mapProductoIdMesesTotalOrdenesProductoAndCanalIt = mapProductoIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
						
					while (mapProductoIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProducto = (Long) mapProductoIdMesesTotalOrdenesProductoAndCanalIt.next();
								
						Map mesesTotalOrdenesProductoAndCanal= (Map) mapProductoIdMesesTotalOrdenesProductoAndCanal.get(idProducto);
								
						Iterator mesesTotalOrdenesProductoAndCanalIt = mesesTotalOrdenesProductoAndCanal.keySet().iterator();
						while (mesesTotalOrdenesProductoAndCanalIt.hasNext()){
							mes = (String) mesesTotalOrdenesProductoAndCanalIt.next();
							valorToAdd = (BigDecimal) mesesTotalOrdenesProductoAndCanal.get(mes);		
										
							valor = mapMesesTotalOrdenesProductoAndCanalTemp.get(mes);
							valor = valor.add(valorToAdd);
										
							mapMesesTotalOrdenesProductoAndCanalTemp.put(mes,valor);
						}
									
						//mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.remove(idMedio);
						//mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.put(idMedio,mapMesesTotalOrdenesProductoAndCanalTemp);
					}
					//mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.put(idProducto,mapMesesTotalOrdenesProductoAndCanalTemp);
				}
				mapClientesIdMesesTotalOrdenesProductoAndCanal.put(idCliente,mapMesesTotalOrdenesProductoAndCanalTemp);
			}
					
		}catch(Exception e){
			e.printStackTrace();
		}
	}	*/		
	//END 10 AGOSTO
	
	//ADD 10 AGOSTO
	//MEDIOS DEL FILTRO T-MEDIO/MEDIOS/CLIENTE/PRODUCTOS
	public void obtenerTotalesMesesOrdenesProductoAndCanalByMedioFiltroTMCP(){
		mapMediosIdMesesTotalOrdenesProductoAndCanal.clear();	
					
		try{
			
			Map<String,BigDecimal> mapMesesTotalOrdenesProductoAndCanalTemp; 
			String mes = "";
			BigDecimal valorToAdd = new BigDecimal(0);
			BigDecimal valor = new BigDecimal(0);
				
			Iterator mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
			while (mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
				Long idMedio = (Long) mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
				
				//ADD 9 AGOSTO
				//se inicializa el mapa de meses con valor 0.0
				mapMesesTotalOrdenesProductoAndCanalTemp = inicializarMapMesesValor();
						
				Map mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal = mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
				Iterator mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
				while (mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idCliente = (Long) mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
							
					Map mapProductoIdMesesTotalOrdenesProductoAndCanal = (Map) mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
					Iterator mapProductoIdMesesTotalOrdenesProductoAndCanalIt = mapProductoIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
						
					while (mapProductoIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProducto = (Long) mapProductoIdMesesTotalOrdenesProductoAndCanalIt.next();
								
						Map mesesTotalOrdenesProductoAndCanal= (Map) mapProductoIdMesesTotalOrdenesProductoAndCanal.get(idProducto);
								
						Iterator mesesTotalOrdenesProductoAndCanalIt = mesesTotalOrdenesProductoAndCanal.keySet().iterator();
						while (mesesTotalOrdenesProductoAndCanalIt.hasNext()){
							mes = (String) mesesTotalOrdenesProductoAndCanalIt.next();
							valorToAdd = (BigDecimal) mesesTotalOrdenesProductoAndCanal.get(mes);		
										
							valor = mapMesesTotalOrdenesProductoAndCanalTemp.get(mes);
							valor = valor.add(valorToAdd);
										
							mapMesesTotalOrdenesProductoAndCanalTemp.put(mes,valor);
						}
									
						//mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.remove(idMedio);
						//mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.put(idMedio,mapMesesTotalOrdenesProductoAndCanalTemp);
					}
					//mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.put(idProducto,mapMesesTotalOrdenesProductoAndCanalTemp);
				}
				mapMediosIdMesesTotalOrdenesProductoAndCanal.put(idMedio,mapMesesTotalOrdenesProductoAndCanalTemp);
			}
					
		}catch(Exception e){
			e.printStackTrace();
		}
	}			
	//END 10 AGOSTO
	
	//ADD 10 AGOSTO
	//CLIENTES DEL FILTRO CLIENTE/T-MEDIO/MEDIOS/PRODUCTOS
	public void obtenerTotalesMesesOrdenesProductoAndCanalByClienteFiltroCTMP(){
		mapClientesIdMesesTotalOrdenesProductoAndCanal.clear();	
					
		try{
			
			Map<String,BigDecimal> mapMesesTotalOrdenesProductoAndCanalTemp; 
			String mes = "";
			BigDecimal valorToAdd = new BigDecimal(0);
			BigDecimal valor = new BigDecimal(0);
				
			Iterator mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
			while (mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
				Long idCliente = (Long) mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
				
				//ADD 9 AGOSTO
				//se inicializa el mapa de meses con valor 0.0
				mapMesesTotalOrdenesProductoAndCanalTemp = inicializarMapMesesValor();
						
				Map mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal = mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
				Iterator mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
				while (mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idMedio = (Long) mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
							
					Map mapProductoIdMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
					Iterator mapProductoIdMesesTotalOrdenesProductoAndCanalIt = mapProductoIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
						
					while (mapProductoIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProducto = (Long) mapProductoIdMesesTotalOrdenesProductoAndCanalIt.next();
								
						Map mesesTotalOrdenesProductoAndCanal= (Map) mapProductoIdMesesTotalOrdenesProductoAndCanal.get(idProducto);
								
						Iterator mesesTotalOrdenesProductoAndCanalIt = mesesTotalOrdenesProductoAndCanal.keySet().iterator();
						while (mesesTotalOrdenesProductoAndCanalIt.hasNext()){
							mes = (String) mesesTotalOrdenesProductoAndCanalIt.next();
							valorToAdd = (BigDecimal) mesesTotalOrdenesProductoAndCanal.get(mes);		
										
							valor = mapMesesTotalOrdenesProductoAndCanalTemp.get(mes);
							valor = valor.add(valorToAdd);
										
							mapMesesTotalOrdenesProductoAndCanalTemp.put(mes,valor);
						}
									
						//mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.remove(idMedio);
						//mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.put(idMedio,mapMesesTotalOrdenesProductoAndCanalTemp);
					}
					//mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.put(idProducto,mapMesesTotalOrdenesProductoAndCanalTemp);
				}
				mapClientesIdMesesTotalOrdenesProductoAndCanal.put(idCliente,mapMesesTotalOrdenesProductoAndCanalTemp);
			}
					
		}catch(Exception e){
			e.printStackTrace();
		}
	}			
	
	//END 10 AGOSTO
	
	//ADD 9 AGOSTO
	//CLIENTES DEL FILTRO CLIENTE/PRODUCTOS/T-MEDIO/MEDIOS
	public void obtenerTotalesMesesOrdenesProductoAndCanalByClienteFiltroCPTM(){
		mapClientesIdMesesTotalOrdenesProductoAndCanal.clear();	
					
		try{
			
			Map<String,BigDecimal> mapMesesTotalOrdenesProductoAndCanalTemp; 
			String mes = "";
			BigDecimal valorToAdd = new BigDecimal(0);
			BigDecimal valor = new BigDecimal(0);
				
			Iterator mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
			while (mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
				Long idCliente = (Long) mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
				
				//ADD 9 AGOSTO
				//se inicializa el mapa de meses con valor 0.0
				mapMesesTotalOrdenesProductoAndCanalTemp = inicializarMapMesesValor();
						
				Map mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal = mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
				Iterator mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
				while (mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idProducto = (Long) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
							
					Map mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idProducto);
					Iterator mapMedioIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
						
					while (mapMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idMedio = (Long) mapMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
								
						Map mesesTotalOrdenesProductoAndCanal= (Map) mapMedioIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
								
						Iterator mesesTotalOrdenesProductoAndCanalIt = mesesTotalOrdenesProductoAndCanal.keySet().iterator();
						while (mesesTotalOrdenesProductoAndCanalIt.hasNext()){
							mes = (String) mesesTotalOrdenesProductoAndCanalIt.next();
							valorToAdd = (BigDecimal) mesesTotalOrdenesProductoAndCanal.get(mes);		
										
							valor = mapMesesTotalOrdenesProductoAndCanalTemp.get(mes);
							valor = valor.add(valorToAdd);
										
							mapMesesTotalOrdenesProductoAndCanalTemp.put(mes,valor);
						}
									
						//mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.remove(idMedio);
						//mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.put(idMedio,mapMesesTotalOrdenesProductoAndCanalTemp);
					}
					//mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.put(idProducto,mapMesesTotalOrdenesProductoAndCanalTemp);
				}
				mapClientesIdMesesTotalOrdenesProductoAndCanal.put(idCliente,mapMesesTotalOrdenesProductoAndCanalTemp);
			}
					
		}catch(Exception e){
			e.printStackTrace();
		}
	}			
	//END 9 AGOSTO
	
	//ADD 10 AGOSTO
	//MEDIOS/CLIENTE DEL FILTRO T-MEDIO/MEDIOS/CLIENTE/PRODUCTOS
	public void obtenerTotalesMesesOrdenesProductoAndCanalByMedioTMediosFiltroTMCP(){
		mapMediosIdClientesIdMesesTotalOrdenesProductoAndCanal.clear();	
					
		try{
			Map<Long,Map<String,BigDecimal>> mapClienteIdMesesTotalOrdenesProductoAndCanalTemp; 
			Map<String,BigDecimal> mapMesesTotalOrdenesProductoAndCanalTemp; 
			String mes = "";
			BigDecimal valorToAdd = new BigDecimal(0);
			BigDecimal valor = new BigDecimal(0);
				
			Iterator mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
			while (mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
				Long idMedio = (Long) mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
				mapClienteIdMesesTotalOrdenesProductoAndCanalTemp = new LinkedHashMap<Long, Map<String,BigDecimal>>();
						
				Map mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal = mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
				Iterator mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
				while (mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idCliente = (Long) mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
					
					//ADD 9 AGOSTO
					//se inicializa el mapa de meses con valor 0.0
					mapMesesTotalOrdenesProductoAndCanalTemp = inicializarMapMesesValor();
							
					Map mapProductoIdMesesTotalOrdenesProductoAndCanal = (Map) mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
					Iterator mapProductoIdMesesTotalOrdenesProductoAndCanalIt = mapProductoIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
						
					while (mapProductoIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProducto = (Long) mapProductoIdMesesTotalOrdenesProductoAndCanalIt.next();
								
						Map mesesTotalOrdenesProductoAndCanal= (Map) mapProductoIdMesesTotalOrdenesProductoAndCanal.get(idProducto);
								
						Iterator mesesTotalOrdenesProductoAndCanalIt = mesesTotalOrdenesProductoAndCanal.keySet().iterator();
						while (mesesTotalOrdenesProductoAndCanalIt.hasNext()){
							mes = (String) mesesTotalOrdenesProductoAndCanalIt.next();
							valorToAdd = (BigDecimal) mesesTotalOrdenesProductoAndCanal.get(mes);		
										
							valor = mapMesesTotalOrdenesProductoAndCanalTemp.get(mes);
							valor = valor.add(valorToAdd);
									
							mapMesesTotalOrdenesProductoAndCanalTemp.put(mes,valor);
						}
									
						//mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.remove(idMedio);
						//mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.put(idMedio,mapMesesTotalOrdenesProductoAndCanalTemp);
						
					}
					mapClienteIdMesesTotalOrdenesProductoAndCanalTemp.put(idCliente,mapMesesTotalOrdenesProductoAndCanalTemp);
				}
				mapMediosIdClientesIdMesesTotalOrdenesProductoAndCanal.put(idMedio,mapClienteIdMesesTotalOrdenesProductoAndCanalTemp);
			}
					
		}catch(Exception e){
			e.printStackTrace();
		}
	}		
	//END 10 AGOSTO
	
	//ADD 10 AGOSTO
	//CLIENTE/MEDIOS DEL FILTRO CLIENTE/T-MEDIO/MEDIOS/PRODUCTOS
	public void obtenerTotalesMesesOrdenesProductoAndCanalByClienteTMediosFiltroCTMP(){
		mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanal.clear();	
					
		try{
			Map<Long,Map<String,BigDecimal>> mapMedioIdMesesTotalOrdenesProductoAndCanalTemp; 
			Map<String,BigDecimal> mapMesesTotalOrdenesProductoAndCanalTemp; 
			String mes = "";
			BigDecimal valorToAdd = new BigDecimal(0);
			BigDecimal valor = new BigDecimal(0);
				
			Iterator mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
			while (mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
				Long idCliente = (Long) mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
				mapMedioIdMesesTotalOrdenesProductoAndCanalTemp = new LinkedHashMap<Long, Map<String,BigDecimal>>();
						
				Map mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal = mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
				Iterator mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
				while (mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idMedio = (Long) mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
					
					//ADD 9 AGOSTO
					//se inicializa el mapa de meses con valor 0.0
					mapMesesTotalOrdenesProductoAndCanalTemp = inicializarMapMesesValor();
							
					Map mapProductoIdMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
					Iterator mapProductoIdMesesTotalOrdenesProductoAndCanalIt = mapProductoIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
						
					while (mapProductoIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProducto = (Long) mapProductoIdMesesTotalOrdenesProductoAndCanalIt.next();
								
						Map mesesTotalOrdenesProductoAndCanal= (Map) mapProductoIdMesesTotalOrdenesProductoAndCanal.get(idProducto);
								
						Iterator mesesTotalOrdenesProductoAndCanalIt = mesesTotalOrdenesProductoAndCanal.keySet().iterator();
						while (mesesTotalOrdenesProductoAndCanalIt.hasNext()){
							mes = (String) mesesTotalOrdenesProductoAndCanalIt.next();
							valorToAdd = (BigDecimal) mesesTotalOrdenesProductoAndCanal.get(mes);		
										
							valor = mapMesesTotalOrdenesProductoAndCanalTemp.get(mes);
							valor = valor.add(valorToAdd);
									
							mapMesesTotalOrdenesProductoAndCanalTemp.put(mes,valor);
						}
									
						//mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.remove(idMedio);
						//mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.put(idMedio,mapMesesTotalOrdenesProductoAndCanalTemp);
						
					}
					mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.put(idMedio,mapMesesTotalOrdenesProductoAndCanalTemp);
				}
				mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idCliente,mapMedioIdMesesTotalOrdenesProductoAndCanalTemp);
			}
					
		}catch(Exception e){
			e.printStackTrace();
		}
	}		
	//END 9 AGOSTO
	
	
	//ADD 9 AGOSTO
	//CLIENTE/PRODUCTOS DEL FILTRO CLIENTE/PRODUCTOS/T-MEDIO/MEDIOS
	public void obtenerTotalesMesesOrdenesProductoAndCanalByClienteProductosFiltroCPTM(){
		mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.clear();	
				
		try{
			Map<Long,Map<String,BigDecimal>> mapProductoIdMesesTotalOrdenesProductoAndCanalTemp; 
			Map<String,BigDecimal> mapMesesTotalOrdenesProductoAndCanalTemp; 
			String mes = "";
			BigDecimal valorToAdd = new BigDecimal(0);
			BigDecimal valor = new BigDecimal(0);
			
			Iterator mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
				
			while (mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
				Long idCliente = (Long) mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
				mapProductoIdMesesTotalOrdenesProductoAndCanalTemp = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
				Map mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal = mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
				Iterator mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
				
				while (mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idProducto = (Long) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
					
					//ADD 9 AGOSTO
					//se inicializa el mapa de meses con valor 0.0
					mapMesesTotalOrdenesProductoAndCanalTemp = inicializarMapMesesValor();
						
					Map mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idProducto);
					Iterator mapMedioIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
					while (mapMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idMedio = (Long) mapMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
							
						Map mesesTotalOrdenesProductoAndCanal= (Map) mapMedioIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
							
						Iterator mesesTotalOrdenesProductoAndCanalIt = mesesTotalOrdenesProductoAndCanal.keySet().iterator();
						while (mesesTotalOrdenesProductoAndCanalIt.hasNext()){
							mes = (String) mesesTotalOrdenesProductoAndCanalIt.next();
							valorToAdd = (BigDecimal) mesesTotalOrdenesProductoAndCanal.get(mes);		
									
							valor = mapMesesTotalOrdenesProductoAndCanalTemp.get(mes);
							valor = valor.add(valorToAdd);
									
							mapMesesTotalOrdenesProductoAndCanalTemp.put(mes,valor);
						}
								
						//mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.remove(idMedio);
						//mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.put(idMedio,mapMesesTotalOrdenesProductoAndCanalTemp);
					}
					mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.put(idProducto,mapMesesTotalOrdenesProductoAndCanalTemp);
				}
				mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.put(idCliente,mapProductoIdMesesTotalOrdenesProductoAndCanalTemp);
			}
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}		
	//END 9 AGOSTO
	
	//ADD 10 AGOSTO
	//MEDIO/PRODUCTOS DEL FILTRO T-MEDIO/MEDIOS/CLIENTE/PRODUCTOS
	public void obtenerTotalesMesesOrdenesProductoAndCanalByMedioProductosFiltroTMCP(){
		mapMediosIdProductoIdMesesTotalOrdenesProductoAndCanal.clear();	
		//mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.clear();
		
		try{
			Map<Long,Map<String,BigDecimal>> mapProductoIdMesesTotalOrdenesProductoAndCanalTemp; 
			Map<String,BigDecimal> mapMesesTotalOrdenesProductoAndCanalTemp; 
			String mes = "";
			BigDecimal valorToAdd = new BigDecimal(0);
			BigDecimal valor = new BigDecimal(0);
				
			Iterator mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
				
			while (mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
				Long idMedio = (Long) mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
				mapProductoIdMesesTotalOrdenesProductoAndCanalTemp = new LinkedHashMap<Long, Map<String,BigDecimal>>();
				
				Map mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal = mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
				Iterator mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
				
				while (mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idCliente = (Long) mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
						
					Map mapProductoIdMesesTotalOrdenesProductoAndCanal = (Map) mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
					Iterator mapProductoIdMesesTotalOrdenesProductoAndCanalIt = mapProductoIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
					while (mapProductoIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProducto = (Long) mapProductoIdMesesTotalOrdenesProductoAndCanalIt.next();
							
						Map mesesTotalOrdenesProductoAndCanal= (Map) mapProductoIdMesesTotalOrdenesProductoAndCanal.get(idProducto);
													
						if (!mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.containsKey(idProducto)){
							mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.put(idProducto, mesesTotalOrdenesProductoAndCanal);	
								
						}else{
							mapMesesTotalOrdenesProductoAndCanalTemp = mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.get(idProducto);
								
							Iterator mesesTotalOrdenesProductoAndCanalIt = mesesTotalOrdenesProductoAndCanal.keySet().iterator();
							while (mesesTotalOrdenesProductoAndCanalIt.hasNext()){
								mes = (String) mesesTotalOrdenesProductoAndCanalIt.next();
								valorToAdd = (BigDecimal) mesesTotalOrdenesProductoAndCanal.get(mes);		
								
								valor = mapMesesTotalOrdenesProductoAndCanalTemp.get(mes);
								valor = valor.add(valorToAdd);
									
								mapMesesTotalOrdenesProductoAndCanalTemp.put(mes,valor);
							}
								
							mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.remove(idProducto);
							mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.put(idProducto,mapMesesTotalOrdenesProductoAndCanalTemp);
						}
					}
				}
				mapMediosIdProductoIdMesesTotalOrdenesProductoAndCanal.put(idMedio,mapProductoIdMesesTotalOrdenesProductoAndCanalTemp);
			}
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//END 10 AGOSTO
	
	//ADD 9 AGOSTO
	//CLIENTE/PRODUCTOS DEL FILTRO CLIENTE/T-MEDIO/MEDIOS/PRODUCTOS
	public void obtenerTotalesMesesOrdenesProductoAndCanalByClienteProductosFiltroCTMP(){
		mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.clear();	
		//mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.clear();
		
		try{
			Map<Long,Map<String,BigDecimal>> mapProductoIdMesesTotalOrdenesProductoAndCanalTemp; 
			Map<String,BigDecimal> mapMesesTotalOrdenesProductoAndCanalTemp; 
			String mes = "";
			BigDecimal valorToAdd = new BigDecimal(0);
			BigDecimal valor = new BigDecimal(0);
				
			Iterator mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
				
			while (mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
				Long idCliente = (Long) mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
				mapProductoIdMesesTotalOrdenesProductoAndCanalTemp = new LinkedHashMap<Long, Map<String,BigDecimal>>();
				
				Map mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal = mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
				Iterator mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
				
				while (mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idMedio = (Long) mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
						
					Map mapProductoIdMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
					Iterator mapProductoIdMesesTotalOrdenesProductoAndCanalIt = mapProductoIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
					
					while (mapProductoIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProducto = (Long) mapProductoIdMesesTotalOrdenesProductoAndCanalIt.next();
							
						Map mesesTotalOrdenesProductoAndCanal= (Map) mapProductoIdMesesTotalOrdenesProductoAndCanal.get(idProducto);
													
						if (!mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.containsKey(idProducto)){
							mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.put(idProducto, mesesTotalOrdenesProductoAndCanal);	
								
						}else{
							mapMesesTotalOrdenesProductoAndCanalTemp = mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.get(idProducto);
								
							Iterator mesesTotalOrdenesProductoAndCanalIt = mesesTotalOrdenesProductoAndCanal.keySet().iterator();
							while (mesesTotalOrdenesProductoAndCanalIt.hasNext()){
								mes = (String) mesesTotalOrdenesProductoAndCanalIt.next();
								valorToAdd = (BigDecimal) mesesTotalOrdenesProductoAndCanal.get(mes);		
								
								valor = mapMesesTotalOrdenesProductoAndCanalTemp.get(mes);
								valor = valor.add(valorToAdd);
									
								mapMesesTotalOrdenesProductoAndCanalTemp.put(mes,valor);
							}
								
							mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.remove(idProducto);
							mapProductoIdMesesTotalOrdenesProductoAndCanalTemp.put(idProducto,mapMesesTotalOrdenesProductoAndCanalTemp);
						}
					}
				}
				mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.put(idCliente,mapProductoIdMesesTotalOrdenesProductoAndCanalTemp);
			}
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//END 9 AGOSTO
	
	//ADD 9 AGOSTO
	//CLIENTE/T-MEDIOS/MEDIOS DEL FILTRO CLIENTE/PRODUCTOS/T-MEDIO/MEDIOS
	public void obtenerTotalesMesesOrdenesProductoAndCanalByClienteTMediosFiltroCPTM(){
		mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanal.clear();	
		//mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.clear();
		
		try{
			Map<Long,Map<String,BigDecimal>> mapMedioIdMesesTotalOrdenesProductoAndCanalTemp; 
			Map<String,BigDecimal> mapMesesTotalOrdenesProductoAndCanalTemp; 
			String mes = "";
			BigDecimal valorToAdd = new BigDecimal(0);
			BigDecimal valor = new BigDecimal(0);
			
			Iterator mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
			
			while (mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
				Long idCliente = (Long) mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
				mapMedioIdMesesTotalOrdenesProductoAndCanalTemp = new LinkedHashMap<Long, Map<String,BigDecimal>>();
				
				Map mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal = mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
				Iterator mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
			
				while (mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idProducto = (Long) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
					
					Map mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idProducto);
					Iterator mapMedioIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator();
				
					while (mapMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idMedio = (Long) mapMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
						
						Map mesesTotalOrdenesProductoAndCanal= (Map) mapMedioIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
												
						if (!mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.containsKey(idMedio)){
							mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.put(idMedio, mesesTotalOrdenesProductoAndCanal);	
							
						}else{
							mapMesesTotalOrdenesProductoAndCanalTemp = mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.get(idMedio);
							
							Iterator mesesTotalOrdenesProductoAndCanalIt = mesesTotalOrdenesProductoAndCanal.keySet().iterator();
							while (mesesTotalOrdenesProductoAndCanalIt.hasNext()){
								mes = (String) mesesTotalOrdenesProductoAndCanalIt.next();
								valorToAdd = (BigDecimal) mesesTotalOrdenesProductoAndCanal.get(mes);		
								
								valor = mapMesesTotalOrdenesProductoAndCanalTemp.get(mes);
								valor = valor.add(valorToAdd);
								
								mapMesesTotalOrdenesProductoAndCanalTemp.put(mes,valor);
							}
							
							mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.remove(idMedio);
							mapMedioIdMesesTotalOrdenesProductoAndCanalTemp.put(idMedio,mapMesesTotalOrdenesProductoAndCanalTemp);
						}
					}
				}
				mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idCliente,mapMedioIdMesesTotalOrdenesProductoAndCanalTemp);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//END 9 AGOSTO
	
	//ADD 15 AGOSTO
	public void obtenerTotalesMesesOrdenesByClienteProductoTMedios(){
		mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.clear();
		
		try {
			
			Iterator mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoIt =  mapClientesIdProductosIdMedioIdMesesTotalOrdenesProducto.keySet().iterator();
			BigDecimal valorToAdd = new BigDecimal(0D);
			//ADD 2 AGOSTO
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			while (mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoIt.hasNext()){
				Long idCliente = (Long) mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapProductosIdMedioIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				Map mapaProductoMedioIdsOrdenesXProducto = mapClientesIdProductosIdMedioIdMesesTotalOrdenesProducto.get(idCliente);
				Iterator mapaProductoMedioIdsOrdenesXProductoIt = mapaProductoMedioIdsOrdenesXProducto.keySet().iterator();
				
				while (mapaProductoMedioIdsOrdenesXProductoIt.hasNext()){
					Long idProducto = (Long) mapaProductoMedioIdsOrdenesXProductoIt.next();
					Map<Long,Map<String,BigDecimal>> mapMedioIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					Map mapaMedioIdsOrdenesXProducto = (Map) mapaProductoMedioIdsOrdenesXProducto.get(idProducto);
					Iterator mapaMedioIdsOrdenesXProductoIt = mapaMedioIdsOrdenesXProducto.keySet().iterator();
					
					while (mapaMedioIdsOrdenesXProductoIt.hasNext()){
						Long idMedio = (Long) mapaMedioIdsOrdenesXProductoIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenes = inicializarMapMesesValor();
						
						//ADD 2 AGOSTO
						Map mapMesesTotalOrdenesProducto = (Map) mapaMedioIdsOrdenesXProducto.get(idMedio);
						Iterator mapMesesTotalOrdenesProductoIt = mapMesesTotalOrdenesProducto.keySet().iterator();
						
						while (mapMesesTotalOrdenesProductoIt.hasNext()){
							mes = (String) mapMesesTotalOrdenesProductoIt.next();
							valor = (BigDecimal) mapMesesTotalOrdenesProducto.get(mes);
						
							Map mapProductosIdMedioIdMesesTotalOrdenesCanal =  mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanal.get(idCliente);
							
							if (mapProductosIdMedioIdMesesTotalOrdenesCanal != null && !mapProductosIdMedioIdMesesTotalOrdenesCanal.isEmpty()){
								Map mapMedioIdMesesTotalOrdenesCanal = (Map) mapProductosIdMedioIdMesesTotalOrdenesCanal.get(idProducto);
								
								if (mapMedioIdMesesTotalOrdenesCanal != null && !mapMedioIdMesesTotalOrdenesCanal.isEmpty()){
									Map mapMesesTotalOrdenesCanal = (Map) mapMedioIdMesesTotalOrdenesCanal.get(idMedio);
									
									if (mapMesesTotalOrdenesCanal != null && !mapMesesTotalOrdenesCanal.isEmpty()){
										valorToAdd = (BigDecimal) mapMesesTotalOrdenesCanal.get(mes);
										
										valor = valor.add(valorToAdd);
									}
								}
							}
							mapMesesTotalOrdenes.put(mes, valor);
						}
						//END 2 AGOSTO
						mapMedioIdMesesTotalOrdenes.put(idMedio, mapMesesTotalOrdenes);
					}
					mapProductosIdMedioIdMesesTotalOrdenes.put(idProducto,mapMedioIdMesesTotalOrdenes);
				}
				mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idCliente, mapProductosIdMedioIdMesesTotalOrdenes);
			}
			
			//para los clientes,productos,medios de los mapas Ordenes x Canal que no estan en los mapas de Ordenes de Producto 
			Iterator mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanalIt =  mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanal.keySet().iterator();
			Map<String,BigDecimal> mapMesesTotalOrdenesFaltantes;
			BigDecimal valorFaltante= new BigDecimal(0D);
			BigDecimal valorFaltanteToAdd = new BigDecimal(0D);
			String mesFaltante = "";
			
			Map<Long,Map<Long,Map<String,BigDecimal>>> mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal;
			Map<Long,Map<String,BigDecimal>> mapMedioIdMesesTotalOrdenesProductoAndCanal;
			Map<String,BigDecimal> mapMesesTotalOrdenesProductoAndCanal;
			
			//boolean 
			
			while (mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanalIt.hasNext()){
				Long idClienteFaltante = (Long) mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanalIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapProductosIdMedioIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				//mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal =  mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
				
				Map mapaProductoMedioIdsOrdenesXCanal = mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanal.get(idClienteFaltante);
				Iterator mapaProductoMedioIdsOrdenesXCanalIt = mapaProductoMedioIdsOrdenesXCanal.keySet().iterator();
				
				while (mapaProductoMedioIdsOrdenesXCanalIt.hasNext()){
					Long idProductoFaltante = (Long) mapaProductoMedioIdsOrdenesXCanalIt.next();
					Map<Long,Map<String,BigDecimal>> mapMedioIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					//mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idProducto);
					
					Map mapaMedioIdsOrdenesXCanal = (Map) mapaProductoMedioIdsOrdenesXCanal.get(idProductoFaltante);
					Iterator mapaMedioIdsOrdenesXCanalIt = mapaMedioIdsOrdenesXCanal.keySet().iterator();
					
					while (mapaMedioIdsOrdenesXCanalIt.hasNext()){
						Long idMedioFaltante = (Long) mapaMedioIdsOrdenesXCanalIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenesFaltantes = inicializarMapMesesValor();
						
						//mapMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
						
						//ADD 2 AGOSTO
						Map mapMesesTotalOrdenesCanal = (Map) mapaMedioIdsOrdenesXCanal.get(idMedioFaltante);
						Iterator mapMesesTotalOrdenesCanalIt = mapMesesTotalOrdenesCanal.keySet().iterator();
						
						while (mapMesesTotalOrdenesCanalIt.hasNext()){
							mesFaltante = (String) mapMesesTotalOrdenesCanalIt.next();
							valorFaltante = (BigDecimal) mapMesesTotalOrdenesCanal.get(mesFaltante);
							
							mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal =  mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idClienteFaltante);
							
							if (mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal != null && !mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.isEmpty()){
								mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idProductoFaltante);
								
								if (mapMedioIdMesesTotalOrdenesProductoAndCanal != null && !mapMedioIdMesesTotalOrdenesProductoAndCanal.isEmpty()){
									mapMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdMesesTotalOrdenesProductoAndCanal.get(idMedioFaltante);
									
									if (mapMesesTotalOrdenesProductoAndCanal != null && !mapMesesTotalOrdenesProductoAndCanal.isEmpty()){
										
										if (!isMedioIdInTotalesOrdenesProducto(idClienteFaltante,idProductoFaltante,idMedioFaltante)){
											valorFaltanteToAdd = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mesFaltante);
											
											valorFaltante = valorFaltante.add(valorFaltanteToAdd);
											
											mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
											mapMedioIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante, mapMesesTotalOrdenesProductoAndCanal);	
											mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante,mapMedioIdMesesTotalOrdenesProductoAndCanal);
											mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal);
										}
									}
									else {
										mapMesesTotalOrdenesProductoAndCanal = mapMesesTotalOrdenesFaltantes;
																				
										mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
										mapMedioIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante, mapMesesTotalOrdenesProductoAndCanal);	
										mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante,mapMedioIdMesesTotalOrdenesProductoAndCanal);
										mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal);
									}
								}else{
									mapMesesTotalOrdenesProductoAndCanal = mapMesesTotalOrdenesFaltantes;
									mapMedioIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<String,BigDecimal>>();									
									
									mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
									mapMedioIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante, mapMesesTotalOrdenesProductoAndCanal);
									mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante,mapMedioIdMesesTotalOrdenesProductoAndCanal);
									mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal);
								}
							}else{
								mapMesesTotalOrdenesProductoAndCanal = mapMesesTotalOrdenesFaltantes;
								mapMedioIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<String,BigDecimal>>();
								mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
								
								mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
								mapMedioIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante, mapMesesTotalOrdenesProductoAndCanal);
								mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante,mapMedioIdMesesTotalOrdenesProductoAndCanal);
								mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal);
							}
						}
					}
				}
			}
			//System.out.println("mapClientesIdsOrdenesMedioXProducto size00: " + mapClientesIdProductosIdMedioIdOrdenesXProducto.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//END 15 AGOSTO
	
	
	public void obtenerTotalesMesesOrdenesProductoAndCanalByClienteProductoTMedios(){
		mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.clear();
		
		try {
			
			Iterator mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoIt =  mapClientesIdProductosIdMedioIdMesesTotalOrdenesProducto.keySet().iterator();
			BigDecimal valorToAdd = new BigDecimal(0D);
			//ADD 2 AGOSTO
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			while (mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoIt.hasNext()){
				Long idCliente = (Long) mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapProductosIdMedioIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				Map mapaProductoMedioIdsOrdenesXProducto = mapClientesIdProductosIdMedioIdMesesTotalOrdenesProducto.get(idCliente);
				Iterator mapaProductoMedioIdsOrdenesXProductoIt = mapaProductoMedioIdsOrdenesXProducto.keySet().iterator();
				
				while (mapaProductoMedioIdsOrdenesXProductoIt.hasNext()){
					Long idProducto = (Long) mapaProductoMedioIdsOrdenesXProductoIt.next();
					Map<Long,Map<String,BigDecimal>> mapMedioIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					Map mapaMedioIdsOrdenesXProducto = (Map) mapaProductoMedioIdsOrdenesXProducto.get(idProducto);
					Iterator mapaMedioIdsOrdenesXProductoIt = mapaMedioIdsOrdenesXProducto.keySet().iterator();
					
					while (mapaMedioIdsOrdenesXProductoIt.hasNext()){
						Long idMedio = (Long) mapaMedioIdsOrdenesXProductoIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenes = inicializarMapMesesValor();
						
						//ADD 2 AGOSTO
						Map mapMesesTotalOrdenesProducto = (Map) mapaMedioIdsOrdenesXProducto.get(idMedio);
						Iterator mapMesesTotalOrdenesProductoIt = mapMesesTotalOrdenesProducto.keySet().iterator();
						
						while (mapMesesTotalOrdenesProductoIt.hasNext()){
							mes = (String) mapMesesTotalOrdenesProductoIt.next();
							valor = (BigDecimal) mapMesesTotalOrdenesProducto.get(mes);
						
							Map mapProductosIdMedioIdMesesTotalOrdenesCanal =  mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanal.get(idCliente);
							
							if (mapProductosIdMedioIdMesesTotalOrdenesCanal != null && !mapProductosIdMedioIdMesesTotalOrdenesCanal.isEmpty()){
								Map mapMedioIdMesesTotalOrdenesCanal = (Map) mapProductosIdMedioIdMesesTotalOrdenesCanal.get(idProducto);
								
								if (mapMedioIdMesesTotalOrdenesCanal != null && !mapMedioIdMesesTotalOrdenesCanal.isEmpty()){
									Map mapMesesTotalOrdenesCanal = (Map) mapMedioIdMesesTotalOrdenesCanal.get(idMedio);
									
									if (mapMesesTotalOrdenesCanal != null && !mapMesesTotalOrdenesCanal.isEmpty()){
										valorToAdd = (BigDecimal) mapMesesTotalOrdenesCanal.get(mes);
										
										valor = valor.add(valorToAdd);
									}
								}
							}
							mapMesesTotalOrdenes.put(mes, valor);
						}
						//END 2 AGOSTO
						mapMedioIdMesesTotalOrdenes.put(idMedio, mapMesesTotalOrdenes);
					}
					mapProductosIdMedioIdMesesTotalOrdenes.put(idProducto,mapMedioIdMesesTotalOrdenes);
				}
				mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idCliente, mapProductosIdMedioIdMesesTotalOrdenes);
			}
			
			//para los clientes,productos,medios de los mapas Ordenes x Canal que no estan en los mapas de Ordenes de Producto 
			Iterator mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanalIt =  mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanal.keySet().iterator();
			Map<String,BigDecimal> mapMesesTotalOrdenesFaltantes;
			BigDecimal valorFaltante= new BigDecimal(0D);
			BigDecimal valorFaltanteToAdd = new BigDecimal(0D);
			String mesFaltante = "";
			
			Map<Long,Map<Long,Map<String,BigDecimal>>> mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal;
			Map<Long,Map<String,BigDecimal>> mapMedioIdMesesTotalOrdenesProductoAndCanal;
			Map<String,BigDecimal> mapMesesTotalOrdenesProductoAndCanal;
			
			//boolean 
			
			while (mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanalIt.hasNext()){
				Long idClienteFaltante = (Long) mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanalIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapProductosIdMedioIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				//mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal =  mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
				
				Map mapaProductoMedioIdsOrdenesXCanal = mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanal.get(idClienteFaltante);
				Iterator mapaProductoMedioIdsOrdenesXCanalIt = mapaProductoMedioIdsOrdenesXCanal.keySet().iterator();
				
				while (mapaProductoMedioIdsOrdenesXCanalIt.hasNext()){
					Long idProductoFaltante = (Long) mapaProductoMedioIdsOrdenesXCanalIt.next();
					Map<Long,Map<String,BigDecimal>> mapMedioIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					//mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idProducto);
					
					Map mapaMedioIdsOrdenesXCanal = (Map) mapaProductoMedioIdsOrdenesXCanal.get(idProductoFaltante);
					Iterator mapaMedioIdsOrdenesXCanalIt = mapaMedioIdsOrdenesXCanal.keySet().iterator();
					
					while (mapaMedioIdsOrdenesXCanalIt.hasNext()){
						Long idMedioFaltante = (Long) mapaMedioIdsOrdenesXCanalIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenesFaltantes = inicializarMapMesesValor();
						
						//mapMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
						
						//ADD 2 AGOSTO
						Map mapMesesTotalOrdenesCanal = (Map) mapaMedioIdsOrdenesXCanal.get(idMedioFaltante);
						Iterator mapMesesTotalOrdenesCanalIt = mapMesesTotalOrdenesCanal.keySet().iterator();
						
						while (mapMesesTotalOrdenesCanalIt.hasNext()){
							mesFaltante = (String) mapMesesTotalOrdenesCanalIt.next();
							valorFaltante = (BigDecimal) mapMesesTotalOrdenesCanal.get(mesFaltante);
							
							mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal =  mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idClienteFaltante);
							
							if (mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal != null && !mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.isEmpty()){
								mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idProductoFaltante);
								
								if (mapMedioIdMesesTotalOrdenesProductoAndCanal != null && !mapMedioIdMesesTotalOrdenesProductoAndCanal.isEmpty()){
									mapMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdMesesTotalOrdenesProductoAndCanal.get(idMedioFaltante);
									
									if (mapMesesTotalOrdenesProductoAndCanal != null && !mapMesesTotalOrdenesProductoAndCanal.isEmpty()){
										
										if (!isMedioIdInTotalesOrdenesProducto(idClienteFaltante,idProductoFaltante,idMedioFaltante)){
											valorFaltanteToAdd = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mesFaltante);
											
											valorFaltante = valorFaltante.add(valorFaltanteToAdd);
											
											mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
											mapMedioIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante, mapMesesTotalOrdenesProductoAndCanal);	
											mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante,mapMedioIdMesesTotalOrdenesProductoAndCanal);
											mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal);
										}
									}
									else {
										mapMesesTotalOrdenesProductoAndCanal = mapMesesTotalOrdenesFaltantes;
																				
										mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
										mapMedioIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante, mapMesesTotalOrdenesProductoAndCanal);	
										mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante,mapMedioIdMesesTotalOrdenesProductoAndCanal);
										mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal);
									}
								}else{
									mapMesesTotalOrdenesProductoAndCanal = mapMesesTotalOrdenesFaltantes;
									mapMedioIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<String,BigDecimal>>();									
									
									mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
									mapMedioIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante, mapMesesTotalOrdenesProductoAndCanal);
									mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante,mapMedioIdMesesTotalOrdenesProductoAndCanal);
									mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal);
								}
							}else{
								mapMesesTotalOrdenesProductoAndCanal = mapMesesTotalOrdenesFaltantes;
								mapMedioIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<String,BigDecimal>>();
								mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
								
								mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
								mapMedioIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante, mapMesesTotalOrdenesProductoAndCanal);
								mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante,mapMedioIdMesesTotalOrdenesProductoAndCanal);
								mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal);
							}
						}
					}
				}
			}
			//System.out.println("mapClientesIdsOrdenesMedioXProducto size00: " + mapClientesIdProductosIdMedioIdOrdenesXProducto.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//ADD 2 AGOSTO
	
		
	//ADD 2 AGOSTO
	//verifica si existe el Medio en las Ordenes Totales x Producto
	public boolean isMedioIdInTotalesOrdenesProducto(Long idCliente, Long idProducto, Long idMedio){
		boolean existe = false;
		
		Map mapProductosIdMedioIdMesesTotalOrdenesProducto =  mapClientesIdProductosIdMedioIdMesesTotalOrdenesProducto.get(idCliente);
		Map mapMedioIdMesesTotalOrdenesProducto;
		Map mapMesesTotalOrdenesProducto;
		
		if (mapProductosIdMedioIdMesesTotalOrdenesProducto != null && !mapProductosIdMedioIdMesesTotalOrdenesProducto.isEmpty()){
			mapMedioIdMesesTotalOrdenesProducto = (Map) mapProductosIdMedioIdMesesTotalOrdenesProducto.get(idProducto);
			
			if (mapMedioIdMesesTotalOrdenesProducto != null && !mapMedioIdMesesTotalOrdenesProducto.isEmpty()){
				mapMesesTotalOrdenesProducto = (Map) mapMedioIdMesesTotalOrdenesProducto.get(idMedio);
				
				if (mapMesesTotalOrdenesProducto != null && !mapMesesTotalOrdenesProducto.isEmpty()){
					existe = true;
				}
			}
		}
		return existe;	
	}
	//END 2 AGOSTO
	
	//MODIFIED 8 AGOSTO
	//ADD 3 AGOSTO
	public boolean isProductoIdInTotalesOrdenesProducto(Long idCliente, Long idMedio, Long idProducto){
				
		boolean existe = false;
		
		Map mapMedioIdProductosIdMesesTotalOrdenesProducto =  mapClientesIdMedioIdProductosIdMesesTotalOrdenesProducto.get(idCliente);
		Map mapProductoIdMesesTotalOrdenesProducto;
		Map mapMesesTotalOrdenesProducto;
		
		if (mapMedioIdProductosIdMesesTotalOrdenesProducto != null && !mapMedioIdProductosIdMesesTotalOrdenesProducto.isEmpty()){
			mapProductoIdMesesTotalOrdenesProducto = (Map) mapMedioIdProductosIdMesesTotalOrdenesProducto.get(idMedio);
			
			if (mapProductoIdMesesTotalOrdenesProducto != null && !mapProductoIdMesesTotalOrdenesProducto.isEmpty()){
				mapMesesTotalOrdenesProducto = (Map) mapProductoIdMesesTotalOrdenesProducto.get(idProducto);
				
				if (mapMesesTotalOrdenesProducto != null && !mapMesesTotalOrdenesProducto.isEmpty()){
					existe = true;
				}
			}
		}
		return existe;	
	}
	//END 3 AGOSTO
	
	//ADD 3 AGOSTO
	public boolean isProductoIdInTotalesOrdenesProductoFirstOrderMedio(Long idMedio, Long idCliente, Long idProducto){
		boolean existe = false;
		
		Map mapClienteIdProductosIdMesesTotalOrdenesProducto =  mapMedioIdClientesIdProductosIdMesesTotalOrdenesProducto.get(idMedio);
		Map mapProductoIdMesesTotalOrdenesProducto;
		Map mapMesesTotalOrdenesProducto;
		
		if (mapClienteIdProductosIdMesesTotalOrdenesProducto != null && !mapClienteIdProductosIdMesesTotalOrdenesProducto.isEmpty()){
			mapProductoIdMesesTotalOrdenesProducto = (Map) mapClienteIdProductosIdMesesTotalOrdenesProducto.get(idCliente);
			
			if (mapProductoIdMesesTotalOrdenesProducto != null && !mapProductoIdMesesTotalOrdenesProducto.isEmpty()){
				mapMesesTotalOrdenesProducto = (Map) mapProductoIdMesesTotalOrdenesProducto.get(idProducto);
				
				if (mapMesesTotalOrdenesProducto != null && !mapMesesTotalOrdenesProducto.isEmpty()){
					existe = true;
				}
			}
		}
		return existe;	
	}
	
	//ADD 4 AGOSTO
	public boolean isClienteIdInTotalesOrdenesProducto(Long idCliente){
		boolean existe = false;
		
		Map mapProductosIdMedioIdMesesTotalOrdenesProducto =  mapClientesIdProductosIdMedioIdMesesTotalOrdenesProducto.get(idCliente);
				
		if (mapProductosIdMedioIdMesesTotalOrdenesProducto != null && !mapProductosIdMedioIdMesesTotalOrdenesProducto.isEmpty())
			existe = true;			
		
		return existe;	
	}
	//END 4 AGOSTO
	
	
	//ADD 8 AGOSTO
	public void obtenerTotalesMesesOrdenesProductoAndCanalByClienteTMediosProducto(){
		mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.clear();
		
		try {
			
			Iterator mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoIt =  mapClientesIdMedioIdProductosIdMesesTotalOrdenesProducto.keySet().iterator();
			BigDecimal valorToAdd = new BigDecimal(0D);
			//ADD 2 AGOSTO
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			while (mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoIt.hasNext()){
				Long idCliente = (Long) mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapMedioIdProductosIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				Map mapaMedioProductoIdsOrdenesXProducto = mapClientesIdMedioIdProductosIdMesesTotalOrdenesProducto.get(idCliente);
				Iterator mapaMedioProductoIdsOrdenesXProductoIt = mapaMedioProductoIdsOrdenesXProducto.keySet().iterator();
				
				while (mapaMedioProductoIdsOrdenesXProductoIt.hasNext()){
					Long idMedio = (Long) mapaMedioProductoIdsOrdenesXProductoIt.next();
					Map<Long,Map<String,BigDecimal>> mapProductoIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					Map mapaProductoIdsOrdenesXProducto = (Map) mapaMedioProductoIdsOrdenesXProducto.get(idMedio);
					Iterator mapaMedioIdsOrdenesXProductoIt = mapaProductoIdsOrdenesXProducto.keySet().iterator();
					
					while (mapaMedioIdsOrdenesXProductoIt.hasNext()){
						Long idProducto = (Long) mapaMedioIdsOrdenesXProductoIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenes = inicializarMapMesesValor();
						
						//ADD 2 AGOSTO
						Map mapMesesTotalOrdenesProducto = (Map) mapaProductoIdsOrdenesXProducto.get(idProducto);
						Iterator mapMesesTotalOrdenesProductoIt = mapMesesTotalOrdenesProducto.keySet().iterator();
						
						while (mapMesesTotalOrdenesProductoIt.hasNext()){
							mes = (String) mapMesesTotalOrdenesProductoIt.next();
							valor = (BigDecimal) mapMesesTotalOrdenesProducto.get(mes);
						
							Map mapMedioIdProductosIdMesesTotalOrdenesCanal =  mapClientesIdMedioIdProductosIdMesesTotalOrdenesCanal.get(idCliente);
							
							if (mapMedioIdProductosIdMesesTotalOrdenesCanal != null && !mapMedioIdProductosIdMesesTotalOrdenesCanal.isEmpty()){
								Map mapProductoIdMesesTotalOrdenesCanal = (Map) mapMedioIdProductosIdMesesTotalOrdenesCanal.get(idMedio);
								
								if (mapProductoIdMesesTotalOrdenesCanal != null && !mapProductoIdMesesTotalOrdenesCanal.isEmpty()){
									Map mapMesesTotalOrdenesCanal = (Map) mapProductoIdMesesTotalOrdenesCanal.get(idProducto);
									
									if (mapMesesTotalOrdenesCanal != null && !mapMesesTotalOrdenesCanal.isEmpty()){
										valorToAdd = (BigDecimal) mapMesesTotalOrdenesCanal.get(mes);
										
										valor = valor.add(valorToAdd);
									}
								}
							}
							mapMesesTotalOrdenes.put(mes, valor);
						}
						//END 2 AGOSTO
						mapProductoIdMesesTotalOrdenes.put(idProducto, mapMesesTotalOrdenes);
					}
					mapMedioIdProductosIdMesesTotalOrdenes.put(idMedio,mapProductoIdMesesTotalOrdenes);
				}
				mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idCliente, mapMedioIdProductosIdMesesTotalOrdenes);
			}
			
			//para los clientes,productos,medios de los mapas Ordenes x Canal que no estan en los mapas de Ordenes de Producto 
			Iterator mapClientesIdMedioIdProductosIdMesesTotalOrdenesCanalIt =  mapClientesIdMedioIdProductosIdMesesTotalOrdenesCanal.keySet().iterator();
			Map<String,BigDecimal> mapMesesTotalOrdenesFaltantes;
			BigDecimal valorFaltante= new BigDecimal(0D);
			BigDecimal valorFaltanteToAdd = new BigDecimal(0D);
			String mesFaltante = "";
			
			Map<Long,Map<Long,Map<String,BigDecimal>>> mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal;
			Map<Long,Map<String,BigDecimal>> mapProductoIdMesesTotalOrdenesProductoAndCanal;
			Map<String,BigDecimal> mapMesesTotalOrdenesProductoAndCanal;
			
			//boolean 
			
			while (mapClientesIdMedioIdProductosIdMesesTotalOrdenesCanalIt.hasNext()){
				Long idClienteFaltante = (Long) mapClientesIdMedioIdProductosIdMesesTotalOrdenesCanalIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapMedioIdProductosIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				//mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal =  mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
				
				Map mapaMedioProductoIdsOrdenesXCanal = mapClientesIdMedioIdProductosIdMesesTotalOrdenesCanal.get(idClienteFaltante);
				Iterator mapaMedioProductoIdsOrdenesXCanalIt = mapaMedioProductoIdsOrdenesXCanal.keySet().iterator();
				
				while (mapaMedioProductoIdsOrdenesXCanalIt.hasNext()){
					Long idMedioFaltante = (Long) mapaMedioProductoIdsOrdenesXCanalIt.next();
					Map<Long,Map<String,BigDecimal>> mapProductoIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					//mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idProducto);
					
					Map mapaProductoIdsOrdenesXCanal = (Map) mapaMedioProductoIdsOrdenesXCanal.get(idMedioFaltante);
					Iterator mapaProductoIdsOrdenesXCanalIt = mapaProductoIdsOrdenesXCanal.keySet().iterator();
					
					while (mapaProductoIdsOrdenesXCanalIt.hasNext()){
						Long idProductoFaltante = (Long) mapaProductoIdsOrdenesXCanalIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenesFaltantes = inicializarMapMesesValor();
						
						//mapMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
						
						//ADD 2 AGOSTO
						Map mapMesesTotalOrdenesCanal = (Map) mapaProductoIdsOrdenesXCanal.get(idProductoFaltante);
						Iterator mapMesesTotalOrdenesCanalIt = mapMesesTotalOrdenesCanal.keySet().iterator();
						
						while (mapMesesTotalOrdenesCanalIt.hasNext()){
							mesFaltante = (String) mapMesesTotalOrdenesCanalIt.next();
							valorFaltante = (BigDecimal) mapMesesTotalOrdenesCanal.get(mesFaltante);
							
							mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal =  mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idClienteFaltante);
							
							if (mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal != null && !mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.isEmpty()){
								mapProductoIdMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idMedioFaltante);
								
								if (mapProductoIdMesesTotalOrdenesProductoAndCanal != null && !mapProductoIdMesesTotalOrdenesProductoAndCanal.isEmpty()){
									mapMesesTotalOrdenesProductoAndCanal = (Map) mapProductoIdMesesTotalOrdenesProductoAndCanal.get(idProductoFaltante);
									
									if (mapMesesTotalOrdenesProductoAndCanal != null && !mapMesesTotalOrdenesProductoAndCanal.isEmpty()){
										
										if (!isProductoIdInTotalesOrdenesProducto(idClienteFaltante,idMedioFaltante,idProductoFaltante)){
											valorFaltanteToAdd = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mesFaltante);
											
											valorFaltante = valorFaltante.add(valorFaltanteToAdd);
											
											mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
											mapProductoIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante, mapMesesTotalOrdenesProductoAndCanal);	
											mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante,mapProductoIdMesesTotalOrdenesProductoAndCanal);
											mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal);
										}
									}
									else {
										mapMesesTotalOrdenesProductoAndCanal = mapMesesTotalOrdenesFaltantes;
																				
										mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
										mapProductoIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante, mapMesesTotalOrdenesProductoAndCanal);	
										mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante,mapProductoIdMesesTotalOrdenesProductoAndCanal);
										mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal);
									}
								}else{
									mapMesesTotalOrdenesProductoAndCanal = mapMesesTotalOrdenesFaltantes;
									mapProductoIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<String,BigDecimal>>();									
									
									mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
									mapProductoIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante, mapMesesTotalOrdenesProductoAndCanal);
									mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante,mapProductoIdMesesTotalOrdenesProductoAndCanal);
									mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal);
								}
							}else{
								mapMesesTotalOrdenesProductoAndCanal = mapMesesTotalOrdenesFaltantes;
								mapProductoIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<String,BigDecimal>>();
								mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
								
								mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
								mapProductoIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante, mapMesesTotalOrdenesProductoAndCanal);
								mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante,mapProductoIdMesesTotalOrdenesProductoAndCanal);
								mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal);
							}
						}
					}
				}
			}
			//System.out.println("mapClientesIdsOrdenesMedioXProducto size00: " + mapClientesIdProductosIdMedioIdOrdenesXProducto.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//END 8 AGOSTO
	
	//ADD 8 AGOSTO
	public void obtenerTotalesMesesOrdenesProductoAndCanalByTMediosClienteProducto(){
		mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.clear();
		
		try {																	   
			
			Iterator mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoIt =  mapMedioIdClientesIdProductosIdMesesTotalOrdenesProducto.keySet().iterator();
			BigDecimal valorToAdd = new BigDecimal(0D);
			//ADD 2 AGOSTO
			Map<String,BigDecimal> mapMesesTotalOrdenes;
			BigDecimal valor = new BigDecimal(0D);
			String mes = "";
			
			while (mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoIt.hasNext()){
				Long idMedio = (Long) mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapClienteIdProductosIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				Map mapaClienteProductoIdsOrdenesXProducto = mapMedioIdClientesIdProductosIdMesesTotalOrdenesProducto.get(idMedio);
				Iterator mapaClienteProductoIdsOrdenesXProductoIt = mapaClienteProductoIdsOrdenesXProducto.keySet().iterator();
				
				while (mapaClienteProductoIdsOrdenesXProductoIt.hasNext()){
					Long idCliente = (Long) mapaClienteProductoIdsOrdenesXProductoIt.next();
					Map<Long,Map<String,BigDecimal>> mapProductoIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					Map mapaProductoIdsOrdenesXProducto = (Map) mapaClienteProductoIdsOrdenesXProducto.get(idCliente);
					Iterator mapaProductoIdsOrdenesXProductoIt = mapaProductoIdsOrdenesXProducto.keySet().iterator();
					
					while (mapaProductoIdsOrdenesXProductoIt.hasNext()){
						Long idProducto = (Long) mapaProductoIdsOrdenesXProductoIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenes = inicializarMapMesesValor();
						
						//ADD 2 AGOSTO
						Map mapMesesTotalOrdenesProducto = (Map) mapaProductoIdsOrdenesXProducto.get(idProducto);
						Iterator mapMesesTotalOrdenesProductoIt = mapMesesTotalOrdenesProducto.keySet().iterator();
						
						while (mapMesesTotalOrdenesProductoIt.hasNext()){
							mes = (String) mapMesesTotalOrdenesProductoIt.next();
							valor = (BigDecimal) mapMesesTotalOrdenesProducto.get(mes);
						
							Map mapClienteIdProductosIdMesesTotalOrdenesCanal =  mapMedioIdClientesIdProductosIdMesesTotalOrdenesCanal.get(idMedio);
							
							if (mapClienteIdProductosIdMesesTotalOrdenesCanal != null && !mapClienteIdProductosIdMesesTotalOrdenesCanal.isEmpty()){
								Map mapProductoIdMesesTotalOrdenesCanal = (Map) mapClienteIdProductosIdMesesTotalOrdenesCanal.get(idCliente);
								
								if (mapProductoIdMesesTotalOrdenesCanal != null && !mapProductoIdMesesTotalOrdenesCanal.isEmpty()){
									Map mapMesesTotalOrdenesCanal = (Map) mapProductoIdMesesTotalOrdenesCanal.get(idProducto);
									
									if (mapMesesTotalOrdenesCanal != null && !mapMesesTotalOrdenesCanal.isEmpty()){
										valorToAdd = (BigDecimal) mapMesesTotalOrdenesCanal.get(mes);
										
										valor = valor.add(valorToAdd);
									}
								}
							}
							mapMesesTotalOrdenes.put(mes, valor);
						}
						//END 2 AGOSTO
						mapProductoIdMesesTotalOrdenes.put(idProducto, mapMesesTotalOrdenes);
					}
					mapClienteIdProductosIdMesesTotalOrdenes.put(idCliente,mapProductoIdMesesTotalOrdenes);
				}
				mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idMedio, mapClienteIdProductosIdMesesTotalOrdenes);
			}
			
			//para los clientes,productos,medios de los mapas Ordenes x Canal que no estan en los mapas de Ordenes de Producto 
			Iterator mapMedioIdClientesIdProductosIdMesesTotalOrdenesCanalIt =  mapMedioIdClientesIdProductosIdMesesTotalOrdenesCanal.keySet().iterator();
			Map<String,BigDecimal> mapMesesTotalOrdenesFaltantes;
			BigDecimal valorFaltante= new BigDecimal(0D);
			BigDecimal valorFaltanteToAdd = new BigDecimal(0D);
			String mesFaltante = "";
			
			Map<Long,Map<Long,Map<String,BigDecimal>>> mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal;
			Map<Long,Map<String,BigDecimal>> mapProductoIdMesesTotalOrdenesProductoAndCanal;
			Map<String,BigDecimal> mapMesesTotalOrdenesProductoAndCanal;
			
			//boolean 
			
			while (mapMedioIdClientesIdProductosIdMesesTotalOrdenesCanalIt.hasNext()){
				Long idMedioFaltante = (Long) mapMedioIdClientesIdProductosIdMesesTotalOrdenesCanalIt.next();
				Map<Long,Map<Long,Map<String,BigDecimal>>> mapClienteIdProductosIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
				
				//mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal =  mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
				
				Map mapaClienteProductoIdsOrdenesXCanal = mapMedioIdClientesIdProductosIdMesesTotalOrdenesCanal.get(idMedioFaltante);
				Iterator mapaClienteProductoIdsOrdenesXCanalIt = mapaClienteProductoIdsOrdenesXCanal.keySet().iterator();
				
				while (mapaClienteProductoIdsOrdenesXCanalIt.hasNext()){
					Long idClienteFaltante = (Long) mapaClienteProductoIdsOrdenesXCanalIt.next();
					Map<Long,Map<String,BigDecimal>> mapProductoIdMesesTotalOrdenes = new LinkedHashMap<Long, Map<String,BigDecimal>>();
					
					//mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idProducto);
					
					Map mapaProductoIdsOrdenesXCanal = (Map) mapaClienteProductoIdsOrdenesXCanal.get(idClienteFaltante);
					Iterator mapaProductoIdsOrdenesXCanalIt = mapaProductoIdsOrdenesXCanal.keySet().iterator();
					
					while (mapaProductoIdsOrdenesXCanalIt.hasNext()){
						Long idProductoFaltante = (Long) mapaProductoIdsOrdenesXCanalIt.next();
						//se inicializa el mapa de meses con valor 0.0
						mapMesesTotalOrdenesFaltantes = inicializarMapMesesValor();
						
						//mapMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
						
						//ADD 2 AGOSTO
						Map mapMesesTotalOrdenesCanal = (Map) mapaProductoIdsOrdenesXCanal.get(idProductoFaltante);
						Iterator mapMesesTotalOrdenesCanalIt = mapMesesTotalOrdenesCanal.keySet().iterator();
						
						while (mapMesesTotalOrdenesCanalIt.hasNext()){
							mesFaltante = (String) mapMesesTotalOrdenesCanalIt.next();
							valorFaltante = (BigDecimal) mapMesesTotalOrdenesCanal.get(mesFaltante);
							
							mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal =  mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idMedioFaltante);
							
							if (mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal != null && !mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.isEmpty()){
								mapProductoIdMesesTotalOrdenesProductoAndCanal = (Map) mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idClienteFaltante);
								
								if (mapProductoIdMesesTotalOrdenesProductoAndCanal != null && !mapProductoIdMesesTotalOrdenesProductoAndCanal.isEmpty()){
									mapMesesTotalOrdenesProductoAndCanal = (Map) mapProductoIdMesesTotalOrdenesProductoAndCanal.get(idProductoFaltante);
									
									if (mapMesesTotalOrdenesProductoAndCanal != null && !mapMesesTotalOrdenesProductoAndCanal.isEmpty()){
										
										if (!isProductoIdInTotalesOrdenesProductoFirstOrderMedio(idMedioFaltante,idClienteFaltante,idProductoFaltante)){
											valorFaltanteToAdd = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mesFaltante);
											
											valorFaltante = valorFaltante.add(valorFaltanteToAdd);
											
											mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
											mapProductoIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante, mapMesesTotalOrdenesProductoAndCanal);	
											mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapProductoIdMesesTotalOrdenesProductoAndCanal);
											mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante,mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal);
										}
									}
									else {
										mapMesesTotalOrdenesProductoAndCanal = mapMesesTotalOrdenesFaltantes;
																				
										mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
										mapProductoIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante, mapMesesTotalOrdenesProductoAndCanal);	
										mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapProductoIdMesesTotalOrdenesProductoAndCanal);
										mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante,mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal);
									}
								}else{
									mapMesesTotalOrdenesProductoAndCanal = mapMesesTotalOrdenesFaltantes;
									mapProductoIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<String,BigDecimal>>();									
									
									mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
									mapProductoIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante, mapMesesTotalOrdenesProductoAndCanal);
									mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapProductoIdMesesTotalOrdenesProductoAndCanal);
									mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante,mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal);
								}
							}else{
								mapMesesTotalOrdenesProductoAndCanal = mapMesesTotalOrdenesFaltantes;
								mapProductoIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<String,BigDecimal>>();
								mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal = new LinkedHashMap<Long, Map<Long,Map<String,BigDecimal>>>();
								
								mapMesesTotalOrdenesProductoAndCanal.put(mesFaltante, valorFaltante);	
								mapProductoIdMesesTotalOrdenesProductoAndCanal.put(idProductoFaltante, mapMesesTotalOrdenesProductoAndCanal);
								mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idClienteFaltante,mapProductoIdMesesTotalOrdenesProductoAndCanal);
								mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.put(idMedioFaltante,mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal);
							}
						}
					}
				}
			}
			//System.out.println("mapClientesIdsOrdenesMedioXProducto size00: " + mapClientesIdProductosIdMedioIdOrdenesXProducto.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//END 8 AGOSTO
	
	//MODIFIED 8 AGOSTO
	//ADD 2 AGOSTO
	private void cargarTabla() {
		try {
			String mes = "";
			BigDecimal valorMes = new BigDecimal(0D);
						
			tblInversionesPlanesMedio = (DefaultTableModel)getTblInversionesPlanMedios().getModel();
			
			//ADD 8 AGOSTO
			if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_PRODUCTO_TMEDIOS_MEDIOS) == 0 && mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.size() > 0 ){
			
				Iterator mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
				
				while (mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idCliente = (Long) mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
					ClienteIf clienteTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idCliente);
					
					Map mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal = mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
					Iterator mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
					
					while (mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProductoCliente = (Long) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
						ProductoClienteIf productoClienteTempIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(idProductoCliente);
						
						Map mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idProductoCliente);
						Iterator mapMedioIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
						
						while (mapMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
							Long idMedio = (Long) mapMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
							ClienteIf medioTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idMedio);
							Vector<String> fila = new Vector<String>();
							fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre() + " / " + medioTempIf.getNombreLegal());
							
							Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
							Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
							BigDecimal valor = new BigDecimal(0D);
							
							//ADD 4 AGOSTO
							InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
							inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
							inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
							inversionClienteMesData.setRuc(clienteTempIf.getIdentificacion());
							inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
							inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
							inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
							
							while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
								mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
								valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
														
								//ADD 4 AGOSTO
								agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
							
								valor = valor.add(valorMes);
								
							}
							
							fila.add(formatoDecimal.format(valor).toString());
							//COMENTED 5 AGOSTO
							//ADD 4 AGOSTO
							//inversionClienteMesData.setTotal(formatoDecimal.format(valor).toString());
							//MODIFIED 5 AGOSTO
							//inversionClienteMesData.setTotal(valor.toString());
							inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
							
							tblInversionesPlanesMedio.addRow(fila);
							
							//ADD 4 AGOSTO
							inversionColeccion.add(inversionClienteMesData);
						}
					}
				}
			}else if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO) == 0 && mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.size() > 0 ){//ADD 8 AGOSTO
				
				Iterator mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
				
				while (mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idCliente = (Long) mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
					ClienteIf clienteTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idCliente);
					
					Map mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal = mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
					Iterator mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
					
					while (mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idMedio = (Long) mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
						ClienteIf medioTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idMedio);
												
						Map mapProductoIdMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
						Iterator mapProductoIdMesesTotalOrdenesProductoAndCanalIt = mapProductoIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
						
						while (mapProductoIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
							Long idProductoCliente = (Long) mapProductoIdMesesTotalOrdenesProductoAndCanalIt.next();
							ProductoClienteIf productoClienteTempIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(idProductoCliente);
							
							Vector<String> fila = new Vector<String>();
							fila.add(clienteTempIf.getNombreLegal() + " / " + medioTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre() );
							
							Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapProductoIdMesesTotalOrdenesProductoAndCanal.get(idProductoCliente);
							Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
							BigDecimal valor = new BigDecimal(0D);
							
							//ADD 4 AGOSTO
							InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
							inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
							inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
							inversionClienteMesData.setRuc(clienteTempIf.getIdentificacion());
							inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
							inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
							inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
							inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
							
							while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
								mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
								valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
														
								//ADD 4 AGOSTO
								agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
							
								valor = valor.add(valorMes);
								
							}
							
							fila.add(formatoDecimal.format(valor).toString());
							//COMENTED 5 AGOSTO
							//ADD 4 AGOSTO
							//inversionClienteMesData.setTotal(formatoDecimal.format(valor).toString());
							//MODIFIED 5 AGOSTO
							//inversionClienteMesData.setTotal(valor.toString());
							inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
							
							tblInversionesPlanesMedio.addRow(fila);
							
							//ADD 4 AGOSTO
							inversionColeccion.add(inversionClienteMesData);
						}
					}
				}//ADD 8 AGOSTO
			}else if (agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO) == 0 && mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.size() > 0 ){
				
				Iterator mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
				
				while (mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idMedio = (Long) mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
					ClienteIf medioTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idMedio);
					
					Map mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal = mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
					Iterator mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
					
					while (mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idCliente = (Long) mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
						ClienteIf clienteTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idCliente);
												
						Map mapProductoIdMesesTotalOrdenesProductoAndCanal = (Map) mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
						Iterator mapProductoIdMesesTotalOrdenesProductoAndCanalIt = mapProductoIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
						
						while (mapProductoIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
							Long idProductoCliente = (Long) mapProductoIdMesesTotalOrdenesProductoAndCanalIt.next();
							ProductoClienteIf productoClienteTempIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(idProductoCliente);
							
							Vector<String> fila = new Vector<String>();
							fila.add(medioTempIf.getNombreLegal() + " / " + clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre() );
							
							Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapProductoIdMesesTotalOrdenesProductoAndCanal.get(idProductoCliente);
							Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
							BigDecimal valor = new BigDecimal(0D);
							
							//ADD 4 AGOSTO
							InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
							inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
							inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
							inversionClienteMesData.setRuc(clienteTempIf.getIdentificacion());
							inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
							inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
							inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
							inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
							
							while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
								mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
								valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
														
								//ADD 4 AGOSTO
								agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
							
								valor = valor.add(valorMes);
								
							}
							
							fila.add(formatoDecimal.format(valor).toString());
							//COMENTED 5 AGOSTO
							//ADD 4 AGOSTO
							//inversionClienteMesData.setTotal(formatoDecimal.format(valor).toString());
							//MODIFIED 5 AGOSTO
							//inversionClienteMesData.setTotal(valor.toString());
							inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
							
							tblInversionesPlanesMedio.addRow(fila);
							
							//ADD 4 AGOSTO
							inversionColeccion.add(inversionClienteMesData);
						}
					}
				}
			}
			
			System.out.println("fin que pasa");
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}	
	//END 2 AGOSTO
	
	//ADD 10 AGOSTO
	private void cargarTablaXTMediosClientesProducto2() {
		try {
			String mes = "";
			BigDecimal valorMes = new BigDecimal(0D);
						
			tblInversionesPlanesMedio = (DefaultTableModel)getTblInversionesPlanMedios().getModel();
						
			//MODIFIED 10 AGOSTO PARA MOSTRAR T-MEDIOS/MEDIOS/CLIENTES/PRODUCTOS
			if ( mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.size() > 0  &&
				 getCbMostrarCliente().isSelected() && getCbMostrarProducto().isSelected()){
			
				Iterator mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
				
				while (mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idMedio = (Long) mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
					ClienteIf medioTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idMedio);
					
					Map mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal = mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
					Iterator mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt = mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
					
					while (mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idCliente = (Long) mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanalIt.next();
						ClienteIf clienteTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idCliente);
												
						Map mapProductoIdMesesTotalOrdenesProductoAndCanal = (Map) mapClienteIdProductosIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
						Iterator mapProductoIdMesesTotalOrdenesProductoAndCanalIt = mapProductoIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
						
						while (mapProductoIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
							Long idProductoCliente = (Long) mapProductoIdMesesTotalOrdenesProductoAndCanalIt.next();
							ProductoClienteIf productoClienteTempIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(idProductoCliente);
							
							Vector<String> fila = new Vector<String>();
							fila.add(medioTempIf.getNombreLegal() + " / " + clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre() );
							
							Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapProductoIdMesesTotalOrdenesProductoAndCanal.get(idProductoCliente);
							Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
							BigDecimal valor = new BigDecimal(0D);
							
							//ADD 4 AGOSTO
							InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
							inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
							inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
							inversionClienteMesData.setRuc(clienteTempIf.getIdentificacion());
							inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
							inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
							inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
							inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
							//ADD 10 AGOSTO
							inversionClienteMesData.setRucMedio(medioTempIf.getIdentificacion());
							
							//FALTA EL RUC DE MEDIOS NO ES EL RUC DEL CLIENTE 10 AGOSTO
							
							while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
								mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
								valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
														
								//ADD 4 AGOSTO
								agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
							
								valor = valor.add(valorMes);
								
							}
							
							fila.add(formatoDecimal.format(valor).toString());
							//COMENTED 5 AGOSTO
							//ADD 4 AGOSTO
							//inversionClienteMesData.setTotal(formatoDecimal.format(valor).toString());
							//MODIFIED 5 AGOSTO
							//inversionClienteMesData.setTotal(valor.toString());
							inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
							
							tblInversionesPlanesMedio.addRow(fila);
							
							//ADD 4 AGOSTO
							inversionColeccion.add(inversionClienteMesData);
						}
					}
				}
			}//ADD 9 AGOSTO PARA MOSTRAR T-MEDIOS/MEDIOS/CLIENTES
			else if (mapMediosIdClientesIdMesesTotalOrdenesProductoAndCanal.size() > 0 &&
					 getCbMostrarCliente().isSelected() && !getCbMostrarProducto().isSelected()){
				
				Iterator mapMediosIdClientesIdMesesTotalOrdenesProductoAndCanalIt = mapMediosIdClientesIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
				
				while (mapMediosIdClientesIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idMedio = (Long) mapMediosIdClientesIdMesesTotalOrdenesProductoAndCanalIt.next();
					ClienteIf medioTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idMedio);
					
					/*Map mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal = mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
					Iterator mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
					
					while (mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProductoCliente = (Long) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
						ProductoClienteIf productoClienteTempIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(idProductoCliente);
						*/
						Map mapClienteIdMesesTotalOrdenesProductoAndCanal = (Map) mapMediosIdClientesIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
						Iterator mapClienteIdMesesTotalOrdenesProductoAndCanalIt = mapClienteIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
						
						while (mapClienteIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
							Long idCliente = (Long) mapClienteIdMesesTotalOrdenesProductoAndCanalIt.next();
							ClienteIf clienteTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idCliente);
							Vector<String> fila = new Vector<String>();
							fila.add(medioTempIf.getNombreLegal() + " / " + " / " + clienteTempIf.getNombreLegal());
							
							Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapClienteIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
							Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
							BigDecimal valor = new BigDecimal(0D);
							
							//ADD 4 AGOSTO
							InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
							inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
							inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
							inversionClienteMesData.setRuc(clienteTempIf.getIdentificacion());
							//inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
							//inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
							//MODIFIED 10 AGOSTO
							//inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
							inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
							inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
							//ADD 10 AGOSTO
							inversionClienteMesData.setRucMedio(medioTempIf.getIdentificacion());
							
							while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
								mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
								valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
														
								//ADD 4 AGOSTO
								agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
							
								valor = valor.add(valorMes);
								
							}
							
							fila.add(formatoDecimal.format(valor).toString());
							//MODIFIED 5 AGOSTO
							//inversionClienteMesData.setTotal(valor.toString());
							inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
							
							tblInversionesPlanesMedio.addRow(fila);
							
							//ADD 4 AGOSTO
							inversionColeccion.add(inversionClienteMesData);
						}
					//}COMENTED 9 AGOSTO
				}
			} //ADD 9 AGOSTO PARA MOSTRAR T-MEDIOS/MEDIOS/PRODUCTOS
			else if (mapMediosIdProductoIdMesesTotalOrdenesProductoAndCanal.size() > 0 &&
					 !getCbMostrarCliente().isSelected() && getCbMostrarProducto().isSelected()){
				
				Iterator mapMediosIdProductoIdMesesTotalOrdenesProductoAndCanalIt = mapMediosIdProductoIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
				
				while (mapMediosIdProductoIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idMedio = (Long) mapMediosIdProductoIdMesesTotalOrdenesProductoAndCanalIt.next();
					ClienteIf medioTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idMedio);
					
					Map mapProductoIdMesesTotalOrdenesProductoAndCanal = mapMediosIdProductoIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
					Iterator mapProductoIdMesesTotalOrdenesProductoAndCanalIt = mapProductoIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
					
					while (mapProductoIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProductoCliente = (Long) mapProductoIdMesesTotalOrdenesProductoAndCanalIt.next();
						ProductoClienteIf productoClienteTempIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(idProductoCliente);
						
					/*	Map mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
						Iterator mapMedioIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
						
						while (mapMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
							Long idMedio = (Long) mapMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
							ClienteIf medioTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idMedio);*/
							Vector<String> fila = new Vector<String>();
							fila.add(medioTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre());
							
							Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapProductoIdMesesTotalOrdenesProductoAndCanal.get(idProductoCliente);
							Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
							BigDecimal valor = new BigDecimal(0D);
							
							//ADD 4 AGOSTO
							InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
							//inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
							//inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
							//inversionClienteMesData.setRuc(clienteTempIf.getIdentificacion());
							inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
							inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
							//inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
							//ADD 10 AGOSTO
							inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
							inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
							//ADD 10 AGOSTO
							inversionClienteMesData.setRucMedio(medioTempIf.getIdentificacion());
							
							while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
								mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
								valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
														
								//ADD 4 AGOSTO
								agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
							
								valor = valor.add(valorMes);
								
							}
							
							fila.add(formatoDecimal.format(valor).toString());
							//MODIFIED 5 AGOSTO
							//inversionClienteMesData.setTotal(valor.toString());
							inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
							
							tblInversionesPlanesMedio.addRow(fila);
							
							//ADD 4 AGOSTO
							inversionColeccion.add(inversionClienteMesData);
						//}COMENTED 9 AGOSTO
					}
				}
			} //ADD 9 AGOSTO PARA MOSTRAR T-MEDIOS/MEDIOS/
			else if (mapMediosIdMesesTotalOrdenesProductoAndCanal.size() > 0 &&
					!getCbMostrarCliente().isSelected() && !getCbMostrarProducto().isSelected()){
				
				Iterator mapMediosIdMesesTotalOrdenesProductoAndCanalIt = mapMediosIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
				
				while (mapMediosIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idMedio = (Long) mapMediosIdMesesTotalOrdenesProductoAndCanalIt.next();
					ClienteIf medioTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idMedio);
					
					/*Map mapMesesTotalOrdenesProductoAndCanal = mapClientesIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
					Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator();*/ 
					
					/*while (mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProductoCliente = (Long) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
						ProductoClienteIf productoClienteTempIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(idProductoCliente);
					*/	
					/*	Map mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
						Iterator mapMedioIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
						
						while (mapMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
							Long idMedio = (Long) mapMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
							ClienteIf medioTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idMedio);*/
							Vector<String> fila = new Vector<String>();
							fila.add(medioTempIf.getNombreLegal());// + " / " + productoClienteTempIf.getNombre());
							
							Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapMediosIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
							Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
							BigDecimal valor = new BigDecimal(0D);
							
							//ADD 4 AGOSTO
							InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
							//COMENTED 10 AGOSTO
							//inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
							//inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
							//inversionClienteMesData.setRuc(clienteTempIf.getIdentificacion());
							
							//inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
							//inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
							//inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
							//ADD 10 AGOSTO
							inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
							inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
							inversionClienteMesData.setRucMedio(medioTempIf.getIdentificacion());
							
							while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
								mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
								valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
														
								//ADD 4 AGOSTO
								agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
							
								valor = valor.add(valorMes);
								
							}
							
							fila.add(formatoDecimal.format(valor).toString());
							//MODIFIED 5 AGOSTO
							//inversionClienteMesData.setTotal(valor.toString());
							inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
							
							tblInversionesPlanesMedio.addRow(fila);
							
							//ADD 4 AGOSTO
							inversionColeccion.add(inversionClienteMesData);
						//}COMENTED 9 AGOSTO
					//}COMENTED 9 AGOSTO
				}
			}			
			//ADD 9 AGOSTO
			System.out.println("fin que pasa");
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}	
		
	private void cargarTablaXClientesProductoTMedios() {
		try {
			String mes = "";
			BigDecimal valorMes = new BigDecimal(0D);
			int numeroClienteOficina  = 0;
			int numeroProductoCliente = 0;
			int numeroMedioOficina = 0;
			BigDecimal valorPautaMes = new BigDecimal(0D);
						
			tblInversionesPlanesMedio = (DefaultTableModel)getTblInversionesPlanMedios().getModel();
						
			//CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS, nada esta seleccionado (totales por Cliente)
			if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected()  &&
					!getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					!getCbMostrarTipoMedio().isSelected() && !getCbMostrarMedio().isSelected() && 
					!getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() && 
					!getCbMostrarDerechoPrograma().isSelected()){
									
				Iterator mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
				Map<String,BigDecimal> mesesTotales = new HashMap<String,BigDecimal>();
				ClienteOficinaIf clienteOficinaTempIf = null;
									
				while (mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
				
					Map mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
					Iterator mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
				
					numeroClienteOficina = mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
				
					while (mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
					
						mesesTotales = inicializarMapMesesValor();
						
						Map mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
						Iterator mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
						while (mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idMarcaProducto = (Long) mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
														
							Map mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
							Iterator mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
							
							numeroProductoCliente = mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
						
							while (mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idProductoCliente = (Long) mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
								
								Map mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
								Iterator mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																
								
								while (mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idCampana = (Long) mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
									
									Map mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCampana);
									Iterator mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																
								
									while (mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
											
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
									
										while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
											Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											ClienteIf medioTempIf = mapaCliente.get(idMedio);
																							
											Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
											Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
										
											while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
																							
												Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
												Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
												while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																						
													Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
													Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																					
													while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
														Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
															
														Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
														Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
														
														while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
															mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
															valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
															
															valorPautaMes = mesesTotales.get(mes);
															valorPautaMes = valorPautaMes.add(valorMes);
															
															mesesTotales.put(mes, valorPautaMes);
														}
													}													
												}												
											}		
										}
									}
								}								
							}
							
						}
					}
					
					
					Vector<String> fila = new Vector<String>();
					fila.add(clienteTempIf.getNombreLegal());// + " / " + productoClienteTempIf.getNombre());// + " / " + medioTempIf.getNombreLegal());
					BigDecimal valor = new BigDecimal(0D);
					
					InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
					
					if (numeroClienteOficina > 1){
						inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
						inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
					}
												
					inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
					inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
					
					for (String mesTotal : mesesTotales.keySet()){	
						valorMes = mesesTotales.get(mesTotal);
						agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
						valor = valor.add(valorMes);
					}
					
					fila.add(formatoDecimal.format(valor).toString());
					inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
						
					tblInversionesPlanesMedio.addRow(fila);
					inversionColeccion.add(inversionClienteMesData);
				}
			}	
		
			//CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS, presentar Marca y Producto, o solo Producto
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					getCbMostrarClienteOficina().isSelected() && (getCbMostrarMarca().isSelected() ||
					getCbMostrarProducto().isSelected() || getCbMostrarCampana().isSelected()) && 
					!getCbMostrarTipoMedio().isSelected() && !getCbMostrarMedio().isSelected() && 
					!getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() && 
					!getCbMostrarDerechoPrograma().isSelected()){
				
				Iterator mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
			
				Map<String,BigDecimal> mesesTotales = new HashMap<String,BigDecimal>();
				ProductoClienteIf productoClienteTempIf = null;
				MarcaProductoIf marcaProductoTempIf = null;
				ClienteOficinaIf clienteOficinaTempIf = null;
				CampanaIf campanaTempIf = null;
			
				while (mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
				
					Map mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
					Iterator mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
				
					numeroClienteOficina = mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
				
					while (mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
					
						Map mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
						Iterator mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
						while (mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idMarcaProducto = (Long) mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
						
							if(getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected()){
								mesesTotales = inicializarMapMesesValor();
							}
							
							Map mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
							Iterator mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
												
							numeroProductoCliente = mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
						
							while (mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idProductoCliente = (Long) mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
								
								if(getCbMostrarProducto().isSelected()){
									mesesTotales = inicializarMapMesesValor();
								}
								
								Map mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
								Iterator mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																
								
								while (mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idCampana = (Long) mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
									
									if(!getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected()){
										mesesTotales = inicializarMapMesesValor();
									}									
									
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCampana);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																	
								
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
											
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
									
										while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
											Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											ClienteIf medioTempIf = mapaCliente.get(idMedio);
																							
											Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
											Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
										
											while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
												
												Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
												Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
												while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																						
													Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
													Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																					
													while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
														Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
															
														Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
														Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
														
														while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
															mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
															valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
															
															valorPautaMes = mesesTotales.get(mes);
															valorPautaMes = valorPautaMes.add(valorMes);
															
															mesesTotales.put(mes, valorPautaMes);
														}
													}													
												}												
											}		
										}
									}
									
									//Cuando solo esta seccionado campaña
									if(!getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected()){
										Vector<String> fila = new Vector<String>();
										fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre());
										BigDecimal valor = new BigDecimal(0D);
										
										InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
										
										if (numeroClienteOficina > 1){
											inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
											inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
										}
										
										inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
										inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
																		
										if (getCbMostrarMarca().isSelected()){
											inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
											inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
										}
										
										if (getCbMostrarProducto().isSelected()){
											inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
											inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
										}	
										
										if (getCbMostrarCampana().isSelected()){
											campanaTempIf = SessionServiceLocator.getCampanaSessionService().getCampana(idCampana);
											inversionClienteMesData.setCampana(campanaTempIf.getNombre());
											inversionClienteMesData.setCampanaId(campanaTempIf.getId().toString());
										}
										
										for (String mesTotal : mesesTotales.keySet()){	
											valorMes = mesesTotales.get(mesTotal);
											agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
											valor = valor.add(valorMes);
										}
										
										fila.add(formatoDecimal.format(valor).toString());
										inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
											
										tblInversionesPlanesMedio.addRow(fila);
										inversionColeccion.add(inversionClienteMesData);
									}									
								}
								
								//cuando solo esta seleccionado producto
								if(getCbMostrarProducto().isSelected()){
									Vector<String> fila = new Vector<String>();
									fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre());
									BigDecimal valor = new BigDecimal(0D);
									
									InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
									
									if (numeroClienteOficina > 1){
										inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
										inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
									}
									
									inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
									inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
																	
									if (getCbMostrarMarca().isSelected()){
										inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
										inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
									}
									
									if (getCbMostrarProducto().isSelected()){
										inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
										inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
									}	
									
									if (getCbMostrarCampana().isSelected()){
										inversionClienteMesData.setCampana(campanaTempIf.getNombre());
										inversionClienteMesData.setCampanaId(campanaTempIf.getId().toString());
									}
									
									for (String mesTotal : mesesTotales.keySet()){	
										valorMes = mesesTotales.get(mesTotal);
										agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
										valor = valor.add(valorMes);
									}
									
									fila.add(formatoDecimal.format(valor).toString());
									inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
										
									tblInversionesPlanesMedio.addRow(fila);
									inversionColeccion.add(inversionClienteMesData);
								}
								
							}
							
							//Cuando solo esta seccionado marca (puede estar producto tambien seleccionado)
							if(getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected()){
								Vector<String> fila = new Vector<String>();
								fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre());
								BigDecimal valor = new BigDecimal(0D);
								
								InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
								
								if (numeroClienteOficina > 1){
									inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
									inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
								}
								
								inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
								inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
																
								if (getCbMostrarMarca().isSelected()){
									inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
									inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
								}
								
								if (getCbMostrarProducto().isSelected()){
									inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
									inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
								}	
								
								if (getCbMostrarCampana().isSelected()){
									inversionClienteMesData.setCampana(campanaTempIf.getNombre());
									inversionClienteMesData.setCampanaId(campanaTempIf.getId().toString());
								}
								
								for (String mesTotal : mesesTotales.keySet()){	
									valorMes = mesesTotales.get(mesTotal);
									agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
									valor = valor.add(valorMes);
								}
								
								fila.add(formatoDecimal.format(valor).toString());
								inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
									
								tblInversionesPlanesMedio.addRow(fila);
								inversionColeccion.add(inversionClienteMesData);
							}	
							
						}
					}				
				}
			}

			//CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS, presentar Tipo Medio, Medio y Medio Oficina
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0 && 
					getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected() &&
					!getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					(getCbMostrarTipoMedio().isSelected() || getCbMostrarMedio().isSelected() ||
					getCbMostrarMedioOficina().isSelected()) && !getCbMostrarTipoPauta().isSelected() && 
					!getCbMostrarDerechoPrograma().isSelected()){
				
				Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>> mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>();
				Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>> mapClienteOfiTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal;
			    Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>> mapTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal; 
			    Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>> mapMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal;
				Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>> mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
				Map<String,Map<Long,Map<String,BigDecimal>>> mapTipoPautaDerechoProgramaIdMesesTotal;
				Map<Long,Map<String,BigDecimal>> mapDerechoProgramaIdMesesTotal;
				Map<String,BigDecimal> mapMesesTotal;
				
				Map<String,BigDecimal> mesesTotales;
			
				Iterator mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
				
				while (mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
					
					mapClienteOfiTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>();
					
					Map mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
					Iterator mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
					while (mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						
						mapTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>();
						
						Map mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
						Iterator mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
						while (mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idMarcaProducto = (Long) mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							
							Map mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
							Iterator mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
									
							
							while (mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idProductoCliente = (Long) mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																			
								Map mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
								Iterator mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																
							
								while (mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idCampana = (Long) mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																				
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCampana);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
											
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												
										mapMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>();
																				
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										
										if (!mapTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.containsKey(idTipoMedio))
											mapTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio, mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
										else{
											mapMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
																				
											Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												
												mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>();
													
												Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
												
												if (!mapMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedio))
													mapMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.put(idMedio, mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
												else{
													mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
													
													Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
													numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
											
														while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
															Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
															
															mapTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<String,Map<Long,Map<String,BigDecimal>>>();
																														
															Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
															
															if (!mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedioOficina))
																mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina, mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
															else{
																mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
																															
																Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
													
																while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																	String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																	
																	mapDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																	
																	Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
																	
																	if (!mapTipoPautaDerechoProgramaIdMesesTotal.containsKey(tipoPautaTemp))
																		mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp, mapDerechoProgramaIdMesesTotalOrdenesProducto);	
																	else{
																		mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
																			
																		Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																								
																		while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																			Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																			
																			mapMesesTotal = new LinkedHashMap<String,BigDecimal>();
																			
																			Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
																			
																			if (!mapDerechoProgramaIdMesesTotal.containsKey(idDerechoPrograma))
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma, mapMesesTotalOrdenesProductoAndCanal);	
																			else{
																				mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
																			
																				Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
																																					
																				BigDecimal valor = new BigDecimal(0D);
																			
																				while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
																					mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
																					valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
																					
																					valor = mapMesesTotal.get(mes);
																					valor = valor.add(valorMes);
																					mapMesesTotal.put(mes,valor);																
																				}
																				
																				mapDerechoProgramaIdMesesTotal.remove(idDerechoPrograma);
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma,mapMesesTotal);
																		} 
																	}
																	
																	mapTipoPautaDerechoProgramaIdMesesTotal.remove(tipoPautaTemp);
																	mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp,mapDerechoProgramaIdMesesTotal);
																		
																}
															}
																
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedioOficina);
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina,mapTipoPautaDerechoProgramaIdMesesTotal);
															
														}
													}
													
													mapMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.remove(idMedio);
													mapMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.put(idMedio,mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
												}
											}
											
											mapTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.remove(idTipoMedio);
											mapTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio,mapMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal);
											
										}
									}
								}
								
							}
							
						}
						mapClienteOfiTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.put(idClienteOficina, mapTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal);
					}
					mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idCliente, mapClienteOfiTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal);
				}
	
				Iterator mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
			
				while(mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
					mapClienteOfiTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idCliente);
					numeroClienteOficina = mapClienteOfiTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.size();
					
					Iterator mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteOfiTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
					
					while(mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
						ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
						mapTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal = mapClienteOfiTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.get(idClienteOficina);
						Iterator mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
						
						while(mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
							Long idTipoMedio = (Long) mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
							TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
							
							//mesesTotales = inicializarMapMesesValor();
							
							if(tipoProveedorTempIf != null){
								mapMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
								Iterator mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
									
								while(mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
									Long idMedio = (Long) mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
									ClienteIf medioTempIf = mapaCliente.get(idMedio);
									
									//mesesTotales = inicializarMapMesesValor();
										
									mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOfiTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
									numeroMedioOficina = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
									
									Iterator mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();	
									
									while(mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
										Long idMedioOficina = (Long) mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
										ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
														
										mesesTotales = inicializarMapMesesValor();
										
										mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
										Iterator mapTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
											
										while(mapTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
											String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalIt.next();
																																									
											mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
											Iterator mapDerechoProgramaIdMesesTotalIt = mapDerechoProgramaIdMesesTotal.keySet().iterator();
											
											while(mapDerechoProgramaIdMesesTotalIt.hasNext()){
												Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalIt.next();
																						
												mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
												Iterator mapMesesTotalIt = mapMesesTotal.keySet().iterator();
													
												while (mapMesesTotalIt.hasNext()){
													mes = (String) mapMesesTotalIt.next();
													valorMes = (BigDecimal) mapMesesTotal.get(mes);
													
													valorPautaMes = mesesTotales.get(mes);
													valorPautaMes = valorPautaMes.add(valorMes);
													mesesTotales.put(mes, valorPautaMes);			
												}
											}											
										}
										
										Vector<String> fila = new Vector<String>();
										fila.add(clienteTempIf.getNombreLegal() + " / " + medioTempIf.getNombreLegal());
										BigDecimal valor = new BigDecimal(0D);
										
										InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
										
										if (numeroClienteOficina > 1){
											inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
											inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
										}
										if (getCbMostrarMedio().isSelected() && numeroMedioOficina > 1){
											inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
											inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
										}
																				
										inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
										inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
										inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());									
										inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
										inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
										inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
										
										for (String mesTotal : mesesTotales.keySet()){	
											valorMes = mesesTotales.get(mesTotal);
											agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
											valor = valor.add(valorMes);
										}
										
										fila.add(formatoDecimal.format(valor).toString());
										inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
											
										tblInversionesPlanesMedio.addRow(fila);
										inversionColeccion.add(inversionClienteMesData);
									}
								}
							}						
						}
					}						
				}
			}
			
			//CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS, presentar Marca (opcional), Producto , Tipo de Medio, Medio y Medio Oficina
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() &&  getCbMostrarProducto().isSelected() && 
					 !getCbMostrarCampana().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   && 
					 !getCbMostrarTipoPauta().isSelected() && !getCbMostrarDerechoPrograma().isSelected()){
							
				Iterator mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
				Map<String,BigDecimal> mesesTotales;
				
				while (mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
				
					Map mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
					Iterator mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
				
					numeroClienteOficina = mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
				
					while (mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
					
						Map mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
						Iterator mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
						while (mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idMarcaProducto = (Long) mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
						
							Map mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
							Iterator mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
												
							numeroProductoCliente = mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
						
							
							while (mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idProductoCliente = (Long) mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
									
								Map mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
								Iterator mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																
							
								while (mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idCampana = (Long) mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCampana);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
										
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
										
										if(tipoProveedorTempIf != null){
											Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
											Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												ClienteIf medioTempIf = mapaCliente.get(idMedio);
												
												//mesesTotales = inicializarMapMesesValor();
													
												Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
												Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
												numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
											
												while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
													ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
															
													mesesTotales = inicializarMapMesesValor();
													
													Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
													Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
												
													while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
														String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																							
														Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
														Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																						
														while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
															Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																												
															Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
															Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
															
															while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
																mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
																valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
																
																valorPautaMes = mesesTotales.get(mes);
																valorPautaMes = valorPautaMes.add(valorMes);
																
																mesesTotales.put(mes, valorPautaMes);
															}
														}													
													}
		
													Vector<String> fila = new Vector<String>();
													fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre() + " / " + medioTempIf.getNombreLegal());
													BigDecimal valor = new BigDecimal(0D);
													
													InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
													
													if (numeroClienteOficina > 1){
														inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
														inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
													}
													if ( getCbMostrarMarca().isSelected()){
														inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
														inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
													}
													if (numeroMedioOficina > 1){
														inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
														inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
													}
													
													inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
													inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
													inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
													inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
													inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
													inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
													inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
													inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
													
													for (String mesTotal : mesesTotales.keySet()){	
														valorMes = mesesTotales.get(mesTotal);
														agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
														valor = valor.add(valorMes);
													}
													
													fila.add(formatoDecimal.format(valor).toString());
													inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
														
													tblInversionesPlanesMedio.addRow(fila);
													inversionColeccion.add(inversionClienteMesData);
												}		
											}
										}								
									}
								}						
							}					
						}	
					}
				}
			}

			//CLIENTES/T-MEDIOS/MEDIOS, presentar Tipo Medio, Medio, Medio Oficina y Tipo Pauta
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected() &&
					 !getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
					 getCbMostrarTipoPauta().isSelected() && !getCbMostrarDerechoPrograma().isSelected()){
			
				Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>> mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>();
				Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>> mapClienteOfiTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
			    Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>> mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal; 
			    Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>> mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
				Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>> mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
				Map<String,Map<Long,Map<String,BigDecimal>>> mapTipoPautaDerechoProgramaIdMesesTotal;
				Map<Long,Map<String,BigDecimal>> mapDerechoProgramaIdMesesTotal;
				Map<String,BigDecimal> mapMesesTotal;
				
				Map<String,BigDecimal> mesesTotales;
			
				Iterator mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
				
				while (mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
					
					mapClienteOfiTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>();
					
					Map mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
					Iterator mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
					while (mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						
						mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>();
						
						Map mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
						Iterator mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
						while (mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idMarcaProducto = (Long) mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							
							Map mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
							Iterator mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
															
							while (mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idProductoCliente = (Long) mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																			
								Map mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
								Iterator mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
									
								
								while (mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idCampana = (Long) mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																				
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCampana);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																		
								
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												
										mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>();
																				
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										
										if (!mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idTipoMedio))
											mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio, mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
										else{
											mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
																				
											Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												
												mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>();
													
												Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
												
												if (!mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedio))
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio, mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
												else{
													mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
													
													Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
													numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
											
														while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
															Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
															
															mapTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<String,Map<Long,Map<String,BigDecimal>>>();
																														
															Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
															
															if (!mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedioOficina))
																mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina, mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
															else{
																mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
																															
																Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
													
																while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																	String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																	
																	mapDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																	
																	Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
																	
																	if (!mapTipoPautaDerechoProgramaIdMesesTotal.containsKey(tipoPautaTemp))
																		mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp, mapDerechoProgramaIdMesesTotalOrdenesProducto);	
																	else{
																		mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
																			
																		Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																								
																		while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																			Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																			
																			mapMesesTotal = new LinkedHashMap<String,BigDecimal>();
																			
																			Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
																			
																			if (!mapDerechoProgramaIdMesesTotal.containsKey(idDerechoPrograma))
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma, mapMesesTotalOrdenesProductoAndCanal);	
																			else{
																				mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
																			
																				Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
																																					
																				BigDecimal valor = new BigDecimal(0D);
																			
																				while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
																					mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
																					valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
																					
																					valor = mapMesesTotal.get(mes);
																					valor = valor.add(valorMes);
																					mapMesesTotal.put(mes,valor);																
																				}
																				
																				mapDerechoProgramaIdMesesTotal.remove(idDerechoPrograma);
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma,mapMesesTotal);
																		} 
																	}
																	mapTipoPautaDerechoProgramaIdMesesTotal.remove(tipoPautaTemp);
																	mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp,mapDerechoProgramaIdMesesTotal);
																}
															}
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedioOficina);
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina,mapTipoPautaDerechoProgramaIdMesesTotal);
														}
													}
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedio);
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio,mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
												}
											}
											mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idTipoMedio);
											mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio,mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
										}
									}
								}
							}	
						}
						mapClienteOfiTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idClienteOficina, mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
					}
					mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idCliente, mapClienteOfiTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
				}
	
				Iterator mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
				
				while(mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
					mapClienteOfiTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idCliente);
					numeroClienteOficina = mapClienteOfiTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
					
					Iterator mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteOfiTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
					
					while(mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
						ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
						mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteOfiTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idClienteOficina);
						Iterator mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
						
						while(mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
							Long idTipoMedio = (Long) mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
							TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
							
							//tipoProveedorTempIf es nulo si el idTipoMedio (id de tipo de proveedor) no es de Medios
							if(tipoProveedorTempIf != null){
								mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
								Iterator mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
									
								while(mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
									Long idMedio = (Long) mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
									ClienteIf medioTempIf = mapaCliente.get(idMedio);
										
									mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
									numeroMedioOficina = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
									
									Iterator mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();	
									
									while(mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
										Long idMedioOficina = (Long) mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
										ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
									
										mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
										Iterator mapTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
											
										while(mapTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
											String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalIt.next();
											
											Vector<String> fila = new Vector<String>();
											fila.add(clienteTempIf.getNombreLegal() + " / "  + medioTempIf.getNombreLegal());
																																
											BigDecimal valor = new BigDecimal(0D);
											
											mesesTotales = inicializarMapMesesValor();
											
											InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
												
											if (numeroClienteOficina > 1){ //CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL CLIENTE OFCINA SIN PONER AL CLIENTE
												inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
												inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
											}												
											if (numeroMedioOficina > 1){	//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL MEDIOOFICINA SIN PONER AL MEDIO
												inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
												inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
											}
																								
											inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
											inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
											inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
											inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
																																
											inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
											inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
											inversionClienteMesData.setTipoPauta(tipoPautaTemp);
																					
											mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
											Iterator mapDerechoProgramaIdMesesTotalIt = mapDerechoProgramaIdMesesTotal.keySet().iterator();
											
											while(mapDerechoProgramaIdMesesTotalIt.hasNext()){
												Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalIt.next();
												DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
												
												mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
												Iterator mapMesesTotalIt = mapMesesTotal.keySet().iterator();
													
												while (mapMesesTotalIt.hasNext()){
													mes = (String) mapMesesTotalIt.next();
													valorMes = (BigDecimal) mapMesesTotal.get(mes);
													
													valorPautaMes = mesesTotales.get(mes);
													valorPautaMes = valorPautaMes.add(valorMes);
													mesesTotales.put(mes, valorPautaMes);																								
												}											
											}
											
											for (String mesTotal : mesesTotales.keySet()){
												valorMes = mesesTotales.get(mesTotal);
												agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
												valor = valor.add(valorMes);
											}
																								
											fila.add(formatoDecimal.format(valor).toString());
											inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
																									
											tblInversionesPlanesMedio.addRow(fila);
											inversionColeccion.add(inversionClienteMesData);
										}
									}
								}
							}						
						}
					}						
				}
			}
			
			//MOSTRAR CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS SE MUESTRA TODO
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
				 getCbMostrarClienteOficina().isSelected() &&
				 getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
				 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
				 getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
							
				Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
				
				while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
					Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
					Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
					numeroClienteOficina = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
					
					while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
						Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
						Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
						while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
							
							Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
							Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
													
							numeroProductoCliente = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
							
							while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
										
								Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
								Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
										
								while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
									TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
											
									Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
									Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
									
									while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										ClienteIf medioTempIf = mapaCliente.get(idMedio);
												
										Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
										Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
										numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
										
										while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
											Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
													
											Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
											Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
											while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																										
												Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
												Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																					
												while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
													DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
													
													Vector<String> fila = new Vector<String>();
													fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre() + " / " + medioTempIf.getNombreLegal());
														
													Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
													Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
													BigDecimal valor = new BigDecimal(0D);
														
													InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
													
													//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL CLIENTE OFCINA SIN PONER AL CLIENTE
													if (numeroClienteOficina > 1){ 
														inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
														inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());														
													}
													
													//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL PRODUCTO SIN PONER A LA MARCA
													if (getCbMostrarMarca().isSelected()){	
														inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
														inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
													}
													
													//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL MEDIOOFICINA SIN PONER AL MEDIO
													if (numeroMedioOficina > 1){	
														inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
														inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
													}
													
													inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
													inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
													inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
													inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
													inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
													inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
													
													inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
													inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
													inversionClienteMesData.setTipoPauta(tipoPautaTemp);
													inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getId().toString());
													inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getNombre());
														
													while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
														mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
														valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
																					
														agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
														
														valor = valor.add(valorMes);															
													}
														
													fila.add(formatoDecimal.format(valor).toString());
													
													inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
														
													tblInversionesPlanesMedio.addRow(fila);
														
													inversionColeccion.add(inversionClienteMesData);
												}
											}
										}		
									}
								}
							}							
						}	
					}
				}
			}
			
			//PARA MOSTRAR CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS SE MUESTRA TODO EXCEPTO EL DERECHO DE PROGRAMA
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && getCbMostrarProducto().isSelected() && 
					 !getCbMostrarCampana().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   && 
					 getCbMostrarTipoPauta().isSelected() && !getCbMostrarDerechoPrograma().isSelected()){
													
					Iterator mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					Map<String,BigDecimal> mesesTotales;
					
					while (mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
						Map mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
						numeroClienteOficina = mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
					
						while (mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
							Map mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
							while (mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
							
								Map mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
													
								numeroProductoCliente = mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
							
								while (mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
									ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
										
									Map mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
										
									
									while (mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idCampana = (Long) mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											
										Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCampana);
										Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																				
									
										while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
											Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
												
											Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
											Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												ClienteIf medioTempIf = mapaCliente.get(idMedio);
													
												Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
												Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
												numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
											
												while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
													ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
														
													Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
													Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
												
													while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
														String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
														
														Vector<String> fila = new Vector<String>();
														fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre() + " / " + medioTempIf.getNombreLegal());
														BigDecimal valor = new BigDecimal(0D);
														
														InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
														
														mesesTotales = inicializarMapMesesValor();
															
														//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL CLIENTE OFICINA SIN EL CLIENTE
														if (numeroClienteOficina > 1){ 
															inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
															inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
														}
														
														//CASO CONTRARIO SE MUESTRA DIRECTAMENTE LA MARCA SIN EL PRODUCTO
														if (getCbMostrarMarca().isSelected()){	
															inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
															inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
														}
														
														//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL MEDIO OFICINA SIN EL MEDIO
														if (numeroMedioOficina > 1){ 
															inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
															inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
														}
														
														inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
														inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
														inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
														inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
														inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
														inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
														inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
														inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
														inversionClienteMesData.setTipoPauta(tipoPautaTemp);
																																								
														Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
														Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																						
														while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
															Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
															DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);	
															
															Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
															Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
															
															while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
																mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
																valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
																
																valorPautaMes = mesesTotales.get(mes);
																valorPautaMes = valorPautaMes.add(valorMes);
																mesesTotales.put(mes, valorPautaMes);
															}
														}
														
														for (String mesTotal : mesesTotales.keySet()){
															valorMes = mesesTotales.get(mesTotal);
															agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
															valor = valor.add(valorMes);
														}
														
														fila.add(formatoDecimal.format(valor).toString());
														inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
															
														tblInversionesPlanesMedio.addRow(fila);
														inversionColeccion.add(inversionClienteMesData);
													}
												}		
											}
										}
										
									}
									
								}							
							}	
						}
					}
			}
			
			//PARA MOSTRAR CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS SE MUESTRA TODO EXCEPTO EL TIPO DE PAUTA CON DERECHO DE PROGRAMA
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && 
					 getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   && 
					 !getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
									
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					Map<String,BigDecimal> mesesTotales;
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
						numeroClienteOficina = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
					
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
							
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
													
								numeroProductoCliente = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
							
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
									ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
										
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
										
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
											
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
									
										while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
											Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											ClienteIf medioTempIf = mapaCliente.get(idMedio);
												
											Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
											Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
										
											while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
												
												Map<Long,Map<String,BigDecimal>> mapaDerechoProgramaMesesTotales = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																								
												Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
												Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
												while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																						
													Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
													Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																					
													while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
														Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
														
														//mesesTotales = inicializarMapMesesValor();
														
														if (!mapaDerechoProgramaMesesTotales.containsKey(idDerechoPrograma)){
															mesesTotales = inicializarMapMesesValor();
															mapaDerechoProgramaMesesTotales.put(idDerechoPrograma, mesesTotales);
														}else
															mesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);
														
														Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
														Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
														
														while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
															mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
															valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
															
															valorPautaMes = mesesTotales.get(mes);
															valorPautaMes = valorPautaMes.add(valorMes);
															
															mesesTotales.put(mes, valorPautaMes);
														}
														
														mapaDerechoProgramaMesesTotales.remove(idDerechoPrograma);
														mapaDerechoProgramaMesesTotales.put(idDerechoPrograma, mesesTotales);
													}													
												}

												for (Long idDerechoPrograma : mapaDerechoProgramaMesesTotales.keySet()){
													DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
													
													Vector<String> fila = new Vector<String>();
													fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre() + " / " + medioTempIf.getNombreLegal());
													BigDecimal valor = new BigDecimal(0D);
													
													InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
													
													if (numeroClienteOficina > 1){
														inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
														inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
													}
													if (getCbMostrarMarca().isSelected()){
														inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
														inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
													}
													if (numeroMedioOficina > 1){
														inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
														inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
													}
													
													inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
													inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
													inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
													inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
													inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
													inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
													inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
													inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
													inversionClienteMesData.setDerechoProgramaId(derechoProgramaTempIf.getId().toString());
													inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getNombre());
													
													Map<String,BigDecimal> mapMesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);
													
													for (String mesTotal : mapMesesTotales.keySet()){	
														valorMes = mapMesesTotales.get(mesTotal);
														agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
														valor = valor.add(valorMes);
													}
													
													fila.add(formatoDecimal.format(valor).toString());
													inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
														
													tblInversionesPlanesMedio.addRow(fila);
													inversionColeccion.add(inversionClienteMesData);
												}
											}		
										}
									}
								}							
							}	
						}
					}
			}						
				
			
			//PARA MOSTRAR CLIENTES/T-MEDIOS/MEDIOS SE AGRUPA SIN PRODUCTO 
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && getCbMostrarMarca().isSelected() &&
					 !getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
					 getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
					
					Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>> mapClienteClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>();
					Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>> mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
				    Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>> mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal; 
				    Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>> mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal; 
					Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>> mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>> mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<String,Map<Long,Map<String,BigDecimal>>> mapTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,BigDecimal>> mapDerechoProgramaIdMesesTotal;
					Map<String,BigDecimal> mapMesesTotal;
				
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												
						mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>();
						
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
														
							mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>();
							
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
							
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								
								mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>();
								
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																				
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
											
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																				
										mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>();
																				
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										
										if (!mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idTipoMedio))
											mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio, mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
										else{
											mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
																				
											Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																								
												mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>();
													
												Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
												
												if (!mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedio))
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio, mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
												else{
													mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
													
													Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
													numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
											
														while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
															Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																													
															mapTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<String,Map<Long,Map<String,BigDecimal>>>();
																														
															Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
															
															if (!mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedioOficina))
																mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina, mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
															else{
																mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
																															
																Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
													
																while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																	String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																	
																	mapDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																	
																	Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
																	
																	if (!mapTipoPautaDerechoProgramaIdMesesTotal.containsKey(tipoPautaTemp))
																		mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp, mapDerechoProgramaIdMesesTotalOrdenesProducto);	
																	else{
																		mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
																			
																		Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																								
																		while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																			Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																						
																			mapMesesTotal = new LinkedHashMap<String,BigDecimal>();
																			
																			Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
																			
																			if (!mapDerechoProgramaIdMesesTotal.containsKey(idDerechoPrograma))
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma, mapMesesTotalOrdenesProductoAndCanal);	
																			else{
																				mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
																			
																				Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
																																					
																				BigDecimal valor = new BigDecimal(0D);
																			
																				while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
																					mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
																					valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
																												
																					valor = mapMesesTotal.get(mes);
																					valor = valor.add(valorMes);
																					mapMesesTotal.put(mes,valor);																
																				}
																				
																				mapDerechoProgramaIdMesesTotal.remove(idDerechoPrograma);
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma,mapMesesTotal);
																		} 
																	}
																	
																	mapTipoPautaDerechoProgramaIdMesesTotal.remove(tipoPautaTemp);
																	mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp,mapDerechoProgramaIdMesesTotal);
																		
																}
															}
																
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedioOficina);
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina,mapTipoPautaDerechoProgramaIdMesesTotal);
															
														}
													}
													
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedio);
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio,mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
																												
											}
										}
											
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idTipoMedio);
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio,mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
											
									}
								}
							} 		
							mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMarcaProducto, mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
						}	
						mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.put(idClienteOficina, mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
					}
					mapClienteClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idCliente, mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);
				}
				
				Iterator mapClienteClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
				
				while(mapClienteClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
					mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idCliente);
					numeroClienteOficina = mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
					
					Iterator mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
					
					while(mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
						ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
						mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idClienteOficina);
						Iterator mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
						
						while(mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
							Long idMarcaProducto = (Long) mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
							MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
							
							mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMarcaProducto);
							Iterator mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
							
							while(mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
								Long idTipoMedio = (Long) mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
								TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
								
								mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
								Iterator mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
								
								while(mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
									Long idMedio = (Long) mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
									ClienteIf medioTempIf = mapaCliente.get(idMedio);
									
									mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
									numeroMedioOficina = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
									
									Iterator mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();	
									
									while(mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
										Long idMedioOficina = (Long) mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
										ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
									
										mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
										Iterator mapTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
										
										while(mapTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
											String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalIt.next();
											
											mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
											Iterator mapDerechoProgramaIdMesesTotalIt = mapDerechoProgramaIdMesesTotal.keySet().iterator();
											
											while(mapDerechoProgramaIdMesesTotalIt.hasNext()){
												Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalIt.next();
												DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
												
												Vector<String> fila = new Vector<String>();
												fila.add(clienteTempIf.getNombreLegal() + " / "  + medioTempIf.getNombreLegal());
																																	
												BigDecimal valor = new BigDecimal(0D);
												
												InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
												
												if (numeroClienteOficina > 1){ //CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL CLIENTE OFCINA SIN PONER AL CLIENTE
													inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
													inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
												}												
												if (getCbMostrarMarca().isSelected()){	//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL PRODUCTO SIN PONER A LA MARCA
													inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
													inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
												}												
												if (numeroMedioOficina > 1){	//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL MEDIOOFICINA SIN PONER AL MEDIO
													inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
													inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
												}
																								
												inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
												inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
												inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
												inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
																																
												inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
												inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
												inversionClienteMesData.setTipoPauta(tipoPautaTemp);
												inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getId().toString());
												inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getNombre());
												
												mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
												Iterator mapMesesTotalIt = mapMesesTotal.keySet().iterator();
												
												while (mapMesesTotalIt.hasNext()){
													mes = (String) mapMesesTotalIt.next();
													valorMes = (BigDecimal) mapMesesTotal.get(mes);
																										
													agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
													valor = valor.add(valorMes);													
												}
												
												fila.add(formatoDecimal.format(valor).toString());
												inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
												tblInversionesPlanesMedio.addRow(fila);
												inversionColeccion.add(inversionClienteMesData);
											}
										}
									}
								}
							}
						}
					}						
				}
				
			}
			
			//PARA MOSTRAR CLIENTES/T-MEDIOS/MEDIOS SE AGRUPA SIN MARCA Y PRODUCTO
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected() &&
					 !getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
					 getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
					
					Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>> mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>();
					Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>> mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
				    Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>> mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal; 
				    Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>> mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>> mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<String,Map<Long,Map<String,BigDecimal>>> mapTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,BigDecimal>> mapDerechoProgramaIdMesesTotal;
					Map<String,BigDecimal> mapMesesTotal;
				
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						
						mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>();
						
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							
							mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>();
							
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
							
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																				
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
											
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																				
										mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>();
																				
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										
										if (!mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idTipoMedio))
											mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio, mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
										else{
											mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
																				
											Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																								
												mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>();
													
												Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
												
												if (!mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedio))
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio, mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
												else{
													mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
													
													Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
													numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
											
														while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
															Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																													
															mapTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<String,Map<Long,Map<String,BigDecimal>>>();
																														
															Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
															
															if (!mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedioOficina))
																mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina, mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
															else{
																mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
																															
																Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
													
																while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																	String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																	
																	mapDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																	
																	Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
																	
																	if (!mapTipoPautaDerechoProgramaIdMesesTotal.containsKey(tipoPautaTemp))
																		mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp, mapDerechoProgramaIdMesesTotalOrdenesProducto);	
																	else{
																		mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
																			
																		Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																								
																		while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																			Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																						
																			mapMesesTotal = new LinkedHashMap<String,BigDecimal>();
																			
																			Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
																			
																			if (!mapDerechoProgramaIdMesesTotal.containsKey(idDerechoPrograma))
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma, mapMesesTotalOrdenesProductoAndCanal);	
																			else{
																				mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
																			
																				Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
																																					
																				BigDecimal valor = new BigDecimal(0D);
																			
																				while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
																					mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
																					valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
																												
																					valor = mapMesesTotal.get(mes);
																					valor = valor.add(valorMes);
																					mapMesesTotal.put(mes,valor);																
																				}
																				
																				mapDerechoProgramaIdMesesTotal.remove(idDerechoPrograma);
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma,mapMesesTotal);
																		} 
																	}
																	
																	mapTipoPautaDerechoProgramaIdMesesTotal.remove(tipoPautaTemp);
																	mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp,mapDerechoProgramaIdMesesTotal);
																		
																}
															}
																
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedioOficina);
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina,mapTipoPautaDerechoProgramaIdMesesTotal);
															
														}
													}
													
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedio);
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio,mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
																												
											}
										}
											
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idTipoMedio);
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio,mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
											
									}
								}
							}										
						}
						mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idClienteOficina, mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
					}
					mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idCliente, mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
				}
				
				Iterator mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
				
				while(mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
					mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idCliente);
					numeroClienteOficina = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
					
					Iterator mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
					
					while(mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
						ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
						mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idClienteOficina);
						Iterator mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
						
						while(mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
							Long idTipoMedio = (Long) mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
							TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
							
							mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
							Iterator mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
								
							while(mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
								Long idMedio = (Long) mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
								ClienteIf medioTempIf = mapaCliente.get(idMedio);
									
								mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
								numeroMedioOficina = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
								
								Iterator mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();	
								
								while(mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
									Long idMedioOficina = (Long) mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
									ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
								
									mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
									Iterator mapTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
										
									while(mapTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
										String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalIt.next();
										
										mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
										Iterator mapDerechoProgramaIdMesesTotalIt = mapDerechoProgramaIdMesesTotal.keySet().iterator();
										
										while(mapDerechoProgramaIdMesesTotalIt.hasNext()){
											Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalIt.next();
											DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
											
											Vector<String> fila = new Vector<String>();
											fila.add(clienteTempIf.getNombreLegal() + " / "  + medioTempIf.getNombreLegal());
																																
											BigDecimal valor = new BigDecimal(0D);
											
											InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
												
											if (numeroClienteOficina > 1){ //CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL CLIENTE OFCINA SIN PONER AL CLIENTE
												inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
												inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
											}												
											if (numeroMedioOficina > 1){	//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL MEDIOOFICINA SIN PONER AL MEDIO
												inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
												inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
											}
																								
											inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
											inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
											inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
											inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
																																
											inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
											inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
											inversionClienteMesData.setTipoPauta(tipoPautaTemp);
											inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getId().toString());
											inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getNombre());
												
											mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
											Iterator mapMesesTotalIt = mapMesesTotal.keySet().iterator();
												
											while (mapMesesTotalIt.hasNext()){
												mes = (String) mapMesesTotalIt.next();
												valorMes = (BigDecimal) mapMesesTotal.get(mes);
																								
												agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
												valor = valor.add(valorMes);													
											}
												
											fila.add(formatoDecimal.format(valor).toString());
											inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
											tblInversionesPlanesMedio.addRow(fila);
											inversionColeccion.add(inversionClienteMesData);
										}
									}
								}
							}
						}
					}						
				}				
			}			
			
			//PARA MOSTRAR CLIENTES/T-MEDIOS/MEDIOS SE AGRUPA SIN MARCA Y PRODUCTO NO MUESTRA TIPO PAUTA PERO SI DERECHO PROGRAMA
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected() &&
					 !getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
					 !getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
					
					Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>> mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>();
					Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>> mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
				    Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>> mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal; 
				    Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>> mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>> mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<String,Map<Long,Map<String,BigDecimal>>> mapTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,BigDecimal>> mapDerechoProgramaIdMesesTotal;
					Map<String,BigDecimal> mapMesesTotal;
					
					Map<String,BigDecimal> mesesTotales;
				
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						
						mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>();
						
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							
							mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>();
							
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
							
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																				
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
											
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												
										mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>();
																				
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										
										if (!mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idTipoMedio))
											mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio, mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
										else{
											mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
																				
											Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												
												mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>();
													
												Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
												
												if (!mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedio))
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio, mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
												else{
													mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
													
													Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
													numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
											
														while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
															Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
															
															mapTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<String,Map<Long,Map<String,BigDecimal>>>();
																														
															Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
															
															if (!mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedioOficina))
																mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina, mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
															else{
																mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
																															
																Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
													
																while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																	String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																	
																	mapDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																	
																	Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
																	
																	if (!mapTipoPautaDerechoProgramaIdMesesTotal.containsKey(tipoPautaTemp))
																		mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp, mapDerechoProgramaIdMesesTotalOrdenesProducto);	
																	else{
																		mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
																			
																		Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																								
																		while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																			Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																			
																			mapMesesTotal = new LinkedHashMap<String,BigDecimal>();
																			
																			Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
																			
																			if (!mapDerechoProgramaIdMesesTotal.containsKey(idDerechoPrograma))
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma, mapMesesTotalOrdenesProductoAndCanal);	
																			else{
																				mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
																			
																				Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
																																					
																				BigDecimal valor = new BigDecimal(0D);
																			
																				while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
																					mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
																					valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
																					
																					valor = mapMesesTotal.get(mes);
																					valor = valor.add(valorMes);
																					mapMesesTotal.put(mes,valor);																
																				}
																				
																				mapDerechoProgramaIdMesesTotal.remove(idDerechoPrograma);
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma,mapMesesTotal);
																		}  
																	}
																	
																	mapTipoPautaDerechoProgramaIdMesesTotal.remove(tipoPautaTemp);
																	mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp,mapDerechoProgramaIdMesesTotal);
																		
																}
															}
																
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedioOficina);
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina,mapTipoPautaDerechoProgramaIdMesesTotal);
															
														}
													}
													
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedio);
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio,mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
																												
											}
										}
											
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idTipoMedio);
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio,mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
											
									}
								}
							}								
						}
						mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idClienteOficina, mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
					}
					mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idCliente, mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
				}
				
				Iterator mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
				
				while(mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
					mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idCliente);
					numeroClienteOficina = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
					
					Iterator mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
					
					while(mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
						ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
						mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idClienteOficina);
						Iterator mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
						
						while(mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
							Long idTipoMedio = (Long) mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
							TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
							
							mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
							Iterator mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
								
							while(mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
								Long idMedio = (Long) mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
								ClienteIf medioTempIf = mapaCliente.get(idMedio);
									
								mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
								numeroMedioOficina = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
								
								Iterator mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();	
								
								while(mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
									Long idMedioOficina = (Long) mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
									ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
								
									Map<Long,Map<String,BigDecimal>> mapaDerechoProgramaMesesTotales = new LinkedHashMap<Long,Map<String,BigDecimal>>();
									
									mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
									Iterator mapTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
										
									while(mapTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
										String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalIt.next();
																				
										mesesTotales = inicializarMapMesesValor();
																																								
										mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
										Iterator mapDerechoProgramaIdMesesTotalIt = mapDerechoProgramaIdMesesTotal.keySet().iterator();
										
										while(mapDerechoProgramaIdMesesTotalIt.hasNext()){
											Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalIt.next();
																					
											if (!mapaDerechoProgramaMesesTotales.containsKey(idDerechoPrograma)){
												mesesTotales = inicializarMapMesesValor();
												mapaDerechoProgramaMesesTotales.put(idDerechoPrograma, mesesTotales);
											}else
												mesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);
											
											
											mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
											Iterator mapMesesTotalIt = mapMesesTotal.keySet().iterator();
												
											while (mapMesesTotalIt.hasNext()){
												mes = (String) mapMesesTotalIt.next();
												valorMes = (BigDecimal) mapMesesTotal.get(mes);
												
												valorPautaMes = mesesTotales.get(mes);
												valorPautaMes = valorPautaMes.add(valorMes);
												mesesTotales.put(mes, valorPautaMes);			
											}
											
											mapaDerechoProgramaMesesTotales.remove(idDerechoPrograma);
											mapaDerechoProgramaMesesTotales.put(idDerechoPrograma, mesesTotales);
										}										
									}
									
									for (Long idDerechoPrograma : mapaDerechoProgramaMesesTotales.keySet()){
										DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
										
										Vector<String> fila = new Vector<String>();
										fila.add(clienteTempIf.getNombreLegal() + " / " + medioTempIf.getNombreLegal());
										BigDecimal valor = new BigDecimal(0D);
										
										InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
										
										if (numeroClienteOficina > 1){
											inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
											inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
										}
										if (numeroMedioOficina > 1){
											inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
											inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
										}
																				
										inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
										inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
										inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
										inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
										inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
										inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());										
										inversionClienteMesData.setDerechoProgramaId(derechoProgramaTempIf.getId().toString());
										inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getNombre());
										
										Map<String,BigDecimal> mapMesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);
										
										for (String mesTotal : mapMesesTotales.keySet()){	
											valorMes = mapMesesTotales.get(mesTotal);
											agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
											valor = valor.add(valorMes);
										}
										
										fila.add(formatoDecimal.format(valor).toString());
										inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
											
										tblInversionesPlanesMedio.addRow(fila);
										inversionColeccion.add(inversionClienteMesData);
									}
								}
							}
						}
					}						
				}
			}							
			
			//PARA MOSTRAR CLIENTES/T-MEDIOS/MEDIOS
			else if (mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanal.size() > 0 &&
					 getCbMostrarMedio().isSelected() && !getCbMostrarProducto().isSelected()){
				
				Iterator mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
				
				while (mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idCliente = (Long) mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
					Map mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
					Iterator mapMedioIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
					
					while (mapMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idMedio = (Long) mapMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
						ClienteIf medioTempIf = mapaCliente.get(idMedio);
						Vector<String> fila = new Vector<String>();
						fila.add(clienteTempIf.getNombreLegal() + " / " + " / " + medioTempIf.getNombreLegal());
						
						Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
						Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
						BigDecimal valor = new BigDecimal(0D);
						
						InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
						inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
						inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
						inversionClienteMesData.setRuc(clienteTempIf.getIdentificacion());
						inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
						
						while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
							mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
							valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
													
							agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
						
							valor = valor.add(valorMes);							
						}
						
						fila.add(formatoDecimal.format(valor).toString());
						inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
						
						tblInversionesPlanesMedio.addRow(fila);
						
						inversionColeccion.add(inversionClienteMesData);
					}
				}
			} 
			
			//PARA MOSTRAR CLIENTES/PRODUCTOS
			else if (mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.size() > 0 &&
					 !getCbMostrarMedio().isSelected() && getCbMostrarProducto().isSelected()){
				
				Iterator mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
				
				while (mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idCliente = (Long) mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanalIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
					Map mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal = mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
					Iterator mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
					
					while (mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProductoCliente = (Long) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
						ProductoClienteIf productoClienteTempIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(idProductoCliente);
						
						Vector<String> fila = new Vector<String>();
						fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre());
						
						Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idProductoCliente);
						Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
						BigDecimal valor = new BigDecimal(0D);
						
						InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
						inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
						inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
						inversionClienteMesData.setRuc(clienteTempIf.getIdentificacion());
						inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
						inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
						
						while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
							mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
							valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
													
							agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
						
							valor = valor.add(valorMes);
						}
						
						fila.add(formatoDecimal.format(valor).toString());
						
						inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
						
						tblInversionesPlanesMedio.addRow(fila);
						
						inversionColeccion.add(inversionClienteMesData);
					}
				}
			} 
			
			//PARA MOSTRAR CLIENTES
			else if (mapClientesIdMesesTotalOrdenesProductoAndCanal.size() > 0 &&
					!getCbMostrarMedio().isSelected() && !getCbMostrarProducto().isSelected()){
				
				Iterator mapClientesIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
				
				while (mapClientesIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idCliente = (Long) mapClientesIdMesesTotalOrdenesProductoAndCanalIt.next();
					ClienteIf clienteTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idCliente);
					
					Vector<String> fila = new Vector<String>();
					fila.add(clienteTempIf.getNombreLegal());// + " / " + productoClienteTempIf.getNombre());
					
					Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapClientesIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
					Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
					BigDecimal valor = new BigDecimal(0D);
					
					InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
					inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
					inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
					inversionClienteMesData.setRuc(clienteTempIf.getIdentificacion());
					
					while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
						valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
												
						agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
					
						valor = valor.add(valorMes);						
					}
					
					fila.add(formatoDecimal.format(valor).toString());
					
					inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
					
					tblInversionesPlanesMedio.addRow(fila);
					
					inversionColeccion.add(inversionClienteMesData);
				}
			}			
			
			System.out.println("fin que pasa");
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarTablaXTMediosClientesProducto() {
		//try {
			String mes = "";
			BigDecimal valorMes = new BigDecimal(0D);
			int numeroClienteOficina  = 0;
			int numeroProductoCliente = 0;
			int numeroMedioOficina = 0;
			BigDecimal valorPautaMes = new BigDecimal(0D);
						
			tblInversionesPlanesMedio = (DefaultTableModel)getTblInversionesPlanMedios().getModel();
						
			//T-MEDIOS/MEDIOS/CLIENTES/PRODUCTOS, nada esta seleccionado (totales por Medio)
			if ( mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					!getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected()  &&
					!getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					getCbMostrarTipoMedio().isSelected() && getCbMostrarMedio().isSelected() && 
					getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() && 
					!getCbMostrarDerechoPrograma().isSelected()){
									
				Iterator mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
				Map<String,BigDecimal> mesesTotales = new HashMap<String,BigDecimal>();
				ClienteOficinaIf medioOficinaTempIf = null;
									
				while (mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
					Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
					TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
									
					Map mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
					Iterator mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
									
					while (mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idMedio = (Long) mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						ClienteIf medioTempIf = mapaCliente.get(idMedio);										
						
						//mesesTotales = inicializarMapMesesValor();
						
						Map mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
						Iterator mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
						numeroMedioOficina = mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
												
						while (mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idMedioOficina = (Long) mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
																					
							Map mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
							Iterator mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
														
							while (mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idCliente = (Long) mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								ClienteIf clienteTempIf = mapaCliente.get(idCliente);								
								
								mesesTotales = inicializarMapMesesValor();
																
								Map mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
								Iterator mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
									
								while (mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idClienteOficina = (Long) mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
									ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
									
									Map mapMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
									Iterator mapMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
									
									while (mapMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idMarcaProducto = (Long) mapMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
																															
										Map mapProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
										Iterator mapProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
									
										while (mapProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
											Long idProductoCliente = (Long) mapProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
																							
											Map mapCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
											Iterator mapCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											while (mapCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idCampana = (Long) mapCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												//CampanaIf campanaTempIf = SessionServiceLocator.getCampanaSessionService().getCampana(idCampana);
																																			
												Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCampana);
												Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
												while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																						
													Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
													Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																					
													while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
														Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
															
														Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
														Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
														
														while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
															mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
															valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
															
															valorPautaMes = mesesTotales.get(mes);
															valorPautaMes = valorPautaMes.add(valorMes);
															
															mesesTotales.put(mes, valorPautaMes);
														}
													}													
												}												
											}		
										}
									}
								}
								
								Vector<String> fila = new Vector<String>();
								fila.add(medioTempIf.getNombreLegal());
								
								BigDecimal valor = new BigDecimal(0D);
								
								InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
								
								inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
								inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
								
								if (numeroMedioOficina > 1){
									inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
									inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
								}
															
								inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
								inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
								
								for (String mesTotal : mesesTotales.keySet()){	
									valorMes = mesesTotales.get(mesTotal);
									agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
									valor = valor.add(valorMes);
								}
								
								fila.add(formatoDecimal.format(valor).toString());
								inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
									
								tblInversionesPlanesMedio.addRow(fila);
								inversionColeccion.add(inversionClienteMesData);
							}
						}					
					}										
				}
			}
			
			//T-MEDIOS/MEDIOS/CLIENTES/PRODUCTOS, esta seleccionado Cliente Oficina
			else if ( mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected()  &&
					!getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					getCbMostrarTipoMedio().isSelected() && getCbMostrarMedio().isSelected() && 
					getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() && 
					!getCbMostrarDerechoPrograma().isSelected()){
									
				Iterator mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
				Map<String,BigDecimal> mesesTotales = new HashMap<String,BigDecimal>();
				ClienteOficinaIf medioOficinaTempIf = null;
									
				while (mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
					Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
					TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
									
					Map mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
					Iterator mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
									
					while (mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idMedio = (Long) mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						ClienteIf medioTempIf = mapaCliente.get(idMedio);										
						
						Map mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
						Iterator mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
						numeroMedioOficina = mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
												
						while (mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idMedioOficina = (Long) mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
																					
							Map mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
							Iterator mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
														
							while (mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idCliente = (Long) mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								ClienteIf clienteTempIf = mapaCliente.get(idCliente);								
								
								Map mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
								Iterator mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
									
								while (mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idClienteOficina = (Long) mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
									ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
									
									Map mapMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
									Iterator mapMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
									
									while (mapMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idMarcaProducto = (Long) mapMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
													
										mesesTotales = inicializarMapMesesValor();
																				
										Map mapProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
										Iterator mapProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
									
										while (mapProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
											Long idProductoCliente = (Long) mapProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
																							
											Map mapCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
											Iterator mapCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											while (mapCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idCampana = (Long) mapCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												//CampanaIf campanaTempIf = SessionServiceLocator.getCampanaSessionService().getCampana(idCampana);
																																			
												Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCampana);
												Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
												while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																						
													Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
													Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																					
													while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
														Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
															
														Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
														Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
														
														while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
															mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
															valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
															
															valorPautaMes = mesesTotales.get(mes);
															valorPautaMes = valorPautaMes.add(valorMes);
															
															mesesTotales.put(mes, valorPautaMes);
														}
													}													
												}												
											}		
										}
										
										Vector<String> fila = new Vector<String>();
										fila.add(medioTempIf.getNombreLegal());
										
										BigDecimal valor = new BigDecimal(0D);
										
										InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
										
										inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
										inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
										
										if (numeroMedioOficina > 1){
											inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
											inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
										}
																	
										inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
										inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
										
										inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
										inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
										
										for (String mesTotal : mesesTotales.keySet()){	
											valorMes = mesesTotales.get(mesTotal);
											agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
											valor = valor.add(valorMes);
										}
										
										fila.add(formatoDecimal.format(valor).toString());
										inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
											
										tblInversionesPlanesMedio.addRow(fila);
										inversionColeccion.add(inversionClienteMesData);
									}
									
							
								}								
							}						
						}					
					}										
				}
			}
			
		/*} catch (GenericBusinessException e) {
			e.printStackTrace();
		}*/
	}
	
	private void cargarTablaXClientesTMediosProducto2() {
		try {
			String mes = "";
			BigDecimal valorMes = new BigDecimal(0D);
			int numeroClienteOficina  = 0;
			int numeroProductoCliente = 0;
			int numeroMedioOficina = 0;
			BigDecimal valorPautaMes = new BigDecimal(0D);
						
			tblInversionesPlanesMedio = (DefaultTableModel)getTblInversionesPlanMedios().getModel();
						
			//PARA MOSTRAR CLIENTES/T-MEDIOS/MEDIOS/PRODUCTOS (se muestra todo)
			if ( mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
				 getCbMostrarClienteOficina().isSelected() &&
				 getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
				 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
				 getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
				
				Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
				
				while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
					Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfTipoMedioMedioMedioOfMarcaProductoIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
					Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
					numeroClienteOficina = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
					
					while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
						Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
						Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
						while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
							
							Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
							Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
													
							numeroProductoCliente = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
							
							while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
										
								Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
								Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
										
								while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
									TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
											
									Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
									Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
									
									while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										ClienteIf medioTempIf = mapaCliente.get(idMedio);
												
										Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
										Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
										numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
										
										while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
											Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
													
											Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
											Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
											while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																										
												Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
												Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																					
												while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
													DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
													
													Vector<String> fila = new Vector<String>();
													fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre() + " / " + medioTempIf.getNombreLegal());
														
													Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
													Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
													BigDecimal valor = new BigDecimal(0D);
														
													InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
													
													if (numeroClienteOficina > 1){ //CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL CLIENTE OFCINA SIN PONER AL CLIENTE
														inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
														inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
													}
													
													if (getCbMostrarMarca().isSelected()){	//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL PRODUCTO SIN PONER A LA MARCA
														inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
														inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
													}
													
													if (numeroMedioOficina > 1){	//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL MEDIOOFICINA SIN PONER AL MEDIO
														inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
														inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
													}
													
													inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
													inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
													inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
													inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
													inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
													inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
													inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
													inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
													inversionClienteMesData.setTipoPauta(tipoPautaTemp);
													inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getId().toString());
													inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getNombre());
														
													while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
														mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
														valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
																					
														agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
														
														valor = valor.add(valorMes);															
													}
														
													fila.add(formatoDecimal.format(valor).toString());
													
													inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
														
													tblInversionesPlanesMedio.addRow(fila);
														
													inversionColeccion.add(inversionClienteMesData);
												}
											}
										}		
									}
								}
							}							
						}	
					}
				}
			}//CLIENTES/T-MEDIOS/MEDIOS/PRODUCTOS SE MUESTRA todo EXCEPTO EL DERECHO DE PROGRAMA
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && 
					 getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   && 
					 getCbMostrarTipoPauta().isSelected() && !getCbMostrarDerechoPrograma().isSelected()){
													
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					//ADD 17 AGOSTO
					Map<String,BigDecimal> mesesTotales;
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
						//ADD 17 AGOSTO
						numeroClienteOficina = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
					
						//ADD 16 AGOSTO
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
							
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
													
								//ADD 17 AGOSTO
								numeroProductoCliente = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
							
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
									ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
										
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
										
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
											
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
									
										while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
											Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											ClienteIf medioTempIf = mapaCliente.get(idMedio);
												
											Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
											Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											//ADD 17 AGOSTO
											numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
										
											while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
													
												Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
												Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
												while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
													
													Vector<String> fila = new Vector<String>();
													fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre() + " / " + medioTempIf.getNombreLegal());
													BigDecimal valor = new BigDecimal(0D);
													
													//ADD 4 AGOSTO
													InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
													
													//ADD 17 AGOSTO
													mesesTotales = inicializarMapMesesValor();
																										
													if (numeroClienteOficina > 1){ //CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL CLIENTE OFICINA SIN EL CLIENTE
														inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
														inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
													}
													if (getCbMostrarMarca().isSelected()){	//CASO CONTRARIO SE MUESTRA DIRECTAMENTE LA MARCA SIN EL PRODUCTO
														inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
														inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
													}
													if (numeroMedioOficina > 1){ //CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL MEDIO OFICINA SIN EL MEDIO
														inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
														inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
													}
													//END 17 AGOSTO
													
													inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
													inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
													inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
													inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
													inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
													inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
													inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
													inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
													inversionClienteMesData.setTipoPauta(tipoPautaTemp);
																																							
													Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
													Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																					
													while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
														Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
														DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);	
														
														Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
														Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
														
														while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
															mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
															valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
															
															valorPautaMes = mesesTotales.get(mes);
															valorPautaMes = valorPautaMes.add(valorMes);
															mesesTotales.put(mes, valorPautaMes);
														}
													}
													
													//ADD 17 AGOSTO
													for (String mesTotal : mesesTotales.keySet()){
														valorMes = mesesTotales.get(mesTotal);
														agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
														valor = valor.add(valorMes);
													}
													
													fila.add(formatoDecimal.format(valor).toString());
													inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
														
													tblInversionesPlanesMedio.addRow(fila);
													inversionColeccion.add(inversionClienteMesData);
												}
											}		
										}
									}
								}							
							}	
						}
					}
			}//ADD 18 AGOSTO PARA MOSTRAR CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS SE MUESTRA TODO EXCEPTO EL TIPO DE PAUTA CON DERECHO DE PROGRAMA
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && 
					 getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   && 
					 !getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
									
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					//ADD 17 AGOSTO
					Map<String,BigDecimal> mesesTotales;
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
						//ADD 17 AGOSTO
						numeroClienteOficina = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
					
						//ADD 16 AGOSTO
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
							
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
													
								//ADD 17 AGOSTO
								numeroProductoCliente = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
							
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
									ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
										
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
										
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
											
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
									
										while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
											Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											ClienteIf medioTempIf = mapaCliente.get(idMedio);
												
											Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
											Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											//ADD 17 AGOSTO
											numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
										
											while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
												
												//ADD 18 AGOSTO
												Map<Long,Map<String,BigDecimal>> mapaDerechoProgramaMesesTotales = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																								
												Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
												Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
												while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																						
													Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
													Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																					
													while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
														Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
														
														//MODIFIED 18 ADD 17 AGOSTO
														//mesesTotales = inicializarMapMesesValor();
														
														//ADD 18 AGOSTO
														if (!mapaDerechoProgramaMesesTotales.containsKey(idDerechoPrograma)){
															mesesTotales = inicializarMapMesesValor();
															mapaDerechoProgramaMesesTotales.put(idDerechoPrograma, mesesTotales);
														}else
															mesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);
														
														Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
														Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
														
														while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
															mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
															valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
															
															valorPautaMes = mesesTotales.get(mes);
															valorPautaMes = valorPautaMes.add(valorMes);
															
															mesesTotales.put(mes, valorPautaMes);
														}
														
														//ADD 18 AGOSTO
														mapaDerechoProgramaMesesTotales.remove(idDerechoPrograma);
														mapaDerechoProgramaMesesTotales.put(idDerechoPrograma, mesesTotales);
													}													
												}

												//ADD 17 AGOSTO
												for (Long idDerechoPrograma : mapaDerechoProgramaMesesTotales.keySet()){
													DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
													
													//ADD 18 AGOSTO
													Vector<String> fila = new Vector<String>();
													fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre() + " / " + medioTempIf.getNombreLegal());
													BigDecimal valor = new BigDecimal(0D);
													
													//ADD 4 AGOSTO
													InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
													
													//ADD 17 AGOSTO
													if (numeroClienteOficina > 1){
														inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
														inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
													}
													if (getCbMostrarMarca().isSelected()){
														inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
														inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
													}
													if (numeroMedioOficina > 1){
														inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
														inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
													}
													//END 17 AGOSTO
													
													inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
													inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
													inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
													inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
													inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
													inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
													inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
													inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
													
													inversionClienteMesData.setDerechoProgramaId(derechoProgramaTempIf.getId().toString());
													inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getNombre());
													
													Map<String,BigDecimal> mapMesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);
													
													for (String mesTotal : mapMesesTotales.keySet()){	
														valorMes = mapMesesTotales.get(mesTotal);
														agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
														valor = valor.add(valorMes);
													}
													
													fila.add(formatoDecimal.format(valor).toString());
													inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
														
													tblInversionesPlanesMedio.addRow(fila);
													inversionColeccion.add(inversionClienteMesData);
												}
											}		
										}
									}
								}							
							}	
						}
					}
			}//ADD 18 AGOSTO PARA MOSTRAR CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS SE MUESTRA TODO EXCEPTO EL TIPO DE PAUTA Y DERECHO DE PROGRAMA
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && 
					 getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   && 
					 !getCbMostrarTipoPauta().isSelected() && !getCbMostrarDerechoPrograma().isSelected()){
									
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					//ADD 17 AGOSTO
					Map<String,BigDecimal> mesesTotales;
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
						//ADD 17 AGOSTO
						numeroClienteOficina = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
					
						//ADD 16 AGOSTO
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
							
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
													
								//ADD 17 AGOSTO
								numeroProductoCliente = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
							
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
									ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
										
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
										
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
											
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
									
										while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
											Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											ClienteIf medioTempIf = mapaCliente.get(idMedio);
											
											//ADD 18 AGOSTO
											mesesTotales = inicializarMapMesesValor();
												
											Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
											Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											//ADD 17 AGOSTO
											numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
										
											while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
												
												//ADD 18 AGOSTO
												//Map<Long,Map<String,BigDecimal>> mapaDerechoProgramaMesesTotales = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																								
												Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
												Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
												while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																						
													Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
													Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																					
													while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
														Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
														
														//MODIFIED 18 ADD 17 AGOSTO
														//mesesTotales = inicializarMapMesesValor();
														
														//ADD 18 AGOSTO
														/*if (!mapaDerechoProgramaMesesTotales.containsKey(idDerechoPrograma)){
															mesesTotales = inicializarMapMesesValor();
															mapaDerechoProgramaMesesTotales.put(idDerechoPrograma, mesesTotales);
														}else
															mesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);*/
														
														Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
														Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
														
														while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
															mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
															valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
															
															valorPautaMes = mesesTotales.get(mes);
															valorPautaMes = valorPautaMes.add(valorMes);
															
															mesesTotales.put(mes, valorPautaMes);
														}
														
														//ADD 18 AGOSTO
														//mapaDerechoProgramaMesesTotales.remove(idDerechoPrograma);
														//mapaDerechoProgramaMesesTotales.put(idDerechoPrograma, mesesTotales);
													}													
												}

												//ADD 17 AGOSTO
												//for (Long idDerechoPrograma : mapaDerechoProgramaMesesTotales.keySet()){
												//	DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
													
													//ADD 18 AGOSTO
													Vector<String> fila = new Vector<String>();
													fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre() + " / " + medioTempIf.getNombreLegal());
													BigDecimal valor = new BigDecimal(0D);
													
													//ADD 4 AGOSTO
													InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
													
													//ADD 17 AGOSTO
													if (numeroClienteOficina > 1){
														inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
														inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
													}
													if ( getCbMostrarMarca().isSelected()){
														inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
														inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
													}
													if (numeroMedioOficina > 1){
														inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
														inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
													}
													//END 17 AGOSTO
													
													inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
													inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
													inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
													inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
													inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
													inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
													inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
													inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
													
													//Map<String,BigDecimal> mapMesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);
													
													for (String mesTotal : mesesTotales.keySet()){	
														valorMes = mesesTotales.get(mesTotal);
														agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
														valor = valor.add(valorMes);
													}
													
													fila.add(formatoDecimal.format(valor).toString());
													inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
														
													tblInversionesPlanesMedio.addRow(fila);
													inversionColeccion.add(inversionClienteMesData);
												//}
											}		
										}
									}
								}							
							}	
						}
					}
			}//ADD 18 AGOSTO PARA MOSTRAR CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS SE MUESTRA TODO EXCEPTO EL TIPO DE MEDIO,MEDIO,MEDIO OFICINA,  TIPO DE PAUTA Y DERECHO DE PROGRAMA
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && 
					 getCbMostrarProducto().isSelected() && !getCbMostrarTipoMedio().isSelected()   &&
					 !getCbMostrarMedio().isSelected() && !getCbMostrarMedioOficina().isSelected()   && 
					 !getCbMostrarTipoPauta().isSelected() && !getCbMostrarDerechoPrograma().isSelected()){
									
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					//ADD 17 AGOSTO
					Map<String,BigDecimal> mesesTotales;
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
						//ADD 17 AGOSTO
						numeroClienteOficina = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
					
						//ADD 16 AGOSTO
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
							
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
													
								//ADD 17 AGOSTO
								numeroProductoCliente = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
							
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
									ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
									
									//ADD 18 AGOSTO
									mesesTotales = inicializarMapMesesValor();
									
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
										
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
											
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
									
										while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
											Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											ClienteIf medioTempIf = mapaCliente.get(idMedio);
																							
											Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
											Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											//ADD 17 AGOSTO
											numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
										
											while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
												
												//ADD 18 AGOSTO
												//Map<Long,Map<String,BigDecimal>> mapaDerechoProgramaMesesTotales = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																								
												Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
												Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
												while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																						
													Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
													Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																					
													while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
														Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
															
														Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
														Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
														
														while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
															mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
															valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
															
															valorPautaMes = mesesTotales.get(mes);
															valorPautaMes = valorPautaMes.add(valorMes);
															
															mesesTotales.put(mes, valorPautaMes);
														}
													}													
												}												
											}		
										}
									}
									
									//ADD 18 AGOSTO
									//for (Long idDerechoPrograma : mapaDerechoProgramaMesesTotales.keySet()){
									//	DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
										
										//ADD 18 AGOSTO
										Vector<String> fila = new Vector<String>();
										fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre());// + " / " + medioTempIf.getNombreLegal());
										BigDecimal valor = new BigDecimal(0D);
										
										//ADD 4 AGOSTO
										InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
										
										//ADD 17 AGOSTO
										if (numeroClienteOficina > 1){
											inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
											inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
										}
										//COMENTED 19 AGOSTO
										//if (numeroProductoCliente > 1 && getCbMostrarMarca().isSelected()){
										if (getCbMostrarMarca().isSelected()){
											inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
											inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
										}
										/*if (numeroMedioOficina > 1){
											inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
											inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
										}*/
										//END 17 AGOSTO
										
										inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
										inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
										inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
										inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
										/*inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
										inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
										inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
										inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
										*/
										//Map<String,BigDecimal> mapMesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);
										
										for (String mesTotal : mesesTotales.keySet()){	
											valorMes = mesesTotales.get(mesTotal);
											agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
											valor = valor.add(valorMes);
										}
										
										fila.add(formatoDecimal.format(valor).toString());
										inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
											
										tblInversionesPlanesMedio.addRow(fila);
										inversionColeccion.add(inversionClienteMesData);
									//}
								}							
							}	
						}
					}
			}//ADD 18 AGOSTO PARA MOSTRAR CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS SE MUESTRA TODO EXCEPTO EL PRODUCTO TIPO DE MEDIO,MEDIO,MEDIO OFICINA,  TIPO DE PAUTA Y DERECHO DE PROGRAMA
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && getCbMostrarMarca().isSelected()   &&
					 !getCbMostrarProducto().isSelected() && !getCbMostrarTipoMedio().isSelected()   &&
					 !getCbMostrarMedio().isSelected() && !getCbMostrarMedioOficina().isSelected()   && 
					 !getCbMostrarTipoPauta().isSelected() && !getCbMostrarDerechoPrograma().isSelected()){
									
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					//ADD 17 AGOSTO
					Map<String,BigDecimal> mesesTotales;
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
						//ADD 17 AGOSTO
						numeroClienteOficina = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
					
						//ADD 16 AGOSTO
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
							
								//ADD 18 AGOSTO
								mesesTotales = inicializarMapMesesValor();
								
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
													
								//ADD 17 AGOSTO
								numeroProductoCliente = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
							
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
									ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
									
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
										
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
											
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
									
										while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
											Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											ClienteIf medioTempIf = mapaCliente.get(idMedio);
																							
											Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
											Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											//ADD 17 AGOSTO
											numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
										
											while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
												
												//ADD 18 AGOSTO
												//Map<Long,Map<String,BigDecimal>> mapaDerechoProgramaMesesTotales = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																								
												Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
												Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
												while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																						
													Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
													Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																					
													while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
														Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
															
														Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
														Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
														
														while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
															mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
															valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
															
															valorPautaMes = mesesTotales.get(mes);
															valorPautaMes = valorPautaMes.add(valorMes);
															
															mesesTotales.put(mes, valorPautaMes);
														}
													}													
												}												
											}		
										}
									}
								}
								//ADD 18 AGOSTO
								//for (Long idDerechoPrograma : mapaDerechoProgramaMesesTotales.keySet()){
								//	DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
									
									//ADD 18 AGOSTO
									Vector<String> fila = new Vector<String>();
									fila.add(clienteTempIf.getNombreLegal());// + " / " + productoClienteTempIf.getNombre());// + " / " + medioTempIf.getNombreLegal());
									BigDecimal valor = new BigDecimal(0D);
									
									//ADD 4 AGOSTO
									InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
									
									//ADD 17 AGOSTO
									if (numeroClienteOficina > 1){
										inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
										inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
									}
									if (getCbMostrarMarca().isSelected()){
										inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
										inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
									}
									/*if (numeroMedioOficina > 1){
										inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
										inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
									}*/
									//END 17 AGOSTO
									
									inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
									inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
									/*inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
									inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
									inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
									inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
									inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
									inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
									*/
									//Map<String,BigDecimal> mapMesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);
									
									for (String mesTotal : mesesTotales.keySet()){	
										valorMes = mesesTotales.get(mesTotal);
										agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
										valor = valor.add(valorMes);
									}
									
									fila.add(formatoDecimal.format(valor).toString());
									inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
										
									tblInversionesPlanesMedio.addRow(fila);
									inversionColeccion.add(inversionClienteMesData);
								//}
							}	
						}
					}
			}//ADD 19 AGOSTO PARA MOSTRAR CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS SE MUESTRA TODO EXCEPTO LA MARCA,PRODUCTO TIPO DE MEDIO,MEDIO,MEDIO OFICINA,  TIPO DE PAUTA Y DERECHO DE PROGRAMA
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected()   &&
					 !getCbMostrarProducto().isSelected() && !getCbMostrarTipoMedio().isSelected()   &&
					 !getCbMostrarMedio().isSelected() && !getCbMostrarMedioOficina().isSelected()   && 
					 !getCbMostrarTipoPauta().isSelected() && !getCbMostrarDerechoPrograma().isSelected()){
									
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
					Map<String,BigDecimal> mesesTotales;
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
						numeroClienteOficina = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
					
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
							mesesTotales = inicializarMapMesesValor();
							
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
															
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
								
								numeroProductoCliente = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
							
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
									ProductoClienteIf productoClienteTempIf = mapaProductoCliente.get(idProductoCliente);
									
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
										
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
										TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
											
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
									
										while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
											Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
											ClienteIf medioTempIf = mapaCliente.get(idMedio);
																							
											Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
											Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
										
											while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
																							
												Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
												Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
												while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
													String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																						
													Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
													Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																					
													while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
														Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
															
														Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
														Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
														
														while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
															mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
															valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
															
															valorPautaMes = mesesTotales.get(mes);
															valorPautaMes = valorPautaMes.add(valorMes);
															
															mesesTotales.put(mes, valorPautaMes);
														}
													}													
												}												
											}		
										}
									}
								}								
							}								
							//ADD 19 AGOSTO
							//ADD 18 AGOSTO
							//for (Long idDerechoPrograma : mapaDerechoProgramaMesesTotales.keySet()){
							//	DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
								
								//ADD 18 AGOSTO
								Vector<String> fila = new Vector<String>();
								fila.add(clienteTempIf.getNombreLegal());// + " / " + productoClienteTempIf.getNombre());// + " / " + medioTempIf.getNombreLegal());
								BigDecimal valor = new BigDecimal(0D);
								
								//ADD 4 AGOSTO
								InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
								
								//ADD 17 AGOSTO
								if (numeroClienteOficina > 1){
									inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
									inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
								}
								/*if (numeroProductoCliente > 1 && getCbMostrarMarca().isSelected()){
									inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
									inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
								}*/
								/*if (numeroMedioOficina > 1){
									inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
									inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
								}*/
								//END 17 AGOSTO
								
								inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
								inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
								/*inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
								inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
								inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
								inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
								inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
								inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
								*/
								//Map<String,BigDecimal> mapMesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);
								
								for (String mesTotal : mesesTotales.keySet()){	
									valorMes = mesesTotales.get(mesTotal);
									agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
									valor = valor.add(valorMes);
								}
								
								fila.add(formatoDecimal.format(valor).toString());
								inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
									
								tblInversionesPlanesMedio.addRow(fila);
								inversionColeccion.add(inversionClienteMesData);
							//}
							//END 19 AGOSTO
						}
					}
			}//ADD 19 AGOSTO PARA MOSTRAR CLIENTES/T-MEDIOS/MEDIOS SE AGRUPA SIN PRODUCTO 
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && getCbMostrarMarca().isSelected() &&
					 !getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
					 getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
					// getCbMostrarMedio().isSelected() && getCbMostrarProducto().isSelected()){
				
					//ADD 19 AGOSTO
					Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>> mapClienteClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>>();
					Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>> mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
				    Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>> mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal; 
				    Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>> mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal; 
					Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>> mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>> mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<String,Map<Long,Map<String,BigDecimal>>> mapTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,BigDecimal>> mapDerechoProgramaIdMesesTotal;
					Map<String,BigDecimal> mapMesesTotal;
				
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												
						//ADD 19 AGOSTO
						mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>();
						
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
						//ADD 16 AGOSTO
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
														
							//ADD 19 AGOSTO
							mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>();
							
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
							
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								
								//ADD 19 AGOSTO
								mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>();
								
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																				
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
											
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																				
										//ADD 19 AGOSTO
										mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>();
																				
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										
										//ADD 19 AGOSTO
										if (!mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idTipoMedio))
											mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio, mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
										else{
											mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
																				
											Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																								
												//ADD 19 AGOSTO
												mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>();
													
												Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
												
												//ADD 19 AGOSTO
												if (!mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedio))
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio, mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
												else{
													mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
													
													Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
													//ADD 17 AGOSTO
													numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
											
														while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
															Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																													
															//ADD 19 AGOSTO
															mapTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<String,Map<Long,Map<String,BigDecimal>>>();
																														
															Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
															
															//ADD 19 AGOSTO
															if (!mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedioOficina))
																mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina, mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
															else{
																mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
																															
																Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
													
																while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																	String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																	
																	//ADD 19 AGOSTO
																	mapDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																	
																	Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
																	
																	//ADD 19 AGOSTO
																	if (!mapTipoPautaDerechoProgramaIdMesesTotal.containsKey(tipoPautaTemp))
																		mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp, mapDerechoProgramaIdMesesTotalOrdenesProducto);	
																	else{
																		mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
																			
																		Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																								
																		while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																			Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																						
																			//ADD 19 AGOSTO
																			mapMesesTotal = new LinkedHashMap<String,BigDecimal>();
																			
																			Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
																			
																			if (!mapDerechoProgramaIdMesesTotal.containsKey(idDerechoPrograma))
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma, mapMesesTotalOrdenesProductoAndCanal);	
																			else{
																				mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
																			
																				Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
																																					
																				BigDecimal valor = new BigDecimal(0D);
																			
																				while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
																					mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
																					valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
																												
																					//ADD 19 AGOSTO
																					valor = mapMesesTotal.get(mes);
																					valor = valor.add(valorMes);
																					mapMesesTotal.put(mes,valor);																
																				}
																				
																				//ADD 19 JULIO
																				mapDerechoProgramaIdMesesTotal.remove(idDerechoPrograma);
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma,mapMesesTotal);
																		} //END ELSE ADD 19 AGOSTO 
																	}//END WHILE DERECHO PROGRAMA
																	
																	//ADD 19 JULIO
																	mapTipoPautaDerechoProgramaIdMesesTotal.remove(tipoPautaTemp);
																	mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp,mapDerechoProgramaIdMesesTotal);
																		
																}//END ELSE ADD 19 AGOSTO
															}//END WHILE TIPO PAUTA
																
															//ADD 19 JULIO
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedioOficina);
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina,mapTipoPautaDerechoProgramaIdMesesTotal);
															
														}//END ELSE ADD 19 AGOSTO
													}//END WHILE MEDIO OFICINA
													
													//ADD 19 JULIO
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedio);
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio,mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
																												
											}//END ELSE ADD 19 AGOSTO
										}//END WHILE MEDIO
											
										//ADD 19 JULIO
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idTipoMedio);
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio,mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
											
									}//END ELSE ADD 19 AGOSTO
								}//END WHILE TIPO MEDIO
							}//END WHILE PRODUCTO 		
							mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMarcaProducto, mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
						}//END WHILE MARCA	
						mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.put(idClienteOficina, mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
					}//END WHILE CLIENTE OFICINA
					mapClienteClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idCliente, mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);
				}
				
				//ADD 22 AGOSTO
				Iterator mapClienteClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
				
				while(mapClienteClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
					mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idCliente);
					numeroClienteOficina = mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
					
					Iterator mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
					
					while(mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
						ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
						mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteOficinaMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idClienteOficina);
						Iterator mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
						
						while(mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
							Long idMarcaProducto = (Long) mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
							MarcaProductoIf marcaProductoTempIf = mapaMarcaProducto.get(idMarcaProducto);
							
							mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMarcaProducto);
							Iterator mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
							
							while(mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
								Long idTipoMedio = (Long) mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
								TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
								
								mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
								Iterator mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
								
								while(mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
									Long idMedio = (Long) mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
									ClienteIf medioTempIf = mapaCliente.get(idMedio);
									
									mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
									numeroMedioOficina = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
									
									Iterator mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();	
									
									while(mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
										Long idMedioOficina = (Long) mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
										ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
									
										mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
										Iterator mapTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
										
										while(mapTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
											String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalIt.next();
											
											mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
											Iterator mapDerechoProgramaIdMesesTotalIt = mapDerechoProgramaIdMesesTotal.keySet().iterator();
											
											while(mapDerechoProgramaIdMesesTotalIt.hasNext()){
												Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalIt.next();
												DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
												
												Vector<String> fila = new Vector<String>();
												fila.add(clienteTempIf.getNombreLegal() + " / "  + medioTempIf.getNombreLegal());
																																	
												BigDecimal valor = new BigDecimal(0D);
												
												InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
												
												if (numeroClienteOficina > 1){ //CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL CLIENTE OFCINA SIN PONER AL CLIENTE
													inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
													inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
												}												
												if (getCbMostrarMarca().isSelected()){	//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL PRODUCTO SIN PONER A LA MARCA
													inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
													inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
												}												
												if (numeroMedioOficina > 1){	//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL MEDIOOFICINA SIN PONER AL MEDIO
													inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
													inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
												}
																								
												inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
												inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
												inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
												inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
																																
												inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
												inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
												inversionClienteMesData.setTipoPauta(tipoPautaTemp);
												inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getId().toString());
												inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getNombre());
												
												mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
												Iterator mapMesesTotalIt = mapMesesTotal.keySet().iterator();
												
												while (mapMesesTotalIt.hasNext()){
													mes = (String) mapMesesTotalIt.next();
													valorMes = (BigDecimal) mapMesesTotal.get(mes);
																										
													agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
													valor = valor.add(valorMes);													
												}
												
												fila.add(formatoDecimal.format(valor).toString());
												inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
												tblInversionesPlanesMedio.addRow(fila);
												inversionColeccion.add(inversionClienteMesData);
											}
										}
									}
								}
							}
						}
					}						
				}
				//END 22 AGOSTO
			}//ADD 22 AGOSTO PARA MOSTRAR CLIENTES/T-MEDIOS/MEDIOS SE AGRUPA SIN MARCA Y PRODUCTO
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected() &&
					 !getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
					 getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
					// getCbMostrarMedio().isSelected() && getCbMostrarProducto().isSelected()){
				
					//ADD 19 AGOSTO
					Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>> mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>();
					Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>> mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
				    Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>> mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal; 
				    Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>> mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>> mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<String,Map<Long,Map<String,BigDecimal>>> mapTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,BigDecimal>> mapDerechoProgramaIdMesesTotal;
					Map<String,BigDecimal> mapMesesTotal;
				
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						
						mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>();
						
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							
							mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>();
							
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
							
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																				
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
											
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																				
										//ADD 19 AGOSTO
										mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>();
																				
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										
										//ADD 19 AGOSTO
										if (!mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idTipoMedio))
											mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio, mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
										else{
											mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
																				
											Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																								
												//ADD 19 AGOSTO
												mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>();
													
												Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
												
												//ADD 19 AGOSTO
												if (!mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedio))
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio, mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
												else{
													mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
													
													Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
													//ADD 17 AGOSTO
													numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
											
														while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
															Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																													
															//ADD 19 AGOSTO
															mapTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<String,Map<Long,Map<String,BigDecimal>>>();
																														
															Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
															
															//ADD 19 AGOSTO
															if (!mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedioOficina))
																mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina, mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
															else{
																mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
																															
																Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
													
																while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																	String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																	
																	//ADD 19 AGOSTO
																	mapDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																	
																	Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
																	
																	//ADD 19 AGOSTO
																	if (!mapTipoPautaDerechoProgramaIdMesesTotal.containsKey(tipoPautaTemp))
																		mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp, mapDerechoProgramaIdMesesTotalOrdenesProducto);	
																	else{
																		mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
																			
																		Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																								
																		while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																			Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																																						
																			//ADD 19 AGOSTO
																			mapMesesTotal = new LinkedHashMap<String,BigDecimal>();
																			
																			Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
																			
																			if (!mapDerechoProgramaIdMesesTotal.containsKey(idDerechoPrograma))
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma, mapMesesTotalOrdenesProductoAndCanal);	
																			else{
																				mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
																			
																				Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
																																					
																				BigDecimal valor = new BigDecimal(0D);
																			
																				while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
																					mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
																					valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
																												
																					//ADD 19 AGOSTO
																					valor = mapMesesTotal.get(mes);
																					valor = valor.add(valorMes);
																					mapMesesTotal.put(mes,valor);																
																				}
																				
																				//ADD 19 JULIO
																				mapDerechoProgramaIdMesesTotal.remove(idDerechoPrograma);
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma,mapMesesTotal);
																		} //END ELSE ADD 19 AGOSTO 
																	}//END WHILE DERECHO PROGRAMA
																	
																	//ADD 19 JULIO
																	mapTipoPautaDerechoProgramaIdMesesTotal.remove(tipoPautaTemp);
																	mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp,mapDerechoProgramaIdMesesTotal);
																		
																}//END ELSE ADD 19 AGOSTO
															}//END WHILE TIPO PAUTA
																
															//ADD 19 JULIO
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedioOficina);
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina,mapTipoPautaDerechoProgramaIdMesesTotal);
															
														}//END ELSE ADD 19 AGOSTO
													}//END WHILE MEDIO OFICINA
													
													//ADD 19 JULIO
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedio);
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio,mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
																												
											}//END ELSE ADD 19 AGOSTO
										}//END WHILE MEDIO
											
										//ADD 19 JULIO
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idTipoMedio);
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio,mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
											
									}//END ELSE ADD 19 AGOSTO
								}//END WHILE TIPO MEDIO
							}//END WHILE PRODUCTO 
								//COMENTED 22 AGOSTO
								//REVISARRRRRRRRRRRRRRRRRRRRR!!!!!!!!!!!!
							//mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMarcaProducto, mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
								
						}//END WHILE MARCA	
						mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idClienteOficina, mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
					}//END WHILE CLIENTE OFICINA
					mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idCliente, mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
				}
				
				//ADD 22 AGOSTO
				Iterator mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
				
				while(mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
					mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idCliente);
					numeroClienteOficina = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
					
					Iterator mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
					
					while(mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
						ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
						mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idClienteOficina);
						Iterator mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
						
						while(mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
							Long idTipoMedio = (Long) mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
							TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
							
							mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
							Iterator mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
								
							while(mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
								Long idMedio = (Long) mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
								ClienteIf medioTempIf = mapaCliente.get(idMedio);
									
								mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
								numeroMedioOficina = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
								
								Iterator mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();	
								
								while(mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
									Long idMedioOficina = (Long) mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
									ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
								
									mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
									Iterator mapTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
										
									while(mapTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
										String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalIt.next();
										
										mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
										Iterator mapDerechoProgramaIdMesesTotalIt = mapDerechoProgramaIdMesesTotal.keySet().iterator();
										
										while(mapDerechoProgramaIdMesesTotalIt.hasNext()){
											Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalIt.next();
											DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
											
											Vector<String> fila = new Vector<String>();
											fila.add(clienteTempIf.getNombreLegal() + " / "  + medioTempIf.getNombreLegal());
																																
											BigDecimal valor = new BigDecimal(0D);
											
											InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
												
											if (numeroClienteOficina > 1){ //CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL CLIENTE OFCINA SIN PONER AL CLIENTE
												inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
												inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
											}												
											/*if (getCbMostrarMarca().isSelected()){	//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL PRODUCTO SIN PONER A LA MARCA
												inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
												inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
											}*/												
											if (numeroMedioOficina > 1){	//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL MEDIOOFICINA SIN PONER AL MEDIO
												inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
												inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
											}
																								
											inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
											inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
											inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
											inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
																																
											inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
											inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
											inversionClienteMesData.setTipoPauta(tipoPautaTemp);
											inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getId().toString());
											inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getNombre());
												
											mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
											Iterator mapMesesTotalIt = mapMesesTotal.keySet().iterator();
												
											while (mapMesesTotalIt.hasNext()){
												mes = (String) mapMesesTotalIt.next();
												valorMes = (BigDecimal) mapMesesTotal.get(mes);
																								
												agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
												valor = valor.add(valorMes);													
											}
												
											fila.add(formatoDecimal.format(valor).toString());
											inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
											tblInversionesPlanesMedio.addRow(fila);
											inversionColeccion.add(inversionClienteMesData);
										}
									}
								}
							}
						}
					//}COMENTED 22 AGOSTO
					}						
				}
				//END 22 AGOSTO
			}//ADD 22 AGOSTO PARA MOSTRAR CLIENTES/T-MEDIOS/MEDIOS SE AGRUPA SIN MARCA Y PRODUCTO NO MUESTRA DERECHO PROGRAMA
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected() &&
					 !getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
					 getCbMostrarTipoPauta().isSelected() && !getCbMostrarDerechoPrograma().isSelected()){
					// getCbMostrarMedio().isSelected() && getCbMostrarProducto().isSelected()){
				
					Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>> mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>();
					Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>> mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
				    Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>> mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal; 
				    Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>> mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>> mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<String,Map<Long,Map<String,BigDecimal>>> mapTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,BigDecimal>> mapDerechoProgramaIdMesesTotal;
					Map<String,BigDecimal> mapMesesTotal;
					
					Map<String,BigDecimal> mesesTotales;
				
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						
						mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>();
						
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							
							mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>();
							
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
							
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																				
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
											
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												
										mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>();
																				
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										
										if (!mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idTipoMedio))
											mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio, mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
										else{
											mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
																				
											Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												
												mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>();
													
												Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
												
												if (!mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedio))
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio, mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
												else{
													mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
													
													Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
													numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
											
														while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
															Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
															
															mapTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<String,Map<Long,Map<String,BigDecimal>>>();
																														
															Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
															
															if (!mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedioOficina))
																mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina, mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
															else{
																mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
																															
																Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
													
																while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																	String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																	
																	mapDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																	
																	Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
																	
																	if (!mapTipoPautaDerechoProgramaIdMesesTotal.containsKey(tipoPautaTemp))
																		mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp, mapDerechoProgramaIdMesesTotalOrdenesProducto);	
																	else{
																		mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
																			
																		Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																								
																		while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																			Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																			
																			mapMesesTotal = new LinkedHashMap<String,BigDecimal>();
																			
																			Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
																			
																			if (!mapDerechoProgramaIdMesesTotal.containsKey(idDerechoPrograma))
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma, mapMesesTotalOrdenesProductoAndCanal);	
																			else{
																				mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
																			
																				Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
																																					
																				BigDecimal valor = new BigDecimal(0D);
																			
																				while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
																					mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
																					valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
																					
																					valor = mapMesesTotal.get(mes);
																					valor = valor.add(valorMes);
																					mapMesesTotal.put(mes,valor);																
																				}
																				
																				mapDerechoProgramaIdMesesTotal.remove(idDerechoPrograma);
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma,mapMesesTotal);
																		} //END ELSE ADD 19 AGOSTO 
																	}//END WHILE DERECHO PROGRAMA
																	
																	//ADD 19 JULIO
																	mapTipoPautaDerechoProgramaIdMesesTotal.remove(tipoPautaTemp);
																	mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp,mapDerechoProgramaIdMesesTotal);
																		
																}//END ELSE ADD 19 AGOSTO
															}//END WHILE TIPO PAUTA
																
															//ADD 19 JULIO
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedioOficina);
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina,mapTipoPautaDerechoProgramaIdMesesTotal);
															
														}//END ELSE ADD 19 AGOSTO
													}//END WHILE MEDIO OFICINA
													
													//ADD 19 JULIO
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedio);
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio,mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
																												
											}//END ELSE ADD 19 AGOSTO
										}//END WHILE MEDIO
											
										//ADD 19 JULIO
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idTipoMedio);
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio,mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
											
									}//END ELSE ADD 19 AGOSTO
								}//END WHILE TIPO MEDIO
							}//END WHILE PRODUCTO 
								//COMENTED 22 AGOSTO								
							//mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMarcaProducto, mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
								
						}//END WHILE MARCA	
						mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idClienteOficina, mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
					}//END WHILE CLIENTE OFICINA
					mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idCliente, mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
				}
				
				//ADD 22 AGOSTO
				Iterator mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
				
				while(mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
					mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idCliente);
					numeroClienteOficina = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
					
					Iterator mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
					
					while(mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
						ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
						mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idClienteOficina);
						Iterator mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
						
						while(mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
							Long idTipoMedio = (Long) mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
							TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
							
							mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
							Iterator mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
								
							while(mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
								Long idMedio = (Long) mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
								ClienteIf medioTempIf = mapaCliente.get(idMedio);
									
								mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
								numeroMedioOficina = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
								
								Iterator mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();	
								
								while(mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
									Long idMedioOficina = (Long) mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
									ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
								
									mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
									Iterator mapTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
										
									while(mapTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
										String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalIt.next();
										
										Vector<String> fila = new Vector<String>();
										fila.add(clienteTempIf.getNombreLegal() + " / "  + medioTempIf.getNombreLegal());
																															
										BigDecimal valor = new BigDecimal(0D);
										
										//ADD 22 AGOSTO
										mesesTotales = inicializarMapMesesValor();
										
										InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
											
										if (numeroClienteOficina > 1){ //CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL CLIENTE OFCINA SIN PONER AL CLIENTE
											inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
											inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
										}												
										/*if (getCbMostrarMarca().isSelected()){	//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL PRODUCTO SIN PONER A LA MARCA
											inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
											inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
										}*/												
										if (numeroMedioOficina > 1){	//CASO CONTRARIO SE MUESTRA DIRECTAMENTE EL MEDIOOFICINA SIN PONER AL MEDIO
											inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
											inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
										}
																							
										inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
										inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
										inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
										inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
																															
										inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
										inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
										inversionClienteMesData.setTipoPauta(tipoPautaTemp);
																				
										mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
										Iterator mapDerechoProgramaIdMesesTotalIt = mapDerechoProgramaIdMesesTotal.keySet().iterator();
										
										while(mapDerechoProgramaIdMesesTotalIt.hasNext()){
											Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalIt.next();
											DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
											
											mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
											Iterator mapMesesTotalIt = mapMesesTotal.keySet().iterator();
												
											while (mapMesesTotalIt.hasNext()){
												mes = (String) mapMesesTotalIt.next();
												valorMes = (BigDecimal) mapMesesTotal.get(mes);
												
												valorPautaMes = mesesTotales.get(mes);
												valorPautaMes = valorPautaMes.add(valorMes);
												mesesTotales.put(mes, valorPautaMes);																								
											}											
										}
										
										//ADD 22 AGOSTO
										for (String mesTotal : mesesTotales.keySet()){
											valorMes = mesesTotales.get(mesTotal);
											agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
											valor = valor.add(valorMes);
										}
																							
										fila.add(formatoDecimal.format(valor).toString());
										inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
																								
										tblInversionesPlanesMedio.addRow(fila);
										inversionColeccion.add(inversionClienteMesData);
									}
								}
							}
						}
					//}COMENTED 22 AGOSTO
					}						
				}
				//END 22 AGOSTO
			}//ADD 22 AGOSTO PARA MOSTRAR CLIENTES/T-MEDIOS/MEDIOS SE AGRUPA SIN MARCA Y PRODUCTO NO MUESTRA TIPO PAUTA PERO SI DERECHO PROGRAMA
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected() &&
					 !getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
					 !getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
					// getCbMostrarMedio().isSelected() && getCbMostrarProducto().isSelected()){
				
					Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>> mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>();
					Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>> mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
				    Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>> mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal; 
				    Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>> mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>> mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<String,Map<Long,Map<String,BigDecimal>>> mapTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,BigDecimal>> mapDerechoProgramaIdMesesTotal;
					Map<String,BigDecimal> mapMesesTotal;
					
					Map<String,BigDecimal> mesesTotales;
				
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						
						mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>();
						
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							
							mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>();
							
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
							
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																				
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
											
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												
										mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>();
																				
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										
										if (!mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idTipoMedio))
											mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio, mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
										else{
											mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
																				
											Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												
												mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>();
													
												Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
												
												if (!mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedio))
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio, mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
												else{
													mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
													
													Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
													numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
											
														while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
															Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
															
															mapTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<String,Map<Long,Map<String,BigDecimal>>>();
																														
															Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
															
															if (!mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedioOficina))
																mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina, mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
															else{
																mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
																															
																Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
													
																while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																	String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																	
																	mapDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																	
																	Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
																	
																	if (!mapTipoPautaDerechoProgramaIdMesesTotal.containsKey(tipoPautaTemp))
																		mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp, mapDerechoProgramaIdMesesTotalOrdenesProducto);	
																	else{
																		mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
																			
																		Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																								
																		while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																			Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																			
																			mapMesesTotal = new LinkedHashMap<String,BigDecimal>();
																			
																			Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
																			
																			if (!mapDerechoProgramaIdMesesTotal.containsKey(idDerechoPrograma))
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma, mapMesesTotalOrdenesProductoAndCanal);	
																			else{
																				mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
																			
																				Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
																																					
																				BigDecimal valor = new BigDecimal(0D);
																			
																				while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
																					mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
																					valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
																					
																					valor = mapMesesTotal.get(mes);
																					valor = valor.add(valorMes);
																					mapMesesTotal.put(mes,valor);																
																				}
																				
																				mapDerechoProgramaIdMesesTotal.remove(idDerechoPrograma);
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma,mapMesesTotal);
																		} //END ELSE ADD 19 AGOSTO 
																	}//END WHILE DERECHO PROGRAMA
																	
																	//ADD 19 JULIO
																	mapTipoPautaDerechoProgramaIdMesesTotal.remove(tipoPautaTemp);
																	mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp,mapDerechoProgramaIdMesesTotal);
																		
																}//END ELSE ADD 19 AGOSTO
															}//END WHILE TIPO PAUTA
																
															//ADD 19 JULIO
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedioOficina);
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina,mapTipoPautaDerechoProgramaIdMesesTotal);
															
														}//END ELSE ADD 19 AGOSTO
													}//END WHILE MEDIO OFICINA
													
													//ADD 19 JULIO
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedio);
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio,mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
																												
											}//END ELSE ADD 19 AGOSTO
										}//END WHILE MEDIO
											
										//ADD 19 JULIO
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idTipoMedio);
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio,mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
											
									}//END ELSE ADD 19 AGOSTO
								}//END WHILE TIPO MEDIO
							}//END WHILE PRODUCTO 
								//COMENTED 22 AGOSTO								
							//mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMarcaProducto, mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
								
						}//END WHILE MARCA	
						mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idClienteOficina, mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
					}//END WHILE CLIENTE OFICINA
					mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idCliente, mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
				}
				
				//ADD 22 AGOSTO
				Iterator mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
				
				while(mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
					mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idCliente);
					numeroClienteOficina = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
					
					Iterator mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
					
					while(mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
						ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
						mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idClienteOficina);
						Iterator mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
						
						while(mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
							Long idTipoMedio = (Long) mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
							TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
							
							mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
							Iterator mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
								
							while(mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
								Long idMedio = (Long) mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
								ClienteIf medioTempIf = mapaCliente.get(idMedio);
									
								mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
								numeroMedioOficina = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
								
								Iterator mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();	
								
								while(mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
									Long idMedioOficina = (Long) mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
									ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
								
									//ADD 22 AGOSTO
									Map<Long,Map<String,BigDecimal>> mapaDerechoProgramaMesesTotales = new LinkedHashMap<Long,Map<String,BigDecimal>>();
									
									mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
									Iterator mapTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
										
									while(mapTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
										String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalIt.next();
																				
										//ADD 22 AGOSTO
										mesesTotales = inicializarMapMesesValor();
																																								
										mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
										Iterator mapDerechoProgramaIdMesesTotalIt = mapDerechoProgramaIdMesesTotal.keySet().iterator();
										
										while(mapDerechoProgramaIdMesesTotalIt.hasNext()){
											Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalIt.next();
																					
											//ADD 22 AGOSTO
											if (!mapaDerechoProgramaMesesTotales.containsKey(idDerechoPrograma)){
												mesesTotales = inicializarMapMesesValor();
												mapaDerechoProgramaMesesTotales.put(idDerechoPrograma, mesesTotales);
											}else
												mesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);
											
											
											mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
											Iterator mapMesesTotalIt = mapMesesTotal.keySet().iterator();
												
											while (mapMesesTotalIt.hasNext()){
												mes = (String) mapMesesTotalIt.next();
												valorMes = (BigDecimal) mapMesesTotal.get(mes);
												
												valorPautaMes = mesesTotales.get(mes);
												valorPautaMes = valorPautaMes.add(valorMes);
												mesesTotales.put(mes, valorPautaMes);			
											}
											
											//ADD 22 AGOSTO
											mapaDerechoProgramaMesesTotales.remove(idDerechoPrograma);
											mapaDerechoProgramaMesesTotales.put(idDerechoPrograma, mesesTotales);
										}
										
										//ADD 22 AGOSTO
										/*for (String mesTotal : mesesTotales.keySet()){
											valorMes = mesesTotales.get(mesTotal);
											agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
											valor = valor.add(valorMes);
										}
																							
										fila.add(formatoDecimal.format(valor).toString());
										inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
																								
										tblInversionesPlanesMedio.addRow(fila);
										inversionColeccion.add(inversionClienteMesData);*/
									}
									
									//ADD 22 AGOSTO
									for (Long idDerechoPrograma : mapaDerechoProgramaMesesTotales.keySet()){
										DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
										
										Vector<String> fila = new Vector<String>();
										fila.add(clienteTempIf.getNombreLegal() + " / " + medioTempIf.getNombreLegal());
										BigDecimal valor = new BigDecimal(0D);
										
										InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
										
										if (numeroClienteOficina > 1){
											inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
											inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
										}
										/*if (getCbMostrarMarca().isSelected()){
											inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
											inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
										}*/
										if (numeroMedioOficina > 1){
											inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
											inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
										}
																				
										inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
										inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
										inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
										inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
										inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
										inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
										
										inversionClienteMesData.setDerechoProgramaId(derechoProgramaTempIf.getId().toString());
										inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getNombre());
										
										Map<String,BigDecimal> mapMesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);
										
										for (String mesTotal : mapMesesTotales.keySet()){	
											valorMes = mapMesesTotales.get(mesTotal);
											agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
											valor = valor.add(valorMes);
										}
										
										fila.add(formatoDecimal.format(valor).toString());
										inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
											
										tblInversionesPlanesMedio.addRow(fila);
										inversionColeccion.add(inversionClienteMesData);
									}
								}
							}
						}
					//}COMENTED 22 AGOSTO
					}						
				}
				//END 22 AGOSTO
			}
			//ADD 22 AGOSTO PARA MOSTRAR CLIENTES/T-MEDIOS/MEDIOS SE AGRUPA SIN MARCA Y PRODUCTO NO MUESTRA TIPO PAUTA NI DERECHO PROGRAMA
			else if ( mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
					 getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected() &&
					 !getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
					 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
					 !getCbMostrarTipoPauta().isSelected() && !getCbMostrarDerechoPrograma().isSelected()){
					// getCbMostrarMedio().isSelected() && getCbMostrarProducto().isSelected()){
				
					Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>> mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>>();
					Map<Long,Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>> mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
				    Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>> mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal; 
				    Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>> mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>> mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal;
					Map<String,Map<Long,Map<String,BigDecimal>>> mapTipoPautaDerechoProgramaIdMesesTotal;
					Map<Long,Map<String,BigDecimal>> mapDerechoProgramaIdMesesTotal;
					Map<String,BigDecimal> mapMesesTotal;
					
					Map<String,BigDecimal> mesesTotales;
				
					Iterator mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
					
					while (mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
						Long idCliente = (Long) mapClienteClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
						
						mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>>();
						
						Map mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idCliente);
						Iterator mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
						
						while (mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
							Long idClienteOficina = (Long) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
							
							mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>>();
							
							Map mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapClienteOfMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idClienteOficina);
							Iterator mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
							
							while (mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
								Long idMarcaProducto = (Long) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
								
								Map mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMarcaProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMarcaProducto);
								Iterator mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
																
								while (mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
									Long idProductoCliente = (Long) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																				
									Map mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapProductoTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idProductoCliente);
									Iterator mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 
											
									while (mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
										Long idTipoMedio = (Long) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												
										mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long, Map<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>>();
																				
										Map mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoMedioMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idTipoMedio);
										
										if (!mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idTipoMedio))
											mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio, mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
										else{
											mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
																				
											Iterator mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
										
											while (mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
												Long idMedio = (Long) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
												
												mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,Map<Long,Map<String,BigDecimal>>>>();
													
												Map mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedio);
												
												if (!mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedio))
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio, mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
												else{
													mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
													
													Iterator mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
											
													numeroMedioOficina = mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size();
											
														while (mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
															Long idMedioOficina = (Long) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
															
															mapTipoPautaDerechoProgramaIdMesesTotal = new LinkedHashMap<String,Map<Long,Map<String,BigDecimal>>>();
																														
															Map mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapMedioOfIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(idMedioOficina);
															
															if (!mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.containsKey(idMedioOficina))
																mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina, mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto);	
															else{
																mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
																															
																Iterator mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt = mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
													
																while (mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																	String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																	
																	mapDerechoProgramaIdMesesTotal = new LinkedHashMap<Long,Map<String,BigDecimal>>();
																	
																	Map mapDerechoProgramaIdMesesTotalOrdenesProducto = (Map) mapTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.get(tipoPautaTemp);
																	
																	if (!mapTipoPautaDerechoProgramaIdMesesTotal.containsKey(tipoPautaTemp))
																		mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp, mapDerechoProgramaIdMesesTotalOrdenesProducto);	
																	else{
																		mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
																			
																		Iterator mapDerechoProgramaIdMesesTotalOrdenesProductoIt = mapDerechoProgramaIdMesesTotalOrdenesProducto.keySet().iterator(); 	
																								
																		while (mapDerechoProgramaIdMesesTotalOrdenesProductoIt.hasNext()){
																			Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalOrdenesProductoIt.next();
																			
																			mapMesesTotal = new LinkedHashMap<String,BigDecimal>();
																			
																			Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapDerechoProgramaIdMesesTotalOrdenesProducto.get(idDerechoPrograma);
																			
																			if (!mapDerechoProgramaIdMesesTotal.containsKey(idDerechoPrograma))
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma, mapMesesTotalOrdenesProductoAndCanal);	
																			else{
																				mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
																			
																				Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
																																					
																				BigDecimal valor = new BigDecimal(0D);
																			
																				while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
																					mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
																					valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
																					
																					valor = mapMesesTotal.get(mes);
																					valor = valor.add(valorMes);
																					mapMesesTotal.put(mes,valor);																
																				}
																				
																				mapDerechoProgramaIdMesesTotal.remove(idDerechoPrograma);
																				mapDerechoProgramaIdMesesTotal.put(idDerechoPrograma,mapMesesTotal);
																		} //END ELSE ADD 19 AGOSTO 
																	}//END WHILE DERECHO PROGRAMA
																	
																	//ADD 19 JULIO
																	mapTipoPautaDerechoProgramaIdMesesTotal.remove(tipoPautaTemp);
																	mapTipoPautaDerechoProgramaIdMesesTotal.put(tipoPautaTemp,mapDerechoProgramaIdMesesTotal);
																		
																}//END ELSE ADD 19 AGOSTO
															}//END WHILE TIPO PAUTA
																
															//ADD 19 JULIO
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedioOficina);
															mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedioOficina,mapTipoPautaDerechoProgramaIdMesesTotal);
															
														}//END ELSE ADD 19 AGOSTO
													}//END WHILE MEDIO OFICINA
													
													//ADD 19 JULIO
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idMedio);
													mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMedio,mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
																												
											}//END ELSE ADD 19 AGOSTO
										}//END WHILE MEDIO
											
										//ADD 19 JULIO
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.remove(idTipoMedio);
										mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idTipoMedio,mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
											
									}//END ELSE ADD 19 AGOSTO
								}//END WHILE TIPO MEDIO
							}//END WHILE PRODUCTO 
								//COMENTED 22 AGOSTO								
							//mapMarcaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idMarcaProducto, mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
								
						}//END WHILE MARCA	
						mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idClienteOficina, mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
					}//END WHILE CLIENTE OFICINA
					mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.put(idCliente, mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal);
				}
				
				//ADD 22 AGOSTO
				Iterator mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
				
				while(mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
					Long idCliente = (Long) mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
					ClienteIf clienteTempIf = mapaCliente.get(idCliente);
					
					mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idCliente);
					numeroClienteOficina = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
					
					Iterator mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
					
					while(mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
						Long idClienteOficina = (Long) mapClienteOficinaTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
						ClienteOficinaIf clienteOficinaTempIf = mapaClienteOficina.get(idClienteOficina);
						
						mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapClienteOfTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idClienteOficina);
						Iterator mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator(); 
						
						while(mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
							Long idTipoMedio = (Long) mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
							TipoProveedorIf tipoProveedorTempIf = mapaTipoMedio.get(idTipoMedio);
							
							mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapTipoMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idTipoMedio);
							Iterator mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
								
							while(mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
								Long idMedio = (Long) mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
								ClienteIf medioTempIf = mapaCliente.get(idMedio);
								
								//ADD 22 AGOSTO
								mesesTotales = inicializarMapMesesValor();
									
								mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal = mapMedioMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedio);
								numeroMedioOficina = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.size();
								
								Iterator mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();	
								
								while(mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
									Long idMedioOficina = (Long) mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotalIt.next();
									ClienteOficinaIf medioOficinaTempIf = mapaClienteOficina.get(idMedioOficina);
																	
									//ADD 22 AGOSTO
									//Map<Long,Map<String,BigDecimal>> mapaDerechoProgramaMesesTotales = new LinkedHashMap<Long,Map<String,BigDecimal>>();
									
									mapTipoPautaDerechoProgramaIdMesesTotal = mapMedioOficinaTipoPautaDerechoProgramaIdMesesTotal.get(idMedioOficina);
									Iterator mapTipoPautaDerechoProgramaIdMesesTotalIt = mapTipoPautaDerechoProgramaIdMesesTotal.keySet().iterator();
										
									while(mapTipoPautaDerechoProgramaIdMesesTotalIt.hasNext()){
										String tipoPautaTemp = (String) mapTipoPautaDerechoProgramaIdMesesTotalIt.next();
																																								
										mapDerechoProgramaIdMesesTotal = mapTipoPautaDerechoProgramaIdMesesTotal.get(tipoPautaTemp);
										Iterator mapDerechoProgramaIdMesesTotalIt = mapDerechoProgramaIdMesesTotal.keySet().iterator();
										
										while(mapDerechoProgramaIdMesesTotalIt.hasNext()){
											Long idDerechoPrograma = (Long) mapDerechoProgramaIdMesesTotalIt.next();
																					
											//ADD 22 AGOSTO
											/*if (!mapaDerechoProgramaMesesTotales.containsKey(idDerechoPrograma)){
												mesesTotales = inicializarMapMesesValor();
												mapaDerechoProgramaMesesTotales.put(idDerechoPrograma, mesesTotales);
											}else
												mesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);
											*/
											
											mapMesesTotal = mapDerechoProgramaIdMesesTotal.get(idDerechoPrograma);
											Iterator mapMesesTotalIt = mapMesesTotal.keySet().iterator();
												
											while (mapMesesTotalIt.hasNext()){
												mes = (String) mapMesesTotalIt.next();
												valorMes = (BigDecimal) mapMesesTotal.get(mes);
												
												valorPautaMes = mesesTotales.get(mes);
												valorPautaMes = valorPautaMes.add(valorMes);
												mesesTotales.put(mes, valorPautaMes);			
											}
											
											//ADD 22 AGOSTO
											//mapaDerechoProgramaMesesTotales.remove(idDerechoPrograma);
											//mapaDerechoProgramaMesesTotales.put(idDerechoPrograma, mesesTotales);
										}
										
										//ADD 22 AGOSTO
										/*for (String mesTotal : mesesTotales.keySet()){
											valorMes = mesesTotales.get(mesTotal);
											agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
											valor = valor.add(valorMes);
										}
																							
										fila.add(formatoDecimal.format(valor).toString());
										inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
																								
										tblInversionesPlanesMedio.addRow(fila);
										inversionColeccion.add(inversionClienteMesData);*/
									}
									
									//ADD 22 AGOSTO
									//for (Long idDerechoPrograma : mapaDerechoProgramaMesesTotales.keySet()){
									//	DerechoProgramaIf derechoProgramaTempIf = mapaDerechoPrograma.get(idDerechoPrograma);
										
										Vector<String> fila = new Vector<String>();
										fila.add(clienteTempIf.getNombreLegal() + " / " + medioTempIf.getNombreLegal());
										BigDecimal valor = new BigDecimal(0D);
										
										InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
										
										if (numeroClienteOficina > 1){
											inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
											inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
										}
										/*if (getCbMostrarMarca().isSelected()){
											inversionClienteMesData.setMarcaId(marcaProductoTempIf.getId().toString());
											inversionClienteMesData.setMarca(marcaProductoTempIf.getNombre());
										}*/
										if (numeroMedioOficina > 1){
											inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
											inversionClienteMesData.setMedioId(medioTempIf.getId().toString());
										}
																				
										inversionClienteMesData.setClienteOficinaId(clienteOficinaTempIf.getId().toString());
										inversionClienteMesData.setClienteOficina(clienteOficinaTempIf.getDescripcion());
										inversionClienteMesData.setTipoMedioId(tipoProveedorTempIf.getId().toString());
										inversionClienteMesData.setTipoMedio(tipoProveedorTempIf.getNombre());
										inversionClienteMesData.setMedioOficinaId(medioOficinaTempIf.getId().toString());
										inversionClienteMesData.setMedioOficina(medioOficinaTempIf.getDescripcion());
										
										//inversionClienteMesData.setDerechoProgramaId(derechoProgramaTempIf.getId().toString());
										//inversionClienteMesData.setDerechoPrograma(derechoProgramaTempIf.getNombre());
										
									//	Map<String,BigDecimal> mapMesesTotales = mapaDerechoProgramaMesesTotales.get(idDerechoPrograma);
										
										for (String mesTotal : mesesTotales.keySet()){	
											valorMes = mesesTotales.get(mesTotal);
											agregarColumnasTabla(mesTotal, valorMes, fila, inversionClienteMesData);
											valor = valor.add(valorMes);
										}
										
										fila.add(formatoDecimal.format(valor).toString());
										inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
											
										tblInversionesPlanesMedio.addRow(fila);
										inversionColeccion.add(inversionClienteMesData);
									//}
								}
							}
						}
					//}COMENTED 22 AGOSTO
					}						
				}
				//END 22 AGOSTO
			}				
			//ADD 9 AGOSTO PARA MOSTRAR CLIENTES/T-MEDIOS/MEDIOS
			else if (mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanal.size() > 0 &&
					 getCbMostrarMedio().isSelected() && !getCbMostrarProducto().isSelected()){
				
				Iterator mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
				
				while (mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idCliente = (Long) mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
					ClienteIf clienteTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idCliente);
					
					/*Map mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal = mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
					Iterator mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
					
					while (mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProductoCliente = (Long) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
						ProductoClienteIf productoClienteTempIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(idProductoCliente);
						*/
						Map mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
						Iterator mapMedioIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
						
						while (mapMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
							Long idMedio = (Long) mapMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
							ClienteIf medioTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idMedio);
							Vector<String> fila = new Vector<String>();
							fila.add(clienteTempIf.getNombreLegal() + " / " + " / " + medioTempIf.getNombreLegal());
							
							Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapMedioIdMesesTotalOrdenesProductoAndCanal.get(idMedio);
							Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
							BigDecimal valor = new BigDecimal(0D);
							
							//ADD 4 AGOSTO
							InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
							inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
							inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
							inversionClienteMesData.setRuc(clienteTempIf.getIdentificacion());
							//inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
							//inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
							inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
							
							while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
								mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
								valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
														
								//ADD 4 AGOSTO
								agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
							
								valor = valor.add(valorMes);
								
							}
							
							fila.add(formatoDecimal.format(valor).toString());
							//MODIFIED 5 AGOSTO
							//inversionClienteMesData.setTotal(valor.toString());
							inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
							
							tblInversionesPlanesMedio.addRow(fila);
							
							//ADD 4 AGOSTO
							inversionColeccion.add(inversionClienteMesData);
						}
					//}COMENTED 9 AGOSTO
				}
			} //ADD 9 AGOSTO PARA MOSTRAR CLIENTES/PRODUCTOS
			else if (mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.size() > 0 &&
					 !getCbMostrarMedio().isSelected() && getCbMostrarProducto().isSelected()){
				
				Iterator mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
				
				while (mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idCliente = (Long) mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanalIt.next();
					ClienteIf clienteTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idCliente);
					
					Map mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal = mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
					Iterator mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt = mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
					
					while (mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProductoCliente = (Long) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
						ProductoClienteIf productoClienteTempIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(idProductoCliente);
						
					/*	Map mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
						Iterator mapMedioIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
						
						while (mapMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
							Long idMedio = (Long) mapMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
							ClienteIf medioTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idMedio);*/
							Vector<String> fila = new Vector<String>();
							fila.add(clienteTempIf.getNombreLegal() + " / " + productoClienteTempIf.getNombre());
							
							Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.get(idProductoCliente);
							Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
							BigDecimal valor = new BigDecimal(0D);
							
							//ADD 4 AGOSTO
							InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
							inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
							inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
							inversionClienteMesData.setRuc(clienteTempIf.getIdentificacion());
							inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
							inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
							//inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
							
							while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
								mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
								valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
														
								//ADD 4 AGOSTO
								agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
							
								valor = valor.add(valorMes);
							}
							
							fila.add(formatoDecimal.format(valor).toString());
							//MODIFIED 5 AGOSTO
							//inversionClienteMesData.setTotal(valor.toString());
							inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
							
							tblInversionesPlanesMedio.addRow(fila);
							
							//ADD 4 AGOSTO
							inversionColeccion.add(inversionClienteMesData);
						//}COMENTED 9 AGOSTO
					}
				}
			} //ADD 9 AGOSTO PARA MOSTRAR CLIENTES
			else if (mapClientesIdMesesTotalOrdenesProductoAndCanal.size() > 0 &&
					!getCbMostrarMedio().isSelected() && !getCbMostrarProducto().isSelected()){
				
				Iterator mapClientesIdMesesTotalOrdenesProductoAndCanalIt = mapClientesIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
				
				while (mapClientesIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
					Long idCliente = (Long) mapClientesIdMesesTotalOrdenesProductoAndCanalIt.next();
					ClienteIf clienteTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idCliente);
					
					/*Map mapMesesTotalOrdenesProductoAndCanal = mapClientesIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
					Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator();*/ 
					
					/*while (mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
						Long idProductoCliente = (Long) mapProductosIdMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
						ProductoClienteIf productoClienteTempIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(idProductoCliente);
					*/	
					/*	Map mapMedioIdMesesTotalOrdenesProductoAndCanal = (Map) mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
						Iterator mapMedioIdMesesTotalOrdenesProductoAndCanalIt = mapMedioIdMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
						
						while (mapMedioIdMesesTotalOrdenesProductoAndCanalIt.hasNext()){
							Long idMedio = (Long) mapMedioIdMesesTotalOrdenesProductoAndCanalIt.next();
							ClienteIf medioTempIf = SessionServiceLocator.getClienteSessionService().getCliente(idMedio);*/
							Vector<String> fila = new Vector<String>();
							fila.add(clienteTempIf.getNombreLegal());// + " / " + productoClienteTempIf.getNombre());
							
							Map mapMesesTotalOrdenesProductoAndCanal = (Map) mapClientesIdMesesTotalOrdenesProductoAndCanal.get(idCliente);
							Iterator mapMesesTotalOrdenesProductoAndCanalIt = mapMesesTotalOrdenesProductoAndCanal.keySet().iterator(); 
							BigDecimal valor = new BigDecimal(0D);
							
							//ADD 4 AGOSTO
							InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
							inversionClienteMesData.setCliente(clienteTempIf.getNombreLegal());
							inversionClienteMesData.setClienteId(clienteTempIf.getId().toString());
							inversionClienteMesData.setRuc(clienteTempIf.getIdentificacion());
							//inversionClienteMesData.setProducto(productoClienteTempIf.getNombre());
							//inversionClienteMesData.setProductoId(productoClienteTempIf.getId().toString());
							//inversionClienteMesData.setMedio(medioTempIf.getNombreLegal());
							
							while (mapMesesTotalOrdenesProductoAndCanalIt.hasNext()){
								mes = (String) mapMesesTotalOrdenesProductoAndCanalIt.next();
								valorMes = (BigDecimal) mapMesesTotalOrdenesProductoAndCanal.get(mes);
														
								//ADD 4 AGOSTO
								agregarColumnasTabla(mes, valorMes, fila, inversionClienteMesData);
							
								valor = valor.add(valorMes);
								
							}
							
							fila.add(formatoDecimal.format(valor).toString());
							//MODIFIED 5 AGOSTO
							//inversionClienteMesData.setTotal(valor.toString());
							inversionClienteMesData.setTotal(Double.parseDouble(valor.toString()));
							
							tblInversionesPlanesMedio.addRow(fila);
							
							//ADD 4 AGOSTO
							inversionColeccion.add(inversionClienteMesData);
						//}COMENTED 9 AGOSTO
					//}COMENTED 9 AGOSTO
				}
			}			
			//ADD 9 AGOSTO
			System.out.println("fin que pasa");
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
		
	public boolean sonIgualesInversionClienteMesData(InversionClienteMesData inversionClienteMesData1, InversionClienteMesData inversionClienteMesData2){
		
		if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_PRODUCTO_TMEDIOS_MEDIOS) == 0){
			if(!getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					!getCbMostrarTipoMedio().isSelected() && !getCbMostrarMedio().isSelected() && 
					!getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId())){
					return true;
			}
			else if(getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					!getCbMostrarTipoMedio().isSelected() && !getCbMostrarMedio().isSelected() && 
					!getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getMarca().equals(inversionClienteMesData2.getMarca()) &&
					inversionClienteMesData1.getMarcaId().equals(inversionClienteMesData2.getMarcaId())){
					return true;
			}
			else if(!getCbMostrarMarca().isSelected() && getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					!getCbMostrarTipoMedio().isSelected() && !getCbMostrarMedio().isSelected() && 
					!getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getProducto().equals(inversionClienteMesData2.getProducto()) &&
					inversionClienteMesData1.getProductoId().equals(inversionClienteMesData2.getProductoId())){
					return true;
			}
			else if(getCbMostrarMarca().isSelected() && getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					!getCbMostrarTipoMedio().isSelected() && !getCbMostrarMedio().isSelected() && 
					!getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getProducto().equals(inversionClienteMesData2.getProducto()) &&
					inversionClienteMesData1.getProductoId().equals(inversionClienteMesData2.getProductoId()) &&
					inversionClienteMesData1.getMarca().equals(inversionClienteMesData2.getMarca()) &&
					inversionClienteMesData1.getMarcaId().equals(inversionClienteMesData2.getMarcaId())){
					return true;
			}
			else if(!getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected() && getCbMostrarCampana().isSelected() &&
					!getCbMostrarTipoMedio().isSelected() && !getCbMostrarMedio().isSelected() && 
					!getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getCampana().equals(inversionClienteMesData2.getCampana()) &&
					inversionClienteMesData1.getCampanaId().equals(inversionClienteMesData2.getCampanaId())){
					return true;
			}
			else if(!getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					getCbMostrarTipoMedio().isSelected() && !getCbMostrarMedio().isSelected() && 
					!getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getTipoMedio().equals(inversionClienteMesData2.getTipoMedio()) &&
					inversionClienteMesData1.getTipoMedioId().equals(inversionClienteMesData2.getTipoMedioId())){
					return true;
			}
			else if(!getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					!getCbMostrarTipoMedio().isSelected() && getCbMostrarMedio().isSelected() && 
					!getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getMedio().equals(inversionClienteMesData2.getMedio()) &&
					inversionClienteMesData1.getMedioId().equals(inversionClienteMesData2.getMedioId())){
					return true;
			}
			else if(!getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					!getCbMostrarTipoMedio().isSelected() && !getCbMostrarMedio().isSelected() && 
					getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getMedioOficina().equals(inversionClienteMesData2.getMedioOficina()) &&
					inversionClienteMesData1.getMedioOficinaId().equals(inversionClienteMesData2.getMedioOficinaId())){
					return true;
			}
			else if(!getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					getCbMostrarTipoMedio().isSelected() && getCbMostrarMedio().isSelected() && 
					getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getMedio().equals(inversionClienteMesData2.getMedio()) &&
					inversionClienteMesData1.getMedioId().equals(inversionClienteMesData2.getMedioId()) &&
					inversionClienteMesData1.getMedioOficina().equals(inversionClienteMesData2.getMedioOficina()) &&
					inversionClienteMesData1.getMedioOficinaId().equals(inversionClienteMesData2.getMedioOficinaId()) &&
					inversionClienteMesData1.getTipoMedio().equals(inversionClienteMesData2.getTipoMedio()) &&
					inversionClienteMesData1.getTipoMedioId().equals(inversionClienteMesData2.getTipoMedioId())){
					return true;
			}
			else if(!getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					getCbMostrarTipoMedio().isSelected() && !getCbMostrarMedio().isSelected() && 
					getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getMedioOficina().equals(inversionClienteMesData2.getMedioOficina()) &&
					inversionClienteMesData1.getMedioOficinaId().equals(inversionClienteMesData2.getMedioOficinaId()) &&
					inversionClienteMesData1.getTipoMedio().equals(inversionClienteMesData2.getTipoMedio()) &&
					inversionClienteMesData1.getTipoMedioId().equals(inversionClienteMesData2.getTipoMedioId())){
					return true;
			}
			else if(!getCbMostrarMarca().isSelected() && getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					getCbMostrarTipoMedio().isSelected() && getCbMostrarMedio().isSelected() && 
					getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getProducto().equals(inversionClienteMesData2.getProducto()) &&
					inversionClienteMesData1.getProductoId().equals(inversionClienteMesData2.getProductoId()) &&
					inversionClienteMesData1.getMedio().equals(inversionClienteMesData2.getMedio()) &&
					inversionClienteMesData1.getMedioId().equals(inversionClienteMesData2.getMedioId()) &&
					inversionClienteMesData1.getMedioOficina().equals(inversionClienteMesData2.getMedioOficina()) &&
					inversionClienteMesData1.getMedioOficinaId().equals(inversionClienteMesData2.getMedioOficinaId()) &&
					inversionClienteMesData1.getTipoMedio().equals(inversionClienteMesData2.getTipoMedio()) &&
					inversionClienteMesData1.getTipoMedioId().equals(inversionClienteMesData2.getTipoMedioId())){
					return true;
			}
			else if(!getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					getCbMostrarTipoMedio().isSelected() && getCbMostrarMedio().isSelected() && 
					getCbMostrarMedioOficina().isSelected() && getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getMedio().equals(inversionClienteMesData2.getMedio()) &&
					inversionClienteMesData1.getMedioId().equals(inversionClienteMesData2.getMedioId()) &&
					inversionClienteMesData1.getMedioOficina().equals(inversionClienteMesData2.getMedioOficina()) &&
					inversionClienteMesData1.getMedioOficinaId().equals(inversionClienteMesData2.getMedioOficinaId()) &&
					inversionClienteMesData1.getTipoMedio().equals(inversionClienteMesData2.getTipoMedio()) &&
					inversionClienteMesData1.getTipoMedioId().equals(inversionClienteMesData2.getTipoMedioId()) &&
					inversionClienteMesData1.getTipoPauta().equals(inversionClienteMesData2.getTipoPauta())){
					return true;
			}
			else if(!getCbMostrarMarca().isSelected() && getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					getCbMostrarTipoMedio().isSelected() && getCbMostrarMedio().isSelected() && 
					getCbMostrarMedioOficina().isSelected() && getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getProducto().equals(inversionClienteMesData2.getProducto()) &&
					inversionClienteMesData1.getProductoId().equals(inversionClienteMesData2.getProductoId()) &&
					inversionClienteMesData1.getMedio().equals(inversionClienteMesData2.getMedio()) &&
					inversionClienteMesData1.getMedioId().equals(inversionClienteMesData2.getMedioId()) &&
					inversionClienteMesData1.getMedioOficina().equals(inversionClienteMesData2.getMedioOficina()) &&
					inversionClienteMesData1.getMedioOficinaId().equals(inversionClienteMesData2.getMedioOficinaId()) &&
					inversionClienteMesData1.getTipoMedio().equals(inversionClienteMesData2.getTipoMedio()) &&
					inversionClienteMesData1.getTipoMedioId().equals(inversionClienteMesData2.getTipoMedioId()) &&
					inversionClienteMesData1.getTipoPauta().equals(inversionClienteMesData2.getTipoPauta())){
					return true;
			}
			else if(getCbMostrarMarca().isSelected() && getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					getCbMostrarTipoMedio().isSelected() && getCbMostrarMedio().isSelected() && 
					getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getMarca().equals(inversionClienteMesData2.getMarca()) &&
					inversionClienteMesData1.getMarcaId().equals(inversionClienteMesData2.getMarcaId()) &&
					inversionClienteMesData1.getProducto().equals(inversionClienteMesData2.getProducto()) &&
					inversionClienteMesData1.getProductoId().equals(inversionClienteMesData2.getProductoId()) &&
					inversionClienteMesData1.getMedio().equals(inversionClienteMesData2.getMedio()) &&
					inversionClienteMesData1.getMedioId().equals(inversionClienteMesData2.getMedioId()) &&
					inversionClienteMesData1.getMedioOficina().equals(inversionClienteMesData2.getMedioOficina()) &&
					inversionClienteMesData1.getMedioOficinaId().equals(inversionClienteMesData2.getMedioOficinaId()) &&
					inversionClienteMesData1.getTipoMedio().equals(inversionClienteMesData2.getTipoMedio()) &&
					inversionClienteMesData1.getTipoMedioId().equals(inversionClienteMesData2.getTipoMedioId())){
					return true;
			}
			else if(getCbMostrarMarca().isSelected() && getCbMostrarProducto().isSelected() && getCbMostrarCampana().isSelected() && 
					getCbMostrarTipoMedio().isSelected() && getCbMostrarMedio().isSelected() && 
					getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getMarca().equals(inversionClienteMesData2.getMarca()) &&
					inversionClienteMesData1.getMarcaId().equals(inversionClienteMesData2.getMarcaId()) &&
					inversionClienteMesData1.getProducto().equals(inversionClienteMesData2.getProducto()) &&
					inversionClienteMesData1.getProductoId().equals(inversionClienteMesData2.getProductoId()) &&
					inversionClienteMesData1.getCampana().equals(inversionClienteMesData2.getCampana()) &&
					inversionClienteMesData1.getCampanaId().equals(inversionClienteMesData2.getCampanaId()) &&
					inversionClienteMesData1.getMedio().equals(inversionClienteMesData2.getMedio()) &&
					inversionClienteMesData1.getMedioId().equals(inversionClienteMesData2.getMedioId()) &&
					inversionClienteMesData1.getMedioOficina().equals(inversionClienteMesData2.getMedioOficina()) &&
					inversionClienteMesData1.getMedioOficinaId().equals(inversionClienteMesData2.getMedioOficinaId()) &&
					inversionClienteMesData1.getTipoMedio().equals(inversionClienteMesData2.getTipoMedio()) &&
					inversionClienteMesData1.getTipoMedioId().equals(inversionClienteMesData2.getTipoMedioId())){
					return true;
			}
			else if(getCbMostrarMarca().isSelected() && getCbMostrarProducto().isSelected() && getCbMostrarCampana().isSelected() && 
					getCbMostrarTipoMedio().isSelected() && getCbMostrarMedio().isSelected() && 
					getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() &&
					inversionClienteMesData1.getCliente().equals(inversionClienteMesData2.getCliente()) &&
					inversionClienteMesData1.getClienteId().equals(inversionClienteMesData2.getClienteId()) &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getMarca().equals(inversionClienteMesData2.getMarca()) &&
					inversionClienteMesData1.getMarcaId().equals(inversionClienteMesData2.getMarcaId()) &&
					inversionClienteMesData1.getProducto().equals(inversionClienteMesData2.getProducto()) &&
					inversionClienteMesData1.getProductoId().equals(inversionClienteMesData2.getProductoId()) &&
					inversionClienteMesData1.getCampana().equals(inversionClienteMesData2.getCampana()) &&
					inversionClienteMesData1.getCampanaId().equals(inversionClienteMesData2.getCampanaId()) &&
					inversionClienteMesData1.getMedio().equals(inversionClienteMesData2.getMedio()) &&
					inversionClienteMesData1.getMedioId().equals(inversionClienteMesData2.getMedioId()) &&
					inversionClienteMesData1.getMedioOficina().equals(inversionClienteMesData2.getMedioOficina()) &&
					inversionClienteMesData1.getMedioOficinaId().equals(inversionClienteMesData2.getMedioOficinaId()) &&
					inversionClienteMesData1.getTipoMedio().equals(inversionClienteMesData2.getTipoMedio()) &&				
					inversionClienteMesData1.getTipoMedioId().equals(inversionClienteMesData2.getTipoMedioId()) &&
					inversionClienteMesData1.getTipoPauta().equals(inversionClienteMesData2.getTipoPauta())){
					return true;
			}
		} else if (agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO) == 0){
			if(!getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected() && !getCbMostrarClienteOficina().isSelected() &&
					//inversionClienteMesData1.getMedio().equals(inversionClienteMesData2.getMedio()) &&
					//inversionClienteMesData1.getMedioId().equals(inversionClienteMesData2.getMedioId()) &&
					inversionClienteMesData1.getMedioOficina().equals(inversionClienteMesData2.getMedioOficina()) &&
					inversionClienteMesData1.getMedioOficinaId().equals(inversionClienteMesData2.getMedioOficinaId()) &&
					inversionClienteMesData1.getTipoMedio().equals(inversionClienteMesData2.getTipoMedio()) &&
					inversionClienteMesData1.getTipoMedioId().equals(inversionClienteMesData2.getTipoMedioId())){
					return true;
			}
			
			else if(!getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
					getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected() && getCbMostrarClienteOficina().isSelected() &&
					inversionClienteMesData1.getClienteOficina().equals(inversionClienteMesData2.getClienteOficina()) &&
					inversionClienteMesData1.getClienteOficinaId().equals(inversionClienteMesData2.getClienteOficinaId()) &&
					inversionClienteMesData1.getMedioOficina().equals(inversionClienteMesData2.getMedioOficina()) &&
					inversionClienteMesData1.getMedioOficinaId().equals(inversionClienteMesData2.getMedioOficinaId()) &&
					inversionClienteMesData1.getTipoMedio().equals(inversionClienteMesData2.getTipoMedio()) &&
					inversionClienteMesData1.getTipoMedioId().equals(inversionClienteMesData2.getTipoMedioId())){
					return true;
			}
		}
		
		return false;
	}
	
	public boolean coleccionContieneInversionMesData(Vector<InversionClienteMesData> inversionColeccionAgregados, InversionClienteMesData inversionClienteMesData){
		
		if(inversionColeccionAgregados.contains(inversionClienteMesData)){
			return true;
		}
		
		return false;
	}
	
	Comparator<InversionClienteMesData> ordenadorInversionClientePorNombreClienteOficina = new Comparator<InversionClienteMesData>(){
		public int compare(InversionClienteMesData o1, InversionClienteMesData o2) {
			return o1.getClienteOficina().compareTo(o2.getClienteOficina());
		}		
	};
	
	Comparator<InversionClienteMesData> ordenadorInversionClientePorMarca = new Comparator<InversionClienteMesData>(){
		public int compare(InversionClienteMesData o1, InversionClienteMesData o2) {
			return o1.getMarca().compareTo(o2.getMarca());
		}		
	};
	
	Comparator<InversionClienteMesData> ordenadorInversionClientePorProducto = new Comparator<InversionClienteMesData>(){
		public int compare(InversionClienteMesData o1, InversionClienteMesData o2) {
			return o1.getProducto().compareTo(o2.getProducto());
		}		
	};
	
	Comparator<InversionClienteMesData> ordenadorInversionClientePorCampana = new Comparator<InversionClienteMesData>(){
		public int compare(InversionClienteMesData o1, InversionClienteMesData o2) {
			return o1.getCampana().compareTo(o2.getCampana());
		}		
	};
	
	Comparator<InversionClienteMesData> ordenadorInversionClientePorTipoMedio = new Comparator<InversionClienteMesData>(){
		public int compare(InversionClienteMesData o1, InversionClienteMesData o2) {
			return o1.getTipoMedio().compareTo(o2.getTipoMedio());
		}		
	};
	
	Comparator<InversionClienteMesData> ordenadorInversionClientePorNombreProveedor = new Comparator<InversionClienteMesData>(){
		public int compare(InversionClienteMesData o1, InversionClienteMesData o2) {
			//if(o1.getMedio() != null && o2.getMedio() != null){
			
			/*if(!o1.getMedio().equals("") && !o2.getMedio().equals("") && o1.getMedio().compareTo(o2.getMedio()) == 0){
				System.out.println("a");
			}*/
			
				return o1.getMedio().compareTo(o2.getMedio());
			//}
			//return 0;
		}		
	};
	
	Comparator<InversionClienteMesData> ordenadorInversionClientePorNombreProveedorOficina = new Comparator<InversionClienteMesData>(){
		public int compare(InversionClienteMesData o1, InversionClienteMesData o2) {
			return o1.getMedioOficina().compareTo(o2.getMedioOficina());
		}		
	};
	
	public void report(boolean datosConsultados){
		//Si no se ha solicitado ver solo los auspicios entonces aumento a la inversion los datos de Presupuesto
		if(datosConsultados && !(getCbMostrarTipoPauta().isSelected() 
				&& getCbAuspicio().isSelected() 
				&& !getCbPautaRegular().isSelected())){
			//Aumento a la coleccion de Inversión los datos que vienen de las Ordenes de Compra (Prensa, Revista, Vallas, etc.)
			//Primero debo obtener la coleccion que viene de las Ordenes Compra Detalle
			Vector<InversionClienteMesData> inversionColeccionOrdenesCompraDetalle = obtenerColeccionInversionOrdenesCompraDetalle();
			//Ahora debo sumar todos los valores iguales de la coleccion inversionColeccionOrdenesCompraDetalle
			Vector<InversionClienteMesData> inversionColeccionOrdenesCompraDetalleAgrupadas = obtenerColeccionInversionAgrupada(inversionColeccionOrdenesCompraDetalle);
			
			//Seteo vacios dependiendo el caso medio y medioId si solo existe una oficina para que no aparezca en el reporte
			limpiarMediosConUnaOficina(inversionColeccionOrdenesCompraDetalleAgrupadas);			
			
			//Siguiente agrego la coleccion agrupada de ordenes compra detalle a la coleccion que viene de plan de medios
			inversionColeccion.addAll(inversionColeccionOrdenesCompraDetalleAgrupadas);
			//Por ultimo vuelvo a agrupar la coleccion completa para sumar valores de medios y presupuesto. 
			inversionColeccion = obtenerColeccionInversionAgrupada(inversionColeccion);
		}
		
		report();
	}
	
	public void report() {
		try {		
			//Si existen datos genero el reporte de Inversión.
			if (inversionColeccion.size() > 0) {
				
				if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_PRODUCTO_TMEDIOS_MEDIOS) == 0){
					if(getCbMostrarMedioOficina().isSelected())
						Collections.sort((Vector)inversionColeccion,ordenadorInversionClientePorNombreProveedorOficina);
					if(getCbMostrarMedio().isSelected())
						Collections.sort((Vector)inversionColeccion,ordenadorInversionClientePorNombreProveedor);
					if(getCbMostrarTipoMedio().isSelected())
						Collections.sort((Vector)inversionColeccion,ordenadorInversionClientePorTipoMedio);
					if(getCbMostrarCampana().isSelected())
						Collections.sort((Vector)inversionColeccion,ordenadorInversionClientePorCampana);
					if(getCbMostrarProducto().isSelected())
						Collections.sort((Vector)inversionColeccion,ordenadorInversionClientePorProducto);
					if(getCbMostrarMarca().isSelected())
						Collections.sort((Vector)inversionColeccion,ordenadorInversionClientePorMarca);
					
					//siempre se debe ordenar al ultimo por cliente oficina
					Collections.sort((Vector)inversionColeccion,ordenadorInversionClientePorNombreClienteOficina);
				
				}else if(agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO) == 0){					
					if(getCbMostrarCampana().isSelected())
						Collections.sort((Vector)inversionColeccion,ordenadorInversionClientePorCampana);
					if(getCbMostrarProducto().isSelected())
						Collections.sort((Vector)inversionColeccion,ordenadorInversionClientePorProducto);
					if(getCbMostrarMarca().isSelected())
						Collections.sort((Vector)inversionColeccion,ordenadorInversionClientePorMarca);
					if(getCbMostrarClienteOficina().isSelected())
						Collections.sort((Vector)inversionColeccion,ordenadorInversionClientePorNombreClienteOficina);
					if(getCbMostrarMedioOficina().isSelected())
						Collections.sort((Vector)inversionColeccion,ordenadorInversionClientePorNombreProveedorOficina);
					if(getCbMostrarMedio().isSelected())
						Collections.sort((Vector)inversionColeccion,ordenadorInversionClientePorNombreProveedor);
					
					//siempre se debe ordenar al ultimo por tipo de medio
					Collections.sort((Vector)inversionColeccion,ordenadorInversionClientePorTipoMedio);
				}			
				
				String si = "Si";
				String no = "No";
				Object[] options = {si,no};
				String mensaje = "¿Desea generar el reporte de Inversión de Planes de Medio?";
				//int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				//if(opcion == JOptionPane.YES_OPTION) {
				
				String mensajeCabeceras = "¿Desea el reporte sin cabeceras?";
				boolean mensajeSinCabeceras = false;
				int opcion = JOptionPane.showOptionDialog(null, mensajeCabeceras, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if(opcion == JOptionPane.YES_OPTION) {
					mensajeSinCabeceras = true;
				}
					
					String fileName = "";
					if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_PRODUCTO_TMEDIOS_MEDIOS) == 0){
						
						//CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS, nada esta seleccionado (totales por Cliente)
						if ( inversionColeccion.size() > 0   &&
								getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected()  &&
								!getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
								!getCbMostrarTipoMedio().isSelected() && !getCbMostrarMedio().isSelected() && 
								!getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() && 
								!getCbMostrarDerechoPrograma().isSelected()){
							
							//fileName = "jaspers/medios/RPInversionesPlanesMedioXClienteProductoTMedioGeneralSinProductoSinMedioSinTipoPautaSinDerechoPrograma.jasper";
							if(mensajeSinCabeceras){
								fileName = "jaspers/medios/RPInversionesMedios_CPM_Cliente_EXCEL.jasper";
							}else{
								fileName = "jaspers/medios/RPInversionesMedios_CPM_Cliente.jasper";
							}
						}
						//CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS OFICINA, presentar Producto
						else if ( inversionColeccion.size() > 0   &&
								getCbMostrarClienteOficina().isSelected() && (getCbMostrarMarca().isSelected() ||
								getCbMostrarProducto().isSelected() || getCbMostrarCampana().isSelected()) && 
								!getCbMostrarTipoMedio().isSelected() && !getCbMostrarMedio().isSelected() && 
								!getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() && 
								!getCbMostrarDerechoPrograma().isSelected()){
							
							//fileName = "jaspers/medios/RPInversionesPlanesMedioXClienteProductoTMedioGeneralSinMedioSinTipoPautaSinDerechoPrograma.jasper";
							if(getCbMostrarMarca().isSelected() && !getCbMostrarProducto().isSelected()){
								if(mensajeSinCabeceras){
									fileName = "jaspers/medios/RPInversionesMedios_CPM_Marca_EXCEL.jasper";
								}else{
									fileName = "jaspers/medios/RPInversionesMedios_CPM_Marca.jasper";
								}
							}else if(getCbMostrarProducto().isSelected()){
								if(mensajeSinCabeceras){
									fileName = "jaspers/medios/RPInversionesMedios_CPM_Producto_EXCEL.jasper";
								}else{
									fileName = "jaspers/medios/RPInversionesMedios_CPM_Producto.jasper";
								}
							}else {
								if(mensajeSinCabeceras){
									fileName = "jaspers/medios/RPInversionesMedios_CPM_Campana_EXCEL.jasper";
								}else{
									fileName = "jaspers/medios/RPInversionesMedios_CPM_Campana.jasper";
								}
							}
						}
						//CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS OFICINA, presentar Campana
						else if ( inversionColeccion.size() > 0   &&
								getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected() &&
								!getCbMostrarProducto().isSelected() && getCbMostrarCampana().isSelected() && 
								!getCbMostrarTipoMedio().isSelected() && !getCbMostrarMedio().isSelected() && 
								!getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() && 
								!getCbMostrarDerechoPrograma().isSelected()){
							
							//fileName = "jaspers/medios/RPInversionesPlanesMedioXClienteProductoTMedioGeneralConMarcaSinProductoSinMedioSinTipoPautaSinDerechoPrograma.jasper";
							if(mensajeSinCabeceras){
								fileName = "jaspers/medios/RPInversionesMedios_CPM_Campana_EXCEL.jasper";
							}else{
								fileName = "jaspers/medios/RPInversionesMedios_CPM_Campana.jasper";
							}
						}				
						
						//CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS, presentar Tipo Medio, Medio y Medio Oficina
						else if ( inversionColeccion.size() > 0 && 
								getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected() &&
								!getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
								(getCbMostrarTipoMedio().isSelected() || getCbMostrarMedio().isSelected() || 
								getCbMostrarMedioOficina().isSelected()) && !getCbMostrarTipoPauta().isSelected() && 
								!getCbMostrarDerechoPrograma().isSelected()){
							
							//fileName = "jaspers/medios/RPInversionesPlanesMedioXClienteProductoTMedioClienteTMedioSinMarcaSinTipoPautaSinDerechoPrograma.jasper";
							if(getCbMostrarTipoMedio().isSelected() && !getCbMostrarMedio().isSelected() && !getCbMostrarMedioOficina().isSelected()){
								fileName = "jaspers/medios/RPInversionesMedios_CPM_TMedio_Medio_MedioOfi_EXCEL.jasper";								
							}else if(getCbMostrarTipoMedio().isSelected()){
								if(mensajeSinCabeceras){
									fileName = "jaspers/medios/RPInversionesMedios_CPM_TMedio_Medio_MedioOfi2.jasper";
								}else{
									fileName = "jaspers/medios/RPInversionesMedios_CPM_TMedio_Medio_MedioOfi.jasper";
								}								
							}else{
								if(mensajeSinCabeceras){
									fileName = "jaspers/medios/RPInversionesMedios_CPM_MedioOfi_EXCEL.jasper";
								}else{
									fileName = "jaspers/medios/RPInversionesMedios_CPM_MedioOfi.jasper";
								}								
							}
							
						}
						//CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS, presentar Marca (opcional), Producto, Tipo de Medio, Medio y Medio Oficina
						else if ( inversionColeccion.size() > 0   &&
								 getCbMostrarClienteOficina().isSelected() &&  getCbMostrarProducto().isSelected() && 
								 !getCbMostrarCampana().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
								 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   && 
								 !getCbMostrarTipoPauta().isSelected() && !getCbMostrarDerechoPrograma().isSelected()){
							
							//fileName = "jaspers/medios/RPInversionesPlanesMedioXClienteProductoTMedioGeneralSinTipoPautaSinDerechoPrograma.jasper";
							fileName = "jaspers/medios/RPInversionesMedios_CPM_Marca_Producto_TMedio_Medio_MedioOfi.jasper";
						}
						//CLIENTES/T-MEDIOS/MEDIOS, presentar Tipo Medio, Medio, Medio Oficina y Tipo Pauta
						else if ( inversionColeccion.size() > 0   &&
								 getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected() &&
								 !getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
								 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
								 getCbMostrarTipoPauta().isSelected() && !getCbMostrarDerechoPrograma().isSelected()){
							
							//fileName = "jaspers/medios/RPInversionesPlanesMedioXClienteProductoTMedioClienteTMedioSinMarcaSinDerechoPrograma.jasper";
							fileName = "jaspers/medios/RPInversionesMedios_CPM_TMedio_Medio_MedioOfi_TPauta.jasper";
						}
						
						
						// MOSTRAR CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS
						/*else if (getCbMostrarClienteOficina().isSelected() &&
							 getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
							 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
							 getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
							
							fileName = "jaspers/medios/RPInversionesPlanesMedioXClienteProductoTMedioGeneral.jasper";
						}							
						else if ( (mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0 || presupuestosDetalle.size() > 0) &&
								getCbMostrarClienteOficina().isSelected() && 
								getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
								getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   && 
								getCbMostrarTipoPauta().isSelected()){
							
							fileName = "jaspers/medios/RPInversionesPlanesMedioXClienteProductoTMedioGeneralConMarcaSinDereechoPrograma.jasper";
						}
						//PARA MOSTRAR CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS SE MUESTRA TODO EXCEPTO EL TIPO DE PAUTA CON DERECHO DE PROGRAMA
						else if ( (mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0 || presupuestosDetalle.size() > 0) &&
								 getCbMostrarClienteOficina().isSelected() && 
								 getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
								 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   && 
								 !getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
							
							fileName = "jaspers/medios/RPInversionesPlanesMedioXClienteProductoTMedioGeneralSinTipoPautaConDerechoPrograma.jasper";
						}						
						//PARA MOSTRAR CLIENTES/T-MEDIOS/MEDIOS SE MUESTRA TODO DEL FILTRO CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS
						else if ( (mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0 || presupuestosDetalle.size() > 0) &&
								 getCbMostrarClienteOficina().isSelected() && getCbMostrarMarca().isSelected() &&
								 !getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
								 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
								 getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
							
							fileName = "jaspers/medios/RPInversionesPlanesMedioXClienteProductoTMedioClienteTMedio.jasper";
						}
						//PARA MOSTRAR CLIENTES/T-MEDIOS/MEDIOS SE AGRUPA SIN MARCA Y PRODUCTO
						else if ( (mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0 || presupuestosDetalle.size() > 0) &&
								 getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected() &&
								 !getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
								 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
								 getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
							
							fileName = "jaspers/medios/RPInversionesPlanesMedioXClienteProductoTMedioClienteTMedioSinMarca.jasper";
						}						
						//PARA MOSTRAR CLIENTES/T-MEDIOS/MEDIOS SE AGRUPA SIN MARCA Y PRODUCTO NO MUESTRA TIPO PAUTA, DERECHO PROGRAMA
						else if ( (mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0 || presupuestosDetalle.size() > 0) &&
								 getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected() &&
								 !getCbMostrarProducto().isSelected() && getCbMostrarTipoMedio().isSelected()   &&
								 getCbMostrarMedio().isSelected() && getCbMostrarMedioOficina().isSelected()   &&
								 !getCbMostrarTipoPauta().isSelected() && getCbMostrarDerechoPrograma().isSelected()){
							
							fileName = "jaspers/medios/RPInversionesPlanesMedioXClienteProductoTMedioClienteTMedioSinMarcaSinTipoPauta.jasper";
						}						
						//MOSTRAR CLIENTES/T-MEDIOS/MEDIOS
						else if (getCbMostrarMedio().isSelected() && !getCbMostrarProducto().isSelected()){
							
							fileName = "jaspers/medios/RPInversionesPlanesMedioSinProducto.jasper";
						}
						//MOSTRAR CLIENTES/PRODUCTOS/
						else if (!getCbMostrarMedio().isSelected() && getCbMostrarProducto().isSelected()){
							
							fileName = "jaspers/medios/RPInversionesPlanesMedioSinMedio.jasper";
						}
						//MOSTRAR CLIENTES
						else if (!getCbMostrarMedio().isSelected() && !getCbMostrarProducto().isSelected()){
							
							fileName = "jaspers/medios/RPInversionesPlanesMedioSinProductoSinMedio.jasper";
						}		*/				
					}
					
					else if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO) == 0){
						
						// MOSTRAR CLIENTES/PRODUCTOS/T-MEDIOS/MEDIOS
						if (getCbMostrarMedio().isSelected() && getCbMostrarProducto().isSelected()){
							fileName = "jaspers/medios/RPInversionesPlanesMedioXClienteTMedioProducto.jasper";
						}						
						// MOSTRAR CLIENTES/T-MEDIOS/MEDIOS
						else if (getCbMostrarMedio().isSelected() && !getCbMostrarProducto().isSelected()){
							fileName = "jaspers/medios/RPInversionesPlanesMedioSinProducto.jasper";
						}						
						// MOSTRAR CLIENTES/PRODUCTOS/
						else if (!getCbMostrarMedio().isSelected() && getCbMostrarProducto().isSelected()){
							fileName = "jaspers/medios/RPInversionesPlanesMedioSinMedio.jasper";
						}						
						// MOSTRAR CLIENTES
						else if (!getCbMostrarMedio().isSelected() && !getCbMostrarProducto().isSelected()){
							fileName = "jaspers/medios/RPInversionesPlanesMedioSinProductoSinMedio.jasper";
						}
						
					}
					else if (agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO) == 0){
						
						//T-MEDIOS/MEDIOS/CLIENTES/PRODUCTOS, nada esta seleccionado (totales por Medio)
						if ( inversionColeccion.size() > 0   &&
								!getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected()  &&
								!getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
								getCbMostrarTipoMedio().isSelected() && getCbMostrarMedio().isSelected() && 
								getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() && 
								!getCbMostrarDerechoPrograma().isSelected()){
							
							if(mensajeSinCabeceras){
								fileName = "jaspers/medios/RPInversionesMedios_MCP_TMedio_Medio_MedioOfi_EXCEL.jasper";
							}else{
								fileName = "jaspers/medios/RPInversionesMedios_MCP_TMedio_Medio_MedioOfi.jasper";
							}
						}
						
						//T-MEDIOS/MEDIOS/CLIENTES/PRODUCTOS, esta seleccionado Cliente Oficina
						//else if ( mapTipoMedioMedioMedioOfiClienteClienteOfiMarcaProductoCampanaTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.size() > 0   &&
						else if ( inversionColeccion.size() > 0   &&
								getCbMostrarClienteOficina().isSelected() && !getCbMostrarMarca().isSelected()  &&
								!getCbMostrarProducto().isSelected() && !getCbMostrarCampana().isSelected() && 
								getCbMostrarTipoMedio().isSelected() && getCbMostrarMedio().isSelected() && 
								getCbMostrarMedioOficina().isSelected() && !getCbMostrarTipoPauta().isSelected() && 
								!getCbMostrarDerechoPrograma().isSelected()){
							
							if(mensajeSinCabeceras){
								fileName = "jaspers/medios/RPInversionesMedios_MCP_TMedio_Medio_MedioOfi_ClienteOfi_EXCEL.jasper";
							}else{
								fileName = "jaspers/medios/RPInversionesMedios_MCP_TMedio_Medio_MedioOfi_ClienteOfi.jasper";
							}
						}
						
						//fileName = "jaspers/medios/RPInversionesPlanesMedioXTMediosClienteProducto.jasper";
						// MOSTRAR T-MEDIOS/MEDIOS/CLIENTES/PRODUCTOS
						/*if (getCbMostrarCliente().isSelected() && getCbMostrarProducto().isSelected())
							fileName = "jaspers/medios/RPInversionesPlanesMedioXTMediosClienteProducto.jasper";
						// MOSTRAR T-MEDIOS/MEDIOS/CLIENTES
						else if (getCbMostrarCliente().isSelected() && !getCbMostrarProducto().isSelected())
							fileName = "jaspers/medios/RPInversionesPlanesMedioXTMediosClienteProductoSinProducto.jasper";
						// MOSTRAR T-MEDIOS/MEDIOS/PRODUCTOS
						else if (!getCbMostrarCliente().isSelected() && getCbMostrarProducto().isSelected())
							fileName = "jaspers/medios/RPInversionesPlanesMedioXTMediosClienteProductoSinCliente.jasper";
						// MOSTRAR MEDIOS
						else if (!getCbMostrarCliente().isSelected() && !getCbMostrarProducto().isSelected())
							fileName = "jaspers/medios/RPInversionesPlanesMedioXTMediosSinClienteSinProducto.jasper";*/
					}						
					//END MODIFIED 10 AGOSTO ADD 8 AGOSTO
										
					HashMap parametrosMap = new HashMap();
					
					MenuIf menu = null;
					Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_MEDIOS).iterator();
					if(menuIT.hasNext()) menu = (MenuIf)menuIT.next();
					
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre().substring(0,1).concat(ciudad.getNombre().substring(1, ciudad.getNombre().length()).toLowerCase()));
					parametrosMap.put("usuario", Parametros.getUsuario());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)).toLowerCase() + " del " + year + ".-";
					parametrosMap.put("emitido", fechaEmision);					
					Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
					Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
					
					if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_PRODUCTO_TMEDIOS_MEDIOS) == 0)
						filtro = NOMBRE_AGRUPAR_POR_CLIENTE_PRODUCTO_TMEDIOS_MEDIOS;
					else if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO) == 0)
						filtro = NOMBRE_AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO;
					else if (agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO) == 0)
						filtro = NOMBRE_AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO;
					
					//aumento al filtro el tipo de producto
					if(!getCmbTipoProducto().getSelectedItem().equals(TODOS)){
						String tipoProducto = ((TipoProductoIf)getCmbTipoProducto().getSelectedItem()).getNombre();
						filtro = filtro + " (" + tipoProducto + ")";
					}
					
					
					parametrosMap.put("fechaInicio", Utilitarios.getFechaCortaUppercase(fechaInicio));
					parametrosMap.put("fechaFin", Utilitarios.getFechaCortaUppercase(fechaFin));
					//parametrosMap.put("totalFacturacion", formatoDecimal.format(totalTotal));
					parametrosMap.put("filtro", filtro);
					
					if(!getCbClienteOficinaTodos().isSelected() && clienteOficinaIf != null){
						parametrosMap.put("clienteNombre", clienteOficinaIf.getDescripcion());
					}else if(!getCbClientesTodos().isSelected() && clienteIf != null){
						parametrosMap.put("clienteNombre", clienteIf.getNombreLegal());
					}else{
						parametrosMap.put("clienteNombre", "");
					}
					
					if(!getCmbOficinaEmpresa().getSelectedItem().equals("TODOS")){
						OficinaIf oficinaEmpresa = (OficinaIf)getCmbOficinaEmpresa().getSelectedItem();
						parametrosMap.put("oficinaEmpresa", oficinaEmpresa.getNombre());
					}else{
						parametrosMap.put("oficinaEmpresa", "");
					}
					
					if(getRbValorNeto().isSelected()){
						parametrosMap.put("tipoValor", "Valor Neto");
					}else{
						parametrosMap.put("tipoValor", "Valor Bruto");
					}
										
					if(fileName.equals("")){
						SpiritAlert.createAlert("No existe un reporte para este caso.", SpiritAlert.WARNING);
					}else{
						ReportModelImpl.processReport(fileName, parametrosMap, inversionColeccion, true);
					}
				//}
				
			} else{
				SpiritAlert.createAlert("No existen datos para imprimir.", SpiritAlert.WARNING);
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
		catch (ParseException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
	}
	//END 4 AGOSTO
	//END 16 AGOSTO

	private void limpiarMediosConUnaOficina(
			Vector<InversionClienteMesData> inversionColeccionOrdenesCompraDetalleAgrupadas) {
		if(agruparBy.compareTo(AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO) == 0){
			Map<String,Boolean> medioVariasOficinasMap = new HashMap<String,Boolean>();
			boolean variasOficinas = false;
			Map<String,String> medioOficinasMap = new HashMap<String,String>();
			
			//para lo que viene de presupuestos
			for(InversionClienteMesData inversionClienteMesData : inversionColeccionOrdenesCompraDetalleAgrupadas){
				if(medioOficinasMap.get(inversionClienteMesData.getMedioId()) == null){
					medioOficinasMap.put(inversionClienteMesData.getMedioId(), inversionClienteMesData.getMedioOficinaId());
				}else{
					Long idOficina = Long.valueOf(medioOficinasMap.get(inversionClienteMesData.getMedioId()));
					Long idOficinaComparar = Long.valueOf(inversionClienteMesData.getMedioOficinaId());
					if(idOficina.compareTo(idOficinaComparar) != 0){
						medioVariasOficinasMap.put(inversionClienteMesData.getMedioId(), true);
					}
				}
			}
			
			//para lo que viene de plan de medios
			for(InversionClienteMesData inversionClienteMesData : inversionColeccion){
				if(medioOficinasMap.get(inversionClienteMesData.getMedioId()) == null){
					medioOficinasMap.put(inversionClienteMesData.getMedioId(), inversionClienteMesData.getMedioOficinaId());
				}else{
					Long idOficina = Long.valueOf(medioOficinasMap.get(inversionClienteMesData.getMedioId()));
					Long idOficinaComparar = Long.valueOf(inversionClienteMesData.getMedioOficinaId());
					if(idOficina.compareTo(idOficinaComparar) != 0){
						medioVariasOficinasMap.put(inversionClienteMesData.getMedioId(), true);
					}
				}
			}
			
			Vector<InversionClienteMesData> inversionColeccionOrdenesCompraDetalleTemp = (Vector<InversionClienteMesData>)DeepCopy.copy(inversionColeccionOrdenesCompraDetalleAgrupadas);
							
			for(int i=0; i<inversionColeccionOrdenesCompraDetalleTemp.size(); i++){
				InversionClienteMesData inversionClienteMesDataTemp = inversionColeccionOrdenesCompraDetalleTemp.get(i);
				
				if(medioVariasOficinasMap.get(inversionClienteMesDataTemp.getMedioId()) == null){
					inversionColeccionOrdenesCompraDetalleAgrupadas.get(i).setMedio("");
					inversionColeccionOrdenesCompraDetalleAgrupadas.get(i).setMedioId("");
				}
			}
		}
	}

	private Vector<InversionClienteMesData> obtenerColeccionInversionAgrupada(Vector<InversionClienteMesData> inversionColeccionDetalle) {
		
		Vector<InversionClienteMesData> inversionColeccionAgrupada = new Vector<InversionClienteMesData>();		
		
		Vector<InversionClienteMesData> inversionColeccionAgregados = new Vector<InversionClienteMesData>();
		int secuencia1 = 0;
		int secuencia2 = 0;
		boolean ingreso = false;
		
		for(InversionClienteMesData inversionClienteMesDataTemp1 : inversionColeccionDetalle){
			
			InversionClienteMesData inversionClienteMesDataSuma = (InversionClienteMesData)DeepCopy.copy(inversionClienteMesDataTemp1);
							
			for(InversionClienteMesData inversionClienteMesDataTemp2 : inversionColeccionDetalle){
				
				if(secuencia1 != secuencia2 &&
						sonIgualesInversionClienteMesData(inversionClienteMesDataTemp1, inversionClienteMesDataTemp2) &&
						!coleccionContieneInversionMesData(inversionColeccionAgregados,inversionClienteMesDataTemp1)){
												
					inversionClienteMesDataSuma.setEnero(inversionClienteMesDataSuma.getEnero() + inversionClienteMesDataTemp2.getEnero());
					inversionClienteMesDataSuma.setFebrero(inversionClienteMesDataSuma.getFebrero() + inversionClienteMesDataTemp2.getFebrero());
					inversionClienteMesDataSuma.setMarzo(inversionClienteMesDataSuma.getMarzo() + inversionClienteMesDataTemp2.getMarzo());
					inversionClienteMesDataSuma.setAbril(inversionClienteMesDataSuma.getAbril() + inversionClienteMesDataTemp2.getAbril());
					inversionClienteMesDataSuma.setMayo(inversionClienteMesDataSuma.getMayo() + inversionClienteMesDataTemp2.getMayo());
					inversionClienteMesDataSuma.setJunio(inversionClienteMesDataSuma.getJunio() + inversionClienteMesDataTemp2.getJunio());
					inversionClienteMesDataSuma.setJulio(inversionClienteMesDataSuma.getJulio() + inversionClienteMesDataTemp2.getJulio());
					inversionClienteMesDataSuma.setAgosto(inversionClienteMesDataSuma.getAgosto() + inversionClienteMesDataTemp2.getAgosto());
					inversionClienteMesDataSuma.setSeptiembre(inversionClienteMesDataSuma.getSeptiembre() + inversionClienteMesDataTemp2.getSeptiembre());
					inversionClienteMesDataSuma.setOctubre(inversionClienteMesDataSuma.getOctubre() + inversionClienteMesDataTemp2.getOctubre());
					inversionClienteMesDataSuma.setNoviembre(inversionClienteMesDataSuma.getNoviembre() + inversionClienteMesDataTemp2.getNoviembre());
					inversionClienteMesDataSuma.setDiciembre(inversionClienteMesDataSuma.getDiciembre() + inversionClienteMesDataTemp2.getDiciembre());
					inversionClienteMesDataSuma.setTotal(inversionClienteMesDataSuma.getTotal() + inversionClienteMesDataTemp2.getTotal());
					
					inversionColeccionAgregados.add(inversionClienteMesDataTemp2);
					ingreso = true;
				}
				secuencia2++;
			}
			
			if(ingreso || (!ingreso && !coleccionContieneInversionMesData(inversionColeccionAgregados,inversionClienteMesDataTemp1))){
				inversionColeccionAgrupada.add(inversionClienteMesDataSuma);
			}
			inversionColeccionAgregados.add(inversionClienteMesDataTemp1);
			secuencia2 = 0;
			secuencia1++;
			ingreso = false;
		}
		
		return inversionColeccionAgrupada;
	}

	private Vector<InversionClienteMesData> obtenerColeccionInversionOrdenesCompraDetalle() {
		
		Vector<InversionClienteMesData> inversionColeccionOrdenesCompraDetalle = new Vector<InversionClienteMesData>();
				
		try {
			if(presupuestosDetalle.size() > 0){		
				
				Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
				while(presupuestosDetalleIt.hasNext()){
					Object[] ordenCompraDetalle = (Object[])presupuestosDetalleIt.next();
					
					String cliente = "";
					String clienteId = "";
					String ruc = "";
					String clienteOficina = "";
					String clienteOficinaId = "";
					String medio = "";
					String medioId = "";
					String medioOficina = "";
					String medioOficinaId = "";
					String marca = "";
					String marcaId = "";
					String producto = "";
					String productoId = "";
					String campana = "";
					Long campanaId = 0L;
					String tipoMedio = "";
					String tipoMedioId = "";
					String tipoPauta = "Pauta Regular";
					Timestamp fecha = new Timestamp(Calendar.getInstance().getTimeInMillis());
					BigDecimal valorSubtotal = new BigDecimal(0);
					BigDecimal cantidad = new BigDecimal(0);
					BigDecimal porcentajeDescuentoEspecial = new BigDecimal(0);
					BigDecimal porcentajeDescuentoAgencia = new BigDecimal(0);
					BigDecimal porcentajeDescuentosVarios = new BigDecimal(0);
					BigDecimal porcentajeComisionAgencia = new BigDecimal(0);
					Long usuarioCreacionId = 0L;
					Long usuarioActualizacionId = 0L;
					Long ordenId = 0L;
					Long presupuestoId = 0L;
									
					if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO) == 0){
						cliente = (String)ordenCompraDetalle[0];
						clienteId = ((Long)ordenCompraDetalle[1]).toString();
						medio = (String)ordenCompraDetalle[2];
						medioId = ((Long)ordenCompraDetalle[3]).toString();
						producto = (String)ordenCompraDetalle[4];
						productoId = ((Long)ordenCompraDetalle[5]).toString();
						tipoMedio = (String)ordenCompraDetalle[6];
						tipoMedioId = ((Long)ordenCompraDetalle[7]).toString();
						fecha = (Timestamp)ordenCompraDetalle[8];
						valorSubtotal = (BigDecimal)ordenCompraDetalle[9];
						cantidad = (BigDecimal)ordenCompraDetalle[10];
						porcentajeDescuentoAgencia = (BigDecimal)ordenCompraDetalle[11];
						porcentajeComisionAgencia = (BigDecimal)ordenCompraDetalle[12];
						ruc = (String)ordenCompraDetalle[13];
						marca = (String)ordenCompraDetalle[14];
						marcaId = ((Long)ordenCompraDetalle[15]).toString();
						campanaId = (Long)ordenCompraDetalle[16];
						usuarioCreacionId = (Long)ordenCompraDetalle[17];
						usuarioActualizacionId = (Long)ordenCompraDetalle[18];
						porcentajeDescuentoEspecial = (BigDecimal)ordenCompraDetalle[19];
						porcentajeDescuentosVarios = (BigDecimal)ordenCompraDetalle[20];
						ordenId = (Long)ordenCompraDetalle[21];
						presupuestoId = (Long)ordenCompraDetalle[12];
					}else{
						clienteOficina = (String)ordenCompraDetalle[0];
						clienteOficinaId = ((Long)ordenCompraDetalle[1]).toString();
						medioOficina = (String)ordenCompraDetalle[2];
						medioOficinaId = ((Long)ordenCompraDetalle[3]).toString();
						producto = (String)ordenCompraDetalle[4];
						productoId = ((Long)ordenCompraDetalle[5]).toString();
						tipoMedio = (String)ordenCompraDetalle[6];
						tipoMedioId = ((Long)ordenCompraDetalle[7]).toString();
						fecha = (Timestamp)ordenCompraDetalle[8];
						valorSubtotal = (BigDecimal)ordenCompraDetalle[9];
						cantidad = (BigDecimal)ordenCompraDetalle[10];
						porcentajeDescuentoAgencia = (BigDecimal)ordenCompraDetalle[11];
						porcentajeComisionAgencia = (BigDecimal)ordenCompraDetalle[12];
						marca = (String)ordenCompraDetalle[13];
						marcaId = ((Long)ordenCompraDetalle[14]).toString();
						campanaId = (Long)ordenCompraDetalle[15];
						presupuestoId = (Long)ordenCompraDetalle[16];
						medio = (String)ordenCompraDetalle[17];
						medioId = ((Long)ordenCompraDetalle[18]).toString();
						campana = (String)ordenCompraDetalle[19];
						usuarioCreacionId = (Long)ordenCompraDetalle[20];
						usuarioActualizacionId = (Long)ordenCompraDetalle[21];
						porcentajeDescuentoEspecial = (BigDecimal)ordenCompraDetalle[22];
						porcentajeDescuentosVarios = (BigDecimal)ordenCompraDetalle[23];
						ordenId = (Long)ordenCompraDetalle[24];
					}
					
					double totalMes = (valorSubtotal.multiply(cantidad)).doubleValue();
					
					
					//para calcular los valores de las notas de credito Error y Ganancia
					double subtotalError = 0D;
					double subtotalGanancia = 0D;
					Map aMap = new HashMap();
					aMap.put("tipoReferencia", "F");
					aMap.put("tipoPresupuesto", "P");
					aMap.put("presupuestoId", presupuestoId);
					aMap.put("ordenId", ordenId);
					String[] tiposNota = {"E", "G"}; //ERROR Y GANANCIA
					Collection notasCreditoDetalle = SessionServiceLocator.getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByQueryAndByEstados(aMap, tiposNota);
					Iterator notasCreditoDetalleIt = notasCreditoDetalle.iterator();
					while(notasCreditoDetalleIt.hasNext()){
						NotaCreditoDetalleIf notaCreditoDetalle = (NotaCreditoDetalleIf)notasCreditoDetalleIt.next();
						if(notaCreditoDetalle.getTipoNota().equals("E")){
							subtotalError = subtotalError + (notaCreditoDetalle.getValor().doubleValue() * notaCreditoDetalle.getCantidad().doubleValue());
						}else if(notaCreditoDetalle.getTipoNota().equals("G")){
							subtotalGanancia = subtotalGanancia + (notaCreditoDetalle.getValor().doubleValue() * notaCreditoDetalle.getCantidad().doubleValue());
						}
					}
					
					//si se quiere valor neto (se resta ademas de los descuentos las notas de credito error y ganancia
					if(getRbValorNeto().isSelected()){
						double porcentajeDescuentoEspecialTemp = porcentajeDescuentoEspecial.doubleValue() / 100;
						double descuentoEspecial = totalMes * porcentajeDescuentoEspecialTemp;
						double porcentajeDescuentoAgenciaTemp = porcentajeDescuentoAgencia.doubleValue() / 100;
						double descuentoAgencia = (totalMes - descuentoEspecial) * porcentajeDescuentoAgenciaTemp;
						double porcentajeDescuentosVariosTemp = porcentajeDescuentosVarios.doubleValue() / 100;
						double descuentosVarios = (totalMes - descuentoEspecial) * porcentajeDescuentosVariosTemp;
						double porcentajeComisionAgenciaTemp = porcentajeComisionAgencia.doubleValue() / 100;
						double comisionAgencia = (totalMes - descuentoEspecial - descuentoAgencia - descuentosVarios) * porcentajeComisionAgenciaTemp;
											
						totalMes = totalMes - descuentoEspecial - descuentoAgencia - descuentosVarios + comisionAgencia - subtotalError - subtotalGanancia;
					}
					//en valor bruto solo se resta las notas de credito por error.
					else{
						totalMes = totalMes - subtotalError;
					}
												
					Double enero = 0D;
					if(fecha.getMonth() == 0)
						enero = totalMes;
					Double febrero = 0D;
					if(fecha.getMonth() == 1)
						febrero = totalMes;
					Double marzo = 0D;
					if(fecha.getMonth() == 2)
						marzo = totalMes;
					Double abril = 0D;
					if(fecha.getMonth() == 3)
						abril = totalMes;
					Double mayo = 0D;
					if(fecha.getMonth() == 4)
						mayo = totalMes;
					Double junio = 0D;
					if(fecha.getMonth() == 5)
						junio = totalMes;
					Double julio = 0D;
					if(fecha.getMonth() == 6)
						julio = totalMes;
					Double agosto = 0D;
					if(fecha.getMonth() == 7)
						agosto = totalMes;
					Double septiembre = 0D;
					if(fecha.getMonth() == 8)
						septiembre = totalMes;
					Double octubre = 0D;
					if(fecha.getMonth() == 9)
						octubre = totalMes;
					Double noviembre = 0D;
					if(fecha.getMonth() == 10)
						noviembre = totalMes;
					Double diciembre = 0D;
					if(fecha.getMonth() == 11)
						diciembre = totalMes;
					
					Double total = enero+febrero+marzo+abril+mayo+junio+julio+agosto+septiembre+octubre+noviembre+diciembre;
										
					InversionClienteMesData inversionClienteMesData = new InversionClienteMesData();
					inversionClienteMesData.setCliente(cliente);
					inversionClienteMesData.setClienteId(clienteId);
					inversionClienteMesData.setClienteOficina(clienteOficina);
					inversionClienteMesData.setClienteOficinaId(clienteOficinaId);
					
					if (getCbMostrarMedio().isSelected()){
						inversionClienteMesData.setMedio(medio);
						inversionClienteMesData.setMedioId(medioId);
					}
					
					inversionClienteMesData.setMedioOficina(medioOficina);
					inversionClienteMesData.setMedioOficinaId(medioOficinaId);
					inversionClienteMesData.setProducto(producto);
					inversionClienteMesData.setProductoId(productoId);
					inversionClienteMesData.setCampana(campana);
					inversionClienteMesData.setCampanaId(campanaId.toString());
					inversionClienteMesData.setTipoMedio(tipoMedio);
					inversionClienteMesData.setTipoMedioId(tipoMedioId);
					inversionClienteMesData.setTipoPauta(tipoPauta);
					inversionClienteMesData.setRuc(ruc);
					
					if(getCbMostrarMarca().isSelected()){
						inversionClienteMesData.setMarca(marca);
						inversionClienteMesData.setMarcaId(marcaId);					
					}				
					
					inversionClienteMesData.setEnero(enero);
					inversionClienteMesData.setFebrero(febrero);
					inversionClienteMesData.setMarzo(marzo);
					inversionClienteMesData.setAbril(abril);
					inversionClienteMesData.setMayo(mayo);
					inversionClienteMesData.setJunio(junio);
					inversionClienteMesData.setJulio(julio);
					inversionClienteMesData.setAgosto(agosto);
					inversionClienteMesData.setSeptiembre(septiembre);
					inversionClienteMesData.setOctubre(octubre);
					inversionClienteMesData.setNoviembre(noviembre);
					inversionClienteMesData.setDiciembre(diciembre);
					inversionClienteMesData.setTotal(total);
					
					//si se eligio una oficina empresa especifica entonces solo se presentara los presupuestos
					//ingresados por usuarios de esa oficina
					if(!getCmbOficinaEmpresa().getSelectedItem().equals("TODOS")){
						OficinaIf oficinaEmpresa = (OficinaIf)getCmbOficinaEmpresa().getSelectedItem();
						
						UsuarioIf usuarioOrden = null;
						if(usuarioActualizacionId != null)
							usuarioOrden = mapaUsuario.get(usuarioActualizacionId);
						else if(usuarioCreacionId != null)
							usuarioOrden = mapaUsuario.get(usuarioCreacionId);
						
						EmpleadoIf empleadoOrden = null;
						if(usuarioOrden != null)
							empleadoOrden = mapaEmpleado.get(usuarioOrden.getEmpleadoId());
						
						if(empleadoOrden != null && empleadoOrden.getOficinaId().compareTo(oficinaEmpresa.getId()) == 0){
							inversionColeccionOrdenesCompraDetalle.add(inversionClienteMesData);
						}
						
					}else{
						inversionColeccionOrdenesCompraDetalle.add(inversionClienteMesData);
					}			
				}			
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		return inversionColeccionOrdenesCompraDetalle;
	}
	
	
	private void agregarColumnasTabla(String mes, BigDecimal valorMes, Vector<String> fila, InversionClienteMesData inversionClienteProductoMedioMesData) {
		fila.add(formatoDecimal.format(valorMes).toString());		
		
		if (mes.compareTo("ENERO") == 0)
			inversionClienteProductoMedioMesData.setEnero(Double.parseDouble(valorMes.toString()));
		else if (mes.compareTo("FEBRERO") == 0)
			inversionClienteProductoMedioMesData.setFebrero(Double.parseDouble(valorMes.toString()));
		else if (mes.compareTo("MARZO") == 0)
			inversionClienteProductoMedioMesData.setMarzo(Double.parseDouble(valorMes.toString()));
		else if (mes.compareTo("ABRIL") == 0)
			inversionClienteProductoMedioMesData.setAbril(Double.parseDouble(valorMes.toString()));
		else if (mes.compareTo("MAYO") == 0)
			inversionClienteProductoMedioMesData.setMayo(Double.parseDouble(valorMes.toString()));
		else if (mes.compareTo("JUNIO") == 0)
			inversionClienteProductoMedioMesData.setJunio(Double.parseDouble(valorMes.toString()));
		else if (mes.compareTo("JULIO") == 0)
			inversionClienteProductoMedioMesData.setJulio(Double.parseDouble(valorMes.toString()));
		else if (mes.compareTo("AGOSTO") == 0)
			inversionClienteProductoMedioMesData.setAgosto(Double.parseDouble(valorMes.toString()));
		else if (mes.compareTo("SEPTIEMBRE") == 0)
			inversionClienteProductoMedioMesData.setSeptiembre(Double.parseDouble(valorMes.toString()));
		else if (mes.compareTo("OCTUBRE") == 0)
			inversionClienteProductoMedioMesData.setOctubre(Double.parseDouble(valorMes.toString()));
		else if (mes.compareTo("NOVIEMBRE") == 0)
			inversionClienteProductoMedioMesData.setNoviembre(Double.parseDouble(valorMes.toString()));
		else if (mes.compareTo("DICIEMBRE") == 0)
			inversionClienteProductoMedioMesData.setDiciembre(Double.parseDouble(valorMes.toString()));
		//inversionClienteProductoMedioMesData.setDiciembre(valorMes.toString());
	}
	//END 5 AGOSTO
	
	
	//ADD 26 JULIO
	private void setClienteOficina() {
		getTxtClienteOficina().setText(clienteOficinaIf.getCodigo() + " - "+ clienteOficinaIf.getDescripcion());
	}
	
	private void setMedioOficina() {
		getTxtMedioOficina().setText(medioOficinaIf.getCodigo() + " - "+ medioOficinaIf.getDescripcion());
	}
	//END 26 JULIO
	
	public void clean() {
		agruparBy = AGRUPAR_POR_CLIENTE_PRODUCTO_TMEDIOS_MEDIOS;
		
		getRbValorBruto().setSelected(true);
		
		calendarFechaInicio = new GregorianCalendar();
		calendarFechaFin    = new GregorianCalendar();
		calendarFechaInicioMin = GregorianCalendar.getInstance();
		calendarFechaFinMax = GregorianCalendar.getInstance();
		fechaInicio = calendarFechaInicio.getTime();
		fechaFin = calendarFechaFin.getTime();
		
		clienteCriteria = null;
		medioCriteria = null;
		clienteOficinaCriteria = null;
		medioOficinaCriteria = null;
		clienteOficinaIf = null;
		medioOficinaIf = null;
		clienteIf = null;
		medioIf = null;
		marcaProductoIf = null;
		tipoProveedorIf = null;
		productoClienteCriteria = null;
		productoClienteIf = null;
		filtro = "";
		
		inversionColeccion.clear();
		inversionColeccion = new Vector<InversionClienteMesData>();
		
		ordenesMedioXProductoColl = null;
		ordenesMedioDetalleXCanalColl = null;
		ordenesMedioDetalleXProductoColl = null;
			
		mapClientesIdProductosIdMedioIdMesesTotalOrdenesCanal.clear();
		mapClientesIdProductosIdMedioIdMesesTotalOrdenesProductoAndCanal.clear();
		mapClientesIdProductosIdMedioIdMesesTotalOrdenesProducto.clear();
		mapClientesIdProductosIdMedioIdOrdenMedioDetalleCanalList.clear();
		mapClientesIdProductosIdMedioIdOrdenesXProducto.clear();
		mapClientesIdProductosIdMedioIdOrdenMedioDetalleProductoList.clear();
		
		mapClientesIdMediosIdProductosIdOrdenesXProducto.clear();
		mapClientesIdMedioIdProductosIdOrdenMedioDetalleCanalList.clear();
		mapClientesIdMedioIdProductosIdMesesTotalOrdenesProducto.clear();
		mapClientesIdMedioIdProductosIdMesesTotalOrdenesCanal.clear();
		mapClientesIdMedioIdProductosIdMesesTotalOrdenesProductoAndCanal.clear();
		mapClientesIdMedioIdProductosIdOrdenMedioDetalleProductoList.clear();
		
		mapMediosIdClientesIdProductosIdOrdenesXProducto.clear();
		mapMedioIdClientesIdProductosIdOrdenMedioDetalleCanalList.clear();
		mapMedioIdClientesIdProductosIdMesesTotalOrdenesProducto.clear();
		mapMedioIdClientesIdProductosIdMesesTotalOrdenesCanal.clear();
		mapMedioIdClientesIdProductosIdMesesTotalOrdenesProductoAndCanal.clear();
		mapMedioIdClientesIdProductosIdOrdenMedioDetalleProductoList.clear();
		
		mapClientesIdMedioIdMesesTotalOrdenesProductoAndCanal.clear();
		mapClientesIdProductoIdMesesTotalOrdenesProductoAndCanal.clear();
		mapClientesIdMesesTotalOrdenesProductoAndCanal.clear();

		mapMediosIdClientesIdMesesTotalOrdenesProductoAndCanal.clear();
		mapMediosIdProductoIdMesesTotalOrdenesProductoAndCanal.clear();
		mapMediosIdMesesTotalOrdenesProductoAndCanal.clear();
		
		mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdsOrdenesMedioDetalleProductoList.clear();
		mapClienteClienteOfiMarcaProductoCampanaTipoMedioMedioMedioOfiIdsTipoPautaDerechoProgramaIdMesesTotalOrdenesProducto.clear();
		
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		
		getCmbMarcas().removeAllItems();
		getCmbMarcas().addItem(TODOS);
		getCmbTipoMedio().setSelectedItem(TODOS);
		getCmbDerechoPrograma().setSelectedItem(TODOS);
		
		getRbAgruparClienteProductoMedios().setSelected(true);
		getBtnProducto().setEnabled(false);
		getCmbMarcas().setEnabled(false);
		
		getTxtCliente().setText("");
		getTxtClienteOficina().setText("");
		getTxtProducto().setText("");
		getTxtMedio().setText("");	
		getTxtMedioOficina().setText("");	
		
		getCbPautaRegular().setSelected(true);
		getCbAuspicio().setSelected(true);
		getCbClientesTodos().setSelected(true);
		getCbClienteOficinaTodos().setSelected(true);
		getCbProductosTodos().setSelected(true);
		getCbMediosTodos().setSelected(true);
		getCbMedioOficinaTodos().setSelected(true);
		
		getCbMostrarCliente().setSelected(true);
		getCbMostrarProducto().setSelected(false);
		getCbMostrarTipoMedio().setSelected(false);
		getCbMostrarMedio().setSelected(false);
		getCbMostrarDerechoPrograma().setSelected(false);
		getCbMostrarCliente().setEnabled(false);
		getCbMostrarClienteOficina().setSelected(true);
		getCbMostrarClienteOficina().setEnabled(false);
		getCbMostrarMarca().setSelected(false);
		getCbMostrarMedioOficina().setSelected(false);
		getCbMostrarTipoPauta().setSelected(false);
		
		cargarMapas();
		cleanTable();
	}

	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblInversionesPlanMedios().getModel();
		for(int i= this.getTblInversionesPlanMedios().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void showSaveMode() {
		setSaveMode();
		clean();
	}
	
}


