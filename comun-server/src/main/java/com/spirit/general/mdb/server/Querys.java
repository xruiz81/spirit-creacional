package com.spirit.general.mdb.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.spirit.exception.GenericBusinessException;
import com.spirit.poscola.entity.PosColaIf;
import com.spirit.poscola.session.PosColaSessionLocal;

@Stateless
public class Querys implements QueryIf {
	@EJB
	private PosColaSessionLocal posColaSessionLocal;

	private Map<String, Object> mapaQuery = new HashMap<String, Object>();

	public PosColaIf obtenerInfoPosCola(String posName)
			throws GenericBusinessException {
		List lista = (List) posColaSessionLocal.findPosColaByPosName(posName);
		// mapaQuery.put("", );
		PosColaIf posCola = null;
		if (lista != null && lista.size() > 0) {
			posCola = (PosColaIf) lista.get(0);
		}
		return posCola;
	}

	public PosColaIf obtenerInfoColaPrincipal() throws GenericBusinessException {
		List lista = (List) posColaSessionLocal.findPosColaByTipoServer("P");
		PosColaIf posCola = null;
		if (lista != null && lista.size() > 0) {
			posCola = (PosColaIf) lista.get(0);
		}
		return posCola;
	}

	public PosColaIf obtenerInfoPosColaByOficinaId(Long oficinaId)
			throws GenericBusinessException {
		List lista = (List) posColaSessionLocal
				.findPosColaByOficinaId(oficinaId);
		PosColaIf posCola = null;
		if (lista != null && lista.size() > 0) {
			posCola = (PosColaIf) lista.get(0);
		}
		return posCola;
	}

	public PosColaIf obtenerInfoPosColaByBodegaID(Long bodegaId)
			throws GenericBusinessException {
		List lista = (List) posColaSessionLocal.findPosColaByBodegaId(bodegaId);
		PosColaIf posCola = null;
		if (lista != null && lista.size() > 0) {
			posCola = (PosColaIf) lista.get(0);
		}
		return posCola;
	}

	public List<PosColaIf> obtenerAllPosCola() throws GenericBusinessException {
		return (List<PosColaIf>) posColaSessionLocal.findPosColaByTipoServer("E");
	}

	public PosColaIf obtenerInfoColaYO() throws GenericBusinessException {
		List lista = (List) posColaSessionLocal.findPosColaByMe("1");
		PosColaIf posCola = null;
		if (lista != null && lista.size() > 0) {
			posCola = (PosColaIf) lista.get(0);
		}
		return posCola;
	}
	
	public List<PosColaIf> findTodosMenosPrincipalYoParametro(String posName) throws GenericBusinessException {
		return (List<PosColaIf>)posColaSessionLocal.findTodosMenosPrincipalYoParametro(posName);
	}
}
