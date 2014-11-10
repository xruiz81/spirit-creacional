package com.spirit.inventario.gui.model;

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
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.entity.TipoProductoData;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.inventario.gui.panel.JPTipoProducto;
import com.spirit.inventario.session.TipoProductoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoProductoModel extends JPTipoProducto {

	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	
	private DefaultTableModel tableModel;
	private Vector tipoProductoVector = new Vector();
	private int tipoProductoSeleccionado;
	private TipoProductoIf tipoProductoActualizadoIf;
	
	public TipoProductoModel() {
		initKeyListeners();
		setSorterTable(getTblTipoProducto());
		anchoColumnasTabla();
		getTblTipoProducto().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		getTblTipoProducto().addMouseListener(oMouseAdapterTblTipoProducto);
		getTblTipoProducto().addKeyListener(oKeyAdapterTblTipoProducto);
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoProducto().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblTipoProducto().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(270);
		anchoColumna = getTblTipoProducto().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(50);
	}
	
	MouseListener oMouseAdapterTblTipoProducto = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			 enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblTipoProducto = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoProductoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoProductoActualizadoIf = (TipoProductoIf) getTipoProductoVector().get(getTipoProductoSeleccionado());
			getTxtCodigo().setText(getTipoProductoActualizadoIf().getCodigo());
			getTxtNombre().setText(getTipoProductoActualizadoIf().getNombre());
			if (getTipoProductoActualizadoIf().getEstado().equals(ESTADO_ACTIVO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			else if (getTipoProductoActualizadoIf().getEstado().equals(ESTADO_INACTIVO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			getTxtCodigo().setEnabled(false);
			showUpdateMode();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				TipoProductoData data = new TipoProductoData();
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());
				data.setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
				SessionServiceLocator.getTipoProductoSessionService().addTipoProducto(data);
				this.showSaveMode();
				SpiritAlert.createAlert("Tipo de Producto guardado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {	
			if (validateFields()) {
				setTipoProductoActualizadoIf((TipoProductoIf) getTipoProductoVector().get(getTipoProductoSeleccionado()));
				getTipoProductoActualizadoIf().setNombre(getTxtNombre().getText());
				getTipoProductoActualizadoIf().setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
				this.clean();
				getTipoProductoVector().setElementAt(getTipoProductoActualizadoIf(), getTipoProductoSeleccionado());
				SessionServiceLocator.getTipoProductoSessionService().saveTipoProducto(getTipoProductoActualizadoIf());
				setTipoProductoActualizadoIf(null);
				this.showSaveMode();
				SpiritAlert.createAlert("Tipo de Producto actualizado con éxito", SpiritAlert.INFORMATION);
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			TipoProductoIf tipoProductoIf = (TipoProductoIf) getTipoProductoVector().get(getTipoProductoSeleccionado());
			SessionServiceLocator.getTipoProductoSessionService().deleteTipoProducto(tipoProductoIf.getId());
			this.showSaveMode();
			SpiritAlert.createAlert("Tipo de Producto eliminado con éxito", SpiritAlert.INFORMATION);
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			this.showSaveMode();
		}
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);

		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoProducto().getModel();
		
		for(int i= this.getTblTipoProducto().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getCmbEstado().setEnabled(true);
		clean();
		cargarTabla();		
		getTxtCodigo().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private void cargarTabla() {
		try {
			if (!SessionServiceLocator.getTipoProductoSessionService().findTipoProductoByEmpresaId(Parametros.getIdEmpresa()).isEmpty()) {
				Collection tipoProductos =SessionServiceLocator.getTipoProductoSessionService().findTipoProductoByEmpresaId(Parametros.getIdEmpresa()); 
				Iterator tipoProductosIterator = tipoProductos.iterator();
				
				if(!getTipoProductoVector().isEmpty()){
					getTipoProductoVector().removeAllElements();
				}
				
				while (tipoProductosIterator.hasNext()) {
					TipoProductoIf tipoProductoIf = (TipoProductoIf) tipoProductosIterator.next();
					
					tableModel = (DefaultTableModel) getTblTipoProducto().getModel();
					Vector<String> fila = new Vector<String>();
					getTipoProductoVector().add(tipoProductoIf);
					
					agregarColumnasTabla(tipoProductoIf, fila);
					
					tableModel.addRow(fila);
				}
				Utilitarios.scrollToCenter(getTblTipoProducto(), tipoProductos, 0);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(TipoProductoIf tipoProductoIf, Vector<String> fila) {	
		fila.add(tipoProductoIf.getCodigo());
		fila.add(tipoProductoIf.getNombre());
		if (tipoProductoIf.getEstado().equals(ESTADO_ACTIVO))
			fila.add(NOMBRE_ESTADO_ACTIVO);
		else if (tipoProductoIf.getEstado().equals(ESTADO_INACTIVO))
		fila.add(NOMBRE_ESTADO_INACTIVO);
	}
	
	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();

		Collection tipoProductos = null;
		boolean codigoRepetido = false;
		
		try {
			tipoProductos = SessionServiceLocator.getTipoProductoSessionService().findTipoProductoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator tipoProductosIt = tipoProductos.iterator();
		
		while (tipoProductosIt.hasNext()) {
			TipoProductoIf tipoProductoIf = (TipoProductoIf) tipoProductosIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoProductoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoProductoIf.getCodigo())) 
					if (tipoProductoActualizadoIf.getId().equals(tipoProductoIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Tipo de Producto está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para el Tipo de Producto !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo de Producto !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (getCmbEstado().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el estado para el Tipo de Producto !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public Vector getTipoProductoVector() {
		return this.tipoProductoVector;
	}
	
	public void setTipoProductoVector(Vector tipoProductoVector) {
		this.tipoProductoVector = tipoProductoVector;
	}
	
	public int getTipoProductoSeleccionado() {
		return this.tipoProductoSeleccionado;
	}
	
	public void setTipoProductoSeleccionado(int tipoProductoSeleccionado) {
		this.tipoProductoSeleccionado = tipoProductoSeleccionado;
	}
	
	public TipoProductoIf getTipoProductoActualizadoIf() {
		return this.tipoProductoActualizadoIf;
	}
	
	public void setTipoProductoActualizadoIf(TipoProductoIf tipoProductoActualizadoIf) {
		this.tipoProductoActualizadoIf = tipoProductoActualizadoIf;
	}
	 
}
