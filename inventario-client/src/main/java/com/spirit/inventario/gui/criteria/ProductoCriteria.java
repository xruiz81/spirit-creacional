package com.spirit.inventario.gui.criteria;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.MultiComparator;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.PopupFinderActionListener;
import com.spirit.inventario.entity.ColorIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;

public class ProductoCriteria extends Criteria{
	
	String nombrePanel = "de Productos";
	Long idReferencia=0L;
	String tipoProducto = "";
	String nombreGenerico = "";
	String tipoReferencia = "";
	String servicioConsumo = "";
	String mmpg;
	boolean buscarPorTexto = false;
	Map queryBuilded = null;
	boolean filtrarProveedor = false;
	ClienteOficinaIf clienteOficinaIf = null;
	String txtCodigo,txtNombreGenerico;
	String txtPresentacion = "";
	String txtModelo;
	Map genericosMap = new HashMap();
	Map modelosMap = new HashMap();
	Map presentacionesMap = new HashMap();
	Map coloresMap = new HashMap();
	
	public ProductoCriteria(boolean filtrarProveedor) {
		this.filtrarProveedor = filtrarProveedor;
		//CARGAR MAPAS
		loadMaps();
	}
	

	public void setNombrePanel() {
		this.nombrePanel = "de Productos";
	}
	
	/*public ProductoCriteria(Long idReferencia) {
		this.idReferencia = idReferencia;
	}
	
	public ProductoCriteria(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}*/
	
	public ProductoCriteria(String nombrePanel, Long idReferencia, String filtro, String tipoReferencia, String servicioConsumo, boolean filtrarProveedor, String mmpg) {
		//this.modelForm = model;
		//panelForm = (JPanel) modelForm;
		this.nombrePanel = nombrePanel;
		// idReferencia: ID del presupuesto, orden de medios o proveedor
		this.idReferencia = idReferencia;
		// idTipoProducto: Indica si el producto a buscar es de PRODUCCION (P), MEDIOS (M) o AMBOS(A)
		this.tipoProducto = filtro;
		// nombreGenerico: El nombre del genérico por el cual se desea filtrar la búsqueda
		this.nombreGenerico = filtro;
		// tipoReferencia: Indica si la búsqueda se la hace por REFERENCIA (R) o por PROVEEDOR (P). Cuando la búsqueda se hace en base a un presupuesto
		// u orden de medios se debe usar el valor R
		this.tipoReferencia = tipoReferencia;
		// servicioConsumo: Indica si el producto que se busca es un servicio (S) o un producto de consumo (C) o ambos (A) 
		this.servicioConsumo = servicioConsumo;
		//Indica si se tiene que presentar el campo de busqueda por proveedor
		this.filtrarProveedor = filtrarProveedor;
		/* El parámetro mmpg es utilizado en los casos en que se requiere buscar productos por tipo
		 * Funciona mediante exclusion explícita, es decir que si requiero buscar productos exclusivamente de medios el valor de mmpg
		 * debería ser "PG", lo cual excluye de la búsqueda los productos de produccion y de gastos. El valor del parámetro no necesariamente
		 * requiere que las letras estén en un orden específico, tampoco afecta el número de veces que una letra pueda estar repetida en 
		 * el valor del parámetro, las letras distintas de las que tienen un significado explícito dentro del parámetro tampoco afectan, 
		 * por tanto valores como: "GP", "PPG", "GPPPP", "GPXYZ", tendrían el mismo efecto en la búsqueda.
		 * Si por ejemplo deseamos buscar exclusivamente productos de producción y medios, opciones como las siguientes son válidas:
		 * "G", "ABCG", "GGGG", etc.
		 * Si deseamos buscar todos los productos sin importar el tipo las opciones siguientes son válidas: "", "ABC", "123", etc.  
		 * El parámetro mmpg no es sensible a mayúsculas y minúsculas así que pueden utilizarse indistintamente "m" en lugar de "M", "p" en lugar
		 * de "P" o "g" en lugar de "G" sin afectar el resultado de la búsqueda. 
		 */
		this.mmpg = mmpg;
		
		//CARGAR MAPAS
		loadMaps();
	}
	
	public ProductoCriteria(String nombrePanel) {
		this.buscarPorTexto = true;
		loadMaps();
	}
	
	private void loadMaps() {
		genericosMap = mapearGenericos();
		modelosMap = mapearModelos();
		presentacionesMap = mapearPresentaciones();
		coloresMap = mapearColores();
	}
	
