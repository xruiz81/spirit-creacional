package com.spirit.medios.session;

import com.spirit.crm.entity.ClienteContactoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.medios.entity.ReunionAsistenteEJB;
import com.spirit.medios.entity.ReunionAsistenteIf;
import com.spirit.medios.session.generated._ReunionAsistenteSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ReunionAsistenteSessionService extends _ReunionAsistenteSessionService{

	public ReunionAsistenteEJB registrarReunionAsistenteAgencia(ReunionAsistenteIf modelReunionAsistenteAgencia, EmpleadoIf modelEmpleado) throws com.spirit.exception.GenericBusinessException;
	public ReunionAsistenteEJB registrarReunionAsistenteCliente(ReunionAsistenteIf modelReunionAsistenteCliente, ClienteContactoIf modelClienteContacto) throws com.spirit.exception.GenericBusinessException;
	public ReunionAsistenteEJB registrarReunionAsistenteProspectoCliente(ReunionAsistenteIf modelReunionAsistenteProspectoCliente, String modelProspectoCliente) throws com.spirit.exception.GenericBusinessException;
}
