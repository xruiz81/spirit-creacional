package com.spirit.medios.gui.model;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.medios.entity.EquipoEmpleadoData;
import com.spirit.medios.entity.EquipoEmpleadoIf;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.gui.panel.JPEmpleadoEquipo;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.Utilitarios;

public class EmpleadoEquipoModel extends JPEmpleadoEquipo {
	private DefaultTableModel tableModel;
	private Vector equipoEmpleadoVector = new Vector();
	private int equipoEmpleadoSeleccionado;
	private EquipoEmpleadoIf equipoEmpleadoActualizadoIf;
	private EmpleadoIf empleadoIf;
	private JDPopupFinderModel popupFinder;
	private EmpleadoCriteria empleadoCriteria;
	private static final String SI_ES_JEFE = "S";
	private static final String NO_ES_JEFE = "N";
	
	public EmpleadoEquipoModel() {
		initKeyListeners();
		setSorterTable(getTblEmpleadoEquipo());
		anchoColumnasTabla();
		cargarCombos();
		initListeners();
		getTblEmpleadoEquipo().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		getTblEmpleadoEquipo().addMouseListener(oMouseAdapterTblEquipoEmpleado);
		getTblEmpleadoEquipo().addKeyListener(oKeyAdapterTblEquipoEmpleado);
		new HotKeyComponent(this);
	}
	
	public void initKeyListeners(){
		getTxtTipoOrden().setEditable(false);
		getBtnBuscarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarEmpleado().setToolTipText("Buscar Empleado");	
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblEmpleadoEquipo().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblEmpleadoEquipo().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblEmpleadoEquipo().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblEmpleadoEquipo().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(50);
	}
	
	private void cargarCombos() {
		cargarComboEquipoTrabajo();
		cargarComboRol();
	}
	
