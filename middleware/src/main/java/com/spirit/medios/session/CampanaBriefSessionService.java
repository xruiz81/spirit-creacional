package com.spirit.medios.session;

import com.spirit.medios.entity.CampanaBriefEJB;
import com.spirit.medios.entity.CampanaBriefIf;
import com.spirit.medios.session.generated._CampanaBriefSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CampanaBriefSessionService extends _CampanaBriefSessionService{

	public CampanaBriefEJB registrarCampanaBrief(CampanaBriefIf modelBrief,String urlCarpetaSevidor) throws com.spirit.exception.GenericBusinessException;
}
