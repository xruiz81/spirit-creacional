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
import com.spirit.general.entity.TipoArchivoData;
import com.spirit.general.entity.TipoArchivoIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPTipoArchivo;
import com.spirit.general.session.TipoArchivoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoArchivoModel extends JPTipoArchivo {

	private static final long serialVersionUID = 7204963777908716678L;
	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	
	private Vector tipoArchivoVector = new Vector();
	private int tipoArchivoSeleccionado;
	private TipoArchivoIf tipoArchivoActualizadoIf;
	private DefaultTableModel tableModel;
	
	public TipoArchivoModel(){
		initKeyListeners();
		setSorterTable(getTblTipoArchivo());
		anchoColumnasTabla();
		getTblTipoArchivo().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		
		getTblTipoArchivo().addMouseListener(oMouseAdapterTblTipoArchivo);
		getTblTipoArchivo().addKeyListener(oKeyAdapterTblTipoArchivo);
		
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoArchivo().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTipoArchivo().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
	}
	
	MouseListener oMouseAdapterTblTipoArchivo = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblTipoArchivo = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoArchivoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoArchivoActualizadoIf = (TipoArchivoIf)  getTipoArchivoVector().get(getTipoArchivoSeleccionado());
			getTxtCodigo().setText(getTipoArchivoActualizadoIf().getCodigo());
			getTxtNombre().setText(getTipoArchivoActualizadoIf().getNombre());
			showUpdateMode();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				TipoArchivoData data = new TipoArchivoData();
				
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());

				SessionServiceLocator.getTipoArchivoSessionService().addTipoArchivo(data);
				SpiritAlert.createAlert("Tipo de Archivo guardado con éxito", SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoArchivo",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la infomacin!",
					SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {
			
			if (validateFields()) {
				setTipoArchivoActualizadoIf((TipoArchivoIf) getTipoArchivoVector().get(getTipoArchivoSeleccionado()));

				getTipoArchivoActualizadoIf().setCodigo(getTxtCodigo().getText());
				getTipoArchivoActualizadoIf().setNombre(getTxtNombre().getText());
				
				SessionServiceLocator.getTipoArchivoSessionService().saveTipoArchivo(getTipoArchivoActualizadoIf());
				getTipoArchivoVector().setElementAt(getTipoArchivoActualizadoIf(), getTipoArchivoSeleccionado());
				setTipoArchivoActualizadoIf(null);
				
				SpiritAlert.createAlert("Tipo de archivo actualizado con éxito", SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoArchivo",null);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la infomacin!",
					SpiritAlert.ERROR);
		}		
	}

	public void delete() {
		try {
			TipoArchivoIf tipoArchivoIf = (TipoArchivoIf) getTipoArchivoVector().get(getTipoArchivoSeleccionado());
			SessionServiceLocator.getTipoArchivoSessionService().deleteTipoArchivo(tipoArchivoIf.getId());
			SpiritAlert.createAlert("Tipo de archivo eliminado con éxito", SpiritAlert.INFORMATION);
			SpiritCache.setObject("tipoArchivo",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	private void agregarColumnasTabla(TipoArchivoIf tipoArchivoIf, Vector<String> fila){
		
		fila.add(tipoArchivoIf.getCodigo());
		fila.add(tipoArchivoIf.getNombre());		
	}
	
	private void cargarTabla() {
		try {			
			Collection tipoArchivo = SessionServiceLocator.getTipoArchivoSessionService().getTipoArchivoList(); 
			Iterator tipoArchivoIterator = tipoArchivo.iterator();
			
			if(!getTipoArchivoVector().isEmpty()){
				getTipoArchivoVector().removeAllElements();
			}
			
			while (tipoArchivoIterator.hasNext()) {
				TipoArchivoIf tipoArchivoIf = (TipoArchivoIf) tipoArchivoIterator.next();
				
				tableModel = (DefaultTableModel) getTblTipoArchivo().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoArchivoVector().add(tipoArchivoIf);
				
				agregarColumnasTabla(tipoArchivoIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoArchivo(), tipoArchivo, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		
		Collection tipoArchivo = null;
		boolean codigoRepetido = false;
		
		try {
			tipoArchivo = SessionServiceLocator.getTipoArchivoSessionService().getTipoArchivoList();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator tipoArchivoIt = tipoArchivo.iterator();
		
		while (tipoArchivoIt.hasNext()) {
			TipoArchivoIf tipoArchivoIf = (TipoArchivoIf) tipoArchivoIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoArchivoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoArchivoIf.getCodigo())) 
					if (tipoArchivoActualizadoIf.getId().equals(tipoArchivoIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("No pueden haber cdigos iguales !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
	
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un codigo para Tipo Archivo !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para Tipo Archivo !!",
					SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		return true;
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtNombre().getText())) {
			return true;
		} else {
			return false;
		}
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoArchivo().getModel();
		
		for(int i= this.getTblTipoArchivo().getRowCount();i>0;--i)
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
		getTblTipoArchivo().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public Vector getTipoArchivoVector() {
		return this.tipoArchivoVector;
	}
	
	public void setTipoArchivoVector(Vector tipoArchivoVector) {
		this.tipoArchivoVector = tipoArchivoVector;
	}
	
	public int getTipoArchivoSeleccionado() {
		return this.tipoArchivoSeleccionado;
	}
	
	public void setTipoArchivoSeleccionado(int tipoArchivoSeleccionado) {
		this.tipoArchivoSeleccionado = tipoArchivoSeleccionado;
	}
	
	public TipoArchivoIf getTipoArchivoActualizadoIf() {
		return this.tipoArchivoActualizadoIf;
	}
	
	public void setTipoArchivoActualizadoIf(TipoArchivoIf tipoArchivoActualizadoIf) {
		this.tipoArchivoActualizadoIf = tipoArchivoActualizadoIf;
	}
 
}
