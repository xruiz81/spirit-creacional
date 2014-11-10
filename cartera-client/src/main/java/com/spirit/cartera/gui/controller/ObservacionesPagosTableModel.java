package com.spirit.cartera.gui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.spirit.cartera.data.ObservacionesPago;

public class ObservacionesPagosTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<ObservacionesPago> listaStock = null;
	private List<ColumnModel> listaColumnas = null;

	public ObservacionesPagosTableModel() {
		listaColumnas = new ArrayList<ColumnModel>();
		listaStock = new ArrayList<ObservacionesPago>();
		listaColumnas.add(new ColumnModel("Fecha", false, 80));
		listaColumnas.add(new ColumnModel("Codigo", false, 80));
		listaColumnas.add(new ColumnModel("Tipo Doc.", false, 80));
		listaColumnas.add(new ColumnModel("Observacion", false, 80));
		listaColumnas.add(new ColumnModel("Saldo", false, 80));
	}

	public void addRow(ObservacionesPago inventarioData) {
		listaStock.add(inventarioData);
		fireTableDataChanged();
	}

	public void refresh(List<ObservacionesPago> kardexDataList) {
		clean();
		for (ObservacionesPago inventarioData : kardexDataList) {
			listaStock.add(inventarioData);
		}
		fireTableDataChanged();
	}

	public void clean() {
		int last = getRowCount();
		listaStock.clear();
		fireTableRowsDeleted(0, last);
	}

	public void remove(int row) {
		listaStock.remove(row);
		fireTableRowsDeleted(row, row);
	}

	@Override
	public void fireTableCellUpdated(int row, int column) {
		// TODO Auto-generated method stub
		super.fireTableCellUpdated(row, column);
	}
	@Override
	public boolean isCellEditable(int row, int column) {
		return listaColumnas.get(column).isEditable();
	}

	public String getColumnName(int column) {
		return listaColumnas.get(column).getCabecera();
	}

	public int getRowCount() {
		return listaStock.size();
	}

	public int getColumnCount() {
		return this.listaColumnas.size();
	}

	
	public Object getValueAt(int row, int column) {
		ObservacionesPago inventarioData = listaStock.get(row);
		switch (column) {
		case 0: // SUCURSAL
			return inventarioData.getFecha();
		case 1:// 
			return inventarioData.getCodigo();
		case 2:
			return inventarioData.getTipoDocumento();
		case 3:
			return inventarioData.getObservacion();
		case 4:
			return inventarioData.getSaldo();						
		default:
			return null;
		}
	}

	public List<ObservacionesPago> getListaObservacionesPago() {
		return listaStock;
	}

}