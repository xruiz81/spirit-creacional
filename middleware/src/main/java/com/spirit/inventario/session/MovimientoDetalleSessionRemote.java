package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.MovimientoDetalleSessionService;
import com.spirit.inventario.session.generated._MovimientoDetalleSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface MovimientoDetalleSessionRemote extends MovimientoDetalleSessionService,_MovimientoDetalleSessionService {
}
