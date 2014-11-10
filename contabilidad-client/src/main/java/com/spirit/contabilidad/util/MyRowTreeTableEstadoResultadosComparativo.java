package com.spirit.contabilidad.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.jidesoft.grid.AbstractExpandableRow;
import com.jidesoft.grid.Row;
import com.spirit.client.SpiritAlert;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.contabilidad.entity.TipoResultadoIf;
import com.spirit.util.Utilitarios;

//Clase que va a setear las subActividades del del Estado de Flujo de Efectivo
public class MyRowTreeTableEstadoResultadosComparativo extends AbstractExpandableRow implements Comparable {
	private List _hijos;
	private transient String _nombre;
	private transient Long _id;
	private transient String _valor;
	private transient String _margen;
	private transient String _porcentaje;
	private transient boolean _esHoja;
	private Map filtroBusqueda;
	private transient String _comparativo;
	private transient String _margenComparativo;
	private transient String _porcentajeComparativo;
	private transient String _diferencia;
	private transient int _nivelesVisibles;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private static java.sql.Date fechaInicio;
	private static java.sql.Date fechaFin;
	private static java.sql.Date fechaInicioComparativo;
	private static java.sql.Date fechaFinComparativo;
	private static Vector<CuentaIf> cuentasEstadoResultadosTreeTable;
	private static LinkedHashMap cuentasEstadoResultadosMap;
	private static Vector<CuentaIf> cuentasEstadoResultadosComparativoTreeTable;
	private static LinkedHashMap cuentasEstadoResultadosComparativoMap;
	private static Map tiposCuentaMap;
	
	public MyRowTreeTableEstadoResultadosComparativo(Object objeto, String tabla, String valor ,boolean esHoja, Map filtro, String margen, String porcentaje, String comparativo, String margenComparativo, String porcentajeComparativo, String diferencia, int nivelesVisibles) {
		
		filtroBusqueda = filtro;
		//El constructor setea los valores segun la palabra clave que se le envie
		if(tabla.equals("TipoResultado")){
			_nombre = ((TipoResultadoIf)objeto).getNombre();
			_id = ((TipoResultadoIf)objeto).getId();
			Double saldo = Double.parseDouble(Utilitarios.removeDecimalFormat(valor));
			_valor = formatoDecimal.format(saldo);
			Double saldoComparativo = Double.parseDouble(Utilitarios.removeDecimalFormat(comparativo));
			_comparativo = formatoDecimal.format(saldoComparativo);
			_diferencia = diferencia;
			_nivelesVisibles = nivelesVisibles;
		} else if(tabla.equals("Cuenta")){
			_nombre = "[" + ((CuentaIf)objeto).getCodigo() + "] " + ((CuentaIf)objeto).getNombre();
			_id = ((CuentaIf)objeto).getId();
			TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(((CuentaIf)objeto).getTipocuentaId());
			Double saldo = Double.parseDouble(Utilitarios.removeDecimalFormat(valor));
			if (saldo != null && tipoCuenta.getDebehaber().equals("H"))
				saldo = saldo * -1D;
			_valor = formatoDecimal.format(saldo);
			Double saldoComparativo = Double.parseDouble(Utilitarios.removeDecimalFormat(comparativo));
			if (saldoComparativo != null && tipoCuenta.getDebehaber().equals("H"))
				saldoComparativo = saldoComparativo * -1D;
			_comparativo = formatoDecimal.format(saldoComparativo);
			_diferencia = diferencia;
			_nivelesVisibles = nivelesVisibles;
		} else if(tabla.equals("CuentaPadre")) {
			_nombre = "[" + ((CuentaIf)objeto).getCodigo() + "] " + ((CuentaIf)objeto).getNombre();
			_id = ((CuentaIf)objeto).getId();
			TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(((CuentaIf)objeto).getTipocuentaId());
			Double saldo = Double.parseDouble(Utilitarios.removeDecimalFormat(valor));
			if (saldo != null && tipoCuenta.getDebehaber().equals("H"))
				saldo = saldo * -1D;
			_valor = formatoDecimal.format(saldo);
			Double saldoComparativo = Double.parseDouble(Utilitarios.removeDecimalFormat(comparativo));
			if (saldoComparativo != null && tipoCuenta.getDebehaber().equals("H"))
				saldoComparativo = saldoComparativo * -1D;
			_comparativo = formatoDecimal.format(saldoComparativo);
			_diferencia = diferencia;
			_nivelesVisibles = nivelesVisibles;
		} else if (tabla.equals("Total")){
			_nombre = (String) objeto;
			_valor = valor;
			_comparativo = comparativo;
			_margen = margen;
			_margenComparativo = margenComparativo;
			_porcentaje = porcentaje;
			_porcentajeComparativo = porcentajeComparativo;
			_diferencia = diferencia;
			_nivelesVisibles = nivelesVisibles;
		}
				
		_esHoja = esHoja;
	}
	
