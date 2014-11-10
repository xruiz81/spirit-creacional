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
import com.spirit.contabilidad.entity.TipoCuentaData;
import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.contabilidad.gui.panel.JPTipoCuenta;
import com.spirit.contabilidad.session.TipoCuentaSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoCuentaModel extends JPTipoCuenta {
	
	private static final long serialVersionUID = 3618701906990872114L;
	private static final int MAX_LONGITUD_CODIGO = 2;
	private static final int MAX_LONGITUD_NOMBRE = 50;
	
	private static final String NOMBRE_TIPO_HABER = "HABER";
	private static final String NOMBRE_TIPO_DEBE = "DEBE";
	private static final String TIPO_HABER = NOMBRE_TIPO_HABER.substring(0,1);
	private static final String TIPO_DEBE = NOMBRE_TIPO_DEBE.substring(0,1);
	
	private Vector tipoCuentaVector = new Vector();
	private int tipoCuentaSeleccionada;
	private TipoCuentaIf tipoCuentaActualizadaIf;
	private DefaultTableModel tableModel;

	public TipoCuentaModel() {
		initKeyListeners();
		setSorterTable(getTblTipoCuenta());
		getTblTipoCuenta().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setColumnWidthTable();
		showSaveMode();
		getTblTipoCuenta().addMouseListener(oMouseAdapterTblTipoCuenta);
		getTblTipoCuenta().addKeyListener(oKeyAdapterTblTipoCuenta);
		
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void setColumnWidthTable() {

		TableColumn anchoColumna = getTblTipoCuenta().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		
		anchoColumna = getTblTipoCuenta().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(300);
		    
		anchoColumna = getTblTipoCuenta().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(30);
	}
	
	MouseListener oMouseAdapterTblTipoCuenta = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	 KeyListener oKeyAdapterTblTipoCuenta = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoCuentaSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoCuentaActualizadaIf = (TipoCuentaIf)  getTipoCuentaVector().get(getTipoCuentaSeleccionada());
			getTxtCodigo().setText(getTipoCuentaActualizadaIf().getCodigo());
			getTxtNombre().setText(getTipoCuentaActualizadaIf().getNombre());
			if (TIPO_DEBE.equals(getTipoCuentaActualizadaIf().getDebehaber()))
				getCmbDebeHaber().setSelectedItem(NOMBRE_TIPO_DEBE);
			else
				getCmbDebeHaber().setSelectedItem(NOMBRE_TIPO_HABER);
			
			showUpdateMode();
		}
	}
	
	private void cargarTabla() {
		try {			
			Collection tipoCuenta = SessionServiceLocator.getTipoCuentaSessionService().getTipoCuentaList(); 
			Iterator tipoCuentaIterator = tipoCuenta.iterator();
			
			if(!getTipoCuentaVector().isEmpty()){
				getTipoCuentaVector().removeAllElements();
			}
			
			while (tipoCuentaIterator.hasNext()) {
				TipoCuentaIf tipoCuentaIf = (TipoCuentaIf) tipoCuentaIterator.next();
				
				tableModel = (DefaultTableModel) getTblTipoCuenta().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoCuentaVector().add(tipoCuentaIf);
				
				agregarColumnasTabla(tipoCuentaIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoCuenta(), tipoCuenta, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(TipoCuentaIf tipoCuentaIf, Vector<String> fila){
		
		fila.add(tipoCuentaIf.getCodigo());
		fila.add(tipoCuentaIf.getNombre());
		
		if(TIPO_DEBE.equals(tipoCuentaIf.getDebehaber()))
			fila.add(NOMBRE_TIPO_DEBE);
		else if(TIPO_HABER.equals(tipoCuentaIf.getDebehaber()))
			fila.add(NOMBRE_TIPO_HABER);
		
	}

	public void save() {
		try {
			if (validateFields()) {
				TipoCuentaData data = new TipoCuentaData();
				data.setCodigo(this.getTxtCodigo().getText().toUpperCase());
				data.setNombre(this.getTxtNombre().getText().toUpperCase());
				data.setDebehaber(this.getCmbDebeHaber().getSelectedItem().toString().substring(0, 1));
				SessionServiceLocator.getTipoCuentaSessionService().addTipoCuenta(data);
				this.clean();
				this.cleanCombos();
				this.showSaveMode();
				SpiritAlert.createAlert("Tipo de Cuenta guardado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setTipoCuentaActualizadaIf((TipoCuentaIf) getTipoCuentaVector().get(getTipoCuentaSeleccionada()));
				getTipoCuentaActualizadaIf().setCodigo(getTxtCodigo().getText());
				getTipoCuentaActualizadaIf().setDebehaber(getCmbDebeHaber().getSelectedItem().toString().substring(0,1));
				getTipoCuentaActualizadaIf().setNombre(getTxtNombre().getText());
				getTipoCuentaVector().setElementAt(getTipoCuentaActualizadaIf(), getTipoCuentaSeleccionada());
				SessionServiceLocator.getTipoCuentaSessionService().saveTipoCuenta(getTipoCuentaActualizadaIf());
				setTipoCuentaActualizadaIf(null);	
				this.showSaveMode();
				SpiritAlert.createAlert("Tipo de Cuenta actualizado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void delete() {
		try {
			TipoCuentaIf tipoCuentaIf = (TipoCuentaIf) getTipoCuentaVector().get(getTipoCuentaSeleccionada());
			SessionServiceLocator.getTipoCuentaSessionService().deleteTipoCuenta(tipoCuentaIf.getId());
			this.showSaveMode();
			SpiritAlert.createAlert("Tipo de Cuenta eliminado con éxito", SpiritAlert.INFORMATION);
		} 
		catch (Exception e) {
			e.printStackTrace();
			this.showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText()) && "".equals(this.getTxtNombre().getText())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateFields() {
	
		if ("".equals(getTxtCodigo().getText())) {
			SpiritAlert.createAlert("Debe ingresar un código para el Tipo de Cuenta !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;			
		}
		
		Collection tipoCuentas = null;
		boolean codigoRepetido = false;
		
		try {
			tipoCuentas = SessionServiceLocator.getTipoCuentaSessionService().getTipoCuentaList();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		Iterator tipoCuentasIt = tipoCuentas.iterator();
		
		while (tipoCuentasIt.hasNext()) {
			TipoCuentaIf tipoCuentaIf = (TipoCuentaIf) tipoCuentasIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoCuentaIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoCuentaIf.getCodigo())) 
					if (tipoCuentaActualizadaIf.getId().equals(tipoCuentaIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Tipo de Cuenta está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	
		
		if ("".equals(getTxtNombre().getText())) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Tipo de Cuenta !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;			
		}

		if (getCmbDebeHaber().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un tipo DEBE/HABER para el Tipo de Cuenta !!", SpiritAlert.WARNING);
			getCmbDebeHaber().grabFocus();
			return false;			
		}
			
		return true;

	}

	public void clean() {

		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoCuenta().getModel();
		
		for(int i= this.getTblTipoCuenta().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void cleanCombos(){
		this.getCmbDebeHaber().removeAllItems();
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getCmbDebeHaber().setEnabled(true);
		
		clean();
		loadCombos();
		cargarTabla();
		
		getTxtCodigo().grabFocus();
	}
	
	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getCmbDebeHaber().setEnabled(false);
		getTblTipoCuenta().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private void loadCombos() {
		if (getCmbDebeHaber().getItemCount()>0)
			getCmbDebeHaber().removeAllItems();
		
		getCmbDebeHaber().addItem(NOMBRE_TIPO_DEBE);
		getCmbDebeHaber().addItem(NOMBRE_TIPO_HABER);
	}
	
	public Vector getTipoCuentaVector() {
		return this.tipoCuentaVector;
	}
	
	public void setTipoCuentaVector(Vector tipoCuentaVector) {
		this.tipoCuentaVector = tipoCuentaVector;
	}
	
	public int getTipoCuentaSeleccionada() {
		return this.tipoCuentaSeleccionada;
	}
	
	public void setTipoCuentaSeleccionada(int tipoCuentaSeleccionada) {
		this.tipoCuentaSeleccionada = tipoCuentaSeleccionada;
	}
	
	public TipoCuentaIf getTipoCuentaActualizadaIf() {
		return this.tipoCuentaActualizadaIf;
	}
	
	public void setTipoCuentaActualizadaIf(TipoCuentaIf tipoCuentaActualizadaIf) {
		this.tipoCuentaActualizadaIf = tipoCuentaActualizadaIf;
	}

 
}
