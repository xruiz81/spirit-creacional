package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.MedidaSessionService;
import com.spirit.inventario.session.generated._MedidaSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface MedidaSessionRemote extends MedidaSessionService,_MedidaSessionService {
}
