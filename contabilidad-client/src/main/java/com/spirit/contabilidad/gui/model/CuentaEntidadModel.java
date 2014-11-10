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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.GastoIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.CuentaEntidadData;
import com.spirit.contabilidad.entity.CuentaEntidadIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.gui.criteria.CuentaCriteria;
import com.spirit.contabilidad.gui.criteria.SriSustentoTributarioCriteria;
import com.spirit.contabilidad.gui.panel.JPCuentaEntidad;
import com.spirit.contabilidad.handler.TipoEntidadEnum;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DepartamentoIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.OrigenDocumentoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.general.gui.criteria.CuentaBancariaCriteria;
import com.spirit.general.gui.criteria.DepartamentoCriteria;
import com.spirit.general.gui.criteria.DocumentoCriteria;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.general.gui.criteria.GastoCriteria;
import com.spirit.general.gui.criteria.OficinaCriteria;
import com.spirit.general.gui.criteria.OrigenDocumentoCriteria;
import com.spirit.general.gui.criteria.SriAirCriteria;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.gui.criteria.GenericoCriteria;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.gui.criteria.RubroCriteria;
import com.spirit.server.SpiritIf;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriSustentoTributarioIf;
import com.spirit.util.SpiritComboBoxModel;

public class CuentaEntidadModel extends JPCuentaEntidad {
	
	private static final long serialVersionUID = -3038777979775990774L;
	
