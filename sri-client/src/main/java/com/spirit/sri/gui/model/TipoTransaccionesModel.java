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
import com.spirit.sri.entity.SriTipoTransaccionData;
import com.spirit.sri.entity.SriTipoTransaccionIf;
import com.spirit.sri.gui.panel.JPTipoTransacciones;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoTransaccionesModel extends JPTipoTransacciones{

	private static final long serialVersionUID = -8723547613629619841L;
	
	private final int MAX_LONGITUD_CODIGO = 2;
	private final int MAX_LONGITUD_TIPOTRANSACCION = 100;
	
	private int filaSeleccionada = -1;
	private ArrayList<SriTipoTransaccionIf> tipoTransaccionVector = new ArrayList<SriTipoTransaccionIf>();
	private SriTipoTransaccionIf tipoTransaccionIf;
	
	public TipoTransaccionesModel(){
		initKeyListeners();
		iniciarComponentes();
		showSaveMode();
		new HotKeyComponent(this);
		setSorterTable(getTblTipoTransaccion());
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtTipoTransaccion().addKeyListener(new TextChecker(MAX_LONGITUD_TIPOTRANSACCION));
		
		getTblTipoTransaccion().addMouseListener(oMouseAdapterTblTipoTransaccion);
		getTblTipoTransaccion().addKeyListener(oKeyAdapterTblTipoTransaccion);
	}
	
	private void iniciarComponentes() {
		getTblTipoTransaccion().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblTipoTransaccion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblTipoTransaccion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(500);
	}
	
	MouseListener oMouseAdapterTblTipoTransaccion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblTipoTransaccion = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (getTblTipoTransaccion().getSelectedRow() != -1) {
			int selectedRow = getTblTipoTransaccion().getSelectedRow();
			filaSeleccionada =  getTblTipoTransaccion().convertRowIndexToModel(selectedRow); 
			tipoTransaccionIf = tipoTransaccionVector.get(filaSeleccionada);
			getTxtCodigo().setText(tipoTransaccionIf.getCodigo());
			getTxtTipoTransaccion().setText(tipoTransaccionIf.getNombre());
			showUpdateMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				SriTipoTransaccionData data = new SriTipoTransaccionData();
				
				data.setCodigo(getTxtCodigo().getText());
				data.setNombre(getTxtTipoTransaccion().getText());

				SessionServiceLocator.getSriTipoTransaccionSessionService().addSriTipoTransaccion(data);
				SpiritAlert.createAlert("Tipo de Transacci\u00f3n guardado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la infomación!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			SriTipoTransaccionIf tipoTransaccion = tipoTransaccionVector.get(filaSeleccionada);
			SessionServiceLocator.getSriTipoTransaccionSessionService().deleteSriTipoTransaccion(tipoTransaccion.getId());
			SpiritAlert.createAlert("Tipo de Transacci\u00f3n eliminado con \u00e9xito", SpiritAlert.INFORMATION);
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
				tipoTransaccionIf.setCodigo(getTxtCodigo().getText());
				tipoTransaccionIf.setNombre(getTxtTipoTransaccion().getText());
				SessionServiceLocator.getSriTipoTransaccionSessionService().saveSriTipoTransaccion(tipoTransaccionIf);
				SpiritAlert.createAlert("Tipo de Transacci\u00f3n actualizado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!",	SpiritAlert.ERROR);
		}		
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtTipoTransaccion().setText("");
		
		tipoTransaccionVector = null;
		tipoTransaccionVector = new ArrayList<SriTipoTransaccionIf>();
		
		//Vacio la tabla
		limpiarTabla(getTblTipoTransaccion());
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {			
			DefaultTableModel tableModel = (DefaultTableModel) getTblTipoTransaccion().getModel();
			Collection<SriTipoTransaccionIf> tipoTransaccionesExistentes = SessionServiceLocator.getSriTipoTransaccionSessionService().getSriTipoTransaccionList(); 

			for (SriTipoTransaccionIf tipoTransaccionIf : tipoTransaccionesExistentes ){
				Vector<String> fila = new Vector<String>();
				tipoTransaccionVector.add(tipoTransaccionIf);
				agregarColumnasTabla(tipoTransaccionIf, fila);
				tableModel.addRow(fila);
			}
			
			Utilitarios.scrollToCenter(getTblTipoTransaccion(), tipoTransaccionesExistentes, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(SriTipoTransaccionIf tipoTransaccionIf, Vector<String> fila){
		fila.add(tipoTransaccionIf.getCodigo());
		fila.add(tipoTransaccionIf.getNombre());		
	}

	public boolean validateFields() {
		String strCodigo = getTxtCodigo().getText();
		String strTipoTransaccion = getTxtTipoTransaccion().getText();
		if (strCodigo == null || "".equals(strCodigo)) {
			SpiritAlert.createAlert("Debe ingresar un C\u00f3digo para el Tipo de Transacción !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if (strTipoTransaccion == null || "".equals(strTipoTransaccion) ){
			SpiritAlert.createAlert("Debe ingresar el nombre del Tipo de Transaccion !!", SpiritAlert.WARNING);
			getTxtTipoTransaccion().grabFocus();
			return false;
		}
		

		boolean codigoRepetido = false;
		boolean tipoTransaccionRepetido = false;
		
		try {
			Collection<SriTipoTransaccionIf>tipoTransaccionesExistentes = SessionServiceLocator.getSriTipoTransaccionSessionService().getSriTipoTransaccionList();
		
			for (SriTipoTransaccionIf tipoTransaccion : tipoTransaccionesExistentes){
			
				if (this.getMode() == SpiritMode.SAVE_MODE){
					if (getTxtCodigo().getText().equals(tipoTransaccion.getCodigo())){				
						codigoRepetido=true;
						break;
					}
					if (getTxtTipoTransaccion().getText().equals(tipoTransaccion.getNombre())){
						tipoTransaccionRepetido=true;
						break;
					}	
				}
				
				if (this.getMode() == SpiritMode.UPDATE_MODE){
					if (getTxtCodigo().getText().equals(tipoTransaccion.getCodigo())) 
						if (tipoTransaccionIf.getId().equals(tipoTransaccion.getId()) == false){
							codigoRepetido = true;
							break;
						}
					if (getTxtTipoTransaccion().getText().equals(tipoTransaccion.getNombre()) )
						if (tipoTransaccionIf.getId().equals(tipoTransaccion.getId()) == false){
							tipoTransaccionRepetido=true;
							break;
						}				
				}
			}
			
			if (codigoRepetido) {
				SpiritAlert.createAlert("El c\u00f3digo del Tipo de Transacci\u00f3n est\u00e1 duplicado !!", SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				getTxtCodigo().setSelectionStart(0);
				getTxtCodigo().setSelectionEnd(getTxtCodigo().getText().length());
				return false;
			}
			
			if (tipoTransaccionRepetido) {
				SpiritAlert.createAlert("La descripci\u00f3n del Tipo de Transacci\u00f3n est\u00e1 duplicado !!", SpiritAlert.WARNING);
				getTxtTipoTransaccion().grabFocus();
				getTxtTipoTransaccion().setSelectionStart(0);
				getTxtTipoTransaccion().setSelectionEnd(getTxtTipoTransaccion().getText().length());
				return false;
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al consultar los Tipos de Transacciones", SpiritAlert.ERROR);
			return false;
		}
		
		return true;
	}
	
	
}
