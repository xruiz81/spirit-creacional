package com.spirit.timeTracker.gui.model;

import java.util.Collection;
import java.util.Iterator;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.SubtipoOrdenIf;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.session.SubtipoOrdenSessionService;
import com.spirit.general.session.TipoOrdenSessionService;
import com.spirit.medios.entity.EquipoEmpleadoIf;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.session.EquipoEmpleadoSessionService;
import com.spirit.medios.session.EquipoTrabajoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.timeTracker.gui.main.JPInfoTareaOrdenTrabajo;
import com.spirit.util.Utilitarios;

public class InfoTareaOrdenTrabajoModel extends JPInfoTareaOrdenTrabajo {
	
	private ClienteIf clienteIf;
	private ClienteOficinaIf clienteOficinaIf;
	
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

	private static final long serialVersionUID = -4672206864900601037L;
	

	public InfoTareaOrdenTrabajoModel() {
	}
	
	public InfoTareaOrdenTrabajoModel(OrdenTrabajoDetalleIf tareaOrdenTrabajo ) throws GenericBusinessException {
		try {

			SubtipoOrdenIf subTipoOrden = getSubtipoOrdenSessionService().getSubtipoOrden(tareaOrdenTrabajo.getSubtipoId());
			TipoOrdenIf tipoOrden =  getTipoOrdenSessionService().getTipoOrden(subTipoOrden.getTipoordenId());
			Collection equipo = getEquipoTrabajoSessionService().findEquipoTrabajoByEmpresaIdAndTipoOrdenId(
					Parametros.getIdEmpresa(),subTipoOrden.getTipoordenId());

			EmpleadoIf empleado = PanelListaProyectosModel.getEmpleadoSessionService()
			.getEmpleado(tareaOrdenTrabajo.getAsignadoaId());
			if ( empleado != null){
				getTxtAsignado().setText(empleado.getCodigo()+" - "
						+empleado.getNombres()+" "+empleado.getApellidos());
			}

			Iterator itequipo = equipo.iterator();
			if (itequipo.hasNext()) {
				EquipoTrabajoIf equipoIf = (EquipoTrabajoIf) itequipo.next();
				getTxtEquipo().setText(equipoIf.toString());
			}

			if ( tipoOrden != null )
				getTxtTipo().setText(tipoOrden.toString());
			if ( subTipoOrden != null )
				getTxtSubtipo().setText(subTipoOrden.toString());

			if (tareaOrdenTrabajo.getFechaentrega()!=null)
				getTxtFechaEntrega().setText(
						Utilitarios.getFechaUppercase(tareaOrdenTrabajo.getFechaentrega()) );

			if (tareaOrdenTrabajo.getFechalimite()!=null)
				getTxtFechaLimite().setText(
						Utilitarios.getFechaUppercase(tareaOrdenTrabajo.getFechalimite()) );

			String estado = tareaOrdenTrabajo.getEstado();
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

			getTxtDescripcion().setText(tareaOrdenTrabajo.getDescripcion());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al obtener datos de la Orden de Trabajo");
		}
	}
	 
}
