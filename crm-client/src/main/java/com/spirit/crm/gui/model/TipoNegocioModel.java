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
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.TipoNegocioData;
import com.spirit.crm.entity.TipoNegocioIf;
import com.spirit.crm.gui.panel.JPTipoNegocio;
import com.spirit.crm.session.TipoNegocioSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoNegocioModel extends JPTipoNegocio {
	
	private static final long serialVersionUID = 528212784392845045L;

	private static final int MAX_LONGITUD_CODIGO = 5;   
	private static final int MAX_LONGITUD_NOMBRE = 20;
   
	private Vector tipoNegocioVector = new Vector();
	private int tipoNegocioSeleccionado;
	private TipoNegocioIf tipoNegocioActualizadoIf;
	private DefaultTableModel tableModel;

	public TipoNegocioModel() {
		initKeyListeners();
		setSorterTable(getTblTipoNegocio());
		anchoColumnasTabla();
        getTblTipoNegocio().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        showSaveMode();
        getTblTipoNegocio().addMouseListener(oMouseAdapterTblTipoNegocio);	    
        getTblTipoNegocio().addKeyListener(oKeyAdapterTblTipoNegocio);
        new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
        getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoNegocio().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTipoNegocio().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
	}

	MouseListener oMouseAdapterTblTipoNegocio = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblTipoNegocio = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoNegocioSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoNegocioActualizadoIf = (TipoNegocioIf)  getTipoNegocioVector().get(getTipoNegocioSeleccionado());
			getTxtCodigo().setText(getTipoNegocioActualizadoIf().getCodigo());
			getTxtNombre().setText(getTipoNegocioActualizadoIf().getNombre());
			showUpdateMode();
		}
	}

	public void save() {
		
		try {
			if (validateFields()) {
				TipoNegocioData data = new TipoNegocioData();

				data.setEmpresaId(Parametros.getIdEmpresa());
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());

				SessionServiceLocator.getTipoNegocioSessionService().addTipoNegocio(data);
				SpiritAlert.createAlert("Tipo de Negocio guardado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoNegocio",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {
			if (validateFields()) {
				setTipoNegocioActualizadoIf((TipoNegocioIf) getTipoNegocioVector().get(getTipoNegocioSeleccionado()));

				getTipoNegocioActualizadoIf().setCodigo(getTxtCodigo().getText());
				getTipoNegocioActualizadoIf().setNombre(getTxtNombre().getText());
				
				SessionServiceLocator.getTipoNegocioSessionService().saveTipoNegocio(getTipoNegocioActualizadoIf());
				getTipoNegocioVector().setElementAt(getTipoNegocioActualizadoIf(), getTipoNegocioSeleccionado());
				setTipoNegocioActualizadoIf(null);
				
				SpiritAlert.createAlert("Tipo de Negocio actualizado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoNegocio",null);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!",SpiritAlert.ERROR);
		}		

	}

	public void delete() {
		try {
			TipoNegocioIf tipoNegocioIf = (TipoNegocioIf) getTipoNegocioVector().get(getTipoNegocioSeleccionado());
			SessionServiceLocator.getTipoNegocioSessionService().deleteTipoNegocio(tipoNegocioIf.getId());
			SpiritAlert.createAlert("Tipo de Negocio eliminado con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("tipoNegocio",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}
	
	private void agregarColumnasTabla(TipoNegocioIf tipoNegocioIf, Vector<String> fila){
		fila.add(tipoNegocioIf.getCodigo());
		fila.add(tipoNegocioIf.getNombre());		
	}

	private void cargarTabla() {
		try {
			Collection tipoNegocio = SessionServiceLocator.getTipoNegocioSessionService().findTipoNegocioByEmpresaId(Parametros.getIdEmpresa()); 
			Iterator tipoNegocioIterator = tipoNegocio.iterator();
			
			if(!getTipoNegocioVector().isEmpty()){
				getTipoNegocioVector().removeAllElements();
			}
			
			while (tipoNegocioIterator.hasNext()) {
				TipoNegocioIf tipoNegocioIf = (TipoNegocioIf) tipoNegocioIterator.next();
				
				tableModel = (DefaultTableModel) getTblTipoNegocio().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoNegocioVector().add(tipoNegocioIf);
				
				agregarColumnasTabla(tipoNegocioIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoNegocio(), tipoNegocio, 0);
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
		
		Collection tipoNegocio = null;
		boolean codigoRepetido = false;
		
		try {
			tipoNegocio = SessionServiceLocator.getTipoNegocioSessionService().findTipoNegocioByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator tipoNegocioIt = tipoNegocio.iterator();
		
		while (tipoNegocioIt.hasNext()) {
			TipoNegocioIf tipoNegocioIf = (TipoNegocioIf) tipoNegocioIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoNegocioIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoNegocioIf.getCodigo())) 
					if (tipoNegocioActualizadoIf.getId().equals(tipoNegocioIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Tipo de Negocio está duplicado !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para el Tipo de Negocio !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar una descripción para el Tipo de Negocio !!",
					SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		return true;
	}

	public void clean() {

		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoNegocio().getModel();
		
		for(int i= this.getTblTipoNegocio().getRowCount();i>0;--i)
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
		getTblTipoNegocio().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public Vector getTipoNegocioVector() {
		return this.tipoNegocioVector;
	}
	
	public void setTipoNegocioVector(Vector tipoNegocioVector) {
		this.tipoNegocioVector = tipoNegocioVector;
	}
	
	public int getTipoNegocioSeleccionado() {
		return this.tipoNegocioSeleccionado;
	}
	
	public void setTipoNegocioSeleccionado(int tipoNegocioSeleccionado) {
		this.tipoNegocioSeleccionado = tipoNegocioSeleccionado;
	}
	
	public TipoNegocioIf getTipoNegocioActualizadoIf() {
		return this.tipoNegocioActualizadoIf;
	}
	
	public void setTipoNegocioActualizadoIf(TipoNegocioIf tipoNegocioActualizadoIf) {
		this.tipoNegocioActualizadoIf = tipoNegocioActualizadoIf;
	}
 
}
