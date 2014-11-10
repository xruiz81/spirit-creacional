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
import javax.swing.table.TableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoProveedorData;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPTipoProveedor;
import com.spirit.general.session.TipoProveedorSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoProveedorModel extends JPTipoProveedor {

	private static final long serialVersionUID = -2113447996325855818L;

	protected TableModel dataModel;
	ArrayList lista;
	private Vector tipoProveedorVector = new Vector();
	private int tipoProveedorSeleccionado;
	private TipoProveedorIf tipoProveedor;
	private DefaultTableModel tableModel;
	
	private static final int MAX_LONGITUD_CODIGO = 2;
	private static final int MAX_LONGITUD_NOMBRE = 40;
	private static final String NOMBRE_TIPO_PROVEEDOR_MEDIOS = "MEDIOS";
	private static final String TIPO_PROVEEDOR_MEDIOS = NOMBRE_TIPO_PROVEEDOR_MEDIOS.substring(0,1);
	private static final String NOMBRE_TIPO_PROVEEDOR_PRODUCCION = "PRODUCCION";
	private static final String TIPO_PROVEEDOR_PRODUCCION = NOMBRE_TIPO_PROVEEDOR_PRODUCCION.substring(0,1);
	private static final String NOMBRE_TIPO_PROVEEDOR_GASTO = "GASTO";
	private static final String TIPO_PROVEEDOR_GASTO = NOMBRE_TIPO_PROVEEDOR_GASTO.substring(0,1);
	
	public TipoProveedorModel() {
		initKeyListeners();
		setSorterTable(getTblTipoProveedor());
		anchoColumnasTabla();
		loadCombos();
		getTblTipoProveedor().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.showSaveMode();
		getTblTipoProveedor().addMouseListener(oMouseAdapterTblTipoProveedor);
		getTblTipoProveedor().addKeyListener(oKeyAdapterTblTipoProveedor);
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoProveedor().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblTipoProveedor().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(230);
		anchoColumna = getTblTipoProveedor().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(230);
	}
	
	MouseListener oMouseAdapterTblTipoProveedor = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	 KeyListener oKeyAdapterTblTipoProveedor = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoProveedorSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoProveedor = (TipoProveedorIf)  getTipoProveedorVector().get(getTipoProveedorSeleccionado());
			getTxtCodigo().setText(getTipoProveedorActualizadoIf().getCodigo());
			getTxtNombre().setText(getTipoProveedorActualizadoIf().getNombre());
			if (getTipoProveedorActualizadoIf().getTipo().equals(TIPO_PROVEEDOR_MEDIOS))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_PROVEEDOR_MEDIOS);
			if (getTipoProveedorActualizadoIf().getTipo().equals(TIPO_PROVEEDOR_PRODUCCION))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_PROVEEDOR_PRODUCCION);
			if (getTipoProveedorActualizadoIf().getTipo().equals(TIPO_PROVEEDOR_GASTO))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_PROVEEDOR_GASTO);
			
			showUpdateMode();
		}
	}
		
	private void loadCombos() {
		getCmbTipo().addItem(NOMBRE_TIPO_PROVEEDOR_MEDIOS);
		getCmbTipo().addItem(NOMBRE_TIPO_PROVEEDOR_PRODUCCION);
		getCmbTipo().addItem(NOMBRE_TIPO_PROVEEDOR_GASTO);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void cargarTabla() {
		try {			
			Collection tipoProveedor = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByEmpresaId(Parametros.getIdEmpresa()); 
			Iterator tipoProveedorIterator = tipoProveedor.iterator();
			
			if(!getTipoProveedorVector().isEmpty()){
				getTipoProveedorVector().removeAllElements();
			}
			
			while (tipoProveedorIterator.hasNext()) {
				TipoProveedorIf tipoProveedorIf = (TipoProveedorIf) tipoProveedorIterator.next();
				
				tableModel = (DefaultTableModel) getTblTipoProveedor().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoProveedorVector().add(tipoProveedorIf);
				
				agregarColumnasTabla(tipoProveedorIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoProveedor(), tipoProveedor, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(TipoProveedorIf tipoProveedorIf, Vector<String> fila){	
		fila.add(tipoProveedorIf.getCodigo());
		fila.add(tipoProveedorIf.getNombre());
		if (tipoProveedorIf.getTipo().equals(TIPO_PROVEEDOR_MEDIOS))
			fila.add(NOMBRE_TIPO_PROVEEDOR_MEDIOS);
		if (tipoProveedorIf.getTipo().equals(TIPO_PROVEEDOR_PRODUCCION))
			fila.add(NOMBRE_TIPO_PROVEEDOR_PRODUCCION);
		if (tipoProveedorIf.getTipo().equals(TIPO_PROVEEDOR_GASTO))
			fila.add(NOMBRE_TIPO_PROVEEDOR_GASTO);
	}
	
	public boolean validateFields() {

		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		
		Collection tipoProveedores = null;
		boolean codigoRepetido = false;
		
		try {
			tipoProveedores = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		Iterator tipoProveedorIt = tipoProveedores.iterator();
		
		while (tipoProveedorIt.hasNext()) {
			TipoProveedorIf tipoProveedorIf = (TipoProveedorIf) tipoProveedorIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoProveedorIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoProveedorIf.getCodigo())) 
					if (tipoProveedor.getId().equals(tipoProveedorIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El cdigo del Tipo de Proveedor está duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un cdigo para el Tipo de Proveedor !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo de Proveedor !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (getCmbTipo().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la clasificacin para el Tipo de Proveedor !!",SpiritAlert.WARNING);
			getCmbTipo().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public void save() {
		if (validateFields()) {
			TipoProveedorData data = new TipoProveedorData();

			data.setCodigo(this.getTxtCodigo().getText());
			data.setNombre(this.getTxtNombre().getText());
			data.setEmpresaId(Parametros.getIdEmpresa());
			
			if(NOMBRE_TIPO_PROVEEDOR_MEDIOS.equals(getCmbTipo().getSelectedItem()))
				data.setTipo(TIPO_PROVEEDOR_MEDIOS);
			else if(NOMBRE_TIPO_PROVEEDOR_PRODUCCION.equals(getCmbTipo().getSelectedItem()))
				data.setTipo(TIPO_PROVEEDOR_PRODUCCION);
			else if(NOMBRE_TIPO_PROVEEDOR_GASTO.equals(getCmbTipo().getSelectedItem()))
				data.setTipo(TIPO_PROVEEDOR_GASTO);
			
			try {
				SessionServiceLocator.getTipoProveedorSessionService().addTipoProveedor(data);
				SpiritAlert.createAlert("Tipo de Proveedor guardado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoProveedor",null);
				showSaveMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la informacin!",SpiritAlert.ERROR);
			}
		} 
	}
	
	public void update() {
		try {
			if (validateFields()) {
				setTipoProveedorActualizadoIf((TipoProveedorIf) getTipoProveedorVector().get(getTipoProveedorSeleccionado()));
				
				getTipoProveedorActualizadoIf().setCodigo(getTxtCodigo().getText());
				getTipoProveedorActualizadoIf().setNombre(getTxtNombre().getText());
				
				if(NOMBRE_TIPO_PROVEEDOR_MEDIOS.equals(getCmbTipo().getSelectedItem()))
					getTipoProveedorActualizadoIf().setTipo(TIPO_PROVEEDOR_MEDIOS);
				else if(NOMBRE_TIPO_PROVEEDOR_PRODUCCION.equals(getCmbTipo().getSelectedItem()))
					getTipoProveedorActualizadoIf().setTipo(TIPO_PROVEEDOR_PRODUCCION);
				else if(NOMBRE_TIPO_PROVEEDOR_GASTO.equals(getCmbTipo().getSelectedItem()))
					getTipoProveedorActualizadoIf().setTipo(TIPO_PROVEEDOR_GASTO);

				getTipoProveedorActualizadoIf().setEmpresaId(Parametros.getIdEmpresa());
				
				SessionServiceLocator.getTipoProveedorSessionService().saveTipoProveedor(getTipoProveedorActualizadoIf());
				getTipoProveedorVector().setElementAt(getTipoProveedorActualizadoIf(), getTipoProveedorSeleccionado());
				setTipoProveedorActualizadoIf(null);
				
				SpiritAlert.createAlert("Tipo de Proveedor actualizado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoProveedor",null);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la informacin!",SpiritAlert.ERROR);
		}	
	}

	public void delete() {
		try {
			TipoProveedorIf tipoProveedorIf = (TipoProveedorIf) getTipoProveedorVector().get(getTipoProveedorSeleccionado());
			SessionServiceLocator.getTipoProveedorSessionService().deleteTipoProveedor(tipoProveedorIf.getId());
			SpiritAlert.createAlert("Tipo de Proveedor eliminado con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("tipoProveedor",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			this.showSaveMode();
		}
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtNombre().getText())
				&& getCmbTipo().getSelectedItem()==null) {
			return true;
		} else {
			return false;
		}
	}

	public void clean() {
		this.getCmbTipo().setSelectedItem(null);
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		// Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoProveedor().getModel();
		
		for(int i= this.getTblTipoProveedor().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void showFindMode() {
		showSaveMode();
	}
	
	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getTblTipoProveedor().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public Vector getTipoProveedorVector() {
		return this.tipoProveedorVector;
	}
	
	public void setTipoProveedorVector(Vector tipoProveedorVector) {
		this.tipoProveedorVector = tipoProveedorVector;
	}
	
	public int getTipoProveedorSeleccionado() {
		return this.tipoProveedorSeleccionado;
	}
	
	public void setTipoProveedorSeleccionado(int tipoProveedorSeleccionado) {
		this.tipoProveedorSeleccionado = tipoProveedorSeleccionado;
	}
	
	public TipoProveedorIf getTipoProveedorActualizadoIf() {
		return this.tipoProveedor;
	}
	
	public void setTipoProveedorActualizadoIf(TipoProveedorIf tipoProveedorActualizadoIf) {
		this.tipoProveedor = tipoProveedorActualizadoIf;
	}
 
}
