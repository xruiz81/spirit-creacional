package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.EmbalajeProductoSessionService;
import com.spirit.inventario.session.generated._EmbalajeProductoSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface EmbalajeProductoSessionRemote extends EmbalajeProductoSessionService,_EmbalajeProductoSessionService {
}
