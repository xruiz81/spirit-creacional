package com.spirit.nomina.gui.model;

import java.awt.Toolkit;
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
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jidesoft.docking.DockableFrame;
import com.spirit.cartera.handler.ComprobanteEgresoData;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.MainFrameModel;
import com.spirit.client.NumberCellRenderer;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritModel;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaEntidadIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.gui.controller.PanelHandler;
import com.spirit.general.gui.panel.JDCheque;
import com.spirit.general.util.Contratos;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.panel.JPPagoRol;
import com.spirit.nomina.gui.util.EstadoRolPago;
import com.spirit.nomina.gui.util.ModelUtil;
import com.spirit.nomina.gui.util.TipoContratoUtil;
import com.spirit.nomina.gui.util.TipoPagoCellEditor;
import com.spirit.nomina.gui.util.TipoRolUtil;
import com.spirit.nomina.handler.EstadoRolPagoDetalle;
import com.spirit.nomina.handler.EstadoRubroEventual;
import com.spirit.nomina.handler.TipoRol;
import com.spirit.nomina.handler.TipoRolFormaPago;
import com.spirit.nomina.handler.TipoRolProvisionado;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class PagoRolModel extends JPPagoRol {

	private static final long serialVersionUID = 2260850962611203314L;

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
	private static final int COLUMNA_DETALLE_ANT_BENFICIARIO = 3;
	private static final int COLUMNA_DETALLE_ANT_TIPO_PAGO = 4;
	private static final int COLUMNA_DETALLE_ANT_CUENTA_BANCARIA = 5;
	private static final int COLUMNA_DETALLE_ANT_NUMERO_CHEQUE = 6;
	
	private boolean filasSeleccionadas = false;
	private String textoSeleccionarTodo = "Seleccionar Todo";
	private String textoDeseleccionarTodo = "Deseleccionar Todo";
	
	private ArrayList<RolPagoIf> rolPagoIfVector = null;
	private RolPagoIf rolPagoIf = null;
	private TipoRolIf tipoRolIf = null;
	private TipoContratoIf tipoContratoIf = null;

	private ArrayList<Map<String, Object>> rolPagoDetalleList = null;
	private Collection<Map<String,Object>> rolPagoDetalleSeleccionadosList = null;
	private JTable tablaSeleccionada =null;
	
	private Map<Long, String> cuentaBancariaMap = null;
	private Map<Long, TipoPagoIf> tipoPagoMap = null;
	boolean tablaQuincenalMensualSeleccionada = false;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private ArrayList<String[]> cheques = new ArrayList<String[]>();
	private JDCheque jdCheque;

	private Map<Long,TipoContratoIf> mapaTiposContrato = new HashMap<Long,TipoContratoIf>();
	private Map<Long,TipoRolIf> mapaTiposRol = new HashMap<Long,TipoRolIf>();
	
	private Calendar calInicio = null;
	private Calendar calFin = null;
	
	private static Map panels;
	
	public PagoRolModel(){
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
		
		//getPanelRolPagoDetalle().add(getSpTblRolPagoDetalleAyD(), new CellConstraints().xywh(1, 1, 1, 3));
		//getPanelRolPagoDetalle().add(getSpTblRolPagoDetalleAnticipos(), new CellConstraints().xywh(1, 1, 1, 3));
		getPanelDetalleRolPago().add(getSpTblRolPagoDetalleAyD(), new CellConstraints().xywh(1, 7, 3, 3));
		getPanelDetalleRolPago().add(getSpTblRolPagoDetalleAnticipos(), new CellConstraints().xywh(1, 7, 3, 3));
		
		//Combo tipo de rol
		getRbNormal().setSelected(true);
		
		//Combo tipo de contrato
		//getCmbTipoContrato().setSelectedItem(null);
		//cargarCmbTipoContrato();
		
		//Tabla Rol de Pago
		setSorterTable(getTblRolPago());

		//Tabla Rol de Pago DEtalle Quincenal y Mensual 
		setSorterTable(getTblRolPagoDetalleQyM());
		getTblRolPagoDetalleQyM().getTableHeader().setReorderingAllowed(false);
		getTblRolPagoDetalleQyM().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumnModel modeloColumna = getTblRolPagoDetalleQyM().getColumnModel(); 
		modeloColumna.getColumn(COLUMNA_DETALLE_QYM_INGRESOS).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		modeloColumna.getColumn(COLUMNA_DETALLE_QYM_EGRESOS).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		modeloColumna.getColumn(COLUMNA_DETALLE_QYM_TOTAL).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		//getTblRolPagoDetalleQyM().getColumnModel().getColumn(COLUMNA_DETALLE_QYM_TOTAL).setCellRenderer(new TableCellRendererHorizontalRightAlignment());

		cargarTipoPagos();
		
		//Tabla Rol de Pago DEtalle Aportes y Decimos
		setSorterTable(getTblRolPagoDetalleAportesDecimos());
		getTblRolPagoDetalleAportesDecimos().getTableHeader().setReorderingAllowed(false);
		getTblRolPagoDetalleAportesDecimos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//getTblRolPagoDetalleAportesDecimos().getColumnModel().getColumn(COLUMNA_DETALLE_AYD_TOTAL).setCellRenderer(new TableCellRendererHorizontalRightAlignment());
		getTblRolPagoDetalleAportesDecimos().getColumnModel().getColumn(COLUMNA_DETALLE_AYD_TOTAL).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		cargarCuentasBancarias();
		
		//Tabla Rol de Pago DEtalle ANTICIPOS
		setSorterTable(getTblRolPagoDetalleAnticipos());
		getTblRolPagoDetalleAnticipos().getTableHeader().setReorderingAllowed(false);
		getTblRolPagoDetalleAnticipos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_TOTAL).setCellRenderer(new TableCellRendererHorizontalRightAlignment());
		getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_TOTAL).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		
		JComboBox comboTipoPago = new JComboBox();
		Iterator<Long> itCuentaBancaria = tipoPagoMap.keySet().iterator();
		for ( ; itCuentaBancaria.hasNext() ; ){
			Long cuentaId = itCuentaBancaria.next();
			comboTipoPago.addItem( tipoPagoMap.get(cuentaId) );
		}
		comboTipoPago.setSelectedIndex(0);
		getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_TIPO_PAGO).setCellEditor(
				new TipoPagoCellEditor(comboTipoPago));
		
	}

	private void cargarCuentasBancarias(){
		try {
			cuentaBancariaMap = null;
			cuentaBancariaMap = new HashMap<Long, String>();
			Collection<CuentaBancariaIf> cuentasBancarias = SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByEmpresaId(Parametros.getIdEmpresa());
			for ( CuentaBancariaIf cuentaBancaria : cuentasBancarias ){
				BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancaria.getBancoId());
				cuentaBancariaMap.put(cuentaBancaria.getId() ,banco.getNombre() + ", Cta. " + cuentaBancaria.getCuenta());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la carga de Cuentas Bancarias !!", SpiritAlert.ERROR);
		}
	}

	private void cargarTipoPagos(){
		try {
			tipoPagoMap = null;
			tipoPagoMap =  new HashMap<Long, TipoPagoIf>();
			Collection<TipoPagoIf> tipos = SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByEmpresaId(Parametros.getIdEmpresa());
			for ( TipoPagoIf tipo : tipos ){
				tipoPagoMap.put(tipo.getId(), tipo);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la carga de tipos de pagos !!", SpiritAlert.ERROR);
		}
	}
	
	/*private void cargarCmbTipoContrato(){
		try {
			Collection<TipoContratoIf> tiposContratoCollection =  SessionServiceLocator.getTipoContratoSessionService().findTiposContratosUsados(Parametros.getIdEmpresa());
			Vector<TipoContratoIf> tiposContrato = new Vector<TipoContratoIf>(tiposContratoCollection);
			DefaultComboBoxModel comboModel = new DefaultComboBoxModel(tiposContrato);
			getCmbTipoContrato().setModel(comboModel);
			refreshCombo(getCmbTipoContrato(), (List)tiposContratoCollection);
			for (int i=0 ; i<comboModel.getSize() ; i++){
				TipoContratoIf tc = (TipoContratoIf) getCmbTipoContrato().getItemAt(i);
				if ( "RD".equals(tc.getCodigo()) ){
					tipoContratoIf = tc;
					getCmbTipoContrato().setSelectedIndex(i);
					break;
				}
			}	
			getCmbTipoContrato().repaint();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la cargar de los tipos de rol", SpiritAlert.WARNING);
			e.printStackTrace();
		};
	}*/

	private void setAnchoComlumnas(){
		//TABLA DE QUINCENA Y MENSUAL 
		TableColumnModel modelo = getTblRolPagoDetalleQyM().getColumnModel();
		modelo.getColumn(COLUMNA_DETALLE_SELECCION).setPreferredWidth(45);
		modelo.getColumn(COLUMNA_DETALLE_NOMBRE).setPreferredWidth(280);
		modelo.getColumn(2).setPreferredWidth(95);
		modelo.getColumn(3).setPreferredWidth(95);	
		modelo.getColumn(4).setPreferredWidth(95);
		modelo.getColumn(COLUMNA_DETALLE_QYM_TIPO_PAGO).setPreferredWidth(95);
		modelo.getColumn(COLUMNA_DETALLE_QYM_CUENTA_BANCARIA).setPreferredWidth(250);
		modelo.getColumn(COLUMNA_DETALLE_QYM_NUMERO_CHEQUE).setPreferredWidth(120);

		//TABLA DE APORTES Y DECIMOS
		modelo = getTblRolPagoDetalleAportesDecimos().getColumnModel();
		modelo.getColumn(COLUMNA_DETALLE_SELECCION).setPreferredWidth(45);
		modelo.getColumn(COLUMNA_DETALLE_NOMBRE).setPreferredWidth(280);	
		modelo.getColumn(COLUMNA_DETALLE_AYD_VALOR).setPreferredWidth(95);
		modelo.getColumn(COLUMNA_DETALLE_AYD_ANTICIPOS).setPreferredWidth(95);
		modelo.getColumn(COLUMNA_DETALLE_AYD_TOTAL).setPreferredWidth(95);
		modelo.getColumn(COLUMNA_DETALLE_AYD_TIPO_PAGO).setPreferredWidth(95);
		modelo.getColumn(COLUMNA_DETALLE_AYD_CUENTA_BANCARIA).setPreferredWidth(250);
		modelo.getColumn(COLUMNA_DETALLE_AYD_NUMERO_CHEQUE).setPreferredWidth(120);
		
		//TABLA DE ANTICIPOS
		modelo = getTblRolPagoDetalleAnticipos().getColumnModel();
		modelo.getColumn(COLUMNA_DETALLE_SELECCION).setPreferredWidth(45);
		modelo.getColumn(COLUMNA_DETALLE_NOMBRE).setPreferredWidth(280);
		modelo.getColumn(COLUMNA_DETALLE_ANT_TOTAL).setPreferredWidth(95);
		modelo.getColumn(COLUMNA_DETALLE_ANT_TIPO_PAGO).setPreferredWidth(95);
		modelo.getColumn(COLUMNA_DETALLE_ANT_BENFICIARIO).setPreferredWidth(310);
		modelo.getColumn(COLUMNA_DETALLE_ANT_CUENTA_BANCARIA).setPreferredWidth(250);
		modelo.getColumn(COLUMNA_DETALLE_ANT_NUMERO_CHEQUE).setPreferredWidth(120);
	}

	private void initListener(){
		getBtnSeleccionarTodos().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				seleccionarTodo();
			}
		});
		
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
		
		//POPUP PARA VER DETALLE DE RUBRO POR CONTRATO 
		JPopupMenu popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("<html><font color=red>Detalle de Contrato</font></html>");
		menuItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				try {
					visualizarRubrosPorEmpleado();
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
	}
	
	private void filtrarBusquedaRolPago() {
		try{
			calInicio = null;
			calFin = null;
			
			//tipoContratoIf = (TipoContratoIf)getCmbTipoContrato().getSelectedItem();
			
			if ( getCmbFechaInicio().getDate() != null ){
				calInicio = new GregorianCalendar();
				calInicio.setTime( getCmbFechaInicio().getDate() );
				calInicio.set(Calendar.DATE, 1);
				if ( getCmbFechaFin().getDate() != null ){
					calFin = new GregorianCalendar();
					calFin.setTime( getCmbFechaFin().getDate() );
					calFin.set(Calendar.DATE, calFin.getActualMaximum(Calendar.DATE));
					if ( calFin.compareTo(calInicio) < 0 ){
						SpiritAlert.createAlert("Fecha Fin tiene que ser mayor a Fecha Inicio !!", SpiritAlert.ERROR);
						return;
					}
				} else {
					calFin = new GregorianCalendar();
					calFin.set(Calendar.DATE, calFin.getActualMaximum(Calendar.DATE));
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
		
		for ( int i = 0 ; i < modelo.getRowCount() ; i++  ){
			//modelo.setValueAt(true, i, COLUMNA_DETALLE_SELECCION);
			
			int columnaValor = 0;
			if ( getRbNormal().isSelected() ){
				columnaValor = COLUMNA_DETALLE_QYM_TOTAL;
			} else if ( getRbAnticipos().isSelected() ){
				columnaValor = COLUMNA_DETALLE_ANT_TOTAL;
			} else if ( getRbBeneficiosSociales().isSelected() ){
				columnaValor = COLUMNA_DETALLE_AYD_TOTAL;
			} else
				SpiritAlert.createAlert("Tipo de Rol no considerado para presentación en tabla de detalle !!", SpiritAlert.WARNING);
			
			
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
			ContratoIf contratoIf = SessionServiceLocator.getContratoSessionService().getContrato(contratoId);
			SpiritModel panelRolPagoPorContrato = (SpiritModel) new ConsultaRolContratoModel(tipoRolIf,rolPagoIf,contratoIf,false,null);
			
			if (panels.size()>0 && panels.get("Consulta Rol Por Contrato")!=null)
				MainFrameModel.get_dockingManager().removeFrame("Consulta Rol Por Contrato");
			
			DockableFrame panel = PanelHandler.createPanelesApp(panelRolPagoPorContrato);
			MainFrameModel.get_dockingManager().addFrame(panel);
			MainFrameModel.get_dockingManager().showFrame(panel.getName());
		} else{
			SpiritAlert.createAlert("Por favot selecciona una fila !!", SpiritAlert.INFORMATION);
		}
	}
	
	private ActionListener alRbTipoRubro = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			showSaveMode();
		}
	};

	private void seleccionarFila() {
		rolPagoIf = null;
		limpiarTabla( getTblRolPagoDetalleQyM() );
		limpiarTabla( getTblRolPagoDetalleAportesDecimos() );
		limpiarTabla( getTblRolPagoDetalleAnticipos() );

		filasSeleccionadas = false;
		getBtnSeleccionarTodos().setText(textoSeleccionarTodo);
		
		Runnable runnable = new Runnable(){
			public void run() {
				setCursorEspera();
				int filaSeleccionada = getTblRolPago().getSelectedRow();
				if ( filaSeleccionada >= 0 ){
					try {
						int fila = getTblRolPago().convertRowIndexToModel(filaSeleccionada);
						rolPagoIf = rolPagoIfVector.get(fila);
						tipoRolIf = TipoRolUtil.verificarMapaTipoRol(mapaTiposRol,rolPagoIf.getTiporolId());
						tipoContratoIf = TipoContratoUtil.verificarMapaTipoContrato(
								mapaTiposContrato,rolPagoIf.getTipocontratoId());
						cargarTablaRolPagoDetalle();
						showUpdateMode();
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
					} catch (Exception e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Error general Rol de Pago", SpiritAlert.ERROR);
					} finally{
						setCursorNormal();
					}
				}
			}
		};
		Thread t = new Thread(runnable);
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
		Collection<Long> contratosIdCollection = Contratos.obtenerContratosId(tipoRol,tipoContratoIf,rolPagoIf,EstadoRolPagoDetalle.AUTORIZADO.getLetra() );
		rolPagoDetalleSeleccionadosList = null;
		rolPagoDetalleList = null;
		DefaultTableModel modelo = establecerTabla(tipoRol);
		
		if ( modelo != null ){
		
			if ( getRbNormal().isSelected() ) {
				rolPagoDetalleList = (ArrayList<Map<String, Object>>)SessionServiceLocator.getRolPagoSessionService()
					.crearColeccionContratos(contratosIdCollection,rolPagoIf,
						EstadoRolPagoDetalle.AUTORIZADO.getLetra(),true,false,false,"",false);
						//,EtapaRolPago.PAGO_ROL);
			} else  if ( getRbAnticipos().isSelected() ) {
				Map<String, Object> mapaAnticipos = new HashMap<String, Object>();
				//mapaAnticipos.put("rolpagoId",rolPagoIf.getId());
				mapaAnticipos.put("tipoRolIdPago",rolPagoIf.getTiporolId());
				mapaAnticipos.put("mesPago",rolPagoIf.getMes());
				mapaAnticipos.put("anioPago",rolPagoIf.getAnio());
				
				rolPagoDetalleList = (ArrayList<Map<String, Object>>)SessionServiceLocator.getRolPagoDetalleSessionService()
					.crearColeccionAnticiposRolPagoDetalleByQueryByEstadosConEventuales(mapaAnticipos,
						EstadoRolPagoDetalle.AUTORIZADO.getLetra() );
			} else  if ( getRbBeneficiosSociales().isSelected() ) {
				rolPagoDetalleList = (ArrayList<Map<String, Object>>)SessionServiceLocator.getRolPagoSessionService()
					.crearColeccionContratos(contratosIdCollection,rolPagoIf,
						EstadoRolPagoDetalle.AUTORIZADO.getLetra(),true,false,false,"",false);
						//,null);
			}
			//else 
			//	rolPagoDetalleList = (ArrayList)getRolPagoDetalleSessionService().findRolPagoDetalleEventualesByRolPagoByEstado(rolPagoIf,RolPagoDetalle.getLetraEstadoDetalle(EstadoRolPagoDetalle.EMITIDO));
						
			if ( getRbNormal().isSelected() ){
				//if ( tipoRol == TipoRol.QUINCENAL || tipoRol == TipoRol.MENSUAL ){
					cargarTablaRolPagoDetalleQyM(rolPagoDetalleList, modelo);
					getTblRolPagoDetalleQyM().validate();
					getTblRolPagoDetalleQyM().repaint();
					tablaQuincenalMensualSeleccionada = true;
				//} 
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
		Collection<RolPagoDetalleIf> detalles =  (Collection<RolPagoDetalleIf>)mapa.get("detallesRolPago");
		RolPagoDetalleIf detalle = null;
		if ( detalles.size() > 0 )
			detalle = detalles.iterator().next();
		
		Vector<Object> fila = new Vector<Object>();
		fila.add(false);
		fila.add(nombreEmpleado);
		fila.add(totalIngresos);
		fila.add(totalEgresos);
		fila.add(total);
		fila.add(detalle!=null?tipoPagoMap.get(detalle.getTipoPagoId()):" - ");
		fila.add(detalle!=null?cuentaBancariaMap.get(detalle.getCuentaBancariaId()):"");
		fila.add(detalle!=null?detalle.getPreimpreso():"");
		return fila;
	}

	private void cargarTablaRolPagoDetalleAportesDecimos(Collection<Map<String, Object>> rolPagoContratoCollection, DefaultTableModel modelo)
	throws GenericBusinessException {
		for ( Map<String, Object> mapa : rolPagoContratoCollection){
			TipoRolFormaPago formaPago = TipoRolFormaPago.getTipoRolPagoByLetra(tipoRolIf.getFormaPago());
			String nombreMes = rolPagoIf.getMes() != null? Utilitarios.getNombreMes(Integer.valueOf(rolPagoIf.getMes())) : "";
			Vector<Object> fila = ModelUtil.crearFilaTablaRolPagoDetalleAportesDecimosPagos(
				nombreMes,formaPago,mapa, tipoPagoMap, cuentaBancariaMap);
			modelo.addRow(fila);
		}
	}

	/*private Vector<Object> crearFilaTablaRolPagoDetalleAportesDecimos(Map<String, Object> mapa)
	throws GenericBusinessException {
		String nombreEmpleado = (String)mapa.get("nombreEmpleado");
		Double valor = Utilitarios.redondeoValor( (Double)mapa.get("total") );
		Double anticipos = Utilitarios.redondeoValor( mapa.get("anticipos") != null ? (Double)mapa.get("anticipos") : 0D );
		Double total = Utilitarios.redondeoValor( valor - anticipos );
		Collection<RolPagoDetalleIf> detalles =  (Collection<RolPagoDetalleIf>)mapa.get("detallesRolPago");
		RolPagoDetalleIf detalle = null;
		if ( detalles.size() > 0 )
			detalle = detalles.iterator().next();

		Vector<Object> fila = new Vector<Object>();
		fila.add(false);
		fila.add(nombreEmpleado);
		fila.add(valor);
		fila.add(anticipos);
		fila.add(total);
		fila.add(detalle!=null?tipoPagoMap.get(detalle.getTipoPagoId()):" - ");
		fila.add(detalle!=null?cuentaBancariaMap.get(detalle.getCuentaBancariaId()):"");
		fila.add(detalle!=null?detalle.getPreimpreso():"");
		return fila;
	}*/
	
	private void cargarTablaRolPagoDetalleAnticipos(Collection<Map<String, Object>> rolPagoContratoCollection, DefaultTableModel modelo)
	throws GenericBusinessException {
		for ( Map<String, Object> mapa : rolPagoContratoCollection){
			if ( mapa.get("nombreRubroEventual")!=null && mapa.get("total")!=null ){
				Vector<Object> fila = crearFilaTablaRolPagoDetalleAnticipos(mapa);
				modelo.addRow(fila);
			}
		}
	}

	private Vector<Object> crearFilaTablaRolPagoDetalleAnticipos(Map<String, Object> mapa)
	throws GenericBusinessException {
		String nombreRubroEventual = (String)mapa.get("nombreRubroEventual");
		Long tipoPagoId = (Long) mapa.get("tipoPagoId");
		Long cuentaBancariaId = (Long) mapa.get("cuentaBancariaId");
		String preimpreso = (String) mapa.get("preimpreso");
		String nombreEmpleado = (String)mapa.get("nombreEmpleado");
		Double total = Utilitarios.redondeoValor( (Double)mapa.get("total") ) ;
		
		Vector<Object> fila = new Vector<Object>();
		fila.add(false);
		fila.add(nombreRubroEventual);
		fila.add(total);
		fila.add(nombreEmpleado!=null?nombreEmpleado:"");
		fila.add(tipoPagoId!=null?tipoPagoMap.get(tipoPagoId):" - ");
		fila.add(cuentaBancariaId!=null?cuentaBancariaMap.get(cuentaBancariaId):"");
		fila.add(preimpreso!=null?preimpreso:"");
		
		return fila;
	}

	private void cargarTablaRolPago() {
		try {
			if ( getRbAnticipos().isSelected() || getRbNormal().isSelected() ||
					 getRbBeneficiosSociales().isSelected() ){
				ocultarTablasDetalle();
				presentarTablaSeleccionada();
				
				//tipoContratoIf = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
				
				rolPagoIfVector = null;
				if ( getRbNormal().isSelected() )
					rolPagoIfVector = (ArrayList<RolPagoIf>) SessionServiceLocator.getRolPagoSessionService()
						.getRolPagoList( TipoRolProvisionado.NO.getLetra(), 
							tipoContratoIf != null ? tipoContratoIf.getId() : null,
							EstadoRolPagoDetalle.AUTORIZADO.getLetra(),calInicio,calFin);
				else  if ( getRbAnticipos().isSelected() ) {
					//rolPagoIfVector = (ArrayList<RolPagoIf>) getRolPagoSessionService().getRolPagoAnticiposList(RolPagoDetalle.getLetraEstadoDetalle(EstadoRolPagoDetalle.AUTORIZADO));
					//ArrayList<RolPagoIf> rolesTemp = (ArrayList<RolPagoIf>) SessionServiceLocator
					//	.getRolPagoSessionService().getRolPagoAnticiposList(EstadoRolPagoDetalle.AUTORIZADO.getLetra());
					ArrayList<RolPagoIf> rolesTemp = (ArrayList<RolPagoIf>) SessionServiceLocator.getRubroEventualSessionService()
						.getRolPagoAnticiposList(
							EstadoRubroEventual.AUTORIZADO.getLetra(),
							EstadoRubroEventual.AUTORIZADO_PARCIAL.getLetra() );
					
					rolPagoIfVector = new ArrayList<RolPagoIf>();
					for ( RolPagoIf r : rolesTemp ){
						if ( !contieneRolPago(rolPagoIfVector,r) ){
							rolPagoIfVector.add(r);
						}
					}
				} else  if ( getRbBeneficiosSociales().isSelected() ) {
					rolPagoIfVector = (ArrayList<RolPagoIf>) SessionServiceLocator.getRolPagoSessionService()
						.getRolPagoList( TipoRolProvisionado.SI.getLetra(), 
							null,
							EstadoRolPagoDetalle.AUTORIZADO.getLetra(),calInicio,calFin);
				}
				if ( rolPagoIfVector != null ) {
					DefaultTableModel modelo = (DefaultTableModel) getTblRolPago().getModel();
					for ( RolPagoIf rolPagoIf : rolPagoIfVector ){
						Vector<Object> fila = crearFilaTablaRolPago(rolPagoIf);
						modelo.addRow(fila);
					}
				}
			}
			setCursorNormal();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al cargar tabla de Roles de Pago", SpiritAlert.ERROR);
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
		TipoRolIf tipoRol = TipoRolUtil.verificarMapaTipoRol(mapaTiposRol,rolPagoIf.getTiporolId());
		String nombreTipoRol = tipoRol.getNombre();
		TipoContratoIf tipoContrato = TipoContratoUtil.verificarMapaTipoContrato(
				mapaTiposContrato,rolPagoIf.getTipocontratoId());
		String nombreTipoContrato = tipoContrato.getNombre();
		int mesInteger = rolPagoIf.getMes() != null? Integer.parseInt(rolPagoIf.getMes()) : 0;
		String mesString = rolPagoIf.getMes() != null? Utilitarios.getNombreMes(mesInteger) : "";
		Integer anio = Integer.valueOf( rolPagoIf.getAnio() );
		
		String estado = "";
		if(rolPagoIf.getEstado() != null){
			if(rolPagoIf.getEstado().equals(EstadoRolPago.GENERADO.getLetra())){
				estado = EstadoRolPago.GENERADO.toString();
			}else{
				estado = EstadoRolPago.CERRADO.toString();
			}	
		}			
		
		Vector<Object> fila = new Vector<Object>();
		fila.add(nombreTipoRol);
		if ( getRbNormal().isSelected() )
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
		int columnaNumeroCheque = -1;
		if ( getRbNormal().isSelected() ){
			if ( tablaQuincenalMensualSeleccionada ){
				rowCount = getTblRolPagoDetalleQyM().getRowCount();
				modelo = (DefaultTableModel) getTblRolPagoDetalleQyM().getModel();
				columnaTotal = COLUMNA_DETALLE_QYM_TOTAL;
				columnaTipoPago = COLUMNA_DETALLE_QYM_TIPO_PAGO;
				columnaNumeroCheque = COLUMNA_DETALLE_QYM_NUMERO_CHEQUE;
			}
		} else if ( getRbAnticipos().isSelected() ){
			rowCount = getTblRolPagoDetalleAnticipos().getRowCount();
			modelo = (DefaultTableModel) getTblRolPagoDetalleAnticipos().getModel();
			columnaTotal = COLUMNA_DETALLE_ANT_TOTAL;
			columnaTipoPago = COLUMNA_DETALLE_ANT_TIPO_PAGO;
			columnaNumeroCheque = COLUMNA_DETALLE_ANT_NUMERO_CHEQUE;
		} else{
			rowCount = getTblRolPagoDetalleAportesDecimos().getRowCount();
			modelo = (DefaultTableModel) getTblRolPagoDetalleAportesDecimos().getModel();
			columnaTotal = COLUMNA_DETALLE_AYD_TOTAL;
			columnaTipoPago = COLUMNA_DETALLE_AYD_TIPO_PAGO;
			columnaNumeroCheque = COLUMNA_DETALLE_AYD_NUMERO_CHEQUE;
		}

		for ( int i = 0 ; i < rowCount ; i++){
			boolean seleccionado = (Boolean) modelo.getValueAt(i, 0);
			if (seleccionado  ){
				
				String nombre = (String) modelo.getValueAt(i, COLUMNA_DETALLE_NOMBRE);
				TipoPagoIf tipoPagoIf = (TipoPagoIf)modelo.getValueAt(i, columnaTipoPago);
				String numeroCheque = null;
				if ( tipoPagoIf.getNombre().contains("CHEQUE") ){
					numeroCheque = (String) modelo.getValueAt(i, columnaNumeroCheque);
					if ( numeroCheque == null || "".equals(numeroCheque.trim()) ){
						SpiritAlert.createAlert("Ingresar número de cheque para "+nombre, SpiritAlert.WARNING);
						return false;	
					} else if ( !esNumeroPositivo(numeroCheque) ) {
						SpiritAlert.createAlert("Número de cheque tiene que ser mayor que cero para "+nombre, SpiritAlert.WARNING);
						return false;
					}
				}
				String beneficiario = null;
				if ( getRbAnticipos().isSelected() ){
					beneficiario = (String) modelo.getValueAt(i, COLUMNA_DETALLE_ANT_BENFICIARIO);
				}

				Map<String, Object> rolPagoDetalleSeleccionadosMapa = new HashMap<String, Object>();
				Map<String, Object> mapa = rolPagoDetalleList.get(i);

				Collection<RolPagoDetalleIf> detallesRolPago = (Collection<RolPagoDetalleIf>) mapa.get("detallesRolPago");
				rolPagoDetalleSeleccionadosMapa.put("rolPagoDetalleCollection",detallesRolPago);

				Double total = (Double) modelo.getValueAt(i, columnaTotal);
				total = Utilitarios.redondeoValor(total);
				Long cuentaBancariaId = (Long) mapa.get("cuentaBancariaId");
				
				rolPagoDetalleSeleccionadosMapa.put("total",total);
				rolPagoDetalleSeleccionadosMapa.put("nombre",nombre);
				rolPagoDetalleSeleccionadosMapa.put("tipoPagoIf",tipoPagoIf);
				rolPagoDetalleSeleccionadosMapa.put("cuentaBancariaId",cuentaBancariaId);

				//numeroCheque = (String) modelo.getValueAt(i, columnaNumeroCheque);
				if ( numeroCheque!=null )
					numeroCheque = numeroCheque.trim();
				rolPagoDetalleSeleccionadosMapa.put("numeroCheque",numeroCheque);
				TipoPagoIf tipoPago = (TipoPagoIf)modelo.getValueAt(i, columnaTipoPago);
				if ( tipoPago.getNombre().contains("CHEQUE") && ( numeroCheque ==null || "".equals(numeroCheque) ) ){
					SpiritAlert.createAlert("Ingresar número de cheque para "+nombre, SpiritAlert.WARNING);
					return false;
				}
				if ( getRbAnticipos().isSelected() ){
					rolPagoDetalleSeleccionadosMapa.put("beneficiario", beneficiario);
					rolPagoDetalleSeleccionadosMapa.put("nombreRubro", nombre);
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

		mapaTiposContrato = null;
		mapaTiposContrato = new HashMap<Long,TipoContratoIf>();
		
		mapaTiposRol = null;
		mapaTiposRol = new HashMap<Long,TipoRolIf>();

		calInicio = null;
		calFin = null;
		
		ocultarTablasDetalle();
		
		//getCmbTipoContrato().setSelectedItem(null);
		
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
			if ( filtrarAutorizados() ){
				if ( rolPagoDetalleSeleccionadosList.size() > 0 ){
					Date fechaHoy = new Date(new java.util.Date().getTime());
					
					Collection<AsientoIf> asientos = null;
					if ( getRbNormal().isSelected() ){
						asientos = SessionServiceLocator.getRolPagoSessionService().procesarPagoRol(fechaHoy,rolPagoIf, rolPagoDetalleSeleccionadosList);
						SpiritAlert.createAlert("Generación de Pagos exitosa !!", SpiritAlert.INFORMATION);
						generarCheques(rolPagoDetalleSeleccionadosList);
						
					}
					else if ( getRbAnticipos().isSelected() ){
						asientos = SessionServiceLocator.getRolPagoSessionService().procesarPagoAnticipoRol(fechaHoy,rolPagoIf,rolPagoDetalleSeleccionadosList);
						SpiritAlert.createAlert("Generación de Pagos exitosa !!", SpiritAlert.INFORMATION);
						generarCheques(rolPagoDetalleSeleccionadosList);
						
					}
					else{
						SpiritAlert.createAlert("Aún no esta implementada la opción: Beneficios Sociales.", SpiritAlert.WARNING);
					}
					if ( cheques.size() > 0 ){
						int contadorCheque = 0;
						for (AsientoIf asiento : asientos){
							List<AsientoDetalleIf> detalles = (ArrayList)SessionServiceLocator
								.getAsientoDetalleSessionService().findAsientoDetalleByAsientoId(asiento.getId());
							generarReporte(asiento, detalles, false,contadorCheque);
							contadorCheque++;
						}
					}
					showSaveMode();
				} else {
					SpiritAlert.createAlert("Debe seleccionar al menos uno !!", SpiritAlert.INFORMATION);
				}
			} 
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al autorizar Rol de Pago", SpiritAlert.ERROR);
		}
	}
	
	public void generarCheques(Collection<Map<String,Object>> rolPagoDetalleSeleccionadosList) {
		try {
			cheques = null;
			cheques = new ArrayList<String[]>();
			//Iterator rolPagoDetalleSeleccionadosListIt = rolPagoDetalleSeleccionadosList.iterator();
			for(Map<String,Object> rolPagoDetalleSeleccionado : rolPagoDetalleSeleccionadosList ){
				//Map<String,Object> rolPagoDetalleSeleccionado = (Map<String,Object>)rolPagoDetalleSeleccionadosListIt.next();
				if ( rolPagoDetalleSeleccionado.get("numeroCheque")!=null ){
					Double totalCheque = (Double)rolPagoDetalleSeleccionado.get("total");
					
					if (totalCheque.compareTo(0D) != 0) {
						OficinaIf oficina = (OficinaIf) Parametros.getOficina();
						CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
						String valorCheque = formatoDecimal.format(Double.valueOf(totalCheque));
						String parteDecimal = valorCheque.substring(valorCheque.indexOf('.'), valorCheque.length());
						Double dParteDecimal = 0.0;
						if (!parteDecimal.isEmpty())
							dParteDecimal = Double.valueOf(parteDecimal);
						String pagueseA = null;
						if ( rolPagoDetalleSeleccionado.get("beneficiario") != null )
							pagueseA = (String)rolPagoDetalleSeleccionado.get("beneficiario");
						else 
							pagueseA = (String)rolPagoDetalleSeleccionado.get("nombre");
						String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(valorCheque, dParteDecimal, new int[] { 70, 90 }, false, Parametros.getMonedaPredeterminada());
						String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
						String cantidadLetrasSegundaLinea = cantidadLetras[1].replaceAll("  ","");
						//String lugarFecha = ciudad.getNombre() + ", " + Utilitarios.getFechaUppercase(Utilitarios.dateHoy());
						//String lugarFechaPrimerReemplazo = lugarFecha.replaceFirst("-","DE");
						//lugarFecha = lugarFechaPrimerReemplazo.replaceAll("-","DEL");
						
						String lugarFecha = ciudad.getNombre() + ", " + Utilitarios.getFechaNuevosCheques(Utilitarios.dateHoy());
						
						String[] datosCheque = new String[5];
						datosCheque[0] = valorCheque;
						datosCheque[1] = pagueseA;
						datosCheque[2] = cantidadLetrasPrimeraLinea;
						datosCheque[3] = cantidadLetrasSegundaLinea;
						datosCheque[4] = lugarFecha;
						cheques.add(datosCheque);
					}
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
			
		} catch (ParseException pe) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			pe.printStackTrace();
		}
	}
	
	private void generarReporte(AsientoIf asiento, List<AsientoDetalleIf> asientoDetalleColeccion, boolean anulado, int numero) {
		try {
			Vector<ComprobanteEgresoData> comprobanteEgresoColeccion = new Vector<ComprobanteEgresoData>();
			String[] datosCheque = cheques.get(numero);
			String valorCheque = Utilitarios.removeDecimalFormat(datosCheque[0]);
			Double totalCheque = Double.valueOf(valorCheque);
			
			//String valorComprobante = String.valueOf(totalCheque);
			String valorComprobante = formatoDecimal.format(Double.valueOf(totalCheque));
			String parteDecimal = valorComprobante.substring(valorComprobante.indexOf('.'), valorComprobante.length());
			Double dParteDecimal = 0.0;
			if (!parteDecimal.isEmpty())
				dParteDecimal = Double.valueOf(parteDecimal);
			String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(valorComprobante, dParteDecimal, new int[] { 90 }, false, Parametros.getMonedaPredeterminada());
			String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
			
			for(AsientoDetalleIf asientoDetalle : asientoDetalleColeccion){
				if((asientoDetalle.getHaber() != null) && (asientoDetalle.getHaber().compareTo(new BigDecimal(0)) == 1)){
					ComprobanteEgresoData comprobanteEgresoData = agregarDetalleComprobante(asiento, asientoDetalle, anulado);
					String fecha = asiento.getFecha().toString();
					String year = fecha.substring(0,4);
					String month = fecha.substring(5,7);
					String day = fecha.substring(8,10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					comprobanteEgresoData.setFechaEmision(fechaEmision);
					comprobanteEgresoData.setProveedor(asiento.getObservacion());
					comprobanteEgresoData.setCantidad(cantidadLetrasPrimeraLinea);
					comprobanteEgresoData.setConcepto(asiento.getObservacion());
					comprobanteEgresoData.setValorTotal(valorComprobante);
					comprobanteEgresoData.setCodigo(asiento.getNumero());
					comprobanteEgresoColeccion.add(comprobanteEgresoData);
				}				
			}			
			
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

			String fecha = asiento.getFecha().toString();
			String year = fecha.substring(0,4);
			String month = fecha.substring(5,7);
			String day = fecha.substring(8,10);
			String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
			parametrosMap.put("emitido", fechaEmision);
			ReportModelImpl.processReport(fileName, parametrosMap, comprobanteEgresoColeccion, true);

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
	}
	
	private ComprobanteEgresoData agregarDetalleComprobante(AsientoIf asiento, AsientoDetalleIf asientoDetalle, boolean anulado) {
		ComprobanteEgresoData data = new ComprobanteEgresoData();
		
		try {
			CuentaIf cuenta = SessionServiceLocator.getCuentaSessionService().getCuenta(asientoDetalle.getCuentaId());
			Map cuentaEntidadMapa = new HashMap();
			cuentaEntidadMapa.put("tipoEntidad", "B");
			cuentaEntidadMapa.put("cuentaId",cuenta.getId());
			CuentaEntidadIf cuentaEntidad = (CuentaEntidadIf)SessionServiceLocator.getCuentaEntidadSessionService().findCuentaEntidadByQuery(cuentaEntidadMapa).iterator().next();
			CuentaBancariaIf cuentaBancaria = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(cuentaEntidad.getEntidadId());
			data.setBanco(cuenta.getNombre());
			data.setNumeroCuenta(cuentaBancaria.getCuenta());
			
			if((asientoDetalle.getReferencia()==null) || asientoDetalle.getReferencia().trim().equals("")){
				data.setNumeroCheque("D/B");
			}else{
				data.setNumeroCheque(asientoDetalle.getReferencia());
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
				data.setDetalle(asientoDetalle.getGlosa().length()>52?asientoDetalle.getGlosa().substring(0,52):asientoDetalle.getGlosa());
				data.setValor((anulado || (asientoDetalle.getHaber() == null))?formatoDecimal.format(new Double(0)):formatoDecimal.format(asientoDetalle.getHaber()));
				data.setSaldo(formatoDecimal.format(new Double(0)));
			}			
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		
		return data;
	}
	
	public void authorize() {
		//super.authorize();
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
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
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
