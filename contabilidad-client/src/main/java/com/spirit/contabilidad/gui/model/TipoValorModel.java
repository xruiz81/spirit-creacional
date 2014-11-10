package com.spirit.contabilidad.gui.model;

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
import com.spirit.contabilidad.entity.TipoValorData;
import com.spirit.contabilidad.entity.TipoValorIf;
import com.spirit.contabilidad.gui.panel.JPTipoValor;
import com.spirit.contabilidad.session.TipoValorSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoValorModel extends JPTipoValor {

	private static final long serialVersionUID = 1250478419226037763L;
	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 20;

	private Vector tipoValorVector = new Vector();
	private int tipoValorSeleccionado;
	private TipoValorIf tipoValorActualizadoIf;
	private DefaultTableModel tableModel;

	public TipoValorModel() {
		initKeyListeners();
		setSorterTable(getTblTipoValor());
		getTblTipoValor().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		setColumnWidthTable();
		this.getTblTipoValor().addMouseListener(oMouseAdapterTblTipoValor);
		this.getTblTipoValor().addKeyListener(oKeyAdapterTblTipoValor);
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void setColumnWidthTable() {
		TableColumn anchoColumna = getTblTipoValor().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(40);
		
		anchoColumna = getTblTipoValor().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(400);
	}

	MouseListener oMouseAdapterTblTipoValor = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {	
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblTipoValor = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoValorSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoValorActualizadoIf = (TipoValorIf)  getTipoValorVector().get(getTipoValorSeleccionado());
			getTxtCodigo().setText(getTipoValorActualizadoIf().getCodigo());
			getTxtNombre().setText(getTipoValorActualizadoIf().getNombre());
			showUpdateMode();
		}
	}
	
	private void cargarTabla() {
		try {			
			Collection tipoValor = SessionServiceLocator.getTipoValorSessionService().findTipoValorByEmpresaId(Parametros.getIdEmpresa()); 
			Iterator tipoValorIterator = tipoValor.iterator();
			
			if(!getTipoValorVector().isEmpty()){
				getTipoValorVector().removeAllElements();
			}
			
			while (tipoValorIterator.hasNext()) {
				TipoValorIf tipoValorIf = (TipoValorIf) tipoValorIterator.next();
				
				tableModel = (DefaultTableModel) getTblTipoValor().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoValorVector().add(tipoValorIf);
				
				agregarColumnasTabla(tipoValorIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoValor(), tipoValor, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(TipoValorIf tipoValorIf, Vector<String> fila){
		
		fila.add(tipoValorIf.getCodigo());
		fila.add(tipoValorIf.getNombre());		
	}

	public void save() {
		try {
			if (validateFields()) {
				TipoValorData data = new TipoValorData();
				data.setCodigo(this.getTxtCodigo().getText().toUpperCase());
				data.setNombre(this.getTxtNombre().getText().toUpperCase());
				data.setEmpresaId(Parametros.getIdEmpresa());
				SessionServiceLocator.getTipoValorSessionService().addTipoValor(data);
				this.clean();
				this.showSaveMode();
				SpiritAlert.createAlert("Tipo de Valor guardado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setTipoValorActualizadoIf((TipoValorIf) getTipoValorVector().get(getTipoValorSeleccionado()));
				getTipoValorActualizadoIf().setCodigo(getTxtCodigo().getText());
				getTipoValorActualizadoIf().setNombre(getTxtNombre().getText());
				getTipoValorActualizadoIf().setEmpresaId(Parametros.getIdEmpresa());
				getTipoValorVector().setElementAt(getTipoValorActualizadoIf(), getTipoValorSeleccionado());
				SessionServiceLocator.getTipoValorSessionService().saveTipoValor(getTipoValorActualizadoIf());
				setTipoValorActualizadoIf(null);	
				this.showSaveMode();
				SpiritAlert.createAlert("Tipo de Valor actualizado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}	
	}

	public void delete() {
		try {
			TipoValorIf tipoValorIf = (TipoValorIf) getTipoValorVector().get(getTipoValorSeleccionado());
			SessionServiceLocator.getTipoValorSessionService().deleteTipoValor(tipoValorIf.getId());
			this.showSaveMode();
			SpiritAlert.createAlert("Tipo de Valor eliminado con éxito", SpiritAlert.INFORMATION);
		} 
		catch (Exception e) {
			e.printStackTrace();
			this.showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
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
		if ("".equals(getTxtCodigo().getText())) {
			SpiritAlert.createAlert("Debe ingresar un código para el Tipo de Valor !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;			
		}
		
		Collection tipoValores = null;
		boolean codigoRepetido = false;
		
		try {
			tipoValores = SessionServiceLocator.getTipoValorSessionService().findTipoValorByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		Iterator tipoValorIt = tipoValores.iterator();
		
		while (tipoValorIt.hasNext()) {
			TipoValorIf tipoValorIf = (TipoValorIf) tipoValorIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoValorIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoValorIf.getCodigo())) 
					if (tipoValorActualizadoIf.getId().equals(tipoValorIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Tipo de Valor está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	
		
		if ("".equals(getTxtNombre().getText())) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo de Valor !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;			
		}
		
		return true;
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");

		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoValor().getModel();
		
		for(int i= this.getTblTipoValor().getRowCount();i>0;--i)
			model.removeRow(i-1);

	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEditable(true);
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtCodigo().setEditable(false);
		getTxtNombre().setEnabled(true);
		getTblTipoValor().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public Vector getTipoValorVector() {
		return this.tipoValorVector;
	}
	
	public void setTipoValorVector(Vector tipoValorVector) {
		this.tipoValorVector = tipoValorVector;
	}
	
	public int getTipoValorSeleccionado() {
		return this.tipoValorSeleccionado;
	}
	
	public void setTipoValorSeleccionado(int tipoValorSeleccionado) {
		this.tipoValorSeleccionado = tipoValorSeleccionado;
	}
	
	public TipoValorIf getTipoValorActualizadoIf() {
		return this.tipoValorActualizadoIf;
	}
	
	public void setTipoValorActualizadoIf(TipoValorIf tipoValorActualizadoIf) {
		this.tipoValorActualizadoIf = tipoValorActualizadoIf;
	}
 
}
