package com.spirit.exception;

/**
 * Exception that will be thrown if a service can not be instantiated.
 *
 * @author JAG - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:47 $
 */
public class ServiceInstantiationException extends GenericBusinessException {
    /**
     * message constructor.
     * @param message the error message
     */
    public ServiceInstantiationException(String message) {
        super(message);
    }

    /**
     * cause constructor.
     * @param cause the cause
     */
    public ServiceInstantiationException(Exception cause) {
        super(cause);
    }

    /**
     * message en cause constructor.
     * @param message the error message
     * @param cause   the cause
     */
    public ServiceInstantiationException(String message, Exception cause) {
        super(message, cause);
    }
}
