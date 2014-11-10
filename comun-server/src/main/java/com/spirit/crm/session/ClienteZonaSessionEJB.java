package com.spirit.crm.session;

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

import com.spirit.crm.entity.ClienteZonaEJB;
import com.spirit.crm.entity.ClienteZonaIf;
import com.spirit.crm.session.generated._ClienteZonaSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>ClienteZonaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:20 $
 *
 */
@Stateless
public class ClienteZonaSessionEJB extends _ClienteZonaSession implements ClienteZonaSessionRemote,ClienteZonaSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(ClienteZonaSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   private DecimalFormat formatoEntero = new DecimalFormat("000");
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public ClienteZonaEJB registrarClienteZona(ClienteZonaIf modelClienteZona, boolean save) {
		ClienteZonaEJB clienteZona = new ClienteZonaEJB();
		
		if(save || (modelClienteZona.getCodigo() == null)){
			String codigo = getMaximoCodigoClienteZona(modelClienteZona.getClienteId());
			if(codigo.equals("")){
				codigo = "001";
			}else{
				codigo = formatoEntero.format(Integer.valueOf(codigo) + 1); 
			}
			clienteZona.setCodigo(codigo);
		}else{
			clienteZona.setCodigo(modelClienteZona.getCodigo());
		}
		
		clienteZona.setId(modelClienteZona.getId());
		clienteZona.setClienteId(modelClienteZona.getClienteId());
		clienteZona.setNombre(modelClienteZona.getNombre());
		
		return clienteZona;
	}
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public ClienteZonaEJB registrarClienteZona(ClienteZonaIf modelClienteZona) {
		ClienteZonaEJB clienteZona = new ClienteZonaEJB();
		
		if( modelClienteZona.getId() == null ){
			String codigo = getMaximoCodigoClienteZona(modelClienteZona.getClienteId());
			if(codigo.equals("")){
				codigo = "001";
			}else{
				codigo = formatoEntero.format(Integer.valueOf(codigo) + 1); 
			}
			clienteZona.setCodigo(codigo);
		}else{
			clienteZona.setCodigo(modelClienteZona.getCodigo());
		}
		
		clienteZona.setId(modelClienteZona.getId());
		clienteZona.setClienteId(modelClienteZona.getClienteId());
		clienteZona.setNombre(modelClienteZona.getNombre());
		
		return clienteZona;
	}
   
    public String getMaximoCodigoClienteZona(Long clienteId){
		String queryString = "select max(cz.codigo) from ClienteZonaEJB cz where cz.clienteId = " + clienteId + "";
		Query query = manager.createQuery(queryString);
		String codigo = "";
		if(query.getResultList().get(0)!=null){
			codigo = query.getResultList().toString().substring(1).replaceAll("]","");
		}
		return codigo;
	}

}
