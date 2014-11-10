package com.spirit.timeTracker.gui.model.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.spirit.client.Parametros;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.medios.entity.EquipoEmpleadoIf;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.medios.entity.TipoBriefIf;

public class MapaCache {

	private static Map<Long, EmpleadoIf> mapaEmpleados = null;
	private static Map<Long, ClienteIf> mapaClientes = null;
	private static Map<Long, SubtipoOrdenIf> mapaSubTipoOrden = null;
	private static Map<Long, TipoOrdenIf> mapaTipoOrden = null;
	private static Map<Long, TipoBriefIf> mapaTipoBrief = null;
	private static Map<Long, ProductoClienteIf> mapaProductoCliente = null;
	private static Map<Long, ClienteOficinaIf> mapaClienteOficina = null;

	public static EmpleadoIf verificarEmpleadoEnMapa(
			Map<Long, EmpleadoIf> mapaEmpleados, Long idEmpleado)
			throws GenericBusinessException {
		EmpleadoIf empleadoIf = null;// (EmpleadoIf)verificarObjetoEnMapa(mapaEmpleados,
										// idEmpleado);
		if (idEmpleado != null) {
			if (!mapaEmpleados.containsKey(idEmpleado)) {
				empleadoIf = SessionServiceLocator.getEmpleadoSessionService()
						.getEmpleado(idEmpleado);
				mapaEmpleados.put(empleadoIf.getId(), empleadoIf);
			} else {
				empleadoIf = mapaEmpleados.get(idEmpleado);
			}
		}
		return empleadoIf;
	}

	public static ClienteOficinaIf verificarClienteOficinaEnMapa(
			Map<Long, ClienteOficinaIf> mapaClienteOficina,
			Long idClienteOficina) throws GenericBusinessException {
		ClienteOficinaIf clienteOficinaIf = null;// (ClienteIf)verificarObjetoEnMapa(mapaEmpleados,
		// idCliente);
		if (idClienteOficina != null) {
			if (!mapaClienteOficina.containsKey(idClienteOficina)) {
				clienteOficinaIf = SessionServiceLocator
						.getClienteOficinaSessionService().getClienteOficina(
								idClienteOficina);
				mapaClienteOficina.put(clienteOficinaIf.getId(),
						clienteOficinaIf);
			} else {
				clienteOficinaIf = mapaClienteOficina.get(idClienteOficina);
			}
		}
		return clienteOficinaIf;
	}

	public static ClienteIf verificarClienteEnMapa(
			Map<Long, ClienteIf> mapaClientes, Long idCliente)
			throws GenericBusinessException {
		ClienteIf clienteIf = null;// (ClienteIf)verificarObjetoEnMapa(mapaEmpleados,
									// idCliente);
		if (idCliente != null) {
			if (!mapaClientes.containsKey(idCliente)) {
				clienteIf = SessionServiceLocator.getClienteSessionService()
						.getCliente(idCliente);
				mapaClientes.put(clienteIf.getId(), clienteIf);
			} else {
				clienteIf = mapaClientes.get(idCliente);
			}
		}
		return clienteIf;
	}

	public static SubtipoOrdenIf verificarSubTipoOrdenEnMapa(
			Map<Long, SubtipoOrdenIf> mapaSubTipoOrden, Long idSubTipoOrden)
			throws GenericBusinessException {
		SubtipoOrdenIf subTipoOrden = null;// (SubtipoOrdenIf)verificarObjetoEnMapa(mapaSubTipoOrden,
											// idSubTipoOrden);
		if (idSubTipoOrden != null) {
			if (!mapaSubTipoOrden.containsKey(idSubTipoOrden)) {
				subTipoOrden = SessionServiceLocator
						.getSubtipoOrdenSessionService().getSubtipoOrden(
								idSubTipoOrden);
				mapaSubTipoOrden.put(subTipoOrden.getId(), subTipoOrden);
			} else {
				subTipoOrden = mapaSubTipoOrden.get(idSubTipoOrden);
			}
		}
		return subTipoOrden;
	}

	public static TipoOrdenIf verificarTipoOrdenEnMapa(
			Map<Long, TipoOrdenIf> mapaTipoOrden, Long idTipoOrden)
			throws GenericBusinessException {
		TipoOrdenIf tipoOrden = null;// (TipoOrdenIf)verificarObjetoEnMapa(mapaTipoOrden,
										// idTipoOrden);
		if (idTipoOrden != null) {
			if (!mapaTipoOrden.containsKey(idTipoOrden)) {
				tipoOrden = SessionServiceLocator.getTipoOrdenSessionService()
						.getTipoOrden(idTipoOrden);
				mapaTipoOrden.put(tipoOrden.getId(), tipoOrden);
			} else {
				tipoOrden = mapaTipoOrden.get(idTipoOrden);
			}
		}
		return tipoOrden;
	}

