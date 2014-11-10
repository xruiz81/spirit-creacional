package com.spirit.pos.session;

import javax.ejb.Remote;

/**
 * The <code>VentasConsolidadoSessionRemote</code>bean, allows the session ejb to be called remote.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:30 $
 *
 */
@Remote
public interface VentasConsolidadoSessionRemote extends VentasConsolidadoSessionService  {
}
