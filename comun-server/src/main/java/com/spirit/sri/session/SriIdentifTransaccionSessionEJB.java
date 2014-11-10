package com.spirit.sri.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.sri.session.generated._SriIdentifTransaccionSession;

/**
 * The <code>SriIdentifTransaccionSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:17 $
 *
 */
@Stateless
public class SriIdentifTransaccionSessionEJB extends _SriIdentifTransaccionSession implements SriIdentifTransaccionSessionRemote,SriIdentifTransaccionSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(SriIdentifTransaccionSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


}
