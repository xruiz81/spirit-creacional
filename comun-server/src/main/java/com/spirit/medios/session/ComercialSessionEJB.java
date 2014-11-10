package com.spirit.medios.session;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.medios.entity.ComercialEJB;
import com.spirit.medios.entity.ComercialIf;
import com.spirit.medios.session.generated._ComercialSession;

/**
 * The <code>ComercialSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class ComercialSessionEJB extends _ComercialSession implements ComercialSessionRemote  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;
   
   private DecimalFormat formatoEntero = new DecimalFormat("00000");

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findComercialByClienteIdAndByEmpresaId(Long idCliente,Long idEmpresa) {
      String queryString = "select e from ComercialEJB e,CampanaEJB camp,ClienteEJB clie where e.campanaId = camp.id and camp.clienteId = clie.id and e.empresaId = " + idEmpresa + " and clie.id = " + idCliente;
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      return query.getResultList();
   }
  
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Collection findComercialByEmpresaIdByEstadoActivoAndByClienteId(Long idEmpresa, Long idCliente) {
     String queryString = "select e from ComercialEJB e,CampanaEJB camp,ClienteEJB clie where e.campanaId = camp.id and camp.clienteId = clie.id and e.empresaId = " + idEmpresa + " and e.estado = 'A' and clie.id = " + idCliente;
     // Add a an order by on all primary keys to assure reproducable results.
     String orderByPart = "";
     orderByPart += " order by e.id";
     queryString += orderByPart;
     Query query = manager.createQuery(queryString);
     return query.getResultList();
  }
  
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void procesarComercialColeccion(List<ComercialIf> comercialColeccion, List<ComercialIf> comercialEliminadoColeccion, Long clienteId) throws com.spirit.exception.GenericBusinessException{
		
	  for(ComercialIf comercial : comercialColeccion){
			if(comercial.getId() == null){
				ComercialEJB model = registrarComercial(comercial, clienteId, true);
				manager.persist(model);
			}else{
				ComercialEJB model = registrarComercial(comercial, clienteId, false);
				manager.merge(model);
			}
		}
		
		for(ComercialIf comercialEliminado : comercialEliminadoColeccion){
			ComercialEJB model = manager.find(ComercialEJB.class, comercialEliminado.getId());
			manager.remove(model);
			manager.flush();
		}
	}
  
  public ComercialEJB registrarComercial(ComercialIf comercial, Long clienteId, boolean save){
		ComercialEJB model = new ComercialEJB();
				
		if(save || (comercial.getCodigo() == null)){
			String codigo = getMaximoCodigoComercial(clienteId);
			if(codigo.equals("")){
				codigo = "00001";
			}else{
				codigo = formatoEntero.format(Integer.valueOf(codigo) + 1); 
			}
			model.setCodigo(codigo);
		}else{
			model.setCodigo(comercial.getCodigo());
		}		
		
		model.setId(comercial.getId());
		model.setNombre(comercial.getNombre());
		model.setDerechoprogramaId(comercial.getDerechoprogramaId());
		model.setDescripcion(comercial.getDescripcion());
		model.setCampanaId(comercial.getCampanaId());
		model.setVersion(comercial.getVersion());
		model.setDuracion(comercial.getDuracion());
		model.setEmpresaId(comercial.getEmpresaId());
		model.setEstado(comercial.getEstado());
		model.setProductoClienteId(comercial.getProductoClienteId());
		model.setCampanaProductoVersionId(comercial.getCampanaProductoVersionId()); //ADD 5 OCTUBRE
		
		return model;
	}
	
	public String getMaximoCodigoComercial(Long clienteId){
		String queryString = "select max(co.codigo) from ComercialEJB co, CampanaEJB ca, ClienteEJB cl where " +
				"co.campanaId = ca.id and ca.clienteId = cl.id and cl.id = " + clienteId + "";
		Query query = manager.createQuery(queryString);
		String codigo = "";
		if(query.getResultList().get(0)!=null){
			codigo = query.getResultList().toString().substring(1).replaceAll("]","");
		}
		return codigo;
	}
   
	//ADD 7 SEPTIEMBRE
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	   public Collection findComercialByProductoClienteIdAndVersion(Long idEmpresa, Long idProductoCliente, String version) {
	      String queryString = "select e from ComercialEJB e,CampanaEJB camp,ClienteEJB clie " +
	      						"where e.campanaId = camp.id and camp.clienteId = clie.id and e.empresaId = " + idEmpresa + 
	      						" and e.productoClienteId = " + idProductoCliente + " and e.version = '"+ version + "'";
	      // Add a an order by on all primary keys to assure reproducable results.
	      String orderByPart = "";
	      orderByPart += " order by e.id";
	      queryString += orderByPart;
	      Query query = manager.createQuery(queryString);
	      return query.getResultList();
	   }
	//END 7 SEPTIEMBRE
	
}
