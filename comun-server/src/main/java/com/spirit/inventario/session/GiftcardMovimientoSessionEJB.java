package com.spirit.inventario.session;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.inventario.session.GiftcardMovimientoSessionRemote;
import com.spirit.inventario.session.generated._GiftcardMovimientoSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class GiftcardMovimientoSessionEJB extends _GiftcardMovimientoSession implements GiftcardMovimientoSessionRemote,GiftcardMovimientoSessionLocal  {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(GiftcardMovimientoSessionEJB.class);

	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public BigDecimal getValorMinimoGiftcardHistorial(Long idgiftCard) 
	throws com.spirit.exception.GenericBusinessException 
	{
		BigDecimal countResult=new BigDecimal("0.00");
		Query countQuery = manager.createQuery("select min(gc.saldoFinal) from GiftcardMovimientoEJB gc where gc.giftcardId='" + idgiftCard+"'");
		List countQueryResult = countQuery.getResultList();
		if(countQueryResult.get(0)!=null)			
			countResult = (BigDecimal) countQueryResult.get(0);
		//log.debug("The list size is: " + countResult.intValue());
		return countResult;
	}
}
