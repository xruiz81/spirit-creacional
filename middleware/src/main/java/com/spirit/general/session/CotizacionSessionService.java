package com.spirit.general.session;

import com.spirit.general.session.generated._CotizacionSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CotizacionSessionService extends _CotizacionSessionService{
    java.util.Collection findCotizacionByMonedaAndByMonedaEquivalente(java.lang.Long monedaId,java.lang.Long monedaEquivId) throws com.spirit.exception.GenericBusinessException;

}
