package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.EmbalajeSessionService;
import com.spirit.inventario.session.generated._EmbalajeSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface EmbalajeSessionRemote extends EmbalajeSessionService,_EmbalajeSessionService {
}
