package com.spirit.contabilidad.session;

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

import com.spirit.contabilidad.entity.SubtipoAsientoEJB;
import com.spirit.contabilidad.session.generated._SubtipoAsientoSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>SubTipoAsientoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class SubTipoAsientoSessionEJB extends _SubtipoAsientoSession implements SubTipoAsientoSessionRemote, SubTipoAsientoSessionLocal {

	@PersistenceContext(unitName="spirit")
	private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(SubTipoAsientoSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
   
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findSubtipoAsientoByEmpresaId(Long idEmpresa) {
		//select distinct sa.* from SUBTIPO_ASIENTO sa, TIPO_ASIENTO ta where sa.TIPO_ID = ta.ID and ta.EMPRESA_ID = 1
		String queryString = "select distinct sa from SubtipoAsientoEJB sa, TipoAsientoEJB ta where sa.tipoId = ta.id and ta.empresaId = " + idEmpresa;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

}
