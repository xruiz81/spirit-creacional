package bonita.test;

import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.spirit.bpm.connectors.BonitaRemote;
import com.spirit.bpm.process.SpiritProcessDefinition;
import com.spirit.bpm.process.elements.ClientParams;

public class EJBTester {

	private static InitialContext context = null;
	public static HashMap<String, Object> cacheRemote = new HashMap<String, Object>();
	static {
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}

	public static Object getService(String serviceName) {
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

	public static void main(String[] args) throws Exception {
		ClientParams clientParams = new ClientParams();
		clientParams.setPassword("bpm");
		clientParams.setUser("admin");
		BonitaRemote bonitaRemote = (BonitaRemote) getService("BonitaConnector");
		bonitaRemote.obtenerProceso(clientParams, SpiritProcessDefinition.TEST);
	}
}
