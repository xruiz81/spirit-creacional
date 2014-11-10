package com.spirit.general.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.general.session.generated._TipoEmpleadoSession;

/**
 * The <code>TipoEmpleadoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class TipoEmpleadoSessionEJB extends _TipoEmpleadoSession implements TipoEmpleadoSessionRemote,TipoEmpleadoSessionLocal  {


	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findTipoEmpleadoByCodigoAndByEmpresaId(String codigo, Long idEmpresa) {
		String objectName = "t";
		String queryString = "from TipoEmpleadoEJB " + objectName
		+ " where t.codigo = '" + codigo + "' and t.empresaId = " + idEmpresa;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
		
	}
	
}
