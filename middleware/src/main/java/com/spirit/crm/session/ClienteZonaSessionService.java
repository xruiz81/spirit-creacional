package com.spirit.crm.session;

import com.spirit.crm.entity.ClienteZonaEJB;
import com.spirit.crm.entity.ClienteZonaIf;
import com.spirit.crm.session.generated._ClienteZonaSessionService;
import com.spirit.exception.GenericBusinessException;

public interface ClienteZonaSessionService extends _ClienteZonaSessionService{

	public ClienteZonaEJB registrarClienteZona(ClienteZonaIf modelClienteZona, boolean save) throws com.spirit.exception.GenericBusinessException;
	public ClienteZonaEJB registrarClienteZona(ClienteZonaIf modelClienteZona) throws com.spirit.exception.GenericBusinessException;
	public String getMaximoCodigoClienteZona(Long clienteId) throws GenericBusinessException;
	
}
