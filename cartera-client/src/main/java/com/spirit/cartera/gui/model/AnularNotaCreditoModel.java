package com.spirit.cartera.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.spirit.cartera.entity.NotaCreditoDetalleIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.cartera.gui.criteria.AnularNotaCreditoCriteria;
import com.spirit.cartera.gui.panel.JPAnularNotaCredito;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.ServiceInstantiationException;
import com.spirit.exception.UnknownServiceException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class AnularNotaCreditoModel extends JPAnularNotaCredito {
	
	private static final long serialVersionUID = -8459413652603920876L;
	
	private static final String PRODUCTO = "P";
	private static final String NOMBRE_ESTADO_INACTIVA = "INACTIVA";
	private static final String ESTADO_INACTIVA = NOMBRE_ESTADO_INACTIVA.substring(0, 1);
	private static final String NOMBRE_ESTADO_ACTIVA = "ACTIVA";
	private static final String NOMBRE_ESTADO_ANULADO = "ANULADO";
	private static final String ESTADO_ANULADO = NOMBRE_ESTADO_ANULADO.substring(1,2);
	private static final int MAX_LONGITUD_CODIGO = 10;
	private static final int MAX_LONGITUD_OBSERVACION = 100;
	private static final int MAX_LONGITUD_REFERENCIA = 20;
	private static final int MAX_LONGITUD_PREIMPRESO = 15;
	private static final int MAX_LONGITUD_AUTORIZACION = 10;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	protected Double IVA = Parametros.getIVA() / 100;
	private JDPopupFinderModel popupFinder;
	ArrayList lista;
	private Vector<NotaCreditoDetalleIf> notaCreditoDetalleColeccion = new Vector<NotaCreditoDetalleIf>();
	private NotaCreditoDetalleIf notaCreditoDetalleForUpdate;
	private ClienteOficinaIf operadorNegocio;
	private ProductoIf productoIf;
	private GenericoIf genericoIf;
		private NotaCreditoIf notaCredito;
	java.sql.Date fechaCreacionCartera;
	Long idProductoTemp = 0L;
	String codigoProductoTemp = "";
	String strTipoReferencia = "";
	private static final int MAX_LONGITUD_CANTIDAD = 8;
	private static final int MAX_LONGITUD_VALOR = 22;
	private static final int MAX_LONGITUD_OTRO_IMPUESTO = 3;
	private static final int MAX_LONGITUD_DESCRIPCION = 300;
	protected JMenuItem menuItem;
	protected JPopupMenu popup = new JPopupMenu();
	private static final String TIPO_CARTERA_CLIENTE = "C";
	private static final String TIPO_CARTERA_PROVEEDOR = "P";
	String tipoCartera = TIPO_CARTERA_CLIENTE;
	private String si = "Si";
	private String no = "No";
	private Object[] options = {si,no};
	
	public AnularNotaCreditoModel() {
		iniciarComponentes();
		initKeyListeners();
		setSorterTable(getTblNotaCreditoDetalle());
		initListeners();
		cargarCombos();
		setSorterTable(getTblNotaCreditoDetalle());
		getTblNotaCreditoDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.clean();
		this.showSaveMode();
		new HotKeyComponent(this);
	}
		
	private void iniciarComponentes(){
		getBtnBuscarOperadorNegocio().setToolTipText("Buscar operador de negocio");
		getBtnBuscarOperadorNegocio().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblNotaCreditoDetalle().getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
		getTblNotaCreditoDetalle().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		getTblNotaCreditoDetalle().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		getTblNotaCreditoDetalle().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		getTblNotaCreditoDetalle().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblNotaCreditoDetalle().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
	}
	
	private void initKeyListeners() {
		getTxtObservacion().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACION));
		getTxtReferencia().addKeyListener(new TextChecker(MAX_LONGITUD_REFERENCIA));
		getTxtPreimpreso().addKeyListener(new TextChecker(MAX_LONGITUD_PREIMPRESO));
		getTxtAutorizacion().addKeyListener(new TextChecker(MAX_LONGITUD_AUTORIZACION));
		getTxtAutorizacion().addKeyListener(new NumberTextField());
		getTxtCantidad().addKeyListener(new TextChecker(MAX_LONGITUD_CANTIDAD));
		getTxtCantidad().addKeyListener(new NumberTextField());
		getTxtValor().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtValor().addKeyListener(new NumberTextFieldDecimal());
		getTxtOtroImpuesto().addKeyListener(new TextChecker(MAX_LONGITUD_OTRO_IMPUESTO));
		getTxtOtroImpuesto().addKeyListener(new NumberTextField());
		getTxtDescripcion().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION, true));
		getCmbFechaEmision().setLocale(Utilitarios.esLocale);
		getCmbFechaVencimiento().setLocale(Utilitarios.esLocale);
		getCmbFechaCaducidad().setLocale(Utilitarios.esLocale);
		getCmbFechaEmision().setEditable(false);
		getCmbFechaVencimiento().setEditable(false);
		getCmbFechaCaducidad().setEditable(false);
		getCmbFechaEmision().setShowNoneButton(false);
		getCmbFechaVencimiento().setShowNoneButton(false);
		getCmbFechaCaducidad().setShowNoneButton(false);
	}
		
	private void cargarCombos() {
		// DateCombos
		getCmbFechaEmision().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaVencimiento().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaCaducidad().setFormat(Utilitarios.setFechaUppercase());
		if (getMode() == SpiritMode.FIND_MODE) {
			getCmbFechaEmision().setSelectedItem(null);
			getCmbFechaVencimiento().setSelectedItem(null);
			getCmbFechaCaducidad().setSelectedItem(null);
		}
	}
		
	private void initListeners() {
		getBtnBuscarOperadorNegocio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarOperadorNegocio();
			}
		});
		
		getTblNotaCreditoDetalle().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				selectRow();
			}
		});
		
		getTblNotaCreditoDetalle().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				selectRow();
			}
		});
				
	}
	
	KeyListener oKeyListenerTxtCodigo = new TextChecker(MAX_LONGITUD_CODIGO);
		
	private void selectRow() {
		if (getTblNotaCreditoDetalle().getSelectedRow() != -1) {
			int filaSeleccionada = getTblNotaCreditoDetalle().convertRowIndexToModel(getTblNotaCreditoDetalle().getSelectedRow() );
			notaCreditoDetalleForUpdate = (NotaCreditoDetalleIf) notaCreditoDetalleColeccion.get(filaSeleccionada);
			enableNotaCreditoDetalleForUpdate(notaCreditoDetalleForUpdate);
		}
	}
				
	public void getSelectedObject() {
		this.showUpdateMode();
		cleanTableDetalle();
		
		try {
			operadorNegocio = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(notaCredito.getOperadorNegocioId());
			ClienteIf operador = SessionServiceLocator.getClienteSessionService().getCliente(operadorNegocio.getClienteId());
				
			getTxtCodigo().setText(notaCredito.getCodigo());
			Calendar calendarFecha = new GregorianCalendar();
			calendarFecha.setTime(notaCredito.getFechaEmision());
			getCmbFechaEmision().setCalendar(calendarFecha);
			getCmbFechaEmision().repaint();
			Calendar calendarFechaVencimiento = new GregorianCalendar();
			calendarFechaVencimiento.setTime(notaCredito.getFechaVencimiento());
			getCmbFechaVencimiento().setCalendar(calendarFechaVencimiento);
			getCmbFechaVencimiento().repaint();
			Calendar calendarFechaCaducidad = new GregorianCalendar();
			calendarFechaCaducidad.setTime(notaCredito.getFechaCaducidad());
			getCmbFechaCaducidad().setCalendar(calendarFechaCaducidad);
			getCmbFechaCaducidad().repaint();
			getTxtOperadorNegocio().setText(operador.getIdentificacion() + " - " + operadorNegocio.getDescripcion()); 
			getTxtPreimpreso().setText(notaCredito.getPreimpreso());
			getTxtAutorizacion().setText(notaCredito.getAutorizacion());
			MonedaIf moneda = SessionServiceLocator.getMonedaSessionService().getMoneda(notaCredito.getMonedaId());
			getTxtMoneda().setText(moneda.getNombre());
			
			if (ESTADO_INACTIVA.equals(notaCredito.getEstado()))
				getTxtEstado().setText(NOMBRE_ESTADO_INACTIVA);
			else
				getTxtEstado().setText(NOMBRE_ESTADO_ACTIVA);
						
			getTxtObservacion().setText(notaCredito.getObservacion());
			
			getTxtReferencia().setText(notaCredito.getReferencia());
			DefaultTableModel tableModel = (DefaultTableModel) getTblNotaCreditoDetalle().getModel();
			Iterator it = SessionServiceLocator.getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByNotaCreditoId(notaCredito.getId()).iterator();
			
			while (it.hasNext()) {
				NotaCreditoDetalleIf notaCreditoDetalle = (NotaCreditoDetalleIf) it.next();
				notaCreditoDetalleColeccion.add(notaCreditoDetalle);
				ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(notaCreditoDetalle.getProductoId());
				productoIf = producto;
				Vector<String> fila = new Vector<String>();
				double cantidad = Double.parseDouble(notaCreditoDetalle.getCantidad().toString());
				double valor = Double.parseDouble(notaCreditoDetalle.getValor().toString());
				double subtotal = cantidad * valor;
				double otroImpuestoSubtotal = Double.parseDouble(notaCreditoDetalle.getOtroImpuesto().toString());
				double iva = Double.parseDouble(notaCreditoDetalle.getIva().toString());
				double ice = Double.parseDouble(notaCreditoDetalle.getIce().toString());
				subtotal = subtotal + iva + ice + otroImpuestoSubtotal;
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(producto.getGenericoId());
				PresentacionIf presentacion = null;
				if (producto.getPresentacionId() != null)
					presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(producto.getPresentacionId());
				String productoNombre = producto.getCodigo() + " - " + generico.getNombre();
				if (presentacion != null)
					productoNombre += " - " + presentacion.getNombre();
				fila.add(productoNombre);
				fila.add(String.valueOf(Double.valueOf(cantidad).intValue()));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(valor)));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(iva)));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(ice)));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(otroImpuestoSubtotal)));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(subtotal)));
				tableModel.addRow(fila);
			}
			
			actualizarTotales();	
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
	
	private void buscarOperadorNegocio() {
		Long idCorporacion = 0L;
		Long idCliente = 0L;
		String tipoCliente = "PR";
		String tituloVentanaBusqueda = "Proveedores";
		if (tipoCartera.equals(TIPO_CARTERA_CLIENTE)) {
			tipoCliente = "CL";
			tituloVentanaBusqueda = "Clientes";
		}
		ClienteOficinaCriteria clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente,"", false);
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.addElement(80);
		anchoColumnas.addElement(500);
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
		if (popupFinder.getElementoSeleccionado() != null)
			setOperadorNegocio();
	}
		
	private void setOperadorNegocio() {
		operadorNegocio = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
		try {
			ClienteIf operador = SessionServiceLocator.getClienteSessionService().getCliente(operadorNegocio.getClienteId());
			if(operador != null){
				getTxtOperadorNegocio().setText(operador.getIdentificacion() + " - " + operadorNegocio.getDescripcion()); // " - " + proveedor.getNombreLegal() + 
			} else
				SpiritAlert.createAlert("No existe el Proveedor", SpiritAlert.ERROR);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error en consulta de Proveedor", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		if (operadorNegocio != null) {
			getTxtReferencia().setEditable(true);
			getTxtReferencia().setText("");
			getTxtProducto().setText("");
			cleanTableDetalle();
		}
	}
	
	private double calcularIVA(double subtotal) {
		double iva = 0.0;
		
		if ("S".equals(genericoIf.getCobraIva()))
			iva = subtotal * IVA;
		else
			iva = 0.0;
		
		return iva;
	}
	
	private double calcularICE(double subtotal) {
		double ice = 0.0;
		
		if ("S".equals(genericoIf.getCobraIce()))
			ice = (subtotal) * (genericoIf.getIce().doubleValue() / 100D);
		else
			ice = 0.0;
		
		return ice;
	}
	
	private void enableNotaCreditoDetalleForUpdate(NotaCreditoDetalleIf notaCreditoDetalle) {
		try {
			productoIf = SessionServiceLocator.getProductoSessionService().getProducto(notaCreditoDetalle.getProductoId());
			genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
			idProductoTemp = productoIf.getId();
			codigoProductoTemp = productoIf.getCodigo();
			if(notaCreditoDetalle.getDocumentoId() != null){
				DocumentoIf documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(notaCreditoDetalle.getDocumentoId());
				getTxtDocumento().setText(documento.getNombre());
			}
			if ("S".equals(genericoIf.getUsaLote())) {
				PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
				getTxtProducto().setText(productoIf.getCodigo() + " - " + genericoIf.getNombre()+ " - " + presentacion.getNombre());
			} else
				getTxtProducto().setText(productoIf.getCodigo() + " - " + genericoIf.getNombre());
			
			double cantidad = notaCreditoDetalle.getCantidad().doubleValue();
			getTxtCantidad().setText(notaCreditoDetalle.getCantidad().toString());
			double valor = notaCreditoDetalle.getValor().doubleValue();
			getTxtValor().setText(formatoDecimal.format(Utilitarios.redondeoValor(valor)));
			String strOtroImpuesto = notaCreditoDetalle.getOtroImpuesto().toString();
			double otroImpuesto = (Double.parseDouble(strOtroImpuesto) * 100D / (valor * cantidad));
			getTxtOtroImpuesto().setText(String.valueOf(otroImpuesto));
			if(notaCreditoDetalle.getDescripcion() != null)
				getTxtDescripcion().setText(notaCreditoDetalle.getDescripcion());
			else
				getTxtDescripcion().setText("");
			
			getTxtCantidad().setEnabled(true);
			getTxtCantidad().setEditable(true);
			getTxtValor().setEnabled(true);
			getTxtValor().setEditable(true);
			getTxtOtroImpuesto().setEnabled(true);
			getTxtOtroImpuesto().setEditable(true);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cleanNotaCreditoDetalle() {
		getTxtProducto().setText("");
		getTxtCantidad().setText("");
		getTxtCantidad().setEnabled(false);
		getTxtCantidad().setEditable(false);
		getTxtValor().setText("");
		getTxtValor().setEnabled(false);
		getTxtValor().setEditable(false);
		getTxtOtroImpuesto().setText("");
		getTxtDescripcion().setText("");
		getTxtOtroImpuesto().setEnabled(false);
		getTxtOtroImpuesto().setEditable(false);
	}
	
	private void actualizarTotales() {
		DefaultTableModel tableModel = (DefaultTableModel) getTblNotaCreditoDetalle().getModel();
		
		double totalValor = 0.00;
		double totalIVA = 0.00;
		double totalICE = 0.00;
		double totalOtrosImpuestos = 0.00;
		double grandTotal = 0.00;
		
		try {
			for (int fila = 0; fila < getTblNotaCreditoDetalle().getRowCount(); fila++) {
				String strCantidad = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 1).toString().trim());
				String strValor = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 2).toString().trim());
				String strIVA = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 3).toString().trim());
				String strICE = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 4).toString().trim());
				String strOtrosImpuestos = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 5).toString().trim());
				String strGrandTotal = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 6).toString().trim());
				
				if ((!strValor.equals("0")) && (!strValor.equals("0.00")) && (!strValor.equals("")) && (strValor != null))
					totalValor += Double.parseDouble(strCantidad) * Double.parseDouble(strValor);
				if ((!strIVA.equals("0")) && (!strIVA.equals("0.00")) && (!strIVA.equals("")) && (strIVA != null))
					//totalIVA += Double.parseDouble(strIVA);
					totalIVA = totalValor * IVA;
				if ((!strICE.equals("0")) && (!strICE.equals("0.00")) && (!strICE.equals("")) && (strICE != null))
					totalICE += Double.parseDouble(strICE);
				if ((!strOtrosImpuestos.equals("0")) && (!strOtrosImpuestos.equals("0.00")) && (!strOtrosImpuestos.equals("")) && (strOtrosImpuestos != null))
					totalOtrosImpuestos += Double.parseDouble(strOtrosImpuestos);
				if ((!strGrandTotal.equals("0")) && (!strGrandTotal.equals("0.00")) && (!strGrandTotal.equals("")) && (strGrandTotal != null))
					grandTotal = totalValor + totalIVA + totalICE + totalOtrosImpuestos;
			}
			
			getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalValor)));
			getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalIVA)));
			getTxtICEFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalICE)));
			getTxtOtroImpuestoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalOtrosImpuestos)));
			getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(grandTotal)));
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("¡ Ocurrió un error al actualizar los totales de la Nota de Crédito !", SpiritAlert.ERROR);
		}
	}
	
	public void cleanTableDetalle() {
		DefaultTableModel model = (DefaultTableModel) this.getTblNotaCreditoDetalle().getModel();
		for (int i = this.getTblNotaCreditoDetalle().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
		
		notaCreditoDetalleColeccion.clear();
		actualizarTotales();
		cleanNotaCreditoDetalle();
		this.repaint();
	}
	
	private void cleanForDuplicate() {
		getTxtPreimpreso().setText("");
		getTxtReferencia().setText("");
		getTxtDocumento().setText("");
		getTxtProducto().setText("");
		getTxtDescripcion().setText("");
		getTxtCantidad().setText("");
		getTxtValor().setText("");
	}
		
	public void save() {
		SpiritAlert.createAlert("No se puede guardar la nota de crédito!", SpiritAlert.WARNING);
	}
	
	public void update() {		
		String mensaje = "¿Está seguro que desea anular la Nota de Crédito?";
		int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
		if(opcion == JOptionPane.YES_OPTION) {
			String preimpreso = this.getTxtPreimpreso().getText();
			
			if (!ESTADO_ANULADO.equals(notaCredito.getEstado())) {
				String usuario = Parametros.getUsuario();
				try {
					anularNotaCredito(preimpreso, usuario);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				}
			}
		}
	}
	
	private void anularNotaCredito(String preimpreso, String usuario) throws GenericBusinessException {
		realizarAnulacion(usuario);
	}
	
	private void realizarAnulacion(String usuario) throws GenericBusinessException {
		notaCredito.setEstado(ESTADO_ANULADO);
		Map aMap = new HashMap();
		aMap.put("tipoDocumentoId", notaCredito.getTipoDocumentoId());
		aMap.put("transaccionId", notaCredito.getId());
		aMap.put("empresaId", Parametros.getIdEmpresa());
		SessionServiceLocator.getCarteraSessionService().procesarAnularNotaCredito(notaCredito, notaCreditoDetalleColeccion, aMap, usuario);
		SpiritAlert.createAlert("Nota de cr\u00e9dito anulada con \u00e9xito", SpiritAlert.INFORMATION);
		this.clean();
		this.showSaveMode();
	}
	
	public void delete() {
		SpiritAlert.createAlert("No se puede borrar la nota de crédito!", SpiritAlert.WARNING);
	}
	
	public void report() {

	}
	
	public void refresh() {

	}
	
	public void duplicate() {
	
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpAnularNotaCredito().getSelectedIndex();
		int tabCount = this.getJtpAnularNotaCredito().getTabCount();
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpAnularNotaCredito().setSelectedIndex(selectedTab);
	}
	
	public void addDetail() {

	}
	
	public void updateDetail() {

	}
	
	public void deleteDetail() {
		
	}

	private Map buildQuery() {
		Map aMap = new HashMap();
		Long operadorNegocioId = 0L;
		
		aMap.put("tipoCartera", tipoCartera);
		
		if (getTxtCodigo().getText().equals("") == false)
			aMap.put("codigo", getTxtCodigo().getText() + "%");
		else
			aMap.put("codigo", "%");
		
		if (operadorNegocio != null) {
			operadorNegocioId = operadorNegocio.getId();
			aMap.put("operadorNegocioId", operadorNegocioId);
		}
				
		if ( getTxtPreimpreso().getText()!=null && !"".equals(getTxtPreimpreso().getText()))
			aMap.put("preimpreso", getTxtPreimpreso().getText().trim() );
		
		return aMap;
	}
	
	public void find() {
		try {
			Map mapa = buildQuery();
			int tamanoLista = SessionServiceLocator.getNotaCreditoSessionService().getNotaCreditoNoAnuladaListSize(mapa, Parametros.getIdEmpresa());
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.addElement(90);
			anchoColumnas.addElement(70);
			anchoColumnas.addElement(200);
			anchoColumnas.addElement(280);
			anchoColumnas.addElement(90);
			Map alineacionColumnas = new HashMap();
			alineacionColumnas.put(0, SwingConstants.CENTER);
			alineacionColumnas.put(1, SwingConstants.CENTER);
			alineacionColumnas.put(4, SwingConstants.RIGHT);
			if (tamanoLista > 0) {
				AnularNotaCreditoCriteria anularNotaCreditoCriteria = new AnularNotaCreditoCriteria();
				anularNotaCreditoCriteria.setResultListSize(tamanoLista);
				anularNotaCreditoCriteria.setQueryBuilded(mapa);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), anularNotaCreditoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, alineacionColumnas);
				if ( popupFinder.getElementoSeleccionado() != null ){
					notaCredito = (NotaCreditoIf) popupFinder.getElementoSeleccionado();
					if(notaCredito != null){
						getSelectedObject();
					}
				}
				popupFinder = null;
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
				if (getMode()==SpiritMode.FIND_MODE)
					this.getCmbFechaEmision().setSelectedItem(null);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		}
	}

	
	public boolean isEmpty() {
		return false;
	}
	
	public void clean() {
		notaCreditoDetalleColeccion = null;
		notaCreditoDetalleColeccion = new Vector<NotaCreditoDetalleIf>();
	}
	
	public void showFindMode() {
		setFindMode();
		clean();
		cleanTableDetalle();
		getTxtCodigo().setBackground(Parametros.findColor);
		getTxtOperadorNegocio().setBackground(Parametros.findColor);
		getTxtCodigo().addKeyListener(oKeyListenerTxtCodigo);
		getTxtPreimpreso().setBackground(Parametros.findColor);
		
		operadorNegocio = null;
		getTxtCodigo().setText("");
		getTxtCodigo().setEditable(true);
		getCmbFechaEmision().setSelectedItem(null);
		getCmbFechaEmision().setEnabled(false);
		getCmbFechaVencimiento().setSelectedItem(null);
		getCmbFechaVencimiento().setEnabled(false);
		getCmbFechaCaducidad().setSelectedItem(null);
		getCmbFechaCaducidad().setEnabled(false);
		getTxtOficina().setEnabled(false);
		getTxtOperadorNegocio().setText("");
		getTxtOperadorNegocio().setEditable(false);
		getBtnBuscarOperadorNegocio().setEnabled(true);
		getTxtMoneda().setText("");
		getTxtMoneda().setEditable(false);
		getTxtEstado().setText("");
		getTxtEstado().setEditable(false);
		getTxtObservacion().setText("");
		getTxtObservacion().setEnabled(false);
		getTxtPreimpreso().setText("");
		getTxtPreimpreso().setEnabled(true);
		getTxtAutorizacion().setText("");
		getTxtAutorizacion().setEnabled(false);
		getTxtReferencia().setEditable(false);
		getTxtUsuario().setEnabled(false);
		getTxtProducto().setEditable(false);
		getTxtCantidad().setEnabled(false);
		getTxtValor().setEnabled(false);
		getTxtOtroImpuesto().setEnabled(false);
		getTxtReferencia().setText("");
		getTxtCodigo().grabFocus();
	}
	
	public void showSaveMode() {
		showFindMode();
	}
	
	public void showUpdateMode() {
		getTxtCodigo().setBackground(getBackground());
		getTxtOperadorNegocio().setBackground(getBackground());
		getTxtPreimpreso().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().removeKeyListener(oKeyListenerTxtCodigo);
		setUpdateMode();
		getTxtCodigo().setEditable(false);
		getCmbFechaEmision().setEnabled(false);
		getCmbFechaVencimiento().setEnabled(false);
		getCmbFechaCaducidad().setEnabled(false);
		getTxtOficina().setEditable(false);
		getTxtOperadorNegocio().setEditable(false);
		getBtnBuscarOperadorNegocio().setEnabled(false);
		getTxtMoneda().setEditable(false);
		getTxtEstado().setEditable(false);
		getTxtObservacion().setEditable(false);
		getTxtPreimpreso().setEditable(false);
		getTxtAutorizacion().setEditable(false);
		getTxtReferencia().setEditable(false);
		getTxtUsuario().setEditable(false);
		getTxtProducto().setEditable(false);
		getTxtCantidad().setEditable(false);
		getTxtValor().setEditable(false);
		getTxtOtroImpuesto().setEditable(false);
		getTxtValorFinal().setEditable(false);
		getTxtIVAFinal().setEditable(false);
		getTxtICEFinal().setEditable(false);
		getTxtOtroImpuestoFinal().setEditable(false);
		getTxtTotalFinal().setEditable(false);
	}
	
	public boolean validateFields() {
		return true;
	}
}