package com.spirit.bpm.connectors;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.Stateless;

import org.ow2.bonita.facade.BAMAPI;
import org.ow2.bonita.facade.IdentityAPI;
import org.ow2.bonita.facade.ManagementAPI;
import org.ow2.bonita.facade.QueryDefinitionAPI;
import org.ow2.bonita.facade.QueryRuntimeAPI;
import org.ow2.bonita.facade.RuntimeAPI;
import org.ow2.bonita.facade.def.element.BusinessArchive;
import org.ow2.bonita.facade.def.majorElement.ParticipantDefinition;
import org.ow2.bonita.facade.def.majorElement.ProcessDefinition;
import org.ow2.bonita.facade.exception.ActivityNotFoundException;
import org.ow2.bonita.facade.exception.DeploymentException;
import org.ow2.bonita.facade.exception.IllegalTaskStateException;
import org.ow2.bonita.facade.exception.InstanceNotFoundException;
import org.ow2.bonita.facade.exception.ProcessNotFoundException;
import org.ow2.bonita.facade.exception.TaskNotFoundException;
import org.ow2.bonita.facade.exception.UserNotFoundException;
import org.ow2.bonita.facade.exception.VariableNotFoundException;
import org.ow2.bonita.facade.identity.User;
import org.ow2.bonita.facade.runtime.ActivityState;
import org.ow2.bonita.facade.runtime.Comment;
import org.ow2.bonita.facade.runtime.ProcessInstance;
import org.ow2.bonita.facade.runtime.TaskInstance;
import org.ow2.bonita.facade.uuid.ProcessDefinitionUUID;
import org.ow2.bonita.facade.uuid.ProcessInstanceUUID;
import org.ow2.bonita.util.AccessorUtil;
import org.ow2.bonita.util.BusinessArchiveFactory;

import com.spirit.bpm.process.SpiritProcessDefinition;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.BamAdminApi;
import com.spirit.bpm.process.elements.BamApi;
import com.spirit.bpm.process.elements.ClientParams;
import com.spirit.bpm.process.elements.Comentario;
import com.spirit.bpm.process.elements.InstanciaProceso;
import com.spirit.bpm.process.elements.Proceso;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.bpm.process.elements.UserBpm;
import com.spirit.bpm.process.elemets.state.EnumInstanceState;
import com.spirit.bpm.process.elemets.state.EnumNivelVencido;
import com.spirit.bpm.process.elemets.state.EnumPrioridad;
import com.spirit.bpm.process.elemets.state.EnumProcessState;
import com.spirit.bpm.process.elemets.state.EnumTareaState;

