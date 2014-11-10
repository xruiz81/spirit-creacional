package com.spirit.inventario.session;

import javax.ejb.Local;
import com.spirit.inventario.session.LoteSessionService;
import com.spirit.inventario.session.generated._LoteSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Local
public interface LoteSessionLocal extends LoteSessionService,_LoteSessionService{
}
