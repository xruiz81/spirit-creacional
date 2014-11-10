package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.SolicitudTransferenciaSessionService;
import com.spirit.inventario.session.generated._SolicitudTransferenciaSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface SolicitudTransferenciaSessionRemote extends SolicitudTransferenciaSessionService,_SolicitudTransferenciaSessionService {
}
