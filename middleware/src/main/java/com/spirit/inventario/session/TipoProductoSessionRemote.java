package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.TipoProductoSessionService;
import com.spirit.inventario.session.generated._TipoProductoSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface TipoProductoSessionRemote extends TipoProductoSessionService,_TipoProductoSessionService {
}
