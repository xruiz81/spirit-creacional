package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.LoteSessionService;
import com.spirit.inventario.session.generated._LoteSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface LoteSessionRemote extends LoteSessionService,_LoteSessionService {
}
