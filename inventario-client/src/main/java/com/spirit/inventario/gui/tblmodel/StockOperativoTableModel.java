package com.spirit.inventario.gui.tblmodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.spirit.inventario.helper.KardexData;
import com.spirit.inventario.helper.StockInventarioData;
import com.spirit.inventario.helper.StockOperativoDataModel;

public class StockOperativoTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<StockOperativoDataModel> dataList = null;
	private List<ColumnModel> listaColumnas = null;

	public StockOperativoTableModel() {
		listaColumnas = new ArrayList<ColumnModel>();
		dataList = new ArrayList<StockOperativoDataModel>();
		listaColumnas.add(new ColumnModel("Producto", false, 80));
		listaColumnas.add(new ColumnModel("Min", true, 80));
		listaColumnas.add(new ColumnModel("Max", true, 80));
		listaColumnas.add(new ColumnModel("Tiempo Min. Reposicion", true, 80));
	}

	public void addRow(StockOperativoDataModel kardexData) {
		dataList.add(kardexData);
		fireTableDataChanged();
	}

	public void refresh(List<StockOperativoDataModel> kardexDataList) {
		clean();
		for (StockOperativoDataModel kardexData : kardexDataList) {
			dataList.add(kardexData);
		}
		fireTableDataChanged();
	}

	public void clean() {
		int last = getRowCount();
		dataList.clear();
		fireTableRowsDeleted(0, last);
	}

	public void remove(int row) {
		dataList.remove(row);
		fireTableRowsDeleted(row, row);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return listaColumnas.get(column).isEditable();
	}

	public String getColumnName(int column) {
		return listaColumnas.get(column).getCabecera();
	}

	public int getRowCount() {
		return dataList.size();
	}

	public int getColumnCount() {
		return this.listaColumnas.size();
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		String valorStr = (String) value;
		BigDecimal valorBD = null;
		if (valorStr == null || valorStr.trim().equalsIgnoreCase("")) {
			valorBD = null;
		} else {
			valorBD = new BigDecimal(valorStr);
		}
		StockOperativoDataModel kardexData = dataList.get(rowIndex);
		switch (columnIndex) {
		case 1:
			kardexData.setMin(valorBD);
			break;
		case 2:
			kardexData.setMax(valorBD);
			break;
		case 3:
			if (valorStr == null || valorStr.trim().equalsIgnoreCase("")) {
				kardexData.setTiempoMinimoReposision(null);
			} else {
				kardexData.setTiempoMinimoReposision(new Long(valorStr));
			}

			break;
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int row, int column) {
		StockOperativoDataModel kardexData = dataList.get(row);
		switch (column) {
		case 0:// 
			return kardexData.getProducto();
		case 1:
			return kardexData.getMin();
		case 2:
			return kardexData.getMax();
		case 3:
			return kardexData.getTiempoMinimoReposision();
		default:
			return null;
		}
	}

	public List<StockOperativoDataModel> getListaStockOperativoDataModel() {
		return dataList;
	}

}