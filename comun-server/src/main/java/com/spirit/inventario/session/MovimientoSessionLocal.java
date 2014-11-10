package com.spirit.inventario.session;

import javax.ejb.Local;
import com.spirit.inventario.session.MovimientoSessionService;
import com.spirit.inventario.session.generated._MovimientoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Local
public interface MovimientoSessionLocal extends MovimientoSessionService,_MovimientoSessionService{
}
