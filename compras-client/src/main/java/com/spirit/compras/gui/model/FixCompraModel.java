package com.spirit.compras.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
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

import com.jidesoft.docking.DockableFrame;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.MainFrameModel;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritModel;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.gui.criteria.CompraCriteria;
import com.spirit.compras.gui.panel.JPFixCompra;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.gui.model.AsientoModel;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.ServiceInstantiationException;
import com.spirit.exception.UnknownServiceException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.controller.PanelHandler;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.sri.entity.SriProveedorRetencionIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class FixCompraModel extends JPFixCompra {
	private static final long serialVersionUID = 7724251259472720472L;
	private static final String DOCUMENTO_COMPRA_LOCAL = "COML";
	private static final String NOMBRE_ESTADO_ACTIVA = "ACTIVA";
	private static final String ESTADO_ACTIVA = NOMBRE_ESTADO_ACTIVA.substring(0,1);
	private static final String NOMBRE_TIPO_COMPRA_LOCAL = "LOCAL";
	private static final String TIPO_COMPRA_LOCAL = NOMBRE_TIPO_COMPRA_LOCAL.substring(0, 1);
	private static final String NOMBRE_TIPO_COMPRA_IMPORTACION = "IMPORTACIÓN";
	private static final String OPERACION_ACTUALIZAR = "ACTUALIZAR";
	private static final String ACTUALIZAR = OPERACION_ACTUALIZAR.substring(1,2);
	protected Double IVA = Parametros.getIVA() / 100;
	private JDPopupFinderModel popupFinder;
	protected OrdenCompraIf ordenCompraIf;
	private CompraIf compra;
	private List<CompraDetalleIf> compraDetalleColeccion = new ArrayList<CompraDetalleIf>();
	private CompraDetalleIf compraDetalleForUpdate;
	private ClienteOficinaIf proveedorIf;
	private GenericoIf genericoIf;
	private ProductoIf productoIf;
	Long idProductoTemp = 0L;
	String codigoProductoTemp = "";
	private Map productosLocal = new HashMap();
	private TipoDocumentoIf tipoDocumentoIf;
	private DocumentoIf documentoIf;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private static Map panels;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no}; 
	
	public FixCompraModel() {
		panels = MainFrameModel.get_panels();
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
	
	private void iniciarComponentes(){
		getBtnActualizarDetalle().setText("");
		getBtnActualizarDetalle().setToolTipText("Actualizar detalle");
		getBtnActualizarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblCompraDetalle().getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(8).setCellRenderer(tableCellRenderer);
		
		getCmbFecha().setLocale(Utilitarios.esLocale);
		getCmbFechaVencimiento().setLocale(Utilitarios.esLocale);
		getCmbFechaCaducidad().setLocale(Utilitarios.esLocale);
		getCmbFecha().setEditable(false);
		getCmbFechaVencimiento().setEditable(false);
		getCmbFechaCaducidad().setEditable(false);
		getCmbFecha().setShowNoneButton(false);
		getCmbFechaVencimiento().setShowNoneButton(false);
		getCmbFechaCaducidad().setShowNoneButton(false);
	}
	
	private void cargarCombos() {
		// Combos estáticos
		getCmbEstado().addItem(FixCompraModel.NOMBRE_ESTADO_ACTIVA);
		getCmbTipoCompra().addItem(FixCompraModel.NOMBRE_TIPO_COMPRA_LOCAL);
		getCmbTipoCompra().addItem(FixCompraModel.NOMBRE_TIPO_COMPRA_IMPORTACION);
		// DateCombos
		getCmbFecha().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaVencimiento().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaCaducidad().setFormat(Utilitarios.setFechaUppercase());
		if (getMode() == SpiritMode.FIND_MODE) {
			getCmbFecha().setSelectedItem(null);
			getCmbFechaVencimiento().setSelectedItem(null);
			getCmbFechaCaducidad().setSelectedItem(null);
		}
		
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
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
			
			if (generico.getServicio().equals("S"))
				parameterMap.put("bienServicio", "S");
			else if (generico.getServicio().equals("N"))
				parameterMap.put("bienServicio", "B");
			
			ClienteOficinaIf proveedorOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(productoIf.getProveedorId());
			ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
			parameterMap.put("tipoPersona", proveedor.getTipoPersona());
			parameterMap.put("llevaContabilidad", proveedor.getLlevaContabilidad());
			parameterMap.put("estado", "A");
			java.sql.Date fechaCompra = new java.sql.Date(109,2,20); //La fecha debe ser 20/MAR/2009 o posterior para que los combos de retenciones se carguen con los nuevos códigos.
			Iterator<SriProveedorRetencionIf> sriProveedorRetencionIt = SessionServiceLocator.getSriProveedorRetencionSessionService().findSriProveedorRetencionByQueryAndFecha(parameterMap, fechaCompra).iterator();
			List<SriAirIf> retencionesRenta = new ArrayList<SriAirIf>();
			List<SriIvaRetencionIf> retencionesIva = new ArrayList<SriIvaRetencionIf>();
			while (sriProveedorRetencionIt.hasNext()) {
				boolean itemExiste = false;
				SriProveedorRetencionIf spr =  sriProveedorRetencionIt.next();
				parameterMap.clear();
				parameterMap.put("porcentaje",spr.getRetefuente());
				Iterator<SriAirIf> retencionesRentaIt = SessionServiceLocator.getSriAirSessionService().findSriAirByQueryAndFecha(parameterMap, fechaCompra).iterator();
				while (retencionesRentaIt.hasNext()) {
					SriAirIf sriAir =  retencionesRentaIt.next();
					itemExiste = retencionAgregada(retencionesRenta, "RENTA", sriAir.getId());
					if (!itemExiste)
						retencionesRenta.add(sriAir);
				}
				parameterMap.clear();
				parameterMap.put("porcentaje",spr.getReteiva());
				Iterator<SriIvaRetencionIf> retencionesIvaIt = SessionServiceLocator.getSriIvaRetencionSessionService().findSriIvaRetencionByQueryAndFecha(parameterMap, fechaCompra).iterator();
				itemExiste = false;
				while (retencionesIvaIt.hasNext()) {
					SriIvaRetencionIf sriIvaRetencion = retencionesIvaIt.next();
					itemExiste = retencionAgregada(retencionesIva, "IVA", sriIvaRetencion.getId());
					if (!itemExiste)
						retencionesIva.add(sriIvaRetencion);
				}
			}
			parameterMap.clear();
			parameterMap.put("porcentaje", BigDecimal.ZERO);
			Iterator<SriAirIf> retencionesRentaIt = SessionServiceLocator.getSriAirSessionService().findSriAirByQueryAndFecha(parameterMap, fechaCompra).iterator();
			while (retencionesRentaIt.hasNext()) {
				SriAirIf sriAir =  retencionesRentaIt.next();
				retencionesRenta.add(sriAir);
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
	
	private boolean retencionAgregada(List retencionesList, String tipo, Long itemId) {
		boolean agregada = false;
		Iterator it = retencionesList.iterator();
		while (it.hasNext()) {
			if (tipo.equals("RENTA")) {
				SriAirIf sriAir = (SriAirIf) it.next();
				if (sriAir.getId().compareTo(itemId) == 0) {
					agregada = true;
					break;
				}
			} else if (tipo.equals("IVA")) {
				SriIvaRetencionIf sriIvaRetencion = (SriIvaRetencionIf) it.next();
				if (sriIvaRetencion.getId().compareTo(itemId) == 0) {
					agregada = true;
					break;
				}
			}
		}
		
		return agregada;
	}
	
	private void initListeners() {
		getBtnActualizarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarCompraDetalle();
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
									if (getCmbDocumento().getItemCount() > 0){
										Map documentoMap = new HashMap();
										documentoMap.put("tipoDocumentoId",tipoDocumentoIf.getId());
										if (tipoDocumentoIf.getCodigo().equals("COM"))
											documentoMap.put("codigo", DOCUMENTO_COMPRA_LOCAL);
										if(SessionServiceLocator.getDocumentoSessionService().findDocumentoByQuery(documentoMap).size() > 0){
											DocumentoIf documentoCompraLocal = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByQuery(documentoMap).iterator().next();
											getCmbDocumento().setSelectedItem(documentoCompraLocal);
										}else{
											getCmbDocumento().setSelectedIndex(-1);
										}
										getCmbDocumento().validate();
										getCmbDocumento().repaint();
									}
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
		
		getCmbDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbDocumento().getSelectedItem() != null) {
					documentoIf = (DocumentoIf) getCmbDocumento().getSelectedItem();
				}
			}
		});
		
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
	}
	
	private void selectRow() {
		if (getTblCompraDetalle().getSelectedRow() != -1) {
			int filaSeleccionada = getTblCompraDetalle().convertRowIndexToModel(getTblCompraDetalle().getSelectedRow() );
			compraDetalleForUpdate = (CompraDetalleIf) compraDetalleColeccion.get(filaSeleccionada);
			enableCompraDetalleForUpdate(compraDetalleForUpdate);
		}
	}
	
	public void getSelectedObject(Object compraSeleccionada) {
		compra = (CompraIf) compraSeleccionada;
		cleanTableDetalle();
		
		try {
			proveedorIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(compra.getProveedorId());
			ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorIf.getClienteId());
			getTxtCodigo().setText(compra.getCodigo());
			OficinaIf oficina = SessionServiceLocator.getOficinaSessionService().getOficina(compra.getOficinaId());
			getTxtOficina().setText(oficina.getNombre());
			Calendar calendarFecha = new GregorianCalendar();
			calendarFecha.setTime(compra.getFecha());
			getCmbFecha().setCalendar(calendarFecha);
			getCmbFecha().repaint();
			Calendar calendarFechaVencimiento = new GregorianCalendar();
			calendarFechaVencimiento.setTime(compra.getFechaVencimiento());
			getCmbFechaVencimiento().setCalendar(calendarFechaVencimiento);
			getCmbFechaVencimiento().repaint();
			if (compra.getFechaCaducidad() != null) {
				Calendar calendarFechaCaducidad = new GregorianCalendar();
				calendarFechaCaducidad.setTime(compra.getFechaCaducidad());
				getCmbFechaCaducidad().setCalendar(calendarFechaCaducidad);
				getCmbFechaCaducidad().repaint();
			}
			getTxtProveedor().setText(proveedor.getIdentificacion() + " - " + proveedorIf.getDescripcion()); // " - " + proveedor.getNombreLegal() + 
			getTxtPreimpreso().setText(compra.getPreimpreso());
			getTxtAutorizacion().setText(compra.getAutorizacion());
			getCmbTipoDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoDocumento(), compra.getTipodocumentoId()));
			getCmbMoneda().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbMoneda(), compra.getMonedaId()));
			getCmbTipoSustentoTributario().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoSustentoTributario(), compra.getIdSriSustentoTributario()));
			
			if (FixCompraModel.ESTADO_ACTIVA.equals(compra.getEstado()))
				getCmbEstado().setSelectedItem(FixCompraModel.NOMBRE_ESTADO_ACTIVA);
			else
				getCmbEstado().setSelectedItem(null);
			
			if (FixCompraModel.TIPO_COMPRA_LOCAL.equals(compra.getLocalimportada()))
				getCmbTipoCompra().setSelectedItem(FixCompraModel.NOMBRE_TIPO_COMPRA_LOCAL);
			else
				getCmbTipoCompra().setSelectedItem(FixCompraModel.NOMBRE_TIPO_COMPRA_IMPORTACION);
			
			getTxtObservacion().setText(compra.getObservacion());
			getTxtReferencia().setEditable(true);
			getTxtReferencia().setText(compra.getReferencia());
			getTxtUsuario().setText(Parametros.getUsuario());
			
			DefaultTableModel tableModel = (DefaultTableModel) getTblCompraDetalle().getModel();
			compraDetalleColeccion = (List) SessionServiceLocator.getCompraDetalleSessionService().findCompraDetalleByCompraId(compra.getId());
			Iterator it = compraDetalleColeccion.iterator();
			
			while (it.hasNext()) {
				CompraDetalleIf compraDetalle = (CompraDetalleIf) it.next();
				ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(compraDetalle.getProductoId());
				productoIf = producto;
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
				double descuentoSubtotal = Double.parseDouble(compraDetalle.getDescuento().toString());
				double iva = Double.parseDouble(compraDetalle.getIva().toString());
				SriAirIf sriAir = (SriAirIf) getCmbRetencionRenta().getSelectedItem();
				SriIvaRetencionIf sriIvaRetencion = (SriIvaRetencionIf) getCmbRetencionIva().getSelectedItem();
				double porcentaje_retencion_fuente = 0D;
				double porcentaje_retencion_iva = 0D;
				if (sriAir != null)
					porcentaje_retencion_fuente = sriAir.getPorcentaje().doubleValue();
				if (sriIvaRetencion != null)
					porcentaje_retencion_iva = sriIvaRetencion.getPorcentaje().doubleValue();
				double retencion_fuente = (subtotal - descuentoSubtotal) * porcentaje_retencion_fuente / 100;
				double retencion_iva = (iva) * porcentaje_retencion_iva / 100;
				double ice = Double.parseDouble(compraDetalle.getIce().toString());
				subtotal = subtotal - descuentoSubtotal + iva + ice + otroImpuestoSubtotal;
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(producto.getGenericoId());
				PresentacionIf presentacion = null;
				if (producto.getPresentacionId() != null)
					presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(producto.getPresentacionId());
				String productoNombre = producto.getCodigo() + " - " + generico.getNombre();
				if (presentacion != null)
					productoNombre += " - " + presentacion.getNombre();
				fila.add(productoNombre);
				fila.add(String.valueOf(Double.valueOf(cantidad).intValue()));
				fila.add(formatoDecimal.format(valor));
				fila.add(formatoDecimal.format(descuentoSubtotal));
				fila.add(formatoDecimal.format(iva));
				fila.add(formatoDecimal.format(retencion_fuente + retencion_iva));
				fila.add(formatoDecimal.format(ice));
				fila.add(formatoDecimal.format(otroImpuestoSubtotal));
				fila.add(formatoDecimal.format(subtotal));
				tableModel.addRow(fila);
			}
			
			actualizarTotales();
			showUpdateMode();
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
	
	private void actualizarTotales() {
		DefaultTableModel tableModel = (DefaultTableModel) getTblCompraDetalle().getModel();
		
		double totalValor = 0.00;
		double totalDescuento = 0.00;
		double totalRetencion = 0.00;
		double totalIVA = 0.00;
		double totalICE = 0.00;
		double totalOtrosImpuestos = 0.00;
		double grandTotal = 0.00;
		
		try {
			for (int fila = 0; fila < getTblCompraDetalle().getRowCount(); fila++) {
				String strCantidad = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 1).toString().trim());
				String strValor = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 2).toString().trim());
				String strDescuento = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 3).toString().trim());
				String strIVA = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 4).toString().trim());
				String strRetencion = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 5).toString().trim());
				String strICE = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 6).toString().trim());
				String strOtrosImpuestos = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 7).toString().trim());
				String strGrandTotal = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 8).toString().trim());
				
				if ((!strValor.equals("0")) && (!strValor.equals("0.00")) && (!strValor.equals("")) && (strValor != null))
					totalValor += Double.parseDouble(strCantidad) * Double.parseDouble(strValor);
				if ((!strDescuento.equals("0")) && (!strDescuento.equals("0.00")) && (!strDescuento.equals("")) && (strDescuento != null))
					totalDescuento += Double.parseDouble(strDescuento);
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
			getTxtDescuentoFinal().setText(formatoDecimal.format(totalDescuento));
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
	
	private double calcularIVA(double subtotal, double descuentoSubtotal) {
		double iva = 0.0;
		
		if ("S".equals(genericoIf.getCobraIva()) && !documentoIf.getCodigo().equals("COMI") && !documentoIf.getCodigo().equals("CIIN"))
			iva = (subtotal - descuentoSubtotal) * IVA;
		else
			iva = 0.0;
		
		return iva;
	}
	
	private double calcularICE(double subtotal, double descuentoSubtotal) {
		double ice = 0.0;
		
		if ("S".equals(genericoIf.getCobraIce()))
			ice = (subtotal - descuentoSubtotal) * (genericoIf.getIce().doubleValue() / 100D);
		else
			ice = 0.0;
		
		return ice;
	}
	
	private void actualizarCompraDetalle() {
		DefaultTableModel tableModel = (DefaultTableModel) getTblCompraDetalle().getModel();
		try {
			if (getTblCompraDetalle().getSelectedRow() == -1) {
				SpiritAlert.createAlert("Por favor, elija la información que desea actualizar !!!", SpiritAlert.WARNING);
				
			} else {
				if ( documentoIf == null ){
					SpiritAlert.createAlert("Debe elegir un documento !!", SpiritAlert.INFORMATION);
					return;
				}
				if (validarCompraDetalle(FixCompraModel.ACTUALIZAR, documentoIf.getBonificacion())) {
					// Copio la referencia que se debe actualizar
					CompraDetalleIf data = compraDetalleForUpdate;
					String strCantidad = getTxtCantidad().getText();
					String strValor = Utilitarios.removeDecimalFormat(getTxtValor().getText());
					String strDescuento = Utilitarios.removeDecimalFormat(getTxtDescuento().getText());
					String strOtroImpuesto = Utilitarios.removeDecimalFormat(getTxtOtroImpuesto().getText());
					SriAirIf sriAir = (SriAirIf) getCmbRetencionRenta().getSelectedItem();
					double porcentaje_retencion_fuente = sriAir.getPorcentaje().doubleValue();
					SriIvaRetencionIf sriIvaRetencion = (SriIvaRetencionIf) getCmbRetencionIva().getSelectedItem();
					double porcentaje_retencion_iva = 0D;
					if (sriIvaRetencion != null)
						porcentaje_retencion_iva = sriIvaRetencion.getPorcentaje().doubleValue();
					long cantidad = Long.parseLong(strCantidad);
					double valor = Double.parseDouble(strValor);
					double descuento = Double.parseDouble(strDescuento) / 100D;
					double otroImpuesto = Double.parseDouble(strOtroImpuesto) / 100D;
					double subtotal = cantidad * valor;
					double otroImpuestoSubtotal = subtotal * otroImpuesto;
					double descuentoSubtotal = subtotal * descuento;
					double iva = calcularIVA(subtotal, descuentoSubtotal);
					double ice = calcularICE(subtotal, descuentoSubtotal);
					double retencion_fuente = (subtotal - descuentoSubtotal) * porcentaje_retencion_fuente / 100;
					double retencion_iva = (iva) * porcentaje_retencion_iva / 100;
					subtotal = subtotal - descuentoSubtotal + iva + ice + otroImpuestoSubtotal;
					data.setDocumentoId(documentoIf.getId());
					data.setProductoId(productoIf.getId());
					data.setCantidad(Long.valueOf(cantidad));
					data.setValor(BigDecimal.valueOf(Double.valueOf(valor)));
					data.setDescuento(BigDecimal.valueOf(Double.valueOf(descuentoSubtotal)));
					data.setIva(BigDecimal.valueOf(Double.valueOf(iva)));
					data.setIce(BigDecimal.valueOf(Double.valueOf(ice)));
					data.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf(otroImpuestoSubtotal)));
					data.setIdSriAir(sriAir.getId());
					if (sriIvaRetencion != null)
						data.setSriIvaRetencionId(sriIvaRetencion.getId());
					if(getTxtDescripcion().getText() != null)
						data.setDescripcion(getTxtDescripcion().getText());
					
					int filaSeleccionada = getTblCompraDetalle().convertRowIndexToModel(getTblCompraDetalle().getSelectedRow() );
					compraDetalleColeccion.set(filaSeleccionada, data);
					// actualizo informacion a la tabla visual para el usuario.
					GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
					PresentacionIf presentacion = null;
					if (productoIf.getPresentacionId() != null)
						presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
					String producto = productoIf.getCodigo() + " - " + generico.getNombre();
					if (presentacion != null)
						producto += " - " + presentacion.getNombre();
					tableModel.setValueAt(producto, filaSeleccionada, 0);
					tableModel.setValueAt(strCantidad, filaSeleccionada, 1);
					tableModel.setValueAt(formatoDecimal.format(valor), filaSeleccionada, 2);
					tableModel.setValueAt(formatoDecimal.format(descuentoSubtotal), filaSeleccionada, 3);
					tableModel.setValueAt(formatoDecimal.format(iva), filaSeleccionada, 4);
					tableModel.setValueAt(formatoDecimal.format(retencion_fuente + retencion_iva), filaSeleccionada, 5);
					tableModel.setValueAt(formatoDecimal.format(ice), filaSeleccionada, 6);
					tableModel.setValueAt(formatoDecimal.format(otroImpuestoSubtotal), filaSeleccionada, 7);
					tableModel.setValueAt(formatoDecimal.format(subtotal), filaSeleccionada, 8);
					
					if (productosLocal.get(productoIf.getId()) == null) {
						productosLocal.remove(idProductoTemp);
						productosLocal.put(productoIf.getId(), "P");
					}
				}
				
				cleanCompraDetalle();
				this.repaint();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private boolean validarCompraDetalle(String tipoOperacion, String esBonificacion) {		
		if (getCmbRetencionRenta().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el porcentaje de retencion renta!!!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(1);
			getCmbRetencionRenta().grabFocus();
			return false;
		}
		
		if (getCmbRetencionIva().isEnabled() && getCmbRetencionIva().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el porcentaje de retencion iva!!!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(1);
			getCmbRetencionIva().grabFocus();
		}
		
		return true;
	}
	
	private void enableCompraDetalleForUpdate(CompraDetalleIf compraDetalle) {
		try {
			productoIf = SessionServiceLocator.getProductoSessionService().getProducto(compraDetalle.getProductoId());
			cargarComboRetencion();
			genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
			idProductoTemp = productoIf.getId();
			codigoProductoTemp = productoIf.getCodigo();
			if(compraDetalle.getDocumentoId() != null){
				getCmbDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocumento(),compraDetalle.getDocumentoId()));
				getCmbDocumento().validate();
				getCmbDocumento().repaint();
			}
			if ("S".equals(genericoIf.getUsaLote())) {
				PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
				getTxtProducto().setText(productoIf.getCodigo() + " - " + genericoIf.getNombre()+ " - " + presentacion.getNombre());
			} else
				getTxtProducto().setText(productoIf.getCodigo() + " - " + genericoIf.getNombre());
			
			Long cantidad = compraDetalle.getCantidad();
			getTxtCantidad().setText(compraDetalle.getCantidad().toString());
			Double valor = compraDetalle.getValor().doubleValue();
			getTxtValor().setText(formatoDecimal.format(valor));
			Double descuento = (compraDetalle.getDescuento().doubleValue() * 100D / (valor * cantidad));
			getTxtDescuento().setText(String.valueOf(descuento.intValue()));
			String strOtroImpuesto = compraDetalle.getOtroImpuesto().toString();
			Double otroImpuesto = (Double.parseDouble(strOtroImpuesto) * 100D / (valor * cantidad));
			getTxtOtroImpuesto().setText(String.valueOf(otroImpuesto.intValue()));
			if (compraDetalle.getDescripcion() != null)
				getTxtDescripcion().setText(compraDetalle.getDescripcion());
			else
				getTxtDescripcion().setText("");
			
			getCmbRetencionRenta().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionRenta(),compraDetalle.getIdSriAir()));
			getCmbRetencionRenta().validate();
			getCmbRetencionRenta().repaint();
			if (compraDetalle.getSriIvaRetencionId() != null)
				getCmbRetencionIva().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionIva(),compraDetalle.getSriIvaRetencionId()));
			else
				getCmbRetencionIva().setSelectedItem(null);
			getCmbRetencionIva().validate();
			getCmbRetencionIva().repaint();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void save() {
		// TODO Auto-generated method stub
		
	}
	
	public void update() {
		if (validateFields()) {
			try {
				CompraIf compra = this.compra;
				AsientoIf asientoRetencion = SessionServiceLocator.getCompraSessionService().fixCompra(compra, compraDetalleColeccion, Parametros.getIdEmpresa(), Parametros.getIdOficina(), (UsuarioIf) Parametros.getUsuarioIf());
				if (asientoRetencion != null) {
					SpiritAlert.createAlert("Compra actualizada con éxito\nPara completar el ajuste se debe editar el asiento número: " + asientoRetencion.getNumero() + "\nPulse [OK] para proceder con la actualización del asiento respectivo", SpiritAlert.INFORMATION);
					editarAsientoContable(asientoRetencion);
				} else
					SpiritAlert.createAlert("No se ha podido actualizar la compra!!!", SpiritAlert.WARNING);
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error general al actualizar la compra",SpiritAlert.ERROR);
			}
			
			clean();
			showSaveMode();
		}
	}
	
	private void editarAsientoContable(AsientoIf asiento) {
		SpiritModel panelAsiento = (SpiritModel) new AsientoModel(asiento);
		
		if (panels.size()>0 && panels.get("Asiento")!=null){
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea cerrar la ventana Asiento?, se perderá la información que no haya sido guardada", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				MainFrameModel.get_dockingManager().removeFrame("Asiento");
			}
		}
		
		DockableFrame panel = PanelHandler.createPanelesApp(panelAsiento);
		MainFrameModel.get_dockingManager().addFrame(panel);
		MainFrameModel.get_dockingManager().showFrame(panel.getName());	
	}
	
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void refresh() {
		cargarComboTipoDocumento();
		cargarComboMonedas();
		if (productoIf != null)
			cargarComboRetencion();
	}
	
	public void duplicate() {
		
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpCompras().getSelectedIndex();
		int tabCount = this.getJtpCompras().getTabCount();
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpCompras().setSelectedIndex(selectedTab);
	}
	
	public void updateDetail() {
		if (getJtpCompras().getSelectedIndex() == 1)
			actualizarCompraDetalle();
	}
	
	public void deleteDetail() {
		
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if ( getTxtPreimpreso().getText()!=null && !"".equals(getTxtPreimpreso().getText()))
			aMap.put("preimpreso", getTxtPreimpreso().getText().trim() );
		else
			aMap.put("preimpreso", "%");
		
		return aMap;
	}
	
	public void find() {
		try {
			Map mapa = buildQuery();
			int tamanoLista = SessionServiceLocator.getCompraSessionService().findCompraByQueryListSize(mapa, Parametros.getIdEmpresa());
			if (tamanoLista > 0) {
				CompraCriteria compraCriteria = new CompraCriteria(true);
				compraCriteria.setResultListSize(tamanoLista);
				compraCriteria.setQueryBuilded(mapa);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), compraCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
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
	
	public boolean isEmpty() {
		return false;
	}
	
	public void clean() {
		compraDetalleColeccion = null;
		compraDetalleColeccion = new ArrayList<CompraDetalleIf>();
		cleanTableDetalle();
	}
	
	public void cleanTableDetalle() {
		DefaultTableModel model = (DefaultTableModel) this.getTblCompraDetalle().getModel();
		for (int i = this.getTblCompraDetalle().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
		
		compraDetalleColeccion.clear();
		cleanCompraDetalle();
		this.repaint();
	}
	
	private void cleanCompraDetalle() {
		getCmbDocumento().setSelectedItem(null);
		getTxtProducto().setText("");
		getTxtDescripcion().setText("");
		getTxtValor().setText("");
		getTxtOtroImpuesto().setText("");
		getTxtCantidad().setText("");
		getTxtDescuento().setText("");
		getCmbRetencionRenta().setSelectedItem(null);
		getCmbRetencionIva().setSelectedItem(null);
	}
	
	public void showFindMode() {
		clean();
		getTxtPreimpreso().setBackground(Parametros.findColor);
		proveedorIf = null;
		tipoDocumentoIf = null;
		
		/* CAMPOS HEADER */
		getTxtCodigo().setText("");
		getTxtCodigo().setEditable(false);
		getTxtOficina().setText("");
		getTxtOficina().setEditable(false);
		getTxtProveedor().setText("");
		getTxtProveedor().setEditable(false);
		getTxtOrdenCompra().setText("");
		getTxtOrdenCompra().setEditable(false);
		getTxtReferencia().setText("");
		getTxtReferencia().setEditable(false);
		getCmbTipoDocumento().setSelectedItem(null);
		getCmbTipoDocumento().setEnabled(false);
		getCmbEstado().setSelectedItem(null);
		getCmbEstado().setEnabled(false);
		getTxtObservacion().setText("");
		getTxtObservacion().setEditable(false);
		getCmbFecha().setSelectedItem(null);
		getCmbFecha().setEnabled(false);
		getCmbFechaVencimiento().setSelectedItem(null);
		getCmbFechaVencimiento().setEnabled(false);
		getCmbFechaCaducidad().setSelectedItem(null);
		getCmbFechaCaducidad().setEnabled(false);
		getCmbMoneda().setSelectedItem(null);
		getCmbMoneda().setEnabled(false);
		getCmbTipoCompra().setSelectedItem(null);
		getCmbTipoCompra().setEnabled(false);		
		getCmbTipoSustentoTributario().setSelectedItem(null);
		getCmbTipoSustentoTributario().setEnabled(false);
		getTxtPreimpreso().setText("");
		getTxtPreimpreso().setEditable(true);
		getTxtAutorizacion().setText("");
		getTxtAutorizacion().setEditable(false);
		getTxtUsuario().setText("");
		getTxtUsuario().setEditable(false);
		
		/* CAMPOS DETAIL */
		getCmbDocumento().setSelectedItem(null);
		getCmbDocumento().setEnabled(false);
		getTxtProducto().setText("");
		getTxtProducto().setEditable(false);
		getTxtDescripcion().setText("");
		getTxtDescripcion().setEditable(false);
		getTxtValor().setText("");
		getTxtValor().setEditable(false);
		getTxtOtroImpuesto().setText("");
		getTxtOtroImpuesto().setEditable(false);
		getTxtCantidad().setText("");
		getTxtCantidad().setEditable(false);
		getTxtDescuento().setText("");
		getTxtDescuento().setEditable(false);
		getCmbRetencionRenta().setSelectedItem(null);
		getCmbRetencionRenta().setEnabled(false);
		getCmbRetencionIva().setSelectedItem(null);
		getCmbRetencionIva().setEnabled(false);
		getBtnActualizarDetalle().setEnabled(false);
		getTxtValorFinal().setText("");
		getTxtValorFinal().setEditable(false);
		getTxtIVAFinal().setText("");
		getTxtIVAFinal().setEditable(false);
		getTxtICEFinal().setText("");
		getTxtICEFinal().setEditable(false);
		getTxtDescuentoFinal().setText("");
		getTxtDescuentoFinal().setEditable(false);
		getTxtRetencionFinal().setText("");
		getTxtRetencionFinal().setEditable(false);
		getTxtOtroImpuestoFinal().setText("");
		getTxtOtroImpuestoFinal().setEditable(false);
		getTxtTotalFinal().setText("");
		getTxtTotalFinal().setEditable(false);
		getTxtPreimpreso().grabFocus();
		setFindMode();
	}
	
	public void showSaveMode() {
		showFindMode();
	}
	
	public void showUpdateMode() {
		/*getTxtPreimpreso().setBackground(getBackground());
		 setUpdateMode();
		 getTxtCodigo().setEditable(false);
		 getCmbFecha().setEnabled(false);
		 getCmbFechaVencimiento().setEnabled(false);
		 getCmbFechaCaducidad().setEnabled(false);
		 getTxtOficina().setEnabled(false);
		 getTxtProveedor().setEditable(false);
		 getCmbMoneda().setEnabled(false);
		 getCmbEstado().setEnabled(false);
		 getCmbTipoDocumento().setEnabled(false);
		 getCmbTipoCompra().setEnabled(false);
		 getTxtObservacion().setEnabled(false);
		 getTxtPreimpreso().setEnabled(false);
		 getTxtAutorizacion().setEnabled(false);
		 getTxtReferencia().setEditable(false);
		 getTxtUsuario().setEnabled(false);
		 getTxtProducto().setEditable(false);
		 getTxtCantidad().setEnabled(false);
		 getTxtValor().setEnabled(false);
		 getTxtDescuento().setEnabled(false);
		 getTxtOtroImpuesto().setEnabled(false);
		 getBtnActualizarDetalle().setEnabled(true);
		 getJtpCompras().setSelectedIndex(0);
		 getTxtObservacion().grabFocus();
		 getTxtOrdenCompra().setEditable(false);
		 getCmbTipoSustentoTributario().setEnabled(false);
		 getCmbDocumento().setEnabled(false);
		 getTxtDescripcion().setEditable(false);
		 getTxtValor().setEnabled(false);
		 getTxtValor().setEditable(false);
		 getTxtCantidad().setEnabled(false);
		 getTxtCantidad().setEditable(false);
		 getTxtOtroImpuesto().setEnabled(false);
		 getTxtOtroImpuesto().setEditable(false);
		 getTxtDescuento().setEnabled(false);
		 getTxtDescuento().setEditable(false);
		 getCmbRetencionRenta().setEnabled(true);
		 getCmbRetencionIva().setEnabled(true);
		 getTxtValorFinal().setEditable(false);
		 getTxtDescuentoFinal().setEditable(false);
		 getTxtIVAFinal().setEditable(false);
		 getTxtRetencionFinal().setEditable(false);
		 getTxtICEFinal().setEditable(false);
		 getTxtOtroImpuestoFinal().setEditable(false);
		 getTxtTotalFinal().setEditable(false);*/
		
		getTxtPreimpreso().setBackground(getBackground());
		
		/* CAMPOS HEADER */
		getTxtCodigo().setEditable(false);
		getTxtOficina().setEditable(false);
		getTxtProveedor().setEditable(false);
		getTxtOrdenCompra().setEditable(false);
		getTxtReferencia().setEditable(false);
		getCmbTipoDocumento().setEnabled(false);
		getCmbEstado().setEnabled(false);
		getTxtObservacion().setEditable(false);
		getCmbFecha().setEnabled(false);
		getCmbFechaVencimiento().setEnabled(false);
		getCmbFechaCaducidad().setEnabled(false);
		getCmbMoneda().setEnabled(false);
		getCmbTipoCompra().setEnabled(false);		
		getCmbTipoSustentoTributario().setEnabled(false);
		getTxtPreimpreso().setEditable(false);
		getTxtAutorizacion().setEditable(false);
		getTxtUsuario().setEditable(false);
		
		/* CAMPOS DETAIL */
		getCmbDocumento().setEnabled(false);
		getTxtProducto().setEditable(false);
		getTxtDescripcion().setEditable(false);
		getTxtValor().setEditable(false);
		getTxtOtroImpuesto().setEditable(false);
		getTxtCantidad().setEditable(false);
		getTxtDescuento().setEditable(false);
		getCmbRetencionRenta().setSelectedItem(null);
		getCmbRetencionRenta().setEnabled(true);
		getCmbRetencionIva().setSelectedItem(null);
		getCmbRetencionIva().setEnabled(true);
		getBtnActualizarDetalle().setEnabled(true);
		getTxtValorFinal().setEditable(false);
		getTxtIVAFinal().setEditable(false);
		getTxtICEFinal().setEditable(false);
		getTxtDescuentoFinal().setEditable(false);
		getTxtRetencionFinal().setEditable(false);
		getTxtOtroImpuestoFinal().setEditable(false);
		getTxtTotalFinal().setEditable(false);
		setUpdateMode();
	}
	
	public boolean validateFields() {
		for(CompraDetalleIf compraDetalle : compraDetalleColeccion){
			if(compraDetalle.getDocumentoId() == null){
				SpiritAlert.createAlert("Uno o más detalles de la compra no tienen Documento asociado !", SpiritAlert.WARNING);
				getJtpCompras().setSelectedIndex(1);
				getCmbDocumento().grabFocus();
				return false;
			}
		}
		
		for (int i=0; i<compraDetalleColeccion.size(); i++) {
			try {
				CompraDetalleIf compraDetalle = (CompraDetalleIf) compraDetalleColeccion.get(i);
				DocumentoIf documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(compraDetalle.getDocumentoId());
				if (documento.getTipoDocumentoId().compareTo(tipoDocumentoIf.getId()) != 0) {
					SpiritAlert.createAlert("Documento no corresponde con tipo de documento seleccionado !!", SpiritAlert.WARNING);
					this.getJtpCompras().setSelectedIndex(1);
					this.getTblCompraDetalle().getSelectionModel().setSelectionInterval(i,i);
					return false;
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
			}
		}
		
		return true;
	}
}