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
import com.spirit.sri.entity.SriSustentoTributarioData;
import com.spirit.sri.entity.SriSustentoTributarioIf;
import com.spirit.sri.gui.panel.JPTipoSustentoTributario;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoSustentoTributarioModel extends JPTipoSustentoTributario {

	private static final long serialVersionUID = 1L;
	private final int MAX_LONGITUD_CODIGO = 3;
	private final int MAX_LONGITUD_DESCRIPCION = 150;
	
	private int filaSeleccionada = -1;
	private ArrayList<SriSustentoTributarioIf> sustentoTributarioVector = new ArrayList<SriSustentoTributarioIf>();
	private SriSustentoTributarioIf sustentoTributarioIf;
	
	
	public TipoSustentoTributarioModel(){
		initKeyListeners();
		iniciarComponentes();
		showSaveMode();
		new HotKeyComponent(this);
		setSorterTable(getTblSustentoTributario());
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtDescripcion().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION));
		
		getTblSustentoTributario().addMouseListener(oMouseAdapterTblSustentoTributario);
		getTblSustentoTributario().addKeyListener(oKeyAdapterTblSutentoTributario);
	}
	
	private void iniciarComponentes() {
		getTblSustentoTributario().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblSustentoTributario().getColumnModel().getColumn(0);
		anchoColumna.setMaxWidth(60);
		//anchoColumna = getTblSustentoTributario().getColumnModel().getColumn(1);
		//anchoColumna.setPreferredWidth(350);
	}
	
	MouseListener oMouseAdapterTblSustentoTributario = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblSutentoTributario = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (getTblSustentoTributario().getSelectedRow() != -1) {
			int selectedRow = getTblSustentoTributario().getSelectedRow();
			filaSeleccionada =  getTblSustentoTributario().convertRowIndexToModel(selectedRow); 
			sustentoTributarioIf = sustentoTributarioVector.get(filaSeleccionada);
			getTxtCodigo().setText(sustentoTributarioIf.getCodigo());
			getTxtDescripcion().setText(sustentoTributarioIf.getDescripcion());
			showUpdateMode();
		}
	}
	
	@Override
	public boolean validateFields() {
		String strCodigo = getTxtCodigo().getText();
		String strDescripcion = getTxtDescripcion().getText();
		if (strCodigo == null || "".equals(strCodigo)) {
			SpiritAlert.createAlert("Debe ingresar un c\u00f3digo para el Sustento Tributario !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ( strDescripcion == null || "".equals(strDescripcion) ){
			SpiritAlert.createAlert("Debe ingresar una descripci\u00f3n para el Sustento Tributario !!",
					SpiritAlert.WARNING);
			getTxtDescripcion().grabFocus();
			return false;
		}
		
		boolean codigoRepetido = false;
		boolean descripcionRepetido = false;
		
		try {
			Collection<SriSustentoTributarioIf> sustentosTributariosExistentes = SessionServiceLocator.getSustentoTributarioSessionService().getSriSustentoTributarioList();
		
			for (SriSustentoTributarioIf sustentoTributario : sustentosTributariosExistentes){
				if (this.getMode() == SpiritMode.SAVE_MODE){
					if (getTxtCodigo().getText().equals(sustentoTributario.getCodigo())){				
						codigoRepetido=true;
						break;
					}
					if (getTxtDescripcion().getText().equals(sustentoTributario.getDescripcion())){
						descripcionRepetido=true;
						break;
					}	
				}
				
				if (this.getMode() == SpiritMode.UPDATE_MODE){
					if (getTxtCodigo().getText().equals(sustentoTributario.getCodigo())) 
						if (sustentoTributarioIf.getId().equals(sustentoTributario.getId()) == false){
							codigoRepetido = true;
							break;
						}
					if (getTxtDescripcion().getText().equals(sustentoTributario.getDescripcion()) )
						if (sustentoTributarioIf.getId().equals(sustentoTributario.getId()) == false){
							descripcionRepetido=true;
							break;
						}
					
				}
			}
			
			if (codigoRepetido) {
				SpiritAlert.createAlert("El c\u00f3digo del Sustento Tributario est\u00e1 duplicado !!", SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				getTxtCodigo().setSelectionStart(0);
				getTxtCodigo().setSelectionStart(getTxtCodigo().getText().length());
				return false;
			}
			
			if (descripcionRepetido) {
				SpiritAlert.createAlert("La descripci\u00f3n del Sustento Tributario est\u00e1 duplicado !!", SpiritAlert.WARNING);
				getTxtDescripcion().grabFocus();
				getTxtDescripcion().setSelectionStart(0);
				getTxtDescripcion().setSelectionStart(getTxtDescripcion().getText().length());
				return false;
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al consultar los Sustentos Tributarios", SpiritAlert.ERROR);
			return false;
		}
		
		return true;
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtDescripcion().setText("");
		
		sustentoTributarioVector = null;
		sustentoTributarioVector = new ArrayList<SriSustentoTributarioIf>();
		
		
		//Vacio la tabla
		limpiarTabla(getTblSustentoTributario());
	}

	public void delete() {
		try {
			SriSustentoTributarioIf sustentoTributario = sustentoTributarioVector.get(filaSeleccionada);
			SessionServiceLocator.getSustentoTributarioSessionService().deleteSriSustentoTributario(sustentoTributario.getId());
			SpiritAlert.createAlert("Tipo de Sustento Tributario eliminado con \u00e9xito", SpiritAlert.INFORMATION);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				SriSustentoTributarioData data = new SriSustentoTributarioData();
				
				data.setCodigo(getTxtCodigo().getText());
				data.setDescripcion(getTxtDescripcion().getText());

				SessionServiceLocator.getSustentoTributarioSessionService().addSriSustentoTributario(data);
				SpiritAlert.createAlert("Tipo de Sustento Tributario guardado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la infomación!", SpiritAlert.ERROR);
		}
	}
	
	private void cargarTabla() {
		try {			
			DefaultTableModel tableModel = (DefaultTableModel) getTblSustentoTributario().getModel();
			Collection<SriSustentoTributarioIf> sustentosTributariosExistentes =
				SessionServiceLocator.getSustentoTributarioSessionService().getSriSustentoTributarioList(); 
			for (SriSustentoTributarioIf sustentoTributario : sustentosTributariosExistentes){
				Vector<String> fila = new Vector<String>();
				sustentoTributarioVector.add(sustentoTributario);
				agregarColumnasTabla(sustentoTributario, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblSustentoTributario(), sustentosTributariosExistentes, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(SriSustentoTributarioIf sustentoTributario, Vector<String> fila){
		fila.add(sustentoTributario.getCodigo());
		fila.add(sustentoTributario.getDescripcion());		
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtDescripcion().setEnabled(true);
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}

	public void update() {
		try {	
			if (validateFields()) {
				sustentoTributarioIf.setCodigo(getTxtCodigo().getText());
				sustentoTributarioIf.setDescripcion(getTxtDescripcion().getText());
				SessionServiceLocator.getSustentoTributarioSessionService().saveSriSustentoTributario(sustentoTributarioIf);
				SpiritAlert.createAlert("Tipo de Sustento Tributario actualizado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!",	SpiritAlert.ERROR);
		}		
	}	

}
