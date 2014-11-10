package com.spirit.crm.session;



import com.spirit.crm.entity.ClienteIndicadorEJB;
import com.spirit.crm.entity.ClienteIndicadorIf;
import com.spirit.crm.session.generated._ClienteIndicadorSessionService;

public interface ClienteIndicadorSessionService extends _ClienteIndicadorSessionService{

	public ClienteIndicadorEJB registrarClienteIndicador(ClienteIndicadorIf modelClienteIndicador) throws com.spirit.exception.GenericBusinessException;

}
