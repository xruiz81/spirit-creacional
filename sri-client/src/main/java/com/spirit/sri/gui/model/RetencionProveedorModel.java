package com.spirit.sri.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.criteria.CuentaCriteria;
import com.spirit.contabilidad.gui.model.PlanCuentaModel;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.sri.entity.SriProveedorRetencionData;
import com.spirit.sri.entity.SriProveedorRetencionIf;
import com.spirit.sri.gui.panel.JPRetencionProveedor;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.Utilitarios;

public class RetencionProveedorModel extends JPRetencionProveedor {
	
	
	private static final long serialVersionUID = 2579588931091095038L;
	
	private final int MAX_LONGITUD_RETENCION_FUENTE = 6;
	private final int MAX_LONGITUD_RETENCION_IVA = 6;
	
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_ACTIVO = "A";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_INACTIVO = "I";
	
	private SriProveedorRetencionIf proveedorRetencionIf = null;
	private ArrayList<SriProveedorRetencionIf> proveedorRetencionVector = new ArrayList<SriProveedorRetencionIf>();
	private int filaSeleccionada = -1;
	private DecimalFormat formatoDecimal = new DecimalFormat("###.##");
	
	private CuentaIf cuentaRetencionFuente;
	private CuentaIf cuentaRetencionIva;
	
	private Map<Long, CuentaIf> mapaCuentaRetencionFuente = null;
	private Map<Long, CuentaIf> mapaCuentaRetencioniva = null;
	private Map<Long, PlanCuentaIf> mapaPlanCuenta = null;
	
	private JDPopupFinderModel popupFinder;
	private CuentaCriteria cuentaCriteria;
	
	public RetencionProveedorModel(){
		initListeners();
		iniciarComponentes();
		anchoColumnasTabla();
		showSaveMode();
		cargarCombos();
		new HotKeyComponent(this);
		setSorterTable(getTblProveedorRetencion());
	}
	
	public void cargarCombos(){
		cargarComboPlanCuenta();
		cargarComboRetencionFuente();
		cargarComboRetencionIva();
	}
	
	private void cargarComboPlanCuenta(){
		List planesCuentas = ContabilidadFinder.findPlanCuentaActivo(Parametros.getIdEmpresa());
		refreshCombo(getCmbPlanCuenta(),planesCuentas);
		if (getCmbPlanCuenta().getSelectedItem() == null){
			PlanCuentaModel.seleccionarPlanCuentaPredeterminado(getCmbPlanCuenta());
		}
	}
	
