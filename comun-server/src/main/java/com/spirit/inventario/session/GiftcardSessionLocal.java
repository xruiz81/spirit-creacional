package com.spirit.inventario.session;

import java.util.List;

import javax.ejb.Local;

import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.GiftcardIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Local
public interface GiftcardSessionLocal extends GiftcardSessionService{
	public java.util.Collection findGiftcardByEmpresaId(java.lang.Long idEmpresa) throws GenericBusinessException;	
	public java.util.Collection findProductoGiftcardByEmpresaId(java.lang.Long idEmpresa) throws GenericBusinessException;
	public void registrarGiftcardList(List<GiftcardIf> giftcardList) throws GenericBusinessException;
	public java.util.Collection findGiftcardByQueryWebService(Long empresaId,String codigoBarras,String estado) throws GenericBusinessException;
	public GiftcardIf findGiftcardByIdWebService(Long empresaId, Long id) throws GenericBusinessException;
}
