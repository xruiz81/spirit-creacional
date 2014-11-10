package com.spirit.pos.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.pos.entity.VentasTrackEJB;
import com.spirit.pos.entity.VentasTrackIf;
import com.spirit.pos.session.generated._VentasTrackSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
 
/**
 * The <code>VentasTrackSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 *
 */
@Stateless
public class VentasTrackSessionEJB extends _VentasTrackSession implements VentasTrackSessionRemote,VentasTrackSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;
   

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(VentasTrackSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
   public Long procesarVentasTrack(VentasTrackIf venta) throws GenericBusinessException {
		Long idVentasTrack = 0L;
		try { 		 
			VentasTrackEJB ventaPrincipal = registrarVentasTrack(venta);	 
			manager.persist(ventaPrincipal); 
			idVentasTrack = ventaPrincipal.getPrimaryKey();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar el Pedido");
		}	
		
		return idVentasTrack;
	}
  
  
  public VentasTrackEJB registrarVentasTrack(VentasTrackIf model) {
	   VentasTrackEJB ventas = new VentasTrackEJB();		
	   ventas.setId(model.getId());
	   ventas.setCajasesionId(model.getCajasesionId());
	   ventas.setValorTotal(model.getValorTotal());
	   ventas.setFechaVenta(model.getFechaVenta());
		return ventas;
	}
 
}
