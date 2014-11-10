package com.spirit.bpm.connectors;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.ow2.bonita.util.SimpleCallbackHandler;

import com.spirit.bpm.process.SpiritBPMConnectorIf;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.ClientParams;

public abstract class SpiritBPMConnectorImpl implements SpiritBPMConnectorIf {

	private LoginContext loginContext;

	protected void handleException(Exception e) throws BPMException {
		handleException(e, "Error inesperado");
	}

	protected void handleException(Exception e, String mensaje)
			throws BPMException {
		e.printStackTrace();
		throw new BPMException(mensaje + " " + e.getMessage());
	}

	public void login(ClientParams clientParams) throws BPMException {
		try {
			if (clientParams == null)
				throw new BPMException("Debe proporcionar el cliente");
			if (loginContext != null)
				loginContext.logout();
			loginContext = new LoginContext(getContext(),
					new SimpleCallbackHandler(clientParams.getUser(),
							clientParams.getPassword()));
			loginContext.login();
		} catch (LoginException e) {
			handleException(e, "Usuario no autenticado");
		} catch (Exception e) {
			handleException(e);
		}
	}

	public void logout() throws BPMException {
		try {
			if (loginContext != null)
				loginContext.logout();
		} catch (LoginException e) {
			handleException(e, "Usuario no autenticado");
		} catch (Exception e) {
			handleException(e);
		}
	}

}
