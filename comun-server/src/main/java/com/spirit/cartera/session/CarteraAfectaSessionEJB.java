package com.spirit.cartera.session;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.cartera.session.generated._CarteraAfectaSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.performance.session.PerformanceInterceptor;
import com.spirit.server.QueryBuilder;

/**
 * The <code>CarteraAfectaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.2 $, $Date: 2014/07/04 22:53:49 $
 *
 */
@Stateless
@Interceptors( { PerformanceInterceptor.class })
public class CarteraAfectaSessionEJB extends _CarteraAfectaSession implements CarteraAfectaSessionRemote, CarteraAfectaSessionLocal  {

	@PersistenceContext(unitName="spirit")
	private EntityManager manager;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	   public java.util.Collection findCarteraAfectaByQueryByFechas(Map aMap, Date fechaInicial, Date fechaFinal, java.lang.Long idEmpresa) 
		throws com.spirit.exception.GenericBusinessException{
			try{
		  	 /*
		  	    String objectName = "ca";
				String queryString = "select ca,cd,car from CarteraAfectaEJB " + objectName + ",CarteraDetalleEJB cd,CarteraEJB car where " ;
				queryString += " cd.carteraId=car.id and ca.carteradetalleId=cd.id";
				queryString += " and ca.fechaAplicacion between :fechaInicial and :fechaFinal" ;
				queryString += " and ca.carteraafectaId in (select cd.id from CarteraEJB c,CarteraDetalleEJB cd where c.id=cd.carteraId and";
				queryString += " c.fechaEmision not between :fechaInicial and :fechaFinal)" ;
			 */
				
				String objectName = "ca";
				String queryString = "select distinct ca,cd,car from CarteraAfectaEJB " + objectName + ",CarteraDetalleEJB cd,CarteraEJB car where " ;
				queryString += " cd.carteraId=car.id and ca.carteradetalleId=cd.id";
				queryString += " and ca.fechaAplicacion between :fechaInicial and :fechaFinal" ;
				 
				 if (aMap!=null && aMap.size()>0) {
					 String where = QueryBuilder.buildWhere(aMap, objectName);
					 queryString += (" and  " + where);
				 }
				 
				//queryString += " order by ca.codigo asc";
				
				Query query = manager.createQuery(queryString);
				 
				if (aMap!=null){
					Iterator it = aMap.keySet().iterator();
					while (it.hasNext()) {
						String propertyKey = (String) it.next();
						Object property = aMap.get(propertyKey);
						query.setParameter(propertyKey, property);
					}
				}
				
				query.setParameter("fechaInicial",fechaInicial);
				query.setParameter("fechaFinal",fechaFinal);
				
				
				return query.getResultList();
			} catch(Exception e){
				e.printStackTrace();
				throw new GenericBusinessException("Se ha producido un error al consultar CarteraAfecta por fecha ");
			}
	   }
	
