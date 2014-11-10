package com.spirit.bpm.compras.src;

import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.jbpm.taskmgmt.exe.TaskInstance;

import com.spirit.bpm.compras.exception.ComprasBpmException;
import com.spirit.bpm.compras.exception.NoExisteUsuarioBpmException;

public interface ProcesoPrincipalCompraService {

	public void buscarCompraIdealByActor(String idActorAutorizador, JList JLista);
	
	public void cargarParametros();
	
	public List<TaskInstance> buscarAutorizarSolicitudCompraByAutorizador(
			String idAutorizador, int opcionMensaje) 
			throws ComprasBpmException, NoExisteUsuarioBpmException;
	
	public List<TaskInstance> buscarCrearOrdenCompraByAutorizador(
			String idAutorizador, int opcionMensaje) 
			throws ComprasBpmException, NoExisteUsuarioBpmException;
	
	public List<TaskInstance> buscarAutorizarOrdenCompraByAutorizador(
			String idCreadorOrdenCompra,int opcionMensaje) 
			throws ComprasBpmException, NoExisteUsuarioBpmException;
	
	public List<TaskInstance> buscarCrearCompraByAutorizador(String idCreadorOrdenCompra
			,int opcionMensaje) throws ComprasBpmException, NoExisteUsuarioBpmException;
	
	public List<TaskInstance> buscarAutorizarCompraByAutorizador(String idCreadorOrdenCompra
			,int opcionMensaje) throws ComprasBpmException, NoExisteUsuarioBpmException;
	
	public void borrarInstanciaPrincipalCompras(long valorId,String nombreVariable,int nombreEstadoActual) 
	throws ComprasBpmException;
	
	public void iniciarInstanciaPrincipalCompras(String idSolicitudCompra
			,String tipoDocumento,String descripcion) throws ComprasBpmException;
	
	public void actualizarParametrosProceso(long valorId,String nombreVarReferencia
			, HashMap parametros) throws ComprasBpmException;
	
	public void actualizarParametroTareaCompra(long valorId,String nombreVarReferencia
			, String nombreVariable, String valorVariable) throws ComprasBpmException;
	
	public void autorizarSolicitudPrincipalCompra(boolean autorizado,long idTarea)
		throws ComprasBpmException;
	
	public void guardarOrdenPrincipalCompra(long idTarea, String idOrdenCompra, String descripcion) throws ComprasBpmException;
	
	public void autorizarOrdenPrincipalCompra(boolean autorizado,long idTarea)
		throws ComprasBpmException;
	
	public void guardarCompraPrincipalCompra(long idTarea,String idCompra
			,String tipoDocumento,String descripcion) throws ComprasBpmException;
	
	
	public List<TaskInstance> getListaTareas(String idUsuario);
	
	public DefaultListModel busquedaTareas(String idUsuario,int mensaje);
	
}