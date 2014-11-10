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
public class MyRowTreeTableEstadoResultados extends AbstractExpandableRow implements Comparable {
	private List _hijos;
	private transient String _etiqueta;
	private transient String _tabla;
	private transient String _nombre;
	private transient Long _id;
	private transient TipoCuentaIf _tipoCuenta;
	private transient String _valor;
	private transient Map _saldosMap;
	private transient Map _saldosRealesMap;
	private transient String _margen;
	private transient String _porcentaje;
	private transient int _nivelesVisibles;
	private transient boolean _esHoja;
	private transient boolean _acumulado;
	private Map filtroBusqueda;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private static java.sql.Date fechaInicio;
	private static java.sql.Date fechaFin;
	private static Vector<CuentaIf> cuentasEstadoResultadosTreeTable;
	private static LinkedHashMap cuentasEstadoResultadosMap;
	private static Map tiposCuentaMap;
	
	public MyRowTreeTableEstadoResultados(Object objeto, String tabla, String valor, int nivelesVisibles, boolean esHoja, Map filtro, String margen, String porcentaje, boolean acumulado) {
		if (objeto instanceof String)
			_etiqueta = (String) objeto;
		if (objeto instanceof CuentaIf)
			_etiqueta = ((CuentaIf) objeto).getCodigo();
		filtroBusqueda = filtro;
		_acumulado = acumulado;
		_tabla = tabla;
		//El constructor setea los valores segun la palabra clave que se le envie
		if (tabla.equals("TipoResultado") && objeto != null) {
			_nombre = ((TipoResultadoIf)objeto).getNombre();
			_id = ((TipoResultadoIf)objeto).getId();
			Double saldo = Double.parseDouble(Utilitarios.removeDecimalFormat(valor));
			_valor = formatoDecimal.format(saldo);
			_nivelesVisibles = nivelesVisibles;
		} else if(tabla.equals("Cuenta")){
			_nombre = "[" + ((CuentaIf)objeto).getCodigo() + "] " + ((CuentaIf)objeto).getNombre();
			_id = ((CuentaIf)objeto).getId();
			TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(((CuentaIf)objeto).getTipocuentaId());
			Double saldo = Double.parseDouble(Utilitarios.removeDecimalFormat(valor));
			//if (saldo != null && tipoCuenta.getDebehaber().equals("H"))
			if (saldo != null && !tipoCuenta.getDebehaber().equals("H"))
				saldo = saldo * -1D;
			_valor = formatoDecimal.format(saldo);
			_nivelesVisibles = nivelesVisibles;
		} else if(tabla.equals("CuentaPadre")) {
			_nombre = "[" + ((CuentaIf)objeto).getCodigo() + "] " + ((CuentaIf)objeto).getNombre();
			_id = ((CuentaIf)objeto).getId();
			TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(((CuentaIf)objeto).getTipocuentaId());
			Double saldo = Double.parseDouble(Utilitarios.removeDecimalFormat(valor));
			//if (saldo != null && tipoCuenta.getDebehaber().equals("H"))
			if (saldo != null && !tipoCuenta.getDebehaber().equals("H"))
				saldo = saldo * -1D;
			_valor = formatoDecimal.format(saldo);
			_nivelesVisibles = nivelesVisibles;
		} else if (tabla.equals("Total")) {
			//---_nombre = (String) objeto;
			//---_valor = valor;
			//---_margen = margen;
			_nombre = valor;
			_valor = margen;
			_porcentaje = porcentaje;
			_nivelesVisibles = nivelesVisibles;
		}
		
		_esHoja = esHoja;
	}
	
