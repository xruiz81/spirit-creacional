package com.spirit.inventario.gui.model;

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
import com.spirit.facturacion.entity.PersonalizacionTipoPersonalizacionData;
import com.spirit.facturacion.entity.PersonalizacionTipoPersonalizacionIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.gui.panel.JPPersonalizacionTipoPersonalizacion;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class PersonalizacionTipoPersonalizacionModel extends JPPersonalizacionTipoPersonalizacion {

	private static final long serialVersionUID = -7357466808081462547L;
	
	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 60;
	
	private DefaultTableModel tableModel;
	private ArrayList<PersonalizacionTipoPersonalizacionIf> entityVector = new ArrayList<PersonalizacionTipoPersonalizacionIf>();
	private int filaSeleccionada;
	private PersonalizacionTipoPersonalizacionIf entityActualizadoIf;
	private PersonalizacionTipoPersonalizacionIf data = null;
	
	public PersonalizacionTipoPersonalizacionModel() {
		initKeyListeners();
		setSorterTable(getTblTipoPersonalizacion());
		anchoColumnasTabla();
		getTblTipoPersonalizacion().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		getTblTipoPersonalizacion().addMouseListener(oMouseAdapterTblMedida);
		getTblTipoPersonalizacion().addKeyListener(oKeyAdapterTblMedida);
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoPersonalizacion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblTipoPersonalizacion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(270);
	}
	
	MouseListener oMouseAdapterTblMedida = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			 enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblMedida = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
		if (selectedRow != -1) {
			filaSeleccionada = ((JTable)evt.getSource()).convertRowIndexToModel(selectedRow);
			entityActualizadoIf = entityVector.get(filaSeleccionada);
			getTxtCodigo().setText(entityActualizadoIf.getCodigo());
			getTxtNombre().setText(entityActualizadoIf.getNombre());
			getTxtCodigo().setEnabled(false);
			showUpdateMode();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				data = new PersonalizacionTipoPersonalizacionData();
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());
				SessionServiceLocator.getPersonalizacionTipoPersonalizacionSessionService().addPersonalizacionTipoPersonalizacion(data);
				this.showSaveMode();
				SpiritAlert.createAlert("Medida guardada con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {	
			if (validateFields()) {
				entityActualizadoIf.setNombre(getTxtNombre().getText());
				SessionServiceLocator.getPersonalizacionTipoPersonalizacionSessionService().savePersonalizacionTipoPersonalizacion(entityActualizadoIf);
				entityActualizadoIf = null;
				this.showSaveMode();
				SpiritAlert.createAlert("Medida actualizada con éxito", SpiritAlert.INFORMATION);
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			SessionServiceLocator.getPersonalizacionTipoPersonalizacionSessionService().deletePersonalizacionTipoPersonalizacion(entityActualizadoIf.getId());
			this.showSaveMode();
			SpiritAlert.createAlert("Medida eliminada con éxito", SpiritAlert.INFORMATION);
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			this.showSaveMode();
		}
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");

		entityVector = null;
		entityVector = new ArrayList<PersonalizacionTipoPersonalizacionIf>();
		
		//Vacio la tabla
		limpiarTabla(getTblTipoPersonalizacion());
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		clean();
		cargarTabla();		
		getTxtCodigo().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private void cargarTabla() {
		try {
			Collection<PersonalizacionTipoPersonalizacionIf> entidades = SessionServiceLocator.getPersonalizacionTipoPersonalizacionSessionService()
				.findPersonalizacionTipoPersonalizacionByEmpresaId(Parametros.getIdEmpresa());
			tableModel = (DefaultTableModel) getTblTipoPersonalizacion().getModel();
			
			for (PersonalizacionTipoPersonalizacionIf entidad : entidades){

				Vector<String> fila = new Vector<String>();
				entityVector.add(entidad);
				fila.add(entidad.getCodigo());
				fila.add(entidad.getNombre());
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoPersonalizacion(), entityVector, 0);

		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();

		boolean codigoRepetido = false;
		
		try {
			Collection<PersonalizacionTipoPersonalizacionIf> entidades = SessionServiceLocator.getPersonalizacionTipoPersonalizacionSessionService()
				.findPersonalizacionTipoPersonalizacionByEmpresaId(Parametros.getIdEmpresa());
		
			for ( PersonalizacionTipoPersonalizacionIf entidad : entidades ) {
				
				if (this.getMode() == SpiritMode.SAVE_MODE)
					if (getTxtCodigo().getText().equals(entidad.getCodigo()))				
						codigoRepetido = true;
				
				if (this.getMode() == SpiritMode.UPDATE_MODE)
					if (getTxtCodigo().getText().equals(entidad.getCodigo())) 
						if (entityActualizadoIf.getId().equals(entidad.getId()) == false)
							codigoRepetido = true;
			}
			
			if (codigoRepetido) {
				SpiritAlert.createAlert("El código de la Medida está duplicado !!", SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				return false;
			}
			
			if ((("".equals(strCodigo)) || (strCodigo == null))) {
				SpiritAlert.createAlert("Debe ingresar un código para la Medida !!", SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				return false;
			}
	
			if ((("".equals(strNombre)) || (strNombre == null))) {
				SpiritAlert.createAlert("Debe ingresar un nombre para la Medida !!", SpiritAlert.WARNING);
				getTxtNombre().grabFocus();
				return false;
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	
	
}
