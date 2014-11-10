package bonita.test;

import java.util.HashMap;
import java.util.List;

import org.ow2.bonita.util.BonitaConstants;

import com.spirit.bpm.connectors.BonitaConnector;
import com.spirit.bpm.process.SpiritProcessDefinition;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.ClientParams;
import com.spirit.bpm.process.elements.Comentario;
import com.spirit.bpm.process.elements.InstanciaProceso;
import com.spirit.bpm.process.elements.Proceso;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.bpm.process.elements.UserBpm;
import com.spirit.bpm.process.elemets.state.EnumInstanceState;

public class Tester {

	private static final String JAAS_FILE_PATH = "src/test/resources/jaas-jboss.cfg";
	static {
		System.setProperty(BonitaConstants.JAAS_PROPERTY, JAAS_FILE_PATH);
		System.setProperty(BonitaConstants.API_TYPE_PROPERTY, "EJB3");
	}

	private static String space(String s) {
		for (int i = s.length(); i < 20; i++) {
			s = s + " ";
		}
		return s;
	}

	private static void print(String text, Object value) {
		System.out.println(space(text + ": ") + value);
	}

	private static void yell(Proceso p) {
		System.out.println("----------PROCESO--------------");
		print("ID", p.getId());
		print("NOMBRE", p.getNombre());
		print("DESC", p.getDescripcion());
		print("VERSION", p.getVersion());
		print("ESTADO", p.getEstado());
		System.out.println("PARTICIPANTES: ");
		for (UserBpm u : p.getParticipantes()) {
			print(" ", u.getUsername());
		}
	}

	private static void yell(InstanciaProceso p) {
		System.out.println("----------INSTANCIA PROCESO--------------");
		print("ID", p.getId());
		print("DESCRIPCION", p.getDescripcion());
		print("FINALIZADO POR", p.getFinalizadoPor());
		print("INICIADO POR", p.getIniciadoPor());
		print("PROCESO", p.getProceso());
		print("ESTADO", p.getEstado());
		print("FECHA FIN", p.getFechaFinalizado());
		print("FECHA INICIO", p.getFechaIniciado());
		print("FECHA ULTIMA ACTUALIZACION", p.getFechaUltimaActualizacion());
		System.out.println("TAREAS: ");
		for (Tarea t : p.getTareas()) {
			print("   ", t.getNombre());
		}

		System.out.println("COMENTARIOS: ");
		for (Comentario c : p.getComentarios()) {
			print("   ", c.getDescripcion());
		}
	}

	private static void yell(List<InstanciaProceso> instanciaProcesoList) {
		for (InstanciaProceso instanciaProceso : instanciaProcesoList) {
			yell(instanciaProceso);
		}

	}

	public void fullTest() throws BPMException, InterruptedException {
		ClientParams clientParams = new ClientParams();
		clientParams.setPassword("bpm");
		clientParams.setUser("admin");
		BonitaConnector bc = new BonitaConnector();
		System.out.println("--------CONSULTANDO PROCESO");
		Proceso p = bc.obtenerProceso(clientParams,
				SpiritProcessDefinition.COMPRA);
		yell(p);
		System.out.println("--------FIN");
		Thread.sleep(3000);

		System.out
				.println("--------CONSULTANDO INSTANCIAS ACTIVAS DEL PROCESO");
		yell(bc.consultarProcesosActivos(clientParams,
				SpiritProcessDefinition.COMPRA, EnumInstanceState.ALL));
		System.out.println("--------FIN");
		Thread.sleep(3000);

		System.out.println("--------INICIANDO INSTANCIA DE PROCESO");
		// INICIAR UN PROCESO DE COMPRA, PASAR COMO PARAMETROS:
		// CLIENTE
		// CODIGO

		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cliente", "EL CLIENTE MANDA");
		parametros.put("codigo", "00-45-8956");
		// bc.iniciarProceso(clientParams, SpiritProcessDefinition.COMPRA,
		// parametros);
		System.out.println("--------FIN");
		Thread.sleep(3000);

		// ASIGNAR A RANDRADE
		List<Tarea> tareas = bc.consultarTareasPendientes(clientParams);
		for (Tarea tarea : tareas) {
			bc.asignarTarea(clientParams, tarea, "randrade");
			System.out.println("ASIGNANDO TAREA PENDIENTE: "
					+ tarea.getNombre());
		}
		Thread.sleep(3000);
		// CONSULTAR MIS TAREAS PENDIENTES (COMO RANDRADE)
		clientParams.setUser("randrade");
		clientParams.setPassword("12345678");
		List<Tarea> tareasReAsignadas = bc
				.consultarTareasPendientes(clientParams);
		for (Tarea tarea : tareasReAsignadas) {
			System.out.println("TAREA PENDIENTE: " + tarea.getNombre());
			System.out.println(tarea.getId());
		}
		Thread.sleep(3000);
		// INGRESAR LA INFO REQUERIDA (TERMINAR LA TAREA ESPECIFICA)
		tareasReAsignadas = bc.consultarTareasPendientes(clientParams);
		for (Tarea tarea : tareasReAsignadas) {
			bc.terminarTarea(clientParams, tarea);
			System.out.println("TERMINANDO TAREA: " + tarea.getNombre());
		}
		// VERIFICAR FECHAS, EL USUARIO QUE LA TERMINO, ETC (CONSULTAR LA TAREA
		// TERMINADA)
		// CONSULTAR LOS PROCESOS ACTIVOS (INSTANCIAS)
	}

	public static void main(String[] args) throws Exception {
		System.setProperty(BonitaConstants.JAAS_PROPERTY,
				"src/test/java/jaas-jboss.cfg");
		System.setProperty(BonitaConstants.API_TYPE_PROPERTY, "EJB3");
		System.setProperty(BonitaConstants.PROVIDER_URL_PROPERTY,
				"localhost:1099");
		;
		ClientParams clientParams = new ClientParams();
		clientParams.setPassword("bpm");
		clientParams.setUser("admin");
		BonitaConnector bc = new BonitaConnector();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("descripcion",
				"Inicio del Proceso de la Orden de trabajo # " + "XXX");
		parametros.put("campanaId", 1l);
		parametros.put("ordenTrabajoId", 2l);
		// ID,CORREO,USUARIO,OBSERVACION,SUBJECT;ID,CORREO,USUARIO,OBSERVACION,SUBJECT ETC
		parametros
				.put(
						"listaValores",
						"5016,nirvametal@hotmail.com,randrade,Haste este trabajo pilas..;" +
						"5017,ricardo.andrade@versality.com.ec,eroncoroni,Haste este trabajo PEPE pilas..");
		InstanciaProceso instanciaProceso = bc.iniciarProceso(clientParams,
				SpiritProcessDefinition.ORDEN_TRABAJO, parametros);

	}
}
