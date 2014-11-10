package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.layout.CellConstraints;
import com.jidesoft.docking.DockableFrame;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.MainFrameModel;
import com.spirit.client.NumberCellRenderer;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModel;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.gui.controller.PanelHandler;
import com.spirit.general.util.Contratos;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.panel.JPGeneracionAsientoRolPago;
import com.spirit.nomina.gui.util.EstadoRolPago;
import com.spirit.nomina.gui.util.TipoContratoUtil;
import com.spirit.nomina.gui.util.TipoRolUtil;
import com.spirit.nomina.handler.EstadoRolPagoDetalle;
import com.spirit.nomina.handler.TipoRol;
import com.spirit.util.Utilitarios;

public class GeneracionAsientoRolPagoModel extends JPGeneracionAsientoRolPago {

	private static final long serialVersionUID = 2260850962611203314L;

	private static final int COLUMNA_DETALLE_SELECCION = 0;
	private static final int COLUMNA_DETALLE_NOMBRE = 1;
	
	private static final int COLUMNA_DETALLE_QYM_INGRESOS = 2;
	private static final int COLUMNA_DETALLE_QYM_EGRESOS = 3;
	private static final int COLUMNA_DETALLE_QYM_TOTAL = 4;
	private static final int COLUMNA_DETALLE_QYM_TIPO_PAGO = 5;
	private static final int COLUMNA_DETALLE_QYM_CUENTA_BANCARIA = 6;
	private static final int COLUMNA_DETALLE_QYM_NUMERO_CHEQUE = 7;
	
	private static final int COLUMNA_DETALLE_AYD_TOTAL = 2;
	private static final int COLUMNA_DETALLE_AYD_TIPO_PAGO = 3;
	private static final int COLUMNA_DETALLE_AYD_CUENTA_BANCARIA = 4;
	private static final int COLUMNA_DETALLE_AYD_NUMERO_CHEQUE = 5;

	
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

	private Map<Long,TipoContratoIf> mapaTiposContrato = new HashMap<Long,TipoContratoIf>();
	private Map<Long,TipoRolIf> mapaTiposRol = new HashMap<Long,TipoRolIf>();

	private Calendar calInicio = null;
	private Calendar calFin = null;
	
	private static Map panels;
	
