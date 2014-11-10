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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.cartera.entity.FormaPagoIf;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
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
import com.spirit.facturacion.gui.criteria.AnularFacturaCriteria;
import com.spirit.facturacion.gui.panel.JPAnularFactura;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
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
import com.spirit.medios.entity.EquipoEmpleadoIf;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.entity.PlanMedioFormaPagoIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoFacturacionIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class AnularFacturaModel extends JPAnularFactura {
	private static final long serialVersionUID = 4901559804883920984L;
	private CorporacionCriteria corporacionCriteria;
	private ClienteCriteria clienteCriteria;
	private ClienteOficinaCriteria clienteOficinaCriteria;
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
	private static final String ESTADO_ANULADO = NOMBRE_ESTADO_ANULADO.substring(0,1);
	private static final String ESTADO_PENDIENTE = NOMBRE_ESTADO_PENDIENTE.substring(0,1);
	private static final String ESTADO_INCOMPLETO = NOMBRE_ESTADO_INCOMPLETO.substring(0,1);
	private static final String ESTADO_COMPLETO = NOMBRE_ESTADO_COMPLETO.substring(0,1);
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA = "D";
	private static final String TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA = "P";
	
	private static final String ESTADO_PLAN_MEDIO_APROBADO = "A";
	private static final String ESTADO_PLAN_MEDIO_PEDIDO = "D";
	private static final String ESTADO_PLAN_MEDIO_FACTURADO = "F";
	private static final String ESTADO_PLAN_MEDIO_EN_PROCESO = "N";
	private static final String ESTADO_PLAN_MEDIO_PENDIENTE = "P";
	
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DecimalFormat formatoDecimalDescuentoGlobalPorcentaje = new DecimalFormat("#,##0.00");
	private DecimalFormat formatoSerial = new DecimalFormat("0000000");
	protected Double subTotal = 0.00 , descuentoTotal = 0.00 , ivaTotal = 0.00 , total = 0.00, 
	valorBruto = 0.00, ivaTemp = 0.00, descuentosVariosTotal = 0.00;
	protected Double porcentajeComision = 0.00, comisionAgenciaTotal = 0.00;
	protected Double montoDescuento = 0.00;
	protected Double IVA = Parametros.getIVA() / 100;
	protected Double IVA_CERO = 0D;
	
	java.util.Date fechaCreacion = new java.util.Date();
	JMenuItem itemEliminarFacturaDetalle;
	final JPopupMenu popupMenuFacturaDetalle = new JPopupMenu();
	PedidoIf pedido = null;
	
	private static Date fechaFactura;
	Vector<FacturaDetalleIf> FacturaDetalleColeccion = new Vector<FacturaDetalleIf>();
	
	private static final int MAX_LONGITUD_IDENTIFICACION = 20;
	private static final int MAX_LONGITUD_NUMERO = 22;
	private static final int MAX_LONGITUD_DIRECCION = 30;
	private static final int MAX_LONGITUD_TELEFONO = 10;
	private static final int MAX_LONGITUD_CONTACTO = 40;
	private String si = "Si";
	private String no = "No";
	private Object[] options = {si,no};
	
	public AnularFacturaModel() {
		initKeyListeners();
		initListeners();
		getTblFacturaDetalle().addMouseListener(oMouseAdapterTblFacturaDetalle);
		getTblFacturaDetalle().addKeyListener(oKeyAdapterTblFacturaDetalle);
		getBtnBuscarCorporacion().addActionListener(oActionListenerBtnBuscarCorporacion);
		getBtnBuscarCliente().addActionListener(oActionListenerBtnBuscarCliente);
		getBtnBuscarClienteOficina().addActionListener(oActionListenerBtnBuscarClienteOficina);
		showFindMode();
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
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
		
		//Calculo y seteo la maxima longitud del preimpreso segun lo fijado en Parametros Empresa
		int maxlongPreimpEstablecimiento = Parametros.getMaximaLongitudPreimpresoEstablecimiento();
		int maxlongPreimpPuntoEmision = Parametros.getMaximaLongitudPreimpresoPuntoEmision();
		int maxlongPreimpSecuencial = Parametros.getMaximaLongitudPreimpresoSecuencial();
		int guionesSeparadores = 2;
		int longitudPreimpreso = maxlongPreimpEstablecimiento + maxlongPreimpPuntoEmision + maxlongPreimpSecuencial + guionesSeparadores;
		
		getTxtPreimpreso().addKeyListener(new TextChecker(longitudPreimpreso));
		getTxtNumero().addKeyListener(new TextChecker(MAX_LONGITUD_NUMERO));
		getTxtContacto().addKeyListener(new TextChecker(MAX_LONGITUD_CONTACTO));
		
		getCmbFechaFactura().setLocale(Utilitarios.esLocale);
		getCmbFechaFactura().setFormat(Utilitarios.setFechaUppercase());
		getTxtIdentificacion().addKeyListener(new TextChecker(MAX_LONGITUD_IDENTIFICACION));
		getTxtContacto().addKeyListener(new TextChecker(MAX_LONGITUD_CONTACTO));
		getTxtDireccion().addKeyListener(new TextChecker(MAX_LONGITUD_DIRECCION));
		getTxtTelefono().addKeyListener(new TextChecker(MAX_LONGITUD_TELEFONO));
		getCmbFechaFactura().setShowNoneButton(false);
		getCmbFechaFactura().setEditable(false);
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
	
	public void save() {
		SpiritAlert.createAlert("No se puede guardar la factura!", SpiritAlert.WARNING);
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if ("".equals(getTxtContacto().getText()) == false)
			aMap.put("contacto", getTxtContacto().getText()+"%");
		else
			aMap.put("contacto", "%");
		
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
	
	public void find() {
		try {
			Map mapa = buildQuery();
			int tamanoLista = SessionServiceLocator.getFacturaSessionService().getFacturaNoAnuladaListSize(mapa, Parametros.getIdEmpresa());
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
				AnularFacturaCriteria anularFacturaCriteria = new AnularFacturaCriteria();
				anularFacturaCriteria.setResultListSize(tamanoLista);
				anularFacturaCriteria.setQueryBuilded(mapa);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), anularFacturaCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, alineacionColumnas);
				if ( popupFinder.getElementoSeleccionado() != null ){
					factura = (FacturaIf) popupFinder.getElementoSeleccionado();
					if(factura != null){
						getSelectedObject();
					}
				}
				popupFinder = null;
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
				if (getMode()==SpiritMode.FIND_MODE)
					this.getCmbFechaFactura().setSelectedItem(null);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la busqueda de información", SpiritAlert.ERROR);
		}
	}
	
	public void delete() {
		SpiritAlert.createAlert("No se puede borrar la factura!", SpiritAlert.WARNING);
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
		
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpAnularFactura().getSelectedIndex();
		int tabCount = this.getJtpAnularFactura().getTabCount();
		
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpAnularFactura().setSelectedIndex(selectedTab);
	}
	
	public void addDetail() {
		
	}
	
	public void updateDetail() {
		
	}
	
	public void deleteDetail() {
		
	}
	
	public void update() {		
		String mensaje = "¿Esta seguro que desea anular la Factura?";
		int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
		if(opcion == JOptionPane.YES_OPTION) {
			String preimpreso = this.getTxtPreimpreso().getText();
			
			if (!ESTADO_ANULADO.equals(factura.getEstado())) {
				try {
					String usuario = Parametros.getUsuario();
					if ( preimpreso==null || preimpreso.equals("") ){
						PreimpresoAnularFacturaModel preimpresoFactura = new PreimpresoAnularFacturaModel(Parametros.getMainFrame(),factura);
						if ( preimpresoFactura.isAnularFactura() ){
							anularFactura(preimpreso);
						}
						preimpresoFactura = null;
					} else{
						anularFactura(preimpreso);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				}
			}
		}		
	}

	private void anularFactura(String preimpreso) throws GenericBusinessException {
		
		if(tieneAsociadoCompra(FacturaDetalleColeccion)){
			String mensaje = "¿La Factura de Reembolso va a pasar a ser Factura Normal?";
			int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if(opcion == JOptionPane.YES_OPTION) {
				SpiritAlert.createAlert("La Factura no puede ser anulada hasta que se anule o modifique la Compra", SpiritAlert.WARNING);
			}else{
				realizarAnulacion();
			}
		}else{
			realizarAnulacion();
		}
	}
	
	private void realizarAnulacion() throws GenericBusinessException {
		factura.setEstado(ESTADO_ANULADO);
		
		Map aMap = new HashMap();
		aMap.put("tipoDocumentoId", factura.getTipodocumentoId());
		aMap.put("transaccionId", factura.getId());
		aMap.put("empresaId", Parametros.getIdEmpresa());

		SessionServiceLocator.getCarteraSessionService().procesarAnularFactura(factura, FacturaDetalleColeccion, aMap, (UsuarioIf) Parametros.getUsuarioIf());
		SpiritAlert.createAlert("Factura anulada con \u00e9xito", SpiritAlert.INFORMATION);
				
		this.clean();
		this.showSaveMode();
	}
	
	private boolean tieneAsociadoCompra(Vector<FacturaDetalleIf> facturaDetalleColeccion){
		for(FacturaDetalleIf detalleAnulado : FacturaDetalleColeccion){
			if(detalleAnulado.getComprasReembolsoAsociadas() != null && !detalleAnulado.getComprasReembolsoAsociadas().equals("")){
				return true;
			}
		}
		return false;
	}
	
	public boolean isEmpty() {
		if ("".equals(this.getTxtNumero().getText())
				&& "".equals(this.getTxtOficinaEmpresa().getText())
				&& "".equals(this.getTxtIdentificacion().getText())
				&& "".equals(this.getTxtLinea().getText())
				&& "".equals(this.getTxtCantidad().getText())
				&& "".equals(this.getTxtDireccion().getText())
				&& "".equals(this.getTxtTelefono().getText())
				&& "".equals(this.getTxtContacto().getText())
				&& "".equals(this.getTxtFacturadoPor().getText())
				&& "".equals(this.getTxtPreimpreso().getText())
				&& "".equals(this.getTxtFechaCreacion().getText())
				&& "".equals(this.getTxtIVAFinal().getText())
				&& "".equals(this.getTxtICEFinal().getText())
				&& "".equals(this.getTxtOtroImpuestoFinal().getText())
				&& "".equals(this.getTxtValorFinal().getText())
				&& "".equals(this.getTxtPorcentajeDescuentoAgencia().getText())
				&& "".equals(this.getTxtPorcentajeDescuentosVarios().getText())
				&& "".equals(this.getTxtProveedor().getText())
				&& "".equals(this.getTxtDescripcion().getText())
				&& "".equals(this.getTxtTipodentificacion().getText())
				&& "".equals(this.getTxtTipoDocumento().getText())
				&& "".equals(this.getTxtPuntoImpresion().getText())
				&& "".equals(this.getTxtPresupuesto().getText())
				&& "".equals(this.getTxtClienteOficina().getText())
		) {
			
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validateFields() {		
		return true;
	}
	
	public boolean validateFieldsDetalle(){
		return true;
	}
	
	public void clean() {
		factura = null;
		pedido = null;
		//Vacio la tabla de factura detalle
		DefaultTableModel model = (DefaultTableModel) this.getTblFacturaDetalle().getModel();
		for(int i= this.getTblFacturaDetalle().getRowCount();i>0;--i)
			model.removeRow(i-1);
		
		FacturaDetalleColeccion = new Vector();
		subTotal = 0.00;
		valorBruto = 0.00;
		descuentoTotal = 0.00;
		descuentosVariosTotal = 0.00;
		porcentajeComision = 0.00;
		comisionAgenciaTotal = 0.00;
		ivaTotal = 0.00;
		total = 0.00;
		//inicializo los campos de texto de pedido
		this.getTxtNumero().setText("");
		this.getTxtFechaCreacion().setText("");
		this.getTxtOficinaEmpresa().setText("");
		this.getTxtObservacion().setText("");
		this.getTxtTipodentificacion().setText("");
		this.getTxtIdentificacion().setText("");
		this.getTxtTelefono().setText("");
		this.getTxtContacto().setText("");
		this.getTxtDireccion().setText("");
		this.getTxtAutorizacionSAP().setText("");
		this.getTxtPuntoImpresion().setText("");
		this.getTxtMoneda().setText("");
		this.getTxtFormaPago().setText("");
		this.getTxtListaPrecio().setText("");
		this.getTxtCaja().setText("");
		this.getTxtFechaVencimiento().setText("");
		this.getTxtOrigenDocumento().setText("");
		this.getTxtVendedor().setText("");
		this.getTxtDirectorCuentas().setText("");
		this.getTxtBodega().setText("");
		this.getTxtPreimpreso().setText("");
		//inicializo los campos de texto de pedido detalle
		this.getTxtProveedor().setText("");
		this.getTxtLinea().setText("");
		this.getTxtCantidad().setText("");
		this.getTxtPrecioReal().setText("");
		this.getTxtCantidadDevuelta().setText("");
		this.getTxtPorcentajeDescuentoAgencia().setText("");
		this.getTxtPorcentajeDescuentosVarios().setText("");
		this.getTxtValorFinal().setText("");
		this.getTxtDescuentoAgenciaTotal().setText("");
		this.getTxtDescuentosVariosTotal().setText("");
		this.getTxtIVAFinal().setText("");
		this.getTxtICEFinal().setText("");
		this.getTxtOtroImpuestoFinal().setText("");
		this.getTxtTotalFinal().setText("");
		this.getTxtPorcentajeComision().setText("");
		this.getTxtValorComision().setText("");
		this.getTxtDocumento().setText("");
		this.getTxtMotivo().setText("");
		//inicializo los combos de pedido
		this.getTxtTipoDocumento().setText("");
		this.getTxtEstado().setText("");
		//remuevo los listners de los combos de pedido
		//this.getCmbFechaFactura().removeActionListener(oActionListenerCmbFechaFactura);
		//limpio los campos de texto con listeners
		this.getTxtCodigoProducto().setText("");
		this.getTxtDescripcion().setText("");
		//remuevo los elemntos de los combos de factura
		//this.getCmbTipoDocumento().removeAllItems();
		//this.getCmbEstado().removeAllItems();
		this.getTxtCorporacion().setText("");
		corporacionIf = null;
		this.getTxtCliente().setText("");
		clienteIf = null;
		this.getTxtClienteOficina().setText("");
		clienteOficinaIf = null;
		getTxtPlanMedios().setText("");
		getTxtPresupuesto().setText("");
		getTxtFacturadoPor().setText("");
		getTxtPrecio().setText("");
		getTxtCosto().setText("");
		this.getCbNegociacionDirecta().setSelected(false);
		this.getCbComisionPura().setSelected(false);
		this.getTxtPorcentajeNegociacionDirecta().setText("");
		this.getTxtDsctoCompraPorcentaje().setText("");
		this.getTxtClienteNegociacion().setText("");
		this.clienteOficinaNegociacionIf = null;
		
		if(getMode()==SpiritMode.FIND_MODE){
			this.getCmbFechaFactura().setSelectedItem(null);
		}
		
		this.repaint();
	}
	
	public void cleanTabla() {
		DefaultTableModel model = (DefaultTableModel) this.getTblFacturaDetalle().getModel();
		
		for(int i= this.getTblFacturaDetalle().getRowCount();i>0;--i)
			model.removeRow(i-1);
		
		FacturaDetalleColeccion = new Vector();
		subTotal = 0.00;
		valorBruto = 0.00;
		descuentoTotal = 0.00;
		descuentosVariosTotal = 0.00;
		porcentajeComision = 0.00;
		comisionAgenciaTotal = 0.00;
		ivaTotal = 0.00;
		total = 0.00;
		this.repaint();
	}
	
	public void showFindMode() {
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
		getTxtEstado().setEditable(false);
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
		getTxtPreimpreso().grabFocus();
		setFindMode();
		getJpNegociacion().setVisible(false);
	}
	
	public void showSaveMode() {
		showFindMode();
	}
		
	ActionListener oActionListenerCmbFechaFactura = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			
			fechaFactura = (Date) ((DateComboBox) eventoInicio.getSource()).getDate();
			java.util.Date fechaHoy = new java.util.Date();
			
			if(fechaFactura.before(fechaHoy)){
				SpiritAlert.createAlert("Por favor seleccione una fecha de inicio válida!", SpiritAlert.INFORMATION);
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(fechaHoy);
				fechaFactura = fechaHoy;
				getCmbFechaFactura().setCalendar(calendar);
			}
		}
	};
	
	ActionListener oActionListenerCmbFechaFacturaUpdate = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			fechaFactura = (Date) ((DateComboBox) eventoInicio.getSource()).getDate();	
		}
	};
	
	public void showUpdateMode() {
		getTxtContacto().setBackground(getBackground());
		getCmbFechaFactura().setBackground(Parametros.saveUpdateColor);
		getTxtClienteOficina().setBackground(getBackground());
		getTxtNumero().setBackground(getBackground());
		getTxtPreimpreso().setBackground(getBackground());
		
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
		getTxtEstado().setEditable(false);
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
		getTxtPreimpreso().setEditable(false);
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
	}
	
	private void getSelectedObject(){
		cargarCombos();
		
		try {
			TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(factura.getTipodocumentoId());
			getTxtTipoDocumento().setText(tipoDocumento.getCodigo() + " - " + tipoDocumento.getNombre());
			getTxtNumero().setText(formatoSerial.format(factura.getNumero()));
			String fecha = Utilitarios.getFechaUppercase(factura.getFechaCreacion());
			getTxtFechaCreacion().setText(fecha);
			Calendar calendarInicio = new GregorianCalendar();
			calendarInicio.setTime(factura.getFechaFactura());
			getCmbFechaFactura().setCalendar(calendarInicio);
			fechaFactura = factura.getFechaFactura();
			Calendar calendarFin = new GregorianCalendar();
			calendarFin.setTime(factura.getFechaVencimiento());
			//fechaVencimiento = dateFormatter.format(factura.getFechaVencimiento());
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
				getTxtEstado().setText(NOMBRE_ESTADO_COMPLETO);
			else if (ESTADO_INCOMPLETO.equals(factura.getEstado()))
				getTxtEstado().setText(NOMBRE_ESTADO_INCOMPLETO);
			else if (ESTADO_PENDIENTE.equals(factura.getEstado()))
				getTxtEstado().setText(NOMBRE_ESTADO_PENDIENTE);
			else if (ESTADO_ANULADO.equals(factura.getEstado()))
				getTxtEstado().setText(NOMBRE_ESTADO_ANULADO);
			
			if(factura.getPuntoImpresionId()!=null){
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
							getTxtPresupuesto().setText(presupuesto.getCodigo());
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
						Iterator it = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndByEstados(codigo, ESTADO_PLAN_MEDIO_PEDIDO,ESTADO_PLAN_MEDIO_FACTURADO,ESTADO_PLAN_MEDIO_APROBADO,ESTADO_PLAN_MEDIO_EN_PROCESO,ESTADO_PLAN_MEDIO_PENDIENTE).iterator();
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
			
			BodegaIf bodega = SessionServiceLocator.getBodegaSessionService().getBodega(factura.getBodegaId());
			getTxtBodega().setText(bodega.getCodigo() + " - " + bodega.getNombre());
			
			if(factura.getOrigendocumentoId()!=null){
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
			Iterator it = listaPlantillaDetalle.iterator();
			porcentajeComision = factura.getPorcentajeComisionAgencia().doubleValue();
			getTxtPorcentajeComision().setText(formatoDecimal.format(porcentajeComision));

			while (it.hasNext()) {
				Double subtotal = 0D;
				FacturaDetalleIf pedidoDetalleIf = (FacturaDetalleIf) it.next();
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
				filaPlantillaDetalle.add(formatoDecimal.format(pedidoDetalleIf.getPrecioReal().doubleValue()));
				Double cantidad = pedidoDetalleIf.getCantidad().doubleValue() * pedidoDetalleIf.getPrecioReal().doubleValue();
				Double descuento = 0.0;
				Double descuentoGlobal = 0.0;
				if ( cantidad != 0 ){
					descuento = Utilitarios.redondeoValor((pedidoDetalleIf.getDescuento().doubleValue() * 100) / (cantidad));
					descuentoGlobal = Utilitarios.redondeoValor((pedidoDetalleIf.getDescuentoGlobal().doubleValue() * 100) / (cantidad));
				}

				filaPlantillaDetalle.add(String.valueOf(descuento.intValue()) + " % ");
				filaPlantillaDetalle.add(String.valueOf(descuentoGlobal.intValue()) + "%");
				if (pedidoDetalleIf.getIva().doubleValue() > 0D)
					filaPlantillaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
				else
					filaPlantillaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");
				getTxtDescuentoGlobal().setText(formatoDecimalDescuentoGlobalPorcentaje.format(descuentoGlobal));
				descuento = Utilitarios.redondeoValor(pedidoDetalleIf.getDescuento().doubleValue());
				descuentoGlobal = Utilitarios.redondeoValor(pedidoDetalleIf.getDescuentoGlobal().doubleValue());
				Double iva = pedidoDetalleIf.getIva().doubleValue();
				subtotal = pedidoDetalleIf.getPrecioReal().doubleValue() * pedidoDetalleIf.getCantidad().doubleValue();
				double porcentajeDescuentosVarios = pedidoDetalleIf.getPorcentajeDescuentosVarios().doubleValue() / 100;
				double descuentosVarios = subtotal * porcentajeDescuentosVarios;
				Double comisionAgencia = Utilitarios.redondeoValor(((subtotal - descuento - descuentosVarios - descuentoGlobal) * porcentajeComision) / 100D);

				if (documento.getBonificacion().equals(OPCION_NO)) {
					subTotal += subtotal;
					this.getTxtValorFinal().setText(formatoDecimal.format(subTotal));
					this.getTxtICEFinal().setText("0.00");
					this.getTxtOtroImpuestoFinal().setText("0.00");
					descuentoTotal = descuentoTotal + descuento + descuentoGlobal;
					descuentosVariosTotal = descuentosVariosTotal + descuentosVarios;
					this.getTxtDescuentoAgenciaTotal().setText(formatoDecimal.format(descuentoTotal));
					this.getTxtDescuentosVariosTotal().setText(formatoDecimal.format(descuentosVariosTotal));
					comisionAgenciaTotal = comisionAgenciaTotal + comisionAgencia;
					this.getTxtValorComision().setText(formatoDecimal.format(comisionAgenciaTotal));
					ivaTotal = ivaTotal + iva;
					this.getTxtIVAFinal().setText(formatoDecimal.format(ivaTotal));
					total = subTotal - descuentoTotal - descuentosVariosTotal + ivaTotal + comisionAgenciaTotal;
					this.getTxtTotalFinal().setText(formatoDecimal.format(total));
				}

				modelFacturaDetalle.addRow(filaPlantillaDetalle);
			}
			
			showUpdateMode();			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	MouseListener oMouseAdapterTblFacturaDetalle = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			getDetailTblFacturaDetalle(evt);
		}
	};
	
	KeyListener oKeyAdapterTblFacturaDetalle = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			getDetailTblFacturaDetalle(evt);
		}
	};
	
	private void getDetailTblFacturaDetalle(ComponentEvent evt) {
		if (getTblFacturaDetalle().getSelectedRow() != -1) {
			FacturaDetalleIf facturaDetalleIf = (FacturaDetalleIf) FacturaDetalleColeccion.get(((JTable) evt.getSource()).getSelectedRow());
			
			try {
				if(facturaDetalleIf.getProductoId()!=null){
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
					
					getTxtCodigoProducto().setText(productoIf.getCodigo());
					getTxtDescripcion().setText(facturaDetalleIf.getDescripcion());
					
					if(facturaDetalleIf.getLoteId() != null) {
						LoteIf loteIf = SessionServiceLocator.getLoteSessionService().getLote(facturaDetalleIf.getLoteId());
						getTxtLote().setText(loteIf.getCodigo());
					}
					
					LineaIf lineaIf = SessionServiceLocator.getLineaSessionService().getLinea(generico.getLineaId());
					getTxtLinea().setText(lineaIf.getCodigo() + " - " + lineaIf.getNombre());
					DocumentoIf documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(facturaDetalleIf.getDocumentoId());
					getTxtDocumento().setText(documento.getCodigo() + " - " + documento.getNombre());
					
					if(facturaDetalleIf.getMotivodocumentoId() != null){
						MotivoDocumentoIf motivoDocumento = SessionServiceLocator.getMotivoDocumentoSessionService().getMotivoDocumento(facturaDetalleIf.getMotivodocumentoId());
						getTxtMotivo().setText(motivoDocumento.getCodigo() + " - " + motivoDocumento.getNombre());
					}
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			} 
			
			getTxtCantidad().setText(facturaDetalleIf.getCantidad().toString());
			getTxtPrecio().setText(formatoDecimal.format(Utilitarios.redondeoValor(facturaDetalleIf.getPrecio().doubleValue())));
			
			if (facturaDetalleIf.getCosto() != null)
				getTxtCosto().setText(formatoDecimal.format(Utilitarios.redondeoValor(facturaDetalleIf.getCosto().doubleValue())));
			
			getTxtPrecioReal().setText(formatoDecimal.format(Utilitarios.redondeoValor(facturaDetalleIf.getPrecioReal().doubleValue())));
			getTxtCantidadDevuelta().setText(formatoDecimal.format(facturaDetalleIf.getCantidadDevuelta()));
			Double cantidad = facturaDetalleIf.getCantidad().doubleValue() * facturaDetalleIf.getPrecioReal().doubleValue(); 
			Double  descuento = 0.0;
			
			if ( cantidad != 0 )
				descuento = (facturaDetalleIf.getDescuento().doubleValue() * 100)/(cantidad);
			
			getTxtPorcentajeDescuentoAgencia().setText(formatoDecimal.format(descuento));
			getTxtPorcentajeDescuentosVarios().setText(formatoDecimal.format(facturaDetalleIf.getPorcentajeDescuentosVarios()));
		}
	}
	
	public void cargarCombos(){
		getCmbFechaFactura().setFormat(Utilitarios.setFechaUppercase());
	}
	
	// Listener para el combo de corporacion
	ActionListener oActionListenerBtnBuscarCorporacion = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
			JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
					corporacionCriteria,
					JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
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
	
	//listener para el combo de cliente
	ActionListener oActionListenerBtnBuscarCliente = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			Long idCorporacion = 0L;
			
			if (corporacionIf != null)
				idCorporacion = corporacionIf.getId();
			
			clienteCriteria = new ClienteCriteria("Clientes", idCorporacion, CODIGO_TIPO_CLIENTE);
			JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if (popupFinder.getElementoSeleccionado() != null) {
				clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
				getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
				if (corporacionIf == null) {
					try {
						corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
						getTxtCorporacion().setText(corporacionIf.getCodigo()  + " - " + corporacionIf.getNombre());
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
	
	//listener para el combo de cliente oficina
	ActionListener oActionListenerBtnBuscarClienteOficina = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			Long idCorporacion = 0L;
			Long idCliente = 0L;
			String tituloVentanaBusqueda = "Oficinas del Cliente";
			
			if (corporacionIf != null)
				idCorporacion = corporacionIf.getId();
			
			if (clienteIf != null)
				idCliente = clienteIf.getId();
			 
			clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_CLIENTE, "", false);
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.addElement(70);
			anchoColumnas.addElement(200);
			anchoColumnas.addElement(80);
			anchoColumnas.addElement(230);
			JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS,anchoColumnas, null);
			
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