	public boolean esHoja() {
		return _esHoja;
	}
	
	//Se obtiene los valores de cada una de las columnas
	public Object getValueAt(int columnIndex) {
		try {
			switch (columnIndex) {
			case 0:
				return getNombre();
			case 1:
				return get_valor();
			case 2:
				return get_margen();
			case 3:
				return get_porcentaje();
			case 4:
				return get_comparativo();
			case 5:
				return get_margenComparativo();
			case 6:
				return get_porcentajeComparativo();
			case 7:
				return get_diferencia();
			}
		}
		catch (SecurityException se) {
		}
		return null;
	}
	
	public Class getCellClassAt(int columnIndex) {
		return null;
	}
	
	//Setear nodos hijos y padres
	public void setChildren(List hijos) {
		_hijos = hijos;
		if (_hijos != null) {
			for (int i = 0; i < _hijos.size(); i++) {
				Row row = (Row) _hijos.get(i);
				row.setParent(this);
			}
		}
	}
	
	//Ver si tiene hijos o es hoja
	public boolean hasChildren() {
		return !_esHoja;
	}
	
	//Función principal que obtiene las subactivades de cada una las actividades
	//del Flujo de Efectivo
	public List getChildren() {
		Double totalDebeHaber = 0D;
		Double totalDebeHaberComparativo = 0D;
		
		if (_hijos != null)
			return _hijos;
		
		try {
			if (!_esHoja) {
				List hijos = new ArrayList();
				ArrayList hijosHoja = new ArrayList();
				Double diferenciaPorcentaje = 0D;
				Vector<CuentaIf> cuentasHijas = findCuentasTreeTableByPadreId(getId());
				Iterator itCuentasHijas = cuentasHijas.iterator();
				
				while(itCuentasHijas.hasNext()){
					CuentaIf cuentaHijaIf = (CuentaIf) itCuentasHijas.next();
					if (cuentaHijaIf.getNivel() <= _nivelesVisibles) {
						String sangria = "";
						for (int i=1; i<cuentaHijaIf.getNivel(); i++)
							sangria += "     ";
						//Coleccion de todas las cuentas "nietas" de las cuentas padre principal
						Collection losHijos = findCuentasTreeTableByPadreId(cuentaHijaIf.getId());
						MyRowTreeTableEstadoResultadosComparativo fila = null;
						
						Double saldo = (getCuentasEstadoResultadosMap().get(cuentaHijaIf.getId()) != null)?(Double) getCuentasEstadoResultadosMap().get(cuentaHijaIf.getId()):0D;
						Double saldoComparativo = (getCuentasEstadoResultadosComparativoMap().get(cuentaHijaIf.getId()) != null)?(Double) getCuentasEstadoResultadosComparativoMap().get(cuentaHijaIf.getId()):0D;
						diferenciaPorcentaje = ((saldo - saldoComparativo)/saldoComparativo)*100;
						
						if (losHijos.size()>0) {
							if (saldo != null && (Utilitarios.redondeoValor(saldo) > 0D || Utilitarios.redondeoValor(saldo) < 0D))
								//fila = new MyRowTreeTableEstadoResultadosComparativo(cuentaHijaIf, "CuentaPadre", formatoDecimal.format(saldo) + sangria, false, filtroBusqueda, null, null, formatoDecimal.format(saldoComparativo) + sangria, null, null, formatoDecimal.format(diferenciaPorcentaje) + "%" + sangria, _nivelesVisibles);
								fila = new MyRowTreeTableEstadoResultadosComparativo(cuentaHijaIf, "CuentaPadre", formatoDecimal.format(saldo) + sangria, false, filtroBusqueda, null, null, formatoDecimal.format(saldoComparativo) + sangria, null, null, "" + sangria, _nivelesVisibles);
						} else if (saldo != null && (Utilitarios.redondeoValor(saldo) > 0D || Utilitarios.redondeoValor(saldo) < 0D)) {
							//fila = new MyRowTreeTableEstadoResultadosComparativo(cuentaHijaIf, "Cuenta", formatoDecimal.format(saldo) + sangria, false, filtroBusqueda, null, null, formatoDecimal.format(saldoComparativo) + sangria, null, null, formatoDecimal.format(diferenciaPorcentaje) + "%" + sangria, _nivelesVisibles);
							fila = new MyRowTreeTableEstadoResultadosComparativo(cuentaHijaIf, "Cuenta", formatoDecimal.format(saldo) + sangria, false, filtroBusqueda, null, null, formatoDecimal.format(saldoComparativo) + sangria, null, null, "" + sangria, _nivelesVisibles);
						}
						
						if (fila != null) {
							if (fila.esHoja())
								hijosHoja.add(fila);
							else
								hijos.add(fila);
						}
					}
					
				}
				
				hijos.addAll(hijosHoja);
				setChildren(hijos);
			}
		} catch (SecurityException se) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			se.printStackTrace();
		}
		
