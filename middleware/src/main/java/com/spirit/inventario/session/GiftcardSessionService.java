package com.spirit.inventario.session;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.GiftcardIf;
import com.spirit.inventario.session.generated._GiftcardSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface GiftcardSessionService extends _GiftcardSessionService{
	public Collection findGiftcardByEmpresaId(java.lang.Long idEmpresa) throws GenericBusinessException;
	public Collection findProductoGiftcardByEmpresaId(java.lang.Long idEmpresa) throws GenericBusinessException;
	public void registrarGiftcardList(List<GiftcardIf> giftcardList) throws GenericBusinessException;
	
	public java.util.Collection findGiftcardByQueryWebService(Long empresaId,String codigoBarras,String estado) throws GenericBusinessException;
	public GiftcardIf findGiftcardByIdWebService(Long empresaId, Long id) throws GenericBusinessException;
}
