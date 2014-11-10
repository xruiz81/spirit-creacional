package com.spirit.timeTracker.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;

import javax.swing.JOptionPane;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.medios.handler.EstadoOrdenTrabajo;
import com.spirit.timeTracker.componentes.SubTareaListener;
import com.spirit.timeTracker.gui.main.JPInfoTareaOrdenTrabajo;
import com.spirit.timeTracker.gui.model.cache.MapaCache;
import com.spirit.util.Archivos;
import com.spirit.util.Utilitarios;

public class InfoTareaOrdenTrabajoModel extends JPInfoTareaOrdenTrabajo {
	
	private static final long serialVersionUID = -4672206864900601037L;
	private OrdenTrabajoDetalleIf tareaOrdenTrabajo;
	private static final String ESTADO_ENTREGADO = "T";
	
	public InfoTareaOrdenTrabajoModel() {
		initKeyListeners();
	}
	
	public void initKeyListeners(){
		getBtnArchivoDescripcion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnArchivoDescripcion().setToolTipText("Ver Archivo");
		getBtnPropuesta().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnPropuesta().setToolTipText("Ver Archivo");
		getBtnOrdenEntregada().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/tareaRealizada.png"));
	}
	
	public void initListeners(){
		final String si = "Si"; 
		final String no = "No"; 
		final Object[] options ={si,no}; 
		getBtnOrdenEntregada().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(tareaOrdenTrabajo != null){
					int opcion = JOptionPane.showOptionDialog(null, "¿El trabajo esta ENTREGADO?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if (opcion == JOptionPane.YES_OPTION) {
						tareaOrdenTrabajo.setEstado(ESTADO_ENTREGADO);
						try {
							SessionServiceLocator.getOrdenTrabajoDetalleSessionService().saveOrdenTrabajoDetalle(tareaOrdenTrabajo);
							SpiritAlert.createAlert("Trabajo actualizado con éxito", SpiritAlert.INFORMATION);
							SubTareaListener.actualizarTablaProyectos();
						} catch (GenericBusinessException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		getBtnArchivoDescripcion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int opcion = JOptionPane.showOptionDialog(null, "Desea visualizar el archivo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
				if (opcion == JOptionPane.YES_OPTION) {
					try {
						String urlArchivoDescripcion = getTxtArchivoDescripcion().getText();
						Archivos.abrirArchivoDesdeServidor(urlArchivoDescripcion);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		getBtnPropuesta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int opcion = JOptionPane.showOptionDialog(null, "Desea visualizar el archivo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
				if (opcion == JOptionPane.YES_OPTION) {
					try {
						String urlArchivo = getTxtPropuesta().getText();
						Archivos.abrirArchivoDesdeServidor(urlArchivo);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	public InfoTareaOrdenTrabajoModel(OrdenTrabajoDetalleIf tareaOrdenTrabajo) throws GenericBusinessException {
		this.tareaOrdenTrabajo = tareaOrdenTrabajo;
		initKeyListeners();
		initListeners();
		try {
			//SubtipoOrdenIf subTipoOrden = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(tareaOrdenTrabajo.getSubtipoId());
			SubtipoOrdenIf subTipoOrden = MapaCache.verificarSubTipoOrdenEnMapa(MapaCache.getMapaSubTipoOrden(), tareaOrdenTrabajo.getSubtipoId());
			//TipoOrdenIf tipoOrden =  SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(subTipoOrden.getTipoordenId());
			TipoOrdenIf tipoOrden =  MapaCache.verificarTipoOrdenEnMapa(MapaCache.getMapaTipoOrden(), subTipoOrden.getTipoordenId());
			Collection<EquipoTrabajoIf> equipo = SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByEmpresaIdAndTipoOrdenId(Parametros.getIdEmpresa(),subTipoOrden.getTipoordenId());
			for (EquipoTrabajoIf equipoIf : equipo) {
				getTxtEquipo().setText(equipoIf.toString());
			}
			
			EmpleadoIf empleado = MapaCache.verificarEmpleadoEnMapa(MapaCache.getMapaEmpleados(), tareaOrdenTrabajo.getAsignadoaId());
			if ( empleado != null){
				getTxtAsignado().setText(empleado.getCodigo() + " - "	+ empleado.getNombres() + " " + empleado.getApellidos());
			}

			if ( tipoOrden != null )
				getTxtTipo().setText(tipoOrden.toString());
			if ( subTipoOrden != null )
				getTxtSubtipo().setText(subTipoOrden.toString());

			if (tareaOrdenTrabajo.getFechaentrega()!=null)
				getTxtFechaEntrega().setText(Utilitarios.getFechaUppercase(tareaOrdenTrabajo.getFechaentrega()) );

			if (tareaOrdenTrabajo.getFechalimite()!=null)
				getTxtFechaLimite().setText(Utilitarios.getFechaUppercase(tareaOrdenTrabajo.getFechalimite()) );

			String estadoLetra = tareaOrdenTrabajo.getEstado();
			EstadoOrdenTrabajo estado = EstadoOrdenTrabajo.getEstadoOrdenTrabajoByLetra(estadoLetra);
			getTxtEstado().setText(estado.toString());
			
			if(tareaOrdenTrabajo.getUrlDescripcion() != null) {
				getTxtArchivoDescripcion().setText(tareaOrdenTrabajo.getUrlDescripcion());
				getBtnArchivoDescripcion().setEnabled(true);
			} else{
				getBtnArchivoDescripcion().setEnabled(false);				
			}
			
			if(tareaOrdenTrabajo.getUrlPropuesta() != null) {
				getTxtPropuesta().setText(tareaOrdenTrabajo.getUrlPropuesta());
				getBtnPropuesta().setEnabled(true);
			} else{
				getBtnPropuesta().setEnabled(false);				
			}

			getTxtDescripcion().setText(tareaOrdenTrabajo.getDescripcion());
			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al obtener datos de la Orden de Trabajo");
		}
	}	
}
