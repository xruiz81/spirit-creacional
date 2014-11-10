package com.spirit.pos.session;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.pos.entity.VentasPagosEJB;
import com.spirit.pos.entity.VentasPagosIf;
import com.spirit.pos.session.generated._VentasPagosSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>VentasPagosSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:19 $
 *
 */
@Stateless
public class VentasPagosSessionEJB extends _VentasPagosSession implements VentasPagosSessionRemote, VentasPagosSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;
   
   @EJB private UtilitariosSessionLocal utilitariosLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(VentasPagosSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
  //johanna
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findDonacionesDetalle(java.sql.Timestamp fechaInicial, java.sql.Timestamp fechaFinal,
		   Long fundacionid,Long color,Long modelo,Long talla,Long tipoproducto,
		   Long clienteoficinaid) throws GenericBusinessException{
	   try{
		   	String queryString = "FROM DonaciondetalleEJB d " + "where d.id is not null ";		   	
		   if (fundacionid != null)	queryString += " and (d.fundacionid = :fundacionid or d.fundaciondevolucionid= :fundacionid)";		   
		   if (color != null)			queryString += " and d.colorId = :color ";
		   if (modelo != null)			queryString += " and d.modeloId = :modelo ";
		   if (talla != null)			queryString += " and d.tallaId = :talla";
		   if (tipoproducto != null)	queryString += " and d.tipoproducto = :tipoproducto ";
		   if (clienteoficinaid != null)	queryString += " and d.clienteoficinaid = :clienteoficinaid ";
		   
		   if (fechaInicial != null) {
			   fechaInicial = utilitariosLocal.resetTimestampStartDate(fechaInicial);
			   queryString += " and d.fecha >= :fechaInicial";
		   }
		   if (fechaFinal != null)	{
			   fechaFinal = utilitariosLocal.resetTimestampEndDate(fechaFinal);
			   queryString += " and d.fecha <= :fechaFinal";
		   }
		  
		   queryString += " order by d.fecha,d.preimpreso asc";	
		   
		   System.out.println("QWERUR>>>"+queryString);
		   Query query = manager.createQuery(queryString);
		    
		   if (fundacionid != null)	query.setParameter("fundacionid", fundacionid);
		   if (clienteoficinaid != null)	query.setParameter("clienteoficinaid", clienteoficinaid);		   
			if (fechaInicial != null)		query.setParameter("fechaInicial", fechaInicial);
			if (fechaFinal != null)			query.setParameter("fechaFinal", fechaFinal);
			if (color != null)				query.setParameter("color", color);
			if (modelo != null)				query.setParameter("modelo", modelo);
			if (talla != null)				query.setParameter("talla", talla);
			if (tipoproducto != null)		query.setParameter("tipoproducto", tipoproducto);
			if (fechaInicial != null)		query.setParameter("fechaInicial", fechaInicial);
			if (fechaFinal != null)			query.setParameter("fechaFinal", fechaFinal);			
		    return query.getResultList();		   
	   } catch(Exception e){
		   e.printStackTrace();
		   throw new GenericBusinessException("Error al consultar donaciones");
	   }
   }
   
     

   //johanna
     @TransactionAttribute(TransactionAttributeType.REQUIRED)
     public java.util.Collection findVentasPagosByQueryVariosId(Map aMap) {
  	   
  	   
  	   Vector<String> ventastrackId= (Vector)aMap.get("ventastrackId");
  	   aMap.remove("ventastrackId");
  	   
  	String objectName = "e";
  	String where = QueryBuilder.buildWhere(aMap, objectName);
  		
  	String queryString = "from VentasPagosEJB " + objectName + " where " + where;
  	
  	if(where==null) where="";
  	if(where.equals(" ")) queryString = "from VentasPagosEJB " + objectName + " where ";
  	
  	if ( ventastrackId!=null && ventastrackId.size() > 0 && !where.equals(" ")){
  		queryString += "and  (";
  		for ( String estado : ventastrackId ){
  			queryString += (" e.ventastrackId = '"+estado+"' or");
  		}
  		queryString = queryString.substring(0,queryString.length()-3);
  		queryString += " )";
  	}
  	
  	if ( ventastrackId!=null && ventastrackId.size() > 0 && where.equals(" ")){
  		queryString += " ";
  		for ( String estado : ventastrackId ){
  			queryString += (" e.ventastrackId = '"+estado+"' or");
  		}
  		queryString = queryString.substring(0,queryString.length()-3);
  		queryString += " )";
  	}
  	
  	Query query = manager.createQuery(queryString);

  		Set keys = aMap.keySet();
  		Iterator it = keys.iterator();
  		while (it.hasNext()) {
  			String propertyKey = (String) it.next();
  			Object property = aMap.get(propertyKey);
  			query.setParameter(propertyKey, property);
  		}
  		return query.getResultList();       
     }
     
     
     public void procesarVentasPagosVarios(List<VentasPagosIf> pagosDetalle) throws GenericBusinessException {
  		try { 
  		 		for (VentasPagosIf modelDetalle : pagosDetalle) {
  					VentasPagosEJB pagosVarios = registrarVentasPagos(modelDetalle);
  					manager.merge(pagosVarios);
  				}
  		} catch (Exception e) {  			 
  			e.printStackTrace();
  			throw new GenericBusinessException("Se ha producido un error al guardar el Formas de Pago en la tabla VENTAS_PAGOS");
  		} 
  	}
    
    public VentasPagosEJB registrarVentasPagos(VentasPagosIf model) {
  	   VentasPagosEJB movipos = new VentasPagosEJB();			
  		movipos.setVentastrackId(model.getVentastrackId());
  		movipos.setReferencia(model.getReferencia());//egresos
  		movipos.setReferenciaId(model.getReferenciaId());//egresos
  		movipos.setTipo(model.getTipo());//en ingresos caja siempre		 		
  		movipos.setValor(model.getValor());
  		movipos.setNumDoc(model.getNumDoc());//numero doc
  		movipos.setRevisado(model.getRevisado());
  		return movipos;
  	}

 
}
