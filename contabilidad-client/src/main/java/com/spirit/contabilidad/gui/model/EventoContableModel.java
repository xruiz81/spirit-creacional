package com.spirit.contabilidad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.EventoContableData;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.SubtipoAsientoIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.panel.JPEventoContable;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.LineaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.gui.controller.GeneralFinder;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class EventoContableModel extends JPEventoContable {
	private static final long serialVersionUID = 3618701906990872114L;
	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final int MAX_LONGITUD_ETAPA = 2;
	private static final String NOMBRE_OPCION_SI = "SI";
	private static final String OPCION_SI = NOMBRE_OPCION_SI.substring(0,1);
	private static final String NOMBRE_OPCION_NO = "NO";
	private static final String OPCION_NO = NOMBRE_OPCION_NO.substring(0,1);
	private Vector eventoContableVector = new Vector();
	private DefaultTableModel tableModel;
	protected EventoContableIf eventoContableIf;
	private int eventoContableSeleccionado;
			
	public EventoContableModel() {
		initKeyListeners();
		setSorterTable(getTblEventoContable());
		getTblEventoContable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		anchoColumnasTabla();
		showSaveMode();
		getCmbModulo().addActionListener(comboModuloListener);
		getTblEventoContable().addMouseListener(oMouseAdapterTblEventoContable);
		getTblEventoContable().addKeyListener(oKeyAdapterTblEventoContable);
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtEtapa().addKeyListener(new TextChecker(MAX_LONGITUD_ETAPA));
		getTxtEtapa().addKeyListener(new NumberTextField());
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblEventoContable().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblEventoContable().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(180);
		anchoColumna = getTblEventoContable().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblEventoContable().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(180);
		anchoColumna = getTblEventoContable().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(120);		
		anchoColumna = getTblEventoContable().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(30);
	}
	
	ActionListener comboModuloListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {

			try {
				ModuloIf modulo = (ModuloIf) getCmbModulo().getSelectedItem();
				getCmbDocumento().removeAllItems();
				getCmbDocumento().validate();
				if ( modulo!=null ){
					getCmbDocumento().setEnabled(true);
					SpiritComboBoxModel cmbModelDocumento = new SpiritComboBoxModel((List) SessionServiceLocator.getDocumentoSessionService().findDocumentoByEmpresaIdAndModuloId(Parametros.getIdEmpresa(), modulo.getId()));
					getCmbDocumento().setModel(cmbModelDocumento);
				}
			
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}
	};
	
	MouseListener oMouseAdapterTblEventoContable = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblEventoContable = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setEventoContableSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			eventoContableIf = (EventoContableIf)  getEventoContableVector().get(getEventoContableSeleccionado());
			getTxtCodigo().setText(eventoContableIf.getCodigo());
			getTxtNombre().setText(eventoContableIf.getNombre());
			getCmbModulo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbModulo(), eventoContableIf.getModuloId()));
			getCmbModulo().repaint();
			getCmbDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocumento(), eventoContableIf.getDocumentoId()));
			getCmbDocumento().repaint();
			if(eventoContableIf.getLineaId() != null){
				getCmbLinea().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbLinea(), eventoContableIf.getLineaId()));
				getCmbLinea().repaint();
			}
			getCmbPlanCuenta().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPlanCuenta(), eventoContableIf.getPlanCuentaId()));
			getCmbPlanCuenta().repaint();
			if (eventoContableIf.getSubtipoAsientoId() != null) {
				getCmbSubtipoAsiento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbSubtipoAsiento(), eventoContableIf.getSubtipoAsientoId()));
				getCmbSubtipoAsiento().repaint();
			} else {
				getCmbSubtipoAsiento().setSelectedIndex(-1);
				getCmbSubtipoAsiento().repaint();
			}
			if (eventoContableIf.getEtapa() != null)
				getTxtEtapa().setText(eventoContableIf.getEtapa().toString());
			else
				getTxtEtapa().setText("");
			if (eventoContableIf.getUsarDetalleDescripcion().equals("S"))
				getCbUsarDescripcionDetalle().setSelected(true);
			else
				getCbUsarDescripcionDetalle().setSelected(false);
			if (eventoContableIf.getAutorizacionRequerida().equals("S"))
				getCbAutorizacionRequerida().setSelected(true);
			else
				getCbAutorizacionRequerida().setSelected(false);
			if (eventoContableIf.getAgruparDetalles().equals("S"))
				getCbAgruparDetalles().setSelected(true);
			else
				getCbAgruparDetalles().setSelected(false);
			if (eventoContableIf.getValidoAlGuardar().equals("S"))
				getCbValidoGuardar().setSelected(true);
			else
				getCbValidoGuardar().setSelected(false);
			if (eventoContableIf.getValidoAlActualizar().equals("S"))
				getCbValidoActualizar().setSelected(true);
			else
				getCbValidoActualizar().setSelected(false);
			showUpdateMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				EventoContableData data = new EventoContableData();
				data.setCodigo(getTxtCodigo().getText());
				data.setNombre(getTxtNombre().getText());
				data.setModuloId(((ModuloIf) getCmbModulo().getSelectedItem()).getId());
				if(getCmbDocumento().getSelectedItem() != null) 
					data.setDocumentoId(((DocumentoIf) getCmbDocumento().getSelectedItem()).getId());
				if(getCmbLinea().getSelectedItem() != null) 
					data.setLineaId(((LineaIf) getCmbLinea().getSelectedItem()).getId());
				data.setEmpresaId(Parametros.getIdEmpresa());
				data.setOficinaId(Parametros.getIdOficina());
				data.setPlanCuentaId(((PlanCuentaIf) getCmbPlanCuenta().getSelectedItem()).getId());
				if(getCmbSubtipoAsiento().getSelectedItem() != null)
					data.setSubtipoAsientoId(((SubtipoAsientoIf) getCmbSubtipoAsiento().getSelectedItem()).getId());
				if (!getTxtEtapa().getText().equals(""))
					data.setEtapa(Long.parseLong(getTxtEtapa().getText()));
				data.setUsarDetalleDescripcion(getCbUsarDescripcionDetalle().isSelected()?OPCION_SI:OPCION_NO);
				data.setAutorizacionRequerida((getCbAutorizacionRequerida().isSelected())?OPCION_SI:OPCION_NO);
				data.setAgruparDetalles((getCbAgruparDetalles().isSelected())?OPCION_SI:OPCION_NO);
				data.setValidoAlGuardar(getCbValidoGuardar().isSelected()?OPCION_SI:OPCION_NO);
				data.setValidoAlActualizar(getCbValidoActualizar().isSelected()?OPCION_SI:OPCION_NO);
				SessionServiceLocator.getEventoContableSessionService().addEventoContable(data);
				SpiritCache.setObject("eventosContables", null);
				showSaveMode();
				SpiritAlert.createAlert("Evento Contable guardado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setEventoContableActualizadoIf((EventoContableIf) getEventoContableVector().get(getEventoContableSeleccionado()));
				getEventoContableActualizadoIf().setCodigo(getTxtCodigo().getText());
				getEventoContableActualizadoIf().setNombre(getTxtNombre().getText());
				getEventoContableActualizadoIf().setModuloId(((ModuloIf) getCmbModulo().getSelectedItem()).getId());
				if(getCmbDocumento().getSelectedItem() != null) 
					getEventoContableActualizadoIf().setDocumentoId(((DocumentoIf) getCmbDocumento().getSelectedItem()).getId());
				if(getCmbLinea().getSelectedItem() != null) 
					getEventoContableActualizadoIf().setLineaId(((LineaIf) getCmbLinea().getSelectedItem()).getId());
				getEventoContableActualizadoIf().setEmpresaId(Parametros.getIdEmpresa());
				getEventoContableActualizadoIf().setOficinaId(Parametros.getIdOficina());
				getEventoContableActualizadoIf().setPlanCuentaId(((PlanCuentaIf) getCmbPlanCuenta().getSelectedItem()).getId());
				if (getCmbSubtipoAsiento().getSelectedItem() != null)
					getEventoContableActualizadoIf().setSubtipoAsientoId(((SubtipoAsientoIf) getCmbSubtipoAsiento().getSelectedItem()).getId());
				if (!getTxtEtapa().getText().equals(""))
					getEventoContableActualizadoIf().setEtapa(Long.parseLong(getTxtEtapa().getText()));
				else
					getEventoContableActualizadoIf().setEtapa(null);
				getEventoContableActualizadoIf().setUsarDetalleDescripcion((getCbUsarDescripcionDetalle().isSelected())?OPCION_SI:OPCION_NO);
				getEventoContableActualizadoIf().setAutorizacionRequerida((getCbAutorizacionRequerida().isSelected())?OPCION_SI:OPCION_NO);
				getEventoContableActualizadoIf().setAgruparDetalles((getCbAgruparDetalles().isSelected())?OPCION_SI:OPCION_NO);
				getEventoContableActualizadoIf().setValidoAlGuardar((getCbValidoGuardar().isSelected())?OPCION_SI:OPCION_NO);
				getEventoContableActualizadoIf().setValidoAlActualizar((getCbValidoActualizar().isSelected())?OPCION_SI:OPCION_NO);
				SessionServiceLocator.getEventoContableSessionService().saveEventoContable(getEventoContableActualizadoIf());
				getEventoContableVector().setElementAt(getEventoContableActualizadoIf(), getEventoContableSeleccionado());
				setEventoContableActualizadoIf(null);
				showSaveMode();
				SpiritAlert.createAlert("Evento Contable actualizado con éxito", SpiritAlert.INFORMATION);	
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void delete() {
		try {
			EventoContableIf eventoContableIf = (EventoContableIf) getEventoContableVector().get(getEventoContableSeleccionado());
			SessionServiceLocator.getEventoContableSessionService().deleteEventoContable(eventoContableIf.getId());
			showSaveMode();
			SpiritAlert.createAlert("Evento Contable eliminado con éxito", SpiritAlert.INFORMATION);
			} 
		catch (Exception e) {
			e.printStackTrace();
			showSaveMode();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}

	public boolean validateFields() {
		String strCodigo = getTxtCodigo().getText();
		String strNombre = getTxtNombre().getText();
		ModuloIf modulo = (ModuloIf) getCmbModulo().getSelectedItem();
		PlanCuentaIf planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getSelectedItem();
		
		Collection eventoContable = null;
		boolean codigoRepetido = false;
		
		try {
			eventoContable = SessionServiceLocator.getEventoContableSessionService().findEventoContableByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		Iterator eventoContableIt = eventoContable.iterator();
		
		while (eventoContableIt.hasNext()) {
			EventoContableIf eventoContableIf = (EventoContableIf) eventoContableIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(eventoContableIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(eventoContableIf.getCodigo())) 
					if (getEventoContableActualizadoIf().getId().equals(eventoContableIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Evento Contable está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar el Código !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(planCuenta)) || (planCuenta == null))) {
			SpiritAlert.createAlert("Debe escoger un Plan de Cuenta !!", SpiritAlert.WARNING);
			getCmbPlanCuenta().grabFocus();
			return false;
		}
		
		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un Nombre !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if ((("".equals(modulo)) || (modulo == null))) {
			SpiritAlert.createAlert("Debe escoger un Módulo !!", SpiritAlert.WARNING);
			getCmbModulo().grabFocus();
			return false;
		}
				
		return true;
	}

	public void clean() {
		
		getTxtCodigo().setText("");
		getTxtNombre().setText(""); 
		getCmbPlanCuenta().removeAllItems();
		getCmbModulo().removeAllItems();
		getCmbDocumento().setSelectedIndex(-1);
		getCmbDocumento().removeAllItems();
		getCmbLinea().removeAllItems();
		getCmbSubtipoAsiento().removeAllItems();
		getTxtEtapa().setText("");
		getCbUsarDescripcionDetalle().setSelected(false);
		getCbAutorizacionRequerida().setSelected(false);
		getCbAgruparDetalles().setSelected(false);
		getCbValidoGuardar().setSelected(false);
		getCbValidoActualizar().setSelected(false);
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblEventoContable().getModel();
		for(int i= this.getTblEventoContable().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		getCmbDocumento().setEnabled(false);
		
		cargarCombos();
		getCmbLinea().setSelectedItem(null);
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	public void refresh() {
		cargarCombos();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void cargarCombos(){
		cargarComboModulo();
		cargarComboPlanCuenta();
		cargarComboLinea();
		cargarComboSuptipoAsiento();
	}
	
	private void cargarComboModulo(){
		List modulos = (List) GeneralFinder.findModulo();
		refreshCombo(getCmbModulo(),modulos);
	}

	private void cargarComboPlanCuenta(){
		List planesCuentas = (List)ContabilidadFinder.findPlanCuentaActivo(Parametros.getIdEmpresa());
		refreshCombo(getCmbPlanCuenta(),planesCuentas);
		if (getCmbPlanCuenta().getSelectedItem() == null)
			PlanCuentaModel.seleccionarPlanCuentaPredeterminado(getCmbPlanCuenta());
	}

	private void cargarComboLinea(){
		try{
			List lineas = (List) SessionServiceLocator.getLineaSessionService().findLineaByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbLinea(),lineas);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarComboSuptipoAsiento() {
		try {
			List subtiposAsiento = (List) SessionServiceLocator.getSubTipoAsientoSessionService().findSubtipoAsientoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbSubtipoAsiento(), subtiposAsiento);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarTabla() {
		Collection eventoContable = ContabilidadFinder.findEventoContableByEmpresaId(Parametros.getIdEmpresa());
		Iterator eventoContableIterator = eventoContable.iterator();
		
		if(!getEventoContableVector().isEmpty()){
			getEventoContableVector().removeAllElements();
		}
		
		while (eventoContableIterator.hasNext()) {
			EventoContableIf eventoContableIf = (EventoContableIf) eventoContableIterator.next();
			
			tableModel = (DefaultTableModel) getTblEventoContable().getModel();
			Vector<String> fila = new Vector<String>();
			getEventoContableVector().add(eventoContableIf);
			
			agregarFilasTabla(eventoContableIf, fila);
			
			tableModel.addRow(fila);
		}
		Utilitarios.scrollToCenter(getTblEventoContable(), eventoContable, 0);
	}
	
	private void agregarFilasTabla(EventoContableIf eventoContableIf, Vector<String> fila){
		
		fila.add(eventoContableIf.getCodigo());
		fila.add(eventoContableIf.getNombre());
		
		try {
			ModuloIf modulo = SessionServiceLocator.getModuloSessionService().getModulo(eventoContableIf.getModuloId());
			fila.add(modulo.getNombre());
			
			if(eventoContableIf.getDocumentoId() != null){
				DocumentoIf documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(eventoContableIf.getDocumentoId());
				fila.add(documento.getNombre());
			} else
				fila.add("");
			
			if(eventoContableIf.getLineaId() != null){
				LineaIf linea = SessionServiceLocator.getLineaSessionService().getLinea(eventoContableIf.getLineaId());
				fila.add(linea.getNombre());
			} else
				fila.add("");
			
			if (eventoContableIf.getEtapa() != null)
				fila.add(eventoContableIf.getEtapa().toString());
			else 
				fila.add("");
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public Vector getEventoContableVector() {
		return  eventoContableVector;
	}
	
	public void setEventoContableVector(Vector  eventoContableVec) {
		 eventoContableVector = eventoContableVec;
	}
	
	public int getEventoContableSeleccionado() {
		return eventoContableSeleccionado;
	}
	
	public void setEventoContableSeleccionado(int selectedEventoContable) {
		eventoContableSeleccionado = selectedEventoContable;
	}

	public EventoContableIf getEventoContableActualizadoIf() {
		return eventoContableIf;
	}
	
	public void setEventoContableActualizadoIf(EventoContableIf eventoContableIf) {
		this.eventoContableIf = eventoContableIf;
	}
	
	 
}
