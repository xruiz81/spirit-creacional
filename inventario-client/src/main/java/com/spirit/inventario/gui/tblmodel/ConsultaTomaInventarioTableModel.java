package com.spirit.inventario.gui.tblmodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.spirit.inventario.helper.KardexData;

public class ConsultaTomaInventarioTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<KardexData> listaKardex = null;
	private List<ColumnModel> listaColumnas = null;

	public ConsultaTomaInventarioTableModel() {
		listaColumnas = new ArrayList<ColumnModel>();
		listaKardex = new ArrayList<KardexData>();
		listaColumnas.add(new ColumnModel("Bodega", false, 80));
		listaColumnas.add(new ColumnModel("Lote", false, 80));
		listaColumnas.add(new ColumnModel("Producto", false, 80));
		listaColumnas.add(new ColumnModel("Fecha", false, 80));
		listaColumnas.add(new ColumnModel("Movimiento", false, 80));
		listaColumnas.add(new ColumnModel("Toma Fisica", false, 80));
		listaColumnas.add(new ColumnModel("Stock", false, 80));
		listaColumnas.add(new ColumnModel("Diferencia", false, 80));
	}

	public void addRow(KardexData kardexData) {
		listaKardex.add(kardexData);
		fireTableDataChanged();
	}

	public void refresh(List<KardexData> kardexDataList) {
		clean();
		for (KardexData kardexData : kardexDataList) {
			listaKardex.add(kardexData);
		}
		fireTableDataChanged();
	}

	public void clean() {
		int last = getRowCount();
		listaKardex.clear();
		fireTableRowsDeleted(0, last);
	}

	public void remove(int row) {
		listaKardex.remove(row);
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
		return listaKardex.size();
	}

	public int getColumnCount() {
		return this.listaColumnas.size();
	}

	public Object getValueAt(int row, int column) {
		KardexData kardexData = listaKardex.get(row);
		switch (column) {
		case 0:// 
			return kardexData.getBodega();
		case 1:
			return kardexData.getLote();
		case 2:
			return kardexData.getProducto();
		case 3:
			return kardexData.getFecha();
		case 4:
			return kardexData.getMovimiento();
		case 5:
			if (kardexData.getDifAnterior() != null) {
				return kardexData.getCantidad()
						.add(kardexData.getDifAnterior());
			} else {
				return "";
			}	
		case 6:
			return kardexData.getDifAnterior();
		case 7:
			return kardexData.getCantidad();
		default:
			return null;
		}
	}

	public List<KardexData> getListaKardex() {
		return listaKardex;
	}

}