package com.spirit.pos.session;

import javax.ejb.Remote;
import com.spirit.pos.session.TarjetaTransaccionSessionService;
import com.spirit.pos.session.generated._TarjetaTransaccionSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface TarjetaTransaccionSessionRemote extends TarjetaTransaccionSessionService,_TarjetaTransaccionSessionService {
}
