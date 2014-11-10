package com.spirit.general.webservice.handler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.session.ParametroEmpresaSessionLocal;
import com.spirit.general.session.TipoParametroSessionLocal;

@Stateless
public class WebServiceConsumerUtiles implements WebServiceConsumerUtilesLocal{

	@PersistenceContext(unitName = "spirit")
	protected EntityManager manager;
	
	
	@EJB private TipoParametroSessionLocal tipoParametroLocal;
	@EJB private ParametroEmpresaSessionLocal parametroEmpresaLocal;
	
	public TipoParametroIf getTipoParametroNomina(Long empresaId) throws GenericBusinessException{
		Collection<TipoParametroIf> tiposParametros = null;
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("empresaId", empresaId);
		mapa.put("codigo", WebServiceConsumerParameters.TIPO_PARAMETRO );
		tiposParametros = tipoParametroLocal.findTipoParametroByQuery(mapa);
		if ( tiposParametros.size() == 1 ){
			return tiposParametros.iterator().next();
		} 
		return null;
	}
	
	public String getParametro(Long empresaId,String codigoParametro) throws GenericBusinessException{
		
		String resultado = null;
		TipoParametroIf tipoParametro = getTipoParametroNomina(empresaId);
		if ( tipoParametro != null ){
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("tipoparametroId", tipoParametro.getId());
			mapa.put("empresaId", empresaId);
			mapa.put("codigo", codigoParametro);
			Collection<ParametroEmpresaIf> parametros = 
				parametroEmpresaLocal.findParametroEmpresaByQuery(mapa);
			for ( ParametroEmpresaIf pe : parametros ){
				resultado = pe.getValor();
			}

		}
		return resultado;
	}
	
	public Map<String,String> getParametros(Long empresaId) throws GenericBusinessException{
		
		Map<String,String> resultado = new HashMap<String, String>();
		TipoParametroIf tipoParametro = getTipoParametroNomina(empresaId);
		if ( tipoParametro != null ){
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("tipoparametroId", tipoParametro.getId());
			mapa.put("empresaId", empresaId);
			Collection<ParametroEmpresaIf> parametros = 
				parametroEmpresaLocal.findParametroEmpresaByQuery(mapa);
			for ( ParametroEmpresaIf pe : parametros ){
				resultado.put(pe.getCodigo(),pe.getValor());
			}
		}
		return resultado;
	}
	
}
