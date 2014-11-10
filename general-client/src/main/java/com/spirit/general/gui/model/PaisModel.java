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
import javax.swing.table.TableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.PaisData;
import com.spirit.general.entity.PaisIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPPais;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class PaisModel extends JPPais {

	private static final long serialVersionUID = 3257283617339029297L;	
	protected TableModel dataModel;
	PaisIf pais;
	boolean isPopup = false;

	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 50;
	
	private Vector paisVector = new Vector();
	private int paisSeleccionado;
	private PaisIf paisActualizadoIf;
	private DefaultTableModel tableModel;

	public PaisModel() {
		initKeyListeners();
		setSorterTable(getTblPais());
		anchoColumnasTabla();
		getTblPais().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		getTblPais().addMouseListener(oMouseAdapterTblPais);
		getTblPais().addKeyListener(oKeyAdapterTblPais);
		
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblPais().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblPais().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
	}
	
	MouseListener oMouseAdapterTblPais = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblPais = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setPaisSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
			paisActualizadoIf = (PaisIf)  getPaisVector().get(getPaisSeleccionado());
			getTxtCodigo().setText(getPaisActualizadoIf().getCodigo());
			getTxtNombre().setText(getPaisActualizadoIf().getNombre());
			showUpdateMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				PaisData data = new PaisData();
				
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());

				SessionServiceLocator.getPaisSessionService().addPais(data);
				SpiritAlert.createAlert("País guardado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("pais",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la informacin!",SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setPaisActualizadoIf((PaisIf) getPaisVector().get(getPaisSeleccionado()));

				getPaisActualizadoIf().setCodigo(getTxtCodigo().getText());
				getPaisActualizadoIf().setNombre(getTxtNombre().getText());
				
				SessionServiceLocator.getPaisSessionService().savePais(getPaisActualizadoIf());
				getPaisVector().setElementAt(getPaisActualizadoIf(), getPaisSeleccionado());
				setPaisActualizadoIf(null);
				
				SpiritAlert.createAlert("País actualizado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("pais",null);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informacin!",SpiritAlert.ERROR);
		}		
	}

	public void delete() {
		try {
			PaisIf paisIf = (PaisIf) getPaisVector().get(getPaisSeleccionado());
			SessionServiceLocator.getPaisSessionService().deletePais(paisIf.getId());
			SpiritAlert.createAlert("País eliminado con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("pais",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	private void agregarColumnasTabla(PaisIf paisIf, Vector<String> fila){
		fila.add(paisIf.getCodigo());
		fila.add(paisIf.getNombre());		
	}
	
	private void cargarTabla() {
		try {			
			Collection pais = SessionServiceLocator.getPaisSessionService().getPaisList(); 
			Iterator paisIterator = pais.iterator();
			
			if(!getPaisVector().isEmpty()){
				getPaisVector().removeAllElements();
			}
			
			while (paisIterator.hasNext()) {
				PaisIf paisIf = (PaisIf) paisIterator.next();
				
				tableModel = (DefaultTableModel) getTblPais().getModel();
				Vector<String> fila = new Vector<String>();
				getPaisVector().add(paisIf);
				
				agregarColumnasTabla(paisIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblPais(), pais, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
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
		
		Collection pais = null;
		boolean codigoRepetido = false;
		
		try {
			pais = SessionServiceLocator.getPaisSessionService().getPaisList();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator paisIt = pais.iterator();
		
		while (paisIt.hasNext()) {
			PaisIf paisIf = (PaisIf) paisIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(paisIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(paisIf.getCodigo())) 
					if (getPaisActualizadoIf().getId().equals(paisIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El cdigo del País está duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un cdigo para el País !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el País !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		return true;
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblPais().getModel();
		
		for(int i= this.getTblPais().getRowCount();i>0;--i)
			model.removeRow(i-1);

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
		getTblPais().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public Vector getPaisVector() {
		return this.paisVector;
	}
	
	public void setPaisVector(Vector paisVector) {
		this.paisVector = paisVector;
	}
	
	public int getPaisSeleccionado() {
		return this.paisSeleccionado;
	}
	
	public void setPaisSeleccionado(int paisSeleccionado) {
		this.paisSeleccionado = paisSeleccionado;
	}
	
	public PaisIf getPaisActualizadoIf() {
		return this.paisActualizadoIf;
	}
	
	public void setPaisActualizadoIf(PaisIf paisActualizadoIf) {
		this.paisActualizadoIf = paisActualizadoIf;
	}
 
}
