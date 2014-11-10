package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
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
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.panel.JPGeneracionAsientoRubrosEventuales;
import com.spirit.nomina.gui.util.EstadoRolPago;
import com.spirit.nomina.gui.util.TipoPagoCellEditor;
import com.spirit.nomina.gui.util.TipoRolUtil;
import com.spirit.nomina.handler.EstadoRolPagoDetalle;
import com.spirit.nomina.handler.EstadoRubroEventual;
import com.spirit.nomina.handler.TipoRol;
import com.spirit.util.Utilitarios;

public class GeneracionAsientoRubrosEventualesModel extends JPGeneracionAsientoRubrosEventuales {

	private static final long serialVersionUID = 2260850962611203314L;

	private static final int COLUMNA_DETALLE_SELECCION = 0;
	private static final int COLUMNA_DETALLE_NOMBRE = 1;
	
	private static final int COLUMNA_DETALLE_ANT_TOTAL = 2;
	private static final int COLUMNA_DETALLE_ANT_BENFICIARIO = 3;
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

	private Map<Long,TipoContratoIf> mapaTiposContrato = new HashMap<Long,TipoContratoIf>();
	private Map<Long,TipoRolIf> mapaTiposRol = new HashMap<Long,TipoRolIf>();

	private static Map panels;
	
	public GeneracionAsientoRubrosEventualesModel(){
		panels = MainFrameModel.get_panels();
		iniciarComponentes();
		setAnchoComlumnas();
		initListener();
		showSaveMode();
		new HotKeyComponent(this);
	}

