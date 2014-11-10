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
import com.spirit.general.entity.TipoIdentificacionData;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPTipoIdentificacion;
import com.spirit.general.session.TipoIdentificacionSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoIdentificacionModel extends JPTipoIdentificacion {
	private static final long serialVersionUID = 3257282535024373813L;
	private static final int MAX_LONGITUD_CODIGO = 2;
	private static final int MAX_LONGITUD_NOMBRE = 20;
	private static final int MAX_LONGITUD_CODIGO_SRI = 2;
	
	private Vector tipoIdentificacionVector = new Vector();
	private int tipoIdentificacionSeleccionado;
	private TipoIdentificacionIf tipoIdentificacionActualizadoIf;
	private DefaultTableModel tableModel;

	public TipoIdentificacionModel() {
		initKeyListeners();
		setSorterTable(getTblTipoIdentificacion());
		anchoColumnasTabla();
		getTblTipoIdentificacion().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		getTblTipoIdentificacion().addMouseListener(oMouseAdapterTblTipoIdentificacion);
		getTblTipoIdentificacion().addKeyListener(oKeyAdapterTblTipoIdentificacion);
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtCodigoSri().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO_SRI));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoIdentificacion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTipoIdentificacion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
		anchoColumna = getTblTipoIdentificacion().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(30);
	}

	MouseListener oMouseAdapterTblTipoIdentificacion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblTipoIdentificacion = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoIdentificacionSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoIdentificacionActualizadoIf = (TipoIdentificacionIf)  getTipoIdentificacionVector().get(getTipoIdentificacionSeleccionado());
			getTxtCodigo().setText(getTipoIdentificacionActualizadoIf().getCodigo());
			getTxtNombre().setText(getTipoIdentificacionActualizadoIf().getNombre());
			getTxtCodigoSri().setText(getTipoIdentificacionActualizadoIf().getCodigoSri());
			showUpdateMode();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				TipoIdentificacionData data = new TipoIdentificacionData();
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setCodigoSri(this.getTxtCodigoSri().getText());
				SessionServiceLocator.getTipoIdentificacionSessionService().addTipoIdentificacion(data);
				SpiritAlert.createAlert("Tipo de Identificacin guardado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoIdentificacion",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la infomacin!",SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setTipoIdentificacionActualizadoIf((TipoIdentificacionIf) getTipoIdentificacionVector().get(getTipoIdentificacionSeleccionado()));
				getTipoIdentificacionActualizadoIf().setCodigo(getTxtCodigo().getText());
				getTipoIdentificacionActualizadoIf().setNombre(getTxtNombre().getText());
				getTipoIdentificacionActualizadoIf().setCodigoSri(getTxtCodigoSri().getText());
				SessionServiceLocator.getTipoIdentificacionSessionService().saveTipoIdentificacion(getTipoIdentificacionActualizadoIf());
				getTipoIdentificacionVector().setElementAt(getTipoIdentificacionActualizadoIf(), getTipoIdentificacionSeleccionado());
				setTipoIdentificacionActualizadoIf(null);
				SpiritAlert.createAlert("Tipo de Identificacin actualizado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoIdentificacion",null);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la infomacin!",SpiritAlert.ERROR);
		}		
	}

	public void delete() {
		try {
			TipoIdentificacionIf tipoIdentificacionIf = (TipoIdentificacionIf) getTipoIdentificacionVector().get(getTipoIdentificacionSeleccionado());
			SessionServiceLocator.getTipoIdentificacionSessionService().deleteTipoIdentificacion(tipoIdentificacionIf.getId());
			SpiritAlert.createAlert("Tipo de Identificacin eliminado con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("tipoIdentificacion",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	private void agregarColumnasTabla(TipoIdentificacionIf tipoIdentificacionIf, Vector<String> fila){
		fila.add(tipoIdentificacionIf.getCodigo());
		fila.add(tipoIdentificacionIf.getNombre());		
		fila.add(tipoIdentificacionIf.getCodigoSri());
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText()) && "".equals(this.getTxtNombre().getText()))
			return true;
		else
			return false;
	}

	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		String strCodigoSri = this.getTxtCodigoSri().getText();
		
		Collection tipoIdentificacion = null;
		boolean codigoRepetido = false;
		
		try {
			tipoIdentificacion = SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacionList();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator tipoIdentificacionIt = tipoIdentificacion.iterator();
		
		while (tipoIdentificacionIt.hasNext()) {
			TipoIdentificacionIf tipoIdentificacionIf = (TipoIdentificacionIf) tipoIdentificacionIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoIdentificacionIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoIdentificacionIf.getCodigo())) 
					if (tipoIdentificacionActualizadoIf.getId().equals(tipoIdentificacionIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El cdigo del Tipo de Identificacin está duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un cdigo para el Tipo de Identificacin !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo de Identificacin !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigoSri)) || (strCodigoSri == null))) {
			SpiritAlert.createAlert("Debe ingresar el cdigo del SRI !!", SpiritAlert.WARNING);
			getTxtCodigoSri().grabFocus();
			return false;
		}
		
		return true;

	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getTxtCodigoSri().setText("");
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoIdentificacion().getModel();
		
		for(int i= this.getTblTipoIdentificacion().getRowCount();i>0;--i)
			model.removeRow(i-1);

	}

	public void showFindMode() {
		showSaveMode();
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtCodigoSri().setEnabled(true);
		clean();
		cargarTabla();		
		getTxtCodigo().grabFocus();

	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getTxtCodigoSri().setEnabled(true);
		getTblTipoIdentificacion().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	private void cargarTabla() {
		try {
			Collection tipoIdentificacion = SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacionList(); 
			Iterator tipoIdentificacionIterator = tipoIdentificacion.iterator();
			
			if(!getTipoIdentificacionVector().isEmpty()){
				getTipoIdentificacionVector().removeAllElements();
			}
			
			while (tipoIdentificacionIterator.hasNext()) {
				TipoIdentificacionIf tipoIdentificacionIf = (TipoIdentificacionIf) tipoIdentificacionIterator.next();
				
				tableModel = (DefaultTableModel) getTblTipoIdentificacion().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoIdentificacionVector().add(tipoIdentificacionIf);
				
				agregarColumnasTabla(tipoIdentificacionIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoIdentificacion(), tipoIdentificacion, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public Vector getTipoIdentificacionVector() {
		return this.tipoIdentificacionVector;
	}
	
	public void setTipoIdentificacionVector(Vector tipoIdentificacionVector) {
		this.tipoIdentificacionVector = tipoIdentificacionVector;
	}
	
	public int getTipoIdentificacionSeleccionado() {
		return this.tipoIdentificacionSeleccionado;
	}
	
	public void setTipoIdentificacionSeleccionado(int tipoIdentificacionSeleccionado) {
		this.tipoIdentificacionSeleccionado = tipoIdentificacionSeleccionado;
	}
	
	public TipoIdentificacionIf getTipoIdentificacionActualizadoIf() {
		return this.tipoIdentificacionActualizadoIf;
	}
	
	public void setTipoIdentificacionActualizadoIf(TipoIdentificacionIf tipoIdentificacionActualizadoIf) {
		this.tipoIdentificacionActualizadoIf = tipoIdentificacionActualizadoIf;
	}
 
}
