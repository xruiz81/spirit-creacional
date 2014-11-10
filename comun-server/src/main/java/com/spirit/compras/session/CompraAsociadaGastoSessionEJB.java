package com.spirit.compras.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.compras.entity.CompraAsociadaGastoEJB;
import com.spirit.compras.entity.CompraAsociadaGastoIf;
import com.spirit.compras.entity.CompraGastoIf;
import com.spirit.compras.session.generated._CompraAsociadaGastoSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>CompraAsociadaGastoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:13 $
 *
 */
@Stateless
public class CompraAsociadaGastoSessionEJB extends _CompraAsociadaGastoSession implements CompraAsociadaGastoSessionRemote,CompraAsociadaGastoSessionLocal  {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/


	public java.util.Collection<CompraAsociadaGastoIf> findCompraAsociadaGastoByCompraIdDeGasto(CompraGastoIf compraGasto) throws GenericBusinessException {
		try{
			Long compraId = compraGasto.getCompraId() ;
			Long compraGastoId = compraGasto.getId();
			String queryString = "select cag from CompraAsociadaGastoEJB cag,CompraGastoEJB cg, CompraEJB c" +
					" where cag.compraGastoId = cg.id and cg.compraId = c.id "+
					" and c.id = " + compraId+" and cg.id = "+compraGastoId;
			Query query = manager.createQuery(queryString);
			return query.getResultList();
		} catch( Exception e ){
			e.printStackTrace();
			throw new GenericBusinessException("Error al consultar Compras Asociadas de la Compra !!");
		}
	}

}
