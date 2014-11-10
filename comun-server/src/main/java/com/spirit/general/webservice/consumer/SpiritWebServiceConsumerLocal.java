package com.spirit.general.webservice.consumer;

import java.util.Collection;

import javax.ejb.Local;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.webservice.SpiritWebServiceRemote;
import com.spirit.inventario.entity.GiftcardEJB;
import com.spirit.pos.entity.TarjetaEJB;

@Local
public interface SpiritWebServiceConsumerLocal {

	//Ejemplo
	public Collection consultarWSGiftCardByQuery(Long empresaId,String codigoBarras,String estado) throws GenericBusinessException;
	public GiftcardEJB consultarWSGiftCardById(Long empresaId, Long id) throws GenericBusinessException;
	
	public Collection consultarWSTarjetaByQuery(Long empresaId,String codigoBarras,String estado) throws GenericBusinessException;
	public TarjetaEJB consultarWSTarjetaById(Long empresaId, Long id) throws GenericBusinessException;
	public Collection getWSStockModificado(Long empresaId, int intervaloMinutos, boolean obtenerTodos) throws GenericBusinessException;
	
	public TarjetaEJB findTarjetaByidentificacionClienteByCodigoOficinaByEmpresaId(String identificacion,String codigoOficina,Long empresaId) throws GenericBusinessException;
	
	public void procesarEgresoTransferenciaTarjetaAfiliacion(Long oficinaId, Long empresaId, Long usuarioId) throws GenericBusinessException;
	
	//Uso Basico
	public SpiritWebServiceRemote getPort(String targetNameSpace,String name,String endpointURI) throws GenericBusinessException;
	
}
