package com.spirit.rrhh.gui.model;

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
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.rrhh.entity.IdiomaData;
import com.spirit.rrhh.entity.IdiomaIf;
import com.spirit.rrhh.gui.panel.JPIdioma;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class IdiomaModel extends JPIdioma {
	
	private static final long serialVersionUID = 3157293110985597465L;
	
	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 25;
	private Vector<IdiomaIf> idiomaVector = new Vector<IdiomaIf>();
	private int idiomaSeleccionado;
	private IdiomaIf idiomaActualizadoIf;
	private DefaultTableModel tableModel;
	
	public IdiomaModel(){
		initKeyListeners();
		setSorterTable(getTblIdioma());
		anchoColumnasTabla();
		getTblIdioma().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		getTblIdioma().addMouseListener(oMouseAdapterTblIdioma);
		getTblIdioma().addKeyListener(oKeyAdapterTblIdioma);
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblIdioma().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblIdioma().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(300);
	}
	
	MouseListener oMouseAdapterTblIdioma = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblIdioma = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setIdiomaSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			idiomaActualizadoIf = (IdiomaIf)  getIdiomaVector().get(getIdiomaSeleccionado());
			getTxtCodigo().setText(getIdiomaActualizadoIf().getCodigo());
			getTxtNombre().setText(getIdiomaActualizadoIf().getNombre());
			showUpdateMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				IdiomaData data = new IdiomaData();
				
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				
				SessionServiceLocator.getIdiomaSessionService().addIdioma(data);
				SpiritAlert.createAlert("Idioma guardado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setIdiomaActualizadoIf((IdiomaIf) getIdiomaVector().get(getIdiomaSeleccionado()));

				getIdiomaActualizadoIf().setCodigo(getTxtCodigo().getText());
				getIdiomaActualizadoIf().setNombre(getTxtNombre().getText());
				
				SessionServiceLocator.getIdiomaSessionService().saveIdioma(getIdiomaActualizadoIf());
				getIdiomaVector().setElementAt(getIdiomaActualizadoIf(), getIdiomaSeleccionado());
				setIdiomaActualizadoIf(null);
				
				SpiritAlert.createAlert("Idioma actualizado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la información!",SpiritAlert.ERROR);
		}		
	}

	public void delete() {
		try {
			IdiomaIf idiomaIf = (IdiomaIf) getIdiomaVector().get(getIdiomaSeleccionado());
			SessionServiceLocator.getIdiomaSessionService().deleteIdioma(idiomaIf.getId());
			SpiritAlert.createAlert("Idioma eliminado con éxito",SpiritAlert.INFORMATION);
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
		
		Collection idioma = null;
		boolean codigoRepetido = false;
		
		try {
			idioma = SessionServiceLocator.getIdiomaSessionService().getIdiomaList();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		Iterator idiomaIt = idioma.iterator();
		
		while (idiomaIt.hasNext()) {
			IdiomaIf idiomaIf = (IdiomaIf) idiomaIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(idiomaIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(idiomaIf.getCodigo())) 
					if (getIdiomaActualizadoIf().getId().equals(idiomaIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Idioma está duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para el Idioma !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Idioma !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		return true;
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		
		limpiarTabla(getTblIdioma());
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
			Collection idioma = SessionServiceLocator.getIdiomaSessionService().getIdiomaList(); 
			Iterator idiomaIterator = idioma.iterator();
			
			if(!getIdiomaVector().isEmpty()){
				getIdiomaVector().removeAllElements();
			}
			
			tableModel = (DefaultTableModel) getTblIdioma().getModel();
			while (idiomaIterator.hasNext()) {
				IdiomaIf idiomaIf = (IdiomaIf) idiomaIterator.next();
				
				Vector<String> fila = new Vector<String>();
				getIdiomaVector().add(idiomaIf);
				
				agregarColumnasTabla(idiomaIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblIdioma(), idioma, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void refresh() {
		clean();
		cargarTabla();
	}
	
	private void agregarColumnasTabla(IdiomaIf idiomaIf, Vector<String> fila){
		fila.add(idiomaIf.getCodigo());
		fila.add(idiomaIf.getNombre());		
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
 

	public IdiomaIf getIdiomaActualizadoIf() {
		return idiomaActualizadoIf;
	}

	public void setIdiomaActualizadoIf(IdiomaIf idiomaActualizadoIf) {
		this.idiomaActualizadoIf = idiomaActualizadoIf;
	}

	public int getIdiomaSeleccionado() {
		return idiomaSeleccionado;
	}

	public void setIdiomaSeleccionado(int idiomaSeleccionado) {
		this.idiomaSeleccionado = idiomaSeleccionado;
	}

	public Vector<IdiomaIf> getIdiomaVector() {
		return idiomaVector;
	}

	public void setIdiomaVector(Vector<IdiomaIf> idiomaVector) {
		this.idiomaVector = idiomaVector;
	}
}
