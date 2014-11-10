package com.spirit.sri.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.commons.collections.map.LinkedMap;

import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoData;
import com.spirit.contabilidad.entity.AsientoDetalleData;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.util.Contratos;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.handler.TipoRol;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriCamposFormularioIf;
import com.spirit.sri.entity.SriClienteRetencionIf;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.sri.gui.panel.JPImpuestosMensuales;
import com.spirit.sri.gui.reporteData.ImpuestosMensualesData;
import com.spirit.util.Utilitarios;



public class ImpuestosMensualesModel extends  JPImpuestosMensuales{
	
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private Vector<ImpuestosMensualesData> facturacionColeccion = new Vector<ImpuestosMensualesData>();
	private String filtro = "";
	private BigDecimal totalTotal = new BigDecimal(0);
	private static final String ESTADO_TODOS = "TODOS";
	private final static String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private final static String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	
	private Map<String,Map<String,BigDecimal>> mapaCamposValoresMeses = new LinkedMap();
	
	
	private static String NOMBRE_MENU_SRI = "SRI";
	
	private Map<String,Map<Long,BigDecimal[]>> mapaCamposValores332 = new LinkedMap();
	
	 
	
	String[] meses = new String[]{"ENE","FEB","MAR","ABR","MAY","JUN","JUL","AGO","SEP","OCT","NOV","DIC"};
	
	private String secuencialInicio = "";
	private String secuencialFin = "";
	
	private static final String IMPUESTO_RENTA = "RENTA";
	private static final String IMPUESTO_IVA = "I.V.A";
	private static final String AMBOS = "AMBOS";
	private static final String COBRA_IVA_NO = "N";
	
	private Map comprasMap = new HashMap();
	private LinkedMap mapaCompra = new LinkedMap();
	private Vector<CompraDetalleIf> compraDetalleVector = new Vector<CompraDetalleIf>();
	private Vector<CompraRetencionIf> compraRetencionVector = new Vector<CompraRetencionIf>();
	
	
	private Map<Long,BigDecimal[]> mapaCompraBases = new HashMap<Long,BigDecimal[]>();
	private Map<Long,Map<String,Object[]>> mapaCompraRetenciones = new HashMap<Long,Map<String,Object[]>>();
	private Map<Long,ProductoIf> mapaProducto = new HashMap<Long,ProductoIf>();
	private Map<Long,GenericoIf> mapaGenerico = new HashMap<Long,GenericoIf>();
	
	private Map<String,String> mapasCamposFormulario = new HashMap<String,String>();
	private Map<String,String> mapasCamposFormularioRentas = new HashMap<String,String>();
	
	private Map<String,String> mapasCamposRentaFormularioPorcentaje = new HashMap<String,String>();
	private Map<Integer,String> mapasCamposRentaFormularioSumatoria = new HashMap<Integer,String>();
	
	private Map<Long,SriAirIf> mapaCodigoRetencionRenta = new HashMap<Long,SriAirIf>();
	private Map<Long,SriIvaRetencionIf> mapaCodigoRetencionIva = new HashMap<Long,SriIvaRetencionIf>();	
	
	
	private Map<String,BigDecimal> mapaCodigoPorcentajeRenta = new HashMap<String,BigDecimal>();
	
	///////400//
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
	
	private static final String ESTADO_ANULADO = "A";
	private static final String ESTADO_ANULADO_COMPRAS = "N";
	private static final String CODIGO_FACTURA_REEMBOLSO = "FAR";
	private static final String CODIGO_FACTURA_EXTERIOR = "FAE";
	
	private Map<Long,TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long,TipoDocumentoIf>();
	
	 
	private Map<Long,SriClienteRetencionIf> mapaSriClienteRetencion = new HashMap<Long,SriClienteRetencionIf>();
	
	//private Map<Long,DocumentoIf> mapaDocumento = new HashMap<Long,DocumentoIf>();
	
	private Map<String,Long> mapaTipoDocumentoCodigo = new HashMap<String,Long>();
	
	private Map<Integer,String> mesConsultadosMap = new HashMap<Integer,String>();
	
	
	private Map tiposCuentaMap = new HashMap();
	private Map<Long,Long> planCuentasMap = new HashMap();
	private Map<String,Long> cuentasCodigoMap = new HashMap();
	private Map<String,Long> cuentasNombreMap = new HashMap();
	
	private Map tipoContratoMap = new HashMap();
	private Map tipoRolMap = new HashMap();
	
	
	private Map saldosInicialesMap = new HashMap();
	
	///////
	Map<String,List<AsientoDetalleIf>> mapaAsientosDetalleMes411 = new HashMap<String,List<AsientoDetalleIf>>();			
	Map<String,List<AsientoDetalleIf>> mapaAsientosDetalleMes501Costo = new HashMap<String,List<AsientoDetalleIf>>();
	Map<String,List<AsientoDetalleIf>> mapaAsientosDetalleMes501Gasto = new HashMap<String,List<AsientoDetalleIf>>();
	
	
	public ImpuestosMensualesModel(){
		cargarMapasVarios();
		initKeyListeners();
		anchoColumnasTabla();
		showSaveMode();
		initListeners();
	}
	public void initKeyListeners(){
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
	}
	
