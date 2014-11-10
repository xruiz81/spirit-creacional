package com.spirit.medios.session;

import com.spirit.medios.entity.ReunionArchivoEJB;
import com.spirit.medios.entity.ReunionArchivoIf;
import com.spirit.medios.session.generated._ReunionArchivoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ReunionArchivoSessionService extends _ReunionArchivoSessionService{

	public ReunionArchivoEJB registrarReunionArchivo(ReunionArchivoIf modelReunionArchivo, String urlCarpetaSevidor) throws com.spirit.exception.GenericBusinessException;
}
