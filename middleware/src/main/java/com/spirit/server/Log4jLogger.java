package com.spirit.server;

import org.apache.log4j.Category;


/**
 * This class is wil log messages to the log file using log4j logging framework.
 * @author Ronald Kramp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:32 $
 */
public final class Log4jLogger implements Logger {

   /** the log object to log to */
   private Category logger = null;


   /**
    * Constructor for creating a logger object using jdk 1.4 or higher logging.
    *
    * @param cls the class which wants to log
    */
   public Log4jLogger(Class cls) {
      logger = Category.getInstance(cls);
   }


   /**
    * Logging a finest message.
    * @param message the message to log
    */
   public void finest(String message) {
      logger.debug(message);
   }


   /**
    * Logging a finer message.
    * @param message the message to log
    */
   public void finer(String message) {
      logger.debug(message);
   }


   /**
    * Logging a fine message
    * @param message the message to log.
    */
   public void fine(String message) {
      logger.debug(message);
   }


   /**
    * Logging a config message.
    * @param message the message to log
    */
   public void config(String message) {
      logger.debug(message);
   }


   /**
    * Logging a info message.
    * @param message the message to log
    */
   public void info(String message) {
      logger.info(message);
   }


   /**
    * Logging a warning message.
    * @param message the message to log
    */
   public void warning(String message) {
      logger.warn(message);
   }


   /**
    * Logging a severe message.
    * @param message the message to log
    */
   public void severe(String message) {
      logger.error(message);
   }

   //****************************************************
   //*  The methods from log4j also implemented below   *
   //****************************************************


   /**
    * Logging a debug message.
    * @param message the message to log
    */
   public void debug(String message) {
      logger.debug(message);
   }


   /**
    * Logging a debug message with the throwable message.
    * @param message the message to log
    * @param t the exception
    */
   public void debug(String message, Throwable t) {
      logger.debug(message, t);
   }


   /**
    * Logging an info message with the throwable message.
    * @param message the message to log
    * @param t the exception
    */
   public void info(String message, Throwable t) {
      logger.info(message, t);
   }


   /**
    * Logging a warning message.
    * @param message the message to log
    */
   public void warn(String message) {
      logger.warn(message);
   }


   /**
    * Logging a warning message with the throwable message.
    * @param message the message to log
    * @param t the exception
    */
   public void warn(String message, Throwable t) {
      logger.warn(message, t);
   }


   /**
    * Logging an error message.
    * @param message the message to log
    */
   public void error(String message) {
      logger.error(message);
   }


   /**
    * Logging an error message with the throwable message.
    * @param message the message to log
    * @param t the exception
    */
   public void error(String message, Throwable t) {
      logger.error(message, t);
   }


   /**
    * Logging a fatal message.
    * @param message the message to log
    */
   public void fatal(String message) {
      logger.fatal(message);
   }


   /**
    * Logging a fatal message with the throwable message.
    * @param message the message to log
    * @param t the exception
    */
   public void fatal(String message, Throwable t) {
      logger.fatal(message, t);
   }
}
