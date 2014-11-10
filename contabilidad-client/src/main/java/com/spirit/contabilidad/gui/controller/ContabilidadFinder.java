package com.spirit.contabilidad.gui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.handler.EstadoPeriodo;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
/**
 * 
 * @author lmunoz
 * 
 * Permite encontrar objetos con cache del modulo de contabilidad
 *
 */
public class ContabilidadFinder {
	
	private static Logger log = LogService.getLogger(ContabilidadFinder.class);
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);

	public static List findPlanCuentaActivo(Long idEmpresa) {
		List plancuenta;
		plancuenta = SpiritCache.findObject("plancuenta");
	
		if (plancuenta == null) {
			try {
				Map aMap = new HashMap();
				
					aMap.put("empresaId", idEmpresa);
					aMap.put("estado", ESTADO_ACTIVO);
				
				plancuenta = (List) SessionServiceLocator
						.getPlanCuentaSessionService().findPlanCuentaByQuery(aMap);
				SpiritCache.setObject("plancuenta", plancuenta);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return plancuenta;
		}
		return plancuenta;
	}
	
	public static List findPeriodo(Long idEmpresa) {
		List periodo;
		periodo = SpiritCache.findObject("periodo");
	
		if (periodo == null) {
			try {
				periodo = (List) SessionServiceLocator.getPeriodoSessionService().findPeriodoByEmpresaId(idEmpresa); 
				SpiritCache.setObject("periodo", periodo);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return periodo;
		}
		return periodo;
	}
	
	public static List findPeriodosActivos(Long idEmpresa) {
		List periodos;
		periodos = SpiritCache.findObject("periodosActivos");
	
		if (periodos == null) {
			try {
				Map aMap = new HashMap();
				
					aMap.put("empresaId", idEmpresa);
					//aMap.put("status", PeriodoModel.ESTADO_ACTIVO);
					aMap.put("status", EstadoPeriodo.ACTIVO.getLetra());
				
				periodos = (List) SessionServiceLocator
						.getPeriodoSessionService().findPeriodoByQuery(aMap);
				SpiritCache.setObject("periodosActivos", periodos);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return periodos;
		}
		return periodos;
	}
	
	/*public static List findEventoContableByEmpresaIdAndByOficinaId(Long idEmpresa, Long idOficina) {
		List eventosContables;
		eventosContables = SpiritCache.findObject("eventosContables");
	
		if (eventosContables == null) {
			try {
				Map aMap = new HashMap();
				
					aMap.put("empresaId", idEmpresa);
					aMap.put("oficinaId", idOficina);
				
				eventosContables = (List) EventoContableModel.getEventoContableSessionService().findEventoContableByQuery(aMap);
				SpiritCache.setObject("eventosContables", eventosContables);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return eventosContables;
		}
		return eventosContables;
	}*/
	
	public static List findEventoContableByEmpresaId(Long idEmpresa) {
		List eventosContables;
		eventosContables = SpiritCache.findObject("eventosContables");
	
		if (eventosContables == null) {
			try {
				Map aMap = new HashMap();
				aMap.put("empresaId", idEmpresa);
				eventosContables = (List) SessionServiceLocator.getEventoContableSessionService().findEventoContableByQuery(aMap);
				SpiritCache.setObject("eventosContables", eventosContables);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return eventosContables;
		}
		return eventosContables;
	}

	public static List findTipoAsientoByEmpresa(Long idEmpresa) {
		List tipoAsientoByEmpresa;
		//Adiciono idEmpresa en el nombre de cache para que pueda soportar multiples empresas.
		tipoAsientoByEmpresa = SpiritCache.findObject("tipoAsiento" + idEmpresa);
	
		if (tipoAsientoByEmpresa == null) {
			try {
				tipoAsientoByEmpresa = (List) SessionServiceLocator.getTipoAsientoSessionService().findTipoAsientoByEmpresaId(idEmpresa);
				SpiritCache.setObject("tipoAsientoByEmpresa" + idEmpresa, tipoAsientoByEmpresa);
				
								
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return tipoAsientoByEmpresa;
		}
		return tipoAsientoByEmpresa;
	}

	public static List findSubtipoAsientoByTipoAsiento(Long codigoTipoAsiento) {
		List subtipoAsientoByTipoAsiento;
		//Adiciono idEmpresa en el nombre de cache para que pueda soportar multiples empresas.
		subtipoAsientoByTipoAsiento = SpiritCache.findObject("subtipoAsientoByTipoAsiento");
	
		if (subtipoAsientoByTipoAsiento == null) {
			try {
				subtipoAsientoByTipoAsiento = (List) SessionServiceLocator.getSubTipoAsientoSessionService().findSubtipoAsientoByTipoId(codigoTipoAsiento);
				SpiritCache.setObject("subtipoAsientoByTipoAsiento" , subtipoAsientoByTipoAsiento);
				
								
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return subtipoAsientoByTipoAsiento;
		}
		return subtipoAsientoByTipoAsiento;
	}

	public static List findTipoCuenta() {
		List tipocuenta;
		tipocuenta = SpiritCache.findObject("tipocuenta");
	
		if (tipocuenta == null) {
			try {
				tipocuenta = (List) SessionServiceLocator.getTipoCuentaSessionService().getTipoCuentaList();
				SpiritCache.setObject("tipocuenta", tipocuenta);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return tipocuenta;
		}
		return tipocuenta;
	}

	public static List findTipoResultado() {
		List tiporesultado;
		tiporesultado = SpiritCache.findObject("tiporesultado");
	
		if (tiporesultado == null) {
			try {
				tiporesultado = (List) SessionServiceLocator.getTipoResultadoSessionService().getTipoResultadoList();
				SpiritCache.setObject("tiporesultado", tiporesultado);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return tiporesultado;
		}
		return tiporesultado;
	}

	public static List findCuenta() {
		List cuenta;
		cuenta = SpiritCache.findObject("cuenta");
	
		if (cuenta == null) {
			try {
				cuenta = (List) SessionServiceLocator.getCuentaSessionService().getCuentaList();
				SpiritCache.setObject("cuenta", cuenta);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return cuenta;
		}
		return cuenta;
	}

	public static List findCuenta(String imputable) {
		List cuenta;
		cuenta = SpiritCache.findObject("cuenta");
	
		if (cuenta == null) {
			try {
				cuenta = (List) SessionServiceLocator.getCuentaSessionService().findCuentaByImputable(imputable);
				SpiritCache.setObject("cuenta", cuenta);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return cuenta;
		}
		return cuenta;
	}

	public static List findTipoValor() {
		List tipovalor;
		tipovalor = SpiritCache.findObject("tipovalor");
	
		if (tipovalor == null) {
			try {
				tipovalor = (List) SessionServiceLocator.getTipoValorSessionService().getTipoValorList();		
				SpiritCache.setObject("tipovalor", tipovalor);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return tipovalor;
		}
		return tipovalor;
	}

	public static List findEvento(Long idEmpresa) {
		List eventocontable;
		eventocontable = SpiritCache.findObject("eventocontable");
	
		if (eventocontable == null) {
			try {
				eventocontable = (List) SessionServiceLocator.getEventoContableSessionService().findEventoContableByEmpresaId(idEmpresa);
				SpiritCache.setObject("eventocontable", eventocontable);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return eventocontable;
		}
		return eventocontable;
	}
}
