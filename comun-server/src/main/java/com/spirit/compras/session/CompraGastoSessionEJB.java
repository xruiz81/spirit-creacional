package com.spirit.compras.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.compras.entity.CompraAsociadaGastoIf;
import com.spirit.compras.entity.CompraDetalleGastoIf;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraGastoIf;
import com.spirit.compras.gastos.CompraAsociadaGastoClase;
import com.spirit.compras.gastos.CompraDetalleGastoClase;
import com.spirit.compras.gastos.CompraGastoClase;
import com.spirit.compras.session.generated._CompraGastoSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * The <code>CompraGastoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class CompraGastoSessionEJB extends _CompraGastoSession implements CompraGastoSessionRemote,CompraGastoSessionLocal  {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	private @EJB CompraDetalleGastoSessionLocal compraDetalleGastoLocal;
	
	private @EJB CompraAsociadaGastoSessionLocal compraAsociadaGastoLocal;

	@Resource private SessionContext ctx;

	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 * @throws GenericBusinessException 
	 *******************************************************************************************************************/

	public CompraGastoClase procesarCompraGasto( CompraGastoClase compraGastoClase ) throws GenericBusinessException{
		try{
			CompraGastoClase compraGastoClaseGuardada = new CompraGastoClase();
			eliminarCompraGasto(compraGastoClase);

			Long compraId = compraGastoClase.getCompraId();
			Map<Long, CompraGastoIf> mapaGasto = compraGastoClase.getMapaComprasGastos();
			for ( Long gastoId : mapaGasto.keySet() ){
				CompraGastoIf cg = mapaGasto.get(gastoId);
				if (cg.getValor().doubleValue() > 0D ){
					if ( cg.getCompraId() == null )
						cg.setCompraId(compraId);
					if (cg.getId() == null){
						cg = addCompraGasto(cg);

					} else {
						saveCompraGasto(cg);
					}

					//guardo en mapa nuevo para devolver
					compraGastoClaseGuardada.getMapaComprasGastos().put(gastoId, cg);
					//---------------------------------------------------------------

					Map<Long, CompraDetalleGastoClase> mapaDetalles = compraGastoClase.getMapaCompraDetalleGasto();
					//------
					CompraDetalleGastoClase cdgcg = new CompraDetalleGastoClase(cg);
					compraGastoClaseGuardada.getMapaCompraDetalleGasto().put(gastoId, cdgcg);
					//------

					CompraDetalleGastoClase cdgc = mapaDetalles.get(gastoId);
					Map<CompraDetalleIf, CompraDetalleGastoIf> mapaCompraDetalleGasto = cdgc.getDetalle();
					for( CompraDetalleIf cd : mapaCompraDetalleGasto.keySet() ){
						CompraDetalleGastoIf cdg = mapaCompraDetalleGasto.get(cd);
						if ( cdg.getValor().doubleValue() > 0D ){
							if (cdg.getId() == null){
								cdg.setCompraGastoId(cg.getId());
								cdg = compraDetalleGastoLocal.addCompraDetalleGasto(cdg);
							} else {
								compraDetalleGastoLocal.saveCompraDetalleGasto(cdg);
							}
							cdgcg.getDetalle().put(cd, cdg);
						}
					}

					Map<Long, CompraAsociadaGastoClase> mapaCompraAsociada = compraGastoClase.getMapaComprasAsociadas();
					//------
					CompraAsociadaGastoClase cagcg = new CompraAsociadaGastoClase();
					compraGastoClaseGuardada.getMapaComprasAsociadas().put(gastoId, cagcg);
					//------
					
					CompraAsociadaGastoClase cagc = mapaCompraAsociada.get(gastoId);
					if (cagc != null) {
						ArrayList<CompraAsociadaGastoIf> comprasAsociadas = cagc.getDetalle();
						for ( CompraAsociadaGastoIf cag : comprasAsociadas ){
							if ( cag.getId() == null ){
								cag.setCompraGastoId(cg.getId());
								cag = compraAsociadaGastoLocal.addCompraAsociadaGasto(cag);
							} else {
								compraAsociadaGastoLocal.saveCompraAsociadaGasto(cag);
							}
							cagcg.getDetalle().add(cag);
						}
					}
				}
			}
			return compraGastoClaseGuardada;
		} catch(Exception e){
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al guardar Gasto(s) de la compra !!");
		}

	}

	private void eliminarCompraGasto(CompraGastoClase compraGastoClase) throws GenericBusinessException {

		try{

			ArrayList<CompraDetalleGastoIf> listaCompraDetalleGasto = compraGastoClase.getListaEliminacionComprasDetalleGastos();
			for ( CompraDetalleGastoIf cgd : listaCompraDetalleGasto ){
				compraDetalleGastoLocal.deleteCompraDetalleGasto(cgd.getId());
			}
			
			ArrayList<CompraAsociadaGastoIf> listaComprasAsociadas = compraGastoClase.getListaEliminacionComprassAsociadas();
			for (CompraAsociadaGastoIf cag : listaComprasAsociadas){
				compraAsociadaGastoLocal.deleteCompraAsociadaGasto(cag.getId());
			}

			ArrayList<CompraGastoIf> listaCompraGasto = compraGastoClase.getListaEliminacionComprasGastos();
			for ( CompraGastoIf cg : listaCompraGasto ){
				deleteCompraGasto(cg.getId());
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error en la eliminacion de Gastos !!");
		}

	}

	/*public void actualizarCompraGasto( CompraGastoClase compraGastoClase ) throws GenericBusinessException{
		try{
			Long compraId = compraGastoClase.getCompraId();
			Map<Long, CompraGastoIf> mapaGasto = compraGastoClase.getMapaComprasGastos();
			for ( Long gastoId : mapaGasto.keySet() ){
				CompraGastoIf cg = mapaGasto.get(gastoId);
				if (cg.getValor().doubleValue() > 0D ){
					cg.setCompraId(compraId);
					saveCompraGasto(cg);
					Map<Long, CompraDetalleGastoClase> mapaDetalles = compraGastoClase.getMapaCompraDetalleGasto();
					for ( Long gastoDetalleId : mapaDetalles.keySet() ){
						CompraDetalleGastoClase cdgc = mapaDetalles.get(gastoDetalleId);
						Map<CompraDetalleIf, CompraDetalleGastoIf> mapaCompraDetalleGasto = cdgc.getDetalle();
						for( CompraDetalleIf cd : mapaCompraDetalleGasto.keySet() ){
							CompraDetalleGastoIf cdg = mapaCompraDetalleGasto.get(cd);
							cdg.setCompraGastoId(cg.getPrimaryKey());
							if ( cdg.getValor().doubleValue() > 0D )
								compraDetalleGastoLocal.saveCompraDetalleGasto(cdg);
						}
					}
				}
			}
		} catch(Exception e){
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al guardar Gasto(s) de la compra !!");
		}

	}*/

	public CompraGastoClase getMapaGastoMapaDetalleGasto(Long compraId) throws GenericBusinessException{
		try {

			CompraGastoClase cgc = new CompraGastoClase();

			if (compraId==null)
				throw new GenericBusinessException("No existe Id de Compra !!");

			cgc.setCompraId(compraId);
			Map<Long,CompraGastoIf> mapaCompraGasto = cgc.getMapaComprasGastos(); 
			Map<Long,CompraDetalleGastoClase> mapaCompraDetalleGasto =  cgc.getMapaCompraDetalleGasto();
			Map<Long, CompraAsociadaGastoClase> mapaCompraAsociadaGasto = cgc.getMapaComprasAsociadas();
			
			Collection<CompraGastoIf> comprasGastos = findCompraGastoByCompraId(compraId);
			for ( CompraGastoIf cg : comprasGastos ){
				mapaCompraGasto.put(cg.getGastoId(),cg);

				Map<String, Object> mapaBusqueda =  new HashMap<String, Object>();
				mapaBusqueda.put("compraGastoId", cg.getId());
				Collection<Object[]> cdgs = compraDetalleGastoLocal.findCompraDetalleGastoCompraDetalleByQuery(mapaBusqueda);

				CompraDetalleGastoClase cdgc = new CompraDetalleGastoClase(cg);
				for ( Object[] o : cdgs ){
					CompraDetalleIf cd = (CompraDetalleIf) o[0];
					CompraDetalleGastoIf cdg = (CompraDetalleGastoIf) o[1];
					cdgc.getDetalle().put(cd,cdg);
				}
				mapaCompraDetalleGasto.put(cg.getGastoId(), cdgc);
				
				ArrayList<CompraAsociadaGastoIf> comprasAsociadas = (ArrayList<CompraAsociadaGastoIf>) 
					compraAsociadaGastoLocal.findCompraAsociadaGastoByCompraIdDeGasto(cg);
				CompraAsociadaGastoClase cagc = new CompraAsociadaGastoClase();
				cagc.setDetalle(comprasAsociadas);
				mapaCompraAsociadaGasto.put(cg.getGastoId(), cagc);

			}

			return cgc;

		} catch (Exception e) {
			throw new GenericBusinessException("Error en la consula de Gastos y detalle de Gastos !!");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findGastoByCompraId(Long idCompra) throws GenericBusinessException {
		//select distinct d.CODIGO, cdg.* from COMPRA_DETALLE_GASTO cdg, COMPRA_GASTO cg, COMPRA_DETALLE cd, DOCUMENTO d where cdg.COMPRA_GASTO_ID = cg.ID and cg.COMPRA_ID = 80 and cdg.COMPRA_DETALLE_ID = cd.ID and cd.DOCUMENTO_ID = d.ID
		String queryString = "select distinct cdg, d, cd from CompraDetalleGastoEJB cdg, CompraGastoEJB cg, CompraDetalleEJB cd, DocumentoEJB d where cdg.compraGastoId = cg.id and cg.compraId = " + idCompra + " and cdg.compraDetalleId = cd.id and cd.documentoId = d.id";
		Query query = manager.createQuery(queryString);
		return query.getResultList();  
	}

}
