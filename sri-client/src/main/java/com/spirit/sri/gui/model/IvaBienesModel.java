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
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.sri.entity.SriIvaBienData;
import com.spirit.sri.entity.SriIvaBienIf;
import com.spirit.sri.gui.panel.JPIvaBienes;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class IvaBienesModel extends JPIvaBienes {
	
	private static final long serialVersionUID = -6433512319864391385L;
	
	private final int MAX_LONGITUD_CODIGO = 6;
	private final int MAX_LONGITUD_PORCENTAJE = 10;
	
	private int filaSeleccionada = -1;
	private ArrayList<SriIvaBienIf> ivaBienVector = new ArrayList<SriIvaBienIf>();
	private SriIvaBienIf ivaBienIf;
	
	public IvaBienesModel(){
		initKeyListeners();
		iniciarComponentes();
		showSaveMode();
		new HotKeyComponent(this);
		setSorterTable(getTblIvaBienes());
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtPorcentaje().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE));
		
		getTblIvaBienes().addMouseListener(oMouseAdapterTblIvaBienes);
		getTblIvaBienes().addKeyListener(oKeyAdapterTblIvaBienes);
	}
	
	private void iniciarComponentes() {
		getTblIvaBienes().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblIvaBienes().getColumnModel().getColumn(0);
		anchoColumna.setMinWidth(150);
		anchoColumna = getTblIvaBienes().getColumnModel().getColumn(1);
		anchoColumna.setMinWidth(150);
	}
	
	MouseListener oMouseAdapterTblIvaBienes = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblIvaBienes = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (getTblIvaBienes().getSelectedRow() != -1) {
			int selectedRow = getTblIvaBienes().getSelectedRow();
			filaSeleccionada =  getTblIvaBienes().convertRowIndexToModel(selectedRow); 
			ivaBienIf = ivaBienVector.get(filaSeleccionada);
			getTxtCodigo().setText(ivaBienIf.getCodigo().toString());
			getTxtPorcentaje().setText(ivaBienIf.getDescripcionPorcentaje());
			showUpdateMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				SriIvaBienData data = new SriIvaBienData();
				
				data.setCodigo(Integer.valueOf(getTxtCodigo().getText()));
				data.setDescripcionPorcentaje(getTxtPorcentaje().getText());

				SessionServiceLocator.getSriIvaBienSessionService().addSriIvaBien(data);
				SpiritAlert.createAlert("IVA Bien guardado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la infomación!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			SriIvaBienIf ivaBien = ivaBienVector.get(filaSeleccionada);
			SessionServiceLocator.getSriIvaBienSessionService().deleteSriIvaBien(ivaBien.getId());
			SpiritAlert.createAlert("IVA Bien eliminado con \u00e9xito", SpiritAlert.INFORMATION);
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
				ivaBienIf.setCodigo(Integer.valueOf(getTxtCodigo().getText()));
				ivaBienIf.setDescripcionPorcentaje(getTxtPorcentaje().getText());
				SessionServiceLocator.getSriIvaBienSessionService().saveSriIvaBien(ivaBienIf);
				SpiritAlert.createAlert("IVA Bien actualizado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!",	SpiritAlert.ERROR);
		}		
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtPorcentaje().setText("");
		
		ivaBienVector = null;
		ivaBienVector = new ArrayList<SriIvaBienIf>();
		
		limpiarTabla(getTblIvaBienes());
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {			
			DefaultTableModel tableModel = (DefaultTableModel) getTblIvaBienes().getModel();
			
			Collection<SriIvaBienIf> ivaBienesExistentes = 
				SessionServiceLocator.getSriIvaBienSessionService().getSriIvaBienList(); 
			
			for (SriIvaBienIf ivaBienIf : ivaBienesExistentes ){
				Vector<String> fila = new Vector<String>();
				ivaBienVector.add(ivaBienIf);
				agregarColumnasTabla(ivaBienIf, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblIvaBienes(), ivaBienesExistentes, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(SriIvaBienIf ivaBienIf, Vector<String> fila){
		fila.add(ivaBienIf.getCodigo().toString());
		fila.add(ivaBienIf.getDescripcionPorcentaje());		
	}

	public boolean validateFields() {
		String strCodigo = getTxtCodigo().getText();
		String strPorcentaje = getTxtPorcentaje().getText();
		if (strCodigo == null || "".equals(strCodigo)) {
			SpiritAlert.createAlert("Debe ingresar un C\u00f3digo!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if (strPorcentaje == null || "".equals(strPorcentaje) ){
			SpiritAlert.createAlert("Debe ingresar un Porcentaje!", SpiritAlert.WARNING);
			getTxtPorcentaje().grabFocus();
			return false;
		}
		
		Collection<SriIvaBienIf> ivaBienExistentes = null;
		boolean repetido = false;
		
		try {
			ivaBienExistentes = SessionServiceLocator.getSriIvaBienSessionService().getSriIvaBienList();
		
			for (SriIvaBienIf ivaBien : ivaBienExistentes){
				if (this.getMode() == SpiritMode.SAVE_MODE){
					if ((getTxtCodigo().getText().equals(ivaBien.getCodigo().toString())) && (getTxtPorcentaje().getText().equals(ivaBien.getDescripcionPorcentaje()))){				
						repetido=true;
						break;
					}				
				}

				if (this.getMode() == SpiritMode.UPDATE_MODE){
					if ((getTxtCodigo().getText().equals(ivaBien.getCodigo().toString())) && (getTxtPorcentaje().getText().equals(ivaBien.getDescripcionPorcentaje()))){ 
						if (ivaBienIf.getId().equals(ivaBien.getId()) == false){
							repetido = true;
							break;
						}
					}
				}
			}

			if (repetido) {
				SpiritAlert.createAlert("El registro ya existe !!", SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				getTxtCodigo().setSelectionStart(0);
				getTxtCodigo().setSelectionStart(getTxtCodigo().getText().length());
				return false;
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			return false;
		}
		
		return true;
	}
	
}
