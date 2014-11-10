package com.spirit.inventario.gui.model;

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
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.entity.MedidaData;
import com.spirit.inventario.entity.MedidaIf;
import com.spirit.inventario.gui.panel.JPMedida;
import com.spirit.inventario.session.MedidaSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class MedidaModel extends JPMedida {

	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 20;
	
	private DefaultTableModel tableModel;
	private Vector medidaVector = new Vector();
	private int medidaSeleccionada;
	private MedidaIf medidaActualizadaIf;
	
	public MedidaModel() {
		initKeyListeners();
		setSorterTable(getTblMedida());
		anchoColumnasTabla();
		getTblMedida().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		getTblMedida().addMouseListener(oMouseAdapterTblMedida);
		getTblMedida().addKeyListener(oKeyAdapterTblMedida);
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblMedida().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblMedida().getColumnModel().getColumn(1);
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
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setMedidaSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			medidaActualizadaIf = (MedidaIf) getMedidaVector().get(getMedidaSeleccionada());
			getTxtCodigo().setText(getMedidaActualizadaIf().getCodigo());
			getTxtNombre().setText(getMedidaActualizadaIf().getNombre());
			getTxtCodigo().setEnabled(false);
			showUpdateMode();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				MedidaData data = new MedidaData();
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());
				SessionServiceLocator.getMedidaSessionService().addMedida(data);
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
				setMedidaActualizadaIf((MedidaIf) getMedidaVector().get(getMedidaSeleccionada()));
				getMedidaActualizadaIf().setNombre(getTxtNombre().getText());
				getMedidaVector().setElementAt(getMedidaActualizadaIf(), getMedidaSeleccionada());
				SessionServiceLocator.getMedidaSessionService().saveMedida(getMedidaActualizadaIf());
				setMedidaActualizadaIf(null);
				this.showSaveMode();
				SpiritAlert.createAlert("Medida actualizada con éxito", SpiritAlert.INFORMATION);
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			MedidaIf medidaIf = (MedidaIf) getMedidaVector().get(getMedidaSeleccionada());
			SessionServiceLocator.getMedidaSessionService().deleteMedida(medidaIf.getId());
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

		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblMedida().getModel();
		
		for(int i= this.getTblMedida().getRowCount();i>0;--i)
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
			if (!SessionServiceLocator.getMedidaSessionService().findMedidaByEmpresaId(Parametros.getIdEmpresa()).isEmpty()) {
				Collection medidas = SessionServiceLocator.getMedidaSessionService().findMedidaByEmpresaId(Parametros.getIdEmpresa()); 
				Iterator medidasIterator = medidas.iterator();
				
				if(!getMedidaVector().isEmpty()){
					getMedidaVector().removeAllElements();
				}
				
				while (medidasIterator.hasNext()) {
					MedidaIf medidaIf = (MedidaIf) medidasIterator.next();
					
					tableModel = (DefaultTableModel) getTblMedida().getModel();
					Vector<String> fila = new Vector<String>();
					getMedidaVector().add(medidaIf);
					
					agregarColumnasTabla(medidaIf, fila);
					
					tableModel.addRow(fila);
				}
				Utilitarios.scrollToCenter(getTblMedida(), medidas, 0);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(MedidaIf medidaIf, Vector<String> fila) {	
		fila.add(medidaIf.getCodigo());
		fila.add(medidaIf.getNombre());
	}
	
	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();

		Collection medidas = null;
		boolean codigoRepetido = false;
		
		try {
			medidas = SessionServiceLocator.getMedidaSessionService().findMedidaByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator medidasIt = medidas.iterator();
		
		while (medidasIt.hasNext()) {
			MedidaIf medidaIf = (MedidaIf) medidasIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(medidaIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(medidaIf.getCodigo())) 
					if (medidaActualizadaIf.getId().equals(medidaIf.getId()) == false)
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
		
		return true;
	}
	
	public Vector getMedidaVector() {
		return this.medidaVector;
	}
	
	public void setMedidaVector(Vector medidaVector) {
		this.medidaVector = medidaVector;
	}
	
	public int getMedidaSeleccionada() {
		return this.medidaSeleccionada;
	}
	
	public void setMedidaSeleccionada(int medidaSeleccionada) {
		this.medidaSeleccionada = medidaSeleccionada;
	}
	
	public MedidaIf getMedidaActualizadaIf() {
		return this.medidaActualizadaIf;
	}
	
	public void setMedidaActualizadaIf(MedidaIf medidaActualizadaIf) {
		this.medidaActualizadaIf = medidaActualizadaIf;
	}
	 
}
