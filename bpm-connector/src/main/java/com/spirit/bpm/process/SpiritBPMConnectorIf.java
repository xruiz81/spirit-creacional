package com.spirit.bpm.process;

import java.util.List;
import java.util.Map;

import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.BamAdminApi;
import com.spirit.bpm.process.elements.BamApi;
import com.spirit.bpm.process.elements.ClientParams;
import com.spirit.bpm.process.elements.InstanciaProceso;
import com.spirit.bpm.process.elements.Proceso;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.bpm.process.elements.UserBpm;
import com.spirit.bpm.process.elemets.state.EnumInstanceState;

public interface SpiritBPMConnectorIf {

	public Object subirProceso(ClientParams clientParams, String rutaArchivo)
			throws BPMException;

	public Proceso obtenerProceso(ClientParams clientParams,
			SpiritProcessDefinition spiritProcess) throws BPMException;

	public InstanciaProceso iniciarProceso(ClientParams clientParams,
			SpiritProcessDefinition spiritProcess) throws BPMException;

	public InstanciaProceso iniciarProceso(ClientParams clientParams,
			SpiritProcessDefinition spiritProcess, Map<String, Object> mapaData)
			throws BPMException;

	public String getContext();

	public List<Tarea> consultarTareasPendientes(ClientParams clientParams)
			throws BPMException;

	public void setValue(ClientParams clientParams, Tarea tarea,
			String paramName, Object value) throws BPMException;

	public void asignarTarea(ClientParams clientParams, Tarea tarea,
			String username) throws BPMException;

	public void terminarTarea(ClientParams clientParams, Tarea tarea)
			throws BPMException;
	
	public void login(ClientParams clientParams) throws BPMException;
	
	public void logout() throws BPMException;

	public List<InstanciaProceso> consultarProcesosActivos(
			ClientParams clientParams, SpiritProcessDefinition spiritProcess,
			EnumInstanceState stateFilter) throws BPMException;

	public List<InstanciaProceso> consultarProcesosActivos(
			ClientParams clientParams, EnumInstanceState stateFilter)
			throws BPMException;

	public void iniciarTerminarTarea(ClientParams clientParams, Tarea tarea)
			throws BPMException;

	public void iniciarTarea(ClientParams clientParams, Tarea tarea)
			throws BPMException;

	public Object getValue(ClientParams clientParams, Tarea tarea,
			String paramName) throws BPMException;

	public List<Tarea> consultarTareas(ClientParams clientParams)
			throws BPMException;

	public void suspenderTarea(ClientParams clientParams, Tarea tarea)
			throws BPMException;

	public void reaunudarTarea(ClientParams clientParams, Tarea tarea)
			throws BPMException;

	public BamApi getBam(ClientParams clientParams) throws BPMException;

	public List<UserBpm> getUsers(ClientParams clientParams)
			throws BPMException;

	public List<Proceso> obtenerProcesos(ClientParams clientParams)
			throws BPMException;

	public void cancelarInstanciaProceso(ClientParams clientParams,
			InstanciaProceso instanciaProceso) throws BPMException;

	public BamAdminApi getAdminBam(ClientParams clientParams)
			throws BPMException;

	public InstanciaProceso refreshInstanciaProceso(ClientParams clientParams,
			InstanciaProceso instanciaProceso) throws BPMException;

	public void commentar(ClientParams clientParams, Tarea tarea,
			String comentario) throws BPMException;
	
	public Object getValueFromInstance(ClientParams clientParams, Tarea tarea,
			String paramName) throws BPMException;

	public void setInstanceVariable(ClientParams clientParams, Tarea tarea,
			String paramName,Object value) throws BPMException;
	
	public InstanciaProceso buscarPorVariable(ClientParams clientParams,
			String variableName, Object value) throws BPMException;
	
	public InstanciaProceso buscarPadreDeInstancia(InstanciaProceso instanciaProceso) throws BPMException;
	
	public InstanciaProceso getInstanciaProcesoByTarea(Tarea t) throws BPMException;
	
	public InstanciaProceso getInstanciaProcesoById(
			ClientParams clientParams, InstanciaProceso instanciaProceso)
			throws BPMException;
	
	public Tarea getTareaActivaByInstanciaProceso(InstanciaProceso instanciaProceso)
			throws BPMException;

}