	public void generarColeccionRetenc3_7_100(String tipoImpuesto){
		try {
			
			comprasMap = null;
			comprasMap = new HashMap();			
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
			ArrayList compras = (ArrayList)SessionServiceLocator.getCompraSessionService().findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFin(comprasMap, fechaInicio, fechaFin, true,null,null,null);
			
			cargarMapasCompras(compras,tipoImpuesto);
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}		
	} 
	
	
	public void cargarMapasVarios(){
		try {
			
			mesConsultadosMap.clear();

			cargarCuentasMap();
			

			Map parameterMapPC = new HashMap();
			parameterMapPC.put("codigo", "PC");
			parameterMapPC.put("empresaId", Parametros.getIdEmpresa());			
			Iterator plancuentasIterator = SessionServiceLocator.getPlanCuentaSessionService().findPlanCuentaByQuery(parameterMapPC).iterator();
			if (plancuentasIterator.hasNext())
				{
					PlanCuentaIf plancuentas=null;
					plancuentas = (PlanCuentaIf) plancuentasIterator.next();				
					planCuentasMap.put(plancuentas.getEmpresaId(), plancuentas.getId());				
				}
			
			
						 
			tipoRolMap.clear();
			Iterator tiposRolIterator = SessionServiceLocator.getTipoRolSessionService().getTipoRolList().iterator();
			while (tiposRolIterator.hasNext()) {
					TipoRolIf tipoRol = (TipoRolIf) tiposRolIterator.next();
					tipoRolMap.put(tipoRol.getCodigo(), tipoRol);
			}
			 
				
			tipoContratoMap.clear();
			Iterator tiposContratoIterator = SessionServiceLocator.getTipoContratoSessionService().getTipoContratoList().iterator();
			while (tiposContratoIterator.hasNext()) {
				TipoContratoIf tipoContrato = (TipoContratoIf) tiposContratoIterator.next();
				tipoContratoMap.put(tipoContrato.getCodigo(), tipoContrato);
			}
			
			
			
			tiposCuentaMap.clear();
			Iterator tiposCuentaIterator = SessionServiceLocator.getTipoCuentaSessionService().getTipoCuentaList().iterator();
			while (tiposCuentaIterator.hasNext()) {
				TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaIterator.next();
				tiposCuentaMap.put(tipoCuenta.getId(), tipoCuenta);
			}
			 
			
			
			
			
			
			 
			mapaSriClienteRetencion.clear();
			Collection sriclienteReten = SessionServiceLocator.getSriClienteRetencionSessionService().getSriClienteRetencionList();
			Iterator sriclienteRetenIt = sriclienteReten.iterator();
			while(sriclienteRetenIt.hasNext()){
				SriClienteRetencionIf sricliente = (SriClienteRetencionIf)sriclienteRetenIt.next();
				mapaSriClienteRetencion.put(sricliente.getId(), sricliente);
			}
			
			mapaTipoDocumento.clear();
			Collection tiposDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumentoList();
			Iterator tiposDocumentoIt = tiposDocumento.iterator();
			while(tiposDocumentoIt.hasNext()){
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)tiposDocumentoIt.next();
				mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
			}
			
			
			mapaTipoDocumentoCodigo.clear();
			Collection tiposDocumento2 = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumentoList();
			Iterator tiposDocumentoIt2 = tiposDocumento2.iterator();
			while(tiposDocumentoIt2.hasNext()){
				TipoDocumentoIf tipoDocumento2= (TipoDocumentoIf)tiposDocumentoIt2.next();
				mapaTipoDocumentoCodigo.put(tipoDocumento2.getCodigo(), tipoDocumento2.getId());
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

			
			mapaCodigoPorcentajeRenta.clear();
			Collection porcentajesRenta = SessionServiceLocator.getSriAirSessionService().findSriAirCodigoFechaActual();
			Iterator porcentajesRentaIt = porcentajesRenta.iterator();
			while(porcentajesRentaIt.hasNext()){
				SriAirIf sriAirIf = (SriAirIf)porcentajesRentaIt.next();
				
				
				mapaCodigoPorcentajeRenta.put(sriAirIf.getCodigo(), sriAirIf.getPorcentaje());
			}
			
			
			
			mapasCamposFormulario.clear();
			//Collection camposFormulario = SessionServiceLocator.getSriCamposFormularioSessionService().getSriCamposFormularioList();
			Collection camposFormulario = SessionServiceLocator.getSriCamposFormularioSessionService().findSriCamposFormularioByImpuesto("I");
			Iterator camposFormularioIt = camposFormulario.iterator();
			while(camposFormularioIt.hasNext()){				
				SriCamposFormularioIf sriCamposIf = (SriCamposFormularioIf)camposFormularioIt.next();				
				mapasCamposFormulario.put(sriCamposIf.getCodigo().toString(), sriCamposIf.getConcepto().toString());
			}
			mapasCamposFormulario.put("9990", "VALOR PAGADO");
			mapasCamposFormulario.put("9991", "PENDIENTE");
			//mapasCamposFormulario.put("9992", "PENDIENTE");
			
			mapasCamposFormularioRentas.clear();
			//Collection camposFormulario = SessionServiceLocator.getSriCamposFormularioSessionService().getSriCamposFormularioList();
			Collection camposFormulario2 = SessionServiceLocator.getSriCamposFormularioSessionService().findSriCamposFormularioByImpuesto("R");
			Iterator camposFormularioIt2 = camposFormulario2.iterator();
			while(camposFormularioIt2.hasNext()){				
				SriCamposFormularioIf sriCamposIf2 = (SriCamposFormularioIf)camposFormularioIt2.next();				
				mapasCamposFormularioRentas.put(sriCamposIf2.getCodigo().toString(), sriCamposIf2.getConcepto().toString());
			}	
			mapasCamposFormularioRentas.put("9990", "VALOR PAGADO");
			mapasCamposFormularioRentas.put("9991", "PENDIENTE");
			//mapasCamposFormularioRentas.put("9992", "PENDIENTE");
			
			
			
			Map parameterMap = new HashMap();
			parameterMap.put("observacion", "porcentaje");
			parameterMap.put("impuesto", "R");			
			mapasCamposRentaFormularioPorcentaje.clear();
			//Collection camposFormularioPorcentaje = SessionServiceLocator.getSriCamposFormularioSessionService().findSriCamposFormularioByObservacion("porcentaje");
			Collection camposFormularioPorcentaje = SessionServiceLocator.getSriCamposFormularioSessionService().findSriCamposFormularioByQuery(parameterMap);
			Iterator camposFormularioPorcentajeIt = camposFormularioPorcentaje.iterator();
			while(camposFormularioPorcentajeIt.hasNext()){				
				SriCamposFormularioIf sriCamposIf = (SriCamposFormularioIf)camposFormularioPorcentajeIt.next();				
				mapasCamposRentaFormularioPorcentaje.put(sriCamposIf.getCodigo().toString(), sriCamposIf.getValor().toString());
			}
			
			
			Map parameterMap2 = new HashMap();
			parameterMap2.put("observacion", "sumatoria");
			parameterMap2.put("impuesto", "R");			
			mapasCamposRentaFormularioSumatoria.clear();
			//Collection camposFormularioPorcentaje = SessionServiceLocator.getSriCamposFormularioSessionService().findSriCamposFormularioByObservacion("porcentaje");
			Collection camposFormularioSumatoria = SessionServiceLocator.getSriCamposFormularioSessionService().findCamposFormularioByQueryOrderCodigo("sumatoria","R");
			
			Iterator camposFormularioSumatoriaIt = camposFormularioSumatoria.iterator();
			while(camposFormularioSumatoriaIt.hasNext()){				
				SriCamposFormularioIf sriCamposIf = (SriCamposFormularioIf)camposFormularioSumatoriaIt.next();
				mapasCamposRentaFormularioSumatoria.put(new Integer(sriCamposIf.getCodigo()), sriCamposIf.getValor().toString());
			}
 			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}

	

	
	/*
	public static HashMap ordenarHashMap(HashMap hmap){
		HashMap map = new LinkedHashMap();
		List mapKeys = new ArrayList(hmap.keySet());
		List mapValues = new ArrayList(hmap.values());
		hmap.clear();
		TreeSet sortedSet = new TreeSet(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i=0; imap.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i]);
		}
		return map;
		} */

	
	public void cargarMapasCompras(ArrayList compras,String tipoImpuesto){
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
		/*for(int i=0; i<compraRetencionVector.size(); i++){
			CompraRetencionIf compraRetencion = compraRetencionVector.get(i);
		}*/
		cargarMapaCompraBases(tipoImpuesto);
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
	
	 
	public void cargarMapaCompraBases(String tipoImpuesto){
		int mes = 0;
		
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
			
			//if(!tipoImpuesto.equals("R"))
			  
			  for(int i=0; i<compraDetalleVector.size(); i++){			
				mes = compraIf.getFecha().getMonth();
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}				
				CompraDetalleIf compraDetalleIf = compraDetalleVector.get(i);
				if(compraDetalleIf.getCompraId().equals(compraIf.getId()) && !compraIf.getEstado().equals(ESTADO_ANULADO_COMPRAS)){
					ProductoIf producto = mapaProducto.get(compraDetalleIf.getProductoId());
					GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
					 	
					if(generico.getCobraIva().equals(COBRA_IVA_NO)){
						sumaBase0 = sumaBase0.add(compraDetalleIf.getValor().multiply(BigDecimal.valueOf(compraDetalleIf.getCantidad())).subtract(compraDetalleIf.getDescuento()));
						if(compraDetalleIf.getIdSriAir() != null){							
							if(mapaRentasBases.get(compraDetalleIf.getIdSriAir()) == null){
								mapaRentasBases.put(compraDetalleIf.getIdSriAir(),new BigDecimal[]{new BigDecimal(0),compraDetalleIf.getValor().multiply(BigDecimal.valueOf(compraDetalleIf.getCantidad())).subtract(compraDetalleIf.getDescuento())});								
							}else{
								BigDecimal[] rentaBases = mapaRentasBases.get(compraDetalleIf.getIdSriAir());
								rentaBases[1] = rentaBases[1].add(compraDetalleIf.getValor().multiply(BigDecimal.valueOf(compraDetalleIf.getCantidad())).subtract(compraDetalleIf.getDescuento()));
								mapaRentasBases.put(compraDetalleIf.getIdSriAir(),rentaBases);	
							}
							 
							if(tipoImpuesto.equals("R") )
							  {
								  SriAirIf sriAir = mapaCodigoRetencionRenta.get(compraDetalleIf.getIdSriAir());
 
								  
								  
								  if(sriAir.getCodigo().equals("333"))
								  { 
									  BigDecimal[] rentaBases = mapaRentasBases.get(compraDetalleIf.getIdSriAir());
									  
								  	  BigDecimal sumados=BigDecimal.ZERO;
								  	  if(rentaBases[0]!=null) sumados=rentaBases[0];
								  	  if(rentaBases[1]!=null) sumados=sumados.add(rentaBases[1]);
								  	  
								  	Map<String,BigDecimal> mapaMesValor605 = new HashMap<String,BigDecimal>();
									if(mapaCamposValoresMeses.get("333")!=null)
									{					
										Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("333").keySet().iterator();
										while(mapaMesValorIt2.hasNext()){
											String key = (String)mapaMesValorIt2.next();
											mapaMesValor605.put(key,mapaCamposValoresMeses.get("333").get(key));
										}
									}	
									mapaMesValor605.put(meses[mes],sumados);
									mapaCamposValoresMeses.put("333",mapaMesValor605);
									
								  }
								 
								  
								  if(sriAir.getCodigo().equals("322"))
								  { 
									 BigDecimal sumados=BigDecimal.ZERO;
								  	 sumados=compraDetalleIf.getIva();		
								  	  
								  	 //llenarCampoValorString
								  	  if(sumados!=null && !sumados.equals("0.00") && !sumados.equals("0"))
								  		  sumados=sumados.divide(new BigDecimal("0.12"));
								  	  
								  	Map<String,BigDecimal> mapaMesValor605 = new HashMap<String,BigDecimal>();
									if(mapaCamposValoresMeses.get("372")!=null)
									{					
										Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("372").keySet().iterator();
										while(mapaMesValorIt2.hasNext()){
											String key = (String)mapaMesValorIt2.next();
											mapaMesValor605.put(key,mapaCamposValoresMeses.get("372").get(key));
										}
									}	
									mapaMesValor605.put(meses[mes],sumados);
									//mapaCamposValoresMeses.put("372",mapaMesValor605);
									llenarCampoValorString("372",sumados, meses[mes]);
									
								  } 
								  
								 
								  if(sriAir.getCodigo().equals("332"))
								  { 
									  BigDecimal[] rentaBases = mapaRentasBases.get(compraDetalleIf.getIdSriAir());
									
									  BigDecimal sumados=BigDecimal.ZERO;
								  	  if(rentaBases[0]!=null) sumados=rentaBases[0];
								  	  if(rentaBases[1]!=null) sumados=sumados.add(rentaBases[1]);
								  	  
								  	  
								  	Map<String,BigDecimal> mapaMesValor605 = new HashMap<String,BigDecimal>();
									if(mapaCamposValoresMeses.get("332")!=null)
									{					
										Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("332").keySet().iterator();
										while(mapaMesValorIt2.hasNext()){
											String key = (String)mapaMesValorIt2.next();
											mapaMesValor605.put(key,mapaCamposValoresMeses.get("332").get(key));
										}
									}	
									mapaMesValor605.put(meses[mes],sumados);
									mapaCamposValoresMeses.put("332",mapaMesValor605);
									
								  }
									  	
							  }
							
							
							if(!tipoImpuesto.equals("R"))
								llenarCampoValor("517",compraDetalleIf.getValor().multiply(BigDecimal.valueOf(compraDetalleIf.getCantidad())).subtract(compraDetalleIf.getDescuento()),mes);
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
							
							
							
							 
							if(tipoImpuesto.equals("R") )
							  {
								  SriAirIf sriAir = mapaCodigoRetencionRenta.get(compraDetalleIf.getIdSriAir());
								   
								  
								  if(sriAir.getCodigo().equals("333"))
								  { 
									  BigDecimal[] rentaBases = mapaRentasBases.get(compraDetalleIf.getIdSriAir());
									 
									  
								  	  BigDecimal sumados=BigDecimal.ZERO;
								  	  if(rentaBases[0]!=null) sumados=rentaBases[0];
								  	  if(rentaBases[1]!=null) sumados=sumados.add(rentaBases[1]);
								  	  
								  	Map<String,BigDecimal> mapaMesValor605 = new HashMap<String,BigDecimal>();
									if(mapaCamposValoresMeses.get("333")!=null)
									{					
										Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("333").keySet().iterator();
										while(mapaMesValorIt2.hasNext()){
											String key = (String)mapaMesValorIt2.next();
											mapaMesValor605.put(key,mapaCamposValoresMeses.get("333").get(key));
										}
									}	
									mapaMesValor605.put(meses[mes],sumados);
									mapaCamposValoresMeses.put("333",mapaMesValor605);
								  }
								  

								  if(sriAir.getCodigo().equals("322"))
								  { 
									 BigDecimal sumados=BigDecimal.ZERO;
								  	 sumados=compraDetalleIf.getIva();		
								  	  
								  	 
								  	  
								  	  if(sumados!=null && !sumados.equals("0.00") && !sumados.equals("0"))
								  		  sumados=new BigDecimal(Utilitarios.redondeoValor(sumados.doubleValue()/new BigDecimal("0.12").doubleValue()));
								  	
								  	//  sumados=sumados.divide(new BigDecimal("0.12"));
								  	  //new Double(Utilitarios.redondeoValor(sinIva)).toString()
								  	  
								  	Map<String,BigDecimal> mapaMesValor605 = new HashMap<String,BigDecimal>();
									if(mapaCamposValoresMeses.get("372")!=null)
									{					
										Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("372").keySet().iterator();
										while(mapaMesValorIt2.hasNext()){
											String key = (String)mapaMesValorIt2.next();
											mapaMesValor605.put(key,mapaCamposValoresMeses.get("372").get(key));
										}
									}	
									mapaMesValor605.put(meses[mes],sumados);
									//mapaCamposValoresMeses.put("372",mapaMesValor605);
									llenarCampoValorString("372",sumados, meses[mes]);
									
								  } 
								  
								  
								  if(sriAir.getCodigo().equals("332"))
								  { 
									  BigDecimal[] rentaBases = mapaRentasBases.get(compraDetalleIf.getIdSriAir());
									  
									  BigDecimal sumados=BigDecimal.ZERO;
								  	  if(rentaBases[0]!=null) sumados=rentaBases[0];
								  	  if(rentaBases[1]!=null) sumados=sumados.add(rentaBases[1]);
								  	  
								  	Map<String,BigDecimal> mapaMesValor605 = new HashMap<String,BigDecimal>();
									if(mapaCamposValoresMeses.get("332")!=null)
									{					
										Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("332").keySet().iterator();
										while(mapaMesValorIt2.hasNext()){
											String key = (String)mapaMesValorIt2.next();
											mapaMesValor605.put(key,mapaCamposValoresMeses.get("332").get(key));
										}
									}	
									mapaMesValor605.put(meses[mes],sumados);
									mapaCamposValoresMeses.put("332",mapaMesValor605);
								  }
								  
									  	
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
			
			String tipo="";
			if(getCmbTipoImpuesto().getSelectedItem().equals(IMPUESTO_IVA))tipo="I";
			if(getCmbTipoImpuesto().getSelectedItem().equals(IMPUESTO_RENTA))tipo="R";
			if(getCmbTipoImpuesto().getSelectedItem().equals(AMBOS))tipo="";
			
			 
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
					
					mes = compraIf.getFecha().getMonth();					
					if (mesConsultadosMap.get(mes)== null) {
						mesConsultadosMap.put(mes,meses[mes]);
					}
					
					if(tipo.equals(""))
					{
						Map<String,BigDecimal> mapaMesValor = new HashMap<String,BigDecimal>();				
						if((mapaCamposValoresMeses.get(compraRetencionIf.getCodigoImpuesto()) == null)){
							mapaMesValor.put(meses[mes], compraRetencionIf.getValorRetenido());
							mapaCamposValoresMeses.put(compraRetencionIf.getCodigoImpuesto(),mapaMesValor);
							
						} else {					
							Iterator mapaMesValorIt = mapaCamposValoresMeses.get(compraRetencionIf.getCodigoImpuesto()).keySet().iterator();
							while(mapaMesValorIt.hasNext()){
								String key = (String)mapaMesValorIt.next();
								mapaMesValor.put(key,mapaCamposValoresMeses.get(compraRetencionIf.getCodigoImpuesto()).get(key));
							}
							//----
							//BigDecimal nuevovalor=mapaIvasBases.get(compraRetencionIf.getCodigoImpuesto()).add(compraRetencionIf.getValorRetenido());
							BigDecimal nuevovalor=compraRetencionIf.getValorRetenido();
							if (mapaCamposValoresMeses.get(compraRetencionIf.getCodigoImpuesto()).get(meses[mes]) == null) {
								mapaMesValor.put(meses[mes],nuevovalor );				
								mapaCamposValoresMeses.put(compraRetencionIf.getCodigoImpuesto(),mapaMesValor);					
							} else {
								mapaMesValor.put(meses[mes], nuevovalor.add(mapaCamposValoresMeses.get(compraRetencionIf.getCodigoImpuesto()).get(meses[mes])));				
								mapaCamposValoresMeses.put(compraRetencionIf.getCodigoImpuesto(),mapaMesValor);					
							} 
						}
					}
					else{			
						if(tipo.equals(compraRetencionIf.getImpuesto()) && !tipo.equals("I"))
						{
							
							 
							
							
							Map<String,BigDecimal> mapaMesValor = new HashMap<String,BigDecimal>();				
							if((mapaCamposValoresMeses.get(compraRetencionIf.getCodigoImpuesto()) == null)){
								mapaMesValor.put(meses[mes], compraRetencionIf.getValorRetenido());
								mapaCamposValoresMeses.put(compraRetencionIf.getCodigoImpuesto(),mapaMesValor);
								
							} else {					
								Iterator mapaMesValorIt = mapaCamposValoresMeses.get(compraRetencionIf.getCodigoImpuesto()).keySet().iterator();
								while(mapaMesValorIt.hasNext()){
									String key = (String)mapaMesValorIt.next();
									mapaMesValor.put(key,mapaCamposValoresMeses.get(compraRetencionIf.getCodigoImpuesto()).get(key));
								}			
								///aacaaa
								
							 
								//BigDecimal nuevovalor=mapaIvasBases.get(compraRetencionIf.getCodigoImpuesto()).add(compraRetencionIf.getValorRetenido());
								BigDecimal nuevovalor=compraRetencionIf.getValorRetenido();
								if (mapaCamposValoresMeses.get(compraRetencionIf.getCodigoImpuesto()).get(meses[mes]) == null) {
									mapaMesValor.put(meses[mes],nuevovalor );				
									mapaCamposValoresMeses.put(compraRetencionIf.getCodigoImpuesto(),mapaMesValor);					
								} else {
									mapaMesValor.put(meses[mes], nuevovalor.add(mapaCamposValoresMeses.get(compraRetencionIf.getCodigoImpuesto()).get(meses[mes])));				
									mapaCamposValoresMeses.put(compraRetencionIf.getCodigoImpuesto(),mapaMesValor);					
								} 
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
			//comprasResumenColeccion.add(compraResumenData);
			
  
			
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
			//comprasResumenColeccion.add(compraResumenData);
		}
		//Collections.sort(comprasResumenColeccion,ordenadorArrayListPorCodigo);
	}
	
	
	Comparator<ComprasIvaRetencionResumenData> ordenadorArrayListPorCodigo = new Comparator<ComprasIvaRetencionResumenData>(){
		public int compare(ComprasIvaRetencionResumenData o1, ComprasIvaRetencionResumenData o2) {
			return (o1.getCodigo().compareTo(o2.getCodigo()));
		}		
	};
	
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
	
	
	private void initListeners() {
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				clean();
				
				//long start=System.currentTimeMillis();
				
				cargarTabla();
				
				//long fin=System.currentTimeMillis();
				//System.out.println("---------------------123: "+(fin-start)/1000+" seg");
			}
		});
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblFacturacion().getModel();
		
		for(int i= this.getTblFacturacion().getRowCount();i>0;--i)
			model.removeRow(i-1);
		
		
	}
	
	public void cleanTotales(){
		facturacionColeccion = null;
		facturacionColeccion = new Vector<ImpuestosMensualesData>();
		mapaCamposValoresMeses.clear();
		totalTotal = new BigDecimal(0);
		mapaFacturaRetenciones.clear();
		mesConsultadosMap.clear();
	}
	
	
	public void generarColeccionFacturas(){
		try {
			facturasMap = null;
			facturasMap = new HashMap();
			
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
			//ArrayList facturas = (ArrayList)SessionServiceLocator.getFacturaSessionService().findFacturasCarterasAndCarterasDetalleByQueryByFechaInicioAndByFechaFin(facturasMap, fechaInicio, fechaFin);

			//ArrayList facturas = (ArrayList)SessionServiceLocator.getFacturaSessionService().findFacturasCarterasAndCarterasDetalleByQueryByFechaInicioAndByFechaFin(facturasMap,new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()));
			ArrayList facturas = (ArrayList)SessionServiceLocator.getFacturaSessionService().findFacturasCarterasAndCarterasDetalleByQueryByFechaInicioAndByFechaFinEmpresaId(facturasMap,new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()),Parametros.getIdEmpresa());

			facturasMap.put("estado", ESTADO_ANULADO);
			//ArrayList facturasAnuladas = (ArrayList)SessionServiceLocator.getFacturaSessionService().findFacturasByQueryByFechaInicioAndByFechaFin(facturasMap,new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()));
			ArrayList facturasAnuladas = (ArrayList)SessionServiceLocator.getFacturaSessionService().findFacturasByQueryByFechaInicioAndByFechaFinEmpresaId(facturasMap,new java.sql.Timestamp(fechaInicio.getTime()), new java.sql.Timestamp(fechaFin.getTime()),Parametros.getIdEmpresa());
			cargarMapasFacturaCartera(facturas, facturasAnuladas);
	 
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}		
	}
	
	 
	
	public void cargarMapasFacturaCartera(ArrayList facturasCartera_CDetalle, ArrayList facturasAnuladas){
		
		mapaFactura = null;
		mapaCartera = null;
		carteraDetalleVector = null;
		mapaFactura = new LinkedMap();
		mapaCartera = new HashMap<Long,CarteraIf>();
		carteraDetalleVector = new Vector<CarteraDetalleIf>();
		ArrayList<FacturaIf> facturasOrdenadas = new ArrayList<FacturaIf>();
		Iterator facturasCartera_CDetalleIt = facturasCartera_CDetalle.iterator();
		while (facturasCartera_CDetalleIt.hasNext()) {
			Object[] facturasCartera_CDetalleObject = (Object[]) facturasCartera_CDetalleIt.next();
			FacturaIf facturaIf = (FacturaIf) facturasCartera_CDetalleObject[0];
			mapaFactura.put(facturaIf.getId(),facturaIf);
			CarteraIf carteraIf = (CarteraIf) facturasCartera_CDetalleObject[1];
			mapaCartera.put(carteraIf.getReferenciaId(),carteraIf);
			CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) facturasCartera_CDetalleObject[2];
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
				mapaFacturaRetenciones.put(factura,mapaRetenciones);
			}
			 
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	public void cargarCuentasMap(){
		
		Map parameterMap = new HashMap();
		parameterMap.put("codigoNombreCuenta", "%");
		parameterMap.put("estado", "A");
		parameterMap.put("imputable", "S");
		try {
			int tamanoLista = SessionServiceLocator.getCuentaSessionService().findCuentaByQueryByUsuarioIdAndCodigoOrNombreListSize(parameterMap, ((UsuarioIf) Parametros.getUsuarioIf()).getId());
			
			CuentaIf cuentaIf = null;
			if (tamanoLista > 1) {
				Iterator cuentaIterator = SessionServiceLocator.getCuentaSessionService().findCuentaByQueryByUsuarioIdAndCodigoOrNombre(parameterMap, ((UsuarioIf) Parametros.getUsuarioIf()).getId()).iterator();
				while (cuentaIterator.hasNext()) {
					cuentaIf = (CuentaIf) cuentaIterator.next();
					cuentasCodigoMap.put(cuentaIf.getCodigo(), cuentaIf.getId());
					cuentasNombreMap.put(cuentaIf.getNombre(), cuentaIf.getId());
				}
			} 
		} catch (GenericBusinessException ex) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			ex.printStackTrace();
		}	
		 
	}
	
 
	//johy
	private void cargarTabla() {
		
		
		anchoColumnasTabla();
		cleanTotales();		
		String tipo="";
		
		if(getCmbTipoImpuesto().getSelectedItem().equals(IMPUESTO_IVA))tipo="I";
		if(getCmbTipoImpuesto().getSelectedItem().equals(IMPUESTO_RENTA))tipo="R";
		if(getCmbTipoImpuesto().getSelectedItem().equals(AMBOS))tipo="";
		
		//Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
		//int anio=fechaInicio.getYear();
		//anio=1900+anio;
		java.util.Date fechaInicio = getCmbFechaInicio().getDate();
		if ( fechaInicio == null ){
			SpiritAlert.createAlert("Seleccionar Fecha Inicio!!", SpiritAlert.WARNING);
			return;
		}
		java.util.Date fechaFin = getCmbFechaFin().getDate();
		if ( fechaFin == null ){
			SpiritAlert.createAlert("Seleccionar Fecha Fin!!", SpiritAlert.WARNING);
			return;
		}
		
		Calendar calFechaInicio = new GregorianCalendar();
		calFechaInicio.setTime(fechaInicio);
		int anio = calFechaInicio.get(Calendar.YEAR);
				
		
		if(tipo.equals("I"))
		{	 
			generarColeccionFacturas();	//llena vector mapaFacturaRetenciones			 
			Iterator mapaFacturaRetencionesIt = mapaFacturaRetenciones.keySet().iterator();
			while(mapaFacturaRetencionesIt.hasNext()){
				FacturaIf facturaIf = (FacturaIf)mapaFacturaRetencionesIt.next();
				tableModel = (DefaultTableModel) getTblFacturacion().getModel();
				Vector<String> fila = new Vector<String>();
				agregarColumnasCampo_401_408_418_434(facturaIf, fila);				  
			}
			
			ArrayList asientosDetallesList = getAsientosDetallesList_new();								
			generarMovimientosCuentas_IVA("21040100001","411",asientosDetallesList);//busca los asientosDetalle compara con la cta=21040100001 y luego toma los asientosdetalles correcto y llena los valores de las n/c llena 421 y mapaAsientosDetalleMes411			
			agregarColumnasCampo_411_421_429_434_444_480_482_484();
		    generarMovimientosCuentas_IVA("13030100001","501Costo",asientosDetallesList);//busca los asientosDetalle compara con la cta=13033100001 y luego toma los asientosdetalles correcto y llena los valores de las n/c//mapaAsientosDetalleMes501Costo
		 	generarMovimientosCuentas_IVA("13030100002","501Gasto",asientosDetallesList);// llena 521 y mapaAsientosDetalleMes501Gasto			
			generarColeccionRetenc3_7_100(tipo); //llena el 517 (resumen del iva de compra el 0%)
			/*generarMovimientosCuentas_IVA("21020200016","721",asientosDetallesList);
			generarMovimientosCuentas_IVA("21020200017","722",asientosDetallesList);
			generarMovimientosCuentas_IVA("21020200015","725",asientosDetallesList);*/			
			generarMovimientosCuentas_IVA("21020200016","722",asientosDetallesList);
			generarMovimientosCuentas_IVA("21020200017","721",asientosDetallesList);
			generarMovimientosCuentas_IVA("21020200015","725",asientosDetallesList);			
			generarMovimientosCuentas_IVA("11020300030","534_544",asientosDetallesList);
			generarMovimientosCuentas_IVA("11020300040","534_544",asientosDetallesList);
			/*generarMovimientosCuentas_IVA("21040200006","9990_9991",asientosDetallesList);			
			generarMovimientosCuentas_IVA("21040200005","9990_9991",asientosDetallesList); */
			generarMovimientosCuentas_IVA("IMPUESTO RET IVA "+anio+" POR PAGAR","9990_9991",asientosDetallesList);		
			generarMovimientosCuentas_IVA("IMPUESTO IVA "+anio+" POR PAGAR","9990_9991",asientosDetallesList);
						
			agregarColumnasCampo_501_511_521_529_534_544_507_509_519_553_554_9902();		//generaColeccionCompras();
			generarMovimientosCuentas_IVA("13030100020","607_617",asientosDetallesList);//credito fiscal sumatoria de debitos y creditos(movimientos de cuentas)
			generarMovimientosCuentas_IVA("13030100010","609",asientosDetallesList);
			generarMovimientosCuentas_IVA("13030100011","609",asientosDetallesList);
			generarMovimientosCuentas_IVA("13030100012","609",asientosDetallesList);					
			generarMovimientosCuentas_IVA("21040200006","897",asientosDetallesList);
			generarMovimientosCuentas_IVA("11020250051","variasN/C",asientosDetallesList);//notas de credito por utilizar //908=NUM N/C		//909= 11020250051 ret iva/09 			//generarCampos605_615("13030100021");
			
			agregarColumnasCampo601_602_605_615_617_619_699_799_859_902_907_905();	//897= 2104020006 glosa SUSTITUTIVA	//799:721+723+725*	//859:699+799*	//897= 2104020006 glosa SUSTITUTIVA*	//902=859-897*		//905=902-909	//si 905 es mayor que cero... entonces revisar si queda algo "POR PAGAR"
			mapaCamposValoresMeses.remove("temp");
			mapaCamposValoresMeses.remove("temp1"); 
			
		} 
		
		//pp  
		if(tipo.equals("R")) 
		{	
			
			ArrayList asientosDetallesList = getAsientosDetallesList_new();
			//generarMovimientosCuentas_Rentas("21020200051","9990_9991",asientosDetallesList);
			generarMovimientosCuentas_Rentas("RETENCION FUENTE "+anio+" POR PAGAR","9990_9991",asientosDetallesList);			
			generarMovimientosCuentas_Rentas("21020200020","352",asientosDetallesList);//el valor del impuesto a la renta de los empleados busco en la cta 21020200020
			 			
			generarColeccionRetenc3_7_100("R");//  332,333,322,372,309,303,304			
			
			generarMovimientosCuentas_Rentas("21020200014","309",asientosDetallesList);//aumentar centavos
			generarMovimientosCuentas_Rentas("11020250051","variasN/C",asientosDetallesList); //908_909_910_911_912_913_914_915_RENTAS	 //notas de credito para el impuesto a la renta
			
		 
			agregarColumnasCampoRenta_302_porcentaje_sumatoria_9992(anio);//(toma de la porcentaje y de la fórmula) y llena el 9992			
				//llena el campo 302 buscando por rol mensual de sueldo en relacion de dependencia
				// por lo general llena estos campos o mas 303_353_304_354_307_357_308_358_309_359_310_360_320_370_341_391 //por formula se llenan: 349,399,429,498,499,902,999			 
				//luego q se llena el 322 y 333... lo ponemos la suma de los dos en uno
			if(mapaCamposValoresMeses.get("333")!=null)	 	mapaCamposValoresMeses.remove("333");
			mapaCamposValoresMeses.remove("temp");
			mapaCamposValoresMeses.remove("temp1");
			
			
			
		}	
		
		//long ii=System.currentTimeMillis();
		
		mostrarTablaWidth();//muestra todos los campos de mapaCamposValoresMeses//Iterator mapaClienteMesesIt = mapaCamposValoresMeses.keySet().iterator();	 
		Map mapOrdenado = new TreeMap(mapaCamposValoresMeses);
		Set ref = mapOrdenado.keySet();
		Iterator it = ref.iterator();
		while (it.hasNext()) {			
			String campo = (String)it.next();
			tableModel = (DefaultTableModel) getTblFacturacion().getModel();
			Vector<String> fila = new Vector<String>();
			ImpuestosMensualesData impuestosMensualesData = new ImpuestosMensualesData();
			agregarColumnasTablaFacturas(campo, fila, impuestosMensualesData,tipo);			
			tableModel.addRow(fila);
		} 
		
		//long fini=System.currentTimeMillis();
		//System.out.println("---------------------hacer tabla: "+(fini-ii)/1000+" seg");
		//System.out.println("---------------------hacer tabla: "+(fini-ii)/1000+" seg");
		
	}
	 
	public void moverValores(String codPrimero,String codSegundo,String mes)
	{
		if(mapaCamposValoresMeses.get("372")!=null && mapaCamposValoresMeses.get("322")!=null){
			if(mapaCamposValoresMeses.get("372").get(mes)!=null && mapaCamposValoresMeses.get("322").get(mes)!=null){
				double valor372=mapaCamposValoresMeses.get("322").get(mes).doubleValue();
				double valor322=mapaCamposValoresMeses.get("372").get(mes).doubleValue();			
				Map<String,BigDecimal> mapaMesValor605 = new HashMap<String,BigDecimal>();
				if(mapaCamposValoresMeses.get("372")!=null)
				{					
					Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("372").keySet().iterator();
					while(mapaMesValorIt2.hasNext()){
						String key = (String)mapaMesValorIt2.next();
						mapaMesValor605.put(key,mapaCamposValoresMeses.get("372").get(key));
					}
				}	
				mapaMesValor605.put(mes,new BigDecimal(valor372));
				mapaCamposValoresMeses.put("372",mapaMesValor605);			
				Map<String,BigDecimal> mapaMesValor322= new HashMap<String,BigDecimal>();
				if(mapaCamposValoresMeses.get("322")!=null)
				{					
					Iterator mapaMesValorIt322 = mapaCamposValoresMeses.get("322").keySet().iterator();
					while(mapaMesValorIt322.hasNext()){
						String key = (String)mapaMesValorIt322.next();
						mapaMesValor322.put(key,mapaCamposValoresMeses.get("322").get(key));
					}
				}	
				mapaMesValor322.put(mes,new BigDecimal(valor322));
				mapaCamposValoresMeses.put("322",mapaMesValor322);
			}
		}
	}
	
	
	public void generarMovimientosCuentas_IVA(String cuentaNombreorCodigo,String tipo,ArrayList asientosDetallesList){		

		Long idCuentaContable = cuentasNombreMap.get(cuentaNombreorCodigo);
		if(idCuentaContable==null) idCuentaContable=cuentasCodigoMap.get(cuentaNombreorCodigo);
		if(idCuentaContable==null) idCuentaContable=cuentasNombreMap.get(cuentaNombreorCodigo+" ");


		try{
			if(tipo.equals("721"))	getAsientosDetallesByCuentaId721(asientosDetallesList, idCuentaContable);		
			if(tipo.equals("722"))	getAsientosDetallesByCuentaId722(asientosDetallesList, idCuentaContable);	
			if(tipo.equals("725"))	getAsientosDetallesByCuentaId725(asientosDetallesList, idCuentaContable);
			if(tipo.equals("609")) 	getAsientosDetallesByCuentaId609(asientosDetallesList, idCuentaContable);	//77
			if(tipo.equals("897"))	getAsientosDetallesByCuentaId897(asientosDetallesList, idCuentaContable);	//77	
			if(tipo.equals("607_617"))		getAsientosDetallesByCuentaId_607_617DebitoCredito(asientosDetallesList, idCuentaContable);	//77
			if(tipo.equals("9990_9991"))	getAsientosDetallesByCuentaId_9990_9991_DebitoCredito(asientosDetallesList, idCuentaContable);		
			if(tipo.equals("variasN/C"))	getAsientosDetallesByCuentaId908_909_910_911_912_913_914_915(asientosDetallesList, idCuentaContable);
			if(tipo.equals("534_544"))		getAsientosDetallesByCuentaId_534_544(asientosDetallesList, idCuentaContable);//77
			if(tipo.equals("501Costo"))	 	getAsientosDetallesByCuentaId_501Costo(asientosDetallesList, idCuentaContable);		
			if(tipo.equals("501Gasto"))	 	getAsientosDetallesByCuentaId_501Gasto(asientosDetallesList, idCuentaContable);
			if(tipo.equals("411"))			getAsientosDetallesByCuentaId_411(asientosDetallesList, idCuentaContable);	

		} catch(Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Verifique los nombre de una de las cuentas no existe.", SpiritAlert.ERROR);			
		}

		//return cuentaInicial.getTipocuentaId();
	} 



	public void generarMovimientosCuentas_Rentas(String cuentaNombreorCodigo,String tipo,ArrayList asientosDetallesList){	

		Long idCuentaContable = null;

		try{
			idCuentaContable = cuentasNombreMap.get(cuentaNombreorCodigo);
			if(idCuentaContable==null) idCuentaContable=cuentasCodigoMap.get(cuentaNombreorCodigo);
			if(idCuentaContable==null) idCuentaContable=cuentasNombreMap.get(cuentaNombreorCodigo+" ");

			if(tipo.equals("352"))		getAsientosDetallesByCuentaId352(asientosDetallesList, idCuentaContable);		
			if(tipo.equals("309"))		getAsientosDetallesByCuentaId309_aumentarCentavos(asientosDetallesList, idCuentaContable);
			if(tipo.equals("9990_9991"))getAsientosDetallesByCuentaId_9990_9901_DebitoCreditoRENTAS(asientosDetallesList, idCuentaContable);
			if(tipo.equals("variasN/C"))getAsientosDetallesByCuentaId908_909_910_911_912_913_914_915_RENTAS(asientosDetallesList, idCuentaContable);

		}
		catch(Exception e)
		{
			e.printStackTrace();
			SpiritAlert.createAlert("Verifique los nombre de una de las cuentas no existe.", SpiritAlert.ERROR);			
		}
	} 
	
	
	 
 

	//alexander no 
	private void getAsientosDetallesByCuentaId352(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
		 
			for(int i=0; i<asientosDetallesList.size(); i++){
				 ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
				AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
				AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);
			if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();
				
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				String mes3Letras=meses[mes];				
				
				
				llenarCampoValor("352",asientoDetalle.getHaber(),mes);	
				
							
			 
								
			}
		}
	}
	 
	 

	private void getAsientosDetallesByCuentaId309_aumentarCentavos(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
		mapaAsientosDetalleMes501Costo.clear();
		
		for(int i=0; i<asientosDetallesList.size(); i++){
			 ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
			AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);	
			if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();	
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				String mes3Letras=meses[mes];
				//POR AJE ENE/09 AJSUTE				
				if(asientoDetalle.getGlosa().contains(" "+mes3Letras+"/") && asientoDetalle.getGlosa().contains("POR AJE"))
				//if(asientoDetalle.getGlosa().contains("AJSUTE"))					
					{
					
						llenarCampoValor("309",asientoDetalle.getHaber(),mes);
					}
								
			}
		}
		
	}
	 
 

	private void getAsientosDetallesByCuentaId721(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
		
		int contando=0;
		
		
		for(int i=0; i<asientosDetallesList.size(); i++){
			 ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
			AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);
			if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();
				int anio=asientoif.getFecha().getYear();
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				
				if(contando==0){
					Map<String,BigDecimal> mapaMesValor605 = new HashMap<String,BigDecimal>();
					if(mapaCamposValoresMeses.get("721")!=null)
					{					
						Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("721").keySet().iterator();
						while(mapaMesValorIt2.hasNext()){
							String key = (String)mapaMesValorIt2.next();
							mapaMesValor605.put(key,mapaCamposValoresMeses.get("721").get(key));
						}
					}	
					mapaMesValor605.put(meses[mes],new BigDecimal("0.00"));
					mapaCamposValoresMeses.put("721",mapaMesValor605);	
				}
				contando++;
				llenarCampoValor("721",asientoDetalle.getHaber(),mes);		 
			}
			
		} 
		
		
	}
	
	
	  

	private void getAsientosDetallesByCuentaId722(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
		
		
		int contandoNo=0;
		int contando=0;
		
		for(int i=0; i<asientosDetallesList.size(); i++){
			 ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
			AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);			
			if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();
				int anio=asientoif.getFecha().getYear();
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				if(contando==0){
					Map<String,BigDecimal> mapaMesValor605 = new HashMap<String,BigDecimal>();
					if(mapaCamposValoresMeses.get("722")!=null)
					{					
						Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("722").keySet().iterator();
						while(mapaMesValorIt2.hasNext()){
							String key = (String)mapaMesValorIt2.next();
							mapaMesValor605.put(key,mapaCamposValoresMeses.get("722").get(key));
						}
					}	
					mapaMesValor605.put(meses[mes],new BigDecimal("0.00"));
					mapaCamposValoresMeses.put("722",mapaMesValor605);	
				}
				
				
				
				llenarCampoValor("722",asientoDetalle.getHaber(),mes);	
				contando++;
			}
		}
	}
	
	 

	private void getAsientosDetallesByCuentaId725(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
				
		int contandoNo=0;
		int contando=0;
		
		for(int i=0; i<asientosDetallesList.size(); i++){
			 ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
			AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);	
			if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();
				int anio=asientoif.getFecha().getYear();
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				
				if(contando==0){
					Map<String,BigDecimal> mapaMesValor605 = new HashMap<String,BigDecimal>();
					if(mapaCamposValoresMeses.get("725")!=null)
					{					
						Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("725").keySet().iterator();
						while(mapaMesValorIt2.hasNext()){
							String key = (String)mapaMesValorIt2.next();
							mapaMesValor605.put(key,mapaCamposValoresMeses.get("725").get(key));
						}
					}	
					mapaMesValor605.put(meses[mes],new BigDecimal("0.00"));
					mapaCamposValoresMeses.put("725",mapaMesValor605);	
				} 
				
				llenarCampoValor("725",asientoDetalle.getHaber(),mes);
				contando++;
			}
		}
	}
	
	
	 
	
	private void getAsientosDetallesByCuentaId_9990_9901_DebitoCreditoRENTAS(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		

		for(int i=0; i<asientosDetallesList.size(); i++){
			ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
			AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);

			if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();
				int anio=asientoif.getFecha().getYear();
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				String mes3Letras=meses[mes];
				//CREACIONAL IMPUESTOS RET FTE IVA MAR-09

				//por cada detalle de asiento tengo q ver de acuerdo a la glosa a que mes pertenece 
				if (mesConsultadosMap.get(0)!=null)   datosTemporales(asientoDetalle.getGlosa(),0,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				if (mesConsultadosMap.get(1)!=null)  datosTemporales(asientoDetalle.getGlosa(),1,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				if (mesConsultadosMap.get(2)!=null)  datosTemporales(asientoDetalle.getGlosa(),2,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				if (mesConsultadosMap.get(3)!=null)  datosTemporales(asientoDetalle.getGlosa(),3,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				if (mesConsultadosMap.get(4)!=null)  datosTemporales(asientoDetalle.getGlosa(),4,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				if (mesConsultadosMap.get(5)!=null)  datosTemporales(asientoDetalle.getGlosa(),5,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				if (mesConsultadosMap.get(6)!=null)  datosTemporales(asientoDetalle.getGlosa(),6,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				if (mesConsultadosMap.get(7)!=null)  datosTemporales(asientoDetalle.getGlosa(),7,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				if (mesConsultadosMap.get(8)!=null)  datosTemporales(asientoDetalle.getGlosa(),8,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				if (mesConsultadosMap.get(9)!=null)  datosTemporales(asientoDetalle.getGlosa(),9,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				if (mesConsultadosMap.get(10)!=null)  datosTemporales(asientoDetalle.getGlosa(),10,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				if (mesConsultadosMap.get(11)!=null)  datosTemporales(asientoDetalle.getGlosa(),11,asientoDetalle.getHaber(),asientoDetalle.getDebe());

			}
		}
	}
	
	//RENTAS
	public void datosTemporales(String glosa,int mes,BigDecimal valorCredito,BigDecimal valorDebito)
	{
		String mes3Letras=meses[mes];
		if(glosa.contains(mes3Letras) &&  !glosa.contains("IVA") && glosa.contains("CREACIONAL IMPUESTOS") )
		{	
			llenarCampoValor("9990",valorCredito,mes);			
		}
	else
		{
			/*if(glosa.contains(mes3Letras) && !glosa.contains("IVA") && glosa.contains("POR PAGO") )						
			{		
				llenarCampoValor("9991",valorDebito,mes);				
			}*/
		}
	}
	
	
	public void datosTemporalesIVA(String glosa,int mes,BigDecimal valorCredito,BigDecimal valorDebito)
	{
		String mes3Letras=meses[mes];	
		if(glosa.contains(mes3Letras) &&  glosa.contains("IVA") && glosa.contains("CREACIONAL IMPUESTOS") )
		{	
			llenarCampoValor("9990",valorCredito,mes);			
		}
	    else
		{
			/*if(glosa.contains(mes3Letras) && glosa.contains("IVA") && glosa.contains("POR PAGO") )						
			{		
				llenarCampoValor("9991",valorDebito,mes);				
			}
			else{
				llenarCampoValor("9991",BigDecimal.ZERO,mes);	
			}*/
			
		}
	}
	  


	private void getAsientosDetallesByCuentaId_9990_9991_DebitoCredito(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
	
		for(int i=0; i<asientosDetallesList.size(); i++){
			 	ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
				AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
				AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);			
				if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();
				int anio=asientoif.getFecha().getYear();
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				String mes3Letras=meses[mes];
				//CREACIONAL IMPUESTOS RET FTE IVA MAR-09				
				//System.out.println(mes3Letras+"LLENANDO LOS TEMPORALES 1000,1001**************************************************************"+asientoDetalle.getGlosa());			 
				//por cada detalle de asiento tengo q ver de acuerdo a la glosa a que mes pertenece 
				 if (mesConsultadosMap.get(0)!=null)  datosTemporalesIVA(asientoDetalle.getGlosa(),0,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				 if (mesConsultadosMap.get(1)!=null)  datosTemporalesIVA(asientoDetalle.getGlosa(),1,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				 if (mesConsultadosMap.get(2)!=null)  datosTemporalesIVA(asientoDetalle.getGlosa(),2,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				 if (mesConsultadosMap.get(3)!=null)  datosTemporalesIVA(asientoDetalle.getGlosa(),3,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				 if (mesConsultadosMap.get(4)!=null)  datosTemporalesIVA(asientoDetalle.getGlosa(),4,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				 if (mesConsultadosMap.get(5)!=null)  datosTemporalesIVA(asientoDetalle.getGlosa(),5,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				 if (mesConsultadosMap.get(6)!=null)  datosTemporalesIVA(asientoDetalle.getGlosa(),6,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				 if (mesConsultadosMap.get(7)!=null)  datosTemporalesIVA(asientoDetalle.getGlosa(),7,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				 if (mesConsultadosMap.get(8)!=null)  datosTemporalesIVA(asientoDetalle.getGlosa(),8,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				 if (mesConsultadosMap.get(9)!=null)  datosTemporalesIVA(asientoDetalle.getGlosa(),9,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				 if (mesConsultadosMap.get(10)!=null)  datosTemporalesIVA(asientoDetalle.getGlosa(),10,asientoDetalle.getHaber(),asientoDetalle.getDebe());
				 if (mesConsultadosMap.get(11)!=null)  datosTemporalesIVA(asientoDetalle.getGlosa(),11,asientoDetalle.getHaber(),asientoDetalle.getDebe());
			}
		}
	}
	 
	
	public void generar_302(String mes,String anio){		
			TipoRolIf tipoRolIf = null;
			TipoContratoIf tipoContratoIf = null;
		try {		
			Map<String,Object> mapaParametros = null;
			mapaParametros =  new HashMap<String, Object>();
			//codigo "RD" relacion de dependencia
			ArrayList<TipoContratoIf> tipoContratoCollection;	
			
			tipoContratoIf = (TipoContratoIf) tipoContratoMap.get("RD");			
			mapaParametros.put("tipocontratoId",tipoContratoIf.getId());	
			
			tipoRolIf = (TipoRolIf) tipoRolMap.get("RM");			
			
			mapaParametros.put("tiporolId",tipoRolIf.getId());				
			mapaParametros.put("mes", mes);
			mapaParametros.put("anio", anio);
			
			Collection<Long> contratosIdCollection = null;		
			RolPagoIf rolPagoIf = null;
			ArrayList<RolPagoIf> rolPagoCollection = (ArrayList<RolPagoIf>) SessionServiceLocator.getRolPagoSessionService().findRolPagoByQuery(mapaParametros);
			if ( rolPagoCollection.size() == 1 ){
				rolPagoIf = (RolPagoIf) rolPagoCollection.iterator().next();
				Collection<Long> contratosId = null;			
				TipoRol tipoRol; 
				tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
				contratosId = Contratos.obtenerContratosId(tipoRol,tipoContratoIf,rolPagoIf,null);
				if ( contratosId!=null && contratosId.size() > 0 ){
					contratosIdCollection = contratosId;								
				} 
			}	
			
			TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
			Double sumatoriasSueldos = SessionServiceLocator.getRolPagoSessionService().sumaSueldos(contratosIdCollection, rolPagoIf, tipoRolIf, tipoRol,null,false);
			DecimalFormat formatoDosEnteros=null;
			
			String mesAntes = new Integer(new Integer(mes).intValue()-1).toString();		 
			llenarCampoValor("302",new BigDecimal(sumatoriasSueldos),new Integer(mesAntes).intValue());
			
			 
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void generar_rentas905_907(String mes){
		
	Double valor907 = 0D;	
		
		if(mapaCamposValoresMeses.get("909")!=null && mapaCamposValoresMeses.get("909").get(mes)!=null)			
			valor907=valor907 +mapaCamposValoresMeses.get("909").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("911")!=null && mapaCamposValoresMeses.get("911").get(mes)!=null)			
			valor907=valor907 + mapaCamposValoresMeses.get("911").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("913")!=null && mapaCamposValoresMeses.get("913").get(mes)!=null)			
			valor907=valor907 + mapaCamposValoresMeses.get("913").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("915")!=null && mapaCamposValoresMeses.get("915").get(mes)!=null)			
			valor907=valor907 + mapaCamposValoresMeses.get("915").get(mes).doubleValue();
		
	 
			Map<String,BigDecimal> mapaMesValor907 = new HashMap<String,BigDecimal>();
			if(mapaCamposValoresMeses.get("907")!=null)
			{					
				Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("907").keySet().iterator();
				while(mapaMesValorIt2.hasNext()){
					String key = (String)mapaMesValorIt2.next();
					mapaMesValor907.put(key,mapaCamposValoresMeses.get("907").get(key));
				}
			}
			mapaMesValor907.put(mes,new BigDecimal(valor907));
			mapaCamposValoresMeses.put("907",mapaMesValor907);
		 
		
		Double valor905 = 0D;		
		Map<String,BigDecimal> mapaMesValor905 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("905")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("905").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValor905.put(key,mapaCamposValoresMeses.get("905").get(key));
			}
		}		
		if(mapaCamposValoresMeses.get("902")!=null && mapaCamposValoresMeses.get("902").get(mes)!=null)			
			valor905=mapaCamposValoresMeses.get("902").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("909")!=null && mapaCamposValoresMeses.get("909").get(mes)!=null)			
			valor905=valor905-mapaCamposValoresMeses.get("909").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("911")!=null && mapaCamposValoresMeses.get("911").get(mes)!=null)			
			valor905=valor905-mapaCamposValoresMeses.get("911").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("913")!=null && mapaCamposValoresMeses.get("913").get(mes)!=null)			
			valor905=valor905-mapaCamposValoresMeses.get("913").get(mes).doubleValue();
		 
		if(mapaCamposValoresMeses.get("915")!=null && mapaCamposValoresMeses.get("915").get(mes)!=null)			
			valor905=valor905-mapaCamposValoresMeses.get("915").get(mes).doubleValue();
		
		mapaMesValor905.put(mes,new BigDecimal(valor905));
		mapaCamposValoresMeses.put("905",mapaMesValor905);
		 
		
		
		 BigDecimal valor9992=BigDecimal.ZERO;
		Double valor9992_double = 0D;
		
		if(mapaCamposValoresMeses.get("905")!=null && mapaCamposValoresMeses.get("905").get(mes)!=null)
		{	
			valor9992=mapaCamposValoresMeses.get("905").get(mes);
			valor9992_double=valor9992.doubleValue();
		}
		
		if(mapaCamposValoresMeses.get("9990")!=null && mapaCamposValoresMeses.get("9990").get(mes)!=null)
		{	
			valor9992=mapaCamposValoresMeses.get("9990").get(mes);
			valor9992_double=valor9992_double - valor9992.doubleValue();
		}
		
		
		Map<String,BigDecimal> mapaMesValorDebe9992 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("9991")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("9991").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValorDebe9992.put(key,mapaCamposValoresMeses.get("9991").get(key));
			}
		}
		mapaMesValorDebe9992.put(mes,new BigDecimal(valor9992_double));
		mapaCamposValoresMeses.put("9991",mapaMesValorDebe9992);
		
	}

	private void agregarColumnasCampoRenta_302_porcentaje_sumatoria_9992(int anio) {	
		if (mesConsultadosMap.get(0)!=null) {
			moverValores("322","372",meses[0]);//cambia los valores de 322 a 372 y de 372 a 322
			generar_302("01",new Integer(anio).toString());//rol de pago suma columna de sueldo		 
			recorrerListaColumnasCampoRentasPorcentaje(meses[0]);
			recorrerListaColumnasCampoRentasSumatoria(meses[0],0);
			generar_rentas905_907(meses[0]);
		}
		if (mesConsultadosMap.get(1)!=null){
			moverValores("322","372",meses[1]);
			generar_302("02",new Integer(anio).toString());//rol de pago suma columna de sueldo			
			recorrerListaColumnasCampoRentasPorcentaje(meses[1]);
			recorrerListaColumnasCampoRentasSumatoria(meses[1],1);
			generar_rentas905_907(meses[1]);
		}
		if (mesConsultadosMap.get(2)!=null){
			moverValores("322","372",meses[2]);
			generar_302("03",new Integer(anio).toString());//rol de pago suma columna de sueldo
			recorrerListaColumnasCampoRentasPorcentaje(meses[2]);
			recorrerListaColumnasCampoRentasSumatoria(meses[2],2);
			generar_rentas905_907(meses[2]);
		}
		if (mesConsultadosMap.get(3)!=null){
			moverValores("322","372",meses[3]);
			generar_302("04",new Integer(anio).toString());//rol de pago suma columna de sueldo
			recorrerListaColumnasCampoRentasPorcentaje(meses[3]);
			recorrerListaColumnasCampoRentasSumatoria(meses[3],3);
			generar_rentas905_907(meses[3]);
		}
		if (mesConsultadosMap.get(4)!=null){
			moverValores("322","372",meses[4]);
			generar_302("05",new Integer(anio).toString());//rol de pago suma columna de sueldo
			recorrerListaColumnasCampoRentasPorcentaje(meses[4]);
			recorrerListaColumnasCampoRentasSumatoria(meses[4],4);
			generar_rentas905_907(meses[4]);
		}
		if (mesConsultadosMap.get(5)!=null){
			moverValores("322","372",meses[5]);
			generar_302("06",new Integer(anio).toString());//rol de pago suma columna de sueldo
			recorrerListaColumnasCampoRentasPorcentaje(meses[5]);
			recorrerListaColumnasCampoRentasSumatoria(meses[5],5);
			generar_rentas905_907(meses[5]);
		}
		if (mesConsultadosMap.get(6)!=null){
			moverValores("322","372",meses[6]);
			generar_302("07",new Integer(anio).toString());//rol de pago suma columna de sueldo
			recorrerListaColumnasCampoRentasPorcentaje(meses[6]);
			recorrerListaColumnasCampoRentasSumatoria(meses[6],6);
			generar_rentas905_907(meses[6]);
		}
		if (mesConsultadosMap.get(7)!=null){
			moverValores("322","372",meses[7]);
			generar_302("08",new Integer(anio).toString());//rol de pago suma columna de sueldo
			recorrerListaColumnasCampoRentasPorcentaje(meses[7]);
			recorrerListaColumnasCampoRentasSumatoria(meses[7],7);
			generar_rentas905_907(meses[7]);
		}
		if (mesConsultadosMap.get(8)!=null){
			moverValores("322","372",meses[8]);
			generar_302("09",new Integer(anio).toString());//rol de pago suma columna de sueldo
			recorrerListaColumnasCampoRentasPorcentaje(meses[8]);
			recorrerListaColumnasCampoRentasSumatoria(meses[8],8);
			generar_rentas905_907(meses[8]);
		}
		if (mesConsultadosMap.get(9)!=null){
			moverValores("322","372",meses[9]);
			generar_302("10",new Integer(anio).toString());//rol de pago suma columna de sueldo
			recorrerListaColumnasCampoRentasPorcentaje(meses[9]);
			recorrerListaColumnasCampoRentasSumatoria(meses[9],9);
			generar_rentas905_907(meses[9]);
		}
		if (mesConsultadosMap.get(10)!=null){
			moverValores("322","372",meses[10]);
			generar_302("11",new Integer(anio).toString());//rol de pago suma columna de sueldo
			recorrerListaColumnasCampoRentasPorcentaje(meses[10]);
			recorrerListaColumnasCampoRentasSumatoria(meses[10],10);
			generar_rentas905_907(meses[10]);
		}
		if (mesConsultadosMap.get(11)!=null){
			moverValores("322","372",meses[11]);
			generar_302("12",new Integer(anio).toString());//rol de pago suma columna de sueldo
			recorrerListaColumnasCampoRentasPorcentaje(meses[11]);
			recorrerListaColumnasCampoRentasSumatoria(meses[11],11);
			generar_rentas905_907(meses[11]);
		}				 
	}

	
	
	
	
	
	
	public void recorrerListaColumnasCampoRentasSumatoria(String mes,int mes2){
		//ORDENAR EL MAP: para que las formulas concuerden
		Map mapOrdenado = new TreeMap(mapasCamposRentaFormularioSumatoria);
		Set ref = mapOrdenado.keySet();
		Iterator it = ref.iterator();
		while (it.hasNext()) {			
			Integer campo = (Integer)it.next();
			String campo2=mapasCamposRentaFormularioSumatoria.get(campo);			
			if(campo2!=null)   agregarCamposRentaSumatoria(campo.toString(),campo2,mes2);
		}
 
	}
	
	public void agregarCamposRentaSumatoria(String campo,String formula,int mes){		
		//548//jj
		//584+549
		BigDecimal nuevovalor=BigDecimal.ZERO;
		boolean primera=true;
		if(formula.length()>0)
		{
			String subs=formula;
			if(formula.contains("+") || formula.contains("-"))
			{
				while(subs.contains("+") || subs.contains("-")){
					int pos1=subs.indexOf("+");
					
					int pos2=subs.indexOf("-");
					int pos=0;
					if(pos1>pos2) 
						{if(pos2==-1) pos=pos1;
						else pos=pos2;
						}
					if(pos2>pos1) 
					{if(pos1==-1) pos=pos2;
					else pos=pos1;
					}		
					
					if(pos==-1){
						String tem=subs;						
						pos=formula.indexOf("-");						
						tem=tem.substring(0,pos);						
						if(primera)
							nuevovalor=valordelCampo(tem,meses[mes]);
						else
							nuevovalor=nuevovalor.subtract(valordelCampo(tem,meses[mes]));	
						
						
						subs=subs.substring(pos+1);
						primera=false;
						
					}
					else{						
						String tem=subs;
						tem=tem.substring(0,pos);						
						nuevovalor=nuevovalor.add(valordelCampo(tem,meses[mes]));						
						subs=subs.substring(pos+1);
						primera=false;		
						
					}						
				}
				
				if(subs.length()!=0)
				{
					nuevovalor=nuevovalor.add(valordelCampo(subs,meses[mes]));	
				}
				
			}
			else{
				nuevovalor=valordelCampo(formula,meses[mes]);
				
			}
	 
			//llenarCampoValor(campo,nuevovalor,mes);
			
			Map<String,BigDecimal> mapaMesValor605 = new HashMap<String,BigDecimal>();
			if(mapaCamposValoresMeses.get(campo)!=null)
			{					
				Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get(campo).keySet().iterator();
				while(mapaMesValorIt2.hasNext()){
					String key = (String)mapaMesValorIt2.next();
					mapaMesValor605.put(key,mapaCamposValoresMeses.get(campo).get(key));
				}
			}	
			mapaMesValor605.put(meses[mes],nuevovalor);
			mapaCamposValoresMeses.put(campo,mapaMesValor605);
			
			
			
			
		}		
	}
	
	
	public BigDecimal valordelCampo(String campo,String mes)
	{		
		BigDecimal nuevovalor=BigDecimal.ZERO;
		if (mapaCamposValoresMeses.get(campo)!=null && mapaCamposValoresMeses.get(campo).get(mes) != null) {
			nuevovalor=mapaCamposValoresMeses.get(campo).get(mes);					
		}
		return nuevovalor;
	}
	
	
	
	 
	public void recorrerListaColumnasCampoRentasPorcentaje(String mes){
		Iterator mapaClienteMesesIt = mapasCamposRentaFormularioPorcentaje.keySet().iterator();		
		while(mapaClienteMesesIt.hasNext()){
			String campo = (String)mapaClienteMesesIt.next();
			String campo2=mapasCamposRentaFormularioPorcentaje.get(campo);			
			if(campo2!=null)   recorrerListaCamposRentaPorcentaje(campo,campo2,mes);
		}
	}
	

	public void recorrerListaCamposRentaPorcentaje(String campo1,String campo2,String mes){		 
		Map<String,BigDecimal> mapaMesValor303 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get(campo1)!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get(campo1).keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValor303.put(key,mapaCamposValoresMeses.get(campo1).get(key));
			}
		}
		BigDecimal porcentaje= mapaCodigoPorcentajeRenta.get(campo1);		
		
		
		if(porcentaje!=null && porcentaje.doubleValue()!=0D )
		{
			BigDecimal valor303_302=BigDecimal.ZERO;		
			BigDecimal valor353Final=BigDecimal.ZERO;
			if(mapaCamposValoresMeses.get(campo1)!=null && mapaCamposValoresMeses.get(campo1).get(mes)!=null)
				valor303_302=mapaCamposValoresMeses.get(campo1).get(mes);
			
			
			valor353Final=valor303_302;
			valor303_302=BigDecimal.valueOf(valor303_302.doubleValue() / porcentaje.doubleValue());		
			valor303_302=valor303_302.multiply(new BigDecimal("100"));
			mapaMesValor303.put(mes,valor303_302);
			mapaCamposValoresMeses.put(campo1,mapaMesValor303);		
			Map<String,BigDecimal> mapaMesValor353 = new HashMap<String,BigDecimal>();
			if(mapaCamposValoresMeses.get(campo2)!=null)
			{					
				Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get(campo2).keySet().iterator();
				while(mapaMesValorIt2.hasNext()){
					String key = (String)mapaMesValorIt2.next();
					mapaMesValor353.put(key,mapaCamposValoresMeses.get(campo2).get(key));
				}
			}	
			mapaMesValor353.put(mes,valor353Final);
			
			//llenarCampoValor(campo2,valor353Final,)
			mapaCamposValoresMeses.put(campo2,mapaMesValor353);
		}
		
	}
	
	public void mostrarTablaWidth(){
		if (mesConsultadosMap.get(0)!=null) setearAnchoColumna(2);  
		if (mesConsultadosMap.get(1)!=null) setearAnchoColumna(3);
		if (mesConsultadosMap.get(2)!=null) setearAnchoColumna(4);
		if (mesConsultadosMap.get(3)!=null) setearAnchoColumna(5);
		if (mesConsultadosMap.get(4)!=null) setearAnchoColumna(6);
		if (mesConsultadosMap.get(5)!=null) setearAnchoColumna(7);
		if (mesConsultadosMap.get(6)!=null) setearAnchoColumna(8);
		if (mesConsultadosMap.get(7)!=null) setearAnchoColumna(9);
		if (mesConsultadosMap.get(8)!=null) setearAnchoColumna(10);
		if (mesConsultadosMap.get(9)!=null) setearAnchoColumna(11);
		if (mesConsultadosMap.get(10)!=null) setearAnchoColumna(12);
		if (mesConsultadosMap.get(11)!=null) setearAnchoColumna(13);			 
	}
		
		public void setearAnchoColumna(int x) {			
			getTblFacturacion().getColumnModel().getColumn(x).setPreferredWidth(90);
			getTblFacturacion().getColumnModel().getColumn(x).setMinWidth(90);
			getTblFacturacion().getColumnModel().getColumn(x).setMaxWidth(90);
		}
	
	 
	
	
	private void getAsientosDetallesByCuentaId_534_544(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
		
		Long tipodoc_NCC=mapaTipoDocumentoCodigo.get("NCC");
		 

			for(int i=0; i<asientosDetallesList.size(); i++){
				 	ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
					AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
					AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);	
			if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();
				int anio=asientoif.getFecha().getYear();
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				String mes3Letras=meses[mes];	
				
				//mapaTipoDocumentoCodigo
				
				if(!asientoDetalle.getGlosa().contains("N/C #") && asientoif.getTipoDocumentoId()!=tipodoc_NCC)
					{					
						llenarCampoValor("534",asientoDetalle.getDebe(),mes);			
					}
			}
		}
		
		
	}
	
	public void datosTemporalesRENTAS_notasCredito(String glosaOriginal,int mes,BigDecimal valorHaber)
	{
		String mes3Letras=meses[mes];
	 
		if(glosaOriginal.contains(""+mes3Letras+"") && !glosaOriginal.contains("IVA") && (glosaOriginal.contains("RET") || glosaOriginal.contains("RTE")) && glosaOriginal.contains("N/C") )
		{					
			
		
		llenarCampoValor("temp",new BigDecimal("2"),mes);
		llenarCampoValor("temp1",new BigDecimal("2"),mes);
		 
		int contandoNo=mapaCamposValoresMeses.get("temp").get(meses[mes]).intValue();
		int contando=mapaCamposValoresMeses.get("temp1").get(meses[mes]).intValue();
				
		if(contandoNo!=10 && contando!=10)
		 {
			String glosa=glosaOriginal;
			
			
			int uno=glosa.indexOf("N/C:");
			glosa=glosa.substring(uno);
			int dos=glosa.indexOf("RET");
			if(dos==-1) dos=glosa.indexOf("RTE");
			
			glosa=glosa.substring(4,dos);			
			glosa=glosa.trim(); 
			if(glosa.contains(" ")) glosa=glosa.substring(0,glosa.indexOf(" "));
			
			
			
			String campoVariosNo=new Integer(contandoNo+908-2).toString();
			String campoVarios=new Integer(contando+909-2).toString();
			if(glosa.equals("")) glosa="0";	
			glosa=glosa.trim();						
			if(glosa.equals("")) glosa="0";	
			
			 
			try{
				new BigDecimal(glosa);
			}
			catch (Exception e) {
				// TODO: handle exception
				glosa="999999999";
			}
			
			llenarCampoValor(campoVariosNo,new BigDecimal(glosa),mes);
			llenarCampoValor(campoVarios,valorHaber,mes);
		 }
		}
		
	}
	
	
	public void datosTemporalesIVA_notasCredito(String glosaOriginal,int mes,BigDecimal valorHaber)
	{
	
		 
		 
		String mes3Letras=meses[mes];		
		
		if(glosaOriginal.contains("IVA "+mes3Letras+"") && (glosaOriginal.contains("RET") || glosaOriginal.contains("RTE")) && glosaOriginal.contains("N/C") )
		{					
			
		
		llenarCampoValor("temp",new BigDecimal("2"),mes);
		llenarCampoValor("temp1",new BigDecimal("2"),mes);
		 
		int contandoNo=mapaCamposValoresMeses.get("temp").get(meses[mes]).intValue();
		int contando=mapaCamposValoresMeses.get("temp1").get(meses[mes]).intValue();
	 
		if(contandoNo!=10 && contando!=10)
		 {
			String glosa=glosaOriginal;
			int uno=glosa.indexOf("N/C:");
			glosa=glosa.substring(uno);
			int dos=glosa.indexOf("RET");
			if(dos==-1) dos=glosa.indexOf("RTE");
			
			
			
			glosa=glosa.substring(4,dos);
			glosa=glosa.trim(); 
			if(glosa.contains(" ")) glosa=glosa.substring(0,glosa.indexOf(" "));
			
			
			String campoVariosNo=new Integer(contandoNo+908-2).toString();
			String campoVarios=new Integer(contando+909-2).toString();
			if(glosa.equals("")) glosa="0";	
			glosa=glosa.trim();						
			if(glosa.equals("")) glosa="0";	
			
			try{
				new BigDecimal(glosa);
			}
			catch (Exception e) {
				// TODO: handle exception
				glosa="999999999";
			}
			
			llenarCampoValor(campoVariosNo,new BigDecimal(glosa),mes);
			llenarCampoValor(campoVarios,valorHaber,mes);
		 }
		}
		
	}

	private void getAsientosDetallesByCuentaId908_909_910_911_912_913_914_915(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
		if(mapaCamposValoresMeses.get("temp")!=null)	mapaCamposValoresMeses.get("temp").clear();
		if(mapaCamposValoresMeses.get("temp1")!=null)    mapaCamposValoresMeses.get("temp1").clear();
	 	
		for(int i=0; i<asientosDetallesList.size(); i++){
			 	ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
				AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
				AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);			
			if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();
				int anio=asientoif.getFecha().getYear();
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				String mes3Letras=meses[mes];				
				//RET, IVA JUN-09//yep
				
				
				
				
				 if (mesConsultadosMap.get(0)!=null)  datosTemporalesIVA_notasCredito(asientoDetalle.getGlosa(),0,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(1)!=null)  datosTemporalesIVA_notasCredito(asientoDetalle.getGlosa(),1,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(2)!=null)  datosTemporalesIVA_notasCredito(asientoDetalle.getGlosa(),2,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(3)!=null)  datosTemporalesIVA_notasCredito(asientoDetalle.getGlosa(),3,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(4)!=null)  datosTemporalesIVA_notasCredito(asientoDetalle.getGlosa(),4,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(5)!=null)  datosTemporalesIVA_notasCredito(asientoDetalle.getGlosa(),5,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(6)!=null)  datosTemporalesIVA_notasCredito(asientoDetalle.getGlosa(),6,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(7)!=null)  datosTemporalesIVA_notasCredito(asientoDetalle.getGlosa(),7,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(8)!=null)  datosTemporalesIVA_notasCredito(asientoDetalle.getGlosa(),8,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(9)!=null)  datosTemporalesIVA_notasCredito(asientoDetalle.getGlosa(),9,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(10)!=null)  datosTemporalesIVA_notasCredito(asientoDetalle.getGlosa(),10,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(11)!=null)  datosTemporalesIVA_notasCredito(asientoDetalle.getGlosa(),11,asientoDetalle.getHaber());
				
			}
		}
		
		if(mapaCamposValoresMeses.get("temp")!=null)	mapaCamposValoresMeses.get("temp").clear();
		if(mapaCamposValoresMeses.get("temp1")!=null)    mapaCamposValoresMeses.get("temp1").clear();
		
		
	}
	
	
	
	

	private void getAsientosDetallesByCuentaId908_909_910_911_912_913_914_915_RENTAS(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
		
		
		if(mapaCamposValoresMeses.get("temp")!=null)	mapaCamposValoresMeses.get("temp").clear();
		if(mapaCamposValoresMeses.get("temp1")!=null)    mapaCamposValoresMeses.get("temp1").clear();
		
		int contandoNo=0;
		int contando=0;
		 
		for(int i=0; i<asientosDetallesList.size(); i++){
				 ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
				AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
				AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);
				if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();
				int anio=asientoif.getFecha().getYear();
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				String mes3Letras=meses[mes];
				 
				 if (mesConsultadosMap.get(0)!=null)  datosTemporalesRENTAS_notasCredito(asientoDetalle.getGlosa(),0,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(1)!=null)  datosTemporalesRENTAS_notasCredito(asientoDetalle.getGlosa(),1,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(2)!=null)  datosTemporalesRENTAS_notasCredito(asientoDetalle.getGlosa(),2,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(3)!=null)  datosTemporalesRENTAS_notasCredito(asientoDetalle.getGlosa(),3,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(4)!=null)  datosTemporalesRENTAS_notasCredito(asientoDetalle.getGlosa(),4,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(5)!=null)  datosTemporalesRENTAS_notasCredito(asientoDetalle.getGlosa(),5,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(6)!=null)  datosTemporalesRENTAS_notasCredito(asientoDetalle.getGlosa(),6,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(7)!=null)  datosTemporalesRENTAS_notasCredito(asientoDetalle.getGlosa(),7,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(8)!=null)  datosTemporalesRENTAS_notasCredito(asientoDetalle.getGlosa(),8,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(9)!=null)  datosTemporalesRENTAS_notasCredito(asientoDetalle.getGlosa(),9,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(10)!=null)  datosTemporalesRENTAS_notasCredito(asientoDetalle.getGlosa(),10,asientoDetalle.getHaber());
				 if (mesConsultadosMap.get(11)!=null)  datosTemporalesRENTAS_notasCredito(asientoDetalle.getGlosa(),11,asientoDetalle.getHaber());
				 
				 	
			}
		}
		
		if(mapaCamposValoresMeses.get("temp")!=null)	mapaCamposValoresMeses.get("temp").clear();
		if(mapaCamposValoresMeses.get("temp1")!=null)    mapaCamposValoresMeses.get("temp1").clear();
		
	}
	



	private void getAsientosDetallesByCuentaId897(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();
		 
		for(int i=0; i<asientosDetallesList.size(); i++){
				 ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
				AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
				AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);			
			if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				if(asientoDetalle.getGlosa().contains("SUSTITUTIVA"))
					llenarCampoValor("897",asientoDetalle.getDebe(),mes);
				else
					llenarCampoValor("897",BigDecimal.ZERO,mes);	
			}
		}
	}
	
	
	
 

	private void getAsientosDetallesByCuentaId609(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
		mapaAsientosDetalleMes501Costo.clear();
		
		for(int i=0; i<asientosDetallesList.size(); i++){
			 ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
			AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);	
			if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();	
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				llenarCampoValor("609",asientoDetalle.getDebe(),mes);				
			}
		}
	}
	
	
	

	
	
