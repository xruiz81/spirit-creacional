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

/**
 * Value object with log settings
 * @author Ronald Kramp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:32 $
 */
public class LogConfig {

   /** the logfile to log to */
   private String logFile = "finalist%g.log";

   /** append to the logfile */
   private boolean append = true;

   /** maximum number of logfiles to create */
   private int maxBackupIndex = 1;

   /** maxfilesize for each created logfile */
   private int maxFileSize = 50;

   /** the number of packages to show for a class */
   private int showNumberOfLastPackages = 0;

   /** the datepattern to log */
   private String datePattern = "yyyy-MM-dd HH:mm:ss,SSS";

   /** the messagespearator */
   private String messageSeparator = "-";

   /** the loglevel for logging certain messages */
   private String logLevel = "INFO";


   /**
    * Constrcutor for making a LogConfig
    * All settings for a appender
    * @param logFile the name of the logfile, default finalist%g.log
    * @param append default true
    * @param maxBackupIndex the number of files to create, must be greater than 0, default 1
    * @param maxFileSize the size of a log file in megabytes, must be greater than 0, default 50
    * @param showNumberOfLastPackages the number of pacakges of a class to show, cannot be negative, default 0
    * @param datePattern the date to log, default yyyy-MM-dd HH:mm:ss,SSS
    * @param messageSeparator default -
    * @param logLevel default INFO
    */
   public LogConfig(String logFile,
         boolean append,
         int maxBackupIndex,
         int maxFileSize,
         int showNumberOfLastPackages,
         String datePattern,
         String messageSeparator,
         String logLevel) {
      if ((logFile != null) && (!logFile.equals(""))) {
         this.logFile = logFile;
      }
      this.append = append;
      if (maxBackupIndex > 0) {
         this.maxBackupIndex = maxBackupIndex;
      }
      if (maxFileSize > 0) {
         this.maxFileSize = maxFileSize;
      }
      if (showNumberOfLastPackages > -1) {
         this.showNumberOfLastPackages = showNumberOfLastPackages;
      }
      if ((datePattern != null) && (!datePattern.equals(""))) {
         this.datePattern = datePattern;
      }
      if ((messageSeparator != null) && (!messageSeparator.equals(""))) {
         this.messageSeparator = messageSeparator;
      }
      if ((logLevel != null) && (!logLevel.equals(""))) {
         this.logLevel = logLevel;
      }
   }


   /**
    * Get the name of the logfile
    * @return String, the name of the log file
    */
   public String getLogFile() {
      return this.logFile;
   }


   /**
    * Check to see if the logfile is appendable
    * @return boolean, logfile is appendable
    */
   public boolean isAppendable() {
      return this.append;
   }


   /**
    * Get the maxBackupIndex
    * @return int, the maxBackupIndex
    */
   public int getMaxBackupIndex() {
      return this.maxBackupIndex;
   }


   /**
    * Get the maxFileSize
    * @return int, maxFileSize
    */
   public int getMaxFileSize() {
      return this.maxFileSize;
   }


   /**
    * Get the showNumberOfLastPackages
    * @return int, the showNumberOfLastPackages
    */
   public int getShowNumberOfLastPackages() {
      return this.showNumberOfLastPackages;
   }


   /**
    * Get the datePattern
    * @return String, the datePattern
    */
   public String getDatePattern() {
      return this.datePattern;
   }


   /**
    * Get the messageSeparator
    * @return String, the messageSeparator
    */
   public String getMessageSeparator() {
      return this.messageSeparator;
   }


   /**
    * Get the logLevel
    * @return String, the logLevel
    */
   public String getLogLevel() {
      return this.logLevel;
   }


   /**
    * returning the string with all values
    * @return String, all values as a String
    */
   public String toString() {
      return "[logFile=" + this.logFile + "]" +
            ", [append=" + this.append + "]" +
            ", [maxBackupIndex=" + this.maxBackupIndex + "]" +
            ", [maxFileSize=" + this.maxFileSize + "]" +
            ", [showNumberOfLastPackages=" + this.showNumberOfLastPackages + "]" +
            ", [datePattern=" + this.datePattern + "]" +
            ", [messageSeparator=" + this.messageSeparator + "]" +
            ", [logLevel=" + this.logLevel + "]";
   }
}