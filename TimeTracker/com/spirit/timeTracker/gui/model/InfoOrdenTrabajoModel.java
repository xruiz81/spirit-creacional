package com.spirit.timeTracker.gui.model;

import com.spirit.client.SpiritAlert;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.ClienteOficinaSessionService;
import com.spirit.crm.session.ClienteSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.session.CampanaSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.timeTracker.gui.main.JPInfoOrdenTrabajo;
import com.spirit.util.Utilitarios;

public class InfoOrdenTrabajoModel extends JPInfoOrdenTrabajo {
	
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_EN_CURSO = "EN CURSO";
	private static final String NOMBRE_ESTADO_REALIZADO = "REALIZADO";
	private static final String NOMBRE_ESTADO_CANCELADO = "CANCELADO";
	private static final String NOMBRE_ESTADO_SUSPENDIDO = "SUSPENDIDO";
	private static final String ESTADO_PENDIENTE = NOMBRE_ESTADO_PENDIENTE.substring(0, 1);
	private static final String ESTADO_EN_CURSO = NOMBRE_ESTADO_EN_CURSO.substring(0, 1);
	private static final String ESTADO_REALIZADO = NOMBRE_ESTADO_REALIZADO.substring(0, 1);
	private static final String ESTADO_CANCELADO = NOMBRE_ESTADO_CANCELADO.substring(0, 1);
	private static final String ESTADO_SUSPENDIDO = NOMBRE_ESTADO_SUSPENDIDO.substring(0, 1);
	
	private ClienteIf clienteIf;
	private ClienteOficinaIf clienteOficinaIf;

	private static final long serialVersionUID = -4672206864900601037L;
	

	public InfoOrdenTrabajoModel() {
	}
	
	public InfoOrdenTrabajoModel(OrdenTrabajoIf ordenTrabajo ) throws GenericBusinessException {
		if (ordenTrabajo!=null){
			try {
				clienteOficinaIf = getClienteOficinaSessionService().getClienteOficina(ordenTrabajo.getClienteoficinaId());
				clienteIf = getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
				CampanaIf campanaIf = getCampanaSessionService().getCampana(ordenTrabajo.getCampanaId());
				
				getTxtCodigo().setText(ordenTrabajo.getCodigo());
				getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
				//getTxtOficina().setText(clienteOficinaIf.getCodigo() + " - " + clienteOficinaIf.getDescripcion());
				
				if (campanaIf != null)
					getTxtCampana().setText(campanaIf.toString());
				
				if (ordenTrabajo.getFechaentrega()!=null)
					getTxtFechaEntrega().setText(
						Utilitarios.getFechaUppercase(ordenTrabajo.getFechaentrega()) );
				
				if (ordenTrabajo.getFechalimite()!=null)
					getTxtFechaLimite().setText(
						Utilitarios.getFechaUppercase(ordenTrabajo.getFechalimite()) );
				
				getTxtDescripcion().setText(ordenTrabajo.getDescripcion());
				getTxtObservacion().setText(ordenTrabajo.getObservacion());
				
				String estado = ordenTrabajo.getEstado();
				String nombreEstado = "";
				if (ESTADO_PENDIENTE.equals(estado))
					nombreEstado = NOMBRE_ESTADO_PENDIENTE;
				else if (ESTADO_SUSPENDIDO.equals(estado))
					nombreEstado = NOMBRE_ESTADO_SUSPENDIDO;
				else if (ESTADO_CANCELADO.equals(estado))
					nombreEstado = NOMBRE_ESTADO_CANCELADO;
				else if (ESTADO_EN_CURSO.equals(estado))
					nombreEstado = NOMBRE_ESTADO_EN_CURSO;
				else if (ESTADO_REALIZADO.equals(estado))
					nombreEstado = NOMBRE_ESTADO_REALIZADO;
				getTxtEstado().setText(nombreEstado);

			} catch (GenericBusinessException e) {
				e.printStackTrace();
				throw new GenericBusinessException("Error al obtener datos de la Orden de Trabajo");
			}
		} else{
			SpiritAlert.createAlert("No existe la orden de trabajo seleccionada"
					, SpiritAlert.WARNING);
		}
		
	}
	 
}
