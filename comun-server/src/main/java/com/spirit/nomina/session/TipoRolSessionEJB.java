package com.spirit.nomina.session;

import java.util.ArrayList;
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

import com.spirit.general.webservice.consumer.SpiritWebServiceConsumerLocal;
import com.spirit.general.webservice.handler.WebServiceConsumerUtilesLocal;
import com.spirit.nomina.session.generated._TipoRolSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>TipoRolSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:21 $
 *
 */
@Stateless
public class TipoRolSessionEJB extends _TipoRolSession implements TipoRolSessionRemote,TipoRolSessionLocal  {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	//@EJB private WebServiceConsumerUtilesLocal wscLocal;
	
	//@EJB private SpiritWebServiceConsumerLocal swscLocal;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(TipoRolSessionEJB.class);

	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getTipoRolListSize(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from TipoRolEJB " + objectName + " where "
		+ where;
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
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findTipoRolByQuery(int startIndex,int endIndex,Map aMap) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from TipoRolEJB " + objectName + " where "
		+ where;
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
	
	
	/*private static SpiritWebServiceRemote getPort(String endpointURI) throws GenericBusinessException    {  
		try {   
			QName serviceName = new QName(
					"http://webservice.general.spirit.com/", 
			"SpiritWebService");

			URL wsdlURL = new URL(endpointURI);

			Service service = Service.create(wsdlURL, serviceName);
			return service.getPort(SpiritWebServiceRemote.class);

		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new GenericBusinessException("URL mal formada");
		}  catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		}
	}*/

	/*@WebServiceClient(name="ProcessorService",
		targetNamespace="http://SpiritWebServiceService",
		wsdlLocation="http://192.168.100.144/SpiritWebServiceService/SpiritWebService?WSDL")
	public class ProcessorService extends javax.xml.ws.Service {
		public ProcessorService( ) throws MalformedURLException {
			super(new URL("http://192.168.100.144/SpiritWebServiceService/SpiritWebService?WSDL"),
				new QName("http://192.168.100.144/SpiritWebServiceService/SpiritWebService" ) );
		}

		public ProcessorService(String wsdlLocation, QName serviceName) throws MalformedURLException {
			super(new URL(wsdlLocation), serviceName);
		}

		@WebEndpoint(name = "ProcessorPort")
		public SpiritWebServiceRemote getSpiritWebServicePort( ) {
			QName qname = new QName(
				//"http://192.168.100.144/SpiritWebServiceService/SpiritWebService","ProcessorPort");
				"http://192.168.100.144/SpiritWebServiceService/SpiritWebService");
			SpiritWebServiceRemote ws = (SpiritWebService)super.getPort(qname,SpiritWebServiceRemote.class); 
			return ws;
		}
	}*/
}
