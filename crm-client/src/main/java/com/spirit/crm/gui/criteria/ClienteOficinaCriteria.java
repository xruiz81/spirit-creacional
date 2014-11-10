package com.spirit.crm.gui.criteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.util.GeneralUtil;

public class ClienteOficinaCriteria extends Criteria {
	
	Long idCorporacion;
	Long idCliente;
	//ADD 27 JULIO
	Long idTipoProveedor;
	
	String codigoTipoCliente;
	String mmpg;
	String txtCodigo, txtDescripcion;
	String txtCiudad;
	Map queryBuilded;
	boolean textFieldSearch;	

	Map<Long,ClienteIf> mapaCliente = new HashMap<Long, ClienteIf>();
	Map<Long,CiudadIf> mapaCiudad = new HashMap<Long, CiudadIf>();
	
	public ClienteOficinaCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Oficina de Cliente";
	}
	
	public ClienteOficinaCriteria(String nombrePanel, Long idCorporacion, Long idCliente, String tipoCliente, String mmpg, boolean textFieldSearch) {
		this.nombrePanel = nombrePanel;
		this.idCorporacion = idCorporacion;
		this.idCliente = idCliente;
		//ADD 27 JULIO
		this.idTipoProveedor = 0L;
		this.codigoTipoCliente = tipoCliente;
		/* El parámetro mmpg es utilizado en los casos en que se requiere buscar proveedores por el tipo de productos que proveen
		 * Funciona mediante exclusion explícita, es decir que si requiero buscar proveedores exclusivamente de medios el valor de mmpg
		 * debería ser "PG", lo cual excluye de la búsqueda a proveedores de produccion y de gastos. El valor del parámetro no necesariamente
		 * requiere que las letras estén en un orden específico, tampoco afecta el número de veces que una letra pueda estar repetida en 
		 * el valor del parámetro, las letras distintas de las que tienen un significado explícito dentro del parámetro tampoco afectan, 
		 * por tanto valores como: "GP", "PPG", "GPPPP", "GPXYZ", tendrían el mismo efecto en la búsqueda.
		 * Si por ejemplo deseamos buscar exclusivamente proveedores de producción y medios, opciones como las siguientes son válidas:
		 * "G", "ABCG", "GGGG", etc.
		 * Si deseamos buscar todos los proveedores sin importar el tipo las opciones siguientes son válidas: "", "ABC", "123", etc.  
		 * El parámetro mmpg no es sensible a mayúsculas y minúsculas así que pueden utilizarse indistintamente "m" en lugar de "M", "p" en lugar
		 * de "P" o "g" en lugar de "G" sin afectar el resultado de la búsqueda. 
		 */
		this.mmpg = mmpg;
		this.textFieldSearch = textFieldSearch;
	}
	
	//ADD 27 JULIO
	public ClienteOficinaCriteria(String nombrePanel, Long idCorporacion, Long idCliente, String tipoCliente,Long idTipoProveedor, String mmpg, boolean textFieldSearch) {
		this.nombrePanel = nombrePanel;
		this.idCorporacion = idCorporacion;
		this.idCliente = idCliente;
		//ADD 27 JULIO
		this.idTipoProveedor = idTipoProveedor;
		this.codigoTipoCliente = tipoCliente;
		this.mmpg = mmpg;
		this.textFieldSearch = textFieldSearch;
	}
	//END ADD 27 JULIO

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Identificación");
		//headers.add("Nombre Legal");
		headers.add("Descripción");
		headers.add("Ciudad");
		headers.add("Dirección");
		return headers;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();

		for (int i = 0; i < lista.size(); i++) {
			try {
				ArrayList fila = new ArrayList();
				ClienteOficinaIf bean = (ClienteOficinaIf) lista.get(i);
				ClienteIf beanCliente = GeneralUtil.verificarMapaCliente(mapaCliente,bean.getClienteId());
				fila.add(beanCliente.getIdentificacion());
				//fila.add(beanCliente.getNombreLegal());
				fila.add(bean.getDescripcion());
				CiudadIf ciudad = GeneralUtil.verificarMapaCiudad(mapaCiudad,bean.getCiudadId());
				fila.add(ciudad.getNombre());
				fila.add(bean.getDireccion());
				data.add(fila);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
		return data;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	//MODIFIED 27 JULIO
	public int getResultListSize() {
		try {
			if (!textFieldSearch) {
				if (idCorporacion == 0L && idCliente == 0L && idTipoProveedor == 0L) {
					if (queryBuilded.size() == 0)
						tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaListSize(queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					else
						tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaListSize(queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
				} //ADD 27 JULIO
				else if (idCorporacion == 0L && idCliente == 0L && idTipoProveedor != 0L) {
					if (queryBuilded.size() == 0)
						tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaByTipoProveedorIdListSize(queryBuilded, codigoTipoCliente, idTipoProveedor, Parametros.getIdEmpresa(), mmpg);
					else
						tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaByTipoProveedorIdListSize(queryBuilded, codigoTipoCliente, idTipoProveedor, Parametros.getIdEmpresa(), mmpg);
				}//END 27 JULIO
				else if (idCorporacion != 0L && idCliente == 0L && idTipoProveedor == 0L) {
					if (queryBuilded.size() == 0)
						tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaListSize(queryBuilded, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					else
						tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaListSize(queryBuilded, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
				} //ADD 27 JULIO
				 else if (idCliente != 0L && idTipoProveedor != 0L) {
						if (queryBuilded.size() == 0) {
							Map filtroMap = new HashMap();
							filtroMap.put("clienteId", idCliente);
							tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaByTipoProveedorIdListSize(filtroMap, idCorporacion, codigoTipoCliente, idTipoProveedor, Parametros.getIdEmpresa(), mmpg);
						} else {
							queryBuilded.put("clienteId", idCliente);
							tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaByTipoProveedorIdListSize(queryBuilded, idCorporacion, codigoTipoCliente, idTipoProveedor, Parametros.getIdEmpresa(), mmpg);
						}
					}
				//END 27 JULIO
				else {
					if (queryBuilded.size() == 0) {
						Map filtroMap = new HashMap();
						filtroMap.put("clienteId", idCliente);
						tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaListSize(filtroMap, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					} else {
						queryBuilded.put("clienteId", idCliente);
						tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaListSize(queryBuilded, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					}
				}
			} else {
				tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().findBusinessOperatorOfficeByQueryListSize(queryBuilded);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}
	
	/*//COMENTED 27 JULIO
	 * public int getResultListSize() {
		try {
			if (!textFieldSearch) {
				if (idCorporacion == 0L && idCliente == 0L) {
					if (queryBuilded.size() == 0)
						tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaListSize(queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					else
						tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaListSize(queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
				} else if (idCorporacion != 0L && idCliente == 0L) {
					if (queryBuilded.size() == 0)
						tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaListSize(queryBuilded, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					else
						tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaListSize(queryBuilded, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
				} else {
					if (queryBuilded.size() == 0) {
						Map filtroMap = new HashMap();
						filtroMap.put("clienteId", idCliente);
						tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaListSize(filtroMap, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					} else {
						queryBuilded.put("clienteId", idCliente);
						tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaListSize(queryBuilded, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					}
				}
			} else {
				tamanoListaResultados = SessionServiceLocator.getClienteOficinaSessionService().findBusinessOperatorOfficeByQueryListSize(queryBuilded);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}
	 * */

	//ADD 27 JULIO
	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			if (!textFieldSearch) {
				if (idCorporacion == 0L && idCliente == 0L && idTipoProveedor == 0L) {
					if (queryBuilded.size() == 0)
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList(startIndex, endIndex,	queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					else
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList(startIndex, endIndex,	queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
				}//ADD 27 JULIO
				else if (idCorporacion == 0L && idCliente == 0L && idTipoProveedor != 0L) {
					if (queryBuilded.size() == 0)
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaByTipoProveedorIdList(startIndex, endIndex, queryBuilded, codigoTipoCliente, idTipoProveedor, Parametros.getIdEmpresa(), mmpg);
					else
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaByTipoProveedorIdList(startIndex, endIndex, queryBuilded, codigoTipoCliente, idTipoProveedor, Parametros.getIdEmpresa(), mmpg);
				}//END 27 JULIO
				else if (idCorporacion != 0L && idCliente == 0L && idTipoProveedor == 0L) {
					if (queryBuilded.size() == 0)
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList(startIndex, endIndex,	queryBuilded, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					else
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList(startIndex, endIndex,	queryBuilded, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
				} //ADD 27 JULIO
				else if (idCliente != 0L && idTipoProveedor != 0L) {
					if (queryBuilded.size() == 0) {
						Map filtroMap = new HashMap();
						filtroMap.put("clienteId", idCliente);
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaByTipoProveedorIdList(startIndex, endIndex,	filtroMap, idCorporacion, codigoTipoCliente, idTipoProveedor, Parametros.getIdEmpresa(), mmpg);
					} else {
						queryBuilded.put("clienteId", idCliente);
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaByTipoProveedorIdList(startIndex, endIndex,	queryBuilded, idCorporacion, codigoTipoCliente, idTipoProveedor, Parametros.getIdEmpresa(), mmpg);
					}
				}//END 27 JULIO
				else {
					if (queryBuilded.size() == 0) {
						Map filtroMap = new HashMap();
						filtroMap.put("clienteId", idCliente);
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList(startIndex, endIndex,	filtroMap, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					} else {
						queryBuilded.put("clienteId", idCliente);
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList(startIndex, endIndex,	queryBuilded, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					}
				}
			} else {
				listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().findBusinessOperatorOfficeByQuery(startIndex, endIndex, queryBuilded);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	//COMENTED 27 JULIO
	/*public void buscarRegistros(int startIndex, int endIndex) {
		try {
			if (!textFieldSearch) {
				if (idCorporacion == 0L && idCliente == 0L) {
					if (queryBuilded.size() == 0)
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList(startIndex, endIndex,	queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					else
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList(startIndex, endIndex,	queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
				} else if (idCorporacion != 0L && idCliente == 0L) {
					if (queryBuilded.size() == 0)
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList(startIndex, endIndex,	queryBuilded, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					else
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList(startIndex, endIndex,	queryBuilded, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
				} else {
					if (queryBuilded.size() == 0) {
						Map filtroMap = new HashMap();
						filtroMap.put("clienteId", idCliente);
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList(startIndex, endIndex,	filtroMap, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					} else {
						queryBuilded.put("clienteId", idCliente);
						listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList(startIndex, endIndex,	queryBuilded, idCorporacion, codigoTipoCliente, Parametros.getIdEmpresa(), mmpg);
					}
				}
			} else {
				listaResultados = (ArrayList) SessionServiceLocator.getClienteOficinaSessionService().findBusinessOperatorOfficeByQuery(startIndex, endIndex, queryBuilded);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}*/

	public void setTxtParametros(String parametro1, String parametro2, String parametro3) {
		this.txtCodigo = parametro1;
		this.txtDescripcion = parametro2;
		this.txtCiudad= parametro3;
		
		queryBuilded = buildQuery();
	}

	// Me devuele un map segun el campo que mando a buscar
	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if (this.txtCodigo != null && !("".equals(this.txtCodigo)))
			aMap.put("identificacion", this.txtCodigo + "%");
		else
			aMap.put("identificacion", "%");
		
		if (this.txtDescripcion != null && !("".equals(this.txtDescripcion)))
			aMap.put("descripcion", this.txtDescripcion + "%");
		
		
		if (this.txtCiudad != null && !("".equals(this.txtCiudad))){			
			Collection ciudadColeccion = null;
			try {
				ciudadColeccion = SessionServiceLocator.getCiudadSessionService().findCiudadByNombre(this.txtCiudad.toString()+ "%");
			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(ciudadColeccion.size()>0)
			{
				Iterator itCiudad = ciudadColeccion.iterator();
				if(itCiudad.hasNext())
				{
					CiudadIf ciudad = (CiudadIf) itCiudad.next();
					this.txtCiudad=ciudad.getId().toString();
				}				
			}
			else{
				this.txtCiudad="0";
			}			
			aMap.put("ciudadId", new Long(this.txtCiudad));
		}
		
		return aMap;
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
}
