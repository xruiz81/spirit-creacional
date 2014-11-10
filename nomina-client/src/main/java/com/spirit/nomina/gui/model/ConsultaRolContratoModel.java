package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.math.BigDecimal;
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
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

import com.spirit.cartera.handler.ComprobanteEgresoData;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.NumberCellRenderer;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaEntidadIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.general.gui.model.EmpleadoModel;
import com.spirit.general.util.Contratos;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.criteria.ContratoCriteria;
import com.spirit.nomina.gui.datos.RolPagoContratoContinuoDatos;
import com.spirit.nomina.gui.panel.JPConsultaRolContrato;
import com.spirit.nomina.gui.util.ModelUtil;
import com.spirit.nomina.handler.EstadoRolPagoDetalle;
import com.spirit.nomina.handler.OperacionNomina;
import com.spirit.nomina.handler.RolPagoContratoDatos;
import com.spirit.nomina.handler.RubroContratoDatos;
import com.spirit.nomina.handler.TipoRol;
import com.spirit.nomina.handler.UtilesNomina;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.Utilitarios;


public class ConsultaRolContratoModel extends JPConsultaRolContrato {

	private static final long serialVersionUID = -7292916188285266727L;
	private DecimalFormat formatoDosEnteros = new DecimalFormat("00");
	private TipoRolIf tipoRolIf = null;
	private TipoContratoIf tipoContratoIf = null;
	private String mesSeleccionado = "";
	private String anioSeleccionado = "";
	private Map<String,Object> mapaParametros = null;
	private Boolean rolGenerado = false;
	private RolPagoIf rolPagoIf = null;
	private EmpleadoIf empleadoIf = null;
	private ContratoIf contratoIf = null;
	
	private Collection<TipoRolIf> tiposRolCollection = null;
	private Collection<Map<String, Object>> rolPagoDetalleCollection = null;	
	private Map<Long,RolPagoContratoDatos> rolPagoDetalleMapaTodo = null;
	private Collection<RolPagoContratoContinuoDatos> rolPagoDetalleCollectionTodo = null;

	private String letraEstadoDetalle = null;
	private Double totalIngresos = 0.0;
	private Double totalEgresos = 0.0;

	private Map<Long, RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
	private TipoRol tipoRol; 	
	private final int COLUMNA_VALOR = 1;
	private Vector<Long> asientosId = new Vector<Long>();
	private List<BigDecimal> valoresChequeColeccion = new ArrayList<BigDecimal>();
	private Vector<ComprobanteEgresoData> comprobanteEgresoColeccion = new Vector<ComprobanteEgresoData>();
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	
	public ConsultaRolContratoModel(){
		initKeyListeners();
		iniciarComponentes();
		initListeners();
		showSaveMode();
		new HotKeyComponent(this);
	}
	
	public void initKeyListeners(){
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnConsultar().setToolTipText("Consultar");
	}
	