	private PlanCuentaIf planCuenta;
	private Vector<CuentaEntidadIf> cuentaEntidadVector = new Vector<CuentaEntidadIf>();
	Vector<Long> idActualizar = new Vector<Long>();
	private CuentaIf cuentaIf;
	private ClienteIf clienteIf;
	private EmpleadoIf empleadoIf;
	private GenericoIf genericoIf;
	private ProductoIf productoIf;
	private DocumentoIf documentoIf;
	private DepartamentoIf departamentoIf;
	private RubroIf rubroIf;
	private CuentaBancariaIf cuentaBancariaIf;
	private SriAirIf sriAirIf;
	private OficinaIf oficinaIf;
	private GastoIf gastoIf;
	private OrigenDocumentoIf origenDocumentoIf;
	private CuentaEntidadIf cuentaEntidad;
	private CuentaEntidadIf cuentaEntidadSeleccionadaIf;
	private DefaultTableModel tableModel;
	private int registroSeleccionado;
	private static final String TIPO_CLIENTE = "Clientes";
	private static final String TIPO_PROVEEDOR = "Proveedores";
	private boolean registroAgregado = true;
	private boolean datosGuardados = true;
	private static final String ESTADO_CUENTA_ACTIVA = "A";
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no}; 
	private SriSustentoTributarioIf sriSustentoTributarioIf;
	
	public CuentaEntidadModel() {
		
		//este combo se debe cargar en el constructor porque si se carga en el showsavemode
		//se lo llama mas de una vez y se resetea la oficina seleccionada.
		cargarComboOficina();
		
		showSaveMode();
		iniciarComponentes();
		initListeners();
		loadCombos();
		setSorterTable(getTblCuentaEntidad());
		tableModel = (DefaultTableModel) getTblCuentaEntidad().getModel();
		new HotKeyComponent(this);
	}
	
	private void cargarComboOficina(){
		try {
			List<OficinaIf> oficinas = (ArrayList<OficinaIf>)SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbOficina(),oficinas);
			
			//seteo por default la oficina del usuario.
			OficinaIf oficinaUsuario = (OficinaIf)Parametros.getOficina();
			int indice = 0;
			for(int i=0; i<oficinas.size(); i++){
				OficinaIf oficinaIf = oficinas.get(i);
				if(oficinaIf.getId().compareTo(oficinaUsuario.getId()) == 0){
					indice = i;
				}
			}
			
			getCmbOficina().setSelectedIndex(indice);
			getCmbOficina().repaint();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void loadCombos() {
		try {
			Collection planesCuentaByEmpresaId = SessionServiceLocator.getPlanCuentaSessionService().findPlanCuentaByEmpresaId(Parametros.getIdEmpresa());
			SpiritComboBoxModel cmbModelPlanCuenta = new SpiritComboBoxModel((ArrayList) planesCuentaByEmpresaId);
			getCmbPlanCuenta().setModel(cmbModelPlanCuenta);
			PlanCuentaModel.seleccionarPlanCuentaPredeterminado(getCmbPlanCuenta());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void iniciarComponentes(){
		getBtnBuscarEntidad().setText("");
		getBtnBuscarEntidad().setToolTipText("Buscar entidad");
		getBtnBuscarEntidad().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCuenta().setText("");
		getBtnBuscarCuenta().setToolTipText("Buscar cuenta");
		getBtnBuscarCuenta().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnAgregarRegistro().setText("");
		getBtnAgregarRegistro().setToolTipText("Agregar Registro");
		getBtnAgregarRegistro().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarRegistro().setText("");
		getBtnActualizarRegistro().setToolTipText("Actualizar Registro");
		getBtnActualizarRegistro().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnRemoverRegistro().setText("");
		getBtnRemoverRegistro().setToolTipText("Eliminar Registro");
		getBtnRemoverRegistro().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		
		
		//Combo TipoDocumento
		cargarComboTipoEntidad();
	}
	
	private void cargarComboTipoEntidad(){
		getCmbTipoEntidad().setModel(
				new DefaultComboBoxModel( TipoEntidadEnum.values() )
			);
	}
	
	private void initListeners() {
		getBtnBuscarEntidad().addActionListener(btnBuscarEntidadListener);
		getCmbTipoEntidad().addActionListener(cmbTipoEntidadListener);
		getCmbPlanCuenta().addActionListener(cmbPlanCuentaListener);
		getBtnBuscarCuenta().addActionListener(btnBuscarCuentaListener);
		getBtnAgregarRegistro().addActionListener(btnAgregarRegistroListener);
		getBtnActualizarRegistro().addActionListener(btnActualizarRegistroListener);
		getBtnRemoverRegistro().addActionListener(btnRemoverRegistroListener);
		getTblCuentaEntidad().addMouseListener(oMouseAdapterTblCuentaEntidad);
		getTblCuentaEntidad().addKeyListener(oKeyAdapterTblCuentaEntidad);
	}
	
	MouseListener oMouseAdapterTblCuentaEntidad = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblCuentaEntidad = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	ActionListener btnAgregarRegistroListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			setRegistroAgregado(true);
			addDetail();
		}
	};
	
	ActionListener btnActualizarRegistroListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			setRegistroAgregado(false);
			updateDetail();
		}
	};
	
	ActionListener btnRemoverRegistroListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			eliminarDetalle();
		}
	};
	
	ActionListener btnBuscarCuentaListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			CuentaCriteria cuentaCriteria = new CuentaCriteria("Cuentas", "S", planCuenta.getId(), 0L, ESTADO_CUENTA_ACTIVA);
			JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), cuentaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if ( popupFinder.getElementoSeleccionado() != null ) {
				setCuentaSeleccionadaIf((CuentaIf) popupFinder.getElementoSeleccionado());
				getTxtCuenta().setText(getCuentaSeleccionadaIf().getCodigo()	+ " - " + getCuentaSeleccionadaIf().getNombre());
			}
		}
	};
	
	ActionListener btnBuscarEntidadListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			verificarDatosGuardados();
			TipoEntidadEnum tipoEntidad = (TipoEntidadEnum) getCmbTipoEntidad().getSelectedItem(); 
			if ( tipoEntidad == TipoEntidadEnum.CLIENTE || tipoEntidad == TipoEntidadEnum.PROVEEDOR ) {
				String tipoCliente = "";
				if ( tipoEntidad == TipoEntidadEnum.CLIENTE )
					tipoCliente = TIPO_CLIENTE;
				else
					tipoCliente = TIPO_PROVEEDOR;
				
				ClienteCriteria clienteCriteria = new ClienteCriteria(TIPO_CLIENTE, 0L, tipoCliente.substring(0,2).toUpperCase());
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ) {
					clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
					setEntidadSeleccionadaIf(clienteIf);
					getTxtEntidad().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());					
				}
			} else if ( tipoEntidad == TipoEntidadEnum.EMPLEADO ) {
				EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria("Empleados",Parametros.getIdEmpresa());
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ) {
					empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					setEntidadSeleccionadaIf(empleadoIf);
					getTxtEntidad().setText(empleadoIf.getCodigo() + " - " + empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
				}
			} else if ( tipoEntidad == TipoEntidadEnum.GENERICO ) {
				GenericoCriteria genericoCriteria = new GenericoCriteria();
				Map<String,Object> parameterMap = new HashMap<String,Object>();
				parameterMap.put("empresaId", Parametros.getIdEmpresa());
				parameterMap.put("estado", "A");
				genericoCriteria.setQueryBuilded(parameterMap);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), genericoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ) {
					genericoIf = (GenericoIf) popupFinder.getElementoSeleccionado();
					setEntidadSeleccionadaIf(genericoIf);
					getTxtEntidad().setText(genericoIf.getCodigo() + " - " + genericoIf.getNombre());
				}
			} else if ( tipoEntidad == TipoEntidadEnum.PRODUCTO ) {
				ProductoCriteria productoCriteria = new ProductoCriteria(true);
				Map<String,Object> parameterMap = new HashMap<String,Object>();
				parameterMap.put("estado", "A");
				productoCriteria.setQueryBuilded(parameterMap);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), productoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ) {
					productoIf = (ProductoIf) popupFinder.getElementoSeleccionado();
					setEntidadSeleccionadaIf(productoIf);
					GenericoIf genericoIf = null;
					try {
						genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
					
					if (genericoIf != null)
						getTxtEntidad().setText(productoIf.getCodigo() + " - " + genericoIf.getNombre());
					else
						getTxtEntidad().setText(productoIf.getCodigo() + " - GENÉRICO DESCONOCIDO");
				}
				productoCriteria = null;
				popupFinder = null;
			}else if ( tipoEntidad == TipoEntidadEnum.DOCUMENTO ) {
				DocumentoCriteria documentoCriteria = new DocumentoCriteria("Documentos");
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), documentoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if (popupFinder.getElementoSeleccionado() != null) {
					documentoIf = (DocumentoIf) popupFinder.getElementoSeleccionado();
					setEntidadSeleccionadaIf(documentoIf);
					getTxtEntidad().setText(documentoIf.getCodigo() + " - " + documentoIf.getNombre());
				}
				documentoCriteria = null;
				popupFinder = null;
			} else if ( tipoEntidad == TipoEntidadEnum.CUENTA_BANCARIA ) {
				CuentaBancariaCriteria cuentaBancariaCriteria = new CuentaBancariaCriteria("Cuentas Bancarias");
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), cuentaBancariaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if (popupFinder.getElementoSeleccionado() != null) {
					try {
						cuentaBancariaIf = (CuentaBancariaIf) popupFinder.getElementoSeleccionado();
						BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancariaIf.getBancoId());
						setEntidadSeleccionadaIf(cuentaBancariaIf);
						getTxtEntidad().setText(banco.getNombre() + " - " + cuentaBancariaIf.getCuenta());
					} catch(GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}
				cuentaBancariaCriteria = null;
				popupFinder = null;
			} else if ( tipoEntidad == TipoEntidadEnum.DEPARTAMENTO ){
				DepartamentoCriteria departamentoCriteria = new DepartamentoCriteria();
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("empresaId", Parametros.getIdEmpresa());
				departamentoCriteria.setQueryBuilded(mapa);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), departamentoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if (popupFinder.getElementoSeleccionado() != null) {
					departamentoIf = (DepartamentoIf) popupFinder.getElementoSeleccionado();
					getTxtEntidad().setText(departamentoIf.getCodigo() + " - " + departamentoIf.getNombre());
				}
				departamentoCriteria = null;
				popupFinder = null;
			} else if ( tipoEntidad == TipoEntidadEnum.RUBRO_EVENTUAL ){
				try {
					Map<String, Object> mapaRubros = new HashMap<String, Object>();
					mapaRubros.put("empresaId", Parametros.getIdEmpresa());
					mapaRubros.put("tipoRubro", "E");
					int tamanioLista = SessionServiceLocator.getRubroSessionService().getRubroListSize(mapaRubros);
					if ( tamanioLista > 0 ){
						RubroCriteria rubroCriteria = new RubroCriteria(null);
						rubroCriteria.setQueryBuilded(mapaRubros);
						rubroCriteria.setResultListSize(tamanioLista);
						JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), rubroCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
						if (popupFinder.getElementoSeleccionado() != null) {
							rubroIf = (RubroIf) popupFinder.getElementoSeleccionado();
							getTxtEntidad().setText(rubroIf.getNombre());
						}
					} else {
						SpiritAlert.createAlert("No existen Rubros Eventuales Disponibles !!", SpiritAlert.INFORMATION);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				} catch (Exception e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Erroa general en la búsqueda de Rubro !!", SpiritAlert.ERROR);
				}
			} else if ( tipoEntidad == TipoEntidadEnum.SRI_AIR ){
				SriAirCriteria sriAirCriteria = new SriAirCriteria();
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), sriAirCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if (popupFinder.getElementoSeleccionado() != null) {
					sriAirIf = (SriAirIf) popupFinder.getElementoSeleccionado();
					getTxtEntidad().setText("[" + sriAirIf.getPorcentaje().doubleValue() + "%] " + sriAirIf.getCodigo() + " - " + sriAirIf.getConcepto());
				}
				sriAirCriteria = null;
				popupFinder = null;
			} else if ( tipoEntidad == TipoEntidadEnum.OFICINA ) {
				OficinaCriteria oficinaCriteria = new OficinaCriteria();
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("empresaId", Parametros.getIdEmpresa());
				oficinaCriteria.setQueryBuilded(mapa);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), oficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if (popupFinder.getElementoSeleccionado() != null) {
					oficinaIf = (OficinaIf) popupFinder.getElementoSeleccionado();
					getTxtEntidad().setText(oficinaIf.getNombre());
				}
				oficinaCriteria = null;
				popupFinder = null;
			} else if ( tipoEntidad == TipoEntidadEnum.GASTO ){
				GastoCriteria gastoCriteria = new GastoCriteria();
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("empresaId", Parametros.getIdEmpresa());
				gastoCriteria.setQueryBuilded(mapa);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), gastoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if (popupFinder.getElementoSeleccionado() != null) {
					gastoIf = (GastoIf) popupFinder.getElementoSeleccionado();
					getTxtEntidad().setText(gastoIf.getNombre());
				}
				gastoCriteria = null;
				popupFinder = null;
			} else if (tipoEntidad == TipoEntidadEnum.ORIGEN_DOCUMENTO) {
				OrigenDocumentoCriteria origenDocumentoCriteria = new OrigenDocumentoCriteria();
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("empresaId", Parametros.getIdEmpresa());
				origenDocumentoCriteria.setQueryBuilded(mapa);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), origenDocumentoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if (popupFinder.getElementoSeleccionado() != null) {
					origenDocumentoIf = (OrigenDocumentoIf) popupFinder.getElementoSeleccionado();
					getTxtEntidad().setText(origenDocumentoIf.getNombre());
				}
				origenDocumentoCriteria = null;
				popupFinder = null;
			} else if ( tipoEntidad == TipoEntidadEnum.TIPO_SUSTENTO_TRIBUTARIO ) {
				SriSustentoTributarioCriteria sriSustentoTributarioCriteria = new SriSustentoTributarioCriteria("Tipo Sustento Tributario");
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(40);
				anchoColumnas.add(600);	
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), sriSustentoTributarioCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null ) {
					sriSustentoTributarioIf = (SriSustentoTributarioIf) popupFinder.getElementoSeleccionado();
					setEntidadSeleccionadaIf(sriSustentoTributarioIf);
					getTxtEntidad().setText(sriSustentoTributarioIf.getCodigo() + " - " + sriSustentoTributarioIf.getDescripcion());
				}
			}
			
			loadTable();
			getTxtCuenta().setText("");
			cuentaIf = null;
		}
	};
	
	ActionListener cmbTipoEntidadListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			getTxtEntidad().setText("");
			getTxtCuenta().setText("");
			cuentaIf = null;
			cleanTable();
		}
	};
	
	ActionListener cmbPlanCuentaListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getCmbPlanCuenta().getSelectedItem() != null) {
				verificarDatosGuardados();
				planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getModel().getSelectedItem();
				getTxtCuenta().setText("");
				getBtnBuscarCuenta().setEnabled(true);
			}
		}
	};
	
	private void eliminarDetalle() {
		int filaSeleccionada = getTblCuentaEntidad().getSelectedRow();
		if (filaSeleccionada >= 0) {
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if(opcion == JOptionPane.YES_OPTION) {
				CuentaEntidadIf bean = cuentaEntidadVector.get(filaSeleccionada);
				try {
					SessionServiceLocator.getCuentaEntidadSessionService().deleteCuentaEntidad(bean.getId());
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}
				cuentaEntidadVector.remove(filaSeleccionada);
				tableModel.removeRow(filaSeleccionada);
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.INFORMATION);
		}
	}
	
	private void verificarDatosGuardados() {
		if (!datosGuardados()) {
			int opcion = JOptionPane.showOptionDialog(null, "¿Existen datos sin guardar, desea guardarlos?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION){
				save();
			}
		}
		loadTable();
	}
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		try {
			if (((JTable)evt.getSource()).getSelectedRow() != -1) {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setRegistroSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
				cuentaEntidadSeleccionadaIf = (CuentaEntidadIf)  cuentaEntidadVector.get(getRegistroSeleccionado());
				getCmbTipoEntidad().removeActionListener(cmbTipoEntidadListener);
				//getCmbPlanCuenta().removeActionListener(cmbPlanCuentaListener);
				
				String tipoEntidadSeleccionada = cuentaEntidadSeleccionadaIf.getTipoEntidad();
				getCmbTipoEntidad().setSelectedItem(TipoEntidadEnum.getTipoEntidadByLetra(tipoEntidadSeleccionada));
				/*if (cuentaEntidadSeleccionadaIf.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.CLIENTE)))
					getCmbTipoEntidad().setSelectedItem(TipoEntidadEnum.CLIENTE);
				else if (cuentaEntidadSeleccionadaIf.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.PROVEEDOR)))
					getCmbTipoEntidad().setSelectedItem(TipoEntidadEnum.PROVEEDOR);
				else if (cuentaEntidadSeleccionadaIf.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.EMPLEADO)))
					getCmbTipoEntidad().setSelectedItem(TipoEntidadEnum.EMPLEADO);
				else if (cuentaEntidadSeleccionadaIf.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.GENERICO)))
					getCmbTipoEntidad().setSelectedItem(TipoEntidadEnum.GENERICO);
				else if (cuentaEntidadSeleccionadaIf.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.PRODUCTO))) 	// I por ITEM, puesto que la P de producto ya se utiliza para
					getCmbTipoEntidad().setSelectedItem(TipoEntidadEnum.PRODUCTO);				// representar a la entidad PROVEEDOR
				else if (cuentaEntidadSeleccionadaIf.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.DOCUMENTO)))
					getCmbTipoEntidad().setSelectedItem(TipoEntidadEnum.DOCUMENTO);
				else if (cuentaEntidadSeleccionadaIf.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.CUENTA_BANCARIA)))
					getCmbTipoEntidad().setSelectedItem(TipoEntidadEnum.CUENTA_BANCARIA);
				else if (cuentaEntidadSeleccionadaIf.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.DEPARTAMENTO)))
					getCmbTipoEntidad().setSelectedItem(TipoEntidadEnum.DEPARTAMENTO);
				else if (cuentaEntidadSeleccionadaIf.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.RUBRO_EVENTUAL)))
					getCmbTipoEntidad().setSelectedItem(TipoEntidadEnum.RUBRO_EVENTUAL);
				else if (cuentaEntidadSeleccionadaIf.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.SRI_AIR)))
					getCmbTipoEntidad().setSelectedItem(TipoEntidadEnum.SRI_AIR);
				else if (cuentaEntidadSeleccionadaIf.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.OFICINA)))
					getCmbTipoEntidad().setSelectedItem(TipoEntidadEnum.OFICINA);
				else if (cuentaEntidadSeleccionadaIf.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.GASTO)))
					getCmbTipoEntidad().setSelectedItem(TipoEntidadEnum.GASTO);*/
				
				if (tipoEntidadSeleccionada.equals(TipoEntidadEnum.CLIENTE.getLetra()) 
						|| tipoEntidadSeleccionada.equals(TipoEntidadEnum.PROVEEDOR.getLetra())) {
					clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(cuentaEntidadSeleccionadaIf.getEntidadId());
					setEntidadSeleccionadaIf(clienteIf);
					getTxtEntidad().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());		
				} else if (tipoEntidadSeleccionada.equals(TipoEntidadEnum.EMPLEADO.getLetra())) {
					empleadoIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(cuentaEntidadSeleccionadaIf.getEntidadId());
					setEntidadSeleccionadaIf(empleadoIf);
					getTxtEntidad().setText(empleadoIf.getCodigo() + " - " + empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
				} else if (tipoEntidadSeleccionada.equals(TipoEntidadEnum.GENERICO.getLetra())) {
					genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(cuentaEntidadSeleccionadaIf.getEntidadId());
					setEntidadSeleccionadaIf(genericoIf);
					getTxtEntidad().setText(genericoIf.getCodigo() + " - " + genericoIf.getNombre());
				} else if (tipoEntidadSeleccionada.equals(TipoEntidadEnum.PRODUCTO.getLetra())) {
					productoIf = SessionServiceLocator.getProductoSessionService().getProducto(cuentaEntidadSeleccionadaIf.getEntidadId());
					setEntidadSeleccionadaIf(productoIf);
					GenericoIf genericoIf = null;
					try {
						genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
					
					if (genericoIf != null)
						getTxtEntidad().setText(productoIf.getCodigo() + " - " + genericoIf.getNombre());
					else
						getTxtEntidad().setText(productoIf.getCodigo() + " - GENÉRICO DESCONOCIDO");
				} else if (tipoEntidadSeleccionada.equals(TipoEntidadEnum.DOCUMENTO.getLetra())) {
					documentoIf = SessionServiceLocator.getDocumentoSessionService().getDocumento(cuentaEntidadSeleccionadaIf.getEntidadId());
					setEntidadSeleccionadaIf(documentoIf);
					getTxtEntidad().setText(documentoIf.getCodigo() + " - " + documentoIf.getNombre());
				} else if (tipoEntidadSeleccionada.equals(TipoEntidadEnum.CUENTA_BANCARIA.getLetra())) {
					cuentaBancariaIf = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(cuentaEntidadSeleccionadaIf.getEntidadId());
					BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancariaIf.getBancoId());
					setEntidadSeleccionadaIf(cuentaBancariaIf);
					getTxtEntidad().setText(banco.getNombre() + " - " + cuentaBancariaIf.getCuenta());					
				} else if (tipoEntidadSeleccionada.equals(TipoEntidadEnum.DEPARTAMENTO.getLetra())) {
					departamentoIf = SessionServiceLocator.getDepartamentoSessionService().getDepartamento(cuentaEntidadSeleccionadaIf.getEntidadId());
					setEntidadSeleccionadaIf(departamentoIf);
					getTxtEntidad().setText(departamentoIf.getNombre());					
				} else if (tipoEntidadSeleccionada.equals(TipoEntidadEnum.RUBRO_EVENTUAL.getLetra())) {
					rubroIf = SessionServiceLocator.getRubroSessionService().getRubro(cuentaEntidadSeleccionadaIf.getEntidadId());
					setEntidadSeleccionadaIf(rubroIf);
					getTxtEntidad().setText(rubroIf.getNombre());					
				} else if (tipoEntidadSeleccionada.equals(TipoEntidadEnum.SRI_AIR.getLetra())) {
					sriAirIf = SessionServiceLocator.getSriAirSessionService().getSriAir(cuentaEntidadSeleccionadaIf.getEntidadId());
					setEntidadSeleccionadaIf(sriAirIf);
					getTxtEntidad().setText(sriAirIf.getCodigo() + " " + sriAirIf.getConcepto());					
				} else if (tipoEntidadSeleccionada.equals(TipoEntidadEnum.OFICINA.getLetra())) {
					oficinaIf = SessionServiceLocator.getOficinaSessionService().getOficina(cuentaEntidadSeleccionadaIf.getEntidadId());
					setEntidadSeleccionadaIf(oficinaIf);
					getTxtEntidad().setText(oficinaIf.getNombre());					
				} else if (tipoEntidadSeleccionada.equals(TipoEntidadEnum.GASTO.getLetra()) ){
					gastoIf = SessionServiceLocator.getGastoSessionService().getGasto(cuentaEntidadSeleccionadaIf.getEntidadId());
					setEntidadSeleccionadaIf(gastoIf);
					getTxtEntidad().setText(gastoIf.getNombre());	
				} else if (tipoEntidadSeleccionada.equals(TipoEntidadEnum.ORIGEN_DOCUMENTO.getLetra())) {
					origenDocumentoIf = SessionServiceLocator.getOrigenDocumentoSessionService().getOrigenDocumento(cuentaEntidadSeleccionadaIf.getEntidadId());
					setEntidadSeleccionadaIf(origenDocumentoIf);
					getTxtEntidad().setText(origenDocumentoIf.getNombre());
				} else if (tipoEntidadSeleccionada.equals(TipoEntidadEnum.TIPO_SUSTENTO_TRIBUTARIO.getLetra())) {
					sriSustentoTributarioIf = SessionServiceLocator.getSriSustentoTributarioSessionService().getSriSustentoTributario(cuentaEntidadSeleccionadaIf.getEntidadId());
					setEntidadSeleccionadaIf(sriSustentoTributarioIf);
					getTxtEntidad().setText(sriSustentoTributarioIf.getDescripcion());
				}
				
				getCmbNemonico().setSelectedItem(cuentaEntidadSeleccionadaIf.getNemonico());
				setCuentaSeleccionadaIf(SessionServiceLocator.getCuentaSessionService().getCuenta(cuentaEntidadSeleccionadaIf.getCuentaId()));
				getTxtCuenta().setText(getCuentaSeleccionadaIf().getCodigo() + " - " + getCuentaSeleccionadaIf().getNombre());
				
				//oficina
				List<OficinaIf> oficinas = (ArrayList<OficinaIf>)SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(Parametros.getIdEmpresa());
				int indice = 0;
				for(int i=0; i<oficinas.size(); i++){
					OficinaIf oficinaIf = oficinas.get(i);
					if(oficinaIf.getId().compareTo(cuentaEntidadSeleccionadaIf.getOficinaId()) == 0){
						indice = i;
					}
				}				
				getCmbOficina().setSelectedIndex(indice);
				getCmbOficina().repaint();
				
				getBtnActualizarRegistro().setEnabled(true);
				getCmbTipoEntidad().addActionListener(cmbTipoEntidadListener);
				getCmbPlanCuenta().addActionListener(cmbPlanCuentaListener);
				showUpdateMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void find() {
		// TODO Auto-generated method stub
	}
	
	public void save() {
		CuentaEntidadIf cuentaEntidad;
		try {
			for(int i=0; i < cuentaEntidadVector.size(); i++){
				cuentaEntidad = (CuentaEntidadIf) cuentaEntidadVector.get(i);
				CuentaEntidadData data = new CuentaEntidadData();
				data.setEntidadId(cuentaEntidad.getEntidadId());
				data.setTipoEntidad(cuentaEntidad.getTipoEntidad());
				data.setNemonico(cuentaEntidad.getNemonico());
				data.setCuentaId(cuentaEntidad.getCuentaId());
				data.setOficinaId(cuentaEntidad.getOficinaId());
				if (idActualizar.size() >= (i+1)) {
					data.setId(idActualizar.get(i));
					SessionServiceLocator.getCuentaEntidadSessionService().saveCuentaEntidad(data);
				} else {
					SessionServiceLocator.getCuentaEntidadSessionService().addCuentaEntidad(data);
				}
			}
			cuentaEntidadVector.removeAllElements();
			setEntidadSeleccionadaIf((EmpleadoIf) null);
			setEntidadSeleccionadaIf((ClienteIf) null);
			setEntidadSeleccionadaIf((GenericoIf) null);
			setEntidadSeleccionadaIf((ProductoIf) null);
			setEntidadSeleccionadaIf((DocumentoIf) null);
			setEntidadSeleccionadaIf((CuentaBancariaIf) null);
			setEntidadSeleccionadaIf((DepartamentoIf) null);
			setEntidadSeleccionadaIf((RubroIf) null);
			setEntidadSeleccionadaIf((SriAirIf) null);
			setEntidadSeleccionadaIf((GastoIf)null);
			setEntidadSeleccionadaIf((OrigenDocumentoIf)null);
			setEntidadSeleccionadaIf((OficinaIf)null);
			setDatosGuardados(true);
			SpiritAlert.createAlert("Cuenta por entidad guardada con éxito", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		CuentaEntidadIf cuentaEntidad;
		try {
			for(int i=0; i < cuentaEntidadVector.size(); i++){
				cuentaEntidad = (CuentaEntidadIf) cuentaEntidadVector.get(i);
				CuentaEntidadData data = new CuentaEntidadData();
				data.setEntidadId(cuentaEntidad.getEntidadId());
				data.setTipoEntidad(cuentaEntidad.getTipoEntidad());
				data.setNemonico(cuentaEntidad.getNemonico());
				data.setCuentaId(cuentaEntidad.getCuentaId());
				data.setOficinaId(cuentaEntidad.getOficinaId());
				if (idActualizar.size() >= (i+1)) {
					data.setId(idActualizar.get(i));
					SessionServiceLocator.getCuentaEntidadSessionService().saveCuentaEntidad(data);
				} else {
					SessionServiceLocator.getCuentaEntidadSessionService().addCuentaEntidad(data);
				}
			}
			cuentaEntidadVector.removeAllElements();
			setEntidadSeleccionadaIf((EmpleadoIf) null);
			setEntidadSeleccionadaIf((ClienteIf) null);
			setEntidadSeleccionadaIf((GenericoIf) null);
			setEntidadSeleccionadaIf((ProductoIf) null);
			setEntidadSeleccionadaIf((DocumentoIf) null);
			setEntidadSeleccionadaIf((CuentaBancariaIf) null);
			setEntidadSeleccionadaIf((DepartamentoIf) null);
			setEntidadSeleccionadaIf((RubroIf) null);
			setEntidadSeleccionadaIf((SriAirIf) null);
			setEntidadSeleccionadaIf((GastoIf)null);
			setEntidadSeleccionadaIf((OrigenDocumentoIf)null);
			setEntidadSeleccionadaIf((OficinaIf)null);
			setDatosGuardados(true);
			SpiritAlert.createAlert("Cuenta por entidad guardada con éxito", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void delete() {
		// TODO Auto-generated method stub
	}
	
	public void clean() {
		getTxtCuenta().setText("");
		getCmbPlanCuenta().setSelectedItem(null);
		getCmbNemonico().setEnabled(false);
		getBtnBuscarCuenta().setEnabled(false);
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblCuentaEntidad().getModel();
		for(int i= this.getTblCuentaEntidad().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void report() {
		// TODO Auto-generated method stub
	}
	
	public void refresh() {
		// TODO Auto-generated method stub
		cargarNemonicos();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public boolean validateFields() {
		if (getCmbTipoEntidad().getSelectedItem() == null) {
			SpiritAlert.createAlert("¡Debe seleccionar el tipo de entidad!", SpiritAlert.WARNING);
			getCmbTipoEntidad().grabFocus();
			return false;
		}
		
		if (getTxtEntidad().getText().equals("")) {
			SpiritAlert.createAlert("¡Debe ingresar la entidad!", SpiritAlert.WARNING);
			getBtnBuscarEntidad().grabFocus();
			return false;
		}
		
		if (getCmbNemonico().getSelectedItem() == null) {
			SpiritAlert.createAlert("¡Debe seleccionar el nemónico!", SpiritAlert.WARNING);
			getCmbNemonico().grabFocus();
			return false;
		}
		
		if (getTxtCuenta().getText().equals("")) {
			SpiritAlert.createAlert("¡Debe ingresar la cuenta!", SpiritAlert.WARNING);
			getBtnBuscarCuenta().grabFocus();
			return false;
		}
		
		/*if (esCuentaEntidadRepetida()) {
			SpiritAlert.createAlert("¡Esta asociación entidad/evento contable/nemónico ya existe!", SpiritAlert.WARNING);
			getBtnBuscarCuenta().grabFocus();
			return false;
		}*/
		
		return true;
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	/*private boolean esCuentaEntidadRepetida() {
		String nemonico = getCmbNemonico().getSelectedItem().toString();
		
		for (int i=0; i<cuentaEntidadVector.size(); i++) {
			CuentaEntidadIf cuentaEntidad = cuentaEntidadVector.get(i);
			if (cuentaEntidad.getNemonico().equals(nemonico) && cuentaEntidad.getOficinaId().compareTo(Parametros.getIdOficina()) == 0)
				if (esRegistroAgregado() || (!esRegistroAgregado() && i!=getRegistroSeleccionado()))
					return true;
		}
		
		return false;
	}*/
	
	public void showSaveMode() {
		setSaveMode();
		cuentaEntidadVector.removeAllElements();
		idActualizar.removeAllElements();
		getTxtEntidad().setEditable(false);
		getTxtEntidad().setText("");
		getTxtCuenta().setEditable(false);
		clean();
		cleanTable();
		cargarNemonicos();
	}
	
	public void showFindMode() {
		showSaveMode();
	}
	
	public void showUpdateMode() {
		setUpdateMode();
	}
	
	public void addDetail() {
		if (validateFields()) {
			CuentaEntidadData bean = new CuentaEntidadData();
			setCuentaEntidad(bean);
			TipoEntidadEnum tipoEntidad = (TipoEntidadEnum) getCmbTipoEntidad().getSelectedItem(); 
			if (tipoEntidad == TipoEntidadEnum.CLIENTE || tipoEntidad == TipoEntidadEnum.PROVEEDOR)
				getCuentaEntidad().setEntidadId(clienteIf.getId());
			else if (tipoEntidad == TipoEntidadEnum.EMPLEADO)
				getCuentaEntidad().setEntidadId(empleadoIf.getId());
			else if (tipoEntidad == TipoEntidadEnum.GENERICO)
				getCuentaEntidad().setEntidadId(genericoIf.getId());
			else if (tipoEntidad == TipoEntidadEnum.PRODUCTO)
				getCuentaEntidad().setEntidadId(productoIf.getId());
			else if (tipoEntidad == TipoEntidadEnum.DOCUMENTO)
				getCuentaEntidad().setEntidadId(documentoIf.getId());
			else if (tipoEntidad == TipoEntidadEnum.CUENTA_BANCARIA)
				getCuentaEntidad().setEntidadId(cuentaBancariaIf.getId());
			else if (tipoEntidad == TipoEntidadEnum.DEPARTAMENTO)
				getCuentaEntidad().setEntidadId(departamentoIf.getId());
			else if (tipoEntidad == TipoEntidadEnum.RUBRO_EVENTUAL)
				getCuentaEntidad().setEntidadId(rubroIf.getId());
			else if (tipoEntidad == TipoEntidadEnum.SRI_AIR)
				getCuentaEntidad().setEntidadId(sriAirIf.getId());
			else if (tipoEntidad == TipoEntidadEnum.OFICINA)
				getCuentaEntidad().setEntidadId(oficinaIf.getId());
			else if (tipoEntidad == TipoEntidadEnum.GASTO)
				getCuentaEntidad().setEntidadId(gastoIf.getId());
			else if (tipoEntidad == TipoEntidadEnum.ORIGEN_DOCUMENTO)
				getCuentaEntidad().setEntidadId(origenDocumentoIf.getId());
			else if (tipoEntidad == TipoEntidadEnum.TIPO_SUSTENTO_TRIBUTARIO)
				getCuentaEntidad().setEntidadId(sriSustentoTributarioIf.getId());
			
			getCuentaEntidad().setTipoEntidad( TipoEntidadEnum.getLetraTipoEntidad(tipoEntidad) );
			
			/*
			if (!getCmbTipoEntidad().getSelectedItem().equals("PRODUCTO") && !getCmbTipoEntidad().getSelectedItem().equals("CUENTA BANCARIA"))
				getCuentaEntidad().setTipoEntidad(getCmbTipoEntidad().getSelectedItem().toString().substring(0,1));
			else if (getCmbTipoEntidad().getSelectedItem().equals("PRODUCTO"))
				getCuentaEntidad().setTipoEntidad("I");
			else if (getCmbTipoEntidad().getSelectedItem().equals("CUENTA BANCARIA"))
				getCuentaEntidad().setTipoEntidad("B");
			*/
			
			getCuentaEntidad().setNemonico(getCmbNemonico().getSelectedItem().toString());
			getCuentaEntidad().setCuentaId(getCuentaSeleccionadaIf().getId());
			
			OficinaIf oficina = (OficinaIf)getCmbOficina().getSelectedItem();
			getCuentaEntidad().setOficinaId(oficina.getId());
			//getCuentaEntidad().setOficinaId(Parametros.getIdOficina());
			
			cuentaEntidadVector.add(getCuentaEntidad());
			
			Vector<String> fila = createRowTable(oficina.getNombre());
			tableModel.addRow(fila);
			
			getTxtCuenta().setText("");
			setDatosGuardados(false);
		}
	}
	
	private void loadTable() {
		try {
			if (getCmbPlanCuenta().getSelectedItem() != null) {
				if (getEntidadSeleccionadaIf() != null) {
					Map<String,Object> parameterMap = new HashMap<String,Object>();
					SpiritIf entidad = getEntidadSeleccionadaIf();
					parameterMap.put("entidadId", entidad.getId());
					TipoEntidadEnum tipoEntidad = (TipoEntidadEnum) getCmbTipoEntidad().getSelectedItem();
					//parameterMap.put("tipoEntidad", TipoEntidadEnum.getLetraTipoEntidad(tipoEntidad));
					parameterMap.put("tipoEntidad", tipoEntidad.getLetra());
					//parameterMap.put("oficinaId", Parametros.getIdOficina());
					/*
					if (!getCmbTipoEntidad().getSelectedItem().equals("PRODUCTO") && !getCmbTipoEntidad().getSelectedItem().equals("CUENTA BANCARIA"))
						parameterMap.put("tipoEntidad", getCmbTipoEntidad().getSelectedItem().toString().substring(0,1));
					else if (getCmbTipoEntidad().getSelectedItem().equals("PRODUCTO"))
						parameterMap.put("tipoEntidad", "I");
					else if (getCmbTipoEntidad().getSelectedItem().equals("CUENTA BANCARIA"))
						parameterMap.put("tipoEntidad", "B");
					*/
					Iterator cuentaEntidadIterator;
					
					//if (cuentaEntidadVector.isEmpty())
					cuentaEntidadIterator = SessionServiceLocator.getCuentaEntidadSessionService().findCuentaEntidadByQuery(parameterMap).iterator();
					//else
					//cuentaEntidadIterator = ((Vector<CuentaEntidadIf>) cuentaEntidadVector.clone()).iterator();
					
					idActualizar.clear();
					cuentaEntidadVector.clear();
					cleanTable();
					
					while (cuentaEntidadIterator.hasNext()) {
						CuentaEntidadIf cuentaEntidad = (CuentaEntidadIf) cuentaEntidadIterator.next();
						cuentaEntidadVector.add(cuentaEntidad);
						CuentaIf cuentaIf = SessionServiceLocator.getCuentaSessionService().getCuenta(cuentaEntidad.getCuentaId());
						//eventoContableIf = getEventoContableSessionService().getEventoContable(cuentaEntidad.getEventoContableId());
						Vector<String> fila = new Vector<String>();
						fila.add(cuentaIf.getCodigo());
						fila.add(cuentaIf.getNombre());
						fila.add(cuentaEntidad.getNemonico());
						
						OficinaIf oficina = SessionServiceLocator.getOficinaSessionService().getOficina(cuentaEntidad.getOficinaId());
						fila.add(oficina.getNombre());
						
						tableModel.addRow(fila);
						idActualizar.add(cuentaEntidad.getId());
					}
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	/*private void cargarNemonicos(EventoContableIf eventoContableIf, PlanCuentaIf planCuentaIf) throws GenericBusinessException {
	 Collection plantillas = getPlantillaSessionService().findPlantillaByEventoContableIdAndByPlanCuentaId(eventoContableIf.getId(), planCuentaIf.getId());
	 getCmbNemonico().removeAllItems();
	 
	 if (plantillas.isEmpty()) {
	 getCmbNemonico().setEnabled(false);
	 getBtnAgregarRegistro().setEnabled(false);
	 getBtnRemoverRegistro().setEnabled(false);
	 } else {
	 Iterator plantillasIterator = plantillas.iterator();
	 while (plantillasIterator.hasNext()) {
	 PlantillaIf plantilla = (PlantillaIf) plantillasIterator.next();
	 getCmbNemonico().addItem(plantilla.getNemonico());
	 }
	 getCmbNemonico().setEnabled(true);
	 getBtnAgregarRegistro().setEnabled(true);
	 getBtnRemoverRegistro().setEnabled(true);
	 getBtnBuscarCuenta().setEnabled(true);
	 }
	 }*/
	
	private void cargarNemonicos() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		Collection<String> nemonicos = SessionServiceLocator.getPlantillaSessionService().findNemonicosDePlantillas();
		for ( String nemonico : nemonicos ){
			model.addElement(nemonico);
		}
		model.addElement("GASTO");
		getCmbNemonico().setModel(model);
		getCmbNemonico().setEnabled(true);
		getBtnAgregarRegistro().setEnabled(true);
		getBtnRemoverRegistro().setEnabled(true);
		getBtnBuscarCuenta().setEnabled(true);
	}
	
	private Vector<String> createRowTable(String oficina) {
		Vector<String> fila = new Vector<String>();
		fila.add(getCuentaSeleccionadaIf().getCodigo());
		fila.add(getCuentaSeleccionadaIf().getNombre());
		fila.add(getCmbNemonico().getSelectedItem().toString());
		fila.add(oficina);
		return fila;
	}
	
	public void updateDetail() {
		int filaSeleccionada = getRegistroSeleccionado();
		if(filaSeleccionada >= 0 && validateFields()) {
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea actualizar la fila seleccionada?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				CuentaEntidadData bean = new CuentaEntidadData();
				
				setCuentaEntidad(bean);
				TipoEntidadEnum tipoEntidad = (TipoEntidadEnum) getCmbTipoEntidad().getSelectedItem();
				if (tipoEntidad == TipoEntidadEnum.CLIENTE || tipoEntidad == TipoEntidadEnum.PROVEEDOR)
					getCuentaEntidad().setEntidadId(clienteIf.getId());
				else if (tipoEntidad == TipoEntidadEnum.EMPLEADO)
					getCuentaEntidad().setEntidadId(empleadoIf.getId());
				else if (tipoEntidad == TipoEntidadEnum.GENERICO)
					getCuentaEntidad().setEntidadId(genericoIf.getId());
				else if (tipoEntidad == TipoEntidadEnum.PRODUCTO)
					getCuentaEntidad().setEntidadId(productoIf.getId());
				else if (tipoEntidad == TipoEntidadEnum.DOCUMENTO)
					getCuentaEntidad().setEntidadId(documentoIf.getId());
				else if (tipoEntidad == TipoEntidadEnum.CUENTA_BANCARIA)
					getCuentaEntidad().setEntidadId(cuentaBancariaIf.getId());
				else if (tipoEntidad == TipoEntidadEnum.DEPARTAMENTO)
					getCuentaEntidad().setEntidadId(departamentoIf.getId());
				else if (tipoEntidad == TipoEntidadEnum.RUBRO_EVENTUAL)
					getCuentaEntidad().setEntidadId(rubroIf.getId());
				else if (tipoEntidad == TipoEntidadEnum.SRI_AIR)
					getCuentaEntidad().setEntidadId(sriAirIf.getId());
				else if (tipoEntidad == TipoEntidadEnum.OFICINA)
					getCuentaEntidad().setEntidadId(oficinaIf.getId());
				else if (tipoEntidad == TipoEntidadEnum.GASTO)
					getCuentaEntidad().setEntidadId(gastoIf.getId());
				else if (tipoEntidad == TipoEntidadEnum.ORIGEN_DOCUMENTO)
					getCuentaEntidad().setEntidadId(origenDocumentoIf.getId());
				else if (tipoEntidad == TipoEntidadEnum.TIPO_SUSTENTO_TRIBUTARIO)
					getCuentaEntidad().setEntidadId(sriSustentoTributarioIf.getId());
				
				getCuentaEntidad().setTipoEntidad(TipoEntidadEnum.getLetraTipoEntidad(tipoEntidad));
				/*
				if (!getCmbTipoEntidad().getSelectedItem().equals("PRODUCTO") && !getCmbTipoEntidad().getSelectedItem().equals("CUENTA BANCARIA"))
					getCuentaEntidad().setTipoEntidad(getCmbTipoEntidad().getSelectedItem().toString().substring(0,1));
				else if (getCmbTipoEntidad().getSelectedItem().equals("PRODUCTO"))
					getCuentaEntidad().setTipoEntidad("I");
				else if (getCmbTipoEntidad().getSelectedItem().equals("CUENTA BANCARIA"))
					getCuentaEntidad().setTipoEntidad("B");
				*/
				getCuentaEntidad().setNemonico(getCmbNemonico().getSelectedItem().toString());
				getCuentaEntidad().setCuentaId(getCuentaSeleccionadaIf().getId());
				
				OficinaIf oficina = (OficinaIf)getCmbOficina().getSelectedItem();
				getCuentaEntidad().setOficinaId(oficina.getId());
				
				cuentaEntidadVector.add(filaSeleccionada, getCuentaEntidad());
				cuentaEntidadVector.remove(filaSeleccionada + 1);
				
				Vector<String> fila = createRowTable(oficina.getNombre());
				tableModel.insertRow(filaSeleccionada, fila);
				tableModel.removeRow(filaSeleccionada + 1);
				
				getTxtCuenta().setText("");
				getBtnActualizarRegistro().setEnabled(false);
				//clean();
				setRegistroSeleccionado(-1);
				getTblCuentaEntidad().removeRowSelectionInterval(0, getTblCuentaEntidad().getRowCount() - 1);
				setDatosGuardados(false);
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.INFORMATION);
		}
	}
	
	public void deleteDetail() {
		eliminarDetalle();
	}
	
	public CuentaEntidadIf getCuentaEntidad(){
		return cuentaEntidad;
	}
	
	public void setCuentaEntidad(CuentaEntidadIf cuentaEntidad) {
		this.cuentaEntidad =  cuentaEntidad;
	}
	
	public CuentaEntidadIf getCuentaEntidadSeleccionadaIf(){
		return cuentaEntidadSeleccionadaIf;
	}
	
	public void setCuentaEntidadSeleccionadaIf(CuentaEntidadIf cuentaEntidadSeleccionadaIf) {
		this.cuentaEntidadSeleccionadaIf =  cuentaEntidadSeleccionadaIf;
	}
	
	public CuentaIf getCuentaSeleccionadaIf(){
		return cuentaIf;
	}
	
	public void setCuentaSeleccionadaIf(CuentaIf cuentaSeleccionada) {
		this.cuentaIf =  cuentaSeleccionada;
	}
	
	public SpiritIf getEntidadSeleccionadaIf() {
		TipoEntidadEnum tipoEntidad = (TipoEntidadEnum) getCmbTipoEntidad().getSelectedItem();
		if (tipoEntidad == TipoEntidadEnum.CLIENTE || tipoEntidad == TipoEntidadEnum.PROVEEDOR)
			return clienteIf;
		else if (tipoEntidad == TipoEntidadEnum.EMPLEADO)
			return empleadoIf;
		else if (tipoEntidad == TipoEntidadEnum.GENERICO)
			return genericoIf;
		else if (tipoEntidad == TipoEntidadEnum.PRODUCTO)
			return productoIf;
		else if (tipoEntidad == TipoEntidadEnum.DOCUMENTO)
			return documentoIf;
		else if (tipoEntidad == TipoEntidadEnum.CUENTA_BANCARIA)
			return cuentaBancariaIf;
		else if (tipoEntidad == TipoEntidadEnum.DEPARTAMENTO)
			return departamentoIf;
		else if (tipoEntidad == TipoEntidadEnum.RUBRO_EVENTUAL)
			return rubroIf;
		else if (tipoEntidad == TipoEntidadEnum.SRI_AIR)
			return sriAirIf;
		else if (tipoEntidad == TipoEntidadEnum.OFICINA)
			return oficinaIf;
		else if (tipoEntidad == TipoEntidadEnum.GASTO)
			return gastoIf;
		else if (tipoEntidad == TipoEntidadEnum.ORIGEN_DOCUMENTO)
			return origenDocumentoIf;
		else if (tipoEntidad == TipoEntidadEnum.TIPO_SUSTENTO_TRIBUTARIO)
			return sriSustentoTributarioIf;
		return null;
	}
	
	public void setEntidadSeleccionadaIf(ClienteIf clienteSeleccionado) {
		this.clienteIf =  clienteSeleccionado;
	}
	
	public void setEntidadSeleccionadaIf(EmpleadoIf empleadoSeleccionado){
		this.empleadoIf =  empleadoSeleccionado;
	}
	
	public void setEntidadSeleccionadaIf(GenericoIf genericoSeleccionado) {
		this.genericoIf = genericoSeleccionado;
	}
	
	public void setEntidadSeleccionadaIf(ProductoIf productoSeleccionado) {
		this.productoIf =  productoSeleccionado;
	}
	
	public void setEntidadSeleccionadaIf(DocumentoIf documentoSeleccionado) {
		this.documentoIf =  documentoSeleccionado;
	}
	
	public void setEntidadSeleccionadaIf(CuentaBancariaIf cuentaBancariaSeleccionada) {
		this.cuentaBancariaIf =  cuentaBancariaSeleccionada;
	}
	
	public void setEntidadSeleccionadaIf(DepartamentoIf departamentoSeleccionado) {
		this.departamentoIf =  departamentoSeleccionado;
	}
	
	public void setEntidadSeleccionadaIf(RubroIf rubroEventualSeleccionado) {
		this.rubroIf =  rubroEventualSeleccionado;
	}
	
	public void setEntidadSeleccionadaIf(SriAirIf sriAirSeleccionado) {
		this.sriAirIf =  sriAirSeleccionado;
	}
	
	public void setEntidadSeleccionadaIf(OficinaIf oficinaSeleccionada) {
		this.oficinaIf =  oficinaSeleccionada;
	}
	
	public void setEntidadSeleccionadaIf(GastoIf gastoSeleccionado) {
		this.gastoIf =  gastoSeleccionado;
	}
	
	public void setEntidadSeleccionadaIf(OrigenDocumentoIf origenDocumentoSeleccionado) {
		this.origenDocumentoIf = origenDocumentoSeleccionado;
	}
	
	public void setEntidadSeleccionadaIf(SriSustentoTributarioIf sriSustentoTributarioSeleccionado) {
		this.sriSustentoTributarioIf = sriSustentoTributarioSeleccionado;
	}
	
	public int getRegistroSeleccionado() {
		return registroSeleccionado;
	}
	
	public void setRegistroSeleccionado(int registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}
	
	public boolean esRegistroAgregado() {
		return registroAgregado;
	}
	
	public void setRegistroAgregado(boolean registroAgregado) {
		this.registroAgregado = registroAgregado;
	}
	
	public boolean datosGuardados() {
		return datosGuardados;
	}
	
	public void setDatosGuardados(boolean datosGuardados) {
		this.datosGuardados = datosGuardados;
	}
 
}