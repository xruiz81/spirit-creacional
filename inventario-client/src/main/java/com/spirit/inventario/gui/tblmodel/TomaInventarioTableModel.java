package com.spirit.inventario.gui.tblmodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.spirit.inventario.helper.StockInventarioData;

public class TomaInventarioTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<StockInventarioData> listaStock = null;
	private List<ColumnModel> listaColumnas = null;

	public TomaInventarioTableModel() {
		listaColumnas = new ArrayList<ColumnModel>();
		listaStock = new ArrayList<StockInventarioData>();
		listaColumnas.add(new ColumnModel("Bodega", false, 80));
		listaColumnas.add(new ColumnModel("Producto", false, 80));
		listaColumnas.add(new ColumnModel("Stock", false, 80));
		listaColumnas.add(new ColumnModel("Stock Físico", true, 80));
	}

	public void addRow(StockInventarioData inventarioData) {
		listaStock.add(inventarioData);
		fireTableDataChanged();
	}

	public void refresh(List<StockInventarioData> kardexDataList) {
		clean();
		for (StockInventarioData inventarioData : kardexDataList) {
			listaStock.add(inventarioData);
		}
		System.out.println("tamaño cliente_:"+listaStock.size());
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

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		StockInventarioData inventarioData=listaStock.get(rowIndex);
		switch (columnIndex) {
		case 3:
			inventarioData.setCantidadFisica(new BigDecimal((String)value));
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	
	public Object getValueAt(int row, int column) {
		StockInventarioData inventarioData = listaStock.get(row);
		switch (column) {
		case 0: // SUCURSAL
			return inventarioData.getBodega();
		case 1:// 
			return inventarioData.getProducto();
		case 2:
			return inventarioData.getCantidad();
		case 3:
			return inventarioData.getCantidadFisica();
		default:
			return null;
		}
	}

	public List<StockInventarioData> getListaStock() {
		return listaStock;
	}

}