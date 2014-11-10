package com.spirit.sri.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.sri.entity.SriIdentifTransaccionData;
import com.spirit.sri.entity.SriIdentifTransaccionIf;
import com.spirit.sri.entity.SriTipoTransaccionIf;
import com.spirit.sri.gui.panel.JPIdentificacionTransacciones;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class IdentificacionTransaccionesModel extends JPIdentificacionTransacciones{

	private static final long serialVersionUID = 2088674543969027666L;

	private final int MAX_LONGITUD_CODIGO = 2;
	
	private int filaSeleccionada = -1;
	private ArrayList<SriIdentifTransaccionIf> identificacionTransaccionVector = new ArrayList<SriIdentifTransaccionIf>();
	private SriIdentifTransaccionIf identificacionTransaccionIf;
	
	public IdentificacionTransaccionesModel(){
		initKeyListeners();
		iniciarComponentes();
		cargarCombos();
		showSaveMode();
		new HotKeyComponent(this);
		setSorterTable(getTblIdentificacionTransaccion());
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		
		getTblIdentificacionTransaccion().addMouseListener(oMouseAdapterTblIdentificacionTransaccion);
		getTblIdentificacionTransaccion().addKeyListener(oKeyAdapterTblIdentificacionTransaccion);
	}
	
	private void iniciarComponentes() {
		getTblIdentificacionTransaccion().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblIdentificacionTransaccion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblIdentificacionTransaccion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblIdentificacionTransaccion().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(300);
	}
	
	private void cargarCombos() {
		cargarComboTipoTransaccion();
		cargarComboTipoIdentificacion();
	}
	
	private void cargarComboTipoTransaccion(){
		try {
			List tipoTransacciones = (List) SessionServiceLocator.getSriTipoTransaccionSessionService().getSriTipoTransaccionList();
			refreshCombo(this.getCmbTipoTransaccion(), tipoTransacciones);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboTipoIdentificacion(){
		try {
			List tipoIdentificaciones = (List) SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacionList();
			refreshCombo(this.getCmbTipoIdentificacion(), tipoIdentificaciones);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	MouseListener oMouseAdapterTblIdentificacionTransaccion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblIdentificacionTransaccion = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (getTblIdentificacionTransaccion().getSelectedRow() != -1) {
			int selectedRow = getTblIdentificacionTransaccion().getSelectedRow();
			filaSeleccionada =  getTblIdentificacionTransaccion().convertRowIndexToModel(selectedRow); 
			identificacionTransaccionIf = identificacionTransaccionVector.get(filaSeleccionada);
			getTxtCodigo().setText(identificacionTransaccionIf.getCodigo());
			getCmbTipoTransaccion().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoTransaccion(), identificacionTransaccionIf.getIdTipoTransaccion()));
			getCmbTipoTransaccion().repaint();
			getCmbTipoIdentificacion().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoIdentificacion(), identificacionTransaccionIf.getIdTipoIdentificacion()));
			getCmbTipoIdentificacion().repaint();
			showUpdateMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				SriIdentifTransaccionData data = new SriIdentifTransaccionData();
				
				data.setCodigo(getTxtCodigo().getText());
				SriTipoTransaccionIf tipoTransaccion = (SriTipoTransaccionIf) getCmbTipoTransaccion().getSelectedItem();
				data.setIdTipoTransaccion(tipoTransaccion.getId());
				TipoIdentificacionIf tipoIdentificacion = (TipoIdentificacionIf) getCmbTipoIdentificacion().getSelectedItem();
				data.setIdTipoIdentificacion(tipoIdentificacion.getId());
				
				SessionServiceLocator.getSriIdentifTransaccionSessionService().addSriIdentifTransaccion(data);
				SpiritAlert.createAlert("Identificación de Transacción guardado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la infomación!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			SriIdentifTransaccionIf identificacionTransaccion = identificacionTransaccionVector.get(filaSeleccionada);
			SessionServiceLocator.getSriIdentifTransaccionSessionService().deleteSriIdentifTransaccion(identificacionTransaccion.getId());
			SpiritAlert.createAlert("Identificación de Transacción eliminado con \u00e9xito", SpiritAlert.INFORMATION);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void update() {
		try {	
			if (validateFields()) {
				identificacionTransaccionIf.setCodigo(getTxtCodigo().getText());
				SriTipoTransaccionIf tipoTransaccion = (SriTipoTransaccionIf) getCmbTipoTransaccion().getSelectedItem();
				identificacionTransaccionIf.setIdTipoTransaccion(tipoTransaccion.getId());
				TipoIdentificacionIf tipoIdentificacion = (TipoIdentificacionIf) getCmbTipoIdentificacion().getSelectedItem();
				identificacionTransaccionIf.setIdTipoIdentificacion(tipoIdentificacion.getId());
				SessionServiceLocator.getSriIdentifTransaccionSessionService().saveSriIdentifTransaccion(identificacionTransaccionIf);
				SpiritAlert.createAlert("Identificación de Transacción actualizado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!",	SpiritAlert.ERROR);
		}		
	}

	public void clean() {
		this.getTxtCodigo().setText("");
				
		identificacionTransaccionVector = null;
		identificacionTransaccionVector = new ArrayList<SriIdentifTransaccionIf>();
		
		//Vacio la tabla
		limpiarTabla(getTblIdentificacionTransaccion());
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {			
			DefaultTableModel tableModel = (DefaultTableModel) getTblIdentificacionTransaccion().getModel();
			
			Collection<SriIdentifTransaccionIf> identificacionTransaccionesExistentes = 
				SessionServiceLocator.getSriIdentifTransaccionSessionService().getSriIdentifTransaccionList(); 
			
			for (SriIdentifTransaccionIf identificacionTransaccionIf : identificacionTransaccionesExistentes){
				Vector<String> fila = new Vector<String>();
				identificacionTransaccionVector.add(identificacionTransaccionIf);
				agregarColumnasTabla(identificacionTransaccionIf, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblIdentificacionTransaccion(), identificacionTransaccionesExistentes, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(SriIdentifTransaccionIf identificacionTransaccionIf, Vector<String> fila){
		try {
			fila.add(identificacionTransaccionIf.getCodigo());
			SriTipoTransaccionIf tipoTransaccion = 
				SessionServiceLocator.getSriTipoTransaccionSessionService().getSriTipoTransaccion(identificacionTransaccionIf.getIdTipoTransaccion());
			fila.add(tipoTransaccion.getNombre());
			TipoIdentificacionIf tipoIdentificacion = 
				SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacion(identificacionTransaccionIf.getIdTipoIdentificacion());
			fila.add(tipoIdentificacion.getNombre());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		String strCodigo = getTxtCodigo().getText();
		if (strCodigo == null || "".equals(strCodigo)) {
			SpiritAlert.createAlert("Debe ingresar un C\u00f3digo para Identificación de Transacción !", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}		
		if(getCmbTipoTransaccion().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Tipo de Transacción !", SpiritAlert.WARNING);
			getCmbTipoTransaccion().grabFocus();
			return false;
		}
		if(getCmbTipoIdentificacion().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Tipo de Identificación !", SpiritAlert.WARNING);
			getCmbTipoIdentificacion().grabFocus();
			return false;
		}
		
		boolean codigoRepetido = false;
				
		try {
			Collection<SriIdentifTransaccionIf> identificacionTransaccionesExistentes = 
				SessionServiceLocator.getSriIdentifTransaccionSessionService().getSriIdentifTransaccionList();
		
		
			for (SriIdentifTransaccionIf identificacionTransaccion : identificacionTransaccionesExistentes){
				
				if (this.getMode() == SpiritMode.SAVE_MODE){
					if (getTxtCodigo().getText().equals(identificacionTransaccion.getCodigo())){				
						codigoRepetido=true;
						break;
					}
				}
				
				if (this.getMode() == SpiritMode.UPDATE_MODE){
					if (getTxtCodigo().getText().equals(identificacionTransaccion.getCodigo())){
						if (identificacionTransaccionIf.getId().equals(identificacionTransaccion.getId()) == false){
							codigoRepetido = true;
							break;
						}
					}
				}
			}
			
			if (codigoRepetido) {
				SpiritAlert.createAlert("El c\u00f3digo est\u00e1 duplicado !!", SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				getTxtCodigo().setSelectionStart(0);
				getTxtCodigo().setSelectionStart(getTxtCodigo().getText().length());
				return false;
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al consultar las Identificaciones de Transacciones", SpiritAlert.ERROR);
			return false;
		}
		
		return true;
	}
	
	public void refresh() {
		cargarCombos();
	}
	
}
