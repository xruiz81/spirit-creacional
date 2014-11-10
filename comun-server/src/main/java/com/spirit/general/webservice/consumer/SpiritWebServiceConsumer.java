package com.spirit.general.webservice.consumer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.TipoClienteSessionLocal;
import com.spirit.general.webservice.SpiritWebServiceRemote;
import com.spirit.general.webservice.handler.WebServiceConsumerParameters;
import com.spirit.general.webservice.handler.WebServiceConsumerUtilesLocal;
import com.spirit.inventario.entity.GiftcardEJB;
import com.spirit.inventario.entity.GiftcardIf;
import com.spirit.pos.entity.TarjetaEJB;
import com.spirit.pos.entity.TarjetaIf;

@Stateless
public class SpiritWebServiceConsumer implements SpiritWebServiceConsumerLocal {

	@PersistenceContext(unitName = "spirit")
	protected EntityManager manager;
	
	
	@EJB private WebServiceConsumerUtilesLocal webServiceUtilesLocal;
	
	@EJB private TipoClienteSessionLocal tipoClienteLocal;
	
	public Collection consultarWSGiftCardByQuery(Long empresaId,String codigoBarras,String estado) throws GenericBusinessException{
		
		Map<String, String> mapaParametros = webServiceUtilesLocal.getParametros(empresaId);
		
		String endpointURI = mapaParametros.get(WebServiceConsumerParameters.CODIGO_ENDPOINTURI);
		String targetNameSpace = mapaParametros.get(WebServiceConsumerParameters.CODIGO_TARGETNAMESPACE);
		String name = mapaParametros.get(WebServiceConsumerParameters.CODIGO_NAME);
		
		try {
			SpiritWebServiceRemote swr = getPort(targetNameSpace,name,endpointURI); 
			GiftcardEJB giftcard = swr.findGiftcardByCodigoBarras(codigoBarras,estado);
			Collection<GiftcardIf> gifts = new ArrayList<GiftcardIf>();
			if (giftcard != null)
				gifts.add(giftcard);
			return gifts;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error general en Consulta de Gift Card !!");
		}

	}
	
	public GiftcardEJB consultarWSGiftCardById(Long empresaId, Long id) throws GenericBusinessException{
		
		Map<String, String> mapaParametros = webServiceUtilesLocal.getParametros(empresaId);
		
		String endpointURI = mapaParametros.get(WebServiceConsumerParameters.CODIGO_ENDPOINTURI);
		String targetNameSpace = mapaParametros.get(WebServiceConsumerParameters.CODIGO_TARGETNAMESPACE);
		String name = mapaParametros.get(WebServiceConsumerParameters.CODIGO_NAME);
		
		try {
			SpiritWebServiceRemote swr = getPort(targetNameSpace,name,endpointURI); 
			GiftcardEJB giftcard = swr.getGiftcard(id);
			return giftcard;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error general en Consulta de Gift Card !!");
		}

	}
	
	public Collection consultarWSTarjetaByQuery(Long empresaId,String codigoBarras,String estado) throws GenericBusinessException{
		
		Map<String, String> mapaParametros = webServiceUtilesLocal.getParametros(empresaId);
		
		String endpointURI = mapaParametros.get(WebServiceConsumerParameters.CODIGO_ENDPOINTURI);
		String targetNameSpace = mapaParametros.get(WebServiceConsumerParameters.CODIGO_TARGETNAMESPACE);
		String name = mapaParametros.get(WebServiceConsumerParameters.CODIGO_NAME);
		
		try {
			SpiritWebServiceRemote swr = getPort(targetNameSpace,name,endpointURI); 
			TarjetaEJB tarjeta = swr.findTarjetaByCodigoBarras(codigoBarras,estado);
			Collection<TarjetaIf> tarjetas = new ArrayList<TarjetaIf>();
			if (tarjetas != null)
				tarjetas.add(tarjeta);
			return tarjetas;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error general en Consulta de Tarjeta de Afiliación !!");
		}

	}
	
	public Collection getWSStockModificado(Long empresaId, int intervaloMinutos,boolean obtenerTodos) throws GenericBusinessException{
		Map<String, String> mapaParametros = webServiceUtilesLocal.getParametros(empresaId);
		
		String endpointURI = mapaParametros.get(WebServiceConsumerParameters.CODIGO_ENDPOINTURI);
		String targetNameSpace = mapaParametros.get(WebServiceConsumerParameters.CODIGO_TARGETNAMESPACE);
		String name = mapaParametros.get(WebServiceConsumerParameters.CODIGO_NAME);
		
		try {
			SpiritWebServiceRemote swr = getPort(targetNameSpace,name,endpointURI); 
			Object[][] stock = swr.getStockModificado(intervaloMinutos, obtenerTodos);
			Collection<Object[][]> stockModificado = new ArrayList<Object[][]>();
			if (stockModificado != null)
				stockModificado.add(stock);
			return stockModificado;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error general en Consulta de Stock Modificado !!");
		}
	}
	
