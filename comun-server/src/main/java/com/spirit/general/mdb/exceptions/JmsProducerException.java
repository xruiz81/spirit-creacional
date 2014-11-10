package com.spirit.general.mdb.exceptions;


public class JmsProducerException extends RuntimeException {

    public JmsProducerException() {}

    public JmsProducerException(String message) {
        super(message);
    }

    public JmsProducerException(Throwable cause) {
        super(cause);
    }

    public JmsProducerException(String message, Throwable cause) {
        super(message, cause);
    }

}