//	toma los asientos y los compara con el idcuenta y luego va llenando el mapa de asientos detalle y tomando las n/c
	private void getAsientosDetallesByCuentaId_605_615DebitoCredito(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
		mapaAsientosDetalleMes501Costo.clear();
		while (asientosDetallesIterator.hasNext()) {
			Object[] data = (Object[]) asientosDetallesIterator.next();
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) data[1];
			AsientoIf asientoif = (AsientoIf) data[0];
			if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();			
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				llenarCampoValor("615",asientoDetalle.getDebe(),mes);
				llenarCampoValor("605",asientoDetalle.getHaber(),mes);
				//llenarCampoLista605_607_617DebitoCredito(asientoDetalle,meses[mes],asientoif.getTipoDocumentoId(),mes);
			}
		}
	}
	
	 
	
	
//	toma los asientos y los compara con el idcuenta y luego va llenando el mapa de asientos detalle y tomando las n/c
	private void getAsientosDetallesByCuentaId_607_617DebitoCredito(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
		mapaAsientosDetalleMes501Costo.clear();
		/*while (asientosDetallesIterator.hasNext()) {
			Object[] data = (Object[]) asientosDetallesIterator.next();
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) data[1];
			AsientoIf asientoif = (AsientoIf) data[0];
			*/
			
	 for(int i=0; i<asientosDetallesList.size(); i++){
		 	ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
			AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);	
			if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();			
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				llenarCampoValor("617",asientoDetalle.getDebe(),mes);
				llenarCampoValor("607",asientoDetalle.getHaber(),mes);
				//llenarCampoLista605_607_617DebitoCredito(asientoDetalle,meses[mes],asientoif.getTipoDocumentoId(),mes);
			}
		}
	}
	
	//alexander
	
	
	
