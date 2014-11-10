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
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.medios.entity.Timetracker2If;
import com.spirit.medios.entity.Timetracker2TiempoData;
import com.spirit.medios.entity.Timetracker2TiempoIf;
import com.spirit.medios.gui.panel.JPTimetracker2Tiempos;
import com.spirit.util.NumberTextField;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class Timetracker2TiemposModel extends JPTimetracker2Tiempos {

	private EmpleadoIf empleadoIf = null;
	private EmpleadoCriteria empleadoCriteria;
	private JDPopupFinderModel popupFinder;
	protected ClienteOficinaIf clienteOficinaIf;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final int MAX_LONGITUD_VALOR = 3;
	private DefaultTableModel tableModel;
	private Vector tiemposVector = new Vector();
	private int tiempoSeleccionado;
	private Timetracker2TiempoIf tiempoActualizadoIf;
	private Map<Long, EmpleadoIf> mapaEmpleado = new HashMap<Long, EmpleadoIf>();
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private DecimalFormat formatoDecimal = new DecimalFormat("##0.00");
	
	
	public Timetracker2TiemposModel(){
		initKeyListeners();
		anchoColumnasTabla();
		cargarMapas();
		initListeners();
		showSaveMode();
		this.getTblTiempos().addMouseListener(oMouseAdapterTblModulo);
		this.getTblTiempos().addKeyListener(oKeyAdapterTblModulo);
		
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTiempos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(100);
				
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblTiempos().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
	}
	
	public void cargarMapas() {
		try {		
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator
					.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while (clientesOficinaIt.hasNext()) {
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clientesOficinaIt
						.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
			
			mapaEmpleado.clear();
			Collection empleados = SessionServiceLocator
					.getEmpleadoSessionService().findEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Iterator empleadosIt = empleados.iterator();
			while (empleadosIt.hasNext()) {
				EmpleadoIf empleadoIf = (EmpleadoIf)empleadosIt.next();
				mapaEmpleado.put(empleadoIf.getId(), empleadoIf);
			}					
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void initKeyListeners(){
		getBtnEmpleadoTiempo().setToolTipText("Buscar Empleado");
		getBtnEmpleadoTiempo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getTxtTiempo().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtTiempo().addKeyListener(new NumberTextField());
		
	}
	
	public void initListeners(){
		getBtnEmpleadoTiempo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoCriteria = new EmpleadoCriteria("Empleados",Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
										empleadoCriteria,
										JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getTxtEmpleadoTiempo().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
				}
			}
		});
		
		getBtnCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tituloVentanaBusqueda = "de Oficinas del Cliente";
				clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_CLIENTE, "", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					getTxtCliente().setText(clienteOficinaIf.getDescripcion());
				}
			}
		});
	}
	
	MouseListener oMouseAdapterTblModulo = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblModulo = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			tiempoSeleccionado = ((JTable)evt.getSource()).convertRowIndexToModel(selectedRow);
			tiempoActualizadoIf = (Timetracker2TiempoIf)  tiemposVector.get(tiempoSeleccionado);
			empleadoIf = mapaEmpleado.get(tiempoActualizadoIf.getEmpleadoId());
			getTxtEmpleadoTiempo().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos().split(" ")[0]);
			clienteOficinaIf = mapaClienteOficina.get(tiempoActualizadoIf.getClienteOficinaId());
			getTxtCliente().setText(clienteOficinaIf.getDescripcion());
			getTxtTiempo().setText(tiempoActualizadoIf.getTiempoDesignado().toString());
			
			showUpdateMode();
		}
	}
	
	public void clean() {
		getTxtEmpleadoTiempo().setText("");
		empleadoIf = null;
		getTxtCliente().setText("");
		clienteOficinaIf = null;
		getTxtTiempo().setText("");
	}

	public void delete() {
		try {
			if(tiempoActualizadoIf != null){
				SessionServiceLocator.getTimetracker2TiempoSessionService().deleteTimetracker2Tiempo(tiempoActualizadoIf.getId());
				SpiritAlert.createAlert("Timetracker2Tiempo eliminado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}			
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
	}

	public void save() {
		try {
			if (validateFields()) {
				Timetracker2TiempoIf tiempo = registrarTiempo();
				SessionServiceLocator.getTimetracker2TiempoSessionService().addTimetracker2Tiempo(tiempo);
				SpiritAlert.createAlert("Timetracker2Tiempo guardado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}				
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public Timetracker2TiempoIf registrarTiempo(){
		Timetracker2TiempoData tiempo = new Timetracker2TiempoData();
		tiempo.setEmpleadoId(empleadoIf.getId());
		tiempo.setClienteOficinaId(clienteOficinaIf.getId());
		tiempo.setTiempoDesignado(Integer.valueOf(getTxtTiempo().getText()));
		
		return tiempo;
	}
	
	public Timetracker2TiempoIf registrarTiempoForUpdate(){
		tiempoActualizadoIf.setEmpleadoId(empleadoIf.getId());
		tiempoActualizadoIf.setClienteOficinaId(clienteOficinaIf.getId());
		tiempoActualizadoIf.setTiempoDesignado(Integer.valueOf(getTxtTiempo().getText()));
		
		return tiempoActualizadoIf;
	}

	public void update() {
		try {
			if (validateFields()) {
				Timetracker2TiempoIf tiempo = registrarTiempoForUpdate();
				SessionServiceLocator.getTimetracker2TiempoSessionService().saveTimetracker2Tiempo(tiempo);
				SpiritAlert.createAlert("Timetracker2Tiempo actualizado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}				
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		if(empleadoIf == null){
			SpiritAlert.createAlert("Debe seleccionar un empleado.", SpiritAlert.WARNING);
			getTxtEmpleadoTiempo().grabFocus();
			return false;
		}
		
		if(clienteOficinaIf == null){
			SpiritAlert.createAlert("Debe seleccionar un cliente.", SpiritAlert.WARNING);
			getTxtCliente().grabFocus();
			return false;
		}
		
		if(getTxtTiempo().getText().trim().equals("")){
			SpiritAlert.createAlert("Debe ingresar un tiempo.", SpiritAlert.WARNING);
			getTxtTiempo().grabFocus();
			return false;
		}
		
		try {
			if(getMode() == SpiritMode.SAVE_MODE){
				Map aMap = new HashMap();
				aMap.put("empleadoId", empleadoIf.getId());
				aMap.put("clienteOficinaId", clienteOficinaIf.getId());			
				Collection registros = SessionServiceLocator.getTimetracker2TiempoSessionService().findTimetracker2TiempoByQuery(aMap);
				if(registros.size() > 0){
					SpiritAlert.createAlert("Ya existe un registro con el Empleado y Cliente.", SpiritAlert.WARNING);
					getBtnEmpleadoTiempo().grabFocus();
					return false;
				}
			}			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		
		return true;
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void deleteDetail() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblTiempos().getModel();
		for(int i= this.getTblTiempos().getRowCount();i>0;--i){
			model.removeRow(i-1);
		}	
	}
	
	public void cargarTabla(){
		try {
			cleanTable();
			
			Collection timetracker2Tiempos = SessionServiceLocator.getTimetracker2TiempoSessionService().getTimetracker2TiempoList();
			Iterator timetracker2TiemposIt = timetracker2Tiempos.iterator();
			
			if(!tiemposVector.isEmpty()){
				tiemposVector.removeAllElements();
			}		
			
			while (timetracker2TiemposIt.hasNext()) {
				Timetracker2TiempoIf tiempo = (Timetracker2TiempoIf) timetracker2TiemposIt.next();
				
				tableModel = (DefaultTableModel) getTblTiempos().getModel();
				Vector<String> fila = new Vector<String>();
				tiemposVector.add(tiempo);
				
				agregarColumnasTabla(tiempo, fila);
				
				tableModel.addRow(fila);
			}
			
			Utilitarios.scrollToCenter(getTblTiempos(), timetracker2Tiempos, 0);
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(Timetracker2TiempoIf tiempo, Vector<String> fila){
		EmpleadoIf empleado = mapaEmpleado.get(tiempo.getEmpleadoId());		
		fila.add(empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0]);
		ClienteOficinaIf clienteOficina = mapaClienteOficina.get(tiempo.getClienteOficinaId());
		fila.add(clienteOficina.getDescripcion());
		fila.add(formatoDecimal.format(tiempo.getTiempoDesignado()));
	}

	public void showUpdateMode() {
		setUpdateMode();
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
}
