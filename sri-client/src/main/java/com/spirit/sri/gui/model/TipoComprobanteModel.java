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
import com.spirit.sri.entity.SriTipoComprobanteData;
import com.spirit.sri.entity.SriTipoComprobanteIf;
import com.spirit.sri.gui.panel.JPTipoComprobante;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoComprobanteModel extends JPTipoComprobante{

	private static final long serialVersionUID = 1L;
	private final int MAX_LONGITUD_CODIGO = 3;
	private final int MAX_LONGITUD_NOMBRE = 150;
	
	private int filaSeleccionada = -1;
	private SriTipoComprobanteIf tipoComprobanteIf = null;
	private ArrayList<SriTipoComprobanteIf> tipoComprobanteVector = new ArrayList<SriTipoComprobanteIf>();
	
	public TipoComprobanteModel(){
		initListeners();
		iniciarComponentes();
		showSaveMode();
		new HotKeyComponent(this);
		setSorterTable(getTblTipoComprobante());
	}
	
	private void initListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		
		getTblTipoComprobante().addMouseListener(oMouseAdapterTblTipoComprobante);
		getTblTipoComprobante().addKeyListener(oKeyAdapterTblTipoComprobante);
	}
	
	private void iniciarComponentes() {
		getTblTipoComprobante().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblTipoComprobante().getColumnModel().getColumn(0);
		anchoColumna.setMaxWidth(60);
	}
	MouseListener oMouseAdapterTblTipoComprobante = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblTipoComprobante = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (getTblTipoComprobante().getSelectedRow() != -1) {
			int selectedRow = getTblTipoComprobante().getSelectedRow();
			filaSeleccionada =  getTblTipoComprobante().convertRowIndexToModel(selectedRow); 
			tipoComprobanteIf = tipoComprobanteVector.get(filaSeleccionada);
			getTxtCodigo().setText(tipoComprobanteIf.getCodigo());
			getTxtNombre().setText(tipoComprobanteIf.getNombre());
			getTxtAnulacion().setText("");
			if ( tipoComprobanteIf.getAnulacionTipoComprobanteId() != null ){
				SriTipoComprobanteIf tc;
				try {
					tc = SessionServiceLocator.getSriTipoComprobanteSessionService()
						.getSriTipoComprobante(tipoComprobanteIf.getAnulacionTipoComprobanteId());
					if ( tc != null ){
						getTxtAnulacion().setText(tc.getNombre());
					} else {
						getTxtAnulacion().setText("");
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Error al consultar Tipo de Comprobante para Anulacion !!", SpiritAlert.ERROR);
				}
				
			}
			showUpdateMode();
		}
	}
	
	private void cargarTabla() {
		try {			
			DefaultTableModel tableModel = (DefaultTableModel) getTblTipoComprobante().getModel();
			Collection<SriTipoComprobanteIf> tiposComprobantesExistentes =
				SessionServiceLocator.getTipoComrobanteSessionService().getSriTipoComprobanteList(); 
			for (SriTipoComprobanteIf tipoComprobante : tiposComprobantesExistentes){
				Vector<String> fila = new Vector<String>();
				tipoComprobanteVector.add(tipoComprobante);
				agregarFilasTabla(tipoComprobante, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoComprobante(), tiposComprobantesExistentes, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarFilasTabla(SriTipoComprobanteIf tipoComprobante, Vector<String> fila){
		fila.add(tipoComprobante.getCodigo());
		fila.add(tipoComprobante.getNombre());	
		if ( tipoComprobante.getAnulacionTipoComprobanteId() != null ){
			for ( SriTipoComprobanteIf tc : tipoComprobanteVector){
				if ( tipoComprobante.getAnulacionTipoComprobanteId().equals(tc.getId()) ){
					fila.add(tc.getNombre());
				} else {
					fila.add("");
				}
			}
		}
	}

	@Override
	public void clean() {
		getTxtCodigo().setText("");
		getTxtNombre().setText("");
		getTxtAnulacion().setText("");
		
		tipoComprobanteVector = null;
		tipoComprobanteVector = new ArrayList<SriTipoComprobanteIf>();
		
		//Vacio la tabla
		limpiarTabla(getTblTipoComprobante());
	}

	@Override
	public void delete() {	
		try {
			SriTipoComprobanteIf tipoComprobante = tipoComprobanteVector.get(filaSeleccionada);
			SessionServiceLocator.getTipoComrobanteSessionService().deleteSriTipoComprobante(tipoComprobante.getId());
			SpiritAlert.createAlert("Tipo de Comprobante eliminado con \u00e9xito", SpiritAlert.INFORMATION);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	@Override
	public void save() {
		try {
			if (validateFields()) {
				SriTipoComprobanteData data = new SriTipoComprobanteData();
				
				data.setCodigo(getTxtCodigo().getText());
				data.setNombre(getTxtNombre().getText());

				SessionServiceLocator.getTipoComrobanteSessionService().addSriTipoComprobante(data);
				SpiritAlert.createAlert("Tipo de Sustento Tributario guardado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la infomación!", SpiritAlert.ERROR);
		}
	}

	@Override
	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}

	@Override
	public void update() {
		try {	
			if (validateFields()) {
				tipoComprobanteIf.setCodigo(getTxtCodigo().getText());
				tipoComprobanteIf.setNombre(getTxtNombre().getText());
				SessionServiceLocator.getTipoComrobanteSessionService().saveSriTipoComprobante(tipoComprobanteIf);
				SpiritAlert.createAlert("Tipo de Comprobante actualizado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!",
					SpiritAlert.ERROR);
		}		
	}

	@Override
	public boolean validateFields() {
		String strCodigo = getTxtCodigo().getText();
		String strNombre = getTxtNombre().getText();
		if (strCodigo == null || "".equals(strCodigo)) {
			SpiritAlert.createAlert("Debe ingresar un c\u00f3digo para el Tipo de Comprobante !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ( strNombre == null || "".equals(strNombre) ){
			SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo de Comprobante !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		boolean codigoRepetido = false;
		boolean nombreRepetido = false;
		
		try {
			Collection<SriTipoComprobanteIf> tiposComprobantesExistentes = SessionServiceLocator.getTipoComrobanteSessionService().getSriTipoComprobanteList();
		
			for ( SriTipoComprobanteIf tipoComprobante : tiposComprobantesExistentes ){
				
				if (this.getMode() == SpiritMode.SAVE_MODE){
					if (getTxtCodigo().getText().equals(tipoComprobante.getCodigo())){				
						codigoRepetido=true;
						break;
					}
					if (getTxtNombre().getText().equals(tipoComprobante.getNombre())){
						nombreRepetido=true;
						break;
					}	
				}
				
				if (this.getMode() == SpiritMode.UPDATE_MODE){
					if (getTxtCodigo().getText().equals(tipoComprobante.getCodigo())) 
						if (tipoComprobanteIf.getId().equals(tipoComprobante.getId()) == false){
							codigoRepetido = true;
							break;
						}
					if (getTxtNombre().getText().equals(tipoComprobante.getNombre()) )
						if (tipoComprobanteIf.getId().equals(tipoComprobante.getId()) == false){
							nombreRepetido=true;
							break;
						}
					
				}
			}
			
			if (codigoRepetido) {
				SpiritAlert.createAlert("El c\u00f3digo del Tipo de Comprobante est\u00e1 duplicado !!", SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				getTxtCodigo().setSelectionStart(0);
				getTxtCodigo().setSelectionStart(getTxtCodigo().getText().length());
				return false;
			}
			
			if (nombreRepetido) {
				SpiritAlert.createAlert("El nombre del Tipo de Comprobante est\u00e1 duplicado !!", SpiritAlert.WARNING);
				getTxtNombre().grabFocus();
				getTxtNombre().setSelectionStart(0);
				getTxtNombre().setSelectionStart(getTxtNombre().getText().length());
				return false;
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al consultar los Tipos de Comprobantes", SpiritAlert.ERROR);
			return false;
		}
		
		return true;
	}
	
	

}
