package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
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

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.handler.EstadoOrdenCompra;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.gui.panel.JPOrdenesClientes;
import com.spirit.medios.gui.reporteData.OrdenesClientesData;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class OrdenesClientesModel extends JPOrdenesClientes{

	private static String NOMBRE_MENU_MEDIOS = "MEDIOS";
	private static final String TODOS_TIPOS_PROVEEDOR = "TODOS";
	
	private static final long serialVersionUID = 1203731472443080243L;
	private static final String NOMBRE_ESTADO_COTIZADO = "COTIZADO";
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_APROBADO = "APROBADO";
	private static final String NOMBRE_ESTADO_CANCELADO = "CANCELADO";
	private static final String NOMBRE_ESTADO_FACTURADO = "FACTURADO";
	private static final String NOMBRE_ESTADO_ANULADO = "ANULADO";
	private static final String ESTADO_ANULADO = NOMBRE_ESTADO_ANULADO.substring(0, 1);
	public static final String ESTADO_COTIZADO = NOMBRE_ESTADO_COTIZADO.substring(2, 3);
	public static final String ESTADO_PENDIENTE = NOMBRE_ESTADO_PENDIENTE.substring(0, 1); 
	public static final String ESTADO_APROBADO = NOMBRE_ESTADO_APROBADO.substring(0, 1);
	public static final String ESTADO_CANCELADO = NOMBRE_ESTADO_CANCELADO.substring(0, 1);
	public static final String ESTADO_FACTURADO = NOMBRE_ESTADO_FACTURADO.substring(0, 1);
	public static final String APROBADO_FACTURADO = "APROBADO vs FACTURADO";
	public static final String FACTURAS_COMPRAS = "FACTURAS Y COMPRAS";
	private DefaultTableModel tableModel;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	protected ClienteOficinaIf clienteOficinaIf;
	protected ClienteIf clienteIf;
	protected OrdenTrabajoDetalleIf ordenTrabajoDetalleIf;
	protected OrdenTrabajoIf ordenTrabajoIf;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	//private Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
	private Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
	//private Map<Long,OrdenTrabajoDetalleIf> mapaOrdenTrabajoDetalle = new HashMap<Long,OrdenTrabajoDetalleIf>();
	//private Map<Long,OrdenTrabajoIf> mapaOrdenTrabajo = new HashMap<Long,OrdenTrabajoIf>();
	private Vector<PresupuestosClientesData> presupuestosColeccion = new Vector<PresupuestosClientesData>();
	private Vector<OrdenesClientesData> presupuestosColeccionReporte = new Vector<OrdenesClientesData>();
	private List<OrdenesClientesData> ordenesClienteDataColeccion = new ArrayList<OrdenesClientesData>();	
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	protected EmpleadoIf creadoPor;	
	private Map<Long, DocumentoIf> mapaDocumento = new HashMap<Long, DocumentoIf>();
	
	
	public OrdenesClientesModel(){
		cargarMapas();
		cargarCombos();
		initKeyListeners();
		anchoColumnasTabla();
		showSaveMode();
		initListeners();
	}
	
	/*private void cargarComboSubTipoOrden(){
		getCmbSubtipoOrden().addItem(NOMBRE_ESTADO_COTIZADO);
		getCmbSubtipoOrden().addItem(NOMBRE_ESTADO_PENDIENTE);
		getCmbSubtipoOrden().addItem(NOMBRE_ESTADO_APROBADO);
		getCmbSubtipoOrden().addItem(NOMBRE_ESTADO_CANCELADO);
		getCmbSubtipoOrden().addItem(NOMBRE_ESTADO_FACTURADO);
		getCmbSubtipoOrden().addItem(NOMBRE_ESTADO_TODOS);
		getCmbSubtipoOrden().setSelectedItem(NOMBRE_ESTADO_TODOS);
		getCmbSubtipoOrden().repaint();
	}*/
	
	public void initKeyListeners(){
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnConsultar().setToolTipText("Consultar Ordenes");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
						
		getBtnCreadoPor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCreadoPor().setToolTipText("Buscar Empleado");		
		getBtnLimpiarCreadoPor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnLimpiarCreadoPor().setToolTipText("Limpiar Empleado seleccionado");
		
		getTxtCliente().setEditable(false);
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
	}
	
	private void anchoColumnasTabla() {
		setSorterTable(getTblOrdenesClientes());
		getTblOrdenesClientes().getTableHeader().setReorderingAllowed(false);
		getTblOrdenesClientes().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblOrdenesClientes().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(180);
		anchoColumna = getTblOrdenesClientes().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblOrdenesClientes().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblOrdenesClientes().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblOrdenesClientes().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(120);
		anchoColumna = getTblOrdenesClientes().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(180);
		anchoColumna = getTblOrdenesClientes().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(120);
		anchoColumna = getTblOrdenesClientes().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(120);
		anchoColumna = getTblOrdenesClientes().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblOrdenesClientes().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblOrdenesClientes().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblOrdenesClientes().getColumnModel().getColumn(11);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblOrdenesClientes().getColumnModel().getColumn(12);
		anchoColumna.setPreferredWidth(80);
		
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		getTblOrdenesClientes().getColumnModel().getColumn(1).setCellRenderer(tableCellRendererCenter);
		getTblOrdenesClientes().getColumnModel().getColumn(2).setCellRenderer(tableCellRendererCenter);
		getTblOrdenesClientes().getColumnModel().getColumn(3).setCellRenderer(tableCellRendererCenter);
		getTblOrdenesClientes().getColumnModel().getColumn(9).setCellRenderer(tableCellRendererCenter);
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblOrdenesClientes().getColumnModel().getColumn(10).setCellRenderer(tableCellRenderer);
		getTblOrdenesClientes().getColumnModel().getColumn(11).setCellRenderer(tableCellRenderer);
		getTblOrdenesClientes().getColumnModel().getColumn(12).setCellRenderer(tableCellRenderer);
		getTblOrdenesClientes().getColumnModel().getColumn(13).setCellRenderer(tableCellRenderer);
	}
	
	public void cargarMapas(){
		try {
			/*mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while(clientesOficinaIt.hasNext()){
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf)clientesOficinaIt.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}*/
			
			mapaCliente.clear();
			Collection clientes = SessionServiceLocator.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesIt = clientes.iterator();
			while(clientesIt.hasNext()){
				ClienteIf cliente = (ClienteIf)clientesIt.next();
				mapaCliente.put(cliente.getId(), cliente);
			}
			
			mapaDocumento.clear();
			Collection documentos = SessionServiceLocator
					.getDocumentoSessionService().findDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator documentosIt = documentos.iterator();
			while (documentosIt.hasNext()) {
				DocumentoIf documento = (DocumentoIf) documentosIt
						.next();
				mapaDocumento.put(documento.getId(), documento);
			}
			
			/*mapaOrdenTrabajoDetalle.clear();
			Collection ordenesTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalleList();
			Iterator ordenesTrabajoDetalleIt = ordenesTrabajoDetalle.iterator();
			while(ordenesTrabajoDetalleIt.hasNext()){
				OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf)ordenesTrabajoDetalleIt.next();
				mapaOrdenTrabajoDetalle.put(ordenTrabajoDetalleIf.getId(), ordenTrabajoDetalleIf);
			}
			
			mapaOrdenTrabajo.clear();
			Collection ordenesTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajoList();
			Iterator ordenesTrabajoIt = ordenesTrabajo.iterator();
			while(ordenesTrabajoIt.hasNext()){
				OrdenTrabajoIf ordenTrabajoIf = (OrdenTrabajoIf)ordenesTrabajoIt.next();
				mapaOrdenTrabajo.put(ordenTrabajoIf.getId(), ordenTrabajoIf);
			}		*/
			
			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	
	public void cargarCombos(){
		cargarComboTipoProveedor();		
	}
	
	
	private void cargarComboTipoProveedor(){
		try {
			Collection tipoProveedores = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByEmpresaId(Parametros.getIdEmpresa());
			Iterator tipoProveedoresIt = tipoProveedores.iterator();
			ArrayList tiposProveedor = new ArrayList();
			while(tipoProveedoresIt.hasNext()){
				TipoProveedorIf tipoProveedor = (TipoProveedorIf)tipoProveedoresIt.next();
				tiposProveedor.add(tipoProveedor);
			}			
			Collections.sort(tiposProveedor,ordenarTipoPorNombre);
			tiposProveedor.add(TODOS_TIPOS_PROVEEDOR);		
			
			refreshCombo(getCmbTipoProveedor(),tiposProveedor);
			getCmbTipoProveedor().setSelectedItem(TODOS_TIPOS_PROVEEDOR);
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	Comparator<TipoProveedorIf> ordenarTipoPorNombre = new Comparator<TipoProveedorIf>(){
		public int compare(TipoProveedorIf o1, TipoProveedorIf o2) {
			return o1.getNombre().compareTo(o2.getNombre());
		}		
	};
		
	private void initListeners() {
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cleanTable();
				cargarTabla();
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
				}
			}
		});
		
		getBtnLimpiarCreadoPor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getTxtCreadoPor().setText("");
				creadoPor = null;
				getBtnCreadoPor().grabFocus();
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
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblOrdenesClientes().getModel();
		for(int i= this.getTblOrdenesClientes().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public Collection generarColeccionPresupuestos(){
		Collection<PresupuestoIf> presupuestos = null;		
		try {
			Map presupuestosMap = new HashMap();	 
			
			if(clienteOficinaIf != null){
				presupuestosMap.put("clienteoficinaId", clienteOficinaIf.getId());
			}
			
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());

			presupuestos = SessionServiceLocator.getPresupuestoSessionService().findPresupuestosByQueryByFechaInicioAndByFechaFin(presupuestosMap, fechaInicio, fechaFin);
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		return presupuestos;
	}
	
	Comparator<PresupuestoIf> ordenadorPresupuestosPorCodigo = new Comparator<PresupuestoIf>(){
		public int compare(PresupuestoIf o1, PresupuestoIf o2) {
			if(o1.getCodigo() == null){
				return -1;
			}else if(o2.getCodigo() == null){
				return 1;
			}else{
				return o2.getCodigo().compareTo(o1.getCodigo());
			}
		}		
	};
	
	private void cargarTabla() {
		try {
			tableModel = (DefaultTableModel) getTblOrdenesClientes().getModel();
			ordenesClienteDataColeccion = new ArrayList<OrdenesClientesData>();
			
			Map aMap = new HashMap();
			if(!getCmbTipoProveedor().getSelectedItem().equals(TODOS_TIPOS_PROVEEDOR)){
				aMap.put("tipoProveedorId", ((TipoProveedorIf)getCmbTipoProveedor().getSelectedItem()).getId());
			}

			if (clienteOficinaIf != null) {
				aMap.put("clienteoficinaId", clienteOficinaIf.getId());
			}

			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());

			System.out.println("todo ok");
			
			boolean ordenarPorCodigoOrden = getRbOrdenarCodigoOrden().isSelected()?true:false;
			boolean ordenarPorCodigoPresupuesto = getRbOrdenarCodigoPresupuesto().isSelected()?true:false;
			boolean ordenarPorFecha = getRbOrdenarFecha().isSelected()?true:false;
			Long creadoPorId = creadoPor != null? creadoPor.getId():null;
			
			Collection<Object[]> ordenesPresupuesto = SessionServiceLocator.getPresupuestoSessionService()
			.findOrdenesClientesByPresupuestos(aMap, fechaInicio, fechaFin, Parametros.getIdEmpresa(), ordenarPorFecha, creadoPorId, ordenarPorCodigoOrden, ordenarPorCodigoPresupuesto);
			
			Collection<Object[]> ordenesMedios = SessionServiceLocator.getOrdenMedioSessionService()
			.findOrdenesClientesByOrdenesMedios(aMap, fechaInicio, fechaFin, Parametros.getIdEmpresa(), ordenarPorFecha, creadoPorId, ordenarPorCodigoOrden, ordenarPorCodigoPresupuesto);
			
			ordenesPresupuesto.addAll(ordenesMedios);
			
			if(ordenarPorFecha){
				Collections.sort((ArrayList<Object[]>)ordenesPresupuesto,ordenarArreglosPorFecha);
			}else if(ordenarPorCodigoOrden){
				Collections.sort((ArrayList<Object[]>)ordenesPresupuesto,ordenarArreglosPorCodigoOrden);
			}else if(ordenarPorCodigoPresupuesto){
				Collections.sort((ArrayList<Object[]>)ordenesPresupuesto,ordenarArreglosPorCodigoOrden);
				Collections.sort((ArrayList<Object[]>)ordenesPresupuesto,ordenarArreglosPorCodigoPresupuesto);
			}
			
			Iterator ordenesPresupuestoIt = ordenesPresupuesto.iterator();			
			
			while (ordenesPresupuestoIt.hasNext()) {
				Object[] datos = (Object[]) ordenesPresupuestoIt.next();

				if (datos != null) {
					String codigoPresupuesto = "";
					String orden = "";
					String tipo = "";
					String proveedor = "";
					String marca = "";
					String producto = "";
					String creadopor = "";
					Date fechaOrden = null;
					String subtotal = "";
					String descuento = "";
					String iva = "";
					String total = "";
					String clienteNombre = "";
					String compra = "";
					String tipoOrden = "";
					Long ordenId = 0L;
					String cartera = "";
					
					if (datos[0] != null)
						codigoPresupuesto = datos[0].toString();
					if (datos[1] != null)
						clienteNombre = datos[1].toString();
					if (datos[2] != null)
						orden = datos[2].toString();
					
					if(datos[3] != null){
						//para ordenes de compra (ingresada es letra "G")
						if(EstadoOrdenCompra.INGRESADA.equals(EstadoOrdenCompra.getEstadoOrdenCompraByLetra(datos[3].toString()))){
							compra = "C";
							tipoOrden = "OC";
						}
						//para ordenes de medios (ingresada es letra "I")
						if(datos[3].toString().equals("I")){
							compra = "C";
							tipoOrden = "OM";
						}
					}
					
					if(compra.equals("C")){
						ordenId = (Long)datos[16];
						
						Map aMapOrden = new HashMap();
						aMapOrden.put("tipoOrden", tipoOrden);
						aMapOrden.put("ordenId", ordenId);
						Collection ordenesAsociadas = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByQuery(aMapOrden);
						if(ordenesAsociadas.size() > 0){
							OrdenAsociadaIf ordenAsociada = (OrdenAsociadaIf)ordenesAsociadas.iterator().next();
							CompraIf compraIf = SessionServiceLocator.getCompraSessionService().getCompra(ordenAsociada.getCompraId());
							
							
							//cartera
							if(getCbVerCartera().isSelected()){
								boolean tieneRetencion = false;
								double valorRetencion = 0D;
								double valorPago = 0D;
								CarteraIf carteraFacturaIf = null;
								Collection carterasIngresoDetalle = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaCarteraIngresoDetalleByTipoCarteraByReferenciaAfectadaIdAndByPreimpreso("P", compraIf.getId(), compraIf.getPreimpreso());
								Iterator carterasIngresoDetalleIt = carterasIngresoDetalle.iterator();
								while(carterasIngresoDetalleIt.hasNext()){
									Object[] carteraIngresoDetalle = (Object[])carterasIngresoDetalleIt.next();
									CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf)carteraIngresoDetalle[0];
									CarteraDetalleIf carteraDetalleIngreso = (CarteraDetalleIf)carteraIngresoDetalle[1];
									carteraFacturaIf = (CarteraIf)carteraIngresoDetalle[2];
									
									DocumentoIf documentoIf = mapaDocumento.get(carteraDetalleIngreso.getDocumentoId());
									if(documentoIf.getRetencionIva().equals("S") || documentoIf.getRetencionRenta().equals("S")){
										tieneRetencion = true;
										valorRetencion = valorRetencion + carteraAfectaIf.getValor().doubleValue();
									}
									valorPago = valorPago + carteraAfectaIf.getValor().doubleValue();
								}
								
								if(carteraFacturaIf != null && carteraFacturaIf.getSaldo().doubleValue() <= 0.01){
									cartera = "T";
								}
								else if(tieneRetencion && (valorPago > valorRetencion)){
									cartera = "R/P";
								}
								else if(!tieneRetencion && (valorPago > 0)){
									cartera = "P";
								}
								else if(tieneRetencion && (valorPago == valorRetencion)){
									cartera = "R";
								}
							}							
						}
					}
					
					
					if (datos[4] != null)
						tipo = datos[4].toString();
					if (datos[5] != null)
						proveedor = datos[5].toString();
					if (datos[6] != null)
						creadopor = datos[6].toString().split(" ")[0] + " " + datos[7].toString().split(" ")[0];
					if (datos[8] != null){
						if(datos[8].getClass().getName().equals("java.sql.Timestamp")){
							fechaOrden = new Date(((java.sql.Timestamp)datos[8]).getTime());
						}else{
							fechaOrden = (Date) datos[8];
						}
					}
					
					double subtotalDouble = 0D;					
					if (datos[9] != null){
						subtotalDouble = ((BigDecimal)datos[9]).doubleValue();
						subtotal = formatoDecimal.format(subtotalDouble);
					}
					
					double descuentoAgencia = 0D;
					if (datos[10] != null){
						descuentoAgencia = ((BigDecimal)datos[10]).doubleValue();
					}
					
					if (datos[11] != null)
						iva = formatoDecimal.format(datos[11]);
					if (datos[12] != null)
						marca = datos[12].toString();
					if (datos[13] != null)
						producto = datos[13].toString();
										
					double descuentoEspecial = 0D;
					double descuentosVarios = 0D;
					if (datos[14] != null){
						double porcentajeDescuentoEspecial = ((BigDecimal)datos[14]).doubleValue() / 100;						
						descuentoEspecial = subtotalDouble * porcentajeDescuentoEspecial;
					}
					if (datos[15] != null){
						double porcentajeDescuentosVarios = ((BigDecimal)datos[15]).doubleValue() / 100;
						descuentosVarios = (subtotalDouble - descuentoEspecial) * porcentajeDescuentosVarios;
					}
					
					BigDecimal descuentoTotal = BigDecimal.valueOf(descuentoEspecial + descuentoAgencia + descuentosVarios);
					descuento = formatoDecimal.format(descuentoTotal);
					
					BigDecimal totalBIGD = new BigDecimal("0.00");
					totalBIGD = BigDecimal.valueOf(subtotalDouble).subtract(descuentoTotal);
					
					if (datos[11] != null)
						totalBIGD = totalBIGD.add((BigDecimal)datos[11]);

					total = formatoDecimal.format(totalBIGD);

					Vector<String> fila = new Vector<String>();
					OrdenesClientesData ocData = new OrdenesClientesData();
					
					fila.add(clienteNombre);
					ocData.setCliente(clienteNombre);
					fila.add(codigoPresupuesto);
					ocData.setPresupuesto(codigoPresupuesto);
					fila.add(orden);
					ocData.setOrden(orden);
					fila.add(compra);
					ocData.setCompra(compra);
					
					ocData.setCartera(cartera);
					
					fila.add(tipo);
					ocData.setTipo(tipo);
					fila.add(proveedor);
					ocData.setProveedor(proveedor);
					fila.add(marca);
					ocData.setMarca(marca);
					fila.add(producto);
					ocData.setProducto(producto);
					fila.add(creadopor);
					ocData.setCreadoPor(creadopor);
					fila.add(Utilitarios.getFechaCortaUppercase(fechaOrden));
					ocData.setFecha(Utilitarios.getFechaCortaUppercase(fechaOrden));
					fila.add(subtotal);
					ocData.setSubtotal(subtotal);
					fila.add(descuento);
					ocData.setDescuento(descuento);
					fila.add(iva);
					ocData.setIva(iva);
					fila.add(total);
					ocData.setTotal(total);

					if(getCbSoloCompras().isSelected() && compra.equals("C")){
						tableModel.addRow(fila);
						ordenesClienteDataColeccion.add(ocData);
					}else if(!getCbSoloCompras().isSelected()){
						tableModel.addRow(fila);
						ordenesClienteDataColeccion.add(ocData);
					}					
				}
			}
			
			report();
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	Comparator<Object[]> ordenarArreglosPorFecha = new Comparator<Object[]>(){
		public int compare(Object[] o1, Object[] o2) {
			java.sql.Date fecha1 = new Date(0);
			if(o1[8].getClass().getName().equals("java.sql.Timestamp")){
				fecha1 = new Date(((java.sql.Timestamp)o1[8]).getTime());
			}else{
				fecha1 = (Date) o1[8];
			}
			java.sql.Date fecha2 = new Date(0);
			if(o2[8].getClass().getName().equals("java.sql.Timestamp")){
				fecha2 = new Date(((java.sql.Timestamp)o2[8]).getTime());
			}else{
				fecha2 = (Date) o2[8];
			}
			
			return fecha1.compareTo(fecha2);
		}		
	};
	
	Comparator<Object[]> ordenarArreglosPorCodigoOrden = new Comparator<Object[]>(){
		public int compare(Object[] o1, Object[] o2) {
			String codigo1 = (String) o1[2];
			String codigo2 = (String) o2[2];
			
			return codigo1.compareTo(codigo2);
		}		
	};
	
	Comparator<Object[]> ordenarArreglosPorCodigoPresupuesto = new Comparator<Object[]>(){
		public int compare(Object[] o1, Object[] o2) {
			String codigo1 = (String) o1[0];
			String codigo2 = (String) o2[0];
			
			return codigo1.compareTo(codigo2);
		}		
	};
	
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
		presupuestosColeccion = null;
		presupuestosColeccion = new Vector<PresupuestosClientesData>();
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		cleanTable();
	}
	
	public void report() {
		try {
			/*for(int i=0; i < ordenesClienteDataColeccion.size(); i++){
				String subtotal = ordenesClienteDataColeccion.get(i).getSubtotal();
				if(subtotal.equals("8,371.25")){
					System.out.println("aqui");
				}
			}*/
			
			
			DefaultTableModel tblModelReporte = (DefaultTableModel) getTblOrdenesClientes().getModel();
			
			//para crear un listado de acuerdo a la vista de la tabla (se puede ordenar por cabecera)
			ArrayList<OrdenesClientesData> reporteLista = new ArrayList<OrdenesClientesData>();
			for(int i=0; i < ordenesClienteDataColeccion.size(); i++){
				reporteLista.add(ordenesClienteDataColeccion.get(getTblOrdenesClientes().convertRowIndexToModel(i)));
			}
			
			//para numerar las filas
			for(int i=0; i < reporteLista.size(); i++){
				reporteLista.get(i).setNumero(String.valueOf(i+1));
			}
			
			
			if (tblModelReporte.getRowCount() > 0 ) {				
				String si = "Si";
				String no = "No";
				Object[] options = {si,no};
				String mensaje = "¿Desea generar el reporte de Ordenes de clientes?";
				int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if(opcion == JOptionPane.YES_OPTION) {
					
					String fileName = "jaspers/medios/RPOrdenesCliente.jasper";
				 					
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_MEDIOS).iterator().next();
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
					
					if (clienteOficinaIf != null) {
						parametrosMap.put("filtro", "Cliente: " + clienteOficinaIf.getDescripcion());
					}else{
						parametrosMap.put("filtro", "");
					}					 
					
					ReportModelImpl.processReport(fileName, parametrosMap, reporteLista, true);
					//ReportModelImpl.processReport(fileName, parametrosMap, tblModelReporte, true);
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
		catch (ParseException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
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
		// TODO Auto-generated method stub
		
	}
	
}
