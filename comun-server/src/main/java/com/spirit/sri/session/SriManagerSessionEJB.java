package com.spirit.sri.session;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.LogCarteraDetalleIf;
import com.spirit.cartera.entity.LogCarteraIf;
import com.spirit.cartera.entity.NotaCreditoDetalleIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.cartera.session.CarteraAfectaSessionLocal;
import com.spirit.cartera.session.CarteraDetalleSessionLocal;
import com.spirit.cartera.session.CarteraSessionLocal;
import com.spirit.cartera.session.LogCarteraDetalleSessionLocal;
import com.spirit.cartera.session.LogCarteraSessionLocal;
import com.spirit.cartera.session.NotaCreditoDetalleSessionLocal;
import com.spirit.cartera.session.NotaCreditoSessionLocal;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.compras.entity.LogCompraRetencionIf;
import com.spirit.compras.session.CompraDetalleSessionLocal;
import com.spirit.compras.session.CompraRetencionSessionLocal;
import com.spirit.compras.session.CompraSessionLocal;
import com.spirit.compras.session.LogCompraRetencionSessionLocal;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.session.AsientoSessionLocal;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.ClienteOficinaSessionLocal;
import com.spirit.crm.session.ClienteSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.session.FacturaDetalleSessionLocal;
import com.spirit.facturacion.session.FacturaSessionLocal;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.TipoIdentificacionSessionLocal;
import com.spirit.general.session.TipoParametroSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.GenericoSessionLocal;
import com.spirit.inventario.session.ProductoSessionLocal;
import com.spirit.sri.at.DetalleAnulados;
import com.spirit.sri.at.DetalleVentas;
import com.spirit.sri.dimm.DatosPreimpresoFactura;
import com.spirit.sri.dimm.DimmConstantes;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIdentifTransaccionIf;
import com.spirit.sri.entity.SriIvaBienIf;
import com.spirit.sri.entity.SriIvaServicioIf;
import com.spirit.sri.entity.SriProveedorRetencionIf;
import com.spirit.sri.entity.SriSustentoTributarioIf;
import com.spirit.sri.entity.SriTipoComprobanteIf;
import com.spirit.sri.entity.SriTipoTransaccionIf;
import com.spirit.sri.handler.SriParametros;
import com.spirit.sri.reoc.Air;
import com.spirit.sri.reoc.DetalleAir;
import com.spirit.sri.reoc.DetalleCompras;

/**
 * The <code>SriManagerSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:17 $
 *
 */
@Stateless
public class SriManagerSessionEJB implements SriManagerSessionRemote  {

	@EJB private CompraSessionLocal compraLocal;

	@EJB private CompraDetalleSessionLocal compraDetalleLocal;

	@EJB private CompraRetencionSessionLocal compraRetencionLocal;

	@EJB private SriProveedorRetencionSessionLocal proveedorRetencionLocal;

	@EJB private  ClienteSessionLocal clienteLocal;

	@EJB private  ClienteOficinaSessionLocal clienteOficinaLocal;

	@EJB private  ProductoSessionLocal productoLocal;

	@EJB private  GenericoSessionLocal genericoLocal;

	@EJB private  SriSustentoTributarioSessionLocal sustentoTributarioLocal;
	
	@EJB private  SriTipoTransaccionSessionLocal tipoTransaccionLocal;
	
	@EJB private  SriIdentifTransaccionSessionLocal identifTransaccionLocal;

	@EJB private TipoDocumentoSessionLocal tipoDocumentoLocal;
	
	@EJB private DocumentoSessionLocal documentoLocal;

	@EJB private SriTipoComprobanteSessionLocal tipoComprobanteLocal;

	@EJB private AsientoSessionLocal asientoLocal;

	@EJB private SriAirSessionLocal airLocal;

	@EJB private FacturaSessionLocal facturaLocal;

	@EJB private FacturaDetalleSessionLocal facturaDetalleLocal;

	@EJB private CarteraSessionLocal carteraLocal;
	
	@EJB private LogCarteraSessionLocal logCarteraLocal;
	
	@EJB private LogCarteraDetalleSessionLocal logCarteraDetalleLocal;

	@EJB private CarteraDetalleSessionLocal carteraDetalleLocal;

	@EJB private CarteraAfectaSessionLocal carteraAfectaLocal;
	
	@EJB private NotaCreditoSessionLocal notaCreditoLocal;
	
	@EJB private NotaCreditoDetalleSessionLocal notaCreditoDetalleLocal;
	
	@EJB private TipoParametroSessionLocal tipoParametroLocal;
	
	@EJB private TipoIdentificacionSessionLocal tipoIdentificacionLocal;
	
	@EJB private LogCompraRetencionSessionLocal logCompraRetencionLocal;
	
	@EJB  private UtilitariosSessionLocal utilitariosLocal;	

	private static final long serialVersionUID = 4640874398418404557L;
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	//private DecimalFormat formatoDosEnteros = new DecimalFormat("00");
	//private DecimalFormat formatoDosDecimales = new DecimalFormat("#######.##");
	private Format formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	//private Format formatoFechaCaducidad = new SimpleDateFormat("MM/yyyy");