	 @TransactionAttribute(TransactionAttributeType.REQUIRED)
	   public java.util.Collection findCarteraAfectaByQueryByFechasdos(Map aMap, Date fechaInicial, Date fechaFinal, java.lang.Long idEmpresa) 
		throws com.spirit.exception.GenericBusinessException{
			try{
				
				
				/*
				 select * from CARTERA_AFECTA ca WHERE CARTERAAFECTA_ID IN (
						 select CD.ID from CARTERA c,cartera_detalle cd where c.id=cd.cartera_id and c.fecha_emision not between '08-SEP-09' AND '08-SEP-09' )
						  and ca.fecha_aplicacion between '08-SEP-09' AND '08-SEP-09'
						 order by ca.valor*/
						 
						 
				String objectName = "ca";
				String queryString = "select distinct ca from CarteraAfectaEJB " + objectName + " where " ;				
				queryString += "ca.fechaAplicacion between :fechaInicial and :fechaFinal" ;
				queryString += " and ca.carteraafectaId in (select cd.id from CarteraEJB c,CarteraDetalleEJB cd where c.id=cd.carteraId and";
				queryString += " c.fechaEmision not between :fechaInicial and :fechaFinal)" ;
			 
				 
				 if (aMap!=null && aMap.size()>0) {
					 String where = QueryBuilder.buildWhere(aMap, objectName);
					 queryString += (" and  " + where);
				 }
				 
				//queryString += " order by ca.codigo asc";
				
				Query query = manager.createQuery(queryString);
				 
				if (aMap!=null){
					Iterator it = aMap.keySet().iterator();
					while (it.hasNext()) {
						String propertyKey = (String) it.next();
						Object property = aMap.get(propertyKey);
						query.setParameter(propertyKey, property);
					}
				}
				
				query.setParameter("fechaInicial",fechaInicial);
				query.setParameter("fechaFinal",fechaFinal);
				
				
				return query.getResultList();
			} catch(Exception e){
				e.printStackTrace();
				throw new GenericBusinessException("Se ha producido un error al consultar CarteraAfecta por fecha ");
			}
	   }
	
	
	
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findCarteraAfectaByCarteraAfectaIdAndByCartera(java.lang.Long carteraAfectaId,java.lang.String esCartera) {
     String queryString = "from CarteraAfectaEJB e where e.carteraafectaId = " + carteraAfectaId + " and e.cartera = '" + esCartera + "'";
     // Add a an order by on all primary keys to assure reproducable results.
     String orderByPart = "";
     orderByPart += " order by e.id";
     queryString += orderByPart;
     Query query = manager.createQuery(queryString);
     return query.getResultList();
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findCarteraAfectaByFechaInicioFechaFin(Map aMap,Date fechaInicio,Date fechaFin, java.lang.Long idEmpresa) 
	throws com.spirit.exception.GenericBusinessException{
		try{
			String objectName = "ca";
			String queryString = "select distinct ca from CarteraAfectaEJB " + objectName + ",CarteraDetalleEJB cd,DocumentoEJB d, TipoDocumentoEJB td where " ;
			queryString += " ca.carteradetalleId = cd.id and cd.documentoId = d.id ";
			queryString += " and d.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa ;
			 if (fechaInicio!=null && fechaFin!=null){
				 queryString += " and ca.fechaAplicacion >= :fechaInicio and ca.fechaAplicacion <= :fechaFin";
			 }
			 
			 if (aMap!=null && aMap.size()>0) {
				 String where = QueryBuilder.buildWhere(aMap, objectName);
				 queryString += (" and  "+where);
			 }
			 
			 //queryString += " order by e.codigo asc";
			
			Query query = manager.createQuery(queryString);
			 
			if (aMap!=null){
				Iterator it = aMap.keySet().iterator();
				while (it.hasNext()) {
					String propertyKey = (String) it.next();
					Object property = aMap.get(propertyKey);
					query.setParameter(propertyKey, property);
				}
			}
			
			query.setParameter("fechaInicio",fechaInicio);
			query.setParameter("fechaFin",fechaFin);
			return query.getResultList();
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al consultar CarteraAfecta por fecha ");
		}
   }
   
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Map<Long,Object[]> findCarteraAfectaSaldoByQueryByFechaInicialByFechaFinalAndEmpresaId(Map aMap, Date fechaInicial, Date fechaFinal, java.lang.Long idEmpresa) 
	throws com.spirit.exception.GenericBusinessException{
		try{
			
			Map<Long,Object[]> resultado = new HashMap<Long, Object[]>();	
			String objectName = "ca";
			String queryString = "select ca.id,ca.carteraafectaId,ca.carteradetalleId,ca.valor from CarteraAfectaEJB " + objectName + ", CarteraDetalleEJB cd, DocumentoEJB d, TipoDocumentoEJB td where " ;
			queryString += " ca.carteradetalleId = cd.id and cd.documentoId = d.id ";
			queryString += " and d.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa ;
			if (fechaInicial!=null)
				 queryString += " and ca.fechaAplicacion >= :fechaInicial";
			 if (fechaFinal!=null)
				 queryString += " and ca.fechaAplicacion <= :fechaFinal";
			 
			 if (aMap!=null && aMap.size()>0) {
				 String where = QueryBuilder.buildWhere(aMap, objectName);
				 queryString += (" and  " + where);
			 }			 
			//queryString += " order by ca.codigo asc";			
			Query query = manager.createQuery(queryString);			 
			if (aMap!=null){
				Iterator it = aMap.keySet().iterator();
				while (it.hasNext()) {
					String propertyKey = (String) it.next();
					Object property = aMap.get(propertyKey);
					query.setParameter(propertyKey, property);
				}
			}
			
			if (fechaInicial!=null)
				query.setParameter("fechaInicial",fechaInicial);
			if (fechaFinal!=null)
				query.setParameter("fechaFinal",fechaFinal);
			
			Collection cc = query.getResultList();
			if(cc.size()>0) {		
				Iterator it=cc.iterator();		
				while(it.hasNext()){		
					Object[] datos = (Object[]) it.next();				
					Long id = (Long) datos[0];
					Long afectaId = (Long) datos[1];
					Long detalleId = (Long) datos[2];
					BigDecimal valor = (BigDecimal) datos[3];
					
					Object[] datosValores = new Object[3];				
					datosValores[0] = afectaId;
					datosValores[1] = detalleId;
					datosValores[2] = valor;
					
					resultado.put(id, datosValores);				
				}			
			}
			return resultado;			 
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException(e+"Se ha producido un error al consultar CarteraAfecta por fecha ");
		}
   }
   
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findCarteraAfectaByQueryByFechaInicialByFechaFinalByEmpresaIdAndCuentaBancariaId(Map aMap, Date fechaInicial, Date fechaFinal, java.lang.Long idEmpresa, java.lang.Long idCuentaBancaria) 
	throws com.spirit.exception.GenericBusinessException{
		try{
			String objectName = "ca";
			String queryString = "select distinct ca from CarteraAfectaEJB " + objectName + ", CarteraDetalleEJB cd, DocumentoEJB d, TipoDocumentoEJB td where " ;
			queryString += " ca.carteradetalleId = cd.id and cd.documentoId = d.id ";
			queryString += " and d.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa ;
			
			if (fechaInicial!=null)
				 queryString += " and ca.fechaAplicacion >= :fechaInicial";
			
			if (fechaFinal!=null)
				 queryString += " and ca.fechaAplicacion <= :fechaFinal";
			 
			if (aMap!=null && aMap.size()>0) {
				 String where = QueryBuilder.buildWhere(aMap, objectName);
				 queryString += (" and  " + where);
			}
			
			if (idCuentaBancaria!=null)
				 queryString += " and cd.depositoCuentaBancariaId = " + idCuentaBancaria;
			 
			//queryString += " order by ca.codigo asc";
			
			Query query = manager.createQuery(queryString);
			 
			if (aMap!=null){
				Iterator it = aMap.keySet().iterator();
				while (it.hasNext()) {
					String propertyKey = (String) it.next();
					Object property = aMap.get(propertyKey);
					query.setParameter(propertyKey, property);
				}
			}
			
			if (fechaInicial!=null)
				query.setParameter("fechaInicial",fechaInicial);
			if (fechaFinal!=null)
				query.setParameter("fechaFinal",fechaFinal);
			return query.getResultList();
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al consultar CarteraAfecta por fecha ");
		}
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findCarteraAfectaByEmpresaIdByFechaInicialByFechaFinalByClienteOficinaIdByTipoProveedorIdAndByTipoDocumentoId(java.lang.Long idEmpresa, Date fechaInicial, Date fechaFinal, java.lang.Long idClienteOficina, java.lang.Long idTipoProveedor, java.lang.Long idTipoDocumento, java.lang.Long idCuentaBancariaDeposito, java.lang.Long idTipoProducto) 
	throws com.spirit.exception.GenericBusinessException{
		try{
			String objectName = "ca";
			
			/*String queryString = "select distinct ca from CarteraAfectaEJB " + objectName + ", CarteraDetalleEJB cd, CarteraEJB c, FacturaEJB f, FacturaDetalleEJB fd, ProductoEJB pr, ClienteOficinaEJB provof, ClienteEJB prov, TipoProveedorEJB tp where " ;
			queryString += " ca.carteraafectaId = cd.id and cd.carteraId = c.id and c.referenciaId = f.id and f.id = fd.facturaId and fd.productoId = pr.id and pr.proveedorId = provof.id and provof.clienteId = prov.id and prov.tipoproveedorId = tp.id ";
			queryString += " and f.clienteoficinaId = " + idClienteOficina + " and tp.empresaId = " + idEmpresa;*/
			
			String queryString = "select distinct ca from CarteraAfectaEJB " + objectName + ", CarteraDetalleEJB cd, CarteraEJB c, FacturaEJB f, FacturaDetalleEJB fd, ProductoEJB pr, ClienteOficinaEJB provof, ClienteEJB prov, TipoProveedorEJB tp, TipoProductoEJB tpr, GenericoEJB g, " ;
			queryString += " CarteraDetalleEJB cdd, CarteraEJB cc where ca.carteradetalleId = cdd.id and cdd.carteraId = cc.id and (cdd.depositoCuentaBancariaId = " + idCuentaBancariaDeposito + " or  cdd.transferenciaCuentaDestinoId = " + idCuentaBancariaDeposito + ") and ";
			queryString += " ca.carteraafectaId = cd.id and cd.carteraId = c.id and c.referenciaId = f.id and f.id = fd.facturaId and fd.productoId = pr.id and pr.proveedorId = provof.id and provof.clienteId = prov.id and prov.tipoproveedorId = tp.id and pr.genericoId = g.id and g.tipoproductoId = tpr.id ";
			queryString += " and f.clienteoficinaId = " + idClienteOficina + " and tp.empresaId = " + idEmpresa;
			
			if (idTipoProveedor!=null)
				 queryString += " and tp.id = " + idTipoProveedor + "";
			
			if (idTipoProducto!=null)
				 queryString += " and tpr.id = " + idTipoProducto + "";
			
			if (idTipoDocumento!=null)
				 queryString += " and f.tipodocumentoId = " + idTipoDocumento + "";
			
			/*if (fechaInicial!=null)
				 queryString += " and ca.fechaAplicacion >= :fechaInicial";			
			if (fechaFinal!=null)
				 queryString += " and ca.fechaAplicacion <= :fechaFinal";*/
			
			if (fechaInicial!=null)
				 queryString += " and cc.fechaEmision >= :fechaInicial";			
			if (fechaFinal!=null)
				 queryString += " and cc.fechaEmision <= :fechaFinal";
			 
			Query query = manager.createQuery(queryString);
			 
			if (fechaInicial!=null)
				query.setParameter("fechaInicial",fechaInicial);
			if (fechaFinal!=null)
				query.setParameter("fechaFinal",fechaFinal);
			
			return query.getResultList();
			
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al consultar CarteraAfecta por fecha ");
		}
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findCarteraAfectaCarteraIngresoDetalleByTipoCarteraByReferenciaAfectadaIdAndByPreimpreso(String tipoCartera, java.lang.Long idReferenciaAfectada, String preimpreso)
	throws com.spirit.exception.GenericBusinessException{
		try{
			String queryString = "select distinct ca, cid, c from CarteraAfectaEJB ca, CarteraDetalleEJB cid, CarteraEJB c,CarteraDetalleEJB cd where c.referenciaId = " + idReferenciaAfectada + " and c.tipo = '" + tipoCartera + "' " +
					"and c.preimpreso = '" + preimpreso + "' and c.id = cd.carteraId and cd.id = ca.carteraafectaId and ca.carteradetalleId = cid.id";
						
			Query query = manager.createQuery(queryString);	
			return query.getResultList();
			
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al consultar CarteraAfecta por fecha ");
		}
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findCarteraAfectaByCarteraDetalleAfectadaIdSiLaAfectacionNoEsUnaRetencion(java.lang.Long idCarteraDetalleAfectada)
	throws com.spirit.exception.GenericBusinessException{
		try{
			String queryString = "select distinct ca from CarteraAfectaEJB ca, CarteraDetalleEJB cd where " +
					"ca.carteraafectaId = " + idCarteraDetalleAfectada + " and ca.carteradetalleId = cd.id and cd.sriClienteRetencionId is null";
						
			Query query = manager.createQuery(queryString);
			
			//query.setParameter("carteraafecta_id",idCarteraDetalleAfectada);
			
			return query.getResultList();
			
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al consultar CarteraAfecta por fecha ");
		}
   }

}