	public TarjetaEJB consultarWSTarjetaById(Long empresaId, Long id) throws GenericBusinessException{
		
		Map<String, String> mapaParametros = webServiceUtilesLocal.getParametros(empresaId);
		
		String endpointURI = mapaParametros.get(WebServiceConsumerParameters.CODIGO_ENDPOINTURI);
		String targetNameSpace = mapaParametros.get(WebServiceConsumerParameters.CODIGO_TARGETNAMESPACE);
		String name = mapaParametros.get(WebServiceConsumerParameters.CODIGO_NAME);
		
		try {
			SpiritWebServiceRemote swr = getPort(targetNameSpace,name,endpointURI); 
			TarjetaEJB tarjeta = swr.getTarjeta(id);
			return tarjeta;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error general en Consulta de Tarjeta de Afiliación !!");
		}

	}
	
	public SpiritWebServiceRemote getPort(String targetNameSpace,String name,String endpointURI) throws GenericBusinessException {
		if  ( targetNameSpace == null || "".equals(targetNameSpace.trim()) )
			throw new GenericBusinessException("Sin TargetNameSpace para Consumo Web !!");
		if  ( name == null || "".equals(name.trim()) )
			throw new GenericBusinessException("Sin Name para Consumo Web !!");
		if  ( endpointURI == null || "".equals(endpointURI.trim()) )
			throw new GenericBusinessException("Sin EndPointUri para Consumo Web !!");
		try {   
			QName serviceName = new QName(targetNameSpace,name);
			URL wsdlURL = new URL(endpointURI);
			Service service = Service.create(wsdlURL, serviceName);
			return service.getPort(SpiritWebServiceRemote.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new GenericBusinessException("URL mal formada");
		}  catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		}
	}
	
	public TarjetaEJB findTarjetaByidentificacionClienteByCodigoOficinaByEmpresaId(String identificacion,String codigoOficina,Long empresaId) throws GenericBusinessException{
		
		Map<String, String> mapaParametros = webServiceUtilesLocal.getParametros(empresaId);
		
		String endpointURI = mapaParametros.get(WebServiceConsumerParameters.CODIGO_ENDPOINTURI);
		String targetNameSpace = mapaParametros.get(WebServiceConsumerParameters.CODIGO_TARGETNAMESPACE);
		String name = mapaParametros.get(WebServiceConsumerParameters.CODIGO_NAME);
		
		try {
			SpiritWebServiceRemote swr = getPort(targetNameSpace,name,endpointURI);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("targetNameSpace: " + targetNameSpace);
			System.out.println("name: " + name);
			System.out.println("endpointURI: " + endpointURI);
			System.out.println("identificacion: " + identificacion);
			System.out.println("codigoOficina: " + codigoOficina);
			System.out.println("empresaId" + empresaId);
			TarjetaEJB tarjeta = (TarjetaEJB) swr.findTarjetaAfiliacion(identificacion, codigoOficina, empresaId);
			//TarjetaEJB tarjeta = null;
			return tarjeta;
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error general en Consulta de Tarjeta de Afiliación !!");
		}
	}
	
public void procesarEgresoTransferenciaTarjetaAfiliacion(Long oficinaId, Long empresaId, Long usuarioId) throws GenericBusinessException{
		
		Map<String, String> mapaParametros = webServiceUtilesLocal.getParametros(empresaId);
		
		String endpointURI = mapaParametros.get(WebServiceConsumerParameters.CODIGO_ENDPOINTURI);
		String targetNameSpace = mapaParametros.get(WebServiceConsumerParameters.CODIGO_TARGETNAMESPACE);
		String name = mapaParametros.get(WebServiceConsumerParameters.CODIGO_NAME);
		
		try {
			SpiritWebServiceRemote swr = getPort(targetNameSpace,name,endpointURI);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("targetNameSpace: " + targetNameSpace);
			System.out.println("name: " + name);
			System.out.println("endpointURI: " + endpointURI);
			System.out.println("oficinaId: " + oficinaId);
			System.out.println("empresaId: " + empresaId);
			System.out.println("usuarioId: " + usuarioId);
			swr.procesarTransferenciaTarjetaAfiliacion(oficinaId, empresaId, usuarioId);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error general en transferencia de tarjeta de afiliación !!");
		}
	}
	
}
