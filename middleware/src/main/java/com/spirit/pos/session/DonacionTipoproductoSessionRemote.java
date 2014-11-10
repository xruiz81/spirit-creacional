package com.spirit.pos.session;

import javax.ejb.Remote;
import com.spirit.pos.session.DonacionTipoproductoSessionService;
import com.spirit.pos.session.generated._DonacionTipoproductoSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface DonacionTipoproductoSessionRemote extends DonacionTipoproductoSessionService,_DonacionTipoproductoSessionService {
}
