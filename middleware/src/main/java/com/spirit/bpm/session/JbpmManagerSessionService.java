package com.spirit.bpm.session;



import java.util.HashMap;
import java.util.List;

import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

import com.spirit.bpm.compras.exception.ComprasBpmException;
import com.spirit.bpm.compras.exception.NoExisteUsuarioBpmException;

/**
 * The <code>JbpmManagerSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:45 $
 */
public interface JbpmManagerSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	//public long iniciarProceso(String nombreProceso, String nombrePanelSiguiente) throws ComprasBpmException;
	public void actualizarDocumentoIdInstanciaProceso(long idProcessInstance, String documentoId) throws ComprasBpmException;
	public void actualizarVariableInstanciaProceso(long idInstanciaProceso,String nombreObjeto,Object objeto) throws ComprasBpmException;
	public void actualizarVariableTareaByIdInstanciaProceso(long idInstanciaProceso,String nombreObjeto,Object objeto) throws ComprasBpmException;
	public void actualizarVariableTarea(long idTarea,String nombreObjeto,Object objeto) throws ComprasBpmException;
	public long iniciarProceso(String nombreProceso) throws ComprasBpmException;
	public void borrarInstanciaProcesoByIdTarea(long idTarea) throws ComprasBpmException;
	public void borrarInstanciaTareaByIdTarea(long idTarea) throws ComprasBpmException;
	public void borrarInstanciaProcesoByVariable(String nombreProceso,String nombreVariable, String valorVariable) throws ComprasBpmException;
	public void autorizarTareaBiopcional(boolean autorizar,long idTarea,String nombrePanelSiguiente,String palabraAceptado,String palabraNegado) throws ComprasBpmException;
	public void terminarTarea(long idTarea,String transicion) throws ComprasBpmException;
	public void volver(long idTarea,String transicion) throws ComprasBpmException;
	public long realizarAutorizacionTarea(long idTarea,String nombrePanelSiguiente,HashMap<String, String> parametros) throws ComprasBpmException;
	public ProcessInstance buscarInstanciaProcesoByVariable(String nombreProceso, String nombreVariable, String valorVariable) throws ComprasBpmException;
	public List<TaskInstance> buscarInstanciaTarea(String nombreProceso, String nombreVariableAutorizador
			, String idAutorizador, String nombreTarea) throws ComprasBpmException, NoExisteUsuarioBpmException;
	public HashMap<String,String> getInformacionTarea(long idTarea) throws ComprasBpmException;
	public HashMap<String,String> getInformacionProcesoByIdTarea(long idTarea,List parametros) throws ComprasBpmException;
	public long obtenerIdInstanciaProcesoByIdTarea(long  idTarea) throws ComprasBpmException;
}
