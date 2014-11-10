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
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.gui.panel.JPPresupuestosClientes;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class PresupuestosClientesModel extends JPPresupuestosClientes{

	private static final long serialVersionUID = 1203731472443080243L;
	
	private static final String NOMBRE_ESTADO_COTIZADO = "COTIZADO";
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_APROBADO = "APROBADO";
	private static final String NOMBRE_ESTADO_CANCELADO = "CANCELADO";
	private static final String NOMBRE_ESTADO_FACTURADO = "FACTURADO";
	private static final String NOMBRE_ESTADO_PREPAGADO = "PREPAGADO";
	private static final String NOMBRE_ESTADO_ANULADO = "ANULADO";
	private static final String ESTADO_ANULADO = NOMBRE_ESTADO_ANULADO.substring(0, 1);
	public static final String ESTADO_COTIZADO = NOMBRE_ESTADO_COTIZADO.substring(2, 3);
	public static final String ESTADO_PENDIENTE = NOMBRE_ESTADO_PENDIENTE.substring(0, 1);
	public static final String ESTADO_APROBADO = NOMBRE_ESTADO_APROBADO.substring(0, 1);
	public static final String ESTADO_CANCELADO = NOMBRE_ESTADO_CANCELADO.substring(0, 1);
	public static final String ESTADO_FACTURADO = NOMBRE_ESTADO_FACTURADO.substring(0, 1);
	public static final String ESTADO_PREPAGADO = NOMBRE_ESTADO_PREPAGADO.substring(1, 2);
	public static final String APROBADO_FACTURADO = "APROBADO vs FACTURADO";
	public static final String FACTURAS_COMPRAS = "FACTURAS Y COMPRAS";
	private static final String NOMBRE_ESTADO_TODOS = "TODOS";
	private DefaultTableModel tableModel;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	protected ClienteOficinaIf clienteOficinaIf;
	protected ClienteIf clienteIf;
	protected OrdenTrabajoDetalleIf ordenTrabajoDetalleIf;
	protected OrdenTrabajoIf ordenTrabajoIf;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
	private Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
	private Map<Long,OrdenTrabajoDetalleIf> mapaOrdenTrabajoDetalle = new HashMap<Long,OrdenTrabajoDetalleIf>();
	private Map<Long,OrdenTrabajoIf> mapaOrdenTrabajo = new HashMap<Long,OrdenTrabajoIf>();
	private Vector<PresupuestosClientesData> presupuestosColeccion = new Vector<PresupuestosClientesData>();
	private String filtro = "";
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private BigDecimal totalCotizado = new BigDecimal(0);
	private BigDecimal totalAprobado = new BigDecimal(0);
	private double totalFacturado = 0;
	private double totalCompras = 0;
	private double totalPresupuestos = 0;
	private int cantidadCotizados = 0;
	private int cantidadAprobados = 0;
	private int cantidadFacturados = 0;
	private int cantidadTotal = 0;
	private int cantidadCompras = 0;
	private BigDecimal totalCotizadoCliente = new BigDecimal(0);
	private BigDecimal totalAprobadoCliente = new BigDecimal(0);
	private double totalFacturadoCliente = 0;
	private double totalComprasCliente = 0;
	private double totalPresupuestosCliente = 0;
	private int cantCotizadosCliente = 0;
	private int cantAprobadosCliente = 0;
	private int cantFacturadosCliente = 0;
	private int cantTotalCliente = 0;
	private int cantComprasCliente = 0;
	
	
	public PresupuestosClientesModel(){
		cargarMapas();
		cargarComboEstado();
		initKeyListeners();
		anchoColumnasTabla();
		showSaveMode();
		initListeners();
	}
	
	private void cargarComboEstado(){
		getCmbEstado().addItem(NOMBRE_ESTADO_COTIZADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_PENDIENTE);
		getCmbEstado().addItem(NOMBRE_ESTADO_APROBADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_CANCELADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_FACTURADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_PREPAGADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_TODOS);
		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_TODOS);
		getCmbEstado().repaint();
	}
	
	public void initKeyListeners(){
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnConsultar().setToolTipText("Consultar Balance General");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		getTblPresupuestos().getColumnModel().getColumn(0).setCellRenderer(tableCellRendererCenter);
		getTblPresupuestos().getColumnModel().getColumn(1).setCellRenderer(tableCellRendererCenter);
		getTblPresupuestos().getColumnModel().getColumn(2).setCellRenderer(tableCellRendererCenter);
		getTblPresupuestos().getColumnModel().getColumn(3).setCellRenderer(tableCellRendererCenter);
		getTblPresupuestos().getColumnModel().getColumn(5).setCellRenderer(tableCellRendererCenter);
		getTblPresupuestos().getColumnModel().getColumn(6).setCellRenderer(tableCellRendererCenter);
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblPresupuestos().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
				
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
		getTblPresupuestos().getTableHeader().setReorderingAllowed(false);
		//getTblPresupuestos().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblPresupuestos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(55);
		anchoColumna = getTblPresupuestos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblPresupuestos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblPresupuestos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(20);
		anchoColumna = getTblPresupuestos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(210);
		anchoColumna = getTblPresupuestos().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblPresupuestos().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblPresupuestos().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(70);
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
			
			mapaOrdenTrabajoDetalle.clear();
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
			}			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	private void initListeners() {
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cleanTable();
				cargarTabla();
			}
		});
		
		getCbSinCompras().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento){
				if(getCbSinCompras().isSelected()){
					getCbSoloFacturas().setSelected(false);
					getCbPresupuestosConFacturaCompra().setSelected(false);
				}
			}
		});
		
		getCbPresupuestosConFacturaCompra().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento){
				if(getCbPresupuestosConFacturaCompra().isSelected()){
					getCbSoloFacturas().setSelected(false);
					getCbAprobadoFacturado().setSelected(false);
					getCbSinCompras().setSelected(false);
				}
			}
		});
		
		getCbSoloFacturas().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento){
				if(getCbSoloFacturas().isSelected()){
					getCbPresupuestosConFacturaCompra().setSelected(false);
					getCbAprobadoFacturado().setSelected(false);
					getCbSinCompras().setSelected(false);
				}
			}
		});
		
		getCbAprobadoFacturado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento){
				if(getCbAprobadoFacturado().isSelected()){
					getCbPresupuestosConFacturaCompra().setSelected(false);
					getCbSoloFacturas().setSelected(false);
				}
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
		
		getCbReporteCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbReporteCliente().isSelected()){
					getCbReporteCodigo().setSelected(false);
				}else{
					getCbReporteCodigo().setSelected(true);
				}
			}
		});
		
		getCbReporteCodigo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbReporteCodigo().isSelected()){
					getCbReporteCliente().setSelected(false);
				}else{
					getCbReporteCliente().setSelected(true);
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
		DefaultTableModel model = (DefaultTableModel) getTblPresupuestos().getModel();
		for(int i= this.getTblPresupuestos().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public Collection generarColeccionPresupuestos(){
		Collection<PresupuestoIf> presupuestos = null;		
		try {
			filtro = "TODOS";
			Map presupuestosMap = new HashMap();
			if(getCbAprobadoFacturado().isSelected()){
				presupuestosMap.put("versus", APROBADO_FACTURADO);
				filtro = APROBADO_FACTURADO;
			}
			else if(!getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_TODOS)){				
				if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_APROBADO)){
					presupuestosMap.put("estado", ESTADO_APROBADO);
					filtro = NOMBRE_ESTADO_APROBADO;
				}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_CANCELADO)){
					presupuestosMap.put("estado", ESTADO_CANCELADO);
					filtro = NOMBRE_ESTADO_CANCELADO;
				}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_COTIZADO)){
					presupuestosMap.put("estado", ESTADO_COTIZADO);
					filtro = NOMBRE_ESTADO_COTIZADO;
				}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_FACTURADO)){
					presupuestosMap.put("estado", ESTADO_FACTURADO);
					filtro = NOMBRE_ESTADO_FACTURADO;
				}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PENDIENTE)){
					presupuestosMap.put("estado", ESTADO_PENDIENTE);
					filtro = NOMBRE_ESTADO_PENDIENTE;
				}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PREPAGADO)){
					presupuestosMap.put("estado", ESTADO_PREPAGADO);
					filtro = NOMBRE_ESTADO_PREPAGADO;
				}				
			}
			
			if(getCbPresupuestosConFacturaCompra().isSelected()){
				filtro = FACTURAS_COMPRAS;
			}
			
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
			totalCotizado = new BigDecimal(0);
			totalAprobado = new BigDecimal(0);
			totalFacturado = 0;
			totalCompras = 0;
			totalPresupuestos = 0;
			cantidadCotizados = 0;
			cantidadAprobados = 0;
			cantidadFacturados = 0;
			cantidadTotal = 0;
			cantidadCompras = 0;
			totalCotizadoCliente = new BigDecimal(0);
			totalAprobadoCliente = new BigDecimal(0);
			totalFacturadoCliente = 0;
			totalComprasCliente = 0;
			totalPresupuestosCliente = 0;
			cantCotizadosCliente = 0;
			cantAprobadosCliente = 0;
			cantFacturadosCliente = 0;
			cantTotalCliente = 0;
			cantComprasCliente = 0;
						
			Long clienteIdInicio = 0L;
			Long clienteId = 0L;
			
			presupuestosColeccion = null;
			presupuestosColeccion = new Vector<PresupuestosClientesData>();
			Collection<PresupuestoIf> presupuestos = generarColeccionPresupuestos();
			if(getCbReporteCodigo().isSelected()){
				Collections.sort((ArrayList)presupuestos,ordenadorPresupuestosPorCodigo);
			}
			Iterator presupuestosIterator = presupuestos.iterator();
			boolean ingreso = false;

			while (presupuestosIterator.hasNext()) {
				PresupuestoIf presupuestoIf = (PresupuestoIf) presupuestosIterator.next();

				tableModel = (DefaultTableModel) getTblPresupuestos().getModel();
				
				ordenTrabajoDetalleIf = mapaOrdenTrabajoDetalle.get(presupuestoIf.getOrdentrabajodetId());
				ordenTrabajoIf = mapaOrdenTrabajo.get(ordenTrabajoDetalleIf.getOrdenId());
				ClienteOficinaIf clienteOficina = mapaClienteOficina.get(ordenTrabajoIf.getClienteoficinaId());
				if(clienteIdInicio == 0L){
					clienteIdInicio = mapaCliente.get(clienteOficina.getClienteId()).getId();
					clienteId = mapaCliente.get(clienteOficina.getClienteId()).getId();
				}else{
					clienteId = mapaCliente.get(clienteOficina.getClienteId()).getId();
				}
					
				if(clienteIdInicio.compareTo(clienteId) != 0){
					clienteIdInicio = mapaCliente.get(clienteOficina.getClienteId()).getId();
					totalCotizadoCliente = new BigDecimal(0);
					totalAprobadoCliente = new BigDecimal(0);
					totalFacturadoCliente = 0;
					totalComprasCliente = 0;
					totalPresupuestosCliente = 0;
					cantCotizadosCliente = 0;
					cantAprobadosCliente = 0;
					cantFacturadosCliente = 0;
					cantTotalCliente = 0;
					cantComprasCliente = 0;
				}
				
				Map aMap = new HashMap();
				aMap.put("tiporeferencia", "P");
				aMap.put("referencia", presupuestoIf.getCodigo());
				Collection<PedidoIf> pedidos = SessionServiceLocator.getPedidoSessionService().findPedidoByQuery(aMap); 
				if( pedidos.size() > 0){
					Iterator pedidosIt = pedidos.iterator();
					while(pedidosIt.hasNext()){
						PedidoIf pedido = (PedidoIf)pedidosIt.next();
						if(SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId()).size() > 0){
							Iterator facturasIt = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId()).iterator();
							while(facturasIt.hasNext()){
								FacturaIf factura = (FacturaIf)facturasIt.next();
								if(!factura.getEstado().equals(ESTADO_ANULADO)){
									Vector<String> fila = new Vector<String>();
									PresupuestosClientesData presupuestoData = new PresupuestosClientesData();
									agregarFilasComunes(presupuestoIf, fila, presupuestoData);
									agregarFilasFacturaTabla(presupuestoIf, factura, fila, presupuestoData);
									tableModel.addRow(fila);
									presupuestosColeccion.add(presupuestoData);
									ingreso = true;
								}								
							}
						}
					}
				}
				
				if(!ingreso && !getCbPresupuestosConFacturaCompra().isSelected() && !getCbSoloFacturas().isSelected()){
					Vector<String> fila = new Vector<String>();
					PresupuestosClientesData presupuestoData = new PresupuestosClientesData();
					agregarFilasComunes(presupuestoIf, fila, presupuestoData);					
					agregarFilasSimplesTabla(presupuestoIf, fila, presupuestoData);					
					tableModel.addRow(fila);
					presupuestosColeccion.add(presupuestoData);
					ingreso = true;				
				}
				
				if(!getCbSoloFacturas().isSelected() && !getCbSinCompras().isSelected()){
					aMap.remove("tiporeferencia");
					aMap.put("tipoReferencia", "P");
					Collection<SolicitudCompraIf> solicitudesCompra = SessionServiceLocator.getSolicitudCompraSessionService().findSolicitudCompraByQuery(aMap); 
					if( solicitudesCompra.size() > 0){
						Iterator solicitudesIt = solicitudesCompra.iterator();
						while(solicitudesIt.hasNext()){
							SolicitudCompraIf solicitudCompra = (SolicitudCompraIf)solicitudesIt.next();
							
							Collection<OrdenCompraIf> ordenesCompra =SessionServiceLocator.getOrdenCompraSessionService().findOrdenCompraBySolicitudcompraId(solicitudCompra.getId()); 
							if( ordenesCompra.size() > 0){
								Iterator ordenesIt = ordenesCompra.iterator();
								while(ordenesIt.hasNext()){
									OrdenCompraIf ordenCompra = (OrdenCompraIf)ordenesIt.next();
									Map queryMap = new HashMap();
									queryMap.put("tipoOrden", "OC");
									queryMap.put("ordenId", ordenCompra.getId());
									Collection<OrdenAsociadaIf> ordenesAsociadas = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByQuery(queryMap);
									if( ordenesAsociadas.size() > 0){
										Iterator ordenesAsociadasIt = ordenesAsociadas.iterator();
										while(ordenesAsociadasIt.hasNext()){
											OrdenAsociadaIf ordenAsociada = (OrdenAsociadaIf) ordenesAsociadasIt.next();
											CompraIf compra = SessionServiceLocator.getCompraSessionService().getCompra(ordenAsociada.getCompraId());
											Vector<String> fila = new Vector<String>();
											PresupuestosClientesData presupuestoData = new PresupuestosClientesData();
											if(!ingreso){
												agregarFilasComunes(presupuestoIf, fila, presupuestoData);
											}else{
												agregarFilasComunesVacias(fila, presupuestoData);
											}
											agregarFilasCompraTabla(presupuestoIf, compra, fila, presupuestoData);
											tableModel.addRow(fila);
											presupuestosColeccion.add(presupuestoData);
											ingreso = true;
										}								
									}
								}						
							}
						}				
					}	
				}				
				
				ingreso = false;
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void agregarFilasComunesVacias(Vector<String> fila, PresupuestosClientesData presupuestoData) {
		fila.add("");			
		fila.add("");
		fila.add("");
		presupuestoData.setCodigo("");
		presupuestoData.setFechaCreacion("");
		presupuestoData.setEstado("");
	}

	private void agregarFilasFacturaTabla(PresupuestoIf presupuestoIf, FacturaIf factura, Vector<String> fila, PresupuestosClientesData presupuestoData) {
		fila.add("F");
		presupuestoData.setTipo("F");
		
		ordenTrabajoDetalleIf = mapaOrdenTrabajoDetalle.get(presupuestoIf.getOrdentrabajodetId());
		ordenTrabajoIf = mapaOrdenTrabajo.get(ordenTrabajoDetalleIf.getOrdenId());
		ClienteOficinaIf clienteOficina = mapaClienteOficina.get(ordenTrabajoIf.getClienteoficinaId());
		ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());									
		fila.add(cliente.getNombreLegal());		
		presupuestoData.setOperador(cliente.getNombreLegal());
		presupuestoData.setClienteId(cliente.getId().toString());
				
		fila.add(Utilitarios.getFechaCortaUppercase(factura.getFechaFactura()));
		fila.add(factura.getPreimpresoNumero()!=null?factura.getPreimpresoNumero():"");
		
		double subtotal = factura.getValor().doubleValue()-factura.getDescuento().doubleValue()-factura.getDescuentoGlobal().doubleValue();
		double porcentajeComision = factura.getPorcentajeComisionAgencia()!=null?factura.getPorcentajeComisionAgencia().doubleValue():0D;
		double comision = (subtotal * porcentajeComision) / 100D;
		double total = subtotal+factura.getIva().doubleValue()+factura.getIce().doubleValue()+factura.getOtroImpuesto().doubleValue()+comision;
		fila.add(formatoDecimal.format(Utilitarios.redondeoValor(total)));
		totalFacturado = totalFacturado + total;
		totalPresupuestos = totalPresupuestos + total;
		cantidadFacturados++;
		cantidadTotal++;
		totalFacturadoCliente = totalFacturadoCliente + total;
		totalPresupuestosCliente = totalPresupuestosCliente + total;
		cantFacturadosCliente++;
		cantTotalCliente ++;
				
		presupuestoData.setFechaFactura(Utilitarios.getFechaCortaUppercase(factura.getFechaFactura()));
		presupuestoData.setPreimpreso(factura.getPreimpresoNumero()!=null?factura.getPreimpresoNumero():"");
		presupuestoData.setValor(formatoDecimal.format(Utilitarios.redondeoValor(total)));
		
		presupuestoData.setTotalCotizadoCliente(formatoDecimal.format(totalCotizadoCliente.doubleValue()));
		presupuestoData.setTotalAprobadoCliente(formatoDecimal.format(totalAprobadoCliente.doubleValue()));
		presupuestoData.setTotalFacturadoCliente(formatoDecimal.format(totalFacturadoCliente));
		presupuestoData.setTotalComprasCliente(formatoDecimal.format(totalComprasCliente));
		presupuestoData.setTotalPresupuestosCliente(formatoDecimal.format(totalPresupuestosCliente));
		presupuestoData.setCantCotizadosCliente(String.valueOf(cantCotizadosCliente));
		presupuestoData.setCantAprobadosCliente(String.valueOf(cantAprobadosCliente));
		presupuestoData.setCantFacturadosCliente(String.valueOf(cantFacturadosCliente));
		presupuestoData.setCantTotalCliente(String.valueOf(cantTotalCliente));
		presupuestoData.setCantComprasCliente(String.valueOf(cantComprasCliente));
	}
	
	private void agregarFilasCompraTabla(PresupuestoIf presupuestoIf, CompraIf compra, Vector<String> fila, PresupuestosClientesData presupuestoData) {
		fila.add("C");
		presupuestoData.setTipo("C");
		
		ClienteOficinaIf clienteOficina = mapaClienteOficina.get(compra.getProveedorId());
		ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());											
		fila.add(cliente.getNombreLegal());		
		presupuestoData.setOperador(cliente.getNombreLegal());
		
		//Para el reporte
		OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = mapaOrdenTrabajoDetalle.get(presupuestoIf.getOrdentrabajodetId());
		OrdenTrabajoIf ordenTrabajoIf = mapaOrdenTrabajo.get(ordenTrabajoDetalleIf.getOrdenId());
		ClienteOficinaIf clienteOficinaPresupuesto = mapaClienteOficina.get(ordenTrabajoIf.getClienteoficinaId());
		ClienteIf clientePresupuesto = mapaCliente.get(clienteOficinaPresupuesto.getClienteId());
		presupuestoData.setClienteId(clientePresupuesto.getId().toString());
		
		
		fila.add(Utilitarios.getFechaCortaUppercase(compra.getFecha()));
		fila.add(compra.getPreimpreso()!=null?compra.getPreimpreso():"");
		
		double total = compra.getValor().doubleValue()-compra.getDescuento().doubleValue()+compra.getIva().doubleValue()+compra.getIce().doubleValue()+compra.getOtroImpuesto().doubleValue();
		fila.add(formatoDecimal.format(Utilitarios.redondeoValor(total)));
		totalCompras = totalCompras + total;
		cantidadCompras++;
		totalComprasCliente = totalComprasCliente + total;
		cantComprasCliente++;
		
		presupuestoData.setFechaFactura(Utilitarios.getFechaCortaUppercase(compra.getFecha()));
		presupuestoData.setPreimpreso(compra.getPreimpreso()!=null?compra.getPreimpreso():"");
		presupuestoData.setValor(formatoDecimal.format(Utilitarios.redondeoValor(total)));
		
		presupuestoData.setTotalCotizadoCliente(formatoDecimal.format(totalCotizadoCliente.doubleValue()));
		presupuestoData.setTotalAprobadoCliente(formatoDecimal.format(totalAprobadoCliente.doubleValue()));
		presupuestoData.setTotalFacturadoCliente(formatoDecimal.format(totalFacturadoCliente));
		presupuestoData.setTotalComprasCliente(formatoDecimal.format(totalComprasCliente));
		presupuestoData.setTotalPresupuestosCliente(formatoDecimal.format(totalPresupuestosCliente));
		presupuestoData.setCantCotizadosCliente(String.valueOf(cantCotizadosCliente));
		presupuestoData.setCantAprobadosCliente(String.valueOf(cantAprobadosCliente));
		presupuestoData.setCantFacturadosCliente(String.valueOf(cantFacturadosCliente));
		presupuestoData.setCantTotalCliente(String.valueOf(cantTotalCliente));
		presupuestoData.setCantComprasCliente(String.valueOf(cantComprasCliente));
	}
	
	private void agregarFilasComunes(PresupuestoIf presupuestoIf, Vector<String> fila, PresupuestosClientesData presupuestoData) {
		fila.add(presupuestoIf.getCodigo()!=null?presupuestoIf.getCodigo():"");			
		fila.add(Utilitarios.getFechaCortaUppercase(presupuestoIf.getFecha()));
		presupuestoData.setCodigo(presupuestoIf.getCodigo()!=null?presupuestoIf.getCodigo():"");
		presupuestoData.setFechaCreacion(Utilitarios.getFechaCortaUppercase(presupuestoIf.getFecha()));
		
		if (ESTADO_COTIZADO.equals(presupuestoIf.getEstado())){
			fila.add(NOMBRE_ESTADO_COTIZADO);
			presupuestoData.setEstado(NOMBRE_ESTADO_COTIZADO);
		}else if (ESTADO_PENDIENTE.equals(presupuestoIf.getEstado())){
			fila.add(NOMBRE_ESTADO_PENDIENTE);
			presupuestoData.setEstado(NOMBRE_ESTADO_PENDIENTE);
		}else if (ESTADO_APROBADO.equals(presupuestoIf.getEstado())){
			fila.add(NOMBRE_ESTADO_APROBADO);
			presupuestoData.setEstado(NOMBRE_ESTADO_APROBADO);
		}else if (ESTADO_CANCELADO.equals(presupuestoIf.getEstado())){
			fila.add(NOMBRE_ESTADO_CANCELADO);
			presupuestoData.setEstado(NOMBRE_ESTADO_CANCELADO);
		}else if (ESTADO_FACTURADO.equals(presupuestoIf.getEstado())){
			fila.add(NOMBRE_ESTADO_FACTURADO);
			presupuestoData.setEstado(NOMBRE_ESTADO_FACTURADO);
		}else if (ESTADO_PREPAGADO.equals(presupuestoIf.getEstado())){
			fila.add(NOMBRE_ESTADO_PREPAGADO);
			presupuestoData.setEstado(NOMBRE_ESTADO_PREPAGADO);
		}		
	}
	
	private void agregarFilasSimplesTabla(PresupuestoIf presupuestoIf, Vector<String> fila, PresupuestosClientesData presupuestoData) {
		fila.add("");
		presupuestoData.setTipo("");					
		ordenTrabajoDetalleIf = mapaOrdenTrabajoDetalle.get(presupuestoIf.getOrdentrabajodetId());
		ordenTrabajoIf = mapaOrdenTrabajo.get(ordenTrabajoDetalleIf.getOrdenId());
		ClienteOficinaIf clienteOficina = mapaClienteOficina.get(ordenTrabajoIf.getClienteoficinaId());
		ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());									
		fila.add(cliente.getNombreLegal());		
		presupuestoData.setOperador(cliente.getNombreLegal());
		presupuestoData.setClienteId(cliente.getId().toString());
		
		fila.add("");
		fila.add("");					
		fila.add(formatoDecimal.format(Utilitarios.redondeoValor(presupuestoIf.getValor().doubleValue())));					
		presupuestoData.setFechaFactura("");
		presupuestoData.setPreimpreso("");
		presupuestoData.setValor(formatoDecimal.format(Utilitarios.redondeoValor(presupuestoIf.getValor().doubleValue())));
		
		if(presupuestoIf.getEstado().equals(ESTADO_COTIZADO)){
			totalCotizadoCliente = totalCotizadoCliente.add(presupuestoIf.getValor());
			totalCotizado = totalCotizado.add(presupuestoIf.getValor());
			totalPresupuestos = totalPresupuestos + presupuestoIf.getValor().doubleValue();
			cantidadCotizados++;
			cantidadTotal++;
			totalPresupuestosCliente = totalPresupuestosCliente + presupuestoIf.getValor().doubleValue();
			cantCotizadosCliente++;
			cantTotalCliente++;
		}else if(presupuestoIf.getEstado().equals(ESTADO_APROBADO)){
			totalAprobadoCliente = totalAprobadoCliente.add(presupuestoIf.getValor());
			totalAprobado = totalAprobado.add(presupuestoIf.getValor());
			totalPresupuestos = totalPresupuestos + presupuestoIf.getValor().doubleValue();
			cantidadAprobados++;
			cantidadTotal++;
			totalPresupuestosCliente = totalPresupuestosCliente + presupuestoIf.getValor().doubleValue();
			cantAprobadosCliente++;
			cantTotalCliente++;
		}
		
		presupuestoData.setTotalCotizadoCliente(formatoDecimal.format(totalCotizadoCliente.doubleValue()));
		presupuestoData.setTotalAprobadoCliente(formatoDecimal.format(totalAprobadoCliente.doubleValue()));
		presupuestoData.setTotalFacturadoCliente(formatoDecimal.format(totalFacturadoCliente));
		presupuestoData.setTotalComprasCliente(formatoDecimal.format(totalComprasCliente));
		presupuestoData.setTotalPresupuestosCliente(formatoDecimal.format(totalPresupuestosCliente));
		presupuestoData.setCantCotizadosCliente(String.valueOf(cantCotizadosCliente));
		presupuestoData.setCantAprobadosCliente(String.valueOf(cantAprobadosCliente));
		presupuestoData.setCantFacturadosCliente(String.valueOf(cantFacturadosCliente));
		presupuestoData.setCantTotalCliente(String.valueOf(cantTotalCliente));
		presupuestoData.setCantComprasCliente(String.valueOf(cantComprasCliente));
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
		presupuestosColeccion = null;
		presupuestosColeccion = new Vector<PresupuestosClientesData>();
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		getCbReporteCodigo().setSelected(true);
		getCbReporteCliente().setSelected(false);
		cleanTable();
	}

	public void report() {
		try {
			DefaultTableModel tblModelReporte = (DefaultTableModel) getTblPresupuestos().getModel();
			if (tblModelReporte.getRowCount() > 0) {
				String si = "Si";
				String no = "No";
				Object[] options = {si,no};
				String mensaje = "¿Desea generar el reporte de Presupuestos?";
				int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
				if(opcion == JOptionPane.YES_OPTION) {
					
					String fileName = "jaspers/medios/RPPresupuestosClientes.jasper";					
					if(getCbReporteCliente().isSelected()){
						fileName = "jaspers/medios/RPPresupuestosClientesAgrupados.jasper";					
					}else{
						fileName = "jaspers/medios/RPPresupuestosClientes.jasper";	
					}
					
					HashMap parametrosMap = new HashMap();
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
					parametrosMap.put("filtro", filtro);
					parametrosMap.put("totalCotizado", formatoDecimal.format(totalCotizado.doubleValue()));
					parametrosMap.put("totalAprobado", formatoDecimal.format(totalAprobado.doubleValue()));
					parametrosMap.put("totalFacturado", formatoDecimal.format(totalFacturado));
					parametrosMap.put("totalCompras", formatoDecimal.format(totalCompras));
					//parametrosMap.put("totalPresupuestos", formatoDecimal.format(totalCotizado.doubleValue()+totalAceptado.doubleValue()+totalFacturado));
					parametrosMap.put("totalPresupuestos", formatoDecimal.format(totalPresupuestos));
					parametrosMap.put("cantidadCotizados", String.valueOf(cantidadCotizados));
					parametrosMap.put("cantidadAprobados", String.valueOf(cantidadAprobados));
					parametrosMap.put("cantidadFacturados", String.valueOf(cantidadFacturados));
					parametrosMap.put("cantidadTotal", String.valueOf(cantidadTotal));
					parametrosMap.put("cantidadCompras", String.valueOf(cantidadCompras));					
					
					ReportModelImpl.processReport(fileName, parametrosMap, presupuestosColeccion, true);
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
		} catch (ParseException e) {
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
