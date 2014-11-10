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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.medios.entity.Timetracker2Data;
import com.spirit.medios.entity.Timetracker2EmpleadoData;
import com.spirit.medios.entity.Timetracker2EmpleadoIf;
import com.spirit.medios.entity.Timetracker2If;
import com.spirit.medios.gui.panel.JPTimetracker2Actividades;
import com.spirit.util.TextChecker;

public class Timetracker2ActividadesModel extends JPTimetracker2Actividades {
	private static final long serialVersionUID = -6633355195363191951L;
	private EmpleadoCriteria empleadoCriteria;
	private JDPopupFinderModel popupFinder;
	protected EmpleadoIf empleadoIf;
	private List<Timetracker2If> actividadColeccion = new ArrayList<Timetracker2If>();
	private List<Timetracker2EmpleadoIf> empleadoColeccion = new ArrayList<Timetracker2EmpleadoIf>();
	private List<Timetracker2EmpleadoIf> empleadoEliminadoColeccion = new ArrayList<Timetracker2EmpleadoIf>();
	private DefaultTableModel modelActividad;
	private DefaultTableModel modelEmpleado;
	final JPopupMenu  popupMenuActividad = new JPopupMenu();
	final JPopupMenu  popupMenuEmpleado = new JPopupMenu();
	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 50;
	private int selectedRowTblActividad = -1;
	private int selectedRowTblEmpleado = -1;
	private Timetracker2If actividadSeleccionada;
	private String si = "Sí"; 
	private String no = "No"; 
	private Object[] options = {si, no}; 
	
	public Timetracker2ActividadesModel(){
		setSorterTable(getTblEmpleado());
		anchoColumnasTabla();
		initKeyListeners();
	    showSaveMode();
	    initListeners();
	    getTblActividad().addMouseListener(oMouseAdapterTblActividad);	    
		getTblActividad().addKeyListener(oKeyAdapterTblActividad);
	    getTblEmpleado().addMouseListener(oMouseAdapterTblEmpleado);	    
		getTblEmpleado().addKeyListener(oKeyAdapterTblEmpleado);
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumnaMarca = getTblActividad().getColumnModel().getColumn(0);
		anchoColumnaMarca.setPreferredWidth(80);
		anchoColumnaMarca = getTblActividad().getColumnModel().getColumn(1);
		anchoColumnaMarca.setPreferredWidth(250);
		anchoColumnaMarca = getTblActividad().getColumnModel().getColumn(2);
		anchoColumnaMarca.setPreferredWidth(100);
				
		TableColumn anchoColumnaProducto = getTblEmpleado().getColumnModel().getColumn(0);
		anchoColumnaProducto.setPreferredWidth(200);
		anchoColumnaProducto = getTblEmpleado().getColumnModel().getColumn(1);
		anchoColumnaProducto.setPreferredWidth(100);		
	}

	private void initKeyListeners() {
		getTxtCodigoActividad().setEditable(false);
		getTxtCodigoActividad().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtActividad().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
        getTxtEmpleado().setEditable(false);
        
		getBtnAgregarEmpleado().setText("");
		getBtnActualizarEmpleado().setText("");
		getBtnEliminarEmpleado().setText("");
		
		//---- btnBuscarEmpleado ----
		getBtnBuscarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarEmpleado().setToolTipText("Buscar Empleado");		
		//---- btnBorrarEmpleado
		getBtnBorrarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnBorrarEmpleado().setToolTipText("Borro el Empleado seleccionado");		
		
		//---- btnAgregarEmpleado ----
		getBtnAgregarEmpleado().setToolTipText("Agregar Empleado");
		getBtnAgregarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		//---- btnActualizarEmpleado ----
		getBtnActualizarEmpleado().setToolTipText("Actualizar Empleado");
		getBtnActualizarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		//---- btnEliminarEmpleado ----
		getBtnEliminarEmpleado().setToolTipText("Eliminar Empleado");
		getBtnEliminarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
	}
	
