package com.spirit.compras.session;

import com.spirit.compras.entity.CompraAsociadaGastoIf;
import com.spirit.compras.entity.CompraGastoIf;
import com.spirit.compras.session.generated._CompraAsociadaGastoSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CompraAsociadaGastoSessionService extends _CompraAsociadaGastoSessionService{

	public java.util.Collection<CompraAsociadaGastoIf> findCompraAsociadaGastoByCompraIdDeGasto(CompraGastoIf compraGasto) throws GenericBusinessException ;
}
