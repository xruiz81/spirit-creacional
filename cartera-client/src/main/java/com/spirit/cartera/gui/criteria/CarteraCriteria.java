package com.spirit.cartera.gui.criteria;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.SwingConstants;

import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.util.GeneralUtil;
import com.spirit.util.Utilitarios;

public class CarteraCriteria extends CarteraCriteriaBase {
	Map queryBuilded;
	Long clienteId,moduloId,empresaId;
	private static final String NOMBRE_ESTADO_NORMAL = "NORMAL";
	private static final String ESTADO_NORMAL = NOMBRE_ESTADO_NORMAL.substring(0,1);
	private static final String NOMBRE_ESTADO_ANULADO = "ANULADO";
	private static final String ESTADO_ANULADO = NOMBRE_ESTADO_ANULADO.substring(0, 1);
	private static final String NOMBRE_ESTADO_DUDOSO = "DUDOSO";
	private static final String ESTADO_DUDOSO = NOMBRE_ESTADO_DUDOSO.substring(0,1);
	private static final String NOMBRE_ESTADO_CASTIGADO = "CASTIGADO";
	private static final String ESTADO_CASTIGADO = NOMBRE_ESTADO_CASTIGADO.substring(0,1);
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private static final int ANCHO_COLUMNA_CODIGO = 70;
	private static final int ANCHO_COLUMNA_FECHA_EMISION = 50;
	private static final int ANCHO_COLUMNA_CLIENTE = 140;
	private static final int ANCHO_COLUMNA_DESCRIPCION = 180;
	private static final int ANCHO_COLUMNA_TOTAL = 40;
	private static final int ANCHO_COLUMNA_SALDO = 40; 
	private static final int INDICE_COLUMNA_CODIGO = 0;
	private static final int INDICE_COLUMNA_FECHA_EMISION = 1;
	private static final int INDICE_COLUMNA_CLIENTE = 2;
	private static final int INDICE_COLUMNA_DESCRIPCION = 3;
	private static final int INDICE_COLUMNA_TOTAL = 4;
	private static final int INDICE_COLUMNA_SALDO = 5; 
	private Vector<Integer> anchoColumnasVector = new Vector<Integer>();
	Map<Integer, Integer> alineacionColumnasMap = new HashMap<Integer, Integer>();
	
	public CarteraCriteria(){
		initFinderTblProperties();
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "";
	}
	
