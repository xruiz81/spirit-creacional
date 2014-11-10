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

//Clase que construye cada una de las filas que constituyen el treetable
public class MyRowTreeTablePlanCuentas extends AbstractExpandableRow implements Comparable {
	
	//Variables Principales
	private List _hijos;
	private transient String _nombre;
	private transient Long _id;
	private transient int _nivelesVisibles;
	private transient boolean _esHoja;
	private Map filtroBusqueda;
	private static Vector<CuentaIf> planCuentasTreeTable;
	
	//Formato para presentar los valores monetarios
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	//CONSTRUCTOR
	public MyRowTreeTablePlanCuentas(Object objeto, String tabla, int nivelesVisibles, boolean esHoja, Map filtro) {
		
		//Filtro por el cual BalanceGeneralModel envia el PlanCuenta y el Periodo
		filtroBusqueda = filtro;
		
		//El constructor setea los valores segun la palabra clave que se le envie
		//Palabras Clave: "TipoResultado", "Cuenta", "Total"
		if(tabla.equals("Cuenta")){
			_nombre = "[" + ((CuentaIf)objeto).getCodigo() + "] " + ((CuentaIf)objeto).getNombre();
			_id = ((CuentaIf)objeto).getId();	
			_nivelesVisibles = nivelesVisibles;
		}
		else if(tabla.equals("CuentaPadre")){
			_nombre = "[" + ((CuentaIf)objeto).getCodigo() + "] " + ((CuentaIf)objeto).getNombre();
			_id = ((CuentaIf)objeto).getId();
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
						//TREETABLE
						//Variable Fila que va a contener lo nodos y valores de cada fila del treetable
						MyRowTreeTablePlanCuentas fila = null;    					
						//Cheque si las cuentas hijas de la cuenta padre principal son tambien padres,
						//si es asi, entonces seteo el total devuelto de la funcion recursiva que trabaja con toda la familia de esa cuenta,
						//sino utilizo el total DebeHaber de las cuentas hija.
						if (losHijos.size()>0)
							fila = new MyRowTreeTablePlanCuentas(cuentaHijaIf, "CuentaPadre", _nivelesVisibles, false, filtroBusqueda);
						else
							fila = new MyRowTreeTablePlanCuentas(cuentaHijaIf, "Cuenta", _nivelesVisibles, false, filtroBusqueda);
						
						//Voy añadiendo cada una de las filas, fijandome si es hoja o no.
						if (fila.esHoja()) {
							hijosHoja.add(fila);
						}
						else {
							hijos.add(fila);
						}
					}
				}	
				
				hijos.addAll(hijosHoja);
				setChildren(hijos);
			}
		}
		catch (SecurityException se) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			se.printStackTrace();
		}
		return _hijos;
	}
	
	public int compareTo(Object o) {
		if (o instanceof MyRowTreeTablePlanCuentas) {
			MyRowTreeTablePlanCuentas fileRow = (MyRowTreeTablePlanCuentas) o;
			return getNombre().compareToIgnoreCase(fileRow.getNombre());
		}
		return 0;
	}
	
	private Vector<CuentaIf> findCuentasTreeTableByPadreId(Long padreId) {
		Vector<CuentaIf> cuentasHijasVector = new Vector<CuentaIf>();
		for (int i=0; i<getPlanCuentasTreeTable().size(); i++) {
			CuentaIf cuentaHijaIf = getPlanCuentasTreeTable().get(i);
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
	
	/*public static LinkedHashMap getPlanCuentasMap() {
		return planCuentasMap;
	}
	
	public static void setPlanCuentasMap(LinkedHashMap planCuentasMap) {
		MyRowTreeTablePlanCuentas.planCuentasMap = planCuentasMap;
	}*/
	
	public static Vector<CuentaIf> getPlanCuentasTreeTable() {
		return planCuentasTreeTable;
	}
	
	public static void setPlanCuentasTreeTable(Vector<CuentaIf> planCuentasTreeTable) {
		MyRowTreeTablePlanCuentas.planCuentasTreeTable = planCuentasTreeTable;
	}
}
