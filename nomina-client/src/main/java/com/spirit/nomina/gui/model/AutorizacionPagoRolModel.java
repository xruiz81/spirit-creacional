package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jidesoft.docking.DockableFrame;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.MainFrameModel;
import com.spirit.client.NumberCellRenderer;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritModel;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.gui.controller.PanelHandler;
import com.spirit.general.util.Contratos;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.RubroEventualEJB;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.datos.AutorizacionPagoRolRubrosEventualesData;
import com.spirit.nomina.gui.panel.JPAutorizacionPagoRol;
import com.spirit.nomina.gui.util.EstadoRolPago;
import com.spirit.nomina.gui.util.ModelUtil;
import com.spirit.nomina.gui.util.TipoContratoUtil;
import com.spirit.nomina.gui.util.TipoRolUtil;
import com.spirit.nomina.handler.EstadoRolPagoDetalle;
import com.spirit.nomina.handler.EstadoRubroEventual;
import com.spirit.nomina.handler.TipoRol;
import com.spirit.nomina.handler.TipoRolFormaPago;
import com.spirit.nomina.handler.TipoRolProvisionado;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class AutorizacionPagoRolModel extends JPAutorizacionPagoRol {

	private static final long serialVersionUID = 2260850962611203314L;

	private boolean filasSeleccionadas = false;
	private String textoSeleccionarTodo = "Seleccionar Todo";
	private String textoDeseleccionarTodo = "Deseleccionar Todo";
	
	
	private ArrayList<RolPagoIf> rolPagoIfVector = null;
	private RolPagoIf rolPagoIf = null;
	private TipoRolIf tipoRolIf = null;
	private TipoContratoIf tipoContratoIf = null;

	private Map<Long,TipoContratoIf> mapaTiposContrato = new HashMap<Long,TipoContratoIf>();
	private Map<Long,TipoRolIf> mapaTiposRol = new HashMap<Long,TipoRolIf>();
	
	private ArrayList<Map<String, Object>> rolPagoDetalleList = null;
	private Collection<Map<String,Object>> rolPagoDetalleSeleccionadosList = null;
	private JTable tablaSeleccionada =null;
	
	private Map<String, CuentaBancariaIf> cuentaBancariaMap = null;
	private Map<Long, String> cuentaBancariaLongMap = null;
	private Map<String, TipoPagoIf> tipoPagoMap = null;
	private Map<Long, TipoPagoIf> tipoPagoLongMap = null;
	boolean tablaQuincenalMensualSeleccionada = false;
	
	private Calendar calInicio = null;
	private Calendar calFin = null;
	
	private static Map panels;
	
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	private static final int COLUMNA_DETALLE_SELECCION = 0;
	private static final int COLUMNA_DETALLE_NOMBRE = 1;
	
	private static final int COLUMNA_DETALLE_QYM_INGRESOS = 2;
	private static final int COLUMNA_DETALLE_QYM_EGRESOS = 3;
	private static final int COLUMNA_DETALLE_QYM_TOTAL = 4;
	private static final int COLUMNA_DETALLE_QYM_TIPO_PAGO = 5;
	private static final int COLUMNA_DETALLE_QYM_CUENTA_BANCARIA = 6;
	private static final int COLUMNA_DETALLE_QYM_NUMERO_CHEQUE = 7;
	
	private static final int COLUMNA_DETALLE_AYD_VALOR = 2;
	private static final int COLUMNA_DETALLE_AYD_ANTICIPOS = 3;
	private static final int COLUMNA_DETALLE_AYD_TOTAL = 4;
	private static final int COLUMNA_DETALLE_AYD_TIPO_PAGO = 5;
	private static final int COLUMNA_DETALLE_AYD_CUENTA_BANCARIA = 6;
	private static final int COLUMNA_DETALLE_AYD_NUMERO_CHEQUE = 7;
	
	private static final int COLUMNA_DETALLE_ANT_TOTAL = 2;
	private static final int COLUMNA_DETALLE_ANT_TIPO_PAGO = 3;
	private static final int COLUMNA_DETALLE_ANT_CUENTA_BANCARIA = 4;
	private static final int COLUMNA_DETALLE_ANT_NUMERO_CHEQUE = 5;
	private static String NOMBRE_MENU_NOMINA = "NOMINA";
	

	public AutorizacionPagoRolModel(){
		panels = MainFrameModel.get_panels();
		iniciarComponentes();
		setAnchoComlumnas();
		initListener();
		getRbNormal().setSelected(false);
		showSaveMode();
		getRbNormal().setSelected(true);
		new HotKeyComponent(this);
	}

	private void iniciarComponentes(){
		
		//COMBOS DE FECHA
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(true);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setShowNoneButton(true);
		getCmbFechaFin().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaFin().setEditable(false);
		
		ocultarTablasDetalle();
		
		//getPanelRolPagoDetalle().add(getSpTblRolPagoDetalleAyD(), new CellConstraints().xywh(1, 7, 3, 3));
		//getPanelRolPagoDetalle().add(getSpTblRolPagoDetalleAnticipos(), new CellConstraints().xywh(1, 7, 3, 3));
		getPanelDetalleRolPago().add(getSpTblRolPagoDetalleAyD(), new CellConstraints().xywh(1, 7, 3, 3));
		getPanelDetalleRolPago().add(getSpTblRolPagoDetalleAnticipos(), new CellConstraints().xywh(1, 7, 3, 3));
		
		//CheckBox tipo rubro
		getRbNormal().setSelected(true);
		
		//Combo tipo de contrato
		//getCmbTipoContrato().setSelectedItem(null);
		//ModelUtil.cargarCmbTipoContrato(getCmbTipoContrato());
		
		//Tabla Rol de Pago
		setSorterTable(getTblRolPago());

		//Tabla Rol de Pago DEtalle Quincenal y Mensual 
		setSorterTable(getTblRolPagoDetalleQyM());
		//getTblRolPagoDetalleQyM().getTableHeader().setReorderingAllowed(false);
		getTblRolPagoDetalleQyM().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumnModel modeloColumna = getTblRolPagoDetalleQyM().getColumnModel(); 
		modeloColumna.getColumn(COLUMNA_DETALLE_QYM_INGRESOS).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		modeloColumna.getColumn(COLUMNA_DETALLE_QYM_EGRESOS).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA));
		modeloColumna.getColumn(COLUMNA_DETALLE_QYM_TOTAL).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA));
		//modeloColumna.getColumn(3).setCellRenderer(new TableCellRendererHorizontalRightAlignment());
		//modeloColumna.getColumn(4).setCellRenderer(new TableCellRendererHorizontalRightAlignment());


		cargarTipoPagos();
		cargarCuentasBancarias();
		
		cargarComboTipoPago(getCmbTipoPagoTodos());
		cargarComboCuentaBancaria(getCmbCuentaBancariaTodos());
		
		JComboBox comboTipoPago = new JComboBox();
		cargarComboTipoPago(comboTipoPago);
		getTblRolPagoDetalleQyM().getColumnModel().getColumn(COLUMNA_DETALLE_QYM_TIPO_PAGO)
			.setCellEditor(new DefaultCellEditor(comboTipoPago));
		getTblRolPagoDetalleAportesDecimos().getColumnModel().getColumn(COLUMNA_DETALLE_AYD_TIPO_PAGO)
			.setCellEditor(new DefaultCellEditor(comboTipoPago));
		getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_TIPO_PAGO)
			.setCellEditor(new DefaultCellEditor(comboTipoPago));
		
		//Tabla anticipos
		getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_TOTAL).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA));
		
		//Tabla Rol de Pago DEtalle Aportes y Decimos
		setSorterTable(getTblRolPagoDetalleAportesDecimos());
		getTblRolPagoDetalleAportesDecimos().getTableHeader().setReorderingAllowed(false);
		getTblRolPagoDetalleAportesDecimos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblRolPagoDetalleAportesDecimos().getColumnModel().getColumn(COLUMNA_DETALLE_AYD_VALOR).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA));

		JComboBox comboCuentasBancarias = new JComboBox(); 
		cargarComboCuentaBancaria(comboCuentasBancarias);
		getTblRolPagoDetalleQyM().getColumnModel().getColumn(COLUMNA_DETALLE_QYM_CUENTA_BANCARIA)
			.setCellEditor(new DefaultCellEditor(comboCuentasBancarias));
		getTblRolPagoDetalleAportesDecimos().getColumnModel().getColumn(COLUMNA_DETALLE_AYD_CUENTA_BANCARIA)
			.setCellEditor(new DefaultCellEditor(comboCuentasBancarias));
		getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_CUENTA_BANCARIA)
			.setCellEditor(new DefaultCellEditor(comboCuentasBancarias));
		
		//INI_CAMBIO_20140903 En principio los objetos para generacion de totales no sera visible  
		//hasta que se cargue la tabla rol pago detalle en pantalla
		getBtnGeneraTotal().setVisible(false);
		getLblTotalDebitos().setVisible(false);
		getTxtTotalDebitos().setVisible(false);
		getLblTotalCheques().setVisible(false);
		getTxtTotalCheques().setVisible(false);
		//FIN_CAMBIO_20140903
	}
	
	private void cargarComboTipoPago(JComboBox comboTipoPago){
		comboTipoPago.setModel(new DefaultComboBoxModel());
		llenarComboTipoPagoTodos(comboTipoPago);
	}
	
	private void cargarComboCuentaBancaria(JComboBox comboCuentasBancarias){
		comboCuentasBancarias.setModel(new DefaultComboBoxModel());
		llenarComboCuentaBancariaTodos(comboCuentasBancarias);
	}

	private JComboBox llenarComboCuentaBancariaTodos(JComboBox comboCuentasBancarias) {
		for ( String cuenta : cuentaBancariaMap.keySet() ){
			comboCuentasBancarias.addItem(cuenta);
		}
		//if ( comboCuentasBancarias.getModel().getSize() > 0 )
		//	comboCuentasBancarias.setSelectedIndex(0);
		return comboCuentasBancarias;
	}

	private JComboBox llenarComboTipoPagoTodos(JComboBox comboTipoPago) {
		for (String tipoPagoNombre : tipoPagoMap.keySet()){
			TipoPagoIf tipoPago = tipoPagoMap.get(tipoPagoNombre);
			comboTipoPago.addItem(tipoPago);
		}
		return comboTipoPago;
	}

	private void cargarCuentasBancarias(){
		try {
			cuentaBancariaMap = null;
			cuentaBancariaMap = new HashMap<String, CuentaBancariaIf>();
			cuentaBancariaLongMap = null;
			cuentaBancariaLongMap = new HashMap<Long, String>();
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			parameterMap.put("cuentaCliente", "N");
			Collection<CuentaBancariaIf> cuentasBancarias = SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByQuery(parameterMap);
			for ( CuentaBancariaIf cuentaBancaria : cuentasBancarias ){
				BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancaria.getBancoId());
				cuentaBancariaMap.put(ModelUtil.nombreCuentaBanco(banco, cuentaBancaria), cuentaBancaria);
				cuentaBancariaLongMap.put(cuentaBancaria.getId(), ModelUtil.nombreCuentaBanco(banco, cuentaBancaria));
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la carga de Cuentas Bancarias !!", SpiritAlert.ERROR);
		}
	}

	private void cargarTipoPagos(){
		try {
			tipoPagoMap = null;
			tipoPagoMap =  new HashMap<String, TipoPagoIf>();
			tipoPagoLongMap = null;
			tipoPagoLongMap =  new HashMap<Long, TipoPagoIf>();
			Collection<TipoPagoIf> tipos = SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByEmpresaId(Parametros.getIdEmpresa());
			for ( TipoPagoIf tipo : tipos ){
				if ( tipo.getNombre().contains("DEBITO") || tipo.getNombre().contains("DÉBITO") )
					tipoPagoMap.put(tipo.getNombre(), tipo);
				else if ( tipo.getNombre().contains("CHEQUE") )
					tipoPagoMap.put(tipo.getNombre(), tipo);
				tipoPagoLongMap.put(tipo.getId(), tipo);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la carga de tipos de pagos !!", SpiritAlert.ERROR);
		}
	}
	
	private void setAnchoComlumnas(){
		//TABLA DE QUINCENA Y MENSUAL 
		TableColumnModel modelo = getTblRolPagoDetalleQyM().getColumnModel();
		modelo.getColumn(COLUMNA_DETALLE_SELECCION).setPreferredWidth(45);
		modelo.getColumn(COLUMNA_DETALLE_NOMBRE).setPreferredWidth(280);
		modelo.getColumn(COLUMNA_DETALLE_ANT_TOTAL).setPreferredWidth(95);
		modelo.getColumn(3).setPreferredWidth(95);	
		modelo.getColumn(4).setPreferredWidth(95);
		modelo.getColumn(COLUMNA_DETALLE_QYM_TIPO_PAGO).setPreferredWidth(130);
		modelo.getColumn(COLUMNA_DETALLE_QYM_CUENTA_BANCARIA).setPreferredWidth(250);
		modelo.getColumn(COLUMNA_DETALLE_QYM_NUMERO_CHEQUE).setPreferredWidth(120);

		//TABLA DE APORTES Y DECIMOS
		modelo = getTblRolPagoDetalleAportesDecimos().getColumnModel();
		modelo.getColumn(COLUMNA_DETALLE_SELECCION).setPreferredWidth(45);
		modelo.getColumn(COLUMNA_DETALLE_NOMBRE).setPreferredWidth(280);	
		modelo.getColumn(COLUMNA_DETALLE_AYD_VALOR).setPreferredWidth(95);
		modelo.getColumn(COLUMNA_DETALLE_AYD_ANTICIPOS).setPreferredWidth(95);
		modelo.getColumn(COLUMNA_DETALLE_AYD_TOTAL).setPreferredWidth(95);
		modelo.getColumn(COLUMNA_DETALLE_AYD_TIPO_PAGO).setPreferredWidth(130);
		modelo.getColumn(COLUMNA_DETALLE_AYD_CUENTA_BANCARIA).setPreferredWidth(250);
		modelo.getColumn(COLUMNA_DETALLE_AYD_NUMERO_CHEQUE).setPreferredWidth(120);
		
		//TABLA DE ANTICIPOS
		modelo = getTblRolPagoDetalleAnticipos().getColumnModel(); 
		modelo.getColumn(COLUMNA_DETALLE_SELECCION).setPreferredWidth(45);
		modelo.getColumn(COLUMNA_DETALLE_NOMBRE).setPreferredWidth(300);
		modelo.getColumn(COLUMNA_DETALLE_ANT_TOTAL).setPreferredWidth(95);
		modelo.getColumn(COLUMNA_DETALLE_ANT_TIPO_PAGO).setPreferredWidth(130);
		modelo.getColumn(COLUMNA_DETALLE_ANT_CUENTA_BANCARIA).setPreferredWidth(250);
		modelo.getColumn(COLUMNA_DETALLE_ANT_NUMERO_CHEQUE).setPreferredWidth(120);
		
	}

	private void initListener(){
		getBtnSeleccionarTodos().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				seleccionarTodo();
			}
		});
		
		//INI_CAMBIO_20140901 Se añade listener para boton que genera los totales en la 
		//autorizacion de pagos
		getBtnGeneraTotal().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				totalesAutorizacionDebitoCheque();
			}
		});
		//FIN_CAMBIO_20140901
		
		getTblRolPago().addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent arg0) {
				seleccionarFila();
			}			
		});

		getTblRolPago().addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent arg0) {
				seleccionarFila();
			}
		});
		
		getBtnFiltrar().addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent arg0) {
				filtrarBusquedaRolPago();
			}
		});
		
		getBtnTipoPagoTodos().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				seleccionarTipoPagoTodos();
			}
		});
		
		getBtnCuentaBancariaTodos().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				seleccionarCuentaBancariaTodos();
			}
		});
		
		//POPUP PARA VER DETALLE DE RUBRO POR CONTRATO 
		JPopupMenu popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("<html><font color=red>Detalle de Contrato</font></html>");
		menuItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				try {
					if(!getRbAnticipos().isSelected()){
						visualizarRubrosPorEmpleado();
					}else{
						visualizarDetallesRubroEventual();
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				} catch(Exception e){
					e.printStackTrace();
					SpiritAlert.createAlert("Error general al abrir panel de Rol de Pago por Contrato !!", SpiritAlert.ERROR);
				}
			}
		});
		popup.add(menuItem);
		PopupListener ppl = new PopupListener(popup);
		getTblRolPagoDetalleQyM().addMouseListener(ppl);
		getTblRolPagoDetalleAportesDecimos().addMouseListener(ppl);
		getTblRolPagoDetalleAnticipos().addMouseListener(ppl);

		getRbNormal().addActionListener(alRbTipoRubro);
		getRbAnticipos().addActionListener(alRbTipoRubro);
		getRbBeneficiosSociales().addActionListener(alRbTipoRubro);
		
		//INI_CAMBIO_20140903 Se configura opciones para cuando se selecciona anticipo, o beneficios 
		//sociales al momento de realizar la autorizacion de los pagos
		getRbAnticipos().addChangeListener(clVisualizaTotales);
		getRbBeneficiosSociales().addChangeListener(clVisualizaTotales);
		//FIN_CAMBIO_20140903
	}
	
	//INI_CAMBIO_20140903 Si se selecciona un anticipo o un beneficio social en una autorizacion de 
	//pagos las opciones para generar totales no seran visibles
	ChangeListener clVisualizaTotales =  new ChangeListener(){
		public void stateChanged(ChangeEvent e) {
			if ( getRbAnticipos().isSelected() || getRbBeneficiosSociales().isSelected()){
				getBtnGeneraTotal().setVisible(false);
				getLblTotalDebitos().setVisible(false);
				getTxtTotalDebitos().setVisible(false);
				getLblTotalCheques().setVisible(false);
				getTxtTotalCheques().setVisible(false);
			}
		}
	};
	//FIN_CAMBIO_20140903
	
	Comparator<AutorizacionPagoRolRubrosEventualesData> ordenadorRubroEventualDataPorCodigo = new Comparator<AutorizacionPagoRolRubrosEventualesData>(){
		public int compare(AutorizacionPagoRolRubrosEventualesData o1, AutorizacionPagoRolRubrosEventualesData o2) {
			return o1.getCodigoContrato().compareTo(o2.getCodigoContrato());
		}		
	};
	
	private void visualizarDetallesRubroEventual() throws GenericBusinessException {
		int filaSeleccionada = tablaSeleccionada.getSelectedRow();
		if (filaSeleccionada >= 0) {
			int fila = tablaSeleccionada.convertRowIndexToModel(filaSeleccionada);
			Map<String, Object> mapa = rolPagoDetalleList.get(fila);

			// saco los detalles de los rubros eventuales
			ArrayList<RubroEventualEJB> detallesRubrosEventuales = (ArrayList<RubroEventualEJB>) mapa.get("detalles");

			ArrayList<AutorizacionPagoRolRubrosEventualesData> detallesReporte = new ArrayList<AutorizacionPagoRolRubrosEventualesData>();

			// lleno el arreglo de datos para el reporte
			for (RubroEventualIf rubroEventual : detallesRubrosEventuales) {
				AutorizacionPagoRolRubrosEventualesData autorizacionPagoRolRubrosEventualesData = new AutorizacionPagoRolRubrosEventualesData();
				RubroIf rubro = SessionServiceLocator.getRubroSessionService().getRubro(rubroEventual.getRubroId());
				autorizacionPagoRolRubrosEventualesData.setNombreRubro(rubro.getNombre());
				ContratoIf contrato = SessionServiceLocator.getContratoSessionService().getContrato(rubroEventual.getContratoId());
				autorizacionPagoRolRubrosEventualesData.setCodigoContrato(contrato.getCodigo());
				autorizacionPagoRolRubrosEventualesData.setObservacion(rubroEventual.getObservacion());
				autorizacionPagoRolRubrosEventualesData.setValor(rubroEventual.getValor());
				detallesReporte.add(autorizacionPagoRolRubrosEventualesData);
			}

			// ordeno el arreglo por codigo de contrato
			Collections.sort((ArrayList) detallesReporte, ordenadorRubroEventualDataPorCodigo);

			generarReporteRubrosEventuales(detallesReporte);

		} else {
			SpiritAlert.createAlert("Por favor seleccione una fila !!", SpiritAlert.INFORMATION);
		}
	}
	
	public void generarReporteRubrosEventuales(ArrayList<AutorizacionPagoRolRubrosEventualesData> detallesReporte){
		try {
			if (detallesReporte.size() > 0) {				
				//String si = "Si";
				//String no = "No";
				//Object[] options = {si,no};
				//String mensaje = "¿Desea generar el reporte de Rubros Eventuales?";
				//int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				//if(opcion == JOptionPane.YES_OPTION) {
					
					String fileName = "jaspers/nomina/RPAutorizacionPagoRubrosEventuales.jasper";
									
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_NOMINA).iterator().next();
					parametrosMap.put("codigoReporte", menu.getCodigo());
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
					
					ReportModelImpl.processReport(fileName, parametrosMap, detallesReporte, true);
				//}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}
	
	private void filtrarBusquedaRolPago() {
		try{
			calInicio = null;
			calFin = null;
			
			//tipoContratoIf = (TipoContratoIf)getCmbTipoContrato().getSelectedItem();
			
			if ( getCmbFechaInicio().getDate() != null ){
				calInicio = new GregorianCalendar();
				calInicio.setTime( getCmbFechaInicio().getDate() );
				calInicio.set(Calendar.DAY_OF_MONTH, 1);
				if ( getCmbFechaFin().getDate() != null ){
					calFin = new GregorianCalendar();
					calFin.setTime( getCmbFechaFin().getDate() );
					calFin.set(Calendar.DAY_OF_MONTH, calFin.getActualMaximum(Calendar.DAY_OF_MONTH));
					if ( calFin.compareTo(calInicio) < 0 ){
						SpiritAlert.createAlert("Fecha Fin tiene que ser mayor a Fecha Inicio !!", SpiritAlert.ERROR);
						return;
					}
				} else {
					calFin = new GregorianCalendar();
					calFin.set(Calendar.DAY_OF_MONTH, calFin.getActualMaximum(Calendar.DAY_OF_MONTH));
					if ( calFin.compareTo(calInicio) < 0 ){
						calFin.setTime(calInicio.getTime());
						calFin.set(Calendar.YEAR, calFin.get(Calendar.YEAR)+1);
					}
				}
			}
			
			limpiarTabla(getTblRolPago());
			limpiarTabla(getTblRolPagoDetalleQyM());
			limpiarTabla(getTblRolPagoDetalleAportesDecimos());
			cargarTablaRolPago();
			
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al filtar informacion para busqueda !!", SpiritAlert.ERROR);
		}
	}
	
	private void seleccionarTodo(){
		
		if ( tablaSeleccionada != null ){
			DefaultTableModel modelo  = (DefaultTableModel) tablaSeleccionada.getModel();
			if ( filasSeleccionadas ){
				filasSeleccionadas = false;
				getBtnSeleccionarTodos().setText(textoSeleccionarTodo);
				for ( int i=0 ; i < modelo.getRowCount() ; i++ ){
					modelo.setValueAt(false, i, COLUMNA_DETALLE_SELECCION);
				}
				return;
			}
			else{ 
				filasSeleccionadas = true;
				getBtnSeleccionarTodos().setText(textoDeseleccionarTodo);
			}
			
			int columnaValor = 0;
			if ( getRbNormal().isSelected() ){
				columnaValor = COLUMNA_DETALLE_QYM_TOTAL;
			} else if ( getRbAnticipos().isSelected() ){
				columnaValor = COLUMNA_DETALLE_ANT_TOTAL;
			} else if ( getRbBeneficiosSociales().isSelected() ){
				columnaValor = COLUMNA_DETALLE_AYD_TOTAL;
			} else
				SpiritAlert.createAlert("Tipo de Rol no considerado para presentación en tabla de detalle !!", SpiritAlert.WARNING);
						
			for ( int i = 0 ; i < modelo.getRowCount() ; i++  ){
				//modelo.setValueAt(filasSeleccionadas, i, COLUMNA_DETALLE_SELECCION);
				Object valor = modelo.getValueAt(i, columnaValor);
				if ( valor != null ){
					if ( valor instanceof Double || valor instanceof BigDecimal ){
						Double valorD = valor instanceof Double ? (Double)valor : ((BigDecimal)valor).doubleValue() ;
						if ( valorD > 0 )
							modelo.setValueAt(true, i, COLUMNA_DETALLE_SELECCION);
					} else if ( valor instanceof String ){
						String valorS = (String)valor;
						if ( !"".equals(valorS.trim()) ){
							Double valorSD = Double.valueOf(valorS);
							if ( valorSD > 0 )
								modelo.setValueAt(true, i, COLUMNA_DETALLE_SELECCION);
						}
					}
				}
			}
		}
	}
	
	private void seleccionarTipoPagoTodos(){
		DefaultTableModel modelo  = null;//(DefaultTableModel) tablaSeleccionada.getModel();
		int columnaTipoPago = -1;
		
		if ( getRbNormal().isSelected() ){
			if ( tablaQuincenalMensualSeleccionada ){
				modelo = (DefaultTableModel) getTblRolPagoDetalleQyM().getModel();
				columnaTipoPago = COLUMNA_DETALLE_QYM_TIPO_PAGO;
			}
		} else if ( getRbAnticipos().isSelected() ){
			modelo = (DefaultTableModel) getTblRolPagoDetalleAnticipos().getModel();
			columnaTipoPago = COLUMNA_DETALLE_ANT_TIPO_PAGO;
		} else {
			modelo = (DefaultTableModel) getTblRolPagoDetalleAportesDecimos().getModel();
			columnaTipoPago = COLUMNA_DETALLE_AYD_TIPO_PAGO;
		}
		if ( columnaTipoPago == -1 )
			return;
		TipoPagoIf tp = (TipoPagoIf) getCmbTipoPagoTodos().getSelectedItem();
		for ( int i = 0 ; i < modelo.getRowCount() ; i++ ){
			modelo.setValueAt(tp, i, columnaTipoPago);
		}
	}
	
	private void seleccionarCuentaBancariaTodos(){
		DefaultTableModel modelo  = null;//(DefaultTableModel) tablaSeleccionada.getModel();
		int columnaCuentaBancaria = -1;
		
		if ( getRbNormal().isSelected() ){
			if ( tablaQuincenalMensualSeleccionada ){
				modelo = (DefaultTableModel) getTblRolPagoDetalleQyM().getModel();
				columnaCuentaBancaria = COLUMNA_DETALLE_QYM_CUENTA_BANCARIA;
			}
		} else if ( getRbAnticipos().isSelected() ){
			modelo = (DefaultTableModel) getTblRolPagoDetalleAnticipos().getModel();
			columnaCuentaBancaria = COLUMNA_DETALLE_ANT_CUENTA_BANCARIA;
		} else {
			modelo = (DefaultTableModel) getTblRolPagoDetalleAportesDecimos().getModel();
			columnaCuentaBancaria = COLUMNA_DETALLE_AYD_CUENTA_BANCARIA;
		}
		if ( columnaCuentaBancaria == -1 )
			return;
		String nombreCuentaBancaria = (String) getCmbCuentaBancariaTodos().getSelectedItem();
		for ( int i = 0 ; i < modelo.getRowCount() ; i++ ){
			modelo.setValueAt(nombreCuentaBancaria, i, columnaCuentaBancaria);
		}
	}
	
	class PopupListener extends MouseAdapter {
		JPopupMenu popupMenu = null;
		PopupListener(JPopupMenu popupMenu){
			this.popupMenu = popupMenu;
		}
		public void mousePressed(MouseEvent e) {
			showPopup(e);
		}
		public void mouseReleased(MouseEvent e) {
			showPopup(e);
		}
		private void showPopup(MouseEvent e) {
			if (e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON3) {
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	
	private void visualizarRubrosPorEmpleado() throws GenericBusinessException{
		int filaSeleccionada = tablaSeleccionada.getSelectedRow();
		if ( filaSeleccionada >= 0 ){
			int fila = tablaSeleccionada.convertRowIndexToModel(filaSeleccionada);
			Map<String, Object> mapa = rolPagoDetalleList.get(fila);
			Long contratoId = (Long) mapa.get("contratoId");
			//ContratoIf contratoIf = SessionServiceLocator.getContratoSessionService().getContrato(81L);
			ContratoIf contratoIf = SessionServiceLocator.getContratoSessionService().getContrato(contratoId);
			SpiritModel panelRolPagoPorContrato = (SpiritModel) new ConsultaRolContratoModel(tipoRolIf,rolPagoIf,contratoIf,false,null);
			
			if (panels.size()>0 && panels.get("Consulta Rol Por Contrato")!=null)
				MainFrameModel.get_dockingManager().removeFrame("Consulta Rol Por Contrato");
			
			DockableFrame panel = PanelHandler.createPanelesApp(panelRolPagoPorContrato);
			MainFrameModel.get_dockingManager().addFrame(panel);
			MainFrameModel.get_dockingManager().showFrame(panel.getName());
		} else{
			SpiritAlert.createAlert("Por favor seleccione una fila !!", SpiritAlert.INFORMATION);
		}
	}
	
	private ActionListener alRbTipoRubro = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			showSaveMode();
		}
	};

	private void seleccionarFila() {

		filasSeleccionadas = false;
		getBtnSeleccionarTodos().setText(textoSeleccionarTodo);
		
		Runnable rn = new Runnable(){
			public void run() {
				int filaSeleccionada = getTblRolPago().getSelectedRow();
				if ( filaSeleccionada >= 0 ){
					try {
						getTblRolPago().setEnabled(false);

						setCursorEspera();
						
						int fila = getTblRolPago().convertRowIndexToModel(filaSeleccionada);
						rolPagoIf = null;
						limpiarTabla( getTblRolPagoDetalleQyM() );
						limpiarTabla( getTblRolPagoDetalleAportesDecimos() );
						limpiarTabla( getTblRolPagoDetalleAnticipos() );
						rolPagoIf = rolPagoIfVector.get(fila);
						//tipoRolIf = SessionServiceLocator.getTipoRolSessionService().getTipoRol(rolPagoIf.getTiporolId());
						tipoRolIf = TipoRolUtil.verificarMapaTipoRol(mapaTiposRol, rolPagoIf.getTiporolId());
						//tipoContratoIf = SessionServiceLocator.getTipoContratoSessionService().getTipoContrato(rolPagoIf.getTipocontratoId());
						tipoContratoIf = TipoContratoUtil.verificarMapaTipoContrato(mapaTiposContrato, rolPagoIf.getTipocontratoId());
						
						cargarTablaRolPagoDetalle();
						showUpdateMode();
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
					} catch (Exception e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Error al obtener información de Rol de Pago", SpiritAlert.ERROR);
					} finally{
						getTblRolPago().setEnabled(true);
						setCursorNormal();
					}
				}
			}

		};
		Thread t = new Thread(rn);
		t.start();
		t = null;
	}

	/*private Comparator<RolPagoDetalleIf> comparadorContrato = new Comparator<RolPagoDetalleIf>(){
		public int compare(RolPagoDetalleIf o1, RolPagoDetalleIf o2) {
			return o1.getContratoId().compareTo(o2.getContratoId());
		}
	};*/

	private void cargarTablaRolPagoDetalle() throws GenericBusinessException{

		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		Collection<Long> contratosIdCollection = Contratos.obtenerContratosId(tipoRol,tipoContratoIf,rolPagoIf,EstadoRolPagoDetalle.EMITIDO.getLetra());
		rolPagoDetalleSeleccionadosList = null;
		rolPagoDetalleList = null;
		DefaultTableModel modelo = establecerTabla(tipoRol);
		
		//INI_CAMBIO_20140904
		habilitarOpcTotales();
		getTxtTotalDebitos().setText("");
		getTxtTotalCheques().setText("");
		//FIN_CAMBIO_20140904
		
		if ( modelo != null ){
		
			if ( getRbNormal().isSelected() ){
				rolPagoDetalleList = (ArrayList<Map<String, Object>>)SessionServiceLocator
					.getRolPagoSessionService().crearColeccionContratos(
						contratosIdCollection,rolPagoIf,
						EstadoRolPagoDetalle.EMITIDO.getLetra(),true,false,false,"",false);
						
			} else if ( getRbAnticipos().isSelected() ) {
				Map<String, Object> mapaAnticipos = new HashMap<String, Object>();
				
				rolPagoDetalleList = (ArrayList<Map<String, Object>>)SessionServiceLocator.getRubroEventualSessionService()
					.findRubroEventualByQueryByEstadosAgrupadosRubroByMesByAnioParaAutorizacion(
						rolPagoIf,mapaAnticipos,
						EstadoRubroEventual.EMITIDO.getLetra(),EstadoRubroEventual.AUTORIZADO_PARCIAL.getLetra() );
				
			} else if ( getRbBeneficiosSociales().isSelected() ) {
				rolPagoDetalleList = (ArrayList<Map<String, Object>>)SessionServiceLocator
					.getRolPagoSessionService().crearColeccionContratos(
						contratosIdCollection,rolPagoIf,
						EstadoRolPagoDetalle.EMITIDO.getLetra(),true,false,false,"",false);
			}
									
			if ( getRbNormal().isSelected() ){
					cargarTablaRolPagoDetalleQyM(rolPagoDetalleList, modelo);
					getTblRolPagoDetalleQyM().validate();
					getTblRolPagoDetalleQyM().repaint();
					tablaQuincenalMensualSeleccionada = true;
					
					//INI_CAMBIO_20140903 Una vez que se finalice la carga de la tabla rol pago detalle 
					//se cargan las opciones para generartotales para debitos y cheques
					getBtnGeneraTotal().setVisible(true);
					getLblTotalDebitos().setVisible(true);
					getTxtTotalDebitos().setVisible(true);
					getLblTotalCheques().setVisible(true);
					getTxtTotalCheques().setVisible(true);
					getTxtTotalDebitos().setEditable(false);
					getTxtTotalCheques().setEditable(false);
					//FIN_CAMBIO_20140903
				 
			} else if ( getRbAnticipos().isSelected() ) {
				cargarTablaRolPagoDetalleAnticipos(rolPagoDetalleList, modelo);
				getTblRolPagoDetalleAnticipos().validate();
				getTblRolPagoDetalleAnticipos().repaint();
				tablaQuincenalMensualSeleccionada = false;
			} else if ( tipoRol == TipoRol.DECIMO_TERCERO || tipoRol == TipoRol.DECIMO_CUARTO 
					|| tipoRol == TipoRol.VACACIONES  
					|| tipoRol == TipoRol.APORTE_PATRONAL ||  tipoRol == TipoRol.FONDO_RESERVA 
					|| tipoRol == TipoRol.UTILIDADES ){
				cargarTablaRolPagoDetalleAportesDecimos(rolPagoDetalleList, modelo);
				getTblRolPagoDetalleAportesDecimos().validate();
				getTblRolPagoDetalleAportesDecimos().repaint();
				tablaQuincenalMensualSeleccionada = false;
			} else 
				SpiritAlert.createAlert("Tipo de Rol no considerado para presentación en la tabla !!", SpiritAlert.WARNING);
		}
	}

	private void cargarTablaRolPagoDetalleQyM(
			Collection<Map<String, Object>> rolPagoContratoCollection, DefaultTableModel modelo)
	throws GenericBusinessException {
		for ( Map<String,Object> mapa : rolPagoContratoCollection ){
			Vector<Object> fila = crearFilaTablaRolPagoDetalleQyM(rolPagoIf,mapa);
			modelo.addRow(fila);

		}
	}

	private DefaultTableModel establecerTabla(TipoRol tipoRol){
		ocultarTablasDetalle();
		if ( tipoRol == TipoRol.QUINCENAL || tipoRol == TipoRol.MENSUAL ){
			if ( getRbNormal().isSelected() ){
				tablaSeleccionada = getTblRolPagoDetalleQyM();
				getSpTblRolPagoDetalleQyM().setVisible(true);
				return (DefaultTableModel)getTblRolPagoDetalleQyM().getModel();
			} else if ( getRbAnticipos().isSelected() ){
				tablaSeleccionada = getTblRolPagoDetalleAnticipos();
				getSpTblRolPagoDetalleAnticipos().setVisible(true);
				return (DefaultTableModel)getTblRolPagoDetalleAnticipos().getModel();
			} else{
				SpiritAlert.createAlert("Error en seleccion de tabla !!", SpiritAlert.WARNING);
			}
		} else if ( tipoRol == TipoRol.DECIMO_TERCERO || tipoRol == TipoRol.DECIMO_CUARTO 
				|| tipoRol == TipoRol.VACACIONES 
				|| tipoRol == TipoRol.APORTE_PATRONAL || tipoRol == TipoRol.FONDO_RESERVA ){
			tablaSeleccionada = getTblRolPagoDetalleAportesDecimos();
			getSpTblRolPagoDetalleAyD().setVisible(true);
			return (DefaultTableModel)getTblRolPagoDetalleAportesDecimos().getModel();
		} else
			SpiritAlert.createAlert("Tipo de Rol no considerado para presentación en tabla de detalle !!", SpiritAlert.WARNING);
		return null;
	}

	private Vector<Object> crearFilaTablaRolPagoDetalleQyM(RolPagoIf rolPagoIf,Map<String, Object> mapa)
	throws GenericBusinessException {
		String nombreEmpleado = (String)mapa.get("nombreEmpleado");
		Double totalIngresos = Utilitarios.redondeoValor( (Double)mapa.get("totalIngresos") );
		Double totalEgresos = Utilitarios.redondeoValor( (Double)mapa.get("totalDescuentos") );
		Double total = Utilitarios.redondeoValor( totalIngresos - totalEgresos );
		Vector<Object> fila = new Vector<Object>();
		fila.add(false);
		fila.add(nombreEmpleado);
		fila.add(totalIngresos);
		fila.add(totalEgresos);
		fila.add(total);
		fila.add("");
		fila.add("");
		fila.add("");
		return fila;
	}

	private void cargarTablaRolPagoDetalleAportesDecimos(Collection<Map<String, Object>> rolPagoContratoCollection, DefaultTableModel modelo)
	throws GenericBusinessException {
		for ( Map<String, Object> mapa : rolPagoContratoCollection){
			TipoRolFormaPago formaPago = TipoRolFormaPago.getTipoRolPagoByLetra(tipoRolIf.getFormaPago());
			String nombreMes = rolPagoIf.getMes()!= null ? Utilitarios.getNombreMes(Integer.valueOf(rolPagoIf.getMes())) : null;
			Vector<Object> fila = ModelUtil.crearFilaTablaRolPagoDetalleAportesDecimosPagos(
				nombreMes,formaPago,mapa, tipoPagoLongMap, cuentaBancariaLongMap);
			modelo.addRow(fila);
		}
	}

	private void cargarTablaRolPagoDetalleAnticipos(Collection<Map<String, Object>> rolPagoContratoCollection, DefaultTableModel modelo)
	throws GenericBusinessException {
		for ( Map<String, Object> mapa : rolPagoContratoCollection){
			if ( mapa.get("rubroId")!=null && mapa.get("nombreRubroEventual")!=null ){
				Vector<Object> fila = crearFilaTablaRolPagoDetalleAnticipos(mapa);
				modelo.addRow(fila);
			}
		}
	}

	private Vector<Object> crearFilaTablaRolPagoDetalleAnticipos(Map<String, Object> mapa)
			throws GenericBusinessException {
		String nombreRubroEventual = (String)mapa.get("nombreRubroEventual");
		Double total = Utilitarios.redondeoValor( (Double)mapa.get("total") );
		//String descripcion = "";//(String) mapa.get("descripcion");
		Vector<Object> fila = new Vector<Object>();
		fila.add(false);
		fila.add(nombreRubroEventual);
		fila.add(total);
		fila.add("");
		fila.add("");
		fila.add("");
		return fila;
	}

	private void cargarTablaRolPago() {
		try {
			if ( getRbAnticipos().isSelected() || getRbNormal().isSelected() ||
				 getRbBeneficiosSociales().isSelected()	){
				ocultarTablasDetalle();
				presentarTablaSeleccionada();
				
				rolPagoIfVector = null;
				if ( getRbNormal().isSelected() )
					rolPagoIfVector = (ArrayList<RolPagoIf>)SessionServiceLocator.getRolPagoSessionService()
						.getRolPagoList( TipoRolProvisionado.NO.getLetra(), 
							tipoContratoIf != null ? tipoContratoIf.getId() : null,	
							EstadoRolPagoDetalle.EMITIDO.getLetra(),calInicio,calFin);
				else if ( getRbAnticipos().isSelected() ) {
					ArrayList<RolPagoIf> rolesTemp = (ArrayList<RolPagoIf>) SessionServiceLocator.getRubroEventualSessionService()
						//.getRolPagoAnticiposList(EstadoRolPagoDetalle.EMITIDO.getLetra());
						.getRolPagoAnticiposList(EstadoRubroEventual.EMITIDO.getLetra());
					
					rolPagoIfVector = new ArrayList<RolPagoIf>();
					for ( RolPagoIf r : rolesTemp ){
						if ( !contieneRolPago(rolPagoIfVector,r) ){
							rolPagoIfVector.add(r);
						}
					}
				} else  if ( getRbBeneficiosSociales().isSelected() ) {
					rolPagoIfVector = (ArrayList<RolPagoIf>)SessionServiceLocator.getRolPagoSessionService()
						.getRolPagoList( TipoRolProvisionado.SI.getLetra(), null,	
							EstadoRolPagoDetalle.EMITIDO.getLetra(),calInicio,calFin);
				}
				
				if ( rolPagoIfVector != null ) {
					DefaultTableModel modelo = (DefaultTableModel) getTblRolPago().getModel();
					for ( RolPagoIf rolPagoIf : rolPagoIfVector ){						
						Vector<Object> fila = crearFilaTablaRolPago(rolPagoIf);
						modelo.addRow(fila);
					}
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al cargar tabla de Roles de Pago", SpiritAlert.ERROR);
			e.printStackTrace();
		} catch (Exception e) {
			SpiritAlert.createAlert("Error general al cargar tabla de Roles de Pago", SpiritAlert.ERROR);
			e.printStackTrace();
		} 
	}

	private void presentarTablaSeleccionada() {
		if ( getRbNormal().isSelected() )
			getSpTblRolPagoDetalleQyM().setVisible(true);
		else if ( getRbAnticipos().isSelected() )
			getSpTblRolPagoDetalleAnticipos().setVisible(true);
		else if ( getRbBeneficiosSociales().isSelected() )
			getSpTblRolPagoDetalleAyD().setVisible(true);
	}
	
	private boolean contieneRolPago( ArrayList<RolPagoIf> roles,RolPagoIf r ){
		for ( RolPagoIf rp : roles ){
			if ( r.getMes().equals(rp.getMes()) 
				 && r.getAnio().equals(rp.getAnio())
				 && r.getTiporolId().equals(rp.getTiporolId()) )
				return true;
		}
		return false;
	}

	private Vector<Object> crearFilaTablaRolPago(RolPagoIf rolPagoIf)
	throws GenericBusinessException {
		//TipoRolIf tipoRol = SessionServiceLocator.getTipoRolSessionService().getTipoRol(rolPagoIf.getTiporolId());
		TipoRolIf tipoRol = TipoRolUtil.verificarMapaTipoRol(mapaTiposRol,rolPagoIf.getTiporolId());
		String nombreTipoRol = tipoRol.getNombre();
		//TipoContratoIf tipoContrato = SessionServiceLocator.getTipoContratoSessionService().getTipoContrato(rolPagoIf.getTipocontratoId());
		TipoContratoIf tipoContrato = TipoContratoUtil.verificarMapaTipoContrato(mapaTiposContrato,rolPagoIf.getTipocontratoId());
		
		String nombreTipoContrato = tipoContrato.getNombre();
		Integer mesInteger = rolPagoIf.getMes() != null ? Integer.parseInt(rolPagoIf.getMes()) : null;
		String mesString = mesInteger!= null ? Utilitarios.getNombreMes(mesInteger) : null;
		Integer anio = Integer.valueOf( rolPagoIf.getAnio() );
		String estado = rolPagoIf.getEstado()!= null ? 
			( rolPagoIf.getEstado().equals(EstadoRolPago.GENERADO.getLetra())
				? EstadoRolPago.GENERADO.toString() : EstadoRolPago.CERRADO.toString() ) 
			: null	;
		Vector<Object> fila = new Vector<Object>();
		fila.add(nombreTipoRol);
		if ( getRbNormal().isSelected() || getRbBeneficiosSociales().isSelected() )
			fila.add(nombreTipoContrato);
		else 
			fila.add("");
			fila.add(mesString);
			fila.add(anio);
			fila.add(estado);
			return fila;
	}

	private boolean filtrarAutorizados() throws GenericBusinessException{
		int rowCount=-1;
		DefaultTableModel modelo = null;

		rolPagoDetalleSeleccionadosList = null;
		rolPagoDetalleSeleccionadosList = new ArrayList<Map<String,Object>>();
		int columnaTotal = -1;
		int columnaTipoPago = -1;
		int columnaCuentaBancaria = -1;
		int columnaNumeroCheque = -1;
		if ( getRbNormal().isSelected() ){
			if ( tablaQuincenalMensualSeleccionada ){
				rowCount = getTblRolPagoDetalleQyM().getRowCount();
				modelo = (DefaultTableModel) getTblRolPagoDetalleQyM().getModel();
				columnaTotal = COLUMNA_DETALLE_QYM_TOTAL;
				columnaTipoPago = COLUMNA_DETALLE_QYM_TIPO_PAGO;
				columnaCuentaBancaria = COLUMNA_DETALLE_QYM_CUENTA_BANCARIA;
				columnaNumeroCheque = COLUMNA_DETALLE_QYM_NUMERO_CHEQUE;
			}
			
		} else if ( getRbAnticipos().isSelected() ){
			rowCount = getTblRolPagoDetalleAnticipos().getRowCount();
			modelo = (DefaultTableModel) getTblRolPagoDetalleAnticipos().getModel();
			columnaTotal = COLUMNA_DETALLE_ANT_TOTAL;
			columnaTipoPago = COLUMNA_DETALLE_ANT_TIPO_PAGO;
			columnaCuentaBancaria = COLUMNA_DETALLE_ANT_CUENTA_BANCARIA;
			columnaNumeroCheque = COLUMNA_DETALLE_ANT_NUMERO_CHEQUE;
		} else {
			rowCount = getTblRolPagoDetalleAportesDecimos().getRowCount();
			modelo = (DefaultTableModel) getTblRolPagoDetalleAportesDecimos().getModel();
			columnaTotal = COLUMNA_DETALLE_AYD_TOTAL;
			columnaTipoPago = COLUMNA_DETALLE_AYD_TIPO_PAGO;
			columnaCuentaBancaria = COLUMNA_DETALLE_AYD_CUENTA_BANCARIA;
			columnaNumeroCheque = COLUMNA_DETALLE_AYD_NUMERO_CHEQUE;
		}

		for ( int i = 0 ; i < rowCount ; i++){
			boolean seleccionado = (Boolean) modelo.getValueAt(i, 0);
			if ( seleccionado ){
				Double total = null;
				Object oTipoPago = modelo.getValueAt(i, columnaTipoPago);
				String sTipoPago = oTipoPago instanceof String ? (String)oTipoPago : null;
				TipoPagoIf tipoPagoIf = oTipoPago instanceof TipoPagoIf ? (TipoPagoIf)oTipoPago : null;
				String sCuentaBancaria = (String) modelo.getValueAt(i, columnaCuentaBancaria);
				String nombreEmpleado = (String) modelo.getValueAt(i, COLUMNA_DETALLE_NOMBRE);
				total = (Double) modelo.getValueAt(i, columnaTotal);
				
				if ( tipoPagoIf == null && ( sTipoPago == null || "".equals(sTipoPago) ) ){
					SpiritAlert.createAlert("Elegir el tipo de pago para "+nombreEmpleado, SpiritAlert.WARNING);
					return false;
				}	
				
				if ( sCuentaBancaria == null  || "".equals(sCuentaBancaria) ){
					SpiritAlert.createAlert("Elegir la cuenta bancaria para "+nombreEmpleado, SpiritAlert.WARNING);
					return false;
				}
				
				if (total ==null){
					SpiritAlert.createAlert("Erro en Total de "+nombreEmpleado, SpiritAlert.WARNING);
					return false;
				}
				
				if ( tipoPagoIf == null )
					tipoPagoIf = tipoPagoMap.get(sTipoPago);
				String numeroCheque = null;
				if ( tipoPagoIf.getNombre().contains("CHEQUE") ){
					numeroCheque = (String) modelo.getValueAt(i, columnaNumeroCheque);
					if ( numeroCheque != null && !"".equals(numeroCheque) && !esNumeroPositivo(numeroCheque) ) {
						SpiritAlert.createAlert("Número de cheque tiene que ser mayor que cero para "+nombreEmpleado, SpiritAlert.WARNING);
						return false;
					}
				}
				
				Map<String, Object> rolPagoDetalleSeleccionadosMapa = new HashMap<String, Object>();
				Map<String, Object> mapa = rolPagoDetalleList.get(i);
				rolPagoDetalleSeleccionadosMapa.put("total", total);
				rolPagoDetalleSeleccionadosMapa.put("tipoPagoIf",tipoPagoIf);
				rolPagoDetalleSeleccionadosMapa.put("cuentaBancariaIf",cuentaBancariaMap.get(sCuentaBancaria));
				rolPagoDetalleSeleccionadosMapa.put("nombreEmpleado",nombreEmpleado);
				
				if ( numeroCheque!=null && !"".equals(numeroCheque.trim()) ){
					rolPagoDetalleSeleccionadosMapa.put("numeroCheque",numeroCheque);
				}
				if (  getRbNormal().isSelected() || getRbBeneficiosSociales().isSelected() ){
					Collection<RolPagoDetalleIf> detallesRolPago = (Collection<RolPagoDetalleIf>) mapa.get("detallesRolPago");
					rolPagoDetalleSeleccionadosMapa.put("rolPagoDetalleCollection",detallesRolPago);
				} else if ( getRbAnticipos().isSelected() ){
					Collection<RubroEventualIf> detallesRubroEventuales = (ArrayList<RubroEventualIf>)mapa.get("detalles");
					rolPagoDetalleSeleccionadosMapa.put("rubroId", mapa.get("rubroId"));
					rolPagoDetalleSeleccionadosMapa.put("rubroEventualCollection", detallesRubroEventuales);
				}	
				rolPagoDetalleSeleccionadosList.add(rolPagoDetalleSeleccionadosMapa);
			}
		}
		return true;
	}

	private boolean esNumeroPositivo(String numeroString ){
		try{
			Integer numero = Integer.valueOf(numeroString);
			if ( numero <= 0 )
				return false;
			numero = null;
		} catch(Exception e ){
			return false;			
		}
		return true;
	}
	
	@Override
	public void clean() {
		rolPagoIfVector = null;
		rolPagoIf = null;
		rolPagoDetalleList = null;
		rolPagoDetalleSeleccionadosList = null;

		calInicio = null;
		calFin = null;
		
		mapaTiposContrato = null;
		mapaTiposContrato = new HashMap<Long,TipoContratoIf>();
		
		mapaTiposRol = null;
		mapaTiposRol = new HashMap<Long,TipoRolIf>();
		
		ocultarTablasDetalle();
		
		getCmbFechaInicio().setDate(null);
		getCmbFechaInicio().repaint();
		getCmbFechaFin().setDate(null);
		getCmbFechaFin().repaint();
		
		limpiarTabla(getTblRolPago());
		limpiarTabla(getTblRolPagoDetalleQyM());
		limpiarTabla(getTblRolPagoDetalleAnticipos());
		limpiarTabla(getTblRolPagoDetalleAportesDecimos());
	}

	private void ocultarTablasDetalle() {
		getSpTblRolPagoDetalleQyM().setVisible(false);
		getSpTblRolPagoDetalleAyD().setVisible(false);
		getSpTblRolPagoDetalleAnticipos().setVisible(false);
	}

	@Override
	public void delete() {
	}

	public void find() {
	}

	public void report() {
	}

	public void save() {
	}

	public void update() {
		try {
			setCursorEspera();
			if ( filtrarAutorizados() ){
				if ( rolPagoDetalleSeleccionadosList.size() > 0 ){
					Date fechaHoy = new Date(new java.util.Date().getTime());
					if ( getRbNormal().isSelected() || getRbBeneficiosSociales().isSelected() )
						SessionServiceLocator.getRolPagoSessionService().autorizarRolPago(fechaHoy,rolPagoIf,rolPagoDetalleSeleccionadosList);
					else if ( getRbAnticipos().isSelected() ){
						SessionServiceLocator.getRolPagoSessionService().autorizarAnticipoRolPago(fechaHoy,rolPagoIf,rolPagoDetalleSeleccionadosList);
					}
					SpiritAlert.createAlert("Autorización Rol de Pago exitosa !!", SpiritAlert.INFORMATION);
					showSaveMode();
				} else {
					SpiritAlert.createAlert("Debe seleccionar al menos uno !!", SpiritAlert.WARNING);
				}
			} 
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al autorizar Rol de Pago", SpiritAlert.ERROR);
		} finally{
			setCursorNormal();
		}
	}
	
	//INI_CAMBIO_20140903 Se crea metodo para el calculo de los totales de Debito y cheque 
	//para autorizaciones de pago de rol 
	public void totalesAutorizacionDebitoCheque() {
		Double sumaTotalesDebitos = 0.0;
		Double sumaTotalesCheques = 0.0;
		TipoPagoIf tipoPago = null;
		
		try {
			setCursorEspera();
			if ( filtrarAutorizados() ){
				if ( rolPagoDetalleSeleccionadosList.size() > 0 ){ 
					if ( getRbNormal().isSelected() ){
						for (Map<String, Object> mapa : rolPagoDetalleSeleccionadosList) {
							tipoPago = (TipoPagoIf)mapa.get("tipoPagoIf");	
							if(tipoPago.getNombre().contains("DEBITO")){
								sumaTotalesDebitos = Utilitarios.redondeoValor(sumaTotalesDebitos +( (Double)mapa.get("total") ));
							}
						}
						getTxtTotalDebitos().setText(formatoDecimal.format(sumaTotalesDebitos));
						getTxtTotalDebitos().setEditable(false);
						for (Map<String, Object> mapa : rolPagoDetalleSeleccionadosList) {
							tipoPago = (TipoPagoIf)mapa.get("tipoPagoIf");
							if(tipoPago.getNombre().contains("CHEQUE")){
								sumaTotalesCheques = Utilitarios.redondeoValor(sumaTotalesCheques +( (Double)mapa.get("total") ));
							}
						}
						getTxtTotalCheques().setText(formatoDecimal.format(sumaTotalesCheques));
						getTxtTotalCheques().setEditable(false);
					}
						
				} else {
					SpiritAlert.createAlert("Debe seleccionar al menos uno !!", SpiritAlert.WARNING);
				}
			} 
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al generar totales", SpiritAlert.ERROR);
		} finally{
			setCursorNormal();
		}
	}
	//FIN_CAMBIO_20140903
	
	//INI_CAMBIO_20140903 Habilita los objetos que forman parte del calculo de los totales de la 
	//autorizacion del rol
	public void habilitarOpcTotales(){
		getLblTotalCheques().setEnabled(true);
		getTxtTotalCheques().setEnabled(true);
		getLblTotalDebitos().setEnabled(true);
		getTxtTotalDebitos().setEnabled(true);
	}
	//FIN_CAMBIO_20140903
		
	public void authorize() {
	}

	@Override
	public boolean validateFields() {
		return false;
	}

	public void addDetail() {
	}

	public boolean isEmpty() {
		return false;
	}

	public void refresh() {
		showSaveMode();
		
		//INI_CAMBIO_20140904
		getTxtTotalCheques().setText("");
		getTxtTotalDebitos().setText("");
		habilitarOpcTotales();
		//FIN_CAMBIO_20140904
	}
	
	public void duplicate() {
	}

	public void showFindMode() {
		clean();
		cargarTablaRolPago();
	}

	public void showSaveMode() {
		setSaveMode();
		showFindMode();
	}

	public void showUpdateMode() {
		setUpdateMode();
	}

	public void updateDetail() {
	}

	public void deleteDetail() {
		
	}
}
