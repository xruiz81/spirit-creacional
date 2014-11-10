package com.spirit.inventario.session;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.LoteEJB;
import com.spirit.inventario.entity.ProductoEJB;
import com.spirit.inventario.session.generated._LoteSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>LoteSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 *
 */
@Stateless
public class LoteSessionEJB extends _LoteSession implements LoteSessionRemote, LoteSessionLocal {

	@PersistenceContext(unitName="spirit")
	private EntityManager manager;
	
	@EJB
	private UtilitariosSessionLocal utilitariosLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(LoteSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
   	//select * from lote om where om.producto_ID = '98304' and om.fecha_vencimiento >= TO_Date( '7/10/2006', 'MM/DD/YYYY') order by om.fecha_vencimiento
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findLoteByProductoIdAndEstadoAndFecha(java.lang.Long idProducto, Date fecha) {
	   String objectName = "m";
	   String queryString = "select m from LoteEJB " + objectName + " where  m.productoId = " + idProducto + " and m.estado = 'A' and (m.fechaVencimiento > :fecha OR m.fechaVencimiento is null) order by m.fechaVencimiento asc";
	   Query query = manager.createQuery(queryString);
	   query.setParameter("fecha",fecha);
	   return query.getResultList();
   }
   
   public java.util.Collection findLoteByProductoIdAndBodegaIdAndEstadoAndFecha(java.lang.Long idProducto, Long idBodega,Date fecha) {
	   String queryString = "select m from LoteEJB m where  m.productoId = " + idProducto + " and m.estado = 'A' and m.bodegaId="+idBodega+" and (m.fechaVencimiento > :fecha OR m.fechaVencimiento is null) order by m.fechaVencimiento asc";
	   Query query = manager.createQuery(queryString);
	   query.setParameter("fecha",fecha);
	   return query.getResultList();
   }
   
   //select distinct l.* from stock s, lote l, producto p where s.LOTE_ID = l.ID and l.PRODUCTO_ID = p.ID and s.CANTIDAD > 0 and p.id = 557058
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findLoteByProductoIdAndByStockCantidadMinimaAndByEstadoAndByFecha(java.lang.Long idProducto, int cantidadMinimaStock, Date fecha) {
	   String objectName = "l";
	   String queryString = "select l from LoteEJB " + objectName + ", ProductoEJB p, StockEJB s where s.loteId = l.id and l.productoId = p.id and p.id = " + idProducto + " and s.cantidad >= " + cantidadMinimaStock + " and l.estado = 'A' and l.fechaElaboracion <= :fecha order by l.fechaElaboracion asc";
	   Query query = manager.createQuery(queryString);
	   query.setParameter("fecha",fecha);
	   return query.getResultList();
   }
   /*
   public PedidoDetalleIf verificarStockPorLote(PedidoDetalleIf pedidoDetalle, Long idBodega) throws GenericBusinessException {
	
	   ProductoIf producto = productoLocal.getProducto(pedidoDetalle.getProductoId());
	   GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
	   String tipoProducto = tipoProductoLocal.getTipoProducto(generico.getTipoproductoId()).getCodigo();
	   String CODIGO_TIPO_PRODUCTO_CONSUMO = "CON";

	   DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
	   java.util.Date fechaHoy = new java.util.Date();
	   String fecha = dateFormatter.format(fechaHoy);
	   int restante = pedidoDetalle.getCantidadpedida().intValue();
	   Long idProducto = producto.getId();

	   if (CODIGO_TIPO_PRODUCTO_CONSUMO.equals(tipoProducto)) {
		   LoteIf loteIf = null;
		   int cantidadStock = 0;
		   Collection lotes;

		   if (pedidoDetalle.getLoteId() == null)
			   lotes = loteLocal.findLoteByProductoIdAndEstadoAndFecha(idProducto, fecha);
		   else
			   lotes = loteLocal.findLoteById(pedidoDetalle.getLoteId());

		   Iterator itLotes = lotes.iterator();

		   while (itLotes.hasNext()) {
			   
			   loteIf = (LoteIf) itLotes.next();

			   Collection stocks = stockLocal.findStockByIdLoteAndIdBodega(loteIf.getId(),idBodega);
			   Iterator itStocks = stocks.iterator();

			   while (itStocks.hasNext()) {

				   StockIf stockIf = (StockIf) itStocks.next();

				   // valido si el stock de cual estoy leyendo los datos tiene cantidades mayores a cero
				   if (stockIf.getCantidad().intValue() > 0) {
					   // a cantidad stock le agrego la cantidad que posee el stock
					   cantidadStock = stockIf.getCantidad().intValue();
					   // disminuyo la cantidad restante que aun falta por facturar
					   restante = restante - cantidadStock;

					   // esto se da si el stock satisface la cantidad pedida
					   if (restante <= 0)
						   break;
				   }

				   // si el restante es menor a 0 significa que lo que tenía en el stock era suficiente
				   // para poder facturar por lo tanto salgo del while de los lotes
				   if (restante <= 0)
					   break;
			   }

			   if (restante <= 0)
				   break;
		   }

	   } else 
		   restante = 0;
	   
	   
	   Double cantidadFacturada = pedidoDetalle.getCantidadpedida().doubleValue() - restante;
	   pedidoDetalle.setCantidad(BigDecimal.valueOf(cantidadFacturada));
	   
	   return pedidoDetalle;
   }   
   */
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findLoteByEmpresaId(Long idEmpresa) {
 	  //select * from lote l, producto p, generico g where l.PRODUCTO_ID = p.ID and p.GENERICO_ID = g.ID and g.EMPRESA_ID = 1300
      String objectName = "l";
 	  String queryString = "select l from LoteEJB " + objectName + ", ProductoEJB p, GenericoEJB g where l.productoId = p.id and p.genericoId = g.id and g.empresaId = " + idEmpresa;
 	  Query query = manager.createQuery(queryString);
 	  return query.getResultList();
    }
   
}
