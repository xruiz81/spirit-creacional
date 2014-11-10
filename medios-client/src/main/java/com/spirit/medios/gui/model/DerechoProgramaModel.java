package com.spirit.medios.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.medios.entity.DerechoProgramaData;
import com.spirit.medios.entity.DerechoProgramaIf;
import com.spirit.medios.gui.panel.JPDerechoPrograma;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class DerechoProgramaModel extends JPDerechoPrograma {
	
	private static final long serialVersionUID = 528212784392845045L;

	private static final int MAX_LONGITUD_CODIGO = 3;   
	private static final int MAX_LONGITUD_NOMBRE = 40;
   
	private Vector derechoProgramaVector = new Vector();
	private int derechoProgramaSeleccionado;
	private DerechoProgramaIf derechoProgramaActualizadoIf;
	private DefaultTableModel tableModel;
	int ultimaFila = 0;
	int saveUpdate = -1;
	
	public DerechoProgramaModel() {
		setSorterTable(getTblDerechoPrograma());
		initKeyListeners();
		setColumnWidthTable();
        getTblDerechoPrograma().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
	    this.showSaveMode();
	    
		this.getTblDerechoPrograma().addMouseListener(oMouseAdapterTblDerechoPrograma);	    
		this.getTblDerechoPrograma().addKeyListener(oKeyAdapterTblDerechoPrograma);	    
		
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
        getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void setColumnWidthTable() {
		TableColumn anchoColumna = getTblDerechoPrograma().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(40);
		
		anchoColumna = getTblDerechoPrograma().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(400);
	}

	MouseListener oMouseAdapterTblDerechoPrograma = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblDerechoPrograma = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setDerechoProgramaSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			derechoProgramaActualizadoIf = (DerechoProgramaIf)  getDerechoProgramaVector().get(getDerechoProgramaSeleccionado());
			getTxtCodigo().setText(getDerechoProgramaActualizadoIf().getCodigo());
			getTxtNombre().setText(getDerechoProgramaActualizadoIf().getNombre());	
			showUpdateMode();
		}
	}

	private ArrayList getModel(ArrayList listaDerechoPrograma) {
		ArrayList data = new ArrayList();
		Iterator it = listaDerechoPrograma.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			DerechoProgramaIf bean = (DerechoProgramaIf) it.next();
 
            fila.add(bean.getCodigo()); 
            fila.add(bean.getNombre()); 
            data.add(fila);
		}
		return data;
	}
	
	public void save() {

		try {
			if (validateFields()) {
				DerechoProgramaData data = new DerechoProgramaData();

				data.setEmpresaId(Parametros.getIdEmpresa());
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());

				SessionServiceLocator.getDerechoProgramaSessionService().addDerechoPrograma(data);
				SpiritAlert.createAlert("Derecho de programa guardado con éxito", SpiritAlert.INFORMATION);

				setSaveUpdate(SpiritMode.SAVE_MODE);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {
			if (validateFields()) {

				setDerechoProgramaActualizadoIf((DerechoProgramaIf) getDerechoProgramaVector().get(getDerechoProgramaSeleccionado()));

				getDerechoProgramaActualizadoIf().setCodigo(getTxtCodigo().getText());
				getDerechoProgramaActualizadoIf().setNombre(getTxtNombre().getText());
				
				getDerechoProgramaVector().setElementAt(getDerechoProgramaActualizadoIf(), getDerechoProgramaSeleccionado());
				
				SessionServiceLocator.getDerechoProgramaSessionService().saveDerechoPrograma(getDerechoProgramaActualizadoIf());
				setDerechoProgramaActualizadoIf(null);
				
				SpiritAlert.createAlert("Derecho de programa actualizado con éxito", SpiritAlert.INFORMATION);
				setSaveUpdate(SpiritMode.UPDATE_MODE);
				this.showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}		
	}

	public void delete() {
		try {
			DerechoProgramaIf derechoProgramaIf = (DerechoProgramaIf) getDerechoProgramaVector().get(getDerechoProgramaSeleccionado());
			SessionServiceLocator.getDerechoProgramaSessionService().deleteDerechoPrograma(derechoProgramaIf.getId());
			SpiritAlert.createAlert("Derecho de programa eliminado con éxito", SpiritAlert.INFORMATION);
			this.showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			this.showSaveMode();
		}
	}
	
	private void agregarColumnasTabla(DerechoProgramaIf derechoProgramaIf, Vector<String> fila){
		fila.add(derechoProgramaIf.getCodigo());
		fila.add(derechoProgramaIf.getNombre());		
	}

	private void cargarTabla() {
		try {			
			Collection derechoPrograma = SessionServiceLocator.getDerechoProgramaSessionService().findDerechoProgramaByEmpresaId(Parametros.getIdEmpresa());
			Iterator derechoProgramaIterator = derechoPrograma.iterator();
			
			if(!getDerechoProgramaVector().isEmpty()){
				getDerechoProgramaVector().removeAllElements();
			}
			
			while (derechoProgramaIterator.hasNext()) {
				DerechoProgramaIf derechoProgramaIf = (DerechoProgramaIf) derechoProgramaIterator.next();
				
				tableModel = (DefaultTableModel) getTblDerechoPrograma().getModel();
				Vector<String> fila = new Vector<String>();
				getDerechoProgramaVector().add(derechoProgramaIf);
				
				agregarColumnasTabla(derechoProgramaIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblDerechoPrograma(), derechoPrograma, 0);					
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
		    && "".equals(this.getTxtNombre().getText())) {

			return true;
			
		} else {
			return false;
		}
	}
	
	public boolean validateFields() {
		
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para el Derecho de Programa !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		Collection derechoPrograma = null;
		
		try {
			derechoPrograma = SessionServiceLocator.getDerechoProgramaSessionService().findDerechoProgramaByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		boolean codigoRepetido = false;	
		Iterator derechoProgramaIt = derechoPrograma.iterator();
		
		while (derechoProgramaIt.hasNext()) {
			DerechoProgramaIf derechoProgramaIf = (DerechoProgramaIf) derechoProgramaIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(derechoProgramaIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(derechoProgramaIf.getCodigo())) 
					if (derechoProgramaActualizadoIf.getId().equals(derechoProgramaIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Derecho de Programa está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		boolean nombreRepetido = false;	
		Iterator derechoProgramaIt2 = derechoPrograma.iterator();
		
		while (derechoProgramaIt2.hasNext()) {
			DerechoProgramaIf derechoProgramaIf = (DerechoProgramaIf) derechoProgramaIt2.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtNombre().getText().equals(derechoProgramaIf.getNombre()))				
					nombreRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtNombre().getText().equals(derechoProgramaIf.getNombre())) 
					if (derechoProgramaActualizadoIf.getId().equals(derechoProgramaIf.getId()) == false)
						nombreRepetido = true;
		}
		
		if (nombreRepetido) {
			SpiritAlert.createAlert("El nombre del Derecho de Programa está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar una descripción para el Derecho de Programa !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		try {
			if(getDerechoProgramaActualizadoIf() != null){
				Collection comercialesConDerechoPrograma = SessionServiceLocator.getComercialSessionService().findComercialByDerechoprogramaId(getDerechoProgramaActualizadoIf().getId());
				if(comercialesConDerechoPrograma.size() > 0){
					SpiritAlert.createAlert("No se puede modificar el Derecho de Programa porque ya esta siendo usado en un Comercial.", SpiritAlert.WARNING);
					getTxtNombre().grabFocus();
					return false;
				}
			}			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	public void clean() {

		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblDerechoPrograma().getModel();
		
		for(int i= this.getTblDerechoPrograma().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void showFindMode() {
		showSaveMode();
	}
   
	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getSpTblDerechoPrograma().getVerticalScrollBar().setValue(getSpTblDerechoPrograma().getVerticalScrollBar().getMinimum());
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getTblDerechoPrograma().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
		
	public Vector getDerechoProgramaVector() {
		return this.derechoProgramaVector;
	}
	
	public void setDerechoProgramaVector(Vector derechoProgramaVector) {
		this.derechoProgramaVector = derechoProgramaVector;
	}
	
	public int getDerechoProgramaSeleccionado() {
		return this.derechoProgramaSeleccionado;
	}
	
	public void setDerechoProgramaSeleccionado(int derechoProgramaSeleccionado) {
		this.derechoProgramaSeleccionado = derechoProgramaSeleccionado;
	}
	
	public DerechoProgramaIf getDerechoProgramaActualizadoIf() {
		return this.derechoProgramaActualizadoIf;
	}
	
	public void setDerechoProgramaActualizadoIf(DerechoProgramaIf derechoProgramaActualizadoIf) {
		this.derechoProgramaActualizadoIf = derechoProgramaActualizadoIf;
	}

	public int getSaveUpdate() {
		return saveUpdate;
	}

	public void setSaveUpdate(int saveUpdate) {
		this.saveUpdate = saveUpdate;
	}
	
}
