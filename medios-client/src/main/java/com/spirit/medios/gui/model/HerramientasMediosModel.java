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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.medios.entity.HerramientasMediosData;
import com.spirit.medios.entity.HerramientasMediosIf;
import com.spirit.medios.gui.panel.JPHerramientasMedios;
import com.spirit.medios.handler.FrecuenciaHerramientasMedios;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;

public class HerramientasMediosModel extends JPHerramientasMedios {

	private Map<Long,ClienteOficinaIf> mapaClienteOficinas = new HashMap<Long, ClienteOficinaIf>();
	private ClienteOficinaCriteria clienteOficinaCriteria;
	protected ClienteOficinaIf clienteOficinaIf;
	private ClienteOficinaCriteria proveedorOficinaCriteria;
	protected ClienteOficinaIf proveedorOficinaIf;
	final JPopupMenu  popupOver = new JPopupMenu();
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String CODIGO_TIPO_PROVEEDOR = "PR";
	private int selectedRowTblHerramienta = -1;
	private List<HerramientasMediosIf> herramientasColeccion = new ArrayList<HerramientasMediosIf>();
	private List<HerramientasMediosIf> herramientasColeccionEliminado = new ArrayList<HerramientasMediosIf>();
	private DefaultTableModel modelTblHerramientas;
	private String si = "Sí"; 
	private String no = "No"; 
	private Object[] options = {si, no};
	
	
	public HerramientasMediosModel(){
		cargarMapas();
		initKeyListeners();
		anchoColumnasTabla();
		showSaveMode();
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		setSorterTable(getTblHerramientas());
		getTblHerramientas().getTableHeader().setReorderingAllowed(false);
		//getTblHerramientas().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getTblHerramientas().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		TableColumn anchoColumna = getTblHerramientas().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblHerramientas().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblHerramientas().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(100);
						
		//TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		//getTblHerramientas().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		getTblHerramientas().getColumnModel().getColumn(2).setCellRenderer(tableCellRendererCenter);
	}
	
