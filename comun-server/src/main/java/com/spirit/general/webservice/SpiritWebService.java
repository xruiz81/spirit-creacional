package com.spirit.general.webservice;

import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.apache.xerces.dom.ElementNSImpl;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.spirit.cartera.entity.FormaPagoIf;
import com.spirit.cartera.session.FormaPagoSessionLocal;
import com.spirit.crm.entity.ClienteData;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaData;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.ClienteRetencionIf;
import com.spirit.crm.entity.ClienteWebEJB;
import com.spirit.crm.entity.ClienteWebIf;
import com.spirit.crm.entity.ClienteZonaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.session.ClienteOficinaSessionLocal;
import com.spirit.crm.session.ClienteSessionLocal;
import com.spirit.crm.session.ClienteWebSessionLocal;
import com.spirit.crm.session.CorporacionSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.ListaPrecioIf;
import com.spirit.facturacion.entity.PedidoData;
import com.spirit.facturacion.entity.PedidoDetalleData;
import com.spirit.facturacion.entity.PedidoDetalleIf;
import com.spirit.facturacion.entity.PedidoDetallePersonalizacionEJB;
import com.spirit.facturacion.entity.PedidoDetallePersonalizacionIf;
import com.spirit.facturacion.entity.PedidoEnvioData;
import com.spirit.facturacion.entity.PedidoEnvioDetalleData;
import com.spirit.facturacion.entity.PedidoEnvioDetalleIf;
import com.spirit.facturacion.entity.PedidoEnvioIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.entity.PersonalizacionColorIf;
import com.spirit.facturacion.entity.PersonalizacionDisenioIf;
import com.spirit.facturacion.entity.PersonalizacionLogoDisenioIf;
import com.spirit.facturacion.entity.PersonalizacionTamanioIf;
import com.spirit.facturacion.entity.PersonalizacionTipoImpresionIf;
import com.spirit.facturacion.entity.PersonalizacionTipoLetraIf;
import com.spirit.facturacion.entity.PersonalizacionTipoPersonalizacionIf;
import com.spirit.facturacion.entity.PersonalizacionUbicacionIf;
import com.spirit.facturacion.session.ListaPrecioSessionLocal;
import com.spirit.facturacion.session.PedidoDetallePersonalizacionSessionLocal;
import com.spirit.facturacion.session.PedidoDetalleSessionLocal;
import com.spirit.facturacion.session.PedidoEnvioDetalleSessionLocal;
import com.spirit.facturacion.session.PedidoEnvioSessionLocal;
import com.spirit.facturacion.session.PedidoSessionLocal;
import com.spirit.facturacion.session.PersonalizacionColorSessionLocal;
import com.spirit.facturacion.session.PersonalizacionDisenioSessionLocal;
import com.spirit.facturacion.session.PersonalizacionLogoDisenioSessionLocal;
import com.spirit.facturacion.session.PersonalizacionTamanioSessionLocal;
import com.spirit.facturacion.session.PersonalizacionTipoImpresionSessionLocal;
import com.spirit.facturacion.session.PersonalizacionTipoLetraSessionLocal;
import com.spirit.facturacion.session.PersonalizacionTipoPersonalizacionSessionLocal;
import com.spirit.facturacion.session.PersonalizacionUbicacionSessionLocal;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.OrigenDocumentoIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.PuntoImpresionIf;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.entity.UsuarioPuntoImpresionIf;
import com.spirit.general.session.CiudadSessionRemote;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.EmpleadoSessionLocal;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.MonedaSessionLocal;
import com.spirit.general.session.OficinaSessionLocal;
import com.spirit.general.session.OrigenDocumentoSessionLocal;
import com.spirit.general.session.ParametroEmpresaSessionLocal;
import com.spirit.general.session.PuntoImpresionSessionLocal;
import com.spirit.general.session.TipoClienteSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.TipoIdentificacionSessionLocal;
import com.spirit.general.session.TipoParametroSessionLocal;
import com.spirit.general.session.UsuarioPuntoImpresionSessionLocal;
import com.spirit.general.session.UsuarioSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.GiftcardEJB;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.MovimientoDetalleIf;
import com.spirit.inventario.entity.MovimientoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.BodegaSessionLocal;
import com.spirit.inventario.session.FuncionBodegaSessionLocal;
import com.spirit.inventario.session.GenericoSessionLocal;
import com.spirit.inventario.session.GiftcardSessionLocal;
import com.spirit.inventario.session.LoteSessionLocal;
import com.spirit.inventario.session.MovimientoSessionLocal;
import com.spirit.inventario.session.ProductoSessionLocal;
import com.spirit.inventario.session.StockSessionLocal;
import com.spirit.inventario.session.TipoProductoSessionLocal;
import com.spirit.pos.entity.TarjetaEJB;
import com.spirit.pos.entity.TarjetaIf;
import com.spirit.pos.session.TarjetaSessionLocal;

@Stateless
@WebService(serviceName="SpiritWebService",endpointInterface="com.spirit.general.webservice.SpiritWebServiceRemote")

public class SpiritWebService implements SpiritWebServiceLocal,SpiritWebServiceRemote{

	@EJB private StockSessionLocal stockSessionLocal;

	@EJB private ClienteSessionLocal clienteLocal;

	@EJB private ClienteOficinaSessionLocal clienteOficinaLocal;

	@EJB private TipoDocumentoSessionLocal tipoDocumentoLocal;
	
	@EJB private DocumentoSessionLocal documentoLocal;

	@EJB private TipoClienteSessionLocal tipoClienteLocal; 
	
	@EJB private TipoIdentificacionSessionLocal tipoIdentificacionLocal; 

	@EJB private FormaPagoSessionLocal formaPagoLocal;
	
	@EJB private CorporacionSessionLocal corporacionLocal;

	@EJB private MonedaSessionLocal monedaLocal;

	@EJB private CiudadSessionRemote ciudadLocal;
	
	@EJB private UsuarioSessionLocal usuarioLocal;

	@EJB private UsuarioPuntoImpresionSessionLocal usuarioPuntoImpresionLocal;

	@EJB private PuntoImpresionSessionLocal puntoImpresionLocal; 

	@EJB private OrigenDocumentoSessionLocal origenDocumentoLocal;

	@EJB private BodegaSessionLocal bodegaLocal;
	
	@EJB private FuncionBodegaSessionLocal funcionBodegaLocal;

	@EJB private ListaPrecioSessionLocal listaPrecioLocal;

	@EJB private PedidoSessionLocal pedidoLocal;

	@EJB private PedidoDetalleSessionLocal pedidoDetalleLocal; 
	
	@EJB private PedidoEnvioSessionLocal pedidoEnvioLocal;
	
	@EJB private PedidoEnvioDetalleSessionLocal pedidoEnvioDetalleLocal;
	
	@EJB private EmpleadoSessionLocal empleadoLocal;

	@EJB private GenericoSessionLocal genericoLocal;
	
	@EJB private TipoProductoSessionLocal tipoProductoLocal;
	
	@EJB private ProductoSessionLocal productoLocal;
	
	@EJB private LoteSessionLocal loteLocal;
	
	@EJB private EmpresaSessionLocal empresaLocal;
	
	@EJB private OficinaSessionLocal oficinaLocal;
	
	@EJB private TarjetaSessionLocal tarjetaLocal;
	
	@EJB private MovimientoSessionLocal movimientoLocal;
	
	@EJB private PersonalizacionTipoPersonalizacionSessionLocal personalizacionTipoPersonalizacionLocal;
	
	@EJB private PersonalizacionTipoImpresionSessionLocal  personalizacionTipoImpresionLocal;
	
	@EJB private PersonalizacionTamanioSessionLocal personalizacionTamanioLocal;
	
	@EJB private PersonalizacionColorSessionLocal personalizacionColorLocal;
	
	@EJB private PersonalizacionUbicacionSessionLocal personalizacionUbicacionLocal;
	
	@EJB private PersonalizacionTipoLetraSessionLocal personalizacionTipoLetraLocal;
	
	@EJB private PersonalizacionDisenioSessionLocal personalizacionDisenioLocal;
	
	@EJB private PersonalizacionLogoDisenioSessionLocal personalizacionLogoDisenioLocal;

	@EJB private PedidoDetallePersonalizacionSessionLocal pedidoDetallePersonalizacionLocal;
	
	@EJB private ClienteWebSessionLocal clienteWebLocal;
	
	@EJB private GiftcardSessionLocal giftCardLocal;
	
	@EJB private TipoParametroSessionLocal tipoParametroLocal;
	
	@EJB private ParametroEmpresaSessionLocal parametroEmpresaLocal;
	
	@EJB private UtilitariosSessionLocal utilitariosLocal;

	static Map<String, ProductoIf> mapaProductos = new HashMap<String, ProductoIf>();
	static Map<String, String> mapaProductoDescripcion = new HashMap<String, String>();
	
	
	public Object[][] getStockModificado(int intervaloMinutos,boolean obtenerTodos) {

		Object[][] listaStock = new Object[][]{};
		System.out.println(" ----------- INICIO DE CONSULTA DE INVENTARIO POR INTERNET ------------- ");
		try{	
			Collection<TipoParametroIf> tiposParametros = tipoParametroLocal.findTipoParametroByCodigo("COMPINTERN");
			if ( tiposParametros.size() == 0 )
				throw new GenericBusinessException("No se encuentra Tipo de Parametro con código \"COMPINTERN\"");
			TipoParametroIf tipoParametroIf = tiposParametros.iterator().next();
			
			Map<String, Object> mapa =  new HashMap<String, Object>();
			mapa.put("tipoparametroId", tipoParametroIf.getId());
			mapa.put("codigo", "BODEGAINTERNET");
			Collection<ParametroEmpresaIf> parametros = 
				parametroEmpresaLocal.findParametroEmpresaByQuery(mapa);
			if ( parametros.size() == 0 )
				throw new GenericBusinessException("No se encontró parametro de empresa con codigo BODEGAINTERNET ");
			ParametroEmpresaIf parametroEmpresaIf = parametros.iterator().next();
			
			
			
			List consultaStock = stockSessionLocal
			.getStockModificado(intervaloMinutos,obtenerTodos,parametroEmpresaIf.getValor());
			if (consultaStock.size() > 0) {
				listaStock = new Object[consultaStock.size()][2];
			} else {
				return listaStock;
			}
	
			Object[] temp = null;
			for (int i = 0; i < consultaStock.size(); i++) {
				temp = (Object[]) consultaStock.get(i);
				listaStock[i][0] = temp[0];
				listaStock[i][1] = temp[1];
			}
			System.out.println(" ----------- FIN DE CONSULTA DE INVENTARIO POR INTERNET ------------- ");
		} catch (GenericBusinessException e){
			
		}
		return listaStock;
		
	}

