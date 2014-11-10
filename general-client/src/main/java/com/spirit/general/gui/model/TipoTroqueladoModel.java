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
import javax.swing.table.TableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoTroqueladoData;
import com.spirit.general.entity.TipoTroqueladoIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPTipoTroquelado;
import com.spirit.general.session.TipoTroqueladoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoTroqueladoModel extends JPTipoTroquelado {

	private static final long serialVersionUID = 3257283617339029297L;	
	protected TableModel dataModel;
	TipoTroqueladoIf tipoTroquelado;
	boolean isPopup = false;

	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 50;
	
	private Vector tipoTroqueladoVector = new Vector();
	private int tipoTroqueladoSeleccionado;
	private TipoTroqueladoIf tipoTroqueladoActualizadoIf;
	private DefaultTableModel tableModel;

	public TipoTroqueladoModel() {
		initKeyListeners();
		setSorterTable(getTblTipoTroquelado());
		anchoColumnasTabla();
		getTblTipoTroquelado().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		getTblTipoTroquelado().addMouseListener(oMouseAdapterTblPais);
		getTblTipoTroquelado().addKeyListener(oKeyAdapterTblPais);
		
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtDescripcion().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoTroquelado().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTipoTroquelado().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
		anchoColumna = getTblTipoTroquelado().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(50);
	}
	
	MouseListener oMouseAdapterTblPais = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblPais = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoTroqueladoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
			tipoTroqueladoActualizadoIf = (TipoTroqueladoIf)  getTipoTroqueladoVector().get(getTipoTroqueladoSeleccionado());
			getTxtCodigo().setText(getTipoTroqueladoActualizadoIf().getCodigo());
			getTxtDescripcion().setText(getTipoTroqueladoActualizadoIf().getDescripcion());
			getCmbSeccionesHoja().setSelectedItem(getTipoTroqueladoActualizadoIf().getNumeroSeccionesHoja().toString());
			showUpdateMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				TipoTroqueladoData data = new TipoTroqueladoData();
				
				data.setCodigo(this.getTxtCodigo().getText());
				data.setDescripcion(this.getTxtDescripcion().getText());
				data.setNumeroSeccionesHoja(Integer.parseInt(this.getCmbSeccionesHoja().getSelectedItem().toString()));

				SessionServiceLocator.getTipoTroqueladoSessionService().addTipoTroquelado(data);
				SpiritAlert.createAlert("Tipo troquelado guardado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setTipoTroqueladoActualizadoIf((TipoTroqueladoIf) getTipoTroqueladoVector().get(getTipoTroqueladoSeleccionado()));

				getTipoTroqueladoActualizadoIf().setCodigo(getTxtCodigo().getText());
				getTipoTroqueladoActualizadoIf().setDescripcion(getTxtDescripcion().getText());
				getTipoTroqueladoActualizadoIf().setNumeroSeccionesHoja(Integer.parseInt(getCmbSeccionesHoja().getSelectedItem().toString()));
				
				SessionServiceLocator.getTipoTroqueladoSessionService().saveTipoTroquelado(getTipoTroqueladoActualizadoIf());
				getTipoTroqueladoVector().setElementAt(getTipoTroqueladoActualizadoIf(), getTipoTroqueladoSeleccionado());
				setTipoTroqueladoActualizadoIf(null);
				
				SpiritAlert.createAlert("Tipo troquelado actualizado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la información!",SpiritAlert.ERROR);
		}		
	}

	public void delete() {
		try {
			TipoTroqueladoIf tipoTroqueladoIf = (TipoTroqueladoIf) getTipoTroqueladoVector().get(getTipoTroqueladoSeleccionado());
			SessionServiceLocator.getTipoTroqueladoSessionService().deleteTipoTroquelado(tipoTroqueladoIf.getId());
			SpiritAlert.createAlert("Tipo troquelado eliminado con éxito",SpiritAlert.INFORMATION);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	private void agregarFilasTabla(TipoTroqueladoIf tipoTroquelado, Vector<String> fila){
		fila.add(tipoTroquelado.getCodigo());
		fila.add(tipoTroquelado.getDescripcion());
		fila.add(tipoTroquelado.getNumeroSeccionesHoja().toString());
	}
	
	private void cargarTabla() {
		try {			
			Collection tiposTroquelado = SessionServiceLocator.getTipoTroqueladoSessionService().getTipoTroqueladoList(); 
			Iterator tipoTroqueladoIterator = tiposTroquelado.iterator();
			
			if(!getTipoTroqueladoVector().isEmpty()){
				getTipoTroqueladoVector().removeAllElements();
			}
			
			while (tipoTroqueladoIterator.hasNext()) {
				TipoTroqueladoIf tipoTroqueladoIf = (TipoTroqueladoIf) tipoTroqueladoIterator.next();
				
				tableModel = (DefaultTableModel) getTblTipoTroquelado().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoTroqueladoVector().add(tipoTroqueladoIf);
				
				agregarFilasTabla(tipoTroqueladoIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoTroquelado(), tiposTroquelado, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtDescripcion().getText())) {
			return true;
		} else {
			return false;
		}

	}
	
	public boolean validateFields() {
		
		String strCodigo = this.getTxtCodigo().getText();
		String strDescripcion = this.getTxtDescripcion().getText();
		
		Collection tipoTroquelado = null;
		boolean codigoRepetido = false;
		
		try {
			tipoTroquelado = SessionServiceLocator.getTipoTroqueladoSessionService().getTipoTroqueladoList();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator tipoTroqueladoIt = tipoTroquelado.iterator();
		
		while (tipoTroqueladoIt.hasNext()) {
			TipoTroqueladoIf tipoTroqueladoIf = (TipoTroqueladoIf) tipoTroqueladoIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoTroqueladoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoTroqueladoIf.getCodigo())) 
					if (getTipoTroqueladoActualizadoIf().getId().equals(tipoTroqueladoIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Tipo de Troquelado está duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para el Tipo de Troquelado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strDescripcion)) || (strDescripcion == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo de Troquelado !!",SpiritAlert.WARNING);
			getTxtDescripcion().grabFocus();
			return false;
		}
		
		return true;
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtDescripcion().setText("");
		this.getCmbSeccionesHoja().setSelectedIndex(0);
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoTroquelado().getModel();
		
		for(int i= this.getTblTipoTroquelado().getRowCount();i>0;--i)
			model.removeRow(i-1);

	}

	public void showFindMode() {
		showSaveMode();
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtDescripcion().setEnabled(true);
		getCmbSeccionesHoja().setEnabled(true);

		clean();
		cargarTabla();		
		getTxtCodigo().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtDescripcion().setEnabled(true);
		getCmbSeccionesHoja().setEnabled(true);
		getTblTipoTroquelado().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public Vector getTipoTroqueladoVector() {
		return this.tipoTroqueladoVector;
	}
	
	public void setTipoTroqueladoVector(Vector tipoTroqueladoVector) {
		this.tipoTroqueladoVector = tipoTroqueladoVector;
	}
	
	public int getTipoTroqueladoSeleccionado() {
		return this.tipoTroqueladoSeleccionado;
	}
	
	public void setTipoTroqueladoSeleccionado(int tipoTroqueladoSeleccionado) {
		this.tipoTroqueladoSeleccionado = tipoTroqueladoSeleccionado;
	}
	
	public TipoTroqueladoIf getTipoTroqueladoActualizadoIf() {
		return this.tipoTroqueladoActualizadoIf;
	}
	
	public void setTipoTroqueladoActualizadoIf(TipoTroqueladoIf tipoTroqueladoActualizadoIf) {
		this.tipoTroqueladoActualizadoIf = tipoTroqueladoActualizadoIf;
	} 
}