	public ConsultaRolContratoModel(TipoRolIf tipoRolIf,RolPagoIf rolPagoIf,ContratoIf contratoIf,boolean crearPDF,String identificador) throws GenericBusinessException{
		
		iniciarComponentes();
		initListeners();
		
		this.tipoRolIf = tipoRolIf;
		tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		
		
		if ( tipoRol == null )
			return;
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
		
		new HotKeyComponent(this);
		
		if(crearPDF)
		{
			consultaTotalRolPagoContrato("PDF");//sin hilos			
			guardarArchivoPdf(identificador);
		}
		else{
			consultaTotalRolPagoContrato("");		
		}
	
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
				try{
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
			}			
		});

		getBtnConsultar().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eve) {
				consultaTotalRolPagoContrato("");
			}			
		});
		
		//POPUP PARA VER DETALLE DEL ROL POR CONTRATO 
		JPopupMenu popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("<html><font color=red>Detalle de Rol por Contrato</font></html>");
		menuItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				try {
					int filaSeleccionada = getTblRolPagoContrato().getSelectedRow();
					if (filaSeleccionada >= 0) {
						int fila = getTblRolPagoContrato().convertRowIndexToModel(filaSeleccionada);
						Long asientoId = asientosId.get(fila);
						if(asientoId != null){
							AsientoIf asiento = SessionServiceLocator.getAsientoSessionService().getAsiento(asientoId);
							List<AsientoDetalleIf> asientoDetalleColeccion = (ArrayList<AsientoDetalleIf>)SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByAsientoId(asientoId);
							verificarImpresionCheque(asientoDetalleColeccion);
							generarReporteComprobanteEgreso(asiento, asientoDetalleColeccion, false);
						}
						else{
							SpiritAlert.createAlert("No existen datos para presentar.", SpiritAlert.INFORMATION);
						}
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				} 
			}
		});
		popup.add(menuItem);
		PopupListener ppl = new PopupListener(popup);
		getTblRolPagoContrato().addMouseListener(ppl);
	}
	
	public boolean verificarImpresionCheque(List<AsientoDetalleIf> asientoDetalleColeccion){
		try {
			valoresChequeColeccion.clear();
			for(AsientoDetalleIf asientoDetalle : asientoDetalleColeccion){
				if((asientoDetalle.getHaber()!=null) && (asientoDetalle.getHaber().compareTo(new BigDecimal(0)) == 1)){
					CuentaIf cuenta = SessionServiceLocator.getCuentaSessionService().getCuenta(asientoDetalle.getCuentaId());
					if(!cuenta.getCuentaBanco().equals("S")){
						return false;
					}
				}				
			}
			for(AsientoDetalleIf asientoDetalle : asientoDetalleColeccion){
				if((asientoDetalle.getHaber()!=null) && (asientoDetalle.getHaber().compareTo(new BigDecimal(0)) == 1)){
					valoresChequeColeccion.add(asientoDetalle.getHaber());
				}				
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private void generarReporteComprobanteEgreso(AsientoIf asiento, List<AsientoDetalleIf> asientoDetalleColeccion, boolean anulado) {
		try {
			comprobanteEgresoColeccion.clear();
			Double totalCheque = 0D;
			if(!anulado){
				for(BigDecimal valor : valoresChequeColeccion){
					totalCheque = totalCheque + valor.doubleValue();
				}
			}			
			//String valorComprobante = String.valueOf(totalCheque);
			String valorComprobante = formatoDecimal.format(Double.valueOf(totalCheque));
			String parteDecimal = valorComprobante.substring(valorComprobante.indexOf('.'), valorComprobante.length());
			Double dParteDecimal = 0.0;
			if (!parteDecimal.isEmpty())
				dParteDecimal = Double.valueOf(parteDecimal);
			String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(valorComprobante, dParteDecimal, new int[] { 90 }, false, Parametros.getMonedaPredeterminada());
			String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
			
			for(AsientoDetalleIf asientoDetalle : asientoDetalleColeccion){
				if((asientoDetalle.getHaber() != null) && (asientoDetalle.getHaber().compareTo(new BigDecimal(0)) == 1)){
					ComprobanteEgresoData comprobanteEgresoData = agregarDetalleComprobanteAnticipo(asiento, asientoDetalle, anulado);
					String fecha = asiento.getFecha().toString();
					String year = fecha.substring(0,4);
					String month = fecha.substring(5,7);
					String day = fecha.substring(8,10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					comprobanteEgresoData.setFechaEmision(fechaEmision);
					comprobanteEgresoData.setProveedor(asiento.getObservacion());
					comprobanteEgresoData.setCantidad(cantidadLetrasPrimeraLinea);
					comprobanteEgresoData.setConcepto(asiento.getObservacion());
					comprobanteEgresoData.setValorTotal(valorComprobante);
					comprobanteEgresoData.setCodigo(asiento.getNumero());
					comprobanteEgresoData.setCodigoAsiento("N/A");
					comprobanteEgresoColeccion.add(comprobanteEgresoData);
				}				
			}			
			
			if (comprobanteEgresoColeccion.size() > 0) {
				String fileName = "jaspers/cartera/RPComprobanteEgreso.jasper";
				HashMap parametrosMap = new HashMap();
				//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("CARTERA").iterator().next();
				
				MenuIf menu = null;
				Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre("CARTERA").iterator();
				if(menuIT.hasNext()) menu = (MenuIf)menuIT.next();
				
				parametrosMap.put("codigoReporte", menu.getCodigo());
				EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
				parametrosMap.put("empresa", empresa.getNombre());
				parametrosMap.put("ruc", empresa.getRuc());
				OficinaIf oficina = (OficinaIf) Parametros.getOficina();
				CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
				parametrosMap.put("ciudad", ciudad.getNombre());
				parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
				parametrosMap.put("usuario", Parametros.getUsuario().toLowerCase());
				
				String fecha = asiento.getFecha().toString();
				String year = fecha.substring(0,4);
				String month = fecha.substring(5,7);
				String day = fecha.substring(8,10);
				String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
				parametrosMap.put("emitido", fechaEmision);
				ReportModelImpl.processReport(fileName, parametrosMap, comprobanteEgresoColeccion, true);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
	}
	
	private ComprobanteEgresoData agregarDetalleComprobanteAnticipo(AsientoIf asiento, AsientoDetalleIf asientoDetalle, boolean anulado) {
		ComprobanteEgresoData data = new ComprobanteEgresoData();
		
		try {
			CuentaIf cuenta = SessionServiceLocator.getCuentaSessionService().getCuenta(asientoDetalle.getCuentaId());
			Map cuentaEntidadMapa = new HashMap();
			cuentaEntidadMapa.put("tipoEntidad", "B");
			cuentaEntidadMapa.put("cuentaId",cuenta.getId());
			//CuentaEntidadIf cuentaEntidad = (CuentaEntidadIf)SessionServiceLocator.getCuentaEntidadSessionService().findCuentaEntidadByQuery(cuentaEntidadMapa).iterator().next();
			CuentaEntidadIf cuentaEntidad = null;
			Iterator itCE = SessionServiceLocator.getCuentaEntidadSessionService().findCuentaEntidadByQuery(cuentaEntidadMapa).iterator();
			if(itCE.hasNext()) cuentaEntidad= (CuentaEntidadIf)itCE.next();
			
			CuentaBancariaIf cuentaBancaria = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(cuentaEntidad.getEntidadId());
			data.setBanco(cuenta.getNombre());
			data.setNumeroCuenta(cuentaBancaria.getCuenta());
			
			if((asientoDetalle.getReferencia()==null) || asientoDetalle.getReferencia().trim().equals("")){
				data.setNumeroCheque("D/B");
			}else{
				data.setNumeroCheque(asientoDetalle.getReferencia());
			}
			
			data.setFechaCompra("N/A");
			data.setCodigoCompra("");
			
			if(anulado){
				data.setPreimpresoFactura(" ANULADO");
				data.setDetalle("");
				data.setValor("0.00");
				data.setSaldo("0.00");
			}else{
				data.setPreimpresoFactura("N/A\n");
				data.setDetalle(asientoDetalle.getGlosa().length()>52?asientoDetalle.getGlosa().substring(0,52):asientoDetalle.getGlosa());
				data.setValor((anulado || (asientoDetalle.getHaber() == null))?formatoDecimal.format(new Double(0)):formatoDecimal.format(asientoDetalle.getHaber()));
				data.setSaldo(formatoDecimal.format(new Double(0)));
			}			
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		
		return data;
	}
	
	private void consultaTotalRolPagoContrato(String pdf) {
		try{
			
			if(validateFields()){
				verificarSelecciones();
				consultarRolPagoPorContrato(pdf);
			}
		} catch(GenericBusinessException e){
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.INFORMATION);
		} catch(Exception e ){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en la búsqueda del Detalle del Rol de Pago", SpiritAlert.ERROR);
		}
	}

	private void consultarRolPagoPorContrato(String pdf) {

		asientosId.clear();
		if(pdf.equals("PDF"))//sin hilos
		{

			try {

				limpiarTabla(getTblRolPagoContrato());
				if ( rolPagoIf != null ){
					DefaultTableModel modelo = (DefaultTableModel)getTblRolPagoContrato().getModel();

					if ( empleadoIf != null && contratoIf == null) {
						SpiritAlert.createAlert("Debe elegir un contrato !!", SpiritAlert.INFORMATION);
						return;
					}

					//PARA RUBROS NO EVENTUALES 
					Collection<Long> contratosIdCollection = null;
					if ( contratoIf == null )
						contratosIdCollection = Contratos.obtenerContratosId(tipoRol,tipoContratoIf,rolPagoIf,letraEstadoDetalle);
					else {
						contratosIdCollection = new ArrayList<Long>();
						contratosIdCollection.add(contratoIf.getId());
					}
					rolPagoDetalleCollection = (ArrayList) SessionServiceLocator.getRolPagoSessionService()
						.crearColeccionContratos(contratosIdCollection,rolPagoIf,
							letraEstadoDetalle,true,false,false,"",false);
							//,EtapaRolPago.GENERACION_ASIENTO);


					rolPagoDetalleCollectionTodo = new ArrayList<RolPagoContratoContinuoDatos>();
					rolPagoDetalleMapaTodo = new HashMap<Long, RolPagoContratoDatos>();
					if ( tipoRol == TipoRol.MENSUAL || tipoRol == TipoRol.QUINCENAL ){
							crearColeccionDetalleContinuoIngresoEgresoNormales(rolPagoDetalleCollection);
							crearColeccionDetalleIngresoEgresoNormales(rolPagoDetalleCollection);
					} else if ( tipoRol == TipoRol.APORTE_PATRONAL || tipoRol == TipoRol.FONDO_RESERVA 
							|| tipoRol == TipoRol.VACACIONES  
							|| tipoRol == TipoRol.DECIMO_TERCERO || tipoRol == TipoRol.DECIMO_CUARTO	)
						crearColeccionDetalleIngresoEgresoProvisionados(rolPagoDetalleCollection);
					else 
						SpiritAlert.createAlert("Tipo de Rol no considerado para presentación en tabla !!", SpiritAlert.WARNING);
					
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
							asientosId.add(datos.getAsientoId() != null ? datos.getAsientoId() : null);
						}

						Iterator<RubroContratoDatos> itEgresos = egresos.iterator();
						while ( itEgresos.hasNext() ){
							RubroContratoDatos datos = itEgresos.next();
							Vector<Object> fila = crearFilaTabla(datos);
							modelo.addRow(fila);
							asientosId.add(datos.getAsientoId() != null ? datos.getAsientoId() : null);
						}
					}

				}

			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			} catch(Exception e){
				e.printStackTrace();
				SpiritAlert.createAlert("Error al consultar Rol por Contrato", SpiritAlert.ERROR);
			} finally{
				setCursorNormal();
			}
		}
		else{

			Runnable r = new Runnable(){
				public void run() {
					try {
						setCursorEspera();					
						limpiarTabla(getTblRolPagoContrato());
						if ( rolPagoIf != null ){
							DefaultTableModel modelo = (DefaultTableModel)getTblRolPagoContrato().getModel();

							if ( empleadoIf != null && contratoIf == null) {
								SpiritAlert.createAlert("Debe elegir un contrato !!", SpiritAlert.INFORMATION);
								return;
							}

							//PARA RUBROS NO EVENTUALES 
							Collection<Long> contratosIdCollection = null;
							if ( contratoIf == null )
								contratosIdCollection = Contratos.obtenerContratosId(tipoRol,tipoContratoIf,rolPagoIf,letraEstadoDetalle);
							else {
								contratosIdCollection = new ArrayList<Long>();
								contratosIdCollection.add(contratoIf.getId());
							}
							rolPagoDetalleCollection = (ArrayList) SessionServiceLocator.getRolPagoSessionService()
							.crearColeccionContratos(contratosIdCollection,rolPagoIf,
								letraEstadoDetalle,true,false,false,"",false);
								//,EtapaRolPago.GENERACION_ASIENTO);

							rolPagoDetalleCollectionTodo = new ArrayList<RolPagoContratoContinuoDatos>();
							rolPagoDetalleMapaTodo = new HashMap<Long, RolPagoContratoDatos>();
							
							if ( tipoRol == TipoRol.MENSUAL || tipoRol == TipoRol.QUINCENAL ){
									crearColeccionDetalleContinuoIngresoEgresoNormales(rolPagoDetalleCollection);
									crearColeccionDetalleIngresoEgresoNormales(rolPagoDetalleCollection);
							} else if ( tipoRol == TipoRol.APORTE_PATRONAL || tipoRol == TipoRol.FONDO_RESERVA 
									|| tipoRol == TipoRol.VACACIONES  
									|| tipoRol == TipoRol.DECIMO_TERCERO || tipoRol == TipoRol.DECIMO_CUARTO	)
								crearColeccionDetalleIngresoEgresoProvisionados(rolPagoDetalleCollection);
							else 
								SpiritAlert.createAlert("Tipo de Rol no considerado para presentación en tabla !!", SpiritAlert.WARNING);
						

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
									asientosId.add(datos.getAsientoId() != null ? datos.getAsientoId() : null);
								}

								Iterator<RubroContratoDatos> itEgresos = egresos.iterator();
								while ( itEgresos.hasNext() ){
									RubroContratoDatos datos = itEgresos.next();
									Vector<Object> fila = crearFilaTabla(datos);
									modelo.addRow(fila);
									asientosId.add(datos.getAsientoId() != null ? datos.getAsientoId() : null);
								}
							}

						}

					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
					} catch(Exception e){
						e.printStackTrace();
						SpiritAlert.createAlert("Error al consultar Rol por Contrato", SpiritAlert.ERROR);
					} finally{
						setCursorNormal();
					}
				}
			};
			Thread t = new Thread(r);
			rolPagoDetalleCollectionTodo = null;
			rolPagoDetalleMapaTodo = null;
			t.start();
			t = null;
		}

	}

	private void crearColeccionDetalleIngresoEgresoNormales(Collection<Map<String, Object>> rolPagoDetallesCompleto) throws GenericBusinessException{

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
				datos.setObservacion(rolPagoDetalle.getObservacion());
				datos.setAsientoId(rolPagoDetalle.getAsientoId());
				
				RubroIf rubro = null;
				try {
					if ( rolPagoDetalle.getRubroId() != null){
						rubro = verificarRubrosEnMapa(rolPagoDetalle.getRubroId());
						datos.setEventual(false);
					}
					else{
						RubroEventualIf rubroEventual = SessionServiceLocator.getRubroEventualSessionService().getRubroEventual(rolPagoDetalle.getRubroEventualId());
						rubro = verificarRubrosEnMapa(rubroEventual.getRubroId());
						datos.setValor( rolPagoDetalle.getValor().doubleValue() );
						datos.setEventual(true);
						datos.setObservacion(rubroEventual.getObservacion());
					}
					datos.setNombreRubro(rubro.getNombre());
					
					OperacionNomina ingresoEgreso = UtilesNomina.getIngresoEgreso(tipoRolIf,rubro);
	
					if ( ingresoEgreso == OperacionNomina.INGRESO ){
						ingresos.add(datos);
					}
					else{
						egresos.add(datos);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					throw new GenericBusinessException("Error al buscar nombre de rubro !!");
				}
			}
			datosContrato.setTotalIngresos(totalIngresos);
			datosContrato.setTotalEgresos(totalEgresos);
		}
	}
	
	private void crearColeccionDetalleContinuoIngresoEgresoNormales(Collection<Map<String, Object>> rolPagoDetallesCompleto) throws GenericBusinessException{

		//INGRESOS Y EGRESOS NORMALES
		int contadorDetalleTodos = 1;
		for ( Map<String, Object> mapa : rolPagoDetallesCompleto ){
			totalIngresos = 0.0;
			totalEgresos=0.0;
			
			boolean esPar = contadorDetalleTodos % 2 == 0;
			
			Long contratoId = (Long) mapa.get("contratoId");
			
			
			Collection<RolPagoDetalleIf> rolPagoDetalles = (Collection<RolPagoDetalleIf>) mapa.get("detallesRolPago");
			String nombre = (String)mapa.get("nombreEmpleado");
			
			Double totalIngresosContrato = (Double)mapa.get("totalIngresos");
			totalIngresos = Utilitarios.redondeoValor(totalIngresosContrato);
			Double totalEgresosContrato = (Double)mapa.get("totalDescuentos");
			totalEgresos = Utilitarios.redondeoValor(totalEgresosContrato);
			
			
			ArrayList<RolPagoContratoContinuoDatos> ingresosDetalle = new ArrayList<RolPagoContratoContinuoDatos>();
			ArrayList<RolPagoContratoContinuoDatos> egresosDetalle = new ArrayList<RolPagoContratoContinuoDatos>();
			
			
			
			for ( RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles ){
				
				RolPagoContratoContinuoDatos datosContrato = new RolPagoContratoContinuoDatos(); 
					
				datosContrato.setContratoId(contratoId);
				datosContrato.setTotalIngresos(totalIngresos);
				datosContrato.setTotalEgresos(totalEgresos);
				datosContrato.setNombreEmpleado(nombre);
				datosContrato.setObservacion(rolPagoDetalle.getObservacion());
				try {
					RubroIf rubro = null;
					if ( rolPagoDetalle.getRubroId() != null){
						rubro = verificarRubrosEnMapa(rolPagoDetalle.getRubroId());
						datosContrato.setEventual(false);
					}
					else{
						RubroEventualIf rubroEventual = SessionServiceLocator.getRubroEventualSessionService().getRubroEventual(rolPagoDetalle.getRubroEventualId());
						rubro = verificarRubrosEnMapa(rubroEventual.getRubroId());
						datosContrato.setEventual(true);
						datosContrato.setObservacion(rubroEventual.getObservacion());
					}
					
					OperacionNomina ingresoEgreso = UtilesNomina.getIngresoEgreso(tipoRolIf,rubro);
					Double valor = Utilitarios.redondeoValor(rolPagoDetalle.getValor().doubleValue());
					
					datosContrato.setEstado(rolPagoDetalle.getEstado());
						
					if ( ingresoEgreso == OperacionNomina.INGRESO ){
						datosContrato.setValorIngreso(valor);
						datosContrato.setNombreRubroIngreso(rubro.getNombre());
						ingresosDetalle.add(datosContrato);
					}
					else{
						datosContrato.setValorEgreso(valor);
						datosContrato.setNombreRubroEgreso(rubro.getNombre());
						egresosDetalle.add(datosContrato);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					throw new GenericBusinessException("Error al buscar nombre de rubro !!");
				}
			
			}
			
			int maximo = Math.max(ingresosDetalle.size(), egresosDetalle.size());
			
			for ( int i = 0 ; i < maximo ; i++ ){
				RolPagoContratoContinuoDatos ingreso = null;
				RolPagoContratoContinuoDatos egreso = null;
				
				RolPagoContratoContinuoDatos nuevo = new RolPagoContratoContinuoDatos();
				nuevo.setContratoId(contratoId);
				nuevo.setTotalIngresos(totalIngresos);
				nuevo.setTotalEgresos(totalEgresos);
				nuevo.setNombreEmpleado(nombre);
				
				if ( i < ingresosDetalle.size() ){
					ingreso = ingresosDetalle.get(i);
					nuevo.setValorIngreso(ingreso.getValorIngreso());
					nuevo.setNombreRubroIngreso(ingreso.getNombreRubroIngreso());
				} else {
					nuevo.setValorIngreso(null);
					nuevo.setNombreRubroIngreso(null);
				}
				if ( i < egresosDetalle.size() ){
					egreso = egresosDetalle.get(i);
					nuevo.setValorEgreso(egreso.getValorEgreso());
					nuevo.setNombreRubroEgreso(egreso.getNombreRubroEgreso());
				} else {
					nuevo.setValorEgreso(null);
					nuevo.setNombreRubroEgreso(null);
				}
				
				rolPagoDetalleCollectionTodo.add(nuevo);
				
			}
			
			int numeroMinimoDetalle = 8; 
			if ( maximo <= numeroMinimoDetalle ){
				aumentarDetalleEnBlanco(contratoId, nombre, maximo,
						numeroMinimoDetalle);
				contadorDetalleTodos++;
			} /*else if ( maximo == numeroMinimoDetalle  ){
				aumentarDetalleEnBlanco(contratoId, nombre, maximo,
						numeroMinimoDetalle);
				contadorDetalleTodos++;
			}*/ else {
				//if  ( maximo > 7 && maximo <= 10 ) {
				//	contadorDetalleTodos += 2;
				//} else 
				if ( maximo >= 10 && maximo <= 25 ){
					if ( !esPar )
						aumentarDetalleEnBlanco(contratoId, nombre, 0,5);
					else 
						aumentarDetalleEnBlanco(contratoId, nombre, 0,33-maximo);
					contadorDetalleTodos += 2;
				} else {
					contadorDetalleTodos++;
				}
			}
		}
	}

	private void aumentarDetalleEnBlanco(Long contratoId, String nombre,
			int base, int cantidadAAumentar) {
		for ( int i = base ; i <= cantidadAAumentar ; i++ ){
			RolPagoContratoContinuoDatos nuevo = new RolPagoContratoContinuoDatos();
			nuevo.setContratoId(contratoId);
			nuevo.setTotalIngresos(totalIngresos);
			nuevo.setTotalEgresos(totalEgresos);
			nuevo.setNombreEmpleado(nombre);
			nuevo.setValorIngreso(null);
			nuevo.setNombreRubroIngreso(null);
			nuevo.setValorEgreso(null);
			nuevo.setNombreRubroEgreso(null);
			rolPagoDetalleCollectionTodo.add(nuevo);
		}
	}
	
	private void crearColeccionDetalleIngresoEgresoProvisionados(Collection<Map<String, Object>> rolPagoDetallesCompleto) throws GenericBusinessException{

		//INGRESOS Y EGRESOS NORMALES
		
		for ( Map<String, Object> mapa : rolPagoDetallesCompleto ){
			totalIngresos = 0.0;
			totalEgresos=0.0;
			
			Long contratoId = (Long) mapa.get("contratoId");
			RolPagoContratoDatos datosContrato = verificarMapaContrato(contratoId); 
			datosContrato.setContratoId(contratoId);
			
			Collection<RubroContratoDatos> ingresos =(Collection<RubroContratoDatos>) datosContrato.getIngresosRubro();
			//Collection<RubroContratoDatos> egresos =(Collection<RubroContratoDatos>) datosContrato.getEgresosRubro();
			
			Double totalIngresosContrato = (Double)mapa.get("total");
			totalIngresos = Utilitarios.redondeoValor(totalIngresosContrato);
			
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
					else{
						RubroEventualIf rubroEventual = SessionServiceLocator.getRubroEventualSessionService().getRubroEventual(rolPagoDetalle.getRubroEventualId());
						rubro = verificarRubrosEnMapa(rubroEventual.getRubroId());
						datos.setEventual(true);
						datos.setObservacion(rubroEventual.getObservacion());
					}
					datos.setNombreRubro(rubro.getNombre());
					
					ingresos.add(datos);
					
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					throw new GenericBusinessException("Error al buscar nombre de rubro !!");
				}
			}
			
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

	/*private void crearColeccionDetalleEventuales(Collection<Map<String, Object>> rolPagoDetallesCompleto) throws GenericBusinessException{

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
			}
			datosContrato.setTotalIngresos(totalIngresos);
			datosContrato.setTotalEgresos(totalEgresos);
		}

	}*/

	private Vector<Object> crearFilaTabla(RubroContratoDatos datos) throws GenericBusinessException {
		Vector<Object> fila = new Vector<Object>();
		fila.add(datos.getNombreRubro());
		
		if(datos.getNombreRubro().equals("ANTICIPO A DCTAR")){
			System.out.println("");
		}
		
		fila.add(datos.getValor());
		fila.add(datos.isEventual()?"         X":"");
		String estado = datos.getEstado();
		if (estado.equals(EstadoRolPagoDetalle.EMITIDO.getLetra())) 
			estado=EstadoRolPagoDetalle.EMITIDO.toString();
		else if ( estado.equals(EstadoRolPagoDetalle.PROVISIONADO.getLetra()) ) 
			estado = EstadoRolPagoDetalle.PROVISIONADO.toString(); 
		else if (estado.equals(EstadoRolPagoDetalle.PAGADO.getLetra())) 
			estado = EstadoRolPagoDetalle.PAGADO.toString();
		else if ( estado.equals(EstadoRolPagoDetalle.AUTORIZADO.getLetra()) )
			estado = EstadoRolPagoDetalle.AUTORIZADO.toString();
		fila.add(estado);
		fila.add(datos.getObservacion());
		
		return fila;
	}

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
			if ( tipoContratoIf == null ){
				return;
			}
			
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

			Collection<ContratoIf> contratos  = SessionServiceLocator.getContratoSessionService().findContratoByQueryConFecha(mapa);
			if ( contratos.size() == 1 ){
				contratoIf = contratos.iterator().next();
				getTxtContrato().setText(contratoIf.getCodigo());
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
		
		setAnchoComlumnas();
		
		//SE CARGA LOS ESTADOS DE LOS DETALLES DE ROL DE PAGO POR LOS QUE SE PUEDE BUSCAR
		getCmbEstadoDetalle().addItem(null);
		getCmbEstadoDetalle().addItem(EstadoRolPagoDetalle.EMITIDO);
		getCmbEstadoDetalle().addItem(EstadoRolPagoDetalle.AUTORIZADO);
		getCmbEstadoDetalle().addItem(EstadoRolPagoDetalle.PAGADO);
		
		//MES
		getCmbMes().setSelectedIndex(0);
		mesSeleccionado = (String) getCmbMes().getSelectedItem();
		
		//COMBO ANIO
		ModelUtil.establecerCmbAnio(getCmbAnio());
		anioSeleccionado = (String)getCmbAnio().getSelectedItem();

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

		//Alineamiento de la columna de valor
		getTblRolPagoContrato().getColumnModel().getColumn(COLUMNA_VALOR).setCellRenderer(
			new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		
	}
	
	private void setAnchoComlumnas(){
		TableColumnModel modelo = getTblRolPagoContrato().getColumnModel();
		TableColumn anchoColumna = modelo.getColumn(0);
		anchoColumna.setPreferredWidth(290);
		anchoColumna.setMaxWidth(290);
		anchoColumna = modelo.getColumn(1);
		anchoColumna.setPreferredWidth(80);
		anchoColumna.setMaxWidth(80);
		anchoColumna = modelo.getColumn(2);
		anchoColumna.setPreferredWidth(70);
		anchoColumna.setMaxWidth(70);
		anchoColumna = modelo.getColumn(3);
		anchoColumna.setPreferredWidth(90);
		anchoColumna.setMaxWidth(90);	
		//anchoColumna = modelo.getColumn(4);
		//anchoColumna.setPreferredWidth(95);

	}
	
	private void verificarSelecciones() throws GenericBusinessException{
		//try {
		rolGenerado = false;
		rolPagoDetalleMapaTodo = null;
		mapaParametros = null;
		rolPagoIf = null;
		rolPagoDetalleCollection = null;
		getTxtObservacion().setText("No Generado");
		totalIngresos = 0.0;
		totalEgresos = 0.0;
		limpiarTabla(getTblRolPagoContrato());
		getScpRolPagoContrato().setVisible(false);

		tipoRolIf = (TipoRolIf) getCmbTipoRol().getSelectedItem();
		if ( tipoRolIf == null )
			return;
		else{
			tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
			if ( tipoRol == null )
				return;
		}
		
		tipoContratoIf = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
		if ( tipoContratoIf == null )
			return;

		mesSeleccionado = (String)getCmbMes().getSelectedItem();
		String mes = formatoDosEnteros.format( Utilitarios.getMesInt(mesSeleccionado) + 1 );
		if ( mesSeleccionado == null )
			return;

		anioSeleccionado = (String) getCmbAnio().getSelectedItem();
		if ( anioSeleccionado == null )
			return;

		//if (contratoIf == null)
		//	return;

		mapaParametros =  new HashMap<String, Object>();
		mapaParametros.put("tiporolId", tipoRolIf.getId());
		mapaParametros.put("tipocontratoId", tipoContratoIf.getId());
		mapaParametros.put("mes", mes);
		mapaParametros.put("anio", anioSeleccionado);

		//rolPagoIf = RolPago.buscarRolPago(mapaParametros);
		EstadoRolPagoDetalle estadoDetalle = (EstadoRolPagoDetalle)getCmbEstadoDetalle().getSelectedItem(); 
		letraEstadoDetalle = estadoDetalle!=null? estadoDetalle.getLetra() : null ;
		Collection<RolPagoIf> rolesPagos = SessionServiceLocator.getRolPagoSessionService().findRolPagoByQuery(
				mapaParametros,contratoIf!=null?contratoIf.getId():null, letraEstadoDetalle );
		
		if ( rolesPagos!=null && rolesPagos.size()>0 ){
			rolPagoIf = rolesPagos.iterator().next();
			getTxtObservacion().setText("Rol Generado");
			rolGenerado = true;
		}
		else
			getTxtObservacion().setText("Sin detalle");

	}
	
	@Override
	public boolean validateFields() {

		
		//if ( tipoRolIf == null ){
		if ( getCmbTipoRol().getSelectedItem() == null ){
			SpiritAlert.createAlert("Seleccionar Tipo de Rol !!", SpiritAlert.INFORMATION);
			return false;
		}

		if ( getCmbMes().getSelectedItem() == null ){
			SpiritAlert.createAlert("Seleccionar Mes !!", SpiritAlert.INFORMATION);
			return false;
		}

		if ( getCmbAnio().getSelectedItem() == null ){
			SpiritAlert.createAlert("Seleccionar A\u00f1o !!", SpiritAlert.INFORMATION);
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

	public void report() {
		try {
			tipoContratoIf = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
			if ( tipoContratoIf == null ){
				SpiritAlert.createAlert("Seleccionar un tipo de contrato !!", SpiritAlert.INFORMATION);
				return;
			}
			if ( rolPagoIf != null){
				
				if ( getRbImpresionContinua().isSelected() && contratoIf == null ){
					if ( rolPagoDetalleCollectionTodo==null || rolPagoDetalleCollectionTodo.size() == 0){
						SpiritAlert.createAlert("Rol de pago no tiene detalle !!", SpiritAlert.INFORMATION);
						return;
					}
				} else{
					if ( rolPagoDetalleMapaTodo==null || rolPagoDetalleMapaTodo.size() == 0){
						SpiritAlert.createAlert("Rol de pago no tiene detalle !!", SpiritAlert.INFORMATION);
						return;
					}	
				}

				String fileName = "";
				if (getRbImpresionContinua().isSelected() && contratoIf == null)
					fileName = "jaspers/nomina/RPRolPagoContratoContinuo.jasper";
				else 
					fileName = "jaspers/nomina/RPRolPagoContratoPorPagina.jasper";

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
					parametrosMap.put("tituloRol","R O L   D E   P A G O   P O R   C O N T R A T O");
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
				
				ReportModelImpl.processReport(fileName, parametrosMap, dataSourceDetail, true);
				
				contratoCollection = null;
				
			}
		} catch ( GenericBusinessException e ) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
		} catch( Exception e ){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al generar Reporte !!", SpiritAlert.WARNING);
		}
	}
	
	
	
	public void guardarArchivoPdf(String identificador) {
		
		try {
			tipoContratoIf = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
			if ( tipoContratoIf == null ){
				SpiritAlert.createAlert("Seleccionar un tipo de contrato !!", SpiritAlert.INFORMATION);
				return;
			}
			
			if ( rolPagoIf != null){
				 if ( rolPagoDetalleMapaTodo==null || rolPagoDetalleMapaTodo.size() == 0){
					SpiritAlert.createAlert("Rol de pago no tiene detalle en generar!", SpiritAlert.INFORMATION);
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
				
				//ReportModelImpl.processReport(fileName, parametrosMap, dataSourceDetail, true);
				JasperPrint jasperPrint = null;
				 
				jasperPrint = JasperFillManager.fillReport(
						SpiritResourceManager.getInputStreamResource(Parametros.getRutaCarpetaReportes()+"/"+fileName),
						parametrosMap,
						dataSourceDetail);
				
				String nombre=identificador+"_"+empleadoIf.getIdentificacion()+"_"+mesSeleccionado+"_"+anioSeleccionado;
				
				JRPdfExporter exporter= new JRPdfExporter();				
		        File file = new File("C:\\rolesEnviadosMail\\"+nombre+".pdf");
		        exporter.setParameter(JRTextExporterParameter.JASPER_PRINT, jasperPrint);
		        exporter.setParameter(JRTextExporterParameter.OUTPUT_FILE, file);
		        exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, new Integer(250));
		        exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Integer(50));		        
		        exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Integer(2));
		        exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Integer(4));
		        exporter.exportReport();
					
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
		if ( getRbImpresionContinua().isSelected() && contratoIf == null ){
			/*ArrayList<RolPagoContratoContinuoDatos> a = (ArrayList<RolPagoContratoContinuoDatos>) rolPagoDetalleCollectionTodo;
			Iterator<RolPagoContratoContinuoDatos> it = a.iterator();
			int contador=0;
			Long contratoId = 0L;
			while ( it.hasNext() ){
				RolPagoContratoContinuoDatos r = it.next();
				if ( contador > 1 )
					it.remove();
				
				if ( !contratoId.equals(r.getContratoId()) ){
					contratoId = r.getContratoId();
					contador++;
				}
			}*/
			return rolPagoDetalleCollectionTodo;
			
		} else {
			if (rolPagoDetalleMapaTodo!=null){
				Iterator<Long> itMapa = rolPagoDetalleMapaTodo.keySet().iterator();
				while( itMapa.hasNext() ){
					Long contratoId = itMapa.next();
					
					RolPagoContratoDatos datoContrato = rolPagoDetalleMapaTodo.get(contratoId);
									
					Collection<RubroContratoDatos> ingresos = datoContrato.getIngresosRubro();
					JRBeanCollectionDataSource ingresosBeans = new JRBeanCollectionDataSource(ingresos);
					datoContrato.setIngresos(ingresosBeans);
					ingresos = null;
					
					Collection<RubroContratoDatos> egresos = datoContrato.getEgresosRubro();
					JRBeanCollectionDataSource egresosBeans = new JRBeanCollectionDataSource(egresos);
					datoContrato.setEgresos(egresosBeans);
					egresos = null;
					
					//cargo del empleado
					String cargoEmpleado = "";
					try {
						ContratoIf contrato = SessionServiceLocator.getContratoSessionService().getContrato(contratoId);
						EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(contrato.getEmpleadoId());
						if(empleado.getTipoempleadoId() != null){
							TipoEmpleadoIf tipoEmpleado = SessionServiceLocator.getTipoEmpleadoSessionService().getTipoEmpleado(empleado.getTipoempleadoId());
							cargoEmpleado = tipoEmpleado!=null? tipoEmpleado.getNombre() : "";
						}
					} catch (GenericBusinessException e) {
						e.printStackTrace();
					}					
					datoContrato.setCargoEmpleado(cargoEmpleado);
					
					coleccion.add(datoContrato);
					
				}
				Collections.sort((ArrayList<RolPagoContratoDatos>)coleccion, comparadorNombre);
			}
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
	
	class PopupListener extends MouseAdapter {
		JPopupMenu popupMenu = null;
		PopupListener(JPopupMenu popupMenu){
			this.popupMenu = popupMenu;
		}
		public void mousePressed(MouseEvent e) {
			showPopup(e);
		}
		public void mouseReleased(MouseEvent e) {
			showPopup(e);
		}
		private void showPopup(MouseEvent e) {
			if (e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON3) {
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

}
