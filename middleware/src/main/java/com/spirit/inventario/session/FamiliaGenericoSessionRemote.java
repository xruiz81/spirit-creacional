package com.spirit.inventario.session;

import javax.ejb.Remote;
import com.spirit.inventario.session.FamiliaGenericoSessionService;
import com.spirit.inventario.session.generated._FamiliaGenericoSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface FamiliaGenericoSessionRemote extends FamiliaGenericoSessionService,_FamiliaGenericoSessionService {
}
