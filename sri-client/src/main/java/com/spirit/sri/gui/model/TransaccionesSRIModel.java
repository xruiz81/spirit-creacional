package com.spirit.sri.gui.model;

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
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.commons.collections.map.LinkedMap;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.compras.entity.LogCompraRetencionIf;
import com.spirit.compras.reportesData.TransaccionesSRIData;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriClienteRetencionIf;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.sri.gui.panel.JPTransaccionesSRI;
import com.spirit.util.Utilitarios;

public class TransaccionesSRIModel extends JPTransaccionesSRI {
	
	protected ClienteOficinaIf clienteOficinaIf;
	protected ClienteIf clienteIf;
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private Map<Long,TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long,TipoDocumentoIf>();
	private Map<String,Long> mapaTipoDocumentoCodigo = new HashMap<String,Long>();
	
	private Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();	
	private Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
	private Map<Long,ProductoIf> mapaProducto = new HashMap<Long,ProductoIf>();
	private Map<Long,GenericoIf> mapaGenerico = new HashMap<Long,GenericoIf>();
	private Map<Long,SriIvaRetencionIf> mapaCodigoRetencionIva = new HashMap<Long,SriIvaRetencionIf>();
	private Map<Long,SriAirIf> mapaCodigoRetencionRenta = new HashMap<Long,SriAirIf>();
	private Map comprasMap = new HashMap();
	private LinkedMap mapaCompra = new LinkedMap();
	
	private LinkedMap mapaFacturaRetenciones = new LinkedMap();
	private Map facturasMap = new HashMap();
	private LinkedMap mapaFactura = new LinkedMap();
	private Map<Long,CarteraIf> mapaCartera = new HashMap<Long,CarteraIf>();
	private Vector<CarteraDetalleIf> carteraDetalleVector = new Vector<CarteraDetalleIf>();
	
	private static final String RETENCION_RENTA = "RETENCION_RENTA";
	private static final String RETENCION_IVA = "RETENCION_PREIMPRESO";
	private static final String RETENCION_RENTA_1 = "RETENCION_RENTA_1";
	private static final String RETENCION_RENTA_2 = "RETENCION_RENTA_2";
	private static final String RETENCION_IVA_30 = "RETENCION_IVA_30";
	private static final String RETENCION_IVA_70 = "RETENCION_IVA_70";
	private static final String RETENCION_IVA_100 = "RETENCION_IVA_100";
	private static final String TIPO_RETENCION_RENTA = "R";
	private static final String TIPO_RETENCION_IVA = "I";
	
	private Vector<CarteraIf> carteraVector = new Vector<CarteraIf>();
	
	private Vector<CompraDetalleIf> compraDetalleVector = new Vector<CompraDetalleIf>();
	private Vector<CompraRetencionIf> compraRetencionVector = new Vector<CompraRetencionIf>();	
	private static final String COBRA_IVA_NO = "N";
	private static final String ESTADO_ANULADO = "N";
	private Map<Long,BigDecimal[]> mapaCompraBases = new HashMap<Long,BigDecimal[]>();
	private Map<Long,Map<String,Object[]>> mapaCompraRetenciones = new HashMap<Long,Map<String,Object[]>>();
	private static final String CODIGO_COMPRA_REEMBOLSO = "COR";
	private static final String CODIGO_COMPRA_IMPORTADA = "COI";
	private static final String IMPUESTO_RENTA = "R";
	private static final String IMPUESTO_IVA = "I";
	private static String NOMBRE_MENU_COMPRAS = "COMPRAS";	 
	private ArrayList<ComprasIvaRetencionResumenData> comprasResumenColeccion = new ArrayList<ComprasIvaRetencionResumenData>();
	private BigDecimal totalTotal = new BigDecimal(0);
	private String secuencialInicio = "";
	private String secuencialFin = "";
	
	
	private Vector<TransaccionesSRIData> transaccionesSRIColeccion = new Vector<TransaccionesSRIData>();
	
	
	public TransaccionesSRIModel(){
		cargarMapasTipoDocumentoCliente();
		anchoColumnasTabla();
		initKeyListeners();
		showSaveMode();
		initListeners();
	}
	
