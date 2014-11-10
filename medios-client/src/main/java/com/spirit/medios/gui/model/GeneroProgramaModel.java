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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
import com.spirit.medios.entity.GeneroProgramaData;
import com.spirit.medios.entity.GeneroProgramaIf;
import com.spirit.medios.gui.panel.JPGeneroPrograma;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class GeneroProgramaModel extends JPGeneroPrograma {
	
	private static final long serialVersionUID = -741982191726450417L;

	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	
	private Vector generoProgramaVector = new Vector();
	private int generoProgramaSeleccionado;
	private GeneroProgramaIf generoProgramaActualizadoIf;
	private DefaultTableModel tableModel;
   
	public GeneroProgramaModel() {
		setSorterTable(getTblGeneroPrograma());
		initKeyListeners();
		setColumnWidthTable();
        getTblGeneroPrograma().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.showSaveMode();
        getTblGeneroPrograma().addMouseListener(oMouseAdapterTblGeneroPrograma);
        getTblGeneroPrograma().addKeyListener(oKeyAdapterTblGeneroPrograma);
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
        getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void setColumnWidthTable() {
		TableColumn anchoColumna = getTblGeneroPrograma().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(40);
		
		anchoColumna = getTblGeneroPrograma().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(400);
	}
	
	MouseListener oMouseAdapterTblGeneroPrograma = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	 KeyListener oKeyAdapterTblGeneroPrograma = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setGeneroProgramaSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			generoProgramaActualizadoIf = (GeneroProgramaIf)  getGeneroProgramaVector().get(getGeneroProgramaSeleccionado());
		
			getTxtCodigo().setText(getGeneroProgramaActualizadoIf().getCodigo());
			getTxtNombre().setText(getGeneroProgramaActualizadoIf().getNombre());
	
			showUpdateMode();
		}
	}

	private ArrayList getModel(ArrayList listaGeneroPrograma) {
		ArrayList data = new ArrayList();
		Iterator it = listaGeneroPrograma.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			GeneroProgramaIf bean = (GeneroProgramaIf) it.next();
            fila.add(bean.getCodigo()); 
            fila.add(bean.getNombre()); 
            data.add(fila);
		}
		return data;
	}

	public void save() {
		try {
			if (validateFields()) {
				GeneroProgramaData data = new GeneroProgramaData();

				data.setEmpresaId(Parametros.getIdEmpresa());
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());

				SessionServiceLocator.getGeneroProgramaSessionService().addGeneroPrograma(data);
				SpiritAlert.createAlert("Género de programa guardado con éxito", SpiritAlert.INFORMATION);
				this.clean();
				this.showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {
			if (validateFields()) {
				setGeneroProgramaActualizadoIf((GeneroProgramaIf) getGeneroProgramaVector().get(getGeneroProgramaSeleccionado()));

				getGeneroProgramaActualizadoIf().setNombre(getTxtNombre().getText());
				
				getGeneroProgramaVector().setElementAt(getGeneroProgramaActualizadoIf(), getGeneroProgramaSeleccionado());
				
				SessionServiceLocator.getGeneroProgramaSessionService().saveGeneroPrograma(getGeneroProgramaActualizadoIf());
				setGeneroProgramaActualizadoIf(null);
				
				SpiritAlert.createAlert("Género de programa actualizado con éxito", SpiritAlert.INFORMATION);	
				this.showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}		

	}
	
	public void delete() {
		try {
			GeneroProgramaIf generoProgramaIf = (GeneroProgramaIf) getGeneroProgramaVector().get(getGeneroProgramaSeleccionado());
			SessionServiceLocator.getGeneroProgramaSessionService().deleteGeneroPrograma(generoProgramaIf.getId());
			SpiritAlert.createAlert("Género de programa eliminado con éxito", SpiritAlert.INFORMATION);
			this.showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			this.showSaveMode();
		}
	}
	
	private void agregarColumnasTabla(GeneroProgramaIf generoProgramaIf, Vector<String> fila){
		fila.add(generoProgramaIf.getCodigo());
		fila.add(generoProgramaIf.getNombre());		
	}

	private void cargarTabla() {
		try {
			Collection generoPrograma = SessionServiceLocator.getGeneroProgramaSessionService().getGeneroProgramaList(); 
			Iterator generoProgramaIterator = generoPrograma.iterator();
			
			if(!getGeneroProgramaVector().isEmpty()){
				getGeneroProgramaVector().removeAllElements();
			}
			
			while (generoProgramaIterator.hasNext()) {
				GeneroProgramaIf generoProgramaIf = (GeneroProgramaIf) generoProgramaIterator.next();
				
				tableModel = (DefaultTableModel) getTblGeneroPrograma().getModel();
				Vector<String> fila = new Vector<String>();
				getGeneroProgramaVector().add(generoProgramaIf);
				
				agregarColumnasTabla(generoProgramaIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblGeneroPrograma(), generoPrograma, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean validateFields() {

		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para el Género de Programa !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		Collection generosPrograma = null;
		boolean codigoRepetido = false;
		
		try {
			generosPrograma = SessionServiceLocator.getGeneroProgramaSessionService().findGeneroProgramaByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator generosProgramaIt = generosPrograma.iterator();
		
		while (generosProgramaIt.hasNext()) {
			GeneroProgramaIf generoProgramaIf = (GeneroProgramaIf) generosProgramaIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(generoProgramaIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(generoProgramaIf.getCodigo())) 
					if (generoProgramaActualizadoIf.getId().equals(generoProgramaIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Género de Programa está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar una descripción para el Género de Programa !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		return true;
	}
		
	public boolean isEmpty() {
		if (true
		    && "".equals(this.getTxtCodigo().getText())
		    && "".equals(this.getTxtNombre().getText())
	               ) {

			return true;
		} else {
			return false;
		}
	}

	public void clean() {

		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblGeneroPrograma().getModel();
		
		for(int i= this.getTblGeneroPrograma().getRowCount();i>0;--i)
			model.removeRow(i-1);

	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		if (!("".equals(getTxtCodigo().getText()))) {
			aMap.put("codigo", getTxtCodigo().getText());
		}
		if (!("".equals(getTxtNombre().getText()))) {
			aMap.put("nombre", getTxtNombre().getText());
		}
		return aMap;

	}
	
	public void showFindMode() {
		showSaveMode();
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		
		clean();
		cargarTabla();		
		getTxtCodigo().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getTblGeneroPrograma().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public Vector getGeneroProgramaVector() {
		return this.generoProgramaVector;
	}
	
	public void setGeneroProgramaVector(Vector generoProgramaVector) {
		this.generoProgramaVector = generoProgramaVector;
	}
	
	public int getGeneroProgramaSeleccionado() {
		return this.generoProgramaSeleccionado;
	}
	
	public void setGeneroProgramaSeleccionado(int generoProgramaSeleccionado) {
		this.generoProgramaSeleccionado = generoProgramaSeleccionado;
	}
	
	public GeneroProgramaIf getGeneroProgramaActualizadoIf() {
		return this.generoProgramaActualizadoIf;
	}
	
	public void setGeneroProgramaActualizadoIf(GeneroProgramaIf generoProgramaActualizadoIf) {
		this.generoProgramaActualizadoIf = generoProgramaActualizadoIf;
	}

}
