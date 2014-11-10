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

import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


/**
 * This class is an helper class for retrieve log settings from an xml file
 * @author Ronald Kramp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 17:33:41 $
 */
public class XMLLogPropertyReader {

   /** name for the appender tag in the xml file */
   public static final String APPENDER = "appender";

   /** name for the name attribute of the appender in the xml file */
   public static final String NAME = "name";

   /** name for the logfile attribute of the appender in the xml file */
   public static final String LOG_FILE = "logfile";

   /** name for the append attribute of the appender in the xml file */
   public static final String APPEND = "append";

   /** name for the maxbackupindex attribute of the appender in the xml file */
   public static final String MAX_BACKUP_INDEX = "maxbackupindex";

   /** name for the maxfilesize attribute of the appender in the xml file */
   public static final String MAX_FILE_SIZE = "maxfilesize";

   /** name for the shownumberoflastpackages attribute of the appender in the xml file */
   public static final String SHOW_NUMBER_OF_LAST_PACKAGES = "shownumberoflastpackages";

   /** name for the datepattern attribute of the appender in the xml file */
   public static final String DATE_PATTERN = "datepattern";

   /** name for the messageseparator attribute of the appender in the xml file */
   public static final String MESSAGE_SEPARATOR = "messageseparator";

   /** name for the loglevel attribute of the appender in the xml file */
   public static final String LOG_LEVEL = "loglevel";

   /**
    * Retrieving all appender setting from a configuration file.
    * @param location the directory + filename where to find the log configuration xml file
    * @return HashMap all log configurations
    */
   public static HashMap getLogConfigs(String location) {
      HashMap logConfigs = new HashMap();
      try {
         //URL url = PropertyReader.getPropertiesURL(location);
         URL url = PropertyReader.getPropertiesURL(location);
         InputSource xmlInp = new InputSource(url.openStream());
         Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlInp);
         NodeList appenders = doc.getElementsByTagName(APPENDER);
         for (int i = 0; i < appenders.getLength(); i++) {
            Node curNode = appenders.item(i);
            if (curNode.getNodeName().equals(APPENDER)) {
               NamedNodeMap nnm = curNode.getAttributes();
               String appenderName = nnm.getNamedItem(NAME).getNodeValue();
               String logFile = nnm.getNamedItem(LOG_FILE).getNodeValue();
               String append = nnm.getNamedItem(APPEND).getNodeValue();

               String maxBackupIndexString = nnm.getNamedItem(MAX_BACKUP_INDEX).getNodeValue();
               int maxBackupIndex = -1;
               try {
                  maxBackupIndex = Integer.parseInt(maxBackupIndexString);
               }
               catch (Exception e) {
                  System.out.println("Incorrect number for maxBackupIndex with appender: " + appenderName + ": " + e);
               }

               String maxFileSizeString = nnm.getNamedItem(MAX_FILE_SIZE).getNodeValue();
               int maxFileSize = -1;
               try {
                  maxFileSize = Integer.parseInt(maxFileSizeString);
               }
               catch (Exception e) {
                  System.out.println("Incorrect number for maxfilesize with appender: " + appenderName + ": " + e);
               }

               String showNumberOfLastPackagesString = nnm.getNamedItem(SHOW_NUMBER_OF_LAST_PACKAGES).getNodeValue();
               int showNumberOfLastPackages = -1;
               try {
                  showNumberOfLastPackages = Integer.parseInt(showNumberOfLastPackagesString);
               }
               catch (Exception e) {
                  System.out.println("Incorrect number for shownumberoflastpackages with appender: " +
                        appenderName + ": " + e);
               }

               String datePattern = nnm.getNamedItem(DATE_PATTERN).getNodeValue();
               String messageSeparator = nnm.getNamedItem(MESSAGE_SEPARATOR).getNodeValue();
               String logLevel = nnm.getNamedItem(LOG_LEVEL).getNodeValue();
               LogConfig lg = new LogConfig(logFile, append.equals("true"), maxBackupIndex, maxFileSize,
                     showNumberOfLastPackages, datePattern, messageSeparator, logLevel);
               System.out.println("Logging for filter [" + appenderName + "] with setting\n");
               System.out.println(lg);
               logConfigs.put(appenderName, lg);
            }
         }
      }
      catch (Exception e) {
         System.out.println("Exception: " + e);
      }
      return logConfigs;
   }
}
