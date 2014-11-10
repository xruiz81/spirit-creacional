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
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPSubtipoOrden;
import com.spirit.general.session.TipoOrdenSessionService;
import com.spirit.general.session.TipoProveedorSessionService;
import com.spirit.medios.entity.SubtipoOrdenData;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.medios.session.SubtipoOrdenSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class SubtipoOrdenModel extends JPSubtipoOrden {

	private static final int MAX_LONGITUD_CODIGO = 2;
	private static final int MAX_LONGITUD_NOMBRE = 20;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	
	private DefaultTableModel tableModel;
	private Vector subtipoOrdenVector = new Vector();
	private int subtipoOrdenSeleccionada;
	private SubtipoOrdenIf subtipoOrdenActualizadaIf;
	
	public SubtipoOrdenModel() {
		initKeyListeners();
		setSorterTable(getTblSubtipoOrden());
		anchoColumnasTabla();
		cargarCombos();
		getTblSubtipoOrden().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		getTblSubtipoOrden().addMouseListener(oMouseAdapterTblSubtipoOrden);
		getTblSubtipoOrden().addKeyListener(oKeyAdapterTblSubtipoOrden);
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblSubtipoOrden().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblSubtipoOrden().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblSubtipoOrden().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblSubtipoOrden().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(150);
	}
	
	private void cargarCombos() {
		cargarComboTipoOrden();
		cargarComboTipoProveedor();
	}
	
	MouseListener oMouseAdapterTblSubtipoOrden = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			 enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblSubtipoOrden = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setSubtipoOrdenSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			subtipoOrdenActualizadaIf = (SubtipoOrdenIf) getSubtipoOrdenVector().get(getSubtipoOrdenSeleccionada());
			getTxtCodigo().setText(getSubtipoOrdenActualizadaIf().getCodigo());
			getTxtNombre().setText(getSubtipoOrdenActualizadaIf().getNombre());
			
			if (getSubtipoOrdenActualizadaIf().getTipoordenId() != null)
				getCmbTipoOrden().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoOrden(), getSubtipoOrdenActualizadaIf().getTipoordenId()));
			else
				getCmbTipoOrden().setSelectedItem(null);
			
			getCmbTipoOrden().validate();
			getCmbTipoOrden().repaint();
			
			if (getSubtipoOrdenActualizadaIf().getTipoproveedorId() != null)
				getCmbTipoProveedor().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoProveedor(), getSubtipoOrdenActualizadaIf().getTipoproveedorId()));
			else 
				getCmbTipoProveedor().setSelectedItem(null);
			
			getCmbTipoProveedor().validate();
			getCmbTipoProveedor().repaint();
			getTxtCodigo().setEnabled(false);
			showUpdateMode();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				SubtipoOrdenData data = new SubtipoOrdenData();
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				if (getCmbTipoOrden().getSelectedItem() != null)
					data.setTipoordenId(((TipoOrdenIf) this.getCmbTipoOrden().getSelectedItem()).getId());
				if (getCmbTipoProveedor().getSelectedItem() != null)
					data.setTipoproveedorId(((TipoProveedorIf) this.getCmbTipoProveedor().getSelectedItem()).getId());
				SessionServiceLocator.getSubtipoOrdenSessionService().addSubtipoOrden(data);
				this.showSaveMode();
				SpiritAlert.createAlert("Subtipo de Orden guardado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la informacin!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {	
			if (validateFields()) {
				setSubtipoOrdenActualizadaIf((SubtipoOrdenIf) getSubtipoOrdenVector().get(getSubtipoOrdenSeleccionada()));
				getSubtipoOrdenActualizadaIf().setNombre(getTxtNombre().getText());
				if (getCmbTipoOrden().getSelectedItem() != null)
					getSubtipoOrdenActualizadaIf().setTipoordenId(((TipoOrdenIf) this.getCmbTipoOrden().getSelectedItem()).getId());
				if (getCmbTipoProveedor().getSelectedItem() != null)
					getSubtipoOrdenActualizadaIf().setTipoproveedorId(((TipoProveedorIf) this.getCmbTipoProveedor().getSelectedItem()).getId());
				this.clean();
				getSubtipoOrdenVector().setElementAt(getSubtipoOrdenActualizadaIf(), getSubtipoOrdenSeleccionada());
				SessionServiceLocator.getSubtipoOrdenSessionService().saveSubtipoOrden(getSubtipoOrdenActualizadaIf());
				setSubtipoOrdenActualizadaIf(null);
				this.showSaveMode();				
				SpiritAlert.createAlert("Subtipo de Orden actualizado con éxito", SpiritAlert.INFORMATION);
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la informacin!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			SubtipoOrdenIf subtipoOrdenIf = (SubtipoOrdenIf) getSubtipoOrdenVector().get(getSubtipoOrdenSeleccionada());
			SessionServiceLocator.getSubtipoOrdenSessionService().deleteSubtipoOrden(subtipoOrdenIf.getId());
			this.showSaveMode();
			SpiritAlert.createAlert("Subtipo de Orden eliminado con éxito", SpiritAlert.INFORMATION);
		} 
		catch (Exception e) {
			e.printStackTrace();
			this.showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}
	
	public void refresh() {
		cargarCombos();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getCmbTipoOrden().setSelectedItem(null);
		this.getCmbTipoProveedor().setSelectedItem(null);
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblSubtipoOrden().getModel();
		for(int i= this.getTblSubtipoOrden().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getCmbTipoOrden().setEnabled(true);
		getCmbTipoProveedor().setEnabled(true);
		clean();
		cargarTabla();		
		getTxtCodigo().grabFocus();
	}

	private void cargarTabla() {
		try {
			if (!SessionServiceLocator.getSubtipoOrdenSessionService().findSubtipoOrdenByEmpresaId(Parametros.getIdEmpresa()).isEmpty()) {
				Collection subtipoOrdenes = SessionServiceLocator.getSubtipoOrdenSessionService().findSubtipoOrdenByEmpresaId(Parametros.getIdEmpresa()); 
				Iterator subtipoOrdenesIterator = subtipoOrdenes.iterator();
				
				if(!getSubtipoOrdenVector().isEmpty()){
					getSubtipoOrdenVector().removeAllElements();
				}
				
				while (subtipoOrdenesIterator.hasNext()) {
					SubtipoOrdenIf subtipoOrdenIf = (SubtipoOrdenIf) subtipoOrdenesIterator.next();
					
					tableModel = (DefaultTableModel) getTblSubtipoOrden().getModel();
					Vector<String> fila = new Vector<String>();
					getSubtipoOrdenVector().add(subtipoOrdenIf);
					
					agregarColumnasTabla(subtipoOrdenIf, fila);
					
					tableModel.addRow(fila);
				}
				Utilitarios.scrollToCenter(getTblSubtipoOrden(), subtipoOrdenes, 0);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(SubtipoOrdenIf subtipoOrdenIf, Vector<String> fila) {	
		fila.add(subtipoOrdenIf.getCodigo());
		fila.add(subtipoOrdenIf.getNombre());
		try {
			if (subtipoOrdenIf.getTipoordenId() != null) {
				TipoOrdenIf tipoOrden = SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(subtipoOrdenIf.getTipoordenId());
				fila.add(tipoOrden.getNombre());
			} else
				fila.add("");
			
			if (subtipoOrdenIf.getTipoproveedorId() != null) {
				TipoProveedorIf tipoProveedor = SessionServiceLocator.getTipoProveedorSessionService().getTipoProveedor(subtipoOrdenIf.getTipoproveedorId());
				fila.add(tipoProveedor.getNombre());
			} else
				fila.add("");
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();

		Collection subtipoOrdenes = null;
		boolean codigoRepetido = false;
		
		try {
			subtipoOrdenes = SessionServiceLocator.getSubtipoOrdenSessionService().findSubtipoOrdenByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator subtipoOrdenesIt = subtipoOrdenes.iterator();
		
		while (subtipoOrdenesIt.hasNext()) {
			SubtipoOrdenIf subtipoOrdenIf = (SubtipoOrdenIf) subtipoOrdenesIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(subtipoOrdenIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(subtipoOrdenIf.getCodigo())) 
					if (subtipoOrdenActualizadaIf.getId().equals(subtipoOrdenIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El cdigo del Subtipo de Orden está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un cdigo para el Subtipo de Orden !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Subtipo de Orden !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (getCmbTipoOrden().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de orden para el Subtipo !!", SpiritAlert.WARNING);
			getCmbTipoOrden().grabFocus();
			return false;
		}
		
		if (getCmbTipoProveedor().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de proveedor para el Subtipo !!", SpiritAlert.WARNING);
			getCmbTipoProveedor().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public Vector getSubtipoOrdenVector() {
		return this.subtipoOrdenVector;
	}
	
	public void setSubtipoOrdenVector(Vector subtipoOrdenVector) {
		this.subtipoOrdenVector = subtipoOrdenVector;
	}
	
	public int getSubtipoOrdenSeleccionada() {
		return this.subtipoOrdenSeleccionada;
	}
	
	public void setSubtipoOrdenSeleccionada(int subtipoOrdenSeleccionada) {
		this.subtipoOrdenSeleccionada = subtipoOrdenSeleccionada;
	}
	
	public SubtipoOrdenIf getSubtipoOrdenActualizadaIf() {
		return this.subtipoOrdenActualizadaIf;
	}
	
	public void setSubtipoOrdenActualizadaIf(SubtipoOrdenIf subtipoOrdenActualizadaIf) {
		this.subtipoOrdenActualizadaIf = subtipoOrdenActualizadaIf;
	}
	
	private void cargarComboTipoOrden(){
		try {
			List tipoOrdenes = (List) SessionServiceLocator.getTipoOrdenSessionService().findTipoOrdenByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(this.getCmbTipoOrden(), tipoOrdenes);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboTipoProveedor() {
		try {
			List tipoProveedores = (List) SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(this.getCmbTipoProveedor(), tipoProveedores);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	 
	 
}
