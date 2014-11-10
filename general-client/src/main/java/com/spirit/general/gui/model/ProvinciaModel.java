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
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.PaisIf;
import com.spirit.general.entity.ProvinciaData;
import com.spirit.general.entity.ProvinciaIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPProvincia;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class ProvinciaModel extends JPProvincia {
	
	private static final long serialVersionUID = 3546080246274077753L;
	Map aMap;
	ProvinciaIf provincia;
	ArrayList lista;
	protected TableModel dataModel;
	boolean isPopup = false;
	
	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 50;
	
	private Vector provinciaVector = new Vector();
	private DefaultTableModel tableModel;
	private int provinciaSeleccionada;
	protected ProvinciaIf provinciaIf;
	

	public ProvinciaModel() {
		initKeyListeners();
		setSorterTable(getTblProvincia());
		getTblProvincia().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		showSaveMode();
		
		getTblProvincia().addMouseListener(oMouseAdapterTblProvincia);
		getTblProvincia().addKeyListener(oKeyAdapterTblProvincia);
		
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	MouseListener oMouseAdapterTblProvincia = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblProvincia = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setProvinciaSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
			provinciaIf = (ProvinciaIf)  getProvinciaVector().get(getProvinciaSeleccionada());				
			getTxtCodigo().setText(provinciaIf.getCodigo());
			getTxtNombre().setText(provinciaIf.getNombre());
			getCmbPais().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPais(), provinciaIf.getPaisId()));
			getCmbPais().repaint();
			showUpdateMode();
		}
	}

	public void save() {
		if (validateFields()) {
			ProvinciaData data = new ProvinciaData();

			data.setCodigo(this.getTxtCodigo().getText());
			data.setNombre(this.getTxtNombre().getText());
			data.setPaisId(((PaisIf) this.getCmbPais().getSelectedItem()).getId());

			try {
				SessionServiceLocator.getProvinciaSessionService().addProvincia(data);
				SpiritAlert.createAlert("Provincia guardada con éxito!",SpiritAlert.INFORMATION);
				SpiritCache.setObject("provincia",null);
				showSaveMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la infomacion!",SpiritAlert.ERROR);
			}
		} 
	}

	public void update() {
		try {
			if (validateFields()) {
				setProvinciaActualizadaIf((ProvinciaIf) getProvinciaVector().get(getProvinciaSeleccionada()));
				
				getProvinciaActualizadaIf().setCodigo(getTxtCodigo().getText());
				getProvinciaActualizadaIf().setNombre(getTxtNombre().getText());
				if(getCmbPais().getSelectedItem() != null) getProvinciaActualizadaIf().setPaisId(((PaisIf) getCmbPais().getSelectedItem()).getId());
				
				SessionServiceLocator.getProvinciaSessionService().saveProvincia(getProvinciaActualizadaIf());
				getProvinciaVector().setElementAt(getProvinciaActualizadaIf(), getProvinciaSeleccionada());
				setProvinciaActualizadaIf(null);
				
				SpiritAlert.createAlert("Provincia actualizada con éxito!",SpiritAlert.INFORMATION);
				SpiritCache.setObject("provincia",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar informacion!",SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			ProvinciaIf provinciaIf = (ProvinciaIf) getProvinciaVector().get(getProvinciaSeleccionada());
			SessionServiceLocator.getProvinciaSessionService().deleteProvincia(provinciaIf.getId());
			SpiritAlert.createAlert("Provincia eliminada con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("provincia",null);
			showSaveMode();
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtNombre().getText())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		
		Collection provincia = null;
		boolean codigoRepetido = false;
		
		try {
			provincia = SessionServiceLocator.getProvinciaSessionService().getProvinciaList();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator provinciaIt = provincia.iterator();
		
		while (provinciaIt.hasNext()) {
			ProvinciaIf provinciaIf = (ProvinciaIf) provinciaIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(provinciaIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(provinciaIf.getCodigo())) 
					if (getProvinciaActualizadaIf().getId().equals(provinciaIf.getId()) == false)
						codigoRepetido = true;
		}
		if (codigoRepetido) {
			SpiritAlert.createAlert("El cdigo de la Provincia está duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un cdigo para Provincia !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para Provincia !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}	
		if (getCmbPais().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar un País para Provincia !!",SpiritAlert.WARNING);
			getCmbPais().grabFocus();
			return false;
		}
		return true;
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getCmbPais().setSelectedItem(null);
		this.getCmbPais().removeAllItems();
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblProvincia().getModel();
		for(int i= this.getTblProvincia().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showFindMode() {
		showSaveMode();
	}
	
	public void cargarCombos(){
		cargarComboPais();
	}
	
	Comparator<PaisIf> ordenadorPais = new Comparator<PaisIf>(){
		public int compare(PaisIf p1, PaisIf p2) {
			return p1.getNombre().compareTo(p2.getNombre());
		}		
	};
	
	private void cargarComboPais(){
		try {
			List<PaisIf> paisesLista = (ArrayList)SessionServiceLocator.getPaisSessionService().getPaisList();
			Collections.sort(paisesLista,ordenadorPais);			
			SpiritComboBoxModel cmbModelPais = new SpiritComboBoxModel(paisesLista);
			getCmbPais().setModel(cmbModelPais);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		getCmbPais().setEnabled(true);
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtCodigo().grabFocus();
		
		clean();
		cargarCombos();
		cargarTabla();
	}
	
	private void cargarTabla() {		
		try {
			Collection provincia = SessionServiceLocator.getProvinciaSessionService().getProvinciaList();
			Iterator provinciaIterator = provincia.iterator();
			
			if(!getProvinciaVector().isEmpty()){
				getProvinciaVector().removeAllElements();
			}
			
			while (provinciaIterator.hasNext()) {
				ProvinciaIf provinciaIf = (ProvinciaIf) provinciaIterator.next();
				
				tableModel = (DefaultTableModel) getTblProvincia().getModel();
				Vector<String> fila = new Vector<String>();
				getProvinciaVector().add(provinciaIf);
				
				agregarColumnasTabla(provinciaIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblProvincia(), provincia, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(ProvinciaIf provinciaIf, Vector<String> fila){
		
		fila.add(provinciaIf.getCodigo());
		fila.add(provinciaIf.getNombre());
		
		try {
			PaisIf pais = SessionServiceLocator.getPaisSessionService().getPais(provinciaIf.getPaisId());
			fila.add(pais.getNombre());
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getCmbPais().setEnabled(true);
		getTblProvincia().grabFocus();
	}
	
	public void refresh(){
		cargarComboPais();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public Vector getProvinciaVector() {
		return provinciaVector;
	}

	public void setProvinciaVector(Vector provinciaVector) {
		this.provinciaVector = provinciaVector;
	}

	public int getProvinciaSeleccionada() {
		return provinciaSeleccionada;
	}

	public void setProvinciaSeleccionada(int provinciaSeleccionada) {
		this.provinciaSeleccionada = provinciaSeleccionada;
	}

	public ProvinciaIf getProvinciaActualizadaIf() {
		return provinciaIf;
	}

	public void setProvinciaActualizadaIf(ProvinciaIf provinciaIf) {
		this.provinciaIf = provinciaIf;
	}
 
}