	public GeneracionAsientoRolPagoModel(){
		panels = MainFrameModel.get_panels();
		iniciarComponentes();
		setAnchoComlumnas();
		initListener();
		showSaveMode();
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
		
		getPanelDetalleRolPago().add(getSpTblRolPagoDetalleAyD(), new CellConstraints().xywh(1, 7, 3, 3));
		
		//Tabla Rol de Pago
		setSorterTable(getTblRolPago());

		//Tabla Rol de Pago DEtalle Quincenal y Mensual 
		setSorterTable(getTblRolPagoDetalleQyM());
		getTblRolPagoDetalleQyM().getTableHeader().setReorderingAllowed(false);
		getTblRolPagoDetalleQyM().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblRolPagoDetalleQyM().getColumnModel().getColumn(COLUMNA_DETALLE_QYM_INGRESOS).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		getTblRolPagoDetalleQyM().getColumnModel().getColumn(COLUMNA_DETALLE_QYM_EGRESOS).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		getTblRolPagoDetalleQyM().getColumnModel().getColumn(COLUMNA_DETALLE_QYM_TOTAL).setCellRenderer(
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
		
		JComboBox comboTipoPago = new JComboBox();
		Iterator<Long> itCuentaBancaria = tipoPagoMap.keySet().iterator();
		for ( ; itCuentaBancaria.hasNext() ; ){
			Long cuentaId = itCuentaBancaria.next();
			comboTipoPago.addItem( tipoPagoMap.get(cuentaId) );
		}
		comboTipoPago.setSelectedIndex(0);
		
		
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
	
	private void setAnchoComlumnas(){
		//TABLA DE QUINCENA Y MENSUAL 
		TableColumn anchoColumna = getTblRolPagoDetalleQyM().getColumnModel().getColumn(COLUMNA_DETALLE_SELECCION);
		anchoColumna.setPreferredWidth(45);
		anchoColumna = getTblRolPagoDetalleQyM().getColumnModel().getColumn(COLUMNA_DETALLE_NOMBRE);
		anchoColumna.setPreferredWidth(280);
		anchoColumna = getTblRolPagoDetalleQyM().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblRolPagoDetalleQyM().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(95);	
		anchoColumna = getTblRolPagoDetalleQyM().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblRolPagoDetalleQyM().getColumnModel().getColumn(COLUMNA_DETALLE_QYM_TIPO_PAGO);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblRolPagoDetalleQyM().getColumnModel().getColumn(COLUMNA_DETALLE_QYM_CUENTA_BANCARIA);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblRolPagoDetalleQyM().getColumnModel().getColumn(COLUMNA_DETALLE_QYM_NUMERO_CHEQUE);
		anchoColumna.setPreferredWidth(120);

		//TABLA DE APORTES Y DECIMOS
		anchoColumna = getTblRolPagoDetalleAportesDecimos().getColumnModel().getColumn(COLUMNA_DETALLE_SELECCION);
		anchoColumna.setPreferredWidth(45);
		anchoColumna = getTblRolPagoDetalleAportesDecimos().getColumnModel().getColumn(COLUMNA_DETALLE_NOMBRE);
		anchoColumna.setPreferredWidth(280);	
		anchoColumna = getTblRolPagoDetalleAportesDecimos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblRolPagoDetalleAportesDecimos().getColumnModel().getColumn(COLUMNA_DETALLE_AYD_TIPO_PAGO);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblRolPagoDetalleAportesDecimos().getColumnModel().getColumn(COLUMNA_DETALLE_AYD_CUENTA_BANCARIA);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblRolPagoDetalleAportesDecimos().getColumnModel().getColumn(COLUMNA_DETALLE_AYD_NUMERO_CHEQUE);
		anchoColumna.setPreferredWidth(120);
		
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

	}
	
	private void filtrarBusquedaRolPago() {
		try{
			calInicio = null;
			calFin = null;
			
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
					//if ( calFin.compareTo(calInicio) < 0 ){
						calFin.setTime(calInicio.getTime());
						calFin.set(Calendar.DATE, calFin.getActualMaximum(Calendar.DATE));
						calFin.set(Calendar.YEAR, calFin.get(Calendar.YEAR)+1);
					//}
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
		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		for ( int i = 0 ; i < modelo.getRowCount() ; i++  ){
			int columnaValor = 0;
			if ( tipoRol == TipoRol.QUINCENAL || tipoRol == TipoRol.MENSUAL ){
				columnaValor = COLUMNA_DETALLE_QYM_TOTAL;
			} else if ( tipoRol == TipoRol.DECIMO_TERCERO || tipoRol == TipoRol.DECIMO_CUARTO 
					|| tipoRol == TipoRol.VACACIONES 
					|| tipoRol == TipoRol.APORTE_PATRONAL ||  tipoRol == TipoRol.FONDO_RESERVA
					|| tipoRol == TipoRol.UTILIDADES ){
				columnaValor = COLUMNA_DETALLE_AYD_TOTAL;
			} else
				SpiritAlert.createAlert("Tipo de Rol no considerado para presentación en tabla de detalle !!", SpiritAlert.WARNING);
			
			Object valor = modelo.getValueAt(i, columnaValor);
			if ( valor != null ){
				if ( valor instanceof Double || valor instanceof BigDecimal ){
					Double valorD = valor instanceof Double ? (Double)valor : ((BigDecimal)valor).doubleValue() ;
					modelo.setValueAt(true, i, COLUMNA_DETALLE_SELECCION);
				} else if ( valor instanceof String ){
					String valorS = (String)valor;
					if ( !"".equals(valorS.trim()) ){
						Double valorSD = Double.valueOf(valorS);
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
			SpiritAlert.createAlert("Por favor seleccione una fila !!", SpiritAlert.INFORMATION);
		}
	}
	
	private void seleccionarFila() {
		rolPagoIf = null;
		limpiarTabla( getTblRolPagoDetalleQyM() );
		limpiarTabla( getTblRolPagoDetalleAportesDecimos() );

		Runnable runnable = new Runnable(){
			public void run() {
				setCursorEspera();
				int filaSeleccionada = getTblRolPago().getSelectedRow();
				if ( filaSeleccionada >= 0 ){
					try {
						//getSpTblRolPagoDetalle().setViewportView(null);
						//getSpTblRolPagoDetalle().repaint();
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

	private void cargarTablaRolPagoDetalle() throws GenericBusinessException{
		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		//Collection<Long> contratosIdCollection = Contratos.obtenerContratosId(tipoRol,tipoContratoIf,rolPagoIf,EstadoRolPagoDetalle.PAGADO.getLetra());
		Collection<Long> contratosIdCollection = Contratos.obtenerContratosId(tipoRol,tipoContratoIf,rolPagoIf,null);
		rolPagoDetalleSeleccionadosList = null;
		rolPagoDetalleList = null;
		DefaultTableModel modelo = establecerTabla(tipoRol);
		
		if ( modelo != null ){

			rolPagoDetalleList = (ArrayList<Map<String, Object>>)SessionServiceLocator.getRolPagoSessionService()
			.crearColeccionContratos(contratosIdCollection,rolPagoIf,
					//EstadoRolPagoDetalle.PAGADO.getLetra(),true,
					null,true,false,false,"",false);
					//EtapaRolPago.GENERACION_ASIENTO);

			if ( tipoRol == TipoRol.QUINCENAL || tipoRol == TipoRol.MENSUAL ){
				cargarTablaRolPagoDetalleQyM(rolPagoDetalleList, modelo);
				getTblRolPagoDetalleQyM().validate();
				getTblRolPagoDetalleQyM().repaint();
				tablaQuincenalMensualSeleccionada = true;
			} else if ( tipoRol == TipoRol.DECIMO_TERCERO || tipoRol == TipoRol.DECIMO_CUARTO 
					|| tipoRol == TipoRol.VACACIONES 
					|| tipoRol == TipoRol.APORTE_PATRONAL ||  tipoRol == TipoRol.FONDO_RESERVA ){
				String nombreMes = Utilitarios.getNombreMes(Integer.valueOf(rolPagoIf.getMes()));
				cargarTablaRolPagoDetalleAportesDecimos(rolPagoDetalleList, modelo,nombreMes);
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
			tablaSeleccionada = getTblRolPagoDetalleQyM();
			getSpTblRolPagoDetalleQyM().setVisible(true);
			return (DefaultTableModel)getTblRolPagoDetalleQyM().getModel();
		} else if ( tipoRol == TipoRol.DECIMO_TERCERO || tipoRol == TipoRol.DECIMO_CUARTO 
				|| tipoRol == TipoRol.VACACIONES 
				|| tipoRol == TipoRol.APORTE_PATRONAL ||  tipoRol == TipoRol.FONDO_RESERVA){
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

	private void cargarTablaRolPagoDetalleAportesDecimos(Collection<Map<String, Object>> rolPagoContratoCollection, DefaultTableModel modelo,String nombreMes)
	throws GenericBusinessException {
		for ( Map<String, Object> mapa : rolPagoContratoCollection){
			Vector<Object> fila = crearFilaTablaRolPagoDetalleAportesDecimos(mapa,nombreMes);
			modelo.addRow(fila);
		}
	}

	private Vector<Object> crearFilaTablaRolPagoDetalleAportesDecimos(Map<String, Object> mapa,String nombreMes)
	throws GenericBusinessException {
		String nombreEmpleado = (String)mapa.get("nombreEmpleado");
		//Double total = Utilitarios.redondeoValor( (Double)mapa.get("total") );
		Double total = mapa.get(nombreMes) != null ? (Double)mapa.get(nombreMes) : (Double)mapa.get("total") ;
		total = Utilitarios.redondeoValor( total );
		Collection<RolPagoDetalleIf> detalles =  (Collection<RolPagoDetalleIf>)mapa.get("detallesRolPago");
		RolPagoDetalleIf detalle = null;
		if ( detalles.size() > 0 )
			detalle = detalles.iterator().next();

		Vector<Object> fila = new Vector<Object>();
		fila.add(false);
		fila.add(nombreEmpleado);
		fila.add(total);
		//fila.add(detalle!=null?tipoPagoMap.get(detalle.getTipoPagoId()):null);
		fila.add("");
		//fila.add(detalle!=null?cuentaBancariaMap.get(detalle.getCuentaBancariaId()):null);
		fila.add("");
		//fila.add(detalle!=null?detalle.getPreimpreso():"");
		fila.add("");
		return fila;
	}
	
	private void cargarTablaRolPago() {
		try {
			ocultarTablasDetalle();
			getSpTblRolPagoDetalleQyM().setVisible(true);
			
			Collection<String> listaTiposRol = new ArrayList<String>();
			listaTiposRol.add("MENSUAL");
			rolPagoIfVector = null;
			
			/*rolPagoIfVector = (ArrayList<RolPagoIf>) SessionServiceLocator.getRolPagoSessionService()
				.getRolPagoListGeneracionAsientos(EstadoRolPagoDetalle.PAGADO.getLetra(),listaTiposRol,
						calInicio,calFin,
						EstadoRolPagoDetalle.EMITIDO.getLetra(),
						EstadoRolPagoDetalle.AUTORIZADO.getLetra(),
						EstadoRolPagoDetalle.PAGADO.getLetra() );*/
			rolPagoIfVector = (ArrayList<RolPagoIf>) SessionServiceLocator.getRolPagoSessionService()
				.getRolPagoListGeneracionAsientos(null,listaTiposRol,
					calInicio,calFin,
					EstadoRolPagoDetalle.EMITIDO.getLetra(),
					EstadoRolPagoDetalle.AUTORIZADO.getLetra(),
					EstadoRolPagoDetalle.PAGADO.getLetra() );
			
			DefaultTableModel modelo = (DefaultTableModel) getTblRolPago().getModel();
			for ( RolPagoIf rolPagoIf : rolPagoIfVector ){
				Vector<Object> fila = crearFilaTablaRolPago(rolPagoIf);
				modelo.addRow(fila);
			}
			setCursorNormal();
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al cargar tabla de Roles de Pago", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private Vector<Object> crearFilaTablaRolPago(RolPagoIf rolPagoIf)
	throws GenericBusinessException {
		TipoRolIf tipoRol = TipoRolUtil.verificarMapaTipoRol(mapaTiposRol,rolPagoIf.getTiporolId());
		String nombreTipoRol = tipoRol.getNombre();
		TipoContratoIf tipoContrato = TipoContratoUtil.verificarMapaTipoContrato(
			mapaTiposContrato,rolPagoIf.getTipocontratoId());
		String nombreTipoContrato = tipoContrato.getNombre();
		int mesInteger = Integer.parseInt( rolPagoIf.getMes() );
		String mesString = Utilitarios.getNombreMes(mesInteger);
		Integer anio = Integer.valueOf( rolPagoIf.getAnio() );
		String estado = rolPagoIf.getEstado().equals(EstadoRolPago.GENERADO.getLetra())
			?EstadoRolPago.GENERADO.toString():EstadoRolPago.CERRADO.toString();
		Vector<Object> fila = new Vector<Object>();
		fila.add(nombreTipoRol);
		fila.add(nombreTipoContrato);
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
		if ( tablaQuincenalMensualSeleccionada ){
			rowCount = getTblRolPagoDetalleQyM().getRowCount();
			modelo = (DefaultTableModel) getTblRolPagoDetalleQyM().getModel();
			columnaTotal = COLUMNA_DETALLE_QYM_TOTAL;
		}
		else{
			rowCount = getTblRolPagoDetalleAportesDecimos().getRowCount();
			modelo = (DefaultTableModel) getTblRolPagoDetalleAportesDecimos().getModel();
			columnaTotal = COLUMNA_DETALLE_AYD_TOTAL;
		}
		
		if ( tipoRolIf.getRubroProvisionado() == null ){
			SpiritAlert.createAlert("Establecer si "+tipoRolIf.getNombre()+"es Rol de Provision primero !! ", SpiritAlert.ERROR);
			return false;
		}
		
		for ( int i = 0 ; i < rowCount ; i++){
			boolean seleccionado = (Boolean) modelo.getValueAt(i, 0);
			if (seleccionado  ){
				
				String nombre = (String) modelo.getValueAt(i, COLUMNA_DETALLE_NOMBRE);
				
				/*TipoPagoIf tipoPagoIf = (TipoPagoIf)modelo.getValueAt(i, columnaTipoPago);
				String numeroCheque = null;
				if ( TipoRolProvisionado.NO.getLetra().equals(tipoRolIf.getRubroProvisionado()) ) {
					if ( tipoPagoIf!=null && tipoPagoIf.getNombre().contains("CHEQUE") ){
						numeroCheque = (String) modelo.getValueAt(i, columnaNumeroCheque);
						if ( numeroCheque == null || "".equals(numeroCheque.trim()) ){
							SpiritAlert.createAlert("Ingresar número de cheque para "+nombre, SpiritAlert.WARNING);
							return false;	
						} else if ( !esNumeroPositivo(numeroCheque) ) {
							SpiritAlert.createAlert("Número de cheque tiene que ser mayor que cero para "+nombre, SpiritAlert.WARNING);
							return false;
						}
					}
				}*/
				
				Map<String, Object> rolPagoDetalleSeleccionadosMapa = new HashMap<String, Object>();
				Map<String, Object> mapa = rolPagoDetalleList.get(i);

				Collection<RolPagoDetalleIf> detallesRolPago = (Collection<RolPagoDetalleIf>) mapa.get("detallesRolPago");
				rolPagoDetalleSeleccionadosMapa.put("rolPagoDetalleCollection",detallesRolPago);

				Double total = (Double) modelo.getValueAt(i, columnaTotal);
				total = Utilitarios.redondeoValor(total);
				Long cuentaBancariaId = (Long) mapa.get("cuentaBancariaId");
				Long contratoId = (Long) mapa.get("contratoId");
				
				rolPagoDetalleSeleccionadosMapa.put("total",total);
				rolPagoDetalleSeleccionadosMapa.put("nombreEmpleado",nombre);
				//rolPagoDetalleSeleccionadosMapa.put("tipoPagoIf",tipoPagoIf);
				rolPagoDetalleSeleccionadosMapa.put("cuentaBancariaId",cuentaBancariaId);
				rolPagoDetalleSeleccionadosMapa.put("contratoId", contratoId);
				
				/*if ( numeroCheque!=null ){
					numeroCheque = numeroCheque.trim();
					rolPagoDetalleSeleccionadosMapa.put("numeroCheque",numeroCheque);
				}
				
				if ( tipoPagoIf != null && 
					tipoPagoIf.getNombre().contains("CHEQUE") && ( numeroCheque ==null || "".equals(numeroCheque) ) ){
					SpiritAlert.createAlert("Ingresar número de cheque para "+nombre, SpiritAlert.WARNING);
					return false;
				}*/
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
		
		//cleanCombosFechas();
		
		limpiarTabla(getTblRolPago());
		limpiarTabla(getTblRolPagoDetalleQyM());
		limpiarTabla(getTblRolPagoDetalleAportesDecimos());
	}

	private void cleanCombosFechas() {
		getCmbFechaInicio().setDate(null);
		getCmbFechaInicio().repaint();
		getCmbFechaFin().setDate(null);
		getCmbFechaFin().repaint();
	}

	private void ocultarTablasDetalle() {
		getSpTblRolPagoDetalleQyM().setVisible(false);
		getSpTblRolPagoDetalleAyD().setVisible(false);
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
					
					//Date fechaHoy = new Date(new java.util.Date().getTime());
					Calendar calFechaRolPago = new GregorianCalendar();
					int mesRolPago = Integer.parseInt( rolPagoIf.getMes() )-1;
					calFechaRolPago.set(Calendar.MONTH, mesRolPago);
					int anio = Integer.parseInt(rolPagoIf.getAnio());
					calFechaRolPago.set(Calendar.YEAR, anio);
					calFechaRolPago.set(Calendar.DAY_OF_MONTH, calFechaRolPago.getActualMaximum(Calendar.DAY_OF_MONTH));
					Date fechaRolPago = new Date(calFechaRolPago.getTime().getTime());
					
					//SessionServiceLocator.getRolPagoSessionService().generarAsientoRolPago(fechaHoy,rolPagoIf, rolPagoDetalleSeleccionadosList);
					SessionServiceLocator.getRolPagoSessionService().generarAsientoRolPago(fechaRolPago,rolPagoIf, rolPagoDetalleSeleccionadosList);
					
					SpiritAlert.createAlert("Generación de Asiento exitoso !!", SpiritAlert.INFORMATION);

					//showSaveMode();
					setSaveMode();
					clean();
					cargarTablaRolPago();
					
				} else {
					SpiritAlert.createAlert("Debe seleccionar al menos uno !!", SpiritAlert.INFORMATION);
				}
			} 
			
			//para pago
			/*if ( filtrarAutorizados() ){
				if ( rolPagoDetalleSeleccionadosList.size() > 0 ){
					Date fechaHoy = new Date(new java.util.Date().getTime());
					//if ( getRbNormal().isSelected() ){
						getRolPagoSessionService().procesarPagoRol(fechaHoy,rolPagoIf, rolPagoDetalleSeleccionadosList);
					//}
					//else if ( getRbAnticipos().isSelected() )
					//	getRolPagoSessionService().procesarPagoAnticipoRol(fechaHoy,rolPagoIf,rolPagoDetalleSeleccionadosList);
					SpiritAlert.createAlert("Generación de Pagos exitoso !!", SpiritAlert.INFORMATION);
					//generarCheques(rolPagoDetalleSeleccionadosList);
					showSaveMode();
				} else {
					SpiritAlert.createAlert("Debe seleccionar al menos uno !!", SpiritAlert.INFORMATION);
				}
			} */
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al generar asiento de Rol de Pago", SpiritAlert.ERROR);
		}
		//SpiritAlert.createAlert("Metodo no Implementado !!", SpiritAlert.INFORMATION);
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
		cleanCombosFechas();
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
