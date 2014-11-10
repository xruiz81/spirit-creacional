package com.spirit.contabilidad.util;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jidesoft.grid.AbstractExpandableRow;
import com.jidesoft.grid.Row;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.SubtipoAsientoIf;
import com.spirit.contabilidad.entity.TipoAsientoIf;
import com.spirit.exception.GenericBusinessException;

//Clase que va a setear las subActividades del del Estado de Flujo de Efectivo
public class MyRowTreeTableFlujoEfectivo extends AbstractExpandableRow implements Comparable {
	private List _hijos;
	private transient String _nombre;
	private transient Long _id;
	private transient String _valor;
	private transient boolean _esHoja;
	private Map filtroBusqueda;
	//Formato para presentar los valores monetarios
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private Font tipoletra = new Font("Courier", 1, 12);
	private static java.sql.Date fechaInicio;
	private static java.sql.Date fechaFin;
	private static Collection asientoDetallesColeccion;
	private static Map subtiposAsientoMap;
	
	public MyRowTreeTableFlujoEfectivo(Object objeto, String tabla, String valor ,boolean esHoja, Map filtro) {
		
		filtroBusqueda = filtro;
		
		//El constructor setea los valores segun la palabra clave que se le envie
		if(tabla.equals("TipoAsiento")){
			_nombre = ((TipoAsientoIf)objeto).getNombre();
			_id = ((TipoAsientoIf)objeto).getId();
			_valor = valor;	
		}
		else if(tabla.equals("SubTipoAsiento")){
			_nombre = ((SubtipoAsientoIf)objeto).getNombre();
			_id = ((SubtipoAsientoIf)objeto).getId();
			_valor = valor;	
		}
		else if (tabla.equals("Total")){
			_nombre = (String) objeto;
			_valor = valor;
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
	
	//Función principal que obtiene las subactivades de cada una las actividades
	//del Flujo de Efectivo
	public List getChildren() {
		
		Double totalDebe = 0D;
		Double totalHaber = 0D;
		Double totalDebeHaber = 0D;
		
		if (_hijos != null) {
			return _hijos;
		}
		try {
			if (!_esHoja) {
				List hijos = new ArrayList();
				ArrayList hijosHoja = new ArrayList();
				//Se obtiene del mapa filtrobusqueda las ip del plancuenta y periodo seteados en EstadoFlujoEfectivoModel
				Long idPlanCuenta = (Long) filtroBusqueda.get("idPlanCuenta");
				Long idPeriodo = (Long) filtroBusqueda.get("idPeriodo");
				//Obtengo de la base todos los subtipoasiento
				ArrayList subtiposAsientoList = getSubtiposAsientoByTipoIdList(getId());
				Iterator itSubtiposAsiento = subtiposAsientoList.iterator();
				
				while(itSubtiposAsiento.hasNext()){
					SubtipoAsientoIf subTipoAsientoIf = (SubtipoAsientoIf) itSubtiposAsiento.next();
					//Obtengo de la base todos los asientos detalle de cada uno de las subactivades (subtipoasiento), limitando por Periodo, PlanCuenta, Asiento, Cuenta y Fecha.
					Collection asientoDetalle = SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleBySubTipoAsientoIdByPeriodoIdByPlanCuentaIdAndByAsientoEfectivoAndByCuentaEfectivoImputableAndByFechaInicioAndFechaFin(subTipoAsientoIf.getId(), idPeriodo, idPlanCuenta, getFechaInicio(), getFechaFin());
					Iterator itAsientoDetalle = asientoDetalle.iterator();
					//Seteo en cero para volver a hacer el calculo
					totalDebe = 0D;
					totalHaber = 0D;
					totalDebeHaber = 0D;
					
					while(itAsientoDetalle.hasNext()){
						AsientoDetalleIf asientoDetalleIf = (AsientoDetalleIf) itAsientoDetalle.next();
						//Calculo los totales de Debe y Haber de cada una de las subtipoasiento (subactividades del Estado de Flujo de Efectivo)
						if (asientoDetalleIf != null) {
							if (asientoDetalleIf.getDebe() != null)
								totalDebe = totalDebe + asientoDetalleIf.getDebe().doubleValue();
							if (asientoDetalleIf.getHaber() != null)
								totalHaber = totalHaber + asientoDetalleIf.getHaber().doubleValue();
						}
					}
					//Total de cada subtipoasiento
					totalDebeHaber = totalDebe - totalHaber;
					//TreeTable
					//Armo el arbol con las subactividades de cada actividad 
					MyRowTreeTableFlujoEfectivo fila = new MyRowTreeTableFlujoEfectivo(subTipoAsientoIf, "SubTipoAsiento", formatoDecimal.format(totalDebeHaber), true, filtroBusqueda);
					//Sigo añadiendo filas x cada tipo y subtipoasiento que haya
					if (fila.esHoja()) {
						hijosHoja.add(fila);
					}
					else {
						hijos.add(fila);
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
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		return _hijos;
	}
	
	
	public int compareTo(Object o) {
		if (o instanceof MyRowTreeTableFlujoEfectivo) {
			MyRowTreeTableFlujoEfectivo fileRow = (MyRowTreeTableFlujoEfectivo) o;
			return getNombre().compareToIgnoreCase(fileRow.getNombre());
		}
		return 0;
	}
	
	private ArrayList getSubtiposAsientoByTipoIdList(Long idTipo) {
		ArrayList subtiposAsientoList = new ArrayList();
		Iterator it = getSubtiposAsientoMap().keySet().iterator();
		
		while (it.hasNext()) {
			SubtipoAsientoIf subtipoAsiento = (SubtipoAsientoIf) getSubtiposAsientoMap().get((Long) it.next());
			if (subtipoAsiento.getTipoId().compareTo(idTipo) == 0)
				subtiposAsientoList.add(subtipoAsiento);
		}
		
		return subtiposAsientoList;
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
		MyRowTreeTableFlujoEfectivo.fechaFin = fechaFin;
	}
	
	public static Collection getAsientoDetallesColeccion() {
		return asientoDetallesColeccion;
	}
	
	public static void setAsientoDetallesColeccion(Collection asientoDetallesColeccion) {
		MyRowTreeTableFlujoEfectivo.asientoDetallesColeccion = asientoDetallesColeccion;
	}
	
	public static java.sql.Date getFechaInicio() {
		return fechaInicio;
	}
	
	public static void setFechaInicio(java.sql.Date fechaInicio) {
		MyRowTreeTableFlujoEfectivo.fechaInicio = fechaInicio;
	}
	
	public static Map getSubtiposAsientoMap() {
		return subtiposAsientoMap;
	}
	
	public static void setSubtiposAsientoMap(Map subtiposAsientoMap) {
		MyRowTreeTableFlujoEfectivo.subtiposAsientoMap = subtiposAsientoMap;
	}
}