//	va llenando le mapaAsientosDetalle y el campo 421 si es tipo de doc = N/C
	public void llenarCampoLista605_607_617DebitoCredito(AsientoDetalleIf asientoDetalle,String mes,Long tipodocumentoId,int mesnumero)
	{	 
		Map<String,BigDecimal> mapaMesValor = new HashMap<String,BigDecimal>();				
		if((mapaAsientosDetalleMes501Costo.get(mes) == null)){			
			/*List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
			asientosDetallesByCuentaIdList.add(asientoDetalle);
			/mapaAsientosDetalleMes501Costo.put(mes,asientosDetallesByCuentaIdList);*/			
			llenarCampoValor("617",asientoDetalle.getDebe(),mesnumero);
			
			
		} else {			
			//List<AsientoDetalleIf> viejo = new ArrayList<AsientoDetalleIf>();
			//viejo=mapaAsientosDetalleMes501Costo.get(mes);
			//viejo.add(asientoDetalle);
			//mapaAsientosDetalleMes501Costo.put(mes,viejo);
			llenarCampoValor("607",asientoDetalle.getHaber(),mesnumero);
			llenarCampoValor("617",asientoDetalle.getDebe(),mesnumero);
		}
	}
	 

//	toma los asientos y los compara con el idcuenta y luego va llenando el mapa de asientos detalle y tomando las n/c
	private void getAsientosDetallesByCuentaId_501Gasto(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
		mapaAsientosDetalleMes501Gasto.clear();
		/*while (asientosDetallesIterator.hasNext()) {
			Object[] data = (Object[]) asientosDetallesIterator.next();
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) data[1];
			AsientoIf asientoif = (AsientoIf) data[0];*/

		for(int i=0; i<asientosDetallesList.size(); i++){
			 	ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
				AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
				AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);	
				if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				llenarCampoLista501_Gasto(asientoDetalle,meses[mes],asientoif.getTipoDocumentoId(),mes);
			}
		}
	}
	
