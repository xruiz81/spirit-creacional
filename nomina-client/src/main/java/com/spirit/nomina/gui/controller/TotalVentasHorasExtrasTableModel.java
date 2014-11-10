package com.spirit.nomina.gui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.spirit.client.SpiritConstants;

public class TotalVentasHorasExtrasTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 6492219125119813730L;

	private ArrayList datalist = new ArrayList();
	
	private String[] columns = {"Selecci\u00f3n", "Empleado", 
			"Total Ventas", "H.E. 50% Recargo", "H.E. 100% Recargo", "CONTRATO_ID"};
	
	public TotalVentasHorasExtrasTableWidget getWidgetAt(int row) {
		return (TotalVentasHorasExtrasTableWidget)datalist.get(row);
	}
	public TotalVentasHorasExtrasTableWidget removeWidgetAt(int row) {
		return (TotalVentasHorasExtrasTableWidget)datalist.remove(row);
	}
	public void addWidget(TotalVentasHorasExtrasTableWidget w) {
		datalist.add(w);
		fireTableDataChanged();
	}
	public void addWidgetList(List l) {
		datalist.addAll(l);
		fireTableDataChanged();
	}
	public TotalVentasHorasExtrasTableModel(List l) {
		datalist.addAll(l);
	}
	public TotalVentasHorasExtrasTableModel() {
	}
	public int getRowCount() {
		return datalist.size();
	}
	public String getColumnName(int i) {
		return columns[i];
	}
	public int getColumnCount() {
		return columns.length;
	}
	public Object getValueAt(int row, int col) {
		TotalVentasHorasExtrasTableWidget widget = (TotalVentasHorasExtrasTableWidget) datalist.get(row);
		switch (col) {
		case 0:
			return new Boolean(widget.getSeleccion());
		case 1:
			return widget.getEmpleado();
		case 2:
			return new Double (widget.getTotalVentas());
		case 3:
			return new Double (widget.getNumeroHorasExtras50());
		case 4:
			return new Double (widget.getNumeroHorasExtras100());
		case 5:
			return new Long (widget.getContratoId());
		default:
			return null;
		}
	}

	public boolean isCellEditable(int row, int col) {
		switch (col) {
		case 0: //Selección
			return true;
		case 1: //Empleado
			return false;
		case 2: //Total ventas
			return true;
		case 3: //Número horas extras 50% recargo
			return true;
		case 4: //Número horas extras 100% recargo
			return true;
		case 5: //Contrato ID
			return false;
		default:
			return false;
		}
	}
	public Class getColumnClass(int col) {
		switch (col) {
		case 0: //Selección
			return Boolean.class;
		case 1: //Empleado
			return String.class;
		case 2: //Total ventas
			return Double.class;
		case 3: //Número horas extras 50% recargo
			return Double.class;
		case 4: //Número horas extras 100% recargo
			return Double.class;
		case 5: //Contrato ID
			return Long.class;
		default:
			return null;
		}
	}
	public void setValueAt(Object value, int row, int col) {
		TotalVentasHorasExtrasTableWidget w = (TotalVentasHorasExtrasTableWidget)datalist.get(row);
		switch (col) {
		case 0: //Selección
			Boolean _seleccion = Boolean.parseBoolean(String.valueOf(value));
			w.setSeleccion(_seleccion.booleanValue());
			break;
		case 1: //Empleado
			w.setEmpleado(value.toString());
			break;
		case 2: //Total ventas
			Double _totalVentas = Double.parseDouble(!String.valueOf(value).trim().equals(SpiritConstants.getEmptyCharacter())?String.valueOf(value):"0");
			w.setTotalVentas(_totalVentas.doubleValue());
			break;
		case 3: //Número horas extras 50% recargo
			Double _numeroHorasExtras50 = Double.parseDouble(!String.valueOf(value).trim().equals(SpiritConstants.getEmptyCharacter())?String.valueOf(value):"0");
			w.setNumeroHorasExtras50(_numeroHorasExtras50.doubleValue());
			break;
		case 4: //Número horas extras 100% recargo
			Double _numeroHorasExtras100 = Double.parseDouble(!String.valueOf(value).trim().equals(SpiritConstants.getEmptyCharacter())?String.valueOf(value):"0");
			w.setNumeroHorasExtras100(_numeroHorasExtras100.doubleValue());
			break;
		case 5: //Contrato ID
			Long _contratoId = Long.parseLong(String.valueOf(value));
			w.setContratoId(_contratoId.longValue());
			break;
		}
	}
}
