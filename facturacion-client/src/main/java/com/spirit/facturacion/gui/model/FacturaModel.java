package com.spirit.facturacion.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.FormaPagoIf;
import com.spirit.client.ChangeModeImpl;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
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
import com.spirit.facturacion.entity.FacturaDetalleData;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaDetalleIvaIncluidoData;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.ListaPrecioIf;
import com.spirit.facturacion.entity.MotivoDocumentoIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.gui.criteria.FacturaCriteria;
import com.spirit.facturacion.gui.panel.JPFactura;
import com.spirit.facturacion.handler.TipoReferenciaPedido;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.LineaIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.OrigenDocumentoIf;
import com.spirit.general.entity.PuntoImpresionIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.CampanaProductoVersionIf;
import com.spirit.medios.entity.EquipoEmpleadoIf;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.OrdenTrabajoProductoIf;
import com.spirit.medios.entity.PlanMedioFacturacionIf;
import com.spirit.medios.entity.PlanMedioFormaPagoIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoFacturacionIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.PresupuestoProductoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class FacturaModel extends JPFactura {
	private static final long serialVersionUID = 4901559804883920984L;
	private CorporacionCriteria corporacionCriteria;
	private ClienteCriteria clienteCriteria;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private JDPopupFinderModel popupFinder;
	FacturaIf factura;
	ArrayList lista;
	protected ClienteOficinaIf proveedorIf;
	protected EmpleadoIf facturador;
	protected CorporacionIf corporacionIf;
	protected ClienteOficinaIf clienteOficinaIf;
	protected ClienteOficinaIf clienteOficinaNegociacionIf;
	protected ClienteIf clienteIf;
	protected PresupuestoIf presupuestoIf;
	protected PresupuestoDetalleIf presupuestoDetalleIf;
	protected FacturaDetalleIf facturaDetalleIf;
	DefaultTableModel modelFacturaDetalle;
	protected ProductoIf productoIf;
	protected PuntoImpresionIf puntoImpresionIf;
	protected LineaIf lineaIf;
	protected TipoIdentificacionIf tipoIdentificacionIf;
	protected CajaIf cajaIf;
	boolean escogiolote = false;
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_COMPLETO = "COMPLETO";
	private static final String NOMBRE_ESTADO_INCOMPLETO = "INCOMPLETO";
	private static final String NOMBRE_ESTADO_ANULADO = "ANULADO";
	private static final String OPCION_NO = "N";
	private static final String ESTADO_ANULADO = NOMBRE_ESTADO_ANULADO.substring(0, 1);
	private static final String ESTADO_PENDIENTE = NOMBRE_ESTADO_PENDIENTE.substring(0, 1);
	private static final String ESTADO_INCOMPLETO = NOMBRE_ESTADO_INCOMPLETO.substring(0, 1);
	private static final String ESTADO_COMPLETO = NOMBRE_ESTADO_COMPLETO.substring(0, 1);
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA = "D";
	private static final String TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA = "P";
	
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DecimalFormat formatoEntero = new DecimalFormat("###0");
	private DecimalFormat formatoDecimalDescuentoGlobalPorcentaje = new DecimalFormat("#,##0.00");
	private DecimalFormat formatoSerial = new DecimalFormat("0000000");
	protected Double subTotal = 0.00, totalIvaCero = 0.00, descuentoTotal = 0.00, ivaTotal = 0.00, 
	total = 0.00, valorBruto = 0.00, ivaTemp = 0.00, comisionAgenciaTotal = 0.00, descuentosVariosTotal = 0.00;
	protected Double porcentajeComisionAgencia = 0.00;
	protected Double montoDescuento = 0.00;
	protected Double IVA = Parametros.getIVA() / 100;
	protected Double IVA_CERO = 0D;
	private Double subtotalFinalReporte = 0D;
	private Double ivaFinalReporte = 0D;
	private String campanaPeriodo = "";
	java.util.Date fechaCreacion = new java.util.Date();
	JMenuItem itemEliminarFacturaDetalle;
	final JPopupMenu popupMenuFacturaDetalle = new JPopupMenu();
	Vector FacturaDetalleColeccion = new Vector();
	boolean ordenmedios = false, presupuesto = false, sinpresupuesto = false;
	PedidoIf pedido = null;
	private static final int MAX_LONGITUD_NUMERO = 7;
	//private static final int MAX_LONGITUD_PREIMPRESO = 15;
	private static final int MAX_LONGITUD_CONTACTO = 40;
	private static final String REPORTE_PRESUPUESTO_SI = "S";
	private static final String REPORTE_PRESUPUESTO_NO = "N";
	private static final String TIPO_PRESUPUESTO_FACTURACION_NORMAL = "N";
	private static final String TIPO_PRESUPUESTO_FACTURACION_CLIENTE = "C";
	private static final String TIPO_PRESUPUESTO_FACTURACION_PARCIAL = "R";
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long, ProductoIf> mapaProductoProveedor = new HashMap<Long, ProductoIf>();
	private Map<Long, GenericoIf> mapaGenerico = new HashMap<Long, GenericoIf>();
	
	
	public FacturaModel() {
		initKeyListeners();
		cargarMapas();
		initListeners();
		getTblFacturaDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblFacturaDetalle().addMouseListener(oMouseAdapterTblFacturaDetalle);
		getTblFacturaDetalle().addKeyListener(oKeyAdapterTblFacturaDetalle);
		getBtnBuscarCorporacion().addActionListener(oActionListenerBtnBuscarCorporacion);
		getBtnBuscarCliente().addActionListener(oActionListenerBtnBuscarCliente);
		getBtnBuscarClienteOficina().addActionListener(oActionListenerBtnBuscarClienteOficina);
		cargarCombos();
		showSaveMode();
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		//Calculo y seteo la maxima longitud del preimpreso segun lo fijado en Parametros Empresa
		int maxlongPreimpEstablecimiento = Parametros.getMaximaLongitudPreimpresoEstablecimiento();
		int maxlongPreimpPuntoEmision = Parametros.getMaximaLongitudPreimpresoPuntoEmision();
		int maxlongPreimpSecuencial = Parametros.getMaximaLongitudPreimpresoSecuencial();
		int guionesSeparadores = 2;
		int longitudPreimpreso = maxlongPreimpEstablecimiento + maxlongPreimpPuntoEmision + maxlongPreimpSecuencial + guionesSeparadores;
		
		getTxtPreimpreso().addKeyListener(new TextChecker(longitudPreimpreso));
		getTxtNumero().addKeyListener(new TextChecker(MAX_LONGITUD_NUMERO));
		getTxtContacto().addKeyListener(new TextChecker(MAX_LONGITUD_CONTACTO));
		
		getBtnEncerarCliente().setToolTipText("Limpiar datos cliente");
		getBtnEncerarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/cancelar.png"));
		getBtnBuscarCorporacion().setToolTipText("Buscar Corporación");
		getBtnBuscarCorporacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCliente().setToolTipText("Buscar Cliente");
		getBtnBuscarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarClienteOficina().setToolTipText("Buscar Oficina de Cliente");
		getBtnBuscarClienteOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarClienteNegociacion().setToolTipText("Buscar Cliente Negociación");
		getBtnBuscarClienteNegociacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
				
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblFacturaDetalle().getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
		getTblFacturaDetalle().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		getTblFacturaDetalle().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		getTblFacturaDetalle().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		getTblFacturaDetalle().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);		
		
		getCmbFechaFactura().setLocale(Utilitarios.esLocale);
		getCmbFechaFactura().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFactura().setEditable(false);
		getCmbFechaFactura().setShowNoneButton(false);
	}
	
	private void cargarMapas() {
		try {
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while (clientesOficinaIt.hasNext()) {
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clientesOficinaIt.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
			
			mapaProductoProveedor.clear();
			Collection productos = SessionServiceLocator.getProductoSessionService().findProductoByEmpresaId(Parametros.getIdEmpresa());
			Iterator productosIt = productos.iterator();
			while (productosIt.hasNext()) {
				ProductoIf productoIf = (ProductoIf) productosIt.next();
				mapaProductoProveedor.put(productoIf.getId(), productoIf);
			}
			
			mapaGenerico.clear();
			Collection genericos = SessionServiceLocator.getGenericoSessionService().findGenericoByEmpresaId(Parametros.getIdEmpresa());
			Iterator genericosIt = genericos.iterator();
			while (genericosIt.hasNext()) {
				GenericoIf genericoIf = (GenericoIf) genericosIt.next();
				mapaGenerico.put(genericoIf.getId(), genericoIf);
			}
			
		}catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	private void initListeners() {
		getBtnEncerarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				corporacionIf = null;
				clienteIf = null;
				clienteOficinaIf = null;
				getTxtCorporacion().setText("");
				getTxtCliente().setText("");
				getTxtClienteOficina().setText("");
				getTxtIdentificacion().setText("");
				getTxtContacto().setText("");
				getTxtTelefono().setText("");
				getTxtDireccion().setText("");
			}
		});
	}

	public void find() {
		try {
			Map mapa = buildQuery();
			int tamanoLista = SessionServiceLocator.getFacturaSessionService().getFacturaListSize(mapa, Parametros.getIdEmpresa());
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.addElement(110);
			anchoColumnas.addElement(70);
			anchoColumnas.addElement(220);
			anchoColumnas.addElement(220);
			anchoColumnas.addElement(90);
			Map alineacionColumnas = new HashMap();
			alineacionColumnas.put(0, SwingConstants.CENTER);
			alineacionColumnas.put(1, SwingConstants.CENTER);
			alineacionColumnas.put(4, SwingConstants.RIGHT);
			if (tamanoLista > 0) {
				FacturaCriteria facturaCriteria = new FacturaCriteria();
				facturaCriteria.setResultListSize(tamanoLista);
				facturaCriteria.setQueryBuilded(mapa);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), facturaCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, alineacionColumnas);
				if ( popupFinder.getElementoSeleccionado() != null )
					getSelectedObject();
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
				if (getMode() == SpiritMode.FIND_MODE)
					this.getCmbFechaFactura().setSelectedItem(null);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la busqueda de información", SpiritAlert.ERROR);
		}
	}
	
	public void save() {
		SpiritAlert.createAlert("No se puede guardar la factura!", SpiritAlert.WARNING);
	}
	
	public void update() {
		try {
			SessionServiceLocator.getFacturaSessionService().actualizarPreimpreso(factura, getTxtPreimpreso().getText(), false);
			//SpiritAlert.createAlert("No se puede actualizar la factura!", SpiritAlert.WARNING);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al actualizar el preimpreso de la factura", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		if(revisarFactura()){
			try {
				SessionServiceLocator.getCarteraSessionService().eliminarFactura(factura, Parametros.getUsuario());
				SpiritAlert.createAlert("Factura eliminada con exito!", SpiritAlert.INFORMATION);
				
				//Al eliminar una factura cambio el estado del presupuesto asociado a ACEPTADO (si es que existe)
				if(pedido.getTiporeferencia() != null && pedido.getTiporeferencia().equals("P") && pedido.getReferencia() != null){
					PresupuestoIf presupuestoIf = (PresupuestoIf)SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(pedido.getReferencia()).iterator().next();
					presupuestoIf.setEstado("A");
					SessionServiceLocator.getPresupuestoSessionService().savePresupuesto(presupuestoIf);
				}
				
				showSaveMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean revisarFactura(){
		
		if(factura.getPreimpresoNumero() != null){
			SpiritAlert.createAlert("No se puede borrar la factura, ya tiene Preimpreso!", SpiritAlert.WARNING);
			return false;
		}
		
		try {
			Map aMap = new HashMap();
			aMap.put("tipodocumentoId", factura.getTipodocumentoId());
			aMap.put("referenciaId", factura.getId());		
			
			if(!SessionServiceLocator.getCarteraSessionService().findCarteraByQuery(aMap).isEmpty()){
				CarteraIf carteraFactura = (CarteraIf)SessionServiceLocator.getCarteraSessionService().findCarteraByQuery(aMap).iterator().next();
				if(carteraFactura.getValor().compareTo(carteraFactura.getSaldo()) != 0){
					SpiritAlert.createAlert("No se puede borrar la factura, ya esta Cruzada!", SpiritAlert.WARNING);
					return false;
				}
			}			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void authorize() {
		try {
			if(validateFields()){
				String preimpreso = getTxtPreimpreso().getText();
				SessionServiceLocator.getFacturaSessionService().actualizarPreimpreso(factura, preimpreso, Parametros.isActivarReplicacion());
				SpiritAlert.createAlert("El Preimpreso de la factura se ha actualizado con éxito !", SpiritAlert.WARNING);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public boolean validateFields() {
		String preimpreso = getTxtPreimpreso().getText();
		
		if((preimpreso == null) || preimpreso.equals("")){
			SpiritAlert.createAlert("Debe ingresar un Preimpreso para la factura!", SpiritAlert.WARNING);
			getTxtPreimpreso().grabFocus();
			return false;
		}
		
		return true;
	}

	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if(getCmbEstado().getSelectedIndex() != -1){
			if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ANULADO)){
				aMap.put("estado", ESTADO_ANULADO);
			}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_COMPLETO)){
				aMap.put("estado", ESTADO_COMPLETO);
			}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INCOMPLETO)){
				aMap.put("estado", ESTADO_INCOMPLETO);
			}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PENDIENTE)){
				aMap.put("estado", ESTADO_PENDIENTE);
			}			
		}
		
		if ("".equals(getTxtContacto().getText()) == false)
			aMap.put("contacto", "%" + getTxtContacto().getText() + "%");
		
		if (getCmbFechaFactura().getSelectedItem() != null)
			aMap.put("fechaFactura", new java.sql.Date(getCmbFechaFactura().getDate().getYear(),getCmbFechaFactura().getDate().getMonth(),getCmbFechaFactura().getDate().getDate()));
		
		if (clienteOficinaIf != null)
			aMap.put("clienteoficinaId", clienteOficinaIf.getId());
		
		try {
			if ("".equals(getTxtNumero().getText()) == false)
				aMap.put("numero", BigDecimal.valueOf(Double.parseDouble(this.getTxtNumero().getText())));
		} catch (java.lang.NumberFormatException e) {
			SpiritAlert.createAlert("El número de factura ingresado no es válido", SpiritAlert.ERROR);
			getTxtNumero().setText("");
			getTxtNumero().grabFocus();
		}
		
		try {
			if ("".equals(getTxtPreimpreso().getText()) == false)
				aMap.put("preimpresoNumero", this.getTxtPreimpreso().getText());
		} catch (java.lang.NumberFormatException e) {
			SpiritAlert.createAlert("El Preimpreso de factura ingresado no es válido", SpiritAlert.ERROR);
			getTxtPreimpreso().setText("");
			getTxtPreimpreso().grabFocus();
		}
		
		return aMap;
	}
	
	public void refresh() {

	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	/*public void report() {
	Double subtotalReporte = 0D;
	Double ivaReporte = 0D;
	if (getMode() == SpiritMode.UPDATE_MODE) {
		try {
			if (FacturaDetalleColeccion.size() > 0) {
				String si = "Si"; 
    	        String no = "No"; 
    	        Object[] options ={si,no}; 
    			int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir la Factura?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					Long idParametro = factura.getId();
					
					Collection dataSourceCollection = initializeBeanCollection(idParametro, false);
					JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(dataSourceCollection);
					TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(factura.getTipodocumentoId());
					String fileName = (tipoDocumento.getCodigo().equals("VTA"))?"jaspers/facturacion/RPNotaVenta.jasper":"jaspers/facturacion/RPFactura.jasper";
					dataSourceCollection = initializeBeanCollection(idParametro, true);
					
					Map proveedoresMap = getProveedoresMap(idParametro);
					Map productosMap = getProductosMap(idParametro);
					HashMap parametrosMap = new HashMap();
					if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
						parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes()+"/"+(tipoDocumento.getCodigo().equals("VTA")?"jaspers/facturacion/RPNotaVentaDetalle.jasper":"jaspers/facturacion/RPFacturaDetalle.jasper"));
					else 
						throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
					
					if (tipoDocumento.getReembolso().equals("S")){
						parametrosMap.put("reembolso", "S");
						parametrosMap.put("comision", "N");
						parametrosMap.put("reembolsoGasto", "REEMBOLSO DE GASTO");
					}else if (tipoDocumento.getCodigo().equals("FCO")){
						parametrosMap.put("reembolso", "N");
						parametrosMap.put("comision", "S");
						parametrosMap.put("reembolsoGasto", "COMISION DE AGENCIA");
					}else{
						parametrosMap.put("reembolso", "N");
						parametrosMap.put("comision", "N");
						parametrosMap.put("reembolsoGasto", "");
					}
					
					parametrosMap.put("ivaPorcentaje", String.valueOf(Parametros.getIVA()));
					
					parametrosMap.put("dataSourceDetail", dataSourceDetail);
					parametrosMap.put("proveedoresMap", proveedoresMap);
					parametrosMap.put("productosMap", productosMap);
					parametrosMap.put("numeroFactura", formatoSerial.format(factura.getNumero()!=null?factura.getNumero().doubleValue():0D));
					String fecha = factura.getFechaFactura().toString();
					String year = fecha.substring(0,4);
					String month = fecha.substring(5,7);
					String day = fecha.substring(8,10);
					OficinaIf oficina = SessionServiceLocator.getOficinaSessionService().getOficina(factura.getOficinaId());
					CiudadIf ciudad = SessionServiceLocator.getCiudadSessionService().getCiudad(oficina.getCiudadId());
					String nombreCiudad = ciudad.getNombre();
					String fechaFactura = nombreCiudad + ", " + day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + year;
					parametrosMap.put("fechaCreacion", fechaFactura);
					parametrosMap.put("nombreOficina", oficina.getNombre());
					parametrosMap.put("direccionOficina", (!oficina.getDireccion().equals("S/D") && !oficina.getDireccion().equals("S/N") && !oficina.getDireccion().trim().equals(""))?oficina.getDireccion():"");
					parametrosMap.put("telefonoOficina", (!oficina.getTelefono().equals("S/D") && !oficina.getTelefono().equals("S/N") && !oficina.getTelefono().trim().equals(""))?oficina.getTelefono():"");
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("razonSocialEmpresa", empresa.getNombre());
					parametrosMap.put("rucEmpresa", empresa.getRuc());
					ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(factura.getClienteoficinaId());
					ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
					parametrosMap.put("razonSocialCliente", cliente.getRazonSocial());
					parametrosMap.put("descuentoTotal", factura.getDescuento().add(factura.getDescuentoGlobal()).doubleValue());
					parametrosMap.put("porcentajeComision", factura.getPorcentajeComisionAgencia());
					parametrosMap.put("otroImpuestoTotal", factura.getOtroImpuesto());
					Double valor = factura.getValor().doubleValue();
					Double descuento = factura.getDescuento().doubleValue() + factura.getDescuentoGlobal().doubleValue();
					Double porcentajeComision = factura.getPorcentajeComisionAgencia()!=null?factura.getPorcentajeComisionAgencia().doubleValue():0D;
					Double comision = ((valor - descuento) * porcentajeComision) / 100D;
					Double iva = factura.getIva().doubleValue();
					Double otroImpuesto = factura.getOtroImpuesto().doubleValue();
					Double grandTotal = valor + iva + otroImpuesto - descuento + comision;
					parametrosMap.put("grandTotal", Utilitarios.redondeoValor(grandTotal));
					String totalFactura = formatoDecimal.format(Utilitarios.redondeoValor(grandTotal));
					FormaPagoIf formaPago = SessionServiceLocator.getFormaPagoSessionService().getFormaPago(factura.getFormapagoId());
					String detallePagos = formaPago.getNombre() + ": " + "$ " + totalFactura;
					parametrosMap.put("detallePagos", detallePagos);
					String parteDecimal = totalFactura.substring(totalFactura.indexOf('.'), totalFactura.length());
					Double dParteDecimal = 0.0;
					if (!parteDecimal.isEmpty())
						dParteDecimal = Double.valueOf(parteDecimal);
					MonedaIf moneda = SessionServiceLocator.getMonedaSessionService().getMoneda(factura.getMonedaId());
					String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(totalFactura, dParteDecimal, new int[] { 200 }, false, moneda);
					//String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","").replaceAll("DÓLARES", "");
					String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
					String cantidadLetrasSegundaLinea =" ";
					
					if(cantidadLetrasPrimeraLinea.length()>50)
					{
						String letras=cantidadLetrasPrimeraLinea;
						cantidadLetrasPrimeraLinea=letras.substring(0,50);
						cantidadLetrasSegundaLinea=letras.substring(50,letras.length());
					}
						
					parametrosMap.put("cantidadEnLetras", cantidadLetrasPrimeraLinea);
					parametrosMap.put("cantidadEnLetras2", cantidadLetrasSegundaLinea);
					parametrosMap.put("valorComision", Utilitarios.redondeoValor(comision));
					
					if(tipoDocumento.getReembolso().equals("S")){
						opcion = JOptionPane.showOptionDialog(null, "¿Desea presentar el IVA en la Factura de Reembolso?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
						if (opcion == JOptionPane.YES_OPTION) {
							subtotalReporte = Utilitarios.redondeoValor(factura.getValor().doubleValue() / 1.12);
							ivaReporte = Utilitarios.redondeoValor((factura.getValor().doubleValue() / 1.12) * 0.12);
							parametrosMap.put("valorTotal", subtotalReporte);
							parametrosMap.put("totalIvaCero", totalIvaCero);
							parametrosMap.put("ivaTotal", ivaReporte);
							parametrosMap.put("mostrarIVA", "S");
							parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
						}else{
							subtotalReporte = Utilitarios.redondeoValor(factura.getValor().doubleValue());
							ivaReporte = Utilitarios.redondeoValor(factura.getIva().doubleValue());
							parametrosMap.put("valorTotal", subtotalReporte);
							parametrosMap.put("totalIvaCero", totalIvaCero);
							parametrosMap.put("ivaTotal", ivaReporte);
							parametrosMap.put("mostrarIVA", "N");
							parametrosMap.put("porcentajeIVA", formatoEntero.format(IVA_CERO.doubleValue()));
						}
					}else{
						subtotalReporte = Utilitarios.redondeoValor(factura.getValor().doubleValue());
						ivaReporte = Utilitarios.redondeoValor(factura.getIva().doubleValue());
						parametrosMap.put("valorTotal", subtotalReporte);
						parametrosMap.put("totalIvaCero", totalIvaCero);
						parametrosMap.put("ivaTotal", ivaReporte);
						parametrosMap.put("mostrarIVA", "S");
						parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
					}
					
					ReportModelImpl.processReport(fileName, parametrosMap, dataSourceCollection, true);
				}
			} else {
				SpiritAlert.createAlert("No existen datos para imprimir",SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
}*/
	
	public void report() {
		try {
			Double subtotalReporte = 0D;
			Double ivaReporte = 0D;
			ivaFinalReporte = 0D;
			subtotalFinalReporte = 0D;
			if (FacturaDetalleColeccion.size() > 0) {
				String si = "Si"; 
    	        String no = "No"; 
    	        Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir la Factura?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
				if (opcion == JOptionPane.YES_OPTION) {
					
					Long idParametro = factura.getId();
					TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(factura.getTipodocumentoId());
									
					PedidoIf pedidoReportePresupuesto = SessionServiceLocator.getPedidoSessionService().getPedido(factura.getPedidoId());
										
					boolean resumirConcepto = false;
					
					boolean facturaReembolsoNormal = false;
					boolean facturaReembolsoIvaEnTotal = false;
					boolean facturaReembolsoIvaEnDetalles = false;
					if(tipoDocumento.getReembolso().equals("S") && tipoDocumento.getCodigo().equals("FAR") ){
						FacturaReembolsoFormatoModel jdFacturacionPresupuesto = new FacturaReembolsoFormatoModel(Parametros.getMainFrame());
						if(jdFacturacionPresupuesto.getRbFormatoNormal().isSelected()){
							facturaReembolsoNormal = true;
						}else if(jdFacturacionPresupuesto.getRbFormatoIVAenTotal().isSelected()){
							facturaReembolsoIvaEnTotal = true;
						}else if(jdFacturacionPresupuesto.getRbFormatoIVAenDetalles().isSelected()){
							facturaReembolsoIvaEnDetalles = true;
						}
					}else if(pedidoReportePresupuesto.getTiporeferencia().equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())){
						int opcionDetalle = JOptionPane.showOptionDialog(null, "¿Desea resumir el detalle de cada proveedor?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
						if (opcionDetalle == JOptionPane.YES_OPTION) {
							resumirConcepto = true;
						}
					}		
										
					String codigoReferencia = "";
					PlanMedioFormaPagoIf planMedioFormaPagoReporte = null;
					
					if(pedidoReportePresupuesto.getReferencia() != null){
						if(pedidoReportePresupuesto.getTiporeferencia().equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())){
							/*PresupuestoIf presupuestoReporte = (PresupuestoIf)SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(pedidoReportePresupuesto.getReferencia()).iterator().next();
							Map aMap = new HashMap();
							aMap.put("presupuestoId", presupuestoReporte.getId());
							aMap.put("reporte", "S");
							Collection PresupuestoDetalleReporte = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByQuery(aMap);
							if(PresupuestoDetalleReporte.size() > 0)*/
								codigoReferencia = pedidoReportePresupuesto.getReferencia();							
						
						}else if(pedidoReportePresupuesto.getTiporeferencia().equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra())){
							/*PlanMedioIf planMedioReporte = (PlanMedioIf)SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigo(pedidoReportePresupuesto.getReferencia()).iterator().next();
							PlanMedioMesIf planMedioMesReporte = (PlanMedioMesIf)SessionServiceLocator.getPlanMedioMesSessionService().findPlanMedioMesByPlanmedioId(planMedioReporte.getId()).iterator().next();
							Collection planMedioDetalleReporte = SessionServiceLocator.getPlanMedioDetalleSessionService().findPlanMedioDetalleByPlanMedioMesId(planMedioMesReporte.getId());
							
							if(planMedioDetalleReporte.size() > 0)*/
								codigoReferencia = pedidoReportePresupuesto.getReferencia();	
							
							Collection planMedioFormaPagoColl = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(factura.getPedidoId());
							if (planMedioFormaPagoColl.size() > 0){
								Iterator planMedioFormaPagoIt = planMedioFormaPagoColl.iterator();
								planMedioFormaPagoReporte = (PlanMedioFormaPagoIf)planMedioFormaPagoIt.next();
							}
						}
					}
					
					//para saber el tipo de facturacion cuando es presupuesto
					String tipoFacturacionPresupuesto = "";
					if(pedidoReportePresupuesto.getReferencia() != null 
							&& pedidoReportePresupuesto.getTiporeferencia().equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())){
						Collection facturacionPresupuestos = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByFacturaId(factura.getId());
						Iterator facturacionPresupuestosIt = facturacionPresupuestos.iterator();
						while(facturacionPresupuestosIt.hasNext()){
							PresupuestoFacturacionIf presupuestoFacturacion = (PresupuestoFacturacionIf)facturacionPresupuestosIt.next();
							tipoFacturacionPresupuesto = presupuestoFacturacion.getTipo();
						}
					}				
					
					// Inicializa los datos del reporte que viene del pedido
					Collection dataSourceCollection = initializeBeanCollection(planMedioFormaPagoReporte,idParametro, false, 
							pedidoReportePresupuesto, tipoDocumento, "N", facturaReembolsoIvaEnDetalles, 
							facturaReembolsoNormal, resumirConcepto, tipoFacturacionPresupuesto);
					Map proveedoresMap = getProveedoresMap(factura);
					Map productosMap = getProductosMap(factura);
					JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(dataSourceCollection);					
					dataSourceCollection = initializeBeanCollection(planMedioFormaPagoReporte,idParametro, true, 
							pedidoReportePresupuesto, tipoDocumento, "N", facturaReembolsoIvaEnDetalles, 
							facturaReembolsoNormal, resumirConcepto, tipoFacturacionPresupuesto);
					
					//SAP y Producto Cliente
					String autorizacionSAP = "";
					String productoCliente = "";
					
					if(factura.getAutorizacionSap() != null && !factura.getAutorizacionSap().equals("")){
						autorizacionSAP = factura.getAutorizacionSap();
					}
					
					//busco el producto cliente
					if (pedido.getTiporeferencia() != null 
							&& pedido.getTiporeferencia().equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())){
						
						Collection presupuestos = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigoAndEmpresaId(pedido.getReferencia(), Parametros.getIdEmpresa());
						Iterator presupuestosIt = presupuestos.iterator();
						while(presupuestosIt.hasNext()){
							PresupuestoIf presupuesto = (PresupuestoIf)presupuestosIt.next();
							Collection presupuestoProductos = SessionServiceLocator.getPresupuestoProductoSessionService().findPresupuestoProductoByPresupuestoId(presupuesto.getId());
							Iterator presupuestoProductosIt = presupuestoProductos.iterator();
							while(presupuestoProductosIt.hasNext()){
								PresupuestoProductoIf presupuestoProductoIf = (PresupuestoProductoIf)presupuestoProductosIt.next();
								ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(presupuestoProductoIf.getProductoClienteId());
								productoCliente = productoClienteIf.getNombre();
							}
						}						
					}
					
					else if (pedido.getTiporeferencia() != null 
							&& pedido.getTiporeferencia().equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra())){
						
						String referencia = pedido.getReferencia();
						//si es caso multiple (varios presupuestos en una misma factura)
						if(pedido.getReferencia().equals("M")){
							//busco en plan medio forma de pago (tiene muchos menos registros que plan medio facturacion)
							Collection planesMedioFormaPago = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(pedido.getId());
							Iterator planesMedioFormaPagoIt = planesMedioFormaPago.iterator();
							while(planesMedioFormaPagoIt.hasNext()){
								PlanMedioFormaPagoIf planMedioFormaPago = (PlanMedioFormaPagoIf)planesMedioFormaPagoIt.next();
								PlanMedioIf planMedio = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(planMedioFormaPago.getPlanMedioId());
								referencia = planMedio.getCodigo(); 
							}
						}
						
						PlanMedioIf planMedioReporte = (PlanMedioIf)SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndEstadosAprobadoPedido(referencia, Parametros.getIdEmpresa()).iterator().next();
						OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(planMedioReporte.getOrdenTrabajoDetalleId());
						OrdenTrabajoIf ordenTrabajoIf = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalleIf.getOrdenId());
						Collection ordenTrabajoProductos = SessionServiceLocator.getOrdenTrabajoProductoSessionService().findOrdenTrabajoProductoByOrdenTrabajoId(ordenTrabajoIf.getId());
						Iterator ordenTrabajoProductosIt = ordenTrabajoProductos.iterator();
						while(ordenTrabajoProductosIt.hasNext()){
							OrdenTrabajoProductoIf ordenTrabajoProducto = (OrdenTrabajoProductoIf)ordenTrabajoProductosIt.next();
							ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(ordenTrabajoProducto.getProductoClienteId());
							productoCliente = productoClienteIf.getNombre();
						}
					}
					
					HashMap parametrosMap = new HashMap();
										
					if (tipoDocumento.getReembolso().equals("S")){
						parametrosMap.put("reembolso", "S");
						parametrosMap.put("comision", "N");
						if(!autorizacionSAP.equals("") && !productoCliente.equals("")){
							parametrosMap.put("reembolsoGasto", "SAP: " + autorizacionSAP + "  |  PRODUCTO: " + productoCliente +  "\nREEMBOLSO DE GASTO\n");
						}else if(!autorizacionSAP.equals("")){
							parametrosMap.put("reembolsoGasto", "SAP: " + autorizacionSAP + "\nREEMBOLSO DE GASTO\n");
						}else{
							parametrosMap.put("reembolsoGasto", "REEMBOLSO DE GASTO\n");
						}
					}
					else if (tipoDocumento.getCodigo().equals("FCO")){
						String preimpresoFacturaReembolso = "";
						
						Map pedidoReembolsoMap = new HashMap();
						pedidoReembolsoMap.put("oficinaId", pedido.getOficinaId());
						pedidoReembolsoMap.put("clienteoficinaId", pedido.getClienteoficinaId());
						pedidoReembolsoMap.put("tiporeferencia", pedido.getTiporeferencia());
						pedidoReembolsoMap.put("referencia", pedido.getReferencia());
						pedidoReembolsoMap.put("estado", pedido.getEstado());
						Collection pedidos = SessionServiceLocator.getPedidoSessionService().findPedidoByQuery(pedidoReembolsoMap);
						Iterator pedidosIt = pedidos.iterator();
						while(pedidosIt.hasNext()){
							PedidoIf pedidoReembolso = (PedidoIf)pedidosIt.next();
							//veo que no sea el mismo pedido de comision sino el de reembolso
							if(pedidoReembolso.getTipodocumentoId().compareTo(pedido.getTipodocumentoId()) != 0){
								Map facturaReembolsoMap = new HashMap();
								facturaReembolsoMap.put("estado", pedidoReembolso.getEstado());
								facturaReembolsoMap.put("pedidoId", pedidoReembolso.getId());
								Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByQuery(facturaReembolsoMap, Parametros.getIdEmpresa());
								Iterator facturasIt = facturas.iterator();
								while(facturasIt.hasNext()){
									FacturaIf facturaReembolso = (FacturaIf)facturasIt.next();
									preimpresoFacturaReembolso = facturaReembolso.getPreimpresoNumero();
								}
							}
						}
						
						parametrosMap.put("reembolso", "N");
						parametrosMap.put("comision", "S");
						parametrosMap.put("reembolsoGasto", "COMISION DE AGENCIA \n SOBRE F: " + preimpresoFacturaReembolso + "\n");
						//recien ahora limpio la variable porque si lo hago en clean() se borra antes de tiempo 
						preimpresoFacturaReembolso = "";
					}
					else{
						parametrosMap.put("reembolso", "N");
						parametrosMap.put("comision", "N");
						//parametrosMap.put("reembolsoGasto", "");
						
						if(!autorizacionSAP.equals("")){
							parametrosMap.put("reembolsoGasto", "SAP: " + autorizacionSAP + "\n");
						}else{
							parametrosMap.put("reembolsoGasto", "");
						}
					}
						
					parametrosMap.put("campanaPeriodo", campanaPeriodo);
					parametrosMap.put("dataSourceDetail", dataSourceDetail);
					parametrosMap.put("proveedoresMap", proveedoresMap);
					parametrosMap.put("productosMap", productosMap);
					parametrosMap.put("numeroFactura", formatoSerial.format(factura.getNumero().doubleValue()));
					String fecha = factura.getFechaFactura().toString();
					String year = fecha.substring(0,4);
					String month = fecha.substring(5,7);
					String day = fecha.substring(8,10);
					OficinaIf oficina = SessionServiceLocator.getOficinaSessionService().getOficina(factura.getOficinaId());
					CiudadIf ciudad = SessionServiceLocator.getCiudadSessionService().getCiudad(oficina.getCiudadId());
					String nombreCiudad = ciudad.getNombre();
					String fechaFactura = nombreCiudad + ", " + day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + year;
					parametrosMap.put("fechaCreacion", fechaFactura);
					
					String fechaVencimiento = "";
					if(pedido.getFechaVencimiento() != null){
						fechaVencimiento = Utilitarios.getFechaUppercase(pedido.getFechaVencimiento());
					}
					parametrosMap.put("fechaVencimiento", fechaVencimiento);
					
					parametrosMap.put("nombreOficina", oficina.getNombre());
					parametrosMap.put("direccionOficina", (!oficina.getDireccion().equals("S/D") && !oficina.getDireccion().equals("S/N") && !oficina.getDireccion().trim().equals(""))?oficina.getDireccion():"");
					parametrosMap.put("telefonoOficina", (!oficina.getTelefono().equals("S/D") && !oficina.getTelefono().equals("S/N") && !oficina.getTelefono().trim().equals(""))?oficina.getTelefono():"");
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("razonSocialEmpresa", empresa.getNombre());
					parametrosMap.put("rucEmpresa", empresa.getRuc());
					ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(factura.getClienteoficinaId());
					ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
					parametrosMap.put("razonSocialCliente", cliente.getRazonSocial());
					String presupuestoReferencia = SpiritConstants.getEmptyCharacter();
					if (getCbMostrarReferenciaFactura().isSelected()) {
						if (!getTxtPresupuesto().getText().equals(SpiritConstants.getEmptyCharacter()))
							presupuestoReferencia = getTxtPresupuesto().getText().split(" ")[0];
						if (!getTxtPlanMedios().getText().equals(SpiritConstants.getEmptyCharacter()))
							presupuestoReferencia = getTxtPlanMedios().getText().split(" ")[0];
					}
					parametrosMap.put("presupuesto", presupuestoReferencia);
					parametrosMap.put("descuentoTotal", factura.getDescuento().add(factura.getDescuentoGlobal()).doubleValue());
					parametrosMap.put("descuentosVariosTotal", factura.getDescuentosVarios().doubleValue());
					parametrosMap.put("porcentajeComision", factura.getPorcentajeComisionAgencia());
					parametrosMap.put("otroImpuestoTotal", factura.getOtroImpuesto());
					Double valor = factura.getValor().doubleValue();
					Double descuento =  factura.getDescuento().doubleValue() + factura.getDescuentoGlobal().doubleValue();
					Double descuentosVarios = factura.getDescuentosVarios().doubleValue();
					Double porcentajeComision = factura.getPorcentajeComisionAgencia().doubleValue();
					Double comision = ((valor - descuento - descuentosVarios) * porcentajeComision) / 100D;
					Double iva = factura.getIva().doubleValue();
					Double otroImpuesto = factura.getOtroImpuesto().doubleValue();
					Double grandTotal = valor - descuento - descuentosVarios + comision + otroImpuesto + iva;
					parametrosMap.put("grandTotal", Utilitarios.redondeoValor(grandTotal));
					String totalFactura = formatoDecimal.format( Utilitarios.redondeoValor(grandTotal));
					FormaPagoIf formaPago = SessionServiceLocator.getFormaPagoSessionService().getFormaPago(factura.getFormapagoId());
					String detallePagos = formaPago.getNombre() + ": " + "$ " + totalFactura;
					parametrosMap.put("detallePagos", detallePagos);
					String parteDecimal = totalFactura.substring(totalFactura.indexOf('.'), totalFactura.length());
					Double dParteDecimal = 0.0;
					
					if (!parteDecimal.isEmpty())
						dParteDecimal = Double.valueOf(parteDecimal);
					
					MonedaIf moneda = SessionServiceLocator.getMonedaSessionService().getMoneda(factura.getMonedaId());
					String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(totalFactura, dParteDecimal, new int[] { 200 }, false, moneda);
					String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
					parametrosMap.put("cantidadEnLetras", cantidadLetrasPrimeraLinea);
					parametrosMap.put("valorComision",  Utilitarios.redondeoValor(comision));
							 
					String cantidadLetrasSegundaLinea =" ";
					
					if(cantidadLetrasPrimeraLinea.length()>75)
					{
						String letras=cantidadLetrasPrimeraLinea;
						cantidadLetrasPrimeraLinea=letras.substring(0,75);
						cantidadLetrasSegundaLinea=letras.substring(75,letras.length());
					}
						
					parametrosMap.put("cantidadEnLetras", cantidadLetrasPrimeraLinea);
					parametrosMap.put("cantidadEnLetras2", cantidadLetrasSegundaLinea);
										
					if(facturaReembolsoIvaEnTotal){
						//parametrosMap.put("reembolso", "N");
						parametrosMap.put("valorTotal", subtotalFinalReporte);
						parametrosMap.put("totalIvaCero", totalIvaCero);
						parametrosMap.put("ivaTotal", ivaFinalReporte);
						parametrosMap.put("mostrarIVA", "S");
						parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
						
					}else if(facturaReembolsoNormal){
						//parametrosMap.put("reembolso", "N");
						subtotalReporte =  Utilitarios.redondeoValor(factura.getValor().doubleValue());
						ivaReporte =  Utilitarios.redondeoValor(factura.getIva().doubleValue());
						parametrosMap.put("valorTotal", subtotalReporte);
						parametrosMap.put("totalIvaCero", totalIvaCero);
						parametrosMap.put("ivaTotal", ivaReporte);
						parametrosMap.put("mostrarIVA", "N");
						parametrosMap.put("porcentajeIVA", formatoEntero.format(IVA_CERO.doubleValue()));					
					}
					else if(facturaReembolsoIvaEnDetalles){
						subtotalReporte =  Utilitarios.redondeoValor(factura.getValor().doubleValue());
						ivaReporte =  Utilitarios.redondeoValor(factura.getIva().doubleValue());
						parametrosMap.put("valorTotal", subtotalReporte);
						parametrosMap.put("totalIvaCero", totalIvaCero);
						parametrosMap.put("ivaTotal", ivaReporte);
						parametrosMap.put("mostrarIVA", "N");
						parametrosMap.put("porcentajeIVA", formatoEntero.format(IVA_CERO.doubleValue()));					
					}
					else{	
						subtotalReporte =  Utilitarios.redondeoValor(factura.getValor().doubleValue());
						ivaReporte =  Utilitarios.redondeoValor(factura.getIva().doubleValue());
						parametrosMap.put("valorTotal", subtotalReporte);
						parametrosMap.put("totalIvaCero", totalIvaCero);
						parametrosMap.put("ivaTotal", ivaReporte);
						parametrosMap.put("mostrarIVA", "S");
						parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
					}
					
					parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
					parametrosMap.put("ivaPorcentaje", String.valueOf(Parametros.getIVA()));
					
					String fileName = (tipoDocumento.getCodigo().equals("VTA"))?"jaspers/facturacion/RPNotaVenta.jasper":"jaspers/facturacion/RPFactura.jasper";
					
					/*if(bonificacion > 0 && !tipoDocumento.getCodigo().equals("VTA"))
						fileName = "jaspers/facturacion/RPFacturaConBonificacion.jasper";*/
										
					if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) ){
						//parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes()+"/"+(tipoDocumento.getCodigo().equals("VTA")?"jaspers/facturacion/RPNotaVentaDetalle.jasper":"jaspers/facturacion/RPFacturaDetalle.jasper"));
						if(facturaReembolsoIvaEnDetalles){
							fileName = "jaspers/facturacion/RPFacturaReembolsoIvaIncluidoDetalle.jasper";
							parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPFacturaDetalleIvaIncluido.jasper");
						}else if(tipoDocumento.getCodigo().equals("VTA")){
							parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPNotaVentaDetalle.jasper");
						}else if (tipoDocumento.getReembolso().equals("S")){
							fileName = "jaspers/facturacion/RPFacturaReembolso.jasper";
							parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPFacturaReembolsoDetalle.jasper");
						} else {
							parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPFacturaDetalle.jasper");
						}
					}						
					else{
						throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
					}
										
					ReportModelImpl.processReport(fileName, parametrosMap, dataSourceCollection, true);
				}
			} else {
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private Map getProveedoresMap(FacturaIf factura) {
		Map proveedoresMap = new HashMap();
		Collection rowCollection = null;
		try {
			rowCollection = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(factura.getId());
			Iterator itRows = rowCollection.iterator();
			TipoDocumentoIf tipoDocumentoFactura = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(factura.getTipodocumentoId());
			while (itRows.hasNext()) {
				FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
				ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(bean.getProductoId());
				ClienteOficinaIf proveedorOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(producto.getProveedorId());
				String facturaReembolso = "";
				String facturaComision = "";
				if(tipoDocumentoFactura.getCodigo().equals("FAR") && !bean.getComprasReembolsoAsociadas().equals("")) {
					facturaReembolso = "\nF: " + bean.getComprasReembolsoAsociadas();
				}
				
				ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
				TipoIdentificacionIf tipoIdentificacion = SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacion(proveedor.getTipoidentificacionId());
				
				if(tipoDocumentoFactura.getCodigo().equals("FCO")){
					proveedoresMap.put(bean.getId(), facturaComision);
				}else{
					proveedoresMap.put(bean.getId(), proveedor.getRazonSocial() + "\n" + tipoIdentificacion.getNombre() + ": " + proveedor.getIdentificacion() + facturaReembolso);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}

		return proveedoresMap;
	}

	public void switchTab() {
		int selectedTab = this.getJtpFactura().getSelectedIndex();
		int tabCount = this.getJtpFactura().getTabCount();
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpFactura().setSelectedIndex(selectedTab);
	}

	public void addDetail() {

	}

	public void updateDetail() {

	}
	
	public void deleteDetail() {
		
	}
	
	private Collection initializeBeanCollection(PlanMedioFormaPagoIf planMedioFormaPagoIf, Long idParametro, 
			boolean isHeader, PedidoIf pedido, TipoDocumentoIf tipoDocumento, 
			String tipoFacturacion, boolean facturaReembolsoIvaEnDetalles, boolean facturaReembolsoNormal, 
			boolean resumirConcepto, String tipoFacturacionPresupuesto) {
		ArrayList reportRows = new ArrayList();
		Collection rowCollection = null;
		BigDecimal descuentoPlanMedios = new BigDecimal(0);
		String tipoReferencia = pedido.getTiporeferencia();
		try {
			
			if(tipoReferencia.equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra())){
				
				String referencia = pedido.getReferencia();
				//si es caso multiple (varios presupuestos en una misma factura)
				if(pedido.getReferencia().equals("M")){
					//busco en plan medio forma de pago (tiene muchos menos registros que plan medio facturacion)
					Collection planesMedioFormaPago = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(pedido.getId());
					Iterator planesMedioFormaPagoIt = planesMedioFormaPago.iterator();
					while(planesMedioFormaPagoIt.hasNext()){
						PlanMedioFormaPagoIf planMedioFormaPago = (PlanMedioFormaPagoIf)planesMedioFormaPagoIt.next();
						PlanMedioIf planMedio = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(planMedioFormaPago.getPlanMedioId());
						referencia = planMedio.getCodigo(); 
					}
				}
				
				PlanMedioIf planMedio = (PlanMedioIf)SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndEstadosAprobadoPedido(referencia, Parametros.getIdEmpresa()).iterator().next();
				OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(planMedio.getOrdenTrabajoDetalleId());
				OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
				CampanaIf campana = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajo.getCampanaId());

				Calendar fechaInicio = GregorianCalendar.getInstance();
				Calendar fechaFin    = GregorianCalendar.getInstance();
				fechaInicio.setTime(planMedioFormaPagoIf.getFechaInicio());
				fechaFin.setTime(planMedioFormaPagoIf.getFechaFin());

				int dayInicio = fechaInicio.get(Calendar.DATE);
				int dayFin    = fechaFin.get(Calendar.DATE);
				int month    = fechaFin.get(Calendar.MONTH) + 1;

				String mesS = Utilitarios.getNombreMes(month);
				String anioS = Utilitarios.getYearFromDate(planMedioFormaPagoIf.getFechaFin());
				campanaPeriodo = "CAMPAÑA: " + campana.getNombre() + "\nPERIODO: DEL " + dayInicio + " AL " + dayFin + " DE " + mesS + " DE " + anioS + "\n";
			}/*else if(tipoReferencia.equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())){
				PresupuestoIf presupuesto = (PresupuestoIf)SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigoAndEmpresaId(codigoReferencia, Parametros.getIdEmpresa()).iterator().next();
				OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuesto.getOrdentrabajodetId());
				OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
				CampanaIf campana = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajo.getCampanaId());

				Calendar fecha = GregorianCalendar.getInstance();
				fecha.setTime(presupuesto.getFecha());
				
				int month    = fecha.get(Calendar.MONTH) + 1;
				String mesS = Utilitarios.getNombreMes(month);
				String anioS = Utilitarios.getYearFromDate(presupuesto.getFecha());
				campanaPeriodo = "CAMPAÑA: " + campana.getNombre() + "\nMES: " + mesS + " - " + anioS + "\n";
			}*/
			
			if((tipoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA) || tipoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA)) && !isHeader){
				Collection facturasNegociacionDirecta = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId());
				Iterator facturasNegociacionDirectaIt = facturasNegociacionDirecta.iterator();
				while(facturasNegociacionDirectaIt.hasNext()){
					FacturaIf facturaNegociacionDirecta = (FacturaIf)facturasNegociacionDirectaIt.next();
					
					Collection facturasDetalleNegociacionDirecta = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(facturaNegociacionDirecta.getId());
					Iterator facturasDetalleNegociacionDirectaIt = facturasDetalleNegociacionDirecta.iterator();
					while(facturasDetalleNegociacionDirectaIt.hasNext()){
						FacturaDetalleIf facturaDetalleNegociacionDirecta = (FacturaDetalleIf)facturasDetalleNegociacionDirectaIt.next();
						
						FacturaDetalleData bean = new FacturaDetalleData();
						bean.setDescripcion(facturaDetalleNegociacionDirecta.getDescripcion());
						bean.setCantidad(facturaDetalleNegociacionDirecta.getCantidad());
						BigDecimal precio = facturaDetalleNegociacionDirecta.getPrecioReal();
						bean.setPrecioReal(precio);
						
						bean.setDescuento(facturaDetalleNegociacionDirecta.getDescuento());
						if(precio.multiply(facturaDetalleNegociacionDirecta.getCantidad()).compareTo(new BigDecimal(0)) == 1){
							bean.setValor(precio.multiply(facturaDetalleNegociacionDirecta.getCantidad()));
						}else{
							// para que salga en blanco en el reporte (o sea en la factura)
							bean.setValor(null);
						}
						bean.setIva(facturaDetalleNegociacionDirecta.getIva());
						bean.setDescuentoGlobal(null);
						bean.setProductoId(facturaDetalleNegociacionDirecta.getProductoId());
						bean.setId(facturaDetalleNegociacionDirecta.getId());
						if(precio.compareTo(new BigDecimal(0)) == 1)
							reportRows.add(bean);
					}
				}
				return reportRows;
			}
			
			// No entra si es reembolso porque los detalles en caso de reembolso
			// deben venir de factura detalle
			else if (tipoDocumento.getReembolso().equals("N") && !tipoDocumento.getCodigo().equals("FCO") 
					&& !isHeader && pedido.getReferencia() != null && !pedido.getReferencia().equals("") && tipoReferencia.equals(TipoReferenciaPedido.PRESUPUESTO.getLetra()) && 
						(tipoFacturacionPresupuesto.equals(TIPO_PRESUPUESTO_FACTURACION_NORMAL)
								|| tipoFacturacionPresupuesto.equals(TIPO_PRESUPUESTO_FACTURACION_CLIENTE))){
					
				//si es facturación multiple
				if(pedido.getReferencia().equals("M")){
					Map<Long,Long> presupuestosDetalleMap = new HashMap<Long,Long>();
					Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId());
					Iterator facturasIt = facturas.iterator();
					while(facturasIt.hasNext()){
						FacturaIf factura = (FacturaIf)facturasIt.next();
						Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByFacturaId(factura.getId());
						Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
						while(presupuestosFacturacionIt.hasNext()){
							PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
							presupuestosDetalleMap.put(presupuestoFacturacionIf.getPresupuestoDetalleId(), presupuestoFacturacionIf.getPresupuestoDetalleId());
						}
					}
					ArrayList presupuestosDetalleIf = new ArrayList();
					Iterator presupuestosDetalleMapIt = presupuestosDetalleMap.keySet().iterator();
					while(presupuestosDetalleMapIt.hasNext()){
						Long presupuestoDetalleId = (Long)presupuestosDetalleMapIt.next();
						PresupuestoDetalleIf presupuestoDetalleIf = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoDetalleId);
						presupuestosDetalleIf.add(presupuestoDetalleIf);						
					}
					rowCollection = presupuestosDetalleIf;
				}
				else{
					PresupuestoIf presupuestoReporte = (PresupuestoIf)SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(pedido.getReferencia()).iterator().next();
					Map aMap = new HashMap();
					aMap.put("presupuestoId", presupuestoReporte.getId());
					// Solo si la facturacion del presupuesto es normal
					// (completa) se puede usar los detalles tipo reporte
					// caso contrario no coincidirian la suma de los detalles
					// con el total
										
					aMap.put("reporte", REPORTE_PRESUPUESTO_SI);
					rowCollection = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByQuery(aMap);
				}		
			 								
			}
			else if (tipoDocumento.getReembolso().equals("N") && !tipoDocumento.getCodigo().equals("FCO") 
					&& !isHeader && pedido.getReferencia() != null  && !pedido.getReferencia().equals("") && tipoReferencia.equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra())){				
				
				try{					
					/*Calendar calendarFechaInicio = GregorianCalendar.getInstance();
					Calendar calendarFechaFin    = GregorianCalendar.getInstance();
					calendarFechaInicio.setTime(planMedioFormaPagoIf.getFechaInicio());
					calendarFechaFin.setTime(planMedioFormaPagoIf.getFechaFin());
					
					int diaInicio = calendarFechaInicio.get(Calendar.DATE);
					int diaFin    = calendarFechaFin.get(Calendar.DATE);
					int mesInt    = calendarFechaFin.get(Calendar.MONTH) + 1;*/
					
					//String mes = Utilitarios.getNombreMes(mesInt);
					//String anio = Utilitarios.getYearFromDate(planMedioFormaPagoIf.getFechaFin());
				
					Collection rowCollectionTmp = null;
					
					ArrayList<FacturaDetalleIf> listaTmp = new ArrayList<FacturaDetalleIf>();
					
					FacturaDetalleData facturaDetalleIf1 = new FacturaDetalleData();
					FacturaDetalleData facturaDetalleIf2 = new FacturaDetalleData();
					FacturaDetalleData facturaDetalleIf3 = new FacturaDetalleData();
					FacturaDetalleData facturaDetalleIf4 = new FacturaDetalleData();
					
					// Forma de Facturacion segun el Plan de Medio Forma de Pago
					String formaFacturacioPMFP = planMedioFormaPagoIf.getTipoFormaPago();
					
					// COMPLETO "C"
					if(formaFacturacioPMFP.compareTo("C") == 0 ){
						//facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
						//facturaDetalleIf2.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
						// VERIFICAR SI ES TV-RADIO-PRENSA
						facturaDetalleIf3.setDescripcion("PAUTA EN LOS SIGUIENTES MEDIOS:\n");
						//listaTmp.add(facturaDetalleIf1);
						//listaTmp.add(facturaDetalleIf2);
						listaTmp.add(facturaDetalleIf3);
					
					// POR CANAL
					}else {
						
						PlanMedioIf planMedioReporte = (PlanMedioIf)SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndEstadosAprobadoPedido(pedido.getReferencia(), Parametros.getIdEmpresa()).iterator().next();
						
						CampanaProductoVersionIf version = null;
						
						FacturaIf facturaIf = SessionServiceLocator.getFacturaSessionService().getFactura(idParametro);
											
						descuentoPlanMedios = facturaIf.getDescuentoGlobal();
						Map aMapPlanMedioFacturacion = new HashMap();
						aMapPlanMedioFacturacion.put("planMedioId", planMedioReporte.getId());
						aMapPlanMedioFacturacion.put("pedidoId",facturaIf.getPedidoId());

						Collection planMedioFacturacionColl = SessionServiceLocator.getPlanMedioFacturacionSessionService().findPlanMedioFacturacionByQuery(aMapPlanMedioFacturacion);
						Iterator planMedioFacturacionCollIt = planMedioFacturacionColl.iterator();
						PlanMedioFacturacionIf planMedioFacturacionIf = (PlanMedioFacturacionIf)planMedioFacturacionCollIt.next();
												
						if(formaFacturacioPMFP.compareTo("L") == 0 ){
							
							ClienteOficinaIf clienteProveedor = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(planMedioFacturacionIf.getProveedorId());
							String proveedor = clienteProveedor.getDescripcion();
														
							//facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
							facturaDetalleIf2.setDescripcion("PROVEEDOR: "+ proveedor);
							//facturaDetalleIf3.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
							// VERIFICAR SI ES TV-RADIO-PRENSA
							facturaDetalleIf4.setDescripcion("PAUTA DE LOS SIGUIENTES PRODUCTOS:\n");
							//listaTmp.add(facturaDetalleIf1);
							listaTmp.add(facturaDetalleIf2);
							//listaTmp.add(facturaDetalleIf3);
							listaTmp.add(facturaDetalleIf4);	
						
						// PRODUCTO "P"
						}else if(formaFacturacioPMFP.compareTo("P") == 0 ){
							
							String producto  = "";
							
							if(planMedioFacturacionIf.getCampanaProductoVersionId() != null && planMedioFacturacionIf.getCampanaProductoVersionId().compareTo(new Long(0))==0){
								
								ArrayList<CampanaProductoVersionIf> listCampanaProductoVersion = new ArrayList<CampanaProductoVersionIf>();
								Collection ordenMedioColl = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planMedioReporte.getId());
								
								Iterator ordenMedioCollIt = ordenMedioColl.iterator();						
								while(ordenMedioCollIt.hasNext()){
									OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenMedioCollIt.next();
									Collection ordenMedioDetalleColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
									Iterator ordenMedioDetalleCollIt = ordenMedioDetalleColl.iterator();
									while(ordenMedioDetalleCollIt.hasNext()){
										OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleCollIt.next();
										CampanaProductoVersionIf campanaProductoVersionIf = SessionServiceLocator.getCampanaProductoVersionSessionService().getCampanaProductoVersion(ordenMedioDetalleIf.getCampanaProductoVersionId());
										int tmp = 0;
										for(CampanaProductoVersionIf com: listCampanaProductoVersion){
											if(com.getId().compareTo(campanaProductoVersionIf.getId())!=0){
												tmp++;
											}
										}
										if(tmp==listCampanaProductoVersion.size()){
											listCampanaProductoVersion.add(campanaProductoVersionIf);
										}
									}							
								}
								
								for(CampanaProductoVersionIf campanaProductoVersionIf : listCampanaProductoVersion ){
									producto = producto.concat(campanaProductoVersionIf.getNombre());
									producto = producto.concat(", ");
								}									
								
							}else{
								ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(planMedioFacturacionIf.getProductoClienteId());
								
								if(planMedioFacturacionIf.getCampanaProductoVersionId() != null){
									version = SessionServiceLocator.getCampanaProductoVersionSessionService().getCampanaProductoVersion(planMedioFacturacionIf.getCampanaProductoVersionId());
									producto = productoClienteIf.getNombre()+ " (" + version + ")";
								}else{
									producto = productoClienteIf.getNombre();
								}
							}
							
							//facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
							facturaDetalleIf2.setDescripcion("PRODUCTO: "+ producto);
							//facturaDetalleIf3.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
							// VERIFICAR SI ES TV-RADIO-PRENSA
							facturaDetalleIf4.setDescripcion("PAUTA EN LOS SIGUIENTES MEDIOS:\n");
							//listaTmp.add(facturaDetalleIf1);
							listaTmp.add(facturaDetalleIf2);
							//listaTmp.add(facturaDetalleIf3);
							listaTmp.add(facturaDetalleIf4);
						
						// VERSION "V"
						}else if(formaFacturacioPMFP.compareTo("V") == 0 ){
							//facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
							version = SessionServiceLocator.getCampanaProductoVersionSessionService().getCampanaProductoVersion(planMedioFacturacionIf.getCampanaProductoVersionId());
							facturaDetalleIf2.setDescripcion("VERSION: "+ version.getNombre());
							//facturaDetalleIf3.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
							// VERIFICAR SI ES TV-RADIO-PRENSA
							facturaDetalleIf4.setDescripcion("PAUTA EN LOS SIGUIENTES MEDIOS:\n");
							//listaTmp.add(facturaDetalleIf1);
							listaTmp.add(facturaDetalleIf2);
							//listaTmp.add(facturaDetalleIf3);
							listaTmp.add(facturaDetalleIf4);
						
						// ES POR COMISIONES DEL MEDIO "M"
						}else if(formaFacturacioPMFP.compareTo("M") == 0 ){
							//facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
							//facturaDetalleIf2.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
							facturaDetalleIf3.setDescripcion("COMISIÓN DE AGENCIA POR MUTUO CLIENTE:\n");
							//listaTmp.add(facturaDetalleIf1);
							//listaTmp.add(facturaDetalleIf2);
							listaTmp.add(facturaDetalleIf3);
						}
					}

					rowCollectionTmp = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(idParametro);
					listaTmp.addAll(rowCollectionTmp);
					
					rowCollection = listaTmp;
				}
				catch (Exception e){
					e.printStackTrace();
				}								
			}
			else if(!isHeader)
				rowCollection = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(idParametro);
			else
				rowCollection = SessionServiceLocator.getFacturaSessionService().findFacturaById(idParametro);
							
			
			Iterator itRows = rowCollection.iterator();

			while (itRows.hasNext()) {
				// No entra si es reembolso porque los detalles en caso de reembolso
				// deben venir de factura detalle
				if (tipoDocumento.getReembolso().equals("N") && !tipoDocumento.getCodigo().equals("FCO") 
						&& !isHeader && pedido.getReferencia() != null && !pedido.getReferencia().equals("") && tipoReferencia.equals(TipoReferenciaPedido.PRESUPUESTO.getLetra()) &&
							(tipoFacturacionPresupuesto.equals(TIPO_PRESUPUESTO_FACTURACION_NORMAL)
									|| tipoFacturacionPresupuesto.equals(TIPO_PRESUPUESTO_FACTURACION_CLIENTE))){
					PresupuestoDetalleIf presupuestoDetalle = (PresupuestoDetalleIf) itRows.next();
					
					// si no es una facturacion parcial se añade todos los
					// detalles
					// si es un facturacion parcial se añaden solo los
					// detalles del mapa
					//if(!tipoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_PARCIAL)){
						
						FacturaDetalleData bean = new FacturaDetalleData();
						
						String concepto = presupuestoDetalle.getConcepto();
												
						if(presupuestoDetalle.getFechaPublicacion() != null){
							String fechaCompleta = Utilitarios.getFechaDiaSemanaDiaMesAnioUppercase(presupuestoDetalle.getFechaPublicacion());
							concepto = concepto + "\n\nPUBLICACIÓN: " + fechaCompleta + "\n";
						}
						
						String proveedor = "";
						String producto = "";
						String publicacion = "";
						if(resumirConcepto){
							if(presupuestoDetalle.getProveedorId() != null){
								ClienteOficinaIf proveedorDetalle = mapaClienteOficina.get(presupuestoDetalle.getProveedorId());
								proveedor = proveedorDetalle.getDescripcion();									
							}
							
							if(presupuestoDetalle.getProductoId() != null){	
								ProductoIf productoDetalle = mapaProductoProveedor.get(presupuestoDetalle.getProductoId());
								GenericoIf generico = mapaGenerico.get(productoDetalle.getGenericoId());
								producto = generico.getNombre();
								
								//si el proveedor es null porque el detalle es tipo reporte entonces lo busco a partir del producto
								if(presupuestoDetalle.getProveedorId() == null){
									ClienteOficinaIf proveedorDetalle = mapaClienteOficina.get(productoDetalle.getProveedorId());
									proveedor = proveedorDetalle.getDescripcion();
								}
							}
							
							
							if(presupuestoDetalle.getFechaPublicacion() != null){
								String fechaCompleta = Utilitarios.getFechaDiaSemanaDiaMesAnioUppercase(presupuestoDetalle.getFechaPublicacion());
								publicacion = "PUBLICACIÓN: " + fechaCompleta;
							}
							
							concepto = proveedor + "\n" + producto + "\n" + publicacion + "\n";
						}
						
						bean.setDescripcion(concepto);
						bean.setCantidad(presupuestoDetalle.getCantidad());
						
						BigDecimal precio = presupuestoDetalle.getPrecioventa();
						double descuentoEspecial = 0D;
						if(presupuestoDetalle.getPorcentajeDescuentoEspecialVenta() != null){
							descuentoEspecial = precio.doubleValue() * (presupuestoDetalle.getPorcentajeDescuentoEspecialVenta().doubleValue() / 100);
						}
						precio = BigDecimal.valueOf(precio.doubleValue() - descuentoEspecial);				
						
						// Si exite negociacion directa se le resta al
						// precio el porcentaje de negociacion
						if(presupuestoDetalle.getPorcentajeNegociacionDirecta() != null && presupuestoDetalle.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(0)) == 1){
							BigDecimal valorCanje = precio.multiply((presupuestoDetalle.getPorcentajeNegociacionDirecta().divide(new BigDecimal(100))));
							precio = precio.subtract(valorCanje);
						}
						
						// seteo el precio
						bean.setPrecioReal(precio);
						
						bean.setDescuento(presupuestoDetalle.getPorcentajeDescuentoAgenciaVenta());
						if(precio.multiply(presupuestoDetalle.getCantidad()).compareTo(new BigDecimal(0)) == 1){
							bean.setValor(precio.multiply(presupuestoDetalle.getCantidad()));
						}else{
							// para que salga en blanco en el reporte (o sea
							// en la factura)
							bean.setValor(null);
						}
						bean.setIva(presupuestoDetalle.getIva());
						bean.setDescuentoGlobal(presupuestoDetalle.getIva());
						bean.setProductoId(presupuestoDetalle.getProductoId());
						bean.setId(presupuestoDetalle.getId());
						
						if(precio.compareTo(new BigDecimal(0)) == 1)
							reportRows.add(bean);
					//}		
				}
				else if (tipoDocumento.getReembolso().equals("N") && !tipoDocumento.getCodigo().equals("FCO") 
						&& !isHeader && pedido.getReferencia() != null && !pedido.getReferencia().equals("") 
						&& tipoReferencia.equals(TipoReferenciaPedido.PRESUPUESTO.getLetra()) &&
						tipoFacturacionPresupuesto.equals(TIPO_PRESUPUESTO_FACTURACION_PARCIAL)){
					
					FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
					
					String concepto = bean.getDescripcion();
					String proveedor = "";
					String producto = "";
					String publicacion = "";
					if(resumirConcepto){
						if(bean.getProductoId() != null){	
							ProductoIf productoDetalle = mapaProductoProveedor.get(bean.getProductoId());
							GenericoIf generico = mapaGenerico.get(productoDetalle.getGenericoId());
							producto = generico.getNombre();
							ClienteOficinaIf proveedorDetalle = mapaClienteOficina.get(productoDetalle.getProveedorId());
							proveedor = proveedorDetalle.getDescripcion();
						}						
						
						concepto = proveedor + "\n" + producto + "\n" + publicacion + "\n";
					}
					bean.setDescripcion(concepto);
					
					reportRows.add(bean);
					
				}
				else if (tipoDocumento.getReembolso().equals("N") && !tipoDocumento.getCodigo().equals("FCO") 
						&& !isHeader && pedido.getReferencia() != null && !pedido.getReferencia().equals("") && tipoReferencia.equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra())){
					FacturaDetalleIf facturaDetalleIf = (FacturaDetalleIf) itRows.next();
					
					FacturaDetalleData bean = new FacturaDetalleData();				
					
					bean.setDescripcion(facturaDetalleIf.getDescripcion());
					bean.setCantidad(facturaDetalleIf.getCantidad());
					bean.setPrecioReal(facturaDetalleIf.getPrecioReal());
					bean.setDescuento(facturaDetalleIf.getDescuento());
					bean.setValor(facturaDetalleIf.getValor());
					bean.setIva(facturaDetalleIf.getIva());
					bean.setDescuentoGlobal(descuentoPlanMedios);
					bean.setProductoId(facturaDetalleIf.getProductoId());
					bean.setId(facturaDetalleIf.getId());
					reportRows.add(bean);				
				}
				
				else if (tipoDocumento.getReembolso().equals("S") && tipoDocumento.getCodigo().equals("FAR") && !isHeader && pedido.getReferencia() != null && !pedido.getReferencia().equals("")
						&& tipoReferencia.equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra()) && !facturaReembolsoIvaEnDetalles){
					FacturaDetalleIf facturaDetalleIf = (FacturaDetalleIf) itRows.next();
						
					FacturaDetalleData bean = new FacturaDetalleData();
						
					bean.setDescripcion(facturaDetalleIf.getDescripcion());
					bean.setCantidad(facturaDetalleIf.getCantidad());
					bean.setPrecioReal(facturaDetalleIf.getPrecioReal());
					bean.setDescuento(facturaDetalleIf.getDescuento());
					bean.setDescuentoGlobal(descuentoPlanMedios);
					bean.setProductoId(facturaDetalleIf.getProductoId());
					bean.setId(facturaDetalleIf.getId());					
					GenericoIf generico = (GenericoIf) SessionServiceLocator.getGenericoSessionService().findGenericoByProductoId(facturaDetalleIf.getProductoId()).iterator().next();
					if (generico.getCobraIva().equals("S")) {
						BigDecimal ivaPorcentaje = BigDecimal.valueOf(Parametros.IVA).divide(new BigDecimal(100));
						Double valor = facturaDetalleIf.getValor().doubleValue() / (new BigDecimal(1).add(ivaPorcentaje)).doubleValue();
						Double iva = BigDecimal.valueOf(valor).multiply(ivaPorcentaje).doubleValue();
						bean.setValor(BigDecimal.valueOf(valor));
						bean.setIva(BigDecimal.valueOf(iva));
						ivaFinalReporte += BigDecimal.valueOf(valor).multiply(ivaPorcentaje).doubleValue();
					} else {
						BigDecimal ivaPorcentaje = BigDecimal.valueOf(Parametros.IVA).divide(new BigDecimal(100));
						Double valor = facturaDetalleIf.getValor().doubleValue() / (new BigDecimal(1).add(ivaPorcentaje)).doubleValue();
						Double iva = BigDecimal.valueOf(valor).multiply(ivaPorcentaje).doubleValue();
						bean.setValor(BigDecimal.valueOf(valor + iva));
						bean.setIva(BigDecimal.ZERO);
					}
					subtotalFinalReporte += bean.getValor().doubleValue();
					reportRows.add(bean);				
				}
				
				else if (tipoDocumento.getReembolso().equals("S") && tipoDocumento.getCodigo().equals("FAR") && !isHeader /*&& !codigoReferencia.equals("")
						&& tipoReferencia.equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())*/ && facturaReembolsoIvaEnDetalles){
					
					FacturaDetalleIf facturaDetalleIf = (FacturaDetalleIf) itRows.next();
					
					FacturaDetalleIvaIncluidoData bean = new FacturaDetalleIvaIncluidoData();				
					
					//se setea vacio porque lo unico que se necesita son los datos de proveedor
					//y esos datos vienen de un mapa que se envia al reporte
					bean.setDescripcion("");
					
					//iva = 0.12
					BigDecimal ivaPorcentaje = BigDecimal.valueOf(Parametros.IVA).divide(new BigDecimal(100));
					BigDecimal valorTotalDetalle = facturaDetalleIf.getValor();
					//subtotal = total / 1.12 
					GenericoIf generico = (GenericoIf) SessionServiceLocator.getGenericoSessionService().findGenericoByProductoId(facturaDetalleIf.getProductoId()).iterator().next();
					double valorSubtotal = 0D;
					BigDecimal valorSubTotalDetalle = BigDecimal.ZERO;
					BigDecimal ivaSubTotalDetalle = BigDecimal.ZERO;
					if (generico.getCobraIva().equals("S")) {
						valorSubtotal = facturaDetalleIf.getValor().doubleValue() / (new BigDecimal(1).add(ivaPorcentaje)).doubleValue();
						valorSubTotalDetalle = BigDecimal.valueOf(valorSubtotal);
						ivaSubTotalDetalle = valorSubTotalDetalle.multiply(ivaPorcentaje);
					} else {
						valorSubtotal = facturaDetalleIf.getValor().doubleValue();
						valorSubTotalDetalle = BigDecimal.valueOf(valorSubtotal);
					}
					
					String subtotal = formatoDecimal.format(valorSubTotalDetalle);
					String iva = formatoDecimal.format(ivaSubTotalDetalle);
					String total = formatoDecimal.format(valorTotalDetalle);
					
					//bean.setValores(valorSubTotalDetalleFormato + "\n" + ivaSubTotalDetalleFormato + "\n__________\n" + valorTotalDetalleFormato);
					bean.setSubtotal(subtotal);
					bean.setIva(iva);
					bean.setTotal(total);			
					
					bean.setId(facturaDetalleIf.getId());
					reportRows.add(bean);					
				}
				
				//para todos los demas casos como reembolso de presupuesto que no tiene sap.
				else if (!isHeader) {
					FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
					GenericoIf generico = (GenericoIf) SessionServiceLocator.getGenericoSessionService().findGenericoByProductoId(bean.getProductoId()).iterator().next();
					if (generico.getCobraIva().equals("S")) {
						BigDecimal ivaPorcentaje = BigDecimal.valueOf(Parametros.IVA).divide(new BigDecimal(100));
						Double valor = bean.getValor().doubleValue() / (new BigDecimal(1).add(ivaPorcentaje)).doubleValue();
						Double iva = BigDecimal.valueOf(valor).multiply(ivaPorcentaje).doubleValue();
						if (!facturaReembolsoNormal && tipoDocumento.getReembolso().equals("S"))
							bean.setValor(BigDecimal.valueOf(valor));
						else
							bean.setValor(BigDecimal.valueOf(valor + iva));
						ivaFinalReporte += BigDecimal.valueOf(valor).multiply(ivaPorcentaje).doubleValue();
						subtotalFinalReporte += valor;
					}
					reportRows.add(bean);
				} else {
					FacturaIf bean = (FacturaIf) itRows.next();					
					bean.setTelefono((bean.getTelefono() != null && bean.getTelefono().length() >= 7)?bean.getTelefono():"");
					reportRows.add(bean);
				}
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}		

		return reportRows;
	}

	/*private Collection initializeBeanCollection(Long idParametro, boolean isHeader) {
		ArrayList reportRows = new ArrayList();
		Collection rowCollection = null;
		try {
			if (!isHeader)
				rowCollection = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(idParametro);
			else
				rowCollection = SessionServiceLocator.getFacturaSessionService().findFacturaById(idParametro);
			
			////EXCLUSIVO PARA MEDIOS////
			rowCollection = generarRowCollectionMedios(idParametro, isHeader, rowCollection);			
			
			Iterator itRows = rowCollection.iterator();

			while (itRows.hasNext()) {
				if (!isHeader) {
					FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
					reportRows.add(bean);
				} else {
					FacturaIf bean = (FacturaIf) itRows.next();
					bean.setTelefono((bean.getTelefono() != null && bean.getTelefono().length() >= 7)?bean.getTelefono():"");
					reportRows.add(bean);
				}
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}	

		return reportRows;	
	}*/

	private Collection generarRowCollectionMedios(Long idParametro,	boolean isHeader, Collection rowCollection) {		
		try {
			String tipoReferencia = pedido.getTiporeferencia();
			TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(factura.getTipodocumentoId());
			String codigoReferencia = pedido.getReferencia();
			
			if (tipoDocumento.getReembolso().equals("N") && !tipoDocumento.getCodigo().equals("FCO") && !isHeader && !codigoReferencia.equals("")){
				if(tipoReferencia.equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra())){					
					PlanMedioIf planMedioReporte = (PlanMedioIf)SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndByEstados(codigoReferencia, "A", "D", "F").iterator().next();
					OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(planMedioReporte.getOrdenTrabajoDetalleId());
					OrdenTrabajoIf ordenTrabajoIf = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalleIf.getOrdenId());
					CampanaIf campanaIf = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajoIf.getCampanaId());
					
					CampanaProductoVersionIf version = null;					
					FacturaIf facturaIf = SessionServiceLocator.getFacturaSessionService().getFactura(idParametro);
					
					Map aMap = new HashMap();
					aMap.put("planMedioId", planMedioReporte.getId());
					aMap.put("pedidoId",facturaIf.getPedidoId());

					Collection planMedioFacturacionColl = SessionServiceLocator.getPlanMedioFacturacionSessionService().findPlanMedioFacturacionByQuery(aMap);
					Iterator planMedioFacturacionCollIt = planMedioFacturacionColl.iterator();
					PlanMedioFacturacionIf planMedioFacturacionIf = (PlanMedioFacturacionIf)planMedioFacturacionCollIt.next();
					String producto  = "";
					
					ClienteOficinaIf clienteProveedor = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(planMedioFacturacionIf.getProveedorId());
					String proveedor = clienteProveedor.getDescripcion();
					
					if(planMedioFacturacionIf.getCampanaProductoVersionId() != null && planMedioFacturacionIf.getCampanaProductoVersionId().compareTo(new Long(0))==0){
						Collection ordenMedioColl = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planMedioReporte.getId());
						Iterator ordenMedioCollIt = ordenMedioColl.iterator();
						ArrayList<CampanaProductoVersionIf> listCampanaProductoVersion = new ArrayList<CampanaProductoVersionIf>();
						while(ordenMedioCollIt.hasNext()){
							OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenMedioCollIt.next();
							Collection ordenMedioDetalleColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
							Iterator ordenMedioDetalleCollIt = ordenMedioDetalleColl.iterator();
							while(ordenMedioDetalleCollIt.hasNext()){
								OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleCollIt.next();
								CampanaProductoVersionIf campanaProductoVersionIf = SessionServiceLocator.getCampanaProductoVersionSessionService().getCampanaProductoVersion(ordenMedioDetalleIf.getCampanaProductoVersionId());
								int tmp = 0;
								for(CampanaProductoVersionIf com: listCampanaProductoVersion){
									if(com.getId().compareTo(campanaProductoVersionIf.getId())!=0){
										tmp++;
									}
								}
								if(tmp==listCampanaProductoVersion.size()){
									listCampanaProductoVersion.add(campanaProductoVersionIf);
								}
							}							
						}
						
						for(CampanaProductoVersionIf campanaProductoVersionIf:listCampanaProductoVersion ){
							producto = producto.concat(campanaProductoVersionIf.getNombre());
							producto = producto.concat(", ");
						}						
						
					}else{
						ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(planMedioFacturacionIf.getProductoClienteId());
						if(planMedioFacturacionIf.getCampanaProductoVersionId() != null){
							version = SessionServiceLocator.getCampanaProductoVersionSessionService().getCampanaProductoVersion(planMedioFacturacionIf.getCampanaProductoVersionId());
							producto = productoClienteIf.getNombre()+ " (" + version + ")";
						}else{
							producto = productoClienteIf.getNombre();
						}
					}
					
					PlanMedioFormaPagoIf planMedioFormaPagoIf = null;
					Collection planMedioFormaPagoColl = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(factura.getPedidoId());
					if (planMedioFormaPagoColl.size() > 0){
						Iterator planMedioFormaPagoIt = planMedioFormaPagoColl.iterator();
						planMedioFormaPagoIf = (PlanMedioFormaPagoIf)planMedioFormaPagoIt.next();
						
						Calendar calendarFechaInicio = GregorianCalendar.getInstance();
						Calendar calendarFechaFin    = GregorianCalendar.getInstance();
						calendarFechaInicio.setTime(planMedioFormaPagoIf.getFechaInicio());
						calendarFechaFin.setTime(planMedioFormaPagoIf.getFechaFin());
						
						int diaInicio = calendarFechaInicio.get(Calendar.DATE);
						int diaFin    = calendarFechaFin.get(Calendar.DATE);
						int mesInt    = calendarFechaFin.get(Calendar.MONTH) + 1;
						
						String mes = Utilitarios.getNombreMes(mesInt);
						String anio = Utilitarios.getYearFromDate(planMedioFormaPagoIf.getFechaFin());
					
						ArrayList<FacturaDetalleIf> listaTmp = new ArrayList<FacturaDetalleIf>();
						
						FacturaDetalleData facturaDetalleIf1 = new FacturaDetalleData();
						FacturaDetalleData facturaDetalleIf2 = new FacturaDetalleData();
						FacturaDetalleData facturaDetalleIf3 = new FacturaDetalleData();
						FacturaDetalleData facturaDetalleIf4 = new FacturaDetalleData();
						
						// Forma de Facturacion segun el Plan de Medio Forma de Pago
						String formaFacturacioPMFP = planMedioFormaPagoIf.getTipoFormaPago();
						
						// COMPLETO "C"
						if(formaFacturacioPMFP.compareTo("C") == 0 ){
							facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
							facturaDetalleIf2.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
							// VERIFICAR SI ES TV-RADIO-PRENSA
							facturaDetalleIf3.setDescripcion("PAUTA EN LOS SIGUIENTES MEDIOS:\n");
							listaTmp.add(facturaDetalleIf1);
							listaTmp.add(facturaDetalleIf2);
							listaTmp.add(facturaDetalleIf3);
						
						// POR CANAL
						}else if(formaFacturacioPMFP.compareTo("L") == 0 ){
							facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
							facturaDetalleIf2.setDescripcion("PROVEEDOR: "+ proveedor);
							facturaDetalleIf3.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
							// VERIFICAR SI ES TV-RADIO-PRENSA
							facturaDetalleIf4.setDescripcion("PAUTA DE LOS SIGUIENTES PRODUCTOS:\n");
							listaTmp.add(facturaDetalleIf1);
							listaTmp.add(facturaDetalleIf2);
							listaTmp.add(facturaDetalleIf3);
							listaTmp.add(facturaDetalleIf4);	
						
						// PRODUCTO "P"
						}else if(formaFacturacioPMFP.compareTo("P") == 0 ){
							facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
							facturaDetalleIf2.setDescripcion("PRODUCTO: "+ producto);
							facturaDetalleIf3.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
							// VERIFICAR SI ES TV-RADIO-PRENSA
							facturaDetalleIf4.setDescripcion("PAUTA EN LOS SIGUIENTES MEDIOS:\n");
							listaTmp.add(facturaDetalleIf1);
							listaTmp.add(facturaDetalleIf2);
							listaTmp.add(facturaDetalleIf3);
							listaTmp.add(facturaDetalleIf4);
						
						// VERSION "V"
						}else if(formaFacturacioPMFP.compareTo("V") == 0 ){
							facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
							facturaDetalleIf2.setDescripcion("VERSION: "+ version.getNombre());
							facturaDetalleIf3.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
							// VERIFICAR SI ES TV-RADIO-PRENSA
							facturaDetalleIf4.setDescripcion("PAUTA EN LOS SIGUIENTES MEDIOS:\n");
							listaTmp.add(facturaDetalleIf1);
							listaTmp.add(facturaDetalleIf2);
							listaTmp.add(facturaDetalleIf3);
							listaTmp.add(facturaDetalleIf4);
						
						// ES POR COMISIONES DEL MEDIO "M"
						}else if(formaFacturacioPMFP.compareTo("M") == 0 ){
							facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
							facturaDetalleIf2.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
							facturaDetalleIf3.setDescripcion("COMISIÓN DE AGENCIA POR MUTUO CLIENTE:\n");
							listaTmp.add(facturaDetalleIf1);
							listaTmp.add(facturaDetalleIf2);
							listaTmp.add(facturaDetalleIf3);
						}

						Collection rowCollectionTmp = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(idParametro);
						listaTmp.addAll(rowCollectionTmp);
						
						rowCollection = listaTmp;
					}
				}
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}		
		
		return rowCollection;
	}
	
	private Map getProveedoresMap(Long idParametro) {
		Map proveedoresMap = new HashMap();
		Collection rowCollection = null;
		try {
			rowCollection = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(idParametro);
			Iterator itRows = rowCollection.iterator();
			
			while (itRows.hasNext()) {
				FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
				ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(bean.getProductoId());
				ClienteOficinaIf proveedorOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(producto.getProveedorId());
				String facturaReembolso = "";
				if(!bean.getComprasReembolsoAsociadas().equals("")){
					facturaReembolso = "\nF: " + bean.getComprasReembolsoAsociadas();
				}
				ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
				TipoIdentificacionIf tipoIdentificacion = SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacion(proveedor.getTipoidentificacionId());
				proveedoresMap.put(bean.getId(), proveedor.getRazonSocial() + "\n" + tipoIdentificacion.getNombre() + ": " + proveedor.getIdentificacion() + facturaReembolso);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}

		return proveedoresMap;
	}
	
	private Map getProductosMap(FacturaIf factura) {
		Map productosMap = new HashMap();
		Collection rowCollection = null;
		try {
			rowCollection = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(factura.getId());
			Iterator itRows = rowCollection.iterator();
			while (itRows.hasNext()) {
				FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
				ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(bean.getProductoId());
				String codigo = (producto.getCodigoBarras()!=null && !producto.getCodigoBarras().trim().equals(""))?producto.getCodigoBarras():producto.getCodigo();
				productosMap.put(bean.getId(), codigo);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}

		return productosMap;
	}
	
	private Map getProductosMap(Long idParametro) {
		Map productosMap = new HashMap();
		Collection rowCollection = null;
		try {
			rowCollection = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(idParametro);
			Iterator itRows = rowCollection.iterator();
			while (itRows.hasNext()) {
				FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
				ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(bean.getProductoId());
				String codigo = (producto.getCodigoBarras()!=null && !producto.getCodigoBarras().trim().equals(""))?producto.getCodigoBarras():producto.getCodigo();
				productosMap.put(bean.getId(), codigo);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}

		return productosMap;
	}

	public boolean isEmpty() {
		return true;
	}

	public void clean() {
		cleanTabla();
		corporacionIf = null;
		clienteIf = null;
		clienteOficinaIf = null;
		getTxtNumero().setText("");
		getTxtFechaCreacion().setText("");
		getTxtOficinaEmpresa().setText("");
		getTxtObservacion().setText("");
		getTxtTipodentificacion().setText("");
		getTxtIdentificacion().setText("");
		getTxtTelefono().setText("");
		getTxtContacto().setText("");
		getTxtDireccion().setText("");
		getTxtAutorizacionSAP().setText("");
		getTxtPlanMedios().setText("");
		getTxtPresupuesto().setText("");
		getTxtPuntoImpresion().setText("");
		getTxtMoneda().setText("");
		getTxtFormaPago().setText("");
		getTxtListaPrecio().setText("");
		getTxtCaja().setText("");
		getTxtFechaVencimiento().setText("");
		getTxtOrigenDocumento().setText("");
		getTxtVendedor().setText("");
		getTxtDirectorCuentas().setText("");
		getTxtBodega().setText("");
		getTxtPreimpreso().setText("");
		getTxtProveedor().setText("");
		getTxtLinea().setText("");
		getTxtCantidad().setText("");
		getTxtPrecioReal().setText("");
		getTxtCantidadDevuelta().setText("");
		getTxtPorcentajeDescuentoAgencia().setText("");
		getTxtPorcentajeDescuentosVarios().setText("");
		getTxtValorFinal().setText("");
		getTxtDescuentoAgenciaTotal().setText("");
		getTxtDescuentosVariosTotal().setText("");
		getTxtIVAFinal().setText("");
		getTxtICEFinal().setText("");
		getTxtOtroImpuestoFinal().setText("");
		getTxtPorcentajeComision().setText("");
		getTxtValorComision().setText("");
		getTxtTotalFinal().setText("");
		getTxtDocumento().setText("");
		getTxtMotivo().setText("");
		getTxtTipoDocumento().setText("");
		getTxtCodigoProducto().setText("");
		getTxtDescripcion().setText("");
		getTxtCorporacion().setText("");
		getTxtCliente().setText("");
		getTxtClienteOficina().setText("");
		getTxtFacturaProveedor().setText("");
		getCbNegociacionDirecta().setSelected(false);
		getCbComisionPura().setSelected(false);
		getTxtPorcentajeNegociacionDirecta().setText("");
		getTxtDsctoCompraPorcentaje().setText("");
		getTxtClienteNegociacion().setText("");
		clienteOficinaNegociacionIf = null;

		if (getMode() == SpiritMode.FIND_MODE)
			getCmbFechaFactura().setSelectedItem(null);

		repaint();
	}

	public void cleanTabla() {
		DefaultTableModel model = (DefaultTableModel) getTblFacturaDetalle().getModel();

		for (int i = this.getTblFacturaDetalle().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);

		FacturaDetalleColeccion = new Vector();
		subTotal = 0.00;
		totalIvaCero = 0.00;
		valorBruto = 0.00;
		descuentoTotal = 0.00;
		descuentosVariosTotal = 0.00;
		porcentajeComisionAgencia = 0.00;
		comisionAgenciaTotal = 0.00;
		ivaTotal = 0.00;
		total = 0.00;
		repaint();
	}

	public void showFindMode() {		
		getCmbEstado().setBackground(Parametros.findColor);
		getTxtContacto().setBackground(Parametros.findColor);
		getCmbFechaFactura().setBackground(Parametros.findColor);
		getTxtClienteOficina().setBackground(Parametros.findColor);
		getTxtNumero().setBackground(Parametros.findColor);
		getTxtPreimpreso().setBackground(Parametros.findColor);
		clean();
		clienteOficinaIf = null;
		getTxtTipoDocumento().setEditable(false);
		getTxtDescuentoGlobal().setEditable(false);
		getRbOrdenMedios().setEnabled(false);
		getRbPresupuesto().setEnabled(false);
		getRbNinguno().setEnabled(false);
		getCmbFechaFactura().setEnabled(true);
		getCmbFechaFactura().setSelectedItem(null);
		getTxtFechaCreacion().setEditable(false);
		getTxtOficinaEmpresa().setEditable(false);
		getTxtFormaPago().setEditable(false);
		getTxtMoneda().setEditable(false);
		getTxtListaPrecio().setEditable(false);
		getTxtCaja().setEditable(false);
		getTxtFechaVencimiento().setEditable(false);
		getCmbEstado().setSelectedIndex(-1);
		getTxtObservacion().setEditable(false);
		getTxtCorporacion().setEditable(false);
		getTxtCliente().setEditable(false);
		getTxtClienteOficina().setEditable(false);
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getBtnBuscarClienteOficina().setEnabled(true);
		getTxtTipodentificacion().setEditable(false);
		getTxtIdentificacion().setEditable(false);
		getTxtContacto().setEditable(true);
		getTxtTelefono().setEditable(false);
		getTxtNumero().setEditable(true);
		getTxtDireccion().setEditable(false);
		getTxtPlanMedios().setEditable(false);
		getTxtPresupuesto().setEditable(false);
		getTxtFacturadoPor().setEditable(false);
		getTxtBodega().setEditable(false);
		getTxtVendedor().setEditable(false);
		getTxtDirectorCuentas().setEditable(false);
		getTxtPuntoImpresion().setEditable(false);
		getTxtOrigenDocumento().setEditable(false);
		getTxtPreimpreso().setEditable(true);
		getTxtDocumento().setEditable(false);
		getTxtMotivo().setEditable(false);
		getTxtCodigoProducto().setEditable(false);
		getTxtDescripcion().setEditable(false);
		getTxtProveedor().setEditable(false);
		getTxtCantidad().setEditable(false);
		getTxtCantidadDevuelta().setEditable(false);
		getTxtPrecio().setEditable(false);
		getTxtCosto().setEditable(false);
		getTxtPrecioReal().setEditable(false);
		getTxtLinea().setEditable(false);
		getTxtPorcentajeDescuentoAgencia().setEditable(false);
		getTxtPorcentajeDescuentosVarios().setEditable(false);
		getTxtLote().setEditable(false);
		getTxtValorFinal().setEditable(false);
		getTxtDescuentoAgenciaTotal().setEditable(false);
		getTxtDescuentosVariosTotal().setEditable(false);
		getTxtIVAFinal().setEditable(false);
		getTxtICEFinal().setEditable(false);
		getTxtOtroImpuestoFinal().setEditable(false);
		getTxtTotalFinal().setEditable(false);
		getTxtPorcentajeComision().setEditable(false);
		getTxtValorComision().setEditable(false);
		getTxtFacturaProveedor().setEditable(false);
		getJtpFactura().setSelectedIndex(0);
		getTxtPreimpreso().grabFocus();
		getJpNegociacion().setVisible(false);
	}

	public void showSaveMode() {
		this.setMode(SpiritMode.FIND_MODE);
		this.clean();
		new ChangeModeImpl().fireChangeModeEvent("MODO: FIND");
		showFindMode();
	}

	public void showUpdateMode() {
		getCmbEstado().setBackground(getBackground());
		getTxtContacto().setBackground(getBackground());
		getCmbFechaFactura().setBackground(Parametros.saveUpdateColor);
		getTxtClienteOficina().setBackground(getBackground());
		getTxtNumero().setBackground(getBackground());
		getTxtPreimpreso().setBackground(Parametros.saveUpdateColor);
		
		setUpdateMode();
		getTxtTipoDocumento().setEditable(false);
		getTxtDescuentoGlobal().setEditable(false);
		getRbOrdenMedios().setEnabled(false);
		getRbPresupuesto().setEnabled(false);
		getRbNinguno().setEnabled(false);
		getCmbFechaFactura().setEnabled(false);
		getTxtFechaCreacion().setEditable(false);
		getTxtOficinaEmpresa().setEditable(false);
		getTxtFormaPago().setEditable(false);
		getTxtMoneda().setEditable(false);
		getTxtListaPrecio().setEditable(false);
		getTxtCaja().setEditable(false);
		getTxtFechaVencimiento().setEditable(false);
		getTxtObservacion().setEditable(false);
		getTxtCorporacion().setEditable(false);
		getTxtCliente().setEditable(false);
		getTxtClienteOficina().setEditable(false);
		getBtnBuscarCorporacion().setEnabled(false);
		getBtnBuscarCliente().setEnabled(false);
		getBtnBuscarClienteOficina().setEnabled(false);
		getTxtTipodentificacion().setEditable(false);
		getTxtIdentificacion().setEditable(false);
		getTxtContacto().setEditable(false);
		getTxtTelefono().setEditable(false);
		getTxtNumero().setEditable(false);
		getTxtDireccion().setEditable(false);
		getTxtPlanMedios().setEditable(false);
		getTxtPresupuesto().setEditable(false);
		getTxtFacturadoPor().setEditable(false);
		getTxtBodega().setEditable(false);
		getTxtVendedor().setEditable(false);
		getTxtDirectorCuentas().setEditable(false);
		getTxtPuntoImpresion().setEditable(false);
		getTxtOrigenDocumento().setEditable(false);
		//getTxtPreimpreso().setEditable(false);
		getTxtDocumento().setEditable(false);
		getTxtMotivo().setEditable(false);
		getTxtCodigoProducto().setEditable(false);
		getTxtDescripcion().setEditable(false);
		getTxtProveedor().setEditable(false);
		getTxtCantidad().setEditable(false);
		getTxtCantidadDevuelta().setEditable(false);
		getTxtPrecio().setEditable(false);
		getTxtCosto().setEditable(false);
		getTxtPrecioReal().setEditable(false);
		getTxtLinea().setEditable(false);
		getTxtPorcentajeDescuentoAgencia().setEditable(false);
		getTxtPorcentajeDescuentosVarios().setEditable(false);
		getTxtLote().setEditable(false);
		getTxtValorFinal().setEditable(false);
		getTxtDescuentoAgenciaTotal().setEditable(false);
		getTxtDescuentosVariosTotal().setEditable(false);
		getTxtIVAFinal().setEditable(false);
		getTxtICEFinal().setEditable(false);
		getTxtOtroImpuestoFinal().setEditable(false);
		getTxtTotalFinal().setEditable(false);
		getTxtFacturaProveedor().setEditable(false);
	}

	private void getSelectedObject() {
		factura = (FacturaIf) popupFinder.getElementoSeleccionado();

		try {
			TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(factura.getTipodocumentoId());
			getTxtTipoDocumento().setText(tipoDocumento.getCodigo() + " - " + tipoDocumento.getNombre());
			if (factura.getNumero() != null)
				getTxtNumero().setText(formatoSerial.format(factura.getNumero()));
			else
				getTxtNumero().setText("");
			String fecha = Utilitarios.getFechaUppercase(factura.getFechaCreacion());
			getTxtFechaCreacion().setText(fecha);
			Calendar calendarInicio = new GregorianCalendar();
			calendarInicio.setTime(factura.getFechaFactura());
			getCmbFechaFactura().setCalendar(calendarInicio);
			Calendar calendarFin = new GregorianCalendar();
			calendarFin.setTime(factura.getFechaVencimiento());
			String fechaVencimiento = Utilitarios.getFechaUppercase(factura.getFechaVencimiento());
			getTxtFechaVencimiento().setText(fechaVencimiento);
			OficinaIf oficina = SessionServiceLocator.getOficinaSessionService().getOficina(factura.getOficinaId());
			getTxtOficinaEmpresa().setText(Parametros.getNombreEmpresa() + " - " + oficina.getNombre());
			FormaPagoIf formaPago = SessionServiceLocator.getFormaPagoSessionService().getFormaPago(factura.getFormapagoId());
			getTxtFormaPago().setText(formaPago.getCodigo() + " - " + formaPago.getNombre());
			MonedaIf moneda = SessionServiceLocator.getMonedaSessionService().getMoneda(factura.getMonedaId());
			getTxtMoneda().setText(moneda.getCodigo() + " - " + moneda.getNombre());
			ListaPrecioIf listaPrecio = SessionServiceLocator.getListaPrecioSessionService().getListaPrecio(factura.getListaPrecioId());
			getTxtListaPrecio().setText(listaPrecio.getCodigo() + " - " + listaPrecio.getNombre());

			if (ESTADO_COMPLETO.equals(factura.getEstado()))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_COMPLETO);
			else if (ESTADO_INCOMPLETO.equals(factura.getEstado()))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INCOMPLETO);
			else if (ESTADO_PENDIENTE.equals(factura.getEstado()))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_PENDIENTE);
			else if (ESTADO_ANULADO.equals(factura.getEstado()))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ANULADO);

			if (factura.getPuntoImpresionId() != null) {
				puntoImpresionIf = SessionServiceLocator.getPuntoImpresionSessionService().getPuntoImpresion(factura.getPuntoImpresionId());
				CajaIf caja = SessionServiceLocator.getCajaSessionService().getCaja(puntoImpresionIf.getCajaId());
				getTxtCaja().setText(caja.getCodigo() + " - " + caja.getNombre());
				getTxtPuntoImpresion().setText(puntoImpresionIf.getSerie());
				getTxtPreimpreso().setText(factura.getPreimpresoNumero());
			}

			getTxtObservacion().setText(factura.getObservacion());
			clienteOficinaIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(factura.getClienteoficinaId());
			clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
			corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
			tipoIdentificacionIf = SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacion(factura.getTipoidentificacionId());
			getTxtTipodentificacion().setText(tipoIdentificacionIf.getNombre());
			getTxtIdentificacion().setText(factura.getIdentificacion());
			getTxtContacto().setText(factura.getContacto());
			getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
			getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
			getTxtClienteOficina().setText(clienteOficinaIf.getCodigo() + " - " + clienteOficinaIf.getDescripcion());
			getTxtTelefono().setText(factura.getTelefono());
			getTxtDireccion().setText(factura.getDireccion());
			if(factura.getAutorizacionSap() != null){
				getTxtAutorizacionSAP().setText(factura.getAutorizacionSap());
			}

			if (factura.getPedidoId() != null) {
				pedido = SessionServiceLocator.getPedidoSessionService().getPedido(factura.getPedidoId());
				String codigo = pedido.getReferencia();
				Long idEmpresa = Parametros.getIdEmpresa();
				Map parameterMap = new HashMap();
				parameterMap.put("codigo", codigo);
				
				if (("PRESUPUESTO".substring(0, 1)).equals(pedido.getTiporeferencia())) {
					
					if(pedido.getReferencia().equals("M")){
						Map<Long,PresupuestoIf> presupuestosMap = new HashMap<Long,PresupuestoIf>();
						Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId());
						Iterator facturasIt = facturas.iterator();
						while(facturasIt.hasNext()){
							FacturaIf factura = (FacturaIf)facturasIt.next();
							Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByFacturaId(factura.getId());
							Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
							while(presupuestosFacturacionIt.hasNext()){
								PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
								PresupuestoDetalleIf presupuestoDetalleIf = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoFacturacionIf.getPresupuestoDetalleId());
								PresupuestoIf presupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalleIf.getPresupuestoId());
								presupuestosMap.put(presupuestoIf.getId(), presupuestoIf);
							}
						}
						String referencia = "";
						Iterator presupuestosMapIt = presupuestosMap.keySet().iterator();
						while(presupuestosMapIt.hasNext()){
							Long presupuestoId = (Long)presupuestosMapIt.next();
							presupuestoIf = presupuestosMap.get(presupuestoId);			
							
							if(referencia.equals("")){
								referencia = presupuestoIf.getCodigo();
							}else{
								referencia = referencia + ";" + presupuestoIf.getCodigo();
							}
						}
						getTxtPresupuesto().setText(referencia);
																
					}else{
						Iterator it = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByQuery(parameterMap, idEmpresa).iterator();
						while (it.hasNext()) {
							PresupuestoIf presupuesto = (PresupuestoIf) it.next();
							getTxtPlanMedios().setText("");
							getTxtPresupuesto().setText(presupuesto.toString());
						}
					}
					
				} else if (("MEDIOS".substring(3, 4)).equals(pedido.getTiporeferencia())) {
					
					if(pedido.getReferencia().equals("M")){
						String referencia = "";
						Collection planesMedioFormaPago = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(pedido.getId());
						Iterator planesMedioFormaPagoIt = planesMedioFormaPago.iterator();
						while(planesMedioFormaPagoIt.hasNext()){
							PlanMedioFormaPagoIf planMedioFormaPago = (PlanMedioFormaPagoIf)planesMedioFormaPagoIt.next();
							PlanMedioIf planMedio = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(planMedioFormaPago.getPlanMedioId());
							if(referencia.equals("")){
								referencia = planMedio.getCodigo();
							}else{
								referencia = referencia + ";" + planMedio.getCodigo();
							}
						}
						getTxtPlanMedios().setText(referencia);
						
					}else{
						Iterator it = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndByEstados(codigo, "D", "F", "A").iterator();
						while (it.hasNext()) {
							PlanMedioIf planMedios = (PlanMedioIf) it.next();
							getTxtPresupuesto().setText("");
							getTxtPlanMedios().setText(planMedios.getCodigo());
						}
					}				
				} 
				//cuando es facturacion combinada
				else if ("C".equals(pedido.getTiporeferencia())) {
					if(pedido.getReferencia().equals("M")){
						//PRESUPUESTO
						Map<Long,PresupuestoIf> presupuestosMap = new HashMap<Long,PresupuestoIf>();
						Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId());
						Iterator facturasIt = facturas.iterator();
						while(facturasIt.hasNext()){
							FacturaIf factura = (FacturaIf)facturasIt.next();
							Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByFacturaId(factura.getId());
							Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
							while(presupuestosFacturacionIt.hasNext()){
								PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
								PresupuestoDetalleIf presupuestoDetalleIf = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoFacturacionIf.getPresupuestoDetalleId());
								PresupuestoIf presupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalleIf.getPresupuestoId());
								presupuestosMap.put(presupuestoIf.getId(), presupuestoIf);
							}
						}
						String referenciaPresupuesto = "";
						Iterator presupuestosMapIt = presupuestosMap.keySet().iterator();
						while(presupuestosMapIt.hasNext()){
							Long presupuestoId = (Long)presupuestosMapIt.next();
							presupuestoIf = presupuestosMap.get(presupuestoId);			
							
							if(referenciaPresupuesto.equals("")){
								referenciaPresupuesto = presupuestoIf.getCodigo();
							}else{
								referenciaPresupuesto = referenciaPresupuesto + ";" + presupuestoIf.getCodigo();
							}
						}
						getTxtPresupuesto().setText(referenciaPresupuesto);
						
						//PLAN DE MEDIO
						String referenciaPlanMedios = "";
						Collection planesMedioFormaPago = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(pedido.getId());
						Iterator planesMedioFormaPagoIt = planesMedioFormaPago.iterator();
						while(planesMedioFormaPagoIt.hasNext()){
							PlanMedioFormaPagoIf planMedioFormaPago = (PlanMedioFormaPagoIf)planesMedioFormaPagoIt.next();
							PlanMedioIf planMedio = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(planMedioFormaPago.getPlanMedioId());
							if(referenciaPlanMedios.equals("")){
								referenciaPlanMedios = planMedio.getCodigo();
							}else{
								referenciaPlanMedios = referenciaPlanMedios + ";" + planMedio.getCodigo();
							}
						}
						getTxtPlanMedios().setText(referenciaPlanMedios);
						
					}
				}
			}

			UsuarioIf usuario = SessionServiceLocator.getUsuarioSessionService().getUsuario(factura.getUsuarioId());
			facturador = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(usuario.getEmpleadoId());
			getTxtFacturadoPor().setText(facturador.getNombres() + " " + facturador.getApellidos());
			if (factura.getVendedorId() != null) {
				EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(factura.getVendedorId());
				getTxtVendedor().setText(empleado.getNombres() + " " + empleado.getApellidos());
				
				//Selecciono el equipo y director del ejecutivo
				HashMap buscarDirectorMap = new HashMap();
				buscarDirectorMap.put("equipoId", factura.getEquipoId());
				buscarDirectorMap.put("rol", "DCU");
				Collection directorEquipos = SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByQuery(buscarDirectorMap);
				if(directorEquipos.size() > 0){
					EquipoEmpleadoIf directorEquipo = (EquipoEmpleadoIf)directorEquipos.iterator().next();
					EmpleadoIf director = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(directorEquipo.getEmpleadoId());
					EquipoTrabajoIf equipoTrabajoDirector = SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajo(directorEquipo.getEquipoId());
					getTxtDirectorCuentas().setText(equipoTrabajoDirector.getCodigo() + " - " + director);
				}
				
			} else{
				getTxtVendedor().setText("");
				getTxtDirectorCuentas().setText("");
			}
			BodegaIf bodega = SessionServiceLocator.getBodegaSessionService().getBodega(factura.getBodegaId());
			getTxtBodega().setText(bodega.getCodigo() + " - " + bodega.getNombre());

			if (factura.getOrigendocumentoId() != null) {
				OrigenDocumentoIf origenDocumento = SessionServiceLocator.getOrigenDocumentoSessionService().getOrigenDocumento(factura.getOrigendocumentoId());
				getTxtOrigenDocumento().setText(origenDocumento.getCodigo() + " - " + origenDocumento.getNombre());
			}
			
			//seteo datos de negociación si la hubiera.
			if(pedido.getTipoNegociacion() != null){
				getJpNegociacion().setVisible(true);
				if(pedido.getTipoNegociacion().equals(TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA)){
					getCbNegociacionDirecta().setSelected(true);
					getTxtPorcentajeNegociacionDirecta().setText(formatoDecimal.format(pedido.getPorcentajeNegociacionDirecta()));
					getTxtDsctoCompraPorcentaje().setText(formatoDecimal.format(pedido.getPorcentajeDescuentoNegociacion()));
					clienteOficinaNegociacionIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(pedido.getClienteNegociacionId());
					getTxtClienteNegociacion().setText(clienteOficinaNegociacionIf.getCodigo() + " - " + clienteOficinaNegociacionIf.getDescripcion());
				}else if(pedido.getTipoNegociacion().equals(TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA)){
					getCbComisionPura().setSelected(true);
					getTxtDsctoCompraPorcentaje().setText(formatoDecimal.format(pedido.getPorcentajeDescuentoNegociacion()));
					clienteOficinaNegociacionIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(pedido.getClienteNegociacionId());
					getTxtClienteNegociacion().setText(clienteOficinaNegociacionIf.getCodigo() + " - " + clienteOficinaNegociacionIf.getDescripcion());
				}
			}
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}

		try {
			modelFacturaDetalle = (DefaultTableModel) getTblFacturaDetalle().getModel();
			Collection listaPlantillaDetalle = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(factura.getId());
			Iterator listaDetalleIt = listaPlantillaDetalle.iterator();
			if(factura.getPorcentajeComisionAgencia() != null){
				porcentajeComisionAgencia = factura.getPorcentajeComisionAgencia().doubleValue();
			}else{
				porcentajeComisionAgencia = 0D;
			}
			getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));

			while (listaDetalleIt.hasNext()) {
				Double subtotal = 0D;
				FacturaDetalleIf pedidoDetalleIf = (FacturaDetalleIf) listaDetalleIt.next();
				Vector<String> filaPlantillaDetalle = new Vector<String>();
				FacturaDetalleColeccion.add(pedidoDetalleIf);
				DocumentoIf documento = null;
				
				try {
					documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(pedidoDetalleIf.getDocumentoId());
				} catch (GenericBusinessException e1) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e1.printStackTrace();
				}

				filaPlantillaDetalle.add(pedidoDetalleIf.getDescripcion());
				filaPlantillaDetalle.add(String.valueOf(pedidoDetalleIf.getCantidad().intValue()));
				filaPlantillaDetalle.add(String.valueOf(pedidoDetalleIf.getPrecioReal().doubleValue()));
				Double cantidad = pedidoDetalleIf.getCantidad().doubleValue() * pedidoDetalleIf.getPrecioReal().doubleValue();
				Double descuento = 0D;
				Double porcentajeDescuentosVarios = 0D;
				Double descuentoGlobal = 0D;
				if ( cantidad != 0 ){
					descuento = Utilitarios.redondeoValor((pedidoDetalleIf.getDescuento().doubleValue() * 100) / (cantidad));
					porcentajeDescuentosVarios = pedidoDetalleIf.getPorcentajeDescuentosVarios().doubleValue();
					descuentoGlobal = Utilitarios.redondeoValor((pedidoDetalleIf.getDescuentoGlobal().doubleValue() * 100) / (cantidad));
				}

				filaPlantillaDetalle.add(formatoDecimal.format(descuento) + " % ");
				filaPlantillaDetalle.add(formatoDecimal.format(Utilitarios.redondeoValor(descuentoGlobal.doubleValue())) + " % ");
				
				if (pedidoDetalleIf.getIva().doubleValue() > 0D)
					filaPlantillaDetalle.add(formatoDecimal.format(IVA * 100) + " %");
				else
					filaPlantillaDetalle.add(formatoDecimal.format(IVA_CERO) + " %");
				
				filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuentosVarios) + " % ");
				
				getTxtDescuentoGlobal().setText(formatoDecimalDescuentoGlobalPorcentaje.format(descuentoGlobal));
				descuento = pedidoDetalleIf.getDescuento().doubleValue();
				descuentoGlobal = Utilitarios.redondeoValor(pedidoDetalleIf.getDescuentoGlobal().doubleValue());
				Double iva = pedidoDetalleIf.getIva().doubleValue();
				subtotal = pedidoDetalleIf.getPrecioReal().doubleValue() * pedidoDetalleIf.getCantidad().doubleValue();
				double descuentosVarios = subtotal * (porcentajeDescuentosVarios / 100);
				Double comisionAgencia = ((subtotal - descuento - descuentosVarios - descuentoGlobal) * porcentajeComisionAgencia) / 100D;

				if (documento.getBonificacion().equals(OPCION_NO)) {
					subTotal += subtotal;
					ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(pedidoDetalleIf.getProductoId());
					GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(producto.getGenericoId());
					
					if (generico.getCobraIvaCliente().equals("N"))
						totalIvaCero += subtotal;
					
					this.getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					this.getTxtICEFinal().setText("0.00");
					this.getTxtOtroImpuestoFinal().setText("0.00");
					descuentoTotal = descuentoTotal + descuento + descuentoGlobal;
					descuentosVariosTotal = descuentosVariosTotal + descuentosVarios;
					this.getTxtDescuentoAgenciaTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
					this.getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));					
					comisionAgenciaTotal = comisionAgenciaTotal + comisionAgencia;
					this.getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
					ivaTotal += iva;
					this.getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
					total = subTotal - descuentoTotal - descuentosVariosTotal + ivaTotal + comisionAgenciaTotal;
					this.getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
				}

				modelFacturaDetalle.addRow(filaPlantillaDetalle);
			}

			showUpdateMode();
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	KeyListener oKeyAdapterTblFacturaDetalle = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRow(evt);
		}
	};
	
	MouseListener oMouseAdapterTblFacturaDetalle = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRow(evt);
		}
	};
	
	private void enableSelectedRow(ComponentEvent evt) {
		if (getTblFacturaDetalle().getSelectedRow() != -1) {
			FacturaDetalleIf facturaDetalleIf = (FacturaDetalleIf) FacturaDetalleColeccion.get(((JTable) evt.getSource()).getSelectedRow());

			try {
				if (facturaDetalleIf.getProductoId() != null) {
					productoIf = SessionServiceLocator.getProductoSessionService().getProducto(facturaDetalleIf.getProductoId());
					GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
					if (productoIf.getProveedorId() != null) {
						proveedorIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(productoIf.getProveedorId());
						getTxtProveedor().setText(proveedorIf.getCodigo() + " - " + ((ClienteIf) SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(productoIf.getProveedorId()).iterator().next()).getNombreLegal());
						if(!facturaDetalleIf.getComprasReembolsoAsociadas().equals("")){
							getTxtFacturaProveedor().setText(facturaDetalleIf.getComprasReembolsoAsociadas());
						}else{
							getTxtFacturaProveedor().setText("");
						}
					}
					
					String codigo = (productoIf.getCodigoBarras()!=null && !productoIf.getCodigoBarras().trim().equals(""))?productoIf.getCodigoBarras():productoIf.getCodigo();
					getTxtCodigoProducto().setText(codigo);
					getTxtDescripcion().setText(facturaDetalleIf.getDescripcion());

					if (facturaDetalleIf.getLoteId() != null) {
						LoteIf loteIf = SessionServiceLocator.getLoteSessionService().getLote(facturaDetalleIf.getLoteId());
						getTxtLote().setText(loteIf.getCodigo());
					}

					if (generico.getLineaId() != null) {
						LineaIf lineaIf = SessionServiceLocator.getLineaSessionService().getLinea(generico.getLineaId());
						getTxtLinea().setText(lineaIf.getCodigo() + " - " + lineaIf.getNombre());
					}
					DocumentoIf documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(facturaDetalleIf.getDocumentoId());
					getTxtDocumento().setText(documento.getCodigo() + " - " + documento.getNombre());

					if (facturaDetalleIf.getMotivodocumentoId() != null) {
						MotivoDocumentoIf motivoDocumento = SessionServiceLocator.getMotivoDocumentoSessionService().getMotivoDocumento(facturaDetalleIf.getMotivodocumentoId());
						getTxtMotivo().setText(motivoDocumento.getCodigo() + " - " + motivoDocumento.getNombre());
					}
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}

			getTxtCantidad().setText(formatoDecimal.format(facturaDetalleIf.getCantidad().doubleValue()));
			getTxtPrecio().setText(formatoDecimal.format(facturaDetalleIf.getPrecio().doubleValue()));
			
			if (facturaDetalleIf.getCosto() != null)
				getTxtCosto().setText(formatoDecimal.format(facturaDetalleIf.getCosto().doubleValue()));
			
			getTxtPrecioReal().setText(formatoDecimal.format(facturaDetalleIf.getPrecioReal().doubleValue()));
			getTxtCantidadDevuelta().setText(formatoDecimal.format(facturaDetalleIf.getCantidadDevuelta().doubleValue()));
			Double cantidad = facturaDetalleIf.getCantidad().doubleValue() * facturaDetalleIf.getPrecioReal().doubleValue();
			Double descuento = 0.0;
			Double porcentajeDescuentosVarios = 0.0;
			
			if ( cantidad != 0){
				descuento = (facturaDetalleIf.getDescuento().doubleValue() * 100) / (cantidad);
				porcentajeDescuentosVarios = facturaDetalleIf.getPorcentajeDescuentosVarios().doubleValue();
			}
			
			getTxtPorcentajeDescuentoAgencia().setText(formatoDecimal.format(descuento));
			getTxtPorcentajeDescuentosVarios().setText(formatoDecimal.format(porcentajeDescuentosVarios));
		}
	}

	public void cargarCombos() {
		getCmbFechaFactura().setFormat(Utilitarios.setFechaUppercase());
	}
	
	// listener para el combo de corporacion
	ActionListener oActionListenerBtnBuscarCorporacion = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if ( popupFinder.getElementoSeleccionado() != null ){
				corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
				getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
				clienteIf = null;
				clienteOficinaIf = null;
				getTxtCliente().setText("");
				getTxtClienteOficina().setText("");
				getTxtIdentificacion().setText("");
				getTxtContacto().setText("");
				getTxtTelefono().setText("");
				getTxtDireccion().setText("");
			}
		}
	};

	// listener para el combo de cliente
	ActionListener oActionListenerBtnBuscarCliente = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			Long idCorporacion = 0L;

			if (corporacionIf != null)
				idCorporacion = corporacionIf.getId();

			clienteCriteria = new ClienteCriteria("Clientes", idCorporacion, CODIGO_TIPO_CLIENTE);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if ( popupFinder.getElementoSeleccionado() != null ) {
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

				clienteOficinaIf = null;
				getTxtClienteOficina().setText("");
				getTxtIdentificacion().setText("");
				getTxtContacto().setText("");
				getTxtTelefono().setText("");
				getTxtDireccion().setText("");
			}
		}
	};

	// listener para el combo de cliente oficina
	ActionListener oActionListenerBtnBuscarClienteOficina = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			Long idCorporacion = 0L;
			Long idCliente = 0L;
			String tituloVentanaBusqueda = "Oficinas del Cliente";
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.addElement(10);
			anchoColumnas.addElement(350);
			
			if (corporacionIf != null)
				idCorporacion = corporacionIf.getId();
			
			if (clienteIf != null)
				idCliente = clienteIf.getId();
			
			clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_CLIENTE, "", false);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),clienteOficinaCriteria,JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			
			if (popupFinder.getElementoSeleccionado() != null) {
				clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
				getTxtClienteOficina().setText(clienteOficinaIf.getCodigo() + " - " + clienteOficinaIf.getDescripcion());
				getTxtIdentificacion().setText("");
				getTxtContacto().setText("");
				getTxtTelefono().setText("");
				getTxtDireccion().setText("");
				
				if (clienteIf == null) {
					try {
						
						clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
						getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
						
						if (corporacionIf == null) {
							corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
							getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
						}
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}
			}
		}
	};
}
