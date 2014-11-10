package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.GenericoSessionService;
import com.spirit.inventario.session.generated._GenericoSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface GenericoSessionRemote extends GenericoSessionService,_GenericoSessionService {
}
