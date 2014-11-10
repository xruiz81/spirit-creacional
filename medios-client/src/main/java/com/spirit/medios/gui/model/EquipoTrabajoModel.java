package com.spirit.medios.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
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
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.medios.entity.EquipoTrabajoData;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.gui.panel.JPEquipoTrabajo;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class EquipoTrabajoModel extends JPEquipoTrabajo {
	
	private static final long serialVersionUID = 6287782264755998176L;
	
	private static int MAX_LONGITUD_CODIGO = 10;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	private Calendar calendarFechaInicio = new GregorianCalendar();
	private Calendar calendarFechaFin = new GregorianCalendar();
	private Vector equipoTrabajoVector = new Vector();
	private int equipoTrabajoSeleccionado;
	private EquipoTrabajoIf equipoTrabajoActualizadoIf;
	private DefaultTableModel tableModel;
	private DecimalFormat formatoEntero = new DecimalFormat("000");
	
	public EquipoTrabajoModel(){
		anchoColumnasTabla();
		initKeyListeners();
		setSorterTable(getTblEquipoTrabajo());
		cargarCombos();
		showSaveMode();
		getTblEquipoTrabajo().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblEquipoTrabajo().addMouseListener(oMouseAdapterTblEquipoTrabajo);
		getTblEquipoTrabajo().addKeyListener(oKeyAdapterTblEquipoTrabajo);
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblEquipoTrabajo().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblEquipoTrabajo().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblEquipoTrabajo().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblEquipoTrabajo().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblEquipoTrabajo().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().setEditable(false);
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		reiniciarCombosFecha();
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
	}

	private void reiniciarCombosFecha() {
		Calendar calendar = new GregorianCalendar();
		getCmbFechaInicio().setCalendar(calendar);
		getCmbFechaFin().setCalendar(calendar);
	}
	
	public void cargarCombos() {
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		cargarComboTipoOrden();
	}
	
	MouseListener oMouseAdapterTblEquipoTrabajo = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblEquipoTrabajo = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setEquipoTrabajoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			equipoTrabajoActualizadoIf = (EquipoTrabajoIf) getEquipoTrabajoVector().get(getEquipoTrabajoSeleccionado());
			getTxtCodigo().setText(equipoTrabajoActualizadoIf.getCodigo());
			getCmbTipoOrden().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoOrden(), equipoTrabajoActualizadoIf.getTipoordenId()));
			getCmbTipoOrden().validate();
			getCmbTipoOrden().repaint();
			calendarFechaInicio.setTime(equipoTrabajoActualizadoIf.getFechaini());
			getCmbFechaInicio().setCalendar(calendarFechaInicio);
			getCmbFechaInicio().repaint();
			calendarFechaFin.setTime(equipoTrabajoActualizadoIf.getFechafin());
			getCmbFechaFin().setCalendar(calendarFechaFin);
			getCmbFechaFin().repaint();
			
			if (equipoTrabajoActualizadoIf.getEstado().equals(ESTADO_ACTIVO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			else if (equipoTrabajoActualizadoIf.getEstado().equals(ESTADO_INACTIVO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			
			showUpdateMode();
		}
	}
	
	private EquipoTrabajoData registrarEquipoTrabajo() {
		EquipoTrabajoData data = new EquipoTrabajoData();
		//data.setCodigo(getTxtCodigo().getText());
		try {
			TipoOrdenIf tipoOrden = (TipoOrdenIf) getCmbTipoOrden().getSelectedItem();
			String maxCodigo = SessionServiceLocator.getEquipoTrabajoSessionService().getMaximoCodigoEquipoTrabajo(Parametros.getIdEmpresa(), "EQ"+tipoOrden.getCodigo());
			if(!maxCodigo.equals("")){
				int codigo = Integer.valueOf(maxCodigo) + 1;
				data.setCodigo("EQ"+tipoOrden.getCodigo()+formatoEntero.format(codigo));
			}else{
				data.setCodigo("EQ"+tipoOrden.getCodigo()+"001");
			}
			
			data.setTipoordenId(tipoOrden.getId());
			data.setFechaini(new java.sql.Date(getCmbFechaInicio().getDate().getTime()));
			data.setFechafin(new java.sql.Date(getCmbFechaFin().getDate().getTime()));
			
			if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO))
				data.setEstado(ESTADO_ACTIVO);
			else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO))
				data.setEstado(ESTADO_INACTIVO);
			
			data.setEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	private void registrarEquipoTrabajoForUpdate() {
		try {
			setEquipoTrabajoActualizadoIf((EquipoTrabajoIf) getEquipoTrabajoVector().get(getEquipoTrabajoSeleccionado()));				
			//getEquipoTrabajoActualizadoIf().setCodigo(getTxtCodigo().getText());		
			TipoOrdenIf tipoOrden = (TipoOrdenIf) getCmbTipoOrden().getSelectedItem();
			if(getEquipoTrabajoActualizadoIf().getTipoordenId().compareTo(tipoOrden.getId()) != 0){
				String maxCodigo = SessionServiceLocator.getEquipoTrabajoSessionService().getMaximoCodigoEquipoTrabajo(Parametros.getIdEmpresa(), "EQ"+tipoOrden.getCodigo());
				if(!maxCodigo.equals("")){
					int codigo = Integer.valueOf(maxCodigo) + 1;
					getEquipoTrabajoActualizadoIf().setCodigo("EQ"+tipoOrden.getCodigo()+formatoEntero.format(codigo));
				}else{
					getEquipoTrabajoActualizadoIf().setCodigo("EQ"+tipoOrden.getCodigo()+"001");
				}
			}
			
			getEquipoTrabajoActualizadoIf().setTipoordenId(((TipoOrdenIf) getCmbTipoOrden().getSelectedItem()).getId());
			getEquipoTrabajoActualizadoIf().setFechaini(new java.sql.Date(getCmbFechaInicio().getDate().getTime()));
			getEquipoTrabajoActualizadoIf().setFechafin(new java.sql.Date(getCmbFechaFin().getDate().getTime()));
			if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO))
				getEquipoTrabajoActualizadoIf().setEstado(ESTADO_ACTIVO);
			else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO))
				getEquipoTrabajoActualizadoIf().setEstado(ESTADO_INACTIVO);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				EquipoTrabajoData data = registrarEquipoTrabajo();				
				SessionServiceLocator.getEquipoTrabajoSessionService().addEquipoTrabajo(data);
				SpiritAlert.createAlert("Equipo de Trabajo guardado con éxito !", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {
			if (validateFields()) {
				registrarEquipoTrabajoForUpdate();				
				getEquipoTrabajoVector().setElementAt(getEquipoTrabajoActualizadoIf(), getEquipoTrabajoSeleccionado());
				SessionServiceLocator.getEquipoTrabajoSessionService().saveEquipoTrabajo(getEquipoTrabajoActualizadoIf());
				setEquipoTrabajoActualizadoIf(null);
				showSaveMode();
				SpiritAlert.createAlert("Equipo de Trabajo actualizado con éxito !", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			EquipoTrabajoIf equipoTrabajoIf = (EquipoTrabajoIf) getEquipoTrabajoVector().get(getEquipoTrabajoSeleccionado());
			SessionServiceLocator.getEquipoTrabajoSessionService().deleteEquipoTrabajo(equipoTrabajoIf.getId());
			showSaveMode();
			SpiritAlert.createAlert("Equipo de Trabajo eliminado con éxito !", SpiritAlert.INFORMATION);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
		}
	}
	
	public void refresh(){
		cargarComboTipoOrden();
	}

	public void clean() {
		getTxtCodigo().setText("");
		getCmbTipoOrden().setSelectedItem(null);
		reiniciarCombosFecha();
		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
		cleanTablaEquipoTrabajo();
	}
	
	public void cleanTablaEquipoTrabajo() {
		DefaultTableModel model = (DefaultTableModel) getTblEquipoTrabajo().getModel();
		for (int i = this.getTblEquipoTrabajo().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	private void cargarTabla() {
		cleanTablaEquipoTrabajo();
		try {
			Collection equipoTrabajos = equipoTrabajos = SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByEmpresaId(Parametros.getIdEmpresa());
			Iterator equipoTrabajosIterator = equipoTrabajos.iterator();

			if (!getEquipoTrabajoVector().isEmpty())
				getEquipoTrabajoVector().removeAllElements();

			while (equipoTrabajosIterator.hasNext()) {
				EquipoTrabajoIf equipoTrabajosIf = (EquipoTrabajoIf) equipoTrabajosIterator.next();
				tableModel = (DefaultTableModel) getTblEquipoTrabajo().getModel();
				Vector<String> fila = new Vector<String>();
				getEquipoTrabajoVector().add(equipoTrabajosIf);				
				agregarColumnasTabla(equipoTrabajosIf, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblEquipoTrabajo(), equipoTrabajos, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void agregarColumnasTabla(EquipoTrabajoIf equipoTrabajosIf, Vector<String> fila) {
		try {
			fila.add(equipoTrabajosIf.getCodigo());
			TipoOrdenIf tipoOrden = SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(equipoTrabajosIf.getTipoordenId());
			fila.add(tipoOrden.getCodigo() + " - " + tipoOrden.getNombre());
			fila.add(Utilitarios.getFechaCortaUppercase(equipoTrabajosIf.getFechaini()));
			fila.add(Utilitarios.getFechaCortaUppercase(equipoTrabajosIf.getFechafin()));
			
			if(equipoTrabajosIf.getEstado().equals(ESTADO_ACTIVO))
				fila.add(NOMBRE_ESTADO_ACTIVO);
			else if(equipoTrabajosIf.getEstado().equals(ESTADO_INACTIVO))
				fila.add(NOMBRE_ESTADO_INACTIVO);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		String strCodigo = getTxtCodigo().getText();
		java.util.Date fechaInicio;
		java.util.Date fechaFin;
		fechaInicio = getCmbFechaInicio().getDate();
		fechaFin = getCmbFechaFin().getDate();
		
		/*if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		Collection equipoTrabajos = null;
		boolean codigoRepetido = false;

		try {
			equipoTrabajos = getEquipoTrabajoSessionService().findEquipoTrabajoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator equipoTrabajosIt = equipoTrabajos.iterator();

		while (equipoTrabajosIt.hasNext()) {
			EquipoTrabajoIf equipoTrabajosIf = (EquipoTrabajoIf) equipoTrabajosIt.next();

			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(equipoTrabajosIf.getCodigo()))
					codigoRepetido = true;

			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(equipoTrabajosIf.getCodigo()))
					if (getEquipoTrabajoActualizadoIf().getId().equals(equipoTrabajoActualizadoIf.getId()) == false)
						codigoRepetido = true;
		}

		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Equipo de Trabajo está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}*/
		
		if (getCmbTipoOrden().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un tipo de orden !!", SpiritAlert.WARNING);
			getCmbTipoOrden().grabFocus();
			return false;
		}
		
		if ((("".equals(getCmbFechaInicio().getSelectedItem())) || (getCmbFechaInicio().getSelectedItem() == null))) {
			SpiritAlert.createAlert("Debe seleccionar una fecha de inicio !!",	SpiritAlert.WARNING);
			getCmbFechaInicio().grabFocus();
			return false;
		}
		
		if ((("".equals(getCmbFechaFin().getSelectedItem())) || (getCmbFechaFin().getSelectedItem() == null))) {
			SpiritAlert.createAlert("Debe seleccionar una fecha de fin !!", SpiritAlert.WARNING);
			getCmbFechaFin().grabFocus();
			return false;
		}
		
		if (fechaInicio.after(fechaFin)) {
			SpiritAlert.createAlert("La fecha de inicio no puede ser posterior a la fecha de fin!", SpiritAlert.WARNING);
			getCmbFechaInicio().grabFocus();
			return false;
		}
		
		if ((("".equals(getCmbEstado().getSelectedItem())) || (getCmbEstado().getSelectedItem() == null))) {
			SpiritAlert.createAlert("Debe seleccionar un estado !!", SpiritAlert.WARNING);
			getCmbEstado().grabFocus();
			return false;
		}
		
		return true;
	}
	
	private void cargarComboTipoOrden(){
		try {
			List tipoOrden = (List) SessionServiceLocator.getTipoOrdenSessionService().findTipoOrdenByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTipoOrden(), tipoOrden);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	public EquipoTrabajoIf getEquipoTrabajoActualizadoIf() {
		return equipoTrabajoActualizadoIf;
	}

	public void setEquipoTrabajoActualizadoIf(EquipoTrabajoIf equipoTrabajoActualizadoIf) {
		this.equipoTrabajoActualizadoIf = equipoTrabajoActualizadoIf;
	}

	public int getEquipoTrabajoSeleccionado() {
		return equipoTrabajoSeleccionado;
	}

	public void setEquipoTrabajoSeleccionado(int equipoTrabajoSeleccionado) {
		this.equipoTrabajoSeleccionado = equipoTrabajoSeleccionado;
	}

	public Vector getEquipoTrabajoVector() {
		return equipoTrabajoVector;
	}

	public void setEquipoTrabajoVector(Vector equipoTrabajoVector) {
		this.equipoTrabajoVector = equipoTrabajoVector;
	}

}
