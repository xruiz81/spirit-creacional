package com.spirit.bpm.session;

import javax.ejb.Remote;

/**
 * The <code>JbpmManagerSessionRemote</code>bean, allows the session ejb to be called remote.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:45 $
 *
 */
@Remote
public interface JbpmManagerSessionRemote extends JbpmManagerSessionService  {
}
