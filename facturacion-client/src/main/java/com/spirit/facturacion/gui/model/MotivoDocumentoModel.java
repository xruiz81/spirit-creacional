package com.spirit.facturacion.gui.model;

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
import com.spirit.facturacion.entity.MotivoDocumentoData;
import com.spirit.facturacion.entity.MotivoDocumentoIf;
import com.spirit.facturacion.gui.panel.JPMotivoDocumento;
import com.spirit.facturacion.session.MotivoDocumentoSessionService;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class MotivoDocumentoModel extends JPMotivoDocumento {

	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	
	private Vector motivoDocumentoVector = new Vector();
	private int motivoDocumentoSeleccionado;
	private MotivoDocumentoIf motivoDocumentoActualizadoIf;
	private DefaultTableModel tableModel;
	
	public MotivoDocumentoModel(){
		initKeyListeners();
		setSorterTable(getTblMotivoDocumento());
		anchoColumnasTabla();
		getTblMotivoDocumento().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		getTblMotivoDocumento().addMouseListener(oMouseAdapterTblMotivoDocumento);
		getTblMotivoDocumento().addKeyListener(oKeyAdapterTblMotivoDocumento);
		
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblMotivoDocumento().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblMotivoDocumento().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(300);
	}
	
	MouseListener oMouseAdapterTblMotivoDocumento = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblMotivoDocumento = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setMotivoDocumentoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			motivoDocumentoActualizadoIf = (MotivoDocumentoIf)  getMotivoDocumentoVector().get(getMotivoDocumentoSeleccionado());
			getTxtCodigo().setText(getMotivoDocumentoActualizadoIf().getCodigo());
			getTxtNombre().setText(getMotivoDocumentoActualizadoIf().getNombre());
			showUpdateMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				MotivoDocumentoData data = new MotivoDocumentoData();
				
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());

				SessionServiceLocator.getMotivoDocumentoSessionService().addMotivoDocumento(data);
				SpiritAlert.createAlert("Motivo Documento guardado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la infomación!",SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setMotivoDocumentoActualizadoIf((MotivoDocumentoIf) getMotivoDocumentoVector().get(getMotivoDocumentoSeleccionado()));

				getMotivoDocumentoActualizadoIf().setCodigo(getTxtCodigo().getText());
				getMotivoDocumentoActualizadoIf().setNombre(getTxtNombre().getText());
				getMotivoDocumentoActualizadoIf().setEmpresaId(Parametros.getIdEmpresa());
				
				SessionServiceLocator.getMotivoDocumentoSessionService().saveMotivoDocumento(getMotivoDocumentoActualizadoIf());
				getMotivoDocumentoVector().setElementAt(getMotivoDocumentoActualizadoIf(), getMotivoDocumentoSeleccionado());
				setMotivoDocumentoActualizadoIf(null);
				
				SpiritAlert.createAlert("Motivo Documento actualizado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la infomación!",SpiritAlert.ERROR);
		}		
	}

	public void delete() {
		try {
			MotivoDocumentoIf motivoDocumentoIf = (MotivoDocumentoIf) getMotivoDocumentoVector().get(getMotivoDocumentoSeleccionado());
			SessionServiceLocator.getMotivoDocumentoSessionService().deleteMotivoDocumento(motivoDocumentoIf.getId());
			SpiritAlert.createAlert("Motivo Documento eliminado con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("motivoDocumento",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public boolean validateFields() {
		
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		
		Collection motivoDocumento = null;
		boolean codigoRepetido = false;
		
		try {
			motivoDocumento = SessionServiceLocator.getMotivoDocumentoSessionService().findMotivoDocumentoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator motivoDocumentoIt = motivoDocumento.iterator();
		
		while (motivoDocumentoIt.hasNext()) {
			MotivoDocumentoIf motivoDocumentoIf = (MotivoDocumentoIf) motivoDocumentoIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(motivoDocumentoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(motivoDocumentoIf.getCodigo())) 
					if (getMotivoDocumentoActualizadoIf().getId().equals(motivoDocumentoIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Motivo Documento está duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para el Motivo Documento !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Motivo Documento !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		return true;
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		
		DefaultTableModel model = (DefaultTableModel) getTblMotivoDocumento().getModel();
		for(int i= this.getTblMotivoDocumento().getRowCount();i>0;--i)
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
			Collection motivoDocumento = SessionServiceLocator.getMotivoDocumentoSessionService().findMotivoDocumentoByEmpresaId(Parametros.getIdEmpresa()); 
			Iterator motivoDocumentoIterator = motivoDocumento.iterator();
			
			if(!getMotivoDocumentoVector().isEmpty()){
				getMotivoDocumentoVector().removeAllElements();
			}
			
			while (motivoDocumentoIterator.hasNext()) {
				MotivoDocumentoIf motivoDocumentoIf = (MotivoDocumentoIf) motivoDocumentoIterator.next();
				
				tableModel = (DefaultTableModel) getTblMotivoDocumento().getModel();
				Vector<String> fila = new Vector<String>();
				getMotivoDocumentoVector().add(motivoDocumentoIf);
				
				agregarColumnasTabla(motivoDocumentoIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblMotivoDocumento(), motivoDocumento, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(MotivoDocumentoIf motivoDocumentoIf, Vector<String> fila){
		fila.add(motivoDocumentoIf.getCodigo());
		fila.add(motivoDocumentoIf.getNombre());		
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	 

	public int getMotivoDocumentoSeleccionado() {
		return motivoDocumentoSeleccionado;
	}

	public void setMotivoDocumentoSeleccionado(int motivoDocumentoSeleccionado) {
		this.motivoDocumentoSeleccionado = motivoDocumentoSeleccionado;
	}

	public Vector getMotivoDocumentoVector() {
		return motivoDocumentoVector;
	}

	public void setMotivoDocumentoVector(Vector motivoDocumentoVector) {
		this.motivoDocumentoVector = motivoDocumentoVector;
	}

	public MotivoDocumentoIf getMotivoDocumentoActualizadoIf() {
		return motivoDocumentoActualizadoIf;
	}

	public void setMotivoDocumentoActualizadoIf(
			MotivoDocumentoIf motivoDocumentoActualizadoIf) {
		this.motivoDocumentoActualizadoIf = motivoDocumentoActualizadoIf;
	}
}
