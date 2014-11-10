package com.spirit.compras.gui.criteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.compras.entity.CompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.util.GeneralUtil;
import com.spirit.util.Utilitarios;

public class CompraCriteria extends CompraCriteriaBase{

	Map queryBuilded;
	boolean all = true;
	
	private Object[] tipoDocumentos = null; 
	
	public CompraCriteria(boolean all){
		this.all = all;
		cargarTipoDocumentos();
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Compras";
	}
	
	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}

	public List getHeadersString() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Preimpreso");
		headers.add("Tipo de Documento");
		headers.add("Proveedor");
		headers.add("Fecha");
		headers.add("Estado");
		return headers;
	}
	
	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Preimpreso");
		headers.add(
			new Object[]{	
				"Tipo de Documento",
				tipoDocumentos
			}
		);
		headers.add("Proveedor");
		headers.add("Fecha");
		headers.add("Estado");
		return headers;
	}

	public List getModel() {
		ArrayList data = (ArrayList) armarModel(listaResultados);
		return data;
	}
	
	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				CompraIf bean = (CompraIf) it.next();

				fila.add(bean.getCodigo());
				fila.add(bean.getPreimpreso());
				fila.add(GeneralUtil.verificarMapaTipoDocumento(mapaTipoDocumento,bean.getTipodocumentoId()));
				fila.add(GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina,bean.getProveedorId()));
				fila.add(Utilitarios.getFechaCortaUppercase((Date)bean.getFecha()));
				String estado = bean.getEstado(); 
				fila.add(estado.equals("A")?"ACTIVA":"INACTIVA");
				data.add(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
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
			if (all)
				listaResultados = (ArrayList) SessionServiceLocator.getCompraSessionService().findCompraByQueryList(startIndex,endIndex,queryBuilded,Parametros.getIdEmpresa());
			else
				listaResultados = (ArrayList) SessionServiceLocator.getCompraSessionService().getCompraByQueryList(startIndex,endIndex,queryBuilded,Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		if ( queryBuilded != null && tamanoListaResultados > 0 ){
			try {
				if (all)
					this.tamanoListaResultados = SessionServiceLocator.getCompraSessionService().findCompraByQueryListSize(queryBuilded,Parametros.getIdEmpresa());
				else
					this.tamanoListaResultados = SessionServiceLocator.getCompraSessionService().getCompraByQueryListSize(queryBuilded,Parametros.getIdEmpresa());
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error general en la búsqueda de la compra !!", SpiritAlert.ERROR);
			}
		}
		return this.tamanoListaResultados;
	}
	
	public void setTxtParametros(String txtCodigo, String txtDescripcion,String parametro3) {
	}

	public void setTxtParametros(Map<String, Object> mapaParametros) {
		queryBuilded = new HashMap<String, Object>();
		String codigo = (String) mapaParametros.get("parametro1");
		if ( codigo!= null && !"".equals(codigo) ){
			queryBuilded.put("codigo", "%"+codigo+"%");
		} else {
			queryBuilded.put("codigo", "%");
		}
		String preimpreso = (String)mapaParametros.get("parametro2");
		if (preimpreso!= null && !"".equals(preimpreso)){
			queryBuilded.put("preimpreso", preimpreso+"%");
		} else {
			queryBuilded.put("preimpreso", "%");
		}
		TipoDocumentoIf td = (TipoDocumentoIf)mapaParametros.get("parametro3");
		if ( td!= null ){
			queryBuilded.put("tipodocumentoId", td.getId());
		} 
	}
	
	private void cargarTipoDocumentos() {
		try{
			Map<String, Object> mapaModulo = new HashMap<String, Object>();
			mapaModulo.put("nombre", "COMPRA%");
			Collection<ModuloIf> modulos = SessionServiceLocator.getModuloSessionService().findModuloByQuery(mapaModulo);
			ModuloIf modulo = null;
			if ( modulos.size() == 1 ){
				modulo = modulos.iterator().next();
			} else if ( modulos.size() == 0 ){
				throw new GenericBusinessException("No existe módulo con nombre Compra !!");
			} else if ( modulos.size() > 0 ){
				throw new GenericBusinessException("Existe mas de un módulo con nombre Compra !!");
			}
			
			Map<String, Object> mapaTipoDoc = new HashMap<String, Object>();
			mapaTipoDoc.put("empresaId", Parametros.getIdEmpresa());
			mapaTipoDoc.put("estado", "A");
			mapaTipoDoc.put("moduloId", modulo.getId());
			Collection<TipoDocumentoIf> tipos = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQuery(mapaTipoDoc);
			tipoDocumentos = new Object[tipos.size()+1];
			tipoDocumentos[0] = null;
			int contador = 1;
			for (TipoDocumentoIf td : tipos){
				tipoDocumentos[contador] = td;
				contador++;
			}
		} catch (GenericBusinessException e) {
			tipoDocumentos = new Object[]{null};
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (Exception e) {
			tipoDocumentos = new Object[]{null};
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar los tipos Documentos !!", SpiritAlert.ERROR);
		}
	}
	
	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
}
