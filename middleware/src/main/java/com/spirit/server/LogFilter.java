/*   Copyright (C) 2003 Finalist IT Group
 *
 *   This file is part of JAG - the Java J2EE Application Generator
 *
 *   JAG is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *   JAG is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *   You should have received a copy of the GNU General Public License
 *   along with JAG; if not, write to the Free Software
 *   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.spirit.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * A class for setting a filter
 * @author Ronald Kramp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:32 $
 */
public final class LogFilter implements Filter {

   /** a filter which should be logged */
   private String logFilterValue = "";


   /**
    * Constrcutor for making a LogFormatter
    * @param logFilterValue the filter for logging
    */
   public LogFilter(String logFilterValue) {
      this.logFilterValue = logFilterValue;
   }

   /**
    * Check to see if the filter is in the logrecord
    * @param logRecord the log record to format
    * @return boolean true if the filter exists in the logRecord
    */
   public boolean isLoggable(LogRecord logRecord) {
      return (logRecord.getLoggerName().indexOf(logFilterValue) > -1);
   }
}