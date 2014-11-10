package com.spirit.general.gui.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.model.BancoModel;
import com.spirit.general.gui.model.CajaModel;
import com.spirit.general.gui.model.CentroGastoModel;
import com.spirit.general.gui.model.CiudadModel;
import com.spirit.general.gui.model.DepartamentoModel;
import com.spirit.general.gui.model.EmpleadoModel;
import com.spirit.general.gui.model.EmpresaModel;
import com.spirit.general.gui.model.LineaModel;
import com.spirit.general.gui.model.ModuloModel;
import com.spirit.general.gui.model.MonedaModel;
import com.spirit.general.gui.model.NoticiaModel;
import com.spirit.general.gui.model.OficinaModel;
import com.spirit.general.gui.model.OrigenDocumentoModel;
import com.spirit.general.gui.model.PaisModel;
import com.spirit.general.gui.model.ParametroEmpresaModel;
import com.spirit.general.gui.model.ProvinciaModel;
import com.spirit.general.gui.model.TipoArchivoModel;
import com.spirit.general.gui.model.TipoEmpleadoModel;
import com.spirit.general.gui.model.TipoIdentificacionModel;
import com.spirit.general.gui.model.TipoParametroModel;
import com.spirit.general.gui.model.TipoProveedorModel;
import com.spirit.server.LogService;
import com.spirit.server.Logger;


/**
 * @author lmunoz Clase que permite buscar catalogos, los cuales son muy
 *         utilizados en las busquedas, pero varian con poca frecuencia.
 */
public class GeneralFinder {

	private static Logger log = LogService.getLogger(GeneralFinder.class);

