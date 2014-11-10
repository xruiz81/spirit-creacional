package com.spirit.contabilidad.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
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
import com.spirit.contabilidad.entity.PlanCuentaData;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.gui.panel.JPPlanCuenta;
import com.spirit.contabilidad.session.PlanCuentaSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.session.MonedaSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class PlanCuentaModel extends JPPlanCuenta {

	private static final long serialVersionUID = 3618701906990872114L;
	
	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final int MAX_LONGITUD_MASCARA = 50;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	private static final String NOMBRE_OPCION_SI = "SI";
	private static final String NOMBRE_OPCION_NO = "NO";
	private static final String OPCION_SI = NOMBRE_OPCION_SI.substring(0,1);
	private static final String OPCION_NO = NOMBRE_OPCION_NO.substring(0,1);
	
	private Vector planCuentaVector = new Vector();
	private int planCuentaSeleccionado;
	private PlanCuentaIf planCuentaActualizadoIf;
	private DefaultTableModel tableModel;

	Calendar calendar = new GregorianCalendar();
	java.sql.Date fechaCreacion = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
	
	public PlanCuentaModel() {
		initKeyListeners();
		setSorterTable(getTblPlanCuenta());
		getTblPlanCuenta().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		setColumnWidthTable();
		this.getTblPlanCuenta().addMouseListener(oMouseAdapterTblPlanCuenta);
		this.getTblPlanCuenta().addKeyListener(oKeyAdapterTblPlanCuenta);	 
		new HotKeyComponent(this);
	}
	
	private void setColumnWidthTable() {

		TableColumn anchoColumna = getTblPlanCuenta().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(10);
		
		anchoColumna = getTblPlanCuenta().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(160);
		    
		anchoColumna = getTblPlanCuenta().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(100);
		
		anchoColumna = getTblPlanCuenta().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(220);
		
		anchoColumna = getTblPlanCuenta().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(20);
		
		anchoColumna = getTblPlanCuenta().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(20);
		
	}
	
	MouseListener oMouseAdapterTblPlanCuenta = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblPlanCuenta = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setPlanCuentaSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			planCuentaActualizadoIf = (PlanCuentaIf) getPlanCuentaVector().get(getPlanCuentaSeleccionado());
			getTxtCodigo().setText(getPlanCuentaActualizadoIf().getCodigo());
			getTxtNombre().setText(getPlanCuentaActualizadoIf().getNombre());
			getTxtFecha().setText(Utilitarios.getFechaUppercase(getPlanCuentaActualizadoIf().getFecha()));
			getCmbMoneda().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbMoneda(), getPlanCuentaActualizadoIf().getMonedaId()));
			getCmbMoneda().repaint();
			
			if (ESTADO_ACTIVO.equals(getPlanCuentaActualizadoIf().getEstado()))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			else
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			
			if (OPCION_SI.equals(getPlanCuentaActualizadoIf().getPredeterminado()))
				getCmbPredeterminado().setSelectedItem(NOMBRE_OPCION_SI);
			else if (OPCION_NO.equals(getPlanCuentaActualizadoIf().getPredeterminado()))
				getCmbPredeterminado().setSelectedItem(NOMBRE_OPCION_NO);
			
			getTxtMascara().setText(getPlanCuentaActualizadoIf().getMascara());
			showUpdateMode();
		}
	}

	private ArrayList getModel(ArrayList listaPlanCuenta) {
		ArrayList data = new ArrayList();
		Iterator it = listaPlanCuenta.iterator();
		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			PlanCuentaIf bean = (PlanCuentaIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());
			data.add(fila);
		}
		return data;
	}

	public void save() {
		try {
			if (validateFields()) {
				PlanCuentaData data = new PlanCuentaData();
				data.setCodigo(this.getTxtCodigo().getText().toUpperCase());
				data.setNombre(this.getTxtNombre().getText().toUpperCase());
				data.setEmpresaId(Parametros.getIdEmpresa());
				data.setFecha(fechaCreacion);
				data.setMonedaId(((MonedaIf) this.getCmbMoneda().getSelectedItem()).getId());
				data.setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));
				data.setMascara(this.getTxtMascara().getText());
				data.setPredeterminado(this.getCmbPredeterminado().getSelectedItem().toString().substring(0,1));
				SessionServiceLocator.getPlanCuentaSessionService().addPlanCuenta(data);
				SpiritCache.setObject("plancuenta",null);
				this.clean();
				this.showSaveMode();
				SpiritAlert.createAlert("Plan de Cuenta guardado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setPlanCuentaActualizadoIf((PlanCuentaIf) getPlanCuentaVector().get(getPlanCuentaSeleccionado()));
				getPlanCuentaActualizadoIf().setCodigo(getTxtCodigo().getText());
				getPlanCuentaActualizadoIf().setNombre(getTxtNombre().getText());
				getPlanCuentaActualizadoIf().setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));
				getPlanCuentaActualizadoIf().setMascara(getTxtMascara().getText());
				getPlanCuentaActualizadoIf().setMonedaId(((MonedaIf) getCmbMoneda().getSelectedItem()).getId());
				getPlanCuentaActualizadoIf().setPredeterminado(this.getCmbPredeterminado().getSelectedItem().toString().substring(0,1));
				getPlanCuentaVector().setElementAt(getPlanCuentaActualizadoIf(), getPlanCuentaSeleccionado());
				SessionServiceLocator.getPlanCuentaSessionService().savePlanCuenta(getPlanCuentaActualizadoIf());
				setPlanCuentaActualizadoIf(null);	
				this.showSaveMode();
				SpiritAlert.createAlert("Plan de Cuenta actualizado con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}		
	}

	public void delete() {
		try {
			PlanCuentaIf planCuentaIf = (PlanCuentaIf) getPlanCuentaVector().get(getPlanCuentaSeleccionado());
			SessionServiceLocator.getPlanCuentaSessionService().deletePlanCuenta(planCuentaIf.getId());
			this.showSaveMode();
			SpiritAlert.createAlert("Plan de Cuenta eliminado con éxito", SpiritAlert.INFORMATION);
		} 
		catch (Exception e) {
			e.printStackTrace();
			this.showSaveMode();
			SpiritAlert.createAlert("El Plan de Cuenta tiene información relacionada y no puede eliminarse", SpiritAlert.ERROR);
		}
	}

	private void agregarColumnasTabla(PlanCuentaIf planCuentaIf, Vector<String> fila){
		fila.add(planCuentaIf.getCodigo());
		fila.add(planCuentaIf.getNombre());
		
		try {
			fila.add(SessionServiceLocator.getMonedaSessionService().getMoneda(planCuentaIf.getMonedaId()).getNombre());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		fila.add(planCuentaIf.getMascara());
		
		if(ESTADO_ACTIVO.equals(planCuentaIf.getEstado()))
			fila.add(NOMBRE_ESTADO_ACTIVO);
		else if(ESTADO_INACTIVO.equals(planCuentaIf.getEstado()))
			fila.add(NOMBRE_ESTADO_INACTIVO);
		
		if(OPCION_SI.equals(planCuentaIf.getPredeterminado()))
			fila.add(NOMBRE_OPCION_SI);
		else if(OPCION_NO.equals(planCuentaIf.getPredeterminado()))
			fila.add(NOMBRE_OPCION_NO);	
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtMascara().addKeyListener(new TextChecker(MAX_LONGITUD_MASCARA));
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtNombre().getText())
				&& "".equals(this.getTxtFecha().getText())
				&& (this.getCmbMoneda().getSelectedItem() == null)
				&& (this.getCmbEstado().getSelectedItem() == null)
				&& "".equals(this.getTxtMascara().getText())
				&& (this.getCmbPredeterminado().getSelectedItem() == null)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateFields() {

		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		String strFecha = this.getTxtFecha().getText();
		MonedaIf moneda = (MonedaIf) this.getCmbMoneda().getSelectedItem();
		String strEstado = (String) this.getCmbEstado().getSelectedItem();
		String strMascara = this.getTxtMascara().getText();
		String strPredeterminado = (String) this.getCmbPredeterminado().getSelectedItem();

		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para el Plan de Cuentas !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		Collection planCuentas = null;
		boolean codigoRepetido = false;
		boolean predeterminadoExiste = false;
		
		try {
			planCuentas = SessionServiceLocator.getPlanCuentaSessionService().findPlanCuentaByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		Iterator planCuentasIt = planCuentas.iterator();
		
		while (planCuentasIt.hasNext()) {
			PlanCuentaIf planCuentaIf = (PlanCuentaIf) planCuentasIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE) {
				if (strCodigo.equals(planCuentaIf.getCodigo()))				
					codigoRepetido = true;
				
				if (strPredeterminado.equals(NOMBRE_OPCION_SI) && planCuentaIf.getPredeterminado().equals(OPCION_SI))
					predeterminadoExiste = true;
			}
			
			if (this.getMode() == SpiritMode.UPDATE_MODE) {
				if (strCodigo.equals(planCuentaIf.getCodigo()))
					if (planCuentaActualizadoIf.getId().equals(planCuentaIf.getId()) == false)
						codigoRepetido = true;
					
				if (strPredeterminado.equals(NOMBRE_OPCION_SI) && planCuentaIf.getPredeterminado().equals(OPCION_SI))
					if (planCuentaActualizadoIf.getId().equals(planCuentaIf.getId()) == false)
						predeterminadoExiste = true;
			}
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Plan de Cuenta está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Plan de Cuentas !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}

		if ((("".equals(strFecha)) || (strFecha == null))) {
			SpiritAlert.createAlert("Debe ingresar una fecha para el Plan de Cuentas !!", SpiritAlert.WARNING);
			getTxtFecha().grabFocus();
			return false;
		}

		if (moneda == null) {
			SpiritAlert.createAlert("Debe seleccionar una moneda para el Plan de Cuentas !!", SpiritAlert.WARNING);
			getCmbMoneda().grabFocus();
			return false;
		}
		if ((("".equals(strEstado)) || (strEstado == null))) {
			SpiritAlert.createAlert("Debe seleccionar el estado ACTIVO/INACTIVO para el Plan de Cuentas !!", SpiritAlert.WARNING);
			getCmbEstado().grabFocus();
			return false;
		}
		if ((("".equals(strMascara)) || (strMascara == null))) {
			SpiritAlert.createAlert("Debe ingresar una máscara para el Plan de Cuentas !!", SpiritAlert.WARNING);
			getTxtMascara().grabFocus();
			return false;
		}
		
		if (predeterminadoExiste) {
			SpiritAlert.createAlert("Ya existe un plan de cuentas predeterminado !!", SpiritAlert.WARNING);
			getCmbPredeterminado().grabFocus();
			return false;
		}

		return true;

	}

	private void cargarTabla() {
		try {			
			Collection planCuenta = SessionServiceLocator.getPlanCuentaSessionService().findPlanCuentaByEmpresaId(Parametros.getIdEmpresa()); 
			Iterator planCuentaIterator = planCuenta.iterator();
			
			if(!getPlanCuentaVector().isEmpty()){
				getPlanCuentaVector().removeAllElements();
			}
			
			while (planCuentaIterator.hasNext()) {
				PlanCuentaIf planCuentaIf = (PlanCuentaIf) planCuentaIterator.next();
				
				tableModel = (DefaultTableModel) getTblPlanCuenta().getModel();
				Vector<String> fila = new Vector<String>();
				getPlanCuentaVector().add(planCuentaIf);
				
				agregarColumnasTabla(planCuentaIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblPlanCuenta(), planCuenta, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboMoneda(){
		try {
			List monedas = (List)SessionServiceLocator.getMonedaSessionService().getMonedaList();
			refreshCombo(getCmbMoneda(), monedas);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getTxtFecha().setText("");
		this.getTxtMascara().setText("");

		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblPlanCuenta().getModel();
		
		for(int i= this.getTblPlanCuenta().getRowCount();i>0;--i)
			model.removeRow(i-1);

	}

	public void showSaveMode() {
		
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtFecha().setEnabled(false);

		getCmbMoneda().setEnabled(true);
		/*SpiritComboBoxModel cmbModel = new SpiritComboBoxModel(GeneralFinder.findMoneda());
		getCmbMoneda().setModel(cmbModel);*/
		cargarComboMoneda();

		getCmbEstado().setEnabled(true);
		
		if (getCmbEstado().getItemCount()>0)
			getCmbEstado().removeAllItems();
		
		getCmbEstado().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbEstado().addItem(NOMBRE_ESTADO_INACTIVO);

		getTxtMascara().setEnabled(true);
		
		getCmbPredeterminado().setEnabled(true);
		
		clean();
		getTxtFecha().setText(Utilitarios.fechaAhora());
		cargarTabla();
		
		getTxtCodigo().grabFocus();
		
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getTxtFecha().setEnabled(false);
		getCmbEstado().setEnabled(true);
		getTxtMascara().setEnabled(true);
		getCmbPredeterminado().setEnabled(true);
		getTblPlanCuenta().grabFocus();
	}
	
	public void refresh(){
		cargarComboMoneda();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public Vector getPlanCuentaVector() {
		return this.planCuentaVector;
	}
	
	public void setPlanCuentaVector(Vector planCuentaVector) {
		this.planCuentaVector = planCuentaVector;
	}
	
	public int getPlanCuentaSeleccionado() {
		return this.planCuentaSeleccionado;
	}
	
	public void setPlanCuentaSeleccionado(int planCuentaSeleccionado) {
		this.planCuentaSeleccionado = planCuentaSeleccionado;
	}
	
	public PlanCuentaIf getPlanCuentaActualizadoIf() {
		return this.planCuentaActualizadoIf;
	}
	
	public void setPlanCuentaActualizadoIf(PlanCuentaIf planCuentaActualizadoIf) {
		this.planCuentaActualizadoIf = planCuentaActualizadoIf;
	}
	
	public static void seleccionarPlanCuentaPredeterminado(JComboBox cmbPlanCuenta) {
		for (int i=0; i<cmbPlanCuenta.getItemCount(); i++) {
			PlanCuentaIf planCuentaIf = (PlanCuentaIf) cmbPlanCuenta.getItemAt(i);
			if (planCuentaIf.getPredeterminado().equals("S")) {
				cmbPlanCuenta.setSelectedIndex(i);
				cmbPlanCuenta.validate();
				cmbPlanCuenta.repaint();
				break;
			}
		}
	}
	
	 
}