	private void initListeners() {
		getBtnBuscarEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarEmpleado();        
			}
		});
		
		getCmbEquipoTrabajo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					if(getCmbEquipoTrabajo().getSelectedItem() != null){
						EquipoTrabajoIf equipo = (EquipoTrabajoIf)getCmbEquipoTrabajo().getSelectedItem();
						TipoOrdenIf tipoOrden = SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(equipo.getTipoordenId());
						getTxtTipoOrden().setText(tipoOrden.getNombre());
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	MouseListener oMouseAdapterTblEquipoEmpleado = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			 enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblEquipoEmpleado = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void buscarEmpleado() {
		empleadoCriteria = new EmpleadoCriteria("Empleados",Parametros.getIdEmpresa());
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if ( popupFinder.getElementoSeleccionado()!=null ){
			empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
			getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
		}
	}
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		try {
			if (((JTable)evt.getSource()).getSelectedRow() != -1) {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setEquipoEmpleadoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
				equipoEmpleadoActualizadoIf = (EquipoEmpleadoIf) getEquipoEmpleadoVector().get(getEquipoEmpleadoSeleccionado());
				empleadoIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(equipoEmpleadoActualizadoIf.getEmpleadoId());
				getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
				
				if (getEquipoEmpleadoActualizadoIf().getEquipoId() != null){
					getCmbEquipoTrabajo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbEquipoTrabajo(), getEquipoEmpleadoActualizadoIf().getEquipoId()));
				}else{
					getCmbEquipoTrabajo().setSelectedItem(null);
				}
				
				getCmbEquipoTrabajo().validate();
				getCmbEquipoTrabajo().repaint();
				
				if (getEquipoEmpleadoActualizadoIf().getRol() != null) {
					Iterator tipoEmpleadoIterator = SessionServiceLocator.getTipoEmpleadoSessionService().findTipoEmpleadoByCodigo(getEquipoEmpleadoActualizadoIf().getRol()).iterator();
					if (tipoEmpleadoIterator.hasNext()) {
						TipoEmpleadoIf tipoEmpleado = (TipoEmpleadoIf) tipoEmpleadoIterator.next();
						getCmbRol().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRol(), tipoEmpleado.getId()));
					}
				}else{
					getCmbRol().setSelectedItem(null);
				}
				
				if(getEquipoEmpleadoActualizadoIf().getJefe().equals(SI_ES_JEFE)){
					getCbJefe().setSelected(true);
				}else{
					getCbJefe().setSelected(false);
				}
				
				getCmbRol().validate();
				getCmbRol().repaint();
				showUpdateMode();
			}
		} catch (GenericBusinessException e ) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				EquipoEmpleadoData data = new EquipoEmpleadoData();
				data.setEquipoId(((EquipoTrabajoIf) getCmbEquipoTrabajo().getSelectedItem()).getId());
				data.setEmpleadoId(empleadoIf.getId());
				data.setRol(((TipoEmpleadoIf) getCmbRol().getSelectedItem()).getCodigo());
				
				if(getCbJefe().isSelected()){
					data.setJefe(SI_ES_JEFE);
				}else{
					data.setJefe(NO_ES_JEFE);
				}
				
				SessionServiceLocator.getEquipoEmpleadoSessionService().addEquipoEmpleado(data);
				this.showSaveMode();
				SpiritAlert.createAlert("Datos guardados con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {	
			if (validateFields()) {
				setEquipoEmpleadoActualizadoIf((EquipoEmpleadoIf) getEquipoEmpleadoVector().get(getEquipoEmpleadoSeleccionado()));
				getEquipoEmpleadoActualizadoIf().setEquipoId(((EquipoTrabajoIf) getCmbEquipoTrabajo().getSelectedItem()).getId());
				getEquipoEmpleadoActualizadoIf().setEmpleadoId(empleadoIf.getId());
				getEquipoEmpleadoActualizadoIf().setRol(((TipoEmpleadoIf) getCmbRol().getSelectedItem()).getCodigo());
				
				if(getCbJefe().isSelected()){
					getEquipoEmpleadoActualizadoIf().setJefe(SI_ES_JEFE);
				}else{
					getEquipoEmpleadoActualizadoIf().setJefe(NO_ES_JEFE);
				}
				
				this.clean();
				getEquipoEmpleadoVector().setElementAt(getEquipoEmpleadoActualizadoIf(), getEquipoEmpleadoSeleccionado());
				SessionServiceLocator.getEquipoEmpleadoSessionService().saveEquipoEmpleado(getEquipoEmpleadoActualizadoIf());
				setEquipoEmpleadoActualizadoIf(null);
				this.showSaveMode();				
				SpiritAlert.createAlert("Datos actualizados con éxito", SpiritAlert.INFORMATION);
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			EquipoEmpleadoIf equipoEmpleadoIf = (EquipoEmpleadoIf) getEquipoEmpleadoVector().get(getEquipoEmpleadoSeleccionado());
			SessionServiceLocator.getEquipoEmpleadoSessionService().eliminarEquipoEmpleado(equipoEmpleadoIf.getId(), Parametros.getUsuario());
			this.showSaveMode();
			SpiritAlert.createAlert("Datos eliminados con éxito", SpiritAlert.INFORMATION);
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
		getTxtEmpleado().setText("");
		empleadoIf = null;
		getCmbEquipoTrabajo().setSelectedItem(null);
		getCmbRol().setSelectedItem(null);
		
		DefaultTableModel model = (DefaultTableModel) getTblEmpleadoEquipo().getModel();		
		for(int i= this.getTblEmpleadoEquipo().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		getBtnBuscarEmpleado().setEnabled(true);
		getTxtEmpleado().setEditable(false);
		getCmbEquipoTrabajo().setEnabled(true);
		getCmbRol().setEnabled(true);
		getCbJefe().setSelected(false);
		clean();
		cargarTabla();		
		getBtnBuscarEmpleado().grabFocus();
	}

	private void cargarTabla() {
		try {
			Collection equipoEmpleados = SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			if (!equipoEmpleados.isEmpty()) {
				Iterator equipoEmpleadosIterator = equipoEmpleados.iterator();
				
				if(!getEquipoEmpleadoVector().isEmpty()){
					getEquipoEmpleadoVector().removeAllElements();
				}
				
				while (equipoEmpleadosIterator.hasNext()) {
					EquipoEmpleadoIf equipoEmpleadoIf = (EquipoEmpleadoIf) equipoEmpleadosIterator.next();
					tableModel = (DefaultTableModel) getTblEmpleadoEquipo().getModel();
					Vector<String> fila = new Vector<String>();
					getEquipoEmpleadoVector().add(equipoEmpleadoIf);
					agregarColumnasTabla(equipoEmpleadoIf, fila);
					tableModel.addRow(fila);
				}
				Utilitarios.scrollToCenter(getTblEmpleadoEquipo(), equipoEmpleados, 0);
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(EquipoEmpleadoIf equipoEmpleadoIf, Vector<String> fila) {
		try {
			EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(equipoEmpleadoIf.getEmpleadoId());
			fila.add(empleado.getNombres() + " " + empleado.getApellidos());
			
			if (equipoEmpleadoIf.getEquipoId() != null) {
				EquipoTrabajoIf equipoTrabajo = SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajo(equipoEmpleadoIf.getEquipoId());
				fila.add(equipoTrabajo.getCodigo());
			} else {
				fila.add("");
			}
			
			if (equipoEmpleadoIf.getRol() != null) {
				Iterator rolIterator = SessionServiceLocator.getTipoEmpleadoSessionService().findTipoEmpleadoByCodigo(equipoEmpleadoIf.getRol()).iterator();
				if (rolIterator.hasNext()) {
					TipoEmpleadoIf tipoEmpleado = (TipoEmpleadoIf) rolIterator.next();
					fila.add(tipoEmpleado.getNombre());
				}
			} else {
				fila.add("");
			}
			
			if(equipoEmpleadoIf.getJefe().equals(NO_ES_JEFE)){
				fila.add("NO");				
			}else if (equipoEmpleadoIf.getJefe().equals(SI_ES_JEFE)){
				fila.add("SI");	
			}else{
				fila.add("");
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}			
	}
	
	public boolean validateFields() {
		if (empleadoIf == null) {
			SpiritAlert.createAlert("Debe ingresar un empleado !!", SpiritAlert.WARNING);
			getBtnBuscarEmpleado().grabFocus();
			return false;
		}

		if (getCmbEquipoTrabajo().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un equipo de trabajo !!", SpiritAlert.WARNING);
			getCmbEquipoTrabajo().grabFocus();
			return false;
		}
		
		if (getCmbRol().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un rol !!", SpiritAlert.WARNING);
			getCmbRol().grabFocus();
			return false;
		}
				
		return true;
	}
	
	public Vector getEquipoEmpleadoVector() {
		return this.equipoEmpleadoVector;
	}
	
	public void setEquipoEmpleadoVector(Vector equipoEmpleadoVector) {
		this.equipoEmpleadoVector = equipoEmpleadoVector;
	}
	
	public int getEquipoEmpleadoSeleccionado() {
		return this.equipoEmpleadoSeleccionado;
	}
	
	public void setEquipoEmpleadoSeleccionado(int equipoEmpleadoSeleccionado) {
		this.equipoEmpleadoSeleccionado = equipoEmpleadoSeleccionado;
	}
	
	public EquipoEmpleadoIf getEquipoEmpleadoActualizadoIf() {
		return this.equipoEmpleadoActualizadoIf;
	}
	
	public void setEquipoEmpleadoActualizadoIf(EquipoEmpleadoIf equipoEmpleadoActualizadoIf) {
		this.equipoEmpleadoActualizadoIf = equipoEmpleadoActualizadoIf;
	}
	
	private void cargarComboEquipoTrabajo(){
		try {
			Map aMap = new HashMap();
			aMap.put("empresaId", Parametros.getIdEmpresa());
			aMap.put("estado", "A");
			List equiposTrabajo = (List) SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByQuery(aMap);
			Collections.sort(equiposTrabajo, ordenadorArrayListPorCodigo);
			refreshCombo(this.getCmbEquipoTrabajo(), equiposTrabajo);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	Comparator<EquipoTrabajoIf> ordenadorArrayListPorCodigo = new Comparator<EquipoTrabajoIf>(){
		public int compare(EquipoTrabajoIf o1, EquipoTrabajoIf o2) {
			return (o1.getCodigo().compareTo(o2.getCodigo()));
		}		
	};
	
	private void cargarComboRol() {
		try {
			List roles = (List) SessionServiceLocator.getTipoEmpleadoSessionService().findTipoEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Collections.sort(roles, ordenadorArrayListPorNombre);
			refreshCombo(this.getCmbRol(), roles);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	Comparator<TipoEmpleadoIf> ordenadorArrayListPorNombre = new Comparator<TipoEmpleadoIf>(){
		public int compare(TipoEmpleadoIf o1, TipoEmpleadoIf o2) {
			return (o1.getNombre().compareTo(o2.getNombre()));
		}		
	};
	
}
