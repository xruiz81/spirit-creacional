package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.StockSessionService;
import com.spirit.inventario.session.generated._StockSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface StockSessionRemote extends StockSessionService,_StockSessionService {
}
