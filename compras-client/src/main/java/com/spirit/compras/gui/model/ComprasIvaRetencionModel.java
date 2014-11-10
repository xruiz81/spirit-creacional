package com.spirit.compras.gui.model;

import java.awt.Component;
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

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.collections.map.LinkedMap;

import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.compras.entity.LogCompraRetencionIf;
import com.spirit.compras.gui.criteria.RetencionCriteria;
import com.spirit.compras.gui.panel.JPComprasIvaRetencion;
import com.spirit.compras.reportesData.TransaccionesSRIData;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.sri.entity.SriAirData;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIvaRetencionData;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.util.Utilitarios;

public class ComprasIvaRetencionModel extends JPComprasIvaRetencion {
	
	private static final long serialVersionUID = 8670052747186270751L;
	
	protected ClienteOficinaIf clienteOficinaIf;
	protected ClienteIf clienteIf;
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private Map<Long,TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long,TipoDocumentoIf>();
	private Map<Long,DocumentoIf> mapaDocumento = new HashMap<Long,DocumentoIf>();
	private Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
	
	private Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
	private Map<Long,ProductoIf> mapaProducto = new HashMap<Long,ProductoIf>();
	private Map<Long,GenericoIf> mapaGenerico = new HashMap<Long,GenericoIf>();
	private Map<Long,SriIvaRetencionIf> mapaCodigoRetencionIva = new HashMap<Long,SriIvaRetencionIf>();
	private Map<Long,SriAirIf> mapaCodigoRetencionRenta = new HashMap<Long,SriAirIf>();
	private Map comprasMap = new HashMap();
	private LinkedMap mapaCompra = new LinkedMap();
	private Vector<CompraDetalleIf> compraDetalleVector = new Vector<CompraDetalleIf>();
	private Vector<CompraRetencionIf> compraRetencionVector = new Vector<CompraRetencionIf>();
	
	private LinkedMap mapaCompraAnuladas = new LinkedMap();
	private Vector<LogCompraRetencionIf> compraRetencionAnuladasVector = new Vector<LogCompraRetencionIf>();
	
	private Map<Long,Map<String,Object[]>> mapaCompraRetencionesAnuladas = new HashMap<Long,Map<String,Object[]>>();
	
	private static final String COBRA_IVA_NO = "N";
	private static final String ESTADO_ANULADO = "N";
	private Map<Long,BigDecimal[]> mapaCompraBases = new HashMap<Long,BigDecimal[]>();
	private Map<Long,Map<String,Object[]>> mapaCompraRetenciones = new HashMap<Long,Map<String,Object[]>>();
	
	private static final String CODIGO_COMPRA_REEMBOLSO = "COR";
	private static final String CODIGO_COMPRA_IMPORTADA = "COI";
	
	
	private static final String CODIGO_NC_COMPRA_REEMBOLSO = "NCCR";
	
	
	private static final String IMPUESTO_RENTA = "R";
	private static final String IMPUESTO_IVA = "I";
	private static String NOMBRE_MENU_COMPRAS = "COMPRAS";
	private Vector<ComprasIvaRetencionData> comprasColeccion = new Vector<ComprasIvaRetencionData>();
	
	private Vector<ComprasIvaRetencionData> comprasColeccionNCND = new Vector<ComprasIvaRetencionData>();
	private Vector<ComprasIvaRetencionData> comprasColeccionTotales = new Vector<ComprasIvaRetencionData>();
	
	private Vector<ComprasIvaRetencionConsolidadoData> compraConsolidadoColeccion = new Vector<ComprasIvaRetencionConsolidadoData>();
	
	private ArrayList<ComprasIvaRetencionResumenData> comprasResumenColeccion = new ArrayList<ComprasIvaRetencionResumenData>();
	private BigDecimal totalTotal = new BigDecimal(0);
	private String secuencialInicio = "";
	private String secuencialFin = "";
	
	private Vector<CarteraIf> carteraVector = new Vector<CarteraIf>();
	private Map<String,Long> mapaTipoDocumentoCodigo = new HashMap<String,Long>();
	private Vector<TransaccionesSRIData> transaccionesSRIColeccion = new Vector<TransaccionesSRIData>();
	
	private Map<String,BigDecimal> mapaCamposValoresMeses = new LinkedMap();
	
	private Map<String,Map<String,BigDecimal>> mapaCamposValoresMeses309 = new LinkedMap();
	
	private Map<String,String> mapaContadorRegistros = new LinkedMap();
	
	
	
	
	public double exterior=0D;
	public double reembolso=0D;
	public double normal=0D;
	public double iva=0D;
	public double total=0D;
	
	
	public String tipo="C";
	
	public ComprasIvaRetencionModel(){
		cargarMapasTipoDocumentoCliente();
		anchoColumnasTabla();
		initKeyListeners();
		showSaveMode();
		initListeners();
	}
	
