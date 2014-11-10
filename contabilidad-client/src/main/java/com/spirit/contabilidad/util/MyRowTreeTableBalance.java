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
import com.spirit.util.Utilitarios;

public class MyRowTreeTableBalance extends AbstractExpandableRow implements Comparable {
	private List _hijos;
	private transient String _nombre;
	private transient Long _id;
	private transient String _valor;
	private transient int _nivelesVisibles;
	private transient boolean _esHoja;
	private Map filtroBusqueda;
	private static java.sql.Date fechaInicioPeriodo;
	private static java.sql.Date fechaElegida;
	private static Vector<CuentaIf> cuentasBalanceTreeTable;
	private static LinkedHashMap cuentasBalanceMap;
	private static Map tiposCuentaMap;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	public MyRowTreeTableBalance(Object objeto, String tabla, String valor, int nivelesVisibles, boolean esHoja, Map filtro) {
		//Filtro por el cual BalanceGeneralModel envia el PlanCuenta y el Periodo
		filtroBusqueda = filtro;
		//El constructor setea los valores segun la palabra clave que se le envie
		//Palabras Clave: "Cuenta", "CuentaPadre", "Total"
		if (tabla.equals("Cuenta")) {
			_nombre = "[" + ((CuentaIf)objeto).getCodigo() + "] " + ((CuentaIf)objeto).getNombre();
			_id = ((CuentaIf)objeto).getId();
			TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(((CuentaIf)objeto).getTipocuentaId());
			Double saldo = Double.parseDouble(Utilitarios.removeDecimalFormat(valor));
			if (saldo != null && tipoCuenta.getDebehaber().equals("H"))
				saldo = saldo * -1D;
			_valor = formatoDecimal.format(saldo);	
			_nivelesVisibles = nivelesVisibles;
		} else if(tabla.equals("CuentaPadre")) {
			_nombre = "[" + ((CuentaIf)objeto).getCodigo() + "] " + ((CuentaIf)objeto).getNombre();
			_id = ((CuentaIf)objeto).getId();
			TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(((CuentaIf)objeto).getTipocuentaId());
			Double saldo = Double.parseDouble(Utilitarios.removeDecimalFormat(valor));
			if (saldo != null && tipoCuenta.getDebehaber().equals("H"))
				saldo = saldo * -1D;
			_valor = formatoDecimal.format(saldo);
			_nivelesVisibles = nivelesVisibles;
		} else if (tabla.equals("Total")) {
			_nombre = (String) objeto;
			_valor = valor;
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
	
	//Funcion principal que crea cada una de las filas del TreeTable
	public List getChildren() {
		if (_hijos != null)
			return _hijos;
		
		try {
			if (!_esHoja) {
				List hijos = new ArrayList();
				ArrayList hijosHoja = new ArrayList();
				//Coleccion de todas las cuentas hijas del padre principal cuyo Id se me envia desde BalanceGeneralModel
				//Los padres principales son: Activo, Pasivo, Capital y Utilidades Retenidas
				Vector<CuentaIf> cuentasHijas = findCuentasTreeTableByPadreId(getId());
				Iterator itCuentasHijas = cuentasHijas.iterator();
				//Mientras existan cuentas hijas del padre principal    			  
				while(itCuentasHijas.hasNext()){
					CuentaIf cuentaHijaIf = (CuentaIf) itCuentasHijas.next();
					if (cuentaHijaIf.getNivel() <= _nivelesVisibles) {
						String sangria = "";
						for (int i=1; i<cuentaHijaIf.getNivel(); i++)
							sangria += "     ";
						//Coleccion de todas las cuentas "nietas" de las cuentas padre principal
						Collection losHijos = findCuentasTreeTableByPadreId(cuentaHijaIf.getId());
						MyRowTreeTableBalance fila = null;  					
						//Chequeo si las cuentas hijas de la cuenta padre principal son tambien padres,
						//si es asi, entonces seteo el total devuelto de la funcion recursiva que trabaja con toda la familia de esa cuenta,
						//sino utilizo el total DebeHaber de las cuentas hija.
						Double saldo = (Double) getCuentasBalanceMap().get(cuentaHijaIf.getId());
						
						if (losHijos.size()>0) {
							if (saldo != null && (Utilitarios.redondeoValor(saldo) > 0D || Utilitarios.redondeoValor(saldo) < 0D))
								fila = new MyRowTreeTableBalance(cuentaHijaIf, "CuentaPadre", formatoDecimal.format(saldo) + sangria, _nivelesVisibles, false, filtroBusqueda);
						} else if (saldo != null && (Utilitarios.redondeoValor(saldo) > 0D || Utilitarios.redondeoValor(saldo) < 0D)) {
							fila = new MyRowTreeTableBalance(cuentaHijaIf, "Cuenta", formatoDecimal.format(saldo) + sangria, _nivelesVisibles, false, filtroBusqueda);
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
		if (o instanceof MyRowTreeTableBalance) {
			MyRowTreeTableBalance fileRow = (MyRowTreeTableBalance) o;
			return getNombre().compareToIgnoreCase(fileRow.getNombre());
		}
		return 0;
	}
	
	private Vector<CuentaIf> findCuentasTreeTableByPadreId(Long padreId) {
		Vector<CuentaIf> cuentasHijasVector = new Vector<CuentaIf>();
		for (int i=0; i<getCuentasBalanceTreeTable().size(); i++) {
			CuentaIf cuentaHijaIf = getCuentasBalanceTreeTable().get(i);
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
	
	public static java.sql.Date getFechaElegida() {
		return fechaElegida;
	}
	
	public static void setFechaElegida(java.sql.Date fechaElegida) {
		MyRowTreeTableBalance.fechaElegida = fechaElegida;
	}
	
	public static java.sql.Date getFechaInicioPeriodo() {
		return fechaInicioPeriodo;
	}
	
	public static void setFechaInicioPeriodo(java.sql.Date fechaInicioPeriodo) {
		MyRowTreeTableBalance.fechaInicioPeriodo = fechaInicioPeriodo;
	}
	
	public static LinkedHashMap getCuentasBalanceMap() {
		return cuentasBalanceMap;
	}
	
	public static void setCuentasBalanceMap(LinkedHashMap cuentasBalanceMap) {
		MyRowTreeTableBalance.cuentasBalanceMap = cuentasBalanceMap;
	}
	
	public static Vector<CuentaIf> getCuentasBalanceTreeTable() {
		return cuentasBalanceTreeTable;
	}
	
	public static void setCuentasBalanceTreeTable(Vector<CuentaIf> cuentasBalanceTreeTable) {
		MyRowTreeTableBalance.cuentasBalanceTreeTable = cuentasBalanceTreeTable;
	}
	
	public static Map getTiposCuentaMap() {
		return tiposCuentaMap;
	}
	
	public static void setTiposCuentaMap(Map tiposCuentaMap) {
		MyRowTreeTableBalance.tiposCuentaMap = tiposCuentaMap;
	}
}
