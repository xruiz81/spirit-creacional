/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spirit.bpm.compras;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import com.spirit.bpm.impl.SpiritServerProcess;
import com.spirit.bpm.process.SpiritProcessDefinition;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.ClientParams;
import com.spirit.bpm.process.elements.InstanciaProceso;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.compras.session.SolicitudCompraSessionEJB;
import com.spirit.exception.GenericBusinessException;

/**
 * 
 * @author Administrador
 */
@Stateless
public class ProcesoCompraCreacionalSessionEJB extends SpiritServerProcess implements
		ProcesoCompraSessionLocal, ProcesoCompraSessionRemote {
	private static final long serialVersionUID = 1L;
	private SolicitudCompraSessionEJB solicitudCompraSessionEJB;

	/**
	 * INICIA UN PROCESO DE COMPRA, DEJA LA TAREA DE INGRESAR UNA ORDEN DE
	 * COMPRA LISTA PARA UN USUARIO ESPECIFICADO EN EL PROCESO
	 * 
	 * @param descripcion
	 * @throws BPMException
	 */
	@Override
	public InstanciaProceso iniciarProceso(ClientParams clientParams,
			String descripcion) throws BPMException {
		// INICIAR UN PROCESO DE COMPRA
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(DESCRIPCION_PARAM, descripcion);
		InstanciaProceso instanciaProceso = getSpiritBPMConnector()
				.iniciarProceso(clientParams, SpiritProcessDefinition.COMPRA,
						parametros);
		return instanciaProceso;
	}

	@Override
	public void procesarSolicitudCompra(ClientParams clientParams,
			SolicitudCompraIf solicitudCompraParamIf) throws BPMException,
			GenericBusinessException {
		// INICIA EL PROCESO SI ESTA APROBADA, Y ES LA PRIMERA VEZ QUE LA GUARDO
		// NO SI SE ACTUALIZA
		System.out.println("PROCESANDO SC: " + solicitudCompraParamIf.getId());
		SolicitudCompraIf solicitudCompraIf = solicitudCompraSessionEJB
				.getSolicitudCompra(solicitudCompraParamIf.getId());
		System.out.println("SC ACT: " + solicitudCompraParamIf.getId());
		System.out.println("SC ACT: " + solicitudCompraParamIf.getEstado());
		if (solicitudCompraIf.getEstado().equalsIgnoreCase("A")) {
			// INICIAR PROCESO
			InstanciaProceso instanciaProceso = iniciarProceso(clientParams,
					"INICIADO AUTOMATICAMENTE POR SOLICITUD DE COMPRA ["
							+ solicitudCompraIf.getCodigo() + "]");
			// GUARDAR EL ID DE LA SOLICITUD DE COMPRA EN LA TAREA DE ORDEN DE
			// COMPRA
			// PARA PODER CARGARLO EN LA PANTALLA
			Tarea tareaSolicitudCompraIf = instanciaProceso.getTareas().get(0);
			getSpiritBPMConnector().iniciarTarea(clientParams,
					tareaSolicitudCompraIf);

			getSpiritBPMConnector().setValue(
					clientParams,
					tareaSolicitudCompraIf,
					DESCRIPCION_PARAM,
					"Solicitud de Compra [" + solicitudCompraIf.getCodigo()
							+ "] ingresada");

			getSpiritBPMConnector().setValue(clientParams,
					tareaSolicitudCompraIf, SOLICITUD_COMPRA_ID_PARAM,
					solicitudCompraIf.getId());

			getSpiritBPMConnector().terminarTarea(clientParams,
					tareaSolicitudCompraIf);

			// SE PROCESO LA SOLICITUD DE COMPRA
			// PONER LOS DATOS A LA TAREA DE ORDEN DE COMPRA
			instanciaProceso = getSpiritBPMConnector().refreshInstanciaProceso(
					clientParams, instanciaProceso);
			List<Tarea> listaTareas = instanciaProceso.getTareas();
			Tarea tareaOrdenCompraIf = null;
			for (Tarea tarea : listaTareas) {
				if (tarea.getNombre().equalsIgnoreCase(TAREA_ORDEN)) {
					tareaOrdenCompraIf = tarea;
					break;
				}
			}
			if (tareaOrdenCompraIf != null) {
				getSpiritBPMConnector().setValue(clientParams,
						tareaOrdenCompraIf, SOLICITUD_COMPRA_ID_PARAM,
						solicitudCompraIf.getId());

				getSpiritBPMConnector().setValue(
						clientParams,
						tareaOrdenCompraIf,
						DESCRIPCION_PARAM,
						"Ingrese los datos a partir de la Solicitud de Compra ["
								+ solicitudCompraIf.getCodigo() + "]");
			} else {
				throw new BPMException("Tarea " + TAREA_ORDEN
						+ " no encontrada");
			}

		}
	}

	/**
	 * SE LLAMA AL GRABAR UNA ORDEN DE COMPRA, SI SE TIENE LA TAREA PARA
	 * TERMINAR SOLO COMPLETA LA TAREA CUANDO EL ESTADO ES ACEPTADO EL PROCESO
	 * YA DEBE ESTAR INICIADO
	 * 
	 * @param tarea
	 * @param ordenCompraIf
	 * @throws BPMException
	 */
	@Override
	public void procesarOrdenCompra(ClientParams clientParams, Tarea tarea,
			OrdenCompraIf ordenCompraIf) throws BPMException {
		getSpiritBPMConnector().setValue(clientParams, tarea,
				ORDEN_COMPRA_ID_PARAM, ordenCompraIf.getId());
		// SI EL ESTADO NO ES APROBADO, NO SE FINALIZA LA TAREA
		if (ordenCompraIf.getEstado().equalsIgnoreCase("A")) {
			getSpiritBPMConnector().terminarTarea(clientParams, tarea);
			InstanciaProceso instanciaProceso = getSpiritBPMConnector()
					.refreshInstanciaProceso(clientParams,
							new InstanciaProceso(tarea.getInstanciaId()));
			Tarea tareaCompra = null;
			for (Tarea t : instanciaProceso.getTareas()) {
				if (t.getNombre().equalsIgnoreCase(TAREA_COMPRA)) {
					tareaCompra = t;
					break;
				}
			}
			if (tareaCompra != null) {
				getSpiritBPMConnector().setValue(
						clientParams,
						tareaCompra,
						DESCRIPCION_PARAM,
						"Ingrese los datos de la compra a partir de la Orden de Compra[ "
								+ ordenCompraIf.getCodigo() + "]");
				getSpiritBPMConnector().setValue(clientParams, tareaCompra,
						ORDEN_COMPRA_ID_PARAM, ordenCompraIf.getId());

			} else {
				throw new BPMException("Tarea de Compra no encontrada");
			}

		}
	}

	/**
	 * INICIA AUTOMATICAMENTE UN PROCESO DE COMPRA Y TERMINA LA TAREA 1 DE
	 * INGRESO DE ORDEN DE COMPRA PARA DAR PASO A LA TAREA 2 DE INGRESO DE
	 * COMPRA
	 * 
	 * @param ordenCompraIf
	 * @throws BPMException
	 */
	@Override
	public void procesarOrdenCompra(ClientParams clientParams,
			OrdenCompraIf ordenCompraIf) throws BPMException {
		// SI EL ESTADO NO ES APROBADO, NO SE FINALIZA LA TAREA
		if (ordenCompraIf.getEstado().equalsIgnoreCase("A")) {
			// TODO: BUSCAR SI EL INGRESO NO PERTENECE A UNA TAREA ANTERIOR
			// PRESENTAR UN DIALOGO CON LAS TAREAS

			// SI NO HAY TONCES PREGUNTO SI DEBO CREAR UN NUEVO PROCESO
			InstanciaProceso instanciaProceso = iniciarProceso(clientParams,
					"PROCESO DE COMPRA INICIADO AUTOMATICAMENTE");
			Tarea tareaOrdenCompra = instanciaProceso.getTareas().get(0);
			// TOME EL PRIMERO PORQUE SOLO HAY UNA TAREA ASOCIADA A LA ORDEN DE
			// COMPRA

			// SETEAR LAS VARIABLES
			getSpiritBPMConnector().setValue(clientParams, tareaOrdenCompra,
					DESCRIPCION_PARAM,
					"INGRESAR LA ORDEN DE COMPRA " + ordenCompraIf.getCodigo());

			getSpiritBPMConnector().setValue(clientParams, tareaOrdenCompra,
					ORDEN_COMPRA_ID_PARAM, ordenCompraIf.getId());

			// INICIAR Y TERMINAR LA TAREA
			getSpiritBPMConnector().iniciarTerminarTarea(clientParams,
					tareaOrdenCompra);

		}
	}

	public void setSolicitudCompraSessionEJB(
			SolicitudCompraSessionEJB solicitudCompraSessionEJB) {
		this.solicitudCompraSessionEJB = solicitudCompraSessionEJB;
	}

}
