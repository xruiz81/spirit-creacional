package com.spirit.general.mdb.server;

import java.util.List;

import javax.ejb.Local;

import com.spirit.exception.GenericBusinessException;
import com.spirit.poscola.entity.PosColaIf;

@Local
public interface QueryIf {

	public PosColaIf obtenerInfoPosCola(String posName)
			throws GenericBusinessException;

	public PosColaIf obtenerInfoColaPrincipal() throws GenericBusinessException;

	public PosColaIf obtenerInfoPosColaByOficinaId(Long oficinaId)
			throws GenericBusinessException;

	public PosColaIf obtenerInfoPosColaByBodegaID(Long bodegaId)
			throws GenericBusinessException;

	public List<PosColaIf> obtenerAllPosCola() throws GenericBusinessException;
	
	public PosColaIf obtenerInfoColaYO() throws GenericBusinessException;
	
	public List<PosColaIf> findTodosMenosPrincipalYoParametro(String posName) throws GenericBusinessException;
}
