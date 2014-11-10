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
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CentroGastoData;
import com.spirit.general.entity.CentroGastoIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPCentroGasto;
import com.spirit.general.session.CentroGastoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class CentroGastoModel extends JPCentroGasto {

	private static final long serialVersionUID = 3834770899590149606L;
	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 20;
	
	private Vector centroGastoVector = new Vector();
	private int centroGastoSeleccionado;
	private CentroGastoIf centroGastoActualizadoIf;
	private DefaultTableModel tableModel;

	public CentroGastoModel() {
		initKeyListeners();
		setSorterTable(getTblCentroGasto());
		anchoColumnasTabla();
		getTblCentroGasto().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		getTblCentroGasto().addMouseListener(oMouseAdapterTblCentroGasto);
		getTblCentroGasto().addKeyListener(oKeyAdapterTblCentroGasto);
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblCentroGasto().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblCentroGasto().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
	}
	
	MouseListener oMouseAdapterTblCentroGasto = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblCentroGasto = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setCentroGastoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
			centroGastoActualizadoIf = (CentroGastoIf)  getCentroGastoVector().get(getCentroGastoSeleccionado());
			getTxtCodigo().setText(getCentroGastoActualizadoIf().getCodigo());
			getTxtNombre().setText(getCentroGastoActualizadoIf().getNombre());
			showUpdateMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				CentroGastoData data = new CentroGastoData();
				
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());

				CentroGastoModel.getCentroGastoSessionService().addCentroGasto(data);
				SpiritAlert.createAlert("Centro de Gasto guardado con éxito", SpiritAlert.INFORMATION);
				SpiritCache.setObject("centroGasto",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la infomaci\u00f3n!", SpiritAlert.ERROR);
		}
	}

	public void update() {			
		try {	
			if (validateFields()) {
				setCentroGastoActualizadoIf((CentroGastoIf) getCentroGastoVector().get(getCentroGastoSeleccionado()));

				getCentroGastoActualizadoIf().setCodigo(getTxtCodigo().getText());
				getCentroGastoActualizadoIf().setNombre(getTxtNombre().getText());
				
				CentroGastoModel.getCentroGastoSessionService().saveCentroGasto(getCentroGastoActualizadoIf());
				getCentroGastoVector().setElementAt(getCentroGastoActualizadoIf(), getCentroGastoSeleccionado());
				SpiritCache.setObject("centroGasto",null);
				setCentroGastoActualizadoIf(null);
				
				SpiritAlert.createAlert("Centro de Gasto actualizado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!",
					SpiritAlert.ERROR);
		}		

	}

	public void delete() {
		try {
			CentroGastoIf centroGastoIf = (CentroGastoIf) getCentroGastoVector().get(getCentroGastoSeleccionado());
			CentroGastoModel.getCentroGastoSessionService().deleteCentroGasto(centroGastoIf.getId());
			SpiritAlert.createAlert("Centro de Gasto eliminado con éxito", SpiritAlert.INFORMATION);
			SpiritCache.setObject("centroGasto",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	private void agregarColumnasTabla(CentroGastoIf centroGastoIf, Vector<String> fila){
		fila.add(centroGastoIf.getCodigo());
		fila.add(centroGastoIf.getNombre());		
	}

	public boolean isEmpty() {
		
		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtNombre().getText())) {

			return true;

		} else {

			return false;
		}
	}
	
	private void cargarTabla() {
		try {			
			Collection centroGasto = CentroGastoModel.getCentroGastoSessionService().findCentroGastoByEmpresaId(Parametros.getIdEmpresa()); 
			Iterator centroGastoIterator = centroGasto.iterator();
			
			if(!getCentroGastoVector().isEmpty()){
				getCentroGastoVector().removeAllElements();
			}
			tableModel = (DefaultTableModel) getTblCentroGasto().getModel();
			while (centroGastoIterator.hasNext()) {
				CentroGastoIf centroGastoIf = (CentroGastoIf) centroGastoIterator.next();
				Vector<String> fila = new Vector<String>();
				getCentroGastoVector().add(centroGastoIf);
				agregarColumnasTabla(centroGastoIf, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblCentroGasto(), centroGasto, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean validateFields() {

		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		
		Collection centroGasto = null;
		boolean codigoRepetido = false;
		
		try {
			centroGasto = getCentroGastoSessionService().findCentroGastoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return false;
		}
		Iterator centroGastoIt = centroGasto.iterator();
		
		while (centroGastoIt.hasNext()) {
			CentroGastoIf centroGastoIf = (CentroGastoIf) centroGastoIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(centroGastoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(centroGastoIf.getCodigo())) 
					if (centroGastoActualizadoIf.getId().equals(centroGastoIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El c\u00f3digo del Centro Gasto está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un c\u00f3digo para el Centro de Gasto !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Centro de Gasto !!",
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
		DefaultTableModel model = (DefaultTableModel) getTblCentroGasto().getModel();
		
		for(int i= this.getTblCentroGasto().getRowCount();i>0;--i)
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

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getTblCentroGasto().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public Vector getCentroGastoVector() {
		return this.centroGastoVector;
	}
	
	public void setCentroGastoVector(Vector centroGastoVector) {
		this.centroGastoVector = centroGastoVector;
	}
	
	public int getCentroGastoSeleccionado() {
		return this.centroGastoSeleccionado;
	}
	
	public void setCentroGastoSeleccionado(int centroGastoSeleccionado) {
		this.centroGastoSeleccionado = centroGastoSeleccionado;
	}
	
	public CentroGastoIf getCentroGastoActualizadoIf() {
		return this.centroGastoActualizadoIf;
	}
	
	public void setCentroGastoActualizadoIf(CentroGastoIf centroGastoActualizadoIf) {
		this.centroGastoActualizadoIf = centroGastoActualizadoIf;
	}

	public static CentroGastoSessionService getCentroGastoSessionService() {
		try {
			return (CentroGastoSessionService) ServiceLocator
					.getService(ServiceLocator.CENTROGASTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicaci\u00f3n con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
}
