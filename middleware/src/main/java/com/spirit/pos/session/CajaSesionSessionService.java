package com.spirit.pos.session;



import java.math.BigDecimal;
import java.util.*;

import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.pos.entity.CajaSesionData;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.session.generated._CajaSesionSessionService;

/**
 * The <code>CajaSesionSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:30 $
 */
public interface CajaSesionSessionService extends _CajaSesionSessionService{

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	public void activarCaja(CajaSesionIf cajasesionIf) throws GenericBusinessException;
	public void crearCaja(CajaSesionIf cajasesionIf) throws GenericBusinessException;
	
	public void cerrarCaja(String cajero,CajaSesionIf cajaSesionIf,HashMap<String, BigDecimal> mapaAsientos,Long idEmpresa,Long idOficina) throws GenericBusinessException;
	public void generarAsientos(HashMap<String, BigDecimal> mapaAsientos,
			Long idEmpresa, Long idOficina,String cajero);

}
