package com.spirit.contabilidad.session;

import java.util.Iterator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoDetalleTmpIf;
import com.spirit.contabilidad.session.generated._AsientoDescuadradoSession;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class AsientoDescuadradoSessionEJB extends _AsientoDescuadradoSession implements AsientoDescuadradoSessionRemote,AsientoDescuadradoSessionLocal  {
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	@EJB private AsientoSessionLocal asientoSessionLocal;
	@EJB private AsientoDetalleSessionLocal asientoDetalleSessionLocal;
	@EJB private AsientoTmpSessionLocal asientoTmpSessionLocal;
	@EJB private AsientoDetalleTmpSessionLocal asientoDetalleTmpSessionLocal;
	
   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	/*public void restaurarAsiento(String asientoNumero) throws GenericBusinessException {
		System.out.println(asientoNumero);
		Iterator<AsientoTmpIf> asientoTmpIterator = asientoTmpSessionLocal.findAsientoTmpByNumero(asientoNumero).iterator();
		if (asientoTmpIterator.hasNext()) {
			AsientoTmpIf asientoTmp = asientoTmpIterator.next();
			Iterator<AsientoDetalleTmpIf> asientoDetalleTmpIterator = asientoDetalleTmpSessionLocal.findAsientoDetalleTmpByAsientoId(asientoTmp.getId()).iterator();
			while (asientoDetalleTmpIterator.hasNext()) {
				AsientoDetalleTmpIf asientoDetalleTmp = asientoDetalleTmpIterator.next();
				AsientoDetalleIf asientoDetalle = asientoDetalleSessionLocal.getAsientoDetalle(asientoDetalleTmp.getId());
				AsientoIf asiento = asientoSessionLocal.getAsiento(asientoDetalleTmp.getId());
				if (asiento != null) {
					asientoDetalle.setAsientoId(asientoDetalleTmp.getAsientoId());
					asientoDetalleSessionLocal.saveAsientoDetalle(asientoDetalle);
				}
			}
		}
	}*/
	
	public void restaurarAsiento() throws GenericBusinessException {
		Iterator<AsientoDetalleTmpIf> asientoDetalleTmpIterator = asientoDetalleTmpSessionLocal.getAsientoDetalleTmpList().iterator();
		while (asientoDetalleTmpIterator.hasNext()) {
			AsientoDetalleTmpIf asientoDetalleTmp = asientoDetalleTmpIterator.next();
			AsientoDetalleIf asientoDetalle = asientoDetalleSessionLocal.getAsientoDetalle(asientoDetalleTmp.getId());
			if (asientoDetalle != null) {
				asientoDetalle.setAsientoId(asientoDetalleTmp.getAsientoId());
				asientoDetalleSessionLocal.saveAsientoDetalle(asientoDetalle);
			}
		}
	}
}
