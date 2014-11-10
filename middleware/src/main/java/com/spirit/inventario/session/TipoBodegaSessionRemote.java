package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.TipoBodegaSessionService;
import com.spirit.inventario.session.generated._TipoBodegaSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface TipoBodegaSessionRemote extends TipoBodegaSessionService,_TipoBodegaSessionService {
}
