package com.spirit.cartera.gui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ApplyingDocumentsTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -2014485275595938577L;
	private ArrayList datalist = new ArrayList();
	
	private String[] columns = {"Transacci\u00f3n", "Saldo", 
			"Valor a aplicar", "Fecha de aplicaci\u00f3n"};
	public ApplyingDocumentsTableWidget getWidgetAt(int row) {
		return (ApplyingDocumentsTableWidget)datalist.get(row);
	}
	public ApplyingDocumentsTableWidget removeWidgetAt(int row) {
		return (ApplyingDocumentsTableWidget)datalist.remove(row);
	}
	public void addWidget(ApplyingDocumentsTableWidget w) {
		datalist.add(w);
		fireTableDataChanged();
	}
	public void addWidgetList(List l) {
		datalist.addAll(l);
		fireTableDataChanged();
	}
	public ApplyingDocumentsTableModel(List l) {
		datalist.addAll(l);
	}
	public ApplyingDocumentsTableModel() {
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
		ApplyingDocumentsTableWidget widget = (ApplyingDocumentsTableWidget) datalist.get(row);
		switch (col) {
		case 0:
			return widget.getTransaction();
		case 1:
			return new Double (widget.getBalance());
		case 2:
			return new Double (widget.getValueToApply());
		case 3:
			return widget.getDateToApply();
		default:
			return null;
		}
	}

	public boolean isCellEditable(int row, int col) {
		switch (col) {
		case 0: //Transaction
			return false;
		case 1: //Balance
			return false;
		case 2: //Value to apply
			return true;
		case 3: //Date for apply
			return true;
		default:
			return false;
		}
	}
	public Class getColumnClass(int col) {
		switch (col) {
		case 0: //Transaction
			return String.class;
		case 1: //Balance
			return Double.class;
		case 2: //Value to apply
			return Double.class;
		case 3: //Date for apply
			return java.util.GregorianCalendar.class;
		default:
			return null;
		}
	}
	public void setValueAt(Object value, int row, int col) {
		ApplyingDocumentsTableWidget w = (ApplyingDocumentsTableWidget)datalist.get(row);
		switch (col) {
		case 0: //Transaction
			w.setTransaction(value.toString());
			break;
		case 1: //Balance
			Double _balance = Double.parseDouble(String.valueOf(value));
			w.setBalance(_balance.doubleValue());
			break;
		case 2: //Value to apply
			//Double _valueToApply = (Double)value;
			Double _valueToApply = Double.parseDouble(String.valueOf(value));
			w.setValueToApply(_valueToApply.doubleValue());
			break;
		case 3: //Date for apply
			try {
				java.util.Date _dateToApply = new java.util.Date(((java.util.GregorianCalendar) value).getTimeInMillis());
				w.setDateToApply(_dateToApply);
			} catch (java.lang.NullPointerException ex1) {
				w.setDateToApply(new java.util.Date());
			} catch (java.lang.ClassCastException ex2) {
				w.setDateToApply(new java.util.Date());
			}
			break;
		}
	}
}