	public void cargarMapasTipoDocumentoCliente(){
		try {
			
			mapaTipoDocumento.clear();
			Collection tiposDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumentoList();
			Iterator tiposDocumentoIt = tiposDocumento.iterator();
			while(tiposDocumentoIt.hasNext()){
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)tiposDocumentoIt.next();
				mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
			}
			
			
			mapaTipoDocumentoCodigo.clear();
			Collection tiposDocumentoCodigo = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumentoList();
			Iterator tiposDocumentoCodigoIt = tiposDocumentoCodigo.iterator();
			while(tiposDocumentoCodigoIt.hasNext()){
				TipoDocumentoIf tipoDocumentoCodigo = (TipoDocumentoIf)tiposDocumentoCodigoIt.next();				
				mapaTipoDocumentoCodigo.put(tipoDocumentoCodigo.getCodigo(), tipoDocumentoCodigo.getId());
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
			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	
	public void initKeyListeners(){
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnProveedor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getTxtProveedor().setEditable(false);
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
		setSorterTable(getTblCompras());
		getTblCompras().getTableHeader().setReorderingAllowed(false);
		//getTblChequesEmitidos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblCompras().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblCompras().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(82);
		anchoColumna = getTblCompras().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(82);
		anchoColumna = getTblCompras().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(110);
		anchoColumna = getTblCompras().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblCompras().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblCompras().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblCompras().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblCompras().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblCompras().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblCompras().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblCompras().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblCompras().getColumnModel().getColumn(11);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblCompras().getColumnModel().getColumn(12);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblCompras().getColumnModel().getColumn(13);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblCompras().getColumnModel().getColumn(14);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblCompras().getColumnModel().getColumn(15);
		anchoColumna.setPreferredWidth(110);
		anchoColumna = getTblCompras().getColumnModel().getColumn(16);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblCompras().getColumnModel().getColumn(17);
		anchoColumna.setPreferredWidth(85);		
	}
	
	private void initListeners() {
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cleanTable();
				
				if(clienteOficinaIf != null){
					cargarTabla();
				}
				else{
					SpiritAlert.createAlert("Debe escoger un cliente", SpiritAlert.INFORMATION);
				}
				
				
			}
		});
		
		
		getBtnProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tipoCliente = "PR";
				String tituloVentanaBusqueda = "Proveedores";
				
