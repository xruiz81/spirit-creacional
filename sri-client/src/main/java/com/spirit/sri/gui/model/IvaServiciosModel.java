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
import com.spirit.sri.entity.SriIvaServicioData;
import com.spirit.sri.entity.SriIvaServicioIf;
import com.spirit.sri.gui.panel.JPIvaServicios;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class IvaServiciosModel extends JPIvaServicios {
	
	private static final long serialVersionUID = 3975005365465646902L;
	
	private final int MAX_LONGITUD_CODIGO = 6;
	private final int MAX_LONGITUD_PORCENTAJE = 10;
	
	private int filaSeleccionada = -1;
	private ArrayList<SriIvaServicioIf> ivaServicioVector = new ArrayList<SriIvaServicioIf>();
	private SriIvaServicioIf ivaServicioIf;
	
	public IvaServiciosModel(){
		initKeyListeners();
		iniciarComponentes();
		showSaveMode();
		new HotKeyComponent(this);
		setSorterTable(getTblIvaServicios());
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtPorcentaje().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE));
		
		getTblIvaServicios().addMouseListener(oMouseAdapterTblIvaServicios);
		getTblIvaServicios().addKeyListener(oKeyAdapterTblIvaServicios);
	}
	
	private void iniciarComponentes() {
		getTblIvaServicios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblIvaServicios().getColumnModel().getColumn(0);
		anchoColumna.setMinWidth(150);
		anchoColumna = getTblIvaServicios().getColumnModel().getColumn(1);
		anchoColumna.setMinWidth(150);
	}
	
	MouseListener oMouseAdapterTblIvaServicios = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblIvaServicios = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (getTblIvaServicios().getSelectedRow() != -1) {
			int selectedRow = getTblIvaServicios().getSelectedRow();
			filaSeleccionada =  getTblIvaServicios().convertRowIndexToModel(selectedRow); 
			ivaServicioIf = ivaServicioVector.get(filaSeleccionada);
			getTxtCodigo().setText(ivaServicioIf.getCodigo().toString());
			getTxtPorcentaje().setText(ivaServicioIf.getDescripcionPorcentaje());
			showUpdateMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				SriIvaServicioData data = new SriIvaServicioData();
				
				data.setCodigo(Integer.valueOf(getTxtCodigo().getText()));
				data.setDescripcionPorcentaje(getTxtPorcentaje().getText());

				SessionServiceLocator.getSriIvaServicioSessionService().addSriIvaServicio(data);
				SpiritAlert.createAlert("IVA Servicio guardado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la infomación!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			SriIvaServicioIf ivaServicio = ivaServicioVector.get(filaSeleccionada);
			SessionServiceLocator.getSriIvaServicioSessionService().deleteSriIvaServicio(ivaServicio.getId());
			SpiritAlert.createAlert("IVA Servicio eliminado con \u00e9xito", SpiritAlert.INFORMATION);
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
				ivaServicioIf.setCodigo(Integer.valueOf(getTxtCodigo().getText()));
				ivaServicioIf.setDescripcionPorcentaje(getTxtPorcentaje().getText());
				SessionServiceLocator.getSriIvaServicioSessionService().saveSriIvaServicio(ivaServicioIf);
				SpiritAlert.createAlert("IVA Servicio actualizado con \u00e9xito", SpiritAlert.INFORMATION);
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
		
		ivaServicioVector = null;
		ivaServicioVector =  new ArrayList<SriIvaServicioIf>();
		
		limpiarTabla(getTblIvaServicios());
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {			
			DefaultTableModel tableModel = (DefaultTableModel) getTblIvaServicios().getModel();
			
			Collection<SriIvaServicioIf> ivaServiciosExistentes = 
				SessionServiceLocator.getSriIvaServicioSessionService().getSriIvaServicioList(); 
			
			for (SriIvaServicioIf ivaServicioIf : ivaServiciosExistentes){
				Vector<String> fila = new Vector<String>();
				ivaServicioVector.add(ivaServicioIf);
				agregarColumnasTabla(ivaServicioIf, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblIvaServicios(), ivaServiciosExistentes, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(SriIvaServicioIf ivaServicioIf, Vector<String> fila){
		fila.add(ivaServicioIf.getCodigo().toString());
		fila.add(ivaServicioIf.getDescripcionPorcentaje());		
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
		
		boolean repetido = false;
		
		try {
			Collection<SriIvaServicioIf> ivaServicioExistentes = 
				SessionServiceLocator.getSriIvaServicioSessionService().getSriIvaServicioList();
		
			for (SriIvaServicioIf ivaServicio : ivaServicioExistentes){
				
				if (this.getMode() == SpiritMode.SAVE_MODE){
					if ((getTxtCodigo().getText().equals(ivaServicio.getCodigo().toString())) && (getTxtPorcentaje().getText().equals(ivaServicio.getDescripcionPorcentaje()))){				
						repetido=true;
						break;
					}				
				}
				
				if (this.getMode() == SpiritMode.UPDATE_MODE){
					if ((getTxtCodigo().getText().equals(ivaServicio.getCodigo().toString())) && (getTxtPorcentaje().getText().equals(ivaServicio.getDescripcionPorcentaje()))) 
						if (ivaServicioIf.getId().equals(ivaServicio.getId()) == false){
							repetido = true;
							break;
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
