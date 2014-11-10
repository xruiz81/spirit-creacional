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
import com.spirit.contabilidad.entity.TipoAsientoData;
import com.spirit.contabilidad.entity.TipoAsientoIf;
import com.spirit.contabilidad.gui.panel.JPTipoAsiento;
import com.spirit.contabilidad.session.TipoAsientoSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoAsientoModel extends JPTipoAsiento {

	private static final long serialVersionUID = 4879765653954132500L;	
	public static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	public static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	public static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	public static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 40;
	private static final int MAX_LONGITUD_ORDEN = 2;

	private Vector tipoAsientoVector = new Vector();
	private int tipoAsientoSeleccionado;
	private TipoAsientoIf tipoAsientoActualizadoIf;
	private DefaultTableModel tableModel;
	
	public TipoAsientoModel() {
		initKeyListeners();
		setSorterTable(getTblTipoAsiento());
		getTblTipoAsiento().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		setColumnWidthTable();
		this.getTblTipoAsiento().addMouseListener(oMouseAdapterTblTipoAsiento);
		this.getTblTipoAsiento().addKeyListener(oKeyAdapterTblTipoAsiento);
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtOrden().addKeyListener(new TextChecker(MAX_LONGITUD_ORDEN));
		getTxtOrden().addKeyListener (new NumberTextField());
	}
	
	private void setColumnWidthTable() {

		TableColumn anchoColumna = getTblTipoAsiento().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(40);
		
		anchoColumna = getTblTipoAsiento().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(300);
		    
		anchoColumna = getTblTipoAsiento().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(40);
		
		anchoColumna = getTblTipoAsiento().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(40);
		
	}

	MouseListener oMouseAdapterTblTipoAsiento = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {	
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblTipoAsiento = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoAsientoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoAsientoActualizadoIf = (TipoAsientoIf)  getTipoAsientoVector().get(getTipoAsientoSeleccionado());
			getTxtCodigo().setText(getTipoAsientoActualizadoIf().getCodigo());
			getTxtNombre().setText(getTipoAsientoActualizadoIf().getNombre());
			getTxtOrden().setText(getTipoAsientoActualizadoIf().getOrden().toString());
			if (ESTADO_ACTIVO.equals(getTipoAsientoActualizadoIf().getStatus()))
				getCmbStatus().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			else
				getCmbStatus().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			
			showUpdateMode();
		}
	}

	private ArrayList getModel(ArrayList listaTipoAsiento) {
		ArrayList data = new ArrayList();
		Iterator it = listaTipoAsiento.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			TipoAsientoIf bean = (TipoAsientoIf) it.next();

			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());

			data.add(fila);
		}
		return data;
	}

	public void save() {
		try {
			if (validateFields()) {
				TipoAsientoData data = new TipoAsientoData();
				data.setCodigo(this.getTxtCodigo().getText().toUpperCase());
				data.setNombre(this.getTxtNombre().getText().toUpperCase());
				data.setEmpresaId(Parametros.getIdEmpresa());
				data.setOrden(Long.parseLong(this.getTxtOrden().getText()));
				data.setStatus(this.getCmbStatus().getSelectedItem().toString().substring(0,1));
				SessionServiceLocator.getTipoAsientoSessionService().addTipoAsiento(data);
				this.clean();
				this.showSaveMode();
				SpiritAlert.createAlert("Tipo de asiento guardado con éxito", SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoAsiento" + Parametros.getIdEmpresa(),null);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}

	}

	public void update() {
		try {
			if (validateFields()) {
				setTipoAsientoActualizadoIf((TipoAsientoIf) getTipoAsientoVector().get(getTipoAsientoSeleccionado()));
				getTipoAsientoActualizadoIf().setCodigo(getTxtCodigo().getText());
				getTipoAsientoActualizadoIf().setNombre(getTxtNombre().getText());
				getTipoAsientoActualizadoIf().setEmpresaId(Parametros.getIdEmpresa());
				getTipoAsientoActualizadoIf().setOrden(Long.parseLong(getTxtOrden().getText()));
				getTipoAsientoActualizadoIf().setStatus(getCmbStatus().getSelectedItem().toString().substring(0,1));
				getTipoAsientoVector().setElementAt(getTipoAsientoActualizadoIf(), getTipoAsientoSeleccionado());
				SessionServiceLocator.getTipoAsientoSessionService().saveTipoAsiento(getTipoAsientoActualizadoIf());
				setTipoAsientoActualizadoIf(null);		
				this.showSaveMode();
				SpiritAlert.createAlert("Tipo de asiento actualizado con éxito", SpiritAlert.INFORMATION);	
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}		
	}

	public void delete() {
		try {
			TipoAsientoIf tipoAsientoIf = (TipoAsientoIf) getTipoAsientoVector().get(getTipoAsientoSeleccionado());
			SessionServiceLocator.getTipoAsientoSessionService().deleteTipoAsiento(tipoAsientoIf.getId());
			this.showSaveMode();
			SpiritAlert.createAlert("Tipo de asiento eliminado con éxito", SpiritAlert.INFORMATION);
		} 
		catch (Exception e) {
			e.printStackTrace();
			this.showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}

	public boolean isEmpty() {

		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtNombre().getText())
				&& "".equals(this.getTxtOrden().getText())
				&& this.getCmbStatus().getSelectedItem() == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateFields() {
		if ("".equals(getTxtCodigo().getText())) {
			SpiritAlert.createAlert("Debe ingresar un código para el Tipo de Asiento !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;			
		}
		
		Collection tipoAsientos = null;
		boolean codigoRepetido = false;
		
		try {
			tipoAsientos = SessionServiceLocator.getTipoAsientoSessionService().findTipoAsientoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		Iterator tipoAsientoIt = tipoAsientos.iterator();
		
		while (tipoAsientoIt.hasNext()) {
			TipoAsientoIf tipoAsientoIf = (TipoAsientoIf) tipoAsientoIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoAsientoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoAsientoIf.getCodigo())) 
					if (tipoAsientoActualizadoIf.getId().equals(tipoAsientoIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Tipo de Asiento está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	
		
		if ("".equals(getTxtNombre().getText())) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo de Asiento !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;			
		}

		if ("".equals(getTxtOrden().getText())) {
			SpiritAlert.createAlert("Debe ingresar un orden para el Tipo de Asiento !!", SpiritAlert.WARNING);
			getTxtOrden().grabFocus();
			return false;			
		}
		
		if (getCmbStatus().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el estado ACTIVO/INACTIVO para el Tipo de Asiento !!", SpiritAlert.WARNING);
			getCmbStatus().grabFocus();
			return false;			
		}
	
		return true;
	}
	
	private void cargarTabla() {
		try {			
			Collection tipoAsiento = SessionServiceLocator.getTipoAsientoSessionService().findTipoAsientoByEmpresaId(Parametros.getIdEmpresa()); 
			Iterator tipoAsientoIterator = tipoAsiento.iterator();
			
			if(!getTipoAsientoVector().isEmpty()){
				getTipoAsientoVector().removeAllElements();
			}
			
			while (tipoAsientoIterator.hasNext()) {
				TipoAsientoIf tipoAsientoIf = (TipoAsientoIf) tipoAsientoIterator.next();
				
				tableModel = (DefaultTableModel) getTblTipoAsiento().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoAsientoVector().add(tipoAsientoIf);
				
				agregarColumnasTabla(tipoAsientoIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoAsiento(), tipoAsiento, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void clean() {

		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getTxtOrden().setText("");
				
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoAsiento().getModel();
		
		for(int i= this.getTblTipoAsiento().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void cleanCombos(){
		this.getCmbStatus().removeAllItems();
		}

	public void showFindMode() {
		showSaveMode();
	}

	public void showSaveMode() {		
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtOrden().setEnabled(true);
		getCmbStatus().setEnabled(true); 

		clean();
		loadCombos();
		cargarTabla();
		
		getTxtCodigo().grabFocus();
	}
	
	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getTxtOrden().setEnabled(true);
		getCmbStatus().setEnabled(true);
		getTblTipoAsiento().grabFocus();
	}	
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private void loadCombos() {
		if (getCmbStatus().getItemCount()>0)
			getCmbStatus().removeAllItems();
		
		getCmbStatus().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbStatus().addItem(NOMBRE_ESTADO_INACTIVO);
	}

	public Vector getTipoAsientoVector() {
		return this.tipoAsientoVector;
	}
	
	public void setTipoAsientoVector(Vector tipoAsientoVector) {
		this.tipoAsientoVector = tipoAsientoVector;
	}
	
	public int getTipoAsientoSeleccionado() {
		return this.tipoAsientoSeleccionado;
	}
	
	public void setTipoAsientoSeleccionado(int tipoAsientoSeleccionado) {
		this.tipoAsientoSeleccionado = tipoAsientoSeleccionado;
	}
	
	public TipoAsientoIf getTipoAsientoActualizadoIf() {
		return this.tipoAsientoActualizadoIf;
	}
	
	public void setTipoAsientoActualizadoIf(TipoAsientoIf tipoAsientoActualizadoIf) {
		this.tipoAsientoActualizadoIf = tipoAsientoActualizadoIf;
	}
	
	private void agregarColumnasTabla(TipoAsientoIf tipoAsientoIf, Vector<String> fila){
		
		fila.add(tipoAsientoIf.getCodigo());
		fila.add(tipoAsientoIf.getNombre());
		fila.add(tipoAsientoIf.getOrden().toString());
		if (ESTADO_ACTIVO.equals(tipoAsientoIf.getStatus()))
			fila.add(NOMBRE_ESTADO_ACTIVO);
		else
			fila.add(NOMBRE_ESTADO_INACTIVO);		
	}

	 
}
