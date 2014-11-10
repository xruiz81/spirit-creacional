package com.spirit.general.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.generated._BancoSession;

/**
 * The <code>BancoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:16 $
 *
 */
@Stateless
public class BancoSessionEJB extends _BancoSession implements BancoSessionRemote  {
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findBancosDeCuentaBancariasPorEmpresa(java.lang.Long idEmpresa) throws GenericBusinessException {
		//select distinct b.* from BANCO b, CUENTA_BANCARIA cb where b.ID = cb.BANCO_ID and cb.EMPRESA_ID = 1 and cb.CUENTA_CLIENTE = 'N' order by b.NOMBRE asc
		String queryString = "select distinct b from BancoEJB b, CuentaBancariaEJB cb where b.id = cb.bancoId and cb.empresaId = :idEmpresa and cb.cuentaCliente = 'N' order by b.nombre asc";
		Query query = manager.createQuery(queryString);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}
}
