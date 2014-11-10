package com.spirit.inventario.session;

import javax.ejb.Local;
import com.spirit.inventario.session.GenericoSessionService;
import com.spirit.inventario.session.generated._GenericoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Local
public interface GenericoSessionLocal extends GenericoSessionService,_GenericoSessionService{
}
