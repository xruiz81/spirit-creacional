package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.ModeloSessionService;
import com.spirit.inventario.session.generated._ModeloSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface ModeloSessionRemote extends ModeloSessionService,_ModeloSessionService {
}
