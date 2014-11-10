package com.spirit.pos.session;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.ParametroEmpresaSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.general.webservice.consumer.SpiritWebServiceConsumerLocal;
import com.spirit.inventario.entity.GiftcardIf;
import com.spirit.pos.entity.TarjetaIf;
import com.spirit.pos.entity.TarjetaTipoIf;
import com.spirit.pos.session.generated._TarjetaSession;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class TarjetaSessionEJB extends _TarjetaSession implements TarjetaSessionRemote,TarjetaSessionLocal  {
	
	@EJB private SpiritWebServiceConsumerLocal swsLocal;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	@EJB private ParametroEmpresaSessionLocal parametroEmpresaSessionLocal;
	@EJB private TarjetaSessionLocal tarjetaSessionLocal;
	@EJB private TarjetaTipoSessionLocal tarjetaTipoSessionLocal;
	@EJB private UtilitariosSessionLocal utilitariosSessionLocal;
	@EJB private EmpresaSessionLocal empresaSessionLocal;
	@EJB private SpiritWebServiceConsumerLocal swsl;
	
	public TarjetaIf findTarjetaWebService(String identificacion,String codigoOficina,Long empresaId) throws GenericBusinessException {
		
		//return ((TarjetaIf) swsl.findTarjetaByidentificacionClienteByCodigoOficinaByEmpresaId(identificacion, codigoOficina, empresaId));
		return null;
	}
	
	
	public Collection findTarjetaByIdentificacionByCodigoOficinaByEmpresaId(String identificacion,String codigoOficina,Long empresaId ) throws GenericBusinessException{
		try{
			String queryString = " select distinct ta " +
					" from TarjetaEJB ta,ClienteOficinaEJB co,ClienteEJB cl, TipoClienteEJB tc " +
					" where ta.clienteoficinaId = co.id and co.clienteId = cl.id and cl.tipoclienteId = tc.id " +
					" and cl.identificacion = :identificacion and co.codigo = :codigo and tc.empresaId = :empresaId ";
			
			Query query = manager.createQuery(queryString);
			query.setParameter("identificacion", identificacion);
			query.setParameter("codigo", codigoOficina);
			query.setParameter("empresaId", empresaId);
			return query.getResultList();
		} catch(Exception e){
				e.printStackTrace();
				throw new GenericBusinessException("Error en consulta de Tarjeta de Afiliación !!");
		}
	}
	
	public void registrarTarjetaList(List<TarjetaIf> tarjetaAfiliacionList) throws GenericBusinessException {
		Iterator it = tarjetaAfiliacionList.iterator();
		while (it.hasNext()) {
			TarjetaIf tarjeta = (TarjetaIf) it.next();
			tarjeta.setFechaEmision(utilitariosSessionLocal.getServerDateTimeStamp().getTime());
			tarjeta.setFechaUltimoCambioStatus(utilitariosSessionLocal.getServerDateTimeStamp().getTime());
			System.out.println(tarjeta.getCodigo());
			addTarjeta(tarjeta);
		}
	}

	public void procesarActualizacionStatusTarjetas() throws GenericBusinessException {
		boolean servicioTarjetasAfiliacionActivado = false;
		Iterator empresaIterator = empresaSessionLocal.getEmpresaList().iterator();
		while (empresaIterator.hasNext()) {
			long empresaId = ((EmpresaIf) empresaIterator.next()).getId();
			Map tiposTarjetaMap = mapearTiposTarjeta(empresaId);
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", empresaId);
			parameterMap.put("codigo", "STAA");
			Iterator it = parametroEmpresaSessionLocal.findParametroEmpresaByQuery(parameterMap).iterator();
			if (it.hasNext()) {
				servicioTarjetasAfiliacionActivado = (((ParametroEmpresaIf) it.next()).getValor()).equals("S")?true:false;
				if (servicioTarjetasAfiliacionActivado) {
					parameterMap.put("codigo", "APD");
					it = parametroEmpresaSessionLocal.findParametroEmpresaByQuery(parameterMap).iterator();
					if (it.hasNext()) {
						String apd = ((ParametroEmpresaIf) it.next()).getValor();
						parameterMap.clear();
						parameterMap.put("empresaId", empresaId);
						parameterMap.put("estado", "A");
						it = tarjetaSessionLocal.findTarjetaByQuery(parameterMap).iterator();
						while (it.hasNext()) {
							TarjetaIf tarjeta = (TarjetaIf) it.next();
							TarjetaTipoIf tipoTarjeta = (TarjetaTipoIf) tiposTarjetaMap.get(tarjeta.getTipoId());
							if (apd.equals("D")) {
								if (tarjeta.getDineroAcumuladoStatus().doubleValue() >= tipoTarjeta.getDineroSubirStatus().doubleValue()) {
									if (tipoTarjeta.getStatusSiguiente() != null) {
										tarjeta.setTipoId(tipoTarjeta.getStatusSiguiente());
										tarjeta.setDineroAcumuladoStatus(BigDecimal.ZERO);
										tarjeta.setFechaUltimoCambioStatus(utilitariosSessionLocal.getServerDateTimeStamp().getTime());
									} else if (!validarMantenerStatus(tarjeta, tipoTarjeta) && tarjeta.getDineroAcumuladoStatus().doubleValue() >= tipoTarjeta.getDineroMantenerStatus().doubleValue()) {
										tarjeta.setDineroAcumuladoStatus(BigDecimal.ZERO);
										tarjeta.setFechaUltimoCambioStatus(utilitariosSessionLocal.getServerDateTimeStamp().getTime());
									}
									tarjeta.setPrimaryKey(tarjeta.getId());
									tarjetaSessionLocal.saveTarjeta(tarjeta);
								} else if (!validarMantenerStatus(tarjeta, tipoTarjeta) && tarjeta.getDineroAcumuladoStatus().doubleValue() < tipoTarjeta.getDineroMantenerStatus().doubleValue()) {
									tarjeta.setTipoId((tipoTarjeta.getStatusAnterior()!=null)?tipoTarjeta.getStatusAnterior():tarjeta.getTipoId());
									tarjeta.setDineroAcumuladoStatus(BigDecimal.ZERO);
									tarjeta.setFechaUltimoCambioStatus(utilitariosSessionLocal.getServerDateTimeStamp().getTime());
									tarjeta.setPrimaryKey(tarjeta.getId());
									tarjetaSessionLocal.saveTarjeta(tarjeta);
								} else if (!validarMantenerStatus(tarjeta, tipoTarjeta)) {
									tarjeta.setDineroAcumuladoStatus(BigDecimal.ZERO);
									tarjeta.setFechaUltimoCambioStatus(utilitariosSessionLocal.getServerDateTimeStamp().getTime());
									tarjeta.setPrimaryKey(tarjeta.getId());
									tarjetaSessionLocal.saveTarjeta(tarjeta);
								}
							} else if (apd.equals("P")) {
								if (tarjeta.getPuntosAcumulados().doubleValue() >= tipoTarjeta.getPuntosSubirStatus().doubleValue()) {
									if (tipoTarjeta.getStatusSiguiente() != null) {
										tarjeta.setTipoId((tipoTarjeta.getStatusSiguiente()!=null)?tipoTarjeta.getStatusSiguiente():tarjeta.getTipoId());
										tarjeta.setPuntosAcumuladosStatus(BigDecimal.ZERO);
										tarjeta.setFechaUltimoCambioStatus(utilitariosSessionLocal.getServerDateTimeStamp().getTime());
									} else if (!validarMantenerStatus(tarjeta, tipoTarjeta)) {
										tarjeta.setPuntosAcumuladosStatus(BigDecimal.ZERO);
										tarjeta.setFechaUltimoCambioStatus(utilitariosSessionLocal.getServerDateTimeStamp().getTime());
									}
									tarjeta.setPrimaryKey(tarjeta.getId());
									tarjetaSessionLocal.saveTarjeta(tarjeta);
								} else if (!validarMantenerStatus(tarjeta, tipoTarjeta) && tarjeta.getPuntosAcumuladosStatus().doubleValue() < tipoTarjeta.getPuntosMantenerStatus().doubleValue()) {
									tarjeta.setTipoId((tipoTarjeta.getStatusAnterior()!=null)?tipoTarjeta.getStatusAnterior():tarjeta.getTipoId());
									tarjeta.setPuntosAcumuladosStatus(BigDecimal.ZERO);
									tarjeta.setFechaUltimoCambioStatus(utilitariosSessionLocal.getServerDateTimeStamp().getTime());
									tarjeta.setPrimaryKey(tarjeta.getId());
									tarjetaSessionLocal.saveTarjeta(tarjeta);
								} else if (!validarMantenerStatus(tarjeta, tipoTarjeta) && tarjeta.getPuntosAcumuladosStatus().doubleValue() >= tipoTarjeta.getPuntosMantenerStatus().doubleValue()) {
									tarjeta.setPuntosAcumuladosStatus(BigDecimal.ZERO);
									tarjeta.setFechaUltimoCambioStatus(utilitariosSessionLocal.getServerDateTimeStamp().getTime());
									tarjeta.setPrimaryKey(tarjeta.getId());
									tarjetaSessionLocal.saveTarjeta(tarjeta);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public Map mapearTiposTarjeta(Long empresaId) {
		Map tiposTarjetaMap = new HashMap();
		try {
			Iterator it = tarjetaTipoSessionLocal.findTarjetaTipoByEmpresaId(empresaId).iterator();
			while (it.hasNext()) {
				TarjetaTipoIf tipoTarjeta = (TarjetaTipoIf) it.next();
				tiposTarjetaMap.put(tipoTarjeta.getId(), tipoTarjeta);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}

		return tiposTarjetaMap;
	}
	
	public boolean validarMantenerStatus(TarjetaIf tarjeta, TarjetaTipoIf tipoTarjeta) {
		try {
			long diferencia = utilitariosSessionLocal.obtenerDiferenciaDias(new java.sql.Date(tarjeta.getFechaUltimoCambioStatus()), utilitariosSessionLocal.getServerDateSql());
			System.out.println("DIFERENCIA DÍAS: >>>>> " + diferencia);
			if (diferencia > 365L) {
				return false;
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public java.util.Collection findTarjetaByEmpresaIdByCodigoBarrasAndEstadoWebService(Long empresaId,String codigoBarras,String estado) throws GenericBusinessException{
		
		return swsLocal.consultarWSTarjetaByQuery(empresaId, codigoBarras,estado);
		//return null;
		
	}
	
	public TarjetaIf findTarjetaByIdWebService(Long empresaId, Long id) throws GenericBusinessException{
		
		return swsLocal.consultarWSTarjetaById(empresaId, id);
		//return null;
		
	}
}
