package com.spirit.exception;


/**
 * Base Exception class that can be thrown by the business layer.
 *
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:05:12 $
 */
public class SaldoCuentaNegativoException extends Exception {
    /**
     * message constructor.
     * @param message the error message
     */
    public SaldoCuentaNegativoException(String message) {
        super(message);
    }

    /**
     * cause constructor.
     * @param cause the cause
     */
    public SaldoCuentaNegativoException(Exception cause) {
        super(cause);
    }

    /**
     * message en cause constructor.
     * @param message the error message
     * @param cause   the cause
     */
    public SaldoCuentaNegativoException(String message, Exception cause) {
        super(message, cause);
    }
}