	public static TipoBriefIf verificarTipoBriefEnMapa(
			Map<Long, TipoBriefIf> mapaTipoBrief, Long idTipoBrief)
			throws GenericBusinessException {
		TipoBriefIf tipoBrief = null; // (TipoBriefIf)verificarObjetoEnMapa(mapaTipoBrief,
										// idTipoBrief);
		if (idTipoBrief != null) {
			if (!mapaTipoBrief.containsKey(idTipoBrief)) {
				tipoBrief = SessionServiceLocator.getTipoBriefSessionService()
						.getTipoBrief(idTipoBrief);
				mapaTipoBrief.put(tipoBrief.getId(), tipoBrief);
			} else {
				tipoBrief = mapaTipoBrief.get(idTipoBrief);
			}
		}
		return tipoBrief;
	}

	public static ProductoClienteIf verificarProductoClienteEnMapa(
			Map<Long, ProductoClienteIf> mapaProductoCliente,
			Long idProductoCliente) throws GenericBusinessException {
		ProductoClienteIf productoCliente = null; // (TipoBriefIf)verificarObjetoEnMapa(mapaTipoBrief,
													// idTipoBrief);
		if (idProductoCliente != null) {
			if (!mapaProductoCliente.containsKey(idProductoCliente)) {
				productoCliente = SessionServiceLocator
						.getProductoClienteSessionService().getProductoCliente(
								idProductoCliente);
				mapaProductoCliente.put(productoCliente.getId(),
						productoCliente);
			} else {
				productoCliente = mapaProductoCliente.get(idProductoCliente);
			}
		}
		return productoCliente;
	}

	private static Map<Long, List<EmpleadoIf>> mapaEquipos = new HashMap<Long, List<EmpleadoIf>>();

	private static void llenarMapaEquipo() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("empresaId", Parametros.getIdEmpresa());

		try {
			Collection<EquipoTrabajoIf> listaEquipos = SessionServiceLocator
					.getEquipoTrabajoSessionService().findEquipoTrabajoByQuery(
							parametros);
			for (EquipoTrabajoIf et : listaEquipos) {

				List<EmpleadoIf> setEmpleados = new ArrayList<EmpleadoIf>();
				Collection<EquipoEmpleadoIf> listaEmpleado = SessionServiceLocator
						.getEquipoEmpleadoSessionService()
						.findEquipoEmpleadoByEquipoId(et.getId());
				for (EquipoEmpleadoIf ee : listaEmpleado) {
					setEmpleados.add(SessionServiceLocator
							.getEmpleadoSessionService().getEmpleado(
									ee.getEmpleadoId()));
				}
				mapaEquipos.put(et.getId(), setEmpleados);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public static List<EmpleadoIf> obtenerEquipoTrabajoPorEmpleadoId(Long idEmpleado) {
		llenarMapaEquipo();
		Iterator<Long> itMapaEquipo = mapaEquipos.keySet().iterator();
		while (itMapaEquipo.hasNext()) {
			Long idEquipo = itMapaEquipo.next();
			List<EmpleadoIf> empleados = mapaEquipos.get(idEquipo);
			for (EmpleadoIf empleado : empleados) {
				if (idEmpleado.equals(empleado.getId())) {
					return empleados;
				}
			}
		}
		return new ArrayList<EmpleadoIf>();
	}

	public static Map<Long, EmpleadoIf> getMapaEmpleados() {
		return mapaEmpleados;
	}

	public static void setMapaEmpleados(Map<Long, EmpleadoIf> mapaEmpleados) {
		MapaCache.mapaEmpleados = mapaEmpleados;
	}

	public static Map<Long, ClienteIf> getMapaClientes() {
		return mapaClientes;
	}

	public static void setMapaClientes(Map<Long, ClienteIf> mapaClientes) {
		MapaCache.mapaClientes = mapaClientes;
	}

	public static Map<Long, SubtipoOrdenIf> getMapaSubTipoOrden() {
		return mapaSubTipoOrden;
	}

	public static void setMapaSubTipoOrden(
			Map<Long, SubtipoOrdenIf> mapaSubTipoOrden) {
		MapaCache.mapaSubTipoOrden = mapaSubTipoOrden;
	}

	public static Map<Long, TipoOrdenIf> getMapaTipoOrden() {
		return mapaTipoOrden;
	}

	public static void setMapaTipoOrden(Map<Long, TipoOrdenIf> mapaTipoOrden) {
		MapaCache.mapaTipoOrden = mapaTipoOrden;
	}

	public static Map<Long, TipoBriefIf> getMapaTipoBrief() {
		return mapaTipoBrief;
	}

	public static void setMapaTipoBrief(Map<Long, TipoBriefIf> mapaTipoBrief) {
		MapaCache.mapaTipoBrief = mapaTipoBrief;
	}

	public static Map<Long, ProductoClienteIf> getMapaProductoCliente() {
		return mapaProductoCliente;
	}

	public static void setMapaProductoCliente(
			Map<Long, ProductoClienteIf> mapaProductoCliente) {
		MapaCache.mapaProductoCliente = mapaProductoCliente;
	}

	public static Map<Long, ClienteOficinaIf> getMapaClienteOficina() {
		return mapaClienteOficina;
	}

	public static void setMapaClienteOficina(
			Map<Long, ClienteOficinaIf> mapaClienteOficina) {
		MapaCache.mapaClienteOficina = mapaClienteOficina;
	}

}
