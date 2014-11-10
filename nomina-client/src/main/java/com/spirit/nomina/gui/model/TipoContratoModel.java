package com.spirit.nomina.gui.model;

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
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.nomina.entity.TipoContratoData;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.gui.panel.JPTipoContrato;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoContratoModel extends JPTipoContrato {

	private static final long serialVersionUID = -9079674177620375162L;
	
	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 50;
	
	private ArrayList<TipoContratoIf> tipoContratoVector = new ArrayList<TipoContratoIf>();
	private TipoContratoIf tipoContratoActualizadoIf;
	private DefaultTableModel tableModel;

	public TipoContratoModel() {
		initKeyListeners();
		iniciarComponentes();
		setSorterTable(getTblTipoContrato());
		anchoColumnasTabla();
		getTblTipoContrato().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		getTblTipoContrato().addMouseListener(oMouseAdapterTblTipoContrato);
		getTblTipoContrato().addKeyListener(oKeyAdapterTblTipoContrato);
		new HotKeyComponent(this);
	}
	
	private void iniciarComponentes(){
		
		
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoContrato().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTipoContrato().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
	}
	
	MouseListener oMouseAdapterTblTipoContrato = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblTipoContrato = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		int tipoContratoSeleccionado = getTblTipoContrato().getSelectedRow();
		if ( tipoContratoSeleccionado != -1) {
			tipoContratoSeleccionado = getTblTipoContrato().convertRowIndexToModel(tipoContratoSeleccionado); 
			tipoContratoActualizadoIf = (TipoContratoIf)  tipoContratoVector.get(tipoContratoSeleccionado);
			getTxtCodigo().setText(tipoContratoActualizadoIf.getCodigo());
			getTxtNombre().setText(tipoContratoActualizadoIf.getNombre());
			
			setUpdateMode();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		
		getTxtCodigo().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	
	private void cargarTabla() {
		try {			
			Collection<TipoContratoIf> tiposContrato = SessionServiceLocator.getTipoContratoSessionService().findTipoContratoByEmpresaId(Parametros.getIdEmpresa()); 
			for (TipoContratoIf tipoContratoIf : tiposContrato) {
				
				tableModel = (DefaultTableModel) getTblTipoContrato().getModel();
				Vector<String> fila = new Vector<String>();
				tipoContratoVector.add(tipoContratoIf);
				
				agregarColumnasTabla(tipoContratoIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoContrato(), tiposContrato, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(TipoContratoIf tipoContratoIf, Vector<String> fila){
		
		fila.add(tipoContratoIf.getCodigo());
		fila.add(tipoContratoIf.getNombre());		
	}

	public void showFindMode() {
		showSaveMode();
	}

	public void save() {
		try {
			if (validateFields()) {
				TipoContratoData data = new TipoContratoData();
				
				data.setCodigo(getTxtCodigo().getText());
				data.setNombre(getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());

				SessionServiceLocator.getTipoContratoSessionService().addTipoContrato(data);
				SpiritAlert.createAlert("Tipo Contrato guardado con éxito", SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoContratos",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la infomación!", SpiritAlert.ERROR);
		}
	}

	public void refresh() {
		clean();
		cargarTabla();
	}

	public void update() {
		try {	
			if (validateFields()) {
				tipoContratoActualizadoIf.setCodigo(getTxtCodigo().getText());
				tipoContratoActualizadoIf.setNombre(getTxtNombre().getText());
				SessionServiceLocator.getTipoContratoSessionService().saveTipoContrato(tipoContratoActualizadoIf);
				tipoContratoActualizadoIf = null;
				
				
				SpiritAlert.createAlert("Tipo Contrato actualizado con éxito", SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoContratos",null);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la infomación!",
					SpiritAlert.ERROR);
		}	
	}


	public void delete() {
		try {
			SessionServiceLocator.getTipoContratoSessionService().deleteTipoContrato(tipoContratoActualizadoIf.getId());
			SpiritAlert.createAlert("Tipo Contrato eliminado con éxito", SpiritAlert.INFORMATION);
			SpiritCache.setObject("tipoContratos",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void clean() {
		getTxtCodigo().setText("");
		getTxtNombre().setText("");
		
		tipoContratoVector = null;
		tipoContratoVector = new ArrayList<TipoContratoIf>();
		
		//Vacio la tabla
		limpiarTabla(getTblTipoContrato());

	}

	public boolean validateFields() {
		
		String strCodigo = getTxtCodigo().getText();
		String strNombre = getTxtNombre().getText();
		
		boolean codigoRepetido = false;
		
		try {
			Collection<TipoContratoIf> tiposContrato = SessionServiceLocator.getTipoContratoSessionService().findTipoContratoByEmpresaId(Parametros.getIdEmpresa());
			
			for ( TipoContratoIf tipoContratoIf : tiposContrato ) {
				
				if (this.getMode() == SpiritMode.SAVE_MODE)
					if (getTxtCodigo().getText().equals(tipoContratoIf.getCodigo()))				
						codigoRepetido = true;
				
				if (this.getMode() == SpiritMode.UPDATE_MODE)
					if (getTxtCodigo().getText().equals(tipoContratoIf.getCodigo())) 
						if (tipoContratoActualizadoIf.getId().equals(tipoContratoIf.getId()) == false)
							codigoRepetido = true;
			}
			
			if (codigoRepetido) {
				SpiritAlert.createAlert("El código del Tipo Contrato está repetido !!",
						SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				return false;
			}
			
			if ((("".equals(strCodigo)) || (strCodigo == null))) {
				SpiritAlert.createAlert("Debe ingresar un código para el Tipo Contrato !!",
						SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				return false;
			}
	
			if ((("".equals(strNombre)) || (strNombre == null))) {
				SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo Contrato !!",
						SpiritAlert.WARNING);
				getTxtNombre().grabFocus();
				return false;
			}
			
			return true;
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return false;
	}
	
}
