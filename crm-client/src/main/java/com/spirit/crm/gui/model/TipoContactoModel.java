package com.spirit.crm.gui.model;

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
import com.spirit.crm.entity.TipoContactoData;
import com.spirit.crm.entity.TipoContactoIf;
import com.spirit.crm.gui.panel.JPTipoContacto;
import com.spirit.crm.session.TipoContactoSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoContactoModel extends JPTipoContacto {

	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 50;
	
	private DefaultTableModel tableModel;
	private Vector tipoContactoVector = new Vector();
	private int tipoContactoSeleccionado;
	private TipoContactoIf tipoContactoActualizadoIf;
	
	public TipoContactoModel() {
		initKeyListeners();
		setSorterTable(getTblTipoContacto());
		anchoColumnasTabla();
		getTblTipoContacto().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		getTblTipoContacto().addMouseListener(oMouseAdapterTblTipoContacto);
		getTblTipoContacto().addKeyListener(oKeyAdapterTblTipoContacto);
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoContacto().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblTipoContacto().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(270);
	}
	
	MouseListener oMouseAdapterTblTipoContacto = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			 enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblTipoContacto = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoContactoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoContactoActualizadoIf = (TipoContactoIf) getTipoContactoVector().get(getTipoContactoSeleccionado());
			getTxtCodigo().setText(getTipoContactoActualizadoIf().getCodigo());
			getTxtNombre().setText(getTipoContactoActualizadoIf().getNombre());
			getTxtCodigo().setEnabled(false);
			showUpdateMode();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				TipoContactoData data = new TipoContactoData();

				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());

				SessionServiceLocator.getTipoContactoSessionService().addTipoContacto(data);
				SpiritAlert.createAlert("Tipo de contacto guardado con éxito", SpiritAlert.INFORMATION);
				this.showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {	
			if (validateFields()) {
				setTipoContactoActualizadoIf((TipoContactoIf) getTipoContactoVector().get(getTipoContactoSeleccionado()));
				getTipoContactoActualizadoIf().setNombre(getTxtNombre().getText());
				this.clean();
				getTipoContactoVector().setElementAt(getTipoContactoActualizadoIf(), getTipoContactoSeleccionado());
				SessionServiceLocator.getTipoContactoSessionService().saveTipoContacto(getTipoContactoActualizadoIf());
				setTipoContactoActualizadoIf(null);
				
				SpiritAlert.createAlert("Tipo de contacto actualizado con éxito", SpiritAlert.INFORMATION);

				this.showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			TipoContactoIf tipoContactoIf = (TipoContactoIf) getTipoContactoVector().get(getTipoContactoSeleccionado());
			SessionServiceLocator.getTipoContactoSessionService().deleteTipoContacto(tipoContactoIf.getId());
			SpiritAlert.createAlert("Tipo de contacto eliminado con éxito", SpiritAlert.INFORMATION);
			this.showSaveMode();
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

		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoContacto().getModel();
		
		for(int i= this.getTblTipoContacto().getRowCount();i>0;--i)
			model.removeRow(i-1);
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
			if (!SessionServiceLocator.getTipoContactoSessionService().findTipoContactoByEmpresaId(Parametros.getIdEmpresa()).isEmpty()) {
				Collection tiposContactos = SessionServiceLocator.getTipoContactoSessionService().findTipoContactoByEmpresaId(Parametros.getIdEmpresa()); 
				Iterator tiposContactosIterator = tiposContactos.iterator();
				
				if(!getTipoContactoVector().isEmpty()){
					getTipoContactoVector().removeAllElements();
				}
				
				while (tiposContactosIterator.hasNext()) {
					TipoContactoIf tipoContactoIf = (TipoContactoIf) tiposContactosIterator.next();
					
					tableModel = (DefaultTableModel) getTblTipoContacto().getModel();
					Vector<String> fila = new Vector<String>();
					getTipoContactoVector().add(tipoContactoIf);
					
					agregarColumnasTabla(tipoContactoIf, fila);
					
					tableModel.addRow(fila);
				}
				Utilitarios.scrollToCenter(getTblTipoContacto(), tiposContactos, 0);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(TipoContactoIf tipoContactoIf, Vector<String> fila) {	
		fila.add(tipoContactoIf.getCodigo());
		fila.add(tipoContactoIf.getNombre());
	}
	
	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();

		Collection tiposContactos = null;
		boolean codigoRepetido = false;
		
		try {
			tiposContactos = SessionServiceLocator.getTipoContactoSessionService().findTipoContactoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator tiposContactosIt = tiposContactos.iterator();
		
		while (tiposContactosIt.hasNext()) {
			TipoContactoIf tipoContactoIf = (TipoContactoIf) tiposContactosIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoContactoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoContactoIf.getCodigo())) 
					if (tipoContactoActualizadoIf.getId().equals(tipoContactoIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Tipo de Contacto está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para el Tipo de Contacto !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo de Contacto !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public Vector getTipoContactoVector() {
		return this.tipoContactoVector;
	}
	
	public void setTipoContactoVector(Vector tipoContactoVector) {
		this.tipoContactoVector = tipoContactoVector;
	}
	
	public int getTipoContactoSeleccionado() {
		return this.tipoContactoSeleccionado;
	}
	
	public void setTipoContactoSeleccionado(int tipoContactoSeleccionado) {
		this.tipoContactoSeleccionado = tipoContactoSeleccionado;
	}
	
	public TipoContactoIf getTipoContactoActualizadoIf() {
		return this.tipoContactoActualizadoIf;
	}
	
	public void setTipoContactoActualizadoIf(TipoContactoIf tipoContactoActualizadoIf) {
		this.tipoContactoActualizadoIf = tipoContactoActualizadoIf;
	}
	 
}