				String facturaCompra=getCbComprasFacturas().getSelectedItem().toString();
				if(facturaCompra.equals("COMPRAS")){
					tipoCliente = "PR";
					tituloVentanaBusqueda = "Proveedores";
				}
				else{
					tipoCliente = "CL";
					tituloVentanaBusqueda = "Clientes";
				}
				
				
				ClienteOficinaCriteria clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, "", false);
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
		
		comprasResumenColeccion = null;
		comprasResumenColeccion = new ArrayList<ComprasIvaRetencionResumenData>();
		
		transaccionesSRIColeccion.clear();
		
		totalTotal = new BigDecimal(0);
	}
	
	public void report() {		
		if(clienteOficinaIf != null){
			try {
				DefaultTableModel tblModelReporte = (DefaultTableModel) getTblCompras().getModel();
				if (tblModelReporte.getRowCount() > 0) {
					String si = "Si";
					String no = "No";
					Object[] options = {si,no};
					String mensaje = "¿Desea generar el reporte de Transacciones SRI?";
					int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if(opcion == JOptionPane.YES_OPTION) {
						String fileName = "jaspers/sri/RPTransaccionesSRI.jasper";
						String tipo="DETALLE DE VENTAS REALIZADAS A:";
						String facturaCompra=getCbComprasFacturas().getSelectedItem().toString();
						
						if(facturaCompra.equals("COMPRAS"))				 tipo="DETALLE DE COMPRAS REALIZADAS A:";
						HashMap parametrosMap = new HashMap();
						MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_COMPRAS).iterator().next();
						parametrosMap.put("codigoReporte", menu.getCodigo());
						EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
						parametrosMap.put("empresa", clienteIf.getNombreLegal());
						parametrosMap.put("ruc",clienteIf.getIdentificacion());
						parametrosMap.put("tipo",tipo);
						
						OficinaIf oficina = (OficinaIf) Parametros.getOficina();
						
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
						parametrosMap.put("blanco", " ");
						
						if(facturaCompra.equals("COMPRAS"))
							ReportModelImpl.processReport(fileName, parametrosMap, transaccionesSRIColeccion, true); 	
						else
							ReportModelImpl.processReport(fileName, parametrosMap, transaccionesSRIColeccion, true); 
						
						
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
		else{
			SpiritAlert.createAlert("Debe escoger un cliente", SpiritAlert.INFORMATION);
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
	
	public void generarColeccionFacturas(){
		try {
			facturasMap = null;
			facturasMap = new HashMap();
			facturasMap.put("clienteoficinaId", clienteOficinaIf.getId());
			
			System.out.println("CELITENID:"+clienteOficinaIf.getId());
			System.out.println("CELITENID:"+clienteOficinaIf.getId());
			
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
			//ArrayList facturas = (ArrayList)SessionServiceLocator.getFacturaSessionService().findFacturasCarterasAndCarterasDetalleByQueryByFechaInicioAndByFechaFin(facturasMap, fechaInicio, fechaFin);
			ArrayList facturas = (ArrayList)SessionServiceLocator.getFacturaSessionService().findFacturasCarterasAndCarterasDetalleByQueryByFechaInicioAndByFechaFinEmpresaId(facturasMap,new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()),Parametros.getIdEmpresa());
			
			System.out.println("facua"+facturas.size());
			facturasMap.put("estado", ESTADO_ANULADO);
			//ArrayList facturasAnuladas = (ArrayList)SessionServiceLocator.getFacturaSessionService().findFacturasByQueryByFechaInicioAndByFechaFin(facturasMap, fechaInicio, fechaFin);
			ArrayList facturasAnuladas = (ArrayList)SessionServiceLocator.getFacturaSessionService().findFacturasByQueryByFechaInicioAndByFechaFinEmpresaId(facturasMap,new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()),Parametros.getIdEmpresa());
			
			System.out.println("facturasAnuladas"+facturasAnuladas.size());
			System.out.println("facturasAnuladas"+facturasAnuladas.size());
			
			cargarMapasFacturaCartera(facturas, facturasAnuladas);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}		
	}
	
	public void generarColeccionCompras(){
		try {
			comprasMap = null;
			comprasMap = new HashMap();
			
			comprasMap.put("proveedorId", clienteOficinaIf.getId());
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());				
			ArrayList compras = (ArrayList)SessionServiceLocator.getCompraSessionService().findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFin(comprasMap, fechaInicio, fechaFin, false,null,null,null);				
//			ArrayList comprasRetencionesAnuladas = (ArrayList)SessionServiceLocator.getCompraSessionService().findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFinRetencionesAnuladas(comprasMap, fechaInicio, fechaFin, getCbOrdenarPorSecuencialRetencion().isSelected(),idIva,idRenta,txtretencionNumero);
			cargarMapasCompras(compras);
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
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
	
	public void cargarMapaCompraBases(){
		Map<Long,BigDecimal[]> mapaRentasBases = new HashMap<Long,BigDecimal[]>();
		Map<Long,BigDecimal> mapaIvasBases = new HashMap<Long,BigDecimal>();
		mapaCompraBases = null;
		mapaCompraBases = new HashMap<Long,BigDecimal[]>();
		mapaCompraRetenciones = null;
		mapaCompraRetenciones = new HashMap<Long,Map<String,Object[]>>();
		BigDecimal sumaBase12 = new BigDecimal(0);
		BigDecimal sumaBase0 = new BigDecimal(0);
		Iterator mapaCompraIt = mapaCompra.keySet().iterator();
		
		while(mapaCompraIt.hasNext()){
			Long compraId = (Long)mapaCompraIt.next();
			CompraIf compraIf = (CompraIf)mapaCompra.get(compraId);
			//Cargo mapa que relaciona compra con base 12 y base 0
			sumaBase12 = new BigDecimal(0);
			sumaBase0 = new BigDecimal(0);
			BigDecimal[] sumaBases = new BigDecimal[2];
			for(int i=0; i<compraDetalleVector.size(); i++){
				CompraDetalleIf compraDetalleIf = compraDetalleVector.get(i);	
				
				if(compraDetalleIf.getCompraId().equals(compraIf.getId()) 
						&& !compraIf.getEstado().equals(ESTADO_ANULADO))
				{
					ProductoIf producto = mapaProducto.get(compraDetalleIf.getProductoId());
					GenericoIf generico = mapaGenerico.get(producto.getGenericoId());					
					if(generico.getCobraIva().equals(COBRA_IVA_NO))
					{
						sumaBase0 = sumaBase0.add(compraDetalleIf.getValor().multiply(BigDecimal.valueOf(compraDetalleIf.getCantidad())).subtract(compraDetalleIf.getDescuento()));
						if(compraDetalleIf.getIdSriAir() != null){
							if(mapaRentasBases.get(compraDetalleIf.getIdSriAir()) == null){
								mapaRentasBases.put(compraDetalleIf.getIdSriAir(),new BigDecimal[]{new BigDecimal(0),compraDetalleIf.getValor().multiply(BigDecimal.valueOf(compraDetalleIf.getCantidad())).subtract(compraDetalleIf.getDescuento())});
							}else{
								BigDecimal[] rentaBases = mapaRentasBases.get(compraDetalleIf.getIdSriAir());
								rentaBases[1] = rentaBases[1].add(compraDetalleIf.getValor().multiply(BigDecimal.valueOf(compraDetalleIf.getCantidad())).subtract(compraDetalleIf.getDescuento()));
								mapaRentasBases.put(compraDetalleIf.getIdSriAir(),rentaBases);
							}
						}
					}else{
						sumaBase12 = sumaBase12.add(compraDetalleIf.getValor().multiply(BigDecimal.valueOf(compraDetalleIf.getCantidad())).subtract(compraDetalleIf.getDescuento()));
						if(compraDetalleIf.getIdSriAir() != null){
							if(mapaRentasBases.get(compraDetalleIf.getIdSriAir()) == null){
								mapaRentasBases.put(compraDetalleIf.getIdSriAir(), new BigDecimal[]{compraDetalleIf.getValor().multiply(BigDecimal.valueOf(compraDetalleIf.getCantidad())).subtract(compraDetalleIf.getDescuento()),new BigDecimal(0)});
							}else{
								BigDecimal[] rentaBases = mapaRentasBases.get(compraDetalleIf.getIdSriAir());
								rentaBases[0] = rentaBases[0].add(compraDetalleIf.getValor().multiply(BigDecimal.valueOf(compraDetalleIf.getCantidad())).subtract(compraDetalleIf.getDescuento()));
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
			sumaBases[0] = sumaBase12;
			sumaBases[1] = sumaBase0;
			mapaCompraBases.put(compraId, sumaBases);
			
			//Cargo mapa que relaciona compra con retenciones
			Map<String,Object[]> mapaCodigoRetencion = new HashMap<String,Object[]>();
			for(int j=0; j<compraRetencionVector.size(); j++){
				CompraRetencionIf compraRetencionIf = compraRetencionVector.get(j);
				Object[] retencion = new Object[4];
				if(compraRetencionIf.getCompraId().equals(compraIf.getId())){
					retencion[0] = compraRetencionIf.getImpuesto();
					retencion[1] = compraRetencionIf.getPorcentajeRetencion();
					retencion[2] = compraRetencionIf.getValorRetenido();
					retencion[3] = compraRetencionIf.getSecuencial();
					mapaCodigoRetencion.put(compraRetencionIf.getCodigoImpuesto(),retencion);
				}
			}
			mapaCompraRetenciones.put(compraId, mapaCodigoRetencion);
		}	
		//generarComprasResumenColeccion(mapaRentasBases, mapaIvasBases);
	}
	
	
	
	
	public void cargarMapasFacturaCartera(ArrayList facturas, ArrayList facturasAnuladas){
		mapaFactura = null;
		mapaCartera = null;
		carteraDetalleVector = null;
		mapaFactura = new LinkedMap();
		mapaCartera = new HashMap<Long,CarteraIf>();
		carteraDetalleVector = new Vector<CarteraDetalleIf>();
		ArrayList<FacturaIf> facturasOrdenadas = new ArrayList<FacturaIf>();
		Iterator facturasIt = facturas.iterator();
		while (facturasIt.hasNext()) {
			Object[] facturasObject = (Object[]) facturasIt.next();
			FacturaIf facturaIf = (FacturaIf) facturasObject[0];
			mapaFactura.put(facturaIf.getId(),facturaIf);
			CarteraIf carteraIf = (CarteraIf) facturasObject[1];
			mapaCartera.put(carteraIf.getReferenciaId(),carteraIf);
			CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) facturasObject[2];
			carteraDetalleVector.add(carteraDetalleIf);
		}
		Iterator facturasAnuladasIt = facturasAnuladas.iterator();
		while (facturasAnuladasIt.hasNext()) {
			FacturaIf facturaIf = (FacturaIf) facturasAnuladasIt.next();
			mapaFactura.put(facturaIf.getId(),facturaIf);
		}
		Iterator mapaFacturasIt = mapaFactura.keySet().iterator();
		while (mapaFacturasIt.hasNext()) {
			Long facturaId = (Long)mapaFacturasIt.next();
			FacturaIf factura = (FacturaIf)mapaFactura.get(facturaId);
			facturasOrdenadas.add(factura);
		}
		
		generarMapaFacturaRentenciones(facturasOrdenadas, carteraDetalleVector);
		
	}
	
	
	private void generarMapaFacturaRentenciones(ArrayList<FacturaIf> facturasOrdenadas, Vector<CarteraDetalleIf> carteraDetalleVector) {
		try {
			mapaFacturaRetenciones = null;
			mapaFacturaRetenciones = new LinkedMap();
			
			DocumentoIf documento = null;
			Iterator facturasOrdenadasIt = facturasOrdenadas.iterator();
			while (facturasOrdenadasIt.hasNext()) {
				FacturaIf factura = (FacturaIf)facturasOrdenadasIt.next();
				
				Map mapaRetenciones = null;
				if(mapaFacturaRetenciones.get(factura) == null){
					mapaRetenciones = new HashMap();
					mapaRetenciones.put(RETENCION_RENTA, " ");
					mapaRetenciones.put(RETENCION_RENTA_1, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_RENTA_2, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_IVA, " ");
					mapaRetenciones.put(RETENCION_IVA_30, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_IVA_70, new BigDecimal(0));
					mapaRetenciones.put(RETENCION_IVA_100, new BigDecimal(0));
				}else{
					mapaRetenciones = (Map<String,BigDecimal>)mapaFacturaRetenciones.get(factura);
				}
				
				if(mapaCartera.get(factura.getId()) != null){
					CarteraIf cartera = (CarteraIf)mapaCartera.get(factura.getId());
					for(int i=0; i<carteraDetalleVector.size(); i++){
						CarteraDetalleIf carteraDetalle = carteraDetalleVector.get(i);				
						if(carteraDetalle.getCarteraId().compareTo(cartera.getId()) == 0){
							Collection carterasAfecta = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteraafectaId(carteraDetalle.getId());
							Iterator carterasAfectaIt = carterasAfecta.iterator();
							while(carterasAfectaIt.hasNext()){
								CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf)carterasAfectaIt.next();
								CarteraDetalleIf carteraDetalleIf = SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(carteraAfectaIf.getCarteradetalleId());
								if(carteraDetalleIf.getSriClienteRetencionId() != null){
									SriClienteRetencionIf sriClienteRetencion = SessionServiceLocator.getSriClienteRetencionSessionService().getSriClienteRetencion(carteraDetalleIf.getSriClienteRetencionId());
									if(sriClienteRetencion.getTipoRetencion().equals(TIPO_RETENCION_RENTA)){
										mapaRetenciones.put(RETENCION_RENTA, carteraDetalleIf.getPreimpreso()!=null?carteraDetalleIf.getPreimpreso():" ");
										if(sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(1)) == 0){
											mapaRetenciones.put(RETENCION_RENTA_1, ((BigDecimal)mapaRetenciones.get(RETENCION_RENTA_1)).add(carteraAfectaIf.getValor()));
										}else if(sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(2)) == 0){
											mapaRetenciones.put(RETENCION_RENTA_2, ((BigDecimal)mapaRetenciones.get(RETENCION_RENTA_2)).add(carteraAfectaIf.getValor()));
										}
									}
									else if(sriClienteRetencion.getTipoRetencion().equals(TIPO_RETENCION_IVA)){
										mapaRetenciones.put(RETENCION_IVA, carteraDetalleIf.getPreimpreso()!=null?carteraDetalleIf.getPreimpreso():" ");
										if(sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(30)) == 0){
											mapaRetenciones.put(RETENCION_IVA_30, ((BigDecimal)mapaRetenciones.get(RETENCION_IVA_30)).add(carteraAfectaIf.getValor()));
										}else if(sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(70)) == 0){
											mapaRetenciones.put(RETENCION_IVA_70, ((BigDecimal)mapaRetenciones.get(RETENCION_IVA_70)).add(carteraAfectaIf.getValor()));
										}else if(sriClienteRetencion.getPorcentajeRetencion().compareTo(new BigDecimal(100)) == 0){
											mapaRetenciones.put(RETENCION_IVA_100, ((BigDecimal)mapaRetenciones.get(RETENCION_IVA_100)).add(carteraAfectaIf.getValor()));
										}	
									}
								}else {
									documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(carteraDetalleIf.getDocumentoId());
									if(documento.getCodigo().equals("RERC")){
										mapaRetenciones.put(RETENCION_RENTA, carteraDetalleIf.getPreimpreso()!=null?carteraDetalleIf.getPreimpreso():" ");
										mapaRetenciones.put(RETENCION_RENTA_1, ((BigDecimal)mapaRetenciones.get(RETENCION_RENTA_1)).add(carteraAfectaIf.getValor()));
									}else if(documento.getCodigo().equals("REIC")){
										mapaRetenciones.put(RETENCION_IVA, carteraDetalleIf.getPreimpreso()!=null?carteraDetalleIf.getPreimpreso():" ");
										mapaRetenciones.put(RETENCION_IVA_70, ((BigDecimal)mapaRetenciones.get(RETENCION_IVA_70)).add(carteraAfectaIf.getValor()));
									}
								}
							}
						}
					}
				} 
				mapaFacturaRetenciones.put(factura,mapaRetenciones);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	Comparator<ComprasIvaRetencionResumenData> ordenadorArrayListPorCodigo = new Comparator<ComprasIvaRetencionResumenData>(){
		public int compare(ComprasIvaRetencionResumenData o1, ComprasIvaRetencionResumenData o2) {
			return (o1.getCodigo().compareTo(o2.getCodigo()));
		}		
	};
	
	//johy	//	ASOCIACION ECUATORIANA DE AGENCIAS DE PUBLICIDAD
	public void cargarTabla(){
		cleanTotales();
		
		String facturaCompra=getCbComprasFacturas().getSelectedItem().toString();
		if(facturaCompra.equals("COMPRAS"))
		{
			generarColeccionCompras();
			int contador = 0;	
			Iterator mapaCompraIt = mapaCompra.keySet().iterator();		
			while(mapaCompraIt.hasNext()){			
				Long compraId = (Long)mapaCompraIt.next();			
				Map<String,Object[]> mapaCodigoRetencion = mapaCompraRetenciones.get(compraId);			
				Iterator mapaCodigoRetencionIt = mapaCodigoRetencion.keySet().iterator();	
				TransaccionesSRIData compraData = new TransaccionesSRIData();
				while(mapaCodigoRetencionIt.hasNext()){
					String codigoImpuesto = (String)mapaCodigoRetencionIt.next();
					Object[] retencion = mapaCodigoRetencion.get(codigoImpuesto);
					tableModel = (DefaultTableModel) getTblCompras().getModel();
					Vector<String> fila = new Vector<String>();
					agregarColumnasTabla(compraId, fila, compraData, codigoImpuesto, retencion, contador);
					contador++;
				}	
			}
			//****** BUSCAR LAS NOTAS DE CREDITO
			ArrayList carterap=null;
			carterap=generarColeccionNotasCredito();
			Iterator carteraIt = carterap.iterator();
			while (carteraIt.hasNext()) {				
				CarteraIf compraDetalleIf = (CarteraIf) carteraIt.next();	
				Vector<String> fila = new Vector<String>();
				agregarColumnasTablaNC_ND(fila,compraDetalleIf,"NC");
			}
			////////////////////////////////
			ArrayList carteraND=null;
			carteraND=generarColeccionNotasDebito();				
			Iterator carteraNDIt = carteraND.iterator();
			while (carteraNDIt.hasNext()) {				
				CarteraIf compraDetalleIf = (CarteraIf) carteraNDIt.next();	
				Vector<String> fila = new Vector<String>();
				agregarColumnasTablaNC_ND(fila,compraDetalleIf,"ND");
			}
			//////////////////////////////////
		}
		else{
				cleanTotales();			
				generarColeccionFacturas();	//llena vector mapaFacturaRetenciones 
				int contador = 0;	
				Iterator mapaFacturaIt = mapaFactura.keySet().iterator();
				
				while(mapaFacturaIt.hasNext()){			
					Long compraId = (Long)mapaFacturaIt.next();	
					FacturaIf facturaIf = (FacturaIf)mapaFactura.get(compraId);
					tableModel = (DefaultTableModel) getTblCompras().getModel();
					TransaccionesSRIData compraData = new TransaccionesSRIData();
					Vector<String> fila = new Vector<String>();
					agregarColumnasTablaFactura(fila,compraData,facturaIf); 
				}
				//******BUSCAR LAS NOTAS DE CREDITO
				ArrayList carterap=null;
				carterap=generarColeccionNotasCreditoFactura();
				Iterator carteraIt = carterap.iterator();
				while (carteraIt.hasNext()) {				
					CarteraIf compraDetalleIf = (CarteraIf) carteraIt.next();	
					Vector<String> fila = new Vector<String>();
					agregarColumnasTablaNC_ND(fila,compraDetalleIf,"NC");
				}
				////////////////////////////////
				ArrayList carteraND=null;
				carteraND=generarColeccionNotasDebitoFactura();				
				Iterator carteraNDIt = carteraND.iterator();
				while (carteraNDIt.hasNext()) {				
					CarteraIf compraDetalleIf = (CarteraIf) carteraNDIt.next();	
					Vector<String> fila = new Vector<String>();
					agregarColumnasTablaNC_ND(fila,compraDetalleIf,"ND");
				}			
			} 
	}
	
	
	
	public void agregarColumnasTablaFactura(Vector<String> fila, TransaccionesSRIData transaccionesSRI, FacturaIf factura){
		
		//tipoDocumentoFactura.getCodigo().equals(CODIGO_FACTURA_EXTERIOR)
		
		if(factura.getEstado().equals(ESTADO_ANULADO) ){				
			//nada!!
		}else{				
			
			String porcentajeRenta="";
			String valorRenta="";
			String numRetencion="";
			
		 
			Map mapaRetenciones = (Map)mapaFacturaRetenciones.get(factura);
			Iterator mapaFacturaRetencionesIt = mapaRetenciones.keySet().iterator();			 
				if(mapaRetenciones.get(RETENCION_RENTA)!=null)	numRetencion=mapaRetenciones.get(RETENCION_RENTA).toString();				
				if(mapaRetenciones.get(RETENCION_RENTA_1)!=null && !mapaRetenciones.get(RETENCION_RENTA_1).toString().equals("0")) 
				{
					porcentajeRenta="1%";
					valorRenta=mapaRetenciones.get(RETENCION_RENTA_1).toString();
				}
				if(mapaRetenciones.get(RETENCION_RENTA_2)!=null && !mapaRetenciones.get(RETENCION_RENTA_2).toString().equals("0"))
				{
					porcentajeRenta="2%";
					valorRenta=mapaRetenciones.get(RETENCION_RENTA_2).toString();
				}
		 
				
			 
			fila.add(Utilitarios.getFechaCortaUppercase(factura.getFechaFactura()));		//fila.add(tipoDocumentoFactura.getNombre());
			fila.add("FACTURA");	//transaccionesSRI.setTipoDocumentoNombre(tipoDocumentoFactura.getNombre());
			transaccionesSRI.setTipoDocumentoNombre("FACTURA");
			transaccionesSRI.setNumeroAutorizacion("");				
			fila.add(factura.getPreimpresoNumero()!=null?factura.getPreimpresoNumero():"");		
			fila.add("");
			transaccionesSRI.setClienteId(factura.getClienteoficinaId().toString());
			transaccionesSRI.setCliente(clienteIf.getNombreLegal());
			transaccionesSRI.setRuc(clienteIf.getIdentificacion());
			transaccionesSRI.setFechaEmision(Utilitarios.getFechaCortaUppercase(factura.getFechaFactura()));
			transaccionesSRI.setNumeroFactura(factura.getPreimpresoNumero()!=null?factura.getPreimpresoNumero():"");
			
			BigDecimal descuentoTotal = factura.getDescuento().add(factura.getDescuentoGlobal());
			
			BigDecimal subTotal = factura.getValor().subtract(descuentoTotal);
			
			BigDecimal porcentajeComision = new BigDecimal(0);
			BigDecimal comisionAgencia = new BigDecimal(0);
			if(factura.getPorcentajeComisionAgencia() != null){
				porcentajeComision = factura.getPorcentajeComisionAgencia();
				comisionAgencia = subTotal.multiply(porcentajeComision.divide(new BigDecimal(100)));
			}
			subTotal=subTotal.add(comisionAgencia);
			
			fila.add(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
			transaccionesSRI.setSubtotalFactura(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
			
			fila.add(formatoDecimal.format(Utilitarios.redondeoValor(new BigDecimal(0))));
			fila.add(formatoDecimal.format(Utilitarios.redondeoValor(new BigDecimal(0))));
						
			fila.add(formatoDecimal.format(Utilitarios.redondeoValor(factura.getOtroImpuesto())));
			fila.add(formatoDecimal.format(Utilitarios.redondeoValor(factura.getIva())));
			fila.add(formatoDecimal.format(Utilitarios.redondeoValor(subTotal.add(factura.getIva()))));
			totalTotal = totalTotal.add(subTotal.add(factura.getIva()));
			transaccionesSRI.setIva(formatoDecimal.format(Utilitarios.redondeoValor(factura.getIva())));				
			transaccionesSRI.setTotal(formatoDecimal.format(Utilitarios.redondeoValor(subTotal.add(factura.getIva()))));
			fila.add(porcentajeRenta);
			fila.add(valorRenta);
			fila.add(numRetencion);
			
			transaccionesSRI.setNumeroRetencion(numRetencion);			
			transaccionesSRI.setImpuesto(factura.getOtroImpuesto().toString());
			transaccionesSRI.setCodigo("0");			
			transaccionesSRI.setPorcentajeRenta(porcentajeRenta);
			transaccionesSRI.setValorRetenido(valorRenta);
			transaccionesSRIColeccion.add(transaccionesSRI);
			tableModel.addRow(fila);
			
		}
		
	}
	
	
	//TELEVISORA NACIONAL COMPANIA ANONIMA TELENACIONAL CA
	public ArrayList generarColeccionNotasCredito(){		
		ArrayList carterap=null;		
		try {			
			comprasMap = null;
			comprasMap = new HashMap();
			carteraVector.clear();
			comprasMap.put("clienteoficinaId", clienteOficinaIf.getId());
			Long tipoDocNotaCreditoProveedor=mapaTipoDocumentoCodigo.get("NCP");
			comprasMap.put("tipodocumentoId",tipoDocNotaCreditoProveedor);
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());		
			carterap = (ArrayList)SessionServiceLocator.getCarteraSessionService().getClienteConCarterabyFechaInicioFechaFin(Parametros.getIdEmpresa(), fechaInicio, fechaFin,comprasMap);
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
			
			comprasMap.put("clienteoficinaId", clienteOficinaIf.getId());				
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
	
	
	
	public ArrayList generarColeccionNotasCreditoFactura(){		
		ArrayList carterap=null;		
		try {			
			comprasMap = null;
			comprasMap = new HashMap();
			carteraVector.clear();
			
			comprasMap.put("clienteoficinaId", clienteOficinaIf.getId());
			Long tipoDocNotaCreditoProveedor=mapaTipoDocumentoCodigo.get("NCC");
			comprasMap.put("tipodocumentoId",tipoDocNotaCreditoProveedor);
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
			carterap = (ArrayList)SessionServiceLocator.getCarteraSessionService().getClienteConCarterabyFechaInicioFechaFin(Parametros.getIdEmpresa(), fechaInicio, fechaFin,comprasMap);								 
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}	
		return carterap;
	} 
	
	
	
	public ArrayList generarColeccionNotasDebitoFactura(){		
		ArrayList carterap=null;
		try {
			comprasMap = null;
			comprasMap = new HashMap();
			carteraVector.clear();
			
			comprasMap.put("clienteoficinaId", clienteOficinaIf.getId());				
			Long tipoDocNotaDebitoProveedor=mapaTipoDocumentoCodigo.get("NDC");				
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
	

	public void agregarColumnasTabla(Long compraId, Vector<String> fila, TransaccionesSRIData transaccionesSRI, String codigoImpuesto, Object[] retencion, int contador){
		CompraIf compraIf = (CompraIf)mapaCompra.get(compraId);
		ClienteOficinaIf clienteOficina = mapaClienteOficina.get(compraIf.getProveedorId());
		ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());		 	
		if(compraIf.getEstado().equals(ESTADO_ANULADO)){				
			//nada!!
		}else{				
			if(retencion[0].toString().equals("R")){
				TipoDocumentoIf tipoDocumentoFactura = mapaTipoDocumento.get(compraIf.getTipodocumentoId());				
				fila.add(Utilitarios.getFechaCortaUppercase(compraIf.getFecha()));
				//fila.add(tipoDocumentoFactura.getNombre());
				fila.add("FACTURA");
				//transaccionesSRI.setTipoDocumentoNombre(tipoDocumentoFactura.getNombre());
				transaccionesSRI.setTipoDocumentoNombre("FACTURA");
				transaccionesSRI.setNumeroAutorizacion(compraIf.getAutorizacion());				
				fila.add(compraIf.getPreimpreso()!=null?compraIf.getPreimpreso():"");		
				fila.add(compraIf.getAutorizacion());
				transaccionesSRI.setClienteId(cliente.getId().toString());
				transaccionesSRI.setCliente(cliente.getNombreLegal());
				transaccionesSRI.setRuc(cliente.getIdentificacion());
				transaccionesSRI.setFechaEmision(Utilitarios.getFechaCortaUppercase(compraIf.getFecha()));
				transaccionesSRI.setNumeroFactura(compraIf.getPreimpreso()!=null?compraIf.getPreimpreso():""); 
				BigDecimal subTotal = compraIf.getValor().subtract(compraIf.getDescuento());
				if(tipoDocumentoFactura.getCodigo().equals(CODIGO_COMPRA_IMPORTADA)){
					fila.add(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					transaccionesSRI.setNormal(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));		
					transaccionesSRI.setSubtotalFactura(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
				}else if(tipoDocumentoFactura.getCodigo().equals(CODIGO_COMPRA_REEMBOLSO)){
					fila.add(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					transaccionesSRI.setNormal(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));	
					transaccionesSRI.setSubtotalFactura(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
				}else{					 
					fila.add(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					transaccionesSRI.setNormal(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));	
					transaccionesSRI.setSubtotalFactura(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
				}	
				BigDecimal[] sumaBases = mapaCompraBases.get(compraId);
				if(tipoDocumentoFactura.getCodigo().equals(CODIGO_COMPRA_IMPORTADA)){
					fila.add(formatoDecimal.format(Utilitarios.redondeoValor(new BigDecimal(0))));
					fila.add(formatoDecimal.format(Utilitarios.redondeoValor(new BigDecimal(0))));
					transaccionesSRI.setBaseIva12(formatoDecimal.format(Utilitarios.redondeoValor(new BigDecimal(0))));		
					transaccionesSRI.setBaseIva0(formatoDecimal.format(Utilitarios.redondeoValor(new BigDecimal(0))));
				}else{
					fila.add(formatoDecimal.format(Utilitarios.redondeoValor(sumaBases[0])));
					fila.add(formatoDecimal.format(Utilitarios.redondeoValor(sumaBases[1])));
					transaccionesSRI.setBaseIva12(formatoDecimal.format(Utilitarios.redondeoValor(sumaBases[0])));		
					transaccionesSRI.setBaseIva0(formatoDecimal.format(Utilitarios.redondeoValor(sumaBases[1])));	
				}	
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(compraIf.getOtroImpuesto())));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(compraIf.getIva())));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(subTotal.add(compraIf.getIva()))));
				totalTotal = totalTotal.add(subTotal.add(compraIf.getIva()));
				transaccionesSRI.setIva(formatoDecimal.format(Utilitarios.redondeoValor(compraIf.getIva())));				
				transaccionesSRI.setTotal(formatoDecimal.format(Utilitarios.redondeoValor(subTotal.add(compraIf.getIva()))));
				fila.add(retencion[1].toString());//%renta
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor((BigDecimal) retencion[2])));//valore retenido
				if(retencion[3].toString().equals("0")){ 	//numero de retencion
					fila.add("");
					transaccionesSRI.setNumeroRetencion("");	
					
				}else{
					fila.add(retencion[3].toString());
					transaccionesSRI.setNumeroRetencion(retencion[3].toString());					 
				}
				transaccionesSRI.setImpuesto(compraIf.getOtroImpuesto().toString());
				transaccionesSRI.setCodigo(codigoImpuesto);			
				transaccionesSRI.setPorcentajeRenta(retencion[1].toString());
				transaccionesSRI.setValorRetenido(formatoDecimal.format(Utilitarios.redondeoValor((BigDecimal) retencion[2])));
				transaccionesSRIColeccion.add(transaccionesSRI);
				tableModel.addRow(fila);
				
			}
			else{
				
			}
			
		}	
		
	}
	
	
	public void agregarColumnasTablaNC_ND(Vector<String> fila, CarteraIf carteraData,String tipo){
		//(retencion[0].toString().equals("R") 					
		//fila.add("");
		//compraData.setAnulada("");
		fila.add("");
		fila.add("");
		fila.add("");		
		fila.add("");
		fila.add("");			  
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		if(tipo.equals("NC")) fila.add("N/CREDITO");
		if(tipo.equals("ND")) fila.add("N/DEBITO");
		
		fila.add(carteraData.getFechaEmision().toString());
		fila.add(carteraData.getPreimpreso());
		BigDecimal conIva=carteraData.getValor();
		double sinIva=conIva.doubleValue()/1.12;
		fila.add(new Double(Utilitarios.redondeoValor(sinIva)).toString());
		fila.add(conIva.toString());
		fila.add("");
		
		TransaccionesSRIData compraData = new TransaccionesSRIData();
		
		compraData.setFechaDocNcNd(carteraData.getFechaEmision().toString());
		compraData.setNumeroNcNd(carteraData.getPreimpreso());
		
		compraData.setValorSinIvaNcNd(new Double(Utilitarios.redondeoValor(sinIva)).toString());
		compraData.setValorIvaNcNd(conIva.toString());
		
		transaccionesSRIColeccion.add(compraData);
		
		tableModel.addRow(fila);
		
		
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
	
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
}
