package com.spirit.poscola.session;

import javax.ejb.Local;

/**
 * The <code>PosColaSessionRemote</code>bean, allows the session ejb to be called remote.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:24 $
 *
 */
@Local
public interface PosColaSessionLocal extends PosColaSessionService  {
}