@Stateless
public class BonitaConnector extends SpiritBPMConnectorImpl implements
		BonitaRemote, BonitaLocal {

	private final ManagementAPI managementAPI = AccessorUtil.getManagementAPI();
	private final QueryDefinitionAPI queryDefinitionAPI = AccessorUtil
			.getQueryDefinitionAPI();
	private final RuntimeAPI runtimeAPI = AccessorUtil.getRuntimeAPI();
	private final QueryRuntimeAPI queryRuntimeAPI = AccessorUtil
			.getQueryRuntimeAPI();
	private final IdentityAPI identityAPI = AccessorUtil.getIdentityAPI();
	private final BAMAPI bamapi = AccessorUtil.getBAMAPI();

	@Override
	public String getContext() {
		return "BonitaStore";
	}

	@Override
	public Object subirProceso(ClientParams clientParams, String rutaArchivo)
			throws BPMException {
		try {
			login(clientParams);
			final File barFile = new File(rutaArchivo);
			BusinessArchive businessArchive = BusinessArchiveFactory
					.getBusinessArchive(barFile);
			ProcessDefinition pd = managementAPI.deploy(businessArchive);
			logout();
			return pd;
		} catch (IOException e) {
			handleException(e);
		} catch (ClassNotFoundException e) {
			handleException(e);
		} catch (DeploymentException e) {
			handleException(e);
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}

	private Proceso convert(ProcessDefinition pd) {
		Proceso p = new Proceso();
		p.setDescripcion(pd.getDescription());
		p.setNombre(pd.getName());
		p.setVersion(pd.getVersion());
		List<UserBpm> participantes = new ArrayList<UserBpm>();
		for (ParticipantDefinition participantDefinition : pd.getParticipants()) {
			UserBpm u = new UserBpm();
			u.setUsername(participantDefinition.getName());
			participantes.add(u);
		}
		p.setParticipantes(participantes);
		p.setEstado(EnumProcessState.getState(pd.getState().name()));
		p.setId(pd.getUUID().toString());
		return p;
	}

	private List<Comentario> convert(List<Comment> commentList)
			throws BPMException {
		List<Comentario> comentarios = new ArrayList<Comentario>();
		for (Comment c : commentList) {
			Comentario comentario = new Comentario();
			comentario.setDescripcion(c.getMessage());
			try {
				User u = identityAPI.getUser(c.getUserId());
				comentario.setUserBpm(convert(u));
			} catch (UserNotFoundException e) {
				handleException(e, "Usuario no encontrado");
			}
			comentario.setFechaIngreso(c.getDate());
			comentarios.add(comentario);
		}
		return comentarios;
	}

	private Tarea convert(TaskInstance taskInstance) throws BPMException {
		Tarea tarea = new Tarea();
		tarea.setId(taskInstance.getUUID().toString());
		tarea.setNombre(taskInstance.getActivityName());
		tarea.setEstado(EnumTareaState.getState(taskInstance.getState().name()));
		tarea.setPrioridad(EnumPrioridad.getPrioridad(tarea.getEstado(),
				taskInstance.getPriority()));
		tarea.setFechaCreacion(taskInstance.getCreatedDate());
		tarea.setFechaEsperadaFinalizacion(taskInstance.getExpectedEndDate());
		tarea.setFechaInicio(taskInstance.getStartedDate());
		tarea.setFechaAsignacion(taskInstance.getReadyDate());
		tarea.setProceso(taskInstance.getProcessDefinitionUUID()
				.getProcessName());
		tarea.setFinalizadoPor(taskInstance.getEndedBy());
		tarea.setIniciadoPor(taskInstance.getStartedBy());
		try {
			String descripcion = (String) taskInstance
					.getLastKnownVariableValues().get("descripcion");
			if (descripcion != null) {
				tarea.setDescripcion(descripcion);
			} else {
				tarea.setDescripcion(taskInstance.getActivityDescription());
			}
		} catch (Exception e) {
			tarea.setDescripcion(taskInstance.getActivityDescription());
		}

		tarea.setFechaFinalizacion(taskInstance.getEndedDate());
		try {
			String userId = taskInstance.getLastAssignUpdate()
					.getAssignedUserId();

			if (userId != null) {
				User u = identityAPI.getUser(userId);
				tarea.setAsignadaA(convert(u));
				System.out.println(userId + " " + u.getUsername());
			} else {
				System.out.println("USER NULL " + tarea.getNombre() + " "
						+ userId + " " + tarea.getEstado() + " "
						+ taskInstance.getTaskUser());
				Set<String> candidates = taskInstance.getTaskCandidates();
				if (candidates.size() == 1) {
					User u = identityAPI.getUser(candidates.iterator().next());
					tarea.setAsignadaA(convert(u));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		tarea.setInstanciaId(taskInstance.getProcessInstanceUUID().toString());
		try {
			tarea.setComentarios(convert(queryRuntimeAPI
					.getActivityInstanceCommentFeed(taskInstance.getUUID())));
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}

		// TODO
		taskInstance.getLastKnownVariableValues();
		long time = 0;

		if (tarea.getFechaFinalizacion() == null
				&& tarea.getFechaAsignacion() != null) {
			time = new Date().getTime() - tarea.getFechaAsignacion().getTime();
		} else if (tarea.getFechaAsignacion() != null) {
			time = tarea.getFechaFinalizacion().getTime()
					- tarea.getFechaAsignacion().getTime();
		}
		long dias = time / (1000 * 60 * 60 * 24);
		long horas = (time / (1000 * 60 * 60)) - (dias * 24);
		long minutos = (time / (1000 * 60)) - (dias * 24 * 60) - (horas * 60);
		tarea.setTiempoEjecucion("Dias: " + dias + " Horas: " + horas
				+ " Min: " + minutos);
		setVencida(tarea);
		return tarea;
	}

	private InstanciaProceso convert(ClientParams clientParams,ProcessInstance processInstance)
			throws BPMException {
		login(clientParams);
		InstanciaProceso instanciaProceso = new InstanciaProceso();
		instanciaProceso.setEstado(EnumInstanceState.getState(processInstance
				.getInstanceState().name()));
		instanciaProceso.setFinalizadoPor(processInstance.getEndedBy());
		instanciaProceso.setFechaFinalizado(processInstance.getEndedDate());
		instanciaProceso.setIniciadoPor(processInstance.getStartedBy());
		instanciaProceso.setFechaIniciado(processInstance.getStartedDate());
		instanciaProceso.setFechaUltimaActualizacion(processInstance
				.getLastUpdate());
		List<Tarea> taskList = new ArrayList<Tarea>();
		for (TaskInstance taskInstance : processInstance.getTasks()) {
			taskList.add(convert(taskInstance));
		}
		instanciaProceso.setTareas(taskList);

		List<Comentario> comentarios = convert(processInstance.getCommentFeed());
		instanciaProceso.setComentarios(comentarios);
		Map<String, Object> varValues = processInstance
				.getLastKnownVariableValues();
		String descripcion = (String) varValues.get("descripcion");
		instanciaProceso.setDescripcion(descripcion);
		instanciaProceso.setProceso(processInstance.getProcessDefinitionUUID()
				.getProcessName()
				+ " (" + processInstance.getNb() + ")");
		instanciaProceso.setId(processInstance.getProcessInstanceUUID()
				.toString());

		long time = 0;

		if (instanciaProceso.getFechaFinalizado() == null
				&& instanciaProceso.getFechaIniciado() != null) {
			time = new Date().getTime()
					- instanciaProceso.getFechaIniciado().getTime();
		} else if (instanciaProceso.getFechaIniciado() != null) {
			time = instanciaProceso.getFechaFinalizado().getTime()
					- instanciaProceso.getFechaIniciado().getTime();
		}
		long dias = time / (1000 * 60 * 60 * 24);
		long horas = (time / (1000 * 60 * 60)) - (dias * 24);
		long minutos = (time / (1000 * 60)) - (dias * 24 * 60) - (horas * 60);
		instanciaProceso.setTiempoEjecucion("Dias: " + dias + " Horas: "
				+ horas + " Min: " + minutos);
		logout();
		return instanciaProceso;
	}

	private List<InstanciaProceso> filter(
			List<InstanciaProceso> listaInstanciaProceso,
			EnumInstanceState instanceState) {
		List<InstanciaProceso> newInstanciaProcesoList = new ArrayList<InstanciaProceso>();
		for (InstanciaProceso instanciaProceso : listaInstanciaProceso) {
			if (instanceState.equals((instanciaProceso.getEstado()))) {
				newInstanciaProcesoList.add(instanciaProceso);
			}
		}
		return newInstanciaProcesoList;
	}

	private List<InstanciaProceso> listaInstanciaProceso(
			Set<ProcessInstance> processInstanceList) throws BPMException {
		List<InstanciaProceso> instanciaProcesoList = new ArrayList<InstanciaProceso>();
		for (ProcessInstance processInstance : processInstanceList) {
			instanciaProcesoList.add(convert(new ClientParams(),processInstance));
		}
		ordenarListaInstancia(instanciaProcesoList);
		return instanciaProcesoList;
	}

	private ProcessDefinition getProcessDefinition(ClientParams clientParams,
			SpiritProcessDefinition spiritProcess) throws BPMException {
		try {
			login(clientParams);

			// TODO:DETERMINAR ESTADO Y VERIFICAR SI ESTA ACTIVO
			ProcessDefinition ob = queryDefinitionAPI
					.getLastProcess(spiritProcess.getNombreProceso());
			logout();
			return ob;

		} catch (ProcessNotFoundException e) {
			handleException(e, "Proceso no encontrado");
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}

	public List<Proceso> obtenerProcesos(ClientParams clientParams)
			throws BPMException {
		login(clientParams);
		List<Proceso> listaProcesos = new ArrayList<Proceso>();
		Set<ProcessDefinition> processDefinitionSet = queryDefinitionAPI
				.getProcesses();

		for (ProcessDefinition processDefinition : processDefinitionSet) {
			listaProcesos.add(convert(processDefinition));
		}
		logout();
		return listaProcesos;
	}

	private InstanciaProceso instantiateProcess(ClientParams clientParams,
			SpiritProcessDefinition spiritProcess, Map<String, Object> mapaData)
			throws BPMException {

		ProcessDefinition processDefinition = getProcessDefinition(
				clientParams, spiritProcess);
		login(clientParams);
		ProcessDefinitionUUID processDefinitionUUID = processDefinition
				.getUUID();
		ProcessInstanceUUID processInstanceUUID = null;
		try {
			if (mapaData == null) {
				processInstanceUUID = runtimeAPI
						.instantiateProcess(processDefinitionUUID);
			} else {
				processInstanceUUID = runtimeAPI.instantiateProcess(
						processDefinitionUUID, mapaData);
			}
			ProcessInstance processInstance = queryRuntimeAPI
					.getProcessInstance(processInstanceUUID);
			InstanciaProceso instanciaProceso = convert(clientParams,processInstance);
			logout();
			return instanciaProceso;
		} catch (ProcessNotFoundException e) {
			handleException(e, "Proceso no encontrado");
		} catch (VariableNotFoundException e) {
			handleException(e, "Variable no encontrada");
		} catch (Exception e) {
			handleException(e);
		}
		logout();
		return null;
	}

	@Override
	public Proceso obtenerProceso(ClientParams clientParams,
			SpiritProcessDefinition spiritProcess) throws BPMException {
		return convert(getProcessDefinition(clientParams, spiritProcess));
	}

	@Override
	public InstanciaProceso iniciarProceso(ClientParams clientParams,
			SpiritProcessDefinition spiritProcess, Map<String, Object> mapaData)
			throws BPMException {
		return instantiateProcess(clientParams, spiritProcess, mapaData);
	}

	@Override
	public InstanciaProceso iniciarProceso(ClientParams clientParams,
			SpiritProcessDefinition spiritProcess) throws BPMException {
		return instantiateProcess(clientParams, spiritProcess, null);
	}

	@Override
	public List<InstanciaProceso> consultarProcesosActivos(
			ClientParams clientParams, EnumInstanceState stateFilter)
			throws BPMException {
		login(clientParams);
		List<InstanciaProceso> listaInstanciaProceso = listaInstanciaProceso(queryRuntimeAPI
				.getProcessInstances());
		logout();
		if (stateFilter == null || EnumInstanceState.ALL.equals(stateFilter)) {
			return listaInstanciaProceso;
		} else {
			return filter(listaInstanciaProceso, stateFilter);
		}

	}

	private ClientParams getUserAdmin() {
		ClientParams clientParams = new ClientParams();
		clientParams.setUser("admin");
		clientParams.setPassword("bpm");
		return clientParams;
	}

	@Override
	public InstanciaProceso buscarPorVariable(ClientParams clientParams,
			String variableName, Object value) throws BPMException {
		Object tempValue = null;
		ProcessInstance processInstance = null;
		Map<String, Object> mapaVariables = null;

		List<InstanciaProceso> instanciaProcesoList = consultarProcesosActivos(
				getUserAdmin(), EnumInstanceState.INICIADA);
		login(getUserAdmin());
		for (InstanciaProceso instanciaProceso : instanciaProcesoList) {

			processInstance = encontrarProcessInstanceById(getUserAdmin(),
					instanciaProceso);
			mapaVariables = processInstance.getLastKnownVariableValues();
			tempValue = mapaVariables.get(variableName);
			if (tempValue != null && tempValue.equals(value)) {
				logout();
				return instanciaProceso;
			}
		}
		logout();

		// FORMA 2
		/*
		 * List<Tarea> tareaList = consultarTareas(clientParams); for (Tarea
		 * tarea : tareaList) { TaskInstance task =
		 * encontrarTareaById(clientParams, tarea); try { processInstance =
		 * queryRuntimeAPI.getProcessInstance(task .getProcessInstanceUUID()); }
		 * catch (InstanceNotFoundException e) { handleException(e, "Instancia
		 * no encontrada"); } mapaVariables =
		 * processInstance.getLastKnownVariableValues(); tempValue =
		 * mapaVariables.get(variableName); if (tempValue != null &&
		 * tempValue.equals(value)) { return convert(processInstance); } }
		 */

		return null;
	}

	public InstanciaProceso buscarPadreDeInstancia(
			InstanciaProceso instanciaProceso) throws BPMException {
		ClientParams ua = getUserAdmin();
		login(ua);
		ProcessInstance processInstance = encontrarProcessInstanceById(
				getUserAdmin(), instanciaProceso);

		while (processInstance.getParentInstanceUUID() != null) {
			try {
				processInstance = queryRuntimeAPI
						.getProcessInstance(processInstance
								.getParentInstanceUUID());
			} catch (InstanceNotFoundException e) {
				e.printStackTrace();
				logout();
				throw new BPMException("Instancia de proceso no encontrada!!");
			}
		}
		instanciaProceso = convert(ua,processInstance);
		logout();
		return instanciaProceso;

	}

	@Override
	public List<InstanciaProceso> consultarProcesosActivos(
			ClientParams clientParams, SpiritProcessDefinition spiritProcess,
			EnumInstanceState stateFilter) throws BPMException {
		try {
			ProcessDefinition pd = getProcessDefinition(clientParams,
					spiritProcess);
			login(clientParams);

			List<InstanciaProceso> listaInstanciaProceso = listaInstanciaProceso(queryRuntimeAPI
					.getProcessInstances(pd.getUUID()));
			logout();
			if (stateFilter == null
					|| EnumInstanceState.ALL.equals(stateFilter)) {
				return listaInstanciaProceso;
			} else {
				return filter(listaInstanciaProceso, stateFilter);
			}
		} catch (ProcessNotFoundException e) {
			handleException(e, "Proceso no encontrado");
		} catch (BPMException e) {
			throw e;
		}
		return null;
	}

	private ClientParams getUserClientParams(UserBpm userBpm) {
		ClientParams clientParams = new ClientParams();
		clientParams.setUser(userBpm.getUsername());
		clientParams.setPassword(userBpm.getPassword());
		return clientParams;
	}

	private TaskInstance encontrarTareaById(ClientParams clientParams,
			Tarea tarea) throws BPMException {
		UserBpm userBpm = tarea.getAsignadaA();
		ClientParams params = clientParams;
		if (userBpm != null) {
			params = getUserClientParams(tarea.getAsignadaA());
			System.out.println("Buscando tarea de: " + userBpm.getUsername());
		}
		login(params);
		Collection<TaskInstance> tasks = listaTaskInstance((ActivityState[]) null);
		for (TaskInstance taskInstance : tasks) {
			System.out.println(taskInstance.getUUID().toString());
			if (taskInstance.getUUID().toString().equalsIgnoreCase(
					tarea.getId())) {

				return taskInstance;
			}
		}
		logout();
		System.out.println("ERROR GRAVE!! " + tarea.getId());
		throw new BPMException("Tarea no encontrada");
	}

	@Override
	public void setValue(ClientParams clientParams, Tarea tarea,
			String paramName, Object value) throws BPMException {
		try {
			login(clientParams);
			TaskInstance taskInstance = encontrarTareaById(getUserAdmin(), tarea);
			runtimeAPI.setActivityInstanceVariable(taskInstance.getUUID(),
					paramName, value);
			logout();
		} catch (ActivityNotFoundException e) {
			handleException(e, "Tarea no encontrada");
		} catch (VariableNotFoundException e) {
			handleException(e, "Variable no encontrada");
		}
	}

	@Override
	public Object getValue(ClientParams clientParams, Tarea tarea,
			String paramName) throws BPMException {
		TaskInstance taskInstance = encontrarTareaById(clientParams, tarea);
		login(clientParams);
		Object paramValue = taskInstance.getLastKnownVariableValues().get(
				paramName);
		logout();
		return paramValue;
	}

	@Override
	public void asignarTarea(ClientParams clientParams, Tarea tarea,
			String username) throws BPMException {
		try {
			TaskInstance taskInstance = encontrarTareaById(clientParams, tarea);
			login(clientParams);
			runtimeAPI.assignTask(taskInstance.getUUID(), username);
			logout();
		} catch (TaskNotFoundException e) {
			handleException(e, "Tarea no encontrada");
		}
	}

	@Override
	public void iniciarTarea(ClientParams clientParams, Tarea tarea)
			throws BPMException {
		try {
			TaskInstance taskInstance = encontrarTareaById(clientParams, tarea);
			login(clientParams);
			runtimeAPI.startTask(taskInstance.getUUID(), true);
			logout();
		} catch (TaskNotFoundException e) {
			handleException(e, "Tarea no encontrada");
		} catch (IllegalTaskStateException e) {
			handleException(e, "Estado de la tarea no es el adecuado..");
		}
	}

	@Override
	public void terminarTarea(ClientParams clientParams, Tarea tarea)
			throws BPMException {
		try {
			TaskInstance taskInstance = encontrarTareaById(clientParams, tarea);
			login(clientParams);
			runtimeAPI.finishTask(taskInstance.getUUID(), true);
			logout();
		} catch (TaskNotFoundException e) {
			handleException(e, "Tarea no encontrada");
		} catch (IllegalTaskStateException e) {
			handleException(e, "Estado de la tarea no es el adecuado..");
		}
	}

	@Override
	public void suspenderTarea(ClientParams clientParams, Tarea tarea)
			throws BPMException {
		try {
			TaskInstance taskInstance = encontrarTareaById(clientParams, tarea);
			login(clientParams);
			runtimeAPI.suspendTask(taskInstance.getUUID(), true);
			logout();
		} catch (TaskNotFoundException e) {
			handleException(e, "Tarea no encontrada");
		} catch (IllegalTaskStateException e) {
			handleException(e, "Estado de la tarea no es el adecuado..");
		}
	}

	@Override
	public void reaunudarTarea(ClientParams clientParams, Tarea tarea)
			throws BPMException {
		try {
			TaskInstance taskInstance = encontrarTareaById(clientParams, tarea);
			login(clientParams);
			runtimeAPI.resumeTask(taskInstance.getUUID(), true);
			logout();
		} catch (TaskNotFoundException e) {
			handleException(e, "Tarea no encontrada");
		} catch (IllegalTaskStateException e) {
			handleException(e, "Estado de la tarea no es el adecuado..");
		}
	}

	@Override
	public void iniciarTerminarTarea(ClientParams clientParams, Tarea tarea)
			throws BPMException {
		try {
			TaskInstance taskInstance = encontrarTareaById(clientParams, tarea);
			login(clientParams);
			runtimeAPI.startTask(taskInstance.getUUID(), true);
			runtimeAPI.finishTask(taskInstance.getUUID(), true);
			logout();
		} catch (TaskNotFoundException e) {
			handleException(e, "Tarea no encontrada");
		} catch (IllegalTaskStateException e) {
			handleException(e, "Estado de la tarea no es el adecuado..");
		}
	}

	public InstanciaProceso getInstanciaProcesoById(
			ClientParams clientParams, InstanciaProceso instanciaProceso)
			throws BPMException{
		
		ProcessInstance pi = encontrarProcessInstanceById(clientParams, instanciaProceso);
		InstanciaProceso ip = convert(clientParams,pi); 
		return ip;
		
	}
	
	public Tarea getTareaActivaByInstanciaProceso(InstanciaProceso instanciaProceso)
		throws BPMException{
		try{
			List<Tarea> tareas = instanciaProceso.getTareas();
			for ( Tarea t : tareas ){
				if ( t.getEstado() == EnumTareaState.EJECUCION ){
					return t;
				}
			}
			return null;
		} catch(Exception e){
			throw new BPMException("Error en busqueda de Tarea en Ejecucion!!");
		}
	}
	
	private ProcessInstance encontrarProcessInstanceById(
			ClientParams clientParams, InstanciaProceso instanciaProceso)
			throws BPMException {
		login(clientParams);
		Collection<ProcessInstance> processInstances = queryRuntimeAPI
				.getProcessInstances();
		for (ProcessInstance processInstance : processInstances) {
			if (processInstance.getUUID().toString().equalsIgnoreCase(
					instanciaProceso.getId())) {
				logout();
				return processInstance;
			}
		}
		logout();
		System.out.println("ERROR GRAVE!! " + instanciaProceso.getId());
		throw new BPMException("Instancia de Proceso no encontrada");
	}

	@Override
	public void cancelarInstanciaProceso(ClientParams clientParams,
			InstanciaProceso instanciaProceso) throws BPMException {
		try {

			login(getUserAdmin());
			ProcessInstance processInstance = encontrarProcessInstanceById(
					clientParams, instanciaProceso);

			runtimeAPI.cancelProcessInstance(processInstance
					.getProcessInstanceUUID());
			logout();
		} catch (Exception e) {
			handleException(e, "Instancia no se pudo cancelar");
		}
	}

	private Collection<TaskInstance> listaTaskInstance(
			ActivityState... filtroParam) throws BPMException {
		Vector<TaskInstance> allTaskInstance = new Vector<TaskInstance>();
		ActivityState[] filtro = ActivityState.values();
		if (filtroParam != null && filtroParam.length > 0) {
			filtro = filtroParam;
		}
		for (ActivityState activityState : filtro) {
			allTaskInstance.addAll(queryRuntimeAPI.getTaskList(activityState));
		}

		return allTaskInstance;
	}

	private List<Tarea> listarTareas(Collection<TaskInstance> tasks)
			throws BPMException {
		List<Tarea> tareas = new ArrayList<Tarea>();
		for (TaskInstance taskInstance : tasks) {
			tareas.add(setVencida(convert(taskInstance)));
		}
		ordenarListaTareas(tareas);
		return tareas;
	}

	@Override
	public List<Tarea> consultarTareas(ClientParams clientParams)
			throws BPMException {
		login(clientParams);
		Collection<TaskInstance> tasks = listaTaskInstance((ActivityState[]) null);
		List<Tarea> tareas = listarTareas(tasks);
		logout();
		return tareas;
	}

	@Override
	public List<Tarea> consultarTareasPendientes(ClientParams clientParams)
			throws BPMException {
		login(clientParams);
		Collection<TaskInstance> tasks = listaTaskInstance(ActivityState.READY,
				ActivityState.EXECUTING, ActivityState.SUSPENDED);
		List<Tarea> tareas = listarTareas(tasks);
		logout();
		return tareas;
	}

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy");

	private static Date eliminarHora(Date date) {
		try {
			return dateFormat.parse(dateFormat.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@SuppressWarnings("deprecation")
	public static int compararFechas(java.sql.Date fecha1, java.sql.Date fecha2) {
		int comparacion = 0;
		int anioFecha1 = fecha1.getYear();
		int mesFecha1 = fecha1.getMonth();
		int diaFecha1 = fecha1.getDate();
		int anioFecha2 = fecha2.getYear();
		int mesFecha2 = fecha2.getMonth();
		int diaFecha2 = fecha2.getDate();

		if (anioFecha1 > anioFecha2) {
			comparacion = 1;
		} else if (anioFecha1 < anioFecha2) {
			comparacion = -1;
		} else {
			if (mesFecha1 > mesFecha2) {
				comparacion = 1;
			} else if (mesFecha1 < mesFecha2) {
				comparacion = -1;
			} else {
				if (diaFecha1 > diaFecha2) {
					comparacion = 1;
				} else if (diaFecha1 < diaFecha2) {
					comparacion = -1;
				} else {
					comparacion = 0;
				}
			}
		}

		return comparacion;
	}

	private static long getDiasDiferencia(Date fecha1, Date fecha2) {
		long diferenciaMilisegundos = (fecha1.before(fecha2)) ? fecha2
				.getTime()
				- fecha1.getTime() : fecha1.getTime() - fecha2.getTime();
		long diferenciaDias = diferenciaMilisegundos / (1000 * 60 * 60 * 24);
		return diferenciaDias;
	}

	private Tarea setVencida(Tarea tarea) {
		Date fechaEsperada = tarea.getFechaEsperadaFinalizacion();
		if (fechaEsperada == null) {
			return tarea;
		}
		int orderIndex = 0;
		tarea.setObservacionFechaVencida("");
		tarea.setNivelVencido(EnumNivelVencido.NO_APLICA);
		EnumTareaState estado = tarea.getEstado();
		if (EnumTareaState.ABORTADA.equals(estado)
				|| EnumTareaState.CANCELADA.equals(estado)
				|| EnumTareaState.TERMINADA.equals(estado)) {
			orderIndex = 0;
		} else {
			Date fechaHoy = eliminarHora(new Date());
			Date fechaEsperadaSinHora = eliminarHora(fechaEsperada);
			System.out.println(fechaHoy);
			System.out.println(fechaEsperadaSinHora);
			int compare = compararFechas(new java.sql.Date(fechaHoy.getTime()),
					new java.sql.Date(fechaEsperadaSinHora.getTime()));
			if (compare < 0) {
				tarea.setNivelVencido(EnumNivelVencido.A_TIEMPO);
				orderIndex = 0;
			} else if (compare == 0) {
				// PARA HOY
				tarea.setNivelVencido(EnumNivelVencido.PARA_HOY);
				tarea.setObservacionFechaVencida("Tarea esperada para hoy");
				orderIndex += 1;
			} else {
				long diasDifeerncia = getDiasDiferencia(fechaEsperada, fechaHoy) + 1;
				tarea.setNivelVencido(EnumNivelVencido.VENCIDO);
				tarea.setObservacionFechaVencida("Tarea esparada hace :"
						+ diasDifeerncia + " dia(s)");
				orderIndex += 2;
			}
		}
		EnumPrioridad prioridad = tarea.getPrioridad();
		if (EnumPrioridad.NORMAL.equals(prioridad)) {
			orderIndex += 1;
		} else if (EnumPrioridad.ALTA.equals(prioridad)) {
			orderIndex += 2;
		} else if (EnumPrioridad.URGENTE.equals(prioridad)) {
			orderIndex += 3;
		}
		tarea.setOrderIndex(orderIndex);
		return tarea;
	}

	private Comparator<Tarea> comparadorTarea = new Comparator<Tarea>() {
		@Override
		public int compare(Tarea o1, Tarea o2) {
			// ORDENAR PRIMERO LAS VENCIDAS Y LUEGO POR PRIORIDAD
			if (o1.getOrderIndex() > o2.getOrderIndex()) {
				return -1;
			} else if (o1.getOrderIndex() < o2.getOrderIndex()) {
				return 1;
			} else {
				return 0;
			}

		}
	};

	private Comparator<InstanciaProceso> comparadorInstanciasProceso = new Comparator<InstanciaProceso>() {
		@Override
		public int compare(InstanciaProceso o1, InstanciaProceso o2) {
			return o2.getFechaIniciado().compareTo(o1.getFechaIniciado());
		}
	};

	private void ordenarListaInstancia(
			List<InstanciaProceso> instanciaProcesoList) {
		Collections.sort((ArrayList<InstanciaProceso>) instanciaProcesoList,
				comparadorInstanciasProceso);
	}

	private void ordenarListaTareas(List<Tarea> tareas) {
		Collections.sort((ArrayList<Tarea>) tareas, comparadorTarea);
	}

	@Override
	public BamApi getBam(ClientParams clientParams) throws BPMException {
		/*
		 * Calendar cal = Calendar.getInstance(); cal.setTime(new Date());
		 * cal.add(Calendar.MONTH, -1); Date fechaDesde = cal.getTime(); int
		 * casosTerminados = 0; int casosEnRiesgo = 0; for (int i = 0; i < 3;
		 * i++) { casosTerminados += bamapi.getNumberOfUserFinishedSteps(i,
		 * fechaDesde); casosEnRiesgo += bamapi.getNumberOfUserStepsAtRisk(i); }
		 * BamApi bamApi = new BamApi(); bamApi.setFechaDesde(fechaDesde);
		 * bamApi.setCasosPendientes(bamapi.getNumberOfOpenSteps());
		 * bamApi.setCasosTerminados(casosTerminados);
		 * bamApi.setCasosVencidos(bamapi.getNumberOfOverdueSteps());
		 * bamApi.setCasosEnRiesgo(casosEnRiesgo);
		 */
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -1);
		Date fechaDesde = cal.getTime();
		BamApi bamApi = new BamApi();
		bamApi.setFechaDesde(fechaDesde);
		List<Tarea> tareasList = consultarTareas(clientParams);
		int casosPendientes = 0;
		int casosTerminados = 0;
		int casosVencidos = 0;
		int casosEnRiesgo = 0;

		for (Tarea t : tareasList) {
			if (EnumTareaState.PENDIENTE.equals(t.getEstado())) {
				casosPendientes++;
			} else if (EnumTareaState.TERMINADA.equals(t.getEstado())) {
				casosTerminados++;
			}
			EnumNivelVencido nivelVencido = t.getNivelVencido();
			if (nivelVencido != null) {
				if (EnumNivelVencido.VENCIDO.equals(nivelVencido)) {
					casosVencidos++;
				} else if (EnumNivelVencido.PARA_HOY.equals(nivelVencido)) {
					casosEnRiesgo++;
				}
			}
		}
		bamApi.setCasosPendientes(casosPendientes);
		bamApi.setCasosTerminados(casosTerminados);
		bamApi.setCasosVencidos(casosVencidos);
		bamApi.setCasosEnRiesgo(casosEnRiesgo);
		return bamApi;
	}

	@Override
	public BamAdminApi getAdminBam(ClientParams clientParams)
			throws BPMException {
		login(clientParams);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -1);
		Date fechaDesde = cal.getTime();
		List<InstanciaProceso> instanciaProcesoList = consultarProcesosActivos(
				clientParams, null);
		int instanciasActivas = 0;
		int instanciasTerminadas = 0;

		for (InstanciaProceso instanciaProceso : instanciaProcesoList) {
			if (instanciaProceso.getFechaIniciado().after(fechaDesde)) {
				if (EnumInstanceState.INICIADA.equals(instanciaProceso
						.getEstado())) {
					instanciasActivas++;
				} else if (EnumInstanceState.TERMINADA.equals(instanciaProceso
						.getEstado())) {
					instanciasTerminadas++;
				}
			}
		}
		BamAdminApi bamApi = new BamAdminApi();
		bamApi.setFechaDesde(fechaDesde);
		bamApi.setProcesosActivos(instanciasActivas);
		bamApi.setProcesosTerminados(instanciasTerminadas);
		logout();
		return bamApi;
	}

	private UserBpm convert(User u) {
		UserBpm userBpm = new UserBpm();
		userBpm.setUsername(u.getUsername());
		userBpm.setApellido(u.getLastName());
		userBpm.setNombre(u.getFirstName());
		userBpm.setEmail(u.getEmail());
		userBpm.setPassword(u.getPassword());
		return userBpm;
	}

	@Override
	public List<UserBpm> getUsers(ClientParams clientParams)
			throws BPMException {
		login(clientParams);
		List<UserBpm> userBpmList = new ArrayList<UserBpm>();
		for (User u : identityAPI.getUsers()) {
			userBpmList.add(convert(u));
		}
		logout();
		return userBpmList;
	}

	@Override
	public InstanciaProceso refreshInstanciaProceso(ClientParams clientParams,
			InstanciaProceso instanciaProcesoParam) throws BPMException {
		login(clientParams);
		ProcessInstance processInstance = encontrarProcessInstanceById(
				clientParams, instanciaProcesoParam);
		InstanciaProceso instanciaProceso = convert(clientParams,processInstance);
		logout();
		return instanciaProceso;
	}

	@Override
	public void commentar(ClientParams clientParams, Tarea tarea,
			String comentario) throws BPMException {
		TaskInstance taskInstance = encontrarTareaById(clientParams, tarea);
		login(clientParams);
		try {
			runtimeAPI.addComment(taskInstance.getProcessInstanceUUID(),
					taskInstance.getUUID(), comentario, clientParams.getUser());
		} catch (InstanceNotFoundException e) {
			handleException(e, "Instancia no encontrada");
		} catch (ActivityNotFoundException e) {
			handleException(e, "Tarea no encontrada");
		}
		logout();
	}

	@Override
	public Object getValueFromInstance(ClientParams clientParams, Tarea tarea,
			String paramName) throws BPMException {
		try {
			TaskInstance taskInstance = encontrarTareaById(clientParams, tarea);
			login(clientParams);
			ProcessInstance processInstance;
			processInstance = queryRuntimeAPI.getProcessInstance(taskInstance
					.getProcessInstanceUUID());
			Object paramValue = processInstance.getLastKnownVariableValues()
					.get(paramName);
			logout();
			return paramValue;
		} catch (InstanceNotFoundException e) {
			handleException(e, "Instancia no encontrada");
		}
		return null;
	}

	@Override
	public void setInstanceVariable(ClientParams clientParams, Tarea tarea,
			String paramName, Object value) throws BPMException {
		try {
			TaskInstance taskInstance = encontrarTareaById(clientParams, tarea);
			login(clientParams);
			runtimeAPI.setProcessInstanceVariable(taskInstance
					.getProcessInstanceUUID(), paramName, value);
			logout();
		} catch (VariableNotFoundException e) {
			handleException(e, "Variable no encontrada");
		} catch (InstanceNotFoundException e) {
			handleException(e, "Instancia no encontrada no encontrada");
		}
	}

	public InstanciaProceso getInstanciaProcesoByTarea(Tarea t) throws BPMException {
		ClientParams clientParams = new ClientParams();
		//clientParams.setPassword("bpm");
		//clientParams.setUser(t.getAsignadaA().getUsername());
		ProcessInstance pi = encontrarProcessInstanceById(clientParams,
			new InstanciaProceso(t.getInstanciaId()));
		InstanciaProceso ip = convert(clientParams,pi); 
		return ip;
	}
}
