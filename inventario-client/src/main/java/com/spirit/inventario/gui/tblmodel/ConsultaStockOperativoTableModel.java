package com.spirit.inventario.gui.tblmodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.spirit.inventario.helper.ConsultaStockOperativoData;
import com.spirit.inventario.helper.StockInventarioData;
import com.spirit.inventario.helper.StockOperativoDataModel;

public class ConsultaStockOperativoTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<ConsultaStockOperativoData> dataList = null;
	private List<ColumnModel> listaColumnas = null;

	public ConsultaStockOperativoTableModel() {
		listaColumnas = new ArrayList<ColumnModel>();
		dataList = new ArrayList<ConsultaStockOperativoData>();
		listaColumnas.add(new ColumnModel("", true, 80));
		listaColumnas.add(new ColumnModel("Bodega", false, 80));
		listaColumnas.add(new ColumnModel("Producto", false, 80));
		listaColumnas.add(new ColumnModel("Stock Actual", false, 80));
		listaColumnas.add(new ColumnModel("Min", false, 80));
		listaColumnas.add(new ColumnModel("Prom Diario", false, 80));
		listaColumnas.add(new ColumnModel("Rotacion", false, 80));
		listaColumnas.add(new ColumnModel("Tiempo Min", false, 80));
		listaColumnas.add(new ColumnModel("Adv. Stock.", false, 80));
		listaColumnas.add(new ColumnModel("Adv. Rotacion.", false, 80));
		listaColumnas.add(new ColumnModel("Cantidad Solicitada", true, 80));
	}

	public void addRow(ConsultaStockOperativoData inventarioData) {
		dataList.add(inventarioData);
		fireTableDataChanged();
	}

	public void refresh(List<ConsultaStockOperativoData> kardexDataList) {
		clean();
		for (ConsultaStockOperativoData inventarioData : kardexDataList) {
			dataList.add(inventarioData);
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

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0)
			return Boolean.class;
		else if (columnIndex == 0)
			return BigDecimal.class;
		else
			return super.getColumnClass(columnIndex);
	}

	public int getRowCount() {
		return dataList.size();
	}

	public int getColumnCount() {
		return this.listaColumnas.size();
	}

	public Object getValueAt(int row, int column) {
		ConsultaStockOperativoData inventarioData = dataList.get(row);
		switch (column) {
		case 0:
			return inventarioData.isSelected();
		case 1:
			return inventarioData.getBodega();
		case 2:
			return inventarioData.getProducto();
		case 3:
			return inventarioData.getStockActual();
		case 4:
			return inventarioData.getMin();
		case 5:
			return inventarioData.getPromedioDiario();
		case 6:
			return inventarioData.getRotacion();
		case 7:
			return inventarioData.getTiempoMinimoReposision();
		case 8:
			return inventarioData.getSemaforoStock();
		case 9:
			return inventarioData.getSemaforoRotacion();
		case 10:
			if(inventarioData.getCantidadSolicitada()==null){
			BigDecimal cantidadSolicitada=inventarioData.getMin().subtract(inventarioData.getStockActual());
			if(cantidadSolicitada.compareTo(BigDecimal.ZERO)<=0)
				inventarioData.setCantidadSolicitada(BigDecimal.ZERO);
			else
				inventarioData.setCantidadSolicitada(cantidadSolicitada);
			}
			return inventarioData.getCantidadSolicitada();
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		ConsultaStockOperativoData inventarioData = dataList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			inventarioData.setSelected((Boolean) value);
			return;
		case 10:
			System.out.println("----------"+value);
			if(value!=null && !((String)value).trim().equalsIgnoreCase(""))
				inventarioData.setCantidadSolicitada(new BigDecimal((String)value));
			else
				inventarioData.setCantidadSolicitada(null);
			return;
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public List<ConsultaStockOperativoData> getDataList() {
		return dataList;
	}

}