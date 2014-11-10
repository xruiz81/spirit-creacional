package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.entity.OrdenCompraDetalleIf;
import com.spirit.compras.entity.OrdenCompraIf;
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
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.CampanaProductoIf;
import com.spirit.medios.entity.CampanaProductoVersionIf;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.gui.criteria.CampanaCriteria;
import com.spirit.medios.gui.criteria.ProductoClienteCriteria;
import com.spirit.medios.gui.panel.JPOrdenesPresupuesto;
import com.spirit.medios.gui.reporteData.OrdenesPresupuestoData;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;


public class OrdenesPresupuestoModel extends JPOrdenesPresupuesto {

	private static final String TODOS = "TODOS";
	private ClienteOficinaCriteria clienteOficinaCriteria;
	protected ClienteOficinaIf clienteOficinaIf;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String CODIGO_TIPO_PROVEEDOR = "PR";
	private CampanaCriteria campanaCriteria;
	private JDPopupFinderModel popupFinder;
	protected CampanaIf campanaIf;
	protected EmpleadoIf creadoPor;	
	private ArrayList<Object[]> ordenesMedioDetalle = new ArrayList<Object[]>();
	private ArrayList presupuestosDetalle = new ArrayList();
	private ArrayList<OrdenesPresupuestoData> ordenesPresupuestoDataVector = new ArrayList<OrdenesPresupuestoData>();
	private String tipoProveedor = TODOS;
	private static final String NOMBRE_TIPO_PROVEEDOR_PRODUCCION = "PRODUCCION";
	protected ClienteOficinaIf medioOficinaIf = null;
	protected TipoProveedorIf tipoProveedorIf = null;
	protected ClienteIf medioIf = null;
	private static final String PAUTA_REGULAR_AUSPICIO = "B";
	protected String tipoPauta = PAUTA_REGULAR_AUSPICIO;
	private String estadoPresupuesto = TODOS;
	private String estadoOrden = TODOS;
	private Map<Long, ClienteIf> mapaCliente = new HashMap<Long, ClienteIf>();
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long, TipoProveedorIf> mapaTipoProveedor = new HashMap<Long, TipoProveedorIf>();
	private Map<Long, MarcaProductoIf> mapaMarcasProducto = new HashMap<Long, MarcaProductoIf>();
	private Map<Long, ProductoClienteIf> mapaProductoCliente = new HashMap<Long, ProductoClienteIf>();
	private Map<Long, CampanaProductoIf> mapaCampanaProducto = new HashMap<Long, CampanaProductoIf>();
	private Map<Long, CampanaIf> mapaCampana = new HashMap<Long, CampanaIf>();
	private Map<Long, CampanaProductoVersionIf> mapaCampanaProductoVersion = new HashMap<Long, CampanaProductoVersionIf>();
	private Map<Long, EmpleadoIf> mapaEmpleado = new HashMap<Long, EmpleadoIf>();
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DefaultTableModel tableModel;
	private ClienteCriteria medioCriteria;
	private ClienteOficinaCriteria medioOficinaCriteria;
	protected MarcaProductoIf marcaProductoIf = null;
	private ProductoClienteIf productoClienteIf = null;
	private ProductoClienteCriteria productoClienteCriteria;
	private ClienteCriteria clienteCriteria;
	protected ClienteIf clienteIf = null;
		
	//totales
	private Double[] ordenes = new Double[13];
	private double totalOrdenes = 0D;
	private Integer[] cantidadOrdenes = new Integer[13];
	private int totalCantidadOrdenes = 0;
	
	private Double[] ordenesEmitidas = new Double[13];
	private double totalOrdenesEmitidas = 0D;
	private Double[] ordenesOrdenadas = new Double[13];
	private double totalOrdenesOrdenadas = 0D;
	private Double[] ordenesEnviadas = new Double[13];
	private double totalOrdenesEnviadas = 0D;
	private Double[] ordenesIngresadas = new Double[13];
	private double totalOrdenesIngresadas = 0D;
	private Double[] ordenesPrepagadas = new Double[13];
	private double totalOrdenesPrepagadas = 0D;
	
	private Integer[] cantidadOrdenesEmitidas = new Integer[13];
	private int totalCantidadOrdenesEmitidas = 0;
	private Integer[] cantidadOrdenesOrdenadas = new Integer[13];
	private int totalCantidadOrdenesOrdenadas = 0;
	private Integer[] cantidadOrdenesEnviadas = new Integer[13];
	private int totalCantidadOrdenesEnviadas = 0;
	private Integer[] cantidadOrdenesIngresadas = new Integer[13];
	private int totalCantidadOrdenesIngresadas = 0;
	private Integer[] cantidadOrdenesPrepagadas = new Integer[13];
	private int totalCantidadOrdenesPrepagadas = 0;
	
	
	public OrdenesPresupuestoModel(){
		cargarMapas();
		anchoColumnasTabla();
		cargarComboSubtipoProveedor();
		initKeyListeners();
		initListeners();
		showSaveMode();
	}
	
