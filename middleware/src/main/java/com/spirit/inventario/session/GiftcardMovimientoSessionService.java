package com.spirit.inventario.session;

import java.math.BigDecimal;

import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.session.generated._GiftcardMovimientoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface GiftcardMovimientoSessionService extends _GiftcardMovimientoSessionService{
	/*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *
    *******************************************************************************************************************/
	public BigDecimal getValorMinimoGiftcardHistorial(Long idgiftCard) throws GenericBusinessException ;
}
