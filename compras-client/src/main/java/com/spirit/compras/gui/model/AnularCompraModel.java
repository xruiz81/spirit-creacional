package com.spirit.compras.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.gui.criteria.CompraCriteria;
import com.spirit.compras.gui.panel.JPAnularCompra;
import com.spirit.compras.handler.OrderData;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.ServiceInstantiationException;
import com.spirit.exception.UnknownServiceException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class AnularCompraModel extends JPAnularCompra {

	private static final long serialVersionUID = 4054251552379974630L;
	
	private List<CompraDetalleIf> compraDetalleColeccion = new ArrayList<CompraDetalleIf>();
	private Vector<CompraRetencionIf> compraRetencionColeccion = new Vector<CompraRetencionIf>();
	
	private TipoDocumentoIf tipoDocumentoIf;
	private DocumentoIf documentoIf;
	private ClienteOficinaIf proveedorIf;
	private DefaultTableModel modelTblRetenciones;
	private CompraRetencionIf compraRetencion  = null;
	private int filaSeleccionadaRetencion = -1;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private CompraIf compra;
	private Vector<OrderData> orderDataVector = new Vector<OrderData>();
	private ProductoIf productoIf;
	private static final int MAX_LONGITUD_CODIGO = 10;
	private static final String NOMBRE_TIPO_COMPRA_IMPORTACION = "IMPORTACIÓN";
	private static final String NOMBRE_TIPO_COMPRA_LOCAL = "LOCAL";
	private static final String TIPO_COMPRA_LOCAL = NOMBRE_TIPO_COMPRA_LOCAL.substring(0, 1);
	private static final String IMPUESTO_RENTA = "RENTA";
	private static final String IMPUESTO_IVA = "IVA";
	//private static final int MAX_LONGITUD_PREIMPRESO = 15;
	
	enum EstadoCompra {ACTIVA,INACTIVA,ANULADA}
	
	public AnularCompraModel() {
		initKeyListeners();
		iniciarComponentes();
		setSorterTable(getTblCompraDetalle());
		cargarCombos();
		initListeners();
		setSorterTable(getTblCompraDetalle());
		getTblCompraDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.clean();
		this.showSaveMode();
		new HotKeyComponent(this);
	}
	
	private void initListeners(){

		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTblCompraDetalle().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				selectRow();
			}
		});
		
		getTblCompraDetalle().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				selectRow();
			}
		});
		
		getCmbTipoDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoDocumento().getSelectedItem() != null) {
					tipoDocumentoIf = (TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem();
					
					if (tipoDocumentoIf != null) {
						try {
							getCmbDocumento().removeAllItems();
							SpiritComboBoxModel cmbModelDocumento = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipodocumentoIdAndUsuarioId(tipoDocumentoIf.getId(), ((UsuarioIf) Parametros.getUsuarioIf()).getId()));
							getCmbDocumento().setModel(cmbModelDocumento);
							if (getCmbDocumento().getItemCount() > 0) {
								if (getMode() != SpiritMode.FIND_MODE) {
									getCmbDocumento().setEnabled(true);
									if (getCmbDocumento().getItemCount() > 0)
										getCmbDocumento().setSelectedIndex(0);
								}
							}
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
				}
			}
		});
		
	}
	
	private void selectRow() {
		if (getTblCompraDetalle().getSelectedRow() != -1) {
			int filaSeleccionada = getTblCompraDetalle().convertRowIndexToModel(getTblCompraDetalle().getSelectedRow() );
			CompraDetalleIf compraDetalleForUpdate = (CompraDetalleIf) compraDetalleColeccion.get(filaSeleccionada);
			enableCompraDetalleForUpdate(compraDetalleForUpdate);
		}
	}
	
	private void enableCompraDetalleForUpdate(CompraDetalleIf compraDetalle) {
		try {
			productoIf = SessionServiceLocator.getProductoSessionService().getProducto(compraDetalle.getProductoId());
			cargarComboRetencion();
			GenericoIf genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
			Long idProductoTemp = productoIf.getId();
			String codigoProductoTemp = productoIf.getCodigo();
			getCmbDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocumento(),compraDetalle.getDocumentoId()));
			getCmbDocumento().validate();
			getCmbDocumento().repaint();
			if ("S".equals(genericoIf.getUsaLote())) {
				PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
				getTxtProducto().setText(productoIf.getCodigo() + " - " + genericoIf.getNombre()+ " - " + presentacion.getNombre());
			} else
				getTxtProducto().setText(productoIf.getCodigo() + " - " + genericoIf.getNombre());
			
			Long cantidad = compraDetalle.getCantidad();
			getTxtCantidad().setText(compraDetalle.getCantidad().toString());
			Double valor = compraDetalle.getValor().doubleValue();
			getTxtValor().setText(formatoDecimal.format(valor));
			double porcentajeDescuentoEspecial = compraDetalle.getPorcentajeDescuentoEspecial().doubleValue();
			getTxtPorcentajeDescuentoEspecial().setText(formatoDecimal.format(porcentajeDescuentoEspecial));
			double descuentoEspecial = (valor * cantidad) * (porcentajeDescuentoEspecial / 100);
			Double descuento = (compraDetalle.getDescuento().doubleValue() * 100D) / ((valor * cantidad) - descuentoEspecial);
			getTxtPorcentajeDescuentoAgencia().setText(formatoDecimal.format(descuento));
			double porcentajeDescuentosVarios = compraDetalle.getPorcentajeDescuentosVarios().doubleValue();
			getTxtPorcentajeDescuentosVarios().setText(formatoDecimal.format(porcentajeDescuentosVarios));
			String strOtroImpuesto = compraDetalle.getOtroImpuesto().toString();
			Double otroImpuesto = (Double.parseDouble(strOtroImpuesto) * 100D / (valor * cantidad));
			getTxtOtroImpuesto().setText(String.valueOf(otroImpuesto.intValue()));
			if(compraDetalle.getDescripcion() != null){
				getTxtDescripcion().setText(compraDetalle.getDescripcion());
			}else{
				getTxtDescripcion().setText("");
			}
			getTxtCantidad().setEditable(false);
			getTxtValor().setEditable(false);
			getTxtPorcentajeDescuentoAgencia().setEditable(false);
			getTxtPorcentajeDescuentoEspecial().setEditable(false);
			getTxtPorcentajeDescuentosVarios().setEditable(false);
			getTxtOtroImpuesto().setEditable(false);
			getCmbRetencionRenta().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionRenta(),compraDetalle.getIdSriAir()));
			getCmbRetencionRenta().validate();
			getCmbRetencionRenta().repaint();
			getCmbRetencionIva().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionIva(),compraDetalle.getSriIvaRetencionId()));
			getCmbRetencionIva().validate();
			getCmbRetencionIva().repaint();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void initKeyListeners() {
		getCmbFecha().setLocale(Utilitarios.esLocale);
		getCmbFechaVencimiento().setLocale(Utilitarios.esLocale);
		getCmbFechaCaducidad().setLocale(Utilitarios.esLocale);
		getCmbFecha().setEditable(false);
		getCmbFechaVencimiento().setEditable(false);
		getCmbFechaCaducidad().setEditable(false);
		getCmbFecha().setShowNoneButton(false);
		getCmbFechaVencimiento().setShowNoneButton(false);
		getCmbFechaCaducidad().setShowNoneButton(false);
		getCmbFechaEmision().setLocale(Utilitarios.esLocale);
		getCmbFechaEmision().setEditable(false);
		getCmbFechaEmision().setShowNoneButton(false);
		getCmbFechaEmision().setFormat(Utilitarios.setFechaUppercase());
		
		//Calculo y seteo la maxima longitud del preimpreso segun lo fijado en Parametros Empresa
		int maxlongPreimpEstablecimiento = Parametros.getMaximaLongitudPreimpresoEstablecimiento();
		int maxlongPreimpPuntoEmision = Parametros.getMaximaLongitudPreimpresoPuntoEmision();
		int maxlongPreimpSecuencial = Parametros.getMaximaLongitudPreimpresoSecuencial();
		int guionesSeparadores = 2;
		int longitudPreimpreso = maxlongPreimpEstablecimiento + maxlongPreimpPuntoEmision + maxlongPreimpSecuencial + guionesSeparadores;
		
		getTxtPreimpreso().addKeyListener(new TextChecker(longitudPreimpreso));
	}
	
	//KeyListener oKeyListenerTxtPreimpreso = new TextChecker(longitudPreimpreso);
	
	private void iniciarComponentes(){
		getBtnBuscarProveedor().setToolTipText("Buscar proveedor");
		getBtnBuscarProveedor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		//MODIFIED 20 JULIO
		//getBtnBuscarOrdenCompra().setToolTipText("Buscar orden de compra");
		//getBtnBuscarOrdenCompra().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarOrden().setToolTipText("Buscar orden");
		getBtnBuscarOrden().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarProducto().setToolTipText("Buscar producto");
		getBtnBuscarProducto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnAgregarDetalle().setText("");
		getBtnAgregarDetalle().setToolTipText("Agregar detalle");
		getBtnAgregarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarDetalle().setText("");
		getBtnActualizarDetalle().setToolTipText("Actualizar detalle");
		getBtnActualizarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnEliminarDetalle().setText("");
		getBtnEliminarDetalle().setToolTipText("Eliminar detalle");
		getBtnEliminarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnActualizarRetencion().setText("");
		getBtnActualizarRetencion().setToolTipText("Actualizar retención");
		getBtnActualizarRetencion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblCompraDetalle().getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(8).setCellRenderer(tableCellRenderer);
		
		getTblRetenciones().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		getTblRetenciones().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblRetenciones().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		getTblRetenciones().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		
		getTblRetenciones().getTableHeader().setReorderingAllowed(false);
		TableColumn anchoColumna = getTblRetenciones().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblRetenciones().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblRetenciones().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblRetenciones().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblRetenciones().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblRetenciones().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblRetenciones().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblRetenciones().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(95);
	}
	
	public void clean() {
		// TODO Auto-generated method stub
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void find() {
		try {
			Map mapa = buildQuery();
			int tamanoLista = SessionServiceLocator.getCompraSessionService().findCompraByQueryListSize(mapa, Parametros.getIdEmpresa());
			if (tamanoLista > 0) {
				CompraCriteria compraCriteria = new CompraCriteria(true);
				compraCriteria.setResultListSize(tamanoLista);
				compraCriteria.setQueryBuilded(mapa);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), compraCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if (popupFinder.getElementoSeleccionado() != null)
					getSelectedObject(popupFinder.getElementoSeleccionado());
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		} catch (Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en la búsqueda de información", SpiritAlert.ERROR);
		}
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		Long proveedorId = 0L, tipoDocumentoId = 0L;
		
		if (getCmbTipoCompra().getSelectedItem() != null) {
			if ("".compareTo(getCmbTipoCompra().getSelectedItem().toString().substring(0, 1)) != 0)
				aMap.put("localimportada", getCmbTipoCompra().getSelectedItem().toString().substring(0, 1));
		}
		
		if (getTxtCodigo().getText().equals("") == false)
			aMap.put("codigo", getTxtCodigo().getText() + "%");
		else
			aMap.put("codigo", "%");
		
		if (getTxtReferencia().getText().equals("") == false)
			aMap.put("referencia", getTxtReferencia().getText() + "%");
		
		if (proveedorIf != null) {
			proveedorId = proveedorIf.getId();
			aMap.put("proveedorId", proveedorId);
		}
		
		if (tipoDocumentoIf != null) {
			tipoDocumentoId = tipoDocumentoIf.getId();
			aMap.put("tipodocumentoId", tipoDocumentoId);
		}
		
		if ( getCmbEstado().getSelectedItem()!=null ){
			EstadoCompra estado = (EstadoCompra)getCmbEstado().getSelectedItem(); 
			aMap.put("estado", getLetraEstadoCompra(estado));
		}
		
		try {
			if ("".equals(getTxtPreimpreso().getText()) == false)
				aMap.put("preimpreso", this.getTxtPreimpreso().getText());
		} catch (java.lang.NumberFormatException e) {
			SpiritAlert.createAlert("El Preimpreso de factura ingresado no es válido", SpiritAlert.ERROR);
			getTxtPreimpreso().setText("");
			getTxtPreimpreso().grabFocus();
		}
		
		return aMap;
	}
	
	public void getSelectedObject(Object compraSeleccionada) {
		compra = (CompraIf) compraSeleccionada;
		this.showUpdateMode();
		cleanTableDetalle();
		
		try {
			proveedorIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(compra.getProveedorId());
			ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorIf.getClienteId());
				
			getTxtCodigo().setText(compra.getCodigo());
			Calendar calendarFecha = new GregorianCalendar();
			calendarFecha.setTime(compra.getFecha());
			getCmbFecha().setCalendar(calendarFecha);
			getCmbFecha().repaint();
			Calendar calendarFechaVencimiento = new GregorianCalendar();
			calendarFechaVencimiento.setTime(compra.getFechaVencimiento());
			getCmbFechaVencimiento().setCalendar(calendarFechaVencimiento);
			getCmbFechaVencimiento().repaint();
			Calendar calendarFechaCaducidad = new GregorianCalendar();
			if ( compra.getFechaCaducidad()!=null )
				calendarFechaCaducidad.setTime(compra.getFechaCaducidad());
			getCmbFechaCaducidad().setCalendar(calendarFechaCaducidad);
			getCmbFechaCaducidad().repaint();
			getTxtProveedor().setText(proveedor.getIdentificacion() + " - " + proveedorIf.getDescripcion()); // " - " + proveedor.getNombreLegal() + 
			getTxtPreimpreso().setText(compra.getPreimpreso());
			getTxtAutorizacion().setText(compra.getAutorizacion());
			getCmbTipoDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoDocumento(), compra.getTipodocumentoId()));
			getCmbMoneda().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbMoneda(), compra.getMonedaId()));
			getCmbTipoSustentoTributario().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoSustentoTributario(), compra.getIdSriSustentoTributario()));
			
			getCmbEstado().setSelectedItem(getEstadoCompra(compra.getEstado()));
			
			if (TIPO_COMPRA_LOCAL.equals(compra.getLocalimportada()))
				getCmbTipoCompra().setSelectedItem(NOMBRE_TIPO_COMPRA_LOCAL);
			else
				getCmbTipoCompra().setSelectedItem(NOMBRE_TIPO_COMPRA_IMPORTACION);
			
			getTxtObservacion().setText(compra.getObservacion());
			
			if (proveedorIf != null)
				getBtnBuscarOrden().setEnabled(true);
				//getBtnBuscarOrdenCompra().setEnabled(true); //MODIFIED 20 JULIO
			Iterator<OrdenAsociadaIf> ordenAsociadaIt = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(compra.getId()).iterator();
			while (ordenAsociadaIt.hasNext()) {
				OrdenAsociadaIf ordenAsociada = ordenAsociadaIt.next();
				OrderData orden = new OrderData();
				orden.setProvider(SessionServiceLocator.getClienteSessionService().getCliente(proveedorIf.getClienteId()));
				orden.setOrderType(ordenAsociada.getTipoOrden());
				if (ordenAsociada.getTipoOrden().equals("OC"))
					orden.setPurchaseOrder(SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(ordenAsociada.getOrdenId()));
				else
					orden.setMediaOrder(SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenAsociada.getOrdenId()));
				orderDataVector.add(orden);
			}
			
			getTxtReferencia().setEditable(true);
			getTxtReferencia().setText(compra.getReferencia());
			DefaultTableModel tableModel = (DefaultTableModel) getTblCompraDetalle().getModel();
			compraDetalleColeccion = (List) SessionServiceLocator.getCompraDetalleSessionService().findCompraDetalleByCompraId(compra.getId());
			Iterator it = compraDetalleColeccion.iterator();
			
			while (it.hasNext()) {
				CompraDetalleIf compraDetalle = (CompraDetalleIf) it.next();
				productoIf = SessionServiceLocator.getProductoSessionService().getProducto(compraDetalle.getProductoId());
				cargarComboRetencion();
				
				if (compraDetalle.getIdSriAir() != null)
					getCmbRetencionRenta().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionRenta(),compraDetalle.getIdSriAir()));
				
				if (compraDetalle.getSriIvaRetencionId() != null)
					getCmbRetencionIva().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionIva(),compraDetalle.getSriIvaRetencionId()));
				
				
				Vector<String> fila = new Vector<String>();
				double cantidad = Double.parseDouble(compraDetalle.getCantidad().toString());
				double valor = Double.parseDouble(compraDetalle.getValor().toString());
				double subtotal = cantidad * valor;
				double otroImpuestoSubtotal = Double.parseDouble(compraDetalle.getOtroImpuesto().toString());
				double porcentajeDescuentoEspecial = compraDetalle.getPorcentajeDescuentoEspecial().doubleValue() / 100;
				double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;				
				double descuentoSubtotal = Double.parseDouble(compraDetalle.getDescuento().toString());
				double porcentajeDescuentosVarios = compraDetalle.getPorcentajeDescuentosVarios().doubleValue() / 100;
				double descuentosVarios = (subtotal - descuentoEspecial) * porcentajeDescuentosVarios;
				double iva = Double.parseDouble(compraDetalle.getIva().toString());
				SriAirIf sriAir = (SriAirIf) getCmbRetencionRenta().getSelectedItem();
				SriIvaRetencionIf sriIvaRetencion = (SriIvaRetencionIf) getCmbRetencionIva().getSelectedItem();
				
				double porcentaje_retencion_fuente = 0D;
				if (sriAir != null)
					porcentaje_retencion_fuente = sriAir.getPorcentaje().doubleValue();
				
				double porcentaje_retencion_iva = 0D;
				if (sriIvaRetencion != null)
					porcentaje_retencion_iva = sriIvaRetencion.getPorcentaje().doubleValue();
				
				double retencion_fuente = (subtotal - descuentoEspecial - descuentoSubtotal - descuentosVarios) * (porcentaje_retencion_fuente / 100);
				double retencion_iva = iva * (porcentaje_retencion_iva / 100);
				
				double ice = Double.parseDouble(compraDetalle.getIce().toString());
				subtotal = subtotal - descuentoEspecial - descuentoSubtotal - descuentosVarios + iva + ice + otroImpuestoSubtotal;
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
				PresentacionIf presentacion = null;
				if (productoIf.getPresentacionId() != null)
					presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
				String productoNombre = productoIf.getCodigo() + " - " + generico.getNombre();
				if (presentacion != null)
					productoNombre += " - " + presentacion.getNombre();
				fila.add(productoNombre);
				fila.add(String.valueOf(Double.valueOf(cantidad).intValue()));
				fila.add(formatoDecimal.format(valor));
				fila.add(formatoDecimal.format(descuentoEspecial));
				fila.add(formatoDecimal.format(descuentoSubtotal));
				fila.add(formatoDecimal.format(descuentosVarios));
				fila.add(formatoDecimal.format(iva));
				fila.add(formatoDecimal.format(retencion_fuente + retencion_iva));
				fila.add(formatoDecimal.format(ice));
				fila.add(formatoDecimal.format(otroImpuestoSubtotal));
				fila.add(formatoDecimal.format(subtotal));
				tableModel.addRow(fila);
			}
			
			actualizarTotales();
			
			cargarTablaRetenciones();
			
		} catch (UnknownServiceException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		} catch (ServiceInstantiationException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarTablaRetenciones() throws GenericBusinessException {
		Collection compraRetencionesColeccion = SessionServiceLocator.getCompraRetencionSessionService().findCompraRetencionByCompraId(compra.getId());
		Iterator itCompraRetenciones = compraRetencionesColeccion.iterator();
		
		while (itCompraRetenciones.hasNext()) {
			CompraRetencionIf  compraRetencionIf = (CompraRetencionIf) itCompraRetenciones.next();
			compraRetencionColeccion.add(compraRetencionIf);
			
			modelTblRetenciones = (DefaultTableModel) getTblRetenciones().getModel();
			Vector<String> filaRetenciones = new Vector<String>();
			filaRetenciones.add(compraRetencionIf.getEjercicioFiscal());
			filaRetenciones.add(compraRetencionIf.getEstablecimiento() +" - "+ compraRetencionIf.getPuntoEmision() + " - " + compraRetencionIf.getSecuencial());
			filaRetenciones.add(compraRetencionIf.getAutorizacion());
			filaRetenciones.add(formatoDecimal.format(compraRetencionIf.getBaseImponible()));
			if(compraRetencionIf.getImpuesto().equals(IMPUESTO_RENTA.substring(0,1))){
				filaRetenciones.add(IMPUESTO_RENTA);
			}else if(compraRetencionIf.getImpuesto().equals(IMPUESTO_IVA.substring(0,1))){
				filaRetenciones.add(IMPUESTO_IVA);
			}
			filaRetenciones.add(formatoDecimal.format(compraRetencionIf.getPorcentajeRetencion()));
			filaRetenciones.add(formatoDecimal.format(compraRetencionIf.getValorRetenido()));
			filaRetenciones.add(Utilitarios.getFechaUppercase(compraRetencionIf.getFechaEmision()));
			modelTblRetenciones.addRow(filaRetenciones);				
			cleanRetencion();
		}
	}

	public void report() {
		// TODO Auto-generated method stub
		
	}

	public void save() {
		SpiritAlert.createAlert("No se puede guardar la compra!", SpiritAlert.WARNING);
	}

	public void update() {
		EstadoCompra estado = getEstadoCompra(compra.getEstado());
		
		if ( estado!=EstadoCompra.ANULADA ){
			if ( confirmarAnularCompra() ) {
				try {
					Map queryMap = new HashMap();
					queryMap.put("tipodocumentoId", compra.getTipodocumentoId());
					queryMap.put("referenciaId", compra.getId());
					Iterator<CarteraIf> carteraIterador = SessionServiceLocator.getCarteraSessionService().findCarteraByQuery(queryMap).iterator();
					if (carteraIterador.hasNext()) {
						CarteraIf cartera = carteraIterador.next();
						queryMap.clear();
						queryMap.put("documentoId", null);
						queryMap.put("referencia", String.valueOf(cartera.getId()));
						Iterator<CarteraDetalleIf> it = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByQuery(queryMap).iterator();
						if (!it.hasNext()) {
							compra.setEstado(getLetraEstadoCompra(EstadoCompra.ANULADA));
							SessionServiceLocator.getCarteraSessionService().procesarAnularCompra(compra, Parametros.getUsuario());
							SpiritAlert.createAlert("Compra anulada con éxito !!", SpiritAlert.INFORMATION);
							//Si anulo una compra, paso el estado de la orden de compra (si existe) otra vez a ENVIADA
							Iterator<OrdenAsociadaIf> ordenAsociadaIt = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(compra.getId()).iterator();
							while (ordenAsociadaIt.hasNext()) {
								OrdenAsociadaIf ordenAsociada = ordenAsociadaIt.next();
								if (ordenAsociada.getTipoOrden().equals("OC")) {
									OrdenCompraIf ordenCompra = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(ordenAsociada.getOrdenId());
									if(ordenCompra != null){
										ordenCompra.setEstado("E");
										SessionServiceLocator.getOrdenCompraSessionService().saveOrdenCompra(ordenCompra);
									}									
								} else {
									OrdenMedioIf ordenMedio = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenAsociada.getOrdenId());
									if(ordenMedio != null){
										ordenMedio.setEstado("E");
										SessionServiceLocator.getOrdenMedioSessionService().saveOrdenMedio(ordenMedio);
									}									
								}
								SessionServiceLocator.getOrdenAsociadaSessionService().deleteOrdenAsociada(ordenAsociada.getId());
							}
							showFindMode();
						} else {
							SpiritAlert.createAlert("Existe un pago aprobado para esta compra. Si desea anular la compra primero debe desaprobar el pago", SpiritAlert.WARNING);
						}
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
				} catch(Exception e ){
					e.printStackTrace();
					SpiritAlert.createAlert("Error general en anulación de compra !!", SpiritAlert.ERROR);
				}
			}
		} else {
			SpiritAlert.createAlert("Compra ya está anulada !!", SpiritAlert.INFORMATION);
		}
	}
	
	private boolean confirmarAnularCompra(){
		Object[] options = {"Si","No"}; 
		int opcion = JOptionPane.showOptionDialog(
				null,"¿Está seguro de que desea anular la compra?", "Confirmación"
				,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE
				,null,options,"No");
		return opcion == JOptionPane.YES_OPTION ? true : false;
	}
	
	private String getLetraEstadoCompra( EstadoCompra estadoCompra ){
		if ( estadoCompra == EstadoCompra.ANULADA )
			return "N";
		else
			return estadoCompra.toString().substring(0,1);
	}
	
	private EstadoCompra getEstadoCompra(String letraEstado ){
		if ( "A".equals(letraEstado) )
			return EstadoCompra.ACTIVA;
		else if ( "I".equals(letraEstado) )
			return EstadoCompra.INACTIVA;
		else 
			return EstadoCompra.ANULADA;
	}

	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	private void cargarCombos() {
		// Combos estáticos
		getCmbEstado().addItem(EstadoCompra.ACTIVA);
		getCmbEstado().addItem(EstadoCompra.ANULADA);
		//getCmbEstado().addItem(EstadoCompra.INACTIVA);
		//getCmbEstado().addItem(EstadoCompra.ANULADA);
		getCmbTipoCompra().addItem(NOMBRE_TIPO_COMPRA_LOCAL);
		getCmbTipoCompra().addItem(NOMBRE_TIPO_COMPRA_IMPORTACION);
		// DateCombos
		getCmbFecha().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaVencimiento().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaCaducidad().setFormat(Utilitarios.setFechaUppercase());
		if (getMode() == SpiritMode.FIND_MODE) {
			getCmbFecha().setSelectedItem(null);
			getCmbFechaVencimiento().setSelectedItem(null);
			getCmbFechaCaducidad().setSelectedItem(null);
		}
		
		/*if (getMode() == SpiritMode.SAVE_MODE) {
			Calendar calendarInicio = new GregorianCalendar();
			Calendar now = Calendar.getInstance();
			calendarInicio.setTime(now.getTime());
			getCmbFecha().setCalendar(calendarInicio);
			getCmbFecha().getDateModel().setMinDate(now);
			getCmbFechaVencimiento().setCalendar(calendarInicio);
			getCmbFechaVencimiento().getDateModel().setMinDate(now);
		} else if (getMode() == SpiritMode.UPDATE_MODE) {
			getCmbFecha().getDateModel().setMinDate(null);
			getCmbFechaVencimiento().getDateModel().setMinDate(null);
		}*/
		// Combos dinámicos
		cargarComboTipoDocumento();
		cargarComboMonedas();
		cargarComboTipoSustentoTributario();
	}
	
	private void cargarComboTipoDocumento() {
		Long idModulo;
		Collection collection = null;
		try {
			idModulo = ((ModuloIf) SessionServiceLocator.getModuloSessionService().findModuloByNombre("COMPRAS").iterator().next()).getId();
			Map parameterMap = new HashMap();
			parameterMap.put("moduloId", idModulo);
			parameterMap.put("empresaId", Long.valueOf(Parametros.getIdEmpresa()));
			List tiposDocumentos = (List)SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQuery(parameterMap); 
			refreshCombo(getCmbTipoDocumento(), tiposDocumentos);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboMonedas() {
		try {
			List monedas = (List)SessionServiceLocator.getMonedaSessionService().getMonedaList();
			refreshCombo(getCmbMoneda(),monedas);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboTipoSustentoTributario() {
		try {
			List sustentosTributarios = (List) SessionServiceLocator.getSriSustentoTributarioSessionService().getSriSustentoTributarioList();
			refreshCombo(getCmbTipoSustentoTributario(),sustentosTributarios);
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private Comparator<SriAirIf> sriAirCodigoComparador = new Comparator<SriAirIf>(){
		public int compare(SriAirIf o1, SriAirIf o2) {
			return o1.getCodigo().compareTo(o2.getCodigo());
		}
	};
	
	private Comparator<SriIvaRetencionIf> sriIvaRetencionCodigoComparador = new Comparator<SriIvaRetencionIf>(){
		public int compare(SriIvaRetencionIf o1, SriIvaRetencionIf o2) {
			return o1.getCodigo().compareTo(o2.getCodigo());
		}
	};
	
	private void cargarComboRetencion() {
		try {
			java.sql.Date fechaCompra = new java.sql.Date(getCmbFecha().getDate().getTime());
			List<SriAirIf> retencionesRenta = new ArrayList<SriAirIf>();
			List<SriIvaRetencionIf> retencionesIva = new ArrayList<SriIvaRetencionIf>();
			Iterator<SriAirIf> retencionesRentaIt = SessionServiceLocator.getSriAirSessionService().findSriAirByFecha(fechaCompra).iterator();
			while (retencionesRentaIt.hasNext()) {
				SriAirIf sriAir =  retencionesRentaIt.next();
				retencionesRenta.add(sriAir);
			}

			Iterator<SriIvaRetencionIf> retencionesIvaIt = SessionServiceLocator.getSriIvaRetencionSessionService().findSriIvaRetencionByFecha(fechaCompra).iterator();
			while (retencionesIvaIt.hasNext()) {
				SriIvaRetencionIf sriIvaRetencion = retencionesIvaIt.next();
				retencionesIva.add(sriIvaRetencion);
			}
			Collections.sort(retencionesRenta,sriAirCodigoComparador);
			refreshCombo(getCmbRetencionRenta(), retencionesRenta);
			Collections.sort(retencionesIva,sriIvaRetencionCodigoComparador);
			refreshCombo(getCmbRetencionIva(), retencionesIva);
			if (getCmbRetencionIva().getItemCount() <= 0)
				getCmbRetencionIva().setEnabled(false);
			else
				getCmbRetencionIva().setEnabled(true);
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void showFindMode() {
		clean();
		cleanRetencion();
		cleanTableDetalle();
		cleanTableRetencion();
		getCmbEstado().setBackground(Parametros.findColor);
		getCmbTipoCompra().setBackground(Parametros.findColor);
		getTxtCodigo().setBackground(Parametros.findColor);
		//MODIFIED 20 JULIO
		//getTxtOrdenCompra().setBackground(Parametros.findColor);
		getTxtOrden().setBackground(Parametros.findColor);
		getTxtProveedor().setBackground(Parametros.findColor);
		getCmbTipoDocumento().setBackground(Parametros.findColor);
		getTxtPreimpreso().setBackground(Parametros.findColor);
		getTxtReferencia().setBackground(Parametros.findColor);
		
		proveedorIf = null;
		tipoDocumentoIf = null;
		getTxtCodigo().setText("");
		getTxtCodigo().setEditable(true);
		getCmbFecha().setSelectedItem(null);
		getCmbFecha().setEditable(false);
		getCmbFechaVencimiento().setSelectedItem(null);
		getCmbFechaVencimiento().setEditable(false);
		getCmbFechaCaducidad().setSelectedItem(null);
		getCmbFechaCaducidad().setEditable(false);
		getTxtOficina().setEditable(false);
		getTxtProveedor().setText("");
		getTxtProveedor().setEditable(false);
		//MODIFIED 20 JULIO
		//getTxtOrdenCompra().setEditable(false);
		getTxtOrden().setEditable(false);
		getBtnBuscarProveedor().setEnabled(true);
		getCmbMoneda().setEditable(false);
		getCmbEstado().setEnabled(true);
		getCmbTipoDocumento().setEditable(false);
		getCmbTipoCompra().setEnabled(true);
		getTxtObservacion().setText("");
		getTxtObservacion().setEditable(false);
		getTxtPreimpreso().setText("");
		getTxtReferencia().setText("");
		getTxtReferencia().setEditable(true);
		getTxtAutorizacion().setText("");
		getTxtAutorizacion().setEditable(false);
		getTxtDescripcion().setText("");
		getTxtDescripcion().setEditable(false);
		getTxtValor().setText("");
		getTxtValor().setEditable(false);
		getTxtValorFinal().setText("");
		getTxtValorFinal().setEditable(false);
		getTxtOtroImpuesto().setText("");
		getTxtOtroImpuesto().setEditable(false);
		getTxtCantidad().setText("");
		getTxtCantidad().setEditable(false);
		getTxtICEFinal().setText("");
		getTxtICEFinal().setEditable(false);
		getTxtIVAFinal().setText("");
		getTxtIVAFinal().setEditable(false);
		getTxtPorcentajeDescuentoAgencia().setText("");
		getTxtPorcentajeDescuentoAgencia().setEditable(false);
		getTxtPorcentajeDescuentoEspecial().setText("");
		getTxtPorcentajeDescuentoEspecial().setEditable(false);
		getTxtPorcentajeDescuentosVarios().setText("");
		getTxtPorcentajeDescuentosVarios().setEditable(false);
		getTxtDescuentoAgenciaFinal().setText("");
		getTxtDescuentoAgenciaFinal().setEditable(false);
		getTxtDescuentoEspecialFinal().setText("");
		getTxtDescuentoEspecialFinal().setEditable(false);
		getTxtDescuentosVariosFinal().setText("");
		getTxtDescuentosVariosFinal().setEditable(false);
		//MODIFIED 20 JULIO
		//getBtnBuscarOrdenCompra().setEnabled(false);
		getBtnBuscarOrden().setEnabled(false);
		getTxtUsuario().setEditable(false);
		getBtnBuscarProducto().setEnabled(false);
		getTxtProducto().setEditable(false);
		getTxtCantidad().setEditable(false);
		getTxtValor().setEditable(false);
		getTxtOtroImpuesto().setEditable(false);
		getTxtRetencionFinal().setEditable(false);
		getTxtOtroImpuestoFinal().setEditable(false);
		getTxtTotalFinal().setEditable(false);
		//MODIFIED 20 JULIO
		//getTxtOrdenCompra().setEditable(false);
		getTxtOrden().setEditable(false);
		getBtnAgregarDetalle().setEnabled(false);
		getBtnActualizarDetalle().setEnabled(false);
		getBtnEliminarDetalle().setEnabled(false);
		getCmbDocumento().setEditable(false);
		getCmbTipoDocumento().setSelectedItem(null);
		getCmbMoneda().setSelectedItem(null);
		getCmbTipoCompra().setSelectedItem(null);
		getCmbFechaEmision().setEditable(false);
		getCmbTipoSustentoTributario().setEditable(false);
		//MODIFIED 20 JULIO
		//getTxtOrdenCompra().setText("");
		getTxtOrden().setText("");
		getCmbRetencionRenta().setEditable(false);
		getCmbRetencionIva().setEditable(false);
		getTxtCodigo().grabFocus();
		
		setFindMode();
	}
	
	public void cleanRetencion(){
		filaSeleccionadaRetencion = -1;
		compraRetencion = null;
		getTxtEstablecimiento().setText("");
		getTxtPuntoEmision().setText("");
		getTxtSecuencial().setText("");
		getTxtAutorizacionRetencion().setText("");
	}
	
	public void cleanTableDetalle() {
		DefaultTableModel model = (DefaultTableModel) this.getTblCompraDetalle().getModel();
		for (int i = this.getTblCompraDetalle().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
		
		compraDetalleColeccion.clear();
		actualizarTotales();
		cleanCompraDetalle();
		this.repaint();
	}
	
	private void cleanCompraDetalle() {
		getTxtProducto().setText("");
		getTxtCantidad().setText("");
		getTxtCantidad().setEditable(false);
		getTxtCantidad().setEditable(false);
		getTxtValor().setText("");
		getTxtValor().setEditable(false);
		getTxtValor().setEditable(false);
		getTxtPorcentajeDescuentoAgencia().setText("");
		getTxtPorcentajeDescuentoAgencia().setEditable(false);
		getTxtPorcentajeDescuentoEspecial().setText("");
		getTxtPorcentajeDescuentoEspecial().setEditable(false);
		getTxtPorcentajeDescuentosVarios().setText("");
		getTxtPorcentajeDescuentosVarios().setEditable(false);
		getTxtOtroImpuesto().setText("");
		getTxtDescripcion().setText("");
		getTxtOtroImpuesto().setEditable(false);
		getTxtOtroImpuesto().setEditable(false);
	}
	
	public void cleanTableRetencion() {
		DefaultTableModel model = (DefaultTableModel) this.getTblRetenciones().getModel();
		for (int i = this.getTblRetenciones().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}
	
	private void actualizarTotales() {
		DefaultTableModel tableModel = (DefaultTableModel) getTblCompraDetalle().getModel();
		
		double totalValor = 0.00;
		double descuentoEspecial = 0D;
		double descuentoAgencia = 0D;
		double descuentosVarios = 0D;
		double totalRetencion = 0.00;
		double totalIVA = 0.00;
		double totalICE = 0.00;
		double totalOtrosImpuestos = 0.00;
		double grandTotal = 0.00;
		
		try {
			for (int fila = 0; fila < getTblCompraDetalle().getRowCount(); fila++) {
				String strCantidad = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 1).toString().trim());
				String strValor = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 2).toString().trim());
				String strDescuentoEspecial = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 3).toString().trim());
				String strDescuentoAgencia = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 4).toString().trim());
				String strDescuentosVarios = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 5).toString().trim());
				String strIVA = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 6).toString().trim());
				String strRetencion =Utilitarios.removeDecimalFormat( tableModel.getValueAt(fila, 7).toString().trim());
				String strICE = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 8).toString().trim());
				String strOtrosImpuestos = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 9).toString().trim());
				String strGrandTotal = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 10).toString().trim());
								
				if ((!strValor.equals("0")) && (!strValor.equals("0.00")) && (!strValor.equals("")) && (strValor != null))
					totalValor += Double.parseDouble(strCantidad) * Double.parseDouble(strValor);
				
				if ((!strDescuentoEspecial.equals("0")) && (!strDescuentoEspecial.equals("0.00")) && (!strDescuentoEspecial.equals("")) && (strDescuentoEspecial != null))
					descuentoEspecial = descuentoEspecial + Double.parseDouble(strDescuentoEspecial);
				if ((!strDescuentoAgencia.equals("0")) && (!strDescuentoAgencia.equals("0.00")) && (!strDescuentoAgencia.equals("")) && (strDescuentoAgencia != null))
					descuentoAgencia = descuentoAgencia + Double.parseDouble(strDescuentoAgencia);
				if ((!strDescuentosVarios.equals("0")) && (!strDescuentosVarios.equals("0.00")) && (!strDescuentosVarios.equals("")) && (strDescuentosVarios != null))
					descuentosVarios = descuentosVarios + Double.parseDouble(strDescuentosVarios);
				if ((!strIVA.equals("0")) && (!strIVA.equals("0.00")) && (!strIVA.equals("")) && (strIVA != null))
					totalIVA += Double.parseDouble(strIVA);
				if ((!strRetencion.equals("0")) && (!strRetencion.equals("0.00")) && (!strRetencion.equals("")) && (strRetencion != null))
					totalRetencion += Double.parseDouble(strRetencion);
				if ((!strICE.equals("0")) && (!strICE.equals("0.00")) && (!strICE.equals("")) && (strICE != null))
					totalICE += Double.parseDouble(strICE);
				if ((!strOtrosImpuestos.equals("0")) && (!strOtrosImpuestos.equals("0.00")) && (!strOtrosImpuestos.equals("")) && (strOtrosImpuestos != null))
					totalOtrosImpuestos += Double.parseDouble(strOtrosImpuestos);
				if ((!strGrandTotal.equals("0")) && (!strGrandTotal.equals("0.00")) && (!strGrandTotal.equals("")) && (strGrandTotal != null))
					grandTotal += Double.parseDouble(strGrandTotal);
			}
			
			getTxtValorFinal().setText(formatoDecimal.format(totalValor));
			getTxtDescuentoEspecialFinal().setText(formatoDecimal.format(descuentoEspecial));
			getTxtDescuentoAgenciaFinal().setText(formatoDecimal.format(descuentoAgencia));
			getTxtDescuentosVariosFinal().setText(formatoDecimal.format(descuentosVarios));
			getTxtIVAFinal().setText(formatoDecimal.format(totalIVA));
			getTxtRetencionFinal().setText(formatoDecimal.format(totalRetencion));
			getTxtICEFinal().setText(formatoDecimal.format(totalICE));
			getTxtOtroImpuestoFinal().setText(formatoDecimal.format(totalOtrosImpuestos));
			getTxtTotalFinal().setText(formatoDecimal.format(grandTotal));
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("¡ Ocurrió un error al actualizar los totales de la Compra !", SpiritAlert.ERROR);
		}
	}

	public void showSaveMode() {
		showFindMode();
	}

	public void showUpdateMode() {
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		getCmbTipoCompra().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().setBackground(getBackground());
		getTxtProveedor().setBackground(getBackground());
		getCmbTipoDocumento().setBackground(Parametros.saveUpdateColor);
		//MODIFIED 20 JULIO
		//getTxtOrdenCompra().setBackground(getBackground());
		getTxtOrden().setBackground(getBackground());
		getTxtPreimpreso().setBackground(Parametros.saveUpdateColor);
		getTxtReferencia().setBackground(Parametros.saveUpdateColor);
		
		setUpdateMode();
		getTxtCodigo().setEditable(false);
		getCmbFecha().setEditable(false);
		getCmbFechaVencimiento().setEditable(false);
		getCmbFechaCaducidad().setEditable(false);
		getTxtOficina().setEditable(false);
		getTxtProveedor().setEditable(false);
		//MODIFIED 20 JULIO
		//getTxtOrdenCompra().setEditable(false);
		getTxtOrden().setEditable(false);
		getBtnBuscarProveedor().setEnabled(false);
		getCmbMoneda().setEditable(false);
		getCmbEstado().setEditable(false);
		getCmbTipoDocumento().setEditable(false);
		getCmbTipoCompra().setEditable(false);
		getTxtObservacion().setEditable(false);
		getTxtReferencia().setEditable(false);
		getTxtPreimpreso().setText("");
		getTxtAutorizacion().setText("");
		getTxtAutorizacion().setEditable(false);
		//MODIFIED 20 JULIO
		//getBtnBuscarOrdenCompra().setEnabled(false);
		getBtnBuscarOrden().setEnabled(false);
		getTxtUsuario().setEditable(false);
		getBtnBuscarProducto().setEnabled(false);
		getTxtProducto().setEditable(false);
		getTxtCantidad().setEditable(false);
		getTxtValor().setEditable(false);
		getTxtRetencionFinal().setEditable(false);
		getTxtOtroImpuestoFinal().setEditable(false);
		getTxtTotalFinal().setEditable(false);
		//MODIFIED 20 JULIO
		//getTxtOrdenCompra().setEditable(false);
		getTxtOrden().setEditable(false);
		getTxtPorcentajeDescuentoAgencia().setEditable(false);
		getTxtPorcentajeDescuentoEspecial().setEditable(false);
		getTxtPorcentajeDescuentosVarios().setEditable(false);
		getTxtOtroImpuesto().setEditable(false);
		getBtnAgregarDetalle().setEnabled(false);
		getBtnActualizarDetalle().setEnabled(false);
		getBtnEliminarDetalle().setEnabled(false);
		getJtpCompras().setSelectedIndex(0);
		getCmbDocumento().setEditable(false);
		getTxtObservacion().grabFocus();
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}	
	
	public void deleteDetail() {
		
	}
}
