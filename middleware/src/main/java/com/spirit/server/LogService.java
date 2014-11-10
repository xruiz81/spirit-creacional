package com.spirit.server;

/**
 * A class to get an instance for a logger object.
 * @author Ronald Kramp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:32 $
 */
public final class LogService {
   
   /**
    * Get an instance of a logger object.
    * @param cls the Class to log from
    * @return Logger the logger instance
    */
   public static Logger getLogger(Class cls) {
      return new Log4jLogger(cls);
   }
}
