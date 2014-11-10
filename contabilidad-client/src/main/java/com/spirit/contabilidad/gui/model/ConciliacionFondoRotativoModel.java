package com.spirit.contabilidad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
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

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.SaldoCuentaIf;
import com.spirit.contabilidad.gui.data.CuentaBancariaEgresoData;
import com.spirit.contabilidad.gui.data.FondoRotativoData;
import com.spirit.contabilidad.gui.data.FondoRotativoEgresoData;
import com.spirit.contabilidad.gui.data.FondoRotativoFacturaCanceladaData;
import com.spirit.contabilidad.gui.data.FondoRotativoFacturaPendienteData;
import com.spirit.contabilidad.gui.data.FondoRotativoIngresoData;
import com.spirit.contabilidad.gui.panel.JPConciliacionFondoRotativo;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.gui.controller.GeneralFinder;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.NuevoCuentaBancariaCriteria;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioDetalleIf;
import com.spirit.medios.entity.PlanMedioFacturacionIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PlanMedioMesIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoFacturacionIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.PresupuestoProductoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.DeepCopy;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;
 

public class ConciliacionFondoRotativoModel extends JPConciliacionFondoRotativo {
	
	private static final long serialVersionUID = 120127567314785861L;
	ArrayList<CuentaBancariaIf> cuentaBancariaArrayList =  new ArrayList<CuentaBancariaIf>();
	CuentaBancariaIf cuentaBancariaIf = null;
	private DefaultTableModel tableModel;
	private Map tiposDocumentoMap = new HashMap();
	private Map documentosMap = new HashMap();
	private Map modulosMap = new HashMap();
	private int selectedRow = -1;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private ClienteOficinaCriteria clienteOficinaCriteria;
	protected ClienteOficinaIf clienteOficinaIf;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String TODOS = "TODOS";
	protected TipoProveedorIf tipoProveedorIf = null;
	protected TipoProductoIf tipoProductoIf = null;
	protected TipoDocumentoIf tipoDocumentoIf = null;
	private Map<Long, TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long, TipoDocumentoIf>();
	private Map<Long, TipoProveedorIf> mapaTipoProveedor = new HashMap<Long, TipoProveedorIf>();
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long, ClienteIf> mapaCliente = new HashMap<Long, ClienteIf>();
	
	private Map<Long, PlanMedioIf> mapaPlanMedioPorFacturar = new HashMap<Long, PlanMedioIf>();
	private Map<Long, Double> mapaPlanMedioPorFacturarValores = new HashMap<Long, Double>();
	
	private Map<Long, PresupuestoIf> mapaPresupuestoPorFacturar = new HashMap<Long, PresupuestoIf>();
	private Map<Long, Double> mapaPresupuestoPorFacturarValores = new HashMap<Long, Double>();
	
	private Map<Long, CarteraIf> mapaCarteraEgresoPorFacturar = new HashMap<Long, CarteraIf>();
	private Map<Long, Double> mapaCarteraEgresoPorFacturarValores = new HashMap<Long, Double>();
	
	private Map<Long, AsientoIf> mapaDebitosBancarios = new HashMap<Long, AsientoIf>(); 
	
	
	public ConciliacionFondoRotativoModel() {
		cargarMapas();
		cargarComboTipoDocumento();
		cargarComboTipoProveedor();
		cargarComboTipoProducto();
		iniciarComponentes();
		anchoColumnasTabla();
		alineacionColumnasTabla();
		initListeners();
		showSaveMode();
		new HotKeyComponent(this);		
		getBtnBuscarCuentaBancaria().addKeyListener(oKeyAdapterBtnCuentaBancaria);		
		//KeyListeners de radio buttons
		getRbNo().addKeyListener(oKeyAdapterRbNo);
		getRbNo2().addKeyListener(oKeyAdapterRbNo2);		
		getRbSi().addKeyListener(oKeyAdapterRbSi);		
		getRbSiAnulados().addKeyListener(oKeyAdapterRbSiAnulados);
		//KeyListeners de Botones
		getBtnAgregarCuentaBancaria().addKeyListener(oKeyAdapterBtnAgregarCuenta);		
		getBtnEliminarCuentaBancaria().addKeyListener(oKeyAdapterBtnEliminarCuenta);
	 
	}
	
