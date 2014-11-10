package com.spirit.general.webservice;

import javax.ejb.Remote;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.spirit.inventario.entity.GiftcardEJB;
import com.spirit.pos.entity.TarjetaEJB;

@Remote
@WebService
@SOAPBinding(style = Style.RPC)
public interface SpiritWebServiceRemote {

	@WebMethod
	@WebResult(name="listaStockModificado")
	public Object[][] getStockModificado(
			@WebParam(name = "IntervaloDeMinutos") int intervaloMinutos,
			@WebParam(name = "ObtenerTodos") boolean obtenerTodos);
	
	
	public boolean ingresarCompraPorInternet(Object[][] pedido);
	
	@WebMethod
	@WebResult(name="puntosDisponiblesDeTarjetaAfiliacion")
	public long getPuntosDisponiblesDeTarjetaAfiliacion(
			@WebParam(name = "tipoDeIdentificacion") String tipoIdentificacion, 
			@WebParam(name = "identificacion") String identificacion );
	
	@WebMethod
	@WebResult(name="actualizarPuntosComprometidos")
	public boolean actualizarPuntosComprometidos(
			@WebParam(name = "tipoDeIdentificacion") String tipoIdentificacion , 
			@WebParam(name = "identificacion") String identificacion , 
			@WebParam(name = "puntos") long puntos );
	
	@WebMethod
	@WebResult(name="ingresarClienteBasico")
	public boolean ingresarClienteBasico(
			@WebParam(name = "arregloDeDatos") Object[] datos);
	
	@WebMethod
	@WebResult(name="findGiftcardByCodigoBarras")
	public GiftcardEJB findGiftcardByCodigoBarras(
			@WebParam(name = "codigoBarras") String codigoBarras,
			@WebParam(name = "estado") String estado);
	
	@WebMethod
	@WebResult(name="findGiftcardById")
	public GiftcardEJB getGiftcard (
			@WebParam(name = "id") Long id);
	
	@WebMethod
	@WebResult(name="findTarjetaByCodigoBarras")
	public TarjetaEJB findTarjetaByCodigoBarras(
			@WebParam(name = "codigo") String codigoBarras,
			@WebParam(name = "estado") String estado);
	
	@WebMethod
	@WebResult(name="findTarjetaById")
	public TarjetaEJB getTarjeta (
			@WebParam(name = "id") Long id);
	
	@WebMethod
	@WebResult(name="findTarjetaAfiliacion")
	public TarjetaEJB findTarjetaAfiliacion(
			@WebParam(name = "identificacion") String identificacion,
			@WebParam(name = "codigoOficina") String codigoOficina,
			@WebParam(name = "empresaId") Long empresaId);	
	
	@WebMethod
	@Oneway
	public void procesarTransferenciaTarjetaAfiliacion(
			@WebParam(name = "oficinaId") Long oficinaId,
			@WebParam(name = "empresaId") Long empresaId,
			@WebParam(name = "usuarioId") Long usuarioId);
}
