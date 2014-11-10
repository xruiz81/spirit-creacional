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

import org.apache.commons.collections.map.LinkedMap;

import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.gui.panel.JPFacturacionMes;
import com.spirit.facturacion.gui.reporteData.FacturacionClienteMesData;
import com.spirit.facturacion.gui.reporteData.NotaCreditoClientePorMesData;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.GeneralFinder;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class FacturacionMesModel extends JPFacturacionMes {
	
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private ClienteOficinaCriteria clienteOficinaCriteria;
	protected ClienteOficinaIf clienteOficinaIf;
	protected ClienteIf clienteIf;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String ESTADO_TODOS = "TODOS";
	private static final String ESTADO_ANULADO = "A";
	private static String NOMBRE_MENU_FACTURACION = "FACTURACION";
	private BigDecimal totalTotal = new BigDecimal(0);
	private List<FacturacionClienteMesData> facturacionColeccion = new ArrayList<FacturacionClienteMesData>();
	private Map<Long,TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long,TipoDocumentoIf>();
	private Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
	private Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
	private Map<ClienteIf,Map<String,BigDecimal>> mapaClienteMeses = new LinkedMap();
	String[] meses = new String[]{"ENE","FEB","MAR","ABR","MAY","JUN","JUL","AGO","SEP","OCT","NOV","DIC"};
	private static final String CON_IVA = "CON IVA";
	private static final String SIN_IVA = "SIN IVA";
	private static final String FACTURA_REEMBOLSO = "FAR";
	private String filtro = "";
	private static final String TIPO_CARTERA_CLIENTE = "C";
	private List<NotaCreditoClientePorMesData> notasCreditoClientePorMesDataColeccion = new ArrayList<NotaCreditoClientePorMesData>();
	private Map<ClienteIf,Map<String,BigDecimal>> mapaClienteNotasCreditoMeses = new LinkedMap();
	private static BigDecimal eneroNotasCredito = new BigDecimal(0);
	private static BigDecimal febreroNotasCredito = new BigDecimal(0);
	private static BigDecimal marzoNotasCredito = new BigDecimal(0);
	private static BigDecimal abrilNotasCredito = new BigDecimal(0);
	private static BigDecimal mayoNotasCredito = new BigDecimal(0);
	private static BigDecimal junioNotasCredito = new BigDecimal(0);
	private static BigDecimal julioNotasCredito = new BigDecimal(0);
	private static BigDecimal agostoNotasCredito = new BigDecimal(0);
	private static BigDecimal septiembreNotasCredito = new BigDecimal(0);
	private static BigDecimal octubreNotasCredito = new BigDecimal(0);
	private static BigDecimal noviembreNotasCredito = new BigDecimal(0);
	private static BigDecimal diciembreNotasCredito = new BigDecimal(0);
	private static BigDecimal eneroFacturacion = new BigDecimal(0);
	private static BigDecimal febreroFacturacion = new BigDecimal(0);
	private static BigDecimal marzoFacturacion = new BigDecimal(0);
	private static BigDecimal abrilFacturacion = new BigDecimal(0);
	private static BigDecimal mayoFacturacion = new BigDecimal(0);
	private static BigDecimal junioFacturacion = new BigDecimal(0);
	private static BigDecimal julioFacturacion = new BigDecimal(0);
	private static BigDecimal agostoFacturacion = new BigDecimal(0);
	private static BigDecimal septiembreFacturacion = new BigDecimal(0);
	private static BigDecimal octubreFacturacion = new BigDecimal(0);
	private static BigDecimal noviembreFacturacion = new BigDecimal(0);
	private static BigDecimal diciembreFacturacion = new BigDecimal(0);
	private static BigDecimal totalNotasCredito = new BigDecimal(0);
	
	
	public FacturacionMesModel(){
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
			Collection tiposDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumentoList();
			Iterator tiposDocumentoIt = tiposDocumento.iterator();
			while(tiposDocumentoIt.hasNext()){
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)tiposDocumentoIt.next();
				mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
			}
			
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList();
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while(clientesOficinaIt.hasNext()){
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf)clientesOficinaIt.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
			
			mapaCliente.clear();
			Collection clientes = SessionServiceLocator.getClienteSessionService().getClienteList();
			Iterator clientesIt = clientes.iterator();
			while(clientesIt.hasNext()){
				ClienteIf cliente = (ClienteIf)clientesIt.next();
				mapaCliente.put(cliente.getId(), cliente);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	public void initKeyListeners(){
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnConsultar().setToolTipText("Consultar");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblFacturacion().getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(8).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(9).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(10).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(11).setCellRenderer(tableCellRenderer);
		getTblFacturacion().getColumnModel().getColumn(12).setCellRenderer(tableCellRenderer);
	
		getTxtCliente().setEditable(false);
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
	}
	
	private void anchoColumnasTabla() {
		setSorterTable(getTblFacturacion());
		getTblFacturacion().getTableHeader().setReorderingAllowed(false);
		//getTblChequesEmitidos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblFacturacion().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblFacturacion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(265);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(11);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(12);
		anchoColumna.setPreferredWidth(90);
	}
	
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
				String tituloVentanaBusqueda = "Oficinas del Cliente";
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
		facturacionColeccion = new ArrayList<FacturacionClienteMesData>();
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
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
			if (tblModelReporte.getRowCount() > 0) {
				
				String fileName = "jaspers/facturacion/RPFacturacionPorMesConNotasCredito.jasper";
				
				String si = "Si";
				String no = "No";
				Object[] options = {si,no};
				String mensaje = "¿Desea el reporte sin cabeceras?";
				int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
				if(opcion == JOptionPane.YES_OPTION) {					
					fileName = "jaspers/facturacion/RPFacturacionPorMesConNotasCreditoEXCEL.jasper";
				}
				
				//Si la colección de facturas esta vacia porque escogi tipo documento Notas de Credito 
				//tengo que aumentarle por lo menos un elemento para que salga el reporte de Notas de Credito.
				if(facturacionColeccion.size() == 0){
					FacturacionClienteMesData facturacionClienteMesDataBlanco = new FacturacionClienteMesData();
					facturacionClienteMesDataBlanco.setEnero("0");
					facturacionClienteMesDataBlanco.setFebrero("0");
					facturacionClienteMesDataBlanco.setMarzo("0");
					facturacionClienteMesDataBlanco.setAbril("0");
					facturacionClienteMesDataBlanco.setMayo("0");
					facturacionClienteMesDataBlanco.setJunio("0");
					facturacionClienteMesDataBlanco.setJulio("0");
					facturacionClienteMesDataBlanco.setAgosto("0");
					facturacionClienteMesDataBlanco.setSeptiembre("0");
					facturacionClienteMesDataBlanco.setOctubre("0");
					facturacionClienteMesDataBlanco.setNoviembre("0");
					facturacionClienteMesDataBlanco.setDiciembre("0");
					facturacionClienteMesDataBlanco.setTotal("0");
					facturacionColeccion.add(facturacionClienteMesDataBlanco);
				}					
				
				HashMap parametrosMap = new HashMap();
				
				MenuIf menu = null;
				Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_FACTURACION).iterator();
				if(menuIT.hasNext()) menu = (MenuIf)menuIT.next();
				
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
				
				parametrosMap.put("eneroFacturacionNotasCredito", formatoDecimal.format(eneroFacturacion.subtract(eneroNotasCredito)));
				parametrosMap.put("febreroFacturacionNotasCredito", formatoDecimal.format(febreroFacturacion.subtract(febreroNotasCredito)));
				parametrosMap.put("marzoFacturacionNotasCredito", formatoDecimal.format(marzoFacturacion.subtract(marzoNotasCredito)));
				parametrosMap.put("abrilFacturacionNotasCredito", formatoDecimal.format(abrilFacturacion.subtract(abrilNotasCredito)));
				parametrosMap.put("mayoFacturacionNotasCredito", formatoDecimal.format(mayoFacturacion.subtract(mayoNotasCredito)));
				parametrosMap.put("junioFacturacionNotasCredito", formatoDecimal.format(junioFacturacion.subtract(junioNotasCredito)));
				parametrosMap.put("julioFacturacionNotasCredito", formatoDecimal.format(julioFacturacion.subtract(julioNotasCredito)));
				parametrosMap.put("agostoFacturacionNotasCredito", formatoDecimal.format(agostoFacturacion.subtract(agostoNotasCredito)));
				parametrosMap.put("septiembreFacturacionNotasCredito", formatoDecimal.format(septiembreFacturacion.subtract(septiembreNotasCredito)));
				parametrosMap.put("octubreFacturacionNotasCredito", formatoDecimal.format(octubreFacturacion.subtract(octubreNotasCredito)));
				parametrosMap.put("noviembreFacturacionNotasCredito", formatoDecimal.format(noviembreFacturacion.subtract(noviembreNotasCredito)));
				parametrosMap.put("diciembreFacturacionNotasCredito", formatoDecimal.format(diciembreFacturacion.subtract(diciembreNotasCredito)));
				
				JRDataSource dataSourceResumenNotasCredito = new JRBeanCollectionDataSource(notasCreditoClientePorMesDataColeccion);
				parametrosMap.put("dataSourceResumenNotasCredito", dataSourceResumenNotasCredito);
				
				if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) ){
					if(opcion == JOptionPane.YES_OPTION) {	
						parametrosMap.put("pathsubreportResumenNotasCredito", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPFacturacionPorMesConNotasCreditoDetalleEXCEL.jasper");
					}else{
						parametrosMap.put("pathsubreportResumenNotasCredito", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPFacturacionPorMesConNotasCreditoDetalle.jasper");						
					}
				}else{
					throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");					
				}
				
				ReportModelImpl.processReport(fileName, parametrosMap, facturacionColeccion, true);
				
			} else {
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
			}
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
		facturacionColeccion = new ArrayList<FacturacionClienteMesData>();
		notasCreditoClientePorMesDataColeccion.clear();
		totalTotal = new BigDecimal(0);
		
		eneroNotasCredito = new BigDecimal(0);
		febreroNotasCredito = new BigDecimal(0);
		marzoNotasCredito = new BigDecimal(0);
		abrilNotasCredito = new BigDecimal(0);
		mayoNotasCredito = new BigDecimal(0);
		junioNotasCredito = new BigDecimal(0);
		julioNotasCredito = new BigDecimal(0);
		agostoNotasCredito = new BigDecimal(0);
		septiembreNotasCredito = new BigDecimal(0);
		octubreNotasCredito = new BigDecimal(0);
		noviembreNotasCredito = new BigDecimal(0);
		diciembreNotasCredito = new BigDecimal(0);
		eneroFacturacion = new BigDecimal(0);
		febreroFacturacion = new BigDecimal(0);
		marzoFacturacion = new BigDecimal(0);
		abrilFacturacion = new BigDecimal(0);
		mayoFacturacion = new BigDecimal(0);
		junioFacturacion = new BigDecimal(0);
		julioFacturacion = new BigDecimal(0);
		agostoFacturacion = new BigDecimal(0);
		septiembreFacturacion = new BigDecimal(0);
		octubreFacturacion = new BigDecimal(0);
		noviembreFacturacion = new BigDecimal(0);
		diciembreFacturacion = new BigDecimal(0);
		totalNotasCredito = new BigDecimal(0);
	}
	
	public Collection generarColeccionFacturas(){
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
			
			if(filtro.contains("TODOS"))
				facturas = SessionServiceLocator.getFacturaSessionService().findFacturasByQueryByFechaInicioAndByFechaFinEmpresaId(facturasMap, new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()),Parametros.getIdEmpresa());
			else
				facturas = SessionServiceLocator.getFacturaSessionService().findFacturasByQueryByFechaInicioAndByFechaFin(facturasMap, new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()));
			
			
			generarMapaTotalClienteMes(facturas);
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}		
		return facturas;
	}
	
	
	public void generarMapaTotalClienteMes(Collection<FacturaIf> facturas){
		mapaClienteMeses = null;
		mapaClienteMeses = new LinkedMap();
		int mes = 0;
		
		for(FacturaIf facturaIf : facturas){
			if(!facturaIf.getEstado().equals(ESTADO_ANULADO)){
				ClienteOficinaIf clienteOficina = mapaClienteOficina.get(facturaIf.getClienteoficinaId());
				ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());							
				BigDecimal descuentoTotal = facturaIf.getDescuento().add(facturaIf.getDescuentosVarios()).add(facturaIf.getDescuentoGlobal());
				BigDecimal porcentajeComision = new BigDecimal(0);
				if(facturaIf.getPorcentajeComisionAgencia() != null){
					porcentajeComision = facturaIf.getPorcentajeComisionAgencia();
				}
				BigDecimal comisionAgencia = (facturaIf.getValor().subtract(descuentoTotal)).multiply(porcentajeComision.divide(new BigDecimal(100)));
				BigDecimal subTotal = new BigDecimal(0);
				BigDecimal total = new BigDecimal(0);				
				TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(facturaIf.getTipodocumentoId());
				if(getCmbIVA().getSelectedItem().equals(SIN_IVA)){
					if(tipoDocumento.getCodigo().equals(FACTURA_REEMBOLSO)){
						subTotal = facturaIf.getValor().divide(new BigDecimal(1).add(BigDecimal.valueOf(Parametros.IVA).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
						subTotal = subTotal.subtract(descuentoTotal).add(comisionAgencia);
					}else{
						subTotal = facturaIf.getValor().subtract(descuentoTotal).add(comisionAgencia);
					}
					total = subTotal;
				}else{
					subTotal = facturaIf.getValor().subtract(descuentoTotal).add(comisionAgencia);
					total = subTotal.add(facturaIf.getIva());
				}
				
				if (tipoDocumento.getCodigo().equals("DEV"))
					total = total.multiply(BigDecimal.valueOf(-1D));
				
				mes = facturaIf.getFechaFactura().getMonth();	
				
				Map<String,BigDecimal> mapaMesValor = new HashMap<String,BigDecimal>();	
				
				if((mapaClienteMeses.get(cliente) == null)){
					mapaMesValor.put(meses[mes], total);
					mapaClienteMeses.put(cliente,mapaMesValor);					
				} else {
					Iterator mapaMesValorIt = mapaClienteMeses.get(cliente).keySet().iterator();
					while(mapaMesValorIt.hasNext()){
						String key = (String)mapaMesValorIt.next();
						mapaMesValor.put(key,mapaClienteMeses.get(cliente).get(key));
					}
					if (mapaClienteMeses.get(cliente).get(meses[mes]) == null) {
						mapaMesValor.put(meses[mes], total);				
						mapaClienteMeses.put(cliente,mapaMesValor);					
					} else {
						mapaMesValor.put(meses[mes], total.add(mapaClienteMeses.get(cliente).get(meses[mes])));				
						mapaClienteMeses.put(cliente,mapaMesValor);					
					} 
				}
			}
		}
	}
	
	public void generarMapaTotalNotasCreditoClienteMes(Collection<NotaCreditoIf> notasCredito){
		mapaClienteNotasCreditoMeses = null;
		mapaClienteNotasCreditoMeses = new LinkedMap();
		int mes = 0;
		
		for(NotaCreditoIf notaCreditoIf : notasCredito){
			ClienteOficinaIf clienteOficina = mapaClienteOficina.get(notaCreditoIf.getOperadorNegocioId());
			ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());							
			BigDecimal subTotal = new BigDecimal(0);
			BigDecimal total = new BigDecimal(0);				
			if(getCmbIVA().getSelectedItem().equals(SIN_IVA)){
				subTotal = notaCreditoIf.getValor();
				total = subTotal;
			}else{
				subTotal = notaCreditoIf.getValor();
				total = subTotal.add(notaCreditoIf.getIva());
			}
						
			mes = notaCreditoIf.getFechaEmision().getMonth();	
			
			Map<String,BigDecimal> mapaMesValor = new HashMap<String,BigDecimal>();	
			
			if((mapaClienteNotasCreditoMeses.get(cliente) == null)){
				mapaMesValor.put(meses[mes], total);
				mapaClienteNotasCreditoMeses.put(cliente,mapaMesValor);					
			} else {
				Iterator mapaMesValorIt = mapaClienteNotasCreditoMeses.get(cliente).keySet().iterator();
				while(mapaMesValorIt.hasNext()){
					String key = (String)mapaMesValorIt.next();
					mapaMesValor.put(key,mapaClienteNotasCreditoMeses.get(cliente).get(key));
				}
				if (mapaClienteNotasCreditoMeses.get(cliente).get(meses[mes]) == null) {
					mapaMesValor.put(meses[mes], total);				
					mapaClienteNotasCreditoMeses.put(cliente,mapaMesValor);					
				} else {
					mapaMesValor.put(meses[mes], total.add(mapaClienteNotasCreditoMeses.get(cliente).get(meses[mes])));				
					mapaClienteNotasCreditoMeses.put(cliente,mapaMesValor);					
				} 
			}
		}
	}
	
	Comparator<FacturaIf> ordenadorFacturaPorPreimpreso = new Comparator<FacturaIf>(){
		public int compare(FacturaIf o1, FacturaIf o2) {
			if(o1.getPreimpresoNumero() == null){
				return -1;
			}else if(o2.getPreimpresoNumero() == null){
				return 1;
			}else{
				return o1.getPreimpresoNumero().compareTo(o2.getPreimpresoNumero());
			}
		}		
	};
	
	public Collection<NotaCreditoIf> generarColeccionNotasCreditoCliente(){
		Collection<NotaCreditoIf> notasCreditoCliente = null;
		
		try {
			Map notasCreditoMap = new HashMap();
			notasCreditoMap.put("tipoCartera", TIPO_CARTERA_CLIENTE);
			notasCreditoMap.put("estado", "A");
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
	
	Comparator<NotaCreditoIf> ordenadorNotaCreditoPorNombreCliente = new Comparator<NotaCreditoIf>(){
		public int compare(NotaCreditoIf o1, NotaCreditoIf o2) {
			ClienteOficinaIf clienteOficina1 = mapaClienteOficina.get(o1.getOperadorNegocioId());
			ClienteIf cliente1 = mapaCliente.get(clienteOficina1.getClienteId());
			ClienteOficinaIf clienteOficina2 = mapaClienteOficina.get(o2.getOperadorNegocioId());
			ClienteIf cliente2 = mapaCliente.get(clienteOficina2.getClienteId());
			
			return cliente1.getNombreLegal().compareTo(cliente2.getNombreLegal());
		}		
	};
	
	Comparator<FacturacionClienteMesData> ordenadorFacturasPorNombreCliente = new Comparator<FacturacionClienteMesData>(){
		public int compare(FacturacionClienteMesData o1, FacturacionClienteMesData o2) {
			return o1.getCliente().compareTo(o2.getCliente());
		}		
	};
	
	private void cargarTabla() {
		cleanTotales();
		generarColeccionFacturas();
		Iterator mapaClienteMesesIt = mapaClienteMeses.keySet().iterator();
		while(mapaClienteMesesIt.hasNext()){
			ClienteIf cliente = (ClienteIf)mapaClienteMesesIt.next();
			
			tableModel = (DefaultTableModel) getTblFacturacion().getModel();
			Vector<String> fila = new Vector<String>();
			FacturacionClienteMesData facturacionData = new FacturacionClienteMesData();
			
			agregarColumnasTabla(cliente, fila, facturacionData);
			
			tableModel.addRow(fila);
			facturacionColeccion.add(facturacionData);
		}
		
		//ordeno por nombre del cliente
		if(facturacionColeccion.size() > 0){
			Collections.sort((ArrayList)facturacionColeccion,ordenadorFacturasPorNombreCliente);
		}
		
		if(!getCbSinNotasCredito().isSelected()){
			//esta coleccion la lleno para presentarla en el reporte, no se ve en pantalla.				
			Collection<NotaCreditoIf> notasCreditoCliente = generarColeccionNotasCreditoCliente();
			
			//Ordeno las Notas de Credito por Nombre Legal del Cliente
			Collections.sort((ArrayList)notasCreditoCliente,ordenadorNotaCreditoPorNombreCliente);
			
			generarMapaTotalNotasCreditoClienteMes(notasCreditoCliente);
			Iterator mapaClienteNotasCreditoMesesIt = mapaClienteNotasCreditoMeses.keySet().iterator();
			while(mapaClienteNotasCreditoMesesIt.hasNext()){
				ClienteIf cliente = (ClienteIf)mapaClienteNotasCreditoMesesIt.next();
				
				BigDecimal totalCliente = new BigDecimal(0);
				
				NotaCreditoClientePorMesData notaCreditoClientePorMesData = new NotaCreditoClientePorMesData();
				notaCreditoClientePorMesData.setCliente(cliente.getNombreLegal());
				
				BigDecimal enero = mapaClienteNotasCreditoMeses.get(cliente).get(meses[0])!=null?mapaClienteNotasCreditoMeses.get(cliente).get(meses[0]):new BigDecimal(0);
				notaCreditoClientePorMesData.setEnero(formatoDecimal.format(enero));
				eneroNotasCredito = eneroNotasCredito.add(enero);
				totalCliente = totalCliente.add(enero);
				
				BigDecimal febrero = mapaClienteNotasCreditoMeses.get(cliente).get(meses[1])!=null?mapaClienteNotasCreditoMeses.get(cliente).get(meses[1]):new BigDecimal(0);
				notaCreditoClientePorMesData.setFebrero(formatoDecimal.format(febrero));
				febreroNotasCredito = febreroNotasCredito.add(febrero);
				totalCliente = totalCliente.add(febrero);
				
				BigDecimal marzo = mapaClienteNotasCreditoMeses.get(cliente).get(meses[2])!=null?mapaClienteNotasCreditoMeses.get(cliente).get(meses[2]):new BigDecimal(0);
				notaCreditoClientePorMesData.setMarzo(formatoDecimal.format(marzo));
				marzoNotasCredito = marzoNotasCredito.add(marzo);
				totalCliente = totalCliente.add(marzo);
				
				BigDecimal abril = mapaClienteNotasCreditoMeses.get(cliente).get(meses[3])!=null?mapaClienteNotasCreditoMeses.get(cliente).get(meses[3]):new BigDecimal(0);
				notaCreditoClientePorMesData.setAbril(formatoDecimal.format(abril));
				abrilNotasCredito = abrilNotasCredito.add(abril);
				totalCliente = totalCliente.add(abril);
				
				BigDecimal mayo = mapaClienteNotasCreditoMeses.get(cliente).get(meses[4])!=null?mapaClienteNotasCreditoMeses.get(cliente).get(meses[4]):new BigDecimal(0);
				notaCreditoClientePorMesData.setMayo(formatoDecimal.format(mayo));
				mayoNotasCredito = mayoNotasCredito.add(mayo);
				totalCliente = totalCliente.add(mayo);
				
				BigDecimal junio = mapaClienteNotasCreditoMeses.get(cliente).get(meses[5])!=null?mapaClienteNotasCreditoMeses.get(cliente).get(meses[5]):new BigDecimal(0);
				notaCreditoClientePorMesData.setJunio(formatoDecimal.format(junio));
				junioNotasCredito = junioNotasCredito.add(junio);
				totalCliente = totalCliente.add(junio);
				
				BigDecimal julio = mapaClienteNotasCreditoMeses.get(cliente).get(meses[6])!=null?mapaClienteNotasCreditoMeses.get(cliente).get(meses[6]):new BigDecimal(0);
				notaCreditoClientePorMesData.setJulio(formatoDecimal.format(julio));
				julioNotasCredito = julioNotasCredito.add(julio);
				totalCliente = totalCliente.add(julio);
				
				BigDecimal agosto = mapaClienteNotasCreditoMeses.get(cliente).get(meses[7])!=null?mapaClienteNotasCreditoMeses.get(cliente).get(meses[7]):new BigDecimal(0);
				notaCreditoClientePorMesData.setAgosto(formatoDecimal.format(agosto));
				agostoNotasCredito = agostoNotasCredito.add(agosto);
				totalCliente = totalCliente.add(agosto);
				
				BigDecimal septiembre = mapaClienteNotasCreditoMeses.get(cliente).get(meses[8])!=null?mapaClienteNotasCreditoMeses.get(cliente).get(meses[8]):new BigDecimal(0);
				notaCreditoClientePorMesData.setSeptiembre(formatoDecimal.format(septiembre));
				septiembreNotasCredito = septiembreNotasCredito.add(septiembre);
				totalCliente = totalCliente.add(septiembre);
				
				BigDecimal octubre = mapaClienteNotasCreditoMeses.get(cliente).get(meses[9])!=null?mapaClienteNotasCreditoMeses.get(cliente).get(meses[9]):new BigDecimal(0);
				notaCreditoClientePorMesData.setOctubre(formatoDecimal.format(octubre));
				octubreNotasCredito = octubreNotasCredito.add(octubre);
				totalCliente = totalCliente.add(octubre);
				
				BigDecimal noviembre = mapaClienteNotasCreditoMeses.get(cliente).get(meses[10])!=null?mapaClienteNotasCreditoMeses.get(cliente).get(meses[10]):new BigDecimal(0);
				notaCreditoClientePorMesData.setNoviembre(formatoDecimal.format(noviembre));
				noviembreNotasCredito = noviembreNotasCredito.add(noviembre);
				totalCliente = totalCliente.add(noviembre);
				
				BigDecimal diciembre = mapaClienteNotasCreditoMeses.get(cliente).get(meses[11])!=null?mapaClienteNotasCreditoMeses.get(cliente).get(meses[11]):new BigDecimal(0);
				notaCreditoClientePorMesData.setDiciembre(formatoDecimal.format(diciembre));
				diciembreNotasCredito = diciembreNotasCredito.add(diciembre);
				totalCliente = totalCliente.add(diciembre);
				
				notaCreditoClientePorMesData.setTotal(formatoDecimal.format(totalCliente));
				totalNotasCredito = totalNotasCredito.add(totalCliente);
				
				notasCreditoClientePorMesDataColeccion.add(notaCreditoClientePorMesData);
			}
		}		
	}

	private void agregarColumnasTabla(ClienteIf cliente, Vector<String> fila, FacturacionClienteMesData facturacionData) {
		BigDecimal total = new BigDecimal(0);
		
		fila.add(cliente.getNombreLegal());
		facturacionData.setCliente(cliente.getNombreLegal());
		
		BigDecimal enero = mapaClienteMeses.get(cliente).get(meses[0])!=null?mapaClienteMeses.get(cliente).get(meses[0]):new BigDecimal(0);
		fila.add(formatoDecimal.format(enero));
		facturacionData.setEnero(formatoDecimal.format(enero));
		eneroFacturacion = eneroFacturacion.add(enero);
		total = total.add(enero);
		
		BigDecimal febrero = mapaClienteMeses.get(cliente).get(meses[1])!=null?mapaClienteMeses.get(cliente).get(meses[1]):new BigDecimal(0);
		fila.add(formatoDecimal.format(febrero));
		facturacionData.setFebrero(formatoDecimal.format(febrero));
		febreroFacturacion = febreroFacturacion.add(febrero);
		total = total.add(febrero);
		
		BigDecimal marzo = mapaClienteMeses.get(cliente).get(meses[2])!=null?mapaClienteMeses.get(cliente).get(meses[2]):new BigDecimal(0);
		fila.add(formatoDecimal.format(marzo));
		facturacionData.setMarzo(formatoDecimal.format(marzo));
		marzoFacturacion = marzoFacturacion.add(marzo);
		total = total.add(marzo);
		
		BigDecimal abril = mapaClienteMeses.get(cliente).get(meses[3])!=null?mapaClienteMeses.get(cliente).get(meses[3]):new BigDecimal(0);
		fila.add(formatoDecimal.format(abril));
		facturacionData.setAbril(formatoDecimal.format(abril));
		abrilFacturacion = abrilFacturacion.add(abril);
		total = total.add(abril);
		
		BigDecimal mayo = mapaClienteMeses.get(cliente).get(meses[4])!=null?mapaClienteMeses.get(cliente).get(meses[4]):new BigDecimal(0);
		fila.add(formatoDecimal.format(mayo));
		facturacionData.setMayo(formatoDecimal.format(mayo));
		mayoFacturacion = mayoFacturacion.add(mayo);
		total = total.add(mayo);
		
		BigDecimal junio = mapaClienteMeses.get(cliente).get(meses[5])!=null?mapaClienteMeses.get(cliente).get(meses[5]):new BigDecimal(0);
		fila.add(formatoDecimal.format(junio));
		facturacionData.setJunio(formatoDecimal.format(junio));
		junioFacturacion = junioFacturacion.add(junio);
		total = total.add(junio);
		
		BigDecimal julio = mapaClienteMeses.get(cliente).get(meses[6])!=null?mapaClienteMeses.get(cliente).get(meses[6]):new BigDecimal(0);
		fila.add(formatoDecimal.format(julio));
		facturacionData.setJulio(formatoDecimal.format(julio));
		julioFacturacion = julioFacturacion.add(julio);
		total = total.add(julio);
		
		BigDecimal agosto = mapaClienteMeses.get(cliente).get(meses[7])!=null?mapaClienteMeses.get(cliente).get(meses[7]):new BigDecimal(0);
		fila.add(formatoDecimal.format(agosto));
		facturacionData.setAgosto(formatoDecimal.format(agosto));
		agostoFacturacion = agostoFacturacion.add(agosto);
		total = total.add(agosto);
		
		BigDecimal septiembre = mapaClienteMeses.get(cliente).get(meses[8])!=null?mapaClienteMeses.get(cliente).get(meses[8]):new BigDecimal(0);
		fila.add(formatoDecimal.format(septiembre));
		facturacionData.setSeptiembre(formatoDecimal.format(septiembre));
		septiembreFacturacion = septiembreFacturacion.add(septiembre);
		total = total.add(septiembre);
		
		BigDecimal octubre = mapaClienteMeses.get(cliente).get(meses[9])!=null?mapaClienteMeses.get(cliente).get(meses[9]):new BigDecimal(0);
		fila.add(formatoDecimal.format(octubre));
		facturacionData.setOctubre(formatoDecimal.format(octubre));
		octubreFacturacion = octubreFacturacion.add(octubre);
		total = total.add(octubre);
		
		BigDecimal noviembre = mapaClienteMeses.get(cliente).get(meses[10])!=null?mapaClienteMeses.get(cliente).get(meses[10]):new BigDecimal(0);
		fila.add(formatoDecimal.format(noviembre));
		facturacionData.setNoviembre(formatoDecimal.format(noviembre));
		noviembreFacturacion = noviembreFacturacion.add(noviembre);
		total = total.add(noviembre);
		
		BigDecimal diciembre = mapaClienteMeses.get(cliente).get(meses[11])!=null?mapaClienteMeses.get(cliente).get(meses[11]):new BigDecimal(0);
		fila.add(formatoDecimal.format(diciembre));
		facturacionData.setDiciembre(formatoDecimal.format(diciembre));
		diciembreFacturacion = diciembreFacturacion.add(diciembre);
		total = total.add(diciembre);
		
		facturacionData.setTotal(formatoDecimal.format(total));
		
		totalTotal = totalTotal.add(total);
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
