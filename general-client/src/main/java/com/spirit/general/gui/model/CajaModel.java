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
import java.util.List;
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
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CajaData;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPCaja;
import com.spirit.general.session.CajaSessionService;
import com.spirit.general.session.OficinaSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class CajaModel extends JPCaja {

	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final String NOMBRE_ESTADO_ACTIVA = "ACTIVA";
	private static final String ESTADO_ACTIVA = NOMBRE_ESTADO_ACTIVA.substring(0, 1);
	private static final String NOMBRE_ESTADO_INACTIVA = "INACTIVA";
	private static final String ESTADO_INACTIVA = NOMBRE_ESTADO_INACTIVA.substring(0, 1);
	private static final String NOMBRE_TURNO_A = "A TURNO";
	private static final String TURNO_A = NOMBRE_TURNO_A.substring(0, 1);
	private static final String NOMBRE_TURNO_B = "B TURNO";
	private static final String TURNO_B = NOMBRE_TURNO_B.substring(0, 1);
	private static final String NOMBRE_TURNO_C = "C TURNO";
	private static final String TURNO_C = NOMBRE_TURNO_C.substring(0, 1);
	
	private Vector cajaVector = new Vector();
	private int cajaSeleccionada;
	private CajaIf cajaActualizadaIf;
	private DefaultTableModel tableModel;
	private OficinaIf oficinaIf;

	public CajaModel() {
		showSaveMode();
		initKeyListeners();
		setSorterTable(getTblCaja());
		getTblCaja().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setAnchoColumnas();
		getTblCaja().addMouseListener(oMouseAdapterTblCaja);
		getTblCaja().addKeyListener(oKeyAdapterTblCaja);
		new HotKeyComponent(this);
	}
	
	

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}

	private void setAnchoColumnas() {
		TableColumn anchoColumna = getTblCaja().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);

		anchoColumna = getTblCaja().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(200);
		
		anchoColumna = getTblCaja().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(200);
		
		anchoColumna = getTblCaja().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(80);

		anchoColumna = getTblCaja().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(40);
	}

	MouseListener oMouseAdapterTblCaja = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblCaja = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		// Obtengo la instancia del objeto seleccionado de la tabla
		int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
		if (selectedRow != -1) {
			setCajaSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
			cajaActualizadaIf = (CajaIf) getCajaVector().get(getCajaSeleccionada());
			getTxtCodigo().setText(getCajaActualizadaIf().getCodigo());
			getTxtNombre().setText(getCajaActualizadaIf().getNombre());
			try {
				oficinaIf = SessionServiceLocator.getOficinaSessionService().getOficina(getCajaActualizadaIf().getOficinaId());
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
			getCmbOficina().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbOficina(),oficinaIf.getId()));
			getCmbOficina().repaint();
			if (getCajaActualizadaIf().getTurno().equals(TURNO_A))
				getCmbTurno().setSelectedItem(NOMBRE_TURNO_A);
			else if (getCajaActualizadaIf().getTurno().equals(TURNO_B))
				getCmbTurno().setSelectedItem(NOMBRE_TURNO_B);
			else if (getCajaActualizadaIf().getTurno().equals(TURNO_C))
				getCmbTurno().setSelectedItem(NOMBRE_TURNO_C);
			if (getCajaActualizadaIf().getEstado().equals(ESTADO_ACTIVA))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVA);
			if (getCajaActualizadaIf().getEstado().equals(ESTADO_INACTIVA))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVA);

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
			Collection caja = SessionServiceLocator.getCajaSessionService().findCajaByEmpresaId(Parametros.getIdEmpresa());
			Iterator cajaIterator = caja.iterator();

			if (!getCajaVector().isEmpty()) {
				getCajaVector().removeAllElements();
			}

			while (cajaIterator.hasNext()) {
				CajaIf cajaIf = (CajaIf) cajaIterator.next();
				
				tableModel = (DefaultTableModel) getTblCaja().getModel();
				Vector<String> fila = new Vector<String>();
				getCajaVector().add(cajaIf);
				
				agregarColumnasTabla(cajaIf, fila);

				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblCaja(), caja, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void agregarColumnasTabla(CajaIf cajaIf, Vector<String> fila) {
		fila.add(cajaIf.getCodigo());
		fila.add(cajaIf.getNombre());
		try {
			OficinaIf oficinaIf = SessionServiceLocator.getOficinaSessionService().getOficina(cajaIf.getOficinaId());
			fila.add(oficinaIf.getNombre());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		if (cajaIf.getTurno().equals(TURNO_A))
			fila.add(NOMBRE_TURNO_A);
		else if (cajaIf.getTurno().equals(TURNO_B))
			fila.add(NOMBRE_TURNO_B);
		else if (cajaIf.getTurno().equals(TURNO_C))
			fila.add(NOMBRE_TURNO_C);
		
		if (cajaIf.getEstado().equals(ESTADO_ACTIVA))
			fila.add(NOMBRE_ESTADO_ACTIVA);
		if (cajaIf.getEstado().equals(ESTADO_INACTIVA))
			fila.add(NOMBRE_ESTADO_INACTIVA);
	}

	public void cargarCombos() {
		cargarComboOficina();
	}
	
	private void cargarComboOficina() {
		try {
			List oficinas = (List) SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbOficina(), oficinas);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	public void save() {
		if (validateFields()) {
			CajaData data = new CajaData();

			data.setCodigo(this.getTxtCodigo().getText());
			data.setNombre(this.getTxtNombre().getText());
			data.setOficinaId(((OficinaIf) this.getCmbOficina().getSelectedItem()).getId());
			if (this.getCmbTurno().getSelectedItem().equals(NOMBRE_TURNO_A))
				data.setTurno(TURNO_A);
			else if (this.getCmbTurno().getSelectedItem().equals(NOMBRE_TURNO_B))
				data.setTurno(TURNO_B);
			else if (this.getCmbTurno().getSelectedItem().equals(NOMBRE_TURNO_C))
				data.setTurno(TURNO_C);
			if (this.getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVA))
				data.setEstado(ESTADO_ACTIVA);
			if (this.getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVA))
				data.setEstado(ESTADO_INACTIVA);

			try {
				SessionServiceLocator.getCajaSessionService().addCaja(data);
				showSaveMode();
				SpiritAlert.createAlert("Caja guardada con éxito", SpiritAlert.INFORMATION);
				SpiritCache.setObject("caja", null);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error al guardar la informaci\u00f3n!", SpiritAlert.ERROR);
			}
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setCajaActualizadaIf((CajaIf) getCajaVector().get(getCajaSeleccionada()));

				getCajaActualizadaIf().setCodigo(getTxtCodigo().getText());
				getCajaActualizadaIf().setNombre(getTxtNombre().getText());
				getCajaActualizadaIf().setOficinaId(((OficinaIf) this.getCmbOficina().getSelectedItem()).getId());
				if (this.getCmbTurno().getSelectedItem().equals(NOMBRE_TURNO_A))
					getCajaActualizadaIf().setTurno(TURNO_A);
				else if (this.getCmbTurno().getSelectedItem().equals(NOMBRE_TURNO_B))
					getCajaActualizadaIf().setTurno(TURNO_B);
				else if (this.getCmbTurno().getSelectedItem().equals(NOMBRE_TURNO_C))
					getCajaActualizadaIf().setTurno(TURNO_C);
				if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVA))
					getCajaActualizadaIf().setEstado(ESTADO_ACTIVA);
				if (this.getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVA))
					getCajaActualizadaIf().setEstado(ESTADO_INACTIVA);

				SessionServiceLocator.getCajaSessionService().saveCaja(getCajaActualizadaIf());
				getCajaVector().setElementAt(getCajaActualizadaIf(), getCajaSeleccionada());
				setCajaActualizadaIf(null);
				showSaveMode();
				SpiritAlert.createAlert("Caja actualizada con éxito", SpiritAlert.INFORMATION);
				SpiritCache.setObject("caja", null);
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			CajaIf cajaIf = (CajaIf) getCajaVector().get(getCajaSeleccionada());
			SessionServiceLocator.getCajaSessionService().deleteCaja(cajaIf.getId());
			showSaveMode();
			SpiritAlert.createAlert("Caja eliminado con éxito", SpiritAlert.INFORMATION);
			SpiritCache.setObject("caja", null);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}
	
	public void refresh(){
		cargarComboOficina();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public void clean() {
		getTxtCodigo().setText("");
		getTxtNombre().setText("");
		getCmbOficina().setSelectedItem(null);
		getCmbTurno().setSelectedItem(null);
		getCmbEstado().setSelectedItem(null);
		cargarCombos();

		DefaultTableModel model = (DefaultTableModel) getTblCaja().getModel();
		for (int i = this.getTblCaja().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}

	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();

		Collection cajas = null;
		boolean codigoRepetido = false;

		try {
			cajas = SessionServiceLocator.getCajaSessionService().findCajaByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator cajasIt = cajas.iterator();

		while (cajasIt.hasNext()) {
			CajaIf cajasIf = (CajaIf) cajasIt.next();

			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(cajasIf.getCodigo()))
					codigoRepetido = true;

			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(cajasIf.getCodigo()))
					if (cajaActualizadaIf.getId().equals(cajasIf.getId()) == false)
						codigoRepetido = true;
		}

		if (codigoRepetido) {
			SpiritAlert.createAlert("El c\u00f3digo de la Caja está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un c\u00f3digo para la Caja !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para la Caja !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (getCmbOficina().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar una oficina para la Caja!!", SpiritAlert.WARNING);
			getCmbOficina().grabFocus();
			return false;
		}
		
		if (getCmbTurno().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un turno para la Caja!!", SpiritAlert.WARNING);
			getCmbTurno().grabFocus();
			return false;
		}
		
		if (getCmbEstado().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un estado para la Caja!!", SpiritAlert.WARNING);
			getCmbEstado().grabFocus();
			return false;
		}

		return true;
	}

	public void showUpdateMode() {
		setUpdateMode();
	}

	public CajaIf getCajaActualizadaIf() {
		return cajaActualizadaIf;
	}

	public void setCajaActualizadaIf(CajaIf cajaActualizadaIf) {
		this.cajaActualizadaIf = cajaActualizadaIf;
	}

	public int getCajaSeleccionada() {
		return cajaSeleccionada;
	}

	public void setCajaSeleccionada(int cajaSeleccionada) {
		this.cajaSeleccionada = cajaSeleccionada;
	}

	public Vector getCajaVector() {
		return cajaVector;
	}

	public void setCajaVector(Vector cajaVector) {
		this.cajaVector = cajaVector;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
 
}
