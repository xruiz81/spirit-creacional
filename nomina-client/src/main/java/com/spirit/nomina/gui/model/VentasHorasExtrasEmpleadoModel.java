package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.SpiritCursor;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.gui.panel.JPVentasHorasExtrasEmpleado;
import com.spirit.nomina.handler.VentasHorasExtrasEmpleado;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class VentasHorasExtrasEmpleadoModel extends JPVentasHorasExtrasEmpleado {
	private static final long serialVersionUID = -9138290238492974680L;
	private static final int INDEX_TBL_DATOS_SELECCION = 0;
	private static final int INDEX_TBL_DATOS_EMPLEADO = 1;
	private static final int INDEX_TBL_DATOS_TOTAL_VENTAS = 2;
	private static final int INDEX_TBL_DATOS_HORAS_EXTRAS_50 = 3;
	private static final int INDEX_TBL_DATOS_HORAS_EXTRAS_100 = 4;
	private static final int INDEX_TBL_DATOS_CONTRATO_ID = 5;
	Vector<VentasHorasExtrasEmpleado> datosVector = new Vector<VentasHorasExtrasEmpleado>();
	private int tblDatosSelectedRow;
	private boolean tblDatosStopCellEditing;
	private static final int MAX_DIGITOS_TOTAL_VENTAS = 22;
	private static final int MAX_DIGITOS_NUMERO_HORAS_EXTRAS_50 = 3;
	private static final int MAX_DIGITOS_NUMERO_HORAS_EXTRAS_100 = 3;
	private static final int MAX_DIGITOS_YEAR = 4;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");

	public VentasHorasExtrasEmpleadoModel() {
		setName("Datos total ventas y horas extras");
		showSaveMode();
		initListeners();
	}
	
	private void initListeners() {
		getBtnBorrarTodo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Realmente desea borrar la hoja de datos?\nAdvertencia: Esta acción no eliminará los datos de ventas y horas extras de los contratos.", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionYes());
				if (opcion == JOptionPane.YES_OPTION)
					showSaveMode();
			}
		});
		
		getBtnBorrarSeleccionados().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Realmente desea borrar los elementos seleccionados de la hoja de datos?\nAdvertencia: Esta acción no eliminará los datos de ventas y horas extras de los contratos.", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionYes());
				if (opcion == JOptionPane.YES_OPTION)
					borrarSeleccionados();
			}
		});
		
		getBtnImportar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (validateFields()) {
					try {
						importarTotalVentas();
						SpiritAlert.createAlert("Importación de datos realizada con éxito", SpiritAlert.INFORMATION);
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error al obtener mes para la importación", SpiritAlert.ERROR);
						e.printStackTrace();
					}

				}
			}
		});
		
		getTxtAnio().addKeyListener(new TextChecker(getMaxDigitosYear()));
		getTxtAnio().addKeyListener(new NumberTextField());
	}
	
	private void importarTotalVentas() throws GenericBusinessException {
		Map<Long, TipoEmpleadoIf> tipoEmpleadoMap = mapearTipoEmpleado();
		int month = Utilitarios.getMesInt(getCmbMes().getSelectedItem().toString());
		int year = Integer.parseInt(getTxtAnio().getText().trim());
		int lastDay = Utilitarios.getLastDayOfMonth(year, year);
		java.sql.Date firstDate = new java.sql.Date(new GregorianCalendar(year, month, 1).getTimeInMillis());
		java.sql.Date lastDate = new java.sql.Date(new GregorianCalendar(year, month, lastDay).getTimeInMillis());
		java.util.Collection<FacturaIf> ventasList = SessionServiceLocator.getFacturaSessionService().findFacturasParaCalculoTotalVentas(Parametros.getIdEmpresa(), firstDate, lastDate);
		java.util.Collection<FacturaIf> devolucionesList = SessionServiceLocator.getFacturaSessionService().findDevolucionesParaCalculoTotalVentas(Parametros.getIdEmpresa(), firstDate, lastDate);
		for (int i=0; i<getDatosVector().size(); i++) {
			VentasHorasExtrasEmpleado data = getDatosVector().get(i);
			EmpleadoIf empleado = data.getEmpleado();
			double totalVentas = calcularTotalVentas(ventasList, devolucionesList, empleado, tipoEmpleadoMap);
			getTblDatos().setValueAt(getFormatoDecimal().format(Utilitarios.redondeoValor(totalVentas)), i, getIndexTblDatosTotalVentas());
			data.setTotalVentas(totalVentas);
			getDatosVector().set(i, data);
		}
	}
	
	private double calcularTotalVentas(Collection<FacturaIf> ventasList, Collection<FacturaIf> devolucionesList, EmpleadoIf empleado, Map<Long, TipoEmpleadoIf> tipoEmpleadoMap) {
		double ventas = 0D; double devoluciones = 0D;
		TipoEmpleadoIf tipoEmpleado = tipoEmpleadoMap.get(empleado.getTipoempleadoId());
		if (tipoEmpleado != SpiritConstants.getNullValue()) {
			Iterator<FacturaIf> ventasIterator = ventasList.iterator();
			while (ventasIterator.hasNext()) {
				FacturaIf venta = ventasIterator.next();
				if (tipoEmpleado.getAdministrador().equals(SpiritConstants.getOptionYes().substring(0,1)) || (tipoEmpleado.getSupervisor().equals(SpiritConstants.getOptionYes().substring(0,1)) && empleado.getOficinaId().compareTo(venta.getOficinaId()) == 0) || (tipoEmpleado.getVendedor().equals(SpiritConstants.getOptionYes().substring(0,1)) && empleado.getId().compareTo(venta.getVendedorId()) == 0))
					ventas += venta.getValor().add(venta.getDescuento().negate()).doubleValue();
			}
			Iterator<FacturaIf> devolucionesIterator = devolucionesList.iterator();
			while (devolucionesIterator.hasNext()) {
				FacturaIf devolucion = devolucionesIterator.next();
				if (tipoEmpleado.getAdministrador().equals(SpiritConstants.getOptionYes().substring(0,1)) || (tipoEmpleado.getSupervisor().equals(SpiritConstants.getOptionYes().substring(0,1)) && empleado.getOficinaId().compareTo(devolucion.getOficinaId()) == 0) || (tipoEmpleado.getVendedor().equals(SpiritConstants.getOptionYes().substring(0,1)) && empleado.getId().compareTo(devolucion.getVendedorId()) == 0))
					devoluciones += devolucion.getValor().add(devolucion.getDescuento().negate()).doubleValue();
			}
		} else {
			SpiritAlert.createAlert(empleado.getNombres() + SpiritConstants.getBlankSpaceCharacter() + empleado.getApellidos() + SpiritConstants.getBlankSpaceCharacter() + "no tiene asociado tipo de empleado. No se pueden importar datos para este empleado.", SpiritAlert.WARNING);
			return 0D;
		}
		return (ventas-devoluciones);
	}
	
	@SuppressWarnings("unchecked")
	private Map<Long, TipoEmpleadoIf> mapearTipoEmpleado() {
		Map<Long, TipoEmpleadoIf> tipoEmpleadoMapa = new HashMap<Long, TipoEmpleadoIf>();
		try {
		Iterator<TipoEmpleadoIf> it = SessionServiceLocator.getTipoEmpleadoSessionService().findTipoEmpleadoByEmpresaId(Parametros.getIdEmpresa()).iterator();
		while (it.hasNext()) {
			TipoEmpleadoIf tipoEmpleado = it.next();
			tipoEmpleadoMapa.put(tipoEmpleado.getId(), tipoEmpleado);
		}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear datos", SpiritAlert.ERROR);
		}
		return tipoEmpleadoMapa;
	}
		
	private void borrarSeleccionados() {
		for (int i=0; i<getTblDatos().getRowCount(); i++) {
			boolean seleccionado = ((Boolean) getTblDatos().getValueAt(i, getIndexTblDatosSeleccion())).booleanValue();
			if (seleccionado) {
				VentasHorasExtrasEmpleado data = getDatosVector().get(i);
				getTblDatos().setValueAt(SpiritConstants.getEmptyCharacter(), i, getIndexTblDatosTotalVentas());
				data.setTotalVentas(0D);
				getTblDatos().setValueAt(SpiritConstants.getEmptyCharacter(), i, getIndexTblDatosHorasExtras50());
				data.setNumeroHorasExtras50(0D);
				getTblDatos().setValueAt(SpiritConstants.getEmptyCharacter(), i, getIndexTblDatosHorasExtras100());
				data.setNumeroHorasExtras100(0D);
				getDatosVector().set(i, data);
			}
		}
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showSaveMode() {
		clean();
		initPanelComponents();
	}
	
	private void initPanelComponents() {
		initTables();
		loadData();
	}
	
	private void initTblDatos() {
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblDatos().getColumnModel().getColumn(getIndexTblDatosTotalVentas()).setCellRenderer(tableCellRenderer);
		getTblDatos().getColumnModel().getColumn(getIndexTblDatosHorasExtras50()).setCellRenderer(tableCellRenderer);
		getTblDatos().getColumnModel().getColumn(getIndexTblDatosHorasExtras100()).setCellRenderer(tableCellRenderer);
		getTblDatos().getTableHeader().setReorderingAllowed(false);
		getTblDatos().getTableHeader().setReorderingAllowed(false);
		getTblDatos().getColumnModel().getColumn(getIndexTblDatosSeleccion()).setPreferredWidth(30);
		getTblDatos().getColumnModel().getColumn(getIndexTblDatosSeleccion()).setMinWidth(30);
		getTblDatos().getColumnModel().getColumn(getIndexTblDatosSeleccion()).setMaxWidth(30);
		getTblDatos().getColumnModel().getColumn(getIndexTblDatosEmpleado()).setPreferredWidth(250);
		getTblDatos().getColumnModel().getColumn(getIndexTblDatosEmpleado()).setMinWidth(250);
		getTblDatos().getColumnModel().getColumn(getIndexTblDatosContratoId()).setPreferredWidth(0);
		getTblDatos().getColumnModel().getColumn(getIndexTblDatosContratoId()).setMinWidth(0);
		getTblDatos().getColumnModel().getColumn(getIndexTblDatosContratoId()).setMaxWidth(0);
		getTblDatos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblDatos().putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		getTblDatos().getTableHeader().setReorderingAllowed(false);
		getTblDatos().setSurrendersFocusOnKeystroke(true);
		setCustomCellEditor(getIndexTblDatosTotalVentas(), getMaxDigitosTotalVentas());
		setCustomCellEditor(getIndexTblDatosHorasExtras50(), getMaxDigitosNumeroHorasExtras50());
		setCustomCellEditor(getIndexTblDatosHorasExtras100(), getMaxDigitosNumeroHorasExtras100());
	}
	
	private void setCustomCellEditor(int columnIndex, int textChecker) {
		TableColumn column = getTblDatos().getColumnModel().getColumn(columnIndex);
		JTextField txtField = new JTextField();
		txtField.addKeyListener(new TextChecker(textChecker));
		txtField.addKeyListener(new NumberTextFieldDecimal());
		column.setCellEditor(new DefaultCellEditor(txtField));
		((DefaultCellEditor) getTblDatos().getCellEditor(0, columnIndex)).setClickCountToStart(1);
	}
	
	private void loadTblDatosData() {
		DefaultTableModel dtm = (DefaultTableModel) getTblDatos().getModel();
		for (int i=0; i<getDatosVector().size(); i++) {
			VentasHorasExtrasEmpleado data = getDatosVector().get(i);
			Vector<Object> dataRow = getWidgetToTblDatosDataList(data);
			dtm.addRow(dataRow);
		}
		getTblDatos().setModel(dtm);
	}
	
	private Vector<Object> getWidgetToTblDatosDataList(VentasHorasExtrasEmpleado data) {
		Vector<Object> dataRow = new Vector<Object>();
		dataRow.add(new Boolean(false));
		dataRow.add(data.getEmpleado().getNombres() + SpiritConstants.getBlankSpaceCharacter() + data.getEmpleado().getApellidos());
		double totalVentas = Utilitarios.redondeoValor(data.getTotalVentas());
		dataRow.add(totalVentas>0D?String.valueOf(totalVentas):SpiritConstants.getEmptyCharacter());
		double numeroHorasExtras50 = Utilitarios.redondeoValor(data.getNumeroHorasExtras50());
		dataRow.add(numeroHorasExtras50>0D?String.valueOf(numeroHorasExtras50):SpiritConstants.getEmptyCharacter());
		double numeroHorasExtras100 = Utilitarios.redondeoValor(data.getNumeroHorasExtras100()); 
		dataRow.add(numeroHorasExtras100>0D?String.valueOf(numeroHorasExtras100):SpiritConstants.getEmptyCharacter());
		dataRow.add(data.getContrato().getId());
		return dataRow;
		
	}
	
	private void initTables() {
		initTblDatos();
	}
	
	private void loadData() {
		try {
			Iterator<Object[]> it = SessionServiceLocator.getContratoSessionService().getContratosEmpleados(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				Object[] object = it.next();
				VentasHorasExtrasEmpleado data = new VentasHorasExtrasEmpleado();
				ContratoIf contrato = (ContratoIf) object[0];
				EmpleadoIf empleado = (EmpleadoIf) object[1];
				data.setContrato(contrato);
				data.setEmpleado(empleado);
				getDatosVector().add(data);
			}
			loadTblDatosData();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar los datos", SpiritAlert.ERROR);
		}
	}

	private void cleanTblDatos() {
		DefaultTableModel dtm = (DefaultTableModel) getTblDatos().getModel();
		int rows = getTblDatos().getRowCount();
		for(int j=rows;j>0;--j)
			dtm.removeRow(j-1);
	}
	
	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	public void find() {
		// TODO Auto-generated method stub
		
	}

	public void save() {
		for (int i=0; i<getTblDatos().getRowCount(); i++) {
			VentasHorasExtrasEmpleado data = new VentasHorasExtrasEmpleado();
			data.setContrato(getDatosVector().get(i).getContrato());
			data.setEmpleado(getDatosVector().get(i).getEmpleado());
			String strTotalVentas = (String) getTblDatos().getValueAt(i, INDEX_TBL_DATOS_TOTAL_VENTAS);
			double totalVentas = (!strTotalVentas.trim().equals(SpiritConstants.getEmptyCharacter())?Utilitarios.redondeoValor(Double.parseDouble(Utilitarios.removeDecimalFormat(strTotalVentas))):0D);
			data.setTotalVentas(totalVentas);
			String strNumeroHorasExtras50 = (String) getTblDatos().getValueAt(i, INDEX_TBL_DATOS_HORAS_EXTRAS_50);
			double numeroHorasExtras50 = (!strNumeroHorasExtras50.trim().equals(SpiritConstants.getEmptyCharacter())?Utilitarios.redondeoValor(Double.parseDouble(Utilitarios.removeDecimalFormat(strNumeroHorasExtras50))):0D);
			data.setNumeroHorasExtras50(numeroHorasExtras50);
			String strNumeroHorasExtras100 = (String) getTblDatos().getValueAt(i, INDEX_TBL_DATOS_HORAS_EXTRAS_100);
			double numeroHorasExtras100 = (!strNumeroHorasExtras100.trim().equals(SpiritConstants.getEmptyCharacter())?Utilitarios.redondeoValor(Double.parseDouble(Utilitarios.removeDecimalFormat(strNumeroHorasExtras100))):0D);
			data.setNumeroHorasExtras100(numeroHorasExtras100);
			getDatosVector().set(i, data);
		}
		try {
			setCursor(SpiritCursor.hourglassCursor);
			SessionServiceLocator.getContratoRubroSessionService().saveTotalVentasHorasExtrasEmpleado(Parametros.getIdEmpresa(), getDatosVector());
			SpiritAlert.createAlert("Datos guardados exitosamente", SpiritAlert.INFORMATION);
			showSaveMode();
			setCursor(SpiritCursor.normalCursor);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al guardar los datos", SpiritAlert.ERROR);
		}
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	public void clean() {
		getCmbMes().setSelectedItem(Utilitarios.getNombreMes((new java.util.Date()).getMonth()+1));
		getTxtAnio().setText(SpiritConstants.getEmptyCharacter());
		getDatosVector().clear();
		cleanTblDatos();
	}

	public void report() {
		// TODO Auto-generated method stub
		
	}

	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	public boolean validateFields() {
		if (getTxtAnio().getText().trim().equals(SpiritConstants.getEmptyCharacter())) {
			SpiritAlert.createAlert("Debe ingresar el año para realizar la importación", SpiritAlert.WARNING);
			getTxtAnio().grabFocus();
			return false;
		}
		return true;
	}

	public Vector<VentasHorasExtrasEmpleado> getDatosVector() {
		return datosVector;
	}

	public void setDatosVector(Vector<VentasHorasExtrasEmpleado> datosVector) {
		this.datosVector = datosVector;
	}

	public static int getIndexTblDatosSeleccion() {
		return INDEX_TBL_DATOS_SELECCION;
	}

	public static int getIndexTblDatosEmpleado() {
		return INDEX_TBL_DATOS_EMPLEADO;
	}

	public static int getIndexTblDatosTotalVentas() {
		return INDEX_TBL_DATOS_TOTAL_VENTAS;
	}

	public static int getIndexTblDatosHorasExtras50() {
		return INDEX_TBL_DATOS_HORAS_EXTRAS_50;
	}

	public static int getIndexTblDatosHorasExtras100() {
		return INDEX_TBL_DATOS_HORAS_EXTRAS_100;
	}

	public static int getIndexTblDatosContratoId() {
		return INDEX_TBL_DATOS_CONTRATO_ID;
	}

	public static int getMaxDigitosTotalVentas() {
		return MAX_DIGITOS_TOTAL_VENTAS;
	}

	public static int getMaxDigitosNumeroHorasExtras50() {
		return MAX_DIGITOS_NUMERO_HORAS_EXTRAS_50;
	}

	public static int getMaxDigitosNumeroHorasExtras100() {
		return MAX_DIGITOS_NUMERO_HORAS_EXTRAS_100;
	}

	public static int getMaxDigitosYear() {
		return MAX_DIGITOS_YEAR;
	}

	public int getTblDatosSelectedRow() {
		return tblDatosSelectedRow;
	}

	public void setTblDatosSelectedRow(int tblDatosSelectedRow) {
		this.tblDatosSelectedRow = tblDatosSelectedRow;
	}

	public boolean isTblDatosStopCellEditing() {
		return tblDatosStopCellEditing;
	}

	public void setTblDatosStopCellEditing(boolean tblDatosStopCellEditing) {
		this.tblDatosStopCellEditing = tblDatosStopCellEditing;
	}

	public DecimalFormat getFormatoDecimal() {
		return formatoDecimal;
	}

	public void setFormatoDecimal(DecimalFormat formatoDecimal) {
		this.formatoDecimal = formatoDecimal;
	}
}
