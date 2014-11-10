package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.general.util.Contratos;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoData;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.criteria.ContratoCriteria;
import com.spirit.nomina.gui.panel.JPRolContrato;
import com.spirit.nomina.gui.util.EstadoRolPago;
import com.spirit.nomina.gui.util.ModelUtil;
import com.spirit.nomina.gui.util.RolPagoUtil;
import com.spirit.nomina.handler.EstadoRolPagoDetalle;
import com.spirit.nomina.handler.OperacionNomina;
import com.spirit.nomina.handler.RolPagoContratoDatos;
import com.spirit.nomina.handler.RubroContratoDatos;
import com.spirit.nomina.handler.TipoRol;
import com.spirit.nomina.handler.UtilesNomina;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.Utilitarios;

public class RolContratoModel extends JPRolContrato {

	private static final long serialVersionUID = -7292916188285266727L;
	private DecimalFormat formatoDosEnteros = new DecimalFormat("00");
	private TipoRolIf tipoRolIf = null;
	private String mesSeleccionado = "";
	private String anioSeleccionado = "";
	private Map<String,Object> mapaParametros = null;
	private Boolean rolGenerado = false;
	private RolPagoIf rolPagoIf = null;
	private EmpleadoIf empleadoIf = null;
	private ContratoIf contratoIf = null;
	Collection<TipoRolIf> tiposRolCollection = null;
	private Collection<Map<String, Object>> rolPagoDetalleCollection = null;
	
	private Map<Long,RolPagoContratoDatos> rolPagoDetalleMapaTodo = null;
	private Double totalIngresos = 0.0;
	private Double totalEgresos = 0.0;

	private Map<Long, RubroIf> mapaRubros = new HashMap<Long, RubroIf>();

	private TipoRol tipoRol; 
	private TipoContratoIf tipoContratoIf = null;

	public RolContratoModel(){
		iniciarComponentes();
		initListeners();
		showSaveMode();
		new HotKeyComponent(this);
	}
	
