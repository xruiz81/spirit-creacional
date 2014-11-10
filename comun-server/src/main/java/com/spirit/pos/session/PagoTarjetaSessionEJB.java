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
import com.spirit.pos.entity.PagoTarjetaEJB;
import com.spirit.pos.entity.PagoTarjetaIf;
import com.spirit.pos.session.generated._PagoTarjetaSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>PagoTarjetaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 *
 */
@Stateless
public class PagoTarjetaSessionEJB extends _PagoTarjetaSession implements PagoTarjetaSessionRemote,PagoTarjetaSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(PagoTarjetaSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   public Long procesarPagoTarjetaCredito(PagoTarjetaIf pagotarjeta) throws GenericBusinessException {
		Long idPedido = 0L;
		
		try { 
		 
			PagoTarjetaEJB datosTarjeta = registrarPagoTarjeta(pagotarjeta);	 
			
			manager.persist(datosTarjeta); 
		 
			
			idPedido = datosTarjeta.getPrimaryKey();
		} catch (Exception e) {
			 
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al guardar el Pedido");
		}	
		
		return idPedido;
	}
   
   public PagoTarjetaEJB registrarPagoTarjeta(PagoTarjetaIf model) {
	   PagoTarjetaEJB datos = new PagoTarjetaEJB();		
	   datos.setId(model.getId());
	   datos.setNombreCliente(model.getNombreCliente());
	   datos.setTelefono(model.getTelefono());
	   datos.setNoReferencia(model.getNoReferencia());
	   datos.setNoVoucher(model.getNoVoucher());
	   datos.setNoAutorizacion(model.getNoAutorizacion());
	   datos.setFecha(model.getFecha());
	   datos.setTipoTarjeta(model.getTipoTarjeta());
	   datos.setIdentificacion(model.getIdentificacion());
	   datos.setValor(model.getValor());
	   
	   
	   /*ventas.setCajasesionId(model.getCajasesionId());
	   ventas.setValorTotal(model.getValorTotal());
	   ventas.setFechaVenta(model.getFechaVenta());*/
		return datos;
	}
 
/////////////////
}
