package com.spirit.timeTracker.gui.model;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.WindowConstants;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.medios.entity.EquipoEmpleadoIf;
import com.spirit.timeTracker.gui.main.JDCrearOrdenTrabajoGenerica;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;

public class CrearOrdenTrabajoGenericaModel extends JDCrearOrdenTrabajoGenerica {
	
	private static final long serialVersionUID = -3603398904571796870L;
	
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private JDPopupFinderModel popupFinder;
	private ClienteOficinaIf clienteOficina;
	private boolean crearOrden = false;
	private static final int MAX_LONGITUD_DESCRIPCION_ORDEN = 200;
	
	public CrearOrdenTrabajoGenericaModel(Frame owner, ArrayList ejecutivos){
		super(owner);
		
		initKeyListeners();
		initListeners();
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setVisible(true);
	}
	
	public void initKeyListeners(){
		/*getLblDescripcion().setVisible(false);
		getTxtDescripcion().setVisible(false);
		getLblTipo().setVisible(false);
		getCmbTipo().setVisible(false);
		getLblSubtipo().setVisible(false);
		getCmbSubtipo().setVisible(false);*/
		cargarComboTipoOrden();
		
		cargarComboEjecutivo(0L);
		getBtnBuscarClienteOficina().setIcon(SpiritResourceManager.getImageIcon("images/timetracker/findElement.png"));

		getTxtDescripcion().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION_ORDEN));
	}
	
	private void initListeners() {
		// Manejo los eventos de Buscar ClienteOficina
		getBtnBuscarClienteOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tipoCliente = "CL";
				String tituloVentanaBusqueda = "Oficinas de Clientes";
									
				clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, "", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					clienteOficina = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					getTxtClienteOficina().setText(clienteOficina.getCodigo() + " - "+ clienteOficina.getDescripcion());
				}
			}
		});
		
		getBtnCrear().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(validateFields()){
					setCrearOrden(true);
					salir();
				}				
			}
		});
		
		getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setCrearOrden(false);
				salir();
			}
		});	
		
		getCmbTipo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				TipoOrdenIf tipo = (TipoOrdenIf) getCmbTipo().getModel().getSelectedItem();
				
				if ( tipo != null ){
					getCmbEquipo().setEnabled(true);
					cargarComboEquipo(tipo);
					
					getCmbSubtipo().setEnabled(true);
					cargarComboSubTipoOrden(tipo);
				}
			}
		});
	}
	
	private boolean validateFields(){
		if(clienteOficina == null){
			SpiritAlert.createAlert("Debe seleccionar un cliente.", SpiritAlert.WARNING);
			getBtnBuscarClienteOficina().grabFocus();
			return false;
		}
		if(getTxtDescripcion().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar una descripción.", SpiritAlert.WARNING);
			getTxtDescripcion().grabFocus();
			return false;
		}
		if(getCmbEjecutivo().getSelectedItem() == null){
			SpiritAlert.createAlert("Debe seleccionar un ejecutivo.", SpiritAlert.WARNING);
			getCmbEjecutivo().grabFocus();
			return false;
		}
		if(getCmbEquipo().getSelectedItem() == null){
			SpiritAlert.createAlert("Debe seleccionar un equipo.", SpiritAlert.WARNING);
			getCmbEquipo().grabFocus();
			return false;
		}
		if(getCmbSubtipo().getSelectedItem() == null){
			SpiritAlert.createAlert("Debe seleccionar un subtipo.", SpiritAlert.WARNING);
			getCmbSubtipo().grabFocus();
			return false;
		}
		return true;
	}
	
	private void salir(){
		this.dispose();
	}
	
	Comparator<EmpleadoIf> ordenadorArrayListPorNombre = new Comparator<EmpleadoIf>(){
		public int compare(EmpleadoIf o1, EmpleadoIf o2) {
			return (o1.getNombres().compareTo(o2.getNombres()));
		}		
	};
	
	private void cargarComboEjecutivo(Long ejecutivoSeleccionadoId){
		try {						
			String CODIGO_ROL_EJECUTIVO_CUENTA = "ECU";
			int ejecutivoSeleccionadoIndex = -1;
			List<EmpleadoIf> ejecutivos = new ArrayList<EmpleadoIf>();
			Collection empleadosEquipo = SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Iterator empleadosEquipoIt = empleadosEquipo.iterator();
			while(empleadosEquipoIt.hasNext()){
				EquipoEmpleadoIf empleadoEquipo = (EquipoEmpleadoIf)empleadosEquipoIt.next();
				if(empleadoEquipo.getRol().equals(CODIGO_ROL_EJECUTIVO_CUENTA)){
					EmpleadoIf ejecutivo = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(empleadoEquipo.getEmpleadoId());
					boolean ejecutivoIngresado = false;
					for(EmpleadoIf ejecutivoAgregado : ejecutivos){
						if(ejecutivoAgregado.getId().compareTo(ejecutivo.getId()) == 0){
							ejecutivoIngresado = true;
						}
					}
					if(!ejecutivoIngresado)
						ejecutivos.add(ejecutivo);
				}
			}
			Collections.sort((ArrayList)ejecutivos,ordenadorArrayListPorNombre);
			//Se es update y se envio un id de ejecutivo entonces lo busco para setear su nombre en el combo.
			for(int i=0; i<ejecutivos.size(); i++){
				EmpleadoIf ejecutivo = ejecutivos.get(i);
				if(ejecutivoSeleccionadoId > 0L && ejecutivo.getId().compareTo(ejecutivoSeleccionadoId) == 0){
					ejecutivoSeleccionadoIndex = i;
				}
			}
			refreshComboByIndex(getCmbEjecutivo(), ejecutivos, ejecutivoSeleccionadoIndex);
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	protected static void refreshComboByIndex(JComboBox combo,List<? extends Object> lista,int index){
		try {
			SpiritComboBoxModel cmbModel = new SpiritComboBoxModel(lista);
			int itemSeleccionado = combo.getSelectedIndex(); 
			combo.setModel(cmbModel);
			int numeroItems = combo.getItemCount();
			if (combo.getItemCount() > 0) {
				if ( itemSeleccionado >= 0 && numeroItems == combo.getItemCount() && itemSeleccionado <= combo.getItemCount())
					combo.setSelectedIndex(itemSeleccionado);
				else
					combo.setSelectedIndex(index);
			}
		} catch (java.lang.IllegalArgumentException ae) {
			ae.printStackTrace();
			combo.setSelectedIndex(index);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error al refrescar la pantalla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public boolean isCrearOrden() {
		return crearOrden;
	}

	public void setCrearOrden(boolean crearOrden) {
		this.crearOrden = crearOrden;
	}

	public ClienteOficinaIf getClienteOficina() {
		return clienteOficina;
	}

	public void setClienteOficina(ClienteOficinaIf clienteOficina) {
		this.clienteOficina = clienteOficina;
	}
	
	private void cargarComboTipoOrden(){
		try {
			List tiposOrden = (List) SessionServiceLocator.getTipoOrdenSessionService()
			.findTipoOrdenByEmpresaId(Parametros.getIdEmpresa());
						
			for(int i=0; i<tiposOrden.size(); i++){
				getCmbTipo().addItem(tiposOrden.get(i));
			}
			getCmbEquipo().setEnabled(false);
			getCmbSubtipo().setEnabled(false);
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboEquipo(TipoOrdenIf tipo){
		try {
			getCmbEquipo().removeAllItems();
			Map aMap = new HashMap();
			aMap.put("empresaId", Parametros.getIdEmpresa());
			aMap.put("tipoordenId", tipo.getId());
			aMap.put("estado", "A");
			UsuarioIf usuario = (UsuarioIf)Parametros.getUsuarioIf();
			List equipos = (List) SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByQueryAndByEmpleadoId(aMap, usuario.getEmpleadoId());
			for(int i=0; i<equipos.size(); i++){
				getCmbEquipo().addItem(equipos.get(i));
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboSubTipoOrden(TipoOrdenIf tipo){
		try {
			getCmbSubtipo().removeAllItems();
			List subTipoOrden = (List) SessionServiceLocator.getSubtipoOrdenSessionService().findSubtipoOrdenByTipoordenId(tipo.getId());
			for(int i=0; i<subTipoOrden.size(); i++){
				getCmbSubtipo().addItem(subTipoOrden.get(i));
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
}
