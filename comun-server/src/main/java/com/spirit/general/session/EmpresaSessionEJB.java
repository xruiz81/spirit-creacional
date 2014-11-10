package com.spirit.general.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaEJB;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.NumeradoresEJB;
import com.spirit.general.entity.NumeradoresIf;
import com.spirit.general.entity.TipoClienteEJB;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.session.generated._EmpresaSession;
import com.spirit.server.QueryBuilder;

/**
 * The <code>EmpresaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class EmpresaSessionEJB extends _EmpresaSession implements EmpresaSessionRemote, EmpresaSessionLocal {
	
	
	@EJB
	private NumeradoresSessionLocal numeradoresLocal;
	
	@EJB
	private TipoClienteSessionLocal tipoClienteLocal;

	@Resource private SessionContext ctx;
	
	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findEmpresaByQuery(int startIndex, int endIndex,Map aMap) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from EmpresaEJB " + objectName + " where "
		+ where;
		Query query = manager.createQuery(queryString);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			String property = (String) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
			
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getEmpresaListSize(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from EmpresaEJB " + objectName + " where "
		+ where;
		Query query = manager.createQuery(queryString);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			String property = (String) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
			
		}
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void procesarEmpresa(EmpresaIf model,List<NumeradoresIf> modelNumeradoresList, List<TipoClienteIf> modelOperadoresNegocioList) throws GenericBusinessException {
		try {
			EmpresaEJB empresa = registrarEmpresa(model);
			manager.persist(empresa);
			
			for (NumeradoresIf modelNumerador : modelNumeradoresList) {
				modelNumerador.setEmpresaId(empresa.getPrimaryKey());
				NumeradoresEJB numerador = registrarNumerador(modelNumerador);
				manager.merge(numerador);
			}
			
			for (TipoClienteIf modelOperadorNegocio : modelOperadoresNegocioList) {
				modelOperadorNegocio.setEmpresaId(empresa.getPrimaryKey());
				TipoClienteEJB operadorNegocio = registrarOperadorNegocio(modelOperadorNegocio);
				manager.merge(operadorNegocio);
			}
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al insertar informaci\u00f3n en Empresa-Numeradores-TipoCliente");
		}
	}
	
	private EmpresaEJB registrarEmpresa(EmpresaIf model) {
		EmpresaEJB empresa = new EmpresaEJB();
		empresa.setId(model.getId());
		empresa.setCodigo(model.getCodigo());
		empresa.setNombre(model.getNombre());
		empresa.setLogo(model.getLogo());
		empresa.setRuc(model.getRuc());
		empresa.setWeb(model.getWeb());
		empresa.setEmailContador(model.getEmailContador());
		empresa.setRucContador(model.getRucContador());
		empresa.setTipoIdRepresentante(model.getTipoIdRepresentante());
		empresa.setNumeroIdentificacion(model.getNumeroIdentificacion());
		
		return empresa;
	}
	
	private NumeradoresEJB registrarNumerador(NumeradoresIf modelNumerador) {
		NumeradoresEJB numerador = new NumeradoresEJB();
		
		numerador.setId(modelNumerador.getId());
		numerador.setUltimoValor(modelNumerador.getUltimoValor());
		numerador.setNombreTabla(modelNumerador.getNombreTabla());
		numerador.setEmpresaId(modelNumerador.getEmpresaId());
		
		return numerador;
	}
	
	private TipoClienteEJB registrarOperadorNegocio(TipoClienteIf modelOperadorNegocio) {
		TipoClienteEJB operadorNegocio = new TipoClienteEJB();
		
		operadorNegocio.setId(modelOperadorNegocio.getId());
		operadorNegocio.setCodigo(modelOperadorNegocio.getCodigo());
		operadorNegocio.setNombre(modelOperadorNegocio.getNombre());
		operadorNegocio.setEmpresaId(modelOperadorNegocio.getEmpresaId());
		
		return operadorNegocio;
	}
	
	public void eliminarEmpresa(Long idEmpresa) throws GenericBusinessException {
		try {
			EmpresaEJB data = manager.find(EmpresaEJB.class, idEmpresa);
			
			Collection<NumeradoresIf> modelNumeradoresList = numeradoresLocal.findNumeradoresByEmpresaId(idEmpresa);
			Collection<TipoClienteIf> modelTipoClienteList = tipoClienteLocal.findTipoClienteByEmpresaId(idEmpresa);
			
			for (NumeradoresIf modelNumeradores : modelNumeradoresList) {				
				manager.remove(modelNumeradores);
			}
			
			for (TipoClienteIf modelTipoCliente : modelTipoClienteList) {				
				manager.remove(modelTipoCliente);
			}			
			manager.remove(data);
			manager.flush();
			
		} catch (Exception e) {
			throw new GenericBusinessException("Error al eliminar informaci\u00f3n en Empresa");
		}
	}
	
}