	private void cargarMapas(){
		try {
			mapaTipoDocumento.clear();
			Collection tiposDocumento = SessionServiceLocator
					.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator tiposDocumentoIt = tiposDocumento.iterator();
			while (tiposDocumentoIt.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoIt
						.next();
				mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
			}
			
			mapaTipoProveedor.clear();
			Collection tiposProveedor = SessionServiceLocator
					.getTipoProveedorSessionService().findTipoProveedorByEmpresaId(Parametros.getIdEmpresa());
			Iterator tiposProveedorIt = tiposProveedor.iterator();
			while (tiposProveedorIt.hasNext()) {
				TipoProveedorIf tipoProveedor = (TipoProveedorIf) tiposProveedorIt
						.next();
				mapaTipoProveedor.put(tipoProveedor.getId(), tipoProveedor);
			}
			
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator
					.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while (clientesOficinaIt.hasNext()) {
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clientesOficinaIt
						.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
			
			mapaCliente.clear();
			Collection clientes = SessionServiceLocator
					.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesIt = clientes.iterator();
			while (clientesIt.hasNext()) {
				ClienteIf cliente = (ClienteIf) clientesIt.next();
				mapaCliente.put(cliente.getId(), cliente);
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarComboTipoDocumento(){
		try {
			Long idModulo = ((ModuloIf) SessionServiceLocator.getModuloSessionService().findModuloByCodigo("FACT").iterator().next()).getId();
			List<TipoDocumentoIf> tiposDocumento = (List<TipoDocumentoIf>) GeneralFinder.findTipoDocumento(Parametros.getIdEmpresa(), idModulo);
						
			List tipoDocumentosFacturacion = new ArrayList();
			for(TipoDocumentoIf tipoDocumento : tiposDocumento){
				tipoDocumentosFacturacion.add(tipoDocumento);
				
				if(tipoDocumento.getCodigo().equals("FAR")){
					tipoDocumentoIf = tipoDocumento;				
				}
			}
			
			tipoDocumentosFacturacion.add(TODOS);
			refreshCombo(getCmbTipoDocumento(),tipoDocumentosFacturacion);
			getCmbTipoDocumento().setSelectedItem(tipoDocumentoIf);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarComboTipoProveedor(){
		try {
			List tiposMedios = new ArrayList();
						
			Iterator it = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				TipoProveedorIf tipoProveedorMedio = (TipoProveedorIf) it.next();
				tiposMedios.add(tipoProveedorMedio);
				
				/*if(tipoProveedorMedio.getNombre().equals("PRENSA")){
					tipoProveedorIf = tipoProveedorMedio;				
				}*/
			}
						
			tiposMedios.add(TODOS);
			refreshCombo(getCmbTipoProveedor(),tiposMedios);
			
			if(tipoProveedorIf != null){
				getCmbTipoProveedor().setSelectedItem(tipoProveedorIf);
			}else{
				getCmbTipoProveedor().setSelectedItem(TODOS);
			}
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	Comparator<TipoProductoIf> ordenadorTipoProductoPorNombre = new Comparator<TipoProductoIf>(){
		public int compare(TipoProductoIf o1, TipoProductoIf o2) {
			return o1.getNombre().compareTo(o2.getNombre());
		}		
	};
	
	private void cargarComboTipoProducto(){
		try {
			List tiposProducto = new ArrayList();
			
			List tiposProductosColeccion = (List) SessionServiceLocator.getTipoProductoSessionService().findTipoProductoByEmpresaId(Parametros.getIdEmpresa());
			Collections.sort((ArrayList)tiposProductosColeccion, ordenadorTipoProductoPorNombre);
			Iterator tiposProductosColeccionIt = tiposProductosColeccion.iterator();
			
			while (tiposProductosColeccionIt.hasNext()) {
				TipoProductoIf tipoProducto = (TipoProductoIf) tiposProductosColeccionIt.next();
				tiposProducto.add(tipoProducto);
				
				if(tipoProducto.getNombre().equals("AVISOS PRENSA")){
					tipoProductoIf = tipoProducto;				
				}
			}
			
			tiposProducto.add(TODOS);
			refreshCombo(getCmbTipoProducto(), tiposProducto);
			
			if(tipoProductoIf != null){
				getCmbTipoProducto().setSelectedItem(tipoProductoIf);
			}else{
				getCmbTipoProducto().setSelectedItem(TODOS);
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	KeyListener oKeyAdapterRbSiAnulados = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				if(getRbSiAnulados().isSelected())
					getRbSiAnulados().setSelected(false);
				else
					getRbSiAnulados().setSelected(true);
			}
		}
	};
	
	KeyListener oKeyAdapterRbSi = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				if(getRbSi().isSelected())
					getRbSi().setSelected(false);
				else
					getRbSi().setSelected(true);
			}
		}
	};
	
	KeyListener oKeyAdapterRbNo = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				if(getRbNo().isSelected()) getRbNo().setSelected(false);
				else getRbNo().setSelected(true);
			}
		}
	};
	
	KeyListener oKeyAdapterRbNo2 = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				if(getRbNo2().isSelected()) getRbNo2().setSelected(false);
				else getRbNo2().setSelected(true);
			}
		}
	};
	
	KeyListener oKeyAdapterBtnEliminarCuenta = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				int filaSeleccionada = getTblCuentaBancaria().getSelectedRow();
				DefaultTableModel modelo = (DefaultTableModel) getTblCuentaBancaria().getModel();
				if ( filaSeleccionada >= 0 ){
					modelo.removeRow(filaSeleccionada);
					cuentaBancariaArrayList.remove(filaSeleccionada);
					limpiarTabla(getTblDetalleConciliacion());
				}
			}
		}
	};

	KeyListener oKeyAdapterBtnAgregarCuenta = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				  if (cuentaBancariaIf!=null && !contieneCuentaBancaria(cuentaBancariaArrayList, cuentaBancariaIf) ){
						cuentaBancariaArrayList.add(cuentaBancariaIf);
						llenarTablaCuentasBancarias();
						cuentaBancariaIf = null;
						getTxtCuentaBancaria().setText("");					
					} else {					
						SpiritAlert.createAlert("Cuenta Bancaria ya está en lista !!", SpiritAlert.WARNING);
					}
			}
		}
	};
	
	KeyListener oKeyAdapterBtnCuentaBancaria = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				buscarCuentaBancaria();
			}
		}
	};
	
	private void iniciarComponentes(){
		Date fechaHoy = new Date();
		getCmbFechaFin().setDate(fechaHoy);
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setShowNoneButton(false);
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setEditable(false);
		getBtnBuscarCuentaBancaria().setText("");
		getBtnBuscarCuentaBancaria().setToolTipText("Buscar Cuenta Bancaria");
		getBtnBuscarCuentaBancaria().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnAgregarCuentaBancaria().setText("");
		getBtnAgregarCuentaBancaria().setToolTipText("Agregar Cuenta Bancaria");
		getBtnAgregarCuentaBancaria().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnEliminarCuentaBancaria().setText("");
		getBtnEliminarCuentaBancaria().setToolTipText("Eliminar Cuenta Bancaria");
		getBtnEliminarCuentaBancaria().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnBuscarClienteOficina().setToolTipText("Buscar Cliente Oficina");
		getBtnBuscarClienteOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumnaCuentaBancaria = getTblCuentaBancaria().getColumnModel().getColumn(0);
		anchoColumnaCuentaBancaria.setPreferredWidth(120);
		anchoColumnaCuentaBancaria = getTblCuentaBancaria().getColumnModel().getColumn(1);
		anchoColumnaCuentaBancaria.setPreferredWidth(100);
		anchoColumnaCuentaBancaria = getTblCuentaBancaria().getColumnModel().getColumn(2);
		anchoColumnaCuentaBancaria.setPreferredWidth(100);
		anchoColumnaCuentaBancaria = getTblCuentaBancaria().getColumnModel().getColumn(3);
		anchoColumnaCuentaBancaria.setPreferredWidth(110);
	}
	
	private void alineacionColumnasTabla() {
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblDetalleConciliacion().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		TableCellRendererHorizontalCenterAlignment tableCellRendederHorizontalCenterAlignment = new TableCellRendererHorizontalCenterAlignment();
		getTblDetalleConciliacion().getColumnModel().getColumn(1).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		getTblDetalleConciliacion().getColumnModel().getColumn(2).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
	}
	
	private void initListeners(){
		getBtnBuscarCuentaBancaria().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				buscarCuentaBancaria();
			}
		});
		
		getBtnAgregarCuentaBancaria().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				  if (cuentaBancariaIf!=null && !contieneCuentaBancaria(cuentaBancariaArrayList, cuentaBancariaIf) ){
					cuentaBancariaArrayList.add(cuentaBancariaIf);
					llenarTablaCuentasBancarias();
					cuentaBancariaIf = null;
					getTxtCuentaBancaria().setText("");					
				} else {					
					SpiritAlert.createAlert("Cuenta Bancaria ya está en lista !!", SpiritAlert.WARNING);
				} 
			}
		});
		
		getBtnEliminarCuentaBancaria().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int filaSeleccionada = getTblCuentaBancaria().getSelectedRow();
				DefaultTableModel modelo = (DefaultTableModel) getTblCuentaBancaria().getModel();
				if ( filaSeleccionada >= 0 ){
					modelo.removeRow(filaSeleccionada);
					cuentaBancariaArrayList.remove(filaSeleccionada);
					limpiarTabla(getTblDetalleConciliacion());
				}
			}
		});
		
		getTblCuentaBancaria().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				enableSelectedRowForAuthorize(evt);
			}
		});
		
		getTblCuentaBancaria().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				enableSelectedRowForAuthorize(evt);
			}
		});
		
		getBtnBuscarClienteOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tituloVentanaBusqueda = "de Oficinas del Cliente";
				clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_CLIENTE, "", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					getTxtClienteOficina().setText(clienteOficinaIf.getDescripcion());
				}
			}
		});
		
		getCmbTipoProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoProveedor().getSelectedItem() != null && !getCmbTipoProveedor().getSelectedItem().equals(TODOS)){
					tipoProveedorIf = (TipoProveedorIf) getCmbTipoProveedor().getSelectedItem();
				}
				else{
					tipoProveedorIf = null;
				}
			}
		});
		
		getCmbTipoProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoProducto().getSelectedItem() != null && !getCmbTipoProducto().getSelectedItem().equals(TODOS)){
					tipoProductoIf = (TipoProductoIf) getCmbTipoProducto().getSelectedItem();
				}
				else{
					tipoProductoIf = null;
				}
			}
		});
		
		getCmbTipoDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoDocumento().getSelectedItem() != null && !getCmbTipoDocumento().getSelectedItem().equals(TODOS)){
					tipoDocumentoIf = (TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem();
				}
				else{
					tipoDocumentoIf = null;
				}
			}
		});
		
		getCmbFechaInicio().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				limpiarTabla(getTblDetalleConciliacion());
			}}
		);
		
		getCmbFechaFin().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				limpiarTabla(getTblDetalleConciliacion());
			}}
		);
	}
	
	private void enableSelectedRowForAuthorize(ComponentEvent evt) {
		setCursorEspera();
		if (getTblCuentaBancaria().getSelectedRow() != -1) {
			int selectedRow = ((JTable) evt.getSource()).getSelectedRow();
			selectRow(selectedRow);
		}
		setCursorNormal();
	}
	
	private void selectRow(int selectedRow) {		
		limpiarTabla(getTblDetalleConciliacion());
		try {
			cargarDatosTabla(selectedRow);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}	
	}

	private void cargarDatosTabla(int selectedRow) throws GenericBusinessException {
		Collection<FondoRotativoData> datosConciliacionFondoRotativo = crearConciliacionFondoRotativo(selectedRow);
		FondoRotativoData conciliacionFondoRotativo = datosConciliacionFondoRotativo.iterator().next();
		tableModel = (DefaultTableModel) getTblDetalleConciliacion().getModel();
		if (conciliacionFondoRotativo != null) {
			// Saldo inicial bancario
			Vector<String> fila = new Vector<String>();			 
			fila.add("<html><b>" + "SALDO INICIAL BANCARIO" + "</b></html>");
			fila.add("");
			fila.add("");		
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(conciliacionFondoRotativo.getSaldoInicialBancario())) + "</b></html>");
			tableModel.addRow(fila);
			
			// Saldo inicial
			fila = new Vector<String>();		 
			fila.add("<html><b>" + "SALDO INICIAL EN LIBROS" + "</b></html>");
			fila.add("");
			fila.add("");		
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(conciliacionFondoRotativo.getSaldoInicial())) + "</b></html>");
			tableModel.addRow(fila);
			double totalEnLibros = conciliacionFondoRotativo.getSaldoInicial();
			//  Ingresos
			fila = new Vector<String>();
			fila.add("<html><b>" + "(+) INGRESOS" + "</b></html>");
			fila.add("");
			fila.add("");
			fila.add("");
			tableModel.addRow(fila);
			double totalIngresos = 0D;
			for (int i=0; i<conciliacionFondoRotativo.getIngresosConciliacionFondoRotativo().size(); i++) {
				FondoRotativoIngresoData ingresoConciliacionFondoRotativo = conciliacionFondoRotativo.getIngresosConciliacionFondoRotativo().get(i);
				fila = new Vector<String>();
				fila.add(ingresoConciliacionFondoRotativo.getDetalleComprobante());
				fila.add(ingresoConciliacionFondoRotativo.getCodigoComprobante());
				fila.add(getNumeroChequeParaTabla(ingresoConciliacionFondoRotativo.getNumeroCheque()));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(ingresoConciliacionFondoRotativo.getValorComprobante())));
				totalIngresos = conciliacionFondoRotativo.getTotalIngresos();
				tableModel.addRow(fila);
			}
			fila = new Vector<String>();
			fila.add("");
			fila.add("");
			fila.add("<html><b>" + "TOTAL ING" + "</b></html>");
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(totalIngresos)) + "</b></html>");
			tableModel.addRow(fila);
			totalEnLibros += totalIngresos;
			fila = new Vector<String>();
			fila.add("");
			fila.add("");
			fila.add("");
			fila.add("");
			tableModel.addRow(fila);
			// Egresos
			fila = new Vector<String>();
			fila.add("<html><b>" + "(-) EGRESOS" + "</b></html>");
			fila.add("");
			fila.add("");
			fila.add("");
			tableModel.addRow(fila);
			double totalEgresos = 0D;
			for (int i=0; i<conciliacionFondoRotativo.getEgresosConciliacionFondoRotativo().size(); i++) {
				FondoRotativoEgresoData egresoConciliacionFondoRotativo = conciliacionFondoRotativo.getEgresosConciliacionFondoRotativo().get(i);
				fila = new Vector<String>();
				fila.add(egresoConciliacionFondoRotativo.getFecha());
				fila.add(egresoConciliacionFondoRotativo.getDetalle());
				fila.add(getNumeroChequeParaTabla(egresoConciliacionFondoRotativo.getCheque()));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(egresoConciliacionFondoRotativo.getValor())));
				totalEgresos += egresoConciliacionFondoRotativo.getValor();
				tableModel.addRow(fila);
			}
			fila = new Vector<String>();
			fila.add("");
			fila.add("");
			fila.add("<html><b>" + "TOTAL EGR" + "</b></html>");
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(totalEgresos)) + "</b></html>");
			tableModel.addRow(fila);
			totalEnLibros -= totalEgresos;
			fila = new Vector<String>();
			fila.add("");
			fila.add("");
			fila.add("");
			fila.add("");
			tableModel.addRow(fila);
			fila = new Vector<String>();
			fila.add("<html><b>" + "TOTAL EN LIBROS" + "</b></html>");
			fila.add("");
			fila.add("");
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(totalEnLibros)) + "</b></html>");
			tableModel.addRow(fila);
			fila = new Vector<String>();
			fila.add("");
			fila.add("");
			fila.add("");
			fila.add("");
			tableModel.addRow(fila);
			// Cheques en circulación
			fila = new Vector<String>();
			fila.add("<html><b>" + "CHEQUES EN CIRCULACIÓN" + "</b></html>");
			fila.add("");
			fila.add("");
			fila.add("");
			tableModel.addRow(fila);
			double totalChequesEmitidos = 0D;
			for (int i=0; i<conciliacionFondoRotativo.getChequesEmitidosConciliacionFondoRotativo().size(); i++) {
				FondoRotativoEgresoData chequeEmitidoConciliacionFondoRotativo = conciliacionFondoRotativo.getChequesEmitidosConciliacionFondoRotativo().get(i);
				fila = new Vector<String>();
				fila.add(chequeEmitidoConciliacionFondoRotativo.getFecha());
				fila.add(chequeEmitidoConciliacionFondoRotativo.getDetalle());
				fila.add(getNumeroChequeParaTabla(chequeEmitidoConciliacionFondoRotativo.getCheque()));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(chequeEmitidoConciliacionFondoRotativo.getValor())));
				totalChequesEmitidos += chequeEmitidoConciliacionFondoRotativo.getValor();
				tableModel.addRow(fila);
			}
			fila = new Vector<String>();
			fila.add("<html><b>" + "TOTAL CHEQ. EMIT." + "</b></html>");
			fila.add("");
			fila.add("");			
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(totalChequesEmitidos)) + "</b></html>");
			tableModel.addRow(fila);
			fila = new Vector<String>();
			fila.add("");
			fila.add("");
			fila.add("");
			fila.add("");
			tableModel.addRow(fila);
			fila = new Vector<String>();
			fila.add("");
			fila.add("");
			fila.add("");
			fila.add("");
			tableModel.addRow(fila);
			// Saldo en bancos
			double saldoBanco = totalEnLibros + totalChequesEmitidos;
			fila = new Vector<String>();
			fila.add("<html><b>" + "SALDO EN BANCO" + "</b></html>");
			fila.add("");
			fila.add("");			
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(saldoBanco)) + "</b></html>");
			tableModel.addRow(fila);
		}
	}
	
	private boolean contieneCuentaBancaria(Collection<CuentaBancariaIf> cuentasBancarias,CuentaBancariaIf cuentaBancariaIf){
		for( CuentaBancariaIf cuentaBancaria : cuentasBancarias ){
			if ( cuentaBancaria.getId().equals(cuentaBancariaIf.getId()) )
				return true;
		}
		return false;
	}

	private void buscarCuentaBancaria() {
		NuevoCuentaBancariaCriteria cuentaBancariaCriteria = new NuevoCuentaBancariaCriteria("Cuentas Bancarias");
		Map queryBuilded = new HashMap();
		queryBuilded.put("cuentaCliente", "N");
		cuentaBancariaCriteria.setQueryBuilded(queryBuilded);
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), cuentaBancariaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if (popupFinder.getElementoSeleccionado() != null) {
			try {
				cuentaBancariaIf = (CuentaBancariaIf) popupFinder.getElementoSeleccionado();
				BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancariaIf.getBancoId());
				getTxtCuentaBancaria().setText(banco.getNombre() + " - " + cuentaBancariaIf.getCuenta());

			} catch(GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
		cuentaBancariaCriteria = null;
		popupFinder = null;
	}
	
	private void llenarTablaCuentasBancarias(){
		limpiarTabla(getTblCuentaBancaria());
		DefaultTableModel modelo = (DefaultTableModel) getTblCuentaBancaria().getModel();
		for ( CuentaBancariaIf cuentaBancaria : cuentaBancariaArrayList ){
			try {
				Object[] filaArreglo = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancariaPlusByCuentaBancariaId(cuentaBancaria.getId());
				modelo.addRow(filaArreglo);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			}
		}
	}
	
	public void clean() {
		cuentaBancariaArrayList = null;
		cuentaBancariaArrayList = new ArrayList<CuentaBancariaIf>();
		cuentaBancariaIf = null;
		//getCmbFechaInicio().setCalendar(null);
		//getCmbFechaFin().setCalendar(null);
		getTxtCuentaBancaria().setText("");
		limpiarTabla(getTblCuentaBancaria());
		limpiarTabla(getTblDetalleConciliacion());
	}
	
	public boolean validateFields() {
		Date fechaInicio = getCmbFechaInicio().getDate();
		if ( fechaInicio == null ){
			SpiritAlert.createAlert("Debe elegir Fecha Inicio !!", SpiritAlert.WARNING);
			return false;
		}
		Date fechaFin = getCmbFechaFin().getDate();
		if ( fechaFin == null ){
			SpiritAlert.createAlert("Debe elegir Fecha Fin !!", SpiritAlert.WARNING);
			return false;
		}
		if ( fechaInicio.compareTo(fechaFin) > 0 ){
			SpiritAlert.createAlert("Fecha Fin debe ser mayor o igual a Fecha Inicio !!", SpiritAlert.WARNING);
			return false;	
		}
		if ( cuentaBancariaArrayList == null || cuentaBancariaArrayList.size() == 0 ){
			SpiritAlert.createAlert("Debe elegir al menos una Cuenta Bancaria !!", SpiritAlert.INFORMATION);
			return false;
		}
		return true;
	}

	public void report() {
		try {
			if ( validateFields() ){
				String si = "Si"; 
		        String no = "No"; 
		        Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
				if(opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/contabilidad/RPConciliacionFondoRotativo2.jasper";
					HashMap parametrosMap = new HashMap();
					MenuIf menu = null;
					Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre("CONCILIACION FONDO ROTATIVO").iterator();
					if(menuIT.hasNext()) menu = (MenuIf)menuIT.next();					
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("usuario", Parametros.getUsuario().toLowerCase());
					parametrosMap.put("emitido", fechaEmision);
					Date fechaInicio = getCmbFechaInicio().getDate(); 
					Date fechaFin = getCmbFechaFin().getDate();
					parametrosMap.put("fechaInicial", Utilitarios.getFechaUppercase(fechaInicio) );
					parametrosMap.put("fechaFinal", Utilitarios.getFechaUppercase(fechaFin));
					
					String tipoProducto = "TODOS";
					if(tipoProductoIf != null){
						tipoProducto = tipoProductoIf.getNombre();
					}
					parametrosMap.put("tipoProducto", tipoProducto);
							
					if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) ) {
						parametrosMap.put("pathSubreportIngresos", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/contabilidad/RPConciliacionFondoRotativoIngresos2.jasper");
						parametrosMap.put("pathSubreportEgresos", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/contabilidad/RPConciliacionFondoRotativoEgresos2.jasper");
						parametrosMap.put("pathSubreportChequesEmitidos", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/contabilidad/RPConciliacionFondoRotativoChequesEmitidos2.jasper");
						parametrosMap.put("pathSubreportFacturasCanceladas", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/contabilidad/RPConciliacionFondoRotativoFacturasCanceladas.jasper");
						parametrosMap.put("pathSubreportFacturasPendientes", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/contabilidad/RPConciliacionFondoRotativoFacturasPendientes.jasper");
						parametrosMap.put("pathSubreportPresupuestosPendientes", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/contabilidad/RPConciliacionFondoRotativoPresupuestosPendientes.jasper");
						parametrosMap.put("pathSubreportPendientesPorCobrar", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/contabilidad/RPConciliacionFondoRotativoPendientesPorCobrar.jasper");
						parametrosMap.put("pathSubreportDebitosBancarios", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/contabilidad/RPConciliacionFondoRotativoDebitosBancarios.jasper");
					}						
					else 
						throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
										
					Collection<FondoRotativoData> datosConciliacionFondoRotativo = crearConciliacionFondoRotativo(-1);
					
					JRDataSource cuentasContables = new JRBeanCollectionDataSource(datosConciliacionFondoRotativo);
					
					ReportModelImpl.processReport(fileName, parametrosMap, cuentasContables, true);
					datosConciliacionFondoRotativo = null;
					cuentasContables = null;
					System.gc();
				}
			} 
		} catch (GenericBusinessException e) {
			setCursorNormal();
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (ParseException e) {
			setCursorNormal();
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
	}
	
	public double valorSumaInicial(String mesAnterior, String anio, String dia, CuentaIf cuentaIf, CuentaBancariaIf cuentaBancaria) {
		double totalSubDetalle = 0D;
		String mesActual=new Integer(new Integer(mesAnterior)).toString();
		
		if (!mesActual.equals("13")) {
			try {
				int diafin=new Integer(dia).intValue()-1;
				if (diafin!=0) {
					totalSubDetalle = 0D;
					Calendar calFechaIni = new GregorianCalendar(new Integer(anio).intValue(),new Integer(mesActual).intValue(),new Integer("1").intValue());
					Date fechaIni = new Date(calFechaIni.getTime().getTime());
					Calendar calFechaFin = new GregorianCalendar(new Integer(anio).intValue(),new Integer(mesActual).intValue(),diafin);
					Date fechaFin= new Date(calFechaFin.getTime().getTime());
					java.sql.Date fechaInicioSql = new java.sql.Date(fechaIni.getTime());
					java.sql.Date fechaFinSql = new java.sql.Date(fechaFin.getTime());
					// INGRESOS CONCILIACION BANCARIA
					double totalIngresos = 0D;
					Collection<Object[]> ingresos = SessionServiceLocator.getAsientoSessionService().findAsientosConciliacionBancaria(cuentaIf.getId(), fechaInicioSql , fechaFinSql);
					for ( Object[] ingreso : ingresos )			
						totalIngresos += ((BigDecimal)ingreso[4]).doubleValue();
					totalSubDetalle = totalIngresos;
					// EGRESOS CONCILIACION BANCARIA		
					double totalEgresos = 0D;
					Collection<Object[]> egresos = SessionServiceLocator.getAsientoSessionService().findAsientosConciliacionBancaria(cuentaIf.getId(), fechaInicioSql , fechaFinSql);
					ArrayList<CuentaBancariaEgresoData> egresosDetalle = new ArrayList<CuentaBancariaEgresoData>();
					for ( Object[] egreso : egresos )				
						totalEgresos += ((BigDecimal)egreso[5]).doubleValue() ;	 
					totalSubDetalle -= totalEgresos;
				}		
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error al calcular el saldo inicial", SpiritAlert.ERROR);
			}
		}
		return totalSubDetalle;
	}
		
	private String getNumeroChequeParaTabla(String numero){
		return numero.equals("-1")?"":numero;
	}

	private Collection<FondoRotativoData> crearConciliacionFondoRotativo(int filaSeleccionada) throws GenericBusinessException {
		
		DefaultTableModel modelo = (DefaultTableModel) getTblCuentaBancaria().getModel();
		Calendar calendarFechaInicio = getCmbFechaInicio().getCalendar();
		Date fechaInicio = getCmbFechaInicio().getDate();
		java.sql.Date fechaInicioSql = Utilitarios.fromTimestampToSqlDate(Utilitarios.resetTimestampStartDate(new java.sql.Timestamp(fechaInicio.getTime())));
		Date fechaFin = getCmbFechaFin().getDate();
		java.sql.Date fechaFinSql = Utilitarios.fromTimestampToSqlDate(Utilitarios.resetTimestampEndDate(new java.sql.Timestamp(fechaFin.getTime())));
		//Map carterasMap = mapearCarterasMap(null, fechaFinSql);
		//Map carteraDetallesMap = mapearCarteraDetallesMap(null, fechaFinSql);
		Map carterasMap = null;
		Map carteraDetallesMap = null;	
		ArrayList<FondoRotativoData> datosConciliacionFondoRotativo = new ArrayList<FondoRotativoData>();
		
		int startIndex = 0;
		int endIndex = getTblCuentaBancaria().getRowCount();
		
		if (filaSeleccionada > 0) {
			startIndex = filaSeleccionada;
			endIndex = filaSeleccionada + 1;
		}
		
		for ( int filaTabla = startIndex ; filaTabla < endIndex ; filaTabla++ ) {	
			
			CuentaBancariaIf cuentaBancaria = cuentaBancariaArrayList.get(filaTabla);
			FondoRotativoData fondoRotativoData =  new FondoRotativoData();
			fondoRotativoData.setIdCuentaBancaria(cuentaBancaria.getId());
			Collection<CuentaIf> cuentasContables = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaDeCuentaBancariaByCuentaBancariaId(cuentaBancaria.getId());
			
			if (cuentasContables.size() == 0)
				throw new GenericBusinessException( "No existe Cuenta asociada a cuenta Bancaria \""+cuentaBancaria.getCuenta()+"\" !!" );
			else if (cuentasContables.size() > 1)
				throw new GenericBusinessException( "Existe mas de una Cuenta asociada a cuenta Bancaria \""+cuentaBancaria.getCuenta()+"\" !!" );
			
			CuentaIf cuentaIf = cuentasContables.iterator().next();
			String nombreBanco = (String) modelo.getValueAt(filaTabla, 0);
			fondoRotativoData.setNombreBanco(nombreBanco);
			String codigoCuentaBancaria = (String) modelo.getValueAt(filaTabla, 1);
			fondoRotativoData.setCodigoCuentaBancaria(codigoCuentaBancaria);
			
			JRBeanCollectionDataSource datosReporte = new JRBeanCollectionDataSource(new ArrayList());
			Collection<Object[]> asientosConciliacionFondoRotativo = null;
			
			if (getRbSiVersionExtendida().isSelected())
				asientosConciliacionFondoRotativo = SessionServiceLocator.getAsientoSessionService().findAsientosConciliacionBancariaExtendida(cuentaIf.getId(), fechaInicioSql, fechaFinSql);
			else
				asientosConciliacionFondoRotativo = SessionServiceLocator.getAsientoSessionService().findAsientosConciliacionBancaria(cuentaIf.getId(), fechaInicioSql, fechaFinSql);
			
			//el siguiente querry va a servir mas adelante para sacer los egresos hasta la fecha (no hay fecha inicio solo fin)
			//y poder listarlos en "PENDIENTES POR COBRAR"
			Collection<Object[]> asientosPendientesPorCobrarConciliacionFondoRotativo = SessionServiceLocator.getAsientoSessionService().findAsientosPendientesPorCobrarConciliacionFondoRotativo(cuentaIf.getId(), fechaFinSql);
			
			//////////////////////////////////////////
			//INGRESOS CONCILIACION FONDO ROTATIVO
			//////////////////////////////////////////
												
			//INGRESOS
			double totalIngresos = 0D;
			ArrayList<FondoRotativoIngresoData> ingresosConciliacionFondoRotativo = new ArrayList<FondoRotativoIngresoData>();
						
			Map<Long, CarteraIf> carterasIngresosMap = new HashMap<Long, CarteraIf>();
			Map<Long, CarteraDetalleIf> carteraIngresoIngresoDetalle = new HashMap<Long, CarteraDetalleIf>();
			Collection carterasIngresosNoCruzados = SessionServiceLocator.getCarteraSessionService().findCarteraByTipoDocumentoByEmpresaIdByFechaInicialByFechaFinalAndByCuentaBancariaId("CIN", Parametros.getIdEmpresa(), fechaInicioSql, fechaFinSql, cuentaBancaria.getId());
			Iterator carterasIngresosNoCruzadosIt = carterasIngresosNoCruzados.iterator();
			while(carterasIngresosNoCruzadosIt.hasNext()){
				Object[] carteraIngresoObject = (Object[])carterasIngresosNoCruzadosIt.next();
				CarteraIf carteraIngreso = (CarteraIf)carteraIngresoObject[0];
				CarteraDetalleIf carteraDetalleIngreso = (CarteraDetalleIf)carteraIngresoObject[1];
				carterasIngresosMap.put(carteraIngreso.getId(), carteraIngreso);					
				carteraIngresoIngresoDetalle.put(carteraIngreso.getId(), carteraDetalleIngreso);
			}
						
			Iterator carterasIngresosMapIt = carterasIngresosMap.keySet().iterator();
			while(carterasIngresosMapIt.hasNext()){
				Long carteraId = (Long)carterasIngresosMapIt.next();
				CarteraIf cartera = (CarteraIf)carterasIngresosMap.get(carteraId);
				CarteraDetalleIf carteraDetalle = (CarteraDetalleIf)carteraIngresoIngresoDetalle.get(cartera.getId());
				
				int contadorCIN = 0;
				
				//si la cartera es tipo Cliente
				if(cartera != null && cartera.getTipo().equals("C")){
					
					//CIN = COMPROBANTE DE INGRESO, 
					//cuento las veces que entre para no sumar dos veces el mismo valor
					contadorCIN = contadorCIN + 1;														
					
					FondoRotativoIngresoData ingresoConciliacionFondoRotativo = new FondoRotativoIngresoData();
					if (cartera == null || (cartera != null && cartera.getEstado().equals("N"))) {
						ingresoConciliacionFondoRotativo.setFechaComprobante(Utilitarios.getFechaCortaUppercase(cartera.getFechaEmision()));
						ingresoConciliacionFondoRotativo.setCodigoComprobante(cartera.getCodigo());
						String numeroCheque = carteraDetalle.getChequeNumero();								
						ingresoConciliacionFondoRotativo.setNumeroCheque(numeroCheque!=null?  "CHQ.# " + numeroCheque : "TRANSFERENCIA");
						String nombreCuenta = "";
						if(carteraDetalle.getChequeBancoId() != null){
							BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(carteraDetalle.getChequeBancoId());
							nombreCuenta = banco.getNombre();
						}						
						
						ingresoConciliacionFondoRotativo.setDetalleComprobante(ingresoConciliacionFondoRotativo.getCodigoComprobante() + " CON " + ingresoConciliacionFondoRotativo.getNumeroCheque() + " DEL " + nombreCuenta);
						ingresoConciliacionFondoRotativo.setValorComprobante((cartera.getValor()).doubleValue());
						
						if(contadorCIN == 1){
							totalIngresos += (cartera.getValor()).doubleValue();
						}
						
						ingresoConciliacionFondoRotativo.setDetalleFactura("N/A");
						ingresoConciliacionFondoRotativo.setValorFactura(0D);
					}
					
					//veo si esta cruzada
					Collection carteraAfecta = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(carteraDetalle.getId());
					
					//la validacion de si esta cruzada se pone porque ahora salen junto a las facturas canceladas y ahi salen los ingresos que si estan cruzados
					if (/*carteraAfecta.size() == 0 &&*/ ingresoConciliacionFondoRotativo != null && ingresoConciliacionFondoRotativo.getValorComprobante() > 0D) {
						ingresoConciliacionFondoRotativo.setImprimirCabecera(true);					
						
						ingresosConciliacionFondoRotativo.add(ingresoConciliacionFondoRotativo);
					}									
				}
			}
						
			//INGRESOS.- DE ASIENTOS MANUALES
			
			Iterator<Object[]> ingresosIterator = asientosConciliacionFondoRotativo.iterator();			
			while (ingresosIterator.hasNext()) {
				Object[] obj = ingresosIterator.next();
				FondoRotativoIngresoData ingresoConciliacionFondoRotativo = obtenerDatosIngresosConciliacionFondoRotativo(obj, carterasMap);
				if (ingresoConciliacionFondoRotativo != null && ingresoConciliacionFondoRotativo.getValorComprobante() > 0D) {
					ingresoConciliacionFondoRotativo.setImprimirCabecera(true);
					
					totalIngresos += ingresoConciliacionFondoRotativo.getValorComprobante();
										
					ingresosConciliacionFondoRotativo.add(ingresoConciliacionFondoRotativo);
				}
			}	
			
			
			/////////////////////////////////////////////////
			//FACTURAS DE PRENSA CONCILIACION FONDO ROTATIVO
			/////////////////////////////////////////////////
			
			Long idTipoProveedor = null;
			Long idTipoProducto = null;
			Long idTipoDocumento = null;
			if(tipoProveedorIf != null)
				idTipoProveedor = tipoProveedorIf.getId();
			if(tipoProductoIf != null)
				idTipoProducto = tipoProductoIf.getId();
			if(tipoDocumentoIf != null)
				idTipoDocumento = tipoDocumentoIf.getId();
			
			
			double totalFacturasCanceladas = 0D;
			ArrayList<FondoRotativoFacturaCanceladaData> facturasCanceladasConciliacionFondoRotativo = new ArrayList<FondoRotativoFacturaCanceladaData>();
			
			double totalFacturasPendientes = 0D;
			ArrayList<FondoRotativoFacturaPendienteData> facturasPendientesConciliacionFondoRotativo = new ArrayList<FondoRotativoFacturaPendienteData>();
					
			//FACTURAS CANCELADAS
			
			//FACTURAS CRUZADAS DE CLIENTE ESPECIFICADO, EN RANGO DE FECHAS ESPECIFICADO, DE TIPO PROVEEDOR SELECCIONADO
			Collection carterasAfectaDeFacturasCanceladas = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByEmpresaIdByFechaInicialByFechaFinalByClienteOficinaIdByTipoProveedorIdAndByTipoDocumentoId(Parametros.getIdEmpresa(), fechaInicioSql, fechaFinSql, clienteOficinaIf.getId(), idTipoProveedor, idTipoDocumento, cuentaBancaria.getId(), idTipoProducto);
			Iterator carterasAfectaDeFacturasCanceladasIt = carterasAfectaDeFacturasCanceladas.iterator();
						
			//hago un mapa para que no se repitan los ingresos
			Map<Long, CarteraIf> carterasIngresosFacturasCanceladasMap = new HashMap<Long, CarteraIf>();
			//hago un mapa que asocie carteraIngreso con cartera facturas
			Map<Long, ArrayList<CarteraIf>> carteraIngresoCarterasFacturasCanceladas = new HashMap<Long, ArrayList<CarteraIf>>();
			ArrayList<CarteraIf> carterasFacturasCanceladas = new ArrayList<CarteraIf>();
			//mapa que asocia cartera ingreso con cartera ingreso detalle
			Map<Long, CarteraDetalleIf> carteraIngresoIngresoDetalleFacturaCancelada = new HashMap<Long, CarteraDetalleIf>();
			//mapa que asocia cartera factura con cartera afecta
			Map<Long, CarteraAfectaIf> carteraFacturaCanceladaCarteraAfecta = new HashMap<Long, CarteraAfectaIf>();
			
			while(carterasAfectaDeFacturasCanceladasIt.hasNext()){
				CarteraAfectaIf carteraAfectaFacturaCancelada = (CarteraAfectaIf)carterasAfectaDeFacturasCanceladasIt.next();
				
				//cartera detalle del ingreso
				CarteraDetalleIf carteraDetalleIngresoFacturaCancelada = SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(carteraAfectaFacturaCancelada.getCarteradetalleId());
				//cartera del ingreso
				CarteraIf carteraIngresoFacturaCancelada = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleIngresoFacturaCancelada.getCarteraId());
				
				carterasIngresosFacturasCanceladasMap.put(carteraIngresoFacturaCancelada.getId(), carteraIngresoFacturaCancelada);
				carteraIngresoIngresoDetalleFacturaCancelada.put(carteraIngresoFacturaCancelada.getId(), carteraDetalleIngresoFacturaCancelada);
								
				//cartera detalle de la factura
				CarteraDetalleIf carteraDetalleFacturaCancelada = SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(carteraAfectaFacturaCancelada.getCarteraafectaId());
				//cartera de la factura
				CarteraIf carteraFacturaCancelada = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleFacturaCancelada.getCarteraId());
				carteraFacturaCanceladaCarteraAfecta.put(carteraFacturaCancelada.getId(), carteraAfectaFacturaCancelada);				
				
				//armo el mapa de "cartera ingreso id" en correspondencia con "lista de todas las carteras de facturas"
				if(carteraIngresoCarterasFacturasCanceladas.get(carteraIngresoFacturaCancelada.getId()) == null){
					carterasFacturasCanceladas = new ArrayList<CarteraIf>();
					carterasFacturasCanceladas.add(carteraFacturaCancelada);
					carteraIngresoCarterasFacturasCanceladas.put(carteraIngresoFacturaCancelada.getId(), carterasFacturasCanceladas);
				}else{
					carterasFacturasCanceladas = carteraIngresoCarterasFacturasCanceladas.get(carteraIngresoFacturaCancelada.getId());
					
					//reviso si la cartera de la factura no esta en la lista, si no esta la ingreso
					boolean enLista = false;
					for(CarteraIf carteraFacturaCanceladaTemp: carterasFacturasCanceladas){
						if(carteraFacturaCanceladaTemp.getId().compareTo(carteraFacturaCancelada.getId()) == 0){
							enLista = true;
						}
					}
					
					if(!enLista){
						carterasFacturasCanceladas.add(carteraFacturaCancelada);
						carteraIngresoCarterasFacturasCanceladas.put(carteraIngresoFacturaCancelada.getId(), carterasFacturasCanceladas);
					}
				}
			}
			
			//LLeno las facturas canceladas
			Iterator carterasIngresosFacturasCanceladasMapIt = carterasIngresosFacturasCanceladasMap.keySet().iterator();
			while(carterasIngresosFacturasCanceladasMapIt.hasNext()){
				Long carteraFacturaCanceladaId = (Long)carterasIngresosFacturasCanceladasMapIt.next();
				//cartera del ingreso
				CarteraIf carteraIngresoFacturaCancelada = (CarteraIf)carterasIngresosFacturasCanceladasMap.get(carteraFacturaCanceladaId);
				//cartera detalle del ingreso
				CarteraDetalleIf carteraDetalle = (CarteraDetalleIf)carteraIngresoIngresoDetalleFacturaCancelada.get(carteraIngresoFacturaCancelada.getId());
								
				//si la cartera es tipo Cliente
				if(carteraIngresoFacturaCancelada != null && carteraIngresoFacturaCancelada.getTipo().equals("C") && carteraIngresoFacturaCancelada.getEstado().equals("N")){
					
					ArrayList<CarteraIf> carterasDeFacturasCanceladas = carteraIngresoCarterasFacturasCanceladas.get(carteraIngresoFacturaCancelada.getId());
					
					//por cada factura del ingreso
					for(CarteraIf carteraFacturaCancelada : carterasDeFacturasCanceladas){
						
						//documento del detalle del ingreso para ver si no es retencion
						DocumentoIf documentoDetalleIngreso = (DocumentoIf)documentosMap.get(carteraDetalle.getDocumentoId());
						
						//si es un ingreso con cheque o tranferencia
						if(documentoDetalleIngreso.getCheque().equals("S") || documentoDetalleIngreso.getTransferenciaBancaria().equals("S")){
							//se usa esta cartera afecta para ver el valor del cruce
							//luego no se uso porque se requeria solo el valor completo de la factura
							CarteraAfectaIf cateraAfecta = carteraFacturaCanceladaCarteraAfecta.get(carteraFacturaCancelada.getId());
							
							//clase data de factura cancelada
							FondoRotativoFacturaCanceladaData facturaCanceladaConciliacionFondoRotativo = new FondoRotativoFacturaCanceladaData();
							
							//codigo del comprobante
							facturaCanceladaConciliacionFondoRotativo.setCodigoComprobante(carteraIngresoFacturaCancelada.getCodigo());
							
							//numero del cheque
							String numeroCheque = carteraDetalle.getChequeNumero();
							if(numeroCheque == null || numeroCheque.equals("")){
								numeroCheque = "TRANFERENCIA";
							}else{
								numeroCheque = "CHQ.# " + numeroCheque;
							}
							facturaCanceladaConciliacionFondoRotativo.setNumeroCheque(numeroCheque);
							
							//nombre de la cuenta
							String nombreCuenta = "";
							if(carteraDetalle.getChequeBancoId() != null){
								BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(carteraDetalle.getChequeBancoId());
								nombreCuenta = banco.getNombre();
							}
							
							//fecha del comprabante
							facturaCanceladaConciliacionFondoRotativo.setFechaComprobante(Utilitarios.getFechaCortaUppercase(carteraIngresoFacturaCancelada.getFechaEmision()));
							//detalle del comprabante
							facturaCanceladaConciliacionFondoRotativo.setDetalleComprobante(facturaCanceladaConciliacionFondoRotativo.getCodigoComprobante() + " CON " + facturaCanceladaConciliacionFondoRotativo.getNumeroCheque() + " DEL " + nombreCuenta /*+ (String)obj[2]*/);
							//valor del comprobante
							facturaCanceladaConciliacionFondoRotativo.setValorComprobante((carteraIngresoFacturaCancelada.getValor()).doubleValue());
								
							//total de facturas canceladas
							totalFacturasCanceladas += carteraFacturaCancelada.getValor().doubleValue();
																
							//factura
							FacturaIf factura = SessionServiceLocator.getFacturaSessionService().getFactura(carteraFacturaCancelada.getReferenciaId());
							//fecha de la factura
							String fechaFactura = Utilitarios.getFechaCortaUppercase(factura.getFechaFactura());
							
							//presupuesto
							String codigoPresupuesto = "N/A";
							PedidoIf pedido = SessionServiceLocator.getPedidoSessionService().getPedido(factura.getPedidoId());
							if(pedido.getReferencia() != null && !pedido.getReferencia().equals("")){
								codigoPresupuesto = pedido.getReferencia();
							}
																					
							//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
							//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'
																
							//producto, mes del presupuesto
							String producto = "N/A";
							String mesPresupuesto = "N/A";
							if(pedido.getTiporeferencia() != null && pedido.getTiporeferencia().equals("I")){
								Collection planMedioColeccion = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndByEstados(codigoPresupuesto, "N", "P", "A", "D", "F");
								Iterator planMedioColeccionIt = planMedioColeccion.iterator();
								while(planMedioColeccionIt.hasNext()){
									PlanMedioIf planMedio = (PlanMedioIf)planMedioColeccionIt.next();
									
									mesPresupuesto = Utilitarios.getMonthUpperCase(planMedio.getFechaInicio());
									
									Collection planMedioMeses = SessionServiceLocator.getPlanMedioMesSessionService().findPlanMedioMesByPlanmedioId(planMedio.getId());
									Iterator planMedioMesesIt = planMedioMeses.iterator();
									
									while(planMedioMesesIt.hasNext()){
										PlanMedioMesIf planMedioMes = (PlanMedioMesIf)planMedioMesesIt.next();
										Collection planMedioDetalles = SessionServiceLocator.getPlanMedioDetalleSessionService().findPlanMedioDetalleByPlanMedioMesId(planMedioMes.getId());
										Iterator planMedioDetallesIt = planMedioDetalles.iterator();
										while(planMedioDetallesIt.hasNext()){
											PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf)planMedioDetallesIt.next();
											ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(planMedioDetalle.getProductoClienteId());
											
											producto = productoCliente.getNombre();
										}
									}
								}										
							}else if(pedido.getTiporeferencia() != null && pedido.getTiporeferencia().equals("P")){
								Collection presupuestosColeccion = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(codigoPresupuesto);
								Iterator presupuestosColeccionIt = presupuestosColeccion.iterator();
								while(presupuestosColeccionIt.hasNext()){
									PresupuestoIf presupuesto = (PresupuestoIf)presupuestosColeccionIt.next();
									
									mesPresupuesto = Utilitarios.getMonthUpperCase(presupuesto.getFecha());
									
									Collection presupuestoProductos = SessionServiceLocator.getPresupuestoProductoSessionService().findPresupuestoProductoByPresupuestoId(presupuesto.getId());
									Iterator presupuestoProductosIt = presupuestoProductos.iterator();
									while(presupuestoProductosIt.hasNext()){
										PresupuestoProductoIf presupuestoProducto = (PresupuestoProductoIf)presupuestoProductosIt.next();
										ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(presupuestoProducto.getProductoClienteId());
										producto = productoCliente.getNombre();
									}
								}
							}
							
							//mes del presupuesto
							facturaCanceladaConciliacionFondoRotativo.setMesPresupuesto(mesPresupuesto);
														
							//sap
							String sap = "N/A";
							if(factura.getAutorizacionSap() != null){
								sap = factura.getAutorizacionSap();
							}
							
							//detalle de la factura
							facturaCanceladaConciliacionFondoRotativo.setDetalleFactura("F: " + carteraFacturaCancelada.getPreimpreso() + ", " + fechaFactura + ", P: " + codigoPresupuesto + ", SAP: " + sap + ", " + numeroCheque + ", PRODUCTO: " + producto);
							//valor de la factura
							facturaCanceladaConciliacionFondoRotativo.setValorFactura(carteraFacturaCancelada.getValor().doubleValue());
							
							//agrego facturas si existen datos y el comprobante es mayor que cero						
							if (facturaCanceladaConciliacionFondoRotativo != null && facturaCanceladaConciliacionFondoRotativo.getValorComprobante() > 0D) {
								facturaCanceladaConciliacionFondoRotativo.setImprimirCabecera(true);
								facturasCanceladasConciliacionFondoRotativo.add(facturaCanceladaConciliacionFondoRotativo);
							}
							
						}
						//si no es cheque entonces la factura solo esta cruzada con retenciones y se la considera como pendiente
						else{
							//clase data de factura pendiente
							FondoRotativoFacturaPendienteData facturaPendienteConciliacionFondoRotativo = new FondoRotativoFacturaPendienteData();
							//codigo del comprobante
							facturaPendienteConciliacionFondoRotativo.setCodigoComprobante(carteraFacturaCancelada.getCodigo());
							String numeroCheque = "";								
							facturaPendienteConciliacionFondoRotativo.setNumeroCheque(numeroCheque);
							String nombreCuenta = "";
							//detalle del comprobante
							facturaPendienteConciliacionFondoRotativo.setDetalleComprobante(facturaPendienteConciliacionFondoRotativo.getCodigoComprobante() + " CON " + facturaPendienteConciliacionFondoRotativo.getNumeroCheque() + " DEL " + nombreCuenta );
							//valor del comprobante
							facturaPendienteConciliacionFondoRotativo.setValorComprobante((carteraFacturaCancelada.getValor()).doubleValue());
								
							//total facturas pendientes
							totalFacturasPendientes += carteraFacturaCancelada.getValor().doubleValue();
																
							//factura
							FacturaIf factura = SessionServiceLocator.getFacturaSessionService().getFactura(carteraFacturaCancelada.getReferenciaId());
							//fecha de la factura
							String fechaFactura = Utilitarios.getFechaCortaUppercase(factura.getFechaFactura());
							
							//presupuesto
							String codigoPresupuesto = "N/A";
							PedidoIf pedido = SessionServiceLocator.getPedidoSessionService().getPedido(factura.getPedidoId());
							if(pedido.getReferencia() != null && !pedido.getReferencia().equals("")){
								codigoPresupuesto = pedido.getReferencia();
							}
																					
							//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
							//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'
																
							//producto, mes del presupuesto
							String producto = "N/A";
							String mesPresupuesto = "N/A";
							if(pedido.getTiporeferencia() != null && pedido.getTiporeferencia().equals("I")){
								Collection planMedioColeccion = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndByEstados(codigoPresupuesto, "N", "P", "A", "D", "F");
								Iterator planMedioColeccionIt = planMedioColeccion.iterator();
								while(planMedioColeccionIt.hasNext()){
									PlanMedioIf planMedio = (PlanMedioIf)planMedioColeccionIt.next();
									
									mesPresupuesto = Utilitarios.getMonthUpperCase(planMedio.getFechaInicio());
									
									Collection planMedioMeses = SessionServiceLocator.getPlanMedioMesSessionService().findPlanMedioMesByPlanmedioId(planMedio.getId());
									Iterator planMedioMesesIt = planMedioMeses.iterator();
									
									while(planMedioMesesIt.hasNext()){
										PlanMedioMesIf planMedioMes = (PlanMedioMesIf)planMedioMesesIt.next();
										Collection planMedioDetalles = SessionServiceLocator.getPlanMedioDetalleSessionService().findPlanMedioDetalleByPlanMedioMesId(planMedioMes.getId());
										Iterator planMedioDetallesIt = planMedioDetalles.iterator();
										while(planMedioDetallesIt.hasNext()){
											PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf)planMedioDetallesIt.next();
											ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(planMedioDetalle.getProductoClienteId());
											
											producto = productoCliente.getNombre();											
										}
									}
								}										
							}else if(pedido.getTiporeferencia() != null && pedido.getTiporeferencia().equals("P")){
								Collection presupuestosColeccion = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(codigoPresupuesto);
								Iterator presupuestosColeccionIt = presupuestosColeccion.iterator();
								while(presupuestosColeccionIt.hasNext()){
									PresupuestoIf presupuesto = (PresupuestoIf)presupuestosColeccionIt.next();
									
									mesPresupuesto = Utilitarios.getMonthUpperCase(presupuesto.getFecha());
									
									Collection presupuestoProductos = SessionServiceLocator.getPresupuestoProductoSessionService().findPresupuestoProductoByPresupuestoId(presupuesto.getId());
									Iterator presupuestoProductosIt = presupuestoProductos.iterator();
									while(presupuestoProductosIt.hasNext()){
										PresupuestoProductoIf presupuestoProducto = (PresupuestoProductoIf)presupuestoProductosIt.next();
										ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(presupuestoProducto.getProductoClienteId());
										producto = productoCliente.getNombre();
									}
								}
							}
							
							//mes del presupuesto
							facturaPendienteConciliacionFondoRotativo.setMesPresupuesto(mesPresupuesto);
														
							//sap
							String sap = "N/A";
							if(factura.getAutorizacionSap() != null){
								sap = factura.getAutorizacionSap();
							}
							
							//detalle de la factura
							facturaPendienteConciliacionFondoRotativo.setDetalleFactura("F: " + carteraFacturaCancelada.getPreimpreso() + ", " + fechaFactura + ", P: " + codigoPresupuesto + ", SAP: " + sap + ", " + numeroCheque + ", PRODUCTO: " + producto);
							//valor de la factura
							facturaPendienteConciliacionFondoRotativo.setValorFactura(carteraFacturaCancelada.getValor().doubleValue());
							
							//agrego facturas si existen datos y el comprobante es mayor que cero		
							if (facturaPendienteConciliacionFondoRotativo != null && facturaPendienteConciliacionFondoRotativo.getValorComprobante() > 0D) {
								facturasPendientesConciliacionFondoRotativo.add(facturaPendienteConciliacionFondoRotativo);
							}
						}
					}											
				}
			}
			
			//FACTURAS PENDIENTES
		
			//FACTURAS CLIENTE ESPECIFICADO, EN RANGO DE FECHAS ESPECIFICADO, DE TIPO PROVEEDOR SELECCIONADO
			Collection carterasDeFacturasPendientes = SessionServiceLocator.getCarteraSessionService().findCarteraByEmpresaIdByFechaInicialByFechaFinalByClienteOficinaIdByTipoProveedorIdAndByTipoDocumentoId(Parametros.getIdEmpresa(), fechaInicioSql, fechaFinSql, clienteOficinaIf.getId(), idTipoProveedor, idTipoDocumento, idTipoProducto);
			Iterator carterasDeFacturasPendientesIt = carterasDeFacturasPendientes.iterator();
			
			while(carterasDeFacturasPendientesIt.hasNext()){
				Object[] carteraFacturaPendienteObj = (Object[])carterasDeFacturasPendientesIt.next();
				//cartera del factura
				CarteraIf carteraFacturaPendiente = (CarteraIf)carteraFacturaPendienteObj[0];
				//cartera detalle de la factura
				CarteraDetalleIf carteraDetalleFacturaPendiente = (CarteraDetalleIf)carteraFacturaPendienteObj[1];
				
				//Busco si la factura ha sido cruzada
				Collection carterasAfecta = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteraDetalleAfectadaIdSiLaAfectacionNoEsUnaRetencion(carteraDetalleFacturaPendiente.getId());
				
				//reviso si el cruce se debe solo a la retencion
				/*Iterator carterasAfectaIt = carterasAfecta.iterator();
				while(carterasAfectaIt.hasNext()){
					CarteraAfectaIf carteraAfecta = (CarteraAfectaIf)carterasAfectaIt.next();
				}*/
				
				//si no esta cruzada, es tipo cliente y el estado es normal, agrego la factura como pendiente
				if(carterasAfecta.size() == 0 && carteraFacturaPendiente.getTipo().equals("C") && carteraFacturaPendiente.getEstado().equals("N")){
										
					//clase data de factura pendiente
					FondoRotativoFacturaPendienteData facturaPendienteConciliacionFondoRotativo = new FondoRotativoFacturaPendienteData();
					
					//codigo del comprobante
					facturaPendienteConciliacionFondoRotativo.setCodigoComprobante(carteraFacturaPendiente.getCodigo());
					String numeroCheque = "";								
					facturaPendienteConciliacionFondoRotativo.setNumeroCheque(numeroCheque);
					String nombreCuenta = "";
					//detalle del comprobante
					facturaPendienteConciliacionFondoRotativo.setDetalleComprobante(facturaPendienteConciliacionFondoRotativo.getCodigoComprobante() + " CON " + facturaPendienteConciliacionFondoRotativo.getNumeroCheque() + " DEL " + nombreCuenta );
					//valor del comprobante
					facturaPendienteConciliacionFondoRotativo.setValorComprobante((carteraFacturaPendiente.getValor()).doubleValue());
						
					//total de facturas pendientes
					totalFacturasPendientes += carteraFacturaPendiente.getValor().doubleValue();
														
					//factura
					FacturaIf factura = SessionServiceLocator.getFacturaSessionService().getFactura(carteraFacturaPendiente.getReferenciaId());
															
					//fecha de la factura
					String fechaFactura = Utilitarios.getFechaCortaUppercase(factura.getFechaFactura());
					
					//presupuesto
					String codigoPresupuesto = "N/A";
					PedidoIf pedido = SessionServiceLocator.getPedidoSessionService().getPedido(factura.getPedidoId());
					if(pedido.getReferencia() != null && !pedido.getReferencia().equals("")){
						codigoPresupuesto = pedido.getReferencia();
					}
																			
					//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
					//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'
														
					//producto, mes del presupuesto
					String producto = "N/A";
					String mesPresupuesto = "N/A";
					if(pedido.getTiporeferencia() != null && pedido.getTiporeferencia().equals("I")){
						Collection planMedioColeccion = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndByEstados(codigoPresupuesto, "N", "P", "A", "D", "F");
						Iterator planMedioColeccionIt = planMedioColeccion.iterator();
						while(planMedioColeccionIt.hasNext()){
							PlanMedioIf planMedio = (PlanMedioIf)planMedioColeccionIt.next();
							
							mesPresupuesto = Utilitarios.getMonthUpperCase(planMedio.getFechaInicio());
							
							Collection planMedioMeses = SessionServiceLocator.getPlanMedioMesSessionService().findPlanMedioMesByPlanmedioId(planMedio.getId());
							Iterator planMedioMesesIt = planMedioMeses.iterator();
							
							while(planMedioMesesIt.hasNext()){
								PlanMedioMesIf planMedioMes = (PlanMedioMesIf)planMedioMesesIt.next();
								Collection planMedioDetalles = SessionServiceLocator.getPlanMedioDetalleSessionService().findPlanMedioDetalleByPlanMedioMesId(planMedioMes.getId());
								Iterator planMedioDetallesIt = planMedioDetalles.iterator();
								while(planMedioDetallesIt.hasNext()){
									PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf)planMedioDetallesIt.next();
									ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(planMedioDetalle.getProductoClienteId());
									
									producto = productoCliente.getNombre();											
								}
							}
						}										
					}else if(pedido.getTiporeferencia() != null && pedido.getTiporeferencia().equals("P")){
						Collection presupuestosColeccion = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(codigoPresupuesto);
						Iterator presupuestosColeccionIt = presupuestosColeccion.iterator();
						while(presupuestosColeccionIt.hasNext()){
							PresupuestoIf presupuesto = (PresupuestoIf)presupuestosColeccionIt.next();
							
							mesPresupuesto = Utilitarios.getMonthUpperCase(presupuesto.getFecha());
							
							Collection presupuestoProductos = SessionServiceLocator.getPresupuestoProductoSessionService().findPresupuestoProductoByPresupuestoId(presupuesto.getId());
							Iterator presupuestoProductosIt = presupuestoProductos.iterator();
							while(presupuestoProductosIt.hasNext()){
								PresupuestoProductoIf presupuestoProducto = (PresupuestoProductoIf)presupuestoProductosIt.next();
								ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(presupuestoProducto.getProductoClienteId());
								producto = productoCliente.getNombre();
							}
						}
					}
					
					//mes del presupuesto
					facturaPendienteConciliacionFondoRotativo.setMesPresupuesto(mesPresupuesto);
												
					//sap
					String sap = "N/A";
					if(factura.getAutorizacionSap() != null){
						sap = factura.getAutorizacionSap();
					}
					
					//detalle de la factura
					facturaPendienteConciliacionFondoRotativo.setDetalleFactura("F: " + carteraFacturaPendiente.getPreimpreso() + ", " + fechaFactura + ", P: " + codigoPresupuesto + ", SAP: " + sap + ", " + numeroCheque + ", PRODUCTO: " + producto);
					//valor de la factura
					facturaPendienteConciliacionFondoRotativo.setValorFactura(carteraFacturaPendiente.getValor().doubleValue());
					
					//agrego facturas si existen datos y el comprobante es mayor que cero		
					if (facturaPendienteConciliacionFondoRotativo != null && facturaPendienteConciliacionFondoRotativo.getValorComprobante() > 0D) {
						facturasPendientesConciliacionFondoRotativo.add(facturaPendienteConciliacionFondoRotativo);
					}
					
				}
				
				//si esta cruzada pero no en el rango de fechas especificado entonces tambien va como pendiente
				else if(carterasAfecta.size() > 0){
					CarteraAfectaIf carteraAfecta = (CarteraAfectaIf)carterasAfecta.iterator().next();
					
					//verifico que este fuera del rango de fechas
					if(carteraFacturaPendiente.getTipo().equals("C") && carteraFacturaPendiente.getEstado().equals("N")
							&& carteraAfecta.getFechaAplicacion().getTime() > fechaFinSql.getTime()){
						
						//clase data de factura pendiente
						FondoRotativoFacturaPendienteData facturaPendienteConciliacionFondoRotativo = new FondoRotativoFacturaPendienteData();
						
						//codigo del comprobante
						facturaPendienteConciliacionFondoRotativo.setCodigoComprobante(carteraFacturaPendiente.getCodigo());
						String numeroCheque = "";								
						facturaPendienteConciliacionFondoRotativo.setNumeroCheque(numeroCheque);
						String nombreCuenta = "";
						//detalle del comprobante
						facturaPendienteConciliacionFondoRotativo.setDetalleComprobante(facturaPendienteConciliacionFondoRotativo.getCodigoComprobante() + " CON " + facturaPendienteConciliacionFondoRotativo.getNumeroCheque() + " DEL " + nombreCuenta );
						//valor del comprobante
						facturaPendienteConciliacionFondoRotativo.setValorComprobante((carteraFacturaPendiente.getValor()).doubleValue());
							
						//total facturas pedientes
						totalFacturasPendientes += carteraFacturaPendiente.getValor().doubleValue();
															
						//factura
						FacturaIf factura = SessionServiceLocator.getFacturaSessionService().getFactura(carteraFacturaPendiente.getReferenciaId());
						
						//fecha de la factura
						String fechaFactura = Utilitarios.getFechaCortaUppercase(factura.getFechaFactura());
						
						//presupuesto
						String codigoPresupuesto = "N/A";
						PedidoIf pedido = SessionServiceLocator.getPedidoSessionService().getPedido(factura.getPedidoId());
						if(pedido.getReferencia() != null && !pedido.getReferencia().equals("")){
							codigoPresupuesto = pedido.getReferencia();
						}
																				
						//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
						//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'
															
						//producto, mes del presupuesto
						String producto = "N/A";
						String mesPresupuesto = "N/A";
						if(pedido.getTiporeferencia() != null && pedido.getTiporeferencia().equals("I")){
							Collection planMedioColeccion = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndByEstados(codigoPresupuesto, "N", "P", "A", "D", "F");
							Iterator planMedioColeccionIt = planMedioColeccion.iterator();
							while(planMedioColeccionIt.hasNext()){
								PlanMedioIf planMedio = (PlanMedioIf)planMedioColeccionIt.next();
								
								mesPresupuesto = Utilitarios.getMonthUpperCase(planMedio.getFechaInicio());
								
								Collection planMedioMeses = SessionServiceLocator.getPlanMedioMesSessionService().findPlanMedioMesByPlanmedioId(planMedio.getId());
								Iterator planMedioMesesIt = planMedioMeses.iterator();
								
								while(planMedioMesesIt.hasNext()){
									PlanMedioMesIf planMedioMes = (PlanMedioMesIf)planMedioMesesIt.next();
									Collection planMedioDetalles = SessionServiceLocator.getPlanMedioDetalleSessionService().findPlanMedioDetalleByPlanMedioMesId(planMedioMes.getId());
									Iterator planMedioDetallesIt = planMedioDetalles.iterator();
									while(planMedioDetallesIt.hasNext()){
										PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf)planMedioDetallesIt.next();
										ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(planMedioDetalle.getProductoClienteId());
										
										producto = productoCliente.getNombre();											
									}
								}
							}										
						}else if(pedido.getTiporeferencia() != null && pedido.getTiporeferencia().equals("P")){
							Collection presupuestosColeccion = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(codigoPresupuesto);
							Iterator presupuestosColeccionIt = presupuestosColeccion.iterator();
							while(presupuestosColeccionIt.hasNext()){
								PresupuestoIf presupuesto = (PresupuestoIf)presupuestosColeccionIt.next();
								
								mesPresupuesto = Utilitarios.getMonthUpperCase(presupuesto.getFecha());
								
								Collection presupuestoProductos = SessionServiceLocator.getPresupuestoProductoSessionService().findPresupuestoProductoByPresupuestoId(presupuesto.getId());
								Iterator presupuestoProductosIt = presupuestoProductos.iterator();
								while(presupuestoProductosIt.hasNext()){
									PresupuestoProductoIf presupuestoProducto = (PresupuestoProductoIf)presupuestoProductosIt.next();
									ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(presupuestoProducto.getProductoClienteId());
									producto = productoCliente.getNombre();
								}
							}
						}
						
						//mes del presupuesto
						facturaPendienteConciliacionFondoRotativo.setMesPresupuesto(mesPresupuesto);
													
						//sap
						String sap = "N/A";
						if(factura.getAutorizacionSap() != null){
							sap = factura.getAutorizacionSap();
						}
						
						//detalle de la factura
						facturaPendienteConciliacionFondoRotativo.setDetalleFactura("F: " + carteraFacturaPendiente.getPreimpreso() + ", " + fechaFactura + ", P: " + codigoPresupuesto + ", SAP: " + sap + ", " + numeroCheque + ", PRODUCTO: " + producto);
						//valor de la factura
						facturaPendienteConciliacionFondoRotativo.setValorFactura(carteraFacturaPendiente.getValor().doubleValue());
						
						//agrego facturas si existen datos y el comprobante es mayor que cero		
						if (facturaPendienteConciliacionFondoRotativo != null && facturaPendienteConciliacionFondoRotativo.getValorComprobante() > 0D) {
							facturasPendientesConciliacionFondoRotativo.add(facturaPendienteConciliacionFondoRotativo);
						}
					}
				}
			}
			
						
			ingresosConciliacionFondoRotativo = ordenarIngresosConciliacionFondoRotativo(ingresosConciliacionFondoRotativo);
			datosReporte = new JRBeanCollectionDataSource(ingresosConciliacionFondoRotativo);
			fondoRotativoData.setIngresosDetalles(datosReporte);
			fondoRotativoData.setIngresosConciliacionFondoRotativo(ingresosConciliacionFondoRotativo);
			fondoRotativoData.setTotalIngresos(Utilitarios.redondeoValor(totalIngresos));
			
			Collections.sort((ArrayList)facturasCanceladasConciliacionFondoRotativo, ordenadorFacturasCanceladasConciliacionFondoRotativo);
			Collections.sort((ArrayList)facturasCanceladasConciliacionFondoRotativo, ordenadorFacturasCanceladasConciliacionFondoRotativoIngreso);
			datosReporte = new JRBeanCollectionDataSource(facturasCanceladasConciliacionFondoRotativo);
			fondoRotativoData.setFacturasCanceladasDetalles(datosReporte);
			fondoRotativoData.setFacturasCanceladasConciliacionFondoRotativo(facturasCanceladasConciliacionFondoRotativo);
			fondoRotativoData.setTotalFacturasCanceladas(Utilitarios.redondeoValor(totalFacturasCanceladas));
			
			Collections.sort((ArrayList)facturasPendientesConciliacionFondoRotativo, ordenadorFacturasPendientesConciliacionFondoRotativo);
			Collections.sort((ArrayList)facturasPendientesConciliacionFondoRotativo, ordenadorFacturasPendientesConciliacionFondoRotativoIngreso);
			datosReporte = new JRBeanCollectionDataSource(facturasPendientesConciliacionFondoRotativo);
			fondoRotativoData.setFacturasPendientesDetalles(datosReporte);
			fondoRotativoData.setFacturasPendientesConciliacionFondoRotativo(facturasPendientesConciliacionFondoRotativo);
			fondoRotativoData.setTotalFacturasPendientes(Utilitarios.redondeoValor(totalFacturasPendientes));	
			
			//limpio los mapas que se van a llenar en los while de egresos y cheques emitidos
			mapaPlanMedioPorFacturar.clear();
			mapaPlanMedioPorFacturarValores.clear();
			mapaPresupuestoPorFacturar.clear();
			mapaPresupuestoPorFacturarValores.clear();
			mapaCarteraEgresoPorFacturar.clear();
			mapaCarteraEgresoPorFacturarValores.clear();
			
			
			// EGRESOS CONCILIACION FONDO ROTATIVO
			ArrayList<FondoRotativoEgresoData> egresosConciliacionFondoRotativo = new ArrayList<FondoRotativoEgresoData>();
			double totalEgresos = 0D;
			Iterator<Object[]> egresosIterator = asientosConciliacionFondoRotativo.iterator();
			
			while (egresosIterator.hasNext()) {
				Object[] obj = egresosIterator.next();
				Vector<FondoRotativoEgresoData> egresoConciliacionFondoRotativoVector = obtenerDatosEgresosConciliacionFondoRotativo(obj, cuentaBancaria, carterasMap, carteraDetallesMap, false);
				for (int i=0; i<egresoConciliacionFondoRotativoVector.size(); i++) {
					FondoRotativoEgresoData egresoConciliacionFondoRotativo = egresoConciliacionFondoRotativoVector.get(i);
					if (egresoConciliacionFondoRotativo.getValor() > 0D && egresoConciliacionFondoRotativo.getCheque() != null) {
						egresosConciliacionFondoRotativo.add(egresoConciliacionFondoRotativo);
						totalEgresos += egresoConciliacionFondoRotativo.getValor();
					}
				}
			}
			egresosConciliacionFondoRotativo = ordenarEgresosConciliacionFondoRotativo(egresosConciliacionFondoRotativo);
			datosReporte = new JRBeanCollectionDataSource(egresosConciliacionFondoRotativo);
			fondoRotativoData.setEgresosDetalles(datosReporte);
			fondoRotativoData.setEgresosConciliacionFondoRotativo(egresosConciliacionFondoRotativo);
			fondoRotativoData.setTotalEgresos(Utilitarios.redondeoValor(totalEgresos));
			
			
			
			
			
			// DEBITOS BANCARIOS FONDO ROTATIVO
			
			//son asientos manuales con cuenta contable 71020100005 - GASTOS BANCARIOS, id 940
			CuentaIf cuentaGastosBancarios = SessionServiceLocator.getCuentaSessionService().getCuenta(940L);
			Long cuentaGastosBancariosId = null;
			if(cuentaGastosBancarios != null){
				cuentaGastosBancariosId = cuentaGastosBancarios.getId();
			}
			
			//limpio el mapa de asientos de debitos bancarios
			mapaDebitosBancarios.clear();
			
			ArrayList<FondoRotativoEgresoData> debitosBancariosConciliacionFondoRotativo = new ArrayList<FondoRotativoEgresoData>();
			double totalDebitosBancarios = 0D;
			Collection<Object[]> asientosDebitosBancariosCFR = (Collection<Object[]>)DeepCopy.copy(asientosPendientesPorCobrarConciliacionFondoRotativo);
			Iterator<Object[]> debitosBancariosIterator = asientosDebitosBancariosCFR.iterator();
			
			//primero saco solo los asientos porque se repiten entonces uso un mapa para filtrar
			while (debitosBancariosIterator.hasNext()) {
				Object[] obj = debitosBancariosIterator.next();
				
				//veo si tipo documento (6) y transaccion id (7) son nulos para constatar que es asiento manual
				if(obj[6] == null && obj[7] == null){
					
					AsientoIf asientoIf = null;
					String glosa = "";
					
					//busco el asiento por el número
					String numeroAsiento = (String)obj[0];
					Collection asientoManualColeccion = SessionServiceLocator.getAsientoSessionService().findAsientoByNumero(numeroAsiento);
					Iterator asientoManualColeccionIt = asientoManualColeccion.iterator();
					while(asientoManualColeccionIt.hasNext()){
						asientoIf = (AsientoIf)asientoManualColeccionIt.next();			
						mapaDebitosBancarios.put(asientoIf.getId(), asientoIf);
					}
				}
			}
			
			//paso los datos del mapa a un vector para poder ordenarlo
			List<AsientoIf> asientosDebitosBancarios = new ArrayList<AsientoIf>();
			
			Iterator mapaDebitosBancariosIt = mapaDebitosBancarios.keySet().iterator();
			while(mapaDebitosBancariosIt.hasNext()){
				Long asientoId = (Long)mapaDebitosBancariosIt.next();
				AsientoIf asientoIf = mapaDebitosBancarios.get(asientoId);
				asientosDebitosBancarios.add(asientoIf);
			}
			
			//ordeno la lista
			Collections.sort((ArrayList)asientosDebitosBancarios, ordenadorAsientoPorFecha);
			
			//ahora si lleno la informacion de debitos
			for(AsientoIf asientoIf : asientosDebitosBancarios){
				Vector<FondoRotativoEgresoData> debitosBancariosConciliacionFondoRotativoVector = obtenerDatosDebitosBancariosConciliacionFondoRotativo(asientoIf, cuentaGastosBancariosId);
				for (int i=0; i<debitosBancariosConciliacionFondoRotativoVector.size(); i++) {
					FondoRotativoEgresoData debitoBancariosConciliacionFondoRotativo = debitosBancariosConciliacionFondoRotativoVector.get(i);
					if (debitoBancariosConciliacionFondoRotativo.getValor() > 0D) {
						debitosBancariosConciliacionFondoRotativo.add(debitoBancariosConciliacionFondoRotativo);
						totalDebitosBancarios += debitoBancariosConciliacionFondoRotativo.getValor();
					}
				}
			}	
			
			debitosBancariosConciliacionFondoRotativo = ordenarEgresosConciliacionFondoRotativo(debitosBancariosConciliacionFondoRotativo);
			datosReporte = new JRBeanCollectionDataSource(debitosBancariosConciliacionFondoRotativo);
			fondoRotativoData.setDebitosBancariosDetalles(datosReporte);
			fondoRotativoData.setDebitosBancariosConciliacionFondoRotativo(debitosBancariosConciliacionFondoRotativo);
			fondoRotativoData.setTotalDebitosBancarios(Utilitarios.redondeoValor(totalDebitosBancarios));
			
			
			
			
			
			
			
			
			// PENDIENTES POR COBRAR FONDO ROTATIVO
			/*ArrayList<FondoRotativoEgresoData> pendientesPorCobrarConciliacionFondoRotativo = new ArrayList<FondoRotativoEgresoData>();
			double totalPendientesPorCobrar = 0D;
			Iterator<Object[]> pendientesPorCobrarIterator = asientosPendientesPorCobrarConciliacionFondoRotativo.iterator();
			
			while (pendientesPorCobrarIterator.hasNext()) {
				Object[] obj = pendientesPorCobrarIterator.next();
				Vector<FondoRotativoEgresoData> pendientesPorCobrarConciliacionFondoRotativoVector = obtenerDatosPendientesPorCobrarConciliacionFondoRotativo(obj, cuentaBancaria, carterasMap, carteraDetallesMap, false);
				for (int i=0; i<pendientesPorCobrarConciliacionFondoRotativoVector.size(); i++) {
					FondoRotativoEgresoData pendientePorCobrarConciliacionFondoRotativo = pendientesPorCobrarConciliacionFondoRotativoVector.get(i);
					if (pendientePorCobrarConciliacionFondoRotativo.getValor() > 0D 
							&& pendientePorCobrarConciliacionFondoRotativo.getCheque() != null) {
						pendientesPorCobrarConciliacionFondoRotativo.add(pendientePorCobrarConciliacionFondoRotativo);
						totalPendientesPorCobrar += pendientePorCobrarConciliacionFondoRotativo.getValor();
					}
				}
			}
			pendientesPorCobrarConciliacionFondoRotativo = ordenarEgresosConciliacionFondoRotativo(pendientesPorCobrarConciliacionFondoRotativo);
			datosReporte = new JRBeanCollectionDataSource(pendientesPorCobrarConciliacionFondoRotativo);
			fondoRotativoData.setPendientesPorCobrarDetalles(datosReporte);
			fondoRotativoData.setPendientesPorCobrarConciliacionFondoRotativo(pendientesPorCobrarConciliacionFondoRotativo);
			fondoRotativoData.setTotalPendientesPorCobrar(Utilitarios.redondeoValor(totalPendientesPorCobrar));*/
			
			//SALDO INICIAL CUENTA BANCOS
			
			Calendar fechaSaldoInicial =  new GregorianCalendar();
			fechaSaldoInicial.setTime(calendarFechaInicio.getTime());
			fechaSaldoInicial.add(Calendar.MONTH,-1);
			
			Calendar fechaFinSaldoInicial =  new GregorianCalendar();
			fechaFinSaldoInicial.setTime(fechaSaldoInicial.getTime());
			fechaFinSaldoInicial.set(Calendar.DATE,fechaFinSaldoInicial.getActualMaximum(Calendar.DAY_OF_MONTH));
			
			Collection<PeriodoIf> periodos = SessionServiceLocator.getPeriodoSessionService().findPeriodoByRangoFechas(Parametros.getIdEmpresa(), new java.sql.Date (fechaSaldoInicial.getTime().getTime()), new java.sql.Date (fechaFinSaldoInicial.getTime().getTime()) );
			
			if ( periodos.size() > 1 )
				throw new GenericBusinessException("En rango de fecha abarca mas de un periodo !! ");
			else if (periodos.size() == 1) {
				PeriodoIf periodoIf = periodos.iterator().next();
				NumberFormat format = new DecimalFormat("00");
				Map<String, Object> mapaSaldoCuenta = new HashMap<String, Object>();
				mapaSaldoCuenta.put("anio",String.valueOf( fechaSaldoInicial.get(Calendar.YEAR) ) );
				String mes =  format.format( fechaSaldoInicial.get(Calendar.MONTH) + 1);
				mapaSaldoCuenta.put("mes",mes);
				mapaSaldoCuenta.put("cuentaId",cuentaIf.getId());
				mapaSaldoCuenta.put("periodoId",periodoIf.getId());
				Collection<SaldoCuentaIf> saldosCuentas = SessionServiceLocator.getSaldoCuentaSessionService().findSaldoCuentaByQuery(mapaSaldoCuenta);
				double totalSaldoCuenta = 0.0;
				SaldoCuentaIf saldoCuentaIf = null;
				
				if ( saldosCuentas.size() > 1 )
					throw new GenericBusinessException("Existe mas de un saldo Cuenta en la fecha Inicio para cuenta \""+cuentaIf.getCodigo()+"\" ");
				else if ( saldosCuentas.size()== 1 )
					saldoCuentaIf = saldosCuentas.iterator().next();
				
				if ( saldoCuentaIf != null )
					totalSaldoCuenta = Utilitarios.redondeoValor(saldoCuentaIf.getValor().doubleValue());
				
				String dia=String.valueOf( fechaSaldoInicial.get(Calendar.DAY_OF_MONTH));
				double valorSumar = valorSumaInicial(mes,String.valueOf(fechaSaldoInicial.get(Calendar.YEAR) ), dia, cuentaIf, cuentaBancaria);
				//String valAnt=new Double(totalSaldoCuenta).toString(); 
				//String valSum=new Double(valorSumar).toString();			
				totalSaldoCuenta=totalSaldoCuenta+valorSumar;	
				totalSaldoCuenta = Utilitarios.redondeoValor(totalSaldoCuenta);		
				fondoRotativoData.setSaldoInicial(totalSaldoCuenta);
			
			} else if (periodos.size() == 0) {
				fondoRotativoData.setSaldoInicial(0D);
			}

			ArrayList<FondoRotativoEgresoData> chequesEmitidosConciliacionFondoRotativo = new ArrayList<FondoRotativoEgresoData>();
			Collection<Object[]> chequesEmitidos = SessionServiceLocator.getChequeEmitidoSessionService().findChequesEmitidosConciliacionBancaria(cuentaBancaria.getId(), fechaInicioSql, fechaFinSql);
			double totalChequesEmitidos = 0D;
			Iterator<Object[]> chequesEmitidosIterator = chequesEmitidos.iterator();
			while (chequesEmitidosIterator.hasNext()) {
				Object[] obj = chequesEmitidosIterator.next();
				FondoRotativoEgresoData chequeEmitidoConciliacionFondoRotativo = obtenerDatosChequesEmitidosConciliacionFondoRotativo(obj, carterasMap);
				
				if (chequeEmitidoConciliacionFondoRotativo.getValor() > 0D) {
					chequesEmitidosConciliacionFondoRotativo.add(chequeEmitidoConciliacionFondoRotativo);
					totalChequesEmitidos += chequeEmitidoConciliacionFondoRotativo.getValor();
				}
			}

			chequesEmitidosConciliacionFondoRotativo = ordenarChequesEmitidosConciliacionFondoRotativo(chequesEmitidosConciliacionFondoRotativo);
			datosReporte = new JRBeanCollectionDataSource(chequesEmitidosConciliacionFondoRotativo);
			fondoRotativoData.setChequesEmitidosDetalles(datosReporte);
			fondoRotativoData.setChequesEmitidosConciliacionFondoRotativo(chequesEmitidosConciliacionFondoRotativo);
			fondoRotativoData.setTotalChequesEmitidos(totalChequesEmitidos);
					
						
			//PRESUPUESTOS POR FACTURAR
			ArrayList<FondoRotativoEgresoData> presupuestosPendientesConciliacionFondoRotativo = new ArrayList<FondoRotativoEgresoData>();
			double totalPresupuestosPendientes = 0D;
			
			//plan de medios
			Iterator mapaPlanMedioPorFacturarIt = mapaPlanMedioPorFacturar.keySet().iterator();
			while(mapaPlanMedioPorFacturarIt.hasNext()){
				Long planMedioId = (Long)mapaPlanMedioPorFacturarIt.next();
				PlanMedioIf planMedio = mapaPlanMedioPorFacturar.get(planMedioId);
				
				FondoRotativoEgresoData presupuestosPendientes = new FondoRotativoEgresoData();
				
				//mes
				String mesPresupuesto = Utilitarios.getMonthUpperCase(planMedio.getFechaInicio());
				presupuestosPendientes.setFecha(mesPresupuesto);
				
				//detalle
				String producto = "N/A";
				Collection planMedioMeses = SessionServiceLocator.getPlanMedioMesSessionService().findPlanMedioMesByPlanmedioId(planMedio.getId());
				Iterator planMedioMesesIt = planMedioMeses.iterator();				
				while(planMedioMesesIt.hasNext()){
					PlanMedioMesIf planMedioMes = (PlanMedioMesIf)planMedioMesesIt.next();
					Collection planMedioDetalles = SessionServiceLocator.getPlanMedioDetalleSessionService().findPlanMedioDetalleByPlanMedioMesId(planMedioMes.getId());
					Iterator planMedioDetallesIt = planMedioDetalles.iterator();
					while(planMedioDetallesIt.hasNext()){
						PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf)planMedioDetallesIt.next();
						ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(planMedioDetalle.getProductoClienteId());						
						producto = productoCliente.getNombre();											
					}
				}
				presupuestosPendientes.setDetalle("PRESUPUESTO: " + planMedio.getCodigo() + ", PRODUCTO: " + producto);
				
				//valor
				presupuestosPendientes.setValor(mapaPlanMedioPorFacturarValores.get(planMedioId));
				totalPresupuestosPendientes = totalPresupuestosPendientes + mapaPlanMedioPorFacturarValores.get(planMedioId);
				
				presupuestosPendientesConciliacionFondoRotativo.add(presupuestosPendientes);
			}
			//presupuesto
			Iterator mapaPresupuestoPorFacturarIt = mapaPresupuestoPorFacturar.keySet().iterator();
			while(mapaPresupuestoPorFacturarIt.hasNext()){
				Long presupuestoId = (Long)mapaPresupuestoPorFacturarIt.next();
				PresupuestoIf presupuesto = mapaPresupuestoPorFacturar.get(presupuestoId);
				
				FondoRotativoEgresoData presupuestosPendientes = new FondoRotativoEgresoData();
				
				//mes
				String mesPresupuesto = Utilitarios.getMonthUpperCase(presupuesto.getFecha());
				presupuestosPendientes.setFecha(mesPresupuesto);
				
				//detalle
				String producto = "N/A";
				Collection presupuestoProductos = SessionServiceLocator.getPresupuestoProductoSessionService().findPresupuestoProductoByPresupuestoId(presupuesto.getId());
				Iterator presupuestoProductosIt = presupuestoProductos.iterator();
				while(presupuestoProductosIt.hasNext()){
					PresupuestoProductoIf presupuestoProducto = (PresupuestoProductoIf)presupuestoProductosIt.next();
					ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(presupuestoProducto.getProductoClienteId());
					producto = productoCliente.getNombre();
				}
				presupuestosPendientes.setDetalle("PRESUPUESTO: " + presupuesto.getCodigo() + ", PRODUCTO: " + producto);
				
				//valor
				presupuestosPendientes.setValor(mapaPresupuestoPorFacturarValores.get(presupuestoId));
				totalPresupuestosPendientes = totalPresupuestosPendientes + mapaPresupuestoPorFacturarValores.get(presupuestoId);
				
				presupuestosPendientesConciliacionFondoRotativo.add(presupuestosPendientes);
			}		
			//cartera egreso
			Iterator mapaCarteraEgresoPorFacturarIt = mapaCarteraEgresoPorFacturar.keySet().iterator();
			while(mapaCarteraEgresoPorFacturarIt.hasNext()){
				Long carteraId = (Long)mapaCarteraEgresoPorFacturarIt.next();
				CarteraIf cartera = mapaCarteraEgresoPorFacturar.get(carteraId);
				
				FondoRotativoEgresoData presupuestosPendientes = new FondoRotativoEgresoData();
				
				//mes
				String mesPresupuesto = Utilitarios.getMonthUpperCase(cartera.getFechaEmision());
				presupuestosPendientes.setFecha(mesPresupuesto);
				
				//detalle
				presupuestosPendientes.setDetalle("CARTERA: " + cartera.getCodigo());
				
				//valor
				presupuestosPendientes.setValor(mapaCarteraEgresoPorFacturarValores.get(cartera.getId()));
				totalPresupuestosPendientes = totalPresupuestosPendientes + mapaCarteraEgresoPorFacturarValores.get(cartera.getId());
				
				presupuestosPendientesConciliacionFondoRotativo.add(presupuestosPendientes);
			}
			
			//Collections.sort((ArrayList)presupuestosPendientesConciliacionFondoRotativo, ordenadorPresupuestosPendientesPorMes);
			Collections.sort((ArrayList)presupuestosPendientesConciliacionFondoRotativo, ordenadorPresupuestosPendientesPorDetalle);
			datosReporte = new JRBeanCollectionDataSource(presupuestosPendientesConciliacionFondoRotativo);
			fondoRotativoData.setPresupuestosPendientesDetalles(datosReporte);
			fondoRotativoData.setPresupuestosPendientesConciliacionFondoRotativo(presupuestosPendientesConciliacionFondoRotativo);
			fondoRotativoData.setTotalPresupuestosPendientes(totalPresupuestosPendientes);
						
			datosReporte = new JRBeanCollectionDataSource(new ArrayList());
			
			double saldoInicialBancario = calcularSaldoInicialBancario(-1);
			fondoRotativoData.setSaldoInicialBancario(saldoInicialBancario);
			
			datosConciliacionFondoRotativo.add(fondoRotativoData);
		}
		
		return datosConciliacionFondoRotativo;
	}
	
	Comparator<AsientoIf> ordenadorAsientoPorFecha = new Comparator<AsientoIf>(){
		public int compare(AsientoIf o1, AsientoIf o2) {
			return o1.getFecha().compareTo(o2.getFecha());
		}		
	};
	
	
	private double calcularSaldoInicialBancario(int filaSeleccionada) throws GenericBusinessException {
		
		double saldoInicialBancario = 0D;
				
		DefaultTableModel modelo = (DefaultTableModel) getTblCuentaBancaria().getModel();
		
		//¿Cual es la fecha del día anterior al rango de fechas?
				
		Calendar calendarFechaSaldoInicial = (Calendar)DeepCopy.copy(getCmbFechaInicio().getCalendar());
		//resto un día
		calendarFechaSaldoInicial.add(Calendar.DATE, -1);
				
		Date fechaSaldoInicialTemp = new Date(calendarFechaSaldoInicial.getTimeInMillis());		
		java.sql.Date fechaSaldoInicialSql = Utilitarios.fromTimestampToSqlDate(Utilitarios.resetTimestampStartDate(new java.sql.Timestamp(fechaSaldoInicialTemp.getTime())));
				
		//Map carterasMap = mapearCarterasMap(fechaSaldoInicialSql, fechaSaldoInicialSql);
		//Map carteraDetallesMap = mapearCarteraDetallesMap(fechaSaldoInicialSql, fechaSaldoInicialSql);
		Map carterasMap = null;
		Map carteraDetallesMap = null;		
		
		int startIndex = 0;
		int endIndex = getTblCuentaBancaria().getRowCount();
		
		if (filaSeleccionada > 0) {
			startIndex = filaSeleccionada;
			endIndex = filaSeleccionada + 1;
		}
		
		for ( int filaTabla = startIndex ; filaTabla < endIndex ; filaTabla++ ) {	
			
			CuentaBancariaIf cuentaBancaria = cuentaBancariaArrayList.get(filaTabla);
			Collection<CuentaIf> cuentasContables = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaDeCuentaBancariaByCuentaBancariaId(cuentaBancaria.getId());
			
			if (cuentasContables.size() == 0)
				throw new GenericBusinessException( "No existe Cuenta asociada a cuenta Bancaria \""+cuentaBancaria.getCuenta()+"\" !!" );
			else if (cuentasContables.size() > 1)
				throw new GenericBusinessException( "Existe mas de una Cuenta asociada a cuenta Bancaria \""+cuentaBancaria.getCuenta()+"\" !!" );
			
			CuentaIf cuentaIf = cuentasContables.iterator().next();
			
			JRBeanCollectionDataSource datosReporte = new JRBeanCollectionDataSource(new ArrayList());
			Collection<Object[]> asientosConciliacionFondoRotativo = null;
			
			if (getRbSiVersionExtendida().isSelected())
				asientosConciliacionFondoRotativo = SessionServiceLocator.getAsientoSessionService().findAsientosConciliacionBancariaExtendida(cuentaIf.getId(), fechaSaldoInicialSql, fechaSaldoInicialSql);
			else
				asientosConciliacionFondoRotativo = SessionServiceLocator.getAsientoSessionService().findAsientosConciliacionBancaria(cuentaIf.getId(), fechaSaldoInicialSql, fechaSaldoInicialSql);
			
			//////////////////////////////////////////
			//INGRESOS CONCILIACION FONDO ROTATIVO
			//////////////////////////////////////////
												
			//INGRESOS
			double totalIngresos = 0D;
			
			Map<Long, CarteraIf> carterasIngresosMap = new HashMap<Long, CarteraIf>();
			Map<Long, CarteraDetalleIf> carteraIngresoIngresoDetalle = new HashMap<Long, CarteraDetalleIf>();
			Collection carterasIngresosNoCruzados = SessionServiceLocator.getCarteraSessionService().findCarteraByTipoDocumentoByEmpresaIdByFechaInicialByFechaFinalAndByCuentaBancariaId("CIN", Parametros.getIdEmpresa(), fechaSaldoInicialSql, fechaSaldoInicialSql, cuentaBancaria.getId());
			Iterator carterasIngresosNoCruzadosIt = carterasIngresosNoCruzados.iterator();
			while(carterasIngresosNoCruzadosIt.hasNext()){
				Object[] carteraIngresoObject = (Object[])carterasIngresosNoCruzadosIt.next();
				CarteraIf carteraIngreso = (CarteraIf)carteraIngresoObject[0];
				CarteraDetalleIf carteraDetalleIngreso = (CarteraDetalleIf)carteraIngresoObject[1];
				
				carterasIngresosMap.put(carteraIngreso.getId(), carteraIngreso);					
				carteraIngresoIngresoDetalle.put(carteraIngreso.getId(), carteraDetalleIngreso);
			}
						
			Iterator carterasIngresosMapIt = carterasIngresosMap.keySet().iterator();
			while(carterasIngresosMapIt.hasNext()){
				Long carteraId = (Long)carterasIngresosMapIt.next();
				CarteraIf cartera = (CarteraIf)carterasIngresosMap.get(carteraId);
				CarteraDetalleIf carteraDetalle = (CarteraDetalleIf)carteraIngresoIngresoDetalle.get(cartera.getId());
				
				int contadorCIN = 0;
				
				//si la cartera es tipo Cliente
				if(cartera != null && cartera.getTipo().equals("C")){
					
					//CIN = COMPROBANTE DE INGRESO, 
					//cuento las veces que entre para no sumar dos veces el mismo valor
					contadorCIN = contadorCIN + 1;														
					
					if (cartera == null || (cartera != null && cartera.getEstado().equals("N"))) {
						if(contadorCIN == 1){
							totalIngresos += (cartera.getValor()).doubleValue();
						}
					}
				}
			}
						
			//INGRESOS.- DE ASIENTOS MANUALES
			
			Iterator<Object[]> ingresosIterator = asientosConciliacionFondoRotativo.iterator();			
			while (ingresosIterator.hasNext()) {
				Object[] obj = ingresosIterator.next();
				FondoRotativoIngresoData ingresoConciliacionFondoRotativo = obtenerDatosIngresosConciliacionFondoRotativo(obj, carterasMap);
				if (ingresoConciliacionFondoRotativo != null && ingresoConciliacionFondoRotativo.getValorComprobante() > 0D) {
					ingresoConciliacionFondoRotativo.setImprimirCabecera(true);
					
					totalIngresos += ingresoConciliacionFondoRotativo.getValorComprobante();
				}
			}						
						
			//limpio los mapas que se van a llenar en los while de egresos y cheques emitidos
			mapaPlanMedioPorFacturar.clear();
			mapaPlanMedioPorFacturarValores.clear();
			mapaPresupuestoPorFacturar.clear();
			mapaPresupuestoPorFacturarValores.clear();
			mapaCarteraEgresoPorFacturar.clear();
			mapaCarteraEgresoPorFacturarValores.clear();
			
			
			// EGRESOS CONCILIACION FONDO ROTATIVO
			double totalEgresos = 0D;
			Iterator<Object[]> egresosIterator = asientosConciliacionFondoRotativo.iterator();
			
			while (egresosIterator.hasNext()) {
				Object[] obj = egresosIterator.next();
				Vector<FondoRotativoEgresoData> egresoConciliacionFondoRotativoVector = obtenerDatosEgresosConciliacionFondoRotativo(obj, cuentaBancaria, carterasMap, carteraDetallesMap, false);
				for (int i=0; i<egresoConciliacionFondoRotativoVector.size(); i++) {
					FondoRotativoEgresoData egresoConciliacionFondoRotativo = egresoConciliacionFondoRotativoVector.get(i);
					if (egresoConciliacionFondoRotativo.getValor() > 0D && egresoConciliacionFondoRotativo.getCheque() != null) {
						totalEgresos += egresoConciliacionFondoRotativo.getValor();
					}
				}
			}
			
			//SALDO INICIAL CUENTA BANCOS
			
			Calendar fechaSaldoInicial =  new GregorianCalendar();
			fechaSaldoInicial.setTime(calendarFechaSaldoInicial.getTime());
			fechaSaldoInicial.add(Calendar.MONTH,-1);
			
			Calendar fechaFinSaldoInicial =  new GregorianCalendar();
			fechaFinSaldoInicial.setTime(fechaSaldoInicial.getTime());
			fechaFinSaldoInicial.set(Calendar.DATE,fechaFinSaldoInicial.getActualMaximum(Calendar.DAY_OF_MONTH));
			
			Collection<PeriodoIf> periodos = SessionServiceLocator.getPeriodoSessionService().findPeriodoByRangoFechas(Parametros.getIdEmpresa(), new java.sql.Date (fechaSaldoInicial.getTime().getTime()), new java.sql.Date (fechaFinSaldoInicial.getTime().getTime()) );
			
			double totalSaldoCuenta = 0.0;
			if ( periodos.size() > 1 )
				throw new GenericBusinessException("En rango de fecha abarca mas de un periodo !! ");
			else if (periodos.size() == 1) {
				PeriodoIf periodoIf = periodos.iterator().next();
				NumberFormat format = new DecimalFormat("00");
				Map<String, Object> mapaSaldoCuenta = new HashMap<String, Object>();
				mapaSaldoCuenta.put("anio",String.valueOf( fechaSaldoInicial.get(Calendar.YEAR) ) );
				String mes =  format.format( fechaSaldoInicial.get(Calendar.MONTH) + 1);
				mapaSaldoCuenta.put("mes",mes);
				mapaSaldoCuenta.put("cuentaId",cuentaIf.getId());
				mapaSaldoCuenta.put("periodoId",periodoIf.getId());
				Collection<SaldoCuentaIf> saldosCuentas = SessionServiceLocator.getSaldoCuentaSessionService().findSaldoCuentaByQuery(mapaSaldoCuenta);
				SaldoCuentaIf saldoCuentaIf = null;
				
				if ( saldosCuentas.size() > 1 )
					throw new GenericBusinessException("Existe mas de un saldo Cuenta en la fecha Inicio para cuenta \""+cuentaIf.getCodigo()+"\" ");
				else if ( saldosCuentas.size()== 1 )
					saldoCuentaIf = saldosCuentas.iterator().next();
				
				if ( saldoCuentaIf != null )
					totalSaldoCuenta = Utilitarios.redondeoValor(saldoCuentaIf.getValor().doubleValue());
				
				String dia=String.valueOf( fechaSaldoInicial.get(Calendar.DAY_OF_MONTH));
				double valorSumar = valorSumaInicial(mes,String.valueOf(fechaSaldoInicial.get(Calendar.YEAR) ), dia, cuentaIf, cuentaBancaria);
				totalSaldoCuenta=totalSaldoCuenta+valorSumar;	
				totalSaldoCuenta = Utilitarios.redondeoValor(totalSaldoCuenta);					
			} 

			Collection<Object[]> chequesEmitidos = SessionServiceLocator.getChequeEmitidoSessionService().findChequesEmitidosConciliacionBancaria(cuentaBancaria.getId(), fechaSaldoInicialSql, fechaSaldoInicialSql);
			double totalChequesEmitidos = 0D;
			Iterator<Object[]> chequesEmitidosIterator = chequesEmitidos.iterator();
			while (chequesEmitidosIterator.hasNext()) {
				Object[] obj = chequesEmitidosIterator.next();
				FondoRotativoEgresoData chequeEmitidoConciliacionFondoRotativo = obtenerDatosChequesEmitidosConciliacionFondoRotativo(obj, carterasMap);
				
				if (chequeEmitidoConciliacionFondoRotativo.getValor() > 0D) {
					totalChequesEmitidos += chequeEmitidoConciliacionFondoRotativo.getValor();
				}
			}

			saldoInicialBancario = totalSaldoCuenta - totalEgresos + totalIngresos  + totalChequesEmitidos;
		}
		
		return saldoInicialBancario;
	}
	
	Comparator<FondoRotativoFacturaCanceladaData> ordenadorFacturasCanceladasConciliacionFondoRotativo = new Comparator<FondoRotativoFacturaCanceladaData>(){
		public int compare(FondoRotativoFacturaCanceladaData o1, FondoRotativoFacturaCanceladaData o2) {
			return o1.getDetalleFactura().compareTo(o2.getDetalleFactura());			
		}		
	};
	
	Comparator<FondoRotativoFacturaCanceladaData> ordenadorFacturasCanceladasConciliacionFondoRotativoIngreso = new Comparator<FondoRotativoFacturaCanceladaData>(){
		public int compare(FondoRotativoFacturaCanceladaData o1, FondoRotativoFacturaCanceladaData o2) {
			return o1.getCodigoComprobante().compareTo(o2.getCodigoComprobante());			
		}		
	};
	
	Comparator<FondoRotativoFacturaPendienteData> ordenadorFacturasPendientesConciliacionFondoRotativo = new Comparator<FondoRotativoFacturaPendienteData>(){
		public int compare(FondoRotativoFacturaPendienteData o1, FondoRotativoFacturaPendienteData o2) {
			return o1.getDetalleFactura().compareTo(o2.getDetalleFactura());			
		}		
	};
	
	Comparator<FondoRotativoFacturaPendienteData> ordenadorFacturasPendientesConciliacionFondoRotativoIngreso = new Comparator<FondoRotativoFacturaPendienteData>(){
		public int compare(FondoRotativoFacturaPendienteData o1, FondoRotativoFacturaPendienteData o2) {
			return o1.getCodigoComprobante().compareTo(o2.getCodigoComprobante());			
		}		
	};
	
	Comparator<FondoRotativoEgresoData> ordenadorPresupuestosPendientesPorMes = new Comparator<FondoRotativoEgresoData>(){
		public int compare(FondoRotativoEgresoData o1, FondoRotativoEgresoData o2) {
			return o1.getFecha().compareTo(o2.getFecha());			
		}		
	};
	
	Comparator<FondoRotativoEgresoData> ordenadorPresupuestosPendientesPorDetalle = new Comparator<FondoRotativoEgresoData>(){
		public int compare(FondoRotativoEgresoData o1, FondoRotativoEgresoData o2) {
			return o1.getDetalle().compareTo(o2.getDetalle());			
		}		
	};
	
	/*
	ArrayList<FondoRotativoIngresoData> ingresosConciliacionFondoRotativo = new ArrayList<FondoRotativoIngresoData>();
	double totalIngresos = 0D;
	Iterator<Object[]> ingresosIterator = asientosConciliacionFondoRotativo.iterator();
	
	while (ingresosIterator.hasNext()) {
		Object[] obj = ingresosIterator.next();
		//FondoRotativoIngresoData ingresoConciliacionFondoRotativo = obtenerDatosIngresosConciliacionFondoRotativo(obj, carterasMap);
						
		if(((BigDecimal)obj[4]).doubleValue() > 0D){
			CarteraIf cartera = null;				
			int contadorCIN = 0;
			if (obj[6] != null && obj[7] != null) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get((Long) obj[6]);
				ModuloIf modulo = (ModuloIf) modulosMap.get(tipoDocumento.getModuloId());
				if (modulo.getCodigo().equals("CART"))
					cartera = (CarteraIf) carterasMap.get((Long) obj[7]);
			}
							
			if(cartera != null){
				//detalles de la cartera CIN
				Collection detallesCartera = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(cartera.getId());
				Iterator detallesCarteraIt = detallesCartera.iterator();
				while(detallesCarteraIt.hasNext()){
					CarteraDetalleIf carteraDetalle = (CarteraDetalleIf)detallesCarteraIt.next();
																				
					//carteras afecta de cada detalle
					Collection afectasCarteraDetalle = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(carteraDetalle.getId());
					
					if(afectasCarteraDetalle.size() > 0){
						Iterator afectasCarteraDetalleIt = afectasCarteraDetalle.iterator();
						while(afectasCarteraDetalleIt.hasNext()){
							CarteraAfectaIf cateraAfecta = (CarteraAfectaIf)afectasCarteraDetalleIt.next();
							
							//CIN = COMPROBANTE DE INGRESO, 
							//cuento las veces que entre para no sumar dos veces el mismo valor
							contadorCIN = contadorCIN + 1;
																
							//cartera detalle de la factura
							CarteraDetalleIf carteraDetalleFactura = SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(cateraAfecta.getCarteraafectaId());
							//cartera de la factura
							CarteraIf carteraFactura = SessionServiceLocator.getCarteraSessionService().getCartera(carteraDetalleFactura.getCarteraId());
							
							FondoRotativoIngresoData ingresoConciliacionFondoRotativo = new FondoRotativoIngresoData();
							if (cartera == null || (cartera != null && cartera.getEstado().equals("N")) && carteraFactura.getPreimpreso() != null) {
								ingresoConciliacionFondoRotativo.setFechaComprobante(Utilitarios.getFechaCortaUppercase((java.sql.Date) obj[1]));
								ingresoConciliacionFondoRotativo.setCodigoComprobante((cartera!=null)?cartera.getCodigo():(String)obj[0]);
								String numeroCheque = carteraDetalle.getChequeNumero();								
								ingresoConciliacionFondoRotativo.setNumeroCheque(numeroCheque!=null?  "CHQ.# " + numeroCheque : "TRANSFERENCIA");
								String nombreCuenta = "";
								if(carteraDetalle.getChequeBancoId() != null){
									BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(carteraDetalle.getChequeBancoId());
									nombreCuenta = banco.getNombre();
								}
								
								ingresoConciliacionFondoRotativo.setDetalleComprobante(ingresoConciliacionFondoRotativo.getCodigoComprobante() + " CON " + ingresoConciliacionFondoRotativo.getNumeroCheque() + " DEL " + nombreCuenta);
								ingresoConciliacionFondoRotativo.setValorComprobante(((BigDecimal)obj[4]).doubleValue());
									
								if(contadorCIN == 1){
									totalIngresos += ((BigDecimal)obj[4]).doubleValue();
								}
								
								String chequeFactura = carteraDetalle.getChequeNumero();
								if(chequeFactura == null || chequeFactura.equals("")){
									chequeFactura = "TRANFERENCIA";
								}else{
									chequeFactura = "CHQ.# " + chequeFactura;
								}
								
								//factura
								FacturaIf factura = SessionServiceLocator.getFacturaSessionService().getFactura(carteraFactura.getReferenciaId());
								
								if(factura == null){
									System.out.println("s");
								}
								
								String fechaFactura = Utilitarios.getFechaCortaUppercase(factura.getFechaFactura());
								//presupuesto
								String codigoPresupuesto = "N/A";
								PedidoIf pedido = SessionServiceLocator.getPedidoSessionService().getPedido(factura.getPedidoId());
								if(pedido.getReferencia() != null){
									codigoPresupuesto = pedido.getReferencia();
								}
								
								//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
								//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'
																	
								//producto
								String producto = "N/A";
								if(pedido.getTiporeferencia() != null && pedido.getTiporeferencia().equals("I")){
									Collection planMedioColeccion = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndByEstados(codigoPresupuesto, "N", "P", "A", "D", "F");
									Iterator planMedioColeccionIt = planMedioColeccion.iterator();
									while(planMedioColeccionIt.hasNext()){
										PlanMedioIf planMedio = (PlanMedioIf)planMedioColeccionIt.next();
										Collection planMedioMeses = SessionServiceLocator.getPlanMedioMesSessionService().findPlanMedioMesByPlanmedioId(planMedio.getId());
										Iterator planMedioMesesIt = planMedioMeses.iterator();
										while(planMedioMesesIt.hasNext()){
											PlanMedioMesIf planMedioMes = (PlanMedioMesIf)planMedioMesesIt.next();
											Collection planMedioDetalles = SessionServiceLocator.getPlanMedioDetalleSessionService().findPlanMedioDetalleByPlanMedioMesId(planMedioMes.getId());
											Iterator planMedioDetallesIt = planMedioDetalles.iterator();
											while(planMedioDetallesIt.hasNext()){
												PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf)planMedioDetallesIt.next();
												ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(planMedioDetalle.getProductoClienteId());
												
												producto = productoCliente.getNombre();	
											}
										}
									}										
								}else if(pedido.getTiporeferencia() != null && pedido.getTiporeferencia().equals("P")){
									Collection presupuestosColeccion = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(codigoPresupuesto);
									Iterator presupuestosColeccionIt = presupuestosColeccion.iterator();
									while(presupuestosColeccionIt.hasNext()){
										PresupuestoIf presupuesto = (PresupuestoIf)presupuestosColeccionIt.next();
										Collection presupuestoProductos = SessionServiceLocator.getPresupuestoProductoSessionService().findPresupuestoProductoByPresupuestoId(presupuesto.getId());
										Iterator presupuestoProductosIt = presupuestoProductos.iterator();
										while(presupuestoProductosIt.hasNext()){
											PresupuestoProductoIf presupuestoProducto = (PresupuestoProductoIf)presupuestoProductosIt.next();
											ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(presupuestoProducto.getProductoClienteId());
											producto = productoCliente.getNombre();
										}
									}
								}
								
								//sap
								String sap = "N/A";
								if(factura.getAutorizacionSap() != null){
									sap = factura.getAutorizacionSap();
								}
								
								ingresoConciliacionFondoRotativo.setDetalleFactura("F: " + carteraFactura.getPreimpreso() + ", " + fechaFactura + ", P: " + codigoPresupuesto + ", SAP: " + sap + ", " + chequeFactura + ", PRODUCTO: " + producto);
								ingresoConciliacionFondoRotativo.setValorFactura(cateraAfecta.getValor().doubleValue());
							}
							
							if (ingresoConciliacionFondoRotativo != null && ingresoConciliacionFondoRotativo.getValorComprobante() > 0D) {
								
								//aumento un vector al inicio para que salga el mensaje FACTURAS CANCELADAS
								if(ingresosConciliacionFondoRotativo.size() == 0){
									FondoRotativoIngresoData ingresoConciliacionFondoRotativoEXTRA = (FondoRotativoIngresoData)DeepCopy.copy(ingresoConciliacionFondoRotativo);
									ingresoConciliacionFondoRotativoEXTRA.setDetalleFactura("FACTURAS CANCELADAS:");
									ingresoConciliacionFondoRotativoEXTRA.setValorFactura(0D);
									ingresosConciliacionFondoRotativo.add(ingresoConciliacionFondoRotativoEXTRA);
								}
								
								//agrego facturas
								ingresosConciliacionFondoRotativo.add(ingresoConciliacionFondoRotativo);
							}
						}
						
					//Si el ingreso aún no ha sido cruzado con ninguna factura
					}else{
						
						//CIN = COMPROBANTE DE INGRESO, 
						//cuento las veces que entre para no sumar dos veces el mismo valor
						contadorCIN = contadorCIN + 1;
														
						
						FondoRotativoIngresoData ingresoConciliacionFondoRotativo = new FondoRotativoIngresoData();
						if (cartera == null || (cartera != null && cartera.getEstado().equals("N"))) {
							ingresoConciliacionFondoRotativo.setFechaComprobante(Utilitarios.getFechaCortaUppercase((java.sql.Date) obj[1]));
							ingresoConciliacionFondoRotativo.setCodigoComprobante((cartera!=null)?cartera.getCodigo():(String)obj[0]);
							String numeroCheque = carteraDetalle.getChequeNumero();								
							ingresoConciliacionFondoRotativo.setNumeroCheque(numeroCheque!=null?  "CHQ.# " + numeroCheque : "TRANSFERENCIA");
							String nombreCuenta = "";
							if(carteraDetalle.getChequeBancoId() != null){
								BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(carteraDetalle.getChequeBancoId());
								nombreCuenta = banco.getNombre();
							}
							
							
							//fri.setDetalleComprobante(fri.getCodigoComprobante() + " " + (String)obj[2]);
							ingresoConciliacionFondoRotativo.setDetalleComprobante(ingresoConciliacionFondoRotativo.getCodigoComprobante() + " CON " + ingresoConciliacionFondoRotativo.getNumeroCheque() + " DEL " + nombreCuenta);
							ingresoConciliacionFondoRotativo.setValorComprobante(((BigDecimal)obj[4]).doubleValue());
							
							if(contadorCIN == 1){
								totalIngresos += ((BigDecimal)obj[4]).doubleValue();
							}
							
							ingresoConciliacionFondoRotativo.setDetalleFactura("N/A");
							ingresoConciliacionFondoRotativo.setValorFactura(0D);
						}
						
						if (ingresoConciliacionFondoRotativo != null && ingresoConciliacionFondoRotativo.getValorComprobante() > 0D) {
							ingresosConciliacionFondoRotativo.add(ingresoConciliacionFondoRotativo);
						}
					}
					
					
				}
			}else{
				FondoRotativoIngresoData ingresoConciliacionFondoRotativo = new FondoRotativoIngresoData();
				if (cartera == null || (cartera != null && cartera.getEstado().equals("N"))) {
					ingresoConciliacionFondoRotativo.setFechaComprobante(Utilitarios.getFechaCortaUppercase((java.sql.Date) obj[1]));
					ingresoConciliacionFondoRotativo.setCodigoComprobante((cartera!=null)?cartera.getCodigo():(String)obj[0]);
					String numeroCheque = (String) obj[3];
					ingresoConciliacionFondoRotativo.setNumeroCheque(numeroCheque!=null?  "CHQ.# " + numeroCheque : "TRANSFERENCIA");
					Long cuentaId = (Long) obj[9];
					String nombreCuenta = SessionServiceLocator.getCuentaSessionService().getCuenta(cuentaId).getNombre();
					ingresoConciliacionFondoRotativo.setDetalleComprobante(ingresoConciliacionFondoRotativo.getCodigoComprobante() + " CON " + ingresoConciliacionFondoRotativo.getNumeroCheque() + " DEL " + nombreCuenta);
					ingresoConciliacionFondoRotativo.setValorComprobante(((BigDecimal)obj[4]).doubleValue());
									
					totalIngresos += ((BigDecimal)obj[4]).doubleValue();
					
					ingresoConciliacionFondoRotativo.setDetalleFactura("N/A");
					ingresoConciliacionFondoRotativo.setValorFactura(0D);
				}
				
				if (ingresoConciliacionFondoRotativo != null && ingresoConciliacionFondoRotativo.getValorComprobante() > 0D) {
					ingresosConciliacionFondoRotativo.add(ingresoConciliacionFondoRotativo);
					
				}
			}	
		}	*/					
		
		/*if (ingresoConciliacionFondoRotativo != null && ingresoConciliacionFondoRotativo.getValorComprobante() > 0D) {
			ingresosConciliacionFondoRotativo.add(ingresoConciliacionFondoRotativo);
			totalIngresos += ingresoConciliacionFondoRotativo.getValorComprobante();
		}*/
	//}
	
	@SuppressWarnings("unchecked")
	private Vector<FondoRotativoEgresoData> obtenerDatosEgresosConciliacionFondoRotativo(Object[] obj, CuentaBancariaIf cuentaBancaria, Map carterasMap, Map carteraDetallesMap, boolean buscarChequesAnulados) throws GenericBusinessException {
		Vector<FondoRotativoEgresoData> cbeVector = new Vector<FondoRotativoEgresoData>();
		
		//tipo de proveedor de la compra
		Long idTipoProveedor = null;
		if(tipoProveedorIf != null)
			idTipoProveedor = tipoProveedorIf.getId();
		
		//tipo de documento de la factura del cliente
		Long idTipoDocumento = null;
		if(tipoDocumentoIf != null)
			idTipoDocumento = tipoDocumentoIf.getId();
		
		
		CarteraIf cartera = null;
		
		if (obj[6] != null && obj[7] != null) {
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get((Long) obj[6]);
			ModuloIf modulo = (ModuloIf) modulosMap.get(tipoDocumento.getModuloId());
			if (modulo.getCodigo().equals("CART")) {
				if(carterasMap != null){
					cartera = (CarteraIf) carterasMap.get((Long) obj[7]);
				}else{
					cartera = SessionServiceLocator.getCarteraSessionService().getCartera((Long) obj[7]);
				}
			}
		}
		
		//cuando no viene de cartera
		if (!buscarChequesAnulados && cartera == null) {
			FondoRotativoEgresoData cbe = new FondoRotativoEgresoData();
			cbe.setFecha(Utilitarios.getFechaCortaUppercase((java.sql.Date) obj[1]));
			cbe.setDetalle((String)obj[0]);
			cbe.setProveedor((String)obj[2]);
			String numeroCheque = (String) obj[3];
			cbe.setCheque( numeroCheque!=null?numeroCheque:"N/A" );
			cbe.setValor(((BigDecimal)obj[5]).doubleValue());
			cbeVector.add(cbe);
		} 
		
		//si viene de cartera
		else {
			String filtroEstado = !buscarChequesAnulados?"N":"A";
			
			ClienteOficinaIf clienteOficina = mapaClienteOficina.get(cartera.getClienteoficinaId());
			ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());
			TipoProveedorIf tipoProveedor = mapaTipoProveedor.get(cliente.getTipoproveedorId());				
						
			if (cartera != null 
					&& cartera.getEstado().equals(filtroEstado) 
					&& ((BigDecimal)obj[5]).doubleValue() > 0D 
					&& cartera.getTipo().equals("P")) {				
				
				if(cartera.getCodigo().equals("CRE-ANP-09-2012-00033")){
					System.out.println("test");
				}
				
				FacturaIf factura = null;
				double valorFactura = 0D;
				PlanMedioIf planMedio = null;
				PresupuestoIf presupuesto = null;
				Collection carterasCompra = SessionServiceLocator.getCarteraSessionService().findCarteraAfectadaByCarteraId(cartera.getId());
				Iterator carterasCompraIt = carterasCompra.iterator();
				while(carterasCompraIt.hasNext()){
					CarteraIf carteraCompra = (CarteraIf)carterasCompraIt.next();
					
					valorFactura = carteraCompra.getValor().doubleValue();
					
					TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(carteraCompra.getTipodocumentoId());
					if(tipoDocumento.getCodigo().equals("COM") || tipoDocumento.getCodigo().equals("COR")){
						Collection ordenesAsociadas = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(carteraCompra.getReferenciaId());
						Iterator ordenesAsociadasIt = ordenesAsociadas.iterator();
						while(ordenesAsociadasIt.hasNext()){
							OrdenAsociadaIf ordenAsociada = (OrdenAsociadaIf)ordenesAsociadasIt.next();
							if(ordenAsociada.getTipoOrden().equals("OM")){
								OrdenMedioIf ordenMedio = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenAsociada.getOrdenId());
								
								planMedio = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(ordenMedio.getPlanMedioId());
												
								//muy importante que la busqueda sea por la orden ya que si existe facturacion parcial entonces esto nos dara la factura correcta
								Collection planesMedioFacturacion = SessionServiceLocator.getPlanMedioFacturacionSessionService().findPlanMedioFacturacionByOrdenMedioId(ordenMedio.getId());
								Iterator planesMedioFacturacionIt = planesMedioFacturacion.iterator();
								while(planesMedioFacturacionIt.hasNext()){
									PlanMedioFacturacionIf planMedioFacturacion = (PlanMedioFacturacionIf)planesMedioFacturacionIt.next();
									Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(planMedioFacturacion.getPedidoId());
									Iterator facturasIt = facturas.iterator();
									while(facturasIt.hasNext()){
										factura = (FacturaIf)facturasIt.next();										
									}
								}
								
							}else if(ordenAsociada.getTipoOrden().equals("OC")){
								OrdenCompraIf ordenCompra = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(ordenAsociada.getOrdenId());
								
								//valor Total
								/*double porcentajeDescuentoEspecial = ordenCompra.getPorcentajeDescuentoEspecial().doubleValue() / 100;
								double descuentoEspecial = ordenCompra.getValor().doubleValue() * porcentajeDescuentoEspecial;
								double descuentoAgencia = ordenCompra.getDescuentoAgenciaCompra().doubleValue();
								double porcentajeDescuestosVarios = ordenCompra.getPorcentajeDescuentosVariosCompra().doubleValue() / 100;
								double descuentosVarios = (ordenCompra.getValor().doubleValue() - descuentoEspecial) * porcentajeDescuestosVarios;
								valorFactura = ordenCompra.getValor().doubleValue() - descuentoEspecial - descuentoAgencia - descuentosVarios + ordenCompra.getIva().doubleValue();
								*/
								
								if(ordenCompra != null){
									Collection presupuestosDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByOrdenCompraId(ordenCompra.getId());
									Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
									while(presupuestosDetalleIt.hasNext()){
										PresupuestoDetalleIf presupuestoDetalle = (PresupuestoDetalleIf)presupuestosDetalleIt.next();
										
										presupuesto = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalle.getPresupuestoId());
										
										//muy importante que la busqueda sea por la presupuesto detalle ya que si existe facturacion parcial entonces esto nos dara la factura correcta
										Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByPresupuestoDetalleId(presupuestoDetalle.getId());
										Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
										while(presupuestosFacturacionIt.hasNext()){
											PresupuestoFacturacionIf presupuestoFacturacion = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
											factura = SessionServiceLocator.getFacturaSessionService().getFactura(presupuestoFacturacion.getFacturaId());
										}
									}
								}else{
									System.out.println("a");
								}
							}
						}
					}
				}				
				
				Map chequesMap = new HashMap();
				
				
				//Map detallesPorCarteraIdMap = (Map) carteraDetallesMap.get(cartera.getId());
				//Iterator it = detallesPorCarteraIdMap.keySet().iterator();
				
				Collection carterasDetalle = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(cartera.getId());
				Iterator carteraDetalleIt = carterasDetalle.iterator();
				
				while(carteraDetalleIt.hasNext()){
					CarteraDetalleIf carteraDetalle = (CarteraDetalleIf)carteraDetalleIt.next();
				//}				
				
				//while (it.hasNext()) {
					//Long carteraDetalleId = (Long) it.next();
					//CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) detallesPorCarteraIdMap.get(carteraDetalleId);
					DocumentoIf documento = (DocumentoIf) documentosMap.get(carteraDetalle.getDocumentoId());
					if (agregarEgreso(cuentaBancaria, documento, carteraDetalle, buscarChequesAnulados)) {
						
						if (!documento.getCheque().equals("S")) {
							FondoRotativoEgresoData cbe = new FondoRotativoEgresoData();
							cbe.setFecha(Utilitarios.getFechaCortaUppercase(cartera.getFechaEmision()));
							cbe.setDetalle(cartera.getCodigo());
							
							if(idTipoProveedor == null || (idTipoProveedor != null && tipoProveedor.getId().compareTo(idTipoProveedor)  == 0)){
								cbe.setProveedor(cliente.getRazonSocial());
							}else{
								cbe.setProveedor(cliente.getRazonSocial() + " (*)");
							}
							
							if(factura != null){
								cbe.setFactura(factura.getPreimpresoNumero());
								cbe.setValorFactura(valorFactura);
							}else if(presupuesto != null){
								cbe.setFactura("P: " + presupuesto.getCodigo());
								if(mapaPresupuestoPorFacturar.get(presupuesto.getId()) == null){
									mapaPresupuestoPorFacturar.put(presupuesto.getId(), presupuesto);	
									mapaPresupuestoPorFacturarValores.put(presupuesto.getId(), valorFactura);
								}else{
									double valorPorFacturar = valorFactura + mapaPresupuestoPorFacturarValores.get(presupuesto.getId());
									mapaPresupuestoPorFacturarValores.put(presupuesto.getId(), valorPorFacturar);
								}
								
							}else if(planMedio != null){
								cbe.setFactura("P: " + planMedio.getCodigo());
								if(mapaPlanMedioPorFacturar.get(planMedio.getId()) == null){
									mapaPlanMedioPorFacturar.put(planMedio.getId(), planMedio);				
									mapaPlanMedioPorFacturarValores.put(planMedio.getId(), valorFactura);
								}else{
									double valorPorFacturar = valorFactura + mapaPlanMedioPorFacturarValores.get(planMedio.getId());
									mapaPlanMedioPorFacturarValores.put(planMedio.getId(), valorPorFacturar);
								}
							}else{
								mapaCarteraEgresoPorFacturar.put(cartera.getId(), cartera);				
								mapaCarteraEgresoPorFacturarValores.put(cartera.getId(), cartera.getValor().doubleValue());
							}
							
							cbe.setCheque( "-1" );
							cbe.setValor(carteraDetalle.getValor().doubleValue());
							cbeVector.add(cbe);
						} else {
							if (carteraDetalle.getPreimpreso() != null) {
								BigDecimal valorAcumuladoCheque = (BigDecimal) chequesMap.get(carteraDetalle.getPreimpreso());
								chequesMap.put(carteraDetalle.getPreimpreso(), valorAcumuladoCheque != null?valorAcumuladoCheque.add(carteraDetalle.getValor()):carteraDetalle.getValor());
							} else if (carteraDetalle.getChequeNumero() != null) {
								BigDecimal valorAcumuladoCheque = (BigDecimal) chequesMap.get(carteraDetalle.getChequeNumero());
								chequesMap.put(carteraDetalle.getChequeNumero(), valorAcumuladoCheque != null?valorAcumuladoCheque.add(carteraDetalle.getValor()):carteraDetalle.getValor());
							}
						}
					}
				}
				
				if (chequesMap.size() > 0) {
					Iterator chequeIterator = chequesMap.keySet().iterator();
					while (chequeIterator.hasNext()) {
						String cheque = (String) chequeIterator.next();
						FondoRotativoEgresoData cbe = new FondoRotativoEgresoData();
						cbe.setFecha(Utilitarios.getFechaCortaUppercase(cartera.getFechaEmision()));
						cbe.setDetalle(cartera.getCodigo());
						
						if(idTipoProveedor == null || (idTipoProveedor != null && tipoProveedor.getId().compareTo(idTipoProveedor)  == 0)){
							cbe.setProveedor(cliente.getRazonSocial());
						}else{
							cbe.setProveedor(cliente.getRazonSocial() + " (*)");
						}
						
						if(factura != null){
							cbe.setFactura(factura.getPreimpresoNumero());
							cbe.setValorFactura(valorFactura);
						}else if(presupuesto != null){
							cbe.setFactura("P: " + presupuesto.getCodigo());
							if(mapaPresupuestoPorFacturar.get(presupuesto.getId()) == null){
								mapaPresupuestoPorFacturar.put(presupuesto.getId(), presupuesto);	
								mapaPresupuestoPorFacturarValores.put(presupuesto.getId(), valorFactura);
							}else{
								double valorPorFacturar = valorFactura + mapaPresupuestoPorFacturarValores.get(presupuesto.getId());
								mapaPresupuestoPorFacturarValores.put(presupuesto.getId(), valorPorFacturar);
							}
							
						}else if(planMedio != null){
							cbe.setFactura("P: " + planMedio.getCodigo());
							if(mapaPlanMedioPorFacturar.get(planMedio.getId()) == null){
								mapaPlanMedioPorFacturar.put(planMedio.getId(), planMedio);				
								mapaPlanMedioPorFacturarValores.put(planMedio.getId(), valorFactura);
							}else{
								double valorPorFacturar = valorFactura + mapaPlanMedioPorFacturarValores.get(planMedio.getId());
								mapaPlanMedioPorFacturarValores.put(planMedio.getId(), valorPorFacturar);
							}
						}else{
							mapaCarteraEgresoPorFacturar.put(cartera.getId(), cartera);				
							mapaCarteraEgresoPorFacturarValores.put(cartera.getId(), cartera.getValor().doubleValue());
						}
						
						cbe.setCheque(cheque);
						cbe.setValor(((BigDecimal) chequesMap.get(cheque)).doubleValue());
						cbeVector.add(cbe);
					}
				}
				
				if(presupuesto != null && presupuesto.getCodigo().equals("2012-01223")){
					System.out.println("test");
				}
			}
		}
		return cbeVector;
	}
	
	@SuppressWarnings("unchecked")
	private Vector<FondoRotativoEgresoData> obtenerDatosPendientesPorCobrarConciliacionFondoRotativo(Object[] obj, CuentaBancariaIf cuentaBancaria, Map carterasMap, Map carteraDetallesMap, boolean buscarChequesAnulados) throws GenericBusinessException {
		Vector<FondoRotativoEgresoData> cbeVector = new Vector<FondoRotativoEgresoData>();
		
		//tipo de proveedor de la compra
		Long idTipoProveedor = null;
		if(tipoProveedorIf != null)
			idTipoProveedor = tipoProveedorIf.getId();
		
		//tipo de documento de la factura del cliente
		Long idTipoDocumento = null;
		if(tipoDocumentoIf != null)
			idTipoDocumento = tipoDocumentoIf.getId();
		
		Date fechaFin = getCmbFechaFin().getDate();
		java.sql.Date fechaFinSql = Utilitarios.fromTimestampToSqlDate(Utilitarios.resetTimestampEndDate(new java.sql.Timestamp(fechaFin.getTime())));
				
		CarteraIf cartera = null;
		
		/*if(((String)obj[0]).equals("CRE-PC-07-2012-01546")){
			System.out.println("test");
		}*/
		
		if (obj[6] != null && obj[7] != null) {
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get((Long) obj[6]);
			ModuloIf modulo = (ModuloIf) modulosMap.get(tipoDocumento.getModuloId());
			if (modulo.getCodigo().equals("CART")) {
				if(carterasMap != null){
					cartera = (CarteraIf) carterasMap.get((Long) obj[7]);
				}else{
					cartera = SessionServiceLocator.getCarteraSessionService().getCartera((Long) obj[7]);
				}
			}
		}
		
		if (cartera != null && mapaClienteOficina.get(cartera.getClienteoficinaId()) != null) {
			String filtroEstado = !buscarChequesAnulados?"N":"A";
			
			ClienteOficinaIf clienteOficina = mapaClienteOficina.get(cartera.getClienteoficinaId());
			
			if(clienteOficina == null){
				System.out.println("problema");
			}
			
			ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());
			TipoProveedorIf tipoProveedor = mapaTipoProveedor.get(cliente.getTipoproveedorId());				
						
			if (cartera.getEstado().equals(filtroEstado) 
					&& ((BigDecimal)obj[5]).doubleValue() > 0D 
					&& cartera.getTipo().equals("P")) {				
				
				FacturaIf factura = null;
				double valorFactura = 0D;
				PlanMedioIf planMedio = null;
				PresupuestoIf presupuesto = null;
				
				Collection carterasCompra = SessionServiceLocator.getCarteraSessionService().findCarteraAfectadaByCarteraId(cartera.getId());
				Iterator carterasCompraIt = carterasCompra.iterator();
				
				while(carterasCompraIt.hasNext()){
					CarteraIf carteraCompra = (CarteraIf)carterasCompraIt.next();
					
					valorFactura = carteraCompra.getValor().doubleValue();
					
					TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(carteraCompra.getTipodocumentoId());
					
					if(tipoDocumento.getCodigo().equals("COM") || tipoDocumento.getCodigo().equals("COR")){
						
						Collection ordenesAsociadas = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(carteraCompra.getReferenciaId());
						Iterator ordenesAsociadasIt = ordenesAsociadas.iterator();
						
						while(ordenesAsociadasIt.hasNext()){
							OrdenAsociadaIf ordenAsociada = (OrdenAsociadaIf)ordenesAsociadasIt.next();
							
							if(ordenAsociada.getTipoOrden().equals("OM")){
								OrdenMedioIf ordenMedio = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenAsociada.getOrdenId());
								
								planMedio = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(ordenMedio.getPlanMedioId());
												
								//muy importante que la busqueda sea por la orden ya que si existe facturacion parcial entonces esto nos dara la factura correcta
								Collection planesMedioFacturacion = SessionServiceLocator.getPlanMedioFacturacionSessionService().findPlanMedioFacturacionByOrdenMedioId(ordenMedio.getId());
								Iterator planesMedioFacturacionIt = planesMedioFacturacion.iterator();
								while(planesMedioFacturacionIt.hasNext()){
									PlanMedioFacturacionIf planMedioFacturacion = (PlanMedioFacturacionIf)planesMedioFacturacionIt.next();
									Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(planMedioFacturacion.getPedidoId());
									Iterator facturasIt = facturas.iterator();
									while(facturasIt.hasNext()){
										factura = (FacturaIf)facturasIt.next();
										if(factura != null && factura.getEstado().equals("A")){
											factura = null;
										}
									}									
								}
								
							}else if(ordenAsociada.getTipoOrden().equals("OC")){
								OrdenCompraIf ordenCompra = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(ordenAsociada.getOrdenId());
								
								if(ordenCompra != null){
									Collection presupuestosDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByOrdenCompraId(ordenCompra.getId());
									Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
									while(presupuestosDetalleIt.hasNext()){
										PresupuestoDetalleIf presupuestoDetalle = (PresupuestoDetalleIf)presupuestosDetalleIt.next();
										
										presupuesto = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalle.getPresupuestoId());
										
										//muy importante que la busqueda sea por la presupuesto detalle ya que si existe facturacion parcial entonces esto nos dara la factura correcta
										Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByPresupuestoDetalleId(presupuestoDetalle.getId());
										Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
										while(presupuestosFacturacionIt.hasNext()){
											PresupuestoFacturacionIf presupuestoFacturacion = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
											factura = SessionServiceLocator.getFacturaSessionService().getFactura(presupuestoFacturacion.getFacturaId());
											if(factura != null && factura.getEstado().equals("A")){
												factura = null;
											}
										}
									}
								}							
							}
						}
					}
				}				
				
				Map chequesMap = new HashMap();
				//Map detallesPorCarteraIdMap = (Map) carteraDetallesMap.get(cartera.getId());
				//Iterator it = detallesPorCarteraIdMap.keySet().iterator();
								
				Collection carterasDetalle = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(cartera.getId());
				Iterator carterasDetalleIt = carterasDetalle.iterator();
				
				while(carterasDetalleIt.hasNext()){
					CarteraDetalleIf carteraDetalle = (CarteraDetalleIf)carterasDetalleIt.next();
				//}	
				
				//while (it.hasNext()) {
					//Long carteraDetalleId = (Long) it.next();
					//CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) detallesPorCarteraIdMap.get(carteraDetalleId);
					DocumentoIf documento = (DocumentoIf) documentosMap.get(carteraDetalle.getDocumentoId());
					
					if (agregarEgreso(cuentaBancaria, documento, carteraDetalle, buscarChequesAnulados)) {
						
						if (!documento.getCheque().equals("S")) {
							FondoRotativoEgresoData cbe = new FondoRotativoEgresoData();
							cbe.setFecha(Utilitarios.getFechaCortaUppercase(cartera.getFechaEmision()));
							cbe.setDetalle(cartera.getCodigo());
							
							if(idTipoProveedor == null || (idTipoProveedor != null && tipoProveedor.getId().compareTo(idTipoProveedor)  == 0)){
								cbe.setProveedor(cliente.getRazonSocial());
							}else{
								cbe.setProveedor(cliente.getRazonSocial() + " (*)");
							}
							
							boolean fueFacturaPagada = false;
							if(factura != null){
								cbe.setFactura(factura.getPreimpresoNumero());
								cbe.setValorFactura(valorFactura);
								
								//para revisar si la factura fue pagada antes de, o en la fecha fin
								Map mapaCarteraFactura = new HashMap();
								mapaCarteraFactura.put("referenciaId", factura.getId());
								mapaCarteraFactura.put("tipo", "C");
								mapaCarteraFactura.put("tipodocumentoId", factura.getTipodocumentoId());
								
								Collection carteraFactura = SessionServiceLocator.getCarteraSessionService().findCarteraByQuery(mapaCarteraFactura);
								Iterator carteraFacturaIt = carteraFactura.iterator();
								while(carteraFacturaIt.hasNext()){
									CarteraIf carteraFacturaIf = (CarteraIf)carteraFacturaIt.next();
									Collection carterasDetalleFactura = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(carteraFacturaIf.getId());
									Iterator carterasDetalleFacturaIt = carterasDetalleFactura.iterator();
									while(carterasDetalleFacturaIt.hasNext()){
										CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf)carterasDetalleFacturaIt.next();
										Collection carterasAfecta = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteraDetalleAfectadaIdSiLaAfectacionNoEsUnaRetencion(carteraDetalleIf.getId());
										Iterator carterasAfectaIt = carterasAfecta.iterator();
										while(carterasAfectaIt.hasNext()){
											CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf)carterasAfectaIt.next();
											if(carteraAfectaIf.getFechaAplicacion().getTime() <= fechaFinSql.getTime()){
												fueFacturaPagada = true;
											}
										}
									}
								}
								
							}else if(presupuesto != null){
								cbe.setFactura("P: " + presupuesto.getCodigo());
								if(mapaPresupuestoPorFacturar.get(presupuesto.getId()) == null){
									mapaPresupuestoPorFacturar.put(presupuesto.getId(), presupuesto);	
									mapaPresupuestoPorFacturarValores.put(presupuesto.getId(), valorFactura);
								}else{
									double valorPorFacturar = valorFactura + mapaPresupuestoPorFacturarValores.get(presupuesto.getId());
									mapaPresupuestoPorFacturarValores.put(presupuesto.getId(), valorPorFacturar);
								}
								
							}else if(planMedio != null){
								cbe.setFactura("P: " + planMedio.getCodigo());
								if(mapaPlanMedioPorFacturar.get(planMedio.getId()) == null){
									mapaPlanMedioPorFacturar.put(planMedio.getId(), planMedio);				
									mapaPlanMedioPorFacturarValores.put(planMedio.getId(), valorFactura);
								}else{
									double valorPorFacturar = valorFactura + mapaPlanMedioPorFacturarValores.get(planMedio.getId());
									mapaPlanMedioPorFacturarValores.put(planMedio.getId(), valorPorFacturar);
								}
							}else{
								mapaCarteraEgresoPorFacturar.put(cartera.getId(), cartera);				
								mapaCarteraEgresoPorFacturarValores.put(cartera.getId(), cartera.getValor().doubleValue());
							}
							
							cbe.setCheque( "-1" );
							cbe.setValor(carteraDetalle.getValor().doubleValue());
							
							if(!fueFacturaPagada)
								cbeVector.add(cbe);
						
						} else {
							if (carteraDetalle.getPreimpreso() != null) {
								BigDecimal valorAcumuladoCheque = (BigDecimal) chequesMap.get(carteraDetalle.getPreimpreso());
								chequesMap.put(carteraDetalle.getPreimpreso(), valorAcumuladoCheque != null?valorAcumuladoCheque.add(carteraDetalle.getValor()):carteraDetalle.getValor());
							} else if (carteraDetalle.getChequeNumero() != null) {
								BigDecimal valorAcumuladoCheque = (BigDecimal) chequesMap.get(carteraDetalle.getChequeNumero());
								chequesMap.put(carteraDetalle.getChequeNumero(), valorAcumuladoCheque != null?valorAcumuladoCheque.add(carteraDetalle.getValor()):carteraDetalle.getValor());
							}
						}
					}
				}
				
				if (chequesMap.size() > 0) {
					Iterator chequeIterator = chequesMap.keySet().iterator();
					while (chequeIterator.hasNext()) {
						String cheque = (String) chequeIterator.next();
						FondoRotativoEgresoData cbe = new FondoRotativoEgresoData();
						cbe.setFecha(Utilitarios.getFechaCortaUppercase(cartera.getFechaEmision()));
						cbe.setDetalle(cartera.getCodigo());
						
						if(idTipoProveedor == null || (idTipoProveedor != null && tipoProveedor.getId().compareTo(idTipoProveedor)  == 0)){
							cbe.setProveedor(cliente.getRazonSocial());
						}else{
							cbe.setProveedor(cliente.getRazonSocial() + " (*)");
						}
						
						boolean fueFacturaPagada = false;
						if(factura != null){
							cbe.setFactura(factura.getPreimpresoNumero());
							cbe.setValorFactura(valorFactura);
							
							//para revisar si la factura fue pagada antes de, o en la fecha fin
							Map mapaCarteraFactura = new HashMap();
							mapaCarteraFactura.put("referenciaId", factura.getId());
							mapaCarteraFactura.put("tipo", "C");
							mapaCarteraFactura.put("tipodocumentoId", factura.getTipodocumentoId());
							
							Collection carteraFactura = SessionServiceLocator.getCarteraSessionService().findCarteraByQuery(mapaCarteraFactura);
							Iterator carteraFacturaIt = carteraFactura.iterator();
							while(carteraFacturaIt.hasNext()){
								CarteraIf carteraFacturaIf = (CarteraIf)carteraFacturaIt.next();
								Collection carterasDetalleFactura = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(carteraFacturaIf.getId());
								Iterator carterasDetalleFacturaIt = carterasDetalleFactura.iterator();
								while(carterasDetalleFacturaIt.hasNext()){
									CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf)carterasDetalleFacturaIt.next();
									Collection carterasAfecta = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteraDetalleAfectadaIdSiLaAfectacionNoEsUnaRetencion(carteraDetalleIf.getId());
									Iterator carterasAfectaIt = carterasAfecta.iterator();
									while(carterasAfectaIt.hasNext()){
										CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf)carterasAfectaIt.next();
										if(carteraAfectaIf.getFechaAplicacion().getTime() <= fechaFinSql.getTime()){
											fueFacturaPagada = true;
										}
									}
								}
							}
						
						}else if(presupuesto != null){
							cbe.setFactura("P: " + presupuesto.getCodigo());
							if(mapaPresupuestoPorFacturar.get(presupuesto.getId()) == null){
								mapaPresupuestoPorFacturar.put(presupuesto.getId(), presupuesto);	
								mapaPresupuestoPorFacturarValores.put(presupuesto.getId(), valorFactura);
							}else{
								double valorPorFacturar = valorFactura + mapaPresupuestoPorFacturarValores.get(presupuesto.getId());
								mapaPresupuestoPorFacturarValores.put(presupuesto.getId(), valorPorFacturar);
							}
							
						}else if(planMedio != null){
							cbe.setFactura("P: " + planMedio.getCodigo());
							if(mapaPlanMedioPorFacturar.get(planMedio.getId()) == null){
								mapaPlanMedioPorFacturar.put(planMedio.getId(), planMedio);				
								mapaPlanMedioPorFacturarValores.put(planMedio.getId(), valorFactura);
							}else{
								double valorPorFacturar = valorFactura + mapaPlanMedioPorFacturarValores.get(planMedio.getId());
								mapaPlanMedioPorFacturarValores.put(planMedio.getId(), valorPorFacturar);
							}
						}else{
							mapaCarteraEgresoPorFacturar.put(cartera.getId(), cartera);				
							mapaCarteraEgresoPorFacturarValores.put(cartera.getId(), cartera.getValor().doubleValue());
						}
						
						cbe.setCheque(cheque);
						cbe.setValor(((BigDecimal) chequesMap.get(cheque)).doubleValue());
						
						if(!fueFacturaPagada)
							cbeVector.add(cbe);
					}
				}
				
				/*if(presupuesto != null && presupuesto.getCodigo().equals("2012-01223")){
					System.out.println("test");
				}*/
			}
		} 
		
		return cbeVector;
	}
	
	@SuppressWarnings("unchecked")
	private Vector<FondoRotativoEgresoData> obtenerDatosDebitosBancariosConciliacionFondoRotativo(AsientoIf asientoIf, Long cuentaGastosBancariosId) throws GenericBusinessException {
		Vector<FondoRotativoEgresoData> cbeVector = new Vector<FondoRotativoEgresoData>();
				
		double valorGastoBancario = 0;
		String glosa = "";
		
		//busco los detalles
		Collection asientoDetalles = SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByAsientoId(asientoIf.getId());
		Iterator asientoDetallesIt = asientoDetalles.iterator();
		while(asientoDetallesIt.hasNext()){
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf)asientoDetallesIt.next();
			
			//reviso si la cuenta del asiento detalle es cuenta de Gastos Bancarios
			if(asientoDetalle.getCuentaId().compareTo(cuentaGastosBancariosId) == 0){
				
				//si es DEBE sumo y si es HABER resto
				if(asientoDetalle.getDebe().compareTo(new BigDecimal(0)) == 1){
					valorGastoBancario = valorGastoBancario + asientoDetalle.getDebe().doubleValue();
				}else{
					valorGastoBancario = valorGastoBancario - asientoDetalle.getHaber().doubleValue();
				}
				
				glosa = asientoDetalle.getGlosa();
			}
		}
		
		FondoRotativoEgresoData cbe = new FondoRotativoEgresoData();
		cbe.setFecha(Utilitarios.getFechaCortaUppercase(asientoIf.getFecha()));
		cbe.setDetalle(asientoIf.getNumero());
		cbe.setProveedor(glosa);
		cbe.setFactura("");
		cbe.setValorFactura(null);
		cbe.setCheque("");
		cbe.setValor(valorGastoBancario);
		cbeVector.add(cbe);
			
		return cbeVector;
	}
	
	private boolean agregarEgreso(CuentaBancariaIf cuentaBancaria, DocumentoIf documento, CarteraDetalleIf carteraDetalle, boolean buscarChequesAnulados) {
		if (documento.getCheque().equals("S") && ((carteraDetalle.getCuentaBancariaId() != null && !carteraDetalle.getCuentaBancariaId().equals("") && cuentaBancaria.getId().compareTo(carteraDetalle.getCuentaBancariaId()) == 0) || (carteraDetalle.getChequeCuentaBancariaId() != null && !carteraDetalle.getChequeCuentaBancariaId().equals("") && cuentaBancaria.getId().compareTo(carteraDetalle.getChequeCuentaBancariaId()) == 0)))
			return true;
		if (!buscarChequesAnulados && documento.getDebitoBancario().equals("S") && ((carteraDetalle.getCuentaBancariaId() != null && !carteraDetalle.getCuentaBancariaId().equals("") && cuentaBancaria.getId().compareTo(carteraDetalle.getCuentaBancariaId()) == 0) || (carteraDetalle.getDebitoCuentaBancariaId() != null && !carteraDetalle.getDebitoCuentaBancariaId().equals("") && cuentaBancaria.getId().compareTo(carteraDetalle.getDebitoCuentaBancariaId()) == 0)))
			return true;
		if (!buscarChequesAnulados && documento.getTransferenciaBancaria().equals("S") && ((carteraDetalle.getCuentaBancariaId() != null && !carteraDetalle.getCuentaBancariaId().equals("") && cuentaBancaria.getId().compareTo(carteraDetalle.getCuentaBancariaId()) == 0) || (carteraDetalle.getTransferenciaCuentaOrigenId() != null && !carteraDetalle.getTransferenciaCuentaOrigenId().equals("") && cuentaBancaria.getId().compareTo(carteraDetalle.getTransferenciaCuentaOrigenId()) == 0)))
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	private FondoRotativoIngresoData obtenerDatosIngresosConciliacionFondoRotativo(Object[] obj, Map carterasMap) throws GenericBusinessException {
		CarteraIf cartera = null;
		if (obj[6] != null && obj[7] != null) {
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get((Long) obj[6]);
			ModuloIf modulo = (ModuloIf) modulosMap.get(tipoDocumento.getModuloId());
			if (modulo.getCodigo().equals("CART")){
				if(carterasMap != null){
					cartera = (CarteraIf) carterasMap.get((Long) obj[7]);
				}else{
					cartera = SessionServiceLocator.getCarteraSessionService().getCartera((Long) obj[7]);
				}
			}
		}
		FondoRotativoIngresoData fri = new FondoRotativoIngresoData();
		if (cartera == null || (cartera != null && cartera.getEstado().equals("N")) && cartera.getTipo().equals("C") && obj[7] == null) {
			fri.setFechaComprobante(Utilitarios.getFechaCortaUppercase((java.sql.Date) obj[1]));
			fri.setCodigoComprobante((cartera!=null)?cartera.getCodigo():(String)obj[0]);
			String numeroCheque = (String) obj[3];
			fri.setNumeroCheque(numeroCheque!=null?numeroCheque:"TRANSF.");
			Long cuentaId = (Long) obj[9];
			String nombreCuenta = SessionServiceLocator.getCuentaSessionService().getCuenta(cuentaId).getNombre();
			//fri.setDetalleComprobante(fri.getCodigoComprobante() + " " + (String)obj[2]);
			fri.setDetalleComprobante(fri.getCodigoComprobante() + " CON CHQ.# " + fri.getNumeroCheque() + " DEL " + nombreCuenta + (String)obj[2]);
			fri.setValorComprobante(((BigDecimal)obj[4]).doubleValue());
			
			//detalles de la cartera CIN
			//Collection detallesCartera = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(cartera.getId());
			//Iterator detallesCartera
			
			fri.setDetalleFactura("N/A");
			fri.setValorFactura(0D);
			return fri;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private FondoRotativoEgresoData obtenerDatosChequesEmitidosConciliacionFondoRotativo(Object[] obj, Map carterasMap) throws GenericBusinessException {
		
		//tipo de proveedor de la compra
		Long idTipoProveedor = null;
		if(tipoProveedorIf != null)
			idTipoProveedor = tipoProveedorIf.getId();
		
		//tipo de documento de la factura del cliente
		Long idTipoDocumento = null;
		if(tipoDocumentoIf != null)
			idTipoDocumento = tipoDocumentoIf.getId();
		
		CarteraIf cartera = null;
		if (obj[5] != null && obj[6] != null) {
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get((Long) obj[5]);
			ModuloIf modulo = (ModuloIf) modulosMap.get(tipoDocumento.getModuloId());
			if (modulo.getCodigo().equals("CART")) {
				if(carterasMap != null){
					cartera = (CarteraIf) carterasMap.get((Long) obj[6]);
				}else{
					cartera = SessionServiceLocator.getCarteraSessionService().getCartera((Long) obj[6]);
				}
			}
		}
		
		FacturaIf factura = null;
		double valorFactura = 0D;
		PlanMedioIf planMedio = null;
		PresupuestoIf presupuesto = null;
		TipoProveedorIf tipoProveedor = null;
		ClienteIf cliente = null;
		
		if (cartera != null) {
			
			ClienteOficinaIf clienteOficina = mapaClienteOficina.get(cartera.getClienteoficinaId());
			cliente = mapaCliente.get(clienteOficina.getClienteId());
			tipoProveedor = mapaTipoProveedor.get(cliente.getTipoproveedorId());
			
			Collection carterasCompra = SessionServiceLocator.getCarteraSessionService().findCarteraAfectadaByCarteraId(cartera.getId());
			Iterator carterasCompraIt = carterasCompra.iterator();
			while(carterasCompraIt.hasNext()){
				CarteraIf carteraCompra = (CarteraIf)carterasCompraIt.next();
				
				valorFactura = carteraCompra.getValor().doubleValue();
				
				TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(carteraCompra.getTipodocumentoId());
				if(tipoDocumento.getCodigo().equals("COM") || tipoDocumento.getCodigo().equals("COR")){
					Collection ordenesAsociadas = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(carteraCompra.getReferenciaId());
					Iterator ordenesAsociadasIt = ordenesAsociadas.iterator();
					while(ordenesAsociadasIt.hasNext()){
						OrdenAsociadaIf ordenAsociada = (OrdenAsociadaIf)ordenesAsociadasIt.next();
						if(ordenAsociada.getTipoOrden().equals("OM")){
							OrdenMedioIf ordenMedio = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenAsociada.getOrdenId());
							
							if(ordenMedio != null){
								planMedio = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(ordenMedio.getPlanMedioId());
								
								//muy importante que la busqueda sea por la orden ya que si existe facturacion parcial entonces esto nos dara la factura correcta
								Collection planesMedioFacturacion = SessionServiceLocator.getPlanMedioFacturacionSessionService().findPlanMedioFacturacionByOrdenMedioId(ordenMedio.getId());
								Iterator planesMedioFacturacionIt = planesMedioFacturacion.iterator();
								while(planesMedioFacturacionIt.hasNext()){
									PlanMedioFacturacionIf planMedioFacturacion = (PlanMedioFacturacionIf)planesMedioFacturacionIt.next();
									Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(planMedioFacturacion.getPedidoId());
									Iterator facturasIt = facturas.iterator();
									while(facturasIt.hasNext()){
										factura = (FacturaIf)facturasIt.next();										
									}
								}
							}
							
							
						}else if(ordenAsociada.getTipoOrden().equals("OC")){
							OrdenCompraIf ordenCompra = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(ordenAsociada.getOrdenId());
							
							if(ordenCompra != null){
								Collection presupuestosDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByOrdenCompraId(ordenCompra.getId());
								Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
								while(presupuestosDetalleIt.hasNext()){
									PresupuestoDetalleIf presupuestoDetalle = (PresupuestoDetalleIf)presupuestosDetalleIt.next();
									
									presupuesto = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalle.getPresupuestoId());
									
									//muy importante que la busqueda sea por presupuesto detalle ya que si existe facturacion parcial entonces esto nos dara la factura correcta
									Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByPresupuestoDetalleId(presupuestoDetalle.getId());
									Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
									while(presupuestosFacturacionIt.hasNext()){
										PresupuestoFacturacionIf presupuestoFacturacion = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
										factura = SessionServiceLocator.getFacturaSessionService().getFactura(presupuestoFacturacion.getFacturaId());
									}
								}
							}							
						}
					}
				}
			}			
		}
		
		//seteo los datos de los cheques en circulacion
		FondoRotativoEgresoData cbi = new FondoRotativoEgresoData();
		cbi.setFecha(Utilitarios.getFechaCortaUppercase((java.sql.Date) obj[1]));
		cbi.setDetalle((String)obj[0]);
		
		/*if(idTipoProveedor == null || 
				(idTipoProveedor != null && tipoProveedor != null && cliente != null && tipoProveedor.getId().compareTo(idTipoProveedor)  == 0)){
			cbi.setProveedor(cliente.getRazonSocial());
		}else if(cliente != null ){
			cbi.setProveedor(cliente.getRazonSocial() + " (*)");
		}else{
			cbi.setProveedor((String)obj[2]);
		}*/
		
		if(cliente != null ){
			if(idTipoProveedor == null || 
					(idTipoProveedor != null && tipoProveedor != null && tipoProveedor.getId().compareTo(idTipoProveedor)  == 0)){
				cbi.setProveedor(cliente.getRazonSocial());
			}else{
				cbi.setProveedor(cliente.getRazonSocial() + " (*)");
			}
		}else{
			cbi.setProveedor((String)obj[2]);
		}
		
		String numeroCheque = (String) obj[3];
		cbi.setCheque( numeroCheque!=null?numeroCheque:"N/A" );
		cbi.setValor(((BigDecimal)obj[4]).doubleValue());

		if(factura != null){
			cbi.setFactura(factura.getPreimpresoNumero());
			cbi.setValorFactura(valorFactura);
		}else if(presupuesto != null){
			cbi.setFactura("P: " + presupuesto.getCodigo());
			if(mapaPresupuestoPorFacturar.get(presupuesto.getId()) == null){
				mapaPresupuestoPorFacturar.put(presupuesto.getId(), presupuesto);	
				mapaPresupuestoPorFacturarValores.put(presupuesto.getId(), valorFactura);
			}else{
				double valorPorFacturar = valorFactura + mapaPresupuestoPorFacturarValores.get(presupuesto.getId());
				mapaPresupuestoPorFacturarValores.put(presupuesto.getId(), valorPorFacturar);
			}
			
		}else if(planMedio != null){
			cbi.setFactura("P: " + planMedio.getCodigo());
			if(mapaPlanMedioPorFacturar.get(planMedio.getId()) == null){
				mapaPlanMedioPorFacturar.put(planMedio.getId(), planMedio);				
				mapaPlanMedioPorFacturarValores.put(planMedio.getId(), valorFactura);
			}else{
				double valorPorFacturar = valorFactura + mapaPlanMedioPorFacturarValores.get(planMedio.getId());
				mapaPlanMedioPorFacturarValores.put(planMedio.getId(), valorPorFacturar);
			}
		}else if(cartera != null){
			mapaCarteraEgresoPorFacturar.put(cartera.getId(), cartera);				
			mapaCarteraEgresoPorFacturarValores.put(cartera.getId(), cartera.getValor().doubleValue());
		}else{
			System.out.println("test");
		}
		
		if(presupuesto != null && presupuesto.getCodigo().equals("2012-01223")){
			System.out.println("test");
		}
		
		return cbi;
	}
	
	@Override
	public void showSaveMode() {
		clean();
		setSaveMode();
		getCmbFechaInicio().grabFocus();
		tiposDocumentoMap = mapearTiposDocumentos();
		documentosMap = mapearDocumentos();
		modulosMap = mapearModulos();
		
		try {
			clienteOficinaIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(7280L);
			if(clienteOficinaIf != null){
				getTxtClienteOficina().setText(clienteOficinaIf.getDescripcion());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private Map mapearTiposDocumentos() {
		Map tiposDocumentosMap = new HashMap();
		
		try {
			Iterator tiposDocumentosIterator = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (tiposDocumentosIterator.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentosIterator.next();
				tiposDocumentosMap.put(tipoDocumento.getId(), tipoDocumento);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear tipos de documentos", SpiritAlert.ERROR);
		}
		
		return tiposDocumentosMap;
	}
	
	private Map mapearDocumentos() {
		Map documentosMap = new HashMap();
		
		try {
			Iterator documentosIterator = SessionServiceLocator.getDocumentoSessionService().findDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (documentosIterator.hasNext()) {
				DocumentoIf documento = (DocumentoIf) documentosIterator.next();
				documentosMap.put(documento.getId(), documento);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear tipos de documentos", SpiritAlert.ERROR);
		}
		
		return documentosMap;
	}
	
	private Map mapearModulos() {
		Map modulosMap = new HashMap();
		
		try {
			Iterator modulosIterator = SessionServiceLocator.getModuloSessionService().getModuloList().iterator();
			while (modulosIterator.hasNext()) {
				ModuloIf modulo = (ModuloIf) modulosIterator.next();
				modulosMap.put(modulo.getId(), modulo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear módulos", SpiritAlert.ERROR);
		}
		
		return modulosMap;
	}
	
	private Map mapearCarterasMap(java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		Map carterasMap = new HashMap();
		
		try {
			Iterator carterasIterator = SessionServiceLocator.getCarteraSessionService().findCarteraByFechaInicioFechaFin(fechaInicio, fechaFin, Parametros.getIdEmpresa()).iterator();
			while (carterasIterator.hasNext()) {
				CarteraIf cartera = (CarteraIf) carterasIterator.next();
				carterasMap.put(cartera.getId(), cartera);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapeartransacciones", SpiritAlert.ERROR);
		}
		return carterasMap;
	}
	
	private Map mapearCarteraDetallesMap(java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		Map carteraDetallesMap = new HashMap();
		
		try {
			Iterator carteraDetallesIterator = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByFechaInicialByFechaFinalAndEmpresaId(fechaInicio, fechaFin, Parametros.getIdEmpresa()).iterator();
			while (carteraDetallesIterator.hasNext()) {
				CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) carteraDetallesIterator.next();
				Map detallesPorCarteraIdMap = (Map) carteraDetallesMap.get(carteraDetalle.getCarteraId());
				if (detallesPorCarteraIdMap == null)
					detallesPorCarteraIdMap = new HashMap();
				detallesPorCarteraIdMap.put(carteraDetalle.getId(), carteraDetalle);
				carteraDetallesMap.put(carteraDetalle.getCarteraId(), detallesPorCarteraIdMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear detalles de transacciones", SpiritAlert.ERROR);
		}
		return carteraDetallesMap;
	}
	
	private ArrayList<FondoRotativoEgresoData> ordenarEgresosConciliacionFondoRotativo(ArrayList<FondoRotativoEgresoData> egresosConciliacionFondoRotativo) {
		for (int i=0; i<egresosConciliacionFondoRotativo.size(); i++) {
			for (int j=i+1; j<egresosConciliacionFondoRotativo.size(); j++) {
				String numeroChequeI = egresosConciliacionFondoRotativo.get(i).getCheque();
				numeroChequeI = (numeroChequeI != null && !numeroChequeI.equals(""))?numeroChequeI.trim():"0";
				String numeroChequeJ = egresosConciliacionFondoRotativo.get(j).getCheque();
				numeroChequeJ = (numeroChequeJ != null && !numeroChequeJ.equals(""))?numeroChequeJ.trim():"0";
				if (numeroChequeI != null && !numeroChequeI.equals("D/B") && !numeroChequeI.equals("")) {
					if (numeroChequeJ != null && !numeroChequeJ.equals("D/B") && !numeroChequeJ.equals("")) {
						try {
							if (numeroChequeI.compareTo(numeroChequeJ) > 0) {
								FondoRotativoEgresoData auxiliar = (FondoRotativoEgresoData) DeepCopy.copy(egresosConciliacionFondoRotativo.get(j));
								egresosConciliacionFondoRotativo.set(j, (FondoRotativoEgresoData) DeepCopy.copy(egresosConciliacionFondoRotativo.get(i)));
								egresosConciliacionFondoRotativo.set(i, auxiliar);
							}
						} catch (java.lang.NumberFormatException e) {
							System.out.println("WARNING: Formato incorrecto número de cheque");
						}
					}
				}
			}
		}
		return egresosConciliacionFondoRotativo;
	}
	
	private ArrayList<FondoRotativoIngresoData> ordenarIngresosConciliacionFondoRotativo(ArrayList<FondoRotativoIngresoData> ingresosConciliacionFondoRotativo) {
		for (int i=0; i<ingresosConciliacionFondoRotativo.size(); i++) {
			for (int j=i+1; j<ingresosConciliacionFondoRotativo.size(); j++) {
				String codigoComprobanteI = ingresosConciliacionFondoRotativo.get(i).getCodigoComprobante();
				codigoComprobanteI = (codigoComprobanteI != null && !codigoComprobanteI.equals(""))?codigoComprobanteI.trim():SpiritConstants.getBlankSpaceCharacter();
				String codigoComprobanteJ = ingresosConciliacionFondoRotativo.get(j).getCodigoComprobante();
				codigoComprobanteJ = (codigoComprobanteJ != null && !codigoComprobanteJ.equals(""))?codigoComprobanteJ.trim():SpiritConstants.getBlankSpaceCharacter();
				if (codigoComprobanteI != null && !codigoComprobanteI.equals("")) {
					if (codigoComprobanteJ != null && !codigoComprobanteJ.equals("")) {
						try {
							if (codigoComprobanteI.compareTo(codigoComprobanteJ) > 0) {
								FondoRotativoIngresoData auxiliar = (FondoRotativoIngresoData) DeepCopy.copy(ingresosConciliacionFondoRotativo.get(j));
								ingresosConciliacionFondoRotativo.set(j, (FondoRotativoIngresoData) DeepCopy.copy(ingresosConciliacionFondoRotativo.get(i)));
								ingresosConciliacionFondoRotativo.set(i, auxiliar);
							}
						} catch (java.lang.NumberFormatException e) {
							System.out.println("WARNING: Formato incorrecto código de comprobante");
						}
					}
				}
			}
		}
		return ingresosConciliacionFondoRotativo;
	}
	
	private ArrayList<FondoRotativoFacturaCanceladaData> ordenarFacturasCanceladasConciliacionFondoRotativo(ArrayList<FondoRotativoFacturaCanceladaData> facturasCanceladasConciliacionFondoRotativo) {
		for (int i=0; i<facturasCanceladasConciliacionFondoRotativo.size(); i++) {
			for (int j=i+1; j<facturasCanceladasConciliacionFondoRotativo.size(); j++) {
				String codigoComprobanteI = facturasCanceladasConciliacionFondoRotativo.get(i).getCodigoComprobante();
				codigoComprobanteI = (codigoComprobanteI != null && !codigoComprobanteI.equals(""))?codigoComprobanteI.trim():SpiritConstants.getBlankSpaceCharacter();
				String codigoComprobanteJ = facturasCanceladasConciliacionFondoRotativo.get(j).getCodigoComprobante();
				codigoComprobanteJ = (codigoComprobanteJ != null && !codigoComprobanteJ.equals(""))?codigoComprobanteJ.trim():SpiritConstants.getBlankSpaceCharacter();
				if (codigoComprobanteI != null && !codigoComprobanteI.equals("")) {
					if (codigoComprobanteJ != null && !codigoComprobanteJ.equals("")) {
						try {
							if (codigoComprobanteI.compareTo(codigoComprobanteJ) > 0) {
								FondoRotativoFacturaCanceladaData auxiliar = (FondoRotativoFacturaCanceladaData) DeepCopy.copy(facturasCanceladasConciliacionFondoRotativo.get(j));
								facturasCanceladasConciliacionFondoRotativo.set(j, (FondoRotativoFacturaCanceladaData) DeepCopy.copy(facturasCanceladasConciliacionFondoRotativo.get(i)));
								facturasCanceladasConciliacionFondoRotativo.set(i, auxiliar);
							}
						} catch (java.lang.NumberFormatException e) {
							System.out.println("WARNING: Formato incorrecto código de comprobante");
						}
					}
				}
			}
		}
		return facturasCanceladasConciliacionFondoRotativo;
	}
	
	private ArrayList<FondoRotativoFacturaPendienteData> ordenarFacturasPendientesConciliacionFondoRotativo(ArrayList<FondoRotativoFacturaPendienteData> facturasPendientesConciliacionFondoRotativo) {
		for (int i=0; i<facturasPendientesConciliacionFondoRotativo.size(); i++) {
			for (int j=i+1; j<facturasPendientesConciliacionFondoRotativo.size(); j++) {
				String codigoComprobanteI = facturasPendientesConciliacionFondoRotativo.get(i).getCodigoComprobante();
				codigoComprobanteI = (codigoComprobanteI != null && !codigoComprobanteI.equals(""))?codigoComprobanteI.trim():SpiritConstants.getBlankSpaceCharacter();
				String codigoComprobanteJ = facturasPendientesConciliacionFondoRotativo.get(j).getCodigoComprobante();
				codigoComprobanteJ = (codigoComprobanteJ != null && !codigoComprobanteJ.equals(""))?codigoComprobanteJ.trim():SpiritConstants.getBlankSpaceCharacter();
				if (codigoComprobanteI != null && !codigoComprobanteI.equals("")) {
					if (codigoComprobanteJ != null && !codigoComprobanteJ.equals("")) {
						try {
							if (codigoComprobanteI.compareTo(codigoComprobanteJ) > 0) {
								FondoRotativoFacturaPendienteData auxiliar = (FondoRotativoFacturaPendienteData) DeepCopy.copy(facturasPendientesConciliacionFondoRotativo.get(j));
								facturasPendientesConciliacionFondoRotativo.set(j, (FondoRotativoFacturaPendienteData) DeepCopy.copy(facturasPendientesConciliacionFondoRotativo.get(i)));
								facturasPendientesConciliacionFondoRotativo.set(i, auxiliar);
							}
						} catch (java.lang.NumberFormatException e) {
							System.out.println("WARNING: Formato incorrecto código de comprobante");
						}
					}
				}
			}
		}
		return facturasPendientesConciliacionFondoRotativo;
	}
	
	private ArrayList<FondoRotativoEgresoData> ordenarChequesEmitidosConciliacionFondoRotativo(ArrayList<FondoRotativoEgresoData> chequesEmitidosConciliacionFondoRotativo) {
		for (int i=0; i<chequesEmitidosConciliacionFondoRotativo.size(); i++) {
			for (int j=i+1; j<chequesEmitidosConciliacionFondoRotativo.size(); j++) {
				String numeroChequeI = chequesEmitidosConciliacionFondoRotativo.get(i).getCheque();
				numeroChequeI = (numeroChequeI != null && !numeroChequeI.equals(""))?numeroChequeI.trim():"0";
				String numeroChequeJ = chequesEmitidosConciliacionFondoRotativo.get(j).getCheque();
				numeroChequeJ = (numeroChequeJ != null && !numeroChequeJ.equals(""))?numeroChequeJ.trim():"0";
				if (numeroChequeI != null && !numeroChequeI.equals("D/B") && !numeroChequeI.equals("")) {
					if (numeroChequeJ != null && !numeroChequeJ.equals("D/B") && !numeroChequeJ.equals("")) {
						try {
							if (numeroChequeI.compareTo(numeroChequeJ) > 0) {
								FondoRotativoEgresoData auxiliar = (FondoRotativoEgresoData) DeepCopy.copy(chequesEmitidosConciliacionFondoRotativo.get(j));
								chequesEmitidosConciliacionFondoRotativo.set(j, (FondoRotativoEgresoData) DeepCopy.copy(chequesEmitidosConciliacionFondoRotativo.get(i)));
								chequesEmitidosConciliacionFondoRotativo.set(i, auxiliar);
							}
						} catch (java.lang.NumberFormatException e) {
							System.out.println("WARNING: Formato incorrecto número de cheque");
						}
					}
				}
			}
		}
		return chequesEmitidosConciliacionFondoRotativo;
	}
}
