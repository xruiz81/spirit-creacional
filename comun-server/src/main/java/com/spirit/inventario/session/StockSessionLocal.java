package com.spirit.inventario.session;

import javax.ejb.Local;
import com.spirit.inventario.session.StockSessionService;
import com.spirit.inventario.session.generated._StockSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Local
public interface StockSessionLocal extends StockSessionService,_StockSessionService{
}
