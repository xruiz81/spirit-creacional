package com.spirit.exception;


/**
 * Exception that will be thrown if the service is unknown.
 * @author JAG - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:05:12 $
 */
public class UnknownServiceException extends GenericBusinessException {
    /**
     * message constructor.
     * @param message the error message
     */
    public UnknownServiceException(String message) {
        super(message);
    }

    /**
     * cause constructor.
     * @param cause the cause
     */
    public UnknownServiceException(Exception cause) {
        super(cause);
    }

    /**
     * message en cause constructor.
     * @param message the error message
     * @param cause   the cause
     */
    public UnknownServiceException(String message, Exception cause) {
        super(message, cause);
    }
}