		return _hijos;
	}
	
	public int compareTo(Object o) {
		if (o instanceof MyRowTreeTableEstadoResultadosComparativo) {
			MyRowTreeTableEstadoResultadosComparativo fileRow = (MyRowTreeTableEstadoResultadosComparativo) o;
			return getNombre().compareToIgnoreCase(fileRow.getNombre());
		}
		return 0;
	}
	
	private Vector<CuentaIf> findCuentasTreeTableByPadreId(Long padreId) {
		Vector<CuentaIf> cuentasHijasVector = new Vector<CuentaIf>();
		for (int i=0; i<getCuentasEstadoResultadosTreeTable().size(); i++) {
			CuentaIf cuentaHijaIf = getCuentasEstadoResultadosTreeTable().get(i);
			if (cuentaHijaIf.getPadreId() != null)
				if (cuentaHijaIf.getPadreId().compareTo(padreId) == 0)
					cuentasHijasVector.add(cuentaHijaIf);
		}
		
		return cuentasHijasVector;
	}
	
	public String getNombre() {
		return _nombre;
	}
	
	public void setNombre(String _nombre) {
		this._nombre = _nombre;
	}
	
	public Long getId() {
		return _id;
	}
	
	public void setId(Long _id) {
		this._id = _id;
	}
	
	public String get_valor() {
		return _valor;
	}
	
	public void set_valor(String _valor) {
		this._valor = _valor;
	}
	
	public String get_comparativo() {
		return _comparativo;
	}

	public void set_comparativo(String _comparativo) {
		this._comparativo = _comparativo;
	}

	public static java.sql.Date getFechaFinComparativo() {
		return fechaFinComparativo;
	}
	
	public static void setFechaFinComparativo(java.sql.Date fechaFinComparativo) {
		MyRowTreeTableEstadoResultadosComparativo.fechaFinComparativo = fechaFinComparativo;
	}
	
	public static java.sql.Date getFechaInicioComparativo() {
		return fechaInicioComparativo;
	}
	
	public static void setFechaInicioComparativo(
			java.sql.Date fechaInicioComparativo) {
		MyRowTreeTableEstadoResultadosComparativo.fechaInicioComparativo = fechaInicioComparativo;
	}
	
	public static java.sql.Date getFechaFin() {
		return fechaFin;
	}
	
	public static void setFechaFin(java.sql.Date fechaFin) {
		MyRowTreeTableEstadoResultadosComparativo.fechaFin = fechaFin;
	}
	
	public static java.sql.Date getFechaInicio() {
		return fechaInicio;
	}
	
	public static void setFechaInicio(java.sql.Date fechaInicio) {
		MyRowTreeTableEstadoResultadosComparativo.fechaInicio = fechaInicio;
	}
	
	public String get_margen() {
		return _margen;
	}
	
	public void set_margen(String _margen) {
		this._margen = _margen;
	}
	
	public String get_margenComparativo() {
		return _margenComparativo;
	}

	public void set_margenComparativo(String comparativo) {
		_margenComparativo = comparativo;
	}

	public String get_porcentaje() {
		return _porcentaje;
	}
	
	public void set_porcentaje(String _porcentaje) {
		this._porcentaje = _porcentaje;
	}
	
	public String get_porcentajeComparativo() {
		return _porcentajeComparativo;
	}

	public void set_porcentajeComparativo(String comparativo) {
		_porcentajeComparativo = comparativo;
	}

	public String get_diferencia() {
		return _diferencia;
	}
	
	public void set_diferencia(String _diferencia) {
		this._diferencia = _diferencia;
	}
	
	public static LinkedHashMap getCuentasEstadoResultadosComparativoMap() {
		return cuentasEstadoResultadosComparativoMap;
	}
	
	public static void setCuentasEstadoResultadosComparativoMap(
			LinkedHashMap cuentasEstadoResultadosComparativoMap) {
		MyRowTreeTableEstadoResultadosComparativo.cuentasEstadoResultadosComparativoMap = cuentasEstadoResultadosComparativoMap;
	}
	
	public static Vector<CuentaIf> getCuentasEstadoResultadosComparativoTreeTable() {
		return cuentasEstadoResultadosComparativoTreeTable;
	}
	
	public static void setCuentasEstadoResultadosComparativoTreeTable(
			Vector<CuentaIf> cuentasEstadoResultadosComparativoTreeTable) {
		MyRowTreeTableEstadoResultadosComparativo.cuentasEstadoResultadosComparativoTreeTable = cuentasEstadoResultadosComparativoTreeTable;
	}
	
	public static LinkedHashMap getCuentasEstadoResultadosMap() {
		return cuentasEstadoResultadosMap;
	}
	
	public static void setCuentasEstadoResultadosMap(
			LinkedHashMap cuentasEstadoResultadosMap) {
		MyRowTreeTableEstadoResultadosComparativo.cuentasEstadoResultadosMap = cuentasEstadoResultadosMap;
	}
	
	public static Vector<CuentaIf> getCuentasEstadoResultadosTreeTable() {
		return cuentasEstadoResultadosTreeTable;
	}
	
	public static void setCuentasEstadoResultadosTreeTable(
			Vector<CuentaIf> cuentasEstadoResultadosTreeTable) {
		MyRowTreeTableEstadoResultadosComparativo.cuentasEstadoResultadosTreeTable = cuentasEstadoResultadosTreeTable;
	}
	
	public static Map getTiposCuentaMap() {
		return tiposCuentaMap;
	}
	
	public static void setTiposCuentaMap(Map tiposCuentaMap) {
		MyRowTreeTableEstadoResultadosComparativo.tiposCuentaMap = tiposCuentaMap;
	}
}
