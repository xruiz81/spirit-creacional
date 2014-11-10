package com.spirit.poscola.session;

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
import com.spirit.poscola.entity.PosColaEJB;
import com.spirit.poscola.entity.PosColaIf;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>PosColaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:24 $
 * 
 */
@Stateless
public class PosColaSessionEJB implements PosColaSessionLocal {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(PosColaSessionEJB.class);

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/

	public PosColaIf obtenerInfoColaYO() throws GenericBusinessException {
		List lista = (List) findPosColaByMe("1");
		PosColaIf posCola = null;
		if (lista != null && lista.size() > 0) {
			posCola = (PosColaIf) lista.get(0);
		}
		return posCola;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findTodosMenosPrincipalYoParametro(
			java.lang.String posName) {

		String queryString = "from PosColaEJB e where e.me = :me and e.tipoServer <> :tipo and posName <> :name ";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("me", "0");
		query.setParameter("tipo", "P");
		query.setParameter("name", posName);
		return query.getResultList();
	}

	/***************************************************************************
	 * P E R S I S T E N C E M E T H O D S
	 **************************************************************************/

	/**
	 * Adds a new posCola to the database.
	 * 
	 * @param model
	 *            a data object
	 * @return PosColaIf a data object with the primary key
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public com.spirit.poscola.entity.PosColaIf addPosCola(
			com.spirit.poscola.entity.PosColaIf model)
			throws com.spirit.exception.GenericBusinessException {
		PosColaEJB value = new PosColaEJB();
		try {
			value.setId(model.getId());
			value.setDireccionIp(model.getDireccionIp());
			value.setHostName(model.getHostName());
			value.setPort(model.getPort());
			value.setFactory(model.getFactory());
			value.setQname(model.getQname());
			value.setPosName(model.getPosName());
			value.setBodegaId(model.getBodegaId());
			value.setOficinaId(model.getOficinaId());
			value.setTipoServer(model.getTipoServer());
			value.setMe(model.getMe());
			manager.persist(value);
			manager.flush();
		} catch (Exception e) {
			log.error("Error al insertar información en posCola.", e);
			throw new GenericBusinessException(
					"Error al insertar información en posCola.");
		}

		return getPosCola(value.getPrimaryKey());
	}

	/**
	 * Stores the <code>PosColaIf</code> in the database.
	 * 
	 * @param model
	 *            the data model to store
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void savePosCola(com.spirit.poscola.entity.PosColaIf model)
			throws com.spirit.exception.GenericBusinessException {

		try {
			PosColaEJB data = new PosColaEJB();
			data.setId(model.getId());
			data.setDireccionIp(model.getDireccionIp());
			data.setHostName(model.getHostName());
			data.setPort(model.getPort());
			data.setFactory(model.getFactory());
			data.setQname(model.getQname());
			data.setPosName(model.getPosName());
			data.setBodegaId(model.getBodegaId());
			data.setOficinaId(model.getOficinaId());
			data.setTipoServer(model.getTipoServer());
			data.setMe(model.getMe());
			manager.merge(data);
			manager.flush();
		} catch (Exception e) {
			log.error("Error al actualizar información en posCola.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en posCola.");
		}

	}

	/**
	 * Removes a posCola.
	 * 
	 * @param id
	 *            the unique reference for the posCola
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deletePosCola(java.lang.Integer id)
			throws com.spirit.exception.GenericBusinessException {
		try {

			PosColaEJB data = manager.find(PosColaEJB.class, id);
			manager.remove(data);
			manager.flush();

		} catch (Exception e) {
			log.error("Error al eliminar información en posCola.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en posCola.");
		}

	}

	/**
	 * Retrieves a data object from the database by its primary key.
	 * 
	 * @param id
	 *            the unique reference
	 * @return PosColaIf the data object
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public com.spirit.poscola.entity.PosColaIf getPosCola(java.lang.Integer id) {
		return manager.find(PosColaEJB.class, id);
	}

	/**
	 * Returns a collection of all posCola instances.
	 * 
	 * @return a collection of PosColaIf objects.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getPosColaList() {
		String queryString = "from PosColaEJB e";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	/**
	 * Returns a subset of all posCola instances.
	 * 
	 * @param startIndex
	 *            the start index within the result set (1 = first record); any
	 *            zero/negative values are regarded as 1, and any values greater
	 *            than or equal to the total number of posCola instances will
	 *            simply return an empty set.
	 * @param endIndex
	 *            the end index within the result set (<code>getPosColaListSize()</code> =
	 *            last record), any values greater than or equal to the total
	 *            number of posCola instances will cause the full set to be
	 *            returned.
	 * @return a collection of PosColaIf objects, of size
	 *         <code>(endIndex - startIndex)</code>.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getPosColaList(int startIndex, int endIndex) {
		if (startIndex < 1) {
			startIndex = 1;
		}
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String queryString = "from PosColaEJB e";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setFirstResult(startIndex - 1);
		query.setMaxResults(endIndex - startIndex + 1);
		return query.getResultList();
	}

	/**
	 * Obtains the total number of posCola objects in the database.
	 * 
	 * @return an integer value.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getPosColaListSize() {
		Query countQuery = manager
				.createQuery("select count(*) from PosColaEJB");
		List countQueryResult = countQuery.getResultList();
		Integer countResult = (Integer) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}

	/**
	 * 
	 * Retrieves a list of data object for the specified id field.
	 * 
	 * @param id
	 *            the field
	 * @return Collection of PosColaIf data objects, empty list in case no
	 *         results were found.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPosColaById(java.lang.Integer id) {

		String queryString = "from PosColaEJB e where e.id = :id ";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("id", id);
		return query.getResultList();
	}

	/**
	 * 
	 * Retrieves a list of data object for the specified direccionIp field.
	 * 
	 * @param direccionIp
	 *            the field
	 * @return Collection of PosColaIf data objects, empty list in case no
	 *         results were found.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPosColaByDireccionIp(
			java.lang.String direccionIp) {

		String queryString = "from PosColaEJB e where e.direccionIp = :direccionIp ";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("direccionIp", direccionIp);
		return query.getResultList();
	}

	/**
	 * 
	 * Retrieves a list of data object for the specified hostName field.
	 * 
	 * @param hostName
	 *            the field
	 * @return Collection of PosColaIf data objects, empty list in case no
	 *         results were found.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPosColaByHostName(java.lang.String hostName) {

		String queryString = "from PosColaEJB e where e.hostName = :hostName ";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("hostName", hostName);
		return query.getResultList();
	}

	/**
	 * 
	 * Retrieves a list of data object for the specified port field.
	 * 
	 * @param port
	 *            the field
	 * @return Collection of PosColaIf data objects, empty list in case no
	 *         results were found.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPosColaByPort(java.lang.String port) {

		String queryString = "from PosColaEJB e where e.port = :port ";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("port", port);
		return query.getResultList();
	}

	/**
	 * 
	 * Retrieves a list of data object for the specified factory field.
	 * 
	 * @param factory
	 *            the field
	 * @return Collection of PosColaIf data objects, empty list in case no
	 *         results were found.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPosColaByFactory(java.lang.String factory) {

		String queryString = "from PosColaEJB e where e.factory = :factory ";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("factory", factory);
		return query.getResultList();
	}

	/**
	 * 
	 * Retrieves a list of data object for the specified qname field.
	 * 
	 * @param qname
	 *            the field
	 * @return Collection of PosColaIf data objects, empty list in case no
	 *         results were found.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPosColaByQname(java.lang.String qname) {

		String queryString = "from PosColaEJB e where e.qname = :qname ";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("qname", qname);
		return query.getResultList();
	}

	/**
	 * 
	 * Retrieves a list of data object for the specified posName field.
	 * 
	 * @param posName
	 *            the field
	 * @return Collection of PosColaIf data objects, empty list in case no
	 *         results were found.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPosColaByPosName(java.lang.String posName) {

		String queryString = "from PosColaEJB e where e.posName = :posName ";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("posName", posName);
		return query.getResultList();
	}

	/**
	 * 
	 * Retrieves a list of data object for the specified bodegaId field.
	 * 
	 * @param bodegaId
	 *            the field
	 * @return Collection of PosColaIf data objects, empty list in case no
	 *         results were found.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPosColaByBodegaId(java.lang.Long bodegaId) {

		String queryString = "from PosColaEJB e where e.bodegaId = :bodegaId ";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("bodegaId", bodegaId);
		return query.getResultList();
	}

	/**
	 * 
	 * Retrieves a list of data object for the specified oficinaId field.
	 * 
	 * @param oficinaId
	 *            the field
	 * @return Collection of PosColaIf data objects, empty list in case no
	 *         results were found.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPosColaByOficinaId(java.lang.Long oficinaId) {

		String queryString = "from PosColaEJB e where e.oficinaId = :oficinaId ";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("oficinaId", oficinaId);
		return query.getResultList();
	}

	/**
	 * 
	 * Retrieves a list of data object for the specified tipoServer field.
	 * 
	 * @param tipoServer
	 *            the field
	 * @return Collection of PosColaIf data objects, empty list in case no
	 *         results were found.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPosColaByTipoServer(
			java.lang.String tipoServer) {

		String queryString = "from PosColaEJB e where e.tipoServer = :tipoServer ";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("tipoServer", tipoServer);
		return query.getResultList();
	}

	/**
	 * 
	 * Retrieves a list of data object for the specified me field.
	 * 
	 * @param me
	 *            the field
	 * @return Collection of PosColaIf data objects, empty list in case no
	 *         results were found.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPosColaByMe(java.lang.String me) {

		String queryString = "from PosColaEJB e where e.me = :me ";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("me", me);
		return query.getResultList();
	}

	// ////////////

	/**
	 * 
	 * Retrieves a list of data object for the specified query Map.
	 * 
	 * //@param Map $field.Name the field
	 * 
	 * @return Collection of PosColaIf data objects, empty list in case no
	 *         results were found.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPosColaByQuery(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from PosColaEJB " + objectName + " where "
				+ where;
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

	// ///////////////
}
