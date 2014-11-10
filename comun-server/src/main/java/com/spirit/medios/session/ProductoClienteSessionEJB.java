package com.spirit.medios.session;

import java.text.DecimalFormat;
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
import com.spirit.medios.entity.MarcaProductoEJB;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.medios.entity.ProductoClienteEJB;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.generated._ProductoClienteSession;
import com.spirit.server.QueryBuilder;

/**
 * The <code>ProductoClienteSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class ProductoClienteSessionEJB extends _ProductoClienteSession implements ProductoClienteSessionRemote, ProductoClienteSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   private DecimalFormat formatoEntero = new DecimalFormat("000");

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public Collection findProductoClienteByQueryAndByClienteId(Map aMap, Long idClienteOficina){
	 	String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "from ProductoClienteEJB " + objectName + " where " + where + " and clienteId = " + idClienteOficina;
	 	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			String property = (String) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		return query.getResultList();
	}
	
	
	//ADD 26 JULIO
	public int findProductoClienteByQueryAndByClienteIdAndMarcaProductoIdSize(Map aMap, Long idClienteOficina,Long idMarcaProducto){
	 	String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "select distinct count(*) from ProductoClienteEJB " + objectName + " where " + where + 
	 						 " and clienteId = " + idClienteOficina +
	 						 " and marcaProductoId = " + idMarcaProducto ; 
	 	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			String property = (String) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
	}
	//END 26 JULIO
	
	
	public int findProductoClienteByQueryAndByClienteIdSize(Map aMap, Long idClienteOficina){
	 	String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "select distinct count(*) from ProductoClienteEJB " + objectName + " where " + where + " and clienteId = " + idClienteOficina;
	 	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			String property = (String) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		return countResult.intValue();
	}

	//ADD 26 JULIO
	public Collection findProductoClienteByQueryAndByClienteIdAndMarcaProductoId(int startIndex,int endIndex,Map aMap, Long idClienteOficina,Long idMarcaProducto){
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "from ProductoClienteEJB " + objectName + " where " + where + " and clienteId = " + idClienteOficina +
	 						 " and marcaProductoId = " + idMarcaProducto;
	 	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			String property = (String) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	
	}
	//END 26 JULIO
	
	public Collection findProductoClienteByQueryAndByClienteId(int startIndex,int endIndex,Map aMap, Long idClienteOficina){
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "from ProductoClienteEJB " + objectName + " where " + where + " and clienteId = " + idClienteOficina;
	 	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			String property = (String) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	
	}
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getProductoClienteList(int startIndex, int endIndex, Long idClienteOficina) {
      if (startIndex < 1) {
         startIndex = 1;
      }
      if ( (endIndex - startIndex) < 0) {
         // Just return an empty list.
         return new ArrayList();
      }
      String queryString = "select e from ProductoClienteEJB e where e.clienteId = " + idClienteOficina;
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setFirstResult(startIndex - 1);
      query.setMaxResults(endIndex - startIndex + 1);
      return query.getResultList();
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getProductoClienteList(int startIndex, int endIndex, Map aMap) {
		if (startIndex < 1) {
			startIndex = 1;
		}
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select e from ProductoClienteEJB " + objectName + " where " + where;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			String property = (String) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		query.setFirstResult(startIndex - 1);
		query.setMaxResults(endIndex - startIndex + 1);
		return query.getResultList();
	}

   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getProductoClienteList(int startIndex, int endIndex, Map aMap, Long idClienteOficina){
		if (startIndex < 1) {
			startIndex = 1;
		}
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select e from ProductoClienteEJB " + objectName + " where " + where + " and e.clienteId = " + idClienteOficina;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			String property = (String) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		query.setFirstResult(startIndex - 1);
		query.setMaxResults(endIndex - startIndex + 1);
		return query.getResultList();
	}
   
   public int getProductoClienteListSize(Long idClienteOficina){
	   Query countQuery = manager.createQuery("select count(*) from ProductoClienteEJB e where e.clienteId = " + idClienteOficina);
	   List countQueryResult = countQuery.getResultList();
	   Integer countResult = (Integer) countQueryResult.get(0);
	   return countResult.intValue();
	}

	public int getProductoClienteListSize(Map aMap){
		String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "select count(*) from ProductoClienteEJB " + objectName + " where " + where;
	 	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			String property = (String) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}

		List countQueryResult = query.getResultList();
	    Integer countResult = (Integer) countQueryResult.get(0);
	    return countResult.intValue();
	}

	public int getProductoClienteListSize(Map aMap, Long idClienteOficina){
		String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "select count(*) from ProductoClienteEJB " + objectName + " where " + where + " and e.clienteId = " + idClienteOficina;
	 	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			String property = (String) aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}

		List countQueryResult = query.getResultList();
	    Integer countResult = (Integer) countQueryResult.get(0);
	    return countResult.intValue();
	}
	
	 @TransactionAttribute(TransactionAttributeType.REQUIRED)
	    public java.util.Collection findProductoClienteByQuery(int startIndex,int endIndex,Map aMap) {
		 if ((endIndex - startIndex) < 0) {
				return new ArrayList();
			}
	 	String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "from ProductoClienteEJB " + objectName + " where " + where;
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

	 	public void procesarProductoClienteColeccion(MarcaProductoIf marcaProducto, List<ProductoClienteIf> productoClienteColeccion, List<ProductoClienteIf> productoClienteEliminadoColeccion) throws com.spirit.exception.GenericBusinessException{
	 		MarcaProductoEJB marca = new MarcaProductoEJB();
	 		if(marcaProducto.getId() == null){
	 			marca = registrarMarcaProducto(marcaProducto, true);
	 			manager.persist(marca);
	 		}else{
	 			marca = registrarMarcaProducto(marcaProducto, false);
	 			manager.merge(marca);
	 		}
	 		
	 		for(ProductoClienteIf productoCliente : productoClienteColeccion){
				if(productoCliente.getId() == null){
					productoCliente.setMarcaProductoId(marca.getPrimaryKey());
					ProductoClienteEJB model = registrarProductoCliente(productoCliente, true);
					manager.persist(model);
				}else{
					ProductoClienteEJB model = registrarProductoCliente(productoCliente, false);
					manager.merge(model);
				}
			}
			
			for(ProductoClienteIf productoClienteEliminado : productoClienteEliminadoColeccion){
				if(productoClienteEliminado.getId() != null){
					ProductoClienteEJB model = manager.find(ProductoClienteEJB.class, productoClienteEliminado.getId());
					manager.remove(model);
					manager.flush();
				}				
			}
		}
	 	
	 	public void eliminarMarcaProducto(MarcaProductoIf marcaProducto) throws com.spirit.exception.GenericBusinessException{
	 		Collection<ProductoClienteIf> productoClienteList = findProductoClienteByMarcaProductoId(marcaProducto.getId());
	 		
	 		for(ProductoClienteIf productoClienteEliminado : productoClienteList){
				ProductoClienteEJB model = manager.find(ProductoClienteEJB.class, productoClienteEliminado.getId());
				manager.remove(model);
			}
	 		
	 		MarcaProductoEJB model = manager.find(MarcaProductoEJB.class, marcaProducto.getId());
			manager.remove(model);
			manager.flush();
	 	}
	 	
	 	public MarcaProductoEJB registrarMarcaProducto(MarcaProductoIf marcaProducto, boolean save){
	 		MarcaProductoEJB model = new MarcaProductoEJB();
				 		
	 		if(save || (marcaProducto.getCodigo() == null)){
				String codigo = getMaximoCodigoMarcaProducto(marcaProducto.getClienteId());
				if(codigo.equals("")){
					codigo = "001";
				}else{
					codigo = formatoEntero.format(Integer.valueOf(codigo) + 1); 
				}
				model.setCodigo(codigo);
			}else{
				model.setCodigo(marcaProducto.getCodigo());
			}		
	 		
	 		model.setClienteId(marcaProducto.getClienteId());
	 		model.setEstado(marcaProducto.getEstado());
	 		model.setFechaCreacion(marcaProducto.getFechaCreacion());
	 		model.setId(marcaProducto.getId());
	 		model.setNombre(marcaProducto.getNombre());
	 		model.setTipo(marcaProducto.getTipo());
	 		model.setEmpresaId(marcaProducto.getEmpresaId());
	 		
			return model;
		}
	 	
	 	public ProductoClienteEJB registrarProductoCliente(ProductoClienteIf productoCliente, boolean save){
			ProductoClienteEJB model = new ProductoClienteEJB();
			model.setClienteId(productoCliente.getClienteId());
			
			if(save || (productoCliente.getCodigo() == null)){
				String codigo = getMaximoCodigoProductoCliente(productoCliente.getClienteId());
				if(codigo.equals("")){
					codigo = "001";
				}else{
					codigo = formatoEntero.format(Integer.valueOf(codigo) + 1); 
				}
				model.setCodigo(codigo);
			}else{
				model.setCodigo(productoCliente.getCodigo());
			}		
			
			model.setCreativoId(productoCliente.getCreativoId());
			model.setEjecutivoId(productoCliente.getEjecutivoId());
			model.setEstado(productoCliente.getEstado());
			model.setId(productoCliente.getId());
			model.setNombre(productoCliente.getNombre());
			model.setMarcaProductoId(productoCliente.getMarcaProductoId());
			model.setMarcaProductoNombre(productoCliente.getMarcaProductoNombre());
			
			return model;
		}
	 	
	 	public String getMaximoCodigoMarcaProducto(Long clienteId){
			String queryString = "select max(mp.codigo) from MarcaProductoEJB mp where mp.clienteId = " + clienteId + "";
			Query query = manager.createQuery(queryString);
			String codigo = "";
			if(query.getResultList().get(0)!=null){
				codigo = query.getResultList().toString().substring(1).replaceAll("]","");
			}
			return codigo;
		}
		
		public String getMaximoCodigoProductoCliente(Long clienteId){
			String queryString = "select max(pc.codigo) from ProductoClienteEJB pc where pc.clienteId = " + clienteId + "";
			Query query = manager.createQuery(queryString);
			String codigo = "";
			if(query.getResultList().get(0)!=null){
				codigo = query.getResultList().toString().substring(1).replaceAll("]","");
			}
			return codigo;
		}
		
		public Collection findProductoClienteByCampanaId(Long idCampana){
		 	String queryString = "select pc from ProductoClienteEJB pc, CampanaProductoEJB cp where pc.id = cp.productoClienteId and cp.campanaId = " + idCampana + "";
		 	Query query = manager.createQuery(queryString);
			return query.getResultList();
		}
		
		public Collection findProductoClienteByOrdenTrabajoId(Long idOrdenTrabajo){
		 	String queryString = "select pc from ProductoClienteEJB pc, OrdenTrabajoProductoEJB otp where pc.id = otp.productoClienteId and otp.ordenTrabajoId = " + idOrdenTrabajo + "";
		 	Query query = manager.createQuery(queryString);
			return query.getResultList();
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public java.util.Collection findProductoClienteByEmpresaId(Long idEmpresa)throws GenericBusinessException {
						
			 String cadenaQuery = "select distinct pc from ProductoClienteEJB pc, MarcaProductoEJB mp where " +
			 					  "pc.marcaProductoId = mp.id and mp.empresaId = " + idEmpresa + "";
					 							    		
			Query query = manager.createQuery(cadenaQuery);
			return query.getResultList();
		}
}
