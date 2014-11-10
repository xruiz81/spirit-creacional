package com.spirit.mailer.session;

public interface MailerIf {
	/**
	 * Metodo <b>Sincronico</b> para enviar correos en ese momento.
	 * 
	 * @param mailObject
	 * @throws MailerException
	 */
	public void sendMailMessage(MailMessage mailMessage) throws MailerException;

	/**
	 * Metodo <b>Asincronico</b> para enviar correos. (No bloquea el proceso)
	 * 
	 * @param mailObject
	 * @throws MailerException
	 */
	public void sendMailToQueue(MailMessage mailMessage) throws MailerException;

	/**
	 * Envia una Exception a los correos definidos como administrador en la
	 * tabla de parametros
	 * 
	 * @param descripcion
	 * @param e
	 * @throws MailerException
	 */
	public void mailErrorToAdmin(String descripcion, Exception e)
			throws MailerException;
}
