package com.spirit.nomina.gui.model;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

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
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModel;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.gui.controller.PanelHandler;
import com.spirit.general.gui.panel.JDCheque;
import com.spirit.general.util.Contratos;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.panel.JPRolPagado;
import com.spirit.nomina.gui.util.EstadoRolPago;
import com.spirit.nomina.handler.EstadoRolPagoDetalle;
import com.spirit.nomina.handler.TipoRol;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class RolPagadoModel extends JPRolPagado {

	private static final long serialVersionUID = 2260850962611203314L;

	private static final int COLUMNA_DETALLE_SELECCION = 0;
	private static final int COLUMNA_DETALLE_NOMBRE = 1;
	
	private static final int COLUMNA_DETALLE_QYM_TOTAL = 4;
	private static final int COLUMNA_DETALLE_QYM_TIPO_PAGO = 5;
	private static final int COLUMNA_DETALLE_QYM_CUENTA_BANCARIA = 6;
	private static final int COLUMNA_DETALLE_QYM_NUMERO_CHEQUE = 7;
	
	private static final int COLUMNA_DETALLE_AYD_TOTAL = 2;
	private static final int COLUMNA_DETALLE_AYD_TIPO_PAGO = 3;
	private static final int COLUMNA_DETALLE_AYD_CUENTA_BANCARIA = 4;
	private static final int COLUMNA_DETALLE_AYD_NUMERO_CHEQUE = 5;
		
	private static final int COLUMNA_DETALLE_ANT_DESCRIPCION = 2;
	private static final int COLUMNA_DETALLE_ANT_TOTAL = 3;
	private static final int COLUMNA_DETALLE_ANT_TIPO_PAGO = 4;
	private static final int COLUMNA_DETALLE_ANT_CUENTA_BANCARIA = 5;
	private static final int COLUMNA_DETALLE_ANT_NUMERO_CHEQUE = 6;
	
	private ArrayList<RolPagoIf> rolPagoIfVector = null;
	private RolPagoIf rolPagoIf = null;
	private TipoRolIf tipoRolIf = null;

	private ArrayList<Map<String, Object>> rolPagoDetalleList = null;
	private Collection<Map<String,Object>> rolPagoDetalleSeleccionadosList = null;
	private JTable tablaSeleccionada =null;
	
	private Map<Long, String> cuentaBancariaMap = null;
	private Map<Long, TipoPagoIf> tipoPagoMap = null;
	boolean tablaQuincenalMensualSeleccionada = false;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private ArrayList<String[]> cheques = new ArrayList<String[]>();
	private JDCheque jdCheque;

	private static Map panels;
	
	public RolPagadoModel(){
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
		
		ocultarTablasDetalle();
		
		getPanelRolPagoDetalle().add(getSpTblRolPagoDetalleAyD(), new CellConstraints().xywh(1, 1, 1, 3));
		getPanelRolPagoDetalle().add(getSpTblRolPagoDetalleAnticipos(), new CellConstraints().xywh(1, 1, 1, 3));
		
		//Combo tipo de rol
		getRbNormal().setSelected(true);
		
		//Tabla Rol de Pago
		setSorterTable(getTblRolPago());

		//Tabla Rol de Pago DEtalle Quincenal y Mensual 
		setSorterTable(getTblRolPagoDetalleQyM());
		getTblRolPagoDetalleQyM().getTableHeader().setReorderingAllowed(false);
		getTblRolPagoDetalleQyM().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblRolPagoDetalleQyM().getColumnModel().getColumn(2).setCellRenderer(new TableCellRendererHorizontalRightAlignment());
		getTblRolPagoDetalleQyM().getColumnModel().getColumn(3).setCellRenderer(new TableCellRendererHorizontalRightAlignment());
		getTblRolPagoDetalleQyM().getColumnModel().getColumn(COLUMNA_DETALLE_QYM_TOTAL).setCellRenderer(new TableCellRendererHorizontalRightAlignment());

		cargarTipoPagos();
		
		//Tabla Rol de Pago DEtalle Aportes y Decimos
		setSorterTable(getTblRolPagoDetalleAportesDecimos());
		getTblRolPagoDetalleAportesDecimos().getTableHeader().setReorderingAllowed(false);
		getTblRolPagoDetalleAportesDecimos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblRolPagoDetalleAportesDecimos().getColumnModel().getColumn(COLUMNA_DETALLE_AYD_TOTAL).setCellRenderer(new TableCellRendererHorizontalRightAlignment());

		cargarCuentasBancarias();
		
		//Tabla Rol de Pago DEtalle ANTICIPOS
		setSorterTable(getTblRolPagoDetalleAnticipos());
		getTblRolPagoDetalleAnticipos().getTableHeader().setReorderingAllowed(false);
		getTblRolPagoDetalleAnticipos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_TOTAL).setCellRenderer(new TableCellRendererHorizontalRightAlignment());

		
		
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
		
		//TABLA DE ANTICIPOS
		anchoColumna = getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_SELECCION);
		anchoColumna.setPreferredWidth(45);
		anchoColumna = getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_NOMBRE);
		anchoColumna.setPreferredWidth(280);
		anchoColumna = getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(310);
		anchoColumna = getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_DESCRIPCION);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_TIPO_PAGO);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_CUENTA_BANCARIA);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_NUMERO_CHEQUE);
		anchoColumna.setPreferredWidth(120);
	}

	private void initListener(){
		getBtnSeleccionarTodos().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				seleccionatTodo();
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
	}
	
	private void seleccionatTodo(){
		DefaultTableModel modelo  = (DefaultTableModel) tablaSeleccionada.getModel();
		for ( int i = 0 ; i < modelo.getRowCount() ; i++  ){
			modelo.setValueAt(true, i, COLUMNA_DETALLE_SELECCION);
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

		int filaSeleccionada = getTblRolPago().getSelectedRow();
		if ( filaSeleccionada >= 0 ){
			try {
				int fila = getTblRolPago().convertRowIndexToModel(filaSeleccionada);
				rolPagoIf = rolPagoIfVector.get(fila);
				tipoRolIf = SessionServiceLocator.getTipoRolSessionService().getTipoRol(rolPagoIf.getTiporolId());
				cargarTablaRolPagoDetalle();
				showUpdateMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error al obtener información de Rol de Pago", SpiritAlert.ERROR);
			}
		}
	}

	private void cargarTablaRolPagoDetalle() throws GenericBusinessException{
		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		Collection<Long> contratosIdCollection = Contratos
			.obtenerContratosId(tipoRol,null,rolPagoIf,EstadoRolPagoDetalle.PAGADO.getLetra());
		rolPagoDetalleSeleccionadosList = null;
		rolPagoDetalleList = null;
		DefaultTableModel modelo = establecerTabla(tipoRol);
		
		if ( modelo != null ){
		
			if ( getRbNormal().isSelected() )
				rolPagoDetalleList = (ArrayList) SessionServiceLocator.getRolPagoSessionService()
					.crearColeccionContratos(
					contratosIdCollection,rolPagoIf,EstadoRolPagoDetalle.PAGADO.getLetra(),true,false,false,"",false);
					//,EtapaRolPago.PAGO_ROL);
			else {
				Map<String, Object> mapaAnticipos = new HashMap<String, Object>();
				mapaAnticipos.put("rolpagoId",rolPagoIf.getId());
				
				rolPagoDetalleList = (ArrayList) SessionServiceLocator.getRolPagoDetalleSessionService()
					.crearColeccionAnticiposRolPagoDetalleByQueryByEstados(mapaAnticipos
						, EstadoRolPagoDetalle.PAGADO.getLetra() );
			}
						
			if ( getRbNormal().isSelected() ){
				if ( tipoRol == TipoRol.QUINCENAL || tipoRol == TipoRol.MENSUAL ){
					cargarTablaRolPagoDetalleQyM(rolPagoDetalleList, modelo);
					getTblRolPagoDetalleQyM().validate();
					getTblRolPagoDetalleQyM().repaint();
					tablaQuincenalMensualSeleccionada = true;
				} else if ( tipoRol == TipoRol.DECIMO_TERCERO || tipoRol == TipoRol.DECIMO_CUARTO 
						|| tipoRol == TipoRol.APORTE_PATRONAL ||  tipoRol == TipoRol.FONDO_RESERVA ){
					cargarTablaRolPagoDetalleAportesDecimos(rolPagoDetalleList, modelo);
					getTblRolPagoDetalleAportesDecimos().validate();
					getTblRolPagoDetalleAportesDecimos().repaint();
					tablaQuincenalMensualSeleccionada = false;
				}
			} else if ( getRbAnticipos().isSelected() ) {
				cargarTablaRolPagoDetalleAnticipos(rolPagoDetalleList, modelo);
				getTblRolPagoDetalleAnticipos().validate();
				getTblRolPagoDetalleAnticipos().repaint();
				tablaQuincenalMensualSeleccionada = false;
			}
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
				|| tipoRol == TipoRol.APORTE_PATRONAL){
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
			Vector<Object> fila = crearFilaTablaRolPagoDetalleAportesDecimos(mapa);
			modelo.addRow(fila);
		}
	}

	private Vector<Object> crearFilaTablaRolPagoDetalleAportesDecimos(Map<String, Object> mapa)
	throws GenericBusinessException {
		String nombreEmpleado = (String)mapa.get("nombreEmpleado");
		Double total = Utilitarios.redondeoValor( (Double)mapa.get("total") );
		Collection<RolPagoDetalleIf> detalles =  (Collection<RolPagoDetalleIf>)mapa.get("detallesRolPago");
		RolPagoDetalleIf detalle = null;
		if ( detalles.size() > 0 )
			detalle = detalles.iterator().next();

		Vector<Object> fila = new Vector<Object>();
		fila.add(false);
		fila.add(nombreEmpleado);
		fila.add(total);
		fila.add(detalle!=null?tipoPagoMap.get(detalle.getTipoPagoId()):" - ");
		fila.add(detalle!=null?cuentaBancariaMap.get(detalle.getCuentaBancariaId()):"");
		fila.add(detalle!=null?detalle.getPreimpreso():"");
		return fila;
	}
	
	private void cargarTablaRolPagoDetalleAnticipos(Collection<Map<String, Object>> rolPagoContratoCollection, DefaultTableModel modelo)
	throws GenericBusinessException {
		for ( Map<String, Object> mapa : rolPagoContratoCollection){
			Vector<Object> fila = crearFilaTablaRolPagoDetalleAnticipos(mapa);
			modelo.addRow(fila);
		}
	}

	private Vector<Object> crearFilaTablaRolPagoDetalleAnticipos(Map<String, Object> mapa)
	throws GenericBusinessException {
		String nombreEmpleado = (String)mapa.get("nombreEmpleado");
		Double total = Utilitarios.redondeoValor( (Double)mapa.get("total") );
		String descripcion = (String) mapa.get("descripcion");
		Collection<RolPagoDetalleIf> detalles =  (Collection<RolPagoDetalleIf>)mapa.get("detallesRolPago");
		RolPagoDetalleIf detalle = null;
		if ( detalles.size() > 0 )
			detalle = detalles.iterator().next();

		Vector<Object> fila = new Vector<Object>();
		fila.add(false);
		fila.add(nombreEmpleado);
		fila.add(descripcion);
		fila.add(total);
		fila.add(detalle!=null?tipoPagoMap.get(detalle.getTipoPagoId()):" - ");
		fila.add(detalle!=null?cuentaBancariaMap.get(detalle.getCuentaBancariaId()):"");
		fila.add(detalle!=null?detalle.getPreimpreso():"");
		return fila;
	}

	private void cargarTablaRolPago() {
		try {
			if ( getRbAnticipos().isSelected() || getRbNormal().isSelected() ){
				ocultarTablasDetalle();
				if ( getRbNormal().isSelected() )
					getSpTblRolPagoDetalleQyM().setVisible(true);
				else if ( getRbAnticipos().isSelected() )
					getSpTblRolPagoDetalleAnticipos().setVisible(true);
				
				rolPagoIfVector = null;
				/*if ( getRbNormal().isSelected() )
					rolPagoIfVector =new ArrayList<RolPagoIf>( SessionServiceLocator
							.getRolPagoSessionService().getRolPagoList(null,
									EstadoRolPagoDetalle.PAGADO.getLetra(),null,null) );*/
				//else 
				//	rolPagoIfVector =new ArrayList<RolPagoIf>(getRolPagoSessionService().getRolPagoAnticiposList(RolPagoDetalle.getLetraEstadoDetalle(EstadoRolPagoDetalle.PAGADO)));
				
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
		}
	}

	private Vector<Object> crearFilaTablaRolPago(RolPagoIf rolPagoIf)
	throws GenericBusinessException {
		TipoRolIf tipoRol = SessionServiceLocator.getTipoRolSessionService().getTipoRol(rolPagoIf.getTiporolId());
		String nombreTipoRol = tipoRol.getNombre();
		int mesInteger = Integer.parseInt( rolPagoIf.getMes() );
		String mesString = Utilitarios.getNombreMes(mesInteger);
		Integer anio = Integer.valueOf( rolPagoIf.getAnio() );
		String estado = rolPagoIf.getEstado().equals(EstadoRolPago.GENERADO.getLetra())
		?EstadoRolPago.GENERADO.toString():EstadoRolPago.CERRADO.toString();
		Vector<Object> fila = new Vector<Object>();
		fila.add(nombreTipoRol);
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
			else{
				rowCount = getTblRolPagoDetalleAportesDecimos().getRowCount();
				modelo = (DefaultTableModel) getTblRolPagoDetalleAportesDecimos().getModel();
				columnaTotal = COLUMNA_DETALLE_AYD_TOTAL;
				columnaTipoPago = COLUMNA_DETALLE_AYD_TIPO_PAGO;
				columnaNumeroCheque = COLUMNA_DETALLE_AYD_NUMERO_CHEQUE;
			}
		} else if ( getRbAnticipos().isSelected() ){
			rowCount = getTblRolPagoDetalleAnticipos().getRowCount();
			modelo = (DefaultTableModel) getTblRolPagoDetalleAnticipos().getModel();
			columnaTotal = COLUMNA_DETALLE_ANT_TOTAL;
			columnaTipoPago = COLUMNA_DETALLE_ANT_TIPO_PAGO;
			columnaNumeroCheque = COLUMNA_DETALLE_ANT_NUMERO_CHEQUE;
		}

		for ( int i = 0 ; i < rowCount ; i++){
			boolean seleccionado = (Boolean) modelo.getValueAt(i, 0);
			if (seleccionado  ){
				
				String nombreEmpleado = (String) modelo.getValueAt(i, COLUMNA_DETALLE_NOMBRE);
				TipoPagoIf tipoPagoIf = (TipoPagoIf)modelo.getValueAt(i, columnaTipoPago);;
				String numeroCheque = null;
				if ( tipoPagoIf.getNombre().contains("CHEQUE") ){
					numeroCheque = (String) modelo.getValueAt(i, columnaNumeroCheque);
					if ( numeroCheque == null || "".equals(numeroCheque.trim()) ){
						SpiritAlert.createAlert("Ingresar número de cheque para "+nombreEmpleado, SpiritAlert.WARNING);
						return false;	
					}	
				}

				Map<String, Object> rolPagoDetalleSeleccionadosMapa = new HashMap<String, Object>();
				Map<String, Object> mapa = rolPagoDetalleList.get(i);

				Collection<RolPagoDetalleIf> detallesRolPago = (Collection<RolPagoDetalleIf>) mapa.get("detallesRolPago");
				rolPagoDetalleSeleccionadosMapa.put("rolPagoDetalleCollection",detallesRolPago);

				Double total = (Double) modelo.getValueAt(i, columnaTotal);
				total = Utilitarios.redondeoValor(total);
				rolPagoDetalleSeleccionadosMapa.put("total",total);

				String nombre = (String) modelo.getValueAt(i, COLUMNA_DETALLE_NOMBRE);
				rolPagoDetalleSeleccionadosMapa.put("nombre",nombre);

				rolPagoDetalleSeleccionadosMapa.put("tipoPagoIf",tipoPagoIf);

				//numeroCheque = (String) modelo.getValueAt(i, columnaNumeroCheque);
				if ( numeroCheque!=null )
					numeroCheque = numeroCheque.trim();
				rolPagoDetalleSeleccionadosMapa.put("numeroCheque",numeroCheque);
				TipoPagoIf tipoPago = (TipoPagoIf)modelo.getValueAt(i, columnaTipoPago);
				if ( tipoPago.getNombre().contains("CHEQUE") && ( numeroCheque ==null || "".equals(numeroCheque) ) ){
					SpiritAlert.createAlert("Ingresar número de cheque para "+nombre, SpiritAlert.WARNING);
					return false;
				}

				rolPagoDetalleSeleccionadosList.add(rolPagoDetalleSeleccionadosMapa);
			}
		}
		return true;
	}

	@Override
	public void clean() {
		rolPagoIfVector = null;
		rolPagoIf = null;
		rolPagoDetalleList = null;
		rolPagoDetalleSeleccionadosList = null;

		ocultarTablasDetalle();
		
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
					SessionServiceLocator.getRolPagoSessionService().procesarPagoRol(fechaHoy,rolPagoIf, rolPagoDetalleSeleccionadosList);
					SpiritAlert.createAlert("Generación de Pagos exitoso !!", SpiritAlert.INFORMATION);
					generarCheques(rolPagoDetalleSeleccionadosList);
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
			Iterator rolPagoDetalleSeleccionadosListIt = rolPagoDetalleSeleccionadosList.iterator();
			while(rolPagoDetalleSeleccionadosListIt.hasNext()){
				Map<String,Object> rolPagoDetalleSeleccionado = (Map<String,Object>)rolPagoDetalleSeleccionadosListIt.next();
				Double totalCheque = (Double)rolPagoDetalleSeleccionado.get("total");
				
				if (totalCheque.compareTo(0D) != 0) {
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					String valorCheque = formatoDecimal.format(Double.valueOf(totalCheque));
					String parteDecimal = valorCheque.substring(valorCheque.indexOf('.'), valorCheque.length());
					Double dParteDecimal = 0.0;
					if (!parteDecimal.isEmpty())
						dParteDecimal = Double.valueOf(parteDecimal);
					String pagueseA = (String)rolPagoDetalleSeleccionado.get("nombre");
					String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(valorCheque, dParteDecimal, new int[] { 70, 90 }, false, Parametros.getMonedaPredeterminada());
					String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
					String cantidadLetrasSegundaLinea = cantidadLetras[1].replaceAll("  ","");
					String lugarFecha = ciudad.getNombre() + ", " + Utilitarios.getFechaUppercase(Utilitarios.dateHoy());
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
