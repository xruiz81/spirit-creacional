package com.spirit.cartera.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.FormaPagoData;
import com.spirit.cartera.entity.FormaPagoIf;
import com.spirit.cartera.gui.panel.JPFormaPago;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class FormaPagoModel extends JPFormaPago {
	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final int MAX_LONGITUD_DIAS_INICIO = 3;
	private static final int MAX_LONGITUD_DIAS_FIN = 3;
	private static final int MAX_LONGITUD_VALOR = 5;
	private DefaultTableModel tableListaModel;
	private Vector formaPagoVector = new Vector();
	private int formaPagoSeleccionada;
	protected FormaPagoIf formaPagoIf;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");

	public FormaPagoModel() {
		showSaveMode();
		initKeyListeners();
		setSorterTable(getTblFormaPago());
		anchoColumnasTabla();
		getTblFormaPago().addMouseListener(oMouseAdapterTblFormaPago);
		getTblFormaPago().addKeyListener(oKeyAdapterTblFormaPago);
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtDiasInicio().addKeyListener(new TextChecker(MAX_LONGITUD_DIAS_INICIO));
		getTxtDiasFin().addKeyListener(new TextChecker(MAX_LONGITUD_DIAS_FIN));
		getTxtDescuentoVenta().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtDescuentoCartera().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtInteres().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtDiasInicio().addKeyListener(new NumberTextField());
		getTxtDiasFin().addKeyListener(new NumberTextField());
		getTxtDescuentoVenta().addKeyListener(new NumberTextFieldDecimal());
		getTxtDescuentoCartera().addKeyListener(new NumberTextFieldDecimal());
		getTxtInteres().addKeyListener(new NumberTextFieldDecimal());
	}

	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblFormaPago().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(20);
		anchoColumna = getTblFormaPago().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblFormaPago().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblFormaPago().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblFormaPago().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblFormaPago().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblFormaPago().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(30);
	}

	MouseListener oMouseAdapterTblFormaPago = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblFormaPago = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		// Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setFormaPagoSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			formaPagoIf = (FormaPagoIf) getFormaPagoVector().get(getFormaPagoSeleccionada());
			getTxtCodigo().setText(formaPagoIf.getCodigo());
			getTxtNombre().setText(formaPagoIf.getNombre());
			getTxtDiasInicio().setText(formaPagoIf.getDiasInicio().toString());
			getTxtDiasFin().setText(formaPagoIf.getDiasFin().toString());
			getTxtDescuentoVenta().setText(formatoDecimal.format(formaPagoIf.getDescuentoVenta().doubleValue()));
			getTxtDescuentoCartera().setText(formatoDecimal.format(formaPagoIf.getDescuentoCartera().doubleValue()));
			getTxtInteres().setText(formatoDecimal.format(formaPagoIf.getInteres().doubleValue()));
			showUpdateMode();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();

	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private void cargarTabla() {
		try {
			Collection formaPago = SessionServiceLocator.getFormaPagoSessionService().findFormaPagoByEmpresaId(Parametros.getIdEmpresa());
			Iterator formaPagoIterator = formaPago.iterator();

			if (!getFormaPagoVector().isEmpty())
				getFormaPagoVector().removeAllElements();

			while (formaPagoIterator.hasNext()) {
				FormaPagoIf formaPagoIf = (FormaPagoIf) formaPagoIterator.next();
				tableListaModel = (DefaultTableModel) getTblFormaPago().getModel();
				Vector<String> fila = new Vector<String>();
				getFormaPagoVector().add(formaPagoIf);
				agregarColumnasTabla(formaPagoIf, fila);
				tableListaModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblFormaPago(), formaPago, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void agregarColumnasTabla(FormaPagoIf formaPagoIf, Vector<String> fila) {
		fila.add(formaPagoIf.getCodigo());
		fila.add(formaPagoIf.getNombre());
		fila.add(formaPagoIf.getDiasInicio().toString());
		fila.add(formaPagoIf.getDiasFin().toString());
		fila.add(formatoDecimal.format(formaPagoIf.getDescuentoVenta().doubleValue()) + "%");
		fila.add(formatoDecimal.format(formaPagoIf.getDescuentoCartera().doubleValue()) + "%");
		fila.add(formatoDecimal.format(formaPagoIf.getInteres().doubleValue()) + "%");
	}

	public void save() {
		try {
			if (validateFields()) {
				FormaPagoData data = new FormaPagoData();
				data.setCodigo(getTxtCodigo().getText());
				data.setNombre(getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());
				data.setDiasInicio(Integer.valueOf(getTxtDiasInicio().getText()));
				data.setDiasFin(Integer.valueOf(getTxtDiasFin().getText()));
				data.setDescuentoVenta(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtDescuentoVenta().getText()))));
				data.setDescuentoCartera(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtDescuentoCartera().getText()))));
				data.setInteres(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtInteres().getText()))));
				SessionServiceLocator.getFormaPagoSessionService().addFormaPago(data);
				showSaveMode();
				SpiritAlert.createAlert("Forma de Pago guardada con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setFormaPagoActualizadaIf((FormaPagoIf) getFormaPagoVector().get(getFormaPagoSeleccionada()));
				getFormaPagoActualizadaIf().setCodigo(getTxtCodigo().getText());
				getFormaPagoActualizadaIf().setNombre(getTxtNombre().getText());
				getFormaPagoActualizadaIf().setEmpresaId(Parametros.getIdEmpresa());
				getFormaPagoActualizadaIf().setDiasInicio(Integer.valueOf(getTxtDiasInicio().getText()));
				getFormaPagoActualizadaIf().setDiasFin(Integer.valueOf(getTxtDiasFin().getText()));
				getFormaPagoActualizadaIf().setDescuentoVenta(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtDescuentoVenta().getText()))));
				getFormaPagoActualizadaIf().setDescuentoCartera(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtDescuentoCartera().getText()))));
				getFormaPagoActualizadaIf().setInteres(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtInteres().getText()))));
				getFormaPagoVector().setElementAt(getFormaPagoActualizadaIf(), getFormaPagoSeleccionada());
				SessionServiceLocator.getFormaPagoSessionService().saveFormaPago(getFormaPagoActualizadaIf());
				setFormaPagoActualizadaIf(null);
				showSaveMode();
				SpiritAlert.createAlert("Forma de Pago actualizada con éxito", SpiritAlert.INFORMATION);
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la infomación!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			FormaPagoIf formaPagoIf = (FormaPagoIf) getFormaPagoVector().get(getFormaPagoSeleccionada());
			SessionServiceLocator.getFormaPagoSessionService().deleteFormaPago(formaPagoIf.getId());
			showSaveMode();
			SpiritAlert.createAlert("Forma de Pago eliminada con éxito", SpiritAlert.INFORMATION);
		} catch (Exception e) {
			e.printStackTrace();
			showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}

	public void clean() {
		getTxtCodigo().setText("");
		getTxtNombre().setText("");
		getTxtDiasInicio().setText("");
		getTxtDiasFin().setText("");
		getTxtDescuentoVenta().setText("");
		getTxtDescuentoCartera().setText("");
		getTxtInteres().setText("");

		DefaultTableModel modelTabla = (DefaultTableModel) getTblFormaPago().getModel();
		for (int i = this.getTblFormaPago().getRowCount(); i > 0; --i)
			modelTabla.removeRow(i - 1);
	}

	public boolean validateFields() {
		String strCodigo = getTxtCodigo().getText();
		String strNombre = getTxtNombre().getText();
		String strDiasInicio = getTxtDiasInicio().getText();
		String strDiasFin = getTxtDiasFin().getText();
		String strDsctVenta = Utilitarios.removeDecimalFormat(getTxtDescuentoVenta().getText());
		String strDsctCartera = Utilitarios.removeDecimalFormat(getTxtDescuentoCartera().getText());
		String strInteres = Utilitarios.removeDecimalFormat(getTxtInteres().getText());
		int diasInicio = 0;
		int diasFin = 0;

		Collection formaPago = null;
		boolean codigoRepetido = false;

		try {
			formaPago = SessionServiceLocator.getFormaPagoSessionService().findFormaPagoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator formaPagoIt = formaPago.iterator();

		while (formaPagoIt.hasNext()) {
			FormaPagoIf formaPagoIf = (FormaPagoIf) formaPagoIt.next();

			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(formaPagoIf.getCodigo()))
					codigoRepetido = true;

			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(formaPagoIf.getCodigo()))
					if (getFormaPagoActualizadaIf().getId().equals(formaPagoIf.getId()) == false)
						codigoRepetido = true;
		}

		if (codigoRepetido) {
			SpiritAlert.createAlert("El código de la Forma de Pago está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un Código !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un Nombre !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}

		if ((("".equals(strDiasInicio)) || (strDiasInicio == null))) {
			SpiritAlert.createAlert("Debe ingresar los Días Inicio !!", SpiritAlert.WARNING);
			getTxtDiasInicio().grabFocus();
			return false;
		}
		if ((("".equals(strDiasFin)) || (strDiasFin == null))) {
			SpiritAlert.createAlert("Debe ingresar los Días Fin !!", SpiritAlert.WARNING);
			getTxtDiasFin().grabFocus();
			return false;
		}

		diasInicio = Integer.parseInt(getTxtDiasInicio().getText());
		diasFin = Integer.parseInt(getTxtDiasFin().getText());

		if (diasInicio > diasFin) {
			SpiritAlert.createAlert("El número de Días Inicio debe ser menor que Días Fin", SpiritAlert.WARNING);
			getTxtDiasInicio().grabFocus();
			return false;
		}

		if ((("".equals(strDsctVenta)) || (strDsctVenta == null))) {
			SpiritAlert.createAlert("Debe ingresar el Descuento de Venta !!", SpiritAlert.WARNING);
			getTxtDescuentoVenta().grabFocus();
			return false;
		}
		
		if (Double.valueOf(strDsctVenta) < 0D || Double.valueOf(strDsctVenta) > 100D) {
			SpiritAlert.createAlert("Descuento de Venta debe estar entre 0-100 [%]", SpiritAlert.WARNING);
			getTxtDescuentoVenta().grabFocus();
			return false;
		}
		
		
		if ((("".equals(strDsctCartera)) || (strDsctCartera == null))) {
			SpiritAlert.createAlert("Debe ingresar el Descuento de Cartera !!", SpiritAlert.WARNING);
			getTxtDescuentoCartera().grabFocus();
			return false;
		}
		
		if (Double.valueOf(strDsctCartera) < 0D || Double.valueOf(strDsctCartera) > 100D) {
			SpiritAlert.createAlert("Descuento de Cartera debe estar entre 0-100 [%]", SpiritAlert.WARNING);
			getTxtDescuentoCartera().getText();
			return false;
		}

		if ((("".equals(strInteres)) || (strInteres == null))) {
			SpiritAlert.createAlert("Debe ingresar el Interes !!", SpiritAlert.WARNING);
			getTxtInteres().grabFocus();
			return false;
		}
		
		if (Double.valueOf(strInteres) < 0D || Double.valueOf(strInteres) > 100D) {
			SpiritAlert.createAlert("Interés debe estar entre 0-100 [%]", SpiritAlert.WARNING);
			getTxtInteres().getText();
			return false;
		}

		return true;
	}

	public Vector getFormaPagoVector() {
		return formaPagoVector;
	}

	public void setFormaPagoVector(Vector formaPagoVector) {
		this.formaPagoVector = formaPagoVector;
	}

	public int getFormaPagoSeleccionada() {
		return formaPagoSeleccionada;
	}

	public void setFormaPagoSeleccionada(int formaPagoSeleccionada) {
		this.formaPagoSeleccionada = formaPagoSeleccionada;
	}

	public FormaPagoIf getFormaPagoActualizadaIf() {
		return formaPagoIf;
	}

	public void setFormaPagoActualizadaIf(FormaPagoIf formaPagoActualizadaIf) {
		this.formaPagoIf = formaPagoActualizadaIf;
	}
}