	public void cargarMapas(){
		try {			
			mapaClienteOficinas.clear();
			Collection clientesOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList();
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while(clientesOficinaIt.hasNext()){
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf)clientesOficinaIt.next();
				mapaClienteOficinas.put(clienteOficina.getId(), clienteOficina);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void initKeyListeners() {
		getTxtCliente().setEditable(false);
		getTxtProveedor().setEditable(false);
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");		
		getBtnProveedor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnProveedor().setToolTipText("Buscar Proveedor");		
		
		getBtnAgregar().setText("");
		getBtnActualizar().setText("");
		getBtnEliminar().setText("");
		getBtnAgregar().setToolTipText("Agregar Producto");
		getBtnAgregar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizar().setToolTipText("Actualizar Producto");
		getBtnActualizar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnEliminar().setToolTipText("Eliminar Producto");
		getBtnEliminar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
	}
	
	public void initListeners(){
		getTblHerramientas().addMouseListener(oMouseAdapterTblHerramientas);	    
		getTblHerramientas().addKeyListener(oKeyAdapterTblHerramientas);
			
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
		
		getBtnProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tituloVentanaBusqueda = "de Proveedores";
				proveedorOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_PROVEEDOR, "", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), proveedorOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					proveedorOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					getTxtProveedor().setText(proveedorOficinaIf.getDescripcion());
				}
			}
		});
		
		getBtnAgregar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarHerramienta();			
			}
		});
			
		getBtnActualizar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarHerramienta();			
			}
		});
		
		getBtnEliminar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarHerramienta();			
			}
		});
	}
	
	private void agregarHerramienta() {
		if (validateFieldsDetalle(true)) {			
			modelTblHerramientas =  (DefaultTableModel) getTblHerramientas().getModel();
			Vector<String> filaOver = new Vector<String>();

			HerramientasMediosData data = new HerramientasMediosData();
			
			data.setClienteOficinaId(clienteOficinaIf.getId());
			data.setProveedorOficinaId(proveedorOficinaIf.getId());
			
			String frecuenciaSeleccionada = (String)getCmbFrecuencia().getSelectedItem();
			for(int i=0; i<FrecuenciaHerramientasMedios.values().length; i++){
				FrecuenciaHerramientasMedios frecuencia = FrecuenciaHerramientasMedios.values()[i];
				if(frecuencia.toString().equals(frecuenciaSeleccionada)){
					data.setFrecuencia(frecuencia.getLetra());
				}
			}
			
			herramientasColeccion.add(data);

			filaOver.add(clienteOficinaIf.getDescripcion());
			filaOver.add(proveedorOficinaIf.getDescripcion());
			filaOver.add(frecuenciaSeleccionada);
			
			modelTblHerramientas.addRow(filaOver);
			cleanHerramienta();
		}
	}
	
	private void actualizarHerramienta() {
		if (validateFieldsDetalle(false)) {			
			modelTblHerramientas =  (DefaultTableModel) getTblHerramientas().getModel();
			
			HerramientasMediosIf data = (HerramientasMediosIf) herramientasColeccion.get(getSelectedRowTblHerramienta());
						
			data.setClienteOficinaId(clienteOficinaIf.getId());
			data.setProveedorOficinaId(proveedorOficinaIf.getId());
			
			String frecuenciaSeleccionada = (String)getCmbFrecuencia().getSelectedItem();
			for(int i=0; i<FrecuenciaHerramientasMedios.values().length; i++){
				FrecuenciaHerramientasMedios frecuencia = FrecuenciaHerramientasMedios.values()[i];
				if(frecuencia.toString().equals(frecuenciaSeleccionada)){
					data.setFrecuencia(frecuencia.getLetra());
				}
			}
			
			herramientasColeccion.set(getSelectedRowTblHerramienta(),data);

			modelTblHerramientas.setValueAt(clienteOficinaIf.getDescripcion(), getSelectedRowTblHerramienta(), 0);
			modelTblHerramientas.setValueAt(proveedorOficinaIf.getDescripcion(), getSelectedRowTblHerramienta(), 1);
			modelTblHerramientas.setValueAt(frecuenciaSeleccionada, getSelectedRowTblHerramienta(), 2);
			
			cleanHerramienta();
		}
	}
	
	private void eliminarHerramienta() {
		if (getTblHerramientas().getSelectedRow()!=-1){
			int opcion = JOptionPane.showOptionDialog(null, "¿Esta seguro que desea eliminar la Herramienta?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				HerramientasMediosIf herramientasMediosTemp = (HerramientasMediosIf) herramientasColeccion.get(getSelectedRowTblHerramienta());
				herramientasColeccionEliminado.add(herramientasMediosTemp);    			
				herramientasColeccion.remove(getSelectedRowTblHerramienta());
				modelTblHerramientas.removeRow(getSelectedRowTblHerramienta());	
				cleanHerramienta();
			}			
		} else {
			SpiritAlert.createAlert("Primero debe seleccionar en la tabla el Producto que desea eliminar!",SpiritAlert.WARNING);
		}	
	}
	
	public boolean validateFieldsDetalle(boolean agregar){
		
		if(!agregar && (selectedRowTblHerramienta == -1)){			
			SpiritAlert.createAlert("Seleccione el registro a actualizar de la tabla.",SpiritAlert.WARNING);
			getBtnCliente().grabFocus();
			return false;
		}
		
		if(clienteOficinaIf == null){
			SpiritAlert.createAlert("Debe seleccionar un Cliente.",SpiritAlert.WARNING);
			getBtnCliente().grabFocus();
			return false;
		}
		
		if(proveedorOficinaIf == null){
			SpiritAlert.createAlert("Debe seleccionar un Proveedor.",SpiritAlert.WARNING);
			getBtnProveedor().grabFocus();
			return false;
		}
				
		if(getCmbFrecuencia().getSelectedItem() == null){
			SpiritAlert.createAlert("Debe seleccionar una Frecuencia." ,SpiritAlert.WARNING);
			getCmbFrecuencia().grabFocus();
			return false;
		}		
		
		for(int i=0;i<herramientasColeccion.size();i++){
			HerramientasMediosIf HerramientasMediosTemp = (HerramientasMediosIf) herramientasColeccion.get(i);
			
			if(agregar){
				if(HerramientasMediosTemp.getClienteOficinaId().compareTo(clienteOficinaIf.getId()) == 0 
				&& HerramientasMediosTemp.getProveedorOficinaId().compareTo(proveedorOficinaIf.getId()) == 0){
					SpiritAlert.createAlert("El Over de este Cliente con este Proveedor ya se encuentra agregado!", SpiritAlert.WARNING);
					getBtnCliente().grabFocus();
					return false;
				}
			}else if(i != getSelectedRowTblHerramienta()) {
				if(HerramientasMediosTemp.getClienteOficinaId().compareTo(clienteOficinaIf.getId()) == 0 
					&& HerramientasMediosTemp.getProveedorOficinaId().compareTo(proveedorOficinaIf.getId()) == 0){
					SpiritAlert.createAlert("El Over de este Cliente con este Proveedor ya se encuentra agregado!", SpiritAlert.WARNING);
					getBtnCliente().grabFocus();
					return false;
				}
			}
		}
		
		return true;
	}
	
	MouseListener oMouseAdapterTblHerramientas = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
            if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblHerramientas().getModel().getRowCount()>0)
            	popupOver.show(evt.getComponent(), evt.getX(), evt.getY());
            else
            	enableSelectedRowOverForUpdate(evt);
        }
	};
	
	KeyListener oKeyAdapterTblHerramientas = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowOverForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowOverForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setSelectedRowTblHerramienta(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
     		HerramientasMediosIf herramientasMediosTemp = (HerramientasMediosIf) herramientasColeccion.get(getSelectedRowTblHerramienta());
     			            	
     		clienteOficinaIf = mapaClienteOficinas.get(herramientasMediosTemp.getClienteOficinaId());
     		getTxtCliente().setText(clienteOficinaIf.getDescripcion());
     		proveedorOficinaIf = mapaClienteOficinas.get(herramientasMediosTemp.getProveedorOficinaId());
     		getTxtProveedor().setText(proveedorOficinaIf.getDescripcion());
     		
     		try {
				getCmbFrecuencia().setSelectedItem(FrecuenciaHerramientasMedios.getFrecuenciaHerramientasMediosByLetra(herramientasMediosTemp.getFrecuencia().toString()).toString());
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}
	}

	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblHerramientas().getModel();
		for(int i= this.getTblHerramientas().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void clean() {
		herramientasColeccion.clear();
		herramientasColeccionEliminado.clear();
		cleanHerramienta();
		cleanTable();
	}
	
	public void cleanHerramienta(){
		clienteOficinaIf = null;
		getTxtCliente().setText("");
		proveedorOficinaIf = null;
		getTxtProveedor().setText("");
		getCmbFrecuencia().setSelectedIndex(0);
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
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
			if (herramientasColeccion.size() > 0) {
				SessionServiceLocator.getHerramientasMediosSessionService().procesarHerramientasMedios(herramientasColeccion, herramientasColeccionEliminado);
				SpiritAlert.createAlert("Herramientas de Medios guardadas con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}else{
				SpiritAlert.createAlert("De agregar al menos una herramienta para poder guardar.",SpiritAlert.INFORMATION);
				getBtnCliente().grabFocus();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la infomación!" ,SpiritAlert.ERROR);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
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
	
	public void cargarTabla(){
		try {
			herramientasColeccion.clear();
			modelTblHerramientas =  (DefaultTableModel) getTblHerramientas().getModel();
			
			Collection herramientasMedios = SessionServiceLocator.getHerramientasMediosSessionService().getHerramientasMediosList();
			Iterator herramientasMediosIt = herramientasMedios.iterator();
			while(herramientasMediosIt.hasNext()){
				HerramientasMediosIf herramientasMediosIf = (HerramientasMediosIf)herramientasMediosIt.next();
				
				herramientasColeccion.add(herramientasMediosIf);
				
				Vector<String> filaOver = new Vector<String>();
				
				ClienteOficinaIf clienteOficina = mapaClienteOficinas.get(herramientasMediosIf.getClienteOficinaId());
				ClienteOficinaIf proveedorOficina = mapaClienteOficinas.get(herramientasMediosIf.getProveedorOficinaId());
							
				filaOver.add(clienteOficina.getDescripcion());
				filaOver.add(proveedorOficina.getDescripcion());
				filaOver.add(FrecuenciaHerramientasMedios.getFrecuenciaHerramientasMediosByLetra(herramientasMediosIf.getFrecuencia().toString()).toString());
				
				modelTblHerramientas.addRow(filaOver);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public int getSelectedRowTblHerramienta() {
		return selectedRowTblHerramienta;
	}

	public void setSelectedRowTblHerramienta(int selectedRowTblHerramienta) {
		this.selectedRowTblHerramienta = selectedRowTblHerramienta;
	}
}