	public CarteraCriteria(List listaResultados){
		this.listaResultados = listaResultados;
		initFinderTblProperties();
	}
	
	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}
	
	public CarteraCriteria(Long clienteId,Long moduloId,Long empresaId) {
		this.clienteId = clienteId;
		this.moduloId = moduloId;
		this.empresaId = empresaId;
		initFinderTblProperties();
	}
	
	public void initFinderTblProperties() {
		getAnchoColumnasVector().addElement(getAnchoColumnaCodigo());
		getAnchoColumnasVector().addElement(getAnchoColumnaFechaEmision());
		getAnchoColumnasVector().addElement(getAnchoColumnaCliente());
		getAnchoColumnasVector().addElement(getAnchoColumnaDescripcion());
		getAnchoColumnasVector().addElement(getAnchoColumnaTotal());
		getAnchoColumnasVector().addElement(getAnchoColumnaSaldo());
		getAlineacionColumnasMap().put(getIndiceColumnaCodigo(), SwingConstants.CENTER);
		getAlineacionColumnasMap().put(getIndiceColumnaFechaEmision(), SwingConstants.CENTER);
		getAlineacionColumnasMap().put(getIndiceColumnaTotal(), SwingConstants.RIGHT);
		getAlineacionColumnasMap().put(getIndiceColumnaSaldo(), SwingConstants.RIGHT);
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("F. Emisión");
		headers.add("Cliente");
		headers.add("Descripción");
		headers.add("Total");
		headers.add("Saldo");
		return headers;
	}
	
	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();
		try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				CarteraIf bean = (CarteraIf) it.next();
				int indiceInicial = bean.getCodigo().indexOf(SpiritConstants.getPlaceholderCharacter()) + 1;
				fila.add(bean.getCodigo().substring(indiceInicial, bean.getCodigo().length()));
				fila.add(bean.getFechaEmision()!=null ? Utilitarios.getFechaCortaUppercase(bean.getFechaEmision()) : "");
				ClienteOficinaIf clienteOficina = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina, bean.getClienteoficinaId());
				ClienteIf cliente = GeneralUtil.verificarMapaCliente(mapaCliente,clienteOficina.getClienteId());
				fila.add(cliente.getNombreLegal()!=null?cliente.getNombreLegal():"");
				String descripcion = obtenerDescripcionCartera(bean);
				fila.add(descripcion);
				fila.add(bean.getValor()!=null?formatoDecimal.format(bean.getValor()):"error");
				fila.add(bean.getSaldo()!=null?formatoDecimal.format(bean.getSaldo()):"error");
	            data.add(fila);
			
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
		return data;
	}
	
	@SuppressWarnings("unchecked")
	private String obtenerDescripcionCartera(CarteraIf cartera) throws GenericBusinessException {
		String descripcion = SpiritConstants.getEmptyCharacter();
		Iterator<CarteraDetalleIf> it = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(cartera.getId()).iterator();
		while (it.hasNext()) {
			CarteraDetalleIf carteraDetalle = it.next();
			descripcion += carteraDetalle.getObservacion() + SpiritConstants.getSemicolonCharacter() + SpiritConstants.getBlankSpaceCharacter();
		}
		if (descripcion.equals(SpiritConstants.getEmptyCharacter()))
			descripcion = "SIN DESCRIPCIÓN";
		return descripcion;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}
	
	public List getListaResultados() {
		return listaResultados;
	}
	
	public void buscarRegistros(int startIndex,int endIndex){
		try{
			listaResultados = (ArrayList)SessionServiceLocator.getCarteraSessionService().getCarteraList(startIndex,endIndex,queryBuilded, clienteId, moduloId, empresaId);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		return this.tamanoListaResultados;
	}

	public void setTxtParametros(String txtCodigo, String txtDescripcion,String parametro3) {
	}

	public String getNombrePanel() {
		return nombrePanel;
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}

	public Vector<Integer> getAnchoColumnasVector() {
		return anchoColumnasVector;
	}

	public void setAnchoColumnasVector(Vector<Integer> anchoColumnasVector) {
		this.anchoColumnasVector = anchoColumnasVector;
	}

	public Map<Integer, Integer> getAlineacionColumnasMap() {
		return alineacionColumnasMap;
	}

	public void setAlineacionColumnasMap(Map<Integer, Integer> alineacionColumnasMap) {
		this.alineacionColumnasMap = alineacionColumnasMap;
	}

	public static int getIndiceColumnaCodigo() {
		return INDICE_COLUMNA_CODIGO;
	}

	public static int getIndiceColumnaFechaEmision() {
		return INDICE_COLUMNA_FECHA_EMISION;
	}

	public static int getIndiceColumnaCliente() {
		return INDICE_COLUMNA_CLIENTE;
	}

	public static int getIndiceColumnaDescripcion() {
		return INDICE_COLUMNA_DESCRIPCION;
	}

	public static int getIndiceColumnaTotal() {
		return INDICE_COLUMNA_TOTAL;
	}

	public static int getIndiceColumnaSaldo() {
		return INDICE_COLUMNA_SALDO;
	}

	public static int getAnchoColumnaCodigo() {
		return ANCHO_COLUMNA_CODIGO;
	}

	public static int getAnchoColumnaFechaEmision() {
		return ANCHO_COLUMNA_FECHA_EMISION;
	}

	public static int getAnchoColumnaCliente() {
		return ANCHO_COLUMNA_CLIENTE;
	}

	public static int getAnchoColumnaDescripcion() {
		return ANCHO_COLUMNA_DESCRIPCION;
	}

	public static int getAnchoColumnaTotal() {
		return ANCHO_COLUMNA_TOTAL;
	}

	public static int getAnchoColumnaSaldo() {
		return ANCHO_COLUMNA_SALDO;
	}
}
