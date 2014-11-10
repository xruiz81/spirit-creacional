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
import com.spirit.cartera.entity.LogCarteraIf;
import com.spirit.client.ChangeModeImpl;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.LogAsientoIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.ListaPrecioIf;
import com.spirit.facturacion.entity.MotivoDocumentoIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.gui.criteria.FacturaCriteria;
import com.spirit.facturacion.gui.panel.JPReactivarFacturasAnuladas;
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
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class ReactivarFacturasAnuladasModel  extends JPReactivarFacturasAnuladas {
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
	private static final String TIPO_PRESUPUESTO = "P";
	private static final String PRESUPUESTO_ESTADO_FACTURADO = "F";
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DecimalFormat formatoEntero = new DecimalFormat("###0");
	private DecimalFormat formatoDecimalDescuentoGlobalPorcentaje = new DecimalFormat("#,##0.00");
	private DecimalFormat formatoSerial = new DecimalFormat("0000000");
	protected Double subTotal = 0.00, descuentoTotal = 0.00, ivaTotal = 0.00, total = 0.00, valorBruto = 0.00, ivaTemp = 0.00, comisionAgenciaTotal = 0.00;
	protected Double porcentajeComisionAgencia = 0.00;
	protected Double montoDescuento = 0.00;
	protected Double IVA = Parametros.getIVA() / 100;
	protected Double IVA_CERO = 0D;
	java.util.Date fechaCreacion = new java.util.Date();
	JMenuItem itemEliminarFacturaDetalle;
	final JPopupMenu popupMenuFacturaDetalle = new JPopupMenu();
	Vector FacturaDetalleColeccion = new Vector();
	boolean ordenmedios = false, presupuesto = false, sinpresupuesto = false;
	PedidoIf pedido = null;
	private static final int MAX_LONGITUD_NUMERO = 7;
	//private static final int MAX_LONGITUD_PREIMPRESO = 15;
	private static final int MAX_LONGITUD_CONTACTO = 40;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no}; 
	
	
	public ReactivarFacturasAnuladasModel() {
		initKeyListeners();
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
		int opcion = JOptionPane.showOptionDialog(null, "¿Desea reactivar esta factura?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
		if (opcion == JOptionPane.YES_OPTION) {
			procesarReactivacionFacturasAnuladas();
			showSaveMode();
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
		aMap.put("estado", ESTADO_ANULADO);
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

	public void report() {
		Double subtotalReporte = 0D;
		Double ivaReporte = 0D;
		if (getMode() == SpiritMode.UPDATE_MODE) {
			try {
				if (FacturaDetalleColeccion.size() > 0) {
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
						
						System.out.println("pathsuBBBB:"+Parametros.getRutaCarpetaReportes());
						if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
							parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes()+"/"+(tipoDocumento.getCodigo().equals("VTA")?"jaspers/facturacion/RPNotaVentaDetalle.jasper":"jaspers/facturacion/RPFacturaDetalle.jasper"));
						else 
							throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
						
						if (tipoDocumento.getReembolso().equals("S")){
							parametrosMap.put("reembolso", "S");
							parametrosMap.put("reembolsoGasto", "REEMBOLSO DE GASTO");
						}else{
							parametrosMap.put("reembolso", "N");
							parametrosMap.put("reembolsoGasto", "");
						}
						
						parametrosMap.put("ivaPorcentaje", String.valueOf(Parametros.getIVA()));
						
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
						String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
						String cantidadLetrasSegundaLinea =" ";
						
						if(cantidadLetrasPrimeraLinea.length()>45)
						{
							String letras=cantidadLetrasPrimeraLinea;
							cantidadLetrasPrimeraLinea=letras.substring(0,45);
							cantidadLetrasSegundaLinea=letras.substring(45,letras.length());
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
								parametrosMap.put("ivaTotal", ivaReporte);
								parametrosMap.put("mostrarIVA", "S");
								parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
							}else{
								subtotalReporte = Utilitarios.redondeoValor(factura.getValor().doubleValue());
								ivaReporte = Utilitarios.redondeoValor(factura.getIva().doubleValue());
								parametrosMap.put("valorTotal", subtotalReporte);
								parametrosMap.put("ivaTotal", ivaReporte);
								parametrosMap.put("mostrarIVA", "N");
								parametrosMap.put("porcentajeIVA", formatoEntero.format(IVA_CERO.doubleValue()));
							}
						}else{
							subtotalReporte = Utilitarios.redondeoValor(factura.getValor().doubleValue());
							ivaReporte = Utilitarios.redondeoValor(factura.getIva().doubleValue());
							parametrosMap.put("valorTotal", subtotalReporte);
							parametrosMap.put("ivaTotal", ivaReporte);
							parametrosMap.put("mostrarIVA", "S");
							parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
						}
						
						System.out.println("FILENAME:"+fileName);
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

	private Collection initializeBeanCollection(Long idParametro, boolean isHeader) {
		ArrayList reportRows = new ArrayList();
		Collection rowCollection = null;
		try {
			if (!isHeader)
				rowCollection = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(idParametro);
			else
				rowCollection = SessionServiceLocator.getFacturaSessionService().findFacturaById(idParametro);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator itRows = rowCollection.iterator();

		while (itRows.hasNext()) {
			if (!isHeader) {
				FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
				reportRows.add(bean);
			} else {
				FacturaIf bean = (FacturaIf) itRows.next();
				reportRows.add(bean);
			}
		}

		return reportRows;
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
		getTxtPuntoImpresion().setText("");
		getTxtMoneda().setText("");
		getTxtFormaPago().setText("");
		getTxtListaPrecio().setText("");
		getTxtCaja().setText("");
		getTxtFechaVencimiento().setText("");
		getTxtOrigenDocumento().setText("");
		getTxtVendedor().setText("");
		getTxtBodega().setText("");
		getTxtPreimpreso().setText("");
		getTxtProveedor().setText("");
		getTxtLinea().setText("");
		getTxtCantidad().setText("");
		getTxtPrecioReal().setText("");
		getTxtCantidadDevuelta().setText("");
		getTxtDescuento().setText("");
		getTxtDescuento().setText("");
		getTxtValorFinal().setText("");
		getTxtDescuentoFinal().setText("");
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
		valorBruto = 0.00;
		descuentoTotal = 0.00;
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
		getCmbEstado().setSelectedIndex(0);
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
		getTxtDescuento().setEditable(false);
		getTxtLote().setEditable(false);
		getTxtValorFinal().setEditable(false);
		getTxtDescuentoFinal().setEditable(false);
		getTxtIVAFinal().setEditable(false);
		getTxtICEFinal().setEditable(false);
		getTxtOtroImpuestoFinal().setEditable(false);
		getTxtTotalFinal().setEditable(false);
		getTxtPorcentajeComision().setEditable(false);
		getTxtValorComision().setEditable(false);
		getTxtFacturaProveedor().setEditable(false);
		getJtpFactura().setSelectedIndex(0);
		getTxtPreimpreso().grabFocus();
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
		getTxtDescuento().setEditable(false);
		getTxtLote().setEditable(false);
		getTxtValorFinal().setEditable(false);
		getTxtDescuentoFinal().setEditable(false);
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

			if (ESTADO_ANULADO.equals(factura.getEstado()))
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

			if (factura.getPedidoId() != null) {
				pedido = SessionServiceLocator.getPedidoSessionService().getPedido(factura.getPedidoId());
				String codigo = pedido.getReferencia();
				Long idEmpresa = Parametros.getIdEmpresa();
				Map parameterMap = new HashMap();
				parameterMap.put("codigo", codigo);
				
				if (("PRESUPUESTO".substring(0, 1)).equals(pedido.getTiporeferencia())) {
					Iterator it = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByQuery(parameterMap, idEmpresa).iterator();
					while (it.hasNext()) {
						PresupuestoIf presupuesto = (PresupuestoIf) it.next();
						getTxtPlanMedios().setText("");
						getTxtPresupuesto().setText(presupuesto.toString());
					}
				} else if (("MEDIOS".substring(0, 1)).equals(pedido.getTiporeferencia())) {
					Iterator it = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigo(codigo).iterator();
					while (it.hasNext()) {
						PlanMedioIf planMedios = (PlanMedioIf) it.next();
						getTxtPresupuesto().setText("");
						getTxtPlanMedios().setText(planMedios.getCodigo());
					}
				}
			}

			UsuarioIf usuario = SessionServiceLocator.getUsuarioSessionService().getUsuario(factura.getUsuarioId());
			facturador = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(usuario.getEmpleadoId());
			getTxtFacturadoPor().setText(facturador.getNombres() + " " + facturador.getApellidos());
			if (factura.getVendedorId() != null) {
				EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(factura.getVendedorId());
				getTxtVendedor().setText(empleado.getNombres() + " " + empleado.getApellidos());
			} else
				getTxtVendedor().setText("");
			BodegaIf bodega = SessionServiceLocator.getBodegaSessionService().getBodega(factura.getBodegaId());
			getTxtBodega().setText(bodega.getCodigo() + " - " + bodega.getNombre());

			if (factura.getOrigendocumentoId() != null) {
				OrigenDocumentoIf origenDocumento = SessionServiceLocator.getOrigenDocumentoSessionService().getOrigenDocumento(factura.getOrigendocumentoId());
				getTxtOrigenDocumento().setText(origenDocumento.getCodigo() + " - " + origenDocumento.getNombre());
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
				filaPlantillaDetalle.add(formatoDecimal.format(Utilitarios.redondeoValor(pedidoDetalleIf.getPrecioReal().doubleValue())));
				Double cantidad = pedidoDetalleIf.getCantidad().doubleValue() * pedidoDetalleIf.getPrecioReal().doubleValue();
				Double descuento = 0D;
				Double descuentoGlobal = 0D;
				if ( cantidad != 0 ){
					descuento = Utilitarios.redondeoValor((pedidoDetalleIf.getDescuento().doubleValue() * 100) / (cantidad));
					descuentoGlobal = Utilitarios.redondeoValor((pedidoDetalleIf.getDescuentoGlobal().doubleValue() * 100) / (cantidad));
				}

				filaPlantillaDetalle.add(String.valueOf(descuento.intValue()) + " % ");
				filaPlantillaDetalle.add(formatoDecimal.format(Utilitarios.redondeoValor(descuentoGlobal.doubleValue())) + " % ");
				if (pedidoDetalleIf.getIva().doubleValue() > 0D)
					filaPlantillaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
				else
					filaPlantillaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");
				
				getTxtDescuentoGlobal().setText(formatoDecimalDescuentoGlobalPorcentaje.format(descuentoGlobal));
				descuento = pedidoDetalleIf.getDescuento().doubleValue();
				descuentoGlobal = Utilitarios.redondeoValor(pedidoDetalleIf.getDescuentoGlobal().doubleValue());
				Double iva = pedidoDetalleIf.getIva().doubleValue();
				subtotal = pedidoDetalleIf.getPrecioReal().doubleValue() * pedidoDetalleIf.getCantidad().doubleValue();
				Double comisionAgencia = ((subtotal - descuento - descuentoGlobal) * porcentajeComisionAgencia) / 100D;

				if (documento.getBonificacion().equals(OPCION_NO)) {
					subTotal += subtotal;
					this.getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					this.getTxtICEFinal().setText("0.00");
					this.getTxtOtroImpuestoFinal().setText("0.00");
					descuentoTotal = descuentoTotal + descuento + descuentoGlobal;
					this.getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
					comisionAgenciaTotal = comisionAgenciaTotal + comisionAgencia;
					this.getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
					ivaTotal += iva;
					this.getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
					total = subTotal - descuentoTotal + ivaTotal + comisionAgenciaTotal;
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

			getTxtCantidad().setText(String.valueOf(Double.valueOf(facturaDetalleIf.getCantidad().doubleValue()).intValue()));
			getTxtPrecio().setText(formatoDecimal.format(Utilitarios.redondeoValor(facturaDetalleIf.getPrecio().doubleValue())));
			if (facturaDetalleIf.getCosto() != null)
				getTxtCosto().setText(formatoDecimal.format(Utilitarios.redondeoValor(facturaDetalleIf.getCosto().doubleValue())));
			getTxtPrecioReal().setText(formatoDecimal.format(Utilitarios.redondeoValor(facturaDetalleIf.getPrecioReal().doubleValue())));
			getTxtCantidadDevuelta().setText(String.valueOf(Double.valueOf(facturaDetalleIf.getCantidadDevuelta().doubleValue()).intValue()));
			Double cantidad = facturaDetalleIf.getCantidad().doubleValue() * facturaDetalleIf.getPrecioReal().doubleValue();
			Double descuento = 0.0;
			if ( cantidad != 0)
				descuento = (facturaDetalleIf.getDescuento().doubleValue() * 100) / (cantidad);
			getTxtDescuento().setText(String.valueOf(descuento.intValue()));
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
	
	private void procesarReactivacionFacturasAnuladas() {
		try {
			Iterator logCarteraIterator = SessionServiceLocator.getFacturaSessionService().findLogCarteraFacturaAnulada(factura).iterator();
			LogCarteraIf logCartera = (logCarteraIterator.hasNext())?(LogCarteraIf) logCarteraIterator.next():null;
			Iterator logAsientoIterator = SessionServiceLocator.getFacturaSessionService().findLogAsientoFacturaAnulada(factura).iterator();
			LogAsientoIf logAsiento = (logAsientoIterator.hasNext())?(LogAsientoIf) logAsientoIterator.next():null;
			if (factura != null && logCartera != null && logAsiento != null) {
				SessionServiceLocator.getFacturaSessionService().procesarReactivacionFacturasAnuladas(factura, logCartera, logAsiento, (UsuarioIf) Parametros.getUsuarioIf());
				SpiritAlert.createAlert("Reactivación de factura realizada con éxito", SpiritAlert.INFORMATION);
				
				//Si la factura venia de un presupuesto, vuelvo a poner el presupuesto en estado Facturado.	
				PedidoIf pedido = SessionServiceLocator.getPedidoSessionService().getPedido(factura.getPedidoId());
				if(pedido.getTiporeferencia() != null && pedido.getReferencia() != null){
					if(pedido.getTiporeferencia().equals(TIPO_PRESUPUESTO)){
						if(SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigoAndEmpresaId(pedido.getReferencia(), Parametros.getIdEmpresa()).size() > 0){
							PresupuestoIf presupuesto = (PresupuestoIf)SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigoAndEmpresaId(pedido.getReferencia(), Parametros.getIdEmpresa()).iterator().next();
							presupuesto.setEstado(PRESUPUESTO_ESTADO_FACTURADO);
							SessionServiceLocator.getPresupuestoSessionService().savePresupuesto(presupuesto);
						}
					}
				}
			
			} else if (logCartera == null || logAsiento == null) {
				SpiritAlert.createAlert("No se puede reactivar factura. Log de factura no disponible", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
	}
}
