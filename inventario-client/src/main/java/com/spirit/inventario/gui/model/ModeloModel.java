package com.spirit.inventario.gui.model;

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
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.entity.ModeloData;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.gui.panel.JPModelo;
import com.spirit.inventario.session.ModeloSessionService;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class ModeloModel extends JPModelo {
	private static final long serialVersionUID = 3546080246274077753L;
	Map aMap;
	ModeloIf modelo;
	ArrayList lista;
	protected TableModel dataModel;
	private static final int MAX_LONGITUD_CODIGO = 15;
	private static final int MAX_LONGITUD_NOMBRE = 100;
	private static final String NOMBRE_TIPO_PROVEEDOR = "PROVEEDOR";
	private static final String TIPO_PROVEEDOR = NOMBRE_TIPO_PROVEEDOR.substring(0,1);
	private Vector modeloVector = new Vector();
	private DefaultTableModel tableModel;
	private int modeloSeleccionada;
	protected ModeloIf modeloIf;
	
	public ModeloModel() {
		initKeyListeners();
		setSorterTable(getTblModelo());
		getTblModelo().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		getTblModelo().addMouseListener(oMouseAdapterTblModelo);
		getTblModelo().addKeyListener(oKeyAdapterTblModelo);
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	MouseListener oMouseAdapterTblModelo = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblModelo = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setModeloSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
			modeloIf = (ModeloIf)  getModeloVector().get(getModeloSeleccionada());				
			getTxtCodigo().setText(modeloIf.getCodigo());
			getTxtNombre().setText(modeloIf.getNombre());
			getCmbMarca().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbMarca(), modeloIf.getMarcaId()));
			getCmbMarca().repaint();
			showUpdateMode();
		}
	}

	public void save() {
		if (validateFields()) {
			ModeloData data = new ModeloData();
			data.setCodigo(this.getTxtCodigo().getText());
			data.setNombre(this.getTxtNombre().getText());
			data.setMarcaId(((MarcaProductoIf) this.getCmbMarca().getSelectedItem()).getId());

			try {
				SessionServiceLocator.getModeloSessionService().addModelo(data);
				SpiritAlert.createAlert("Modelo guardado con éxito!",SpiritAlert.INFORMATION);
				SpiritCache.setObject("modelo",null);
				showSaveMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la infomación!",SpiritAlert.ERROR);
			}
		} 
	}

	public void update() {
		try {
			if (validateFields()) {
				setModeloActualizadaIf((ModeloIf) getModeloVector().get(getModeloSeleccionada()));
				getModeloActualizadaIf().setCodigo(getTxtCodigo().getText());
				getModeloActualizadaIf().setNombre(getTxtNombre().getText());
				if(getCmbMarca().getSelectedItem() != null) getModeloActualizadaIf().setMarcaId(((MarcaProductoIf) getCmbMarca().getSelectedItem()).getId());
				SessionServiceLocator.getModeloSessionService().saveModelo(getModeloActualizadaIf());
				getModeloVector().setElementAt(getModeloActualizadaIf(), getModeloSeleccionada());
				setModeloActualizadaIf(null);
				SpiritAlert.createAlert("Modelo actualizado con éxito!",SpiritAlert.INFORMATION);
				SpiritCache.setObject("modelo",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar información!",SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			ModeloIf modeloIf = (ModeloIf) getModeloVector().get(getModeloSeleccionada());
			SessionServiceLocator.getModeloSessionService().deleteModelo(modeloIf.getId());
			SpiritAlert.createAlert("Modelo eliminado con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("modelo",null);
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
		Collection modelo = null;
		boolean codigoRepetido = false;
		
		try {
			modelo = SessionServiceLocator.getModeloSessionService().findModeloByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator modeloIt = modelo.iterator();
		
		while (modeloIt.hasNext()) {
			ModeloIf modeloIf = (ModeloIf) modeloIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(modeloIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(modeloIf.getCodigo())) 
					if (getModeloActualizadaIf().getId().equals(modeloIf.getId()) == false)
						codigoRepetido = true;
		}
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del modelo está duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para el modelo !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el modelo !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}	
		if (getCmbMarca().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar una marca para el modelo !!",SpiritAlert.WARNING);
			getCmbMarca().grabFocus();
			return false;
		}
		return true;
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getCmbMarca().setSelectedItem(null);
		this.getCmbMarca().removeAllItems();
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblModelo().getModel();
		for(int i= this.getTblModelo().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showFindMode() {
		showSaveMode();
	}
	
	public void cargarCombos(){
		cargarComboMarcaProducto();
	}
	
	private void cargarComboMarcaProducto(){
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("tipo", TIPO_PROVEEDOR);
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			SpiritComboBoxModel cmbModelMarcaProducto = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getMarcaProductoSessionService().findMarcaProductoByQuery(parameterMap));
			getCmbMarca().setModel(cmbModelMarcaProducto);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		getCmbMarca().setEnabled(true);
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtCodigo().grabFocus();
		clean();
		cargarCombos();
		cargarTabla();
	}
	
	private void cargarTabla() {		
		try {
			Collection modelo = SessionServiceLocator.getModeloSessionService().findModeloByEmpresaId(Parametros.getIdEmpresa());
			Iterator modeloIterator = modelo.iterator();
			if(!getModeloVector().isEmpty())
				getModeloVector().removeAllElements();
			
			while (modeloIterator.hasNext()) {
				ModeloIf modeloIf = (ModeloIf) modeloIterator.next();
				tableModel = (DefaultTableModel) getTblModelo().getModel();
				Vector<String> fila = new Vector<String>();
				getModeloVector().add(modeloIf);
				agregarFilasTabla(modeloIf, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblModelo(), modelo, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarFilasTabla(ModeloIf modeloIf, Vector<String> fila){
		fila.add(modeloIf.getCodigo());
		fila.add(modeloIf.getNombre());
		try {
			MarcaProductoIf marcaProducto = SessionServiceLocator.getMarcaProductoSessionService().getMarcaProducto(modeloIf.getMarcaId());
			fila.add(marcaProducto.getNombre());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getCmbMarca().setEnabled(true);
		getTblModelo().grabFocus();
	}
	
	public void refresh(){
		cargarComboMarcaProducto();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public Vector getModeloVector() {
		return modeloVector;
	}

	public void setModeloVector(Vector modeloVector) {
		this.modeloVector = modeloVector;
	}

	public int getModeloSeleccionada() {
		return modeloSeleccionada;
	}

	public void setModeloSeleccionada(int modeloSeleccionada) {
		this.modeloSeleccionada = modeloSeleccionada;
	}

	public ModeloIf getModeloActualizadaIf() {
		return modeloIf;
	}

	public void setModeloActualizadaIf(ModeloIf modeloIf) {
		this.modeloIf = modeloIf;
	}
 
}
