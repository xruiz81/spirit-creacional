package com.spirit.contabilidad.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import com.spirit.contabilidad.entity.SubtipoAsientoData;
import com.spirit.contabilidad.entity.SubtipoAsientoIf;
import com.spirit.contabilidad.entity.TipoAsientoIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.panel.JPSubtipoAsiento;
import com.spirit.contabilidad.session.SubTipoAsientoSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class SubtipoAsientoModel extends JPSubtipoAsiento {

	private static final long serialVersionUID = 4879765653954132500L;
	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 40;
	private static final int MAX_LONGITUD_ORDEN = 2;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	
	private Vector subtipoAsientoVector = new Vector();
	private int subtipoAsientoSeleccionado;
	private SubtipoAsientoIf subtipoAsientoActualizadoIf;
	private DefaultTableModel tableModel;

	public SubtipoAsientoModel() {
		initKeyListeners();
		setSorterTable(getTblSubtipoAsiento());
		getTblSubtipoAsiento().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		setColumnWidthTable();
		this.getTblSubtipoAsiento().addMouseListener(oMouseAdapterTblSubtipoAsiento);
		this.getTblSubtipoAsiento().addKeyListener(oKeyAdapterTblSubtipoAsiento);
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtOrden().addKeyListener(new TextChecker(MAX_LONGITUD_ORDEN));
		getTxtOrden().addKeyListener (new NumberTextField());
	}

	private void setColumnWidthTable() {

		TableColumn anchoColumna = getTblSubtipoAsiento().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(20);
		
		anchoColumna = getTblSubtipoAsiento().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(250);
		    
		anchoColumna = getTblSubtipoAsiento().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(250);
		
		anchoColumna = getTblSubtipoAsiento().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(10);
		
		anchoColumna = getTblSubtipoAsiento().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(35);

	}

	MouseListener oMouseAdapterTblSubtipoAsiento = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblSubtipoAsiento = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setSubtipoAsientoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			subtipoAsientoActualizadoIf = (SubtipoAsientoIf)  getSubtipoAsientoVector().get(getSubtipoAsientoSeleccionado());
			
			getTxtCodigo().setText(getSubtipoAsientoActualizadoIf().getCodigo());
			getTxtNombre().setText(getSubtipoAsientoActualizadoIf().getNombre());			
			getCmbTipoAsiento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoAsiento(), getSubtipoAsientoActualizadoIf().getTipoId()));
			getCmbTipoAsiento().repaint();
			
			getTxtOrden().setText(getSubtipoAsientoActualizadoIf().getOrden().toString());
			
			if (ESTADO_ACTIVO.equals(getSubtipoAsientoActualizadoIf().getStatus()))
				getCmbStatus().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			else
				getCmbStatus().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			
			showUpdateMode();
		}
	}

	private ArrayList getModel(ArrayList listaSubtipoAsiento) {
		ArrayList data = new ArrayList();
		Iterator it = listaSubtipoAsiento.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			SubtipoAsientoIf bean = (SubtipoAsientoIf) it.next();

			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());

			data.add(fila);
		}
		return data;
	}

	public void save() {
		try {
			if (validateFields()) {
				SubtipoAsientoData data = new SubtipoAsientoData();
				data.setCodigo(this.getTxtCodigo().getText().toUpperCase());
				data.setNombre(this.getTxtNombre().getText().toUpperCase());
				data.setTipoId(((TipoAsientoIf) this.getCmbTipoAsiento().getSelectedItem()).getId());
				data.setOrden(Long.parseLong(this.getTxtOrden().getText()));
				data.setStatus(this.getCmbStatus().getSelectedItem().toString().substring(0, 1));
				SessionServiceLocator.getSubTipoAsientoSessionService().addSubtipoAsiento(data);
				this.clean();
				this.showSaveMode();
				SpiritAlert.createAlert("Subtipo de Asiento guardado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {	
				setSubtipoAsientoActualizadoIf((SubtipoAsientoIf) getSubtipoAsientoVector().get(getSubtipoAsientoSeleccionado()));
				getSubtipoAsientoActualizadoIf().setCodigo(getTxtCodigo().getText());
				getSubtipoAsientoActualizadoIf().setNombre(getTxtNombre().getText());
				getSubtipoAsientoActualizadoIf().setOrden(Long.parseLong(this.getTxtOrden().getText()));
				getSubtipoAsientoActualizadoIf().setTipoId(((TipoAsientoIf) getCmbTipoAsiento().getSelectedItem()).getId());
				getSubtipoAsientoActualizadoIf().setStatus(this.getCmbStatus().getSelectedItem().toString().substring(0, 1));
				getSubtipoAsientoVector().setElementAt(getSubtipoAsientoActualizadoIf(), getSubtipoAsientoSeleccionado());
				SessionServiceLocator.getSubTipoAsientoSessionService().saveSubtipoAsiento(getSubtipoAsientoActualizadoIf());
				setSubtipoAsientoActualizadoIf(null);
				this.showSaveMode();
				SpiritAlert.createAlert("Subtipo de Asiento actualizado con éxito", SpiritAlert.INFORMATION);	
			}			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}					
	}

	public void delete() {
		try {
			SubtipoAsientoIf subtipoAsientoIf = (SubtipoAsientoIf) getSubtipoAsientoVector().get(getSubtipoAsientoSeleccionado());
			SessionServiceLocator.getSubTipoAsientoSessionService().deleteSubtipoAsiento(subtipoAsientoIf.getId());
			this.showSaveMode();
			SpiritAlert.createAlert("Subtipo de Asiento eliminado con éxito", SpiritAlert.INFORMATION);
		} 
		catch (Exception e) {
			e.printStackTrace();
			this.showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}
	
	private void agregarColumnasTabla(SubtipoAsientoIf subtipoAsientoIf, Vector<String> fila){
		
		fila.add(subtipoAsientoIf.getCodigo());
		fila.add(subtipoAsientoIf.getNombre());
		
		try {
			fila.add(SessionServiceLocator.getTipoAsientoSessionService().getTipoAsiento(subtipoAsientoIf.getTipoId()).getNombre());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		fila.add(subtipoAsientoIf.getOrden().toString());
		
		if(ESTADO_ACTIVO.equals(subtipoAsientoIf.getStatus()))
			fila.add(NOMBRE_ESTADO_ACTIVO);
		else
			fila.add(NOMBRE_ESTADO_INACTIVO);
	}

	public boolean isEmpty() {

		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtNombre().getText())
				&& (this.getCmbTipoAsiento().getSelectedItem() == null)
			    && "".equals(this.getTxtOrden().getText())
				&& (this.getCmbStatus().getSelectedItem() == null)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateFields() {
		
		if ("".equals(getTxtCodigo().getText())) {
			SpiritAlert.createAlert("Debe ingresar un código para el Subtipo de Asiento !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;			
		}
		
		Collection subtipoAsientos = null;
		boolean codigoRepetido = false;
		
		try {
			subtipoAsientos = SessionServiceLocator.getSubTipoAsientoSessionService().findSubtipoAsientoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		Iterator subtipoAsientoIt = subtipoAsientos.iterator();
		
		while (subtipoAsientoIt.hasNext()) {
			SubtipoAsientoIf subtipoAsientoIf = (SubtipoAsientoIf) subtipoAsientoIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(subtipoAsientoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(subtipoAsientoIf.getCodigo())) 
					if (subtipoAsientoActualizadoIf.getId().equals(subtipoAsientoIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Subtipo de Asiento está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	

		if ("".equals(getTxtNombre().getText())) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Subtipo de Asiento !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;			
		}

		if (getCmbTipoAsiento().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de asiento para el Subtipo de Asiento !!", SpiritAlert.WARNING);
			getCmbTipoAsiento().grabFocus();
			return false;			
		}
		
		if ("".equals(getTxtOrden().getText())) {
			SpiritAlert.createAlert("Debe ingresar un orden para el Subtipo de Asiento !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;			
		}
		
		if (getCmbStatus().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el estado ACTIVO/INACTIVO para el Subtipo de Asiento !!", SpiritAlert.WARNING);
			getCmbTipoAsiento().grabFocus();
			return false;			
		}

		return true;
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getCmbStatus().setSelectedItem("");
		this.getTxtOrden().setText("");
		this.getCmbStatus().setSelectedItem("");

		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblSubtipoAsiento().getModel();
		
		for(int i= this.getTblSubtipoAsiento().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);

		//getCmbTipoAsiento().removeAllItems();
		getCmbTipoAsiento().setEnabled(true);
		cargarComboTipoAsiento();
		getTxtOrden().setEnabled(true);
		getCmbStatus().setEnabled(true);

		getCmbStatus().removeAllItems();
		getCmbStatus().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbStatus().addItem(NOMBRE_ESTADO_INACTIVO);
		
		clean();
		cargarTabla();		
		getTxtCodigo().grabFocus();
	}
	
	public void refresh() {
		cargarComboTipoAsiento();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	private void cargarComboTipoAsiento(){
		List tiposAsiento = ContabilidadFinder.findTipoAsientoByEmpresa(Parametros.getIdEmpresa());
		refreshCombo(getCmbTipoAsiento(),tiposAsiento);
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getTxtOrden().setEnabled(true);
		getCmbTipoAsiento().setEnabled(true);
		getCmbStatus().setEnabled(true);
		getTblSubtipoAsiento().grabFocus();
	}
	
	private void cargarTabla() {
		try {			
			//Collection subtipoAsiento = SessionServiceLocator.getSubtipoAsientoSessionService().findSubtipoAsientoByEmpresaId(Parametros.getIdEmpresa());
			Collection subtipoAsiento = SessionServiceLocator.getSubTipoAsientoSessionService().findSubtipoAsientoByEmpresaId(Parametros.getIdEmpresa());
			Iterator subtipoAsientoIterator = subtipoAsiento.iterator();
			
			if(!getSubtipoAsientoVector().isEmpty()) {
				getSubtipoAsientoVector().removeAllElements();
			}
			
			while (subtipoAsientoIterator.hasNext()) {
				SubtipoAsientoIf subtipoAsientoIf = (SubtipoAsientoIf) subtipoAsientoIterator.next();
				
				tableModel = (DefaultTableModel) getTblSubtipoAsiento().getModel();
				Vector<String> fila = new Vector<String>();
				getSubtipoAsientoVector().add(subtipoAsientoIf);
				
				agregarColumnasTabla(subtipoAsientoIf, fila);
				
				tableModel.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private Map buildQuery() {
		Map aMap = new HashMap();

		if (!("".equals(getTxtCodigo().getText()))) {
			aMap.put("codigo", getTxtCodigo().getText().toUpperCase());
		}
		if (!("".equals(getTxtNombre().getText()))) {
			aMap.put("nombre", getTxtNombre().getText().toUpperCase());
		}
		if (aMap.isEmpty() == true){
			String buscarTodos = "%";
			aMap.put("codigo", buscarTodos);
		}
		return aMap;
	}
	
	public Vector getSubtipoAsientoVector() {
		return this.subtipoAsientoVector;
	}
	
	public void setSubtipoAsientoVector(Vector subtipoAsientoVector) {
		this.subtipoAsientoVector = subtipoAsientoVector;
	}
	
	public int getSubtipoAsientoSeleccionado() {
		return this.subtipoAsientoSeleccionado;
	}
	
	public void setSubtipoAsientoSeleccionado(int subtipoAsientoSeleccionado) {
		this.subtipoAsientoSeleccionado = subtipoAsientoSeleccionado;
	}
	
	public SubtipoAsientoIf getSubtipoAsientoActualizadoIf() {
		return this.subtipoAsientoActualizadoIf;
	}
	
	public void setSubtipoAsientoActualizadoIf(SubtipoAsientoIf subtipoAsientoActualizadoIf) {
		this.subtipoAsientoActualizadoIf = subtipoAsientoActualizadoIf;
	}
 
	 
}
