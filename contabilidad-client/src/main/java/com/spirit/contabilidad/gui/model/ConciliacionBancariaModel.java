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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.ChequeEmitidoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.SaldoCuentaIf;
import com.spirit.contabilidad.gui.data.CuentaBancariaData;
import com.spirit.contabilidad.gui.data.CuentaBancariaEgresoData;
import com.spirit.contabilidad.gui.data.CuentaBancariaIngresoData;
import com.spirit.contabilidad.gui.panel.JPConciliacionBancaria;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.NuevoCuentaBancariaCriteria;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.DeepCopy;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;
 
public class ConciliacionBancariaModel extends JPConciliacionBancaria {

	private static final long serialVersionUID = 8655695343925923047L;
	
	ArrayList<CuentaBancariaIf> cuentaBancariaArrayList =  new ArrayList<CuentaBancariaIf>();
	CuentaBancariaIf cuentaBancariaIf = null;
	private DefaultTableModel tableModel;
	private Map clientesMap = new HashMap();
	private Map clienteOficinasMap = new HashMap();
	private Map tiposDocumentoMap = new HashMap();
	private Map documentosMap = new HashMap();
	private Map modulosMap = new HashMap();
	private int selectedRow = -1;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");

	public ConciliacionBancariaModel() {
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
		Collection<CuentaBancariaData> datosConciliacionBancaria = crearConciliacionCuentaBancaria(selectedRow);
		CuentaBancariaData conciliacionBancaria = datosConciliacionBancaria.iterator().next();
		tableModel = (DefaultTableModel) getTblDetalleConciliacion().getModel();
		if (conciliacionBancaria != null) {
			// Saldo inicial
			Vector<String> fila = new Vector<String>();			 
			fila.add("<html><b>" + "SALDO INICIAL EN LIBROS" + "</b></html>");
			fila.add("");
			fila.add("");		
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(conciliacionBancaria.getSaldoInicial())) + "</b></html>");
			tableModel.addRow(fila);
			double totalEnLibros = conciliacionBancaria.getSaldoInicial();
			//  Ingresos
			fila = new Vector<String>();
			fila.add("<html><b>" + "(+) INGRESOS" + "</b></html>");
			fila.add("");
			fila.add("");
			fila.add("");
			tableModel.addRow(fila);
			double totalIngresos = 0D;
			for (int i=0; i<conciliacionBancaria.getIngresosConciliacionBancaria().size(); i++) {
				CuentaBancariaIngresoData ingresoConciliacionBancaria = conciliacionBancaria.getIngresosConciliacionBancaria().get(i);
				fila = new Vector<String>();
				fila.add(ingresoConciliacionBancaria.getDetalle());
				fila.add(ingresoConciliacionBancaria.getCodigoOperacion());
				fila.add(getNumeroChequeParaTabla(ingresoConciliacionBancaria.getNumeroCheque()));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(ingresoConciliacionBancaria.getValor())));
				totalIngresos += ingresoConciliacionBancaria.getValor();
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
			for (int i=0; i<conciliacionBancaria.getEgresosConciliacionBancaria().size(); i++) {
				CuentaBancariaEgresoData egresoConciliacionBancaria = conciliacionBancaria.getEgresosConciliacionBancaria().get(i);
				fila = new Vector<String>();
				fila.add(egresoConciliacionBancaria.getDetalle());
				fila.add(egresoConciliacionBancaria.getCodigoOperacion());
				fila.add(getNumeroChequeParaTabla(egresoConciliacionBancaria.getNumeroCheque()));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(egresoConciliacionBancaria.getValor())));
				totalEgresos += egresoConciliacionBancaria.getValor();
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
			// Egresos anulados
			fila = new Vector<String>();
			fila.add("<html><b>" + "(+) CHEQUES ANULADOS" + "</b></html>");
			fila.add("");
			fila.add("");
			fila.add("");
			tableModel.addRow(fila);
			double totalChequesAnulados = 0D;
			for (int i=0; i<conciliacionBancaria.getEgresosAnuladosConciliacionBancaria().size(); i++) {
				CuentaBancariaEgresoData egresoAnuladoConciliacionBancaria = conciliacionBancaria.getEgresosAnuladosConciliacionBancaria().get(i);
				fila = new Vector<String>();
				fila.add(egresoAnuladoConciliacionBancaria.getDetalle());
				fila.add(egresoAnuladoConciliacionBancaria.getCodigoOperacion());
				fila.add(getNumeroChequeParaTabla(egresoAnuladoConciliacionBancaria.getNumeroCheque()));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(egresoAnuladoConciliacionBancaria.getValor())));
				totalChequesAnulados += egresoAnuladoConciliacionBancaria.getValor();
				tableModel.addRow(fila);
			}
			fila = new Vector<String>();
			fila.add("");
			fila.add("");
			fila.add("<html><b>" + "TOTAL CHEQ. ANUL." + "</b></html>");
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(totalChequesAnulados)) + "</b></html>");
			tableModel.addRow(fila);
			totalEnLibros += totalChequesAnulados;
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
			for (int i=0; i<conciliacionBancaria.getChequesEmitidosConciliacionBancaria().size(); i++) {
				CuentaBancariaIngresoData chequeEmitidoConciliacionBancaria = conciliacionBancaria.getChequesEmitidosConciliacionBancaria().get(i);
				fila = new Vector<String>();
				fila.add(chequeEmitidoConciliacionBancaria.getDetalle());
				fila.add(chequeEmitidoConciliacionBancaria.getCodigoOperacion());
				fila.add(getNumeroChequeParaTabla(chequeEmitidoConciliacionBancaria.getNumeroCheque()));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(chequeEmitidoConciliacionBancaria.getValor())));
				totalChequesEmitidos += chequeEmitidoConciliacionBancaria.getValor();
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
			// Notas de débito
			fila = new Vector<String>();			 
			fila.add("<html><b>" + "NOTAS DE DEBITO" + "</b></html>");
			tableModel.addRow(fila);
			double totalNotasDebito = 0D;
			fila = new Vector<String>();
			fila.add("<html><b>" + "TOTAL NOTAS DEBITO" + "</b></html>");
			fila.add("");
			fila.add("");			
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(totalNotasDebito)) + "</b></html>");
			tableModel.addRow(fila);
			fila = new Vector<String>();
			fila.add("");
			fila.add("");
			fila.add("");
			fila.add("");
			tableModel.addRow(fila);
			// Saldo en bancos
			double saldoBanco = totalEnLibros + totalChequesEmitidos - totalNotasDebito;
			fila = new Vector<String>();
			fila.add("<html><b>" + "SALDO EN BANCO" + "</b></html>");
			fila.add("");
			fila.add("");			
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(saldoBanco)) + "</b></html>");
			tableModel.addRow(fila);
			// Saldo en libros
			fila = new Vector<String>();
			fila.add("<html><b>" + "SALDO EN LIBROS" + "</b></html>");
			fila.add("");
			fila.add("");			
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(totalEnLibros)) + "</b></html>");
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
				String siCompleto = "Sí, Completo";
				String siTotales =  "Sí, Totales";
				Object[] opciones = { siCompleto, siTotales, "No" };
		        
		        int opcion = JOptionPane.showOptionDialog(null,
						"¿Desea generar el reporte?",
						"Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, opciones, "Si");
				
				int opcionSiCompleto = 0;
				int opcionSiTotales = 1;
				int opcionNo = 2;
				
				if(opcion != opcionNo){
					String fileName = "jaspers/contabilidad/RPConciliacionBancaria.jasper";
					if (opcion == opcionSiTotales) {
						fileName = "jaspers/contabilidad/RPConciliacionBancariaGerencia.jasper";
					}
					
					HashMap parametrosMap = new HashMap();
					//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("CONCILIACION BANCARIA").iterator().next();
					
					MenuIf menu = null;
					Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre("CONCILIACION BANCARIA").iterator();
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
					
					parametrosMap.put("etiquetaIngresos", " (+) INGRESOS "  );
					parametrosMap.put("etiquetaEgresos", " (-) EGRESOS "  );
					parametrosMap.put("etiquetaChequesAnulados", " (+) CHEQUES ANULADOS "  );
					parametrosMap.put("etiquetaChequesEmitidos", " CHEQUES EN CIRCULACION "  );
					parametrosMap.put("etiquetaNotasDebitos", " NOTAS DE DEBITO "  );
					
					if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) ) {
						parametrosMap.put("pathSubreportIngresos", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/contabilidad/RPConciliacionBancariaIngresos.jasper");
						parametrosMap.put("pathSubreportEgresos", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/contabilidad/RPConciliacionBancariaEgresos.jasper");
						parametrosMap.put("pathSubreportChequesAnulados", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/contabilidad/RPConciliacionBancariaChequesAnulados.jasper");
						parametrosMap.put("pathSubreportChequesEmitidos", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/contabilidad/RPConciliacionBancariaChequesEmitidos.jasper");
						parametrosMap.put("pathSubreportNotasDebitos", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/contabilidad/RPConciliacionBancariaNotasDebitos.jasper");
					}						
					else 
						throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
										
					Collection<CuentaBancariaData> datosConciliacionBancaria = crearConciliacionCuentaBancaria(-1);
					
					JRDataSource cuentasContables = new JRBeanCollectionDataSource(datosConciliacionBancaria);
					
					ReportModelImpl.processReport(fileName, parametrosMap, cuentasContables, true);
					datosConciliacionBancaria = null;
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

	private Collection<CuentaBancariaData> crearConciliacionCuentaBancaria(int filaSeleccionada) throws GenericBusinessException {
		DefaultTableModel modelo = (DefaultTableModel) getTblCuentaBancaria().getModel();
		Calendar calendarFechaInicio = getCmbFechaInicio().getCalendar();
		Date fechaInicio = getCmbFechaInicio().getDate();
		java.sql.Date fechaInicioSql = Utilitarios.fromTimestampToSqlDate(Utilitarios.resetTimestampStartDate(new java.sql.Timestamp(fechaInicio.getTime())));
		Date fechaFin = getCmbFechaFin().getDate();
		java.sql.Date fechaFinSql = Utilitarios.fromTimestampToSqlDate(Utilitarios.resetTimestampEndDate(new java.sql.Timestamp(fechaFin.getTime())));
		Map carterasMap = mapearCarterasMap(fechaInicioSql, fechaFinSql);
		Map carteraDetallesMap = mapearCarteraDetallesMap(fechaInicioSql, fechaFinSql);
		ArrayList<CuentaBancariaData> datosConciliacionBancaria = new ArrayList<CuentaBancariaData>();
		int startIndex = 0;
		int endIndex = getTblCuentaBancaria().getRowCount();
		if (filaSeleccionada > 0) {
			startIndex = filaSeleccionada;
			endIndex = filaSeleccionada + 1;
		}
		for ( int filaTabla = startIndex ; filaTabla < endIndex ; filaTabla++ ) {			
			CuentaBancariaIf cuentaBancaria = cuentaBancariaArrayList.get(filaTabla);
			CuentaBancariaData cuentaBancariaData =  new CuentaBancariaData();
			cuentaBancariaData.setIdCuentaBancaria(cuentaBancaria.getId());
			Collection<CuentaIf> cuentasContables = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaDeCuentaBancariaByCuentaBancariaId(cuentaBancaria.getId());
			if (cuentasContables.size() == 0)
				throw new GenericBusinessException( "No existe Cuenta asociada a cuenta Bancaria \""+cuentaBancaria.getCuenta()+"\" !!" );
			else if (cuentasContables.size() > 1)
				throw new GenericBusinessException( "Existe mas de una Cuenta asociada a cuenta Bancaria \""+cuentaBancaria.getCuenta()+"\" !!" );
			CuentaIf cuentaIf = cuentasContables.iterator().next();
			String nombreBanco = (String) modelo.getValueAt(filaTabla, 0);
			cuentaBancariaData.setNombreBanco(nombreBanco);
			String codigoCuentaBancaria = (String) modelo.getValueAt(filaTabla, 1);
			cuentaBancariaData.setCodigoCuentaBancaria(codigoCuentaBancaria);
			
			JRBeanCollectionDataSource datosReporte = new JRBeanCollectionDataSource(new ArrayList());
			Collection<Object[]> asientosConciliacionBancaria = null;
			if (getRbSiVersionExtendida().isSelected())
				asientosConciliacionBancaria = SessionServiceLocator.getAsientoSessionService().findAsientosConciliacionBancariaExtendida(cuentaIf.getId(), fechaInicioSql, fechaFinSql);
			else
				asientosConciliacionBancaria = SessionServiceLocator.getAsientoSessionService().findAsientosConciliacionBancaria(cuentaIf.getId(), fechaInicioSql, fechaFinSql);
			
			//INGRESOS CONCILIACION BANCARIA
			ArrayList<CuentaBancariaIngresoData> ingresosConciliacionBancaria = new ArrayList<CuentaBancariaIngresoData>();
			double totalIngresos = 0D;
			Iterator<Object[]> ingresosIterator = asientosConciliacionBancaria.iterator();
			while (ingresosIterator.hasNext()) {
				Object[] obj = ingresosIterator.next();
				CuentaBancariaIngresoData ingresoConciliacionBancaria = obtenerDatosIngresosConciliacionBancaria(obj, carterasMap);
				if (ingresoConciliacionBancaria != null && ingresoConciliacionBancaria.getValor() > 0D) {
					ingresosConciliacionBancaria.add(ingresoConciliacionBancaria);
					totalIngresos += ingresoConciliacionBancaria.getValor();
				}
			}
			ingresosConciliacionBancaria = ordenarIngresosConciliacionBancaria(ingresosConciliacionBancaria);
			datosReporte = new JRBeanCollectionDataSource(ingresosConciliacionBancaria);
			cuentaBancariaData.setIngresosDetalles(datosReporte);
			cuentaBancariaData.setIngresosConciliacionBancaria(ingresosConciliacionBancaria);
			cuentaBancariaData.setTotalIngresos(Utilitarios.redondeoValor(totalIngresos));
			// EGRESOS CONCILIACION BANCARIA
			ArrayList<CuentaBancariaEgresoData> egresosConciliacionBancaria = new ArrayList<CuentaBancariaEgresoData>();
			double totalEgresos = 0D;
			Iterator<Object[]> egresosIterator = asientosConciliacionBancaria.iterator();
			while (egresosIterator.hasNext()) {
				Object[] obj = egresosIterator.next();
				
				if(obj[0].equals("CRE-PC-01-2013-01323")){
					System.out.println("a");
				}
				
				Vector<CuentaBancariaEgresoData> egresoConciliacionBancariaVector = obtenerDatosEgresosConciliacionBancaria(obj, cuentaBancaria, carterasMap, carteraDetallesMap, false);
				for (int i=0; i<egresoConciliacionBancariaVector.size(); i++) {
					CuentaBancariaEgresoData egresoConciliacionBancaria = egresoConciliacionBancariaVector.get(i);
					if (egresoConciliacionBancaria.getValor() > 0D && egresoConciliacionBancaria.getNumeroCheque() != null) {
						egresosConciliacionBancaria.add(egresoConciliacionBancaria);
						totalEgresos += egresoConciliacionBancaria.getValor();
					}
				}
			}
			// La búsqueda findAsientosConciliacionBancaria no trae transacciones anuladas.
			// Las transacciones anuladas mediante la antigua pantalla de cartera no tienen asientos.
			Collection<Object[]> transaccionesAnuladasConciliacionBancaria = SessionServiceLocator.getCarteraSessionService().findTransaccionesAnuladasConciliacionBancaria(Parametros.getIdEmpresa(), fechaInicioSql, fechaFinSql);
			ArrayList<CuentaBancariaEgresoData> egresosAnuladosConciliacionBancaria = new ArrayList<CuentaBancariaEgresoData>();
			double totalChequesAnulados = 0D;
			Iterator<Object[]> egresosAnuladosIterator = transaccionesAnuladasConciliacionBancaria.iterator();
			while (egresosAnuladosIterator.hasNext()) {
				Object[] obj = egresosAnuladosIterator.next();
				Vector<CuentaBancariaEgresoData> egresoConciliacionBancariaVector = obtenerDatosEgresosConciliacionBancaria(obj, cuentaBancaria, carterasMap, carteraDetallesMap, true);
				for (int i=0; i<egresoConciliacionBancariaVector.size(); i++) {
					CuentaBancariaEgresoData egresoConciliacionBancaria = egresoConciliacionBancariaVector.get(i);
					if (egresoConciliacionBancaria.getValor() > 0D && egresoConciliacionBancaria.getNumeroCheque() != null) {
						egresosConciliacionBancaria.add(egresoConciliacionBancaria);
						egresosAnuladosConciliacionBancaria.add(egresoConciliacionBancaria);
						totalEgresos += egresoConciliacionBancaria.getValor();
						totalChequesAnulados += egresoConciliacionBancaria.getValor();
					}
				}
			}			
			Map queryMap = new HashMap();
			queryMap.put("cuentaBancariaId", cuentaBancaria.getId());
			queryMap.put("origen", "A");
			queryMap.put("estado", "A");
			Iterator<ChequeEmitidoIf> chequesAnuladosSinAsientoConciliacionBancariaIt = SessionServiceLocator.getChequeEmitidoSessionService().findChequeEmitidoByQuery(queryMap).iterator();
			while (chequesAnuladosSinAsientoConciliacionBancariaIt.hasNext()) {
				ChequeEmitidoIf chequeEmitido = chequesAnuladosSinAsientoConciliacionBancariaIt.next();
				if (!chequeEmitido.getFechaEmision().before(fechaInicioSql) && !chequeEmitido.getFechaEmision().after(fechaFinSql)) {
					CuentaBancariaEgresoData egresoConciliacionBancaria = new CuentaBancariaEgresoData();
					egresoConciliacionBancaria.setCodigoOperacion("");
					egresoConciliacionBancaria.setDetalle(chequeEmitido.getFechaEmision() + " " + chequeEmitido.getDetalle() + " (ANULADO) ");
					egresoConciliacionBancaria.setNumeroCheque(chequeEmitido.getNumero());
					egresoConciliacionBancaria.setValor(chequeEmitido.getValor().doubleValue());
					egresosConciliacionBancaria.add(egresoConciliacionBancaria);
					egresosAnuladosConciliacionBancaria.add(egresoConciliacionBancaria);
					totalEgresos += egresoConciliacionBancaria.getValor();
					totalChequesAnulados += egresoConciliacionBancaria.getValor();
				}
			}
			queryMap = new HashMap();
			queryMap.put("cuentaBancariaId", cuentaBancaria.getId());
			queryMap.put("origen", "N");
			queryMap.put("estado", "A");
			Iterator<ChequeEmitidoIf> chequesAnuladosNominaConciliacionBancariaIt = SessionServiceLocator.getChequeEmitidoSessionService().findChequeEmitidoByQuery(queryMap).iterator();
			while (chequesAnuladosNominaConciliacionBancariaIt.hasNext()) {
				ChequeEmitidoIf chequeEmitido = chequesAnuladosNominaConciliacionBancariaIt.next();
				if (!chequeEmitido.getFechaEmision().before(fechaInicioSql) && !chequeEmitido.getFechaEmision().after(fechaFinSql)) {
					CuentaBancariaEgresoData egresoConciliacionBancaria = new CuentaBancariaEgresoData();
					egresoConciliacionBancaria.setCodigoOperacion("");
					egresoConciliacionBancaria.setDetalle(chequeEmitido.getFechaEmision() + " " + chequeEmitido.getDetalle() + " (ANULADO) ");
					egresoConciliacionBancaria.setNumeroCheque(chequeEmitido.getNumero());
					egresoConciliacionBancaria.setValor(chequeEmitido.getValor().doubleValue());
					egresosConciliacionBancaria.add(egresoConciliacionBancaria);
					egresosAnuladosConciliacionBancaria.add(egresoConciliacionBancaria);
					totalEgresos += egresoConciliacionBancaria.getValor();
					totalChequesAnulados += egresoConciliacionBancaria.getValor();
				}
			}
			
			egresosConciliacionBancaria = ordenarEgresosConciliacionBancaria(egresosConciliacionBancaria);
			datosReporte = new JRBeanCollectionDataSource(egresosConciliacionBancaria);
			cuentaBancariaData.setEgresosDetalles(datosReporte);
			cuentaBancariaData.setEgresosConciliacionBancaria(egresosConciliacionBancaria);
			cuentaBancariaData.setTotalEgresos(Utilitarios.redondeoValor(totalEgresos));
			
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
				cuentaBancariaData.setSaldoInicial(totalSaldoCuenta);
			} else if (periodos.size() == 0) {
				cuentaBancariaData.setSaldoInicial(0D);
			}

			egresosAnuladosConciliacionBancaria = ordenarEgresosConciliacionBancaria(egresosAnuladosConciliacionBancaria);
			datosReporte = new JRBeanCollectionDataSource(egresosAnuladosConciliacionBancaria);
			cuentaBancariaData.setChequesAnuladosDetalles(datosReporte);
			cuentaBancariaData.setEgresosAnuladosConciliacionBancaria(egresosAnuladosConciliacionBancaria);
			cuentaBancariaData.setTotalChequesAnulados(totalChequesAnulados);
			ArrayList<CuentaBancariaIngresoData> chequesEmitidosConciliacionBancaria = new ArrayList<CuentaBancariaIngresoData>();
			Collection<Object[]> chequesEmitidos = SessionServiceLocator.getChequeEmitidoSessionService().findChequesEmitidosConciliacionBancaria(cuentaBancaria.getId(), fechaInicioSql, fechaFinSql);
			double totalChequesEmitidos = 0D;
			Iterator<Object[]> chequesEmitidosIterator = chequesEmitidos.iterator();
			while (chequesEmitidosIterator.hasNext()) {
				Object[] obj = chequesEmitidosIterator.next();
				CuentaBancariaIngresoData chequeEmitidoConciliacionBancaria = obtenerDatosChequesEmitidosConciliacionBancaria(obj);
				if (chequeEmitidoConciliacionBancaria.getValor() > 0D) {
					chequesEmitidosConciliacionBancaria.add(chequeEmitidoConciliacionBancaria);
					totalChequesEmitidos += chequeEmitidoConciliacionBancaria.getValor();
				}
			}

			chequesEmitidosConciliacionBancaria = ordenarChequesEmitidosConciliacionBancaria(chequesEmitidosConciliacionBancaria);
			datosReporte = new JRBeanCollectionDataSource(chequesEmitidosConciliacionBancaria);
			cuentaBancariaData.setChequesEmitidosDetalles(datosReporte);
			cuentaBancariaData.setChequesEmitidosConciliacionBancaria(chequesEmitidosConciliacionBancaria);
			cuentaBancariaData.setTotalChequesEmitidos(totalChequesEmitidos);
			datosReporte = new JRBeanCollectionDataSource(new ArrayList());
			cuentaBancariaData.setNotasDebitosDetalles(datosReporte);
			cuentaBancariaData.setNotasDebitoConciliacionBancaria(new ArrayList());
			cuentaBancariaData.setTotalNotasDebitos(0D);
			//----
			datosConciliacionBancaria.add(cuentaBancariaData);
		}
		
		return datosConciliacionBancaria;
	}
	
	@SuppressWarnings("unchecked")
	private Vector<CuentaBancariaEgresoData> obtenerDatosEgresosConciliacionBancaria(Object[] obj, CuentaBancariaIf cuentaBancaria, Map carterasMap, Map carteraDetallesMap, boolean buscarChequesAnulados) throws GenericBusinessException {
		Vector<CuentaBancariaEgresoData> cbeVector = new Vector<CuentaBancariaEgresoData>();
		CarteraIf cartera = null;
		if (obj[6] != null && obj[7] != null) {
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get((Long) obj[6]);
			ModuloIf modulo = (ModuloIf) modulosMap.get(tipoDocumento.getModuloId());
			if (modulo.getCodigo().equals("CART")) {
				cartera = (CarteraIf) carterasMap.get((Long) obj[7]);
			}
		}
		if (!buscarChequesAnulados && cartera == null) {
			CuentaBancariaEgresoData cbe = new CuentaBancariaEgresoData();
			cbe.setCodigoOperacion((String)obj[0]);
			cbe.setDetalle(obj[1] + " " + (String)obj[2]);
			String numeroCheque = (String) obj[3];
			cbe.setNumeroCheque( numeroCheque!=null?numeroCheque:"-1" );
			cbe.setValor(((BigDecimal)obj[5]).doubleValue());
			cbeVector.add(cbe);
		} else {
			String filtroEstado = !buscarChequesAnulados?"N":"A";
			if (cartera != null && cartera.getEstado().equals(filtroEstado) && ((BigDecimal)obj[5]).doubleValue() > 0D && cartera.getTipo().equals("P")) {
				Map chequesMap = new HashMap();
				Map detallesPorCarteraIdMap = (Map) carteraDetallesMap.get(cartera.getId());
				Iterator it = detallesPorCarteraIdMap.keySet().iterator();
				while (it.hasNext()) {
					Long carteraDetalleId = (Long) it.next();
					CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) detallesPorCarteraIdMap.get(carteraDetalleId);
					DocumentoIf documento = (DocumentoIf) documentosMap.get(carteraDetalle.getDocumentoId());
					if (agregarEgreso(cuentaBancaria, documento, carteraDetalle, buscarChequesAnulados)) {
						if (!documento.getCheque().equals("S")) {
							CuentaBancariaEgresoData cbe = new CuentaBancariaEgresoData();
							cbe.setCodigoOperacion(cartera.getCodigo());
							ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clienteOficinasMap.get(cartera.getClienteoficinaId());
							ClienteIf cliente = (ClienteIf) clientesMap.get(clienteOficina.getClienteId());
							cbe.setDetalle(obj[1] + " " + cliente.getRazonSocial());
							cbe.setNumeroCheque( "-1" );
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
						CuentaBancariaEgresoData cbe = new CuentaBancariaEgresoData();
						cbe.setCodigoOperacion(cartera.getCodigo());
						ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clienteOficinasMap.get(cartera.getClienteoficinaId());
						ClienteIf cliente = (ClienteIf) clientesMap.get(clienteOficina.getClienteId());
						cbe.setDetalle(obj[1] + " " + cliente.getRazonSocial() + ((buscarChequesAnulados)?" (ANULADO) ":""));
						cbe.setNumeroCheque(cheque);
						cbe.setValor(((BigDecimal) chequesMap.get(cheque)).doubleValue());
						cbeVector.add(cbe);
					}
				}
			}
		}
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
	private CuentaBancariaIngresoData obtenerDatosIngresosConciliacionBancaria(Object[] obj, Map carterasMap) throws GenericBusinessException {
		CarteraIf cartera = null;
		if (obj[6] != null && obj[7] != null) {
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get((Long) obj[6]);
			ModuloIf modulo = (ModuloIf) modulosMap.get(tipoDocumento.getModuloId());
			if (modulo.getCodigo().equals("CART"))
				cartera = (CarteraIf) carterasMap.get((Long) obj[7]);
		}
		CuentaBancariaIngresoData cbi = new CuentaBancariaIngresoData();
		if (cartera == null || (cartera != null && cartera.getEstado().equals("N"))) {
			cbi.setCodigoOperacion((cartera!=null)?cartera.getCodigo():(String)obj[0]);
			cbi.setDetalle(obj[1] + " " + (String)obj[2]);
			String numeroCheque = null;
			if (getRbSiVersionExtendida().isSelected())
				numeroCheque = (String) obj[3];
			cbi.setNumeroCheque( numeroCheque!=null?numeroCheque:"-1" );
			cbi.setValor(((BigDecimal)obj[4]).doubleValue());
			return cbi;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private CuentaBancariaIngresoData obtenerDatosChequesEmitidosConciliacionBancaria(Object[] obj) throws GenericBusinessException {
		CuentaBancariaIngresoData cbi = new CuentaBancariaIngresoData();
		cbi.setCodigoOperacion((String)obj[0]);
		cbi.setDetalle(obj[1] + " " + (String)obj[2]);
		String numeroCheque = (String) obj[3];
		cbi.setNumeroCheque( numeroCheque!=null?numeroCheque:"-1" );
		cbi.setValor(((BigDecimal)obj[4]).doubleValue());
		return cbi;
	}
	
	@Override
	public void showSaveMode() {
		clean();
		setSaveMode();
		getCmbFechaInicio().grabFocus();
		clientesMap = mapearClientes();
		clienteOficinasMap = mapearClienteOficinas();
		tiposDocumentoMap = mapearTiposDocumentos();
		documentosMap = mapearDocumentos();
		modulosMap = mapearModulos();
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
	
	private Map mapearClientes() {
		Map clientesMap = new HashMap();
		
		try {
			Iterator clientesIterator = SessionServiceLocator.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (clientesIterator.hasNext()) {
				ClienteIf cliente = (ClienteIf) clientesIterator.next();
				clientesMap.put(cliente.getId(), cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear datos de clientes", SpiritAlert.ERROR);
		}
		
		return clientesMap;
	}
	
	private Map mapearClienteOficinas() {
		Map clienteOficinasMap = new HashMap();
		
		try {
			Iterator clienteOficinasIterator = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (clienteOficinasIterator.hasNext()) {
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clienteOficinasIterator.next();
				clienteOficinasMap.put(clienteOficina.getId(), clienteOficina);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear datos de clientes", SpiritAlert.ERROR);
		}
		
		return clienteOficinasMap;
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
	
	private ArrayList<CuentaBancariaEgresoData> ordenarEgresosConciliacionBancaria(ArrayList<CuentaBancariaEgresoData> egresosConciliacionBancaria) {
		for (int i=0; i<egresosConciliacionBancaria.size(); i++) {
			for (int j=i+1; j<egresosConciliacionBancaria.size(); j++) {
				String numeroChequeI = egresosConciliacionBancaria.get(i).getNumeroCheque();
				numeroChequeI = (numeroChequeI != null && !numeroChequeI.equals(""))?numeroChequeI.trim():"0";
				String numeroChequeJ = egresosConciliacionBancaria.get(j).getNumeroCheque();
				numeroChequeJ = (numeroChequeJ != null && !numeroChequeJ.equals(""))?numeroChequeJ.trim():"0";
				if (numeroChequeI != null && !numeroChequeI.equals("D/B") && !numeroChequeI.equals("")) {
					if (numeroChequeJ != null && !numeroChequeJ.equals("D/B") && !numeroChequeJ.equals("")) {
						try {
							String comparadorI = egresosConciliacionBancaria.get(i).getDetalle().split(SpiritConstants.getBlankSpaceCharacter())[0] + SpiritConstants.getBlankSpaceCharacter() + numeroChequeI;
							String comparadorJ = egresosConciliacionBancaria.get(j).getDetalle().split(SpiritConstants.getBlankSpaceCharacter())[0] + SpiritConstants.getBlankSpaceCharacter() + numeroChequeJ;
							if (comparadorI.compareTo(comparadorJ) > 0) {
								CuentaBancariaEgresoData auxiliar = (CuentaBancariaEgresoData) DeepCopy.copy(egresosConciliacionBancaria.get(j));
								egresosConciliacionBancaria.set(j, (CuentaBancariaEgresoData) DeepCopy.copy(egresosConciliacionBancaria.get(i)));
								egresosConciliacionBancaria.set(i, auxiliar);
							}
						} catch (java.lang.NumberFormatException e) {
							System.out.println("WARNING: Formato incorrecto número de cheque");
						}
					}
				}
			}
		}
		return egresosConciliacionBancaria;
	}
	
	private ArrayList<CuentaBancariaIngresoData> ordenarIngresosConciliacionBancaria(ArrayList<CuentaBancariaIngresoData> ingresosConciliacionBancaria) {
		for (int i=0; i<ingresosConciliacionBancaria.size(); i++) {
			for (int j=i+1; j<ingresosConciliacionBancaria.size(); j++) {
				String codigoOperacionI = ingresosConciliacionBancaria.get(i).getCodigoOperacion();
				codigoOperacionI = (codigoOperacionI != null && !codigoOperacionI.equals(""))?codigoOperacionI.trim():SpiritConstants.getBlankSpaceCharacter();
				String codigoOperacionJ = ingresosConciliacionBancaria.get(j).getCodigoOperacion();
				codigoOperacionJ = (codigoOperacionJ != null && !codigoOperacionJ.equals(""))?codigoOperacionJ.trim():SpiritConstants.getBlankSpaceCharacter();
				if (codigoOperacionI != null && !codigoOperacionI.equals("")) {
					if (codigoOperacionJ != null && !codigoOperacionJ.equals("")) {
						try {
							String comparadorI = ingresosConciliacionBancaria.get(i).getDetalle().split(SpiritConstants.getBlankSpaceCharacter())[0] + SpiritConstants.getBlankSpaceCharacter() + codigoOperacionI;
							String comparadorJ = ingresosConciliacionBancaria.get(j).getDetalle().split(SpiritConstants.getBlankSpaceCharacter())[0] + SpiritConstants.getBlankSpaceCharacter() + codigoOperacionJ;
							if (comparadorI.compareTo(comparadorJ) > 0) {
								CuentaBancariaIngresoData auxiliar = (CuentaBancariaIngresoData) DeepCopy.copy(ingresosConciliacionBancaria.get(j));
								ingresosConciliacionBancaria.set(j, (CuentaBancariaIngresoData) DeepCopy.copy(ingresosConciliacionBancaria.get(i)));
								ingresosConciliacionBancaria.set(i, auxiliar);
							}
						} catch (java.lang.NumberFormatException e) {
							System.out.println("WARNING: Formato incorrecto código de operación");
						}
					}
				}
			}
		}
		return ingresosConciliacionBancaria;
	}
	
	private ArrayList<CuentaBancariaIngresoData> ordenarChequesEmitidosConciliacionBancaria(ArrayList<CuentaBancariaIngresoData> chequesEmitidosConciliacionBancaria) {
		for (int i=0; i<chequesEmitidosConciliacionBancaria.size(); i++) {
			for (int j=i+1; j<chequesEmitidosConciliacionBancaria.size(); j++) {
				String numeroChequeI = chequesEmitidosConciliacionBancaria.get(i).getNumeroCheque();
				numeroChequeI = (numeroChequeI != null && !numeroChequeI.equals(""))?numeroChequeI.trim():"0";
				String numeroChequeJ = chequesEmitidosConciliacionBancaria.get(j).getNumeroCheque();
				numeroChequeJ = (numeroChequeJ != null && !numeroChequeJ.equals(""))?numeroChequeJ.trim():"0";
				if (numeroChequeI != null && !numeroChequeI.equals("D/B") && !numeroChequeI.equals("")) {
					if (numeroChequeJ != null && !numeroChequeJ.equals("D/B") && !numeroChequeJ.equals("")) {
						try {
							String comparadorI = chequesEmitidosConciliacionBancaria.get(i).getDetalle().split(SpiritConstants.getBlankSpaceCharacter())[0] + SpiritConstants.getBlankSpaceCharacter() + numeroChequeI;
							String comparadorJ = chequesEmitidosConciliacionBancaria.get(j).getDetalle().split(SpiritConstants.getBlankSpaceCharacter())[0] + SpiritConstants.getBlankSpaceCharacter() + numeroChequeJ;
							if (comparadorI.compareTo(comparadorJ) > 0) {
								CuentaBancariaIngresoData auxiliar = (CuentaBancariaIngresoData) DeepCopy.copy(chequesEmitidosConciliacionBancaria.get(j));
								chequesEmitidosConciliacionBancaria.set(j, (CuentaBancariaIngresoData) DeepCopy.copy(chequesEmitidosConciliacionBancaria.get(i)));
								chequesEmitidosConciliacionBancaria.set(i, auxiliar);
							}
						} catch (java.lang.NumberFormatException e) {
							System.out.println("WARNING: Formato incorrecto número de cheque");
						}
					}
				}
			}
		}
		return chequesEmitidosConciliacionBancaria;
	}
}
