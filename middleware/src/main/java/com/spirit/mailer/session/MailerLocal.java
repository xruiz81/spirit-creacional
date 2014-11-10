package com.spirit.mailer.session;

import javax.ejb.Local;
import javax.mail.Transport;

@Local
public interface MailerLocal extends MailerIf{

	void sendMailMessage(MailMessage mailObject)
			throws MailerException;
	
}
