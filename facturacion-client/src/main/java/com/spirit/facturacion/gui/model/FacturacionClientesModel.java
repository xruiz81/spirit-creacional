package com.spirit.facturacion.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.spirit.cartera.entity.NotaCreditoDetalleIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.gui.panel.JPFacturacionClientes;
import com.spirit.facturacion.gui.reporteData.FacturacionClientesData;
import com.spirit.facturacion.gui.reporteData.FacturacionClientesEquipoResumenData;
import com.spirit.facturacion.gui.reporteData.NotaCreditoClienteData;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.GeneralFinder;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.medios.entity.EquipoEmpleadoIf;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.entity.LogEquipoEmpleadoIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PlanMedioFacturacionIf;
import com.spirit.medios.entity.PlanMedioFormaPagoIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoFacturacionIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class FacturacionClientesModel extends JPFacturacionClientes {
	
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private ClienteOficinaCriteria clienteOficinaCriteria;
	protected ClienteOficinaIf clienteOficinaIf;
	protected ClienteIf clienteIf;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String CODIGO_FACTURA_REEMBOLSO = "FAR";
	private static final String ESTADO_TODOS = "TODOS";
	private static final String ESTADO_ANULADO = "A";
	private static String NOMBRE_MENU_FACTURACION = "FACTURACION";
	private BigDecimal totalTotal = new BigDecimal(0);
	private ArrayList<FacturacionClientesData> facturacionColeccion = new ArrayList<FacturacionClientesData>();
	private Vector<FacturacionClientesEquipoResumenData> facturacionEquipoResumenColeccion = new Vector<FacturacionClientesEquipoResumenData>();
	private Map<Long,TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long,TipoDocumentoIf>();
	private Map<Long,DocumentoIf> mapaDocumento = new HashMap<Long,DocumentoIf>();
	private Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
	private Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
	private Map<Long,CorporacionIf> mapaCorporacion = new HashMap<Long,CorporacionIf>();
	private Map<Long,EmpleadoIf> mapaEmpleado = new HashMap<Long,EmpleadoIf>();
	private Map<Long,EquipoTrabajoIf> mapaEquipoTrabajo = new HashMap<Long,EquipoTrabajoIf>();
	private Map<Long,EquipoEmpleadoIf> mapaEquipoEmpleado = new HashMap<Long,EquipoEmpleadoIf>();
	private Map<Long,LogEquipoEmpleadoIf> mapaLogEquipoEmpleado = new HashMap<Long,LogEquipoEmpleadoIf>();
	private static final String CON_IVA = "CON IVA";
	private static final String SIN_IVA = "SIN IVA";
	private static final String FACTURA_REEMBOLSO = "FAR";
	private static final String TIPO_CARTERA_CLIENTE = "C";
	private String filtro = "";
	private List<NotaCreditoClienteData> notasCreditoClienteDataColeccion = new ArrayList<NotaCreditoClienteData>();
	private static final String DOCUMENTO_NC_FACTURA_REEMBOLSO = "NCFR";
	private static final String DOCUMENTO_NOTA_CREDITO_CLIENTE = "NCFA";
	private static final String DOCUMENTO_NC_ANULACION_FACTURA = "NCAF";
	private static final String DOCUMENTO_NC_FACTURA_EXTERIOR = "NCFE";
	private static final String CODIGO_TIPO_DOCUMENTO_NOTA_CREDITO_CLIENTE = "NCC";
	private static BigDecimal totalNotasCredito = new BigDecimal(0);
	private Map<String,Double> mapaFacturacionMenosNotasCreditoCliente = new HashMap<String,Double>();
	private static final String TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA = "D";
	private static final String TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA = "P";
	
	
	public FacturacionClientesModel(){
		cargarMapas();
		cargarComboTipoDocumento();
		initKeyListeners();
		anchoColumnasTabla();
		showSaveMode();
		initListeners();
	}
	
	private void cargarComboTipoDocumento(){
		try {
			Long idModulo = ((ModuloIf) SessionServiceLocator.getModuloSessionService().findModuloByCodigo("FACT").iterator().next()).getId();
			List tiposDocumento = (List) GeneralFinder.findTipoDocumento(Parametros.getIdEmpresa(), idModulo);
			
			//Esta parte es para que aparezca Nota de Credito Cliente en el combo.
			Map aMap = new HashMap();
			aMap.put("empresaId", Parametros.getIdEmpresa());
			aMap.put("codigo", CODIGO_TIPO_DOCUMENTO_NOTA_CREDITO_CLIENTE);
			Collection tiposDocumentoNotaCreditoCliente = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQuery(aMap);
			if(tiposDocumentoNotaCreditoCliente.size()>0){
				TipoDocumentoIf tipoDocumentoNotaCreditoCliente = (TipoDocumentoIf)tiposDocumentoNotaCreditoCliente.iterator().next();
				tiposDocumento.add(tipoDocumentoNotaCreditoCliente);
			}
			
			tiposDocumento.add(ESTADO_TODOS);
			refreshCombo(getCmbTipoDocumento(),tiposDocumento);
			getCmbTipoDocumento().setSelectedItem(ESTADO_TODOS);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarMapas(){
		try {
			mapaTipoDocumento.clear();
			Collection tiposDocumento = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator tiposDocumentoIt = tiposDocumento.iterator();
			while(tiposDocumentoIt.hasNext()){
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)tiposDocumentoIt.next();
				mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
			}
			
			mapaDocumento.clear();
			Collection documentos = SessionServiceLocator.getDocumentoSessionService().getDocumentoList();
			Iterator documentosIt = documentos.iterator();
			while(documentosIt.hasNext()){
				DocumentoIf documento = (DocumentoIf)documentosIt.next();
				mapaDocumento.put(documento.getId(), documento);
			}
			
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList();
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while(clientesOficinaIt.hasNext()){
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf)clientesOficinaIt.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
			
			mapaCliente.clear();
			Collection clientes = SessionServiceLocator.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesIt = clientes.iterator();
			while(clientesIt.hasNext()){
				ClienteIf cliente = (ClienteIf)clientesIt.next();
				mapaCliente.put(cliente.getId(), cliente);
			}
			
			mapaCorporacion.clear();
			Collection corporaciones = SessionServiceLocator.getCorporacionSessionService().findCorporacionByEmpresaId(Parametros.getIdEmpresa());
			Iterator corporacionesIt = corporaciones.iterator();
			while(corporacionesIt.hasNext()){
				CorporacionIf corporacion = (CorporacionIf)corporacionesIt.next();
				mapaCorporacion.put(corporacion.getId(), corporacion);
			}
			
			mapaEmpleado.clear();
			Collection empleados = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Iterator empleadosIt = empleados.iterator();
			while(empleadosIt.hasNext()){
				EmpleadoIf empleado = (EmpleadoIf)empleadosIt.next();
				mapaEmpleado.put(empleado.getId(), empleado);
			}
			
			mapaEquipoTrabajo.clear();
			Collection equiposTrabajo = SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByEmpresaId(Parametros.getIdEmpresa());
			Iterator equiposTrabajoIt = equiposTrabajo.iterator();
			while(equiposTrabajoIt.hasNext()){
				EquipoTrabajoIf equipoTrabajo = (EquipoTrabajoIf)equiposTrabajoIt.next();
				mapaEquipoTrabajo.put(equipoTrabajo.getId(), equipoTrabajo);
			}
			
			mapaEquipoEmpleado.clear();
			Collection equiposEmpleados = SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByCodigoTipoOrdenAndByEmpresaId("CU", Parametros.getIdEmpresa());
			Iterator equiposEmpleadosIt = equiposEmpleados.iterator();
			while(equiposEmpleadosIt.hasNext()){
				EquipoEmpleadoIf equipoEmpleado = (EquipoEmpleadoIf)equiposEmpleadosIt.next();
				mapaEquipoEmpleado.put(equipoEmpleado.getEmpleadoId(), equipoEmpleado);
			}
			
			mapaLogEquipoEmpleado.clear();
			Collection logEquiposEmpleados = SessionServiceLocator.getLogEquipoEmpleadoSessionService().getLogEquipoEmpleadoList();
			Iterator logEquiposEmpleadosIt = logEquiposEmpleados.iterator();
			while(logEquiposEmpleadosIt.hasNext()){
				LogEquipoEmpleadoIf logEquipoEmpleado = (LogEquipoEmpleadoIf)logEquiposEmpleadosIt.next();
				mapaLogEquipoEmpleado.put(logEquipoEmpleado.getEmpleadoId(), logEquipoEmpleado);
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	public void initKeyListeners(){
		//getRbReporteCorporacion().setVisible(false);
		//getRbReporteEqCuentas().setVisible(false);
		//getRbReporteEjecutivo().setVisible(false);
		getRbReportePreimpreso().setSelected(true);		
		
		getTxtCliente().setEditable(false);
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
		
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnConsultar().setToolTipText("Consultar");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		getTblFacturacion().getColumnModel().getColumn(3).setCellRenderer(tableCellRendererCenter);
		getTblFacturacion().getColumnModel().getColumn(4).setCellRenderer(tableCellRendererCenter);
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblFacturacion().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(8).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(9).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(10).setCellRenderer(tableCellRenderer);
	}
	
	private void anchoColumnasTabla() {
		setSorterTable(getTblFacturacion());
		getTblFacturacion().getTableHeader().setReorderingAllowed(false);
		//getTblChequesEmitidos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblFacturacion().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblFacturacion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(115);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(100);
	}
	
	/*public void llenarFDCA(){
		try{
			Collection facturasDetalle = SessionServiceLocator.getFacturaDetalleSessionService().getFacturaDetalleList();
			Iterator facturasDetalleIt = facturasDetalle.iterator();
			while(facturasDetalleIt.hasNext()){
				FacturaDetalleIf facturaDetalle = (FacturaDetalleIf)facturasDetalleIt;
				if(facturaDetalle.g){
					
				}
			}
		}catch(Exception e){
			
		}
	}*/
	
	private void initListeners() {
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cleanTable();
				cargarTabla();
				report();
			}
		});
		
		getCbTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodos().isSelected()){
					clienteOficinaIf = null;
					getTxtCliente().setText("");
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
					clienteIf = mapaCliente.get(clienteOficinaIf.getClienteId());
					getTxtCliente().setText(clienteIf.getNombreLegal());
					getCbTodos().setSelected(false);
				}
			}
		});
		
		getRbReporteEquipo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getRbReporteEquipo().isSelected()){
					getCbReporteEquipoResumen().setEnabled(true);
				}
			}
		});
		
		getRbReportePreimpreso().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getRbReportePreimpreso().isSelected()){
					getCbReporteEquipoResumen().setEnabled(false);
					getCbReporteEquipoResumen().setSelected(false);
				}
			}
		});
		
		getRbReporteCorporacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getRbReporteCorporacion().isSelected()){
					getCbReporteEquipoResumen().setEnabled(false);
					getCbReporteEquipoResumen().setSelected(false);
				}
			}
		});
		
		getRbReporteCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getRbReporteCliente().isSelected()){
					getCbReporteEquipoResumen().setEnabled(false);
					getCbReporteEquipoResumen().setSelected(false);
				}
			}
		});
		
		getRbReporteEjecutivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getRbReporteEjecutivo().isSelected()){
					getCbReporteEquipoResumen().setEnabled(false);
					getCbReporteEquipoResumen().setSelected(false);
				}
			}
		});
		
		getCbReporteEquipoResumen().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbReporteEquipoResumen().isSelected()){
					getCmbIVA().setSelectedItem(SIN_IVA);
				}
			}
		});
		
		getCmbIVA().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCmbIVA().getSelectedItem().equals(CON_IVA)){
					getCbReporteEquipoResumen().setSelected(false);
				}
			}
		});
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	public void save() {
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void clean() {
		facturacionColeccion = null;
		facturacionColeccion = new ArrayList<FacturacionClientesData>();
		facturacionEquipoResumenColeccion = null;
		facturacionEquipoResumenColeccion = new Vector<FacturacionClientesEquipoResumenData>();
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		getCbReporteEquipoResumen().setEnabled(false);
		cleanTable();
	}

	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblFacturacion().getModel();
		for(int i= this.getTblFacturacion().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void report() {
		try {
			DefaultTableModel tblModelReporte = (DefaultTableModel) getTblFacturacion().getModel();
			if (tblModelReporte.getRowCount() > 0 || notasCreditoClienteDataColeccion.size() > 0) {				
				String si = "Si";
				String no = "No";
				Object[] options = {si,no};
				String mensaje = "¿Desea generar el reporte de Facturación?";
				int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if(opcion == JOptionPane.YES_OPTION) {
					
					//Si la colección de facturas esta vacia porque escogi tipo documento Notas de Credito 
					//tengo que aumentarle por lo menos un elemento para que salga el reporte de Notas de Credito.
					if(facturacionColeccion.size() == 0){
						FacturacionClientesData facturacionClientesDataBlanco = new FacturacionClientesData();
						facturacionClientesDataBlanco.setComision("0");
						facturacionClientesDataBlanco.setDescuento("0");
						facturacionClientesDataBlanco.setIva("0");
						facturacionClientesDataBlanco.setSubtotal("0");
						facturacionClientesDataBlanco.setSuma("0");
						facturacionClientesDataBlanco.setTotal("0");
						facturacionColeccion.add(facturacionClientesDataBlanco);
					}
					
					String fileName = "jaspers/facturacion/RPFacturacionPorPreimpresoConNotasCredito.jasper";
					if(getRbReporteCliente().isSelected()){
						fileName = "jaspers/facturacion/RPFacturacionPorClienteConNotasCredito.jasper";					
					}else if(getRbReporteCorporacion().isSelected()){
						fileName = "jaspers/facturacion/RPFacturacionPorCorporacionConNotasCredito.jasper";	
					}else if(getRbReporteEjecutivo().isSelected()){
						fileName = "jaspers/facturacion/RPFacturacionPorEjecutivoConNotasCredito.jasper";	
					}else if(getRbReporteEquipo().isSelected() && !getCbReporteEquipoResumen().isSelected()){
						fileName = "jaspers/facturacion/RPFacturacionPorEquipoConNotasCredito.jasper";	
					}else if(getRbReporteEquipo().isSelected() && getCbReporteEquipoResumen().isSelected()){
						fileName = "jaspers/facturacion/RPFacturacionPorEquipoResumen.jasper";	
					}else{
						fileName = "jaspers/facturacion/RPFacturacionPorPreimpresoConNotasCredito.jasper";	
					}
					
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_FACTURACION).iterator().next();
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre().substring(0,1).concat(ciudad.getNombre().substring(1, ciudad.getNombre().length()).toLowerCase()));
					parametrosMap.put("usuario", Parametros.getUsuario());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)).toLowerCase() + " del " + year + ".-";
					parametrosMap.put("emitido", fechaEmision);					
					Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
					Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
					parametrosMap.put("fechaInicio", Utilitarios.getFechaCortaUppercase(fechaInicio));
					parametrosMap.put("fechaFin", Utilitarios.getFechaCortaUppercase(fechaFin));
					parametrosMap.put("totalFacturacion", formatoDecimal.format(totalTotal));
					parametrosMap.put("filtro", filtro);
					parametrosMap.put("totalFacturacionNotasCredito", formatoDecimal.format(totalTotal.subtract(totalNotasCredito)));
					parametrosMap.put("mapaFacturacionMenosNotasCreditoCliente", mapaFacturacionMenosNotasCreditoCliente);
					
					JRDataSource dataSourceResumenNotasCredito = new JRBeanCollectionDataSource(notasCreditoClienteDataColeccion);
					parametrosMap.put("dataSourceResumenNotasCredito", dataSourceResumenNotasCredito);
					
					if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) ){
						if(getRbReporteCliente().isSelected()){
							parametrosMap.put("pathsubreportResumenNotasCredito", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPFacturacionPorClienteConNotasCreditoDetalle.jasper");
						}else{
							parametrosMap.put("pathsubreportResumenNotasCredito", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPFacturacionPorPreimpresoConNotasCreditoDetalle.jasper");
						}						
					}else{
						throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");					
					}				
				
					if(getRbReporteEquipo().isSelected() && getCbReporteEquipoResumen().isSelected()){
						ReportModelImpl.processReport(fileName, parametrosMap, facturacionEquipoResumenColeccion, true);
					}else{
						ReportModelImpl.processReport(fileName, parametrosMap, facturacionColeccion, true);
					}
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
		catch (ParseException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}
	
	public void cleanTotales(){
		facturacionColeccion = null;
		facturacionColeccion = new ArrayList<FacturacionClientesData>();
		facturacionEquipoResumenColeccion = null;
		facturacionEquipoResumenColeccion = new Vector<FacturacionClientesEquipoResumenData>();
		notasCreditoClienteDataColeccion.clear();
		totalTotal = new BigDecimal(0);
		totalNotasCredito = new BigDecimal(0);
		mapaFacturacionMenosNotasCreditoCliente.clear();
	}
	
	public Collection<FacturaIf> generarColeccionFacturas(){
		Collection<FacturaIf> facturas = null;		
		try {
			filtro = "TODOS";
			Map facturasMap = new HashMap();
			if(!getCmbTipoDocumento().getSelectedItem().equals(ESTADO_TODOS)){
				facturasMap.put("tipodocumentoId", ((TipoDocumentoIf)getCmbTipoDocumento().getSelectedItem()).getId());
				filtro = ((TipoDocumentoIf)getCmbTipoDocumento().getSelectedItem()).getNombre();
			}
			if(clienteOficinaIf != null){
				facturasMap.put("clienteoficinaId", clienteOficinaIf.getId());
			}
			if(getCmbIVA().getSelectedItem().equals(CON_IVA)){
				filtro = filtro + " " + CON_IVA;
			}else{
				filtro = filtro + " " + SIN_IVA;
			}
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());

			System.out.println("TODOS::::"+filtro);
			
			if(filtro.contains("TODOS"))
				facturas = SessionServiceLocator.getFacturaSessionService().findFacturasByQueryByFechaInicioAndByFechaFinEmpresaId(facturasMap, new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()),Parametros.getIdEmpresa());
			else
				facturas = SessionServiceLocator.getFacturaSessionService().findFacturasByQueryByFechaInicioAndByFechaFin(facturasMap, new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()));
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		return facturas;
	}
	
	Comparator<FacturacionClientesData> ordenadorFacturacionClientesDataPorPreimpreso = new Comparator<FacturacionClientesData>(){
		public int compare(FacturacionClientesData o1, FacturacionClientesData o2) {
			return o1.getNumeroFactura().compareTo(o2.getNumeroFactura());			
		}		
	};
	
	Comparator<FacturacionClientesData> ordenadorFacturacionClientesDataPorCliente = new Comparator<FacturacionClientesData>(){
		public int compare(FacturacionClientesData o1, FacturacionClientesData o2) {
			return o1.getCliente().compareTo(o2.getCliente());
			//return o1.getRuc().compareTo(o2.getRuc());
		}		
	};
	
	Comparator<FacturacionClientesData> ordenadorFacturacionClientesDataPorCorporacion = new Comparator<FacturacionClientesData>(){
		public int compare(FacturacionClientesData o1, FacturacionClientesData o2) {
			return o1.getCorporacion().compareTo(o2.getCorporacion());			
		}		
	};
	
	Comparator<FacturacionClientesData> ordenadorFacturacionClientesDataPorEjecutivo = new Comparator<FacturacionClientesData>(){
		public int compare(FacturacionClientesData o1, FacturacionClientesData o2) {
			return o1.getEjecutivo().compareTo(o2.getEjecutivo());			
		}		
	};	
	
	Comparator<FacturacionClientesData> ordenadorFacturacionClientesDataPorEquipo = new Comparator<FacturacionClientesData>(){
		public int compare(FacturacionClientesData o1, FacturacionClientesData o2) {
			return o1.getEquipo().compareTo(o2.getEquipo());			
		}		
	};
	
	Comparator<NotaCreditoIf> ordenadorNotaCreditoPorPreimpreso = new Comparator<NotaCreditoIf>(){
		public int compare(NotaCreditoIf o1, NotaCreditoIf o2) {
			if(o1.getPreimpreso() == null){
				return -1;
			}else if(o2.getPreimpreso() == null){
				return 1;
			}else{
				return o1.getPreimpreso().compareTo(o2.getPreimpreso());
			}
		}		
	};
	
	Comparator<NotaCreditoIf> ordenadorNotaCreditoPorNombreCliente = new Comparator<NotaCreditoIf>(){
		public int compare(NotaCreditoIf o1, NotaCreditoIf o2) {
			ClienteOficinaIf clienteOficina1 = mapaClienteOficina.get(o1.getOperadorNegocioId());
			ClienteIf cliente1 = mapaCliente.get(clienteOficina1.getClienteId());
			ClienteOficinaIf clienteOficina2 = mapaClienteOficina.get(o2.getOperadorNegocioId());
			ClienteIf cliente2 = mapaCliente.get(clienteOficina2.getClienteId());
			
			return cliente1.getNombreLegal().compareTo(cliente2.getNombreLegal());
		}		
	};
	
	public Collection<NotaCreditoIf> generarColeccionNotasCreditoCliente(){
		Collection<NotaCreditoIf> notasCreditoCliente = null;
		
		try {
			Map notasCreditoMap = new HashMap();
			notasCreditoMap.put("tipoCartera", TIPO_CARTERA_CLIENTE);
			notasCreditoMap.put("estado", "A"); //solo las activas
			if(clienteOficinaIf != null){
				notasCreditoMap.put("operadorNegocioId", clienteOficinaIf.getId());
			}
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
			
			notasCreditoCliente = SessionServiceLocator.getNotaCreditoSessionService().getNotaCreditoByMapFechaInicioFechaFin(notasCreditoMap, fechaInicio, fechaFin, Parametros.getIdEmpresa());
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		return notasCreditoCliente;
	}
	
	private void cargarTabla() {
		try {
			cleanTotales();
			Collection<FacturaIf> facturas = generarColeccionFacturas();
								
			Iterator facturasIterator = facturas.iterator();

			while (facturasIterator.hasNext()) {
				FacturaIf facturaIf = (FacturaIf) facturasIterator.next();

				tableModel = (DefaultTableModel) getTblFacturacion().getModel();
				Vector<String> fila = new Vector<String>();
				FacturacionClientesData facturacionData = new FacturacionClientesData();
				
				agregarColumnasTabla(facturaIf, fila, facturacionData);
				
				tableModel.addRow(fila);
				facturacionColeccion.add(facturacionData);
			}
			
			
			//ORDENAMOS EL ARREGLO DE DATOS
			//es muy importante que no se cambien las posiciones de ordenamiento
			if(getRbReportePreimpreso().isSelected()){
				Collections.sort((ArrayList)facturacionColeccion,ordenadorFacturacionClientesDataPorPreimpreso);
			}else if(getRbReporteCliente().isSelected()){
				Collections.sort((ArrayList)facturacionColeccion,ordenadorFacturacionClientesDataPorCliente);
			}else if(getRbReporteCorporacion().isSelected()){
				Collections.sort((ArrayList)facturacionColeccion,ordenadorFacturacionClientesDataPorCliente);
				Collections.sort((ArrayList)facturacionColeccion,ordenadorFacturacionClientesDataPorCorporacion);
			}else if(getRbReporteEjecutivo().isSelected()){
				Collections.sort((ArrayList)facturacionColeccion,ordenadorFacturacionClientesDataPorEjecutivo);
			}else if(getRbReporteEquipo().isSelected() && getCbReporteEquipoResumen().isSelected()){
				Collections.sort((ArrayList)facturacionColeccion,ordenadorFacturacionClientesDataPorCliente);
				Collections.sort((ArrayList)facturacionColeccion,ordenadorFacturacionClientesDataPorEjecutivo);
				Collections.sort((ArrayList)facturacionColeccion,ordenadorFacturacionClientesDataPorEquipo);
			}else if(getRbReporteEquipo().isSelected()){
				Collections.sort((ArrayList)facturacionColeccion,ordenadorFacturacionClientesDataPorEjecutivo);
				Collections.sort((ArrayList)facturacionColeccion,ordenadorFacturacionClientesDataPorEquipo);
			}
			
			//Para el reporte del Resumen por Equipos
			if(getCbReporteEquipoResumen().isSelected()){
				totalTotal = new BigDecimal(0);
				//Tomando en cuenta que facturacionColeccion esta agrupado por Equipo, Ejecutivo, Cliente
				for(FacturacionClientesData facturacionData : facturacionColeccion){
					
					FacturaIf factura = SessionServiceLocator.getFacturaSessionService().getFactura(facturacionData.getFacturaId());
					
					if(factura.getTipoNegociacion() == null || (factura.getTipoNegociacion() != null && !factura.getTipoNegociacion().equals(TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA))){
						
						Double[] clienteTotales = new Double[5];					
						double facturacionPreimpreso = 0D;
 						double negociacionPreimpreso = 0D;
 						double facturacionNegociacionPreimpreso = 0D;
						double suma = Double.valueOf(facturacionData.getSuma().replaceAll(",", ""));
						double descuento = Double.valueOf(facturacionData.getDescuento().replaceAll(",", ""));
						double comision = Double.valueOf(facturacionData.getComision().replaceAll(",", ""));
						
						if(getCmbIVA().getSelectedItem().equals(SIN_IVA) && facturacionData.getReembolso().equals("R")){
							BigDecimal sumaBigDecimal = BigDecimal.valueOf(suma);
							BigDecimal reembolsoSinIva = sumaBigDecimal.divide(new BigDecimal(1).add(BigDecimal.valueOf(Parametros.IVA).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
							suma = reembolsoSinIva.doubleValue();
						}
						
						ClienteOficinaIf clienteOficinaReal = null;
						ClienteIf clienteReal = null;
						
						double porcentajeNegociacion = 0D;
						double porcentajeDescuento = 0D;
						double porcentajeComision = 0D;
						
						///////////PRESUPUESTO////////////////////////
						
						//Busca si la factura existe en los registros de PRESUPUESTO_FACTURACION con tipo "D" (Negociación Directa)
						boolean facturaPresupuestoNegociacion = false;
						Map aMap = new HashMap();
						aMap.put("facturaId", factura.getId());
						aMap.put("tipo", TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA);
						Collection presupuestosFacturaNegociacionDirecta = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByQuery(aMap);
						Iterator presupuestosFacturaNegociacionDirectaIt = presupuestosFacturaNegociacionDirecta.iterator();
						//Si viene de un presupuesto con negociacion busco la orden de trabajo para saber el cliente real y cargar ese valor a su negociación.
						while(presupuestosFacturaNegociacionDirectaIt.hasNext()){
							PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturaNegociacionDirectaIt.next();
							PresupuestoDetalleIf presupuestoDetalleIf = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoFacturacionIf.getPresupuestoDetalleId());
							PresupuestoIf presupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalleIf.getPresupuestoId());
							
							if(presupuestoIf.getClienteOficinaId() != null){
								clienteOficinaReal = mapaClienteOficina.get(presupuestoIf.getClienteOficinaId());
								clienteReal = mapaCliente.get(clienteOficinaReal.getClienteId());
							}else{
								OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuestoIf.getOrdentrabajodetId());
								OrdenTrabajoIf ordenTrabajoIf = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalleIf.getOrdenId());
								clienteOficinaReal = mapaClienteOficina.get(ordenTrabajoIf.getClienteoficinaId());
								clienteReal = mapaCliente.get(clienteOficinaReal.getClienteId());
							}
							
							porcentajeNegociacion = presupuestoDetalleIf.getPorcentajeNegociacionDirecta().doubleValue() / 100;
							porcentajeDescuento = presupuestoDetalleIf.getPorcentajeDescuentoAgenciaCompra().doubleValue() / 100;
							
							facturaPresupuestoNegociacion = true;
						}
						
						//Busca si la factura existe en los registros de PRESUPUESTO_FACTURACION con tipo "P" (Comision Pura)
						boolean facturaPresupuestoComision = false;
						Map aMapComision = new HashMap();
						aMapComision.put("facturaId", factura.getId());
						aMapComision.put("tipo", TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA);
						Collection presupuestosFacturaComisionPura = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByQuery(aMapComision);
						Iterator presupuestosFacturaComisionPuraIt = presupuestosFacturaComisionPura.iterator();
						//Si viene de un presupuesto con negociacion busco la orden de trabajo para saber el cliente real y cargar ese valor a su negociación.
						while(presupuestosFacturaComisionPuraIt.hasNext()){
							PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturaComisionPuraIt.next();
							PresupuestoDetalleIf presupuestoDetalleIf = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoFacturacionIf.getPresupuestoDetalleId());
							PresupuestoIf presupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalleIf.getPresupuestoId());
							
							if(presupuestoIf.getClienteOficinaId() != null){
								clienteOficinaReal = mapaClienteOficina.get(presupuestoIf.getClienteOficinaId());
								clienteReal = mapaCliente.get(clienteOficinaReal.getClienteId());
							}else{
								OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuestoIf.getOrdentrabajodetId());
								OrdenTrabajoIf ordenTrabajoIf = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalleIf.getOrdenId());
								clienteOficinaReal = mapaClienteOficina.get(ordenTrabajoIf.getClienteoficinaId());
								clienteReal = mapaCliente.get(clienteOficinaReal.getClienteId());								
							}							
							
							porcentajeNegociacion = presupuestoDetalleIf.getPorcentajeNegociacionDirecta().doubleValue() / 100;
							porcentajeComision = presupuestoDetalleIf.getPorcentajeComisionPura().doubleValue() / 100;
							
							facturaPresupuestoComision = true;
						}
						
						///////////PLAN DE MEDIOS////////////////////////
						
						//Busca si la factura existe en los registros de PlanMedioFacturacion y veo si tiene canje y por cuanto
						boolean planMedioFacturacionCanje = false;
						double porcentajeCanjeMedios = 0D; 
						double porcentajeDescuentoMedios = 0D; 
						Collection planMediosFacturacion = SessionServiceLocator.getPlanMedioFacturacionSessionService().findPlanMedioFacturacionByPedidoId(factura.getPedidoId());
						Iterator planMediosFacturacionIt = planMediosFacturacion.iterator();
						while(planMediosFacturacionIt.hasNext()){
							PlanMedioFacturacionIf planMedioFacturacionIf = (PlanMedioFacturacionIf)planMediosFacturacionIt.next();
							if(planMedioFacturacionIf.getPorcentajeCanje() != null && 
									planMedioFacturacionIf.getPorcentajeCanje().compareTo(new BigDecimal(0)) == 1){
								planMedioFacturacionCanje = true;
								porcentajeCanjeMedios = planMedioFacturacionIf.getPorcentajeCanje().doubleValue() / 100;
								
								OrdenMedioIf ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(planMedioFacturacionIf.getOrdenMedioId());
								porcentajeDescuentoMedios = ordenMedioIf.getPorcentajeNegociacionComision().doubleValue() / 100;
																	
								clienteOficinaReal = mapaClienteOficina.get(ordenMedioIf.getClienteOficinaId());
								clienteReal = mapaCliente.get(clienteOficinaReal.getClienteId());
							}
						}
						
						
						//Si la factura viene de negociacion o comisión se debe encontrar el cliente real que origino la negociacion o comision
						//caso contrario si es una factura normal se toma el cliente de la factura.
						if(facturaPresupuestoNegociacion || facturaPresupuestoComision){
							//Sabiendo los porcentajes calculo los valores hacia atras para saber cual fue la inversion del cliente
							facturacionNegociacionPreimpreso = suma - descuento + comision;
							
							if(getCmbIVA().getSelectedItem().equals(CON_IVA)){
								facturacionNegociacionPreimpreso = facturacionNegociacionPreimpreso + Double.valueOf(facturacionData.getIva().replaceAll(",",""));
							}
							
							if(facturaPresupuestoNegociacion){
								negociacionPreimpreso = facturacionNegociacionPreimpreso / (porcentajeNegociacion * porcentajeDescuento);
							}
							else if(facturaPresupuestoComision){
								negociacionPreimpreso = facturacionNegociacionPreimpreso / porcentajeComision;								
							}
							
							//Sabiendo la inversion total le aplico nuevamente el porcentaje de negociacion para saber solo el valor del porcentaje de la compra
							//ya que el valor de venta si se le facturo al cliente directamente, este valor mas el de compra dara la inversion del cliente
							negociacionPreimpreso = negociacionPreimpreso * porcentajeNegociacion;
							
						}
						else if(planMedioFacturacionCanje){
							negociacionPreimpreso = suma - descuento + comision;
							negociacionPreimpreso = negociacionPreimpreso / (porcentajeCanjeMedios * porcentajeDescuentoMedios);
							negociacionPreimpreso = negociacionPreimpreso * porcentajeCanjeMedios;							
						}					
						
						else if(factura.getTipoNegociacion() != null && factura.getClienteNegociacionId() != null){
							//Sabiendo los porcentajes calculo los valores hacia atras para saber cual fue la inversion del cliente
							facturacionNegociacionPreimpreso = suma - descuento + comision;
							
							if(getCmbIVA().getSelectedItem().equals(CON_IVA)){
								facturacionNegociacionPreimpreso = facturacionNegociacionPreimpreso + Double.valueOf(facturacionData.getIva().replaceAll(",",""));
							}
							
							porcentajeNegociacion = factura.getPorcentajeNegociacionDirecta().doubleValue();
							porcentajeDescuento = factura.getPorcentajeDescuentoNegociacion().doubleValue();
							negociacionPreimpreso = (facturacionNegociacionPreimpreso * porcentajeNegociacion) / porcentajeDescuento;
							
							//Encuentro al cliente que provoco la negociación
							clienteOficinaReal = mapaClienteOficina.get(factura.getClienteNegociacionId());
							clienteReal = mapaCliente.get(clienteOficinaReal.getClienteId());
							
						}else{
							facturacionPreimpreso = suma - descuento + comision;
							
							if(getCmbIVA().getSelectedItem().equals(CON_IVA)){
								facturacionPreimpreso = facturacionPreimpreso + Double.valueOf(facturacionData.getIva().replaceAll(",",""));
							}
							
							clienteOficinaReal = mapaClienteOficina.get(factura.getClienteoficinaId());
							clienteReal = mapaCliente.get(clienteOficinaReal.getClienteId());
						}							
						
						//facturacion
						//TODO: restar Notas de Credito
						clienteTotales[0] = facturacionPreimpreso;
						//negociacion
						clienteTotales[1] = negociacionPreimpreso;
						//facturacion por negociacion
						clienteTotales[2] = facturacionNegociacionPreimpreso;
						//iva (se pidio retirar la columna de IVA del reporte ya que el IVA es mas para reportes tributarios)
						clienteTotales[3] = 0D; //Double.valueOf(facturacionData.getIva().replaceAll(",", ""));
						//total
						clienteTotales[4] = clienteTotales[0] + clienteTotales[1] + clienteTotales[3];
											
						totalTotal = totalTotal.add(BigDecimal.valueOf(clienteTotales[4]));
												
						FacturacionClientesEquipoResumenData facturacionEquipoResumenData = new FacturacionClientesEquipoResumenData();
						facturacionEquipoResumenData.setEquipoId(facturacionData.getEquipoId());
						facturacionEquipoResumenData.setEquipo(mapaEquipoTrabajo.get(Long.valueOf(facturacionData.getEquipoId())).getCodigo());
						facturacionEquipoResumenData.setEjecutivoId(facturacionData.getEjecutivoId());
						EmpleadoIf ejecutivo = mapaEmpleado.get(Long.valueOf(facturacionData.getEjecutivoId()));
						facturacionEquipoResumenData.setEjecutivo(ejecutivo.getNombres() + " " + ejecutivo.getApellidos());
						facturacionEquipoResumenData.setClienteId(clienteReal.getId().toString());
						facturacionEquipoResumenData.setCliente(clienteOficinaReal.getDescripcion());
						//facturacionEquipoResumenData.setCliente(clienteReal.getNombreLegal());
						facturacionEquipoResumenData.setFacturacionCliente(formatoDecimal.format(clienteTotales[0]));
						facturacionEquipoResumenData.setNegociacionCliente(formatoDecimal.format(clienteTotales[1]));
						facturacionEquipoResumenData.setFacturacionNegociacionCliente(formatoDecimal.format(clienteTotales[2]));
						facturacionEquipoResumenData.setIvaCliente(formatoDecimal.format(clienteTotales[3]));
						facturacionEquipoResumenData.setTotalCliente(formatoDecimal.format(clienteTotales[4]));
						facturacionEquipoResumenColeccion.add(facturacionEquipoResumenData);
					}else{
						System.out.println("a");
					}
				}
			}
			
			if(!getCbSinNotasCredito().isSelected()){
				//esta coleccion la lleno para presentarla en el reporte, no se ve en pantalla.				
				Collection<NotaCreditoIf> notasCreditoCliente = generarColeccionNotasCreditoCliente();
				
				//ordeno la coleccion deacuerdo al criterio de busqueda
				if(getRbReportePreimpreso().isSelected()){
					Collections.sort((ArrayList)notasCreditoCliente,ordenadorNotaCreditoPorPreimpreso);
				}else if(getRbReporteCliente().isSelected()){
					Collections.sort((ArrayList)notasCreditoCliente,ordenadorNotaCreditoPorNombreCliente);
				}
				
				Iterator notasCreditoClienteIt = notasCreditoCliente.iterator();
				while(notasCreditoClienteIt.hasNext()){
					NotaCreditoIf notaCredito = (NotaCreditoIf)notasCreditoClienteIt.next();
					
					ClienteOficinaIf clienteOficina = mapaClienteOficina.get(notaCredito.getOperadorNegocioId());
					ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());
					
					//Para saber que documento es tengo que buscar por lo menos un detalle
					Collection notasCreditoDetalle = SessionServiceLocator.getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByNotaCreditoId(notaCredito.getId());
					if(notasCreditoDetalle.size()>0){
						NotaCreditoDetalleIf notaCreditoDetalle = (NotaCreditoDetalleIf) SessionServiceLocator.getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByNotaCreditoId(notaCredito.getId()).iterator().next();
						
						NotaCreditoClienteData notaCreditoData = new NotaCreditoClienteData();
						notaCreditoData.setClienteId(cliente.getId().toString());
						notaCreditoData.setRuc(cliente.getIdentificacion());
						notaCreditoData.setAutorizacion(notaCredito.getAutorizacion());
						notaCreditoData.setCliente(cliente.getNombreLegal());
						notaCreditoData.setFechaEmision(Utilitarios.getFechaCortaUppercase(notaCredito.getFechaEmision()));
						notaCreditoData.setObservacion(notaCredito.getObservacion());
						notaCreditoData.setPreimpreso(notaCredito.getPreimpreso());				
						
						DocumentoIf documento = mapaDocumento.get(notaCreditoDetalle.getDocumentoId());
						
						if(documento.getCodigo().equals(DOCUMENTO_NOTA_CREDITO_CLIENTE)){
							notaCreditoData.setDocumentoNotaCredito(DOCUMENTO_NOTA_CREDITO_CLIENTE.substring(2));
						}else if(documento.getCodigo().equals(DOCUMENTO_NC_FACTURA_REEMBOLSO)){
							notaCreditoData.setDocumentoNotaCredito(DOCUMENTO_NC_FACTURA_REEMBOLSO.substring(2));
						}else if(documento.getCodigo().equals(DOCUMENTO_NC_ANULACION_FACTURA)){
							notaCreditoData.setDocumentoNotaCredito(DOCUMENTO_NC_ANULACION_FACTURA.substring(2));
						}else if(documento.getCodigo().equals(DOCUMENTO_NC_FACTURA_EXTERIOR)){
							notaCreditoData.setDocumentoNotaCredito(DOCUMENTO_NC_FACTURA_EXTERIOR.substring(2));
						}						
						
						BigDecimal total = new BigDecimal(0);
						if(getCmbIVA().getSelectedItem().equals(SIN_IVA) || getCbReporteEquipoResumen().isSelected()){
							BigDecimal subTotal = new BigDecimal(0);
											
							if(documento.getCodigo().equals(DOCUMENTO_NC_FACTURA_REEMBOLSO)){
								subTotal = notaCredito.getValor().divide(new BigDecimal(1).add(BigDecimal.valueOf(Parametros.IVA).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);				
							}else{
								subTotal = notaCredito.getValor();										
							}		
							notaCreditoData.setIva(formatoDecimal.format(new BigDecimal(0)));
							notaCreditoData.setSubtotal(formatoDecimal.format(subTotal));
							notaCreditoData.setTotal(formatoDecimal.format(subTotal));
							total = subTotal;
							totalNotasCredito = totalNotasCredito.add(total);
						}else{
							notaCreditoData.setIva(formatoDecimal.format(notaCredito.getIva()));
							notaCreditoData.setSubtotal(formatoDecimal.format(notaCredito.getValor()));
							total = notaCredito.getValor().add(notaCredito.getIva());
							notaCreditoData.setTotal(formatoDecimal.format(total));
							totalNotasCredito = totalNotasCredito.add(total);
						}
						
						notasCreditoClienteDataColeccion.add(notaCreditoData);
						
						//para calcular total de facturacion menos notas de credito por cliente
						if(mapaFacturacionMenosNotasCreditoCliente.get(cliente.getId().toString()) == null || mapaFacturacionMenosNotasCreditoCliente.get(cliente.getId().toString()).compareTo(0D) == 0){
							mapaFacturacionMenosNotasCreditoCliente.put(cliente.getId().toString(), 0D-total.doubleValue());
						}else{
							mapaFacturacionMenosNotasCreditoCliente.put(cliente.getId().toString(), mapaFacturacionMenosNotasCreditoCliente.get(cliente.getId().toString())-total.doubleValue());
						}
											
					}else{
						System.out.println("NOTA DE CREDITO SIN DETALLE: " + notaCredito.getId());
					}				
				}
			}
			
			

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void agregarColumnasTabla(FacturaIf facturaIf, Vector<String> fila, FacturacionClientesData facturacionData) {
		ClienteOficinaIf clienteOficina = mapaClienteOficina.get(facturaIf.getClienteoficinaId());
		ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());
		CorporacionIf corporacion = mapaCorporacion.get(cliente.getCorporacionId());
		EmpleadoIf ejecutivo = mapaEmpleado.get(facturaIf.getVendedorId());
		
		EquipoEmpleadoIf equipoEmpleado = null;
		LogEquipoEmpleadoIf logEquipoEmpleado = null;
		EquipoTrabajoIf equipoTrabajo = null;
		//Si en la factura ya tiene a que equipo pertenece no es necesaria buscar el equipo del vendedor
		if(facturaIf.getEquipoId() != null){
			equipoTrabajo = mapaEquipoTrabajo.get(facturaIf.getEquipoId());
		}else{
			//Si el empleado no existe en la tabla equipo_empleado lo busco en el log_equipo_empleado
			if(mapaEquipoEmpleado.get(ejecutivo.getId()) != null)
				equipoEmpleado = mapaEquipoEmpleado.get(ejecutivo.getId());
			else if(mapaLogEquipoEmpleado.get(ejecutivo.getId()) != null)
				logEquipoEmpleado = mapaLogEquipoEmpleado.get(ejecutivo.getId());
			
			if(equipoEmpleado != null && mapaEquipoTrabajo.get(equipoEmpleado.getEquipoId()) != null)
				equipoTrabajo = mapaEquipoTrabajo.get(equipoEmpleado.getEquipoId());
			else if(logEquipoEmpleado != null && mapaEquipoTrabajo.get(logEquipoEmpleado.getEquipoId()) != null)
				equipoTrabajo = mapaEquipoTrabajo.get(logEquipoEmpleado.getEquipoId());
		}
		
		//seteo el id de la factura para en los reportes resumen encontrar mas facilmente la factura y ver si es de negociacion.
		facturacionData.setFacturaId(facturaIf.getId());
				
		fila.add(facturaIf.getPreimpresoNumero()!=null?facturaIf.getPreimpresoNumero():"");			
		fila.add(Utilitarios.getFechaCortaUppercase(facturaIf.getFechaFactura()));
		fila.add(cliente.getNombreLegal());
		facturacionData.setClienteId(cliente.getId().toString());
		facturacionData.setCliente(cliente.getNombreLegal());
		facturacionData.setCorporacionId(corporacion.getId().toString());
		facturacionData.setCorporacion(corporacion.getNombre());
		facturacionData.setEjecutivoId(ejecutivo.getId().toString());
		facturacionData.setEjecutivo(ejecutivo.getNombres() + " " + ejecutivo.getApellidos());
		
		/*if(equipoTrabajo != null && equipoTrabajo.getCodigo().equals("EQPR003")){
			int a = 0;
		}*/
		
		facturacionData.setEquipoId(equipoTrabajo != null? equipoTrabajo.getId().toString() : "");
		facturacionData.setEquipo(equipoTrabajo != null? equipoTrabajo.getCodigo() : "");
		facturacionData.setRuc(cliente.getIdentificacion());
		facturacionData.setFechaEmision(Utilitarios.getFechaCortaUppercase(facturaIf.getFechaFactura()));
		facturacionData.setNumeroFactura(facturaIf.getPreimpresoNumero()!=null?facturaIf.getPreimpresoNumero():"");
		//facturacionData.setNumeroFactura(facturaIf.getPreimpresoNumero() + " - " + cliente.getNombreLegal()!=null?cliente.getNombreLegal():"");
		
		TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(facturaIf.getTipodocumentoId());
		if(tipoDocumento.getCodigo().equals(CODIGO_FACTURA_REEMBOLSO)){
			fila.add("R");
			facturacionData.setReembolso("R");
		} else {
			fila.add("");
			facturacionData.setReembolso("");
		}
		
		if(facturaIf.getEstado().equals(ESTADO_ANULADO)){
			fila.add("A");
			facturacionData.setAnulada("A");
			
			fila.add(formatoDecimal.format(new BigDecimal(0)));
			facturacionData.setSuma(formatoDecimal.format(new BigDecimal(0)));
			
			fila.add(formatoDecimal.format(new BigDecimal(0)));
			facturacionData.setDescuento(formatoDecimal.format(new BigDecimal(0)));
			
			fila.add(formatoDecimal.format(new BigDecimal(0)));
			facturacionData.setComision(formatoDecimal.format(new BigDecimal(0)));
			
			fila.add(formatoDecimal.format(new BigDecimal(0)));
			facturacionData.setSubtotal(formatoDecimal.format(new BigDecimal(0)));
			
			fila.add(formatoDecimal.format(new BigDecimal(0)));
			facturacionData.setIva(formatoDecimal.format(new BigDecimal(0)));
			
			fila.add(formatoDecimal.format(new BigDecimal(0)));
			totalTotal = totalTotal.add(new BigDecimal(0));
			facturacionData.setTotal(formatoDecimal.format(new BigDecimal(0)));
			
		}else{
			/*String infoPresupuesto = "";
			try {
				PedidoIf pedidoIf = SessionServiceLocator.getPedidoSessionService().getPedido(facturaIf.getPedidoId());
				String tipoReferencia = pedidoIf.getTiporeferencia();
				if(tipoReferencia.equals("P")){
					Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByFacturaId(facturaIf.getId());
					Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
					while(presupuestosFacturacionIt.hasNext()){
						PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
						PresupuestoDetalleIf presupuestoDetalleIf = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoFacturacionIf.getPresupuestoDetalleId());
						PresupuestoIf presupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalleIf.getPresupuestoId());
						//if(presupuestoIf.getConcepto().contains("JINGLE") || presupuestoDetalleIf.getConcepto().contains("JINGLE")){
							int mes = presupuestoIf.getFecha().getMonth()+1;
							//infoPresupuesto = String.valueOf(mes);
							infoPresupuesto = presupuestoIf.getCodigo();
						//}						
					}
				}				
				else if(tipoReferencia.equals("I")){
					Collection planMedioFormasPago = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(pedidoIf.getId());
					Iterator planMedioFormasPagoIt = planMedioFormasPago.iterator();
					while(planMedioFormasPagoIt.hasNext()){
						PlanMedioFormaPagoIf planMedioFormaPagoIf = (PlanMedioFormaPagoIf)planMedioFormasPagoIt.next();
						PlanMedioIf planMedioIf = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(planMedioFormaPagoIf.getPlanMedioId());
						int mes = planMedioIf.getFechaInicio().getMonth()+1;
						//infoPresupuesto = String.valueOf(mes);
						infoPresupuesto = planMedioIf.getCodigo();
					}
				}
				else if(tipoReferencia.equals("C")){
					Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByFacturaId(facturaIf.getId());
					Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
					while(presupuestosFacturacionIt.hasNext()){
						PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
						PresupuestoDetalleIf presupuestoDetalleIf = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoFacturacionIf.getPresupuestoDetalleId());
						PresupuestoIf presupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalleIf.getPresupuestoId());
						//if(presupuestoIf.getConcepto().contains("JINGLE") || presupuestoDetalleIf.getConcepto().contains("JINGLE")){
							int mes = presupuestoIf.getFecha().getMonth()+1;
							//infoPresupuesto = String.valueOf(mes);
							infoPresupuesto = presupuestoIf.getCodigo();
						//}						
					}
					Collection planMedioFormasPago = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(pedidoIf.getId());
					Iterator planMedioFormasPagoIt = planMedioFormasPago.iterator();
					while(planMedioFormasPagoIt.hasNext()){
						PlanMedioFormaPagoIf planMedioFormaPagoIf = (PlanMedioFormaPagoIf)planMedioFormasPagoIt.next();
						PlanMedioIf planMedioIf = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(planMedioFormaPagoIf.getPlanMedioId());
						int mes = planMedioIf.getFechaInicio().getMonth()+1;
						//infoPresupuesto = String.valueOf(mes);
						infoPresupuesto = planMedioIf.getCodigo();
					}
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}*/
			
			//fila.add(infoPresupuesto);
			//facturacionData.setAnulada(infoPresupuesto);
			
			fila.add("");
			facturacionData.setAnulada("");
			
			BigDecimal total = new BigDecimal(0);
			if(getCmbIVA().getSelectedItem().equals(SIN_IVA)){
				BigDecimal subTotal = new BigDecimal(0);
								
				if(tipoDocumento.getCodigo().equals(FACTURA_REEMBOLSO)){
					subTotal = facturaIf.getValor().divide(new BigDecimal(1).add(BigDecimal.valueOf(Parametros.IVA).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);				
				}else{
					subTotal = facturaIf.getValor();										
				}
				
				BigDecimal descuentoTotal = facturaIf.getDescuento().add(facturaIf.getDescuentosVarios()).add(facturaIf.getDescuentoGlobal());
				BigDecimal porcentajeComision = new BigDecimal(0);
				if(facturaIf.getPorcentajeComisionAgencia() != null){
					porcentajeComision = facturaIf.getPorcentajeComisionAgencia();
				}
				BigDecimal comisionAgencia = (subTotal.subtract(descuentoTotal)).multiply(porcentajeComision.divide(new BigDecimal(100)));
				subTotal = subTotal.subtract(descuentoTotal).add(comisionAgencia);
				
				if (tipoDocumento.getCodigo().equals("DEV")) {
					fila.add(formatoDecimal.format(facturaIf.getValor().multiply(BigDecimal.valueOf(-1D))));
					facturacionData.setSuma(formatoDecimal.format(facturaIf.getValor().multiply(BigDecimal.valueOf(-1D))));
					fila.add(formatoDecimal.format(descuentoTotal.multiply(BigDecimal.valueOf(-1D))));
					facturacionData.setDescuento(formatoDecimal.format(descuentoTotal.multiply(BigDecimal.valueOf(-1D))));
					fila.add(formatoDecimal.format(comisionAgencia.multiply(BigDecimal.valueOf(-1D))));
					facturacionData.setComision(formatoDecimal.format(comisionAgencia.multiply(BigDecimal.valueOf(-1D))));
					fila.add(formatoDecimal.format(subTotal.multiply(BigDecimal.valueOf(-1D))));
					facturacionData.setSubtotal(formatoDecimal.format(subTotal.multiply(BigDecimal.valueOf(-1D))));
				} else {
					fila.add(formatoDecimal.format(facturaIf.getValor()));
					facturacionData.setSuma(formatoDecimal.format(facturaIf.getValor()));
					fila.add(formatoDecimal.format(descuentoTotal));
					facturacionData.setDescuento(formatoDecimal.format(descuentoTotal));
					fila.add(formatoDecimal.format(comisionAgencia));
					facturacionData.setComision(formatoDecimal.format(comisionAgencia));
					fila.add(formatoDecimal.format(subTotal));
					facturacionData.setSubtotal(formatoDecimal.format(subTotal));
				}
				
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				facturacionData.setIva(formatoDecimal.format(new BigDecimal(0)));				
				total = subTotal;
			}else{
				BigDecimal descuentoTotal = facturaIf.getDescuento().add(facturaIf.getDescuentosVarios()).add(facturaIf.getDescuentoGlobal());
				BigDecimal porcentajeComision = new BigDecimal(0);
				if(facturaIf.getPorcentajeComisionAgencia() != null){
					porcentajeComision = facturaIf.getPorcentajeComisionAgencia();
				}
				BigDecimal comisionAgencia = (facturaIf.getValor().subtract(descuentoTotal)).multiply(porcentajeComision.divide(new BigDecimal(100)));
				BigDecimal subTotal = facturaIf.getValor().subtract(descuentoTotal).add(comisionAgencia);
				if (tipoDocumento.getCodigo().equals("DEV")){
					fila.add(formatoDecimal.format(facturaIf.getValor().multiply(BigDecimal.valueOf(-1D))));
					facturacionData.setSuma(formatoDecimal.format(facturaIf.getValor().multiply(BigDecimal.valueOf(-1D))));
					fila.add(formatoDecimal.format(descuentoTotal.multiply(BigDecimal.valueOf(-1D))));
					facturacionData.setDescuento(formatoDecimal.format(descuentoTotal.multiply(BigDecimal.valueOf(-1D))));
					fila.add(formatoDecimal.format(comisionAgencia.multiply(BigDecimal.valueOf(-1D))));
					facturacionData.setComision(formatoDecimal.format(comisionAgencia.multiply(BigDecimal.valueOf(-1D))));
					fila.add(formatoDecimal.format(subTotal.multiply(BigDecimal.valueOf(-1D))));
					facturacionData.setSubtotal(formatoDecimal.format(subTotal.multiply(BigDecimal.valueOf(-1D))));
					fila.add(formatoDecimal.format(facturaIf.getIva().multiply(BigDecimal.valueOf(-1D))));
					facturacionData.setIva(formatoDecimal.format(facturaIf.getIva().multiply(BigDecimal.valueOf(-1D))));
				}else {
					fila.add(formatoDecimal.format(facturaIf.getValor()));
					facturacionData.setSuma(formatoDecimal.format(facturaIf.getValor()));
					fila.add(formatoDecimal.format(descuentoTotal));
					facturacionData.setDescuento(formatoDecimal.format(descuentoTotal));
					fila.add(formatoDecimal.format(comisionAgencia));
					facturacionData.setComision(formatoDecimal.format(comisionAgencia));
					fila.add(formatoDecimal.format(subTotal));
					facturacionData.setSubtotal(formatoDecimal.format(subTotal));
					fila.add(formatoDecimal.format(facturaIf.getIva()));
					facturacionData.setIva(formatoDecimal.format(facturaIf.getIva()));
				}				
				total = subTotal.add(facturaIf.getIva());
			}
			
			if (tipoDocumento.getCodigo().equals("DEV"))
				total = total.multiply(BigDecimal.valueOf(-1D));
			
			fila.add(formatoDecimal.format(total));
			totalTotal = totalTotal.add(total);
			facturacionData.setTotal(formatoDecimal.format(total));
			
			//para calcular total de facturacion menos notas de credito por cliente
			if(mapaFacturacionMenosNotasCreditoCliente.get(cliente.getId().toString()) == null || mapaFacturacionMenosNotasCreditoCliente.get(cliente.getId().toString()).compareTo(0D) == 0){
				mapaFacturacionMenosNotasCreditoCliente.put(cliente.getId().toString(), total.doubleValue());
			}else{
				mapaFacturacionMenosNotasCreditoCliente.put(cliente.getId().toString(), mapaFacturacionMenosNotasCreditoCliente.get(cliente.getId().toString())+total.doubleValue());
			}
		}
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	 
}