	private void iniciarComponentes(){
		getPanelDetalleRolPago().add(getSpTblRolPagoDetalleAnticipos(), new CellConstraints().xywh(1, 7, 3, 3));
		
		//Tabla Rol de Pago
		setSorterTable(getTblRolPago());

		cargarTipoPagos();
		
		cargarCuentasBancarias();
		
		//Tabla Rol de Pago DEtalle ANTICIPOS
		setSorterTable(getTblRolPagoDetalleAnticipos());
		getTblRolPagoDetalleAnticipos().getTableHeader().setReorderingAllowed(false);
		getTblRolPagoDetalleAnticipos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_TOTAL).setCellRenderer(new TableCellRendererHorizontalRightAlignment());
		getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_TOTAL).setCellRenderer(
				new NumberCellRenderer("######.00",NumberCellRenderer.DERECHA) );
		
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
	
	
	private void setAnchoComlumnas(){
		
		//TABLA DE ANTICIPOS
		TableColumn anchoColumna = getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_SELECCION);
		anchoColumna.setPreferredWidth(45);
		anchoColumna = getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_NOMBRE);
		anchoColumna.setPreferredWidth(280);
		anchoColumna = getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_TOTAL);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_TIPO_PAGO);
		anchoColumna.setPreferredWidth(95);
		anchoColumna = getTblRolPagoDetalleAnticipos().getColumnModel().getColumn(COLUMNA_DETALLE_ANT_BENFICIARIO);
		anchoColumna.setPreferredWidth(310);
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
		getTblRolPagoDetalleAnticipos().addMouseListener(ppl);

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
			SpiritAlert.createAlert("Por favor selecciona una fila !!", SpiritAlert.INFORMATION);
		}
	}
	
	private void seleccionarFila() {
		rolPagoIf = null;
		limpiarTabla( getTblRolPagoDetalleAnticipos() );

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
		rolPagoDetalleSeleccionadosList = null;
		rolPagoDetalleList = null;
		DefaultTableModel modelo = establecerTabla(tipoRol);
		
		if ( modelo != null ){
			
			Map<String, Object> mapaAnticipos = new HashMap<String, Object>();
			//mapaAnticipos.put("rolpagoId",rolPagoIf.getId());
			mapaAnticipos.put("tipoRolIdPago",rolPagoIf.getTiporolId());
			mapaAnticipos.put("mesPago",rolPagoIf.getMes());
			mapaAnticipos.put("anioPago",rolPagoIf.getAnio());
			
			rolPagoDetalleList = (ArrayList<Map<String, Object>>)SessionServiceLocator.getRolPagoDetalleSessionService()
			.crearColeccionAnticiposRolPagoDetalleByQueryByEstadosConEventuales(mapaAnticipos
					, EstadoRolPagoDetalle.PAGADO.getLetra() );
			
			cargarTablaRolPagoDetalleAnticipos(rolPagoDetalleList, modelo);
			getTblRolPagoDetalleAnticipos().validate();
			getTblRolPagoDetalleAnticipos().repaint();
			tablaQuincenalMensualSeleccionada = false;
			
		}
	}

	private DefaultTableModel establecerTabla(TipoRol tipoRol){
		if ( tipoRol == TipoRol.QUINCENAL || tipoRol == TipoRol.MENSUAL ){
			tablaSeleccionada = getTblRolPagoDetalleAnticipos();
			getSpTblRolPagoDetalleAnticipos().setVisible(true);
			return (DefaultTableModel)getTblRolPagoDetalleAnticipos().getModel();
			
		} else
			SpiritAlert.createAlert("Tipo de Rol no considerado para presentación en tabla de detalle !!", SpiritAlert.WARNING);
		return null;
	}

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
			getSpTblRolPagoDetalleAnticipos().setVisible(true);
			
			rolPagoIfVector = null;
			//ArrayList<RolPagoIf> rolesTemp = (ArrayList<RolPagoIf>) SessionServiceLocator.getRolPagoSessionService()
			//	.getRolPagoAnticiposList(EstadoRolPagoDetalle.PAGADO.getLetra() );
			ArrayList<RolPagoIf> rolesTemp = (ArrayList<RolPagoIf>) SessionServiceLocator.getRubroEventualSessionService()
				.getRolPagoAnticiposList(EstadoRubroEventual.PAGADO.getLetra());
			
			rolPagoIfVector = new ArrayList<RolPagoIf>();
			for ( RolPagoIf r : rolesTemp ){
				if ( !contieneRolPago(rolPagoIfVector,r) ){
					rolPagoIfVector.add(r);
				}
			}
			
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
		int mesInteger = Integer.parseInt( rolPagoIf.getMes() );
		String mesString = Utilitarios.getNombreMes(mesInteger);
		Integer anio = Integer.valueOf( rolPagoIf.getAnio() );
		String estado = rolPagoIf.getEstado().equals(EstadoRolPago.GENERADO.getLetra())
		?EstadoRolPago.GENERADO.toString():EstadoRolPago.CERRADO.toString();
		Vector<Object> fila = new Vector<Object>();
		fila.add(nombreTipoRol);
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
		
		rowCount = getTblRolPagoDetalleAnticipos().getRowCount();
		modelo = (DefaultTableModel) getTblRolPagoDetalleAnticipos().getModel();
		columnaTotal = COLUMNA_DETALLE_ANT_TOTAL;
		columnaTipoPago = COLUMNA_DETALLE_ANT_TIPO_PAGO;
		columnaNumeroCheque = COLUMNA_DETALLE_ANT_NUMERO_CHEQUE;
		
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
				beneficiario = (String) modelo.getValueAt(i, COLUMNA_DETALLE_ANT_BENFICIARIO);
				
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
				rolPagoDetalleSeleccionadosMapa.put("beneficiario", beneficiario);
				rolPagoDetalleSeleccionadosMapa.put("nombreRubro", nombre);
				
				
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
		
		limpiarTabla(getTblRolPago());
		limpiarTabla(getTblRolPagoDetalleAnticipos());
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
					
					SessionServiceLocator.getRolPagoSessionService().generarAsientoRubrosEventales(fechaHoy,rolPagoIf, rolPagoDetalleSeleccionadosList);
					
					SpiritAlert.createAlert("Generación de Asiento exitoso !!", SpiritAlert.INFORMATION);
					
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
