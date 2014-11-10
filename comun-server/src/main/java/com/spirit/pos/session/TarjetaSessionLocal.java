package com.spirit.pos.session;

import javax.ejb.Local;
import com.spirit.pos.session.TarjetaSessionService;
import com.spirit.pos.session.generated._TarjetaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Local
public interface TarjetaSessionLocal extends TarjetaSessionService,_TarjetaSessionService{
}
