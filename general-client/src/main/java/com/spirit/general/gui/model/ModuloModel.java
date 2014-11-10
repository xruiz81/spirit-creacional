package com.spirit.general.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ModuloData;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPModulo;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class ModuloModel extends JPModulo {

	private static final long serialVersionUID = -5977237742815595752L;

	ArrayList lista;
	
	private Vector moduloVector = new Vector();
	private int moduloSeleccionado;
	private ModuloIf moduloActualizadoIf;
	private DefaultTableModel tableModel;

	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);

	public ModuloModel() {
		initKeyListeners();
		setSorterTable(getTblModulo());
		getTblModulo().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		setColumnWidthTable();
		this.getTblModulo().addMouseListener(oMouseAdapterTblModulo);
		this.getTblModulo().addKeyListener(oKeyAdapterTblModulo);
		
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void setColumnWidthTable() {

		TableColumn anchoColumna = getTblModulo().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(20);
		
		anchoColumna = getTblModulo().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(400);
		
		anchoColumna = getTblModulo().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(40);
	}

	MouseListener oMouseAdapterTblModulo = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblModulo = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setModuloSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			moduloActualizadoIf = (ModuloIf)  getModuloVector().get(getModuloSeleccionado());
			getTxtCodigo().setText(getModuloActualizadoIf().getCodigo());
			getTxtNombre().setText(getModuloActualizadoIf().getNombre());
			if (getModuloActualizadoIf().getStatus().equals(ESTADO_ACTIVO))
				getCmbStatus().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			if (getModuloActualizadoIf().getStatus().equals(ESTADO_INACTIVO))
				getCmbStatus().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			
			showUpdateMode();
		}
	}

	public void save() {
		if (validateFields()) {
			ModuloData data = new ModuloData();

			data.setCodigo(this.getTxtCodigo().getText());
			data.setNombre(this.getTxtNombre().getText());
			if (this.getCmbStatus().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO))
				data.setStatus(ESTADO_ACTIVO);
			if (this.getCmbStatus().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO))
				data.setStatus(ESTADO_INACTIVO);
						
			try {
				SessionServiceLocator.getModuloSessionService().addModulo(data);
				SpiritAlert.createAlert("Mdulo guardado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("modulo",null);
				showSaveMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error al guardar la informacin!",SpiritAlert.ERROR);
			}
		} 		
	}
	
	public void update() {
		try {
			if (validateFields()) {
				setModuloActualizadoIf((ModuloIf) getModuloVector().get(getModuloSeleccionado()));
				
				getModuloActualizadoIf().setCodigo(getTxtCodigo().getText());
				getModuloActualizadoIf().setNombre(getTxtNombre().getText());
				if (this.getCmbStatus().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO))
					getModuloActualizadoIf().setStatus(ESTADO_ACTIVO);
				if (this.getCmbStatus().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO))
					getModuloActualizadoIf().setStatus(ESTADO_INACTIVO);
				
				SessionServiceLocator.getModuloSessionService().saveModulo(getModuloActualizadoIf());
				getModuloVector().setElementAt(getModuloActualizadoIf(), getModuloSeleccionado());
				setModuloActualizadoIf(null);
				
				SpiritAlert.createAlert("Mdulo actualizado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("modulo",null);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informacin!",SpiritAlert.ERROR);
		}	
	}

	public void delete() {
		try {
			ModuloIf moduloIf = (ModuloIf) getModuloVector().get(getModuloSeleccionado());
			SessionServiceLocator.getModuloSessionService().deleteModulo(moduloIf.getId());
			SpiritAlert.createAlert("Mdulo eliminado con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("modulo",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	private void cargarTabla() {
		try {			
			Collection modulo = SessionServiceLocator.getModuloSessionService().getModuloList();
			Iterator moduloIterator = modulo.iterator();
			
			if(!getModuloVector().isEmpty()){
				getModuloVector().removeAllElements();
			}
			
			while (moduloIterator.hasNext()) {
				ModuloIf moduloIf = (ModuloIf) moduloIterator.next();
				
				tableModel = (DefaultTableModel) getTblModulo().getModel();
				Vector<String> fila = new Vector<String>();
				getModuloVector().add(moduloIf);
				
				agregarColumnasTabla(moduloIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblModulo(), modulo, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(ModuloIf moduloIf, Vector<String> fila){
		fila.add(moduloIf.getCodigo());
		fila.add(moduloIf.getNombre());
		if (moduloIf.getStatus().equals(ESTADO_ACTIVO))
			fila.add(NOMBRE_ESTADO_ACTIVO);
		if (moduloIf.getStatus().equals(ESTADO_INACTIVO))
			fila.add(NOMBRE_ESTADO_INACTIVO);
	}

	public boolean validateFields() {
		
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		
		Collection modulos = null;
		boolean codigoRepetido = false;
		
		try {
			modulos = SessionServiceLocator.getModuloSessionService().getModuloList();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator moduloIt = modulos.iterator();
		
		while (moduloIt.hasNext()) {
			ModuloIf moduloIf = (ModuloIf) moduloIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(moduloIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(moduloIf.getCodigo())) 
					if (moduloActualizadoIf.getId().equals(moduloIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El cdigo del Mdulo está duplicado !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un cdigo para el Mdulo !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Mdulo !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		return true;
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtNombre().getText())) {
			return true;
		} else {
			return false;
		}
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getCmbStatus().setSelectedItem(null);
		this.getCmbStatus().removeAllItems();
		
		// Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblModulo().getModel();
		
		for(int i= this.getTblModulo().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void cargarCombos(){
		getCmbStatus().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbStatus().addItem(NOMBRE_ESTADO_INACTIVO);
	}

	public void showFindMode() {
		showSaveMode();
	}
	
	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getCmbStatus().setEnabled(true);
		clean();
		cargarCombos();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getCmbStatus().setEnabled(true);
		getTblModulo().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public Vector getModuloVector() {
		return this.moduloVector;
	}
	
	public void setModuloVector(Vector moduloVector) {
		this.moduloVector = moduloVector;
	}
	
	public int getModuloSeleccionado() {
		return this.moduloSeleccionado;
	}
	
	public void setModuloSeleccionado(int moduloSeleccionado) {
		this.moduloSeleccionado = moduloSeleccionado;
	}
	
	public ModuloIf getModuloActualizadoIf() {
		return this.moduloActualizadoIf;
	}
	
	public void setModuloActualizadoIf(ModuloIf moduloActualizadoIf) {
		this.moduloActualizadoIf = moduloActualizadoIf;
	}
 
}
