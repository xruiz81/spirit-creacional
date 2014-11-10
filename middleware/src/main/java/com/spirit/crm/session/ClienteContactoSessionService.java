package com.spirit.crm.session;



import java.util.Collection;
import java.util.Map;

import com.spirit.crm.entity.ClienteContactoEJB;
import com.spirit.crm.entity.ClienteContactoIf;
import com.spirit.crm.session.generated._ClienteContactoSessionService;


public interface ClienteContactoSessionService extends _ClienteContactoSessionService{

	Collection getClienteContactoList(int startIndex, int endIndex, Long idCliente)	throws com.spirit.exception.GenericBusinessException;
	Collection getClienteContactoList(int startIndex, int endIndex, Map aMap) throws com.spirit.exception.GenericBusinessException;
	Collection getClienteContactoList(int startIndex, int endIndex, Map aMap, Long idCliente) throws com.spirit.exception.GenericBusinessException;	
	int getClienteContactoListSize(Long idCliente)throws com.spirit.exception.GenericBusinessException;
	int getClienteContactoListSize(Map aMap)throws com.spirit.exception.GenericBusinessException;
	int getClienteContactoListSize(Map aMap , Long idCliente)throws com.spirit.exception.GenericBusinessException;
	public ClienteContactoEJB registrarClienteContacto(ClienteContactoIf modelClienteContacto) throws com.spirit.exception.GenericBusinessException;


}
