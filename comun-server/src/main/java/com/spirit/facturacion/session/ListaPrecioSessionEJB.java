package com.spirit.facturacion.session;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.ListaPrecioEJB;
import com.spirit.facturacion.entity.ListaPrecioIf;
import com.spirit.facturacion.entity.PrecioEJB;
import com.spirit.facturacion.entity.PrecioIf;
import com.spirit.facturacion.session.generated._ListaPrecioSession;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * The <code>ListaPrecioSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:17 $
 *
 */
@Stateless
public class ListaPrecioSessionEJB extends _ListaPrecioSession implements ListaPrecioSessionRemote,ListaPrecioSessionLocal  {

	@PersistenceContext(unitName="spirit")
	   private EntityManager manager;

	@EJB 
	private PrecioSessionLocal precioLocal;
	
	@EJB
	private UtilitariosSessionLocal utilitariosLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(ListaPrecioSessionEJB.class);
   
   @Resource private SessionContext ctx;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void procesarListaPrecio(ListaPrecioIf model,List<PrecioIf> modelPrecioList) throws GenericBusinessException {
	   try {
			ListaPrecioEJB listaPrecio = registrarListaPrecio(model);
			manager.persist(listaPrecio);

			for (PrecioIf modelPrecio : modelPrecioList) {

				modelPrecio.setListaprecioId(listaPrecio.getPrimaryKey());
				PrecioEJB precio = registrarPrecio(modelPrecio);
				manager.merge(precio);
			}
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Error al insertar información en Lista Precio - Precio");
		}
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarListaPrecio(ListaPrecioIf model,List<PrecioIf> modelPrecioList,List<PrecioIf> modelPreciosRemovidosList) throws GenericBusinessException {
	   try {
			ListaPrecioEJB listaPrecio = registrarListaPrecio(model);
			manager.merge(listaPrecio);

			for (PrecioIf modelPreciosRemovidos : modelPreciosRemovidosList) {
				PrecioEJB data = manager.find(PrecioEJB.class, modelPreciosRemovidos.getId());
				manager.remove(data);
			}
			for (PrecioIf modelPrecio : modelPrecioList) {
				PrecioEJB precio = registrarPrecioForUpdate(modelPrecio);
				manager.merge(precio);
			}
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException("Error al actualizar información en Lista Precio - Precio");
		}
	}
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarListaPrecio(Long listaPrecioId) throws GenericBusinessException {
		try {
			ListaPrecioEJB data = manager.find(ListaPrecioEJB.class, listaPrecioId);

			Collection<PrecioIf> modelDetalleList = precioLocal.findPrecioByListaprecioId(listaPrecioId);

			for (PrecioIf modelDetalle : modelDetalleList) {

				manager.remove(modelDetalle);
			}

			manager.remove(data);
			manager.flush();

		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al eliminar información en ListaPrecioEJB.", e);
			throw new GenericBusinessException("Error al eliminar información en ListaPrecio");
		}
   }
   
   private ListaPrecioEJB registrarListaPrecio(ListaPrecioIf model) {
	   ListaPrecioEJB listaPrecio = new ListaPrecioEJB();
		
		listaPrecio.setCodigo(model.getCodigo());
		listaPrecio.setEmpresaId(model.getEmpresaId());
		listaPrecio.setEstado(model.getEstado());
		listaPrecio.setFechaCreacion(model.getFechaCreacion());
		listaPrecio.setFechaFinal(model.getFechaFinal());
		listaPrecio.setFechaInicio(model.getFechaInicio());
		listaPrecio.setId(model.getId());
		listaPrecio.setNombre(model.getNombre());
		listaPrecio.setReferenciaFisica(model.getReferenciaFisica());
		
		return listaPrecio;
	}
   
   private PrecioEJB registrarPrecio(PrecioIf modelPrecio) {
	    PrecioEJB precio = new PrecioEJB();
		
		precio.setCambiarPrecio(modelPrecio.getCambiarPrecio());
		precio.setEstado(modelPrecio.getEstado());
		precio.setId(modelPrecio.getId());
		precio.setListaprecioId(modelPrecio.getListaprecioId());
		precio.setPrecio(modelPrecio.getPrecio());
		precio.setProductoId(modelPrecio.getProductoId());
		
		return precio;
	}
   
   private PrecioEJB registrarPrecioForUpdate(PrecioIf modelPrecio) {
	    PrecioEJB precio = new PrecioEJB();
		
	    precio.setId(modelPrecio.getId());
		precio.setCambiarPrecio(modelPrecio.getCambiarPrecio());
		precio.setEstado(modelPrecio.getEstado());
		precio.setId(modelPrecio.getId());
		precio.setListaprecioId(modelPrecio.getListaprecioId());
		precio.setPrecio(modelPrecio.getPrecio());
		precio.setProductoId(modelPrecio.getProductoId());
		
		return precio;
	}
   
}
