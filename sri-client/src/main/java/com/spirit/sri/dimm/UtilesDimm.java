package com.spirit.sri.dimm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoParametroIf;

public class UtilesDimm {

	public static void refrescarParametroDirectorio(Long idEmpresa) throws GenericBusinessException{
		
		TipoParametroIf tp = getTipoParametroDimm(idEmpresa);
		Map<String,Object> mapaParametro = new HashMap<String,Object>();
		//mapaParametro.put("empresaId",Parametros.getIdEmpresa());
		mapaParametro.put("empresaId",idEmpresa);
		mapaParametro.put("tipoparametroId",tp.getId());
		mapaParametro.put("codigo","DIRECTORIOSRIDIMM");
		//mapaParametro.put("codigo",parametro.toUpperCase());
		Collection<ParametroEmpresaIf> parametros = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(mapaParametro);
		if ( parametros.size() == 0 )
			throw new GenericBusinessException("Parametro de empresa con valor \"DIRECTORIOSRIDIMM\" no existe !!");
		else if ( parametros.size() > 1 )
			throw new GenericBusinessException("Existe mas de una parametro de Empresa llamado DIRECTORIOSRIDIMM !!");
		for (ParametroEmpresaIf pe : parametros){
			Parametros.setRutaCarpetaSriDimm(pe.getValor());
		}
		
		ParametroEmpresaIf pe = getParametroDimm(idEmpresa, DimmConstantes.DIRECTORIO_DIMM);
		Parametros.setRutaCarpetaSriDimm(pe.getValor());
	}
	
	public static ParametroEmpresaIf getParametroDimm(Long idEmpresa,String codigoParametro) throws GenericBusinessException{
		
		TipoParametroIf tp = getTipoParametroDimm(idEmpresa);
		Map<String,Object> mapaParametro = new HashMap<String,Object>();
		mapaParametro.put("empresaId",idEmpresa);
		mapaParametro.put("tipoparametroId",tp.getId());
		mapaParametro.put("codigo",codigoParametro);
		Collection<ParametroEmpresaIf> parametros = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(mapaParametro);
		if ( parametros.size() == 0 )
			throw new GenericBusinessException("Parametro de empresa con valor \""+codigoParametro+"\" no existe !!");
		else if ( parametros.size() > 1 )
			throw new GenericBusinessException("Existe mas de una parametro de Empresa llamado "+codigoParametro+" !!");
		ParametroEmpresaIf pe = parametros.iterator().next();
		return pe;
	}
	
	private static TipoParametroIf getTipoParametroDimm(Long idEmpresa) throws GenericBusinessException{
		Map<String,Object> mapaTipo = new HashMap<String,Object>();
		mapaTipo.put("empresaId",idEmpresa);
		mapaTipo.put("codigo",DimmConstantes.TIPO_PARAMETRO_DIMM);
		Collection<TipoParametroIf> tipos = SessionServiceLocator.getTipoParametroSessionService().findTipoParametroByQuery(mapaTipo);
		if ( tipos.size() == 0  )
			throw new GenericBusinessException("No existe tipo de parametro DIRECTORIO !!");
		TipoParametroIf tp = tipos.iterator().next();
		return tp;
	}
	
}
