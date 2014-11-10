package com.spirit.pos.session;



import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.pos.entity.PagoTarjetaIf;
import com.spirit.pos.session.generated._PagoTarjetaSessionService;

/**
 * The <code>PagoTarjetaSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:30 $
 */
public interface PagoTarjetaSessionService extends _PagoTarjetaSessionService{

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	 public Long procesarPagoTarjetaCredito(PagoTarjetaIf pagotarjeta) throws GenericBusinessException ;

 
}
