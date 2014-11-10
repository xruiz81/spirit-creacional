package com.spirit.cartera.gui.model;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraData;
import com.spirit.cartera.entity.CarteraDetalleData;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.gui.criteria.CarteraCriteria;
import com.spirit.cartera.gui.panel.JDAfectaCartera;
import com.spirit.cartera.gui.panel.JPCartera;
import com.spirit.cartera.handler.ComprobanteEgresoData;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.RMCantidadEnLetras;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.compras.entity.CompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.LineaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ServidorIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.general.gui.panel.JDCheque;
import com.spirit.general.util.Numeradores;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.sri.entity.SriClienteRetencionIf;
import com.spirit.sri.entity.SriSustentoTributarioIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.ObjectCloner;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class CarteraModel extends JPCartera {
	private static final long serialVersionUID = 3664763524059474270L;
	private static final String CARTERA_SI = "SI";
	private static final String CARTERA_NO = "NO";
	private static final String OPCION_SI = CARTERA_SI.substring(0, 1);
	private static final String OPCION_NO = CARTERA_NO.substring(0, 1);
	private static final String TIPO_PAGO_CHEQUE = "CHEQUE";
	private static final String TIPO_PAGO_EFECTIVO = "EFECTIVO";
	private static final String TIPO_PAGO_CREDITO = "CREDITO";
	private static final String CODIGO_TIPO_PAGO_CHEQUE = TIPO_PAGO_CHEQUE.substring(0, 2);
	private static final String CODIGO_TIPO_PAGO_EFECTIVO = TIPO_PAGO_EFECTIVO.substring(0, 2);
	private static final String CODIGO_TIPO_PAGO_CREDITO = TIPO_PAGO_CREDITO.substring(0, 2);
	private static final String TIPO_EMPLEADO_VENDEDOR = "ECU";
	private static final String CODIGO_MODULO_CARTERA = "CART";
	private JDPopupFinderModel popupFinder;
	private CorporacionCriteria corporacionCriteria;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private ClienteCriteria clienteCriteria;
	private JDAfectaCartera jdAfectaCartera;
	private JDCheque jdCheque;
	private CarteraIf cartera;
	private CorporacionIf corporacionIf;
	private ClienteIf clienteIf;
	private ClienteOficinaIf clienteOficinaIf;
	private TipoDocumentoIf tipoDocumentoIf;
	private MonedaIf monedaIf;
	private DocumentoIf documentoIf;
	private TipoPagoIf tipoPagoIf;
	private CarteraDetalleIf carteraDetalleForUpdate;
	private static final int MAX_LONGITUD_CODIGO = 30;
	private static final int MAX_LONGITUD_PREIMPRESO = 20;
	private static final int MAX_LONGITUD_COMENTARIOS = 100;
	private static final int MAX_LONGITUD_SECUENCIAL = 3;
	private static final int MAX_LONGITUD_REFERENCIA = 50;
	private static final int MAX_LONGITUD_REFERENCIA_CARTERA = 22;
	private static final int MAX_LONGITUD_AUTORIZACION = 20;
	private static final int MAX_LONGITUD_VALORES = 22;
	private static final String NOMBRE_TIPO_CARTERA_CLIENTE = "CLIENTE";
	private static final String TIPO_CARTERA_CLIENTE = NOMBRE_TIPO_CARTERA_CLIENTE.substring(0, 1);
	private static final String NOMBRE_TIPO_CARTERA_PROVEEDOR = "PROVEEDOR";
	private static final String TIPO_CARTERA_PROVEEDOR = NOMBRE_TIPO_CARTERA_PROVEEDOR.substring(0, 1);
	private static final String NOMBRE_ESTADO_NORMAL = "NORMAL";
	private static final String ESTADO_NORMAL = NOMBRE_ESTADO_NORMAL.substring(0, 1);
	private static final String NOMBRE_ESTADO_ANULADO = "ANULADO";
	private static final String ESTADO_ANULADO = NOMBRE_ESTADO_ANULADO.substring(0, 1);
	private static final String NOMBRE_ESTADO_DUDOSO = "DUDOSO";
	private static final String ESTADO_DUDOSO = NOMBRE_ESTADO_DUDOSO.substring(0, 1);
	private static final String NOMBRE_ESTADO_CASTIGADO = "CASTIGADO";
	private static final String ESTADO_CASTIGADO = NOMBRE_ESTADO_CASTIGADO.substring(0, 1);
	private DecimalFormat formatoSerial = new DecimalFormat("0000000");
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	java.util.Date fechaCartera = new java.util.Date();
	java.util.Date fechaCambioEstadoCartera = new java.util.Date();
	java.util.Date fechaCarteraDetalle = new java.util.Date();
	java.util.Date fechaCreacionDetalle = new java.util.Date();
	java.util.Date fechaActualizacionDetalle = new java.util.Date();
	java.util.Date fechaVencimientoDetalle = new java.util.Date();
	int secuencialDetalle = 1;
	int filaSeleccionadaTablaDetalle = -1;
	Vector<CarteraDetalleIf> carteraDetalleColeccion = new Vector<CarteraDetalleIf>();
	ArrayList<String[]> cheques = new ArrayList<String[]>();
	DefaultTableModel modelCarteraDetalle;
	Map registrosAfectaMap = new HashMap();
	Map registrosAfectaToDetalleCarteraMap = new HashMap();
	Map registrosDetallesToDetalleCarteraMap = new HashMap();
	double valorCartera = 0D;
	double saldoCartera = 0D;
	
	private Vector<CarteraDetalleIf> carteraDetalleEliminadoColeccion = new Vector<CarteraDetalleIf>();
	Map<Long,Map> mapaRegistrosSeleccionadosMap = new HashMap();
	Map<Long,Map> mapaRegistrosSeleccionadosMapTemp = new HashMap();
	Vector registrosDetallesToDetalleCarteraVector;
	Vector registrosDetallesToDetalleCarteraVectorTemp;
	Map registrosDetallesToDetalleCarteraMapTemp;
	Map registrosAfectaMapTemp;
	Vector registrosAfectaToDetalleCarteraVector;
	Vector registrosAfectaToDetalleCarteraVectorTemp;
	Map registrosAfectaToDetalleCarteraMapTemp;
	private Vector<CarteraAfectaIf> carteraAfectaEliminarColeccion = new Vector<CarteraAfectaIf>();
	private Vector<CarteraIf> carteraActualizadaColeccion = new Vector<CarteraIf>();
	private Vector<ComprobanteIngresoData> comprobanteIngresoColeccion = new Vector<ComprobanteIngresoData>();
	private Vector<ComprobanteEgresoData> comprobanteEgresoColeccion = new Vector<ComprobanteEgresoData>();
	private static final String CODIGO_DOC_CLI_NIVELACION_POSITIVA = "CLNP";
	private static final String CODIGO_DOC_COMP_INGRESO_PAGO_EMPLEADO = "CIPE";
	private List<CarteraDetalleIf> carteraDetalleColeccionCheques = new ArrayList<CarteraDetalleIf>();
	private List<CarteraDetalleIf> carteraDetalleColeccionChequesImprimir = new ArrayList<CarteraDetalleIf>();
	private String si = "Si";
	private String no = "No"; 
	private Object[] options ={si,no};
	private static final String DOCUMENTO_ANTICIPO_CLIENTES = "ANCL";
	private static final String ANTICIPO_PROVEEDOR_CHEQUE = "APCH";
	private static final String ANTICIPO_PROVEEDOR_EFECTIVO = "APEF";
	private static final String ANTICIPO_PROVEEDOR_PRESTAMO_ACCIONISTA = "APPA";
	private static final String DOCUMENTO_ANTICIPO_POR_SALDO = "ANSA";
	private static final String TIPO_DOCUMENTO_ANTICIPO_POR_SALDO = "ANS";
	private static final String NOTA_INTERNA_CREDITO_CLIENTE = "NICC";
	private static final String NOTA_CREDITO_ANTICIPO_CLIENTE = "NCAC";
	private static final String NOTA_CREDITO_PROVEEDOR = "NCPR";
	private static final String CRUCE_FACTURA_PROVEEDOR = "CFP";
	private static final String RECIBO_CAJA_EFECTIVO = "RCCE";
	private static final String RECIBO_CAJA_TARJETA_CREDITO = "RCTC";
	private boolean nuevaCodificacionActivada = true;
	private Map<Long,TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long,TipoDocumentoIf>();
	
	public CarteraModel() {
		anchoColumnasTabla();
		cargarMapaTipoDocumento();
		initKeyListeners();
		addPopupMenu();
		cargarComboTipoCartera();
		loadCombos();		
		initListeners();
		cargarComboEstadoCartera();		
		getTblDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.clean();
		this.showSaveMode();
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblDetalle().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(35);
		anchoColumna = getTblDetalle().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(130);
		anchoColumna = getTblDetalle().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblDetalle().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblDetalle().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblDetalle().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblDetalle().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblDetalle().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblDetalle().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblDetalle().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(85);
	}
	
	public void cargarMapaTipoDocumento(){
		try {
			mapaTipoDocumento.clear();
			Collection tiposDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumentoList();
			Iterator tiposDocumentoIt = tiposDocumento.iterator();
			while(tiposDocumentoIt.hasNext()){
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)tiposDocumentoIt.next();
				mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	private void initKeyListeners() {
		getLblDepositoDetalle().setVisible(false);
		getTxtDepositoDetalle().setVisible(false);
		getCmbFechaCarteraDetalle().setLocale(Utilitarios.esLocale);
		getCmbFechaEmisionCartera().setLocale(Utilitarios.esLocale);
		getCmbFechaCarteraDetalle().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaEmisionCartera().setFormat(Utilitarios.setFechaUppercase());
		getTxtCodigoCartera().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtReferenciaCartera().addKeyListener(new TextChecker(MAX_LONGITUD_REFERENCIA_CARTERA));
		getTxtReferenciaCartera().addKeyListener(new NumberTextField());
		getTxtPreimpreso().addKeyListener(new TextChecker(MAX_LONGITUD_PREIMPRESO));
		getTxtPreimpresoDetalle().addKeyListener(new TextChecker(MAX_LONGITUD_PREIMPRESO));
		getTxtComentariosCartera().addKeyListener(new TextChecker(MAX_LONGITUD_COMENTARIOS));
		getTxtSecuencialDetalle().addKeyListener(new TextChecker(MAX_LONGITUD_SECUENCIAL));
		getTxtReferenciaDetalle().addKeyListener(new TextChecker(MAX_LONGITUD_REFERENCIA));
		getTxtAutorizacionDetalle().addKeyListener(new TextChecker(MAX_LONGITUD_AUTORIZACION));
		getTxtValorDetalle().addKeyListener(new TextChecker(MAX_LONGITUD_VALORES));
		getTxtValorDetalle().addKeyListener(new NumberTextFieldDecimal());
		getTxtCotizacionDetalle().addKeyListener(new TextChecker(MAX_LONGITUD_VALORES));
		getTxtCotizacionDetalle().addKeyListener(new NumberTextFieldDecimal());
		getCmbFechaEmisionCartera().setEditable(false);
		getCmbFechaCarteraDetalle().setEditable(false);
		getCmbFechaEmisionCartera().setShowNoneButton(false);
		getCmbFechaCarteraDetalle().setShowNoneButton(false);
	}
	
	private void addPopupMenu() {
		// agregando items de eliminar al popupmenu
		setItemMapaAfectaCartera(new JMenuItem("<html><font color=red>Afecta cartera...</font></html>"));
		getItemMapaAfectaCartera().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				if (tipoDocumentoIf != null && !tipoDocumentoIf.getCodigo().equals("NCC")) {
					if (tipoDocumentoIf != null && tipoDocumentoIf.getCodigo().equals("ANP") && getMode() == SpiritMode.SAVE_MODE)
						SpiritAlert.createAlert("No se puede cruzar este documento.\nPara cruzar el anticipo debe guardarlo primeramente.", SpiritAlert.WARNING);
					else
					 enablePopupAfectaCartera();
				} else
					SpiritAlert.createAlert("Función no permitida para Notas de Crédito", SpiritAlert.WARNING);
			}
		});
		popup.add(itemMapaAfectaCartera);
		setItemEliminarDetalleCartera(new JMenuItem("<html><font color=red>Eliminar</font></html>"));
		getItemEliminarDetalleCartera().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				eliminarCarteraDetalle();
			}
		});
		popup.add(itemEliminarDetalleCartera);
		// agregando el popupmenu a label y su escuchador de raton
		getTblDetalle().add(popup);
		getTblDetalle().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON3) {
					if (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE)
						popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}
	
	public boolean validateFields() {
		String strCodigo = getTxtCodigoCartera().getText();
		Date fechaCreacion = getCmbFechaEmisionCartera().getDate();
		String strCorporacion = getTxtCorporacionCartera().getText();
		String strCliente = getTxtClienteCartera().getText();
		String strClienteOficina = getTxtClienteOficinaCartera().getText();
		String strOficina = getTxtOficinaCartera().getText();
		
		if((getMode() == SpiritMode.SAVE_MODE) && getCmbEstadoCartera().getSelectedItem().equals(NOMBRE_ESTADO_ANULADO)){
			SpiritAlert.createAlert("No puede guardar una cartera con estado Anulado !!", SpiritAlert.WARNING);
			return false;
		}
		
		if ("".equals(strCodigo)) {
			SpiritAlert.createAlert("Debe ingresar el código !!", SpiritAlert.WARNING);
			getJtpCartera().setSelectedIndex(0);
			getTxtCodigoCartera().grabFocus();
			return false;
		}
		
		if (fechaCreacion == null) {
			SpiritAlert.createAlert("Debe ingresar la fecha de creación !!", SpiritAlert.WARNING);
			getJtpCartera().setSelectedIndex(0);
			getCmbFechaEmisionCartera().grabFocus();
			return false;
		}
		
		if (fechaCreacion.compareTo(new java.util.Date()) > 0) {
			SpiritAlert.createAlert("La fecha de emisión no debe superar a la fecha actual", SpiritAlert.WARNING);
			getJtpCartera().setSelectedIndex(0);
			getCmbFechaEmisionCartera().grabFocus();
			return false;
		}
		
		if (getCmbTipoCartera().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de cartera !!", SpiritAlert.WARNING);
			getJtpCartera().setSelectedIndex(0);
			getCmbTipoCartera().grabFocus();
			return false;
		}
		
		if (getCmbTipoDocumento().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de documento !!", SpiritAlert.WARNING);
			getJtpCartera().setSelectedIndex(0);
			getCmbTipoDocumento().grabFocus();
			return false;
		}
		
		if ("".equals(strCorporacion)) {
			SpiritAlert.createAlert("Debe ingresar la corporación !!", SpiritAlert.WARNING);
			getJtpCartera().setSelectedIndex(0);
			getTxtCorporacionCartera().grabFocus();
			return false;
		}
		
		if ("".equals(strCliente)) {
			SpiritAlert.createAlert("Debe ingresar el cliente !!", SpiritAlert.WARNING);
			getJtpCartera().setSelectedIndex(0);
			getTxtClienteCartera().grabFocus();
			return false;
		}
		
		if ("".equals(strClienteOficina)) {
			SpiritAlert.createAlert("Debe ingresar la oficina del cliente !!", SpiritAlert.WARNING);
			getJtpCartera().setSelectedIndex(0);
			getTxtClienteOficinaCartera().grabFocus();
			return false;
		}
		
		if ("".equals(strOficina)) {
			SpiritAlert.createAlert("Debe ingresar la oficina !!", SpiritAlert.WARNING);
			getJtpCartera().setSelectedIndex(0);
			getTxtOficinaCartera().grabFocus();
			return false;
		}
		
		/*if (getCmbVendedorCartera().getSelectedItem() == null && getCmbTipoCartera().getSelectedItem().toString().equals("CLIENTE")) {
			SpiritAlert.createAlert("Debe seleccionar el vendedor !!", SpiritAlert.WARNING);
			getJtpCartera().setSelectedIndex(0);
			getCmbVendedorCartera().grabFocus();
			return false;
		}*/
		
		if (getCmbMonedaCartera().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la moneda !!", SpiritAlert.WARNING);
			getJtpCartera().setSelectedIndex(0);
			getCmbMonedaCartera().grabFocus();
			return false;
		}
		
		if (getCmbEstadoCartera().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el estado de la cartera !!", SpiritAlert.WARNING);
			getJtpCartera().setSelectedIndex(0);
			getCmbEstadoCartera().grabFocus();
			return false;
		}
		
		if (carteraDetalleColeccion.size() == 0) {
			SpiritAlert.createAlert("Debe ingresar el detalle de la cartera !!", SpiritAlert.WARNING);
			getJtpCartera().setSelectedIndex(1);
			this.getCmbDocumentoDetalle().grabFocus();
			return false;
		}
		
		return true;		
	}
	
	public void save() {
		if (validateFields()) {
			try {
				cheques = null;
				cheques = new ArrayList<String[]>();
				Map<String, Object> parametrosEmpresa = new HashMap<String, Object>();
				parametrosEmpresa.put("idEmpresa", Parametros.getIdEmpresa());
				parametrosEmpresa.put("idOficina", Parametros.getIdOficina());
				parametrosEmpresa.put("IVA", Parametros.getIVA());
				
				CarteraIf cartera = registrarCartera();
				TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(cartera.getTipodocumentoId());
				String ANTICIPO_NIVELACION_CLIENTE = "";
				boolean anticipoxSaldo = false;
				if(tipoDocumento.getCodigo().equals("CIN") && (Utilitarios.redondeoValor(cartera.getSaldo().doubleValue()) > 0D)){
					String cancel = "Cancelar"; 
					Object[] optionsCIN ={si,no,cancel}; 
					int opcion = JOptionPane.showOptionDialog(null, "¿Existe un Saldo de $ " + formatoDecimal.format(cartera.getSaldo()) + " , desea crear un Anticipo para el Cliente?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsCIN, si);
					if (opcion == JOptionPane.YES_OPTION) {
						ANTICIPO_NIVELACION_CLIENTE = "AC";
						anticipoxSaldo = true;
						cartera = SessionServiceLocator.getCarteraSessionService().procesarCartera(cartera, carteraDetalleColeccion, mapaRegistrosSeleccionadosMap, registrosAfectaToDetalleCarteraMap, parametrosEmpresa, ANTICIPO_NIVELACION_CLIENTE);
						SpiritAlert.createAlert("Cartera guardada con \u00e9xito",SpiritAlert.INFORMATION);
						boolean anulado = false;
						generarReporte(cartera, anulado, anticipoxSaldo);
						generarCheques(cartera);
						carteraDetalleColeccion.clear();
						this.clean();
						this.loadCombos();
						this.showSaveMode();
					}else if (opcion == JOptionPane.NO_OPTION) {
						ANTICIPO_NIVELACION_CLIENTE = "NP";
						cartera = SessionServiceLocator.getCarteraSessionService().procesarCartera(cartera, carteraDetalleColeccion, mapaRegistrosSeleccionadosMap, registrosAfectaToDetalleCarteraMap, parametrosEmpresa, ANTICIPO_NIVELACION_CLIENTE);
						SpiritAlert.createAlert("Cartera guardada con \u00e9xito",SpiritAlert.INFORMATION);
						boolean anulado = false;
						generarReporte(cartera, anulado, anticipoxSaldo);
						generarCheques(cartera);
						carteraDetalleColeccion.clear();
						this.clean();
						this.loadCombos();
						this.showSaveMode();
					}else{
						//En el caso de cancelar, no debe pasar nada
						// >> Reply: Y si no debe pasar nada ¿por qué se programó este else?
					}			
				}else{
					//ANTICIPO_NIVELACION_CLIENTE = (tipoDocumento.getCodigo().equals("NCC") && cartera.getValor().doubleValue() > cartera.getSaldo().doubleValue())?"NC":"";
 					cartera = SessionServiceLocator.getCarteraSessionService().procesarCartera(cartera, carteraDetalleColeccion, mapaRegistrosSeleccionadosMap, registrosAfectaToDetalleCarteraMap, parametrosEmpresa, ANTICIPO_NIVELACION_CLIENTE);
					SpiritAlert.createAlert("Cartera guardada con \u00e9xito",SpiritAlert.INFORMATION);
					boolean anulado = false;
					generarReporte(cartera, anulado, anticipoxSaldo);
					generarCheques(cartera);
					carteraDetalleColeccion.clear();
					this.clean();
					this.loadCombos();
					this.showSaveMode();
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Ocurri\u00f3 un error al guardar la Cartera", SpiritAlert.ERROR);
			}
		}
	}
	
	public void update() {
		try {
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)getCmbTipoDocumento().getSelectedItem();
			String estado = (String)getCmbEstadoCartera().getSelectedItem();
			boolean anulado = true;
			if(cartera.getEstado().equals(ESTADO_ANULADO)) {
				SpiritAlert.createAlert("No es posible Actualizar una Cartera Anulada", SpiritAlert.WARNING);
				getCmbEstadoCartera().setSelectedItem(NOMBRE_ESTADO_ANULADO);
				getCmbEstadoCartera().grabFocus();
			}
			else if(existeCruceDocumento(carteraDetalleColeccion) && !estado.equals(NOMBRE_ESTADO_ANULADO) && !tipoDocumento.getCodigo().equals("ANP") && !tipoDocumento.getCodigo().equals("ICC") && !tipoDocumento.getCodigo().equals("NCC")) {
				SpiritAlert.createAlert("No es posible Actualizar una Cartera cruzada, debe eliminarla e ingresarla nuevamente", SpiritAlert.WARNING);
			}
			else if(tipoDocumento.getCodigo().equals(TIPO_DOCUMENTO_ANTICIPO_POR_SALDO) && (BigDecimal.valueOf(saldoCartera).compareTo(new BigDecimal(0)) == 1) && !estado.equals(NOMBRE_ESTADO_ANULADO)){
				SpiritAlert.createAlert("Al cruzar un Anticipo por Saldo, debe quedar el saldo en cero !", SpiritAlert.WARNING);
			}
			else if (validateFields()) {
				boolean anticipoxSaldo = false;
				CarteraIf cartera = registrarCarteraForUpdate();
				Map<String, Object> parametrosEmpresa = new HashMap<String, Object>();
				parametrosEmpresa.put("usuario", Parametros.getUsuario());
				parametrosEmpresa.put("idEmpresa", Parametros.getIdEmpresa());
				parametrosEmpresa.put("idOficina", Parametros.getIdOficina());
				parametrosEmpresa.put("IVA", Parametros.getIVA());
				
				if(estado.equals(NOMBRE_ESTADO_ANULADO) && !tipoDocumento.getCodigo().equals("NPC") && !tipoDocumento.getCodigo().equals("NCI")){
					int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea Anular la Cartera?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if (opcion == JOptionPane.YES_OPTION) {						
						if(tipoDocumento.getCodigo().equals("CIN")){
							if(SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo("ANS").size() > 0){
								//TipoDocumentoIf tipoDocumentoANS = (TipoDocumentoIf)SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo("ANS").iterator().next();

								TipoDocumentoIf tipoDocumentoANS = null;
								Iterator itTDans = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo("ANS").iterator();
								if(itTDans.hasNext()) tipoDocumentoANS = (TipoDocumentoIf)itTDans.next();
								
								Map aMapANS = new HashMap();
								aMapANS.put("referenciaId", cartera.getId());
								aMapANS.put("tipodocumentoId", tipoDocumentoANS.getId());
								aMapANS.put("oficinaId", cartera.getOficinaId());
								if(SessionServiceLocator.getCarteraSessionService().findCarteraByQuery(aMapANS).size() > 0){
									CarteraIf carteraANS = (CarteraIf)SessionServiceLocator.getCarteraSessionService().findCarteraByQuery(aMapANS).iterator().next();
									int opcionANS = JOptionPane.showOptionDialog(null, "El Comprobante tiene un Anticipo con saldo de $" + formatoDecimal.format(carteraANS.getSaldo()) + ", ¿Desea anular el Comprobante y el Antcipo de todas formas?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
									if (opcionANS == JOptionPane.YES_OPTION) {
										anularCartera(anulado, anticipoxSaldo, cartera, parametrosEmpresa);
									}
								}else{
									anularCartera(anulado, anticipoxSaldo, cartera, parametrosEmpresa);		
								}
							}else{
								anularCartera(anulado, anticipoxSaldo, cartera, parametrosEmpresa);		
							}
						}else{
							anularCartera(anulado, anticipoxSaldo, cartera, parametrosEmpresa);		
						}									
					}				
				}else{
					boolean carteraActualizada = 
						SessionServiceLocator.getCarteraSessionService().actualizarCartera(cartera, carteraDetalleColeccion, mapaRegistrosSeleccionadosMap, 
								registrosAfectaToDetalleCarteraMap, carteraDetalleEliminadoColeccion, carteraActualizadaColeccion, 
								carteraAfectaEliminarColeccion,parametrosEmpresa);
					SpiritAlert.createAlert("Cartera actualizada con \u00e9xito", SpiritAlert.INFORMATION);
					if (carteraActualizada) {
						generarReporte(cartera, !anulado, anticipoxSaldo);
						generarCheques(cartera);					
					}
				}
				carteraDetalleColeccion.clear();
				clean();
				loadCombos();
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
	}

	private void anularCartera(boolean anulado, boolean anticipoxSaldo, CarteraIf cartera, Map<String, Object> parametrosEmpresa) throws GenericBusinessException {
		SessionServiceLocator.getCarteraSessionService().actualizarCartera(cartera, carteraDetalleColeccion, mapaRegistrosSeleccionadosMap, 
				registrosAfectaToDetalleCarteraMap, carteraDetalleEliminadoColeccion, carteraActualizadaColeccion,
				carteraAfectaEliminarColeccion,parametrosEmpresa);
		SpiritAlert.createAlert("Cartera anulada con \u00e9xito", SpiritAlert.INFORMATION);
		generarReporte(cartera, anulado, anticipoxSaldo);
	}
	
	public boolean existeCruceDocumento(Vector<CarteraDetalleIf> carteraDetalleColeccion){
		boolean existeCruceDocumento = false;
		try {
			for(CarteraDetalleIf carteraDetalle : carteraDetalleColeccion){
				Collection carteraAfectaColeccion = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(carteraDetalle.getId());
				if(!carteraAfectaColeccion.isEmpty()){
					existeCruceDocumento = true;
				}				
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return existeCruceDocumento;
	}
	
	private CarteraIf registrarCartera() throws GenericBusinessException {
		CarteraData data = new CarteraData();
		//java.util.Date fechaActual = new java.util.Date();
		java.util.Date fechaActual = getCmbFechaEmisionCartera().getDate();
		java.sql.Date fechaEmision = new java.sql.Date(fechaActual.getYear(), fechaActual.getMonth(), fechaActual.getDate());
		TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem();
		String unNumeroCartera = getNumeroCartera(fechaEmision, tipoDocumento.getCodigo());
		data.setCodigo(unNumeroCartera);
		data.setTipo(getCmbTipoCartera().getSelectedItem().toString().substring(0, 1));
		data.setOficinaId(Parametros.getIdOficina());
		data.setTipodocumentoId(tipoDocumento.getId());
		data.setFechaEmision(Utilitarios.fromSqlDateToTimestamp(fechaEmision));
		
		if (!getTxtReferenciaCartera().getText().equals(""))
			data.setReferenciaId(Long.parseLong(Utilitarios.removeDecimalFormat(getTxtReferenciaCartera().getText())));
		
		data.setPreimpreso(getTxtPreimpreso().getText());
		data.setClienteoficinaId(clienteOficinaIf.getId());
		if (getCmbVendedorCartera().getSelectedItem() != null)
			data.setVendedorId(((EmpleadoIf) getCmbVendedorCartera().getSelectedItem()).getId());
		data.setMonedaId(((MonedaIf) getCmbMonedaCartera().getSelectedItem()).getId());
		data.setEstado(getCmbEstadoCartera().getSelectedItem().toString().substring(0, 1));
		data.setValor(BigDecimal.valueOf(valorCartera));
		data.setSaldo(BigDecimal.valueOf(saldoCartera));
		data.setComentario(getTxtComentariosCartera().getText());
		data.setUsuarioId(((UsuarioIf) SessionServiceLocator.getUsuarioSessionService().findUsuarioByUsuario(Parametros.getUsuario().toLowerCase()).iterator().next()).getId());
		return data;
	}
	
	private String getNumeroCartera(java.sql.Date fechaCartera, String codigoTipoDocumento) {
		String codigo = "";

		try {
			EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
			String monthCartera = Utilitarios.getMonthFromDate(fechaCartera);
			String anioCartera = Utilitarios.getYearFromDate(fechaCartera);
			codigo = empresa.getCodigo() + "-";
			OficinaIf oficina = (OficinaIf) Parametros.getOficina();
			ServidorIf servidor = (oficina.getServidorId()!=null)?SessionServiceLocator.getServidorSessionService().getServidor(oficina.getServidorId()):null;
			if (servidor!=null)
				codigo += servidor.getCodigo() + "-";
			codigo += codigoTipoDocumento + "-";
			nuevaCodificacionActivada = (Double.parseDouble(anioCartera) <= 2008)?false:true;
			if (nuevaCodificacionActivada)
				codigo += monthCartera + "-";
			codigo += anioCartera + "-";
			return codigo;
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}

		return null;
	}
	
	public CarteraIf registrarCarteraForUpdate() {
		cartera.setTipo(getCmbTipoCartera().getSelectedItem().toString().substring(0, 1));
		cartera.setOficinaId(Parametros.getIdOficina());
		cartera.setTipodocumentoId(((TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem()).getId());
		// referencia
		cartera.setClienteoficinaId(clienteOficinaIf.getId());
		cartera.setPreimpreso(getTxtPreimpreso().getText());
		// usuario
		if (getCmbVendedorCartera().getSelectedItem() != null)
			cartera.setVendedorId(((EmpleadoIf) getCmbVendedorCartera().getSelectedItem()).getId());
		cartera.setMonedaId(((MonedaIf) getCmbMonedaCartera().getSelectedItem()).getId());
		cartera.setValor(BigDecimal.valueOf(valorCartera));
		cartera.setSaldo(BigDecimal.valueOf(saldoCartera));	
		cartera.setEstado(getCmbEstadoCartera().getSelectedItem().toString().substring(0, 1));
		cartera.setComentario(getTxtComentariosCartera().getText());
		return cartera;
	}
	
	public void delete() {
		/*try {
			if (getCarteraSessionService().eliminarCartera(cartera.getId() , Parametros.getUsuario())) {
				SpiritAlert.createAlert("Cartera eliminada con éxito", SpiritAlert.INFORMATION);
				this.clean();
				this.loadCombos();
				this.showSaveMode();
			}else{
				SpiritAlert.createAlert("No se ha podido eliminar la cartera porque su valor ya ha sido cancelado parcial o totalmente", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al eliminar la Cartera!", SpiritAlert.ERROR);
		}*/
	}
	
	private void generarCheques(CarteraIf cartera) throws GenericBusinessException {
		//cheques.clear();
		cheques = null;
		cheques = new ArrayList<String[]>();
		//carteraDetalleColeccionCheques.clear();
		carteraDetalleColeccionCheques = null;
		carteraDetalleColeccionCheques = new ArrayList<CarteraDetalleIf>();
		//carteraDetalleColeccionChequesImprimir.clear();
		carteraDetalleColeccionChequesImprimir = null;
		carteraDetalleColeccionChequesImprimir = new ArrayList<CarteraDetalleIf>();
		Collection carteraDetalleColeccion = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(cartera.getId());
		Iterator itCarteraDetalleColeccion = carteraDetalleColeccion.iterator();
		
		Vector<java.sql.Date> fechasColeccion = new Vector<java.sql.Date>();
		Vector<CuentaBancariaIf> cuentasBancosSeleccionadas = new Vector<CuentaBancariaIf>();
		Vector<String> numerosCheque = new Vector<String>();
		
		while (itCarteraDetalleColeccion.hasNext()) {
			CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) itCarteraDetalleColeccion.next();
			TipoPagoIf tipoPago = (carteraDetalle.getFormaPagoId() != null)?SessionServiceLocator.getTipoPagoSessionService().getTipoPago(carteraDetalle.getFormaPagoId()):null;
			if (tipoPago != null && tipoPago.getCodigo().equals(CODIGO_TIPO_PAGO_CHEQUE) && cartera.getTipo().equals("P")){
				carteraDetalleColeccionCheques.add(carteraDetalle);
				fechasColeccion.add(carteraDetalle.getFechaCartera());
				CuentaBancariaIf cuentaBancaria = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(carteraDetalle.getCuentaBancariaId());
				cuentasBancosSeleccionadas.add(cuentaBancaria);
				numerosCheque.add(carteraDetalle.getPreimpreso());
			}
		}
		
		CuentaBancariaIf cuentaBancariaVerificada;
		java.sql.Date fechaVerificada = new java.sql.Date(2000,1,1);
		Vector<Integer> indicesAgregados = new Vector<Integer>();
		int contador = 0;
		String numeroCheque = "";
		BigDecimal monto = new BigDecimal(0);		
		
		for (int i = 0; i < carteraDetalleColeccionCheques.size(); i++){
			CarteraDetalleIf carteraDetalleVerificada = (CarteraDetalleIf) carteraDetalleColeccionCheques.get(i);
			
			if(carteraDetalleVerificada != null){
				CarteraIf carteraVerificada = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleVerificada.getCarteraId());
				ClienteOficinaIf clienteOficinaVerificada = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(carteraVerificada.getClienteoficinaId());
				ClienteIf clienteVerificada = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaVerificada.getClienteId());
							
				fechaVerificada = fechasColeccion.get(i);
				cuentaBancariaVerificada = cuentasBancosSeleccionadas.get(i);
				numeroCheque = numerosCheque.get(i);
				contador = 0;
				monto = new BigDecimal(0);
				for (int j = 0; j < carteraDetalleColeccionCheques.size(); j++){
					CarteraDetalleIf carteraDetalleTemp = (CarteraDetalleIf) carteraDetalleColeccionCheques.get(j);
					
					if(carteraDetalleTemp != null){
						CarteraIf carteraTemp = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleTemp.getCarteraId());
						ClienteOficinaIf clienteOficinaTemp = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(carteraTemp.getClienteoficinaId());
						ClienteIf clienteTemp = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaTemp.getClienteId());
										
						if((clienteVerificada.getIdentificacion().equals(clienteTemp.getIdentificacion()))
							&& (cuentaBancariaVerificada != null) 
							&& (cuentaBancariaVerificada.getId().compareTo(cuentasBancosSeleccionadas.get(j).getId()) == 0)
							&& (numeroCheque.equals(numerosCheque.get(j)))
							&& (Utilitarios.compararFechas(fechaVerificada, fechasColeccion.get(j)) == 0)){
							
							contador++;
							if(contador == 1){
								monto = carteraDetalleVerificada.getValor();
							}
							else if(contador > 1){
								monto = monto.add(carteraDetalleTemp.getValor());
								if(!indicesAgregados.contains(j)){
									indicesAgregados.add(j);
								}					
							}										
						}
					}					
				}
				if(monto.compareTo(new BigDecimal(0)) == 1){
					if(!indicesAgregados.contains(i)){
						carteraDetalleVerificada.setValor(monto);
						carteraDetalleColeccionChequesImprimir.add(carteraDetalleVerificada);
						indicesAgregados.add(i);
					}
				}
			}			
		}		
		
		for(CarteraDetalleIf carteraDetalleCheque : carteraDetalleColeccionChequesImprimir){
			generarChequeData(cartera, carteraDetalleCheque);
		}
		
		if (cheques.size() > 0) {
			jdCheque = new JDCheque(Parametros.getMainFrame(), cheques);
			int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 600) / 2;
			int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
			jdCheque.setLocation(x, y);
			jdCheque.pack();
			jdCheque.setVisible(true);
		}
	}

	public void generarChequeData(CarteraIf cartera, CarteraDetalleIf carteraDetalle) {
		try {
			Double totalCheque = Double.valueOf(carteraDetalle.getValor().toString());
			if (totalCheque.compareTo(0D) != 0) {
				ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(cartera.getClienteoficinaId());
				ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
				OficinaIf oficina = (OficinaIf) Parametros.getOficina();
				CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
				String valorCheque = formatoDecimal.format(Double.valueOf(totalCheque));
				String parteDecimal = valorCheque.substring(valorCheque.indexOf('.'), valorCheque.length());
				Double dParteDecimal = 0.0;
				if (!parteDecimal.isEmpty())
					dParteDecimal = Double.valueOf(parteDecimal);
				String pagueseA = cliente.getRazonSocial();
				String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(valorCheque, dParteDecimal, new int[] { 70, 90 }, false, (MonedaIf) getCmbMonedaCartera().getSelectedItem());
				String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
				String cantidadLetrasSegundaLinea = cantidadLetras[1].replaceAll("  ","");
				String lugarFecha = ciudad.getNombre() + ", " + Utilitarios.getFechaUppercase(carteraDetalle.getFechaCartera());
				String lugarFechaPrimerReemplazo = lugarFecha.replaceFirst("-","DE");
				lugarFecha = lugarFechaPrimerReemplazo.replaceAll("-","DEL");
				String[] datosCheque = new String[5];
				datosCheque[0] = valorCheque;
				datosCheque[1] = pagueseA;
				datosCheque[2] = cantidadLetrasPrimeraLinea;
				datosCheque[3] = cantidadLetrasSegundaLinea;
				datosCheque[4] = lugarFecha;
				cheques.add(datosCheque);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		} 
	}
	
	public String[] obtenerCantidadEnLetras(String cantidadEnNumeros) {
		//System.out.println("Valor pasado : " + cantidadEnNumeros);
		int[] renglones = new int[] { 60, 70 };
		RMCantidadEnLetras.setRenglones(renglones);
		RMCantidadEnLetras.setPalabra_cero("CE|RO");
		RMCantidadEnLetras.setGenero_unidad(2);
		RMCantidadEnLetras.setPrefijo_inicio("");
		RMCantidadEnLetras.setSufijo_final("");
		RMCantidadEnLetras.setCantidad_decimales(2);
		RMCantidadEnLetras.setTraduce_decimales(true);
		//RMCantidadEnLetras.setCaracter_proteccion('*');
		RMCantidadEnLetras.setSufijo_decimales("CEN|TA|VOS");
		RMCantidadEnLetras.setSufijo_enteros("DÓ|LA|RES");
		String[] texto = RMCantidadEnLetras.getTexto(Utilitarios.removeDecimalFormat(cantidadEnNumeros));
		
		/*if (!texto[0].contains("CENTAVOS")) {
		 String substringCaracteresNumerosEnLetras = texto[0].substring(0, texto[0].indexOf('*')); 
		 int cantidadCaracteresNumerosEnLetras = substringCaracteresNumerosEnLetras.length(); 
		 String substringCaracteresProteccion = texto[0].substring(texto[0].indexOf('*'), texto[0].length() - 1);
		 int cantidadCaracteresProteccion = substringCaracteresProteccion.length();
		 String substringCeroCentavos = "CON CERO CENTAVOS";
		 int cantidadCaracteresCeroCentavos = substringCeroCentavos.length();  
		 }*/
		
		return texto;
	}
	
	public void report() {
		try {
			if(cartera != null){
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)getCmbTipoDocumento().getSelectedItem();
				String estado = (String)getCmbEstadoCartera().getSelectedItem();
				boolean anulado = true;
				boolean anticipoxSaldo = false;
				if((tipoDocumento.getCodigo().equals("CEG") || tipoDocumento.getCodigo().equals("ANP")) && estado.equals(NOMBRE_ESTADO_ANULADO)){
					generarReporte(cartera, anulado, anticipoxSaldo);
				}else if (tipoDocumento.getCodigo().equals("CIN")){
					anticipoxSaldo = true;
					generarReporte(cartera, !anulado, anticipoxSaldo);
				}else{
					generarReporte(cartera, !anulado, anticipoxSaldo);
				}
				generarCheques(cartera);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void generarReporte(CarteraIf cartera, boolean anulado, boolean anticipoxSaldo) {
		TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(cartera.getTipodocumentoId());
		boolean anticipo = true;
		
		if (tipoDocumento.getCodigo().equals("CIN") || tipoDocumento.getCodigo().equals("NNC") || tipoDocumento.getCodigo().equals(TIPO_DOCUMENTO_ANTICIPO_POR_SALDO))
			generarReporteComprobanteIngreso(cartera, anticipoxSaldo);
		else if (tipoDocumento.getCodigo().equals("CEG"))
			generarReporteComprobanteEgreso(cartera, !anticipo, anulado);
		else if (tipoDocumento.getCodigo().equalsIgnoreCase("ANP"))
			generarReporteComprobanteEgreso(cartera, anticipo, anulado);
	}
	
	public void generarReporteComprobanteIngreso(CarteraIf cartera, boolean anticipoxSaldo) {
		try {
			AsientoIf asientoAutomatico = null;
			if(!cartera.getEstado().equals(ESTADO_ANULADO)){
				Map asientoMap = new HashMap();
				asientoMap.put("tipoDocumentoId",cartera.getTipodocumentoId());
				asientoMap.put("transaccionId",cartera.getId());
				if(!SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(asientoMap).isEmpty()){
					asientoAutomatico = (AsientoIf)SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(asientoMap).iterator().next();
				}
			}
			
			comprobanteIngresoColeccion.clear();
			Collection carteraDetalleColeccion = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(cartera.getId());
			Iterator carteraDetalleIterator = carteraDetalleColeccion.iterator();
			while (carteraDetalleIterator.hasNext()) {
				CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) carteraDetalleIterator.next();
				Collection carteraAfectaColeccion = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(carteraDetalleIf.getId());
				Iterator carteraAfectaIterator = carteraAfectaColeccion.iterator();
				while (carteraAfectaIterator.hasNext()) {
					CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf) carteraAfectaIterator.next();					
					CarteraDetalleIf carteraDetalleFacturaAfectada = SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(carteraAfectaIf.getCarteraafectaId());
					CarteraIf carteraFacturaAfectada = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleFacturaAfectada.getCarteraId());
					TipoDocumentoIf tipoDocumentoCarteraAfectada = mapaTipoDocumento.get(carteraFacturaAfectada.getTipodocumentoId());
					if(!tipoDocumentoCarteraAfectada.getCodigo().equals("NCI")){
						comprobanteIngresoColeccion.add(agregarDetalleComprobanteIngreso(carteraDetalleIf, carteraAfectaIf, anticipoxSaldo));
					}
					if(tipoDocumentoCarteraAfectada.getCodigo().equals("NPC")){
						anticipoxSaldo = false;
					}
				}				
			}
			//Veo si hay un Anticipo por Saldo
			if(anticipoxSaldo && !SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByReferencia(cartera.getId().toString()).isEmpty()){
				Collection carterasDetalle = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByReferencia(cartera.getId().toString());
				Iterator carterasDetalleIt = carterasDetalle.iterator();
				while(carterasDetalleIt.hasNext()){
					CarteraDetalleIf carteraDetalleAnticipo = (CarteraDetalleIf)carterasDetalleIt.next();
					if(SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalleAnticipo.getDocumentoId()).getCodigo().equals("ANSA")){
						comprobanteIngresoColeccion.add(agregarDetalleComprobanteIngreso(carteraDetalleAnticipo, null, anticipoxSaldo));
					}
				}				
			}			
			
			if (comprobanteIngresoColeccion.size() > 0) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/cartera/RPComprobanteIngreso.jasper";
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("CARTERA").iterator().next();
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					parametrosMap.put("codigo", cartera.getCodigo());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("usuario", Parametros.getUsuario().toLowerCase());
					UsuarioIf usuario = SessionServiceLocator.getUsuarioSessionService().getUsuario(cartera.getUsuarioId());
					EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(usuario.getEmpleadoId());
					parametrosMap.put("elaborado", empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0]);
					
					String fechaActual = Utilitarios.getStringDateFromDate(cartera.getFechaEmision());
					if(mapaTipoDocumento.get(cartera.getTipodocumentoId()).getCodigo().equals(TIPO_DOCUMENTO_ANTICIPO_POR_SALDO)){
						//Si es un anticipo por saldo que se esta cruzando, entonces la fecha del reporte debe ser la actual.
						fechaActual = Utilitarios.getStringDateFromDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
					}
					
					String day = fechaActual.split("-")[0];
					String month = fechaActual.split("-")[1];
					String year = fechaActual.split("-")[2];
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					parametrosMap.put("emitido", fechaEmision);
					ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(cartera.getClienteoficinaId());
					ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
					parametrosMap.put("cliente", cliente.getRazonSocial());
					String valorComprobante = formatoDecimal.format(Double.valueOf(cartera.getSaldo().subtract(cartera.getValor()).abs().toString()));
					String parteDecimal = valorComprobante.substring(valorComprobante.indexOf('.'), valorComprobante.length());
					Double dParteDecimal = 0.0;
					if (!parteDecimal.isEmpty())
						dParteDecimal = Double.valueOf(parteDecimal);
					String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(valorComprobante, dParteDecimal, new int[] { 90 }, false, (MonedaIf) getCmbMonedaCartera().getSelectedItem());
					String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
					parametrosMap.put("cantidad", cantidadLetrasPrimeraLinea);
					if(cartera.getComentario() != null){
						parametrosMap.put("concepto", cartera.getComentario());
					}else{
						parametrosMap.put("concepto", "");
					}
					parametrosMap.put("codigoAsiento", (asientoAutomatico != null ? asientoAutomatico.getNumero() : "N/A"));
	
					parametrosMap.put("valorTotal", formatoDecimal.format(cartera.getSaldo().subtract(cartera.getValor()).abs().doubleValue()));
					ReportModelImpl.processReport(fileName, parametrosMap, comprobanteIngresoColeccion, true);
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
	}
	
	public void generarReporteComprobanteEgreso(CarteraIf cartera, boolean anticipo, boolean anulado) {
		try {
			AsientoIf asientoAutomatico = null;
			if(!cartera.getEstado().equals(ESTADO_ANULADO)){
				Map asientoMap = new HashMap();
				asientoMap.put("tipoDocumentoId",cartera.getTipodocumentoId());
				asientoMap.put("transaccionId",cartera.getId());
				if(!SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(asientoMap).isEmpty()){
					asientoAutomatico = (AsientoIf)SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(asientoMap).iterator().next();	
				}				
			}
			
			comprobanteEgresoColeccion.clear();
			Collection carteraDetalleColeccion = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(cartera.getId());
			Iterator carteraDetalleIterator = carteraDetalleColeccion.iterator();
			
			ClienteOficinaIf proveedorOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(cartera.getClienteoficinaId());
			ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
			String valorComprobante = "";
			if(anticipo && anulado){
				valorComprobante = formatoDecimal.format(Double.valueOf(cartera.getSaldo().subtract(cartera.getValor()).abs().toString()));
			}else if(anticipo){
				valorComprobante = formatoDecimal.format(Double.valueOf(cartera.getValor().abs().toString()));
			}else{
				valorComprobante = formatoDecimal.format(Double.valueOf(cartera.getSaldo().subtract(cartera.getValor()).abs().toString()));
			}
			String parteDecimal = valorComprobante.substring(valorComprobante.indexOf('.'), valorComprobante.length());
			Double dParteDecimal = 0.0;
			if (!parteDecimal.isEmpty())
				dParteDecimal = Double.valueOf(parteDecimal);
			String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(valorComprobante, dParteDecimal, new int[] { 90 }, false, (MonedaIf) getCmbMonedaCartera().getSelectedItem());
			String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
			String fecha = cartera.getFechaEmision().toString();
			String year = fecha.substring(0,4);
			String month = fecha.substring(5,7);
			String day = fecha.substring(8,10);
			String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
			
			while (carteraDetalleIterator.hasNext()) {
				CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) carteraDetalleIterator.next();
				if(anticipo){
					ComprobanteEgresoData comprobanteEgresoData = agregarDetalleComprobanteAnticipo(carteraDetalleIf, anulado);
					comprobanteEgresoData.setFechaEmision(fechaEmision);
					comprobanteEgresoData.setProveedor(proveedor.getRazonSocial());
					if(anulado){
						comprobanteEgresoData.setValorTotal("0.00");
						comprobanteEgresoData.setCantidad("CERO 00/100 DÓLARES");
					}else{
						comprobanteEgresoData.setValorTotal(valorComprobante);
						comprobanteEgresoData.setCantidad(cantidadLetrasPrimeraLinea);
					}
					comprobanteEgresoData.setConcepto(cartera.getComentario());
					comprobanteEgresoData.setCodigoAsiento(asientoAutomatico != null ? asientoAutomatico.getNumero() : "N/A");
					comprobanteEgresoData.setCodigo(cartera.getCodigo());
					comprobanteEgresoColeccion.add(comprobanteEgresoData);
				}else if(anulado){
					ComprobanteEgresoData comprobanteEgresoData = agregarDetalleComprobanteEgreso(carteraDetalleIf, null, anulado);
					comprobanteEgresoData.setFechaEmision(fechaEmision);
					comprobanteEgresoData.setProveedor(proveedor.getRazonSocial());
					comprobanteEgresoData.setValorTotal("0.00");
					comprobanteEgresoData.setCantidad("CERO 00/100 DÓLARES");
					comprobanteEgresoData.setConcepto(cartera.getComentario());
					comprobanteEgresoData.setCodigoAsiento(asientoAutomatico != null ? asientoAutomatico.getNumero() : "N/A");
					comprobanteEgresoData.setCodigo(cartera.getCodigo());
					comprobanteEgresoColeccion.add(comprobanteEgresoData);
				}else{
					Collection carteraAfectaColeccion = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(carteraDetalleIf.getId());
					Iterator carteraAfectaIterator = carteraAfectaColeccion.iterator();
					while (carteraAfectaIterator.hasNext()) {
						CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf) carteraAfectaIterator.next();
						ComprobanteEgresoData comprobanteEgresoData = agregarDetalleComprobanteEgreso(carteraDetalleIf, carteraAfectaIf, anulado);
						comprobanteEgresoData.setFechaEmision(fechaEmision);
						comprobanteEgresoData.setProveedor(proveedor.getRazonSocial());
						comprobanteEgresoData.setConcepto(cartera.getComentario());
						comprobanteEgresoData.setCodigoAsiento(asientoAutomatico != null ? asientoAutomatico.getNumero() : "N/A");
						comprobanteEgresoData.setValorTotal(valorComprobante);
						comprobanteEgresoData.setCantidad(cantidadLetrasPrimeraLinea);
						comprobanteEgresoData.setCodigo(cartera.getCodigo());
						comprobanteEgresoColeccion.add(comprobanteEgresoData);
					}
				}				
			}
			
			if (comprobanteEgresoColeccion.size() > 0) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/cartera/RPComprobanteEgreso.jasper";
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("CARTERA").iterator().next();
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					parametrosMap.put("codigo", cartera.getCodigo());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("usuario", Parametros.getUsuario().toLowerCase());
					ReportModelImpl.processReport(fileName, parametrosMap, comprobanteEgresoColeccion, true);
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
	}
	
	private ComprobanteIngresoData agregarDetalleComprobanteIngreso(CarteraDetalleIf carteraDetalle, CarteraAfectaIf carteraAfecta, boolean anticipoxSaldo) {
		ComprobanteIngresoData data = new ComprobanteIngresoData();
		Calendar fechaActual = new GregorianCalendar();
		
		try {
			if(anticipoxSaldo && (carteraAfecta == null)){
				String cuentaBancaria = "N/A";
				String bancoDeposito = "";
				if(carteraDetalle.getCuentaBancariaId() != null){
					CuentaBancariaIf cuentaBancariaIf = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(carteraDetalle.getCuentaBancariaId());
					cuentaBancaria = cuentaBancariaIf.getCuenta();
					bancoDeposito = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancariaIf.getBancoId()).getNombre();
				}
				data.setBancoDeposito(bancoDeposito);
				data.setNumeroCuenta("\nCTA. # " + cuentaBancaria);
				data.setBancoCheque("");
				data.setNumeroCheque("\nCHQ. # " + (carteraDetalle.getPreimpreso()!=null?carteraDetalle.getPreimpreso():"N/A"));
				data.setFechaFactura("N/A");
				data.setNumeroFactura("N/A");
				String detalle = "";				
				detalle = "ANTICIPO POR SALDO";
				data.setDetalle(detalle);
				data.setValor(formatoDecimal.format(carteraDetalle.getValor().doubleValue()));
				data.setSaldo(formatoDecimal.format(carteraDetalle.getValor().doubleValue()));
			}else{
				DocumentoIf documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalle.getDocumentoId());
				CarteraDetalleIf carteraDetalleFacturaAfectada = SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(carteraAfecta.getCarteraafectaId());
				CarteraIf carteraFacturaAfectada = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleFacturaAfectada.getCarteraId());
				TipoDocumentoIf tipoDocumentoCarteraAfectada = mapaTipoDocumento.get(carteraFacturaAfectada.getTipodocumentoId());
				if (tipoDocumentoCarteraAfectada.getCodigo().equals("FAC") || tipoDocumentoCarteraAfectada.getCodigo().equals("FAR") || tipoDocumentoCarteraAfectada.getCodigo().equals("FAE") || tipoDocumentoCarteraAfectada.getCodigo().equals("FCO") || tipoDocumentoCarteraAfectada.getCodigo().equals("VTA")) {
					FacturaIf factura = (carteraFacturaAfectada.getReferenciaId() != null)?SessionServiceLocator.getFacturaSessionService().getFactura(carteraFacturaAfectada.getReferenciaId()):null;
					if (documento.getRetencionRenta().equals("N") && documento.getRetencionIva().equals("N")) {
						if(documento.getCheque().equals("S")){
							CuentaBancariaIf cuentaBancaria = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(carteraDetalle.getCuentaBancariaId());
							BancoIf bancoDeposito = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancaria.getBancoId());
							data.setBancoDeposito(bancoDeposito.getNombre());
							data.setNumeroCuenta("\nCTA. # " + cuentaBancaria.getCuenta());
						}else if(Utilitarios.compararFechas(new java.sql.Date(fechaActual.getTime().getTime()), new java.sql.Date(carteraDetalle.getFechaCartera().getTime())) == -1){
							data.setBancoDeposito("CHEQUE POSTFECHADO:");
							data.setNumeroCuenta("\n"+Utilitarios.getFechaCortaUppercase(carteraDetalle.getFechaCartera()));
						}else{
							data.setBancoDeposito("");
							data.setNumeroCuenta("");
						}
						data.setBancoCheque(carteraDetalle.getReferencia()!=null?carteraDetalle.getReferencia():"N/A");
						data.setNumeroCheque(carteraDetalle.getPreimpreso()!=null?"\nCHQ. # " + carteraDetalle.getPreimpreso():"N/A");
					} else {
						
						if((carteraDetalle.getSriClienteRetencionId() != null) && (documento.getCodigo().equals("RERC") || documento.getCodigo().equals("REIC"))){
							SriClienteRetencionIf sriClienteRetencion = SessionServiceLocator.getSriClienteRetencionSessionService().getSriClienteRetencion(carteraDetalle.getSriClienteRetencionId());
							data.setBancoDeposito(documento.getNombre().replaceAll(" CLIENTE","") + " " + sriClienteRetencion.getPorcentajeRetencion() + "%");
						}else{
							data.setBancoDeposito(documento.getNombre().replaceAll(" CLIENTE",""));
						}
												
						data.setBancoCheque(carteraDetalle.getPreimpreso() != null?"PRE. # " + carteraDetalle.getPreimpreso():"");
						data.setNumeroCheque(carteraDetalle.getAutorizacion() != null?"\nAUT. # " + carteraDetalle.getAutorizacion():"");
					}
					
					data.setFechaFactura(Utilitarios.getFechaCortaUppercase((factura != null)?factura.getFechaFactura():carteraFacturaAfectada.getFechaEmision()));
					String numeroFactura = (factura != null)?factura.getPreimpresoNumero():"";
					if (numeroFactura != null && numeroFactura.length() >= 15)
						numeroFactura = numeroFactura.substring(8, numeroFactura.length());
					else
						numeroFactura = carteraFacturaAfectada.getPreimpreso();
					data.setNumeroFactura(numeroFactura);
					data.setDetalle((factura != null)?factura.getObservacion():carteraFacturaAfectada.getComentario());
					
				} else if (tipoDocumentoCarteraAfectada.getCodigo().equals("NPC") || tipoDocumentoCarteraAfectada.getCodigo().equals("CIP")) {
					if (documento.getRetencionRenta().equals("N") && documento.getRetencionIva().equals("N")) {
						if(documento.getCheque().equals("S")){
							CuentaBancariaIf cuentaBancaria = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(carteraDetalle.getCuentaBancariaId());
							BancoIf bancoDeposito = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancaria.getBancoId());
							data.setBancoDeposito(bancoDeposito.getNombre());
							data.setNumeroCuenta("\nCTA. # " + cuentaBancaria.getCuenta());
						}
						data.setBancoCheque(carteraDetalle.getReferencia()!=null?carteraDetalle.getReferencia():"N/A");
						data.setNumeroCheque(carteraDetalle.getPreimpreso()!=null?"\nCHQ. # " + carteraDetalle.getPreimpreso():"N/A");
					} else {
						data.setBancoDeposito(documento.getNombre().replaceAll(" CLIENTE",""));
						data.setBancoCheque(carteraDetalle.getPreimpreso() != null?"PRE. # " + carteraDetalle.getPreimpreso():"");
						data.setNumeroCheque(carteraDetalle.getAutorizacion() != null?"\nAUT. # " + carteraDetalle.getAutorizacion():"");
					}
					data.setFechaFactura("N/A");
					data.setNumeroFactura("N/A");
					String detalle = "";
					
					if (tipoDocumentoCarteraAfectada.getCodigo().equals("NPC"))
						detalle = "NIVELACIÓN POSITIVA";
					else
						detalle = "COBRO A EMPLEADO";
					data.setDetalle(detalle);
				}
				
				data.setValor(formatoDecimal.format(carteraAfecta.getValor().doubleValue()));
				data.setSaldo(formatoDecimal.format(carteraFacturaAfectada.getSaldo().doubleValue()));
			}
			
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		
		return data;
	}
	
	private ComprobanteEgresoData agregarDetalleComprobanteAnticipo(CarteraDetalleIf carteraDetalle, boolean anulado) {
		ComprobanteEgresoData data = new ComprobanteEgresoData();
		
		try {
			DocumentoIf documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalle.getDocumentoId());
			if (documento.getRetencionRenta().equals("N") && documento.getRetencionIva().equals("N")) {
				if(carteraDetalle.getCuentaBancariaId() != null){
					CuentaBancariaIf cuentaBancaria = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(carteraDetalle.getCuentaBancariaId());
					BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancaria.getBancoId());
					data.setBanco(banco.getNombre());
					data.setNumeroCuenta(cuentaBancaria.getCuenta());
					data.setNumeroCheque(carteraDetalle.getPreimpreso());
				}				
			}
			
			data.setFechaCompra("N/A");
			data.setCodigoCompra("");
			
			if(anulado){
				data.setPreimpresoFactura(" ANULADO");
				data.setDetalle("");
				data.setValor("0.00");
				data.setSaldo("0.00");
			}else{
				data.setPreimpresoFactura("N/A\n");
				if(carteraDetalle.getReferencia().length()>50){
					data.setDetalle(carteraDetalle.getReferencia().substring(0,50));
				}else{
					data.setDetalle(carteraDetalle.getReferencia());
				}
				data.setValor(formatoDecimal.format(carteraDetalle.getValor().doubleValue()));
				data.setSaldo(formatoDecimal.format(new Double(0)));
			}			
			
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		
		return data;
	}
	
	private ComprobanteEgresoData agregarDetalleComprobanteEgreso(CarteraDetalleIf carteraDetalle, CarteraAfectaIf carteraAfecta, boolean anulado) {
		ComprobanteEgresoData data = new ComprobanteEgresoData();
		
		try {
			DocumentoIf documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalle.getDocumentoId());
			CarteraIf carteraAfectada = null;
			CompraIf compra = null;
			if(anulado){
				//No entra si el documento es Cruce Factura Proveedor (falta mejor implementacion)
				if(!documento.getCodigo().equals("CFP")){
					Long carteraCompraId = Long.valueOf(carteraDetalle.getReferencia());
					carteraAfectada = SessionServiceLocator.getCarteraSessionService().getCartera(carteraCompraId);
					TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(carteraAfectada.getTipodocumentoId());
					if (tipoDocumento.getCodigo().equals("COM") || tipoDocumento.getCodigo().equals("COR") || tipoDocumento.getCodigo().equals("LIC") || tipoDocumento.getCodigo().equals("COI") || tipoDocumento.getCodigo().equals("CNV") || tipoDocumento.getCodigo().equals("SAE"))
						compra = SessionServiceLocator.getCompraSessionService().getCompra(carteraAfectada.getReferenciaId());
				}
			}else{
				CarteraDetalleIf carteraDetalleCompraAfectada = SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(carteraAfecta.getCarteraafectaId());
				carteraAfectada = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleCompraAfectada.getCarteraId());
				TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(carteraAfectada.getTipodocumentoId());
				
				System.out.println("tipo documento: tipodoc"+tipoDocumento.getCodigo());
				
				if (tipoDocumento.getCodigo().equals("COM") || tipoDocumento.getCodigo().equals("COR") || tipoDocumento.getCodigo().equals("LIC") || tipoDocumento.getCodigo().equals("COI") || tipoDocumento.getCodigo().equals("CNV") || tipoDocumento.getCodigo().equals("SAE"))
					
					System.out.println("carteraAfectada.getReferenciaId()::"+carteraAfectada.getReferenciaId());
					compra = SessionServiceLocator.getCompraSessionService().getCompra(carteraAfectada.getReferenciaId());
			}			
			
			if (documento.getRetencionRenta().equals("N") && documento.getRetencionIva().equals("N") && documento.getCheque().equals("S")) {
				CuentaBancariaIf cuentaBancaria = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(carteraDetalle.getCuentaBancariaId());
				BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancaria.getBancoId());
				data.setBanco(banco.getNombre());
				data.setNumeroCuenta(cuentaBancaria.getCuenta());
				if(Utilitarios.compararFechas(new java.sql.Date(carteraDetalle.getFechaCreacion().getTime()), new java.sql.Date(carteraDetalle.getFechaCartera().getTime())) == -1){
					data.setNumeroCheque(carteraDetalle.getPreimpreso() + " [POSTFECHADO: " + Utilitarios.getFechaCortaUppercase(carteraDetalle.getFechaCartera()) + "]");
				}else if (carteraDetalle.getPreimpreso() != null){
					data.setNumeroCheque(carteraDetalle.getPreimpreso());
				}else{
					data.setNumeroCheque("D/B");
				}
			} else if (documento.getRetencionRenta().equals("N") && documento.getRetencionIva().equals("N") && documento.getCheque().equals("N")) {
				data.setBanco("PAGO EN EFECTIVO");
				data.setNumeroCuenta("N/A");
				data.setNumeroCheque("N/A");
			}
			
			//No entra si el documento es Cruce Factura Proveedor
			if(!documento.getCodigo().equals("CFP")){
				data.setFechaCompra(Utilitarios.getFechaCortaUppercase((compra!=null)?compra.getFecha():carteraAfectada.getFechaEmision()));
				data.setCodigoCompra((compra!=null)?compra.getCodigo():carteraAfectada.getCodigo());
			}else{
				data.setFechaCompra("N/A");
				data.setCodigoCompra("N/A");
			}			
						
			if(anulado){
				data.setPreimpresoFactura(" ANULADO");
				data.setDetalle("");
				data.setValor("0.00");
				data.setSaldo("0.00");
			}else{
				data.setPreimpresoFactura((compra!=null)?" F# " + compra.getPreimpreso():"N/A");
				if (compra != null) {
					data.setDetalle(compra.getObservacion().length()>52?compra.getObservacion().substring(0,52):compra.getObservacion());
				} else {
					data.setDetalle(carteraAfectada.getComentario().length()>52?carteraAfectada.getComentario().substring(0,52):carteraAfectada.getComentario());
				}
				data.setValor(formatoDecimal.format(carteraAfecta.getValor().doubleValue()));
				data.setSaldo(formatoDecimal.format(carteraAfectada.getSaldo().doubleValue()));
			}			
			
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		
		return data;
	}
	
	public void refresh() {
		cargarComboVendedorCartera();
		cargarComboLineaDetalle();
		cargarComboTipoDocumento();
		cargarComboDocumentoDetalle();
		cargarComboBancoDetalle();
		cargarComboMonedaCartera();
		cargarMapaTipoDocumento();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
		/*CarteraIf cartera = (CarteraIf) DeepCopy.copy(carteraAnulada);
		Vector<CarteraDetalleIf> carteraDetalleColeccion = (Vector<CarteraDetalleIf>) DeepCopy.copy(this.carteraDetalleColeccion);
		Map<Long,Map> mapaRegistrosSeleccionadosMap = (Map<Long,Map>) DeepCopy.copy(this.mapaRegistrosSeleccionadosMap);
		Map registrosAfectaToDetalleCarteraMap = (Map) DeepCopy.copy(this.registrosAfectaToDetalleCarteraMap);
		getCarteraSessionService().actualizarCartera(cartera, carteraDetalleColeccion, mapaRegistrosSeleccionadosMap, registrosAfectaToDetalleCarteraMap, carteraDetalleEliminadoColeccion, carteraActualizadaColeccion, carteraAfectaEliminarColeccion,parametrosEmpresa);		
		getCarteraSessionService().procesarCartera(cartera, carteraDetalleColeccion, mapaRegistrosSeleccionadosMap, registrosAfectaToDetalleCarteraMap, parametrosEmpresa, ANTICIPO_NIVELACION_CLIENTE);*/
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	/*public void switchTab() {
		int selectedTab = this.getJtpCartera().getSelectedIndex();
		int tabCount = this.getJtpCartera().getTabCount();
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpCartera().setSelectedIndex(selectedTab);
	}*/
	
	public void addDetail() {
		if (getJtpCartera().getSelectedIndex() == 1)
			agregarCarteraDetalle();
	}
	
	public void updateDetail() {
		if (getJtpCartera().getSelectedIndex() == 1)
			actualizarCarteraDetalle();
	}
	
	public void deleteDetail() {
		if (getJtpCartera().getSelectedIndex() == 1)
			eliminarCarteraDetalle();
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if ("".equals(getTxtCodigoCartera().getText()) == false)
			aMap.put("codigo", getTxtCodigoCartera().getText()+"%");
		else
			aMap.put("codigo", "%");
		
		if (getCmbEstadoCartera().getSelectedItem() != null)
			aMap.put("estado", getCmbEstadoCartera().getSelectedItem().toString().substring(0, 1));
		
		if (!getTxtPreimpresoDetalle().getText().equals(""))
			aMap.put("preimpreso", getTxtPreimpresoDetalle().getText()+"%");
		
		if (getCmbTipoDocumento().getSelectedItem() != null)
			aMap.put("tipodocumentoId",((TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem()).getId());
		
		if (getCmbDocumentoDetalle().getSelectedItem() != null)
			aMap.put("documentoId",((DocumentoIf) getCmbDocumentoDetalle().getSelectedItem()).getId());
				
		if (clienteOficinaIf != null)
			aMap.put("clienteoficinaId", clienteOficinaIf.getId());
		
		if (this.getCmbFechaEmisionCartera().getSelectedItem() != null)
			aMap.put("fechaEmision", new java.sql.Date(getCmbFechaEmisionCartera().getDate().getYear(), getCmbFechaEmisionCartera().getDate().getMonth(), getCmbFechaEmisionCartera().getDate().getDate()));
		
		aMap.put("oficinaId", Parametros.getIdOficina());
		
		return aMap;
	}
	
	public void find() {
		try {
			if (!("".equals(getTxtCorporacionCartera().getText())) && "".equals(getTxtClienteCartera().getText()))
				SpiritAlert.createAlert("Seleccione el cliente por el cual se va a realizar la búsqueda", SpiritAlert.INFORMATION);
			else {
				Long clienteId = 0L, empresaId = 0L;
				
				if (clienteIf != null){
					clienteId = clienteIf.getId();
				}
				
				Long moduloId = ((ModuloIf) SessionServiceLocator.getModuloSessionService().findModuloByCodigo(CODIGO_MODULO_CARTERA).iterator().next()).getId();
				
				if (Long.valueOf(Parametros.getIdEmpresa()).compareTo(0L) != 0){
					empresaId = Long.valueOf(Parametros.getIdEmpresa());
				}
				
				Map mapa = buildQuery();
				int tamanoLista = SessionServiceLocator.getCarteraSessionService().getCarteraListSize(mapa, clienteId, moduloId,empresaId);
				if (tamanoLista > 0) {
					CarteraCriteria carteraCriteria = new CarteraCriteria(clienteId, moduloId, empresaId);
					carteraCriteria.setResultListSize(tamanoLista);
					carteraCriteria.setQueryBuilded(mapa);
					
					Vector<Integer> anchosColumnasVector = new Vector<Integer>();
					anchosColumnasVector.addElement(110);
					anchosColumnasVector.addElement(50);
					//anchosColumnasVector.addElement(125);
					anchosColumnasVector.addElement(140);
					anchosColumnasVector.addElement(70);
					anchosColumnasVector.addElement(35);
					anchosColumnasVector.addElement(40);
					anchosColumnasVector.addElement(40);
					
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), carteraCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchosColumnasVector, null);
					
					if (popupFinder.getElementoSeleccionado() != null){
						getSelectedObject();
					}
				} else {
					SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en la búsqueda de información", SpiritAlert.ERROR);
		}
	}
	
	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigoCartera().getText())
				&& "".equals(this.getTxtPreimpreso().getText())
				&& "".equals(this.getTxtCorporacionCartera().getText())
				&& "".equals(this.getTxtClienteCartera().getText())
				&& getCmbFechaEmisionCartera().getDate() == null
				&& getCmbTipoDocumento().getSelectedItem() == null
				&& getCmbEstadoCartera().getSelectedItem() == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void clean() {
		cleanTable();
		this.getTxtCodigoCartera().setText("");
		this.getCmbTipoCartera().setSelectedIndex(0);
		this.getCmbEstadoCartera().setSelectedItem(0);
		this.getCmbVendedorCartera().removeAllItems();
		this.getCmbTipoDocumento().removeAllItems();
		this.getCmbMonedaCartera().removeAllItems();
		this.getCmbDocumentoDetalle().removeAllItems();
		this.getCmbBancoDetalle().removeAllItems();
		this.getCmbLineaDetalle().removeAllItems();
		
		this.getTxtReferenciaCartera().setText("");
		this.getTxtPreimpreso().setText("");
		this.getTxtCorporacionCartera().setText("");
		this.getTxtClienteCartera().setText("");
		this.getTxtClienteOficinaCartera().setText("");
		this.getTxtOficinaCartera().setText("");
		this.getTxtValorCartera().setText("");
		this.getTxtSaldoCartera().setText("");
		this.getTxtFechaCambioEstadoCartera().setText("");
		this.getTxtComentariosCartera().setText("");
		this.getTxtDepositoDetalle().setText("");
		this.getTxtSecuencialDetalle().setText("");
		this.getTxtFechaCreacionDetalle().setText("");
		this.getTxtFechaVencimientoDetalle().setText("");
		this.getTxtFechaActualizacionDetalle().setText("");
		this.getTxtCarteraDetalle().setText("");
		this.getCmbFechaCarteraDetalle().removeActionListener(oActionListenerCmbFecha);
		Calendar currentDate = Calendar.getInstance();
		this.getCmbFechaCarteraDetalle().setCalendar(currentDate);
		getCmbPorcentaje().setEnabled(false);		
		cartera = null;
		corporacionIf = null;
		clienteIf = null;
		clienteOficinaIf = null;
		tipoDocumentoIf = null;
		tipoPagoIf = null;
		monedaIf = null;
		documentoIf = null;
		secuencialDetalle = 1;
		filaSeleccionadaTablaDetalle = -1;
		valorCartera = 0D;
		saldoCartera = 0D;
	}
	
	private void cleanTable() {
		carteraActualizadaColeccion.clear();
		carteraAfectaEliminarColeccion.clear();			
		carteraDetalleEliminadoColeccion.clear();
		if(getTblDetalle().getRowCount() > 0) {
			DefaultTableModel model = (DefaultTableModel) getTblDetalle().getModel();
			for(int i= this.getTblDetalle().getRowCount();i>0;--i)
				model.removeRow(i-1);
		}
		registrosAfectaMap = new HashMap();
		registrosAfectaToDetalleCarteraMap = new HashMap();
		registrosDetallesToDetalleCarteraMap = new HashMap();
		mapaRegistrosSeleccionadosMap = new HashMap();
		mapaRegistrosSeleccionadosMapTemp = new HashMap();
		secuencialDetalle = 1;
		carteraDetalleColeccion = new Vector();
		this.getTxtReferenciaDetalle().setText("");
		this.getTxtPreimpresoDetalle().setText("");
		this.getTxtAutorizacionDetalle().setText("");
		this.getTxtValorDetalle().setText("");
		this.getTxtSaldoDetalle().setText("");
		this.getTxtCotizacionDetalle().setText("");
	}
	
	public void showFindMode() {
		getTxtCodigoCartera().setBackground(Parametros.findColor);
		getCmbEstadoCartera().setBackground(Parametros.findColor);
		getTxtPreimpresoDetalle().setBackground(Parametros.findColor);
		getCmbTipoDocumento().setBackground(Parametros.findColor);
		getTxtCorporacionCartera().setBackground(Parametros.findColor);
		getTxtClienteCartera().setBackground(Parametros.findColor);
		getTxtClienteOficinaCartera().setBackground(Parametros.findColor);
		getCmbFechaEmisionCartera().setBackground(Parametros.findColor);
		getCmbDocumentoDetalle().setBackground(Parametros.findColor);
		clienteOficinaIf = null;
		getTxtCodigoCartera().setEditable(true);
		getTxtReferenciaCartera().setEditable(false);
		getCmbEstadoCartera().setEnabled(true);
		getCmbTipoCartera().setSelectedIndex(-1);
		getCmbTipoCartera().setEnabled(false);
		getCmbTipoDocumento().setEnabled(true);
		getTxtOficinaCartera().setEditable(false);
		getTxtCorporacionCartera().setEditable(false);
		getBtnBuscarCorporacionCartera().setEnabled(true);
		getTxtClienteCartera().setEditable(false);
		getBtnBuscarClienteCartera().setEnabled(true);
		getTxtClienteOficinaCartera().setEditable(false);
		getBtnBuscarClienteOficinaCartera().setEnabled(true);
		getTxtPreimpreso().setEditable(false);
		getCmbVendedorCartera().setEnabled(false);
		getCmbMonedaCartera().setEnabled(false);
		getTxtValorCartera().setEditable(false);
		getTxtSaldoCartera().setEditable(false);
		getCmbFechaEmisionCartera().setEnabled(true);
		getTxtFechaCambioEstadoCartera().setEditable(false);
		getTxtComentariosCartera().setEditable(false);
		getCmbFechaEmisionCartera().setCalendar(null);
		// Detalle
		getCmbDocumentoDetalle().setEnabled(true);
		getTxtReferenciaDetalle().setEditable(false);
		getTxtPreimpresoDetalle().setEditable(true);
		getCmbBancoDetalle().setEnabled(false);
		getCmbCuentaBancaria().setEnabled(false);
		getTxtDepositoDetalle().setEditable(false);
		getTxtSecuencialDetalle().setEditable(false);
		getCmbLineaDetalle().setEnabled(false);
		getTxtCarteraDetalle().setEditable(false);
		getTxtFechaCreacionDetalle().setEditable(false);
		getCmbFechaCarteraDetalle().setEnabled(false);
		getTxtFechaVencimientoDetalle().setEditable(false);
		getTxtFechaActualizacionDetalle().setEditable(false);
		getTxtAutorizacionDetalle().setEditable(false);
		getTxtCotizacionDetalle().setEditable(false);
		getTxtValorDetalle().setEditable(false);
		getTxtSaldoDetalle().setEditable(false);
		
		// Cargo los combos con lo valores con los cuales puedo mandar hacer la búsqueda
		try {
			Long idModulo = ((ModuloIf) SessionServiceLocator.getModuloSessionService().findModuloByCodigo(CODIGO_MODULO_CARTERA).iterator().next()).getId();
			Map parameterMap = new HashMap();
			parameterMap.put("moduloId", idModulo);
			parameterMap.put("empresaId", Long.valueOf(Parametros.getIdEmpresa()));
			SpiritComboBoxModel cmbModelTipoDocumento = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQuery(parameterMap));
			getCmbTipoDocumento().setModel(cmbModelTipoDocumento);
			tipoDocumentoIf = null;
			getCmbEstadoCartera().removeAllItems();
			getCmbEstadoCartera().addItem(null);
			getCmbEstadoCartera().addItem(NOMBRE_ESTADO_NORMAL);
			getCmbEstadoCartera().addItem(NOMBRE_ESTADO_ANULADO);
			getCmbEstadoCartera().addItem(NOMBRE_ESTADO_DUDOSO);
			getCmbEstadoCartera().addItem(NOMBRE_ESTADO_CASTIGADO);
			
			getCmbDocumentoDetalle().setSelectedIndex(-1);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		getTxtCodigoCartera().grabFocus();
	}
	
	private Map camposDetalle(DocumentoIf documento) {
		//Guardo en un mapa los campos del detalle que van a ser mostrados en el cartera afecta
		Map aMap = new HashMap();
		aMap.put("tipoDocumento", getCmbTipoDocumento().getSelectedItem().toString());
		aMap.put("documento", documento);
		aMap.put("fechaCreacion", getTxtFechaCreacionDetalle().getText());
		aMap.put("fechaVencimiento", getTxtFechaVencimientoDetalle().getText());
		aMap.put("fechaCartera", Utilitarios.getFechaUppercase(fechaCarteraDetalle));
		aMap.put("cartera", getTxtCarteraDetalle().getText());
		aMap.put("tipoPago", tipoPagoIf.toString());
		double valor = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtValorDetalle().getText()));
		aMap.put("valor", valor);
		double saldo = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtSaldoDetalle().getText()));
		aMap.put("saldo", saldo);
		
		if (getCmbBancoDetalle().getSelectedItem() != null)
			aMap.put("banco", getCmbBancoDetalle().getSelectedItem().toString());
		
		return aMap;
	}
	
	public void initListeners() {
		getCmbDocumentoDetalle().addActionListener(oActionListenerCmbDocumentoDetalle);
		getCmbTipoDocumento().addActionListener(oActionListenerCmbTipoDocumento);
		getCmbTipoCartera().addActionListener(oActionListenerCmbTipoCartera);
		getCmbFechaEmisionCartera().addActionListener(oActionListenerCmbFechaEmision);
		
		// Manejo los eventos de Buscar Corporación
		getBtnBuscarCorporacionCartera().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(500);	
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
					getTxtCorporacionCartera().setText(corporacionIf.getNombre());
					getTxtClienteCartera().setText("");
					clienteIf = null;
					getTxtClienteOficinaCartera().setText("");
					clienteOficinaIf = null;
					cleanValorSaldoTabla();
				}
			}
		});
		
		// Manejo los eventos de Buscar Cliente
		getBtnBuscarClienteCartera().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				String tipoCliente = "";
				String tituloVentanaBusqueda = "";
				
				if (corporacionIf != null)
					idCorporacion = corporacionIf.getId();
				
				if (getCmbTipoCartera().getSelectedItem() != null) {
					if (getCmbTipoCartera().getSelectedItem().equals(NOMBRE_TIPO_CARTERA_CLIENTE)) {
						tipoCliente = "CL";
						tituloVentanaBusqueda = "Clientes";
					} else {
						tipoCliente = "PR";
						tituloVentanaBusqueda = "Proveedores";
					}
				} else {
					tipoCliente = "AM";
					tituloVentanaBusqueda = "Operadores de Negocio";
				}
				
				clienteCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, tipoCliente);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtClienteCartera().setText(clienteIf.getNombreLegal());
					if (corporacionIf == null) {
						try {
							corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
							getTxtCorporacionCartera().setText(corporacionIf.getNombre());
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					if (getMode() != SpiritMode.FIND_MODE) {
						getTxtClienteOficinaCartera().setText("");
						clienteOficinaIf = null;
					}
					
					try {
						Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(clienteIf.getId());
						if ( oficinas.size() == 1 ){
							clienteOficinaIf = oficinas.iterator().next();
							getTxtClienteOficinaCartera().setText(clienteOficinaIf.getDescripcion());
						}
					} catch (Exception e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error al consultar la oficina del cliente", SpiritAlert.ERROR);
					}
					cargarComboDocumentoDetalle();
					cleanValorSaldoTabla();
				}
			}
		});
		
		// Manejo los eventos de Buscar Cliente Oficina
		getBtnBuscarClienteOficinaCartera().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tipoCliente = "";
				String tituloVentanaBusqueda = "";
								
				if (corporacionIf != null)
					idCorporacion = corporacionIf.getId();
					
				if (clienteIf != null)
					idCliente = clienteIf.getId();
				
				if (getCmbTipoCartera().getSelectedItem() != null) {
					if (getCmbTipoCartera().getSelectedItem().equals(NOMBRE_TIPO_CARTERA_CLIENTE)) {
						tipoCliente = "CL";
						tituloVentanaBusqueda = "Oficinas de Clientes";
					} else {
						tipoCliente = "PR";
						tituloVentanaBusqueda = "Oficinas de Proveedores";
					}
				} else {
					tipoCliente = "AM";
					tituloVentanaBusqueda = "Oficinas de Operadores de Negocio";
				}
				
				clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, "", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					getTxtClienteOficinaCartera().setText(clienteOficinaIf.getDescripcion());
					if (clienteIf == null) {
						try {
							clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
							getTxtClienteCartera().setText(clienteIf.getNombreLegal());
							
							if (corporacionIf == null) {
								corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
								getTxtCorporacionCartera().setText(corporacionIf.getNombre());
							}
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					cargarComboDocumentoDetalle();
					cleanValorSaldoTabla();
				}
			}
		});
		
		getCmbEstadoCartera().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (NOMBRE_ESTADO_NORMAL.equals(getCmbEstadoCartera().getSelectedItem()) == false)
					getTxtFechaCambioEstadoCartera().setText(Utilitarios.fechaAhora());
				else
					getTxtFechaCambioEstadoCartera().setText("");
				
			}
		});
		
		getCmbMonedaCartera().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				monedaIf = (MonedaIf) getCmbMonedaCartera().getModel().getSelectedItem();
				
				try {
					if (monedaIf != null) {
						Iterator monedaIterator = SessionServiceLocator.getMonedaSessionService().findMonedaByCodigo(Parametros.getCodMoneda()).iterator();
						if (monedaIterator.hasNext()) {
							Long idMoneda = ((MonedaIf) monedaIterator.next()).getId();
							Iterator cotizacionIterator = SessionServiceLocator.getCotizacionSessionService().findCotizacionByMonedaAndByMonedaEquivalente(monedaIf.getId(), idMoneda).iterator();
							if (cotizacionIterator.hasNext()) {
								String cotizacion = cotizacionIterator.next().toString();
								getTxtCotizacionDetalle().setText(cotizacion);
							}
						}
					}
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		});
		
		// Listener de la tabla de detalle
		getTblDetalle().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				getDetail(evt);
			}
		});
		
		getTblDetalle().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				getDetail(evt);
			}
		});
		
		
		getBtnAgregarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if ( clienteOficinaIf!=null )
					agregarCarteraDetalle();
				else
					SpiritAlert.createAlert("Debe seleccionar el cliente !!", SpiritAlert.WARNING);
			}
		});
		
		getBtnActualizarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getTblDetalle().getSelectedRow() != -1) {
					actualizarCarteraDetalle();
				}else{
					SpiritAlert.createAlert("Debe seleccionar una fila de la tabla para Actualizar !!", SpiritAlert.WARNING);
				}
			}
		});
		
		getBtnEliminarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getTblDetalle().getSelectedRow() != -1) {
					eliminarCarteraDetalle();
				}else{
					SpiritAlert.createAlert("Debe seleccionar una fila de la tabla para Eliminar !!", SpiritAlert.WARNING);
				}
			}
		});
		
		getCmbBancoDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbBancoDetalle().getSelectedItem() != null) {
					cargarComboCuentaBancaria();
				} else{
					getCmbCuentaBancaria().setSelectedItem(null);
					getCmbCuentaBancaria().removeAllItems();
					getCmbCuentaBancaria().setEnabled(false);
					getCmbCuentaBancaria().validate();
				}
			}
		});
	}
	
	private void cleanValorSaldoTabla() {
		valorCartera = 0D;
		saldoCartera = 0D;
		getTxtValorCartera().setText(formatoDecimal.format(valorCartera));
		getTxtSaldoCartera().setText(formatoDecimal.format(saldoCartera));
		cleanTable();
	}
	
	private void getDetail(ComponentEvent evt) {
		if (getTblDetalle().getSelectedRow() != -1) {
			filaSeleccionadaTablaDetalle = getTblDetalle().getSelectedRow();
			filaSeleccionadaTablaDetalle = ((JTable) evt.getSource()).convertRowIndexToModel(filaSeleccionadaTablaDetalle);
			carteraDetalleForUpdate = (CarteraDetalleIf) carteraDetalleColeccion.get(filaSeleccionadaTablaDetalle);
			
			enableCarteraDetalleForUpdate(carteraDetalleForUpdate);
			 //joha
		}
	}
	
	private void cargarComboCuentaBancaria(){
		try {
			BancoIf banco = (BancoIf) getCmbBancoDetalle().getSelectedItem();
			List cuentasBancarias = (List) SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByBancoId(banco.getId());
			refreshCombo(getCmbCuentaBancaria(), cuentasBancarias);
			getCmbCuentaBancaria().setEnabled(true);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void agregarCarteraDetalle() {
		try {
			boolean isExisteCartera = false;
			// Extraigo los objetos provenientes de los combos
			DocumentoIf documento = (DocumentoIf) getCmbDocumentoDetalle().getSelectedItem();
			TipoPagoIf tipoPago = tipoPagoIf;
			LineaIf linea = (LineaIf) getCmbLineaDetalle().getSelectedItem();
			// Cambio el formato de la fecha para insertarlo a la base
			DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
			String fechaCreacion = dateFormatter.format(fechaCreacionDetalle.getTime());
			String fechaCartera = dateFormatter.format(fechaCarteraDetalle.getTime());
			String fechaVencimiento = dateFormatter.format(fechaVencimientoDetalle.getTime());
			String fechaActualizacion = dateFormatter.format(fechaActualizacionDetalle.getTime());
			
			// Si la coleccion tiene algun elemento
			if (carteraDetalleColeccion.size() != 0) {
				// Recorro la coleccion de detalles
				for (int i = 0; i < carteraDetalleColeccion.size(); i++) {
					CarteraDetalleIf detalleCarteraTemp = (CarteraDetalleIf) carteraDetalleColeccion.get(i);
					String fechaCreacionTemp = dateFormatter.format(detalleCarteraTemp.getFechaCreacion());
					String fechaCarteraTemp = dateFormatter.format(detalleCarteraTemp.getFechaCartera());
					String fechaVencimientoTemp = dateFormatter.format(detalleCarteraTemp.getFechaVencimiento());
					String fechaActualizacionTemp = dateFormatter.format(detalleCarteraTemp.getFechaUltimaActualizacion());
					// Si el detalle cargado ya esta en lista, entonces muestro un mensaje de error
					if (detalleCarteraTemp.getDocumentoId().equals(documento.getId()) && detalleCarteraTemp.getReferencia().equals(getTxtReferenciaDetalle().getText())
							&& detalleCarteraTemp.getPreimpreso().equals(getTxtPreimpresoDetalle().getText())
							&& detalleCarteraTemp.getFormaPagoId().equals(tipoPago.getId())
							&& detalleCarteraTemp.getAutorizacion().equals(getTxtAutorizacionDetalle().getText())
							&& fechaCreacionTemp.equals(fechaCreacion)
							&& fechaCarteraTemp.equals(fechaCartera)
							&& fechaActualizacionTemp.equals(fechaActualizacion)
							&& fechaVencimientoTemp.equals(fechaVencimiento)
							&& detalleCarteraTemp.getCartera().equals(getTxtCarteraDetalle().getText().substring(0, 1))
							&& detalleCarteraTemp.getValor().toString().equals(Utilitarios.removeDecimalFormat(getTxtValorDetalle().getText()))) {
						isExisteCartera = true;
						break;
					}
				}
			}
			
			modelCarteraDetalle = (DefaultTableModel) getTblDetalle().getModel();
			Vector<String> filaDetalleCartera = new Vector<String>();
			
			if (validateFieldsDetalleCartera()) {
				if (isExisteCartera == false) {
					CarteraDetalleData data = new CarteraDetalleData();
					// Seteo los datos del objeto cartera detalle
					data.setDocumentoId(documento.getId());
					
					if(documento.getCodigo().equals("RERC") || documento.getCodigo().equals("REIC")){
						SriClienteRetencionIf sriClienteRetencion = (SriClienteRetencionIf)getCmbPorcentaje().getSelectedItem();
						data.setSriClienteRetencionId(sriClienteRetencion.getId());
					}
					
					data.setReferencia(getTxtReferenciaDetalle().getText());
					data.setPreimpreso(getTxtPreimpresoDetalle().getText());
					data.setSecuencial(secuencialDetalle);
					
					if (linea != null){
						data.setLineaId(linea.getId());
					}
					
					data.setFormaPagoId(tipoPago.getId());
					data.setFechaCreacion(new java.sql.Date(fechaCreacionDetalle.getYear(), fechaCreacionDetalle.getMonth(), fechaCreacionDetalle.getDate()));
					data.setFechaCartera(new java.sql.Date(fechaCarteraDetalle.getYear(), fechaCarteraDetalle.getMonth(), fechaCarteraDetalle.getDate()));
					data.setFechaVencimiento(new java.sql.Date(fechaVencimientoDetalle.getYear(), fechaVencimientoDetalle.getMonth(), fechaVencimientoDetalle.getDate()));
					data.setFechaUltimaActualizacion(new java.sql.Date(fechaActualizacionDetalle.getYear(), fechaActualizacionDetalle.getMonth(), fechaActualizacionDetalle.getDate()));
					double valor = Double.valueOf(Utilitarios.removeDecimalFormat(getTxtValorDetalle().getText()));					
					data.setValor(BigDecimal.valueOf(valor));
					
					if (!getTxtCotizacionDetalle().getText().equals("")){
						data.setCotizacion(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtCotizacionDetalle().getText()))));
					}
					
					data.setCartera(getTxtCarteraDetalle().getText().substring(0, 1));
															
					data.setAutorizacion(getTxtAutorizacionDetalle().getText());

					// Veo si el tipo de pago es cheque para insertar el banco al que pertenece
					if (tipoPagoIf != null && tipoPagoIf.getNombre().contains(TIPO_PAGO_CHEQUE)) {
						CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) getCmbCuentaBancaria().getSelectedItem();
						data.setCuentaBancariaId(cuentaBancaria.getId());
					}
					
					if (getCmbSustentoTributario().isEnabled() && (getCmbSustentoTributario().getSelectedItem() != null)) {
						SriSustentoTributarioIf tipoSustentoTributario = (SriSustentoTributarioIf) getCmbSustentoTributario().getSelectedItem();
						data.setSriSustentoTributarioId(tipoSustentoTributario.getId());
					}
										
					double saldo = valor;
					data.setSaldo(BigDecimal.valueOf(saldo));
					
					//esta valor de saldo.. pilas!! joha
					
					valorCartera = valorCartera + data.getValor().doubleValue();
					saldoCartera = saldoCartera + data.getSaldo().doubleValue();
					
					valorCartera = BigDecimal.valueOf(valorCartera).abs().doubleValue();
					saldoCartera = BigDecimal.valueOf(saldoCartera).abs().doubleValue();
					
					getTxtValorCartera().setText(formatoDecimal.format(valorCartera));
					getTxtSaldoCartera().setText(formatoDecimal.format(saldoCartera));
					
					if((getCmbDocumentoDetalle().getSelectedItem() != null) && ((DocumentoIf)getCmbDocumentoDetalle().getSelectedItem()).getCodigo().equals("CHPO")){
						data.setDiferido("D");
					}
					
					carteraDetalleColeccion.add(data);
					
					// Agrega los valores al registro que va ser añadido a la tabla.
					filaDetalleCartera.add(secuencialDetalle + "");
					filaDetalleCartera.add(documento.getNombre());
					filaDetalleCartera.add(tipoPago.getNombre());
					
					if (linea != null){
						filaDetalleCartera.add(linea.getNombre());
					}else{
						filaDetalleCartera.add(null);
					}
					
					filaDetalleCartera.add(Utilitarios.getFechaCortaUppercase(fechaCreacionDetalle));
					filaDetalleCartera.add(Utilitarios.getFechaCortaUppercase(fechaCarteraDetalle));
					filaDetalleCartera.add(getTxtCarteraDetalle().getText());
					String strValor = Utilitarios.removeDecimalFormat(getTxtValorDetalle().getText());
					
					filaDetalleCartera.add(formatoDecimal.format(Double.valueOf(strValor)));
					filaDetalleCartera.add(formatoDecimal.format(Double.valueOf(strValor)));
					filaDetalleCartera.add("0");
					modelCarteraDetalle.addRow(filaDetalleCartera);
					// Incremento en 1 el secuencial del detalle
					secuencialDetalle++;
				}else{
					if (isExisteCartera){
						SpiritAlert.createAlert("El detalle de cartera ya se encuentra agregado !!!", SpiritAlert.INFORMATION);
					}else if (cartera.getSaldo().doubleValue() < Double.valueOf(Utilitarios.removeDecimalFormat(getTxtValorDetalle().getText()))){
						SpiritAlert.createAlert("El valor del detalle excede al saldo del recibo !!!", SpiritAlert.INFORMATION);
					}
				}
				//joha
				reiniciarComponentesDetalle();
			}				
		} catch (Exception e) {
			SpiritAlert.createAlert("No se pudo ingresar el detalle de cartera !!!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void reiniciarComponentesDetalle() {
		getCmbDocumentoDetalle().setSelectedIndex(0);
		getCmbDocumentoDetalle().repaint();
		getCmbBancoDetalle().setSelectedIndex(-1);
		getCmbBancoDetalle().repaint();
		getCmbCuentaBancaria().setSelectedIndex(-1);
		getCmbCuentaBancaria().repaint();
		getCmbLineaDetalle().setSelectedIndex(0);
		getCmbLineaDetalle().repaint();
		
		getTxtSecuencialDetalle().setText(String.valueOf(secuencialDetalle));
		getTxtReferenciaDetalle().setText("");
		getTxtPreimpresoDetalle().setText("");
		getTxtDepositoDetalle().setText("");
		getTxtValorDetalle().setText("");
		getTxtSaldoDetalle().setText("");
		getTxtAutorizacionDetalle().setText("");
		getCmbSustentoTributario().setSelectedItem(null);
		getCmbSustentoTributario().repaint();
		Calendar calendarFechaCarteraDetalle = new GregorianCalendar();
		calendarFechaCarteraDetalle.setTime(fechaCreacionDetalle);
		getCmbFechaCarteraDetalle().setCalendar(calendarFechaCarteraDetalle);
		getCmbFechaCarteraDetalle().repaint();
		getTxtReferenciaDetalle().grabFocus();
	}
	
	private void actualizarCarteraDetalle() {
		try {
			if (carteraDetalleForUpdate != null) {
				if((registrosDetallesToDetalleCarteraMap.get(filaSeleccionadaTablaDetalle) == null) 
						|| ((Vector)registrosDetallesToDetalleCarteraMap.get(filaSeleccionadaTablaDetalle)).size() == 0){
					boolean isExisteCartera = false;
					// Extraigo los objetos provenientes de los combos
					DocumentoIf documento = (DocumentoIf) getCmbDocumentoDetalle().getSelectedItem();
					TipoPagoIf tipoPago = tipoPagoIf;
					LineaIf linea = (LineaIf) getCmbLineaDetalle().getSelectedItem();
					// Cambio el formato de la fecha para insertarlo a la base
					DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
					String fechaCreacion = dateFormatter.format(fechaCreacionDetalle.getTime());
					String fechaCartera = dateFormatter.format(fechaCarteraDetalle.getTime());
					String fechaVencimiento = dateFormatter.format(fechaVencimientoDetalle.getTime());
					String fechaActualizacion = dateFormatter.format(fechaActualizacionDetalle.getTime());
					
					if (carteraDetalleColeccion.size() != 0) {
						for (int i = 0; i < carteraDetalleColeccion.size(); i++) {
							CarteraDetalleIf detalleCarteraTemp = (CarteraDetalleIf) carteraDetalleColeccion.get(i);
							String fechaCreacionTemp = dateFormatter.format(detalleCarteraTemp.getFechaCreacion());
							String fechaCarteraTemp = dateFormatter.format(detalleCarteraTemp.getFechaCartera());
							String fechaVencimientoTemp = dateFormatter.format(detalleCarteraTemp.getFechaVencimiento());
							String fechaActualizacionTemp = dateFormatter.format(detalleCarteraTemp.getFechaUltimaActualizacion());
							
							if (detalleCarteraTemp.getDocumentoId().equals(documento.getId()) && detalleCarteraTemp.getReferencia().equals(getTxtReferenciaDetalle().getText())
									&& detalleCarteraTemp.getPreimpreso().equals(getTxtPreimpresoDetalle().getText())
									&& detalleCarteraTemp.getFormaPagoId().equals(tipoPago.getId())
									&& detalleCarteraTemp.getAutorizacion().equals(getTxtAutorizacionDetalle().getText())
									&& fechaCreacionTemp.equals(fechaCreacion)
									&& fechaCarteraTemp.equals(fechaCartera)
									&& fechaActualizacionTemp.equals(fechaActualizacion)
									&& fechaVencimientoTemp.equals(fechaVencimiento)
									&& detalleCarteraTemp.getCartera().equals(getTxtCarteraDetalle().getText().substring(0, 1))
									&& detalleCarteraTemp.getValor().toString().equals(getTxtValorDetalle().getText())) {
								isExisteCartera = true;
								break;
							}
						}
					}
					
					modelCarteraDetalle = (DefaultTableModel) getTblDetalle().getModel();
					// Contador y vector de registros cruazods con este detalle
					int registrosAfecta = 0;
					Vector registrosDetallesToDetalleCarteraVector = null;
					// Saco el vector que contiene los documentos que estan siendo cruzados con este detalle
					if (registrosDetallesToDetalleCarteraMap.get(filaSeleccionadaTablaDetalle) != null) {
						registrosDetallesToDetalleCarteraVector = (Vector) registrosDetallesToDetalleCarteraMap.get(filaSeleccionadaTablaDetalle);
						registrosAfecta = registrosDetallesToDetalleCarteraVector.size();
					}
					
					if (validateFieldsDetalleCartera()) {
						getCmbCuentaBancaria().validate();
						// Si el Detalle no existe y si el detalle no tiene documentos con los cuales ha sido cruzado lo actualizo
						if (isExisteCartera == false && registrosAfecta == 0) {
							CarteraDetalleIf data = (CarteraDetalleIf) carteraDetalleColeccion.get(getTblDetalle().getSelectedRow());

							valorCartera = valorCartera - data.getValor().doubleValue();
							saldoCartera = saldoCartera - data.getSaldo().doubleValue();
							
							// Seteo los datos del objeto cartera detalle
							data.setDocumentoId(documento.getId());
							
							if(documento.getCodigo().equals("RERC") || documento.getCodigo().equals("REIC")){
								SriClienteRetencionIf sriClienteRetencion = (SriClienteRetencionIf)getCmbPorcentaje().getSelectedItem();
								data.setSriClienteRetencionId(sriClienteRetencion.getId());
							}
							
							data.setReferencia(getTxtReferenciaDetalle().getText());
							data.setPreimpreso(getTxtPreimpresoDetalle().getText());
							data.setSecuencial(Integer.valueOf(getTxtSecuencialDetalle().getText()));
							
							if (linea != null){
								data.setLineaId(linea.getId());
							}
							
							data.setFormaPagoId(tipoPago.getId());						
							data.setFechaCreacion(new java.sql.Date(fechaCreacionDetalle.getYear(), fechaCreacionDetalle.getMonth(), fechaCreacionDetalle.getDate()));
							data.setFechaCartera(new java.sql.Date(fechaCarteraDetalle.getYear(), fechaCarteraDetalle.getMonth(), fechaCarteraDetalle.getDate()));
							data.setFechaVencimiento(new java.sql.Date(fechaVencimientoDetalle.getYear(), fechaVencimientoDetalle.getMonth(), fechaVencimientoDetalle.getDate()));
							data.setFechaUltimaActualizacion(new java.sql.Date(fechaActualizacionDetalle.getYear(), fechaActualizacionDetalle.getMonth(), fechaActualizacionDetalle.getDate()));
							double valor = Double.valueOf(Utilitarios.removeDecimalFormat(getTxtValorDetalle().getText()));
							data.setValor(BigDecimal.valueOf(valor));
							if(!getTxtCotizacionDetalle().getText().equals("")){
								data.setCotizacion(BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtCotizacionDetalle().getText()))));
							}
							
							java.sql.Date fechaSeleccionadaDetalle = new java.sql.Date(fechaCarteraDetalle.getTime());
							java.sql.Date fechaActual = new java.sql.Date(Utilitarios.dateHoy().getTime());
							
							data.setCartera(getTxtCarteraDetalle().getText().substring(0, 1));
							
							data.setAutorizacion(getTxtAutorizacionDetalle().getText());
							
							// Veo si el tipo de pago es cheque para insertar el banco al que pertenece
							if (tipoPagoIf != null && tipoPagoIf.getNombre().contains(TIPO_PAGO_CHEQUE)) {
								CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) getCmbCuentaBancaria().getSelectedItem();
								data.setCuentaBancariaId(cuentaBancaria.getId());
							}
							
							if (getCmbSustentoTributario().isEnabled() && (getCmbSustentoTributario().getSelectedItem() != null)) {
								SriSustentoTributarioIf tipoSustentoTributario = (SriSustentoTributarioIf) getCmbSustentoTributario().getSelectedItem();
								data.setSriSustentoTributarioId(tipoSustentoTributario.getId());
							}
							
							double saldo = valor;
							data.setSaldo(BigDecimal.valueOf(saldo));
							
							valorCartera = valorCartera + data.getValor().doubleValue();
							saldoCartera = saldoCartera + data.getSaldo().doubleValue();
							
							getTxtValorCartera().setText(formatoDecimal.format(valorCartera));
							getTxtSaldoCartera().setText(formatoDecimal.format(saldoCartera));
							
							if((getCmbDocumentoDetalle().getSelectedItem() != null) && ((DocumentoIf)getCmbDocumentoDetalle().getSelectedItem()).getCodigo().equals("CHPO")){
								data.setDiferido("D");
							}
							
							// Actualizar en la coleccion de detalleCarteraColeccion el registro que fue cambiado
							carteraDetalleColeccion.set(getTblDetalle().getSelectedRow(), data);
							// Actualizo en la tabla
							modelCarteraDetalle.setValueAt(getTxtSecuencialDetalle().getText(), getTblDetalle().getSelectedRow(), 0);
							modelCarteraDetalle.setValueAt(documento.getNombre(), getTblDetalle().getSelectedRow(), 1);
							modelCarteraDetalle.setValueAt(tipoPago.getNombre(), getTblDetalle().getSelectedRow(), 2);
							
							if (linea != null){
								modelCarteraDetalle.setValueAt(linea.getNombre(), getTblDetalle().getSelectedRow(), 3);
							}else{
								modelCarteraDetalle.setValueAt(null, getTblDetalle().getSelectedRow(), 3);
							}
							
							modelCarteraDetalle.setValueAt(Utilitarios.getFechaCortaUppercase(fechaCreacionDetalle), getTblDetalle().getSelectedRow(), 4);
							modelCarteraDetalle.setValueAt(Utilitarios.getFechaCortaUppercase(fechaCarteraDetalle), getTblDetalle().getSelectedRow(), 5);
							modelCarteraDetalle.setValueAt(getTxtCarteraDetalle().getText(), getTblDetalle().getSelectedRow(), 6);
							
							String strValor = Utilitarios.removeDecimalFormat(getTxtValorDetalle().getText());
							
							modelCarteraDetalle.setValueAt(formatoDecimal.format(Double.valueOf(strValor)), getTblDetalle().getSelectedRow(), 7);
							modelCarteraDetalle.setValueAt(formatoDecimal.format(Double.valueOf(strValor)), getTblDetalle().getSelectedRow(), 8);
						}else if(registrosAfecta != 0){
							// Extraigo el objeto para actualizar su fecha de cartera
							CarteraDetalleIf data = (CarteraDetalleIf) carteraDetalleColeccion.get(getTblDetalle().getSelectedRow());
							// Si la cartera es SI y estoy en modo update no dejo actualizar la fecha
							if (data.getCartera().equals(OPCION_SI) && getMode() == SpiritMode.UPDATE_MODE){
								SpiritAlert.createAlert("El detalle no puede ser actualizado por ser ya cartera !!!", SpiritAlert.WARNING);
							}else {
								data.setFechaCartera(new java.sql.Date(fechaCarteraDetalle.getYear(), fechaCarteraDetalle.getMonth(), fechaCarteraDetalle.getDate()));
								data.setFechaVencimiento(new java.sql.Date(fechaVencimientoDetalle.getYear(), fechaVencimientoDetalle.getMonth(), fechaVencimientoDetalle.getDate()));
								// Actualizar en la coleccion de detalleCarteraColeccion el registro que fue cambiado
								carteraDetalleColeccion.set(getTblDetalle().getSelectedRow(), data);
								// Actualizo en la tabla
								modelCarteraDetalle.setValueAt(Utilitarios.getFechaCortaUppercase(fechaCarteraDetalle), getTblDetalle().getSelectedRow(), 5);
								modelCarteraDetalle.setValueAt(getTxtCarteraDetalle().getText(), getTblDetalle().getSelectedRow(), 6);
							}
						}else{
							SpiritAlert.createAlert("El detalle de cartera ya se encuentra agregado !!!", SpiritAlert.INFORMATION);
						}
						reiniciarComponentesDetalle();
					}
				} else {
					SpiritAlert.createAlert("El pago ya ha sido realizado y no puede ser actualizado", SpiritAlert.WARNING);
				}
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("No se pudo actualizar el detalle de cartera !!!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public boolean validateFieldsDetalleCartera() {
		try {
			String strValorDetalle = Utilitarios.removeDecimalFormat(getTxtValorDetalle().getText());
			java.sql.Date fechaSeleccionadaDetalle = new java.sql.Date(fechaCarteraDetalle.getTime());
			java.sql.Date fechaActual = new java.sql.Date(Utilitarios.dateHoy().getTime());
			
			DocumentoIf documentoSeleccionado = (DocumentoIf) getCmbDocumentoDetalle().getSelectedItem();
			if(documentoSeleccionado.getCodigo().equals("CHPO") && (Utilitarios.compararFechas(fechaSeleccionadaDetalle,fechaActual) != 1)){
				SpiritAlert.createAlert("Si el documento es COBRO A CLIENTE CON CHEQUE POSTFECHADO, la Fecha de cartera debe ser posterior a la fecha actual!", SpiritAlert.WARNING);
				getCmbFechaCarteraDetalle().grabFocus();
				return false;
			}
			
			if (this.getCmbDocumentoDetalle().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar un Documento !!", SpiritAlert.WARNING);
				getJtpCartera().setSelectedIndex(1);
				getCmbDocumentoDetalle().grabFocus();
				return false;
			}
			
			if(documentoSeleccionado.getCodigo().equals("RERC") || documentoSeleccionado.getCodigo().equals("REIC")){
				if(getCmbPorcentaje().getSelectedIndex() == -1){
					SpiritAlert.createAlert("Debe seleccionar un Porcentaje para la Retención !!", SpiritAlert.WARNING);
					getJtpCartera().setSelectedIndex(1);
					getCmbPorcentaje().grabFocus();
					return false;
				}
			}
			
			if (tipoPagoIf != null && tipoPagoIf.getNombre().contains(TIPO_PAGO_CHEQUE)) {
				if (getTxtReferenciaDetalle().getText().equals("")) {
					SpiritAlert.createAlert("Debe ingresar la Referencia del cheque !!!", SpiritAlert.WARNING);
					getJtpCartera().setSelectedIndex(1);
					getTxtReferenciaDetalle().grabFocus();
					return false;
				}
				
				if (getTxtPreimpresoDetalle().getText().equals("")) {
					SpiritAlert.createAlert("Debe ingresar el Preimpreso del cheque !!!", SpiritAlert.WARNING);
					getJtpCartera().setSelectedIndex(1);
					getTxtPreimpresoDetalle().grabFocus();
					return false;
				}
				
				if (getCmbBancoDetalle().getSelectedItem() == null) {
					SpiritAlert.createAlert("Debe seleccionar el Banco !!!", SpiritAlert.WARNING);
					getJtpCartera().setSelectedIndex(1);
					getCmbBancoDetalle().grabFocus();
					return false;
				}
				
				if (getCmbCuentaBancaria().getSelectedItem() == null) {
					SpiritAlert.createAlert("Debe seleccionar la Cuenta Bancaria !!!", SpiritAlert.WARNING);
					getJtpCartera().setSelectedIndex(1);
					getCmbCuentaBancaria().grabFocus();
					return false;
				}
			}
			
			if (getCmbLineaDetalle().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar la Línea del negocio !!!", SpiritAlert.WARNING);
				getJtpCartera().setSelectedIndex(1);
				getCmbLineaDetalle().grabFocus();
				return false;
			}
			
			if ("".equals(strValorDetalle)) {
				SpiritAlert.createAlert("Debe ingresar el Valor !!", SpiritAlert.WARNING);
				getJtpCartera().setSelectedIndex(1);
				getTxtValorDetalle().grabFocus();
				return false;
			}
			
			if (getCmbFechaCarteraDetalle().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe ingresar la Fecha de cartera !!", SpiritAlert.WARNING);
				getJtpCartera().setSelectedIndex(1);
				getCmbFechaCarteraDetalle().grabFocus();
				return false;
			}
			
			if (getCmbTipoCartera().getSelectedItem().toString().equals("PROVEEDOR")) {
				if (getCmbSustentoTributario().getSelectedItem() == null) {
					SpiritAlert.createAlert("Debe seleccionar el Sustento Tributario !!", SpiritAlert.WARNING);
					getJtpCartera().setSelectedIndex(1);
					getCmbSustentoTributario().grabFocus();
					return false;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void loadCombos() {
		getCmbTipoCartera().setSelectedItem(NOMBRE_TIPO_CARTERA_CLIENTE);
		cargarComboVendedorCartera();
		cargarComboLineaDetalle();
		cargarComboDocumentoDetalle();
		cargarComboBancoDetalle();
		cargarComboMonedaCartera();
		cargarComboTipoSustentoTributario();
	}
	
	private void cargarComboSriClienteRetencion(String retencion) {
		try {
			Map aMap = new HashMap();
			aMap.put("estado", "A");
			if (retencion.equals("RERC")){
				aMap.put("tipoRetencion", "R");
			}else if(retencion.equals("REIC")){
				aMap.put("tipoRetencion", "I");
			}
			List clienteRetencionList = (List) SessionServiceLocator.getSriClienteRetencionSessionService().findSriClienteRetencionByQuery(aMap);
			refreshCombo(getCmbPorcentaje(), clienteRetencionList);
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboTipoSustentoTributario() {
		try {
			List sustentosTributarios = (List) SessionServiceLocator.getSriSustentoTributarioSessionService().getSriSustentoTributarioList();
			refreshCombo(getCmbSustentoTributario(),sustentosTributarios);
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboEstadoCartera(){
		getCmbEstadoCartera().removeAllItems();
		getCmbEstadoCartera().addItem(NOMBRE_ESTADO_NORMAL);
	}
	
	private void cargarComboTipoCartera(){
		getCmbTipoCartera().addItem(NOMBRE_TIPO_CARTERA_CLIENTE);
		getCmbTipoCartera().addItem(NOMBRE_TIPO_CARTERA_PROVEEDOR);
		getCmbTipoCartera().setSelectedItem(NOMBRE_TIPO_CARTERA_CLIENTE);
	}
	
	Comparator<EmpleadoIf> ordenadorArrayListPorNombre = new Comparator<EmpleadoIf>(){
		public int compare(EmpleadoIf o1, EmpleadoIf o2) {
			return (o1.getNombres().compareTo(o2.getNombres()));
		}		
	};
	
	private void cargarComboVendedorCartera(){
		try {
			List<EmpleadoIf> vendedores = new ArrayList<EmpleadoIf>();
			HashMap<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("empresaId", Parametros.getIdEmpresa());
			mapa.put("vendedor","S");
			Iterator it = SessionServiceLocator.getTipoEmpleadoSessionService().findTipoEmpleadoByQuery(mapa).iterator();
			while (it.hasNext()) {
				TipoEmpleadoIf tipoEmpleado = (TipoEmpleadoIf) it.next();
				mapa.clear();
				mapa.put("tipoempleadoId" , tipoEmpleado.getId() );
				mapa.put("oficinaId", Parametros.getIdOficina());
				mapa.put("estado" , "A");
				Iterator vendedoresIt = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByQuery(mapa).iterator();
				while (vendedoresIt.hasNext()) {
					EmpleadoIf empleado = (EmpleadoIf) vendedoresIt.next();
					vendedores.add(empleado);
				}
			}
			//TODO LUEGO DE INGRESAR LOS DATOS INICIALES, CAMBIAR A -1 EL VALOR DE CERO
			Collections.sort((ArrayList)vendedores,ordenadorArrayListPorNombre);
			refreshComboByIndex(getCmbVendedorCartera(),vendedores,-1);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboLineaDetalle(){
		try{
			//getCmbLineaDetalle().addItem(null);
			List lineasDetalle = (List) SessionServiceLocator.getLineaSessionService().findLineaByEmpresaId(Parametros.getIdEmpresa()); 
			refreshCombo(getCmbLineaDetalle(), lineasDetalle);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public boolean contieneTipoDocumento(List<TipoDocumentoIf> tiposDocumentos, TipoDocumentoIf tipoDocumento){
		for(TipoDocumentoIf tipoDocumentoTemp : tiposDocumentos){
			if(tipoDocumentoTemp.getId().compareTo(tipoDocumento.getId()) == 0){
				return true;
			}
		}
		return false;
	}
	
	private void cargarComboTipoDocumento() {
		Map tiposDocumentoMap = mapearTiposDocumento();
		Long idModulo;
		try {
			idModulo = ((ModuloIf) SessionServiceLocator.getModuloSessionService().findModuloByCodigo(CODIGO_MODULO_CARTERA).iterator().next()).getId();
			String tipoCartera = getCmbTipoCartera().getSelectedItem().toString().substring(0, 1);
						
			List<DocumentoIf> documentos = (List<DocumentoIf>)SessionServiceLocator.getDocumentoSessionService().findDocumentoByUsuarioIdAndModuloId(((UsuarioIf) Parametros.getUsuarioIf()).getId(), idModulo);
			List<TipoDocumentoIf> tiposDocumentos = new ArrayList();
			for(DocumentoIf documento : documentos){
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get(documento.getTipoDocumentoId());
				if(!contieneTipoDocumento(tiposDocumentos, tipoDocumento) && tipoDocumento.getTipocartera().equals(tipoCartera)){
					if ( !( getCmbTipoCartera().getSelectedItem().toString().equals(NOMBRE_TIPO_CARTERA_PROVEEDOR)
							&& tipoDocumento.getCodigo().equals("CRE") ) ) //Si es cartera de proveedor, no tiene que salir el tipo de documento RETENCION DE PROVEEDOR
						tiposDocumentos.add(tipoDocumento);
				}
			}
			
			refreshCombo(getCmbTipoDocumento(), tiposDocumentos);			
			seterTipoDocumentoDefault();
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private Map mapearTiposDocumento() {
		Map tiposDocumentoMap = new HashMap();
		
		try {
			Iterator tiposDocumentoIterator = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (tiposDocumentoIterator.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoIterator.next();
				tiposDocumentoMap.put(tipoDocumento.getId(), tipoDocumento);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return tiposDocumentoMap;
	}

	private void seterTipoDocumentoDefault() throws GenericBusinessException {
		//Seteo que el tipo documento por default sea Anticipo a Proveedor o Comp. de Ingreso según el Tipo Cartera
		if (getCmbTipoCartera().getSelectedItem().toString().equals(NOMBRE_TIPO_CARTERA_PROVEEDOR)) { 
			if(SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo("ANP").size() > 0){
				TipoDocumentoIf tipoDocumentoDefault = (TipoDocumentoIf)SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo("ANP").iterator().next();
				tipoDocumentoIf = tipoDocumentoDefault;
				int indiceANP = 0;
				for(int i=0; i<getCmbTipoDocumento().getModel().getSize();i++){
					TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)getCmbTipoDocumento().getModel().getElementAt(i);
					if(tipoDocumento.getId().compareTo(tipoDocumentoDefault.getId()) == 0){
						indiceANP = i; break;
					}
				}
				getCmbTipoDocumento().setSelectedIndex(indiceANP);
				getCmbTipoDocumento().repaint();
			}						
		} else if (getCmbTipoCartera().getSelectedItem().toString().equals(NOMBRE_TIPO_CARTERA_CLIENTE)) {
			if(SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo("CIN").size() > 0){
				TipoDocumentoIf tipoDocumentoDefault = (TipoDocumentoIf)SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo("CIN").iterator().next();
				tipoDocumentoIf = tipoDocumentoDefault;
				int indiceCIN = 0;
				for(int i=0; i<getCmbTipoDocumento().getModel().getSize();i++){
					TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)getCmbTipoDocumento().getModel().getElementAt(i);
					if(tipoDocumento.getId().compareTo(tipoDocumentoDefault.getId()) == 0){
						indiceCIN = i; break;
					}
				}
				
				if (getCmbTipoDocumento().getItemCount() > 0)
					getCmbTipoDocumento().setSelectedIndex(indiceCIN);
				getCmbTipoDocumento().repaint();
			}
		}
	}
	
	private void cargarComboDocumentoDetalle(){
		try {
			if (tipoDocumentoIf != null) {
				List documentos = (List)SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipodocumentoIdAndUsuarioId(tipoDocumentoIf.getId(), ((UsuarioIf) Parametros.getUsuarioIf()).getId());
				refreshCombo(getCmbDocumentoDetalle(), documentos);
				if(getMode() == SpiritMode.FIND_MODE){
					getCmbDocumentoDetalle().setSelectedIndex(-1);
				}else if(tipoDocumentoIf.getCodigo().equals("CEG")){
					//Los otros documentos de comprobantes de ingreso que generan automaticamente en Pagos y Pagos Diferidos
					DocumentoIf documentoCruce = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo("CFP").iterator().next();
					getCmbDocumentoDetalle().removeAllItems();
					getCmbDocumentoDetalle().addItem(documentoCruce);
					getCmbDocumentoDetalle().setSelectedIndex(0);
					getCmbDocumentoDetalle().repaint();
				}else{
					if(getCmbDocumentoDetalle().getItemCount() > 0){
						documentoIf = (DocumentoIf) getCmbDocumentoDetalle().getModel().getElementAt(0);
					}
					if(documentoIf!=null && documentoIf.getDiferido().equals(OPCION_SI)){
						getCmbFechaCarteraDetalle().setEnabled(true);
					}
					else{
						getTxtCarteraDetalle().setText(CARTERA_SI);
						getCmbFechaCarteraDetalle().setEnabled(true);
					}
					modificarCmbDocumentos();					
				}				
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error en la cargar de los Documentos", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboBancoDetalle(){
		try{
			if (documentoIf != null) {
				if(documentoIf.getCheque() != null && documentoIf.getCheque().equals(OPCION_SI)){
					tipoPagoIf = (TipoPagoIf) SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByCodigo(CODIGO_TIPO_PAGO_CHEQUE).iterator().next();
				}else if (documentoIf.getCodigo().equals("CFC") || documentoIf.getCodigo().equals("CFP")){
					tipoPagoIf = (TipoPagoIf) SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByCodigo(CODIGO_TIPO_PAGO_CREDITO).iterator().next();
				}else{
					tipoPagoIf = (TipoPagoIf) SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByCodigo(CODIGO_TIPO_PAGO_EFECTIVO).iterator().next();
				}
			}
			
			if (tipoPagoIf != null) {
				if (tipoPagoIf.getNombre().contains(TIPO_PAGO_CHEQUE)) {
					List detalleBancos = (List) SessionServiceLocator.getBancoSessionService().findBancoByEstado("A"); 
					refreshCombo(getCmbBancoDetalle(), detalleBancos);
					getCmbBancoDetalle().setEnabled(true);
				} else{
					getCmbBancoDetalle().setEnabled(false);
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboMonedaCartera(){
		try{
			List monedas = (List)SessionServiceLocator.getMonedaSessionService().getMonedaList(); 
			refreshCombo(getCmbMonedaCartera(), monedas);
			monedaIf = (MonedaIf) getCmbMonedaCartera().getModel().getElementAt(0);
			
			if (monedaIf != null) {
				Long idMonedaEquivalente = ((MonedaIf) SessionServiceLocator.getMonedaSessionService().findMonedaByCodigo("USD").iterator().next()).getId();
				Iterator itCotizaciones = SessionServiceLocator.getCotizacionSessionService().findCotizacionByMonedaAndByMonedaEquivalente(monedaIf.getId(), idMonedaEquivalente).iterator();
				if (itCotizaciones.hasNext()) {
					String cotizacion = itCotizaciones.next().toString();
					getTxtCotizacionDetalle().setText(cotizacion);
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void enableCarteraDetalleForUpdate(CarteraDetalleIf carteraDetalle) {
		try {
			DocumentoIf documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalle.getDocumentoId());
			getCmbTipoDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoDocumento(), mapaTipoDocumento.get(documento.getTipoDocumentoId()).getId()));
			getCmbTipoDocumento().repaint();
			getCmbDocumentoDetalle().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocumentoDetalle(), carteraDetalle.getDocumentoId()));
			getCmbDocumentoDetalle().repaint();
			
			if(carteraDetalle.getSriClienteRetencionId() != null){
				getCmbPorcentaje().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPorcentaje(), carteraDetalle.getSriClienteRetencionId()));
			}
			
			if(carteraDetalle.getCuentaBancariaId() != null){
				CuentaBancariaIf cuentaBancaria = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(carteraDetalle.getCuentaBancariaId());
				BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancaria.getBancoId());
				getCmbBancoDetalle().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbBancoDetalle(), banco.getId()));			
				getCmbCuentaBancaria().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbCuentaBancaria(), cuentaBancaria.getId()));
			}
			
			if (carteraDetalle.getLineaId() != null) {
				getCmbLineaDetalle().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbLineaDetalle(), carteraDetalle.getLineaId()));
				getCmbLineaDetalle().repaint();
			}else if (carteraDetalle.getLineaId() == null && getCmbLineaDetalle().getItemCount() > 0) {
				getCmbLineaDetalle().setSelectedIndex(0);
				getCmbLineaDetalle().repaint();
			}
			
			getTxtReferenciaDetalle().setText(carteraDetalle.getReferencia());
			getTxtPreimpresoDetalle().setText(carteraDetalle.getPreimpreso());
			
			if (carteraDetalle.getDepositoId() != null)
				getTxtDepositoDetalle().setText(carteraDetalle.getDepositoId().toString());
			
			getTxtSecuencialDetalle().setText((carteraDetalle.getSecuencial()!=null)?carteraDetalle.getSecuencial().toString():"");
			getTxtAutorizacionDetalle().setText(carteraDetalle.getAutorizacion());
			getTxtFechaCreacionDetalle().setText(Utilitarios.getFechaUppercase(carteraDetalle.getFechaCreacion()));
			getTxtFechaVencimientoDetalle().setText(carteraDetalle.getFechaVencimiento() != null?Utilitarios.getFechaUppercase(carteraDetalle.getFechaVencimiento()):"");
			getTxtFechaActualizacionDetalle().setText(carteraDetalle.getFechaUltimaActualizacion() != null?Utilitarios.getFechaUppercase(carteraDetalle.getFechaUltimaActualizacion()):"");
			getTxtCarteraDetalle().setText(getTblDetalle().getModel().getValueAt(filaSeleccionadaTablaDetalle, 6).toString());
			if (carteraDetalle.getCotizacion() != null)
				getTxtCotizacionDetalle().setText(formatoDecimal.format(carteraDetalle.getCotizacion().doubleValue()));
			getTxtValorDetalle().setText(formatoDecimal.format(carteraDetalle.getValor().doubleValue()));
			getTxtSaldoDetalle().setText(formatoDecimal.format(carteraDetalle.getSaldo().doubleValue()));
			
			if (carteraDetalle.getSaldo().equals(new BigDecimal(0.00)))
				getTxtSaldoDetalle().setText("0.00");
			else
				getTxtSaldoDetalle().setText(formatoDecimal.format(carteraDetalle.getSaldo().doubleValue()));
			
			if (getCmbTipoCartera().getSelectedItem().toString().equals("PROVEEDOR")) {
				getCmbSustentoTributario().setSelectedIndex(carteraDetalle.getSriSustentoTributarioId() != null ? ComboBoxComponent.getIndexToSelectItem(getCmbSustentoTributario(), carteraDetalle.getSriSustentoTributarioId()) : -1);
				getCmbSustentoTributario().validate();
				getCmbSustentoTributario().repaint();
			}
			
			Calendar calendarFechaCarteraDetalle = new GregorianCalendar();
			calendarFechaCarteraDetalle.setTime(carteraDetalle.getFechaCartera());
			getCmbFechaCarteraDetalle().setCalendar(calendarFechaCarteraDetalle);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	 
	public void showSaveMode() {
		getTxtCodigoCartera().setBackground(getBackground());
		getTxtPreimpresoDetalle().setBackground(Parametros.saveUpdateColor);
		getCmbEstadoCartera().setBackground(Parametros.saveUpdateColor);
		getCmbTipoDocumento().setBackground(Parametros.saveUpdateColor);
		getTxtCorporacionCartera().setBackground(getBackground());
		getTxtClienteCartera().setBackground(getBackground());
		getTxtClienteOficinaCartera().setBackground(getBackground());
		getCmbFechaEmisionCartera().setBackground(Parametros.saveUpdateColor);
		getCmbDocumentoDetalle().setBackground(Parametros.saveUpdateColor);
		setSaveMode();
		clean();
		loadCombos();
		cargarComboEstadoCartera();
		// Cartera
		getTxtPreimpreso().setEditable(true);
		getTxtCodigoCartera().setEditable(false);
		getTxtReferenciaCartera().setEditable(true);
		getCmbEstadoCartera().setEnabled(true);
		getCmbTipoCartera().setEnabled(true);
		getCmbTipoDocumento().setEnabled(true);
		getTxtOficinaCartera().setEditable(false);
		getTxtOficinaCartera().setText(Parametros.getNombreOficina());
		getTxtCorporacionCartera().setEditable(false);
		getBtnBuscarCorporacionCartera().setEnabled(true);
		getTxtClienteCartera().setEditable(false);
		getBtnBuscarClienteCartera().setEnabled(true);
		getTxtClienteOficinaCartera().setEditable(false);
		getBtnBuscarClienteOficinaCartera().setEnabled(true);
		getCmbVendedorCartera().setEnabled(true);
		getCmbMonedaCartera().setEnabled(true);
		getTxtValorCartera().setEditable(false);
		getTxtValorCartera().setText("0.00");
		getTxtSaldoCartera().setEditable(false);
		getTxtSaldoCartera().setText("0.00");
		getCmbFechaEmisionCartera().setEnabled(true);
		getTxtFechaCambioEstadoCartera().setEditable(false);
		getTxtComentariosCartera().setEditable(true);
		// Detalle
		getCmbDocumentoDetalle().setEnabled(true);
		getTxtReferenciaDetalle().setEditable(true);
		getTxtPreimpresoDetalle().setEditable(true);
		getCmbBancoDetalle().setEnabled(false);
		getCmbCuentaBancaria().setEnabled(false);
		getTxtDepositoDetalle().setEditable(true);
		getTxtSecuencialDetalle().setEditable(false);
		getTxtSecuencialDetalle().setText(String.valueOf(secuencialDetalle));
		getCmbLineaDetalle().setEnabled(true);
		getTxtCarteraDetalle().setEditable(false);
		getTxtCarteraDetalle().setText(CARTERA_SI);
		getTxtFechaCreacionDetalle().setEditable(false);
		getTxtFechaCreacionDetalle().setText(Utilitarios.fechaAhora());
		getCmbFechaCarteraDetalle().setEnabled(true);
		getTxtFechaVencimientoDetalle().setEditable(false);
		getTxtFechaActualizacionDetalle().setEditable(false);
		getTxtFechaActualizacionDetalle().setText(Utilitarios.fechaAhora());
		getTxtAutorizacionDetalle().setEditable(true);
		getTxtCotizacionDetalle().setEditable(false);
		getTxtValorDetalle().setEditable(true);
		getTxtSaldoDetalle().setEditable(false);
		// obtengo el ultimo numero de la cartera que se haya generado
		int numCartera = Numeradores.getUltimoValor("CARTERA", Parametros.getIdEmpresa()) + 1;
		// obtengo el año en el que estamos
		Calendar now = Calendar.getInstance();
		int añoActual = now.get(Calendar.YEAR);
		getTxtCodigoCartera().setText(añoActual + "-" + formatoSerial.format(numCartera));
		// Seteo los combos de fechas a la fecha de hoy
		Date fecha = new Date();
		Calendar calendarFechaCartera = new GregorianCalendar();
		calendarFechaCartera.setTime(fecha);
		getCmbFechaEmisionCartera().setCalendar(calendarFechaCartera);
		Calendar calendarFechaCarteraDetalle = new GregorianCalendar();
		calendarFechaCarteraDetalle.setTime(fecha);
		getCmbFechaCarteraDetalle().setCalendar(calendarFechaCarteraDetalle);
		// Seteo el valor de la fecha de vencimiento
		// String fechaVencimiento = dateFormatter.format(fechaCarteraDetalle);
		getTxtFechaVencimientoDetalle().setText(Utilitarios.getFechaUppercase(fecha));
		// Veo los listener de tipo de pago y si las fechas son validas, es
		// decir si es que no es antes del dia de hoy
		getCmbFechaCarteraDetalle().addActionListener(oActionListenerCmbFecha);
		getJtpCartera().setSelectedIndex(0);
		getCmbTipoCartera().grabFocus();
	}
	
	public void showUpdateMode() {
		getTxtCodigoCartera().setBackground(Parametros.saveUpdateColor);
		getTxtPreimpresoDetalle().setBackground(getBackground());
		getCmbEstadoCartera().setBackground(Parametros.saveUpdateColor);
		getCmbTipoDocumento().setBackground(Parametros.saveUpdateColor);
		getTxtCorporacionCartera().setBackground(getBackground());
		getTxtClienteCartera().setBackground(getBackground());
		getTxtClienteOficinaCartera().setBackground(getBackground());
		getCmbFechaEmisionCartera().setBackground(Parametros.saveUpdateColor);
		getCmbDocumentoDetalle().setBackground(Parametros.saveUpdateColor);
		setUpdateMode();
		// Cartera
		getTxtPreimpreso().setEditable(false);
		getTxtCodigoCartera().setEditable(false);
		getTxtReferenciaCartera().setEditable(false);
		getCmbEstadoCartera().setEnabled(true);
		getCmbTipoCartera().setEnabled(true);
		getCmbTipoDocumento().setEnabled(true);
		getTxtOficinaCartera().setEditable(false);
		getTxtCorporacionCartera().setEditable(false);
		getBtnBuscarCorporacionCartera().setEnabled(true);
		getTxtClienteCartera().setEditable(false);
		getBtnBuscarClienteCartera().setEnabled(true);
		getTxtClienteOficinaCartera().setEditable(false);
		getBtnBuscarClienteOficinaCartera().setEnabled(true);
		getCmbVendedorCartera().setEnabled(true);
		getCmbMonedaCartera().setEnabled(true);
		getTxtValorCartera().setEditable(false);
		getTxtSaldoCartera().setEditable(false);
		getCmbFechaEmisionCartera().setEnabled(true);
		getTxtFechaCambioEstadoCartera().setEditable(false);
		getTxtComentariosCartera().setEditable(true);
		// Detalle
		getCmbDocumentoDetalle().setEnabled(true);
		getTxtReferenciaDetalle().setEditable(true);
		getTxtPreimpresoDetalle().setText("");
		getTxtPreimpresoDetalle().setEditable(true);
		getTxtDepositoDetalle().setEditable(true);
		getTxtSecuencialDetalle().setEditable(false);
		getCmbLineaDetalle().setEnabled(true);
		getTxtCarteraDetalle().setEditable(false);
		getTxtCarteraDetalle().setText(CARTERA_SI);
		getTxtFechaCreacionDetalle().setEditable(false);
		//getTxtFechaCreacionDetalle().setText(Utilitarios.fechaAhora());
		getCmbFechaCarteraDetalle().setEnabled(true);
		getTxtFechaVencimientoDetalle().setEditable(false);
		getTxtFechaActualizacionDetalle().setEditable(false);
		getTxtFechaActualizacionDetalle().setText(Utilitarios.fechaAhora());
		getTxtAutorizacionDetalle().setEditable(true);
		getTxtCotizacionDetalle().setEditable(false);
		getTxtValorDetalle().setEditable(true);
		getTxtSaldoDetalle().setEditable(false);
		// Seteo los combos de fechas a la fecha de hoy
		Calendar calendarFechaCarteraDetalle = new GregorianCalendar();
		calendarFechaCarteraDetalle.setTime(fechaCarteraDetalle);
		getCmbFechaCarteraDetalle().setCalendar(calendarFechaCarteraDetalle);
		// Seteo el valor de la fecha de vencimiento
		// String fechaVencimiento = dateFormatter.format(fechaCarteraDetalle);
		getTxtFechaVencimientoDetalle().setText(Utilitarios.getFechaUppercase(fechaCarteraDetalle));
		// Veo si las fechas son validas, es decir si es que no es antes del dia de hoy
		getCmbFechaCarteraDetalle().addActionListener(oActionListenerCmbFecha);
	}
	
	// Action Listener del combo de fecha reunion
	ActionListener oActionListenerCmbFecha = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			java.util.Date fechaHoy = new java.util.Date();
			
			if ((evento.getSource() == getCmbFechaCarteraDetalle())) {
				fechaCarteraDetalle = (java.util.Date) ((DateComboBox) evento.getSource()).getDate();
				fechaVencimientoDetalle = fechaCarteraDetalle;
				getTxtFechaVencimientoDetalle().setText(Utilitarios.getFechaUppercase(fechaVencimientoDetalle));
			}
		}
	};
	
	ActionListener oActionListenerCmbFechaEmision = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			fechaCartera = getCmbFechaEmisionCartera().getDate();
			if ( fechaCartera == null  )
				fechaCartera = new Date();   
			
			fechaCarteraDetalle = fechaCartera;

			Calendar calendar = new GregorianCalendar();
			calendar.setTime(fechaCarteraDetalle);
			getCmbFechaCarteraDetalle().setCalendar(calendar);
			getCmbFechaCarteraDetalle().repaint();
			
			DateFormat dateFormatter = new SimpleDateFormat("dd - MMMM - yyyy",Utilitarios.esLocale);
			Date today = new java.util.Date();
			String dateOut = dateFormatter.format(today);
			fechaVencimientoDetalle = fechaCarteraDetalle;
			getTxtFechaVencimientoDetalle().setText(Utilitarios.getFechaUppercase(fechaCarteraDetalle));
			fechaActualizacionDetalle = fechaCarteraDetalle;
			getTxtFechaActualizacionDetalle().setText(Utilitarios.getFechaUppercase(fechaActualizacionDetalle));
			fechaCreacionDetalle = fechaCarteraDetalle;
			getTxtFechaCreacionDetalle().setText(Utilitarios.getFechaUppercase(fechaActualizacionDetalle));
		}
	};
	
	ActionListener oActionListenerCmbTipoCartera = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(getCmbTipoCartera().getSelectedItem() != null){
				clienteIf = null;
				clienteOficinaIf = null;
				getTxtClienteCartera().setText("");
				getTxtClienteOficinaCartera().setText("");
				cargarComboTipoDocumento();
				
				if (getCmbTipoCartera().getSelectedItem().toString().equals(NOMBRE_TIPO_CARTERA_PROVEEDOR)) { 
					getCmbSustentoTributario().setSelectedItem(null);
					getCmbSustentoTributario().setEnabled(true);						
				} else if (getCmbTipoCartera().getSelectedItem().toString().equals(NOMBRE_TIPO_CARTERA_CLIENTE)) {
					getCmbSustentoTributario().setSelectedItem(null);
					getCmbSustentoTributario().setEnabled(false);
				}
			}			
		}
	};
	
	ActionListener oActionListenerCmbTipoDocumento = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			tipoDocumentoIf = (TipoDocumentoIf) getCmbTipoDocumento().getModel().getSelectedItem();
			
			cargarComboDocumentoDetalle();
 		}
	};
	
	// Listener del combo de documento detalle
	ActionListener oActionListenerCmbDocumentoDetalle = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			try {
				documentoIf = (DocumentoIf) getCmbDocumentoDetalle().getModel().getSelectedItem();
				
				if (documentoIf != null) {
					if (documentoIf.getDiferido() != null && documentoIf.getDiferido().equals(OPCION_SI))
						getCmbFechaCarteraDetalle().setEnabled(true);
					else {
						getTxtCarteraDetalle().setText(CARTERA_SI);
						getCmbFechaCarteraDetalle().setEnabled(true);
					}
					
					if (documentoIf.getCheque() != null && documentoIf.getCheque().equals(OPCION_SI)){
						tipoPagoIf = (TipoPagoIf) SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByCodigo(CODIGO_TIPO_PAGO_CHEQUE).iterator().next();
					}else if (documentoIf.getCodigo().equals("CFC") || documentoIf.getCodigo().equals("CFP")){
						tipoPagoIf = (TipoPagoIf) SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByCodigo(CODIGO_TIPO_PAGO_CREDITO).iterator().next();
					}else{
						tipoPagoIf = (TipoPagoIf) SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByCodigo(CODIGO_TIPO_PAGO_EFECTIVO).iterator().next();
					}
					
					// remuevo todos los items del combo de banco
					getCmbBancoDetalle().removeAllItems();
					
					getCmbCuentaBancaria().setSelectedItem(null);
					getCmbCuentaBancaria().removeAllItems();
					getCmbCuentaBancaria().validate();
					if (tipoPagoIf != null && tipoPagoIf.getNombre().contains(TIPO_PAGO_CHEQUE)) {
						SpiritComboBoxModel cmbModelBancoDetalle = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getBancoSessionService().findBancoByEstado("A"));
						getCmbBancoDetalle().setModel(cmbModelBancoDetalle);
						getCmbBancoDetalle().setEnabled(true);
					} else {
						getCmbBancoDetalle().setSelectedItem(null);
						getCmbBancoDetalle().setEnabled(false);
					}
					
					getTxtReferenciaDetalle().setEditable(true);
					getTxtPreimpresoDetalle().setEditable(true);
					
					//Si es retencion RENTA o IVA, habilito y cargo el combo de Porcentajes
					if (documentoIf.getCodigo().equals("RERC") || documentoIf.getCodigo().equals("REIC")){
						getCmbPorcentaje().setEnabled(true);
						cargarComboSriClienteRetencion(documentoIf.getCodigo());
					}else{
						getCmbPorcentaje().setEnabled(false);
					}
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	};
	
	private void modificarCmbDocumentos() throws GenericBusinessException{
		/*if(clienteOficinaIf != null){
			clienteIf = getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
		}		
		if( getCmbDocumentoDetalle().getModel().getSize() > 0 ){
			if ( tipoDocumentoIf.getCodigo().equals("CIN") || tipoDocumentoIf.getNombre().equals("COMPROBANTE DE INGRESO") ){
				if ( clienteIf!=null ){
					Collection sriClientes = getSriClienteRetencionSessionService().findSriClienteRetencionByContribuyenteEspecial(clienteIf.getContribuyenteEspecial());
					if ( sriClientes!=null && sriClientes.iterator().hasNext()){
						SriClienteRetencionIf sriCliente = (SriClienteRetencionIf) sriClientes.iterator().next();
						int indiceIva = -1,indiceRenta = -1;
						for ( int i=0; i< getCmbDocumentoDetalle().getModel().getSize();i++ ){
							DocumentoIf docIf = (DocumentoIf)getCmbDocumentoDetalle().getModel().getElementAt(i);
							if (docIf!=null){
								if ( docIf.getCodigo().equals("REIC") || docIf.getNombre().equals("RETENCION IVA CLIENTE") )
									indiceIva = i;
								if ( docIf.getCodigo().equals("RERC") || docIf.getNombre().equals("RETENCION RENTA CLIENTE") )
									indiceRenta = i;
							}
						}
						if ( !(sriCliente.getRetefuente().doubleValue() > 0.00) && indiceRenta>=0 ){
							((SpiritComboBoxModel)getCmbDocumentoDetalle().getModel()).removeElementAt(indiceRenta);
						}
						if ( !(sriCliente.getReteiva().doubleValue() > 0.00) && indiceIva>=0 ){
							((SpiritComboBoxModel)getCmbDocumentoDetalle().getModel()).removeElementAt(indiceIva);
						}
					}
				}
			}
			getCmbDocumentoDetalle().validate();
			getCmbDocumentoDetalle().repaint();
		}*/
	}
	
	public void getSelectedObject() {
		try {
			cartera = (CarteraIf) popupFinder.getElementoSeleccionado();
			loadCombos();
			
			if (TIPO_CARTERA_CLIENTE.equals(cartera.getTipo().toString())){
				getCmbTipoCartera().setSelectedItem(NOMBRE_TIPO_CARTERA_CLIENTE);
			}else{
				getCmbTipoCartera().setSelectedItem(NOMBRE_TIPO_CARTERA_PROVEEDOR);
			}
			
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(cartera.getFechaEmision());
			getCmbFechaEmisionCartera().setCalendar(calendar);
			
			if (cartera.getFechaUltimaActualizacion() != null){
				getTxtFechaCambioEstadoCartera().setText(Utilitarios.getFechaUppercase(cartera.getFechaUltimaActualizacion()));
			}
			
			clienteOficinaIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(cartera.getClienteoficinaId());
			clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
			corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
			OficinaIf oficinaIf = SessionServiceLocator.getOficinaSessionService().getOficina(cartera.getOficinaId());
			getTxtCodigoCartera().setText(cartera.getCodigo());
			getTxtPreimpreso().setText(cartera.getPreimpreso());
			getTxtClienteOficinaCartera().setText(clienteOficinaIf.getDescripcion());
			getTxtClienteCartera().setText(clienteIf.getNombreLegal());
			getTxtCorporacionCartera().setText(corporacionIf.getNombre());
			getTxtOficinaCartera().setText(oficinaIf.getNombre());
			getTxtValorCartera().setText(formatoDecimal.format(cartera.getValor().doubleValue()));
			getTxtSaldoCartera().setText(formatoDecimal.format(cartera.getSaldo().doubleValue()));
			getTxtComentariosCartera().setText(cartera.getComentario());
			valorCartera = cartera.getValor().doubleValue();
			saldoCartera = cartera.getSaldo().doubleValue();
			getCmbTipoDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoDocumento(), cartera.getTipodocumentoId()));
			if(cartera.getVendedorId() != null){
				getCmbVendedorCartera().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbVendedorCartera(), cartera.getVendedorId()));
			}
			getCmbMonedaCartera().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbMonedaCartera(), cartera.getMonedaId()));
			
			if (cartera.getReferenciaId() != null){
				getTxtReferenciaCartera().setText(cartera.getReferenciaId().toString());
			}
			
			getCmbEstadoCartera().removeAllItems();
			getCmbEstadoCartera().addItem(NOMBRE_ESTADO_NORMAL);
			getCmbEstadoCartera().addItem(NOMBRE_ESTADO_ANULADO);
			getCmbEstadoCartera().addItem(NOMBRE_ESTADO_DUDOSO);
			getCmbEstadoCartera().addItem(NOMBRE_ESTADO_CASTIGADO);
			
			if (ESTADO_NORMAL.equals(cartera.getEstado().toString())){
				getCmbEstadoCartera().setSelectedItem(NOMBRE_ESTADO_NORMAL);
			}else if (ESTADO_ANULADO.equals(cartera.getEstado().toString())){
				getCmbEstadoCartera().setSelectedItem(NOMBRE_ESTADO_ANULADO);
			}else if (ESTADO_DUDOSO.equals(cartera.getEstado().toString())){
				getCmbEstadoCartera().setSelectedItem(NOMBRE_ESTADO_DUDOSO);
			}else if (ESTADO_CASTIGADO.equals(cartera.getEstado().toString())){
				getCmbEstadoCartera().setSelectedItem(NOMBRE_ESTADO_CASTIGADO);
			}
			
			Collection carteraDetallesColeccion = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(cartera.getId());
			Iterator itCarteraDetallesColeccion = carteraDetallesColeccion.iterator();
			modelCarteraDetalle = (DefaultTableModel) getTblDetalle().getModel();
			
			while (itCarteraDetallesColeccion.hasNext()) {
				CarteraDetalleIf carteraDetalleTemp = (CarteraDetalleIf) itCarteraDetallesColeccion.next();
				DocumentoIf documento = null;
				if (carteraDetalleTemp.getDocumentoId() != null){
					documento = (DocumentoIf) SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalleTemp.getDocumentoId());
				}
				TipoPagoIf tipoPago = null;
				if (carteraDetalleTemp.getFormaPagoId() != null){
					tipoPago = (TipoPagoIf) SessionServiceLocator.getTipoPagoSessionService().getTipoPago(carteraDetalleTemp.getFormaPagoId());
				}
				LineaIf linea = null;
				if (carteraDetalleTemp.getLineaId() != null){
					linea = (LineaIf) SessionServiceLocator.getLineaSessionService().getLinea(carteraDetalleTemp.getLineaId());
				}
				// Si los valores tienen null le seteo "" dentro del objeto
				if (carteraDetalleTemp.getReferencia() == null){
					carteraDetalleTemp.setReferencia("");
				}
				if (carteraDetalleTemp.getPreimpreso() == null){
					carteraDetalleTemp.setPreimpreso("");
				}
				if (carteraDetalleTemp.getAutorizacion() == null){
					carteraDetalleTemp.setAutorizacion("");
				}
				
				String fechaCreacion = Utilitarios.getFechaCortaUppercase(carteraDetalleTemp.getFechaCreacion());
				String fechaCartera = Utilitarios.getFechaCortaUppercase(carteraDetalleTemp.getFechaCartera());
				Vector<String> filaCarteraDetalle = new Vector<String>();
				carteraDetalleColeccion.add(carteraDetalleTemp);
				filaCarteraDetalle.add(secuencialDetalle + "");
				filaCarteraDetalle.add(documento.getNombre());
				if (tipoPago != null){
					filaCarteraDetalle.add(tipoPago.getNombre());
				}else{
					filaCarteraDetalle.add(null);
				}
				if (linea != null){
					filaCarteraDetalle.add(linea.getNombre());
				}else{					
					filaCarteraDetalle.add(null);
				}
				filaCarteraDetalle.add(fechaCreacion);
				filaCarteraDetalle.add(fechaCartera);
				if (carteraDetalleTemp.getCartera().equals(OPCION_SI)){
					filaCarteraDetalle.add(CARTERA_SI);
				}else{
					filaCarteraDetalle.add(CARTERA_NO);
				}
				filaCarteraDetalle.add(formatoDecimal.format(carteraDetalleTemp.getValor().doubleValue()));
				if (carteraDetalleTemp.getSaldo().equals(new BigDecimal(0.00))){
					filaCarteraDetalle.add("0.00");
				}else{
					filaCarteraDetalle.add(formatoDecimal.format(carteraDetalleTemp.getSaldo().doubleValue()));
				}
				
				//valor diferido
				if(carteraDetalleTemp.getCartera().equals("N")){
					Double valorDiferidoCalculado = 0D;
					Collection carterasAfectaColeccion = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(carteraDetalleTemp.getId());
					Iterator carterasAfectaColeccionIterator = carterasAfectaColeccion.iterator();
					while(carterasAfectaColeccionIterator.hasNext()){
						CarteraAfectaIf carteraAfecta = (CarteraAfectaIf)carterasAfectaColeccionIterator.next();
						valorDiferidoCalculado = valorDiferidoCalculado + carteraAfecta.getValor().doubleValue();
					}
					filaCarteraDetalle.add(formatoDecimal.format(valorDiferidoCalculado));
				}else{
					filaCarteraDetalle.add("0");
				}				
				
				modelCarteraDetalle.addRow(filaCarteraDetalle);
				// Documentos cruzados con signo negativo
				Vector registrosAfectaToDetalleVector = new Vector();
				// Busco en la base los detalles cartera (-) de este detalle
				Collection registrosAfectaToDetalleColeccion = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(carteraDetalleTemp.getId());
				Iterator itRegistrosAfectaToDetalleColeccion = registrosAfectaToDetalleColeccion.iterator();
				// Barro la coleccion de detalles cartera (-)
				while (itRegistrosAfectaToDetalleColeccion.hasNext()) {
					CarteraAfectaIf carteraAfectaTemp = (CarteraAfectaIf) itRegistrosAfectaToDetalleColeccion.next();
					// Agregar a la coleccion de registrosAfectaToDetalleVector para grabar al final toda la coleccion
					registrosAfectaToDetalleVector.add(carteraAfectaTemp);
					// guardo en el mapa la coleccion de carteraAfectas (CarteraDetalle -)
					registrosAfectaToDetalleCarteraMap.put(secuencialDetalle - 1, registrosAfectaToDetalleVector);
					// Documentos cruzados con signo positivo
					Vector registrosDetallesToDetalleVector = null;
					// Veo si registrosDetallesToDetalleVector ya existe en el mapa
					if (registrosDetallesToDetalleCarteraMap.get(secuencialDetalle - 1) != null){
						registrosDetallesToDetalleVector = (Vector) registrosDetallesToDetalleCarteraMap.get(secuencialDetalle - 1);
					}else{
						registrosDetallesToDetalleVector = new Vector();
					}
					
					// Busco en la base los detalles cartera (+) de este detalle
					Collection registrosDetallesToDetalleColeccion = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleById(carteraAfectaTemp.getCarteraafectaId());
					Iterator itRegistrosDetallesToDetalleColeccion = registrosDetallesToDetalleColeccion.iterator();
					// Barro la coleccion de detalles cartera (+)
					while (itRegistrosDetallesToDetalleColeccion.hasNext()) {
						CarteraDetalleIf carteraDetallePositivaTemp = (CarteraDetalleIf) itRegistrosDetallesToDetalleColeccion.next();
						// Parte para sacar el valor que tiene diferido el carteraDetallePositivo
						/*BigDecimal valorDiferido = new BigDecimal(0.00);
						// Extraigo todos los cartera afecta al cual ha sido cruzado este cartera detalle (+) y cuyo valor cartera sea igual a N
						Collection carteraAfectaColeccion = CarteraModel.getCarteraAfectaSessionService().findCarteraAfectaByCarteraAfectaIdAndByCartera(carteraDetallePositivaTemp.getId(), "N");
						Iterator itCarteraAfectaColeccion = carteraAfectaColeccion.iterator();
						// Sumo el valor de los documentos que han sido diferidos y estan cruzando con esta cartera
						while (itCarteraAfectaColeccion.hasNext()) {
							CarteraAfectaIf carteraAfectaIt = (CarteraAfectaIf) itCarteraAfectaColeccion.next();
							// Sumo el valor del cartera afecta
							valorDiferido = valorDiferido.add(carteraAfectaIt.getValor());
						}
						// Parte para sacar el valor que tiene diferido el carteraDetallePositivo
						// Mando a setear el saldo del cartera detalle que voy a insertar en la tabla
						carteraDetallePositivaTemp.setSaldo(carteraDetallePositivaTemp.getSaldo().add(valorDiferido));*/
						// Agregar a la coleccion de registrosDetallesToDetalleVector para grabar al final toda la coleccion
						registrosDetallesToDetalleVector.add(carteraDetallePositivaTemp);
						// guardo en el mapa la coleccion de CarteraDetalle +
						registrosDetallesToDetalleCarteraMap.put(secuencialDetalle - 1,registrosDetallesToDetalleVector);
					}
				}
				
				// Incremento en 1 el secuencial del detalle
				secuencialDetalle++;
			}
			
			getTxtSecuencialDetalle().setText("" + secuencialDetalle);
			this.showUpdateMode();
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	 
	
	WindowListener wl = new WindowAdapter(){

		@Override
		public void windowClosed(WindowEvent e) {
			jdAfectaCartera = null;
			System.gc();
		}		
	};
	
	@SuppressWarnings("unchecked")
	private void enablePopupAfectaCartera() {
		try {
			// Si la fila seleccionado es valida
			if (filaSeleccionadaTablaDetalle != -1 && clienteIf != null) {
				DocumentoIf documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalleColeccion.get(filaSeleccionadaTablaDetalle).getDocumentoId());
				//DocumentoIf documento = (DocumentoIf) getCmbDocumentoDetalle().getSelectedItem();
				if((documento != null) && documento.getCodigo().equals(CODIGO_DOC_CLI_NIVELACION_POSITIVA)){
					SpiritAlert.createAlert("No es posible cruzar directamente una nivelación positiva", SpiritAlert.INFORMATION);
				}else if((documento != null) && documento.getCodigo().equals(CODIGO_DOC_COMP_INGRESO_PAGO_EMPLEADO)){
					SpiritAlert.createAlert("No es posible cruzar directamente un comprobante de ingreso pago empleado", SpiritAlert.INFORMATION);
				}else{
					
					// Cargo el vector que contienes los registros de CARTERADETALLE que han sido cruzados con este detalle de cartera
					if (registrosDetallesToDetalleCarteraMap.get(filaSeleccionadaTablaDetalle) != null){
						registrosDetallesToDetalleCarteraVector = (Vector) registrosDetallesToDetalleCarteraMap.get(filaSeleccionadaTablaDetalle);
					}else{
						registrosDetallesToDetalleCarteraVector = new Vector();
					}
					
					// Cargo el vector que contienes los registros de CARTERADETALLE que han sido cruzados con este detalle de cartera
					if (registrosAfectaToDetalleCarteraMap.get(filaSeleccionadaTablaDetalle) != null){
						registrosAfectaToDetalleCarteraVector = (Vector) registrosAfectaToDetalleCarteraMap.get(filaSeleccionadaTablaDetalle);
					}else{
						registrosAfectaToDetalleCarteraVector = new Vector();
					}
					
					String tipoCartera;
					
					if (NOMBRE_TIPO_CARTERA_CLIENTE.equals(getCmbTipoCartera().getSelectedItem())){
						tipoCartera = TIPO_CARTERA_CLIENTE;
					}else{
						tipoCartera = TIPO_CARTERA_PROVEEDOR;
					}
					
					//creo un clon de los mapas y vectores para poder regresar al estado anterior en caso de ser necesario
					try {
						mapaRegistrosSeleccionadosMapTemp = (Map)ObjectCloner.deepCopy(mapaRegistrosSeleccionadosMap);
						registrosDetallesToDetalleCarteraVectorTemp = (Vector)ObjectCloner.deepCopy(registrosDetallesToDetalleCarteraVector);
						registrosDetallesToDetalleCarteraMapTemp = (Map)ObjectCloner.deepCopy(registrosDetallesToDetalleCarteraMap);
						registrosAfectaMapTemp = (Map)ObjectCloner.deepCopy(registrosAfectaMap);
						registrosAfectaToDetalleCarteraVectorTemp = (Vector)ObjectCloner.deepCopy(registrosAfectaToDetalleCarteraVector);
						registrosAfectaToDetalleCarteraMapTemp = (Map)ObjectCloner.deepCopy(registrosAfectaToDetalleCarteraMap);
					} catch (Exception e) {
						e.printStackTrace();
					}
					/*
					Runtime r = Runtime.getRuntime(); 
					long freeMem = r.freeMemory(); 
				    System.out.println("free memory before creating array: " + freeMem);
					*/
					
					Long carteraUpdateId = null;
					if((getMode() == SpiritMode.UPDATE_MODE) && cartera != null){
						carteraUpdateId = cartera.getId();
					}
					
					//Cargo en un popup los registros que estan siendo cruzados con este detalle si es que hay
					jdAfectaCartera = new JDAfectaCartera(Parametros.getMainFrame(), camposDetalle(documento), clienteOficinaIf.getId(), tipoCartera, registrosAfectaMap,
							registrosDetallesToDetalleCarteraVector, registrosAfectaToDetalleCarteraVector, getMode(),mapaRegistrosSeleccionadosMap,mapaRegistrosSeleccionadosMapTemp,registrosAfectaMapTemp,
							carteraAfectaEliminarColeccion,carteraActualizadaColeccion,carteraUpdateId);
					jdAfectaCartera.addWindowListener(wl);
					int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
					int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
					jdAfectaCartera.setLocation(x, y);
					jdAfectaCartera.pack();
					jdAfectaCartera.setModal(true);
					jdAfectaCartera.setVisible(true);
				
				}
			}else if (clienteIf == null){
				SpiritAlert.createAlert("Seleccione por favor el cliente!!!", SpiritAlert.INFORMATION);
			}else{
				SpiritAlert.createAlert("Seleccione el detalle de cartera a trabajar!!!", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e1) {
			e1.printStackTrace();
		}
	}
	
	public void resetearMapaRegistrosSeleccionadosMap(){
		//Regreso el mapa de mapas al estado anterior
		mapaRegistrosSeleccionadosMap = mapaRegistrosSeleccionadosMapTemp;		
	}
	
	public void resetearRegistrosDetallesToDetalleVector(){
		registrosDetallesToDetalleCarteraVector = registrosDetallesToDetalleCarteraVectorTemp;
	}
	
	public void resetearRegistrosDetallesToDetalleCarteraMapTemp(){
		registrosDetallesToDetalleCarteraMap = registrosDetallesToDetalleCarteraMapTemp;
	}
	
	public void resetearRegistrosAfectaMap(){
		registrosAfectaMap = registrosAfectaMapTemp;
	}
	
	public void resetearRegistrosAfectaToDetalleCarteraVector(){
		registrosAfectaToDetalleCarteraVector = registrosAfectaToDetalleCarteraVectorTemp;
	}
	
	public void resetearRegistrosAfectaToDetalleCarteraMapTemp(){
		registrosAfectaToDetalleCarteraMap = registrosAfectaToDetalleCarteraMapTemp;
	}
	
	public void guardarDatosAfectaCartera() {
		DocumentoIf documento = (DocumentoIf)getCmbDocumentoDetalle().getSelectedItem();
		if ((getMode() == SpiritMode.UPDATE_MODE) && !documento.getCodigo().equals(DOCUMENTO_ANTICIPO_CLIENTES) 
				&& !documento.getCodigo().equals(ANTICIPO_PROVEEDOR_CHEQUE) && !documento.getCodigo().equals(ANTICIPO_PROVEEDOR_EFECTIVO)
				&& !documento.getCodigo().equals(ANTICIPO_PROVEEDOR_PRESTAMO_ACCIONISTA)
				&& !documento.getCodigo().equals(DOCUMENTO_ANTICIPO_POR_SALDO) && !documento.getCodigo().equals(NOTA_INTERNA_CREDITO_CLIENTE)
				&& !documento.getCodigo().equals(NOTA_CREDITO_ANTICIPO_CLIENTE)
				&& !documento.getCodigo().equals(CRUCE_FACTURA_PROVEEDOR)
				&& !documento.getCodigo().equals(RECIBO_CAJA_EFECTIVO)
				&& !documento.getCodigo().equals(RECIBO_CAJA_TARJETA_CREDITO)
				&& !documento.getCodigo().equals(NOTA_CREDITO_PROVEEDOR)) {
			
			SpiritAlert.createAlert("No es posible realizar el cruce de documentos!", SpiritAlert.WARNING);

		}else{			
			Vector registrosDetallesToDetalleCarteraVector = jdAfectaCartera.getRegistrosDetallesToDetalleVector();
			Vector registrosAfectaToDetalleCarteraVector = jdAfectaCartera.getRegistrosAfectaToDetalleVector();
			// LLamo a la coleccion de registros CARTERADETALLE que fueron cruzados
			// con este detalle en la ventana de afecta cartera y la guardo en el mapa, si es que no esta vacio
			if(!registrosDetallesToDetalleCarteraVector.isEmpty()){
				registrosDetallesToDetalleCarteraMap.put(filaSeleccionadaTablaDetalle, registrosDetallesToDetalleCarteraVector);
			}else{
				registrosDetallesToDetalleCarteraMap.remove(filaSeleccionadaTablaDetalle);
			}
			// LLamo a la coleccion de registros CARTERAAFECTA que fueron cruzados
			// con este detalle en la ventana de afecta cartera y la guardo en el mapa, si es que no esta vacio
			if(!registrosAfectaToDetalleCarteraVector.isEmpty()){
				registrosAfectaToDetalleCarteraMap.put(filaSeleccionadaTablaDetalle, registrosAfectaToDetalleCarteraVector);
			}else{
				registrosAfectaToDetalleCarteraMap.remove(filaSeleccionadaTablaDetalle);
			}
			
			// Variable que contiene el valor del saldo del detalle que fue cruzado conlos documentos
			String saldoDetalle = Utilitarios.removeDecimalFormat(jdAfectaCartera.getTxtSaldoDetalle().getText());
			// Mando a sumar el valor del saldo del detalle que ha sido modificado
			saldoCartera = saldoCartera + jdAfectaCartera.getSaldoDetalleCartera();
			// Mando a restar el valor del saldo del detalle que tenia la cartera anteriormente
			saldoCartera = saldoCartera - Double.valueOf(Utilitarios.removeDecimalFormat(getTxtSaldoDetalle().getText()));
			// Muestro en el panel de cartera el saldo actualizado
			getTxtSaldoCartera().setText(formatoDecimal.format(saldoCartera));
			
			// Muestro en el panel de detalle cartera la informacion del popup
			getTxtSaldoDetalle().setText(formatoDecimal.format(Double.valueOf(saldoDetalle)));
			// Actualizo el la tabla el valor del saldo del detalle
			modelCarteraDetalle.setValueAt(formatoDecimal.format(Double.valueOf(saldoDetalle)), getTblDetalle().getSelectedRow(), 8);
			
			// Extraigo el objeto detalle del arreglo
			CarteraDetalleIf data = (CarteraDetalleIf) carteraDetalleColeccion.get(getTblDetalle().getSelectedRow());
			// Modifico su saldo
			data.setSaldo(BigDecimal.valueOf(jdAfectaCartera.getSaldoDetalleCartera()));
			// Actualizar en la coleccion de detalleCarteraColeccion el registro que fue cambiado
			carteraDetalleColeccion.set(getTblDetalle().getSelectedRow(), data);
			

			jdAfectaCartera.setVisible(false);
			
			// Veo si han habido documentos cruzados para este detalle
			if (registrosDetallesToDetalleCarteraVector.size() > 0 && registrosAfectaToDetalleCarteraVector.size() > 0){
				SpiritAlert.createAlert("Documentos cruzados exitosamente!!!", SpiritAlert.INFORMATION);
			}else{
				SpiritAlert.createAlert("El detalle no ha sido cruzado con ningún documento!!!", SpiritAlert.WARNING);
			}
		}		
	}
	
	private void eliminarCarteraDetalle() {
		if (getTblDetalle().getSelectedRow() != -1) {
			// Contador y vector de registros cruazods con este detalle
			int registrosDetallesToDetalle = 0;
			int registrosAfectaToDetalle = 0;
			Vector registrosDetallesToDetalleCarteraVector = null;
			Vector registrosAfectaToDetalleCarteraVector = null;
			// Saco el vector que contiene los documentos CARTERADETALLE que estan siendo cruzados con este detalle
			if (registrosDetallesToDetalleCarteraMap.get(filaSeleccionadaTablaDetalle) != null) {
				registrosDetallesToDetalleCarteraVector = (Vector) registrosDetallesToDetalleCarteraMap.get(filaSeleccionadaTablaDetalle);
				registrosDetallesToDetalle = registrosDetallesToDetalleCarteraVector.size();
			}
			// Saco el vector que contiene los documentos CARTERAAFECTA que estan siendo cruzados con este detalle
			if (registrosAfectaToDetalleCarteraMap.get(filaSeleccionadaTablaDetalle) != null) {
				registrosAfectaToDetalleCarteraVector = (Vector) registrosAfectaToDetalleCarteraMap.get(filaSeleccionadaTablaDetalle);
				registrosAfectaToDetalle = registrosAfectaToDetalleCarteraVector.size();
			}
			
			// Si el detalle no tiene documentos con los cuales ha sido cruzado, permito borrar el detalle
			if (registrosDetallesToDetalle == 0 && registrosAfectaToDetalle == 0) {
				// Si existen detalles del resto de las filas posteriores a la del registro que se borro, le resto en uno la clave del mapa
				for (int x = getTblDetalle().getSelectedRow() + 1; x < getTblDetalle().getRowCount(); x++) {
					// para los documentos CARTERADETALLE con el cual se cruzan los detalles
					registrosDetallesToDetalleCarteraVector = (Vector) registrosDetallesToDetalleCarteraMap.get(x);
					// Si el detalle del contacto existe lo guardo en el mapa con un indice menos
					if (registrosDetallesToDetalleCarteraVector != null){
						registrosDetallesToDetalleCarteraMap.put(x - 1, registrosDetallesToDetalleCarteraVector);
						registrosDetallesToDetalleCarteraMap.remove(x);
					}
					// para los documentos CARTERAAFECTA con el cual se cruzan los detalles
					registrosAfectaToDetalleCarteraVector = (Vector) registrosAfectaToDetalleCarteraMap.get(x);
					// Si el detalle del contacto existe lo guardo en el mapa con un indice menos
					if (registrosAfectaToDetalleCarteraVector != null){
						registrosAfectaToDetalleCarteraMap.put(x - 1, registrosAfectaToDetalleCarteraVector);
						registrosAfectaToDetalleCarteraMap.remove(x);
					}
					
					// Extraigo el objeto detalle de cartera de la coleccion
					CarteraDetalleIf carteraDetalleTemp = (CarteraDetalleIf) carteraDetalleColeccion.get(x);
					// Saco el indice del detalle para decrementarlo en 1
					int indiceDetalle = carteraDetalleTemp.getSecuencial() - 1;
					// Actualizo el valor del secuencial en el objeto
					carteraDetalleTemp.setSecuencial(indiceDetalle);
					// Vuelvo a insertar a la coleccion
					carteraDetalleColeccion.set(x, carteraDetalleTemp);
					// Actualizo en la tabla
					modelCarteraDetalle.setValueAt(indiceDetalle, x, 0);
				}
				// Elimino en 1 el secuencialDetalle
				secuencialDetalle--;
				// Extraigo el objeto detalle de cartera de la coleccion segun el registro seleccionado de la tabla
				CarteraDetalleIf carteraDetalleTemp = (CarteraDetalleIf) carteraDetalleColeccion.get(getTblDetalle().getSelectedRow());
				// Disminuyo la variable que guarda el saldo de la cartera
				valorCartera = valorCartera - carteraDetalleTemp.getValor().doubleValue();
				getTxtValorCartera().setText(formatoDecimal.format(valorCartera));
				// Disminuyo la variable que guyarda el saldo de la cartera
				saldoCartera = saldoCartera - carteraDetalleTemp.getSaldo().doubleValue();
				getTxtSaldoCartera().setText(formatoDecimal.format(saldoCartera));
				// Veo si el cliente oficina ya esta insertado en la base, debido a que este registro puede estar referenciado en otra tabla
				if (carteraDetalleTemp.getId() != null) {
					//CarteraModel.getCarteraDetalleSessionService().deleteCarteraDetalle(carteraDetalleTemp.getId());
					carteraDetalleEliminadoColeccion.add(carteraDetalleTemp);
					// Elimino el registro de la coleccion y de la Tabla
					carteraDetalleColeccion.remove(getTblDetalle().getSelectedRow());
					modelCarteraDetalle.removeRow(getTblDetalle().getSelectedRow());
					SpiritAlert.createAlert("Detalle eliminado con éxito !!!", SpiritAlert.INFORMATION);
				}
				// Sino existe, simplemente lo borro de la tabla y de la coleccion
				else {
					carteraDetalleColeccion.remove(getTblDetalle().getSelectedRow());
					modelCarteraDetalle.removeRow(getTblDetalle().getSelectedRow());
				}
				reiniciarComponentesDetalle();
			} else{
				SpiritAlert.createAlert("El detalle no puede ser eliminado debido a que ha sido cruzado !!!", SpiritAlert.WARNING);
			}
		} else{
			SpiritAlert.createAlert("Debe elegir el registro de la lista a eliminar !!!", SpiritAlert.WARNING);
		}
	}
	
	public DefaultTableModel getModelCarteraDetalle() {
		return modelCarteraDetalle;
	}
	
	/*private void generarAsientoAutomaticoAnulacionFactura(CarteraIf cartera, CarteraDetalleIf carteraDetalle, boolean procesarAsiento) throws GenericBusinessException {
		Map parameterMap = new HashMap();
		
		if (getCmbTipoDocumento().getSelectedItem() != null) {
			DocumentoIf documentoIf = getDocumentoSessionService().getDocumento(carteraDetalle.getDocumentoId());
			if (documentoIf != null) {
				Iterator carteraAfectaColeccionIt = getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(carteraDetalle.getId()).iterator();
				while (carteraAfectaColeccionIt.hasNext()) {
					CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf) carteraAfectaColeccionIt.next();
					CarteraDetalleIf carteraDetalleIf = getCarteraDetalleSessionService().getCarteraDetalle(carteraAfectaIf.getCarteraafectaId());
					CarteraIf carteraIf = getCarteraSessionService().getCartera(carteraDetalleIf.getCarteraId());
					FacturaIf factura = getFacturaSessionService().getFactura(carteraIf.getReferenciaId());
					PedidoIf pedido = getPedidoSessionService().getPedido(factura.getPedidoId());
					Iterator facturaDetalleColeccionIt = getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(factura.getId()).iterator();
					AnulacionFacturaAsientoAutomaticoHandler.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
					while (facturaDetalleColeccionIt.hasNext()) {
						FacturaDetalleIf facturaDetalle = (FacturaDetalleIf) facturaDetalleColeccionIt.next();
						Iterator eventoContableIterator = getEventoContableSessionService().findEventoContableByDocumentoId(documentoIf.getId()).iterator();
						if (eventoContableIterator.hasNext()) {
							EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();
							if (eventoContable != null) {
								TipoDocumentoIf tipoDocumentoFactura = (TipoDocumentoIf) getTipoDocumentoSessionService()..factura.getTipodocumentoId());
								Double descuento = Double.valueOf(facturaDetalle.getDescuento().doubleValue());
								Double descuentoGlobal = Double.valueOf(facturaDetalle.getDescuentoGlobal().doubleValue());
								Double iva = Double.valueOf(facturaDetalle.getIva().doubleValue());
								Double valor = Double.valueOf(facturaDetalle.getValor().doubleValue());
								//Double total = valor + iva - descuento - descuentoGlobal;
								Double total = valor - descuento - descuentoGlobal;
								parameterMap.clear();
								parameterMap.put("BEAN_CARTERA", cartera);
								parameterMap.put("BEAN_CARTERA_DETALLE", carteraDetalle);
								parameterMap.put("BEAN_FACTURA", factura);
								parameterMap.put("CTAXCOB", total + iva);
								parameterMap.put("IVA", iva);
								
								if (tipoDocumentoFactura.getReembolso().equals("S"))
									parameterMap.put("CTAXPAG", total);
								else
									parameterMap.put("INGRESO", total);
								parameterMap.put("PRODUCTO_ID", facturaDetalle.getProductoId());
								ProductoIf producto = getProductoSessionService().getProducto(facturaDetalle.getProductoId());
								parameterMap.put("PROVEEDOR_ID", producto.getProveedorId());
								parameterMap.put("TIPO_DOCUMENTO_ID", cartera.getTipodocumentoId());
								new AnulacionFacturaAsientoAutomaticoHandler(eventoContable, parameterMap, tipoDocumentoFactura, pedido.getTiporeferencia(), procesarAsiento);
							}
						}
					}
				}
			}
		}
	}*/
}