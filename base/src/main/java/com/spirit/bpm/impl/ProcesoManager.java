package com.spirit.bpm.impl;

import java.util.HashMap;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.spirit.bpm.process.SpiritBPMConnectorIf;
import com.spirit.bpm.process.SpiritProcessDefinition;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.ClientParams;
import com.spirit.bpm.process.elements.Proceso;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.client.Parametros;
import com.spirit.general.entity.UsuarioIf;

public class ProcesoManager {

	public static final String PROCESO_COMPRA = "PROCESO_COMPRA";
	private static HashMap<SpiritProcessDefinition, SpiritClientProcess> mapaProcesos = new HashMap<SpiritProcessDefinition, SpiritClientProcess>();

	private static InitialContext context = null;
	private static HashMap<String, Object> cacheRemote = new HashMap<String, Object>();
	
	static {
		try {
			Hashtable<String, Object> params = new Hashtable<String, Object>();
			params.put("java.naming.factory.initial",
					"org.jnp.interfaces.NamingContextFactory");
			params.put("java.naming.factory.url.pkgs",
					"org.jboss.naming:org.jnp.interfaces");
			params.put("java.naming.provider.url", "zeus:1099");
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

	public static SpiritBPMConnectorIf getBpmClient() {
		 //System.setProperty(BonitaConstants.JAAS_PROPERTY,
		//"src/test/java/jaas-jboss.cfg");
		//System.setProperty(BonitaConstants.API_TYPE_PROPERTY, "EJB3");
		//System.setProperty(BonitaConstants.PROVIDER_URL_PROPERTY,
		// "192.168.100.144:1099");
		//return new BonitaConnector();
		return (SpiritBPMConnectorIf) getService("BonitaConnector");
	}

	public static void setManager(
			SpiritProcessDefinition spiritProcessDefinition,
			SpiritClientProcess spiritProcess) {
		mapaProcesos.put(spiritProcessDefinition, spiritProcess);
	}

	private static SpiritClientProcess getSpiritProcess(Proceso proceso)
			throws BPMException {
		String nombreProceso = proceso.getNombre();
		SpiritClientProcess process = null;
		if (SpiritProcessDefinition.getSpiritProcessDefinition(proceso).equals(
				SpiritProcessDefinition.COMPRA)) {
			process = mapaProcesos.get(SpiritProcessDefinition.COMPRA);
		}
		if (process == null) {
			throw new BPMException("Proceso : " + nombreProceso
					+ " no encontrado");
		} else {
			process.setSpiritBPMConnector(getBpmClient());
			return process;
		}
	}

	public static SpiritClientProcess getSpiritProcess(
			SpiritProcessDefinition spiritProcessDefinition)
			throws BPMException {
		SpiritClientProcess process = mapaProcesos.get(spiritProcessDefinition);
		if (process != null) {
			process.setSpiritBPMConnector(getBpmClient());
			UsuarioIf usuarioIf = (UsuarioIf) Parametros.getUsuarioIf();
			ClientParams clientParams = new ClientParams();
			clientParams.setUser(usuarioIf.getUsuario());
			clientParams.setPassword(usuarioIf.getClave());
			process.setUserParams(clientParams);
			return process;
		} else {
			throw new BPMException("Proceso Manager para : "
					+ spiritProcessDefinition.getNombreProceso()
					+ " no encontrado");
		}
	}

	private static SpiritClientProcess getSpiritProcess(String nombreProceso)
			throws BPMException {
		SpiritClientProcess process = null;
		if (SpiritProcessDefinition.COMPRA.getNombreProceso().equalsIgnoreCase(
				nombreProceso)) {
			process = mapaProcesos.get(SpiritProcessDefinition.COMPRA);
		} else if (SpiritProcessDefinition.PRESUPUESTO.getNombreProceso()
				.equalsIgnoreCase(nombreProceso)) {
			process = mapaProcesos.get(SpiritProcessDefinition.PRESUPUESTO);
		}
		if (process == null) {
			throw new BPMException("Proceso : " + nombreProceso
					+ " no encontrado");
		} else {
			process.setSpiritBPMConnector(getBpmClient());
			return process;
		}
	}

	public static void openTask(ClientParams clientParams, Tarea tarea)
			throws BPMException {
		SpiritClientProcess spiritProcess = getSpiritProcess(tarea.getProceso());
		spiritProcess.setUserParams(clientParams);
		spiritProcess.openTarea(tarea);

	}

	public static void iniciarTask(ClientParams clientParams, Tarea tarea)
			throws BPMException {
		SpiritClientProcess spiritProcess = getSpiritProcess(tarea.getProceso());
		spiritProcess.setUserParams(clientParams);
		spiritProcess.iniciarTarea(tarea);
	}

	public static void continuarTask(ClientParams clientParams, Tarea tarea)
			throws BPMException {
		SpiritClientProcess spiritProcess = getSpiritProcess(tarea.getProceso());
		spiritProcess.setUserParams(clientParams);
		spiritProcess.continuarTarea(tarea);
	}

	public static void iniciarInstancia(ClientParams clientParams,
			Proceso proceso, String descripcion) throws BPMException {
		SpiritClientProcess spiritProcess = getSpiritProcess(proceso);
		spiritProcess.setUserParams(clientParams);
		spiritProcess.getRemoteProcess().iniciarProceso(clientParams,
				descripcion);
	}

	public static ClientParams getClientParams() {
		UsuarioIf usuarioIf = (UsuarioIf) Parametros.getUsuarioIf();
		return new ClientParams(usuarioIf.getUsuario(), usuarioIf.getClave());

	}
}