	public synchronized boolean ingresarCompraPorInternet(Object[][] pedidos) {

		System.out.println(" ----------- INICIO DE COMPRAS POR INTERNET ------------- ");
		try{
			ProcesarOrdenPorInternet:
			for (int i = 0; i < pedidos.length ; i++ ){
				Object pedidoObjeto = pedidos[i];
	
				System.out.println("*** ARREGLO: "+i);
				if ( pedidoObjeto instanceof Object[] ){
	
					Object[] pedido = (Object[]) pedidoObjeto;
					System.out.println("  Tamanio Interno: "+pedido.length);
					try{
						int contador = 0;
						
						Object o = pedido[contador];						//0
						String valorString = extraerValorDeNodo(o);
						String codigoEmpresa = valorString;
						//System.out.println(contador +") "+codigoEmpresa);
						contador++;
						
						o = pedido[contador];								//1
						valorString = extraerValorDeNodo(o);
						BigDecimal idOrden = new BigDecimal(Long.valueOf(valorString));
						//System.out.println(contador +") "+idOrden);
						contador++;
						
						o = pedido[contador];								//2
						valorString = extraerValorDeNodo(o);
						String estado = valorString;
						//System.out.println(contador +") "+estado);
						contador++;
						
						o = pedido[contador];								//3
						valorString = extraerValorDeNodo(o);
						String fechaCreacionS = valorString;
						java.sql.Timestamp fechaCreacion = new java.sql.Timestamp(convertirFecha(fechaCreacionS).getTime());
						//System.out.println(contador +") "+fechaCreacionS);
						contador++;
						
						o = pedido[contador];								//4
						valorString = extraerValorDeNodo(o);
						String fechaActualizacionS = valorString;
						String fechaActualizacion = fechaActualizacionS;
						//System.out.println(contador +") "+fechaActualizacionS);
						contador++;
						
						o = pedido[contador];								//5
						valorString = extraerValorDeNodo(o);
						Long Activa = Long.valueOf(valorString);
						//System.out.println(contador +") "+Activa);
						contador++;
						
						o = pedido[contador];								//6
						valorString = extraerValorDeNodo(o);
						BigDecimal idCliente = new BigDecimal(Long.valueOf(valorString));
						//System.out.println(contador +") "+idCliente);
						contador++;
						
						o = pedido[contador];								//7
						valorString = extraerValorDeNodo(o);
						String correoCliente = valorString;
						//System.out.println(contador +") "+correo);
						contador++;
						
						o = pedido[contador];								//8
						valorString = extraerValorDeNodo(o);
						String nombresCliente = valorString;
						//System.out.println(contador +") "+nombresCliente);
						contador++;
						
						o = pedido[contador];								//9
						valorString = extraerValorDeNodo(o);
						String apellidosCliente = valorString;
						//System.out.println(contador +") "+apellidosCliente);
						contador++;
						
						o = pedido[contador];								//10
						valorString = extraerValorDeNodo(o);
						BigDecimal valorImpuesto = new BigDecimal(Double.valueOf(valorString));
						//System.out.println(contador +") "+valorImpuesto);
						contador++;
						
						o = pedido[contador];								//11
						valorString = extraerValorDeNodo(o);
						BigDecimal valorEnvio = new BigDecimal(
								!"".equals(valorString)?Double.valueOf(valorString):0D);
						//System.out.println(contador +") "+valorEnvio);
						contador++;
						
						o = pedido[contador];								//12
						valorString = extraerValorDeNodo(o);
						BigDecimal valorDescuento = new BigDecimal(
								!"".equals(valorString)?Double.valueOf(valorString):0D);
						//System.out.println(contador +") "+valorDescuento);
						contador++;
						
						o = pedido[contador];								//13
						valorString = extraerValorDeNodo(o);
						BigDecimal valorSubtotal = new BigDecimal(
								!"".equals(valorString)?Double.valueOf(valorString):0D);
						//System.out.println(contador +") "+valorSubtotal);
						contador++;
						
						o = pedido[contador];								//14
						valorString = extraerValorDeNodo(o);
						BigDecimal valorTotal = new BigDecimal(
								!"".equals(valorString)?Double.valueOf(valorString):0D);
						//System.out.println(contador +") "+valorTotal);
						contador++;
	
						
						o = pedido[contador];								//15
						valorString = extraerValorDeNodo(o);
						String codigoTipoPago = valorString;
						//System.out.println(contador +") "+codigoTipoPago);
						contador++;
						
						o = pedido[contador];								//16
						valorString = extraerValorDeNodo(o);
						String metodoEnvio = valorString;
						//System.out.println(contador +") "+metodoEnvio);
						contador++;
						
						o = pedido[contador];								//17
						valorString = extraerValorDeNodo(o);
						String identificacionFundacionDonacion = valorString;
						//System.out.println(contador +") "+identificacionFundacionDonacion);
						contador++;

						
						
						o = pedido[contador];								//18
						valorString = extraerValorDeNodo(o);
						String nombresFacturacion = valorString;
						//System.out.println(contador +") "+nombresFacturacion);
						contador++;
						
						o = pedido[contador];								//19
						valorString = extraerValorDeNodo(o);
						String apellidosFacturacion = valorString;
						//System.out.println(contador +") "+apellidosFacturacion);
						contador++;
						
						o = pedido[contador];								//20
						valorString = extraerValorDeNodo(o);
						String nombreEmpresaFacturacion = valorString;
						//System.out.println(contador +") "+nombreEmpresaFacturacion);
						contador++;
						
						o = pedido[contador];								//21
						valorString = extraerValorDeNodo(o);
						String direccionFacturacion = valorString;
						//System.out.println(contador +") "+direccionFacturacion);
						contador++;
						
						o = pedido[contador];								//22
						valorString = extraerValorDeNodo(o);
						String ciudadFacturacion = valorString;
						//System.out.println(contador +") "+ciudadFacturacion);
						contador++;
						
						o = pedido[contador];								//23
						valorString = extraerValorDeNodo(o);
						String regionFacturacion = valorString;
						//System.out.println(contador +") "+regionFacturacion);
						contador++;
						
						o = pedido[contador];								//24
						valorString = extraerValorDeNodo(o);
						String zipFacturacion = valorString;
						//System.out.println(contador +") "+zipFacturacion);
						contador++;
						
						o = pedido[contador];								//25
						valorString = extraerValorDeNodo(o);
						String codigoPaisFacturacion = valorString;
						//System.out.println(contador +") "+codigoPaisFacturacion);
						contador++;
						
						o = pedido[contador];								//26
						valorString = extraerValorDeNodo(o);
						String telefonoFacturacion = valorString;
						//System.out.println(contador +") "+telefonoFacturacion);
						contador++;
						
						o = pedido[contador];								//27
						valorString = extraerValorDeNodo(o);
						String celularFacturacion = valorString;
						//System.out.println(contador +") "+celularFacturacion);
						contador++;
	
						
						
						o = pedido[contador];								//28
						valorString = extraerValorDeNodo(o);
						String nombresEnvio = valorString;
						//System.out.println(contador +") "+nombresEnvio);
						contador++;
						
						o = pedido[contador];								//29
						valorString = extraerValorDeNodo(o);
						String apellidosEnvio = valorString;
						//System.out.println(contador +") "+apellidosEnvio);
						contador++;
						
						o = pedido[contador];								//30
						valorString = extraerValorDeNodo(o);
						String nombreEmpresaEnvio = valorString;
						//System.out.println(contador +") "+nombreEmpresaEnvio);
						contador++;
						
						o = pedido[contador];								//31
						valorString = extraerValorDeNodo(o);
						String direccionEnvio = valorString;
						//System.out.println(contador +") "+direccionEnvio);
						contador++;
						
						o = pedido[contador];								//32
						valorString = extraerValorDeNodo(o);
						String ciudadEnvio = valorString;
						//System.out.println(contador +") "+ciudadEnvio);
						contador++;
						
						o = pedido[contador];								//33
						valorString = extraerValorDeNodo(o);
						String regionEnvio = valorString;
						//System.out.println(contador +") "+regionEnvio);
						contador++;
						
						o = pedido[contador];								//34
						valorString = extraerValorDeNodo(o);
						String zipEnvio = valorString;
						//System.out.println(contador +") "+zipEnvio);
						contador++;
						
						o = pedido[contador];								//35
						valorString = extraerValorDeNodo(o);
						String codigoPaisEnvio = valorString;
						//System.out.println(contador +") "+codigoPaisEnvio);
						contador++;
						
						o = pedido[contador];								//36
						valorString = extraerValorDeNodo(o);
						String telefonoEnvio = valorString;
						//System.out.println(contador +") "+telefonoEnvio);
						contador++;
						
						o = pedido[contador];								//37
						valorString = extraerValorDeNodo(o);
						String celularEnvio = valorString;
						//System.out.println(contador +") "+celularEnvio);
						contador++;
	
						//----DETALLE 
						ArrayList<PedidoDetalleIf> pedidoDetalles = new ArrayList<PedidoDetalleIf>();
						o = pedido[contador];
						ElementNSImpl e = (ElementNSImpl) o;
						NodeList listaNodo = e.getElementsByTagName("value");
						System.out.println("   ---Lista tamaño Value Detalle: ) "+listaNodo.getLength());

						String sku = "";
						BigDecimal precio = null,cantidad = null;
						Boolean incluyeIva = null;
						Boolean tienePersonalizacion = null;
						String personalizacion = null;

						
						Map<String, Object> mapa = new HashMap<String, Object>();
						String idOrdenString = String.valueOf( idOrden.longValue() );
						mapa.put("ordenExternaId", idOrdenString);
						
						Collection<PedidoEnvioIf> pedidosEnvios = pedidoEnvioLocal.findPedidoEnvioByOrdenExternaId(idOrdenString);
						Collection<PedidoEnvioDetalleIf> pedidoEnvioDetalles = new ArrayList<PedidoEnvioDetalleIf>();
						
						
						PedidoIf data = null;
						PedidoEnvioIf pedidoEnvioIf = null;

						if ( pedidosEnvios.size() == 0 ) {
							pedidoEnvioIf = new PedidoEnvioData();
							data = new PedidoData();
						} else {
							
							System.out.println("*********************************************************");
							System.out.println("------- Orden por internet ya INGRESADA: "+idOrdenString+"---");
							System.out.println("*********************************************************");
							continue ProcesarOrdenPorInternet;
							
							/*pedidoEnvioIf =  pedidosEnvios.iterator().next();
							data = pedidoLocal.getPedido(pedidoEnvioIf.getPedidoId());
							if ( !"P".equals( data.getEstado() ) ){
								//Si el estado del pedido ya no es pendiente ya no se lo procesa
								System.out.println("*********************************************************");
								System.out.println("------- Orden por internet ya PROCESADA: "+idOrdenString+"---");
								System.out.println("*********************************************************");
								
								continue ProcesarOrdenPorInternet;
							}*/
						}
						
						//System.out.println("------Empresa---------");
						Collection<EmpresaIf> empresas = empresaLocal.findEmpresaByCodigo(codigoEmpresa);
						if ( empresas.size() == 0 )
							throw new GenericBusinessException("No se encuentra Empresa con código \""+codigoEmpresa+"\"");
						EmpresaIf empresaIf = empresas.iterator().next();
						
						Collection<TipoParametroIf> tiposParametros = tipoParametroLocal.findTipoParametroByCodigo("COMPINTERN");
						if ( tiposParametros.size() == 0 )
							throw new GenericBusinessException("No se encuentra Tipo de Parametro con código \"COMPINTERN\"");
						TipoParametroIf tipoParametroIf = tiposParametros.iterator().next();
						
						Collection<ParametroEmpresaIf> parametros = 
							parametroEmpresaLocal.findParametroEmpresaByTipoparametroId(tipoParametroIf.getId());
						if ( parametros.size() == 0 )
							throw new GenericBusinessException("No existen parametros para compras por Internet !!");
						Map<String, String> mapaParametros = new HashMap<String, String>();
						for ( ParametroEmpresaIf pe : parametros ){
							mapaParametros.put(pe.getCodigo(), pe.getValor());
						}
						
						boolean ingresarDetalle = false; 

						int contadorInternoDetalle = 0;
						int contadorTotalInternoDetalle = 0;
						//System.out.println("   ---Tamanio: ) "+listaNodo.getLength());
						for ( int l = 0 ; l < listaNodo.getLength() ; l++ ){
							Node nodo = listaNodo.item(l); 
							if ( nodo != null ){
								Node nodoTexto = nodo.getFirstChild();
								//System.out.println("   ---Nodo Texto: ) "+nodoTexto);
								//if ( nodoTexto!= null && nodoTexto.getNodeValue() != null){
								//if ( nodoTexto!= null ){
								{
									//System.out.println("   -Nodo Texxto: ) "+nodoTexto.getNodeValue()+"  - Tipo : "+nodoTexto.getNodeType() );
									valorString = "";
									if ( nodoTexto!= null )
										valorString = nodoTexto.getNodeValue();
									
									//System.out.println("   ---COntador: ) "+contadorInternoDetalle);
									if ( contadorTotalInternoDetalle <=6 ){
										switch (contadorInternoDetalle){
											case 0 :{
												//sku = valorString;
												//System.out.println("    "+contadorInternoDetalle +") "+valorString);
												break;
											}
											case 1 :{
												sku = valorString;
												//System.out.println("    "+contadorInternoDetalle +") "+sku);
												break;
											}
											case 2:{
												precio = new BigDecimal(Double.valueOf(valorString));
												//System.out.println("    "+contadorInternoDetalle +") "+precio);
												break;
											}
											case 3:{
												cantidad = new BigDecimal(Double.valueOf(valorString));
												//System.out.println("    "+contadorInternoDetalle +") "+cantidad);
												break;
											}
											case 4:{
												Integer valorVerdad = null;
												
												try{
													valorVerdad = Integer.valueOf(valorString);
												} catch(Exception ex){
													//System.out.println("***Detalle con Iva: "+valorString);
												}
												
												if ( valorVerdad != null ){
													
													if ( valorVerdad == 1 )
														incluyeIva = true;
													else 
														incluyeIva = false;
													
													//ingresarDetalle = true;
												} else {
													if ( valorString.equalsIgnoreCase("true") || valorString.equalsIgnoreCase("false") )
														incluyeIva = new Boolean(valorString);
													else 
														incluyeIva = new Boolean(true);
													
													//ingresarDetalle = true;
												}
												if ( listaNodo.getLength() == 5 )
													ingresarDetalle = true;
												
												break;
											}
											case 5:{
												if ( listaNodo.getLength() > 5 ){
													Integer valorVerdad = null;
													try{
														valorVerdad = Integer.valueOf(valorString);
													} catch(Exception ex){
														System.out.println("***Tiene personalizacion: "+valorString);
													}
													
													if ( valorVerdad != null ){
														if ( valorVerdad == 1 )
															tienePersonalizacion = true;
														else 
															tienePersonalizacion = false;
													} else {
														if ( valorString.equalsIgnoreCase("true") || valorString.equalsIgnoreCase("false") )
															tienePersonalizacion = new Boolean(valorString);
														else 
															tienePersonalizacion = new Boolean(true);
													}
												}
												break;
											}
											case 6: {
												if ( listaNodo.getLength() > 5 ){
													if ( valorString!= null && !valorString.equals("") )
														personalizacion = valorString;
													System.out.println("***Personalizacion: "+valorString);
													ingresarDetalle = true;
												}
												break;
											}
										}
									} else {
										switch (contadorInternoDetalle){
											case 0 :{
												sku = valorString;
												//System.out.println("    "+contadorInternoDetalle +") "+sku);
												break;
											}
											case 1:{
												precio = new BigDecimal(Double.valueOf(valorString));
												//System.out.println("    "+contadorInternoDetalle +") "+precio);
												break;
											}
											case 2:{
												cantidad = new BigDecimal(Double.valueOf(valorString));
												//System.out.println("    "+contadorInternoDetalle +") "+cantidad);
												break;
											}
											case 3:{
												Integer valorVerdad = null;
												
												try{
													valorVerdad = Integer.valueOf(valorString);
												} catch(Exception ex){
													//System.out.println("***Detalle con Iva: "+valorString);
												}
												
												if ( valorVerdad != null ){
													
													if ( valorVerdad == 1 )
														incluyeIva = true;
													else 
														incluyeIva = false;
													
													//ingresarDetalle = true;
												} else {
													if ( valorString.equalsIgnoreCase("true") || valorString.equalsIgnoreCase("false") )
														incluyeIva = new Boolean(valorString);
													else 
														incluyeIva = new Boolean(true);
													
													//ingresarDetalle = true;
												}
												if ( listaNodo.getLength() == 5 )
													ingresarDetalle = true;
												
												break;
											}
											case 4:{
												if ( listaNodo.getLength() > 5 ){
													Integer valorVerdad = null;
													try{
														valorVerdad = Integer.valueOf(valorString);
													} catch(Exception ex){
														System.out.println("***Tiene personalizacion: "+valorString);
													}
													
													if ( valorVerdad != null ){
														if ( valorVerdad == 1 )
															tienePersonalizacion = true;
														else 
															tienePersonalizacion = false;
													} else {
														if ( valorString.equalsIgnoreCase("true") || valorString.equalsIgnoreCase("false") )
															tienePersonalizacion = new Boolean(valorString);
														else 
															tienePersonalizacion = new Boolean(true);
													}
												}
												break;
											}
											case 5: {
												if ( listaNodo.getLength() > 5 ){
													if ( valorString!= null && !valorString.equals("") )
														personalizacion = valorString;
													System.out.println("***Personalizacion: "+valorString);
													ingresarDetalle = true;
												}
												break;
											}
										}
									}
									contadorInternoDetalle++;
									contadorTotalInternoDetalle++;

									if ( ingresarDetalle ){
										/*System.out.println("        ------Ingreso detalle---------");
										System.out.println("        ------"+sku);
										System.out.println("        ------"+precio);
										System.out.println("        ------"+cantidad);
										System.out.println("        ------"+incluyeIva);
										System.out.println("        ------"+tienePersonalizacion);
										System.out.println("        ------"+personalizacion);*/
										
										ProductoIf productoIf = verificarProducto(sku, mapaProductos);
										LoteIf loteIf = obtenerLote(productoIf);
										
										String descripcion = verificarProductoDescripcion(sku, mapaProductoDescripcion,0);
										
										PedidoDetalleIf pedidoDetalleIf = new PedidoDetalleData();
										pedidoDetalleIf.setLoteId(loteIf.getId());
										pedidoDetalleIf.setCantidad(cantidad);
										pedidoDetalleIf.setCantidadpedida(cantidad);
										pedidoDetalleIf.setProductoId(productoIf.getId());
										pedidoDetalleIf.setDescuento(BigDecimal.ZERO);
										pedidoDetalleIf.setDescuentoGlobal(BigDecimal.ZERO);
										pedidoDetalleIf.setOtroimpuesto(BigDecimal.ZERO);
										pedidoDetalleIf.setDescripcion(descripcion);
										pedidoDetalleIf.setIce(BigDecimal.ZERO);
										if ( !incluyeIva ){
											pedidoDetalleIf.setPrecio(precio);
											pedidoDetalleIf.setPrecioreal(precio);
											Double iva = precio.doubleValue()*0.12;
											pedidoDetalleIf.setIva(new BigDecimal(iva));
											Double valor = cantidad.doubleValue()*(iva.doubleValue()+precio.doubleValue());
											pedidoDetalleIf.setValor(new BigDecimal(valor));
											
										} else {
											Double subTotal = precio.doubleValue()/1.12;
											pedidoDetalleIf.setPrecio(new BigDecimal(subTotal));
											pedidoDetalleIf.setPrecioreal(new BigDecimal(subTotal));
											Double iva_unitario = precio.doubleValue() - subTotal;
											Double iva_total = cantidad.doubleValue()*iva_unitario;
											pedidoDetalleIf.setIva(new BigDecimal(iva_total));
											Double valor = subTotal.doubleValue()*cantidad.doubleValue();
											pedidoDetalleIf.setValor(new BigDecimal(valor));
										}
										pedidoDetalleIf.setCodigoPersonalizacion(personalizacion);
										pedidoDetalles.add(pedidoDetalleIf);
										
										
										PedidoEnvioDetalleIf pedidoEnvioDetalleIf = new PedidoEnvioDetalleData();
										pedidoEnvioDetalleIf.setValor(precio);
										pedidoEnvioDetalleIf.setCantidad(cantidad);
										pedidoEnvioDetalleIf.setCodigoBarras(sku);
										pedidoEnvioDetalleIf.setIncluyeIva(incluyeIva?"S":"N");
										pedidoEnvioDetalles.add(pedidoEnvioDetalleIf);

										ingresarDetalle = false;
										contadorInternoDetalle = 0;
										
										personalizacion = null;
										//System.out.println("        ------Salgo de Ingreso detalle---------");
									}
								}
							}
						}

						if ( pedidoDetalles.size() == 0 ){
							
							System.out.println("*********************************************************");
							System.out.println("------- Orden por internet NO TIENE DETALLE: "+idOrdenString+"---");
							System.out.println("*********************************************************");
							
							continue ProcesarOrdenPorInternet;
						}
						
						
						//AGREGAR DETALLE ENVIO
						if ( valorEnvio.doubleValue() > 0D ) {
							sku = mapaParametros.get("SKUINTERNET");
							if ( sku==null )
								throw new GenericBusinessException("No se encuentra Parametro con código \"SKUINTERNET\"");
							
							ProductoIf productoIf = verificarProducto(sku, mapaProductos);
							String descripcion = verificarProductoDescripcion(sku, mapaProductoDescripcion,1) ;
							PedidoDetalleIf pedidoDetalleIf = new PedidoDetalleData();
							pedidoDetalleIf.setCantidad(cantidad);
							pedidoDetalleIf.setCantidadpedida(BigDecimal.ONE);
							pedidoDetalleIf.setProductoId(productoIf.getId());
							pedidoDetalleIf.setPrecio(valorEnvio);
							pedidoDetalleIf.setPrecioreal(valorEnvio);
							pedidoDetalleIf.setIva(BigDecimal.ZERO);
							pedidoDetalleIf.setValor(valorEnvio);
							pedidoDetalleIf.setIce(BigDecimal.ZERO);
							pedidoDetalleIf.setDescuento(BigDecimal.ZERO);
							pedidoDetalleIf.setDescuentoGlobal(BigDecimal.ZERO);
							pedidoDetalleIf.setOtroimpuesto(BigDecimal.ZERO);
							pedidoDetalleIf.setDescripcion(descripcion);
							pedidoDetalles.add(pedidoDetalleIf);
						}
						
						mapa = new HashMap<String, Object>();
						String codigoBodegaMatriz = mapaParametros.get("BODEGAINTERNET");
						if ( codigoBodegaMatriz==null )
							throw new GenericBusinessException("No se encuentra Parametro con código \"BODEGAINTERNET\"");
						mapa.put("codigo", codigoBodegaMatriz);
						mapa.put("empresaId", empresaIf.getId());
						Collection<OficinaIf> oficinasEmpresa = oficinaLocal.findOficinaByQuery(mapa);
						if ( oficinasEmpresa.size() == 0 )
							throw new GenericBusinessException("No existe Oficina matriz con codigo \""+codigoBodegaMatriz+"\" !!");
						OficinaIf oficinaIf = oficinasEmpresa.iterator().next();
						
						//System.out.println("------Cliente---------");
						mapa = new HashMap<String, Object>();
						String identificacion = mapaParametros.get("IDENTIFICACIONINTERNET");
						if ( identificacion==null )
							throw new GenericBusinessException("No se encuentra Parametro con código \"IDENTIFICACIONINTERNET\"");
						mapa.put("identificacion", identificacion);
						//mapaCliente.put("tipoIdentificacion", 1L);
						Collection<ClienteIf> clientes;
						clientes = clienteLocal.findClienteByQuery(mapa);
	
						//System.out.println("------Tamanio cliente : "+clientes.size()+" ---------");
						if (  clientes.size() == 0 )
							throw new GenericBusinessException("No existe Cliente Cosumidor Final  con identificacion "+identificacion+" !!");
						ClienteIf clienteConsumidorFinal = clientes.iterator().next();

						mapa = new HashMap<String, Object>();
						mapa.put("clienteId", clienteConsumidorFinal.getId());
						Collection<ClienteOficinaIf> oficinas = clienteOficinaLocal.findClienteOficinaByQuery(mapa);
						if ( oficinas.size() == 0 )
							throw new GenericBusinessException("Consumidor Final no tiene cliente Oficina !!");
						ClienteOficinaIf clienteOficinaIf = oficinas.iterator().next();

						mapa = new HashMap<String, Object>();
						String codigoTipoDocumento = mapaParametros.get("TIPODOCUMENTOINTERNET");
						if ( codigoTipoDocumento==null )
							throw new GenericBusinessException("No se encuentra Parametro con código \"TIPODOCUMENTOINTERNET\"");
						mapa.put("codigo", codigoTipoDocumento);
						mapa.put("empresaId", empresaIf.getId());
						Collection<TipoDocumentoIf> tiposDocumentos = tipoDocumentoLocal.findTipoDocumentoByQuery(mapa);
						if ( tiposDocumentos.size() == 0 )
							throw new GenericBusinessException("No existe tipo Documento con codigo \""+codigoTipoDocumento+"\" !!");
						TipoDocumentoIf tipoDocumentoIf = tiposDocumentos.iterator().next();

						String tipoIdentificacion = mapaParametros.get("TIPOIDENTIFICACIONINTERNET");
						if ( tipoIdentificacion==null )
							throw new GenericBusinessException("No se encuentra Parametro con código \"TIPOIDENTIFIACIONINTERNET\"");
						Collection<TipoIdentificacionIf> tiposIdentificacion = tipoIdentificacionLocal.findTipoIdentificacionByCodigo(tipoIdentificacion);
						if ( tiposIdentificacion.size() == 0 )
							throw new GenericBusinessException("No existe tipo Identificacion con codigo \""+tipoIdentificacion+"\" !!");
						TipoIdentificacionIf tipoIdentificacionIf = tiposIdentificacion.iterator().next();


						Calendar fechaHoyCal = Calendar.getInstance();
						Date fechaHoy = new Date(fechaHoyCal.getTimeInMillis()); 


						//CREACION DE CABECERA PEDIDO

						java.sql.Timestamp fechaPedido = new java.sql.Timestamp(fechaHoy.getTime());
						data.setFechaPedido(fechaPedido);
						data.setFechaCreacion(fechaCreacion);
						data.setOficinaId(oficinaIf.getId());
						data.setTipodocumentoId(tipoDocumentoIf.getId());
						data.setClienteoficinaId(clienteOficinaIf.getId());
						data.setTipoidentificacionId(tipoIdentificacionIf.getId());

						//System.out.println("------TIPO DE PAGO---------");
						//System.out.println("      ------: "+codigoTipoPago);
						mapa = new HashMap<String, Object>();
						mapa.put("codigo", codigoTipoPago+"%");
						mapa.put("empresaId", empresaIf.getId());
						Collection<FormaPagoIf> tiposPagos = formaPagoLocal.findFormaPagoByQuery(mapa);
						if ( tiposPagos.size() == 0 )
							throw new GenericBusinessException("No existe tipo de pago con codigo \"codigoTipoPago\" !!");
						FormaPagoIf formaPagoIf = tiposPagos.iterator().next();
						//System.out.println("      ------id: "+tipoPagoIf.getId());
						//data.setTipopagoId(formaPagoIf.getId());
						data.setFormapagoId(formaPagoIf.getId());
						//System.out.println("      ------id tipo pago data: "+data.getTipopagoId());

						String moneda = mapaParametros.get("MONEDAINTERNET");
						if ( moneda==null )
							throw new GenericBusinessException("No se encuentra Parametro con código \"MONEDAINTERNET\"");
						Collection<MonedaIf> monedas = monedaLocal.findMonedaByCodigo(moneda);
						if ( monedas.size() == 0 )
							throw new GenericBusinessException("No existe moneda con código \""+moneda+"\" !!");
						MonedaIf monedaIf = monedas.iterator().next();
						data.setMonedaId(monedaIf.getId());

						
						String usuario = mapaParametros.get("USUARIOINTERNET");
						if ( usuario==null )
							throw new GenericBusinessException("No se encuentra Parametro con código \"USUARIOINTERNET\"");
						Collection<UsuarioIf> usuarios = usuarioLocal.findUsuarioByUsuario(usuario);
						if ( usuarios.size() == 0 )
							throw new GenericBusinessException("No existe usuario \""+usuario+"\" responsable de las compras en Internet !! ");
						UsuarioIf usuarioIf = usuarios.iterator().next();

						Collection<UsuarioPuntoImpresionIf> upis = usuarioPuntoImpresionLocal.findUsuarioPuntoImpresionByUsuarioId(usuarioIf.getId());
						if ( upis.size() == 0 )
							throw new GenericBusinessException("Usuario \""+usuario+"\" No tiene puntos de Impresion Asignados !! ");
						UsuarioPuntoImpresionIf upi = upis.iterator().next();

						PuntoImpresionIf puntoImpresionIf = puntoImpresionLocal.getPuntoImpresion(upi.getPuntoimpresionId());
						if ( puntoImpresionIf == null )
							throw new GenericBusinessException("No existe Punto de Impresion !!");

						data.setPuntoimpresionId(puntoImpresionIf.getId());

						String origenDocumento = mapaParametros.get("ORIGENDOCUMENTOINTERNET");
						if ( origenDocumento==null )
							throw new GenericBusinessException("No se encuentra Parametro con código \"ORIGENDOCUMENTOINTERNET\"");
						Collection<OrigenDocumentoIf> origenesDocumento = origenDocumentoLocal.findOrigenDocumentoByCodigo(origenDocumento);
						if ( origenesDocumento.size() == 0 )
							throw new GenericBusinessException("No existe Origen de Documento con código \""+origenDocumento+"\" !!");
						OrigenDocumentoIf origenDocumentoIf = origenesDocumento.iterator().next();
						data.setOrigendocumentoId(origenDocumentoIf.getId());

						String vendedor = mapaParametros.get("VENDEDORINTERNET");
						EmpleadoIf empleadoIf = null;
						if ( vendedor != null){
							Collection<EmpleadoIf> vendedores = empleadoLocal.findEmpleadoByCodigo(vendedor);
							if ( vendedores.size() == 0 )
								throw new GenericBusinessException("No existe Empleado (Vendedor) con código \""+vendedor+"\" !!");
							empleadoIf = vendedores.iterator().next();
						}
						data.setVendedorId(empleadoIf!=null?empleadoIf.getId():null);

						String bodega = mapaParametros.get("BODEGAINTERNET");
						if ( bodega==null )
							throw new GenericBusinessException("No se encuentra Parametro con código \"BODEGAINTERNET\"");
						Collection<BodegaIf> bodegas = bodegaLocal.findBodegaByCodigo(bodega);
						if ( bodegas.size() == 0 )
							throw new GenericBusinessException("No existe bodega con código \""+bodega+"\" !!");
						BodegaIf bodegaIf = bodegas.iterator().next();
						data.setBodegaId(bodegaIf.getId());

						String listaPrecio = mapaParametros.get("LISTAPRECIOINTERNET");
						if ( listaPrecio==null )
							throw new GenericBusinessException("No se encuentra Parametro con código \"LISTAPRECIOINTERNET\"");
						Collection<ListaPrecioIf> listasPrecios = listaPrecioLocal.findListaPrecioByCodigo(listaPrecio);
						if ( listasPrecios.size() == 0 )
							throw new GenericBusinessException("No existe Lista de Precios con código \""+listaPrecio+"\" !!");
						ListaPrecioIf listaPrecioIf = listasPrecios.iterator().next();
						data.setListaprecioId(listaPrecioIf.getId());

						data.setUsuarioId(usuarioIf.getId());
						data.setDiasvalidez(10);

						String referenciaPedido  = mapaParametros.get("REFERENCIAPEDIDOINTERNET");
						if ( referenciaPedido==null )
							throw new GenericBusinessException("No se encuentra Parametro con código \"REFERENCIAPEDIDOINTERNET\"");
						data.setTiporeferencia(referenciaPedido);
						
						String estadoPedido = mapaParametros.get("ESTADOPEDIDOINTERNET");
						if ( estadoPedido==null )
							throw new GenericBusinessException("No se encuentra Parametro con código \"ESTADOPEDIDOINTERNET\"");
						data.setEstado(estadoPedido);

						data.setDireccion(clienteOficinaIf.getDireccion());
						data.setObservacion("VENTA POR INTERNET CON CODIGO: "+idOrdenString);
						data.setIdentificacion(clienteConsumidorFinal.getIdentificacion());
						data.setReferencia("");

						data.setTelefono(clienteOficinaIf.getTelefono());
						data.setContacto(" ");

						data.setPorcentajeComisionAgencia(BigDecimal.ZERO);

						data.setOficinaId(oficinaIf.getId());

						Long pedidoId = null;
						if ( data.getId() == null ){
							String codigo = getCodigoPedido(new java.sql.Date(fechaPedido.getTime()));
							data.setCodigo(codigo);
							pedidoId = pedidoLocal.procesarPedido(data, pedidoDetalles, empresaIf.getId(),null);
							ArrayList<PedidoDetalleIf> pedidoDetallesList = (ArrayList) pedidoDetalleLocal.findPedidoDetalleByPedidoId(pedidoId);
							
							// --- PERSONALIZACIONES ---
							ArrayList<PedidoDetallePersonalizacionIf> pedidoDetallePersonalizaciones = 
								procesarPedidoDetallePersonalizacion(pedidoDetallesList,empresaIf.getId());
							
						} else {

							//Se eliminan los detalle que ya existen.
							Collection<PedidoDetalleIf> detallesExistentes = pedidoDetalleLocal.findPedidoDetalleByPedidoId(data.getId());
							for ( PedidoDetalleIf pd : detallesExistentes ){
								pedidoDetalleLocal.deletePedidoDetalle( pd.getId() );
							}

							pedidoLocal.actualizarPedido(data, pedidoDetalles, data, new ArrayList<PedidoDetalleIf>(),null);
							pedidoId = data.getId();
						}
						
						
						pedidoEnvioIf.setPedidoId(pedidoId);
						pedidoEnvioIf.setValorEnvio(valorEnvio);
						pedidoEnvioIf.setMetodoEnvio(metodoEnvio);

						pedidoEnvioIf.setCorreoCliente(correoCliente);
						pedidoEnvioIf.setNombresCliente(nombresCliente);
						pedidoEnvioIf.setApellidosCliente(apellidosCliente);

						pedidoEnvioIf.setNombresFacturacion(nombresFacturacion);
						pedidoEnvioIf.setApellidosFacturacion(apellidosFacturacion);
						pedidoEnvioIf.setNombreEmpresaFacturacion(nombreEmpresaFacturacion);
						pedidoEnvioIf.setDireccionFacturacion(direccionFacturacion);
						pedidoEnvioIf.setCiudadFacturacion(ciudadFacturacion);
						pedidoEnvioIf.setRegionFacturacion(regionFacturacion);
						pedidoEnvioIf.setZipFacturacion(zipFacturacion);
						pedidoEnvioIf.setCodigoPaisFacturacion(codigoPaisFacturacion);
						pedidoEnvioIf.setTelefonoFacturacion(telefonoFacturacion);
						pedidoEnvioIf.setCelularFacturacion(celularFacturacion);

						pedidoEnvioIf.setNombresEnvio(nombresEnvio);
						pedidoEnvioIf.setApellidosEnvio(apellidosEnvio);
						pedidoEnvioIf.setNombreEmpresaEnvio(nombreEmpresaEnvio);
						pedidoEnvioIf.setDireccionEnvio(direccionEnvio);
						pedidoEnvioIf.setCiudadEnvio(ciudadEnvio);
						pedidoEnvioIf.setRegionEnvio(regionEnvio);
						pedidoEnvioIf.setZipEnvio(zipEnvio);
						pedidoEnvioIf.setCodigoPaisEnvio(codigoPaisEnvio);
						pedidoEnvioIf.setTelefonoEnvio(telefonoEnvio);
						pedidoEnvioIf.setCelularEnvio(celularEnvio);

						pedidoEnvioIf.setFechaActualizacion(fechaActualizacion);
						pedidoEnvioIf.setIdentificacionFundacion(identificacionFundacionDonacion);
						pedidoEnvioIf.setOrdenExternaId(idOrdenString);
						
						pedidoEnvioIf.setValorDescuento(valorDescuento);
						pedidoEnvioIf.setValorImpuesto(valorImpuesto);
						pedidoEnvioIf.setValorSubtotal(valorSubtotal);
						pedidoEnvioIf.setValorTotal(valorTotal);

						if ( pedidoEnvioIf.getId() == null ){
							pedidoEnvioIf = pedidoEnvioLocal.addPedidoEnvio(pedidoEnvioIf);
							
							for ( PedidoEnvioDetalleIf ped : pedidoEnvioDetalles ){
								Long pedidoEnvioId = pedidoEnvioIf.getId();
								ped.setPedidoEnvioId(
									pedidoEnvioId!=null ? pedidoEnvioId:pedidoEnvioIf.getPrimaryKey() );
								pedidoEnvioDetalleLocal.addPedidoEnvioDetalle(ped);
							}
							
						} else { 
							pedidoEnvioLocal.savePedidoEnvio(pedidoEnvioIf);
							
							Collection<PedidoEnvioDetalleIf> detalleExistente = 
								pedidoEnvioDetalleLocal.findPedidoEnvioDetalleByPedidoEnvioId(pedidoEnvioIf.getId());
							for ( PedidoEnvioDetalleIf pede : detalleExistente ){
								pedidoEnvioDetalleLocal.deletePedidoEnvioDetalle(pede.getId());
							}
							
							for ( PedidoEnvioDetalleIf ped : pedidoEnvioDetalles ){
								Long pedidoEnvioId = pedidoEnvioIf.getId();
								ped.setPedidoEnvioId(pedidoEnvioId);
								pedidoEnvioDetalleLocal.addPedidoEnvioDetalle(ped);
							}
							
						}

					} catch (GenericBusinessException e) {
						e.printStackTrace();
						System.out.println("***ERROR GENERIC: "+e.getMessage());
						return false;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("***ERROR: "+e.getMessage());
						return false;
					}
	
				} else {
					System.out.println("***TIpo de Dato Interno (fallido): "+pedidoObjeto.getClass().getSimpleName());
				}
			}

			System.out.println(" ----------- FIN DE COMPRAS POR INTERNET : TRUE ------------- ");
			return true;
			
		} catch(Exception e){
			System.out.println("***ERROR: "+e.getMessage());
			e.printStackTrace();
		}
		System.out.println(" ----------- FIN DE COMPRAS POR INTERNET : FALSE ------------- ");
		return false;
	}

