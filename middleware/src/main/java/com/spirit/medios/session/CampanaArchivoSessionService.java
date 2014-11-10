package com.spirit.medios.session;

import com.spirit.medios.entity.CampanaArchivoEJB;
import com.spirit.medios.entity.CampanaArchivoIf;
import com.spirit.medios.session.generated._CampanaArchivoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CampanaArchivoSessionService extends _CampanaArchivoSessionService{

	public CampanaArchivoEJB registrarCampanaArchivo(CampanaArchivoIf modelArchivo,String urlCarpetaSevidor) throws com.spirit.exception.GenericBusinessException;
}
