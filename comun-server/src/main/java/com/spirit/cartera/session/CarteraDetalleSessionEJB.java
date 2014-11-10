package com.spirit.cartera.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.cartera.entity.CarteraDetalleData;
import com.spirit.cartera.entity.CarteraDetalleEJB;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraEJB;
import com.spirit.cartera.handler.WalletDetailData;
import com.spirit.cartera.session.generated._CarteraDetalleSession;
import com.spirit.client.SpiritConstants;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaEJB;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.session.FacturaDetalleSessionLocal;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.session.CajaSessionLocal;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.OficinaSessionLocal;
import com.spirit.general.session.UsuarioSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.performance.session.PerformanceInterceptor;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>CarteraDetalleSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:20 $
 *
 */
@Stateless
@Interceptors( { PerformanceInterceptor.class })
public class CarteraDetalleSessionEJB extends _CarteraDetalleSession implements CarteraDetalleSessionRemote, CarteraDetalleSessionLocal  {

   @PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	@EJB
	private UtilitariosSessionLocal utilitariosLocal;
	@EJB
	private FacturaDetalleSessionLocal facturaDetalleLocal;
	@EJB
	private DocumentoSessionLocal documentoLocal;
	@EJB
	private UsuarioSessionLocal usuarioLocal;
	@EJB
	private CajaSessionLocal cajaLocal;
	@EJB
	private OficinaSessionLocal oficinaLocal;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(CarteraDetalleSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCarteraDetalleByCarteraAndBySignoCarteraAndByFechaInicioAndByFechaFin(java.lang.String cartera,java.lang.String signoCartera, java.sql.Date fechaInicio, java.sql.Date fechaFin, java.lang.Long idEmpresa) {
		String queryString = "select distinct cd from CarteraDetalleEJB cd, DocumentoEJB d, TipoDocumentoEJB td where cd.documentoId = d.id and d.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa + " and td.signocartera = '" + signoCartera + "' and cd.cartera = '"+ cartera +"' and cd.fechaCartera between :fechaInicio and :fechaFin";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by cd.carteraId,cd.fechaCartera";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCarteraDetalleByClienteAndByTipoDocumentoAndByTipoCarteraAndBySignoCarteraSaldoPositivo(java.lang.Long idCliente, java.lang.Long idTipoDocumento, String tipoCartera, String signoCartera, java.lang.Long idEmpresa) throws GenericBusinessException {
		String objectName = "cd";
		String queryString = "select distinct cd, c from ClienteOficinaEJB co, CarteraEJB c, CarteraDetalleEJB " + objectName + ", DocumentoEJB d, TipoDocumentoEJB td where co.id = " + idCliente + " and co.id = c.clienteoficinaId and c.tipo = '" + tipoCartera + "' and c.id = cd.carteraId and cd.saldo > 0.0 and cd.documentoId = d.id and d.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa + " and td.signocartera = '" + signoCartera + "' and d.tipoDocumentoId = " + idTipoDocumento + " order by c.preimpreso asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCarteraDetalleByClienteAndByTipoDocumentoAndByTipoCarteraAndBySignoCarteraSaldoPositivo(java.lang.Long idCliente, java.lang.Long idTipoDocumento, String tipoCartera, String signoCartera, int startIndex, int endIndex, java.lang.Long idEmpresa) throws GenericBusinessException {
		if ( (endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		String objectName = "cd";
		String queryString = "select distinct cd, c from ClienteOficinaEJB co, CarteraEJB c, CarteraDetalleEJB " + objectName + ", DocumentoEJB d, TipoDocumentoEJB td where co.id = " + idCliente + " and co.id = c.clienteoficinaId and c.tipo = '" + tipoCartera + "' and c.id = cd.carteraId and cd.saldo > 0.00 and cd.documentoId = d.id and d.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa + " and td.signocartera = '" + signoCartera + "' and d.tipoDocumentoId = " + idTipoDocumento + " order by c.preimpreso asc";
		
		Query query = manager.createQuery(queryString);
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCarteraDetalleByClienteAndByTipoDocumentoAndByTipoCarteraAndBySignoCartera(java.lang.Long idCliente, java.lang.Long idTipoDocumento, String tipoCartera, String signoCartera, java.lang.Long idEmpresa, Long carteraUpdateId) throws GenericBusinessException {
		/*select distinct cd.*, c.* from Cliente_Oficina co, Cartera c, Cartera_Detalle cd, Documento d, Tipo_Documento td 
		 where co.id = 117 and co.id = c.clienteoficina_Id and c.tipo = 'P' and c.id = cd.cartera_Id and cd.documento_Id = d.id 
		 and d.tipodocumento_Id = td.id and td.empresa_Id = 1 and td.signocartera = '+' and d.tipodocumento_Id = 3 
		 and (cd.SALDO > 0.00 or (cd.id in (select ca.CARTERAAFECTA_ID from cartera car, cartera_detalle cde, cartera_afecta ca 
		 where car.id = cde.CARTERA_ID and cde.ID = ca.CARTERADETALLE_ID and car.id = 14542))) order by c.preimpreso asc*/
		
		String objectName = "cd";
		String queryString = "select distinct cd, c from ClienteOficinaEJB co, CarteraEJB c, CarteraDetalleEJB " + objectName + 
		", DocumentoEJB d, TipoDocumentoEJB td where co.id = " + idCliente + " and co.id = c.clienteoficinaId and c.tipo = '" + tipoCartera + 
		"' and c.id = cd.carteraId and cd.documentoId = d.id and d.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa + 
		" and td.signocartera = '" + signoCartera + "' and d.tipoDocumentoId = " + idTipoDocumento +
		" and (cd.saldo > 0.00 or (cd.id in (select ca.carteraafectaId from CarteraEJB car, CarteraDetalleEJB cde, CarteraAfectaEJB ca" +
		" where car.id = cde.carteraId and cde.id = ca.carteradetalleId and car.id = " + carteraUpdateId + " ))) order by c.preimpreso asc";
		
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findPendingAccountsByWalletTypeByBusinessOperatorOfficeAndWalletSign(String walletType, ClienteOficinaIf businessOperatorOffice, String walletSign) throws GenericBusinessException {
		String selectFromString = "select distinct c, cd, cl, td, d from CarteraEJB c, CarteraDetalleEJB cd, ClienteEJB cl, ClienteOficinaEJB co, TipoDocumentoEJB td, DocumentoEJB d";
		String whereJoinsString = "where c.id = cd.carteraId and c.clienteoficinaId = co.id and co.clienteId = cl.id and c.tipodocumentoId = td.id and cd.documentoId = d.id";
		String whereConditionsString = "and c.tipo = :walletType and c.clienteoficinaId = :businessOperatorOfficeId and td.signocartera = :walletSign and cd.saldo > :zero";
		//String orderByString = "order by c.fechaEmision asc, d.nombre asc";
		String orderByString = "order by c.preimpreso asc";
		String queryString = selectFromString + " " + whereJoinsString + " " + whereConditionsString + " " + orderByString;
		Query query = manager.createQuery(queryString);
		query.setParameter("walletType", walletType);
		query.setParameter("businessOperatorOfficeId", businessOperatorOffice.getId());
		query.setParameter("walletSign", walletSign);
		query.setParameter("zero", SpiritConstants.getZeroValue());
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCarteraDetalleByClienteAndByTipoDocumentoAndByTipoCarteraAndBySignoCartera(java.lang.Long idCliente, java.lang.Long idTipoDocumento, String tipoCartera, String signoCartera, int startIndex, int endIndex, java.lang.Long idEmpresa, Long carteraUpdateId) throws GenericBusinessException {
		if ( (endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		String objectName = "cd";
		String queryString = "select distinct cd, c from ClienteOficinaEJB co, CarteraEJB c, CarteraDetalleEJB " + objectName + 
		", DocumentoEJB d, TipoDocumentoEJB td where co.id = " + idCliente + " and co.id = c.clienteoficinaId and c.tipo = '" + tipoCartera + 
		"' and c.id = cd.carteraId and cd.documentoId = d.id and d.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa + 
		" and td.signocartera = '" + signoCartera + "' and d.tipoDocumentoId = " + idTipoDocumento + 
		" and (cd.saldo > 0.00 or (cd.id in (select ca.carteraafectaId from CarteraEJB car, CarteraDetalleEJB cde, CarteraAfectaEJB ca" +
		" where car.id = cde.carteraId and cde.id = ca.carteradetalleId and car.id = " + carteraUpdateId + " ))) order by c.preimpreso asc";
				
		Query query = manager.createQuery(queryString);
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}
	
	
	//select * from Cartera_Detalle e where e.FECHA_VENCIMIENTO BETWEEN TO_Date( '9/2/2006', 'MM/DD/YYYY') AND TO_Date( '9/25/2006', 'MM/DD/YYYY')
	public Collection findCarteraDetalleByFechas(Long idCartera, java.sql.Date fechaInicio, java.sql.Date fechaFinal)throws GenericBusinessException{
		
		String queryString = "from CarteraDetalleEJB e where e.carteraId = " + idCartera + " and e.fechaCartera between :fechaInicio and :fechaFinal";
		
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFinal",fechaFinal);
		return query.getResultList();
	}
	
	public Collection findCarteraDetalleByFechaInicialByFechaFinalAndEmpresaId_DOS(java.sql.Date fechaFinal)throws GenericBusinessException {
	
		String queryString = "select distinct cd from CarteraDetalleEJB cd where cd.carteraId NOT LIKE '-1' ";
		if (fechaFinal != null)
			queryString += " and cd.fechaCartera <= :fechaFinal"; 
		
		Query query = manager.createQuery(queryString); 
		
		if (fechaFinal != null)
			query.setParameter("fechaFinal",fechaFinal);
		
		return query.getResultList();
	
	}
	
	
	public Collection findCarteraDetalleByFechaInicialByFechaFinalAndEmpresaId_AFECTA(java.sql.Date fechaInicial, java.sql.Date fechaFinal, Long idEmpresa)throws GenericBusinessException {
		String queryString = "select distinct cd from CarteraEJB c, CarteraDetalleEJB cd, TipoDocumentoEJB td " +
				"where cd.carteraId = c.id and c.tipodocumentoId = td.id and td.empresaId = " + idEmpresa+" "+
				"and (cd.id in (select af.carteradetalleId from CarteraAfectaEJB af) or cd.id in (select afc.carteraafectaId from CarteraAfectaEJB afc)) ";
		
		if (fechaInicial != null)
			queryString += " and cd.fechaCartera >= :fechaInicial";
		
		if (fechaFinal != null)
			queryString += " and cd.fechaCartera <= :fechaFinal"; 
		
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by cd.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		if (fechaInicial != null)
			query.setParameter("fechaInicial",fechaInicial);
		if (fechaFinal != null)
			query.setParameter("fechaFinal",fechaFinal);
		return query.getResultList();
	}
	
	public Collection findCarteraDetalleByFechaInicialByFechaFinalAndEmpresaId(java.sql.Date fechaInicial, java.sql.Date fechaFinal, Long idEmpresa)throws GenericBusinessException {
		String queryString = "select distinct cd from CarteraEJB c, CarteraDetalleEJB cd, TipoDocumentoEJB td where cd.carteraId = c.id and c.tipodocumentoId = td.id and td.empresaId = " + idEmpresa;		
		if (fechaInicial != null)
			queryString += " and c.fechaEmision >= :fechaInicial";		
		if (fechaFinal != null)
			queryString += " and c.fechaEmision <= :fechaFinal";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by cd.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		if (fechaInicial != null)
			query.setParameter("fechaInicial",fechaInicial);
		if (fechaFinal != null)
			query.setParameter("fechaFinal",fechaFinal);
		return query.getResultList();
	}
	
	public Map<Long,Long[]> datosMapa(java.sql.Date fechaInicial, java.sql.Date fechaFinal, Long idEmpresa)throws GenericBusinessException {		
		Map<Long,Long[]> resultado = new HashMap<Long, Long[]>();		
		Collection cc=null;
		cc=busquedaMap(fechaInicial, fechaFinal, idEmpresa);
		
		if(cc.size()>0)
		{		
			Iterator it=cc.iterator();		
			while(it.hasNext()){		
				Object[] datos = (Object[]) it.next();				
				Long idCartDetalle = (Long) datos[0];
				Long carteraId = (Long) datos[1];
				Long documentoId = (Long) datos[2];	
				
				Long[] datosValores = new Long[2];
				datosValores[0] = carteraId;
				datosValores[1] = documentoId;
				
				resultado.put(idCartDetalle, datosValores);				
			}			
		}
		return resultado;
	}
	
	public Collection busquedaMap(java.sql.Date fechaInicial, java.sql.Date fechaFinal, Long idEmpresa)throws GenericBusinessException {
		String queryString = "select cd.id,cd.carteraId,cd.documentoId from CarteraEJB c, CarteraDetalleEJB cd, TipoDocumentoEJB td where cd.carteraId = c.id and c.tipodocumentoId = td.id and td.empresaId = " + idEmpresa;
		
	
		/*if (fechaInicial != null)
			queryString += " and cd.fechaCartera >= :fechaInicial";
		
		if (fechaFinal != null)
			queryString += " and cd.fechaCartera <= :fechaFinal";*/
		if (fechaInicial != null)
			queryString += " and c.fechaEmision >= :fechaInicial";
		
		if (fechaFinal != null)
			queryString += " and c.fechaEmision <= :fechaFinal";
		
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by cd.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		if (fechaInicial != null)
			query.setParameter("fechaInicial",fechaInicial);
		if (fechaFinal != null)
			query.setParameter("fechaFinal",fechaFinal);
		return query.getResultList();
	}
	
	public Collection findCarteraDetalleByFechaCarteraFinal(Long idCartera, java.sql.Date fechaInicio)throws GenericBusinessException{
		
		String queryString = "from CarteraDetalleEJB e where e.carteraId = " + idCartera + " and e.fechaCartera <= :fechaInicio";
		
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		return query.getResultList();
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCarteraDetalleByTodo(Long idEmpresa, java.sql.Date fechaFinal){
		//select cd.* from cartera c, cartera_detalle cd, tipo_documento td where c.TIPODOCUMENTO_ID = td.ID and c.TIPO = 'P' and c.SALDO > 0 and td.CODIGO = 'COM' and td.EMPRESA_ID = 1
		String queryString = "select cd from CarteraEJB c, CarteraDetalleEJB cd, TipoDocumentoEJB td where cd.carteraId = c.id and c.tipodocumentoId = td.id and td.empresaId = " + idEmpresa + "";
		if (fechaFinal != null)
			queryString += " and cd.fechaCartera <= :fechaFinal"; 
		
		Query query = manager.createQuery(queryString);
		if (fechaFinal != null)
			query.setParameter("fechaFinal",fechaFinal);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCarteraDetalleByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdAndByReferencia(String tipo, String codigoTipoDocumento, Long idEmpresa, String referencia){
		//select cd.* from cartera c, cartera_detalle cd, tipo_documento td where c.TIPODOCUMENTO_ID = td.ID and c.TIPO = 'P' and c.SALDO > 0 and c.REFERENCIA_ID = 900 and td.CODIGO = 'COM' and td.EMPRESA_ID = 1
		String queryString = "select cd from CarteraEJB c, CarteraDetalleEJB cd, TipoDocumentoEJB td where cd.carteraId = c.id and c.tipodocumentoId = td.id and c.tipo = '" + tipo + "' and c.saldo > 0.0 and cd.referencia = '" + referencia + "' and td.codigo = '" + codigoTipoDocumento + "' and td.empresaId = " + idEmpresa + "";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCarteraDetalleByTipoBySaldoByTipoDocumentoCodigoAndEmpresaId(String tipo, String codigoTipoDocumento, Long idEmpresa){
		//select cd.* from cartera c, cartera_detalle cd, tipo_documento td where c.TIPODOCUMENTO_ID = td.ID and c.TIPO = 'P' and c.SALDO > 0 and td.CODIGO = 'COM' and td.EMPRESA_ID = 1
		String queryString = "select cd from CarteraEJB c, CarteraDetalleEJB cd, TipoDocumentoEJB td where cd.carteraId = c.id and c.tipodocumentoId = td.id and c.tipo = '" + tipo + "' and c.saldo > 0.0 and td.codigo = '" + codigoTipoDocumento + "' and td.empresaId = " + idEmpresa + " and c.estado <> 'A'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCarteraDetalleByTipoBySaldoAndEmpresaId(String tipo, Long idEmpresa){
		//select cd.* from cartera c, cartera_detalle cd, tipo_documento td where c.TIPODOCUMENTO_ID = td.ID and c.TIPO = 'P' and c.SALDO > 0 and td.CODIGO = 'COM' and td.EMPRESA_ID = 1
		String queryString = "select cd from CarteraEJB c, CarteraDetalleEJB cd, TipoDocumentoEJB td where cd.carteraId = c.id and c.tipodocumentoId = td.id and c.tipo = '" + tipo + "' and c.saldo > 0.0 and td.empresaId = " + idEmpresa + " and c.estado <> 'A'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public Collection findCarteraDetalleByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdByAprobadoAndByCartera(String tipo, String codigoTipoDocumento, Long idEmpresa, String aprobado, String cartera){
		   //select c.* from cartera c, cartera_detalle cd, tipo_documento td where c.TIPODOCUMENTO_ID = td.ID and c.ID = cd.CARTERA_ID and cd.CARTERA = 'N' and c.TIPO = 'P' and c.SALDO > 0 and td.CODIGO = 'CEG' and td.EMPRESA_ID = 1
		   /*String queryString = "select c from CarteraEJB c, CarteraDetalleEJB cd, TipoDocumentoEJB td where c.tipodocumentoId = td.id and c.id = cd.carteraId and c.tipo = '" + tipo + "' and c.saldo > 0.0 and c.aprobado = '" + aprobado + "' and td.codigo = '" + codigoTipoDocumento + "' and td.empresaId = " + idEmpresa + " and cd.cartera = '" + cartera + "'";
		   Query query = manager.createQuery(queryString);*/
		   Date fechaServidor = null;
		   try {
			   fechaServidor = utilitariosLocal.getServerDateSql();
		   } catch (GenericBusinessException e) {
			   e.printStackTrace();
		   }

		   String queryString = "select distinct cd from CarteraEJB c, CarteraDetalleEJB cd, TipoDocumentoEJB td " +
		   "where c.tipodocumentoId = td.id and c.id = cd.carteraId and " +
		   "c.tipo = '" + tipo + "' and c.saldo >= 0.0 and c.aprobado = '" + aprobado + "'" +
		   " and td.codigo = '" + codigoTipoDocumento + "' and td.empresaId = " + idEmpresa +
		   " and cd.cartera = '" + cartera + "'";
		   if ( fechaServidor != null )
			   queryString += " and cd.fechaCartera <= :detalleFechaCartera";
		   
		   Query query = manager.createQuery(queryString);
		   if ( fechaServidor != null )
			   query.setParameter("detalleFechaCartera", fechaServidor);
		   
		   return query.getResultList();
	   }
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCarteraDetalleByQueryByFechaCarteraMenorFechaActual(Map aMap) throws GenericBusinessException {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from CarteraDetalleEJB " + objectName + " where "
		+ where;
		Date fechaActual = utilitariosLocal.getServerDateSql();
		if ( fechaActual !=null )
			queryString += ( " and e.fechaCartera  <= :fechaActual" );
		
		queryString += " order by e.fechaCartera asc";
		
		Query query = manager.createQuery(queryString);
		
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
			
		}
		if ( fechaActual != null )
			query.setParameter("fechaActual", fechaActual);
		
		return query.getResultList();
		
	}
	
	public Double registrarCarteraDetalleFactura(FacturaEJB factura, CarteraEJB cartera, List<DocumentoIf> modelDocumentoList, int count, Vector<FacturaDetalleIf> facturaDetalleColeccion, Double valorFacturas) throws GenericBusinessException {
		String OPCION_NO = "N";
		
		for (DocumentoIf modelDocumento : modelDocumentoList) {
			Collection facturas = facturaDetalleLocal.findFacturaDetalleByDocumentoIdAndByFacturaId(modelDocumento.getId(), factura.getPrimaryKey());
			Iterator itFacturas = facturas.iterator();
			CarteraDetalleData carteraDetalleModel = new CarteraDetalleData();
			CarteraDetalleEJB carteraDetalle = new CarteraDetalleEJB();
			boolean registrarCartera = false;
			FacturaDetalleIf facturaDetalle = null;
			while (itFacturas.hasNext()) {
				facturaDetalle = (FacturaDetalleIf) itFacturas.next();
				facturaDetalleColeccion.add(facturaDetalle);
				DocumentoIf documento = null;
				documento = documentoLocal.getDocumento(facturaDetalle.getDocumentoId());
				
				// Si el documento del detalle de la factura que leo es de bonificación para ese no genero ninguna cartera
				if (documento.getBonificacion().equals(OPCION_NO)) {
					double valorFacturaDetalle = facturaDetalle.getValor().doubleValue();
					double descuentoFacturaDetalle = facturaDetalle.getDescuento().doubleValue();
					double descuentoGlobalFacturaDetalle = facturaDetalle.getDescuentoGlobal().doubleValue();
					double descuentoTotalFacturaDetalle = descuentoFacturaDetalle + descuentoGlobalFacturaDetalle;
					double ivaFacturaDetalle = facturaDetalle.getIva().doubleValue();
					double iceFacturaDetalle = facturaDetalle.getIce().doubleValue();
					double otroImpuestoDetalle = facturaDetalle.getOtroImpuesto().doubleValue();
					double impuestoTotalFacturaDetalle = ivaFacturaDetalle + iceFacturaDetalle + otroImpuestoDetalle;
					double valorCarteraDetalle = valorFacturaDetalle - descuentoTotalFacturaDetalle + impuestoTotalFacturaDetalle;
					valorFacturas += valorCarteraDetalle;
					//valorFacturas = utilitariosLocal.redondeoValor(valorFacturas);
					carteraDetalleModel.setCarteraId(cartera.getPrimaryKey());
					registrarCartera = true;
					//carteraDetalle = registrarCarteraDetalle(carteraDetalleModel, facturaDetalle, factura, valorFacturas);
				}
			}
			if ( registrarCartera && facturaDetalle != null ){
				carteraDetalle = registrarCarteraDetalle(carteraDetalleModel, facturaDetalle, factura, valorFacturas);
			}
			// si el detalle de la cartera posee el id de la cartera quiere decir que ha salido de la iteracion
			// por lo tanto aumento en uno el secuencial con el cual se generan los detalles
			// de cartera, esto se valida porque puede darse el caso de que para determinado documento no exista ningun detalle de factura
			if (carteraDetalle.getCarteraId() != null) {
				count++;
				carteraDetalle.setSecuencial(count);
				manager.merge(carteraDetalle);
			}
		}
		return valorFacturas;
	}
	
	private CarteraDetalleEJB registrarCarteraDetalle(CarteraDetalleIf carteraDetalleModel, FacturaDetalleIf facturaDetalleModel, FacturaIf facturaModel, Double valorFacturas) {
		CarteraDetalleEJB carteraDetalle = new CarteraDetalleEJB();
		String OPCION_SI = "S";
		
		try {
			Long usuarioId = ((UsuarioIf) usuarioLocal.findUsuarioById(facturaModel.getUsuarioId()).iterator().next()).getId();
			CajaIf cajaIf = (CajaIf) cajaLocal.findCajaByUsuarioId(usuarioId).iterator().next();
			OficinaIf oficinaIf = oficinaLocal.getOficina(facturaModel.getOficinaId());
			
			if (cajaIf != null) {
				carteraDetalleModel.setPreimpreso(oficinaIf.getCodigo() + "-" + cajaIf.getCodigo() + "-" + facturaModel.getPreimpresoNumero());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		carteraDetalle.setId(carteraDetalleModel.getId());
		carteraDetalle.setCarteraId(carteraDetalleModel.getCarteraId());
		carteraDetalle.setDocumentoId(facturaDetalleModel.getDocumentoId());
		carteraDetalle.setLineaId(facturaDetalleModel.getLineaId());
		carteraDetalle.setPreimpreso(carteraDetalleModel.getPreimpreso());
		java.util.Date fechaHoy = new java.util.Date();
		carteraDetalle.setFechaCreacion(new java.sql.Date(fechaHoy.getYear(),fechaHoy.getMonth(), fechaHoy.getDate()));
		carteraDetalle.setFechaCartera(new java.sql.Date(fechaHoy.getYear(),fechaHoy.getMonth(), fechaHoy.getDate()));
		carteraDetalle.setFechaVencimiento(new java.sql.Date(facturaModel.getFechaVencimiento().getTime()));
		carteraDetalle.setFechaUltimaActualizacion(new java.sql.Date(fechaHoy.getYear(),fechaHoy.getMonth(), fechaHoy.getDate()));
		carteraDetalle.setValor(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorFacturas)));
		carteraDetalle.setSaldo(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorFacturas)));
		carteraDetalle.setCartera(OPCION_SI);
		
		return carteraDetalle;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)	
	public java.util.Collection findCarteraDetalleByReferenciaByDocumentoNullAndByCarteraNo(String referencia) throws com.spirit.exception.GenericBusinessException {
		String objectName = "cd";
		String queryString = "select distinct cd from CarteraDetalleEJB " + objectName + " where cd.referencia = '" + referencia + "' and cd.documentoId is null and cd.cartera = 'N'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)	
	public java.util.Collection findCarteraDetalleNegativos() throws com.spirit.exception.GenericBusinessException {
		//select distinct cd.* from cartera c, cartera_detalle cd, tipo_documento td where cd.CARTERA_ID = c.ID and c.TIPODOCUMENTO_ID = td.ID and td.SIGNOCARTERA = '-'
		String queryString = "select distinct cd from CarteraEJB c, CarteraDetalleEJB cd, TipoDocumentoEJB td where cd.carteraId = c.id and c.tipodocumentoId = td.id and td.signocartera = '-'";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	 public CarteraDetalleEJB registerWalletDetail(WalletDetailData walletDetailData) {
		 CarteraDetalleEJB walletDetail = new CarteraDetalleEJB();
		 //walletDetail.setId(id);
		 //walletDetail.setCarteraId(carteraId);
		 walletDetail.setDocumentoId(walletDetailData.getDocument().getId());
		 walletDetail.setSecuencial(walletDetailData.getSequentialNumber());
		 //walletDetail.setFechaCreacion(fechaCreacion);
		 //walletDetail.setFechaCartera(fechaCartera);
		 //walletDetail.setFechaVencimiento(fechaVencimiento);
		 //walletDetail.setFechaUltimaActualizacion(fechaUltimaActualizacion);
		 //walletDetail.setLineaId(lineaId);
		 //walletDetail.setFormaPagoId(formaPagoId);
		 //walletDetail.setCuentaBancariaId(cuentaBancariaId);
		 //walletDetail.setReferencia(referencia);
		 //walletDetail.setPreimpreso(preimpreso);
		 //walletDetail.setDepositoId(depositoId);
		 walletDetail.setValor(utilitariosLocal.redondeoValor(walletDetailData.getValue()));
		 walletDetail.setSaldo(utilitariosLocal.redondeoValor(walletDetailData.getBalance()));
		 //walletDetail.setCotizacion(cotizacion);
		 //walletDetail.setCartera(cartera);
		 //walletDetail.setAutorizacion(autorizacion);
		 //walletDetail.setSriSustentoTributarioId(sriSustentoTributarioId);
		 //walletDetail.setDiferido(diferido);
		 walletDetail.setObservacion(walletDetailData.getComment());
		 //walletDetail.setSriClienteRetencionId(sriClienteRetencionId);
		 walletDetail.setChequeBancoId(walletDetailData.getCheckBank()!=null?walletDetailData.getCheckBank().getId():null);
		 walletDetail.setChequeCuentaBancariaId(walletDetailData.getCheckAccount()!=null?walletDetailData.getCheckAccount().getId():null);
		 walletDetail.setChequeNumero(walletDetailData.getCheckNumber());
		 walletDetail.setDepositoBancoId(walletDetailData.getDepositBank()!=null?walletDetailData.getDepositBank().getId():null);
		 walletDetail.setDepositoCuentaBancariaId(walletDetailData.getDepositAccount()!=null?walletDetailData.getDepositAccount().getId():null);
		 walletDetail.setRetencionNumero(walletDetailData.getRetentionNumber());
		 walletDetail.setRetencionAutorizacion(walletDetailData.getRetentionAuthorization());
		 walletDetail.setValorRetencionRenta((walletDetailData.getDocument().getRetencionRenta().equals(SpiritConstants.getOptionYes().substring(0,1)))?walletDetailData.getRetentionPercentage():SpiritConstants.getZeroValue());
		 walletDetail.setValorRetencionIva((walletDetailData.getDocument().getRetencionIva().equals(SpiritConstants.getOptionYes().substring(0,1)))?walletDetailData.getRetentionPercentage():SpiritConstants.getZeroValue());
		 if (walletDetailData.getSriRetentionPercentageDefinition() != SpiritConstants.getNullValue())
			 walletDetail.setSriClienteRetencionId(walletDetailData.getSriRetentionPercentageDefinition().getId());
		 walletDetail.setDebitoBancoId(walletDetailData.getDebitBank()!=null?walletDetailData.getDebitBank().getId():null);
		 walletDetail.setDebitoCuentaBancariaId(walletDetailData.getDebitAccount()!=null?walletDetailData.getDebitAccount().getId():null);
		 walletDetail.setDebitoReferencia(walletDetailData.getDebitReference());
		 walletDetail.setTransferenciaBancoOrigenId(walletDetailData.getSourceBank()!=null?walletDetailData.getSourceBank().getId():null);
		 walletDetail.setTransferenciaCuentaOrigenId(walletDetailData.getSourceAccount()!=null?walletDetailData.getSourceAccount().getId():null);
		 walletDetail.setTransferenciaBancoDestinoId(walletDetailData.getTargetBank()!=null?walletDetailData.getTargetBank().getId():null);
		 walletDetail.setTransferenciaCuentaDestinoId(walletDetailData.getTargetAccount()!=null?walletDetailData.getTargetAccount().getId():null);
		 walletDetail.setTarjetaCreditoBancoId(walletDetailData.getCreditCardBank()!=null?walletDetailData.getCreditCardBank().getId():null);
		 walletDetail.setTarjetaCreditoId(walletDetailData.getCreditCard()!=null?walletDetailData.getCreditCard().getId():null);
		 walletDetail.setVoucherReferencia(walletDetailData.getVoucherReference());
		 walletDetail.setPagoElectronicoReferencia(walletDetailData.getElectronicPaymentReference());
		 return walletDetail;
	 }
	 
	@SuppressWarnings("rawtypes")
	public java.util.Collection findWalletReceiptRowDataByWalletId(Long walletId) throws com.spirit.exception.GenericBusinessException {
		 //select distinct *, sum(ca.VALOR) from CARTERA_DETALLE cd, CARTERA_AFECTA ca, CARTERA_DETALLE cardet where cd.CARTERA_ID = 4660 and cd.ID = ca.CARTERADETALLE_ID and cardet.ID = ca.CARTERAAFECTA_ID group by cd.ID, ca.CARTERAAFECTA_ID
		 String select = "select distinct cd, ca, det, sum(ca.valor)";
		 String from = "from CarteraDetalleEJB cd, CarteraAfectaEJB ca, CarteraDetalleEJB det";
		 String where = "where cd.carteraId = :walletId and cd.id = ca.carteradetalleId and det.id = ca.carteraafectaId";
		 String groupBy = "group by cd.id, ca.carteraafectaId order by ca.fechaAplicacion, ca.id";
		 String queryString = select + SpiritConstants.getBlankSpaceCharacter() + from + SpiritConstants.getBlankSpaceCharacter() + where + SpiritConstants.getBlankSpaceCharacter() + groupBy;
		 Query query = manager.createQuery(queryString);
		 query.setParameter("walletId", walletId);
		 return query.getResultList();
	 }
}