	public void initListeners(){
				
		// Manejo los eventos de Buscar Empleado
		getBtnBuscarEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoCriteria = new EmpleadoCriteria("Empleados",Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
										empleadoCriteria,
										JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
					getBtnBorrarEmpleado().setEnabled(true);
				}
			}
		});
		
		getBtnBorrarEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getTxtEmpleado().setText("");
				getBtnBorrarEmpleado().setEnabled(false);
				empleadoIf = null;
				getTxtEmpleado().grabFocus();
			}
		});
		
		//Opcion Que Permite Borrar un regitsro deseado de la tabla actividad
	    JMenuItem itemEliminarMarca = new JMenuItem("Eliminar Actividad");
	    popupMenuActividad.add(itemEliminarMarca);
	    	    		
		//Opcion Que Permite Borrar un regitsro deseado de la tabla empleado
	    JMenuItem itemEliminarProductoCliente = new JMenuItem("Eliminar Empleado");
	    popupMenuEmpleado.add(itemEliminarProductoCliente);
	    
	    //Añado el listener de menupopup
	    itemEliminarProductoCliente.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evento) {
	    		eliminarProductoCliente();
	    	}
	    });
	    
	    getBtnAgregarEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarProductoCliente();			
			}
		});
			
		getBtnActualizarEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarProductoCliente();			
			}
		});
		
		getBtnEliminarEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarProductoCliente();			
			}
		});
	}
	
	MouseListener oMouseAdapterTblActividad = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
            if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblEmpleado().getModel().getRowCount()>0)
            	popupMenuActividad.show(evt.getComponent(), evt.getX(), evt.getY());
            else
            	enableSelectedRowActividadForUpdate(evt);
        }
	};
	
	KeyListener oKeyAdapterTblActividad = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowActividadForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowActividadForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setSelectedRowTblActividad(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			actividadSeleccionada = (Timetracker2If) actividadColeccion.get(getSelectedRowTblActividad());
     			            	
     		getTxtCodigoActividad().setText(actividadSeleccionada.getCodigo());
			getTxtActividad().setText(actividadSeleccionada.getActividad());
        	
        	if ("I".equals(actividadSeleccionada.getEstado()))
				getCmbEstadoActividad().setSelectedItem("INACTIVO");
			else
				getCmbEstadoActividad().setSelectedItem("ACTIVO");
        	
        	cleanEmpleado();
        	getTxtActividadSeleccionada().setText(actividadSeleccionada.getActividad());
        	findEmpleados();
        	showUpdateMode();
		}
	}
	
	MouseListener oMouseAdapterTblEmpleado = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
            if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblEmpleado().getModel().getRowCount()>0)
            	popupMenuEmpleado.show(evt.getComponent(), evt.getX(), evt.getY());
            else
            	enableSelectedRowEmpleadoForUpdate(evt);
        }
	};
	
	KeyListener oKeyAdapterTblEmpleado = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowEmpleadoForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowEmpleadoForUpdate(ComponentEvent evt) {
		try { 
			if (((JTable)evt.getSource()).getSelectedRow() != -1) {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setSelectedRowTblEmpleado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
         		Timetracker2EmpleadoIf empleadoTemp = (Timetracker2EmpleadoIf) empleadoColeccion.get(getSelectedRowTblEmpleado());
         			            	
         		if ("I".equals(empleadoTemp.getEstado()))
    				getCmbEstadoEmpleado().setSelectedItem("INACTIVO");
    			else
    				getCmbEstadoEmpleado().setSelectedItem("ACTIVO");
            	
            	empleadoIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(empleadoTemp.getEmpleadoId());
				getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al seleccionar una fila de la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
		
	private void agregarProductoCliente() {
		if (validateFieldsEmpleado(true)) {			
			modelEmpleado =  (DefaultTableModel) getTblEmpleado().getModel();
			Vector<String> filaEmpleado = new Vector<String>();

			Timetracker2EmpleadoData data = new Timetracker2EmpleadoData();
			
			data.setEstado(getCmbEstadoEmpleado().getSelectedItem().toString().substring(0, 1));
			data.setEmpleadoId(empleadoIf.getId());
			empleadoColeccion.add(data);

			filaEmpleado.add(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
			filaEmpleado.add(getCmbEstadoEmpleado().getSelectedItem().toString());
			
			modelEmpleado.addRow(filaEmpleado);
			cleanEmpleado();
		}
	}
	
	private void actualizarProductoCliente() {
		if (validateFieldsEmpleado(false)) {
			modelEmpleado =  (DefaultTableModel) getTblEmpleado().getModel();
			
			Timetracker2EmpleadoIf data = (Timetracker2EmpleadoIf) empleadoColeccion.get(getSelectedRowTblEmpleado());
			data.setEstado(getCmbEstadoEmpleado().getSelectedItem().toString().substring(0, 1));
			data.setEmpleadoId(empleadoIf.getId());
			
			//Actualizar en la coleccion registro que fue cambiado
			empleadoColeccion.set(getSelectedRowTblEmpleado(),data);
			
			//Actualizo en la tabla
			modelEmpleado.setValueAt(empleadoIf.getNombres() + " " + empleadoIf.getApellidos(),getSelectedRowTblEmpleado(),3);
			modelEmpleado.setValueAt(getCmbEstadoEmpleado().getSelectedItem().toString(),getSelectedRowTblEmpleado(),2);
			
			cleanEmpleado();			
		}
	}
	
	private void eliminarProductoCliente() {
		if (getTblEmpleado().getSelectedRow()!=-1){
			int opcion = JOptionPane.showOptionDialog(null, "¿Esta seguro que desea eliminar el Producto?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				Timetracker2EmpleadoIf productoClienteTemp = (Timetracker2EmpleadoIf) empleadoColeccion.get(getSelectedRowTblEmpleado());
				empleadoEliminadoColeccion.add(productoClienteTemp);    			
				empleadoColeccion.remove(getSelectedRowTblEmpleado());
				modelEmpleado.removeRow(getSelectedRowTblEmpleado());			
				cleanEmpleado();
			}			
		} else {
			SpiritAlert.createAlert("Primero debe seleccionar en la tabla el Producto que desea eliminar!",SpiritAlert.WARNING);
		}	
	}
	
	private void cleanEmpleado() {
		empleadoIf = null;
		getTxtEmpleado().setText("");
	}
	
	public void registrarActividadSeleccionada(){
		if(actividadSeleccionada == null){
			actividadSeleccionada = new Timetracker2Data();
			//actividadSeleccionada.setFechaCreacion(new Date((new java.util.Date()).getTime()));
		}
		
		actividadSeleccionada.setEstado(getCmbEstadoActividad().getSelectedItem().toString().substring(0,1));
		actividadSeleccionada.setActividad(getTxtActividad().getText().toUpperCase());
		actividadSeleccionada.setEmpresaId(Parametros.getIdEmpresa());
	}
	
	public void save() {
		update();
	}
	
	public void update() {
		try {
			if (validateFields()) {
				registrarActividadSeleccionada();
				SessionServiceLocator.getTimetracker2SessionService().procesarTimetracker2Coleccion(actividadSeleccionada, empleadoColeccion, empleadoEliminadoColeccion);
				SpiritAlert.createAlert("Actividad actualizada con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}				
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la infomación!" ,SpiritAlert.ERROR);
		}
	}
	
	public void delete() {
		try {
			if(actividadSeleccionada != null){
				int opcion = JOptionPane.showOptionDialog(null, "Elimando la Actividad, también elimina los registros asignados, ¿Está seguro de eliminarla?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					SessionServiceLocator.getTimetracker2SessionService().eliminarTimetracker2(actividadSeleccionada);
					SpiritAlert.createAlert("La Actividad ha sido eliminada con éxito",SpiritAlert.INFORMATION);
					showSaveMode();
				}				
			}else{
				SpiritAlert.createAlert("Primero debe seleccionar en la tabla la Marca que desea eliminar",SpiritAlert.WARNING);
			}				
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public void refresh() {
	
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void find() {
		buscarActividades();
		
		if(actividadColeccion.size() > 0){
			this.showUpdateMode();
		}else{
			SpiritAlert.createAlert("No hay ninguna actividad ingresada",SpiritAlert.INFORMATION);
		}
	}
	
	Comparator<Timetracker2If> ordenadorActividadPorNombre = new Comparator<Timetracker2If>() {
		public int compare(Timetracker2If o1, Timetracker2If o2) {
			return o1.getActividad().compareTo(o2.getActividad());
		}
	};

	private void buscarActividades() {
		try {
			Map parameterMap = new HashMap();
			ArrayList actividadesColeccion = (ArrayList)SessionServiceLocator.getTimetracker2SessionService().findTimetracker2ByEmpresaId(Parametros.getIdEmpresa());
			Collections.sort(actividadesColeccion,ordenadorActividadPorNombre);
			
			Iterator actividadesColeccionIt = actividadesColeccion.iterator();
			
			while(actividadesColeccionIt.hasNext()){			
				Timetracker2If actividad = (Timetracker2If) actividadesColeccionIt.next();
				
				modelActividad = (DefaultTableModel) getTblActividad().getModel();
				Vector<String> filaMarca = new Vector<String>();
				actividadColeccion.add(actividad);
			
				String estadoActividad = "ACTIVO";
				if ("I".equals(actividad.getEstado()))
					estadoActividad = "INACTIVO";
				
				filaMarca.add(actividad.getCodigo());
				filaMarca.add(actividad.getActividad());
				filaMarca.add(estadoActividad);
					
				modelActividad.addRow(filaMarca);
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}

	private void findEmpleados() {
		try {
			cleanTblProducto();
			empleadoColeccion = null;
			empleadoColeccion = new ArrayList<Timetracker2EmpleadoIf>();
			
			Collection empleadosColeccion = SessionServiceLocator.getTimetracker2EmpleadoSessionService().findTimetracker2EmpleadoByTimetracker2Id(actividadSeleccionada.getId());
			Iterator empleadosColeccionIt = empleadosColeccion.iterator();
			
			modelEmpleado = (DefaultTableModel) getTblEmpleado().getModel();
			
			while(empleadosColeccionIt.hasNext()){					
				Timetracker2EmpleadoIf empleadoIf = (Timetracker2EmpleadoIf) empleadosColeccionIt.next();
				
				Vector<String> filaProductoCliente = new Vector<String>();

				empleadoColeccion.add(empleadoIf);
			
				String estadoEmpleado = "ACTIVO";
				if ("I".equals(empleadoIf.getEstado()))
					estadoEmpleado = "INACTIVO";
				
				EmpleadoIf empleado = (EmpleadoIf) SessionServiceLocator.getEmpleadoSessionService().getEmpleado(empleadoIf.getEmpleadoId());
				
				filaProductoCliente.add(empleado.getNombres() + " " + empleado.getApellidos());
				filaProductoCliente.add(estadoEmpleado);
					
				modelEmpleado.addRow(filaProductoCliente);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isEmpty() {
		return false;
	}

	public boolean validateFields() {
		String strActividad = this.getTxtActividad().getText();
		
		if ((("".equals(strActividad)) || (strActividad == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para la Actividad.", SpiritAlert.WARNING);
			getTxtActividad().grabFocus();
			return false;
		}
		
		for(int i=0;i<actividadColeccion.size();i++){
			Timetracker2If actividad = (Timetracker2If) actividadColeccion.get(i);
			if(this.getMode() == SpiritMode.SAVE_MODE){
				if(actividad.getActividad().equals(getTxtActividad().getText())){
					SpiritAlert.createAlert("La Actividad " + actividad.getActividad() + " ya se encuentra agregada!", SpiritAlert.WARNING);
					getTxtActividad().grabFocus();
					return false;
				}
			}else if(this.getMode() == SpiritMode.UPDATE_MODE){
				if(actividad.getActividad().equals(getTxtActividad().getText()) && !actividadSeleccionada.getActividad().equals(actividad.getActividad())){
					SpiritAlert.createAlert("La Actividad " + actividad.getActividad() + " ya se encuentra agregada!", SpiritAlert.WARNING);
					getTxtActividad().grabFocus();
					return false;
				}
			}			
		}
		
		if(empleadoColeccion.isEmpty()){
			SpiritAlert.createAlert("Debe al menos ingresar 1 Empleado para la actividad.", SpiritAlert.WARNING);
			getJtpActividades().setSelectedIndex(1);
			getBtnBuscarEmpleado().grabFocus();
			return false;
		}
	
		return true;
	}

	public void clean() {
		actividadSeleccionada = null;
		empleadoIf = null;
		actividadColeccion = null;
		actividadColeccion = new ArrayList<Timetracker2If>();
		empleadoColeccion = null;
		empleadoColeccion = new ArrayList<Timetracker2EmpleadoIf>();
		empleadoEliminadoColeccion = null;
		empleadoEliminadoColeccion = new ArrayList<Timetracker2EmpleadoIf>();
		
		getTxtCodigoActividad().setText("");
		getTxtActividad().setText("");
		getTxtActividadSeleccionada().setText("");
		getTxtEmpleado().setText("");
		cleanTblMarca();
		cleanTblProducto();
		getJtpActividades().setSelectedIndex(0);
		getTxtActividad().grabFocus();
	}

	private void cleanTblMarca() {
		DefaultTableModel model = (DefaultTableModel) getTblActividad().getModel();
		for(int i= this.getTblActividad().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	private void cleanTblProducto() {
		DefaultTableModel model = (DefaultTableModel) getTblEmpleado().getModel();
		for(int i= this.getTblEmpleado().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void showSaveMode() {
		setSaveMode();
		clean();
		getCmbEstadoEmpleado().setEnabled(true);
		getBtnBuscarEmpleado().setEnabled(true);
		getBtnBorrarEmpleado().setEnabled(false);
		getBtnAgregarEmpleado().setEnabled(true);
		getBtnActualizarEmpleado().setEnabled(true);
		getCmbEstadoActividad().setSelectedItem("ACTIVO");
		getCmbEstadoEmpleado().setSelectedItem("ACTIVO");
		buscarActividades();
	}
	
	public void showUpdateMode() {
		setUpdateMode();
		getCmbEstadoEmpleado().setEnabled(true);
		getBtnBuscarEmpleado().setEnabled(true);
		getBtnBorrarEmpleado().setEnabled(false);
		getBtnAgregarEmpleado().setEnabled(true);
		getBtnActualizarEmpleado().setEnabled(true);
		getCmbEstadoEmpleado().setSelectedIndex(0);
	}

	public void showFindMode() {
		getCmbEstadoEmpleado().setEnabled(false);
		getBtnBuscarEmpleado().setEnabled(false);
		getBtnBorrarEmpleado().setEnabled(false);
		getBtnAgregarEmpleado().setEnabled(false);
		getBtnActualizarEmpleado().setEnabled(false);
		getCmbEstadoEmpleado().setSelectedIndex(-1);
	}
	
	public boolean validateFieldsEmpleado(boolean agregar){
		String strEmpleado = this.getTxtEmpleado().getText();
		
		if(!agregar && (getSelectedRowTblEmpleado() == -1)){			
			SpiritAlert.createAlert("Debe seleccionar el registro a actualizar.",SpiritAlert.WARNING);
			getBtnBuscarEmpleado().grabFocus();
			return false;
		}
		
		if ((("".equals(getTxtActividad().getText())) || (getTxtActividad().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para la Actividad.", SpiritAlert.WARNING);
			getJtpActividades().setSelectedIndex(0);
			getTxtActividad().grabFocus();
			return false;
		}
		
		if ((("".equals(strEmpleado)) || (strEmpleado == null))) {
			SpiritAlert.createAlert("Debe un empleado."	,SpiritAlert.WARNING);
			getBtnBuscarEmpleado().grabFocus();
			return false;
		}
		
		for(int i=0;i<empleadoColeccion.size();i++){
			Timetracker2EmpleadoIf empleado = (Timetracker2EmpleadoIf) empleadoColeccion.get(i);
			
			if(agregar){
				if(empleado.getEmpleadoId().equals(empleadoIf.getId())){
					SpiritAlert.createAlert("El Empleado " + empleadoIf.getNombres() + " " + empleadoIf.getApellidos() + " ya se encuentra agregado!", SpiritAlert.WARNING);
					getBtnBuscarEmpleado().grabFocus();
					return false;
				}
			}else if(i != getSelectedRowTblEmpleado()) {
				if(empleado.getEmpleadoId().equals(empleadoIf.getId())){
					SpiritAlert.createAlert("El Producto " + empleadoIf.getNombres() + " " + empleadoIf.getApellidos() + " ya se encuentra agregado!", SpiritAlert.WARNING);
					getBtnBuscarEmpleado().grabFocus();
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void setSelectedRowTblEmpleado(int row) {
		this.selectedRowTblEmpleado = row;
	}
	
	public int getSelectedRowTblEmpleado() {
		return this.selectedRowTblEmpleado;
	}
	
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public int getSelectedRowTblActividad() {
		return selectedRowTblActividad;
	}

	public void setSelectedRowTblActividad(int selectedRowTblActividad) {
		this.selectedRowTblActividad = selectedRowTblActividad;
	}
}
