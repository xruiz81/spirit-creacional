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
import java.util.List;
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
import com.spirit.inventario.entity.MedidaIf;
import com.spirit.inventario.entity.PresentacionData;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.gui.panel.JPPresentacion;
import com.spirit.inventario.session.MedidaSessionService;
import com.spirit.inventario.session.PresentacionSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class PresentacionModel extends JPPresentacion {

	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 20;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	
	private DefaultTableModel tableModel;
	private Vector presentacionVector = new Vector();
	private int presentacionSeleccionada;
	private PresentacionIf presentacionActualizadaIf;
	
	public PresentacionModel() {
		initKeyListeners();
		setSorterTable(getTblPresentacion());
		anchoColumnasTabla();
		cargarCombos();
		getTblPresentacion().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		getTblPresentacion().addMouseListener(oMouseAdapterTblPresentacion);
		getTblPresentacion().addKeyListener(oKeyAdapterTblPresentacion);
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblPresentacion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblPresentacion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblPresentacion().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblPresentacion().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblPresentacion().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);
	}
	
	private void cargarCombos() {
		cargarComboPadre();
		cargarComboMedida();
	}
	
	MouseListener oMouseAdapterTblPresentacion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			 enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblPresentacion = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setPresentacionSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			presentacionActualizadaIf = (PresentacionIf) getPresentacionVector().get(getPresentacionSeleccionada());
			getTxtCodigo().setText(getPresentacionActualizadaIf().getCodigo());
			getTxtNombre().setText(getPresentacionActualizadaIf().getNombre());
			if (getPresentacionActualizadaIf().getEstado().equals(ESTADO_ACTIVO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			else if (getPresentacionActualizadaIf().getEstado().equals(ESTADO_INACTIVO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			
			if (getPresentacionActualizadaIf().getPadreId() != null)
				getCmbPadre().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPadre(), getPresentacionActualizadaIf().getPadreId()));
			else
				getCmbPadre().setSelectedItem(null);
			
			getCmbPadre().validate();
			getCmbPadre().repaint();
			
			if (getPresentacionActualizadaIf().getMedidaId() != null)
				getCmbMedida().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbMedida(), getPresentacionActualizadaIf().getMedidaId()));
			else 
				getCmbMedida().setSelectedItem(null);
			
			getCmbMedida().validate();
			getCmbMedida().repaint();
			getTxtCodigo().setEnabled(false);
			showUpdateMode();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				PresentacionData data = new PresentacionData();
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());
				if (getCmbPadre().getSelectedItem() != null)
					data.setPadreId(((PresentacionIf) this.getCmbPadre().getSelectedItem()).getId());
				if (getCmbMedida().getSelectedItem() != null)
					data.setMedidaId(((MedidaIf) this.getCmbMedida().getSelectedItem()).getId());
				data.setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
				SessionServiceLocator.getPresentacionSessionService().addPresentacion(data);
				this.showSaveMode();
				SpiritAlert.createAlert("Presentación guardada con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {	
			if (validateFields()) {
				setPresentacionActualizadaIf((PresentacionIf) getPresentacionVector().get(getPresentacionSeleccionada()));
				getPresentacionActualizadaIf().setNombre(getTxtNombre().getText());
				getPresentacionActualizadaIf().setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
				if (getCmbPadre().getSelectedItem() != null)
					getPresentacionActualizadaIf().setPadreId(((PresentacionIf) this.getCmbPadre().getSelectedItem()).getId());
				if (getCmbMedida().getSelectedItem() != null)
					getPresentacionActualizadaIf().setMedidaId(((MedidaIf) this.getCmbMedida().getSelectedItem()).getId());
				getPresentacionActualizadaIf().setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
				this.clean();
				getPresentacionVector().setElementAt(getPresentacionActualizadaIf(), getPresentacionSeleccionada());
				SessionServiceLocator.getPresentacionSessionService().savePresentacion(getPresentacionActualizadaIf());
				setPresentacionActualizadaIf(null);
				this.showSaveMode();				
				SpiritAlert.createAlert("Presentación actualizada con éxito", SpiritAlert.INFORMATION);
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			PresentacionIf presentacionIf = (PresentacionIf) getPresentacionVector().get(getPresentacionSeleccionada());
			SessionServiceLocator.getPresentacionSessionService().deletePresentacion(presentacionIf.getId());
			this.showSaveMode();
			SpiritAlert.createAlert("Presentación eliminada con éxito", SpiritAlert.INFORMATION);
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			this.showSaveMode();
		}
	}
	
	public void refresh() {
		cargarCombos();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
		this.getCmbPadre().setSelectedItem(null);
		this.getCmbMedida().setSelectedItem(null);

		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblPresentacion().getModel();
		
		for(int i= this.getTblPresentacion().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getCmbEstado().setEnabled(true);
		getCmbPadre().setEnabled(true);
		getCmbMedida().setEnabled(true);
		clean();
		cargarTabla();		
		getTxtCodigo().grabFocus();
	}

	private void cargarTabla() {
		try {
			if (!SessionServiceLocator.getPresentacionSessionService().findPresentacionByEmpresaId(Parametros.getIdEmpresa()).isEmpty()) {
				Collection presentacions = SessionServiceLocator.getPresentacionSessionService().findPresentacionByEmpresaId(Parametros.getIdEmpresa()); 
				Iterator presentacionsIterator = presentacions.iterator();
				
				if(!getPresentacionVector().isEmpty()){
					getPresentacionVector().removeAllElements();
				}
				
				while (presentacionsIterator.hasNext()) {
					PresentacionIf presentacionIf = (PresentacionIf) presentacionsIterator.next();
					
					tableModel = (DefaultTableModel) getTblPresentacion().getModel();
					Vector<String> fila = new Vector<String>();
					getPresentacionVector().add(presentacionIf);
					
					agregarColumnasTabla(presentacionIf, fila);
					
					tableModel.addRow(fila);
				}
				Utilitarios.scrollToCenter(getTblPresentacion(), presentacions, 0);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(PresentacionIf presentacionIf, Vector<String> fila) {	
		fila.add(presentacionIf.getCodigo());
		fila.add(presentacionIf.getNombre());
		try {
			if (presentacionIf.getPadreId() != null) {
				PresentacionIf padre = SessionServiceLocator.getPresentacionSessionService().getPresentacion(presentacionIf.getPadreId());
				fila.add(padre.getNombre());
			} else
				fila.add("");
			
			if (presentacionIf.getMedidaId() != null) {
				MedidaIf medida = SessionServiceLocator.getMedidaSessionService().getMedida(presentacionIf.getMedidaId());
				fila.add(medida.getNombre());
			} else
				fila.add("");
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
			
		if (presentacionIf.getEstado().equals(ESTADO_ACTIVO))
			fila.add(NOMBRE_ESTADO_ACTIVO);
		else if (presentacionIf.getEstado().equals(ESTADO_INACTIVO))
		fila.add(NOMBRE_ESTADO_INACTIVO);
	}
	
	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();

		Collection presentacions = null;
		boolean codigoRepetido = false;
		
		try {
			presentacions = SessionServiceLocator.getPresentacionSessionService().findPresentacionByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator presentacionsIt = presentacions.iterator();
		
		while (presentacionsIt.hasNext()) {
			PresentacionIf presentacionIf = (PresentacionIf) presentacionsIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(presentacionIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(presentacionIf.getCodigo())) 
					if (presentacionActualizadaIf.getId().equals(presentacionIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código de la Presentación está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para la Presentación !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para la Presentación !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (getCmbEstado().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el estado para la Presentación !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (getCmbMedida().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la medida para la Presentación !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public Vector getPresentacionVector() {
		return this.presentacionVector;
	}
	
	public void setPresentacionVector(Vector presentacionVector) {
		this.presentacionVector = presentacionVector;
	}
	
	public int getPresentacionSeleccionada() {
		return this.presentacionSeleccionada;
	}
	
	public void setPresentacionSeleccionada(int presentacionSeleccionada) {
		this.presentacionSeleccionada = presentacionSeleccionada;
	}
	
	public PresentacionIf getPresentacionActualizadaIf() {
		return this.presentacionActualizadaIf;
	}
	
	public void setPresentacionActualizadaIf(PresentacionIf presentacionActualizadaIf) {
		this.presentacionActualizadaIf = presentacionActualizadaIf;
	}
	
	private void cargarComboPadre(){
		try {
			List presentaciones = (List) SessionServiceLocator.getPresentacionSessionService().findPresentacionByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(this.getCmbPadre(), presentaciones);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboMedida() {
		try {
			List medidas = (List) SessionServiceLocator.getMedidaSessionService().findMedidaByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(this.getCmbMedida(), medidas);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	 
}
