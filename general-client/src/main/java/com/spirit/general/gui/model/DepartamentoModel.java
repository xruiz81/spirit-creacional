package com.spirit.general.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DepartamentoData;
import com.spirit.general.entity.DepartamentoIf;
import com.spirit.general.gui.controller.GeneralFinder;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPDepartamento;
import com.spirit.general.session.DepartamentoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class DepartamentoModel extends JPDepartamento {
	
	private static final long serialVersionUID = 4734638512132987629L;
	private DepartamentoIf departamentoPadre;
	
	private Vector departamentoVector = new Vector();
	private int departamentoSeleccionado;
	private DepartamentoIf departamentoActualizadoIf;
	private DefaultTableModel tableModel;

	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 50;

	public DepartamentoModel() {
		anchoColumnasTabla();
		initKeyListeners();
		setSorterTable(getTblDepartamento());
		getTblDepartamento().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getChkDepartamento().addActionListener(new CheckBoxDepartamentoHandler());
		getTblDepartamento().addMouseListener(oMouseAdapterTblDepartamento);
		getTblDepartamento().addKeyListener(oKeyAdapterTblDepartamento);
		showSaveMode();
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblDepartamento().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblDepartamento().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(180);
		anchoColumna = getTblDepartamento().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(180);
	}
	
	MouseListener oMouseAdapterTblDepartamento = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblDepartamento = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setDepartamentoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
			departamentoActualizadoIf = (DepartamentoIf)  getDepartamentoVector().get(getDepartamentoSeleccionado());
			getTxtCodigo().setText(getDepartamentoActualizadoIf().getCodigo());
			getTxtNombre().setText(getDepartamentoActualizadoIf().getNombre());
			if (getDepartamentoActualizadoIf().getDepartamentoId() != null) {
				getChkDepartamento().setSelected(true);
				getChkDepartamento().setEnabled(true);
				getCmbPadre().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPadre(), getDepartamentoActualizadoIf().getDepartamentoId()));
				getCmbPadre().setEnabled(true);
			} else {
				getChkDepartamento().setSelected(false);
				getChkDepartamento().setEnabled(true);
				getCmbPadre().setSelectedItem(null);
				getCmbPadre().setEnabled(true);
			}
			
			showUpdateMode();
		}
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}

	public void save() {

		if (validateFields()) {
			DepartamentoData data = new DepartamentoData();
			data.setCodigo(this.getTxtCodigo().getText());
			data.setNombre(this.getTxtNombre().getText());
			data.setEmpresaId(Parametros.getIdEmpresa());
			
			if (getChkDepartamento().isSelected()) {
				data.setDepartamentoId(((DepartamentoIf) this.getCmbPadre().getSelectedItem()).getId());
			}
		
			try {
				SessionServiceLocator.getDepartamentoSessionService().addDepartamento(data);
				SpiritAlert.createAlert("Departamento guardado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("departamentos",null);
				showSaveMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la informaci\u00f3n!",SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}

	public void update() {		
		try {
			if (validateFields()) {
				setDepartamentoActualizadoIf((DepartamentoIf) getDepartamentoVector().get(getDepartamentoSeleccionado()));
				
				getDepartamentoActualizadoIf().setNombre(getTxtNombre().getText());
				if(departamentoPadre!= null)
					getDepartamentoActualizadoIf().setDepartamentoId(((DepartamentoIf) this.getCmbPadre().getSelectedItem()).getId());
				
				SessionServiceLocator.getDepartamentoSessionService().saveDepartamento(getDepartamentoActualizadoIf());
				getDepartamentoVector().setElementAt(getDepartamentoActualizadoIf(), getDepartamentoSeleccionado());
				setDepartamentoActualizadoIf(null);
				
				SpiritAlert.createAlert("Departamento actualizado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("departamentos",null);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!",SpiritAlert.ERROR);
			e.printStackTrace();
		}	
	}
	
	public void delete() {
		try {
			DepartamentoIf departamentoIf = (DepartamentoIf) getDepartamentoVector().get(getDepartamentoSeleccionado());
			SessionServiceLocator.getDepartamentoSessionService().deleteDepartamento(departamentoIf.getId());
			SpiritAlert.createAlert("Departamento eliminado con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("departamentos",null);
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
			Collection departamento = SessionServiceLocator.getDepartamentoSessionService().findDepartamentoByEmpresaId(Parametros.getIdEmpresa()); 
			Iterator departamentoIterator = departamento.iterator();
			
			if(!getDepartamentoVector().isEmpty()){
				getDepartamentoVector().removeAllElements();
			}
			
			while (departamentoIterator.hasNext()) {
				DepartamentoIf departamentoIf = (DepartamentoIf) departamentoIterator.next();
				
				tableModel = (DefaultTableModel) getTblDepartamento().getModel();
				Vector<String> fila = new Vector<String>();
				getDepartamentoVector().add(departamentoIf);
				
				agregarColumnasTabla(departamentoIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblDepartamento(), departamento, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(DepartamentoIf departamentoIf, Vector<String> fila){
		fila.add(departamentoIf.getCodigo());
		fila.add(departamentoIf.getNombre());
		if (departamentoIf.getDepartamentoId() != null) {
			try {
				fila.add(SessionServiceLocator.getDepartamentoSessionService().getDepartamento(departamentoIf.getDepartamentoId()).getNombre());
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtNombre().getText())
				&& (getChkDepartamento().isSelected() 
				&& "".equals(this.getCmbPadre().getSelectedItem().toString()))) {

			return true;
		} else {
			return false;
		}
	}

	public boolean validateFields() {

		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		
		Collection departamentos = null;
		boolean codigoRepetido = false;
		
		try {
			departamentos = SessionServiceLocator.getDepartamentoSessionService().findDepartamentoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator departamentoIt = departamentos.iterator();
		
		while (departamentoIt.hasNext()) {
			DepartamentoIf departamentoIf = (DepartamentoIf) departamentoIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(departamentoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(departamentoIf.getCodigo())) 
					if (departamentoActualizadoIf.getId().equals(departamentoIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El c\u00f3digo del Departamento está duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un c\u00f3digo para el Departamento !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Departamento !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (getChkDepartamento().isSelected() && (getCmbPadre().getSelectedIndex() == -1)) {
			SpiritAlert.createAlert("Debe seleccionar un departamento padre para el Departamento !!",SpiritAlert.WARNING);
			getCmbPadre().grabFocus();
			return false;
		}
		
		return true;
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getCmbPadre().setSelectedItem(null);

		// Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblDepartamento().getModel();
		
		for(int i= this.getTblDepartamento().getRowCount();i>0;--i)
			model.removeRow(i-1);

		this.repaint();
	}

	public void showFindMode() {
		getCmbPadre().setEnabled(false);
		getChkDepartamento().setSelected(false);
		showSaveMode();
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getCmbPadre().setEnabled(false);
		getChkDepartamento().setEnabled(true);
		getChkDepartamento().setSelected(false);
		clean();
		cargarTabla();
		getCmbPadre().removeAllItems();
		SpiritComboBoxModel cmbModelDepartamento = new SpiritComboBoxModel(GeneralFinder.findDepartamentos());
		getCmbPadre().setModel(cmbModelDepartamento);
		getTxtCodigo().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getCmbPadre().setEnabled(false);
		getChkDepartamento().setEnabled(false);
		getTblDepartamento().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private class CheckBoxDepartamentoHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (getChkDepartamento().isSelected()) {
				getCmbPadre().setEnabled(true);
				SpiritComboBoxModel cmbModelDepartamento = new SpiritComboBoxModel(GeneralFinder.findDepartamentos());
				getCmbPadre().setModel(cmbModelDepartamento);
			} else {
				getCmbPadre().removeAllItems();
				getCmbPadre().setEnabled(false);
				getCmbPadre().repaint();
			}	
		}
	}

	public Vector getDepartamentoVector() {
		return this.departamentoVector;
	}
		
	public void setDepartamentoVector(Vector departamentoVector) {
		this.departamentoVector = departamentoVector;
	}
		
	public int getDepartamentoSeleccionado() {
		return this.departamentoSeleccionado;
	}
		
	public void setDepartamentoSeleccionado(int departamentoSeleccionado) {
		this.departamentoSeleccionado = departamentoSeleccionado;
	}
		
	public DepartamentoIf getDepartamentoActualizadoIf() {
		return this.departamentoActualizadoIf;
	}
		
	public void setDepartamentoActualizadoIf(DepartamentoIf departamentoActualizadoIf) {
		this.departamentoActualizadoIf = departamentoActualizadoIf;
	}

	 
}
