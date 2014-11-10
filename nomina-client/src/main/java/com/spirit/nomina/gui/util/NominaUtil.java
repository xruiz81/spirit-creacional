package com.spirit.nomina.gui.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.spirit.client.Parametros;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.nomina.handler.NominaParametros;

public class NominaUtil {

	
	public static Map<String, String> getParametrosMapa(boolean esBaseCodigo,String... nombresParametros) throws GenericBusinessException{
		Map<String, String> mapaResultado = new HashMap<String, String>();
		TipoParametroIf tipoParametro = getTipoParametroNomina();
		if ( tipoParametro != null ){
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("tipoparametroId", tipoParametro.getId());
			mapa.put("empresaId", Parametros.getIdEmpresa());
			if ( nombresParametros!=null && nombresParametros.length>0 ){
				for (String nombre : nombresParametros){
					if ( esBaseCodigo )
						mapa.put("codigo",nombre+"%" );
					else 
						mapa.put("codigo", nombre);
					Collection<ParametroEmpresaIf> parametros = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(mapa);
					for ( ParametroEmpresaIf pe : parametros ){
						mapaResultado.put(pe.getCodigo(), pe.getValor());
					}
				}
			} else {
				Collection<ParametroEmpresaIf> parametros = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(mapa);
				for ( ParametroEmpresaIf pe : parametros ){
					mapaResultado.put(pe.getCodigo(), pe.getValor());
				}
			}	
		}
		return mapaResultado;
	}
	
	
	public static Set<String> getParametrosSet(boolean esBaseCodigo,String... nombresParametros) throws GenericBusinessException{
		
		Set<String> setResultado = new HashSet<String>();
		TipoParametroIf tipoParametro = getTipoParametroNomina();
		if ( tipoParametro != null ){
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("tipoparametroId", tipoParametro.getId());
			mapa.put("empresaId", Parametros.getIdEmpresa());
			if ( nombresParametros!=null && nombresParametros.length>0 ){
				for (String nombre : nombresParametros){
					if ( esBaseCodigo )
						mapa.put("codigo",nombre+"%" );
					else 
						mapa.put("codigo", nombre);
					Collection<ParametroEmpresaIf> parametros = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(mapa);
					for ( ParametroEmpresaIf pe : parametros ){
						setResultado.add(pe.getValor());
					}
				}
			} else {
				Collection<ParametroEmpresaIf> parametros = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(mapa);
				for ( ParametroEmpresaIf pe : parametros ){
					setResultado.add(pe.getValor());
				}
			}	
		}
		return setResultado;
	}
	
	private static TipoParametroIf getTipoParametroNomina() throws GenericBusinessException{
		Collection<TipoParametroIf> tiposParametros = null;
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("empresaId", Parametros.getIdEmpresa());
		mapa.put("codigo", NominaParametros.TIPO_PARAMETRO );
		tiposParametros = SessionServiceLocator.getTipoParametroSessionService().findTipoParametroByQuery(mapa);
		if ( tiposParametros.size() == 1 ){
			return tiposParametros.iterator().next();
		} 
		return null;
	}
	
}
