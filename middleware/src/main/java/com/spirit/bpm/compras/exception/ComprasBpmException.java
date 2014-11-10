package com.spirit.bpm.compras.exception;


/**
 * Base Exception class that can be thrown by the business layer.
 *
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:47 $
 */
public class ComprasBpmException extends Exception {
    /**
     * message constructor.
     * @param message the error message
     */
    public ComprasBpmException(String message) {
        super(message);
    }

    /**
     * cause constructor.
     * @param cause the cause
     */
    public ComprasBpmException(Exception cause) {
        super(cause);
    }

    /**
     * message en cause constructor.
     * @param message the error message
     * @param cause   the cause
     */
    public ComprasBpmException(String message, Exception cause) {
        super(message, cause);
    }
}
