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
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoOrdenData;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPTipoOrden;
import com.spirit.general.session.TipoOrdenSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoOrdenModel extends JPTipoOrden {
	private static final int MAX_LONGITUD_CODIGO = 2;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final String NOMBRE_TIPO_PRESUPUESTO = "PRESUPUESTO";
	private static final String TIPO_PRESUPUESTO = NOMBRE_TIPO_PRESUPUESTO.substring(0, 1);
	private static final String NOMBRE_TIPO_MEDIOS = "MEDIOS";
	private static final String TIPO_MEDIOS = NOMBRE_TIPO_MEDIOS.substring(0, 1);
	private Vector tipoOrdenVector = new Vector();
	private int tipoOrdenSeleccionado;
	private TipoOrdenIf tipoOrdenActualizadoIf;
	private DefaultTableModel tableModel;

	public TipoOrdenModel() {
		showSaveMode();
		initKeyListeners();
		setSorterTable(getTblTipoOrden());
		getTblTipoOrden().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setAnchoColumnas();
		getTblTipoOrden().addMouseListener(oMouseAdapterTblTipoOrden);
		getTblTipoOrden().addKeyListener(oKeyAdapterTblTipoOrden);
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}

	private void setAnchoColumnas() {
		TableColumn anchoColumna = getTblTipoOrden().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblTipoOrden().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(400);
		anchoColumna = getTblTipoOrden().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(40);
	}

	MouseListener oMouseAdapterTblTipoOrden = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblTipoOrden = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		// Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoOrdenSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoOrdenActualizadoIf = (TipoOrdenIf) getTipoOrdenVector().get(getTipoOrdenSeleccionado());
			getTxtCodigo().setText(getTipoOrdenActualizadoIf().getCodigo());
			getTxtNombre().setText(getTipoOrdenActualizadoIf().getNombre());
			if (getTipoOrdenActualizadoIf().getTipo().equals(TIPO_PRESUPUESTO))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_PRESUPUESTO);
			if (getTipoOrdenActualizadoIf().getTipo().equals(TIPO_MEDIOS))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_MEDIOS);
			
			showUpdateMode();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}

	private void cargarTabla() {
		try {
			Collection tipoOrden = SessionServiceLocator.getTipoOrdenSessionService().findTipoOrdenByEmpresaId(Parametros.getIdEmpresa());
			Iterator tipoOrdenIterator = tipoOrden.iterator();

			if (!getTipoOrdenVector().isEmpty()) {
				getTipoOrdenVector().removeAllElements();
			}

			while (tipoOrdenIterator.hasNext()) {
				TipoOrdenIf tipoOrdenIf = (TipoOrdenIf) tipoOrdenIterator.next();

				tableModel = (DefaultTableModel) getTblTipoOrden().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoOrdenVector().add(tipoOrdenIf);

				agregarColumnasTabla(tipoOrdenIf, fila);

				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoOrden(), tipoOrden, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void agregarColumnasTabla(TipoOrdenIf tipoOrdenIf, Vector<String> fila) {
		fila.add(tipoOrdenIf.getCodigo());
		fila.add(tipoOrdenIf.getNombre());
		if (tipoOrdenIf.getTipo().equals(TIPO_PRESUPUESTO))
			fila.add(NOMBRE_TIPO_PRESUPUESTO);
		if (tipoOrdenIf.getTipo().equals(TIPO_MEDIOS))
			fila.add(NOMBRE_TIPO_MEDIOS);
	}

	public void save() {
		if (validateFields()) {
			TipoOrdenData data = new TipoOrdenData();
			data.setCodigo(this.getTxtCodigo().getText());
			data.setNombre(this.getTxtNombre().getText());
			if (this.getCmbTipo().getSelectedItem().equals(NOMBRE_TIPO_PRESUPUESTO))
				data.setTipo(TIPO_PRESUPUESTO);
			if (this.getCmbTipo().getSelectedItem().equals(NOMBRE_TIPO_MEDIOS))
				data.setTipo(TIPO_MEDIOS);
			data.setEmpresaId(Parametros.getIdEmpresa());

			try {
				SessionServiceLocator.getTipoOrdenSessionService().addTipoOrden(data);
				showSaveMode();
				SpiritAlert.createAlert("Tipo de Orden guardado con éxito", SpiritAlert.INFORMATION);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error al guardar la informacin!", SpiritAlert.ERROR);
			}
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setTipoOrdenActualizadoIf((TipoOrdenIf) getTipoOrdenVector().get(getTipoOrdenSeleccionado()));
				getTipoOrdenActualizadoIf().setCodigo(getTxtCodigo().getText());
				getTipoOrdenActualizadoIf().setNombre(getTxtNombre().getText());
				if (getCmbTipo().getSelectedItem().equals(NOMBRE_TIPO_PRESUPUESTO))
					getTipoOrdenActualizadoIf().setTipo(TIPO_PRESUPUESTO);
				if (this.getCmbTipo().getSelectedItem().equals(NOMBRE_TIPO_MEDIOS))
					getTipoOrdenActualizadoIf().setTipo(TIPO_MEDIOS);
				SessionServiceLocator.getTipoOrdenSessionService().saveTipoOrden(getTipoOrdenActualizadoIf());
				getTipoOrdenVector().setElementAt(getTipoOrdenActualizadoIf(), getTipoOrdenSeleccionado());
				setTipoOrdenActualizadoIf(null);
				showSaveMode();
				SpiritAlert.createAlert("Tipo de Orden actualizado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informacin!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			TipoOrdenIf tipoOrdenIf = (TipoOrdenIf) getTipoOrdenVector().get(getTipoOrdenSeleccionado());
			SessionServiceLocator.getTipoOrdenSessionService().deleteTipoOrden(tipoOrdenIf.getId());
			showSaveMode();
			SpiritAlert.createAlert("Tipo de Orden eliminado con éxito", SpiritAlert.INFORMATION);
		} catch (Exception e) {
			e.printStackTrace();
			showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}

	public void clean() {
		getTxtCodigo().setText("");
		getTxtNombre().setText("");
		getCmbTipo().setSelectedItem(null);
		DefaultTableModel model = (DefaultTableModel) getTblTipoOrden().getModel();
		for (int i = this.getTblTipoOrden().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}

	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();

		Collection tipoOrdenes = null;
		boolean codigoRepetido = false;

		try {
			tipoOrdenes = SessionServiceLocator.getTipoOrdenSessionService().findTipoOrdenByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator tipoOrdenesIt = tipoOrdenes.iterator();

		while (tipoOrdenesIt.hasNext()) {
			TipoOrdenIf tipoOrdensIf = (TipoOrdenIf) tipoOrdenesIt.next();

			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoOrdensIf.getCodigo()))
					codigoRepetido = true;

			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoOrdensIf.getCodigo()))
					if (tipoOrdenActualizadoIf.getId().equals(tipoOrdensIf.getId()) == false)
						codigoRepetido = true;
		}

		if (codigoRepetido) {
			SpiritAlert.createAlert("El cdigo del Tipo de Orden está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un cdigo para el Tipo de Orden !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo de Orden !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (getCmbTipo().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un tipo !!", SpiritAlert.WARNING);
			getCmbTipo().grabFocus();
			return false;
		}

		return true;
	}

	public void showUpdateMode() {
		setUpdateMode();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public TipoOrdenIf getTipoOrdenActualizadoIf() {
		return tipoOrdenActualizadoIf;
	}

	public void setTipoOrdenActualizadoIf(TipoOrdenIf tipoOrdenActualizadoIf) {
		this.tipoOrdenActualizadoIf = tipoOrdenActualizadoIf;
	}

	public int getTipoOrdenSeleccionado() {
		return tipoOrdenSeleccionado;
	}

	public void setTipoOrdenSeleccionado(int tipoOrdenSeleccionado) {
		this.tipoOrdenSeleccionado = tipoOrdenSeleccionado;
	}

	public Vector getTipoOrdenVector() {
		return tipoOrdenVector;
	}

	public void setTipoOrdenVector(Vector tipoOrdenVector) {
		this.tipoOrdenVector = tipoOrdenVector;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
 
}
