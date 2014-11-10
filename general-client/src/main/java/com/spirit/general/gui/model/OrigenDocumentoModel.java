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
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.OrigenDocumentoData;
import com.spirit.general.entity.OrigenDocumentoIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPOrigenDocumento;
import com.spirit.general.session.OrigenDocumentoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class OrigenDocumentoModel extends JPOrigenDocumento {

	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	
	private Vector origenDocumentoVector = new Vector();
	private int origenDocumentoSeleccionado;
	private OrigenDocumentoIf origenDocumentoActualizadoIf;
	private DefaultTableModel tableModel;
	
	public OrigenDocumentoModel(){
		initKeyListeners();
		setSorterTable(getTblOrigenDocumento());
		anchoColumnasTabla();
		getTblOrigenDocumento().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		getTblOrigenDocumento().addMouseListener(oMouseAdapterTblOrigenDocumento);
		getTblOrigenDocumento().addKeyListener(oKeyAdapterTblOrigenDocumento);
		
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblOrigenDocumento().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblOrigenDocumento().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(300);
	}
	
	MouseListener oMouseAdapterTblOrigenDocumento = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblOrigenDocumento = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setOrigenDocumentoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
			origenDocumentoActualizadoIf = (OrigenDocumentoIf)  getOrigenDocumentoVector().get(getOrigenDocumentoSeleccionado());
			getTxtCodigo().setText(getOrigenDocumentoActualizadoIf().getCodigo());
			getTxtNombre().setText(getOrigenDocumentoActualizadoIf().getNombre());
			showUpdateMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				OrigenDocumentoData data = new OrigenDocumentoData();
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());
				SessionServiceLocator.getOrigenDocumentoSessionService().addOrigenDocumento(data);
				SpiritCache.setObject("origenDocumento",null);
				showSaveMode();
				SpiritAlert.createAlert("Origen Documento guardado con éxito",SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la informacin!",SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setOrigenDocumentoActualizadoIf((OrigenDocumentoIf) getOrigenDocumentoVector().get(getOrigenDocumentoSeleccionado()));
				getOrigenDocumentoActualizadoIf().setCodigo(getTxtCodigo().getText());
				getOrigenDocumentoActualizadoIf().setNombre(getTxtNombre().getText());
				getOrigenDocumentoActualizadoIf().setEmpresaId(Parametros.getIdEmpresa());
				SessionServiceLocator.getOrigenDocumentoSessionService().saveOrigenDocumento(getOrigenDocumentoActualizadoIf());
				getOrigenDocumentoVector().setElementAt(getOrigenDocumentoActualizadoIf(), getOrigenDocumentoSeleccionado());
				setOrigenDocumentoActualizadoIf(null);
				SpiritCache.setObject("origenDocumento",null);
				showSaveMode();
				SpiritAlert.createAlert("Origen Documento actualizado con éxito",SpiritAlert.INFORMATION);
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informacin!",SpiritAlert.ERROR);
		}		
	}

	public void delete() {
		try {
			OrigenDocumentoIf origenDocumentoIf = (OrigenDocumentoIf) getOrigenDocumentoVector().get(getOrigenDocumentoSeleccionado());
			SessionServiceLocator.getOrigenDocumentoSessionService().deleteOrigenDocumento(origenDocumentoIf.getId());
			SpiritCache.setObject("origenDocumento",null);
			showSaveMode();
			SpiritAlert.createAlert("Origen Documento eliminado con éxito",SpiritAlert.INFORMATION);
		} 
		catch (Exception e) {
			e.printStackTrace();
			showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
		}
	}

	public boolean validateFields() {
		
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		
		Collection origenDocumento = null;
		boolean codigoRepetido = false;
		
		try {
			origenDocumento = SessionServiceLocator.getOrigenDocumentoSessionService().findOrigenDocumentoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator origenDocumentoIt = origenDocumento.iterator();
		
		while (origenDocumentoIt.hasNext()) {
			OrigenDocumentoIf origenDocumentoIf = (OrigenDocumentoIf) origenDocumentoIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(origenDocumentoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(origenDocumentoIf.getCodigo())) 
					if (getOrigenDocumentoActualizadoIf().getId().equals(origenDocumentoIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El cdigo del Origen Documento está duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un cdigo para el Origen Documento !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Origen Documento !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		return true;
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		
		DefaultTableModel model = (DefaultTableModel) getTblOrigenDocumento().getModel();
		for(int i= this.getTblOrigenDocumento().getRowCount();i>0;--i)
			model.removeRow(i-1);

	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		cargarTabla();	
		
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {
			Collection origenDocumento = SessionServiceLocator.getOrigenDocumentoSessionService().findOrigenDocumentoByEmpresaId(Parametros.getIdEmpresa()); 
			Iterator origenDocumentoIterator = origenDocumento.iterator();
			
			if(!getOrigenDocumentoVector().isEmpty()){
				getOrigenDocumentoVector().removeAllElements();
			}
			
			while (origenDocumentoIterator.hasNext()) {
				OrigenDocumentoIf origenDocumentoIf = (OrigenDocumentoIf) origenDocumentoIterator.next();
				
				tableModel = (DefaultTableModel) getTblOrigenDocumento().getModel();
				Vector<String> fila = new Vector<String>();
				getOrigenDocumentoVector().add(origenDocumentoIf);
				
				agregarColumnasTabla(origenDocumentoIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblOrigenDocumento(), origenDocumento, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(OrigenDocumentoIf origenDocumentoIf, Vector<String> fila){
		fila.add(origenDocumentoIf.getCodigo());
		fila.add(origenDocumentoIf.getNombre());		
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	} 

	public int getOrigenDocumentoSeleccionado() {
		return origenDocumentoSeleccionado;
	}

	public void setOrigenDocumentoSeleccionado(int origenDocumentoSeleccionado) {
		this.origenDocumentoSeleccionado = origenDocumentoSeleccionado;
	}

	public Vector getOrigenDocumentoVector() {
		return origenDocumentoVector;
	}

	public void setOrigenDocumentoVector(Vector origenDocumentoVector) {
		this.origenDocumentoVector = origenDocumentoVector;
	}

	public OrigenDocumentoIf getOrigenDocumentoActualizadoIf() {
		return origenDocumentoActualizadoIf;
	}

	public void setOrigenDocumentoActualizadoIf(
			OrigenDocumentoIf origenDocumentoActualizadoIf) {
		this.origenDocumentoActualizadoIf = origenDocumentoActualizadoIf;
	}
}
