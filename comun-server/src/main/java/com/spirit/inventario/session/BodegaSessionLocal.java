package com.spirit.inventario.session;

import javax.ejb.Local;
import com.spirit.inventario.session.BodegaSessionService;
import com.spirit.inventario.session.generated._BodegaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Local
public interface BodegaSessionLocal extends BodegaSessionService,_BodegaSessionService{
}
