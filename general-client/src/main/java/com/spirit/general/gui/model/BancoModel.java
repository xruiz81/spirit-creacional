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
import com.spirit.general.entity.BancoData;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPBanco;
import com.spirit.general.session.BancoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class BancoModel extends JPBanco {

	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0, 1);
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0, 1);

	private Vector bancoVector = new Vector();
	private int bancoSeleccionado;
	private BancoIf bancoActualizadoIf;
	private DefaultTableModel tableModel;

	
	public BancoModel() {
		showSaveMode();
		initKeyListeners();
		setSorterTable(getTblBanco());
		getTblBanco().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setAnchoColumnas();
		getTblBanco().addMouseListener(oMouseAdapterTblBanco);
		getTblBanco().addKeyListener(oKeyAdapterTblBanco);

		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtCodigoMulticash().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
	}

	private void setAnchoColumnas() {
		TableColumn anchoColumna = getTblBanco().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblBanco().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(400);
		anchoColumna = getTblBanco().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblBanco().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(40);
	}

	MouseListener oMouseAdapterTblBanco = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblBanco = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		// Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setBancoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			bancoActualizadoIf = (BancoIf) getBancoVector().get(getBancoSeleccionado());
			getTxtCodigo().setText(getBancoActualizadoIf().getCodigo());
			getTxtNombre().setText(getBancoActualizadoIf().getNombre());
			if (getBancoActualizadoIf().getEstado().equals(ESTADO_ACTIVO))
				getCmbStatus().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			if (getBancoActualizadoIf().getEstado().equals(ESTADO_INACTIVO))
				getCmbStatus().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			
			if(getBancoActualizadoIf().getCodigoMulticash() != null){
				getTxtCodigoMulticash().setText(getBancoActualizadoIf().getCodigoMulticash());
			}else{
				getTxtCodigoMulticash().setText("");
			}
			
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
			Collection banco = getBancoSessionService().getBancoList();
			Iterator bancoIterator = banco.iterator();

			if (!getBancoVector().isEmpty()) {
				getBancoVector().removeAllElements();
			}

			while (bancoIterator.hasNext()) {
				BancoIf bancoIf = (BancoIf) bancoIterator.next();

				tableModel = (DefaultTableModel) getTblBanco().getModel();
				Vector<String> fila = new Vector<String>();
				getBancoVector().add(bancoIf);

				agregarColumnasTabla(bancoIf, fila);

				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblBanco(), banco, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void agregarColumnasTabla(BancoIf bancoIf, Vector<String> fila) {
		fila.add(bancoIf.getCodigo());
		fila.add(bancoIf.getNombre());
		if (bancoIf.getEstado().equals(ESTADO_ACTIVO))
			fila.add(NOMBRE_ESTADO_ACTIVO);
		if (bancoIf.getEstado().equals(ESTADO_INACTIVO))
			fila.add(NOMBRE_ESTADO_INACTIVO);
		
		if(bancoIf.getCodigoMulticash() != null){
			fila.add(bancoIf.getCodigoMulticash());
		}else{
			fila.add("");
		}
	}

	public void cargarCombos() {
		getCmbStatus().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbStatus().addItem(NOMBRE_ESTADO_INACTIVO);
	}

	public void save() {
		if (validateFields()) {
			BancoData data = new BancoData();

			data.setCodigo(this.getTxtCodigo().getText());
			data.setNombre(this.getTxtNombre().getText());
			if (this.getCmbStatus().getSelectedItem().equals(
					NOMBRE_ESTADO_ACTIVO))
				data.setEstado(ESTADO_ACTIVO);
			if (this.getCmbStatus().getSelectedItem().equals(
					NOMBRE_ESTADO_INACTIVO))
				data.setEstado(ESTADO_INACTIVO);
			
			data.setCodigoMulticash(this.getTxtCodigoMulticash().getText());

			try {
				getBancoSessionService().addBanco(data);
				SpiritAlert.createAlert("Banco guardado con éxito",
						SpiritAlert.INFORMATION);
				SpiritCache.setObject("banco", null);
				showSaveMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error al guardar la informaci\u00f3n!",
						SpiritAlert.ERROR);
			}
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setBancoActualizadoIf((BancoIf) getBancoVector().get(
						getBancoSeleccionado()));

				getBancoActualizadoIf().setCodigo(getTxtCodigo().getText());
				getBancoActualizadoIf().setNombre(getTxtNombre().getText());
				if (getCmbStatus().getSelectedItem().equals(
						NOMBRE_ESTADO_ACTIVO))
					getBancoActualizadoIf().setEstado(ESTADO_ACTIVO);
				if (this.getCmbStatus().getSelectedItem().equals(
						NOMBRE_ESTADO_INACTIVO))
					getBancoActualizadoIf().setEstado(ESTADO_INACTIVO);
				
				getBancoActualizadoIf().setCodigoMulticash(getTxtCodigoMulticash().getText());

				getBancoSessionService().saveBanco(getBancoActualizadoIf());
				getBancoVector().setElementAt(getBancoActualizadoIf(),
						getBancoSeleccionado());
				setBancoActualizadoIf(null);

				SpiritAlert.createAlert("Banco actualizado con éxito",
						SpiritAlert.INFORMATION);
				SpiritCache.setObject("banco", null);
				showSaveMode();
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!",
					SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			BancoIf bancoIf = (BancoIf) getBancoVector().get(
					getBancoSeleccionado());
			getBancoSessionService().deleteBanco(bancoIf.getId());
			SpiritAlert.createAlert("Banco eliminado con éxito",
					SpiritAlert.INFORMATION);
			SpiritCache.setObject("banco", null);
			showSaveMode();
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",
					SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void clean() {
		getTxtCodigo().setText("");
		getTxtNombre().setText("");
		getCmbStatus().removeAllItems();
		getTxtCodigoMulticash().setText("");
		cargarCombos();

		DefaultTableModel model = (DefaultTableModel) getTblBanco().getModel();
		for (int i = this.getTblBanco().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}

	public boolean validateFields() {

		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();

		Collection bancos = null;
		boolean codigoRepetido = false;

		try {
			bancos = getBancoSessionService().getBancoList();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator bancosIt = bancos.iterator();

		while (bancosIt.hasNext()) {
			BancoIf bancosIf = (BancoIf) bancosIt.next();

			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(bancosIf.getCodigo()))
					codigoRepetido = true;

			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(bancosIf.getCodigo()))
					if (bancoActualizadoIf.getId().equals(bancosIf.getId()) == false)
						codigoRepetido = true;
		}

		if (codigoRepetido) {
			SpiritAlert.createAlert("El c\u00f3digo del Banco está duplicado !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un c\u00f3digo para el Banco !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Banco !!",
					SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
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

	public BancoIf getBancoActualizadoIf() {
		return bancoActualizadoIf;
	}

	public void setBancoActualizadoIf(BancoIf bancoActualizadoIf) {
		this.bancoActualizadoIf = bancoActualizadoIf;
	}

	public int getBancoSeleccionado() {
		return bancoSeleccionado;
	}

	public void setBancoSeleccionado(int bancoSeleccionado) {
		this.bancoSeleccionado = bancoSeleccionado;
	}

	public Vector getBancoVector() {
		return bancoVector;
	}

	public void setBancoVector(Vector bancoVector) {
		this.bancoVector = bancoVector;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public static BancoSessionService getBancoSessionService() {
		try {
			return (BancoSessionService) ServiceLocator
					.getService(ServiceLocator.BANCOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicaci\u00f3n con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
}