	private ArrayList<PedidoDetallePersonalizacionIf> procesarPedidoDetallePersonalizacion(
			ArrayList<PedidoDetalleIf> pedidoDetalles,Long empresaId)
			throws GenericBusinessException {
		ArrayList<PedidoDetallePersonalizacionIf> pedidoDetallePersonalizaciones = new ArrayList<PedidoDetallePersonalizacionIf>();
		
		for ( PedidoDetalleIf pd : pedidoDetalles ){
			String codigoPersonalizacion = pd.getCodigoPersonalizacion();
			if ( codigoPersonalizacion != null ){
				String[] personalizaciones = codigoPersonalizacion.split("\\|\\|");
				for (  int m = 0; m < personalizaciones.length; m++ ){
					if ( m == 0 ){
						//String sku_personalizacion = personalizaciones[m];
						String[] string_sku = personalizaciones[m].split(":");
						try {
							String sku_personalizacion = (string_sku.length > 0)?string_sku[0]:"";
						} catch (Exception e) {
							throw new GenericBusinessException("SKU no válido");
						}
					} else {
						
						PersonalizacionTipoPersonalizacionIf tipoPersonalizacionIf = null;
						PersonalizacionLogoDisenioIf personalizacionLogoDisenioIf = null;
						PersonalizacionTipoImpresionIf tipoImpresionIf = null;
						PersonalizacionTamanioIf tamanioIf = null;
						PersonalizacionColorIf personalizacionColorIf = null;
						PersonalizacionUbicacionIf personalizacionUbicacionIf = null;
						PersonalizacionTipoLetraIf personalizacionTipoLetraIf = null;
						PersonalizacionDisenioIf personalizacionDisenioIf = null;
						String mensajeDisenio = null;
						
						PedidoDetallePersonalizacionIf pedidoDetallepersonalizacionIf = null;
						String[] datos_personalizaciones = personalizaciones[m].split(":");
						Map<String, Object> mapaBusqueda = null;
						for ( int n = 0 ; n < datos_personalizaciones.length; n++ ){
							
							String dato_personalizacion = datos_personalizaciones[n];
							if ( dato_personalizacion != null ){
								switch(n){
									case 0:{
										pedidoDetallepersonalizacionIf = new PedidoDetallePersonalizacionEJB();
										pedidoDetallepersonalizacionIf.setPedidoDetalleId(pd.getId());
										
										String tipoPersonalizacion = dato_personalizacion;
										mapaBusqueda = new HashMap<String, Object>();
										mapaBusqueda.put("empresaId", empresaId);
										mapaBusqueda.put("codigo", tipoPersonalizacion);
										Collection<PersonalizacionTipoPersonalizacionIf> tiposPersonalizaciones = 
											personalizacionTipoPersonalizacionLocal
												.findPersonalizacionTipoPersonalizacionByQuery(mapaBusqueda);
										
										if ( tiposPersonalizaciones.size() == 0 ){
											throw new GenericBusinessException("Tipo de Personalizacion con codigo "+tipoPersonalizacion+" no existe");
										}
										
										tipoPersonalizacionIf = tiposPersonalizaciones.iterator().next();
										pedidoDetallepersonalizacionIf.setTipoPersonalizacionId(tipoPersonalizacionIf.getId());
										break;
									}
									case 1:{
	
										String logoDisenio = dato_personalizacion;
										mapaBusqueda = new HashMap<String, Object>();
										mapaBusqueda.put("empresaId", empresaId);
										mapaBusqueda.put("codigo", logoDisenio);
										Collection<PersonalizacionLogoDisenioIf> logos = 
											personalizacionLogoDisenioLocal.findPersonalizacionLogoDisenioByQuery(mapaBusqueda);
										
										if ( logos.size() == 0 ){
											throw new GenericBusinessException("Logo/Diseno con codigo "+logoDisenio+" no existe");
										}
										
										personalizacionLogoDisenioIf = logos.iterator().next();
										pedidoDetallepersonalizacionIf.setLogoDisenioPersonalizacionId(personalizacionLogoDisenioIf.getId());
										break;
									}
									case 2:{
										String tipoImpresion = dato_personalizacion;
										mapaBusqueda = new HashMap<String, Object>();
										mapaBusqueda.put("empresaId", empresaId);
										mapaBusqueda.put("codigo", tipoImpresion);
										Collection<PersonalizacionTipoImpresionIf> tiposImpresion = 
											personalizacionTipoImpresionLocal.findPersonalizacionTipoImpresionByQuery(mapaBusqueda);
										
										if ( tiposImpresion.size() == 0 ){
											throw new GenericBusinessException("Tipo de Impresion con codigo "+tipoImpresion+" no existe");
										}
										
										tipoImpresionIf = tiposImpresion.iterator().next();
										pedidoDetallepersonalizacionIf.setImpresionPersonalizacionId(tipoImpresionIf.getId());
										break;
									}
									case 3:{
										String tamanio = dato_personalizacion;
										mapaBusqueda = new HashMap<String, Object>();
										mapaBusqueda.put("empresaId", empresaId);
										mapaBusqueda.put("codigo", tamanio);
										Collection<PersonalizacionTamanioIf> tamanios = 
											personalizacionTamanioLocal.findPersonalizacionTamanioByQuery(mapaBusqueda);
										
										if ( tamanios.size() == 0 ){
											throw new GenericBusinessException("Tamanio con codigo "+tamanio+" no existe");
										}
										
										tamanioIf = tamanios.iterator().next();
										pedidoDetallepersonalizacionIf.setTamanioPersonalizacionId(tamanioIf.getId());
										break;
									}
									case 4:{
										String color = dato_personalizacion;
										mapaBusqueda = new HashMap<String, Object>();
										mapaBusqueda.put("empresaId", empresaId);
										mapaBusqueda.put("codigo", color);
										Collection<PersonalizacionColorIf> colores = 
											personalizacionColorLocal.findPersonalizacionColorByQuery(mapaBusqueda);
										
										if ( colores.size() == 0 ){
											throw new GenericBusinessException("Color con codigo "+color+" no existe");
										}
										
										personalizacionColorIf = colores.iterator().next();
										pedidoDetallepersonalizacionIf.setColorPersonalizacionId(personalizacionColorIf.getId());
										break;
									}
									case 5:{
										String ubicacion = dato_personalizacion;
										mapaBusqueda = new HashMap<String, Object>();
										mapaBusqueda.put("empresaId", empresaId);
										mapaBusqueda.put("codigo", ubicacion);
										Collection<PersonalizacionUbicacionIf> ubicaciones = 
											personalizacionUbicacionLocal.findPersonalizacionUbicacionByQuery(mapaBusqueda);
										
										if ( ubicaciones.size() == 0 ){
											throw new GenericBusinessException("Ubicacion con codigo "+ubicacion+" no existe");
										}
										
										personalizacionUbicacionIf = ubicaciones.iterator().next();
										pedidoDetallepersonalizacionIf.setUbicacionPersonalizacionId(personalizacionUbicacionIf.getId());
										break;
									}
									case 6:{
										String tipoLetra = dato_personalizacion;
										mapaBusqueda = new HashMap<String, Object>();
										mapaBusqueda.put("empresaId", empresaId);
										mapaBusqueda.put("codigo", tipoLetra);
										Collection<PersonalizacionTipoLetraIf> tiposLetra = 
											personalizacionTipoLetraLocal.findPersonalizacionTipoLetraByQuery(mapaBusqueda);
										
										if ( tiposLetra.size() == 0 ){
											throw new GenericBusinessException("Tipo de Letra con codigo "+tipoLetra+" no existe");
										}
										
										personalizacionTipoLetraIf = tiposLetra.iterator().next();
										pedidoDetallepersonalizacionIf.setTipoLetraPersonalizacionId(personalizacionTipoLetraIf.getId());
										break;
									}
									case 7:{
										if (tipoPersonalizacionIf.getCodigo().equals("5")) {
											String disenio = dato_personalizacion;
											mapaBusqueda = new HashMap<String, Object>();
											mapaBusqueda.put("empresaId", empresaId);
											mapaBusqueda.put("codigo", disenio);
											Collection<PersonalizacionDisenioIf> disenios = 
												personalizacionDisenioLocal.findPersonalizacionDisenioByQuery(mapaBusqueda);
											
											if ( disenios.size() == 0 ){
												throw new GenericBusinessException("Diseño con codigo "+disenio+" no existe");
											}
											personalizacionDisenioIf = disenios.iterator().next();
											pedidoDetallepersonalizacionIf.setDisenioPersonalizacionId(personalizacionDisenioIf.getId());
											pedidoDetallepersonalizacionIf.setMensaje("");
										} else {
											mensajeDisenio = dato_personalizacion;
											pedidoDetallepersonalizacionIf.setMensaje(mensajeDisenio);
										}
										break;
									}
								}
							}
						}
						
						//Concateno todos los nombres de la personalizacion individual, separados por ;
						String descripcion = 
							(tipoPersonalizacionIf != null ? (tipoPersonalizacionIf.getNombre()+";" ): "")+ 
							(personalizacionLogoDisenioIf != null? (personalizacionLogoDisenioIf.getNombre()+";" ): "")+
							(tipoImpresionIf != null ? (tipoImpresionIf.getNombre()+";" ): "")+
							(tamanioIf != null ? (tamanioIf.getNombre()+";" ): "")+
							(personalizacionColorIf != null ? (personalizacionColorIf.getNombre()+";" ): "")+
							(personalizacionUbicacionIf != null ? (personalizacionUbicacionIf.getNombre()+";" ): "")+
							(personalizacionTipoLetraIf != null ? (personalizacionTipoLetraIf.getNombre()+";" ): "")+
							(personalizacionDisenioIf != null ? ("DISEÑO "+(personalizacionDisenioIf.getEtiqueta()!=null?personalizacionDisenioIf.getEtiqueta():personalizacionDisenioIf.getCodigo())+";"): "") +
							(mensajeDisenio != null ? mensajeDisenio : "");
						pedidoDetallepersonalizacionIf.setDescripcion(descripcion);
						
						pedidoDetallePersonalizaciones.add(pedidoDetallepersonalizacionIf);
						
					}
				}
			}
		}
		
		for ( PedidoDetallePersonalizacionIf pedidoDetallePersonalizacionIf :pedidoDetallePersonalizaciones ){
			pedidoDetallePersonalizacionLocal.addPedidoDetallePersonalizacion(pedidoDetallePersonalizacionIf);
		}
		return pedidoDetallePersonalizaciones;
	}
	
