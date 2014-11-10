package com.spirit.pos.session;

import javax.ejb.Remote;
import com.spirit.pos.session.DonaciondetalleSessionService;
import com.spirit.pos.session.generated._DonaciondetalleSessionService;
/**
 *
 * @author  www.versality.com.ec
 *
 */
@Remote
public interface DonaciondetalleSessionRemote extends DonaciondetalleSessionService,_DonaciondetalleSessionService {
}