	/**
	 * The logger object.
	 */
	//private static Logger log = LogService.getLogger(JbpmManagerSessionEJB.class);

	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 * @throws GenericBusinessException 
	 *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Object> generarDetallesCompraReoc(int numeroFila,Map<String, Date> mapaQuery,Long idEmpresa,Map<Long, SriAirIf> mapaAir,HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion,
			HashMap<String, String> mapaCodigoTipoIdentificacionCompra,HashMap<String, Double> mapaImpuestos,Long empresaId) throws GenericBusinessException{
		double baseCero = 0.0, baseGravada = 0.0;
		Map<String, DetalleAir> mapaAirValores = null;
		Collection<CompraRetencionIf> compraRetenciones = null;
		NumberFormat formatoTresNumeros = new DecimalFormat("000");
		
		Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
		Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
		
		Map<Long,ProductoIf> mapaProductos = new HashMap<Long,ProductoIf>();
		Map<Long,GenericoIf> mapaGenericos = new HashMap<Long,GenericoIf>();
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("estado", "A");
		Collection<CompraIf> comprasIf = compraLocal.getCompraByMapFechaInicioFechaFin(
				mapa,mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin"), idEmpresa );
		AsientoIf asientoIf = null;
		ArrayList<Object> detallecompras = new ArrayList<Object>();
		boolean esBaseCero = false;
		Map<PapeletaRetencion, PapeletaRetencion> mapaPapeletas = null;
		Map<Long, SriSustentoTributarioIf> mapaSustentos = cargarSustentoTributario();
		Map<Long, TipoDocumentoIf> mapaTipoDocumentos = cargarTipoDocumento(empresaId);
		Map<Long,SriTipoComprobanteIf> mapaTipoComprobantes = cargarTipoComprobante();
		for(CompraIf compraIf : comprasIf ){
			
			String tipoComprobanteTxt="";
			TipoIdentificacionIf tipoIdentificacion=null;

			String[] preimpreso = null;
			mapaAirValores = null;
			compraRetenciones = null;
			mapaPapeletas = null;
			try{
				mapaPapeletas = new HashMap<PapeletaRetencion, PapeletaRetencion>();
				//ClienteOficinaIf proveedorIf = clienteOficinaLocal.getClienteOficina(compraIf.getProveedorId());
				ClienteOficinaIf proveedorIf = verificarClienteOficina(mapaClienteOficina,compraIf.getProveedorId());
				//ClienteIf cliente = clienteLocal.getCliente(proveedorIf.getClienteId());
				ClienteIf cliente = verificarCliente(mapaCliente,proveedorIf.getClienteId());
				
				if (cliente==null)
					throw new GenericBusinessException("Proveedor Oficina con c\u00f3digo: "+proveedorIf.getCodigo()+"no asociado con Proveedor");
				Collection<CompraDetalleIf> comprasDetalle = compraDetalleLocal.findCompraDetalleByCompraId(compraIf.getId());

				mapaAirValores = new HashMap<String, DetalleAir>();
				
				for (CompraDetalleIf compraDetalle : comprasDetalle ){
					esBaseCero = false;
					baseCero = 0.0; baseGravada = 0.0; 
					//ProductoIf producto = productoLocal.getProducto(compraDetalle.getProductoId());
					ProductoIf producto = verificarProducto(mapaProductos,compraDetalle.getProductoId());
					//GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
					GenericoIf generico = verificarGenerico(mapaGenericos,producto.getGenericoId());
					
					double subtotal = compraDetalle.getValor().doubleValue() * compraDetalle.getCantidad();
					double porcentajeDescuentoEspecial = compraDetalle.getPorcentajeDescuentoEspecial().doubleValue() / 100;
					double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
					double porcentajeDescuentosVarios = compraDetalle.getPorcentajeDescuentosVarios().doubleValue() / 100;
					double descuentosVarios = (subtotal - descuentoEspecial) * porcentajeDescuentosVarios;
															
					//Verifico si cobra IVA
					if ( "s".equalsIgnoreCase(generico.getCobraIva()) ){

						baseGravada = subtotal - descuentoEspecial - compraDetalle.getDescuento().doubleValue() - descuentosVarios;
						
					} else {
						baseCero = subtotal;
						esBaseCero = true;
					}
					
					//Datos para el calculo de AIR
					SriAirIf airIf =  mapaAir.get(compraDetalle.getIdSriAir());
					if (!mapaAirValores.containsKey(airIf.getCodigo())){
						
						DetalleAir detalleAir = new DetalleAir();
						
						String codigoAir = airIf.getCodigo();
						double porcentaje = airIf.getPorcentaje().doubleValue();
						double valorRetenido = 0.0;
						if ( esBaseCero )
							valorRetenido = baseCero*porcentaje/100;
						else 
							valorRetenido = baseGravada*porcentaje/100;
						
						detalleAir.setCodRetAir(codigoAir);
						detalleAir.setPorcentaje(
							BigDecimal.valueOf(utilitariosLocal.redondeoValor(porcentaje)));
						detalleAir.setBase0(
							BigDecimal.valueOf(utilitariosLocal.redondeoValor(baseCero)));
						detalleAir.setBaseGrav(
							BigDecimal.valueOf(utilitariosLocal.redondeoValor(baseGravada)));
						//TODO - *************************** REVISAR ************************************
						detalleAir.setBaseNoGrav(
							BigDecimal.valueOf(utilitariosLocal.redondeoValor(0.00)));
						
						detalleAir.setValRetAir(
								BigDecimal.valueOf(utilitariosLocal.redondeoValor(valorRetenido)));
						
						mapaAirValores.put(airIf.getCodigo(), detalleAir);
						
					} else {
						DetalleAir detalleAir = mapaAirValores.get( airIf.getCodigo() );
						
						String codigoAir = airIf.getCodigo();
						double porcentaje = airIf.getPorcentaje().doubleValue();
						double valorRetenido = 0.0;
						if ( esBaseCero )
							valorRetenido = baseCero*porcentaje/100;
						else 
							valorRetenido = baseGravada*porcentaje/100;
						
						detalleAir.setCodRetAir(codigoAir);
						
						//detalleAir.setPorcentaje(
						//	BigDecimal.valueOf(utilitariosLocal.redondeoValor(porcentaje)).setScale(2,BigDecimal.ROUND_HALF_UP));
						double base0 = detalleAir.getBase0().doubleValue() + baseCero;
						detalleAir.setBase0(
							BigDecimal.valueOf(utilitariosLocal.redondeoValor(base0)));
						double baseGrav = detalleAir.getBaseGrav().doubleValue() + baseGravada;
						detalleAir.setBaseGrav(
							BigDecimal.valueOf(utilitariosLocal.redondeoValor(baseGrav)));
						//TODO - *************************** REVISAR ************************************
						detalleAir.setBaseNoGrav(
							BigDecimal.valueOf(utilitariosLocal.redondeoValor(0.00)));
						
						double valorRetAir = detalleAir.getValRetAir().doubleValue() + valorRetenido; 
						detalleAir.setValRetAir(
								BigDecimal.valueOf(utilitariosLocal.redondeoValor(valorRetAir)));
						
						mapaAirValores.put(airIf.getCodigo(), detalleAir);
					}
					
				}

				preimpreso = compraIf.getPreimpreso().split("-");
				//serieTxt = preimpreso[0]+"-"+ preimpreso[1]; 
				//secuencialTxt = preimpreso[2];

				if (preimpreso==null || preimpreso.length != 3)
					throw new GenericBusinessException("Revisar Preimpreso (formato: ###-###-#######) de compra con c\u00f3digo: "+compraIf.getCodigo());

				SriSustentoTributarioIf sustentoTributario = mapaSustentos.get(compraIf.getIdSriSustentoTributario()); 
				if (sustentoTributario==null || sustentoTributario.getDescripcion().equals("") )
					throw new GenericBusinessException("Revisar Sustento Tributario de compra con c\u00f3digo: "+compraIf.getCodigo());
				
				tipoIdentificacion = mapaCodigoIdentificacion.get(cliente.getTipoidentificacionId());
				if (tipoIdentificacion==null)
					throw new GenericBusinessException("Revisar tipo de Identificai\u00f3n de Cliente con identificac\u00f3n:"+cliente.getIdentificacion());

				TipoDocumentoIf tipoDocumento = mapaTipoDocumentos.get(compraIf.getTipodocumentoId());

				SriTipoComprobanteIf tipoComprobanteIf = mapaTipoComprobantes.get(tipoDocumento.getIdSriTipoComprobante());
				if (tipoComprobanteIf==null)
					throw new GenericBusinessException("Tipo de comprobante que tiene el Tipo de Documento con c\u00f3digo: "+tipoDocumento.getCodigo()+" no existe");

				tipoComprobanteTxt = tipoComprobanteIf.getNombre();
				if (tipoComprobanteTxt==null || tipoComprobanteTxt.equals(""))
					throw new GenericBusinessException("Revisar Tipo de comprobante de compra con c\u00f3digo: "+compraIf.getCodigo());

				Collection asientos = asientoLocal.findAsientoByTransaccionId(compraIf.getId());
				if (asientos.iterator().hasNext())
					asientoIf = (AsientoIf)asientos.iterator().next();
				else
					throw new GenericBusinessException("Compra con c\u00f3digo: "+compraIf.getCodigo()+" no tiene asiento asociado");

				//LLENADO DE XML CON CLASES
				DetalleCompras detalleCompra = new DetalleCompras();
				{
					String codigoTipoIdentificacionProv = mapaCodigoTipoIdentificacionCompra.get(tipoIdentificacion.getCodigo() );
					if ( codigoTipoIdentificacionProv == null ){
						
						//Se crea un mapa de tipo de Identificaciones: clave: codigo, valor: nombre 
						Map<String,String> mapaIdentificacion = new HashMap<String,String>();
						Iterator<Long> itMapaIdentificaciones = mapaCodigoIdentificacion.keySet().iterator();
						while( itMapaIdentificaciones.hasNext() ){
							TipoIdentificacionIf ti = mapaCodigoIdentificacion.get(itMapaIdentificaciones.next());
							mapaIdentificacion.put(ti.getCodigo(), ti.getNombre());
						}
						itMapaIdentificaciones = null;
						
						//Se hace lista con nombre de identificaciones para compra.
						List<String> listaIdentificacionesCompra = new LinkedList<String>();
						Iterator<String> itMapaCompra = mapaCodigoTipoIdentificacionCompra.keySet().iterator();
						while( itMapaCompra.hasNext() ){
							String nombreIdentificacion = mapaIdentificacion.get(itMapaCompra.next());
							if ( nombreIdentificacion != null )
								listaIdentificacionesCompra.add(nombreIdentificacion);
						}
						String error = "Proveedor en compra con c\u00f3digo: "+compraIf.getCodigo()+" debe tener una de las siguientes identificaciones: ";
						for( int i = 0; i < listaIdentificacionesCompra.size() ; i++ ){
							error += ( listaIdentificacionesCompra.get(i) );
							if ( i < ( listaIdentificacionesCompra.size()-1 ) )
								error += ", ";	
						}
							
						throw new GenericBusinessException(error);
						
					}
					detalleCompra.setTpIdProv(codigoTipoIdentificacionProv);

					//detalleCompra.setIdProv(clienteIf.getIdentificacion());
					detalleCompra.setIdProv(cliente.getIdentificacion());

					int codigoTipoComprobante = Integer.valueOf(tipoComprobanteIf.getCodigo());
					detalleCompra.setTipoComp(codigoTipoComprobante);

					detalleCompra.setAut(compraIf.getAutorizacion());
					
					detalleCompra.setEstab(preimpreso[0]);
					
					try{
						int ptoEmi = Integer.parseInt(preimpreso[1]);
						detalleCompra.setPtoEmi(formatoTresNumeros.format(ptoEmi));
					} catch(NumberFormatException e){
						throw new GenericBusinessException("Revisar el Punto de Emision en Preimpreso, en compra con c\u00f3digo: "+compraIf.getCodigo() );
					}
					
					detalleCompra.setSec(Integer.parseInt(preimpreso[2]));
					
					java.util.Date fecha = new java.util.Date(compraIf.getFecha().getTime());
					detalleCompra.setFechaEmiCom(formatoFecha.format(fecha));

					Air air = new Air();
					if (mapaAirValores.size()>0){
						Iterator itMapaAirValores = mapaAirValores.keySet().iterator();
						while(itMapaAirValores.hasNext()){
							DetalleAir detalleAir = mapaAirValores.get(itMapaAirValores.next());
							air.addDetalleAir(detalleAir);
						}
					}
					detalleCompra.setAir(air);

					compraRetenciones = compraRetencionLocal.findCompraRetencionByCompraId(compraIf.getId());
					if (compraRetenciones!=null ){
						//if (compraRetenciones.size()<3){
							for ( CompraRetencionIf compraRetencionIf : compraRetenciones  ){
								try{
									Integer autRetencion = Integer.parseInt(compraRetencionIf.getCodigoImpuesto().trim()); 
									//Los codigos de Retencion a la Renta entre 332 y 337 no se crea papeleta de retencion.
									if ( !(autRetencion>=332 && autRetencion<=337) && 
											veificarDatosRetencion (compraRetencionIf) ){
										PapeletaRetencion p = new PapeletaRetencion(
												compraRetencionIf.getEstablecimiento(),compraRetencionIf.getPuntoEmision()
												,compraRetencionIf.getSecuencial(),compraRetencionIf.getAutorizacion()
												,compraRetencionIf.getFechaEmision());
										mapaPapeletas.put(p, p);
									}
								} catch(Exception e){
									System.out.println("Error en numero de Autorizacion de Retencion de compra con id : "+compraIf.getId());
								}
							}
							
							int contadorRetenciones = 1;
							for ( PapeletaRetencion papeleta : mapaPapeletas.keySet() ){
								//CompraRetencionIf compraRetencion = (CompraRetencionIf) itCompraRetenciones.next();
								if (contadorRetenciones==1){
									detalleCompra.setEstabRet(papeleta.getEstablecimiento());
									detalleCompra.setPtoEmiRet(papeleta.getPtoImpresion());
									detalleCompra.setSecRet(Integer.valueOf(papeleta.getSecuencial()));
									detalleCompra.setAutRet(papeleta.getAutorizacion());
									fecha = new java.util.Date(papeleta.getFechaEmision().getTime());
									detalleCompra.setFechaEmiRet(formatoFecha.format(fecha));
								}
								if (contadorRetenciones==2){
									detalleCompra.setEstabRet1(papeleta.getEstablecimiento());
									detalleCompra.setPtoEmiRet1(papeleta.getPtoImpresion());
									detalleCompra.setSecRet1(Integer.valueOf(papeleta.getSecuencial()));
									detalleCompra.setAutRet1(papeleta.getAutorizacion());
									fecha = new java.util.Date(papeleta.getFechaEmision().getTime());
									detalleCompra.setFechaEmiRet1(formatoFecha.format(fecha));
								}
								contadorRetenciones++;
							}
						/*} else{
							throw new GenericBusinessException("Revisar Retenciones(2 m\u00e1ximo) en compra con c\u00f3digo: "+compraIf.getCodigo());
						}*/
					} 

				}
				detallecompras.add(detalleCompra);

			} catch(Exception e){
				if (e instanceof GenericBusinessException)
					detallecompras.add("\t"+numeroFila+".- "+e.getMessage()+"\n");
				else{
					e.printStackTrace();
					detallecompras.add("\t"+numeroFila+".- Revisar datos compra con c\u00f3digo: "+compraIf.getCodigo()+"\n");
				}
			}
			numeroFila++;
		}
		return detallecompras;
	}
	
	public BigDecimal redondeoValor6Dec(BigDecimal valor)  {
		BigDecimal valorR = BigDecimal.ZERO;
		Double decimales = 6D;
		valorR = valor.setScale(decimales.intValue(),BigDecimal.ROUND_HALF_UP);
		return valorR;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Object> generarDetallesCompraAnexoTransaccional(int numeroFila,Map<String, Date> mapaQuery,Long idEmpresa,Map<Long, SriAirIf> mapaAir,HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion,
			HashMap<String, String> mapaCodigoTipoIdentificacionCompra,HashMap<String, Double> mapaImpuestos,Long empresaId) throws GenericBusinessException{
		double baseCero = 0.0, baseGravada = 0.0;
		Map<String, com.spirit.sri.at.DetalleAir> mapaAirValores = null;
		Collection<CompraRetencionIf> compraRetenciones = null;
		NumberFormat formatoTresNumeros = new DecimalFormat("000");
		
		Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
		Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
		
		Map<Long,ProductoIf> mapaProductos = new HashMap<Long,ProductoIf>();
		Map<Long,GenericoIf> mapaGenericos = new HashMap<Long,GenericoIf>();
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("estado", "A");
		Collection<CompraIf> comprasIf = compraLocal.getCompraByMapFechaInicioFechaFin(
				mapa,mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin"), idEmpresa );
		AsientoIf asientoIf = null;
		ArrayList<Object> detallecompras = new ArrayList<Object>();
		boolean esBaseCero = false;
		Map<PapeletaRetencion, PapeletaRetencion> mapaPapeletas = null;
		Map<Long, SriSustentoTributarioIf> mapaSustentos = cargarSustentoTributario();
		Map<Long, TipoDocumentoIf> mapaTipoDocumentos = cargarTipoDocumento(empresaId);
		Map<Long,SriTipoComprobanteIf> mapaTipoComprobantes = cargarTipoComprobante();
		 
		for(CompraIf compraIf : comprasIf ){
			String tipoComprobanteTxt="";
			TipoIdentificacionIf tipoIdentificacion=null;
			
			if(compraIf.getPreimpreso().equals("001-001-000000002")){
				System.out.println("aqui");
			}			

			String[] preimpreso = null;
			mapaAirValores = null;
			compraRetenciones = null;
			mapaPapeletas = null;
			try{
				mapaPapeletas = new HashMap<PapeletaRetencion, PapeletaRetencion>();
				//ClienteOficinaIf proveedorIf = clienteOficinaLocal.getClienteOficina(compraIf.getProveedorId());
				ClienteOficinaIf proveedorIf = verificarClienteOficina(mapaClienteOficina,compraIf.getProveedorId());
				//ClienteIf cliente = clienteLocal.getCliente(proveedorIf.getClienteId());
				ClienteIf cliente = verificarCliente(mapaCliente,proveedorIf.getClienteId());
				
				if (cliente==null)
					throw new GenericBusinessException("Proveedor Oficina con c\u00f3digo: "+proveedorIf.getCodigo()+"no asociado con Proveedor");
				Collection<CompraDetalleIf> comprasDetalle = compraDetalleLocal.findCompraDetalleByCompraId(compraIf.getId());

				mapaAirValores = new HashMap<String, com.spirit.sri.at.DetalleAir>();
				
				Double baseImponibleGravada = 0.0;
				Double baseImponible = 0.0;
				Double retBienes = 0.0,retServicios = 0.0,retServicios100 = 0.0;
				Double iva = 0.0;
				
			
				for (CompraDetalleIf compraDetalle : comprasDetalle ){
					
					esBaseCero = false;
					baseCero = 0.0; baseGravada = 0.0; 
					//ProductoIf producto = productoLocal.getProducto(compraDetalle.getProductoId());
					ProductoIf producto = verificarProducto(mapaProductos,compraDetalle.getProductoId());
					//GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
					GenericoIf generico = verificarGenerico(mapaGenericos,producto.getGenericoId());
					
					//JOHY BASE
					//Verifico si cobra IVA
					if ( "s".equalsIgnoreCase(generico.getCobraIva()) ){
						
						double subTotal = compraDetalle.getValor().doubleValue() * compraDetalle.getCantidad();
						double porcentajeDescuentoEspecial = compraDetalle.getPorcentajeDescuentoEspecial().doubleValue() / 100D;
						double descuentoEspecial = subTotal * porcentajeDescuentoEspecial;
						double descuentoAgencia = compraDetalle.getDescuento().doubleValue();
						double porcentajeDescuentosVarios = compraDetalle.getPorcentajeDescuentosVarios().doubleValue() / 100D;
						double descuentosVarios = (subTotal - descuentoEspecial) * porcentajeDescuentosVarios;

						baseGravada = subTotal - descuentoEspecial - descuentoAgencia - descuentosVarios;
						
						baseImponibleGravada += baseGravada;
						iva += compraDetalle.getIva().doubleValue();
					} else {
						baseCero = (compraDetalle.getValor().doubleValue() * compraDetalle.getCantidad().longValue() );
						baseImponible += baseCero;
						esBaseCero = true;
					}
					
					
					
					//Datos para el calculo de AIR
					SriAirIf airIf =  mapaAir.get(compraDetalle.getIdSriAir());
					if (!mapaAirValores.containsKey(airIf.getCodigo())){
						
						com.spirit.sri.at.DetalleAir detalleAir = new com.spirit.sri.at.DetalleAir();
						
						String codigoAir = airIf.getCodigo();
						double porcentaje = utilitariosLocal.redondeoValor(airIf.getPorcentaje().doubleValue());
						double valorRetenido = 0.0;
						if ( esBaseCero )
							valorRetenido = baseCero*porcentaje/100;
						else 
							valorRetenido = baseGravada*porcentaje/100;
						
						if ( "s".equalsIgnoreCase(generico.getServicio()) ){
							retServicios += valorRetenido;
						} else {
							if ( porcentaje != 100.00D )
								retBienes += valorRetenido;
							else 
								retServicios100 += valorRetenido;
						}
						
						detalleAir.setCodRetAir(codigoAir);
						
						
						/*if(codigoAir.equals("309"))							
						{	
							//if(valorRetenido!=0D && ("s".equalsIgnoreCase(generico.getCobraIva()) ))
							 * 
								valorRetenido = utilitariosLocal.redondeoValor(valorRetenido);							
								double resta=baseGravada-(valorRetenido*100);	
								resta = utilitariosLocal.redondeoValor(resta);
								baseGravada=baseGravada-resta;
								baseImponibleGravada=baseImponibleGravada-resta;
								
							 *
								if(resta>0)
									{
									resta = utilitariosLocal.redondeoValor(resta);
									baseGravada=baseGravada-resta;
									baseImponibleGravada=baseImponibleGravada-resta;
									}
								else{
									if(resta<0)
									{
										resta = utilitariosLocal.redondeoValor(resta);
										baseGravada=baseGravada-resta;
										baseImponibleGravada=baseImponibleGravada-resta;
									}
									}			
						}*/
						
						double basesTotal = utilitariosLocal.redondeoValor( baseCero + baseGravada ); 
						detalleAir.setBaseImpAir(utilitariosLocal.redondeoValor(new BigDecimal(basesTotal)));
						
						porcentaje = utilitariosLocal.redondeoValor(porcentaje);
						detalleAir.setPorcentajeAir(utilitariosLocal.redondeoValor(new BigDecimal(porcentaje)));
						
						valorRetenido = utilitariosLocal.redondeoValor(valorRetenido);
						detalleAir.setValRetAir(utilitariosLocal.redondeoValor(new BigDecimal(valorRetenido)));
						mapaAirValores.put(airIf.getCodigo(), detalleAir);
						
						
					} else {
						com.spirit.sri.at.DetalleAir detalleAir = mapaAirValores.get( airIf.getCodigo() );
						
						String codigoAir = airIf.getCodigo();
						double porcentaje = utilitariosLocal.redondeoValor(airIf.getPorcentaje().doubleValue());
						double valorRetenido = 0.0;
						if ( esBaseCero )
							valorRetenido = baseCero*porcentaje/100;
						else 
							valorRetenido = baseGravada*porcentaje/100;
						
						if ( "S".equals(generico.getServicio()) ){
							retServicios += valorRetenido;
						} else {
							if ( porcentaje != 100.00D )
								retBienes += valorRetenido;
							else 
								retServicios100 += valorRetenido;
						}
						
						detalleAir.setCodRetAir(codigoAir);
						 
						
						if(codigoAir.equals("309"))							
						{	
							 	//valorRetenido = utilitariosLocal.redondeoValor(valorRetenido);
							 	double resta=baseGravada-(valorRetenido*100);
								
								//resta = utilitariosLocal.redondeoValor(resta);
								baseGravada=baseGravada-resta;
								baseImponibleGravada=baseImponibleGravada-resta;
								
								/*if(resta>0)
									{
									resta = utilitariosLocal.redondeoValor(resta);
									baseGravada=baseGravada-resta;
									baseImponibleGravada=baseImponibleGravada-resta;
									}
								else{
									if(resta<0)
									{
										resta = utilitariosLocal.redondeoValor(resta);
										baseGravada=baseGravada-resta;
										baseImponibleGravada=baseImponibleGravada-resta;
									}
									}*/										 
						}
						
						//double baseImponibleAir = utilitariosLocal.redondeoValor(detalleAir.getBaseImpAir().doubleValue()+ baseCero + baseGravada); 
						double baseImponibleAir = detalleAir.getBaseImpAir().doubleValue()+ baseCero + baseGravada; 
						detalleAir.setBaseImpAir(utilitariosLocal.redondeoValor(new BigDecimal(baseImponibleAir)));
						
						//double valorRetAir = utilitariosLocal.redondeoValor(detalleAir.getValRetAir().doubleValue() + valorRetenido);
						double valorRetAir = detalleAir.getValRetAir().doubleValue() + valorRetenido;
						detalleAir.setValRetAir(utilitariosLocal.redondeoValor(new BigDecimal(valorRetAir)));
						
						mapaAirValores.put(airIf.getCodigo(), detalleAir);
					}					
				}
				
				preimpreso = compraIf.getPreimpreso().split("-");
				//serieTxt = preimpreso[0]+"-"+ preimpreso[1]; 
				//secuencialTxt = preimpreso[2];
				if (preimpreso==null || preimpreso.length != 3)
					throw new GenericBusinessException("Revisar Preimpreso (formato: ###-###-#######) de compra con c\u00f3digo: "+compraIf.getCodigo());

				SriSustentoTributarioIf sustentoTributario = mapaSustentos.get(compraIf.getIdSriSustentoTributario()); 
				if (sustentoTributario==null || sustentoTributario.getDescripcion().equals("") )
					throw new GenericBusinessException("Revisar Sustento Tributario de compra con c\u00f3digo: "+compraIf.getCodigo());
				
				tipoIdentificacion = mapaCodigoIdentificacion.get(cliente.getTipoidentificacionId());
				if (tipoIdentificacion==null)
					throw new GenericBusinessException("Revisar tipo de Identificai\u00f3n de Cliente con identificac\u00f3n:"+cliente.getIdentificacion());

				TipoDocumentoIf tipoDocumento = mapaTipoDocumentos.get(compraIf.getTipodocumentoId());

				SriTipoComprobanteIf tipoComprobanteIf = mapaTipoComprobantes.get(tipoDocumento.getIdSriTipoComprobante());
				if (tipoComprobanteIf==null)
					throw new GenericBusinessException("Tipo de comprobante que tiene el Tipo de Documento con c\u00f3digo: "+tipoDocumento.getCodigo()+" no existe");

				tipoComprobanteTxt = tipoComprobanteIf.getNombre();
				if (tipoComprobanteTxt==null || tipoComprobanteTxt.equals(""))
					throw new GenericBusinessException("Revisar Tipo de comprobante de compra con c\u00f3digo: "+compraIf.getCodigo());

				Collection asientos = asientoLocal.findAsientoByTransaccionId(compraIf.getId());
				if (asientos.iterator().hasNext())
					asientoIf = (AsientoIf)asientos.iterator().next();
				else
					throw new GenericBusinessException("Compra con c\u00f3digo: "+compraIf.getCodigo()+" no tiene asiento asociado");

				//LLENADO DE XML CON CLASES
				com.spirit.sri.at.DetalleCompras detalleCompra = new com.spirit.sri.at.DetalleCompras();
				
				detalleCompra.setCodSustento(sustentoTributario.getCodigo());
				
				detalleCompra.setBaseNoGraIva(BigDecimal.ZERO);
				detalleCompra.setBaseImpGrav(utilitariosLocal.redondeoValor(new BigDecimal(baseImponibleGravada)));
				detalleCompra.setBaseImponible(utilitariosLocal.redondeoValor(new BigDecimal(baseImponible)));
				detalleCompra.setMontoIva(utilitariosLocal.redondeoValor(new BigDecimal(iva)));
				
				detalleCompra.setMontoIce(BigDecimal.ZERO);
				
				{
					String codigoTipoIdentificacionProv = mapaCodigoTipoIdentificacionCompra.get(tipoIdentificacion.getCodigo() );
					if ( codigoTipoIdentificacionProv == null ){
						
						//Se crea un mapa de tipo de Identificaciones: clave: codigo, valor: nombre 
						Map<String,String> mapaIdentificacion = new HashMap<String,String>();
						Iterator<Long> itMapaIdentificaciones = mapaCodigoIdentificacion.keySet().iterator();
						while( itMapaIdentificaciones.hasNext() ){
							TipoIdentificacionIf ti = mapaCodigoIdentificacion.get(itMapaIdentificaciones.next());
							mapaIdentificacion.put(ti.getCodigo(), ti.getNombre());
						}
						itMapaIdentificaciones = null;
						
						//Se hace lista con nombre de identificaciones para compra.
						List<String> listaIdentificacionesCompra = new LinkedList<String>();
						Iterator<String> itMapaCompra = mapaCodigoTipoIdentificacionCompra.keySet().iterator();
						while( itMapaCompra.hasNext() ){
							String nombreIdentificacion = mapaIdentificacion.get(itMapaCompra.next());
							if ( nombreIdentificacion != null )
								listaIdentificacionesCompra.add(nombreIdentificacion);
						}
						String error = "Proveedor en compra con c\u00f3digo: "+compraIf.getCodigo()+" debe tener una de las siguientes identificaciones: ";
						for( int i = 0; i < listaIdentificacionesCompra.size() ; i++ ){
							error += ( listaIdentificacionesCompra.get(i) );
							if ( i < ( listaIdentificacionesCompra.size()-1 ) )
								error += ", ";	
						}
							
						throw new GenericBusinessException(error);
						
					}
					detalleCompra.setTpIdProv(codigoTipoIdentificacionProv);

					//detalleCompra.setIdProv(clienteIf.getIdentificacion());
					detalleCompra.setIdProv(cliente.getIdentificacion());

					int codigoTipoComprobante = Integer.valueOf(tipoComprobanteIf.getCodigo());
					detalleCompra.setTipoComprobante(codigoTipoComprobante);

					detalleCompra.setAutorizacion(compraIf.getAutorizacion());
					
					detalleCompra.setEstablecimiento(preimpreso[0]);
					
					try{
						int ptoEmi = Integer.parseInt(preimpreso[1]);
						detalleCompra.setPuntoEmision(formatoTresNumeros.format(ptoEmi));
					} catch(NumberFormatException e){
						throw new GenericBusinessException("Revisar el Punto de Emision en Preimpreso, en compra con c\u00f3digo: "+compraIf.getCodigo() );
					}
					
					detalleCompra.setSecuencial(Integer.parseInt(preimpreso[2]));
					
					java.util.Date fecha = new java.util.Date(compraIf.getFecha().getTime());
					String fechaString = formatoFecha.format(fecha);
					detalleCompra.setFechaRegistro(fechaString);
					detalleCompra.setFechaEmision(fechaString);

					com.spirit.sri.at.Air air = new com.spirit.sri.at.Air();
					if (mapaAirValores.size()>0){
						Iterator itMapaAirValores = mapaAirValores.keySet().iterator();
						while(itMapaAirValores.hasNext()){
							com.spirit.sri.at.DetalleAir detalleAir = mapaAirValores.get(itMapaAirValores.next());
							air.addDetalleAir(detalleAir);
						}
					}
					detalleCompra.setAir(air);

					detalleCompra.setDocModificado(0);
					detalleCompra.setEstabModificado("000");
					detalleCompra.setPtoEmiModificado("000");
					detalleCompra.setSecModificado(0);
					detalleCompra.setAutModificado("000");
					
					double valorRetenidoIvaBienes = 0.0;
					double valorRetenidoIvaServicio = 0.0;
					double valorRetenidoIvaServicio100 = 0.0;
					
					//JOHY RETENCION
					compraRetenciones = compraRetencionLocal.findCompraRetencionByCompraId(compraIf.getId());
					if (compraRetenciones!=null ){
						//if (compraRetenciones.size()<3){
							for ( CompraRetencionIf compraRetencionIf : compraRetenciones  ){
								try{
									Integer autRetencion = Integer.parseInt(compraRetencionIf.getCodigoImpuesto().trim()); 
									//Los codigos de Retencion a la Renta entre 332 y 337 no se crea papeleta de retencion.
									if ( !(autRetencion>=332 && autRetencion<=337) && 
											veificarDatosRetencion (compraRetencionIf) ){
										PapeletaRetencion p = new PapeletaRetencion(
												compraRetencionIf.getEstablecimiento(),compraRetencionIf.getPuntoEmision()
												,compraRetencionIf.getSecuencial(),compraRetencionIf.getAutorizacion()
												,compraRetencionIf.getFechaEmision());
										mapaPapeletas.put(p, p);
									}
									
									
									//SEPARACION DE VALORES DE IVA EN RETENCIONES
									if (DimmConstantes.LETRA_IMPUESTO_IVA_RETENCION.equals(compraRetencionIf.getImpuesto())){
										double porcentaje = utilitariosLocal.redondeoValor(compraRetencionIf.getPorcentajeRetencion().doubleValue());
										if ( porcentaje == 30D ){
											valorRetenidoIvaBienes += compraRetencionIf.getValorRetenido().doubleValue();
										} else if ( porcentaje == 70D ){
											valorRetenidoIvaServicio += compraRetencionIf.getValorRetenido().doubleValue();
										} else if ( porcentaje == 100D ){
											valorRetenidoIvaServicio100 += compraRetencionIf.getValorRetenido().doubleValue();
										} else {
											throw new GenericBusinessException("Porcentaje "+porcentaje+" no considetado para creacion de archivo XML para DIMM !!");
										}
									}
									
								} catch(Exception e){
									System.out.println("Error en numero de Autorizacion de Retencion de compra con id : "+compraIf.getId());
								}
							}
							
							int contadorRetenciones = 1;
							for ( PapeletaRetencion papeleta : mapaPapeletas.keySet() ){
								//CompraRetencionIf compraRetencion = (CompraRetencionIf) itCompraRetenciones.next();
								if (contadorRetenciones==1){
									detalleCompra.setEstabRetencion1(papeleta.getEstablecimiento());
									detalleCompra.setPtoEmiRetencion1(papeleta.getPtoImpresion());
									detalleCompra.setSecRetencion1(Integer.valueOf(papeleta.getSecuencial()));
									detalleCompra.setAutRetencion1(papeleta.getAutorizacion());
									fecha = new java.util.Date(papeleta.getFechaEmision().getTime());
									detalleCompra.setFechaEmiRet1(formatoFecha.format(fecha));
								}
								if (contadorRetenciones==2){
									detalleCompra.setEstabRetencion2(papeleta.getEstablecimiento());
									detalleCompra.setPtoEmiRetencion2(papeleta.getPtoImpresion());
									detalleCompra.setSecRetencion2(Integer.valueOf(papeleta.getSecuencial()));
									detalleCompra.setAutRetencion2(papeleta.getAutorizacion());
									fecha = new java.util.Date(papeleta.getFechaEmision().getTime());
									detalleCompra.setFechaEmiRet2(formatoFecha.format(fecha));
								}
								contadorRetenciones++;
							}
						/*} else{
							throw new GenericBusinessException("Revisar Retenciones(2 m\u00e1ximo) en compra con c\u00f3digo: "+compraIf.getCodigo());
						}*/
					}
					
					detalleCompra.setValorRetBienes(utilitariosLocal.redondeoValor(new BigDecimal(valorRetenidoIvaBienes)));
					detalleCompra.setValorRetServicios(utilitariosLocal.redondeoValor(new BigDecimal(valorRetenidoIvaServicio)));
					detalleCompra.setValRetServ100(utilitariosLocal.redondeoValor(new BigDecimal(valorRetenidoIvaServicio100)));

				}
				detallecompras.add(detalleCompra);

			} catch(Exception e){
				if (e instanceof GenericBusinessException)
					detallecompras.add("\t"+numeroFila+".- "+e.getMessage()+"\n");
				else{
					e.printStackTrace();
					detallecompras.add("\t"+numeroFila+".- Revisar datos compra con c\u00f3digo: "+compraIf.getCodigo()+"\n");
				}
			}
			numeroFila++;
		}
		return detallecompras;
	}

	private ClienteOficinaIf verificarClienteOficina(Map<Long,ClienteOficinaIf> mapaClienteOficina,Long clienteOficinaId) throws GenericBusinessException{
		ClienteOficinaIf co = mapaClienteOficina.get(clienteOficinaId);
		if ( co == null ){
			co = clienteOficinaLocal.getClienteOficina(clienteOficinaId);
			mapaClienteOficina.put(co.getId(),co);
		}
		return co;
	}

	private ClienteIf verificarCliente(Map<Long,ClienteIf> mapaCliente,Long clienteId) throws GenericBusinessException{
		ClienteIf c = mapaCliente.get(clienteId);
		if ( c == null ){
			c = clienteLocal.getCliente(clienteId);
			mapaCliente.put(c.getId(),c);
		}
		return c;
	}

	private ProductoIf verificarProducto(Map<Long,ProductoIf> mapaProductos,Long productoId) throws GenericBusinessException{
		ProductoIf p = mapaProductos.get(productoId);
		if ( p == null ){
			p = productoLocal.getProducto(productoId);
			mapaProductos.put(p.getId(),p);
		}
		return p;
	}

	private GenericoIf verificarGenerico(Map<Long,GenericoIf> mapaGenericos,Long genericoId) throws GenericBusinessException{
		GenericoIf g = mapaGenericos.get(genericoId);
		if ( g == null ){
			g = genericoLocal.getGenerico(genericoId);
			mapaGenericos.put(g.getId(),g);
		}
		return g;
	}
	
	private boolean veificarDatosRetencion( CompraRetencionIf compraRetencionIf ){
		if ( compraRetencionIf.getEstablecimiento().equals("0") ||
				compraRetencionIf.getPuntoEmision().equals("0") ||
				compraRetencionIf.getSecuencial().equals("0") ||
				compraRetencionIf.getAutorizacion().equals("0")
		 ){
			return false;
		}
		return true;
	}
	
	private Map<Long, SriSustentoTributarioIf> cargarSustentoTributario() throws GenericBusinessException{
		Collection<SriSustentoTributarioIf> sustentos = sustentoTributarioLocal.getSriSustentoTributarioList();
		Map<Long, SriSustentoTributarioIf> mapa = new HashMap<Long, SriSustentoTributarioIf>();
		for ( SriSustentoTributarioIf s : sustentos){
			mapa.put(s.getId(), s);
		}
		return mapa;
	}
	
	private Map<String, SriTipoTransaccionIf> cargarCodigoTipoTransaccion() throws GenericBusinessException{
		Collection<SriTipoTransaccionIf> sustentos = tipoTransaccionLocal.getSriTipoTransaccionList();
		Map<String, SriTipoTransaccionIf> mapa = new HashMap<String, SriTipoTransaccionIf>();
		for ( SriTipoTransaccionIf s : sustentos){
			mapa.put(s.getNombre(), s);
		}
		return mapa;
	}
	
	private Map<Long, TipoDocumentoIf> cargarTipoDocumento(Long empresaId) throws GenericBusinessException{
		Collection<TipoDocumentoIf> tipos = tipoDocumentoLocal.findTipoDocumentoByEmpresaId(empresaId);
		Map<Long, TipoDocumentoIf> mapa = new HashMap<Long, TipoDocumentoIf>();
		for ( TipoDocumentoIf t : tipos){
			mapa.put(t.getId(), t);
		}
		return mapa;
	}
	
	private Map<Long, TipoIdentificacionIf> cargarTipoIdentificacion() throws GenericBusinessException{
		Collection<TipoIdentificacionIf> tipos = tipoIdentificacionLocal.getTipoIdentificacionList();
		Map<Long, TipoIdentificacionIf> mapa = new HashMap<Long, TipoIdentificacionIf>();
		for ( TipoIdentificacionIf t : tipos){
			mapa.put(t.getId(), t);
		}
		return mapa;
	}
	
	private Map<Long, SriTipoComprobanteIf> cargarTipoComprobante() throws GenericBusinessException{
		Collection<SriTipoComprobanteIf> tipos = tipoComprobanteLocal.getSriTipoComprobanteList();
		Map<Long, SriTipoComprobanteIf> mapa = new HashMap<Long, SriTipoComprobanteIf>();
		for ( SriTipoComprobanteIf t : tipos){
			mapa.put(t.getId(), t);
		}
		return mapa;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Object> generarNotasCreditoCompraReoc(int numeroFila,Long idEmpresa,Map<Long, SriAirIf> mapaAir,
			Map<String, Date> mapaQuery,HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion,
			HashMap<String, String> mapaCodigoTipoIdentificacionCompra) throws GenericBusinessException{
		double baseCero = 0.0, baseGravada = 0.0;
		Map<String, DetalleAir> mapaAirValores = null;
		Collection<CompraRetencionIf> compraRetenciones = null;
		NumberFormat formatoTresNumeros = new DecimalFormat("000");
		
		Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
		Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();
		
		Map<Long,ProductoIf> mapaProductos = new HashMap<Long,ProductoIf>();
		Map<Long,GenericoIf> mapaGenericos = new HashMap<Long,GenericoIf>();
		
		Map<Long,DocumentoIf> mapaDocumentosParaProcesar = new HashMap<Long,DocumentoIf>();
		cargarCodigosNotasCredito(mapaDocumentosParaProcesar,idEmpresa);
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("estado", "A");
		mapa.put("tipoCartera", "P");
		Collection<NotaCreditoIf> notasCredito = notaCreditoLocal.getNotaCreditoByMapFechaInicioFechaFin(
				mapa,mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin"), idEmpresa );
		AsientoIf asientoIf = null;
		ArrayList<Object> detallecompras = new ArrayList<Object>();
		boolean esBaseCero = false;
		Map<PapeletaRetencion, PapeletaRetencion> mapaPapeletas = null;
		//Map<Long, SriSustentoTributarioIf> mapaSustentos = cargarSustentoTributario();
		Map<Long, TipoDocumentoIf> mapaTipoDocumentos = cargarTipoDocumento(idEmpresa);
		Map<Long,SriTipoComprobanteIf> mapaTipoComprobantes = cargarTipoComprobante();
		
		ContinuarCabecera:
		for(NotaCreditoIf notaCredito : notasCredito ){
			String tipoComprobanteTxt="";
			TipoIdentificacionIf tipoIdentificacion=null;

			String[] preimpreso = null;
			mapaAirValores = null;
			compraRetenciones = null;
			mapaPapeletas = null;
			try{
				mapaPapeletas = new HashMap<PapeletaRetencion, PapeletaRetencion>();
				ClienteOficinaIf proveedorIf = verificarClienteOficina(mapaClienteOficina,notaCredito.getOperadorNegocioId());
				ClienteIf cliente = verificarCliente(mapaCliente,proveedorIf.getClienteId());
				
				if (cliente==null)
					throw new GenericBusinessException("Proveedor Oficina con c\u00f3digo: "+proveedorIf.getCodigo()+"no asociado con Proveedor");
				Collection<NotaCreditoDetalleIf> detalles = notaCreditoDetalleLocal.findNotaCreditoDetalleByNotaCreditoId(notaCredito.getId());

				mapaAirValores = new HashMap<String, DetalleAir>();
				
				boolean hayDetalles = false;
				
				ContinuarDetalle:
				for (NotaCreditoDetalleIf detalle : detalles ){
					
					DocumentoIf documento = documentoLocal.getDocumento(detalle.getDocumentoId());
					if ( !mapaDocumentosParaProcesar.containsKey(documento.getId()) )
						continue ContinuarDetalle;
					
					hayDetalles = true;
					
					
					esBaseCero = false;
					baseCero = 0.0; baseGravada = 0.0; 
					//ProductoIf producto = productoLocal.getProducto(compraDetalle.getProductoId());
					ProductoIf producto = verificarProducto(mapaProductos,detalle.getProductoId());
					//GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
					GenericoIf generico = verificarGenerico(mapaGenericos,producto.getGenericoId());
					
					//Verifico si cobra IVA
					if ( "s".equalsIgnoreCase(generico.getCobraIva()) ){

						baseGravada = ((detalle.getValor().doubleValue() * detalle.getCantidad().longValue()) );
					} else {
						baseCero = (detalle.getValor().doubleValue() * detalle.getCantidad().longValue() );
						esBaseCero = true;
					}
					
					SriAirIf airIf =  obtenerAirPredeterminadoReoc(idEmpresa);
					if (!mapaAirValores.containsKey(airIf.getCodigo())){
						
						DetalleAir detalleAir = new DetalleAir();
						
						String codigoAir = airIf.getCodigo();
						double porcentaje = airIf.getPorcentaje().doubleValue();
						double valorRetenido = 0.0;
						if ( esBaseCero )
							valorRetenido = baseCero*porcentaje/100;
						else 
							valorRetenido = baseGravada*porcentaje/100;
						
						detalleAir.setCodRetAir(codigoAir);
						detalleAir.setPorcentaje(
							BigDecimal.valueOf(utilitariosLocal.redondeoValor(porcentaje)));
						detalleAir.setBase0(
							BigDecimal.valueOf(utilitariosLocal.redondeoValor(baseCero)));
						detalleAir.setBaseGrav(
							BigDecimal.valueOf(utilitariosLocal.redondeoValor(baseGravada)));
						//TODO - *************************** REVISAR ************************************
						detalleAir.setBaseNoGrav(
							BigDecimal.valueOf(utilitariosLocal.redondeoValor(0.00)));
						
						detalleAir.setValRetAir(
								BigDecimal.valueOf(utilitariosLocal.redondeoValor(valorRetenido)));
						
						mapaAirValores.put(airIf.getCodigo(), detalleAir);
						
					} else {
						DetalleAir detalleAir = mapaAirValores.get( airIf.getCodigo() );
						
						String codigoAir = airIf.getCodigo();
						double porcentaje = airIf.getPorcentaje().doubleValue();
						double valorRetenido = 0.0;
						if ( esBaseCero )
							valorRetenido = baseCero*porcentaje/100;
						else 
							valorRetenido = baseGravada*porcentaje/100;
						
						detalleAir.setCodRetAir(codigoAir);
						
						//detalleAir.setPorcentaje(
						//	BigDecimal.valueOf(utilitariosLocal.redondeoValor(porcentaje)).setScale(2,BigDecimal.ROUND_HALF_UP));
						double base0 = detalleAir.getBase0().doubleValue() + baseCero;
						detalleAir.setBase0(
							BigDecimal.valueOf(utilitariosLocal.redondeoValor(base0)));
						double baseGrav = detalleAir.getBaseGrav().doubleValue() + baseGravada;
						detalleAir.setBaseGrav(
							BigDecimal.valueOf(utilitariosLocal.redondeoValor(baseGrav)));
						//TODO - *************************** REVISAR ************************************
						detalleAir.setBaseNoGrav(
							BigDecimal.valueOf(utilitariosLocal.redondeoValor(0.00)));
						
						double valorRetAir = detalleAir.getValRetAir().doubleValue() + valorRetenido; 
						detalleAir.setValRetAir(
								BigDecimal.valueOf(utilitariosLocal.redondeoValor(valorRetAir)));
						
						mapaAirValores.put(airIf.getCodigo(), detalleAir);
					}
				}
				
				if ( !hayDetalles )
					continue ContinuarCabecera;

				preimpreso = notaCredito.getPreimpreso().split("-");
				//serieTxt = preimpreso[0]+"-"+ preimpreso[1]; 
				//secuencialTxt = preimpreso[2];

				if (preimpreso==null || preimpreso.length != 3)
					throw new GenericBusinessException("Revisar Preimpreso (formato: ###-###-#######) de compra con c\u00f3digo: "+notaCredito.getCodigo());

				
				tipoIdentificacion = mapaCodigoIdentificacion.get(cliente.getTipoidentificacionId());
				if (tipoIdentificacion==null)
					throw new GenericBusinessException("Revisar tipo de Identificai\u00f3n de Cliente con identificac\u00f3n:"+cliente.getIdentificacion());

				TipoDocumentoIf tipoDocumento = mapaTipoDocumentos.get(notaCredito.getTipoDocumentoId());

				SriTipoComprobanteIf tipoComprobanteIf = mapaTipoComprobantes.get(tipoDocumento.getIdSriTipoComprobante());
				if (tipoComprobanteIf==null)
					throw new GenericBusinessException("Tipo de comprobante que tiene el Tipo de Documento con c\u00f3digo: "+tipoDocumento.getCodigo()+" no existe");

				tipoComprobanteTxt = tipoComprobanteIf.getNombre();
				if (tipoComprobanteTxt==null || tipoComprobanteTxt.equals(""))
					throw new GenericBusinessException("Revisar Tipo de comprobante de Nota de Credito con c\u00f3digo: "+notaCredito.getCodigo());

				Collection asientos = asientoLocal.findAsientoByTransaccionId(notaCredito.getId());
				if (asientos.iterator().hasNext())
					asientoIf = (AsientoIf)asientos.iterator().next();
				else
					throw new GenericBusinessException("Compra con c\u00f3digo: "+notaCredito.getCodigo()+" no tiene asiento asociado");

				//LLENADO DE XML CON CLASES
				DetalleCompras detalleCompra = new DetalleCompras();
				{
					String codigoTipoIdentificacionProv = mapaCodigoTipoIdentificacionCompra.get(tipoIdentificacion.getCodigo() );
					if ( codigoTipoIdentificacionProv == null ){
						
						//Se crea un mapa de tipo de Identificaciones: clave: codigo, valor: nombre 
						Map<String,String> mapaIdentificacion = new HashMap<String,String>();
						Iterator<Long> itMapaIdentificaciones = mapaCodigoIdentificacion.keySet().iterator();
						while( itMapaIdentificaciones.hasNext() ){
							TipoIdentificacionIf ti = mapaCodigoIdentificacion.get(itMapaIdentificaciones.next());
							mapaIdentificacion.put(ti.getCodigo(), ti.getNombre());
						}
						itMapaIdentificaciones = null;
						
						//Se hace lista con nombre de identificaciones para compra.
						List<String> listaIdentificacionesCompra = new LinkedList<String>();
						Iterator<String> itMapaCompra = mapaCodigoTipoIdentificacionCompra.keySet().iterator();
						while( itMapaCompra.hasNext() ){
							String nombreIdentificacion = mapaIdentificacion.get(itMapaCompra.next());
							if ( nombreIdentificacion != null )
								listaIdentificacionesCompra.add(nombreIdentificacion);
						}
						String error = "Proveedor en compra con c\u00f3digo: "+notaCredito.getCodigo()+" debe tener una de las siguientes identificaciones: ";
						for( int i = 0; i < listaIdentificacionesCompra.size() ; i++ ){
							error += ( listaIdentificacionesCompra.get(i) );
							if ( i < ( listaIdentificacionesCompra.size()-1 ) )
								error += ", ";	
						}
							
						throw new GenericBusinessException(error);
						
					}
					detalleCompra.setTpIdProv(codigoTipoIdentificacionProv);

					//detalleCompra.setIdProv(clienteIf.getIdentificacion());
					detalleCompra.setIdProv(cliente.getIdentificacion());

					int codigoTipoComprobante = Integer.valueOf(tipoComprobanteIf.getCodigo());
					detalleCompra.setTipoComp(codigoTipoComprobante);

					detalleCompra.setAut(notaCredito.getAutorizacion());
					
					detalleCompra.setEstab(preimpreso[0]);
					
					try{
						int ptoEmi = Integer.parseInt(preimpreso[1]);
						detalleCompra.setPtoEmi(formatoTresNumeros.format(ptoEmi));
					} catch(NumberFormatException e){
						throw new GenericBusinessException("Revisar el Punto de Emision en Preimpreso, en Nota de Credito con c\u00f3digo: "+notaCredito.getCodigo() );
					}
					
					detalleCompra.setSec(Integer.parseInt(preimpreso[2]));
					
					java.util.Date fecha = new java.util.Date(notaCredito.getFechaEmision().getTime());
					detalleCompra.setFechaEmiCom(formatoFecha.format(fecha));

					Air air = new Air();
					if (mapaAirValores.size()>0){
						Iterator itMapaAirValores = mapaAirValores.keySet().iterator();
						while(itMapaAirValores.hasNext()){
							DetalleAir detalleAir = mapaAirValores.get(itMapaAirValores.next());
							air.addDetalleAir(detalleAir);
						}
					}
					detalleCompra.setAir(air);

					compraRetenciones = compraRetencionLocal.findCompraRetencionByCompraId(notaCredito.getId());
					if (compraRetenciones!=null ){
						//if (compraRetenciones.size()<3){
							for ( CompraRetencionIf compraRetencionIf : compraRetenciones  ){
								try{
									Integer autRetencion = Integer.parseInt(compraRetencionIf.getCodigoImpuesto().trim()); 
									//Los codigos de Retencion a la Renta entre 332 y 337 no se crea papeleta de retencion.
									if ( !(autRetencion>=332 && autRetencion<=337) && 
											veificarDatosRetencion (compraRetencionIf) ){
										PapeletaRetencion p = new PapeletaRetencion(
												compraRetencionIf.getEstablecimiento(),compraRetencionIf.getPuntoEmision()
												,compraRetencionIf.getSecuencial(),compraRetencionIf.getAutorizacion()
												,compraRetencionIf.getFechaEmision());
										mapaPapeletas.put(p, p);
									}
								} catch(Exception e){
									System.out.println("Error en numero de Autorizacion de Retencion de compra con id : "+notaCredito.getId());
								}
							}
							
							int contadorRetenciones = 1;
							for ( PapeletaRetencion papeleta : mapaPapeletas.keySet() ){
								//CompraRetencionIf compraRetencion = (CompraRetencionIf) itCompraRetenciones.next();
								if (contadorRetenciones==1){
									detalleCompra.setEstabRet(papeleta.getEstablecimiento());
									detalleCompra.setPtoEmiRet(papeleta.getPtoImpresion());
									detalleCompra.setSecRet(Integer.valueOf(papeleta.getSecuencial()));
									detalleCompra.setAutRet(papeleta.getAutorizacion());
									fecha = new java.util.Date(papeleta.getFechaEmision().getTime());
									detalleCompra.setFechaEmiRet(formatoFecha.format(fecha));
								}
								if (contadorRetenciones==2){
									detalleCompra.setEstabRet1(papeleta.getEstablecimiento());
									detalleCompra.setPtoEmiRet1(papeleta.getPtoImpresion());
									detalleCompra.setSecRet1(Integer.valueOf(papeleta.getSecuencial()));
									detalleCompra.setAutRet1(papeleta.getAutorizacion());
									fecha = new java.util.Date(papeleta.getFechaEmision().getTime());
									detalleCompra.setFechaEmiRet1(formatoFecha.format(fecha));
								}
								contadorRetenciones++;
							}
						/*} else{
							throw new GenericBusinessException("Revisar Retenciones(2 m\u00e1ximo) en compra con c\u00f3digo: "+notaCredito.getCodigo());
						}*/
					} 

				}
				detallecompras.add(detalleCompra);

			} catch(Exception e){
				if (e instanceof GenericBusinessException)
					detallecompras.add("\t"+numeroFila+".- "+e.getMessage()+"\n");
				else{
					e.printStackTrace();
					detallecompras.add("\t"+numeroFila+".- Revisar datos compra con c\u00f3digo: "+notaCredito.getCodigo()+"\n");
				}
			}
			numeroFila++;
		}
		return detallecompras;
	}
	
	private TipoDocumentoIf verificarTipoDocumento( Map<Long,TipoDocumentoIf> mapaTipoDocumento,Long tipoDocumentoId ) throws GenericBusinessException{
		TipoDocumentoIf tipoDocumentoIf = mapaTipoDocumento.get(tipoDocumentoId);
		if ( tipoDocumentoIf == null ){
			tipoDocumentoIf = tipoDocumentoLocal.getTipoDocumento(tipoDocumentoId);
			if ( tipoDocumentoIf == null )
				throw new GenericBusinessException("No existe Tipo Documento con id "+tipoDocumentoId);
			mapaTipoDocumento.put(tipoDocumentoIf.getId(), tipoDocumentoIf);
		}
		return tipoDocumentoIf;
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Object> generarNotasCreditoCompraAnexoTransaccional(int numeroFila,Long idEmpresa,Map<String, Date> mapaQuery,
			HashMap<String, Double> mapaImpuestos,HashMap<Integer, Integer> mapaCodigoIva) throws GenericBusinessException{

		NumberFormat formatoTresNumeros = new DecimalFormat("000");

		Map<Long, TipoDocumentoIf> mapaTipoDocumentos = cargarTipoDocumento(idEmpresa);
		Map<Long,SriTipoComprobanteIf> mapaTipoComprobantes = cargarTipoComprobante();
		Map<Long, TipoIdentificacionIf> mapaTipoIdentificaciones = cargarTipoIdentificacion();
		Map<Long,SriSustentoTributarioIf> mapaSustentoTributarios = cargarSustentoTributario();
		
		Map<String,String> mapaCodigoTipoIdentificacionXCodigoSpirit = new HashMap<String, String>();
		cargarCodigosTipoIdentificacion("COMPRA", mapaCodigoTipoIdentificacionXCodigoSpirit);
		
		Map<Long,ClienteOficinaIf> mapaClienteOficina = new HashMap<Long,ClienteOficinaIf>();
		Map<Long,ClienteIf> mapaCliente = new HashMap<Long,ClienteIf>();

		Map<Long,ProductoIf> mapaProductos = new HashMap<Long,ProductoIf>();
		Map<Long,GenericoIf> mapaGenericos = new HashMap<Long,GenericoIf>();

		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("estado", "A"); //Activa
		mapa.put("tipoCartera", "P"); //Proveedor
		Collection<NotaCreditoIf> notasCreditos = notaCreditoLocal.getNotaCreditoByMapFechaInicioFechaFin(
				mapa,mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin"), idEmpresa );
		
		Map<Long,DocumentoIf> mapaDocumentosParaProcesar = new HashMap<Long,DocumentoIf>();
		cargarCodigosNotasCredito(mapaDocumentosParaProcesar,idEmpresa);
		
		SriSustentoTributarioIf sustentoTributario = obtenerSustentoTributarioPredeterminadoAT(idEmpresa);
		
		ArrayList<Object> detallecompras = new ArrayList<Object>();
		
		ContinuarCabecera:
		for ( NotaCreditoIf notaCredito : notasCreditos ){
			
			TipoDocumentoIf tipoDocumento = mapaTipoDocumentos.get(notaCredito.getTipoDocumentoId());
			SriTipoComprobanteIf tipoComprobante = mapaTipoComprobantes.get(tipoDocumento.getIdSriTipoComprobante());
			
			try{
				ClienteOficinaIf oficina = verificarClienteOficina(
					mapaClienteOficina,notaCredito.getOperadorNegocioId());
				ClienteIf cliente = verificarCliente(mapaCliente, oficina.getClienteId());
				
				Double baseImponibleGravada = 0.0;
				Double baseImponible = 0.0;
				Double retBienes = 0.0,retServicios = 0.0,retServicios100 = 0.0;
				Double iva = 0.0;
	
				Collection<NotaCreditoDetalleIf> detalles = 
					notaCreditoDetalleLocal.findNotaCreditoDetalleByNotaCreditoId(notaCredito.getId());
				
				CompraIf compra = null;
				
				boolean hayDetalles = false;
				ContinuarDetalle:
				for ( NotaCreditoDetalleIf detalle : detalles ){
					
					DocumentoIf documento = documentoLocal.getDocumento(detalle.getDocumentoId());
					if ( !mapaDocumentosParaProcesar.containsKey(documento.getId()) )
						continue ContinuarDetalle;
					
					hayDetalles = true;
					
					ProductoIf producto = verificarProducto(mapaProductos, detalle.getProductoId());
					GenericoIf generico = verificarGenerico(mapaGenericos, producto.getGenericoId());
					Double cantidad = detalle.getCantidad().doubleValue();
					if ( "S".equalsIgnoreCase(generico.getCobraIva()) ){
						baseImponibleGravada += (cantidad * detalle.getValor().doubleValue() );
						iva += (cantidad * detalle.getIva().doubleValue());
					} else {
						baseImponible += (cantidad * detalle.getValor().doubleValue());
					}
					
					Map<String,Object> mapaCartera = new HashMap<String, Object>();
					mapaCartera.put("referenciaId", notaCredito.getId());
					mapaCartera.put("tipodocumentoId", notaCredito.getTipoDocumentoId());
					Collection<CarteraIf> carteras = carteraLocal.findCarteraByQuery(mapaCartera);
					if ( carteras.size() == 0  )
						throw new GenericBusinessException("La Nota de Credito con codigo "+notaCredito.getCodigo()+"no tiene Carteras !!");
					CarteraIf cartera = carteras.iterator().next();
					
					//El detalle de esta cartera es carteraDetalle en tabla CarteraAfecta
					Collection<CarteraDetalleIf> carteraDetalles = carteraDetalleLocal.findCarteraDetalleByCarteraId(cartera.getId());
					if ( detalles.size() == 0 )
						throw new GenericBusinessException("La cartera de la Nota de Credito no tiene detalle !!");
					if ( detalles.size() > 1 )
						throw new GenericBusinessException("La cartera de la Nota de Credito tiene mas de un detalle !!");
					
					CarteraDetalleIf carteraDetalle = carteraDetalles.iterator().next();
					
					Collection<CarteraAfectaIf> carteraAfectas = carteraAfectaLocal.findCarteraAfectaByCarteradetalleId(carteraDetalle.getId());
					if ( carteraAfectas.size() >= 1 ) {
						CarteraAfectaIf carteraAfecta = carteraAfectas.iterator().next();
						Long idCarteraCompraDetalle = carteraAfecta.getCarteraafectaId();
						CarteraDetalleIf carteraDetalleCompra = carteraDetalleLocal.getCarteraDetalle(idCarteraCompraDetalle);
						CarteraIf carteraCompra = carteraLocal.getCartera(carteraDetalleCompra.getCarteraId());
						
						Map<String,Object> mapaCompra = new HashMap<String, Object>();
						mapaCompra.put("id", carteraCompra.getReferenciaId());
						mapaCompra.put("tipodocumentoId", carteraCompra.getTipodocumentoId());
						Collection<CompraIf> compras = compraLocal.findCompraByQuery(mapaCompra);
						if ( compras.size() ==0 )
							throw new GenericBusinessException("No existe Compra para la cartera Registrada !!");
						compra = compras.iterator().next();
						
					}
					
				}
				
				if ( !hayDetalles )
					continue ContinuarCabecera;
				
				com.spirit.sri.at.DetalleCompras detalleCompra = new com.spirit.sri.at.DetalleCompras();
				
				TipoIdentificacionIf tipoIdentificacion = mapaTipoIdentificaciones.get(cliente.getTipoidentificacionId());
				if (tipoIdentificacion==null)
					throw new GenericBusinessException("Revisar tipo de Identificai\u00f3n de Cliente con identificac\u00f3n:"+cliente.getIdentificacion());

				String codigoTipoIdentificacionProv = mapaCodigoTipoIdentificacionXCodigoSpirit.get(tipoIdentificacion.getCodigo());
				detalleCompra.setTpIdProv(codigoTipoIdentificacionProv);
				detalleCompra.setIdProv(cliente.getIdentificacion());
				
				int codigoTipoComprobante = Integer.valueOf(tipoComprobante.getCodigo());
				detalleCompra.setTipoComprobante(codigoTipoComprobante);

				detalleCompra.setAutorizacion(notaCredito.getAutorizacion());
				
				java.util.Date fecha = new java.util.Date(notaCredito.getFechaEmision().getTime());
				String fechaEmision = formatoFecha.format(fecha);
				detalleCompra.setFechaEmision(fechaEmision);
				detalleCompra.setFechaRegistro(fechaEmision);
				
				String preimpreso = notaCredito.getPreimpreso();
				if ( preimpreso == null || "".equals(preimpreso) )
					throw new GenericBusinessException("Nota de credito con codigo "+notaCredito.getCodigo()+"No tiene preimpreso ");
				
				String preimpresoDividido[] = preimpreso.split("-");
				if (preimpreso==null || preimpresoDividido.length != 3)
					throw new GenericBusinessException("Revisar Preimpreso (formato: ###-###-#######) de Nota de Credito con c\u00f3digo: "+notaCredito.getCodigo());
				
				detalleCompra.setEstablecimiento(preimpresoDividido[0]);
				try{
					int ptoEmi = Integer.parseInt(preimpresoDividido[1]);
					detalleCompra.setPuntoEmision(formatoTresNumeros.format(ptoEmi));
				} catch(NumberFormatException e){
					throw new GenericBusinessException("Revisar el Punto de Emision en Preimpreso, en Nota de Credito con c\u00f3digo: "+notaCredito.getCodigo() );
				}
				detalleCompra.setSecuencial(Integer.parseInt(preimpresoDividido[2]));
				
				detalleCompra.setAutorizacion(notaCredito.getAutorizacion());
				
				
				detalleCompra.setCodSustento(sustentoTributario!=null?sustentoTributario.getCodigo():"");
				
				detalleCompra.setBaseNoGraIva(BigDecimal.ZERO);
				baseImponibleGravada = utilitariosLocal.redondeoValor(baseImponibleGravada);
				detalleCompra.setBaseImpGrav(utilitariosLocal.redondeoValor(new BigDecimal(baseImponibleGravada)));
				baseImponible = utilitariosLocal.redondeoValor(baseImponible);
				detalleCompra.setBaseImponible(utilitariosLocal.redondeoValor(new BigDecimal(baseImponible)));
				iva = utilitariosLocal.redondeoValor(iva);
				detalleCompra.setMontoIva(utilitariosLocal.redondeoValor(new BigDecimal(iva)));
				
				detalleCompra.setMontoIce(BigDecimal.ZERO);
				
				if ( compra != null ){
					String preimpresoCompra = compra.getPreimpreso();
					
					if ( preimpresoCompra != null && !"".equals(preimpresoCompra) ){
						String preimpresoCompraDividido[] = preimpresoCompra.split("-");
						if ( preimpresoCompraDividido.length == 3 ){
							detalleCompra.setEstabModificado(preimpresoCompraDividido[0]);
							try{
								int ptoEmi = Integer.parseInt(preimpresoCompraDividido[1]);
								detalleCompra.setPtoEmiModificado(formatoTresNumeros.format(ptoEmi));
							} catch(NumberFormatException e){
								throw new GenericBusinessException("Revisar el Punto de Emision en Preimpreso, en Nota de Credito con c\u00f3digo: "+notaCredito.getCodigo() );
							}
							detalleCompra.setSecuencial(Integer.parseInt(preimpresoDividido[2]));
							detalleCompra.setSecModificado(Integer.parseInt(preimpresoCompraDividido[2]));
							detalleCompra.setDocModificado(1);
							detalleCompra.setAutModificado(compra.getAutorizacion());
						}
					}
				} else {
					detalleCompra.setEstabModificado("999");
					detalleCompra.setPtoEmiModificado("999");
					
					if(preimpresoDividido[2] != null){
						detalleCompra.setSecuencial(Integer.parseInt(preimpresoDividido[2]));
					}else{
						detalleCompra.setSecuencial(Integer.parseInt("9"));
					}
					
					detalleCompra.setSecModificado(Integer.parseInt("9"));
					//ESTO SE AGREGÓ PARA RESOLVER EL PROBLEMA DE LAS N/C NO CRUZADAS
					detalleCompra.setDocModificado(0);
					detalleCompra.setAutModificado("9999999999");
				}
				
				com.spirit.sri.at.Air air = new com.spirit.sri.at.Air();
				detalleCompra.setAir(air);
				
				detalleCompra.setValorRetBienes(BigDecimal.ZERO);
				detalleCompra.setValorRetServicios(BigDecimal.ZERO);
				detalleCompra.setValRetServ100(BigDecimal.ZERO);
				
				detallecompras.add(detalleCompra);
				
			} catch(Exception e){
				if (e instanceof GenericBusinessException)
					detallecompras.add("\t"+numeroFila+".- "+e.getMessage()+"\n");
				else{
					e.printStackTrace();
					detallecompras.add("\t"+numeroFila+".- Revisar datos de Nota de Credito con c\u00f3digo: "+notaCredito.getCodigo()+"\n");
				}
			}

		}
		
		return detallecompras;
	}

	private void cargarCodigosNotasCredito( Map<Long, DocumentoIf> mapaDocumentosParaProcesar ,Long idEmpresa)
			throws GenericBusinessException {
		
		String codigoTipoParametro = SriParametros.TIPO_PARAMETRO;
		Collection<TipoParametroIf> tiposParametros = tipoParametroLocal.findTipoParametroByCodigo(codigoTipoParametro);
		if ( tiposParametros.size() == 0 )
			throw new GenericBusinessException("No existe Tipo de Parametro con codigo "+codigoTipoParametro);
		if ( tiposParametros.size() > 1 )
			throw new GenericBusinessException("Existe mas de un Tipo de Parametro con codigo "+codigoTipoParametro);
		
		TipoParametroIf tipoParametro = tiposParametros.iterator().next();
		
		String baseDocumentosNotasCredito = SriParametros.BASE_CODIGO_NOTA_CREDITO;
		Collection<ParametroEmpresaIf> parametros = utilitariosLocal.getParametroEmpresa(
			idEmpresa, tipoParametro,true, baseDocumentosNotasCredito);
		
		for ( ParametroEmpresaIf pe : parametros ){
			String codigoDocumento = pe.getValor();
			Collection<DocumentoIf> documentos = documentoLocal.findDocumentoByCodigo(codigoDocumento);
			if ( documentos.size() == 0 )
				throw new GenericBusinessException("No existe Documento con codigo "+codigoDocumento);
			DocumentoIf documento = documentos.iterator().next();
			mapaDocumentosParaProcesar.put(documento.getId(),documento);
		}
	}
	
	private SriAirIf obtenerAirPredeterminadoReoc( Long idEmpresa)
	throws GenericBusinessException {

		String codigoTipoParametro = SriParametros.TIPO_PARAMETRO;
		Collection<TipoParametroIf> tiposParametros = tipoParametroLocal.findTipoParametroByCodigo(codigoTipoParametro);
		if ( tiposParametros.size() == 0 )
			throw new GenericBusinessException("No existe Tipo de Parametro con codigo "+codigoTipoParametro);
		if ( tiposParametros.size() > 1 )
			throw new GenericBusinessException("Existe mas de un Tipo de Parametro con codigo "+codigoTipoParametro);

		TipoParametroIf tipoParametro = tiposParametros.iterator().next();

		String parametroCodigoAir = SriParametros.AIR_PREDETERMINADO_NOTA_CREDITO_REOC;
		Collection<ParametroEmpresaIf> parametros = utilitariosLocal.getParametroEmpresa(
				idEmpresa, tipoParametro,false, parametroCodigoAir);

		if ( parametros.size() == 0 )
			throw new GenericBusinessException("No existe parametro de empresa "+parametroCodigoAir+".\n" +
					"Para establecer Air predeterminado en REOC.");
		
		ParametroEmpresaIf pe = parametros.iterator().next();
		
		String codigoAir = pe.getValor();
		Map<String,Object> mapa = new HashMap<String, Object>();
		mapa.put("codigo", codigoAir);
		Collection<SriAirIf> airs = airLocal.findSriAirByQueryAndFecha(mapa, new Date(new java.util.Date().getTime()));
		if ( airs.size() == 0 )
			throw new GenericBusinessException("No existe AIR con codigo "+codigoAir);
		SriAirIf air = airs.iterator().next();
		return air;
		
	}
	
	private SriSustentoTributarioIf obtenerSustentoTributarioPredeterminadoAT( Long idEmpresa)
	throws GenericBusinessException {

		String codigoTipoParametro = SriParametros.TIPO_PARAMETRO;
		Collection<TipoParametroIf> tiposParametros = tipoParametroLocal.findTipoParametroByCodigo(codigoTipoParametro);
		if ( tiposParametros.size() == 0 )
			throw new GenericBusinessException("No existe Tipo de Parametro con codigo "+codigoTipoParametro);
		if ( tiposParametros.size() > 1 )
			throw new GenericBusinessException("Existe mas de un Tipo de Parametro con codigo "+codigoTipoParametro);

		TipoParametroIf tipoParametro = tiposParametros.iterator().next();

		String parametroCodigoAir = SriParametros.SUSTENTO_TRIBUTARIO_PREDETERMINADO_AT;
		Collection<ParametroEmpresaIf> parametros = utilitariosLocal.getParametroEmpresa(
				idEmpresa, tipoParametro,false, parametroCodigoAir);

		if ( parametros.size() == 0 )
			throw new GenericBusinessException("No existe parametro de empresa "+parametroCodigoAir+".\n" +
					"Para establecer Sustento Tributario predeterminado en AT.");
		
		ParametroEmpresaIf pe = parametros.iterator().next();
		
		String codigoST = pe.getValor();
		Collection<SriSustentoTributarioIf> sts = sustentoTributarioLocal.findSriSustentoTributarioByCodigo(codigoST);
		if ( sts.size() == 0 )
			throw new GenericBusinessException("No existe AIR con codigo "+codigoST);
		SriSustentoTributarioIf st = sts.iterator().next();
		return st;
		
	}

	private Object[] cargarRetencionesIvaCompras(ClienteIf cliente,HashMap<String, SriIvaServicioIf> mapaCodigoIvaServicio,HashMap<String, SriIvaBienIf> mapaCodigoIvaBien) throws GenericBusinessException{
		SriIvaBienIf retencionIvaBien=null;SriIvaServicioIf retencionIvaServicio=null;
		HashMap<String , String> mapaParametros = new HashMap<String, String>();
		mapaParametros.put("tipoPersona", cliente.getTipoPersona());
		mapaParametros.put("llevaContabilidad", cliente.getLlevaContabilidad());
		mapaParametros.put("estado", "A");
		Collection condicionesRetencion = proveedorRetencionLocal.findSriProveedorRetencionByQuery(mapaParametros);

		for ( Iterator itCondiciones=condicionesRetencion.iterator();itCondiciones.hasNext(); ){
			SriProveedorRetencionIf retencion = (SriProveedorRetencionIf) itCondiciones.next();
			if ("S".equalsIgnoreCase(retencion.getBienServicio()))
				retencionIvaServicio = mapaCodigoIvaServicio.get(
						"70100".equals(retencion.getReteiva().toString())?"70/100":retencion.getReteiva().toString());
			else if ("B".equalsIgnoreCase(retencion.getBienServicio()))
				retencionIvaBien = mapaCodigoIvaBien.get(retencion.getReteiva().toString());
		}

		return new Object[]{retencionIvaBien,retencionIvaServicio};
	}

	public Collection<Object> generarDetallesVentaAnexoTransaccional(int numeroFila,Long idEmpresa,Map<String, Date> mapaQuery,Set<String> codigosDocumentos,HashMap<String, SriIvaServicioIf> mapaCodigoIvaServicio,HashMap<String, SriIvaBienIf> mapaCodigoIvaBien,HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion,
			HashMap<Long, SriTipoComprobanteIf> mapaCodigoTipoComprobantes,HashMap<String, String> mapaCodigoTipoIdentificacionVenta,HashMap<String, Double> mapaImpuestos,HashMap<Integer, Integer> mapaCodigoIva) throws GenericBusinessException{
		
		
		//NumberFormat formatoDosDecimales = new DecimalFormat("0.00");
		
		double iva=0.0;
		//double ivaBienes = 0.0, ivaServicio = 0.0;
		//double ivaServicioRetenido=0.0,ivaBienesRetenido=0.0;
		int numeroDocumentos = 0;
		//String tipoComprobanteTxt="";
		//DefaultTableModel modelo = (DefaultTableModel)getTblVentas().getModel();

		//OBTENGO EL TIPO DE DOCUEMENTO: FAC O FAR
		Map<Long, TipoDocumentoIf> mapaTipoDocumento =  new HashMap<Long, TipoDocumentoIf>();
		Set<String> setCodigoDocumentos = new LinkedHashSet<String>();
		Set<Long> setIdDocumentos = new LinkedHashSet<Long>();
		Map<String,Object> mapaBusqueda = new HashMap<String, Object>();
		mapaBusqueda.put("empresaId", idEmpresa);
		for ( String codigoDocumento : codigosDocumentos ){
			mapaBusqueda.put("codigo", codigoDocumento);
			Collection<TipoDocumentoIf> tiposDocumento = tipoDocumentoLocal.findTipoDocumentoByQuery(mapaBusqueda);
			if ( tiposDocumento.size() == 0 )
				throw new GenericBusinessException("Tipo de Documento con c\u00f3digo: \""+codigoDocumento+"\" no existe");
			TipoDocumentoIf tipoDocumento = tiposDocumento.iterator().next();
			setCodigoDocumentos.add(tipoDocumento.getCodigo());
			setIdDocumentos.add(tipoDocumento.getId());
			mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
		}

		mapaBusqueda = new HashMap<String, Object>();
		mapaBusqueda.put("codigo", "CIN");
		mapaBusqueda.put("empresaId", idEmpresa);
		Collection<TipoDocumentoIf> tiposDocumentoRetenciones = tipoDocumentoLocal.findTipoDocumentoByQuery(mapaBusqueda);
		if ( tiposDocumentoRetenciones.size() == 0 )
			throw new GenericBusinessException("Tipo de Documento con c\u00f3digo: \"CIN\" no existe");
		TipoDocumentoIf tipoDocumentoRetencion = tiposDocumentoRetenciones.iterator().next();

		Set<Long> setDocumentosRetencionId = new HashSet<Long>();
		//SACO EL DOCUMENTO DE RETENCION DE IVA CLIENTE
		mapaBusqueda = new HashMap<String, Object>();
		//mapaBusqueda.put("nombre", "RETENCION RENTA CLIENTE");
		mapaBusqueda.put("nombre", DimmConstantes.DOCUMENTO_RETENCION_RENTA_CLIENTE);
		mapaBusqueda.put("tipoDocumentoId", tipoDocumentoRetencion.getId());
		Collection<DocumentoIf> documentos = documentoLocal.findDocumentoByQuery(mapaBusqueda);
		if ( documentos.size() == 0  )
			throw new GenericBusinessException("No existe documento con nombre RETENCION RENTA CLIENTE");
		DocumentoIf docRetRentaCli = documentos.iterator().next();
		setDocumentosRetencionId.add(docRetRentaCli.getId());
		
		mapaBusqueda = new HashMap<String, Object>();
		//mapaBusqueda.put("nombre", "RETENCION IVA CLIENTE");
		mapaBusqueda.put("nombre", DimmConstantes.DOCUMENTO_RETENCION_IVA_CLIENTE);
		mapaBusqueda.put("tipoDocumentoId", tipoDocumentoRetencion.getId());
		documentos = documentoLocal.findDocumentoByQuery(mapaBusqueda);
		if ( documentos.size() == 0  )
			throw new GenericBusinessException("No existe documento con nombre RETENCION IVA CLIENTE");
		DocumentoIf docRetIvaCli = documentos.iterator().next();
		setDocumentosRetencionId.add(docRetIvaCli.getId());
		
		TipoParametroIf tp = utilitariosLocal.getTipoParametro(idEmpresa,DimmConstantes.TIPO_PARAMETRO_DIMM);
		
		Collection<ParametroEmpresaIf> parametros = utilitariosLocal.getParametroEmpresa(idEmpresa, tp,false,DimmConstantes.CONTRIBUYENTE_ESPECIAL);
		if ( parametros.size() == 0 )
			throw new GenericBusinessException("Parametro de empresa con valor \""+DimmConstantes.CONTRIBUYENTE_ESPECIAL+"\" no existe !!");
		else if ( parametros.size() > 1 )
			throw new GenericBusinessException("Existe mas de una parametro de Empresa llamado "+DimmConstantes.CONTRIBUYENTE_ESPECIAL+" !!");
		ParametroEmpresaIf peContribuyenteEspecial = parametros.iterator().next();
		if ( peContribuyenteEspecial == null )
			throw new GenericBusinessException("No existe Parametro de Empresa "+DimmConstantes.CONTRIBUYENTE_ESPECIAL);
		if ( peContribuyenteEspecial.getValor() == null )
			throw new GenericBusinessException("Valor no establecido para Parametro de Empresa "+DimmConstantes.CONTRIBUYENTE_ESPECIAL);
		
		boolean esContribuyenteEspecial = peContribuyenteEspecial.getValor().trim().equals("S")?true:false;
		
		//OBTENGO LAS FACTURAS
		ArrayList<Object> detalleVentas = new ArrayList<Object>();
		//Collection<ClienteIf> clientes = facturaLocal.getClienteConFacturaByQuery(
		//		idEmpresa,tipoDocumento.getId(), mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin"));
		
		//PONGO PARAMETROS PARA FACTURAS QUE NO ESTAN ANULADAS
		//mapaFacturas.put("empresaId", idEmpresa);
		
		/*double totalIva = 0.0;
		double totalRentaUno = 0.0;
		double totalRentaDos = 0.0;*/
		
		Map<String,Map<Integer,DetalleVentas>> mapaDetalleVentas = new HashMap<String,Map<Integer,DetalleVentas>>();
		for (Long idDocumento : setIdDocumentos){
			Map<String, Object> mapaFacturas = new HashMap<String, Object>();
			mapaFacturas.put("estado", "C");
			//mapaFacturas.put("tipodocumentoId", tipoDocumento.getId());
			mapaFacturas.put("tipodocumentoId", idDocumento);
			Collection<ClienteIf> clientes = facturaLocal.getClienteConFacturaByMap_FechaInicio_FechaFin(
					mapaFacturas, mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin") );
			
			Map<Long,ProductoIf> mapaProductos = new HashMap<Long,ProductoIf>();
			Map<Long,GenericoIf> mapaGenericos = new HashMap<Long,GenericoIf>();
			
			mapaFacturas.clear();
			
			for (ClienteIf clienteIf : clientes){
				
				if(clienteIf.getIdentificacion().equals("1790598012001")){
					System.out.println("ver");
				}
				
				//tipoComprobanteTxt="";
				mapaFacturas.clear();
				double comisionAgencia = 0.0;
				double baseGravada = 0.0;
				double baseCero = 0.0; iva=0.0;
				double totalRetRentaDetalle = 0.00, totalRetIvaDetalle = 0.00;
				//ivaBienes = 0.0; ivaServicio = 0.0;
				//ivaServicioRetenido=0.0;//ivaBienesRetenido=0.0;

				TipoIdentificacionIf tipoIdentificacion = mapaCodigoIdentificacion.get(clienteIf.getTipoidentificacionId());

				/*
					SriIvaBienIf retencionIvaBien = null;
					SriIvaServicioIf retencionIvaServicio = null;
					Object[] retenciones = cargarRetencionesIvaVentas(clienteIf,mapaCodigoIvaServicio,mapaCodigoIvaBien);
					retencionIvaBien = (SriIvaBienIf)retenciones[0];
					retencionIvaServicio = (SriIvaServicioIf) retenciones[1];

					//if (retencionIvaBien==null)
					//	throw new GenericBusinessException("Revisar Parametros: Contribuyente Especial y Estado de cliente con identificaci\u00f3n: "+clienteIf.getIdentificacion()+
					//		" \n, para el saber los valores de Retenci\u00f3n Iva Bien");
					if (retencionIvaServicio==null)
						throw new GenericBusinessException("Revisar Parametros: Contribuyente Especial y Estado de cliente con identificaci\u00f3n: "+clienteIf.getIdentificacion()+
						" \n, para el saber los valores de Retenci\u00f3n Iva Servicio");

					cargarRetencionesIvaVentas(clienteIf);
				 */			
				
				//if (clienteIf.getIdentificacion().equals("0991296425001"))
				//	System.out.println("");

				try{
					TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(idDocumento);
					SriTipoComprobanteIf tipoComprobante = mapaCodigoTipoComprobantes.get(tipoDocumento.getIdSriTipoComprobante());
					if (tipoComprobante==null)
						throw new GenericBusinessException("Tipo de comprobante que tiene el Tipo de Documento con c\u00f3digo: "+tipoDocumento.getCodigo()+" no existe");

					String codigoTipoIdentificacionCliente = mapaCodigoTipoIdentificacionVenta.get(tipoIdentificacion.getCodigo());
					if ( codigoTipoIdentificacionCliente == null )
						throw new GenericBusinessException("Revisar Identificacion por Tipo de Documento: 'Venta' en modulo SRI." +
								"\n No existe c\u00f3digo para Tipo de Identificaci\u00f3n: "+tipoIdentificacion.getNombre());
					

					//mapaFacturas.put("codigoDocumento", codigoDocumento);
					mapaFacturas.put("codigoDocumento", tipoDocumento.getCodigo());
					mapaFacturas.put("clienteId", clienteIf.getId());
					mapaFacturas.put("estado", "C");
					Collection<FacturaIf> facturas = facturaLocal.getFacturaByMap_FechaInicio_FechaFin(
							mapaFacturas, mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin"),idEmpresa);
					numeroDocumentos = 0;

					Map<String,Object> mapaFactura = new HashMap<String,Object>();

					Double descuento = 0.0;
					Double descuentoGlobal = 0.0;
					for (FacturaIf factura  : facturas){
						
						if(factura.getPreimpresoNumero().equals("002-001-000008769")){
							System.out.println("ver");
						}

						numeroDocumentos++;
						
						double descuentoPorFactura = 0.0,descuentoGlobalPorFactura = 0.0;
						double comisionPorFactura = 0.0;
						if ( factura.getPorcentajeComisionAgencia() != null ){
							Double valorCabecera = utilitariosLocal.redondeoValor(factura.getValor().doubleValue());
							Double porcentajeComision = utilitariosLocal.redondeoValor(factura.getPorcentajeComisionAgencia().doubleValue());
							descuentoPorFactura = utilitariosLocal.redondeoValor(factura.getDescuento().doubleValue());
							descuentoGlobalPorFactura = utilitariosLocal.redondeoValor(factura.getDescuentoGlobal().doubleValue());
							comisionPorFactura = ((valorCabecera-descuentoPorFactura-descuentoGlobalPorFactura)*porcentajeComision/100);
						}
						descuento += descuentoPorFactura;
						descuentoGlobal += descuentoGlobalPorFactura;
						comisionAgencia += comisionPorFactura;

						Collection<FacturaDetalleIf> facturasDetalle = facturaDetalleLocal.findFacturaDetalleByFacturaId(factura.getId());
						for(FacturaDetalleIf facturaDetalle : facturasDetalle){
							//ProductoIf producto = productoLocal.getProducto(facturaDetalle.getProductoId());
							ProductoIf producto = verificarProducto(mapaProductos, facturaDetalle.getProductoId());
							//GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
							GenericoIf generico = verificarGenerico(mapaGenericos, producto.getGenericoId());

							//SI ES UNA FACTURA SI PUEDE COBRAR IVA
							//if (tipoComprobante.getNombre().equalsIgnoreCase("DOCUMENTOS AUTORIZADOS UTILIZADOS EN VENTAS EXCEPTO N/C N/D")){
							if ( tipoDocumento.getCodigo().equalsIgnoreCase("FAC") ||
									tipoDocumento.getCodigo().equalsIgnoreCase("FCO") ){
								//Verifico si cobra iva
								if ( "s".equalsIgnoreCase(generico.getCobraIva() ) ){
									baseGravada += ( facturaDetalle.getPrecioReal().doubleValue() *
											facturaDetalle.getCantidad().longValue() );
									iva += facturaDetalle.getIva().doubleValue();

									//TODO
									//Verifico si es BIEN O SERVICIO
									/*if ( "s".equalsIgnoreCase(generico.getServicio()) ){
												ivaServicio += facturaDetalle.getIva().doubleValue();
												String porcentaje = retencionIvaServicio.getDescripcionPorcentaje();
												ivaServicioRetenido += (facturaDetalle.getIva().doubleValue() * 
														(!porcentaje.equals("70/100")? Double.valueOf(porcentaje).doubleValue() : 0.70) / 100 );
											} */

									//NO SE VENDE BIENES
									//else{
									//	ivaBienes += facturaDetalle.getIva().doubleValue();
									//	ivaBienesRetenido += (ivaBienes * Double.valueOf(retencionIvaBien.getDescripcionPorcentaje()).doubleValue() / 100);
									//}

								} else{
									baseCero += (facturaDetalle.getCantidad().doubleValue() * facturaDetalle.getPrecioReal().doubleValue());
								}
							} 
							//SI ES UNA FACTURA POR REEMBOLSO NO COBRA IVA Y LOS VALORES SE VAN A BASE IMMPONIBLE CERO
							//else if (tipoComprobante.getNombre().equalsIgnoreCase("COMPROBANTE DE VENTA EMITIDO POR REEMBOLSO")){
							else if ( tipoDocumento.getCodigo().equalsIgnoreCase("FAR") ||
									tipoDocumento.getCodigo().equalsIgnoreCase("FAE")	){
								baseCero += (facturaDetalle.getCantidad().doubleValue() * facturaDetalle.getPrecioReal().doubleValue());
							}
						}

						//SUMO TODAS LAS RETENCIONES DE LA FACTURAS 
						/*
						mapaFactura.clear();
						mapaFactura.put("referenciaId", factura.getId());
						mapaFactura.put("tipodocumentoId",tipoDocumento.getId() );
						Collection<CarteraIf> carterasFactura = carteraLocal.findCarteraByQuery(mapaFactura, idEmpresa);
						for ( CarteraIf carteraFactura : carterasFactura ){
							
							Collection<CarteraDetalleIf> carteraDetallesFactura = 
								carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraFactura.getId());
							for(CarteraDetalleIf carteraDetalleIf : carteraDetallesFactura ){
							
								Collection<CarteraAfectaIf> carterasAfecta = carteraAfectaLocal
									.findCarteraAfectaByCarteraafectaId( carteraDetalleIf.getId() );
								for ( CarteraAfectaIf carteraAfecta : carterasAfecta ){
									CarteraDetalleIf carterasDetalle = carteraDetalleLocal.getCarteraDetalle(carteraAfecta.getCarteradetalleId());
									
									if ( carterasDetalle.getDocumentoId().longValue() == docRetRentaCli.getId().longValue() ){
										totalRetRentaDetalle += carterasDetalle.getValor().doubleValue();
									}
									if (!esContribuyenteEspecial){
										if ( carterasDetalle.getDocumentoId().longValue() == docRetIvaCli.getId().longValue() ){
											totalRetIvaDetalle += carterasDetalle.getValor().doubleValue();
										}
									}
									
								}
							}
						}*/
						
						Map<String, Object> mapaRetenciones = new HashMap<String, Object>();
						mapaRetenciones.put("estadoFactura", "C");
						mapaRetenciones.put("facturaId", factura.getId());
						Collection<CarteraDetalleIf> carteraDetalleRetenciones = facturaLocal.getRetencionesDeFacturas(
								setDocumentosRetencionId, mapaRetenciones, 
								mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin"), 
								DimmConstantes.TIPO_RESULTADO_RETENCION_RETENCIONES);
						
						for ( CarteraDetalleIf carterasDetalle : carteraDetalleRetenciones ){
							if ( carterasDetalle.getDocumentoId().longValue() == docRetRentaCli.getId().longValue() ){
								totalRetRentaDetalle += carterasDetalle.getValor().doubleValue();
									/*if ( carterasDetalle.getSriClienteRetencionId().longValue() == 40L ){
										totalRentaUno += carterasDetalle.getValor().doubleValue();
									} else if ( carterasDetalle.getSriClienteRetencionId().longValue() == 41L ){
										totalRentaDos += carterasDetalle.getValor().doubleValue();
									} */
							}
							if (!esContribuyenteEspecial){
								if ( carterasDetalle.getDocumentoId().longValue() == docRetIvaCli.getId().longValue() ){
									totalRetIvaDetalle += carterasDetalle.getValor().doubleValue();
									//totalIva += carterasDetalle.getValor().doubleValue();
								}
							}
						}

					}

					//SriTipoComprobanteIf tipoComprobante = mapaCodigoTipoComprobantes.get(tipoDocumento.getIdSriTipoComprobante());
					//tipoComprobanteTxt = tipoComprobante.getNombre();

					DetalleVentas detalleVenta = new DetalleVentas();

					detalleVenta.setTpIdCliente(codigoTipoIdentificacionCliente);

					String idCliente = clienteIf.getIdentificacion();
					detalleVenta.setIdCliente(idCliente);

					int idTipoComprobante = Integer.valueOf(tipoComprobante.getCodigo());
					detalleVenta.setTipoComprobante(idTipoComprobante);

					//TIENE QUE SER LA FECHA DE FIN DE MES DEL CUAL SE ESTA DECLARANDO
					//String fechaRegistroString = formatoFecha.format(mapaQuery.get("fechaFin"));
					//detalleVenta.setFechaRegistro(fechaRegistroString);

					detalleVenta.setNumeroComprobantes(numeroDocumentos);

					//detalleVenta.setFechaEmision(fechaRegistroString);

					BigDecimal baseNoGravIva = BigDecimal.ZERO;
					detalleVenta.setBaseNoGraIva(baseNoGravIva);

					baseCero = utilitariosLocal.redondeoValor(baseCero);
					BigDecimal baseImponible = utilitariosLocal.redondeoValor(new BigDecimal(baseCero));
					detalleVenta.setBaseImponible(baseImponible);

					//detalleVenta.setIvaPresuntivo(IvaPresuntivoType.N);

					//BASE GRAVADA ES EL TOTAL, ASI ESTA GUARDADO EN LA BASE
					descuento = utilitariosLocal.redondeoValor(descuento);
					descuentoGlobal = utilitariosLocal.redondeoValor(descuentoGlobal);
					comisionAgencia = utilitariosLocal.redondeoValor(comisionAgencia);
					baseGravada = utilitariosLocal.redondeoValor(baseGravada-descuento-descuentoGlobal+comisionAgencia);
					BigDecimal baseImponibleGravada = utilitariosLocal.redondeoValor(new BigDecimal(baseGravada));
					detalleVenta.setBaseImpGrav(baseImponibleGravada);

					//int codigoIva = mapaCodigoIva.get(mapaImpuestos.get("IVA").intValue());
					//detalleVenta.setPorcentajeIva(codigoIva);

					iva = utilitariosLocal.redondeoValor(iva);
					BigDecimal montoIva = utilitariosLocal.redondeoValor(new BigDecimal(iva));
					detalleVenta.setMontoIva(montoIva);

					totalRetRentaDetalle = utilitariosLocal.redondeoValor(totalRetRentaDetalle);
					BigDecimal valorRetRenta = utilitariosLocal.redondeoValor(new BigDecimal(totalRetRentaDetalle));
					detalleVenta.setValorRetRenta(valorRetRenta);

					totalRetIvaDetalle = utilitariosLocal.redondeoValor(totalRetIvaDetalle);
					BigDecimal valorRetIva = utilitariosLocal.redondeoValor(new BigDecimal(totalRetIvaDetalle));
					detalleVenta.setValorRetIva(valorRetIva);

					//BigDecimal baseImponibleIce = new BigDecimal(0.00);
					//detalleVenta.setBaseImpIce(baseImponibleIce);

					//int codigoIce = 0;
					//detalleVenta.setPorcentajeIce(codigoIce);

					//BigDecimal montoIce = new BigDecimal(0.00);
					//detalleVenta.setMontoIce(montoIce);

					//ivaBienes = utilitariosLocal.redondeoValor(BigDecimal.valueOf(ivaBienes)).doubleValue();
					//BigDecimal montoIvaBienes = new BigDecimal(formatoDosDecimales.format(ivaBienes));
					//detalleVenta.setMontoIvaBienes(montoIvaBienes);

					//int codigoIvaBien = retencionIvaBien != null ? retencionIvaBien.getCodigo():0;
					//detalleVenta.setPorRetBienes(codigoIvaBien);

					//ivaBienesRetenido = utilitariosLocal.redondeoValor(BigDecimal.valueOf(ivaBienesRetenido)).doubleValue();
					//BigDecimal valorIvaRetBienes = new BigDecimal(formatoDosDecimales.format(ivaBienesRetenido));
					//detalleVenta.setValorRetBienes(valorIvaRetBienes);

					//ivaServicio = utilitariosLocal.redondeoValor(BigDecimal.valueOf(ivaServicio)).doubleValue();
					//BigDecimal montoIvaServicio = new BigDecimal(formatoDosDecimales.format(ivaServicio));
					//detalleVenta.setMontoIvaServicios(montoIvaServicio);

					//int codigoIvaServicio = retencionIvaServicio!=null?retencionIvaServicio.getCodigo():0;
					//detalleVenta.setPorRetServicios(codigoIvaServicio);

					//ivaServicioRetenido = utilitariosLocal.redondeoValor(BigDecimal.valueOf(ivaServicioRetenido)).doubleValue();
					//BigDecimal valorIvaRetServicio = new BigDecimal(formatoDosDecimales.format(ivaServicioRetenido));
					//detalleVenta.setValorRetServicios(valorIvaRetServicio);

					/*detalleVenta.setRetPresuntiva();

							Air air = new Air();

							String retenidoS = "";
							DetalleAir detalleAir=null;
							BigDecimal retenido = null;

							Map<String , String> mapaRetencionA = new HashMap<String , String>();
							mapaRetencionA.put("contribuyenteEspecial", clienteIf.getContribuyenteEspecial());
							mapaRetencionA.put("estado", "A");
							Collection retencionesA = clienteRetencionLocal.findSriClienteRetencionByQuery(mapaRetencionA);
							SriClienteRetencionIf retencionA = (SriClienteRetencionIf) retencionesA.iterator().next();

							//Se inserta las retenciones Renta que son de las facturas, que salen de los comprobantes de ingreso
							if ( totalRentaDetalle > 0.00 ){
								try {
									detalleAir = new DetalleAir();
									detalleAir.setCodRetAir("318");
									detalleAir.setBaseImpAir(baseImponibleGravada);
									detalleAir.setPorcentajeAir(retencionA.getRetefuente());
									//totalRentaDetalle = 
									//	baseImponibleGravada.doubleValue() * retencionA.getRetefuente().doubleValue() /100; 
									retenidoS = formatoDosDecimales.format(totalRentaDetalle);
									retenido = new BigDecimal(retenidoS);
									detalleAir.setValRetAir(retenido);
									air.addDetalleAir(detalleAir);
								} catch(Exception e){
									e.printStackTrace();
								}
							}


							detalleVenta.setAir(air);*/

					boolean existe = verificarDetalleVenta(
							mapaDetalleVentas, detalleVenta);
					if ( !existe ){
						Map<Integer,DetalleVentas> mapaPorTipoComprobante = mapaDetalleVentas.get(detalleVenta.getTipoComprobante());
						if ( mapaPorTipoComprobante == null ){
							mapaPorTipoComprobante = new HashMap<Integer, DetalleVentas>();
						}
						mapaPorTipoComprobante.put(detalleVenta.getTipoComprobante(), detalleVenta);
						mapaDetalleVentas.put(detalleVenta.getIdCliente(),mapaPorTipoComprobante);

						detalleVentas.add(detalleVenta);
					}

				} catch(Exception e){
					if (e instanceof GenericBusinessException)
						detalleVentas.add("\t"+e.getMessage()+"\n");
					else{
						detalleVentas.add("\t"+numeroFila+".- "+e.getMessage()+" de cliente con identificaci\u00f3n: "
								+clienteIf.getIdentificacion()+"\n");
						e.printStackTrace();
					}
				}
				numeroFila++;
			}
		}
		/*System.out.println("************************************************");
		System.out.println("Iva: "+utilitariosLocal.redondeoValor(totalIva));
		System.out.println("Renta Uno: "+utilitariosLocal.redondeoValor(totalRentaUno));
		System.out.println("Renta Dos: "+utilitariosLocal.redondeoValor(totalRentaDos));
		System.out.println("************************************************");
		
		double ti = 0.0;
		double tr = 0.0;
		for ( Object o : detalleVentas ){
			if ( o instanceof DetalleVentas ){
				DetalleVentas dv = (DetalleVentas) o;
				ti += dv.getValorRetIva().doubleValue();
				tr += dv.getValorRetRenta().doubleValue();
			}
		}
		System.out.println("ti: "+utilitariosLocal.redondeoValor(ti));
		System.out.println("tr: "+utilitariosLocal.redondeoValor(tr));*/
		
		//RETENCIONES QUE LA FACTURA SON DEL MES PASADO
		generarDetallesVentaRetencionesAnexoTransaccional(
			numeroFila, idEmpresa, mapaQuery, codigosDocumentos, mapaCodigoIvaServicio, 
			mapaCodigoIvaBien, mapaCodigoIdentificacion, mapaCodigoTipoComprobantes, 
			mapaCodigoTipoIdentificacionVenta, mapaImpuestos, mapaCodigoIva, 
			mapaDetalleVentas,mapaTipoDocumento,detalleVentas);
		
		
		return detalleVentas;
	}

	public Collection<Object> generarDetallesVentaRetencionesAnexoTransaccional(int numeroFila,Long idEmpresa,Map<String, Date> mapaQuery,
			Set<String> codigosDocumentos,HashMap<String, SriIvaServicioIf> mapaCodigoIvaServicio,
			HashMap<String, SriIvaBienIf> mapaCodigoIvaBien,HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion,
			HashMap<Long, SriTipoComprobanteIf> mapaCodigoTipoComprobantes,HashMap<String, String> mapaCodigoTipoIdentificacionVenta,
			HashMap<String, Double> mapaImpuestos,HashMap<Integer, Integer> mapaCodigoIva,
			Map<String,Map<Integer,DetalleVentas>> mapaDetalleVentas,
			Map<Long, TipoDocumentoIf> mapaTipoDocumento,
			ArrayList<Object> detalleVentas ) throws GenericBusinessException{
		
		double iva=0.0;
		int numeroDocumentos = 0;

		Map<String,Object> mapaBusqueda = new HashMap<String, Object>();
		mapaBusqueda.put("codigo", "CIN");
		mapaBusqueda.put("empresaId", idEmpresa);
		Collection<TipoDocumentoIf> tiposDocumentoRetenciones = tipoDocumentoLocal.findTipoDocumentoByQuery(mapaBusqueda);
		if ( tiposDocumentoRetenciones.size() == 0 )
			throw new GenericBusinessException("Tipo de Documento con c\u00f3digo: \"CIN\" no existe");
		TipoDocumentoIf tipoDocumentoRetencion = tiposDocumentoRetenciones.iterator().next();

		Set<Long> setDocumentosRetencionId = new HashSet<Long>();
		//SACO EL DOCUMENTO DE RETENCION DE IVA CLIENTE
		mapaBusqueda = new HashMap<String, Object>();
		//mapaBusqueda.put("nombre", "RETENCION RENTA CLIENTE");
		mapaBusqueda.put("nombre", DimmConstantes.DOCUMENTO_RETENCION_RENTA_CLIENTE);
		mapaBusqueda.put("tipoDocumentoId", tipoDocumentoRetencion.getId());
		Collection<DocumentoIf> documentos = documentoLocal.findDocumentoByQuery(mapaBusqueda);
		if ( documentos.size() == 0  )
			throw new GenericBusinessException("No existe documento con nombre RETENCION RENTA CLIENTE");
		DocumentoIf docRetRentaCli = documentos.iterator().next();
		setDocumentosRetencionId.add(docRetRentaCli.getId());
		
		mapaBusqueda = new HashMap<String, Object>();
		//mapaBusqueda.put("nombre", "RETENCION IVA CLIENTE");
		mapaBusqueda.put("nombre", DimmConstantes.DOCUMENTO_RETENCION_IVA_CLIENTE);
		mapaBusqueda.put("tipoDocumentoId", tipoDocumentoRetencion.getId());
		documentos = documentoLocal.findDocumentoByQuery(mapaBusqueda);
		if ( documentos.size() == 0  )
			throw new GenericBusinessException("No existe documento con nombre RETENCION IVA CLIENTE");
		DocumentoIf docRetIvaCli = documentos.iterator().next();
		setDocumentosRetencionId.add(docRetIvaCli.getId());
		
		TipoParametroIf tp = utilitariosLocal.getTipoParametro(idEmpresa,DimmConstantes.TIPO_PARAMETRO_DIMM);
		
		Collection<ParametroEmpresaIf> parametros = utilitariosLocal.getParametroEmpresa(idEmpresa, tp,false,DimmConstantes.CONTRIBUYENTE_ESPECIAL);
		if ( parametros.size() == 0 )
			throw new GenericBusinessException("Parametro de empresa con valor \""+DimmConstantes.CONTRIBUYENTE_ESPECIAL+"\" no existe !!");
		else if ( parametros.size() > 1 )
			throw new GenericBusinessException("Existe mas de una parametro de Empresa llamado "+DimmConstantes.CONTRIBUYENTE_ESPECIAL+" !!");
		ParametroEmpresaIf pe = parametros.iterator().next();
		if ( pe == null )
			throw new GenericBusinessException("No existe Parametro de Empresa "+DimmConstantes.CONTRIBUYENTE_ESPECIAL);
		if ( pe.getValor() == null )
			throw new GenericBusinessException("Valor no establecido para Parametro de Empresa "+DimmConstantes.CONTRIBUYENTE_ESPECIAL);
		
		boolean esContribuyenteEspecial = pe.getValor().trim().equals("S")?true:false;
		
		//OBTENGO LAS FACTURAS
		//ArrayList<Object> detalleVentas = new ArrayList<Object>();
		//Collection<ClienteIf> clientes = facturaLocal.getClienteConFacturaByQuery(
		//		idEmpresa,tipoDocumento.getId(), mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin"));
		
		Map<String, Object> mapaRetenciones = new HashMap<String, Object>();
		mapaRetenciones.put("estadoFactura", "C");
		Collection<ClienteIf> clientes = facturaLocal.getRetencionesDeFacturas(
				setDocumentosRetencionId, mapaRetenciones, 
				mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin"), 
				DimmConstantes.TIPO_RESULTADO_RETENCION_CLIENTES_FACTURAS_PASADAS);

		/*double totalIva = 0.0;
		double totalRentaUno = 0.0;
		double totalRentaDos = 0.0;*/
		
		for (ClienteIf clienteIf : clientes){

			double baseGravada = 0.0;
			double baseCero = 0.0; iva=0.0;

			TipoIdentificacionIf tipoIdentificacion = mapaCodigoIdentificacion.get(clienteIf.getTipoidentificacionId());

			try{
				mapaRetenciones = new HashMap<String, Object>();
				mapaRetenciones.put("estadoFactura", "C");
				mapaRetenciones.put("clienteId", clienteIf.getId());
				
				Collection<FacturaIf> facturas = facturaLocal.getRetencionesDeFacturas(
						setDocumentosRetencionId, mapaRetenciones, 
						mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin"), 
						DimmConstantes.TIPO_RESULTADO_RETENCION_FACTURAS);
				
				numeroDocumentos = 0;

				for ( FacturaIf factura : facturas){
					
					mapaRetenciones.put("clienteId", null);
					mapaRetenciones.put("facturaId", null);
					
					TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(factura.getTipodocumentoId());
					SriTipoComprobanteIf tipoComprobante = mapaCodigoTipoComprobantes.get(tipoDocumento.getIdSriTipoComprobante());
					if (tipoComprobante==null)
						throw new GenericBusinessException("Tipo de comprobante que tiene el Tipo de Documento con c\u00f3digo: "+tipoDocumento.getCodigo()+" no existe");

					String codigoTipoIdentificacionCliente = mapaCodigoTipoIdentificacionVenta.get(tipoIdentificacion.getCodigo());
					if ( codigoTipoIdentificacionCliente == null )
						throw new GenericBusinessException("Revisar Identificacion por Tipo de Documento: 'Venta' en modulo SRI." +
								"\n No existe c\u00f3digo para Tipo de Identificaci\u00f3n: "+tipoIdentificacion.getNombre());
						
					double totalRetRentaDetalle = 0.00, totalRetIvaDetalle = 0.00;
					
					mapaRetenciones.put("estadoFactura", "C");
					mapaRetenciones.put("facturaId", factura.getId());
					Collection<CarteraDetalleIf> carteraDetalleRetenciones = facturaLocal.getRetencionesDeFacturas(
							setDocumentosRetencionId, mapaRetenciones, 
							mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin"), 
							DimmConstantes.TIPO_RESULTADO_RETENCION_RETENCIONES_FACTURAS_PASADAS);
					
					for ( CarteraDetalleIf carterasDetalle : carteraDetalleRetenciones ){
						if ( carterasDetalle.getDocumentoId().longValue() == docRetRentaCli.getId().longValue() ){
							totalRetRentaDetalle += carterasDetalle.getValor().doubleValue();
							/*if ( carterasDetalle.getSriClienteRetencionId().longValue() == 40L ){
								totalRentaUno += carterasDetalle.getValor().doubleValue();
							} else if ( carterasDetalle.getSriClienteRetencionId().longValue() == 41L ){
								totalRentaDos += carterasDetalle.getValor().doubleValue();
							} */
						}
						if (!esContribuyenteEspecial){
							if ( carterasDetalle.getDocumentoId().longValue() == docRetIvaCli.getId().longValue() ){
								totalRetIvaDetalle += carterasDetalle.getValor().doubleValue();
								//totalIva += carterasDetalle.getValor().doubleValue();
							}
						}
					}
					
					DetalleVentas detalleVenta = new DetalleVentas();

					detalleVenta.setTpIdCliente(codigoTipoIdentificacionCliente);

					String idCliente = clienteIf.getIdentificacion();
					detalleVenta.setIdCliente(idCliente);

					int idTipoComprobante = Integer.valueOf(tipoComprobante.getCodigo());
					detalleVenta.setTipoComprobante(idTipoComprobante);

					//TIENE QUE SER LA FECHA DE FIN DE MES DEL CUAL SE ESTA DECLARANDO
					//String fechaRegistroString = formatoFecha.format(mapaQuery.get("fechaFin"));
					//detalleVenta.setFechaRegistro(fechaRegistroString);

					detalleVenta.setNumeroComprobantes(numeroDocumentos);

					BigDecimal baseNoGravIva = BigDecimal.ZERO;
					detalleVenta.setBaseNoGraIva(baseNoGravIva);

					BigDecimal baseImponible = utilitariosLocal.redondeoValor(new BigDecimal(baseCero));
					detalleVenta.setBaseImponible(baseImponible);

					BigDecimal baseImponibleGravada = utilitariosLocal.redondeoValor(new BigDecimal(baseGravada));
					detalleVenta.setBaseImpGrav(baseImponibleGravada);

					//iva = utilitariosLocal.redondeoValor(iva);
					BigDecimal montoIva = new BigDecimal(iva);
					detalleVenta.setMontoIva(montoIva);

					totalRetRentaDetalle = utilitariosLocal.redondeoValor(totalRetRentaDetalle);
					BigDecimal valorRetRenta = utilitariosLocal.redondeoValor(new BigDecimal(totalRetRentaDetalle));
					detalleVenta.setValorRetRenta(valorRetRenta);

					totalRetIvaDetalle = utilitariosLocal.redondeoValor(totalRetIvaDetalle);
					BigDecimal valorRetIva = utilitariosLocal.redondeoValor(new BigDecimal(totalRetIvaDetalle));
					detalleVenta.setValorRetIva(valorRetIva);

					boolean existe = verificarDetalleVenta(mapaDetalleVentas, detalleVenta);
					if ( !existe ){
						Map<Integer,DetalleVentas> mapaPorTipoComprobante = mapaDetalleVentas.get(detalleVenta.getTipoComprobante());
						if ( mapaPorTipoComprobante == null ){
							mapaPorTipoComprobante = new HashMap<Integer, DetalleVentas>();
						}
						mapaPorTipoComprobante.put(detalleVenta.getTipoComprobante(), detalleVenta);
						mapaDetalleVentas.put(detalleVenta.getIdCliente(),mapaPorTipoComprobante);

						detalleVentas.add(detalleVenta);
					}
				}

			} catch(Exception e){
				System.out.println("**************************ERROR******************************************");
				if (e instanceof GenericBusinessException)
					detalleVentas.add("\t"+e.getMessage()+"\n");
				else{
					detalleVentas.add("\t"+numeroFila+".- "+e.getMessage()+" de cliente con identificaci\u00f3n: "
							+clienteIf.getIdentificacion()+"\n");
					e.printStackTrace();
				}
			}
			numeroFila++;
		}
		/*System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Iva: "+utilitariosLocal.redondeoValor(totalIva));
		System.out.println("Renta Uno: "+utilitariosLocal.redondeoValor(totalRentaUno));
		System.out.println("Renta Dos: "+utilitariosLocal.redondeoValor(totalRentaDos));
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
		
		double ti = 0.0;
		double tr = 0.0;
		for ( Object o : detalleVentas ){
			if ( o instanceof DetalleVentas ){
				DetalleVentas dv = (DetalleVentas) o;
				ti += dv.getValorRetIva().doubleValue();
				tr += dv.getValorRetRenta().doubleValue();
			}
		}
		System.out.println("ti: "+utilitariosLocal.redondeoValor(ti));
		System.out.println("tr: "+utilitariosLocal.redondeoValor(tr));*/
		return detalleVentas;
	}
	
	private boolean verificarDetalleVenta(Map<String, Map<Integer,DetalleVentas>> mapaDetalleVentas, 
			DetalleVentas detalleVenta){
		String identificacionCliente = detalleVenta.getIdCliente();
		String tipoIdCliente = detalleVenta.getTpIdCliente();
		int tipoComprobante = detalleVenta.getTipoComprobante();
		Map<Integer,DetalleVentas> mapaPorTipoComprobante = mapaDetalleVentas.get(identificacionCliente);
		if ( mapaPorTipoComprobante != null ){
			DetalleVentas detalleVentaExistente = mapaPorTipoComprobante.get(tipoComprobante);
			if ( detalleVentaExistente != null &&
				 identificacionCliente.equals(detalleVentaExistente.getIdCliente()) &&
				 tipoIdCliente.equals(detalleVentaExistente.getTpIdCliente()) &&
				 tipoComprobante == detalleVentaExistente.getTipoComprobante()
			    ){
				acumularDetalleVenta(detalleVentaExistente, detalleVenta);
				return true;
			}
		}
		return false;
	}		
	
	private void acumularDetalleVenta(DetalleVentas detalleVentaA,DetalleVentas detalleVentaB){
		
		double  big = detalleVentaA.getBaseImpGrav().doubleValue() + detalleVentaB.getBaseImpGrav().doubleValue();
		detalleVentaA.setBaseImpGrav(utilitariosLocal.redondeoValor(new BigDecimal(big)));
		
		double bi = detalleVentaA.getBaseImponible().doubleValue() + detalleVentaB.getBaseImponible().doubleValue();
		detalleVentaA.setBaseImponible(utilitariosLocal.redondeoValor(new BigDecimal(bi)));
		
		double bngi = detalleVentaA.getBaseNoGraIva().doubleValue() + detalleVentaB.getBaseNoGraIva().doubleValue();
		detalleVentaA.setBaseNoGraIva(utilitariosLocal.redondeoValor(new BigDecimal(bngi)));
		
		int nc = detalleVentaA.getNumeroComprobantes() + detalleVentaB.getNumeroComprobantes();
		detalleVentaA.setNumeroComprobantes(nc);
		
		double mi = detalleVentaA.getMontoIva().doubleValue() + detalleVentaB.getMontoIva().doubleValue();
		detalleVentaA.setMontoIva(utilitariosLocal.redondeoValor(new BigDecimal(mi)));
		
		double vri = detalleVentaA.getValorRetIva().doubleValue() + detalleVentaB.getValorRetIva().doubleValue();
		detalleVentaA.setValorRetIva(utilitariosLocal.redondeoValor(new BigDecimal(vri)));
		
		double vrr = detalleVentaA.getValorRetRenta().doubleValue() + detalleVentaB.getValorRetRenta().doubleValue();
		detalleVentaA.setValorRetRenta(utilitariosLocal.redondeoValor(new BigDecimal(vrr)));
		
	}
	
	

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Object> generarNotasCreditoVenta(int numeroFila,Long idEmpresa,Map<String, Date> mapaQuery,HashMap<String, SriIvaServicioIf> mapaCodigoIvaServicio,HashMap<String, SriIvaBienIf> mapaCodigoIvaBien,HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion,
			HashMap<Long, SriTipoComprobanteIf> mapaCodigoTipoComprobantes,HashMap<String, String> mapaCodigoTipoIdentificacionVenta,HashMap<String, Double> mapaImpuestos,HashMap<Integer, Integer> mapaCodigoIva) throws GenericBusinessException{
		
		NumberFormat formatoDosDecimales = new DecimalFormat("0.00");
		
		//Map<String, BigDecimal> mapaAirValores = null;

		Collection<TipoDocumentoIf> tiposDocumento = tipoDocumentoLocal.findTipoDocumentoByCodigo("NCC");
		TipoDocumentoIf tipoDocumento = null;
		if ( tiposDocumento!= null && tiposDocumento.iterator().hasNext() )
			tipoDocumento = (TipoDocumentoIf) tiposDocumento.iterator().next();
		else
			throw new GenericBusinessException("Tipo de Documento con nombre: \"NOTA DE CREDITO PARA CLIENTE\" no existe" +
			"\n, tiene que tener c\u00f3digo : NCC");

		//SriTipoComprobanteIf tipoComprobanteIf = mapaCodigoTipoComprobantes.get(tipoDocumento.getIdSriTipoComprobante());
		SriTipoComprobanteIf tipoComprobanteIf = tipoComprobanteLocal.getSriTipoComprobante(tipoDocumento.getIdSriTipoComprobante());
		if (tipoComprobanteIf==null)
			throw new GenericBusinessException("Tipo de comprobante que tiene el Tipo de Documento con c\u00f3digo: "+tipoDocumento.getCodigo()+" no existe");


		Map<String,Object> mapaCartera = new HashMap<String,Object>();
		mapaCartera.put("tipo", "C");
		mapaCartera.put("estado", "N");
		mapaCartera.put("tipodocumentoId",tipoDocumento.getId());
		Collection<ClienteIf> clientes = carteraLocal.getClienteConCarterabyFechaInicio_FechaFin(
				idEmpresa, mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin"), mapaCartera);

		AsientoIf asientoIf = null;
		ArrayList<Object> detallesNotasCredito = new ArrayList<Object>();
		for (ClienteIf clienteIf:  clientes){
			double baseCero = 0.0, baseGravada = 0.0,iva=0.0;
			//double ivaBienes = 0.0, ivaServicio = 0.0;
			//double ivaServicioRetenido=0.0,ivaBienesRetenido=0.0;
			int numeroDocumentos = 0;
			//baseCero = 0.0; baseGravada = 0.0;iva=0.0;
			//ivaBienes = 0.0; ivaServicio = 0.0;
			//ivaServicioRetenido=0.0;//ivaBienesRetenido=0.0;

			TipoIdentificacionIf tipoIdentificacion = mapaCodigoIdentificacion.get(clienteIf.getTipoidentificacionId());
			if (tipoIdentificacion==null)
				throw new GenericBusinessException("Revisar tipo de Identificai\u00f3n de Cliente con identificac\u00f3n:"+clienteIf.getIdentificacion());

			//String[] preimpreso = null;
			numeroDocumentos = 0;
			try{
				Collection<CarteraIf> carteras = carteraLocal.getCarteraByDocumento_Cliente_FechaInicio_FechaFin(
						tipoDocumento.getCodigo(), clienteIf.getId(), mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin"), idEmpresa); 

				for (CarteraIf carteraIf: carteras){
					//baseCero = 0.0; baseGravada = 0.0; iva=0.0;
					//ivaBienes = 0.0;ivaBienesRetenido=0.0; ivaServicio = 0.0;ivaServicioRetenido=0.0;
					//ClienteOficinaIf clienteOficinaIf = clienteOficinaLocal.getClienteOficina(carteraIf.getClienteoficinaId());

					NotaCreditoIf notaCredito = notaCreditoLocal.getNotaCredito(carteraIf.getReferenciaId());
					//Collection<CarteraDetalleIf> carterasDetalle = carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraIf.getId()); 
					if ( notaCredito == null )
						throw new GenericBusinessException("Nota de Credito de Cartera con codigo "+carteraIf.getCodigo()+" no existe !!");
					
					Collection<NotaCreditoDetalleIf> notasCreditoDetalle = notaCreditoDetalleLocal.findNotaCreditoDetalleByNotaCreditoId(notaCredito.getId());
					
					//double porcentajeIva = mapaImpuestos.get("IVA").doubleValue();

					//for (CarteraDetalleIf carteraDetalle: carterasDetalle){
					for (NotaCreditoDetalleIf notaCreditoDetalle: notasCreditoDetalle){
						
						baseGravada += notaCreditoDetalle.getValor().doubleValue();
						iva += notaCreditoDetalle.getIva().doubleValue() ;		

						//ivaBienes = iva;

						//preimpreso = carteraDetalle.getPreimpreso().split("-");

						//if (preimpreso==null || preimpreso.length != 3)
						//	throw new GenericBusinessException("Revisar Preimpreso de Detalle de Cartera de Proveedores con c\u00f3digo: "+carteraIf.getCodigo());

					}
					numeroDocumentos++;
				}
				
				DetalleVentas detalleVenta = new DetalleVentas();

				String codigoTipoIdentificacionCliente = mapaCodigoTipoIdentificacionVenta.get(tipoIdentificacion.getCodigo());
				if ( codigoTipoIdentificacionCliente == null )
					throw new GenericBusinessException("Revisar Identificacion por Tipo de Documento: "+tipoDocumento.getNombre()+" en modulo SRI." +
							"\n No existe c\u00f3digo para Tipo de Identificaci\u00f3n: "+tipoIdentificacion.getNombre());
				detalleVenta.setTpIdCliente(codigoTipoIdentificacionCliente);

				String idCliente = clienteIf.getIdentificacion();
				detalleVenta.setIdCliente(idCliente);

				int idTipoComprobante = Integer.valueOf(tipoComprobanteIf.getCodigo());
				detalleVenta.setTipoComprobante(idTipoComprobante);

				//Calendar calendar = new GregorianCalendar();
				//java.util.Date fecha = calendar.getTime();
				//String fechaRegistroString = formatoFecha.format(fecha);
				//TIENE QUE SER LA FECHA DE FIN DE MES DEL CUAL SE ESTA DECLARANDO
				//String fechaRegistroString = formatoFecha.format(mapaQuery.get("fechaFin"));
				//detalleVenta.setFechaRegistro(fechaRegistroString);

				detalleVenta.setNumeroComprobantes(numeroDocumentos);

				//detalleVenta.setFechaEmision(fechaRegistroString);

				baseCero = utilitariosLocal.redondeoValor(baseCero);
				BigDecimal baseImponible = new BigDecimal(formatoDosDecimales.format(baseCero));
				detalleVenta.setBaseImponible(baseImponible);

				//detalleVenta.setIvaPresuntivo(IvaPresuntivoType.N);

				BigDecimal baseNoGravIva = BigDecimal.ZERO;
				detalleVenta.setBaseNoGraIva(baseNoGravIva);
				
				//BASE GRAVADA ES EL TOTAL, ASI ESTA GUARDADO EN LA BASE
				baseGravada = utilitariosLocal.redondeoValor(baseGravada);
				BigDecimal baseImponibleGravada = new BigDecimal(formatoDosDecimales.format(baseGravada));
				detalleVenta.setBaseImpGrav(baseImponibleGravada);

				//int codigoIva = mapaCodigoIva.get(mapaImpuestos.get("IVA").intValue());
				//detalleVenta.setPorcentajeIva(codigoIva);

				iva = utilitariosLocal.redondeoValor(iva);
				BigDecimal montoIva = new BigDecimal(formatoDosDecimales.format(iva));
				detalleVenta.setMontoIva(montoIva);

				detalleVenta.setValorRetRenta(BigDecimal.ZERO);
				
				detalleVenta.setValorRetIva(BigDecimal.ZERO);

				//BigDecimal baseImponibleIce = new BigDecimal(0.00);
				//detalleVenta.setBaseImpIce(baseImponibleIce);

				//int codigoIce = 0;
				//detalleVenta.setPorcentajeIce(codigoIce);

				//BigDecimal montoIce = new BigDecimal(0.00);
				//detalleVenta.setMontoIce(montoIce);

				//ivaBienes = utilitariosLocal.redondeoValor(BigDecimal.valueOf(ivaBienes)).doubleValue();
				//BigDecimal montoIvaBienes = new BigDecimal(formatoDosDecimales.format(ivaBienes));
				//detalleVenta.setMontoIvaBienes(montoIvaBienes);
				
				//int codigoIvaBien = retencionIvaBien!=null?retencionIvaBien.getCodigo():0;
				//detalleVenta.setPorRetBienes(0);

				//ivaBienesRetenido = utilitariosLocal.redondeoValor(BigDecimal.valueOf(ivaBienesRetenido)).doubleValue();
				//BigDecimal valorIvaRetBienes = new BigDecimal(formatoDosDecimales.format(ivaBienesRetenido));
				//detalleVenta.setValorRetBienes(valorIvaRetBienes);
				
				//ivaServicio = utilitariosLocal.redondeoValor(BigDecimal.valueOf(ivaServicio)).doubleValue();
				//BigDecimal montoIvaServicio = new BigDecimal(formatoDosDecimales.format(ivaServicio));
				//detalleVenta.setMontoIvaServicios(montoIvaServicio);

				//int codigoIvaServicio = retencionIvaServicio!=null?retencionIvaServicio.getCodigo():0;
				//detalleVenta.setPorRetServicios(0);

				//ivaServicioRetenido = utilitariosLocal.redondeoValor(BigDecimal.valueOf(ivaServicioRetenido)).doubleValue();
				//BigDecimal valorIvaRetServicio = new BigDecimal(formatoDosDecimales.format(ivaServicioRetenido));
				//detalleVenta.setValorRetServicios(valorIvaRetServicio);

				//detalleVenta.setRetPresuntiva(IvaPresuntivoType.N);

				//Air air = new Air();
				//detalleVenta.setAir(air);
				
				detallesNotasCredito.add(detalleVenta);
				numeroFila++;

			} catch(Exception e){
				if (e instanceof GenericBusinessException)
					detallesNotasCredito.add("\t"+e.getMessage()+"\n");
				else{
					e.printStackTrace();
					detallesNotasCredito.add("\t"+numeroFila+".- "+e.getMessage()+" de cliente con identificaci\u00f3n: "
							+clienteIf.getIdentificacion()+"\n");
				}
			}

		}
		return detallesNotasCredito;
		
		//return null;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Object> generarDetallesAnulaciones(int numeroFila,Long idEmpresa,Map<String, Date> mapaQuery,String codigoDocumento,HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion,
			HashMap<Long, SriTipoComprobanteIf> mapaCodigoTipoComprobantes) throws GenericBusinessException{
		
		Collection<TipoDocumentoIf> tiposDocumento = tipoDocumentoLocal.findTipoDocumentoByCodigo(codigoDocumento);
		TipoDocumentoIf tipoDocumento = null;

		if ( tiposDocumento!= null && tiposDocumento.iterator().hasNext() )
			tipoDocumento = (TipoDocumentoIf) tiposDocumento.iterator().next();
		else
			throw new GenericBusinessException("Tipo de Documento con c\u00f3digo: \""+codigoDocumento+"\" no existe");

		Map<String, Object> mapaFacturas = new HashMap<String, Object>();
		mapaFacturas.put("codigoDocumento", codigoDocumento);
		mapaFacturas.put("estado", "A");

		ArrayList<Object> detalleAnulados = new ArrayList<Object>();

		SriTipoComprobanteIf tipoComprobante = mapaCodigoTipoComprobantes.get(tipoDocumento.getIdSriTipoComprobante());
		if (tipoComprobante==null)
			throw new GenericBusinessException("Tipo de comprobante que tiene el Tipo de Documento con c\u00f3digo: "+tipoDocumento.getCodigo()+" no existe");
		
		
		Collection<FacturaIf> facturas = facturaLocal.getFacturaByMap_FechaInicio_FechaFin(
				mapaFacturas, mapaQuery.get("fechaInicio"), mapaQuery.get("fechaFin"),idEmpresa);
		for (FacturaIf factura : facturas){
			
			try{
				//FacturaIf factura = (FacturaIf) itFacturas.next();
				DetalleAnulados detalleAnulado = new DetalleAnulados();
				String preimpreso = factura.getPreimpresoNumero();
				String preimpresoSplit[] = preimpreso.split("-"); 
				if ( preimpresoSplit.length!=3 )
					throw new GenericBusinessException("Revisar Preimpreso (formato: ###-###-#######) de la Factura con c\u00f3digo: "+factura.getNumero());
				
				String establecimiento = preimpresoSplit[0]; 
				String puntoEmision = preimpresoSplit[1];
				String secuencial = preimpresoSplit[2];
				
				int idTipoComprobante = Integer.valueOf(tipoComprobante.getCodigo());
				detalleAnulado.setTipoComprobante(idTipoComprobante);
				detalleAnulado.setEstablecimiento(establecimiento);
				detalleAnulado.setPuntoEmision(puntoEmision);
				detalleAnulado.setSecuencialInicio(Integer.parseInt(secuencial));;
				detalleAnulado.setSecuencialFin(Integer.parseInt(secuencial));;
				
				detalleAnulado.setAutorizacion(null);
				//detalleAnulado.setFechaAnulacion(null);
				
				detalleAnulados.add(detalleAnulado);

			} catch(Exception e){
				if (e instanceof GenericBusinessException)
					detalleAnulados.add("\t"+e.getMessage()+"\n");
				else{
					detalleAnulados.add("\t"+numeroFila+".- "+e.getMessage()+" de cliente con identificaci\u00f3n: "+"\n");
					e.printStackTrace();
				}
			}
			numeroFila++;
		}
		return detalleAnulados;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Object> generarDetalleAnulacionesCartera(int numeroFila,Long idEmpresa,Map<String, Date> mapaFecha,
			String codigoTipoDocumento,HashMap<Long, SriTipoComprobanteIf> mapaCodigoTipoComprobantes) throws GenericBusinessException{
		
		Collection<TipoDocumentoIf> tiposDocumento = tipoDocumentoLocal.findTipoDocumentoByCodigo(codigoTipoDocumento);
		TipoDocumentoIf tipoDocumento = null;
		if ( tiposDocumento!= null && tiposDocumento.iterator().hasNext() )
			tipoDocumento = (TipoDocumentoIf) tiposDocumento.iterator().next();
		else
			throw new GenericBusinessException("Tipo de Documento con c\u00f3digo "+codigoTipoDocumento+" no existe" );

		TipoParametroIf tp = utilitariosLocal.getTipoParametro(idEmpresa,DimmConstantes.TIPO_PARAMETRO_DIMM);
		
		Collection<ParametroEmpresaIf> parametros = utilitariosLocal.getParametroEmpresa(idEmpresa,tp,false,DimmConstantes.AUTORIZACION_FACTURAS);
		
		if ( parametros.size() == 0 )
			throw new GenericBusinessException("Parametro de empresa con valor \""+DimmConstantes.AUTORIZACION_FACTURAS+"\" no existe !!");
		//else if ( parametros.size() > 1 )
		//	throw new GenericBusinessException("Existe mas de una parametro de Empresa llamado "+UtilesDimm.AUTORIZACION_FACTURAS+" !!");
		Set<DatosPreimpresoFactura> datosPreimpresos = new HashSet<DatosPreimpresoFactura>();
		for ( ParametroEmpresaIf peAutorizacionFacturas : parametros ){
			DatosPreimpresoFactura dpf = new DatosPreimpresoFactura();
			//ParametroEmpresaIf peAutorizacionFacturas = parametros.iterator().next();
			if ( peAutorizacionFacturas == null )
				throw new GenericBusinessException("No existe Parametro de Empresa "+DimmConstantes.AUTORIZACION_FACTURAS);
			if ( peAutorizacionFacturas.getValor() == null )
				throw new GenericBusinessException("Valor no establecido para Parametro de Empresa "+DimmConstantes.AUTORIZACION_FACTURAS);
			
			String[] datos = peAutorizacionFacturas.getValor().trim().split(",");
			if ( datos.length != 3 ){
				throw new GenericBusinessException("Datos de Autorizacion deben estar separados por ,(coma) !!" +
						"\n Ej: 000-000-000,000-000-000,0000000");
			}
			String secuencialInicio = datos[0];
			String[] datosSecuencial = secuencialInicio.trim().split("-");
			if ( datosSecuencial.length != 3 ){
				throw new GenericBusinessException("Datos de Secuencial Inicio deben estar separados por -(guion) !!" +
						"\n Ej: 000-000-000");
			}
			int secuencialInicioInt = 0;
			try{
				secuencialInicioInt = Integer.valueOf(datosSecuencial[2]);
			} catch(Exception exInteger){
				throw new GenericBusinessException("Error en la conversion de Secuencial Inicio !!");
			}
			dpf.setSecuencialInicial(datosSecuencial[2]);
			dpf.setSecuencialInicialInt(secuencialInicioInt);
			
			String secuencialFin = datos[1];
			datosSecuencial = secuencialFin.trim().split("-");
			if ( datosSecuencial.length != 3 ){
				throw new GenericBusinessException("Datos de Secuencial Fin deben estar separados por -(guion) !!" +
						"\n Ej: 000-000-000");
			}
			int secuencialFinInt = 0;
			try{
				secuencialFinInt = Integer.valueOf(datosSecuencial[2]);
			} catch(Exception exInteger){
				throw new GenericBusinessException("Error en la conversion de Secuencial Fin !!");
			}
			if ( secuencialFinInt < secuencialInicioInt )
				throw new GenericBusinessException("Secuencial Fin tiene que ser mayor que Secuencial Inicio en Parametro de Empresa !!");
			dpf.setSecuencialFinal(datosSecuencial[2]);
			dpf.setSecuencialFinalInt(secuencialFinInt);
			

			String autorizacionFactura = datos[2];
			try{
				int autorizacionInt = Integer.valueOf(autorizacionFactura);
			} catch(Exception exInteger){
				throw new GenericBusinessException("Error en la conversion de Autorizacion !!");
			}
			
			dpf.setAurtorizacion(autorizacionFactura);
			
			datosPreimpresos.add(dpf);
			
		}
		
		ArrayList<Object> detalleAnulados = new ArrayList<Object>();

		SriTipoComprobanteIf tipoComprobante = mapaCodigoTipoComprobantes.get(tipoDocumento.getIdSriTipoComprobante());
		if ( tipoComprobante.getAnulacionTipoComprobanteId() != null ){
			tipoComprobante = mapaCodigoTipoComprobantes.get(tipoComprobante.getAnulacionTipoComprobanteId());
		}
		if (tipoComprobante==null)
			throw new GenericBusinessException("Tipo de comprobante que tiene el Tipo de Documento con c\u00f3digo: "+tipoDocumento.getCodigo()+" no existe");
		
		LogCarteraIf carteraTmp = null;

		Map<Integer, DetalleAnulados> mapaAnulados =  new HashMap<Integer, DetalleAnulados>();
		if ( "CRE".equalsIgnoreCase(codigoTipoDocumento) ){
			Collection<LogCompraRetencionIf> retenciones = logCompraRetencionLocal.getLogCompraRetencionByDocumento_Cliente_FechaInicio_FechaFin(
					tipoDocumento.getCodigo(), null, mapaFecha.get("fechaInicio"), mapaFecha.get("fechaFin"), idEmpresa);
			
			for ( LogCompraRetencionIf lcr : retenciones ){
				if ( lcr.getEstablecimiento().length() == 3 && lcr.getPuntoEmision().length() == 3 && lcr.getSecuencial().length() >= 3 ){
					String[] preimpreso = new String[]{lcr.getEstablecimiento(),lcr.getPuntoEmision(),lcr.getSecuencial()};
					agregarDetalleAnulado(detalleAnulados,mapaAnulados,tipoComprobante,preimpreso,lcr.getAutorizacion());
				}
			}
		} else {
			Collection<LogCarteraIf> carteras = logCarteraLocal.getLogCarteraByDocumento_Cliente_FechaInicio_FechaFin(
				tipoDocumento.getCodigo(), null, mapaFecha.get("fechaInicio"), mapaFecha.get("fechaFin"), idEmpresa);
			
			for (LogCarteraIf carteraIf: carteras){
				try{

					carteraTmp = carteraIf;
					String preimpreso = carteraIf.getPreimpreso();
					Set<String> setPreimpreso = new HashSet<String>();
					if ( preimpreso == null ){
						Collection<LogCarteraDetalleIf> detalles = logCarteraDetalleLocal.findLogCarteraDetalleByLogCarteraId(carteraIf.getId());
						for ( LogCarteraDetalleIf lcd : detalles ){
							setPreimpreso.add(lcd.getPreimpreso());
						}
						if ( setPreimpreso.size() == 0 )
							throw new GenericBusinessException("Revisar Preimpreso de Cartera con c\u00f3digo: "+carteraIf.getCodigo());
					}
					
					if ( preimpreso == null ){
						for ( String preimpresoSet : setPreimpreso ){
							String preimpresoSplit[] = preimpresoSet.split("-");
							if ( preimpresoSplit.length!=3 )
								throw new GenericBusinessException("Revisar Preimpreso (formato: ###-###-#######) de la Cartera con c\u00f3digo: "+carteraIf.getCodigo());
							String secuencial = preimpresoSplit[2];
							//String autorizacion = "000";
							int aurotizacionInt = Integer.parseInt(secuencial);
							String autorizacion = "000";
							//if ( aurotizacionInt >= 26301 && aurotizacionInt <= 29300 )
							for(DatosPreimpresoFactura dpf : datosPreimpresos){
								if ( aurotizacionInt >= dpf.getSecuencialInicialInt() && aurotizacionInt <= dpf.getSecuencialFinalInt() )
									autorizacion = dpf.getAurtorizacion();
							}
							agregarDetalleAnulado(detalleAnulados,mapaAnulados,tipoComprobante,preimpresoSplit,autorizacion);
						}
					} else {
						String preimpresoSplit[] = preimpreso.split("-"); 
						if ( preimpresoSplit.length!=3 )
							throw new GenericBusinessException("Revisar Preimpreso (formato: ###-###-#######) de la Cartera con c\u00f3digo: "+carteraIf.getCodigo());
						String secuencial = preimpresoSplit[2];
						//String autorizacion = "000";
						int aurotizacionInt = Integer.parseInt(secuencial);
						String autorizacion = "000";
						//if ( aurotizacionInt >= 26301 && aurotizacionInt <= 29300 )
						for(DatosPreimpresoFactura dpf : datosPreimpresos){
							if ( aurotizacionInt >= dpf.getSecuencialInicialInt() && aurotizacionInt <= dpf.getSecuencialFinalInt() )
								autorizacion = dpf.getAurtorizacion();
								//autorizacion = "1106400941";
						}
						agregarDetalleAnulado(detalleAnulados,mapaAnulados,tipoComprobante,preimpresoSplit,autorizacion);
					}

					//Collection<CarteraDetalleIf> carterasDetalle = carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraIf.getId()); 
					//for (CarteraDetalleIf carteraDetalle: carterasDetalle){
					//}
				} catch(Exception e){
					if (e instanceof GenericBusinessException)
						detalleAnulados.add("\t"+e.getMessage()+"\n");
					else{
						e.printStackTrace();
						detalleAnulados.add("\t"+numeroFila+".- "+e.getMessage()+" de documento de cartera "+tipoDocumento.getNombre()+
								(carteraTmp!= null?"con c\u00f3digo "+carteraTmp.getCodigo():"")+"\n");
					}
				}
				numeroFila++;

			}
		}

		return detalleAnulados;
	}

	private void agregarDetalleAnulado(ArrayList<Object> detalleAnulados,Map<Integer, DetalleAnulados> mapaAnulados,
			SriTipoComprobanteIf tipoComprobante, String[] preimpresoSplit,String autorizacion) {
		String establecimiento = preimpresoSplit[0];
		String puntoEmision = preimpresoSplit[1];
		String secuencial = preimpresoSplit[2];
		int iSecuencial = Integer.parseInt(secuencial);
		int idTipoComprobante = Integer.valueOf(tipoComprobante.getCodigo());
		
		boolean existeDetalleAnulado = verificarDetalleAnulado(mapaAnulados, establecimiento, puntoEmision, iSecuencial, idTipoComprobante); 
		if ( !existeDetalleAnulado ){
			
			DetalleAnulados detalleAnulado = new DetalleAnulados();
			detalleAnulado.setTipoComprobante(idTipoComprobante);
			detalleAnulado.setEstablecimiento(establecimiento);
			detalleAnulado.setPuntoEmision(puntoEmision);
			detalleAnulado.setAutorizacion(autorizacion);
			detalleAnulado.setSecuencialInicio(iSecuencial);
			detalleAnulado.setSecuencialFin(iSecuencial);
			
			mapaAnulados.put(iSecuencial, detalleAnulado);
			detalleAnulados.add(detalleAnulado);
		}
	}
	
	private boolean verificarDetalleAnulado(Map<Integer, DetalleAnulados> mapaAnulados, 
			String establecimiento,String puntoEmision ,int secuencial, int idTipoComprobante){
		
		DetalleAnulados da = mapaAnulados.get(secuencial); 
		if ( da != null ){
			if (
					da.getEstablecimiento().equals(establecimiento) &&
					da.getPuntoEmision().equals(puntoEmision) &&
					da.getSecuencialInicio() == secuencial &&
					da.getTipoComprobante() == idTipoComprobante
			   ){
				return true;
			}
		}
		return false;
	}
	
	private void cargarCodigosTipoIdentificacion(String nombreTransaccion
			,Map<String, String> mapaCodigoTipoIdentificacionXCodigoSpirit) throws GenericBusinessException{
		
		Map<String, SriTipoTransaccionIf> mapaCodigoTransaccion = cargarCodigoTipoTransaccion();
		SriTipoTransaccionIf transaccion = mapaCodigoTransaccion.get(nombreTransaccion);
		Map<Long, TipoIdentificacionIf> mapaCodigoIdentificacion = cargarTipoIdentificacion();
		
		if(transaccion!=null){
			//HashMap<String, String> mapaCodigoTipoIdentificacionXCodigoSpirit = new HashMap<String, String>();
			//HashMap<String, String> mapaCodigoTipoIdentificacionXCodigoSri = new HashMap<String, String>();
			try {
				Collection<SriIdentifTransaccionIf> tipos = identifTransaccionLocal.findSriIdentifTransaccionByIdTipoTransaccion(transaccion.getId());
				for (SriIdentifTransaccionIf identificacionTransaccion : tipos){
					TipoIdentificacionIf tipoIdentificacion = mapaCodigoIdentificacion.get(identificacionTransaccion.getIdTipoIdentificacion());
					mapaCodigoTipoIdentificacionXCodigoSpirit.put(tipoIdentificacion.getCodigo(), identificacionTransaccion.getCodigo());
					//mapaCodigoTipoIdentificacionXCodigoSri.put(identificacionTransaccion.getCodigo(), tipoIdentificacion.getNombre());
				}
				//Object[] mapas = new Object[]{mapaCodigoTipoIdentificacionXCodigoSpirit,mapaCodigoTipoIdentificacionXCodigoSri};
				//return mapas;
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				throw new GenericBusinessException("No se puede cargar los codigos de Identificaciones para ");
			}

		} else
			throw new GenericBusinessException("No existe Tipo de Transacci\u00f3n \""+nombreTransaccion+"\"");
		
	}
	
}

class PapeletaRetencion implements Serializable{
	
	private static final long serialVersionUID = 7110803129555679761L;
	
	private String establecimiento = "";
	private String ptoImpresion = "";
	private String secuencial = "";
	private String autorizacion = "";
	private java.util.Date fechaEmision = null;
	
	public PapeletaRetencion() {
	}
	
	public PapeletaRetencion(String est,String pto,String sec,String aut,java.util.Date fechaEmision ){
		this.establecimiento = est;
		this.ptoImpresion = pto;
		this.secuencial = sec;
		this.autorizacion = aut;
		this.fechaEmision = fechaEmision;
	}
	
	public boolean equals(Object arg0) {
		if ( arg0 instanceof PapeletaRetencion ){
			PapeletaRetencion p = (PapeletaRetencion)arg0;
			return ( establecimiento == p.getEstablecimiento() &&
					ptoImpresion == p.getPtoImpresion() &&
					secuencial == p.getSecuencial() );
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 1;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String getPtoImpresion() {
		return ptoImpresion;
	}

	public void setPtoImpresion(String ptoImpresion) {
		this.ptoImpresion = ptoImpresion;
	}

	public String getSecuencial() {
		return secuencial;
	}

	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}

	public String getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public java.util.Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(java.util.Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	
	
	
}