	public MyRowTreeTableEstadoResultados(Object objeto, String tabla, Map saldosMap, Map saldosRealesMap, int nivelesVisibles, boolean esHoja, Map filtro, String margen, String porcentaje, boolean acumulado, Long tipoCuentaId) {
		if (objeto instanceof String)
			_etiqueta = (String) objeto;
		if (objeto instanceof CuentaIf)
			_etiqueta = ((CuentaIf) objeto).getCodigo();
		filtroBusqueda = filtro;
		_acumulado = acumulado;
		_tabla = tabla;
		//El constructor setea los valores segun la palabra clave que se le envie
		if(tabla.equals("Cuenta")){
			_nombre = "[" + ((CuentaIf)objeto).getCodigo() + "] " + ((CuentaIf)objeto).getNombre();
			_id = ((CuentaIf)objeto).getId();
			_tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(((CuentaIf)objeto).getTipocuentaId());
			_saldosMap = saldosMap;
			_saldosRealesMap = saldosRealesMap;
			_nivelesVisibles = nivelesVisibles;
		} else if(tabla.equals("CuentaPadre")) {
			_nombre = "[" + ((CuentaIf)objeto).getCodigo() + "] " + ((CuentaIf)objeto).getNombre();
			_id = ((CuentaIf)objeto).getId();
			_tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(((CuentaIf)objeto).getTipocuentaId());
			_saldosMap = saldosMap;
			_saldosRealesMap = saldosRealesMap;
			_nivelesVisibles = nivelesVisibles;
		} else if (tabla.equals("Total")) {
			_nombre = (String) objeto;
			_saldosMap = saldosMap;
			_saldosRealesMap = saldosRealesMap;
			_tipoCuenta = (tipoCuentaId.compareTo(0L) != 0)?(TipoCuentaIf) tiposCuentaMap.get(tipoCuentaId):null;
			_margen = margen;
			_porcentaje = porcentaje;
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
			if (_acumulado) {
				switch (columnIndex) {
				case 0:
					return getNombre();
				case 1:
					return get_valor();
				case 2:
					return get_margen();
				case 3:
					return get_porcentaje();
				}
			} else {
				if (columnIndex == 0)
					return getNombre();
				else if (columnIndex == 13) {
					double total = 0D;
					for (int i=1; i<=12; i++)
						total += (_saldosRealesMap != null && _saldosRealesMap.get(i) != null && ((Double) _saldosRealesMap.get(i)).doubleValue() != 0D)?((Double) _saldosRealesMap.get(i)).doubleValue():0D;	
					//if (_tipoCuenta != null && _tipoCuenta.getDebehaber().equals("H"))
					if (_tipoCuenta != null && !_tipoCuenta.getDebehaber().equals("H"))
						total = total * -1D;
					
					String saldo = "";
					if (total != 0D)
						saldo = formatoDecimal.format(Utilitarios.redondeoValor(total));
					if (_tabla.equals("Total")) {
						if (_etiqueta!=null && !_etiqueta.equals("") && _etiqueta.contains("<html><b>UTILIDAD / PERDIDA DEL EJERCICIO: </b></html>"))
							//saldo = formatoDecimal.format(Utilitarios.redondeoValor(total * -1D));
							saldo = formatoDecimal.format(Utilitarios.redondeoValor(total));
						saldo = "<html><b>" + saldo + "</b></html>";
					}
					return saldo;
				} else {
					double valor = (_saldosMap != null && _saldosMap.get(columnIndex) != null && ((Double) _saldosMap.get(columnIndex)).doubleValue() != 0D)?((Double) _saldosMap.get(columnIndex)).doubleValue():0D;
					//if (_tipoCuenta != null && _tipoCuenta.getDebehaber().equals("H"))
					if (_tipoCuenta != null && !_tipoCuenta.getDebehaber().equals("H"))
						valor = valor * -1D;
					String saldo = "";
					if (valor != 0D)
						saldo = formatoDecimal.format(Utilitarios.redondeoValor(valor));
					if (_tabla.equals("Total")) {
						if (_etiqueta!=null && !_etiqueta.equals("") && _etiqueta.contains("<html><b>UTILIDAD / PERDIDA DEL EJERCICIO: </b></html>"))
							//saldo = formatoDecimal.format(Utilitarios.redondeoValor(valor * -1D));
							saldo = formatoDecimal.format(Utilitarios.redondeoValor(valor));
						if (valor <= 0D && _etiqueta!=null && !_etiqueta.equals("") && (_etiqueta.contains("% PARTICIPACION TRABAJADORES: </b></html>") ||  _etiqueta.contains("% IMPUESTO A LA RENTA: </b></html>")))
							saldo = formatoDecimal.format(0D);
						saldo = "<html><b>" + saldo + "</b></html>";
					}
					return saldo;
				}
			}
		} catch (SecurityException se) {
			
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
		if (_hijos != null)
			return _hijos;
		
		try {
			if (!_esHoja) {
				List hijos = new ArrayList();
				ArrayList hijosHoja = new ArrayList();
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
						MyRowTreeTableEstadoResultados fila = null;
						
						if (_acumulado) {
							Double saldo = (Double) getCuentasEstadoResultadosMap().get(cuentaHijaIf.getId());
							
							if (losHijos.size()>0) {
								if (saldo != null && (Utilitarios.redondeoValor(saldo) > 0D || Utilitarios.redondeoValor(saldo) < 0D))
									fila = new MyRowTreeTableEstadoResultados(cuentaHijaIf, "CuentaPadre", formatoDecimal.format(saldo) + sangria, _nivelesVisibles, false, filtroBusqueda, null, null, _acumulado);
							} else if (saldo != null && (Utilitarios.redondeoValor(saldo) > 0D || Utilitarios.redondeoValor(saldo) < 0D)) {
								fila = new MyRowTreeTableEstadoResultados(cuentaHijaIf, "Cuenta", formatoDecimal.format(saldo) + sangria, _nivelesVisibles, false, filtroBusqueda, null, null, _acumulado);
							}
						} else {
							Map saldosMap = (Map) getCuentasEstadoResultadosMap().get(cuentaHijaIf.getId());
							
							if (losHijos.size()>0) {
								if (saldosMap != null)
									fila = new MyRowTreeTableEstadoResultados(cuentaHijaIf, "CuentaPadre", saldosMap, saldosMap, _nivelesVisibles, false, filtroBusqueda, null, null, _acumulado, 0L);
							} else if (saldosMap != null) {
								fila = new MyRowTreeTableEstadoResultados(cuentaHijaIf, "Cuenta", saldosMap, saldosMap, _nivelesVisibles, false, filtroBusqueda, null, null, _acumulado, 0L);
							}
						}
						
						//Voy añadiendo cada una de las filas, fijandome si es hoja o no.
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
		if (o instanceof MyRowTreeTableEstadoResultados) {
			MyRowTreeTableEstadoResultados fileRow = (MyRowTreeTableEstadoResultados) o;
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
	
	public static java.sql.Date getFechaFin() {
		return fechaFin;
	}
	
	public static void setFechaFin(java.sql.Date fechaFin) {
		MyRowTreeTableEstadoResultados.fechaFin = fechaFin;
	}
	
	public static java.sql.Date getFechaInicio() {
		return fechaInicio;
	}
	
	public static void setFechaInicio(java.sql.Date fechaInicio) {
		MyRowTreeTableEstadoResultados.fechaInicio = fechaInicio;
	}
	
	public String get_margen() {
		return _margen;
	}
	
	public void set_margen(String _margen) {
		this._margen = _margen;
	}
	
	public String get_porcentaje() {
		return _porcentaje;
	}
	
	public void set_porcentaje(String _porcentaje) {
		this._porcentaje = _porcentaje;
	}
	
	public static LinkedHashMap getCuentasEstadoResultadosMap() {
		return cuentasEstadoResultadosMap;
	}
	
	public static void setCuentasEstadoResultadosMap(LinkedHashMap cuentasEstadoResultadosMap) {
		MyRowTreeTableEstadoResultados.cuentasEstadoResultadosMap = cuentasEstadoResultadosMap;
	}
	
	public static Vector<CuentaIf> getCuentasEstadoResultadosTreeTable() {
		return cuentasEstadoResultadosTreeTable;
	}
	
	public static void setCuentasEstadoResultadosTreeTable(Vector<CuentaIf> cuentasEstadoResultadosTreeTable) {
		MyRowTreeTableEstadoResultados.cuentasEstadoResultadosTreeTable = cuentasEstadoResultadosTreeTable;
	}

	public static Map getTiposCuentaMap() {
		return tiposCuentaMap;
	}

	public static void setTiposCuentaMap(Map tiposCuentaMap) {
		MyRowTreeTableEstadoResultados.tiposCuentaMap = tiposCuentaMap;
	}
}