	public static List findPais() {
		List pais;
		pais = SpiritCache.findObject("pais");

		if (pais == null) {
			try {
				log.debug("Entro a buscar paises");
				pais = (List) SessionServiceLocator.getPaisSessionService().getPaisList();
				SpiritCache.setObject("pais", pais);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro paises en cache");
			return pais;
		}
		return pais;
	}
	
	public static List findOrigenDocumento() {
		List origenDocumento;
		origenDocumento = SpiritCache.findObject("origenDocumento");

		if (origenDocumento == null) {
			try {
				log.debug("Entro a buscar Origen Documento");
				origenDocumento = (List) SessionServiceLocator.getOrigenDocumentoSessionService().findOrigenDocumentoByEmpresaId(Parametros.getIdEmpresa());
				SpiritCache.setObject("origenDocumento", origenDocumento);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro Origen Documento en cache");
			return origenDocumento;
		}
		return origenDocumento;
	}
	
	public static List findNoticias() {
		List noticias;
		noticias = SpiritCache.findObject("noticias");

		if (noticias == null) {
			try {
				log.debug("Entro a buscar noticias");
				noticias = (List) SessionServiceLocator.getNoticiasSessionService().findNoticiasByEmpresaId(Parametros.getIdEmpresa());
				SpiritCache.setObject("noticias", noticias);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro noticias en cache");
			return noticias;
		}
		return noticias;
	}
	
	public static List findBanco() {
		List banco;
		banco = SpiritCache.findObject("banco");

		if (banco == null) {
			try {
				log.debug("Entro a buscar banco");
				banco = (List) BancoModel.getBancoSessionService().getBancoList();
				SpiritCache.setObject("banco", banco);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro banco en cache");
			return banco;
		}
		return banco;
	}
	
	public static List findCaja() {
		List caja;
		caja = SpiritCache.findObject("caja");

		if (caja == null) {
			try {
				log.debug("Entro a buscar caja");
				caja = (List) SessionServiceLocator.getCajaSessionService().findCajaByEmpresaId(Parametros.getIdEmpresa());
				SpiritCache.setObject("caja", caja);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro caja en cache");
			return caja;
		}
		return caja;
	}
	
	public static List findEmpresas() {
		List empresas;
		empresas = SpiritCache.findObject("empresas");

		if (empresas == null) {
			try {
				log.debug("Entro a buscar empresas");
				empresas = (List) SessionServiceLocator.getEmpresaSessionService().getEmpresaList();
				SpiritCache.setObject("empresas", empresas);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro empresas en cache");
			return empresas;
		}
		return empresas;
	}

	public static List findProvincia() {
		List provincia;
		provincia = SpiritCache.findObject("provincia");

		if (provincia == null) {
			try {
				log.debug("Entro a buscar provincias");
				provincia = (List) SessionServiceLocator.getProvinciaSessionService()
						.getProvinciaList();
				SpiritCache.setObject("provincia", provincia);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro provincias en cache");
			return provincia;
		}
		return provincia;
	}

	public static List findProvinciasByPais(Long idPais) {
		List provinciasByPais;
		provinciasByPais = SpiritCache.findObject("provinciasByPais");

		if (provinciasByPais == null) {
			try {
				log.debug("Entro a buscar provincias");
				provinciasByPais = (List) SessionServiceLocator.getProvinciaSessionService()
						.findProvinciaByPaisId(idPais);
				SpiritCache.setObject("provincias", provinciasByPais);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro provinciasByPais en cache");
			return provinciasByPais;
		}
		return provinciasByPais;
	}
	
	public static List findOficinasByEmpresa(Long idEmpresa) {
		List oficinasByEmpresas = new ArrayList();
		oficinasByEmpresas = SpiritCache.findObject("oficinasByEmpresas");

		if (oficinasByEmpresas == null) {
			try {
				log.debug("Entro a buscar oficinas");
				oficinasByEmpresas = (List) SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(idEmpresa);
				SpiritCache.setObject("oficinas", oficinasByEmpresas);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro oficinasByEmpresas en cache");
			return oficinasByEmpresas;
		}
		return oficinasByEmpresas;
	}
	
	/*
	public static List findPlanMedioProveedorByEmpresa(Long idEmpresa) {
		List planMedioProveedorByEmpresas;
		planMedioProveedorByEmpresas = SpiritCache.findObject("planMedioProveedorByEmpresas");

		if (planMedioProveedorByEmpresas == null) {
			try {
				log.debug("Entro a buscar PlanMedioProveedor");
				planMedioProveedorByEmpresas = (List) PlanMedioProveedorModel.getPlanMedioProveedorSessionService().findProveedorByEmpresaId(idEmpresa);
				SpiritCache.setObject("planMedioProveedor",planMedioProveedorByEmpresas );
			} catch (GenericBusinessException e) {
				log.debug("Error a buscar planMedioProveedorByEmpresas");
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro planMedioProveedorByEmpresas en cache");
			return planMedioProveedorByEmpresas;
		}
		return planMedioProveedorByEmpresas;
	}
	
	public static List findPresupuestoByEmpresa(Long idEmpresa) {
		List presupuestoByEmpresas;
		presupuestoByEmpresas = SpiritCache.findObject("presupuestoByEmpresas");

		if (presupuestoByEmpresas == null) {
			try {
				log.debug("Entro a buscar presupuesto");
				presupuestoByEmpresas = (List) PresupuestoModel.getPresupuestoSessionService().findProveedorByEmpresaId(idEmpresa);
				SpiritCache.setObject("presupuesto",presupuestoByEmpresas);
			} catch (GenericBusinessException e) {
				log.debug("Error a buscar presupuestoByEmpresas");
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro presupuestoByEmpresas en cache");
			return presupuestoByEmpresas;
		}
		return presupuestoByEmpresas;
	}*/
	
	public static List findCiudades() {
		List ciudades;
		ciudades = SpiritCache.findObject("ciudades");

		if (ciudades == null) {
			try {
				log.debug("Entro a buscar ciudades");
				ciudades = (List) CiudadModel.getCiudadSessionService()
						.getCiudadList();
				SpiritCache.setObject("ciudades", ciudades);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro ciudades en cache");
			return ciudades;
		}
		return ciudades;
	}
	
	public static List findCentroGasto() {
		List centroGasto;
		centroGasto = SpiritCache.findObject("centroGasto");

		if (centroGasto == null) {
			try {
				log.debug("Entro a buscar centros de gasto");
				centroGasto = (List) SessionServiceLocator.getCentroGastoSessionService().findCentroGastoByEmpresaId(Parametros.getIdEmpresa());
				SpiritCache.setObject("centroGasto", centroGasto);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro centros de gasto en cache");
			return centroGasto;
		}
		return centroGasto;
	}
	
	public static List findCruceDocumento() {
		List cruceDocumento;
		cruceDocumento = SpiritCache.findObject("cruceDocumento");

		if (cruceDocumento == null) {
			try {
				log.debug("Entro a buscar cruceDocumentos");
				cruceDocumento = (List) SessionServiceLocator.getCruceDocumentoSessionService().getCruceDocumentoList();
				SpiritCache.setObject("cruceDocumento", cruceDocumento);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro cruceDocumentos en cache");
			return cruceDocumento;
		}
		return cruceDocumento;
	}
	
	public static List findParametroEmpresa() {
		List parametroEmpresa;
		parametroEmpresa = SpiritCache.findObject("parametroEmpresa");

		if (parametroEmpresa == null) {
			try {
				log.debug("Entro a buscar parametroEmpresa");
				parametroEmpresa = (List) SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByEmpresaId(Parametros.getIdEmpresa());
				SpiritCache.setObject("parametroEmpresa", parametroEmpresa);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro parametroEmpresa en cache");
			return parametroEmpresa;
		}
		return parametroEmpresa;
	}
	
	public static List findDocumento() {
		List documento;
		documento = SpiritCache.findObject("documento");

		if (documento == null) {
			try {
				log.debug("Entro a buscar documentos");
				documento = (List) SessionServiceLocator.getDocumentoSessionService().getDocumentoList();
				SpiritCache.setObject("documento", documento);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro documentos en cache");
			return documento;
		}
		return documento;
	}
	
	public static List findTipoArchivo() {
		List tipoArchivo;
		tipoArchivo = SpiritCache.findObject("tipoArchivo");

		if (tipoArchivo == null) {
			try {
				log.debug("Entro a buscar tipo de archivo");
				tipoArchivo = (List) SessionServiceLocator.getTipoArchivoSessionService().getTipoArchivoList();
				SpiritCache.setObject("tipoArchivo", tipoArchivo);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro tipo de archivo en cache");
			return tipoArchivo;
		}
		return tipoArchivo;
	}
	
	public static List findCiudadesByProvincia(Long idProvincia) {
		List ciudadesByProvincia;
		ciudadesByProvincia = SpiritCache.findObject("ciudadesByProvincia");

		if (ciudadesByProvincia == null) {
			try {
				log.debug("Entro a buscar ciudadesByProvincia");
				ciudadesByProvincia = (List) CiudadModel.getCiudadSessionService()
						.findCiudadByProvinciaId(idProvincia);
				SpiritCache.setObject("ciudadesByProvincia", ciudadesByProvincia);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro ciudadesByProvincia en cache");
			return ciudadesByProvincia;
		}
		return ciudadesByProvincia;
	}
	
	public static List findTipoEmpleado() {
		List tipoEmpleado;
		tipoEmpleado = SpiritCache.findObject("tipoEmpleado");

		if (tipoEmpleado == null) {
			System.out.println("entro en tipoEmpleado");
			try {
				log.debug("Entro a buscar tipoEmpleado");
				tipoEmpleado = (List) SessionServiceLocator
						.getTipoEmpleadoSessionService().findTipoEmpleadoByEmpresaId(Parametros.getIdEmpresa());
				SpiritCache.setObject("tipoEmpleado", tipoEmpleado);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro tipoEmpleado en cache");
			return tipoEmpleado;
		}
		return tipoEmpleado;
	}
	
	
	public static List findTipoIdentificacion() {
		List tipoIdentificacion;
		tipoIdentificacion = SpiritCache.findObject("tipoIdentificacion");

		if (tipoIdentificacion == null) {
			try {
				log.debug("Entro a buscar tipoIdentificacion");
				tipoIdentificacion = (List) SessionServiceLocator
						.getTipoIdentificacionSessionService()
						.getTipoIdentificacionList();
				SpiritCache.setObject("tipoIdentificacion", tipoIdentificacion);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro tipoIdentificacion en cache");
			return tipoIdentificacion;
		}
		return tipoIdentificacion;
	}

	public static List findDepartamentos() {
		List departamentos;
		departamentos = SpiritCache.findObject("departamentos");

		if (departamentos == null) {
			try {
				log.debug("Entro a buscar departamentos");
				departamentos = (List) SessionServiceLocator
						.getDepartamentoSessionService().findDepartamentoByEmpresaId(Parametros.getIdEmpresa());
				SpiritCache.setObject("departamentos", departamentos);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro departamentos en cache");
			return departamentos;
		}
		return departamentos;
	}
	
	
	public static List findMoneda() {
		List moneda;
		moneda = SpiritCache.findObject("moneda");

		if (moneda == null) {
			try {
				log.debug("Entro a buscar moneda");
				moneda = (List) MonedaModel.getMonedaSessionService()
						.getMonedaList();
				SpiritCache.setObject("moneda", moneda);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro moneda en cache");
			return moneda;
		}
		return moneda;
	}

	public static List findLinea() {
		List linea;
		linea = SpiritCache.findObject("linea");

		if (linea == null) {
			try {
				log.debug("Entro a buscar lineas");
				linea = (List) SessionServiceLocator.getLineaSessionService().findLineaByEmpresaId(Parametros.getIdEmpresa());
				SpiritCache.setObject("linea", linea);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro lineas en cache");
			return linea;
		}
		return linea;
	}
	
	public static List findEmpleado() {
		List empleado;
		empleado = SpiritCache.findObject("empleado");

		if (empleado == null) {
			try {
				log.debug("Entro a buscar empleados");
				empleado = (List) SessionServiceLocator.getEmpleadoSessionService()
						.getEmpleadoList();
				SpiritCache.setObject("empleado", empleado);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro producto cliente en cache");
			return empleado;
		}
		return empleado;
	}

	public static List findEmpleadosByEmpresa(Long idEmpresa) {
		List empleadosByEmpresas;
		empleadosByEmpresas = SpiritCache.findObject("empleadosByEmpresas");
		//TODO: Arreglar para que busque por muchas empresas en cache, sino solo queda una empresa seleccionada.

		if (empleadosByEmpresas == null) {
			try {
				log.debug("Entro a buscar empleados");
				empleadosByEmpresas = (List) SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaId(idEmpresa);
				SpiritCache.setObject("empleados", empleadosByEmpresas);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro empleadosByEmpresas en cache");
			return empleadosByEmpresas;
		}
		return empleadosByEmpresas;
	}
	
	public static List findTipoProveedor() {
		List tipoProveedor;
		tipoProveedor = SpiritCache.findObject("tipoProveedor");

		if (tipoProveedor == null) {
			try {
				log.debug("Entro a buscar tipo proveedor");
				tipoProveedor = (List) SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByEmpresaId(Parametros.getIdEmpresa());
				SpiritCache.setObject("tipoProveedor",tipoProveedor);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro tipoProveedor en cache");
			return tipoProveedor;
		}
		return tipoProveedor;
	}
	
	public static List findModulo() {
		List modulo;
		modulo = SpiritCache.findObject("modulo");

		if (modulo == null) {
			try {
				log.debug("Entro a buscar modulo");
				modulo = (List) SessionServiceLocator.getModuloSessionService()
						.getModuloList();
				SpiritCache.setObject("modulo", modulo);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro modulo en cache");
			return modulo;
		}
		return modulo;
	}
	
	public static List findOficina() {
		List oficina;
		oficina = SpiritCache.findObject("oficina");

		if (oficina == null) {
			try {
				log.debug("Entro a buscar oficina");
				oficina = (List) SessionServiceLocator.getOficinaSessionService().getOficinaList();
				SpiritCache.setObject("oficina", oficina);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro oficina en cache");
			return oficina;
		}
		return oficina;
	}
	
	public static List findTipoDocumento(Long idEmpresa, Long idModulo) {
		List tipoDocumento;
		tipoDocumento = SpiritCache.findObject("tipoDocumento");
	
		if (tipoDocumento == null) {
			try {
				Map aMap = new HashMap();
				
					aMap.put("empresaId", idEmpresa);
					aMap.put("moduloId", idModulo);
					aMap.put("estado", "A");
				
					tipoDocumento = (List) SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQuery(aMap);
				SpiritCache.setObject("tipoDocumento", tipoDocumento);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return tipoDocumento;
		}
		return tipoDocumento;
	}
	
	public static List findMoneda(String codigo, String nombre) {
		List moneda;
		moneda = SpiritCache.findObject("moneda");
	
		if (moneda == null) {
			try {
				Map aMap = new HashMap();
				
					aMap.put("codigo", codigo);
					aMap.put("nombre", nombre);
				
					moneda = (List) MonedaModel.getMonedaSessionService().findMonedaByQuery(aMap);
				SpiritCache.setObject("moneda", moneda);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return moneda;
		}
		return moneda;
	}
	
	public static List findTipoParametro(Long idEmpresa) {
		List tipoParametro;
		tipoParametro = SpiritCache.findObject("tipoParametro");
	
		if (tipoParametro == null) {
			try {
				log.debug("Entro a buscar tipoParametro");
				tipoParametro = (List) SessionServiceLocator.getTipoParametroSessionService().findTipoParametroByEmpresaId(idEmpresa); 
				
				SpiritCache.setObject("tipoParametro", tipoParametro);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro tipoParametro en cache");
			return tipoParametro;
		}
		return tipoParametro;
	}
	
	public static List findTipoParametro() {
		List tipoParametro;
		tipoParametro = SpiritCache.findObject("tipoParametro");
	
		if (tipoParametro == null) {
			try {
				log.debug("Entro a buscar tipoParametro");
				tipoParametro = (List) SessionServiceLocator.getTipoParametroSessionService().findTipoParametroByEmpresaId(Parametros.getIdEmpresa()); 
				
				SpiritCache.setObject("tipoParametro", tipoParametro);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			log.debug("Encontro tipoParametro en cache");
			return tipoParametro;
		}
		return tipoParametro;
	}

}