	public void cargarMapas() {
		try {
			mapaCliente.clear();
			Collection clientes = SessionServiceLocator
					.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesIt = clientes.iterator();
			while (clientesIt.hasNext()) {
				ClienteIf cliente = (ClienteIf) clientesIt.next();
				mapaCliente.put(cliente.getId(), cliente);
			}
			
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator
					.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while (clientesOficinaIt.hasNext()) {
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clientesOficinaIt
						.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
			
			mapaTipoProveedor.clear();
			Collection tiposProveedor = SessionServiceLocator
					.getTipoProveedorSessionService().findTipoProveedorByEmpresaId(Parametros.getIdEmpresa());
			Iterator tiposProveedorIt = tiposProveedor.iterator();
			while (tiposProveedorIt.hasNext()) {
				TipoProveedorIf tipoProveedor = (TipoProveedorIf) tiposProveedorIt
						.next();
				mapaTipoProveedor.put(tipoProveedor.getId(), tipoProveedor);
			}
			
			mapaMarcasProducto.clear();
			Collection marcasProducto = SessionServiceLocator
					.getMarcaProductoSessionService().findMarcaProductoByEmpresaId(Parametros.getIdEmpresa());
			Iterator marcasProductoIt = marcasProducto.iterator();
			while (marcasProductoIt.hasNext()) {
				MarcaProductoIf marcaProducto = (MarcaProductoIf) marcasProductoIt
						.next();
				mapaMarcasProducto.put(marcaProducto.getId(), marcaProducto);
			}
			
			mapaCampanaProductoVersion.clear();
			Collection campanaProductoVersiones = SessionServiceLocator
					.getCampanaProductoVersionSessionService().findCampanaProductoVersionByEmpresaId(Parametros.getIdEmpresa());
			Iterator campanaProductoVersionesIt = campanaProductoVersiones.iterator();
			while (campanaProductoVersionesIt.hasNext()) {
				CampanaProductoVersionIf campanaProductoVersion = (CampanaProductoVersionIf) campanaProductoVersionesIt
						.next();
				mapaCampanaProductoVersion.put(campanaProductoVersion.getId(), campanaProductoVersion);
			}
			
			
			mapaCampanaProducto.clear();
			Collection campanaProductos = SessionServiceLocator
					.getCampanaProductoSessionService().findCampanaProductoByEmpresaId(Parametros.getIdEmpresa());
			Iterator campanaProductosIt = campanaProductos.iterator();
			while (campanaProductosIt.hasNext()) {
				CampanaProductoIf campanaProducto = (CampanaProductoIf) campanaProductosIt
						.next();
				mapaCampanaProducto.put(campanaProducto.getId(), campanaProducto);
			}
			
			mapaCampana.clear();
			Collection campanas = SessionServiceLocator
					.getCampanaSessionService().findCampanaByEmpresaId(Parametros.getIdEmpresa());
			Iterator campanasIt = campanas.iterator();
			while (campanasIt.hasNext()) {
				CampanaIf campana = (CampanaIf) campanasIt.next();
				mapaCampana.put(campana.getId(), campana);
			}			
			
			mapaProductoCliente.clear();
			Collection productosCliente = SessionServiceLocator
					.getProductoClienteSessionService().findProductoClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator productosClienteIt = productosCliente.iterator();
			while (productosClienteIt.hasNext()) {
				ProductoClienteIf productoCliente = (ProductoClienteIf) productosClienteIt
						.next();
				mapaProductoCliente.put(productoCliente.getId(), productoCliente);
			}
			
			mapaEmpleado.clear();
			Collection empleados = SessionServiceLocator
					.getEmpleadoSessionService().findEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Iterator empleadosIt = empleados.iterator();
			while (empleadosIt.hasNext()) {
				EmpleadoIf empleado = (EmpleadoIf) empleadosIt.next();
				mapaEmpleado.put(empleado.getId(), empleado);
			}	
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void anchoColumnasTabla() {
		setSorterTable(getTblOrdenes());
		getTblOrdenes().getTableHeader().setReorderingAllowed(false);
		getTblOrdenes().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblOrdenes().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(250);		
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(11);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblOrdenes().getColumnModel().getColumn(12);
		anchoColumna.setPreferredWidth(100);
	}
	
	public void initKeyListeners(){
		getBtnConsultar().setToolTipText("Consultar Ordenes");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
				
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnClienteOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnClienteOficina().setToolTipText("Buscar Cliente Oficina");
		getBtnProveedor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnProveedor().setToolTipText("Buscar Proveedor");
		getBtnProveedorOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnProveedorOficina().setToolTipText("Buscar Proveedor Oficina");
		getBtnCampana().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCampana().setToolTipText("Buscar Campaña");
		getBtnProducto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnProducto().setToolTipText("Buscar Producto");
		getBtnCreadoPor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCreadoPor().setToolTipText("Buscar Empleado");		
		
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
	}
	
	Comparator<TipoProveedorIf> ordenarTipoPorNombre = new Comparator<TipoProveedorIf>(){
		public int compare(TipoProveedorIf o1, TipoProveedorIf o2) {
			return o1.getNombre().compareTo(o2.getNombre());
		}		
	};
	
	private void cargarComboSubtipoProveedor(){
		try {
			List tiposProveedor = new ArrayList();
						
			Iterator it = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				TipoProveedorIf tipoProveedorMedio = (TipoProveedorIf) it.next();
				tiposProveedor.add(tipoProveedorMedio);
			}
			Collections.sort(tiposProveedor,ordenarTipoPorNombre);
			tiposProveedor.add(TODOS);
			refreshCombo(getCmbSubtipoProveedor(),tiposProveedor);
			getCmbSubtipoProveedor().setSelectedItem(TODOS);
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	public void obtenerOrdenesMedioPresupuestosDetalle(){
		try {
			//Veo si se desea buscar por código de presupuesto / pauta
			String codigoUnicoPresupuesto = "";
			if(getCbFiltrarCodigoPresupuesto().isSelected()){
				codigoUnicoPresupuesto = getTxtPresupuesto().getText().trim();
			}			
			
			Date fechaInicio = getCmbFechaInicio().getDate();
			Date fechaFin = getCmbFechaFin().getDate();
			//agruparBy sirve para el reporte de inversion de plan de medios
			String agruparBy = "";
			
			Timestamp timeInicio = Utilitarios.resetTimestampStartDate(new Timestamp(fechaInicio.getTime()));
			Timestamp timeFin =  Utilitarios.resetTimestampEndDate(new Timestamp(fechaFin.getTime()));
			
			//si se busca por fecha de aprobacion
			boolean fechaAprobacion = false;
			
			//se declara variable que sirve para otra pantalla
			boolean aprobadosFacturados = false;
			
			boolean buscarPresupuestosPrepago = false;
			if(getCbOrdenesPresupuestoPrepago().isSelected()){
				buscarPresupuestosPrepago = true;
			}
						
			//ORDENES MEDIO DETALLE
			if(!tipoProveedor.equals(NOMBRE_TIPO_PROVEEDOR_PRODUCCION)){
				
				Map<String,Long> mapaOrden = new LinkedHashMap<String, Long>();			
				
				if (clienteOficinaIf != null) 
					mapaOrden.put("clienteOficinaId", clienteOficinaIf.getId());			
							
				if (medioOficinaIf != null)
					mapaOrden.put("proveedorId", medioOficinaIf.getId());
				
				if (creadoPor != null)
					mapaOrden.put("empleadoId", creadoPor.getId());
				
				Map mapaGeneral = new LinkedHashMap();
				
				if (clienteIf != null && clienteOficinaIf == null) 
					mapaGeneral.put("keyClienteId", clienteIf.getId());
				
				if (campanaIf != null) 
					mapaGeneral.put("keyCampanaId", campanaIf.getId());	
								
				if (tipoProveedorIf != null && medioIf == null && medioOficinaIf == null)
					mapaGeneral.put("keyTipoProveedorId", tipoProveedorIf.getId());
				
				if (medioIf != null && medioOficinaIf == null)
					mapaGeneral.put("keyProveedorId", medioIf.getId());
				
				if (marcaProductoIf != null && productoClienteIf == null)
					mapaGeneral.put("keyMarcaProductoId", marcaProductoIf.getId());			
				
				if (productoClienteIf != null)
					mapaGeneral.put("keyProductoClienteId", productoClienteIf.getId());			
				
				
				if(getCbFiltrarCodigoPresupuesto().isSelected())
					mapaGeneral.put("keyCodigoUnicoPresupuesto", codigoUnicoPresupuesto);
							
				ordenesMedioDetalle = (ArrayList)SessionServiceLocator.getOrdenMedioSessionService().
				findOrdenMedioDetalleByQueryAndQueryGeneralByProductoAndByFechas(mapaOrden, 
						mapaGeneral, tipoPauta, timeInicio, timeFin, fechaAprobacion, Parametros.getIdEmpresa(), 
						estadoPresupuesto, aprobadosFacturados, estadoOrden, false, buscarPresupuestosPrepago);
				//ordenesMedioDetalle.clear();
			}
			
			//PRESUPUESTOS DETALLE
			Map mapaPresupuestoDetalle = new LinkedHashMap();
			
			if (clienteOficinaIf != null) 
				mapaPresupuestoDetalle.put("clienteOficinaId", clienteOficinaIf.getId());
			
			if (clienteIf != null && clienteOficinaIf == null) 
				mapaPresupuestoDetalle.put("clienteId", clienteIf.getId());	
			
			if (medioOficinaIf != null)
				mapaPresupuestoDetalle.put("proveedorOficinaId", medioOficinaIf.getId());
			
			if (creadoPor != null)
				mapaPresupuestoDetalle.put("empleadoId", creadoPor.getId());
			
			if (campanaIf != null) 
				mapaPresupuestoDetalle.put("campanaId", campanaIf.getId());
			
			if (tipoProveedor != null && !tipoProveedor.equals("TODOS"))
				mapaPresupuestoDetalle.put("tipo", tipoProveedor.substring(0, 1));
			
			if (tipoProveedorIf != null && medioIf == null && medioOficinaIf == null)
				mapaPresupuestoDetalle.put("tipoProveedorId", tipoProveedorIf.getId());
			
			if (medioIf != null && medioOficinaIf == null)
				mapaPresupuestoDetalle.put("proveedorId", medioIf.getId());
			
			if (marcaProductoIf != null && productoClienteIf == null)
				mapaPresupuestoDetalle.put("marcaProductoId", marcaProductoIf.getId());	
			
			if (productoClienteIf != null)
				mapaPresupuestoDetalle.put("productoClienteId", productoClienteIf.getId());
									
			if(getCbFiltrarCodigoPresupuesto().isSelected())
				mapaPresupuestoDetalle.put("codigoUnicoPresupuesto", codigoUnicoPresupuesto);
			
			presupuestosDetalle = (ArrayList)SessionServiceLocator.getPresupuestoSessionService()
			.findPresupuestosDetalleByQueryByProductoAndByFechas(mapaPresupuestoDetalle, timeInicio, timeFin, 
					fechaAprobacion, Parametros.getIdEmpresa(), agruparBy, estadoPresupuesto, tipoProveedor, 
					aprobadosFacturados, buscarPresupuestosPrepago, estadoOrden, false);
			//presupuestosDetalle.clear();			
			System.out.println("a");
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void reiniciarValores(){
		ordenesPresupuestoDataVector = new ArrayList<OrdenesPresupuestoData>();
		
		ordenes = new Double[13];
		for(int i=0; i < ordenes.length; i++)
			ordenes[i] = 0D;
		
		totalOrdenes = 0D;
		
		ordenesEmitidas = new Double[13];
		for(int i=0; i < ordenesEmitidas.length; i++)
			ordenesEmitidas[i] = 0D;
		
		totalOrdenesEmitidas = 0D;
		
		ordenesOrdenadas = new Double[13];
		for(int i=0; i < ordenesOrdenadas.length; i++)
			ordenesOrdenadas[i] = 0D;
		
		totalOrdenesOrdenadas = 0D;
		
		ordenesEnviadas = new Double[13];
		for(int i=0; i < ordenesEnviadas.length; i++)
			ordenesEnviadas[i] = 0D;
		
		totalOrdenesEnviadas = 0D;
		
		ordenesIngresadas = new Double[13];
		for(int i=0; i < ordenesIngresadas.length; i++)
			ordenesIngresadas[i] = 0D;
		
		totalOrdenesIngresadas = 0D;
		
		ordenesPrepagadas = new Double[13];
		for(int i=0; i < ordenesPrepagadas.length; i++)
			ordenesPrepagadas[i] = 0D;
		
		totalOrdenesPrepagadas = 0D;
		
		cantidadOrdenes = new Integer[13];
		for(int i=0; i < cantidadOrdenes.length; i++)
			cantidadOrdenes[i] = 0;
		
		totalCantidadOrdenes = 0;
		
		cantidadOrdenesEmitidas = new Integer[13];
		for(int i=0; i < cantidadOrdenesEmitidas.length; i++)
			cantidadOrdenesEmitidas[i] = 0;
		
		totalCantidadOrdenesEmitidas = 0;
		
		cantidadOrdenesOrdenadas = new Integer[13];
		for(int i=0; i < cantidadOrdenesOrdenadas.length; i++)
			cantidadOrdenesOrdenadas[i] = 0;
		
		totalCantidadOrdenesOrdenadas = 0;
		
		cantidadOrdenesEnviadas = new Integer[13];
		for(int i=0; i < cantidadOrdenesEnviadas.length; i++)
			cantidadOrdenesEnviadas[i] = 0;
		
		totalCantidadOrdenesEnviadas = 0;
		
		cantidadOrdenesIngresadas = new Integer[13];
		for(int i=0; i < cantidadOrdenesIngresadas.length; i++)
			cantidadOrdenesIngresadas[i] = 0;
		
		totalCantidadOrdenesIngresadas = 0;
		
		cantidadOrdenesPrepagadas = new Integer[13];
		for(int i=0; i < cantidadOrdenesPrepagadas.length; i++)
			cantidadOrdenesPrepagadas[i] = 0;
		
		totalCantidadOrdenesPrepagadas = 0;
	}
	
	private void cargarOrdenesPresupuestoDataVectorMedios(
			Vector<Object[]> ordenesMedioDetalleDelPlan, PlanMedioIf planMedio)
			throws GenericBusinessException {
			
		///////////////////////////////////
		//PARA EL PLAN
		//////////////////////////////////
				
		String clienteIdPlan = "";
		String clienteOficinaPlan = "";
		String fechaPlan = "";
		String codigoPlan = "";
		String estadoPlan = "";
		String campanaPlan = "";
		String marcaPlan = "";
		String productoPlan = "";		
		String codigoOrdenPlan = "";
		String estadoOrdenPlan = "";
		String tipoProveedorPlan = "";
		String proveedorPlan = "";
		String creadaPorPlan = "";
		String valorPlan = "";
		
		//clienteId, clienteOficina
		//Todas las ordenes de medios del vector van a tener el mismo cliente oficina
		Long clienteOficinaId = ((OrdenMedioIf)ordenesMedioDetalleDelPlan.get(0)[1]).getClienteOficinaId();
		ClienteOficinaIf clienteOficinaIf = mapaClienteOficina.get(clienteOficinaId);		
		clienteOficinaPlan = clienteOficinaIf.getDescripcion();				
		ClienteIf cliente = mapaCliente.get(clienteOficinaIf.getClienteId());
		clienteIdPlan = cliente.getId().toString();
		
		//fechaPlan		
		fechaPlan = Utilitarios.getFechaCortaUppercase(planMedio.getFechaInicio());
		Timestamp date = planMedio.getFechaInicio();
		
		//codigoPlan
		codigoPlan = planMedio.getCodigo();
		
		//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
		if(planMedio.getEstado().equals("N")){
			estadoPlan = "EN PROCESO";		
		}else if(planMedio.getEstado().equals("P")){
			estadoPlan = "PENDIENTE";		
		}else if(planMedio.getEstado().equals("A")){
			estadoPlan = "APROBADO";		
		}else if(planMedio.getEstado().equals("D")){
			estadoPlan = "PEDIDO";		
		}else if(planMedio.getEstado().equals("F")){
			estadoPlan = "FACTURADO";		
		}else if(planMedio.getEstado().equals("R")){
			estadoPlan = "PREPAGADO";		
		}
		
		//mapa de ordenes
		Map<Long,OrdenMedioIf> mapaOrdenes = new HashMap<Long,OrdenMedioIf>();
				
		//campaña, marca, producto
		Map<Long,Long> campanaProductoVersionIdMapa = new HashMap<Long,Long>();
		//para obtener todos los campaña producto version id sin repetirse.
		for(Object[] ordenMedioDetalleOrdenMedioCabecera : ordenesMedioDetalleDelPlan){
			OrdenMedioDetalleIf ordenMedioDetalle = (OrdenMedioDetalleIf)ordenMedioDetalleOrdenMedioCabecera[0];
			campanaProductoVersionIdMapa.put(ordenMedioDetalle.getCampanaProductoVersionId(), ordenMedioDetalle.getCampanaProductoVersionId());
			
			OrdenMedioIf ordenMedio = (OrdenMedioIf)ordenMedioDetalleOrdenMedioCabecera[1];
			mapaOrdenes.put(ordenMedio.getId(), ordenMedio);
		}
		
		//lleno mapas de marcas(segmentos) y productos
		Map<Long,ProductoClienteIf> productosClienteMapa = new HashMap<Long,ProductoClienteIf>();
		Map<Long,MarcaProductoIf> marcasClienteMapa = new HashMap<Long,MarcaProductoIf>();
		Iterator<Long> campanaProductoVersionIdMapaIt = campanaProductoVersionIdMapa.keySet().iterator();
		while(campanaProductoVersionIdMapaIt.hasNext()){
			Long campanaProductoVersionId = campanaProductoVersionIdMapaIt.next();
			CampanaProductoVersionIf campanaProductoVersion = mapaCampanaProductoVersion.get(campanaProductoVersionId);
			CampanaProductoIf campanaProducto = mapaCampanaProducto.get(campanaProductoVersion.getCampanaProductoId());
			ProductoClienteIf productoCliente = mapaProductoCliente.get(campanaProducto.getProductoClienteId());

			//campana
			campanaPlan = mapaCampana.get(campanaProducto.getCampanaId()).getNombre();
						
			productosClienteMapa.put(productoCliente.getId(), productoCliente);
			MarcaProductoIf marcaProducto = mapaMarcasProducto.get(productoCliente.getMarcaProductoId());
			marcasClienteMapa.put(marcaProducto.getId(), marcaProducto);
		}
		
		//marca
		Iterator<Long> marcasClienteMapaIt = marcasClienteMapa.keySet().iterator();
		while(marcasClienteMapaIt.hasNext()){
			Long marcaId = marcasClienteMapaIt.next();
			String marca = marcasClienteMapa.get(marcaId).getNombre();
			if(marcaPlan.equals("")){
				marcaPlan = marca;
			}else{
				marcaPlan = marcaPlan + "\n" + marca;
			}
		}
		
		//producto
		Iterator<Long> productosClienteMapaIt = productosClienteMapa.keySet().iterator();
		while(productosClienteMapaIt.hasNext()){
			Long productoId = productosClienteMapaIt.next();
			String productoCliente = productosClienteMapa.get(productoId).getNombre();
			if(productoPlan.equals("")){
				productoPlan = productoCliente;
			}else{
				productoPlan = productoPlan + "\n" + productoCliente;
			}
		}
					
		//valor, iva, total
		BigDecimal subtotalPlanMedio = new BigDecimal(0);
		BigDecimal porcentajeDescuentoVentaPlan = new BigDecimal(0); 
		BigDecimal porcentajeComisionAgenciaPlan = new BigDecimal(0); 
		for(Object[] ordenMedioDetalleOrdenMedioCabecera : ordenesMedioDetalleDelPlan){
			OrdenMedioDetalleIf ordenMedioDetalle = (OrdenMedioDetalleIf)ordenMedioDetalleOrdenMedioCabecera[0];
			OrdenMedioIf ordenMedio = (OrdenMedioIf)ordenMedioDetalleOrdenMedioCabecera[1];
			
			subtotalPlanMedio = subtotalPlanMedio.add(ordenMedioDetalle.getValorSubtotal());
			//estos porcentajes son los mismos en todos los detalles
			porcentajeDescuentoVentaPlan = ordenMedio.getPorcentajeDescuentoVenta();
			porcentajeComisionAgenciaPlan = ordenMedio.getPorcentajeComisionAgencia();
		}
		
		//valor	
		BigDecimal descuentoVentaPlan = subtotalPlanMedio.multiply(porcentajeDescuentoVentaPlan.divide(new BigDecimal(100)));
		BigDecimal comisionAgenciaPlan = (subtotalPlanMedio.subtract(descuentoVentaPlan)).multiply(porcentajeComisionAgenciaPlan.divide(new BigDecimal(100)));
		BigDecimal subTotal = subtotalPlanMedio.subtract(descuentoVentaPlan).add(comisionAgenciaPlan);
		
		if(getRbValorNeto().isSelected()){
			valorPlan = formatoDecimal.format(subTotal);
		}else{
			valorPlan = formatoDecimal.format(subtotalPlanMedio);
		}
			
		
		///////////////////////////////////
		//PARA LAS ORDENES
		//////////////////////////////////
						
		//ordenes
		Vector<OrdenMedioIf> ordenesDelPlan = new Vector<OrdenMedioIf>();
		
		/*Collection ordenesColeccion = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planMedio.getId());
		Iterator ordenesIt = ordenesColeccion.iterator();
		while(ordenesIt.hasNext()){
			OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenesIt.next();
			//si no esta anulada
			if(!ordenMedioIf.getEstado().equals("A")){
				ordenesDelPlan.add(ordenMedioIf);					
			}						
		}*/
		
		Iterator mapaOrdenesIt = mapaOrdenes.keySet().iterator();
		while(mapaOrdenesIt.hasNext()){
			Long ordenMedioId = (Long)mapaOrdenesIt.next();
			ordenesDelPlan.add(mapaOrdenes.get(ordenMedioId));
		}
		
		int cantidadDeOrdenes = 0;
		double totalValorOrdenesPlan = 0D;
			
		//si existen ordenes
		if(ordenesDelPlan.size() > 0){
			
			for(OrdenMedioIf ordenDelPlan : ordenesDelPlan){
				
				String codigoOrden = "";
				String estadoOrdenTemp = "";
				String medioOrden = "";
				String proveedorOrden = "";
				String creadoPor = "";
				String valorOrden = "";
				
				//fechaOrden = Utilitarios.getFechaCortaUppercase(ordenDelPlan.getFechaOrden());
				codigoOrden = ordenDelPlan.getCodigo();
				
				//estado de la orden
				if(ordenDelPlan.getEstado().equals("M")){
					estadoOrdenTemp = "EMITIDA";
				}else if(ordenDelPlan.getEstado().equals("D")){
					estadoOrdenTemp = "ORDENADA";
				}else if(ordenDelPlan.getEstado().equals("E")){
					estadoOrdenTemp = "ENVIADA";
				}else if(ordenDelPlan.getEstado().equals("I")){
					estadoOrdenTemp = "INGRESADA";
				}else if(ordenDelPlan.getEstado().equals("R")){
					estadoOrdenTemp = "PREPAGADA";
				}
				
				//medio, proveedor
				//mapas de proveedores y tipos de medios
				Map<Long,ClienteOficinaIf> proveedorIdMapa = new HashMap<Long,ClienteOficinaIf>();
				Map<Long,TipoProveedorIf> tipoMediosMapa = new HashMap<Long,TipoProveedorIf>();
				
				Collection ordenesDetalle = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenDelPlan.getId());
				Iterator ordenesDetalleIt = ordenesDetalle.iterator();
				while(ordenesDetalleIt.hasNext()){
					OrdenMedioDetalleIf ordenMedioDetalle = (OrdenMedioDetalleIf)ordenesDetalleIt.next();
					ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioDetalle.getProductoProveedorId());
					
					ClienteOficinaIf proveedorOficinaIf = mapaClienteOficina.get(producto.getProveedorId());
					proveedorIdMapa.put(producto.getProveedorId(), proveedorOficinaIf);
					ClienteIf proveedorIf = mapaCliente.get(proveedorOficinaIf.getClienteId());
					TipoProveedorIf tipoMedio = mapaTipoProveedor.get(proveedorIf.getTipoproveedorId());
					tipoMediosMapa.put(tipoMedio.getId(), tipoMedio);
				}				
				
				//medio
				Iterator<Long> tipoMediosMapaIt = tipoMediosMapa.keySet().iterator();
				while(tipoMediosMapaIt.hasNext()){
					Long tipoMedioId = tipoMediosMapaIt.next();
					String tipoMedio = tipoMediosMapa.get(tipoMedioId).getNombre();
					if(medioOrden.equals("")){
						medioOrden = tipoMedio;
					}else{
						medioOrden = medioOrden + "\n" + tipoMedio;
					}
				}
				
				//proveedor
				Iterator<Long> proveedoresMapaIt = proveedorIdMapa.keySet().iterator();
				while(proveedoresMapaIt.hasNext()){
					Long proveedorId = proveedoresMapaIt.next();
					String proveedorNombre = proveedorIdMapa.get(proveedorId).getDescripcion();
					if(proveedorOrden.equals("")){
						proveedorOrden = proveedorNombre;
					}
				}
				
				//creado Por
				EmpleadoIf empleadoIf = mapaEmpleado.get(ordenDelPlan.getEmpleadoId());
				creadoPor = empleadoIf.getNombres().split(" ")[0] + " " + empleadoIf.getApellidos().split(" ")[0];
				
				//valor, iva, total
				double valorBruto = ordenDelPlan.getValorSubtotal().doubleValue();
				double descuento = ordenDelPlan.getValorDescuento().doubleValue();
				double valorNeto = valorBruto - descuento;
				double valorOrdenTemp = valorBruto;
				
				if(getRbValorNeto().isSelected()){
					valorOrdenTemp = valorNeto;
				}else{
					valorOrdenTemp = valorBruto;
				}
				
				valorOrden = formatoDecimal.format(valorOrdenTemp);
				
				//total de ordenes
				if(ordenesDelPlan.size() > 0){
					totalValorOrdenesPlan = totalValorOrdenesPlan + valorOrdenTemp;
					cantidadDeOrdenes = cantidadDeOrdenes + 1;
					
					//seteo los totales por orden por estado
					totalesPorOrden(date, ordenDelPlan.getEstado(), valorOrdenTemp);					
				}
				
				//COMPRA
				/*Map buscarCompra = new HashMap();
				buscarCompra.put("tipoOrden", "OM");
				buscarCompra.put("ordenId", ordenDelPlan.getId());
				ArrayList ordenesAsociadas = (ArrayList)SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByQuery(buscarCompra);
				
				CompraIf compra = null;
				if(ordenesAsociadas.size() > 0){
					OrdenAsociadaIf ordenAsociada = (OrdenAsociadaIf)ordenesAsociadas.get(0);
					compra = SessionServiceLocator.getCompraSessionService().getCompra(ordenAsociada.getCompraId());
				}
				if(compra != null){
					medioOrden = Utilitarios.getFechaCortaUppercase(compra.getFecha());
					creadoPor = compra.getPreimpreso();
				}*/
												
				//LINEA POR CADA ORDEN
				OrdenesPresupuestoData ordenesPresupuestoVector = new OrdenesPresupuestoData();
				ordenesPresupuestoVector.setClienteId(clienteIdPlan);
				ordenesPresupuestoVector.setClienteOficina(clienteOficinaPlan);
				ordenesPresupuestoVector.setFecha("");
				ordenesPresupuestoVector.setCodigoPresupuesto(codigoPlan);
				ordenesPresupuestoVector.setEstadoPresupuesto("");
				ordenesPresupuestoVector.setCampana("");
				ordenesPresupuestoVector.setMarca("");
				ordenesPresupuestoVector.setProducto("");
				ordenesPresupuestoVector.setCodigoOrden(codigoOrden);
				ordenesPresupuestoVector.setEstadoOrden(estadoOrdenTemp);
				ordenesPresupuestoVector.setTipoProveedor(medioOrden);
				ordenesPresupuestoVector.setProveedor(proveedorOrden);
				ordenesPresupuestoVector.setCreadoPor(creadoPor);
				ordenesPresupuestoVector.setValor(valorOrden);
				ordenesPresupuestoVector.setValorOrden(valorOrden);
				ordenesPresupuestoVector.setDate(date);
				ordenesPresupuestoVector.setPosicion("1");
				
				ordenesPresupuestoDataVector.add(ordenesPresupuestoVector);
			}
		}
		
		//SI HAY MAS DE 2 ORDENES SALIDAS DE LA PAUTA SE PONE UN TOTAL DE ORDENES
		if(cantidadDeOrdenes > 1){
			//LINEA EXTRA POR CADA FACTURA DONDE VA EL TOTAL
			OrdenesPresupuestoData ordenesPresupuestoVector = new OrdenesPresupuestoData();
			ordenesPresupuestoVector.setClienteId(clienteIdPlan);
			ordenesPresupuestoVector.setClienteOficina(clienteOficinaPlan);
			ordenesPresupuestoVector.setFecha("");
			ordenesPresupuestoVector.setCodigoPresupuesto(codigoPlan);
			ordenesPresupuestoVector.setEstadoPresupuesto("");
			ordenesPresupuestoVector.setCampana("");
			ordenesPresupuestoVector.setMarca("");
			ordenesPresupuestoVector.setProducto("");
			ordenesPresupuestoVector.setCodigoOrden("");
			ordenesPresupuestoVector.setEstadoOrden("");
			ordenesPresupuestoVector.setTipoProveedor("");
			ordenesPresupuestoVector.setProveedor("TOTAL ORDENES: ");
			ordenesPresupuestoVector.setCreadoPor("");
			ordenesPresupuestoVector.setValor(formatoDecimal.format(totalValorOrdenesPlan));
			ordenesPresupuestoVector.setValorOrden("0,0");
			ordenesPresupuestoVector.setDate(date);
			ordenesPresupuestoVector.setPosicion("1");
			ordenesPresupuestoDataVector.add(ordenesPresupuestoVector);			
		}
				
		//LINEA DEL PLAN
		OrdenesPresupuestoData ordenesPresupuestoVector = new OrdenesPresupuestoData();
		ordenesPresupuestoVector.setClienteId(clienteIdPlan);
		ordenesPresupuestoVector.setClienteOficina(clienteOficinaPlan);
		ordenesPresupuestoVector.setFecha(fechaPlan);
		ordenesPresupuestoVector.setCodigoPresupuesto(codigoPlan);
		ordenesPresupuestoVector.setEstadoPresupuesto(estadoPlan);
		ordenesPresupuestoVector.setCampana(campanaPlan);
		ordenesPresupuestoVector.setMarca(marcaPlan);
		ordenesPresupuestoVector.setProducto(productoPlan);
		ordenesPresupuestoVector.setCodigoOrden(codigoOrdenPlan);
		ordenesPresupuestoVector.setEstadoOrden(estadoOrdenPlan);
		ordenesPresupuestoVector.setTipoProveedor(tipoProveedorPlan);
		ordenesPresupuestoVector.setProveedor(proveedorPlan);
		ordenesPresupuestoVector.setCreadoPor(creadaPorPlan);
		ordenesPresupuestoVector.setValor(valorPlan);
		ordenesPresupuestoVector.setValorOrden("0,0");
		ordenesPresupuestoVector.setDate(date);
		ordenesPresupuestoVector.setPosicion("0");
		
		ordenesPresupuestoDataVector.add(ordenesPresupuestoVector);
	}
	
	private void cargarOrdenesPresupuestoDataVectorPresupuestos(
			Vector<Object> presupuestosDetalleDelPresupuesto, PresupuestoIf presupuestoIf)
			throws GenericBusinessException {
			
		///////////////////////////////////
		//PARA EL PRESUPUESTO
		//////////////////////////////////
				
		String clienteIdPlan = "";
		String clienteOficinaPlan = "";
		String fechaPlan = "";
		String codigoPlan = "";
		String estadoPlan = "";
		String campanaPlan = "";
		String marcaPlan = "";
		String productoPlan = "";		
		String codigoOrdenPlan = "";
		String estadoOrdenPlan = "";
		String tipoProveedorPlan = "";
		String proveedorPlan = "";
		String creadaPorPlan = "";
		String valorPlan = "";
		
		//clienteId, clienteOficina
		//Todas las ordenes de medios del vector van a tener el mismo cliente oficina
		Long clienteOficinaId = (Long)((Object[])presupuestosDetalleDelPresupuesto.get(0))[1];
		ClienteOficinaIf clienteOficinaIf = mapaClienteOficina.get(clienteOficinaId);		
		clienteOficinaPlan = clienteOficinaIf.getDescripcion();				
		ClienteIf cliente = mapaCliente.get(clienteOficinaIf.getClienteId());
		clienteIdPlan = cliente.getId().toString();
		
		//fechaPlan		
		fechaPlan = Utilitarios.getFechaCortaUppercase(presupuestoIf.getFecha());
		Timestamp date = presupuestoIf.getFecha();
		
		//codigoPlan
		codigoPlan = presupuestoIf.getCodigo();
		
		//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'
		if(presupuestoIf.getEstado().equals("T")){
			estadoPlan = "COTIZADO";		
		}else if(presupuestoIf.getEstado().equals("P")){
			estadoPlan = "PENDIENTE";		
		}else if(presupuestoIf.getEstado().equals("A")){
			estadoPlan = "APROBADO";		
		}else if(presupuestoIf.getEstado().equals("F")){
			estadoPlan = "FACTURADO";		
		}else if(presupuestoIf.getEstado().equals("R")){
			estadoPlan = "PREPAGADO";		
		}
		
		//mapa de ordenes
		Map<Long,OrdenCompraIf> mapaOrdenes = new HashMap<Long,OrdenCompraIf>();
		
		//campana, segmento, producto
		Map<Long,String> marcaMapa = new HashMap<Long,String>();
		Map<Long,String> productoMapa = new HashMap<Long,String>();
		
		for(int i = 0; i < presupuestosDetalleDelPresupuesto.size(); i++){
			Object[] presupuestoDetalle = (Object[])presupuestosDetalleDelPresupuesto.get(i);
		
			Long campanaId = (Long)presupuestoDetalle[15];
			campanaPlan = mapaCampana.get(campanaId).getNombre();
			
			Long marcaId = (Long)presupuestoDetalle[14];
			Long productoClienteId = (Long)presupuestoDetalle[5];			
			String marca = (String)presupuestoDetalle[13];
			String productoCliente = (String)presupuestoDetalle[4];
								
			marcaMapa.put(marcaId, marca);
			productoMapa.put(productoClienteId, productoCliente);
			
			//mapa de ordenes
			OrdenCompraIf ordenCompra = (OrdenCompraIf)presupuestoDetalle[26];
			mapaOrdenes.put(ordenCompra.getId(), ordenCompra);
		}
		
		//segmento
		Iterator marcaMapaIt = marcaMapa.keySet().iterator();
		while(marcaMapaIt.hasNext()){
			Long marcaId = (Long)marcaMapaIt.next();
			if(marcaPlan.equals("")){
				marcaPlan = marcaMapa.get(marcaId);
			}else{
				marcaPlan = marcaPlan + "\n" + marcaMapa.get(marcaId);
			}
		}
		
		//producto
		Iterator productoMapaIt = productoMapa.keySet().iterator();
		while(productoMapaIt.hasNext()){
			Long productoId = (Long)productoMapaIt.next();
			if(productoPlan.equals("")){
				productoPlan = productoMapa.get(productoId);
			}else{
				productoPlan = productoPlan + "\n" + productoMapa.get(productoId);
			}
		}
		
		//valor, iva, total
		BigDecimal subtotal = presupuestoIf.getValorbruto();
		BigDecimal descuentoVenta = presupuestoIf.getDescuento();
		BigDecimal porcentajeComisionAgenciaPresupuesto = presupuestoIf.getPorcentajeComisionAgencia();
		BigDecimal descuentosVariosPresupuesto = presupuestoIf.getDescuentosVarios();
		BigDecimal descuentoEspecialPresupuesto = presupuestoIf.getDescuentoEspecial();
		
		BigDecimal comisionAgenciaPresupuesto = (subtotal.subtract(descuentoVenta)).multiply(porcentajeComisionAgenciaPresupuesto.divide(new BigDecimal(100)));
		BigDecimal subTotal = subtotal.subtract(descuentoEspecialPresupuesto).subtract(descuentoVenta).subtract(descuentosVariosPresupuesto).add(comisionAgenciaPresupuesto);
		
		valorPlan = formatoDecimal.format(subTotal);
					
		if(getRbValorNeto().isSelected()){
			valorPlan = formatoDecimal.format(subTotal);
		}else{
			valorPlan = formatoDecimal.format(subtotal);
		}			
		
		///////////////////////////////////
		//PARA LAS ORDENES
		//////////////////////////////////
						
		//ordenes
		Vector<OrdenCompraIf> ordenesDelPlan = new Vector<OrdenCompraIf>();
					
		Iterator mapaOrdenesIt = mapaOrdenes.keySet().iterator();
		while(mapaOrdenesIt.hasNext()){
			Long ordenMedioId = (Long)mapaOrdenesIt.next();
			ordenesDelPlan.add(mapaOrdenes.get(ordenMedioId));
		}
		
		int cantidadDeOrdenes = 0;
		double totalValorOrdenesPlan = 0D;
			
		//si existen ordenes
		if(ordenesDelPlan.size() > 0){
			
			for(OrdenCompraIf ordenDelPlan : ordenesDelPlan){
				
				String codigoOrden = "";
				String estadoOrdenTemp = "";
				String medioOrden = "";
				String proveedorOrden = "";
				String creadoPor = "";
				String valorOrden = "";
				
				//fechaOrden = Utilitarios.getFechaCortaUppercase(ordenDelPlan.getFechaOrden());
				codigoOrden = ordenDelPlan.getCodigo();
				
				//estado de la orden
				if(ordenDelPlan.getEstado().equals("M")){
					estadoOrdenTemp = "EMITIDA";
				}else if(ordenDelPlan.getEstado().equals("D")){
					estadoOrdenTemp = "ORDENADA";
				}else if(ordenDelPlan.getEstado().equals("E")){
					estadoOrdenTemp = "ENVIADA";
				}else if(ordenDelPlan.getEstado().equals("G")){
					estadoOrdenTemp = "INGRESADA";
				}else if(ordenDelPlan.getEstado().equals("R")){
					estadoOrdenTemp = "PREPAGADA";
				}
				
				//medio, proveedor
				//mapas de proveedores y tipos de medios
				Map<Long,ClienteOficinaIf> proveedorIdMapa = new HashMap<Long,ClienteOficinaIf>();
				Map<Long,TipoProveedorIf> tipoMediosMapa = new HashMap<Long,TipoProveedorIf>();
				
				Collection ordenesDetalle = SessionServiceLocator.getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByOrdencompraId(ordenDelPlan.getId());
				Iterator ordenesDetalleIt = ordenesDetalle.iterator();
				while(ordenesDetalleIt.hasNext()){
					OrdenCompraDetalleIf ordenCompraDetalle = (OrdenCompraDetalleIf)ordenesDetalleIt.next();
					ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(ordenCompraDetalle.getProductoId());
					
					ClienteOficinaIf proveedorOficinaIf = mapaClienteOficina.get(producto.getProveedorId());
					proveedorIdMapa.put(producto.getProveedorId(), proveedorOficinaIf);
					ClienteIf proveedorIf = mapaCliente.get(proveedorOficinaIf.getClienteId());
					TipoProveedorIf tipoMedio = mapaTipoProveedor.get(proveedorIf.getTipoproveedorId());
					tipoMediosMapa.put(tipoMedio.getId(), tipoMedio);
				}
								
				//medio
				Iterator<Long> tipoMediosMapaIt = tipoMediosMapa.keySet().iterator();
				while(tipoMediosMapaIt.hasNext()){
					Long tipoMedioId = tipoMediosMapaIt.next();
					String tipoMedio = tipoMediosMapa.get(tipoMedioId).getNombre();
					if(medioOrden.equals("")){
						medioOrden = tipoMedio;
					}else{
						medioOrden = medioOrden + "\n" + tipoMedio;
					}
				}
				
				//proveedor
				Iterator<Long> proveedoresMapaIt = proveedorIdMapa.keySet().iterator();
				while(proveedoresMapaIt.hasNext()){
					Long proveedorId = proveedoresMapaIt.next();
					String proveedorNombre = proveedorIdMapa.get(proveedorId).getDescripcion();
					if(proveedorOrden.equals("")){
						proveedorOrden = proveedorNombre;
					}
				}
				
				//creado Por
				EmpleadoIf empleadoIf = mapaEmpleado.get(ordenDelPlan.getEmpleadoId());
				creadoPor = empleadoIf.getNombres().split(" ")[0] + " " + empleadoIf.getApellidos().split(" ")[0];
				
				//valor, iva, total
				double valorBruto = ordenDelPlan.getValor().doubleValue();
				double porcentajeDescuentoEspecial = ordenDelPlan.getPorcentajeDescuentoEspecial().doubleValue() / 100D;
				double descuentoEspecial = valorBruto * porcentajeDescuentoEspecial;
				double descuentoAgencia = ordenDelPlan.getDescuentoAgenciaCompra().doubleValue();
				double porcentajeDescuentosVarios = ordenDelPlan.getPorcentajeDescuentosVariosCompra().doubleValue() / 100D;
				double descuentosVarios = (valorBruto - descuentoEspecial) * porcentajeDescuentosVarios;
				double valorNeto = valorBruto - descuentoEspecial - descuentoAgencia - descuentosVarios;
								
				double valorOrdenTemp = valorBruto;
				
				if(getRbValorNeto().isSelected()){
					valorOrdenTemp = valorNeto;
				}else{
					valorOrdenTemp = valorBruto;
				}
				
				valorOrden = formatoDecimal.format(valorOrdenTemp);
				
				//total de ordenes
				if(ordenesDelPlan.size() > 0){
					totalValorOrdenesPlan = totalValorOrdenesPlan + valorOrdenTemp;
					cantidadDeOrdenes = cantidadDeOrdenes + 1;
					
					//seteo los totales por orden por estado
					totalesPorOrden(date, ordenDelPlan.getEstado(), valorOrdenTemp);					
				}
				
				//COMPRA
				/*Map buscarCompra = new HashMap();
				buscarCompra.put("tipoOrden", "OC");
				buscarCompra.put("ordenId", ordenDelPlan.getId());
				ArrayList ordenesAsociadas = (ArrayList)SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByQuery(buscarCompra);
				
				CompraIf compra = null;
				if(ordenesAsociadas.size() > 0){
					OrdenAsociadaIf ordenAsociada = (OrdenAsociadaIf)ordenesAsociadas.get(0);
					compra = SessionServiceLocator.getCompraSessionService().getCompra(ordenAsociada.getCompraId());
				}
				
				if(compra != null){
					medioOrden = Utilitarios.getFechaCortaUppercase(compra.getFecha());
					creadoPor = compra.getPreimpreso();
				}*/
												
				//LINEA POR CADA ORDEN
				OrdenesPresupuestoData ordenesPresupuestoVector = new OrdenesPresupuestoData();
				ordenesPresupuestoVector.setClienteId(clienteIdPlan);
				ordenesPresupuestoVector.setClienteOficina(clienteOficinaPlan);
				ordenesPresupuestoVector.setFecha("");
				ordenesPresupuestoVector.setCodigoPresupuesto(codigoPlan);
				ordenesPresupuestoVector.setEstadoPresupuesto("");
				ordenesPresupuestoVector.setCampana("");
				ordenesPresupuestoVector.setMarca("");
				ordenesPresupuestoVector.setProducto("");
				ordenesPresupuestoVector.setCodigoOrden(codigoOrden);
				ordenesPresupuestoVector.setEstadoOrden(estadoOrdenTemp);
				ordenesPresupuestoVector.setTipoProveedor(medioOrden);
				ordenesPresupuestoVector.setProveedor(proveedorOrden);
				ordenesPresupuestoVector.setCreadoPor(creadoPor);
				ordenesPresupuestoVector.setValor(valorOrden);
				ordenesPresupuestoVector.setValorOrden(valorOrden);
				ordenesPresupuestoVector.setDate(date);
				ordenesPresupuestoVector.setPosicion("1");
				
				ordenesPresupuestoDataVector.add(ordenesPresupuestoVector);
			}
		}
		
		//SI HAY MAS DE 2 ORDENES SALIDAS DE LA PAUTA SE PONE UN TOTAL DE ORDENES
		if(cantidadDeOrdenes > 1){
			//LINEA EXTRA POR CADA FACTURA DONDE VA EL TOTAL
			OrdenesPresupuestoData ordenesPresupuestoVector = new OrdenesPresupuestoData();
			ordenesPresupuestoVector.setClienteId(clienteIdPlan);
			ordenesPresupuestoVector.setClienteOficina(clienteOficinaPlan);
			ordenesPresupuestoVector.setFecha("");
			ordenesPresupuestoVector.setCodigoPresupuesto(codigoPlan);
			ordenesPresupuestoVector.setEstadoPresupuesto("");
			ordenesPresupuestoVector.setCampana("");
			ordenesPresupuestoVector.setMarca("");
			ordenesPresupuestoVector.setProducto("");
			ordenesPresupuestoVector.setCodigoOrden("");
			ordenesPresupuestoVector.setEstadoOrden("");
			ordenesPresupuestoVector.setTipoProveedor("");
			ordenesPresupuestoVector.setProveedor("TOTAL ORDENES: ");
			ordenesPresupuestoVector.setCreadoPor("");
			ordenesPresupuestoVector.setValor(formatoDecimal.format(totalValorOrdenesPlan));
			ordenesPresupuestoVector.setValorOrden("0,0");
			ordenesPresupuestoVector.setDate(date);
			ordenesPresupuestoVector.setPosicion("1");
			ordenesPresupuestoDataVector.add(ordenesPresupuestoVector);			
		}
				
		//LINEA DEL PLAN
		OrdenesPresupuestoData ordenesPresupuestoVector = new OrdenesPresupuestoData();
		ordenesPresupuestoVector.setClienteId(clienteIdPlan);
		ordenesPresupuestoVector.setClienteOficina(clienteOficinaPlan);
		ordenesPresupuestoVector.setFecha(fechaPlan);
		ordenesPresupuestoVector.setCodigoPresupuesto(codigoPlan);
		ordenesPresupuestoVector.setEstadoPresupuesto(estadoPlan);
		ordenesPresupuestoVector.setCampana(campanaPlan);
		ordenesPresupuestoVector.setMarca(marcaPlan);
		ordenesPresupuestoVector.setProducto(productoPlan);
		ordenesPresupuestoVector.setCodigoOrden(codigoOrdenPlan);
		ordenesPresupuestoVector.setEstadoOrden(estadoOrdenPlan);
		ordenesPresupuestoVector.setTipoProveedor(tipoProveedorPlan);
		ordenesPresupuestoVector.setProveedor(proveedorPlan);
		ordenesPresupuestoVector.setCreadoPor(creadaPorPlan);
		ordenesPresupuestoVector.setValor(valorPlan);
		ordenesPresupuestoVector.setValorOrden("0,0");
		ordenesPresupuestoVector.setDate(date);
		ordenesPresupuestoVector.setPosicion("0");
		
		ordenesPresupuestoDataVector.add(ordenesPresupuestoVector);
	}

	private void totalesPorOrden(Timestamp date, String estadoOrden,
			double valorNeto) {
		
		//seteo el total de ordenes en el mes correspondiente
		if(ordenes[date.getMonth()] == null){
			ordenes[date.getMonth()] = valorNeto;
		}else{
			ordenes[date.getMonth()] = ordenes[date.getMonth()] + valorNeto;
		}
		
		//total de ordenes
		totalOrdenes = totalOrdenes + valorNeto;
		
		//Estado Emitido
		if(estadoOrden.equals("M")){
			if(ordenesEmitidas[date.getMonth()] == null){
				ordenesEmitidas[date.getMonth()] = valorNeto;
			}else{
				ordenesEmitidas[date.getMonth()] = ordenesEmitidas[date.getMonth()] + valorNeto;
			}
			
			totalOrdenesEmitidas = totalOrdenesEmitidas + valorNeto;
		}
		
		//Estado Ordenado
		else if(estadoOrden.equals("D")){
			if(ordenesOrdenadas[date.getMonth()] == null){
				ordenesOrdenadas[date.getMonth()] = valorNeto;
			}else{
				ordenesOrdenadas[date.getMonth()] = ordenesOrdenadas[date.getMonth()] + valorNeto;
			}
			
			totalOrdenesOrdenadas = totalOrdenesOrdenadas + valorNeto;
		}
		
		//Estado Enviado
		else if(estadoOrden.equals("E")){
			if(ordenesEnviadas[date.getMonth()] == null){
				ordenesEnviadas[date.getMonth()] = valorNeto;
			}else{
				ordenesEnviadas[date.getMonth()] = ordenesEnviadas[date.getMonth()] + valorNeto;
			}
			
			totalOrdenesEnviadas = totalOrdenesEnviadas + valorNeto;
		}
		
		//Estado Ingresado
		else if(estadoOrden.equals("I") || estadoOrden.equals("G")){
			if(ordenesIngresadas[date.getMonth()] == null){
				ordenesIngresadas[date.getMonth()] = valorNeto;
			}else{
				ordenesIngresadas[date.getMonth()] = ordenesIngresadas[date.getMonth()] + valorNeto;
			}
			
			totalOrdenesIngresadas = totalOrdenesIngresadas + valorNeto;
		}
		
		//Estado Prepagado
		else if(estadoOrden.equals("R")){
			if(ordenesPrepagadas[date.getMonth()] == null){
				ordenesPrepagadas[date.getMonth()] = valorNeto;
			}else{
				ordenesPrepagadas[date.getMonth()] = ordenesPrepagadas[date.getMonth()] + valorNeto;
			}
			
			totalOrdenesPrepagadas = totalOrdenesPrepagadas + valorNeto;
		}
		
		
		//seteo el total de cantidad de ordenes en el mes correspondiente
		if(cantidadOrdenes[date.getMonth()] == null){
			cantidadOrdenes[date.getMonth()] = 1;
		}else{
			cantidadOrdenes[date.getMonth()] = cantidadOrdenes[date.getMonth()] + 1;
		}
		//total cantidad de ordenes
		totalCantidadOrdenes = totalCantidadOrdenes + 1;
		
		//Estado Emitido
		if(estadoOrden.equals("M")){
			if(cantidadOrdenesEmitidas[date.getMonth()] == null){
				cantidadOrdenesEmitidas[date.getMonth()] = 1;
			}else{
				cantidadOrdenesEmitidas[date.getMonth()] = cantidadOrdenesEmitidas[date.getMonth()] + 1;
			}
			
			totalCantidadOrdenesEmitidas = totalCantidadOrdenesEmitidas + 1;
		}
		
		//Estado Ordenado
		else if(estadoOrden.equals("D")){
			if(cantidadOrdenesOrdenadas[date.getMonth()] == null){
				cantidadOrdenesOrdenadas[date.getMonth()] = 1;
			}else{
				cantidadOrdenesOrdenadas[date.getMonth()] = cantidadOrdenesOrdenadas[date.getMonth()] + 1;
			}
			
			totalCantidadOrdenesOrdenadas = totalCantidadOrdenesOrdenadas + 1;
		}
		
		//Estado Enviado
		else if(estadoOrden.equals("E")){
			if(cantidadOrdenesEnviadas[date.getMonth()] == null){
				cantidadOrdenesEnviadas[date.getMonth()] = 1;
			}else{
				cantidadOrdenesEnviadas[date.getMonth()] = cantidadOrdenesEnviadas[date.getMonth()] + 1;
			}
			
			totalCantidadOrdenesEnviadas = totalCantidadOrdenesEnviadas + 1;
		}
		
		//Estado Ingresado
		else if(estadoOrden.equals("I") || estadoOrden.equals("G")){
			if(cantidadOrdenesIngresadas[date.getMonth()] == null){
				cantidadOrdenesIngresadas[date.getMonth()] = 1;
			}else{
				cantidadOrdenesIngresadas[date.getMonth()] = cantidadOrdenesIngresadas[date.getMonth()] + 1;
			}
			
			totalCantidadOrdenesIngresadas = totalCantidadOrdenesIngresadas + 1;
		}
		
		//Estado Prepagado
		else if(estadoOrden.equals("R")){
			if(cantidadOrdenesPrepagadas[date.getMonth()] == null){
				cantidadOrdenesPrepagadas[date.getMonth()] = 1;
			}else{
				cantidadOrdenesPrepagadas[date.getMonth()] = cantidadOrdenesPrepagadas[date.getMonth()] + 1;
			}
			
			totalCantidadOrdenesPrepagadas = totalCantidadOrdenesPrepagadas + 1;
		}
	}
	
	public void llenarVectorOrdenesEstadoData(){		
		try {
			reiniciarValores();
						
			//ORDENES DE MEDIO DETALLE
			Map<Long,Vector<Object[]>> mapaPlanMedioOrdenesMedioDetalleVector = new HashMap<Long,Vector<Object[]>>(); 
			Vector<Object[]> ordenesMedioDetalleDelPlan = new Vector<Object[]>();
			Map<Long,PlanMedioIf> mapaPlanMedio = new HashMap<Long,PlanMedioIf>();
			
			Iterator ordenesMedioDetalleIt = ordenesMedioDetalle.iterator();
			
			//Agrupo en un mapa los planes con su vector de ordenes medio detalle
			while(ordenesMedioDetalleIt.hasNext()){
				Object[] ordenesMedioDetalleObj = (Object[])ordenesMedioDetalleIt.next();
				OrdenMedioDetalleIf ordenMedioDetalle = (OrdenMedioDetalleIf)ordenesMedioDetalleObj[1];
				OrdenMedioIf ordenMedio = (OrdenMedioIf)ordenesMedioDetalleObj[0];
				PlanMedioIf planMedio = (PlanMedioIf)ordenesMedioDetalleObj[3];
				mapaPlanMedio.put(planMedio.getId(), planMedio);
												
				if(mapaPlanMedioOrdenesMedioDetalleVector.get(planMedio.getId()) == null){
					ordenesMedioDetalleDelPlan = new Vector<Object[]>();
					Object[] ordenDetalleOrdenCabecera = new Object[2];
					ordenDetalleOrdenCabecera[0] = ordenMedioDetalle;
					ordenDetalleOrdenCabecera[1] = ordenMedio;
					ordenesMedioDetalleDelPlan.add(ordenDetalleOrdenCabecera);
					mapaPlanMedioOrdenesMedioDetalleVector.put(planMedio.getId(), ordenesMedioDetalleDelPlan);
				}else{
					ordenesMedioDetalleDelPlan = mapaPlanMedioOrdenesMedioDetalleVector.get(planMedio.getId());
					Object[] ordenDetalleOrdenCabecera = new Object[2];
					ordenDetalleOrdenCabecera[0] = ordenMedioDetalle;
					ordenDetalleOrdenCabecera[1] = ordenMedio;
					ordenesMedioDetalleDelPlan.add(ordenDetalleOrdenCabecera);
					mapaPlanMedioOrdenesMedioDetalleVector.put(planMedio.getId(), ordenesMedioDetalleDelPlan);
				}				
			}
			
			Iterator mapaPlanMedioOrdenesMedioDetalleVectorIt =  mapaPlanMedioOrdenesMedioDetalleVector.keySet().iterator();
			while(mapaPlanMedioOrdenesMedioDetalleVectorIt.hasNext()){
				Long planMedioId = (Long)mapaPlanMedioOrdenesMedioDetalleVectorIt.next();
				PlanMedioIf planMedio = mapaPlanMedio.get(planMedioId);
				Vector<Object[]> ordenesMedioDetallePlan = mapaPlanMedioOrdenesMedioDetalleVector.get(planMedioId);
				
				//Lleno la información de cada Plan
				//if(planMedio.getCodigo().equals("2012-00474")){
					cargarOrdenesPresupuestoDataVectorMedios(ordenesMedioDetallePlan, planMedio);			
				//}
			}
			
			
			//PRESUPUESTOS DETALLE
			Map<Long,Vector<Object>> mapaPresupuestoPresupuestoDetalleVector = new HashMap<Long,Vector<Object>>(); 
			Vector<Object> presupuestosDetalleDelPresupuesto = new Vector<Object>();
			Map<Long,PresupuestoIf> mapaPresupuesto = new HashMap<Long,PresupuestoIf>();
			
			//Agrupo en un mapa los planes con su vector de ordenes medio detalle
			Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
			while(presupuestosDetalleIt.hasNext()){
				Object[] presupuestoDetalle = (Object[])presupuestosDetalleIt.next();
				Long presupuestoId = (Long)presupuestoDetalle[16];
				PresupuestoIf presupuesto = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoId);
				mapaPresupuesto.put(presupuesto.getId(), presupuesto);
				
				if(mapaPresupuestoPresupuestoDetalleVector.get(presupuesto.getId()) == null){
					presupuestosDetalleDelPresupuesto = new Vector<Object>();
					presupuestosDetalleDelPresupuesto.add(presupuestoDetalle);
					mapaPresupuestoPresupuestoDetalleVector.put(presupuesto.getId(), presupuestosDetalleDelPresupuesto);
				}else{
					presupuestosDetalleDelPresupuesto = mapaPresupuestoPresupuestoDetalleVector.get(presupuesto.getId());
					presupuestosDetalleDelPresupuesto.add(presupuestoDetalle);
					mapaPresupuestoPresupuestoDetalleVector.put(presupuesto.getId(), presupuestosDetalleDelPresupuesto);
				}				
			}
			
			Iterator mapaPresupuestoPresupuestoDetalleVectorIt =  mapaPresupuestoPresupuestoDetalleVector.keySet().iterator();
			while(mapaPresupuestoPresupuestoDetalleVectorIt.hasNext()){
				Long presupuestoId = (Long)mapaPresupuestoPresupuestoDetalleVectorIt.next();
				PresupuestoIf presupuestoIf = mapaPresupuesto.get(presupuestoId);
				Vector<Object> presupuestosDetallePresupuesto = mapaPresupuestoPresupuestoDetalleVector.get(presupuestoId);
				
				//Lleno la información de cada Presupuesto
				cargarOrdenesPresupuestoDataVectorPresupuestos(presupuestosDetallePresupuesto, presupuestoIf);							
			}
			
			//seteo el total de valores y cantidades en el arreglo correspondiente
			ordenes[12] = totalOrdenes;
			cantidadOrdenes[12] = totalCantidadOrdenes;
			
			ordenesEmitidas[12] = totalOrdenesEmitidas;
			cantidadOrdenesEmitidas[12] = totalCantidadOrdenesEmitidas;
			ordenesOrdenadas[12] = totalOrdenesOrdenadas;
			cantidadOrdenesOrdenadas[12] = totalCantidadOrdenesOrdenadas;
			ordenesEnviadas[12] = totalOrdenesEnviadas;
			cantidadOrdenesEnviadas[12] = totalCantidadOrdenesEnviadas;
			ordenesIngresadas[12] = totalOrdenesIngresadas;
			cantidadOrdenesIngresadas[12] = totalCantidadOrdenesIngresadas;
			ordenesPrepagadas[12] = totalOrdenesPrepagadas;
			cantidadOrdenesPrepagadas[12] = totalCantidadOrdenesPrepagadas;
			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	Comparator<OrdenesPresupuestoData> ordenadorOrdenesPresupuestoDataPorClienteOficina = new Comparator<OrdenesPresupuestoData>(){
		public int compare(OrdenesPresupuestoData o1, OrdenesPresupuestoData o2) {
			return o1.getClienteOficina().compareTo(o2.getClienteOficina());			
		}		
	};
	
	Comparator<OrdenesPresupuestoData> ordenadorOrdenesPresupuestoDataPorCodigoPresupuesto = new Comparator<OrdenesPresupuestoData>(){
		public int compare(OrdenesPresupuestoData o1, OrdenesPresupuestoData o2) {
			return o1.getCodigoPresupuesto().compareTo(o2.getCodigoPresupuesto());			
		}		
	};
	
	Comparator<OrdenesPresupuestoData> ordenadorOrdenesPresupuestoDataPorPosicion = new Comparator<OrdenesPresupuestoData>(){
		public int compare(OrdenesPresupuestoData o1, OrdenesPresupuestoData o2) {
			return o1.getPosicion().compareTo(o2.getPosicion());			
		}		
	};
	
	Comparator<OrdenesPresupuestoData> ordenadorOrdenesPresupuestoDataPorDate = new Comparator<OrdenesPresupuestoData>(){
		public int compare(OrdenesPresupuestoData o1, OrdenesPresupuestoData o2) {
			return o1.getDate().compareTo(o2.getDate());			
		}		
	};	
	
	private void cargarTabla() {
		cleanTable();
		//resetearValores();
		ordenesMedioDetalle = new ArrayList<Object[]>();
		presupuestosDetalle = new ArrayList();
		obtenerOrdenesMedioPresupuestosDetalle();
		llenarVectorOrdenesEstadoData();
		
		//ordenar vector
		Collections.sort((ArrayList)ordenesPresupuestoDataVector,ordenadorOrdenesPresupuestoDataPorPosicion);
		Collections.sort((ArrayList)ordenesPresupuestoDataVector,ordenadorOrdenesPresupuestoDataPorCodigoPresupuesto);
		Collections.sort((ArrayList)ordenesPresupuestoDataVector,ordenadorOrdenesPresupuestoDataPorDate);
		Collections.sort((ArrayList)ordenesPresupuestoDataVector,ordenadorOrdenesPresupuestoDataPorClienteOficina);
		
		for(OrdenesPresupuestoData ordenesPresupuestoData : ordenesPresupuestoDataVector){
			tableModel = (DefaultTableModel) getTblOrdenes().getModel();
			Vector<String> fila = new Vector<String>();			
			agregarColumnasTabla(ordenesPresupuestoData, fila);
			tableModel.addRow(fila);
		}
		
		if(ordenesPresupuestoDataVector.size() == 0){
			SpiritAlert.createAlert("No existen datos que presentar.", SpiritAlert.INFORMATION);
		}
		
		report();
	}
	
	private void agregarColumnasTabla(OrdenesPresupuestoData ordenesPresupuestoData, Vector<String> fila){
		fila.add(ordenesPresupuestoData.getFecha());
		fila.add(ordenesPresupuestoData.getClienteOficina());
		fila.add(ordenesPresupuestoData.getCodigoPresupuesto());
		fila.add(ordenesPresupuestoData.getEstadoPresupuesto());
		fila.add(ordenesPresupuestoData.getCampana());
		fila.add(ordenesPresupuestoData.getMarca());
		fila.add(ordenesPresupuestoData.getProducto());
		fila.add(ordenesPresupuestoData.getCodigoOrden());
		fila.add(ordenesPresupuestoData.getEstadoOrden());
		fila.add(ordenesPresupuestoData.getTipoProveedor());
		fila.add(ordenesPresupuestoData.getProveedor());
		fila.add(ordenesPresupuestoData.getCreadoPor());
		fila.add(ordenesPresupuestoData.getValor());
	}
	
	public void initListeners(){
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbFiltrarCodigoPresupuesto().isSelected() && getTxtPresupuesto().getText().equals("")){
					SpiritAlert.createAlert("Debe ingresar el Código del Presupuesto.", SpiritAlert.WARNING);
					getTxtPresupuesto().grabFocus();
				}else{
					cargarTabla();
					/*Iterator problemasIt = problemas.keySet().iterator();
					while(problemasIt.hasNext()){
						String problema = (String)problemasIt.next();
						System.out.println("PROBLEMA: " + problema);
					}*/
				}
			}
		});
		
		getCmbMarca().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbMarca().getSelectedItem() != null && !getCmbMarca().getSelectedItem().equals(TODOS)){
					marcaProductoIf = (MarcaProductoIf) getCmbMarca().getSelectedItem();	
					if (productoClienteIf != null){
						getTxtProducto().setText(null);
						getCbTodosProducto().setSelected(true);
						productoClienteIf = null;
					}
				}else{
					marcaProductoIf = null;
				}
			}
		});
		
		getBtnProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(clienteOficinaIf != null){
					String tituloVentanaBusqueda = "Productos del Cliente";
					Map mapa = new LinkedHashMap();
					Long idMarcaProducto = 0L;
					
					if (marcaProductoIf != null){
						idMarcaProducto = marcaProductoIf.getId();
					}
					
					ClienteIf clienteIf = mapaCliente.get(clienteOficinaIf.getClienteId());
					
					productoClienteCriteria = new ProductoClienteCriteria(tituloVentanaBusqueda,clienteIf.getId(),idMarcaProducto);
					productoClienteCriteria.setQueryBuilded(mapa);
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.addElement(70);
					anchoColumnas.addElement(200);
					anchoColumnas.addElement(80);
				    JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), productoClienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
					if (popupFinder.getElementoSeleccionado() != null) {
						productoClienteIf = (ProductoClienteIf) popupFinder.getElementoSeleccionado();
						getTxtProducto().setText(productoClienteIf.getNombre());
						getCbTodosProducto().setSelected(false);
					}					
				}else{
					SpiritAlert.createAlert("Para buscar por Producto, primero debe seleccionar un Cliente.", SpiritAlert.INFORMATION);
				}				
			}
		});
		
		getCbTodosProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbTodosProducto().isSelected()){
					productoClienteIf = null;
					getTxtProducto().setText("");
				}
			}
		});
		
		getCmbTipoProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoProveedor().getSelectedItem() != null){
					if(getCmbTipoProveedor().getSelectedItem().equals("MEDIOS")){
						tipoProveedor = "MEDIOS";
					}else if(getCmbTipoProveedor().getSelectedItem().equals("PRODUCCION")){
						tipoProveedor = "PRODUCCION";
					}else{
						tipoProveedor = TODOS;
					}
				}
				else{
					tipoProveedor = TODOS;
				}
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
					getCbTodosClientes().setSelected(false);					
					clienteOficinaIf = null;
					getTxtClienteOficina().setText("");										
				}			
			}
		});
		
		getCbTodosClientes().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbTodosClientes().isSelected() || getBtnCliente().isEnabled()){
					if (clienteIf != null){
						clienteIf = null;
						getTxtCliente().setText(null);						
					}
					if (clienteOficinaIf != null || getBtnClienteOficina().isEnabled()){
						clienteOficinaIf = null;
						getTxtClienteOficina().setText(null);	
						getCbTodosClientesOficina().setSelected(true);
					}			
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
							getCbTodosClientes().setSelected(false);
							} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					getTxtClienteOficina().setText(clienteOficinaIf.getCodigo() + " - "+ clienteOficinaIf.getDescripcion());
					getCbTodosClientesOficina().setSelected(false);
				}
			}		
		});
		
		getCbTodosClientesOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodosClientesOficina().isSelected()){
					clienteOficinaIf = null;
					getTxtClienteOficina().setText("");
					
					if(marcaProductoIf != null || getCmbMarca().isEnabled()){
						getCmbMarca().removeAllItems();
						getCmbMarca().addItem(TODOS);
						getCmbMarca().setSelectedItem(TODOS);
						getCmbMarca().setEnabled(false);
						marcaProductoIf = null;
					}
					//END 26 JULIO
					if(productoClienteIf != null || getBtnProducto().isEnabled()){
						productoClienteIf = null;
						getTxtProducto().setText("");	
						getBtnProducto().setEnabled(false);
						getCbTodosProducto().setSelected(true);
					}
				}
			}
		});
		
		getBtnCampana().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(clienteOficinaIf != null){
					campanaCriteria = new CampanaCriteria("Campañas", clienteOficinaIf.getClienteId());
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.add(80);
					anchoColumnas.add(260);
					anchoColumnas.add(260);
					anchoColumnas.add(50);
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), campanaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
					if ( popupFinder.getElementoSeleccionado()!=null ){
						campanaIf = (CampanaIf) popupFinder.getElementoSeleccionado();
						getTxtCampana().setText(campanaIf.getCodigo() + " - " + campanaIf.getNombre());
						getCbTodosCampana().setSelected(false);
					}
				}else{
					SpiritAlert.createAlert("Para buscar por Campaña, primero debe seleccionar un Cliente.", SpiritAlert.INFORMATION);
				}				
			}
		});
		
		getCbTodosCampana().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodosCampana().isSelected()){
					campanaIf = null;
					getTxtCampana().setText("");
				}
			}
		});
		
		getBtnProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idTipoProveedor = 0L;
				String tituloVentanaBusqueda = "Proveedores";
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
					getTxtProveedor().setText(medioIf.getNombreLegal());
					getCbTodosProveedores().setSelected(false);
					
					medioOficinaIf = null;
					getTxtProveedorOficina().setText("");										
										
				}			
			}			
		});
		
		getCbTodosProveedores().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodosProveedores().isSelected()){
					medioIf = null;
					getTxtProveedor().setText("");
				}
			}
		});
		
		getBtnProveedorOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				Long idTipoProveedor = 0L;
				
				String tituloVentanaBusqueda = "Oficinas de Proveedores";
									
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
							getTxtProveedor().setText(medioIf.getNombreLegal());
							getCbTodosProveedores().setSelected(false);
							
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					getTxtProveedorOficina().setText(medioOficinaIf.getDescripcion());
					getCbTodosProveedorOficina().setSelected(false);
				}
			}		
		});
				
		getCbTodosProveedorOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodosProveedorOficina().isSelected()){
					medioOficinaIf = null;
					getTxtProveedorOficina().setText("");
				}
			}
		});
		
		getBtnCreadoPor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				creadoPor = null;
				EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria("Empleados",Parametros.getIdEmpresa());
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					creadoPor = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getTxtCreadoPor().setText(creadoPor.getNombres() + " " + creadoPor.getApellidos());
					getCbTodosCreadoPor().setSelected(false);
				}
			}
		});
		
		getCbTodosCreadoPor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodosCreadoPor().isSelected()){
					creadoPor = null;
					getTxtCreadoPor().setText("");
				}
			}
		});
		
		getCmbEstadoPresupuesto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbEstadoPresupuesto().getSelectedItem() != null){
					if(getCmbEstadoPresupuesto().getSelectedItem().equals("COTIZADO")){
						estadoPresupuesto = "COTIZADO";
					}else if(getCmbEstadoPresupuesto().getSelectedItem().equals("APROBADO")){
						estadoPresupuesto = "APROBADO";
					}else if(getCmbEstadoPresupuesto().getSelectedItem().equals("FACTURADO")){
						estadoPresupuesto = "FACTURADO";
					}else if(getCmbEstadoPresupuesto().getSelectedItem().equals("PREPAGADO")){
						estadoPresupuesto = "PREPAGADO";
					}else{
						estadoPresupuesto = TODOS;
					}
				}
				else{
					estadoPresupuesto = TODOS;
				}
			}
		});
		
		getCmbEstadoOrden().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbEstadoOrden().getSelectedItem() != null){
					if(getCmbEstadoOrden().getSelectedItem().equals("EMITIDA")){
						estadoOrden = "EMITIDA";
					}else if(getCmbEstadoOrden().getSelectedItem().equals("ORDENADA")){
						estadoOrden = "ORDENADA";
					}else if(getCmbEstadoOrden().getSelectedItem().equals("ENVIADA")){
						estadoOrden = "ENVIADA";
					}else if(getCmbEstadoOrden().getSelectedItem().equals("INGRESADA")){
						estadoOrden = "INGRESADA";
					}else if(getCmbEstadoOrden().getSelectedItem().equals("PREPAGADA")){
						estadoOrden = "PREPAGADA";
					}else{
						estadoOrden = TODOS;
					}
				}
				else{
					estadoOrden = TODOS;
				}
			}
		});
		
		getCmbSubtipoProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbSubtipoProveedor().getSelectedItem() != null && !getCmbSubtipoProveedor().getSelectedItem().equals(TODOS)){
					tipoProveedorIf = (TipoProveedorIf) getCmbSubtipoProveedor().getSelectedItem();
					if (medioIf != null){
						getTxtProveedor().setText("");
						getCbTodosProveedores().setSelected(true);
						medioIf = null;
					}
					if (medioOficinaIf != null){
						getTxtProveedorOficina().setText("");
						getCbTodosProveedorOficina().setSelected(true);
						medioOficinaIf = null;
					}
				}
				else{
					tipoProveedorIf = null;
				}
			}
		});
	}
	
	public void cargarComboMarcas(){
		try {
			List marcas = new ArrayList();
						
			ClienteIf clienteIf = mapaCliente.get(clienteOficinaIf.getClienteId());
			
			Iterator it = SessionServiceLocator.getMarcaProductoSessionService().findMarcaProductoByClienteId(clienteIf.getId()).iterator();
			while (it.hasNext()) {
				MarcaProductoIf marcaProducto = (MarcaProductoIf) it.next();
				marcas.add(marcaProducto);
			}
			
			marcas.add(TODOS);
			getCmbMarca().removeAllItems();
			refreshCombo(getCmbMarca(),marcas);
			getCmbMarca().setSelectedItem(TODOS);
			getCmbMarca().setEnabled(true);
						
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	public void clean() {
		getRbValorBruto().setSelected(true);
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		getCmbEstadoPresupuesto().setSelectedItem(TODOS);
		getCmbEstadoOrden().setSelectedItem(TODOS);
		cleanTable();
		
		getCmbMarca().removeAllItems();
		getCmbMarca().addItem(TODOS);
		getCmbMarca().setEnabled(false);
		
		getCmbTipoProveedor().setSelectedItem(TODOS);
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblOrdenes().getModel();
		for(int i= this.getTblOrdenes().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	public void report() {
		try {
			DefaultTableModel tblModelReporte = (DefaultTableModel) getTblOrdenes().getModel();
			if (tblModelReporte.getRowCount() > 0) {				
				String si = "Si";
				String no = "No";
				Object[] options = {si,no};
				String mensaje = "¿Desea generar el reporte de Ordenes?";
				int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
				if(opcion == JOptionPane.YES_OPTION) {
									
					//String fileName = "jaspers/medios/RPOrdenesPresupuestoEXCEL.jasper";
					String fileName = "jaspers/medios/RPOrdenesPresupuesto.jasper";
										
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("ORDENES POR PRESUPUESTO").iterator().next();
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
					parametrosMap.put("fechaInicio", Utilitarios.getFechaCortaUppercase(fechaInicio));
					parametrosMap.put("fechaFin", Utilitarios.getFechaCortaUppercase(fechaFin));
					
					if(getRbValorNeto().isSelected()){
						parametrosMap.put("filtro", "VALOR NETO");
					}else{
						parametrosMap.put("filtro", "VALOR BRUTO");
					}
					
					//para el sumario									
					parametrosMap.put("ordenes", ordenes);
					parametrosMap.put("cantidadOrdenes", cantidadOrdenes);
					
					parametrosMap.put("ordenesEmitidas", ordenesEmitidas);
					parametrosMap.put("cantidadOrdenesEmitidas", cantidadOrdenesEmitidas);
					parametrosMap.put("ordenesOrdenadas", ordenesOrdenadas);
					parametrosMap.put("cantidadOrdenesOrdenadas", cantidadOrdenesOrdenadas);
					parametrosMap.put("ordenesEnviadas", ordenesEnviadas);
					parametrosMap.put("cantidadOrdenesEnviadas", cantidadOrdenesEnviadas);
					parametrosMap.put("ordenesIngresadas", ordenesIngresadas);
					parametrosMap.put("cantidadOrdenesIngresadas", cantidadOrdenesIngresadas);
					parametrosMap.put("ordenesPrepagadas", ordenesPrepagadas);
					parametrosMap.put("cantidadOrdenesPrepagadas", cantidadOrdenesPrepagadas);				
					
					/*ArrayList<OrdenesPresupuestoDataTemp> ordenesPresupuestoDataVectorTemp = new ArrayList<OrdenesPresupuestoDataTemp>();
					OrdenesPresupuestoDataTemp temp = new OrdenesPresupuestoDataTemp();
					temp.setFecha("fecha");
					ordenesPresupuestoDataVectorTemp.add(temp);*/
					
					/*ordenesPresupuestoDataVector.clear();
					OrdenesPresupuestoData temp = new OrdenesPresupuestoData();
					temp.setFecha("fecha2");
					ordenesPresupuestoDataVector.add(temp);*/
					
					ReportModelImpl.processReport(fileName, parametrosMap, ordenesPresupuestoDataVector, true);					
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
			
		} /*catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}*/
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
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
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void deleteDetail() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showSaveMode() {
		clean();
		setSaveMode();		
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
}
