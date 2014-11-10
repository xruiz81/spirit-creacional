package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.StockDiaSessionService;
import com.spirit.inventario.session.generated._StockDiaSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface StockDiaSessionRemote extends StockDiaSessionService,_StockDiaSessionService {
}