//	va llenando le mapaAsientosDetalle y el campo 421 si es tipo de doc = N/C
	public void llenarCampoLista501_Gasto(AsientoDetalleIf asientoDetalle,String mes,Long tipodocumentoId,int mesnumero)
	{	 
		Long tipodoc_NCP=mapaTipoDocumentoCodigo.get("NCP");
		
		Map<String,BigDecimal> mapaMesValor = new HashMap<String,BigDecimal>();				
		if((mapaAsientosDetalleMes501Gasto.get(mes) == null)){			
			List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
			asientosDetallesByCuentaIdList.add(asientoDetalle);
			mapaAsientosDetalleMes501Gasto.put(mes,asientosDetallesByCuentaIdList);	
			
			if((tipodocumentoId!=null && tipodocumentoId.equals(tipodoc_NCP)) || (asientoDetalle.getGlosa().contains("N/C") && tipodocumentoId==null))
				llenarCampoValor("521",asientoDetalle.getHaber(),mesnumero);
			
		} else {				
			List<AsientoDetalleIf> viejo = new ArrayList<AsientoDetalleIf>();
			viejo=mapaAsientosDetalleMes501Gasto.get(mes);
			viejo.add(asientoDetalle);
			mapaAsientosDetalleMes501Gasto.put(mes,viejo);			
			
			if((tipodocumentoId!=null && tipodocumentoId.equals(tipodoc_NCP)) || (asientoDetalle.getGlosa().contains("N/C") && tipodocumentoId==null))
				llenarCampoValor("521",asientoDetalle.getHaber(),mesnumero);
		 
		}
	}
	
	 
	
	
//	toma los asientos y los compara con el idcuenta y luego va llenando el mapa de asientos detalle y tomando las n/c
	private void getAsientosDetallesByCuentaId_501Costo(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
		mapaAsientosDetalleMes501Costo.clear();
		/*while (asientosDetallesIterator.hasNext()) {
			Object[] data = (Object[]) asientosDetallesIterator.next();
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) data[1];
			AsientoIf asientoif = (AsientoIf) data[0];*/

		for(int i=0; i<asientosDetallesList.size(); i++){
			 	ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
				AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
				AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);
				if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();	
				if (mesConsultadosMap.get(mes)== null) {
					mesConsultadosMap.put(mes,meses[mes]);
				}
				llenarCampoLista501Costo(asientoDetalle,meses[mes],asientoif.getTipoDocumentoId(),mes);
			}
		}
	}
	
//	va llenando le mapaAsientosDetalle y el campo 421 si es tipo de doc = N/C
	public void llenarCampoLista501Costo(AsientoDetalleIf asientoDetalle,String mes,Long tipodocumentoId,int mesnumero)
	{	 
		Long tipodoc_NCP=mapaTipoDocumentoCodigo.get("NCP");
		
		Map<String,BigDecimal> mapaMesValor = new HashMap<String,BigDecimal>();				
		if((mapaAsientosDetalleMes501Costo.get(mes) == null)){			
			List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
			asientosDetallesByCuentaIdList.add(asientoDetalle);
			mapaAsientosDetalleMes501Costo.put(mes,asientosDetallesByCuentaIdList);
			
			if((tipodocumentoId!=null && tipodocumentoId.equals(tipodoc_NCP)) || (asientoDetalle.getGlosa().contains("N/C") && tipodocumentoId==null))
				llenarCampoValor("521",asientoDetalle.getHaber(),mesnumero);
			
		} else {			
			List<AsientoDetalleIf> viejo = new ArrayList<AsientoDetalleIf>();
			viejo=mapaAsientosDetalleMes501Costo.get(mes);
			viejo.add(asientoDetalle);
			mapaAsientosDetalleMes501Costo.put(mes,viejo);
			
			if((tipodocumentoId!=null && tipodocumentoId.equals(tipodoc_NCP)) || (asientoDetalle.getGlosa().contains("N/C") && tipodocumentoId==null))	
				llenarCampoValor("521",asientoDetalle.getHaber(),mesnumero);
		}
	}
	
	
	private void agregarColumnasCampo601_602_605_615_617_619_699_799_859_902_907_905() {		 
		if (mesConsultadosMap.get(0)!=null) recorrerLista601_602_605_615_617_619_699_799_859_902_907_905(meses[0]);
		if (mesConsultadosMap.get(1)!=null) recorrerLista601_602_605_615_617_619_699_799_859_902_907_905(meses[1]);
		if (mesConsultadosMap.get(2)!=null) recorrerLista601_602_605_615_617_619_699_799_859_902_907_905(meses[2]);
		if (mesConsultadosMap.get(3)!=null) recorrerLista601_602_605_615_617_619_699_799_859_902_907_905(meses[3]);
		if (mesConsultadosMap.get(4)!=null) recorrerLista601_602_605_615_617_619_699_799_859_902_907_905(meses[4]);
		if (mesConsultadosMap.get(5)!=null) recorrerLista601_602_605_615_617_619_699_799_859_902_907_905(meses[5]);
		if (mesConsultadosMap.get(6)!=null) recorrerLista601_602_605_615_617_619_699_799_859_902_907_905(meses[6]);
		if (mesConsultadosMap.get(7)!=null) recorrerLista601_602_605_615_617_619_699_799_859_902_907_905(meses[7]);
		if (mesConsultadosMap.get(8)!=null) recorrerLista601_602_605_615_617_619_699_799_859_902_907_905(meses[8]);
		if (mesConsultadosMap.get(9)!=null) recorrerLista601_602_605_615_617_619_699_799_859_902_907_905(meses[9]);
		if (mesConsultadosMap.get(10)!=null) recorrerLista601_602_605_615_617_619_699_799_859_902_907_905(meses[10]);
		if (mesConsultadosMap.get(11)!=null) recorrerLista601_602_605_615_617_619_699_799_859_902_907_905(meses[11]);		 
	}
	
	 
	public void recorrerLista601_602_605_615_617_619_699_799_859_902_907_905(String mes){
		 
		Map<String,BigDecimal> mapaMesValor605 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("605")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("605").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValor605.put(key,mapaCamposValoresMeses.get("605").get(key));
			}
		}	
		mapaMesValor605.put(mes,new BigDecimal("0.00"));
		mapaCamposValoresMeses.put("605",mapaMesValor605);
		
		
		BigDecimal valor605=BigDecimal.ZERO;		
		if(mapaCamposValoresMeses.get("605")!=null && mapaCamposValoresMeses.get("605").get(mes)!=null)
			valor605=mapaCamposValoresMeses.get("605").get(mes);
		
		Map<String,BigDecimal> mapaMesValor615 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("615")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("615").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValor615.put(key,mapaCamposValoresMeses.get("615").get(key));
			}
		}
		mapaMesValor615.put(mes,valor605);
		mapaCamposValoresMeses.put("615",mapaMesValor615);
		
		//499-554 mayor cero= 601; 0=son iguales <0 si 499 es menor que 554, >0 si 499 es mayor que 554
		//499-554 menor cero
		
		
		Double valor499 = 0D;
		Double valor554 = 0D;
		if(mapaCamposValoresMeses.get("499")!=null && mapaCamposValoresMeses.get("499").get(mes)!=null)			
			valor499=mapaCamposValoresMeses.get("499").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("554")!=null && mapaCamposValoresMeses.get("554").get(mes)!=null)			
			valor554=mapaCamposValoresMeses.get("554").get(mes).doubleValue();
		
		
		if(valor554.compareTo(valor499)<0) {
			Double valor601 = 0D;
			Map<String,BigDecimal> mapaMesValor601 = new HashMap<String,BigDecimal>();
			if(mapaCamposValoresMeses.get("601")!=null)
			{					
				Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("601").keySet().iterator();
				while(mapaMesValorIt2.hasNext()){
					String key = (String)mapaMesValorIt2.next();
					mapaMesValor601.put(key,mapaCamposValoresMeses.get("601").get(key));
				}
			}
			valor601=valor499-valor554;			
			mapaMesValor601.put(mes,new BigDecimal(valor601));
			mapaCamposValoresMeses.put("601",mapaMesValor601);
		}	
		
		if(valor554.compareTo(valor499)>0)
		 {
			Double valor602 = 0D;
			Map<String,BigDecimal> mapaMesValor602 = new HashMap<String,BigDecimal>();
			if(mapaCamposValoresMeses.get("602")!=null)
			{					
				Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("602").keySet().iterator();
				while(mapaMesValorIt2.hasNext()){
					String key = (String)mapaMesValorIt2.next();
					mapaMesValor602.put(key,mapaCamposValoresMeses.get("602").get(key));
				}
			}
			valor602=valor554-valor499;			
			mapaMesValor602.put(mes,new BigDecimal(valor602));
			mapaCamposValoresMeses.put("602",mapaMesValor602);
		}
				
		if(valor554.compareTo(valor499)==0)
		{ 
			Map<String,BigDecimal> mapaMesValor601 = new HashMap<String,BigDecimal>();
			if(mapaCamposValoresMeses.get("601")!=null)
			{					
				Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("601").keySet().iterator();
				while(mapaMesValorIt2.hasNext()){
					String key = (String)mapaMesValorIt2.next();
					mapaMesValor601.put(key,mapaCamposValoresMeses.get("601").get(key));
				}
			}
			 
			mapaMesValor601.put(mes,BigDecimal.ZERO);
			mapaCamposValoresMeses.put("601",mapaMesValor601);
			
			 
			Map<String,BigDecimal> mapaMesValor602 = new HashMap<String,BigDecimal>();
			if(mapaCamposValoresMeses.get("602")!=null)
			{					
				Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("602").keySet().iterator();
				while(mapaMesValorIt2.hasNext()){
					String key = (String)mapaMesValorIt2.next();
					mapaMesValor602.put(key,mapaCamposValoresMeses.get("602").get(key));
				}
			}
		 		
			mapaMesValor602.put(mes,BigDecimal.ZERO);
			mapaCamposValoresMeses.put("602",mapaMesValor602);
			 
		}
			
		
		Double valor619 = 0D;
		
		Map<String,BigDecimal> mapaMesValor619 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("619")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("619").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValor619.put(key,mapaCamposValoresMeses.get("619").get(key));
			}
		}	
		//601-602-605-607-609+611 mayyor 0  =619		
		if(mapaCamposValoresMeses.get("601")!=null && mapaCamposValoresMeses.get("601").get(mes)!=null)			
			valor619=mapaCamposValoresMeses.get("601").get(mes).doubleValue();		
		
		if(mapaCamposValoresMeses.get("602")!=null && mapaCamposValoresMeses.get("602").get(mes)!=null)			
			valor619=valor619 - mapaCamposValoresMeses.get("602").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("605")!=null && mapaCamposValoresMeses.get("605").get(mes)!=null)			
			valor619=valor619 - mapaCamposValoresMeses.get("605").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("607")!=null && mapaCamposValoresMeses.get("607").get(mes)!=null)			
			valor619=valor619 - mapaCamposValoresMeses.get("607").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("609")!=null && mapaCamposValoresMeses.get("609").get(mes)!=null)			
			valor619=valor619 - mapaCamposValoresMeses.get("609").get(mes).doubleValue();

		if(mapaCamposValoresMeses.get("611")!=null && mapaCamposValoresMeses.get("611").get(mes)!=null)			
			valor619=valor619 + mapaCamposValoresMeses.get("611").get(mes).doubleValue();
		
		if(valor619>0)
			mapaMesValor619.put(mes,new BigDecimal(valor619));
		else
			mapaMesValor619.put(mes,new BigDecimal("0.00"));
		
		mapaCamposValoresMeses.put("619",mapaMesValor619);
		
		//699=619+621
		
		Double valor699 = 0D;		
		Map<String,BigDecimal> mapaMesValor699 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("699")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("699").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValor699.put(key,mapaCamposValoresMeses.get("699").get(key));
			}
		}
		
		if(mapaCamposValoresMeses.get("619")!=null && mapaCamposValoresMeses.get("619").get(mes)!=null)			
			valor699=mapaCamposValoresMeses.get("619").get(mes).doubleValue();		

		if(mapaCamposValoresMeses.get("621")!=null && mapaCamposValoresMeses.get("621").get(mes)!=null)			
			valor699=valor699+mapaCamposValoresMeses.get("621").get(mes).doubleValue();	
		
		mapaMesValor699.put(mes,new BigDecimal(valor699));
		mapaCamposValoresMeses.put("699",mapaMesValor699);
		
		//799:721+723+725
		
		Double valor799 = 0D;		
		Map<String,BigDecimal> mapaMesValor799 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("799")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("799").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValor799.put(key,mapaCamposValoresMeses.get("799").get(key));
			}
		}		
		if(mapaCamposValoresMeses.get("721")!=null && mapaCamposValoresMeses.get("721").get(mes)!=null)			
			valor799=mapaCamposValoresMeses.get("721").get(mes).doubleValue();
		if(mapaCamposValoresMeses.get("722")!=null && mapaCamposValoresMeses.get("722").get(mes)!=null)			
			valor799=valor799+mapaCamposValoresMeses.get("722").get(mes).doubleValue();
		if(mapaCamposValoresMeses.get("725")!=null && mapaCamposValoresMeses.get("725").get(mes)!=null)			
			valor799=valor799+mapaCamposValoresMeses.get("725").get(mes).doubleValue();	
		
		mapaMesValor799.put(mes,new BigDecimal(valor799));
		mapaCamposValoresMeses.put("799",mapaMesValor799);
		