	private Map mapearGenericos() {
		Map genericosMap = new HashMap();
		Long idEmpresa = Parametros.getIdEmpresa();
		try {
			Iterator it = SessionServiceLocator.getGenericoSessionService().findGenericoByEmpresaId(idEmpresa).iterator();
			while (it.hasNext()) {
				GenericoIf generico = (GenericoIf) it.next();
				genericosMap.put(generico.getId(), generico);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return genericosMap;
	}
	
	private Map mapearModelos() {
		Map modelosMap = new HashMap();
		try {
			Iterator it = SessionServiceLocator.getModeloSessionService().getModeloList().iterator();
			while (it.hasNext()) {
				ModeloIf modelo = (ModeloIf) it.next();
				modelosMap.put(modelo.getId(), modelo);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return modelosMap;
	}
	
	private Map mapearPresentaciones() {
		Map presentacionesMap = new HashMap();
		try {
			Iterator it = SessionServiceLocator.getPresentacionSessionService().getPresentacionList().iterator();
			while (it.hasNext()) {
				PresentacionIf presentacion = (PresentacionIf) it.next();
				presentacionesMap.put(presentacion.getId(), presentacion);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return presentacionesMap;
	}
	
	private Map mapearColores() {
		Map coloresMap = new HashMap();
		try {
			Iterator it = SessionServiceLocator.getColorSessionService().getColorList().iterator();
			while (it.hasNext()) {
				ColorIf color = (ColorIf) it.next();
				coloresMap.put(color.getId(), color);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return coloresMap;
	}
	
	/*public ProductoCriteria(SpiritModel model,String nombrePanel,
			Long idFiltroReferencia, String idFiltro, String tipo) {
		this.modelForm = model;
		this.nombrePanel = nombrePanel;
		this.idFiltroBusqueda = idFiltroReferencia;
		this.idFiltroBusquedaString = idFiltro;
		this.tipoFiltro = tipo;
	}*/
	
	
	PopupFinderActionListener al =  new PopupFinderActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Long idCorporacion = 0L;
			Long idCliente = 0L;
			ClienteOficinaCriteria clienteOficinaCriteria = new ClienteOficinaCriteria("Oficinas del Proveedor", idCorporacion, idCliente, "PR", "", false);
			JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if (popupFinder.getElementoSeleccionado() != null) {
				clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
				al.getTxtField().setText(clienteOficinaIf.getDescripcion());
			}
		}
	};

	public List getHeaders() {
		ArrayList<Object> headers = new ArrayList<Object>();
		headers.add("Código");
		headers.add("Nombre Genérico");
		headers.add("Modelo");
		headers.add("Presentación");
		headers.add("Color");
		if ( filtrarProveedor )
			headers.add(new Object[]{"Proveedor",al});
		else 
			headers.add("Proveedor");
		return headers;
	}
	
	public List getHeadersString() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Nombre Genérico");
		headers.add("Modelo");
		headers.add("Presentación");
		headers.add("Color");
		if ( filtrarProveedor )
			headers.add("Proveedor");
		return headers;
	}
	
	public List getHeadersCorto() {
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Código");
		headers.add("Nombre Genérico");
		return headers;
	}
	
	/*private Comparator<ProductoIf> productoComparador = new Comparator<ProductoIf>(){
		public int compare(ProductoIf p1, ProductoIf p2) {
			int genericoComparador = p1.getGenericoId().compareTo(p2.getGenericoId());
			int modeloComparador = (p1.getModeloId() != null && p2.getModeloId() != null)?p1.getModeloId().compareTo(p2.getModeloId()):1;
			int presentacionComparador = (p1.getPresentacionId() != null && p2.getPresentacionId() != null)?p1.getPresentacionId().compareTo(p2.getPresentacionId()):1;
			int colorComparador = (p1.getColorId() != null && p2.getColorId() != null)?p1.getColorId().compareTo(p2.getColorId()):1;
			if (genericoComparador < 0)
				return -1;
			else if (modeloComparador < 0)
				return -1;
			else if (presentacionComparador < 0)
				return -1;
			else if (colorComparador < 0)
				return -1;
			else
				return 1;
		}
	};*/
	
	private Comparator<ProductoIf> genericoIdComparador = new Comparator<ProductoIf>(){
		public int compare(ProductoIf p1, ProductoIf p2) {
			return p1.getGenericoId().compareTo(p2.getGenericoId());
		}
	};
	
	private Comparator<ProductoIf> modeloIdComparador = new Comparator<ProductoIf>(){
		public int compare(ProductoIf p1, ProductoIf p2) {
			if (p1.getModeloId() == null && p2.getModeloId() == null)
				return 0;
			if (p1.getModeloId() != null && p2.getModeloId() == null)
				return -1;
			if (p1.getModeloId() == null && p2.getModeloId() != null)
				return 1;
			if (p1.getModeloId() != null && p2.getModeloId() != null)
				return p1.getModeloId().compareTo(p2.getModeloId());
			return 0;
		}
	};
	
	private Comparator<ProductoIf> presentacionIdComparador = new Comparator<ProductoIf>() {
		public int compare(ProductoIf p1, ProductoIf p2) {
			if (p1.getPresentacionId() == null && p2.getPresentacionId() == null)
				return 0;
			if (p1.getPresentacionId() != null && p2.getPresentacionId() == null)
				return -1;
			if (p1.getPresentacionId() == null && p2.getPresentacionId() != null)
				return 1;
			if (p1.getPresentacionId() != null && p2.getPresentacionId() != null)
				return p1.getPresentacionId().compareTo(p2.getPresentacionId());
			return 0;
		}
	};
	
	private Comparator<ProductoIf> colorIdComparador = new Comparator<ProductoIf>() {
		public int compare(ProductoIf p1, ProductoIf p2) {
			if (p1.getColorId() == null && p2.getColorId() == null)
				return 0;
			if (p1.getColorId() != null && p2.getColorId() == null)
				return -1;
			if (p1.getColorId() == null && p2.getColorId() != null)
				return 1;
			if (p1.getColorId() != null && p2.getColorId() != null)
				return p1.getColorId().compareTo(p2.getColorId());
			return 0;
		}
	};

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		try {
			
			if (lista != null){
				Iterator it = lista.iterator();
		
				while (it.hasNext()) {
					ArrayList<String> fila = new ArrayList<String>();
					ProductoIf bean = (ProductoIf) it.next();
					fila.add((bean.getCodigoBarras()!=null && !bean.getCodigoBarras().trim().equals(""))?bean.getCodigoBarras():bean.getCodigo());
					GenericoIf generico = (GenericoIf) genericosMap.get(bean.getGenericoId());
					fila.add(generico.getNombre()!=null?generico.getNombre():"");
					if (bean.getModeloId() != null) {
						ModeloIf modelo = (ModeloIf) modelosMap.get(bean.getModeloId());
						fila.add(modelo.getNombre());
					} else
						fila.add("");
					
					if (bean.getPresentacionId()!=null) {
						PresentacionIf presentacion = (PresentacionIf) presentacionesMap.get(bean.getPresentacionId());
						fila.add(presentacion.getNombre());
					} else
						fila.add("");
					if (bean.getColorId()!=null) {
						ColorIf color = (ColorIf) coloresMap.get(bean.getColorId());
						fila.add(color.getNombre());
					} else {
						fila.add("");
					}
					if (bean.getProveedorId() != null) {
						ClienteOficinaIf proveedor = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(bean.getProveedorId());
						//fila.add(((ClienteIf) getClienteSessionService().findClienteByClienteOficinaId(proveedor.getId()).iterator().next()).getNombreLegal());
						fila.add( proveedor.getDescripcion() );
					} else
						fila.add("NO TIENE PROVEEDOR");
					
					data.add(fila);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al leer la información en Producto!",SpiritAlert.ERROR);
		}
		return data;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados =listaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public void buscarRegistros(int startIndex,int endIndex){
		try {
			if (queryBuilded != null)
				queryBuilded.put("estado", "A");
			if (mmpg == null)
				mmpg = "";
			if (buscarPorTexto)
				listaResultados = ((ArrayList) SessionServiceLocator.getProductoSessionService().findProductoByCriterioMap(startIndex,endIndex,queryBuilded));
			else if(idReferencia==0L && "".equals(tipoProducto))
				listaResultados = ((ArrayList) SessionServiceLocator.getProductoSessionService().findProductoByQuery(startIndex,endIndex,queryBuilded,Parametros.getIdEmpresa(),servicioConsumo, mmpg));
			else if (idReferencia==0L && !("".equals(nombreGenerico)))
				listaResultados = ((ArrayList) SessionServiceLocator.getProductoSessionService().findProductoByNombreGenerico(startIndex,endIndex,nombreGenerico,servicioConsumo,clienteOficinaIf!=null?clienteOficinaIf.getId():null,mmpg));
			else if ("".equals(tipoProducto)) {
				queryBuilded.put("proveedorId", idReferencia);
				listaResultados = ((ArrayList) SessionServiceLocator.getProductoSessionService().findProductoByQuery(startIndex,endIndex,queryBuilded,mmpg));
			} else{
				if ( !"R".equals(tipoReferencia) )
					listaResultados = ((ArrayList) SessionServiceLocator.getProductoSessionService().getProductoList(startIndex,endIndex,tipoReferencia, queryBuilded, idReferencia, tipoProducto, servicioConsumo, mmpg));
				else
					SpiritAlert.createAlert("No se puede buscar productos de Medios o Producci\u00f3n", SpiritAlert.ERROR);
			}
			if (listaResultados.size() > 0) {
				MultiComparator mc = new MultiComparator();
				mc.addComparator(genericoIdComparador);
				mc.addComparator(modeloIdComparador);
				mc.addComparator(presentacionIdComparador);
				mc.addComparator(colorIdComparador);
				Collections.sort(listaResultados, mc);
			}
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private Map buildQuery() {
		Map<String,Object> aMap = new HashMap<String,Object>();
		
		if (txtCodigo !=null && !txtCodigo.equals(""))
			aMap.put("codigo", txtCodigo + "%");
		else
			aMap.put("codigo", "%");
		
		if (txtNombreGenerico != null && !txtNombreGenerico.equals("") )
			aMap.put("nombreGenerico", txtNombreGenerico + "%");
		
		if (txtModelo != null && !txtModelo.equals("") )
			aMap.put("modelo", txtModelo + "%");

		if ( clienteOficinaIf != null )
			aMap.put("proveedorId",clienteOficinaIf.getId());
		
		return aMap;
	}
	
	/*public int getResultListSize(){
		//Obtengo el tamaño de los regsitros devueltos en la busqueda
		try {
			if(idFiltroBusqueda==0L){
				if (queryBuilded.size()==0)
					tamanoListaResultados = getProductoSessionService().getProductoListSize();
				else
					tamanoListaResultados = getProductoSessionService().getProductoListSize(queryBuilded);
			} else {
				if(queryBuilded.size()==0){
					Map filtroMap = new HashMap();
					filtroMap.put("proveedorId", idFiltroBusqueda);
					tamanoListaResultados = getProductoSessionService().getProductoListSize(filtroMap);
				} else {
					tamanoListaResultados = getProductoSessionService().getProductoListSize(tipoFiltro, queryBuilded, idFiltroBusqueda, idFiltroBusquedaString);
				}
			}
		} catch(Exception e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		} 
		return tamanoListaResultados;
	}*/
	
	public int getResultListSize() {
		try {
			if (queryBuilded != null)
				queryBuilded.put("estado", "A");
			if (mmpg == null)
				mmpg = "";
			if (buscarPorTexto)
				tamanoListaResultados =  SessionServiceLocator.getProductoSessionService().findProductoByCriterioMapSize(queryBuilded);
			else if(idReferencia==0L && "".equals(tipoProducto))
				tamanoListaResultados = SessionServiceLocator.getProductoSessionService().getProductoByQueryListSize(queryBuilded, Parametros.getIdEmpresa(), servicioConsumo, mmpg);
			else if (idReferencia==0L && !("".equals(nombreGenerico)))
				tamanoListaResultados = SessionServiceLocator.getProductoSessionService().getProductoByNombreGenericoListSize(nombreGenerico, servicioConsumo,clienteOficinaIf!=null?clienteOficinaIf.getId():null, mmpg);
			else if ("".equals(tipoProducto)) {
				queryBuilded.put("proveedorId", idReferencia);
				tamanoListaResultados = SessionServiceLocator.getProductoSessionService().getProductoByQueryListSize(queryBuilded, mmpg);
			} else{
				if ( !"R".equals(tipoReferencia) )
					tamanoListaResultados = SessionServiceLocator.getProductoSessionService().getProductoListSize(tipoReferencia, queryBuilded, idReferencia, tipoProducto, servicioConsumo, mmpg);
				else
					tamanoListaResultados = 0;
			}
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	public void setTxtParametros(String parametro1, String parametro2,String parametro3) {
		this.txtCodigo = parametro1;
		this.txtNombreGenerico = parametro2;
		this.txtModelo = parametro3;
		queryBuilded = buildQuery() ;
	}
	
	public void setTxtParametros(Map<String, Object> mapaParametros) {
		this.txtCodigo = (String)mapaParametros.get("parametro1");
		this.txtNombreGenerico = (String)mapaParametros.get("parametro2");
		this.txtModelo = (String)mapaParametros.get("parametro3");
		queryBuilded = buildQuery() ;
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
}
