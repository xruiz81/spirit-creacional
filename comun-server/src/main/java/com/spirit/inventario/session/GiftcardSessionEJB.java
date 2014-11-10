package com.spirit.inventario.session;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.webservice.consumer.SpiritWebServiceConsumerLocal;
import com.spirit.inventario.entity.GiftcardIf;
import com.spirit.inventario.session.generated._GiftcardSession;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class GiftcardSessionEJB extends _GiftcardSession implements GiftcardSessionRemote,GiftcardSessionLocal  {
	
	@EJB private SpiritWebServiceConsumerLocal swsLocal;
	
   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findGiftcardByEmpresaId(java.lang.Long idEmpresa) throws GenericBusinessException {
		//select * from GIFTCARD g, PRODUCTO p, GENERICO ge where g.PRODUCTO_ID = p.ID and p.GENERICO_ID = g.ID and ge.EMPRESA_ID = 1
		String queryString = "select distinct g from GiftcardEJB g, ProductoEJB p, GenericoEJB ge where g.productoId = p.id and p.genericoId = ge.id and ge.empresaId = " + idEmpresa + " order by g.codigoBarras asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public java.util.Collection findProductoGiftcardByEmpresaId(java.lang.Long idEmpresa) throws GenericBusinessException {
		//select * from PRODUCTO p, GENERICO g, TIPO_PRODUCTO tp where p.GENERICO_ID = g.ID and g.TIPOPRODUCTO_ID = tp.ID and tp.CODIGO = 'GC' and tp.EMPRESA_ID = 1 and p.PERMITEVENTA = 'S'
		String queryString = "select distinct p from ProductoEJB p, GenericoEJB g, TipoProductoEJB tp where p.genericoId = g.id and g.tipoproductoId = tp.id and tp.codigo = 'GC' and tp.empresaId = " + idEmpresa + " and p.permiteventa = 'S'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	public void registrarGiftcardList(List<GiftcardIf> giftcardList) throws GenericBusinessException {
		Iterator it = giftcardList.iterator();
		while (it.hasNext()) {
			GiftcardIf giftcard = (GiftcardIf) it.next();
			System.out.println(giftcard.getCodigoBarras());
			addGiftcard(giftcard);
		}
	}
	
	
	public java.util.Collection findGiftcardByQueryWebService(Long empresaId,String codigoBarras,String estado) throws GenericBusinessException{
		
		return swsLocal.consultarWSGiftCardByQuery(empresaId, codigoBarras,estado);
		//return null;
		
	}
	
	public GiftcardIf findGiftcardByIdWebService(Long empresaId, Long id) throws GenericBusinessException{
		
		return swsLocal.consultarWSGiftCardById(empresaId, id);
		//return null;
		
	}
	
	
}
