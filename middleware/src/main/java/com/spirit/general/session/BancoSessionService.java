package com.spirit.general.session;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.generated._BancoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface BancoSessionService extends _BancoSessionService {
	java.util.Collection findBancosDeCuentaBancariasPorEmpresa(java.lang.Long idEmpresa) throws GenericBusinessException;
}