	public void cargarMapasTipoDocumentoCliente(){
		
		try {
			cargarComboAIR();
			cargarComboIVA();	
			mapaTipoDocumentoCodigo.clear();
			Collection tiposDocumentoCodigo = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumentoList();
			Iterator tiposDocumentoCodigoIt = tiposDocumentoCodigo.iterator();
			while(tiposDocumentoCodigoIt.hasNext()){
				TipoDocumentoIf tipoDocumentoCodigo = (TipoDocumentoIf)tiposDocumentoCodigoIt.next();				
				mapaTipoDocumentoCodigo.put(tipoDocumentoCodigo.getCodigo(), tipoDocumentoCodigo.getId());
			}
			
			mapaTipoDocumento.clear();
			Collection tiposDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumentoList();
			Iterator tiposDocumentoIt = tiposDocumento.iterator();
			while(tiposDocumentoIt.hasNext()){
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)tiposDocumentoIt.next();
				mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
			}
			
			mapaDocumento.clear();
			Collection documento = SessionServiceLocator.getDocumentoSessionService().getDocumentoList();
			Iterator documentoIt = documento.iterator();
			while(documentoIt.hasNext()){
				DocumentoIf documentos = (DocumentoIf) documentoIt.next(); 
				mapaDocumento.put(documentos.getId(), documentos);
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
			
			mapaCodigoRetencionIva.clear();
			Collection retencionesIva = SessionServiceLocator.getSriIvaRetencionSessionService().getSriIvaRetencionList();
			Iterator retencionesIvaIt = retencionesIva.iterator();
			while(retencionesIvaIt.hasNext()){
				SriIvaRetencionIf sriIvaRetencionIf = (SriIvaRetencionIf)retencionesIvaIt.next();
				mapaCodigoRetencionIva.put(sriIvaRetencionIf.getId(), sriIvaRetencionIf);
			}
			
			mapaCodigoRetencionRenta.clear();
			Collection retencionesRenta = SessionServiceLocator.getSriAirSessionService().getSriAirList();
			Iterator retencionesRentaIt = retencionesRenta.iterator();
			while(retencionesRentaIt.hasNext()){
				SriAirIf sriAirIf = (SriAirIf)retencionesRentaIt.next();
				mapaCodigoRetencionRenta.put(sriAirIf.getId(), sriAirIf);
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}	
	}
	
	private void cargarComboAIR(){
		try {
			//List tiposProducto = (List)SessionServiceLocator.getTipoProductoSessionService().findTipoProductoDonacion();
			List tiposProducto = (List)SessionServiceLocator.getCompraDetalleSessionService().findSriAirCompraDetalle();
			SriAirData da= new SriAirData();
			da.setId(null);
			da.setCodigo("TODOS");			
			da.setPorcentaje(new BigDecimal("0"));
			da.setConcepto("  ");
			tiposProducto.add(0,da);
			//35
			refreshCombo(getCbCodigoRenta(), tiposProducto);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboIVA(){
		try {
			//List tiposProducto = (List)SessionServiceLocator.getTipoProductoSessionService().findTipoProductoDonacion();
			List codigosIVA = (List)SessionServiceLocator.getCompraDetalleSessionService().findSriIvaRetencionCompraDetalle();			
			SriIvaRetencionData da= new SriIvaRetencionData();
			da.setId(null);
			da.setCodigo("TODOS");			
			da.setPorcentaje(new BigDecimal("0"));
			da.setConcepto("  ");
			codigosIVA.add(0,da);			
			refreshCombo(getCbCodigoIVA(), codigosIVA);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void initKeyListeners(){
		getBtnConsultar().setToolTipText("Consultar");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnConsultarConsolidado().setToolTipText("Consultar Consolidado");
		getBtnConsultarConsolidado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnProveedor().setToolTipText("Buscar Proveedor");
		getBtnProveedor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnRetencionNumero().setToolTipText("Buscar Retencion");
		getBtnRetencionNumero().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getCbOrdenarPorProveedor().setSelected(true);
		getTxtProveedor().setEditable(false);
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
		
		getCkbNotasCredito().setSelected(true);
	}

	private void anchoColumnasTabla() {
		//setSorterTable(getTblCompras());
		getTblCompras().getTableHeader().setReorderingAllowed(false);
		//getTblChequesEmitidos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblCompras().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblCompras().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(115);
		anchoColumna = getTblCompras().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblCompras().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblCompras().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(25);
		anchoColumna = getTblCompras().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblCompras().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblCompras().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblCompras().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblCompras().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(89);
		anchoColumna = getTblCompras().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(89);
		anchoColumna = getTblCompras().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblCompras().getColumnModel().getColumn(11);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblCompras().getColumnModel().getColumn(12);
		anchoColumna.setPreferredWidth(20);
		anchoColumna = getTblCompras().getColumnModel().getColumn(13);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblCompras().getColumnModel().getColumn(14);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblCompras().getColumnModel().getColumn(15);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblCompras().getColumnModel().getColumn(16);
		anchoColumna.setPreferredWidth(95);
		
		columnasConsolidado();
	}
	
	private void initListeners() {
		getCbOrdenarPorProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbOrdenarPorProveedor().isSelected()){
					getCbOrdenarPorSecuencialRetencion().setSelected(false);
				}
			}
		});
		
		getCbOrdenarPorSecuencialRetencion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbOrdenarPorSecuencialRetencion().isSelected()){
					getCbOrdenarPorProveedor().setSelected(false);
				}
			}
		});
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipo="C";
				cleanTable();
				cargarTabla();
			}
		});
		
		 
		/*getCkbNotasCredito().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCkbNotasCredito().isSelected())	getCmbEstado().setSelectedIndex(0);
			}
		}); */
		
		getBtnConsultarConsolidado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipo="CC";//ConsultarConsolidado
				cleanTable();
				cargarTabla();
				if(getCkbNotasCredito().isSelected())	getCkbNotasCredito().setSelected(false);
			}
		});
		
		getCbTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodos().isSelected()){
					clienteOficinaIf = null;
					getTxtProveedor().setText("");
				}
			}
		});
		
		getBtnProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tipoCliente = "PR";
				String tituloVentanaBusqueda = "Proveedores";
				ClienteOficinaCriteria clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente,"", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null){
					clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					try {
						clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
						if(clienteIf != null){
							getTxtProveedor().setText(clienteIf.getIdentificacion() + " - " + clienteOficinaIf.getDescripcion()); 
						} else
							SpiritAlert.createAlert("No existe el Proveedor", SpiritAlert.ERROR);
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error en consulta de Proveedor", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}
			}
		});
		  
		
		getBtnRetencionNumero().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				JDPopupFinderModel popupFinderPedido;
				
				Long idCorporacion = 0L;
				RetencionCriteria retencionCriteria = new RetencionCriteria("Ventas Realizadas", idCorporacion, "");
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(80);
				anchoColumnas.add(150);				

				popupFinderPedido = new JDPopupFinderModel(Parametros.getMainFrame(), retencionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinderPedido.getElementoSeleccionado() != null) {
					CompraRetencionIf compraRet = (CompraRetencionIf) popupFinderPedido.getElementoSeleccionado();
					// //setear valores de cliente////////////////////////////
					//System.out.println("CompraRetencionIf:::>>>>*9*******"+compraRet.getSecuencial());
					if(compraRet.getSecuencial()!=null)
						getTxtRetencionNumero().setText(compraRet.getSecuencial());
					// setear el vendedor
				}				
			}
		});
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
		cleanTotales();
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		cleanTable();
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblCompras().getModel();
		for(int i= this.getTblCompras().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void cleanTotales(){
		secuencialInicio = "";
		secuencialFin = "";
		comprasColeccion = null;
		comprasColeccion = new Vector<ComprasIvaRetencionData>();
		comprasResumenColeccion = null;
		comprasResumenColeccion = new ArrayList<ComprasIvaRetencionResumenData>();
		
		compraConsolidadoColeccion = null;
		compraConsolidadoColeccion = new Vector<ComprasIvaRetencionConsolidadoData>();
		
		comprasColeccionNCND = null;
		comprasColeccionNCND = new Vector<ComprasIvaRetencionData>();
		
		totalTotal = new BigDecimal(0);
		
		exterior=0D;
	    reembolso=0D;
		normal=0D;
		iva=0D;
		total=0D;
	}

	public void report() {
		try {
			DefaultTableModel tblModelReporte = (DefaultTableModel) getTblCompras().getModel();
			if (tblModelReporte.getRowCount() > 0) {
				String si = "Si";
				String no = "No";
				Object[] options = {si,no};
				String mensaje = "¿Desea generar el reporte de Compras?";
				int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if(opcion == JOptionPane.YES_OPTION) {
					String fileName = "";
					boolean consolidado=false;
					
					if(tipo.equals("CC"))
					{
						fileName = "jaspers/compras/RPComprasPorIVAyRetencionConsolidado.jasper";
						consolidado=true;
					}
					else{
						fileName = "jaspers/compras/RPComprasPorIVAyRetencion.jasper";
						consolidado=false;
					}
					 
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_COMPRAS).iterator().next();
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
					parametrosMap.put("blanco", " ");
					//exterior=formatoDecimal.format(exterior)				
					parametrosMap.put("secuencialInicio", secuencialInicio);
					parametrosMap.put("secuencialFin", secuencialFin);
					///////////////////////////////////////////
					JRDataSource dataSourceDebitos = new JRBeanCollectionDataSource(comprasResumenColeccion);
					if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
						parametrosMap.put("pathsubreportResumen", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/compras/RPComprasPorIVAyRetencionResumen.jasper");
					else 
						throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
					
					parametrosMap.put("dataSourceResumen", dataSourceDebitos);
					
					if(!consolidado)
					{	
						if(comprasColeccionNCND.size()>0)
						{	
							parametrosMap.put("exterior",formatoDecimal.format(exterior));						
							parametrosMap.put("reembolso",formatoDecimal.format(reembolso));		
							parametrosMap.put("normal",formatoDecimal.format(normal));		
							parametrosMap.put("iva",formatoDecimal.format(iva));		
							parametrosMap.put("total",formatoDecimal.format(total));	
							
							
							
							fileName = "jaspers/compras/RPComprasPorIVAyRetencionNCResumenPrincipal.jasper";
					 
							JRDataSource dataSourceDebitos2 = new JRBeanCollectionDataSource(comprasColeccionNCND);
							if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
								parametrosMap.put("pathsubreportResumenNCND", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/compras/RPComprasPorIVAyRetencionNCResumen.jasper");
							else 
								throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");					
							parametrosMap.put("dataSourceResumenNCND", dataSourceDebitos2);
							
						}
						else//no hay notas de credito
						{
							
						}
					}
					else{					
						
					}
					
					if(tipo.equals("CC"))
					{
						ReportModelImpl.processReport(fileName, parametrosMap, compraConsolidadoColeccion, true); 
						//ReportModelImpl.processReport(fileName, parametrosMap, (DefaultTableModel) getTblFacturacion().getModel(), true);
					}
					else{						
						ReportModelImpl.processReport(fileName, parametrosMap, comprasColeccion, true);			
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
	
	public Vector<CompraDetalleIf> quitarDuplicadosCompraDetalle(Vector<CompraDetalleIf> compraDetalleVector){
		Map<Long,CompraDetalleIf> mapaTemp = new HashMap<Long,CompraDetalleIf>();
		for(int i=0; i<compraDetalleVector.size(); i++){
			mapaTemp.put(compraDetalleVector.get(i).getId(), compraDetalleVector.get(i));
		}
		compraDetalleVector = null;
		compraDetalleVector = new Vector<CompraDetalleIf>();
		Iterator mapaTempIt = mapaTemp.keySet().iterator();
		while(mapaTempIt.hasNext()){
			Long compraDetalleId = (Long)mapaTempIt.next();
			compraDetalleVector.add(mapaTemp.get(compraDetalleId));
		}
		return compraDetalleVector;
	}
	
	public Vector<CompraRetencionIf> quitarDuplicadosCompraRetencion(Vector<CompraRetencionIf> compraRetencionVector){
		Map<Long,CompraRetencionIf> mapaTemp = new HashMap<Long,CompraRetencionIf>();
		for(int i=0; i<compraRetencionVector.size(); i++){
			mapaTemp.put(compraRetencionVector.get(i).getId(), compraRetencionVector.get(i));
		}
		compraRetencionVector = null;
		compraRetencionVector = new Vector<CompraRetencionIf>();
		Iterator mapaTempIt = mapaTemp.keySet().iterator();
		while(mapaTempIt.hasNext()){
			Long compraRetencionId = (Long)mapaTempIt.next();
			compraRetencionVector.add(mapaTemp.get(compraRetencionId));
		}
		return compraRetencionVector;
	}
	
	
	public void generarColeccionCompras(){
		try {
			comprasMap = null;
			comprasMap = new HashMap();
			if(clienteOficinaIf != null){
				comprasMap.put("proveedorId", clienteOficinaIf.getId());
			}
			
			Long idIva=null;
			Long idRenta=null;
			
			SriIvaRetencionIf iva1 = ((SriIvaRetencionIf) this.getCbCodigoIVA().getSelectedItem());
			if(iva1.getId()!=null)
			{
				idIva=iva1.getId();
			}
			
			SriAirIf renta1 = ((SriAirIf) this.getCbCodigoRenta().getSelectedItem());
			if(renta1.getId()!=null)
			{
				idRenta=renta1.getId();
			}
			
			String txtretencionNumero="";
			if(getTxtRetencionNumero().getText()!=null && !getTxtRetencionNumero().getText().equals(""))
				txtretencionNumero=getTxtRetencionNumero().getText();
				
			
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
			
			//ArrayList compras = (ArrayList)SessionServiceLocator.getCompraSessionService().findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFin(comprasMap, fechaInicio, fechaFin, getCbOrdenarPorSecuencialRetencion().isSelected(),idIva,idRenta,txtretencionNumero);			
			ArrayList compras = (ArrayList)SessionServiceLocator.getCompraSessionService().findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFinEmpresaId(comprasMap, fechaInicio, fechaFin, getCbOrdenarPorSecuencialRetencion().isSelected(),idIva,idRenta,txtretencionNumero,Parametros.getIdEmpresa());
						
			//ArrayList comprasRetencionesAnuladas = (ArrayList)SessionServiceLocator.getCompraSessionService().findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFinRetencionesAnuladas(comprasMap, fechaInicio, fechaFin, getCbOrdenarPorSecuencialRetencion().isSelected(),idIva,idRenta,txtretencionNumero);
			ArrayList comprasRetencionesAnuladas = (ArrayList)SessionServiceLocator.getCompraSessionService().findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFinRetencionesAnuladasEmpresaId(comprasMap, fechaInicio, fechaFin, getCbOrdenarPorSecuencialRetencion().isSelected(),idIva,idRenta,txtretencionNumero,Parametros.getIdEmpresa());
			
			ArrayList comprasSinRetencion = (ArrayList) SessionServiceLocator.getCompraSessionService().findComprasAndCompraDetalleByQueryByFechaInicioAndByFechaFinEmpresaId(comprasMap, fechaInicio, fechaFin, Parametros.getIdEmpresa());
			
			cargarMapasCompras(compras);
			cargarMapasComprasAnuladas(comprasRetencionesAnuladas);
			cargarMapasComprasSinRetencion(comprasSinRetencion);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}		
	}
	
	
	public void cargarMapasComprasAnuladas(ArrayList comprasAnuladas){
		
		mapaCompraAnuladas=null;
		mapaCompraAnuladas = new LinkedMap();
		compraRetencionAnuladasVector = null;
		compraRetencionAnuladasVector = new Vector<LogCompraRetencionIf>();
		
		Iterator comprasIt = comprasAnuladas.iterator();
		while (comprasIt.hasNext()) {
			Object[] comprasObject = (Object[]) comprasIt.next();
			CompraIf compraIf = (CompraIf) comprasObject[0];
			mapaCompraAnuladas.put(compraIf.getId(),compraIf);
		  if(comprasObject[3]!=null)
			{
				LogCompraRetencionIf logCompraRetencionIf = (LogCompraRetencionIf) comprasObject[3];
				 compraRetencionAnuladasVector.add(logCompraRetencionIf); 
			}  
		}
		
		if(compraRetencionAnuladasVector.size() > 0){
			Collections.sort(compraRetencionAnuladasVector,ordenadorAnuladasPorSecuencial);
		 
		}
		
		Iterator mapaCompraAnuladaIt = mapaCompraAnuladas.keySet().iterator();
		while(mapaCompraAnuladaIt.hasNext()){
			Long compraId = (Long)mapaCompraAnuladaIt.next();
			CompraIf compraIf = (CompraIf)mapaCompraAnuladas.get(compraId);
			Map<String,Object[]> mapaCodigoRetencionAnu = new HashMap<String,Object[]>();
			for(int j=0; j<compraRetencionAnuladasVector.size(); j++){
				LogCompraRetencionIf logCompraRetencionIf = compraRetencionAnuladasVector.get(j);
				Object[] retencionAnuladas = new Object[4];
				if(logCompraRetencionIf.getCompraId().equals(compraIf.getId())){
					retencionAnuladas[0] = logCompraRetencionIf.getImpuesto();
					retencionAnuladas[1] = logCompraRetencionIf.getPorcentajeRetencion();
					retencionAnuladas[2] = logCompraRetencionIf.getValorRetenido();
					retencionAnuladas[3] = logCompraRetencionIf.getSecuencial();
					mapaCodigoRetencionAnu.put(logCompraRetencionIf.getCodigoImpuesto()+"/"+logCompraRetencionIf.getSecuencial(),retencionAnuladas);
				}
			}
			mapaCompraRetencionesAnuladas.put(compraId, mapaCodigoRetencionAnu);			
		} 
		
	}
	
	public void cargarMapasCompras(ArrayList compras){
		mapaCompra = null;
		mapaCompra = new LinkedMap();
		compraDetalleVector = null;
		compraDetalleVector = new Vector<CompraDetalleIf>();
		compraRetencionVector = null;
		compraRetencionVector = new Vector<CompraRetencionIf>();
		 
		Iterator comprasIt = compras.iterator();
		while (comprasIt.hasNext()) {
			Object[] comprasObject = (Object[]) comprasIt.next();
			CompraIf compraIf = (CompraIf) comprasObject[0];
			mapaCompra.put(compraIf.getId(),compraIf);
			CompraDetalleIf compraDetalleIf = (CompraDetalleIf) comprasObject[1];
			compraDetalleVector.add(compraDetalleIf);
			CompraRetencionIf compraRetencionIf = (CompraRetencionIf) comprasObject[2];
			compraRetencionVector.add(compraRetencionIf);			
		}
				
		compraDetalleVector = quitarDuplicadosCompraDetalle(compraDetalleVector);
		compraRetencionVector = quitarDuplicadosCompraRetencion(compraRetencionVector);		
		
		if(compraRetencionVector.size() > 0){
			Collections.sort(compraRetencionVector,ordenadorPorSecuencial);
			secuencialInicio = getPrimerSecuencial(compraRetencionVector);
			secuencialFin = compraRetencionVector.get(compraRetencionVector.size()-1).getSecuencial();
		}
	 
		cargarMapaCompraBases();
	}
	
	public void cargarMapasComprasSinRetencion(ArrayList compras) { 
		Iterator comprasIt = compras.iterator();
		while (comprasIt.hasNext()) {
			Object[] comprasObject = (Object[]) comprasIt.next();
			CompraIf compraIf = (CompraIf) comprasObject[0];
			mapaCompra.put(compraIf.getId(),compraIf);
			CompraDetalleIf compraDetalleIf = (CompraDetalleIf) comprasObject[1];
			compraDetalleVector.add(compraDetalleIf);
		}
				
		compraDetalleVector = quitarDuplicadosCompraDetalle(compraDetalleVector);
	}
	
	public String getPrimerSecuencial(Vector<CompraRetencionIf> compraRetencionVector){
		String primerSecuencial = "";
		for(CompraRetencionIf compraRetencion : compraRetencionVector){
			if(!compraRetencion.getSecuencial().equals("0")){
				return compraRetencion.getSecuencial();
			}
		}
		return primerSecuencial;
	}
	
	Comparator<CompraRetencionIf> ordenadorPorSecuencial = new Comparator<CompraRetencionIf>(){
		public int compare(CompraRetencionIf o1, CompraRetencionIf o2) {
			return (o1.getSecuencial().compareTo(o2.getSecuencial()));
		}		
	};
	
	
	Comparator<LogCompraRetencionIf> ordenadorAnuladasPorSecuencial = new Comparator<LogCompraRetencionIf>(){
		public int compare(LogCompraRetencionIf o1, LogCompraRetencionIf o2) {
			return (o1.getSecuencial().compareTo(o2.getSecuencial()));
		}		
	};
	
	Comparator<ComprasIvaRetencionConsolidadoData> ordenadorConsolidadoPorSecuencial = new Comparator<ComprasIvaRetencionConsolidadoData>(){
		public int compare(ComprasIvaRetencionConsolidadoData o1, ComprasIvaRetencionConsolidadoData o2) {
			return (o1.getRetencionRenta().compareTo(o2.getRetencionRenta()));
		}		
	};
	
	
	
	public void cargarMapaCompraBases(){
		Map<Long,BigDecimal[]> mapaRentasBases = new HashMap<Long,BigDecimal[]>();
		Map<Long,BigDecimal> mapaIvasBases = new HashMap<Long,BigDecimal>();
		mapaCompraBases = null;
		mapaCompraBases = new HashMap<Long,BigDecimal[]>();
		mapaCompraRetenciones = null;
		mapaCompraRetenciones = new HashMap<Long,Map<String,Object[]>>();
		double sumaBase12 = 0D;
		double sumaBase0 = 0D;
		Iterator mapaCompraIt = mapaCompra.keySet().iterator();
		
		mapaContadorRegistros.clear();
		
		mapaCamposValoresMeses309.clear();
		
		while(mapaCompraIt.hasNext()){
			Long compraId = (Long)mapaCompraIt.next();
			CompraIf compraIf = (CompraIf)mapaCompra.get(compraId);
			//Cargo mapa que relaciona compra con base 12 y base 0
			sumaBase12 = 0D;
			sumaBase0 = 0D;
			BigDecimal[] sumaBases = new BigDecimal[2];
			
			for(int i=0; i<compraDetalleVector.size(); i++){
				CompraDetalleIf compraDetalleIf = compraDetalleVector.get(i);
				if(compraDetalleIf.getCompraId().equals(compraIf.getId()) && !compraIf.getEstado().equals(ESTADO_ANULADO)){
					ProductoIf producto = mapaProducto.get(compraDetalleIf.getProductoId());
					GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
					
					double subtotal = compraDetalleIf.getValor().doubleValue() * compraDetalleIf.getCantidad();
					double porcentajeDescuentoEspecial = compraDetalleIf.getPorcentajeDescuentoEspecial().doubleValue() / 100;
					double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
					double porcentajeDescuentosVarios = compraDetalleIf.getPorcentajeDescuentosVarios().doubleValue() / 100;
					double descuentosVarios = (subtotal - descuentoEspecial) * porcentajeDescuentosVarios;
					double subtotal2 = subtotal - descuentoEspecial - compraDetalleIf.getDescuento().doubleValue() - descuentosVarios;
										
					if(generico.getCobraIva().equals(COBRA_IVA_NO)){
						
						sumaBase0 = sumaBase0 + subtotal2;
												
						if(compraDetalleIf.getIdSriAir() != null){
							if(mapaRentasBases.get(compraDetalleIf.getIdSriAir()) == null){
								mapaRentasBases.put(compraDetalleIf.getIdSriAir(),new BigDecimal[]{new BigDecimal(0),BigDecimal.valueOf(subtotal2)});
							}else{
								BigDecimal[] rentaBases = mapaRentasBases.get(compraDetalleIf.getIdSriAir());
								rentaBases[1] = rentaBases[1].add(BigDecimal.valueOf(subtotal2));
								mapaRentasBases.put(compraDetalleIf.getIdSriAir(),rentaBases);
							}							
						}
						
					}else{
						sumaBase12 = sumaBase12 + subtotal2;
						if(compraDetalleIf.getIdSriAir() != null){
							if(mapaRentasBases.get(compraDetalleIf.getIdSriAir()) == null){
								mapaRentasBases.put(compraDetalleIf.getIdSriAir(), new BigDecimal[]{BigDecimal.valueOf(subtotal2),new BigDecimal(0)});
							}else{
								BigDecimal[] rentaBases = mapaRentasBases.get(compraDetalleIf.getIdSriAir());
								rentaBases[0] = rentaBases[0].add(BigDecimal.valueOf(subtotal2));
								mapaRentasBases.put(compraDetalleIf.getIdSriAir(), rentaBases);
							}
						}						
					}
					 
					if(compraDetalleIf.getSriIvaRetencionId() != null){
						if(mapaIvasBases.get(compraDetalleIf.getSriIvaRetencionId()) == null){
							mapaIvasBases.put(compraDetalleIf.getSriIvaRetencionId(), compraDetalleIf.getIva());
						}else{
							mapaIvasBases.put(compraDetalleIf.getSriIvaRetencionId(), mapaIvasBases.get(compraDetalleIf.getSriIvaRetencionId()).add(compraDetalleIf.getIva()));
						}
					}
				}
			}
			
			
			sumaBases[0] = BigDecimal.valueOf(sumaBase12);
			sumaBases[1] = BigDecimal.valueOf(sumaBase0);
			mapaCompraBases.put(compraId, sumaBases);
			
			Map<String,Object[]> mapaCodigoRetencion = new HashMap<String,Object[]>();
			for(int j=0; j<compraRetencionVector.size(); j++){
				CompraRetencionIf compraRetencionIf = compraRetencionVector.get(j);
				Object[] retencion = new Object[4];
				if(compraRetencionIf.getCompraId().equals(compraIf.getId())){
					retencion[0] = compraRetencionIf.getImpuesto();
					retencion[1] = compraRetencionIf.getPorcentajeRetencion();
					retencion[2] = compraRetencionIf.getValorRetenido();
					retencion[3] = compraRetencionIf.getSecuencial();

					sumarmapaContadorRegistros(compraRetencionIf.getImpuesto()+"-"+compraRetencionIf.getCodigoImpuesto());
					mapaCodigoRetencion.put(compraRetencionIf.getCodigoImpuesto(),retencion);
					
					
					if(compraRetencionIf.getCodigoImpuesto().equals("309"))
							{
						 
						Map<String,BigDecimal> mapaMesValor = new HashMap<String,BigDecimal>();				
						if((mapaCamposValoresMeses309.get(compraRetencionIf.getCodigoImpuesto()) == null)){
							mapaMesValor.put("0", compraRetencionIf.getValorRetenido());
							mapaCamposValoresMeses309.put(compraRetencionIf.getCodigoImpuesto(),mapaMesValor);
							
						} else {					
							Iterator mapaMesValorIt = mapaCamposValoresMeses309.get(compraRetencionIf.getCodigoImpuesto()).keySet().iterator();
							while(mapaMesValorIt.hasNext()){
								String key = (String)mapaMesValorIt.next();
								mapaMesValor.put(key,mapaCamposValoresMeses309.get(compraRetencionIf.getCodigoImpuesto()).get(key));
							}			
							//BigDecimal nuevovalor=mapaIvasBases.get(compraRetencionIf.getCodigoImpuesto()).add(compraRetencionIf.getValorRetenido());
							BigDecimal nuevovalor=compraRetencionIf.getValorRetenido();
							if (mapaCamposValoresMeses309.get(compraRetencionIf.getCodigoImpuesto()).get("0") == null) {
								mapaMesValor.put("0",nuevovalor );				
								mapaCamposValoresMeses309.put(compraRetencionIf.getCodigoImpuesto(),mapaMesValor);					
							} else {
								mapaMesValor.put("0", nuevovalor.add(mapaCamposValoresMeses309.get(compraRetencionIf.getCodigoImpuesto()).get("0")));				
								mapaCamposValoresMeses309.put(compraRetencionIf.getCodigoImpuesto(),mapaMesValor);					
							} 
						}
					}					
				}
			}
			
			mapaCompraRetenciones.put(compraId, mapaCodigoRetencion);
		}
		
		generarComprasResumenColeccion(mapaRentasBases, mapaIvasBases);
	}
	
	public void generarComprasResumenColeccion(Map<Long,BigDecimal[]> mapaRentasBases, Map<Long,BigDecimal> mapaIvasBases){
		Iterator mapaRentasBasesIt = mapaRentasBases.keySet().iterator();
		BigDecimal sumaBases = new BigDecimal(0);
		
		BigDecimal valor309BaseOriginal=new BigDecimal(0);
		BigDecimal valor309RetenidoOriginal=new BigDecimal(0);
		
		while(mapaRentasBasesIt.hasNext()){
		 	
			Long llaveSriAirId = (Long)mapaRentasBasesIt.next();
			BigDecimal[] rentaBases = mapaRentasBases.get(llaveSriAirId);
			ComprasIvaRetencionResumenData compraResumenData = new ComprasIvaRetencionResumenData();
			compraResumenData.setImpuesto(IMPUESTO_RENTA);
			compraResumenData.setBaseIva12(formatoDecimal.format(rentaBases[0]));
			compraResumenData.setBaseIva0(formatoDecimal.format(rentaBases[1]));
			SriAirIf sriAir = mapaCodigoRetencionRenta.get(llaveSriAirId);
			compraResumenData.setCodigo(sriAir.getCodigo());
			compraResumenData.setIva(formatoDecimal.format(new BigDecimal(0)));
			compraResumenData.setPorcentaje(sriAir.getPorcentaje().toString());
			sumaBases = rentaBases[0].add(rentaBases[1]);
			compraResumenData.setValorRetenido(formatoDecimal.format(sumaBases.multiply(sriAir.getPorcentaje().divide(new BigDecimal(100)))));
			
			if(sriAir.getCodigo().equals("309"))
				{					
					valor309BaseOriginal=rentaBases[0];	
					
					
					valor309RetenidoOriginal=new BigDecimal(formatoDecimal.format(sumaBases.multiply(sriAir.getPorcentaje().divide(new BigDecimal(100)))).replaceAll( ",", "" ));
					
					
				}
			 
			compraResumenData.setNumReg("-");
		 	
			if (mapaContadorRegistros.get("R-"+compraResumenData.getCodigo()) != null) {
				compraResumenData.setNumReg(mapaContadorRegistros.get("R-"+compraResumenData.getCodigo()));
			}
			
			comprasResumenColeccion.add(compraResumenData);
		}
		
		if(mapaCamposValoresMeses309.get("309")!=null){			
			
			BigDecimal valor309BaseAjustado=new BigDecimal(0);
			BigDecimal valor309RetenidoAjustado=new BigDecimal(0);			
			valor309RetenidoAjustado=mapaCamposValoresMeses309.get("309").get("0");			
			valor309BaseAjustado=valor309RetenidoAjustado.multiply(new BigDecimal("100"));
			valor309RetenidoAjustado=valor309RetenidoAjustado.subtract(valor309RetenidoOriginal);
			valor309BaseAjustado=valor309BaseAjustado.subtract(valor309BaseOriginal);
			ComprasIvaRetencionResumenData compraResumenData = new ComprasIvaRetencionResumenData();
			compraResumenData.setImpuesto(IMPUESTO_RENTA);
			compraResumenData.setBaseIva12(valor309BaseAjustado.toString());
			compraResumenData.setBaseIva0(formatoDecimal.format(new BigDecimal(0)));			
			compraResumenData.setCodigo("309(AJ)");
			compraResumenData.setIva(formatoDecimal.format(new BigDecimal(0)));
			compraResumenData.setPorcentaje("1");
			compraResumenData.setValorRetenido(valor309RetenidoAjustado.toString());			
			if(valor309BaseAjustado.doubleValue()!=0D && valor309RetenidoAjustado.doubleValue()!=0D)
						comprasResumenColeccion.add(compraResumenData);			
		}
		
		Iterator mapaIvasBasesIt = mapaIvasBases.keySet().iterator();
		while(mapaIvasBasesIt.hasNext()){
			
			Long llaveSriIvaRetencionId = (Long)mapaIvasBasesIt.next();
			BigDecimal valorRetenido = mapaIvasBases.get(llaveSriIvaRetencionId);
			ComprasIvaRetencionResumenData compraResumenData = new ComprasIvaRetencionResumenData();
			compraResumenData.setImpuesto(IMPUESTO_IVA);
			compraResumenData.setBaseIva12(formatoDecimal.format(new BigDecimal(0)));
			compraResumenData.setBaseIva0(formatoDecimal.format(new BigDecimal(0)));
			SriIvaRetencionIf sriIvaRetencion = mapaCodigoRetencionIva.get(llaveSriIvaRetencionId);
			compraResumenData.setCodigo(sriIvaRetencion.getCodigo().equals("NONE")?"NO APLICA":sriIvaRetencion.getCodigo());
			compraResumenData.setIva(formatoDecimal.format(valorRetenido));
			compraResumenData.setPorcentaje(sriIvaRetencion.getPorcentaje().toString());
			compraResumenData.setValorRetenido(formatoDecimal.format(valorRetenido.multiply(sriIvaRetencion.getPorcentaje()).divide(new BigDecimal(100))));
			
			compraResumenData.setNumReg("-");
			if (mapaContadorRegistros.get("I-"+compraResumenData.getCodigo()) != null) {
				compraResumenData.setNumReg(mapaContadorRegistros.get("I-"+compraResumenData.getCodigo()));
			}
			
			 
			comprasResumenColeccion.add(compraResumenData);
		}
		Collections.sort(comprasResumenColeccion,ordenadorArrayListPorCodigo);
	}
	
	Comparator<ComprasIvaRetencionResumenData> ordenadorArrayListPorCodigo = new Comparator<ComprasIvaRetencionResumenData>(){
		public int compare(ComprasIvaRetencionResumenData o1, ComprasIvaRetencionResumenData o2) {
			return (o1.getCodigo().compareTo(o2.getCodigo()));
		}		
	};
	
	
	//($F{reembolso}.equals("0.00")?(!$F{iva}.equals("")?new Double(Double.parseDouble($F{iva}.replaceAll( ",", "" ))):new Double(0D)):new Double(0D))	
	private void columnasConsolidado(){
		if(tipo.equals("C"))
		{
			Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");		 
			JLabel headerLabel1 = new JLabel("#Factura", JLabel.CENTER);		
			headerLabel1.setBorder(headerBorder);
			JLabel headerLabel2 = new JLabel("FechaEmisión", JLabel.CENTER);		
			headerLabel2.setBorder(headerBorder);
			JLabel headerLabel3 = new JLabel("Proveedor", JLabel.CENTER);		
			headerLabel3.setBorder(headerBorder);
			JLabel headerLabel4 = new JLabel("A", JLabel.CENTER);		
			headerLabel4.setBorder(headerBorder);
			JLabel headerLabel5 = new JLabel("Exterior", JLabel.CENTER);		
			headerLabel5.setBorder(headerBorder);
			JLabel headerLabel6 = new JLabel("Reembolso", JLabel.CENTER);		
			headerLabel6.setBorder(headerBorder);
			JLabel headerLabel7 = new JLabel("Reposición", JLabel.CENTER);		
			headerLabel7.setBorder(headerBorder);
			JLabel headerLabel37 = new JLabel("Normal", JLabel.CENTER);		
			headerLabel37.setBorder(headerBorder);
			JLabel headerLabel8 = new JLabel("Base IVA 12%", JLabel.CENTER);		
			headerLabel8.setBorder(headerBorder);
			JLabel headerLabel38 = new JLabel("Base IVA 0%", JLabel.CENTER);		
			headerLabel38.setBorder(headerBorder);
			JLabel headerLabel39 = new JLabel("IVA", JLabel.CENTER);		
			headerLabel39.setBorder(headerBorder);
			JLabel headerLabel9 = new JLabel("TOTAL US$", JLabel.CENTER);		
			headerLabel9.setBorder(headerBorder);
			JLabel headerLabel10 = new JLabel("Imp.", JLabel.CENTER);		
			headerLabel10.setBorder(headerBorder);
			JLabel headerLabel11 = new JLabel("Código", JLabel.CENTER);		
			headerLabel11.setBorder(headerBorder);
			JLabel headerLabel12 = new JLabel("%", JLabel.CENTER);		
			headerLabel12.setBorder(headerBorder);
			JLabel headerLabel13 = new JLabel("Val Ret.", JLabel.CENTER);		
			headerLabel13.setBorder(headerBorder);
			JLabel headerLabel14 = new JLabel("#Ret.", JLabel.CENTER);
			headerLabel14.setBorder(headerBorder);
			
			TableCellRenderer renderer = new JComponentTableCellRenderer();
			getTblCompras().getColumnModel().getColumn(0).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(0).setHeaderValue(headerLabel1);
			getTblCompras().getColumnModel().getColumn(1).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(1).setHeaderValue(headerLabel2);
			getTblCompras().getColumnModel().getColumn(2).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(2).setHeaderValue(headerLabel3);
			getTblCompras().getColumnModel().getColumn(3).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(3).setHeaderValue(headerLabel4);
			getTblCompras().getColumnModel().getColumn(4).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(4).setHeaderValue(headerLabel5);
			getTblCompras().getColumnModel().getColumn(5).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(5).setHeaderValue(headerLabel6);
			getTblCompras().getColumnModel().getColumn(6).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(6).setHeaderValue(headerLabel7);
			
			getTblCompras().getColumnModel().getColumn(7).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(7).setHeaderValue(headerLabel37);
			
			getTblCompras().getColumnModel().getColumn(8).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(8).setHeaderValue(headerLabel8);
			
			getTblCompras().getColumnModel().getColumn(9).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(9).setHeaderValue(headerLabel38);
			getTblCompras().getColumnModel().getColumn(10).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(10).setHeaderValue(headerLabel39);
			
			getTblCompras().getColumnModel().getColumn(11).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(11).setHeaderValue(headerLabel9);
			getTblCompras().getColumnModel().getColumn(12).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(12).setHeaderValue(headerLabel10);
			getTblCompras().getColumnModel().getColumn(13).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(13).setHeaderValue(headerLabel11);
			getTblCompras().getColumnModel().getColumn(14).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(14).setHeaderValue(headerLabel12);
			getTblCompras().getColumnModel().getColumn(15).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(15).setHeaderValue(headerLabel13);
			getTblCompras().getColumnModel().getColumn(16).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(16).setHeaderValue(headerLabel14);
		 
			TableColumn anchoColumna = getTblCompras().getColumnModel().getColumn(0);
			anchoColumna = getTblCompras().getColumnModel().getColumn(3);
			anchoColumna.setPreferredWidth(25);
			
			anchoColumna = getTblCompras().getColumnModel().getColumn(8);
			anchoColumna.setPreferredWidth(90);
			anchoColumna = getTblCompras().getColumnModel().getColumn(9);
			anchoColumna.setPreferredWidth(100);
			anchoColumna = getTblCompras().getColumnModel().getColumn(10);
			anchoColumna.setPreferredWidth(80);
			anchoColumna = getTblCompras().getColumnModel().getColumn(11);
			anchoColumna.setPreferredWidth(80);
			anchoColumna = getTblCompras().getColumnModel().getColumn(12);
			anchoColumna.setPreferredWidth(100);			 
			
			anchoColumna = getTblCompras().getColumnModel().getColumn(13);
			anchoColumna.setPreferredWidth(80);
			anchoColumna = getTblCompras().getColumnModel().getColumn(14);
			anchoColumna.setPreferredWidth(80);
			anchoColumna = getTblCompras().getColumnModel().getColumn(15);
			anchoColumna.setPreferredWidth(80);
			anchoColumna = getTblCompras().getColumnModel().getColumn(16);
			anchoColumna.setPreferredWidth(80);
			
		}
		
		if(tipo.equals("CC"))
		{
			Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");		 
			JLabel headerLabel1 = new JLabel("#Factura", JLabel.CENTER);		
			headerLabel1.setBorder(headerBorder);
			JLabel headerLabel2 = new JLabel("FechaEmisión", JLabel.CENTER);		
			headerLabel2.setBorder(headerBorder);
			JLabel headerLabel3 = new JLabel("Proveedor", JLabel.CENTER);		
			headerLabel3.setBorder(headerBorder);
			JLabel headerLabel4 = new JLabel("Estado", JLabel.CENTER);		
			headerLabel4.setBorder(headerBorder);
			JLabel headerLabel5 = new JLabel("# Ret.", JLabel.CENTER);		
			headerLabel5.setBorder(headerBorder);
			JLabel headerLabel6 = new JLabel("Ret.AIR.US$", JLabel.CENTER);		
			headerLabel6.setBorder(headerBorder);
			JLabel headerLabel7 = new JLabel("Cód.Ret.", JLabel.CENTER);		
			headerLabel7.setBorder(headerBorder);
			JLabel headerLabel8 = new JLabel("Ret.IVA.US$", JLabel.CENTER);		
			headerLabel8.setBorder(headerBorder);
			JLabel headerLabel9 = new JLabel("Cód.Ret.", JLabel.CENTER);		
			headerLabel9.setBorder(headerBorder);
			 	
			
			TableCellRenderer renderer = new JComponentTableCellRenderer();
			getTblCompras().getColumnModel().getColumn(0).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(0).setHeaderValue(headerLabel1);
			getTblCompras().getColumnModel().getColumn(1).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(1).setHeaderValue(headerLabel2);
			getTblCompras().getColumnModel().getColumn(2).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(2).setHeaderValue(headerLabel3);
			getTblCompras().getColumnModel().getColumn(3).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(3).setHeaderValue(headerLabel4);
			getTblCompras().getColumnModel().getColumn(4).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(4).setHeaderValue(headerLabel5);
			getTblCompras().getColumnModel().getColumn(5).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(5).setHeaderValue(headerLabel6);
			getTblCompras().getColumnModel().getColumn(6).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(6).setHeaderValue(headerLabel7);
			getTblCompras().getColumnModel().getColumn(7).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(7).setHeaderValue(headerLabel8);
			getTblCompras().getColumnModel().getColumn(8).setHeaderRenderer(renderer); 
			getTblCompras().getColumnModel().getColumn(8).setHeaderValue(headerLabel9);
			 
			TableColumn anchoColumna = getTblCompras().getColumnModel().getColumn(0);
			
			anchoColumna = getTblCompras().getColumnModel().getColumn(9);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);
			anchoColumna.setPreferredWidth(0);
			
			
			anchoColumna = getTblCompras().getColumnModel().getColumn(3);
			anchoColumna.setPreferredWidth(55);
			
			anchoColumna = getTblCompras().getColumnModel().getColumn(10);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);
			anchoColumna.setPreferredWidth(0);
			
			anchoColumna = getTblCompras().getColumnModel().getColumn(11);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);
			anchoColumna.setPreferredWidth(0);
			
			anchoColumna = getTblCompras().getColumnModel().getColumn(12);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);
			anchoColumna.setPreferredWidth(0);
			
			anchoColumna = getTblCompras().getColumnModel().getColumn(13);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);
			anchoColumna.setPreferredWidth(0);
			
			anchoColumna = getTblCompras().getColumnModel().getColumn(14);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);
			anchoColumna.setPreferredWidth(0);
			
			anchoColumna = getTblCompras().getColumnModel().getColumn(15);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);		
			anchoColumna.setPreferredWidth(0);
			
			anchoColumna = getTblCompras().getColumnModel().getColumn(16);
			anchoColumna.setPreferredWidth(0);
			anchoColumna.setMinWidth(0);		
			anchoColumna.setPreferredWidth(0);
		}
		
	}
	

	class JComponentTableCellRenderer implements TableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			return (JComponent)value;
		}
	}
	
	public void cargarTabla(){
		cleanTotales();
		columnasConsolidado();	
		
		generarColeccionCompras();
		int contador = 0;		
		Iterator mapaCompraIt = mapaCompra.keySet().iterator();		
		
		while(mapaCompraIt.hasNext()){
			Long compraId = (Long)mapaCompraIt.next();			
			Map<String,Object[]> mapaCodigoRetencion = mapaCompraRetenciones.get(compraId);
			contador = 0;
			if (mapaCodigoRetencion != null) {
				Iterator mapaCodigoRetencionIt = mapaCodigoRetencion.keySet().iterator();
				ComprasIvaRetencionConsolidadoData compraConsolidadoData = new ComprasIvaRetencionConsolidadoData();
				while(mapaCodigoRetencionIt.hasNext()){
					String codigoImpuesto = (String)mapaCodigoRetencionIt.next();
					Object[] retencion = mapaCodigoRetencion.get(codigoImpuesto);
					tableModel = (DefaultTableModel) getTblCompras().getModel();
					Vector<String> fila = new Vector<String>();
					ComprasIvaRetencionData compraData = new ComprasIvaRetencionData();
					agregarColumnasTabla(compraId, fila, compraData, codigoImpuesto, retencion, contador,compraConsolidadoData,false);
					tableModel.addRow(fila);
					comprasColeccion.add(compraData);
					contador++;
				}
				
				if(compraConsolidadoData.getValorIva()==null ) compraConsolidadoData.setValorIva("0.00");
				else if(compraConsolidadoData.getValorIva().equals("")) compraConsolidadoData.setValorIva("0.00");
				
				if(compraConsolidadoData.getValorRenta()==null) compraConsolidadoData.setValorRenta("0.00");
				else if(compraConsolidadoData.getValorRenta().equals("")) compraConsolidadoData.setValorRenta("0.00");
				
				if(compraConsolidadoData.getCodigoRetencionIva()==null) compraConsolidadoData.setCodigoRetencionIva("-");
				else if(compraConsolidadoData.getCodigoRetencionIva().equals("")) compraConsolidadoData.setCodigoRetencionIva("-");
				
				if(compraConsolidadoData.getCodigoRetencionRenta()==null) compraConsolidadoData.setCodigoRetencionRenta("-");
				else if(compraConsolidadoData.getCodigoRetencionRenta().equals("")) compraConsolidadoData.setCodigoRetencionRenta("-");
				
				compraConsolidadoColeccion.add(compraConsolidadoData);
			} else {
				ComprasIvaRetencionConsolidadoData compraConsolidadoData = new ComprasIvaRetencionConsolidadoData();
				tableModel = (DefaultTableModel) getTblCompras().getModel();
				Vector<String> fila = new Vector<String>();
				ComprasIvaRetencionData compraData = new ComprasIvaRetencionData();
				agregarColumnasTabla(compraId, fila, compraData, contador, compraConsolidadoData);
				tableModel.addRow(fila);
				comprasColeccion.add(compraData);
				contador++;
				compraConsolidadoColeccion.add(compraConsolidadoData);
			}
			
			Map<String,Object[]> mapaCodigoRetencionAnuladas = mapaCompraRetencionesAnuladas.get(compraId);
			if(mapaCodigoRetencionAnuladas!=null)
			{	
				contador = 0;
				Iterator mapaCodigoRetencionAnuIt = mapaCodigoRetencionAnuladas.keySet().iterator();
				
				while(mapaCodigoRetencionAnuIt.hasNext()){
					ComprasIvaRetencionConsolidadoData compraConsolidadoDataAnuladas = new ComprasIvaRetencionConsolidadoData();
					String codigoImpuesto = (String)mapaCodigoRetencionAnuIt.next();
					Object[] retencionAnuladas = mapaCodigoRetencionAnuladas.get(codigoImpuesto);
					if(codigoImpuesto.contains("/")) codigoImpuesto=codigoImpuesto.substring(0,codigoImpuesto.indexOf("/"));
					
					tableModel = (DefaultTableModel) getTblCompras().getModel();
					Vector<String> fila = new Vector<String>();
					ComprasIvaRetencionData compraData = new ComprasIvaRetencionData();
					agregarColumnasTabla(compraId, fila, compraData, codigoImpuesto, retencionAnuladas, contador,compraConsolidadoDataAnuladas,true);
					tableModel.addRow(fila);
					comprasColeccion.add(compraData);
					contador++;
					
				    compraConsolidadoDataAnuladas.setValorIva("0.00");
					compraConsolidadoDataAnuladas.setValorRenta("0.00");
					compraConsolidadoDataAnuladas.setCodigoRetencionIva("-");
					compraConsolidadoDataAnuladas.setCodigoRetencionRenta("-");
					
					compraConsolidadoColeccion.add(compraConsolidadoDataAnuladas);
				}
			}
			
			
			if(compraConsolidadoColeccion.size() > 0){
				Collections.sort(compraConsolidadoColeccion,ordenadorConsolidadoPorSecuencial);
			} 
		}
		 
		
		boolean anuladas=false;
		 
		if(tipo.equals("CC"))
		{	
			Vector<ComprasIvaRetencionConsolidadoData> soloRetencionesAnuladas = new Vector<ComprasIvaRetencionConsolidadoData>();
			int k=0;			
			if (compraConsolidadoColeccion.size() != 0) {
				for (int l = 0; l < compraConsolidadoColeccion.size(); l++) {					
					boolean tipoPuntos = ((ComprasIvaRetencionConsolidadoData) compraConsolidadoColeccion.get(l)).getEstado().equals("Ret.A");
					if (tipoPuntos && getCmbEstado().getSelectedItem().equals("(A) Anuladas"))
					{
						ComprasIvaRetencionConsolidadoData compraConsolidado = compraConsolidadoColeccion.get(l);	
						soloRetencionesAnuladas.add(k,compraConsolidado);
						k++;
					}
					}
			}
			if(soloRetencionesAnuladas.size()>0)
			{	
				compraConsolidadoColeccion.clear();
				DefaultTableModel model = (DefaultTableModel) getTblCompras().getModel();
				for (int i = this.getTblCompras().getRowCount(); i > 0; --i) model.removeRow(i - 1);
				
				for (int l = 0; l < soloRetencionesAnuladas.size(); l++) {
					agregarColumnasTablaConsolidadoAnuladas((ComprasIvaRetencionConsolidadoData) soloRetencionesAnuladas.get(l));
					compraConsolidadoColeccion.add((ComprasIvaRetencionConsolidadoData) soloRetencionesAnuladas.get(l));
					resumenAnuladasConsolidado((ComprasIvaRetencionConsolidadoData) soloRetencionesAnuladas.get(l));
				}
				anuladas=true;
				llenarResumenAnuladasConsolidado();
			}
		}
		else{
			Vector<ComprasIvaRetencionData> soloRetencionesAnuladas = new Vector<ComprasIvaRetencionData>();
			int k=0;			
			if (comprasColeccion.size() != 0) {
				for (int l = 0; l < comprasColeccion.size(); l++) {					
					boolean tipoPuntos = ((ComprasIvaRetencionData) comprasColeccion.get(l)).getAnulada().equals("Ret.A");
					if (tipoPuntos && getCmbEstado().getSelectedItem().equals("(A) Anuladas"))
					{
						ComprasIvaRetencionData compraConsolidado = comprasColeccion.get(l);	
						soloRetencionesAnuladas.add(k,compraConsolidado);
						k++;
					}
					}
			}
			if(soloRetencionesAnuladas.size()>0)
			{	
				comprasColeccion.clear();
				DefaultTableModel model = (DefaultTableModel) getTblCompras().getModel();
				for (int i = this.getTblCompras().getRowCount(); i > 0; --i) model.removeRow(i - 1);
				
				for (int l = 0; l < soloRetencionesAnuladas.size(); l++) {
					agregarColumnasTablaNormal((ComprasIvaRetencionData) soloRetencionesAnuladas.get(l));
					comprasColeccion.add((ComprasIvaRetencionData) soloRetencionesAnuladas.get(l));
				}
				comprasResumenColeccion.clear();
				anuladas=true;
			}
		}
		
		 
		/////aumentar al final tabla de notas de debito y credito
	 	
		//if(!anuladas && !tipo.equals("CC"))
		if(!tipo.equals("CC"))
		{
			
			if(getCkbNotasCredito().isSelected())
			{
			//****** BUSCAR LAS NOTAS DE CREDITO
			ArrayList carterap=null;
			carterap=generarColeccionNotasCreditoDebito("NCP");
			Iterator comprasIt = carterap.iterator();
			while (comprasIt.hasNext()) {
				tableModel = (DefaultTableModel) getTblCompras().getModel();
				Object[] comprasObject = (Object[]) comprasIt.next();
				CarteraIf carteraIf = (CarteraIf) comprasObject[0];				
				CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) comprasObject[1];				
				Vector<String> fila = new Vector<String>();
				agregarColumnasTablaNC_ND(fila,carteraIf,carteraDetalleIf,"NC");
			}
			
			////////////////////////////////
			ArrayList carteraND=null;
			//carteraND=generarColeccionNotasDebito();
			carteraND=generarColeccionNotasCreditoDebito("NDP");
			Iterator carteraNDIt = carteraND.iterator();
			while (carteraNDIt.hasNext()) {				
				tableModel = (DefaultTableModel) getTblCompras().getModel();
				//CarteraIf compraDetalleIf = (CarteraIf) carteraNDIt.next();
				Object[] comprasObject = (Object[]) comprasIt.next();
				CarteraIf carteraIf = (CarteraIf) comprasObject[0];
				CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) comprasObject[1];
				Vector<String> fila = new Vector<String>();
				agregarColumnasTablaNC_ND(fila,carteraIf,carteraDetalleIf,"ND");
				//agregarColumnasTablaNC_ND(fila,compraDetalleIf,"ND");
			}
			//////////////////////////////////
			}
		}
		else{
			tipo="CC";
			cleanTable();
			
			 
			 
			for(int i=0; i<compraConsolidadoColeccion.size(); i++){
				//ComprasIvaRetencionConsolidadoData compraDetalleIf = compraConsolidadoColeccion.get(i);
				tableModel = (DefaultTableModel) getTblCompras().getModel();
				agregarColumnasTablaNormalConsolidada((ComprasIvaRetencionConsolidadoData) compraConsolidadoColeccion.get(i));
			}
		//johanna
		}
	}
	
	
	public void resumenAnuladasConsolidado(ComprasIvaRetencionConsolidadoData datos)
	{
		if (mapaCamposValoresMeses.get(datos.getCodigoRetencionIva()) == null) {
				if(datos.getValorIva()==null) datos.setValorIva("0");				
				datos.setValorIva(datos.getValorIva().replace(",",""));
				mapaCamposValoresMeses.put(datos.getCodigoRetencionIva(),new BigDecimal(datos.getValorIva()));					
			} else {								
				if(datos.getValorIva()==null) datos.setValorIva("0");
				datos.setValorIva(datos.getValorIva().replace(",",""));
				mapaCamposValoresMeses.put(datos.getCodigoRetencionIva(),new BigDecimal(datos.getValorIva()).add(mapaCamposValoresMeses.get(datos.getCodigoRetencionIva())));					
			}
			
			if (mapaCamposValoresMeses.get(datos.getCodigoRetencionRenta()) == null) {
				if(datos.getValorRenta()==null) datos.setValorRenta("0");
				datos.setValorRenta(datos.getValorRenta().replace(",",""));
				mapaCamposValoresMeses.put(datos.getCodigoRetencionRenta(),new BigDecimal(datos.getValorRenta()));					
			} else {								
				if(datos.getValorRenta()==null) datos.setValorRenta("0");
				datos.setValorRenta(datos.getValorRenta().replace(",",""));
				mapaCamposValoresMeses.put(datos.getCodigoRetencionRenta(),new BigDecimal(datos.getValorRenta()).add(mapaCamposValoresMeses.get(datos.getCodigoRetencionRenta())));					
			}			
	}
	
	public void sumarmapaContadorRegistros(String codigoTipo)
	{
		if (mapaContadorRegistros.get(codigoTipo) == null) {				
				mapaContadorRegistros.put(codigoTipo,"1");					
			} else {
				mapaContadorRegistros.put(codigoTipo,new Integer(new Integer(mapaContadorRegistros.get(codigoTipo)).intValue()+1).toString());					
			}
	}
	
	public void llenarResumenAnuladasConsolidado(){
		
		comprasResumenColeccion.clear();
	 	
		//ArrayList<ComprasIvaRetencionResumenData> comprasResumenColeccion = new ArrayList<ComprasIvaRetencionResumenData>();
		//ComprasIvaRetencionResumenData compraResumenData = new ComprasIvaRetencionResumenData();
		//comprasResumenColeccion.add(compraResumenData);
 /*		Iterator mapaResumenIt = mapaCamposValoresMeses.keySet().iterator();
		while (mapaResumenIt.hasNext()) {
		ComprasIvaRetencionResumenData compraResumenData = new ComprasIvaRetencionResumenData();
		String key = (String)mapaResumenIt.next();
		//facturacionData.setCodigoRetencionIva(key);
		compraResumenData.setCodigoRetencionIva(key);
		BigDecimal val = (BigDecimal) mapaCamposValoresMeses.get(key);
		System.out.println("key!!!"+key);
		
		if(key.contains("I")){	
			
			String key2=key;
			if(key2.length()>2) key2=key2.substring(2, key2.length()-1);
			compraResumenData.setCodigoRetencionIva(key2);				
			System.out.println("KEY2-->"+key2);				
			compraResumenData.setTipoRetencion("I");
			compraResumenData.setValorIva(val.toString());
			compraResumenData.setValorRenta("0.00");
			if(compraResumenData.getValorIva().equals("")) compraResumenData.setValorIva("0.00"); 
			if(compraResumenData.getValorRenta().equals("")) compraResumenData.setValorRenta("0.00");
			comprasResumenColeccion.add(compraResumenData);
			
		}
		
		if(key.contains("R")){				
			String key2=key;
			if(key2.length()>2) key2=key2.substring(2, key2.length()-1);
			compraResumenData.setCodigoRetencionIva(key2);				
			System.out.println("KEY2 r-->"+key2);				
			compraResumenData.setTipoRetencion("R");
			compraResumenData.setValorRenta(val.toString());
			compraResumenData.setValorIva("0.00");
			if(compraResumenData.getValorIva().equals("")) compraResumenData.setValorIva("0.00"); 
			if(compraResumenData.getValorRenta().equals("")) compraResumenData.setValorRenta("0.00");
			comprasResumenColeccion.add(compraResumenData);
		}*/
		//Collections.sort(facturacionConsolidadoResumenColeccion,ordenadorArrayListPorCodigo);
	}
	
	public void agregarColumnasTablaNormalConsolidada( ComprasIvaRetencionConsolidadoData data){
		 
		Vector<String> fila = new Vector<String>();
		fila.add(data.getNumeroFactura());
		fila.add(data.getFechaEmision().toString());
		fila.add(data.getCliente());
		fila.add(data.getEstado());
		
		fila.add(data.getRetencionRenta());
		fila.add(data.getValorRenta());
		fila.add(data.getCodigoRetencionRenta());
		fila.add(data.getValorIva());
		fila.add(data.getCodigoRetencionIva());
				 
		tableModel.addRow(fila);
	}
	
	
	public void agregarColumnasTablaNormal( ComprasIvaRetencionData data){
		 
		Vector<String> fila = new Vector<String>();
		fila.add(data.getNumeroFactura());
		fila.add(data.getFechaEmision().toString());
		fila.add(data.getCliente());
		fila.add(data.getAnulada());
		fila.add(data.getExterior());
		fila.add(data.getReembolso());
		fila.add(data.getReposicion());
		fila.add(data.getNormal());
		fila.add(data.getBaseIva12());
		fila.add(data.getBaseIva0());
		fila.add(data.getIva());
		fila.add(data.getTotal());
		fila.add(data.getImpuesto());
		fila.add(data.getCodigo());
		fila.add(data.getPorcentaje());
		fila.add(data.getValorRetenido());		
				 
		tableModel.addRow(fila);
	}

	public void agregarColumnasTablaConsolidadoAnuladas( ComprasIvaRetencionConsolidadoData data){
	 
		Vector<String> fila = new Vector<String>();
		fila.add(data.getNumeroFactura());
		fila.add(data.getFechaEmision().toString());
		fila.add(data.getCliente());
		fila.add(data.getEstado());
		fila.add(data.getRetencionRenta());
		fila.add(data.getValorRenta());
		fila.add(data.getCodigoRetencionRenta());	
		fila.add(data.getValorIva());
		fila.add(data.getCodigoRetencionIva());
		 
		tableModel.addRow(fila);
	}
	

	public void agregarColumnasTablaNC_ND(Vector<String> fila, CarteraIf carteraData,CarteraDetalleIf carteradetalleData,String tipo)
	{	
		ClienteOficinaIf clienteOficina = mapaClienteOficina.get(carteraData.getClienteoficinaId());
		ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());		
		TipoDocumentoIf tipoDocumentoFactura = mapaTipoDocumento.get(carteraData.getTipodocumentoId());
		ComprasIvaRetencionData compraData = new ComprasIvaRetencionData();	
		
		fila.add(carteraData.getPreimpreso());
		fila.add(carteraData.getFechaEmision().toString());
		fila.add(cliente.getNombreLegal());
		
		boolean comision=false;		
		DocumentoIf documentoFactura = null;
		if(carteradetalleData.getDocumentoId()!=null)
		{
			documentoFactura = mapaDocumento.get(carteradetalleData.getDocumentoId());
			if(documentoFactura.getCodigo().equals("NCCG"))	comision=true;
		}
		
		if(tipo.equals("NC")) {
			//fila.add("N/C"+"**:"+carteraData.getTipodocumentoId()+"****::"+documentoFactura.getCodigo());
			fila.add("N/C");
			compraData.setAnulada("N/C");
		}
		if(tipo.equals("ND")) {
			fila.add("N/D");
			compraData.setAnulada("N/D");
		}
		
		BigDecimal conIva=carteraData.getValor();
		double sinIva=conIva.doubleValue()/1.12;
		if (tipoDocumentoFactura.getNotaCredito().equals("S")) {
			try {
				NotaCreditoIf notaCredito = SessionServiceLocator.getNotaCreditoSessionService().getNotaCredito(carteraData.getReferenciaId());
				if (notaCredito.getIva().doubleValue() == 0D)
					sinIva = conIva.doubleValue();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error al recuperar datos de N/C", SpiritAlert.ERROR);
			}
		}
		compraData.setCliente(cliente.getNombreLegal());
		compraData.setFechaEmision(Utilitarios.fromTimestampToSqlDate(carteraData.getFechaEmision()).toString());
		compraData.setNumeroFactura(carteraData.getPreimpreso());
		double ivalocal=conIva.doubleValue()-sinIva;
 
		if(comision)
		{
			fila.add(formatoDecimal.format(new BigDecimal(0)));			
			fila.add(formatoDecimal.format(new BigDecimal(0)));
			fila.add(formatoDecimal.format(new BigDecimal(0)));
			fila.add(formatoDecimal.format(new BigDecimal(0)));
			
			compraData.setExterior(formatoDecimal.format(new BigDecimal(0)));
			compraData.setNormal(formatoDecimal.format(new BigDecimal(0)));
			compraData.setReembolso(formatoDecimal.format(new BigDecimal(0)));
			compraData.setReposicion(formatoDecimal.format(conIva));
			
			ivalocal=0D;
		}
		else{
			//****
			if(tipoDocumentoFactura.getCodigo().equals(CODIGO_COMPRA_IMPORTADA)){
				fila.add(new Double(Utilitarios.redondeoValor(sinIva)).toString());
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				fila.add(formatoDecimal.format(new BigDecimal(0)));			
				compraData.setExterior(formatoDecimal.format(sinIva));			
				compraData.setReembolso(formatoDecimal.format(new BigDecimal(0)));		
				compraData.setReposicion(formatoDecimal.format(new BigDecimal(0)));
				compraData.setNormal(formatoDecimal.format(new BigDecimal(0)));		 
				//exterior=exterior-sinIva;
				exterior=exterior-new Double(Utilitarios.redondeoValor(sinIva));
				
				
				
			}else if(tipoDocumentoFactura.getCodigo().equals(CODIGO_NC_COMPRA_REEMBOLSO)){
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				fila.add(conIva.toString());
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				fila.add(formatoDecimal.format(new BigDecimal(0)));						
				compraData.setExterior(formatoDecimal.format(new BigDecimal(0)));
				compraData.setReembolso(formatoDecimal.format(conIva));
				compraData.setReposicion(formatoDecimal.format(new BigDecimal(0)));
				compraData.setNormal(formatoDecimal.format(new BigDecimal(0)));			
				//reembolso=reembolso-conIva.doubleValue();
				reembolso=reembolso-new Double(Utilitarios.redondeoValor(conIva.doubleValue()));
				
				
			}else{
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				fila.add(formatoDecimal.format(new BigDecimal(0)));
				fila.add(new Double(Utilitarios.redondeoValor(sinIva)).toString());
				fila.add(formatoDecimal.format(new BigDecimal(0)));			
				compraData.setNormal(formatoDecimal.format(sinIva));			
				compraData.setReembolso(formatoDecimal.format(new BigDecimal(0)));		
				compraData.setReposicion(formatoDecimal.format(new BigDecimal(0)));
				compraData.setExterior(formatoDecimal.format(new BigDecimal(0)));			
				//normal=normal-sinIva;
				normal=normal-new Double(Utilitarios.redondeoValor(sinIva));
			}
		}
		
		fila.add(formatoDecimal.format(new BigDecimal(0)));
		fila.add(formatoDecimal.format(new BigDecimal(0)));	
		
		if(comision)
			{
				fila.add("0.00");
				compraData.setIva("0.00");			
			}
		else 
			{
				fila.add(new Double(Utilitarios.redondeoValor(ivalocal)).toString());				
				compraData.setIva(new Double(Utilitarios.redondeoValor(ivalocal)).toString());
			}
		
		//fila.add(conIva.toString());
		fila.add(new Double(Utilitarios.redondeoValor(conIva.doubleValue())).toString());
		fila.add("");
		fila.add("");		
		fila.add("");
		fila.add("");			  
		fila.add("");
		
		
		//compraData.setTotal(conIva.toString());
		compraData.setTotal(new Double(Utilitarios.redondeoValor(conIva.doubleValue())).toString());
		//iva=iva-ivalocal;
		iva=iva-new Double(Utilitarios.redondeoValor(ivalocal));
		//total = total-conIva.doubleValue();
		total = total-new Double(Utilitarios.redondeoValor(conIva.doubleValue()));
		comprasColeccionNCND.add(compraData);
		
		
	 
		
		tableModel.addRow(fila);
		
	}
	 
	//TELEVISORA NACIONAL COMPANIA ANONIMA TELENACIONAL CA
	public ArrayList generarColeccionNotasCreditoDebito(String codigo){		
		ArrayList carterap=null;		
		try {			
			comprasMap = null;
			comprasMap = new HashMap();
			carteraVector.clear();
			
			if(clienteOficinaIf != null){
				comprasMap.put("clienteoficinaId", clienteOficinaIf.getId());
			}
			
			 //veronica
			Long tipoDocNotaCreditoProveedor=mapaTipoDocumentoCodigo.get(codigo);
			comprasMap.put("tipodocumentoId",tipoDocNotaCreditoProveedor);
		 	
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());		
			//carterap = (ArrayList)SessionServiceLocator.getCarteraSessionService().getClienteConCarterabyFechaInicioFechaFin(Parametros.getIdEmpresa(), fechaInicio, fechaFin,comprasMap);
			carterap = (ArrayList) SessionServiceLocator.getCarteraSessionService().getClienteConCarteraCarteradetallebyFechaInicioFechaFin(Parametros.getIdEmpresa(), fechaInicio, fechaFin,comprasMap);
			//getClienteConCarteraCarteradetallebyFechaInicioFechaFin
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}	
		return carterap;
	} 
	
	
	public ArrayList generarColeccionNotasDebito(){		
		ArrayList carterap=null;
		try {
			comprasMap = null;
			comprasMap = new HashMap();
			carteraVector.clear();
			if(clienteOficinaIf != null){
				comprasMap.put("clienteoficinaId", clienteOficinaIf.getId());
			}
			Long tipoDocNotaDebitoProveedor=mapaTipoDocumentoCodigo.get("NDP");				
			comprasMap.put("tipodocumentoId",tipoDocNotaDebitoProveedor);
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
			carterap = (ArrayList)SessionServiceLocator.getCarteraSessionService().getClienteConCarterabyFechaInicioFechaFin(Parametros.getIdEmpresa(), fechaInicio, fechaFin,comprasMap);								 
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}	
		return carterap;
	} 
 
	
	public void agregarColumnasTabla(Long compraId, Vector<String> fila, ComprasIvaRetencionData compraData, String codigoImpuesto, Object[] retencion, int contador,ComprasIvaRetencionConsolidadoData compraConsolidadoData,boolean retencionAnulada){
		
		try {
			CompraIf compraIf = (CompraIf)mapaCompra.get(compraId);
			ClienteOficinaIf clienteOficina = mapaClienteOficina.get(compraIf.getProveedorId());
			ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());
			
			//System.out.println("-----***************:"+tipo);
			if(contador == 0){
				TipoDocumentoIf tipoDocumentoFactura = mapaTipoDocumento.get(compraIf.getTipodocumentoId());
					
				fila.add(compraIf.getPreimpreso()!=null?compraIf.getPreimpreso():"");			
				fila.add(Utilitarios.getFechaCortaUppercase(compraIf.getFecha()));
				fila.add(cliente.getNombreLegal());
				
				compraData.setClienteId(cliente.getId().toString());
				compraData.setCliente(cliente.getNombreLegal());
				compraData.setRuc(cliente.getIdentificacion());
				compraData.setFechaEmision(Utilitarios.getFechaCortaUppercase(compraIf.getFecha()));
				compraData.setNumeroFactura(compraIf.getPreimpreso()!=null?compraIf.getPreimpreso():"");		

				compraConsolidadoData.setCliente(cliente.getNombreLegal());
				compraConsolidadoData.setClienteId(cliente.getId().toString());
				compraConsolidadoData.setFechaEmision(Utilitarios.getFechaCortaUppercase(compraIf.getFecha()));
				compraConsolidadoData.setNumeroFactura(compraIf.getPreimpreso()!=null?compraIf.getPreimpreso():"");
				
				if(compraIf.getEstado().equals(ESTADO_ANULADO)){
				
					if(retencionAnulada){
						fila.add("Ret.A");
						compraData.setAnulada("Ret.A");			
					}else{
						fila.add("A");
						compraData.setAnulada("A");			
					}
					
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setExterior(formatoDecimal.format(new BigDecimal(0)));			
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setReembolso(formatoDecimal.format(new BigDecimal(0)));		
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setReposicion(formatoDecimal.format(new BigDecimal(0)));
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setNormal(formatoDecimal.format(new BigDecimal(0)));
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setBaseIva12(formatoDecimal.format(new BigDecimal(0)));		
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setBaseIva0(formatoDecimal.format(new BigDecimal(0)));		
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setIva(formatoDecimal.format(new BigDecimal(0)));				
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setTotal(formatoDecimal.format(new BigDecimal(0)));				
					fila.add("");
					compraData.setImpuesto("");			
					fila.add("");
					compraData.setCodigo("");			
					fila.add("0");
					compraData.setPorcentaje("0");
					
					compraConsolidadoData.setValorIva("0.00");
					compraConsolidadoData.setValorRenta("0.00");
					compraConsolidadoData.setCodigoRetencionIva("-");
					compraConsolidadoData.setCodigoRetencionRenta("-");
					compraConsolidadoData.setRetencionRenta("");
					compraConsolidadoData.setEstado(compraData.getAnulada());
					
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setValorRetenido(formatoDecimal.format(new BigDecimal(0)));	
					fila.add("");
					compraData.setNumeroRetencion("");			 
					
				}else{
					
					if(retencionAnulada){
						fila.add("Ret.A");
						compraConsolidadoData.setEstado("Ret.A");
					}else{
						fila.add("");
						compraConsolidadoData.setEstado("");
					}
					
					//BigDecimal subTotal = compraIf.getValor().subtract(compraIf.getDescuento());
					double subTotal = 0D;
					
					Collection comprasDetalleCollection = SessionServiceLocator.getCompraDetalleSessionService().findCompraDetalleByCompraId(compraId);
					Iterator comprasDetalleCollectionIt = comprasDetalleCollection.iterator();
					while(comprasDetalleCollectionIt.hasNext()){
						CompraDetalleIf compraDetalleIf = (CompraDetalleIf)comprasDetalleCollectionIt.next();
						double subtotal = compraDetalleIf.getValor().doubleValue() * compraDetalleIf.getCantidad();
						double porcentajeDescuentoEspecial = compraDetalleIf.getPorcentajeDescuentoEspecial().doubleValue() / 100;
						double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
						double porcentajeDescuentosVarios = compraDetalleIf.getPorcentajeDescuentosVarios().doubleValue() / 100;
						double descuentosVarios = (subtotal - descuentoEspecial) * porcentajeDescuentosVarios;
						subTotal = subTotal + (subtotal - descuentoEspecial - compraDetalleIf.getDescuento().doubleValue() - descuentosVarios);
					}
					
					if(tipoDocumentoFactura.getCodigo().equals(CODIGO_COMPRA_IMPORTADA)){
						fila.add(formatoDecimal.format(subTotal));
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						compraData.setExterior(formatoDecimal.format(subTotal));			
						compraData.setReembolso(formatoDecimal.format(new BigDecimal(0)));		
						compraData.setReposicion(formatoDecimal.format(new BigDecimal(0)));
						compraData.setNormal(formatoDecimal.format(new BigDecimal(0)));
						
						if(!retencionAnulada){	
							iva=iva+new Double(Utilitarios.redondeoValor(compraIf.getIva().doubleValue()));
							exterior=exterior+new Double(Utilitarios.redondeoValor(subTotal));
						}
						
					}else if(tipoDocumentoFactura.getCodigo().equals(CODIGO_COMPRA_REEMBOLSO)){
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						fila.add(formatoDecimal.format(subTotal + compraIf.getIva().doubleValue()));					
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						compraData.setExterior(formatoDecimal.format(new BigDecimal(0)));			
						compraData.setReembolso(formatoDecimal.format(subTotal + compraIf.getIva().doubleValue()));			
						compraData.setReposicion(formatoDecimal.format(new BigDecimal(0)));
						compraData.setNormal(formatoDecimal.format(new BigDecimal(0)));
						
						if(!retencionAnulada){
							reembolso=reembolso+new Double(Utilitarios.redondeoValor(subTotal + compraIf.getIva().doubleValue()));	
						}
						
					}else{ 
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						fila.add(formatoDecimal.format(subTotal));
						
						compraData.setExterior(formatoDecimal.format(new BigDecimal(0)));			
						compraData.setReembolso(formatoDecimal.format(new BigDecimal(0)));		
						compraData.setReposicion(formatoDecimal.format(new BigDecimal(0)));
						compraData.setNormal(formatoDecimal.format(subTotal));
						
						if(!retencionAnulada){	
							iva=iva+new Double(Utilitarios.redondeoValor(compraIf.getIva().doubleValue()));
							normal=normal+new Double(Utilitarios.redondeoValor(subTotal));
						}					
					}	
					
					BigDecimal[] sumaBases = mapaCompraBases.get(compraId);
					
					if(tipoDocumentoFactura.getCodigo().equals(CODIGO_COMPRA_IMPORTADA)){
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						compraData.setBaseIva12(formatoDecimal.format(new BigDecimal(0)));		
						compraData.setBaseIva0(formatoDecimal.format(new BigDecimal(0)));
					}else{
						fila.add(formatoDecimal.format(sumaBases[0]));
						fila.add(formatoDecimal.format(sumaBases[1]));
						compraData.setBaseIva12(formatoDecimal.format(sumaBases[0]));		
						compraData.setBaseIva0(formatoDecimal.format(sumaBases[1]));	
					}	
					
					if(!retencionAnulada){
						total = total+new Double(Utilitarios.redondeoValor(subTotal + compraIf.getIva().doubleValue()));	
					}				
					 
					fila.add(formatoDecimal.format(compraIf.getIva()));
					fila.add(formatoDecimal.format(subTotal + compraIf.getIva().doubleValue()));	
					 
					totalTotal = totalTotal.add(BigDecimal.valueOf(subTotal + compraIf.getIva().doubleValue()));	
					
					compraData.setIva(formatoDecimal.format(compraIf.getIva()));				
					compraData.setTotal(formatoDecimal.format(subTotal + compraIf.getIva().doubleValue()));					
					
					fila.add(retencion[0].toString());
					fila.add(codigoImpuesto);						
					fila.add(retencion[1].toString());
					fila.add(formatoDecimal.format(retencion[2]));
					
					compraData.setImpuesto(retencion[0].toString());			
					compraData.setCodigo(codigoImpuesto);			
					compraData.setPorcentaje(retencion[1].toString());
					compraData.setValorRetenido(formatoDecimal.format(retencion[2]));	
					
					if(retencion[3].toString().equals("0")){
						fila.add("");
						compraData.setNumeroRetencion("");	
						compraConsolidadoData.setRetencionRenta("");
					}else{
						fila.add(retencion[3].toString());
						compraData.setNumeroRetencion(retencion[3].toString());
						compraConsolidadoData.setRetencionRenta(retencion[3].toString());
					}
					
					if(compraData.getImpuesto().equals("I")){
						compraConsolidadoData.setValorIva(compraData.getValorRetenido());
						compraConsolidadoData.setCodigoRetencionIva(compraData.getCodigo()+" ("+compraData.getPorcentaje()+"%)");
					}
					else{
						if(compraData.getImpuesto().equals("R")){ 
							compraConsolidadoData.setValorRenta(compraData.getValorRetenido());
							compraConsolidadoData.setCodigoRetencionRenta(compraData.getCodigo()+" ("+compraData.getPorcentaje()+"%)");							
						}
						else{
							compraConsolidadoData.setValorIva("0.00");
							compraConsolidadoData.setValorRenta("0.00");
							compraConsolidadoData.setCodigoRetencionIva("-");
							compraConsolidadoData.setCodigoRetencionRenta("-");	
						}	
					}
					
					compraConsolidadoData.setEstado(compraData.getAnulada());
				}
				
			}else{		 
				fila.add("");			
				fila.add("");
				fila.add("");
					
				if(retencionAnulada){
					fila.add("Ret.A");
					compraData.setAnulada("Ret.A");	
					compraConsolidadoData.setEstado("Ret.A");
				}else{
					fila.add("");
					compraData.setAnulada("");		
					compraConsolidadoData.setEstado("");
				}
				
				fila.add("");
				fila.add("");
				fila.add("");
				fila.add("");
				fila.add("");
				fila.add("");
				fila.add("");
				fila.add("");
				
				compraData.setClienteId(cliente.getId().toString());
				compraData.setCliente("");
				compraData.setRuc("");
				compraData.setFechaEmision("");
				compraData.setNumeroFactura("");
				compraData.setExterior("");			
				compraData.setReembolso("");		
				compraData.setReposicion("");
				compraData.setNormal("");			
				compraData.setBaseIva12("");		
				compraData.setBaseIva0("");		
				compraData.setIva("");				
				compraData.setTotal("");				
							
				fila.add(retencion[0].toString());
				fila.add(codigoImpuesto);						
				fila.add(retencion[1].toString());
				fila.add(formatoDecimal.format(retencion[2]));
				
				compraData.setImpuesto(retencion[0].toString());			
				compraData.setCodigo(codigoImpuesto);			
				compraData.setPorcentaje(retencion[1].toString());
				compraData.setValorRetenido(formatoDecimal.format(retencion[2]));
				
				if(retencion[3].toString().equals("0")){
					fila.add("");
					compraData.setNumeroRetencion("");
					compraConsolidadoData.setRetencionRenta("");
				}else{
					fila.add(retencion[3].toString());
					compraData.setNumeroRetencion(retencion[3].toString());
					compraConsolidadoData.setRetencionRenta(retencion[3].toString());
				}	
				
				
				if(compraData.getImpuesto().equals("I")){
					compraConsolidadoData.setValorIva(compraData.getValorRetenido());
					compraConsolidadoData.setCodigoRetencionIva(compraData.getCodigo()+" ("+compraData.getPorcentaje()+"%)");
			 	}
				else{
					if(compraData.getImpuesto().equals("R")){ 
						compraConsolidadoData.setValorRenta(compraData.getValorRetenido());
						compraConsolidadoData.setCodigoRetencionRenta(compraData.getCodigo()+" ("+compraData.getPorcentaje()+"%)");
					}
					else{
						compraConsolidadoData.setValorIva("0.00");
						compraConsolidadoData.setValorRenta("0.00");
						compraConsolidadoData.setCodigoRetencionIva("-");
						compraConsolidadoData.setCodigoRetencionRenta("-");	
					}
				}			 
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void agregarColumnasTabla(Long compraId, Vector<String> fila, ComprasIvaRetencionData compraData, int contador, ComprasIvaRetencionConsolidadoData compraConsolidadoData) {
		
		try {
			CompraIf compraIf = (CompraIf)mapaCompra.get(compraId);
			ClienteOficinaIf clienteOficina = mapaClienteOficina.get(compraIf.getProveedorId());
			ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());
			
			if (contador == 0) {
				TipoDocumentoIf tipoDocumentoFactura = mapaTipoDocumento.get(compraIf.getTipodocumentoId());
					
				fila.add(compraIf.getPreimpreso()!=null?compraIf.getPreimpreso():"");			
				fila.add(Utilitarios.getFechaCortaUppercase(compraIf.getFecha()));
				fila.add(cliente.getNombreLegal());
				
				compraData.setClienteId(cliente.getId().toString());
				compraData.setCliente(cliente.getNombreLegal());
				compraData.setRuc(cliente.getIdentificacion());
				compraData.setFechaEmision(Utilitarios.getFechaCortaUppercase(compraIf.getFecha()));
				compraData.setNumeroFactura(compraIf.getPreimpreso()!=null?compraIf.getPreimpreso():"");			

				compraConsolidadoData.setCliente(cliente.getNombreLegal());
				compraConsolidadoData.setClienteId(cliente.getId().toString());
				compraConsolidadoData.setFechaEmision(Utilitarios.getFechaCortaUppercase(compraIf.getFecha()));
				compraConsolidadoData.setNumeroFactura(compraIf.getPreimpreso()!=null?compraIf.getPreimpreso():"");
				
				if (compraIf.getEstado().equals(ESTADO_ANULADO)) {
					fila.add("A");
					compraData.setAnulada("A");			
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setExterior(formatoDecimal.format(new BigDecimal(0)));			
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setReembolso(formatoDecimal.format(new BigDecimal(0)));		
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setReposicion(formatoDecimal.format(new BigDecimal(0)));
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setNormal(formatoDecimal.format(new BigDecimal(0)));
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setBaseIva12(formatoDecimal.format(new BigDecimal(0)));		
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setBaseIva0(formatoDecimal.format(new BigDecimal(0)));		
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setIva(formatoDecimal.format(new BigDecimal(0)));				
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setTotal(formatoDecimal.format(new BigDecimal(0)));				
					fila.add("");
					compraData.setImpuesto("");			
					fila.add("");
					compraData.setCodigo("");			
					fila.add("0");
					compraData.setPorcentaje("0");
					compraConsolidadoData.setValorIva("0.00");
					compraConsolidadoData.setValorRenta("0.00");
					compraConsolidadoData.setCodigoRetencionIva("-");
					compraConsolidadoData.setCodigoRetencionRenta("-");
					compraConsolidadoData.setRetencionRenta("");
					compraConsolidadoData.setEstado(compraData.getAnulada());
					fila.add(formatoDecimal.format(new BigDecimal(0)));
					compraData.setValorRetenido(formatoDecimal.format(new BigDecimal(0)));	
					fila.add("");
					compraData.setNumeroRetencion("");
				}
				else{
					fila.add("");
					compraConsolidadoData.setEstado("");
					
					//BigDecimal subTotal = compraIf.getValor().subtract(compraIf.getDescuento());				
					double subTotal = 0D;
					
					Collection comprasDetalleCollection = SessionServiceLocator.getCompraDetalleSessionService().findCompraDetalleByCompraId(compraId);
					Iterator comprasDetalleCollectionIt = comprasDetalleCollection.iterator();
					while(comprasDetalleCollectionIt.hasNext()){
						CompraDetalleIf compraDetalleIf = (CompraDetalleIf)comprasDetalleCollectionIt.next();
						double subtotal = compraDetalleIf.getValor().doubleValue() * compraDetalleIf.getCantidad();
						double porcentajeDescuentoEspecial = compraDetalleIf.getPorcentajeDescuentoEspecial().doubleValue() / 100;
						double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
						double porcentajeDescuentosVarios = compraDetalleIf.getPorcentajeDescuentosVarios().doubleValue() / 100;
						double descuentosVarios = (subtotal - descuentoEspecial) * porcentajeDescuentosVarios;
						subTotal = subTotal + (subtotal - descuentoEspecial - compraDetalleIf.getDescuento().doubleValue() - descuentosVarios);
					}
					
					if (tipoDocumentoFactura.getCodigo().equals(CODIGO_COMPRA_IMPORTADA)) {
						fila.add(formatoDecimal.format(subTotal));
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						compraData.setExterior(formatoDecimal.format(subTotal));			
						compraData.setReembolso(formatoDecimal.format(new BigDecimal(0)));		
						compraData.setReposicion(formatoDecimal.format(new BigDecimal(0)));
						compraData.setNormal(formatoDecimal.format(new BigDecimal(0)));
						iva=iva+new Double(Utilitarios.redondeoValor(compraIf.getIva().doubleValue()));
						exterior=exterior+new Double(Utilitarios.redondeoValor(subTotal));
					} else if (tipoDocumentoFactura.getCodigo().equals(CODIGO_COMPRA_REEMBOLSO)) {
							fila.add(formatoDecimal.format(new BigDecimal(0)));
							fila.add(formatoDecimal.format(subTotal + compraIf.getIva().doubleValue()));					
							fila.add(formatoDecimal.format(new BigDecimal(0)));
							fila.add(formatoDecimal.format(new BigDecimal(0)));
							compraData.setExterior(formatoDecimal.format(new BigDecimal(0)));			
							compraData.setReembolso(formatoDecimal.format(subTotal + compraIf.getIva().doubleValue()));		
							compraData.setReposicion(formatoDecimal.format(new BigDecimal(0)));
							compraData.setNormal(formatoDecimal.format(new BigDecimal(0)));
							reembolso=reembolso+new Double(Utilitarios.redondeoValor(subTotal + compraIf.getIva().doubleValue()));		
					} else { 
							fila.add(formatoDecimal.format(new BigDecimal(0)));
							fila.add(formatoDecimal.format(new BigDecimal(0)));
							fila.add(formatoDecimal.format(new BigDecimal(0)));
							fila.add(formatoDecimal.format(subTotal));
							compraData.setExterior(formatoDecimal.format(new BigDecimal(0)));			
							compraData.setReembolso(formatoDecimal.format(new BigDecimal(0)));		
							compraData.setReposicion(formatoDecimal.format(new BigDecimal(0)));
							compraData.setNormal(formatoDecimal.format(subTotal));
							iva=iva+new Double(Utilitarios.redondeoValor(compraIf.getIva().doubleValue()));
							normal=normal+new Double(Utilitarios.redondeoValor(subTotal));					
					}	
						
					BigDecimal[] sumaBases = mapaCompraBases.get(compraId);
					
					if (sumaBases != null) {
						if(tipoDocumentoFactura.getCodigo().equals(CODIGO_COMPRA_IMPORTADA)) {
							fila.add(formatoDecimal.format(new BigDecimal(0)));
							fila.add(formatoDecimal.format(new BigDecimal(0)));
							compraData.setBaseIva12(formatoDecimal.format(new BigDecimal(0)));		
							compraData.setBaseIva0(formatoDecimal.format(new BigDecimal(0)));
						} else {
							fila.add(formatoDecimal.format(sumaBases[0]));
							fila.add(formatoDecimal.format(sumaBases[1]));
							compraData.setBaseIva12(formatoDecimal.format(sumaBases[0]));		
							compraData.setBaseIva0(formatoDecimal.format(sumaBases[1]));	
						}
					} else {
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						fila.add(formatoDecimal.format(new BigDecimal(0)));
						compraData.setBaseIva12(formatoDecimal.format(new BigDecimal(0)));		
						compraData.setBaseIva0(formatoDecimal.format(new BigDecimal(0)));
					}
								
					total = total+new Double(Utilitarios.redondeoValor(subTotal + compraIf.getIva().doubleValue()));		
					fila.add(formatoDecimal.format(compraIf.getIva()));
					fila.add(formatoDecimal.format(subTotal + compraIf.getIva().doubleValue()));		
					totalTotal = totalTotal.add(BigDecimal.valueOf(subTotal + compraIf.getIva().doubleValue()));					
					compraData.setIva(formatoDecimal.format(compraIf.getIva()));				
					compraData.setTotal(formatoDecimal.format(subTotal + compraIf.getIva().doubleValue()));					
					fila.add("");
					fila.add("");						
					fila.add("");
					fila.add("");
					compraData.setImpuesto("");			
					compraData.setCodigo("");			
					compraData.setPorcentaje("");
					compraData.setValorRetenido("");	
					fila.add("");
					compraData.setNumeroRetencion("");	
					compraConsolidadoData.setRetencionRenta("");
					if(compraData.getImpuesto().equals("I")) {
						compraConsolidadoData.setValorIva(compraData.getValorRetenido());
						compraConsolidadoData.setCodigoRetencionIva(compraData.getCodigo()+" ("+compraData.getPorcentaje()+"%)");
					} else {
						if (compraData.getImpuesto().equals("R")) { 
							compraConsolidadoData.setValorRenta(compraData.getValorRetenido());
							compraConsolidadoData.setCodigoRetencionRenta(compraData.getCodigo()+" ("+compraData.getPorcentaje()+"%)");							

						} else {
							compraConsolidadoData.setValorIva("0.00");
							compraConsolidadoData.setValorRenta("0.00");
							compraConsolidadoData.setCodigoRetencionIva("-");
							compraConsolidadoData.setCodigoRetencionRenta("-");	
						}	
					}
							
					compraConsolidadoData.setEstado(compraData.getAnulada()); 
				}	
			} else {
				fila.add("");			
				fila.add("");
				fila.add("");
				fila.add("");
				compraData.setAnulada("");		
				compraConsolidadoData.setEstado("");
				fila.add("");
				fila.add("");
				fila.add("");
				fila.add("");
				fila.add("");
				fila.add("");
				fila.add("");
				fila.add("");
				compraData.setClienteId(cliente.getId().toString());
				compraData.setCliente("");
				compraData.setRuc("");
				compraData.setFechaEmision("");
				compraData.setNumeroFactura("");
				//compraData.setAnulada("");
				compraData.setExterior("");			
				compraData.setReembolso("");		
				compraData.setReposicion("");
				compraData.setNormal("");			
				compraData.setBaseIva12("");		
				compraData.setBaseIva0("");		
				compraData.setIva("");				
				compraData.setTotal("");				
				fila.add("");
				fila.add("");						
				fila.add("");
				fila.add("");
				compraData.setImpuesto("");			
				compraData.setCodigo("");			
				compraData.setPorcentaje("");
				compraData.setValorRetenido("");
				fila.add("");
				compraData.setNumeroRetencion("");
				compraConsolidadoData.setRetencionRenta("");
				
				if(compraData.getImpuesto().equals("I")) {
					compraConsolidadoData.setValorIva(compraData.getValorRetenido());
					compraConsolidadoData.setCodigoRetencionIva(compraData.getCodigo()+" ("+compraData.getPorcentaje()+"%)");

				} else {
					if(compraData.getImpuesto().equals("R")) { 
						compraConsolidadoData.setValorRenta(compraData.getValorRetenido());
						compraConsolidadoData.setCodigoRetencionRenta(compraData.getCodigo()+" ("+compraData.getPorcentaje()+"%)");
					} else {
						compraConsolidadoData.setValorIva("0.00");
						compraConsolidadoData.setValorRenta("0.00");
						compraConsolidadoData.setCodigoRetencionIva("-");
						compraConsolidadoData.setCodigoRetencionRenta("-");	
					}
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
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
