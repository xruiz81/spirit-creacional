package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.BodegaSessionService;
import com.spirit.inventario.session.generated._BodegaSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface BodegaSessionRemote extends BodegaSessionService,_BodegaSessionService {
}
