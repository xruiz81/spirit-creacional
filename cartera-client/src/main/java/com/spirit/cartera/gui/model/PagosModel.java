package com.spirit.cartera.gui.model;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.CarteraPagoData;
import com.spirit.cartera.entity.CarteraPagoIf;
import com.spirit.cartera.gui.controller.WalletConstants;
import com.spirit.cartera.gui.panel.JPPagos;
import com.spirit.cartera.handler.ComprobanteEgresoData;
import com.spirit.cartera.handler.EstadoCarteraPago;
import com.spirit.cartera.handler.FormatoPagoMulticashData;
import com.spirit.cartera.handler.ProviderPaymentReceiptRowData;
import com.spirit.cartera.handler.WalletData;
import com.spirit.cartera.handler.WalletDetailData;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.gui.data.AutorizarAsientoData;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.ServidorIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.entity.TipoTroqueladoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.panel.JDCheque;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class PagosModel extends JPPagos {
	
	private static final long serialVersionUID = -9067616216906797390L;
	private static final String APROBADO_SI = "S";
	private static final String APROBADO_NO = "N";
	private static final String CARTERA_SI = "S";
	private static final String CARTERA_DIFERIDA = "D";
	private static final String NOMBRE_CAJA = "CAJA";
	private static final String CODIGO_PAGO_CHEQUE = "PACH";
	private static final String CODIGO_PAGO_CONTADO = "PACO";
	private static final String CODIGO_PRESTAMO_ACCIONISTA = "PAPA";
	private static final String CODIGO_PAGO_DEBITO_BANCARIO = "PADB";
	private static final String TIPO_PAGO_CHEQUE = "CH";
	private static final String TIPO_PAGO_EFECTIVO = "EF";
	private static final String TIPO_PAGO_VARIOS = "VA";
	private static final String TIPO_PAGO_DEBITO_BANCARIO = "DB";
	private static final String NOMBRE_BASE_FORMAPAGO = "FORMAPAGO";
	private static final String CODIGO_PAGO_ABONO_DIFERIDO = "PADI";
	private Vector<CarteraIf> carteraColeccion = new Vector<CarteraIf>();
	private Vector<CarteraDetalleIf> carteraPagoDetalleColeccion = new Vector<CarteraDetalleIf>();
	private DefaultTableModel tableModel;
	private ClienteOficinaIf clienteOficina;
	private ClienteIf cliente;
	private CompraIf compra;
	private CarteraIf cartera;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DecimalFormat formato13DigitosMulticash = new DecimalFormat("0000000000000");
	private DecimalFormat formato10DigitosMulticash = new DecimalFormat("0000000000");
	private Calendar fechaCobro;
	private Vector<CuentaBancariaIf> cuentasBancos = new Vector<CuentaBancariaIf>();
	private Vector<CuentaBancariaIf> cuentasBancosSeleccionadas = new Vector<CuentaBancariaIf>();
	private Calendar[] fechasColeccion = new Calendar[]{};
	private List<CarteraDetalleIf> carteraDetalleColeccion = new ArrayList<CarteraDetalleIf>();
	private List<CarteraDetalleIf> carteraDetalleColeccionCheques = new ArrayList<CarteraDetalleIf>();
	private List<CarteraDetalleIf> carteraDetalleColeccionChequesImprimir = new ArrayList<CarteraDetalleIf>();
	private Vector<Boolean> pagarColeccion = new Vector<Boolean>();
	private Vector<String> numerosCheque = new Vector<String>();
	private Calendar calendar = new GregorianCalendar();
	private ArrayList<String[]> cheques = new ArrayList<String[]>();
	private JDCheque jdCheque;
	private Vector<ClienteIf> proveedoresColeccion = new Vector<ClienteIf>();
	protected JDPopupFinderModel popupFinder;
	protected ClienteOficinaCriteria clienteOficinaCriteria;
	private ClienteOficinaIf proveedorIf;
	private Vector<ComprobanteEgresoData> comprobanteEgresoColeccion = new Vector<ComprobanteEgresoData>();
	private Vector<FormatoPagoMulticashData> pagoMulticashColeccion = new Vector<FormatoPagoMulticashData>();
	Map<Long,TipoDocumentoIf> tiposDocumentoMap = new HashMap<Long,TipoDocumentoIf>();
	Map<Long,BancoIf> bancosMap = new HashMap<Long,BancoIf>();
	Map<Long,CuentaBancariaIf> cuentasBancariasMap = new HashMap<Long,CuentaBancariaIf>();
	Map documentosMap = new HashMap();
	private Vector<CarteraIf> carterasPagadasColeccion = new Vector<CarteraIf>();
	private Vector<CarteraIf> carterasComprasPagadasColeccion = new Vector<CarteraIf>();
	private Vector<WalletData> walletDataVector = new Vector<WalletData>();
	private Map<Long,Integer> numerosChequeMap = new HashMap<Long,Integer>();
	private Integer numeroChequeActual = 0;
	private static final int MAX_LONGITUD_NUMERO_CHEQUE = 17;
	boolean numerosChequeDiferentes = false;
	private static final String NOMBRE_ESTADO_ANULADO = "ANULADO";
	private static final String ESTADO_ANULADO = NOMBRE_ESTADO_ANULADO.substring(0, 1);
	boolean nuevaCodificacionActivada = true;	
	private Map<String,ParametroEmpresaIf> mapaParametrosEmpresa = null;
	private BigDecimal totalAprobado = new BigDecimal(0);
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no}; 
	private Map cuentasMap = new HashMap(); 
	private Map usuariosMap = new HashMap();
	private Map empleadosMap = new HashMap();
	private Map<Long,Long> carterasEgresoMulticashMap = new HashMap<Long,Long>(); 
	
	
	public PagosModel(){
		anchoColumnasTabla();
		initKeyListeners();
		iniciarMapasReporteAsientos();
		initListeners();
		showSaveMode();
		new HotKeyComponent(this);
	}
	
	public void initKeyListeners(){
		getCmbFechaCobro().setLocale(Utilitarios.esLocale);
		//getCmbFechaCobro().setShowNoneButton(false);
		getCmbFechaCobro().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaCobro().setEditable(false);	
		//getCmbFechaCobro().getDateModel().setMinDate(calendar);		
		
		getBtnProveedor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnProveedor().setToolTipText("Buscar Proveedor");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnConsultar().setToolTipText("Consultar");		
		getBtnFechaCobro().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnFechaCobro().setToolTipText("Actualizar Fecha");
		getBtnNumerosCheque().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnNumerosCheque().setToolTipText("Actualizar numeros");
				
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblPagos().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		getTblPagos().getColumnModel().getColumn(2).setCellRenderer(tableCellRendererCenter);
		getTblPagos().getColumnModel().getColumn(3).setCellRenderer(tableCellRendererCenter);
		
	}
	
	private void iniciarMapasReporteAsientos(){
		try {
			cuentasMap = SessionServiceLocator.getCuentaSessionService().mapearCuentas(Parametros.getIdEmpresa());
			usuariosMap = SessionServiceLocator.getUsuarioSessionService().mapearUsuarios(Parametros.getIdEmpresa());
			empleadosMap = SessionServiceLocator.getEmpleadoSessionService().mapearEmpleados(Parametros.getIdEmpresa());
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void anchoColumnasTabla() {
		getTblPagos().getTableHeader().setReorderingAllowed(false);
		getTblPagos().setCellSelectionEnabled(true);
		getTblPagos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblPagos().addMouseListener(oMouseAdapterTblPagos);
		getTblPagos().addKeyListener(oKeyAdapterTblPagos);
		//getTblPagos().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn anchoColumna = getTblPagos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(45);
		anchoColumna = getTblPagos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
		anchoColumna = getTblPagos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(125);
		anchoColumna = getTblPagos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(85);	
		anchoColumna = getTblPagos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblPagos().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(330);
		anchoColumna = getTblPagos().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblPagos().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(105);
		anchoColumna = getTblPagos().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(300);
	}

	
	MouseListener oMouseAdapterTblPagos = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblPagos = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int row = ((JTable)evt.getSource()).getSelectedRow();
			int column = ((JTable)evt.getSource()).getSelectedColumn();
			getTblPagos().getSelectionModel().setSelectionInterval(row, row);
			getTblPagos().getColumnModel().getSelectionModel().setSelectionInterval(column, column);
			
			if(column == 0){
				if((Boolean)getTblPagos().getModel().getValueAt(row,0)){
					fechaCobro = new GregorianCalendar();
					fechasColeccion[row] = fechaCobro;
					getTblPagos().setValueAt(Utilitarios.getFechaCortaUppercase(fechaCobro.getTime()), row, 7);
				}else if(!((Boolean)getTblPagos().getModel().getValueAt(row,0))){
					fechasColeccion[row] = null;
					getTblPagos().setValueAt("", row, 7);
					getTblPagos().setValueAt("", row, 6);
					getTblPagos().setValueAt("", row, 5);
				}
				getTblPagos().repaint();
			}			
		}
	}
	
	ActionListener oActionListenerNumeroCheque = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			String cuentaBancariaS = "";
			numerosChequeMap.clear();
			generarColeccionPagos();
			if(pagarColeccion.indexOf(true) == -1){
				SpiritAlert.createAlert("Debe seleccionar al menos un pago !",SpiritAlert.WARNING);
			} else {
				for(int i=0; i<pagarColeccion.size(); i++){
					if(pagarColeccion.get(i)){
						for(CuentaBancariaIf cuentaBancaria : cuentasBancos){
							if(cuentaBancaria != null){
								BancoIf banco = (BancoIf) bancosMap.get(cuentaBancaria.getBancoId());
								cuentaBancariaS = banco.getNombre() + ", Cta. " + cuentaBancaria.getCuenta();
								if(cuentaBancariaS.equals(getTblPagos().getModel().getValueAt(i,5).toString())){
									//Si se descomenta las lineas, el numero se va incrementando en 1.
									/*if(numerosChequeMap.get(cuentaBancaria.getId()) != null){
										numeroChequeActual = numerosChequeMap.get(cuentaBancaria.getId())+1;
									}else{*/
										numeroChequeActual = Integer.parseInt(cuentaBancaria.getNumeroCheque())+1;
									//}
									numerosChequeMap.put(cuentaBancaria.getId(),numeroChequeActual);
									getTblPagos().setValueAt(numeroChequeActual.toString(), i, 6);
								}else if(getTblPagos().getModel().getValueAt(i,5).toString().equals("")){
									SpiritAlert.createAlert("Debe seleccionar una cuenta bancaria", SpiritAlert.INFORMATION);
									getTblPagos().getSelectionModel().setSelectionInterval(i, i);
									getTblPagos().getColumnModel().getSelectionModel().setSelectionInterval(5, 5);
									break;
								}
							}							
						}												
					} 
				}
			}
 		}
	};
	
	private void initListeners() {
		getBtnFechaCobro().addActionListener(oActionListenerBtnFechaCobro);
		getBtnNumerosCheque().addActionListener(oActionListenerNumeroCheque);
		
		getCbTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCbProveedor().setSelected(false);
			}
		});
		getCbProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCbTodos().setSelected(false);
			}
		});
		getBtnProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarProveedor();
			}
		});		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(!getCbProveedor().isSelected()){
					clean();
					proveedorIf = null;
					getTxtProveedor().setText("");
					cargarTabla();
				}else if(proveedorIf != null){
					clean();
					cargarTabla();
				}else{
					SpiritAlert.createAlert("Debe buscar un proveedor!", SpiritAlert.INFORMATION);
				}
			}
		});
	}
	
	ActionListener oActionListenerBtnFechaCobro = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getTblPagos().getSelectedRow() != -1) {				
				int row = getTblPagos().getSelectedRow();
				fechaCobro = getCmbFechaCobro().getCalendar();
				fechasColeccion[row] = fechaCobro;
				if(fechaCobro != null){
					getTblPagos().setValueAt(Utilitarios.getFechaCortaUppercase(fechaCobro.getTime()), row, 7);
				}else{
					getTblPagos().setValueAt("", row, 7);
				}
				
				getTblPagos().grabFocus();
			}else{
				SpiritAlert.createAlert("Debe seleccionar primero una fila de la tabla!", SpiritAlert.WARNING);
			}
		}
	};
	
	private void buscarProveedor() {
		Long idCorporacion = 0L;
		Long idCliente = 0L;
		String tipoCliente = "PR";
		String tituloVentanaBusqueda = "Proveedores";
				
		clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, "", false);
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.addElement(70);
		anchoColumnas.addElement(200);
		anchoColumnas.addElement(80);
		anchoColumnas.addElement(230);
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
		if ( popupFinder.getElementoSeleccionado() != null )
			setProveedor(popupFinder.getElementoSeleccionado());
	}
	
	private void setProveedor(Object proveedorObjeto) {
		clean();
		proveedorIf = (ClienteOficinaIf) proveedorObjeto;
		
		try {
			ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(proveedorIf.getId());
			ClienteIf proveedor= (ClienteIf) SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
			getTxtProveedor().setText(proveedor.getIdentificacion() + " - " + proveedor.getNombreLegal() + " - " + proveedorIf.getDescripcion());
			cargarTabla();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al setear el proveedor", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void generarColeccionesFormaChequeFecha() throws GenericBusinessException{
		if(!getCarteraColeccion().isEmpty()){
			cuentasBancosSeleccionadas = null;
			cuentasBancosSeleccionadas = new Vector<CuentaBancariaIf>();
			numerosCheque = null;
			numerosCheque = new Vector<String>();
			String cuentaBancariaS;
			int contador = 0;
			Map numeroChequeMap = new HashMap();
			numerosChequeDiferentes = false;
			String formaPagoSeleccionada = "";
			for(int j=0; j<pagarColeccion.size(); j++){
				formaPagoSeleccionada = (String)getTblPagos().getModel().getValueAt(j,5);
				if(!pagarColeccion.get(j) || NOMBRE_CAJA.equals(formaPagoSeleccionada)
						|| (formaPagoSeleccionada.indexOf(" (DEBITO)") != -1) 
						|| mapaParametrosEmpresa.containsKey(formaPagoSeleccionada) ){
					cuentasBancosSeleccionadas.add(null);
					numerosCheque.add(null);
				}else{
					for(CuentaBancariaIf cuentaBancaria : cuentasBancos){
						if(cuentaBancaria != null){
							BancoIf banco = (BancoIf) bancosMap.get(cuentaBancaria.getBancoId());
							cuentaBancariaS = banco.getNombre() + ", Cta. " + cuentaBancaria.getCuenta();
							if(cuentaBancariaS.equals(formaPagoSeleccionada)){
								cuentasBancosSeleccionadas.add(cuentaBancaria);
								if((getTblPagos().getModel().getValueAt(j,6) != null) && !getTblPagos().getModel().getValueAt(j,6).equals("")){
									numerosCheque.add((String)getTblPagos().getModel().getValueAt(j,6));
									contador++;
									String numeroCheque = (String)getTblPagos().getModel().getValueAt(j,6);
									CarteraDetalleIf carteraDetallePago = getCarteraPagoDetalleColeccion().get(j);
									if(numeroChequeMap.get(carteraDetallePago.getCarteraId()) != null) {
										if (!((String)numeroChequeMap.get(carteraDetallePago.getCarteraId())).equals(numeroCheque)) {
											numerosChequeDiferentes = true;
											break;
										}
									} else {
										numeroChequeMap.put(carteraDetallePago.getCarteraId(), numeroCheque);
									}
								}
							}
						}							
					}	
				}
			}			
		}		
	}

	private void generarColeccionPagos() {
		//pagarColeccion.clear();
		pagarColeccion = null;
		pagarColeccion = new Vector<Boolean>();
		boolean pagoSeleccionado = false;
		for(int i=0; i<getCarteraPagoDetalleColeccion().size(); i++){
			pagoSeleccionado = (Boolean)getTblPagos().getModel().getValueAt(i,0);
			pagarColeccion.add(pagoSeleccionado);
		}
	}
	
	private CarteraDetalleIf registrarCarteraDetalle(int i, CarteraDetalleIf carteraDetalle){
		Map mapaTipoPago = new HashMap();
		try {
			if(cuentasBancosSeleccionadas.get(i) == null){
				String formaPagoSeleccionada = (String)getTblPagos().getModel().getValueAt(i,5);
				if ( mapaParametrosEmpresa.containsKey(formaPagoSeleccionada) ){
					//ParametroEmpresaIf parametro = mapaParametrosEmpresa.get(formaPagoSeleccionada);
					//DocumentoIf documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo(CODIGO_PRESTAMO_ACCIONISTA).iterator().next();
					
					DocumentoIf documento = null;
					Iterator itDoc= SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo(CODIGO_PRESTAMO_ACCIONISTA).iterator();
					if(itDoc.hasNext())  documento = (DocumentoIf) itDoc.next();
					
					carteraDetalle.setDocumentoId(documento.getId());
					mapaTipoPago.put("codigo", TIPO_PAGO_VARIOS);
					mapaTipoPago.put("empresaId" , Parametros.getIdEmpresa());
					TipoPagoIf tipoPago = (TipoPagoIf)SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByQuery(mapaTipoPago).iterator().next();
					carteraDetalle.setFormaPagoId(tipoPago.getId());
					
					
				} else if(formaPagoSeleccionada.indexOf(" (DEBITO)") == -1){
					//Pago con Caja
					DocumentoIf documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo(CODIGO_PAGO_CONTADO).iterator().next();
					carteraDetalle.setDocumentoId(documento.getId());
					mapaTipoPago.put("codigo", TIPO_PAGO_EFECTIVO);
					mapaTipoPago.put("empresaId" , Parametros.getIdEmpresa());
					TipoPagoIf tipoPago = (TipoPagoIf)SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByQuery(mapaTipoPago).iterator().next();
					carteraDetalle.setFormaPagoId(tipoPago.getId());
					
				}else{
					//Pago con Debito Bancario
					DocumentoIf documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo(CODIGO_PAGO_DEBITO_BANCARIO).iterator().next();
					carteraDetalle.setDocumentoId(documento.getId());
					mapaTipoPago.put("codigo", TIPO_PAGO_DEBITO_BANCARIO);
					mapaTipoPago.put("empresaId" , Parametros.getIdEmpresa());
					TipoPagoIf tipoPago = (TipoPagoIf)SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByQuery(mapaTipoPago).iterator().next();
					carteraDetalle.setFormaPagoId(tipoPago.getId());
					
					//Seteo la cuenta bancaria xque el asiento debe ser igual a cuando se paga con cheque
					for(CuentaBancariaIf cuentaBancaria : cuentasBancos){
						if(cuentaBancaria != null){
							BancoIf banco = (BancoIf) bancosMap.get(cuentaBancaria.getBancoId());
							String cuentaBancariaS = banco.getNombre() + ", Cta. " + cuentaBancaria.getCuenta() + " (DEBITO)";
							if(cuentaBancariaS.equals(formaPagoSeleccionada)){
								carteraDetalle.setCuentaBancariaId(cuentaBancaria.getId());
								carteraDetalle.setDebitoBancoId(cuentaBancaria.getBancoId());
								carteraDetalle.setDebitoCuentaBancariaId(cuentaBancaria.getId());
							}
						}
					}
				}								
				carteraDetalle.setFechaCartera(new Date(fechasColeccion[i].getTimeInMillis()));
				carteraDetalle.setSaldo(new BigDecimal(0));
				carteraDetalle.setCartera(CARTERA_SI);
			}else{
				//Pago con Cheque
				mapaTipoPago.put("codigo", TIPO_PAGO_CHEQUE);
				mapaTipoPago.put("empresaId" , Parametros.getIdEmpresa());
				TipoPagoIf tipoPago = (TipoPagoIf)SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByQuery(mapaTipoPago).iterator().next();
				carteraDetalle.setFormaPagoId(tipoPago.getId());
				carteraDetalle.setPreimpreso(numerosCheque.get(i));
				carteraDetalle.setFechaCartera(new Date(fechasColeccion[i].getTimeInMillis()));
				if(Utilitarios.compararFechas(new Date(calendar.getTime().getTime()), new Date(fechasColeccion[i].getTime().getTime())) >= 0){
					DocumentoIf documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo(CODIGO_PAGO_CHEQUE).iterator().next();
					carteraDetalle.setDocumentoId(documento.getId());
					carteraDetalle.setSaldo(new BigDecimal(0));
					carteraDetalle.setCartera(CARTERA_SI);
				}else if(Utilitarios.compararFechas(new Date(calendar.getTime().getTime()), new Date(fechasColeccion[i].getTime().getTime())) == -1){
					DocumentoIf documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo(CODIGO_PAGO_ABONO_DIFERIDO).iterator().next();
					carteraDetalle.setDocumentoId(documento.getId());
					carteraDetalle.setSaldo(new BigDecimal(0));
					carteraDetalle.setCartera(CARTERA_SI);
					carteraDetalle.setDiferido(CARTERA_DIFERIDA);
				}
				carteraDetalle.setCuentaBancariaId(cuentasBancosSeleccionadas.get(i).getId());
				carteraDetalle.setChequeCuentaBancariaId(cuentasBancosSeleccionadas.get(i).getId());
				carteraDetalle.setChequeBancoId(cuentasBancosSeleccionadas.get(i).getBancoId());
				carteraDetalle.setChequeNumero(numerosCheque.get(i));
			}		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return carteraDetalle;
	}
	
	private void generarCheques() throws GenericBusinessException {
		//carteraDetalleColeccionChequesImprimir.clear();
		carteraDetalleColeccionChequesImprimir = null;
		carteraDetalleColeccionChequesImprimir = new ArrayList<CarteraDetalleIf>();
		CuentaBancariaIf cuentaBancariaVerificada;
		Calendar fechaVerificada = new GregorianCalendar(2000,1,1);
		ArrayList<Integer> indicesAgregados = new ArrayList<Integer>();
		int contador = 0;
		String numeroCheque = "";
		BigDecimal monto = new BigDecimal(0);
		for (int i = 0; i < carteraDetalleColeccionCheques.size(); i++){
			CarteraDetalleIf carteraDetalleVerificada = (CarteraDetalleIf) carteraDetalleColeccionCheques.get(i);
			
			if(carteraDetalleVerificada != null){
				CarteraIf carteraVerificada = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleVerificada.getCarteraId());
				ClienteOficinaIf clienteOficinaVerificada = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(carteraVerificada.getClienteoficinaId());
				ClienteIf clienteVerificada = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaVerificada.getClienteId());
							
				fechaVerificada = fechasColeccion[i];
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
							&& (cuentasBancosSeleccionadas.get(j) != null)
							&& (cuentaBancariaVerificada.getId().compareTo(cuentasBancosSeleccionadas.get(j).getId()) == 0)
							&& (numeroCheque.equals(numerosCheque.get(j)))
							&& (Utilitarios.compararFechas(new Date(fechaVerificada.getTime().getTime()),new Date(fechasColeccion[j].getTime().getTime())) == 0)){
							
							contador++;
							if(contador == 1){
								monto = carteraDetalleVerificada.getValor();
							}
							else if(contador > 1){
								monto = monto.add(BigDecimal.valueOf(Double.parseDouble((Utilitarios.removeDecimalFormat((String)getTblPagos().getModel().getValueAt(j,4))))));
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
		
		for (int i = 0; i < carteraDetalleColeccionChequesImprimir.size(); i++) {
			CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) carteraDetalleColeccionChequesImprimir.get(i);
			CarteraIf cartera = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalle.getCarteraId());
			TipoPagoIf tipoPago = SessionServiceLocator.getTipoPagoSessionService().getTipoPago(carteraDetalle.getFormaPagoId());
			if (tipoPago.getCodigo().equals(TIPO_PAGO_CHEQUE) && cartera.getTipo().equals("P")){
				generarChequeData(cartera, carteraDetalle);
			}
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
				//String valorCheque = formatoDecimal.format(totalCheque);
				MonedaIf moneda = SessionServiceLocator.getMonedaSessionService().getMoneda(cartera.getMonedaId());
				String valorCheque = formatoDecimal.format(Double.valueOf(totalCheque));
				String parteDecimal = valorCheque.substring(valorCheque.indexOf('.'), valorCheque.length());
				Double dParteDecimal = 0.0;
				if (!parteDecimal.isEmpty())
					dParteDecimal = Double.valueOf(parteDecimal);
				String pagueseA = cliente.getRazonSocial();
				//String[] cantidadLetras = obtenerCantidadEnLetras(valorCheque);
				String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(valorCheque, dParteDecimal, new int[] { 70, 90 }, false, moneda);
				String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
				String cantidadLetrasSegundaLinea = cantidadLetras[1].replaceAll("  ","");
				//String lugarFecha = ciudad.getNombre() + ", " + Utilitarios.getFechaUppercase(carteraDetalle.getFechaCartera());
				//String lugarFechaPrimerReemplazo = lugarFecha.replaceFirst("-","DE");
				//lugarFecha = lugarFechaPrimerReemplazo.replaceAll("-","DEL");
				
				String lugarFecha = ciudad.getNombre() + ", " + Utilitarios.getFechaNuevosCheques(carteraDetalle.getFechaCartera());
				
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
	
	public void save() {
		Map proveedoresMap = new HashMap();
		Map proveedoresOficinasMap = new HashMap();
		try {
			generarColeccionPagos();
			if(pagarColeccion.indexOf(true) == -1){
				SpiritAlert.createAlert("Debe seleccionar al menos un pago !",SpiritAlert.WARNING);
			} else {
				if(validateFields()){
					carteraDetalleColeccion = null;
					carteraDetalleColeccion = new ArrayList<CarteraDetalleIf>();
					carterasPagadasColeccion = null;
					carterasPagadasColeccion = new Vector<CarteraIf>();
					walletDataVector = new Vector<WalletData>();
					carterasComprasPagadasColeccion = null;
					carterasComprasPagadasColeccion = new Vector<CarteraIf>();
					documentosMap = null;
					documentosMap = mapearDocumentos();
					Map monedasMap = mapearMonedas();
					tiposDocumentoMap = null;
					tiposDocumentoMap = mapearTiposDocumento();
					bancosMap = null;
					bancosMap = mapearBancos();
					cuentasBancariasMap = null;
					cuentasBancariasMap = mapearCuentasBancarias();
					generarColeccionesFormaChequeFecha();
					
					cheques = null;
					cheques = new ArrayList<String[]>();
					actualizarNumerosChequeMap();
					String comentario = "";
					if(numerosChequeDiferentes){
						SpiritAlert.createAlert("Los n˙meros de cheque del pago que se va a realizar no son iguales !",SpiritAlert.WARNING);
						getTblPagos().getColumnModel().getSelectionModel().setSelectionInterval(6, 6);
					}else{
						if(pagarColeccion.size() > 0){
							for(int i=0; i<pagarColeccion.size(); i++){
								if(pagarColeccion.get(i)){
									CarteraDetalleIf carteraDetalleIf = getCarteraPagoDetalleColeccion().get(i);
									CarteraIf carteraIf = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleIf.getCarteraId());
									cartera = SessionServiceLocator.getCarteraSessionService().getCartera(Long.parseLong(carteraDetalleIf.getReferencia()));
									if (cartera.getReferenciaId() != null)
										compra = SessionServiceLocator.getCompraSessionService().getCompra(cartera.getReferenciaId());
									else
										compra = null;
									clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(carteraIf.getClienteoficinaId());
									proveedoresOficinasMap.put(clienteOficina.getId(), clienteOficina);
									cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
									proveedoresMap.put(cliente.getId(), cliente);
									comentario = "PAGO AL PROVEEDOR: " + cliente.getNombreLegal();
									//AQUI CAMBIE FECHA
									java.sql.Date fechaCartera = new java.sql.Date(fechasColeccion[i].getTimeInMillis());
									carteraIf.setFechaEmision(Utilitarios.fromSqlDateToTimestamp(fechaCartera));
									TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(carteraIf.getTipodocumentoId());
									String unNumeroCartera = getNumeroCartera(fechaCartera, tipoDocumento.getCodigo());
									carteraIf.setCodigo(unNumeroCartera);
									
									carteraIf.setComentario(comentario);
									CarteraIf carteraCompraIf = cartera;
									carteraDetalleIf = registrarCarteraDetalle(i, carteraDetalleIf);
									
									carteraDetalleColeccion.add(carteraDetalleIf);								
									carteraDetalleColeccionCheques.add(carteraDetalleIf);
									carterasPagadasColeccion.add(carteraIf);
									carterasComprasPagadasColeccion.add(carteraCompraIf);
								} else
									carteraDetalleColeccionCheques.add(null);
							}
							carterasPagadasColeccion = removerCarterasRepetidas(carterasPagadasColeccion);
							carterasComprasPagadasColeccion = removerCarterasRepetidas(carterasComprasPagadasColeccion);
							carteraColeccion = SessionServiceLocator.getCarteraSessionService().procesarPagoProveedor(carterasPagadasColeccion, carteraDetalleColeccion, carterasComprasPagadasColeccion, Parametros.getIdEmpresa(), numerosChequeMap);
							SpiritAlert.createAlert("Pago realizado con Èxito",SpiritAlert.INFORMATION);
							
							//actualizo carteras de pago o creo nuevas si se crearon nuevas carteras egreso
							for (int i=0; i<carteraColeccion.size(); i++) {
								CarteraIf cartera = carteraColeccion.get(i);
								TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get(cartera.getTipodocumentoId());
								
								if (tipoDocumento.getCodigo().equals("CEG")){
									Collection carterasPago = SessionServiceLocator.getCarteraPagoSessionService().findCarteraPagoByCarteraPagoId(cartera.getId());
									//actualizo cartera pago
									if(carterasPago.size() > 0){
										Iterator carterasPagoIt = carterasPago.iterator();
										while(carterasPagoIt.hasNext()){
											CarteraPagoIf carteraPagoIf = (CarteraPagoIf)carterasPagoIt.next();
											Timestamp fecha = new Timestamp(new GregorianCalendar().getTimeInMillis());
											carteraPagoIf.setFechaPago(fecha);
											carteraPagoIf.setUsuarioPagoId(((UsuarioIf)Parametros.getUsuarioIf()).getId());
											carteraPagoIf.setEstado(EstadoCarteraPago.PAGADO.getLetra());
											SessionServiceLocator.getCarteraPagoSessionService().saveCarteraPago(carteraPagoIf);
										} 	
									}
									//creo nueva cartera de pago
									else{
										CarteraPagoData carteraPago = new CarteraPagoData();
										Timestamp fecha = new Timestamp(new GregorianCalendar().getTimeInMillis());
										carteraPago.setCarteraPagoId(cartera.getId());
										carteraPago.setFechaEmision(cartera.getFechaEmision());
										carteraPago.setUsuarioEmisionId(cartera.getUsuarioId());
										carteraPago.setFechaAprobacion(fecha);
										carteraPago.setUsuarioAprobacionId(cartera.getUsuarioId());
										carteraPago.setFechaPago(fecha);
										carteraPago.setUsuarioPagoId(((UsuarioIf)Parametros.getUsuarioIf()).getId());
										carteraPago.setEstado(EstadoCarteraPago.PAGADO.getLetra());
										carteraPago.setEmpresaId(Parametros.getIdEmpresa());
										SessionServiceLocator.getCarteraPagoSessionService().addCarteraPago(carteraPago);
									}									
								}
							}
							
							generarReporte(carteraColeccion);
							generarCheques();
							System.gc();
							showSaveMode();
						}	
					}
				}	
			}				
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
		} catch (SaldoCuentaNegativoException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la informaciÛn!",SpiritAlert.ERROR);
		} finally {
			carteraDetalleColeccionCheques = new ArrayList<CarteraDetalleIf>();
		}
	}
	
	private Comparator<ProviderPaymentReceiptRowData> providerPaymentReceiptRowDataComparator = new Comparator<ProviderPaymentReceiptRowData>() {
		public int compare(ProviderPaymentReceiptRowData providerPaymentReceiptRowData1, ProviderPaymentReceiptRowData providerPaymentReceiptRowData2) {
			return providerPaymentReceiptRowData1.getWalletId().compareTo(providerPaymentReceiptRowData2.getWalletId());
		}
	};
	
	private WalletDetailData setWalletDetailData(CarteraDetalleIf walletDetail) {
		WalletDetailData walletDetailData = new WalletDetailData();
		DocumentoIf document = (DocumentoIf) documentosMap.get(walletDetail.getDocumentoId());
		walletDetailData.setDocument(document);
		walletDetailData.setValue(walletDetail.getValor());
		walletDetailData.setBalance(walletDetail.getSaldo());
		if (document.getCheque().equals(SpiritConstants.getOptionYes().substring(0,1))) {
			walletDetailData.setCheckBank(bancosMap.get(walletDetail.getChequeBancoId()));
			walletDetailData.setCheckAccount(cuentasBancariasMap.get(walletDetail.getChequeCuentaBancariaId()));
			walletDetailData.setCheckNumber((walletDetail.getChequeNumero() != null)?walletDetail.getChequeNumero():SpiritConstants.getEmptyCharacter());
		}
		if (document.getDebitoBancario().equals(SpiritConstants.getOptionYes().substring(0,1))) {
			walletDetailData.setDebitBank(bancosMap.get(walletDetail.getDebitoBancoId()));
			walletDetailData.setDebitAccount(cuentasBancariasMap.get(walletDetail.getDebitoCuentaBancariaId()));
			walletDetailData.setDebitReference((walletDetail.getDebitoReferencia() != null)?walletDetail.getDebitoReferencia():SpiritConstants.getEmptyCharacter());
		}
		walletDetailData.setComment(buildComment(walletDetailData));
		return walletDetailData;
	}
	
	private String buildComment(WalletDetailData walletDetailData) {
		String comment = SpiritConstants.getEmptyCharacter();
		DocumentoIf document = walletDetailData.getDocument();
		comment += document.getAbreviado() + SpiritConstants.getBlankSpaceCharacter();
		if (document.getCheque().equals(SpiritConstants.getOptionYes().substring(0,1)))
			comment += "No. " + walletDetailData.getCheckNumber() + "; CTA. # " + walletDetailData.getCheckAccount().getCuenta() + SpiritConstants.getBlankSpaceCharacter() + walletDetailData.getCheckBank().getNombre();
		else if (document.getDebitoBancario().equals(SpiritConstants.getOptionYes().substring(0,1)))
			comment += "CTA. # " + walletDetailData.getDebitAccount().getCuenta() + SpiritConstants.getBlankSpaceCharacter() + walletDetailData.getDebitBank().getNombre();
		return comment;
	}
	
	private String getNumeroCartera(java.sql.Date fechaCartera, String codigoTipoDocumento) {
		String codigo = "";

		try {
			EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
			String monthCartera = Utilitarios.getMonthFromDate(fechaCartera);
			String anioCartera = Utilitarios.getYearFromDate(fechaCartera);
			codigo = empresa.getCodigo() + "-";
			OficinaIf oficina = (OficinaIf) Parametros.getOficina();
			ServidorIf servidor = (oficina.getServidorId()!=null)? SessionServiceLocator.getServidorSessionService().getServidor(oficina.getServidorId()):null;
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
	
	public void actualizarNumerosChequeMap(){
		String cuentaBancariaS = "";
		//numerosChequeMap.clear();
		numerosChequeMap = null;
		numerosChequeMap= new HashMap<Long,Integer>();
		int numeroChequeAnterior = 0;
		for(int i=0; i<pagarColeccion.size(); i++){
			if(pagarColeccion.get(i)){
				for(CuentaBancariaIf cuentaBancaria : cuentasBancos){
					if(cuentaBancaria != null){
						BancoIf banco = (BancoIf) bancosMap.get(cuentaBancaria.getBancoId());
						cuentaBancariaS = banco.getNombre() + ", Cta. " + cuentaBancaria.getCuenta();
						if(cuentaBancariaS.equals(getTblPagos().getModel().getValueAt(i,5).toString())){
							numeroChequeActual = Integer.valueOf((String)getTblPagos().getModel().getValueAt(i,6));
							if(numerosChequeMap.get(cuentaBancaria.getId()) != null){
								numeroChequeAnterior = numerosChequeMap.get(cuentaBancaria.getId());
								if(numeroChequeActual > numeroChequeAnterior){
									numerosChequeMap.put(cuentaBancaria.getId(),numeroChequeActual);
								}								
							}else{
								numerosChequeMap.put(cuentaBancaria.getId(),numeroChequeActual);
							}
						}else if(getTblPagos().getModel().getValueAt(i,5).toString().equals("")){
							SpiritAlert.createAlert("Falta seleccionar una cuenta bancaria", SpiritAlert.INFORMATION);
							getTblPagos().getColumnModel().getSelectionModel().setSelectionInterval(5, 5);
							break;
						}
					}							
				}												
			} 
		}
	}
	
	public Vector<CarteraIf> removerCarterasRepetidas(Vector<CarteraIf> carterasColeccion){
		Map<Long,CarteraIf> carterasMapa = new HashMap<Long,CarteraIf>();
		for(CarteraIf carteraRepetida : carterasColeccion){
			carterasMapa.put(carteraRepetida.getId(), carteraRepetida);
		}
		carterasColeccion.clear();
		Iterator carterasMapaIt = carterasMapa.keySet().iterator();
		while(carterasMapaIt.hasNext()){
			Long key = (Long)carterasMapaIt.next();
			carterasColeccion.add(carterasMapa.get(key));
		}
		
		return carterasColeccion;
	}
	
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void clean() {
		getCmbFechaCobro().setCalendar(calendar);
		
		numerosChequeMap.clear();
		carterasComprasPagadasColeccion.clear();
		carterasPagadasColeccion.clear();
		carteraColeccion.clear();
		cuentasBancos.clear();
		fechasColeccion = new Calendar[]{};
		carteraDetalleColeccion.clear();
		pagarColeccion.clear();
		numerosCheque.clear();
		cuentasBancosSeleccionadas.clear();
		carteraDetalleColeccionCheques.clear();
		proveedoresColeccion.clear();
		carteraDetalleColeccionChequesImprimir.clear();
		comprobanteEgresoColeccion.clear();
		pagoMulticashColeccion.clear();
		mapaParametrosEmpresa =  new HashMap<String,ParametroEmpresaIf>();
		
		getTxtTotalPago().setText("");
		totalAprobado = new BigDecimal(0);
				
		DefaultTableModel model = (DefaultTableModel) getTblPagos().getModel();
		for(int i= this.getTblPagos().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtProveedor().setEditable(false);
		clean();
		bancosMap = mapearBancos();
		cargarTabla();
	}
	
	private void cargarTabla() {
		try {
			totalAprobado = new BigDecimal(0);
			cuentasBancos.clear();
			Collection cartera = null;
			if(proveedorIf != null){
				cartera = SessionServiceLocator.getCarteraSessionService().findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdByAprobadoAndByClienteOficinaId("P", "CEG", Parametros.getIdEmpresa(), APROBADO_SI, proveedorIf.getId());
			}else{
				cartera = SessionServiceLocator.getCarteraSessionService().findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdAndByAprobado("P", "CEG", Parametros.getIdEmpresa(), APROBADO_SI);
			}
			
			if(cartera.size()>0){
				Iterator carteraIterator = cartera.iterator();
								
				if(!getCarteraColeccion().isEmpty())
					getCarteraColeccion().removeAllElements();
				if (!getCarteraPagoDetalleColeccion().isEmpty())
					getCarteraPagoDetalleColeccion().removeAllElements();
				
				while (carteraIterator.hasNext()) {
					CarteraIf carteraIf = (CarteraIf) carteraIterator.next();
					//Map carteraPagoMapa = new HashMap();
					//carteraPagoMapa.put("carteraPagoId", carteraIf.getId());
					//carteraPagoMapa.put("estado", EstadoCarteraPago.APROBADO.getLetra());
					//Collection carterasPago = SessionServiceLocator.getCarteraPagoSessionService().findCarteraPagoByQuery(carteraPagoMapa);
					//if(carterasPago.size() > 0){
						Iterator carteraDetalleIterator = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(carteraIf.getId()).iterator();
						while(carteraDetalleIterator.hasNext()) {
							CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) carteraDetalleIterator.next();
							if(carteraDetalleIf.getDocumentoId() == null){
								tableModel = (DefaultTableModel) getTblPagos().getModel();
								Vector<Object> fila = new Vector<Object>();
								getCarteraPagoDetalleColeccion().add(carteraDetalleIf);
								agregarColumnasTabla(carteraIf, carteraDetalleIf, fila);
								tableModel.addRow(fila);
							}	
						}					
						getCarteraColeccion().add(carteraIf);
					//}					
				}
				getTxtTotalPago().setText(formatoDecimal.format(totalAprobado));
				fechasColeccion = new Calendar[getCarteraPagoDetalleColeccion().size()];
				
				JComboBox comboBox = new JComboBox();
				Map<String, Object> queryMap = new HashMap<String, Object>();
				queryMap.put("empresaId", Parametros.getIdEmpresa());
				queryMap.put("cuentaCliente", "N");
				Collection cuentasBancarias = SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByQuery(queryMap);
				Iterator cuentasBancariasIt = cuentasBancarias.iterator();
				cuentasBancos.add(null);
				comboBox.addItem(NOMBRE_CAJA);			
				while(cuentasBancariasIt.hasNext()){
					CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) cuentasBancariasIt.next();
					BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancaria.getBancoId());
					cuentasBancos.add(cuentaBancaria);
					comboBox.addItem(banco.getNombre() + ", Cta. " + cuentaBancaria.getCuenta());
					comboBox.addItem(banco.getNombre() + ", Cta. " + cuentaBancaria.getCuenta() + " (DEBITO)");
				}
				
				agregarFormasPagoDesdeParametrosEmpresa(comboBox);
				
				TableColumn txtColumnaCuentas = getTblPagos().getColumnModel().getColumn(5);
				txtColumnaCuentas.setCellEditor(new DefaultCellEditor(comboBox));
				
			} else {
				SpiritAlert.createAlert("No existe ning˙n registro que presentar !",SpiritAlert.INFORMATION);
			}			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void agregarFormasPagoDesdeParametrosEmpresa(JComboBox comboBox) throws GenericBusinessException {
		mapaParametrosEmpresa =  new HashMap<String,ParametroEmpresaIf>();
		Map<String,Object> mapaTipoParametros = new HashMap<String,Object>();
		mapaTipoParametros.put("codigo","PAGOPROVEE");
		mapaTipoParametros.put("empresaId",Parametros.getIdEmpresa());
		Collection<TipoParametroIf> tiposParametros = SessionServiceLocator.getTipoParametroSessionService()
			.findTipoParametroByQuery(mapaTipoParametros);
		for ( TipoParametroIf tipoParametro : tiposParametros ){
			Map<String,Object> mapaParametro = new HashMap<String,Object>();
			mapaParametro.put("tipoparametroId",tipoParametro.getId());
			mapaParametro.put("empresaId",Parametros.getIdEmpresa());
			mapaParametro.put("codigo",NOMBRE_BASE_FORMAPAGO+"%");
			Collection<ParametroEmpresaIf> parametros = SessionServiceLocator.getParametroEmpresaSessionService()
				.findParametroEmpresaByQuery(mapaParametro);
			for ( ParametroEmpresaIf parametro : parametros ){
				comboBox.addItem(parametro.getDescripcion());
				mapaParametrosEmpresa.put(parametro.getDescripcion(),parametro);
			}
		}
	}
	
	private void agregarColumnasTabla(CarteraIf carteraIf, CarteraDetalleIf carteraDetalleIf, Vector<Object> fila){
		try {
			clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(carteraIf.getClienteoficinaId());
			cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
			proveedoresColeccion.add(cliente);
			cartera = SessionServiceLocator.getCarteraSessionService().getCartera(Long.parseLong(carteraDetalleIf.getReferencia()));
			if (cartera.getReferenciaId() != null)
				compra = SessionServiceLocator.getCompraSessionService().getCompra(cartera.getReferenciaId());
			else
				compra = null;
			
			fila.add(new Boolean(false));
			fila.add(cliente.getIdentificacion() + " - " + cliente.getRazonSocial());
			fila.add((compra!=null)?compra.getPreimpreso():cartera.getPreimpreso());
			fila.add((compra!=null)?Utilitarios.getFechaCortaUppercase(compra.getFechaVencimiento()):null);
			fila.add(formatoDecimal.format(carteraDetalleIf.getSaldo()).toString());
			fila.add("");
			fila.add("");
			fila.add("");
			fila.add(carteraDetalleIf.getObservacion());
			totalAprobado = totalAprobado.add(carteraDetalleIf.getSaldo());
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		try {
			cuentasBancosSeleccionadas.clear();
			numerosCheque.clear();
			String cuentaBancariaS;
			for(int j=0; j<pagarColeccion.size(); j++){
				if(!pagarColeccion.get(j)){
					numerosCheque.add(null);	
				}else if((getTblPagos().getModel().getValueAt(j,5) == null) || getTblPagos().getModel().getValueAt(j,5).equals("")){
					SpiritAlert.createAlert("Debe seleccionar una forma de Pago",SpiritAlert.WARNING);
					getTblPagos().getSelectionModel().setSelectionInterval(j, j);
					getTblPagos().getColumnModel().getSelectionModel().setSelectionInterval(5, 5);
					return false;
				}else{
					if(((String)getTblPagos().getModel().getValueAt(j,5)).equals(NOMBRE_CAJA)
							|| (((String)getTblPagos().getModel().getValueAt(j,5)).indexOf(" (DEBITO)") != -1)){
						numerosCheque.add(null);	
					}else{
						for(CuentaBancariaIf cuentaBancaria : cuentasBancos){
							if(cuentaBancaria != null){
								BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancaria.getBancoId());
								cuentaBancariaS = banco.getNombre() + ", Cta. " + cuentaBancaria.getCuenta();
								if(cuentaBancariaS.equals(getTblPagos().getModel().getValueAt(j,5))){
									cuentasBancosSeleccionadas.add(cuentaBancaria);
									if((fechasColeccion[j] == null) || fechasColeccion[j].equals("")){
										SpiritAlert.createAlert("Debe ingresar la Fecha de Cobro del Cheque",SpiritAlert.WARNING);
										getTblPagos().getSelectionModel().setSelectionInterval(j, j);
										getTblPagos().getColumnModel().getSelectionModel().setSelectionInterval(7, 7);
										return false;
									}else if((getTblPagos().getModel().getValueAt(j,6) == null) || getTblPagos().getModel().getValueAt(j,6).equals("")){
										SpiritAlert.createAlert("Debe ingresar el n˙mero del Cheque",SpiritAlert.WARNING);
										getTblPagos().getSelectionModel().setSelectionInterval(j, j);
										getTblPagos().getColumnModel().getSelectionModel().setSelectionInterval(6, 6);
										return false;
									}else{
										numerosCheque.add((String)getTblPagos().getModel().getValueAt(j,6));
									}
								}
							}
						}								
					}					
				}		
			}		
			String numeroCheque;
			Calendar fechaVerificada = new GregorianCalendar(2000,1,1);
			int contador = 0;
			int verificar = 1;
			String formaPago = "";
			String identificacionProveedor = "";
			int espacioBlanco = 0;
			String identificacion = "";
			for (int i=0; i<getCarteraPagoDetalleColeccion().size(); i++) {
				String abonoS = (String) getTblPagos().getModel().getValueAt(i, 4);
				double abono = !abonoS.equals("")?Double.valueOf(Utilitarios.removeDecimalFormat(abonoS)): 0D;
				CarteraIf cartera = SessionServiceLocator.getCarteraSessionService().getCartera(Long.parseLong(Utilitarios.removeDecimalFormat(getCarteraPagoDetalleColeccion().get(i).getReferencia())));
				double saldo = cartera.getSaldo().doubleValue();
				if ((Boolean)getTblPagos().getModel().getValueAt(i,0) && abono > saldo) {
					getTblPagos().setRowSelectionInterval(i, i);
					SpiritAlert.createAlert("No se puede efectuar el pago de la factura seleccionada\nValor a pagar es mayor al saldo actual de la factura", SpiritAlert.WARNING);
					return false;
				}
			}
			
			//no se puede usar carteraColeccion.size() porque una cartera puede contener varios de detalles del mismo proveedor
			//for(int i=0; i<carteraColeccion.size(); i++){	
			
			for(int i=0; i<getTblPagos().getRowCount(); i++){							
				numeroCheque = (String)getTblPagos().getModel().getValueAt(i,6);
				contador = 0;
				if((numeroCheque != null) && (!numeroCheque.equals(""))){
					for(int j=0; j<numerosCheque.size(); j++){
						if(numeroCheque.equals(numerosCheque.get(j))){
							contador++;
							if(contador == verificar){
								fechaVerificada = fechasColeccion[i];
								formaPago = (String)getTblPagos().getModel().getValueAt(i,5);
								identificacionProveedor = proveedoresColeccion.get(i).getIdentificacion();
							}else if(contador > 1){
								try{
									numeroChequeActual = Integer.valueOf((String)getTblPagos().getModel().getValueAt(j,6));
								}catch(NumberFormatException nfe){
									SpiritAlert.createAlert("Un n˙mero de cheque est· mal ingresado", SpiritAlert.INFORMATION);
									getTblPagos().getSelectionModel().setSelectionInterval(j, j);
									getTblPagos().getColumnModel().getSelectionModel().setSelectionInterval(6, 6);
									return false;
								}	
								espacioBlanco = ((String)getTblPagos().getModel().getValueAt(j,1)).indexOf(" ");
								identificacion = ((String)getTblPagos().getModel().getValueAt(j,1)).substring(0, espacioBlanco);
								if((Utilitarios.compararFechas(new Date(fechaVerificada.getTime().getTime()),new Date(fechasColeccion[j].getTime().getTime())) != 0) && formaPago.equals((String)getTblPagos().getModel().getValueAt(j,5))){
									SpiritAlert.createAlert("N˙meros de cheque iguales tienen distinta fecha de cobro!",SpiritAlert.WARNING);
									getTblPagos().getSelectionModel().setSelectionInterval(j, j);
									getTblPagos().getColumnModel().getSelectionModel().setSelectionInterval(7,7);
									return false;
								}else if(formaPago.equals((String)getTblPagos().getModel().getValueAt(j,5)) && !identificacionProveedor.equals(identificacion)){
									SpiritAlert.createAlert("No se puede pagar a proveedores diferentes con el mismo cheque!",SpiritAlert.WARNING);
									getTblPagos().getSelectionModel().setSelectionInterval(j, j);
									getTblPagos().getColumnModel().getSelectionModel().setSelectionInterval(1,1);
									return false;
								}
							}
						}
					}
				}				
			}	
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private Map mapearDocumentos() {
		Map documentosMap = new HashMap();
		
		try {
			Iterator documentosIterator = SessionServiceLocator.getDocumentoSessionService().findDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
		
			while (documentosIterator.hasNext()) {
				DocumentoIf documento = (DocumentoIf) documentosIterator.next();
				documentosMap.put(documento.getId(), documento);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return documentosMap;
	}
	
	private Map mapearMonedas() {
		Map monedasMap = new HashMap();
		try {
			Iterator monedasIterator = SessionServiceLocator.getMonedaSessionService().getMonedaList().iterator();
			while (monedasIterator.hasNext()) {
				MonedaIf moneda = (MonedaIf) monedasIterator.next();
				monedasMap.put(moneda.getId(), moneda);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return monedasMap;
	}
	
	private Map<Long,TipoDocumentoIf> mapearTiposDocumento() {
		Map<Long,TipoDocumentoIf> tiposDocumentoMap = new HashMap<Long,TipoDocumentoIf>();
		
		try {
			Iterator tiposDocumentoIterator = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			
			while (tiposDocumentoIterator.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoIterator.next();
				tiposDocumentoMap.put(tipoDocumento.getId(), tipoDocumento);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return tiposDocumentoMap;
	}
	
	private Map<Long,BancoIf> mapearBancos() {
		Map<Long,BancoIf> bancosMap = new HashMap<Long,BancoIf>();
		
		try {
			Iterator bancosIterator = SessionServiceLocator.getBancoSessionService().getBancoList().iterator();
			
			while (bancosIterator.hasNext()) {
				BancoIf banco = (BancoIf) bancosIterator.next();
				bancosMap.put(banco.getId(), banco);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return bancosMap;
	}
	
	private Map<Long,CuentaBancariaIf> mapearCuentasBancarias() {
		Map<Long,CuentaBancariaIf> cuentasBancariasMap = new HashMap<Long,CuentaBancariaIf>();
		
		try {
			Iterator cuentasBancariasIterator = SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByEmpresaId(Parametros.getIdEmpresa()).iterator();
			
			while (cuentasBancariasIterator.hasNext()) {
				CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) cuentasBancariasIterator.next();
				cuentasBancariasMap.put(cuentaBancaria.getId(), cuentaBancaria);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return cuentasBancariasMap;
	}
	
	private void generarReporte(Vector<CarteraIf> carteraColeccion) {
		try {
			//limpio mapa que me sirve para ver que carteras de egreso estan en el archivo multicash
			carterasEgresoMulticashMap.clear();
			
			for (int i=0; i<carteraColeccion.size(); i++) {
				CarteraIf cartera = carteraColeccion.get(i);
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get(cartera.getTipodocumentoId());
				
				if (tipoDocumento.getCodigo().equals("CEG")){
					//Comprobante de Egreso y data del archivo Multicash
					generarReporteComprobanteEgreso(cartera);			
				}
			}
			
			if (comprobanteEgresoColeccion.size() > 0) {
				String fileName = "jaspers/cartera/RPComprobanteEgreso.jasper";
				HashMap parametrosMap = new HashMap();
				MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("CARTERA").iterator().next();
				parametrosMap.put("codigoReporte", menu.getCodigo());
				EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
				parametrosMap.put("empresa", empresa.getNombre());
				parametrosMap.put("ruc", empresa.getRuc());
				OficinaIf oficina = (OficinaIf) Parametros.getOficina();
				CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
				parametrosMap.put("ciudad", ciudad.getNombre());
				parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
				parametrosMap.put("usuario", Parametros.getUsuario().toLowerCase());
				ReportModelImpl.processReport(fileName, parametrosMap, comprobanteEgresoColeccion, true);
				
				//archivo de pago electronico de Banco de Guayaquil
				generarArchivoMulticash();								
			} 
			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
	}
	
	public void generarArchivoMulticash(){
		try {
			Calendar fechaHoy = new GregorianCalendar();
			String anio = String.valueOf(fechaHoy.getTime().getYear()+1900);
			String mes = String.valueOf(fechaHoy.getTime().getMonth()+1);
			if((fechaHoy.getTime().getMonth()+1) < 10){
				mes = "0"+mes;
			}			
			String dia = String.valueOf(fechaHoy.getTime().getDate());
			if(fechaHoy.getTime().getDate() < 10){
				dia = "0"+dia;
			}
			String anioMesDia = anio+mes+dia;
			
			HSSFWorkbook libro = new HSSFWorkbook();
			
			//busco el secuencial que toca
			int secuencialMayor = 0;
			Map aMap = new HashMap();
			java.sql.Date fechaInicioTemp = new java.sql.Date(fechaHoy.getTime().getYear(),fechaHoy.getTime().getMonth(),fechaHoy.getTime().getDate());
			Timestamp fechaInicio = new Timestamp(fechaInicioTemp.getTime());
			java.sql.Date fechaFinTemp = new java.sql.Date(fechaHoy.getTime().getYear(),fechaHoy.getTime().getMonth(),fechaHoy.getTime().getDate());
			Timestamp fechaFin = new Timestamp(fechaFinTemp.getTime());
			fechaFin.setHours(23);
			fechaFin.setMinutes(59);
			fechaFin.setSeconds(59);
			Collection carterasPago = SessionServiceLocator.getCarteraPagoSessionService().findCarteraPagoByMapByFechaInicioByFechaFin(aMap, fechaInicio, fechaFin, Parametros.getIdEmpresa());
			if(carterasPago.size() > 0){
				Iterator carterasPagoIt = carterasPago.iterator();
				while(carterasPagoIt.hasNext()){
					CarteraPagoIf carteraPago = (CarteraPagoIf)carterasPagoIt.next();
					//los que no tienen secuencial pueden ser cheques o pagos de hechos con otro Banco
					if(carteraPago.getSecuencialMulticash() != null){
						int secuencial = Integer.valueOf(carteraPago.getSecuencialMulticash());
						if(secuencial > secuencialMayor){
							secuencialMayor = secuencial;
						}
					}					
				}
			}
			
			//uno mas que el guardado en la base
			secuencialMayor = secuencialMayor + 1;
			
			String secuencialArchivo = "01";
			if(secuencialMayor < 10){
				secuencialArchivo = "0"+secuencialMayor;
			}else{
				secuencialArchivo = String.valueOf(secuencialMayor);
			}
			
			String nombreArchivoMulticash = "PAGOS_MULTICASH_"+anioMesDia+"_"+secuencialArchivo;
			HSSFSheet hoja = libro.createSheet(nombreArchivoMulticash);
			
			int secuencial = 0;
			
			for(int j=0; j<pagoMulticashColeccion.size(); j++){				
				FormatoPagoMulticashData formatoPagoMulticashData = pagoMulticashColeccion.get(j);
				
				//primera fila numeraciÛn del 1 al 20
				HSSFRow filaNumeros = hoja.createRow(0);
				for(int i=0; i<20; i++){
					HSSFCell celda = filaNumeros.createCell(i);
					HSSFRichTextString texto = new HSSFRichTextString(String.valueOf(i+1));
					celda.setCellValue(texto);
				}							
				
				//segunda fila lleno los 20 campos
				HSSFRow filaCampos = hoja.createRow(j+1);
				for(int i=0; i<20; i++){
					HSSFCell celda = filaCampos.createCell(i);
					HSSFRichTextString texto = new HSSFRichTextString(formatoPagoMulticashData.getCampo(i+1));
					if((i+1) == 3){
						secuencial = secuencial + 1;
						texto = new HSSFRichTextString(String.valueOf(secuencial));						
					}
					celda.setCellValue(texto);
				}			
			}
			
			//indico que las columnas tengan el ancho de su texto
			//por el momento no porque indicaron en Banco Guayaquil no hacer esto
			/*for(int columnIndex = 0; columnIndex < 20; columnIndex++) {
				hoja.autoSizeColumn(columnIndex);
			}*/
			
			FileOutputStream archivo = new FileOutputStream("C:\\Temp\\"+nombreArchivoMulticash+".xls");
			libro.write(archivo);
			archivo.close();
			System.out.println("Archivo creado con Èxito!");
			
			//actualizo los registros de cartera pago con la informaciÛn de secuencial y nombre de archivo
			Iterator carterasEgresoMulticashMapIt = carterasEgresoMulticashMap.keySet().iterator();
			while(carterasEgresoMulticashMapIt.hasNext()){
				Long carteraEgresoId = (Long)carterasEgresoMulticashMapIt.next();
				Collection carterasPagadas = SessionServiceLocator.getCarteraPagoSessionService().findCarteraPagoByCarteraPagoId(carteraEgresoId);
				Iterator carterasPagadasPagoIt = carterasPagadas.iterator();
				while(carterasPagadasPagoIt.hasNext()){
					CarteraPagoIf carteraPago = (CarteraPagoIf)carterasPagadasPagoIt.next();
					carteraPago.setSecuencialMulticash(secuencialArchivo);
					carteraPago.setArchivoMulticash(nombreArchivoMulticash);
					SessionServiceLocator.getCarteraPagoSessionService().saveCarteraPago(carteraPago);
				}
			}			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("No se pudo crear el Excel: File");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No se pudo crear el Excel: IO");
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void generarReporteComprobanteEgreso(CarteraIf cartera) {
		try {
			AsientoIf asientoAutomatico = generarReporteAsientoComprobante(cartera);
			
			Collection carteraDetalleColeccion = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(cartera.getId());
			Iterator carteraDetalleIterator = carteraDetalleColeccion.iterator();
			
			ClienteOficinaIf proveedorOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(cartera.getClienteoficinaId());
			ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
			MonedaIf moneda = SessionServiceLocator.getMonedaSessionService().getMoneda(cartera.getMonedaId());
			String valorComprobante = formatoDecimal.format(Double.valueOf(cartera.getSaldo().subtract(cartera.getValor()).abs().toString()));
			String parteDecimal = valorComprobante.substring(valorComprobante.indexOf('.'), valorComprobante.length());
			Double dParteDecimal = 0.0;
			
			if (!parteDecimal.isEmpty())
				dParteDecimal = Double.valueOf(parteDecimal);
			
			String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(valorComprobante, dParteDecimal, new int[] { 90 }, false, moneda);
			String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
			String totalAplicado = formatoDecimal.format(Utilitarios.redondeoValor(cartera.getValor().subtract(cartera.getSaldo()).doubleValue()));
			String saldoTotal = formatoDecimal.format(Utilitarios.redondeoValor(cartera.getSaldo().doubleValue()));
			
			while (carteraDetalleIterator.hasNext()) {
				CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) carteraDetalleIterator.next();
				int indiceCarteraPagoDetalle = getIndiceCarteraPagoDetalle(carteraDetalleIf);
				
				if (indiceCarteraPagoDetalle != -1 && pagarColeccion.get(indiceCarteraPagoDetalle)) {
					Collection carteraAfectaColeccion = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(carteraDetalleIf.getId());
					Iterator carteraAfectaIterator = carteraAfectaColeccion.iterator();
					
					while (carteraAfectaIterator.hasNext()) {
						CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf) carteraAfectaIterator.next();
						
						ComprobanteEgresoData comprobanteEgresoData = agregarDetalleComprobanteEgreso(carteraDetalleIf, carteraAfectaIf);
						
						String fecha = cartera.getFechaEmision().toString();
						String year = fecha.substring(0,4);
						String month = fecha.substring(5,7);
						String day = fecha.substring(8,10);
						String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
						comprobanteEgresoData.setFechaEmision(fechaEmision);
						comprobanteEgresoData.setProveedor(proveedor.getRazonSocial());
						comprobanteEgresoData.setCantidad(cantidadLetrasPrimeraLinea);
						comprobanteEgresoData.setConcepto(cartera.getComentario());
						comprobanteEgresoData.setCodigoAsiento(asientoAutomatico != null ? asientoAutomatico.getNumero() : "N/A");
						comprobanteEgresoData.setValorTotal(valorComprobante);
						comprobanteEgresoData.setCodigo(cartera.getCodigo());
						comprobanteEgresoData.setAnulado(cartera.getEstado().equals(WalletConstants.getNullifyStatus().substring(0,1)));
						comprobanteEgresoData.setTotalAplicado(totalAplicado);
						comprobanteEgresoData.setSaldoTotal(saldoTotal);
						comprobanteEgresoColeccion.add(comprobanteEgresoData);
						
						
						//DATOS PARA EL ARCHIVO MULTICASH DE BANCO DE GUAYAQUIL (CODIGO: 0017)						
						DocumentoIf documento = (DocumentoIf) documentosMap.get(carteraDetalleIf.getDocumentoId());
						CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) cuentasBancariasMap.get(carteraDetalleIf.getCuentaBancariaId());
						BancoIf banco = (BancoIf) bancosMap.get(cuentaBancaria.getBancoId());
						if (banco.getCodigoMulticash().equals("0017") && documento.getDebitoBancario().equals("S")) {
							
							//lleno los datos para el pago multicash						
							FormatoPagoMulticashData formatoPagoMulticashData = new FormatoPagoMulticashData();
							
							//1: PA = Pago
							formatoPagoMulticashData.setCodigoOrientacion("PA");
							//2: numero de cuenta BG de la empresa 
							formatoPagoMulticashData.setCuentaEmpresa(comprobanteEgresoData.getNumeroCuenta());
							//3: secuencial de pago
							formatoPagoMulticashData.setSecuencialPago("3-Secuencial");
							//4: (opcional) codigo de comprabante de pago
							//formatoPagoMulticashData.setComprobantePago(comprobanteEgresoData.getCodigo().substring(4, comprobanteEgresoData.getCodigo().length()));
							formatoPagoMulticashData.setComprobantePago("");
							
							//5: numero de cuenta de proveedor
							if(proveedor.getNumeroCuenta() != null && !proveedor.getNumeroCuenta().equals("")){
								formatoPagoMulticashData.setCodigo(proveedor.getNumeroCuenta());
							}else{
								formatoPagoMulticashData.setCodigo("5-Codigo");
							}
							
							//6: moneda
							formatoPagoMulticashData.setMoneda("USD");						
							//7: valor representando en 13 digitos enteros, completados con ceros a la izquierda
							String valorSinComas = comprobanteEgresoData.getValor().replaceAll(",", "");
							String valorDecimal = valorSinComas.substring(valorSinComas.length()-2, valorSinComas.length());
							String valorEntero = valorSinComas.substring(0, valorSinComas.length()-3);
							String unirEnteroDecimal = valorEntero + valorDecimal;
							formatoPagoMulticashData.setValor(formato13DigitosMulticash.format(Double.valueOf(unirEnteroDecimal)));
							//8: CTA = CrÈdito a Cuenta
							formatoPagoMulticashData.setFormaPago("CTA");
							
							//9: codigo del banco del proveedor
							BancoIf bancoProveedor = null;
							if(proveedor.getBancoId() != null){
								bancoProveedor = bancosMap.get(proveedor.getBancoId());
								if(bancoProveedor.getCodigoMulticash() != null && !bancoProveedor.getCodigoMulticash().equals("")){
									formatoPagoMulticashData.setCodigoInstitucionFinanciera(bancoProveedor.getCodigoMulticash());
								}else{
									formatoPagoMulticashData.setCodigoInstitucionFinanciera("9-CodigoInstitucionFinanciera");
								}
							}else{
								formatoPagoMulticashData.setCodigoInstitucionFinanciera("9-CodigoInstitucionFinanciera");
							}						
							
							//10: tipo de cuenta del proveedor, CTE = Corriente / AHO = Ahorros
							if(proveedor.getTipoCuenta() != null && !proveedor.getTipoCuenta().equals("")){
								if(proveedor.getTipoCuenta().equals("C")){
									formatoPagoMulticashData.setTipoCuenta("CTE");
								}else if(proveedor.getTipoCuenta().equals("A")){
									formatoPagoMulticashData.setTipoCuenta("AHO");
								}
							}else{
								formatoPagoMulticashData.setTipoCuenta("10-TipoCuenta");
							}
							
							//11: numero de cuenta de proveedor (igual al 5)
							if(proveedor.getNumeroCuenta() != null && !proveedor.getNumeroCuenta().equals("")){
								
								String numeroCuentaProveedor = proveedor.getNumeroCuenta();
								//si el banco es Banco de Guayaquil, el numero de cuenta debe tener 10 digitos
								//si es de otro banco no debe completarse ceros
								if(bancoProveedor != null && bancoProveedor.getCodigoMulticash().equals("0017")){
									numeroCuentaProveedor = formato10DigitosMulticash.format(Integer.valueOf(proveedor.getNumeroCuenta()));
								}				
								formatoPagoMulticashData.setNumeroCuenta(numeroCuentaProveedor);
								
							}else{
								formatoPagoMulticashData.setNumeroCuenta("11-NumeroCuenta");
							}						
							
							//12: tipo de identificacion del proveedor, C=Cedula / R=RUC / P=Pasaporte
							TipoIdentificacionIf tipoIdentificacion = SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacion(proveedor.getTipoidentificacionId());
							formatoPagoMulticashData.setTipoIdClienteBeneficiario(tipoIdentificacion.getCodigoSri());
							//13: identificacion del proveedor
							formatoPagoMulticashData.setNumeroIdClienteBeneficiario(proveedor.getIdentificacion());
							
							
							//14: nombre del proveedor
							//ning˙n caracter especial y m·ximo 40 caracteres
							//formatoPagoMulticashData.setNombreClienteBeneficiario(proveedor.getRazonSocial());
							String razonSocialProveedor = removerCaracteresEspeciales(proveedor.getRazonSocial());
							if(razonSocialProveedor.length() > 40){
								razonSocialProveedor = razonSocialProveedor.substring(0, 40);
							}
							formatoPagoMulticashData.setNombreClienteBeneficiario(razonSocialProveedor);
													
							
							//15: direccion del proveedor
							//formatoPagoMulticashData.setDireccionBeneficiario(proveedorOficina.getDireccion());
							formatoPagoMulticashData.setDireccionBeneficiario("");
							//16: ciudad del proveedor
							CiudadIf ciudad = SessionServiceLocator.getCiudadSessionService().getCiudad(proveedorOficina.getCiudadId());
							//formatoPagoMulticashData.setCiudadBeneficiario(ciudad.getNombre());
							formatoPagoMulticashData.setCiudadBeneficiario("");
							//17: telefono del beneficiario
							//formatoPagoMulticashData.setTelefonoBeneficiario(proveedorOficina.getTelefono());
							formatoPagoMulticashData.setTelefonoBeneficiario("");
							//18: como la forma de pago es CTA entonces debe ir en blanco, si fuese efectivo o cheque iria la ciudad
							formatoPagoMulticashData.setLocalidadPago("");
							//19: preimpresos de las compras
							formatoPagoMulticashData.setReferencia(comprobanteEgresoData.getPreimpresoFactura().split("-")[2]);
							//20: (opcional) observaciÛn y luego de | correo del proveedor para que se le notifique del pago
							if(proveedorOficina.getEmail() != null && !proveedorOficina.getEmail().equals("")){
								//no debe haber espacios en el texto
								formatoPagoMulticashData.setReferenciaAdicional("PAGO|" + proveedorOficina.getEmail());
							}else{
								formatoPagoMulticashData.setReferenciaAdicional("");
							}
							
							carterasEgresoMulticashMap.put(cartera.getId(), cartera.getId());
							
							pagoMulticashColeccion.add(formatoPagoMulticashData);
						}
					}
				}
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
	}
	
	public static String removerCaracteresEspeciales(String input) {
	    // Cadena de caracteres original a sustituir.
	    String original = "·‡‰ÈËÎÌÏÔÛÚˆ˙˘¸Ò¡¿ƒ…»ÀÕÃœ”“÷⁄Ÿ‹—Á«&.";
	    // Cadena de caracteres ASCII que reemplazar·n los originales.
	    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcCY ";
	    String output = input;
	    for (int i=0; i<original.length(); i++) {
	        // Reemplazamos los caracteres especiales.
	        output = output.replace(original.charAt(i), ascii.charAt(i));
	    }//for i
	    
	   return output;
	}

	private AsientoIf generarReporteAsientoComprobante(CarteraIf cartera) throws GenericBusinessException {
		AsientoIf asientoAutomatico = null;
		if(!cartera.getEstado().equals(ESTADO_ANULADO)){
			Map asientoMap = new HashMap();
			asientoMap.put("tipoDocumentoId",cartera.getTipodocumentoId());
			asientoMap.put("transaccionId",cartera.getId());
			if(!SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(asientoMap).isEmpty()){
				asientoAutomatico = (AsientoIf)SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(asientoMap).iterator().next();
				//opcion de generar el reporte del asiento
				generarReporteAsiento(asientoAutomatico);
			}				
		}
		return asientoAutomatico;
	}
	
	private void generarReporteAsiento(AsientoIf asientoReporte) {
		try {
			int opcion = JOptionPane.showOptionDialog(null, "øDesea generar el reporte del Asiento?", "ConfirmaciÛn", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if(opcion == JOptionPane.YES_OPTION) {
				ArrayList<AsientoDetalleIf> asientoDetalleColeccion = (ArrayList<AsientoDetalleIf>)SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByAsientoId(asientoReporte.getId());
				double totalDebe = 0D;
				double totalHaber = 0D;
				List<AutorizarAsientoData> autorizarAsientoDataColeccion = new ArrayList<AutorizarAsientoData>();
				for(AsientoDetalleIf asientoDetalle : asientoDetalleColeccion){
					AutorizarAsientoData data = new AutorizarAsientoData();
					CuentaIf cuenta = (CuentaIf) cuentasMap.get(asientoDetalle.getCuentaId());
					data.setCuenta(cuenta.getCodigo());
					data.setFechaMovimiento(asientoReporte.getFecha().toString());
					data.setGlosa((asientoDetalle.getGlosa().length()>70?asientoDetalle.getGlosa().substring(0,70):asientoDetalle.getGlosa()));
					data.setDebe(formatoDecimal.format(asientoDetalle.getDebe()));
					data.setHaber(formatoDecimal.format(asientoDetalle.getHaber()));
					totalDebe += asientoDetalle.getDebe().doubleValue();
					data.setTotalDebe(formatoDecimal.format(totalDebe));
					totalHaber += asientoDetalle.getHaber().doubleValue();
					data.setTotalHaber(formatoDecimal.format(totalHaber));
					data.setMes(Utilitarios.getNombreMes(asientoReporte.getFecha().getMonth() + 1).substring(0,3));
					data.setNombreCuenta((cuenta.getNombre().length()>70?cuenta.getNombre().substring(0,70):cuenta.getNombre()));
					data.setNumero(asientoReporte.getNumero());
					if (asientoReporte.getElaboradoPorId() != null) {
						UsuarioIf elaboradoPor = (UsuarioIf) usuariosMap.get(asientoReporte.getElaboradoPorId());
						EmpleadoIf empleado = (EmpleadoIf) empleadosMap.get(elaboradoPor.getEmpleadoId());
						data.setElaboradoPor(empleado.getNombres() + " " + empleado.getApellidos());
					}
					
					if (asientoReporte.getAutorizadoPorId() != null) {
						UsuarioIf autorizadoPor = ((UsuarioIf) Parametros.getUsuarioIf());
						EmpleadoIf empleado = (EmpleadoIf) empleadosMap.get(autorizadoPor.getEmpleadoId());
						data.setAutorizadoPor(empleado.getNombres() + " " + empleado.getApellidos());
					}
					data.setTipoDocumentoId((asientoReporte.getTipoDocumentoId()!=null)?asientoReporte.getTipoDocumentoId():null);
					data.setTransaccionId((asientoReporte.getTransaccionId()!=null)?asientoReporte.getTransaccionId():null);
					autorizarAsientoDataColeccion.add(data);
				}
				
				String fileName = "jaspers/contabilidad/RPAutorizacionAsiento.jasper";
				int seccionesHoja = 1;
				Map tiposDocumentoMap = SessionServiceLocator.getTipoDocumentoSessionService().mapearTiposDocumento(Parametros.getIdEmpresa());
				Map tiposTroqueladoMap = SessionServiceLocator.getTipoTroqueladoSessionService().mapearTiposTroquelado();
				if (asientoReporte.getTipoDocumentoId() != null) {
					TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get(asientoReporte.getTipoDocumentoId());
					if (tipoDocumento.getTipoTroqueladoId() != null) {
						TipoTroqueladoIf tipoTroquelado = (TipoTroqueladoIf) tiposTroqueladoMap.get(tipoDocumento.getTipoTroqueladoId());
						if (tipoTroquelado.getNumeroSeccionesHoja() > seccionesHoja)
							seccionesHoja = tipoTroquelado.getNumeroSeccionesHoja();
					}
				}
				
				if (seccionesHoja == 2)
					fileName = "jaspers/contabilidad/RPAutorizacionAsientoDoble.jasper";
				else if (seccionesHoja == 4)
					fileName = "jaspers/contabilidad/RPAutorizacionAsientoCuadruple.jasper";
				
				HashMap parametrosMap = new HashMap();
				MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("ASIENTO").iterator().next();
				
				parametrosMap.put("codigoReporte", menu.getCodigo());
				EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
				parametrosMap.put("empresa", empresa.getNombre());
				parametrosMap.put("ruc", empresa.getRuc());
				OficinaIf oficina = (OficinaIf) Parametros.getOficina();
				CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
				parametrosMap.put("ciudad", ciudad.getNombre());
				parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
				parametrosMap.put("usuario", Parametros.getUsuario().toLowerCase());
				parametrosMap.put("emitido", Utilitarios.dateHoraHoy());
				ReportModelImpl.processReport(fileName, parametrosMap, autorizarAsientoDataColeccion, true);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}
	
	private int getIndiceCarteraPagoDetalle(CarteraDetalleIf carteraDetalleIf) {
		for (int i=0; i<getCarteraPagoDetalleColeccion().size(); i++) {
			CarteraDetalleIf carteraPagoDetalle = getCarteraPagoDetalleColeccion().get(i);
			if (carteraPagoDetalle.getId().compareTo(carteraDetalleIf.getId()) == 0)
				return i;
		}
		
		return -1;
	}
	
	private ComprobanteEgresoData agregarDetalleComprobanteEgreso(CarteraDetalleIf carteraDetalle, 
			CarteraAfectaIf carteraAfecta) {
		
		Calendar fechaActual = new GregorianCalendar();
		ComprobanteEgresoData data = new ComprobanteEgresoData();
		
		try {
			DocumentoIf documento = (DocumentoIf) documentosMap.get(carteraDetalle.getDocumentoId());
			CarteraDetalleIf carteraDetalleCompraAfectada = SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(carteraAfecta.getCarteraafectaId());
			CarteraIf carteraCompraAfectada = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleCompraAfectada.getCarteraId());
			CompraIf compra = null;
			
			if (carteraCompraAfectada.getReferenciaId() != null)
				compra = SessionServiceLocator.getCompraSessionService().getCompra(carteraCompraAfectada.getReferenciaId());
			if (documento.getRetencionRenta().equals("N") && documento.getRetencionIva().equals("N") && documento.getCheque().equals("S")) {
				CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) cuentasBancariasMap.get(carteraDetalle.getCuentaBancariaId());
				BancoIf banco = (BancoIf) bancosMap.get(cuentaBancaria.getBancoId());
				data.setBanco(banco.getNombre());
				data.setNumeroCuenta(cuentaBancaria.getCuenta());
				if(Utilitarios.compararFechas(new Date(fechaActual.getTime().getTime()), new Date(carteraDetalle.getFechaCartera().getTime())) == -1){
					data.setNumeroCheque(carteraDetalle.getPreimpreso() + " [POSTFECHADO: " + Utilitarios.getFechaCortaUppercase(carteraDetalle.getFechaCartera()) + "]");
				}else if (carteraDetalle.getPreimpreso() != null){
					data.setNumeroCheque(carteraDetalle.getPreimpreso());
				}else{
					data.setNumeroCheque("D/B");
				}
			} else if (documento.getRetencionRenta().equals("N") && documento.getRetencionIva().equals("N") && documento.getDebitoBancario().equals("S")) {
				data.setBanco(documento.getNombre());
				CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) cuentasBancariasMap.get(carteraDetalle.getCuentaBancariaId());
				data.setNumeroCuenta(cuentaBancaria.getCuenta());
				data.setNumeroCheque("N/A");
			} else if (documento.getRetencionRenta().equals("N") && documento.getRetencionIva().equals("N") && documento.getCheque().equals("N")) {
				//data.setBanco("PAGO EN EFECTIVO");
				data.setBanco(documento.getNombre());
				data.setNumeroCuenta("N/A");
				data.setNumeroCheque("N/A");
			}
			
			data.setFechaCompra(Utilitarios.getFechaCortaUppercase((compra!=null)?compra.getFecha():carteraCompraAfectada.getFechaEmision()));
			data.setCodigoCompra((compra!=null)?compra.getCodigo():"");
			data.setPreimpresoFactura(" F# " + carteraCompraAfectada.getPreimpreso());
			
			if (compra != null){
				Collection ordenesAsociadas = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(compra.getId());
				if(ordenesAsociadas.size() > 0){
					String detalle = obtenerDetalle(ordenesAsociadas);
					data.setDetalle(detalle);					
				}else{
					data.setDetalle(compra.getObservacion().length()>52?compra.getObservacion().substring(0,52):compra.getObservacion());
				}							
			}else{
				data.setDetalle(carteraCompraAfectada.getComentario());
			}
			
			data.setValor(formatoDecimal.format(carteraAfecta.getValor().doubleValue()));
			data.setSaldo(formatoDecimal.format(carteraCompraAfectada.getSaldo().doubleValue()));
			
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		
		return data;
	}
	
	/*Collection ordenesAsociadas = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(compra.getId());
	if(ordenesAsociadas.size() > 0){
		String orden = "";
		Iterator ordenesAsociadasIt = ordenesAsociadas.iterator();
		while(ordenesAsociadasIt.hasNext()){
			OrdenAsociadaIf ordenAsociada = (OrdenAsociadaIf)ordenesAsociadasIt.next();
			if(ordenAsociada.getTipoOrden().equals("OC")){
				OrdenCompraIf ordenCompra = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(ordenAsociada.getOrdenId());
				orden = ordenCompra.getCodigo();
				
				Collection presupuestosDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByOrdenCompraId(ordenCompra.getId());
				Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
				while(presupuestosDetalleIt.hasNext()){
					PresupuestoDetalleIf presupuestoDetalle = (PresupuestoDetalleIf) presupuestosDetalleIt.next();
					PresupuestoIf presupuesto = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalle.getPresupuestoId());
					ClienteOficinaIf clienteOficina = SessionSer
				}
			}else if(ordenAsociada.getTipoOrden().equals("OM")){
				OrdenMedioIf ordenMedio = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenAsociada.getOrdenId());
				orden = ordenMedio.getCodigo();
			}
		}
		data.setDetalle("Orden: " + orden + ", Cliente: ");
	}*/
	
	public String obtenerDetalle(Collection ordenesAsociadas){
		String detalle = "";
		try {
			String ordenCodigo = "";
			String clienteNombreLegal = "";
			OrdenAsociadaIf ordenAsociada = (OrdenAsociadaIf)ordenesAsociadas.iterator().next();
			if(ordenAsociada.getTipoOrden().equals("OC")){
				Collection ordenesCompraPresupuestosClientesClientesOficina = SessionServiceLocator.getCompraSessionService().findOrdenCompraPresupuestoClienteOficinaClienteByCompraId(compra.getId());
				Iterator ordenesCompraPresupuestosClientesClientesOficinaIt = ordenesCompraPresupuestosClientesClientesOficina.iterator();
				while(ordenesCompraPresupuestosClientesClientesOficinaIt.hasNext()){
					Object[] ordenCompraPresupuestoClienteClienteOficina = (Object[])ordenesCompraPresupuestosClientesClientesOficinaIt.next();
					OrdenCompraIf ordenCompra = (OrdenCompraIf)ordenCompraPresupuestoClienteClienteOficina[0];
					ClienteIf cliente = (ClienteIf)ordenCompraPresupuestoClienteClienteOficina[3];
					ordenCodigo = ordenCompra.getCodigo().split("-")[1];
					clienteNombreLegal = cliente.getNombreLegal();
				}
					
			}else if(ordenAsociada.getTipoOrden().equals("OM")){
				Collection ordenesMedioClientesClientesOficina = SessionServiceLocator.getCompraSessionService().findOrdenMedioClienteOficinaClienteByCompraId(compra.getId());
				Iterator ordenesMedioClientesClientesOficinaIt = ordenesMedioClientesClientesOficina.iterator();
				while(ordenesMedioClientesClientesOficinaIt.hasNext()){
					Object[] ordenMedioClienteClienteOficina = (Object[])ordenesMedioClientesClientesOficinaIt.next();
					OrdenMedioIf ordenMedio = (OrdenMedioIf)ordenMedioClienteClienteOficina[0];
					ClienteIf cliente = (ClienteIf)ordenMedioClienteClienteOficina[2];
					ordenCodigo = ordenMedio.getCodigo().split("-")[1];
					clienteNombreLegal = cliente.getNombreLegal();
				}
			}
			detalle = "Orden: " + ordenCodigo + ", Cliente: " + clienteNombreLegal;
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return detalle;
	}
	
	public Vector<CarteraIf> getCarteraColeccion() {
		return carteraColeccion;
	}

	public void setCarteraColeccion(Vector<CarteraIf> carteraColeccion) {
		this.carteraColeccion = carteraColeccion;
	}
	
	public Vector<CarteraDetalleIf> getCarteraPagoDetalleColeccion() {
		return carteraPagoDetalleColeccion;
	}

	public void setCarteraPagoDetalleColeccion(Vector<CarteraDetalleIf> carteraPagoDetalleColeccion) {
		this.carteraPagoDetalleColeccion = carteraPagoDetalleColeccion;
	}
	
	public void refresh() {
		clean();
		cargarTabla();
	}

	public Comparator<ProviderPaymentReceiptRowData> getProviderPaymentReceiptRowDataComparator() {
		return providerPaymentReceiptRowDataComparator;
	}

	public void setProviderPaymentReceiptRowDataComparator(
			Comparator<ProviderPaymentReceiptRowData> providerPaymentReceiptRowDataComparator) {
		this.providerPaymentReceiptRowDataComparator = providerPaymentReceiptRowDataComparator;
	}
}
