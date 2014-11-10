package com.spirit.mailer.session;

/**
 * Excepcion Generica lanzada por el modulo de correos
 * 
 * @author Ricardo Andrade
 * 
 */
public class MailerException extends Exception {
	/**
	 * Constructor
	 * 
	 * @param exception
	 *            Cadena del error
	 */
	public MailerException(String exception) {
		super(exception);
	}
}