	public long getPuntosDisponiblesDeTarjetaAfiliacion(
			String tipoIdentificacion,String identificacion ){
		
		long puntosDisponibles = 0L; 
		
		try {
			if ( "codigo".equalsIgnoreCase(tipoIdentificacion) ){
			
				Collection<TarjetaIf> tarjetas = tarjetaLocal.findTarjetaByCodigo(identificacion);
				if ( tarjetas.size() == 0 ){
					throw new GenericBusinessException("NO EXISTE TARJETA CON MISMO CODIGO "+identificacion);
				} else if ( tarjetas.size() == 1 ) {
					puntosDisponibles = calcularPuntosDisponibles(tarjetas.iterator().next());
				} else {
					throw new GenericBusinessException("EXISTE MAS DE UNA TARJETA CON EL MISMO CODIGO: "+identificacion);
				}
			} else if ( "email".equalsIgnoreCase(tipoIdentificacion) ){
				
				ClienteOficinaIf clienteOficina = null;
				
				Collection<ClienteOficinaIf> clientesOficina = clienteOficinaLocal.findClienteOficinaByEmail(identificacion);
				
				if ( clientesOficina.size() == 0 ){
					throw new GenericBusinessException("NO EXISTE CLIENTE CON CORREO: "+identificacion);
				} else if ( clientesOficina.size() == 1 ){
					clienteOficina = clientesOficina.iterator().next();
				} else {
					throw new GenericBusinessException("EXISTE MAS DE UN CLIENTE CON CORREO: "+identificacion);
				}
				
				Collection<TarjetaIf> tarjetas = tarjetaLocal.findTarjetaByClienteoficinaId(clienteOficina.getId());
				
				if ( tarjetas.size() == 0 ){
					throw new GenericBusinessException("NO EXISTE TARJETA CON MISMO CODIGO "+identificacion);
				} else if ( tarjetas.size() == 1 ) {
					puntosDisponibles = calcularPuntosDisponibles(tarjetas.iterator().next());
				} else {
					throw new GenericBusinessException("EXISTE MAS DE UNA TARJETA CON EL MISMO CODIGO: "+identificacion);
				}
				
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			System.out.println(" ****** ERROR: "+e.getMessage());
		} catch (Exception e) {
			System.out.println(" ****** ERROR: "+e.getMessage());
			e.printStackTrace();
		}
		return puntosDisponibles;
	}
	
	public boolean actualizarPuntosComprometidos( String tipoIdentificacion , String identificacion , long puntos ){
		
		try {
			if ( "codigo".equalsIgnoreCase(tipoIdentificacion) ){
				Collection<TarjetaIf> tarjetas = tarjetaLocal.findTarjetaByCodigo(identificacion);
				if ( tarjetas.size() == 0 ){
					throw new GenericBusinessException("NO EXISTE TARJETA CON MISMO CODIGO "+identificacion);
				} else if ( tarjetas.size() == 1 ) {
					TarjetaIf tarjeta = tarjetas.iterator().next();
					long suma = sumarPuntosComprometidos(tarjeta,puntos);
					tarjeta.setPuntosComprometidos(new BigDecimal(suma));
					return true;
				} else {
					throw new GenericBusinessException("EXISTE MAS DE UNA TARJETA CON EL MISMO CODIGO: "+identificacion);
				}
			} else if ( "email".equalsIgnoreCase(tipoIdentificacion) ){
				ClienteOficinaIf clienteOficina = null;
				
				Collection<ClienteOficinaIf> clientesOficina = clienteOficinaLocal.findClienteOficinaByEmail(identificacion);
				
				if ( clientesOficina.size() == 0 ){
					throw new GenericBusinessException("NO EXISTE CLIENTE CON CORREO: "+identificacion);
				} else if ( clientesOficina.size() == 1 ){
					clienteOficina = clientesOficina.iterator().next();
				} else {
					throw new GenericBusinessException("EXISTE MAS DE UN CLIENTE CON CORREO: "+identificacion);
				}
				
				Collection<TarjetaIf> tarjetas = tarjetaLocal.findTarjetaByClienteoficinaId(clienteOficina.getId());				
				if ( tarjetas.size() == 0 ){
					throw new GenericBusinessException("NO EXISTE TARJETA CON MISMO CODIGO "+identificacion);
				} else if ( tarjetas.size() == 1 ) {
					TarjetaIf tarjeta = tarjetas.iterator().next();
					long suma = sumarPuntosComprometidos(tarjeta,puntos);
					tarjeta.setPuntosComprometidos(new BigDecimal(suma));
					return true;
				} else {
					throw new GenericBusinessException("EXISTE MAS DE UNA TARJETA CON EL MISMO CODIGO: "+identificacion);
				}
			}	
		} catch (GenericBusinessException e) {
			System.out.println(" ***** ERROR: "+e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(" ***** ERROR: "+e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean ingresarClienteBasico(Object[] datos) {
		
		try{
			System.out.println("****----INICIO DE INGRESO DE CLIENTES... ");
			
			Collection<TipoParametroIf> tiposParametros = tipoParametroLocal.findTipoParametroByCodigo("COMPINTERN");
			if ( tiposParametros.size() == 0 )
				throw new GenericBusinessException("No se encuentra Tipo de Parametro con código \"COMPINTERN\"");
			TipoParametroIf tipoParametroIf = tiposParametros.iterator().next();
			
			Collection<ParametroEmpresaIf> parametros = 
				parametroEmpresaLocal.findParametroEmpresaByTipoparametroId(tipoParametroIf.getId());
			if ( parametros.size() == 0 )
				throw new GenericBusinessException("No existen parametros para compras por Internet !!");
			Map<String, String> mapaParametros = new HashMap<String, String>();
			for ( ParametroEmpresaIf pe : parametros ){
				mapaParametros.put(pe.getCodigo(), pe.getValor());
			}
			
			//System.out.println("---CARGO PARAMETROS... ");
			
			String codigoEmpresa = mapaParametros.get("CODIGOEMPRESACLIENTEINTERNET");
			Collection<EmpresaIf> empresas = empresaLocal.findEmpresaByCodigo(codigoEmpresa);
			if ( empresas.size() == 0 )
				throw new GenericBusinessException("No se encuentra Empresa con código \""+codigoEmpresa+"\"");
			else if ( empresas.size() > 1 )
				throw new GenericBusinessException("Existe mas de una Empresa con código \""+codigoEmpresa+"\"");
			EmpresaIf empresaIf = empresas.iterator().next();
			
			String tipoOperador = mapaParametros.get("TIPOOPERADORNUEVOCLIENTEINTERNET");
			if ( tipoOperador == null )
				throw new GenericBusinessException("No se encuentra Parametro con código \"TIPOOPERADORNUEVOCLIENTEINTERNET\"");
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("codigo", tipoOperador);
			mapa.put("empresaId", empresaIf.getId());
			Collection<TipoClienteIf> tiposClientes = tipoClienteLocal.findTipoClienteByQuery(mapa);
			if ( tiposClientes.size() == 0 )
				throw new GenericBusinessException("No existe tipo de cliente con codigo "+tipoOperador);
			TipoClienteIf tipoClienteIf = tiposClientes.iterator().next();
			
			String tipoIdentificacion = mapaParametros.get("TIPOIDENTIFICACIONCLIENTEINTERNET");
			mapa = new HashMap<String, Object>();
			mapa.put("codigo", tipoIdentificacion);
			Collection<TipoIdentificacionIf> tiposIdentificacion = tipoIdentificacionLocal.findTipoIdentificacionByQuery(mapa);
			if ( tiposIdentificacion.size() == 0 )
				throw new GenericBusinessException("No existe tipo de identificacion con código "+tipoIdentificacion);
			TipoIdentificacionIf tipoIdentificacionIf = tiposIdentificacion.iterator().next();
			
			String corporacion = mapaParametros.get("CORPORACIONNUEVOCLIENTEINTERNET");
			if ( tipoOperador == null )
				throw new GenericBusinessException("No se encuentra Parametro con código \"CORPORACIONNUEVOCLIENTEINTERNET\"");
			mapa = new HashMap<String, Object>();
			mapa.put("codigo", corporacion);
			mapa.put("empresaId", empresaIf.getId());
			Collection<CorporacionIf> corporaciones = corporacionLocal.findCorporacionByQuery(mapa);
			if ( corporaciones.size() == 0 )
				throw new GenericBusinessException("No existe corporacion con codigo "+corporacion);
			CorporacionIf corporacionIf = corporaciones.iterator().next();
			
			String usuarioFinal = mapaParametros.get("USUARIOFINALNUEVOCLIENTEINTERNET");
			if ( usuarioFinal == null )
				throw new GenericBusinessException("No se encuentra Parametro con código \"USUARIOFINALNUEVOCLIENTEINTERNET\"");
			usuarioFinal = "".equals(usuarioFinal) ? "N" : usuarioFinal ;
			
			String contribuyenteEspecial = mapaParametros.get("CONTRIBUYENTEESPECIALNUEVOCLIENTEINTERNET");
			if ( contribuyenteEspecial == null )
				throw new GenericBusinessException("No se encuentra Parametro con código \"CONTRIBUYENTEESPECIALNUEVOCLIENTEINTERNET\"");
			contribuyenteEspecial = "".equals(contribuyenteEspecial) ? "N" : contribuyenteEspecial ;
			
			String tipoPersona = mapaParametros.get("TIPOPERSONANUEVOCLIENTEINTERNET");
			if ( tipoPersona == null )
				throw new GenericBusinessException("No se encuentra Parametro con código \"TIPOPERSONANUEVOCLIENTEINTERNET\"");
			tipoPersona = "".equals(tipoPersona) ? "N" : tipoPersona ;
			
			String llevaContabilidad = mapaParametros.get("LLEVACONTABILIDADNUEVOCLIENTEINTERNET");
			if ( llevaContabilidad == null )
				throw new GenericBusinessException("No se encuentra Parametro con código \"LLEVACONTABILIDADNUEVOCLIENTEINTERNET\"");
			llevaContabilidad = "".equals(llevaContabilidad) ? "N" : llevaContabilidad ;
			
			String observacion = "CLIENTE INGRESADO DESDE INTERNET";
			
			//System.out.println("---OBSERVACION... ");
			
			String ciudadClienteOficina = mapaParametros.get("CIUDADNUEVOCLIENTEINTERNET");
			if ( ciudadClienteOficina == null )
				throw new GenericBusinessException("No se encuentra Parametro con código \"CIUDADNUEVOCLIENTEINTERNET\"");
			Collection<CiudadIf> ciudades = ciudadLocal.findCiudadByCodigo(ciudadClienteOficina);
			if ( ciudades.size() == 0 )
				throw new GenericBusinessException("No existe ciudad con codigo "+ciudadClienteOficina);
			CiudadIf ciudadIf = ciudades.iterator().next();
			
			//System.out.println("---RECORRER EL ARREGLO... ");
			
			//***************DATOS DEL ARREGLO****************//
			System.out.println("****----TAMANIO DE LISTA DE CLIENTES : "+ datos.length );
			
			ArrayList<ClienteIf> listaClientes = new ArrayList<ClienteIf>();
			ArrayList<ClienteWebIf> listaClientesWeb = new ArrayList<ClienteWebIf>();
			
			for ( int indice = 0 ; indice < datos.length ; indice++ ){
			//for ( int indice = 0 ; indice < 10 ; indice++ ){
				//System.out.println("---PRIMER DATO: "+datos[indice]);
				//System.out.println("---CLASE: "+datos[indice].getClass().getName());
				
	
				Object listaValues = (Object)datos[indice];	
				
				ElementNSImpl e = (ElementNSImpl) listaValues;
				NodeList listaNodo = e.getElementsByTagName("value");
				//System.out.println("   ---Lista tamaño Value Detalle: ) "+listaNodo.getLength());
				
				
				boolean ingresarDetalle = false; 
	
				
				String idExterno = null, fecha_creacion = null;
				String nombres = null,apellidos = null;
				String identificacion = null, pais = null,ciudad = null,direccion = null,telefono = null,celular = null;
				
				
				int contadorInternoDetalle = 0; 
				for ( int l = 0 ; l < listaNodo.getLength() ; l++ ){
					Node nodo = listaNodo.item(l); 
					//System.out.println("+NODO ("+l+"): "+nodo);
					if ( nodo != null ){
						
						String valor = "";
						Node nodoTexto = nodo.getFirstChild();
						//System.out.println("-NODO TEXTO ("+l+"): "+nodoTexto);
						
						valor = nodoTexto != null ? nodoTexto.getNodeValue():"";
						//if ( nodoTexto!= null && nodoTexto.getNodeValue() != null)
						{
							ingresarDetalle = false;
							//System.out.println("---Valor de nodo ("+contadorInternoDetalle+"): "+valor);
							
							switch(contadorInternoDetalle){ 
								case 0:{
									idExterno = valor;						//****** 0
									//System.out.println("---id: "+id);
									break;
								}
								case 1:{
									fecha_creacion = valor;						//****** 1
									//System.out.println("---fecha_creacion "+fecha_creacion);
									break;
								}	
								case 2:{
										nombres = valor;						//****** 2
										if (nombres == null || "".equals(nombres.trim()) )
											throw new GenericBusinessException("Nombres Vacios");
										//System.out.println("---nombres: "+nombres);
										break;
								}
								case 3:{
										apellidos = valor;						//****** 3
										if (apellidos == null || "".equals(apellidos.trim()) )
											throw new GenericBusinessException("Apellidos Vacios");
										//System.out.println("---apellidos "+apellidos);
										break;
								}
								case 4:{
										identificacion = valor;				//****** 4
										if (identificacion == null || "".equals(identificacion.trim()) )
											throw new GenericBusinessException("Email Vacio");
										//System.out.println("---identificacion: "+identificacion);
										break;
								}
								case 5:{
										pais = valor;					//****** 5
										//System.out.println("---pais: "+pais);
										break;
										
								}
								case 6:{
										ciudad = valor;					//****** 6
										//System.out.println("---ciudad: "+ciudad);
										break;
								}
								case 7:{
										direccion = valor;					//****** 7
										//System.out.println("---direccion: "+direccion);
										break;
								}
								case 8:{
										telefono = valor;					//****** 8
										//System.out.println("---telefono: "+telefono);
										break;
								}
								case 9:{		
										celular = valor;					//****** 9
										//System.out.println("---celular: "+celular);
										ingresarDetalle = true;
										break;
								}
							}
	
							contadorInternoDetalle++;
							
							if (ingresarDetalle){
								//System.out.println("******* INGRESO DE INFO ******");
								String nombreLegal = apellidos.toUpperCase()+" "+nombres.toUpperCase();
								ClienteIf clienteIf = new ClienteData();
								clienteIf.setTipoclienteId(tipoClienteIf.getId());
								clienteIf.setNombreLegal(nombreLegal);
								clienteIf.setRazonSocial(nombreLegal);
								clienteIf.setTipoidentificacionId(tipoIdentificacionIf.getId());
								clienteIf.setIdentificacion(identificacion);
								clienteIf.setCorporacionId(corporacionIf.getId());
								clienteIf.setUsuariofinal(usuarioFinal);
								clienteIf.setContribuyenteEspecial(contribuyenteEspecial);
								clienteIf.setTipoPersona(tipoPersona);
								clienteIf.setLlevaContabilidad(llevaContabilidad);
								clienteIf.setObservacion(observacion);
								clienteIf.setFechaCreacion(new Timestamp(new java.util.Date().getTime()));
								clienteIf.setEstado("A");
								
								ClienteOficinaIf clienteOficinaIf = new ClienteOficinaData();
								clienteOficinaIf.setCodigo("001");
								clienteOficinaIf.setDescripcion(nombreLegal);
								clienteOficinaIf.setCiudadId(ciudadIf.getId());
								clienteOficinaIf.setDireccion(direccion.toUpperCase() + "( "+ciudad+" - "+pais+" )");
								clienteOficinaIf.setFax(celular);
								clienteOficinaIf.setEmail(identificacion);
								clienteOficinaIf.setEstado("A");
								clienteOficinaIf.setFechaCreacion(new Timestamp(new java.util.Date().getTime()));
								
								Long telefonoL = null;
								try{
									if ( telefono != null )
										telefonoL = Long.valueOf(telefono.replaceAll(" ", ""));
								} catch( Exception ex ){  }
								
								clienteOficinaIf.setTelefono(telefonoL!=null?String.valueOf(telefonoL):"2");
								
								ArrayList<ClienteOficinaIf> listaClienteOficina = new ArrayList<ClienteOficinaIf>();
								listaClienteOficina.add(clienteOficinaIf);
								
								try{
									if ( identificacion != null && !"".equals(identificacion) ){
										clienteLocal.procesarClienteWeb(clienteIf, new ArrayList<ClienteZonaIf>(), listaClienteOficina, 
											new HashMap(), new HashMap() , true);
										listaClientes.add(clienteIf);
									} else {
										//System.out.println(" **** Error: Cliente sin email");
										ClienteWebIf clienteWebIf = registrarClienteWeb(
												idExterno, nombres, apellidos,
												identificacion, pais, ciudad,
												direccion, telefono, celular);
										try{
											Collection<ClienteWebIf> clientesWeb = clienteWebLocal.findClienteWebByEmail(clienteWebIf.getEmail());
											if ( clientesWeb.size() == 0 ){
												ClienteWebIf cwif = clienteWebLocal.addClienteWeb(clienteWebIf);
												System.out.println("***GUARDADO EN CLIENTE WEB - ID: "+idExterno+" email: "+identificacion);
												listaClientesWeb.add(cwif);
											} else {
												ClienteWebIf cw = clientesWeb.iterator().next(); 
												cw.setIdExterno(clienteWebIf.getIdExterno());
												cw.setNombres(clienteWebIf.getNombres());
												cw.setApellidos(clienteWebIf.getApellidos());
												cw.setPais(clienteWebIf.getPais());
												cw.setCiudad(clienteWebIf.getCiudad());
												cw.setDireccion(clienteWebIf.getDireccion());
												cw.setTelefono(clienteWebIf.getTelefono());
												cw.setCelular(clienteWebIf.getCelular());
												listaClientesWeb.add(cw);
											}
										} catch(Exception excw){
											System.out.println("  +++ ERROR EN GUARDAR CLIENTE WEB: "+excw.getMessage());
										}
									}
								} catch(Exception ex){
									//System.out.println("*****************************************************");
									ex.printStackTrace();
									
									System.out.println("***ERROR EN GUARDAR CLIENTE: "+ex.getMessage());
									
									ClienteWebIf clienteWebIf = registrarClienteWeb(
											idExterno, nombres, apellidos,
											identificacion, pais, ciudad,
											direccion, telefono, celular);
									
									try{
										Collection<ClienteWebIf> clientesWeb = clienteWebLocal.findClienteWebByEmail(clienteWebIf.getEmail());
										if ( clientesWeb.size() == 0 ){
											ClienteWebIf cwif = clienteWebLocal.addClienteWeb(clienteWebIf);
											System.out.println("***GUARDADO EN CLIENTE WEB - ID: "+idExterno+" email: "+identificacion);
											listaClientesWeb.add(cwif);
										} else {
											ClienteWebIf cw = clientesWeb.iterator().next(); 
											cw.setIdExterno(clienteWebIf.getIdExterno());
											cw.setNombres(clienteWebIf.getNombres());
											cw.setApellidos(clienteWebIf.getApellidos());
											cw.setPais(clienteWebIf.getPais());
											cw.setCiudad(clienteWebIf.getCiudad());
											cw.setDireccion(clienteWebIf.getDireccion());
											cw.setTelefono(clienteWebIf.getTelefono());
											cw.setCelular(clienteWebIf.getCelular());
											listaClientesWeb.add(cw);
										}
									} catch(Exception excw){
										System.out.println("  +++ ERROR EN GUARDAR CLIENTE WEB: "+excw.getMessage());
									}
									
								}
								contadorInternoDetalle = 0;
							}
							
						}
					}
				}
			}

			System.out.println("****----TAMANIO DE LISTA DE CLIENTES CREADOS: "+ listaClientes.size() );
			System.out.println("****----TAMANIO DE LISTA DE CLIENTES WEB CREADOS: "+ listaClientesWeb.size() );
			
			return true;
			
		} catch (GenericBusinessException e){
			e.printStackTrace();
			System.out.println(" ***** ERROR: "+e.getMessage());
		} catch (Exception e){
			e.printStackTrace();
			System.out.println(" ***** ERROR: ERROR GENERAL EN INGRESO DE CLIENTE !!");
		}
		return false;
	}

	private ClienteWebIf registrarClienteWeb(String idExterno, String nombres,
			String apellidos, String identificacion, String pais,
			String ciudad, String direccion, String telefono, String celular) {
		ClienteWebIf clienteWebIf = new ClienteWebEJB();
		clienteWebIf.setIdExterno(idExterno);
		clienteWebIf.setNombres(nombres);
		clienteWebIf.setApellidos(apellidos);
		clienteWebIf.setEmail(identificacion);
		clienteWebIf.setPais(pais);
		clienteWebIf.setCiudad(ciudad);
		clienteWebIf.setDireccion(direccion);
		clienteWebIf.setTelefono(telefono);
		clienteWebIf.setCelular(celular);
		return clienteWebIf;
	}
	
	public boolean ingresarClienteBasico(ArrayList<String> datos) {
		
		try{
			Collection<TipoParametroIf> tiposParametros = tipoParametroLocal.findTipoParametroByCodigo("COMPINTERN");
			if ( tiposParametros.size() == 0 )
				throw new GenericBusinessException("No se encuentra Tipo de Parametro con código \"COMPINTERN\"");
			TipoParametroIf tipoParametroIf = tiposParametros.iterator().next();
			
			Collection<ParametroEmpresaIf> parametros = 
				parametroEmpresaLocal.findParametroEmpresaByTipoparametroId(tipoParametroIf.getId());
			if ( parametros.size() == 0 )
				throw new GenericBusinessException("No existen parametros para compras por Internet !!");
			Map<String, String> mapaParametros = new HashMap<String, String>();
			for ( ParametroEmpresaIf pe : parametros ){
				mapaParametros.put(pe.getCodigo(), pe.getValor());
			}
			
			int indice = 0;
			String codigoEmpresa = datos.get(indice++);					//****** 0
			Collection<EmpresaIf> empresas = empresaLocal.findEmpresaByCodigo(codigoEmpresa);
			if ( empresas.size() == 0 )
				throw new GenericBusinessException("No se encuentra Empresa con código \""+codigoEmpresa+"\"");
			EmpresaIf empresaIf = empresas.iterator().next();
			
			
			String tipoOperador = mapaParametros.get("TIPOOPERADORNUEVOCLIENTEINTERNET");
			if ( tipoOperador == null )
				throw new GenericBusinessException("No se encuentra Parametro con código \"TIPOOPERADORNUEVOCLIENTEINTERNET\"");
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("codigo", tipoOperador);
			mapa.put("empresaId", empresaIf.getId());
			Collection<TipoClienteIf> tiposClientes = tipoClienteLocal.findTipoClienteByQuery(mapa);
			if ( tiposClientes.size() == 0 )
				throw new GenericBusinessException("No existe tipo de cliente con codigo "+tipoOperador);
			TipoClienteIf tipoClienteIf = tiposClientes.iterator().next();
			
			
			String tipoIdentificacion = datos.get(indice++); 					//****** 1
			Collection<TipoIdentificacionIf> tiposIdentificacion = tipoIdentificacionLocal.findTipoIdentificacionByCodigo(tipoIdentificacion);
			if ( tiposIdentificacion.size() == 0 )
				throw new GenericBusinessException("No existe tipo de identificacion con código "+tipoIdentificacion);
			TipoIdentificacionIf tipoIdentificacionIf = tiposIdentificacion.iterator().next();
			
			
			String identificacion = datos.get(indice++);					//****** 2
			
			String nombres = datos.get(indice++);					//****** 3
			if (nombres == null || "".equals(nombres) )
				throw new GenericBusinessException("Nombres Vacios");
			
			
			String apellidos = datos.get(indice++);					//****** 4
			if (apellidos == null || "".equals(apellidos) )
				throw new GenericBusinessException("Apellidos Vacios");
			
			String direccion = datos.get(indice++);					//****** 5
			
			String telefono = datos.get(indice++);					//****** 6
			
			String email = datos.get(indice++);					//****** 7
			
			String corporacion = mapaParametros.get("CORPORACIONNUEVOCLIENTEINTERNET");
			if ( tipoOperador == null )
				throw new GenericBusinessException("No se encuentra Parametro con código \"CORPORACIONNUEVOCLIENTEINTERNET\"");
			mapa = new HashMap<String, Object>();
			mapa.put("codigo", corporacion);
			mapa.put("empresaId", empresaIf.getId());
			Collection<CorporacionIf> corporaciones = corporacionLocal.findCorporacionByQuery(mapa);
			if ( corporaciones.size() == 0 )
				throw new GenericBusinessException("No existe corporacion con codigo "+corporacion);
			CorporacionIf corporacionIf = corporaciones.iterator().next();
			
			String usuarioFinal = mapaParametros.get("USUARIOFINALNUEVOCLIENTEINTERNE");
			if ( usuarioFinal == null )
				throw new GenericBusinessException("No se encuentra Parametro con código \"USUARIOFINALNUEVOCLIENTEINTERNE\"");
			usuarioFinal = "".equals(usuarioFinal) ? "N" : usuarioFinal ;
			
			String contribuyenteEspecial = mapaParametros.get("CONTRIBUYENTEESPECIALNUEVOCLIENTEINTERNE");
			if ( contribuyenteEspecial == null )
				throw new GenericBusinessException("No se encuentra Parametro con código \"CONTRIBUYENTEESPECIALNUEVOCLIENTEINTERNE\"");
			contribuyenteEspecial = "".equals(contribuyenteEspecial) ? "N" : contribuyenteEspecial ;
			
			String tipoPersona = mapaParametros.get("TIPOPERSONANUEVOCLIENTEINTERNET");
			if ( tipoPersona == null )
				throw new GenericBusinessException("No se encuentra Parametro con código \"TIPOPERSONANUEVOCLIENTEINTERNET\"");
			tipoPersona = "".equals(tipoPersona) ? "N" : tipoPersona ;
			
			String llevaContabilidad = mapaParametros.get("LLEVACONTABILIDADNUEVOCLIENTEINTERNET");
			if ( llevaContabilidad == null )
				throw new GenericBusinessException("No se encuentra Parametro con código \"LLEVACONTABILIDADNUEVOCLIENTEINTERNET\"");
			llevaContabilidad = "".equals(llevaContabilidad) ? "N" : llevaContabilidad ;
			
			String observacion = "CLIENTE INGRESADO DESDE INTERNET";
			
			String ciudadClienteOficina = mapaParametros.get("CIUDADNUEVOCLIENTEINTERNET");
			if ( ciudadClienteOficina == null )
				throw new GenericBusinessException("No se encuentra Parametro con código \"CIUDADNUEVOCLIENTEINTERNET\"");
			Collection<CiudadIf> ciudades = ciudadLocal.findCiudadByCodigo(ciudadClienteOficina);
			if ( ciudades.size() == 0 )
				throw new GenericBusinessException("No existe ciudad con codigo "+ciudadClienteOficina);
			CiudadIf ciudadIf = ciudades.iterator().next();
			
			ClienteIf clienteIf = new ClienteData();
			clienteIf.setTipoclienteId(tipoClienteIf.getId());
			clienteIf.setNombreLegal(apellidos+""+nombres);
			clienteIf.setRazonSocial(apellidos+""+nombres);
			clienteIf.setTipoidentificacionId(tipoIdentificacionIf.getId());
			clienteIf.setIdentificacion(identificacion);
			clienteIf.setCorporacionId(corporacionIf.getId());
			clienteIf.setUsuariofinal(usuarioFinal);
			clienteIf.setContribuyenteEspecial(contribuyenteEspecial);
			clienteIf.setTipoPersona(tipoPersona);
			clienteIf.setLlevaContabilidad(llevaContabilidad);
			clienteIf.setObservacion(observacion);
			
			
			ClienteOficinaIf clienteOficinaIf = new ClienteOficinaData();
			clienteOficinaIf.setCodigo("001");
			clienteOficinaIf.setDescripcion(apellidos+""+nombres);
			clienteOficinaIf.setCiudadId(ciudadIf.getId());
			clienteOficinaIf.setDireccion(direccion.toUpperCase());
			clienteOficinaIf.setEmail(email);
			
			Long telefonoL = null;
			try{
				telefonoL = Long.valueOf(telefono.replaceAll(" ", ""));
			} catch( Exception e ){  }
			
			clienteOficinaIf.setTelefono(telefono!=null?String.valueOf(telefonoL):"2");
			
			ArrayList<ClienteOficinaIf> listaClienteOficina = new ArrayList<ClienteOficinaIf>();
			listaClienteOficina.add(clienteOficinaIf);
			
			clienteLocal.procesarCliente(clienteIf, new ArrayList<ClienteZonaIf>(), new ArrayList<ClienteRetencionIf>(), listaClienteOficina, 
					new HashMap(), new HashMap() , true);
			
			return true;
			
		} catch (GenericBusinessException e){
			e.printStackTrace();
			System.out.println(" ***** ERROR: "+e.getMessage());
		} catch (Exception e){
			e.printStackTrace();
			System.out.println(" ***** ERROR: ERROR GENERAL EN INGRESO DE CLIENTE !!");
		}
		return false;
	}
	
	private long sumarPuntosComprometidos(TarjetaIf tarjeta , long puntos){
		
		long puntosComprometidos = tarjeta.getPuntosComprometidos() != null ?
			tarjeta.getPuntosComprometidos().longValue() : 0 ;
			
		return puntosComprometidos + puntos; 
	}
	
	private long calcularPuntosDisponibles(TarjetaIf tarjeta){
		
		long puntosAcumulados = tarjeta.getPuntosAcumulados() != null ?
			tarjeta.getPuntosAcumulados().longValue() : 0;
		
		long puntosUsados = tarjeta.getPuntosUtilizados() != null?
			tarjeta.getPuntosUtilizados().longValue() : 0 ;
		
		long puntosComprometidos = tarjeta.getPuntosComprometidos() != null ?
			tarjeta.getPuntosComprometidos().longValue() : 0 ;
			
		return puntosAcumulados - puntosUsados - puntosComprometidos; 
	}

	private LoteIf obtenerLote(ProductoIf productoIf) throws GenericBusinessException {
		Collection<LoteIf> lotes = loteLocal.findLoteByProductoId(productoIf.getId());
		if ( lotes.size() == 0 )
			throw new GenericBusinessException(" ***** ERROR: No existe lote para producto con código "+productoIf.getCodigo());
		else if ( lotes.size() >1 )
			System.out.println(" ***** ERROR: Producto con código "+productoIf.getCodigo()+" tiene mas de un registro de lote");
		return lotes.iterator().next();
	}
			
	
	private ProductoIf verificarProducto(String codigoBarras,Map<String,ProductoIf> mapaProductos) throws GenericBusinessException, GeneralSecurityException{
		
		ProductoIf p = mapaProductos.get(codigoBarras);
		if ( p == null ){
			Collection<ProductoIf> productos = productoLocal.findProductoByCodigoBarras(codigoBarras);
			if ( productos.size() == 0 )
				throw new GenericBusinessException(" ***** ERROR: No existe producto con Codigo de Barras \""+codigoBarras+"\" !!");
			else if ( productos.size() > 1 )
				throw new GenericBusinessException(" ***** ERROR: Existe mas de un producto con Codigo de Barras \""+codigoBarras+"\" !!");
			p = productos.iterator().next();
			mapaProductos.put(codigoBarras, p);
		}
		return p;
	}
	
	private synchronized String verificarProductoDescripcion(String codigoBarras,Map<String, String> mapaProductosDescripcion,int tipoDescripcion) throws GenericBusinessException{
		
		String d = mapaProductosDescripcion.get(codigoBarras);
		if ( d == null ){
			d = productoLocal.findDescripcionUnoProductoByCodigoBarra(codigoBarras,tipoDescripcion);
			mapaProductosDescripcion.put(codigoBarras, d);
		}
		return d;
	}

	private synchronized String extraerValorDeNodo(Object o) {
		
		String valorString = "";
		
		ElementNSImpl e = (ElementNSImpl)o;
		NodeList listaNodo = e.getElementsByTagName("value");
		//System.out.println("   -Lista tamañaño: ) "+listaNodo.getLength());
		Node nodo = listaNodo.item(0);
		//System.out.println("   -Nodo 0: ) "+nodo);
		Node primerNodo = nodo.getFirstChild();
		//System.out.println("   -Nodo Primero: ) "+primerNodo);
		if ( primerNodo != null )
			valorString = primerNodo.getNodeValue(); 
		//System.out.println("   -Valor: ) "+valorString);
		return valorString;
	}

	private String getCodigoPedido(java.sql.Date fechaPedido) {
		String codigo = "";
		String anioPedido = utilitariosLocal.getYearFromDate(fechaPedido);
		codigo += anioPedido + "-";
		return codigo;
	}

	private Date convertirFecha(String fechaString) throws GenericBusinessException{
		try{
			Date fechaFinal = null;
	
			int indice = 4;
			String anioS = fechaString.substring(0, indice);
			indice += 1;
			String mesS = fechaString.substring(indice, indice+2);
			indice += (2+1);
			String diaS = fechaString.substring(indice, indice+2);
			indice += (2+1);
			String horaS = fechaString.substring(indice, indice+2);
			indice += (2+1);
			String minutoS = fechaString.substring(indice, indice+2);
			indice += (2+1);
			String segundosS = fechaString.substring(indice, indice+2);
	
			int anio = Integer.valueOf(anioS);
			int mes = Integer.valueOf(mesS)-1;
			int dia = Integer.valueOf(diaS);
			int hora = Integer.valueOf(horaS);
			int minuto = Integer.valueOf(minutoS);
			int segundos = Integer.valueOf(segundosS);
	
			Calendar cal = new GregorianCalendar(anio,mes,dia,hora,minuto,segundos);
			fechaFinal = new Date(cal.getTimeInMillis());

			return fechaFinal;
		} catch(Exception e){
			throw new GenericBusinessException("Error en la conversión de la fecha !!");
		}
	}
	
	public GiftcardEJB findGiftcardByCodigoBarras(String codigoBarras,String estado) {
		GiftcardEJB objeto = null;
		try {
			Map<String,Object> mapa = new HashMap<String, Object>();
			mapa.put("estado", estado);
			mapa.put("codigoBarras", codigoBarras);
			System.out.println("codigoBarras: "+codigoBarras);
			Collection<GiftcardEJB> gifts = giftCardLocal.findGiftcardByQuery(mapa);
			if (gifts.size() > 0){
				for ( GiftcardEJB gc : gifts ) {
					objeto=gc;
				}
				return objeto;
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return objeto;
    }
	
	public GiftcardEJB getGiftcard(Long id) {
		GiftcardEJB giftcard = new GiftcardEJB();
		try {
			giftcard = (GiftcardEJB) giftCardLocal.getGiftcard(id);
			return giftcard;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return giftcard;
    }
	
	public TarjetaEJB findTarjetaByCodigoBarras(String codigoBarras,String estado) {
		TarjetaEJB objeto = null;
		try {
			Map<String,Object> mapa = new HashMap<String, Object>();
			mapa.put("estado", estado);
			mapa.put("codigo", codigoBarras);
			System.out.println("codigoBarras: "+codigoBarras);
			Collection<TarjetaEJB> tarjetas = tarjetaLocal.findTarjetaByQuery(mapa);
			if (tarjetas.size() > 0){
				for ( TarjetaEJB gc : tarjetas ) {
					objeto=gc;
				}
				return objeto;
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return objeto;
    }
	
	public TarjetaEJB getTarjeta(Long id) {
		TarjetaEJB tarjeta = new TarjetaEJB();
		try {
			tarjeta = (TarjetaEJB) tarjetaLocal.getTarjeta(id);
			return tarjeta;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return tarjeta;
    }
	
	public TarjetaEJB findTarjetaAfiliacion(
			String identificacion,String codigoOficina,Long empresaId){
		try {
			Collection<TarjetaEJB> tarjetas = tarjetaLocal.findTarjetaByIdentificacionByCodigoOficinaByEmpresaId(identificacion, codigoOficina, empresaId);
			System.out.println("----Tamaño Afiliacion: "+tarjetas.size());
			Iterator it = tarjetas.iterator();
			if (it.hasNext()) {
				TarjetaEJB tarjeta = (TarjetaEJB) it.next();
				return tarjeta;
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void procesarTransferenciaTarjetaAfiliacion(Long oficinaId, Long empresaId, Long usuarioId) {
		try {
			MovimientoIf movimiento = movimientoLocal.getMovimientoTransferenciaTarjetaAfiliacion(oficinaId, empresaId, usuarioId, "E");
			List<MovimientoDetalleIf> movimientoDetalleList = movimientoLocal.getMovimientoDetalleTransferenciaTarjetaAfiliacion(oficinaId, empresaId, "E");
			UsuarioIf usuario = usuarioLocal.getUsuario(usuarioId);
			movimientoLocal.procesarMovimiento(movimiento, movimientoDetalleList, false, true, usuario);
			movimiento = movimientoLocal.getMovimientoTransferenciaTarjetaAfiliacion(oficinaId, empresaId, usuarioId, "I");
			movimientoDetalleList = movimientoLocal.getMovimientoDetalleTransferenciaTarjetaAfiliacion(oficinaId, empresaId, "I");
			movimientoLocal.procesarMovimiento(movimiento, movimientoDetalleList, false, true, usuario);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
}
