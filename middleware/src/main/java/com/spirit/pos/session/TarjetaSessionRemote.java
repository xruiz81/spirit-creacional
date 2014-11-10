package com.spirit.pos.session;

import javax.ejb.Remote;
import com.spirit.pos.session.TarjetaSessionService;
import com.spirit.pos.session.generated._TarjetaSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface TarjetaSessionRemote extends TarjetaSessionService,_TarjetaSessionService {
}
