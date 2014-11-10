package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.MovimientoSessionService;
import com.spirit.inventario.session.generated._MovimientoSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface MovimientoSessionRemote extends MovimientoSessionService,_MovimientoSessionService {
}
