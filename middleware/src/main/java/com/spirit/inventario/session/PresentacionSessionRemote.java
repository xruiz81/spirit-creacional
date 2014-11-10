package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.PresentacionSessionService;
import com.spirit.inventario.session.generated._PresentacionSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface PresentacionSessionRemote extends PresentacionSessionService,_PresentacionSessionService {
}
