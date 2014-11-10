package com.spirit.medios.gui.criteria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.util.GeneralUtil;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.medios.entity.ProductoClienteIf;

public class ProductoClienteCriteria extends Criteria{

	String nombrePanel = null;
	Long idFiltroBusqueda = 0L;
	Map queryBuilded = null;
	
	String txtCodigo = "";
	String txtNombre = "";
	String txtMarca = "";
	
	//ADD 26 JULIO
	Long marcaProductoId = 0L;
	//END 26 JULIO
	
	protected Map<Long, MarcaProductoIf> mapaMarcaProducto =  new HashMap<Long, MarcaProductoIf>();
	
	public ProductoClienteCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Productos de Clientes ";
	}
	
	public ProductoClienteCriteria(String nombrePanel, Long idFiltro, Long idMarcaProducto) {
		this.nombrePanel = nombrePanel;
		this.idFiltroBusqueda = idFiltro;
		this.marcaProductoId = idMarcaProducto;
	}
	
	public ProductoClienteCriteria( List listaResultados ){
		this.listaResultados =listaResultados;
	}
	
	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Marca");
		headers.add("Nombre");
		return headers;
	}

	Comparator<ProductoClienteIf> ordenadorProductoPorMarca = new Comparator<ProductoClienteIf>(){
		public int compare(ProductoClienteIf o1, ProductoClienteIf o2) {
			try {
				MarcaProductoIf marca1 = GeneralUtil.verificarMapaMarcaProducto(mapaMarcaProducto,o1.getMarcaProductoId());
				MarcaProductoIf marca2 = GeneralUtil.verificarMapaMarcaProducto(mapaMarcaProducto,o2.getMarcaProductoId());
				return (marca1.getNombre().compareTo(marca2.getNombre()));
			} catch(GenericBusinessException e) {
				e.printStackTrace();
			}
			return 0;
		}		
	};
	
	public List armarModel(List lista){
		try {
			ArrayList data = new ArrayList();
			Collections.sort(lista, ordenadorProductoPorMarca);
			Iterator it = lista.iterator();
	
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				ProductoClienteIf bean = (ProductoClienteIf) it.next();
				MarcaProductoIf marca = GeneralUtil.verificarMapaMarcaProducto(mapaMarcaProducto,bean.getMarcaProductoId());
				fila.add(bean.getCodigo());
				fila.add(marca.getNombre());
				fila.add(bean.getNombre());
				//ClienteIf cliente = getClienteSessionService().getCliente(bean.getClienteId());
				//fila.add(cliente.getNombreLegal());
				data.add(fila);
			}
			return data;			
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al leer la informacion en Producto!",SpiritAlert.ERROR);
		}
		
		return null;
	}
	
	public void buscarRegistros(int startIndex,int endIndex){
		try{
			if(idFiltroBusqueda==0L ){
				listaResultados = ( (ArrayList) SessionServiceLocator.getProductoClienteSessionService().findProductoClienteByQuery(
								startIndex,endIndex,queryBuilded) );
			}
			else if (marcaProductoId == 0L){//ADD 26 JULIO
				listaResultados = (ArrayList) SessionServiceLocator.getProductoClienteSessionService().findProductoClienteByQueryAndByClienteId(
						startIndex,endIndex,queryBuilded,idFiltroBusqueda);
			}//ADD 26 JULIO
			else {				
				listaResultados = (ArrayList)SessionServiceLocator.getProductoClienteSessionService().findProductoClienteByQueryAndByClienteIdAndMarcaProductoId(
						startIndex,endIndex,queryBuilded,idFiltroBusqueda,marcaProductoId);
			}//END 26 JULIO
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void setResultList(List listaResultados) {
		this.listaResultados =listaResultados;
	}
		
	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if (txtCodigo!=null && !("".equals(txtCodigo))) {
			aMap.put("codigo", txtCodigo+"%");
		}else
			aMap.put("codigo", "%");
		
		if (txtNombre!=null && !("".equals(txtNombre))) {
			aMap.put("nombre", txtNombre+"%");
		}else
			aMap.put("nombre", "%");
		
		if (txtMarca!=null && !("".equals(txtMarca))) {
			aMap.put("marcaProductoNombre", txtMarca+"%");
		}else
			aMap.put("marcaProductoNombre", "%");
						
		return aMap;
	}
	
	
	
	public int getResultListSize(){
		try{
			if(idFiltroBusqueda==0L){
				tamanoListaResultados = SessionServiceLocator.getProductoClienteSessionService().getProductoClienteListSize(queryBuilded);
			}
			else if (marcaProductoId == 0L){//ADD 26 JULIO
				tamanoListaResultados = SessionServiceLocator.getProductoClienteSessionService().findProductoClienteByQueryAndByClienteIdSize(
						queryBuilded,idFiltroBusqueda);
			}//ADD 26 JULIO
			else {				
				tamanoListaResultados = SessionServiceLocator.getProductoClienteSessionService().findProductoClienteByQueryAndByClienteIdAndMarcaProductoIdSize(
						queryBuilded,idFiltroBusqueda,marcaProductoId);
			}//END 26 JULIO
		}
		catch(GenericBusinessException e){
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}
	
	public void setTxtParametros(String parametro1, String parametro2,String parametro3) {
		this.txtCodigo = parametro1;
		this.txtMarca = parametro2;
		this.txtNombre = parametro3;
		queryBuilded = buildQuery() ;
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
 
}
