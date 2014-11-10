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

import com.spirit.cartera.entity.NotaCreditoDetalleIf;
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
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.gui.panel.JPFacturacionTipoProducto;
import com.spirit.facturacion.gui.reporteData.FacturacionTipoProductoData;
import com.spirit.facturacion.gui.reporteData.NotaCreditoClienteTipoProductoData;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.GeneralFinder;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class FacturacionTipoProductoModel extends JPFacturacionTipoProducto {
	
	private static final String CODIGO_TELEVISION = "PTV";
	private static final String CODIGO_PRENSA = "AVP";
	private static final String CODIGO_RADIO = "PRA";
	private static final String CODIGO_REVISTA = "AVR";
	private static final String CODIGO_PRODUCCION_INTERNA = "PRI";
	private static final String CODIGO_PRODUCCION_EXTERNA = "PRE";
	private static final String CODIGO_VALLAS = "VAL";	
	private static final String CODIGO_FEE = "FEE";	
	private static final String CODIGO_COMISION_DIRECTA = "COD";
	private static final String CODIGO_GASTOS_VARIOS = "GAV";
	private static final String CODIGO_OTROS = "OTROS";	
	private static final String ESTADO_TODOS = "TODOS";
	private static final String CON_IVA = "CON IVA";
	private static final String SIN_IVA = "SIN IVA";
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static String NOMBRE_MENU_FACTURACION = "FACTURACION";
	private Vector<FacturacionTipoProductoData> facturacionColeccion = new Vector<FacturacionTipoProductoData>();
	private Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
	private Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
	private Map<Long,ProductoIf> mapaProducto = new HashMap<Long,ProductoIf>();
	private Map<Long,GenericoIf> mapaGenerico = new HashMap<Long,GenericoIf>();
	private Map<Long,TipoProductoIf> mapaTipoProducto = new HashMap<Long,TipoProductoIf>();
	protected ClienteOficinaIf clienteOficinaIf;
	protected ClienteIf clienteIf;
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private BigDecimal totalTotal = new BigDecimal(0);
	private LinkedMap mapaClienteOficinaTipoProducto = new LinkedMap();
	private BigDecimal valor = new BigDecimal(0);
	private BigDecimal descuentoTotal = new BigDecimal(0);
	private BigDecimal porcentajeComision = new BigDecimal(0);
	private BigDecimal comisionAgencia = new BigDecimal(0);
	private BigDecimal subTotal = new BigDecimal(0);
	private BigDecimal iva = new BigDecimal(0);
	private BigDecimal total = new BigDecimal(0);
	private String filtro = "";
	private Map facturasMap = new HashMap();
	private Map<Long,DocumentoIf> mapaDocumento = new HashMap<Long,DocumentoIf>();
	private static final String FACTURA_REEMBOLSO = "FACR";
	private List<NotaCreditoClienteTipoProductoData> notasCreditoClienteTipoProductoDataColeccion = new ArrayList<NotaCreditoClienteTipoProductoData>();
	private static final String CODIGO_TIPO_DOCUMENTO_NOTA_CREDITO_CLIENTE = "NCC";
	private static final String TIPO_CARTERA_CLIENTE = "C";
	private static final String DOCUMENTO_NC_FACTURA_REEMBOLSO = "NCFR";
	private static BigDecimal totalNotasCredito = new BigDecimal(0);
	private LinkedMap mapaNotaCreditoClienteOficinaTipoProducto = new LinkedMap();
	private static BigDecimal televisionNotasCredito = new BigDecimal(0);
	private static BigDecimal prensaNotasCredito = new BigDecimal(0);
	private static BigDecimal radioNotasCredito = new BigDecimal(0);
	private static BigDecimal revistaNotasCredito = new BigDecimal(0);
	private static BigDecimal pInternaNotasCredito = new BigDecimal(0);
	private static BigDecimal pExternaNotasCredito = new BigDecimal(0);
	private static BigDecimal vallasNotasCredito = new BigDecimal(0);
	private static BigDecimal feeNotasCredito = new BigDecimal(0);
	private static BigDecimal comisionDirectaNotasCredito = new BigDecimal(0);
	private static BigDecimal otrosNotasCredito = new BigDecimal(0);
	private static BigDecimal televisionFacturacion = new BigDecimal(0);
	private static BigDecimal prensaFacturacion = new BigDecimal(0);
	private static BigDecimal radioFacturacion = new BigDecimal(0);
	private static BigDecimal revistaFacturacion = new BigDecimal(0);
	private static BigDecimal pInternaFacturacion = new BigDecimal(0);
	private static BigDecimal pExternaFacturacion = new BigDecimal(0);
	private static BigDecimal vallasFacturacion = new BigDecimal(0);
	private static BigDecimal feeFacturacion = new BigDecimal(0);
	private static BigDecimal comisionDirectaFacturacion = new BigDecimal(0);
	private static BigDecimal otrosFacturacion = new BigDecimal(0);
	private static final String TIPO_REFERENCIA_PRESUPUESTO = "P";
	private static final String PRESUPUESTO_DETALLE_REPORTE_SI = "S";
	private static final String CODIGO_TIPO_DOCUMENTO_FACTURA_COMISION = "FCO";
	private Map<Long,EmpleadoIf> mapaEmpleado = new HashMap<Long,EmpleadoIf>();
	private Map<Long,TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long,TipoDocumentoIf>();
	
	
	public FacturacionTipoProductoModel(){
		cargarMapas();
		cargarComboTipoDocumento();
		cargarComboComisionOficina();
		initKeyListeners();
		anchoColumnasTabla();
		showSaveMode();
		initListeners();
	}
	
	public void resetearValores(){
		valor = new BigDecimal(0);
		descuentoTotal = new BigDecimal(0);
		porcentajeComision = new BigDecimal(0);
		comisionAgencia = new BigDecimal(0);
		subTotal = new BigDecimal(0);
		iva = new BigDecimal(0);
		total = new BigDecimal(0);
	}
	
	private void initListeners() {
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cleanTable();
				//long start=System.currentTimeMillis();
				cargarTabla();
				//long fin=System.currentTimeMillis();
				//System.out.println("---------------------inicializa Mainframe: "+(fin-start)/1000+" seg");
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
	
	private void cargarComboComisionOficina(){
		try {
			List oficinas = (List)SessionServiceLocator.getOficinaSessionService().getOficinaList();
			oficinas.add(ESTADO_TODOS);
			refreshCombo(getCmbOficina(),oficinas);
			getCmbOficina().setSelectedItem(ESTADO_TODOS);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarMapas(){
		try {
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
			
			mapaProducto.clear();
			Collection productos = SessionServiceLocator.getProductoSessionService().getProductoList();
			Iterator productosIt = productos.iterator();
			while(productosIt.hasNext()){
				ProductoIf producto = (ProductoIf)productosIt.next();
				mapaProducto.put(producto.getId(), producto);
			}
			
			mapaGenerico.clear();
			Collection genericos = SessionServiceLocator.getGenericoSessionService().getGenericoList();
			Iterator genericosIt = genericos.iterator();
			while(genericosIt.hasNext()){
				GenericoIf generico = (GenericoIf)genericosIt.next();
				mapaGenerico.put(generico.getId(), generico);
			}
			
			mapaTipoProducto.clear();
			Collection tiposProducto = SessionServiceLocator.getTipoProductoSessionService().getTipoProductoList();
			Iterator tiposProductoIt = tiposProducto.iterator();
			while(tiposProductoIt.hasNext()){
				TipoProductoIf tipoProducto = (TipoProductoIf)tiposProductoIt.next();
				mapaTipoProducto.put(tipoProducto.getId(), tipoProducto);
			}
			
			mapaDocumento.clear();
			Collection documentos = SessionServiceLocator.getDocumentoSessionService().getDocumentoList();
			Iterator documentosIt = documentos.iterator();
			while(documentosIt.hasNext()){
				DocumentoIf documento = (DocumentoIf)documentosIt.next();
				mapaDocumento.put(documento.getId(), documento);
			}
			
			mapaEmpleado.clear();
			Collection empleados = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Iterator empleadosIt = empleados.iterator();
			while(empleadosIt.hasNext()){
				EmpleadoIf empleado = (EmpleadoIf)empleadosIt.next();
				mapaEmpleado.put(empleado.getId(), empleado);
			}
			
			mapaTipoDocumento.clear();
			Collection tiposDocumento = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator tiposDocumentoIt = tiposDocumento.iterator();
			while(tiposDocumentoIt.hasNext()){
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)tiposDocumentoIt.next();
				mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
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
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(100);
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
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(11);
		anchoColumna.setPreferredWidth(100);
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
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
		facturacionColeccion = new Vector<FacturacionTipoProductoData>();
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		getCbComisiones().setSelected(false);
		getCmbIVA().setSelectedItem(SIN_IVA);
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
			if (tblModelReporte.getRowCount() > 0 || notasCreditoClienteTipoProductoDataColeccion.size() > 0) {
				
				String fileName = "jaspers/facturacion/RPFacturacionPorTipoProductoConNotasCredito.jasper";
								
				String si = "Si";
				String no = "No";
				Object[] options = {si,no};
				String mensaje = "¿Desea el reporte sin cabeceras para exportar a Excel?";
				int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if(opcion == JOptionPane.YES_OPTION) {
					fileName = "jaspers/facturacion/RPFacturacionPorTipoProductoConNotasCreditoExcel.jasper";
				}
				
				//Si la colección de facturas esta vacia porque escogi tipo documento Notas de Credito 
				//tengo que aumentarle por lo menos un elemento para que salga el reporte de Notas de Credito.
				if(facturacionColeccion.size() == 0){
					FacturacionTipoProductoData facturacionDataBlanco = new FacturacionTipoProductoData();
					facturacionDataBlanco.setTelevision("0");
					facturacionDataBlanco.setPrensa("0");
					facturacionDataBlanco.setRadio("0");
					facturacionDataBlanco.setRevista("0");
					facturacionDataBlanco.setPinterna("0");
					facturacionDataBlanco.setPexterna("0");
					facturacionDataBlanco.setVallas("0");
					facturacionDataBlanco.setFee("0");
					facturacionDataBlanco.setComisionDirecta("0");
					facturacionDataBlanco.setOtros("0");
					facturacionDataBlanco.setTotal("0");
					facturacionColeccion.add(facturacionDataBlanco);
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
				
				parametrosMap.put("televisionFacturacionNotasCredito", formatoDecimal.format(televisionFacturacion.subtract(televisionNotasCredito)));
				parametrosMap.put("prensaFacturacionNotasCredito", formatoDecimal.format(prensaFacturacion.subtract(prensaNotasCredito)));
				parametrosMap.put("radioFacturacionNotasCredito", formatoDecimal.format(radioFacturacion.subtract(radioNotasCredito)));
				parametrosMap.put("revistaFacturacionNotasCredito", formatoDecimal.format(revistaFacturacion.subtract(revistaNotasCredito)));
				parametrosMap.put("pInternaFacturacionNotasCredito", formatoDecimal.format(pInternaFacturacion.subtract(pInternaNotasCredito)));
				parametrosMap.put("pExternaFacturacionNotasCredito", formatoDecimal.format(pExternaFacturacion.subtract(pExternaNotasCredito)));
				parametrosMap.put("vallasFacturacionNotasCredito", formatoDecimal.format(vallasFacturacion.subtract(vallasNotasCredito)));
				parametrosMap.put("feeFacturacionNotasCredito", formatoDecimal.format(feeFacturacion.subtract(feeNotasCredito)));
				parametrosMap.put("comisionDirectaFacturacionNotasCredito", formatoDecimal.format(comisionDirectaFacturacion.subtract(comisionDirectaNotasCredito)));
				parametrosMap.put("otrosFacturacionNotasCredito", formatoDecimal.format(otrosFacturacion.subtract(otrosNotasCredito)));
									
				JRDataSource dataSourceResumenNotasCredito = new JRBeanCollectionDataSource(notasCreditoClienteTipoProductoDataColeccion);
				parametrosMap.put("dataSourceResumenNotasCredito", dataSourceResumenNotasCredito);
				
				if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) ){
					parametrosMap.put("pathsubreportResumenNotasCredito", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPFacturacionPorTipoProductoConNotasCreditoDetalle.jasper");						
				}else{
					throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");					
				}
				
				ReportModelImpl.processReport(fileName, parametrosMap, facturacionColeccion, true);
				
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
		facturacionColeccion = new Vector<FacturacionTipoProductoData>();
		notasCreditoClienteTipoProductoDataColeccion.clear();
		totalTotal = new BigDecimal(0);
		totalNotasCredito = new BigDecimal(0);
		televisionNotasCredito = new BigDecimal(0);
		prensaNotasCredito = new BigDecimal(0);
		radioNotasCredito = new BigDecimal(0);
		revistaNotasCredito = new BigDecimal(0);
		pInternaNotasCredito = new BigDecimal(0);
		pExternaNotasCredito = new BigDecimal(0);
		vallasNotasCredito = new BigDecimal(0);
		feeNotasCredito = new BigDecimal(0);
		comisionDirectaNotasCredito = new BigDecimal(0);
		otrosNotasCredito = new BigDecimal(0);
		televisionFacturacion = new BigDecimal(0);
		prensaFacturacion = new BigDecimal(0);
		radioFacturacion = new BigDecimal(0);
		revistaFacturacion = new BigDecimal(0);
		pInternaFacturacion = new BigDecimal(0);
		pExternaFacturacion = new BigDecimal(0);
		vallasFacturacion = new BigDecimal(0);
		feeFacturacion = new BigDecimal(0);
		comisionDirectaFacturacion = new BigDecimal(0);
		otrosFacturacion = new BigDecimal(0);
	}
	
	public void generarColeccionFacturas(){
		try {
			filtro = "TODOS";
			facturasMap = null;
			facturasMap = new HashMap();
			if(!getCmbTipoDocumento().getSelectedItem().equals(ESTADO_TODOS)){
				facturasMap.put("tipodocumentoId", ((TipoDocumentoIf)getCmbTipoDocumento().getSelectedItem()).getId());
				filtro = ((TipoDocumentoIf)getCmbTipoDocumento().getSelectedItem()).getNombre();
			}
			if(getCmbIVA().getSelectedItem().equals(CON_IVA)){
				filtro = filtro + " " + CON_IVA;
			}else{
				filtro = filtro + " " + SIN_IVA;
			}
			if(clienteOficinaIf != null){
				facturasMap.put("clienteoficinaId", clienteOficinaIf.getId());
			}
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
			
			boolean usarNombreComercial = false;
			if(getCbUsarNombreComercial().isSelected()){
				usarNombreComercial = true;
			}
			
			ArrayList facturas = null;
					
			if(filtro.contains("TODOS"))
				facturas = (ArrayList)SessionServiceLocator.getFacturaSessionService().findFacturasAndFacturasDetalleByQueryByFechaInicioAndByFechaFinEmpresaId(facturasMap, new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()),Parametros.getIdEmpresa(), usarNombreComercial);        
			else
				facturas = (ArrayList)SessionServiceLocator.getFacturaSessionService().findFacturasAndFacturasDetalleByQueryByFechaInicioAndByFechaFin(facturasMap, new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()), usarNombreComercial);
			
			generarMapaClienteTipoProducto(facturas);
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void generarMapaClienteTipoProducto(ArrayList facturas){
		LinkedMap mapaFacturas = new LinkedMap();
		Vector<FacturaDetalleIf> facturaDetalleVector = new Vector<FacturaDetalleIf>();
		Iterator facturasIt = facturas.iterator();
		while (facturasIt.hasNext()) {
			Object[] facturasObject = (Object[]) facturasIt.next();
			FacturaIf facturaIf = (FacturaIf) facturasObject[0];
			FacturaDetalleIf facturaDetalleIf = (FacturaDetalleIf) facturasObject[1];
			mapaFacturas.put(facturaIf.getId(),facturaIf);
			facturaDetalleVector.add(facturaDetalleIf);
		}
		
		if(getCbComisiones().isSelected()){
			generarMapaComisionClienteOficinaTipoProducto(mapaFacturas, facturaDetalleVector);
			
			//PARA LA CABECERA DEL REPORTE
			if(getCmbOficina().getSelectedItem() != null && !getCmbOficina().getSelectedItem().equals(ESTADO_TODOS)){
				OficinaIf oficina = (OficinaIf) getCmbOficina().getSelectedItem();
				filtro = "COMISIONES, OFICINA " + oficina.getNombre();				
			}else{
				filtro = "COMISIONES";
			}
		}else{
			generarMapaClienteOficinaTipoProducto(mapaFacturas, facturaDetalleVector);
		}
	}

	private void generarMapaClienteOficinaTipoProducto(LinkedMap mapaFacturas, Vector<FacturaDetalleIf> facturaDetalleVector) {
		ProductoIf producto = null;
		GenericoIf generico = null;
		TipoProductoIf tipoProducto = null;
		mapaClienteOficinaTipoProducto = null;
		mapaClienteOficinaTipoProducto = new LinkedMap();
		BigDecimal suma = new BigDecimal(0);
		BigDecimal sumaFactura = new BigDecimal(0);
		Iterator mapaFacturasIt = mapaFacturas.keySet().iterator();
		while (mapaFacturasIt.hasNext()) {
			Long facturaId = (Long)mapaFacturasIt.next();
			FacturaIf factura = (FacturaIf)mapaFacturas.get(facturaId);
			ClienteOficinaIf clienteOficina = mapaClienteOficina.get(factura.getClienteoficinaId());
			
			Map<String,BigDecimal> mapaTipoProductoSuma = null;
			if(mapaClienteOficinaTipoProducto.get(clienteOficina) == null){
				mapaTipoProductoSuma = new HashMap<String,BigDecimal>();
				mapaTipoProductoSuma.put(CODIGO_TELEVISION, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_PRENSA, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_RADIO, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_REVISTA, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_PRODUCCION_INTERNA, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_PRODUCCION_EXTERNA, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_VALLAS, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_FEE, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_COMISION_DIRECTA, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_OTROS, new BigDecimal(0));
			}else{
				mapaTipoProductoSuma = (Map<String,BigDecimal>)mapaClienteOficinaTipoProducto.get(clienteOficina);
			}
			
			if(getCmbOficina().getSelectedItem() != null && !getCmbOficina().getSelectedItem().equals(ESTADO_TODOS)){
				OficinaIf oficina = (OficinaIf) getCmbOficina().getSelectedItem();
				EmpleadoIf vendedor = mapaEmpleado.get(factura.getVendedorId());
				
				if(vendedor.getOficinaId().compareTo(oficina.getId()) == 0){
					suma = calcularMapaClienteTipoProducto(facturaDetalleVector, suma,
							facturaId, factura, clienteOficina, mapaTipoProductoSuma);
				}
			}else{
				suma = calcularMapaClienteTipoProducto(facturaDetalleVector, suma,
						facturaId, factura, clienteOficina, mapaTipoProductoSuma);
			}			
		}
	}

	private BigDecimal calcularMapaClienteTipoProducto(
			Vector<FacturaDetalleIf> facturaDetalleVector, BigDecimal suma,
			Long facturaId, FacturaIf factura, ClienteOficinaIf clienteOficina,
			Map<String, BigDecimal> mapaTipoProductoSuma) {
		ProductoIf producto;
		GenericoIf generico;
		TipoProductoIf tipoProducto;
		BigDecimal sumaFactura;
		sumaFactura = new BigDecimal(0);
		for(int i=0; i<facturaDetalleVector.size(); i++){
			FacturaDetalleIf facturaDetalle = facturaDetalleVector.get(i);
			
			if(facturaDetalle.getFacturaId().compareTo(facturaId) == 0){
				resetearValores();
				valor = facturaDetalle.getValor();
				double porcentajeDescuentosVarios = facturaDetalle.getPorcentajeDescuentosVarios().doubleValue() / 100;
				double descuentosVarios = facturaDetalle.getValor().doubleValue() * porcentajeDescuentosVarios;
				descuentoTotal = facturaDetalle.getDescuento().add(BigDecimal.valueOf(descuentosVarios)).add(facturaDetalle.getDescuentoGlobal());
				porcentajeComision = new BigDecimal(0);
				
				if(factura.getPorcentajeComisionAgencia() != null){
					porcentajeComision = factura.getPorcentajeComisionAgencia();
				}
				
				comisionAgencia = (valor.subtract(descuentoTotal)).multiply(porcentajeComision.divide(new BigDecimal(100)));
								
				if(getCmbIVA().getSelectedItem().equals(SIN_IVA)){
					DocumentoIf documento = mapaDocumento.get(facturaDetalle.getDocumentoId());
					if(documento.getCodigo().equals(FACTURA_REEMBOLSO)){
						subTotal = valor.divide(new BigDecimal(1).add(BigDecimal.valueOf(Parametros.IVA).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
						subTotal = subTotal.subtract(descuentoTotal).add(comisionAgencia);
					}else{
						subTotal = valor.subtract(descuentoTotal).add(comisionAgencia);
					}
					total = subTotal;
				}else{
					subTotal = valor.subtract(descuentoTotal).add(comisionAgencia);
					iva = facturaDetalle.getIva();
					total = subTotal.add(iva);
				}
				
				suma = suma.add(total);
				sumaFactura = sumaFactura.add(total);
				producto = mapaProducto.get(facturaDetalle.getProductoId());
				generico = mapaGenerico.get(producto.getGenericoId());
				tipoProducto = mapaTipoProducto.get(generico.getTipoproductoId());
				if(tipoProducto.getCodigo().equals(CODIGO_TELEVISION)){
					mapaTipoProductoSuma.put(CODIGO_TELEVISION, mapaTipoProductoSuma.get(CODIGO_TELEVISION).add(total));
				}else if(tipoProducto.getCodigo().equals(CODIGO_PRENSA)){
					mapaTipoProductoSuma.put(CODIGO_PRENSA, mapaTipoProductoSuma.get(CODIGO_PRENSA).add(total));
				}else if(tipoProducto.getCodigo().equals(CODIGO_RADIO)){
					mapaTipoProductoSuma.put(CODIGO_RADIO, mapaTipoProductoSuma.get(CODIGO_RADIO).add(total));
				}else if(tipoProducto.getCodigo().equals(CODIGO_REVISTA)){
					mapaTipoProductoSuma.put(CODIGO_REVISTA, mapaTipoProductoSuma.get(CODIGO_REVISTA).add(total));
				}else if(tipoProducto.getCodigo().equals(CODIGO_PRODUCCION_INTERNA)){
					mapaTipoProductoSuma.put(CODIGO_PRODUCCION_INTERNA, mapaTipoProductoSuma.get(CODIGO_PRODUCCION_INTERNA).add(total));
				}else if(tipoProducto.getCodigo().equals(CODIGO_PRODUCCION_EXTERNA)){
					mapaTipoProductoSuma.put(CODIGO_PRODUCCION_EXTERNA, mapaTipoProductoSuma.get(CODIGO_PRODUCCION_EXTERNA).add(total));
				}else if(tipoProducto.getCodigo().equals(CODIGO_VALLAS)){
					mapaTipoProductoSuma.put(CODIGO_VALLAS, mapaTipoProductoSuma.get(CODIGO_VALLAS).add(total));
				}else if(tipoProducto.getCodigo().equals(CODIGO_FEE)){
					mapaTipoProductoSuma.put(CODIGO_FEE, mapaTipoProductoSuma.get(CODIGO_FEE).add(total));
				}else if(tipoProducto.getCodigo().equals(CODIGO_COMISION_DIRECTA)){
					mapaTipoProductoSuma.put(CODIGO_COMISION_DIRECTA, mapaTipoProductoSuma.get(CODIGO_COMISION_DIRECTA).add(total));
				}else if(!tipoProducto.getCodigo().equals(CODIGO_GASTOS_VARIOS)){
					mapaTipoProductoSuma.put(CODIGO_OTROS, mapaTipoProductoSuma.get(CODIGO_OTROS).add(total));
				}else{
					//try {
						System.out.println("GENERICO de GASTOS VARIOS cuyo valor no se presenta:");
						
						//Para cambiar el producto de Factura_detalle, Pedido_detalle y Presupuesto_detalle
						//en este caso el producto_id 4965 es produccion externa.
						/*PedidoIf pedido = SessionServiceLocator.getPedidoSessionService().getPedido(factura.getPedidoId());
						if(pedido.getTiporeferencia().equals("P")){
							PresupuestoIf presupuesto = (PresupuestoIf)SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(pedido.getReferencia()).iterator().next();
							HashMap aMapPrd = new HashMap();
							aMapPrd.put("presupuestoId", presupuesto.getId());
							aMapPrd.put("productoId", facturaDetalle.getProductoId());
							Collection presupuestoDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByQuery(aMapPrd);
							Iterator presupuestoDetalleIt = presupuestoDetalle.iterator();
							while(presupuestoDetalleIt.hasNext()){
								PresupuestoDetalleIf pd = (PresupuestoDetalleIf)presupuestoDetalleIt.next();
								if(pd.getReporte().equals("S")){
									System.out.println("presupuestoDetalle_id: " + pd.getId());
									pd.setProductoId(4965L);
									SessionServiceLocator.getPresupuestoDetalleSessionService().savePresupuestoDetalle(pd);
								}
							}								
						}							
						
						HashMap aMap = new HashMap();
						aMap.put("pedidoId", factura.getPedidoId());
						aMap.put("productoId", facturaDetalle.getProductoId());
						PedidoDetalleIf pedidoDetalle = (PedidoDetalleIf)SessionServiceLocator.getPedidoDetalleSessionService().findPedidoDetalleByQuery(aMap).iterator().next();
						pedidoDetalle.setProductoId(4965L);
						SessionServiceLocator.getPedidoDetalleSessionService().savePedidoDetalle(pedidoDetalle);
						facturaDetalle.setProductoId(4965L);
						SessionServiceLocator.getFacturaDetalleSessionService().saveFacturaDetalle(facturaDetalle);
						*/
						System.out.println("CLIENTE: " + clienteOficina.getDescripcion() + ", " + "FACTURA: " + factura.getPreimpresoNumero() + ", " + "GENERICO: " + generico.getNombre() + ", " + "VALOR: " + factura.getValor().subtract(factura.getDescuento()).add(factura.getIva()));
						System.out.println("factura_id: " + factura.getId() + ", " + "factura_detalle_id: " + facturaDetalle.getId());
					/*} catch (GenericBusinessException e) {
						e.printStackTrace();
					}*/					
				}
			}				
		}
		//System.out.println("la suma factura " + factura.getPreimpresoNumero() + ": " + sumaFactura);
		mapaClienteOficinaTipoProducto.put(clienteOficina,mapaTipoProductoSuma);
		return suma;
	}
	
	private void generarMapaComisionClienteOficinaTipoProducto(LinkedMap mapaFacturas, Vector<FacturaDetalleIf> facturaDetalleVector) {
		try{
			//Calculo de SOLO la camosion de cada presupuesto facturado, se quiere la información en totales
			// de la diferencia entre la factura y las compras, entonces:
			// Descto. Compra - Descto. Venta + Comision Agencia.		
			
			ProductoIf producto = null;
			GenericoIf generico = null;
			TipoProductoIf tipoProducto = null;
			mapaClienteOficinaTipoProducto = null;
			mapaClienteOficinaTipoProducto = new LinkedMap();
			
			Iterator mapaFacturasIt = mapaFacturas.keySet().iterator();
			while (mapaFacturasIt.hasNext()) {
				Long facturaId = (Long)mapaFacturasIt.next();
				FacturaIf factura = (FacturaIf)mapaFacturas.get(facturaId);
				ClienteOficinaIf clienteOficina = mapaClienteOficina.get(factura.getClienteoficinaId());
							
				Map<String,BigDecimal> mapaTipoProductoSuma = null;
				if(mapaClienteOficinaTipoProducto.get(clienteOficina) == null){
					mapaTipoProductoSuma = new HashMap<String,BigDecimal>();
					mapaTipoProductoSuma.put(CODIGO_TELEVISION, new BigDecimal(0));
					mapaTipoProductoSuma.put(CODIGO_PRENSA, new BigDecimal(0));
					mapaTipoProductoSuma.put(CODIGO_RADIO, new BigDecimal(0));
					mapaTipoProductoSuma.put(CODIGO_REVISTA, new BigDecimal(0));
					mapaTipoProductoSuma.put(CODIGO_PRODUCCION_INTERNA, new BigDecimal(0));
					mapaTipoProductoSuma.put(CODIGO_PRODUCCION_EXTERNA, new BigDecimal(0));
					mapaTipoProductoSuma.put(CODIGO_VALLAS, new BigDecimal(0));
					mapaTipoProductoSuma.put(CODIGO_FEE, new BigDecimal(0));
					mapaTipoProductoSuma.put(CODIGO_COMISION_DIRECTA, new BigDecimal(0));
					mapaTipoProductoSuma.put(CODIGO_OTROS, new BigDecimal(0));
				}else{
					mapaTipoProductoSuma = (Map<String,BigDecimal>)mapaClienteOficinaTipoProducto.get(clienteOficina);
				}
				
				//SI SE NECESITA SABER LAS COMISIONES DE UNA OFICINA ESPECIFICA ENTONCES SE BUSCA QUE VENDEDOR
				//HIZO LA ORDEN DE TRABAJO QUE ORIGINO EL PRESUPUESTO FACTURADO Y SOLO ESA INFO SE PRESENTA
				if(getCmbOficina().getSelectedItem() != null && !getCmbOficina().getSelectedItem().equals(ESTADO_TODOS)){
					OficinaIf oficina = (OficinaIf) getCmbOficina().getSelectedItem();
					EmpleadoIf vendedor = mapaEmpleado.get(factura.getVendedorId());
					
					if(vendedor.getOficinaId().compareTo(oficina.getId()) == 0){
						TipoDocumentoIf tipoDocumentoFactura = mapaTipoDocumento.get(factura.getTipodocumentoId());
						if(!tipoDocumentoFactura.getCodigo().equals(CODIGO_TIPO_DOCUMENTO_FACTURA_COMISION)){
							
							PedidoIf pedido = calcularMapaComisionClienteTipoProducto(
									factura, clienteOficina,
									mapaTipoProductoSuma);
						}
					}
				}else{
					TipoDocumentoIf tipoDocumentoFactura = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(factura.getTipodocumentoId());
					if(!tipoDocumentoFactura.getCodigo().equals(CODIGO_TIPO_DOCUMENTO_FACTURA_COMISION)){
						
						PedidoIf pedido = calcularMapaComisionClienteTipoProducto(
								factura, clienteOficina, mapaTipoProductoSuma);
					}
				}
			}
			
		}catch (GenericBusinessException e){
			e.printStackTrace();
		}
	}

	private PedidoIf calcularMapaComisionClienteTipoProducto(FacturaIf factura,
			ClienteOficinaIf clienteOficina,
			Map<String, BigDecimal> mapaTipoProductoSuma)
			throws GenericBusinessException {
		ProductoIf producto;
		GenericoIf generico;
		TipoProductoIf tipoProducto;
		PedidoIf pedido = SessionServiceLocator.getPedidoSessionService().getPedido(factura.getPedidoId());
		
		//Si la factura viene de un presupuesto calculo la comision
		if(pedido.getTiporeferencia().equals(TIPO_REFERENCIA_PRESUPUESTO) && pedido.getReferencia() != null){
			ArrayList presupuestoEncontrado = (ArrayList)SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(pedido.getReferencia());
			if(presupuestoEncontrado.size() > 0){
				PresupuestoIf presupuesto = (PresupuestoIf)presupuestoEncontrado.iterator().next();
				
				BigDecimal totalComisionPresupuestoDetalle = new BigDecimal(0);
				
				Collection presupuestoDetalles = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(presupuesto.getId());
				Iterator presupuestoDetallesIt = presupuestoDetalles.iterator();
				while(presupuestoDetallesIt.hasNext()){
					PresupuestoDetalleIf presupuestoDetalle = (PresupuestoDetalleIf)presupuestoDetallesIt.next();
					
					if(!presupuestoDetalle.getReporte().equals(PRESUPUESTO_DETALLE_REPORTE_SI)){
						//valor compra
						BigDecimal valorCompra = presupuestoDetalle.getPrecioagencia().multiply(presupuestoDetalle.getCantidad());
						double porcentajeDescuentoEspecialCompra = presupuestoDetalle.getPorcentajeDescuentoEspecialCompra().doubleValue() / 100;
						double descuentoEspecialCompra = valorCompra.doubleValue() * porcentajeDescuentoEspecialCompra;
						valorCompra = BigDecimal.valueOf(valorCompra.doubleValue() - descuentoEspecialCompra);
						//valor venta
						BigDecimal valorVenta = presupuestoDetalle.getPrecioventa().multiply(presupuestoDetalle.getCantidad());
						double porcentajeDescuentoEspecialVenta = presupuestoDetalle.getPorcentajeDescuentoEspecialVenta().doubleValue() / 100;
						double descuentoEspecialVenta = valorVenta.doubleValue() * porcentajeDescuentoEspecialVenta;
						valorVenta = BigDecimal.valueOf(valorVenta.doubleValue() - descuentoEspecialVenta);
						//Dscto. Compra
						double porcentajeDescuentoAgenciaCompra = presupuestoDetalle.getPorcentajeDescuentoAgenciaCompra().doubleValue() / 100;
						double descuentoAgenciaCompra = valorCompra.doubleValue() * porcentajeDescuentoAgenciaCompra;
						double porcentajeDescuentosVariosCompra = presupuestoDetalle.getPorcentajeDescuentosVariosCompra().doubleValue() / 100;
						double descuentosVariosCompra = valorCompra.doubleValue() * porcentajeDescuentosVariosCompra;
						BigDecimal dsctoCompra = BigDecimal.valueOf(descuentoAgenciaCompra + descuentosVariosCompra);
						//Dscto. Venta
						double porcentajeDescuentoAgenciaVenta = presupuestoDetalle.getPorcentajeDescuentoAgenciaVenta().doubleValue() / 100;
						double descuentoAgenciaVenta = valorVenta.doubleValue() * porcentajeDescuentoAgenciaVenta;
						double porcentajeDescuentosVariosVenta = presupuestoDetalle.getPorcentajeDescuentosVariosVenta().doubleValue() / 100;
						double descuentosVariosVenta = valorVenta.doubleValue() * porcentajeDescuentosVariosVenta;
						BigDecimal dsctoVenta = BigDecimal.valueOf(descuentoAgenciaVenta + descuentosVariosVenta);
						//Comision Agencia
						BigDecimal comisionAgencia = valorVenta.subtract(dsctoVenta).multiply(presupuesto.getPorcentajeComisionAgencia()).divide(new BigDecimal(100));
						
						//AHORA CALCULO EL TOTAL DE LA COMISION DEL PRESUPUESTO DETALLE FACTURADO
						totalComisionPresupuestoDetalle = dsctoCompra.subtract(dsctoVenta).add(comisionAgencia);
						
						producto = mapaProducto.get(presupuestoDetalle.getProductoId());
						generico = mapaGenerico.get(producto.getGenericoId());
						tipoProducto = mapaTipoProducto.get(generico.getTipoproductoId());
						if(tipoProducto.getCodigo().equals(CODIGO_TELEVISION)){
							mapaTipoProductoSuma.put(CODIGO_TELEVISION, mapaTipoProductoSuma.get(CODIGO_TELEVISION).add(totalComisionPresupuestoDetalle));
						}else if(tipoProducto.getCodigo().equals(CODIGO_PRENSA)){
							mapaTipoProductoSuma.put(CODIGO_PRENSA, mapaTipoProductoSuma.get(CODIGO_PRENSA).add(totalComisionPresupuestoDetalle));
						}else if(tipoProducto.getCodigo().equals(CODIGO_RADIO)){
							mapaTipoProductoSuma.put(CODIGO_RADIO, mapaTipoProductoSuma.get(CODIGO_RADIO).add(totalComisionPresupuestoDetalle));
						}else if(tipoProducto.getCodigo().equals(CODIGO_REVISTA)){
							mapaTipoProductoSuma.put(CODIGO_REVISTA, mapaTipoProductoSuma.get(CODIGO_REVISTA).add(totalComisionPresupuestoDetalle));
						}else if(tipoProducto.getCodigo().equals(CODIGO_PRODUCCION_INTERNA)){
							mapaTipoProductoSuma.put(CODIGO_PRODUCCION_INTERNA, mapaTipoProductoSuma.get(CODIGO_PRODUCCION_INTERNA).add(totalComisionPresupuestoDetalle));
						}else if(tipoProducto.getCodigo().equals(CODIGO_PRODUCCION_EXTERNA)){
							mapaTipoProductoSuma.put(CODIGO_PRODUCCION_EXTERNA, mapaTipoProductoSuma.get(CODIGO_PRODUCCION_EXTERNA).add(totalComisionPresupuestoDetalle));
						}else if(tipoProducto.getCodigo().equals(CODIGO_VALLAS)){
							mapaTipoProductoSuma.put(CODIGO_VALLAS, mapaTipoProductoSuma.get(CODIGO_VALLAS).add(totalComisionPresupuestoDetalle));
						}else if(tipoProducto.getCodigo().equals(CODIGO_FEE)){
							mapaTipoProductoSuma.put(CODIGO_FEE, mapaTipoProductoSuma.get(CODIGO_FEE).add(totalComisionPresupuestoDetalle));
						}else if(tipoProducto.getCodigo().equals(CODIGO_COMISION_DIRECTA)){
							mapaTipoProductoSuma.put(CODIGO_COMISION_DIRECTA, mapaTipoProductoSuma.get(CODIGO_COMISION_DIRECTA).add(totalComisionPresupuestoDetalle));
						}else if(!tipoProducto.getCodigo().equals(CODIGO_GASTOS_VARIOS)){
							mapaTipoProductoSuma.put(CODIGO_OTROS, mapaTipoProductoSuma.get(CODIGO_OTROS).add(totalComisionPresupuestoDetalle));
						}else{
							System.out.println("GENERICO de GASTOS VARIOS cuyo valor no se presenta:");
						}
					}							
				}
				mapaClienteOficinaTipoProducto.put(clienteOficina,mapaTipoProductoSuma);
				
				//CALCULO PRIMERO EL TOTAL DE DSCTO. DE COMPRA
				/*HashMap mapaSolicitudesCompra = new HashMap();
				mapaSolicitudesCompra.put("tipoReferencia", TIPO_REFERENCIA_PRESUPUESTO);
				mapaSolicitudesCompra.put("referencia", presupuesto.getCodigo());
				Collection solicitudesCompra = SessionServiceLocator.getSolicitudCompraSessionService().findSolicitudCompraByQuery(mapaSolicitudesCompra);
				Iterator solicitudesCompraIt = solicitudesCompra.iterator();
				while(solicitudesCompraIt.hasNext()){
					SolicitudCompraIf solicitudCompra = (SolicitudCompraIf)solicitudesCompraIt.next();
					
					Collection ordenesCompra = SessionServiceLocator.getOrdenCompraSessionService().findOrdenCompraBySolicitudcompraId(solicitudCompra.getId());
					Iterator ordenesCompraIt = ordenesCompra.iterator();
					while(ordenesCompraIt.hasNext()){
						OrdenCompraIf ordenCompra = (OrdenCompraIf)ordenesCompraIt.next();
						totalPresupuestoDsctCompra = totalPresupuestoDsctCompra.add(ordenCompra.getDescuento());
					}
				}		*/
				
				//CALCULO LA COMISION DE AGENCIA
				//totalPresupuestoComisionAgencia = presupuesto.getValorbruto().subtract(presupuesto.getDescuento()).multiply(presupuesto.getPorcentajeComisionAgencia()).divide(new BigDecimal(100));
				
				//AHORA CALCULO EL TOTAL DE LA COMISION DEL PRESUPUESTO FACTURADO
				//totalComisionPresupuesto = totalPresupuestoDsctCompra.subtract(presupuesto.getDescuento()).add(totalPresupuestoComisionAgencia);
				
			}					
		}
		return pedido;
	}
	
	public void generarMapaNotaCreditoClienteTipoProducto(ArrayList<NotaCreditoIf> notasCredito){
		try {
			LinkedMap mapaNotasCredito = new LinkedMap();
			Vector<NotaCreditoDetalleIf> notasCreditoDetalleVector = new Vector<NotaCreditoDetalleIf>();
			Iterator notasCreditoIt = notasCredito.iterator();
			while (notasCreditoIt.hasNext()) {
				NotaCreditoIf notaCreditoIf = (NotaCreditoIf) notasCreditoIt.next();
				mapaNotasCredito.put(notaCreditoIf.getId(), notaCreditoIf);
				
				Collection notasCreditoDetalleColeccion = SessionServiceLocator.getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByNotaCreditoId(notaCreditoIf.getId());
				Iterator notasCreditoDetalleColeccionIt = notasCreditoDetalleColeccion.iterator();
				while(notasCreditoDetalleColeccionIt.hasNext()){
					NotaCreditoDetalleIf notaCreditoDetalleIf = (NotaCreditoDetalleIf)notasCreditoDetalleColeccionIt.next();
					notasCreditoDetalleVector.add(notaCreditoDetalleIf);
				}		
			}
			generarMapaNotaCreditoClienteOficinaTipoProducto(mapaNotasCredito, notasCreditoDetalleVector);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void generarMapaNotaCreditoClienteOficinaTipoProducto(LinkedMap mapaNotasCredito, Vector<NotaCreditoDetalleIf> notasCreditoDetalleVector) {
		ProductoIf producto = null;
		GenericoIf generico = null;
		TipoProductoIf tipoProducto = null;
		mapaNotaCreditoClienteOficinaTipoProducto = null;
		mapaNotaCreditoClienteOficinaTipoProducto = new LinkedMap();
		BigDecimal suma = new BigDecimal(0);
		BigDecimal sumaFactura = new BigDecimal(0);
		Iterator mapaNotasCreditoIt = mapaNotasCredito.keySet().iterator();
		while (mapaNotasCreditoIt.hasNext()) {
			Long notaCreditoId = (Long)mapaNotasCreditoIt.next();
			NotaCreditoIf notaCredito = (NotaCreditoIf)mapaNotasCredito.get(notaCreditoId);
			ClienteOficinaIf clienteOficina = mapaClienteOficina.get(notaCredito.getOperadorNegocioId());
			
			Map<String,BigDecimal> mapaTipoProductoSuma = null;
			if(mapaNotaCreditoClienteOficinaTipoProducto.get(clienteOficina) == null){
				mapaTipoProductoSuma = new HashMap<String,BigDecimal>();
				mapaTipoProductoSuma.put(CODIGO_TELEVISION, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_PRENSA, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_RADIO, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_REVISTA, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_PRODUCCION_INTERNA, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_PRODUCCION_EXTERNA, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_VALLAS, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_FEE, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_COMISION_DIRECTA, new BigDecimal(0));
				mapaTipoProductoSuma.put(CODIGO_OTROS, new BigDecimal(0));
			}else{
				mapaTipoProductoSuma = (Map<String,BigDecimal>)mapaNotaCreditoClienteOficinaTipoProducto.get(clienteOficina);
			}
			
			sumaFactura = new BigDecimal(0);
			for(int i=0; i<notasCreditoDetalleVector.size(); i++){
				NotaCreditoDetalleIf notaCreditoDetalleIf = notasCreditoDetalleVector.get(i);
				
				if(notaCreditoDetalleIf.getNotaCreditoId().compareTo(notaCreditoId) == 0){
					
					resetearValores();
					valor = notaCreditoDetalleIf.getValor().multiply(notaCreditoDetalleIf.getCantidad());
					
					if(getCmbIVA().getSelectedItem().equals(SIN_IVA)){
						DocumentoIf documento = mapaDocumento.get(notaCreditoDetalleIf.getDocumentoId());
						if(documento.getCodigo().equals(DOCUMENTO_NC_FACTURA_REEMBOLSO)){
							subTotal = valor.divide(new BigDecimal(1).add(BigDecimal.valueOf(Parametros.IVA).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
						}else{
							subTotal = valor;
						}
						total = subTotal;
					}else{
						subTotal = valor;
						iva = notaCreditoDetalleIf.getIva();
						total = subTotal.add(iva);
					}
					
					suma = suma.add(total);
					sumaFactura = sumaFactura.add(total);
					producto = mapaProducto.get(notaCreditoDetalleIf.getProductoId());
					generico = mapaGenerico.get(producto.getGenericoId());
					tipoProducto = mapaTipoProducto.get(generico.getTipoproductoId());
					if(tipoProducto.getCodigo().equals(CODIGO_TELEVISION)){
						mapaTipoProductoSuma.put(CODIGO_TELEVISION, mapaTipoProductoSuma.get(CODIGO_TELEVISION).add(total));
					}else if(tipoProducto.getCodigo().equals(CODIGO_PRENSA)){
						mapaTipoProductoSuma.put(CODIGO_PRENSA, mapaTipoProductoSuma.get(CODIGO_PRENSA).add(total));
					}else if(tipoProducto.getCodigo().equals(CODIGO_RADIO)){
						mapaTipoProductoSuma.put(CODIGO_RADIO, mapaTipoProductoSuma.get(CODIGO_RADIO).add(total));
					}else if(tipoProducto.getCodigo().equals(CODIGO_REVISTA)){
						mapaTipoProductoSuma.put(CODIGO_REVISTA, mapaTipoProductoSuma.get(CODIGO_REVISTA).add(total));
					}else if(tipoProducto.getCodigo().equals(CODIGO_PRODUCCION_INTERNA)){
						mapaTipoProductoSuma.put(CODIGO_PRODUCCION_INTERNA, mapaTipoProductoSuma.get(CODIGO_PRODUCCION_INTERNA).add(total));
					}else if(tipoProducto.getCodigo().equals(CODIGO_PRODUCCION_EXTERNA)){
						mapaTipoProductoSuma.put(CODIGO_PRODUCCION_EXTERNA, mapaTipoProductoSuma.get(CODIGO_PRODUCCION_EXTERNA).add(total));
					}else if(tipoProducto.getCodigo().equals(CODIGO_VALLAS)){
						mapaTipoProductoSuma.put(CODIGO_VALLAS, mapaTipoProductoSuma.get(CODIGO_VALLAS).add(total));
					}else if(tipoProducto.getCodigo().equals(CODIGO_FEE)){
						mapaTipoProductoSuma.put(CODIGO_FEE, mapaTipoProductoSuma.get(CODIGO_FEE).add(total));
					}else if(tipoProducto.getCodigo().equals(CODIGO_COMISION_DIRECTA)){
						mapaTipoProductoSuma.put(CODIGO_COMISION_DIRECTA, mapaTipoProductoSuma.get(CODIGO_COMISION_DIRECTA).add(total));
					}else if(!tipoProducto.getCodigo().equals(CODIGO_GASTOS_VARIOS)){
						mapaTipoProductoSuma.put(CODIGO_OTROS, mapaTipoProductoSuma.get(CODIGO_OTROS).add(total));
					}else{
						System.out.println("NOTA DE CREDITO: GENERICO de GASTOS VARIOS cuyo valor no se presenta:");
						System.out.println("CLIENTE: " + clienteOficina.getDescripcion() + ", " + "FACTURA: " + notaCredito.getPreimpreso() + ", " + "GENERICO: " + generico.getNombre() + ", " + "VALOR: " + notaCredito.getValor().add(notaCredito.getIva()));
					}
				}				
			}
			//System.out.println("la suma factura " + factura.getPreimpresoNumero() + ": " + sumaFactura);
			mapaNotaCreditoClienteOficinaTipoProducto.put(clienteOficina,mapaTipoProductoSuma);
		}
		//System.out.println("la suma total : " + suma);
	}
	
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
	
	private void cargarTabla() {
		cleanTotales();
		generarColeccionFacturas();
		
		Iterator mapaClienteOficinaTipoProductoIt = mapaClienteOficinaTipoProducto.keySet().iterator();
		while (mapaClienteOficinaTipoProductoIt.hasNext()) {
			ClienteOficinaIf clienteOficinaIf = (ClienteOficinaIf) mapaClienteOficinaTipoProductoIt.next();
			Map<String,BigDecimal> mapaTipoProductoSuma = (Map<String,BigDecimal>)mapaClienteOficinaTipoProducto.get(clienteOficinaIf);
			tableModel = (DefaultTableModel) getTblFacturacion().getModel();
			Vector<String> fila = new Vector<String>();
			FacturacionTipoProductoData facturacionData = new FacturacionTipoProductoData();
			
			agregarColumnasTabla(clienteOficinaIf, mapaTipoProductoSuma, fila, facturacionData);
			
			tableModel.addRow(fila);
			facturacionColeccion.add(facturacionData);
		}
		
		//esta coleccion la lleno para presentarla en el reporte, no se ve en pantalla.	
		if(!getCbSinNotasCredito().isSelected() && getCmbOficina().getSelectedItem().equals(ESTADO_TODOS)){
			
			Collection<NotaCreditoIf> notasCreditoCliente = generarColeccionNotasCreditoCliente();
			
			//Ordeno las Notas de Credito por Nombre Legal del Cliente
			Collections.sort((ArrayList)notasCreditoCliente,ordenadorNotaCreditoPorNombreCliente);
			
			generarMapaNotaCreditoClienteTipoProducto((ArrayList<NotaCreditoIf>)notasCreditoCliente);
			
			Iterator mapaNotaCreditoClienteOficinaTipoProductoIt = mapaNotaCreditoClienteOficinaTipoProducto.keySet().iterator();
			while (mapaNotaCreditoClienteOficinaTipoProductoIt.hasNext()) {
				ClienteOficinaIf clienteOficinaIf = (ClienteOficinaIf) mapaNotaCreditoClienteOficinaTipoProductoIt.next();
				Map<String,BigDecimal> mapaTipoProductoSuma = (Map<String,BigDecimal>)mapaNotaCreditoClienteOficinaTipoProducto.get(clienteOficinaIf);
				ClienteIf cliente = mapaCliente.get(clienteOficinaIf.getClienteId());
					
				BigDecimal totalCliente = new BigDecimal(0);
				NotaCreditoClienteTipoProductoData notaCreditoTipoProductoData = new NotaCreditoClienteTipoProductoData();
				notaCreditoTipoProductoData.setCliente(cliente.getNombreLegal());
				
				notaCreditoTipoProductoData.setTelevision(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_TELEVISION)));
				totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_TELEVISION));
				televisionNotasCredito = televisionNotasCredito.add(mapaTipoProductoSuma.get(CODIGO_TELEVISION));
								
				notaCreditoTipoProductoData.setPrensa(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_PRENSA)));
				totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_PRENSA));
				prensaNotasCredito = prensaNotasCredito.add(mapaTipoProductoSuma.get(CODIGO_PRENSA));
				
				notaCreditoTipoProductoData.setRadio(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_RADIO)));
				totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_RADIO));
				radioNotasCredito = radioNotasCredito.add(mapaTipoProductoSuma.get(CODIGO_RADIO));
				
				notaCreditoTipoProductoData.setRevista(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_REVISTA)));
				totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_REVISTA));
				revistaNotasCredito = revistaNotasCredito.add(mapaTipoProductoSuma.get(CODIGO_REVISTA));
				
				notaCreditoTipoProductoData.setPinterna(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_PRODUCCION_INTERNA)));
				totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_PRODUCCION_INTERNA));
				pInternaNotasCredito = pInternaNotasCredito.add(mapaTipoProductoSuma.get(CODIGO_PRODUCCION_INTERNA));
				
				notaCreditoTipoProductoData.setPexterna(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_PRODUCCION_EXTERNA)));
				totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_PRODUCCION_EXTERNA));
				pExternaNotasCredito = pExternaNotasCredito.add(mapaTipoProductoSuma.get(CODIGO_PRODUCCION_EXTERNA));
				
				notaCreditoTipoProductoData.setVallas(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_VALLAS)));
				totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_VALLAS));
				vallasNotasCredito = vallasNotasCredito.add(mapaTipoProductoSuma.get(CODIGO_VALLAS));
				
				notaCreditoTipoProductoData.setFee(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_FEE)));
				totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_FEE));
				feeNotasCredito = feeNotasCredito.add(mapaTipoProductoSuma.get(CODIGO_FEE));
								
				notaCreditoTipoProductoData.setComisionDirecta(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_COMISION_DIRECTA)));
				totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_COMISION_DIRECTA));
				comisionDirectaNotasCredito = comisionDirectaNotasCredito.add(mapaTipoProductoSuma.get(CODIGO_COMISION_DIRECTA));
								
				notaCreditoTipoProductoData.setOtros(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_OTROS)));
				totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_OTROS));
				otrosNotasCredito = otrosNotasCredito.add(mapaTipoProductoSuma.get(CODIGO_OTROS));
			
				notaCreditoTipoProductoData.setTotal(formatoDecimal.format(totalCliente));
				totalNotasCredito = totalNotasCredito.add(totalCliente);					
				
				notasCreditoClienteTipoProductoDataColeccion.add(notaCreditoTipoProductoData);				
			}
		}
	}

	private void agregarColumnasTabla(ClienteOficinaIf clienteOficinaIf, Map<String,BigDecimal> mapaTipoProductoSuma, Vector<String> fila, FacturacionTipoProductoData facturacionData) {
		ClienteIf cliente = mapaCliente.get(clienteOficinaIf.getClienteId());
		
		facturacionData.setClienteId(cliente.getId().toString());
		
		if(getCbUsarNombreComercial().isSelected()){
			facturacionData.setCliente(clienteOficinaIf.getDescripcion());
		}else{
			facturacionData.setCliente(cliente.getNombreLegal());
		}
		
		facturacionData.setRuc(cliente.getIdentificacion());
				
		if(getCbUsarNombreComercial().isSelected()){
			fila.add(clienteOficinaIf.getDescripcion());
		}else{
			fila.add(cliente.getNombreLegal());
		}
		
		//televisionFacturacion
		BigDecimal totalCliente = new BigDecimal(0);
		fila.add(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_TELEVISION)));
		facturacionData.setTelevision(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_TELEVISION)));
		totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_TELEVISION));
		televisionFacturacion = televisionFacturacion.add(mapaTipoProductoSuma.get(CODIGO_TELEVISION));
		
		fila.add(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_PRENSA)));
		facturacionData.setPrensa(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_PRENSA)));
		totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_PRENSA));
		prensaFacturacion = prensaFacturacion.add(mapaTipoProductoSuma.get(CODIGO_PRENSA));
		
		fila.add(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_RADIO)));
		facturacionData.setRadio(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_RADIO)));
		totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_RADIO));
		radioFacturacion = radioFacturacion.add(mapaTipoProductoSuma.get(CODIGO_RADIO));
		
		fila.add(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_REVISTA)));
		facturacionData.setRevista(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_REVISTA)));
		totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_REVISTA));
		revistaFacturacion = revistaFacturacion.add(mapaTipoProductoSuma.get(CODIGO_REVISTA));
		
		fila.add(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_PRODUCCION_INTERNA)));
		facturacionData.setPinterna(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_PRODUCCION_INTERNA)));
		totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_PRODUCCION_INTERNA));
		pInternaFacturacion = pInternaFacturacion.add(mapaTipoProductoSuma.get(CODIGO_PRODUCCION_INTERNA));
		
		fila.add(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_PRODUCCION_EXTERNA)));
		facturacionData.setPexterna(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_PRODUCCION_EXTERNA)));
		totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_PRODUCCION_EXTERNA));
		pExternaFacturacion = pExternaFacturacion.add(mapaTipoProductoSuma.get(CODIGO_PRODUCCION_EXTERNA));
		
		fila.add(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_VALLAS)));
		facturacionData.setVallas(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_VALLAS)));
		totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_VALLAS));
		vallasFacturacion = vallasFacturacion.add(mapaTipoProductoSuma.get(CODIGO_VALLAS));
		
		fila.add(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_FEE)));
		facturacionData.setFee(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_FEE)));
		totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_FEE));
		feeFacturacion = feeFacturacion.add(mapaTipoProductoSuma.get(CODIGO_FEE));
		
		fila.add(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_COMISION_DIRECTA)));
		facturacionData.setComisionDirecta(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_COMISION_DIRECTA)));
		totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_COMISION_DIRECTA));
		comisionDirectaFacturacion = comisionDirectaFacturacion.add(mapaTipoProductoSuma.get(CODIGO_COMISION_DIRECTA));
		
		fila.add(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_OTROS)));
		facturacionData.setOtros(formatoDecimal.format(mapaTipoProductoSuma.get(CODIGO_OTROS)));
		totalCliente = totalCliente.add(mapaTipoProductoSuma.get(CODIGO_OTROS));
		otrosFacturacion = otrosFacturacion.add(mapaTipoProductoSuma.get(CODIGO_OTROS));
		
		fila.add(formatoDecimal.format(totalCliente));
		facturacionData.setTotal(formatoDecimal.format(totalCliente));
		totalTotal = totalTotal.add(totalCliente);
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
