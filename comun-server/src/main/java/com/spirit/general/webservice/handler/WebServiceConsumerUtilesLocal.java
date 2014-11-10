package com.spirit.general.webservice.handler;

import java.util.Map;

import javax.ejb.Local;

import com.spirit.exception.GenericBusinessException;

@Local
public interface WebServiceConsumerUtilesLocal {

	
	public String getParametro(Long empresaId,String codigoParametro) throws GenericBusinessException;
	public Map<String,String> getParametros(Long empresaId) throws GenericBusinessException;
	
}