//		859:699+799		
		Double valor859 = 0D;		
		Map<String,BigDecimal> mapaMesValor859 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("859")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("859").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValor859.put(key,mapaCamposValoresMeses.get("859").get(key));
			}
		}		
		if(mapaCamposValoresMeses.get("699")!=null && mapaCamposValoresMeses.get("699").get(mes)!=null)			
			valor859=mapaCamposValoresMeses.get("699").get(mes).doubleValue();
		if(mapaCamposValoresMeses.get("799")!=null && mapaCamposValoresMeses.get("799").get(mes)!=null)			
			valor859=valor859+mapaCamposValoresMeses.get("799").get(mes).doubleValue();
		 
		mapaMesValor859.put(mes,new BigDecimal(valor859));
		mapaCamposValoresMeses.put("859",mapaMesValor859);
		
		//902=859-897
		
		Double valor902 = 0D;		
		Map<String,BigDecimal> mapaMesValor902 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("902")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("902").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValor902.put(key,mapaCamposValoresMeses.get("902").get(key));
			}
		}		
		if(mapaCamposValoresMeses.get("859")!=null && mapaCamposValoresMeses.get("859").get(mes)!=null)			
			valor902=mapaCamposValoresMeses.get("859").get(mes).doubleValue();
		if(mapaCamposValoresMeses.get("897")!=null && mapaCamposValoresMeses.get("897").get(mes)!=null)			
			valor902=valor902+mapaCamposValoresMeses.get("897").get(mes).doubleValue();
		 
		mapaMesValor902.put(mes,new BigDecimal(valor902));
		mapaCamposValoresMeses.put("902",mapaMesValor902);
		
		//799:721+723+725*
		//859:699+799*
		//897= 2104020006 glosa SUSTITUTIVA--
		//902=859-897*
		//908=NUM N/C--
		//909= 11020250051 ret iva/09-- 
		//907=909
		//905=902-909
		
		Double valor907 = 0D;	
		
		if(mapaCamposValoresMeses.get("909")!=null && mapaCamposValoresMeses.get("909").get(mes)!=null)			
			valor907=valor907 +mapaCamposValoresMeses.get("909").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("911")!=null && mapaCamposValoresMeses.get("911").get(mes)!=null)			
			valor907=valor907 + mapaCamposValoresMeses.get("911").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("913")!=null && mapaCamposValoresMeses.get("913").get(mes)!=null)			
			valor907=valor907 + mapaCamposValoresMeses.get("913").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("915")!=null && mapaCamposValoresMeses.get("915").get(mes)!=null)			
			valor907=valor907 + mapaCamposValoresMeses.get("915").get(mes).doubleValue();
		
	 
			Map<String,BigDecimal> mapaMesValor907 = new HashMap<String,BigDecimal>();
			if(mapaCamposValoresMeses.get("907")!=null)
			{					
				Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("907").keySet().iterator();
				while(mapaMesValorIt2.hasNext()){
					String key = (String)mapaMesValorIt2.next();
					mapaMesValor907.put(key,mapaCamposValoresMeses.get("907").get(key));
				}
			}
			mapaMesValor907.put(mes,new BigDecimal(valor907));
			mapaCamposValoresMeses.put("907",mapaMesValor907);
		 
		
		Double valor905 = 0D;		
		Map<String,BigDecimal> mapaMesValor905 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("905")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("905").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValor905.put(key,mapaCamposValoresMeses.get("905").get(key));
			}
		}		
		if(mapaCamposValoresMeses.get("902")!=null && mapaCamposValoresMeses.get("902").get(mes)!=null)			
			valor905=mapaCamposValoresMeses.get("902").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("909")!=null && mapaCamposValoresMeses.get("909").get(mes)!=null)			
			valor905=valor905-mapaCamposValoresMeses.get("909").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("911")!=null && mapaCamposValoresMeses.get("911").get(mes)!=null)			
			valor905=valor905-mapaCamposValoresMeses.get("911").get(mes).doubleValue();
		
		if(mapaCamposValoresMeses.get("913")!=null && mapaCamposValoresMeses.get("913").get(mes)!=null)			
			valor905=valor905-mapaCamposValoresMeses.get("913").get(mes).doubleValue();
		 
		if(mapaCamposValoresMeses.get("915")!=null && mapaCamposValoresMeses.get("915").get(mes)!=null)			
			valor905=valor905-mapaCamposValoresMeses.get("915").get(mes).doubleValue();
		
		mapaMesValor905.put(mes,new BigDecimal(valor905));
		mapaCamposValoresMeses.put("905",mapaMesValor905);
		 
		
		
		
		BigDecimal valor9902=BigDecimal.ZERO;
		Double valor9902_double = 0D;
		
		if(mapaCamposValoresMeses.get("905")!=null && mapaCamposValoresMeses.get("905").get(mes)!=null)
		{	
			valor9902=mapaCamposValoresMeses.get("905").get(mes);
			valor9902_double=valor9902.doubleValue();
		}
		 
		
		
		
		if(mapaCamposValoresMeses.get("9990")!=null && mapaCamposValoresMeses.get("9990").get(mes)!=null)
		{	
			valor9902=mapaCamposValoresMeses.get("9990").get(mes);
			valor9902_double=valor9902_double - valor9902.doubleValue();
		}
		
		
		Map<String,BigDecimal> mapaMesValorDebe9902 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("9991")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("9991").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValorDebe9902.put(key,mapaCamposValoresMeses.get("9991").get(key));
			}
		}
		mapaMesValorDebe9902.put(mes,new BigDecimal(valor9902_double));
		mapaCamposValoresMeses.put("9991",mapaMesValorDebe9902);	
		
		
		
	}
	
	
	private void agregarColumnasCampo_501_511_521_529_534_544_507_509_519_553_554_9902( ) {		 
		if (mesConsultadosMap.get(0)!=null) recorrerLista_501_511_521_529_534_544_507_509_519_553_554_9902(meses[0],0);
		if (mesConsultadosMap.get(1)!=null) recorrerLista_501_511_521_529_534_544_507_509_519_553_554_9902(meses[1],1);
		if (mesConsultadosMap.get(2)!=null) recorrerLista_501_511_521_529_534_544_507_509_519_553_554_9902(meses[2],2);
		if (mesConsultadosMap.get(3)!=null) recorrerLista_501_511_521_529_534_544_507_509_519_553_554_9902(meses[3],3);
		if (mesConsultadosMap.get(4)!=null) recorrerLista_501_511_521_529_534_544_507_509_519_553_554_9902(meses[4],4);
		if (mesConsultadosMap.get(5)!=null) recorrerLista_501_511_521_529_534_544_507_509_519_553_554_9902(meses[5],5);
		if (mesConsultadosMap.get(6)!=null) recorrerLista_501_511_521_529_534_544_507_509_519_553_554_9902(meses[6],6);
		if (mesConsultadosMap.get(7)!=null) recorrerLista_501_511_521_529_534_544_507_509_519_553_554_9902(meses[7],7);
		if (mesConsultadosMap.get(8)!=null) recorrerLista_501_511_521_529_534_544_507_509_519_553_554_9902(meses[8],8);
		if (mesConsultadosMap.get(9)!=null) recorrerLista_501_511_521_529_534_544_507_509_519_553_554_9902(meses[9],9);
		if (mesConsultadosMap.get(10)!=null) recorrerLista_501_511_521_529_534_544_507_509_519_553_554_9902(meses[10],10);
		if (mesConsultadosMap.get(11)!=null) recorrerLista_501_511_521_529_534_544_507_509_519_553_554_9902(meses[11],11);		 
	}
	
	
	public void recorrerLista_501_511_521_529_534_544_507_509_519_553_554_9902(String mes,int mesnumero){
		BigDecimal valor = new BigDecimal(0);
		Double totalDebe = 0D;
		Double totalHaber = 0D;
		Double valordel501 = 0D;
		
		//contiene los datos correctos de asientosdetalle y se calcula el debe, haber,saldo		
		if(mapaAsientosDetalleMes501Costo.get(mes)!=null)
		{
			Iterator asientosDetalleCuentaIterator = mapaAsientosDetalleMes501Costo.get(mes).iterator();
			while (asientosDetalleCuentaIterator.hasNext()) {
				AsientoDetalleIf asientoDetalleIf = (AsientoDetalleIf) asientosDetalleCuentaIterator.next();
				String tipoCuentaSegunAsiento = determinarTipoCuentaSegunAsiento(asientoDetalleIf);				
				if (asientoDetalleIf != null) {					
					if (asientoDetalleIf.getDebe() != null) 	 totalDebe = totalDebe + asientoDetalleIf.getDebe().doubleValue();			 
					if (asientoDetalleIf.getHaber() != null)	 totalHaber = totalHaber + asientoDetalleIf.getHaber().doubleValue();							
				}				
				 
			}
		}
		 
		valordel501=totalDebe/0.12;
		 totalDebe = 0D;
//		contiene los datos correctos de asientosdetalle y se calcula el debe, haber,saldo		
		if(mapaAsientosDetalleMes501Gasto.get(mes)!=null)
		{	
			Iterator asientosDetalleCuentaIterator = mapaAsientosDetalleMes501Gasto.get(mes).iterator();
			while (asientosDetalleCuentaIterator.hasNext()) {
				AsientoDetalleIf asientoDetalleIf = (AsientoDetalleIf) asientosDetalleCuentaIterator.next();
				String tipoCuentaSegunAsiento = determinarTipoCuentaSegunAsiento(asientoDetalleIf);				
				if (asientoDetalleIf != null) {					
					if (asientoDetalleIf.getDebe() != null) 	 totalDebe = totalDebe + asientoDetalleIf.getDebe().doubleValue();			 
					if (asientoDetalleIf.getHaber() != null)	 totalHaber = totalHaber + asientoDetalleIf.getHaber().doubleValue();							
				}				
			 
			}
		}			
		totalDebe=totalDebe/0.12;	
		valordel501=valordel501+totalDebe;
		
		Map<String,BigDecimal> mapaMesValorDebe = new HashMap<String,BigDecimal>();	
		if(mapaCamposValoresMeses.get("501")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("501").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValorDebe.put(key,mapaCamposValoresMeses.get("501").get(key));
			}
		}
		mapaMesValorDebe.put(mes,new BigDecimal(valordel501));
		mapaCamposValoresMeses.put("501",mapaMesValorDebe);			
		
	 
		
		
		
		BigDecimal valor511=BigDecimal.ZERO;
		if(mapaCamposValoresMeses.get("501")!=null)
		{		
			if(mapaCamposValoresMeses.get("501").get(mes)!=null )
			{
				valor511=mapaCamposValoresMeses.get("501").get(mes);
				
				if(mapaCamposValoresMeses.get("521")!=null && mapaCamposValoresMeses.get("521").get(mes)!=null )
				{	
					Double div=mapaCamposValoresMeses.get("521").get(mes).doubleValue()/0.12;
					valor511=valor511.subtract(new BigDecimal(div));
					mapaMesValorDebe = new HashMap<String,BigDecimal>();	
					if(mapaCamposValoresMeses.get("521")!=null)
					{					
						Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("521").keySet().iterator();
						while(mapaMesValorIt2.hasNext()){
							String key = (String)mapaMesValorIt2.next();
							mapaMesValorDebe.put(key,mapaCamposValoresMeses.get("521").get(key));
						}
					}
				}
				
				Map<String,BigDecimal> mapaMesValor_511 = new HashMap<String,BigDecimal>();				
				if(mapaCamposValoresMeses.get("511")!=null)
				{					
					Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("511").keySet().iterator();
					while(mapaMesValorIt2.hasNext()){
						String key = (String)mapaMesValorIt2.next();
						mapaMesValor_511.put(key,mapaCamposValoresMeses.get("511").get(key));
					}
				}
				
				 
				mapaMesValor_511.put(mes,valor511);
				mapaCamposValoresMeses.put("511",mapaMesValor_511);	
			}
		}
		
		
		BigDecimal valor521=BigDecimal.ZERO;
		Double valordel_521 = 0D;
		if(mapaCamposValoresMeses.get("511")!=null && mapaCamposValoresMeses.get("511").get(mes)!=null)
		{	
			valor521=mapaCamposValoresMeses.get("511").get(mes);
			valordel_521=valor521.doubleValue() * 0.12;	
		}
		
		Map<String,BigDecimal> mapaMesValorDebe521 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("521")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("521").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValorDebe521.put(key,mapaCamposValoresMeses.get("521").get(key));
			}
		}
		mapaMesValorDebe521.put(mes,new BigDecimal(valordel_521));
		mapaCamposValoresMeses.put("521",mapaMesValorDebe521);			
		
		
		
		BigDecimal valor534=BigDecimal.ZERO;
		Double valor534_double = 0D;
		if(mapaCamposValoresMeses.get("534")!=null && mapaCamposValoresMeses.get("534").get(mes)!=null)
		{	
			valor534=mapaCamposValoresMeses.get("534").get(mes);
			valor534_double=valor534.doubleValue() / 1.12;	
		}		
		Map<String,BigDecimal> mapaMesValorDebe534 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("534")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("534").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValorDebe534.put(key,mapaCamposValoresMeses.get("534").get(key));
			}
		}
		mapaMesValorDebe534.put(mes,new BigDecimal(valor534_double));
		mapaCamposValoresMeses.put("534",mapaMesValorDebe534);		
		
		
		
		
		
		BigDecimal valor544=BigDecimal.ZERO;
		Double valor544_double = 0D;
		if(mapaCamposValoresMeses.get("534")!=null && mapaCamposValoresMeses.get("534").get(mes)!=null)
		{	
			valor544=mapaCamposValoresMeses.get("534").get(mes);
			valor544_double=valor544.doubleValue() * 0.12;	
		}		
		Map<String,BigDecimal> mapaMesValorDebe544 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("544")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("544").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValorDebe544.put(key,mapaCamposValoresMeses.get("544").get(key));
			}
		}
		mapaMesValorDebe544.put(mes,new BigDecimal(valor544_double));
		mapaCamposValoresMeses.put("544",mapaMesValorDebe544);	
		
		 

		
		BigDecimal valor507=BigDecimal.ZERO;
		if(mapaCamposValoresMeses.get("517")!=null && mapaCamposValoresMeses.get("517").get(mes)!=null)
		{
			valor507=mapaCamposValoresMeses.get("517").get(mes);
		}
		llenarCampoValor("507",valor507,mesnumero); 
		
		
		
	 	BigDecimal valor509=BigDecimal.ZERO;		
		if(mapaCamposValoresMeses.get("501")!=null && mapaCamposValoresMeses.get("501").get(mes)!=null)
		{
			valor509=valor509.add(mapaCamposValoresMeses.get("501").get(mes));
		}
		if(mapaCamposValoresMeses.get("507")!=null && mapaCamposValoresMeses.get("507").get(mes)!=null)
		{
			valor509=valor509.add(mapaCamposValoresMeses.get("507").get(mes));
		}
		llenarCampoValor("509",valor509,mesnumero); 
		
		
		BigDecimal valor519=BigDecimal.ZERO;		
		if(mapaCamposValoresMeses.get("511")!=null && mapaCamposValoresMeses.get("511").get(mes)!=null)
		{
			valor519=valor519.add(mapaCamposValoresMeses.get("511").get(mes));
		}
		if(mapaCamposValoresMeses.get("517")!=null && mapaCamposValoresMeses.get("517").get(mes)!=null)
		{
			valor519=valor519.add(mapaCamposValoresMeses.get("517").get(mes));
		}
		llenarCampoValor("519",valor519,mesnumero); 
		
		
		BigDecimal valor529=BigDecimal.ZERO;	
		if(mapaCamposValoresMeses.get("521")!=null && mapaCamposValoresMeses.get("521").get(mes)!=null)
		{
			valor529=valor529.add(mapaCamposValoresMeses.get("521").get(mes));
		}
		
		Map<String,BigDecimal> mapaMesValorDebe529 = new HashMap<String,BigDecimal>();	
		if(mapaCamposValoresMeses.get("529")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("529").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValorDebe529.put(key,mapaCamposValoresMeses.get("529").get(key));
			}
		}
		mapaMesValorDebe529.put(mes,valor529);
		mapaCamposValoresMeses.put("529",mapaMesValorDebe529);	
		
		Map<String,BigDecimal> mapaMesValorDebe553 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("553")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("553").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValorDebe553.put(key,mapaCamposValoresMeses.get("553").get(key));
			}
		}	
		mapaMesValorDebe553.put(mes,new BigDecimal("1.0000"));
		mapaCamposValoresMeses.put("553",mapaMesValorDebe553);
		
		
		
		Map<String,BigDecimal> mapaMesValorDebe554 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("554")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("554").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValorDebe554.put(key,mapaCamposValoresMeses.get("554").get(key));
			}
		}	
		//521+522+524+525 *553
		BigDecimal valor554Temp=BigDecimal.ZERO;	
		if(mapaCamposValoresMeses.get("521")!=null && mapaCamposValoresMeses.get("521").get(mes)!=null)
		{
			valor554Temp=valor554Temp.add(mapaCamposValoresMeses.get("521").get(mes));
		}
		if(mapaCamposValoresMeses.get("522")!=null && mapaCamposValoresMeses.get("522").get(mes)!=null)
		{
			valor554Temp=valor554Temp.add(mapaCamposValoresMeses.get("522").get(mes));
		}
		if(mapaCamposValoresMeses.get("524")!=null && mapaCamposValoresMeses.get("524").get(mes)!=null)
		{
			valor554Temp=valor554Temp.add(mapaCamposValoresMeses.get("524").get(mes));
		}
		if(mapaCamposValoresMeses.get("525")!=null && mapaCamposValoresMeses.get("525").get(mes)!=null)
		{
			valor554Temp=valor554Temp.add(mapaCamposValoresMeses.get("525").get(mes));
		}
		
		if(mapaCamposValoresMeses.get("553")!=null && mapaCamposValoresMeses.get("553").get(mes)!=null)
		{
			valor554Temp=valor554Temp.multiply(mapaCamposValoresMeses.get("553").get(mes));
		}
		else
			valor554Temp=BigDecimal.ZERO;
		
		
		mapaMesValorDebe554.put(mes,valor554Temp);
		mapaCamposValoresMeses.put("554",mapaMesValorDebe554);
		
		

