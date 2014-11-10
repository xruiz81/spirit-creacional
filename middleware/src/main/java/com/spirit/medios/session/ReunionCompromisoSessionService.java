package com.spirit.medios.session;

import com.spirit.medios.entity.ReunionCompromisoEJB;
import com.spirit.medios.entity.ReunionCompromisoIf;
import com.spirit.medios.session.generated._ReunionCompromisoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ReunionCompromisoSessionService extends _ReunionCompromisoSessionService{

	public ReunionCompromisoEJB registrarReunionCompromiso(ReunionCompromisoIf modelReunionCompromiso) throws com.spirit.exception.GenericBusinessException;
}
