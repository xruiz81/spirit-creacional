package com.spirit.general.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.MonedaData;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPMoneda;
import com.spirit.general.session.MonedaSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class MonedaModel extends JPMoneda {

	private static final long serialVersionUID = -1062853346518981755L;
	private DefaultTableModel tableModel;
	private Vector monedaVector = new Vector();
	private int monedaSeleccionada;
	private MonedaIf monedaActualizadaIf;
	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_DESCRIPCION = 30;

	public MonedaModel() {

		setMonedaSeleccionada(-1);
		initKeyListeners();
		setSorterTable(getTblMoneda());
		anchoColumnasTabla();
		getTblMoneda().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		showSaveMode();

		getTblMoneda().addMouseListener(oMouseAdapterTblMoneda);
		getTblMoneda().addKeyListener(oKeyAdapterTblMoneda);

		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtDescripcion().addKeyListener(
				new TextChecker(MAX_LONGITUD_DESCRIPCION));
	}

	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblMoneda().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblMoneda().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
	}

	MouseListener oMouseAdapterTblMoneda = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblMoneda = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		// Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setMonedaSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			monedaActualizadaIf = (MonedaIf) getMonedaVector().get(getMonedaSeleccionada());
			// Muestro los datos del detalle en el panel de abajo
			getTxtCodigo().setText(monedaActualizadaIf.getCodigo());
			getTxtDescripcion().setText(monedaActualizadaIf.getNombre());
			getTxtCodigo().setEnabled(true);
			getTxtDescripcion().setEnabled(true);			
			showUpdateMode();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {

				MonedaData moneda = new MonedaData();
				moneda.setCodigo(getTxtCodigo().getText());
				moneda.setNombre(getTxtDescripcion().getText());

				MonedaModel.getMonedaSessionService().addMoneda(moneda);
				SpiritAlert.createAlert("Moneda guardada con éxito",
						SpiritAlert.INFORMATION);
				SpiritCache.setObject("moneda", null);
				showSaveMode();
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la informacin!",
					SpiritAlert.ERROR);
		}
	}

	public void delete() {

		try {
			MonedaIf monedaIf = (MonedaIf) getMonedaVector().get(
					getMonedaSeleccionada());
			MonedaModel.getMonedaSessionService()
					.deleteMoneda(monedaIf.getId());
			SpiritAlert.createAlert("Moneda eliminada con éxito",
					SpiritAlert.INFORMATION);
			SpiritCache.setObject("moneda", null);
			showSaveMode();
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert
					.createAlert(
							"La moneda tiene datos referenciados y no puede ser eliminada!",
							SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setMonedaActualizadaIf((MonedaIf) getMonedaVector().get(
						getMonedaSeleccionada()));

				getMonedaActualizadaIf().setCodigo(getTxtCodigo().getText());
				getMonedaActualizadaIf().setNombre(
						getTxtDescripcion().getText());

				MonedaModel.getMonedaSessionService().saveMoneda(
						getMonedaActualizadaIf());

				getMonedaVector().setElementAt(getMonedaActualizadaIf(),
						getMonedaSeleccionada());
				setMonedaActualizadaIf(null);

				SpiritAlert.createAlert("Moneda actualizada con éxito",
						SpiritAlert.INFORMATION);
				SpiritCache.setObject("moneda", null);
				showSaveMode();
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informacin!",
					SpiritAlert.ERROR);
		}

	}

	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strDescripcion = this.getTxtDescripcion().getText();

		Collection moneda = null;
		boolean codigoRepetido = false;

		try {
			moneda = getMonedaSessionService().getMonedaList();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator monedaIt = moneda.iterator();

		while (monedaIt.hasNext()) {
			MonedaIf monedaIf = (MonedaIf) monedaIt.next();

			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(monedaIf.getCodigo()))
					codigoRepetido = true;

			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(monedaIf.getCodigo()))
					if (getMonedaActualizadaIf().getId().equals(
							monedaIf.getId()) == false)
						codigoRepetido = true;
		}

		if (codigoRepetido) {
			SpiritAlert.createAlert("El cdigo de la Moneda está duplicado !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert(
					"Debe ingresar un cdigo para la Moneda !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strDescripcion)) || (strDescripcion == null))) {
			SpiritAlert.createAlert(
					"Debe ingresar un nombre para la Moneda !!",
					SpiritAlert.WARNING);
			getTxtDescripcion().grabFocus();
			return false;
		}

		return true;
	}

	public void find() {
		showSaveMode();
	}

	public void clean() {

		getTxtCodigo().setText("");
		getTxtDescripcion().setText("");

		DefaultTableModel model = (DefaultTableModel) getTblMoneda().getModel();

		for (int i = this.getTblMoneda().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);

	}

	public void showFindMode() {
		showSaveMode();
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private void cargarTabla() {
		try {
			Collection monedas = MonedaModel.getMonedaSessionService().getMonedaList();
			Iterator monedasIterator = monedas.iterator();

			if (!getMonedaVector().isEmpty()) {
				getMonedaVector().removeAllElements();
			}

			while (monedasIterator.hasNext()) {
				MonedaIf monedaIf = (MonedaIf) monedasIterator.next();

				tableModel = (DefaultTableModel) getTblMoneda().getModel();
				Vector<String> fila = new Vector<String>();
				getMonedaVector().add(monedaIf);

				fila.add(monedaIf.getCodigo());
				fila.add(monedaIf.getNombre());

				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblMoneda(), monedas, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getMonedaSeleccionada() {
		return monedaSeleccionada;
	}

	public void setMonedaSeleccionada(int selectedMoneda) {
		monedaSeleccionada = selectedMoneda;
	}

	public MonedaIf getMonedaActualizadaIf() {
		return monedaActualizadaIf;
	}

	public void setMonedaActualizadaIf(MonedaIf monedaIf) {
		monedaActualizadaIf = monedaIf;
	}

	public Vector getMonedaVector() {
		return monedaVector;
	}

	public void setMonedaVector(Vector monedaVec) {
		monedaVector = monedaVec;
	}

	public static MonedaSessionService getMonedaSessionService() {
		try {
			return (MonedaSessionService) ServiceLocator
					.getService(ServiceLocator.MONEDASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicacin con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
}
