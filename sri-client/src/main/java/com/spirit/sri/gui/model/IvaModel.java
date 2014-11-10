package com.spirit.sri.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.sri.entity.SriIvaData;
import com.spirit.sri.entity.SriIvaIf;
import com.spirit.sri.gui.panel.JPIva;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class IvaModel extends JPIva {
	
	private static final long serialVersionUID = -2404121077958364314L;
	
	private static final int MAX_LONGITUD_CODIGO = 6; 
	private static final int MAX_LONGITUD_PORCENTAJE = 6;
	
	private int filaSeleccionada = -1;
	private ArrayList<SriIvaIf> ivaVector = new ArrayList<SriIvaIf>();
	private SriIvaIf ivaIf;

	public IvaModel(){
		initKeyListeners();
		iniciarComponentes();
		showSaveMode();
		new HotKeyComponent(this);
		setSorterTable(getTblIva());
	}
	
	private void iniciarComponentes() {
		getTblIva().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblIva().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblIva().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblIva().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblIva().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(150);
	}
	
	
	public void initKeyListeners(){
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtCodigo().addKeyListener(new NumberTextField());
		getTxtPorcentaje().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE));
		getTxtPorcentaje().addKeyListener(new NumberTextField());
		
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);		
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setEditable(false);
		
		getTblIva().addMouseListener(oMouseAdapterTblIva);
		getTblIva().addKeyListener(oKeyAdapterTblIva);
	}
	
	MouseListener oMouseAdapterTblIva = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblIva = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (getTblIva().getSelectedRow() != -1) {
			int selectedRow = getTblIva().getSelectedRow();
			filaSeleccionada =  getTblIva().convertRowIndexToModel(selectedRow); 
			ivaIf = ivaVector.get(filaSeleccionada);
			getTxtCodigo().setText(ivaIf.getCodigo().toString());
			getTxtPorcentaje().setText(ivaIf.getPorcentaje().toString());
			getCmbFechaInicio().setDate(ivaIf.getFechaInicio());
			if(ivaIf.getFechaFin() != null){
				getCmbFechaFin().setDate(ivaIf.getFechaFin());	
			}else{
				getCmbFechaFin().setDate(null);
			}
			showUpdateMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				SriIvaData data = new SriIvaData();
				
				data.setCodigo(Integer.valueOf(getTxtCodigo().getText()));
				data.setPorcentaje(Integer.valueOf(getTxtPorcentaje().getText()));
				data.setFechaInicio(new java.sql.Date(getCmbFechaInicio().getDate().getTime()));
				if(getCmbFechaFin().getDate() != null){
					data.setFechaFin(new java.sql.Date(getCmbFechaFin().getDate().getTime()));
				}
				
				SessionServiceLocator.getSriIvaSessionService().addSriIva(data);
				SpiritAlert.createAlert("IVA guardado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la infomación!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			SriIvaIf iva = ivaVector.get(filaSeleccionada);
			SessionServiceLocator.getSriIvaSessionService().deleteSriIva(iva.getId());
			SpiritAlert.createAlert("IVA eliminado con \u00e9xito", SpiritAlert.INFORMATION);
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
				ivaIf.setCodigo(Integer.valueOf(getTxtCodigo().getText()));
				ivaIf.setPorcentaje(Integer.valueOf(getTxtPorcentaje().getText()));
				ivaIf.setFechaInicio(new java.sql.Date(getCmbFechaInicio().getDate().getTime()));
				if(getCmbFechaFin().getDate() != null){
					ivaIf.setFechaFin(new java.sql.Date(getCmbFechaFin().getDate().getTime()));
				}else{
					ivaIf.setFechaFin(null);
				}
				
				SessionServiceLocator.getSriIvaSessionService().saveSriIva(ivaIf);
				SpiritAlert.createAlert("IVA actualizado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!",	SpiritAlert.ERROR);
		}		
	}

	public void clean() {
		getTxtCodigo().setText("");
		getTxtPorcentaje().setText("");	
		
		Calendar calendar = new GregorianCalendar();
		getCmbFechaInicio().setCalendar(calendar);
		getCmbFechaFin().setCalendar(calendar);
		
		ivaVector = null;
		ivaVector = new ArrayList<SriIvaIf>();
		
		limpiarTabla(getTblIva());	
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {			
			DefaultTableModel tableModel = (DefaultTableModel) getTblIva().getModel();
			
			Collection<SriIvaIf> ivaExistentes = 
				SessionServiceLocator.getSriIvaSessionService().getSriIvaList(); 
			
			for (SriIvaIf ivaIf : ivaExistentes){
				Vector<String> fila = new Vector<String>();
				ivaVector.add(ivaIf);
				agregarColumnasTabla(ivaIf, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblIva(), ivaExistentes, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(SriIvaIf ivaIf, Vector<String> fila){
		fila.add(ivaIf.getCodigo().toString());
		fila.add(ivaIf.getPorcentaje().toString());
		fila.add(Utilitarios.getFechaUppercase(ivaIf.getFechaInicio()));
		if(ivaIf.getFechaFin() != null){
			fila.add(Utilitarios.getFechaUppercase(ivaIf.getFechaFin()));
		}else{
			fila.add("");
		}
	}

	public boolean validateFields() {
		if (getTxtCodigo().getText() == null || "".equals(getTxtCodigo().getText())) {
			SpiritAlert.createAlert("Debe ingresar un C\u00f3digo para el IVA !", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}		
		if (getTxtPorcentaje().getText() == null || "".equals(getTxtPorcentaje().getText())) {
			SpiritAlert.createAlert("Debe ingresar un Porcentaje para el IVA !", SpiritAlert.WARNING);
			getTxtPorcentaje().grabFocus();
			return false;
		}
		if (getCmbFechaInicio().getSelectedItem() == null){
			SpiritAlert.createAlert("Debe seleccionar la Fecha de Inicio!", SpiritAlert.WARNING);
			getCmbFechaInicio().grabFocus();
			return false;
		}
		return true;
	}
	
	
}
