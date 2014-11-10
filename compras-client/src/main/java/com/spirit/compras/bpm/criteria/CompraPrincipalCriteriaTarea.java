package com.spirit.compras.bpm.criteria;

import org.jbpm.taskmgmt.exe.TaskInstance;

import com.spirit.client.CriteriaTarea;
import com.spirit.client.model.SpiritModel;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.compras.gui.model.CompraModel;
import com.spirit.compras.gui.model.OrdenCompraModel;
import com.spirit.compras.gui.model.SolicitudCompraModel;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;

public class CompraPrincipalCriteriaTarea extends CriteriaTarea {

	private static final long serialVersionUID = 1L;
	String nombreCriteria = "PrincipalCompras";
	String nombrePanel = "";
	SpiritModel panel = null;
	String idPrimario = "";
	boolean guardado = false;
	long idTarea = 0L;

	public CompraPrincipalCriteriaTarea(String nombrePanel,String idPrimario,long idTarea){
		this.nombrePanel = nombrePanel;
		this.idPrimario = idPrimario;
		this.idTarea = idTarea;
	}
	
	public CompraPrincipalCriteriaTarea(TaskInstance tarea){
		this.nombrePanel = (String) tarea.getContextInstance().getVariable("nombrePanelSiguiente");
		this.idPrimario = (String) tarea.getContextInstance().getVariable("documentoId");
		this.guardado = Boolean.parseBoolean(
				(String) tarea.getContextInstance().getVariable("guardado"));
		this.idTarea = tarea.getId();
	}
	
	public String getNombrePanel() {
		return nombrePanel;
	}

	public SpiritModel getPanel() {
		return panel;
	}
	
	public String getNombreCriteria() {
		return this.nombreCriteria;
	}

	public void realizarAccion() throws GenericBusinessException {
		if ( nombrePanel.equalsIgnoreCase("Solicitud de Compra") ){
			presentarSolicitudCompra(idPrimario);
		} else if ( nombrePanel.equalsIgnoreCase("Orden de Compra") ){
			presentarOrdenCompra(idPrimario);
		} else if ( nombrePanel.equalsIgnoreCase("Compra") ){
			presentarCompra(idPrimario);
		} 
		else{
			throw new GenericBusinessException("Nombre de panel no existe en criteria " +
					"de Proceso PrincipalCompra");
		}
	}
	
	private void presentarSolicitudCompra(String idSolicitudCompra) 
	throws GenericBusinessException{
		panel = null;
		SolicitudCompraIf  solicitudCompra = SessionServiceLocator.getSolicitudCompraSessionService()
			.getSolicitudCompra(Long.valueOf(idSolicitudCompra));
		//Se verifica si existe 
		if ( solicitudCompra != null ){
			panel = (SpiritModel)new SolicitudCompraModel(solicitudCompra,idTarea);
		}
		else{
			throw new GenericBusinessException("No existe esa solicitud de Compra" +
					"\no ha sido eliminada");
		}
	}
	
	private void presentarOrdenCompra(String idPrimarioActual) 
	throws GenericBusinessException{
		//Si la orden de compra no ha sido guardada, 
		//idPrimarioActual sera el id de Solicitud de Compra
		//Si no sera el id de la Orden de Compra
		panel = null;
			if (guardado){
				OrdenCompraIf ordenCompraIf = SessionServiceLocator
					.getOrdenCompraSessionService()
					.getOrdenCompra(Long.valueOf(idPrimarioActual));
				if ( ordenCompraIf != null )
					panel = (SpiritModel) new OrdenCompraModel(ordenCompraIf,idTarea);
				else
					throw new GenericBusinessException("No existe la Orden " +
							"de Compra seleccionada" +
							"\no ha sido eliminada");
			} else {
				SolicitudCompraIf solicitudCompra = SessionServiceLocator.getSolicitudCompraSessionService()
					.getSolicitudCompra(Long.valueOf(idPrimarioActual));
				if ( solicitudCompra != null ){
					//Collection detalleSolicitudCompra = SolicitudCompraModel
					//	.getSolicitudCompraDetalleSessionService()
					//	.findSolicitudCompraDetalleBySolicitudcompraId(solicitudCompra.getId());
					
					panel = (SpiritModel)new OrdenCompraModel(solicitudCompra
						,idTarea);
				} else{
					throw new GenericBusinessException("No existe la solicitud " +
							"de Compra para crear Orden de Compra");
				}
			}

		/*Collection listaSolicitudes = SolicitudCompraModel
			.getSolicitudCompraSessionService()
			.findSolicitudCompraById(Long.valueOf(idSolicitudCompra));
		if ( listaSolicitudes.size() == 1 ){
			SolicitudCompraIf solicitudCompraIf = (SolicitudCompraIf) listaSolicitudes
				.iterator().next();
			Collection detalleSolicitudCompra = SolicitudCompraModel
				.getSolicitudCompraDetalleSessionService()
				.findSolicitudCompraDetalleBySolicitudcompraId(solicitudCompraIf.getId());
			
			panel = (SpiritModel)new OrdenCompraModel(solicitudCompraIf
					,detalleSolicitudCompra,idTarea);
		}
		else{
			if ( listaSolicitudes.size() == 0 )
				throw new GenericBusinessException("No existe la solicitud de"+ 
					s"Compra para crear Orden de Compra");
		}*/
	}

	private void presentarCompra(String idPrimarioActual) 
	throws GenericBusinessException{
		panel = null;
		if (guardado){
			CompraIf compraIf = SessionServiceLocator.getCompraSessionService()
				.getCompra(Long.valueOf(idPrimarioActual));
			if (compraIf != null)
				panel = (SpiritModel)new CompraModel(compraIf,idTarea);
			else
				throw new GenericBusinessException("No existe la Compra seleccionada" +
						"\no ha sido eliminada");
		} else {
			OrdenCompraIf ordenCompraIf = SessionServiceLocator
				.getOrdenCompraSessionService().getOrdenCompra(Long.valueOf(idPrimarioActual));
			if (ordenCompraIf!=null){
				//Collection detalleOrdenCompra = OrdenCompraModel
				//	.getOrdenCompraDetalleSessionService()
				//	.findOrdenCompraDetalleByOrdencompraId(ordenCompraIf.getId());
				panel = (SpiritModel) new CompraModel(ordenCompraIf,idTarea);
			} else
				throw new GenericBusinessException("No existe la Orden de " +
						"Compra para crear Compra");
			
		}
	}
	
}
