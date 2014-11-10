package com.spirit.medios.session;

import java.util.List;

import com.spirit.medios.entity.HerramientasMediosIf;
import com.spirit.medios.session.generated._HerramientasMediosSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface HerramientasMediosSessionService extends _HerramientasMediosSessionService{
	
	public void procesarHerramientasMedios(List<HerramientasMediosIf> herramientasColeccion, List<HerramientasMediosIf> herramientasColeccionEliminado) throws com.spirit.exception.GenericBusinessException;
}
