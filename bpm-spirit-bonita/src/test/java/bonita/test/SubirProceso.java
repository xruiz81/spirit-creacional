package bonita.test;

import org.ow2.bonita.util.BonitaConstants;

import com.spirit.bpm.connectors.BonitaConnector;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.ClientParams;

public class SubirProceso {

	public static void main(String[] args) throws BPMException {
		System.setProperty(BonitaConstants.JAAS_PROPERTY,
				"src/test/java/jaas-jboss.cfg");
		System.setProperty(BonitaConstants.API_TYPE_PROPERTY, "EJB3");
		System.setProperty(BonitaConstants.PROVIDER_URL_PROPERTY,
				"localhost:1099");
		;
		ClientParams clientParams = new ClientParams();
		clientParams.setPassword("bpm");
		clientParams.setUser("admin");
		new BonitaConnector().subirProceso(clientParams, "c:\\Orden_de_Trabajo_Detalle--1.1.bar");
		new BonitaConnector().subirProceso(clientParams, "c:\\Orden_de_Trabajo--1.1.bar");
		//new BonitaConnector().subirProceso(clientParams, "c:\\Orden_de_Trabajo_Detalle--1.0.proc");
		//new BonitaConnector().subirProceso(clientParams, "c:\\Orden_de_Trabajo--1.0.proc");
	}
}
