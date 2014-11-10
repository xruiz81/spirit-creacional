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
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * A class wich specifies the format for the log messages
 * @author Ronald Kramp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:32 $
 */
public final class LogFormatter extends Formatter {

   /** a variable for specifying a given data format */
   private SimpleDateFormat dateFormatter;

   /**
    * a variable the will set the number of last packages will be shown
    * example number=3 com.finalist.appname.util.log.LogFormatter
    * will be util.log.LogFormatter
    */
   private int showNumberOfLastPackages;

   /** a variable for specifying the separator between the Classname and the message */
   private String messageSeparator;


   /**
    * Constrcutor for making a LogFormatter
    * @param showNumberOfLastPackages the number of packages to log
    * @param datePattern the pattern of the date to log
    * @param messageSeparator the message separator
    */
   public LogFormatter(int showNumberOfLastPackages, String datePattern, String messageSeparator) {
      this.dateFormatter = new SimpleDateFormat(datePattern);
      this.messageSeparator = messageSeparator;
      this.showNumberOfLastPackages = showNumberOfLastPackages;
   }


   /**
    * Formatting a logrecord to a single line.
    * sequence of the logline: Logevel date Classname separator message exception
    * @param rec the log record to format
    * @return String the line to log
    */
   public String format(LogRecord rec) {
      StringBuffer buf = new StringBuffer(1000);
      StringBuffer packageClass = new StringBuffer(50);
      String loggerName = rec.getLoggerName();

      if (this.showNumberOfLastPackages <= 0) {
         packageClass.append(loggerName);
      }

      for (int i = 0; i < this.showNumberOfLastPackages; i++) {
         int index = loggerName.lastIndexOf(".");

         if (index > -1) {
            packageClass.insert(0, loggerName.substring(index, loggerName.length()));
            loggerName = loggerName.substring(0, index);
         }
         else {
            packageClass.insert(0, loggerName);
            break;
         }
      }
      if (packageClass.charAt(0) == '.') {
         packageClass.deleteCharAt(0);
      }
      //String level = rec.getLevel().toString();
      //level = (level.length() >= 5) ? level.substring(0, 5) : level + " ";

      buf.append(rec.getLevel());
      buf.append("     ");
      buf.delete(8, buf.length());
      buf.insert(8, dateFormatter.format(new Date(rec.getMillis())));
      buf.append(" ");
      buf.append(packageClass);
      buf.append(" ");
      buf.append(this.messageSeparator);
      buf.append(" ");
      buf.append(formatMessage(rec));
      buf.append((rec.getThrown() != null) ? rec.getThrown().toString() : "");
      buf.append('\n');
      return buf.toString();
   }
}