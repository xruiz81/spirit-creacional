package com.spirit.exception;


/**
 * Base Exception class that can be thrown by the business layer.
 *
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:47 $
 */
public class CuentaNoImputableException extends Exception {
    /**
     * message constructor.
     * @param message the error message
     */
    public CuentaNoImputableException(String message) {
        super(message);
    }

    /**
     * cause constructor.
     * @param cause the cause
     */
    public CuentaNoImputableException(Exception cause) {
        super(cause);
    }

    /**
     * message en cause constructor.
     * @param message the error message
     * @param cause   the cause
     */
    public CuentaNoImputableException(String message, Exception cause) {
        super(message, cause);
    }
}
