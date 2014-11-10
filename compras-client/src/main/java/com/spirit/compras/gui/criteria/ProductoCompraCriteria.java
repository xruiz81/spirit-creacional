package com.spirit.compras.gui.criteria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModel;
import com.spirit.compras.session.ProductoBusquedaSessionService;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.crm.session.ClienteOficinaSessionService;
import com.spirit.crm.session.ClienteSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.PopupFinderActionListener;
import com.spirit.general.util.GeneralUtil;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.GenericoSessionService;
import com.spirit.inventario.session.PresentacionSessionService;
import com.spirit.inventario.session.ProductoSessionService;
import com.spirit.servicelocator.ServiceLocator;

public class ProductoCompraCriteria extends Criteria{

	Long idReferencia=0L;
	String tipoProducto = "";
	String nombreGenerico = "";
	String tipoReferencia = "";
	String servicioConsumo = "";
	String mmpg;
	Map queryBuilded = null;
	boolean filtrarProveedor = false;
	
	ClienteOficinaIf clienteOficinaIf = null;
	
	String txtCodigo,txtNombreGenerico,txtPresentacion;
	
	protected Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	protected Map<Long, GenericoIf> mapaGenerico = new HashMap<Long, GenericoIf>();
	protected Map<Long, PresentacionIf> mapaPresentacion = new HashMap<Long, PresentacionIf>();
	
	public ProductoCompraCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Productos de Compras";
	}
	
	/*public ProductoCompraCriteria(Long idReferencia) {
		this.idReferencia = idReferencia;
	}
	
	public ProductoCompraCriteria(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}*/
	
	public ProductoCompraCriteria(String nombrePanel, Long idReferencia, String filtro, String tipoReferencia, String servicioConsumo, boolean filtrarProveedor, String mmpg) {
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
	}
	
	/*public ProductoCriteria(SpiritModel model,String nombrePanel,
			Long idFiltroReferencia, String idFiltro, String tipo) {
		this.modelForm = model;
		this.nombrePanel = nombrePanel;
		this.idFiltroBusqueda = idFiltroReferencia;
		this.idFiltroBusquedaString = idFiltro;
		this.tipoFiltro = tipo;
	}*/
	
	public ProductoCompraCriteria( List listaResultados ){
		this.listaResultados =listaResultados;
	}
	
	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}
	
	PopupFinderActionListener al =  new PopupFinderActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Long idCorporacion = 0L;
			Long idCliente = 0L;
			ClienteOficinaCriteria clienteOficinaCriteria = new ClienteOficinaCriteria("Oficinas del Cliente", idCorporacion, idCliente, "PR", "", false);
			JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if (popupFinder.getElementoSeleccionado() != null) {
				clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
				al.getTxtField().setText(clienteOficinaIf.getDescripcion());
			}
		}
	};

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Nombre Genérico");
		headers.add("Presentación");
		if ( filtrarProveedor )
			headers.add(new Object[]{"Proveedor",al});
		else 
			headers.add("Proveedor");
		return headers;
	}
	
	@Override
	public List getHeadersString() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Nombre Genérico");
		headers.add("Presentación");
		if ( filtrarProveedor )
			headers.add("Proveedor");
		return headers;
	}
	
	public List getHeadersCorto() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Nombre Genérico");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		try {
			Iterator it = lista.iterator();
	
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				ProductoIf bean = (ProductoIf) it.next();
				fila.add((bean.getCodigoBarras()!=null && !bean.getCodigoBarras().trim().equals(""))?bean.getCodigoBarras():bean.getCodigo());
				GenericoIf generico = GeneralUtil.verificarMapaGenerico(mapaGenerico,bean.getGenericoId());
				fila.add(generico.getNombre());
				
				if (bean.getPresentacionId()!=null) {
					PresentacionIf presentacion = GeneralUtil.verificarMapaPresentacion(mapaPresentacion,bean.getPresentacionId());
					fila.add(presentacion.getNombre());
				} else
					fila.add("");
				
				if (bean.getProveedorId() != null) {
					ClienteOficinaIf proveedor = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina,bean.getProveedorId());
					//fila.add(((ClienteIf) getClienteSessionService().findClienteByClienteOficinaId(proveedor.getId()).iterator().next()).getNombreLegal());
					fila.add(proveedor.getDescripcion());
				} else
					fila.add("NO TIENE PROVEEDOR");
				
				data.add(fila);
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
			if(idReferencia==0L && "".equals(tipoProducto))
				listaResultados = ((ArrayList) SessionServiceLocator.getProductoSessionService().findProductoByQuery(startIndex,endIndex,queryBuilded,Parametros.getIdEmpresa(),servicioConsumo,mmpg));
			else if (idReferencia==0L && !("".equals(nombreGenerico))){
				listaResultados = ((ArrayList) SessionServiceLocator.getProductoSessionService().findProductoByNombreGenerico(startIndex,endIndex,nombreGenerico,servicioConsumo,clienteOficinaIf!=null?clienteOficinaIf.getId():null,mmpg));
			}
			else if ("".equals(tipoProducto)) {
				queryBuilded.put("proveedorId", idReferencia);
				listaResultados = ((ArrayList) SessionServiceLocator.getProductoSessionService().findProductoByQuery(startIndex,endIndex,queryBuilded,mmpg));
			} else
				listaResultados = ((ArrayList) SessionServiceLocator.getProductoBusquedaSessionService().getProductoList(startIndex,endIndex,tipoReferencia, queryBuilded, idReferencia, tipoProducto, servicioConsumo,mmpg));
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if (txtCodigo !=null && !txtCodigo.equals(""))
			aMap.put("codigo", txtCodigo + "%");
		else
			aMap.put("codigo", "%");
		
		if (txtNombreGenerico != null && !txtNombreGenerico.equals("") )
			aMap.put("nombreGenerico", txtNombreGenerico + "%");
		
		if (txtPresentacion != null && !txtPresentacion.equals("") )
			aMap.put("presentacion", txtPresentacion + "%");
		
		if ( clienteOficinaIf != null )
			aMap.put("proveedorId",clienteOficinaIf.getId());
		
		return aMap;
	}
	
	public int getResultListSize() {
		try {
			if(idReferencia==0L && "".equals(tipoProducto))
				tamanoListaResultados = SessionServiceLocator.getProductoSessionService().getProductoByQueryListSize(queryBuilded, Parametros.getIdEmpresa(), servicioConsumo, mmpg);
			else if (idReferencia==0L && !("".equals(nombreGenerico)))
				tamanoListaResultados = SessionServiceLocator.getProductoSessionService().getProductoByNombreGenericoListSize(nombreGenerico, servicioConsumo,clienteOficinaIf!=null?clienteOficinaIf.getId():null, mmpg);
			else if ("".equals(tipoProducto)) {
				queryBuilded.put("proveedorId", idReferencia);
				tamanoListaResultados = SessionServiceLocator.getProductoSessionService().getProductoByQueryListSize(queryBuilded, mmpg);
			} else
				tamanoListaResultados = SessionServiceLocator.getProductoBusquedaSessionService().getProductoListSize(tipoReferencia, queryBuilded, idReferencia, tipoProducto, servicioConsumo, mmpg);
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	public void setTxtParametros(String parametro1, String parametro2,String parametro3) {
		this.txtCodigo = parametro1;
		this.txtNombreGenerico = parametro2;
		this.txtPresentacion = parametro3;
		queryBuilded = buildQuery() ;
	}
	
	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
 
}
