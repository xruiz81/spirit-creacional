package com.spirit.medios.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.medios.entity.ReunionCompromisoEJB;
import com.spirit.medios.entity.ReunionCompromisoIf;
import com.spirit.medios.session.generated._ReunionCompromisoSession;

/**
 * The <code>ReunionCompromisoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class ReunionCompromisoSessionEJB extends _ReunionCompromisoSession implements ReunionCompromisoSessionRemote, ReunionCompromisoSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ReunionCompromisoEJB registrarReunionCompromiso(ReunionCompromisoIf modelReunionCompromiso) {
		ReunionCompromisoEJB reunionCompromiso = new ReunionCompromisoEJB();
		
		reunionCompromiso.setId(modelReunionCompromiso.getId());
		reunionCompromiso.setReunionId(modelReunionCompromiso.getReunionId());
		reunionCompromiso.setFecha(modelReunionCompromiso.getFecha());
		reunionCompromiso.setDescripcion(modelReunionCompromiso.getDescripcion());
		reunionCompromiso.setEstado(modelReunionCompromiso.getEstado());
		reunionCompromiso.setTemaTratado(modelReunionCompromiso.getTemaTratado());
		
		return reunionCompromiso;
	}
   
}
