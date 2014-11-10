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
import com.spirit.contabilidad.entity.TipoResultadoData;
import com.spirit.contabilidad.entity.TipoResultadoIf;
import com.spirit.contabilidad.gui.panel.JPTipoResultado;
import com.spirit.contabilidad.session.TipoResultadoSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoResultadoModel extends JPTipoResultado {

	private static final long serialVersionUID = 3618701906990872114L;
	private static final int MAX_LONGITUD_CODIGO = 2;
	private static final int MAX_LONGITUD_NOMBRE = 50;
	private static final int MAX_LONGITUD_ORDEN = 2;
	
	private Vector tipoResultadoVector = new Vector();
	private int tipoResultadoSeleccionado;
	private TipoResultadoIf tipoResultadoActualizadoIf;
	private DefaultTableModel tableModel;

	public TipoResultadoModel() {
		initKeyListeners();
		setSorterTable(getTblTipoResultado());
		getTblTipoResultado().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		setColumnWidthTable();
		this.getTblTipoResultado().addMouseListener(oMouseAdapterTblTipoResultado);
		this.getTblTipoResultado().addKeyListener(oKeyAdapterTblTipoResultado);
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtOrden().addKeyListener(new TextChecker(MAX_LONGITUD_ORDEN));
		getTxtOrden().addKeyListener (new NumberTextField());
	}
	
	private void setColumnWidthTable() {

		TableColumn anchoColumna = getTblTipoResultado().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(40);
		
		anchoColumna = getTblTipoResultado().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(400);
		    
		anchoColumna = getTblTipoResultado().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(40);
		
	}

	MouseListener oMouseAdapterTblTipoResultado = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblTipoResultado = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoResultadoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoResultadoActualizadoIf = (TipoResultadoIf)  getTipoResultadoVector().get(getTipoResultadoSeleccionado());
			getTxtCodigo().setText(getTipoResultadoActualizadoIf().getCodigo());
			getTxtNombre().setText(getTipoResultadoActualizadoIf().getNombre());
			getTxtOrden().setText(getTipoResultadoActualizadoIf().getOrden().toString());
			showUpdateMode();
		}
	}
	
	private void cargarTabla() {
		try {			
			Collection tipoResultado = SessionServiceLocator.getTipoResultadoSessionService().getTipoResultadoList(); 
			Iterator tipoResultadoIterator = tipoResultado.iterator();
			
			if(!getTipoResultadoVector().isEmpty()){
				getTipoResultadoVector().removeAllElements();
			}
			
			while (tipoResultadoIterator.hasNext()) {
				TipoResultadoIf tipoResultadoIf = (TipoResultadoIf) tipoResultadoIterator.next();
				
				tableModel = (DefaultTableModel) getTblTipoResultado().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoResultadoVector().add(tipoResultadoIf);
				
				agregarColumnasTabla(tipoResultadoIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoResultado(), tipoResultado, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(TipoResultadoIf tipoResultadoIf, Vector<String> fila){
		
		fila.add(tipoResultadoIf.getCodigo());
		fila.add(tipoResultadoIf.getNombre());
		fila.add(tipoResultadoIf.getOrden().toString());		
	}

	public void save() {
		try {
			if (validateFields()) {
				TipoResultadoData data = new TipoResultadoData();
				data.setCodigo(this.getTxtCodigo().getText().toUpperCase());
				data.setNombre(this.getTxtNombre().getText().toUpperCase());
				data.setOrden(Long.parseLong(this.getTxtOrden().getText()));
				SessionServiceLocator.getTipoResultadoSessionService().addTipoResultado(data);
				this.clean();
				this.showSaveMode();
				SpiritAlert.createAlert("Tipo de Resultado guardado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {
			if (validateFields()) {
				setTipoResultadoActualizadoIf((TipoResultadoIf) getTipoResultadoVector().get(getTipoResultadoSeleccionado()));
				getTipoResultadoActualizadoIf().setCodigo(getTxtCodigo().getText());
				getTipoResultadoActualizadoIf().setNombre(getTxtNombre().getText());
				getTipoResultadoActualizadoIf().setOrden(Long.parseLong(getTxtOrden().getText()));
				getTipoResultadoVector().setElementAt(getTipoResultadoActualizadoIf(), getTipoResultadoSeleccionado());
				SessionServiceLocator.getTipoResultadoSessionService().saveTipoResultado(getTipoResultadoActualizadoIf());
				setTipoResultadoActualizadoIf(null);	
				this.showSaveMode();
				SpiritAlert.createAlert("Tipo de Resultado actualizado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}	
	}

	public void delete() {
		try {
			TipoResultadoIf tipoResultadoIf = (TipoResultadoIf) getTipoResultadoVector().get(getTipoResultadoSeleccionado());
			SessionServiceLocator.getTipoResultadoSessionService().deleteTipoResultado(tipoResultadoIf.getId());
			this.showSaveMode();
			SpiritAlert.createAlert("Tipo de Resultado eliminado con éxito", SpiritAlert.INFORMATION);
		} catch (Exception e) {
			e.printStackTrace();
			this.showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtNombre().getText())
				&& "".equals(this.getTxtOrden().getText())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateFields() {
		if ("".equals(getTxtCodigo().getText())) {
			SpiritAlert.createAlert("Debe ingresar un código para el Tipo de Resultado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;			
		}
		
		Collection tipoResultados = null;
		boolean codigoRepetido = false;
		
		try {
			tipoResultados = SessionServiceLocator.getTipoResultadoSessionService().getTipoResultadoList();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		Iterator tipoResultadoIt = tipoResultados.iterator();
		
		while (tipoResultadoIt.hasNext()) {
			TipoResultadoIf tipoResultadoIf = (TipoResultadoIf) tipoResultadoIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoResultadoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoResultadoIf.getCodigo())) 
					if (tipoResultadoActualizadoIf.getId().equals(tipoResultadoIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Tipo de Resultado está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	
		
		if ("".equals(getTxtNombre().getText())) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo de Resultado !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;			
		}

		if ("".equals(getTxtOrden().getText())) {
			SpiritAlert.createAlert("Debe ingresar un orden para el Tipo de Resultado !!", SpiritAlert.WARNING);
			getTxtOrden().grabFocus();
			return false;			
		}
		
		return true;
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getTxtOrden().setText("");

		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoResultado().getModel();
		
		for(int i= this.getTblTipoResultado().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtOrden().setEnabled(true);
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getTxtOrden().setEnabled(true);
		getTblTipoResultado().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public Vector getTipoResultadoVector() {
		return this.tipoResultadoVector;
	}
	
	public void setTipoResultadoVector(Vector tipoResultadoVector) {
		this.tipoResultadoVector = tipoResultadoVector;
	}
	
	public int getTipoResultadoSeleccionado() {
		return this.tipoResultadoSeleccionado;
	}
	
	public void setTipoResultadoSeleccionado(int tipoResultadoSeleccionado) {
		this.tipoResultadoSeleccionado = tipoResultadoSeleccionado;
	}
	
	public TipoResultadoIf getTipoResultadoActualizadoIf() {
		return this.tipoResultadoActualizadoIf;
	}
	
	public void setTipoResultadoActualizadoIf(TipoResultadoIf tipoResultadoActualizadoIf) {
		this.tipoResultadoActualizadoIf = tipoResultadoActualizadoIf;
	}

	 
}