	public void cargarComboRetencionFuente(){
		try {
			ArrayList sriAirPorcentajes = (ArrayList)SessionServiceLocator.getSriAirSessionService().getPorcentajes();
			refreshCombo(getCmbRetencionFuente(),sriAirPorcentajes);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarComboRetencionIva(){
		try {
			ArrayList sriIvaPorcentajesList = null;
			if(getCmbBienServicio().getSelectedItem().equals("BIEN")){
				sriIvaPorcentajesList = (ArrayList) SessionServiceLocator.getSriIvaBienSessionService().getDescripcionPorcentaje();
			}else{
				sriIvaPorcentajesList = (ArrayList) SessionServiceLocator.getSriIvaServicioSessionService().getDescripcionPorcentaje();
			}			
			refreshCombo(getCmbRetencionIva(), sriIvaPorcentajesList);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void initListeners() {
		getTblProveedorRetencion().addMouseListener(oMouseAdapterTblRetencionProveedor);
		getTblProveedorRetencion().addKeyListener(oKeyAdapterTblRetencionProveedor);
		getCmbBienServicio().addActionListener(oActionListenerCmbBienServicio);
		getBtnCuentaRetencionFuente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				cuentaRetencionFuente = actionListenerBtnCuenta(getTxtCuentaRetencionFuente());
			}	
		});
		
		getBtnCuentaRetencionIva().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				cuentaRetencionIva = actionListenerBtnCuenta(getTxtCuentaRetencionIva());
			}			
		});
		
		getTxtCuentaRetencionFuente().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				cuentaRetencionFuente = keyListenerTxtCuenta(e, getTxtCuentaRetencionFuente());
			}
		});
		
		getTxtCuentaRetencionIva().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				cuentaRetencionIva = keyListenerTxtCuenta(e, getTxtCuentaRetencionIva());
			}
		});
	}
	
	private CuentaIf actionListenerBtnCuenta(JTextField txtCuenta) {
		CuentaIf cuenta = null;
		if (getCmbPlanCuenta().getSelectedItem() != null) {
			PlanCuentaIf planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getSelectedItem();
			cuentaCriteria = new CuentaCriteria("Plan de Cuentas", "S", planCuenta.getId(), ((UsuarioIf) Parametros.getUsuarioIf()).getId(), ESTADO_ACTIVO);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),cuentaCriteria,JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if (popupFinder.getElementoSeleccionado() != null) {
				cuenta = (CuentaIf) popupFinder.getElementoSeleccionado();
				txtCuenta.setText(cuenta.getCodigo() + " - " + cuenta.getNombre());
			}
		}else{
			SpiritAlert.createAlert("Debe seleccionar un Plan de Cuentas", SpiritAlert.WARNING);
		}
		return cuenta;
	}		
	
	private CuentaIf keyListenerTxtCuenta(KeyEvent e, JTextField txtCuenta) {
		CuentaIf cuenta = null;
		try {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				Map<String,Object> parameterMap = new HashMap<String,Object>();
				String strCuenta = txtCuenta.getText();
				String strCodigoNombreCuenta = "";
				
				if (strCuenta.contains(" - "))
					strCodigoNombreCuenta = strCuenta.split(" - ")[0] + "%";
				else 
					strCodigoNombreCuenta = strCuenta + "%";
				
				parameterMap.put("codigoNombreCuenta", strCodigoNombreCuenta);
				if (getCmbPlanCuenta().getSelectedItem() != null)
					parameterMap.put("plancuentaId", ((PlanCuentaIf) getCmbPlanCuenta().getSelectedItem()).getId());
				parameterMap.put("imputable", "S");
				parameterMap.put("estado", ESTADO_ACTIVO);
				
				int tamanoLista = SessionServiceLocator.getCuentaSessionService().findCuentaByQueryByUsuarioIdAndCodigoOrNombreListSize(parameterMap, ((UsuarioIf) Parametros.getUsuarioIf()).getId());
				CuentaIf cuentaIf = null;
				if (tamanoLista == 1) {
					Iterator cuentaIterator = SessionServiceLocator.getCuentaSessionService().findCuentaByQueryByUsuarioIdAndCodigoOrNombre(parameterMap, ((UsuarioIf) Parametros.getUsuarioIf()).getId()).iterator();
					if (cuentaIterator.hasNext()) {
						cuentaIf = (CuentaIf) cuentaIterator.next();
						cuenta = cuentaIf;
						txtCuenta.setText(cuenta.getCodigo() + " - " + cuenta.getNombre());
					}
				} else if (tamanoLista > 1) {
					CuentaCriteria cuentaCriteria = new CuentaCriteria();
					cuentaCriteria.setIdUsuario(((UsuarioIf) Parametros.getUsuarioIf()).getId());
					cuentaCriteria.setResultListSize(tamanoLista);
					cuentaCriteria.setQueryBuilded(parameterMap);
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), cuentaCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
					if ( popupFinder.getElementoSeleccionado() != null ) {
						cuentaIf = (CuentaIf) popupFinder.getElementoSeleccionado();
						txtCuenta.setText(cuentaIf.getCodigo() + " - " + cuentaIf.getNombre());
					}
				} 
				
				if (cuentaIf == null) {
					cuenta = null;
					if (tamanoLista <= 0)
						SpiritAlert.createAlert("No se halló la cuenta deseada en el plan de cuentas seleccionado", SpiritAlert.WARNING);
				} else {
					cuenta = cuentaIf;
				}
			}
		} catch (GenericBusinessException ex) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			ex.printStackTrace();
		}
		return cuenta;
	}
	
	ActionListener oActionListenerCmbBienServicio = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			cargarComboRetencionIva();
		}
	};
	
	private void iniciarComponentes(){
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setEditable(false);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblProveedorRetencion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblProveedorRetencion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblProveedorRetencion().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblProveedorRetencion().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(110);
		anchoColumna = getTblProveedorRetencion().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(120);
		anchoColumna = getTblProveedorRetencion().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblProveedorRetencion().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(130);
		anchoColumna = getTblProveedorRetencion().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(130);
	}
	
	MouseListener oMouseAdapterTblRetencionProveedor = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblRetencionProveedor = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (getTblProveedorRetencion().getSelectedRow() != -1) {
			try {
				int selectedRow = getTblProveedorRetencion().getSelectedRow();
				filaSeleccionada =  getTblProveedorRetencion().convertRowIndexToModel(selectedRow); 
				proveedorRetencionIf = proveedorRetencionVector.get(filaSeleccionada);
				
				getCmbTipoPersona().setSelectedItem(proveedorRetencionIf.getTipoPersona().equals("N")?"NATURAL":"JURIDICA");
				getCmbContabilidad().setSelectedItem(proveedorRetencionIf.getLlevaContabilidad().equals("S")?"SI":"NO");
				getCmbBienServicio().setSelectedItem(proveedorRetencionIf.getBienServicio().equals("B")?"BIEN":"SERVICIO");
				
				if(proveedorRetencionIf.getEstado().equals(ESTADO_ACTIVO)){
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
				}else if(proveedorRetencionIf.getEstado().equals(ESTADO_INACTIVO)){
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
				}
				getCmbEstado().repaint();
				
				BigDecimal reteFuente = proveedorRetencionIf.getRetefuente();
				getCmbRetencionFuente().setSelectedItem(reteFuente);
				getCmbRetencionFuente().repaint();
				BigDecimal reteIva = proveedorRetencionIf.getReteiva();
				getCmbRetencionIva().setSelectedItem(reteIva.toString());
				getCmbRetencionIva().repaint();
				
				Date fechaInicio = proveedorRetencionIf.getFechaInicio();;
				getCmbFechaInicio().setDate(fechaInicio!=null?fechaInicio:new GregorianCalendar().getTime());
				Date fechaFin = proveedorRetencionIf.getFechaFin();
				getCmbFechaFin().setDate(fechaFin!=null?fechaFin:null);
				
				if(proveedorRetencionIf.getIdCuentaRetefuente() != null){
					//cuentaRetencionFuente = getCuentaSessionService().getCuenta(proveedorRetencionIf.getIdCuentaRetefuente());
					cuentaRetencionFuente = verificarCuentaRetencion(mapaCuentaRetencionFuente, proveedorRetencionIf.getIdCuentaRetefuente());
					getTxtCuentaRetencionFuente().setText(cuentaRetencionFuente.getCodigo() + " - " + cuentaRetencionFuente.getNombre());
				}else{
					getTxtCuentaRetencionFuente().setText("");
				}
				if(proveedorRetencionIf.getIdCuentaReteiva() != null){
					//cuentaRetencionIva = getCuentaSessionService().getCuenta(proveedorRetencionIf.getIdCuentaReteiva());
					cuentaRetencionIva = verificarCuentaRetencion(mapaCuentaRetencioniva, proveedorRetencionIf.getIdCuentaReteiva());
					getTxtCuentaRetencionIva().setText(cuentaRetencionIva.getCodigo() + " - " + cuentaRetencionIva.getNombre());
				}else{
					getTxtCuentaRetencionIva().setText("");
				}
				if(cuentaRetencionFuente != null){
					//PlanCuentaIf planCuenta = getPlanCuentaSessionService().getPlanCuenta(cuentaRetencionFuente.getPlancuentaId());
					PlanCuentaIf planCuenta = verificarPlanCuenta(mapaPlanCuenta, cuentaRetencionFuente.getPlancuentaId());
					getCmbPlanCuenta().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPlanCuenta(), planCuenta.getId()));
				}else if(cuentaRetencionIva != null){
					//PlanCuentaIf planCuenta = getPlanCuentaSessionService().getPlanCuenta(cuentaRetencionIva.getPlancuentaId());
					PlanCuentaIf planCuenta = verificarPlanCuenta(mapaPlanCuenta, cuentaRetencionIva.getPlancuentaId());
					getCmbPlanCuenta().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPlanCuenta(), planCuenta.getId()));
				}					
				getCmbPlanCuenta().repaint();	
				
				showUpdateMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}
	}
	
	private CuentaIf verificarCuentaRetencion( Map<Long, CuentaIf> mapaCuenta , Long cuentaId ) throws GenericBusinessException{
		CuentaIf cuentaIf = null; 
		if (  cuentaId != null ){
			cuentaIf = mapaCuenta.get(cuentaId);
			if ( cuentaIf == null ){
				cuentaIf = SessionServiceLocator.getCuentaSessionService().getCuenta(cuentaId);
				mapaCuenta.put(cuentaIf.getId(), cuentaIf);
			}
		}
		return cuentaIf;
	}
	
	private PlanCuentaIf verificarPlanCuenta( Map<Long, PlanCuentaIf> mapa, Long planCuentaId ) throws GenericBusinessException{
		PlanCuentaIf planCuentaIf = null; 
		if (  planCuentaId != null ){
			planCuentaIf = mapa.get(planCuentaId);
			if ( planCuentaIf == null ){
				planCuentaIf = SessionServiceLocator.getPlanCuentaSessionService().getPlanCuenta(planCuentaId);
				mapa.put(planCuentaIf.getId(), planCuentaIf);
			}
		}
		return planCuentaIf;
	}

	public void clean() {
		cuentaRetencionFuente = null;
		cuentaRetencionIva = null;
		
		cargarMapaCuentasRetenciones();
		
		getCmbEstado().setSelectedIndex(0);
		getCmbTipoPersona().setSelectedIndex(0);
		getCmbContabilidad().setSelectedIndex(0);
		getCmbBienServicio().setSelectedIndex(0);
		Date fechaHoy = new GregorianCalendar().getTime();
		getCmbFechaInicio().setDate(fechaHoy);
		getCmbFechaFin().setDate(fechaHoy);
		
		getCmbRetencionFuente().setSelectedIndex(-1);
		getCmbRetencionIva().setSelectedIndex(-1);
		getCmbRetencionFuente().repaint();
		getCmbRetencionIva().repaint();
		getTxtCuentaRetencionFuente().setText("");
		getTxtCuentaRetencionIva().setText("");
		
		proveedorRetencionVector = null;
		proveedorRetencionVector = new ArrayList<SriProveedorRetencionIf>();
		
		//Vacio la tabla
		limpiarTabla(getTblProveedorRetencion());
	}

	public void delete() {
		try {
			SriProveedorRetencionIf proveedorRetencion = proveedorRetencionVector.get(filaSeleccionada);
			SessionServiceLocator.getProveedorRetencionSessionService().deleteSriProveedorRetencion(proveedorRetencion.getId());
			SpiritAlert.createAlert("Retenci\u00f3n de Proveedor eliminado con \u00e9xito", SpiritAlert.INFORMATION);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				SriProveedorRetencionData data = new SriProveedorRetencionData();
				
				data.setEstado(getCmbEstado().getSelectedItem().toString().substring(0, 1));
				data.setTipoPersona(getCmbTipoPersona().getSelectedItem().toString().substring(0, 1));
				data.setLlevaContabilidad(getCmbContabilidad().getSelectedItem().toString().substring(0, 1));
				data.setBienServicio(getCmbBienServicio().getSelectedItem().toString().substring(0, 1));
				
				data.setRetefuente((BigDecimal)getCmbRetencionFuente().getSelectedItem());
				String retenIvaS = (String)getCmbRetencionIva().getSelectedItem();
				data.setReteiva(BigDecimal.valueOf(Double.parseDouble(retenIvaS)));
				
				Date fecha = getCmbFechaInicio().getDate();
				data.setFechaInicio(new java.sql.Date(fecha.getTime()));
				fecha = getCmbFechaFin().getDate();
				data.setFechaFin(fecha!=null?new java.sql.Date(fecha.getTime()):null);
				
				BigDecimal reteFuente = (BigDecimal)getCmbRetencionFuente().getSelectedItem();
				if(reteFuente.compareTo(BigDecimal.valueOf(Double.valueOf(0))) == 0){
					data.setIdCuentaRetefuente(null);
				}else{
					data.setIdCuentaRetefuente(cuentaRetencionFuente.getId());
				}
				String reteIvaS = (String)getCmbRetencionIva().getSelectedItem();
				BigDecimal reteIva = BigDecimal.valueOf(Double.valueOf(reteIvaS));
				if(reteIva.compareTo(BigDecimal.valueOf(Double.valueOf(0))) == 0){
					data.setIdCuentaReteiva(null);
				}else{
					data.setIdCuentaReteiva(cuentaRetencionIva.getId());
				}
				
				SessionServiceLocator.getProveedorRetencionSessionService().addSriProveedorRetencion(data);
				SpiritAlert.createAlert("Retenci\u00f3n de Proveedor guardado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la infomaci\u00f3n!", SpiritAlert.ERROR);
		}
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getCmbTipoPersona().grabFocus();
	}
	
	private void cargarTabla() {
		long start=System.currentTimeMillis();
		 
		
		try {			
			DefaultTableModel tableModel = (DefaultTableModel) getTblProveedorRetencion().getModel();

//Collection<SriProveedorRetencionIf> proveedorRetencionesExistentes =SessionServiceLocator.getProveedorRetencionSessionService().getSriProveedorRetencionList(); 
			Collection<SriProveedorRetencionIf> proveedorRetencionesExistentes =
				SessionServiceLocator.getProveedorRetencionSessionService().findSriProveedorRetencionByQueryAndEmpresaId(Parametros.getIdEmpresa());
			
			for ( SriProveedorRetencionIf sustentoTributario : proveedorRetencionesExistentes ){

				Vector<String> fila = new Vector<String>();
				proveedorRetencionVector.add(sustentoTributario);
				agregarColumnasTabla(sustentoTributario, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblProveedorRetencion(), proveedorRetencionesExistentes, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		long fin=System.currentTimeMillis();
		System.out.println("---------------------TIEMPO TOTAL: "+(fin-start)/1000+" seg");
		
	}
	
	private void agregarColumnasTabla(SriProveedorRetencionIf proveedorRetencion, Vector<String> fila){
		String estado = proveedorRetencion.getEstado();
		fila.add(estado.equals("A")?"ACTIVO":"INACTIVO");
		String tipoPersona = proveedorRetencion.getTipoPersona(); 
		fila.add(tipoPersona.equals("N")?"NATURAL":"JURIDICA");
		String contabilidad = proveedorRetencion.getLlevaContabilidad();
		fila.add(contabilidad.equals("S")?"SI":"NO");
		String bienServicio = proveedorRetencion.getBienServicio();
		fila.add(bienServicio.equals("B")?"BIEN":"SERVICIO");
		BigDecimal reteFuente = proveedorRetencion.getRetefuente();
		fila.add(reteFuente!=null?formatoDecimal.format(reteFuente):"");
		BigDecimal reteIva = proveedorRetencion.getReteiva();
		fila.add(reteIva!=null?formatoDecimal.format(reteIva):"");
		Date fechaInicio = proveedorRetencion.getFechaInicio();
		fila.add(fechaInicio!=null?Utilitarios.getFechaUppercase(fechaInicio):"");
		Date fechaFin = proveedorRetencion.getFechaFin();
		fila.add(fechaFin!=null?Utilitarios.getFechaUppercase(fechaFin):"");
	}

	public void update() {
		try {	
			if (validateFields()) {
				proveedorRetencionIf.setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
				proveedorRetencionIf.setTipoPersona(getCmbTipoPersona().getSelectedItem().toString().substring(0,1));
				proveedorRetencionIf.setLlevaContabilidad(getCmbContabilidad().getSelectedItem().toString().substring(0,1));
				proveedorRetencionIf.setBienServicio(getCmbBienServicio().getSelectedItem().toString().substring(0,1));
				
				proveedorRetencionIf.setRetefuente((BigDecimal)getCmbRetencionFuente().getSelectedItem());
				String retenIvaS = (String)getCmbRetencionIva().getSelectedItem();
				proveedorRetencionIf.setReteiva(BigDecimal.valueOf(Double.parseDouble(retenIvaS)));
				
				Date fecha = getCmbFechaInicio().getDate();
				proveedorRetencionIf.setFechaInicio(new java.sql.Date(fecha.getTime()));
				fecha = getCmbFechaFin().getDate();
				proveedorRetencionIf.setFechaFin(fecha!=null?new java.sql.Date(fecha.getTime()):null);
				
				BigDecimal reteFuente = (BigDecimal)getCmbRetencionFuente().getSelectedItem();
				if(reteFuente.compareTo(BigDecimal.valueOf(Double.valueOf(0))) == 0){
					proveedorRetencionIf.setIdCuentaRetefuente(null);
				}else{
					proveedorRetencionIf.setIdCuentaRetefuente(
							cuentaRetencionFuente!=null?cuentaRetencionFuente.getId():null );
				}
				String reteIvaS = (String)getCmbRetencionIva().getSelectedItem();
				BigDecimal reteIva = BigDecimal.valueOf(Double.valueOf(reteIvaS));
				if(reteIva.compareTo(BigDecimal.valueOf(Double.valueOf(0))) == 0){
					proveedorRetencionIf.setIdCuentaReteiva(null);
				}else{
					proveedorRetencionIf.setIdCuentaReteiva(
							cuentaRetencionIva!=null?cuentaRetencionIva.getId():null );
				}
				
				SessionServiceLocator.getProveedorRetencionSessionService().saveSriProveedorRetencion(proveedorRetencionIf);
				SpiritAlert.createAlert("Retenci\u00f3n de Proveedor actualizado con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!", SpiritAlert.ERROR);
		}	
	}

	public boolean validateFields() {
		if(getCmbRetencionFuente().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar un valor para la Retenci\u00f3n en la Fuente !!", SpiritAlert.WARNING);
			getCmbRetencionFuente().grabFocus();
			return false;
		}		
		if(getCmbRetencionIva().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un valor para la Retenci\u00f3n en el IVA !!", SpiritAlert.WARNING);
			getCmbRetencionIva().grabFocus();
			return false;
		}
		if(getCmbFechaInicio().getDate()==null){
			SpiritAlert.createAlert("Debe ingresar una fecha v\u00e1lida de inicio!!", SpiritAlert.WARNING);
			getCmbFechaInicio().grabFocus();
			return false;
		}
		
		Collection<SriProveedorRetencionIf> proveedorRetencionesExistentes = null;
		try {
			Map<String, String> mapa = new HashMap<String, String>();
			mapa.put("estado", "A");
			proveedorRetencionesExistentes = SessionServiceLocator.getProveedorRetencionSessionService().findSriProveedorRetencionByQuery(mapa);
		
			String tipoPersona = getCmbTipoPersona().getSelectedItem().toString().substring(0,1);
			String contabilidad = getCmbContabilidad().getSelectedItem().toString().substring(0,1);
			String bienServicio = getCmbBienServicio().getSelectedItem().toString().substring(0,1);
			Double fuente,iva;
		
			fuente = Double.valueOf(((BigDecimal)getCmbRetencionFuente().getSelectedItem()).doubleValue());
			iva = Double.valueOf(getCmbRetencionIva().getSelectedItem().toString());
		
		 	for (SriProveedorRetencionIf proveedorRetencion : proveedorRetencionesExistentes){
				
				if (this.getMode() == SpiritMode.SAVE_MODE){
					if ( tipoPersona.equals(proveedorRetencion.getTipoPersona()) 
							&& contabilidad.equals(proveedorRetencion.getLlevaContabilidad())
							&& bienServicio.equals(proveedorRetencion.getBienServicio())
							&& fuente == proveedorRetencion.getRetefuente().doubleValue()
							&& iva == proveedorRetencion.getReteiva().doubleValue()
							&& getCmbFechaInicio().getDate() == proveedorRetencion.getFechaInicio()
							){
						SpiritAlert.createAlert("La retenci\u00f3n ingresada est\u00e1 duplicada !!", SpiritAlert.WARNING);
						return false;
					}
						
				}
				
				if (this.getMode() == SpiritMode.UPDATE_MODE){
					if ( tipoPersona.equals(proveedorRetencion.getTipoPersona()) 
							&& contabilidad.equals(proveedorRetencion.getLlevaContabilidad())
							&& bienServicio.equals(proveedorRetencion.getBienServicio())
							&& fuente == proveedorRetencion.getRetefuente().doubleValue()
							&& iva == proveedorRetencion.getReteiva().doubleValue()
							&& getCmbFechaInicio().getDate() == proveedorRetencion.getFechaInicio()
							){
						if (proveedorRetencion.getId() != proveedorRetencionIf.getId()){
							SpiritAlert.createAlert("La retenci\u00f3n ingresada est\u00e1 duplicada !!", SpiritAlert.WARNING);
							return false;
						}
					}
				}
			}
			
			BigDecimal reteFuente = (BigDecimal)getCmbRetencionFuente().getSelectedItem();
			if(reteFuente.compareTo(BigDecimal.valueOf(Double.valueOf(0))) == 1){
				if ((getTxtCuentaRetencionFuente().getText() == null) || getTxtCuentaRetencionFuente().getText().equals("")){
					SpiritAlert.createAlert("Debe ingresar la Cuenta de Retención en la Fuente!", SpiritAlert.WARNING);
					getTxtCuentaRetencionFuente().grabFocus();
					return false;
				}
			}		
			String reteIvaS = (String)getCmbRetencionIva().getSelectedItem();
			if(!reteIvaS.equals("0")){
				if ((getTxtCuentaRetencionIva().getText() == null) || getTxtCuentaRetencionIva().getText().equals("")){
					SpiritAlert.createAlert("Debe ingresar la Cuenta de Retención del Iva!", SpiritAlert.WARNING);
					getTxtCuentaRetencionIva().grabFocus();
					return false;
				}
			}
			
		} catch(NumberFormatException e){
			SpiritAlert.createAlert("Debe ingresar un valor num\u00e9rico en los porcentajes", SpiritAlert.ERROR);
			return false;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			return false;
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al consultar los Modelos de Retenci\u00f3n de Proveedor", SpiritAlert.ERROR);
			return false;
		}
		
		return true;
	}
	
	private void cargarMapaCuentasRetenciones(){

		mapaCuentaRetencionFuente = null;
		mapaCuentaRetencionFuente = new HashMap<Long, CuentaIf>();
		mapaCuentaRetencioniva = null;
		mapaCuentaRetencioniva = new HashMap<Long, CuentaIf>();
		
		mapaPlanCuenta = null;
		mapaPlanCuenta = new HashMap<Long, PlanCuentaIf>();
	}
	
	
	
	public void refresh() {
		cargarComboPlanCuenta();
		cargarComboRetencionFuente();
		cargarComboRetencionIva();
		cargarMapaCuentasRetenciones();
	}
	
}
