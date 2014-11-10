package com.spirit.bpm.impl;

import java.util.HashMap;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.spirit.bpm.process.SpiritBPMConnectorIf;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.ClientParams;
import com.spirit.bpm.process.elements.InstanciaProceso;

public abstract class SpiritServerProcess extends SpiritProcesParams implements
		SpiritServerProcessIf {
	private static InitialContext context = null;

	private static HashMap<String, Object> cacheRemote = new HashMap<String, Object>();

	static {
		try {
			Hashtable<String, Object> params = new Hashtable<String, Object>();
			params.put("java.naming.factory.initial",
					"org.jnp.interfaces.NamingContextFactory");
			params.put("java.naming.factory.url.pkgs",
					"org.jboss.naming:org.jnp.interfaces");
			params.put("java.naming.provider.url", "localhost:1099");
			// context = new InitialContext(params);
			context = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}

	private static Object getService(String serviceName) {
		try {
			Object remoteBean = cacheRemote.get(serviceName);
			if (remoteBean == null) {

				remoteBean = context
						.lookup("bonita/" + serviceName + "/remote");
				cacheRemote.put(serviceName, remoteBean);
			}
			return remoteBean;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: " + serviceName);
			return null;
		}
	}

	public SpiritBPMConnectorIf getSpiritBPMConnector() {
		return (SpiritBPMConnectorIf) getService("BonitaConnector");
	}

	public abstract InstanciaProceso iniciarProceso(ClientParams clientParams,
			String descripcion) throws BPMException;

	protected ClientParams getUserAdmin() {
		ClientParams clientParams = new ClientParams();
		clientParams.setUser("admin");
		clientParams.setPassword("bpm");
		return clientParams;
	}

}
