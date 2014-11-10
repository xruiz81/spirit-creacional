package com.spirit.facturacion.session;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.bpm.campana.ProcesoOrdenTrabajoCreacionalSessionLocal;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.PedidoDetalleEJB;
import com.spirit.facturacion.entity.PedidoDetalleIf;
import com.spirit.facturacion.entity.PedidoEJB;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.session.generated._PedidoSession;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.server.QueryBuilder;

/**
 * The <code>PedidoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:17 $
 *
 */
@Stateless
public class PedidoSessionEJB extends _PedidoSession implements PedidoSessionRemote,PedidoSessionLocal  {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	private DecimalFormat formatoSerial = new DecimalFormat("00000");
	@EJB private PedidoDetalleSessionLocal pedidoDetalleSessionLocal;
	@EJB private ProcesoOrdenTrabajoCreacionalSessionLocal procesoOrdenTrabajoLocal;
	@EJB private UtilitariosSessionLocal utilitariosLocal;
	
	@Resource private SessionContext ctx;
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection getPedidoList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e from PedidoEJB " + objectName + ", TipoDocumentoEJB td, EmpresaEJB em where " + where;
		queryString += " and e.tipodocumentoId = td.id and td.empresaId = em.id and em.id = " + idEmpresa + " order by e.codigo desc";
		Query query = manager.createQuery(queryString);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
			
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getPedidoListSize(Map aMap, java.lang.Long idEmpresa) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from PedidoEJB " + objectName + ", TipoDocumentoEJB td where " + where;
		queryString += " and e.tipodocumentoId = td.id and td.empresaId = " + idEmpresa;
		Query query = manager.createQuery(queryString);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long procesarPedido(PedidoIf model, List<PedidoDetalleIf> modelDetalleList, Long idEmpresa,Tarea tarea) throws GenericBusinessException {
		Long idPedido = 0L;
		
		try {
			if(model.getCodigo().length()<10)
			{
				String codigo = getMaximoCodigoPedido(model.getCodigo(), idEmpresa);
				int codigoPedido = 1;
				if (!codigo.equals("[null]")) {
					codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
					codigoPedido = Integer.parseInt(codigo.split(model.getCodigo())[1]) + 1;
				}
				model.setCodigo(model.getCodigo() + formatoSerial.format(codigoPedido));
			}
			
			PedidoEJB pedido = registrarPedido(model);
			
			manager.persist(pedido);
			
			for (PedidoDetalleIf modelDetalle : modelDetalleList) {		
							
				modelDetalle.setPedidoId(pedido.getPrimaryKey());				
				PedidoDetalleEJB pedidoDetalle = registrarPedidoDetalle(modelDetalle);
				
				System.out.println("ACA EN PEDIDO DETALLE>"+pedidoDetalle.getIva());
				System.out.println("ACA EN PEDIDO DETALLE>"+pedidoDetalle.getIva());
				manager.merge(pedidoDetalle);
				
				//Sirve para establecer el id en el detalle y poder usarlo en las compras por internet. CFOB.
				modelDetalle.setId(pedidoDetalle.getPrimaryKey());
			}
			
			procesoOrdenTrabajoLocal.procesarPedido(pedido, false, tarea);
			
			idPedido = pedido.getPrimaryKey();
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar el Pedido");
		}	
		
		return idPedido;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PedidoIf actualizarPedido(PedidoIf model, List<PedidoDetalleIf> pedidoDetalleList, 
			PedidoIf pedidoAnterior, List<PedidoDetalleIf> pedidoDetalleEliminadosList,Tarea tarea) throws GenericBusinessException {
		PedidoEJB pedido;
		
		try {
			pedido = registrarPedido(model);
			pedido.setId(pedidoAnterior.getId());
			manager.merge(pedido);
			
			for (PedidoDetalleIf modelDetalleEliminado: pedidoDetalleEliminadosList)
				pedidoDetalleSessionLocal.deletePedidoDetalle(modelDetalleEliminado.getId());
			
			for (PedidoDetalleIf modelDetalle : pedidoDetalleList) {
				modelDetalle.setPedidoId(pedido.getPrimaryKey());
				PedidoDetalleEJB pedidoDetalle = registrarPedidoDetalle(modelDetalle);
				manager.merge(pedidoDetalle);
			}
			
			procesoOrdenTrabajoLocal.procesarPedido(pedido, false, tarea);
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al actualizar el pedido");
		}
		
		return pedido;
	}
	
	private String getMaximoCodigoPedido(String codigoParcialPedido, Long idEmpresa) {
		String queryString = "select max(p.codigo) from PedidoEJB p, TipoDocumentoEJB td where p.tipodocumentoId = td.id and td.empresaId = " + idEmpresa + " and p.codigo like '" + codigoParcialPedido + "%'";
		Query query = manager.createQuery(queryString);
		return query.getResultList().toString();
	}
	
	public PedidoEJB registrarPedido(PedidoIf model) {
		PedidoEJB pedido = new PedidoEJB();
		
		pedido.setId(model.getId());
		pedido.setOficinaId(model.getOficinaId());
		pedido.setTipodocumentoId(model.getTipodocumentoId());
		pedido.setCodigo(model.getCodigo());
		pedido.setClienteoficinaId(model.getClienteoficinaId());
		pedido.setTipoidentificacionId(model.getTipoidentificacionId());
		pedido.setIdentificacion(model.getIdentificacion());
		pedido.setFormapagoId(model.getFormapagoId());
		pedido.setMonedaId(model.getMonedaId());
		pedido.setPuntoimpresionId(model.getPuntoimpresionId());
		pedido.setOrigendocumentoId(model.getOrigendocumentoId());
		pedido.setVendedorId(model.getVendedorId());
		pedido.setBodegaId(model.getBodegaId());
		pedido.setListaprecioId(model.getListaprecioId());
		pedido.setFechaPedido(model.getFechaPedido());
		pedido.setFechaVencimiento(model.getFechaVencimiento());
		pedido.setFechaCreacion(model.getFechaCreacion());
		pedido.setUsuarioId(model.getUsuarioId());
		pedido.setContacto(model.getContacto());
		pedido.setDireccion(model.getDireccion());
		pedido.setTelefono(model.getTelefono());
		pedido.setTiporeferencia(model.getTiporeferencia());
		pedido.setReferencia(model.getReferencia());
		pedido.setDiasvalidez(model.getDiasvalidez());
		pedido.setObservacion(model.getObservacion());
		pedido.setEstado(model.getEstado());
		pedido.setPedidoaplId(model.getPedidoaplId());
		pedido.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
		pedido.setTipopagoId(model.getTipopagoId());
		pedido.setEquipoId(model.getEquipoId());
		pedido.setClienteNegociacionId(model.getClienteNegociacionId());
		pedido.setTipoNegociacion(model.getTipoNegociacion());
		pedido.setPorcentajeNegociacionDirecta(model.getPorcentajeNegociacionDirecta());
		pedido.setPorcentajeDescuentoNegociacion(model.getPorcentajeDescuentoNegociacion());
		pedido.setAutorizacionSap(model.getAutorizacionSap());
		
		return pedido;
	}
	
	public PedidoDetalleEJB registrarPedidoDetalle(PedidoDetalleIf modelDetalle) {
		PedidoDetalleEJB pedidoDetalle = new PedidoDetalleEJB();
		pedidoDetalle.setId(modelDetalle.getId());
		pedidoDetalle.setDocumentoId(modelDetalle.getDocumentoId());
		pedidoDetalle.setPedidoId(modelDetalle.getPedidoId());
		pedidoDetalle.setProductoId(modelDetalle.getProductoId());
		pedidoDetalle.setLoteId(modelDetalle.getLoteId());
		pedidoDetalle.setDescripcion(modelDetalle.getDescripcion());
		pedidoDetalle.setMotivodocumentoId(modelDetalle.getMotivodocumentoId());
		pedidoDetalle.setCantidadpedida(modelDetalle.getCantidadpedida());
		pedidoDetalle.setCantidad(modelDetalle.getCantidad());
		pedidoDetalle.setPrecio(modelDetalle.getPrecio());
		pedidoDetalle.setPrecioreal(modelDetalle.getPrecioreal());
		pedidoDetalle.setDescuento(modelDetalle.getDescuento());
		pedidoDetalle.setValor(modelDetalle.getValor());
		pedidoDetalle.setIva(modelDetalle.getIva());
		pedidoDetalle.setIce(modelDetalle.getIce());
		pedidoDetalle.setOtroimpuesto(modelDetalle.getOtroimpuesto());
		pedidoDetalle.setDescuentoGlobal(modelDetalle.getDescuentoGlobal());
		pedidoDetalle.setComprasReembolsoAsociadas(modelDetalle.getComprasReembolsoAsociadas());
		pedidoDetalle.setCodigoPersonalizacion(modelDetalle.getCodigoPersonalizacion());
		pedidoDetalle.setPorcentajeDescuentosVarios(modelDetalle.getPorcentajeDescuentosVarios());
		
		return pedidoDetalle;
	}
	
}