/*
		BigDecimal valor9902=BigDecimal.ZERO;
		Double valor9902_double = 0D;
		
		if(mapaCamposValoresMeses.get("902")!=null && mapaCamposValoresMeses.get("902").get(mes)!=null)
		{	
			valor9902=mapaCamposValoresMeses.get("902").get(mes);
			valor9902_double=valor9902.doubleValue();
		} 
		
		if(mapaCamposValoresMeses.get("9990")!=null && mapaCamposValoresMeses.get("9990").get(mes)!=null)
		{	
			valor9902=mapaCamposValoresMeses.get("9990").get(mes);
			valor9902_double=valor9902_double - valor9902.doubleValue();
		}
		
		
		Map<String,BigDecimal> mapaMesValorDebe9902 = new HashMap<String,BigDecimal>();
		if(mapaCamposValoresMeses.get("9991")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("9991").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValorDebe9902.put(key,mapaCamposValoresMeses.get("9991").get(key));
			}
		}
		mapaMesValorDebe9902.put(mes,new BigDecimal(valor9902_double));
		mapaCamposValoresMeses.put("9991",mapaMesValorDebe9902);		*/
		
		 
	}
	  
	
	 
	
	
	//toma los asientos y los compara con el idcuenta y luego va llenando el mapa de asientos detalle y tomando las n/c
	private void getAsientosDetallesByCuentaId_411(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();		
		mapaAsientosDetalleMes411.clear();
		/*while (asientosDetallesIterator.hasNext()) {
			Object[] data = (Object[]) asientosDetallesIterator.next();
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) data[1];
			AsientoIf asientoif = (AsientoIf) data[0];
			*/

		for(int i=0; i<asientosDetallesList.size(); i++){
			 	ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
				AsientoIf asientoif = (AsientoIf) arrVarios.get(0);
				AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) arrVarios.get(1);	
				if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				int mes=asientoif.getFecha().getMonth();				
				llenarCampoLista(asientoDetalle,meses[mes],asientoif.getTipoDocumentoId(),mes);
			}
		}
	}
	
	//va llenando le mapaAsientosDetalle y el campo 421 si es tipo de doc = N/C
	public void llenarCampoLista(AsientoDetalleIf asientoDetalle,String mes,Long tipodocumentoId,int mesnumero)
	{	 
		Long tipodoc_NCC=mapaTipoDocumentoCodigo.get("NCC");
		Map<String,BigDecimal> mapaMesValor = new HashMap<String,BigDecimal>();				
		if((mapaAsientosDetalleMes411.get(mes) == null)){			
			List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
			asientosDetallesByCuentaIdList.add(asientoDetalle);
			mapaAsientosDetalleMes411.put(mes,asientosDetallesByCuentaIdList);		
						
			if(tipodocumentoId.equals(tipodoc_NCC))
				llenarCampoValor("421",asientoDetalle.getDebe(),mesnumero);
			
			
			
		} else {				
			List<AsientoDetalleIf> viejo = new ArrayList<AsientoDetalleIf>();
			viejo=mapaAsientosDetalleMes411.get(mes);
			viejo.add(asientoDetalle);
			mapaAsientosDetalleMes411.put(mes,viejo);
			if(tipodocumentoId!=null && tipodocumentoId.equals(tipodoc_NCC))			llenarCampoValor("421",asientoDetalle.getDebe(),mesnumero);
		}
	}
	
	
	
	
	private ArrayList getAsientosDetallesList_new() {
		
		 ArrayList asientosDetallesList = new ArrayList<Object[]>();
		 
		try {
			Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
			Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
			
			PeriodoIf periodo=null;
			Iterator periodosIterator = SessionServiceLocator.getPeriodoSessionService().findPeriodoByRangoFechas(Parametros.getIdEmpresa(),fechaInicio,fechaFin).iterator();
			if (periodosIterator.hasNext())	periodo = (PeriodoIf) periodosIterator.next();
			Long idPeriodo = periodo.getId();
			///////////////////////
		
			Long idPlancuentas= planCuentasMap.get(Parametros.getIdEmpresa());
			 
			java.sql.Date fechaInicioMovimiento = new java.sql.Date(fechaInicio.getYear(), fechaInicio.getMonth(), fechaInicio.getDate());
			java.sql.Date fechaFinMovimiento = new java.sql.Date(fechaFin.getYear(), fechaFin.getMonth(), fechaFin.getDate());
									
			Collection<Object[]> asientosVarios = (ArrayList) SessionServiceLocator.getAsientoDetalleSessionService().findAsiento_ADetalleByEmpresaByPeriodoIdByPlanCuentaIdByStatusAndByFechaInicioAndFechaFin(Parametros.getIdEmpresa(), idPeriodo, idPlancuentas, ESTADO_ACTIVO, fechaInicioMovimiento, fechaFinMovimiento, true);
			
			//a.id,a.numero,a.observacion,ad.referencia,ad.debe,ad.haber,a.fecha
			
			 for ( Object[] ingreso : asientosVarios ){			
				ArrayList as_asdet = new ArrayList<Object[]>();	
				
				AsientoDetalleData ad=new AsientoDetalleData();
				ad.setAsientoId((Long)ingreso[0]);
				if(ingreso[4]==null) ad.setDebe(new BigDecimal("0.00"));
				else ad.setDebe((BigDecimal)ingreso[4]);
				
				if(ingreso[5]==null) ad.setHaber(new BigDecimal("0.00"));
				else ad.setHaber((BigDecimal)ingreso[5]);
				
				ad.setGlosa((String)ingreso[9]);
				
				 
				ad.setCuentaId((Long)ingreso[7]);
								
				AsientoData data=new AsientoData();				 
				data.setEmpresaId(Parametros.getIdEmpresa());
				data.setNumero("1");				
				data.setFecha((Date)ingreso[6]);
				data.setElaboradoPorId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
				data.setTipoDocumentoId((Long)ingreso[8]);
				 
				as_asdet.add(0, data);
				as_asdet.add(1, ad);
				
				asientosDetallesList.add(as_asdet);
			}  
					 /*for(int i=0; i<asientosDetallesList.size(); i++){
						 ArrayList arrVarios = ((ArrayList)asientosDetallesList.get(i));						
						AsientoIf asientoifdata = (AsientoIf) arrVarios.get(0);
						AsientoDetalleIf asientoDetalleifdata = (AsientoDetalleIf) arrVarios.get(1);
					} */
					
		} catch(Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		
		return asientosDetallesList;
	} 
	
	
	
	public void recorrerLista_411_421_429_434_444_480_482_484(String mes,int mesnumero){
		BigDecimal valor = new BigDecimal(0);
		 
		Double totalDebe = 0D;
		Double totalHaber = 0D;
	 
		
		//contiene los datos correctos de asientosdetalle y se calcula el debe, haber,saldo		
		if(mapaAsientosDetalleMes411.get(mes)!=null)
		{
			Iterator asientosDetalleCuentaIterator = mapaAsientosDetalleMes411.get(mes).iterator();
			while (asientosDetalleCuentaIterator.hasNext()) {
				AsientoDetalleIf asientoDetalleIf = (AsientoDetalleIf) asientosDetalleCuentaIterator.next();
				//String tipoCuentaSegunAsiento = determinarTipoCuentaSegunAsiento(asientoDetalleIf);				
				if (asientoDetalleIf != null) {					
					if (asientoDetalleIf.getDebe() != null) 	 totalDebe = totalDebe + asientoDetalleIf.getDebe().doubleValue();			 
					if (asientoDetalleIf.getHaber() != null)	 totalHaber = totalHaber + asientoDetalleIf.getHaber().doubleValue();							
				}				
				 
			}
		}
		Map<String,BigDecimal> mapaMesValorDebe = new HashMap<String,BigDecimal>();	
		if(mapaCamposValoresMeses.get("411")!=null)
		{					
			Iterator mapaMesValorIt2 = mapaCamposValoresMeses.get("411").keySet().iterator();
			while(mapaMesValorIt2.hasNext()){
				String key = (String)mapaMesValorIt2.next();
				mapaMesValorDebe.put(key,mapaCamposValoresMeses.get("411").get(key));
			}
		}
		mapaMesValorDebe.put(mes,new BigDecimal(totalHaber));
		mapaCamposValoresMeses.put("411",mapaMesValorDebe);			
		
		BigDecimal valor444=BigDecimal.ZERO;
		//ya se lo lleno anteriormente el 434 cuando hay FACTURAS de reembolso
		//y el campo 444 es el 434*0.12
		if(mapaCamposValoresMeses.get("434")!=null && mapaCamposValoresMeses.get("434").get(mes)!=null)
		{
			valor444=mapaCamposValoresMeses.get("434").get(mes);
			valor444=new BigDecimal(valor444.doubleValue() * 0.12);					
		}
		llenarCampoValor("444",valor444,mesnumero); 
	 
		//el 421 tenia las asientosdetalle cuando era tipo doc= n/c ese valor es el 429		
		BigDecimal valor421NC=BigDecimal.ZERO;
		if(mapaCamposValoresMeses.get("421")!=null && mapaCamposValoresMeses.get("421").get(meses[mesnumero])!=null) 
			valor421NC=mapaCamposValoresMeses.get("421").get(meses[mesnumero]);
		
		Map<String,BigDecimal> mapaMesValor = new HashMap<String,BigDecimal>();	
		if(mapaCamposValoresMeses.get("429")!=null)
		{	
			Iterator  mapaMesValorIt = mapaCamposValoresMeses.get("429").keySet().iterator();
			while(mapaMesValorIt.hasNext()){
				String key = (String)mapaMesValorIt.next();
				mapaMesValor.put(key,mapaCamposValoresMeses.get("429").get(key));
			}	
		}
		mapaMesValor.put(mes,valor421NC);
		mapaCamposValoresMeses.put("429",mapaMesValor);			
		BigDecimal netoVentasLocales411=BigDecimal.ZERO;
		
		if(mapaCamposValoresMeses.get("411").get(mes)!=null)
		{				
			netoVentasLocales411=mapaCamposValoresMeses.get("411").get(mes).subtract(valor421NC);				
			Map<String,BigDecimal> mapaMesValor421 = new HashMap<String,BigDecimal>();
			
			if(mapaCamposValoresMeses.get("421")!=null){
				Iterator mapaMesValorIt = mapaCamposValoresMeses.get("421").keySet().iterator();
				while(mapaMesValorIt.hasNext()){
					String key = (String)mapaMesValorIt.next();
					mapaMesValor421.put(key,mapaCamposValoresMeses.get("421").get(key));
				}
			}
					
			mapaMesValor421.put(mes,netoVentasLocales411);				
			mapaCamposValoresMeses.put("421",mapaMesValor421);
			
			//el 429=421+422, pero como 422 es cero
			mapaMesValor421 = new HashMap<String,BigDecimal>();
			Iterator mapaMesValorIt = mapaCamposValoresMeses.get("429").keySet().iterator();
			while(mapaMesValorIt.hasNext()){
				String key = (String)mapaMesValorIt.next();
				mapaMesValor421.put(key,mapaCamposValoresMeses.get("429").get(key));
			}	
			mapaMesValor421.put(mes,netoVentasLocales411);
			mapaCamposValoresMeses.put("429",mapaMesValor421);
			 
			
	BigDecimal suma499=BigDecimal.ZERO;
			
			
			
			//el 482 es el mismo campo de 429
			if(mapaCamposValoresMeses.get("482")!=null){
				mapaMesValor421 = new HashMap<String,BigDecimal>();
				mapaMesValorIt = mapaCamposValoresMeses.get("482").keySet().iterator();
				while(mapaMesValorIt.hasNext()){
					String key = (String)mapaMesValorIt.next();
					mapaMesValor421.put(key,mapaCamposValoresMeses.get("482").get(key));
				}				
			}
			mapaMesValor421.put(mes,netoVentasLocales411);
			mapaCamposValoresMeses.put("482",mapaMesValor421);
			
			//el campo 484=482
			if(mapaCamposValoresMeses.get("484")!=null){
				mapaMesValor421 = new HashMap<String,BigDecimal>();
				mapaMesValorIt = mapaCamposValoresMeses.get("484").keySet().iterator();
				while(mapaMesValorIt.hasNext()){
					String key = (String)mapaMesValorIt.next();
					mapaMesValor421.put(key,mapaCamposValoresMeses.get("484").get(key));
				}	
			}
			mapaMesValor421.put(mes,netoVentasLocales411);			
			mapaCamposValoresMeses.put("484",mapaMesValor421);
			suma499=suma499.add(netoVentasLocales411);
			
			//campo 499=429				
			if(mapaCamposValoresMeses.get("499")!=null){
				mapaMesValor421 = new HashMap<String,BigDecimal>();
				mapaMesValorIt = mapaCamposValoresMeses.get("499").keySet().iterator();
				while(mapaMesValorIt.hasNext()){
					String key = (String)mapaMesValorIt.next();
					mapaMesValor421.put(key,mapaCamposValoresMeses.get("499").get(key));
				}
			}
			mapaMesValor421.put(mes,netoVentasLocales411);					
			mapaCamposValoresMeses.put("499",mapaMesValor421);				
			netoVentasLocales411=new BigDecimal(new Double(netoVentasLocales411.doubleValue()/ 0.12));	
			
			Map<String,BigDecimal> mapaMesValor2 = new HashMap<String,BigDecimal>();
			mapaMesValorIt = mapaCamposValoresMeses.get("411").keySet().iterator();
			while(mapaMesValorIt.hasNext()){
				String key = (String)mapaMesValorIt.next();
				mapaMesValor2.put(key,mapaCamposValoresMeses.get("411").get(key));
			}	
			
			mapaMesValor2.put(mes,netoVentasLocales411);				
			mapaCamposValoresMeses.put("411",mapaMesValor2);
			
			if(mapaCamposValoresMeses.get("480")!=null){
				mapaMesValor2 = new HashMap<String,BigDecimal>();
				mapaMesValorIt = mapaCamposValoresMeses.get("480").keySet().iterator();
				while(mapaMesValorIt.hasNext()){
					String key = (String)mapaMesValorIt.next();
					mapaMesValor2.put(key,mapaCamposValoresMeses.get("480").get(key));
				}	
			}
			mapaMesValor2.put(mes,netoVentasLocales411);				
			mapaCamposValoresMeses.put("480",mapaMesValor2);
			
			
			BigDecimal valor418=BigDecimal.ZERO;
			if(mapaCamposValoresMeses.get("418")!=null && mapaCamposValoresMeses.get("418").get(mes)!=null) valor418=mapaCamposValoresMeses.get("418").get(mes);
			
			BigDecimal sumas419=BigDecimal.ZERO;
			sumas419=valor418.add(mapaCamposValoresMeses.get("411").get(mes));
			
			Map<String,BigDecimal> mapaMes419 = new HashMap<String,BigDecimal>();
			if(mapaCamposValoresMeses.get("419")!=null){							
				mapaMesValorIt = mapaCamposValoresMeses.get("419").keySet().iterator();
				while(mapaMesValorIt.hasNext()){
					String key = (String)mapaMesValorIt.next();
					mapaMes419.put(key,mapaCamposValoresMeses.get("419").get(key));
				}		
			}
			mapaMes419.put(mes,sumas419);
			mapaCamposValoresMeses.put("419",mapaMes419);
		} 
	}
	
	
	
	
	private void agregarColumnasCampo_411_421_429_434_444_480_482_484() {
		
		if (mesConsultadosMap.get(0)!=null)	recorrerLista_411_421_429_434_444_480_482_484(meses[0],0);
		if (mesConsultadosMap.get(1)!=null)	recorrerLista_411_421_429_434_444_480_482_484(meses[1],1);
		if (mesConsultadosMap.get(2)!=null) recorrerLista_411_421_429_434_444_480_482_484(meses[2],2);
		if (mesConsultadosMap.get(3)!=null) recorrerLista_411_421_429_434_444_480_482_484(meses[3],3);
		if (mesConsultadosMap.get(4)!=null) recorrerLista_411_421_429_434_444_480_482_484(meses[4],4);
		if (mesConsultadosMap.get(5)!=null) recorrerLista_411_421_429_434_444_480_482_484(meses[5],5);
		if (mesConsultadosMap.get(6)!=null) recorrerLista_411_421_429_434_444_480_482_484(meses[6],6);
		if (mesConsultadosMap.get(7)!=null) recorrerLista_411_421_429_434_444_480_482_484(meses[7],7);
		if (mesConsultadosMap.get(8)!=null) recorrerLista_411_421_429_434_444_480_482_484(meses[8],8);
		if (mesConsultadosMap.get(9)!=null) recorrerLista_411_421_429_434_444_480_482_484(meses[9],9);
		if (mesConsultadosMap.get(10)!=null) recorrerLista_411_421_429_434_444_480_482_484(meses[10],10);
		if (mesConsultadosMap.get(11)!=null) recorrerLista_411_421_429_434_444_480_482_484(meses[11],11);
	}
	
	
	private void agregarColumnasCampo_401_408_418_434(FacturaIf facturaIf,Vector<String> fila) {
		TipoDocumentoIf tipoDocumentoFactura = mapaTipoDocumento.get(facturaIf.getTipodocumentoId());
		BigDecimal subTotal = new BigDecimal(0);		
		BigDecimal descuentoTotal = facturaIf.getDescuento().add(facturaIf.getDescuentoGlobal());
		BigDecimal porcentajeComision = new BigDecimal(0);
		if(facturaIf.getPorcentajeComisionAgencia() != null){
			porcentajeComision = facturaIf.getPorcentajeComisionAgencia();
		}		
		BigDecimal comisionAgencia = (facturaIf.getValor().subtract(descuentoTotal)).multiply(porcentajeComision.divide(new BigDecimal(100)));						
		subTotal = facturaIf.getValor().subtract(descuentoTotal).add(comisionAgencia);
		int mes = 0;
		mes = facturaIf.getFechaFactura().getMonth();
		
		if (mesConsultadosMap.get(mes)== null) {
			mesConsultadosMap.put(mes,meses[mes]);
		}
		 
		
		
		BigDecimal suma409=BigDecimal.ZERO;
		if(facturaIf.getEstado().equals(ESTADO_ANULADO)){
			//no se suma nada ni normal,reembolso o exterior=0.00
			
		}else{				
			fila.add("");
			if(tipoDocumentoFactura.getCodigo().equals(CODIGO_FACTURA_EXTERIOR)){		
				llenarCampoValor("408",subTotal,mes);
				llenarCampoValor("418",subTotal,mes);
				suma409=subTotal;
				//normal,reembolso=0
				//exterior=subotal
			}else if(tipoDocumentoFactura.getCodigo().equals(CODIGO_FACTURA_REEMBOLSO)){
				subTotal=new BigDecimal(subTotal.doubleValue() / 1.12);
				llenarCampoValor("434",subTotal,mes);		
				//exterior=0,normail=0
				//reembolos=subtotal
			}else{
				//exterior,rembolso=0
				//normal=subtotal		
				suma409=suma409.add(subTotal);
				llenarCampoValor("401",subTotal,mes);
			}			
			if(suma409!=BigDecimal.ZERO) llenarCampoValor("409",suma409,mes); 
		}
	}
	
	
	private String determinarTipoCuentaSegunAsiento(AsientoDetalleIf asientoDetalle) {
		BigDecimal zero = new BigDecimal(0.00);
		if (asientoDetalle.getDebe().compareTo(zero) == 1) {
			return "D";
		} else
			return "H";
	}
	
	
	private double calcularSaldoCuenta(AsientoDetalleIf asientoDetalle, TipoCuentaIf tipoCuentaIf, String tipoCuentaSegunAsiento, double saldoCuenta) {
		double valorDebe = 0D;
		double valorHaber = 0D;
		
		if (("D".equals(tipoCuentaIf.getDebehaber())) && ("D".equals(tipoCuentaSegunAsiento)))
			valorDebe = asientoDetalle.getDebe().doubleValue();
		
		if (("D".equals(tipoCuentaIf.getDebehaber())) && ("H".equals(tipoCuentaSegunAsiento)))
			valorHaber = asientoDetalle.getHaber().doubleValue();
		
		if (("H".equals(tipoCuentaIf.getDebehaber())) && ("D".equals(tipoCuentaSegunAsiento)))
			valorDebe = asientoDetalle.getDebe().doubleValue();
		
		if (("H".equals(tipoCuentaIf.getDebehaber())) && ("H".equals(tipoCuentaSegunAsiento)))
			valorHaber = asientoDetalle.getHaber().doubleValue();
		
		if ("D".equals(tipoCuentaIf.getDebehaber()))
			saldoCuenta += valorDebe - valorHaber;
		else
			saldoCuenta += valorHaber - valorDebe;
		
		return saldoCuenta;
	}
	
	
	
	
	public void llenarCampoValor(String campo,BigDecimal valor,int mes)
	{	
		
		
		/*if(campo.equals("352"))
{
			System.out.println("asdasdasd::AAAAAAAAAANTES:"+mapaCamposValoresMeses.get(campo));
			System.out.println("asdasdasd::AAAAAAAAAANTES:"+mapaCamposValoresMeses.get(campo));
}*/
		
		Map<String,BigDecimal> mapaMesValor = new HashMap<String,BigDecimal>();				
		if((mapaCamposValoresMeses.get(campo) == null)){
			mapaMesValor.put(meses[mes],valor);
			mapaCamposValoresMeses.put(campo,mapaMesValor);
			
		} else {					
			Iterator mapaMesValorIt = mapaCamposValoresMeses.get(campo).keySet().iterator();
			while(mapaMesValorIt.hasNext()){
				String key = (String)mapaMesValorIt.next();
				mapaMesValor.put(key,mapaCamposValoresMeses.get(campo).get(key));
			}			
			
			//BigDecimal nuevovalor=mapaIvasBases.get(compraRetencionIf.getCodigoImpuesto()).add(compraRetencionIf.getValorRetenido());
			BigDecimal nuevovalor=valor;
			if (mapaCamposValoresMeses.get(campo).get(meses[mes]) == null) {
				mapaMesValor.put(meses[mes],nuevovalor );				
				mapaCamposValoresMeses.put(campo,mapaMesValor);					
			} else {
				mapaMesValor.put(meses[mes], nuevovalor.add(mapaCamposValoresMeses.get(campo).get(meses[mes])));				
				mapaCamposValoresMeses.put(campo,mapaMesValor);					
			} 
		}
		 
	}
	
	 
	public void llenarCampoValorString(String campo,BigDecimal valor,String mesStr)
	{	
		
		Map<String,BigDecimal> mapaMesValor = new HashMap<String,BigDecimal>();				
		if((mapaCamposValoresMeses.get(campo) == null)){
			mapaMesValor.put(mesStr,valor);
			mapaCamposValoresMeses.put(campo,mapaMesValor);
			
		} else {					
			Iterator mapaMesValorIt = mapaCamposValoresMeses.get(campo).keySet().iterator();
			while(mapaMesValorIt.hasNext()){
				String key = (String)mapaMesValorIt.next();
				mapaMesValor.put(key,mapaCamposValoresMeses.get(campo).get(key));
			}			
			
			//BigDecimal nuevovalor=mapaIvasBases.get(compraRetencionIf.getCodigoImpuesto()).add(compraRetencionIf.getValorRetenido());
			BigDecimal nuevovalor=valor;
			if (mapaCamposValoresMeses.get(campo).get(mesStr) == null) {
				mapaMesValor.put(mesStr,nuevovalor );				
				mapaCamposValoresMeses.put(campo,mapaMesValor);					
			} else {
				mapaMesValor.put(mesStr, nuevovalor.add(mapaCamposValoresMeses.get(campo).get(mesStr)));				
				mapaCamposValoresMeses.put(campo,mapaMesValor);					
			} 
		}
	}
	
	
	
	
	
	private void agregarColumnasTablaFacturas(String campo, Vector<String> fila, ImpuestosMensualesData facturacionData,String tipo) {
		BigDecimal total = new BigDecimal(0);
		
		fila.add("<html><b>" + campo + "</b></html>");
		//fila.add("<html><b>" + String.valueOf(numero) + "</b></html>");		
		facturacionData.setCampo(campo);		
		String concepto="";
		
		if(tipo.equals("R"))
			{if(mapasCamposFormularioRentas.get(campo)!=null) concepto=mapasCamposFormularioRentas.get(campo).toString();}
		
		if(tipo.equals("I"))
		{if(mapasCamposFormulario.get(campo)!=null) concepto=mapasCamposFormulario.get(campo).toString();}
		
		fila.add("<html><b>" +concepto+ "</b></html>");
		BigDecimal enero = mapaCamposValoresMeses.get(campo).get(meses[0])!=null?mapaCamposValoresMeses.get(campo).get(meses[0]):new BigDecimal(0);
		
		String eneroStr=enero.toString();		
		if (mesConsultadosMap.get(0)==null)  eneroStr="";
		
		if(campo.equals("908") || campo.equals("910") || campo.equals("912") || campo.equals("914")  )
			{
				if(eneroStr.equals("0")) fila.add("");
				else fila.add(eneroStr);			
			}
		else 
			{
			
			if(eneroStr.equals("")) fila.add(eneroStr);	
			else fila.add(formatoDecimal.format(enero));
			}
		
		facturacionData.setEnero(formatoDecimal.format(enero));
		total = total.add(enero);
		
		BigDecimal febrero = mapaCamposValoresMeses.get(campo).get(meses[1])!=null?mapaCamposValoresMeses.get(campo).get(meses[1]):new BigDecimal(0);		
		String febreroStr=febrero.toString();		
		if (mesConsultadosMap.get(1)==null)  febreroStr="";
		
		if(campo.equals("908") || campo.equals("910") || campo.equals("912") || campo.equals("914")  )
		{
			if(febreroStr.equals("0")) fila.add("");
			else fila.add(febreroStr);
			}
		else 
			{
			if(febreroStr.equals(""))fila.add(febreroStr);
			else fila.add(formatoDecimal.format(febrero));
			
			}
		
		facturacionData.setFebrero(formatoDecimal.format(febrero));
		total = total.add(febrero);
		
		BigDecimal marzo = mapaCamposValoresMeses.get(campo).get(meses[2])!=null?mapaCamposValoresMeses.get(campo).get(meses[2]):new BigDecimal(0);
		
		String marzoStr=marzo.toString();		
		if (mesConsultadosMap.get(2)==null)  marzoStr="";
		
		if(campo.equals("908") || campo.equals("910") || campo.equals("912") || campo.equals("914")  )
		{
			if(marzoStr.equals("0")) fila.add("");
			else fila.add(marzoStr);
			}
		else 
		{
			if(marzoStr.equals(""))fila.add(marzoStr);
			else fila.add(formatoDecimal.format(marzo));
			
			}
		
	 
		facturacionData.setMarzo(formatoDecimal.format(marzo));
		total = total.add(marzo);
		
		BigDecimal abril = mapaCamposValoresMeses.get(campo).get(meses[3])!=null?mapaCamposValoresMeses.get(campo).get(meses[3]):new BigDecimal(0);
		String abrilStr=abril.toString();		
		if (mesConsultadosMap.get(3)==null)  abrilStr="";
		
		if(campo.equals("908") || campo.equals("910") || campo.equals("912") || campo.equals("914")  )
			{
			if(abrilStr.equals("0")) fila.add("");
			else fila.add(abrilStr);
			}
		else 
		{
			if(abrilStr.equals(""))fila.add(abrilStr);
			else fila.add(formatoDecimal.format(abril));
			
		}
		 
		facturacionData.setAbril(formatoDecimal.format(abril));
		total = total.add(abril);
		
		BigDecimal mayo = mapaCamposValoresMeses.get(campo).get(meses[4])!=null?mapaCamposValoresMeses.get(campo).get(meses[4]):new BigDecimal(0);
		String mayoStr=mayo.toString();		
		if (mesConsultadosMap.get(4)==null)  mayoStr="";
		
		if(campo.equals("908") || campo.equals("910") || campo.equals("912") || campo.equals("914")  )
		{
			if(mayoStr.equals("0")) fila.add("");
			else fila.add(mayoStr);
			}
		else 
		{
			if(mayoStr.equals(""))fila.add(mayoStr);
			else fila.add(formatoDecimal.format(mayo));
			
		}
			 
		facturacionData.setMayo(formatoDecimal.format(mayo));
		total = total.add(mayo);
		

		
		
		BigDecimal junio = mapaCamposValoresMeses.get(campo).get(meses[5])!=null?mapaCamposValoresMeses.get(campo).get(meses[5]):new BigDecimal(0);
		String junioStr=junio.toString();		
		if (mesConsultadosMap.get(5)==null)  junioStr="";	
		if(campo.equals("908") || campo.equals("910") || campo.equals("912") || campo.equals("914")  )
		{
			if(junioStr.equals("0")) fila.add("");
			else fila.add(junioStr);
			}
		else 
			{
			if(junioStr.equals(""))fila.add(junioStr);
			else fila.add(formatoDecimal.format(junio));			
			}
		facturacionData.setJunio(formatoDecimal.format(junio));
		total = total.add(junio);
		
		
		BigDecimal julio = mapaCamposValoresMeses.get(campo).get(meses[6])!=null?mapaCamposValoresMeses.get(campo).get(meses[6]):new BigDecimal(0);
		String julioStr=julio.toString();		
		if (mesConsultadosMap.get(6)==null)  julioStr="";		
		if(campo.equals("908") || campo.equals("910") || campo.equals("912") || campo.equals("914")  )
		{
			if(julioStr.equals("0")) fila.add("");
			else fila.add(julioStr);
			}
		else 
		{
			if(julioStr.equals(""))fila.add(julioStr);
			else fila.add(formatoDecimal.format(julio));			
			} 
		facturacionData.setJulio(formatoDecimal.format(julio));
		total = total.add(julio);
		
		BigDecimal agosto = mapaCamposValoresMeses.get(campo).get(meses[7])!=null?mapaCamposValoresMeses.get(campo).get(meses[7]):new BigDecimal(0);		
		String agostoStr=agosto.toString();		
		if (mesConsultadosMap.get(7)==null)  agostoStr="";
		
		if(campo.equals("908") || campo.equals("910") || campo.equals("912") || campo.equals("914")  )
		{
			if(agostoStr.equals("0")) fila.add("");
			else fila.add(agostoStr);
			}
		else
		{
			if(agostoStr.equals(""))fila.add(agostoStr);
			else fila.add(formatoDecimal.format(agosto));			
		}
		facturacionData.setAgosto(formatoDecimal.format(agosto));
		total = total.add(agosto);
		
		BigDecimal septiembre = mapaCamposValoresMeses.get(campo).get(meses[8])!=null?mapaCamposValoresMeses.get(campo).get(meses[8]):new BigDecimal(0);
		String septiembreStr=septiembre.toString();		
		if (mesConsultadosMap.get(8)==null)  septiembreStr="";
		
		if(campo.equals("908") || campo.equals("910") || campo.equals("912") || campo.equals("914")  )
		{
			if(septiembreStr.equals("0")) fila.add("");
			else fila.add(septiembreStr);
			}
		else 
		{
			if(septiembreStr.equals(""))fila.add(septiembreStr);
			else fila.add(formatoDecimal.format(septiembre));			
		}
			 
		facturacionData.setSeptiembre(formatoDecimal.format(septiembre));
		total = total.add(septiembre);
		
		BigDecimal octubre = mapaCamposValoresMeses.get(campo).get(meses[9])!=null?mapaCamposValoresMeses.get(campo).get(meses[9]):new BigDecimal(0);
		String octubreStr=octubre.toString();		
		if (mesConsultadosMap.get(9)==null)  octubreStr="";
		
		if(campo.equals("908") || campo.equals("910") || campo.equals("912") || campo.equals("914")  )
		{
			if(octubreStr.equals("0")) fila.add("");
			else fila.add(octubreStr);
			}
		else 
		{
			if(octubreStr.equals(""))fila.add(octubreStr);
			else fila.add(formatoDecimal.format(octubre));			
		}
		 
		facturacionData.setOctubre(formatoDecimal.format(octubre));
		total = total.add(octubre);
		
		BigDecimal noviembre = mapaCamposValoresMeses.get(campo).get(meses[10])!=null?mapaCamposValoresMeses.get(campo).get(meses[10]):new BigDecimal(0);
		String noviembreStr=noviembre.toString();		
		if (mesConsultadosMap.get(10)==null)  noviembreStr="";
		
		if(campo.equals("908") || campo.equals("910") || campo.equals("912") || campo.equals("914")  )
		{
			if(noviembreStr.equals("0")) fila.add("");
			else fila.add(noviembreStr);
			}
		else
		{
			if(noviembreStr.equals(""))fila.add(noviembreStr);
			else fila.add(formatoDecimal.format(noviembre));			
		}
			 
		facturacionData.setNoviembre(formatoDecimal.format(noviembre));
		total = total.add(noviembre);
		
		BigDecimal diciembre = mapaCamposValoresMeses.get(campo).get(meses[11])!=null?mapaCamposValoresMeses.get(campo).get(meses[11]):new BigDecimal(0);
		String diciembreStr=diciembre.toString();		
		if (mesConsultadosMap.get(11)==null)  diciembreStr="";
		
		if(campo.equals("908") || campo.equals("910") || campo.equals("912") || campo.equals("914")  )
			{
				if(diciembreStr.equals("0")) fila.add("");
				else fila.add(diciembreStr);			
			}
		else
		{
			if(diciembreStr.equals(""))fila.add(diciembreStr);
			else fila.add(formatoDecimal.format(diciembre));			
		}
			 
		
		facturacionData.setDiciembre(formatoDecimal.format(diciembre));
		total = total.add(diciembre);
		
		
		if(campo.equals("908") || campo.equals("910") || campo.equals("912") || campo.equals("914")  )
			fila.add("-");
		else
			fila.add(formatoDecimal.format(total));
		
		facturacionData.setTotal(formatoDecimal.format(total));
		
		totalTotal = totalTotal.add(total);
		
		 
	}
	
	
	private void anchoColumnasTabla() {
		setSorterTable(getTblFacturacion());
		getTblFacturacion().getTableHeader().setReorderingAllowed(false);
		//getTblChequesEmitidos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblFacturacion().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblFacturacion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(220);
		
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(2);		
		anchoColumna.setMinWidth(0);
		//anchoColumna.setMaxWidth(0);
		anchoColumna.setPreferredWidth(0);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(3);
		anchoColumna.setMinWidth(0);
		//anchoColumna.setMaxWidth(0);
		anchoColumna.setPreferredWidth(0);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(4);
		anchoColumna.setMinWidth(0);
		//anchoColumna.setMaxWidth(0);
		anchoColumna.setPreferredWidth(0);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(5);
		anchoColumna.setMinWidth(0);
		//anchoColumna.setMaxWidth(0);
		anchoColumna.setPreferredWidth(0);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(6);
		anchoColumna.setMinWidth(0);
		//anchoColumna.setMaxWidth(0);
		anchoColumna.setPreferredWidth(0);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(7);
		anchoColumna.setMinWidth(0);
		//anchoColumna.setMaxWidth(0);
		anchoColumna.setPreferredWidth(0);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(8);
		anchoColumna.setMinWidth(0);
		//anchoColumna.setMaxWidth(0);
		anchoColumna.setPreferredWidth(0);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(9);
		anchoColumna.setMinWidth(0);
		//anchoColumna.setMaxWidth(0);
		anchoColumna.setPreferredWidth(0);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(10);
		anchoColumna.setMinWidth(0);
		//anchoColumna.setMaxWidth(0);
		anchoColumna.setPreferredWidth(0);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(11);
		anchoColumna.setMinWidth(0);
		//anchoColumna.setMaxWidth(0);
		anchoColumna.setPreferredWidth(0);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(12);
		anchoColumna.setMinWidth(0);
		//anchoColumna.setMaxWidth(0);
		anchoColumna.setPreferredWidth(0);
		anchoColumna = getTblFacturacion().getColumnModel().getColumn(13);
		anchoColumna.setMinWidth(0);
		//anchoColumna.setMaxWidth(0);
		anchoColumna.setPreferredWidth(0);
		
	}
	
	
	public void clean() {
		// TODO Auto-generated method stub
		mapaCamposValoresMeses.clear();
		cleanTable();
	}
	
	public void delete() {
		// TODO Auto-generated method stub
	}
	
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	
	public void find() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void report() {
		try {
			DefaultTableModel tblModelReporte = (DefaultTableModel) getTblFacturacion().getModel();
			if (tblModelReporte.getRowCount() > 0) {
				String si = "Si";
				String no = "No";
				Object[] options = {si,no};
				String mensaje = "¿Desea generar el reporte de Facturación?";
				int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if(opcion == JOptionPane.YES_OPTION) {
					//String fileName = "jaspers/SRI/RPImpuestosMensuales.jasper";
					String fileName = "jaspers/sri/RPImpuestosMensuales.jasper";
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_SRI).iterator().next();
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
					
					if(getCmbTipoImpuesto().getSelectedItem().equals(IMPUESTO_IVA))filtro="IMPUESTO AL VALOR AGREGADO";
					if(getCmbTipoImpuesto().getSelectedItem().equals(IMPUESTO_RENTA))filtro="RET. EN LA FTE IMPUESTO A LA RENTA";
					if(getCmbTipoImpuesto().getSelectedItem().equals(AMBOS))filtro="IVA-RENTA";
						
						
					parametrosMap.put("filtro", filtro);
					//ReportModelImpl.processReport(fileName, parametrosMap, facturacionColeccion, true);
					//
					ReportModelImpl.processReport(fileName, parametrosMap, (DefaultTableModel) getTblFacturacion().getModel(), true);
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
	
	
	public void save() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return true;
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
	
	
	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void showSaveMode() {
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
	
}