	public RolContratoModel(TipoRolIf tipoRolIf,RolPagoIf rolPagoIf,ContratoIf contratoIf) throws GenericBusinessException{
		
		iniciarComponentes();
		initListeners();
		
		this.tipoRolIf = tipoRolIf;
		establecerTipoRol();
		getCmbTipoRol().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoRol(), tipoRolIf.getId()));
		getCmbTipoRol().repaint();
		
		this.contratoIf = contratoIf;
		empleadoIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(contratoIf.getEmpleadoId()); 
		getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
		getTxtContrato().setText(contratoIf.getCodigo());
		
		this.rolPagoIf = rolPagoIf;
		String mesSeleccionadoStringNumero = rolPagoIf.getMes();
		int mesSeleccionadoInt = Integer.parseInt( mesSeleccionadoStringNumero );
		mesSeleccionado = Utilitarios.getNombreMes(mesSeleccionadoInt);
		getCmbMes().setSelectedItem(mesSeleccionado);

		anioSeleccionado = rolPagoIf.getAnio();
		getCmbAnio().setSelectedItem(anioSeleccionado);
		
		//showSaveMode();

		setSaveMode();
		
		new HotKeyComponent(this);
	}

	private void initListeners(){

		getCmbTipoRol().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				limpiarTabla(getTblRolPagoContrato());
				//verificarSelecciones();
			}
		});
		
		getCmbTipoContrato().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				limpiarTabla(getTblRolPagoContrato());
				contratoIf = null;
				getTxtContrato().setText("");
			}
		});

		getCmbMes().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				limpiarTabla(getTblRolPagoContrato());
				//verificarSelecciones();
			}
		});

		getCmbAnio().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				limpiarTabla(getTblRolPagoContrato());
				//verificarSelecciones();
			}
		});

		getBtnBuscarEmpleado().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento) {
				try {
					busquedaEmpleado();
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				} catch (Exception e) {
					e.printStackTrace(  );
					SpiritAlert.createAlert("Error en la busqueda de Empleado !!", SpiritAlert.ERROR);
				}
				
				//verificarSelecciones();
			}			
		});

		getBtnBuscarContrato().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento) {
				busquedaContrato();
				try {
					verificarSelecciones();
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}	
			}			
		});

		getBtnGenerar().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eve) {
				generarRolPagoPorContrato();
			}			
		});

	}
	
	private void generarRolPagoPorContrato(){
		if(validateFields()){
			try {
				
				setCursorEspera();
				verificarSelecciones();
								
				//primero chequeo si el rol no esta cerrado
				Map<String, Object> mapaRolPago = new HashMap<String, Object>();
				mapaRolPago.put("mes", (String)mapaParametros.get("mes"));
				mapaRolPago.put("anio", (String)mapaParametros.get("anio"));
				mapaRolPago.put("tiporolId", (Long)mapaParametros.get("tiporolId"));
				mapaRolPago.put("tipocontratoId", (Long)mapaParametros.get("tipocontratoId"));
				mapaRolPago.put("estado", EstadoRolPago.CERRADO.getLetra());				
				
				Collection rolesPago = SessionServiceLocator.getRolPagoSessionService().findRolPagoByQuery(mapaRolPago);
				
				//primero chequeo si el rol no esta cerrado
				if(rolesPago.size() > 0){
					SpiritAlert.createAlert("No se puede reemplazar un Rol Cerrado.", SpiritAlert.INFORMATION);
				}else{
					RolPagoIf rolPago = crearRolPagoCabecera();
					
					Collection<Long> contratosIdCollection = new ArrayList<Long>();
					contratosIdCollection.add(contratoIf.getId());
					RolPagoUtil.verificarRubroFondoReserva(contratosIdCollection);
					
					SessionServiceLocator.getRolPagoSessionService().procesarRolPago(
							rolPago,tipoContratoIf,contratoIf,Parametros.getIdEmpresa());
					SpiritAlert.createAlert("Rol de Pago Generado con éxito !!", SpiritAlert.INFORMATION);
					showSaveMode();
				}				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			} catch(Exception e){
				e.printStackTrace();
				SpiritAlert.createAlert("Error general al Generar Rol por Contrato !!", SpiritAlert.ERROR);
			} finally{
				setCursorNormal();
			}
		}
	}
	
	private RolPagoData crearRolPagoCabecera() throws GenericBusinessException {
		RolPagoData rolPago =  new RolPagoData();
		
		rolPago.setId(rolPagoIf!=null?rolPagoIf.getId():null);
		rolPago.setTiporolId((Long)mapaParametros.get("tiporolId"));
		rolPago.setTipocontratoId((Long)mapaParametros.get("tipocontratoId"));
		rolPago.setMes((String)mapaParametros.get("mes"));
		rolPago.setAnio((String)mapaParametros.get("anio"));
		rolPago.setEstado(EstadoRolPago.GENERADO.getLetra());
		if(rolPagoIf != null && rolPagoIf.getAsientoGenerado() != null)
			rolPago.setAsientoGenerado(rolPagoIf.getAsientoGenerado());
		else
			rolPago.setAsientoGenerado("N");
		java.sql.Date firstDate = new java.sql.Date(Integer.parseInt(rolPago.getAnio()) - 1900, Integer.parseInt(rolPago.getMes()) -1, 1);
		int lastDay = Utilitarios.getLastDayOfMonth(Integer.parseInt(rolPago.getMes()) - 1, Integer.parseInt(rolPago.getAnio()) - 1900);
		java.sql.Date lastDate = new java.sql.Date(Integer.parseInt(rolPago.getAnio()) - 1900, Integer.parseInt(rolPago.getMes()) -1, lastDay);
		java.sql.Date today = Utilitarios.fromUtilDateToSqlDate(new java.util.Date());
		if (today.compareTo(firstDate) >=0 && today.compareTo(lastDate) < 0)
			rolPago.setFecha(today);
		else
			rolPago.setFecha(lastDate);
		return rolPago;
	}
	
	private void consultarRolPagoPorContrato() {
		try {
			limpiarTabla(getTblRolPagoContrato());
			if ( rolPagoIf != null ){
				rolPagoDetalleMapaTodo = null;
				rolPagoDetalleMapaTodo = new HashMap<Long, RolPagoContratoDatos>();
				DefaultTableModel modelo = (DefaultTableModel)getTblRolPagoContrato().getModel();
				
				//PARA RUBROS NO EVENTUALES 
				Collection<Long> contratosIdCollection = null;
				if ( contratoIf == null )
					contratosIdCollection = Contratos.obtenerContratosId(tipoRol,tipoContratoIf,rolPagoIf,null);
				else {
					contratosIdCollection = new ArrayList<Long>();
					contratosIdCollection.add(contratoIf.getId());
				}
				rolPagoDetalleCollection = (ArrayList<Map<String, Object>>)SessionServiceLocator
					.getRolPagoSessionService().crearColeccionContratos(
						contratosIdCollection,rolPagoIf,null,true,false,false,"",false);
						//,EtapaRolPago.GENERACION_ROL);
				//if ( rolPagoDetalleCollectionEgresos == null || rolPagoDetalleCollectionIngresos == null )
				crearColeccionDetalleIngresoEgresoNormales(rolPagoDetalleCollection);

				//PARA RUBROS EVENTUALES
				/*rolPagoDetalleCollection = (ArrayList)getRolPagoDetalleSessionService().findRolPagoDetalleEventualesByRolPagoByEstado(
						rolPagoIf,null);
				//if ( rolPagoDetalleCollectionEventual == null )
				crearColeccionDetalleEventuales(rolPagoDetalleCollection);*/
				
				if ( contratoIf!=null ){
					getScpRolPagoContrato().setVisible(true);
					RolPagoContratoDatos datosContrato = verificarMapaContrato(contratoIf.getId()); 
					Collection<RubroContratoDatos> ingresos =(Collection<RubroContratoDatos>) datosContrato.getIngresosRubro();
					Collection<RubroContratoDatos> egresos =(Collection<RubroContratoDatos>) datosContrato.getEgresosRubro();
					
					Iterator<RubroContratoDatos> itIngresos = ingresos.iterator();
					while ( itIngresos.hasNext() ){
						RubroContratoDatos datos = itIngresos.next();
						Vector<Object> fila = crearFilaTabla(datos);
						modelo.addRow(fila);
					}
	
					Iterator<RubroContratoDatos> itEgresos = egresos.iterator();
					while ( itEgresos.hasNext() ){
						RubroContratoDatos datos = itEgresos.next();
						Vector<Object> fila = crearFilaTabla(datos);
						modelo.addRow(fila);
					}
				}

				
				
				/*Iterator<RolPagoContratoDatos> itEventuales = rolPagoDetalleCollectionEventual.iterator();
				while ( itEventuales.hasNext() ){
					RolPagoContratoDatos datos = itEventuales.next();
					Vector<Object> fila = crearFilaTabla(datos);
					modelo.addRow(fila);
				}*/
				
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error al consultar Rol por Contrato", SpiritAlert.ERROR);
		}
	}	

	private void crearColeccionDetalleIngresoEgresoNormales(Collection<Map<String, Object>> rolPagoDetallesCompleto) throws GenericBusinessException{

		/*rolPagoDetalleCollectionIngresos = null;
		rolPagoDetalleCollectionIngresos = new ArrayList();
		rolPagoDetalleCollectionEgresos = null;
		rolPagoDetalleCollectionEgresos = new ArrayList();*/

		//INGRESOS Y EGRESOS NORMALES
		
		for ( Map<String, Object> mapa : rolPagoDetallesCompleto ){
			totalIngresos = 0.0;
			totalEgresos=0.0;
			
			Long contratoId = (Long) mapa.get("contratoId");
			RolPagoContratoDatos datosContrato = verificarMapaContrato(contratoId); 
			datosContrato.setContratoId(contratoId);
			
			Collection<RubroContratoDatos> ingresos =(Collection<RubroContratoDatos>) datosContrato.getIngresosRubro();
			Collection<RubroContratoDatos> egresos =(Collection<RubroContratoDatos>) datosContrato.getEgresosRubro();
			
			Double totalIngresosContrato = (Double)mapa.get("totalIngresos");
			totalIngresos = Utilitarios.redondeoValor(totalIngresosContrato);
			Double totalEgresosContrato = (Double)mapa.get("totalDescuentos");
			totalEgresos = Utilitarios.redondeoValor(totalEgresosContrato);
			
			String nombre = (String)mapa.get("nombreEmpleado");
			datosContrato.setNombreEmpleado(nombre);
			
			Collection<RolPagoDetalleIf> rolPagoDetalles = (Collection<RolPagoDetalleIf>) mapa.get("detallesRolPago");
			
			for ( RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles ){
				RubroContratoDatos datos = new RubroContratoDatos();
				Double valor = Utilitarios.redondeoValor(rolPagoDetalle.getValor().doubleValue());
				datos.setValor(valor);
				datos.setEstado(rolPagoDetalle.getEstado());
				RubroIf rubro = null;
				try {
					if ( rolPagoDetalle.getRubroId() != null){
						rubro = verificarRubrosEnMapa(rolPagoDetalle.getRubroId());
						datos.setEventual(false);
					}
					/*else{
						RubroEventualIf rubroEventual = getRubroEventualSessionService().getRubroEventual(rolPagoDetalle.getRubroEventualId());
						rubro = verificarRubrosEnMapa(rubroEventual.getRubroId());
						datos.setEventual(true);
						datos.setObservacion(rubroEventual.getObservacion());
					}*/
					datos.setNombreRubro(rubro.getNombre()+
							(rolPagoDetalle.getObservacion()!=null?"\n "+rolPagoDetalle.getObservacion():""));
	
					//int ingresoEgreso = Contratos.getRubroSigno(rubro.getTipoRubro(),tipoRolIf);
					OperacionNomina ingresoEgreso = UtilesNomina.getIngresoEgreso(tipoRolIf,rubro);
	
					if ( ingresoEgreso == OperacionNomina.INGRESO ){
						ingresos.add(datos);
						//totalIngresos += Utilitarios.redondeoValor(valor);
					}
					else{
						egresos.add(datos);
						//totalEgresos += Utilitarios.redondeoValor(valor);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					throw new GenericBusinessException("Error al buscar nombre de rubro !!");
				}
			}
			//mapaContrato.put("ingresos", ingresos);
			//mapaContrato.put("egresos", egresos);
			datosContrato.setTotalIngresos(totalIngresos);
			datosContrato.setTotalEgresos(totalEgresos);
		}
	}

	private RolPagoContratoDatos verificarMapaContrato(Long contratoId) {
		
		RolPagoContratoDatos datosContrato = rolPagoDetalleMapaTodo.get(contratoId);
		if ( datosContrato == null ){
			datosContrato = new RolPagoContratoDatos();
			Collection<RubroContratoDatos> ingresos = new ArrayList<RubroContratoDatos>();
			datosContrato.setIngresosRubro(ingresos);
			Collection<RubroContratoDatos> egresos = new ArrayList<RubroContratoDatos>();
			datosContrato.setEgresosRubro(egresos);
			rolPagoDetalleMapaTodo.put(contratoId, datosContrato);
		}
		return datosContrato;
	}

	private void crearColeccionDetalleEventuales(Collection<Map<String, Object>> rolPagoDetallesCompleto) throws GenericBusinessException{

		/*rolPagoDetalleCollectionEventual = new ArrayList();
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("rolpagoId", rolPagoIf.getId());
		mapa.put("contratoId", contratoIf.getId());
		mapa.put("estado", RolPagoDetalle.getLetraEstadoDetalle(EstadoRolPagoDetalle.AUTORIZADO));
		Collection<RolPagoDetalleIf> detalleEventuales = getRolPagoDetalleSessionService()
			.findRolPagoDetalleEventualesByMap(mapa);*/
		
		for ( Map<String, Object> mapa : rolPagoDetallesCompleto ){
			
			Long contratoId = (Long) mapa.get("contratoId");
			RolPagoContratoDatos datosContrato = verificarMapaContrato(contratoId); 
			Collection<RubroContratoDatos> ingresos =(Collection<RubroContratoDatos>) datosContrato.getIngresosRubro();
			Collection<RubroContratoDatos> egresos =(Collection<RubroContratoDatos>) datosContrato.getEgresosRubro();
			
			totalIngresos = (Double)datosContrato.getTotalIngresos();
			totalEgresos = (Double)datosContrato.getTotalEgresos();
			
			Collection<RolPagoDetalleIf> detalleEventuales = (Collection<RolPagoDetalleIf>)mapa.get("detallesRolPago");
			for ( RolPagoDetalleIf rolPagoDetalleIf : detalleEventuales ){
				RubroContratoDatos datos = new RubroContratoDatos();
				datos.setValor(rolPagoDetalleIf.getValor().doubleValue());
				datos.setEstado(rolPagoDetalleIf.getEstado());
				RubroEventualIf rubroEventual = SessionServiceLocator.getRubroEventualSessionService().getRubroEventual(rolPagoDetalleIf.getRubroEventualId());
				RubroIf rubro = verificarRubrosEnMapa(rubroEventual.getRubroId());
				datos.setEventual(true);
				datos.setObservacion(rubroEventual.getObservacion());
				datos.setNombreRubro(rubro.getNombre()+
						(rolPagoDetalleIf.getObservacion()!=null?"\n "+rolPagoDetalleIf.getObservacion():""));

				//int ingresoEgreso = Contratos.getRubroSigno(rubro.getTipoRubro(),tipoRolIf);
				OperacionNomina ingresoEgreso = UtilesNomina.getIngresoEgreso(tipoRolIf,rubro);

				if ( ingresoEgreso == OperacionNomina.INGRESO ){
					ingresos.add(datos);
					totalIngresos += Utilitarios.redondeoValor(datos.getValor());
				}
				else{
					egresos.add(datos);
					totalEgresos += Utilitarios.redondeoValor(datos.getValor());
				}
				//rolPagoDetalleCollectionEventual.add(datos);
			}
			datosContrato.setTotalIngresos(totalIngresos);
			datosContrato.setTotalEgresos(totalEgresos);
	}

	}

	private Vector<Object> crearFilaTabla(RubroContratoDatos datos) throws GenericBusinessException {
		Vector<Object> fila = new Vector<Object>();
		fila.add(datos.getNombreRubro());
		fila.add(datos.getValor());
		fila.add(datos.isEventual()?"     X":"");
		String estado = datos.getEstado();
		if (estado.equals(EstadoRolPagoDetalle.EMITIDO.getLetra() ) ) 
			estado=EstadoRolPagoDetalle.EMITIDO.toString();
		else if ( estado.equals(EstadoRolPagoDetalle.PROVISIONADO.getLetra() ) ) 
			estado = EstadoRolPagoDetalle.PROVISIONADO.toString(); 
		else if (estado.equals(EstadoRolPagoDetalle.PAGADO.getLetra())) 
			estado = EstadoRolPagoDetalle.PAGADO.toString();
		else if ( estado.equals(EstadoRolPagoDetalle.AUTORIZADO.getLetra() ) )
			estado = EstadoRolPagoDetalle.AUTORIZADO.toString();
		fila.add(estado);
		fila.add(datos.getObservacion());
		return fila;
	}

	/*private boolean verificacionRolGenerado() throws GenericBusinessException {
		if ( validateFields() ){
			if ( !rolGenerado ){
				SpiritAlert.createAlert("Rol de Pago no generado, debe generarlo desde el panel Rol Pago !!", SpiritAlert.INFORMATION);
			}else{
				if (rolPagoIf == null){
					SpiritAlert.createAlert("Rol de Pago no establecido !!", SpiritAlert.INFORMATION);
					return false;
				}
				//if ( rolPagoDetalleCollection == null ){
				//	buscarRolPagoDetalle();
				//}
			}
			return true;
		}
		return false;
	}*/

	private void busquedaEmpleado() throws GenericBusinessException, ParseException {
		EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria("de Empleados", Parametros.getIdEmpresa());
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if ( popupFinder.getElementoSeleccionado()!=null ){
			limpiarTabla(getTblRolPagoContrato());
			empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
			getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
			
			int mes = Utilitarios.getMesInt(mesSeleccionado);
			int anio = Integer.valueOf(anioSeleccionado);
			int ultimoDiaMes = Utilitarios.fechaHoy(mes, anio).getDate();
			long fecha = new GregorianCalendar(anio,mes,ultimoDiaMes).getTime().getTime();
			Date fechaActual = new Date( fecha );
			
			tipoContratoIf = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
			if ( tipoContratoIf!=null ){
				Calendar calFechaMedia = new GregorianCalendar(anio,mes,1);
				Date fechaMedia = new Date(calFechaMedia.getTime().getTime());
				int diaFinal = calFechaMedia.getActualMaximum(Calendar.DATE);
				calFechaMedia = new GregorianCalendar(anio,mes,diaFinal);
				Date fechaMediaMax = new Date(calFechaMedia.getTime().getTime());
				Map<String, Object> mapa = new HashMap<String, Object>();
				//mapa.put("fechaMediaContrato", fechaMedia);
				//mapa.put("fechaMediaContratoMax", fechaMediaMax);
				mapa.put("tipocontratoId", tipoContratoIf.getId());
				mapa.put("empleadoId", empleadoIf.getId());
				//mapa.put("estado", "A");
				
				Collection<ContratoIf> contratos = SessionServiceLocator.getContratoSessionService().findContratoByQueryConFecha(mapa);
				if ( contratos.size() == 1 ){
					contratoIf = contratos.iterator().next();
					getTxtContrato().setText(contratoIf.getCodigo());
				} else {
					contratoIf = null;
					getTxtContrato().setText("");
				}
			} else {
				contratoIf = null;
				getTxtContrato().setText("");
			}
			
		}
		empleadoCriteria = null;
		popupFinder = null;
	}

	private void busquedaContrato() {
		try {
			
			tipoContratoIf = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
			if ( tipoContratoIf == null ){
				SpiritAlert.createAlert("Debe elegir un empleado !!", SpiritAlert.INFORMATION);
				return;
			}
			
			if ( empleadoIf == null ){
				SpiritAlert.createAlert("Debe elegir un empleado !!", SpiritAlert.INFORMATION);
				return;
			}
			
			mesSeleccionado = (String)getCmbMes().getSelectedItem();
			int mes = Utilitarios.getMesInt(mesSeleccionado);
			
			anioSeleccionado =(String)getCmbAnio().getSelectedItem();
			int anio = Integer.valueOf(anioSeleccionado);
			
			int ultimoDiaMes = Utilitarios.fechaHoy(mes, anio).getDate();
			long fecha = new GregorianCalendar(anio,mes,ultimoDiaMes).getTime().getTime();
			Date fechaActual = new Date( fecha );
			
			Calendar calFechaMedia = new GregorianCalendar(anio,mes,1);
			Date fechaMedia = new Date(calFechaMedia.getTime().getTime());
			int diaFinal = calFechaMedia.getActualMaximum(Calendar.DATE);
			calFechaMedia = new GregorianCalendar(anio,mes,diaFinal);
			Date fechaMediaMax = new Date(calFechaMedia.getTime().getTime());
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("fechaMediaContrato", fechaMedia);
			mapa.put("fechaMediaContratoMax", fechaMediaMax);
			mapa.put("tipocontratoId", tipoContratoIf.getId());
			mapa.put("empleadoId", empleadoIf.getId());
			//mapa.put("estado", "A");
			
			int tamanoLista = SessionServiceLocator.getContratoSessionService().getContratoListSize(mapa);
			if ( tamanoLista > 0 ){
				ContratoCriteria contratoCriteria = new ContratoCriteria("de Contratos");
				contratoCriteria.setQueryBuilded(mapa);
				contratoCriteria.setResultListSize(tamanoLista);

				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	contratoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					limpiarTabla(getTblRolPagoContrato());
					contratoIf = (ContratoIf) popupFinder.getElementoSeleccionado();
					getTxtContrato().setText(contratoIf.getCodigo());
				}
				contratoCriteria = null;
				popupFinder = null;
			} else
				SpiritAlert.createAlert("No existen contratos activos para el empleado !!",	SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de Contrato", SpiritAlert.WARNING);
		}
	}

	private void accionImprimirRol() {
		report();
	}

	private void iniciarComponentes(){
		
		//MES
		getCmbMes().setSelectedIndex(0);
		mesSeleccionado = (String) getCmbMes().getSelectedItem();
		
		//COMBO ANIO
		ModelUtil.establecerCmbAnio(getCmbAnio());
		anioSeleccionado = (String) getCmbAnio().getSelectedItem();

		//COMBO TIPO ROL
		ModelUtil.cargarCmbTipoRol(getCmbTipoRol(),null,null);
		getCmbTipoRol().setSelectedItem(null);

		//COMBO TIPO DE CONTRATO
		getCmbTipoContrato().setSelectedItem(null);
		ModelUtil.cargarCmbTipoContrato(getCmbTipoContrato());
		
		//BOTON BUSQUEDA DE EMPLEADO
		getBtnBuscarEmpleado().setText("");
		getBtnBuscarEmpleado().setToolTipText("Buscar Empleado");
		getBtnBuscarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));

		//BOTON BUSQUEDA DE CONTRATO
		getBtnBuscarContrato().setText("");
		getBtnBuscarContrato().setToolTipText("Buscar Contrato");
		getBtnBuscarContrato().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));

	}

	private void verificarSelecciones() throws GenericBusinessException{
		//try {
		rolGenerado = false;
		rolPagoDetalleMapaTodo = null;
		mapaParametros = null;
		rolPagoIf = null;
		rolPagoDetalleCollection = null;
		/*rolPagoDetalleCollectionIngresos = null;
		rolPagoDetalleCollectionEgresos = null;
		rolPagoDetalleCollectionEventual = null;*/
		//rubroDecimoTercero = null;
		getTxtObservacion().setText("No Generado");
		totalIngresos = 0.0;
		totalEgresos = 0.0;
		limpiarTabla(getTblRolPagoContrato());
		getScpRolPagoContrato().setVisible(false);

		tipoContratoIf = (TipoContratoIf)getCmbTipoContrato().getSelectedItem();
		if ( tipoContratoIf == null )
			return;
		
		tipoRolIf = (TipoRolIf) getCmbTipoRol().getSelectedItem();
		if ( tipoRolIf == null )
			return;
		else{
			establecerTipoRol();
		}

		mesSeleccionado = (String)getCmbMes().getSelectedItem();
		String mes = formatoDosEnteros.format( Utilitarios.getMesInt(mesSeleccionado) + 1 );
		if ( mesSeleccionado == null )
			return;

		anioSeleccionado = (String) getCmbAnio().getSelectedItem();
		if ( anioSeleccionado == null )
			return;

		if (contratoIf == null)
			return;

		mapaParametros =  new HashMap<String, Object>();
		mapaParametros.put("tipocontratoId", tipoContratoIf.getId());
		mapaParametros.put("tiporolId", tipoRolIf.getId());
		mapaParametros.put("mes", mes);
		mapaParametros.put("anio", anioSeleccionado);

		Collection<RolPagoIf> rolesPagos = SessionServiceLocator.getRolPagoSessionService().findRolPagoByQuery(
				mapaParametros,contratoIf!=null?contratoIf.getId():null, null );
		
		if ( rolesPagos!=null && rolesPagos.size()>0 ){
			rolPagoIf = rolesPagos.iterator().next();
			getTxtObservacion().setText("Rol Generado");
				rolGenerado = true;
		}
		else
			getTxtObservacion().setText("Sin detalle");
	}

	private void establecerTipoRol() {
		if ( tipoRolIf.getNombre().contains("MENSUAL") )
			tipoRol = TipoRol.MENSUAL;
		else if (tipoRolIf.getNombre().contains("QUINCENA") )
			tipoRol = TipoRol.QUINCENAL;
		else if (tipoRolIf.getNombre().contains("DECIMO") && tipoRolIf.getNombre().contains("TERCER") )
			tipoRol = TipoRol.DECIMO_TERCERO;
		else if ( tipoRolIf.getNombre().contains("VACACION") || tipoRolIf.getNombre().contains("VACACIÓN") )
			tipoRol = TipoRol.VACACIONES;
	}

	@Override
	public boolean validateFields() {

		if ( getCmbTipoContrato().getSelectedItem() == null ){
			SpiritAlert.createAlert("Seleccionar Tipo de Contrato !!", SpiritAlert.INFORMATION);
			getCmbTipoContrato().grabFocus();
			return false;
		}
		
		//if ( tipoRolIf == null ){
		if ( getCmbTipoRol().getSelectedItem() == null ){
			SpiritAlert.createAlert("Seleccionar Tipo de Rol !!", SpiritAlert.INFORMATION);
			return false;
		}

		//if ( mesSeleccionado == null ){
		if ( getCmbMes().getSelectedItem() == null ){
			SpiritAlert.createAlert("Seleccionar Mes !!", SpiritAlert.INFORMATION);
			return false;
		}

		//if ( anioSeleccionado == null ){
		if ( getCmbAnio().getSelectedItem() == null ){
			SpiritAlert.createAlert("Seleccionar A\u00f1o !!", SpiritAlert.INFORMATION);
			return false;
		}

		if ( empleadoIf == null ){
			SpiritAlert.createAlert("Seleccionar un empleado !!", SpiritAlert.INFORMATION);
			return false;
		}

		if ( contratoIf == null ){
			SpiritAlert.createAlert("Seleccionar un contrato !!", SpiritAlert.INFORMATION);
			return false;
		}
		return true;
	}

	public void clean() {
		getCmbTipoRol().setSelectedItem(null);
		getTxtObservacion().setText("");
		rolPagoDetalleMapaTodo = null;
		contratoIf = null;
		tipoContratoIf = null;
		getTxtContrato().setText("");
		empleadoIf = null;
		getTxtEmpleado().setText("");
		totalIngresos = 0.0;
		totalEgresos = 0.0;
		limpiarTabla(getTblRolPagoContrato());
		getScpRolPagoContrato().setVisible(false);
	}

	public void delete() {
		try {
			if ( validateFields() ){
				if ( rolGenerado ){
					SessionServiceLocator.getRolPagoSessionService().eliminarRolPagoPorContrato(rolPagoIf, contratoIf);
					SpiritAlert.createAlert("Rol eliminado con éxito !!", SpiritAlert.INFORMATION);
				} else
					SpiritAlert.createAlert("Rol no ha sido generado !!", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
		}
	}

	public void save() {
	}

	public void showFindMode() {
		showSaveMode();
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}

	public void update() {
	}
	
	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public void report() {
		try {
			if ( rolPagoIf != null ){
				
				if ( rolPagoDetalleMapaTodo==null || rolPagoDetalleMapaTodo.size() == 0){
					SpiritAlert.createAlert("Rol de pago no tiene detalle !!", SpiritAlert.INFORMATION);
					return;
				}	

				String fileName = "jaspers/nomina/RPRolPagoContrato.jasper";

				HashMap parametrosMap = new HashMap();

				MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("ESTADO DE CUENTA").iterator().next();
				parametrosMap.put("codigoReporte", menu.getCodigo());
				EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
				parametrosMap.put("empresa", empresa.getNombre());
				parametrosMap.put("ruc", empresa.getRuc());
				parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
				OficinaIf oficina = (OficinaIf) Parametros.getOficina();
				CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
				parametrosMap.put("ciudad", ciudad.getNombre());
				parametrosMap.put("usuario", Parametros.getUsuario());

				parametrosMap.put("mes", mesSeleccionado);
				parametrosMap.put("anio", anioSeleccionado);
				if ( tipoContratoIf.getNombre().contains("PROFESIONAL") ){
					parametrosMap.put("tituloRol","L I Q U I D A C I Ó N");
					parametrosMap.put("nombreTipoRol", tipoRolIf.getNombre().replace("ROL", "").replace("DE", ""));
				}
				else if ( tipoContratoIf.getNombre().contains("DEPENDENCIA") ){
					parametrosMap.put("tituloRol","R O L   D E   P A G O  P O R   C O N T R A T O");
					parametrosMap.put("nombreTipoRol", tipoRolIf.getNombre());
				}
				
				if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) ){
					parametrosMap.put("pathSubreportEgresos", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/nomina/RPRolPagoContratoDetalleEgresos.jasper");
					parametrosMap.put("pathSubreportIngresos", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/nomina/RPRolPagoContratoDetalleIngresos.jasper");
				}
				else 
					throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
				
				Collection contratoCollection = transformarContratoCollection();
				JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(contratoCollection);
				//parametrosMap.put("contratosCollection", dataSourceDetail);

				ReportModelImpl.processReport(fileName, parametrosMap, dataSourceDetail, true);
				
				contratoCollection = null;
				//rolPagoDetalleCollectionIngresos = null;
				//rolPagoDetalleCollectionEgresos = null;
			}
		} catch ( GenericBusinessException e ) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
		} catch( Exception e ){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al generar Reporte !!", SpiritAlert.WARNING);
		}
	}
	
	private Collection transformarContratoCollection(){
		Collection<RolPagoContratoDatos> coleccion = new ArrayList<RolPagoContratoDatos>();
		if (rolPagoDetalleMapaTodo!=null){
			Iterator<Long> itMapa = rolPagoDetalleMapaTodo.keySet().iterator();
			while( itMapa.hasNext() ){
				Long contratoId = itMapa.next();
				
				RolPagoContratoDatos datoContrato = rolPagoDetalleMapaTodo.get(contratoId);
				
				//Map<String, Object> mapaTransformado = new HashMap<String, Object>();
				
				//clonarMapa(mapa, mapaTransformado);
				
				Collection<RubroContratoDatos> ingresos = datoContrato.getIngresosRubro();
				JRBeanCollectionDataSource ingresosBeans = new JRBeanCollectionDataSource(ingresos);
				datoContrato.setIngresos(ingresosBeans);
				datoContrato.setIngresosRubro(null);
				ingresos = null;
				
				Collection<RubroContratoDatos> egresos = datoContrato.getEgresosRubro();
				JRBeanCollectionDataSource egresosBeans = new JRBeanCollectionDataSource(egresos);
				datoContrato.setEgresos(egresosBeans);
				datoContrato.setEgresosRubro(null);
				egresos = null;
				
				coleccion.add(datoContrato);
			}
			Collections.sort((ArrayList<RolPagoContratoDatos>)coleccion, comparadorNombre);
		}
		return coleccion;
	}
	
	Comparator<RolPagoContratoDatos> comparadorNombre = new Comparator<RolPagoContratoDatos>(){
		public int compare(RolPagoContratoDatos o1, RolPagoContratoDatos o2) {
			return o1.getNombreEmpleado().compareTo(o2.getNombreEmpleado());
		}
	};
	
	private RubroIf verificarRubrosEnMapa(Long idRubro)throws GenericBusinessException {
		RubroIf rubroIf = null;
		if ( !mapaRubros.containsKey(idRubro) ){
			rubroIf = SessionServiceLocator.getRubroSessionService().getRubro(idRubro);
			mapaRubros.put(rubroIf.getId(), rubroIf);
		} else {
			rubroIf = mapaRubros.get(idRubro);
		}
		return rubroIf;
	}
	
}
